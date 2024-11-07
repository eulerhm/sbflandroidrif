/*
 * Copyright (C) 2013 Paul Watts (paulcwatts@gmail.com)
 * and individual contributors
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

import org.onebusaway.android.R;
import org.onebusaway.android.app.Application;
import org.onebusaway.android.io.ObaApi;
import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.concurrent.Callable;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Retrieves the current list of OneBusAway regions.
 * {https://github.com/OneBusAway/onebusaway/wiki/Multi-Region#regions-rest-api}
 *
 * @author Paul Watts (paulcwatts@gmail.com)
 */
public final class ObaRegionsRequest extends RequestBase implements Callable<ObaRegionsResponse> {

    protected ObaRegionsRequest(Uri uri) {
        super(uri);
    }

    // 
    public static class Builder {

        private static Uri URI = Uri.parse(Application.get().getResources().getString(R.string.regions_api_url));

        public Builder(Context context) {
        }

        public Builder(Context context, Uri uri) {
            if (!ListenerUtil.mutListener.listen(8376)) {
                URI = uri;
            }
        }

        public ObaRegionsRequest build() {
            return new ObaRegionsRequest(URI);
        }
    }

    /**
     * Helper method for constructing new instances.
     *
     * @param context The package context.
     * @return The new request instance.
     */
    public static ObaRegionsRequest newRequest(Context context) {
        return new Builder(context).build();
    }

    /**
     * Helper method for constructing new instances, allowing
     * the requester to set the URI to retrieve the regions info
     * from
     *
     * @param context The package context.
     * @param uri     URI to the regions file
     * @return The new request instance.
     */
    public static ObaRegionsRequest newRequest(Context context, Uri uri) {
        return new Builder(context, uri).build();
    }

    @Override
    public ObaRegionsResponse call() {
        // If the URI is for an Android resource then get from resource, otherwise get from Region REST API
        if (mUri.getScheme().equals(ContentResolver.SCHEME_ANDROID_RESOURCE)) {
            return getRegionFromResource();
        } else {
            return call(ObaRegionsResponse.class);
        }
    }

    @Override
    public String toString() {
        return "ObaRegionsRequest [mUri=" + mUri + "]";
    }

    private ObaRegionsResponse getRegionFromResource() {
        ObaRegionsResponse response = null;
        InputStream is = Application.get().getApplicationContext().getResources().openRawResource(R.raw.regions_v3);
        ObaApi.SerializationHandler handler = ObaApi.getSerializer(ObaRegionsResponse.class);
        if (!ListenerUtil.mutListener.listen(8377)) {
            response = handler.deserialize(new InputStreamReader(is), ObaRegionsResponse.class);
        }
        if (!ListenerUtil.mutListener.listen(8379)) {
            if (response == null) {
                if (!ListenerUtil.mutListener.listen(8378)) {
                    response = handler.createFromError(ObaRegionsResponse.class, ObaApi.OBA_INTERNAL_ERROR, "Json error");
                }
            }
        }
        return response;
    }
}
