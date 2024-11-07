package fr.free.nrw.commons.auth;

import android.accounts.AbstractAccountAuthenticator;
import android.accounts.Account;
import android.accounts.AccountAuthenticatorResponse;
import android.accounts.AccountManager;
import android.accounts.NetworkErrorException;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import fr.free.nrw.commons.BuildConfig;
import static fr.free.nrw.commons.auth.AccountUtil.AUTH_TOKEN_TYPE;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Handles WikiMedia commons account Authentication
 */
public class WikiAccountAuthenticator extends AbstractAccountAuthenticator {

    private static final String[] SYNC_AUTHORITIES = { BuildConfig.CONTRIBUTION_AUTHORITY, BuildConfig.MODIFICATION_AUTHORITY };

    @NonNull
    private final Context context;

    public WikiAccountAuthenticator(@NonNull Context context) {
        super(context);
        this.context = context;
    }

    /**
     * Provides Bundle with edited Account Properties
     */
    @Override
    public Bundle editProperties(AccountAuthenticatorResponse response, String accountType) {
        Bundle bundle = new Bundle();
        if (!ListenerUtil.mutListener.listen(1277)) {
            bundle.putString("test", "editProperties");
        }
        return bundle;
    }

    @Override
    public Bundle addAccount(@NonNull AccountAuthenticatorResponse response, @NonNull String accountType, @Nullable String authTokenType, @Nullable String[] requiredFeatures, @Nullable Bundle options) throws NetworkErrorException {
        if (!ListenerUtil.mutListener.listen(1279)) {
            // account type not supported returns bundle without loginActivity Intent, it just contains "test" key
            if (!supportedAccountType(accountType)) {
                Bundle bundle = new Bundle();
                if (!ListenerUtil.mutListener.listen(1278)) {
                    bundle.putString("test", "addAccount");
                }
                return bundle;
            }
        }
        return addAccount(response);
    }

    @Override
    public Bundle confirmCredentials(@NonNull AccountAuthenticatorResponse response, @NonNull Account account, @Nullable Bundle options) throws NetworkErrorException {
        Bundle bundle = new Bundle();
        if (!ListenerUtil.mutListener.listen(1280)) {
            bundle.putString("test", "confirmCredentials");
        }
        return bundle;
    }

    @Override
    public Bundle getAuthToken(@NonNull AccountAuthenticatorResponse response, @NonNull Account account, @NonNull String authTokenType, @Nullable Bundle options) throws NetworkErrorException {
        Bundle bundle = new Bundle();
        if (!ListenerUtil.mutListener.listen(1281)) {
            bundle.putString("test", "getAuthToken");
        }
        return bundle;
    }

    @Nullable
    @Override
    public String getAuthTokenLabel(@NonNull String authTokenType) {
        return supportedAccountType(authTokenType) ? AUTH_TOKEN_TYPE : null;
    }

    @Nullable
    @Override
    public Bundle updateCredentials(@NonNull AccountAuthenticatorResponse response, @NonNull Account account, @Nullable String authTokenType, @Nullable Bundle options) throws NetworkErrorException {
        Bundle bundle = new Bundle();
        if (!ListenerUtil.mutListener.listen(1282)) {
            bundle.putString("test", "updateCredentials");
        }
        return bundle;
    }

    @Nullable
    @Override
    public Bundle hasFeatures(@NonNull AccountAuthenticatorResponse response, @NonNull Account account, @NonNull String[] features) throws NetworkErrorException {
        Bundle bundle = new Bundle();
        if (!ListenerUtil.mutListener.listen(1283)) {
            bundle.putBoolean(AccountManager.KEY_BOOLEAN_RESULT, false);
        }
        return bundle;
    }

    private boolean supportedAccountType(@Nullable String type) {
        return BuildConfig.ACCOUNT_TYPE.equals(type);
    }

    /**
     * Provides a bundle containing a Parcel
     * the Parcel packs an Intent with LoginActivity and Authenticator response (requires valid account type)
     */
    private Bundle addAccount(AccountAuthenticatorResponse response) {
        Intent intent = new Intent(context, LoginActivity.class);
        if (!ListenerUtil.mutListener.listen(1284)) {
            intent.putExtra(AccountManager.KEY_ACCOUNT_AUTHENTICATOR_RESPONSE, response);
        }
        Bundle bundle = new Bundle();
        if (!ListenerUtil.mutListener.listen(1285)) {
            bundle.putParcelable(AccountManager.KEY_INTENT, intent);
        }
        return bundle;
    }

    @Override
    public Bundle getAccountRemovalAllowed(AccountAuthenticatorResponse response, Account account) throws NetworkErrorException {
        Bundle result = super.getAccountRemovalAllowed(response, account);
        if (!ListenerUtil.mutListener.listen(1290)) {
            if ((ListenerUtil.mutListener.listen(1286) ? (result.containsKey(AccountManager.KEY_BOOLEAN_RESULT) || !result.containsKey(AccountManager.KEY_INTENT)) : (result.containsKey(AccountManager.KEY_BOOLEAN_RESULT) && !result.containsKey(AccountManager.KEY_INTENT)))) {
                boolean allowed = result.getBoolean(AccountManager.KEY_BOOLEAN_RESULT);
                if (!ListenerUtil.mutListener.listen(1289)) {
                    if (allowed) {
                        if (!ListenerUtil.mutListener.listen(1288)) {
                            {
                                long _loopCounter18 = 0;
                                for (String auth : SYNC_AUTHORITIES) {
                                    ListenerUtil.loopListener.listen("_loopCounter18", ++_loopCounter18);
                                    if (!ListenerUtil.mutListener.listen(1287)) {
                                        ContentResolver.cancelSync(account, auth);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return result;
    }
}
