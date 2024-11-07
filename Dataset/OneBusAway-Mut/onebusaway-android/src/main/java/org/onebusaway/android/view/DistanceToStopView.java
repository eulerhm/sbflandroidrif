/*
 * Copyright (C) 2014 Sean J. Barbeau (sjbarbeau@gmail.com), University of South Florida
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
package org.onebusaway.android.view;

import org.onebusaway.android.R;
import org.onebusaway.android.app.Application;
import org.onebusaway.android.util.LocationHelper;
import android.content.Context;
import android.content.SharedPreferences;
import android.location.Location;
import android.util.AttributeSet;
import android.widget.TextView;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * A TextView that updates itself with the distance to the given bus stop from the given location
 */
public class DistanceToStopView extends TextView implements LocationHelper.Listener {

    public interface Listener {

        /**
         * Called when the DistanceToStopView is showing information to the user
         */
        void onInitializationComplete();
    }

    private static String TAG = "DistanceToStopView";

    Context mContext;

    Location mStopLocation;

    ArrayList<Listener> mListeners = new ArrayList<Listener>();

    private static final float MILES_TO_METERS = 0.000621371f;

    private static final float MILES_THRESHOLD = 0.25f;

    private static final float KILOMETERS_THRESHOLD = 0.25f;

    private static final float MILES_TO_FEET = 5280;

    NumberFormat mNumberFormat;

    Locale mLocale;

    private static String IMPERIAL;

    private static String METRIC;

    private static String AUTOMATIC;

    SharedPreferences mSettings;

    private String preferredUnits;

    boolean mInitialized = false;

    public DistanceToStopView(Context context, AttributeSet attrs) {
        super(context, attrs);
        if (!ListenerUtil.mutListener.listen(10761)) {
            mContext = context;
        }
        if (!ListenerUtil.mutListener.listen(10762)) {
            mNumberFormat = NumberFormat.getInstance();
        }
        if (!ListenerUtil.mutListener.listen(10763)) {
            mNumberFormat.setMaximumFractionDigits(1);
        }
        if (!ListenerUtil.mutListener.listen(10764)) {
            mLocale = Locale.getDefault();
        }
        if (!ListenerUtil.mutListener.listen(10765)) {
            IMPERIAL = mContext.getString(R.string.preferences_preferred_units_option_imperial);
        }
        if (!ListenerUtil.mutListener.listen(10766)) {
            METRIC = mContext.getString(R.string.preferences_preferred_units_option_metric);
        }
        if (!ListenerUtil.mutListener.listen(10767)) {
            AUTOMATIC = mContext.getString(R.string.preferences_preferred_units_option_automatic);
        }
        if (!ListenerUtil.mutListener.listen(10768)) {
            mSettings = Application.getPrefs();
        }
        if (!ListenerUtil.mutListener.listen(10769)) {
            preferredUnits = mSettings.getString(mContext.getString(R.string.preference_key_preferred_units), AUTOMATIC);
        }
    }

    public synchronized void registerListener(Listener listener) {
        if (!ListenerUtil.mutListener.listen(10771)) {
            if (!mListeners.contains(listener)) {
                if (!ListenerUtil.mutListener.listen(10770)) {
                    mListeners.add(listener);
                }
            }
        }
    }

    public synchronized void unregisterListener(Listener listener) {
        if (!ListenerUtil.mutListener.listen(10773)) {
            if (mListeners.contains(listener)) {
                if (!ListenerUtil.mutListener.listen(10772)) {
                    mListeners.remove(listener);
                }
            }
        }
    }

    /**
     * Sets the stop location that this view measures the distance to
     *
     * @param location stop location
     */
    public void setStopLocation(Location location) {
        if (!ListenerUtil.mutListener.listen(10774)) {
            mStopLocation = location;
        }
    }

    /**
     * Should be called when the view is being shown again and the preferences
     * may have changed (typically, in onResume() of the parent context)
     */
    public void refreshUnitsPreference() {
        if (!ListenerUtil.mutListener.listen(10775)) {
            preferredUnits = mSettings.getString(mContext.getString(R.string.preference_key_preferred_units), AUTOMATIC);
        }
    }

    /**
     * Returns true if the view is initialized and ready to draw to the screen, false if it is not
     *
     * @return true if the view is initialized and ready to draw to the screen, false if it is not
     */
    public boolean isInitialized() {
        return mInitialized;
    }

