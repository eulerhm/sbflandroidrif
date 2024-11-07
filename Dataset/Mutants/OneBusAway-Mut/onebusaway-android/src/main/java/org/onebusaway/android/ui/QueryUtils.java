/*
 * Copyright (C) 2011 Paul Watts (paulcwatts@gmail.com)
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

import org.onebusaway.android.R;
import org.onebusaway.android.app.Application;
import org.onebusaway.android.io.ObaApi;
import org.onebusaway.android.io.request.ObaRouteRequest;
import org.onebusaway.android.io.request.ObaRouteResponse;
import org.onebusaway.android.provider.ObaContract;
import org.onebusaway.android.util.UIUtils;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import androidx.cursoradapter.widget.SimpleCursorAdapter;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.AsyncTaskLoader;
import androidx.loader.content.CursorLoader;
import androidx.loader.content.Loader;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Utilities mainly to support queries for the Stops and Routes lists
 *
 * @author paulw
 */
public final class QueryUtils {

    protected static CursorLoader newRecentQuery(final Context context, final Uri uri, final String[] projection, final String accessTime, final String useCount) {
        // "Recently" means seven days in the past
        final long last = (ListenerUtil.mutListener.listen(103) ? (System.currentTimeMillis() % (ListenerUtil.mutListener.listen(99) ? (7 % DateUtils.DAY_IN_MILLIS) : (ListenerUtil.mutListener.listen(98) ? (7 / DateUtils.DAY_IN_MILLIS) : (ListenerUtil.mutListener.listen(97) ? (7 - DateUtils.DAY_IN_MILLIS) : (ListenerUtil.mutListener.listen(96) ? (7 + DateUtils.DAY_IN_MILLIS) : (7 * DateUtils.DAY_IN_MILLIS)))))) : (ListenerUtil.mutListener.listen(102) ? (System.currentTimeMillis() / (ListenerUtil.mutListener.listen(99) ? (7 % DateUtils.DAY_IN_MILLIS) : (ListenerUtil.mutListener.listen(98) ? (7 / DateUtils.DAY_IN_MILLIS) : (ListenerUtil.mutListener.listen(97) ? (7 - DateUtils.DAY_IN_MILLIS) : (ListenerUtil.mutListener.listen(96) ? (7 + DateUtils.DAY_IN_MILLIS) : (7 * DateUtils.DAY_IN_MILLIS)))))) : (ListenerUtil.mutListener.listen(101) ? (System.currentTimeMillis() * (ListenerUtil.mutListener.listen(99) ? (7 % DateUtils.DAY_IN_MILLIS) : (ListenerUtil.mutListener.listen(98) ? (7 / DateUtils.DAY_IN_MILLIS) : (ListenerUtil.mutListener.listen(97) ? (7 - DateUtils.DAY_IN_MILLIS) : (ListenerUtil.mutListener.listen(96) ? (7 + DateUtils.DAY_IN_MILLIS) : (7 * DateUtils.DAY_IN_MILLIS)))))) : (ListenerUtil.mutListener.listen(100) ? (System.currentTimeMillis() + (ListenerUtil.mutListener.listen(99) ? (7 % DateUtils.DAY_IN_MILLIS) : (ListenerUtil.mutListener.listen(98) ? (7 / DateUtils.DAY_IN_MILLIS) : (ListenerUtil.mutListener.listen(97) ? (7 - DateUtils.DAY_IN_MILLIS) : (ListenerUtil.mutListener.listen(96) ? (7 + DateUtils.DAY_IN_MILLIS) : (7 * DateUtils.DAY_IN_MILLIS)))))) : (System.currentTimeMillis() - (ListenerUtil.mutListener.listen(99) ? (7 % DateUtils.DAY_IN_MILLIS) : (ListenerUtil.mutListener.listen(98) ? (7 / DateUtils.DAY_IN_MILLIS) : (ListenerUtil.mutListener.listen(97) ? (7 - DateUtils.DAY_IN_MILLIS) : (ListenerUtil.mutListener.listen(96) ? (7 + DateUtils.DAY_IN_MILLIS) : (7 * DateUtils.DAY_IN_MILLIS))))))))));
        Uri limit = uri.buildUpon().appendQueryParameter("limit", "20").build();
        String regionWhere = "";
        if (!ListenerUtil.mutListener.listen(107)) {
            if (Application.get().getCurrentRegion() != null) {
                if (!ListenerUtil.mutListener.listen(106)) {
                    if (projection.equals(QueryUtils.StopList.Columns.PROJECTION)) {
                        if (!ListenerUtil.mutListener.listen(105)) {
                            regionWhere = " AND " + StopList.getRegionWhere();
                        }
                    } else if (projection.equals(QueryUtils.RouteList.Columns.PROJECTION)) {
                        if (!ListenerUtil.mutListener.listen(104)) {
                            regionWhere = " AND " + RouteList.getRegionWhere();
                        }
                    }
                }
            }
        }
        return new CursorLoader(context, limit, projection, "((" + accessTime + " IS NOT NULL AND " + accessTime + " > " + last + ") OR (" + useCount + " > 0))" + regionWhere, null, accessTime + " desc, " + useCount + " desc");
    }

