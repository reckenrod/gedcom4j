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
package org.gedcom4j.io.reader;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.gedcom4j.exception.GedcomParserException;
import org.gedcom4j.exception.UnsupportedGedcomCharsetException;
import org.gedcom4j.io.event.FileProgressEvent;
import org.gedcom4j.io.event.FileProgressListener;
import org.gedcom4j.parser.GedcomParser;
import org.junit.Test;

/**
 * @author frizbog1
 * 
 */
@SuppressWarnings("PMD.TooManyMethods")
public class GedcomFileReaderTest implements FileProgressListener {

    /**
     * Get all the lines from the file as an arraylist of string
     * 
     * @param gr
     *            the {@link GedcomFileReader}
     * @return all the lines of the file
     * @throws IOException
     *             if the file cannot be read
     * @throws GedcomParserException
     *             if the file cannot be parsed or the load was cancelled
     */
    private static List<String> getLines(GedcomFileReader gr) throws IOException, GedcomParserException {
        ArrayList<String> result = new ArrayList<>();
        String s = gr.nextLine();
        while (s != null) {
            result.add(s);
            s = gr.nextLine();
        }
        return result;
    }

    /**
     * Count of notifications received
     */
    private int notificationCount = 0;

    /**
     * {@inheritDoc}
     */
    @Override
    public void progressNotification(FileProgressEvent e) {
        notificationCount++;
    }

    /**
     * A test for whether the GedcomReader properly handles multi line files with CRLF line terminators
     * 
     * @throws IOException
     *             if the data can't be read
     * @throws GedcomParserException
     *             if the file load was cancelled or had malformed data
     */
    @Test
    public void testAnselCrlf() throws IOException, GedcomParserException {
        /*
         * Some encoded ANSEL data for a file with two lines. Line one consists of a zero, a space, and an uppercase H. Line two
         * consists of a lowercase o. The lines are separated by a CRLF.
         */
        byte[] anselData = { 0x30, 0x20, 0x48, /* CRLF begin */ 0x0D, 0x0A, /* CRLF end */ 0x6F };

        try (BufferedInputStream s = new BufferedInputStream(new ByteArrayInputStream(anselData))) {
            GedcomFileReader gr = new GedcomFileReader(new GedcomParser(), s);
            List<String> lines = getLines(gr);
            assertNotNull(lines);
            assertFalse(lines.isEmpty());
            assertEquals(2, lines.size());
            assertEquals("0 H", lines.get(0));
            assertEquals("o", lines.get(1));
        }
    }

    /**
     * A test for whether the GedcomReader properly handles multi line files with CR-only line terminators
     * 
     * @throws IOException
     *             if the data can't be read
     * @throws GedcomParserException
     *             if the file load was cancelled or had malformed data
     */
    @Test
    public void testAnselCrOnly() throws IOException, GedcomParserException {
        /*
         * Some encoded ANSEL data for a file with two lines. Line one consists of a zero, a space, and an uppercase H. Line two
         * consists of a lowercase o. The lines are separated by a CR only.
         */
        byte[] anselData = { 0x30, 0x20, 0x48, 0x0D, 0x6F };

        try (BufferedInputStream s = new BufferedInputStream(new ByteArrayInputStream(anselData))) {
            GedcomFileReader gr = new GedcomFileReader(new GedcomParser(), s);
            List<String> lines = getLines(gr);
            assertNotNull(lines);
            assertFalse(lines.isEmpty());
            assertEquals(2, lines.size());
            assertEquals("0 H", lines.get(0));
            assertEquals("o", lines.get(1));
        }
    }

    /**
     * A test for whether the GedcomReader properly decodes Ansel data
     * 
     * @throws IOException
     *             if the data can't be read
     * @throws GedcomParserException
     *             if the file load was cancelled or had malformed data
     */
    @Test
    public void testAnselDecodingSingleLine() throws IOException, GedcomParserException {
        /*
         * Some encoded ANSEL data for the phrase "0 Hello", with the l's replaced by those uppercase L's with slashes through them
         * (U+0141)
         */
        byte[] anselData = { 0x30, 0x20, 0x48, 0x65, (byte) 0xA1, (byte) 0xA1, 0x6F };

        try (BufferedInputStream s = new BufferedInputStream(new ByteArrayInputStream(anselData))) {
            GedcomFileReader gr = new GedcomFileReader(new GedcomParser(), s);
            String l = gr.nextLine();
            assertNotNull(l);
            assertEquals("0 He\u0141\u0141o", l);
        }
    }

