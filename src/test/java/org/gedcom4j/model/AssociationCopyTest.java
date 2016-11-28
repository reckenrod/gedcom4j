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
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotSame;

import org.junit.Test;

/**
 * Test copy constructor for {@link Association}
 * 
 * @author frizbog
 */
public class AssociationCopyTest extends AbstractCopyTest {

    /**
     * Test copying a null {@link Association}, which should never work
     */
    @SuppressWarnings("unused")
    @Test(expected = NullPointerException.class)
    public void testCopyNull() {
        new Association(null);
    }

    /**
     * Test the simplest possible scenario - copy a new default {@link Association}
     */
    @Test
    public void testSimplestPossible() {
        Association orig = new Association();
        Association copy = new Association(orig);
        assertEquals(orig, copy);
        assertNotSame(orig, copy);
    }

    /**
     * Test with values
     */
    @Test
    public void testWithValues() {
        Association orig = new Association();
        orig.setAssociatedEntityType("AAA");
        orig.setAssociatedEntityXref("BBB");
        orig.setRelationship("CCC");
        AbstractCitation c = getTestCitation();
        orig.getCitations(true).add(c);
        orig.getCitations().add(new CitationWithSource());
        orig.getCustomFacts(true).add(getTestCustomFact());

        Association copy = new Association(orig);
        assertEquals(orig, copy);
        assertNotSame(orig, copy);
        assertEquals(orig.toString(), copy.toString());

        c.getNoteStructures(true).get(0).getLines(true).set(0, "Changed value");
        assertFalse("Copy shouldn't match when original gets changed", orig.equals(copy));
        assertEquals("This is a test", copy.getCitations().get(0).getNoteStructures().get(0).getLines().get(0));
    }
}
