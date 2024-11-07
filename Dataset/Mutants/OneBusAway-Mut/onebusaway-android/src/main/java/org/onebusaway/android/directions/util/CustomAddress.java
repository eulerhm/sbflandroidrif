/*
 * Copyright 2012 University of South Florida
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.onebusaway.android.directions.util;

import android.location.Address;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import org.geojson.Feature;
import org.geojson.Point;
import java.util.ArrayList;
import java.util.Locale;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Created by foucelhas on 18/08/14.
 */
public class CustomAddress extends Address {

    private static final int ADDRESS_MAX_LINES_TO_SHOW = 5;

    private boolean isTransitCategory = false;

    public CustomAddress(Locale locale) {
        super(locale);
    }

    public CustomAddress() {
        super(Locale.getDefault());
    }

    /**
     * Creates a CustomAddress out of a Android Address from the Android platform Geocoder API
     * @param address
     */
    public CustomAddress(Address address) {
        super(address.getLocale());
        if (!ListenerUtil.mutListener.listen(5664)) {
            {
                long _loopCounter51 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(5663) ? (i >= address.getMaxAddressLineIndex()) : (ListenerUtil.mutListener.listen(5662) ? (i > address.getMaxAddressLineIndex()) : (ListenerUtil.mutListener.listen(5661) ? (i < address.getMaxAddressLineIndex()) : (ListenerUtil.mutListener.listen(5660) ? (i != address.getMaxAddressLineIndex()) : (ListenerUtil.mutListener.listen(5659) ? (i == address.getMaxAddressLineIndex()) : (i <= address.getMaxAddressLineIndex())))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter51", ++_loopCounter51);
                    if (!ListenerUtil.mutListener.listen(5658)) {
                        super.setAddressLine(i, address.getAddressLine(i));
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(5665)) {
            super.setFeatureName(address.getFeatureName());
        }
        if (!ListenerUtil.mutListener.listen(5666)) {
            super.setAdminArea(address.getAdminArea());
        }
        if (!ListenerUtil.mutListener.listen(5667)) {
            super.setSubAdminArea(address.getSubAdminArea());
        }
        if (!ListenerUtil.mutListener.listen(5668)) {
            super.setLocality(address.getLocality());
        }
        if (!ListenerUtil.mutListener.listen(5669)) {
            super.setSubLocality(address.getSubLocality());
        }
        if (!ListenerUtil.mutListener.listen(5670)) {
            super.setThoroughfare(address.getThoroughfare());
        }
        if (!ListenerUtil.mutListener.listen(5671)) {
            super.setSubThoroughfare(address.getSubThoroughfare());
        }
        if (!ListenerUtil.mutListener.listen(5672)) {
            super.setPostalCode(address.getPostalCode());
        }
        if (!ListenerUtil.mutListener.listen(5673)) {
            super.setCountryCode(address.getCountryCode());
        }
        if (!ListenerUtil.mutListener.listen(5674)) {
            super.setCountryName(address.getCountryName());
        }
        if (!ListenerUtil.mutListener.listen(5675)) {
            super.setLatitude(address.getLatitude());
        }
        if (!ListenerUtil.mutListener.listen(5676)) {
            super.setLongitude(address.getLongitude());
        }
        if (!ListenerUtil.mutListener.listen(5677)) {
            super.setPhone(address.getPhone());
        }
        if (!ListenerUtil.mutListener.listen(5678)) {
            super.setUrl(address.getUrl());
        }
        if (!ListenerUtil.mutListener.listen(5679)) {
            super.setExtras(address.getExtras());
        }
    }

    /**
     * Creates a CustomAddress out of a GeoJSON Feature (e.g., from Pelias Autocomplete API)
     * @param address
     */
    public CustomAddress(Feature address) {
        super(Locale.getDefault());
        if (!ListenerUtil.mutListener.listen(5680)) {
            // and https://github.com/CUTR-at-USF/pelias-client-library/blob/master/src/test/java/edu/usf/cutr/pelias/AutocompleteTest.java#L42
            super.setAddressLine(0, (String) address.getProperties().get("name"));
        }
        if (!ListenerUtil.mutListener.listen(5681)) {
            super.setFeatureName((String) address.getProperties().get("label"));
        }
        if (!ListenerUtil.mutListener.listen(5682)) {
            // super.setSubThoroughfare(address.getSubThoroughfare());
            super.setPostalCode((String) address.getProperties().get("postalcode"));
        }
        if (!ListenerUtil.mutListener.listen(5683)) {
            // super.setCountryCode(address.getCountryCode());
            super.setCountryName((String) address.getProperties().get("country"));
        }
        Point p = (Point) address.getGeometry();
        if (!ListenerUtil.mutListener.listen(5684)) {
            super.setLatitude(p.getCoordinates().getLatitude());
        }
        if (!ListenerUtil.mutListener.listen(5685)) {
            super.setLongitude(p.getCoordinates().getLongitude());
        }
        // Check if the geocoder marked this location as having a public transportation category
        ArrayList<String> categories = address.getProperty("category");
        if (!ListenerUtil.mutListener.listen(5689)) {
            if (categories != null) {
                if (!ListenerUtil.mutListener.listen(5688)) {
                    {
                        long _loopCounter52 = 0;
                        for (String category : categories) {
                            ListenerUtil.loopListener.listen("_loopCounter52", ++_loopCounter52);
                            if (!ListenerUtil.mutListener.listen(5687)) {
                                if (category.equalsIgnoreCase("transport:public")) {
                                    if (!ListenerUtil.mutListener.listen(5686)) {
                                        isTransitCategory = true;
                                    }
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (!ListenerUtil.mutListener.listen(5691)) {
            if (getFeatureName() != null) {
                if (!ListenerUtil.mutListener.listen(5690)) {
                    sb.append(getFeatureName());
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(5694)) {
            if ((ListenerUtil.mutListener.listen(5692) ? (getSubThoroughfare() != null || !getSubThoroughfare().equals(getFeatureName())) : (getSubThoroughfare() != null && !getSubThoroughfare().equals(getFeatureName())))) {
                if (!ListenerUtil.mutListener.listen(5693)) {
                    sb.append(", " + getSubThoroughfare());
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(5697)) {
            if ((ListenerUtil.mutListener.listen(5695) ? (getThoroughfare() != null || !getThoroughfare().equals(getFeatureName())) : (getThoroughfare() != null && !getThoroughfare().equals(getFeatureName())))) {
                if (!ListenerUtil.mutListener.listen(5696)) {
                    sb.append(" " + getThoroughfare());
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(5700)) {
            if ((ListenerUtil.mutListener.listen(5698) ? (getSubAdminArea() != null || !getSubAdminArea().equals(getFeatureName())) : (getSubAdminArea() != null && !getSubAdminArea().equals(getFeatureName())))) {
                if (!ListenerUtil.mutListener.listen(5699)) {
                    sb.append(", " + getSubAdminArea());
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(5703)) {
            if ((ListenerUtil.mutListener.listen(5701) ? (getLocality() != null || !getLocality().equals(getFeatureName())) : (getLocality() != null && !getLocality().equals(getFeatureName())))) {
                if (!ListenerUtil.mutListener.listen(5702)) {
                    sb.append(", " + getLocality());
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(5718)) {
            if (TextUtils.isEmpty(sb.toString())) {
                int maxLines = ((ListenerUtil.mutListener.listen(5708) ? (ADDRESS_MAX_LINES_TO_SHOW >= getMaxAddressLineIndex()) : (ListenerUtil.mutListener.listen(5707) ? (ADDRESS_MAX_LINES_TO_SHOW <= getMaxAddressLineIndex()) : (ListenerUtil.mutListener.listen(5706) ? (ADDRESS_MAX_LINES_TO_SHOW < getMaxAddressLineIndex()) : (ListenerUtil.mutListener.listen(5705) ? (ADDRESS_MAX_LINES_TO_SHOW != getMaxAddressLineIndex()) : (ListenerUtil.mutListener.listen(5704) ? (ADDRESS_MAX_LINES_TO_SHOW == getMaxAddressLineIndex()) : (ADDRESS_MAX_LINES_TO_SHOW > getMaxAddressLineIndex()))))))) ? getMaxAddressLineIndex() + 1 : ADDRESS_MAX_LINES_TO_SHOW;
                if (!ListenerUtil.mutListener.listen(5709)) {
                    sb.append(getAddressLine(0));
                }
                if (!ListenerUtil.mutListener.listen(5717)) {
                    {
                        long _loopCounter53 = 0;
                        for (int i = 1; (ListenerUtil.mutListener.listen(5716) ? (i >= maxLines) : (ListenerUtil.mutListener.listen(5715) ? (i <= maxLines) : (ListenerUtil.mutListener.listen(5714) ? (i > maxLines) : (ListenerUtil.mutListener.listen(5713) ? (i != maxLines) : (ListenerUtil.mutListener.listen(5712) ? (i == maxLines) : (i < maxLines)))))); i++) {
                            ListenerUtil.loopListener.listen("_loopCounter53", ++_loopCounter53);
                            if (!ListenerUtil.mutListener.listen(5711)) {
                                if (getAddressLine(i) != null) {
                                    if (!ListenerUtil.mutListener.listen(5710)) {
                                        sb.append(", " + getAddressLine(i));
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return sb.toString();
    }

    public static final Parcelable.Creator<CustomAddress> CREATOR = new Parcelable.Creator<CustomAddress>() {

        public CustomAddress createFromParcel(Parcel in) {
            String language = in.readString();
            String country = in.readString();
            Locale locale = (ListenerUtil.mutListener.listen(5723) ? (country.length() >= 0) : (ListenerUtil.mutListener.listen(5722) ? (country.length() <= 0) : (ListenerUtil.mutListener.listen(5721) ? (country.length() < 0) : (ListenerUtil.mutListener.listen(5720) ? (country.length() != 0) : (ListenerUtil.mutListener.listen(5719) ? (country.length() == 0) : (country.length() > 0)))))) ? new Locale(language, country) : new Locale(language);
            CustomAddress a = new CustomAddress(locale);
            int N = in.readInt();
            if (!ListenerUtil.mutListener.listen(5736)) {
                if ((ListenerUtil.mutListener.listen(5728) ? (N >= 0) : (ListenerUtil.mutListener.listen(5727) ? (N <= 0) : (ListenerUtil.mutListener.listen(5726) ? (N < 0) : (ListenerUtil.mutListener.listen(5725) ? (N != 0) : (ListenerUtil.mutListener.listen(5724) ? (N == 0) : (N > 0))))))) {
                    if (!ListenerUtil.mutListener.listen(5735)) {
                        {
                            long _loopCounter54 = 0;
                            for (int i = 0; (ListenerUtil.mutListener.listen(5734) ? (i >= N) : (ListenerUtil.mutListener.listen(5733) ? (i <= N) : (ListenerUtil.mutListener.listen(5732) ? (i > N) : (ListenerUtil.mutListener.listen(5731) ? (i != N) : (ListenerUtil.mutListener.listen(5730) ? (i == N) : (i < N)))))); i++) {
                                ListenerUtil.loopListener.listen("_loopCounter54", ++_loopCounter54);
                                int index = in.readInt();
                                String line = in.readString();
                                if (!ListenerUtil.mutListener.listen(5729)) {
                                    a.setAddressLine(index, line);
                                }
                            }
                        }
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(5737)) {
                a.setFeatureName(in.readString());
            }
            if (!ListenerUtil.mutListener.listen(5738)) {
                a.setAdminArea(in.readString());
            }
            if (!ListenerUtil.mutListener.listen(5739)) {
                a.setSubAdminArea(in.readString());
            }
            if (!ListenerUtil.mutListener.listen(5740)) {
                a.setLocality(in.readString());
            }
            if (!ListenerUtil.mutListener.listen(5741)) {
                a.setSubLocality(in.readString());
            }
            if (!ListenerUtil.mutListener.listen(5742)) {
                a.setThoroughfare(in.readString());
            }
            if (!ListenerUtil.mutListener.listen(5743)) {
                a.setSubThoroughfare(in.readString());
            }
            if (!ListenerUtil.mutListener.listen(5744)) {
                a.setPremises(in.readString());
            }
            if (!ListenerUtil.mutListener.listen(5745)) {
                a.setPostalCode(in.readString());
            }
            if (!ListenerUtil.mutListener.listen(5746)) {
                a.setCountryCode(in.readString());
            }
            if (!ListenerUtil.mutListener.listen(5747)) {
                a.setCountryName(in.readString());
            }
            boolean mHasLatitude = in.readInt() != 0;
            if (!ListenerUtil.mutListener.listen(5749)) {
                if (mHasLatitude) {
                    if (!ListenerUtil.mutListener.listen(5748)) {
                        a.setLatitude(in.readDouble());
                    }
                }
            }
            boolean mHasLongitude = in.readInt() != 0;
            if (!ListenerUtil.mutListener.listen(5751)) {
                if (mHasLongitude) {
                    if (!ListenerUtil.mutListener.listen(5750)) {
                        a.setLongitude(in.readDouble());
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(5752)) {
                a.setPhone(in.readString());
            }
            if (!ListenerUtil.mutListener.listen(5753)) {
                a.setUrl(in.readString());
            }
            if (!ListenerUtil.mutListener.listen(5754)) {
                a.setExtras(in.readBundle());
            }
            return a;
        }

        public CustomAddress[] newArray(int size) {
            return new CustomAddress[size];
        }
    };

    /**
     * Is this custom address set.
     *
     * @return true if this address has a valid latitude and longitude
     */
    public boolean isSet() {
        return (ListenerUtil.mutListener.listen(5755) ? (getLatitude() != Double.MAX_VALUE || getLongitude() != Double.MAX_VALUE) : (getLatitude() != Double.MAX_VALUE && getLongitude() != Double.MAX_VALUE));
    }

    /**
     * Create a blank CustomAddress.
     *
     * @return CustomAddress with default locale and unset latitude and longitude.
     */
    public static CustomAddress getEmptyAddress() {
        Locale locale = Locale.getDefault();
        CustomAddress addr = new CustomAddress(locale);
        if (!ListenerUtil.mutListener.listen(5756)) {
            addr.setLatitude(Double.MAX_VALUE);
        }
        if (!ListenerUtil.mutListener.listen(5757)) {
            addr.setLongitude(Double.MAX_VALUE);
        }
        return addr;
    }

    /**
     * Return true if this location has been labeled under the category of "public transportation",
     * false if it has not
     *
     * @return true if this location has been labeled under the category of "public transportation",
     * false if it has not
     */
    public boolean isTransitCategory() {
        return isTransitCategory;
    }
}
