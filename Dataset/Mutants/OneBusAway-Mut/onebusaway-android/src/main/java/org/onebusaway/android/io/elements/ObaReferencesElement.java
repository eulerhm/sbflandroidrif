/*
 * Copyright (C) 2010 Paul Watts (paulcwatts@gmail.com)
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
package org.onebusaway.android.io.elements;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public final class ObaReferencesElement implements ObaReferences {

    public static final ObaReferencesElement EMPTY_OBJECT = new ObaReferencesElement();

    private final ObaStopElement[] stops;

    private final ObaRouteElement[] routes;

    private final ObaTripElement[] trips;

    private final ObaAgencyElement[] agencies;

    private final ObaSituationElement[] situations;

    public ObaReferencesElement() {
        stops = ObaStopElement.EMPTY_ARRAY;
        routes = ObaRouteElement.EMPTY_ARRAY;
        trips = ObaTripElement.EMPTY_ARRAY;
        agencies = ObaAgencyElement.EMPTY_ARRAY;
        situations = ObaSituationElement.EMPTY_ARRAY;
    }

    @Override
    public ObaStop getStop(String id) {
        return findById(stops, id);
    }

    @Override
    public List<ObaStop> getStops(String[] ids) {
        return findList(ObaStop.class, stops, ids);
    }

    @Override
    public ObaRoute getRoute(String id) {
        return findById(routes, id);
    }

    @Override
    public List<ObaRoute> getRoutes(String[] ids) {
        return findList(ObaRoute.class, routes, ids);
    }

    @Override
    public List<ObaRoute> getRoutes() {
        return new ArrayList<ObaRoute>(Arrays.asList(routes));
    }

    @Override
    public ObaTrip getTrip(String id) {
        return findById(trips, id);
    }

    @Override
    public List<ObaTrip> getTrips(String[] ids) {
        return findList(ObaTrip.class, trips, ids);
    }

    @Override
    public ObaAgency getAgency(String id) {
        return findById(agencies, id);
    }

    @Override
    public List<ObaAgency> getAgencies(String[] ids) {
        return findList(ObaAgency.class, agencies, ids);
    }

    @Override
    public ObaSituation getSituation(String id) {
        return findById(situations, id);
    }

    @Override
    public List<ObaSituation> getSituations(String[] ids) {
        return findList(ObaSituation.class, situations, ids);
    }

    // 
    private static <T extends ObaElement> T findById(T[] objects, String id) {
        final int len = objects.length;
        if (!ListenerUtil.mutListener.listen(8213)) {
            {
                long _loopCounter105 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(8212) ? (i >= len) : (ListenerUtil.mutListener.listen(8211) ? (i <= len) : (ListenerUtil.mutListener.listen(8210) ? (i > len) : (ListenerUtil.mutListener.listen(8209) ? (i != len) : (ListenerUtil.mutListener.listen(8208) ? (i == len) : (i < len)))))); ++i) {
                    ListenerUtil.loopListener.listen("_loopCounter105", ++_loopCounter105);
                    final T obj = objects[i];
                    if (!ListenerUtil.mutListener.listen(8207)) {
                        if (obj.getId().equals(id)) {
                            return obj;
                        }
                    }
                }
            }
        }
        return null;
    }

    private static <E extends ObaElement, T extends E> List<E> findList(Class<E> cls, T[] objects, String[] ids) {
        ArrayList<E> result = new ArrayList<E>();
        final int len = ids.length;
        if (!ListenerUtil.mutListener.listen(8221)) {
            {
                long _loopCounter106 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(8220) ? (i >= len) : (ListenerUtil.mutListener.listen(8219) ? (i <= len) : (ListenerUtil.mutListener.listen(8218) ? (i > len) : (ListenerUtil.mutListener.listen(8217) ? (i != len) : (ListenerUtil.mutListener.listen(8216) ? (i == len) : (i < len)))))); ++i) {
                    ListenerUtil.loopListener.listen("_loopCounter106", ++_loopCounter106);
                    final String id = ids[i];
                    final T obj = findById(objects, id);
                    if (!ListenerUtil.mutListener.listen(8215)) {
                        if (obj != null) {
                            if (!ListenerUtil.mutListener.listen(8214)) {
                                result.add(obj);
                            }
                        }
                    }
                }
            }
        }
        return result;
    }
}
