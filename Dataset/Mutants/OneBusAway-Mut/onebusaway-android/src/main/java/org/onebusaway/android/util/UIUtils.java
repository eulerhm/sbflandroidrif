/*
 * Copyright (C) 2010-2017 Paul Watts (paulcwatts@gmail.com),
 * University of South  Florida (sjbarbeau@gmail.com), Microsoft Corporation
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

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.SearchManager;
import android.content.ActivityNotFoundException;
import android.content.ContentQueryMap;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.location.Location;
import android.media.ExifInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.SystemClock;
import android.provider.Settings;
import android.text.Spannable;
import android.text.TextUtils;
import android.text.format.DateUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.SearchView;
import androidx.core.content.ContextCompat;
import androidx.core.content.pm.ShortcutInfoCompat;
import androidx.core.content.pm.ShortcutManagerCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.graphics.drawable.IconCompat;
import androidx.core.util.Pair;
import androidx.core.view.MenuItemCompat;
import androidx.core.widget.ImageViewCompat;
import androidx.fragment.app.Fragment;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.firebase.analytics.FirebaseAnalytics;
import org.onebusaway.android.R;
import org.onebusaway.android.app.Application;
import org.onebusaway.android.io.ObaAnalytics;
import org.onebusaway.android.io.ObaApi;
import org.onebusaway.android.io.elements.ObaArrivalInfo;
import org.onebusaway.android.io.elements.ObaRegion;
import org.onebusaway.android.io.elements.ObaRoute;
import org.onebusaway.android.io.elements.ObaSituation;
import org.onebusaway.android.io.elements.ObaStop;
import org.onebusaway.android.io.elements.Occupancy;
import org.onebusaway.android.io.elements.OccupancyState;
import org.onebusaway.android.io.request.ObaArrivalInfoResponse;
import org.onebusaway.android.map.MapParams;
import org.onebusaway.android.provider.ObaContract;
import org.onebusaway.android.ui.ArrivalsListActivity;
import org.onebusaway.android.ui.HomeActivity;
import org.onebusaway.android.ui.RouteInfoActivity;
import org.onebusaway.android.view.RealtimeIndicatorView;
import org.onebusaway.util.comparators.AlphanumComparator;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.TimeUnit;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * A class containing utility methods related to the user interface
 */
public final class UIUtils {

    private static final String TAG = "UIHelp";

    public static void setupActionBar(AppCompatActivity activity) {
        ActionBar bar = activity.getSupportActionBar();
        if (!ListenerUtil.mutListener.listen(6960)) {
            bar.setIcon(android.R.color.transparent);
        }
        if (!ListenerUtil.mutListener.listen(6961)) {
            bar.setDisplayShowTitleEnabled(true);
        }
        if (!ListenerUtil.mutListener.listen(6963)) {
            // HomeActivity is the root for all other activities
            if (!(activity instanceof HomeActivity)) {
                if (!ListenerUtil.mutListener.listen(6962)) {
                    bar.setDisplayHomeAsUpEnabled(true);
                }
            }
        }
    }

