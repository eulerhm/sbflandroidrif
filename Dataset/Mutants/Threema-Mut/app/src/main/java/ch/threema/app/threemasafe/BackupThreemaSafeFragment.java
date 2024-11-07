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
package ch.threema.app.threemasafe;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.format.Formatter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.UiThread;
import androidx.fragment.app.Fragment;
import ch.threema.app.R;
import ch.threema.app.ThreemaApplication;
import ch.threema.app.dialogs.GenericAlertDialog;
import ch.threema.app.dialogs.GenericProgressDialog;
import ch.threema.app.dialogs.SimpleStringAlertDialog;
import ch.threema.app.listeners.ThreemaSafeListener;
import ch.threema.app.managers.ListenerManager;
import ch.threema.app.managers.ServiceManager;
import ch.threema.app.services.PreferenceService;
import ch.threema.app.ui.SilentSwitchCompat;
import ch.threema.app.utils.ConfigUtils;
import ch.threema.app.utils.DialogUtil;
import ch.threema.app.utils.LocaleUtil;
import ch.threema.app.utils.RuntimeUtil;
import ch.threema.base.ThreemaException;
import static ch.threema.app.threemasafe.ThreemaSafeConfigureActivity.EXTRA_CHANGE_PASSWORD;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class BackupThreemaSafeFragment extends Fragment implements GenericAlertDialog.DialogClickListener {

    private static final Logger logger = LoggerFactory.getLogger(BackupThreemaSafeFragment.class);

    private static final int REQUEST_CODE_SAFE_CONFIGURE = 22;

    private static final int REQUEST_CODE_SAFE_CHANGE_PASSWORD = 23;

    private static final String DIALOG_TAG_DELETING = "dts";

    private static final String DIALOG_TAG_DEACTIVATE_CONFIRM = "dcf";

    private View fragmentView;

    private PreferenceService preferenceService;

    private ThreemaSafeService threemaSafeService;

    private ExtendedFloatingActionButton floatingActionButton;

    private Button changePasswordButton;

    private SilentSwitchCompat enableSwitch;

    private View configLayout, explainLayout;

    private ThreemaSafeListener threemaSafeListener = new ThreemaSafeListener() {

        @Override
        public void onBackupStatusChanged() {
            if (!ListenerUtil.mutListener.listen(42569)) {
                RuntimeUtil.runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        if (!ListenerUtil.mutListener.listen(42568)) {
                            updateUI();
                        }
                    }
                });
            }
        }
    };

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(42570)) {
            super.onCreate(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(42571)) {
            setRetainInstance(true);
        }
        try {
            ServiceManager serviceManager = ThreemaApplication.getServiceManager();
            if (!ListenerUtil.mutListener.listen(42574)) {
                this.preferenceService = serviceManager.getPreferenceService();
            }
            if (!ListenerUtil.mutListener.listen(42575)) {
                this.threemaSafeService = serviceManager.getThreemaSafeService();
            }
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(42572)) {
                logger.error("Exception", e);
            }
            if (!ListenerUtil.mutListener.listen(42573)) {
                getActivity().finish();
            }
        }
        if (!ListenerUtil.mutListener.listen(42576)) {
            ListenerManager.threemaSafeListeners.add(this.threemaSafeListener);
        }
    }

    @Override
    public void onDestroy() {
        if (!ListenerUtil.mutListener.listen(42577)) {
            ListenerManager.threemaSafeListeners.remove(this.threemaSafeListener);
        }
        if (!ListenerUtil.mutListener.listen(42578)) {
            super.onDestroy();
        }
    }

    @Override
    public void onDestroyView() {
        if (!ListenerUtil.mutListener.listen(42579)) {
            this.configLayout = null;
        }
        if (!ListenerUtil.mutListener.listen(42580)) {
            this.explainLayout = null;
        }
        if (!ListenerUtil.mutListener.listen(42581)) {
            this.floatingActionButton.setOnClickListener(null);
        }
        if (!ListenerUtil.mutListener.listen(42582)) {
            this.floatingActionButton = null;
        }
        if (!ListenerUtil.mutListener.listen(42583)) {
            this.changePasswordButton.setOnClickListener(null);
        }
        if (!ListenerUtil.mutListener.listen(42584)) {
            this.changePasswordButton = null;
        }
        if (!ListenerUtil.mutListener.listen(42585)) {
            this.enableSwitch.setOnCheckedChangeListener(null);
        }
        if (!ListenerUtil.mutListener.listen(42586)) {
            this.enableSwitch = null;
        }
        if (!ListenerUtil.mutListener.listen(42587)) {
            this.fragmentView = null;
        }
        if (!ListenerUtil.mutListener.listen(42588)) {
            super.onDestroyView();
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(42614)) {
            if (this.fragmentView == null) {
                if (!ListenerUtil.mutListener.listen(42589)) {
                    this.fragmentView = inflater.inflate(R.layout.fragment_backup_threema_safe, container, false);
                }
                if (!ListenerUtil.mutListener.listen(42590)) {
                    configLayout = fragmentView.findViewById(R.id.config_layout);
                }
                if (!ListenerUtil.mutListener.listen(42591)) {
                    explainLayout = fragmentView.findViewById(R.id.explain_layout);
                }
                if (!ListenerUtil.mutListener.listen(42592)) {
                    floatingActionButton = fragmentView.findViewById(R.id.floating);
                }
                if (!ListenerUtil.mutListener.listen(42597)) {
                    floatingActionButton.setOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View v) {
                            if (!ListenerUtil.mutListener.listen(42596)) {
                                if (preferenceService.getThreemaSafeEnabled()) {
                                    if (!ListenerUtil.mutListener.listen(42593)) {
                                        threemaSafeService.unscheduleUpload();
                                    }
                                    if (!ListenerUtil.mutListener.listen(42594)) {
                                        threemaSafeService.scheduleUpload();
                                    }
                                    if (!ListenerUtil.mutListener.listen(42595)) {
                                        threemaSafeService.uploadNow(getContext(), true);
                                    }
                                }
                            }
                        }
                    });
                }
                if (!ListenerUtil.mutListener.listen(42598)) {
                    changePasswordButton = fragmentView.findViewById(R.id.threema_safe_change_password);
                }
                if (!ListenerUtil.mutListener.listen(42603)) {
                    changePasswordButton.setOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View v) {
                            if (!ListenerUtil.mutListener.listen(42602)) {
                                if (preferenceService.getThreemaSafeEnabled()) {
                                    if (!ListenerUtil.mutListener.listen(42599)) {
                                        threemaSafeService.unscheduleUpload();
                                    }
                                    Intent intent = new Intent(getActivity(), ThreemaSafeConfigureActivity.class);
                                    if (!ListenerUtil.mutListener.listen(42600)) {
                                        intent.putExtra(EXTRA_CHANGE_PASSWORD, true);
                                    }
                                    if (!ListenerUtil.mutListener.listen(42601)) {
                                        startActivityForResult(intent, REQUEST_CODE_SAFE_CHANGE_PASSWORD);
                                    }
                                }
                            }
                        }
                    });
                }
                if (!ListenerUtil.mutListener.listen(42604)) {
                    enableSwitch = fragmentView.findViewById(R.id.switch_button);
                }
                if (!ListenerUtil.mutListener.listen(42611)) {
                    enableSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                        @Override
                        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                            if (!ListenerUtil.mutListener.listen(42610)) {
                                if (buttonView.isShown()) {
                                    if (!ListenerUtil.mutListener.listen(42605)) {
                                        logger.debug("*** onCheckedChanged buttonView " + buttonView.isChecked() + " isChecked " + isChecked);
                                    }
                                    if (!ListenerUtil.mutListener.listen(42609)) {
                                        if (isChecked) {
                                            if (!ListenerUtil.mutListener.listen(42608)) {
                                                startActivityForResult(new Intent(getActivity(), ThreemaSafeConfigureActivity.class), REQUEST_CODE_SAFE_CONFIGURE);
                                            }
                                        } else {
                                            GenericAlertDialog dialog = GenericAlertDialog.newInstance(R.string.safe_deactivate, R.string.safe_deactivate_explain, R.string.ok, R.string.cancel);
                                            if (!ListenerUtil.mutListener.listen(42606)) {
                                                dialog.setTargetFragment(BackupThreemaSafeFragment.this, 0);
                                            }
                                            if (!ListenerUtil.mutListener.listen(42607)) {
                                                dialog.show(getFragmentManager(), DIALOG_TAG_DEACTIVATE_CONFIRM);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    });
                }
                if (!ListenerUtil.mutListener.listen(42612)) {
                    fragmentView.findViewById(R.id.info).setOnClickListener(v -> onInfoButtonClicked(v));
                }
                if (!ListenerUtil.mutListener.listen(42613)) {
                    updateUI();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(42619)) {
            // adjust height of switch layout on top
            if (this.fragmentView != null) {
                FrameLayout switchLayout = this.fragmentView.findViewById(R.id.switch_frame);
                if (!ListenerUtil.mutListener.listen(42618)) {
                    if (switchLayout != null) {
                        ViewGroup.LayoutParams lp = switchLayout.getLayoutParams();
                        if (!ListenerUtil.mutListener.listen(42615)) {
                            lp.height = getResources().getDimensionPixelSize(R.dimen.web_sessions_switch_frame_height);
                        }
                        if (!ListenerUtil.mutListener.listen(42616)) {
                            switchLayout.setLayoutParams(lp);
                        }
                        if (!ListenerUtil.mutListener.listen(42617)) {
                            ((TextView) switchLayout.findViewById(R.id.switch_text)).setHeight(lp.height);
                        }
                    }
                }
            }
        }
        return fragmentView;
    }

    private String getShortServerName() {
        if (!ListenerUtil.mutListener.listen(42621)) {
            if (getContext() != null) {
                if (!ListenerUtil.mutListener.listen(42620)) {
                    if (preferenceService.getThreemaSafeServerInfo().isDefaultServer()) {
                        return getString(R.string.safe_use_default_server);
                    } else {
                        return preferenceService.getThreemaSafeServerInfo().getHostName();
                    }
                }
            }
        }
        return getString(R.string.error);
    }

    @UiThread
    private void updateUI() {
        if (!ListenerUtil.mutListener.listen(42649)) {
            if (preferenceService.getThreemaSafeEnabled()) {
                if (!ListenerUtil.mutListener.listen(42626)) {
                    // Threema safe is already configured
                    ((TextView) fragmentView.findViewById(R.id.server_text)).setText(getShortServerName());
                }
                if (!ListenerUtil.mutListener.listen(42627)) {
                    ((TextView) fragmentView.findViewById(R.id.server_size)).setText(Formatter.formatFileSize(getActivity(), preferenceService.getThreemaSafeServerMaxUploadSize()));
                }
                if (!ListenerUtil.mutListener.listen(42628)) {
                    ((TextView) fragmentView.findViewById(R.id.server_retention)).setText(String.format(getString(R.string.number_of_days), preferenceService.getThreemaSafeServerRetention()));
                }
                TextView backupResult = fragmentView.findViewById(R.id.backup_result);
                if (!ListenerUtil.mutListener.listen(42644)) {
                    if (preferenceService.getThreemaSafeBackupDate() != null) {
                        if (!ListenerUtil.mutListener.listen(42637)) {
                            ((TextView) fragmentView.findViewById(R.id.backup_date)).setText(LocaleUtil.formatTimeStampString(getActivity(), preferenceService.getThreemaSafeBackupDate().getTime(), true));
                        }
                        if (!ListenerUtil.mutListener.listen(42638)) {
                            ((TextView) fragmentView.findViewById(R.id.backup_size)).setText(Formatter.formatFileSize(getActivity(), preferenceService.getThreemaSafeBackupSize()));
                        }
                        if (!ListenerUtil.mutListener.listen(42639)) {
                            backupResult.setText(getResources().getStringArray(R.array.threema_safe_error)[preferenceService.getThreemaSafeErrorCode()]);
                        }
                        if (!ListenerUtil.mutListener.listen(42642)) {
                            if (preferenceService.getThreemaSafeErrorCode() == ThreemaSafeService.ERROR_CODE_OK) {
                                if (!ListenerUtil.mutListener.listen(42641)) {
                                    backupResult.setTextColor(getResources().getColor(R.color.material_green));
                                }
                            } else {
                                if (!ListenerUtil.mutListener.listen(42640)) {
                                    backupResult.setTextColor(getResources().getColor(R.color.material_red));
                                }
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(42643)) {
                            changePasswordButton.setVisibility(View.VISIBLE);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(42629)) {
                            ((TextView) fragmentView.findViewById(R.id.backup_date)).setText("-");
                        }
                        if (!ListenerUtil.mutListener.listen(42630)) {
                            ((TextView) fragmentView.findViewById(R.id.backup_size)).setText("-");
                        }
                        if (!ListenerUtil.mutListener.listen(42635)) {
                            if (preferenceService.getThreemaSafeErrorCode() != ThreemaSafeService.ERROR_CODE_OK) {
                                if (!ListenerUtil.mutListener.listen(42633)) {
                                    backupResult.setText(getResources().getStringArray(R.array.threema_safe_error)[preferenceService.getThreemaSafeErrorCode()]);
                                }
                                if (!ListenerUtil.mutListener.listen(42634)) {
                                    backupResult.setTextColor(getResources().getColor(R.color.material_red));
                                }
                            } else {
                                if (!ListenerUtil.mutListener.listen(42631)) {
                                    backupResult.setText("-");
                                }
                                if (!ListenerUtil.mutListener.listen(42632)) {
                                    backupResult.setTextColor(ConfigUtils.getPrimaryColor());
                                }
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(42636)) {
                            changePasswordButton.setVisibility(View.INVISIBLE);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(42645)) {
                    configLayout.setVisibility(View.VISIBLE);
                }
                if (!ListenerUtil.mutListener.listen(42646)) {
                    explainLayout.setVisibility(View.GONE);
                }
                if (!ListenerUtil.mutListener.listen(42647)) {
                    enableSwitch.setCheckedSilent(true);
                }
                if (!ListenerUtil.mutListener.listen(42648)) {
                    floatingActionButton.setVisibility(View.VISIBLE);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(42622)) {
                    configLayout.setVisibility(View.GONE);
                }
                if (!ListenerUtil.mutListener.listen(42623)) {
                    explainLayout.setVisibility(View.VISIBLE);
                }
                if (!ListenerUtil.mutListener.listen(42624)) {
                    enableSwitch.setCheckedSilent(false);
                }
                if (!ListenerUtil.mutListener.listen(42625)) {
                    floatingActionButton.setVisibility(View.GONE);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(42652)) {
            if (ConfigUtils.isWorkRestricted()) {
                if (!ListenerUtil.mutListener.listen(42651)) {
                    if (ThreemaSafeMDMConfig.getInstance().isBackupForced()) {
                        if (!ListenerUtil.mutListener.listen(42650)) {
                            enableSwitch.setEnabled(false);
                        }
                    }
                }
            }
        }
    }

    @SuppressLint("StaticFieldLeak")
    private void disableSafe() {
        if (!ListenerUtil.mutListener.listen(42659)) {
            new AsyncTask<Void, Void, String>() {

                @Override
                protected void onPreExecute() {
                    if (!ListenerUtil.mutListener.listen(42653)) {
                        GenericProgressDialog.newInstance(R.string.safe_deleting, R.string.please_wait).show(getFragmentManager(), DIALOG_TAG_DELETING);
                    }
                }

                @Override
                protected String doInBackground(Void... voids) {
                    try {
                        if (!ListenerUtil.mutListener.listen(42654)) {
                            threemaSafeService.deleteBackup();
                        }
                    } catch (ThreemaException e) {
                        return e.getMessage();
                    }
                    return null;
                }

                @Override
                protected void onPostExecute(String string) {
                    String message;
                    if (!ListenerUtil.mutListener.listen(42655)) {
                        DialogUtil.dismissDialog(getFragmentManager(), DIALOG_TAG_DELETING, true);
                    }
                    if (!ListenerUtil.mutListener.listen(42656)) {
                        threemaSafeService.setEnabled(false);
                    }
                    if (string != null) {
                        message = String.format(getString(R.string.safe_delete_error), string);
                    } else {
                        message = getString(R.string.safe_delete_success);
                    }
                    if (!ListenerUtil.mutListener.listen(42657)) {
                        Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
                    }
                    if (!ListenerUtil.mutListener.listen(42658)) {
                        updateUI();
                    }
                }
            }.execute();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (!ListenerUtil.mutListener.listen(42660)) {
            super.onActivityResult(requestCode, resultCode, data);
        }
        if (!ListenerUtil.mutListener.listen(42661)) {
            updateUI();
        }
    }

    @Override
    public void onYes(String tag, Object data) {
        if (!ListenerUtil.mutListener.listen(42662)) {
            disableSafe();
        }
    }

    @Override
    public void onNo(String tag, Object data) {
        if (!ListenerUtil.mutListener.listen(42663)) {
            enableSwitch.setCheckedSilent(preferenceService.getThreemaSafeEnabled());
        }
    }

    @UiThread
    private void onInfoButtonClicked(View v) {
        if (!ListenerUtil.mutListener.listen(42664)) {
            SimpleStringAlertDialog.newInstance(R.string.threema_safe, R.string.safe_enable_explain).show(getFragmentManager(), "tse");
        }
    }
}
