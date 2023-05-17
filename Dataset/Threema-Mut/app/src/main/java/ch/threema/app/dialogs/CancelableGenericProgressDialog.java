/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
 * Copyright (c) 2014-2021 Threema GmbH
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
package ch.threema.app.dialogs;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import androidx.annotation.NonNull;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialog;
import ch.threema.app.R;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class CancelableGenericProgressDialog extends ThreemaDialogFragment {

    private AlertDialog alertDialog;

    private Activity activity;

    private CancelableGenericProgressDialog.ProgressDialogClickListener callback;

    public static CancelableGenericProgressDialog newInstance(@StringRes int title, @StringRes int message, @StringRes int button) {
        CancelableGenericProgressDialog dialog = new CancelableGenericProgressDialog();
        Bundle args = new Bundle();
        if (!ListenerUtil.mutListener.listen(13245)) {
            args.putInt("title", title);
        }
        if (!ListenerUtil.mutListener.listen(13246)) {
            args.putInt("message", message);
        }
        if (!ListenerUtil.mutListener.listen(13247)) {
            args.putInt("button", button);
        }
        if (!ListenerUtil.mutListener.listen(13248)) {
            dialog.setArguments(args);
        }
        return dialog;
    }

    public interface ProgressDialogClickListener {

        void onProgressbarCanceled(String tag);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(13249)) {
            super.onCreate(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(13251)) {
            if (callback == null) {
                try {
                    if (!ListenerUtil.mutListener.listen(13250)) {
                        callback = (CancelableGenericProgressDialog.ProgressDialogClickListener) getTargetFragment();
                    }
                } catch (ClassCastException e) {
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(13254)) {
            // called from an activity rather than a fragment
            if (callback == null) {
                if (!ListenerUtil.mutListener.listen(13253)) {
                    if ((activity instanceof CancelableGenericProgressDialog.ProgressDialogClickListener)) {
                        if (!ListenerUtil.mutListener.listen(13252)) {
                            callback = (CancelableGenericProgressDialog.ProgressDialogClickListener) activity;
                        }
                    }
                }
            }
        }
    }

    @Override
    public void onAttach(@NonNull Activity activity) {
        if (!ListenerUtil.mutListener.listen(13255)) {
            super.onAttach(activity);
        }
        if (!ListenerUtil.mutListener.listen(13256)) {
            this.activity = activity;
        }
    }

    @NonNull
    @Override
    public AppCompatDialog onCreateDialog(Bundle savedInstanceState) {
        int title = getArguments().getInt("title");
        int message = getArguments().getInt("message");
        int button = getArguments().getInt("button");
        final String tag = this.getTag();
        final View dialogView = activity.getLayoutInflater().inflate(R.layout.dialog_progress_generic, null);
        TextView textView = dialogView.findViewById(R.id.text);
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(getActivity(), getTheme()).setCancelable(false);
        if (!ListenerUtil.mutListener.listen(13257)) {
            builder.setView(dialogView);
        }
        if (!ListenerUtil.mutListener.listen(13264)) {
            if ((ListenerUtil.mutListener.listen(13262) ? (title >= -1) : (ListenerUtil.mutListener.listen(13261) ? (title <= -1) : (ListenerUtil.mutListener.listen(13260) ? (title > -1) : (ListenerUtil.mutListener.listen(13259) ? (title < -1) : (ListenerUtil.mutListener.listen(13258) ? (title == -1) : (title != -1))))))) {
                if (!ListenerUtil.mutListener.listen(13263)) {
                    builder.setTitle(title);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(13271)) {
            if ((ListenerUtil.mutListener.listen(13269) ? (message >= 0) : (ListenerUtil.mutListener.listen(13268) ? (message <= 0) : (ListenerUtil.mutListener.listen(13267) ? (message > 0) : (ListenerUtil.mutListener.listen(13266) ? (message < 0) : (ListenerUtil.mutListener.listen(13265) ? (message == 0) : (message != 0))))))) {
                if (!ListenerUtil.mutListener.listen(13270)) {
                    textView.setText(message);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(13273)) {
            builder.setPositiveButton(getString(button), new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialog, int whichButton) {
                    if (!ListenerUtil.mutListener.listen(13272)) {
                        callback.onProgressbarCanceled(tag);
                    }
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(13274)) {
            alertDialog = builder.create();
        }
        if (!ListenerUtil.mutListener.listen(13275)) {
            setCancelable(false);
        }
        return alertDialog;
    }
}
