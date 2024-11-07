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
package ch.threema.app.fragments.wizard;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import ch.threema.app.R;
import ch.threema.app.dialogs.WizardDialog;
import ch.threema.app.threemasafe.ThreemaSafeServerInfo;
import ch.threema.app.utils.ConfigUtils;
import ch.threema.app.utils.TestUtil;
import static ch.threema.app.ThreemaApplication.EMAIL_LINKED_PLACEHOLDER;
import static ch.threema.app.ThreemaApplication.PHONE_LINKED_PLACEHOLDER;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class WizardFragment5 extends WizardFragment implements View.OnClickListener {

    private TextView nicknameText, phoneText, emailText, syncContactsText, phoneWarnText, emailWarnText, safeText;

    private ImageView phoneWarn, emailWarn;

    private ProgressBar phoneProgress, emailProgress, syncContactsProgress, safeProgress;

    private Button finishButton;

    private SettingsInterface callback;

    public static final int PAGE_ID = 5;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_wizard5, container, false);
        if (!ListenerUtil.mutListener.listen(24234)) {
            nicknameText = rootView.findViewById(R.id.wizard_nickname_preset);
        }
        if (!ListenerUtil.mutListener.listen(24235)) {
            phoneText = rootView.findViewById(R.id.wizard_phone_preset);
        }
        if (!ListenerUtil.mutListener.listen(24236)) {
            emailText = rootView.findViewById(R.id.wizard_email_preset);
        }
        if (!ListenerUtil.mutListener.listen(24237)) {
            syncContactsText = rootView.findViewById(R.id.sync_contacts_preset);
        }
        if (!ListenerUtil.mutListener.listen(24238)) {
            syncContactsProgress = rootView.findViewById(R.id.wizard_contact_sync_progress);
        }
        if (!ListenerUtil.mutListener.listen(24239)) {
            phoneProgress = rootView.findViewById(R.id.wizard_phone_progress);
        }
        if (!ListenerUtil.mutListener.listen(24240)) {
            emailProgress = rootView.findViewById(R.id.wizard_email_progress);
        }
        if (!ListenerUtil.mutListener.listen(24241)) {
            phoneWarn = rootView.findViewById(R.id.wizard_phone_warn);
        }
        if (!ListenerUtil.mutListener.listen(24242)) {
            emailWarn = rootView.findViewById(R.id.wizard_email_warn);
        }
        if (!ListenerUtil.mutListener.listen(24243)) {
            phoneWarnText = rootView.findViewById(R.id.wizard_phone_error_text);
        }
        if (!ListenerUtil.mutListener.listen(24244)) {
            emailWarnText = rootView.findViewById(R.id.wizard_email_error_text);
        }
        if (!ListenerUtil.mutListener.listen(24245)) {
            safeText = rootView.findViewById(R.id.threema_safe_preset);
        }
        if (!ListenerUtil.mutListener.listen(24246)) {
            safeProgress = rootView.findViewById(R.id.threema_safe_progress);
        }
        if (!ListenerUtil.mutListener.listen(24247)) {
            finishButton = rootView.findViewById(R.id.wizard_finish);
        }
        if (!ListenerUtil.mutListener.listen(24253)) {
            finishButton.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    if (!ListenerUtil.mutListener.listen(24248)) {
                        phoneText.setClickable(false);
                    }
                    if (!ListenerUtil.mutListener.listen(24249)) {
                        emailText.setClickable(false);
                    }
                    if (!ListenerUtil.mutListener.listen(24250)) {
                        callback.onWizardFinished(WizardFragment5.this, finishButton);
                    }
                    if (!ListenerUtil.mutListener.listen(24251)) {
                        phoneText.setClickable(true);
                    }
                    if (!ListenerUtil.mutListener.listen(24252)) {
                        emailText.setClickable(true);
                    }
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(24254)) {
            emailText.setOnClickListener(this);
        }
        if (!ListenerUtil.mutListener.listen(24255)) {
            phoneText.setOnClickListener(this);
        }
        if (!ListenerUtil.mutListener.listen(24256)) {
            emailWarnText.setOnClickListener(this);
        }
        if (!ListenerUtil.mutListener.listen(24257)) {
            phoneWarnText.setOnClickListener(this);
        }
        if (!ListenerUtil.mutListener.listen(24260)) {
            if (!ConfigUtils.isWorkBuild()) {
                if (!ListenerUtil.mutListener.listen(24258)) {
                    rootView.findViewById(R.id.wizard_email_layout).setVisibility(View.GONE);
                }
                if (!ListenerUtil.mutListener.listen(24259)) {
                    rootView.findViewById(R.id.wizard_email_error_layout).setVisibility(View.GONE);
                }
            }
        }
        return rootView;
    }

    @Override
    protected int getAdditionalInfoText() {
        return 0;
    }

    @Override
    public void onAttach(Activity activity) {
        if (!ListenerUtil.mutListener.listen(24261)) {
            super.onAttach(activity);
        }
        if (!ListenerUtil.mutListener.listen(24262)) {
            callback = (SettingsInterface) activity;
        }
    }

    void initValues() {
        if (!ListenerUtil.mutListener.listen(24268)) {
            if (isResumed()) {
                String email = callback.getEmail();
                String phone = callback.getPhone();
                if (!ListenerUtil.mutListener.listen(24263)) {
                    nicknameText.setText(callback.getNickname());
                }
                if (!ListenerUtil.mutListener.listen(24264)) {
                    emailText.setText(TestUtil.empty(email) ? getString(R.string.not_linked) : EMAIL_LINKED_PLACEHOLDER.equals(email) ? getString(R.string.unchanged) : email);
                }
                if (!ListenerUtil.mutListener.listen(24265)) {
                    phoneText.setText(TestUtil.empty(phone) ? getString(R.string.not_linked) : PHONE_LINKED_PLACEHOLDER.equals(phone) ? getString(R.string.unchanged) : phone);
                }
                if (!ListenerUtil.mutListener.listen(24266)) {
                    syncContactsText.setText(callback.getSyncContacts() ? R.string.on : R.string.off);
                }
                if (!ListenerUtil.mutListener.listen(24267)) {
                    setThreemaSafeInProgress(false, null);
                }
            }
        }
    }

    @Override
    @SuppressLint("NewApi")
    public void onResume() {
        if (!ListenerUtil.mutListener.listen(24269)) {
            super.onResume();
        }
        if (!ListenerUtil.mutListener.listen(24270)) {
            initValues();
        }
        if (!ListenerUtil.mutListener.listen(24273)) {
            if ((ListenerUtil.mutListener.listen(24271) ? (ConfigUtils.isWorkRestricted() || callback.isSkipWizard()) : (ConfigUtils.isWorkRestricted() && callback.isSkipWizard()))) {
                if (!ListenerUtil.mutListener.listen(24272)) {
                    finishButton.callOnClick();
                }
            }
        }
    }

    @Override
    public void onClick(View v) {
        if (!ListenerUtil.mutListener.listen(24277)) {
            switch(v.getId()) {
                case R.id.wizard_phone_error_text:
                    if (!ListenerUtil.mutListener.listen(24274)) {
                        WizardDialog.newInstance(phoneWarnText.getText().toString(), R.string.ok).show(getFragmentManager(), "ph");
                    }
                    break;
                case R.id.wizard_email_error_text:
                    if (!ListenerUtil.mutListener.listen(24275)) {
                        WizardDialog.newInstance(emailWarnText.getText().toString(), R.string.ok).show(getFragmentManager(), "em");
                    }
                    break;
                case R.id.wizard_email_preset:
                case R.id.wizard_phone_preset:
                    if (!ListenerUtil.mutListener.listen(24276)) {
                        setPage(2);
                    }
                    break;
                default:
                    break;
            }
        }
    }

    public void setMobileLinkingInProgress(boolean inProgress) {
        if (!ListenerUtil.mutListener.listen(24278)) {
            phoneWarn.setVisibility(View.GONE);
        }
        if (!ListenerUtil.mutListener.listen(24279)) {
            phoneProgress.setVisibility(inProgress ? View.VISIBLE : View.GONE);
        }
        if (!ListenerUtil.mutListener.listen(24280)) {
            phoneWarnText.setVisibility(View.GONE);
        }
        if (!ListenerUtil.mutListener.listen(24281)) {
            phoneText.setVisibility(inProgress ? View.GONE : View.VISIBLE);
        }
    }

    public void setEmailLinkingInProgress(boolean inProgress) {
        if (!ListenerUtil.mutListener.listen(24282)) {
            emailWarn.setVisibility(View.GONE);
        }
        if (!ListenerUtil.mutListener.listen(24283)) {
            emailProgress.setVisibility(inProgress ? View.VISIBLE : View.GONE);
        }
        if (!ListenerUtil.mutListener.listen(24284)) {
            emailWarnText.setVisibility(View.GONE);
        }
        if (!ListenerUtil.mutListener.listen(24285)) {
            emailText.setVisibility(inProgress ? View.GONE : View.VISIBLE);
        }
    }

    public void setMobileLinkingAlert(String message) {
        if (!ListenerUtil.mutListener.listen(24286)) {
            phoneWarn.setVisibility(View.VISIBLE);
        }
        if (!ListenerUtil.mutListener.listen(24287)) {
            phoneWarnText.setText(message);
        }
        if (!ListenerUtil.mutListener.listen(24288)) {
            phoneWarnText.setVisibility(View.VISIBLE);
        }
        if (!ListenerUtil.mutListener.listen(24289)) {
            phoneProgress.setVisibility(View.GONE);
        }
        if (!ListenerUtil.mutListener.listen(24290)) {
            phoneText.setVisibility(View.VISIBLE);
        }
    }

    public void setEmailLinkingAlert(String message) {
        if (!ListenerUtil.mutListener.listen(24291)) {
            emailWarn.setVisibility(View.VISIBLE);
        }
        if (!ListenerUtil.mutListener.listen(24292)) {
            emailWarnText.setText(message);
        }
        if (!ListenerUtil.mutListener.listen(24293)) {
            emailWarnText.setVisibility(View.VISIBLE);
        }
        if (!ListenerUtil.mutListener.listen(24294)) {
            emailProgress.setVisibility(View.GONE);
        }
        if (!ListenerUtil.mutListener.listen(24295)) {
            emailText.setVisibility(View.VISIBLE);
        }
    }

    public void setContactsSyncInProgress(boolean inProgress, String text) {
        if (!ListenerUtil.mutListener.listen(24296)) {
            syncContactsProgress.setVisibility(inProgress ? View.VISIBLE : View.GONE);
        }
        if (!ListenerUtil.mutListener.listen(24299)) {
            if (TestUtil.empty(text)) {
                if (!ListenerUtil.mutListener.listen(24298)) {
                    syncContactsText.setText(callback.getSyncContacts() ? R.string.on : R.string.off);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(24297)) {
                    syncContactsText.setText(text);
                }
            }
        }
    }

    public void setThreemaSafeInProgress(boolean inProgress, String text) {
        if (!ListenerUtil.mutListener.listen(24300)) {
            safeProgress.setVisibility(inProgress ? View.VISIBLE : View.GONE);
        }
        if (!ListenerUtil.mutListener.listen(24307)) {
            if (TestUtil.empty(text)) {
                if (!ListenerUtil.mutListener.listen(24306)) {
                    if (TestUtil.empty(callback.getSafePassword())) {
                        if (!ListenerUtil.mutListener.listen(24305)) {
                            safeText.setText(R.string.off);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(24304)) {
                            if (callback.getSafeServerInfo().isDefaultServer()) {
                                if (!ListenerUtil.mutListener.listen(24303)) {
                                    safeText.setText(getString(R.string.on));
                                }
                            } else {
                                if (!ListenerUtil.mutListener.listen(24302)) {
                                    safeText.setText(String.format("%s - %s", getString(R.string.on), callback.getSafeServerInfo().getHostName()));
                                }
                            }
                        }
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(24301)) {
                    safeText.setText(text);
                }
            }
        }
    }

    public interface SettingsInterface {

        String getNickname();

        String getPhone();

        String getPrefix();

        String getNumber();

        String getEmail();

        String getPresetPhone();

        String getPresetEmail();

        boolean getSafeForcePasswordEntry();

        boolean getSafeSkipBackupPasswordEntry();

        boolean getSafeDisabled();

        String getSafePassword();

        ThreemaSafeServerInfo getSafeServerInfo();

        boolean getSyncContacts();

        boolean isReadOnlyProfile();

        boolean isSkipWizard();

        void onWizardFinished(WizardFragment5 fragment, Button finishButton);
    }
}
