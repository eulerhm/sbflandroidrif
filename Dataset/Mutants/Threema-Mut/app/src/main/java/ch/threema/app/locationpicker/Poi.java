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
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class Poi {

    private long id;

    private String name;

    private String category;

    private String type;

    private int distance;

    private String description;

    private LatLng latLng;

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        if (!ListenerUtil.mutListener.listen(29043)) {
            this.id = id;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (!ListenerUtil.mutListener.listen(29044)) {
            this.name = name;
        }
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        if (!ListenerUtil.mutListener.listen(29045)) {
            this.description = description;
        }
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        if (!ListenerUtil.mutListener.listen(29046)) {
            this.type = type;
        }
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        if (!ListenerUtil.mutListener.listen(29047)) {
            this.distance = distance;
        }
    }

    public LatLng getLatLng() {
        return latLng;
    }

    public void setLatLng(LatLng latLng) {
        if (!ListenerUtil.mutListener.listen(29048)) {
            this.latLng = latLng;
        }
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        if (!ListenerUtil.mutListener.listen(29049)) {
            this.category = category;
        }
    }
}
