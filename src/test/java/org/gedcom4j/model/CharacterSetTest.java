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

import org.junit.Test;

/**
 * @author frizbog1
 * 
 */
public class CharacterSetTest {

    /**
     * Test method for {@link org.gedcom4j.model.CharacterSet#equals(java.lang.Object)} .
     */
    @Test
    @SuppressWarnings("PMD.EqualsNull")
    public void testEqualsObject() {
        CharacterSet cs1 = new CharacterSet();
        assertFalse(cs1.equals(null));
        assertFalse(cs1.equals(new Corporation()));
        assertEquals(cs1, cs1);

        CharacterSet cs2 = new CharacterSet();
        assertEquals(cs1, cs2);

        cs2.setCharacterSetName("Test");
        assertFalse(cs1.equals(cs2));
        cs1.setCharacterSetName("Test");
        assertEquals(cs1, cs2);
        cs1.setCharacterSetName((String) null);
        assertFalse(cs1.equals(cs2));
        cs2.setCharacterSetName((String) null);
        assertEquals(cs1, cs2);

        cs2.setVersionNum("1");
        assertFalse(cs1.equals(cs2));
        cs1.setVersionNum("1");
        assertEquals(cs1, cs2);
        cs2.setVersionNum((String) null);
        assertFalse(cs1.equals(cs2));
        cs1.setVersionNum((String) null);
        assertEquals(cs1, cs2);
    }

    /**
     * Test method for {@link org.gedcom4j.model.CharacterSet#hashCode()}.
     */
    @Test
    public void testHashCode() {
        CharacterSet cs1 = new CharacterSet();
        CharacterSet cs2 = new CharacterSet();
        assertEquals("objects are equal, so hashcodes should match", cs1.hashCode(), cs2.hashCode());
        cs1.setCharacterSetName("Frying Pan");
        assertFalse("objects are not equal, so hashcodes should not match", cs1.hashCode() == cs2.hashCode());
        cs2.setCharacterSetName("Frying Pan");
        assertEquals("objects are equal again, so hashcodes should match", cs1.hashCode(), cs2.hashCode());
    }

}
