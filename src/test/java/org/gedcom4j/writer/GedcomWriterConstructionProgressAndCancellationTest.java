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

import java.io.IOException;

import org.gedcom4j.exception.GedcomParserException;
import org.gedcom4j.exception.GedcomWriterException;
import org.gedcom4j.exception.WriterCancelledException;
import org.gedcom4j.io.writer.NullOutputStream;
import org.gedcom4j.model.Gedcom;
import org.gedcom4j.parser.GedcomParser;
import org.gedcom4j.validate.Validator;
import org.gedcom4j.writer.event.ConstructProgressEvent;
import org.gedcom4j.writer.event.ConstructProgressListener;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Test the ability to receive notifications about GEDCOM string construction, and to cancel the operation
 * 
 * @author frizbog
 */
public class GedcomWriterConstructionProgressAndCancellationTest implements ConstructProgressListener {

    /**
     * Number of notifications received
     */
    private int constructionNotificationCount = 0;

    /**
     * How many notifications to cancel after
     */
    private int cancelAfter = Integer.MAX_VALUE;

    /**
     * The GedcomWriter we're testing with
     */
    private GedcomWriter gw;

    /**
     * {@inheritDoc}
     */
    @Override
    public void progressNotification(ConstructProgressEvent e) {
        constructionNotificationCount++;
        Assert.assertTrue(e.isComplete());
        Assert.assertTrue(e.toString().startsWith("ConstructProgressEvent"));
        if (constructionNotificationCount >= cancelAfter) {
            gw.cancel();
        }
    }

    /**
     * Set up before each test
     */
    @Before
    public void setUp() {
        constructionNotificationCount = 0;
    }

    /**
     * Test with cancelling after getting a couple notifications
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
    public void testCancellation() throws IOException, GedcomParserException, GedcomWriterException {
        GedcomParser gp = new GedcomParser();
        gp.load("sample/willis-ascii.ged");
        Gedcom g = gp.getGedcom();
        Validator gv = new Validator(g);
        gv.setAutoRepairResponder(Validator.AUTO_REPAIR_ALL);
        gv.validate(); // Cleanup whatever can be cleaned up
        gw = new GedcomWriter(g);
        gw.setValidationSuppressed(true);
        gw.registerConstructObserver(this);
        cancelAfter = 5;
        gw.write(new NullOutputStream());
    }

    /**
     * Test changing construction notification rate
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
    public void testChangingConstructionNotificationRate() throws IOException, GedcomParserException, GedcomWriterException {
        GedcomParser gp = new GedcomParser();
        gp.load("sample/willis-ascii.ged");
        Gedcom g = gp.getGedcom();
        Validator gv = new Validator(g);
        gv.setAutoRepairResponder(Validator.AUTO_REPAIR_ALL);
        gv.validate(); // Cleanup whatever can be cleaned up
        gw = new GedcomWriter(g);
        gw.setValidationSuppressed(true);
        gw.registerConstructObserver(this);
        gw.setConstructionNotificationRate(1);
        cancelAfter = 5;
        gw.write(new NullOutputStream());
        assertEquals(5, constructionNotificationCount);
    }

    /**
     * Test without cancelling
     * 
     * @throws IOException
     *             if the file can't be read
     * @throws GedcomParserException
     *             if the file can't be parsed
     * @throws GedcomWriterException
     *             if the file can't be written (or is cancelled)
     */
    @SuppressWarnings("resource")
    @Test
    public void testNoCancellation() throws IOException, GedcomParserException, GedcomWriterException {
        GedcomParser gp = new GedcomParser();
        gp.load("sample/willis-ascii.ged");
        Gedcom g = gp.getGedcom();
        Validator gv = new Validator(g);
        gv.setAutoRepairResponder(Validator.AUTO_REPAIR_ALL);
        gv.validate(); // Cleanup whatever can be cleaned up
        gw = new GedcomWriter(g);
        gw.setValidationSuppressed(true);
        gw.registerConstructObserver(this);
        gw.write(new NullOutputStream());
        assertEquals(40, constructionNotificationCount);
    }

    /**
     * Try setting the notification rate below the minimum value
     * 
     * @throws WriterCancelledException
     *             if the writer gets cancelled, which won't happen in this test
     */
    @Test(expected = IllegalArgumentException.class)
    public void testSetConstructionNotificationRateNegativeTest() throws WriterCancelledException {
        new GedcomWriter(new Gedcom()).setConstructionNotificationRate(0);
    }

}
