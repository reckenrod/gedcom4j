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
import static org.junit.Assert.assertTrue;

import org.gedcom4j.model.enumerations.IndividualAttributeType;
import org.junit.Test;

/**
 * Test for {@link IndividualAttribute}
 * 
 * @author frizbog
 * 
 */
@SuppressWarnings("PMD.ExcessiveMethodLength")
public class IndividualAttributeTest {

    /**
     * Test for {@link IndividualAttribute#equals(Object)}
     */
    @Test
    public void testEqualsObject() {
        IndividualAttribute i1 = new IndividualAttribute();
        IndividualAttribute i2 = new IndividualAttribute();

        assertNotSame(i1, i2);
        assertEquals(i1, i2);

        i2.setAge("One");
        assertFalse(i1.equals(i2));
        i1.setAge("One");
        assertEquals(i1, i2);
        i2.setAge((String) null);
        assertFalse(i1.equals(i2));
        i1.setAge((String) null);
        assertEquals(i1, i2);

        i2.setCause("Two");
        assertFalse(i1.equals(i2));
        i1.setCause("Two");
        assertEquals(i1, i2);
        i2.setCause((String) null);
        assertFalse(i1.equals(i2));
        i1.setCause((String) null);
        assertEquals(i1, i2);

        i2.getCitations(true).add(new CitationWithSource());
        assertFalse(i1.equals(i2));
        i1.getCitations(true).add(new CitationWithSource());
        assertEquals(i1, i2);
        i1.getCitations().clear();
        assertFalse(i1.equals(i2));
        i2.getCitations().clear();
        assertEquals(i1, i2);

        i1.getCustomFacts(true).add(new CustomFact("_FOO"));
        assertFalse(i1.equals(i2));
        i2.getCustomFacts(true).add(new CustomFact("_FOO"));
        assertEquals(i1, i2);

        i2.setDate("Test");
        assertFalse(i1.equals(i2));
        i1.setDate("Test");
        assertEquals(i1, i2);
        i2.setDate((String) null);
        assertFalse(i1.equals(i2));
        i1.setDate((String) null);
        assertEquals(i1, i2);

        i2.setDescription("Test");
        assertFalse(i1.equals(i2));
        i1.setDescription("Test");
        assertEquals(i1, i2);
        i2.setDescription((String) null);
        assertFalse(i1.equals(i2));
        i1.setDescription((String) null);
        assertEquals(i1, i2);

        i2.getEmails(true).add(new StringWithCustomFacts("Test"));
        assertFalse(i1.equals(i2));
        i1.getEmails(true).add(new StringWithCustomFacts("Test"));
        assertEquals(i1, i2);
        i2.getEmails().clear();
        assertFalse(i1.equals(i2));
        i1.getEmails().clear();
        assertEquals(i1, i2);

        i2.getFaxNumbers(true).add(new StringWithCustomFacts("Test"));
        assertFalse(i1.equals(i2));
        i1.getFaxNumbers(true).add(new StringWithCustomFacts("Test"));
        assertEquals(i1, i2);
        i2.getFaxNumbers().clear();
        assertFalse(i1.equals(i2));
        i1.getFaxNumbers().clear();
        assertEquals(i1, i2);

        i2.getMultimedia(true).add(new MultimediaReference());
        assertFalse(i1.equals(i2));
        i1.getMultimedia(true).add(new MultimediaReference());
        assertEquals(i1, i2);
        i1.getMultimedia().clear();
        assertFalse(i1.equals(i2));
        i2.getMultimedia().clear();
        assertEquals(i1, i2);

        i1.getNoteStructures(true).add(new NoteStructure());
        assertFalse(i1.equals(i2));
        i2.getNoteStructures(true).add(new NoteStructure());
        assertEquals(i1, i2);

        i2.getPhoneNumbers(true).add(new StringWithCustomFacts("Test"));
        assertFalse(i1.equals(i2));
        i1.getPhoneNumbers(true).add(new StringWithCustomFacts("Test"));
        assertEquals(i1, i2);
        i2.getPhoneNumbers().clear();
        assertFalse(i1.equals(i2));
        i1.getPhoneNumbers().clear();
        assertEquals(i1, i2);

        i2.setPlace(new Place());
        assertFalse(i1.equals(i2));
        i1.setPlace(new Place());
        assertEquals(i1, i2);
        i2.setPlace((Place) null);
        assertFalse(i1.equals(i2));
        i1.setPlace((Place) null);
        assertEquals(i1, i2);

        i2.setReligiousAffiliation("Test");
        assertFalse(i1.equals(i2));
        i1.setReligiousAffiliation("Test");
        assertEquals(i1, i2);
        i2.setReligiousAffiliation((String) null);
        assertFalse(i1.equals(i2));
        i1.setReligiousAffiliation((String) null);
        assertEquals(i1, i2);

        i2.setRespAgency("Test");
        assertFalse(i1.equals(i2));
        i1.setRespAgency("Test");
        assertEquals(i1, i2);
        i2.setRespAgency((String) null);
        assertFalse(i1.equals(i2));
        i1.setRespAgency((String) null);
        assertEquals(i1, i2);

        i2.setRestrictionNotice("Test");
        assertFalse(i1.equals(i2));
        i1.setRestrictionNotice("Test");
        assertEquals(i1, i2);
        i2.setRestrictionNotice((String) null);
        assertFalse(i1.equals(i2));
        i1.setRestrictionNotice((String) null);
        assertEquals(i1, i2);

        i2.setSubType("Test");
        assertFalse(i1.equals(i2));
        i1.setSubType("Test");
        assertEquals(i1, i2);
        i2.setSubType((String) null);
        assertFalse(i1.equals(i2));
        i1.setSubType((String) null);
        assertEquals(i1, i2);

        i2.setType(IndividualAttributeType.FACT);
        assertFalse(i1.equals(i2));
        i1.setType(IndividualAttributeType.FACT);
        assertEquals(i1, i2);
        i2.setType(null);
        assertFalse(i1.equals(i2));
        i1.setType(null);
        assertEquals(i1, i2);

        i2.getWwwUrls(true).add(new StringWithCustomFacts("Test"));
        assertFalse(i1.equals(i2));
        i1.getWwwUrls(true).add(new StringWithCustomFacts("Test"));
        assertEquals(i1, i2);
        i1.getWwwUrls().clear();
        assertFalse(i1.equals(i2));
        i2.getWwwUrls().clear();
        assertEquals(i1, i2);

        i2.setYNull("1");
        assertFalse(i1.equals(i2));
        i1.setYNull("1");
        assertEquals(i1, i2);
        i2.setYNull((String) null);
        assertFalse(i1.equals(i2));
        i1.setYNull((String) null);
        assertEquals(i1, i2);
    }

