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
import org.gedcom4j.model.enumerations.SupportedVersion;

import org.junit.Test;

/**
 * @author frizbog1
 * 
 */
public class GedcomVersionTest {

    /**
     * Test method for {@link org.gedcom4j.model.GedcomVersion#equals(java.lang.Object)} .
     */
    @Test
    @SuppressWarnings("PMD.EqualsNull")
    public void testEqualsObject() {
        GedcomVersion gv1 = new GedcomVersion();
        assertFalse(gv1.equals(null));
        assertFalse(gv1.equals(new Corporation()));
        assertEquals(gv1, gv1);

        GedcomVersion gv2 = new GedcomVersion();
        assertEquals(gv1, gv2);

        gv2.setGedcomForm("Yo");
        assertFalse(gv1.equals(gv2));
        gv1.setGedcomForm("Yo");
        assertEquals(gv1, gv2);
        gv1.setGedcomForm((String) null);
        assertFalse(gv1.equals(gv2));
        gv2.setGedcomForm((String) null);
        assertEquals(gv1, gv2);

        gv2.setVersionNumber(new StringWithCustomFacts("Yo"));
        assertFalse(gv1.equals(gv2));
        gv1.setVersionNumber(new StringWithCustomFacts("Yo"));
        assertEquals(gv1, gv2);
        gv1.setVersionNumber("Test");
        assertFalse(gv1.equals(gv2));
        gv2.setVersionNumber("Test");
        assertEquals(gv1, gv2);
        gv1.setVersionNumber((String) null);
        assertFalse(gv1.equals(gv2));
        gv2.setVersionNumber((String) null);
        assertEquals(gv1, gv2);
        gv1.setVersionNumber((SupportedVersion) null);
        assertEquals(gv1, gv2);
    }

    /**
     * Test method for {@link org.gedcom4j.model.GedcomVersion#hashCode()}.
     */
    @Test
    public void testHashCode() {
        GedcomVersion gv1 = new GedcomVersion();
        GedcomVersion gv2 = new GedcomVersion();
        assertEquals("objects are equal, so hashcodes should equal", gv1.hashCode(), gv2.hashCode());
        gv1.setGedcomForm("Frying Pan");
        assertFalse("objects are not equal, so hashcodes should not equal", gv1.hashCode() == gv2.hashCode());
        gv2.setGedcomForm("Frying Pan");
        assertEquals("objects are equal again, so hashcodes should equal", gv1.hashCode(), gv2.hashCode());
    }
}
