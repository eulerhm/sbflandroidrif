/*
 * Copyright (C) 2015-2017 University of South Florida (sjbarbeau@gmail.com)
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
package org.onebusaway.android.ui;

import org.onebusaway.android.R;
import org.onebusaway.android.provider.ObaContract;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import androidx.fragment.app.DialogFragment;
import androidx.loader.app.LoaderManager;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Asks the user if they was to save a route/headsign favorite for all stops, or just this stop
 */
public class RouteFavoriteDialogFragment extends DialogFragment {

    public static final String TAG = "RouteFavDialogFragment";

    private static final String KEY_ROUTE_ID = "route_id";

    private static final String KEY_ROUTE_SHORT_NAME = "route_short_name";

    private static final String KEY_ROUTE_LONG_NAME = "route_long_name";

    private static final String KEY_HEADSIGN = "headsign";

    private static final String KEY_STOP_ID = "stop_id";

    private static final String KEY_FAVORITE = "favorite";

    private static final int ROUTE_INFO_LOADER = 0;

    // Selections need to match strings.xml "route_favorite_options"
    private static final int SELECTION_THIS_STOP = 0;

    private static final int SELECTION_ALL_STOPS = 1;

    private Callback mCallback;

    private int mSelectedItem;

    /**
     * Interface used to receive callbacks from the dialog after the user picks an option
     * and the database is updated with their choice
     */
    public interface Callback {

        /**
         * Called after the user picks an option and the database is updated with their choice
         *
         * @param savedFavorite true if the user saved a new route/headsign/stop favorite, false
         *                      if they did not
         */
        void onSelectionComplete(boolean savedFavorite);
    }

    /**
     * Builder used to create a new RouteFavoriteDialogFragment
     */
    public static class Builder {

        String mRouteId;

        String mRouteShortName;

        String mRouteLongName;

        String mHeadsign;

        String mStopId;

        String mRouteUrl;

        boolean mFavorite;

        public Builder(String routeId, String headsign) {
            if (!ListenerUtil.mutListener.listen(4964)) {
                mRouteId = routeId;
            }
            if (!ListenerUtil.mutListener.listen(4965)) {
                mHeadsign = headsign;
            }
        }

        public Builder setRouteId(String routeId) {
            if (!ListenerUtil.mutListener.listen(4966)) {
                mRouteId = routeId;
            }
            return this;
        }

        public Builder setRouteShortName(String routeShortName) {
            if (!ListenerUtil.mutListener.listen(4967)) {
                mRouteShortName = routeShortName;
            }
            return this;
        }

        public Builder setRouteLongName(String routeLongName) {
            if (!ListenerUtil.mutListener.listen(4968)) {
                mRouteLongName = routeLongName;
            }
            return this;
        }

        public Builder setRouteUrl(String routeUrl) {
            if (!ListenerUtil.mutListener.listen(4969)) {
                mRouteUrl = routeUrl;
            }
            return this;
        }

        public Builder setHeadsign(String headsign) {
            if (!ListenerUtil.mutListener.listen(4970)) {
                mHeadsign = headsign;
            }
            return this;
        }

        public Builder setStopId(String stopId) {
            if (!ListenerUtil.mutListener.listen(4971)) {
                mStopId = stopId;
            }
            return this;
        }

        public Builder setFavorite(boolean favorite) {
            if (!ListenerUtil.mutListener.listen(4972)) {
                mFavorite = favorite;
            }
            return this;
        }

        public RouteFavoriteDialogFragment build() {
            RouteFavoriteDialogFragment f = new RouteFavoriteDialogFragment();
            // Provide arguments
            Bundle args = new Bundle();
            if (!ListenerUtil.mutListener.listen(4973)) {
                args.putString(KEY_ROUTE_ID, mRouteId);
            }
            if (!ListenerUtil.mutListener.listen(4974)) {
                args.putString(KEY_ROUTE_SHORT_NAME, mRouteShortName);
            }
            if (!ListenerUtil.mutListener.listen(4975)) {
                args.putString(KEY_ROUTE_LONG_NAME, mRouteLongName);
            }
            if (!ListenerUtil.mutListener.listen(4976)) {
                args.putString(KEY_HEADSIGN, mHeadsign);
            }
            if (!ListenerUtil.mutListener.listen(4977)) {
                args.putString(KEY_STOP_ID, mStopId);
            }
            if (!ListenerUtil.mutListener.listen(4978)) {
                args.putBoolean(KEY_FAVORITE, mFavorite);
            }
            if (!ListenerUtil.mutListener.listen(4979)) {
                f.setArguments(args);
            }
            if (!ListenerUtil.mutListener.listen(4980)) {
                f.setCancelable(false);
            }
            return f;
        }
    }

