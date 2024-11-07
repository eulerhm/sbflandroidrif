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
package ch.threema.app.fragments.wizard;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.google.android.material.textfield.TextInputLayout;
import ch.threema.app.R;
import ch.threema.app.threemasafe.ThreemaSafeAdvancedDialog;
import ch.threema.app.threemasafe.ThreemaSafeServerInfo;
import ch.threema.app.utils.AppRestrictionUtil;
import ch.threema.app.utils.ConfigUtils;
import ch.threema.app.utils.EditTextUtil;
import ch.threema.app.utils.TestUtil;
import static ch.threema.app.threemasafe.ThreemaSafeServiceImpl.MAX_PW_LENGTH;
import static ch.threema.app.threemasafe.ThreemaSafeServiceImpl.MIN_PW_LENGTH;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class WizardFragment1 extends WizardFragment implements ThreemaSafeAdvancedDialog.WizardDialogCallback {

    public static final int PAGE_ID = 1;

    private static final String DIALOG_TAG_ADVANCED = "adv";

    private EditText password1, password2;

    private TextInputLayout password1layout, password2layout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        if (!ListenerUtil.mutListener.listen(23963)) {
            // inflate content layout
            contentViewStub.setLayoutResource(R.layout.fragment_wizard1);
        }
        if (!ListenerUtil.mutListener.listen(23964)) {
            contentViewStub.inflate();
        }
        WizardFragment5.SettingsInterface callback = (WizardFragment5.SettingsInterface) getActivity();
        if (!ListenerUtil.mutListener.listen(23965)) {
            this.password1 = rootView.findViewById(R.id.safe_password1);
        }
        if (!ListenerUtil.mutListener.listen(23966)) {
            this.password2 = rootView.findViewById(R.id.safe_password2);
        }
        if (!ListenerUtil.mutListener.listen(23967)) {
            this.password1layout = rootView.findViewById(R.id.password1layout);
        }
        if (!ListenerUtil.mutListener.listen(23968)) {
            this.password2layout = rootView.findViewById(R.id.password2layout);
        }
        if (!ListenerUtil.mutListener.listen(23971)) {
            if (!TestUtil.empty(callback.getSafePassword())) {
                if (!ListenerUtil.mutListener.listen(23969)) {
                    this.password1.setText(callback.getSafePassword());
                }
                if (!ListenerUtil.mutListener.listen(23970)) {
                    this.password2.setText(callback.getSafePassword());
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(23972)) {
            this.password1.addTextChangedListener(new PasswordWatcher());
        }
        if (!ListenerUtil.mutListener.listen(23973)) {
            this.password2.addTextChangedListener(new PasswordWatcher());
        }
        Button advancedOptions = rootView.findViewById(R.id.advanced_options);
        if (!ListenerUtil.mutListener.listen(23974)) {
            advancedOptions.setVisibility(View.VISIBLE);
        }
        if (!ListenerUtil.mutListener.listen(23975)) {
            advancedOptions.setOnClickListener(v -> {
                ThreemaSafeAdvancedDialog dialog = ThreemaSafeAdvancedDialog.newInstance(callback.getSafeServerInfo(), false);
                dialog.setTargetFragment(this, 0);
                dialog.show(getFragmentManager(), DIALOG_TAG_ADVANCED);
            });
        }
        if (!ListenerUtil.mutListener.listen(23985)) {
            if (ConfigUtils.isWorkRestricted()) {
                if (!ListenerUtil.mutListener.listen(23978)) {
                    // administrator forced use of threema safe. do not allow user to override advanced settings
                    if (callback.getSafeForcePasswordEntry()) {
                        TextView explainText = rootView.findViewById(R.id.safe_enable_explain);
                        if (!ListenerUtil.mutListener.listen(23976)) {
                            explainText.setText(R.string.safe_configure_choose_password_force);
                        }
                        if (!ListenerUtil.mutListener.listen(23977)) {
                            advancedOptions.setVisibility(View.GONE);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(23984)) {
                    // threema safe password entry disabled completely
                    if (callback.getSafeSkipBackupPasswordEntry()) {
                        if (!ListenerUtil.mutListener.listen(23979)) {
                            this.password1layout.setVisibility(View.GONE);
                        }
                        if (!ListenerUtil.mutListener.listen(23980)) {
                            this.password2layout.setVisibility(View.GONE);
                        }
                        if (!ListenerUtil.mutListener.listen(23981)) {
                            rootView.findViewById(R.id.safe_enable_explain).setVisibility(View.GONE);
                        }
                        if (!ListenerUtil.mutListener.listen(23982)) {
                            rootView.findViewById(R.id.disabled_by_policy).setVisibility(View.VISIBLE);
                        }
                        if (!ListenerUtil.mutListener.listen(23983)) {
                            advancedOptions.setVisibility(View.GONE);
                        }
                    }
                }
            }
        }
        return rootView;
    }

    @Override
    protected int getAdditionalInfoText() {
        return R.string.safe_enable_explain;
    }

    @Override
    public void onYes(String tag, ThreemaSafeServerInfo serverInfo) {
        if (!ListenerUtil.mutListener.listen(23986)) {
            ((WizardFragment1.OnSettingsChangedListener) getActivity()).onSafeServerInfoSet(serverInfo);
        }
    }

    @Override
    public void onNo(String tag) {
    }

    private class PasswordWatcher implements TextWatcher {

        private PasswordWatcher() {
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        @Override
        public void afterTextChanged(Editable s) {
            boolean passwordOk = getPasswordOK(password1.getText().toString(), password2.getText().toString());
            if (!ListenerUtil.mutListener.listen(23989)) {
                if (passwordOk) {
                    if (!ListenerUtil.mutListener.listen(23988)) {
                        ((WizardFragment1.OnSettingsChangedListener) getActivity()).onSafePasswordSet(s.toString());
                    }
                } else {
                    if (!ListenerUtil.mutListener.listen(23987)) {
                        ((WizardFragment1.OnSettingsChangedListener) getActivity()).onSafePasswordSet(null);
                    }
                }
            }
        }
    }

    public static boolean getPasswordLengthOK(String text, int minLength) {
        return (ListenerUtil.mutListener.listen(24001) ? ((ListenerUtil.mutListener.listen(23995) ? (text != null || (ListenerUtil.mutListener.listen(23994) ? (text.length() <= minLength) : (ListenerUtil.mutListener.listen(23993) ? (text.length() > minLength) : (ListenerUtil.mutListener.listen(23992) ? (text.length() < minLength) : (ListenerUtil.mutListener.listen(23991) ? (text.length() != minLength) : (ListenerUtil.mutListener.listen(23990) ? (text.length() == minLength) : (text.length() >= minLength))))))) : (text != null && (ListenerUtil.mutListener.listen(23994) ? (text.length() <= minLength) : (ListenerUtil.mutListener.listen(23993) ? (text.length() > minLength) : (ListenerUtil.mutListener.listen(23992) ? (text.length() < minLength) : (ListenerUtil.mutListener.listen(23991) ? (text.length() != minLength) : (ListenerUtil.mutListener.listen(23990) ? (text.length() == minLength) : (text.length() >= minLength)))))))) || (ListenerUtil.mutListener.listen(24000) ? (text.length() >= MAX_PW_LENGTH) : (ListenerUtil.mutListener.listen(23999) ? (text.length() > MAX_PW_LENGTH) : (ListenerUtil.mutListener.listen(23998) ? (text.length() < MAX_PW_LENGTH) : (ListenerUtil.mutListener.listen(23997) ? (text.length() != MAX_PW_LENGTH) : (ListenerUtil.mutListener.listen(23996) ? (text.length() == MAX_PW_LENGTH) : (text.length() <= MAX_PW_LENGTH))))))) : ((ListenerUtil.mutListener.listen(23995) ? (text != null || (ListenerUtil.mutListener.listen(23994) ? (text.length() <= minLength) : (ListenerUtil.mutListener.listen(23993) ? (text.length() > minLength) : (ListenerUtil.mutListener.listen(23992) ? (text.length() < minLength) : (ListenerUtil.mutListener.listen(23991) ? (text.length() != minLength) : (ListenerUtil.mutListener.listen(23990) ? (text.length() == minLength) : (text.length() >= minLength))))))) : (text != null && (ListenerUtil.mutListener.listen(23994) ? (text.length() <= minLength) : (ListenerUtil.mutListener.listen(23993) ? (text.length() > minLength) : (ListenerUtil.mutListener.listen(23992) ? (text.length() < minLength) : (ListenerUtil.mutListener.listen(23991) ? (text.length() != minLength) : (ListenerUtil.mutListener.listen(23990) ? (text.length() == minLength) : (text.length() >= minLength)))))))) && (ListenerUtil.mutListener.listen(24000) ? (text.length() >= MAX_PW_LENGTH) : (ListenerUtil.mutListener.listen(23999) ? (text.length() > MAX_PW_LENGTH) : (ListenerUtil.mutListener.listen(23998) ? (text.length() < MAX_PW_LENGTH) : (ListenerUtil.mutListener.listen(23997) ? (text.length() != MAX_PW_LENGTH) : (ListenerUtil.mutListener.listen(23996) ? (text.length() == MAX_PW_LENGTH) : (text.length() <= MAX_PW_LENGTH))))))));
    }

    private boolean getPasswordOK(String password1Text, String password2Text) {
        boolean lengthOk = getPasswordLengthOK(password1Text, AppRestrictionUtil.isSafePasswordPatternSet(getContext()) ? 1 : MIN_PW_LENGTH);
        boolean passwordsMatch = (ListenerUtil.mutListener.listen(24002) ? (password1Text != null || password1Text.equals(password2Text)) : (password1Text != null && password1Text.equals(password2Text)));
        if (!ListenerUtil.mutListener.listen(24016)) {
            if ((ListenerUtil.mutListener.listen(24009) ? ((ListenerUtil.mutListener.listen(24003) ? (!lengthOk || password1Text != null) : (!lengthOk && password1Text != null)) || (ListenerUtil.mutListener.listen(24008) ? (password1Text.length() >= 0) : (ListenerUtil.mutListener.listen(24007) ? (password1Text.length() <= 0) : (ListenerUtil.mutListener.listen(24006) ? (password1Text.length() < 0) : (ListenerUtil.mutListener.listen(24005) ? (password1Text.length() != 0) : (ListenerUtil.mutListener.listen(24004) ? (password1Text.length() == 0) : (password1Text.length() > 0))))))) : ((ListenerUtil.mutListener.listen(24003) ? (!lengthOk || password1Text != null) : (!lengthOk && password1Text != null)) && (ListenerUtil.mutListener.listen(24008) ? (password1Text.length() >= 0) : (ListenerUtil.mutListener.listen(24007) ? (password1Text.length() <= 0) : (ListenerUtil.mutListener.listen(24006) ? (password1Text.length() < 0) : (ListenerUtil.mutListener.listen(24005) ? (password1Text.length() != 0) : (ListenerUtil.mutListener.listen(24004) ? (password1Text.length() == 0) : (password1Text.length() > 0))))))))) {
                if (!ListenerUtil.mutListener.listen(24014)) {
                    this.password1layout.setError(getString(R.string.password_too_short_generic));
                }
                if (!ListenerUtil.mutListener.listen(24015)) {
                    this.password2layout.setError(null);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(24010)) {
                    this.password1layout.setError(null);
                }
                if (!ListenerUtil.mutListener.listen(24013)) {
                    if (!TestUtil.empty(this.password2.getText())) {
                        if (!ListenerUtil.mutListener.listen(24012)) {
                            this.password2layout.setError(passwordsMatch ? null : getString(R.string.passwords_dont_match));
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(24011)) {
                            this.password2layout.setError(null);
                        }
                    }
                }
            }
        }
        return ((ListenerUtil.mutListener.listen(24017) ? (lengthOk || passwordsMatch) : (lengthOk && passwordsMatch)));
    }

    public interface OnSettingsChangedListener {

        void onSafePasswordSet(String password);

        void onSafeServerInfoSet(ThreemaSafeServerInfo serverInfo);
    }

    @Override
    public void onResume() {
        if (!ListenerUtil.mutListener.listen(24018)) {
            super.onResume();
        }
        if (!ListenerUtil.mutListener.listen(24019)) {
            initValues();
        }
        if (!ListenerUtil.mutListener.listen(24022)) {
            if (this.password1 != null) {
                if (!ListenerUtil.mutListener.listen(24020)) {
                    this.password1.requestFocus();
                }
                if (!ListenerUtil.mutListener.listen(24021)) {
                    EditTextUtil.showSoftKeyboard(this.password1);
                }
            }
        }
    }

    @Override
    public void onPause() {
        if (!ListenerUtil.mutListener.listen(24025)) {
            if (this.password1 != null) {
                if (!ListenerUtil.mutListener.listen(24023)) {
                    this.password1.clearFocus();
                }
                if (!ListenerUtil.mutListener.listen(24024)) {
                    EditTextUtil.hideSoftKeyboard(this.password1);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(24026)) {
            super.onPause();
        }
    }

    private void initValues() {
        if (!ListenerUtil.mutListener.listen(24030)) {
            if (isResumed()) {
                WizardFragment5.SettingsInterface callback = (WizardFragment5.SettingsInterface) getActivity();
                if (!ListenerUtil.mutListener.listen(24029)) {
                    if (!callback.getSafeDisabled()) {
                        if (!ListenerUtil.mutListener.listen(24027)) {
                            password1.setText(callback.getSafePassword());
                        }
                        if (!ListenerUtil.mutListener.listen(24028)) {
                            password2.setText(callback.getSafePassword());
                        }
                    }
                }
            }
        }
    }
}