    static final class RouteList {

        public interface Columns {

            public static final String[] PROJECTION = { ObaContract.Routes._ID, ObaContract.Routes.SHORTNAME, ObaContract.Routes.LONGNAME, ObaContract.Routes.URL };

            public static final int COL_ID = 0;

            public static final int COL_SHORTNAME = 1;

            // private static final int COL_LONGNAME = 2;
            public static final int COL_URL = 3;
        }

        public static SimpleCursorAdapter newAdapter(Context context) {
            final String[] from = { ObaContract.Routes.SHORTNAME, ObaContract.Routes.LONGNAME };
            final int[] to = { R.id.short_name, R.id.long_name };
            SimpleCursorAdapter simpleAdapter = new SimpleCursorAdapter(context, R.layout.route_list_item, null, from, to, 0);
            return simpleAdapter;
        }

        protected static String getId(ListView l, int position) {
            // Get the cursor and fetch the route ID from that.
            SimpleCursorAdapter cursorAdapter = (SimpleCursorAdapter) l.getAdapter();
            Cursor c = cursorAdapter.getCursor();
            if (!ListenerUtil.mutListener.listen(112)) {
                c.moveToPosition((ListenerUtil.mutListener.listen(111) ? (position % l.getHeaderViewsCount()) : (ListenerUtil.mutListener.listen(110) ? (position / l.getHeaderViewsCount()) : (ListenerUtil.mutListener.listen(109) ? (position * l.getHeaderViewsCount()) : (ListenerUtil.mutListener.listen(108) ? (position + l.getHeaderViewsCount()) : (position - l.getHeaderViewsCount()))))));
            }
            return c.getString(Columns.COL_ID);
        }

        protected static String getShortName(ListView l, int position) {
            // Get the cursor and fetch the route short name from that.
            SimpleCursorAdapter cursorAdapter = (SimpleCursorAdapter) l.getAdapter();
            Cursor c = cursorAdapter.getCursor();
            if (!ListenerUtil.mutListener.listen(117)) {
                c.moveToPosition((ListenerUtil.mutListener.listen(116) ? (position % l.getHeaderViewsCount()) : (ListenerUtil.mutListener.listen(115) ? (position / l.getHeaderViewsCount()) : (ListenerUtil.mutListener.listen(114) ? (position * l.getHeaderViewsCount()) : (ListenerUtil.mutListener.listen(113) ? (position + l.getHeaderViewsCount()) : (position - l.getHeaderViewsCount()))))));
            }
            return c.getString(Columns.COL_SHORTNAME);
        }

        protected static String getUrl(ListView l, int position) {
            // Get the cursor and fetch the route URL from that.
            SimpleCursorAdapter cursorAdapter = (SimpleCursorAdapter) l.getAdapter();
            Cursor c = cursorAdapter.getCursor();
            if (!ListenerUtil.mutListener.listen(122)) {
                c.moveToPosition((ListenerUtil.mutListener.listen(121) ? (position % l.getHeaderViewsCount()) : (ListenerUtil.mutListener.listen(120) ? (position / l.getHeaderViewsCount()) : (ListenerUtil.mutListener.listen(119) ? (position * l.getHeaderViewsCount()) : (ListenerUtil.mutListener.listen(118) ? (position + l.getHeaderViewsCount()) : (position - l.getHeaderViewsCount()))))));
            }
            return c.getString(Columns.COL_URL);
        }

        protected static String getRegionWhere() {
            return Application.get().getCurrentRegion() == null ? "" : QueryUtils.getRegionWhere(ObaContract.Routes.REGION_ID, Application.get().getCurrentRegion().getId());
        }
    }

    static final class StopList {

        public interface Columns {