    /**
     * Sets the receiver of the callback after the user has picked their choice and the database
     * has been updated with the selection
     *
     * @param callback the receiver of the callback after the user has picked their choice and the
     *                 database
     *                 has been updated with the selection
     */
    public void setCallback(Callback callback) {
        if (!ListenerUtil.mutListener.listen(4981)) {
            mCallback = callback;
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Initialize values from Bundle
        final String routeId = getArguments().getString(KEY_ROUTE_ID);
        final String routeShortName = getArguments().getString(KEY_ROUTE_SHORT_NAME);
        final String routeLongName = getArguments().getString(KEY_ROUTE_LONG_NAME);
        final String headsign = getArguments().getString(KEY_HEADSIGN);
        final String stopId = getArguments().getString(KEY_STOP_ID);
        final Boolean favorite = getArguments().getBoolean(KEY_FAVORITE);
        final Uri routeUri = Uri.withAppendedPath(ObaContract.Routes.CONTENT_URI, routeId);
        final ContentValues values = new ContentValues();
        if (!ListenerUtil.mutListener.listen(4982)) {
            values.put(ObaContract.Routes.SHORTNAME, routeShortName);
        }
        if (!ListenerUtil.mutListener.listen(4983)) {
            values.put(ObaContract.Routes.LONGNAME, routeLongName);
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        if (!ListenerUtil.mutListener.listen(4984)) {
            // Default to the first element in the list, which is "This stop"
            mSelectedItem = SELECTION_THIS_STOP;
        }
        // Show the route name in title
        String routeTitle = buildRouteTitle(routeShortName, headsign);
        String title;
        if (favorite) {
            title = getString(R.string.route_favorite_options_title_star, routeTitle);
        } else {
            title = getString(R.string.route_favorite_options_title_unstar, routeTitle);
        }
        if (!ListenerUtil.mutListener.listen(4996)) {
            builder.setTitle(title).setSingleChoiceItems(R.array.route_favorite_options, mSelectedItem, new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if (!ListenerUtil.mutListener.listen(4995)) {
                        mSelectedItem = which;
                    }
                }
            }).setPositiveButton(R.string.stop_info_save, new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int id) {
                    if (!ListenerUtil.mutListener.listen(4994)) {
                        if (mSelectedItem == SELECTION_THIS_STOP) {
                            if (!ListenerUtil.mutListener.listen(4990)) {
                                Log.d(TAG, "This stop");
                            }
                            if (!ListenerUtil.mutListener.listen(4991)) {
                                // Saved the favorite for just this stop
                                QueryUtils.setFavoriteRouteAndHeadsign(getActivity(), routeUri, headsign, stopId, values, favorite);
                            }
                            if (!ListenerUtil.mutListener.listen(4992)) {
                                LoaderManager.getInstance(getActivity()).restartLoader(ROUTE_INFO_LOADER, null, new QueryUtils.RouteLoaderCallback(getActivity(), routeId));
                            }
                            if (!ListenerUtil.mutListener.listen(4993)) {
                                mCallback.onSelectionComplete(true);
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(4986)) {
                                Log.d(TAG, "All stops");
                            }
                            if (!ListenerUtil.mutListener.listen(4987)) {
                                // Saved the favorite for all stops by passing null as stopId
                                QueryUtils.setFavoriteRouteAndHeadsign(getActivity(), routeUri, headsign, null, values, favorite);
                            }
                            if (!ListenerUtil.mutListener.listen(4988)) {
                                // the long name can be displayed later
                                LoaderManager.getInstance(getActivity()).restartLoader(ROUTE_INFO_LOADER, null, new QueryUtils.RouteLoaderCallback(getActivity(), routeId));
                            }
                            if (!ListenerUtil.mutListener.listen(4989)) {
                                mCallback.onSelectionComplete(true);
                            }
                        }
                    }
                }
            }).setNegativeButton(R.string.stop_info_cancel, new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int id) {
                    if (!ListenerUtil.mutListener.listen(4985)) {
                        // Callback with false value, because nothing changed
                        mCallback.onSelectionComplete(false);
                    }
                }
            });
        }
        return builder.create();
    }

    /**
     * Returns the route shortname (possibly truncated) concatenated with headsign (possibly
     * truncated) plus an ellipse
     *
     * @return the route shortname (possibly truncated) concatenated with headsign (possibly
     * truncated) plus an ellipse
     */
    private String buildRouteTitle(String routeShortName, String headsign) {
        StringBuilder routeTitle = new StringBuilder();
        if (!ListenerUtil.mutListener.listen(5008)) {
            if ((ListenerUtil.mutListener.listen(4997) ? (routeShortName != null || !routeShortName.isEmpty()) : (routeShortName != null && !routeShortName.isEmpty()))) {
                if (!ListenerUtil.mutListener.listen(5006)) {
                    if ((ListenerUtil.mutListener.listen(5002) ? (routeShortName.length() >= 3) : (ListenerUtil.mutListener.listen(5001) ? (routeShortName.length() <= 3) : (ListenerUtil.mutListener.listen(5000) ? (routeShortName.length() < 3) : (ListenerUtil.mutListener.listen(4999) ? (routeShortName.length() != 3) : (ListenerUtil.mutListener.listen(4998) ? (routeShortName.length() == 3) : (routeShortName.length() > 3))))))) {
                        if (!ListenerUtil.mutListener.listen(5004)) {
                            routeTitle.append(routeShortName.substring(0, 3));
                        }
                        if (!ListenerUtil.mutListener.listen(5005)) {
                            routeTitle.append("...");
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(5003)) {
                            routeTitle.append(routeShortName);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(5007)) {
                    routeTitle.append(" - ");
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(5019)) {
            if ((ListenerUtil.mutListener.listen(5009) ? (headsign != null || !headsign.isEmpty()) : (headsign != null && !headsign.isEmpty()))) {
                if (!ListenerUtil.mutListener.listen(5018)) {
                    if ((ListenerUtil.mutListener.listen(5014) ? (headsign.length() >= 8) : (ListenerUtil.mutListener.listen(5013) ? (headsign.length() <= 8) : (ListenerUtil.mutListener.listen(5012) ? (headsign.length() < 8) : (ListenerUtil.mutListener.listen(5011) ? (headsign.length() != 8) : (ListenerUtil.mutListener.listen(5010) ? (headsign.length() == 8) : (headsign.length() > 8))))))) {
                        if (!ListenerUtil.mutListener.listen(5016)) {
                            routeTitle.append(headsign.substring(0, 8));
                        }
                        if (!ListenerUtil.mutListener.listen(5017)) {
                            routeTitle.append("...");
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(5015)) {
                            routeTitle.append(headsign);
                        }
                    }
                }
            }
        }
        return routeTitle.toString();
    }
}
