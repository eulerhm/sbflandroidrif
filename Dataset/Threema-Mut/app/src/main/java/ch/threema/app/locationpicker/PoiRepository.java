/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
 * Copyright (c) 2019-2021 Threema GmbH
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License, version 3,
 * as published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program. If not, see <https://www.gnu.org/licenses/>.
 */
package ch.threema.app.locationpicker;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.os.AsyncTask;
import android.widget.Toast;
import com.mapbox.mapboxsdk.geometry.LatLng;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.net.ssl.HttpsURLConnection;
import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import ch.threema.app.ThreemaApplication;
import ch.threema.app.managers.ServiceManager;
import ch.threema.app.services.PreferenceService;
import ch.threema.app.utils.ConfigUtils;
import ch.threema.app.utils.LocationUtil;
import ch.threema.app.utils.RuntimeUtil;
import ch.threema.app.utils.TestUtil;
import ch.threema.client.ProtocolStrings;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

class PoiRepository {

    private static final Logger logger = LoggerFactory.getLogger(PoiRepository.class);

    public static final int QUERY_MIN_LENGTH = 3;

    private List<Poi> places = new ArrayList<>();

    private final MutableLiveData<List<Poi>> mutableLiveData = new MutableLiveData<>();

    private final MutableLiveData<Boolean> isLoading = new MutableLiveData<>();