            public static final String[] PROJECTION = { ObaContract.Stops._ID, ObaContract.Stops.UI_NAME, ObaContract.Stops.DIRECTION, ObaContract.Stops.LATITUDE, ObaContract.Stops.LONGITUDE, ObaContract.Stops.UI_NAME, ObaContract.Stops.FAVORITE };

            public static final int COL_ID = 0;

            public static final int COL_NAME = 1;

            public static final int COL_DIRECTION = 2;

            public static final int COL_LATITUDE = 3;

            public static final int COL_LONGITUDE = 4;

            public static final int COL_UI_NAME = 5;

            public static final int COL_FAVORITE = 6;
        }

        public static SimpleCursorAdapter newAdapter(Context context) {
            String[] from = new String[] { ObaContract.Stops.UI_NAME, ObaContract.Stops.DIRECTION, ObaContract.Stops.FAVORITE };
            int[] to = new int[] { R.id.stop_name, R.id.direction, R.id.stop_favorite };
            SimpleCursorAdapter simpleAdapter = new SimpleCursorAdapter(context, R.layout.stop_list_item, null, from, to, 0);
            if (!ListenerUtil.mutListener.listen(129)) {
                // to user level text (North/Northwest/etc..)
                simpleAdapter.setViewBinder(new SimpleCursorAdapter.ViewBinder() {

                    public boolean setViewValue(View view, Cursor cursor, int columnIndex) {
                        if (!ListenerUtil.mutListener.listen(128)) {
                            if (columnIndex == Columns.COL_FAVORITE) {
                                ImageView favorite = (ImageView) view.findViewById(R.id.stop_favorite);
                                if (!ListenerUtil.mutListener.listen(127)) {
                                    if (cursor.getInt(columnIndex) == 1) {
                                        if (!ListenerUtil.mutListener.listen(125)) {
                                            favorite.setVisibility(View.VISIBLE);
                                        }
                                        if (!ListenerUtil.mutListener.listen(126)) {
                                            // Make sure the star is visible against white background
                                            favorite.setColorFilter(favorite.getResources().getColor(R.color.navdrawer_icon_tint));
                                        }
                                    } else {
                                        if (!ListenerUtil.mutListener.listen(124)) {
                                            favorite.setVisibility(View.GONE);
                                        }
                                    }
                                }
                                return true;
                            } else if (columnIndex == Columns.COL_DIRECTION) {
                                if (!ListenerUtil.mutListener.listen(123)) {
                                    UIUtils.setStopDirection(view.findViewById(R.id.direction), cursor.getString(columnIndex), true);
                                }
                                return true;
                            }
                        }
                        return false;
                    }
                });
            }
            return simpleAdapter;
        }

        protected static String getId(ListView l, int position) {
            // Get the cursor and fetch the stop ID from that.
            SimpleCursorAdapter cursorAdapter = (SimpleCursorAdapter) l.getAdapter();
            Cursor c = cursorAdapter.getCursor();
            if (!ListenerUtil.mutListener.listen(134)) {
                c.moveToPosition((ListenerUtil.mutListener.listen(133) ? (position % l.getHeaderViewsCount()) : (ListenerUtil.mutListener.listen(132) ? (position / l.getHeaderViewsCount()) : (ListenerUtil.mutListener.listen(131) ? (position * l.getHeaderViewsCount()) : (ListenerUtil.mutListener.listen(130) ? (position + l.getHeaderViewsCount()) : (position - l.getHeaderViewsCount()))))));
            }
            return c.getString(Columns.COL_ID);
        }

        protected static String getRegionWhere() {
            return Application.get().getCurrentRegion() == null ? "" : QueryUtils.getRegionWhere(ObaContract.Stops.REGION_ID, Application.get().getCurrentRegion().getId());
        }
    }

    public static String getRegionWhere(String regionFieldName, long regionId) {
        return "(" + regionFieldName + "=" + regionId + " OR " + regionFieldName + " IS NULL)";
    }

