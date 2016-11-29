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
 * <p>
 * A submitter. Corresponds to the SUBMITTER_RECORD structure in the GEDCOM standard.
 * </p>
 * <p>
 * Note that a valid GEDCOM requires at least one Submitter record to be valid.
 * </p>
 * 
 * @author frizbog1
 */
@SuppressWarnings("PMD.GodClass")
public class Submitter extends AbstractAddressableElement implements HasXref {
    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = 964849855689332389L;

    /**
     * The change date for this submitter
     */
    private ChangeDate changeDate;

    /**
     * The language preferences
     */
    private List<StringWithCustomFacts> languagePref = getLanguagePref(Options.isCollectionInitializationEnabled());

    /**
     * The multimedia for this submitter
     */
    private List<MultimediaReference> multimedia = getMultimedia(Options.isCollectionInitializationEnabled());

    /**
     * The name of this submitter
     */
    private StringWithCustomFacts name;

    /**
     * The record ID number
     */
    private StringWithCustomFacts recIdNumber;

    /**
     * The registration file number for this submitter
     */
    private StringWithCustomFacts regFileNumber;

    /**
     * The user references for this submitter
     */
    private List<UserReference> userReferences = getUserReferences(Options.isCollectionInitializationEnabled());

    /**
     * The xref for this submitter
     */
    private String xref;

    /** Default constructor */
    public Submitter() {
        // Default constructor does nothing
    }

    /**
     * Constructor that takes an xref
     * 
     * @param xref
     *            the xref
     * @param submitterName
     *            the name of the submitter
     */
    public Submitter(String xref, String submitterName) {
        this.xref = xref;
        name = new StringWithCustomFacts(submitterName);
    }

    /**
     * Copy constructor
     * 
     * @param other
     *            object being copied
     */
    public Submitter(Submitter other) {
        super(other);
        if (other.changeDate != null) {
            changeDate = new ChangeDate(other.changeDate);
        }
        if (other.languagePref != null) {
            languagePref = new ArrayList<>();
            for (StringWithCustomFacts swct : other.languagePref) {
                languagePref.add(new StringWithCustomFacts(swct));
            }
        }
        if (other.multimedia != null) {
            multimedia = new ArrayList<>();
            for (MultimediaReference m : other.multimedia) {
                multimedia.add(new MultimediaReference(m));
            }
        }
        if (other.name != null) {
            name = new StringWithCustomFacts(other.name);
        }
        if (other.recIdNumber != null) {
            recIdNumber = new StringWithCustomFacts(other.recIdNumber);
        }
        if (other.regFileNumber != null) {
            regFileNumber = new StringWithCustomFacts(other.regFileNumber);
        }
        if (other.userReferences != null) {
            userReferences = new ArrayList<>();
            for (UserReference ur : other.userReferences) {
                userReferences.add(new UserReference(ur));
            }
        }
        xref = other.xref;
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
        if (!(obj instanceof Submitter)) {
            return false;
        }
        Submitter other = (Submitter) obj;
        if (changeDate == null) {
            if (other.changeDate != null) {
                return false;
            }
        } else if (changeDate.equals(other.changeDate)) {
            return false;
        }
        if (languagePref == null) {
            if (other.languagePref != null) {
                return false;
            }
        } else if (!languagePref.equals(other.languagePref)) {
            return false;
        }
        if (multimedia == null) {
            if (other.multimedia != null) {
                return false;
            }
        } else if (!multimedia.equals(other.multimedia)) {
            return false;
        }
        if (name == null) {
            if (other.name != null) {
                return false;
            }
        } else if (!name.equals(other.name)) {
            return false;
        }
        if (recIdNumber == null) {
            if (other.recIdNumber != null) {
                return false;
            }
        } else if (!recIdNumber.equals(other.recIdNumber)) {
            return false;
        }
        if (regFileNumber == null) {
            if (other.regFileNumber != null) {
                return false;
            }
        } else if (!regFileNumber.equals(other.regFileNumber)) {
            return false;
        }
        if (userReferences == null) {
            if (other.userReferences != null) {
                return false;
            }
        } else if (!userReferences.equals(other.userReferences)) {
            return false;
        }
        if (xref == null) {
            if (other.xref != null) {
                return false;
            }
        } else if (!xref.equals(other.xref)) {
            return false;
        }
        return true;
    }

    /**
     * Gets the change date.
     *
     * @return the change date
     */
    public ChangeDate getChangeDate() {
        return changeDate;
    }

    /**
     * Gets the language pref.
     *
     * @return the language pref
     */
    public List<StringWithCustomFacts> getLanguagePref() {
        return languagePref;
    }

    /**
     * Get the languagePref
     * 
     * @param initializeIfNeeded
     *            initialize the collection, if needed?
     * @return the languagePref
     */
    public List<StringWithCustomFacts> getLanguagePref(boolean initializeIfNeeded) {
        if (initializeIfNeeded && languagePref == null) {
            languagePref = new ArrayList<>(0);
        }
        return languagePref;
    }

    /**
     * Gets the multimedia.
     *
     * @return the multimedia
     */
    public List<MultimediaReference> getMultimedia() {
        return multimedia;
    }

