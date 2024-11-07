/*
 * Copyright 2012 University of South Florida
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *        http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and 
 * limitations under the License.
 */
package org.onebusaway.android.directions.util;

import android.content.Context;
import android.preference.PreferenceManager;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.text.style.StrikethroughSpan;
import android.util.Log;
import org.onebusaway.android.R;
import org.onebusaway.android.util.ArrivalInfoUtils;
import org.onebusaway.android.util.PreferenceUtils;
import org.opentripplanner.api.model.Itinerary;
import org.opentripplanner.api.model.Leg;
import org.opentripplanner.routing.core.TraverseMode;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class ConversionUtils {

    private static final String TAG = "ConversionUtils";

    private static final double FEET_PER_METER = 3.281;

    /**
     * Given a date string from an OTP server, parse it into a java Date.
     *
     * @param text string from OTP
     * @return parsed date object, or null if there is an error with parsing.
     */
    public static Date parseOtpDate(String text) {
        try {
            long ms = Long.parseLong(text);
            return new Date(ms);
        } catch (NumberFormatException ex) {
            if (!ListenerUtil.mutListener.listen(5758)) {
                Log.e(TAG, "Error processing OTP response time text: " + text);
            }
            return null;
        }
    }

    /**
     * Given start time and end time strings, compute the delta between them.
     * Strings should be in the OTP server return format, specified in OTPConstants
     *
     * @param startTimeText start time
     * @param endTimeText end time
     * @param applicationContext context to look up resources
     * @return duration
     */
    public static double getDuration(String startTimeText, String endTimeText, Context applicationContext) {
        double duration = 0;
        Date startTime = parseOtpDate(startTimeText);
        Date endTime = parseOtpDate(endTimeText);
        if (!ListenerUtil.mutListener.listen(5775)) {
            if ((ListenerUtil.mutListener.listen(5759) ? (startTime != null || endTime != null) : (startTime != null && endTime != null))) {
                if (!ListenerUtil.mutListener.listen(5774)) {
                    if (PreferenceManager.getDefaultSharedPreferences(applicationContext).getInt(OTPConstants.PREFERENCE_KEY_API_VERSION, OTPConstants.API_VERSION_V1) == OTPConstants.API_VERSION_V1) {
                        if (!ListenerUtil.mutListener.listen(5773)) {
                            duration = ((ListenerUtil.mutListener.listen(5772) ? (endTime.getTime() % startTime.getTime()) : (ListenerUtil.mutListener.listen(5771) ? (endTime.getTime() / startTime.getTime()) : (ListenerUtil.mutListener.listen(5770) ? (endTime.getTime() * startTime.getTime()) : (ListenerUtil.mutListener.listen(5769) ? (endTime.getTime() + startTime.getTime()) : (endTime.getTime() - startTime.getTime()))))));
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(5768)) {
                            duration = (ListenerUtil.mutListener.listen(5767) ? (((ListenerUtil.mutListener.listen(5763) ? (endTime.getTime() % startTime.getTime()) : (ListenerUtil.mutListener.listen(5762) ? (endTime.getTime() / startTime.getTime()) : (ListenerUtil.mutListener.listen(5761) ? (endTime.getTime() * startTime.getTime()) : (ListenerUtil.mutListener.listen(5760) ? (endTime.getTime() + startTime.getTime()) : (endTime.getTime() - startTime.getTime())))))) % 1000) : (ListenerUtil.mutListener.listen(5766) ? (((ListenerUtil.mutListener.listen(5763) ? (endTime.getTime() % startTime.getTime()) : (ListenerUtil.mutListener.listen(5762) ? (endTime.getTime() / startTime.getTime()) : (ListenerUtil.mutListener.listen(5761) ? (endTime.getTime() * startTime.getTime()) : (ListenerUtil.mutListener.listen(5760) ? (endTime.getTime() + startTime.getTime()) : (endTime.getTime() - startTime.getTime())))))) * 1000) : (ListenerUtil.mutListener.listen(5765) ? (((ListenerUtil.mutListener.listen(5763) ? (endTime.getTime() % startTime.getTime()) : (ListenerUtil.mutListener.listen(5762) ? (endTime.getTime() / startTime.getTime()) : (ListenerUtil.mutListener.listen(5761) ? (endTime.getTime() * startTime.getTime()) : (ListenerUtil.mutListener.listen(5760) ? (endTime.getTime() + startTime.getTime()) : (endTime.getTime() - startTime.getTime())))))) - 1000) : (ListenerUtil.mutListener.listen(5764) ? (((ListenerUtil.mutListener.listen(5763) ? (endTime.getTime() % startTime.getTime()) : (ListenerUtil.mutListener.listen(5762) ? (endTime.getTime() / startTime.getTime()) : (ListenerUtil.mutListener.listen(5761) ? (endTime.getTime() * startTime.getTime()) : (ListenerUtil.mutListener.listen(5760) ? (endTime.getTime() + startTime.getTime()) : (endTime.getTime() - startTime.getTime())))))) + 1000) : (((ListenerUtil.mutListener.listen(5763) ? (endTime.getTime() % startTime.getTime()) : (ListenerUtil.mutListener.listen(5762) ? (endTime.getTime() / startTime.getTime()) : (ListenerUtil.mutListener.listen(5761) ? (endTime.getTime() * startTime.getTime()) : (ListenerUtil.mutListener.listen(5760) ? (endTime.getTime() + startTime.getTime()) : (endTime.getTime() - startTime.getTime())))))) / 1000)))));
                        }
                    }
                }
            }
        }
        return duration;
    }

    /**
     * Return a formatted String for a distance. Should be in proper units according to
     * preferences (either metric or imperial).
     *
     * @param meters distance in meters
     * @param applicationContext context to look up resources
     * @return formatted string of distance
     */
    public static String getFormattedDistance(Double meters, Context applicationContext) {
        String text = "";
        if (!ListenerUtil.mutListener.listen(5806)) {
            if (PreferenceUtils.getUnitsAreMetricFromPreferences(applicationContext)) {
                if (!ListenerUtil.mutListener.listen(5805)) {
                    if ((ListenerUtil.mutListener.listen(5797) ? (meters >= 1000) : (ListenerUtil.mutListener.listen(5796) ? (meters <= 1000) : (ListenerUtil.mutListener.listen(5795) ? (meters > 1000) : (ListenerUtil.mutListener.listen(5794) ? (meters != 1000) : (ListenerUtil.mutListener.listen(5793) ? (meters == 1000) : (meters < 1000))))))) {
                        if (!ListenerUtil.mutListener.listen(5804)) {
                            text += String.format(OTPConstants.FORMAT_DISTANCE_METERS, meters) + " " + applicationContext.getResources().getString(R.string.meters_abbreviation);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(5802)) {
                            meters = (ListenerUtil.mutListener.listen(5801) ? (meters % 1000) : (ListenerUtil.mutListener.listen(5800) ? (meters * 1000) : (ListenerUtil.mutListener.listen(5799) ? (meters - 1000) : (ListenerUtil.mutListener.listen(5798) ? (meters + 1000) : (meters / 1000)))));
                        }
                        if (!ListenerUtil.mutListener.listen(5803)) {
                            text += String.format(OTPConstants.FORMAT_DISTANCE_KILOMETERS, meters) + " " + applicationContext.getResources().getString(R.string.kilometers_abbreviation);
                        }
                    }
                }
            } else {
                double feet = (ListenerUtil.mutListener.listen(5779) ? (meters % 3.281) : (ListenerUtil.mutListener.listen(5778) ? (meters / 3.281) : (ListenerUtil.mutListener.listen(5777) ? (meters - 3.281) : (ListenerUtil.mutListener.listen(5776) ? (meters + 3.281) : (meters * 3.281)))));
                if (!ListenerUtil.mutListener.listen(5792)) {
                    if ((ListenerUtil.mutListener.listen(5784) ? (feet >= 1000) : (ListenerUtil.mutListener.listen(5783) ? (feet <= 1000) : (ListenerUtil.mutListener.listen(5782) ? (feet > 1000) : (ListenerUtil.mutListener.listen(5781) ? (feet != 1000) : (ListenerUtil.mutListener.listen(5780) ? (feet == 1000) : (feet < 1000))))))) {
                        if (!ListenerUtil.mutListener.listen(5791)) {
                            text += String.format(OTPConstants.FORMAT_DISTANCE_METERS, feet) + " " + applicationContext.getResources().getString(R.string.feet_abbreviation);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(5789)) {
                            feet = (ListenerUtil.mutListener.listen(5788) ? (feet % 5280) : (ListenerUtil.mutListener.listen(5787) ? (feet * 5280) : (ListenerUtil.mutListener.listen(5786) ? (feet - 5280) : (ListenerUtil.mutListener.listen(5785) ? (feet + 5280) : (feet / 5280)))));
                        }
                        if (!ListenerUtil.mutListener.listen(5790)) {
                            text += String.format(OTPConstants.FORMAT_DISTANCE_KILOMETERS, feet) + " " + applicationContext.getResources().getString(R.string.miles_abbreviation);
                        }
                    }
                }
            }
        }
        return text;
    }

    /**
     * Get a formatted string for a duration.
     *
     * @param sec duration in seconds
     * @param applicationContext context to look up resources
     * @return formatted duration string
     */
    public static String getFormattedDurationText(long sec, Context applicationContext) {
        String text = "";
        long h = (ListenerUtil.mutListener.listen(5810) ? (sec % 3600) : (ListenerUtil.mutListener.listen(5809) ? (sec * 3600) : (ListenerUtil.mutListener.listen(5808) ? (sec - 3600) : (ListenerUtil.mutListener.listen(5807) ? (sec + 3600) : (sec / 3600)))));
        if (!ListenerUtil.mutListener.listen(5816)) {
            if ((ListenerUtil.mutListener.listen(5815) ? (h <= 24) : (ListenerUtil.mutListener.listen(5814) ? (h > 24) : (ListenerUtil.mutListener.listen(5813) ? (h < 24) : (ListenerUtil.mutListener.listen(5812) ? (h != 24) : (ListenerUtil.mutListener.listen(5811) ? (h == 24) : (h >= 24))))))) {
                return null;
            }
        }
        long m = (ListenerUtil.mutListener.listen(5824) ? (((ListenerUtil.mutListener.listen(5820) ? (sec / 3600) : (ListenerUtil.mutListener.listen(5819) ? (sec * 3600) : (ListenerUtil.mutListener.listen(5818) ? (sec - 3600) : (ListenerUtil.mutListener.listen(5817) ? (sec + 3600) : (sec % 3600)))))) % 60) : (ListenerUtil.mutListener.listen(5823) ? (((ListenerUtil.mutListener.listen(5820) ? (sec / 3600) : (ListenerUtil.mutListener.listen(5819) ? (sec * 3600) : (ListenerUtil.mutListener.listen(5818) ? (sec - 3600) : (ListenerUtil.mutListener.listen(5817) ? (sec + 3600) : (sec % 3600)))))) * 60) : (ListenerUtil.mutListener.listen(5822) ? (((ListenerUtil.mutListener.listen(5820) ? (sec / 3600) : (ListenerUtil.mutListener.listen(5819) ? (sec * 3600) : (ListenerUtil.mutListener.listen(5818) ? (sec - 3600) : (ListenerUtil.mutListener.listen(5817) ? (sec + 3600) : (sec % 3600)))))) - 60) : (ListenerUtil.mutListener.listen(5821) ? (((ListenerUtil.mutListener.listen(5820) ? (sec / 3600) : (ListenerUtil.mutListener.listen(5819) ? (sec * 3600) : (ListenerUtil.mutListener.listen(5818) ? (sec - 3600) : (ListenerUtil.mutListener.listen(5817) ? (sec + 3600) : (sec % 3600)))))) + 60) : (((ListenerUtil.mutListener.listen(5820) ? (sec / 3600) : (ListenerUtil.mutListener.listen(5819) ? (sec * 3600) : (ListenerUtil.mutListener.listen(5818) ? (sec - 3600) : (ListenerUtil.mutListener.listen(5817) ? (sec + 3600) : (sec % 3600)))))) / 60)))));
        long s = (ListenerUtil.mutListener.listen(5832) ? (((ListenerUtil.mutListener.listen(5828) ? (sec / 3600) : (ListenerUtil.mutListener.listen(5827) ? (sec * 3600) : (ListenerUtil.mutListener.listen(5826) ? (sec - 3600) : (ListenerUtil.mutListener.listen(5825) ? (sec + 3600) : (sec % 3600)))))) / 60) : (ListenerUtil.mutListener.listen(5831) ? (((ListenerUtil.mutListener.listen(5828) ? (sec / 3600) : (ListenerUtil.mutListener.listen(5827) ? (sec * 3600) : (ListenerUtil.mutListener.listen(5826) ? (sec - 3600) : (ListenerUtil.mutListener.listen(5825) ? (sec + 3600) : (sec % 3600)))))) * 60) : (ListenerUtil.mutListener.listen(5830) ? (((ListenerUtil.mutListener.listen(5828) ? (sec / 3600) : (ListenerUtil.mutListener.listen(5827) ? (sec * 3600) : (ListenerUtil.mutListener.listen(5826) ? (sec - 3600) : (ListenerUtil.mutListener.listen(5825) ? (sec + 3600) : (sec % 3600)))))) - 60) : (ListenerUtil.mutListener.listen(5829) ? (((ListenerUtil.mutListener.listen(5828) ? (sec / 3600) : (ListenerUtil.mutListener.listen(5827) ? (sec * 3600) : (ListenerUtil.mutListener.listen(5826) ? (sec - 3600) : (ListenerUtil.mutListener.listen(5825) ? (sec + 3600) : (sec % 3600)))))) + 60) : (((ListenerUtil.mutListener.listen(5828) ? (sec / 3600) : (ListenerUtil.mutListener.listen(5827) ? (sec * 3600) : (ListenerUtil.mutListener.listen(5826) ? (sec - 3600) : (ListenerUtil.mutListener.listen(5825) ? (sec + 3600) : (sec % 3600)))))) % 60)))));
        if (!ListenerUtil.mutListener.listen(5839)) {
            if ((ListenerUtil.mutListener.listen(5837) ? (h >= 0) : (ListenerUtil.mutListener.listen(5836) ? (h <= 0) : (ListenerUtil.mutListener.listen(5835) ? (h < 0) : (ListenerUtil.mutListener.listen(5834) ? (h != 0) : (ListenerUtil.mutListener.listen(5833) ? (h == 0) : (h > 0))))))) {
                if (!ListenerUtil.mutListener.listen(5838)) {
                    text += Long.toString(h) + applicationContext.getResources().getString(R.string.hours_abbreviation);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(5840)) {
            text += Long.toString(m) + applicationContext.getResources().getString(R.string.minutes_abbreviation);
        }
        if (!ListenerUtil.mutListener.listen(5841)) {
            text += Long.toString(s) + applicationContext.getResources().getString(R.string.seconds_abbrevation);
        }
        return text;
    }

    /**
     * Get duration text but disallow "seconds" units.
     *
     * @param sec duration in seconds
     * @param longFormat true for long units ("minutes"), false for short units ("min")
     * @param applicationContext context to look up resources
     * @return formatted duration text
     */
    public static String getFormattedDurationTextNoSeconds(long sec, boolean longFormat, Context applicationContext) {
        String text = "";
        long h = (ListenerUtil.mutListener.listen(5845) ? (sec % 3600) : (ListenerUtil.mutListener.listen(5844) ? (sec * 3600) : (ListenerUtil.mutListener.listen(5843) ? (sec - 3600) : (ListenerUtil.mutListener.listen(5842) ? (sec + 3600) : (sec / 3600)))));
        long m = (ListenerUtil.mutListener.listen(5853) ? (((ListenerUtil.mutListener.listen(5849) ? (sec / 3600) : (ListenerUtil.mutListener.listen(5848) ? (sec * 3600) : (ListenerUtil.mutListener.listen(5847) ? (sec - 3600) : (ListenerUtil.mutListener.listen(5846) ? (sec + 3600) : (sec % 3600)))))) % 60) : (ListenerUtil.mutListener.listen(5852) ? (((ListenerUtil.mutListener.listen(5849) ? (sec / 3600) : (ListenerUtil.mutListener.listen(5848) ? (sec * 3600) : (ListenerUtil.mutListener.listen(5847) ? (sec - 3600) : (ListenerUtil.mutListener.listen(5846) ? (sec + 3600) : (sec % 3600)))))) * 60) : (ListenerUtil.mutListener.listen(5851) ? (((ListenerUtil.mutListener.listen(5849) ? (sec / 3600) : (ListenerUtil.mutListener.listen(5848) ? (sec * 3600) : (ListenerUtil.mutListener.listen(5847) ? (sec - 3600) : (ListenerUtil.mutListener.listen(5846) ? (sec + 3600) : (sec % 3600)))))) - 60) : (ListenerUtil.mutListener.listen(5850) ? (((ListenerUtil.mutListener.listen(5849) ? (sec / 3600) : (ListenerUtil.mutListener.listen(5848) ? (sec * 3600) : (ListenerUtil.mutListener.listen(5847) ? (sec - 3600) : (ListenerUtil.mutListener.listen(5846) ? (sec + 3600) : (sec % 3600)))))) + 60) : (((ListenerUtil.mutListener.listen(5849) ? (sec / 3600) : (ListenerUtil.mutListener.listen(5848) ? (sec * 3600) : (ListenerUtil.mutListener.listen(5847) ? (sec - 3600) : (ListenerUtil.mutListener.listen(5846) ? (sec + 3600) : (sec % 3600)))))) / 60)))));
        String longMinutes = applicationContext.getResources().getString(R.string.minutes_full);
        String longMinutesSingular = applicationContext.getResources().getString(R.string.minutes_abbreviation);
        String shortMinutes = applicationContext.getResources().getString(R.string.minutes_abbreviation);
        if (!ListenerUtil.mutListener.listen(5856)) {
            if (longFormat) {
                if (!ListenerUtil.mutListener.listen(5854)) {
                    longMinutes = applicationContext.getResources().getString(R.string.minutes_full);
                }
                if (!ListenerUtil.mutListener.listen(5855)) {
                    longMinutesSingular = applicationContext.getResources().getString(R.string.minute_singular);
                }
            }
        }
        String shortHours = applicationContext.getResources().getString(R.string.hours_abbreviation);
        if (!ListenerUtil.mutListener.listen(5884)) {
            if ((ListenerUtil.mutListener.listen(5861) ? (h >= 0) : (ListenerUtil.mutListener.listen(5860) ? (h <= 0) : (ListenerUtil.mutListener.listen(5859) ? (h < 0) : (ListenerUtil.mutListener.listen(5858) ? (h != 0) : (ListenerUtil.mutListener.listen(5857) ? (h == 0) : (h > 0))))))) {
                if (!ListenerUtil.mutListener.listen(5882)) {
                    text += Long.toString(h) + shortHours;
                }
                if (!ListenerUtil.mutListener.listen(5883)) {
                    text += " " + Long.toString(m) + shortMinutes;
                }
            } else {
                if (!ListenerUtil.mutListener.listen(5881)) {
                    if ((ListenerUtil.mutListener.listen(5866) ? (m >= 0) : (ListenerUtil.mutListener.listen(5865) ? (m <= 0) : (ListenerUtil.mutListener.listen(5864) ? (m > 0) : (ListenerUtil.mutListener.listen(5863) ? (m < 0) : (ListenerUtil.mutListener.listen(5862) ? (m != 0) : (m == 0))))))) {
                        if (!ListenerUtil.mutListener.listen(5880)) {
                            text += "< 1 " + longMinutes;
                        }
                    } else if ((ListenerUtil.mutListener.listen(5877) ? ((ListenerUtil.mutListener.listen(5871) ? (m >= 1) : (ListenerUtil.mutListener.listen(5870) ? (m <= 1) : (ListenerUtil.mutListener.listen(5869) ? (m > 1) : (ListenerUtil.mutListener.listen(5868) ? (m < 1) : (ListenerUtil.mutListener.listen(5867) ? (m != 1) : (m == 1)))))) && (ListenerUtil.mutListener.listen(5876) ? (m >= -1) : (ListenerUtil.mutListener.listen(5875) ? (m <= -1) : (ListenerUtil.mutListener.listen(5874) ? (m > -1) : (ListenerUtil.mutListener.listen(5873) ? (m < -1) : (ListenerUtil.mutListener.listen(5872) ? (m != -1) : (m == -1))))))) : ((ListenerUtil.mutListener.listen(5871) ? (m >= 1) : (ListenerUtil.mutListener.listen(5870) ? (m <= 1) : (ListenerUtil.mutListener.listen(5869) ? (m > 1) : (ListenerUtil.mutListener.listen(5868) ? (m < 1) : (ListenerUtil.mutListener.listen(5867) ? (m != 1) : (m == 1)))))) || (ListenerUtil.mutListener.listen(5876) ? (m >= -1) : (ListenerUtil.mutListener.listen(5875) ? (m <= -1) : (ListenerUtil.mutListener.listen(5874) ? (m > -1) : (ListenerUtil.mutListener.listen(5873) ? (m < -1) : (ListenerUtil.mutListener.listen(5872) ? (m != -1) : (m == -1))))))))) {
                        if (!ListenerUtil.mutListener.listen(5879)) {
                            text += Long.toString(m) + " " + longMinutesSingular;
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(5878)) {
                            text += Long.toString(m) + " " + longMinutes;
                        }
                    }
                }
            }
        }
        return text;
    }

    public static List<Itinerary> fixTimezoneOffsets(List<Itinerary> itineraries, boolean useDeviceTimezone) {
        int agencyTimeZoneOffset = 0;
        boolean containsTransitLegs = false;
        if ((ListenerUtil.mutListener.listen(5885) ? ((itineraries != null) || !itineraries.isEmpty()) : ((itineraries != null) && !itineraries.isEmpty()))) {
            ArrayList<Itinerary> itinerariesFixed = new ArrayList<Itinerary>(itineraries);
            if (!ListenerUtil.mutListener.listen(5893)) {
                {
                    long _loopCounter56 = 0;
                    for (Itinerary it : itinerariesFixed) {
                        ListenerUtil.loopListener.listen("_loopCounter56", ++_loopCounter56);
                        if (!ListenerUtil.mutListener.listen(5892)) {
                            {
                                long _loopCounter55 = 0;
                                for (Leg leg : it.legs) {
                                    ListenerUtil.loopListener.listen("_loopCounter55", ++_loopCounter55);
                                    if (!ListenerUtil.mutListener.listen(5888)) {
                                        if ((ListenerUtil.mutListener.listen(5886) ? ((TraverseMode.valueOf((String) leg.mode)).isTransit() || !containsTransitLegs) : ((TraverseMode.valueOf((String) leg.mode)).isTransit() && !containsTransitLegs))) {
                                            if (!ListenerUtil.mutListener.listen(5887)) {
                                                containsTransitLegs = true;
                                            }
                                        }
                                    }
                                    if (!ListenerUtil.mutListener.listen(5891)) {
                                        if (leg.agencyTimeZoneOffset != 0) {
                                            if (!ListenerUtil.mutListener.listen(5889)) {
                                                agencyTimeZoneOffset = leg.agencyTimeZoneOffset;
                                            }
                                            if (!ListenerUtil.mutListener.listen(5890)) {
                                                // If agencyTimeZoneOffset is different from 0, route contains transit legs
                                                containsTransitLegs = true;
                                            }
                                            break;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(5896)) {
                if ((ListenerUtil.mutListener.listen(5894) ? (useDeviceTimezone && !containsTransitLegs) : (useDeviceTimezone || !containsTransitLegs))) {
                    if (!ListenerUtil.mutListener.listen(5895)) {
                        agencyTimeZoneOffset = TimeZone.getDefault().getOffset(Long.parseLong(itinerariesFixed.get(0).startTime));
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(5905)) {
                if ((ListenerUtil.mutListener.listen(5901) ? (agencyTimeZoneOffset >= 0) : (ListenerUtil.mutListener.listen(5900) ? (agencyTimeZoneOffset <= 0) : (ListenerUtil.mutListener.listen(5899) ? (agencyTimeZoneOffset > 0) : (ListenerUtil.mutListener.listen(5898) ? (agencyTimeZoneOffset < 0) : (ListenerUtil.mutListener.listen(5897) ? (agencyTimeZoneOffset == 0) : (agencyTimeZoneOffset != 0))))))) {
                    if (!ListenerUtil.mutListener.listen(5904)) {
                        {
                            long _loopCounter58 = 0;
                            for (Itinerary it : itinerariesFixed) {
                                ListenerUtil.loopListener.listen("_loopCounter58", ++_loopCounter58);
                                if (!ListenerUtil.mutListener.listen(5903)) {
                                    {
                                        long _loopCounter57 = 0;
                                        for (Leg leg : it.legs) {
                                            ListenerUtil.loopListener.listen("_loopCounter57", ++_loopCounter57);
                                            if (!ListenerUtil.mutListener.listen(5902)) {
                                                leg.agencyTimeZoneOffset = agencyTimeZoneOffset;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            return itinerariesFixed;
        } else {
            return itineraries;
        }
    }

    public static CharSequence getTimeWithContext(Context applicationContext, int offsetGMT, long time, boolean inLine) {
        return getTimeWithContext(applicationContext, offsetGMT, time, inLine, -1);
    }

    public static CharSequence getTimeWithContext(Context applicationContext, int offsetGMT, long time, boolean inLine, int color) {
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
        DateFormat timeFormat = android.text.format.DateFormat.getTimeFormat(applicationContext);
        DateFormat dateFormat = android.text.format.DateFormat.getDateFormat(applicationContext);
        if (!ListenerUtil.mutListener.listen(5906)) {
            timeFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        }
        if (!ListenerUtil.mutListener.listen(5907)) {
            dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        }
        if (!ListenerUtil.mutListener.listen(5908)) {
            cal.setTimeInMillis(time);
        }
        String noDeviceTimezoneNote = "";
        if (!ListenerUtil.mutListener.listen(5926)) {
            if ((ListenerUtil.mutListener.listen(5913) ? (offsetGMT >= TimeZone.getDefault().getOffset(time)) : (ListenerUtil.mutListener.listen(5912) ? (offsetGMT <= TimeZone.getDefault().getOffset(time)) : (ListenerUtil.mutListener.listen(5911) ? (offsetGMT > TimeZone.getDefault().getOffset(time)) : (ListenerUtil.mutListener.listen(5910) ? (offsetGMT < TimeZone.getDefault().getOffset(time)) : (ListenerUtil.mutListener.listen(5909) ? (offsetGMT == TimeZone.getDefault().getOffset(time)) : (offsetGMT != TimeZone.getDefault().getOffset(time)))))))) {
                if (!ListenerUtil.mutListener.listen(5914)) {
                    noDeviceTimezoneNote = "GMT";
                }
                if (!ListenerUtil.mutListener.listen(5925)) {
                    if ((ListenerUtil.mutListener.listen(5919) ? (offsetGMT >= 0) : (ListenerUtil.mutListener.listen(5918) ? (offsetGMT <= 0) : (ListenerUtil.mutListener.listen(5917) ? (offsetGMT > 0) : (ListenerUtil.mutListener.listen(5916) ? (offsetGMT < 0) : (ListenerUtil.mutListener.listen(5915) ? (offsetGMT == 0) : (offsetGMT != 0))))))) {
                        if (!ListenerUtil.mutListener.listen(5924)) {
                            noDeviceTimezoneNote += (ListenerUtil.mutListener.listen(5923) ? (offsetGMT % 3600000) : (ListenerUtil.mutListener.listen(5922) ? (offsetGMT * 3600000) : (ListenerUtil.mutListener.listen(5921) ? (offsetGMT - 3600000) : (ListenerUtil.mutListener.listen(5920) ? (offsetGMT + 3600000) : (offsetGMT / 3600000)))));
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(5927)) {
            cal.add(Calendar.MILLISECOND, offsetGMT);
        }
        if (!ListenerUtil.mutListener.listen(5934)) {
            if ((ListenerUtil.mutListener.listen(5932) ? (cal.get(Calendar.SECOND) <= 30) : (ListenerUtil.mutListener.listen(5931) ? (cal.get(Calendar.SECOND) > 30) : (ListenerUtil.mutListener.listen(5930) ? (cal.get(Calendar.SECOND) < 30) : (ListenerUtil.mutListener.listen(5929) ? (cal.get(Calendar.SECOND) != 30) : (ListenerUtil.mutListener.listen(5928) ? (cal.get(Calendar.SECOND) == 30) : (cal.get(Calendar.SECOND) >= 30))))))) {
                if (!ListenerUtil.mutListener.listen(5933)) {
                    cal.add(Calendar.MINUTE, 1);
                }
            }
        }
        SpannableString spannableTime = new SpannableString(timeFormat.format(cal.getTime()));
        if (!ListenerUtil.mutListener.listen(5941)) {
            if ((ListenerUtil.mutListener.listen(5939) ? (color >= -1) : (ListenerUtil.mutListener.listen(5938) ? (color <= -1) : (ListenerUtil.mutListener.listen(5937) ? (color > -1) : (ListenerUtil.mutListener.listen(5936) ? (color < -1) : (ListenerUtil.mutListener.listen(5935) ? (color == -1) : (color != -1))))))) {
                if (!ListenerUtil.mutListener.listen(5940)) {
                    spannableTime.setSpan(new ForegroundColorSpan(color), 0, spannableTime.length(), 0);
                }
            }
        }
        if (inLine) {
            if (ConversionUtils.isToday(cal)) {
                return TextUtils.concat(" ", applicationContext.getResources().getString(R.string.time_connector_before_time), " ", spannableTime, " ", noDeviceTimezoneNote);
            } else if (ConversionUtils.isTomorrow(cal)) {
                return TextUtils.concat(" ", applicationContext.getResources().getString(R.string.time_connector_next_day), " ", applicationContext.getResources().getString(R.string.time_connector_before_time), " ", spannableTime, " ", noDeviceTimezoneNote);
            } else {
                return TextUtils.concat(" ", applicationContext.getResources().getString(R.string.time_connector_before_date), " ", dateFormat.format(cal.getTime()), " ", applicationContext.getResources().getString(R.string.time_connector_before_time), " ", spannableTime, " ", noDeviceTimezoneNote);
            }
        } else {
            if (ConversionUtils.isToday(cal)) {
                return TextUtils.concat(spannableTime, " ", noDeviceTimezoneNote);
            } else if (ConversionUtils.isTomorrow(cal)) {
                return TextUtils.concat(" ", spannableTime, ", ", applicationContext.getResources().getString(R.string.time_connector_next_day), " ", noDeviceTimezoneNote);
            } else {
                return TextUtils.concat(spannableTime, ", ", dateFormat.format(cal.getTime()), " ", noDeviceTimezoneNote);
            }
        }
    }

    public static CharSequence getTimeUpdated(Context applicationContext, int offsetGMT, long oldTime, long newTime) {
        Calendar calOldTime = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
        Calendar calNewTime = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
        DateFormat timeFormat = android.text.format.DateFormat.getTimeFormat(applicationContext);
        DateFormat dateFormat = android.text.format.DateFormat.getDateFormat(applicationContext);
        if (!ListenerUtil.mutListener.listen(5942)) {
            timeFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        }
        if (!ListenerUtil.mutListener.listen(5943)) {
            dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        }
        CharSequence timeUpdatedString;
        CharSequence beforeDateString = "", beforeTimeString = "", newDateString = "", timezone = "";
        SpannableString oldTimeString, oldDateString, newTimeString;
        if (!ListenerUtil.mutListener.listen(5944)) {
            calOldTime.setTimeInMillis(oldTime);
        }
        if (!ListenerUtil.mutListener.listen(5945)) {
            calNewTime.setTimeInMillis(newTime);
        }
        String noDeviceTimezoneNote = "";
        if (!ListenerUtil.mutListener.listen(5963)) {
            if ((ListenerUtil.mutListener.listen(5950) ? (offsetGMT >= TimeZone.getDefault().getOffset(oldTime)) : (ListenerUtil.mutListener.listen(5949) ? (offsetGMT <= TimeZone.getDefault().getOffset(oldTime)) : (ListenerUtil.mutListener.listen(5948) ? (offsetGMT > TimeZone.getDefault().getOffset(oldTime)) : (ListenerUtil.mutListener.listen(5947) ? (offsetGMT < TimeZone.getDefault().getOffset(oldTime)) : (ListenerUtil.mutListener.listen(5946) ? (offsetGMT == TimeZone.getDefault().getOffset(oldTime)) : (offsetGMT != TimeZone.getDefault().getOffset(oldTime)))))))) {
                if (!ListenerUtil.mutListener.listen(5951)) {
                    noDeviceTimezoneNote = "GMT";
                }
                if (!ListenerUtil.mutListener.listen(5962)) {
                    if ((ListenerUtil.mutListener.listen(5956) ? (offsetGMT >= 0) : (ListenerUtil.mutListener.listen(5955) ? (offsetGMT <= 0) : (ListenerUtil.mutListener.listen(5954) ? (offsetGMT > 0) : (ListenerUtil.mutListener.listen(5953) ? (offsetGMT < 0) : (ListenerUtil.mutListener.listen(5952) ? (offsetGMT == 0) : (offsetGMT != 0))))))) {
                        if (!ListenerUtil.mutListener.listen(5961)) {
                            noDeviceTimezoneNote += (ListenerUtil.mutListener.listen(5960) ? (offsetGMT % 3600000) : (ListenerUtil.mutListener.listen(5959) ? (offsetGMT * 3600000) : (ListenerUtil.mutListener.listen(5958) ? (offsetGMT - 3600000) : (ListenerUtil.mutListener.listen(5957) ? (offsetGMT + 3600000) : (offsetGMT / 3600000)))));
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(5964)) {
            calOldTime.add(Calendar.MILLISECOND, offsetGMT);
        }
        if (!ListenerUtil.mutListener.listen(5965)) {
            calNewTime.add(Calendar.MILLISECOND, offsetGMT);
        }
        if (ConversionUtils.isTomorrow(calNewTime)) {
            oldDateString = new SpannableString(applicationContext.getResources().getString(R.string.time_connector_next_day) + " ");
        } else {
            if (!ListenerUtil.mutListener.listen(5966)) {
                beforeDateString = applicationContext.getResources().getString(R.string.time_connector_before_date) + " ";
            }
            oldDateString = new SpannableString(dateFormat.format(calNewTime.getTime()) + " ");
        }
        if (!ListenerUtil.mutListener.listen(5979)) {
            if ((ListenerUtil.mutListener.listen(5971) ? (calNewTime.get(Calendar.DAY_OF_MONTH) >= calOldTime.get(Calendar.DAY_OF_MONTH)) : (ListenerUtil.mutListener.listen(5970) ? (calNewTime.get(Calendar.DAY_OF_MONTH) <= calOldTime.get(Calendar.DAY_OF_MONTH)) : (ListenerUtil.mutListener.listen(5969) ? (calNewTime.get(Calendar.DAY_OF_MONTH) > calOldTime.get(Calendar.DAY_OF_MONTH)) : (ListenerUtil.mutListener.listen(5968) ? (calNewTime.get(Calendar.DAY_OF_MONTH) < calOldTime.get(Calendar.DAY_OF_MONTH)) : (ListenerUtil.mutListener.listen(5967) ? (calNewTime.get(Calendar.DAY_OF_MONTH) == calOldTime.get(Calendar.DAY_OF_MONTH)) : (calNewTime.get(Calendar.DAY_OF_MONTH) != calOldTime.get(Calendar.DAY_OF_MONTH)))))))) {
                if (!ListenerUtil.mutListener.listen(5972)) {
                    beforeDateString = applicationContext.getResources().getString(R.string.time_connector_before_date) + " ";
                }
                if (!ListenerUtil.mutListener.listen(5973)) {
                    newDateString = dateFormat.format(calNewTime.getTime()) + " ";
                }
                if (!ListenerUtil.mutListener.listen(5978)) {
                    oldDateString.setSpan(new StrikethroughSpan(), 0, (ListenerUtil.mutListener.listen(5977) ? (oldDateString.length() % 1) : (ListenerUtil.mutListener.listen(5976) ? (oldDateString.length() / 1) : (ListenerUtil.mutListener.listen(5975) ? (oldDateString.length() * 1) : (ListenerUtil.mutListener.listen(5974) ? (oldDateString.length() + 1) : (oldDateString.length() - 1))))), 0);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(5980)) {
            beforeTimeString = applicationContext.getResources().getString(R.string.time_connector_before_time) + " ";
        }
        if (!ListenerUtil.mutListener.listen(5981)) {
            timezone = noDeviceTimezoneNote;
        }
        int color = applicationContext.getResources().getColor(ArrivalInfoUtils.computeColorFromDeviation((ListenerUtil.mutListener.listen(5985) ? (calNewTime.getTimeInMillis() % calOldTime.getTimeInMillis()) : (ListenerUtil.mutListener.listen(5984) ? (calNewTime.getTimeInMillis() / calOldTime.getTimeInMillis()) : (ListenerUtil.mutListener.listen(5983) ? (calNewTime.getTimeInMillis() * calOldTime.getTimeInMillis()) : (ListenerUtil.mutListener.listen(5982) ? (calNewTime.getTimeInMillis() + calOldTime.getTimeInMillis()) : (calNewTime.getTimeInMillis() - calOldTime.getTimeInMillis())))))));
        newTimeString = new SpannableString(timeFormat.format(calNewTime.getTime()) + " ");
        if (!ListenerUtil.mutListener.listen(5986)) {
            newTimeString.setSpan(new ForegroundColorSpan(color), 0, newTimeString.length(), 0);
        }
        if ((ListenerUtil.mutListener.listen(5997) ? ((ListenerUtil.mutListener.listen(5991) ? (calOldTime.get(Calendar.HOUR_OF_DAY) >= calNewTime.get(Calendar.HOUR_OF_DAY)) : (ListenerUtil.mutListener.listen(5990) ? (calOldTime.get(Calendar.HOUR_OF_DAY) <= calNewTime.get(Calendar.HOUR_OF_DAY)) : (ListenerUtil.mutListener.listen(5989) ? (calOldTime.get(Calendar.HOUR_OF_DAY) > calNewTime.get(Calendar.HOUR_OF_DAY)) : (ListenerUtil.mutListener.listen(5988) ? (calOldTime.get(Calendar.HOUR_OF_DAY) < calNewTime.get(Calendar.HOUR_OF_DAY)) : (ListenerUtil.mutListener.listen(5987) ? (calOldTime.get(Calendar.HOUR_OF_DAY) == calNewTime.get(Calendar.HOUR_OF_DAY)) : (calOldTime.get(Calendar.HOUR_OF_DAY) != calNewTime.get(Calendar.HOUR_OF_DAY))))))) && (ListenerUtil.mutListener.listen(5996) ? (calOldTime.get(Calendar.MINUTE) >= calNewTime.get(Calendar.MINUTE)) : (ListenerUtil.mutListener.listen(5995) ? (calOldTime.get(Calendar.MINUTE) <= calNewTime.get(Calendar.MINUTE)) : (ListenerUtil.mutListener.listen(5994) ? (calOldTime.get(Calendar.MINUTE) > calNewTime.get(Calendar.MINUTE)) : (ListenerUtil.mutListener.listen(5993) ? (calOldTime.get(Calendar.MINUTE) < calNewTime.get(Calendar.MINUTE)) : (ListenerUtil.mutListener.listen(5992) ? (calOldTime.get(Calendar.MINUTE) == calNewTime.get(Calendar.MINUTE)) : (calOldTime.get(Calendar.MINUTE) != calNewTime.get(Calendar.MINUTE)))))))) : ((ListenerUtil.mutListener.listen(5991) ? (calOldTime.get(Calendar.HOUR_OF_DAY) >= calNewTime.get(Calendar.HOUR_OF_DAY)) : (ListenerUtil.mutListener.listen(5990) ? (calOldTime.get(Calendar.HOUR_OF_DAY) <= calNewTime.get(Calendar.HOUR_OF_DAY)) : (ListenerUtil.mutListener.listen(5989) ? (calOldTime.get(Calendar.HOUR_OF_DAY) > calNewTime.get(Calendar.HOUR_OF_DAY)) : (ListenerUtil.mutListener.listen(5988) ? (calOldTime.get(Calendar.HOUR_OF_DAY) < calNewTime.get(Calendar.HOUR_OF_DAY)) : (ListenerUtil.mutListener.listen(5987) ? (calOldTime.get(Calendar.HOUR_OF_DAY) == calNewTime.get(Calendar.HOUR_OF_DAY)) : (calOldTime.get(Calendar.HOUR_OF_DAY) != calNewTime.get(Calendar.HOUR_OF_DAY))))))) || (ListenerUtil.mutListener.listen(5996) ? (calOldTime.get(Calendar.MINUTE) >= calNewTime.get(Calendar.MINUTE)) : (ListenerUtil.mutListener.listen(5995) ? (calOldTime.get(Calendar.MINUTE) <= calNewTime.get(Calendar.MINUTE)) : (ListenerUtil.mutListener.listen(5994) ? (calOldTime.get(Calendar.MINUTE) > calNewTime.get(Calendar.MINUTE)) : (ListenerUtil.mutListener.listen(5993) ? (calOldTime.get(Calendar.MINUTE) < calNewTime.get(Calendar.MINUTE)) : (ListenerUtil.mutListener.listen(5992) ? (calOldTime.get(Calendar.MINUTE) == calNewTime.get(Calendar.MINUTE)) : (calOldTime.get(Calendar.MINUTE) != calNewTime.get(Calendar.MINUTE)))))))))) {
            oldTimeString = new SpannableString(timeFormat.format(calOldTime.getTime()) + " ");
            if (!ListenerUtil.mutListener.listen(6002)) {
                oldTimeString.setSpan(new StrikethroughSpan(), 0, (ListenerUtil.mutListener.listen(6001) ? (oldTimeString.length() % 1) : (ListenerUtil.mutListener.listen(6000) ? (oldTimeString.length() / 1) : (ListenerUtil.mutListener.listen(5999) ? (oldTimeString.length() * 1) : (ListenerUtil.mutListener.listen(5998) ? (oldTimeString.length() + 1) : (oldTimeString.length() - 1))))), 0);
            }
        } else {
            oldTimeString = new SpannableString(" ");
        }
        timeUpdatedString = TextUtils.concat(beforeDateString, newDateString, oldDateString, beforeTimeString, oldTimeString, newTimeString, timezone);
        return timeUpdatedString;
    }

    public static boolean isToday(Calendar cal) {
        Calendar actualTime = Calendar.getInstance();
        return ((ListenerUtil.mutListener.listen(6019) ? ((ListenerUtil.mutListener.listen(6013) ? ((ListenerUtil.mutListener.listen(6007) ? (actualTime.get(Calendar.ERA) >= cal.get(Calendar.ERA)) : (ListenerUtil.mutListener.listen(6006) ? (actualTime.get(Calendar.ERA) <= cal.get(Calendar.ERA)) : (ListenerUtil.mutListener.listen(6005) ? (actualTime.get(Calendar.ERA) > cal.get(Calendar.ERA)) : (ListenerUtil.mutListener.listen(6004) ? (actualTime.get(Calendar.ERA) < cal.get(Calendar.ERA)) : (ListenerUtil.mutListener.listen(6003) ? (actualTime.get(Calendar.ERA) != cal.get(Calendar.ERA)) : (actualTime.get(Calendar.ERA) == cal.get(Calendar.ERA))))))) || (ListenerUtil.mutListener.listen(6012) ? (actualTime.get(Calendar.YEAR) >= cal.get(Calendar.YEAR)) : (ListenerUtil.mutListener.listen(6011) ? (actualTime.get(Calendar.YEAR) <= cal.get(Calendar.YEAR)) : (ListenerUtil.mutListener.listen(6010) ? (actualTime.get(Calendar.YEAR) > cal.get(Calendar.YEAR)) : (ListenerUtil.mutListener.listen(6009) ? (actualTime.get(Calendar.YEAR) < cal.get(Calendar.YEAR)) : (ListenerUtil.mutListener.listen(6008) ? (actualTime.get(Calendar.YEAR) != cal.get(Calendar.YEAR)) : (actualTime.get(Calendar.YEAR) == cal.get(Calendar.YEAR)))))))) : ((ListenerUtil.mutListener.listen(6007) ? (actualTime.get(Calendar.ERA) >= cal.get(Calendar.ERA)) : (ListenerUtil.mutListener.listen(6006) ? (actualTime.get(Calendar.ERA) <= cal.get(Calendar.ERA)) : (ListenerUtil.mutListener.listen(6005) ? (actualTime.get(Calendar.ERA) > cal.get(Calendar.ERA)) : (ListenerUtil.mutListener.listen(6004) ? (actualTime.get(Calendar.ERA) < cal.get(Calendar.ERA)) : (ListenerUtil.mutListener.listen(6003) ? (actualTime.get(Calendar.ERA) != cal.get(Calendar.ERA)) : (actualTime.get(Calendar.ERA) == cal.get(Calendar.ERA))))))) && (ListenerUtil.mutListener.listen(6012) ? (actualTime.get(Calendar.YEAR) >= cal.get(Calendar.YEAR)) : (ListenerUtil.mutListener.listen(6011) ? (actualTime.get(Calendar.YEAR) <= cal.get(Calendar.YEAR)) : (ListenerUtil.mutListener.listen(6010) ? (actualTime.get(Calendar.YEAR) > cal.get(Calendar.YEAR)) : (ListenerUtil.mutListener.listen(6009) ? (actualTime.get(Calendar.YEAR) < cal.get(Calendar.YEAR)) : (ListenerUtil.mutListener.listen(6008) ? (actualTime.get(Calendar.YEAR) != cal.get(Calendar.YEAR)) : (actualTime.get(Calendar.YEAR) == cal.get(Calendar.YEAR))))))))) || (ListenerUtil.mutListener.listen(6018) ? (actualTime.get(Calendar.DAY_OF_YEAR) >= cal.get(Calendar.DAY_OF_YEAR)) : (ListenerUtil.mutListener.listen(6017) ? (actualTime.get(Calendar.DAY_OF_YEAR) <= cal.get(Calendar.DAY_OF_YEAR)) : (ListenerUtil.mutListener.listen(6016) ? (actualTime.get(Calendar.DAY_OF_YEAR) > cal.get(Calendar.DAY_OF_YEAR)) : (ListenerUtil.mutListener.listen(6015) ? (actualTime.get(Calendar.DAY_OF_YEAR) < cal.get(Calendar.DAY_OF_YEAR)) : (ListenerUtil.mutListener.listen(6014) ? (actualTime.get(Calendar.DAY_OF_YEAR) != cal.get(Calendar.DAY_OF_YEAR)) : (actualTime.get(Calendar.DAY_OF_YEAR) == cal.get(Calendar.DAY_OF_YEAR)))))))) : ((ListenerUtil.mutListener.listen(6013) ? ((ListenerUtil.mutListener.listen(6007) ? (actualTime.get(Calendar.ERA) >= cal.get(Calendar.ERA)) : (ListenerUtil.mutListener.listen(6006) ? (actualTime.get(Calendar.ERA) <= cal.get(Calendar.ERA)) : (ListenerUtil.mutListener.listen(6005) ? (actualTime.get(Calendar.ERA) > cal.get(Calendar.ERA)) : (ListenerUtil.mutListener.listen(6004) ? (actualTime.get(Calendar.ERA) < cal.get(Calendar.ERA)) : (ListenerUtil.mutListener.listen(6003) ? (actualTime.get(Calendar.ERA) != cal.get(Calendar.ERA)) : (actualTime.get(Calendar.ERA) == cal.get(Calendar.ERA))))))) || (ListenerUtil.mutListener.listen(6012) ? (actualTime.get(Calendar.YEAR) >= cal.get(Calendar.YEAR)) : (ListenerUtil.mutListener.listen(6011) ? (actualTime.get(Calendar.YEAR) <= cal.get(Calendar.YEAR)) : (ListenerUtil.mutListener.listen(6010) ? (actualTime.get(Calendar.YEAR) > cal.get(Calendar.YEAR)) : (ListenerUtil.mutListener.listen(6009) ? (actualTime.get(Calendar.YEAR) < cal.get(Calendar.YEAR)) : (ListenerUtil.mutListener.listen(6008) ? (actualTime.get(Calendar.YEAR) != cal.get(Calendar.YEAR)) : (actualTime.get(Calendar.YEAR) == cal.get(Calendar.YEAR)))))))) : ((ListenerUtil.mutListener.listen(6007) ? (actualTime.get(Calendar.ERA) >= cal.get(Calendar.ERA)) : (ListenerUtil.mutListener.listen(6006) ? (actualTime.get(Calendar.ERA) <= cal.get(Calendar.ERA)) : (ListenerUtil.mutListener.listen(6005) ? (actualTime.get(Calendar.ERA) > cal.get(Calendar.ERA)) : (ListenerUtil.mutListener.listen(6004) ? (actualTime.get(Calendar.ERA) < cal.get(Calendar.ERA)) : (ListenerUtil.mutListener.listen(6003) ? (actualTime.get(Calendar.ERA) != cal.get(Calendar.ERA)) : (actualTime.get(Calendar.ERA) == cal.get(Calendar.ERA))))))) && (ListenerUtil.mutListener.listen(6012) ? (actualTime.get(Calendar.YEAR) >= cal.get(Calendar.YEAR)) : (ListenerUtil.mutListener.listen(6011) ? (actualTime.get(Calendar.YEAR) <= cal.get(Calendar.YEAR)) : (ListenerUtil.mutListener.listen(6010) ? (actualTime.get(Calendar.YEAR) > cal.get(Calendar.YEAR)) : (ListenerUtil.mutListener.listen(6009) ? (actualTime.get(Calendar.YEAR) < cal.get(Calendar.YEAR)) : (ListenerUtil.mutListener.listen(6008) ? (actualTime.get(Calendar.YEAR) != cal.get(Calendar.YEAR)) : (actualTime.get(Calendar.YEAR) == cal.get(Calendar.YEAR))))))))) && (ListenerUtil.mutListener.listen(6018) ? (actualTime.get(Calendar.DAY_OF_YEAR) >= cal.get(Calendar.DAY_OF_YEAR)) : (ListenerUtil.mutListener.listen(6017) ? (actualTime.get(Calendar.DAY_OF_YEAR) <= cal.get(Calendar.DAY_OF_YEAR)) : (ListenerUtil.mutListener.listen(6016) ? (actualTime.get(Calendar.DAY_OF_YEAR) > cal.get(Calendar.DAY_OF_YEAR)) : (ListenerUtil.mutListener.listen(6015) ? (actualTime.get(Calendar.DAY_OF_YEAR) < cal.get(Calendar.DAY_OF_YEAR)) : (ListenerUtil.mutListener.listen(6014) ? (actualTime.get(Calendar.DAY_OF_YEAR) != cal.get(Calendar.DAY_OF_YEAR)) : (actualTime.get(Calendar.DAY_OF_YEAR) == cal.get(Calendar.DAY_OF_YEAR))))))))));
    }

    public static boolean isTomorrow(Calendar cal) {
        Calendar tomorrowTime = Calendar.getInstance();
        if (!ListenerUtil.mutListener.listen(6020)) {
            tomorrowTime.add(Calendar.DAY_OF_YEAR, 1);
        }
        return ((ListenerUtil.mutListener.listen(6037) ? ((ListenerUtil.mutListener.listen(6031) ? ((ListenerUtil.mutListener.listen(6025) ? (tomorrowTime.get(Calendar.ERA) >= cal.get(Calendar.ERA)) : (ListenerUtil.mutListener.listen(6024) ? (tomorrowTime.get(Calendar.ERA) <= cal.get(Calendar.ERA)) : (ListenerUtil.mutListener.listen(6023) ? (tomorrowTime.get(Calendar.ERA) > cal.get(Calendar.ERA)) : (ListenerUtil.mutListener.listen(6022) ? (tomorrowTime.get(Calendar.ERA) < cal.get(Calendar.ERA)) : (ListenerUtil.mutListener.listen(6021) ? (tomorrowTime.get(Calendar.ERA) != cal.get(Calendar.ERA)) : (tomorrowTime.get(Calendar.ERA) == cal.get(Calendar.ERA))))))) || (ListenerUtil.mutListener.listen(6030) ? (tomorrowTime.get(Calendar.YEAR) >= cal.get(Calendar.YEAR)) : (ListenerUtil.mutListener.listen(6029) ? (tomorrowTime.get(Calendar.YEAR) <= cal.get(Calendar.YEAR)) : (ListenerUtil.mutListener.listen(6028) ? (tomorrowTime.get(Calendar.YEAR) > cal.get(Calendar.YEAR)) : (ListenerUtil.mutListener.listen(6027) ? (tomorrowTime.get(Calendar.YEAR) < cal.get(Calendar.YEAR)) : (ListenerUtil.mutListener.listen(6026) ? (tomorrowTime.get(Calendar.YEAR) != cal.get(Calendar.YEAR)) : (tomorrowTime.get(Calendar.YEAR) == cal.get(Calendar.YEAR)))))))) : ((ListenerUtil.mutListener.listen(6025) ? (tomorrowTime.get(Calendar.ERA) >= cal.get(Calendar.ERA)) : (ListenerUtil.mutListener.listen(6024) ? (tomorrowTime.get(Calendar.ERA) <= cal.get(Calendar.ERA)) : (ListenerUtil.mutListener.listen(6023) ? (tomorrowTime.get(Calendar.ERA) > cal.get(Calendar.ERA)) : (ListenerUtil.mutListener.listen(6022) ? (tomorrowTime.get(Calendar.ERA) < cal.get(Calendar.ERA)) : (ListenerUtil.mutListener.listen(6021) ? (tomorrowTime.get(Calendar.ERA) != cal.get(Calendar.ERA)) : (tomorrowTime.get(Calendar.ERA) == cal.get(Calendar.ERA))))))) && (ListenerUtil.mutListener.listen(6030) ? (tomorrowTime.get(Calendar.YEAR) >= cal.get(Calendar.YEAR)) : (ListenerUtil.mutListener.listen(6029) ? (tomorrowTime.get(Calendar.YEAR) <= cal.get(Calendar.YEAR)) : (ListenerUtil.mutListener.listen(6028) ? (tomorrowTime.get(Calendar.YEAR) > cal.get(Calendar.YEAR)) : (ListenerUtil.mutListener.listen(6027) ? (tomorrowTime.get(Calendar.YEAR) < cal.get(Calendar.YEAR)) : (ListenerUtil.mutListener.listen(6026) ? (tomorrowTime.get(Calendar.YEAR) != cal.get(Calendar.YEAR)) : (tomorrowTime.get(Calendar.YEAR) == cal.get(Calendar.YEAR))))))))) || (ListenerUtil.mutListener.listen(6036) ? (tomorrowTime.get(Calendar.DAY_OF_YEAR) >= cal.get(Calendar.DAY_OF_YEAR)) : (ListenerUtil.mutListener.listen(6035) ? (tomorrowTime.get(Calendar.DAY_OF_YEAR) <= cal.get(Calendar.DAY_OF_YEAR)) : (ListenerUtil.mutListener.listen(6034) ? (tomorrowTime.get(Calendar.DAY_OF_YEAR) > cal.get(Calendar.DAY_OF_YEAR)) : (ListenerUtil.mutListener.listen(6033) ? (tomorrowTime.get(Calendar.DAY_OF_YEAR) < cal.get(Calendar.DAY_OF_YEAR)) : (ListenerUtil.mutListener.listen(6032) ? (tomorrowTime.get(Calendar.DAY_OF_YEAR) != cal.get(Calendar.DAY_OF_YEAR)) : (tomorrowTime.get(Calendar.DAY_OF_YEAR) == cal.get(Calendar.DAY_OF_YEAR)))))))) : ((ListenerUtil.mutListener.listen(6031) ? ((ListenerUtil.mutListener.listen(6025) ? (tomorrowTime.get(Calendar.ERA) >= cal.get(Calendar.ERA)) : (ListenerUtil.mutListener.listen(6024) ? (tomorrowTime.get(Calendar.ERA) <= cal.get(Calendar.ERA)) : (ListenerUtil.mutListener.listen(6023) ? (tomorrowTime.get(Calendar.ERA) > cal.get(Calendar.ERA)) : (ListenerUtil.mutListener.listen(6022) ? (tomorrowTime.get(Calendar.ERA) < cal.get(Calendar.ERA)) : (ListenerUtil.mutListener.listen(6021) ? (tomorrowTime.get(Calendar.ERA) != cal.get(Calendar.ERA)) : (tomorrowTime.get(Calendar.ERA) == cal.get(Calendar.ERA))))))) || (ListenerUtil.mutListener.listen(6030) ? (tomorrowTime.get(Calendar.YEAR) >= cal.get(Calendar.YEAR)) : (ListenerUtil.mutListener.listen(6029) ? (tomorrowTime.get(Calendar.YEAR) <= cal.get(Calendar.YEAR)) : (ListenerUtil.mutListener.listen(6028) ? (tomorrowTime.get(Calendar.YEAR) > cal.get(Calendar.YEAR)) : (ListenerUtil.mutListener.listen(6027) ? (tomorrowTime.get(Calendar.YEAR) < cal.get(Calendar.YEAR)) : (ListenerUtil.mutListener.listen(6026) ? (tomorrowTime.get(Calendar.YEAR) != cal.get(Calendar.YEAR)) : (tomorrowTime.get(Calendar.YEAR) == cal.get(Calendar.YEAR)))))))) : ((ListenerUtil.mutListener.listen(6025) ? (tomorrowTime.get(Calendar.ERA) >= cal.get(Calendar.ERA)) : (ListenerUtil.mutListener.listen(6024) ? (tomorrowTime.get(Calendar.ERA) <= cal.get(Calendar.ERA)) : (ListenerUtil.mutListener.listen(6023) ? (tomorrowTime.get(Calendar.ERA) > cal.get(Calendar.ERA)) : (ListenerUtil.mutListener.listen(6022) ? (tomorrowTime.get(Calendar.ERA) < cal.get(Calendar.ERA)) : (ListenerUtil.mutListener.listen(6021) ? (tomorrowTime.get(Calendar.ERA) != cal.get(Calendar.ERA)) : (tomorrowTime.get(Calendar.ERA) == cal.get(Calendar.ERA))))))) && (ListenerUtil.mutListener.listen(6030) ? (tomorrowTime.get(Calendar.YEAR) >= cal.get(Calendar.YEAR)) : (ListenerUtil.mutListener.listen(6029) ? (tomorrowTime.get(Calendar.YEAR) <= cal.get(Calendar.YEAR)) : (ListenerUtil.mutListener.listen(6028) ? (tomorrowTime.get(Calendar.YEAR) > cal.get(Calendar.YEAR)) : (ListenerUtil.mutListener.listen(6027) ? (tomorrowTime.get(Calendar.YEAR) < cal.get(Calendar.YEAR)) : (ListenerUtil.mutListener.listen(6026) ? (tomorrowTime.get(Calendar.YEAR) != cal.get(Calendar.YEAR)) : (tomorrowTime.get(Calendar.YEAR) == cal.get(Calendar.YEAR))))))))) && (ListenerUtil.mutListener.listen(6036) ? (tomorrowTime.get(Calendar.DAY_OF_YEAR) >= cal.get(Calendar.DAY_OF_YEAR)) : (ListenerUtil.mutListener.listen(6035) ? (tomorrowTime.get(Calendar.DAY_OF_YEAR) <= cal.get(Calendar.DAY_OF_YEAR)) : (ListenerUtil.mutListener.listen(6034) ? (tomorrowTime.get(Calendar.DAY_OF_YEAR) > cal.get(Calendar.DAY_OF_YEAR)) : (ListenerUtil.mutListener.listen(6033) ? (tomorrowTime.get(Calendar.DAY_OF_YEAR) < cal.get(Calendar.DAY_OF_YEAR)) : (ListenerUtil.mutListener.listen(6032) ? (tomorrowTime.get(Calendar.DAY_OF_YEAR) != cal.get(Calendar.DAY_OF_YEAR)) : (tomorrowTime.get(Calendar.DAY_OF_YEAR) == cal.get(Calendar.DAY_OF_YEAR))))))))));
    }

    /**
     * Shows only the last n words of a sentence, being n the number of words to make the longer
     * sentence that still is smaller than maxLength.
     *
     * @param sentence  phrase to shrink
     * @param maxLength max length of the new sentence
     * @return the reduced sentence
     */
    public static String tailAndTruncateSentence(String sentence, int maxLength) {
        String[] words = sentence.split(" ");
        List<String> list = Arrays.asList(words);
        if (!ListenerUtil.mutListener.listen(6038)) {
            Collections.reverse(list);
        }
        String[] reversedWords = (String[]) list.toArray();
        String modifiedSentence = "";
        if (!ListenerUtil.mutListener.listen(6046)) {
            {
                long _loopCounter59 = 0;
                for (String word : reversedWords) {
                    ListenerUtil.loopListener.listen("_loopCounter59", ++_loopCounter59);
                    if (!ListenerUtil.mutListener.listen(6044)) {
                        if ((ListenerUtil.mutListener.listen(6043) ? (modifiedSentence.length() <= maxLength) : (ListenerUtil.mutListener.listen(6042) ? (modifiedSentence.length() > maxLength) : (ListenerUtil.mutListener.listen(6041) ? (modifiedSentence.length() < maxLength) : (ListenerUtil.mutListener.listen(6040) ? (modifiedSentence.length() != maxLength) : (ListenerUtil.mutListener.listen(6039) ? (modifiedSentence.length() == maxLength) : (modifiedSentence.length() >= maxLength))))))) {
                            return modifiedSentence;
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(6045)) {
                        modifiedSentence = word + " " + modifiedSentence;
                    }
                }
            }
        }
        return modifiedSentence;
    }

    /**
     * Always creates a correct value for the short name of the route. Using the routeShortName,
     * processing the long name if the short is null or returning an empty string if both names are
     * null.
     *
     * Route short name will be preceded by adequate connector.
     *
     * @param routeLongName  to convert it to a route short name if necessary
     * @param routeShortName to be returned if is not null
     * @return a valid route short name
     */
    public static String getRouteShortNameSafe(String routeShortName, String routeLongName, Context context) {
        String routeName = "";
        if (!ListenerUtil.mutListener.listen(6052)) {
            if ((ListenerUtil.mutListener.listen(6047) ? (routeShortName != null && routeLongName != null) : (routeShortName != null || routeLongName != null))) {
                if (!ListenerUtil.mutListener.listen(6048)) {
                    routeName += context.getResources().getString(R.string.connector_before_route);
                }
                if (!ListenerUtil.mutListener.listen(6051)) {
                    if (routeShortName != null) {
                        if (!ListenerUtil.mutListener.listen(6050)) {
                            routeName += " " + routeShortName;
                        }
                    } else if (routeLongName != null) {
                        if (!ListenerUtil.mutListener.listen(6049)) {
                            routeName += " " + tailAndTruncateSentence(routeLongName, OTPConstants.ROUTE_SHORT_NAME_MAX_SIZE);
                        }
                    }
                }
            }
        }
        return routeName;
    }

    /**
     * Always creates a correct value for the long name of the route. Using the routeLongName,
     * returning the short name if the long is null or returning an empty string if both names are
     * null.
     *
     * @param routeLongName  to be returned if is not null
     * @param routeShortName to use if necessary
     * @return a valid route long name
     */
    public static String getRouteLongNameSafe(String routeLongName, String routeShortName, boolean includeShortName) {
        String routeName = "";
        if (!ListenerUtil.mutListener.listen(6060)) {
            if ((ListenerUtil.mutListener.listen(6053) ? (routeShortName != null && routeLongName != null) : (routeShortName != null || routeLongName != null))) {
                if (!ListenerUtil.mutListener.listen(6059)) {
                    if (routeLongName != null) {
                        if (!ListenerUtil.mutListener.listen(6058)) {
                            if ((ListenerUtil.mutListener.listen(6055) ? (includeShortName || routeShortName != null) : (includeShortName && routeShortName != null))) {
                                if (!ListenerUtil.mutListener.listen(6057)) {
                                    routeName = routeShortName + " " + "(" + routeLongName + ")";
                                }
                            } else {
                                if (!ListenerUtil.mutListener.listen(6056)) {
                                    routeName += routeLongName;
                                }
                            }
                        }
                    } else if (routeShortName != null) {
                        if (!ListenerUtil.mutListener.listen(6054)) {
                            routeName += routeShortName;
                        }
                    }
                }
            }
        }
        return routeName;
    }

    /**
     * Convert meters to feet.
     *
     * @param meters
     * @return feet
     */
    public static double metersToFeet(double meters) {
        return (ListenerUtil.mutListener.listen(6064) ? (meters % FEET_PER_METER) : (ListenerUtil.mutListener.listen(6063) ? (meters / FEET_PER_METER) : (ListenerUtil.mutListener.listen(6062) ? (meters - FEET_PER_METER) : (ListenerUtil.mutListener.listen(6061) ? (meters + FEET_PER_METER) : (meters * FEET_PER_METER)))));
    }

    /**
     * Convert feet to meters.
     *
     * @param feet
     * @return meters
     */
    public static double feetToMeters(double feet) {
        return (ListenerUtil.mutListener.listen(6068) ? (feet % FEET_PER_METER) : (ListenerUtil.mutListener.listen(6067) ? (feet * FEET_PER_METER) : (ListenerUtil.mutListener.listen(6066) ? (feet - FEET_PER_METER) : (ListenerUtil.mutListener.listen(6065) ? (feet + FEET_PER_METER) : (feet / FEET_PER_METER)))));
    }
}