    @SuppressLint("StaticFieldLeak")
    MutableLiveData<List<Poi>> getMutableLiveData(PoiQuery poiQuery) {
        if (!ListenerUtil.mutListener.listen(29066)) {
            logger.debug("getMutableLiveData");
        }
        if (!ListenerUtil.mutListener.listen(29116)) {
            new AsyncTask<Void, Void, Void>() {

                @Override
                protected void onPreExecute() {
                    if (!ListenerUtil.mutListener.listen(29067)) {
                        isLoading.setValue(true);
                    }
                }

                @Override
                protected Void doInBackground(Void... voids) {
                    if (!ListenerUtil.mutListener.listen(29068)) {
                        places.clear();
                    }
                    if (!ListenerUtil.mutListener.listen(29070)) {
                        if ((ListenerUtil.mutListener.listen(29069) ? (TestUtil.empty(poiQuery.getQuery()) && poiQuery.getCenter() == null) : (TestUtil.empty(poiQuery.getQuery()) || poiQuery.getCenter() == null))) {
                            return null;
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(29072)) {
                        if ((ListenerUtil.mutListener.listen(29071) ? (poiQuery.getCenter().getLatitude() == 0.0d || poiQuery.getCenter().getLongitude() == 0.0d) : (poiQuery.getCenter().getLatitude() == 0.0d && poiQuery.getCenter().getLongitude() == 0.0d))) {
                            return null;
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(29078)) {
                        if ((ListenerUtil.mutListener.listen(29077) ? (poiQuery.getQuery().length() >= QUERY_MIN_LENGTH) : (ListenerUtil.mutListener.listen(29076) ? (poiQuery.getQuery().length() <= QUERY_MIN_LENGTH) : (ListenerUtil.mutListener.listen(29075) ? (poiQuery.getQuery().length() > QUERY_MIN_LENGTH) : (ListenerUtil.mutListener.listen(29074) ? (poiQuery.getQuery().length() != QUERY_MIN_LENGTH) : (ListenerUtil.mutListener.listen(29073) ? (poiQuery.getQuery().length() == QUERY_MIN_LENGTH) : (poiQuery.getQuery().length() < QUERY_MIN_LENGTH))))))) {
                            return null;
                        }
                    }
                    final ServiceManager serviceManager = ThreemaApplication.getServiceManager();
                    if (!ListenerUtil.mutListener.listen(29080)) {
                        if (serviceManager == null) {
                            if (!ListenerUtil.mutListener.listen(29079)) {
                                logger.error("Could not obtain service manager");
                            }
                            return null;
                        }
                    }
                    final PreferenceService preferenceService = serviceManager.getPreferenceService();
                    if (!ListenerUtil.mutListener.listen(29082)) {
                        if (preferenceService == null) {
                            if (!ListenerUtil.mutListener.listen(29081)) {
                                logger.error("Could not obtain preference service");
                            }
                            return null;
                        }
                    }
                    try {
                        if (!ListenerUtil.mutListener.listen(29113)) {
                            if (!"".equals(poiQuery.getQuery())) {
                                final String placesUrl = LocationUtil.getPlacesUrl(preferenceService);
                                URL serverUrl = new URL(String.format(Locale.US, placesUrl, poiQuery.getCenter().getLatitude(), poiQuery.getCenter().getLongitude(), Uri.encode(poiQuery.getQuery())));
                                if (!ListenerUtil.mutListener.listen(29084)) {
                                    logger.debug("query: " + serverUrl.toString());
                                }
                                HttpsURLConnection urlConnection = null;
                                try {
                                    if (!ListenerUtil.mutListener.listen(29087)) {
                                        urlConnection = (HttpsURLConnection) serverUrl.openConnection();
                                    }
                                    if (!ListenerUtil.mutListener.listen(29088)) {
                                        urlConnection.setSSLSocketFactory(ConfigUtils.getSSLSocketFactory(serverUrl.getHost()));
                                    }
                                    if (!ListenerUtil.mutListener.listen(29089)) {
                                        urlConnection.setConnectTimeout(15000);
                                    }
                                    if (!ListenerUtil.mutListener.listen(29090)) {
                                        urlConnection.setReadTimeout(30000);
                                    }
                                    if (!ListenerUtil.mutListener.listen(29091)) {
                                        urlConnection.setRequestProperty("User-Agent", ProtocolStrings.USER_AGENT);
                                    }
                                    if (!ListenerUtil.mutListener.listen(29092)) {
                                        urlConnection.setRequestMethod("GET");
                                    }
                                    if (!ListenerUtil.mutListener.listen(29093)) {
                                        urlConnection.setDoOutput(false);
                                    }
                                    int responseCode = urlConnection.getResponseCode();
                                    if (!ListenerUtil.mutListener.listen(29112)) {
                                        if (responseCode == HttpsURLConnection.HTTP_OK) {
                                            StringBuilder sb = new StringBuilder();
                                            try (BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()))) {
                                                String line;
                                                if (!ListenerUtil.mutListener.listen(29109)) {
                                                    {
                                                        long _loopCounter187 = 0;
                                                        while ((line = br.readLine()) != null) {
                                                            ListenerUtil.loopListener.listen("_loopCounter187", ++_loopCounter187);
                                                            if (!ListenerUtil.mutListener.listen(29108)) {
                                                                sb.append(line).append("\n");
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                            try {
                                                if (!ListenerUtil.mutListener.listen(29111)) {
                                                    parseJson(sb.toString());
                                                }
                                            } catch (JSONException e) {
                                                if (!ListenerUtil.mutListener.listen(29110)) {
                                                    logger.error("Exception", e);
                                                }
                                            }
                                        } else if ((ListenerUtil.mutListener.listen(29104) ? ((ListenerUtil.mutListener.listen(29098) ? (responseCode >= 400) : (ListenerUtil.mutListener.listen(29097) ? (responseCode <= 400) : (ListenerUtil.mutListener.listen(29096) ? (responseCode > 400) : (ListenerUtil.mutListener.listen(29095) ? (responseCode < 400) : (ListenerUtil.mutListener.listen(29094) ? (responseCode == 400) : (responseCode != 400)))))) || (ListenerUtil.mutListener.listen(29103) ? (responseCode >= 504) : (ListenerUtil.mutListener.listen(29102) ? (responseCode <= 504) : (ListenerUtil.mutListener.listen(29101) ? (responseCode > 504) : (ListenerUtil.mutListener.listen(29100) ? (responseCode < 504) : (ListenerUtil.mutListener.listen(29099) ? (responseCode == 504) : (responseCode != 504))))))) : ((ListenerUtil.mutListener.listen(29098) ? (responseCode >= 400) : (ListenerUtil.mutListener.listen(29097) ? (responseCode <= 400) : (ListenerUtil.mutListener.listen(29096) ? (responseCode > 400) : (ListenerUtil.mutListener.listen(29095) ? (responseCode < 400) : (ListenerUtil.mutListener.listen(29094) ? (responseCode == 400) : (responseCode != 400)))))) && (ListenerUtil.mutListener.listen(29103) ? (responseCode >= 504) : (ListenerUtil.mutListener.listen(29102) ? (responseCode <= 504) : (ListenerUtil.mutListener.listen(29101) ? (responseCode > 504) : (ListenerUtil.mutListener.listen(29100) ? (responseCode < 504) : (ListenerUtil.mutListener.listen(29099) ? (responseCode == 504) : (responseCode != 504))))))))) {
                                            if (!ListenerUtil.mutListener.listen(29107)) {
                                                // Server returns a 400 if string is too short
                                                RuntimeUtil.runOnUiThread(new Runnable() {

                                                    @Override
                                                    public void run() {
                                                        if (!ListenerUtil.mutListener.listen(29106)) {
                                                            Toast.makeText(ThreemaApplication.getAppContext(), "Server Error: " + responseCode, Toast.LENGTH_SHORT).show();
                                                        }
                                                    }
                                                });
                                            }
                                        } else {
                                            if (!ListenerUtil.mutListener.listen(29105)) {
                                                logger.info("Unable to fetch POI names: " + urlConnection.getResponseMessage());
                                            }
                                        }
                                    }
                                } finally {
                                    if (!ListenerUtil.mutListener.listen(29086)) {
                                        if (urlConnection != null) {
                                            if (!ListenerUtil.mutListener.listen(29085)) {
                                                urlConnection.disconnect();
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    } catch (IOException e) {
                        if (!ListenerUtil.mutListener.listen(29083)) {
                            logger.error("Exception", e);
                        }
                    }
                    return null;
                }

                @Override
                protected void onPostExecute(Void aVoid) {
                    if (!ListenerUtil.mutListener.listen(29114)) {
                        isLoading.setValue(false);
                    }
                    if (!ListenerUtil.mutListener.listen(29115)) {
                        mutableLiveData.setValue(places);
                    }
                }
            }.execute();
        }
        return mutableLiveData;
    }

    MutableLiveData<Boolean> getIsLoading() {
        return isLoading;
    }

    private void parseJson(@NonNull String json) throws JSONException {
        JSONArray jsonArray = new JSONArray(json);
        if (!ListenerUtil.mutListener.listen(29138)) {
            if ((ListenerUtil.mutListener.listen(29121) ? (jsonArray.length() >= 0) : (ListenerUtil.mutListener.listen(29120) ? (jsonArray.length() <= 0) : (ListenerUtil.mutListener.listen(29119) ? (jsonArray.length() < 0) : (ListenerUtil.mutListener.listen(29118) ? (jsonArray.length() != 0) : (ListenerUtil.mutListener.listen(29117) ? (jsonArray.length() == 0) : (jsonArray.length() > 0))))))) {
                if (!ListenerUtil.mutListener.listen(29137)) {
                    {
                        long _loopCounter188 = 0;
                        for (int i = 0; (ListenerUtil.mutListener.listen(29136) ? (i >= jsonArray.length()) : (ListenerUtil.mutListener.listen(29135) ? (i <= jsonArray.length()) : (ListenerUtil.mutListener.listen(29134) ? (i > jsonArray.length()) : (ListenerUtil.mutListener.listen(29133) ? (i != jsonArray.length()) : (ListenerUtil.mutListener.listen(29132) ? (i == jsonArray.length()) : (i < jsonArray.length())))))); i++) {
                            ListenerUtil.loopListener.listen("_loopCounter188", ++_loopCounter188);
                            JSONObject result = jsonArray.getJSONObject(i);
                            if (!ListenerUtil.mutListener.listen(29131)) {
                                if (result != null) {
                                    Poi place = new Poi();
                                    double lat = result.getDouble("lat");
                                    double lng = result.getDouble("lon");
                                    if (!ListenerUtil.mutListener.listen(29122)) {
                                        place.setLatLng(new LatLng(lat, lng));
                                    }
                                    if (!ListenerUtil.mutListener.listen(29123)) {
                                        place.setName(result.getString("name"));
                                    }
                                    String placeS = result.optString("place");
                                    String highway = result.optString("highway");
                                    if (!ListenerUtil.mutListener.listen(29126)) {
                                        if (result.has("dist")) {
                                            if (!ListenerUtil.mutListener.listen(29125)) {
                                                place.setDistance(result.getInt("dist"));
                                            }
                                        } else {
                                            if (!ListenerUtil.mutListener.listen(29124)) {
                                                place.setDistance(-1);
                                            }
                                        }
                                    }
                                    if (!ListenerUtil.mutListener.listen(29129)) {
                                        if (!TestUtil.empty(highway)) {
                                            if (!ListenerUtil.mutListener.listen(29128)) {
                                                place.setDescription("street");
                                            }
                                        } else {
                                            if (!ListenerUtil.mutListener.listen(29127)) {
                                                place.setDescription(placeS);
                                            }
                                        }
                                    }
                                    if (!ListenerUtil.mutListener.listen(29130)) {
                                        places.add(place);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
