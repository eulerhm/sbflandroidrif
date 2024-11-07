/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
 * Copyright (c) 2016-2021 Threema GmbH
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
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textfield.TextInputLayout;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialog;
import ch.threema.app.R;
import ch.threema.app.ThreemaApplication;
import ch.threema.app.ui.ComposeEditText;
import ch.threema.app.utils.AnimationUtil;
import ch.threema.app.utils.TestUtil;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class ExpandableTextEntryDialog extends ThreemaDialogFragment {

    private ExpandableTextEntryDialogClickListener callback;

    private Activity activity;

    private AlertDialog alertDialog;

    public static ExpandableTextEntryDialog newInstance(String title, int hint, int positive, int negative, boolean expandable) {
        ExpandableTextEntryDialog dialog = new ExpandableTextEntryDialog();
        Bundle args = new Bundle();
        if (!ListenerUtil.mutListener.listen(13444)) {
            args.putString("title", title);
        }
        if (!ListenerUtil.mutListener.listen(13445)) {
            args.putInt("message", hint);
        }
        if (!ListenerUtil.mutListener.listen(13446)) {
            args.putInt("positive", positive);
        }
        if (!ListenerUtil.mutListener.listen(13447)) {
            args.putInt("negative", negative);
        }
        if (!ListenerUtil.mutListener.listen(13448)) {
            args.putBoolean("expandable", expandable);
        }
        if (!ListenerUtil.mutListener.listen(13449)) {
            dialog.setArguments(args);
        }
        return dialog;
    }

    public static ExpandableTextEntryDialog newInstance(String title, int hint, String preset, int positive, int negative, boolean expandable) {
        ExpandableTextEntryDialog dialog = new ExpandableTextEntryDialog();
        Bundle args = new Bundle();
        if (!ListenerUtil.mutListener.listen(13450)) {
            args.putString("title", title);
        }
        if (!ListenerUtil.mutListener.listen(13451)) {
            args.putString("preset", preset);
        }
        if (!ListenerUtil.mutListener.listen(13452)) {
            args.putInt("message", hint);
        }
        if (!ListenerUtil.mutListener.listen(13453)) {
            args.putInt("positive", positive);
        }
        if (!ListenerUtil.mutListener.listen(13454)) {
            args.putInt("negative", negative);
        }
        if (!ListenerUtil.mutListener.listen(13455)) {
            args.putBoolean("expandable", expandable);
        }
        if (!ListenerUtil.mutListener.listen(13456)) {
            dialog.setArguments(args);
        }
        return dialog;
    }

    public interface ExpandableTextEntryDialogClickListener {

        void onYes(String tag, Object data, String text);

        void onNo(String tag);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(13457)) {
            super.onCreate(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(13462)) {
            if (callback == null) {
                try {
                    if (!ListenerUtil.mutListener.listen(13458)) {
                        callback = (ExpandableTextEntryDialogClickListener) getTargetFragment();
                    }
                } catch (ClassCastException e) {
                }
                if (!ListenerUtil.mutListener.listen(13461)) {
                    // called from an activity rather than a fragment
                    if (callback == null) {
                        if (!ListenerUtil.mutListener.listen(13460)) {
                            if (activity instanceof ExpandableTextEntryDialogClickListener) {
                                if (!ListenerUtil.mutListener.listen(13459)) {
                                    callback = (ExpandableTextEntryDialogClickListener) activity;
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
        if (!ListenerUtil.mutListener.listen(13463)) {
            super.onAttach(activity);
        }
        if (!ListenerUtil.mutListener.listen(13464)) {
            this.activity = activity;
        }
    }

    @NonNull
    @Override
    public AppCompatDialog onCreateDialog(Bundle savedInstanceState) {
        String title = getArguments().getString("title");
        String preset = getArguments().getString("preset", null);
        int message = getArguments().getInt("message");
        int positive = getArguments().getInt("positive");
        int negative = getArguments().getInt("negative");
        boolean expandable = getArguments().getBoolean("expandable");
        final String tag = this.getTag();
        final View dialogView = activity.getLayoutInflater().inflate(R.layout.dialog_text_entry_expandable, null);
        final ComposeEditText editText = dialogView.findViewById(R.id.caption_edittext);
        final TextInputLayout editTextContainer = dialogView.findViewById(R.id.edittext_container);
        final TextView addCaptionText = dialogView.findViewById(R.id.add_caption_text);
        final ImageView expandButton = dialogView.findViewById(R.id.expand_button);
        final LinearLayout addCaptionLayout = dialogView.findViewById(R.id.add_caption_intro);
        if (!ListenerUtil.mutListener.listen(13465)) {
            addCaptionLayout.setClickable(true);
        }
        if (!ListenerUtil.mutListener.listen(13467)) {
            addCaptionLayout.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    if (!ListenerUtil.mutListener.listen(13466)) {
                        toggleLayout(expandButton, editTextContainer);
                    }
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(13469)) {
            editText.addTextChangedListener(new TextWatcher() {

                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    if (!ListenerUtil.mutListener.listen(13468)) {
                        ThreemaApplication.activityUserInteract(activity);
                    }
                }

                @Override
                public void afterTextChanged(Editable s) {
                }
            });
        }
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(activity, getTheme());
        if (!ListenerUtil.mutListener.listen(13470)) {
            builder.setView(dialogView);
        }
        if (!ListenerUtil.mutListener.listen(13472)) {
            if (!TestUtil.empty(title)) {
                if (!ListenerUtil.mutListener.listen(13471)) {
                    builder.setTitle(title);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(13479)) {
            if ((ListenerUtil.mutListener.listen(13477) ? (message >= 0) : (ListenerUtil.mutListener.listen(13476) ? (message <= 0) : (ListenerUtil.mutListener.listen(13475) ? (message > 0) : (ListenerUtil.mutListener.listen(13474) ? (message < 0) : (ListenerUtil.mutListener.listen(13473) ? (message == 0) : (message != 0))))))) {
                if (!ListenerUtil.mutListener.listen(13478)) {
                    addCaptionText.setText(message);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(13483)) {
            if (!TestUtil.empty(preset)) {
                if (!ListenerUtil.mutListener.listen(13480)) {
                    editText.setText(preset);
                }
                if (!ListenerUtil.mutListener.listen(13482)) {
                    if (expandable) {
                        if (!ListenerUtil.mutListener.listen(13481)) {
                            toggleLayout(expandButton, editTextContainer);
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(13485)) {
            if (!expandable) {
                if (!ListenerUtil.mutListener.listen(13484)) {
                    addCaptionLayout.setVisibility(View.GONE);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(13487)) {
            builder.setPositiveButton(getString(positive), new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialog, int whichButton) {
                    if (!ListenerUtil.mutListener.listen(13486)) {
                        callback.onYes(tag, object, editText.getText().toString());
                    }
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(13489)) {
            builder.setNegativeButton(getString(negative), new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialog, int whichButton) {
                    if (!ListenerUtil.mutListener.listen(13488)) {
                        callback.onNo(tag);
                    }
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(13490)) {
            alertDialog = builder.create();
        }
        if (!ListenerUtil.mutListener.listen(13491)) {
            setCancelable(false);
        }
        return alertDialog;
    }

    private void toggleLayout(ImageView button, View v) {
        InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        EditText editText = v.findViewById(R.id.caption_edittext);
        if (!ListenerUtil.mutListener.listen(13501)) {
            if (v.isShown()) {
                if (!ListenerUtil.mutListener.listen(13495)) {
                    AnimationUtil.slideUp(activity, v);
                }
                if (!ListenerUtil.mutListener.listen(13496)) {
                    v.setVisibility(View.GONE);
                }
                if (!ListenerUtil.mutListener.listen(13497)) {
                    button.setRotation(0);
                }
                if (!ListenerUtil.mutListener.listen(13500)) {
                    if ((ListenerUtil.mutListener.listen(13498) ? (imm != null || editText != null) : (imm != null && editText != null))) {
                        if (!ListenerUtil.mutListener.listen(13499)) {
                            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                        }
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(13492)) {
                    v.setVisibility(View.VISIBLE);
                }
                if (!ListenerUtil.mutListener.listen(13493)) {
                    AnimationUtil.slideDown(activity, v, () -> {
                        if (editText != null) {
                            editText.requestFocus();
                            if (imm != null) {
                                imm.showSoftInput(editText, 0);
                            }
                        }
                    });
                }
                if (!ListenerUtil.mutListener.listen(13494)) {
                    button.setRotation(90);
                }
            }
        }
    }
}
