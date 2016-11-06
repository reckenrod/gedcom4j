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

import java.util.ArrayList;
import java.util.List;

import org.gedcom4j.Options;

/**
 * Name for an individual. Corresponds to PERSONAL_NAME_STRUCTURE in the GEDCOM standard.
 * 
 * @author frizbog1
 * 
 */
@SuppressWarnings("PMD.GodClass")
public class PersonalName extends AbstractNotesElement implements HasCitations {
    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = -3038084172374523321L;

    /**
     * The name in basic, unbroken-down format
     */
    private String basic;

    /**
     * The citations for this object
     */
    private List<AbstractCitation> citations = getCitations(Options.isCollectionInitializationEnabled());

    /**
     * The given (aka "Christian" or "first") names
     */
    private StringWithCustomFacts givenName;

    /**
     * Nickname
     */
    private StringWithCustomFacts nickname;

    /**
     * Phonetic spelling. New for GEDCOM 5.5.1
     */
    private List<PersonalNameVariation> phonetic = getPhonetic(Options.isCollectionInitializationEnabled());

    /**
     * The prefix for the name
     */
    private StringWithCustomFacts prefix;

    /**
     * Romanized variant. New for GEDCOM 5.5.1
     */
    private List<PersonalNameVariation> romanized = getRomanized(Options.isCollectionInitializationEnabled());

    /**
     * The suffix
     */
    private StringWithCustomFacts suffix;

    /**
     * The surname (aka "family" or "last" name)
     */
    private StringWithCustomFacts surname;

    /**
     * Surname prefix
     */
    private StringWithCustomFacts surnamePrefix;

    /**
     * Name type - for example the name issued or assumed as an immigrant
     */
    private StringWithCustomFacts type;

    /** Default constructor */
    public PersonalName() {
        // Default constructor does nothing
    }

    /**
     * Copy constructor
     * 
     * @param other
     *            object being copied
     */
    public PersonalName(PersonalName other) {
        super(other);
        basic = other.basic;
        if (other.citations != null) {
            citations = new ArrayList<>();
            for (AbstractCitation ac : other.citations) {
                if (ac instanceof CitationWithoutSource) {
                    citations.add(new CitationWithoutSource((CitationWithoutSource) ac));
                } else if (ac instanceof CitationWithSource) {
                    citations.add(new CitationWithSource((CitationWithSource) ac));
                }
            }
        }
        if (other.givenName != null) {
            givenName = new StringWithCustomFacts(other.givenName);
        }
        if (other.nickname != null) {
            nickname = new StringWithCustomFacts(other.nickname);
        }
        if (other.prefix != null) {
            prefix = new StringWithCustomFacts(other.prefix);
        }
        if (other.romanized != null) {
            romanized = new ArrayList<>();
            for (AbstractNameVariation ph : other.romanized) {
                romanized.add(new PersonalNameVariation((PersonalNameVariation) ph));
            }
        }
        if (other.suffix != null) {
            suffix = new StringWithCustomFacts(other.suffix);
        }
        if (other.surname != null) {
            surname = new StringWithCustomFacts(other.surname);
        }
        if (other.surnamePrefix != null) {
            surnamePrefix = new StringWithCustomFacts(other.surnamePrefix);
        }
        if (other.type != null) {
            type = new StringWithCustomFacts(other.type);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!super.equals(obj)) {
            return false;
        }
        if (!(obj instanceof PersonalName)) {
            return false;
        }
        PersonalName other = (PersonalName) obj;
        if (basic == null) {
            if (other.basic != null) {
                return false;
            }
        } else if (!basic.equals(other.basic)) {
            return false;
        }
        if (citations == null) {
            if (other.citations != null) {
                return false;
            }
        } else if (!citations.equals(other.citations)) {
            return false;
        }
        if (givenName == null) {
            if (other.givenName != null) {
                return false;
            }
        } else if (!givenName.equals(other.givenName)) {
            return false;
        }
        if (nickname == null) {
            if (other.nickname != null) {
                return false;
            }
        } else if (!nickname.equals(other.nickname)) {
            return false;
        }
        if (prefix == null) {
            if (other.prefix != null) {
                return false;
            }
        } else if (!prefix.equals(other.prefix)) {
            return false;
        }
        if (suffix == null) {
            if (other.suffix != null) {
                return false;
            }
        } else if (!suffix.equals(other.suffix)) {
            return false;
        }
        if (surname == null) {
            if (other.surname != null) {
                return false;
            }
        } else if (!surname.equals(other.surname)) {
            return false;
        }
        if (surnamePrefix == null) {
            if (other.surnamePrefix != null) {
                return false;
            }
        } else if (!surnamePrefix.equals(other.surnamePrefix)) {
            return false;
        }
        if (romanized == null) {
            if (other.romanized != null) {
                return false;
            }
        } else if (!romanized.equals(other.romanized)) {
            return false;
        }
        if (phonetic == null) {
            if (other.phonetic != null) {
                return false;
            }
        } else if (!phonetic.equals(other.phonetic)) {
            return false;
        }
        if (type == null) {
            if (other.type != null) {
                return false;
            }
        } else if (!type.equals(other.type)) {
            return false;
        }
        return true;
    }