    /**
     * A test for whether the GedcomReader properly handles multi line files with LF-only line terminators
     * 
     * @throws IOException
     *             if the data can't be read
     * @throws GedcomParserException
     *             if the file load was cancelled or had malformed data
     */
    @Test
    public void testAnselLfOnly() throws IOException, GedcomParserException {
        /*
         * Some encoded ANSEL data for a file with two lines. Line one consists of a zero, a space, and an uppercase H. Line two
         * consists of a lowercase o. The lines are separated by a LF only.
         */
        byte[] anselData = { 0x30, 0x20, 0x48, 0x0A, 0x6F };

        try (BufferedInputStream s = new BufferedInputStream(new ByteArrayInputStream(anselData))) {
            GedcomFileReader gr = new GedcomFileReader(new GedcomParser(), s);
            List<String> lines = getLines(gr);
            assertNotNull(lines);
            assertFalse(lines.isEmpty());
            assertEquals(2, lines.size());
            assertEquals("0 H", lines.get(0));
            assertEquals("o", lines.get(1));
        }
    }

    /**
     * Try reading an Ascii file
     * 
     * @throws IOException
     *             if the file cannot be read
     * @throws GedcomParserException
     *             if the file cannot be parsed
     */
    @Test
    public void testAscii() throws IOException, GedcomParserException {
        try (InputStream is = new FileInputStream("sample/willis-ascii.ged");
                BufferedInputStream bis = new BufferedInputStream(is)) {
            GedcomFileReader gfr = new GedcomFileReader(new GedcomParser(), bis);
            assertNotNull(gfr.nextLine());
        }
    }

    /**
     * Test loading from an empty file
     * 
     * @throws IOException
     *             if the file cannot be read - this is the expected exception
     * @throws GedcomParserException
     *             if the file cannot be parsed or the laod is cancelled
     */
    @SuppressWarnings("unused")
    @Test(expected = IOException.class)
    public void testEmptyFile() throws IOException, GedcomParserException {
        byte[] fileBytes = {};

        try (BufferedInputStream s = new BufferedInputStream(new ByteArrayInputStream(fileBytes))) {
            new GedcomFileReader(new GedcomParser(), s);
        }
    }

    /**
     * Test loading from an empty file
     * 
     * @throws IOException
     *             if the file cannot be read - this is the expected exception
     * @throws GedcomParserException
     *             if the file cannot be parsed or the laod is cancelled
     */
    @Test
    public void testEmptyLinesOnly() throws IOException, GedcomParserException {
        byte[] fileBytes = "\n\n\n".getBytes();

        try (BufferedInputStream s = new BufferedInputStream(new ByteArrayInputStream(fileBytes))) {
            GedcomParser gp = new GedcomParser();
            gp.setReadNotificationRate(1); // Notify on every line read
            GedcomFileReader gr = new GedcomFileReader(gp, s);
            List<String> lines = getLines(gr);
            assertNotNull(lines);
        }
    }

    /**
     * Test for {@link GedcomFileReader#firstNBytes(int)}
     * 
     * @throws IOException
     *             if the first few bytes of the "file" cannot be read
     * @throws UnsupportedGedcomCharsetException
     *             if the character set is not supported
     */
    @Test
    public void testFirstNBytes() throws IOException, UnsupportedGedcomCharsetException {
        byte[] bytes = new byte[] { '0', ' ', 0x12, 0x34 };
        GedcomFileReader gfr = new GedcomFileReader(new GedcomParser(), new BufferedInputStream(new ByteArrayInputStream(bytes)));
        // Haven't save the first chunk yet
        assertNotNull(gfr.firstChunk);
        assertEquals(0x30, gfr.firstNBytes(1));
        assertEquals(0x3020, gfr.firstNBytes(2));
        assertEquals(0x302012, gfr.firstNBytes(3));
        assertEquals(0x30201234, gfr.firstNBytes(4));
    }

