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
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialog;
import ch.threema.app.R;
import ch.threema.app.utils.ConfigUtils;
import ch.threema.app.utils.DialogUtil;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class SMSVerificationDialog extends ThreemaDialogFragment {

    private static final String ARG_PHONE_NUMBER = "title";

    private SMSVerificationDialogCallback callback;

    private AlertDialog alertDialog;

    private Activity activity;

    private String tag;

    public static SMSVerificationDialog newInstance(String phoneNumber) {
        SMSVerificationDialog dialog = new SMSVerificationDialog();
        Bundle args = new Bundle();
        if (!ListenerUtil.mutListener.listen(14276)) {
            args.putString(ARG_PHONE_NUMBER, phoneNumber);
        }
        if (!ListenerUtil.mutListener.listen(14277)) {
            dialog.setArguments(args);
        }
        return dialog;
    }

    public interface SMSVerificationDialogCallback {

        void onYes(String tag, String code);

        void onNo(String tag);

        void onCallRequested(String tag);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(14278)) {
            super.onCreate(savedInstanceState);
        }
        try {
            if (!ListenerUtil.mutListener.listen(14279)) {
                callback = (SMSVerificationDialogCallback) getTargetFragment();
            }
        } catch (ClassCastException e) {
        }
        if (!ListenerUtil.mutListener.listen(14282)) {
            // called from an activity rather than a fragment
            if (callback == null) {
                if (!ListenerUtil.mutListener.listen(14280)) {
                    if (!(activity instanceof SMSVerificationDialogCallback)) {
                        throw new ClassCastException("Calling fragment must implement SMSVerificationDialogCallback interface");
                    }
                }
                if (!ListenerUtil.mutListener.listen(14281)) {
                    callback = (SMSVerificationDialogCallback) activity;
                }
            }
        }
    }

    @Override
    public void onAttach(Activity activity) {
        if (!ListenerUtil.mutListener.listen(14283)) {
            super.onAttach(activity);
        }
        if (!ListenerUtil.mutListener.listen(14284)) {
            this.activity = activity;
        }
    }

    @Override
    public AppCompatDialog onCreateDialog(Bundle savedInstanceState) {
        String phone = getArguments().getString(ARG_PHONE_NUMBER);
        String title = String.format(getString(R.string.verification_of), phone);
        if (!ListenerUtil.mutListener.listen(14285)) {
            tag = this.getTag();
        }
        final View dialogView = activity.getLayoutInflater().inflate(R.layout.dialog_sms_verification, null);
        final Button requestCallButton = dialogView.findViewById(R.id.request_call);
        if (!ListenerUtil.mutListener.listen(14287)) {
            requestCallButton.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    if (!ListenerUtil.mutListener.listen(14286)) {
                        callback.onCallRequested(tag);
                    }
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(14290)) {
            if (ConfigUtils.getAppTheme(activity) == ConfigUtils.THEME_DARK) {
                if (!ListenerUtil.mutListener.listen(14289)) {
                    if (requestCallButton.getCompoundDrawables()[0] != null) {
                        if (!ListenerUtil.mutListener.listen(14288)) {
                            requestCallButton.getCompoundDrawables()[0].setColorFilter(getResources().getColor(android.R.color.white), PorterDuff.Mode.SRC_IN);
                        }
                    }
                }
            }
        }
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(getActivity(), getTheme());
        if (!ListenerUtil.mutListener.listen(14291)) {
            builder.setTitle(title);
        }
        if (!ListenerUtil.mutListener.listen(14292)) {
            builder.setView(dialogView);
        }
        if (!ListenerUtil.mutListener.listen(14293)) {
            builder.setPositiveButton(getString(R.string.ok), new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialog, int whichButton) {
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(14295)) {
            builder.setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialog, int whichButton) {
                    if (!ListenerUtil.mutListener.listen(14294)) {
                        callback.onNo(tag);
                    }
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(14296)) {
            alertDialog = builder.create();
        }
        return alertDialog;
    }

    @Override
    public void onCancel(DialogInterface dialogInterface) {
        if (!ListenerUtil.mutListener.listen(14297)) {
            callback.onNo(tag);
        }
    }

    @Override
    public void onStart() {
        if (!ListenerUtil.mutListener.listen(14298)) {
            super.onStart();
        }
        ColorStateList colorStateList = DialogUtil.getButtonColorStateList(activity);
        if (!ListenerUtil.mutListener.listen(14299)) {
            alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(colorStateList);
        }
        if (!ListenerUtil.mutListener.listen(14300)) {
            alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(colorStateList);
        }
        if (!ListenerUtil.mutListener.listen(14302)) {
            alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    EditText editText = alertDialog.findViewById(R.id.code_edittext);
                    String code = editText.getText().toString();
                    if (!ListenerUtil.mutListener.listen(14301)) {
                        callback.onYes(tag, code);
                    }
                }
            });
        }
    }
}
