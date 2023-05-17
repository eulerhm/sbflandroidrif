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
package com.google.android.gnd.persistence.remote.firestore.schema;

import static com.google.android.gnd.util.ImmutableListCollector.toImmutableList;
import static java8.util.stream.StreamSupport.stream;
import com.google.android.gnd.model.User;
import com.google.android.gnd.model.feature.Point;
import com.google.android.gnd.model.mutation.FeatureMutation;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.firebase.firestore.GeoPoint;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import timber.log.Timber;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Converts between Firestore maps used to merge updates and {@link FeatureMutation} instances.
 */
class LoiMutationConverter {

    /**
     * Returns a map containing key-value pairs usable by Firestore constructed from the provided
     * mutation.
     */
    static ImmutableMap<String, Object> toMap(FeatureMutation mutation, User user) {
        ImmutableMap.Builder<String, Object> map = ImmutableMap.builder();
        if (!ListenerUtil.mutListener.listen(1404)) {
            map.put(LoiConverter.JOB_ID, mutation.getJobId());
        }
        if (!ListenerUtil.mutListener.listen(1405)) {
            mutation.getLocation().map(LoiMutationConverter::toGeoPoint).ifPresent(point -> map.put(LoiConverter.LOCATION, point));
        }
        Map<String, Object> geometry = new HashMap<>();
        if (!ListenerUtil.mutListener.listen(1406)) {
            geometry.put(LoiConverter.GEOMETRY_COORDINATES, toGeoPointList(mutation.getPolygonVertices()));
        }
        if (!ListenerUtil.mutListener.listen(1407)) {
            geometry.put(LoiConverter.GEOMETRY_TYPE, LoiConverter.POLYGON_TYPE);
        }
        if (!ListenerUtil.mutListener.listen(1408)) {
            map.put(LoiConverter.GEOMETRY, geometry);
        }
        AuditInfoNestedObject auditInfo = AuditInfoConverter.fromMutationAndUser(mutation, user);
        if (!ListenerUtil.mutListener.listen(1413)) {
            switch(mutation.getType()) {
                case CREATE:
                    if (!ListenerUtil.mutListener.listen(1409)) {
                        map.put(LoiConverter.CREATED, auditInfo);
                    }
                    if (!ListenerUtil.mutListener.listen(1410)) {
                        map.put(LoiConverter.LAST_MODIFIED, auditInfo);
                    }
                    break;
                case UPDATE:
                    if (!ListenerUtil.mutListener.listen(1411)) {
                        map.put(LoiConverter.LAST_MODIFIED, auditInfo);
                    }
                    break;
                case DELETE:
                case UNKNOWN:
                    // TODO.
                    throw new UnsupportedOperationException();
                default:
                    if (!ListenerUtil.mutListener.listen(1412)) {
                        Timber.e("Unhandled state: %s", mutation.getType());
                    }
                    break;
            }
        }
        return map.build();
    }

    private static GeoPoint toGeoPoint(Point point) {
        return new GeoPoint(point.getLatitude(), point.getLongitude());
    }

    private static List<GeoPoint> toGeoPointList(ImmutableList<Point> point) {
        return stream(point).map(LoiMutationConverter::toGeoPoint).collect(toImmutableList());
    }
}
