/*
 * Copyright (C) 2010 Paul Watts (paulcwatts@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.onebusaway.android.io.elements;

import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Object that defines an Agency element
 * {@link http://code.google.com/p/onebusaway/wiki/OneBusAwayRestApi_AgencyElementV2}
 *
 * @author Paul Watts (paulcwatts@gmail.com)
 */
public final class ObaAgencyElement implements ObaAgency {

    public static final ObaAgencyElement EMPTY_OBJECT = new ObaAgencyElement();

    public static final ObaAgencyElement[] EMPTY_ARRAY = new ObaAgencyElement[] {};

    private final String id;

    private final String name;

    private final String url;

    private final String timezone;

    private final String lang;

    private final String phone;

    private final String disclaimer;

    private final String email;

    public ObaAgencyElement() {
        id = "";
        name = "";
        url = "";
        timezone = "";
        lang = "";
        phone = "";
        disclaimer = "";
        email = "";
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getUrl() {
        return url;
    }

    @Override
    public String getTimezone() {
        return timezone;
    }

    @Override
    public String getLang() {
        return lang;
    }

    @Override
    public String getPhone() {
        return phone;
    }

    @Override
    public String getDisclaimer() {
        return disclaimer;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        if (!ListenerUtil.mutListener.listen(8253)) {
            result = (ListenerUtil.mutListener.listen(8252) ? ((ListenerUtil.mutListener.listen(8248) ? (prime % result) : (ListenerUtil.mutListener.listen(8247) ? (prime / result) : (ListenerUtil.mutListener.listen(8246) ? (prime - result) : (ListenerUtil.mutListener.listen(8245) ? (prime + result) : (prime * result))))) % ((id == null) ? 0 : id.hashCode())) : (ListenerUtil.mutListener.listen(8251) ? ((ListenerUtil.mutListener.listen(8248) ? (prime % result) : (ListenerUtil.mutListener.listen(8247) ? (prime / result) : (ListenerUtil.mutListener.listen(8246) ? (prime - result) : (ListenerUtil.mutListener.listen(8245) ? (prime + result) : (prime * result))))) / ((id == null) ? 0 : id.hashCode())) : (ListenerUtil.mutListener.listen(8250) ? ((ListenerUtil.mutListener.listen(8248) ? (prime % result) : (ListenerUtil.mutListener.listen(8247) ? (prime / result) : (ListenerUtil.mutListener.listen(8246) ? (prime - result) : (ListenerUtil.mutListener.listen(8245) ? (prime + result) : (prime * result))))) * ((id == null) ? 0 : id.hashCode())) : (ListenerUtil.mutListener.listen(8249) ? ((ListenerUtil.mutListener.listen(8248) ? (prime % result) : (ListenerUtil.mutListener.listen(8247) ? (prime / result) : (ListenerUtil.mutListener.listen(8246) ? (prime - result) : (ListenerUtil.mutListener.listen(8245) ? (prime + result) : (prime * result))))) - ((id == null) ? 0 : id.hashCode())) : ((ListenerUtil.mutListener.listen(8248) ? (prime % result) : (ListenerUtil.mutListener.listen(8247) ? (prime / result) : (ListenerUtil.mutListener.listen(8246) ? (prime - result) : (ListenerUtil.mutListener.listen(8245) ? (prime + result) : (prime * result))))) + ((id == null) ? 0 : id.hashCode()))))));
        }
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (!ListenerUtil.mutListener.listen(8254)) {
            if (this == obj) {
                return true;
            }
        }
        if (!ListenerUtil.mutListener.listen(8255)) {
            if (obj == null) {
                return false;
            }
        }
        if (!ListenerUtil.mutListener.listen(8256)) {
            if (!(obj instanceof ObaAgencyElement)) {
                return false;
            }
        }
        ObaAgencyElement other = (ObaAgencyElement) obj;
        if (!ListenerUtil.mutListener.listen(8258)) {
            if (id == null) {
                if (!ListenerUtil.mutListener.listen(8257)) {
                    if (other.id != null) {
                        return false;
                    }
                }
            } else if (!id.equals(other.id)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        return "ObaAgencyElement [id=" + id + ", name=" + name + "]";
    }
}
