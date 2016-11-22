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
package org.gedcom4j.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;

import java.io.IOException;

import org.gedcom4j.exception.GedcomParserException;
import org.junit.Test;

/**
 * Test copy constructor for {@link NoteRecord}
 * 
 * @author frizbog
 */
public class NoteRecordCopyTest extends AbstractCopyTest {

    /**
     * Test copying a null {@link NoteRecord}, which should never work
     */
    @SuppressWarnings("unused")
    @Test(expected = NullPointerException.class)
    public void testCopyNull() {
        new NoteRecord((NoteRecord) null);
    }

    /**
     * Test the simplest possible scenario - copy a new default {@link NoteRecord}
     */
    @Test
    public void testSimplestPossible() {
        NoteRecord orig = new NoteRecord("@N1@");
        NoteRecord copy = new NoteRecord(orig);
        assertEquals(orig, copy);
        assertNotSame(orig, copy);
    }

    /**
     * Test with values
     */
    @Test
    public void testValues() {
        NoteRecord orig = new NoteRecord("@N1@");
        ChangeDate changeDate = new ChangeDate();
        changeDate.setDate("01 JAN 1970");
        orig.setChangeDate(changeDate);
        orig.setRecIdNumber("A");
        orig.setXref("B");
        orig.getLines(true).add("Line 1");
        orig.getLines(true).add("Line 2");
        orig.getLines(true).add("Line 3");

        orig.getCitations(true).add(getTestCitation());
        orig.getCitations(true).add(new CitationWithSource());
        orig.getCustomFacts(true).add(getTestCustomFact());

        orig.getUserReferences(true).add(new UserReference());

        // Copy and compare
        NoteRecord copy = new NoteRecord(orig);
        assertEquals(orig, copy);
        assertNotSame(orig, copy);

        assertEquals(orig.toString(), copy.toString());

        orig.getLines().set(0, "0th Line");
        assertEquals("Copy should not change when original does", "Line 1", copy.getLines().get(0));

        orig.getCustomFacts().clear();
        assertEquals("Copy should not change when original does", "_HOWDY", copy.getCustomFacts().get(0).getTag());
    }

    /**
     * Test with a loaded file
     * 
     * @throws IOException
     *             if the file cannot be read
     * @throws GedcomParserException
     *             if the file cannot be parsed
     */
    @Test
    public void testWithLoadedFile() throws IOException, GedcomParserException {
        Gedcom loadedGedcom = getLoadedGedcom();

        for (NoteRecord original : loadedGedcom.getNotes().values()) {
            NoteRecord copy = new NoteRecord(original);
            assertNotSame(original, copy);
            assertEquals(original, copy);
        }
    }
}
