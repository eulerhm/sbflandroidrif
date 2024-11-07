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

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Date;
import ch.threema.app.R;
import ch.threema.app.dialogs.GenericAlertDialog;
import ch.threema.app.dialogs.GenericProgressDialog;
import ch.threema.app.dialogs.WizardDialog;
import ch.threema.app.ui.NewWizardFingerPrintView;
import ch.threema.app.utils.DialogUtil;
import ch.threema.app.utils.TestUtil;
import ch.threema.base.ThreemaException;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class WizardFingerPrintActivity extends WizardBackgroundActivity implements WizardDialog.WizardDialogCallback, GenericAlertDialog.DialogClickListener {

    private static final Logger logger = LoggerFactory.getLogger(WizardFingerPrintActivity.class);

    public static final int PROGRESS_MAX = 50;

    private static final String DIALOG_TAG_CREATE_ID = "ci";

    private static final String DIALOG_TAG_CREATE_ERROR = "ni";

    private static final String DIALOG_TAG_FINGERPRINT_INFO = "fi";

    private ProgressBar swipeProgress;

    private ImageView fingerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(981)) {
            super.onCreate(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(982)) {
            setContentView(R.layout.activity_new_fingerprint);
        }
        if (!ListenerUtil.mutListener.listen(983)) {
            swipeProgress = findViewById(R.id.wizard1_swipe_progress);
        }
        if (!ListenerUtil.mutListener.listen(984)) {
            swipeProgress.setMax(PROGRESS_MAX);
        }
        if (!ListenerUtil.mutListener.listen(985)) {
            swipeProgress.setProgress(0);
        }
        if (!ListenerUtil.mutListener.listen(986)) {
            fingerView = findViewById(R.id.finger_overlay);
        }
        FrameLayout infoView = findViewById(R.id.more_info_layout);
        if (!ListenerUtil.mutListener.listen(988)) {
            infoView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    WizardDialog wizardDialog = WizardDialog.newInstance(R.string.new_wizard_info_fingerprint, R.string.ok);
                    if (!ListenerUtil.mutListener.listen(987)) {
                        wizardDialog.show(getSupportFragmentManager(), DIALOG_TAG_FINGERPRINT_INFO);
                    }
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(1001)) {
            ((NewWizardFingerPrintView) findViewById(R.id.wizard1_finger_print)).setOnSwipeByte(new NewWizardFingerPrintView.OnSwipeResult() {

                @Override
                public void newBytes(byte[] bytes, int step, int maxSteps) {
                    if (!ListenerUtil.mutListener.listen(989)) {
                        swipeProgress.setProgress(step);
                    }
                    if (!ListenerUtil.mutListener.listen(992)) {
                        if (fingerView != null) {
                            if (!ListenerUtil.mutListener.listen(990)) {
                                fingerView.setVisibility(View.GONE);
                            }
                            if (!ListenerUtil.mutListener.listen(991)) {
                                fingerView = null;
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(1000)) {
                        if ((ListenerUtil.mutListener.listen(997) ? (step <= maxSteps) : (ListenerUtil.mutListener.listen(996) ? (step > maxSteps) : (ListenerUtil.mutListener.listen(995) ? (step < maxSteps) : (ListenerUtil.mutListener.listen(994) ? (step != maxSteps) : (ListenerUtil.mutListener.listen(993) ? (step == maxSteps) : (step >= maxSteps))))))) {
                            if (!ListenerUtil.mutListener.listen(998)) {
                                // disable fingerprint widget
                                findViewById(R.id.wizard1_finger_print).setEnabled(false);
                            }
                            if (!ListenerUtil.mutListener.listen(999)) {
                                // generate id and stuff
                                createIdentity(bytes);
                            }
                        }
                    }
                }
            }, PROGRESS_MAX);
        }
    }

    @SuppressLint("StaticFieldLeak")
    private void createIdentity(final byte[] bytes) {
        if (!ListenerUtil.mutListener.listen(1019)) {
            new AsyncTask<Void, Void, String>() {

                @Override
                protected void onPreExecute() {
                    if (!ListenerUtil.mutListener.listen(1002)) {
                        GenericProgressDialog.newInstance(R.string.wizard_first_create_id, R.string.please_wait).show(getSupportFragmentManager(), DIALOG_TAG_CREATE_ID);
                    }
                }

                @Override
                protected String doInBackground(Void... params) {
                    try {
                        if (!ListenerUtil.mutListener.listen(1009)) {
                            if (!userService.hasIdentity()) {
                                if (!ListenerUtil.mutListener.listen(1005)) {
                                    userService.createIdentity(bytes);
                                }
                                if (!ListenerUtil.mutListener.listen(1006)) {
                                    preferenceService.resetIDBackupCount();
                                }
                                if (!ListenerUtil.mutListener.listen(1007)) {
                                    preferenceService.setLastIDBackupReminderDate(new Date());
                                }
                                if (!ListenerUtil.mutListener.listen(1008)) {
                                    preferenceService.setWizardRunning(true);
                                }
                            }
                        }
                    } catch (final ThreemaException e) {
                        if (!ListenerUtil.mutListener.listen(1003)) {
                            logger.error("Exception", e);
                        }
                        return e.getMessage();
                    } catch (final Exception e) {
                        if (!ListenerUtil.mutListener.listen(1004)) {
                            logger.error("Exception", e);
                        }
                        return getString(R.string.new_wizard_need_internet);
                    }
                    return null;
                }

                @Override
                protected void onPostExecute(String errorString) {
                    if (!ListenerUtil.mutListener.listen(1010)) {
                        DialogUtil.dismissDialog(getSupportFragmentManager(), DIALOG_TAG_CREATE_ID, true);
                    }
                    if (!ListenerUtil.mutListener.listen(1018)) {
                        if (TestUtil.empty(errorString)) {
                            if (!ListenerUtil.mutListener.listen(1015)) {
                                startActivity(new Intent(WizardFingerPrintActivity.this, WizardBaseActivity.class));
                            }
                            if (!ListenerUtil.mutListener.listen(1016)) {
                                overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out);
                            }
                            if (!ListenerUtil.mutListener.listen(1017)) {
                                finish();
                            }
                        } else {
                            try {
                                if (!ListenerUtil.mutListener.listen(1012)) {
                                    userService.removeIdentity();
                                }
                            } catch (Exception e) {
                                if (!ListenerUtil.mutListener.listen(1011)) {
                                    logger.error("Exception", e);
                                }
                            }
                            GenericAlertDialog dialog = GenericAlertDialog.newInstance(R.string.error, errorString, R.string.try_again, R.string.cancel);
                            if (!ListenerUtil.mutListener.listen(1013)) {
                                dialog.setData(bytes);
                            }
                            if (!ListenerUtil.mutListener.listen(1014)) {
                                getSupportFragmentManager().beginTransaction().add(dialog, DIALOG_TAG_CREATE_ERROR).commitAllowingStateLoss();
                            }
                        }
                    }
                }
            }.execute();
        }
    }

    @Override
    public void onBackPressed() {
        if (!ListenerUtil.mutListener.listen(1020)) {
            finish();
        }
    }

    @Override
    public void onYes(String tag, Object data) {
        if (!ListenerUtil.mutListener.listen(1022)) {
            if (tag.equals(DIALOG_TAG_CREATE_ERROR)) {
                if (!ListenerUtil.mutListener.listen(1021)) {
                    createIdentity((byte[]) data);
                }
            }
        }
    }

    @Override
    public void onNo(String tag, Object data) {
        if (!ListenerUtil.mutListener.listen(1023)) {
            finish();
        }
    }

    @Override
    public void onNo(String tag) {
    }
}