    /**
     * Test loading from an empty file
     * 
     * @throws IOException
     *             if the file cannot be read - this is the expected exception
     * @throws GedcomParserException
     *             if the file cannot be parsed or the laod is cancelled
     */
    @Test
    public void testTrailerOnly() throws IOException, GedcomParserException {
        byte[] fileBytes = "0 TRLR".getBytes();

        try (BufferedInputStream s = new BufferedInputStream(new ByteArrayInputStream(fileBytes))) {
            GedcomParser gp = new GedcomParser();
            gp.setReadNotificationRate(1); // Notify on every line read
            GedcomFileReader gr = new GedcomFileReader(gp, s);
            List<String> lines = getLines(gr);
            assertNotNull(lines);
        }
    }

    /**
     * Test reading unicode, big-endian, with CRLF as the line delimiter
     * 
     * @throws IOException
     *             if the data can't be read
     * @throws GedcomParserException
     *             if the file load was cancelled or had malformed data
     */
    @Test
    public void testUnicodeBigEndianWithCrlf() throws IOException, GedcomParserException {
        /*
         * Unicode, big-endian data with CRLF line terminator. Says "0 HEAD" on line 1 and "1 CHAR" on line 2.
         */
        byte[] unicodeData = { 0x00, 0x30, 0x00, 0x20, 0x00, 0x48, 0x00, 0x45, 0x00, 0x41, 0x00, 0x44, 0x00, 0x0d, 0x00, 0x0a, 0x00,
                0x31, 0x00, 0x20, 0x00, 0x43, 0x00, 0x48, 0x00, 0x41, 0x00, 0x52 };
        try (BufferedInputStream s = new BufferedInputStream(new ByteArrayInputStream(unicodeData));) {
            GedcomFileReader gr = new GedcomFileReader(new GedcomParser(), s);
            List<String> lines = getLines(gr);
            assertNotNull(lines);
            assertFalse(lines.isEmpty());
            assertEquals(2, lines.size());
            assertEquals("0 HEAD", lines.get(0));
            assertEquals("1 CHAR", lines.get(1));
        }
    }

    /**
     * Test reading unicode, big-endian, with CR only as the line delimiter
     * 
     * @throws IOException
     *             if the data can't be read
     * @throws GedcomParserException
     *             if the file load was cancelled or had malformed data
     */
    @Test
    public void testUnicodeBigEndianWithCrOnly() throws IOException, GedcomParserException {
        /*
         * Unicode, big-endian data with CRLF line terminator. Says "0 HEAD" on line 1 and "1 CHAR" on line 2.
         */
        byte[] unicodeData = { 0x00, 0x30, 0x00, 0x20, 0x00, 0x48, 0x00, 0x45, 0x00, 0x41, 0x00, 0x44, 0x00, 0x0d, 0x00, 0x31, 0x00,
                0x20, 0x00, 0x43, 0x00, 0x48, 0x00, 0x41, 0x00, 0x52 };
        try (BufferedInputStream s = new BufferedInputStream(new ByteArrayInputStream(unicodeData))) {
            GedcomFileReader gr = new GedcomFileReader(new GedcomParser(), s);
            List<String> lines = getLines(gr);
            assertNotNull(lines);
            assertFalse(lines.isEmpty());
            assertEquals(2, lines.size());
            assertEquals("0 HEAD", lines.get(0));
            assertEquals("1 CHAR", lines.get(1));
        }
    }

    /**
     * Test reading unicode data, big-endian, with only an LF as the line delimiter
     * 
     * @throws IOException
     *             if the data can't be read
     * @throws GedcomParserException
     *             if the file load was cancelled or had malformed data
     */
    @Test
    public void testUnicodeBigEndianWithLfOnly() throws IOException, GedcomParserException {
        /*
         * Unicode, big-endian data with CRLF line terminator. Says "0 HEAD" on line 1 and "1 CHAR" on line 2.
         */
        byte[] unicodeData = { 0x00, 0x30, 0x00, 0x20, 0x00, 0x48, 0x00, 0x45, 0x00, 0x41, 0x00, 0x44, 0x00, 0x0a, 0x00, 0x31, 0x00,
                0x20, 0x00, 0x43, 0x00, 0x48, 0x00, 0x41, 0x00, 0x52 };
        try (BufferedInputStream s = new BufferedInputStream(new ByteArrayInputStream(unicodeData))) {
            GedcomFileReader gr = new GedcomFileReader(new GedcomParser(), s);
            List<String> lines = getLines(gr);
            assertNotNull(lines);
            assertFalse(lines.isEmpty());
            assertEquals(2, lines.size());
            assertEquals("0 HEAD", lines.get(0));
            assertEquals("1 CHAR", lines.get(1));
        }
    }

