/**
 * ************************************************************************************
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
package com.ichi2.anki;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.afollestad.materialdialogs.MaterialDialog;
import com.google.android.material.textfield.TextInputLayout;
import com.ichi2.anki.web.HostNumFactory;
import com.ichi2.async.Connection;
import com.ichi2.async.Connection.Payload;
import com.ichi2.themes.StyledProgressDialog;
import com.ichi2.ui.TextInputEditField;
import com.ichi2.utils.AdaptionUtil;
import java.net.UnknownHostException;
import timber.log.Timber;
import static com.ichi2.anim.ActivityTransitionAnimation.Direction.FADE;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class MyAccount extends AnkiActivity {

    private static final int STATE_LOG_IN = 1;

    private static final int STATE_LOGGED_IN = 2;

    private View mLoginToMyAccountView;

    private View mLoggedIntoMyAccountView;

    private EditText mUsername;

    private TextInputEditField mPassword;

    private TextView mUsernameLoggedIn;

    private MaterialDialog mProgressDialog;

    Toolbar mToolbar = null;

    private TextInputLayout mPasswordLayout;

    private void switchToState(int newState) {
        if (!ListenerUtil.mutListener.listen(9134)) {
            switch(newState) {
                case STATE_LOGGED_IN:
                    String username = AnkiDroidApp.getSharedPrefs(getBaseContext()).getString("username", "");
                    if (!ListenerUtil.mutListener.listen(9123)) {
                        mUsernameLoggedIn.setText(username);
                    }
                    if (!ListenerUtil.mutListener.listen(9124)) {
                        mToolbar = mLoggedIntoMyAccountView.findViewById(R.id.toolbar);
                    }
                    if (!ListenerUtil.mutListener.listen(9127)) {
                        if (mToolbar != null) {
                            if (!ListenerUtil.mutListener.listen(9125)) {
                                // This can be cleaned up if all three main layouts are guaranteed to share the same toolbar object
                                mToolbar.setTitle(getString(R.string.sync_account));
                            }
                            if (!ListenerUtil.mutListener.listen(9126)) {
                                setSupportActionBar(mToolbar);
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(9128)) {
                        setContentView(mLoggedIntoMyAccountView);
                    }
                    break;
                case STATE_LOG_IN:
                    if (!ListenerUtil.mutListener.listen(9129)) {
                        mToolbar = mLoginToMyAccountView.findViewById(R.id.toolbar);
                    }
                    if (!ListenerUtil.mutListener.listen(9132)) {
                        if (mToolbar != null) {
                            if (!ListenerUtil.mutListener.listen(9130)) {
                                // This can be cleaned up if all three main layouts are guaranteed to share the same toolbar object
                                mToolbar.setTitle(getString(R.string.sync_account));
                            }
                            if (!ListenerUtil.mutListener.listen(9131)) {
                                setSupportActionBar(mToolbar);
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(9133)) {
                        setContentView(mLoginToMyAccountView);
                    }
                    break;
            }
        }
        if (!ListenerUtil.mutListener.listen(9135)) {
            // Needed?
            supportInvalidateOptionsMenu();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(9136)) {
            if (showedActivityFailedScreen(savedInstanceState)) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(9137)) {
            super.onCreate(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(9139)) {
            if (AdaptionUtil.isUserATestClient()) {
                if (!ListenerUtil.mutListener.listen(9138)) {
                    finishWithoutAnimation();
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(9140)) {
            mayOpenUrl(Uri.parse(getResources().getString(R.string.register_url)));
        }
        if (!ListenerUtil.mutListener.listen(9141)) {
            initAllContentViews();
        }
        SharedPreferences preferences = AnkiDroidApp.getSharedPrefs(getBaseContext());
        if (!ListenerUtil.mutListener.listen(9149)) {
            if ((ListenerUtil.mutListener.listen(9146) ? (preferences.getString("hkey", "").length() >= 0) : (ListenerUtil.mutListener.listen(9145) ? (preferences.getString("hkey", "").length() <= 0) : (ListenerUtil.mutListener.listen(9144) ? (preferences.getString("hkey", "").length() < 0) : (ListenerUtil.mutListener.listen(9143) ? (preferences.getString("hkey", "").length() != 0) : (ListenerUtil.mutListener.listen(9142) ? (preferences.getString("hkey", "").length() == 0) : (preferences.getString("hkey", "").length() > 0))))))) {
                if (!ListenerUtil.mutListener.listen(9148)) {
                    switchToState(STATE_LOGGED_IN);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(9147)) {
                    switchToState(STATE_LOG_IN);
                }
            }
        }
    }

    public void attemptLogin() {
        // trim spaces, issue 1586
        String username = mUsername.getText().toString().trim();
        String password = mPassword.getText().toString();
        if (!ListenerUtil.mutListener.listen(9154)) {
            if ((ListenerUtil.mutListener.listen(9150) ? (!"".equalsIgnoreCase(username) || !"".equalsIgnoreCase(password)) : (!"".equalsIgnoreCase(username) && !"".equalsIgnoreCase(password)))) {
                if (!ListenerUtil.mutListener.listen(9152)) {
                    Timber.i("Attempting auto-login");
                }
                if (!ListenerUtil.mutListener.listen(9153)) {
                    Connection.login(loginListener, new Connection.Payload(new Object[] { username, password, HostNumFactory.getInstance(this) }));
                }
            } else {
                if (!ListenerUtil.mutListener.listen(9151)) {
                    Timber.i("Auto-login cancelled - username/password missing");
                }
            }
        }
    }

    private void saveUserInformation(String username, String hkey) {
        SharedPreferences preferences = AnkiDroidApp.getSharedPrefs(getBaseContext());
        Editor editor = preferences.edit();
        if (!ListenerUtil.mutListener.listen(9155)) {
            editor.putString("username", username);
        }
        if (!ListenerUtil.mutListener.listen(9156)) {
            editor.putString("hkey", hkey);
        }
        if (!ListenerUtil.mutListener.listen(9157)) {
            editor.apply();
        }
    }

    private void login() {
        // Hide soft keyboard
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (!ListenerUtil.mutListener.listen(9158)) {
            inputMethodManager.hideSoftInputFromWindow(mUsername.getWindowToken(), 0);
        }
        // trim spaces, issue 1586
        String username = mUsername.getText().toString().trim();
        String password = mPassword.getText().toString();
        if (!ListenerUtil.mutListener.listen(9162)) {
            if ((ListenerUtil.mutListener.listen(9159) ? (!"".equalsIgnoreCase(username) || !"".equalsIgnoreCase(password)) : (!"".equalsIgnoreCase(username) && !"".equalsIgnoreCase(password)))) {
                if (!ListenerUtil.mutListener.listen(9161)) {
                    Connection.login(loginListener, new Connection.Payload(new Object[] { username, password, HostNumFactory.getInstance(this) }));
                }
            } else {
                if (!ListenerUtil.mutListener.listen(9160)) {
                    UIUtils.showSimpleSnackbar(this, R.string.invalid_username_password, true);
                }
            }
        }
    }

    private void logout() {
        SharedPreferences preferences = AnkiDroidApp.getSharedPrefs(getBaseContext());
        Editor editor = preferences.edit();
        if (!ListenerUtil.mutListener.listen(9163)) {
            editor.putString("username", "");
        }
        if (!ListenerUtil.mutListener.listen(9164)) {
            editor.putString("hkey", "");
        }
        if (!ListenerUtil.mutListener.listen(9165)) {
            editor.apply();
        }
        if (!ListenerUtil.mutListener.listen(9166)) {
            HostNumFactory.getInstance(this).reset();
        }
        if (!ListenerUtil.mutListener.listen(9167)) {
            // force media resync on deauth
            getCol().getMedia().forceResync();
        }
        if (!ListenerUtil.mutListener.listen(9168)) {
            switchToState(STATE_LOG_IN);
        }
    }

    private void resetPassword() {
        if (!ListenerUtil.mutListener.listen(9169)) {
            super.openUrl(Uri.parse(getResources().getString(R.string.resetpw_url)));
        }
    }

    private void initAllContentViews() {
        if (!ListenerUtil.mutListener.listen(9170)) {
            mLoginToMyAccountView = getLayoutInflater().inflate(R.layout.my_account, null);
        }
        if (!ListenerUtil.mutListener.listen(9171)) {
            mUsername = mLoginToMyAccountView.findViewById(R.id.username);
        }
        if (!ListenerUtil.mutListener.listen(9172)) {
            mPassword = mLoginToMyAccountView.findViewById(R.id.password);
        }
        if (!ListenerUtil.mutListener.listen(9173)) {
            mPasswordLayout = mLoginToMyAccountView.findViewById(R.id.password_layout);
        }
        if (!ListenerUtil.mutListener.listen(9174)) {
            mPassword.setOnKeyListener((v, keyCode, event) -> {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    switch(keyCode) {
                        case KeyEvent.KEYCODE_DPAD_CENTER:
                        case KeyEvent.KEYCODE_ENTER:
                        case KeyEvent.KEYCODE_NUMPAD_ENTER:
                            login();
                            return true;
                        default:
                            break;
                    }
                }
                return false;
            });
        }
        Button loginButton = mLoginToMyAccountView.findViewById(R.id.login_button);
        if (!ListenerUtil.mutListener.listen(9175)) {
            loginButton.setOnClickListener(v -> login());
        }
        Button resetPWButton = mLoginToMyAccountView.findViewById(R.id.reset_password_button);
        if (!ListenerUtil.mutListener.listen(9176)) {
            resetPWButton.setOnClickListener(v -> resetPassword());
        }
        Button signUpButton = mLoginToMyAccountView.findViewById(R.id.sign_up_button);
        Uri url = Uri.parse(getResources().getString(R.string.register_url));
        if (!ListenerUtil.mutListener.listen(9177)) {
            signUpButton.setOnClickListener(v -> openUrl(url));
        }
        if (!ListenerUtil.mutListener.listen(9178)) {
            mLoggedIntoMyAccountView = getLayoutInflater().inflate(R.layout.my_account_logged_in, null);
        }
        if (!ListenerUtil.mutListener.listen(9179)) {
            mUsernameLoggedIn = mLoggedIntoMyAccountView.findViewById(R.id.username_logged_in);
        }
        Button logoutButton = mLoggedIntoMyAccountView.findViewById(R.id.logout_button);
        if (!ListenerUtil.mutListener.listen(9180)) {
            logoutButton.setOnClickListener(v -> logout());
        }
        if (!ListenerUtil.mutListener.listen(9187)) {
            if ((ListenerUtil.mutListener.listen(9185) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.O) : (ListenerUtil.mutListener.listen(9184) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.O) : (ListenerUtil.mutListener.listen(9183) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) : (ListenerUtil.mutListener.listen(9182) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.O) : (ListenerUtil.mutListener.listen(9181) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.O) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O))))))) {
                if (!ListenerUtil.mutListener.listen(9186)) {
                    mPassword.setAutoFillListener((value) -> {
                        // disable "show password".
                        mPasswordLayout.setEndIconVisible(false);
                        Timber.i("Attempting login from autofill");
                        attemptLogin();
                    });
                }
            }
        }
    }

    /**
     * Listeners
     */
    final Connection.TaskListener loginListener = new Connection.TaskListener() {

        @Override
        public void onProgressUpdate(Object... values) {
        }

        @Override
        public void onPreExecute() {
            if (!ListenerUtil.mutListener.listen(9188)) {
                Timber.d("loginListener.onPreExecute()");
            }
            if (!ListenerUtil.mutListener.listen(9191)) {
                if ((ListenerUtil.mutListener.listen(9189) ? (mProgressDialog == null && !mProgressDialog.isShowing()) : (mProgressDialog == null || !mProgressDialog.isShowing()))) {
                    if (!ListenerUtil.mutListener.listen(9190)) {
                        mProgressDialog = StyledProgressDialog.show(MyAccount.this, "", getResources().getString(R.string.alert_logging_message), false);
                    }
                }
            }
        }

        @Override
        public void onPostExecute(Payload data) {
            if (!ListenerUtil.mutListener.listen(9193)) {
                if (mProgressDialog != null) {
                    if (!ListenerUtil.mutListener.listen(9192)) {
                        mProgressDialog.dismiss();
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(9215)) {
                if (data.success) {
                    if (!ListenerUtil.mutListener.listen(9207)) {
                        Timber.i("User successfully logged in!");
                    }
                    if (!ListenerUtil.mutListener.listen(9208)) {
                        saveUserInformation((String) data.data[0], (String) data.data[1]);
                    }
                    Intent i = MyAccount.this.getIntent();
                    if (!ListenerUtil.mutListener.listen(9214)) {
                        if ((ListenerUtil.mutListener.listen(9209) ? (i.hasExtra("notLoggedIn") || i.getExtras().getBoolean("notLoggedIn", false)) : (i.hasExtra("notLoggedIn") && i.getExtras().getBoolean("notLoggedIn", false)))) {
                            if (!ListenerUtil.mutListener.listen(9212)) {
                                MyAccount.this.setResult(RESULT_OK, i);
                            }
                            if (!ListenerUtil.mutListener.listen(9213)) {
                                finishWithAnimation(FADE);
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(9210)) {
                                // Show logged view
                                mUsernameLoggedIn.setText((String) data.data[0]);
                            }
                            if (!ListenerUtil.mutListener.listen(9211)) {
                                switchToState(STATE_LOGGED_IN);
                            }
                        }
                    }
                } else {
                    if (!ListenerUtil.mutListener.listen(9194)) {
                        Timber.e("Login failed, error code %d", data.returnType);
                    }
                    if (!ListenerUtil.mutListener.listen(9206)) {
                        if (data.returnType == 403) {
                            if (!ListenerUtil.mutListener.listen(9205)) {
                                UIUtils.showSimpleSnackbar(MyAccount.this, R.string.invalid_username_password, true);
                            }
                        } else {
                            String message = getResources().getString(R.string.connection_error_message);
                            Object[] result = data.result;
                            if (!ListenerUtil.mutListener.listen(9204)) {
                                if ((ListenerUtil.mutListener.listen(9201) ? ((ListenerUtil.mutListener.listen(9200) ? (result != null || (ListenerUtil.mutListener.listen(9199) ? (result.length >= 0) : (ListenerUtil.mutListener.listen(9198) ? (result.length <= 0) : (ListenerUtil.mutListener.listen(9197) ? (result.length < 0) : (ListenerUtil.mutListener.listen(9196) ? (result.length != 0) : (ListenerUtil.mutListener.listen(9195) ? (result.length == 0) : (result.length > 0))))))) : (result != null && (ListenerUtil.mutListener.listen(9199) ? (result.length >= 0) : (ListenerUtil.mutListener.listen(9198) ? (result.length <= 0) : (ListenerUtil.mutListener.listen(9197) ? (result.length < 0) : (ListenerUtil.mutListener.listen(9196) ? (result.length != 0) : (ListenerUtil.mutListener.listen(9195) ? (result.length == 0) : (result.length > 0)))))))) || result[0] instanceof Exception) : ((ListenerUtil.mutListener.listen(9200) ? (result != null || (ListenerUtil.mutListener.listen(9199) ? (result.length >= 0) : (ListenerUtil.mutListener.listen(9198) ? (result.length <= 0) : (ListenerUtil.mutListener.listen(9197) ? (result.length < 0) : (ListenerUtil.mutListener.listen(9196) ? (result.length != 0) : (ListenerUtil.mutListener.listen(9195) ? (result.length == 0) : (result.length > 0))))))) : (result != null && (ListenerUtil.mutListener.listen(9199) ? (result.length >= 0) : (ListenerUtil.mutListener.listen(9198) ? (result.length <= 0) : (ListenerUtil.mutListener.listen(9197) ? (result.length < 0) : (ListenerUtil.mutListener.listen(9196) ? (result.length != 0) : (ListenerUtil.mutListener.listen(9195) ? (result.length == 0) : (result.length > 0)))))))) && result[0] instanceof Exception))) {
                                    if (!ListenerUtil.mutListener.listen(9203)) {
                                        showSimpleMessageDialog(message, getHumanReadableLoginErrorMessage((Exception) result[0]), false);
                                    }
                                } else {
                                    if (!ListenerUtil.mutListener.listen(9202)) {
                                        UIUtils.showSimpleSnackbar(MyAccount.this, message, false);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        @Override
        public void onDisconnected() {
            if (!ListenerUtil.mutListener.listen(9216)) {
                UIUtils.showSimpleSnackbar(MyAccount.this, R.string.youre_offline, true);
            }
        }
    };

    protected String getHumanReadableLoginErrorMessage(Exception exception) {
        if (!ListenerUtil.mutListener.listen(9217)) {
            if (exception == null) {
                return "";
            }
        }
        if (!ListenerUtil.mutListener.listen(9219)) {
            if (exception.getCause() != null) {
                Throwable cause = exception.getCause();
                if (!ListenerUtil.mutListener.listen(9218)) {
                    if (cause instanceof UnknownHostException) {
                        return getString(R.string.sync_error_unknown_host_readable, exception.getLocalizedMessage());
                    }
                }
            }
        }
        return exception.getLocalizedMessage();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (!ListenerUtil.mutListener.listen(9223)) {
            if ((ListenerUtil.mutListener.listen(9220) ? (keyCode == KeyEvent.KEYCODE_BACK || event.getRepeatCount() == 0) : (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0))) {
                if (!ListenerUtil.mutListener.listen(9221)) {
                    Timber.i("MyAccount - onBackPressed()");
                }
                if (!ListenerUtil.mutListener.listen(9222)) {
                    finishWithAnimation(FADE);
                }
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}
