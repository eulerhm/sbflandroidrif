/*
 * Copyright (c) 2015 PocketHub
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.pockethub.android.accounts;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.PeriodicSync;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import com.afollestad.materialdialogs.MaterialDialog;
import com.github.pockethub.android.BuildConfig;
import com.github.pockethub.android.R;
import com.github.pockethub.android.rx.AutoDisposeUtils;
import com.github.pockethub.android.ui.MainActivity;
import com.github.pockethub.android.ui.base.AccountAuthenticatorAppCompatActivity;
import com.meisolsson.githubsdk.core.ServiceGenerator;
import com.meisolsson.githubsdk.core.TokenStore;
import com.meisolsson.githubsdk.model.GitHubToken;
import com.meisolsson.githubsdk.model.User;
import com.meisolsson.githubsdk.model.request.RequestToken;
import com.meisolsson.githubsdk.service.users.UserService;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.HttpUrl;
import javax.inject.Inject;
import java.util.List;
import java.util.concurrent.TimeUnit;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Activity to login
 */
public class LoginActivity extends AccountAuthenticatorAppCompatActivity {

    /**
     * Auth token type parameter
     */
    public static final String PARAM_AUTHTOKEN_TYPE = "authtokenType";

    /**
     * Initial user name
     */
    public static final String PARAM_USERNAME = "username";

    public static final String OAUTH_HOST = "www.github.com";

    public static final String INTENT_EXTRA_URL = "url";

    private static int WEBVIEW_REQUEST_CODE = 0;

    private static final String TAG = "LoginActivity";

    private static final long SYNC_PERIOD = TimeUnit.HOURS.toSeconds(8);

    private String clientId;

    private String secret;

    private String redirectUri;

    public static void configureSyncFor(Account account) {
        if (!ListenerUtil.mutListener.listen(119)) {
            Log.d(TAG, "Configuring account sync");
        }
        if (!ListenerUtil.mutListener.listen(120)) {
            ContentResolver.setIsSyncable(account, BuildConfig.PROVIDER_AUTHORITY_SYNC, 1);
        }
        if (!ListenerUtil.mutListener.listen(121)) {
            ContentResolver.setSyncAutomatically(account, BuildConfig.PROVIDER_AUTHORITY_SYNC, true);
        }
        List<PeriodicSync> syncs = ContentResolver.getPeriodicSyncs(account, BuildConfig.PROVIDER_AUTHORITY_SYNC);
        if (!ListenerUtil.mutListener.listen(123)) {
            if (syncs.isEmpty()) {
                if (!ListenerUtil.mutListener.listen(122)) {
                    ContentResolver.addPeriodicSync(account, BuildConfig.PROVIDER_AUTHORITY_SYNC, new Bundle(), SYNC_PERIOD);
                }
            }
        }
    }

    private AccountManager accountManager;

    private MaterialDialog progressDialog;

