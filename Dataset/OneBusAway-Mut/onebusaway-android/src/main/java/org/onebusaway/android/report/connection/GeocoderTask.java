/*
* Copyright (C) 2016 University of South Florida (sjbarbeau@gmail.com)
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
package org.onebusaway.android.report.connection;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.AsyncTask;
import java.util.List;
import java.util.Locale;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class GeocoderTask extends AsyncTask<Void, Integer, String> {

    private Callback mCallback;

    private Location mLocation;

    private Context mContext;

    public interface Callback {

        /**
         * Called when the GeocoderTask is complete
         *
         * @param address the address string from given location
         */
        void onGeocoderTaskCompleted(String address);
    }

    public GeocoderTask(Callback callback, Location location, Context context) {
        if (!ListenerUtil.mutListener.listen(12076)) {
            this.mCallback = callback;
        }
        if (!ListenerUtil.mutListener.listen(12077)) {
            this.mLocation = location;
        }
        if (!ListenerUtil.mutListener.listen(12078)) {
            this.mContext = context;
        }
    }

    @Override
    protected String doInBackground(Void... voids) {
        String address = "";
        try {
            Geocoder geo = new Geocoder(mContext, Locale.getDefault());
            List<Address> addresses = geo.getFromLocation(mLocation.getLatitude(), mLocation.getLongitude(), 1);
            if (!ListenerUtil.mutListener.listen(12103)) {
                if ((ListenerUtil.mutListener.listen(12085) ? (!addresses.isEmpty() || (ListenerUtil.mutListener.listen(12084) ? (addresses.size() >= 0) : (ListenerUtil.mutListener.listen(12083) ? (addresses.size() <= 0) : (ListenerUtil.mutListener.listen(12082) ? (addresses.size() < 0) : (ListenerUtil.mutListener.listen(12081) ? (addresses.size() != 0) : (ListenerUtil.mutListener.listen(12080) ? (addresses.size() == 0) : (addresses.size() > 0))))))) : (!addresses.isEmpty() && (ListenerUtil.mutListener.listen(12084) ? (addresses.size() >= 0) : (ListenerUtil.mutListener.listen(12083) ? (addresses.size() <= 0) : (ListenerUtil.mutListener.listen(12082) ? (addresses.size() < 0) : (ListenerUtil.mutListener.listen(12081) ? (addresses.size() != 0) : (ListenerUtil.mutListener.listen(12080) ? (addresses.size() == 0) : (addresses.size() > 0))))))))) {
                    StringBuilder sb = new StringBuilder();
                    int addressLine = addresses.get(0).getMaxAddressLineIndex();
                    if (!ListenerUtil.mutListener.listen(12096)) {
                        {
                            long _loopCounter171 = 0;
                            for (int i = 0; (ListenerUtil.mutListener.listen(12095) ? (i >= (ListenerUtil.mutListener.listen(12090) ? (addressLine % 1) : (ListenerUtil.mutListener.listen(12089) ? (addressLine / 1) : (ListenerUtil.mutListener.listen(12088) ? (addressLine * 1) : (ListenerUtil.mutListener.listen(12087) ? (addressLine + 1) : (addressLine - 1)))))) : (ListenerUtil.mutListener.listen(12094) ? (i <= (ListenerUtil.mutListener.listen(12090) ? (addressLine % 1) : (ListenerUtil.mutListener.listen(12089) ? (addressLine / 1) : (ListenerUtil.mutListener.listen(12088) ? (addressLine * 1) : (ListenerUtil.mutListener.listen(12087) ? (addressLine + 1) : (addressLine - 1)))))) : (ListenerUtil.mutListener.listen(12093) ? (i > (ListenerUtil.mutListener.listen(12090) ? (addressLine % 1) : (ListenerUtil.mutListener.listen(12089) ? (addressLine / 1) : (ListenerUtil.mutListener.listen(12088) ? (addressLine * 1) : (ListenerUtil.mutListener.listen(12087) ? (addressLine + 1) : (addressLine - 1)))))) : (ListenerUtil.mutListener.listen(12092) ? (i != (ListenerUtil.mutListener.listen(12090) ? (addressLine % 1) : (ListenerUtil.mutListener.listen(12089) ? (addressLine / 1) : (ListenerUtil.mutListener.listen(12088) ? (addressLine * 1) : (ListenerUtil.mutListener.listen(12087) ? (addressLine + 1) : (addressLine - 1)))))) : (ListenerUtil.mutListener.listen(12091) ? (i == (ListenerUtil.mutListener.listen(12090) ? (addressLine % 1) : (ListenerUtil.mutListener.listen(12089) ? (addressLine / 1) : (ListenerUtil.mutListener.listen(12088) ? (addressLine * 1) : (ListenerUtil.mutListener.listen(12087) ? (addressLine + 1) : (addressLine - 1)))))) : (i < (ListenerUtil.mutListener.listen(12090) ? (addressLine % 1) : (ListenerUtil.mutListener.listen(12089) ? (addressLine / 1) : (ListenerUtil.mutListener.listen(12088) ? (addressLine * 1) : (ListenerUtil.mutListener.listen(12087) ? (addressLine + 1) : (addressLine - 1))))))))))); i++) {
                                ListenerUtil.loopListener.listen("_loopCounter171", ++_loopCounter171);
                                if (!ListenerUtil.mutListener.listen(12086)) {
                                    sb.append(addresses.get(0).getAddressLine(i)).append(", ");
                                }
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(12101)) {
                        sb.append(addresses.get(0).getAddressLine((ListenerUtil.mutListener.listen(12100) ? (addressLine % 1) : (ListenerUtil.mutListener.listen(12099) ? (addressLine / 1) : (ListenerUtil.mutListener.listen(12098) ? (addressLine * 1) : (ListenerUtil.mutListener.listen(12097) ? (addressLine + 1) : (addressLine - 1))))))).append(".");
                    }
                    if (!ListenerUtil.mutListener.listen(12102)) {
                        address = sb.toString();
                    }
                }
            }
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(12079)) {
                e.printStackTrace();
            }
        }
        return address;
    }

    @Override
    protected void onPostExecute(String s) {
        if (!ListenerUtil.mutListener.listen(12104)) {
            mCallback.onGeocoderTaskCompleted(s);
        }
    }
}
