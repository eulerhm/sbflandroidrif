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
package ch.threema.app.activities.wizard;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.Date;
import androidx.appcompat.widget.AppCompatCheckBox;
import ch.threema.app.R;
import ch.threema.app.ThreemaApplication;
import ch.threema.app.activities.PrivacyPolicyActivity;
import ch.threema.app.dialogs.WizardDialog;
import ch.threema.app.services.PreferenceService;
import ch.threema.app.threemasafe.ThreemaSafeMDMConfig;
import ch.threema.app.utils.AnimationUtil;
import ch.threema.app.utils.AppRestrictionUtil;
import ch.threema.app.utils.ConfigUtils;
import ch.threema.app.utils.TestUtil;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class WizardIntroActivity extends WizardBackgroundActivity implements WizardDialog.WizardDialogCallback {

    private static final String DIALOG_TAG_CHECK_PP = "pp";

    private static final int ACTIVITY_RESULT_PRIVACY_POLICY = 9442;

    private AnimationDrawable frameAnimation;

    private AppCompatCheckBox privacyPolicyCheckBox;

    private LinearLayout buttonLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(1024)) {
            super.onCreate(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(1025)) {
            setContentView(R.layout.activity_wizard_intro);
        }
        if (!ListenerUtil.mutListener.listen(1026)) {
            privacyPolicyCheckBox = findViewById(R.id.wizard_switch_accept_privacy_policy);
        }
        if (!ListenerUtil.mutListener.listen(1031)) {
            privacyPolicyCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (!ListenerUtil.mutListener.listen(1030)) {
                        if (!isChecked) {
                            if (!ListenerUtil.mutListener.listen(1029)) {
                                if (preferenceService.getPrivacyPolicyAccepted() != null) {
                                    if (!ListenerUtil.mutListener.listen(1028)) {
                                        preferenceService.clearPrivacyPolicyAccepted();
                                    }
                                }
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(1027)) {
                                privacyPolicyCheckBox.setBackgroundDrawable(getResources().getDrawable(R.drawable.shape_switch));
                            }
                        }
                    }
                }
            });
        }
        TextView privacyPolicyExplainText = findViewById(R.id.wizard_privacy_policy_explain);
        if (!ListenerUtil.mutListener.listen(1032)) {
            buttonLayout = findViewById(R.id.button_layout);
        }
        if (!ListenerUtil.mutListener.listen(1047)) {
            if (ConfigUtils.isWorkRestricted()) {
                if (!ListenerUtil.mutListener.listen(1046)) {
                    // Skip privacy policy check if admin pre-set a backup to restore - either Safe or ID
                    if (ThreemaSafeMDMConfig.getInstance().isRestoreForced()) {
                        if (!ListenerUtil.mutListener.listen(1043)) {
                            checkPrivacyPolicy(true);
                        }
                        if (!ListenerUtil.mutListener.listen(1044)) {
                            restoreBackup(null);
                        }
                        if (!ListenerUtil.mutListener.listen(1045)) {
                            finish();
                        }
                        return;
                    } else {
                        String backupString = AppRestrictionUtil.getStringRestriction(getString(R.string.restriction__id_backup));
                        String backupPassword = AppRestrictionUtil.getStringRestriction(getString(R.string.restriction__id_backup_password));
                        if (!ListenerUtil.mutListener.listen(1042)) {
                            if ((ListenerUtil.mutListener.listen(1033) ? (!TestUtil.empty(backupString) || !TestUtil.empty(backupPassword)) : (!TestUtil.empty(backupString) && !TestUtil.empty(backupPassword)))) {
                                if (!ListenerUtil.mutListener.listen(1034)) {
                                    checkPrivacyPolicy(true);
                                }
                                Intent intent = new Intent(this, WizardRestoreMainActivity.class);
                                if (!ListenerUtil.mutListener.listen(1038)) {
                                    if ((ListenerUtil.mutListener.listen(1035) ? (!TestUtil.empty(backupString) || !TestUtil.empty(backupPassword)) : (!TestUtil.empty(backupString) && !TestUtil.empty(backupPassword)))) {
                                        if (!ListenerUtil.mutListener.listen(1036)) {
                                            intent.putExtra(ThreemaApplication.INTENT_DATA_ID_BACKUP, backupString);
                                        }
                                        if (!ListenerUtil.mutListener.listen(1037)) {
                                            intent.putExtra(ThreemaApplication.INTENT_DATA_ID_BACKUP_PW, backupPassword);
                                        }
                                    }
                                }
                                if (!ListenerUtil.mutListener.listen(1039)) {
                                    startActivity(intent);
                                }
                                if (!ListenerUtil.mutListener.listen(1040)) {
                                    overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out);
                                }
                                if (!ListenerUtil.mutListener.listen(1041)) {
                                    finish();
                                }
                                return;
                            }
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(1050)) {
            if (savedInstanceState == null) {
                if (!ListenerUtil.mutListener.listen(1048)) {
                    buttonLayout.setVisibility(View.GONE);
                }
                if (!ListenerUtil.mutListener.listen(1049)) {
                    buttonLayout.postDelayed(() -> AnimationUtil.slideInFromBottomOvershoot(buttonLayout), 200);
                }
            }
        }
        ImageView imageView = findViewById(R.id.three_dots);
        if (!ListenerUtil.mutListener.listen(1051)) {
            imageView.setBackgroundResource(R.drawable.animation_wizard2);
        }
        if (!ListenerUtil.mutListener.listen(1052)) {
            frameAnimation = (AnimationDrawable) imageView.getBackground();
        }
        if (!ListenerUtil.mutListener.listen(1053)) {
            frameAnimation.setOneShot(false);
        }
        if (!ListenerUtil.mutListener.listen(1054)) {
            frameAnimation.start();
        }
        if (!ListenerUtil.mutListener.listen(1067)) {
            if (preferenceService.getPrivacyPolicyAccepted() != null) {
                if (!ListenerUtil.mutListener.listen(1065)) {
                    privacyPolicyCheckBox.setVisibility(View.GONE);
                }
                if (!ListenerUtil.mutListener.listen(1066)) {
                    privacyPolicyExplainText.setVisibility(View.GONE);
                }
            } else {
                String privacyPolicy = getString(R.string.privacy_policy);
                SpannableStringBuilder builder = new SpannableStringBuilder();
                if (!ListenerUtil.mutListener.listen(1055)) {
                    builder.append(String.format(getString(R.string.privacy_policy_explain), getString(R.string.app_name), privacyPolicy));
                }
                int index = TextUtils.indexOf(builder, privacyPolicy);
                if (!ListenerUtil.mutListener.listen(1062)) {
                    builder.setSpan(new ClickableSpan() {

                        @Override
                        public void onClick(View widget) {
                            if (!ListenerUtil.mutListener.listen(1056)) {
                                ConfigUtils.setAppTheme(ConfigUtils.THEME_DARK);
                            }
                            if (!ListenerUtil.mutListener.listen(1057)) {
                                startActivityForResult(new Intent(WizardIntroActivity.this, PrivacyPolicyActivity.class), ACTIVITY_RESULT_PRIVACY_POLICY);
                            }
                        }
                    }, index, (ListenerUtil.mutListener.listen(1061) ? (index % privacyPolicy.length()) : (ListenerUtil.mutListener.listen(1060) ? (index / privacyPolicy.length()) : (ListenerUtil.mutListener.listen(1059) ? (index * privacyPolicy.length()) : (ListenerUtil.mutListener.listen(1058) ? (index - privacyPolicy.length()) : (index + privacyPolicy.length()))))), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                }
                if (!ListenerUtil.mutListener.listen(1063)) {
                    privacyPolicyExplainText.setText(builder);
                }
                if (!ListenerUtil.mutListener.listen(1064)) {
                    privacyPolicyExplainText.setMovementMethod(LinkMovementMethod.getInstance());
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(1068)) {
            findViewById(R.id.restore_backup).setOnClickListener(this::restoreBackup);
        }
        if (!ListenerUtil.mutListener.listen(1069)) {
            findViewById(R.id.setup_threema).setOnClickListener(this::setupThreema);
        }
    }

    public void setupThreema(View view) {
        if (!ListenerUtil.mutListener.listen(1074)) {
            if (checkPrivacyPolicy(false)) {
                if (!ListenerUtil.mutListener.listen(1072)) {
                    if (!userService.hasIdentity()) {
                        if (!ListenerUtil.mutListener.listen(1071)) {
                            startActivity(new Intent(this, WizardFingerPrintActivity.class));
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(1070)) {
                            startActivity(new Intent(this, WizardBaseActivity.class));
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(1073)) {
                    overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out);
                }
            }
        }
    }

    /**
     *  Called from button in XML
     *  @param view
     */
    public void restoreBackup(View view) {
        if (!ListenerUtil.mutListener.listen(1077)) {
            if (checkPrivacyPolicy(false)) {
                if (!ListenerUtil.mutListener.listen(1075)) {
                    startActivity(new Intent(this, WizardRestoreMainActivity.class));
                }
                if (!ListenerUtil.mutListener.listen(1076)) {
                    overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out);
                }
            }
        }
    }

    private boolean checkPrivacyPolicy(boolean force) {
        if (!ListenerUtil.mutListener.listen(1078)) {
            if (preferenceService.getPrivacyPolicyAccepted() != null) {
                return true;
            }
        }
        if (!ListenerUtil.mutListener.listen(1081)) {
            if ((ListenerUtil.mutListener.listen(1079) ? (!privacyPolicyCheckBox.isChecked() || !force) : (!privacyPolicyCheckBox.isChecked() && !force))) {
                if (!ListenerUtil.mutListener.listen(1080)) {
                    WizardDialog.newInstance(String.format(getString(R.string.privacy_policy_check_confirm), getString(R.string.app_name)), R.string.ok).show(getSupportFragmentManager(), DIALOG_TAG_CHECK_PP);
                }
                return false;
            }
        }
        if (!ListenerUtil.mutListener.listen(1082)) {
            preferenceService.setPrivacyPolicyAccepted(new Date(), force ? PreferenceService.PRIVACY_POLICY_ACCEPT_IMPLICIT : PreferenceService.PRIVACY_POLICY_ACCEPT_EXCPLICIT);
        }
        return true;
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        if (!ListenerUtil.mutListener.listen(1086)) {
            if (frameAnimation != null) {
                if (!ListenerUtil.mutListener.listen(1085)) {
                    if (hasFocus) {
                        if (!ListenerUtil.mutListener.listen(1084)) {
                            frameAnimation.start();
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(1083)) {
                            frameAnimation.stop();
                        }
                    }
                }
            }
        }
    }

    @Override
    public void onYes(String tag, Object data) {
        if (!ListenerUtil.mutListener.listen(1087)) {
            privacyPolicyCheckBox.setBackgroundDrawable(getResources().getDrawable(R.drawable.shape_switch_alert));
        }
        if (!ListenerUtil.mutListener.listen(1088)) {
            privacyPolicyCheckBox.postDelayed(() -> {
                if (!isFinishing()) {
                    privacyPolicyCheckBox.setBackgroundDrawable(getResources().getDrawable(R.drawable.shape_switch));
                }
            }, 400);
        }
        if (!ListenerUtil.mutListener.listen(1089)) {
            privacyPolicyCheckBox.postDelayed(() -> {
                if (!isFinishing()) {
                    privacyPolicyCheckBox.setBackgroundDrawable(getResources().getDrawable(R.drawable.shape_switch_alert));
                }
            }, 600);
        }
    }

    @Override
    public void onNo(String tag) {
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (!ListenerUtil.mutListener.listen(1090)) {
            super.onActivityResult(requestCode, resultCode, data);
        }
        if (!ListenerUtil.mutListener.listen(1091)) {
            ConfigUtils.resetAppTheme();
        }
    }
}
