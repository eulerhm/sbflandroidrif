/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
 * Copyright (c) 2018-2021 Threema GmbH
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
import android.os.Bundle;
import android.view.View;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import androidx.annotation.NonNull;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatDialog;
import androidx.appcompat.widget.AppCompatCheckBox;
import ch.threema.app.R;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 *  A dialog with a title and a checkbox
 */
public class TextWithCheckboxDialog extends ThreemaDialogFragment {

    private TextWithCheckboxDialogClickListener callback;

    private Activity activity;

    public interface TextWithCheckboxDialogClickListener {

        void onYes(String tag, Object data, boolean checked);
    }

    public static TextWithCheckboxDialog newInstance(String message, @StringRes int checkboxLabel, @StringRes int positive, @StringRes int negative) {
        TextWithCheckboxDialog dialog = new TextWithCheckboxDialog();
        Bundle args = new Bundle();
        if (!ListenerUtil.mutListener.listen(14481)) {
            args.putString("message", message);
        }
        if (!ListenerUtil.mutListener.listen(14482)) {
            args.putInt("checkboxLabel", checkboxLabel);
        }
        if (!ListenerUtil.mutListener.listen(14483)) {
            args.putInt("positive", positive);
        }
        if (!ListenerUtil.mutListener.listen(14484)) {
            args.putInt("negative", negative);
        }
        if (!ListenerUtil.mutListener.listen(14485)) {
            dialog.setArguments(args);
        }
        return dialog;
    }

    @Override
    public void onAttach(@NonNull Activity activity) {
        if (!ListenerUtil.mutListener.listen(14486)) {
            super.onAttach(activity);
        }
        if (!ListenerUtil.mutListener.listen(14487)) {
            this.activity = activity;
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(14488)) {
            super.onCreate(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(14493)) {
            if (callback == null) {
                try {
                    if (!ListenerUtil.mutListener.listen(14489)) {
                        callback = (TextWithCheckboxDialogClickListener) getTargetFragment();
                    }
                } catch (ClassCastException e) {
                }
                if (!ListenerUtil.mutListener.listen(14492)) {
                    // called from an activity rather than a fragment
                    if (callback == null) {
                        if (!ListenerUtil.mutListener.listen(14491)) {
                            if (activity instanceof TextWithCheckboxDialogClickListener) {
                                if (!ListenerUtil.mutListener.listen(14490)) {
                                    callback = (TextWithCheckboxDialogClickListener) activity;
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @NonNull
    @Override
    public AppCompatDialog onCreateDialog(Bundle savedInstanceState) {
        String message = getArguments().getString("message");
        @StringRes
        int checkboxLabel = getArguments().getInt("checkboxLabel");
        @StringRes
        int positive = getArguments().getInt("positive");
        @StringRes
        int negative = getArguments().getInt("negative");
        final View dialogView = activity.getLayoutInflater().inflate(R.layout.dialog_text_with_checkbox, null);
        final AppCompatCheckBox checkbox = dialogView.findViewById(R.id.checkbox);
        final String tag = this.getTag();
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(getActivity(), getTheme()).setTitle(message).setView(dialogView).setCancelable(false).setNegativeButton(negative, null).setPositiveButton(positive, (dialog, which) -> callback.onYes(tag, object, checkbox.isChecked()));
        if (!ListenerUtil.mutListener.listen(14494)) {
            checkbox.setChecked(false);
        }
        if (!ListenerUtil.mutListener.listen(14502)) {
            if ((ListenerUtil.mutListener.listen(14499) ? (checkboxLabel >= 0) : (ListenerUtil.mutListener.listen(14498) ? (checkboxLabel <= 0) : (ListenerUtil.mutListener.listen(14497) ? (checkboxLabel > 0) : (ListenerUtil.mutListener.listen(14496) ? (checkboxLabel < 0) : (ListenerUtil.mutListener.listen(14495) ? (checkboxLabel == 0) : (checkboxLabel != 0))))))) {
                if (!ListenerUtil.mutListener.listen(14501)) {
                    checkbox.setText(checkboxLabel);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(14500)) {
                    checkbox.setVisibility(View.GONE);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(14503)) {
            setCancelable(false);
        }
        return builder.create();
    }
}
