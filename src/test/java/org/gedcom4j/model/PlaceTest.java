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
 * @author reckenrod
 * 
 */
@SuppressWarnings({ "PMD.TooManyMethods", "PMD.SystemPrintln" })
public class PlaceTest {

    /**
     * Test method for {@link Place} .
     */
    @Test
    public void testEquals() {
        Place p1 = new Place();
        assertFalse(p1.equals(null));
        assertFalse(p1.equals(new Corporation()));
        assertEquals(p1, p1);

        Place p2 = new Place();
        assertEquals(p1, p2);

        p2.getCitations(true).add(new CitationWithSource());
        assertFalse(p1.equals(p2));
        p1.getCitations(true).add(new CitationWithSource());
        assertEquals(p1, p2);
        p1.getCitations().clear();
        assertFalse(p1.equals(p2));
        p2.getCitations().clear();
        assertEquals(p1, p2);

        p2.setLatitude("38N");
        assertFalse(p1.equals(p2));
        p1.setLatitude("38N");
        assertEquals(p1, p2);
        p2.setLatitude((String) null);
        assertFalse(p1.equals(p2));
        p1.setLatitude((String) null);
        assertEquals(p1, p2);

        p2.setLongitude("17W");
        assertFalse(p1.equals(p2));
        p1.setLongitude("17W");
        assertEquals(p1, p2);
        p2.setLongitude((String) null);
        assertFalse(p1.equals(p2));
        p1.setLongitude((String) null);
        assertEquals(p1, p2);

        p2.setPlaceName("A");
        assertFalse(p1.equals(p2));
        p1.setPlaceName("A");
        assertEquals(p1, p2);
        p2.setPlaceName((String) null);
        assertFalse(p1.equals(p2));
        p1.setPlaceName((String) null);
        assertEquals(p1, p2);

        p2.setPlaceFormat("17W");
        assertFalse(p1.equals(p2));
        p1.setPlaceFormat("17W");
        assertEquals(p1, p2);
        p2.setPlaceFormat((String) null);
        assertFalse(p1.equals(p2));
        p1.setPlaceFormat((String) null);
        assertEquals(p1, p2);

        p2.getPhonetic(true).add(new PersonalNameVariation());
        assertFalse(p1.equals(p2));
        p1.getPhonetic(true).add(new PersonalNameVariation());
        assertEquals(p1, p2);
        p2.getPhonetic().clear();
        assertFalse(p1.equals(p2));
        p1.getPhonetic().clear();
        assertEquals(p1, p2);

        p2.getRomanized(true).add(new PersonalNameVariation());
        assertFalse(p1.equals(p2));
        p1.getRomanized(true).add(new PersonalNameVariation());
        assertEquals(p1, p2);
        p1.getRomanized().clear();
        assertFalse(p1.equals(p2));
        p2.getRomanized().clear();
        assertEquals(p1, p2);
    }

}
