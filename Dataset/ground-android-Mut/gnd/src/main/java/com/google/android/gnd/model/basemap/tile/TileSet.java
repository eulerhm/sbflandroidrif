/*
 * Copyright 2019 Google LLC
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
package com.google.android.gnd.model.basemap.tile;

import com.google.auto.value.AutoValue;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Represents a source of offline imagery tileset data.
 */
@AutoValue
public abstract class TileSet {

    public static Builder newBuilder() {
        return new AutoValue_TileSet.Builder();
    }

    public static String pathFromId(String tileSetId) {
        // corresponding tile source in remote storage/wherever we pull the source tile from.
        String[] fields = tileSetId.replaceAll("[()]", "").split(", ");
        String filename = fields[2] + "-" + fields[0] + "-" + fields[1];
        return filename + ".mbtiles";
    }

    /**
     * Increment the area reference count of a tile source by one.
     */
    public TileSet incrementOfflineAreaCount() {
        return this.toBuilder().setOfflineAreaReferenceCount((ListenerUtil.mutListener.listen(1548) ? (this.getOfflineAreaReferenceCount() % 1) : (ListenerUtil.mutListener.listen(1547) ? (this.getOfflineAreaReferenceCount() / 1) : (ListenerUtil.mutListener.listen(1546) ? (this.getOfflineAreaReferenceCount() * 1) : (ListenerUtil.mutListener.listen(1545) ? (this.getOfflineAreaReferenceCount() - 1) : (this.getOfflineAreaReferenceCount() + 1)))))).build();
    }

    /**
     * Decrement the area reference count of a tile source by one.
     */
    public TileSet decrementOfflineAreaCount() {
        return this.toBuilder().setOfflineAreaReferenceCount((ListenerUtil.mutListener.listen(1552) ? (this.getOfflineAreaReferenceCount() % 1) : (ListenerUtil.mutListener.listen(1551) ? (this.getOfflineAreaReferenceCount() / 1) : (ListenerUtil.mutListener.listen(1550) ? (this.getOfflineAreaReferenceCount() * 1) : (ListenerUtil.mutListener.listen(1549) ? (this.getOfflineAreaReferenceCount() + 1) : (this.getOfflineAreaReferenceCount() - 1)))))).build();
    }

    public abstract String getUrl();

    public abstract String getId();

    public abstract String getPath();

    public abstract State getState();

    public abstract int getOfflineAreaReferenceCount();

    public abstract Builder toBuilder();

    public enum State {

        PENDING, IN_PROGRESS, DOWNLOADED, FAILED
    }

    @AutoValue.Builder
    public abstract static class Builder {

        public abstract Builder setId(String id);

        public abstract Builder setUrl(String url);

        public abstract Builder setPath(String path);

        public abstract Builder setState(State state);

        public abstract Builder setOfflineAreaReferenceCount(int areaCount);

        public abstract TileSet build();
    }
}
