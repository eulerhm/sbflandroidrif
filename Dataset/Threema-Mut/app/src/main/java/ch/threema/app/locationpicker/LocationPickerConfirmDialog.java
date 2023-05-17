/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
 * Copyright (c) 2019-2021 Threema GmbH
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License, version 3,
 * as published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program. If not, see <https://www.gnu.org/licenses/>.
 */
package ch.threema.app.locationpicker;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.geometry.LatLngBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.Locale;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialog;
import ch.threema.app.R;
import ch.threema.app.dialogs.ThreemaDialogFragment;
import ch.threema.app.utils.GeoLocationUtil;
import ch.threema.app.utils.TestUtil;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class LocationPickerConfirmDialog extends ThreemaDialogFragment {

    private LocationConfirmDialogClickListener callback;

    private Activity activity;

    private String tag = null;

    private static final Logger logger = LoggerFactory.getLogger(LocationPickerConfirmDialog.class);

    public static LocationPickerConfirmDialog newInstance(String title, String name, LatLng latLng, LatLngBounds latLngBounds) {
        LocationPickerConfirmDialog dialog = new LocationPickerConfirmDialog();
        Bundle args = new Bundle();
        if (!ListenerUtil.mutListener.listen(28876)) {
            args.putString("title", title);
        }
        if (!ListenerUtil.mutListener.listen(28877)) {
            args.putString("name", name);
        }
        if (!ListenerUtil.mutListener.listen(28878)) {
            args.putParcelable("latLng", latLng);
        }
        if (!ListenerUtil.mutListener.listen(28879)) {
            args.putParcelable("latLngBounds", latLngBounds);
        }
        if (!ListenerUtil.mutListener.listen(28880)) {
            dialog.setArguments(args);
        }
        return dialog;
    }

    public interface LocationConfirmDialogClickListener {

        void onOK(String tag, Object object);

        void onCancel(String tag);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(28881)) {
            super.onCreate(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(28886)) {
            if (callback == null) {
                try {
                    if (!ListenerUtil.mutListener.listen(28882)) {
                        callback = (LocationConfirmDialogClickListener) getTargetFragment();
                    }
                } catch (ClassCastException e) {
                }
                if (!ListenerUtil.mutListener.listen(28885)) {
                    // called from an activity rather than a fragment
                    if (callback == null) {
                        if (!ListenerUtil.mutListener.listen(28884)) {
                            if (activity instanceof LocationConfirmDialogClickListener) {
                                if (!ListenerUtil.mutListener.listen(28883)) {
                                    callback = (LocationConfirmDialogClickListener) activity;
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public void onAttach(@NonNull Activity activity) {
        if (!ListenerUtil.mutListener.listen(28887)) {
            super.onAttach(activity);
        }
        if (!ListenerUtil.mutListener.listen(28888)) {
            this.activity = activity;
        }
    }

    @Override
    public void onDetach() {
        if (!ListenerUtil.mutListener.listen(28889)) {
            super.onDetach();
        }
    }

    @NonNull
    @Override
    public AppCompatDialog onCreateDialog(Bundle savedInstanceState) {
        String title = getArguments().getString("title");
        String name = getArguments().getString("name");
        LatLng latLng = getArguments().getParcelable("latLng");
        if (!ListenerUtil.mutListener.listen(28890)) {
            tag = this.getTag();
        }
        final View dialogView = activity.getLayoutInflater().inflate(R.layout.dialog_location_picker_confirm, null);
        TextView addressText = dialogView.findViewById(R.id.place_address);
        TextView nameText = dialogView.findViewById(R.id.place_name);
        TextView coordinatesText = dialogView.findViewById(R.id.place_coordinates);
        if (!ListenerUtil.mutListener.listen(28891)) {
            addressText.setVisibility(View.GONE);
        }
        if (!ListenerUtil.mutListener.listen(28893)) {
            if (latLng != null) {
                if (!ListenerUtil.mutListener.listen(28892)) {
                    new LocationNameAsyncTask(getContext(), addressText, latLng.getLatitude(), latLng.getLongitude()).execute();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(28896)) {
            if (!TestUtil.empty(name)) {
                if (!ListenerUtil.mutListener.listen(28895)) {
                    nameText.setText(name);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(28894)) {
                    nameText.setVisibility(View.GONE);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(28899)) {
            if (latLng != null) {
                if (!ListenerUtil.mutListener.listen(28898)) {
                    coordinatesText.setText(String.format(Locale.US, "%f, %f", latLng.getLatitude(), latLng.getLongitude()));
                }
            } else {
                if (!ListenerUtil.mutListener.listen(28897)) {
                    coordinatesText.setVisibility(View.GONE);
                }
            }
        }
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(getContext(), getTheme());
        if (!ListenerUtil.mutListener.listen(28900)) {
            builder.setView(dialogView);
        }
        if (!ListenerUtil.mutListener.listen(28902)) {
            if (!TestUtil.empty(title)) {
                if (!ListenerUtil.mutListener.listen(28901)) {
                    builder.setTitle(title);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(28904)) {
            builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if (!ListenerUtil.mutListener.listen(28903)) {
                        callback.onOK(tag, object);
                    }
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(28906)) {
            builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialog, int which) {
                    if (!ListenerUtil.mutListener.listen(28905)) {
                        callback.onCancel(tag);
                    }
                }
            });
        }
        AlertDialog alertDialog = builder.create();
        if (!ListenerUtil.mutListener.listen(28907)) {
            setCancelable(false);
        }
        return alertDialog;
    }

    /**
     *   AsyncTask that loads the address retrieved from the Geocoder to the supplied TextView
     */
    private static class LocationNameAsyncTask extends AsyncTask<Void, Void, String> {

        WeakReference<Context> contextWeakReference;

        WeakReference<TextView> textViewWeakReference;

        double latitude, longitude;

        public LocationNameAsyncTask(@Nullable Context context, @NonNull TextView textView, double latitude, double longitude) {
            if (!ListenerUtil.mutListener.listen(28908)) {
                this.contextWeakReference = new WeakReference<>(context);
            }
            if (!ListenerUtil.mutListener.listen(28909)) {
                this.textViewWeakReference = new WeakReference<>(textView);
            }
            if (!ListenerUtil.mutListener.listen(28910)) {
                this.latitude = latitude;
            }
            if (!ListenerUtil.mutListener.listen(28911)) {
                this.longitude = longitude;
            }
        }

        @Override
        protected String doInBackground(Void... voids) {
            if (!ListenerUtil.mutListener.listen(28914)) {
                if ((ListenerUtil.mutListener.listen(28912) ? (contextWeakReference != null || contextWeakReference.get() != null) : (contextWeakReference != null && contextWeakReference.get() != null))) {
                    try {
                        return GeoLocationUtil.getAddressFromLocation(contextWeakReference.get(), latitude, longitude);
                    } catch (IOException e) {
                        if (!ListenerUtil.mutListener.listen(28913)) {
                            logger.error("Exception", e);
                        }
                    }
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            if (!ListenerUtil.mutListener.listen(28919)) {
                if (!TestUtil.empty(s)) {
                    if (!ListenerUtil.mutListener.listen(28918)) {
                        if ((ListenerUtil.mutListener.listen(28915) ? (textViewWeakReference != null || textViewWeakReference.get() != null) : (textViewWeakReference != null && textViewWeakReference.get() != null))) {
                            if (!ListenerUtil.mutListener.listen(28916)) {
                                textViewWeakReference.get().setText(s);
                            }
                            if (!ListenerUtil.mutListener.listen(28917)) {
                                textViewWeakReference.get().setVisibility(View.VISIBLE);
                            }
                        }
                    }
                }
            }
        }
    }
}
