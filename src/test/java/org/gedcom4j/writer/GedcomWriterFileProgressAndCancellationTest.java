/*
 * Copyright (c) 2009-2016 Matthew R. Harrah
 *
 * MIT License
 *
 * Permission is hereby granted, free of charge, to any person
 * obtaining a copy of this software and associated documentation
 * files (the "Software"), to deal in the Software without
 * restriction, including without limitation the rights to use,
 * copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the
 * Software is furnished to do so, subject to the following
 * conditions:
 *
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES
 * OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
 * WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
 * OTHER DEALINGS IN THE SOFTWARE.
 */
package org.gedcom4j.writer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.FileOutputStream;
import java.io.IOException;

import org.gedcom4j.exception.GedcomParserException;
import org.gedcom4j.exception.GedcomWriterException;
import org.gedcom4j.exception.WriterCancelledException;
import org.gedcom4j.io.encoding.Encoding;
import org.gedcom4j.io.event.FileProgressEvent;
import org.gedcom4j.io.event.FileProgressListener;
import org.gedcom4j.io.writer.LineTerminator;
import org.gedcom4j.io.writer.NullOutputStream;
import org.gedcom4j.model.AbstractEvent;
import org.gedcom4j.model.CharacterSet;
import org.gedcom4j.model.Family;
import org.gedcom4j.model.Gedcom;
import org.gedcom4j.model.GedcomVersion;
import org.gedcom4j.model.Individual;
import org.gedcom4j.model.StringWithCustomFacts;
import org.gedcom4j.model.enumerations.SupportedVersion;
import org.gedcom4j.parser.GedcomParser;
import org.gedcom4j.validate.Validator;
import org.gedcom4j.validate.Validator.Finding;
import org.gedcom4j.writer.event.ConstructProgressEvent;
import org.gedcom4j.writer.event.ConstructProgressListener;
import org.junit.Test;

/**
 * Test the ability to receive notifications about GEDCOM string construction, and to cancel the operation
 * 
 * @author frizbog
 */
@SuppressWarnings("PMD.TooManyMethods")
public class GedcomWriterFileProgressAndCancellationTest implements ConstructProgressListener, FileProgressListener {

    /**
     * The number of notifications we are going to cancel after, when we're testing cancellation
     */
    private static final int TEST_CANCELLATION_COUNT = 5;

    /**
     * Number of construction notifications received
     */
    private int constructNotificationCount = 0;

    /**
     * How many construction notifications to cancel after
     */
    private int constructionCancelAfter = Integer.MAX_VALUE;

    /**
     * How many lines were constructed
     */
    private int linesConstructed = 0;

    /**
     * How many bytes were written
     */
    private int bytesWritten = 0;

    /**
     * The GedcomWriter we're testing with
     */
    private GedcomWriter gw;

    /**
     * The number of file notifications received
     */
    private int fileNotificationCount = 0;

    /**
     * The number of file notifications to cancel after
     */
    private int fileCancelAfter = Integer.MAX_VALUE;