    /**
     * Sets up the search view in the action bar
     */
    public static void setupSearch(Activity activity, Menu menu) {
        SearchManager searchManager = (SearchManager) activity.getSystemService(Context.SEARCH_SERVICE);
        final MenuItem searchMenu = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchMenu);
        if (!ListenerUtil.mutListener.listen(6964)) {
            searchView.setSearchableInfo(searchManager.getSearchableInfo(activity.getComponentName()));
        }
        if (!ListenerUtil.mutListener.listen(6967)) {
            // Close the keyboard and SearchView at same time when the back button is pressed
            searchView.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener() {

                @Override
                public void onFocusChange(View view, boolean queryTextFocused) {
                    if (!ListenerUtil.mutListener.listen(6966)) {
                        if (!queryTextFocused) {
                            if (!ListenerUtil.mutListener.listen(6965)) {
                                MenuItemCompat.collapseActionView(searchMenu);
                            }
                        }
                    }
                }
            });
        }
    }

    public static void showProgress(Fragment fragment, boolean visible) {
        AppCompatActivity act = (AppCompatActivity) fragment.getActivity();
        if (!ListenerUtil.mutListener.listen(6969)) {
            if (act != null) {
                if (!ListenerUtil.mutListener.listen(6968)) {
                    act.setSupportProgressBarIndeterminateVisibility(visible);
                }
            }
        }
    }

    public static void setClickableSpan(TextView v, ClickableSpan span) {
        Spannable text = (Spannable) v.getText();
        if (!ListenerUtil.mutListener.listen(6970)) {
            text.setSpan(span, 0, text.length(), 0);
        }
        if (!ListenerUtil.mutListener.listen(6971)) {
            v.setMovementMethod(LinkMovementMethod.getInstance());
        }
    }

    public static void removeAllClickableSpans(TextView v) {
        Spannable text = (Spannable) v.getText();
        ClickableSpan[] spans = text.getSpans(0, text.length(), ClickableSpan.class);
        if (!ListenerUtil.mutListener.listen(6973)) {
            {
                long _loopCounter77 = 0;
                for (ClickableSpan cs : spans) {
                    ListenerUtil.loopListener.listen("_loopCounter77", ++_loopCounter77);
                    if (!ListenerUtil.mutListener.listen(6972)) {
                        text.removeSpan(cs);
                    }
                }
            }
        }
    }

    public static int getStopDirectionText(String direction) {
        if (direction.equals("N")) {
            return R.string.direction_n;
        } else if (direction.equals("NW")) {
            return R.string.direction_nw;
        } else if (direction.equals("W")) {
            return R.string.direction_w;
        } else if (direction.equals("SW")) {
            return R.string.direction_sw;
        } else if (direction.equals("S")) {
            return R.string.direction_s;
        } else if (direction.equals("SE")) {
            return R.string.direction_se;
        } else if (direction.equals("E")) {
            return R.string.direction_e;
        } else if (direction.equals("NE")) {
            return R.string.direction_ne;
        } else {
            return R.string.direction_none;
        }
    }

    public static String getRouteDisplayName(String routeShortName, String routeLongName) {
        if (!ListenerUtil.mutListener.listen(6974)) {
            if (!TextUtils.isEmpty(routeShortName)) {
                return routeShortName;
            }
        }
        if (!ListenerUtil.mutListener.listen(6975)) {
            if (!TextUtils.isEmpty(routeLongName)) {
                return routeLongName;
            }
        }
        // Just so we never return null.
        return "";
    }

    public static String getRouteDisplayName(ObaRoute route) {
        return getRouteDisplayName(route.getShortName(), route.getLongName());
    }

    public static String getRouteDisplayName(ObaArrivalInfo arrivalInfo) {
        return getRouteDisplayName(arrivalInfo.getShortName(), arrivalInfo.getRouteLongName());
    }

    public static String getRouteDescription(ObaRoute route) {
        String shortName = route.getShortName();
        String longName = route.getLongName();
        if (!ListenerUtil.mutListener.listen(6977)) {
            if (TextUtils.isEmpty(shortName)) {
                if (!ListenerUtil.mutListener.listen(6976)) {
                    shortName = longName;
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(6980)) {
            if ((ListenerUtil.mutListener.listen(6978) ? (TextUtils.isEmpty(longName) && shortName.equals(longName)) : (TextUtils.isEmpty(longName) || shortName.equals(longName)))) {
                if (!ListenerUtil.mutListener.listen(6979)) {
                    longName = route.getDescription();
                }
            }
        }
        return UIUtils.formatDisplayText(longName);
    }

    /**
     * Returns a formatted displayText for displaying in the UI for stops, routes, and headsigns, or
     * null if the displayText is null.  If the displayText IS ALL CAPS and more than one word and
     * does not contain SPLC (see #883), it will be converted to title case (Is All Caps), otherwise
     * the returned string will match the input.
     *
     * @param displayText displayText to be formatted
     * @return formatted text for stop, route, and heasigns for displaying in the UI, or null if the
     * displayText is null.  If the displayText IS ALL CAPS and more than one word and does not
     * contain SPLC (see #883), it will be converted to title case (Is All Caps), otherwise the
     * returned string will match the input.
     */
    public static String formatDisplayText(String displayText) {
        if (displayText == null) {
            return null;
        }
        // See #883 for "SPLC" logic
        if ((ListenerUtil.mutListener.listen(6982) ? ((ListenerUtil.mutListener.listen(6981) ? (MyTextUtils.isAllCaps(displayText) || displayText.contains(" ")) : (MyTextUtils.isAllCaps(displayText) && displayText.contains(" "))) || !displayText.contains("SPLC")) : ((ListenerUtil.mutListener.listen(6981) ? (MyTextUtils.isAllCaps(displayText) || displayText.contains(" ")) : (MyTextUtils.isAllCaps(displayText) && displayText.contains(" "))) && !displayText.contains("SPLC")))) {
            return MyTextUtils.toTitleCase(displayText);
        } else {
            return displayText;
        }
    }

    // available.
    public static void setStopDirection(View v, String direction, boolean show) {
        final TextView text = (TextView) v;
        final int directionText = UIUtils.getStopDirectionText(direction);
        if (!ListenerUtil.mutListener.listen(6987)) {
            if ((ListenerUtil.mutListener.listen(6983) ? ((directionText != R.string.direction_none) && show) : ((directionText != R.string.direction_none) || show))) {
                if (!ListenerUtil.mutListener.listen(6985)) {
                    text.setText(directionText);
                }
                if (!ListenerUtil.mutListener.listen(6986)) {
                    text.setVisibility(View.VISIBLE);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(6984)) {
                    text.setVisibility(View.GONE);
                }
            }
        }
    }

    // Common code to set a route list item view
    public static void setRouteView(View view, ObaRoute route) {
        TextView shortNameText = (TextView) view.findViewById(R.id.short_name);
        TextView longNameText = (TextView) view.findViewById(R.id.long_name);
        String shortName = route.getShortName();
        String longName = UIUtils.formatDisplayText(route.getLongName());
        if (!ListenerUtil.mutListener.listen(6989)) {
            if (TextUtils.isEmpty(shortName)) {
                if (!ListenerUtil.mutListener.listen(6988)) {
                    shortName = longName;
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(6992)) {
            if ((ListenerUtil.mutListener.listen(6990) ? (TextUtils.isEmpty(longName) && shortName.equals(longName)) : (TextUtils.isEmpty(longName) || shortName.equals(longName)))) {
                if (!ListenerUtil.mutListener.listen(6991)) {
                    longName = UIUtils.formatDisplayText(route.getDescription());
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(6993)) {
            shortNameText.setText(shortName);
        }
        if (!ListenerUtil.mutListener.listen(6994)) {
            longNameText.setText(longName);
        }
    }

    private static final String[] STOP_USER_PROJECTION = { ObaContract.Stops._ID, ObaContract.Stops.FAVORITE, ObaContract.Stops.USER_NAME };

    public static class StopUserInfoMap {

        private final ContentQueryMap mMap;

        public StopUserInfoMap(Context context) {
            ContentResolver cr = context.getContentResolver();
            Cursor c = cr.query(ObaContract.Stops.CONTENT_URI, STOP_USER_PROJECTION, "(" + ObaContract.Stops.USER_NAME + " IS NOT NULL)" + "OR (" + ObaContract.Stops.FAVORITE + "=1)", null, null);
            mMap = new ContentQueryMap(c, ObaContract.Stops._ID, true, null);
        }

        public void close() {
            if (!ListenerUtil.mutListener.listen(6995)) {
                mMap.close();
            }
        }

        public void requery() {
            if (!ListenerUtil.mutListener.listen(6996)) {
                mMap.requery();
            }
        }

        public void setView(View stopRoot, String stopId, String stopName) {
            TextView nameView = (TextView) stopRoot.findViewById(R.id.stop_name);
            if (!ListenerUtil.mutListener.listen(6997)) {
                setView2(nameView, stopId, stopName, true);
            }
        }

        /**
         * This should be used with compound drawables
         */
        public void setView2(TextView nameView, String stopId, String stopName, boolean showIcon) {
            ContentValues values = mMap.getValues(stopId);
            int icon = 0;
            if (!ListenerUtil.mutListener.listen(7008)) {
                if (values != null) {
                    Integer i = values.getAsInteger(ObaContract.Stops.FAVORITE);
                    final boolean favorite = (ListenerUtil.mutListener.listen(7004) ? ((i != null) || ((ListenerUtil.mutListener.listen(7003) ? (i >= 1) : (ListenerUtil.mutListener.listen(7002) ? (i <= 1) : (ListenerUtil.mutListener.listen(7001) ? (i > 1) : (ListenerUtil.mutListener.listen(7000) ? (i < 1) : (ListenerUtil.mutListener.listen(6999) ? (i != 1) : (i == 1)))))))) : ((i != null) && ((ListenerUtil.mutListener.listen(7003) ? (i >= 1) : (ListenerUtil.mutListener.listen(7002) ? (i <= 1) : (ListenerUtil.mutListener.listen(7001) ? (i > 1) : (ListenerUtil.mutListener.listen(7000) ? (i < 1) : (ListenerUtil.mutListener.listen(6999) ? (i != 1) : (i == 1)))))))));
                    final String userName = values.getAsString(ObaContract.Stops.USER_NAME);
                    if (!ListenerUtil.mutListener.listen(7005)) {
                        nameView.setText(TextUtils.isEmpty(userName) ? UIUtils.formatDisplayText(stopName) : userName);
                    }
                    if (!ListenerUtil.mutListener.listen(7007)) {
                        icon = (ListenerUtil.mutListener.listen(7006) ? (favorite || showIcon) : (favorite && showIcon)) ? R.drawable.ic_toggle_star : 0;
                    }
                } else {
                    if (!ListenerUtil.mutListener.listen(6998)) {
                        nameView.setText(UIUtils.formatDisplayText(stopName));
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(7009)) {
                nameView.setCompoundDrawablesWithIntrinsicBounds(icon, 0, 0, 0);
            }
        }
    }

    /**
     * Returns a comma-delimited list of route display names that serve a stop
     * <p/>
     * For example, if a stop was served by "14" and "54", this method will return "14,54"
     *
     * @param stop   the stop for which the route display names should be serialized
     * @param routes a HashMap containing all routes that serve this stop, with the routeId as the
     *               key.
     *               Note that for efficiency this routes HashMap may contain routes that don't
     *               serve this stop as well -
     *               the routes for the stop are referenced via stop.getRouteDisplayNames()
     * @return comma-delimited list of route display names that serve a stop
     */
    public static String serializeRouteDisplayNames(ObaStop stop, HashMap<String, ObaRoute> routes) {
        StringBuffer sb = new StringBuffer();
        String[] routeIds = stop.getRouteIds();
        if (!ListenerUtil.mutListener.listen(7029)) {
            {
                long _loopCounter78 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(7028) ? (i >= routeIds.length) : (ListenerUtil.mutListener.listen(7027) ? (i <= routeIds.length) : (ListenerUtil.mutListener.listen(7026) ? (i > routeIds.length) : (ListenerUtil.mutListener.listen(7025) ? (i != routeIds.length) : (ListenerUtil.mutListener.listen(7024) ? (i == routeIds.length) : (i < routeIds.length)))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter78", ++_loopCounter78);
                    if (!ListenerUtil.mutListener.listen(7012)) {
                        if (routes != null) {
                            ObaRoute route = routes.get(routeIds[i]);
                            if (!ListenerUtil.mutListener.listen(7011)) {
                                sb.append(getRouteDisplayName(route));
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(7010)) {
                                // We don't have route mappings - use routeIds
                                sb.append(routeIds[i]);
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(7023)) {
                        if ((ListenerUtil.mutListener.listen(7021) ? (i >= (ListenerUtil.mutListener.listen(7016) ? (routeIds.length % 1) : (ListenerUtil.mutListener.listen(7015) ? (routeIds.length / 1) : (ListenerUtil.mutListener.listen(7014) ? (routeIds.length * 1) : (ListenerUtil.mutListener.listen(7013) ? (routeIds.length + 1) : (routeIds.length - 1)))))) : (ListenerUtil.mutListener.listen(7020) ? (i <= (ListenerUtil.mutListener.listen(7016) ? (routeIds.length % 1) : (ListenerUtil.mutListener.listen(7015) ? (routeIds.length / 1) : (ListenerUtil.mutListener.listen(7014) ? (routeIds.length * 1) : (ListenerUtil.mutListener.listen(7013) ? (routeIds.length + 1) : (routeIds.length - 1)))))) : (ListenerUtil.mutListener.listen(7019) ? (i > (ListenerUtil.mutListener.listen(7016) ? (routeIds.length % 1) : (ListenerUtil.mutListener.listen(7015) ? (routeIds.length / 1) : (ListenerUtil.mutListener.listen(7014) ? (routeIds.length * 1) : (ListenerUtil.mutListener.listen(7013) ? (routeIds.length + 1) : (routeIds.length - 1)))))) : (ListenerUtil.mutListener.listen(7018) ? (i < (ListenerUtil.mutListener.listen(7016) ? (routeIds.length % 1) : (ListenerUtil.mutListener.listen(7015) ? (routeIds.length / 1) : (ListenerUtil.mutListener.listen(7014) ? (routeIds.length * 1) : (ListenerUtil.mutListener.listen(7013) ? (routeIds.length + 1) : (routeIds.length - 1)))))) : (ListenerUtil.mutListener.listen(7017) ? (i == (ListenerUtil.mutListener.listen(7016) ? (routeIds.length % 1) : (ListenerUtil.mutListener.listen(7015) ? (routeIds.length / 1) : (ListenerUtil.mutListener.listen(7014) ? (routeIds.length * 1) : (ListenerUtil.mutListener.listen(7013) ? (routeIds.length + 1) : (routeIds.length - 1)))))) : (i != (ListenerUtil.mutListener.listen(7016) ? (routeIds.length % 1) : (ListenerUtil.mutListener.listen(7015) ? (routeIds.length / 1) : (ListenerUtil.mutListener.listen(7014) ? (routeIds.length * 1) : (ListenerUtil.mutListener.listen(7013) ? (routeIds.length + 1) : (routeIds.length - 1)))))))))))) {
                            if (!ListenerUtil.mutListener.listen(7022)) {
                                sb.append(",");
                            }
                        }
                    }
                }
            }
        }
        return sb.toString();
    }

    /**
     * Returns a list of route display names from a serialized list of route display names
     * <p/>
     * See {@link #serializeRouteDisplayNames(ObaStop, java.util.HashMap)}
     *
     * @param serializedRouteDisplayNames comma-separate list of routeIds from serializeRouteDisplayNames()
     * @return list of route display names
     */
    public static List<String> deserializeRouteDisplayNames(String serializedRouteDisplayNames) {
        String[] routes = serializedRouteDisplayNames.split(",");
        return Arrays.asList(routes);
    }

    /**
     * Returns a formatted and sorted list of route display names for presentation in a single line
     * <p/>
     * For example, the following list:
     * <p/>
     * 11,1,15, 8b
     * <p/>
     * ...would be formatted as:
     * <p/>
     * 4, 8b, 11, 15
     *
     * @param routeDisplayNames          list of route display names
     * @param nextArrivalRouteShortNames the short route names of the next X arrivals at the stop
     *                                   that are the same.  These will be highlighted in the
     *                                   results.
     * @return a formatted and sorted list of route display names for presentation in a single line
     */
    public static String formatRouteDisplayNames(List<String> routeDisplayNames, List<String> nextArrivalRouteShortNames) {
        if (!ListenerUtil.mutListener.listen(7030)) {
            Collections.sort(routeDisplayNames, new AlphanumComparator());
        }
        StringBuffer sb = new StringBuffer();
        if (!ListenerUtil.mutListener.listen(7053)) {
            {
                long _loopCounter80 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(7052) ? (i >= routeDisplayNames.size()) : (ListenerUtil.mutListener.listen(7051) ? (i <= routeDisplayNames.size()) : (ListenerUtil.mutListener.listen(7050) ? (i > routeDisplayNames.size()) : (ListenerUtil.mutListener.listen(7049) ? (i != routeDisplayNames.size()) : (ListenerUtil.mutListener.listen(7048) ? (i == routeDisplayNames.size()) : (i < routeDisplayNames.size())))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter80", ++_loopCounter80);
                    boolean match = false;
                    if (!ListenerUtil.mutListener.listen(7033)) {
                        {
                            long _loopCounter79 = 0;
                            for (String nextArrivalRouteShortName : nextArrivalRouteShortNames) {
                                ListenerUtil.loopListener.listen("_loopCounter79", ++_loopCounter79);
                                if (!ListenerUtil.mutListener.listen(7032)) {
                                    if (routeDisplayNames.get(i).equalsIgnoreCase(nextArrivalRouteShortName)) {
                                        if (!ListenerUtil.mutListener.listen(7031)) {
                                            match = true;
                                        }
                                        break;
                                    }
                                }
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(7036)) {
                        if (match) {
                            if (!ListenerUtil.mutListener.listen(7035)) {
                                // If this route name matches a route name for the next X arrivals that are the same, highlight this route in the text
                                sb.append(routeDisplayNames.get(i) + "*");
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(7034)) {
                                // Just append the normally-formatted route name
                                sb.append(routeDisplayNames.get(i));
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(7047)) {
                        if ((ListenerUtil.mutListener.listen(7045) ? (i >= (ListenerUtil.mutListener.listen(7040) ? (routeDisplayNames.size() % 1) : (ListenerUtil.mutListener.listen(7039) ? (routeDisplayNames.size() / 1) : (ListenerUtil.mutListener.listen(7038) ? (routeDisplayNames.size() * 1) : (ListenerUtil.mutListener.listen(7037) ? (routeDisplayNames.size() + 1) : (routeDisplayNames.size() - 1)))))) : (ListenerUtil.mutListener.listen(7044) ? (i <= (ListenerUtil.mutListener.listen(7040) ? (routeDisplayNames.size() % 1) : (ListenerUtil.mutListener.listen(7039) ? (routeDisplayNames.size() / 1) : (ListenerUtil.mutListener.listen(7038) ? (routeDisplayNames.size() * 1) : (ListenerUtil.mutListener.listen(7037) ? (routeDisplayNames.size() + 1) : (routeDisplayNames.size() - 1)))))) : (ListenerUtil.mutListener.listen(7043) ? (i > (ListenerUtil.mutListener.listen(7040) ? (routeDisplayNames.size() % 1) : (ListenerUtil.mutListener.listen(7039) ? (routeDisplayNames.size() / 1) : (ListenerUtil.mutListener.listen(7038) ? (routeDisplayNames.size() * 1) : (ListenerUtil.mutListener.listen(7037) ? (routeDisplayNames.size() + 1) : (routeDisplayNames.size() - 1)))))) : (ListenerUtil.mutListener.listen(7042) ? (i < (ListenerUtil.mutListener.listen(7040) ? (routeDisplayNames.size() % 1) : (ListenerUtil.mutListener.listen(7039) ? (routeDisplayNames.size() / 1) : (ListenerUtil.mutListener.listen(7038) ? (routeDisplayNames.size() * 1) : (ListenerUtil.mutListener.listen(7037) ? (routeDisplayNames.size() + 1) : (routeDisplayNames.size() - 1)))))) : (ListenerUtil.mutListener.listen(7041) ? (i == (ListenerUtil.mutListener.listen(7040) ? (routeDisplayNames.size() % 1) : (ListenerUtil.mutListener.listen(7039) ? (routeDisplayNames.size() / 1) : (ListenerUtil.mutListener.listen(7038) ? (routeDisplayNames.size() * 1) : (ListenerUtil.mutListener.listen(7037) ? (routeDisplayNames.size() + 1) : (routeDisplayNames.size() - 1)))))) : (i != (ListenerUtil.mutListener.listen(7040) ? (routeDisplayNames.size() % 1) : (ListenerUtil.mutListener.listen(7039) ? (routeDisplayNames.size() / 1) : (ListenerUtil.mutListener.listen(7038) ? (routeDisplayNames.size() * 1) : (ListenerUtil.mutListener.listen(7037) ? (routeDisplayNames.size() + 1) : (routeDisplayNames.size() - 1)))))))))))) {
                            if (!ListenerUtil.mutListener.listen(7046)) {
                                sb.append(", ");
                            }
                        }
                    }
                }
            }
        }
        return sb.toString();
    }

    /**
     * Generates the dialog text that is used to show detailed information about a particular stop
     *
     * @return a pair of Strings consisting of the <dialog title, dialog message>
     */
    public static Pair<String, String> createStopDetailsDialogText(Context context, String stopName, String stopUserName, String stopCode, String stopDirection, List<String> routeDisplayNames) {
        final String newLine = "\n";
        String title = "";
        StringBuilder message = new StringBuilder();
        if (!ListenerUtil.mutListener.listen(7058)) {
            if (!TextUtils.isEmpty(stopUserName)) {
                if (!ListenerUtil.mutListener.listen(7055)) {
                    title = stopUserName;
                }
                if (!ListenerUtil.mutListener.listen(7057)) {
                    if (stopName != null) {
                        if (!ListenerUtil.mutListener.listen(7056)) {
                            // Show official stop name in addition to user name
                            message.append(context.getString(R.string.stop_info_official_stop_name_label, stopName)).append(newLine);
                        }
                    }
                }
            } else if (stopName != null) {
                if (!ListenerUtil.mutListener.listen(7054)) {
                    title = stopName;
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(7060)) {
            if (stopCode != null) {
                if (!ListenerUtil.mutListener.listen(7059)) {
                    message.append(context.getString(R.string.stop_details_code, stopCode) + newLine);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(7062)) {
            // Routes that serve this stop
            if (routeDisplayNames != null) {
                String routes = context.getString(R.string.stop_info_route_ids_label) + " " + UIUtils.formatRouteDisplayNames(routeDisplayNames, new ArrayList<String>());
                if (!ListenerUtil.mutListener.listen(7061)) {
                    message.append(routes);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(7064)) {
            if (!TextUtils.isEmpty(stopDirection)) {
                if (!ListenerUtil.mutListener.listen(7063)) {
                    message.append(newLine).append(context.getString(UIUtils.getStopDirectionText(stopDirection)));
                }
            }
        }
        return new Pair(title, message.toString());
    }

    /**
     * Builds an AlertDialog with the given title and message
     *
     * @return an AlertDialog with the given title and message
     */
    public static AlertDialog buildAlertDialog(Context context, String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        if (!ListenerUtil.mutListener.listen(7065)) {
            builder.setTitle(title);
        }
        if (!ListenerUtil.mutListener.listen(7066)) {
            builder.setMessage(message);
        }
        return builder.create();
    }

    /**
     * Creates a new shortcut for the provided stop, and returns the ShortcutInfo for that shortcut
     * @param context Context used to create the shortcut
     * @param shortcutName the shortcutName for the stop shortcut
     * @param builder Instance of ArrivalsListActivity.Builder for the provided stop
     * @return the ShortcutInfo for the created shortcut
     */
    public static ShortcutInfoCompat createStopShortcut(Context context, String shortcutName, ArrivalsListActivity.Builder builder) {
        final ShortcutInfoCompat shortcut = UIUtils.makeShortcutInfo(context, shortcutName, builder.getIntent(), R.drawable.ic_stop_flag_triangle);
        if (!ListenerUtil.mutListener.listen(7067)) {
            ShortcutManagerCompat.requestPinShortcut(context, shortcut, null);
        }
        return shortcut;
    }

    /**
     * Creates a new shortcut for the provided route, and returns the ShortcutInfo for that shortcut
     * @param context Context used to create the shortcut
     * @param routeId ID of the route
     * @param routeName short name of the route
     * @return the ShortcutInfo for the created shortcut
     */
    public static ShortcutInfoCompat createRouteShortcut(Context context, String routeId, String routeName) {
        final ShortcutInfoCompat shortcut = UIUtils.makeShortcutInfo(context, routeName, RouteInfoActivity.makeIntent(context, routeId), R.drawable.ic_trip_details);
        if (!ListenerUtil.mutListener.listen(7068)) {
            ShortcutManagerCompat.requestPinShortcut(context, shortcut, null);
        }
        return shortcut;
    }

    /**
     * Default implementation for making a ShortcutInfoCompat object.  Note that this method doesn't
     * create the actual shortcut on the launcher - ShortcutManagerCompat.requestPinShortcut() must
     * be called with the ShortcutInfoCompat returned from this method to create the shortcut
     * on the launcher.
     *
     * @param name       The name of the shortcut
     * @param destIntent The destination intent
     * @param icon       Resource ID for the shortcut icon - should be black so it can be tinted and
     *                   60dp (2dp of asset padding) for high resolution on launcher screens
     * @return ShortcutInfoCompat that can be used to request pinning the shortcut
     */
    public static ShortcutInfoCompat makeShortcutInfo(Context context, String name, Intent destIntent, @DrawableRes int icon) {
        if (!ListenerUtil.mutListener.listen(7069)) {
            // Make sure the shortcut Activity always launches on top (#626)
            destIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        }
        if (!ListenerUtil.mutListener.listen(7070)) {
            destIntent.setAction(Intent.ACTION_VIEW);
        }
        Drawable drawableIcon = ResourcesCompat.getDrawable(context.getResources(), icon, context.getTheme());
        if (!ListenerUtil.mutListener.listen(7071)) {
            drawableIcon.setColorFilter(ContextCompat.getColor(context, R.color.shortcut_icon), PorterDuff.Mode.SRC_IN);
        }
        Drawable drawableBackground = ResourcesCompat.getDrawable(context.getResources(), R.drawable.launcher_background, context.getTheme());
        final LayerDrawable layerDrawable = new LayerDrawable(new Drawable[] { drawableBackground, drawableIcon });
        int backgroundInset = UIUtils.dpToPixels(context, 2.0f);
        if (!ListenerUtil.mutListener.listen(7072)) {
            layerDrawable.setLayerInset(0, backgroundInset, backgroundInset, backgroundInset, backgroundInset);
        }
        int iconInset = UIUtils.dpToPixels(context, 7.0f);
        if (!ListenerUtil.mutListener.listen(7073)) {
            layerDrawable.setLayerInset(1, iconInset, iconInset, iconInset, iconInset);
        }
        final Bitmap b = Bitmap.createBitmap(layerDrawable.getIntrinsicWidth(), layerDrawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        final Canvas canvas = new Canvas(b);
        if (!ListenerUtil.mutListener.listen(7074)) {
            layerDrawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        }
        if (!ListenerUtil.mutListener.listen(7075)) {
            layerDrawable.draw(canvas);
        }
        return new ShortcutInfoCompat.Builder(context, name).setShortLabel(name).setIcon(IconCompat.createWithBitmap(b)).setIntent(destIntent).build();
    }

    public static void goToUrl(Context context, String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        try {
            if (!ListenerUtil.mutListener.listen(7077)) {
                context.startActivity(intent);
            }
        } catch (ActivityNotFoundException e) {
            if (!ListenerUtil.mutListener.listen(7076)) {
                Toast.makeText(context, context.getString(R.string.browser_error), Toast.LENGTH_SHORT).show();
            }
        }
    }

    public static void goToPhoneDialer(Context context, String url) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        if (!ListenerUtil.mutListener.listen(7078)) {
            intent.setData(Uri.parse(url));
        }
        if (!ListenerUtil.mutListener.listen(7079)) {
            context.startActivity(intent);
        }
    }

    /**
     * Opens email apps based on the given email address
     * @param email address
     * @param location string that shows the current location
     */
    public static void sendEmail(Context context, String email, String location) {
        if (!ListenerUtil.mutListener.listen(7080)) {
            sendEmail(context, email, location, null, false);
        }
    }

    /**
     * Opens email apps based on the given email address
     * @param email address
     * @param location string that shows the current location
     * @param tripPlanUrl trip planning URL that failed, if this is a trip problem error report, or null if it's not
     */
    public static void sendEmail(Context context, String email, String location, String tripPlanUrl, boolean tripPlanFail) {
        String obaRegionName = RegionUtils.getObaRegionName();
        boolean autoRegion = Application.getPrefs().getBoolean(context.getString(R.string.preference_key_auto_select_region), true);
        String regionSelectionMethod;
        if (autoRegion) {
            regionSelectionMethod = context.getString(R.string.region_selected_auto);
        } else {
            regionSelectionMethod = context.getString(R.string.region_selected_manually);
        }
        if (!ListenerUtil.mutListener.listen(7081)) {
            UIUtils.sendEmail(context, email, location, obaRegionName, regionSelectionMethod, tripPlanUrl, tripPlanFail);
        }
    }

    /**
     * Opens email apps based on the given email address
     * @param email address
     * @param location string that shows the current location
     * @param regionName name of the current api region
     * @param regionSelectionMethod string that shows if the current api region selected manually or
     *                              automatically
     * @param tripPlanUrl trip planning URL that failed, if this is a trip problem error report, or null if it's not
     */
    private static void sendEmail(Context context, String email, String location, String regionName, String regionSelectionMethod, String tripPlanUrl, boolean tripPlanFail) {
        PackageManager pm = context.getPackageManager();
        PackageInfo appInfoOba;
        PackageInfo appInfoGps;
        String obaVersion = "";
        String googlePlayServicesAppVersion = "";
        try {
            appInfoOba = pm.getPackageInfo(context.getPackageName(), PackageManager.GET_META_DATA);
            if (!ListenerUtil.mutListener.listen(7082)) {
                obaVersion = appInfoOba.versionName;
            }
        } catch (PackageManager.NameNotFoundException e) {
        }
        try {
            appInfoGps = pm.getPackageInfo(GoogleApiAvailability.GOOGLE_PLAY_SERVICES_PACKAGE, 0);
            if (!ListenerUtil.mutListener.listen(7083)) {
                googlePlayServicesAppVersion = appInfoGps.versionName;
            }
        } catch (PackageManager.NameNotFoundException e) {
        }
        String body;
        if (location != null) {
            // Have location
            if (tripPlanUrl == null) {
                // No trip plan
                body = context.getString(R.string.bug_report_body, obaVersion, Build.MODEL, Build.VERSION.RELEASE, Build.VERSION.SDK_INT, googlePlayServicesAppVersion, GoogleApiAvailability.GOOGLE_PLAY_SERVICES_VERSION_CODE, regionName, regionSelectionMethod, location);
            } else {
                // Trip plan
                if (tripPlanFail) {
                    body = context.getString(R.string.bug_report_body_trip_plan_fail, obaVersion, Build.MODEL, Build.VERSION.RELEASE, Build.VERSION.SDK_INT, googlePlayServicesAppVersion, GoogleApiAvailability.GOOGLE_PLAY_SERVICES_VERSION_CODE, regionName, regionSelectionMethod, location, tripPlanUrl);
                } else {
                    body = context.getString(R.string.bug_report_body_trip_plan, obaVersion, Build.MODEL, Build.VERSION.RELEASE, Build.VERSION.SDK_INT, googlePlayServicesAppVersion, GoogleApiAvailability.GOOGLE_PLAY_SERVICES_VERSION_CODE, regionName, regionSelectionMethod, location, tripPlanUrl);
                }
            }
        } else {
            // No location
            if (tripPlanUrl == null) {
                // No trip plan
                body = context.getString(R.string.bug_report_body_without_location, obaVersion, Build.MODEL, Build.VERSION.RELEASE, Build.VERSION.SDK_INT);
            } else {
                // Trip plan
                if (tripPlanFail) {
                    body = context.getString(R.string.bug_report_body_trip_plan_without_location_fail, obaVersion, Build.MODEL, Build.VERSION.RELEASE, Build.VERSION.SDK_INT, tripPlanUrl);
                } else {
                    body = context.getString(R.string.bug_report_body_trip_plan_without_location, obaVersion, Build.MODEL, Build.VERSION.RELEASE, Build.VERSION.SDK_INT, tripPlanUrl);
                }
            }
        }
        Intent send = new Intent(Intent.ACTION_SEND);
        if (!ListenerUtil.mutListener.listen(7084)) {
            send.putExtra(Intent.EXTRA_EMAIL, new String[] { email });
        }
        // Show trip planner subject line if we have a trip planning URL
        String subject;
        if (tripPlanUrl == null) {
            if (tripPlanFail) {
                subject = context.getString(R.string.bug_report_subject_trip_plan);
            } else {
                subject = context.getString(R.string.bug_report_subject);
            }
        } else {
            if (tripPlanFail) {
                subject = context.getString(R.string.bug_report_subject_trip_plan_fail);
            } else {
                subject = context.getString(R.string.bug_report_subject_trip_plan);
            }
        }
        if (!ListenerUtil.mutListener.listen(7085)) {
            send.putExtra(Intent.EXTRA_SUBJECT, subject);
        }
        if (!ListenerUtil.mutListener.listen(7086)) {
            send.putExtra(Intent.EXTRA_TEXT, body);
        }
        if (!ListenerUtil.mutListener.listen(7087)) {
            send.setType("message/rfc822");
        }
        try {
            if (!ListenerUtil.mutListener.listen(7089)) {
                context.startActivity(Intent.createChooser(send, subject));
            }
        } catch (ActivityNotFoundException e) {
            if (!ListenerUtil.mutListener.listen(7088)) {
                Toast.makeText(context, R.string.bug_report_error, Toast.LENGTH_LONG).show();
            }
        }
    }

    public static String getRouteErrorString(Context context, int code) {
        if (!isConnected(context)) {
            if (isAirplaneMode(context)) {
                return context.getString(R.string.airplane_mode_error);
            } else {
                return context.getString(R.string.no_network_error);
            }
        }
        switch(code) {
            case ObaApi.OBA_INTERNAL_ERROR:
                return context.getString(R.string.internal_error);
            case ObaApi.OBA_NOT_FOUND:
                ObaRegion r = Application.get().getCurrentRegion();
                if (r != null) {
                    return context.getString(R.string.route_not_found_error_with_region_name, r.getName());
                } else {
                    return context.getString(R.string.route_not_found_error_no_region);
                }
            case ObaApi.OBA_BAD_GATEWAY:
                return context.getString(R.string.bad_gateway_error);
            case ObaApi.OBA_OUT_OF_MEMORY:
                return context.getString(R.string.out_of_memory_error);
            default:
                return context.getString(R.string.generic_comm_error);
        }
    }

    public static String getStopErrorString(Context context, int code) {
        if (!isConnected(context)) {
            if (isAirplaneMode(context)) {
                return context.getString(R.string.airplane_mode_error);
            } else {
                return context.getString(R.string.no_network_error);
            }
        }
        switch(code) {
            case ObaApi.OBA_INTERNAL_ERROR:
                return context.getString(R.string.internal_error);
            case ObaApi.OBA_NOT_FOUND:
                ObaRegion r = Application.get().getCurrentRegion();
                if (r != null) {
                    return context.getString(R.string.stop_not_found_error_with_region_name, r.getName());
                } else {
                    return context.getString(R.string.stop_not_found_error_no_region);
                }
            case ObaApi.OBA_BAD_GATEWAY:
                return context.getString(R.string.bad_gateway_error);
            case ObaApi.OBA_OUT_OF_MEMORY:
                return context.getString(R.string.out_of_memory_error);
            default:
                return context.getString(R.string.generic_comm_error);
        }
    }

    /**
     * Returns the resource ID for a user-friendly error message based on device state (if a
     * network
     * connection is available or airplane mode is on) or an OBA REST API response code
     *
     * @param code The status code (one of the ObaApi.OBA_* constants)
     * @return the resource ID for a user-friendly error message based on device state (if a network
     * connection is available or airplane mode is on) or an OBA REST API response code
     */
    public static int getMapErrorString(Context context, int code) {
        if (!isConnected(context)) {
            if (isAirplaneMode(context)) {
                return R.string.airplane_mode_error;
            } else {
                return R.string.no_network_error;
            }
        }
        switch(code) {
            case ObaApi.OBA_INTERNAL_ERROR:
                return R.string.internal_error;
            case ObaApi.OBA_BAD_GATEWAY:
                return R.string.bad_gateway_error;
            case ObaApi.OBA_OUT_OF_MEMORY:
                return R.string.out_of_memory_error;
            default:
                return R.string.map_generic_error;
        }
    }

    /**
     * Returns true if the device is in Airplane Mode, and false if the device isn't in Airplane
     * mode or if it can't be determined
     * @param context
     * @return true if the device is in Airplane Mode, and false if the device isn't in Airplane
     * mode or if it can't be determined
     */
    public static boolean isAirplaneMode(Context context) {
        if (!ListenerUtil.mutListener.listen(7090)) {
            if (context == null) {
                // If the context is null, we can't get airplane mode state - assume no
                return false;
            }
        }
        ContentResolver cr = context.getContentResolver();
        return Settings.System.getInt(cr, Settings.System.AIRPLANE_MODE_ON, 0) != 0;
    }

    /**
     * Returns true if the device is connected to a network, and false if the device isn't or if it
     * can't be determined
     * @param context
     * @return true if the device is connected to a network, and false if the device isn't or if it
     * can't be determined
     */
    public static boolean isConnected(Context context) {
        if (!ListenerUtil.mutListener.listen(7091)) {
            if (context == null) {
                // If the context is null, we can't get connected state - assume yes
                return true;
            }
        }
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return (ListenerUtil.mutListener.listen(7092) ? ((activeNetwork != null) || activeNetwork.isConnectedOrConnecting()) : ((activeNetwork != null) && activeNetwork.isConnectedOrConnecting()));
    }

    /**
     * Returns the first string for the query URI.
     */
    public static String stringForQuery(Context context, Uri uri, String column) {
        ContentResolver cr = context.getContentResolver();
        Cursor c = cr.query(uri, new String[] { column }, null, null, null);
        if (!ListenerUtil.mutListener.listen(7095)) {
            if (c != null) {
                try {
                    if (!ListenerUtil.mutListener.listen(7094)) {
                        if (c.moveToFirst()) {
                            return c.getString(0);
                        }
                    }
                } finally {
                    if (!ListenerUtil.mutListener.listen(7093)) {
                        c.close();
                    }
                }
            }
        }
        return "";
    }

    public static Integer intForQuery(Context context, Uri uri, String column) {
        ContentResolver cr = context.getContentResolver();
        Cursor c = cr.query(uri, new String[] { column }, null, null, null);
        if (!ListenerUtil.mutListener.listen(7098)) {
            if (c != null) {
                try {
                    if (!ListenerUtil.mutListener.listen(7097)) {
                        if (c.moveToFirst()) {
                            return c.getInt(0);
                        }
                    }
                } finally {
                    if (!ListenerUtil.mutListener.listen(7096)) {
                        c.close();
                    }
                }
            }
        }
        return null;
    }

    public static final int MINUTES_IN_HOUR = 60;

    /**
     * Takes the number of minutes, and returns a user-readable string
     * saying the number of minutes in which no arrivals are coming,
     * or the number of hours and minutes if minutes if minutes > 60
     *
     * @param minutes            number of minutes for which there are no upcoming arrivals
     * @param additionalArrivals true if the response should include the word additional, false if
     *                           it should not
     * @param shortFormat        true if the format should be abbreviated, false if it should be
     *                           long
     * @return a user-readable string saying the number of minutes in which no arrivals are coming,
     * or the number of hours and minutes if minutes > 60
     */
    public static String getNoArrivalsMessage(Context context, int minutes, boolean additionalArrivals, boolean shortFormat) {
        if ((ListenerUtil.mutListener.listen(7103) ? (minutes >= MINUTES_IN_HOUR) : (ListenerUtil.mutListener.listen(7102) ? (minutes > MINUTES_IN_HOUR) : (ListenerUtil.mutListener.listen(7101) ? (minutes < MINUTES_IN_HOUR) : (ListenerUtil.mutListener.listen(7100) ? (minutes != MINUTES_IN_HOUR) : (ListenerUtil.mutListener.listen(7099) ? (minutes == MINUTES_IN_HOUR) : (minutes <= MINUTES_IN_HOUR))))))) {
            // Return just minutes
            if (additionalArrivals) {
                if (shortFormat) {
                    // Abbreviated version
                    return context.getString(R.string.stop_info_no_additional_data_minutes_short_format, minutes);
                } else {
                    // Long version
                    return context.getString(R.string.stop_info_no_additional_data_minutes, minutes);
                }
            } else {
                if (shortFormat) {
                    // Abbreviated version
                    return context.getString(R.string.stop_info_nodata_minutes_short_format, minutes);
                } else {
                    // Long version
                    return context.getString(R.string.stop_info_nodata_minutes, minutes);
                }
            }
        } else {
            // Return hours and minutes
            if (additionalArrivals) {
                if (shortFormat) {
                    // Abbreviated version
                    return context.getResources().getQuantityString(R.plurals.stop_info_no_additional_data_hours_minutes_short_format, (ListenerUtil.mutListener.listen(7143) ? (minutes % 60) : (ListenerUtil.mutListener.listen(7142) ? (minutes * 60) : (ListenerUtil.mutListener.listen(7141) ? (minutes - 60) : (ListenerUtil.mutListener.listen(7140) ? (minutes + 60) : (minutes / 60))))), (ListenerUtil.mutListener.listen(7147) ? (minutes / 60) : (ListenerUtil.mutListener.listen(7146) ? (minutes * 60) : (ListenerUtil.mutListener.listen(7145) ? (minutes - 60) : (ListenerUtil.mutListener.listen(7144) ? (minutes + 60) : (minutes % 60))))), (ListenerUtil.mutListener.listen(7151) ? (minutes % 60) : (ListenerUtil.mutListener.listen(7150) ? (minutes * 60) : (ListenerUtil.mutListener.listen(7149) ? (minutes - 60) : (ListenerUtil.mutListener.listen(7148) ? (minutes + 60) : (minutes / 60))))));
                } else {
                    // Long version
                    return context.getResources().getQuantityString(R.plurals.stop_info_no_additional_data_hours_minutes, (ListenerUtil.mutListener.listen(7131) ? (minutes % 60) : (ListenerUtil.mutListener.listen(7130) ? (minutes * 60) : (ListenerUtil.mutListener.listen(7129) ? (minutes - 60) : (ListenerUtil.mutListener.listen(7128) ? (minutes + 60) : (minutes / 60))))), (ListenerUtil.mutListener.listen(7135) ? (minutes / 60) : (ListenerUtil.mutListener.listen(7134) ? (minutes * 60) : (ListenerUtil.mutListener.listen(7133) ? (minutes - 60) : (ListenerUtil.mutListener.listen(7132) ? (minutes + 60) : (minutes % 60))))), (ListenerUtil.mutListener.listen(7139) ? (minutes % 60) : (ListenerUtil.mutListener.listen(7138) ? (minutes * 60) : (ListenerUtil.mutListener.listen(7137) ? (minutes - 60) : (ListenerUtil.mutListener.listen(7136) ? (minutes + 60) : (minutes / 60))))));
                }
            } else {
                if (shortFormat) {
                    // Abbreviated version
                    return context.getResources().getQuantityString(R.plurals.stop_info_nodata_hours_minutes_short_format, (ListenerUtil.mutListener.listen(7119) ? (minutes % 60) : (ListenerUtil.mutListener.listen(7118) ? (minutes * 60) : (ListenerUtil.mutListener.listen(7117) ? (minutes - 60) : (ListenerUtil.mutListener.listen(7116) ? (minutes + 60) : (minutes / 60))))), (ListenerUtil.mutListener.listen(7123) ? (minutes / 60) : (ListenerUtil.mutListener.listen(7122) ? (minutes * 60) : (ListenerUtil.mutListener.listen(7121) ? (minutes - 60) : (ListenerUtil.mutListener.listen(7120) ? (minutes + 60) : (minutes % 60))))), (ListenerUtil.mutListener.listen(7127) ? (minutes % 60) : (ListenerUtil.mutListener.listen(7126) ? (minutes * 60) : (ListenerUtil.mutListener.listen(7125) ? (minutes - 60) : (ListenerUtil.mutListener.listen(7124) ? (minutes + 60) : (minutes / 60))))));
                } else {
                    // Long version
                    return context.getResources().getQuantityString(R.plurals.stop_info_nodata_hours_minutes, (ListenerUtil.mutListener.listen(7107) ? (minutes % 60) : (ListenerUtil.mutListener.listen(7106) ? (minutes * 60) : (ListenerUtil.mutListener.listen(7105) ? (minutes - 60) : (ListenerUtil.mutListener.listen(7104) ? (minutes + 60) : (minutes / 60))))), (ListenerUtil.mutListener.listen(7111) ? (minutes / 60) : (ListenerUtil.mutListener.listen(7110) ? (minutes * 60) : (ListenerUtil.mutListener.listen(7109) ? (minutes - 60) : (ListenerUtil.mutListener.listen(7108) ? (minutes + 60) : (minutes % 60))))), (ListenerUtil.mutListener.listen(7115) ? (minutes % 60) : (ListenerUtil.mutListener.listen(7114) ? (minutes * 60) : (ListenerUtil.mutListener.listen(7113) ? (minutes - 60) : (ListenerUtil.mutListener.listen(7112) ? (minutes + 60) : (minutes / 60))))));
                }
            }
        }
    }

    /**
     * Returns true if the activity is still active and dialogs can be managed (i.e., displayed
     * or dismissed), or false if it is
     * not
     *
     * @param activity Activity to check for displaying/dismissing a dialog
     * @return true if the activity is still active and dialogs can be managed, or false if it is
     * not
     */
    public static boolean canManageDialog(Activity activity) {
        if (activity == null) {
            return false;
        }
        if ((ListenerUtil.mutListener.listen(7156) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.JELLY_BEAN_MR1) : (ListenerUtil.mutListener.listen(7155) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN_MR1) : (ListenerUtil.mutListener.listen(7154) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR1) : (ListenerUtil.mutListener.listen(7153) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.JELLY_BEAN_MR1) : (ListenerUtil.mutListener.listen(7152) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.JELLY_BEAN_MR1) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1))))))) {
            return (ListenerUtil.mutListener.listen(7157) ? (!activity.isFinishing() || !activity.isDestroyed()) : (!activity.isFinishing() && !activity.isDestroyed()));
        } else {
            return !activity.isFinishing();
        }
    }

    /**
     * Returns true if the context is an Activity and is still active and dialogs can be managed
     * (i.e., displayed or dismissed) OR the context is not an Activity, or false if the Activity
     * is
     * no longer active.
     *
     * NOTE: We really shouldn't display dialogs from a Service - a notification is a better way
     * to communicate with the user.
     *
     * @param context Context to check for displaying/dismissing a dialog
     * @return true if the context is an Activity and is still active and dialogs can be managed
     * (i.e., displayed or dismissed) OR the context is not an Activity, or false if the Activity
     * is
     * no longer active
     */
    public static boolean canManageDialog(Context context) {
        if (context == null) {
            return false;
        }
        if (context instanceof Activity) {
            return canManageDialog((Activity) context);
        } else {
            // need to do this, we don't have any way of checking whether its possible
            return true;
        }
    }

    /**
     * Returns true if the API level supports animating Views using ViewPropertyAnimator, false if
     * it doesn't
     *
     * @return true if the API level supports animating Views using ViewPropertyAnimator, false if
     * it doesn't
     */
    public static boolean canAnimateViewModern() {
        return (ListenerUtil.mutListener.listen(7162) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.HONEYCOMB_MR1) : (ListenerUtil.mutListener.listen(7161) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.HONEYCOMB_MR1) : (ListenerUtil.mutListener.listen(7160) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB_MR1) : (ListenerUtil.mutListener.listen(7159) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.HONEYCOMB_MR1) : (ListenerUtil.mutListener.listen(7158) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.HONEYCOMB_MR1) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR1))))));
    }

    /**
     * Returns true if the API level supports canceling existing animations via the
     * ViewPropertyAnimator, and false if it does not
     *
     * @return true if the API level supports canceling existing animations via the
     * ViewPropertyAnimator, and false if it does not
     */
    public static boolean canCancelAnimation() {
        return (ListenerUtil.mutListener.listen(7167) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.ICE_CREAM_SANDWICH) : (ListenerUtil.mutListener.listen(7166) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.ICE_CREAM_SANDWICH) : (ListenerUtil.mutListener.listen(7165) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.ICE_CREAM_SANDWICH) : (ListenerUtil.mutListener.listen(7164) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.ICE_CREAM_SANDWICH) : (ListenerUtil.mutListener.listen(7163) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.ICE_CREAM_SANDWICH) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH))))));
    }

    /**
     * Returns true if the API level supports our Arrival Info Style B (sort by route) views, false
     * if it does not.  See #350 and #275.
     *
     * @return true if the API level supports our Arrival Info Style B (sort by route) views, false
     * if it does not
     */
    public static boolean canSupportArrivalInfoStyleB() {
        return (ListenerUtil.mutListener.listen(7172) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.ICE_CREAM_SANDWICH) : (ListenerUtil.mutListener.listen(7171) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.ICE_CREAM_SANDWICH) : (ListenerUtil.mutListener.listen(7170) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.ICE_CREAM_SANDWICH) : (ListenerUtil.mutListener.listen(7169) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.ICE_CREAM_SANDWICH) : (ListenerUtil.mutListener.listen(7168) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.ICE_CREAM_SANDWICH) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH))))));
    }

    /**
     * Shows a view, using animation if the platform supports it
     *
     * @param v                 View to show
     * @param animationDuration duration of animation
     */
    @TargetApi(14)
    public static void showViewWithAnimation(final View v, int animationDuration) {
        if (!ListenerUtil.mutListener.listen(7174)) {
            // If we're on a legacy device, show the view without the animation
            if (!canAnimateViewModern()) {
                if (!ListenerUtil.mutListener.listen(7173)) {
                    showViewWithoutAnimation(v);
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(7176)) {
            if ((ListenerUtil.mutListener.listen(7175) ? (v.getVisibility() == View.VISIBLE || v.getAlpha() == 1) : (v.getVisibility() == View.VISIBLE && v.getAlpha() == 1))) {
                // View is already visible and not transparent, return without doing anything
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(7177)) {
            v.clearAnimation();
        }
        if (!ListenerUtil.mutListener.listen(7179)) {
            if (canCancelAnimation()) {
                if (!ListenerUtil.mutListener.listen(7178)) {
                    v.animate().cancel();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(7182)) {
            if (v.getVisibility() != View.VISIBLE) {
                if (!ListenerUtil.mutListener.listen(7180)) {
                    // (but fully transparent) during the animation.
                    v.setAlpha(0f);
                }
                if (!ListenerUtil.mutListener.listen(7181)) {
                    v.setVisibility(View.VISIBLE);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(7183)) {
            // Animate the content view to 100% opacity, and clear any animation listener set on the view.
            v.animate().alpha(1f).setDuration(animationDuration).setListener(null);
        }
    }

    /**
     * Shows a view without using animation
     *
     * @param v View to show
     */
    public static void showViewWithoutAnimation(final View v) {
        if (!ListenerUtil.mutListener.listen(7184)) {
            if (v.getVisibility() == View.VISIBLE) {
                // View is already visible, return without doing anything
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(7185)) {
            v.setVisibility(View.VISIBLE);
        }
    }

    /**
     * Hides a view, using animation if the platform supports it
     *
     * @param v                 View to hide
     * @param animationDuration duration of animation
     */
    @TargetApi(14)
    public static void hideViewWithAnimation(final View v, int animationDuration) {
        if (!ListenerUtil.mutListener.listen(7187)) {
            // If we're on a legacy device, hide the view without the animation
            if (!canAnimateViewModern()) {
                if (!ListenerUtil.mutListener.listen(7186)) {
                    hideViewWithoutAnimation(v);
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(7188)) {
            if (v.getVisibility() == View.GONE) {
                // View is already gone, return without doing anything
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(7189)) {
            v.clearAnimation();
        }
        if (!ListenerUtil.mutListener.listen(7191)) {
            if (canCancelAnimation()) {
                if (!ListenerUtil.mutListener.listen(7190)) {
                    v.animate().cancel();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(7193)) {
            // an optimization step (it won't participate in layout passes, etc.)
            v.animate().alpha(0f).setDuration(animationDuration).setListener(new AnimatorListenerAdapter() {

                @Override
                public void onAnimationEnd(Animator animation) {
                    if (!ListenerUtil.mutListener.listen(7192)) {
                        v.setVisibility(View.GONE);
                    }
                }
            });
        }
    }

    /**
     * Hides a view without using animation
     *
     * @param v View to hide
     */
    public static void hideViewWithoutAnimation(final View v) {
        if (!ListenerUtil.mutListener.listen(7194)) {
            if (v.getVisibility() == View.GONE) {
                // View is already gone, return without doing anything
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(7195)) {
            // Hide the view without animation
            v.setVisibility(View.GONE);
        }
    }

    /**
     * Prints View visibility information to the log for debugging purposes
     *
     * @param v View to log visibility information for
     */
    @TargetApi(12)
    public static void logViewVisibility(View v) {
        if (!ListenerUtil.mutListener.listen(7203)) {
            if (v != null) {
                if (!ListenerUtil.mutListener.listen(7202)) {
                    if (v.getVisibility() == View.VISIBLE) {
                        if (!ListenerUtil.mutListener.listen(7199)) {
                            Log.d(TAG, v.getContext().getResources().getResourceEntryName(v.getId()) + " is visible");
                        }
                        if (!ListenerUtil.mutListener.listen(7201)) {
                            if (UIUtils.canAnimateViewModern()) {
                                if (!ListenerUtil.mutListener.listen(7200)) {
                                    Log.d(TAG, v.getContext().getResources().getResourceEntryName(v.getId()) + " alpha - " + v.getAlpha());
                                }
                            }
                        }
                    } else if (v.getVisibility() == View.INVISIBLE) {
                        if (!ListenerUtil.mutListener.listen(7198)) {
                            Log.d(TAG, v.getContext().getResources().getResourceEntryName(v.getId()) + " is INVISIBLE");
                        }
                    } else if (v.getVisibility() == View.GONE) {
                        if (!ListenerUtil.mutListener.listen(7197)) {
                            Log.d(TAG, v.getContext().getResources().getResourceEntryName(v.getId()) + " is GONE");
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(7196)) {
                            Log.d(TAG, v.getContext().getResources().getResourceEntryName(v.getId()) + ".getVisibility() - " + v.getVisibility());
                        }
                    }
                }
            }
        }
    }

    /**
     * Converts screen dimension units from dp to pixels, based on algorithm defined in
     * http://developer.android.com/guide/practices/screens_support.html#dips-pels
     *
     * @param dp value in dp
     * @return value in pixels
     */
    public static int dpToPixels(Context context, float dp) {
        // Get the screen's density scale
        final float scale = context.getResources().getDisplayMetrics().density;
        // Convert the dps to pixels, based on density scale
        return (int) ((ListenerUtil.mutListener.listen(7211) ? ((ListenerUtil.mutListener.listen(7207) ? (dp % scale) : (ListenerUtil.mutListener.listen(7206) ? (dp / scale) : (ListenerUtil.mutListener.listen(7205) ? (dp - scale) : (ListenerUtil.mutListener.listen(7204) ? (dp + scale) : (dp * scale))))) % 0.5f) : (ListenerUtil.mutListener.listen(7210) ? ((ListenerUtil.mutListener.listen(7207) ? (dp % scale) : (ListenerUtil.mutListener.listen(7206) ? (dp / scale) : (ListenerUtil.mutListener.listen(7205) ? (dp - scale) : (ListenerUtil.mutListener.listen(7204) ? (dp + scale) : (dp * scale))))) / 0.5f) : (ListenerUtil.mutListener.listen(7209) ? ((ListenerUtil.mutListener.listen(7207) ? (dp % scale) : (ListenerUtil.mutListener.listen(7206) ? (dp / scale) : (ListenerUtil.mutListener.listen(7205) ? (dp - scale) : (ListenerUtil.mutListener.listen(7204) ? (dp + scale) : (dp * scale))))) * 0.5f) : (ListenerUtil.mutListener.listen(7208) ? ((ListenerUtil.mutListener.listen(7207) ? (dp % scale) : (ListenerUtil.mutListener.listen(7206) ? (dp / scale) : (ListenerUtil.mutListener.listen(7205) ? (dp - scale) : (ListenerUtil.mutListener.listen(7204) ? (dp + scale) : (dp * scale))))) - 0.5f) : ((ListenerUtil.mutListener.listen(7207) ? (dp % scale) : (ListenerUtil.mutListener.listen(7206) ? (dp / scale) : (ListenerUtil.mutListener.listen(7205) ? (dp - scale) : (ListenerUtil.mutListener.listen(7204) ? (dp + scale) : (dp * scale))))) + 0.5f))))));
    }

    /**
     * Sets the margins for a given view
     *
     * @param v View to set the margin for
     * @param l left margin, in pixels
     * @param t top margin, in pixels
     * @param r right margin, in pixels
     * @param b bottom margin, in pixels
     */
    public static void setMargins(View v, int l, int t, int r, int b) {
        ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) v.getLayoutParams();
        if (!ListenerUtil.mutListener.listen(7212)) {
            p.setMargins(l, t, r, b);
        }
        if (!ListenerUtil.mutListener.listen(7213)) {
            v.setLayoutParams(p);
        }
    }

    /**
     * Formats a view so it is ignored for accessible access
     */
    public static void setAccessibilityIgnore(View view) {
        if (!ListenerUtil.mutListener.listen(7214)) {
            view.setClickable(false);
        }
        if (!ListenerUtil.mutListener.listen(7215)) {
            view.setFocusable(false);
        }
        if (!ListenerUtil.mutListener.listen(7216)) {
            view.setContentDescription("");
        }
        if (!ListenerUtil.mutListener.listen(7223)) {
            if ((ListenerUtil.mutListener.listen(7221) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.JELLY_BEAN) : (ListenerUtil.mutListener.listen(7220) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN) : (ListenerUtil.mutListener.listen(7219) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) : (ListenerUtil.mutListener.listen(7218) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.JELLY_BEAN) : (ListenerUtil.mutListener.listen(7217) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.JELLY_BEAN) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN))))))) {
                if (!ListenerUtil.mutListener.listen(7222)) {
                    view.setImportantForAccessibility(View.IMPORTANT_FOR_ACCESSIBILITY_NO);
                }
            }
        }
    }

    /**
     * Builds the list of Strings that should be shown for a given trip "Bus Options" menu,
     * provided the arguments for that trip
     *
     * @param c                 Context
     * @param isRouteFavorite   true if this route is a user favorite, false if it is not
     * @param hasUrl            true if the route provides a URL for schedule data, false if it does
     *                          not
     * @param isReminderVisible true if the reminder is currently visible for a trip, false if it
     *                          is
     *                          not
     * @param occupancy occupancy of this trip
     * @param occupancyState occupanceState of this trip
     * @return the list of Strings that should be shown for a given trip, provided the arguments for
     * that trip
     */
    public static List<String> buildTripOptions(Context c, boolean isRouteFavorite, boolean hasUrl, boolean isReminderVisible, boolean hasRouteFilter, Occupancy occupancy, OccupancyState occupancyState) {
        ArrayList<String> list = new ArrayList<>();
        if (!ListenerUtil.mutListener.listen(7226)) {
            if (!isRouteFavorite) {
                if (!ListenerUtil.mutListener.listen(7225)) {
                    list.add(c.getString(R.string.bus_options_menu_add_star));
                }
            } else {
                if (!ListenerUtil.mutListener.listen(7224)) {
                    list.add(c.getString(R.string.bus_options_menu_remove_star));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(7227)) {
            list.add(c.getString(R.string.bus_options_menu_show_route_on_map));
        }
        if (!ListenerUtil.mutListener.listen(7228)) {
            list.add(c.getString(R.string.bus_options_menu_show_trip_details));
        }
        if (!ListenerUtil.mutListener.listen(7231)) {
            if (!isReminderVisible) {
                if (!ListenerUtil.mutListener.listen(7230)) {
                    list.add(c.getString(R.string.bus_options_menu_set_reminder));
                }
            } else {
                if (!ListenerUtil.mutListener.listen(7229)) {
                    list.add(c.getString(R.string.bus_options_menu_edit_reminder));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(7234)) {
            if (!hasRouteFilter) {
                if (!ListenerUtil.mutListener.listen(7233)) {
                    list.add(c.getString(R.string.bus_options_menu_show_only_this_route));
                }
            } else {
                if (!ListenerUtil.mutListener.listen(7232)) {
                    list.add(c.getString(R.string.bus_options_menu_show_all_routes));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(7236)) {
            if (hasUrl) {
                if (!ListenerUtil.mutListener.listen(7235)) {
                    list.add(c.getString(R.string.bus_options_menu_show_route_schedule));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(7237)) {
            list.add(c.getString(R.string.bus_options_menu_report_trip_problem));
        }
        if (!ListenerUtil.mutListener.listen(7241)) {
            if (occupancy != null) {
                if (!ListenerUtil.mutListener.listen(7240)) {
                    if (occupancyState == OccupancyState.HISTORICAL) {
                        if (!ListenerUtil.mutListener.listen(7239)) {
                            list.add(c.getString(R.string.menu_title_about_historical_occupancy));
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(7238)) {
                            list.add(c.getString(R.string.menu_title_about_occupancy));
                        }
                    }
                }
            }
        }
        return list;
    }

    /**
     * Builds the array of icons that should be shown for the trip "Bus Options" menu, given the
     * provided arguments for that trip
     *
     * @param isRouteFavorite   true if this route is a user favorite, false if it is not
     * @param hasUrl true if the route provides a URL for schedule data, false if it does
     *               not
     * @param occupancy occupancy of this trip
     * @return the array of icons that should be shown for a given trip
     */
    public static List<Integer> buildTripOptionsIcons(boolean isRouteFavorite, boolean hasUrl, Occupancy occupancy) {
        ArrayList<Integer> list = new ArrayList<>();
        if (!ListenerUtil.mutListener.listen(7244)) {
            if (!isRouteFavorite) {
                if (!ListenerUtil.mutListener.listen(7243)) {
                    list.add(R.drawable.focus_star_on);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(7242)) {
                    list.add(R.drawable.focus_star_off);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(7245)) {
            list.add(R.drawable.ic_arrivals_styleb_action_map);
        }
        if (!ListenerUtil.mutListener.listen(7246)) {
            list.add(R.drawable.ic_trip_details);
        }
        if (!ListenerUtil.mutListener.listen(7247)) {
            list.add(R.drawable.ic_drawer_alarm);
        }
        if (!ListenerUtil.mutListener.listen(7248)) {
            list.add(R.drawable.ic_content_filter_list);
        }
        if (!ListenerUtil.mutListener.listen(7250)) {
            if (hasUrl) {
                if (!ListenerUtil.mutListener.listen(7249)) {
                    list.add(R.drawable.ic_notification_event_note);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(7251)) {
            list.add(R.drawable.ic_alert_warning);
        }
        if (!ListenerUtil.mutListener.listen(7253)) {
            if (occupancy != null) {
                if (!ListenerUtil.mutListener.listen(7252)) {
                    list.add(R.drawable.ic_occupancy);
                }
            }
        }
        return list;
    }

    /**
     * Sets the line and fill colors for real-time indicator circles contained in the provided
     * realtime_indicator.xml layout.  There are several circles, so each needs to be set
     * individually.  The resource code for the color to be used should be provided.
     *
     * @param vg        realtime_indicator.xml layout
     * @param lineColor resource code color to be used as line color, or null to use the default
     *                  colors
     * @param fillColor resource code color to be used as fill color, or null to use the default
     *                  colors
     */
    public static void setRealtimeIndicatorColorByResourceCode(ViewGroup vg, Integer lineColor, Integer fillColor) {
        Resources r = vg.getResources();
        if (!ListenerUtil.mutListener.listen(7254)) {
            setRealtimeIndicatorColor(vg, r.getColor(lineColor), r.getColor(fillColor));
        }
    }

    /**
     * Sets the line and fill colors for real-time indicator circles contained in the provided
     * realtime_indicator.xml layout.  There are several circles, so each needs to be set
     * individually.  The integer representation of the color to be used should be provided.
     *
     * @param vg        realtime_indicator.xml layout
     * @param lineColor color to be used as line color, or null to use the default colors
     * @param fillColor color to be used as fill color, or null to use the default colors
     */
    public static void setRealtimeIndicatorColor(ViewGroup vg, Integer lineColor, Integer fillColor) {
        if (!ListenerUtil.mutListener.listen(7267)) {
            {
                long _loopCounter81 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(7266) ? (i >= vg.getChildCount()) : (ListenerUtil.mutListener.listen(7265) ? (i <= vg.getChildCount()) : (ListenerUtil.mutListener.listen(7264) ? (i > vg.getChildCount()) : (ListenerUtil.mutListener.listen(7263) ? (i != vg.getChildCount()) : (ListenerUtil.mutListener.listen(7262) ? (i == vg.getChildCount()) : (i < vg.getChildCount())))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter81", ++_loopCounter81);
                    View v = vg.getChildAt(i);
                    if (!ListenerUtil.mutListener.listen(7261)) {
                        if (v instanceof RealtimeIndicatorView) {
                            if (!ListenerUtil.mutListener.listen(7257)) {
                                if (lineColor != null) {
                                    if (!ListenerUtil.mutListener.listen(7256)) {
                                        ((RealtimeIndicatorView) v).setLineColor(lineColor);
                                    }
                                } else {
                                    if (!ListenerUtil.mutListener.listen(7255)) {
                                        // Use default color
                                        ((RealtimeIndicatorView) v).setLineColor(R.color.realtime_indicator_line);
                                    }
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(7260)) {
                                if (fillColor != null) {
                                    if (!ListenerUtil.mutListener.listen(7259)) {
                                        ((RealtimeIndicatorView) v).setFillColor(fillColor);
                                    }
                                } else {
                                    if (!ListenerUtil.mutListener.listen(7258)) {
                                        // Use default color
                                        ((RealtimeIndicatorView) v).setLineColor(R.color.realtime_indicator_fill);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * Creates a new Bitmap, with the black color of the source image changed to the given color.
     * The source Bitmap isn't modified.
     *
     * @param source the source Bitmap with a black background
     * @param color  the color to change the black color to
     * @return the resulting Bitmap that has the black changed to the color
     */
    public static Bitmap colorBitmap(Bitmap source, int color) {
        int width = source.getWidth();
        int height = source.getHeight();
        int[] pixels = new int[(ListenerUtil.mutListener.listen(7271) ? (width % height) : (ListenerUtil.mutListener.listen(7270) ? (width / height) : (ListenerUtil.mutListener.listen(7269) ? (width - height) : (ListenerUtil.mutListener.listen(7268) ? (width + height) : (width * height)))))];
        if (!ListenerUtil.mutListener.listen(7272)) {
            source.getPixels(pixels, 0, width, 0, 0, width, height);
        }
        if (!ListenerUtil.mutListener.listen(7279)) {
            {
                long _loopCounter82 = 0;
                for (int x = 0; (ListenerUtil.mutListener.listen(7278) ? (x >= pixels.length) : (ListenerUtil.mutListener.listen(7277) ? (x <= pixels.length) : (ListenerUtil.mutListener.listen(7276) ? (x > pixels.length) : (ListenerUtil.mutListener.listen(7275) ? (x != pixels.length) : (ListenerUtil.mutListener.listen(7274) ? (x == pixels.length) : (x < pixels.length)))))); ++x) {
                    ListenerUtil.loopListener.listen("_loopCounter82", ++_loopCounter82);
                    if (!ListenerUtil.mutListener.listen(7273)) {
                        pixels[x] = (pixels[x] == Color.BLACK) ? color : pixels[x];
                    }
                }
            }
        }
        Bitmap out = Bitmap.createBitmap(width, height, source.getConfig());
        if (!ListenerUtil.mutListener.listen(7280)) {
            out.setPixels(pixels, 0, width, 0, 0, width, height);
        }
        return out;
    }

    /**
     * Returns true if the provided touch event was within the provided view
     *
     * @return true if the provided touch event was within the provided view
     */
    public static boolean isTouchInView(View view, MotionEvent event) {
        Rect rect = new Rect();
        if (!ListenerUtil.mutListener.listen(7281)) {
            view.getGlobalVisibleRect(rect);
        }
        return rect.contains((int) event.getRawX(), (int) event.getRawY());
    }

    /**
     * Returns the current time for comparison against another current time.  For API levels >=
     * Jelly Bean MR1 the SystemClock.getElapsedRealtimeNanos() method is used, and for API levels
     * <
     * Jelly Bean MR1 System.currentTimeMillis() is used.
     *
     * @return the current time for comparison against another current time, in nanoseconds
     */
    public static long getCurrentTimeForComparison() {
        if ((ListenerUtil.mutListener.listen(7286) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.JELLY_BEAN_MR1) : (ListenerUtil.mutListener.listen(7285) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN_MR1) : (ListenerUtil.mutListener.listen(7284) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR1) : (ListenerUtil.mutListener.listen(7283) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.JELLY_BEAN_MR1) : (ListenerUtil.mutListener.listen(7282) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.JELLY_BEAN_MR1) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1))))))) {
            // Use elapsed real-time nanos, since its guaranteed monotonic
            return SystemClock.elapsedRealtimeNanos();
        } else {
            return TimeUnit.MILLISECONDS.toNanos(System.currentTimeMillis());
        }
    }

    /**
     * Open the soft keyboard
     */
    public static void openKeyboard(Context context) {
        InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (!ListenerUtil.mutListener.listen(7287)) {
            inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    /**
     * Closes the soft keyboard
     */
    public static void closeKeyboard(Context context, View v) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (!ListenerUtil.mutListener.listen(7288)) {
            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
        }
    }

    /**
     * Returns a list of all situations (service alerts) that are specific to the stop, routes, and
     * agency for the provided arrivals-and-departures-for-stop response.  For route-specific alerts, this
     * involves looping through the routes and checking the references element to see if there are
     * any route-specific alerts, and adding them to the list to be shown above the list of
     * arrivals for a stop.  See #700.
     *
     * @param response response from arrivals-and-departures-for-stop API
     * @param filter   list of route_ids to retrieve service alerts for, or null to retrieve service
     *                 alerts for all routes. Note that this filter only affects alerts scoped to
     *                 routes - it does not affect alerts scoped to stops or agencies
     * @return a list of all situations (service alerts) that are specific to the stop, routes, and
     * agency. If a route filter list is provided, situations for all stops and agencies are included
     * in the returned list, but only situations scoped for route_ids in the provided filter list are
     * included in the returned list (i.e., situations specified for route_ids that aren't in the
     * filter list are excluded).
     */
    public static List<ObaSituation> getAllSituations(final ObaArrivalInfoResponse response, List<String> filter) {
        List<ObaSituation> allSituations = new ArrayList<>();
        if (!ListenerUtil.mutListener.listen(7289)) {
            // Add agency-wide and stop-specific alerts
            allSituations.addAll(response.getSituations());
        }
        // Add all existing Ids to a HashSet for O(1) retrieval (vs. list)
        HashSet<String> allIds = new HashSet<>();
        if (!ListenerUtil.mutListener.listen(7291)) {
            {
                long _loopCounter83 = 0;
                for (ObaSituation s : allSituations) {
                    ListenerUtil.loopListener.listen("_loopCounter83", ++_loopCounter83);
                    if (!ListenerUtil.mutListener.listen(7290)) {
                        allIds.add(s.getId());
                    }
                }
            }
        }
        // Do the same for filtered routes
        HashSet<String> filterIds = new HashSet<>();
        if (!ListenerUtil.mutListener.listen(7295)) {
            if ((ListenerUtil.mutListener.listen(7292) ? (filter != null || !filter.isEmpty()) : (filter != null && !filter.isEmpty()))) {
                if (!ListenerUtil.mutListener.listen(7294)) {
                    {
                        long _loopCounter84 = 0;
                        for (String routeId : filter) {
                            ListenerUtil.loopListener.listen("_loopCounter84", ++_loopCounter84);
                            if (!ListenerUtil.mutListener.listen(7293)) {
                                filterIds.add(routeId);
                            }
                        }
                    }
                }
            }
        }
        // it's situations in the returned list.
        ObaArrivalInfo[] info = response.getArrivalInfo();
        if (!ListenerUtil.mutListener.listen(7302)) {
            {
                long _loopCounter86 = 0;
                for (ObaArrivalInfo i : info) {
                    ListenerUtil.loopListener.listen("_loopCounter86", ++_loopCounter86);
                    if (!ListenerUtil.mutListener.listen(7301)) {
                        if ((ListenerUtil.mutListener.listen(7296) ? (filterIds.isEmpty() && filterIds.contains(i.getRouteId())) : (filterIds.isEmpty() || filterIds.contains(i.getRouteId())))) {
                            if (!ListenerUtil.mutListener.listen(7300)) {
                                {
                                    long _loopCounter85 = 0;
                                    for (String situationId : i.getSituationIds()) {
                                        ListenerUtil.loopListener.listen("_loopCounter85", ++_loopCounter85);
                                        if (!ListenerUtil.mutListener.listen(7299)) {
                                            if (!allIds.contains(situationId)) {
                                                if (!ListenerUtil.mutListener.listen(7297)) {
                                                    allIds.add(situationId);
                                                }
                                                if (!ListenerUtil.mutListener.listen(7298)) {
                                                    allSituations.add(response.getSituation(situationId));
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return allSituations;
    }

    /**
     * Returns true if the provided currentTime falls within the situation's (i.e., alert's) active
     * windows or if the situation does not provide an active window, and false if the currentTime
     * falls outside of the situation's active windows
     *
     * @param currentTime the time to compare to the situation's windows, in milliseconds between
     *                    the current time and midnight, January 1, 1970 UTC
     * @return true if the provided currentTime falls within the situation's (i.e., alert's) active
     * windows or if the situation does not provide an active window, and false if the currentTime
     * falls outside of the situation's active windows
     */
    public static boolean isActiveWindowForSituation(ObaSituation situation, long currentTime) {
        if (!ListenerUtil.mutListener.listen(7303)) {
            if (situation.getActiveWindows().length == 0) {
                // We assume a situation is active if it doesn't contain any active window information
                return true;
            }
        }
        // Active window times are in seconds since epoch
        long currentTimeSeconds = TimeUnit.MILLISECONDS.toSeconds(currentTime);
        boolean isActiveWindowForSituation = false;
        if (!ListenerUtil.mutListener.listen(7323)) {
            {
                long _loopCounter87 = 0;
                for (ObaSituation.ActiveWindow activeWindow : situation.getActiveWindows()) {
                    ListenerUtil.loopListener.listen("_loopCounter87", ++_loopCounter87);
                    long from = activeWindow.getFrom();
                    long to = activeWindow.getTo();
                    if (!ListenerUtil.mutListener.listen(7322)) {
                        // 0 is a valid end time that means no end to the window - see #990
                        if ((ListenerUtil.mutListener.listen(7320) ? ((ListenerUtil.mutListener.listen(7308) ? (from >= currentTimeSeconds) : (ListenerUtil.mutListener.listen(7307) ? (from > currentTimeSeconds) : (ListenerUtil.mutListener.listen(7306) ? (from < currentTimeSeconds) : (ListenerUtil.mutListener.listen(7305) ? (from != currentTimeSeconds) : (ListenerUtil.mutListener.listen(7304) ? (from == currentTimeSeconds) : (from <= currentTimeSeconds)))))) || ((ListenerUtil.mutListener.listen(7319) ? ((ListenerUtil.mutListener.listen(7313) ? (to >= 0) : (ListenerUtil.mutListener.listen(7312) ? (to <= 0) : (ListenerUtil.mutListener.listen(7311) ? (to > 0) : (ListenerUtil.mutListener.listen(7310) ? (to < 0) : (ListenerUtil.mutListener.listen(7309) ? (to != 0) : (to == 0)))))) && (ListenerUtil.mutListener.listen(7318) ? (currentTimeSeconds >= to) : (ListenerUtil.mutListener.listen(7317) ? (currentTimeSeconds > to) : (ListenerUtil.mutListener.listen(7316) ? (currentTimeSeconds < to) : (ListenerUtil.mutListener.listen(7315) ? (currentTimeSeconds != to) : (ListenerUtil.mutListener.listen(7314) ? (currentTimeSeconds == to) : (currentTimeSeconds <= to))))))) : ((ListenerUtil.mutListener.listen(7313) ? (to >= 0) : (ListenerUtil.mutListener.listen(7312) ? (to <= 0) : (ListenerUtil.mutListener.listen(7311) ? (to > 0) : (ListenerUtil.mutListener.listen(7310) ? (to < 0) : (ListenerUtil.mutListener.listen(7309) ? (to != 0) : (to == 0)))))) || (ListenerUtil.mutListener.listen(7318) ? (currentTimeSeconds >= to) : (ListenerUtil.mutListener.listen(7317) ? (currentTimeSeconds > to) : (ListenerUtil.mutListener.listen(7316) ? (currentTimeSeconds < to) : (ListenerUtil.mutListener.listen(7315) ? (currentTimeSeconds != to) : (ListenerUtil.mutListener.listen(7314) ? (currentTimeSeconds == to) : (currentTimeSeconds <= to)))))))))) : ((ListenerUtil.mutListener.listen(7308) ? (from >= currentTimeSeconds) : (ListenerUtil.mutListener.listen(7307) ? (from > currentTimeSeconds) : (ListenerUtil.mutListener.listen(7306) ? (from < currentTimeSeconds) : (ListenerUtil.mutListener.listen(7305) ? (from != currentTimeSeconds) : (ListenerUtil.mutListener.listen(7304) ? (from == currentTimeSeconds) : (from <= currentTimeSeconds)))))) && ((ListenerUtil.mutListener.listen(7319) ? ((ListenerUtil.mutListener.listen(7313) ? (to >= 0) : (ListenerUtil.mutListener.listen(7312) ? (to <= 0) : (ListenerUtil.mutListener.listen(7311) ? (to > 0) : (ListenerUtil.mutListener.listen(7310) ? (to < 0) : (ListenerUtil.mutListener.listen(7309) ? (to != 0) : (to == 0)))))) && (ListenerUtil.mutListener.listen(7318) ? (currentTimeSeconds >= to) : (ListenerUtil.mutListener.listen(7317) ? (currentTimeSeconds > to) : (ListenerUtil.mutListener.listen(7316) ? (currentTimeSeconds < to) : (ListenerUtil.mutListener.listen(7315) ? (currentTimeSeconds != to) : (ListenerUtil.mutListener.listen(7314) ? (currentTimeSeconds == to) : (currentTimeSeconds <= to))))))) : ((ListenerUtil.mutListener.listen(7313) ? (to >= 0) : (ListenerUtil.mutListener.listen(7312) ? (to <= 0) : (ListenerUtil.mutListener.listen(7311) ? (to > 0) : (ListenerUtil.mutListener.listen(7310) ? (to < 0) : (ListenerUtil.mutListener.listen(7309) ? (to != 0) : (to == 0)))))) || (ListenerUtil.mutListener.listen(7318) ? (currentTimeSeconds >= to) : (ListenerUtil.mutListener.listen(7317) ? (currentTimeSeconds > to) : (ListenerUtil.mutListener.listen(7316) ? (currentTimeSeconds < to) : (ListenerUtil.mutListener.listen(7315) ? (currentTimeSeconds != to) : (ListenerUtil.mutListener.listen(7314) ? (currentTimeSeconds == to) : (currentTimeSeconds <= to)))))))))))) {
                            if (!ListenerUtil.mutListener.listen(7321)) {
                                isActiveWindowForSituation = true;
                            }
                            break;
                        }
                    }
                }
            }
        }
        return isActiveWindowForSituation;
    }

    /**
     * Returns the time formatting as "1:10pm" to be displayed as an absolute time for an
     * arrival/departure
     *
     * @param time an arrival or departure time (e.g., from ArrivalInfo)
     * @return the time formatting as "1:10pm" to be displayed as an absolute time for an
     * arrival/departure
     */
    public static String formatTime(Context context, long time) {
        return DateUtils.formatDateTime(context, time, DateUtils.FORMAT_SHOW_TIME | DateUtils.FORMAT_NO_NOON | DateUtils.FORMAT_NO_MIDNIGHT);
    }

    /**
     * Set smaller text size if the route short name has more than 3 characters
     *
     * @param view Text view
     * @param routeShortName Route short name
     */
    public static void maybeShrinkRouteName(Context context, TextView view, String routeShortName) {
        if (!ListenerUtil.mutListener.listen(7341)) {
            if ((ListenerUtil.mutListener.listen(7328) ? (routeShortName.length() >= 4) : (ListenerUtil.mutListener.listen(7327) ? (routeShortName.length() <= 4) : (ListenerUtil.mutListener.listen(7326) ? (routeShortName.length() > 4) : (ListenerUtil.mutListener.listen(7325) ? (routeShortName.length() != 4) : (ListenerUtil.mutListener.listen(7324) ? (routeShortName.length() == 4) : (routeShortName.length() < 4))))))) {
                // No-op if text is short enough to fit
                return;
            } else if ((ListenerUtil.mutListener.listen(7333) ? (routeShortName.length() >= 4) : (ListenerUtil.mutListener.listen(7332) ? (routeShortName.length() <= 4) : (ListenerUtil.mutListener.listen(7331) ? (routeShortName.length() > 4) : (ListenerUtil.mutListener.listen(7330) ? (routeShortName.length() < 4) : (ListenerUtil.mutListener.listen(7329) ? (routeShortName.length() != 4) : (routeShortName.length() == 4))))))) {
                if (!ListenerUtil.mutListener.listen(7340)) {
                    view.setTextSize(TypedValue.COMPLEX_UNIT_PX, context.getResources().getDimension(R.dimen.route_name_text_size_medium));
                }
            } else if ((ListenerUtil.mutListener.listen(7338) ? (routeShortName.length() >= 4) : (ListenerUtil.mutListener.listen(7337) ? (routeShortName.length() <= 4) : (ListenerUtil.mutListener.listen(7336) ? (routeShortName.length() < 4) : (ListenerUtil.mutListener.listen(7335) ? (routeShortName.length() != 4) : (ListenerUtil.mutListener.listen(7334) ? (routeShortName.length() == 4) : (routeShortName.length() > 4))))))) {
                if (!ListenerUtil.mutListener.listen(7339)) {
                    view.setTextSize(TypedValue.COMPLEX_UNIT_PX, context.getResources().getDimension(R.dimen.route_name_text_size_small));
                }
            }
        }
    }

    /**
     * Transforms a given opaque color into the same color but with the given alpha value
     *
     * @param solidColor hex color value that is completely opaque
     * @param alpha      Specify an alpha value. 0 means fully transparent, and 255 means fully
     *                   opaque.
     * @return the provided color with the given alpha value
     */
    public static int getTransparentColor(int solidColor, int alpha) {
        int r = Color.red(solidColor);
        int g = Color.green(solidColor);
        int b = Color.blue(solidColor);
        return Color.argb(alpha, r, g, b);
    }

    /**
     * Returns the location of the map center if it has been previously saved in the bundle, or
     * null if it wasn't saved in the bundle.
     *
     * @param b bundle to check for the map center
     * @return the location of the map center if it has been previously saved in the bundle, or null
     * if it wasn't saved in the bundle.
     */
    public static Location getMapCenter(Bundle b) {
        if (!ListenerUtil.mutListener.listen(7342)) {
            if (b == null) {
                return null;
            }
        }
        Location center = null;
        double lat = b.getDouble(MapParams.CENTER_LAT);
        double lon = b.getDouble(MapParams.CENTER_LON);
        if (!ListenerUtil.mutListener.listen(7355)) {
            if ((ListenerUtil.mutListener.listen(7353) ? ((ListenerUtil.mutListener.listen(7347) ? (lat >= 0.0) : (ListenerUtil.mutListener.listen(7346) ? (lat <= 0.0) : (ListenerUtil.mutListener.listen(7345) ? (lat > 0.0) : (ListenerUtil.mutListener.listen(7344) ? (lat < 0.0) : (ListenerUtil.mutListener.listen(7343) ? (lat == 0.0) : (lat != 0.0)))))) || (ListenerUtil.mutListener.listen(7352) ? (lon >= 0.0) : (ListenerUtil.mutListener.listen(7351) ? (lon <= 0.0) : (ListenerUtil.mutListener.listen(7350) ? (lon > 0.0) : (ListenerUtil.mutListener.listen(7349) ? (lon < 0.0) : (ListenerUtil.mutListener.listen(7348) ? (lon == 0.0) : (lon != 0.0))))))) : ((ListenerUtil.mutListener.listen(7347) ? (lat >= 0.0) : (ListenerUtil.mutListener.listen(7346) ? (lat <= 0.0) : (ListenerUtil.mutListener.listen(7345) ? (lat > 0.0) : (ListenerUtil.mutListener.listen(7344) ? (lat < 0.0) : (ListenerUtil.mutListener.listen(7343) ? (lat == 0.0) : (lat != 0.0)))))) && (ListenerUtil.mutListener.listen(7352) ? (lon >= 0.0) : (ListenerUtil.mutListener.listen(7351) ? (lon <= 0.0) : (ListenerUtil.mutListener.listen(7350) ? (lon > 0.0) : (ListenerUtil.mutListener.listen(7349) ? (lon < 0.0) : (ListenerUtil.mutListener.listen(7348) ? (lon == 0.0) : (lon != 0.0))))))))) {
                if (!ListenerUtil.mutListener.listen(7354)) {
                    center = LocationUtils.makeLocation(lat, lon);
                }
            }
        }
        return center;
    }

    /**
     * Creates a JPEG image file with the current date/time as the name
     *
     * @param nameSuffix A string that will be added to the end of the file name, or null if
     *                   nothing
     *                   should be added
     * @return a JPEG image file with the current date/time as the name
     */
    public static File createImageFile(Context context, String nameSuffix) throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        StringBuilder imageFileName = new StringBuilder();
        if (!ListenerUtil.mutListener.listen(7356)) {
            imageFileName.append("JPEG_");
        }
        if (!ListenerUtil.mutListener.listen(7357)) {
            imageFileName.append(timeStamp);
        }
        if (!ListenerUtil.mutListener.listen(7358)) {
            imageFileName.append("_");
        }
        if (!ListenerUtil.mutListener.listen(7360)) {
            if (nameSuffix != null) {
                if (!ListenerUtil.mutListener.listen(7359)) {
                    imageFileName.append(nameSuffix);
                }
            }
        }
        File storageDir = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        return File.createTempFile(imageFileName.toString(), /* prefix */
        ".jpg", /* suffix */
        storageDir);
    }

    /**
     * Decode a smaller sampled bitmap given a large bitmap.
     * Adapted from https://developer.android.com/training/displaying-bitmaps/load-bitmap.html and
     * http://stackoverflow.com/a/31720143/937715.
     *
     * @param pathName  path to the full size image file
     * @param reqWidth  desired width
     * @param reqHeight desired height
     * @return a smaller version of the image at pathName, given the desired width and height
     */
    public static Bitmap decodeSampledBitmapFromFile(String pathName, int reqWidth, int reqHeight) throws IOException {
        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        if (!ListenerUtil.mutListener.listen(7361)) {
            options.inJustDecodeBounds = true;
        }
        if (!ListenerUtil.mutListener.listen(7362)) {
            BitmapFactory.decodeFile(pathName, options);
        }
        if (!ListenerUtil.mutListener.listen(7363)) {
            // Calculate inSampleSize
            options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);
        }
        if (!ListenerUtil.mutListener.listen(7364)) {
            // Decode bitmap with inSampleSize set
            options.inJustDecodeBounds = false;
        }
        Bitmap b = BitmapFactory.decodeFile(pathName, options);
        return rotateImageIfRequired(b, pathName);
    }

    /**
     * Calculate an inSampleSize for use in a {@link BitmapFactory.Options} object when decoding
     * bitmaps using the decode* methods from {@link BitmapFactory}. This implementation calculates
     * the closest inSampleSize that will result in the final decoded bitmap having a width and
     * height equal to or larger than the requested width and height. This implementation does not
     * ensure a power of 2 is returned for inSampleSize which can be faster when decoding but
     * results in a larger bitmap which isn't as useful for caching purposes.
     *
     * From http://stackoverflow.com/a/31720143/937715.
     *
     * @param options   An options object with out* params already populated (run through a decode*
     *                  method with inJustDecodeBounds==true
     * @param reqWidth  The requested width of the resulting bitmap
     * @param reqHeight The requested height of the resulting bitmap
     * @return The value to be used for inSampleSize
     */
    private static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;
        if (!ListenerUtil.mutListener.listen(7417)) {
            if ((ListenerUtil.mutListener.listen(7375) ? ((ListenerUtil.mutListener.listen(7369) ? (height >= reqHeight) : (ListenerUtil.mutListener.listen(7368) ? (height <= reqHeight) : (ListenerUtil.mutListener.listen(7367) ? (height < reqHeight) : (ListenerUtil.mutListener.listen(7366) ? (height != reqHeight) : (ListenerUtil.mutListener.listen(7365) ? (height == reqHeight) : (height > reqHeight)))))) && (ListenerUtil.mutListener.listen(7374) ? (width >= reqWidth) : (ListenerUtil.mutListener.listen(7373) ? (width <= reqWidth) : (ListenerUtil.mutListener.listen(7372) ? (width < reqWidth) : (ListenerUtil.mutListener.listen(7371) ? (width != reqWidth) : (ListenerUtil.mutListener.listen(7370) ? (width == reqWidth) : (width > reqWidth))))))) : ((ListenerUtil.mutListener.listen(7369) ? (height >= reqHeight) : (ListenerUtil.mutListener.listen(7368) ? (height <= reqHeight) : (ListenerUtil.mutListener.listen(7367) ? (height < reqHeight) : (ListenerUtil.mutListener.listen(7366) ? (height != reqHeight) : (ListenerUtil.mutListener.listen(7365) ? (height == reqHeight) : (height > reqHeight)))))) || (ListenerUtil.mutListener.listen(7374) ? (width >= reqWidth) : (ListenerUtil.mutListener.listen(7373) ? (width <= reqWidth) : (ListenerUtil.mutListener.listen(7372) ? (width < reqWidth) : (ListenerUtil.mutListener.listen(7371) ? (width != reqWidth) : (ListenerUtil.mutListener.listen(7370) ? (width == reqWidth) : (width > reqWidth))))))))) {
                // Calculate ratios of height and width to requested height and width
                final int heightRatio = Math.round((ListenerUtil.mutListener.listen(7379) ? ((float) height % (float) reqHeight) : (ListenerUtil.mutListener.listen(7378) ? ((float) height * (float) reqHeight) : (ListenerUtil.mutListener.listen(7377) ? ((float) height - (float) reqHeight) : (ListenerUtil.mutListener.listen(7376) ? ((float) height + (float) reqHeight) : ((float) height / (float) reqHeight))))));
                final int widthRatio = Math.round((ListenerUtil.mutListener.listen(7383) ? ((float) width % (float) reqWidth) : (ListenerUtil.mutListener.listen(7382) ? ((float) width * (float) reqWidth) : (ListenerUtil.mutListener.listen(7381) ? ((float) width - (float) reqWidth) : (ListenerUtil.mutListener.listen(7380) ? ((float) width + (float) reqWidth) : ((float) width / (float) reqWidth))))));
                if (!ListenerUtil.mutListener.listen(7389)) {
                    // with both dimensions larger than or equal to the requested height and width.
                    inSampleSize = (ListenerUtil.mutListener.listen(7388) ? (heightRatio >= widthRatio) : (ListenerUtil.mutListener.listen(7387) ? (heightRatio <= widthRatio) : (ListenerUtil.mutListener.listen(7386) ? (heightRatio > widthRatio) : (ListenerUtil.mutListener.listen(7385) ? (heightRatio != widthRatio) : (ListenerUtil.mutListener.listen(7384) ? (heightRatio == widthRatio) : (heightRatio < widthRatio)))))) ? heightRatio : widthRatio;
                }
                final float totalPixels = (ListenerUtil.mutListener.listen(7393) ? (width % height) : (ListenerUtil.mutListener.listen(7392) ? (width / height) : (ListenerUtil.mutListener.listen(7391) ? (width - height) : (ListenerUtil.mutListener.listen(7390) ? (width + height) : (width * height)))));
                // Anything more than 2x the requested pixels we'll sample down further
                final float totalReqPixelsCap = (ListenerUtil.mutListener.listen(7401) ? ((ListenerUtil.mutListener.listen(7397) ? (reqWidth % reqHeight) : (ListenerUtil.mutListener.listen(7396) ? (reqWidth / reqHeight) : (ListenerUtil.mutListener.listen(7395) ? (reqWidth - reqHeight) : (ListenerUtil.mutListener.listen(7394) ? (reqWidth + reqHeight) : (reqWidth * reqHeight))))) % 2) : (ListenerUtil.mutListener.listen(7400) ? ((ListenerUtil.mutListener.listen(7397) ? (reqWidth % reqHeight) : (ListenerUtil.mutListener.listen(7396) ? (reqWidth / reqHeight) : (ListenerUtil.mutListener.listen(7395) ? (reqWidth - reqHeight) : (ListenerUtil.mutListener.listen(7394) ? (reqWidth + reqHeight) : (reqWidth * reqHeight))))) / 2) : (ListenerUtil.mutListener.listen(7399) ? ((ListenerUtil.mutListener.listen(7397) ? (reqWidth % reqHeight) : (ListenerUtil.mutListener.listen(7396) ? (reqWidth / reqHeight) : (ListenerUtil.mutListener.listen(7395) ? (reqWidth - reqHeight) : (ListenerUtil.mutListener.listen(7394) ? (reqWidth + reqHeight) : (reqWidth * reqHeight))))) - 2) : (ListenerUtil.mutListener.listen(7398) ? ((ListenerUtil.mutListener.listen(7397) ? (reqWidth % reqHeight) : (ListenerUtil.mutListener.listen(7396) ? (reqWidth / reqHeight) : (ListenerUtil.mutListener.listen(7395) ? (reqWidth - reqHeight) : (ListenerUtil.mutListener.listen(7394) ? (reqWidth + reqHeight) : (reqWidth * reqHeight))))) + 2) : ((ListenerUtil.mutListener.listen(7397) ? (reqWidth % reqHeight) : (ListenerUtil.mutListener.listen(7396) ? (reqWidth / reqHeight) : (ListenerUtil.mutListener.listen(7395) ? (reqWidth - reqHeight) : (ListenerUtil.mutListener.listen(7394) ? (reqWidth + reqHeight) : (reqWidth * reqHeight))))) * 2)))));
                if (!ListenerUtil.mutListener.listen(7416)) {
                    {
                        long _loopCounter88 = 0;
                        while ((ListenerUtil.mutListener.listen(7415) ? ((ListenerUtil.mutListener.listen(7410) ? (totalPixels % ((ListenerUtil.mutListener.listen(7406) ? (inSampleSize % inSampleSize) : (ListenerUtil.mutListener.listen(7405) ? (inSampleSize / inSampleSize) : (ListenerUtil.mutListener.listen(7404) ? (inSampleSize - inSampleSize) : (ListenerUtil.mutListener.listen(7403) ? (inSampleSize + inSampleSize) : (inSampleSize * inSampleSize))))))) : (ListenerUtil.mutListener.listen(7409) ? (totalPixels * ((ListenerUtil.mutListener.listen(7406) ? (inSampleSize % inSampleSize) : (ListenerUtil.mutListener.listen(7405) ? (inSampleSize / inSampleSize) : (ListenerUtil.mutListener.listen(7404) ? (inSampleSize - inSampleSize) : (ListenerUtil.mutListener.listen(7403) ? (inSampleSize + inSampleSize) : (inSampleSize * inSampleSize))))))) : (ListenerUtil.mutListener.listen(7408) ? (totalPixels - ((ListenerUtil.mutListener.listen(7406) ? (inSampleSize % inSampleSize) : (ListenerUtil.mutListener.listen(7405) ? (inSampleSize / inSampleSize) : (ListenerUtil.mutListener.listen(7404) ? (inSampleSize - inSampleSize) : (ListenerUtil.mutListener.listen(7403) ? (inSampleSize + inSampleSize) : (inSampleSize * inSampleSize))))))) : (ListenerUtil.mutListener.listen(7407) ? (totalPixels + ((ListenerUtil.mutListener.listen(7406) ? (inSampleSize % inSampleSize) : (ListenerUtil.mutListener.listen(7405) ? (inSampleSize / inSampleSize) : (ListenerUtil.mutListener.listen(7404) ? (inSampleSize - inSampleSize) : (ListenerUtil.mutListener.listen(7403) ? (inSampleSize + inSampleSize) : (inSampleSize * inSampleSize))))))) : (totalPixels / ((ListenerUtil.mutListener.listen(7406) ? (inSampleSize % inSampleSize) : (ListenerUtil.mutListener.listen(7405) ? (inSampleSize / inSampleSize) : (ListenerUtil.mutListener.listen(7404) ? (inSampleSize - inSampleSize) : (ListenerUtil.mutListener.listen(7403) ? (inSampleSize + inSampleSize) : (inSampleSize * inSampleSize))))))))))) >= totalReqPixelsCap) : (ListenerUtil.mutListener.listen(7414) ? ((ListenerUtil.mutListener.listen(7410) ? (totalPixels % ((ListenerUtil.mutListener.listen(7406) ? (inSampleSize % inSampleSize) : (ListenerUtil.mutListener.listen(7405) ? (inSampleSize / inSampleSize) : (ListenerUtil.mutListener.listen(7404) ? (inSampleSize - inSampleSize) : (ListenerUtil.mutListener.listen(7403) ? (inSampleSize + inSampleSize) : (inSampleSize * inSampleSize))))))) : (ListenerUtil.mutListener.listen(7409) ? (totalPixels * ((ListenerUtil.mutListener.listen(7406) ? (inSampleSize % inSampleSize) : (ListenerUtil.mutListener.listen(7405) ? (inSampleSize / inSampleSize) : (ListenerUtil.mutListener.listen(7404) ? (inSampleSize - inSampleSize) : (ListenerUtil.mutListener.listen(7403) ? (inSampleSize + inSampleSize) : (inSampleSize * inSampleSize))))))) : (ListenerUtil.mutListener.listen(7408) ? (totalPixels - ((ListenerUtil.mutListener.listen(7406) ? (inSampleSize % inSampleSize) : (ListenerUtil.mutListener.listen(7405) ? (inSampleSize / inSampleSize) : (ListenerUtil.mutListener.listen(7404) ? (inSampleSize - inSampleSize) : (ListenerUtil.mutListener.listen(7403) ? (inSampleSize + inSampleSize) : (inSampleSize * inSampleSize))))))) : (ListenerUtil.mutListener.listen(7407) ? (totalPixels + ((ListenerUtil.mutListener.listen(7406) ? (inSampleSize % inSampleSize) : (ListenerUtil.mutListener.listen(7405) ? (inSampleSize / inSampleSize) : (ListenerUtil.mutListener.listen(7404) ? (inSampleSize - inSampleSize) : (ListenerUtil.mutListener.listen(7403) ? (inSampleSize + inSampleSize) : (inSampleSize * inSampleSize))))))) : (totalPixels / ((ListenerUtil.mutListener.listen(7406) ? (inSampleSize % inSampleSize) : (ListenerUtil.mutListener.listen(7405) ? (inSampleSize / inSampleSize) : (ListenerUtil.mutListener.listen(7404) ? (inSampleSize - inSampleSize) : (ListenerUtil.mutListener.listen(7403) ? (inSampleSize + inSampleSize) : (inSampleSize * inSampleSize))))))))))) <= totalReqPixelsCap) : (ListenerUtil.mutListener.listen(7413) ? ((ListenerUtil.mutListener.listen(7410) ? (totalPixels % ((ListenerUtil.mutListener.listen(7406) ? (inSampleSize % inSampleSize) : (ListenerUtil.mutListener.listen(7405) ? (inSampleSize / inSampleSize) : (ListenerUtil.mutListener.listen(7404) ? (inSampleSize - inSampleSize) : (ListenerUtil.mutListener.listen(7403) ? (inSampleSize + inSampleSize) : (inSampleSize * inSampleSize))))))) : (ListenerUtil.mutListener.listen(7409) ? (totalPixels * ((ListenerUtil.mutListener.listen(7406) ? (inSampleSize % inSampleSize) : (ListenerUtil.mutListener.listen(7405) ? (inSampleSize / inSampleSize) : (ListenerUtil.mutListener.listen(7404) ? (inSampleSize - inSampleSize) : (ListenerUtil.mutListener.listen(7403) ? (inSampleSize + inSampleSize) : (inSampleSize * inSampleSize))))))) : (ListenerUtil.mutListener.listen(7408) ? (totalPixels - ((ListenerUtil.mutListener.listen(7406) ? (inSampleSize % inSampleSize) : (ListenerUtil.mutListener.listen(7405) ? (inSampleSize / inSampleSize) : (ListenerUtil.mutListener.listen(7404) ? (inSampleSize - inSampleSize) : (ListenerUtil.mutListener.listen(7403) ? (inSampleSize + inSampleSize) : (inSampleSize * inSampleSize))))))) : (ListenerUtil.mutListener.listen(7407) ? (totalPixels + ((ListenerUtil.mutListener.listen(7406) ? (inSampleSize % inSampleSize) : (ListenerUtil.mutListener.listen(7405) ? (inSampleSize / inSampleSize) : (ListenerUtil.mutListener.listen(7404) ? (inSampleSize - inSampleSize) : (ListenerUtil.mutListener.listen(7403) ? (inSampleSize + inSampleSize) : (inSampleSize * inSampleSize))))))) : (totalPixels / ((ListenerUtil.mutListener.listen(7406) ? (inSampleSize % inSampleSize) : (ListenerUtil.mutListener.listen(7405) ? (inSampleSize / inSampleSize) : (ListenerUtil.mutListener.listen(7404) ? (inSampleSize - inSampleSize) : (ListenerUtil.mutListener.listen(7403) ? (inSampleSize + inSampleSize) : (inSampleSize * inSampleSize))))))))))) < totalReqPixelsCap) : (ListenerUtil.mutListener.listen(7412) ? ((ListenerUtil.mutListener.listen(7410) ? (totalPixels % ((ListenerUtil.mutListener.listen(7406) ? (inSampleSize % inSampleSize) : (ListenerUtil.mutListener.listen(7405) ? (inSampleSize / inSampleSize) : (ListenerUtil.mutListener.listen(7404) ? (inSampleSize - inSampleSize) : (ListenerUtil.mutListener.listen(7403) ? (inSampleSize + inSampleSize) : (inSampleSize * inSampleSize))))))) : (ListenerUtil.mutListener.listen(7409) ? (totalPixels * ((ListenerUtil.mutListener.listen(7406) ? (inSampleSize % inSampleSize) : (ListenerUtil.mutListener.listen(7405) ? (inSampleSize / inSampleSize) : (ListenerUtil.mutListener.listen(7404) ? (inSampleSize - inSampleSize) : (ListenerUtil.mutListener.listen(7403) ? (inSampleSize + inSampleSize) : (inSampleSize * inSampleSize))))))) : (ListenerUtil.mutListener.listen(7408) ? (totalPixels - ((ListenerUtil.mutListener.listen(7406) ? (inSampleSize % inSampleSize) : (ListenerUtil.mutListener.listen(7405) ? (inSampleSize / inSampleSize) : (ListenerUtil.mutListener.listen(7404) ? (inSampleSize - inSampleSize) : (ListenerUtil.mutListener.listen(7403) ? (inSampleSize + inSampleSize) : (inSampleSize * inSampleSize))))))) : (ListenerUtil.mutListener.listen(7407) ? (totalPixels + ((ListenerUtil.mutListener.listen(7406) ? (inSampleSize % inSampleSize) : (ListenerUtil.mutListener.listen(7405) ? (inSampleSize / inSampleSize) : (ListenerUtil.mutListener.listen(7404) ? (inSampleSize - inSampleSize) : (ListenerUtil.mutListener.listen(7403) ? (inSampleSize + inSampleSize) : (inSampleSize * inSampleSize))))))) : (totalPixels / ((ListenerUtil.mutListener.listen(7406) ? (inSampleSize % inSampleSize) : (ListenerUtil.mutListener.listen(7405) ? (inSampleSize / inSampleSize) : (ListenerUtil.mutListener.listen(7404) ? (inSampleSize - inSampleSize) : (ListenerUtil.mutListener.listen(7403) ? (inSampleSize + inSampleSize) : (inSampleSize * inSampleSize))))))))))) != totalReqPixelsCap) : (ListenerUtil.mutListener.listen(7411) ? ((ListenerUtil.mutListener.listen(7410) ? (totalPixels % ((ListenerUtil.mutListener.listen(7406) ? (inSampleSize % inSampleSize) : (ListenerUtil.mutListener.listen(7405) ? (inSampleSize / inSampleSize) : (ListenerUtil.mutListener.listen(7404) ? (inSampleSize - inSampleSize) : (ListenerUtil.mutListener.listen(7403) ? (inSampleSize + inSampleSize) : (inSampleSize * inSampleSize))))))) : (ListenerUtil.mutListener.listen(7409) ? (totalPixels * ((ListenerUtil.mutListener.listen(7406) ? (inSampleSize % inSampleSize) : (ListenerUtil.mutListener.listen(7405) ? (inSampleSize / inSampleSize) : (ListenerUtil.mutListener.listen(7404) ? (inSampleSize - inSampleSize) : (ListenerUtil.mutListener.listen(7403) ? (inSampleSize + inSampleSize) : (inSampleSize * inSampleSize))))))) : (ListenerUtil.mutListener.listen(7408) ? (totalPixels - ((ListenerUtil.mutListener.listen(7406) ? (inSampleSize % inSampleSize) : (ListenerUtil.mutListener.listen(7405) ? (inSampleSize / inSampleSize) : (ListenerUtil.mutListener.listen(7404) ? (inSampleSize - inSampleSize) : (ListenerUtil.mutListener.listen(7403) ? (inSampleSize + inSampleSize) : (inSampleSize * inSampleSize))))))) : (ListenerUtil.mutListener.listen(7407) ? (totalPixels + ((ListenerUtil.mutListener.listen(7406) ? (inSampleSize % inSampleSize) : (ListenerUtil.mutListener.listen(7405) ? (inSampleSize / inSampleSize) : (ListenerUtil.mutListener.listen(7404) ? (inSampleSize - inSampleSize) : (ListenerUtil.mutListener.listen(7403) ? (inSampleSize + inSampleSize) : (inSampleSize * inSampleSize))))))) : (totalPixels / ((ListenerUtil.mutListener.listen(7406) ? (inSampleSize % inSampleSize) : (ListenerUtil.mutListener.listen(7405) ? (inSampleSize / inSampleSize) : (ListenerUtil.mutListener.listen(7404) ? (inSampleSize - inSampleSize) : (ListenerUtil.mutListener.listen(7403) ? (inSampleSize + inSampleSize) : (inSampleSize * inSampleSize))))))))))) == totalReqPixelsCap) : ((ListenerUtil.mutListener.listen(7410) ? (totalPixels % ((ListenerUtil.mutListener.listen(7406) ? (inSampleSize % inSampleSize) : (ListenerUtil.mutListener.listen(7405) ? (inSampleSize / inSampleSize) : (ListenerUtil.mutListener.listen(7404) ? (inSampleSize - inSampleSize) : (ListenerUtil.mutListener.listen(7403) ? (inSampleSize + inSampleSize) : (inSampleSize * inSampleSize))))))) : (ListenerUtil.mutListener.listen(7409) ? (totalPixels * ((ListenerUtil.mutListener.listen(7406) ? (inSampleSize % inSampleSize) : (ListenerUtil.mutListener.listen(7405) ? (inSampleSize / inSampleSize) : (ListenerUtil.mutListener.listen(7404) ? (inSampleSize - inSampleSize) : (ListenerUtil.mutListener.listen(7403) ? (inSampleSize + inSampleSize) : (inSampleSize * inSampleSize))))))) : (ListenerUtil.mutListener.listen(7408) ? (totalPixels - ((ListenerUtil.mutListener.listen(7406) ? (inSampleSize % inSampleSize) : (ListenerUtil.mutListener.listen(7405) ? (inSampleSize / inSampleSize) : (ListenerUtil.mutListener.listen(7404) ? (inSampleSize - inSampleSize) : (ListenerUtil.mutListener.listen(7403) ? (inSampleSize + inSampleSize) : (inSampleSize * inSampleSize))))))) : (ListenerUtil.mutListener.listen(7407) ? (totalPixels + ((ListenerUtil.mutListener.listen(7406) ? (inSampleSize % inSampleSize) : (ListenerUtil.mutListener.listen(7405) ? (inSampleSize / inSampleSize) : (ListenerUtil.mutListener.listen(7404) ? (inSampleSize - inSampleSize) : (ListenerUtil.mutListener.listen(7403) ? (inSampleSize + inSampleSize) : (inSampleSize * inSampleSize))))))) : (totalPixels / ((ListenerUtil.mutListener.listen(7406) ? (inSampleSize % inSampleSize) : (ListenerUtil.mutListener.listen(7405) ? (inSampleSize / inSampleSize) : (ListenerUtil.mutListener.listen(7404) ? (inSampleSize - inSampleSize) : (ListenerUtil.mutListener.listen(7403) ? (inSampleSize + inSampleSize) : (inSampleSize * inSampleSize))))))))))) > totalReqPixelsCap))))))) {
                            ListenerUtil.loopListener.listen("_loopCounter88", ++_loopCounter88);
                            if (!ListenerUtil.mutListener.listen(7402)) {
                                inSampleSize++;
                            }
                        }
                    }
                }
            }
        }
        return inSampleSize;
    }

    /**
     * Rotate an image if required.
     *
     * @param img       The image bitmap
     * @param imagePath Path to image
     * @return The resulted Bitmap after manipulation
     */
    private static Bitmap rotateImageIfRequired(Bitmap img, String imagePath) throws IOException {
        ExifInterface ei = new ExifInterface(imagePath);
        int orientation = ei.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
        switch(orientation) {
            case ExifInterface.ORIENTATION_ROTATE_90:
                return rotateImage(img, 90);
            case ExifInterface.ORIENTATION_ROTATE_180:
                return rotateImage(img, 180);
            case ExifInterface.ORIENTATION_ROTATE_270:
                return rotateImage(img, 270);
            default:
                return img;
        }
    }

    /**
     * Rotate the given bitmap
     *
     * @param img    image to rotate
     * @param degree number of degrees to rotate, from 0-360
     * @return the provided bitmap rotated by the given number of degrees
     */
    private static Bitmap rotateImage(Bitmap img, int degree) {
        Matrix matrix = new Matrix();
        if (!ListenerUtil.mutListener.listen(7418)) {
            matrix.postRotate(degree);
        }
        Bitmap rotatedImg = Bitmap.createBitmap(img, 0, 0, img.getWidth(), img.getHeight(), matrix, true);
        if (!ListenerUtil.mutListener.listen(7419)) {
            img.recycle();
        }
        return rotatedImg;
    }

    /**
     * Launches the fare payment app for the currently selected region if the payment app is
     * installed, otherwise directs the user to the Google Play store listing to download it.  If
     * a region has a fare payment app warning, it will show the warning before checking if the app
     * is installed, unless the user has opted out of the warning.
     * If the current region is null (i.e., if a custom API URL is entered), then no-op.
     * @param activity activity to launch the fare payment app or Google Play store from
     */
    public static void launchPayMyFareApp(@NonNull Activity activity) {
        ObaRegion region = Application.get().getCurrentRegion();
        if (!ListenerUtil.mutListener.listen(7420)) {
            if (region == null) {
                // If a custom API URL is set (i.e., no region), then no op
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(7426)) {
            if ((ListenerUtil.mutListener.listen(7421) ? (!TextUtils.isEmpty(region.getPaymentWarningTitle()) && !TextUtils.isEmpty(region.getPaymentWarningBody())) : (!TextUtils.isEmpty(region.getPaymentWarningTitle()) || !TextUtils.isEmpty(region.getPaymentWarningBody())))) {
                if (!ListenerUtil.mutListener.listen(7425)) {
                    // Region has a warning for using the payment app
                    if (!Application.getPrefs().getBoolean(activity.getString(R.string.preference_key_never_show_payment_warning_dialog), false)) {
                        if (!ListenerUtil.mutListener.listen(7424)) {
                            // User hasn't opted out of warning dialog yet - show the dialog
                            showPaymentWarningDialog(activity, region);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(7423)) {
                            // User opted out of warning - start the Intent
                            startPaymentIntent(activity, region);
                        }
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(7422)) {
                    // No payment warning for this region - start the Intent
                    startPaymentIntent(activity, region);
                }
            }
        }
    }

    /**
     * Launches the payment app for the provided region if it's already installed, and if not
     * directs the user to the listing in Google Play where it can be downloaded
     * @param activity Activity to use to launch the Intent
     * @param region region to launch a payment Intent for
     */
    private static void startPaymentIntent(@NonNull Activity activity, @NonNull ObaRegion region) {
        PackageManager manager = activity.getPackageManager();
        Intent intent = manager.getLaunchIntentForPackage(region.getPaymentAndroidAppId());
        if (!ListenerUtil.mutListener.listen(7434)) {
            if (intent != null) {
                if (!ListenerUtil.mutListener.listen(7431)) {
                    // Launch installed app
                    intent.addCategory(Intent.CATEGORY_LAUNCHER);
                }
                if (!ListenerUtil.mutListener.listen(7432)) {
                    activity.startActivity(intent);
                }
                if (!ListenerUtil.mutListener.listen(7433)) {
                    ObaAnalytics.reportUiEvent(FirebaseAnalytics.getInstance(activity), Application.get().getString(R.string.analytics_label_button_fare_payment), Application.get().getString(R.string.analytics_label_open_app));
                }
            } else {
                if (!ListenerUtil.mutListener.listen(7427)) {
                    // Go to Play Store listing to download app
                    intent = new Intent(Intent.ACTION_VIEW);
                }
                if (!ListenerUtil.mutListener.listen(7428)) {
                    intent.setData(Uri.parse(Application.get().getString(R.string.google_play_listing_prefix, region.getPaymentAndroidAppId())));
                }
                if (!ListenerUtil.mutListener.listen(7429)) {
                    activity.startActivity(intent);
                }
                if (!ListenerUtil.mutListener.listen(7430)) {
                    ObaAnalytics.reportUiEvent(FirebaseAnalytics.getInstance(activity), Application.get().getString(R.string.analytics_label_button_fare_payment), Application.get().getString(R.string.analytics_label_download_app));
                }
            }
        }
    }

    /**
     * Shows the payment warning to the user for the provided region if the user hasn't already
     * opted out of the warning, and then calls the method to create the correct payment Intent.
     * If the user has opted out of the warning, just call the method to create the payment Intent
     * @param activity Activity to use to launch the Intent
     * @param region region to launch a payment Intent for
     */
    private static void showPaymentWarningDialog(@NonNull Activity activity, @NonNull ObaRegion region) {
        View view = activity.getLayoutInflater().inflate(R.layout.payment_warning_dialog, null);
        CheckBox neverShowDialog = view.findViewById(R.id.payment_warning_never_ask_again);
        TextView warningBody = view.findViewById(R.id.payment_warning_body);
        if (!ListenerUtil.mutListener.listen(7435)) {
            neverShowDialog.setOnCheckedChangeListener((compoundButton, isChecked) -> {
                // Save the preference
                PreferenceUtils.saveBoolean(activity.getString(R.string.preference_key_never_show_payment_warning_dialog), isChecked);
            });
        }
        if (!ListenerUtil.mutListener.listen(7436)) {
            warningBody.setText(region.getPaymentWarningBody());
        }
        Drawable icon = activity.getResources().getDrawable(android.R.drawable.ic_dialog_alert);
        if (!ListenerUtil.mutListener.listen(7437)) {
            DrawableCompat.setTint(icon, activity.getResources().getColor(R.color.alert_icon_error));
        }
        androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(activity).setTitle(region.getPaymentWarningTitle()).setIcon(icon).setCancelable(false).setView(view).setPositiveButton(R.string.ok, (dialog, which) -> startPaymentIntent(activity, region));
        if (!ListenerUtil.mutListener.listen(7438)) {
            builder.create().show();
        }
    }

    /**
     * Launches the HOPR bikeshare app for Tampa if the app is installed, otherwise directs the user
     * to the Google Play store listing to download it.
     *
     * @param context context to launch the fare payment app or Google Play store from
     */
    public static void launchTampaHoprApp(@NonNull Context context) {
        PackageManager manager = context.getPackageManager();
        Intent intent = manager.getLaunchIntentForPackage(context.getString(R.string.hopr_android_app_id));
        if (!ListenerUtil.mutListener.listen(7446)) {
            if (intent != null) {
                if (!ListenerUtil.mutListener.listen(7443)) {
                    // Launch installed app
                    intent.addCategory(Intent.CATEGORY_LAUNCHER);
                }
                if (!ListenerUtil.mutListener.listen(7444)) {
                    context.startActivity(intent);
                }
                if (!ListenerUtil.mutListener.listen(7445)) {
                    ObaAnalytics.reportUiEvent(FirebaseAnalytics.getInstance(context), Application.get().getString(R.string.analytics_label_button_bike_share), Application.get().getString(R.string.analytics_label_open_app));
                }
            } else {
                if (!ListenerUtil.mutListener.listen(7439)) {
                    // Go to Play Store listing to download app
                    intent = new Intent(Intent.ACTION_VIEW);
                }
                if (!ListenerUtil.mutListener.listen(7440)) {
                    intent.setData(Uri.parse(Application.get().getString(R.string.google_play_listing_prefix, context.getString(R.string.hopr_android_app_id))));
                }
                if (!ListenerUtil.mutListener.listen(7441)) {
                    context.startActivity(intent);
                }
                if (!ListenerUtil.mutListener.listen(7442)) {
                    ObaAnalytics.reportUiEvent(FirebaseAnalytics.getInstance(context), Application.get().getString(R.string.analytics_label_button_bike_share), Application.get().getString(R.string.analytics_label_download_app));
                }
            }
        }
    }

    /**
     * Sets the visibility and colors of the silhouettes in the provided occupancy.xml viewgroup
     *  @param v         occupancy.xml layout viewgroup containing the silhouettes
     * @param occupancy the occupancy value to use to set the silhouette visibility
     * @param occupancyState the state of the occupancy to use to set the silhouette color
     */
    public static void setOccupancyVisibilityAndColor(ViewGroup v, Occupancy occupancy, OccupancyState occupancyState) {
        ImageView silhouette1 = v.findViewById(R.id.silhouette1);
        if (!ListenerUtil.mutListener.listen(7447)) {
            silhouette1.setVisibility(View.INVISIBLE);
        }
        ImageView silhouette2 = v.findViewById(R.id.silhouette2);
        if (!ListenerUtil.mutListener.listen(7448)) {
            silhouette2.setVisibility(View.INVISIBLE);
        }
        ImageView silhouette3 = v.findViewById(R.id.silhouette3);
        if (!ListenerUtil.mutListener.listen(7449)) {
            silhouette3.setVisibility(View.INVISIBLE);
        }
        if (!ListenerUtil.mutListener.listen(7452)) {
            // Hide the entire view group if occupancy is null
            if (occupancy == null) {
                if (!ListenerUtil.mutListener.listen(7451)) {
                    v.setVisibility(View.GONE);
                }
                return;
            } else {
                if (!ListenerUtil.mutListener.listen(7450)) {
                    v.setVisibility(View.VISIBLE);
                }
            }
        }
        int silhouetteColor = Application.get().getResources().getColor(R.color.stop_info_occupancy);
        int backgroundColor = Application.get().getResources().getColor(R.color.stop_info_occupancy_background);
        if (!ListenerUtil.mutListener.listen(7457)) {
            if (occupancyState == OccupancyState.HISTORICAL) {
                // Set the alpha for historical occupancy to 60%
                float alpha = 0.6f;
                if (!ListenerUtil.mutListener.listen(7453)) {
                    v.setAlpha(alpha);
                }
                if (!ListenerUtil.mutListener.listen(7454)) {
                    silhouette1.setAlpha(alpha);
                }
                if (!ListenerUtil.mutListener.listen(7455)) {
                    silhouette2.setAlpha(alpha);
                }
                if (!ListenerUtil.mutListener.listen(7456)) {
                    silhouette3.setAlpha(alpha);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(7462)) {
            // Below switch continues into following cases to minimize number of setVisibility() calls
            switch(occupancy) {
                case NOT_ACCEPTING_PASSENGERS:
                // 3 icons
                case FULL:
                // 3 icons
                case CRUSHED_STANDING_ROOM_ONLY:
                    if (!ListenerUtil.mutListener.listen(7458)) {
                        // 3 icons
                        silhouette3.setVisibility(View.VISIBLE);
                    }
                case STANDING_ROOM_ONLY:
                    if (!ListenerUtil.mutListener.listen(7459)) {
                        // 2 icons
                        silhouette2.setVisibility(View.VISIBLE);
                    }
                case FEW_SEATS_AVAILABLE:
                    if (!ListenerUtil.mutListener.listen(7460)) {
                        // 2 icons
                        silhouette2.setVisibility(View.VISIBLE);
                    }
                case MANY_SEATS_AVAILABLE:
                    if (!ListenerUtil.mutListener.listen(7461)) {
                        // 1 icon
                        silhouette1.setVisibility(View.VISIBLE);
                    }
                case EMPTY:
            }
        }
        if (!ListenerUtil.mutListener.listen(7463)) {
            // Set silhouette colors
            ImageViewCompat.setImageTintList(silhouette1, ColorStateList.valueOf(silhouetteColor));
        }
        if (!ListenerUtil.mutListener.listen(7464)) {
            ImageViewCompat.setImageTintList(silhouette2, ColorStateList.valueOf(silhouetteColor));
        }
        if (!ListenerUtil.mutListener.listen(7465)) {
            ImageViewCompat.setImageTintList(silhouette3, ColorStateList.valueOf(silhouetteColor));
        }
        if (!ListenerUtil.mutListener.listen(7466)) {
            // Set background color
            v.setBackgroundResource(R.drawable.occupancy_background);
        }
        GradientDrawable d = (GradientDrawable) v.getBackground();
        if (!ListenerUtil.mutListener.listen(7467)) {
            d.setColor(backgroundColor);
        }
    }

    /**
     * Sets the content description of the occupancy view group based on the provided occupancy
     *
     * @param v              occupancy.xml layout viewgroup containing the silhouettes
     * @param occupancy      the occupancy value to use to set the content description
     * @param occupancyState the state of the occupancy
     */
    public static void setOccupancyContentDescription(ViewGroup v, Occupancy occupancy, OccupancyState occupancyState) {
        if (!ListenerUtil.mutListener.listen(7469)) {
            // Hide the entire view group if occupancy is null
            if (occupancy == null) {
                if (!ListenerUtil.mutListener.listen(7468)) {
                    v.setContentDescription("");
                }
                return;
            }
        }
        int stringId = R.string.historically_full;
        if (!ListenerUtil.mutListener.listen(7490)) {
            // Below switch continues into following cases to minimize lines of code
            switch(occupancy) {
                case NOT_ACCEPTING_PASSENGERS:
                // "Full"
                case FULL:
                // "Full"
                case CRUSHED_STANDING_ROOM_ONLY:
                    if (!ListenerUtil.mutListener.listen(7473)) {
                        // "Full"
                        if (occupancyState == OccupancyState.HISTORICAL) {
                            if (!ListenerUtil.mutListener.listen(7472)) {
                                stringId = R.string.historically_full;
                            }
                        } else if (occupancyState == OccupancyState.REALTIME) {
                            if (!ListenerUtil.mutListener.listen(7471)) {
                                stringId = R.string.realtime_full;
                            }
                        } else if (occupancyState == OccupancyState.PREDICTED) {
                            if (!ListenerUtil.mutListener.listen(7470)) {
                                stringId = R.string.predicted_full;
                            }
                        }
                    }
                    break;
                case STANDING_ROOM_ONLY:
                    if (!ListenerUtil.mutListener.listen(7477)) {
                        // "Standing room"
                        if (occupancyState == OccupancyState.HISTORICAL) {
                            if (!ListenerUtil.mutListener.listen(7476)) {
                                stringId = R.string.historically_standing_room;
                            }
                        } else if (occupancyState == OccupancyState.REALTIME) {
                            if (!ListenerUtil.mutListener.listen(7475)) {
                                stringId = R.string.realtime_standing_room;
                            }
                        } else if (occupancyState == OccupancyState.PREDICTED) {
                            if (!ListenerUtil.mutListener.listen(7474)) {
                                stringId = R.string.predicted_standing_room;
                            }
                        }
                    }
                    break;
                case FEW_SEATS_AVAILABLE:
                    if (!ListenerUtil.mutListener.listen(7481)) {
                        // "Few seats available"
                        if (occupancyState == OccupancyState.HISTORICAL) {
                            if (!ListenerUtil.mutListener.listen(7480)) {
                                stringId = R.string.historically_few_seats_available;
                            }
                        } else if (occupancyState == OccupancyState.REALTIME) {
                            if (!ListenerUtil.mutListener.listen(7479)) {
                                stringId = R.string.realtime_few_seats_available;
                            }
                        } else if (occupancyState == OccupancyState.PREDICTED) {
                            if (!ListenerUtil.mutListener.listen(7478)) {
                                stringId = R.string.predicted_few_seats_available;
                            }
                        }
                    }
                    break;
                case MANY_SEATS_AVAILABLE:
                    if (!ListenerUtil.mutListener.listen(7485)) {
                        // "Many seats available"
                        if (occupancyState == OccupancyState.HISTORICAL) {
                            if (!ListenerUtil.mutListener.listen(7484)) {
                                stringId = R.string.historically_many_seats_available;
                            }
                        } else if (occupancyState == OccupancyState.REALTIME) {
                            if (!ListenerUtil.mutListener.listen(7483)) {
                                stringId = R.string.realtime_many_seats_available;
                            }
                        } else if (occupancyState == OccupancyState.PREDICTED) {
                            if (!ListenerUtil.mutListener.listen(7482)) {
                                stringId = R.string.predicted_many_seats_available;
                            }
                        }
                    }
                    break;
                case EMPTY:
                    if (!ListenerUtil.mutListener.listen(7489)) {
                        // "Empty"
                        if (occupancyState == OccupancyState.HISTORICAL) {
                            if (!ListenerUtil.mutListener.listen(7488)) {
                                stringId = R.string.historically_empty;
                            }
                        } else if (occupancyState == OccupancyState.REALTIME) {
                            if (!ListenerUtil.mutListener.listen(7487)) {
                                stringId = R.string.realtime_empty;
                            }
                        } else if (occupancyState == OccupancyState.PREDICTED) {
                            if (!ListenerUtil.mutListener.listen(7486)) {
                                stringId = R.string.predicted_empty;
                            }
                        }
                    }
                    break;
            }
        }
        if (!ListenerUtil.mutListener.listen(7491)) {
            v.setContentDescription(Application.get().getString(stringId));
        }
    }

    /**
     * Asks the user to whitelist the application for energy restrictions (e.g., running in
     * the background). See https://developer.android.com/training/monitoring-device-state/doze-standby#support_for_other_use_cases
     *
     * @param activity
     */
    public static void openBatteryIgnoreIntent(Activity activity) {
        Intent intent = new Intent();
        if (!ListenerUtil.mutListener.listen(7492)) {
            intent.setAction(Settings.ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS);
        }
        if (!ListenerUtil.mutListener.listen(7493)) {
            intent.setData(Uri.parse("package:" + activity.getPackageName()));
        }
        if (!ListenerUtil.mutListener.listen(7494)) {
            activity.startActivity(intent);
        }
    }

    public static void setAppTheme(String themeValue) {
        if (!ListenerUtil.mutListener.listen(7496)) {
            if (themeValue.equalsIgnoreCase(Application.get().getString(R.string.preferences_app_theme_option_system_default))) {
                if (!ListenerUtil.mutListener.listen(7495)) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(7498)) {
            if (themeValue.equalsIgnoreCase(Application.get().getString(R.string.preferences_app_theme_option_dark))) {
                if (!ListenerUtil.mutListener.listen(7497)) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(7500)) {
            if (themeValue.equalsIgnoreCase(Application.get().getString(R.string.preferences_app_theme_option_light))) {
                if (!ListenerUtil.mutListener.listen(7499)) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                }
            }
        }
    }
}
