/*
 * Copyright (C) 2012 Paul Watts (paulcwatts@gmail.com)
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
package org.onebusaway.android.ui;

import android.content.ContentValues;
import android.content.Context;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.core.graphics.drawable.DrawableCompat;
import org.onebusaway.android.R;
import org.onebusaway.android.io.elements.ObaArrivalInfo;
import org.onebusaway.android.io.elements.OccupancyState;
import org.onebusaway.android.io.elements.Status;
import org.onebusaway.android.provider.ObaContract;
import org.onebusaway.android.util.ArrivalInfoUtils;
import org.onebusaway.android.util.UIUtils;
import java.util.ArrayList;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Original style of arrivals for OBA Android
 */
public class ArrivalsListAdapterStyleA extends ArrivalsListAdapterBase<ArrivalInfo> {

    public ArrivalsListAdapterStyleA(Context context) {
        super(context, R.layout.arrivals_list_item);
    }

    /**
     * Sets the data to be used with the adapter
     *
     * @param routesFilter routeIds to filter for
     * @param currentTime  current time in milliseconds
     */
    public void setData(ObaArrivalInfo[] arrivals, ArrayList<String> routesFilter, long currentTime) {
        if (!ListenerUtil.mutListener.listen(1348)) {
            if (arrivals != null) {
                ArrayList<ArrivalInfo> list = ArrivalInfoUtils.convertObaArrivalInfo(getContext(), arrivals, routesFilter, currentTime, false);
                if (!ListenerUtil.mutListener.listen(1347)) {
                    setData(list);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(1346)) {
                    setData(null);
                }
            }
        }
    }

