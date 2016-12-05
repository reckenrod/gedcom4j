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

import org.gedcom4j.model.enumerations.LdsIndividualOrdinanceType;
import org.junit.Test;

/**
 * @author reckenrod
 * 
 */
@SuppressWarnings({ "PMD.TooManyMethods", "PMD.SystemPrintln" })
public class LdsIndividualOrdinanceTest {

    /**
     * Test method for {@link LdsLdsIndividualOrdinanceOrdinance} .
     */
    @Test
    public void testEquals() {
        LdsIndividualOrdinance lio1 = new LdsIndividualOrdinance();
        assertFalse(lio1.equals(null));
        assertFalse(lio1.equals(new Corporation()));
        assertEquals(lio1, lio1);

        LdsIndividualOrdinance lio2 = new LdsIndividualOrdinance();
        assertEquals(lio1, lio2);

        lio2.setFamilyWhereChild(new FamilyChild());
        assertFalse(lio1.equals(lio2));
        lio1.setFamilyWhereChild(new FamilyChild());
        assertEquals(lio1, lio2);
        lio2.setFamilyWhereChild((FamilyChild) null);
        assertFalse(lio1.equals(lio2));
        lio1.setFamilyWhereChild((FamilyChild) null);
        assertEquals(lio1, lio2);

        lio2.setType(LdsIndividualOrdinanceType.BAPTISM);
        assertFalse(lio1.equals(lio2));
        lio1.setType(LdsIndividualOrdinanceType.BAPTISM);
        assertEquals(lio1, lio2);

        lio2.setYNull("1");
        assertFalse(lio1.equals(lio2));
        lio1.setYNull("1");
        assertEquals(lio1, lio2);
        lio2.setYNull((String) null);
        assertFalse(lio1.equals(lio2));
        lio1.setYNull((String) null);
        assertEquals(lio1, lio2);
    }
}
