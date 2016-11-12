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
 * Header information about the GEDCOM file
 * 
 * @author frizbog1
 * 
 */
@SuppressWarnings("PMD.GodClass")
public class Header extends AbstractNotesElement {
    /**
     * Notes on this header. Technically, the spec does not allow multiple notes or multiline notes in headers, but it happens so
     * often it's better to allow it than to stop people from being able to parse files.
     */

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = 2648392706111388922L;

    /**
     * The character set in use in the GEDCOM file
     */
    private CharacterSet characterSet = new CharacterSet();

    /**
     * Copyright information for the GEDCOM file.
     */
    private List<String> copyrightData = getCopyrightData(Options.isCollectionInitializationEnabled());

    /**
     * The date of the GEDCOM file
     */
    private StringWithCustomFacts date;

    /**
     * The destination system for the GEDCOM file.
     */
    private StringWithCustomFacts destinationSystem;

    /**
     * The filename for the GEDCOM file
     */
    private StringWithCustomFacts fileName;

    /**
     * The version information for the GEDCOM file
     */
    private GedcomVersion gedcomVersion = new GedcomVersion();

    /**
     * The language for the file
     */
    private StringWithCustomFacts language;

    /**
     * The place structure for the file
     */
    private StringWithCustomFacts placeHierarchy;

    /**
     * The source system for the GEDCOM file
     */
    private SourceSystem sourceSystem = new SourceSystem();

    /**
     * Information about the file submissionReference
     */
    private SubmissionReference submissionReference;

    /**
     * Information about the submitter of the file
     */
    private SubmitterReference submitterReference = new SubmitterReference(new Submitter("@SUBMITTER@", "UNSPECIFIED"));

    /**
     * The time of the file
     */
    private StringWithCustomFacts time;

    /** Default constructor */
    public Header() {
        // Default constructor does nothing
    }

