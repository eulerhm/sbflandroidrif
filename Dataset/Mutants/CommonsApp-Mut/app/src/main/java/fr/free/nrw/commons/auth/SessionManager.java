package fr.free.nrw.commons.auth;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import fr.free.nrw.commons.auth.login.LoginResult;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import fr.free.nrw.commons.BuildConfig;
import fr.free.nrw.commons.kvstore.JsonKvStore;
import io.reactivex.Completable;
import io.reactivex.Observable;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Manage the current logged in user session.
 */
@Singleton
public class SessionManager {

    private final Context context;

    // Unlike a savings account...  ;-)
    private Account currentAccount;

    private JsonKvStore defaultKvStore;

    @Inject
    public SessionManager(Context context, @Named("default_preferences") JsonKvStore defaultKvStore) {
        this.context = context;
        if (!ListenerUtil.mutListener.listen(1458)) {
            this.currentAccount = null;
        }
        if (!ListenerUtil.mutListener.listen(1459)) {
            this.defaultKvStore = defaultKvStore;
        }
    }

    private boolean createAccount(@NonNull String userName, @NonNull String password) {
        Account account = getCurrentAccount();
        if (!ListenerUtil.mutListener.listen(1464)) {
            if ((ListenerUtil.mutListener.listen(1461) ? ((ListenerUtil.mutListener.listen(1460) ? (account == null && TextUtils.isEmpty(account.name)) : (account == null || TextUtils.isEmpty(account.name))) && !account.name.equals(userName)) : ((ListenerUtil.mutListener.listen(1460) ? (account == null && TextUtils.isEmpty(account.name)) : (account == null || TextUtils.isEmpty(account.name))) || !account.name.equals(userName)))) {
                if (!ListenerUtil.mutListener.listen(1462)) {
                    removeAccount();
                }
                if (!ListenerUtil.mutListener.listen(1463)) {
                    account = new Account(userName, BuildConfig.ACCOUNT_TYPE);
                }
                return accountManager().addAccountExplicitly(account, password, null);
            }
        }
        return true;
    }

    private void removeAccount() {
        Account account = getCurrentAccount();
        if (!ListenerUtil.mutListener.listen(1473)) {
            if (account != null) {
                if (!ListenerUtil.mutListener.listen(1472)) {
                    if ((ListenerUtil.mutListener.listen(1469) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP_MR1) : (ListenerUtil.mutListener.listen(1468) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1) : (ListenerUtil.mutListener.listen(1467) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP_MR1) : (ListenerUtil.mutListener.listen(1466) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.LOLLIPOP_MR1) : (ListenerUtil.mutListener.listen(1465) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.LOLLIPOP_MR1) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1))))))) {
                        if (!ListenerUtil.mutListener.listen(1471)) {
                            accountManager().removeAccountExplicitly(account);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(1470)) {
                            // noinspection deprecation
                            accountManager().removeAccount(account, null, null);
                        }
                    }
                }
            }
        }
    }

    public void updateAccount(LoginResult result) {
        boolean accountCreated = createAccount(result.getUserName(), result.getPassword());
        if (!ListenerUtil.mutListener.listen(1475)) {
            if (accountCreated) {
                if (!ListenerUtil.mutListener.listen(1474)) {
                    setPassword(result.getPassword());
                }
            }
        }
    }

    private void setPassword(@NonNull String password) {
        Account account = getCurrentAccount();
        if (!ListenerUtil.mutListener.listen(1477)) {
            if (account != null) {
                if (!ListenerUtil.mutListener.listen(1476)) {
                    accountManager().setPassword(account, password);
                }
            }
        }
    }

    /**
     * @return Account|null
     */
    @Nullable
    public Account getCurrentAccount() {
        if (!ListenerUtil.mutListener.listen(1480)) {
            if (currentAccount == null) {
                AccountManager accountManager = AccountManager.get(context);
                Account[] allAccounts = accountManager.getAccountsByType(BuildConfig.ACCOUNT_TYPE);
                if (!ListenerUtil.mutListener.listen(1479)) {
                    if (allAccounts.length != 0) {
                        if (!ListenerUtil.mutListener.listen(1478)) {
                            currentAccount = allAccounts[0];
                        }
                    }
                }
            }
        }
        return currentAccount;
    }

    public boolean doesAccountExist() {
        return getCurrentAccount() != null;
    }

    @Nullable
    public String getUserName() {
        Account account = getCurrentAccount();
        return account == null ? null : account.name;
    }

    @Nullable
    public String getPassword() {
        Account account = getCurrentAccount();
        return account == null ? null : accountManager().getPassword(account);
    }

    private AccountManager accountManager() {
        return AccountManager.get(context);
    }

    public boolean isUserLoggedIn() {
        return defaultKvStore.getBoolean("isUserLoggedIn", false);
    }

    void setUserLoggedIn(boolean isLoggedIn) {
        if (!ListenerUtil.mutListener.listen(1481)) {
            defaultKvStore.putBoolean("isUserLoggedIn", isLoggedIn);
        }
    }

    public void forceLogin(Context context) {
        if (!ListenerUtil.mutListener.listen(1483)) {
            if (context != null) {
                if (!ListenerUtil.mutListener.listen(1482)) {
                    LoginActivity.startYourself(context);
                }
            }
        }
    }

    /**
     * 1. Clears existing accounts from account manager
     * 2. Calls MediaWikiApi's logout function to clear cookies
     * @return
     */
    public Completable logout() {
        AccountManager accountManager = AccountManager.get(context);
        Account[] allAccounts = accountManager.getAccountsByType(BuildConfig.ACCOUNT_TYPE);
        return Completable.fromObservable(Observable.fromArray(allAccounts).map(a -> accountManager.removeAccount(a, null, null).getResult())).doOnComplete(() -> {
            currentAccount = null;
        });
    }

    /**
     * Return a corresponding boolean preference
     *
     * @param key
     * @return
     */
    public boolean getPreference(String key) {
        return defaultKvStore.getBoolean(key);
    }
}