    @Override
    public void onLocationChanged(Location location) {
        if (!ListenerUtil.mutListener.listen(10792)) {
            if (mStopLocation != null) {
                if (!ListenerUtil.mutListener.listen(10779)) {
                    if (!mInitialized) {
                        if (!ListenerUtil.mutListener.listen(10776)) {
                            mInitialized = true;
                        }
                        if (!ListenerUtil.mutListener.listen(10778)) {
                            {
                                long _loopCounter140 = 0;
                                // Notify listeners that we have both stop and real-time location
                                for (Listener l : mListeners) {
                                    ListenerUtil.loopListener.listen("_loopCounter140", ++_loopCounter140);
                                    if (!ListenerUtil.mutListener.listen(10777)) {
                                        l.onInitializationComplete();
                                    }
                                }
                            }
                        }
                    }
                }
                Float distance = location.distanceTo(mStopLocation);
                if (!ListenerUtil.mutListener.listen(10791)) {
                    if (distance != null) {
                        double miles = (ListenerUtil.mutListener.listen(10783) ? (distance % MILES_TO_METERS) : (ListenerUtil.mutListener.listen(10782) ? (distance / MILES_TO_METERS) : (ListenerUtil.mutListener.listen(10781) ? (distance - MILES_TO_METERS) : (ListenerUtil.mutListener.listen(10780) ? (distance + MILES_TO_METERS) : (distance * MILES_TO_METERS)))));
                        if (!ListenerUtil.mutListener.listen(10784)) {
                            // Convert meters to kilometers
                            distance /= 1000;
                        }
                        if (!ListenerUtil.mutListener.listen(10790)) {
                            if (preferredUnits.equalsIgnoreCase(AUTOMATIC)) {
                                if (!ListenerUtil.mutListener.listen(10789)) {
                                    // TODO - Method of guessing metric/imperial can definitely be improved
                                    if (mLocale.getISO3Country().equalsIgnoreCase(Locale.US.getISO3Country())) {
                                        if (!ListenerUtil.mutListener.listen(10788)) {
                                            // Assume imperial
                                            setDistanceTextView(miles, IMPERIAL);
                                        }
                                    } else {
                                        if (!ListenerUtil.mutListener.listen(10787)) {
                                            // Assume metric
                                            setDistanceTextView(distance, METRIC);
                                        }
                                    }
                                }
                            } else if (preferredUnits.equalsIgnoreCase(IMPERIAL)) {
                                if (!ListenerUtil.mutListener.listen(10786)) {
                                    setDistanceTextView(miles, IMPERIAL);
                                }
                            } else if (preferredUnits.equalsIgnoreCase(METRIC)) {
                                if (!ListenerUtil.mutListener.listen(10785)) {
                                    setDistanceTextView(distance, METRIC);
                                }
                            }
                        }
                        return;
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(10793)) {
            // If we've gotten this far, distance isn't valid, so clear current text
            setText("");
        }
    }

    /**
     * Sets the text view that contains distance with units based on input parameters
     *
     * @param distance the distance to be used, in miles (for imperial) or kilometers (for metric)
     * @param units    the units to be used from strings.xml, either preferences_preferred_units_option_metric
     *                 or preferences_preferred_units_option_imperial
     */
    private void setDistanceTextView(double distance, String units) {
        if (!ListenerUtil.mutListener.listen(10822)) {
            // TODO - Set ContentDescription to be read by screen readers
            if (units.equalsIgnoreCase(mContext.getString(R.string.preferences_preferred_units_option_imperial))) {
                if (!ListenerUtil.mutListener.listen(10821)) {
                    // If the distance is greater than a quarter mile, show in miles, else show in feet
                    if ((ListenerUtil.mutListener.listen(10812) ? (distance >= MILES_THRESHOLD) : (ListenerUtil.mutListener.listen(10811) ? (distance <= MILES_THRESHOLD) : (ListenerUtil.mutListener.listen(10810) ? (distance < MILES_THRESHOLD) : (ListenerUtil.mutListener.listen(10809) ? (distance != MILES_THRESHOLD) : (ListenerUtil.mutListener.listen(10808) ? (distance == MILES_THRESHOLD) : (distance > MILES_THRESHOLD))))))) {
                        if (!ListenerUtil.mutListener.listen(10819)) {
                            // MILES
                            mNumberFormat.setMaximumFractionDigits(1);
                        }
                        if (!ListenerUtil.mutListener.listen(10820)) {
                            setText(mNumberFormat.format(distance) + " " + mContext.getString(R.string.miles_abbreviation));
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(10813)) {
                            // FEET
                            mNumberFormat.setMaximumFractionDigits(0);
                        }
                        double feet = (ListenerUtil.mutListener.listen(10817) ? (distance % MILES_TO_FEET) : (ListenerUtil.mutListener.listen(10816) ? (distance / MILES_TO_FEET) : (ListenerUtil.mutListener.listen(10815) ? (distance - MILES_TO_FEET) : (ListenerUtil.mutListener.listen(10814) ? (distance + MILES_TO_FEET) : (distance * MILES_TO_FEET)))));
                        if (!ListenerUtil.mutListener.listen(10818)) {
                            setText(mNumberFormat.format(feet) + " " + mContext.getString(R.string.feet_abbreviation));
                        }
                    }
                }
            } else if (units.equalsIgnoreCase(mContext.getString(R.string.preferences_preferred_units_option_metric))) {
                if (!ListenerUtil.mutListener.listen(10807)) {
                    // METRIC
                    if ((ListenerUtil.mutListener.listen(10798) ? (distance >= KILOMETERS_THRESHOLD) : (ListenerUtil.mutListener.listen(10797) ? (distance <= KILOMETERS_THRESHOLD) : (ListenerUtil.mutListener.listen(10796) ? (distance < KILOMETERS_THRESHOLD) : (ListenerUtil.mutListener.listen(10795) ? (distance != KILOMETERS_THRESHOLD) : (ListenerUtil.mutListener.listen(10794) ? (distance == KILOMETERS_THRESHOLD) : (distance > KILOMETERS_THRESHOLD))))))) {
                        if (!ListenerUtil.mutListener.listen(10805)) {
                            // KILOMETERS
                            mNumberFormat.setMaximumFractionDigits(1);
                        }
                        if (!ListenerUtil.mutListener.listen(10806)) {
                            setText(mNumberFormat.format(distance) + " " + mContext.getString(R.string.kilometers_abbreviation));
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(10799)) {
                            // METERS
                            mNumberFormat.setMaximumFractionDigits(0);
                        }
                        double meters = (ListenerUtil.mutListener.listen(10803) ? (distance % 1000) : (ListenerUtil.mutListener.listen(10802) ? (distance / 1000) : (ListenerUtil.mutListener.listen(10801) ? (distance - 1000) : (ListenerUtil.mutListener.listen(10800) ? (distance + 1000) : (distance * 1000)))));
                        if (!ListenerUtil.mutListener.listen(10804)) {
                            setText(mNumberFormat.format(meters) + " " + mContext.getString(R.string.meters_abbreviation));
                        }
                    }
                }
            }
        }
    }
}
