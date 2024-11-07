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

import android.app.Dialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import com.google.android.material.snackbar.Snackbar;
import org.onebusaway.android.R;
import org.onebusaway.android.io.elements.ObaSituation;
import org.onebusaway.android.provider.ObaContract;
import org.onebusaway.android.util.PreferenceUtils;
import org.onebusaway.android.util.UIUtils;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Displays service alerts (i.e., situations) is a dialog
 */
public class SituationDialogFragment extends DialogFragment {

    public static final String TAG = "SituationFragment";

    public static final String ID = ".ID";

    public static final String TITLE = ".Title";

    public static final String DESCRIPTION = ".Description";

    public static final String URL = ".Url";

    interface Listener {

        /**
         * Called when this dialog is dismissed
         *
         * @param isAlertHidden true if the service alert was hidden by the user, false if it was
         *                      not
         */
        void onDismiss(boolean isAlertHidden);

        /**
         * Called when the user taps the "Undo" snackbar for hiding an alert
         */
        void onUndo();
    }

    private Listener mListener;

    /**
     * Helper method to show this dialog
     */
    public static SituationDialogFragment newInstance(ObaSituation situation) {
        Bundle args = new Bundle();
        if (!ListenerUtil.mutListener.listen(1578)) {
            args.putString(ID, situation.getId());
        }
        if (!ListenerUtil.mutListener.listen(1579)) {
            args.putString(TITLE, situation.getSummary());
        }
        if (!ListenerUtil.mutListener.listen(1580)) {
            // We don't use the stop name map here...we want the actual stop name.
            args.putString(DESCRIPTION, situation.getDescription());
        }
        if (!ListenerUtil.mutListener.listen(1582)) {
            if (!TextUtils.isEmpty(situation.getUrl())) {
                if (!ListenerUtil.mutListener.listen(1581)) {
                    args.putString(URL, situation.getUrl());
                }
            }
        }
        // Create the list fragment and add it as our sole content.
        SituationDialogFragment dialog = new SituationDialogFragment();
        if (!ListenerUtil.mutListener.listen(1583)) {
            dialog.setArguments(args);
        }
        return dialog;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Bundle args = getArguments();
        final String situationId = args.getString(ID);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity()).setView(R.layout.situation).setPositiveButton(R.string.hide, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                Dialog dialog = (Dialog) dialogInterface;
                if (!ListenerUtil.mutListener.listen(1587)) {
                    // Update the database to indicate that this alert has been hidden
                    ObaContract.ServiceAlerts.insertOrUpdate(situationId, new ContentValues(), false, true);
                }
                if (!ListenerUtil.mutListener.listen(1591)) {
                    // Show the UNDO snackbar
                    Snackbar.make(getActivity().findViewById(R.id.fragment_arrivals_list), R.string.alert_hidden_snackbar_text, Snackbar.LENGTH_SHORT).setAction(R.string.alert_hidden_snackbar_action, new View.OnClickListener() {

                        @Override
                        public void onClick(View v) {
                            if (!ListenerUtil.mutListener.listen(1588)) {
                                ObaContract.ServiceAlerts.insertOrUpdate(situationId, new ContentValues(), false, false);
                            }
                            if (!ListenerUtil.mutListener.listen(1590)) {
                                if (mListener != null) {
                                    if (!ListenerUtil.mutListener.listen(1589)) {
                                        mListener.onUndo();
                                    }
                                }
                            }
                        }
                    }).show();
                }
                if (!ListenerUtil.mutListener.listen(1592)) {
                    dialog.dismiss();
                }
                if (!ListenerUtil.mutListener.listen(1594)) {
                    if (mListener != null) {
                        if (!ListenerUtil.mutListener.listen(1593)) {
                            mListener.onDismiss(true);
                        }
                    }
                }
            }
        }).setNeutralButton(R.string.hide_all, (dialog, which) -> {
            // Hide existing alerts in the database
            ObaContract.ServiceAlerts.hideAllAlerts();
            // Also set the user preference to hide new alerts
            PreferenceUtils.saveBoolean(getString(R.string.preference_key_hide_alerts), true);
            // Show the snackbar
            Snackbar.make(getActivity().findViewById(R.id.fragment_arrivals_list), R.string.all_alert_hidden_snackbar_text, Snackbar.LENGTH_SHORT).show();
            dialog.dismiss();
            if (mListener != null) {
                mListener.onDismiss(true);
            }
        }).setNegativeButton(R.string.close, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (!ListenerUtil.mutListener.listen(1584)) {
                    dialog.dismiss();
                }
                if (!ListenerUtil.mutListener.listen(1586)) {
                    if (mListener != null) {
                        if (!ListenerUtil.mutListener.listen(1585)) {
                            mListener.onDismiss(false);
                        }
                    }
                }
            }
        });
        final androidx.appcompat.app.AlertDialog dialog = builder.create();
        if (!ListenerUtil.mutListener.listen(1595)) {
            dialog.show();
        }
        // Set the title, description, and URL (if provided)
        TextView title = (TextView) dialog.findViewById(R.id.alert_title);
        if (!ListenerUtil.mutListener.listen(1596)) {
            title.setText(args.getString(TITLE));
        }
        TextView desc = (TextView) dialog.findViewById(R.id.alert_description);
        if (!ListenerUtil.mutListener.listen(1597)) {
            desc.setText(args.getString(DESCRIPTION));
        }
        TextView urlView = (TextView) dialog.findViewById(R.id.alert_url);
        if (!ListenerUtil.mutListener.listen(1598)) {
            // Remove any previous clickable spans just to be safe
            UIUtils.removeAllClickableSpans(urlView);
        }
        final String url = args.getString(URL);
        if (!ListenerUtil.mutListener.listen(1603)) {
            if (!TextUtils.isEmpty(url)) {
                if (!ListenerUtil.mutListener.listen(1600)) {
                    urlView.setVisibility(View.VISIBLE);
                }
                ClickableSpan urlClick = new ClickableSpan() {

                    public void onClick(View v) {
                        if (!ListenerUtil.mutListener.listen(1601)) {
                            getActivity().startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
                        }
                    }
                };
                if (!ListenerUtil.mutListener.listen(1602)) {
                    UIUtils.setClickableSpan(urlView, urlClick);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(1599)) {
                    urlView.setVisibility(View.GONE);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(1604)) {
            // Update the database to indicate that this alert has been read
            ObaContract.ServiceAlerts.insertOrUpdate(args.getString(ID), new ContentValues(), true, null);
        }
        return dialog;
    }

    public Listener getListener() {
        return mListener;
    }

    public void setListener(Listener listener) {
        if (!ListenerUtil.mutListener.listen(1605)) {
            mListener = listener;
        }
    }
}
