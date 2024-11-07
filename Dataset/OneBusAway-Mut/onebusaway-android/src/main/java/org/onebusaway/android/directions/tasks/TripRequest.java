/*
 * Copyright 2011 Marcy Gordon
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package org.onebusaway.android.directions.tasks;

import android.os.AsyncTask;
import android.util.Log;
import org.onebusaway.android.app.Application;
import org.onebusaway.android.directions.util.JacksonConfig;
import org.opentripplanner.api.model.TripPlan;
import org.opentripplanner.api.ws.Message;
import org.opentripplanner.api.ws.Request;
import org.opentripplanner.api.ws.Response;
import org.opentripplanner.routing.core.TraverseMode;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class TripRequest extends AsyncTask<Request, Integer, Long> {

    public interface Callback {

        void onTripRequestComplete(TripPlan tripPlan, String url);

        void onTripRequestFailure(int errorCode, String url);
    }

    public static int NO_SERVER_SELECTED = 1000;

    // Constants that are defined in OTPApp in CUTR OTP Android app
    private static final String TAG = "TripRequest";

    private static final String FOLDER_STRUCTURE_PREFIX_NEW = "/routers/default";

    public static final String OTP_RENTAL_QUALIFIER = "_RENT";

    public static final String PLAN_LOCATION = "/plan";

    public static final int HTTP_CONNECTION_TIMEOUT = 15000;

    public static final int HTTP_SOCKET_TIMEOUT = 15000;

    private Response mResponse;

    private String mBaseUrl;

    private String mRequestUrl;

    private Callback mCallback;

    // change Server object to baseUrl string.
    public TripRequest(String baseUrl, Callback callback) {
        if (!ListenerUtil.mutListener.listen(5260)) {
            mBaseUrl = baseUrl;
        }
        if (!ListenerUtil.mutListener.listen(5261)) {
            mCallback = callback;
        }
    }

    /**
     * Show the progress dialog for this request. Called when request starts, or by caller activity
     * (i.e., in onCreate() after a rotation)
     * @param reqs
     * @return
     */
    protected Long doInBackground(Request... reqs) {
        long totalSize = 0;
        if (!ListenerUtil.mutListener.listen(5265)) {
            if (mBaseUrl == null) {
                if (!ListenerUtil.mutListener.listen(5264)) {
                    mCallback.onTripRequestFailure(NO_SERVER_SELECTED, null);
                }
                return null;
            } else {
                String prefix = FOLDER_STRUCTURE_PREFIX_NEW;
                boolean useOldUrlVersion = Application.get().getUseOldOtpApiUrlVersion();
                if (!ListenerUtil.mutListener.listen(5263)) {
                    {
                        long _loopCounter44 = 0;
                        for (Request req : reqs) {
                            ListenerUtil.loopListener.listen("_loopCounter44", ++_loopCounter44);
                            if (!ListenerUtil.mutListener.listen(5262)) {
                                mResponse = requestPlan(req, prefix, mBaseUrl, useOldUrlVersion);
                            }
                        }
                    }
                }
            }
        }
        return totalSize;
    }

    protected void onCancelled(Long result) {
        if (!ListenerUtil.mutListener.listen(5266)) {
            mCallback.onTripRequestFailure(Message.REQUEST_TIMEOUT.getId(), mRequestUrl);
        }
    }

    protected void onPostExecute(Long result) {
        if (!ListenerUtil.mutListener.listen(5267)) {
            if (result == null) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(5276)) {
            if ((ListenerUtil.mutListener.listen(5269) ? ((ListenerUtil.mutListener.listen(5268) ? (mResponse != null || mResponse.getPlan() != null) : (mResponse != null && mResponse.getPlan() != null)) || mResponse.getPlan().getItinerary().get(0) != null) : ((ListenerUtil.mutListener.listen(5268) ? (mResponse != null || mResponse.getPlan() != null) : (mResponse != null && mResponse.getPlan() != null)) && mResponse.getPlan().getItinerary().get(0) != null))) {
                if (!ListenerUtil.mutListener.listen(5275)) {
                    mCallback.onTripRequestComplete(mResponse.getPlan(), mRequestUrl);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(5270)) {
                    Log.e(TAG, "Error retrieving routing from OTP server: " + mResponse);
                }
                int errorCode = -1;
                if (!ListenerUtil.mutListener.listen(5273)) {
                    if ((ListenerUtil.mutListener.listen(5271) ? (mResponse != null || mResponse.getError() != null) : (mResponse != null && mResponse.getError() != null))) {
                        if (!ListenerUtil.mutListener.listen(5272)) {
                            errorCode = mResponse.getError().getId();
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(5274)) {
                    mCallback.onTripRequestFailure(errorCode, mRequestUrl);
                }
            }
        }
    }

    protected Response requestPlan(Request requestParams, String prefix, String baseURL, boolean useOldUrlStructure) {
        HashMap<String, String> tmp = requestParams.getParameters();
        Collection c = tmp.entrySet();
        Iterator itr = c.iterator();
        String params = "";
        boolean first = true;
        if (!ListenerUtil.mutListener.listen(5281)) {
            {
                long _loopCounter45 = 0;
                while (itr.hasNext()) {
                    ListenerUtil.loopListener.listen("_loopCounter45", ++_loopCounter45);
                    if (!ListenerUtil.mutListener.listen(5280)) {
                        if (first) {
                            if (!ListenerUtil.mutListener.listen(5278)) {
                                params += "?" + itr.next();
                            }
                            if (!ListenerUtil.mutListener.listen(5279)) {
                                first = false;
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(5277)) {
                                params += "&" + itr.next();
                            }
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(5283)) {
            if (requestParams.getBikeRental()) {
                String updatedString;
                if (prefix.equals(FOLDER_STRUCTURE_PREFIX_NEW)) {
                    updatedString = params.replace(TraverseMode.BICYCLE.toString(), TraverseMode.BICYCLE.toString() + OTP_RENTAL_QUALIFIER);
                } else {
                    updatedString = params.replace(TraverseMode.BICYCLE.toString(), TraverseMode.BICYCLE.toString() + ", " + TraverseMode.WALK.toString());
                }
                if (!ListenerUtil.mutListener.listen(5282)) {
                    params = updatedString;
                }
            }
        }
        String u;
        if (!useOldUrlStructure) {
            u = baseURL + prefix + PLAN_LOCATION + params;
        } else {
            u = baseURL + PLAN_LOCATION + params;
        }
        if (!ListenerUtil.mutListener.listen(5284)) {
            // Save url for error reporting purposes
            mRequestUrl = u;
        }
        if (!ListenerUtil.mutListener.listen(5285)) {
            Log.d(TAG, "URL: " + u);
        }
        HttpURLConnection urlConnection = null;
        URL url;
        Response plan = null;
        try {
            url = new URL(u);
            if (!ListenerUtil.mutListener.listen(5301)) {
                urlConnection = (HttpURLConnection) url.openConnection();
            }
            if (!ListenerUtil.mutListener.listen(5302)) {
                urlConnection.setConnectTimeout(HTTP_CONNECTION_TIMEOUT);
            }
            if (!ListenerUtil.mutListener.listen(5303)) {
                urlConnection.setReadTimeout(HTTP_SOCKET_TIMEOUT);
            }
            if (!ListenerUtil.mutListener.listen(5304)) {
                plan = JacksonConfig.getObjectReaderInstance().readValue(urlConnection.getInputStream());
            }
            if (!ListenerUtil.mutListener.listen(5306)) {
                if (useOldUrlStructure) {
                    if (!ListenerUtil.mutListener.listen(5305)) {
                        // If the old url structure is successful then cache it
                        Application.get().setUseOldOtpApiUrlVersion(true);
                    }
                }
            }
        } catch (java.net.SocketTimeoutException e) {
            if (!ListenerUtil.mutListener.listen(5286)) {
                Log.e(TAG, "Timeout fetching JSON or XML: " + e);
            }
            if (!ListenerUtil.mutListener.listen(5287)) {
                e.printStackTrace();
            }
            if (!ListenerUtil.mutListener.listen(5288)) {
                cancel(true);
            }
        } catch (FileNotFoundException e) {
            if (!ListenerUtil.mutListener.listen(5295)) {
                if (!useOldUrlStructure) {
                    if (!ListenerUtil.mutListener.listen(5292)) {
                        Log.v(TAG, "The OTP url might be old, trying  old url structure");
                    }
                    if (!ListenerUtil.mutListener.listen(5294)) {
                        if (urlConnection != null) {
                            if (!ListenerUtil.mutListener.listen(5293)) {
                                urlConnection.disconnect();
                            }
                        }
                    }
                    return requestPlan(requestParams, prefix, baseURL, true);
                } else {
                    if (!ListenerUtil.mutListener.listen(5289)) {
                        Log.e(TAG, "Error fetching JSON or XML: " + e);
                    }
                    if (!ListenerUtil.mutListener.listen(5290)) {
                        e.printStackTrace();
                    }
                    if (!ListenerUtil.mutListener.listen(5291)) {
                        cancel(true);
                    }
                }
            }
        } catch (IOException e) {
            if (!ListenerUtil.mutListener.listen(5296)) {
                Log.e(TAG, "Error fetching JSON or XML: " + e);
            }
            if (!ListenerUtil.mutListener.listen(5297)) {
                e.printStackTrace();
            }
            if (!ListenerUtil.mutListener.listen(5298)) {
                cancel(true);
            }
        } finally {
            if (!ListenerUtil.mutListener.listen(5300)) {
                if (urlConnection != null) {
                    if (!ListenerUtil.mutListener.listen(5299)) {
                        urlConnection.disconnect();
                    }
                }
            }
        }
        return plan;
    }
}
