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

/**
 * A submission of a gedcom transmission. Corresponds to SUBMISSION_RECORD in the GEDCOM standard.
 * 
 * @author frizbog1
 */
public class Submission extends AbstractElement implements HasXref {
    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = -2881845846882881000L;

    /**
     * The number of ancestor generations
     */
    private StringWithCustomFacts ancestorsCount;

    /**
     * The number of descendant generations
     */
    private StringWithCustomFacts descendantsCount;

    /**
     * The name of the family file
     */
    private StringWithCustomFacts nameOfFamilyFile;

    /**
     * The ordinance process flag
     */
    private StringWithCustomFacts ordinanceProcessFlag;

    /**
     * The record ID number
     */
    private StringWithCustomFacts recIdNumber;

    /**
     * The submitter of this submission
     */
    private Submitter submitter;

    /**
     * The temple code for this submission
     */
    private StringWithCustomFacts templeCode;

    /**
     * The xref for this submitter
     */
    private String xref;

    /** Default constructor */
    public Submission() {
        // Default constructor does nothing
    }

    /**
     * Constructor, takes required xref value
     * 
     * @param xref
     *            the xref id for this submission
     */
    public Submission(String xref) {
        this.xref = xref;
    }

    /**
     * Copy constructor
     * 
     * @param other
     *            object being copied
     */
    public Submission(Submission other) {
        super(other);
        if (other.ancestorsCount != null) {
            ancestorsCount = new StringWithCustomFacts(other.ancestorsCount);
        }
        if (other.descendantsCount != null) {
            descendantsCount = new StringWithCustomFacts(other.descendantsCount);
        }
        if (other.nameOfFamilyFile != null) {
            nameOfFamilyFile = new StringWithCustomFacts(other.nameOfFamilyFile);
        }
        if (other.ordinanceProcessFlag != null) {
            ordinanceProcessFlag = new StringWithCustomFacts(other.ordinanceProcessFlag);
        }
        if (other.recIdNumber != null) {
            recIdNumber = new StringWithCustomFacts(other.recIdNumber);
        }
        if (other.submitter != null) {
            submitter = new Submitter(other.submitter);
        }
        if (other.templeCode != null) {
            templeCode = new StringWithCustomFacts(other.templeCode);
        }
        xref = other.xref;

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object obj) {
        if (this != obj) {
            return true;
        }
        if (!super.equals(obj)) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Submission other = (Submission) obj;
        if (ancestorsCount == null) {
            if (other.ancestorsCount != null) {
                return false;
            }
        } else if (!ancestorsCount.equals(other.ancestorsCount)) {
            return false;
        }
        if (descendantsCount == null) {
            if (other.descendantsCount != null) {
                return false;
            }
        } else if (!descendantsCount.equals(other.descendantsCount)) {
            return false;
        }
        if (nameOfFamilyFile == null) {
            if (other.nameOfFamilyFile != null) {
                return false;
            }
        } else if (!nameOfFamilyFile.equals(other.nameOfFamilyFile)) {
            return false;
        }
        if (ordinanceProcessFlag == null) {
            if (other.ordinanceProcessFlag != null) {
                return false;
            }
        } else if (!ordinanceProcessFlag.equals(other.ordinanceProcessFlag)) {
            return false;
        }
        if (recIdNumber == null) {
            if (other.recIdNumber != null) {
                return false;
            }
        } else if (!recIdNumber.equals(other.recIdNumber)) {
            return false;
        }
        if (submitter == null) {
            if (other.submitter != null) {
                return false;
            }
        } else if (!submitter.equals(other.submitter)) {
            return false;
        }
        if (templeCode == null) {
            if (other.templeCode != null) {
                return false;
            }
        } else if (!templeCode.equals(other.templeCode)) {
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
     * Gets the ancestors count.
     *
     * @return the ancestors count
     */
    public StringWithCustomFacts getAncestorsCount() {
        return ancestorsCount;
    }

    /**
     * Gets the descendants count.
     *
     * @return the descendants count
     */
    public StringWithCustomFacts getDescendantsCount() {
        return descendantsCount;
    }

    /**
     * Gets the name of family file.
     *
     * @return the name of family file
     */
    public StringWithCustomFacts getNameOfFamilyFile() {
        return nameOfFamilyFile;
    }

    /**
     * Gets the ordinance process flag.
     *
     * @return the ordinance process flag
     */
    public StringWithCustomFacts getOrdinanceProcessFlag() {
        return ordinanceProcessFlag;
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
     * Gets the submitter.
     *
     * @return the submitter
     */
    public Submitter getSubmitter() {
        return submitter;
    }

    /**
     * Gets the temple code.
     *
     * @return the temple code
     */
    public StringWithCustomFacts getTempleCode() {
        return templeCode;
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
        result = prime * result + (ancestorsCount == null ? 0 : ancestorsCount.hashCode());
        result = prime * result + (descendantsCount == null ? 0 : descendantsCount.hashCode());
        result = prime * result + (nameOfFamilyFile == null ? 0 : nameOfFamilyFile.hashCode());
        result = prime * result + (ordinanceProcessFlag == null ? 0 : ordinanceProcessFlag.hashCode());
        result = prime * result + (recIdNumber == null ? 0 : recIdNumber.hashCode());
        result = prime * result + (submitter == null ? 0 : submitter.hashCode());
        result = prime * result + (templeCode == null ? 0 : templeCode.hashCode());
        result = prime * result + (xref == null ? 0 : xref.hashCode());
        return result;
    }

    /**
     * Sets the ancestors count.
     *
     * @param ancestorsCount
     *            the new ancestors count
     */
    public void setAncestorsCount(String ancestorsCount) {
        this.ancestorsCount = ancestorsCount == null ? null : new StringWithCustomFacts(ancestorsCount);
    }

    /**
     * Sets the ancestors count.
     *
     * @param ancestorsCount
     *            the new ancestors count
     */
    public void setAncestorsCount(StringWithCustomFacts ancestorsCount) {
        this.ancestorsCount = ancestorsCount;
    }

    /**
     * Sets the descendants count.
     *
     * @param descendantsCount
     *            the new descendants count
     */
    public void setDescendantsCount(String descendantsCount) {
        this.descendantsCount = descendantsCount == null ? null : new StringWithCustomFacts(descendantsCount);
    }

    /**
     * Sets the descendants count.
     *
     * @param descendantsCount
     *            the new descendants count
     */
    public void setDescendantsCount(StringWithCustomFacts descendantsCount) {
        this.descendantsCount = descendantsCount;
    }

    /**
     * Sets the name of family file.
     *
     * @param nameOfFamilyFile
     *            the new name of family file
     */
    public void setNameOfFamilyFile(String nameOfFamilyFile) {
        this.nameOfFamilyFile = nameOfFamilyFile == null ? null : new StringWithCustomFacts(nameOfFamilyFile);
    }

    /**
     * Sets the name of family file.
     *
     * @param nameOfFamilyFile
     *            the new name of family file
     */
    public void setNameOfFamilyFile(StringWithCustomFacts nameOfFamilyFile) {
        this.nameOfFamilyFile = nameOfFamilyFile;
    }

    /**
     * Sets the ordinance process flag.
     *
     * @param ordinanceProcessFlag
     *            the new ordinance process flag
     */
    public void setOrdinanceProcessFlag(String ordinanceProcessFlag) {
        this.ordinanceProcessFlag = ordinanceProcessFlag == null ? null : new StringWithCustomFacts(ordinanceProcessFlag);
    }

    /**
     * Sets the ordinance process flag.
     *
     * @param ordinanceProcessFlag
     *            the new ordinance process flag
     */
    public void setOrdinanceProcessFlag(StringWithCustomFacts ordinanceProcessFlag) {
        this.ordinanceProcessFlag = ordinanceProcessFlag;
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
     * Sets the submitter.
     *
     * @param submitter
     *            the new submitter
     */
    public void setSubmitter(Submitter submitter) {
        this.submitter = submitter;
    }

    /**
     * Sets the temple code.
     *
     * @param templeCode
     *            the new temple code
     */
    public void setTempleCode(String templeCode) {
        this.templeCode = templeCode == null ? null : new StringWithCustomFacts(templeCode);
    }

    /**
     * Sets the temple code.
     *
     * @param templeCode
     *            the new temple code
     */
    public void setTempleCode(StringWithCustomFacts templeCode) {
        this.templeCode = templeCode;
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
        StringBuilder builder = new StringBuilder(64);
        builder.append("Submission [");
        if (ancestorsCount != null) {
            builder.append("ancestorsCount=");
            builder.append(ancestorsCount);
            builder.append(", ");
        }
        if (descendantsCount != null) {
            builder.append("descendantsCount=");
            builder.append(descendantsCount);
            builder.append(", ");
        }
        if (nameOfFamilyFile != null) {
            builder.append("nameOfFamilyFile=");
            builder.append(nameOfFamilyFile);
            builder.append(", ");
        }
        if (ordinanceProcessFlag != null) {
            builder.append("ordinanceProcessFlag=");
            builder.append(ordinanceProcessFlag);
            builder.append(", ");
        }
        if (recIdNumber != null) {
            builder.append("recIdNumber=");
            builder.append(recIdNumber);
            builder.append(", ");
        }
        if (submitter != null) {
            builder.append("submitter=");
            builder.append(submitter);
            builder.append(", ");
        }
        if (templeCode != null) {
            builder.append("templeCode=");
            builder.append(templeCode);
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
        }
        builder.append("]");
        return builder.toString();
    }
}
