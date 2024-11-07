/*
 * Copyright (C) 2016 University of South Florida (sjbarbeau@gmail.com)
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
package org.onebusaway.android.util;

import android.content.Context;
import android.content.res.Resources;
import org.onebusaway.android.R;
import org.onebusaway.android.app.Application;
import org.onebusaway.android.io.elements.ObaArrivalInfo;
import org.onebusaway.android.ui.ArrivalInfo;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class ArrivalInfoUtils {

    static final class InfoComparator implements Comparator<ArrivalInfo> {

        public int compare(ArrivalInfo lhs, ArrivalInfo rhs) {
            return (int) ((ListenerUtil.mutListener.listen(7561) ? (lhs.getEta() % rhs.getEta()) : (ListenerUtil.mutListener.listen(7560) ? (lhs.getEta() / rhs.getEta()) : (ListenerUtil.mutListener.listen(7559) ? (lhs.getEta() * rhs.getEta()) : (ListenerUtil.mutListener.listen(7558) ? (lhs.getEta() + rhs.getEta()) : (lhs.getEta() - rhs.getEta()))))));
        }
    }

    /**
     * Converts the ObaArrivalInfo array received from the server to an ArrayList for the adapter
     *
     * @param context
     * @param arrivalInfo
     * @param filter                               routeIds to filter for
     * @param ms                                   current time in milliseconds
     * @param includeArrivalDepartureInStatusLabel true if the arrival/departure label should be
     *                                             included in the status label, false if it should
     *                                             not
     * @return ArrayList of arrival info to be used with the adapter
     */
    public static ArrayList<ArrivalInfo> convertObaArrivalInfo(Context context, ObaArrivalInfo[] arrivalInfo, ArrayList<String> filter, long ms, boolean includeArrivalDepartureInStatusLabel) {
        final int len = arrivalInfo.length;
        ArrayList<ArrivalInfo> result = new ArrayList<ArrivalInfo>(len);
        if (!ListenerUtil.mutListener.listen(7575)) {
            if ((ListenerUtil.mutListener.listen(7567) ? (filter != null || (ListenerUtil.mutListener.listen(7566) ? (filter.size() >= 0) : (ListenerUtil.mutListener.listen(7565) ? (filter.size() <= 0) : (ListenerUtil.mutListener.listen(7564) ? (filter.size() < 0) : (ListenerUtil.mutListener.listen(7563) ? (filter.size() != 0) : (ListenerUtil.mutListener.listen(7562) ? (filter.size() == 0) : (filter.size() > 0))))))) : (filter != null && (ListenerUtil.mutListener.listen(7566) ? (filter.size() >= 0) : (ListenerUtil.mutListener.listen(7565) ? (filter.size() <= 0) : (ListenerUtil.mutListener.listen(7564) ? (filter.size() < 0) : (ListenerUtil.mutListener.listen(7563) ? (filter.size() != 0) : (ListenerUtil.mutListener.listen(7562) ? (filter.size() == 0) : (filter.size() > 0))))))))) {
                if (!ListenerUtil.mutListener.listen(7574)) {
                    {
                        long _loopCounter90 = 0;
                        // Only add routes that haven't been filtered out
                        for (ObaArrivalInfo arrival : arrivalInfo) {
                            ListenerUtil.loopListener.listen("_loopCounter90", ++_loopCounter90);
                            if (!ListenerUtil.mutListener.listen(7573)) {
                                if (filter.contains(arrival.getRouteId())) {
                                    ArrivalInfo info = new ArrivalInfo(context, arrival, ms, includeArrivalDepartureInStatusLabel);
                                    if (!ListenerUtil.mutListener.listen(7572)) {
                                        if (shouldAddEta(info)) {
                                            if (!ListenerUtil.mutListener.listen(7571)) {
                                                result.add(info);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(7570)) {
                    {
                        long _loopCounter89 = 0;
                        // Add arrivals for all routes
                        for (ObaArrivalInfo obaArrivalInfo : arrivalInfo) {
                            ListenerUtil.loopListener.listen("_loopCounter89", ++_loopCounter89);
                            ArrivalInfo info = new ArrivalInfo(context, obaArrivalInfo, ms, includeArrivalDepartureInStatusLabel);
                            if (!ListenerUtil.mutListener.listen(7569)) {
                                if (shouldAddEta(info)) {
                                    if (!ListenerUtil.mutListener.listen(7568)) {
                                        result.add(info);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(7576)) {
            // Sort by ETA
            Collections.sort(result, new InfoComparator());
        }
        return result;
    }

    /**
     * Returns true if this ETA should be added based on the user preference for adding negative
     * arrival times, and false if it should not
     *
     * @param info info that includes the ETA to be evaluated
     * @return true if this ETA should be added based on the user preference for adding negative
     * arrival times, and false if it should not
     */
    private static boolean shouldAddEta(ArrivalInfo info) {
        boolean showNegativeArrivals = Application.getPrefs().getBoolean(Application.get().getResources().getString(R.string.preference_key_show_negative_arrivals), true);
        if (!ListenerUtil.mutListener.listen(7583)) {
            if ((ListenerUtil.mutListener.listen(7581) ? (info.getEta() <= 0) : (ListenerUtil.mutListener.listen(7580) ? (info.getEta() > 0) : (ListenerUtil.mutListener.listen(7579) ? (info.getEta() < 0) : (ListenerUtil.mutListener.listen(7578) ? (info.getEta() != 0) : (ListenerUtil.mutListener.listen(7577) ? (info.getEta() == 0) : (info.getEta() >= 0))))))) {
                // Always add positive ETAs
                return true;
            } else {
                if (!ListenerUtil.mutListener.listen(7582)) {
                    // Only add negative ETAs based on setting
                    if (showNegativeArrivals) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Returns the index in the provided infoList for the first non-negative arrival ETA in the
     * list, or -1 if no non-negative ETAs exist in the list
     *
     * @param infoList list to search for non-negative arrival times, ordered by relative ETA from
     *                 negative infinity to positive infinity
     * @return the index in the provided infoList for the first non-negative arrival ETA in the
     * list, or -1 if no non-negative ETAs exist in the list
     */
    public static int findFirstNonNegativeArrival(ArrayList<ArrivalInfo> infoList) {
        if (!ListenerUtil.mutListener.listen(7595)) {
            {
                long _loopCounter91 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(7594) ? (i >= infoList.size()) : (ListenerUtil.mutListener.listen(7593) ? (i <= infoList.size()) : (ListenerUtil.mutListener.listen(7592) ? (i > infoList.size()) : (ListenerUtil.mutListener.listen(7591) ? (i != infoList.size()) : (ListenerUtil.mutListener.listen(7590) ? (i == infoList.size()) : (i < infoList.size())))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter91", ++_loopCounter91);
                    ArrivalInfo info = infoList.get(i);
                    if (!ListenerUtil.mutListener.listen(7589)) {
                        if ((ListenerUtil.mutListener.listen(7588) ? (info.getEta() <= 0) : (ListenerUtil.mutListener.listen(7587) ? (info.getEta() > 0) : (ListenerUtil.mutListener.listen(7586) ? (info.getEta() < 0) : (ListenerUtil.mutListener.listen(7585) ? (info.getEta() != 0) : (ListenerUtil.mutListener.listen(7584) ? (info.getEta() == 0) : (info.getEta() >= 0))))))) {
                            return i;
                        }
                    }
                }
            }
        }
        // We didn't find any non-negative ETAs
        return -1;
    }

    /**
     * Returns the indexes in the provided infoList for the preferred route/headsign combinations
     * to be prioritized for displayed in the header, or null if no non-negative ETAs exist in the
     * list.  If no route/headsign combinations are favorited, the indexes returned may simply be
     * the indexes of the first (and second, if it exists) non-negative arrival times.
     *
     * @param infoList list to search for non-negative arrival times, ordered by relative ETA from
     *                 negative infinity to positive infinity
     * @return the indexes in the provided infoList for the preferred route/headsign combinations
     * to be prioritized for displayed in the header, or null if no non-negative ETAs exist in the
     * list
     */
    public static ArrayList<Integer> findPreferredArrivalIndexes(ArrayList<ArrivalInfo> infoList) {
        // Start by getting the index of the first non-negative arrival time
        int firstIndex = findFirstNonNegativeArrival(infoList);
        if (!ListenerUtil.mutListener.listen(7601)) {
            if ((ListenerUtil.mutListener.listen(7600) ? (firstIndex >= -1) : (ListenerUtil.mutListener.listen(7599) ? (firstIndex <= -1) : (ListenerUtil.mutListener.listen(7598) ? (firstIndex > -1) : (ListenerUtil.mutListener.listen(7597) ? (firstIndex < -1) : (ListenerUtil.mutListener.listen(7596) ? (firstIndex != -1) : (firstIndex == -1))))))) {
                return null;
            }
        }
        // Find any favorites
        ArrayList<Integer> preferredIndexes = new ArrayList<>();
        if (!ListenerUtil.mutListener.listen(7609)) {
            {
                long _loopCounter92 = 0;
                for (int i = firstIndex; (ListenerUtil.mutListener.listen(7608) ? (i >= infoList.size()) : (ListenerUtil.mutListener.listen(7607) ? (i <= infoList.size()) : (ListenerUtil.mutListener.listen(7606) ? (i > infoList.size()) : (ListenerUtil.mutListener.listen(7605) ? (i != infoList.size()) : (ListenerUtil.mutListener.listen(7604) ? (i == infoList.size()) : (i < infoList.size())))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter92", ++_loopCounter92);
                    ArrivalInfo info = infoList.get(i);
                    if (!ListenerUtil.mutListener.listen(7603)) {
                        if (info.isRouteAndHeadsignFavorite()) {
                            if (!ListenerUtil.mutListener.listen(7602)) {
                                preferredIndexes.add(i);
                            }
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(7615)) {
            // If we have at least two favorites, that's enough to fill the header - return them
            if ((ListenerUtil.mutListener.listen(7614) ? (preferredIndexes.size() <= 2) : (ListenerUtil.mutListener.listen(7613) ? (preferredIndexes.size() > 2) : (ListenerUtil.mutListener.listen(7612) ? (preferredIndexes.size() < 2) : (ListenerUtil.mutListener.listen(7611) ? (preferredIndexes.size() != 2) : (ListenerUtil.mutListener.listen(7610) ? (preferredIndexes.size() == 2) : (preferredIndexes.size() >= 2))))))) {
                return preferredIndexes;
            }
        }
        if (!ListenerUtil.mutListener.listen(7628)) {
            // If we have one favorite, and the index is different from the firstIndex, then add the firstIndex and return
            if ((ListenerUtil.mutListener.listen(7626) ? ((ListenerUtil.mutListener.listen(7620) ? (preferredIndexes.size() >= 1) : (ListenerUtil.mutListener.listen(7619) ? (preferredIndexes.size() <= 1) : (ListenerUtil.mutListener.listen(7618) ? (preferredIndexes.size() > 1) : (ListenerUtil.mutListener.listen(7617) ? (preferredIndexes.size() < 1) : (ListenerUtil.mutListener.listen(7616) ? (preferredIndexes.size() != 1) : (preferredIndexes.size() == 1)))))) || (ListenerUtil.mutListener.listen(7625) ? (preferredIndexes.get(0) >= firstIndex) : (ListenerUtil.mutListener.listen(7624) ? (preferredIndexes.get(0) <= firstIndex) : (ListenerUtil.mutListener.listen(7623) ? (preferredIndexes.get(0) > firstIndex) : (ListenerUtil.mutListener.listen(7622) ? (preferredIndexes.get(0) < firstIndex) : (ListenerUtil.mutListener.listen(7621) ? (preferredIndexes.get(0) == firstIndex) : (preferredIndexes.get(0) != firstIndex))))))) : ((ListenerUtil.mutListener.listen(7620) ? (preferredIndexes.size() >= 1) : (ListenerUtil.mutListener.listen(7619) ? (preferredIndexes.size() <= 1) : (ListenerUtil.mutListener.listen(7618) ? (preferredIndexes.size() > 1) : (ListenerUtil.mutListener.listen(7617) ? (preferredIndexes.size() < 1) : (ListenerUtil.mutListener.listen(7616) ? (preferredIndexes.size() != 1) : (preferredIndexes.size() == 1)))))) && (ListenerUtil.mutListener.listen(7625) ? (preferredIndexes.get(0) >= firstIndex) : (ListenerUtil.mutListener.listen(7624) ? (preferredIndexes.get(0) <= firstIndex) : (ListenerUtil.mutListener.listen(7623) ? (preferredIndexes.get(0) > firstIndex) : (ListenerUtil.mutListener.listen(7622) ? (preferredIndexes.get(0) < firstIndex) : (ListenerUtil.mutListener.listen(7621) ? (preferredIndexes.get(0) == firstIndex) : (preferredIndexes.get(0) != firstIndex))))))))) {
                if (!ListenerUtil.mutListener.listen(7627)) {
                    preferredIndexes.add(firstIndex);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(7646)) {
            // If we have no preferred indexes (i.e., starred route/headsigns) at this point, then add the firstIndex
            if ((ListenerUtil.mutListener.listen(7633) ? (preferredIndexes.size() >= 0) : (ListenerUtil.mutListener.listen(7632) ? (preferredIndexes.size() <= 0) : (ListenerUtil.mutListener.listen(7631) ? (preferredIndexes.size() > 0) : (ListenerUtil.mutListener.listen(7630) ? (preferredIndexes.size() < 0) : (ListenerUtil.mutListener.listen(7629) ? (preferredIndexes.size() != 0) : (preferredIndexes.size() == 0))))))) {
                if (!ListenerUtil.mutListener.listen(7634)) {
                    preferredIndexes.add(firstIndex);
                }
                // If there is another non-negative arrival time, then add it too
                int secondIndex = (ListenerUtil.mutListener.listen(7638) ? (firstIndex % 1) : (ListenerUtil.mutListener.listen(7637) ? (firstIndex / 1) : (ListenerUtil.mutListener.listen(7636) ? (firstIndex * 1) : (ListenerUtil.mutListener.listen(7635) ? (firstIndex - 1) : (firstIndex + 1)))));
                if (!ListenerUtil.mutListener.listen(7645)) {
                    if ((ListenerUtil.mutListener.listen(7643) ? (secondIndex >= infoList.size()) : (ListenerUtil.mutListener.listen(7642) ? (secondIndex <= infoList.size()) : (ListenerUtil.mutListener.listen(7641) ? (secondIndex > infoList.size()) : (ListenerUtil.mutListener.listen(7640) ? (secondIndex != infoList.size()) : (ListenerUtil.mutListener.listen(7639) ? (secondIndex == infoList.size()) : (secondIndex < infoList.size()))))))) {
                        if (!ListenerUtil.mutListener.listen(7644)) {
                            preferredIndexes.add(secondIndex);
                        }
                    }
                }
            }
        }
        return preferredIndexes;
    }

    /**
     * Returns the status color to be used, depending on whether the vehicle is running early,
     * late,
     * ontime,
     * or if we don't have real-time info (i.e., scheduled)
     *
     * @param scheduled the scheduled time, in minutes past unix epoch
     * @param predicted the predicted time, in minutes past unix epoch
     * @return the status color to be used, depending on whether the vehicle is running early, late,
     * ontime,
     * or if we don't have real-time info (i.e., scheduled)
     */
    public static int computeColor(final long scheduled, final long predicted) {
        if ((ListenerUtil.mutListener.listen(7651) ? (predicted >= 0) : (ListenerUtil.mutListener.listen(7650) ? (predicted <= 0) : (ListenerUtil.mutListener.listen(7649) ? (predicted > 0) : (ListenerUtil.mutListener.listen(7648) ? (predicted < 0) : (ListenerUtil.mutListener.listen(7647) ? (predicted == 0) : (predicted != 0))))))) {
            return computeColorFromDeviation((ListenerUtil.mutListener.listen(7655) ? (predicted % scheduled) : (ListenerUtil.mutListener.listen(7654) ? (predicted / scheduled) : (ListenerUtil.mutListener.listen(7653) ? (predicted * scheduled) : (ListenerUtil.mutListener.listen(7652) ? (predicted + scheduled) : (predicted - scheduled))))));
        } else {
            // Use scheduled color
            return R.color.stop_info_scheduled_time;
        }
    }

    /**
     * Returns the status color to be used, depending on whether the vehicle is running early,
     * late,
     * ontime,
     * or if we don't have real-time info (i.e., scheduled)
     *
     * @param delay the deviation from the scheduled time, in minutes - positive means bus is
     *              running late,
     *              negative means early
     * @return the status color to be used, depending on whether the vehicle is running early, late,
     * ontime,
     * or if we don't have real-time info (i.e., scheduled)
     */
    public static int computeColorFromDeviation(final long delay) {
        // Bus is arriving
        if ((ListenerUtil.mutListener.listen(7660) ? (delay >= 0) : (ListenerUtil.mutListener.listen(7659) ? (delay <= 0) : (ListenerUtil.mutListener.listen(7658) ? (delay < 0) : (ListenerUtil.mutListener.listen(7657) ? (delay != 0) : (ListenerUtil.mutListener.listen(7656) ? (delay == 0) : (delay > 0))))))) {
            // Arriving delayed
            return R.color.stop_info_delayed;
        } else if ((ListenerUtil.mutListener.listen(7665) ? (delay >= 0) : (ListenerUtil.mutListener.listen(7664) ? (delay <= 0) : (ListenerUtil.mutListener.listen(7663) ? (delay > 0) : (ListenerUtil.mutListener.listen(7662) ? (delay != 0) : (ListenerUtil.mutListener.listen(7661) ? (delay == 0) : (delay < 0))))))) {
            // Arriving early
            return R.color.stop_info_early;
        } else {
            // Arriving on time
            return R.color.stop_info_ontime;
        }
    }

    /**
     * Computes the arrival status label from the delay (i.e., schedule deviation), where positive
     * means the bus is running late and negative means the bus is running ahead of schedule
     *
     * @param delay schedule deviation, in minutes, for this vehicle where positive
     *              means the bus is running late and negative means the bus is running ahead of
     *              schedule
     * @return the arrival status label based on the deviation
     */
    public static String computeArrivalLabelFromDelay(Resources res, long delay) {
        if ((ListenerUtil.mutListener.listen(7670) ? (delay >= 0) : (ListenerUtil.mutListener.listen(7669) ? (delay <= 0) : (ListenerUtil.mutListener.listen(7668) ? (delay < 0) : (ListenerUtil.mutListener.listen(7667) ? (delay != 0) : (ListenerUtil.mutListener.listen(7666) ? (delay == 0) : (delay > 0))))))) {
            // Arriving delayed
            return res.getQuantityString(R.plurals.stop_info_arrive_delayed, (int) delay, delay);
        } else if ((ListenerUtil.mutListener.listen(7675) ? (delay >= 0) : (ListenerUtil.mutListener.listen(7674) ? (delay <= 0) : (ListenerUtil.mutListener.listen(7673) ? (delay > 0) : (ListenerUtil.mutListener.listen(7672) ? (delay != 0) : (ListenerUtil.mutListener.listen(7671) ? (delay == 0) : (delay < 0))))))) {
            if (!ListenerUtil.mutListener.listen(7676)) {
                // Arriving early
                delay = -delay;
            }
            return res.getQuantityString(R.plurals.stop_info_arrive_early, (int) delay, delay);
        } else {
            // Arriving on time
            return res.getString(R.string.stop_info_ontime);
        }
    }
}
