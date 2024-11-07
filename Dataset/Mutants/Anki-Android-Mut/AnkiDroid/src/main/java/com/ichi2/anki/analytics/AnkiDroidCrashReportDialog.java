/**
 * ************************************************************************************
 *  Copyright (c) 2015 Timothy Rae <perceptualchaos2@gmail.com>                          *
 *  Copyright (c) 2020 Mike Hardy <github@mikehardy.net>                                 *
 *                                                                                       *
 *  This program is free software; you can redistribute it and/or modify it under        *
 *  the terms of the GNU General Public License as published by the Free Software        *
 *  Foundation; either version 3 of the License, or (at your option) any later           *
 *  version.                                                                             *
 *                                                                                       *
 *  This program is distributed in the hope that it will be useful, but WITHOUT ANY      *
 *  WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A      *
 *  PARTICULAR PURPOSE. See the GNU General Public License for more details.             *
 *                                                                                       *
 *  You should have received a copy of the GNU General Public License along with         *
 *  this program.  If not, see <http://www.gnu.org/licenses/>.                           *
 * **************************************************************************************
 */
package com.ichi2.anki.analytics;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import com.ichi2.anki.AnkiDroidApp;
import com.ichi2.anki.R;
import org.acra.config.ACRAConfigurationException;
import org.acra.config.CoreConfigurationBuilder;
import org.acra.config.DialogConfiguration;
import org.acra.config.DialogConfigurationBuilder;
import org.acra.dialog.CrashReportDialog;
import org.acra.dialog.CrashReportDialogHelper;
import androidx.annotation.NonNull;
import timber.log.Timber;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * This file will appear to have static type errors because BaseCrashReportDialog extends android.support.XXX
 * instead of androidx.XXX . Details at {@see https://github.com/ankidroid/Anki-Android/wiki/Crash-Reports}
 */
// we are sufficiently registered in this special case
@SuppressLint("Registered")
public class AnkiDroidCrashReportDialog extends CrashReportDialog implements DialogInterface.OnClickListener, DialogInterface.OnDismissListener {

    private static final String STATE_COMMENT = "comment";

    private CheckBox mAlwaysReportCheckBox;

    private EditText mUserComment;

    private CrashReportDialogHelper mHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(34)) {
            super.onCreate(savedInstanceState);
        }
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        try {
            CoreConfigurationBuilder builder = AnkiDroidApp.getInstance().getAcraCoreConfigBuilder();
            DialogConfiguration dialogConfig = (DialogConfiguration) builder.getPluginConfigurationBuilder((DialogConfigurationBuilder.class)).build();
            if (!ListenerUtil.mutListener.listen(36)) {
                dialogBuilder.setIcon(dialogConfig.resIcon());
            }
            if (!ListenerUtil.mutListener.listen(37)) {
                dialogBuilder.setTitle(dialogConfig.title());
            }
            if (!ListenerUtil.mutListener.listen(38)) {
                dialogBuilder.setPositiveButton(dialogConfig.positiveButtonText(), AnkiDroidCrashReportDialog.this);
            }
            if (!ListenerUtil.mutListener.listen(39)) {
                dialogBuilder.setNegativeButton(dialogConfig.negativeButtonText(), AnkiDroidCrashReportDialog.this);
            }
        } catch (ACRAConfigurationException ace) {
            if (!ListenerUtil.mutListener.listen(35)) {
                Timber.e(ace, "Unable to initialize ACRA while creating ACRA dialog?");
            }
        }
        if (!ListenerUtil.mutListener.listen(40)) {
            mHelper = new CrashReportDialogHelper(this, getIntent());
        }
        if (!ListenerUtil.mutListener.listen(41)) {
            dialogBuilder.setView(buildCustomView(savedInstanceState));
        }
        AlertDialog dialog = dialogBuilder.create();
        if (!ListenerUtil.mutListener.listen(42)) {
            dialog.setCanceledOnTouchOutside(false);
        }
        if (!ListenerUtil.mutListener.listen(43)) {
            dialog.setOnDismissListener(this);
        }
        if (!ListenerUtil.mutListener.listen(44)) {
            dialog.show();
        }
    }

    /**
     * Build the custom view used by the dialog
     */
    @Override
    @NonNull
    protected View buildCustomView(Bundle savedInstanceState) {
        SharedPreferences preferences = AnkiDroidApp.getSharedPrefs(this);
        LayoutInflater inflater = getLayoutInflater();
        // when you inflate into an alert dialog, you have no parent view
        @SuppressLint("InflateParams")
        View rootView = inflater.inflate(R.layout.feedback, null);
        if (!ListenerUtil.mutListener.listen(45)) {
            mAlwaysReportCheckBox = rootView.findViewById(R.id.alwaysReportCheckbox);
        }
        if (!ListenerUtil.mutListener.listen(46)) {
            mAlwaysReportCheckBox.setChecked(preferences.getBoolean("autoreportCheckboxValue", true));
        }
        if (!ListenerUtil.mutListener.listen(47)) {
            mUserComment = rootView.findViewById(R.id.etFeedbackText);
        }
        if (!ListenerUtil.mutListener.listen(50)) {
            // Set user comment if reloading after the activity has been stopped
            if (savedInstanceState != null) {
                String savedValue = savedInstanceState.getString(STATE_COMMENT);
                if (!ListenerUtil.mutListener.listen(49)) {
                    if (savedValue != null) {
                        if (!ListenerUtil.mutListener.listen(48)) {
                            mUserComment.setText(savedValue);
                        }
                    }
                }
            }
        }
        return rootView;
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        if (!ListenerUtil.mutListener.listen(57)) {
            if (which == DialogInterface.BUTTON_POSITIVE) {
                // Next time don't tick the auto-report checkbox by default
                boolean autoReport = mAlwaysReportCheckBox.isChecked();
                SharedPreferences preferences = AnkiDroidApp.getSharedPrefs(this);
                if (!ListenerUtil.mutListener.listen(52)) {
                    preferences.edit().putBoolean("autoreportCheckboxValue", autoReport).apply();
                }
                if (!ListenerUtil.mutListener.listen(55)) {
                    // Set the autoreport value to true if ticked
                    if (autoReport) {
                        if (!ListenerUtil.mutListener.listen(53)) {
                            preferences.edit().putString(AnkiDroidApp.FEEDBACK_REPORT_KEY, AnkiDroidApp.FEEDBACK_REPORT_ALWAYS).apply();
                        }
                        if (!ListenerUtil.mutListener.listen(54)) {
                            AnkiDroidApp.getInstance().setAcraReportingMode(AnkiDroidApp.FEEDBACK_REPORT_ALWAYS);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(56)) {
                    // Send the crash report
                    mHelper.sendCrash(mUserComment.getText().toString(), "");
                }
            } else {
                if (!ListenerUtil.mutListener.listen(51)) {
                    mHelper.cancelReports();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(58)) {
            finish();
        }
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        if (!ListenerUtil.mutListener.listen(59)) {
            finish();
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        if (!ListenerUtil.mutListener.listen(60)) {
            super.onSaveInstanceState(outState);
        }
        if (!ListenerUtil.mutListener.listen(63)) {
            if ((ListenerUtil.mutListener.listen(61) ? (mUserComment != null || mUserComment.getText() != null) : (mUserComment != null && mUserComment.getText() != null))) {
                if (!ListenerUtil.mutListener.listen(62)) {
                    outState.putString(STATE_COMMENT, mUserComment.getText().toString());
                }
            }
        }
    }
}
