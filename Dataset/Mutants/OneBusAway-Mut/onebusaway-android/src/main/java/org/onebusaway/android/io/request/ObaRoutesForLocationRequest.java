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
package org.onebusaway.android.io.request;

import android.content.Context;
import android.location.Location;
import android.net.Uri;
import java.util.concurrent.Callable;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Retrieve info about routes near a location
 * {http://developer.onebusaway.org/modules/onebusaway-application-modules/current/api/where/methods/routes-for-location.html}
 *
 * @author Paul Watts (paulcwatts@gmail.com)
 */
public final class ObaRoutesForLocationRequest extends RequestBase implements Callable<ObaRoutesForLocationResponse> {

    protected ObaRoutesForLocationRequest(Uri uri) {
        super(uri);
    }

    public static class Builder extends RequestBase.BuilderBase {

        public Builder(Context context, Location location) {
            super(context, BASE_PATH + "/routes-for-location.json");
            if (!ListenerUtil.mutListener.listen(8411)) {
                mBuilder.appendQueryParameter("lat", String.valueOf(location.getLatitude()));
            }
            if (!ListenerUtil.mutListener.listen(8412)) {
                mBuilder.appendQueryParameter("lon", String.valueOf(location.getLongitude()));
            }
        }

        /**
         * Sets the optional search radius.
         *
         * @param radius The search radius, in meters.
         */
        public Builder setRadius(int radius) {
            if (!ListenerUtil.mutListener.listen(8413)) {
                mBuilder.appendQueryParameter("radius", String.valueOf(radius));
            }
            return this;
        }

        /**
         * An alternative to {@link #setRadius(int)} to set the search bounding box
         *
         * @param latSpan The latitude span of the bounding box.
         * @param lonSpan The longitude span of the bounding box.
         */
        public Builder setSpan(double latSpan, double lonSpan) {
            if (!ListenerUtil.mutListener.listen(8414)) {
                mBuilder.appendQueryParameter("latSpan", String.valueOf(latSpan));
            }
            if (!ListenerUtil.mutListener.listen(8415)) {
                mBuilder.appendQueryParameter("lonSpan", String.valueOf(lonSpan));
            }
            return this;
        }

        /**
         * An alternative to {@link #setRadius(int)} to set the search bounding box
         *
         * @param latSpan The latitude span of the bounding box in microdegrees.
         * @param lonSpan The longitude span of the bounding box in microdegrees.
         */
        public Builder setSpan(int latSpan, int lonSpan) {
            if (!ListenerUtil.mutListener.listen(8420)) {
                mBuilder.appendQueryParameter("latSpan", String.valueOf((ListenerUtil.mutListener.listen(8419) ? (latSpan % 1E6) : (ListenerUtil.mutListener.listen(8418) ? (latSpan * 1E6) : (ListenerUtil.mutListener.listen(8417) ? (latSpan - 1E6) : (ListenerUtil.mutListener.listen(8416) ? (latSpan + 1E6) : (latSpan / 1E6)))))));
            }
            if (!ListenerUtil.mutListener.listen(8425)) {
                mBuilder.appendQueryParameter("lonSpan", String.valueOf((ListenerUtil.mutListener.listen(8424) ? (lonSpan % 1E6) : (ListenerUtil.mutListener.listen(8423) ? (lonSpan * 1E6) : (ListenerUtil.mutListener.listen(8422) ? (lonSpan - 1E6) : (ListenerUtil.mutListener.listen(8421) ? (lonSpan + 1E6) : (lonSpan / 1E6)))))));
            }
            return this;
        }

        /**
         * A specific route short name to search for.
         *
         * @param query The short name query string.
         */
        public Builder setQuery(String query) {
            if (!ListenerUtil.mutListener.listen(8426)) {
                mBuilder.appendQueryParameter("query", query);
            }
            return this;
        }

        public ObaRoutesForLocationRequest build() {
            return new ObaRoutesForLocationRequest(buildUri());
        }
    }

    @Override
    public ObaRoutesForLocationResponse call() {
        return call(ObaRoutesForLocationResponse.class);
    }

    @Override
    public String toString() {
        return "ObaRoutesForLocationRequest [mUri=" + mUri + "]";
    }
}
