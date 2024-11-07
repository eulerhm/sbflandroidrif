/**
 * Copyright (C) 2016 Cambridge Systematics, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.onebusaway.android.directions.util;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import org.onebusaway.android.R;
import org.onebusaway.android.app.Application;
import org.onebusaway.android.directions.tasks.TripRequest;
import org.onebusaway.android.ui.TripModes;
import org.onebusaway.android.util.RegionUtils;
import org.opentripplanner.api.ws.Request;
import org.opentripplanner.routing.core.OptimizeType;
import org.opentripplanner.routing.core.TraverseMode;
import java.io.UnsupportedEncodingException;
import java.lang.ref.WeakReference;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class TripRequestBuilder {

    private static final String ENCODING = "UTF-8";

    private static final String TAG = "TripRequestBuilder";

    private static final String ARRIVE_BY = ".ARRIVE_BY";

    private static final String FROM_ADDRESS = ".FROM_ADDRESS";

    private static final String FROM_LAT = ".FROM_LAT";

    private static final String FROM_LON = ".FROM_LON";

    private static final String FROM_NAME = ".FROM_NAME";

    private static final String TO_ADDRESS = ".TO_ADDRESS";

    private static final String TO_LAT = ".TO_LAT";

    private static final String TO_LON = ".TO_LON";

    private static final String TO_NAME = ".TO_NAME";

    private static final String OPTIMIZE_TRANSFERS = ".OPTIMIZE_TRANSFERS";

    private static final String WHEELCHAIR_ACCESSIBLE = ".WHEELCHAIR_ACCESSIBLE";

    private static final String MAX_WALK_DISTANCE = ".MAX_WALK_DISTANCE";

    private static final String MODE_SET = ".MODE_SET";

    private static final String DATE_TIME = ".DATE_TIME";

    private TripRequest.Callback mListener;

    private Bundle mBundle;

    private int mModeId;

    public TripRequestBuilder(Bundle bundle) {
        if (!ListenerUtil.mutListener.listen(5561)) {
            this.mBundle = bundle;
        }
    }

    public TripRequestBuilder setDepartureTime(Calendar calendar) {
        if (!ListenerUtil.mutListener.listen(5562)) {
            mBundle.putBoolean(ARRIVE_BY, false);
        }
        if (!ListenerUtil.mutListener.listen(5563)) {
            setDateTime(calendar.getTime());
        }
        return this;
    }

    public TripRequestBuilder setArrivalTime(Calendar calendar) {
        if (!ListenerUtil.mutListener.listen(5564)) {
            mBundle.putBoolean(ARRIVE_BY, true);
        }
        if (!ListenerUtil.mutListener.listen(5565)) {
            setDateTime(calendar.getTime());
        }
        return this;
    }

    // default value is false
    public boolean getArriveBy() {
        Boolean b = mBundle.getBoolean(ARRIVE_BY);
        return b == null ? false : b;
    }

    public TripRequestBuilder setFrom(CustomAddress from) {
        if (!ListenerUtil.mutListener.listen(5566)) {
            mBundle.putParcelable(FROM_ADDRESS, from);
        }
        return this;
    }

    public CustomAddress getFrom() {
        return (CustomAddress) mBundle.getParcelable(FROM_ADDRESS);
    }

    public CustomAddress getTo() {
        return (CustomAddress) mBundle.getParcelable(TO_ADDRESS);
    }

    public TripRequestBuilder setTo(CustomAddress to) {
        if (!ListenerUtil.mutListener.listen(5567)) {
            mBundle.putParcelable(TO_ADDRESS, to);
        }
        return this;
    }

    public TripRequestBuilder setListener(TripRequest.Callback listener) {
        if (!ListenerUtil.mutListener.listen(5568)) {
            this.mListener = listener;
        }
        return this;
    }

    public TripRequestBuilder setOptimizeTransfers(boolean set) {
        if (!ListenerUtil.mutListener.listen(5569)) {
            mBundle.putSerializable(OPTIMIZE_TRANSFERS, set ? OptimizeType.TRANSFERS : OptimizeType.QUICK);
        }
        return this;
    }

    private OptimizeType getOptimizeType() {
        OptimizeType type = (OptimizeType) mBundle.getSerializable(OPTIMIZE_TRANSFERS);
        return type == null ? OptimizeType.QUICK : type;
    }

    public boolean getOptimizeTransfers() {
        return getOptimizeType() == OptimizeType.TRANSFERS;
    }

    public TripRequestBuilder setWheelchairAccessible(boolean wheelchair) {
        if (!ListenerUtil.mutListener.listen(5570)) {
            mBundle.putBoolean(WHEELCHAIR_ACCESSIBLE, wheelchair);
        }
        return this;
    }

    public boolean getWheelchairAccessible() {
        return mBundle.getBoolean(WHEELCHAIR_ACCESSIBLE);
    }

    public TripRequestBuilder setMaxWalkDistance(double walkDistance) {
        if (!ListenerUtil.mutListener.listen(5571)) {
            mBundle.putDouble(MAX_WALK_DISTANCE, walkDistance);
        }
        return this;
    }

    public Double getMaxWalkDistance() {
        Double d = mBundle.getDouble(MAX_WALK_DISTANCE);
        return ((ListenerUtil.mutListener.listen(5582) ? ((ListenerUtil.mutListener.listen(5576) ? (d >= 0) : (ListenerUtil.mutListener.listen(5575) ? (d <= 0) : (ListenerUtil.mutListener.listen(5574) ? (d > 0) : (ListenerUtil.mutListener.listen(5573) ? (d < 0) : (ListenerUtil.mutListener.listen(5572) ? (d == 0) : (d != 0)))))) || (ListenerUtil.mutListener.listen(5581) ? (d >= Double.MAX_VALUE) : (ListenerUtil.mutListener.listen(5580) ? (d <= Double.MAX_VALUE) : (ListenerUtil.mutListener.listen(5579) ? (d > Double.MAX_VALUE) : (ListenerUtil.mutListener.listen(5578) ? (d < Double.MAX_VALUE) : (ListenerUtil.mutListener.listen(5577) ? (d == Double.MAX_VALUE) : (d != Double.MAX_VALUE))))))) : ((ListenerUtil.mutListener.listen(5576) ? (d >= 0) : (ListenerUtil.mutListener.listen(5575) ? (d <= 0) : (ListenerUtil.mutListener.listen(5574) ? (d > 0) : (ListenerUtil.mutListener.listen(5573) ? (d < 0) : (ListenerUtil.mutListener.listen(5572) ? (d == 0) : (d != 0)))))) && (ListenerUtil.mutListener.listen(5581) ? (d >= Double.MAX_VALUE) : (ListenerUtil.mutListener.listen(5580) ? (d <= Double.MAX_VALUE) : (ListenerUtil.mutListener.listen(5579) ? (d > Double.MAX_VALUE) : (ListenerUtil.mutListener.listen(5578) ? (d < Double.MAX_VALUE) : (ListenerUtil.mutListener.listen(5577) ? (d == Double.MAX_VALUE) : (d != Double.MAX_VALUE))))))))) ? d : null;
    }

    // rail only -> RAIL,TRAM,WALK (TRAM is included to allow light rail)
    public TripRequestBuilder setModeSetById(int id) {
        List<String> modes;
        if (!ListenerUtil.mutListener.listen(5583)) {
            mModeId = id;
        }
        switch(id) {
            // Transit only
            case TripModes.TRANSIT_ONLY:
                modes = Arrays.asList(TraverseMode.TRANSIT.toString(), TraverseMode.WALK.toString());
                break;
            // Transit & bikeshare
            case TripModes.TRANSIT_AND_BIKE:
                if (Application.isBikeshareEnabled()) {
                    modes = Arrays.asList(TraverseMode.TRANSIT.toString(), TraverseMode.WALK.toString(), Application.get().getString(R.string.traverse_mode_bicycle_rent));
                } else {
                    modes = Arrays.asList(TraverseMode.TRANSIT.toString(), TraverseMode.WALK.toString());
                }
                break;
            case TripModes.BUS_ONLY:
                modes = Arrays.asList(TraverseMode.BUS.toString(), TraverseMode.WALK.toString());
                break;
            case TripModes.RAIL_ONLY:
                modes = Arrays.asList(TraverseMode.RAIL.toString(), TraverseMode.TRAM.toString(), TraverseMode.WALK.toString());
                break;
            case TripModes.BIKESHARE:
                modes = Arrays.asList(Application.get().getString(R.string.traverse_mode_bicycle_rent));
                break;
            default:
                if (!ListenerUtil.mutListener.listen(5584)) {
                    Log.e(TAG, "Invalid mode set ID");
                }
                modes = Arrays.asList(TraverseMode.TRANSIT.toString(), TraverseMode.WALK.toString());
                if (!ListenerUtil.mutListener.listen(5585)) {
                    mModeId = -1;
                }
        }
        String modeString = TextUtils.join(",", modes);
        if (!ListenerUtil.mutListener.listen(5586)) {
            mBundle.putString(MODE_SET, modeString);
        }
        return this;
    }

    public int getModeSetId() {
        if (!ListenerUtil.mutListener.listen(5588)) {
            // IF bike mode is selected in the trip plan additional preferences but bikeshare is not enabled use the default mode (TRANSTI)
            if ((ListenerUtil.mutListener.listen(5587) ? (TripModes.BIKESHARE == mModeId || !Application.isBikeshareEnabled()) : (TripModes.BIKESHARE == mModeId && !Application.isBikeshareEnabled()))) {
                return TripModes.TRANSIT_ONLY;
            }
        }
        return mModeId;
    }

    private List<String> getModes() {
        String modeString = mBundle.getString(MODE_SET);
        if (!ListenerUtil.mutListener.listen(5589)) {
            if (TextUtils.isEmpty(modeString)) {
                return Arrays.asList(TraverseMode.TRANSIT.toString(), TraverseMode.WALK.toString());
            }
        }
        return Arrays.asList(TextUtils.split(modeString, ","));
    }

    private String getModeString() {
        return mBundle.getString(MODE_SET);
    }

    public TripRequest execute(Activity activity) {
        String from = getAddressString(getFrom());
        String to = getAddressString(getTo());
        if (!ListenerUtil.mutListener.listen(5591)) {
            if ((ListenerUtil.mutListener.listen(5590) ? (TextUtils.isEmpty(from) && TextUtils.isEmpty(to)) : (TextUtils.isEmpty(from) || TextUtils.isEmpty(to)))) {
                throw new IllegalArgumentException("Must supply start and end to route between.");
            }
        }
        Request request = new Request();
        if (!ListenerUtil.mutListener.listen(5592)) {
            request.setArriveBy(getArriveBy());
        }
        if (!ListenerUtil.mutListener.listen(5593)) {
            request.setFrom(from);
        }
        if (!ListenerUtil.mutListener.listen(5594)) {
            request.setTo(to);
        }
        if (!ListenerUtil.mutListener.listen(5595)) {
            request.setOptimize(getOptimizeType());
        }
        if (!ListenerUtil.mutListener.listen(5596)) {
            request.setWheelchair(getWheelchairAccessible());
        }
        Double maxWalkDistance = getMaxWalkDistance();
        if (!ListenerUtil.mutListener.listen(5598)) {
            if (maxWalkDistance != null) {
                if (!ListenerUtil.mutListener.listen(5597)) {
                    request.setMaxWalkDistance(maxWalkDistance);
                }
            }
        }
        Date d = getDateTime();
        // OTP expects date/time in this format
        String date = getFormattedDate(OTPConstants.FORMAT_OTP_SERVER_DATE_REQUEST, d);
        String time = getFormattedDate(OTPConstants.FORMAT_OTP_SERVER_TIME_REQUEST, d);
        if (!ListenerUtil.mutListener.listen(5599)) {
            request.setDateTime(date, time);
        }
        // Request mode set does not work properly
        String modeString = mBundle.getString(MODE_SET);
        if (!ListenerUtil.mutListener.listen(5601)) {
            if (modeString != null) {
                if (!ListenerUtil.mutListener.listen(5600)) {
                    request.getParameters().put("mode", modeString);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(5602)) {
            // Our default. This could be configurable.
            request.setShowIntermediateStops(true);
        }
        // TripRequest will accept a null value and give a user-friendly error
        String otpBaseUrl;
        Application app = Application.get();
        if (!TextUtils.isEmpty(app.getCustomOtpApiUrl())) {
            otpBaseUrl = app.getCustomOtpApiUrl();
            if (!ListenerUtil.mutListener.listen(5603)) {
                Log.d(TAG, "Using custom OTP API URL set by user '" + otpBaseUrl + "'.");
            }
        } else {
            otpBaseUrl = app.getCurrentRegion().getOtpBaseUrl();
        }
        try {
            // URI.parse() doesn't tell us if the scheme is missing, so use URL() instead (#126)
            URL url = new URL(otpBaseUrl);
        } catch (MalformedURLException e) {
            // Assume HTTPS scheme, since without a scheme the Uri won't parse the authority
            otpBaseUrl = activity.getString(R.string.https_prefix) + otpBaseUrl;
        }
        String fmtOtpBaseUrl = otpBaseUrl != null ? RegionUtils.formatOtpBaseUrl(otpBaseUrl) : null;
        TripRequest tripRequest;
        if (activity == null) {
            tripRequest = new TripRequest(fmtOtpBaseUrl, mListener);
        } else {
            WeakReference<Activity> ref = new WeakReference<Activity>(activity);
            tripRequest = new TripRequest(fmtOtpBaseUrl, mListener);
        }
        if (!ListenerUtil.mutListener.listen(5604)) {
            tripRequest.execute(request);
        }
        return tripRequest;
    }

    public void execute() {
        if (!ListenerUtil.mutListener.listen(5605)) {
            execute(null);
        }
    }

    private String getAddressString(CustomAddress address) {
        if (address == null) {
            return null;
        }
        if ((ListenerUtil.mutListener.listen(5606) ? (address.hasLatitude() || address.hasLongitude()) : (address.hasLatitude() && address.hasLongitude()))) {
            double lat = address.getLatitude();
            double lon = address.getLongitude();
            return String.format(OTPConstants.OTP_LOCALE, "%g,%g", lat, lon);
        }
        // Not set via geocoder OR via location service. Use raw string (set in TripPlanFragment to first line of address).
        String line = address.getAddressLine(0);
        try {
            return URLEncoder.encode(line, ENCODING);
        } catch (UnsupportedEncodingException ex) {
            if (!ListenerUtil.mutListener.listen(5607)) {
                Log.e(TAG, "Error encoding address: " + ex);
            }
            return "";
        }
    }

    public TripRequestBuilder setDateTime(Date d) {
        if (!ListenerUtil.mutListener.listen(5608)) {
            mBundle.putLong(DATE_TIME, d.getTime());
        }
        return this;
    }

    public TripRequestBuilder setDateTime(Calendar cal) {
        return setDateTime(cal.getTime());
    }

    public Date getDateTime() {
        Long time = mBundle.getLong(DATE_TIME);
        if ((ListenerUtil.mutListener.listen(5614) ? (time == null && (ListenerUtil.mutListener.listen(5613) ? (time >= 0L) : (ListenerUtil.mutListener.listen(5612) ? (time <= 0L) : (ListenerUtil.mutListener.listen(5611) ? (time > 0L) : (ListenerUtil.mutListener.listen(5610) ? (time < 0L) : (ListenerUtil.mutListener.listen(5609) ? (time != 0L) : (time == 0L))))))) : (time == null || (ListenerUtil.mutListener.listen(5613) ? (time >= 0L) : (ListenerUtil.mutListener.listen(5612) ? (time <= 0L) : (ListenerUtil.mutListener.listen(5611) ? (time > 0L) : (ListenerUtil.mutListener.listen(5610) ? (time < 0L) : (ListenerUtil.mutListener.listen(5609) ? (time != 0L) : (time == 0L))))))))) {
            return null;
        } else {
            return new Date(time);
        }
    }

    public Bundle getBundle() {
        return mBundle;
    }

    /**
     * Copy all the data from this builder's bundle into another bundle
     * @param target bundle
     */
    public void copyIntoBundle(Bundle target) {
        if (!ListenerUtil.mutListener.listen(5615)) {
            target.putBoolean(ARRIVE_BY, getArriveBy());
        }
        if (!ListenerUtil.mutListener.listen(5616)) {
            target.putParcelable(FROM_ADDRESS, getFrom());
        }
        if (!ListenerUtil.mutListener.listen(5617)) {
            target.putParcelable(TO_ADDRESS, getTo());
        }
        if (!ListenerUtil.mutListener.listen(5618)) {
            target.putSerializable(OPTIMIZE_TRANSFERS, getOptimizeType());
        }
        if (!ListenerUtil.mutListener.listen(5619)) {
            target.putBoolean(WHEELCHAIR_ACCESSIBLE, getWheelchairAccessible());
        }
        if (!ListenerUtil.mutListener.listen(5621)) {
            if (getMaxWalkDistance() != null) {
                if (!ListenerUtil.mutListener.listen(5620)) {
                    target.putDouble(MAX_WALK_DISTANCE, getMaxWalkDistance());
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(5622)) {
            target.putString(MODE_SET, getModeString());
        }
        if (!ListenerUtil.mutListener.listen(5624)) {
            if (getDateTime() != null) {
                if (!ListenerUtil.mutListener.listen(5623)) {
                    target.putLong(DATE_TIME, getDateTime().getTime());
                }
            }
        }
    }

    /**
     * Copy all the data from this builder's bundle into another bundle, but only use simple data types
     * @param target bundle
     */
    public void copyIntoBundleSimple(Bundle target) {
        if (!ListenerUtil.mutListener.listen(5625)) {
            target.putBoolean(ARRIVE_BY, getArriveBy());
        }
        CustomAddress from = getFrom(), to = getTo();
        if (!ListenerUtil.mutListener.listen(5626)) {
            target.putDouble(FROM_LAT, from.getLatitude());
        }
        if (!ListenerUtil.mutListener.listen(5627)) {
            target.putDouble(FROM_LON, from.getLongitude());
        }
        if (!ListenerUtil.mutListener.listen(5628)) {
            target.putString(FROM_NAME, from.toString());
        }
        if (!ListenerUtil.mutListener.listen(5629)) {
            target.putDouble(TO_LAT, to.getLatitude());
        }
        if (!ListenerUtil.mutListener.listen(5630)) {
            target.putDouble(TO_LON, to.getLongitude());
        }
        if (!ListenerUtil.mutListener.listen(5631)) {
            target.putString(TO_NAME, to.toString());
        }
        if (!ListenerUtil.mutListener.listen(5632)) {
            target.putString(OPTIMIZE_TRANSFERS, getOptimizeType().toString());
        }
        if (!ListenerUtil.mutListener.listen(5633)) {
            target.putBoolean(WHEELCHAIR_ACCESSIBLE, getWheelchairAccessible());
        }
        if (!ListenerUtil.mutListener.listen(5635)) {
            if (getMaxWalkDistance() != null) {
                if (!ListenerUtil.mutListener.listen(5634)) {
                    target.putDouble(MAX_WALK_DISTANCE, getMaxWalkDistance());
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(5636)) {
            target.putString(MODE_SET, getModeString());
        }
        if (!ListenerUtil.mutListener.listen(5638)) {
            if (getDateTime() != null) {
                if (!ListenerUtil.mutListener.listen(5637)) {
                    target.putLong(DATE_TIME, getDateTime().getTime());
                }
            }
        }
    }

    /**
     * Initialize from a BaseBundle
     */
    public static TripRequestBuilder initFromBundleSimple(Bundle bundle) {
        Bundle target = new Bundle();
        if (!ListenerUtil.mutListener.listen(5639)) {
            target.putBoolean(ARRIVE_BY, bundle.getBoolean(ARRIVE_BY));
        }
        CustomAddress from = new CustomAddress();
        if (!ListenerUtil.mutListener.listen(5640)) {
            from.setLatitude(bundle.getDouble(FROM_LAT));
        }
        if (!ListenerUtil.mutListener.listen(5641)) {
            from.setLongitude(bundle.getDouble(FROM_LON));
        }
        if (!ListenerUtil.mutListener.listen(5642)) {
            from.setAddressLine(0, bundle.getString(FROM_NAME));
        }
        CustomAddress to = new CustomAddress();
        if (!ListenerUtil.mutListener.listen(5643)) {
            to.setLatitude(bundle.getDouble(TO_LAT));
        }
        if (!ListenerUtil.mutListener.listen(5644)) {
            to.setLongitude(bundle.getDouble(TO_LON));
        }
        if (!ListenerUtil.mutListener.listen(5645)) {
            to.setAddressLine(0, bundle.getString(TO_NAME));
        }
        if (!ListenerUtil.mutListener.listen(5646)) {
            target.putParcelable(FROM_ADDRESS, from);
        }
        if (!ListenerUtil.mutListener.listen(5647)) {
            target.putParcelable(TO_ADDRESS, to);
        }
        String optName = bundle.getString(OPTIMIZE_TRANSFERS);
        if (!ListenerUtil.mutListener.listen(5649)) {
            if (optName != null) {
                if (!ListenerUtil.mutListener.listen(5648)) {
                    target.putSerializable(OPTIMIZE_TRANSFERS, OptimizeType.valueOf(optName));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(5650)) {
            target.putBoolean(WHEELCHAIR_ACCESSIBLE, bundle.getBoolean(WHEELCHAIR_ACCESSIBLE));
        }
        if (!ListenerUtil.mutListener.listen(5651)) {
            target.putDouble(MAX_WALK_DISTANCE, bundle.getDouble(MAX_WALK_DISTANCE));
        }
        if (!ListenerUtil.mutListener.listen(5652)) {
            target.putString(MODE_SET, bundle.getString(MODE_SET));
        }
        if (!ListenerUtil.mutListener.listen(5653)) {
            target.putLong(DATE_TIME, bundle.getLong(DATE_TIME));
        }
        return new TripRequestBuilder(target);
    }

    /**
     * Determine whether this trip request can be submitted to an OTP server.
     * @return true if ready to submit, false otherwise
     */
    public boolean ready() {
        return (ListenerUtil.mutListener.listen(5657) ? ((ListenerUtil.mutListener.listen(5656) ? ((ListenerUtil.mutListener.listen(5655) ? ((ListenerUtil.mutListener.listen(5654) ? (getFrom() != null || getFrom().isSet()) : (getFrom() != null && getFrom().isSet())) || getTo() != null) : ((ListenerUtil.mutListener.listen(5654) ? (getFrom() != null || getFrom().isSet()) : (getFrom() != null && getFrom().isSet())) && getTo() != null)) || getTo().isSet()) : ((ListenerUtil.mutListener.listen(5655) ? ((ListenerUtil.mutListener.listen(5654) ? (getFrom() != null || getFrom().isSet()) : (getFrom() != null && getFrom().isSet())) || getTo() != null) : ((ListenerUtil.mutListener.listen(5654) ? (getFrom() != null || getFrom().isSet()) : (getFrom() != null && getFrom().isSet())) && getTo() != null)) && getTo().isSet())) || getDateTime() != null) : ((ListenerUtil.mutListener.listen(5656) ? ((ListenerUtil.mutListener.listen(5655) ? ((ListenerUtil.mutListener.listen(5654) ? (getFrom() != null || getFrom().isSet()) : (getFrom() != null && getFrom().isSet())) || getTo() != null) : ((ListenerUtil.mutListener.listen(5654) ? (getFrom() != null || getFrom().isSet()) : (getFrom() != null && getFrom().isSet())) && getTo() != null)) || getTo().isSet()) : ((ListenerUtil.mutListener.listen(5655) ? ((ListenerUtil.mutListener.listen(5654) ? (getFrom() != null || getFrom().isSet()) : (getFrom() != null && getFrom().isSet())) || getTo() != null) : ((ListenerUtil.mutListener.listen(5654) ? (getFrom() != null || getFrom().isSet()) : (getFrom() != null && getFrom().isSet())) && getTo() != null)) && getTo().isSet())) && getDateTime() != null));
    }

    private static String getFormattedDate(String format, Date date) {
        return new SimpleDateFormat(format, OTPConstants.OTP_LOCALE).format(date);
    }
}
