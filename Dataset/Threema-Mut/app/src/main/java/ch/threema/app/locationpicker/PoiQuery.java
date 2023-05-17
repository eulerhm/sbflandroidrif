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
import androidx.annotation.Nullable;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class PoiQuery {

    PoiQuery(String query, LatLng center) {
        if (!ListenerUtil.mutListener.listen(29050)) {
            this.query = query;
        }
        if (!ListenerUtil.mutListener.listen(29051)) {
            this.center = center;
        }
    }

    private String query;

    private LatLng center;

    public String getQuery() {
        return query;
    }

    public LatLng getCenter() {
        return center;
    }

    @Override
    public boolean equals(@Nullable Object other) {
        if (!ListenerUtil.mutListener.listen(29065)) {
            if (other instanceof PoiQuery) {
                PoiQuery otherPoi = (PoiQuery) other;
                boolean centerIsSame = false;
                boolean queryIsSame = false;
                if (!ListenerUtil.mutListener.listen(29054)) {
                    if ((ListenerUtil.mutListener.listen(29052) ? (center == null || otherPoi.getCenter() == null) : (center == null && otherPoi.getCenter() == null))) {
                        if (!ListenerUtil.mutListener.listen(29053)) {
                            centerIsSame = true;
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(29057)) {
                    if ((ListenerUtil.mutListener.listen(29055) ? (query == null || otherPoi.getQuery() == null) : (query == null && otherPoi.getQuery() == null))) {
                        if (!ListenerUtil.mutListener.listen(29056)) {
                            queryIsSame = true;
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(29060)) {
                    if ((ListenerUtil.mutListener.listen(29058) ? (center != null || center.equals(otherPoi.getCenter())) : (center != null && center.equals(otherPoi.getCenter())))) {
                        if (!ListenerUtil.mutListener.listen(29059)) {
                            centerIsSame = true;
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(29063)) {
                    if ((ListenerUtil.mutListener.listen(29061) ? (query != null || query.equals(otherPoi.getQuery())) : (query != null && query.equals(otherPoi.getQuery())))) {
                        if (!ListenerUtil.mutListener.listen(29062)) {
                            queryIsSame = true;
                        }
                    }
                }
                return (ListenerUtil.mutListener.listen(29064) ? (centerIsSame || queryIsSame) : (centerIsSame && queryIsSame));
            }
        }
        return false;
    }
}
