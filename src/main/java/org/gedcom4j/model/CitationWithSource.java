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
import org.gedcom4j.model.enumerations.FamilyEventType;
import org.gedcom4j.model.enumerations.IndividualAttributeType;
import org.gedcom4j.model.enumerations.IndividualEventType;

/**
 * A citation with a source. Corresponds to the first (preferred) form of the SOURCE_CITATION structure (which you'd do in Pascal
 * with a variant record, but here we use subclasses of a parent abstract class).
 * 
 * @author frizbog1
 * 
 */
public class CitationWithSource extends AbstractCitation {
    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = 1886846774727359828L;

    /**
     * A list of citation data entries
     */
    private List<CitationData> data = getData(Options.isCollectionInitializationEnabled());

    /**
     * The type of event or attribute cited from. Will be the tag from one of the the following three enum types:
     * {@link FamilyEventType}, {@link IndividualEventType}, {@link IndividualAttributeType}.
     */
    private StringWithCustomFacts eventCited;

    /**
     * The role in the event cited
     */
    private StringWithCustomFacts roleInEvent;

    /**
     * A reference to the cited source
     */
    private Source source;

    /**
     * Where within the source is the information being cited
     */
    private StringWithCustomFacts whereInSource;

    /** Default constructor */
    public CitationWithSource() {
        // Default constructor does nothing
    }

    /**
     * Copy constructor
     * 
     * @param other
     *            object being copied
     */
    public CitationWithSource(CitationWithSource other) {
        super(other);
        if (other.certainty != null) {
            certainty = new StringWithCustomFacts(other.certainty);
        }
        if (other.data != null) {
            data = new ArrayList<>();
            for (CitationData d : other.data) {
                data.add(new CitationData(d));
            }
        }
        if (other.eventCited != null) {
            eventCited = new StringWithCustomFacts(other.eventCited);
        }
        if (other.multimedia != null) {
            multimedia = new ArrayList<>();
            for (MultimediaReference m : multimedia) {
                multimedia.add(new MultimediaReference(m));
            }
        }
        if (other.roleInEvent != null) {
            roleInEvent = new StringWithCustomFacts(other.roleInEvent);
        }
        if (other.source != null) {
            source = new Source(other.source);
        }
        if (other.whereInSource != null) {
            whereInSource = new StringWithCustomFacts(other.whereInSource);
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
        if (!(obj instanceof CitationWithSource)) {
            return false;
        }
        CitationWithSource other = (CitationWithSource) obj;
        if (data == null) {
            if (other.data != null) {
                return false;
            }
        } else if (!data.equals(other.data)) {
            return false;
        }
        if (eventCited == null) {
            if (other.eventCited != null) {
                return false;
            }
        } else if (!eventCited.equals(other.eventCited)) {
            return false;
        }
        if (roleInEvent == null) {
            if (other.roleInEvent != null) {
                return false;
            }
        } else if (!roleInEvent.equals(other.roleInEvent)) {
            return false;
        }
        if (source == null) {
            if (other.source != null) {
                return false;
            }
        } else if (!source.equals(other.source)) {
            return false;
        }
        if (whereInSource == null) {
            if (other.whereInSource != null) {
                return false;
            }
        } else if (!whereInSource.equals(other.whereInSource)) {
            return false;
        }
        return true;
    }

    /**
     * Gets the data.
     *
     * @return the data
     */
    public List<CitationData> getData() {
        return data;
    }

    /**
     * Get the data
     * 
     * @param initializeIfNeeded
     *            true if this collection should be created on-the-fly if it is currently null
     * @return the data
     */
    public List<CitationData> getData(boolean initializeIfNeeded) {
        if (initializeIfNeeded && data == null) {
            data = new ArrayList<>(0);
        }
        return data;
    }

    /**
     * Gets the event cited.
     *
     * @return the event cited
     */
    public StringWithCustomFacts getEventCited() {
        return eventCited;
    }

    /**
     * Gets the role in event.
     *
     * @return the role in event
     */
    public StringWithCustomFacts getRoleInEvent() {
        return roleInEvent;
    }

    /**
     * Gets the source.
     *
     * @return the source
     */
    public Source getSource() {
        return source;
    }

    /**
     * Gets where in the source is being cited
     *
     * @return where in source
     */
    public StringWithCustomFacts getWhereInSource() {
        return whereInSource;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((data == null) ? 0 : data.hashCode());
        result = prime * result + ((eventCited == null) ? 0 : eventCited.hashCode());
        result = prime * result + ((roleInEvent == null) ? 0 : roleInEvent.hashCode());
        result = prime * result + ((source == null) ? 0 : source.hashCode());
        result = prime * result + ((whereInSource == null) ? 0 : whereInSource.hashCode());
        return result;
    }

    /**
     * Sets the event cited.
     *
     * @param eventCited
     *            the new event cited
     */
    public void setEventCited(String eventCited) {
        this.eventCited = eventCited == null ? null : new StringWithCustomFacts(eventCited);
    }

    /**
     * Sets the event cited.
     *
     * @param eventCited
     *            the new event cited
     */
    public void setEventCited(StringWithCustomFacts eventCited) {
        this.eventCited = eventCited;
    }

    /**
     * Sets the role in event.
     *
     * @param roleInEvent
     *            the new role in event
     */
    public void setRoleInEvent(String roleInEvent) {
        this.roleInEvent = roleInEvent == null ? null : new StringWithCustomFacts(roleInEvent);
    }

    /**
     * Sets the role in event.
     *
     * @param roleInEvent
     *            the new role in event
     */
    public void setRoleInEvent(StringWithCustomFacts roleInEvent) {
        this.roleInEvent = roleInEvent;
    }

    /**
     * Sets the source.
     *
     * @param source
     *            the new source
     */
    public void setSource(Source source) {
        this.source = source;
    }

    /**
     * Sets where in the source is being cited
     *
     * @param whereInSource
     *            where in the source is being cited
     */
    public void setWhereInSource(String whereInSource) {
        this.whereInSource = whereInSource == null ? null : new StringWithCustomFacts(whereInSource);
    }

    /**
     * Sets where in the source is being cited
     *
     * @param whereInSource
     *            where in the source is being cited
     */
    public void setWhereInSource(StringWithCustomFacts whereInSource) {
        this.whereInSource = whereInSource;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(64);
        builder.append("CitationWithSource [");
        if (certainty != null) {
            builder.append("certainty=");
            builder.append(certainty);
            builder.append(", ");
        }
        if (data != null) {
            builder.append("data=");
            builder.append(data);
            builder.append(", ");
        }
        if (eventCited != null) {
            builder.append("eventCited=");
            builder.append(eventCited);
            builder.append(", ");
        }
        if (multimedia != null) {
            builder.append("multimedia=");
            builder.append(multimedia);
            builder.append(", ");
        }
        if (roleInEvent != null) {
            builder.append("roleInEvent=");
            builder.append(roleInEvent);
            builder.append(", ");
        }
        if (source != null) {
            builder.append("source=");
            builder.append(source);
            builder.append(", ");
        }
        if (whereInSource != null) {
            builder.append("whereInSource=");
            builder.append(whereInSource);
            builder.append(", ");
        }
        if (getNoteStructures() != null) {
            builder.append("noteStructures=");
            builder.append(getNoteStructures());
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
