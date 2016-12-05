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
 * <p>
 * Represents a Corporation.
 * </p>
 * 
 * <p>
 * If instantiating one of these programmatically rather than through parsing an existing GEDCOM file, you will probably want to
 * change the value of the {@link Corporation#businessName} field.
 * </p>
 * 
 * @author frizbog1
 * 
 */
public class Corporation extends AbstractAddressableElement {
    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = -2468158832702366329L;

    /**
     * The business name. This field must be valued to pass validation, so the default value is "UNSPECIFIED".
     */
    private String businessName = "UNSPECIFIED";

    /** Default constructor */
    public Corporation() {
        // Default constructor does nothing
    }

    /**
     * Copy constructor
     * 
     * @param other
     *            object being copied
     */
    public Corporation(Corporation other) {
        super(other);
        businessName = other.businessName;
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
        if (getClass() != obj.getClass()) {
            return false;
        }
        Corporation other = (Corporation) obj;
        if (businessName == null) {
            if (other.businessName != null) {
                return false;
            }
        } else if (!businessName.equals(other.businessName)) {
            return false;
        }

        return false;
    }

    /**
     * Gets the business name.
     *
     * @return the business name
     */
    public String getBusinessName() {
        return businessName;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + (businessName == null ? 0 : businessName.hashCode());

        return result;
    }

    /**
     * Sets the business name.
     *
     * @param businessName
     *            the new business name
     */
    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(64);
        builder.append("Corporation [");
        if (businessName != null) {
            builder.append("businessName=");
            builder.append(businessName);
            builder.append(", ");
        }
        appendAddressFields(builder, false);
        builder.append("]");
        return builder.toString();
    }

}
