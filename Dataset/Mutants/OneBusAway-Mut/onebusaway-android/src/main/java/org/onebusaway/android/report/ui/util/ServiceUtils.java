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
package org.onebusaway.android.report.ui.util;

import org.onebusaway.android.R;
import org.onebusaway.android.app.Application;
import org.onebusaway.android.report.constants.ReportConstants;
import android.content.Context;
import java.util.List;
import edu.usf.cutr.open311client.models.Service;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class ServiceUtils {

    /**
     * Given a list of Open311 services (request types), mark which ones are transit-related in
     * place based on group, keyword, or text heuristic matching
     *
     * @param context
     * @param serviceList the list of Open311 services to potentially be marked as transit-related
     * @return true if the services were determined to be via all transit-related via heuristics,
     * false if heuristics matching wasn't used
     */
    public static boolean markTransitServices(Context context, List<Service> serviceList) {
        boolean stopProblemFound = false;
        boolean tripProblemFound = false;
        if (!ListenerUtil.mutListener.listen(10832)) {
            {
                long _loopCounter141 = 0;
                // Search transit services by groups (this is the "right" way to group transit services)
                for (Service s : serviceList) {
                    ListenerUtil.loopListener.listen("_loopCounter141", ++_loopCounter141);
                    if (!ListenerUtil.mutListener.listen(10831)) {
                        if ((ListenerUtil.mutListener.listen(10823) ? (ServiceUtils.isTransitStopServiceByText(s.getGroup()) || !stopProblemFound) : (ServiceUtils.isTransitStopServiceByText(s.getGroup()) && !stopProblemFound))) {
                            if (!ListenerUtil.mutListener.listen(10828)) {
                                s.setGroup(ReportConstants.ISSUE_GROUP_TRANSIT);
                            }
                            if (!ListenerUtil.mutListener.listen(10829)) {
                                s.setType(ReportConstants.DYNAMIC_TRANSIT_SERVICE_STOP);
                            }
                            if (!ListenerUtil.mutListener.listen(10830)) {
                                stopProblemFound = true;
                            }
                        } else if ((ListenerUtil.mutListener.listen(10824) ? (ServiceUtils.isTransitTripServiceByText(s.getGroup()) || !tripProblemFound) : (ServiceUtils.isTransitTripServiceByText(s.getGroup()) && !tripProblemFound))) {
                            if (!ListenerUtil.mutListener.listen(10825)) {
                                s.setGroup(ReportConstants.ISSUE_GROUP_TRANSIT);
                            }
                            if (!ListenerUtil.mutListener.listen(10826)) {
                                s.setType(ReportConstants.DYNAMIC_TRANSIT_SERVICE_TRIP);
                            }
                            if (!ListenerUtil.mutListener.listen(10827)) {
                                tripProblemFound = true;
                            }
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(10844)) {
            // Search transit services by keywords
            if ((ListenerUtil.mutListener.listen(10833) ? (!stopProblemFound && !tripProblemFound) : (!stopProblemFound || !tripProblemFound))) {
                if (!ListenerUtil.mutListener.listen(10843)) {
                    {
                        long _loopCounter142 = 0;
                        for (Service s : serviceList) {
                            ListenerUtil.loopListener.listen("_loopCounter142", ++_loopCounter142);
                            if (!ListenerUtil.mutListener.listen(10842)) {
                                if ((ListenerUtil.mutListener.listen(10834) ? (ServiceUtils.isTransitStopServiceByText(s.getKeywords()) || !stopProblemFound) : (ServiceUtils.isTransitStopServiceByText(s.getKeywords()) && !stopProblemFound))) {
                                    if (!ListenerUtil.mutListener.listen(10839)) {
                                        s.setGroup(ReportConstants.ISSUE_GROUP_TRANSIT);
                                    }
                                    if (!ListenerUtil.mutListener.listen(10840)) {
                                        s.setType(ReportConstants.DYNAMIC_TRANSIT_SERVICE_STOP);
                                    }
                                    if (!ListenerUtil.mutListener.listen(10841)) {
                                        stopProblemFound = true;
                                    }
                                } else if ((ListenerUtil.mutListener.listen(10835) ? (ServiceUtils.isTransitTripServiceByText(s.getKeywords()) || !tripProblemFound) : (ServiceUtils.isTransitTripServiceByText(s.getKeywords()) && !tripProblemFound))) {
                                    if (!ListenerUtil.mutListener.listen(10836)) {
                                        s.setGroup(ReportConstants.ISSUE_GROUP_TRANSIT);
                                    }
                                    if (!ListenerUtil.mutListener.listen(10837)) {
                                        s.setType(ReportConstants.DYNAMIC_TRANSIT_SERVICE_TRIP);
                                    }
                                    if (!ListenerUtil.mutListener.listen(10838)) {
                                        tripProblemFound = true;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(10846)) {
            if ((ListenerUtil.mutListener.listen(10845) ? (stopProblemFound || tripProblemFound) : (stopProblemFound && tripProblemFound))) {
                // Yay!  We had explicit matching via groups or keywords and didn't need to use heuristics
                return false;
            }
        }
        // Search transit services by name and text matching heuristics - count matches
        int transitServiceCounter = 0;
        if (!ListenerUtil.mutListener.listen(10856)) {
            {
                long _loopCounter143 = 0;
                for (Service s : serviceList) {
                    ListenerUtil.loopListener.listen("_loopCounter143", ++_loopCounter143);
                    if (!ListenerUtil.mutListener.listen(10855)) {
                        if (ServiceUtils.isTransitStopServiceByText(s.getService_name())) {
                            if (!ListenerUtil.mutListener.listen(10851)) {
                                s.setGroup(ReportConstants.ISSUE_GROUP_TRANSIT);
                            }
                            if (!ListenerUtil.mutListener.listen(10852)) {
                                s.setType(ReportConstants.DYNAMIC_TRANSIT_SERVICE_STOP);
                            }
                            if (!ListenerUtil.mutListener.listen(10853)) {
                                stopProblemFound = true;
                            }
                            if (!ListenerUtil.mutListener.listen(10854)) {
                                transitServiceCounter++;
                            }
                        } else if (ServiceUtils.isTransitTripServiceByText(s.getService_name())) {
                            if (!ListenerUtil.mutListener.listen(10847)) {
                                s.setGroup(ReportConstants.ISSUE_GROUP_TRANSIT);
                            }
                            if (!ListenerUtil.mutListener.listen(10848)) {
                                s.setType(ReportConstants.DYNAMIC_TRANSIT_SERVICE_TRIP);
                            }
                            if (!ListenerUtil.mutListener.listen(10849)) {
                                tripProblemFound = true;
                            }
                            if (!ListenerUtil.mutListener.listen(10850)) {
                                transitServiceCounter++;
                            }
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(10866)) {
            /**
             * If we've found a large number of potential transit services via heuristics search, assume all
             * services are transit related, and those without a group are stop-related
             */
            if ((ListenerUtil.mutListener.listen(10861) ? (transitServiceCounter <= ReportConstants.NUM_TRANSIT_SERVICES_THRESHOLD) : (ListenerUtil.mutListener.listen(10860) ? (transitServiceCounter > ReportConstants.NUM_TRANSIT_SERVICES_THRESHOLD) : (ListenerUtil.mutListener.listen(10859) ? (transitServiceCounter < ReportConstants.NUM_TRANSIT_SERVICES_THRESHOLD) : (ListenerUtil.mutListener.listen(10858) ? (transitServiceCounter != ReportConstants.NUM_TRANSIT_SERVICES_THRESHOLD) : (ListenerUtil.mutListener.listen(10857) ? (transitServiceCounter == ReportConstants.NUM_TRANSIT_SERVICES_THRESHOLD) : (transitServiceCounter >= ReportConstants.NUM_TRANSIT_SERVICES_THRESHOLD))))))) {
                if (!ListenerUtil.mutListener.listen(10865)) {
                    {
                        long _loopCounter144 = 0;
                        for (Service s : serviceList) {
                            ListenerUtil.loopListener.listen("_loopCounter144", ++_loopCounter144);
                            if (!ListenerUtil.mutListener.listen(10864)) {
                                if (s.getGroup() == null) {
                                    if (!ListenerUtil.mutListener.listen(10862)) {
                                        s.setGroup(ReportConstants.ISSUE_GROUP_TRANSIT);
                                    }
                                    if (!ListenerUtil.mutListener.listen(10863)) {
                                        s.setType(ReportConstants.DYNAMIC_TRANSIT_SERVICE_STOP);
                                    }
                                }
                            }
                        }
                    }
                }
                return true;
            }
        }
        if (!ListenerUtil.mutListener.listen(10869)) {
            // Add our own transit services if none have been found
            if (!stopProblemFound) {
                Service s1 = new Service(context.getString(R.string.ri_service_stop), ReportConstants.STATIC_TRANSIT_SERVICE_STOP);
                if (!ListenerUtil.mutListener.listen(10867)) {
                    s1.setGroup(ReportConstants.ISSUE_GROUP_TRANSIT);
                }
                if (!ListenerUtil.mutListener.listen(10868)) {
                    serviceList.add(s1);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(10872)) {
            if (!tripProblemFound) {
                Service s2 = new Service(context.getString(R.string.ri_service_trip), ReportConstants.STATIC_TRANSIT_SERVICE_TRIP);
                if (!ListenerUtil.mutListener.listen(10870)) {
                    s2.setGroup(ReportConstants.ISSUE_GROUP_TRANSIT);
                }
                if (!ListenerUtil.mutListener.listen(10871)) {
                    serviceList.add(s2);
                }
            }
        }
        return false;
    }

    /**
     * This method looks at the given text and predefined transit keywords (e.g., groups, keywords,
     * and service name), and tries to determine if the this is a transit stop service (i.e., stop
     * problem)
     *
     * @param text text to search for transit stop service match
     * @return true if the text is "transit stop-related"
     */
    public static boolean isTransitStopServiceByText(String text) {
        String[] transitKeywords = Application.get().getResources().getStringArray(R.array.report_stop_transit_category_keywords);
        if (!ListenerUtil.mutListener.listen(10875)) {
            {
                long _loopCounter145 = 0;
                for (String keyword : transitKeywords) {
                    ListenerUtil.loopListener.listen("_loopCounter145", ++_loopCounter145);
                    if (!ListenerUtil.mutListener.listen(10874)) {
                        if ((ListenerUtil.mutListener.listen(10873) ? (text != null || text.toLowerCase().contains(keyword)) : (text != null && text.toLowerCase().contains(keyword)))) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    /**
     * This method looks at the given text and predefined transit keywords (e.g., groups, keywords,
     * and service name), and tries to determine if the this is a transit trip service (i.e., trip
     * problem)
     *
     * @param text text to search for transit trip service match
     * @return true if the text is "transit trip-related"
     */
    public static boolean isTransitTripServiceByText(String text) {
        String[] transitKeywords = Application.get().getResources().getStringArray(R.array.report_trip_transit_category_keywords);
        if (!ListenerUtil.mutListener.listen(10878)) {
            {
                long _loopCounter146 = 0;
                for (String keyword : transitKeywords) {
                    ListenerUtil.loopListener.listen("_loopCounter146", ++_loopCounter146);
                    if (!ListenerUtil.mutListener.listen(10877)) {
                        if ((ListenerUtil.mutListener.listen(10876) ? (text != null || text.toLowerCase().contains(keyword)) : (text != null && text.toLowerCase().contains(keyword)))) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    /**
     * @param type Service type
     * @return true if it is a transit stop service
     */
    public static boolean isTransitStopServiceByType(String type) {
        return (ListenerUtil.mutListener.listen(10879) ? (ReportConstants.DYNAMIC_TRANSIT_SERVICE_STOP.equals(type) && ReportConstants.STATIC_TRANSIT_SERVICE_STOP.equals(type)) : (ReportConstants.DYNAMIC_TRANSIT_SERVICE_STOP.equals(type) || ReportConstants.STATIC_TRANSIT_SERVICE_STOP.equals(type)));
    }

    /**
     * @param type Service type
     * @return true if it is a transit trip service
     */
    public static boolean isTransitTripServiceByType(String type) {
        return (ListenerUtil.mutListener.listen(10880) ? (ReportConstants.DYNAMIC_TRANSIT_SERVICE_TRIP.equals(type) && ReportConstants.STATIC_TRANSIT_SERVICE_TRIP.equals(type)) : (ReportConstants.DYNAMIC_TRANSIT_SERVICE_TRIP.equals(type) || ReportConstants.STATIC_TRANSIT_SERVICE_TRIP.equals(type)));
    }

    /**
     * @param type Service type
     * @return true if it is a transit service
     */
    public static boolean isTransitServiceByType(String type) {
        return (ListenerUtil.mutListener.listen(10881) ? (isTransitStopServiceByType(type) && isTransitTripServiceByType(type)) : (isTransitStopServiceByType(type) || isTransitTripServiceByType(type)));
    }

    /**
     * @param type Service type
     * @return true if it is a transit service and it is coming from open311 endpoint
     */
    public static boolean isTransitOpen311ServiceByType(String type) {
        return (ListenerUtil.mutListener.listen(10882) ? (ReportConstants.DYNAMIC_TRANSIT_SERVICE_TRIP.equals(type) && ReportConstants.DYNAMIC_TRANSIT_SERVICE_STOP.equals(type)) : (ReportConstants.DYNAMIC_TRANSIT_SERVICE_TRIP.equals(type) || ReportConstants.DYNAMIC_TRANSIT_SERVICE_STOP.equals(type)));
    }

    /**
     * This method determines if the given dynamic open311 field is for stop id.
     *
     * @param desc field description
     * @return true if the field description matches at least two keywords
     */
    public static boolean isStopIdField(String desc) {
        String[] stopIdKeywords = Application.get().getResources().getStringArray(R.array.report_stop_id_field_keywords);
        boolean didMatch = false;
        if (!ListenerUtil.mutListener.listen(10887)) {
            {
                long _loopCounter147 = 0;
                for (String keyword : stopIdKeywords) {
                    ListenerUtil.loopListener.listen("_loopCounter147", ++_loopCounter147);
                    if (!ListenerUtil.mutListener.listen(10886)) {
                        if ((ListenerUtil.mutListener.listen(10883) ? (desc != null || desc.toLowerCase().contains(keyword)) : (desc != null && desc.toLowerCase().contains(keyword)))) {
                            if (!ListenerUtil.mutListener.listen(10885)) {
                                if (didMatch) {
                                    // if this is the second matched keyword then return true
                                    return true;
                                } else {
                                    if (!ListenerUtil.mutListener.listen(10884)) {
                                        didMatch = true;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }
}