    /**
     * Test reading unicode data, little-endian byte order, with CRLF's as the line delimiter
     * 
     * @throws IOException
     *             if the data can't be read
     * @throws GedcomParserException
     *             if the file load was cancelled or had malformed data
     */
    @Test
    public void testUnicodeLittleEndianWithCrlf() throws IOException, GedcomParserException {
        /*
         * Unicode, little-endian data with CRLF line terminator. Says "0 HEAD" on line 1 and "1 CHAR" on line 2.
         */
        byte[] unicodeData = { 0x30, 0x00, 0x20, 0x00, 0x48, 0x00, 0x45, 0x00, 0x41, 0x00, 0x44, 0x00, 0x0d, 0x00, 0x0a, 0x00, 0x31,
                0x00, 0x20, 0x00, 0x43, 0x00, 0x48, 0x00, 0x41, 0x00, 0x52, 0x00 };
        try (BufferedInputStream s = new BufferedInputStream(new ByteArrayInputStream(unicodeData))) {
            GedcomFileReader gr = new GedcomFileReader(new GedcomParser(), s);
            List<String> lines = getLines(gr);
            assertNotNull(lines);
            assertFalse(lines.isEmpty());
            assertEquals(2, lines.size());
            assertEquals("0 HEAD", lines.get(0));
            assertEquals("1 CHAR", lines.get(1));
        }
    }

    /**
     * Test reading unicode data, little-endian byte order, CR as line delimiter
     * 
     * @throws IOException
     *             if the data can't be read
     * @throws GedcomParserException
     *             if the file load was cancelled or had malformed data
     */
    @Test
    public void testUnicodeLittleEndianWithCrOnly() throws IOException, GedcomParserException {
        /*
         * Unicode, little-endian data with CRLF line terminator. Says "0 HEAD" on line 1 and "1 CHAR" on line 2.
         */
        byte[] unicodeData = { 0x30, 0x00, 0x20, 0x00, 0x48, 0x00, 0x45, 0x00, 0x41, 0x00, 0x44, 0x00, 0x0d, 0x00, 0x31, 0x00, 0x20,
                0x00, 0x43, 0x00, 0x48, 0x00, 0x41, 0x00, 0x52, 0x00 };
        try (BufferedInputStream s = new BufferedInputStream(new ByteArrayInputStream(unicodeData))) {
            GedcomFileReader gr = new GedcomFileReader(new GedcomParser(), s);
            List<String> lines = getLines(gr);
            assertNotNull(lines);
            assertFalse(lines.isEmpty());
            assertEquals(2, lines.size());
            assertEquals("0 HEAD", lines.get(0));
            assertEquals("1 CHAR", lines.get(1));
        }
    }

    /**
     * Test reading unicode data, little-endian byte order, with LF's as line delimiter
     * 
     * @throws IOException
     *             if the data can't be read
     * @throws GedcomParserException
     *             if the file load was cancelled or had malformed data
     */
    @Test
    public void testUnicodeLittleEndianWithLfOnly() throws IOException, GedcomParserException {
        /*
         * Unicode, little-endian data with CRLF line terminator. Says "0 HEAD" on line 1 and "1 CHAR" on line 2.
         */
        byte[] unicodeData = { 0x30, 0x00, 0x20, 0x00, 0x48, 0x00, 0x45, 0x00, 0x41, 0x00, 0x44, 0x00, 0x0a, 0x00, 0x31, 0x00, 0x20,
                0x00, 0x43, 0x00, 0x48, 0x00, 0x41, 0x00, 0x52, 0x00 };
        try (BufferedInputStream s = new BufferedInputStream(new ByteArrayInputStream(unicodeData))) {
            GedcomFileReader gr = new GedcomFileReader(new GedcomParser(), s);
            List<String> lines = getLines(gr);
            assertNotNull(lines);
            assertFalse(lines.isEmpty());
            assertEquals(2, lines.size());
            assertEquals("0 HEAD", lines.get(0));
            assertEquals("1 CHAR", lines.get(1));
        }
    }

