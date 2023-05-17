/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
 * Copyright (c) 2015-2021 Threema GmbH
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
import android.widget.Button;
import android.widget.TextView;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import androidx.appcompat.app.AppCompatDialog;
import ch.threema.app.R;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class WizardDialog extends ThreemaDialogFragment {

    private static final String ARG_TITLE = "title";

    private static final String ARG_TITLE_STRING = "titleString";

    private static final String ARG_POSITIVE = "positive";

    private static final String ARG_NEGATIVE = "negative";

    private WizardDialogCallback callback;

    private Activity activity;

    public static WizardDialog newInstance(int title, int positive, int negative) {
        WizardDialog dialog = new WizardDialog();
        Bundle args = new Bundle();
        if (!ListenerUtil.mutListener.listen(14515)) {
            args.putInt(ARG_TITLE, title);
        }
        if (!ListenerUtil.mutListener.listen(14516)) {
            args.putInt(ARG_POSITIVE, positive);
        }
        if (!ListenerUtil.mutListener.listen(14517)) {
            args.putInt(ARG_NEGATIVE, negative);
        }
        if (!ListenerUtil.mutListener.listen(14518)) {
            dialog.setArguments(args);
        }
        return dialog;
    }

    public static WizardDialog newInstance(int title, int positive) {
        WizardDialog dialog = new WizardDialog();
        Bundle args = new Bundle();
        if (!ListenerUtil.mutListener.listen(14519)) {
            args.putInt(ARG_TITLE, title);
        }
        if (!ListenerUtil.mutListener.listen(14520)) {
            args.putInt(ARG_POSITIVE, positive);
        }
        if (!ListenerUtil.mutListener.listen(14521)) {
            dialog.setArguments(args);
        }
        return dialog;
    }

    public static WizardDialog newInstance(String title, int positive) {
        WizardDialog dialog = new WizardDialog();
        Bundle args = new Bundle();
        if (!ListenerUtil.mutListener.listen(14522)) {
            args.putString(ARG_TITLE_STRING, title);
        }
        if (!ListenerUtil.mutListener.listen(14523)) {
            args.putInt(ARG_POSITIVE, positive);
        }
        if (!ListenerUtil.mutListener.listen(14524)) {
            dialog.setArguments(args);
        }
        return dialog;
    }

    public interface WizardDialogCallback {

        void onYes(String tag, Object data);

        void onNo(String tag);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(14525)) {
            super.onCreate(savedInstanceState);
        }
        try {
            if (!ListenerUtil.mutListener.listen(14526)) {
                callback = (WizardDialogCallback) getTargetFragment();
            }
        } catch (ClassCastException e) {
        }
        if (!ListenerUtil.mutListener.listen(14529)) {
            // called from an activity rather than a fragment
            if (callback == null) {
                if (!ListenerUtil.mutListener.listen(14527)) {
                    if (!(activity instanceof WizardDialogCallback)) {
                        throw new ClassCastException("Calling fragment must implement WizardDialogCallback interface");
                    }
                }
                if (!ListenerUtil.mutListener.listen(14528)) {
                    callback = (WizardDialogCallback) activity;
                }
            }
        }
    }

    @Override
    public void onAttach(Activity activity) {
        if (!ListenerUtil.mutListener.listen(14530)) {
            super.onAttach(activity);
        }
        if (!ListenerUtil.mutListener.listen(14531)) {
            this.activity = activity;
        }
    }

    @Override
    public AppCompatDialog onCreateDialog(Bundle savedInstanceState) {
        int title = getArguments().getInt(ARG_TITLE, 0);
        String titleString = getArguments().getString(ARG_TITLE_STRING);
        int positive = getArguments().getInt(ARG_POSITIVE);
        int negative = getArguments().getInt(ARG_NEGATIVE, 0);
        final String tag = this.getTag();
        final View dialogView = activity.getLayoutInflater().inflate(R.layout.dialog_wizard, null);
        final TextView titleText = dialogView.findViewById(R.id.wizard_dialog_title);
        final Button positiveButton = dialogView.findViewById(R.id.wizard_yes);
        final Button negativeButton = dialogView.findViewById(R.id.wizard_no);
        if (!ListenerUtil.mutListener.listen(14539)) {
            if ((ListenerUtil.mutListener.listen(14536) ? (title >= 0) : (ListenerUtil.mutListener.listen(14535) ? (title <= 0) : (ListenerUtil.mutListener.listen(14534) ? (title > 0) : (ListenerUtil.mutListener.listen(14533) ? (title < 0) : (ListenerUtil.mutListener.listen(14532) ? (title == 0) : (title != 0))))))) {
                if (!ListenerUtil.mutListener.listen(14538)) {
                    titleText.setText(title);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(14537)) {
                    titleText.setText(titleString);
                }
            }
        }
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(getActivity(), R.style.Threema_Dialog_Wizard);
        if (!ListenerUtil.mutListener.listen(14540)) {
            builder.setView(dialogView);
        }
        if (!ListenerUtil.mutListener.listen(14541)) {
            positiveButton.setText(positive);
        }
        if (!ListenerUtil.mutListener.listen(14544)) {
            positiveButton.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    if (!ListenerUtil.mutListener.listen(14542)) {
                        dismiss();
                    }
                    if (!ListenerUtil.mutListener.listen(14543)) {
                        callback.onYes(tag, object);
                    }
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(14555)) {
            if ((ListenerUtil.mutListener.listen(14549) ? (negative >= 0) : (ListenerUtil.mutListener.listen(14548) ? (negative <= 0) : (ListenerUtil.mutListener.listen(14547) ? (negative > 0) : (ListenerUtil.mutListener.listen(14546) ? (negative < 0) : (ListenerUtil.mutListener.listen(14545) ? (negative == 0) : (negative != 0))))))) {
                if (!ListenerUtil.mutListener.listen(14551)) {
                    negativeButton.setText(negative);
                }
                if (!ListenerUtil.mutListener.listen(14554)) {
                    negativeButton.setOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View v) {
                            if (!ListenerUtil.mutListener.listen(14552)) {
                                dismiss();
                            }
                            if (!ListenerUtil.mutListener.listen(14553)) {
                                callback.onNo(tag);
                            }
                        }
                    });
                }
            } else {
                if (!ListenerUtil.mutListener.listen(14550)) {
                    negativeButton.setVisibility(View.GONE);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(14556)) {
            setCancelable(false);
        }
        return builder.create();
    }

    @Override
    public void onCancel(DialogInterface dialogInterface) {
        if (!ListenerUtil.mutListener.listen(14557)) {
            callback.onNo(this.getTag());
        }
    }
}