    /**
     * Copy constructor
     * 
     * @param other
     *            object being copied
     */
    public Header(Header other) {
        super(other);
        if (other.characterSet != null) {
            characterSet = new CharacterSet(other.characterSet);
        } else {
            characterSet = null;
        }
        if (other.copyrightData != null) {
            copyrightData = new ArrayList<>(other.copyrightData);
        } else {
            copyrightData = null;
        }
        if (other.date != null) {
            date = new StringWithCustomFacts(other.date);
        } else {
            date = null;
        }
        if (other.destinationSystem != null) {
            destinationSystem = new StringWithCustomFacts(other.destinationSystem);
        } else {
            destinationSystem = null;
        }
        if (other.fileName != null) {
            fileName = new StringWithCustomFacts(other.fileName);
        } else {
            fileName = null;
        }
        if (other.gedcomVersion != null) {
            gedcomVersion = new GedcomVersion(other.gedcomVersion);
        } else {
            gedcomVersion = null;
        }
        if (other.language != null) {
            language = new StringWithCustomFacts(other.language);
        } else {
            language = null;
        }
        if (other.placeHierarchy != null) {
            placeHierarchy = new StringWithCustomFacts(other.placeHierarchy);
        } else {
            placeHierarchy = null;
        }
        if (other.sourceSystem != null) {
            sourceSystem = new SourceSystem(other.sourceSystem);
        } else {
            sourceSystem = null;
        }
        if (other.submissionReference != null) {
            submissionReference = new SubmissionReference(other.submissionReference);
        } else {
            submissionReference = null;
        }
        if (other.submitterReference != null) {
            submitterReference = new SubmitterReference(other.submitterReference);
        } else {
            submitterReference = null;
        }
        if (other.time != null) {
            time = new StringWithCustomFacts(other.time);
        } else {
            time = null;
        }
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("PMD.ExcessiveMethodLength")
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!super.equals(obj)) {
            return false;
        }
        Header other = (Header) obj;
        if (characterSet == null) {
            if (other.characterSet != null) {
                return false;
            }
        } else if (!characterSet.equals(other.characterSet)) {
            return false;
        }
        if (copyrightData == null) {
            if (other.copyrightData != null) {
                return false;
            }
        } else if (!copyrightData.equals(other.copyrightData)) {
            return false;
        }
        if (date == null) {
            if (other.date != null) {
                return false;
            }
        } else if (!date.equals(other.date)) {
            return false;
        }
        if (destinationSystem == null) {
            if (other.destinationSystem != null) {
                return false;
            }
        } else if (!destinationSystem.equals(other.destinationSystem)) {
            return false;
        }
        if (fileName == null) {
            if (other.fileName != null) {
                return false;
            }
        } else if (!fileName.equals(other.fileName)) {
            return false;
        }
        if (gedcomVersion == null) {
            if (other.gedcomVersion != null) {
                return false;
            }
        } else if (!gedcomVersion.equals(other.gedcomVersion)) {
            return false;
        }
        if (language == null) {
            if (other.language != null) {
                return false;
            }
        } else if (!language.equals(other.language)) {
            return false;
        }
        if (placeHierarchy == null) {
            if (other.placeHierarchy != null) {
                return false;
            }
        } else if (!placeHierarchy.equals(other.placeHierarchy)) {
            return false;
        }
        if (sourceSystem == null) {
            if (other.sourceSystem != null) {
                return false;
            }
        } else if (!sourceSystem.equals(other.sourceSystem)) {
            return false;
        }
        if (submissionReference == null) {
            if (other.submissionReference != null) {
                return false;
            }
        } else if (!submissionReference.equals(other.submissionReference)) {
            return false;
        }
        if (submitterReference == null) {
            if (other.submitterReference != null) {
                return false;
            }
        } else if (!submitterReference.equals(other.submitterReference)) {
            return false;
        }
        if (time == null) {
            if (other.time != null) {
                return false;
            }
        } else if (!time.equals(other.time)) {
            return false;
        }
        return true;
    }

    /**
     * Gets the character set.
     *
     * @return the character set
     */
    public CharacterSet getCharacterSet() {
        return characterSet;
    }

    /**
     * Gets the copyright data.
     *
     * @return the copyright data
     */
    public List<String> getCopyrightData() {
        return copyrightData;
    }

    /**
     * Get the copyright data
     * 
     * @param initializeIfNeeded
     *            initialize the collection if needed?
     * @return the copyright data
     */
    public List<String> getCopyrightData(boolean initializeIfNeeded) {
        if (initializeIfNeeded && copyrightData == null) {
            copyrightData = new ArrayList<>(0);
        }
        return copyrightData;
    }

    /**
     * Gets the date.
     *
     * @return the date
     */
    public StringWithCustomFacts getDate() {
        return date;
    }

    /**
     * Gets the destination system.
     *
     * @return the destination system
     */
    public StringWithCustomFacts getDestinationSystem() {
        return destinationSystem;
    }

    /**
     * Gets the file name.
     *
     * @return the file name
     */
    public StringWithCustomFacts getFileName() {
        return fileName;
    }

    /**
     * Gets the gedcom version.
     *
     * @return the gedcom version
     */
    public GedcomVersion getGedcomVersion() {
        return gedcomVersion;
    }

    /**
     * Gets the language.
     *
     * @return the language
     */
    public StringWithCustomFacts getLanguage() {
        return language;
    }

    /**
     * Gets the place hierarchy.
     *
     * @return the place hierarchy
     */
    public StringWithCustomFacts getPlaceHierarchy() {
        return placeHierarchy;
    }

    /**
     * Gets the source system.
     *
     * @return the source system
     */
    public SourceSystem getSourceSystem() {
        return sourceSystem;
    }

    /**
     * Gets the submission reference.
     *
     * @return the submission reference
     */
    public SubmissionReference getSubmissionReference() {
        return submissionReference;
    }

    /**
     * Gets the submitter reference
     *
     * @return the submitter reference
     */
    public SubmitterReference getSubmitterReference() {
        return submitterReference;
    }

    /**
     * Gets the time.
     *
     * @return the time
     */
    public StringWithCustomFacts getTime() {
        return time;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + (characterSet == null ? 0 : characterSet.hashCode());
        result = prime * result + (copyrightData == null ? 0 : copyrightData.hashCode());
        result = prime * result + (date == null ? 0 : date.hashCode());
        result = prime * result + (destinationSystem == null ? 0 : destinationSystem.hashCode());
        result = prime * result + (fileName == null ? 0 : fileName.hashCode());
        result = prime * result + (gedcomVersion == null ? 0 : gedcomVersion.hashCode());
        result = prime * result + (language == null ? 0 : language.hashCode());
        result = prime * result + (placeHierarchy == null ? 0 : placeHierarchy.hashCode());
        result = prime * result + (sourceSystem == null ? 0 : sourceSystem.hashCode());
        result = prime * result + (submissionReference == null ? 0 : submissionReference.hashCode());
        result = prime * result + (submitterReference == null ? 0 : submitterReference.hashCode());
        result = prime * result + (time == null ? 0 : time.hashCode());

        return result;
    }

    /**
     * Sets the character set.
     *
     * @param characterSet
     *            the new character set
     */
    public void setCharacterSet(CharacterSet characterSet) {
        this.characterSet = characterSet;
    }

    /**
     * Sets the date.
     *
     * @param date
     *            the new date
     */
    public void setDate(String date) {
        this.date = new StringWithCustomFacts(date);
    }

    /**
     * Sets the date.
     *
     * @param date
     *            the new date
     */
    public void setDate(StringWithCustomFacts date) {
        this.date = date;
    }

    /**
     * Sets the destination system.
     *
     * @param destinationSystem
     *            the new destination system
     */
    public void setDestinationSystem(String destinationSystem) {
        this.destinationSystem = destinationSystem == null ? null : new StringWithCustomFacts(destinationSystem);
    }

    /**
     * Sets the destination system.
     *
     * @param destinationSystem
     *            the new destination system
     */
    public void setDestinationSystem(StringWithCustomFacts destinationSystem) {
        this.destinationSystem = destinationSystem;
    }

    /**
     * Sets the file name.
     *
     * @param fileName
     *            the new file name
     */
    public void setFileName(String fileName) {
        this.fileName = fileName == null ? null : new StringWithCustomFacts(fileName);
    }

    /**
     * Sets the file name.
     *
     * @param fileName
     *            the new file name
     */
    public void setFileName(StringWithCustomFacts fileName) {
        this.fileName = fileName;
    }

    /**
     * Sets the gedcom version.
     *
     * @param gedcomVersion
     *            the new gedcom version
     */
    public void setGedcomVersion(GedcomVersion gedcomVersion) {
        this.gedcomVersion = gedcomVersion;
    }

    /**
     * Sets the language.
     *
     * @param language
     *            the new language
     */
    public void setLanguage(String language) {
        this.language = language == null ? null : new StringWithCustomFacts(language);
    }

    /**
     * Sets the language.
     *
     * @param language
     *            the new language
     */
    public void setLanguage(StringWithCustomFacts language) {
        this.language = language;
    }

    /**
     * Sets the place hierarchy.
     *
     * @param placeHierarchy
     *            the new place hierarchy
     */
    public void setPlaceHierarchy(String placeHierarchy) {
        this.placeHierarchy = placeHierarchy == null ? null : new StringWithCustomFacts(placeHierarchy);
    }

    /**
     * Sets the place hierarchy.
     *
     * @param placeHierarchy
     *            the new place hierarchy
     */
    public void setPlaceHierarchy(StringWithCustomFacts placeHierarchy) {
        this.placeHierarchy = placeHierarchy;
    }

    /**
     * Sets the source system.
     *
     * @param sourceSystem
     *            the new source system
     */
    public void setSourceSystem(SourceSystem sourceSystem) {
        this.sourceSystem = sourceSystem;
    }

    /**
     * Sets the submission reference.
     *
     * @param submissionReference
     *            the new submissionReference
     */
    public void setSubmissionReference(SubmissionReference submissionReference) {
        this.submissionReference = submissionReference;
    }

    /**
     * Sets the submitter.
     *
     * @param submitterReference
     *            the new submitter
     */
    public void setSubmitterReference(SubmitterReference submitterReference) {
        this.submitterReference = submitterReference;
    }

    /**
     * Sets the time.
     *
     * @param time
     *            the new time
     */
    public void setTime(String time) {
        this.time = time == null ? null : new StringWithCustomFacts(time);
    }

    /**
     * Sets the time.
     *
     * @param time
     *            the new time
     */
    public void setTime(StringWithCustomFacts time) {
        this.time = time;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(64);
        builder.append("Header [");
        if (characterSet != null) {
            builder.append("characterSet=");
            builder.append(characterSet);
            builder.append(", ");
        }
        if (copyrightData != null) {
            builder.append("copyrightData=");
            builder.append(copyrightData);
            builder.append(", ");
        }
        if (date != null) {
            builder.append("date=");
            builder.append(date);
            builder.append(", ");
        }
        if (destinationSystem != null) {
            builder.append("destinationSystem=");
            builder.append(destinationSystem);
            builder.append(", ");
        }
        if (fileName != null) {
            builder.append("fileName=");
            builder.append(fileName);
            builder.append(", ");
        }
        if (gedcomVersion != null) {
            builder.append("gedcomVersion=");
            builder.append(gedcomVersion);
            builder.append(", ");
        }
        if (language != null) {
            builder.append("language=");
            builder.append(language);
            builder.append(", ");
        }
        if (getNoteStructures() != null) {
            builder.append("noteStructures=");
            builder.append(getNoteStructures());
            builder.append(", ");
        }
        if (placeHierarchy != null) {
            builder.append("placeHierarchy=");
            builder.append(placeHierarchy);
            builder.append(", ");
        }
        if (sourceSystem != null) {
            builder.append("sourceSystem=");
            builder.append(sourceSystem);
            builder.append(", ");
        }
        if (submissionReference != null) {
            builder.append("submissionReference=");
            builder.append(submissionReference);
            builder.append(", ");
        }
        if (submitterReference != null) {
            builder.append("submitter=");
            builder.append(submitterReference);
            builder.append(", ");
        }
        if (time != null) {
            builder.append("time=");
            builder.append(time);
            builder.append(", ");
        }
        if (getCustomFacts() != null) {
            builder.append("customFacts=");
            builder.append(getCustomFacts());
        }
        builder.append("]");
        return builder.toString();
    }

}