    /**
     * Test for {@link IndividualAttribute#hashCode()}
     * 
     */
    @Test
    public void testHashCode() {
        IndividualAttribute i1 = new IndividualAttribute();
        IndividualAttribute i2 = new IndividualAttribute();

        assertNotSame(i1, i2);
        assertEquals(i1.hashCode(), i2.hashCode());

        i1.setAddress(new Address());
        assertTrue(i1.hashCode() != i2.hashCode());
        i2.setAddress(new Address());
        assertEquals(i1.hashCode(), i2.hashCode());

        i1.setAge("One");
        assertTrue(i1.hashCode() != i2.hashCode());
        i2.setAge("One");
        assertEquals(i1.hashCode(), i2.hashCode());

        i1.setCause("Two");
        assertTrue(i1.hashCode() != i2.hashCode());
        i2.setCause("Two");
        assertEquals(i1.hashCode(), i2.hashCode());

        i1.getCitations(true).add(new CitationWithoutSource());
        assertTrue(i1.hashCode() != i2.hashCode());
        i2.getCitations(true).add(new CitationWithoutSource());
        assertEquals(i1.hashCode(), i2.hashCode());

        i1.getCustomFacts(true).add(new CustomFact("_FOO"));
        assertTrue(i1.hashCode() != i2.hashCode());
        i2.getCustomFacts(true).add(new CustomFact("_FOO"));
        assertEquals(i1.hashCode(), i2.hashCode());

        i1.setDate("Three");
        assertTrue(i1.hashCode() != i2.hashCode());
        i2.setDate("Three");
        assertEquals(i1.hashCode(), i2.hashCode());

        i1.setDescription("Four");
        assertTrue(i1.hashCode() != i2.hashCode());
        i2.setDescription("Four");
        assertEquals(i1.hashCode(), i2.hashCode());

        i1.getEmails(true).add(new StringWithCustomFacts("Five"));
        assertTrue(i1.hashCode() != i2.hashCode());
        i2.getEmails(true).add(new StringWithCustomFacts("Five"));
        assertEquals(i1.hashCode(), i2.hashCode());

        i1.getFaxNumbers(true).add(new StringWithCustomFacts("Six"));
        assertTrue(i1.hashCode() != i2.hashCode());
        i2.getFaxNumbers(true).add(new StringWithCustomFacts("Six"));
        assertEquals(i1.hashCode(), i2.hashCode());

        i1.getMultimedia(true).add(new MultimediaReference(new Multimedia()));
        assertTrue(i1.hashCode() != i2.hashCode());
        i2.getMultimedia(true).add(new MultimediaReference(new Multimedia()));
        assertEquals(i1.hashCode(), i2.hashCode());

        i1.getNoteStructures(true).add(new NoteStructure());
        assertTrue(i1.hashCode() != i2.hashCode());
        i2.getNoteStructures(true).add(new NoteStructure());
        assertEquals(i1.hashCode(), i2.hashCode());

        i1.getPhoneNumbers(true).add(new StringWithCustomFacts("Seven"));
        assertTrue(i1.hashCode() != i2.hashCode());
        i2.getPhoneNumbers(true).add(new StringWithCustomFacts("Seven"));
        assertEquals(i1.hashCode(), i2.hashCode());

        i1.setPlace(new Place());
        assertTrue(i1.hashCode() != i2.hashCode());
        i2.setPlace(new Place());
        assertEquals(i1.hashCode(), i2.hashCode());

        i1.setReligiousAffiliation("Eight");
        assertTrue(i1.hashCode() != i2.hashCode());
        i2.setReligiousAffiliation("Eight");
        assertEquals(i1.hashCode(), i2.hashCode());

        i1.setRespAgency("Nine");
        assertTrue(i1.hashCode() != i2.hashCode());
        i2.setRespAgency("Nine");
        assertEquals(i1.hashCode(), i2.hashCode());

        i1.setRestrictionNotice("Ten");
        assertTrue(i1.hashCode() != i2.hashCode());
        i2.setRestrictionNotice("Ten");
        assertEquals(i1.hashCode(), i2.hashCode());

        i1.setSubType("Eleven");
        assertTrue(i1.hashCode() != i2.hashCode());
        i2.setSubType("Eleven");
        assertEquals(i1.hashCode(), i2.hashCode());

        i1.setType(IndividualAttributeType.FACT);
        assertTrue(i1.hashCode() != i2.hashCode());
        i2.setType(IndividualAttributeType.FACT);
        assertEquals(i1.hashCode(), i2.hashCode());

        i1.getWwwUrls(true).add(new StringWithCustomFacts("Twelve"));
        assertTrue(i1.hashCode() != i2.hashCode());
        i2.getWwwUrls(true).add(new StringWithCustomFacts("Twelve"));
        assertEquals(i1.hashCode(), i2.hashCode());

        i1.setYNull("Thirteen");
        assertTrue(i1.hashCode() != i2.hashCode());
        i2.setYNull("Thirteen");
        assertEquals(i1.hashCode(), i2.hashCode());

    }