    /**
     * Test reading a UTF8 file using CRLF delimiters and Byte order markers
     * 
     * @throws IOException
     *             if the data can't be read
     * @throws GedcomParserException
     *             if the file load was cancelled or had malformed data
     */
    @Test
    public void testUtf8CrLfBOM() throws IOException, GedcomParserException {
        testUtf8File("sample/utf8_crlf_bom.ged");
        assertEquals(78, notificationCount);
    }

    /**
     * Test reading a UTF8 file using CRLF delimiters and no Byte order markers
     * 
     * @throws IOException
     *             if the data can't be read
     * @throws GedcomParserException
     *             if the file load was cancelled or had malformed data
     */
    @Test
    public void testUtf8CrLfNoBOM() throws IOException, GedcomParserException {
        testUtf8File("sample/utf8_crlf_nobom.ged");
    }

    /**
     * Test reading a UTF8 file using CR delimiters and no Byte order markers
     * 
     * @throws IOException
     *             if the data can't be read
     * @throws GedcomParserException
     *             if the file load was cancelled or had malformed data
     */
    @Test
    public void testUtf8CrNoBOM() throws IOException, GedcomParserException {
        testUtf8File("sample/utf8_cr_nobom.ged");
    }

    /**
     * Test reading a UTF8 file using LF delimiters and no Byte order markers
     * 
     * @throws IOException
     *             if the data can't be read
     * @throws GedcomParserException
     *             if the file cannot be parsed or the load was cancelled
     */
    @Test
    public void testUtf8LfNoBOM() throws IOException, GedcomParserException {
        testUtf8File("sample/utf8_lf_nobom.ged");
    }

    /**
     * Test that a UTF file was read and character decoded correctly
     * 
     * @param fileName
     *            the name of the file to load and check
     * @throws IOException
     *             if the file can't be loaded
     * @throws FileNotFoundException
     *             if the file can't be found
     * @throws GedcomParserException
     *             if the file load was cancelled or had malformed data
     */
    private void testUtf8File(String fileName) throws IOException, FileNotFoundException, GedcomParserException {
        try (FileInputStream fileInputStream = new FileInputStream(fileName);
                BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream)) {

            GedcomParser gp = new GedcomParser();
            gp.registerFileObserver(this);
            gp.setReadNotificationRate(1); // Notification for every line
            GedcomFileReader gr = new GedcomFileReader(gp, bufferedInputStream);
            List<String> lines = getLines(gr);
            assertNotNull(lines);
            assertEquals(77, lines.size());
            assertEquals("2 VERS 5.5.1", lines.get(6));
            assertEquals("1 CHAR UTF-8", lines.get(8));

            // Check all the non-ascii characters in the names - just a sample but
            // should give pretty good confidence
            assertEquals("1 NAME John /Gr\u00FCber/", lines.get(10));
            assertEquals("1 NAME Mary /H\u00E6germann/", lines.get(16));
            assertEquals("1 NAME Abraham /Sm\u00EEth/", lines.get(22));
            assertEquals("1 NAME Betsy /Gro\u00DFmann/", lines.get(29));
            assertEquals("1 NAME Cleo /N\u00F4rden/", lines.get(36));
            assertEquals("1 NAME Elizabeth /J\u00e5ckson/", lines.get(39));
            assertEquals("1 NAME Daniel /\u0106uar\u00f3n/", lines.get(42));
            assertEquals("1 NAME Michael /Gar\u00E7on/", lines.get(46));
            assertEquals("1 NAME Ellen /\u0141owenst\u0117in/", lines.get(49));
            assertEquals("1 NAME Fred /\u00DBlrich/", lines.get(53));
        }

    }
}
