package fr.free.nrw.commons.auth;

import android.accounts.AccountAuthenticatorActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import androidx.annotation.ColorRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.app.NavUtils;
import androidx.core.content.ContextCompat;
import fr.free.nrw.commons.auth.login.LoginClient;
import fr.free.nrw.commons.auth.login.LoginResult;
import fr.free.nrw.commons.databinding.ActivityLoginBinding;
import fr.free.nrw.commons.utils.ActivityUtils;
import java.util.Locale;
import org.wikipedia.dataclient.mwapi.MwQueryResponse;
import fr.free.nrw.commons.auth.login.LoginCallback;
import javax.inject.Inject;
import javax.inject.Named;
import fr.free.nrw.commons.BuildConfig;
import fr.free.nrw.commons.R;
import fr.free.nrw.commons.Utils;
import fr.free.nrw.commons.contributions.MainActivity;
import fr.free.nrw.commons.di.ApplicationlessInjection;
import fr.free.nrw.commons.kvstore.JsonKvStore;
import fr.free.nrw.commons.utils.ConfigUtils;
import fr.free.nrw.commons.utils.SystemThemeUtils;
import fr.free.nrw.commons.utils.ViewUtil;
import io.reactivex.disposables.CompositeDisposable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;
import static android.view.KeyEvent.KEYCODE_ENTER;
import static android.view.View.VISIBLE;
import static android.view.inputmethod.EditorInfo.IME_ACTION_DONE;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class LoginActivity extends AccountAuthenticatorActivity {

    @Inject
    SessionManager sessionManager;

    @Inject
    @Named("default_preferences")
    JsonKvStore applicationKvStore;

    @Inject
    LoginClient loginClient;

    @Inject
    SystemThemeUtils systemThemeUtils;

    private ActivityLoginBinding binding;

    ProgressDialog progressDialog;

    private AppCompatDelegate delegate;

    private LoginTextWatcher textWatcher = new LoginTextWatcher();

    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    private Call<MwQueryResponse> loginToken;

    final String saveProgressDailog = "ProgressDailog_state";

    final String saveErrorMessage = "errorMessage";

    final String saveUsername = "username";

    final String savePassword = "password";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(1291)) {
            super.onCreate(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(1292)) {
            ApplicationlessInjection.getInstance(this.getApplicationContext()).getCommonsApplicationComponent().inject(this);
        }
        boolean isDarkTheme = systemThemeUtils.isDeviceInNightMode();
        if (!ListenerUtil.mutListener.listen(1293)) {
            setTheme(isDarkTheme ? R.style.DarkAppTheme : R.style.LightAppTheme);
        }
        if (!ListenerUtil.mutListener.listen(1294)) {
            getDelegate().installViewFactory();
        }
        if (!ListenerUtil.mutListener.listen(1295)) {
            getDelegate().onCreate(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(1296)) {
            binding = ActivityLoginBinding.inflate(getLayoutInflater());
        }
        if (!ListenerUtil.mutListener.listen(1297)) {
            setContentView(binding.getRoot());
        }
        if (!ListenerUtil.mutListener.listen(1298)) {
            binding.loginUsername.addTextChangedListener(textWatcher);
        }
        if (!ListenerUtil.mutListener.listen(1299)) {
            binding.loginPassword.addTextChangedListener(textWatcher);
        }
        if (!ListenerUtil.mutListener.listen(1300)) {
            binding.loginTwoFactor.addTextChangedListener(textWatcher);
        }
        if (!ListenerUtil.mutListener.listen(1301)) {
            binding.skipLogin.setOnClickListener(view -> skipLogin());
        }
        if (!ListenerUtil.mutListener.listen(1302)) {
            binding.forgotPassword.setOnClickListener(view -> forgotPassword());
        }
        if (!ListenerUtil.mutListener.listen(1303)) {
            binding.aboutPrivacyPolicy.setOnClickListener(view -> onPrivacyPolicyClicked());
        }
        if (!ListenerUtil.mutListener.listen(1304)) {
            binding.signUpButton.setOnClickListener(view -> signUp());
        }
        if (!ListenerUtil.mutListener.listen(1305)) {
            binding.loginButton.setOnClickListener(view -> performLogin());
        }
        if (!ListenerUtil.mutListener.listen(1306)) {
            binding.loginPassword.setOnEditorActionListener(this::onEditorAction);
        }
        if (!ListenerUtil.mutListener.listen(1307)) {
            binding.loginPassword.setOnFocusChangeListener(this::onPasswordFocusChanged);
        }
        if (!ListenerUtil.mutListener.listen(1310)) {
            if (ConfigUtils.isBetaFlavour()) {
                if (!ListenerUtil.mutListener.listen(1309)) {
                    binding.loginCredentials.setText(getString(R.string.login_credential));
                }
            } else {
                if (!ListenerUtil.mutListener.listen(1308)) {
                    binding.loginCredentials.setVisibility(View.GONE);
                }
            }
        }
    }

    /**
     * Hides the keyboard if the user's focus is not on the password (hasFocus is false).
     * @param view The keyboard
     * @param hasFocus Set to true if the keyboard has focus
     */
    void onPasswordFocusChanged(View view, boolean hasFocus) {
        if (!ListenerUtil.mutListener.listen(1312)) {
            if (!hasFocus) {
                if (!ListenerUtil.mutListener.listen(1311)) {
                    ViewUtil.hideKeyboard(view);
                }
            }
        }
    }

    boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
        if (!ListenerUtil.mutListener.listen(1317)) {
            if (binding.loginButton.isEnabled()) {
                if (!ListenerUtil.mutListener.listen(1316)) {
                    if (actionId == IME_ACTION_DONE) {
                        if (!ListenerUtil.mutListener.listen(1315)) {
                            performLogin();
                        }
                        return true;
                    } else if ((ListenerUtil.mutListener.listen(1313) ? ((keyEvent != null) || keyEvent.getKeyCode() == KEYCODE_ENTER) : ((keyEvent != null) && keyEvent.getKeyCode() == KEYCODE_ENTER))) {
                        if (!ListenerUtil.mutListener.listen(1314)) {
                            performLogin();
                        }
                        return true;
                    }
                }
            }
        }
        return false;
    }

    protected void skipLogin() {
        if (!ListenerUtil.mutListener.listen(1318)) {
            new AlertDialog.Builder(this).setTitle(R.string.skip_login_title).setMessage(R.string.skip_login_message).setCancelable(false).setPositiveButton(R.string.yes, (dialog, which) -> {
                dialog.cancel();
                performSkipLogin();
            }).setNegativeButton(R.string.no, (dialog, which) -> dialog.cancel()).show();
        }
    }

    protected void forgotPassword() {
        if (!ListenerUtil.mutListener.listen(1319)) {
            Utils.handleWebUrl(this, Uri.parse(BuildConfig.FORGOT_PASSWORD_URL));
        }
    }

    protected void onPrivacyPolicyClicked() {
        if (!ListenerUtil.mutListener.listen(1320)) {
            Utils.handleWebUrl(this, Uri.parse(BuildConfig.PRIVACY_POLICY_URL));
        }
    }

    protected void signUp() {
        Intent intent = new Intent(this, SignupActivity.class);
        if (!ListenerUtil.mutListener.listen(1321)) {
            startActivity(intent);
        }
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(1322)) {
            super.onPostCreate(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(1323)) {
            getDelegate().onPostCreate(savedInstanceState);
        }
    }

    @Override
    protected void onResume() {
        if (!ListenerUtil.mutListener.listen(1324)) {
            super.onResume();
        }
        if (!ListenerUtil.mutListener.listen(1328)) {
            if ((ListenerUtil.mutListener.listen(1325) ? (sessionManager.getCurrentAccount() != null || sessionManager.isUserLoggedIn()) : (sessionManager.getCurrentAccount() != null && sessionManager.isUserLoggedIn()))) {
                if (!ListenerUtil.mutListener.listen(1326)) {
                    applicationKvStore.putBoolean("login_skipped", false);
                }
                if (!ListenerUtil.mutListener.listen(1327)) {
                    startMainActivity();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(1330)) {
            if (applicationKvStore.getBoolean("login_skipped", false)) {
                if (!ListenerUtil.mutListener.listen(1329)) {
                    performSkipLogin();
                }
            }
        }
    }

    @Override
    protected void onDestroy() {
        if (!ListenerUtil.mutListener.listen(1331)) {
            compositeDisposable.clear();
        }
        try {
            if (!ListenerUtil.mutListener.listen(1335)) {
                // To prevent leaked window when finish() is called, see http://stackoverflow.com/questions/32065854/activity-has-leaked-window-at-alertdialog-show-method
                if ((ListenerUtil.mutListener.listen(1333) ? (progressDialog != null || progressDialog.isShowing()) : (progressDialog != null && progressDialog.isShowing()))) {
                    if (!ListenerUtil.mutListener.listen(1334)) {
                        progressDialog.dismiss();
                    }
                }
            }
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(1332)) {
                e.printStackTrace();
            }
        }
        if (!ListenerUtil.mutListener.listen(1336)) {
            binding.loginUsername.removeTextChangedListener(textWatcher);
        }
        if (!ListenerUtil.mutListener.listen(1337)) {
            binding.loginPassword.removeTextChangedListener(textWatcher);
        }
        if (!ListenerUtil.mutListener.listen(1338)) {
            binding.loginTwoFactor.removeTextChangedListener(textWatcher);
        }
        if (!ListenerUtil.mutListener.listen(1339)) {
            delegate.onDestroy();
        }
        if (!ListenerUtil.mutListener.listen(1341)) {
            if (null != loginClient) {
                if (!ListenerUtil.mutListener.listen(1340)) {
                    loginClient.cancel();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(1342)) {
            binding = null;
        }
        if (!ListenerUtil.mutListener.listen(1343)) {
            super.onDestroy();
        }
    }

    public void performLogin() {
        if (!ListenerUtil.mutListener.listen(1344)) {
            Timber.d("Login to start!");
        }
        final String username = binding.loginUsername.getText().toString();
        final String rawUsername = binding.loginUsername.getText().toString().trim();
        final String password = binding.loginPassword.getText().toString();
        String twoFactorCode = binding.loginTwoFactor.getText().toString();
        if (!ListenerUtil.mutListener.listen(1345)) {
            showLoggingProgressBar();
        }
        if (!ListenerUtil.mutListener.listen(1346)) {
            doLogin(username, password, twoFactorCode);
        }
    }

    private void doLogin(String username, String password, String twoFactorCode) {
        if (!ListenerUtil.mutListener.listen(1347)) {
            progressDialog.show();
        }
        if (!ListenerUtil.mutListener.listen(1348)) {
            loginToken = loginClient.getLoginToken();
        }
        if (!ListenerUtil.mutListener.listen(1363)) {
            loginToken.enqueue(new Callback<MwQueryResponse>() {

                @Override
                public void onResponse(Call<MwQueryResponse> call, Response<MwQueryResponse> response) {
                    if (!ListenerUtil.mutListener.listen(1360)) {
                        loginClient.login(username, password, null, twoFactorCode, response.body().query().loginToken(), Locale.getDefault().getLanguage(), new LoginCallback() {

                            @Override
                            public void success(@NonNull LoginResult result) {
                                if (!ListenerUtil.mutListener.listen(1349)) {
                                    Timber.d("Login Success");
                                }
                                if (!ListenerUtil.mutListener.listen(1350)) {
                                    onLoginSuccess(result);
                                }
                            }

                            @Override
                            public void twoFactorPrompt(@NonNull Throwable caught, @Nullable String token) {
                                if (!ListenerUtil.mutListener.listen(1351)) {
                                    Timber.d("Requesting 2FA prompt");
                                }
                                if (!ListenerUtil.mutListener.listen(1352)) {
                                    hideProgress();
                                }
                                if (!ListenerUtil.mutListener.listen(1353)) {
                                    askUserForTwoFactorAuth();
                                }
                            }

                            @Override
                            public void passwordResetPrompt(@Nullable String token) {
                                if (!ListenerUtil.mutListener.listen(1354)) {
                                    Timber.d("Showing password reset prompt");
                                }
                                if (!ListenerUtil.mutListener.listen(1355)) {
                                    hideProgress();
                                }
                                if (!ListenerUtil.mutListener.listen(1356)) {
                                    showPasswordResetPrompt();
                                }
                            }

                            @Override
                            public void error(@NonNull Throwable caught) {
                                if (!ListenerUtil.mutListener.listen(1357)) {
                                    Timber.e(caught);
                                }
                                if (!ListenerUtil.mutListener.listen(1358)) {
                                    hideProgress();
                                }
                                if (!ListenerUtil.mutListener.listen(1359)) {
                                    showMessageAndCancelDialog(caught.getLocalizedMessage());
                                }
                            }
                        });
                    }
                }

                @Override
                public void onFailure(Call<MwQueryResponse> call, Throwable t) {
                    if (!ListenerUtil.mutListener.listen(1361)) {
                        Timber.e(t);
                    }
                    if (!ListenerUtil.mutListener.listen(1362)) {
                        showMessageAndCancelDialog(t.getLocalizedMessage());
                    }
                }
            });
        }
    }

    private void hideProgress() {
        if (!ListenerUtil.mutListener.listen(1364)) {
            progressDialog.dismiss();
        }
    }

    private void showPasswordResetPrompt() {
        if (!ListenerUtil.mutListener.listen(1365)) {
            showMessageAndCancelDialog(getString(R.string.you_must_reset_your_passsword));
        }
    }

    /**
     * This function is called when user skips the login.
     * It redirects the user to Explore Activity.
     */
    private void performSkipLogin() {
        if (!ListenerUtil.mutListener.listen(1366)) {
            applicationKvStore.putBoolean("login_skipped", true);
        }
        if (!ListenerUtil.mutListener.listen(1367)) {
            MainActivity.startYourself(this);
        }
        if (!ListenerUtil.mutListener.listen(1368)) {
            finish();
        }
    }

    private void showLoggingProgressBar() {
        if (!ListenerUtil.mutListener.listen(1369)) {
            progressDialog = new ProgressDialog(this);
        }
        if (!ListenerUtil.mutListener.listen(1370)) {
            progressDialog.setIndeterminate(true);
        }
        if (!ListenerUtil.mutListener.listen(1371)) {
            progressDialog.setTitle(getString(R.string.logging_in_title));
        }
        if (!ListenerUtil.mutListener.listen(1372)) {
            progressDialog.setMessage(getString(R.string.logging_in_message));
        }
        if (!ListenerUtil.mutListener.listen(1373)) {
            progressDialog.setCanceledOnTouchOutside(false);
        }
        if (!ListenerUtil.mutListener.listen(1374)) {
            progressDialog.show();
        }
    }

    private void onLoginSuccess(LoginResult loginResult) {
        if (!ListenerUtil.mutListener.listen(1375)) {
            if (!progressDialog.isShowing()) {
                // no longer attached to activity!
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(1376)) {
            compositeDisposable.clear();
        }
        if (!ListenerUtil.mutListener.listen(1377)) {
            sessionManager.setUserLoggedIn(true);
        }
        if (!ListenerUtil.mutListener.listen(1378)) {
            sessionManager.updateAccount(loginResult);
        }
        if (!ListenerUtil.mutListener.listen(1379)) {
            progressDialog.dismiss();
        }
        if (!ListenerUtil.mutListener.listen(1380)) {
            showSuccessAndDismissDialog();
        }
        if (!ListenerUtil.mutListener.listen(1381)) {
            startMainActivity();
        }
    }

    @Override
    protected void onStart() {
        if (!ListenerUtil.mutListener.listen(1382)) {
            super.onStart();
        }
        if (!ListenerUtil.mutListener.listen(1383)) {
            delegate.onStart();
        }
    }

    @Override
    protected void onStop() {
        if (!ListenerUtil.mutListener.listen(1384)) {
            super.onStop();
        }
        if (!ListenerUtil.mutListener.listen(1385)) {
            delegate.onStop();
        }
    }

    @Override
    protected void onPostResume() {
        if (!ListenerUtil.mutListener.listen(1386)) {
            super.onPostResume();
        }
        if (!ListenerUtil.mutListener.listen(1387)) {
            getDelegate().onPostResume();
        }
    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        if (!ListenerUtil.mutListener.listen(1388)) {
            getDelegate().setContentView(view, params);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (!ListenerUtil.mutListener.listen(1390)) {
            switch(item.getItemId()) {
                case android.R.id.home:
                    if (!ListenerUtil.mutListener.listen(1389)) {
                        NavUtils.navigateUpFromSameTask(this);
                    }
                    return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    @NonNull
    public MenuInflater getMenuInflater() {
        return getDelegate().getMenuInflater();
    }

    public void askUserForTwoFactorAuth() {
        if (!ListenerUtil.mutListener.listen(1391)) {
            progressDialog.dismiss();
        }
        if (!ListenerUtil.mutListener.listen(1392)) {
            binding.twoFactorContainer.setVisibility(VISIBLE);
        }
        if (!ListenerUtil.mutListener.listen(1393)) {
            binding.loginTwoFactor.setVisibility(VISIBLE);
        }
        if (!ListenerUtil.mutListener.listen(1394)) {
            binding.loginTwoFactor.requestFocus();
        }
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (!ListenerUtil.mutListener.listen(1395)) {
            imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
        }
        if (!ListenerUtil.mutListener.listen(1396)) {
            showMessageAndCancelDialog(R.string.login_failed_2fa_needed);
        }
    }

    public void showMessageAndCancelDialog(@StringRes int resId) {
        if (!ListenerUtil.mutListener.listen(1397)) {
            showMessage(resId, R.color.secondaryDarkColor);
        }
        if (!ListenerUtil.mutListener.listen(1399)) {
            if (progressDialog != null) {
                if (!ListenerUtil.mutListener.listen(1398)) {
                    progressDialog.cancel();
                }
            }
        }
    }

    public void showMessageAndCancelDialog(String error) {
        if (!ListenerUtil.mutListener.listen(1400)) {
            showMessage(error, R.color.secondaryDarkColor);
        }
        if (!ListenerUtil.mutListener.listen(1402)) {
            if (progressDialog != null) {
                if (!ListenerUtil.mutListener.listen(1401)) {
                    progressDialog.cancel();
                }
            }
        }
    }

    public void showSuccessAndDismissDialog() {
        if (!ListenerUtil.mutListener.listen(1403)) {
            showMessage(R.string.login_success, R.color.primaryDarkColor);
        }
        if (!ListenerUtil.mutListener.listen(1404)) {
            progressDialog.dismiss();
        }
    }

    public void startMainActivity() {
        if (!ListenerUtil.mutListener.listen(1405)) {
            ActivityUtils.startActivityWithFlags(this, MainActivity.class, Intent.FLAG_ACTIVITY_SINGLE_TOP);
        }
        if (!ListenerUtil.mutListener.listen(1406)) {
            finish();
        }
    }

    private void showMessage(@StringRes int resId, @ColorRes int colorResId) {
        if (!ListenerUtil.mutListener.listen(1407)) {
            binding.errorMessage.setText(getString(resId));
        }
        if (!ListenerUtil.mutListener.listen(1408)) {
            binding.errorMessage.setTextColor(ContextCompat.getColor(this, colorResId));
        }
        if (!ListenerUtil.mutListener.listen(1409)) {
            binding.errorMessageContainer.setVisibility(VISIBLE);
        }
    }

    private void showMessage(String message, @ColorRes int colorResId) {
        if (!ListenerUtil.mutListener.listen(1410)) {
            binding.errorMessage.setText(message);
        }
        if (!ListenerUtil.mutListener.listen(1411)) {
            binding.errorMessage.setTextColor(ContextCompat.getColor(this, colorResId));
        }
        if (!ListenerUtil.mutListener.listen(1412)) {
            binding.errorMessageContainer.setVisibility(VISIBLE);
        }
    }

    private AppCompatDelegate getDelegate() {
        if (!ListenerUtil.mutListener.listen(1414)) {
            if (delegate == null) {
                if (!ListenerUtil.mutListener.listen(1413)) {
                    delegate = AppCompatDelegate.create(this, null);
                }
            }
        }
        return delegate;
    }

    private class LoginTextWatcher implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int start, int count, int after) {
        }

        @Override
        public void afterTextChanged(Editable editable) {
            boolean enabled = (ListenerUtil.mutListener.listen(1418) ? ((ListenerUtil.mutListener.listen(1415) ? (binding.loginUsername.getText().length() != 0 || binding.loginPassword.getText().length() != 0) : (binding.loginUsername.getText().length() != 0 && binding.loginPassword.getText().length() != 0)) || ((ListenerUtil.mutListener.listen(1417) ? ((ListenerUtil.mutListener.listen(1416) ? (BuildConfig.DEBUG && binding.loginTwoFactor.getText().length() != 0) : (BuildConfig.DEBUG || binding.loginTwoFactor.getText().length() != 0)) && binding.loginTwoFactor.getVisibility() != VISIBLE) : ((ListenerUtil.mutListener.listen(1416) ? (BuildConfig.DEBUG && binding.loginTwoFactor.getText().length() != 0) : (BuildConfig.DEBUG || binding.loginTwoFactor.getText().length() != 0)) || binding.loginTwoFactor.getVisibility() != VISIBLE)))) : ((ListenerUtil.mutListener.listen(1415) ? (binding.loginUsername.getText().length() != 0 || binding.loginPassword.getText().length() != 0) : (binding.loginUsername.getText().length() != 0 && binding.loginPassword.getText().length() != 0)) && ((ListenerUtil.mutListener.listen(1417) ? ((ListenerUtil.mutListener.listen(1416) ? (BuildConfig.DEBUG && binding.loginTwoFactor.getText().length() != 0) : (BuildConfig.DEBUG || binding.loginTwoFactor.getText().length() != 0)) && binding.loginTwoFactor.getVisibility() != VISIBLE) : ((ListenerUtil.mutListener.listen(1416) ? (BuildConfig.DEBUG && binding.loginTwoFactor.getText().length() != 0) : (BuildConfig.DEBUG || binding.loginTwoFactor.getText().length() != 0)) || binding.loginTwoFactor.getVisibility() != VISIBLE)))));
            if (!ListenerUtil.mutListener.listen(1419)) {
                binding.loginButton.setEnabled(enabled);
            }
        }
    }

    public static void startYourself(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        if (!ListenerUtil.mutListener.listen(1420)) {
            context.startActivity(intent);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        if (!ListenerUtil.mutListener.listen(1424)) {
            // we maintain visibility of progressDailog after configuration change
            if ((ListenerUtil.mutListener.listen(1421) ? (progressDialog != null || progressDialog.isShowing()) : (progressDialog != null && progressDialog.isShowing()))) {
                if (!ListenerUtil.mutListener.listen(1423)) {
                    outState.putBoolean(saveProgressDailog, true);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(1422)) {
                    outState.putBoolean(saveProgressDailog, false);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(1425)) {
            // Save the errorMessage
            outState.putString(saveErrorMessage, binding.errorMessage.getText().toString());
        }
        if (!ListenerUtil.mutListener.listen(1426)) {
            // Save the username
            outState.putString(saveUsername, getUsername());
        }
        if (!ListenerUtil.mutListener.listen(1427)) {
            // Save the password
            outState.putString(savePassword, getPassword());
        }
    }

    private String getUsername() {
        return binding.loginUsername.getText().toString();
    }

    private String getPassword() {
        return binding.loginPassword.getText().toString();
    }

    @Override
    protected void onRestoreInstanceState(final Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(1428)) {
            super.onRestoreInstanceState(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(1429)) {
            binding.loginUsername.setText(savedInstanceState.getString(saveUsername));
        }
        if (!ListenerUtil.mutListener.listen(1430)) {
            binding.loginPassword.setText(savedInstanceState.getString(savePassword));
        }
        if (!ListenerUtil.mutListener.listen(1432)) {
            if (savedInstanceState.getBoolean(saveProgressDailog)) {
                if (!ListenerUtil.mutListener.listen(1431)) {
                    performLogin();
                }
            }
        }
        String errorMessage = savedInstanceState.getString(saveErrorMessage);
        if (!ListenerUtil.mutListener.listen(1435)) {
            if (sessionManager.isUserLoggedIn()) {
                if (!ListenerUtil.mutListener.listen(1434)) {
                    showMessage(R.string.login_success, R.color.primaryDarkColor);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(1433)) {
                    showMessage(errorMessage, R.color.secondaryDarkColor);
                }
            }
        }
    }
}