    /**
     * Test for {@link IndividualAttribute#toString()}
     */
    @Test
    public void testToString() {
        IndividualAttribute i = new IndividualAttribute();
        assertEquals("IndividualAttribute []", i.toString());

        i.setAddress(new Address());
        i.setAge("One");
        i.setCause("Two");
        i.getCitations(true).add(new CitationWithoutSource());
        i.getCustomFacts(true).add(new CustomFact("_FOO"));
        i.setDate("Three");
        i.setDescription("Four");
        i.getEmails(true).add(new StringWithCustomFacts("Five"));
        i.getFaxNumbers(true).add(new StringWithCustomFacts("Six"));
        i.getMultimedia(true).add(new MultimediaReference(new Multimedia()));
        i.getNoteStructures(true).add(new NoteStructure());
        i.getPhoneNumbers(true).add(new StringWithCustomFacts("Seven"));
        i.setPlace(new Place());
        i.setReligiousAffiliation("Eight");
        i.setRespAgency("Nine");
        i.setRestrictionNotice("Ten");
        i.setSubType("Eleven");
        i.setType(IndividualAttributeType.FACT);
        i.getWwwUrls(true).add(new StringWithCustomFacts("Twelve"));
        i.setYNull("Thirteen");

        assertEquals("IndividualAttribute [type=Fact, age=One, cause=Two, citations=[CitationWithoutSource []], date=Three, "
                + "description=Four, multimedia=[MultimediaReference [multimedia=Multimedia [], ]], "
                + "noteStructures=[NoteStructure []], place=Place [], religiousAffiliation=Eight, respAgency=Nine, "
                + "restrictionNotice=Ten, subType=Eleven, yNull=Thirteen, address=Address [], emails=[Five], faxNumbers=[Six], "
                + "phoneNumbers=[Seven], wwwUrls=[Twelve], customFacts=[CustomFact [tag=_FOO, ]]]", i.toString());
    }

}