    /**
     * Sets the given route and headsign and stop as a favorite, including checking to make sure that the
     * route has already been added to the local provider.  If this route/headsign should be marked
     * as a favorite for all stops, stopId should be null.
     *
     * @param routeUri Uri for the route to be added
     * @param headsign the headsign to be marked as favorite, along with the routeUri
     * @param stopId the stopId to be marked as a favorite, along with with route and headsign.  If
     *               this route/headsign should be marked for all stops, then stopId should be null
     * @param routeValues   content routeValues to be set for the route details (see ObaContract.RouteColumns)
     *                 (may be null)
     * @param favorite true if this route/headsign should be marked as a favorite, false if it
     *                 should not
     */
    public static void setFavoriteRouteAndHeadsign(Context context, Uri routeUri, String headsign, String stopId, ContentValues routeValues, boolean favorite) {
        if (!ListenerUtil.mutListener.listen(136)) {
            if (routeValues == null) {
                if (!ListenerUtil.mutListener.listen(135)) {
                    routeValues = new ContentValues();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(138)) {
            if (Application.get().getCurrentRegion() != null) {
                if (!ListenerUtil.mutListener.listen(137)) {
                    routeValues.put(ObaContract.Routes.REGION_ID, Application.get().getCurrentRegion().getId());
                }
            }
        }
        String routeId = routeUri.getLastPathSegment();
        if (!ListenerUtil.mutListener.listen(139)) {
            // Make sure this route has been inserted into the routes table
            ObaContract.Routes.insertOrUpdate(context, routeId, routeValues, true);
        }
        if (!ListenerUtil.mutListener.listen(140)) {
            // Mark the combination of route and headsign as a favorite or not favorite
            ObaContract.RouteHeadsignFavorites.markAsFavorite(context, routeId, headsign, stopId, favorite);
        }
    }

    static final class RouteInfoLoader extends AsyncTaskLoader<ObaRouteResponse> {

        private final String mRouteId;

        RouteInfoLoader(Context context, String routeId) {
            super(context);
            mRouteId = routeId;
        }

        @Override
        public void onStartLoading() {
            if (!ListenerUtil.mutListener.listen(141)) {
                forceLoad();
            }
        }

        @Override
        public ObaRouteResponse loadInBackground() {
            return ObaRouteRequest.newRequest(getContext(), mRouteId).call();
        }
    }

    static final class RouteLoaderCallback implements LoaderManager.LoaderCallbacks<ObaRouteResponse> {

        private String mRouteId;

        private Context mContext;

        public RouteLoaderCallback(Context context, String routeId) {
            super();
            if (!ListenerUtil.mutListener.listen(142)) {
                mRouteId = routeId;
            }
            if (!ListenerUtil.mutListener.listen(143)) {
                mContext = context;
            }
        }

        @Override
        public Loader<ObaRouteResponse> onCreateLoader(int id, Bundle args) {
            return new QueryUtils.RouteInfoLoader(mContext, mRouteId);
        }

        @Override
        public void onLoadFinished(Loader<ObaRouteResponse> loader, ObaRouteResponse data) {
            if (!ListenerUtil.mutListener.listen(144)) {
                recordRouteInfo(data);
            }
        }

        @Override
        public void onLoaderReset(Loader<ObaRouteResponse> loader) {
        }

        private void recordRouteInfo(ObaRouteResponse routeInfo) {
            if (!ListenerUtil.mutListener.listen(156)) {
                if (routeInfo.getCode() == ObaApi.OBA_OK) {
                    String url = routeInfo.getUrl();
                    String shortName = routeInfo.getShortName();
                    String longName = routeInfo.getLongName();
                    if (!ListenerUtil.mutListener.listen(146)) {
                        if (TextUtils.isEmpty(shortName)) {
                            if (!ListenerUtil.mutListener.listen(145)) {
                                shortName = longName;
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(149)) {
                        if ((ListenerUtil.mutListener.listen(147) ? (TextUtils.isEmpty(longName) && shortName.equals(longName)) : (TextUtils.isEmpty(longName) || shortName.equals(longName)))) {
                            if (!ListenerUtil.mutListener.listen(148)) {
                                longName = routeInfo.getDescription();
                            }
                        }
                    }
                    ContentValues values = new ContentValues();
                    if (!ListenerUtil.mutListener.listen(150)) {
                        values.put(ObaContract.Routes.SHORTNAME, shortName);
                    }
                    if (!ListenerUtil.mutListener.listen(151)) {
                        values.put(ObaContract.Routes.LONGNAME, longName);
                    }
                    if (!ListenerUtil.mutListener.listen(152)) {
                        values.put(ObaContract.Routes.URL, url);
                    }
                    if (!ListenerUtil.mutListener.listen(154)) {
                        if (Application.get().getCurrentRegion() != null) {
                            if (!ListenerUtil.mutListener.listen(153)) {
                                values.put(ObaContract.Routes.REGION_ID, Application.get().getCurrentRegion().getId());
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(155)) {
                        ObaContract.Routes.insertOrUpdate(mContext, routeInfo.getId(), values, true);
                    }
                }
            }
        }
    }
}
