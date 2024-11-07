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
package ch.threema.app.dialogs;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.InputType;
import android.view.View;
import com.google.android.material.chip.Chip;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textfield.TextInputLayout;
import androidx.annotation.NonNull;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialog;
import ch.threema.app.R;
import ch.threema.app.emojis.EmojiEditText;
import ch.threema.client.ProtocolDefines;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class NewContactDialog extends ThreemaDialogFragment {

    private NewContactDialogClickListener callback;

    private Activity activity;

    private AlertDialog alertDialog;

    public static NewContactDialog newInstance(@StringRes int title, @StringRes int message, @StringRes int positive, @StringRes int negative) {
        NewContactDialog dialog = new NewContactDialog();
        Bundle args = new Bundle();
        if (!ListenerUtil.mutListener.listen(13699)) {
            args.putInt("title", title);
        }
        if (!ListenerUtil.mutListener.listen(13700)) {
            args.putInt("message", message);
        }
        if (!ListenerUtil.mutListener.listen(13701)) {
            args.putInt("positive", positive);
        }
        if (!ListenerUtil.mutListener.listen(13702)) {
            args.putInt("negative", negative);
        }
        if (!ListenerUtil.mutListener.listen(13703)) {
            dialog.setArguments(args);
        }
        return dialog;
    }

    public interface NewContactDialogClickListener {

        void onContactEnter(String tag, String text);

        void onCancel(String tag);

        void onScanButtonClick(String tag);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(13704)) {
            super.onCreate(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(13709)) {
            if (callback == null) {
                try {
                    if (!ListenerUtil.mutListener.listen(13705)) {
                        callback = (NewContactDialogClickListener) getTargetFragment();
                    }
                } catch (ClassCastException e) {
                }
                if (!ListenerUtil.mutListener.listen(13708)) {
                    // called from an activity rather than a fragment
                    if (callback == null) {
                        if (!ListenerUtil.mutListener.listen(13707)) {
                            if (activity instanceof NewContactDialogClickListener) {
                                if (!ListenerUtil.mutListener.listen(13706)) {
                                    callback = (NewContactDialogClickListener) activity;
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
        if (!ListenerUtil.mutListener.listen(13710)) {
            super.onAttach(activity);
        }
        if (!ListenerUtil.mutListener.listen(13711)) {
            this.activity = activity;
        }
    }

    @NonNull
    @Override
    public AppCompatDialog onCreateDialog(Bundle savedInstanceState) {
        int title = getArguments().getInt("title");
        int message = getArguments().getInt("message");
        int positive = getArguments().getInt("positive");
        int negative = getArguments().getInt("negative");
        final String tag = this.getTag();
        final View dialogView = activity.getLayoutInflater().inflate(R.layout.dialog_new_contact, null);
        final EmojiEditText editText = dialogView.findViewById(R.id.edit_text);
        final TextInputLayout editTextLayout = dialogView.findViewById(R.id.text_input_layout);
        if (!ListenerUtil.mutListener.listen(13712)) {
            editText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_CAP_CHARACTERS | InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS);
        }
        if (!ListenerUtil.mutListener.listen(13713)) {
            editText.setFilters(new InputFilter[] { new InputFilter.AllCaps(), new InputFilter.LengthFilter(ProtocolDefines.IDENTITY_LEN) });
        }
        final Chip scanButton = dialogView.findViewById(R.id.scan_button);
        if (!ListenerUtil.mutListener.listen(13715)) {
            scanButton.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    if (!ListenerUtil.mutListener.listen(13714)) {
                        // do not dismiss dialog
                        callback.onScanButtonClick(tag);
                    }
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(13722)) {
            if ((ListenerUtil.mutListener.listen(13720) ? (message >= 0) : (ListenerUtil.mutListener.listen(13719) ? (message <= 0) : (ListenerUtil.mutListener.listen(13718) ? (message > 0) : (ListenerUtil.mutListener.listen(13717) ? (message < 0) : (ListenerUtil.mutListener.listen(13716) ? (message == 0) : (message != 0))))))) {
                if (!ListenerUtil.mutListener.listen(13721)) {
                    editTextLayout.setHint(getString(message));
                }
            }
        }
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(getActivity());
        if (!ListenerUtil.mutListener.listen(13723)) {
            builder.setView(dialogView);
        }
        if (!ListenerUtil.mutListener.listen(13730)) {
            if ((ListenerUtil.mutListener.listen(13728) ? (title >= 0) : (ListenerUtil.mutListener.listen(13727) ? (title <= 0) : (ListenerUtil.mutListener.listen(13726) ? (title > 0) : (ListenerUtil.mutListener.listen(13725) ? (title < 0) : (ListenerUtil.mutListener.listen(13724) ? (title == 0) : (title != 0))))))) {
                if (!ListenerUtil.mutListener.listen(13729)) {
                    builder.setTitle(title);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(13732)) {
            builder.setPositiveButton(getString(positive), new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialog, int whichButton) {
                    if (!ListenerUtil.mutListener.listen(13731)) {
                        callback.onContactEnter(tag, editText.getText().toString());
                    }
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(13734)) {
            builder.setNegativeButton(getString(negative), new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialog, int whichButton) {
                    if (!ListenerUtil.mutListener.listen(13733)) {
                        callback.onCancel(tag);
                    }
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(13735)) {
            alertDialog = builder.create();
        }
        return alertDialog;
    }

    @Override
    public void onCancel(@NonNull DialogInterface dialogInterface) {
        if (!ListenerUtil.mutListener.listen(13736)) {
            callback.onCancel(getTag());
        }
    }

    @Override
    public void onStart() {
        if (!ListenerUtil.mutListener.listen(13737)) {
            super.onStart();
        }
    }
}
