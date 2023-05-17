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
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.SpannableString;
import android.text.TextWatcher;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import com.google.android.material.checkbox.MaterialCheckBox;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialog;
import androidx.core.text.util.LinkifyCompat;
import ch.threema.app.R;
import ch.threema.app.utils.DialogUtil;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class PasswordEntryDialog extends ThreemaDialogFragment implements GenericAlertDialog.DialogClickListener {

    private static final String DIALOG_TAG_CONFIRM_CHECKBOX = "dtcc";

    protected PasswordEntryDialogClickListener callback;

    protected Activity activity;

    protected AlertDialog alertDialog;

    protected boolean isLinkify = false;

    protected boolean isLengthCheck = true;

    protected int minLength, maxLength;

    protected MaterialCheckBox checkBox;

    public static PasswordEntryDialog newInstance(@StringRes int title, @StringRes int message, @StringRes int hint, @StringRes int positive, @StringRes int negative, int minLength, int maxLength, int confirmHint, int inputType, int checkboxText) {
        PasswordEntryDialog dialog = new PasswordEntryDialog();
        Bundle args = new Bundle();
        if (!ListenerUtil.mutListener.listen(13738)) {
            args.putInt("title", title);
        }
        if (!ListenerUtil.mutListener.listen(13739)) {
            args.putInt("message", message);
        }
        if (!ListenerUtil.mutListener.listen(13740)) {
            args.putInt("hint", hint);
        }
        if (!ListenerUtil.mutListener.listen(13741)) {
            args.putInt("positive", positive);
        }
        if (!ListenerUtil.mutListener.listen(13742)) {
            args.putInt("negative", negative);
        }
        if (!ListenerUtil.mutListener.listen(13743)) {
            args.putInt("minLength", minLength);
        }
        if (!ListenerUtil.mutListener.listen(13744)) {
            args.putInt("maxLength", maxLength);
        }
        if (!ListenerUtil.mutListener.listen(13745)) {
            args.putInt("confirmHint", confirmHint);
        }
        if (!ListenerUtil.mutListener.listen(13746)) {
            args.putInt("inputType", inputType);
        }
        if (!ListenerUtil.mutListener.listen(13747)) {
            args.putInt("checkboxText", checkboxText);
        }
        if (!ListenerUtil.mutListener.listen(13748)) {
            dialog.setArguments(args);
        }
        return dialog;
    }

    public static PasswordEntryDialog newInstance(@StringRes int title, @StringRes int message, @StringRes int hint, @StringRes int positive, @StringRes int negative, int minLength, int maxLength, int confirmHint, int inputType, int checkboxText, int checkboxConfirmText) {
        PasswordEntryDialog dialog = new PasswordEntryDialog();
        Bundle args = new Bundle();
        if (!ListenerUtil.mutListener.listen(13749)) {
            args.putInt("title", title);
        }
        if (!ListenerUtil.mutListener.listen(13750)) {
            args.putInt("message", message);
        }
        if (!ListenerUtil.mutListener.listen(13751)) {
            args.putInt("hint", hint);
        }
        if (!ListenerUtil.mutListener.listen(13752)) {
            args.putInt("positive", positive);
        }
        if (!ListenerUtil.mutListener.listen(13753)) {
            args.putInt("negative", negative);
        }
        if (!ListenerUtil.mutListener.listen(13754)) {
            args.putInt("minLength", minLength);
        }
        if (!ListenerUtil.mutListener.listen(13755)) {
            args.putInt("maxLength", maxLength);
        }
        if (!ListenerUtil.mutListener.listen(13756)) {
            args.putInt("confirmHint", confirmHint);
        }
        if (!ListenerUtil.mutListener.listen(13757)) {
            args.putInt("inputType", inputType);
        }
        if (!ListenerUtil.mutListener.listen(13758)) {
            args.putInt("checkboxText", checkboxText);
        }
        if (!ListenerUtil.mutListener.listen(13759)) {
            args.putInt("checkboxConfirmText", checkboxConfirmText);
        }
        if (!ListenerUtil.mutListener.listen(13760)) {
            dialog.setArguments(args);
        }
        return dialog;
    }

    @Override
    public void onYes(String tag, Object data) {
    }

    @Override
    public void onNo(String tag, Object data) {
        if (!ListenerUtil.mutListener.listen(13761)) {
            checkBox.setChecked(false);
        }
    }

    public interface PasswordEntryDialogClickListener {

        void onYes(String tag, String text, boolean isChecked, Object data);

        void onNo(String tag);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(13762)) {
            super.onCreate(savedInstanceState);
        }
        try {
            if (!ListenerUtil.mutListener.listen(13763)) {
                callback = (PasswordEntryDialogClickListener) getTargetFragment();
            }
        } catch (ClassCastException e) {
        }
        if (!ListenerUtil.mutListener.listen(13766)) {
            // called from an activity rather than a fragment
            if (callback == null) {
                if (!ListenerUtil.mutListener.listen(13764)) {
                    if (!(activity instanceof PasswordEntryDialogClickListener)) {
                        throw new ClassCastException("Calling fragment must implement TextEntryDialogClickListener interface");
                    }
                }
                if (!ListenerUtil.mutListener.listen(13765)) {
                    callback = (PasswordEntryDialogClickListener) activity;
                }
            }
        }
    }

    @Override
    public void onAttach(Activity activity) {
        if (!ListenerUtil.mutListener.listen(13767)) {
            super.onAttach(activity);
        }
        if (!ListenerUtil.mutListener.listen(13768)) {
            this.activity = activity;
        }
    }

    @Override
    public AppCompatDialog onCreateDialog(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(13770)) {
            if ((ListenerUtil.mutListener.listen(13769) ? (savedInstanceState != null || alertDialog != null) : (savedInstanceState != null && alertDialog != null))) {
                return alertDialog;
            }
        }
        final int title = getArguments().getInt("title");
        int message = getArguments().getInt("message");
        int hint = getArguments().getInt("hint");
        int positive = getArguments().getInt("positive");
        int negative = getArguments().getInt("negative");
        int inputType = getArguments().getInt("inputType", 0);
        if (!ListenerUtil.mutListener.listen(13771)) {
            minLength = getArguments().getInt("minLength", 0);
        }
        if (!ListenerUtil.mutListener.listen(13772)) {
            maxLength = getArguments().getInt("maxLength", 0);
        }
        final int confirmHint = getArguments().getInt("confirmHint", 0);
        final int checkboxText = getArguments().getInt("checkboxText", 0);
        final int checkboxConfirmText = getArguments().getInt("checkboxConfirmText", 0);
        final String tag = this.getTag();
        // InputType defaults
        final int inputTypePasswordHidden = InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS | InputType.TYPE_TEXT_VARIATION_PASSWORD;
        final View dialogView = activity.getLayoutInflater().inflate(R.layout.dialog_password_entry, null);
        final TextView messageTextView = dialogView.findViewById(R.id.message_text);
        final TextInputEditText editText1 = dialogView.findViewById(R.id.password1);
        final TextInputEditText editText2 = dialogView.findViewById(R.id.password2);
        final TextInputLayout editText1Layout = dialogView.findViewById(R.id.password1layout);
        final TextInputLayout editText2Layout = dialogView.findViewById(R.id.password2layout);
        if (!ListenerUtil.mutListener.listen(13773)) {
            checkBox = dialogView.findViewById(R.id.check_box);
        }
        if (!ListenerUtil.mutListener.listen(13774)) {
            editText1.addTextChangedListener(new PasswordWatcher(editText1, editText2));
        }
        if (!ListenerUtil.mutListener.listen(13775)) {
            editText2.addTextChangedListener(new PasswordWatcher(editText1, editText2));
        }
        if (!ListenerUtil.mutListener.listen(13783)) {
            if ((ListenerUtil.mutListener.listen(13780) ? (maxLength >= 0) : (ListenerUtil.mutListener.listen(13779) ? (maxLength <= 0) : (ListenerUtil.mutListener.listen(13778) ? (maxLength < 0) : (ListenerUtil.mutListener.listen(13777) ? (maxLength != 0) : (ListenerUtil.mutListener.listen(13776) ? (maxLength == 0) : (maxLength > 0))))))) {
                if (!ListenerUtil.mutListener.listen(13781)) {
                    editText1.setFilters(new InputFilter[] { new InputFilter.LengthFilter(maxLength) });
                }
                if (!ListenerUtil.mutListener.listen(13782)) {
                    editText2.setFilters(new InputFilter[] { new InputFilter.LengthFilter(maxLength) });
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(13794)) {
            if ((ListenerUtil.mutListener.listen(13788) ? (message >= 0) : (ListenerUtil.mutListener.listen(13787) ? (message <= 0) : (ListenerUtil.mutListener.listen(13786) ? (message > 0) : (ListenerUtil.mutListener.listen(13785) ? (message < 0) : (ListenerUtil.mutListener.listen(13784) ? (message == 0) : (message != 0))))))) {
                String messageString = getString(message);
                if (!ListenerUtil.mutListener.listen(13793)) {
                    if (messageString.contains("https://")) {
                        final SpannableString s = new SpannableString(messageString);
                        if (!ListenerUtil.mutListener.listen(13790)) {
                            LinkifyCompat.addLinks(s, Linkify.WEB_URLS);
                        }
                        if (!ListenerUtil.mutListener.listen(13791)) {
                            messageTextView.setText(s);
                        }
                        if (!ListenerUtil.mutListener.listen(13792)) {
                            isLinkify = true;
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(13789)) {
                            messageTextView.setText(messageString);
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(13802)) {
            if ((ListenerUtil.mutListener.listen(13799) ? (inputType >= 0) : (ListenerUtil.mutListener.listen(13798) ? (inputType <= 0) : (ListenerUtil.mutListener.listen(13797) ? (inputType > 0) : (ListenerUtil.mutListener.listen(13796) ? (inputType < 0) : (ListenerUtil.mutListener.listen(13795) ? (inputType == 0) : (inputType != 0))))))) {
                if (!ListenerUtil.mutListener.listen(13800)) {
                    editText1.setInputType(inputType);
                }
                if (!ListenerUtil.mutListener.listen(13801)) {
                    editText2.setInputType(inputType);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(13810)) {
            if ((ListenerUtil.mutListener.listen(13807) ? (hint >= 0) : (ListenerUtil.mutListener.listen(13806) ? (hint <= 0) : (ListenerUtil.mutListener.listen(13805) ? (hint > 0) : (ListenerUtil.mutListener.listen(13804) ? (hint < 0) : (ListenerUtil.mutListener.listen(13803) ? (hint == 0) : (hint != 0))))))) {
                if (!ListenerUtil.mutListener.listen(13808)) {
                    editText1Layout.setHint(getString(hint));
                }
                if (!ListenerUtil.mutListener.listen(13809)) {
                    editText2Layout.setHint(getString(hint));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(13829)) {
            if ((ListenerUtil.mutListener.listen(13815) ? (checkboxText >= 0) : (ListenerUtil.mutListener.listen(13814) ? (checkboxText <= 0) : (ListenerUtil.mutListener.listen(13813) ? (checkboxText > 0) : (ListenerUtil.mutListener.listen(13812) ? (checkboxText < 0) : (ListenerUtil.mutListener.listen(13811) ? (checkboxText == 0) : (checkboxText != 0))))))) {
                if (!ListenerUtil.mutListener.listen(13816)) {
                    checkBox.setVisibility(View.VISIBLE);
                }
                if (!ListenerUtil.mutListener.listen(13817)) {
                    checkBox.setText(checkboxText);
                }
                if (!ListenerUtil.mutListener.listen(13828)) {
                    if ((ListenerUtil.mutListener.listen(13822) ? (checkboxConfirmText >= 0) : (ListenerUtil.mutListener.listen(13821) ? (checkboxConfirmText <= 0) : (ListenerUtil.mutListener.listen(13820) ? (checkboxConfirmText > 0) : (ListenerUtil.mutListener.listen(13819) ? (checkboxConfirmText < 0) : (ListenerUtil.mutListener.listen(13818) ? (checkboxConfirmText == 0) : (checkboxConfirmText != 0))))))) {
                        if (!ListenerUtil.mutListener.listen(13827)) {
                            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                                @Override
                                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                                    if (!ListenerUtil.mutListener.listen(13826)) {
                                        if (isChecked) {
                                            if (!ListenerUtil.mutListener.listen(13823)) {
                                                DialogUtil.dismissDialog(getFragmentManager(), DIALOG_TAG_CONFIRM_CHECKBOX, true);
                                            }
                                            GenericAlertDialog genericAlertDialog = GenericAlertDialog.newInstance(title, checkboxConfirmText, R.string.ok, R.string.cancel);
                                            if (!ListenerUtil.mutListener.listen(13824)) {
                                                genericAlertDialog.setTargetFragment(PasswordEntryDialog.this, 0);
                                            }
                                            if (!ListenerUtil.mutListener.listen(13825)) {
                                                genericAlertDialog.show(getFragmentManager(), DIALOG_TAG_CONFIRM_CHECKBOX);
                                            }
                                        }
                                    }
                                }
                            });
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(13842)) {
            if ((ListenerUtil.mutListener.listen(13834) ? (confirmHint >= 0) : (ListenerUtil.mutListener.listen(13833) ? (confirmHint <= 0) : (ListenerUtil.mutListener.listen(13832) ? (confirmHint > 0) : (ListenerUtil.mutListener.listen(13831) ? (confirmHint < 0) : (ListenerUtil.mutListener.listen(13830) ? (confirmHint != 0) : (confirmHint == 0))))))) {
                if (!ListenerUtil.mutListener.listen(13838)) {
                    editText1.setInputType(inputTypePasswordHidden);
                }
                if (!ListenerUtil.mutListener.listen(13839)) {
                    editText2.setVisibility(View.GONE);
                }
                if (!ListenerUtil.mutListener.listen(13840)) {
                    editText2Layout.setVisibility(View.GONE);
                }
                if (!ListenerUtil.mutListener.listen(13841)) {
                    isLengthCheck = false;
                }
            } else {
                if (!ListenerUtil.mutListener.listen(13835)) {
                    editText2Layout.setHint(getString(confirmHint));
                }
                if (!ListenerUtil.mutListener.listen(13836)) {
                    editText1Layout.setHelperTextEnabled(true);
                }
                if (!ListenerUtil.mutListener.listen(13837)) {
                    editText1Layout.setHelperText(String.format(activity.getString(R.string.password_too_short), minLength));
                }
            }
        }
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(getActivity(), getTheme());
        if (!ListenerUtil.mutListener.listen(13849)) {
            if ((ListenerUtil.mutListener.listen(13847) ? (title >= 0) : (ListenerUtil.mutListener.listen(13846) ? (title <= 0) : (ListenerUtil.mutListener.listen(13845) ? (title > 0) : (ListenerUtil.mutListener.listen(13844) ? (title < 0) : (ListenerUtil.mutListener.listen(13843) ? (title == 0) : (title != 0))))))) {
                if (!ListenerUtil.mutListener.listen(13848)) {
                    builder.setTitle(title);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(13850)) {
            builder.setView(dialogView);
        }
        if (!ListenerUtil.mutListener.listen(13854)) {
            builder.setPositiveButton(getString(positive), new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialog, int whichButton) {
                    if (!ListenerUtil.mutListener.listen(13853)) {
                        if (checkboxText != 0) {
                            if (!ListenerUtil.mutListener.listen(13852)) {
                                callback.onYes(tag, editText1.getText().toString(), checkBox.isChecked(), object);
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(13851)) {
                                callback.onYes(tag, editText1.getText().toString(), false, object);
                            }
                        }
                    }
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(13856)) {
            builder.setNegativeButton(getString(negative), new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialog, int whichButton) {
                    if (!ListenerUtil.mutListener.listen(13855)) {
                        callback.onNo(tag);
                    }
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(13857)) {
            alertDialog = builder.create();
        }
        return alertDialog;
    }

    @Override
    public void onCancel(DialogInterface dialogInterface) {
        if (!ListenerUtil.mutListener.listen(13858)) {
            callback.onNo(this.getTag());
        }
    }

    public class PasswordWatcher implements TextWatcher {

        private EditText password1, password2;

        public PasswordWatcher(final EditText password1, final EditText password2) {
            if (!ListenerUtil.mutListener.listen(13859)) {
                this.password1 = password1;
            }
            if (!ListenerUtil.mutListener.listen(13860)) {
                this.password2 = password2;
            }
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        @Override
        public void afterTextChanged(Editable s) {
            String password1Text = password1.getText().toString();
            String password2Text = password2.getText().toString();
            if (!ListenerUtil.mutListener.listen(13868)) {
                if (isLengthCheck) {
                    if (!ListenerUtil.mutListener.listen(13867)) {
                        alertDialog.getButton(DialogInterface.BUTTON_POSITIVE).setEnabled(getPasswordOK(password1Text, password2Text));
                    }
                } else {
                    if (!ListenerUtil.mutListener.listen(13866)) {
                        alertDialog.getButton(DialogInterface.BUTTON_POSITIVE).setEnabled((ListenerUtil.mutListener.listen(13865) ? (password1Text.length() >= 0) : (ListenerUtil.mutListener.listen(13864) ? (password1Text.length() <= 0) : (ListenerUtil.mutListener.listen(13863) ? (password1Text.length() < 0) : (ListenerUtil.mutListener.listen(13862) ? (password1Text.length() != 0) : (ListenerUtil.mutListener.listen(13861) ? (password1Text.length() == 0) : (password1Text.length() > 0)))))));
                    }
                }
            }
        }
    }

    private boolean getPasswordOK(String password1Text, String password2Text) {
        boolean lengthOk = (ListenerUtil.mutListener.listen(13873) ? (password1Text.length() <= minLength) : (ListenerUtil.mutListener.listen(13872) ? (password1Text.length() > minLength) : (ListenerUtil.mutListener.listen(13871) ? (password1Text.length() < minLength) : (ListenerUtil.mutListener.listen(13870) ? (password1Text.length() != minLength) : (ListenerUtil.mutListener.listen(13869) ? (password1Text.length() == minLength) : (password1Text.length() >= minLength))))));
        if (!ListenerUtil.mutListener.listen(13886)) {
            if ((ListenerUtil.mutListener.listen(13878) ? (maxLength >= 0) : (ListenerUtil.mutListener.listen(13877) ? (maxLength <= 0) : (ListenerUtil.mutListener.listen(13876) ? (maxLength < 0) : (ListenerUtil.mutListener.listen(13875) ? (maxLength != 0) : (ListenerUtil.mutListener.listen(13874) ? (maxLength == 0) : (maxLength > 0))))))) {
                if (!ListenerUtil.mutListener.listen(13885)) {
                    lengthOk = (ListenerUtil.mutListener.listen(13884) ? (lengthOk || (ListenerUtil.mutListener.listen(13883) ? (password1Text.length() >= maxLength) : (ListenerUtil.mutListener.listen(13882) ? (password1Text.length() > maxLength) : (ListenerUtil.mutListener.listen(13881) ? (password1Text.length() < maxLength) : (ListenerUtil.mutListener.listen(13880) ? (password1Text.length() != maxLength) : (ListenerUtil.mutListener.listen(13879) ? (password1Text.length() == maxLength) : (password1Text.length() <= maxLength))))))) : (lengthOk && (ListenerUtil.mutListener.listen(13883) ? (password1Text.length() >= maxLength) : (ListenerUtil.mutListener.listen(13882) ? (password1Text.length() > maxLength) : (ListenerUtil.mutListener.listen(13881) ? (password1Text.length() < maxLength) : (ListenerUtil.mutListener.listen(13880) ? (password1Text.length() != maxLength) : (ListenerUtil.mutListener.listen(13879) ? (password1Text.length() == maxLength) : (password1Text.length() <= maxLength))))))));
                }
            }
        }
        boolean passwordsMatch = password1Text.equals(password2Text);
        return ((ListenerUtil.mutListener.listen(13887) ? (lengthOk || passwordsMatch) : (lengthOk && passwordsMatch)));
    }

    @Override
    public void onStart() {
        if (!ListenerUtil.mutListener.listen(13888)) {
            super.onStart();
        }
        if (!ListenerUtil.mutListener.listen(13891)) {
            if (isLinkify) {
                View textView = alertDialog.findViewById(R.id.message_text);
                if (!ListenerUtil.mutListener.listen(13890)) {
                    if (textView instanceof TextView) {
                        if (!ListenerUtil.mutListener.listen(13889)) {
                            ((TextView) textView).setMovementMethod(LinkMovementMethod.getInstance());
                        }
                    }
                }
            }
        }
        final TextInputEditText editText1 = alertDialog.findViewById(R.id.password1);
        if (!ListenerUtil.mutListener.listen(13905)) {
            if (isLengthCheck) {
                final TextInputEditText editText2 = alertDialog.findViewById(R.id.password2);
                if (!ListenerUtil.mutListener.listen(13904)) {
                    if ((ListenerUtil.mutListener.listen(13900) ? (editText1 != null || editText2 != null) : (editText1 != null && editText2 != null))) {
                        if (!ListenerUtil.mutListener.listen(13903)) {
                            if ((ListenerUtil.mutListener.listen(13901) ? (editText1.getText() != null || editText2.getText() != null) : (editText1.getText() != null && editText2.getText() != null))) {
                                if (!ListenerUtil.mutListener.listen(13902)) {
                                    alertDialog.getButton(DialogInterface.BUTTON_POSITIVE).setEnabled(getPasswordOK(editText1.getText().toString(), editText2.getText().toString()));
                                }
                            }
                        }
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(13899)) {
                    if ((ListenerUtil.mutListener.listen(13892) ? (editText1 != null || editText1.getText() != null) : (editText1 != null && editText1.getText() != null))) {
                        if (!ListenerUtil.mutListener.listen(13898)) {
                            alertDialog.getButton(DialogInterface.BUTTON_POSITIVE).setEnabled((ListenerUtil.mutListener.listen(13897) ? (editText1.getText().length() >= 0) : (ListenerUtil.mutListener.listen(13896) ? (editText1.getText().length() <= 0) : (ListenerUtil.mutListener.listen(13895) ? (editText1.getText().length() < 0) : (ListenerUtil.mutListener.listen(13894) ? (editText1.getText().length() != 0) : (ListenerUtil.mutListener.listen(13893) ? (editText1.getText().length() == 0) : (editText1.getText().length() > 0)))))));
                        }
                    }
                }
            }
        }
        ColorStateList colorStateList = DialogUtil.getButtonColorStateList(activity);
        if (!ListenerUtil.mutListener.listen(13906)) {
            alertDialog.getButton(DialogInterface.BUTTON_POSITIVE).setTextColor(colorStateList);
        }
        if (!ListenerUtil.mutListener.listen(13907)) {
            alertDialog.getButton(DialogInterface.BUTTON_NEGATIVE).setTextColor(colorStateList);
        }
    }
}