    /**
     * Gets the basic name
     *
     * @return the basic name
     */
    public String getBasic() {
        return basic;
    }

    /**
     * Gets the citations.
     *
     * @return the citations
     */
    @Override
    public List<AbstractCitation> getCitations() {
        return citations;
    }

    /**
     * Get the citations
     * 
     * @param initializeIfNeeded
     *            initialize the collection if needed?
     * 
     * @return the citations
     */
    @Override
    public List<AbstractCitation> getCitations(boolean initializeIfNeeded) {
        if (initializeIfNeeded && citations == null) {
            citations = new ArrayList<>(0);
        }
        return citations;
    }

    /**
     * Gets the given name.
     *
     * @return the given name
     */
    public StringWithCustomFacts getGivenName() {
        return givenName;
    }

    /**
     * Gets the nickname.
     *
     * @return the nickname
     */
    public StringWithCustomFacts getNickname() {
        return nickname;
    }

    /**
     * Gets the phonetic variation(s)
     *
     * @return the phonetic varation(s)
     */
    public List<PersonalNameVariation> getPhonetic() {
        return phonetic;
    }

    /**
     * Get the phonetic variation(s)
     * 
     * @param initializeIfNeeded
     *            initialize the collection, if needed?
     * @return the phonetic variation(s)
     */
    public List<PersonalNameVariation> getPhonetic(boolean initializeIfNeeded) {
        if (initializeIfNeeded && phonetic == null) {
            phonetic = new ArrayList<>(0);
        }
        return phonetic;
    }

    /**
     * Gets the prefix.
     *
     * @return the prefix
     */
    public StringWithCustomFacts getPrefix() {
        return prefix;
    }

    /**
     * Gets the romanized variation(s)
     *
     * @return the romanized variation(s)
     */
    public List<PersonalNameVariation> getRomanized() {
        return romanized;
    }

    /**
     * Get the romanized variation(s)
     * 
     * @param initializeIfNeeded
     *            initialize the collection, if needed?
     * @return the romanized variation(s)
     */
    public List<PersonalNameVariation> getRomanized(boolean initializeIfNeeded) {
        if (initializeIfNeeded && romanized == null) {
            romanized = new ArrayList<>(0);
        }
        return romanized;
    }

    /**
     * Gets the suffix.
     *
     * @return the suffix
     */
    public StringWithCustomFacts getSuffix() {
        return suffix;
    }

    /**
     * Gets the surname.
     *
     * @return the surname
     */
    public StringWithCustomFacts getSurname() {
        return surname;
    }

    /**
     * Gets the surname prefix.
     *
     * @return the surname prefix
     */
    public StringWithCustomFacts getSurnamePrefix() {
        return surnamePrefix;
    }

    /**
     * Get the type
     * 
     * @return the type
     */
    public StringWithCustomFacts getType() {
        return type;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + (basic == null ? 0 : basic.hashCode());
        result = prime * result + (citations == null ? 0 : citations.hashCode());
        result = prime * result + (givenName == null ? 0 : givenName.hashCode());
        result = prime * result + (nickname == null ? 0 : nickname.hashCode());
        result = prime * result + (prefix == null ? 0 : prefix.hashCode());
        result = prime * result + (suffix == null ? 0 : suffix.hashCode());
        result = prime * result + (surname == null ? 0 : surname.hashCode());
        result = prime * result + (surnamePrefix == null ? 0 : surnamePrefix.hashCode());
        result = prime * result + (romanized == null ? 0 : romanized.hashCode());
        result = prime * result + (phonetic == null ? 0 : phonetic.hashCode());
        result = prime * result + (type == null ? 0 : type.hashCode());
        return result;
    }

