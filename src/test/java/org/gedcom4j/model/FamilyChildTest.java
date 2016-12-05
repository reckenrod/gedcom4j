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
 * Test for {@link FamilyChild}
 * 
 * @author reckenrod
 * 
 */
@SuppressWarnings({ "PMD.TooManyMethods", "PMD.SystemPrintln" })
public class FamilyChildTest {

    /**
     * Test for {@link FamilyChild#equals(Object)}
     */
    @Test
    @SuppressWarnings({ "PMD.EqualsNull", "PMD.ExcessiveMethodLength", "PMD.NcssMethodCount" })
    public void testEquals() {
        FamilyChild fc1 = new FamilyChild();
        assertFalse(fc1.equals(null));
        assertFalse(fc1.equals(new Gedcom()));
        assertEquals(fc1, fc1);

        FamilyChild fc2 = new FamilyChild();
        assertEquals(fc1, fc2);

        fc1.getCustomFacts(true).add(new CustomFact("TEST"));
        assertFalse(fc1.equals(fc2));
        fc2.getCustomFacts(true).add(new CustomFact("TEST"));
        assertEquals(fc1, fc2);

        fc2.setAdoptedBy(new StringWithCustomFacts("Test"));
        assertFalse(fc1.equals(fc2));
        fc1.setAdoptedBy(new StringWithCustomFacts("Test"));
        assertEquals(fc1, fc2);
        fc2.setAdoptedBy((StringWithCustomFacts) null);
        assertFalse(fc1.equals(fc2));
        fc1.setAdoptedBy((StringWithCustomFacts) null);
        assertEquals(fc1, fc2);

        fc2.setFamily(new Family());
        assertFalse(fc1.equals(fc2));
        fc1.setFamily(new Family());
        assertEquals(fc1, fc2);
        fc2.getFamily().setXref("Test");
        assertFalse(fc1.equals(fc2));
        fc1.getFamily().setXref("Test");
        assertEquals(fc1, fc2);
        fc2.setFamily((Family) null);
        assertFalse(fc1.equals(fc2));
        fc1.setFamily((Family) null);
        assertEquals(fc1, fc2);

        fc2.setPedigree(new StringWithCustomFacts("Test"));
        assertFalse(fc1.equals(fc2));
        fc1.setPedigree(new StringWithCustomFacts("Test"));
        assertEquals(fc1, fc2);
        fc2.setPedigree((StringWithCustomFacts) null);
        assertFalse(fc1.equals(fc2));
        fc1.setPedigree((StringWithCustomFacts) null);
        assertEquals(fc1, fc2);

        fc2.setStatus(new StringWithCustomFacts("Test"));
        assertFalse(fc1.equals(fc2));
        fc1.setStatus(new StringWithCustomFacts("Test"));
        assertEquals(fc1, fc2);
        fc2.setStatus((StringWithCustomFacts) null);
        assertFalse(fc1.equals(fc2));
        fc1.setStatus((StringWithCustomFacts) null);
        assertEquals(fc1, fc2);
    }
}