    /**
     * Get the multimedia
     * 
     * @param initializeIfNeeded
     *            initialize the collection, if needed?
     * @return the multimedia
     */
    public List<MultimediaReference> getMultimedia(boolean initializeIfNeeded) {
        if (initializeIfNeeded && multimedia == null) {
            multimedia = new ArrayList<>(0);
        }
        return multimedia;
    }

    /**
     * Gets the name.
     *
     * @return the name
     */
    public StringWithCustomFacts getName() {
        return name;
    }

    /**
     * Gets the rec id number.
     *
     * @return the rec id number
     */
    public StringWithCustomFacts getRecIdNumber() {
        return recIdNumber;
    }

    /**
     * Gets the reg file number.
     *
     * @return the reg file number
     */
    public StringWithCustomFacts getRegFileNumber() {
        return regFileNumber;
    }

    /**
     * Gets the user references.
     *
     * @return the user references
     */
    public List<UserReference> getUserReferences() {
        return userReferences;
    }

    /**
     * Get the user references
     * 
     * @param initializeIfNeeded
     *            initialize the collection, if needed?
     * @return the user references
     */
    public List<UserReference> getUserReferences(boolean initializeIfNeeded) {
        if (initializeIfNeeded && userReferences == null) {
            userReferences = new ArrayList<>(0);
        }
        return userReferences;
    }

    /**
     * Gets the xref.
     *
     * @return the xref
     */
    @Override
    public String getXref() {
        return xref;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + (changeDate == null ? 0 : changeDate.hashCode());
        result = prime * result + (languagePref == null ? 0 : languagePref.hashCode());
        result = prime * result + (multimedia == null ? 0 : multimedia.hashCode());
        result = prime * result + (name == null ? 0 : name.hashCode());
        result = prime * result + (recIdNumber == null ? 0 : recIdNumber.hashCode());
        result = prime * result + (regFileNumber == null ? 0 : regFileNumber.hashCode());
        result = prime * result + (userReferences == null ? 0 : userReferences.hashCode());
        result = prime * result + (xref == null ? 0 : xref.hashCode());
        return result;
    }

    /**
     * Sets the change date.
     *
     * @param changeDate
     *            the new change date
     */
    public void setChangeDate(ChangeDate changeDate) {
        this.changeDate = changeDate;
    }

    /**
     * Sets the name.
     *
     * @param name
     *            the new name
     */
    public void setName(String name) {
        this.name = name == null ? null : new StringWithCustomFacts(name);
    }

    /**
     * Sets the name.
     *
     * @param name
     *            the new name
     */
    public void setName(StringWithCustomFacts name) {
        this.name = name;
    }

    /**
     * Sets the rec id number.
     *
     * @param recIdNumber
     *            the new rec id number
     */
    public void setRecIdNumber(String recIdNumber) {
        this.recIdNumber = recIdNumber == null ? null : new StringWithCustomFacts(recIdNumber);
    }

    /**
     * Sets the rec id number.
     *
     * @param recIdNumber
     *            the new rec id number
     */
    public void setRecIdNumber(StringWithCustomFacts recIdNumber) {
        this.recIdNumber = recIdNumber;
    }

    /**
     * Sets the reg file number.
     *
     * @param regFileNumber
     *            the new reg file number
     */
    public void setRegFileNumber(String regFileNumber) {
        this.regFileNumber = regFileNumber == null ? null : new StringWithCustomFacts(regFileNumber);
    }

    /**
     * Sets the reg file number.
     *
     * @param regFileNumber
     *            the new reg file number
     */
    public void setRegFileNumber(StringWithCustomFacts regFileNumber) {
        this.regFileNumber = regFileNumber;
    }

    /**
     * Sets the xref.
     *
     * @param xref
     *            the new xref
     */
    public void setXref(String xref) {
        this.xref = xref;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(48);
        builder.append("Submitter [");
        if (changeDate != null) {
            builder.append("changeDate=");
            builder.append(changeDate);
            builder.append(", ");
        }
        if (languagePref != null) {
            builder.append("languagePref=");
            builder.append(languagePref);
            builder.append(", ");
        }
        if (multimedia != null) {
            builder.append("multimedia=");
            builder.append(multimedia);
            builder.append(", ");
        }
        if (name != null) {
            builder.append("name=");
            builder.append(name);
            builder.append(", ");
        }
        if (recIdNumber != null) {
            builder.append("recIdNumber=");
            builder.append(recIdNumber);
            builder.append(", ");
        }
        if (regFileNumber != null) {
            builder.append("regFileNumber=");
            builder.append(regFileNumber);
            builder.append(", ");
        }
        if (userReferences != null) {
            builder.append("userReferences=");
            builder.append(userReferences);
            builder.append(", ");
        }
        if (xref != null) {
            builder.append("xref=");
            builder.append(xref);
            builder.append(", ");
        }
        if (getCustomFacts() != null) {
            builder.append("customFacts=");
            builder.append(getCustomFacts());
            builder.append(", ");
        }
        appendAddressFields(builder, false);
        builder.append("]");
        return builder.toString();
    }
}
