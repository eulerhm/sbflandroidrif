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
import java.util.List;
import java.util.Locale;
import javax.net.ssl.HttpsURLConnection;
import androidx.annotation.NonNull;
import androidx.annotation.WorkerThread;
import ch.threema.app.services.PreferenceService;
import ch.threema.app.utils.ConfigUtils;
import ch.threema.app.utils.LocationUtil;
import ch.threema.client.ProtocolStrings;
import static ch.threema.app.locationpicker.LocationPickerActivity.POI_RADIUS;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class NearbyPoiUtil {

    private static final Logger logger = LoggerFactory.getLogger(NearbyPoiUtil.class);

    /**
     *  Fetch POIs around the specified location. Append them to the list `pois`.
     */
    @WorkerThread
    public static void getPOIs(@NonNull LatLng center, @NonNull List<Poi> pois, int maxCount, @NonNull PreferenceService preferenceService) {
        long startTime = System.currentTimeMillis();
        try {
            final String poiUrl = LocationUtil.getPoiUrl(preferenceService);
            URL serverUrl = new URL(String.format(Locale.US, poiUrl, center.getLatitude(), center.getLongitude(), POI_RADIUS));
            if (!ListenerUtil.mutListener.listen(28923)) {
                if ((ListenerUtil.mutListener.listen(28921) ? (center.getLatitude() == 0.0d || center.getLongitude() == 0.0d) : (center.getLatitude() == 0.0d && center.getLongitude() == 0.0d))) {
                    if (!ListenerUtil.mutListener.listen(28922)) {
                        logger.debug("ignoring POI fetch request for 0/0");
                    }
                    return;
                }
            }
            if (!ListenerUtil.mutListener.listen(28924)) {
                logger.debug("getting POIs for " + serverUrl.toString());
            }
            HttpsURLConnection urlConnection = null;
            try {
                if (!ListenerUtil.mutListener.listen(28927)) {
                    urlConnection = (HttpsURLConnection) serverUrl.openConnection();
                }
                if (!ListenerUtil.mutListener.listen(28928)) {
                    urlConnection.setSSLSocketFactory(ConfigUtils.getSSLSocketFactory(serverUrl.getHost()));
                }
                if (!ListenerUtil.mutListener.listen(28929)) {
                    urlConnection.setConnectTimeout(15000);
                }
                if (!ListenerUtil.mutListener.listen(28930)) {
                    urlConnection.setReadTimeout(30000);
                }
                if (!ListenerUtil.mutListener.listen(28931)) {
                    urlConnection.setRequestMethod("GET");
                }
                if (!ListenerUtil.mutListener.listen(28932)) {
                    urlConnection.setRequestProperty("User-Agent", ProtocolStrings.USER_AGENT);
                }
                if (!ListenerUtil.mutListener.listen(28933)) {
                    urlConnection.setDoOutput(false);
                }
                int responseCode = urlConnection.getResponseCode();
                if (!ListenerUtil.mutListener.listen(28948)) {
                    if ((ListenerUtil.mutListener.listen(28938) ? (responseCode >= HttpsURLConnection.HTTP_OK) : (ListenerUtil.mutListener.listen(28937) ? (responseCode <= HttpsURLConnection.HTTP_OK) : (ListenerUtil.mutListener.listen(28936) ? (responseCode > HttpsURLConnection.HTTP_OK) : (ListenerUtil.mutListener.listen(28935) ? (responseCode < HttpsURLConnection.HTTP_OK) : (ListenerUtil.mutListener.listen(28934) ? (responseCode != HttpsURLConnection.HTTP_OK) : (responseCode == HttpsURLConnection.HTTP_OK))))))) {
                        StringBuilder sb = new StringBuilder();
                        try (BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()))) {
                            String line;
                            if (!ListenerUtil.mutListener.listen(28940)) {
                                {
                                    long _loopCounter185 = 0;
                                    while ((line = br.readLine()) != null) {
                                        ListenerUtil.loopListener.listen("_loopCounter185", ++_loopCounter185);
                                        if (!ListenerUtil.mutListener.listen(28939)) {
                                            sb.append(line).append("\n");
                                        }
                                    }
                                }
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(28945)) {
                            logger.debug("*** retrieving POIs took " + ((ListenerUtil.mutListener.listen(28944) ? (System.currentTimeMillis() % startTime) : (ListenerUtil.mutListener.listen(28943) ? (System.currentTimeMillis() / startTime) : (ListenerUtil.mutListener.listen(28942) ? (System.currentTimeMillis() * startTime) : (ListenerUtil.mutListener.listen(28941) ? (System.currentTimeMillis() + startTime) : (System.currentTimeMillis() - startTime)))))) + " ms");
                        }
                        try {
                            if (!ListenerUtil.mutListener.listen(28947)) {
                                parseJson(sb.toString(), pois, maxCount);
                            }
                        } catch (JSONException e) {
                            if (!ListenerUtil.mutListener.listen(28946)) {
                                logger.error("Exception", e);
                            }
                        }
                    }
                }
            } finally {
                if (!ListenerUtil.mutListener.listen(28926)) {
                    if (urlConnection != null) {
                        if (!ListenerUtil.mutListener.listen(28925)) {
                            urlConnection.disconnect();
                        }
                    }
                }
            }
        } catch (IOException e) {
            if (!ListenerUtil.mutListener.listen(28920)) {
                logger.error("Exception", e);
            }
        }
    }

    private static void parseJson(@NonNull String json, List<Poi> pois, int maxCount) throws JSONException {
        JSONArray results = new JSONArray(json);
        if (!ListenerUtil.mutListener.listen(28949)) {
            logger.debug("*** # of results: " + results.length());
        }
        if (!ListenerUtil.mutListener.listen(29042)) {
            if ((ListenerUtil.mutListener.listen(28954) ? (results.length() >= 0) : (ListenerUtil.mutListener.listen(28953) ? (results.length() <= 0) : (ListenerUtil.mutListener.listen(28952) ? (results.length() < 0) : (ListenerUtil.mutListener.listen(28951) ? (results.length() != 0) : (ListenerUtil.mutListener.listen(28950) ? (results.length() == 0) : (results.length() > 0))))))) {
                if (!ListenerUtil.mutListener.listen(29041)) {
                    {
                        long _loopCounter186 = 0;
                        for (int i = 0; (ListenerUtil.mutListener.listen(29040) ? ((ListenerUtil.mutListener.listen(29034) ? (i >= results.length()) : (ListenerUtil.mutListener.listen(29033) ? (i <= results.length()) : (ListenerUtil.mutListener.listen(29032) ? (i > results.length()) : (ListenerUtil.mutListener.listen(29031) ? (i != results.length()) : (ListenerUtil.mutListener.listen(29030) ? (i == results.length()) : (i < results.length())))))) || (ListenerUtil.mutListener.listen(29039) ? (i >= maxCount) : (ListenerUtil.mutListener.listen(29038) ? (i <= maxCount) : (ListenerUtil.mutListener.listen(29037) ? (i > maxCount) : (ListenerUtil.mutListener.listen(29036) ? (i != maxCount) : (ListenerUtil.mutListener.listen(29035) ? (i == maxCount) : (i < maxCount))))))) : ((ListenerUtil.mutListener.listen(29034) ? (i >= results.length()) : (ListenerUtil.mutListener.listen(29033) ? (i <= results.length()) : (ListenerUtil.mutListener.listen(29032) ? (i > results.length()) : (ListenerUtil.mutListener.listen(29031) ? (i != results.length()) : (ListenerUtil.mutListener.listen(29030) ? (i == results.length()) : (i < results.length())))))) && (ListenerUtil.mutListener.listen(29039) ? (i >= maxCount) : (ListenerUtil.mutListener.listen(29038) ? (i <= maxCount) : (ListenerUtil.mutListener.listen(29037) ? (i > maxCount) : (ListenerUtil.mutListener.listen(29036) ? (i != maxCount) : (ListenerUtil.mutListener.listen(29035) ? (i == maxCount) : (i < maxCount)))))))); i++) {
                            ListenerUtil.loopListener.listen("_loopCounter186", ++_loopCounter186);
                            JSONObject result = results.getJSONObject(i);
                            if (!ListenerUtil.mutListener.listen(29029)) {
                                if (result != null) {
                                    String name = result.optString("name", null);
                                    if (!ListenerUtil.mutListener.listen(29028)) {
                                        if (name != null) {
                                            if (!ListenerUtil.mutListener.listen(28956)) {
                                                if ((ListenerUtil.mutListener.listen(28955) ? (!result.has("lat") && !result.has("lon")) : (!result.has("lat") || !result.has("lon")))) {
                                                    continue;
                                                }
                                            }
                                            double lat = result.optDouble("lat");
                                            double lng = result.optDouble("lon");
                                            long id = result.optLong("id");
                                            Poi poi = new Poi();
                                            if (!ListenerUtil.mutListener.listen(28957)) {
                                                poi.setName(name);
                                            }
                                            if (!ListenerUtil.mutListener.listen(28958)) {
                                                poi.setId(id);
                                            }
                                            if (!ListenerUtil.mutListener.listen(28959)) {
                                                poi.setLatLng(new LatLng(lat, lng));
                                            }
                                            // possible tags: amenity, shop, tourism, sport, leisure, natural, public_transport
                                            String type = result.optString("amenity", null);
                                            if (!ListenerUtil.mutListener.listen(28990)) {
                                                if (type == null) {
                                                    if (!ListenerUtil.mutListener.listen(28961)) {
                                                        type = result.optString("shop", null);
                                                    }
                                                    if (!ListenerUtil.mutListener.listen(28989)) {
                                                        if (type == null) {
                                                            if (!ListenerUtil.mutListener.listen(28963)) {
                                                                type = result.optString("tourism", null);
                                                            }
                                                            if (!ListenerUtil.mutListener.listen(28988)) {
                                                                if (type == null) {
                                                                    if (!ListenerUtil.mutListener.listen(28965)) {
                                                                        type = result.optString("sport", null);
                                                                    }
                                                                    if (!ListenerUtil.mutListener.listen(28987)) {
                                                                        if (type == null) {
                                                                            if (!ListenerUtil.mutListener.listen(28967)) {
                                                                                type = result.optString("leisure", null);
                                                                            }
                                                                            if (!ListenerUtil.mutListener.listen(28986)) {
                                                                                if (type == null) {
                                                                                    if (!ListenerUtil.mutListener.listen(28969)) {
                                                                                        type = result.optString("natural", null);
                                                                                    }
                                                                                    if (!ListenerUtil.mutListener.listen(28985)) {
                                                                                        if (type == null) {
                                                                                            if (!ListenerUtil.mutListener.listen(28971)) {
                                                                                                type = result.optString("public_transport", null);
                                                                                            }
                                                                                            if (!ListenerUtil.mutListener.listen(28984)) {
                                                                                                if (type == null) {
                                                                                                    if (!ListenerUtil.mutListener.listen(28973)) {
                                                                                                        type = result.optString("aeroway", null);
                                                                                                    }
                                                                                                    if (!ListenerUtil.mutListener.listen(28983)) {
                                                                                                        if (type == null) {
                                                                                                            if (!ListenerUtil.mutListener.listen(28976)) {
                                                                                                                type = result.optString("aerialway", null);
                                                                                                            }
                                                                                                            if (!ListenerUtil.mutListener.listen(28982)) {
                                                                                                                if (type == null) {
                                                                                                                    if (!ListenerUtil.mutListener.listen(28979)) {
                                                                                                                        type = result.optString("highway", null);
                                                                                                                    }
                                                                                                                    if (!ListenerUtil.mutListener.listen(28981)) {
                                                                                                                        if (type != null) {
                                                                                                                            if (!ListenerUtil.mutListener.listen(28980)) {
                                                                                                                                poi.setCategory("highway");
                                                                                                                            }
                                                                                                                        }
                                                                                                                    }
                                                                                                                } else {
                                                                                                                    if (!ListenerUtil.mutListener.listen(28977)) {
                                                                                                                        type = "cablecar";
                                                                                                                    }
                                                                                                                    if (!ListenerUtil.mutListener.listen(28978)) {
                                                                                                                        poi.setCategory("cablecar");
                                                                                                                    }
                                                                                                                }
                                                                                                            }
                                                                                                        } else {
                                                                                                            if (!ListenerUtil.mutListener.listen(28974)) {
                                                                                                                type = "airport";
                                                                                                            }
                                                                                                            if (!ListenerUtil.mutListener.listen(28975)) {
                                                                                                                poi.setCategory("airport");
                                                                                                            }
                                                                                                        }
                                                                                                    }
                                                                                                } else {
                                                                                                    if (!ListenerUtil.mutListener.listen(28972)) {
                                                                                                        poi.setCategory("public_transport");
                                                                                                    }
                                                                                                }
                                                                                            }
                                                                                        } else {
                                                                                            if (!ListenerUtil.mutListener.listen(28970)) {
                                                                                                poi.setCategory("natural");
                                                                                            }
                                                                                        }
                                                                                    }
                                                                                } else {
                                                                                    if (!ListenerUtil.mutListener.listen(28968)) {
                                                                                        poi.setCategory("leisure");
                                                                                    }
                                                                                }
                                                                            }
                                                                        } else {
                                                                            if (!ListenerUtil.mutListener.listen(28966)) {
                                                                                poi.setCategory("sport");
                                                                            }
                                                                        }
                                                                    }
                                                                } else {
                                                                    if (!ListenerUtil.mutListener.listen(28964)) {
                                                                        poi.setCategory("tourism");
                                                                    }
                                                                }
                                                            }
                                                        } else {
                                                            if (!ListenerUtil.mutListener.listen(28962)) {
                                                                poi.setCategory("shop");
                                                            }
                                                        }
                                                    }
                                                } else {
                                                    if (!ListenerUtil.mutListener.listen(28960)) {
                                                        poi.setCategory("amenity");
                                                    }
                                                }
                                            }
                                            if (!ListenerUtil.mutListener.listen(28991)) {
                                                if (type == null) {
                                                    continue;
                                                }
                                            }
                                            if (!ListenerUtil.mutListener.listen(29025)) {
                                                switch(type) {
                                                    case "fast_food":
                                                    case "food_court":
                                                        if (!ListenerUtil.mutListener.listen(28992)) {
                                                            type = "food";
                                                        }
                                                        break;
                                                    case "hairdresser":
                                                        if (!ListenerUtil.mutListener.listen(28993)) {
                                                            type = "hair_care";
                                                        }
                                                        break;
                                                    case "fitness_centre":
                                                        if (!ListenerUtil.mutListener.listen(28994)) {
                                                            type = "gym";
                                                        }
                                                        break;
                                                    case "do_it_yourself":
                                                    case "doityourself":
                                                        if (!ListenerUtil.mutListener.listen(28995)) {
                                                            type = "hardware_store";
                                                        }
                                                        break;
                                                    case "kindergarten":
                                                        if (!ListenerUtil.mutListener.listen(28996)) {
                                                            type = "child_care";
                                                        }
                                                        break;
                                                    case "nursing_home":
                                                    case "clinic":
                                                    case "social_facility":
                                                        if (!ListenerUtil.mutListener.listen(28997)) {
                                                            type = "hospital";
                                                        }
                                                        break;
                                                    case "parking_entrace":
                                                        if (!ListenerUtil.mutListener.listen(28998)) {
                                                            type = "parking";
                                                        }
                                                        break;
                                                    case "playground":
                                                        if (!ListenerUtil.mutListener.listen(28999)) {
                                                            type = "park";
                                                        }
                                                        break;
                                                    case "car_sharing":
                                                        if (!ListenerUtil.mutListener.listen(29000)) {
                                                            type = "car_rental";
                                                        }
                                                        break;
                                                    case "car":
                                                        if (!ListenerUtil.mutListener.listen(29001)) {
                                                            type = "car_dealer";
                                                        }
                                                        break;
                                                    case "hotel":
                                                        if (!ListenerUtil.mutListener.listen(29002)) {
                                                            type = "lodging";
                                                        }
                                                        break;
                                                    case "doctors":
                                                        if (!ListenerUtil.mutListener.listen(29003)) {
                                                            type = "doctor";
                                                        }
                                                        break;
                                                    case "recycling":
                                                        if (!ListenerUtil.mutListener.listen(29004)) {
                                                            type = "establishment";
                                                        }
                                                        break;
                                                    case "mall":
                                                        if (!ListenerUtil.mutListener.listen(29005)) {
                                                            type = "shopping_mall";
                                                        }
                                                        break;
                                                    case "swimming_pool":
                                                    case "water_park":
                                                        if (!ListenerUtil.mutListener.listen(29006)) {
                                                            type = "swimming";
                                                        }
                                                        break;
                                                    case "arts_centre":
                                                        if (!ListenerUtil.mutListener.listen(29007)) {
                                                            type = "gallery";
                                                        }
                                                        break;
                                                    case "dry_cleaning":
                                                        if (!ListenerUtil.mutListener.listen(29008)) {
                                                            type = "laundry";
                                                        }
                                                        break;
                                                    case "second_hand":
                                                        if (!ListenerUtil.mutListener.listen(29009)) {
                                                            type = "furniture";
                                                        }
                                                        break;
                                                    case "boutique":
                                                    case "clothes":
                                                        if (!ListenerUtil.mutListener.listen(29010)) {
                                                            type = "clothing_store";
                                                        }
                                                        break;
                                                    case "beauty":
                                                    case "depilation":
                                                        if (!ListenerUtil.mutListener.listen(29011)) {
                                                            type = "beauty_salon";
                                                        }
                                                        break;
                                                    case "chemist":
                                                        if (!ListenerUtil.mutListener.listen(29012)) {
                                                            type = "health";
                                                        }
                                                        break;
                                                    case "townhall":
                                                        if (!ListenerUtil.mutListener.listen(29013)) {
                                                            type = "local_government_office";
                                                        }
                                                        break;
                                                    case "bicycle":
                                                    case "bicycle_rental":
                                                        if (!ListenerUtil.mutListener.listen(29014)) {
                                                            type = "bicycle_store";
                                                        }
                                                        break;
                                                    case "cave_entrance":
                                                        if (!ListenerUtil.mutListener.listen(29015)) {
                                                            type = "park";
                                                        }
                                                        break;
                                                    case "peak":
                                                    case "volcano":
                                                        if (!ListenerUtil.mutListener.listen(29016)) {
                                                            type = "mountain";
                                                        }
                                                        break;
                                                    case "station":
                                                        if (!ListenerUtil.mutListener.listen(29017)) {
                                                            type = "station";
                                                        }
                                                        break;
                                                    case "pub":
                                                        if (!ListenerUtil.mutListener.listen(29018)) {
                                                            type = "bar";
                                                        }
                                                        break;
                                                    case "stop_position":
                                                        if (!ListenerUtil.mutListener.listen(29019)) {
                                                            type = "bus_station";
                                                        }
                                                        break;
                                                    case "stationery":
                                                    case "kiosk":
                                                        if (!ListenerUtil.mutListener.listen(29020)) {
                                                            type = "store";
                                                        }
                                                        break;
                                                    case "shoes":
                                                        if (!ListenerUtil.mutListener.listen(29021)) {
                                                            type = "shoe_store";
                                                        }
                                                        break;
                                                    case "aerialway":
                                                        if (!ListenerUtil.mutListener.listen(29022)) {
                                                            type = "cablecar";
                                                        }
                                                        break;
                                                    case "rest_area":
                                                    case "services":
                                                        if (!ListenerUtil.mutListener.listen(29023)) {
                                                            type = "highway";
                                                        }
                                                        break;
                                                    case "cinema":
                                                        if (!ListenerUtil.mutListener.listen(29024)) {
                                                            type = "movie_theater";
                                                        }
                                                        break;
                                                    default:
                                                        break;
                                                }
                                            }
                                            if (!ListenerUtil.mutListener.listen(29026)) {
                                                poi.setType(type);
                                            }
                                            if (!ListenerUtil.mutListener.listen(29027)) {
                                                pois.add(poi);
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
    }
}