    /**
     * {@inheritDoc}
     */
    @Override
    public void progressNotification(ConstructProgressEvent e) {
        constructNotificationCount++;
        linesConstructed = e.getLinesProcessed();
        if (constructNotificationCount >= constructionCancelAfter) {
            gw.cancel();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void progressNotification(FileProgressEvent e) {
        fileNotificationCount++;
        bytesWritten = e.getBytesProcessed();
        if (fileNotificationCount > fileCancelAfter) {
            gw.cancel();
        }
    }

    /**
     * Test with cancelling gedcom construction after getting a couple notifications
     * 
     * @throws IOException
     *             if the file can't be read
     * @throws GedcomParserException
     *             if the file can't be parsed
     * @throws GedcomWriterException
     *             if the file can't be written (or is cancelled)
     */
    @SuppressWarnings("resource")
    @Test(expected = WriterCancelledException.class)
    public void testConstructionCancellation() throws IOException, GedcomParserException, GedcomWriterException {
        GedcomParser gp = new GedcomParser();
        gp.load("sample/willis-ascii.ged");
        gw = new GedcomWriter(gp.getGedcom());
        gw.setAutoRepairResponder(Validator.AUTO_REPAIR_ALL);
        gw.registerConstructObserver(this);
        gw.registerFileObserver(this);
        constructionCancelAfter = TEST_CANCELLATION_COUNT;
        gw.setValidationSuppressed(true);
        gw.write(new NullOutputStream());
    }

    /**
     * Test with cancelling file write after getting a couple notifications
     * 
     * @throws IOException
     *             if the file can't be read
     * @throws GedcomParserException
     *             if the file can't be parsed
     * @throws GedcomWriterException
     *             if the file can't be written (or is cancelled)
     */
    @SuppressWarnings("resource")
    @Test(expected = WriterCancelledException.class)
    public void testFileCancellation() throws IOException, GedcomParserException, GedcomWriterException {
        GedcomParser gp = new GedcomParser();
        gp.load("sample/willis-ascii.ged");
        gw = new GedcomWriter(gp.getGedcom());
        gw.setAutoRepairResponder(Validator.AUTO_REPAIR_ALL);
        gw.registerConstructObserver(this);
        gw.registerFileObserver(this);
        gw.setValidationSuppressed(true);
        fileCancelAfter = TEST_CANCELLATION_COUNT;
        gw.write(new NullOutputStream());
    }

    /**
     * Test without cancelling, using ANSEL file
     * 
     * @throws IOException
     *             if the file can't be read
     * @throws GedcomParserException
     *             if the file can't be parsed
     * @throws GedcomWriterException
     *             if the file can't be written (or is cancelled)
     */
    @SuppressWarnings({ "resource", "PMD.SystemPrintln" })
    @Test
    public void testNoCancellationAnselCrlf() throws IOException, GedcomParserException, GedcomWriterException {
        GedcomParser gp = new GedcomParser();
        gp.load("sample/willis-ansel.ged");
        cleanUpGedcom(gp, Encoding.ANSEL);
        Gedcom g = gp.getGedcom();
        Validator gv = new Validator(g);
        gv.setAutoRepairResponder(Validator.AUTO_REPAIR_ALL);
        gv.validate(); // cleanup whatever it can
        gw = new GedcomWriter(g);
        gw.setLineTerminator(LineTerminator.CRLF);
        gw.setAutoRepairResponder(Validator.AUTO_REPAIR_ALL);
        gw.registerConstructObserver(this);
        gw.registerFileObserver(this);
        gw.setValidationSuppressed(true);
        try {
            gw.write(new NullOutputStream());
        } catch (GedcomWriterException e) {
            for (Finding f : gw.getValidator().getResults().getAllFindings()) {
                System.out.println(f);
            }
            throw e;
        }
        assertTrue(constructNotificationCount > TEST_CANCELLATION_COUNT);
        assertTrue(fileNotificationCount > TEST_CANCELLATION_COUNT);
        assertEquals(19996, linesConstructed);
        assertEquals(606594, bytesWritten);
    }

    /**
     * Test without cancelling, using ANSEL file
     * 
     * @throws IOException
     *             if the file can't be read
     * @throws GedcomParserException
     *             if the file can't be parsed
     * @throws GedcomWriterException
     *             if the file can't be written (or is cancelled)
     */
    @SuppressWarnings({ "resource", "PMD.SystemPrintln" })
    @Test
    public void testNoCancellationAnselCrOnly() throws IOException, GedcomParserException, GedcomWriterException {
        GedcomParser gp = new GedcomParser();
        gp.load("sample/willis-ansel.ged");
        cleanUpGedcom(gp, Encoding.ANSEL);
        Gedcom g = gp.getGedcom();
        Validator gv = new Validator(g);
        gv.setAutoRepairResponder(Validator.AUTO_REPAIR_ALL);
        gv.validate(); // cleanup whatever it can
        gw = new GedcomWriter(g);
        gw.setLineTerminator(LineTerminator.CR_ONLY);
        gw.registerConstructObserver(this);
        gw.registerFileObserver(this);
        gw.setValidationSuppressed(true);
        try {
            gw.write(new NullOutputStream());
        } catch (GedcomWriterException e) {
            for (Finding f : gw.getValidator().getResults().getAllFindings()) {
                System.out.println(f);
            }
            throw e;
        }
        assertTrue(constructNotificationCount > TEST_CANCELLATION_COUNT);
        assertTrue(fileNotificationCount > TEST_CANCELLATION_COUNT);
        assertEquals(19996, linesConstructed);
        assertEquals(586598, bytesWritten);
    }

    /**
     * Test without cancelling, using Ascii file
     * 
     * @throws IOException
     *             if the file can't be read
     * @throws GedcomParserException
     *             if the file can't be parsed
     * @throws GedcomWriterException
     *             if the file can't be written (or is cancelled)
     */
    @SuppressWarnings({ "resource", "PMD.SystemPrintln" })
    @Test
    public void testNoCancellationAsciiCrlf() throws IOException, GedcomParserException, GedcomWriterException {
        GedcomParser gp = new GedcomParser();
        gp.load("sample/willis-ascii.ged");
        cleanUpGedcom(gp, Encoding.ASCII);
        Gedcom g = gp.getGedcom();
        Validator gv = new Validator(g);
        gv.setAutoRepairResponder(Validator.AUTO_REPAIR_ALL);
        gv.validate(); // cleanup whatever it can
        gw = new GedcomWriter(g);
        gw.setLineTerminator(LineTerminator.CRLF);
        gw.setAutoRepairResponder(Validator.AUTO_REPAIR_ALL);
        gw.registerConstructObserver(this);
        gw.registerFileObserver(this);
        gw.setValidationSuppressed(true);
        try {
            gw.write(new NullOutputStream());
        } catch (GedcomWriterException e) {
            for (Finding f : gw.getValidator().getResults().getAllFindings()) {
                System.out.println(f);
            }
            throw e;
        }
        assertTrue(constructNotificationCount > TEST_CANCELLATION_COUNT);
        assertTrue(fileNotificationCount > TEST_CANCELLATION_COUNT);
        assertEquals(19996, linesConstructed);
        assertEquals(606594, bytesWritten);
    }

    /**
     * Test without cancelling, using Ascii file
     * 
     * @throws IOException
     *             if the file can't be read
     * @throws GedcomParserException
     *             if the file can't be parsed
     * @throws GedcomWriterException
     *             if the file can't be written (or is cancelled)
     */
    @SuppressWarnings({ "resource", "PMD.SystemPrintln" })
    @Test
    public void testNoCancellationAsciiCrOnly() throws IOException, GedcomParserException, GedcomWriterException {
        GedcomParser gp = new GedcomParser();
        gp.load("sample/willis-ascii.ged");
        cleanUpGedcom(gp, Encoding.ASCII);
        Gedcom g = gp.getGedcom();
        Validator gv = new Validator(g);
        gv.setAutoRepairResponder(Validator.AUTO_REPAIR_ALL);
        gv.validate(); // cleanup whatever it can
        gw = new GedcomWriter(g);
        gw.setLineTerminator(LineTerminator.CR_ONLY);
        gw.registerConstructObserver(this);
        gw.registerFileObserver(this);
        gw.setValidationSuppressed(true);
        try {
            gw.write(new NullOutputStream());
        } catch (GedcomWriterException e) {
            for (Finding f : gw.getValidator().getResults().getAllFindings()) {
                System.out.println(f);
            }
            throw e;
        }
        assertTrue(constructNotificationCount > TEST_CANCELLATION_COUNT);
        assertTrue(fileNotificationCount > TEST_CANCELLATION_COUNT);
        assertEquals(19996, linesConstructed);
        assertEquals(586598, bytesWritten);
    }

    /**
     * Test without cancelling, using Unicode Big-Endian file
     * 
     * @throws IOException
     *             if the file can't be read
     * @throws GedcomParserException
     *             if the file can't be parsed
     * @throws GedcomWriterException
     *             if the file can't be written (or is cancelled)
     */
    @SuppressWarnings({ "resource", "PMD.SystemPrintln" })
    @Test
    public void testNoCancellationUnicodeBigEndianCrlf() throws IOException, GedcomParserException, GedcomWriterException {
        GedcomParser gp = new GedcomParser();
        gp.load("sample/willis-unicode-bigendian.ged");
        cleanUpGedcom(gp, Encoding.UNICODE_BIG_ENDIAN);
        Gedcom g = gp.getGedcom();
        Validator gv = new Validator(g);
        gv.setAutoRepairResponder(Validator.AUTO_REPAIR_ALL);
        gv.validate(); // cleanup whatever it can
        gw = new GedcomWriter(g);
        gw.setLineTerminator(LineTerminator.CRLF);
        gw.registerConstructObserver(this);
        gw.registerFileObserver(this);
        gw.setValidationSuppressed(true);
        try {
            gw.write(new NullOutputStream());
        } catch (GedcomWriterException e) {
            for (Finding f : gw.getValidator().getResults().getAllFindings()) {
                System.out.println(f);
            }
            throw e;
        }
        assertTrue(constructNotificationCount > TEST_CANCELLATION_COUNT);
        assertTrue(fileNotificationCount > TEST_CANCELLATION_COUNT);
        assertEquals(19996, linesConstructed);
        assertEquals(1213216, bytesWritten);
    }

    /**
     * Test without cancelling, using Unicode Big-Endian file
     * 
     * @throws IOException
     *             if the file can't be read
     * @throws GedcomParserException
     *             if the file can't be parsed
     * @throws GedcomWriterException
     *             if the file can't be written (or is cancelled)
     */
    @SuppressWarnings({ "resource", "PMD.SystemPrintln" })
    @Test
    public void testNoCancellationUnicodeBigEndianCrOnly() throws IOException, GedcomParserException, GedcomWriterException {
        GedcomParser gp = new GedcomParser();
        gp.load("sample/willis-unicode-bigendian.ged");
        cleanUpGedcom(gp, Encoding.UNICODE_BIG_ENDIAN);
        Gedcom g = gp.getGedcom();
        Validator gv = new Validator(g);
        gv.setAutoRepairResponder(Validator.AUTO_REPAIR_ALL);
        gv.validate(); // cleanup whatever it can
        gw = new GedcomWriter(g);
        gw.setLineTerminator(LineTerminator.CR_ONLY);
        gw.registerConstructObserver(this);
        gw.registerFileObserver(this);
        gw.setValidationSuppressed(true);
        try {
            gw.write(new NullOutputStream());
        } catch (GedcomWriterException e) {
            for (Finding f : gw.getValidator().getResults().getAllFindings()) {
                System.out.println(f);
            }
            throw e;
        }
        assertTrue(constructNotificationCount > TEST_CANCELLATION_COUNT);
        assertTrue(fileNotificationCount > TEST_CANCELLATION_COUNT);
        assertEquals(19996, linesConstructed);
        assertEquals(1173224, bytesWritten);
    }

    /**
     * Test without cancelling, using Unicode Little-Endian file
     * 
     * @throws IOException
     *             if the file can't be read
     * @throws GedcomParserException
     *             if the file can't be parsed
     * @throws GedcomWriterException
     *             if the file can't be written (or is cancelled)
     */
    @SuppressWarnings({ "resource", "PMD.SystemPrintln" })
    @Test
    public void testNoCancellationUnicodeLittleEndianCrlf() throws IOException, GedcomParserException, GedcomWriterException {
        GedcomParser gp = new GedcomParser();
        gp.load("sample/willis-unicode-littleendian.ged");
        cleanUpGedcom(gp, Encoding.UNICODE_LITTLE_ENDIAN);
        Gedcom g = gp.getGedcom();
        Validator gv = new Validator(g);
        gv.setAutoRepairResponder(Validator.AUTO_REPAIR_ALL);
        gv.validate(); // cleanup whatever it can
        gw = new GedcomWriter(g);
        gw.setLineTerminator(LineTerminator.CRLF);
        gw.registerConstructObserver(this);
        gw.registerFileObserver(this);
        gw.setValidationSuppressed(true);
        try {
            gw.write(new FileOutputStream("tmp/foo-unicode-little-endian.ged"));
        } catch (GedcomWriterException e) {
            for (Finding f : gw.getValidator().getResults().getAllFindings()) {
                System.out.println(f);
            }
            throw e;
        }
        assertTrue(constructNotificationCount > TEST_CANCELLATION_COUNT);
        assertTrue(fileNotificationCount > TEST_CANCELLATION_COUNT);
        assertEquals(19996, linesConstructed);
        assertEquals(1213222, bytesWritten);
    }

    /**
     * Test without cancelling, using Unicode Little-Endian file
     * 
     * @throws IOException
     *             if the file can't be read
     * @throws GedcomParserException
     *             if the file can't be parsed
     * @throws GedcomWriterException
     *             if the file can't be written (or is cancelled)
     */
    @SuppressWarnings({ "resource", "PMD.SystemPrintln" })
    @Test
    public void testNoCancellationUnicodeLittleEndianCrOnly() throws IOException, GedcomParserException, GedcomWriterException {
        GedcomParser gp = new GedcomParser();
        gp.load("sample/willis-unicode-littleendian.ged");
        cleanUpGedcom(gp, Encoding.UNICODE_LITTLE_ENDIAN);
        Gedcom g = gp.getGedcom();
        Validator gv = new Validator(g);
        gv.setAutoRepairResponder(Validator.AUTO_REPAIR_ALL);
        gv.validate(); // cleanup whatever it can
        gw = new GedcomWriter(g);
        gw.setLineTerminator(LineTerminator.CR_ONLY);
        gw.setValidationSuppressed(true);
        gw.registerConstructObserver(this);
        gw.registerFileObserver(this);
        try {
            gw.write(new FileOutputStream("tmp/foo-unicode-little-endian.ged"));
        } catch (GedcomWriterException e) {
            for (Finding f : gw.getValidator().getResults().getAllFindings()) {
                System.out.println(f);
            }
            throw e;
        }
        assertTrue(constructNotificationCount > TEST_CANCELLATION_COUNT);
        assertTrue(fileNotificationCount > TEST_CANCELLATION_COUNT);
        assertEquals(19996, linesConstructed);
        assertEquals(1173230, bytesWritten);
    }

    /**
     * Test without cancelling, using UTF-8 file
     * 
     * @throws IOException
     *             if the file can't be read
     * @throws GedcomParserException
     *             if the file can't be parsed
     * @throws GedcomWriterException
     *             if the file can't be written (or is cancelled)
     */
    @SuppressWarnings({ "resource", "PMD.SystemPrintln" })
    @Test
    public void testNoCancellationUtf8Crlf() throws IOException, GedcomParserException, GedcomWriterException {
        GedcomParser gp = new GedcomParser();
        gp.load("sample/willis.ged");
        cleanUpGedcom(gp, Encoding.UTF_8);
        Gedcom g = gp.getGedcom();
        Validator gv = new Validator(g);
        gv.setAutoRepairResponder(Validator.AUTO_REPAIR_ALL);
        gv.validate(); // cleanup whatever it can
        gw = new GedcomWriter(g);
        gw.setLineTerminator(LineTerminator.CRLF);
        gw.registerConstructObserver(this);
        gw.registerFileObserver(this);
        gw.setValidationSuppressed(true);
        try {
            gw.write(new NullOutputStream());
        } catch (GedcomWriterException e) {
            if (gw.getValidator() != null && gw.getValidator().getResults() != null) {
                for (Finding f : gw.getValidator().getResults().getAllFindings()) {
                    System.out.println(f);
                }
            }
            throw e;
        }
        assertTrue(constructNotificationCount > TEST_CANCELLATION_COUNT);
        assertTrue(fileNotificationCount > TEST_CANCELLATION_COUNT);
        assertEquals(20945, linesConstructed);
        assertEquals(581632, bytesWritten);
    }

    /**
     * Test without cancelling, using UTF-8 file
     * 
     * @throws IOException
     *             if the file can't be read
     * @throws GedcomParserException
     *             if the file can't be parsed
     * @throws GedcomWriterException
     *             if the file can't be written (or is cancelled)
     */
    @SuppressWarnings({ "resource", "PMD.SystemPrintln" })
    @Test
    public void testNoCancellationUtf8CrOnly() throws IOException, GedcomParserException, GedcomWriterException {
        GedcomParser gp = new GedcomParser();
        gp.load("sample/willis.ged");
        cleanUpGedcom(gp, Encoding.UTF_8);
        Gedcom g = gp.getGedcom();
        Validator gv = new Validator(g);
        gv.setAutoRepairResponder(Validator.AUTO_REPAIR_ALL);
        gv.validate(); // cleanup whatever it can
        gw = new GedcomWriter(g);
        gw.setLineTerminator(LineTerminator.CR_ONLY);
        gw.registerConstructObserver(this);
        gw.registerFileObserver(this);
        gw.setValidationSuppressed(true);
        try {
            gw.write(new NullOutputStream());
        } catch (GedcomWriterException e) {
            for (Finding f : gw.getValidator().getResults().getAllFindings()) {
                if (f.getRepairs(true).isEmpty()) {
                    System.out.println(f);
                }
            }
            throw e;
        }
        assertTrue(constructNotificationCount > TEST_CANCELLATION_COUNT);
        assertTrue(fileNotificationCount > TEST_CANCELLATION_COUNT);
        assertEquals(20945, linesConstructed);
        assertEquals(557056, bytesWritten);
    }

    /**
     * Test registering/unregistering observers.
     *
     * @throws WriterCancelledException
     *             the writer cancelled exception
     */
    @Test
    public void testRegisterUnregisterObservers() throws WriterCancelledException {
        ConstructProgressListener c = new ConstructProgressListener() {
            /**
             * {@inheritDoc}
             */
            @Override
            public void progressNotification(ConstructProgressEvent e) {
                // Do nothing
            }
        };
        FileProgressListener f = new FileProgressListener() {
            /**
             * {@inheritDoc}
             */
            @Override
            public void progressNotification(FileProgressEvent e) {
                // Do nothing
            }
        };
        gw = new GedcomWriter(new Gedcom());
        assertEquals(0, gw.constructObservers.size());
        assertEquals(0, gw.fileObservers.size());
        gw.registerConstructObserver(c);
        assertEquals(1, gw.constructObservers.size());
        assertEquals(0, gw.fileObservers.size());
        gw.unregisterConstructObserver(c);
        assertEquals(0, gw.constructObservers.size());
        assertEquals(0, gw.fileObservers.size());

        gw.registerFileObserver(f);
        assertEquals(0, gw.constructObservers.size());
        assertEquals(1, gw.fileObservers.size());
        gw.unregisterFileObserver(f);
        assertEquals(0, gw.constructObservers.size());
        assertEquals(0, gw.fileObservers.size());

        // Unregistering observers that aren't observing does nothing, silently
        gw.unregisterConstructObserver(c);
        gw.unregisterFileObserver(f);

    }

    /**
     * Try setting the notification rate below the minimum value
     * 
     * @throws WriterCancelledException
     *             if the writer gets cancelled, which won't happen in this test
     */
    @Test(expected = IllegalArgumentException.class)
    public void testSetFileNotificationRateNegativeTest() throws WriterCancelledException {
        new GedcomWriter(new Gedcom()).setFileNotificationRate(0);
    }

    /**
     * Helper method to clean up bad data in the GEDCOM so it writes ok
     * 
     * @param gp
     *            the gedcom parser
     * @param encoding
     *            the encoding to use when writing
     */
    private void cleanUpGedcom(GedcomParser gp, Encoding encoding) {
        CharacterSet characterSet = new CharacterSet();
        characterSet.setCharacterSetName(new StringWithCustomFacts(encoding.getCharacterSetName()));
        gp.getGedcom().getHeader().setCharacterSet(characterSet);
        GedcomVersion gv = new GedcomVersion();
        gv.setVersionNumber(SupportedVersion.V5_5_1);
        gp.getGedcom().getHeader().setGedcomVersion(gv);
        for (Individual i : gp.getGedcom().getIndividuals().values()) {
            if (i.getEvents() != null) {
                for (AbstractEvent e : i.getEvents()) {
                    e.setDescription((String) null);
                }
            }
        }
        for (Family f : gp.getGedcom().getFamilies().values()) {
            if (f.getEvents() != null) {
                for (AbstractEvent e : f.getEvents()) {
                    e.setDescription((String) null);
                }
            }
        }
    }
}
