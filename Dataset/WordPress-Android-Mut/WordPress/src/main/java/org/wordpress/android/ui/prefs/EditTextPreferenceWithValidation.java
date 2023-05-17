package org.wordpress.android.ui.prefs;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.PasswordTransformationMethod;
import android.util.AttributeSet;
import android.widget.Button;
import androidx.appcompat.app.AlertDialog;
import org.wordpress.android.util.ValidationUtils;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class EditTextPreferenceWithValidation extends SummaryEditTextPreference {

    private ValidationType mValidationType = ValidationType.NONE;

    // Ignore the default value, such as "Not Set", while showing the dialog
    private String mStringToIgnoreForPrefilling = "";

    public EditTextPreferenceWithValidation(Context context) {
        super(context);
    }

    public EditTextPreferenceWithValidation(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public EditTextPreferenceWithValidation(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void showDialog(Bundle state) {
        if (!ListenerUtil.mutListener.listen(14717)) {
            super.showDialog(state);
        }
        final AlertDialog dialog = (AlertDialog) getDialog();
        final Button positiveButton = dialog.getButton(DialogInterface.BUTTON_POSITIVE);
        if (!ListenerUtil.mutListener.listen(14725)) {
            if (positiveButton != null) {
                if (!ListenerUtil.mutListener.listen(14718)) {
                    positiveButton.setOnClickListener(v -> {
                        callChangeListener(getEditText().getText());
                        dialog.dismiss();
                    });
                }
                if (!ListenerUtil.mutListener.listen(14724)) {
                    getEditText().addTextChangedListener(new TextWatcher() {

                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                        }

                        @Override
                        public void afterTextChanged(Editable s) {
                            if (!ListenerUtil.mutListener.listen(14723)) {
                                switch(mValidationType) {
                                    case NONE:
                                        break;
                                    case EMAIL:
                                        if (!ListenerUtil.mutListener.listen(14719)) {
                                            positiveButton.setEnabled(ValidationUtils.validateEmail(s));
                                        }
                                        break;
                                    case PASSWORD:
                                        if (!ListenerUtil.mutListener.listen(14720)) {
                                            positiveButton.setEnabled(ValidationUtils.validatePassword(s));
                                        }
                                        break;
                                    case URL:
                                        if (!ListenerUtil.mutListener.listen(14721)) {
                                            positiveButton.setEnabled(ValidationUtils.validateUrl(s));
                                        }
                                        break;
                                    case PASSWORD_SELF_HOSTED:
                                        if (!ListenerUtil.mutListener.listen(14722)) {
                                            positiveButton.setEnabled(ValidationUtils.validatePasswordSelfHosted(s));
                                        }
                                }
                            }
                        }
                    });
                }
            }
        }
        CharSequence summary = getSummary();
        if (!ListenerUtil.mutListener.listen(14730)) {
            if ((ListenerUtil.mutListener.listen(14726) ? (summary == null && summary.equals(mStringToIgnoreForPrefilling)) : (summary == null || summary.equals(mStringToIgnoreForPrefilling)))) {
                if (!ListenerUtil.mutListener.listen(14729)) {
                    getEditText().setText("");
                }
            } else {
                if (!ListenerUtil.mutListener.listen(14727)) {
                    getEditText().setText(summary);
                }
                if (!ListenerUtil.mutListener.listen(14728)) {
                    getEditText().setSelection(0, summary.length());
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(14732)) {
            // Use "hidden" input type for passwords so characters are replaced with dots for added security.
            hideInputCharacters((ListenerUtil.mutListener.listen(14731) ? (mValidationType == ValidationType.PASSWORD && mValidationType == ValidationType.PASSWORD_SELF_HOSTED) : (mValidationType == ValidationType.PASSWORD || mValidationType == ValidationType.PASSWORD_SELF_HOSTED)));
        }
    }

    public void setValidationType(ValidationType validationType) {
        if (!ListenerUtil.mutListener.listen(14733)) {
            mValidationType = validationType;
        }
    }

    public void setStringToIgnoreForPrefilling(String stringToIgnoreForPrefilling) {
        if (!ListenerUtil.mutListener.listen(14734)) {
            mStringToIgnoreForPrefilling = stringToIgnoreForPrefilling;
        }
    }

    private void hideInputCharacters(boolean hide) {
        int selectionStart = getEditText().getSelectionStart();
        int selectionEnd = getEditText().getSelectionEnd();
        if (!ListenerUtil.mutListener.listen(14735)) {
            getEditText().setTransformationMethod(hide ? PasswordTransformationMethod.getInstance() : null);
        }
        if (!ListenerUtil.mutListener.listen(14736)) {
            getEditText().setSelection(selectionStart, selectionEnd);
        }
    }

    public enum ValidationType {

        NONE, EMAIL, PASSWORD, PASSWORD_SELF_HOSTED, URL
    }
}
