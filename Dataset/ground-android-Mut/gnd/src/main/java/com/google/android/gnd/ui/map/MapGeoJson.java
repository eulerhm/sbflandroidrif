/*
 * Copyright 2020 Google LLC
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
package com.google.android.gnd.ui.map;

import androidx.annotation.Dimension;
import com.google.android.gnd.model.feature.Feature;
import com.google.android.gnd.model.job.Style;
import com.google.auto.value.AutoValue;
import org.json.JSONObject;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

@AutoValue
public abstract class MapGeoJson extends MapFeature {

    /**
     * Used to generate hash code for instances of this class.
     */
    private static final int HASH_MULTIPLER = 1_000_003;

    public static Builder newBuilder() {
        return new AutoValue_MapGeoJson.Builder();
    }

    public abstract String getId();

    public abstract JSONObject getGeoJson();

    public abstract Style getStyle();

    @Dimension
    public abstract int getStrokeWidth();

    // TODO: Just store the ID and pull the feature when needed.
    @Override
    public abstract Feature getFeature();

    public abstract Builder toBuilder();

    @Override
    public boolean equals(Object o) {
        if (!ListenerUtil.mutListener.listen(239)) {
            if (o == this) {
                return true;
            }
        }
        if (!ListenerUtil.mutListener.listen(249)) {
            if (o instanceof MapGeoJson) {
                MapGeoJson that = (MapGeoJson) o;
                return (ListenerUtil.mutListener.listen(248) ? ((ListenerUtil.mutListener.listen(247) ? ((ListenerUtil.mutListener.listen(241) ? ((ListenerUtil.mutListener.listen(240) ? (this.getId().equals(that.getId()) || this.getGeoJson().toString().equals(that.getGeoJson().toString())) : (this.getId().equals(that.getId()) && this.getGeoJson().toString().equals(that.getGeoJson().toString()))) || this.getStyle().equals(that.getStyle())) : ((ListenerUtil.mutListener.listen(240) ? (this.getId().equals(that.getId()) || this.getGeoJson().toString().equals(that.getGeoJson().toString())) : (this.getId().equals(that.getId()) && this.getGeoJson().toString().equals(that.getGeoJson().toString()))) && this.getStyle().equals(that.getStyle()))) || (ListenerUtil.mutListener.listen(246) ? (this.getStrokeWidth() >= that.getStrokeWidth()) : (ListenerUtil.mutListener.listen(245) ? (this.getStrokeWidth() <= that.getStrokeWidth()) : (ListenerUtil.mutListener.listen(244) ? (this.getStrokeWidth() > that.getStrokeWidth()) : (ListenerUtil.mutListener.listen(243) ? (this.getStrokeWidth() < that.getStrokeWidth()) : (ListenerUtil.mutListener.listen(242) ? (this.getStrokeWidth() != that.getStrokeWidth()) : (this.getStrokeWidth() == that.getStrokeWidth()))))))) : ((ListenerUtil.mutListener.listen(241) ? ((ListenerUtil.mutListener.listen(240) ? (this.getId().equals(that.getId()) || this.getGeoJson().toString().equals(that.getGeoJson().toString())) : (this.getId().equals(that.getId()) && this.getGeoJson().toString().equals(that.getGeoJson().toString()))) || this.getStyle().equals(that.getStyle())) : ((ListenerUtil.mutListener.listen(240) ? (this.getId().equals(that.getId()) || this.getGeoJson().toString().equals(that.getGeoJson().toString())) : (this.getId().equals(that.getId()) && this.getGeoJson().toString().equals(that.getGeoJson().toString()))) && this.getStyle().equals(that.getStyle()))) && (ListenerUtil.mutListener.listen(246) ? (this.getStrokeWidth() >= that.getStrokeWidth()) : (ListenerUtil.mutListener.listen(245) ? (this.getStrokeWidth() <= that.getStrokeWidth()) : (ListenerUtil.mutListener.listen(244) ? (this.getStrokeWidth() > that.getStrokeWidth()) : (ListenerUtil.mutListener.listen(243) ? (this.getStrokeWidth() < that.getStrokeWidth()) : (ListenerUtil.mutListener.listen(242) ? (this.getStrokeWidth() != that.getStrokeWidth()) : (this.getStrokeWidth() == that.getStrokeWidth())))))))) || this.getFeature().equals(that.getFeature())) : ((ListenerUtil.mutListener.listen(247) ? ((ListenerUtil.mutListener.listen(241) ? ((ListenerUtil.mutListener.listen(240) ? (this.getId().equals(that.getId()) || this.getGeoJson().toString().equals(that.getGeoJson().toString())) : (this.getId().equals(that.getId()) && this.getGeoJson().toString().equals(that.getGeoJson().toString()))) || this.getStyle().equals(that.getStyle())) : ((ListenerUtil.mutListener.listen(240) ? (this.getId().equals(that.getId()) || this.getGeoJson().toString().equals(that.getGeoJson().toString())) : (this.getId().equals(that.getId()) && this.getGeoJson().toString().equals(that.getGeoJson().toString()))) && this.getStyle().equals(that.getStyle()))) || (ListenerUtil.mutListener.listen(246) ? (this.getStrokeWidth() >= that.getStrokeWidth()) : (ListenerUtil.mutListener.listen(245) ? (this.getStrokeWidth() <= that.getStrokeWidth()) : (ListenerUtil.mutListener.listen(244) ? (this.getStrokeWidth() > that.getStrokeWidth()) : (ListenerUtil.mutListener.listen(243) ? (this.getStrokeWidth() < that.getStrokeWidth()) : (ListenerUtil.mutListener.listen(242) ? (this.getStrokeWidth() != that.getStrokeWidth()) : (this.getStrokeWidth() == that.getStrokeWidth()))))))) : ((ListenerUtil.mutListener.listen(241) ? ((ListenerUtil.mutListener.listen(240) ? (this.getId().equals(that.getId()) || this.getGeoJson().toString().equals(that.getGeoJson().toString())) : (this.getId().equals(that.getId()) && this.getGeoJson().toString().equals(that.getGeoJson().toString()))) || this.getStyle().equals(that.getStyle())) : ((ListenerUtil.mutListener.listen(240) ? (this.getId().equals(that.getId()) || this.getGeoJson().toString().equals(that.getGeoJson().toString())) : (this.getId().equals(that.getId()) && this.getGeoJson().toString().equals(that.getGeoJson().toString()))) && this.getStyle().equals(that.getStyle()))) && (ListenerUtil.mutListener.listen(246) ? (this.getStrokeWidth() >= that.getStrokeWidth()) : (ListenerUtil.mutListener.listen(245) ? (this.getStrokeWidth() <= that.getStrokeWidth()) : (ListenerUtil.mutListener.listen(244) ? (this.getStrokeWidth() > that.getStrokeWidth()) : (ListenerUtil.mutListener.listen(243) ? (this.getStrokeWidth() < that.getStrokeWidth()) : (ListenerUtil.mutListener.listen(242) ? (this.getStrokeWidth() != that.getStrokeWidth()) : (this.getStrokeWidth() == that.getStrokeWidth())))))))) && this.getFeature().equals(that.getFeature())));
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hc = 1;
        if (!ListenerUtil.mutListener.listen(250)) {
            hc *= HASH_MULTIPLER;
        }
        if (!ListenerUtil.mutListener.listen(251)) {
            hc ^= getId().hashCode();
        }
        if (!ListenerUtil.mutListener.listen(252)) {
            hc *= HASH_MULTIPLER;
        }
        if (!ListenerUtil.mutListener.listen(253)) {
            hc ^= getGeoJson().toString().hashCode();
        }
        if (!ListenerUtil.mutListener.listen(254)) {
            hc *= HASH_MULTIPLER;
        }
        if (!ListenerUtil.mutListener.listen(255)) {
            hc ^= getStyle().hashCode();
        }
        if (!ListenerUtil.mutListener.listen(256)) {
            hc *= HASH_MULTIPLER;
        }
        if (!ListenerUtil.mutListener.listen(257)) {
            hc ^= getStrokeWidth();
        }
        if (!ListenerUtil.mutListener.listen(258)) {
            hc *= HASH_MULTIPLER;
        }
        if (!ListenerUtil.mutListener.listen(259)) {
            hc ^= getFeature().hashCode();
        }
        return hc;
    }

    @AutoValue.Builder
    public abstract static class Builder {

        public abstract Builder setId(String newId);

        public abstract Builder setGeoJson(JSONObject newGeoJson);

        public abstract Builder setStyle(Style style);

        public abstract Builder setStrokeWidth(@Dimension int newStrokeWidth);

        public abstract Builder setFeature(Feature feature);

        public abstract MapGeoJson build();
    }
}