    /**
     * Sets the basic.
     *
     * @param basic
     *            the new basic
     */
    public void setBasic(String basic) {
        this.basic = basic;
    }

    /**
     * Sets the given name.
     *
     * @param givenName
     *            the new given name
     */
    public void setGivenName(String givenName) {
        if (this.givenName == null) {
            this.givenName = new StringWithCustomFacts(givenName);
        } else {
            this.givenName.setValue(givenName);
        }
    }

    /**
     * Sets the given name.
     *
     * @param givenName
     *            the new given name
     */
    public void setGivenName(StringWithCustomFacts givenName) {
        this.givenName = givenName;
    }

    /**
     * Sets the nickname.
     *
     * @param nickname
     *            the new nickname
     */
    public void setNickname(String nickname) {
        if (this.nickname == null) {
            this.nickname = new StringWithCustomFacts(nickname);
        } else {
            this.nickname.setValue(nickname);
        }
    }

    /**
     * Sets the nickname.
     *
     * @param nickname
     *            the new nickname
     */
    public void setNickname(StringWithCustomFacts nickname) {
        this.nickname = nickname;
    }

    /**
     * Sets the prefix.
     *
     * @param prefix
     *            the new prefix
     */
    public void setPrefix(String prefix) {
        if (this.prefix == null) {
            this.prefix = new StringWithCustomFacts(prefix);
        } else {
            this.prefix.setValue(prefix);
        }
    }

    /**
     * Sets the prefix.
     *
     * @param prefix
     *            the new prefix
     */
    public void setPrefix(StringWithCustomFacts prefix) {
        this.prefix = prefix;
    }

    /**
     * Sets the suffix.
     *
     * @param suffix
     *            the new suffix
     */
    public void setSuffix(String suffix) {
        if (this.suffix == null) {
            this.suffix = new StringWithCustomFacts(suffix);
        } else {
            this.suffix.setValue(suffix);
        }
    }

    /**
     * Sets the suffix.
     *
     * @param suffix
     *            the new suffix
     */
    public void setSuffix(StringWithCustomFacts suffix) {
        this.suffix = suffix;
    }

    /**
     * Sets the surname.
     *
     * @param surname
     *            the new surname
     */
    public void setSurname(String surname) {
        if (this.surname == null) {
            this.surname = new StringWithCustomFacts(surname);
        } else {
            this.surname.setValue(surname);
        }
    }

    /**
     * Sets the surname.
     *
     * @param surname
     *            the new surname
     */
    public void setSurname(StringWithCustomFacts surname) {
        this.surname = surname;
    }

    /**
     * Sets the surname prefix.
     *
     * @param surnamePrefix
     *            the new surname prefix
     */
    public void setSurnamePrefix(String surnamePrefix) {
        if (this.surnamePrefix == null) {
            this.surnamePrefix = new StringWithCustomFacts(surnamePrefix);
        } else {
            this.surnamePrefix.setValue(surnamePrefix);
        }
    }

    /**
     * Sets the surname prefix.
     *
     * @param surnamePrefix
     *            the new surname prefix
     */
    public void setSurnamePrefix(StringWithCustomFacts surnamePrefix) {
        this.surnamePrefix = surnamePrefix;
    }

    /**
     * Set the type
     * 
     * @param type
     *            the type to set
     */
    public void setType(String type) {
        if (this.type == null) {
            this.type = new StringWithCustomFacts(type);
        } else {
            this.type.setValue(type);
        }
    }

    /**
     * Set the type
     * 
     * @param type
     *            the type to set
     */
    public void setType(StringWithCustomFacts type) {
        this.type = type;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        if (surname != null || givenName != null) {
            return surname + ", " + givenName + (nickname == null ? "" : " \"" + nickname + "\"");
        }
        return basic;
    }

}