    @Override
    protected void initView(View view, ArrivalInfo stopInfo) {
        final Context context = getContext();
        final ObaArrivalInfo arrivalInfo = stopInfo.getInfo();
        TextView route = (TextView) view.findViewById(R.id.route);
        TextView destination = (TextView) view.findViewById(R.id.destination);
        TextView time = (TextView) view.findViewById(R.id.time);
        TextView status = (TextView) view.findViewById(R.id.status);
        TextView etaView = (TextView) view.findViewById(R.id.eta);
        TextView minView = (TextView) view.findViewById(R.id.eta_min);
        ViewGroup realtimeView = (ViewGroup) view.findViewById(R.id.eta_realtime_indicator);
        ViewGroup occupancyView = view.findViewById(R.id.occupancy);
        ImageView moreView = (ImageView) view.findViewById(R.id.more_horizontal);
        if (!ListenerUtil.mutListener.listen(1349)) {
            moreView.setColorFilter(context.getResources().getColor(R.color.switch_thumb_normal_material_dark));
        }
        ImageView starView = (ImageView) view.findViewById(R.id.route_favorite);
        if (!ListenerUtil.mutListener.listen(1350)) {
            starView.setColorFilter(context.getResources().getColor(R.color.navdrawer_icon_tint));
        }
        if (!ListenerUtil.mutListener.listen(1351)) {
            starView.setImageResource(stopInfo.isRouteAndHeadsignFavorite() ? R.drawable.focus_star_on : R.drawable.focus_star_off);
        }
        if (!ListenerUtil.mutListener.listen(1357)) {
            // CANCELED trips
            if (Status.CANCELED.equals(stopInfo.getStatus())) {
                if (!ListenerUtil.mutListener.listen(1352)) {
                    // Strike through the text fields
                    route.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
                }
                if (!ListenerUtil.mutListener.listen(1353)) {
                    destination.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
                }
                if (!ListenerUtil.mutListener.listen(1354)) {
                    time.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
                }
                if (!ListenerUtil.mutListener.listen(1355)) {
                    etaView.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
                }
                if (!ListenerUtil.mutListener.listen(1356)) {
                    minView.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
                }
            }
        }
        String shortName = arrivalInfo.getShortName();
        if (!ListenerUtil.mutListener.listen(1358)) {
            route.setText(shortName.trim());
        }
        if (!ListenerUtil.mutListener.listen(1359)) {
            UIUtils.maybeShrinkRouteName(getContext(), route, shortName.trim());
        }
        if (!ListenerUtil.mutListener.listen(1360)) {
            destination.setText(UIUtils.formatDisplayText(arrivalInfo.getHeadsign()));
        }
        if (!ListenerUtil.mutListener.listen(1361)) {
            status.setText(stopInfo.getStatusText());
        }
        long eta = stopInfo.getEta();
        if (!ListenerUtil.mutListener.listen(1371)) {
            if ((ListenerUtil.mutListener.listen(1366) ? (eta >= 0) : (ListenerUtil.mutListener.listen(1365) ? (eta <= 0) : (ListenerUtil.mutListener.listen(1364) ? (eta > 0) : (ListenerUtil.mutListener.listen(1363) ? (eta < 0) : (ListenerUtil.mutListener.listen(1362) ? (eta != 0) : (eta == 0))))))) {
                if (!ListenerUtil.mutListener.listen(1369)) {
                    etaView.setText(R.string.stop_info_eta_now);
                }
                if (!ListenerUtil.mutListener.listen(1370)) {
                    minView.setVisibility(View.GONE);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(1367)) {
                    etaView.setText(String.valueOf(eta));
                }
                if (!ListenerUtil.mutListener.listen(1368)) {
                    minView.setVisibility(View.VISIBLE);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(1372)) {
            status.setBackgroundResource(R.drawable.round_corners_style_b_status);
        }
        GradientDrawable d = (GradientDrawable) status.getBackground();
        Integer colorCode = stopInfo.getColor();
        int color = context.getResources().getColor(colorCode);
        if (!ListenerUtil.mutListener.listen(1376)) {
            if (stopInfo.getPredicted()) {
                if (!ListenerUtil.mutListener.listen(1374)) {
                    // Show real-time indicator
                    UIUtils.setRealtimeIndicatorColorByResourceCode(realtimeView, colorCode, android.R.color.transparent);
                }
                if (!ListenerUtil.mutListener.listen(1375)) {
                    realtimeView.setVisibility(View.VISIBLE);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(1373)) {
                    realtimeView.setVisibility(View.INVISIBLE);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(1377)) {
            etaView.setTextColor(color);
        }
        if (!ListenerUtil.mutListener.listen(1378)) {
            minView.setTextColor(color);
        }
        if (!ListenerUtil.mutListener.listen(1379)) {
            d.setColor(color);
        }
        // Set padding on status view
        int pSides = UIUtils.dpToPixels(context, 5);
        int pTopBottom = UIUtils.dpToPixels(context, 2);
        if (!ListenerUtil.mutListener.listen(1380)) {
            status.setPadding(pSides, pTopBottom, pSides, pTopBottom);
        }
        if (!ListenerUtil.mutListener.listen(1381)) {
            time.setText(stopInfo.getTimeText());
        }
        if (!ListenerUtil.mutListener.listen(1386)) {
            // Occupancy
            if (stopInfo.getPredictedOccupancy() != null) {
                if (!ListenerUtil.mutListener.listen(1384)) {
                    // Predicted occupancy data
                    UIUtils.setOccupancyVisibilityAndColor(occupancyView, stopInfo.getPredictedOccupancy(), OccupancyState.PREDICTED);
                }
                if (!ListenerUtil.mutListener.listen(1385)) {
                    UIUtils.setOccupancyContentDescription(occupancyView, stopInfo.getPredictedOccupancy(), OccupancyState.PREDICTED);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(1382)) {
                    // Historical occupancy data
                    UIUtils.setOccupancyVisibilityAndColor(occupancyView, stopInfo.getHistoricalOccupancy(), OccupancyState.HISTORICAL);
                }
                if (!ListenerUtil.mutListener.listen(1383)) {
                    UIUtils.setOccupancyContentDescription(occupancyView, stopInfo.getHistoricalOccupancy(), OccupancyState.HISTORICAL);
                }
            }
        }
        ContentValues values = null;
        if (!ListenerUtil.mutListener.listen(1388)) {
            if (mTripsForStop != null) {
                if (!ListenerUtil.mutListener.listen(1387)) {
                    values = mTripsForStop.getValues(arrivalInfo.getTripId());
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(1402)) {
            if (values != null) {
                String reminderName = values.getAsString(ObaContract.Trips.NAME);
                TextView reminder = (TextView) view.findViewById(R.id.reminder);
                if (!ListenerUtil.mutListener.listen(1396)) {
                    if ((ListenerUtil.mutListener.listen(1394) ? (reminderName.length() >= 0) : (ListenerUtil.mutListener.listen(1393) ? (reminderName.length() <= 0) : (ListenerUtil.mutListener.listen(1392) ? (reminderName.length() > 0) : (ListenerUtil.mutListener.listen(1391) ? (reminderName.length() < 0) : (ListenerUtil.mutListener.listen(1390) ? (reminderName.length() != 0) : (reminderName.length() == 0))))))) {
                        if (!ListenerUtil.mutListener.listen(1395)) {
                            reminderName = context.getString(R.string.trip_info_noname);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(1397)) {
                    reminder.setText(reminderName);
                }
                Drawable d2 = reminder.getCompoundDrawables()[0];
                if (!ListenerUtil.mutListener.listen(1398)) {
                    d2 = DrawableCompat.wrap(d2);
                }
                if (!ListenerUtil.mutListener.listen(1399)) {
                    DrawableCompat.setTint(d2.mutate(), view.getResources().getColor(R.color.button_material_dark));
                }
                if (!ListenerUtil.mutListener.listen(1400)) {
                    reminder.setCompoundDrawables(d2, null, null, null);
                }
                if (!ListenerUtil.mutListener.listen(1401)) {
                    reminder.setVisibility(View.VISIBLE);
                }
            } else {
                // this view.
                View reminder = view.findViewById(R.id.reminder);
                if (!ListenerUtil.mutListener.listen(1389)) {
                    reminder.setVisibility(View.GONE);
                }
            }
        }
    }
}
