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
 * Test for {@link EventRecorded}
 * 
 * @author reckenrod
 * 
 */
public class EventRecordedTest {

    /**
     * Test for {@link EventRecorded#equals(Object)}
     */
    @Test
    @SuppressWarnings({ "PMD.EqualsNull", "PMD.ExcessiveMethodLength", "PMD.NcssMethodCount" })
    public void testEquals() {
        EventRecorded er1 = new EventRecorded();
        assertFalse(er1.equals(null));
        assertFalse(er1.equals(new Gedcom()));
        assertEquals(er1, er1);

        EventRecorded er2 = new EventRecorded();
        assertEquals(er1, er2);

        er1.getCustomFacts(true).add(new CustomFact("TEST"));
        assertFalse(er1.equals(er2));
        er2.getCustomFacts(true).add(new CustomFact("TEST"));
        assertEquals(er1, er2);

        er2.setDatePeriod(new StringWithCustomFacts("Test"));
        assertFalse(er1.equals(er2));
        er1.setDatePeriod(new StringWithCustomFacts("Test"));
        assertEquals(er1, er2);
        er2.setDatePeriod((StringWithCustomFacts) null);
        assertFalse(er1.equals(er2));
        er1.setDatePeriod((StringWithCustomFacts) null);
        assertEquals(er1, er2);

        er2.setEventType(new StringWithCustomFacts("Test"));
        assertFalse(er1.equals(er2));
        er1.setEventType(new StringWithCustomFacts("Test"));
        assertEquals(er1, er2);
        er2.setEventType((String) null);
        assertFalse(er1.equals(er2));
        er1.setEventType((String) null);
        assertEquals(er1, er2);

        er2.setJurisdiction(new StringWithCustomFacts("Test"));
        assertFalse(er1.equals(er2));
        er1.setJurisdiction(new StringWithCustomFacts("Test"));
        assertEquals(er1, er2);
        er2.setJurisdiction((StringWithCustomFacts) null);
        assertFalse(er1.equals(er2));
        er1.setJurisdiction((StringWithCustomFacts) null);
        assertEquals(er1, er2);
    }
}
