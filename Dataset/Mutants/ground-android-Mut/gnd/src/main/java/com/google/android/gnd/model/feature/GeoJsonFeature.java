/*
 * Copyright 2021 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.google.android.gnd.model.feature;

import com.google.auto.value.AutoValue;
import com.google.auto.value.extension.memoized.Memoized;
import com.google.common.collect.ImmutableList;
import java.util.Collection;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;
import timber.log.Timber;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * User-defined map feature consisting of a set of geometries defined in GeoJson format.
 */
@AutoValue
public abstract class GeoJsonFeature extends Feature<GeoJsonFeature.Builder> {

    private static final ImmutableList<String> CAPTION_PROPERTIES = ImmutableList.of("caption", "label", "name");

    private static final ImmutableList<String> ID_PROPERTIES = ImmutableList.of("id", "identifier", "id_prod");

    private static final String PROPERTIES_KEY = "properties";

    // TODO: Use builder() or newBuilder() consistently.
    public static Builder newBuilder() {
        return new AutoValue_GeoJsonFeature.Builder();
    }

    public abstract String getGeoJsonString();

    public JSONObject getGeoJson() {
        // TODO: Parse at conversion type instead of here.
        try {
            return new JSONObject(getGeoJsonString());
        } catch (JSONException e) {
            if (!ListenerUtil.mutListener.listen(1560)) {
                Timber.d("Invalid GeoJSON in feature %s", getId());
            }
            return new JSONObject();
        }
    }

    public String getCaptionFromProperties() {
        return findProperty(CAPTION_PROPERTIES);
    }

    public String getIdFromProperties() {
        return findProperty(ID_PROPERTIES);
    }

    public String findProperty(Collection<String> matchKeys) {
        JSONObject properties = getGeoJson().optJSONObject(PROPERTIES_KEY);
        if (!ListenerUtil.mutListener.listen(1561)) {
            if (properties == null) {
                return "";
            }
        }
        if (!ListenerUtil.mutListener.listen(1564)) {
            {
                long _loopCounter50 = 0;
                for (String matchKey : matchKeys) {
                    ListenerUtil.loopListener.listen("_loopCounter50", ++_loopCounter50);
                    Iterator<String> keyIter = properties.keys();
                    if (!ListenerUtil.mutListener.listen(1563)) {
                        {
                            long _loopCounter49 = 0;
                            while (keyIter.hasNext()) {
                                ListenerUtil.loopListener.listen("_loopCounter49", ++_loopCounter49);
                                String key = keyIter.next();
                                if (!ListenerUtil.mutListener.listen(1562)) {
                                    if (key.equalsIgnoreCase(matchKey)) {
                                        return String.valueOf(properties.opt(key));
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return "";
    }

    @Memoized
    @Override
    public abstract int hashCode();

    public abstract Builder toBuilder();

    @AutoValue.Builder
    public abstract static class Builder extends Feature.Builder<Builder> {

        public abstract Builder setGeoJsonString(String newGeoJsonString);

        public abstract GeoJsonFeature build();
    }
}