    @Inject
    protected UserService userService;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(124)) {
            super.onCreate(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(125)) {
            setContentView(R.layout.login);
        }
        if (!ListenerUtil.mutListener.listen(126)) {
            clientId = getString(R.string.github_client);
        }
        if (!ListenerUtil.mutListener.listen(127)) {
            secret = getString(R.string.github_secret);
        }
        if (!ListenerUtil.mutListener.listen(128)) {
            redirectUri = getString(R.string.github_oauth);
        }
        if (!ListenerUtil.mutListener.listen(129)) {
            accountManager = AccountManager.get(this);
        }
        Account[] accounts = accountManager.getAccountsByType(getString(R.string.account_type));
        if (!ListenerUtil.mutListener.listen(136)) {
            if ((ListenerUtil.mutListener.listen(134) ? (accounts.length >= 0) : (ListenerUtil.mutListener.listen(133) ? (accounts.length <= 0) : (ListenerUtil.mutListener.listen(132) ? (accounts.length < 0) : (ListenerUtil.mutListener.listen(131) ? (accounts.length != 0) : (ListenerUtil.mutListener.listen(130) ? (accounts.length == 0) : (accounts.length > 0))))))) {
                if (!ListenerUtil.mutListener.listen(135)) {
                    openMain();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(137)) {
            checkOauthConfig();
        }
    }

    private void checkOauthConfig() {
        if (!ListenerUtil.mutListener.listen(140)) {
            if ((ListenerUtil.mutListener.listen(138) ? (clientId.equals("dummy_client") && secret.equals("dummy_secret")) : (clientId.equals("dummy_client") || secret.equals("dummy_secret")))) {
                if (!ListenerUtil.mutListener.listen(139)) {
                    Toast.makeText(this, R.string.error_oauth_not_configured, Toast.LENGTH_LONG).show();
                }
            }
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        if (!ListenerUtil.mutListener.listen(141)) {
            super.onNewIntent(intent);
        }
        Uri uri = intent.getData();
        if (!ListenerUtil.mutListener.listen(142)) {
            onUserLoggedIn(uri);
        }
    }

    private void onUserLoggedIn(Uri uri) {
        if (!ListenerUtil.mutListener.listen(146)) {
            if ((ListenerUtil.mutListener.listen(143) ? (uri != null || uri.getScheme().equals(getString(R.string.github_oauth_scheme))) : (uri != null && uri.getScheme().equals(getString(R.string.github_oauth_scheme))))) {
                if (!ListenerUtil.mutListener.listen(144)) {
                    openLoadingDialog();
                }
                String code = uri.getQueryParameter("code");
                RequestToken request = RequestToken.builder().clientId(clientId).clientSecret(secret).redirectUri(redirectUri).code(code).build();
                if (!ListenerUtil.mutListener.listen(145)) {
                    ServiceGenerator.createAuthService().getToken(request).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).as(AutoDisposeUtils.bindToLifecycle(this)).subscribe(response -> {
                        GitHubToken token = response.body();
                        if (token.accessToken() != null) {
                            endAuth(token.accessToken(), token.scope());
                        } else if (token.error() != null) {
                            Toast.makeText(this, token.error(), Toast.LENGTH_LONG).show();
                            progressDialog.dismiss();
                        }
                    }, Throwable::printStackTrace);
                }
            }
        }
    }

    private void openMain() {
        Intent intent = new Intent(this, MainActivity.class);
        if (!ListenerUtil.mutListener.listen(147)) {
            startActivity(intent);
        }
        if (!ListenerUtil.mutListener.listen(148)) {
            finish();
        }
    }

    private void openLoadingDialog() {
        if (!ListenerUtil.mutListener.listen(149)) {
            progressDialog = new MaterialDialog.Builder(this).progress(true, 0).content(R.string.login_activity_authenticating).show();
        }
    }

    public void handleLogin() {
        if (!ListenerUtil.mutListener.listen(150)) {
            openLoginInBrowser();
        }
    }

    private void openLoginInBrowser() {
        String initialScope = "user,public_repo,repo,delete_repo,notifications,gist";
        HttpUrl.Builder url = new HttpUrl.Builder().scheme("https").host(OAUTH_HOST).addPathSegment("login").addPathSegment("oauth").addPathSegment("authorize").addQueryParameter("client_id", getString(R.string.github_client)).addQueryParameter("scope", initialScope);
        Intent intent = new Intent(this, LoginWebViewActivity.class);
        if (!ListenerUtil.mutListener.listen(151)) {
            intent.putExtra(INTENT_EXTRA_URL, url.toString());
        }
        if (!ListenerUtil.mutListener.listen(152)) {
            startActivityForResult(intent, WEBVIEW_REQUEST_CODE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (!ListenerUtil.mutListener.listen(153)) {
            super.onActivityResult(requestCode, resultCode, data);
        }
        if (!ListenerUtil.mutListener.listen(161)) {
            if ((ListenerUtil.mutListener.listen(159) ? ((ListenerUtil.mutListener.listen(158) ? (requestCode >= WEBVIEW_REQUEST_CODE) : (ListenerUtil.mutListener.listen(157) ? (requestCode <= WEBVIEW_REQUEST_CODE) : (ListenerUtil.mutListener.listen(156) ? (requestCode > WEBVIEW_REQUEST_CODE) : (ListenerUtil.mutListener.listen(155) ? (requestCode < WEBVIEW_REQUEST_CODE) : (ListenerUtil.mutListener.listen(154) ? (requestCode != WEBVIEW_REQUEST_CODE) : (requestCode == WEBVIEW_REQUEST_CODE)))))) || resultCode == RESULT_OK) : ((ListenerUtil.mutListener.listen(158) ? (requestCode >= WEBVIEW_REQUEST_CODE) : (ListenerUtil.mutListener.listen(157) ? (requestCode <= WEBVIEW_REQUEST_CODE) : (ListenerUtil.mutListener.listen(156) ? (requestCode > WEBVIEW_REQUEST_CODE) : (ListenerUtil.mutListener.listen(155) ? (requestCode < WEBVIEW_REQUEST_CODE) : (ListenerUtil.mutListener.listen(154) ? (requestCode != WEBVIEW_REQUEST_CODE) : (requestCode == WEBVIEW_REQUEST_CODE)))))) && resultCode == RESULT_OK))) {
                if (!ListenerUtil.mutListener.listen(160)) {
                    onUserLoggedIn(data.getData());
                }
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.m_login:
                if (!ListenerUtil.mutListener.listen(162)) {
                    handleLogin();
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void endAuth(final String accessToken, final String scope) {
        if (!ListenerUtil.mutListener.listen(163)) {
            progressDialog.setContent(getString(R.string.loading_user));
        }
        if (!ListenerUtil.mutListener.listen(164)) {
            TokenStore.getInstance(this).saveToken(accessToken);
        }
        if (!ListenerUtil.mutListener.listen(165)) {
            userService.getUser().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).as(AutoDisposeUtils.bindToLifecycle(this)).subscribe(response -> {
                User user = response.body();
                Account account = new Account(user.login(), getString(R.string.account_type));
                Bundle userData = AccountsHelper.buildBundle(user.name(), user.email(), user.avatarUrl(), scope);
                userData.putString(AccountManager.KEY_AUTHTOKEN, accessToken);
                accountManager.addAccountExplicitly(account, null, userData);
                accountManager.setAuthToken(account, getString(R.string.account_type), accessToken);
                Bundle result = new Bundle();
                result.putString(AccountManager.KEY_ACCOUNT_NAME, account.name);
                result.putString(AccountManager.KEY_ACCOUNT_TYPE, account.type);
                result.putString(AccountManager.KEY_AUTHTOKEN, accessToken);
                configureSyncFor(account);
                setAccountAuthenticatorResult(result);
                finish();
            }, Throwable::printStackTrace);
        }
    }

    @Override
    public void finish() {
        if (!ListenerUtil.mutListener.listen(167)) {
            if (progressDialog != null) {
                if (!ListenerUtil.mutListener.listen(166)) {
                    progressDialog.dismiss();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(168)) {
            super.finish();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu optionMenu) {
        if (!ListenerUtil.mutListener.listen(169)) {
            getMenuInflater().inflate(R.menu.activity_login, optionMenu);
        }
        return true;
    }
}
