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
import android.app.Activity;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialog;
import androidx.appcompat.widget.SwitchCompat;
import ch.threema.app.R;
import ch.threema.app.ThreemaApplication;
import ch.threema.app.dialogs.GenericProgressDialog;
import ch.threema.app.dialogs.ThreemaDialogFragment;
import ch.threema.app.utils.AnimationUtil;
import ch.threema.app.utils.DialogUtil;
import ch.threema.app.utils.EditTextUtil;
import ch.threema.app.utils.TestUtil;
import ch.threema.base.ThreemaException;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class ThreemaSafeAdvancedDialog extends ThreemaDialogFragment implements View.OnClickListener {

    private static final String ARG_SERVER_URL = "sU";

    private static final String ARG_PLAIN_STYLE = "pS";

    private static final String ARG_SERVER_USERNAME = "Un";

    private static final String ARG_SERVER_PASSWORD = "Sp";

    private static final String DIALOG_TAG_PROGRESS = "pr";

    private WizardDialogCallback callback;

    private Activity activity;

    private ThreemaSafeService threemaSafeService;

    private AlertDialog alertDialog;

    private ThreemaSafeServerInfo serverInfo;

    private Button positiveButton;

    private EditText serverUrlEditText, usernameEditText, passwordEditText;

    private LinearLayout serverContainer;

    private SwitchCompat defaultServerSwitch;

    public static ThreemaSafeAdvancedDialog newInstance(ThreemaSafeServerInfo serverInfo, boolean plainStyle) {
        ThreemaSafeAdvancedDialog dialog = new ThreemaSafeAdvancedDialog();
        Bundle args = new Bundle();
        if (!ListenerUtil.mutListener.listen(42665)) {
            args.putString(ARG_SERVER_URL, serverInfo.getServerName());
        }
        if (!ListenerUtil.mutListener.listen(42666)) {
            args.putString(ARG_SERVER_USERNAME, serverInfo.getServerUsername());
        }
        if (!ListenerUtil.mutListener.listen(42667)) {
            args.putString(ARG_SERVER_PASSWORD, serverInfo.getServerPassword());
        }
        if (!ListenerUtil.mutListener.listen(42668)) {
            args.putBoolean(ARG_PLAIN_STYLE, plainStyle);
        }
        if (!ListenerUtil.mutListener.listen(42669)) {
            dialog.setArguments(args);
        }
        return dialog;
    }

    public interface WizardDialogCallback {

        void onYes(String tag, ThreemaSafeServerInfo serverInfo);

        void onNo(String tag);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(42670)) {
            super.onCreate(savedInstanceState);
        }
        try {
            if (!ListenerUtil.mutListener.listen(42671)) {
                callback = (WizardDialogCallback) getTargetFragment();
            }
        } catch (ClassCastException e) {
        }
        if (!ListenerUtil.mutListener.listen(42674)) {
            // called from an activity rather than a fragment
            if (callback == null) {
                if (!ListenerUtil.mutListener.listen(42672)) {
                    if (!(activity instanceof WizardDialogCallback)) {
                        throw new ClassCastException("Calling fragment must implement WizardDialogCallback interface");
                    }
                }
                if (!ListenerUtil.mutListener.listen(42673)) {
                    callback = (WizardDialogCallback) activity;
                }
            }
        }
    }

    @Override
    public void onAttach(Activity activity) {
        if (!ListenerUtil.mutListener.listen(42675)) {
            super.onAttach(activity);
        }
        if (!ListenerUtil.mutListener.listen(42676)) {
            this.activity = activity;
        }
    }

    @Override
    public AppCompatDialog onCreateDialog(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(42677)) {
            serverInfo = new ThreemaSafeServerInfo();
        }
        if (!ListenerUtil.mutListener.listen(42678)) {
            serverInfo.setServerName(getArguments().getString(ARG_SERVER_URL));
        }
        if (!ListenerUtil.mutListener.listen(42679)) {
            serverInfo.setServerUsername(getArguments().getString(ARG_SERVER_USERNAME));
        }
        if (!ListenerUtil.mutListener.listen(42680)) {
            serverInfo.setServerPassword(getArguments().getString(ARG_SERVER_PASSWORD));
        }
        boolean plainStyle = getArguments().getBoolean(ARG_PLAIN_STYLE);
        final View dialogView = activity.getLayoutInflater().inflate(plainStyle ? R.layout.dialog_safe_advanced : R.layout.dialog_wizard_safe_advanced, null);
        if (!ListenerUtil.mutListener.listen(42681)) {
            positiveButton = dialogView.findViewById(R.id.ok);
        }
        if (!ListenerUtil.mutListener.listen(42682)) {
            serverUrlEditText = dialogView.findViewById(R.id.safe_edit_server);
        }
        if (!ListenerUtil.mutListener.listen(42683)) {
            serverContainer = dialogView.findViewById(R.id.safe_server_container);
        }
        if (!ListenerUtil.mutListener.listen(42684)) {
            usernameEditText = dialogView.findViewById(R.id.safe_edit_username);
        }
        if (!ListenerUtil.mutListener.listen(42685)) {
            passwordEditText = dialogView.findViewById(R.id.safe_edit_server_password);
        }
        if (!ListenerUtil.mutListener.listen(42686)) {
            defaultServerSwitch = dialogView.findViewById(R.id.safe_switch_server);
        }
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(getActivity(), plainStyle ? getTheme() : R.style.Threema_Dialog_Wizard);
        if (!ListenerUtil.mutListener.listen(42687)) {
            builder.setView(dialogView);
        }
        try {
            if (!ListenerUtil.mutListener.listen(42688)) {
                threemaSafeService = ThreemaApplication.getServiceManager().getThreemaSafeService();
            }
        } catch (Exception e) {
        }
        if (!ListenerUtil.mutListener.listen(42689)) {
            defaultServerSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
                updateUI();
            });
        }
        if (!ListenerUtil.mutListener.listen(42692)) {
            if ((ListenerUtil.mutListener.listen(42690) ? (TestUtil.empty(serverInfo.getServerName()) || serverInfo.isDefaultServer()) : (TestUtil.empty(serverInfo.getServerName()) && serverInfo.isDefaultServer()))) {
                if (!ListenerUtil.mutListener.listen(42691)) {
                    serverContainer.setVisibility(View.GONE);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(42693)) {
            serverUrlEditText.setText(serverInfo.isDefaultServer() ? "" : serverInfo.getServerName());
        }
        if (!ListenerUtil.mutListener.listen(42694)) {
            usernameEditText.setText(serverInfo.getServerUsername());
        }
        if (!ListenerUtil.mutListener.listen(42695)) {
            passwordEditText.setText(serverInfo.getServerPassword());
        }
        if (!ListenerUtil.mutListener.listen(42696)) {
            defaultServerSwitch.setChecked(serverInfo.isDefaultServer());
        }
        if (!ListenerUtil.mutListener.listen(42697)) {
            updateUI();
        }
        if (!ListenerUtil.mutListener.listen(42699)) {
            serverUrlEditText.addTextChangedListener(new TextWatcher() {

                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    if (!ListenerUtil.mutListener.listen(42698)) {
                        updateButtons();
                    }
                }

                @Override
                public void afterTextChanged(Editable s) {
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(42707)) {
            if (plainStyle) {
                if (!ListenerUtil.mutListener.listen(42704)) {
                    builder.setTitle(R.string.safe_configure_choose_server);
                }
                if (!ListenerUtil.mutListener.listen(42705)) {
                    builder.setPositiveButton(getString(R.string.ok), null);
                }
                if (!ListenerUtil.mutListener.listen(42706)) {
                    builder.setNegativeButton(getString(R.string.cancel), null);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(42700)) {
                    positiveButton.setOnClickListener(this);
                }
                if (!ListenerUtil.mutListener.listen(42701)) {
                    updateButtons();
                }
                if (!ListenerUtil.mutListener.listen(42702)) {
                    dialogView.findViewById(R.id.cancel).setOnClickListener(v -> onCancel(null));
                }
                if (!ListenerUtil.mutListener.listen(42703)) {
                    updateButtons();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(42708)) {
            setCancelable(false);
        }
        if (!ListenerUtil.mutListener.listen(42709)) {
            alertDialog = builder.create();
        }
        return alertDialog;
    }

    @Override
    public void onStart() {
        if (!ListenerUtil.mutListener.listen(42710)) {
            super.onStart();
        }
        Button button = alertDialog.getButton(AlertDialog.BUTTON_POSITIVE);
        if (!ListenerUtil.mutListener.listen(42715)) {
            if (button != null) {
                if (!ListenerUtil.mutListener.listen(42711)) {
                    this.positiveButton = button;
                }
                if (!ListenerUtil.mutListener.listen(42712)) {
                    this.positiveButton.setOnClickListener(this);
                }
                if (!ListenerUtil.mutListener.listen(42713)) {
                    alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setOnClickListener(v -> onCancel(null));
                }
                if (!ListenerUtil.mutListener.listen(42714)) {
                    updateButtons();
                }
            }
        }
    }

    private void updateUI() {
        if (!ListenerUtil.mutListener.listen(42716)) {
            updateButtons();
        }
        if (!ListenerUtil.mutListener.listen(42721)) {
            if (defaultServerSwitch.isChecked()) {
                if (!ListenerUtil.mutListener.listen(42720)) {
                    if (serverContainer.getVisibility() == View.VISIBLE) {
                        if (!ListenerUtil.mutListener.listen(42719)) {
                            AnimationUtil.fadeViewVisibility(serverContainer, View.GONE);
                        }
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(42718)) {
                    if (serverContainer.getVisibility() != View.VISIBLE) {
                        if (!ListenerUtil.mutListener.listen(42717)) {
                            AnimationUtil.fadeViewVisibility(serverContainer, View.VISIBLE);
                        }
                    }
                }
            }
        }
    }

    private void updateButtons() {
        if (!ListenerUtil.mutListener.listen(42733)) {
            if ((ListenerUtil.mutListener.listen(42723) ? ((ListenerUtil.mutListener.listen(42722) ? (positiveButton != null || serverUrlEditText != null) : (positiveButton != null && serverUrlEditText != null)) || defaultServerSwitch != null) : ((ListenerUtil.mutListener.listen(42722) ? (positiveButton != null || serverUrlEditText != null) : (positiveButton != null && serverUrlEditText != null)) && defaultServerSwitch != null))) {
                if (!ListenerUtil.mutListener.listen(42732)) {
                    if (defaultServerSwitch.isChecked()) {
                        if (!ListenerUtil.mutListener.listen(42731)) {
                            positiveButton.setEnabled(true);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(42730)) {
                            positiveButton.setEnabled((ListenerUtil.mutListener.listen(42729) ? (serverUrlEditText.getText() != null || (ListenerUtil.mutListener.listen(42728) ? (serverUrlEditText.getText().length() <= 9) : (ListenerUtil.mutListener.listen(42727) ? (serverUrlEditText.getText().length() > 9) : (ListenerUtil.mutListener.listen(42726) ? (serverUrlEditText.getText().length() < 9) : (ListenerUtil.mutListener.listen(42725) ? (serverUrlEditText.getText().length() != 9) : (ListenerUtil.mutListener.listen(42724) ? (serverUrlEditText.getText().length() == 9) : (serverUrlEditText.getText().length() >= 9))))))) : (serverUrlEditText.getText() != null && (ListenerUtil.mutListener.listen(42728) ? (serverUrlEditText.getText().length() <= 9) : (ListenerUtil.mutListener.listen(42727) ? (serverUrlEditText.getText().length() > 9) : (ListenerUtil.mutListener.listen(42726) ? (serverUrlEditText.getText().length() < 9) : (ListenerUtil.mutListener.listen(42725) ? (serverUrlEditText.getText().length() != 9) : (ListenerUtil.mutListener.listen(42724) ? (serverUrlEditText.getText().length() == 9) : (serverUrlEditText.getText().length() >= 9)))))))));
                        }
                    }
                }
            }
        }
    }

    @SuppressLint("StaticFieldLeak")
    private void testServer() {
        if (!ListenerUtil.mutListener.listen(42741)) {
            new AsyncTask<Void, Void, String>() {

                @Override
                protected void onPreExecute() {
                    if (!ListenerUtil.mutListener.listen(42734)) {
                        GenericProgressDialog.newInstance(R.string.safe_test_server, R.string.please_wait).show(getFragmentManager(), DIALOG_TAG_PROGRESS);
                    }
                }

                @Override
                protected String doInBackground(Void... voids) {
                    try {
                        if (!ListenerUtil.mutListener.listen(42735)) {
                            threemaSafeService.testServer(serverInfo);
                        }
                        return null;
                    } catch (ThreemaException e) {
                        return e.getMessage();
                    }
                }

                @Override
                protected void onPostExecute(String failureMessage) {
                    if (!ListenerUtil.mutListener.listen(42736)) {
                        DialogUtil.dismissDialog(getFragmentManager(), DIALOG_TAG_PROGRESS, true);
                    }
                    if (!ListenerUtil.mutListener.listen(42739)) {
                        if (failureMessage != null) {
                            if (!ListenerUtil.mutListener.listen(42738)) {
                                Toast.makeText(getActivity(), getString(R.string.test_unsuccessful) + ": " + failureMessage, Toast.LENGTH_LONG).show();
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(42737)) {
                                onYes();
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(42740)) {
                        updateUI();
                    }
                }
            }.execute();
        }
    }

    private void onYes() {
        if (!ListenerUtil.mutListener.listen(42742)) {
            dismiss();
        }
        if (!ListenerUtil.mutListener.listen(42743)) {
            callback.onYes(getTag(), serverInfo);
        }
    }

    @Override
    public void onCancel(DialogInterface dialogInterface) {
        if (!ListenerUtil.mutListener.listen(42744)) {
            dismiss();
        }
        if (!ListenerUtil.mutListener.listen(42745)) {
            callback.onNo(this.getTag());
        }
    }

    @Override
    public void onClick(View v) {
        if (!ListenerUtil.mutListener.listen(42753)) {
            if (!defaultServerSwitch.isChecked()) {
                if (!ListenerUtil.mutListener.listen(42748)) {
                    EditTextUtil.hideSoftKeyboard(serverUrlEditText);
                }
                if (!ListenerUtil.mutListener.listen(42749)) {
                    serverInfo.setServerName(serverUrlEditText.getText().toString());
                }
                if (!ListenerUtil.mutListener.listen(42750)) {
                    serverInfo.setServerUsername(usernameEditText.getText().toString());
                }
                if (!ListenerUtil.mutListener.listen(42751)) {
                    serverInfo.setServerPassword(passwordEditText.getText().toString());
                }
                if (!ListenerUtil.mutListener.listen(42752)) {
                    testServer();
                }
            } else {
                if (!ListenerUtil.mutListener.listen(42746)) {
                    serverInfo = new ThreemaSafeServerInfo();
                }
                if (!ListenerUtil.mutListener.listen(42747)) {
                    onYes();
                }
            }
        }
    }
}
