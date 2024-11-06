package fr.free.nrw.commons.auth;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.Context;
import androidx.annotation.Nullable;
import fr.free.nrw.commons.BuildConfig;
import timber.log.Timber;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class AccountUtil {

    public static final String AUTH_TOKEN_TYPE = "CommonsAndroid";

    public AccountUtil() {
    }

    /**
     * @return Account|null
     */
    @Nullable
    public static Account account(Context context) {
        try {
            Account[] accounts = accountManager(context).getAccountsByType(BuildConfig.ACCOUNT_TYPE);
            if (!ListenerUtil.mutListener.listen(1276)) {
                if ((ListenerUtil.mutListener.listen(1275) ? (accounts.length >= 0) : (ListenerUtil.mutListener.listen(1274) ? (accounts.length <= 0) : (ListenerUtil.mutListener.listen(1273) ? (accounts.length < 0) : (ListenerUtil.mutListener.listen(1272) ? (accounts.length != 0) : (ListenerUtil.mutListener.listen(1271) ? (accounts.length == 0) : (accounts.length > 0))))))) {
                    return accounts[0];
                }
            }
        } catch (SecurityException e) {
            if (!ListenerUtil.mutListener.listen(1270)) {
                Timber.e(e);
            }
        }
        return null;
    }

    @Nullable
    public static String getUserName(Context context) {
        Account account = account(context);
        return account == null ? null : account.name;
    }

    private static AccountManager accountManager(Context context) {
        return AccountManager.get(context);
    }
}
