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
 * Test copy constructor for {@link PersonalName}
 * 
 * @author frizbog
 */
public class PersonalNameCopyTest extends AbstractCopyTest {

    /**
     * Test copying a null {@link PersonalName}, which should never work
     */
    @SuppressWarnings("unused")
    @Test(expected = NullPointerException.class)
    public void testCopyNull() {
        new PersonalName(null);
    }

    /**
     * Test the simplest possible scenario - copy a new default {@link PersonalName}
     */
    @Test
    public void testSimplestPossible() {
        PersonalName orig = new PersonalName();
        PersonalName copy = new PersonalName(orig);
        assertEquals(orig, copy);
        assertNotSame(orig, copy);
    }

    /**
     * Test with values
     */
    @Test
    public void testWithValues() {
        PersonalName orig = new PersonalName();
        orig.setBasic("Qwerty I /Op/");
        orig.setGivenName("Qwerty I");
        orig.setNickname("Shifty");
        orig.setPrefix("Prof.");
        orig.setSuffix("III");
        orig.setSurname("Op");
        orig.setSurnamePrefix("[]");
        orig.setType("immigrant");
        orig.getSurnamePrefix().getCustomFacts(true).add(getTestCustomFact());
        orig.getPhonetic(true).add(new PersonalNameVariation());
        orig.getRomanized(true).add(new PersonalNameVariation());
        orig.getCitations(true).add(getTestCitation());

        PersonalName copy = new PersonalName(orig);
        assertEquals(orig, copy);
        assertNotSame(orig, copy);
        assertEquals(orig.toString(), copy.toString());

        orig.getSurnamePrefix().getCustomFacts().clear();
        assertFalse("Copy shouldn't match if original changes", orig.equals(copy));
    }

}
