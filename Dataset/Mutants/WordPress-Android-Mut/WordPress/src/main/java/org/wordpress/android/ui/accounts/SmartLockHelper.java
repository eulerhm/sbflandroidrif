package org.wordpress.android.ui.accounts;

import android.app.Activity;
import android.content.IntentSender;
import android.net.Uri;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.credentials.Credential;
import com.google.android.gms.auth.api.credentials.CredentialRequest;
import com.google.android.gms.auth.api.credentials.CredentialRequestResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import org.wordpress.android.ui.RequestCodes;
import org.wordpress.android.util.AppLog;
import org.wordpress.android.util.AppLog.T;
import java.lang.ref.WeakReference;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class SmartLockHelper {

    private GoogleApiClient mCredentialsClient;

    private WeakReference<FragmentActivity> mActivity;

    public interface Callback {

        void onCredentialRetrieved(Credential credential);

        void onCredentialsUnavailable();
    }

    public SmartLockHelper(@NonNull FragmentActivity activity) {
        if (!ListenerUtil.mutListener.listen(4179)) {
            if ((ListenerUtil.mutListener.listen(4177) ? (activity instanceof OnConnectionFailedListener || activity instanceof ConnectionCallbacks) : (activity instanceof OnConnectionFailedListener && activity instanceof ConnectionCallbacks))) {
                if (!ListenerUtil.mutListener.listen(4178)) {
                    mActivity = new WeakReference<>(activity);
                }
            } else {
                throw new RuntimeException("SmartLockHelper constructor needs an activity that " + "implements OnConnectionFailedListener and ConnectionCallbacks");
            }
        }
    }

    private FragmentActivity getActivityAndCheckAvailability() {
        FragmentActivity activity = mActivity.get();
        if (!ListenerUtil.mutListener.listen(4180)) {
            if (activity == null) {
                return null;
            }
        }
        int status = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(activity);
        if (!ListenerUtil.mutListener.listen(4181)) {
            if (status == ConnectionResult.SUCCESS) {
                return activity;
            }
        }
        return null;
    }

    public boolean initSmartLockForPasswords() {
        FragmentActivity activity = getActivityAndCheckAvailability();
        if (!ListenerUtil.mutListener.listen(4182)) {
            if (activity == null) {
                return false;
            }
        }
        if (!ListenerUtil.mutListener.listen(4183)) {
            mCredentialsClient = new GoogleApiClient.Builder(activity).addConnectionCallbacks((ConnectionCallbacks) activity).enableAutoManage(activity, (OnConnectionFailedListener) activity).addApi(Auth.CREDENTIALS_API).build();
        }
        return true;
    }

    public void smartLockAutoFill(@NonNull final Callback callback) {
        Activity activity = getActivityAndCheckAvailability();
        if (!ListenerUtil.mutListener.listen(4186)) {
            if ((ListenerUtil.mutListener.listen(4185) ? ((ListenerUtil.mutListener.listen(4184) ? (activity == null && mCredentialsClient == null) : (activity == null || mCredentialsClient == null)) && !mCredentialsClient.isConnected()) : ((ListenerUtil.mutListener.listen(4184) ? (activity == null && mCredentialsClient == null) : (activity == null || mCredentialsClient == null)) || !mCredentialsClient.isConnected()))) {
                return;
            }
        }
        CredentialRequest credentialRequest = new CredentialRequest.Builder().setPasswordLoginSupported(true).build();
        if (!ListenerUtil.mutListener.listen(4196)) {
            Auth.CredentialsApi.request(mCredentialsClient, credentialRequest).setResultCallback(new ResultCallback<CredentialRequestResult>() {

                @Override
                public void onResult(@NonNull CredentialRequestResult result) {
                    Status status = result.getStatus();
                    if (!ListenerUtil.mutListener.listen(4195)) {
                        if (status.isSuccess()) {
                            Credential credential = result.getCredential();
                            if (!ListenerUtil.mutListener.listen(4194)) {
                                callback.onCredentialRetrieved(credential);
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(4193)) {
                                if (status.getStatusCode() == CommonStatusCodes.RESOLUTION_REQUIRED) {
                                    try {
                                        Activity activity = getActivityAndCheckAvailability();
                                        if (!ListenerUtil.mutListener.listen(4191)) {
                                            if (activity == null) {
                                                return;
                                            }
                                        }
                                        if (!ListenerUtil.mutListener.listen(4192)) {
                                            // Prompt the user to choose a saved credential
                                            status.startResolutionForResult(activity, RequestCodes.SMART_LOCK_READ);
                                        }
                                    } catch (IntentSender.SendIntentException e) {
                                        if (!ListenerUtil.mutListener.listen(4189)) {
                                            AppLog.d(T.NUX, "SmartLock: Failed to send resolution for credential request");
                                        }
                                        if (!ListenerUtil.mutListener.listen(4190)) {
                                            callback.onCredentialsUnavailable();
                                        }
                                    }
                                } else {
                                    if (!ListenerUtil.mutListener.listen(4187)) {
                                        // The user must create an account or log in manually.
                                        AppLog.d(T.NUX, "SmartLock: Unsuccessful credential request.");
                                    }
                                    if (!ListenerUtil.mutListener.listen(4188)) {
                                        callback.onCredentialsUnavailable();
                                    }
                                }
                            }
                        }
                    }
                }
            });
        }
    }

    public void saveCredentialsInSmartLock(@NonNull final String username, @NonNull final String password, @NonNull final String displayName, @Nullable final Uri profilePicture) {
        if (!ListenerUtil.mutListener.listen(4199)) {
            // https://github.com/wordpress-mobile/WordPress-Android/issues/5850
            if ((ListenerUtil.mutListener.listen(4197) ? (TextUtils.isEmpty(password) && TextUtils.isEmpty(username)) : (TextUtils.isEmpty(password) || TextUtils.isEmpty(username)))) {
                if (!ListenerUtil.mutListener.listen(4198)) {
                    AppLog.i(T.MAIN, String.format("Cannot save Smart Lock credentials, username (%s) or password (%s) is empty", username, password));
                }
                return;
            }
        }
        Activity activity = getActivityAndCheckAvailability();
        if (!ListenerUtil.mutListener.listen(4202)) {
            if ((ListenerUtil.mutListener.listen(4201) ? ((ListenerUtil.mutListener.listen(4200) ? (activity == null && mCredentialsClient == null) : (activity == null || mCredentialsClient == null)) && !mCredentialsClient.isConnected()) : ((ListenerUtil.mutListener.listen(4200) ? (activity == null && mCredentialsClient == null) : (activity == null || mCredentialsClient == null)) || !mCredentialsClient.isConnected()))) {
                return;
            }
        }
        Credential credential = new Credential.Builder(username).setPassword(password).setName(displayName).setProfilePictureUri(profilePicture).build();
        if (!ListenerUtil.mutListener.listen(4207)) {
            Auth.CredentialsApi.save(mCredentialsClient, credential).setResultCallback(new ResultCallback<Status>() {

                @Override
                public void onResult(@NonNull Status status) {
                    if (!ListenerUtil.mutListener.listen(4206)) {
                        if ((ListenerUtil.mutListener.listen(4203) ? (!status.isSuccess() || status.hasResolution()) : (!status.isSuccess() && status.hasResolution()))) {
                            try {
                                Activity activity = getActivityAndCheckAvailability();
                                if (!ListenerUtil.mutListener.listen(4204)) {
                                    if (activity == null) {
                                        return;
                                    }
                                }
                                if (!ListenerUtil.mutListener.listen(4205)) {
                                    // This prompt the user to resolve the save request
                                    status.startResolutionForResult(activity, RequestCodes.SMART_LOCK_SAVE);
                                }
                            } catch (IntentSender.SendIntentException e) {
                            }
                        }
                    }
                }
            });
        }
    }

    public void deleteCredentialsInSmartLock(@NonNull final String username, @NonNull final String password) {
        Activity activity = getActivityAndCheckAvailability();
        if (!ListenerUtil.mutListener.listen(4210)) {
            if ((ListenerUtil.mutListener.listen(4209) ? ((ListenerUtil.mutListener.listen(4208) ? (activity == null && mCredentialsClient == null) : (activity == null || mCredentialsClient == null)) && !mCredentialsClient.isConnected()) : ((ListenerUtil.mutListener.listen(4208) ? (activity == null && mCredentialsClient == null) : (activity == null || mCredentialsClient == null)) || !mCredentialsClient.isConnected()))) {
                return;
            }
        }
        Credential credential = new Credential.Builder(username).setPassword(password).build();
        if (!ListenerUtil.mutListener.listen(4212)) {
            Auth.CredentialsApi.delete(mCredentialsClient, credential).setResultCallback(new ResultCallback<Status>() {

                @Override
                public void onResult(@NonNull Status status) {
                    if (!ListenerUtil.mutListener.listen(4211)) {
                        AppLog.i(T.NUX, status.isSuccess() ? "SmartLock: credentials deleted for username: " + username : "SmartLock: Credentials not deleted for username: " + username);
                    }
                }
            });
        }
    }

    public void disableAutoSignIn() {
        if (!ListenerUtil.mutListener.listen(4213)) {
            Auth.CredentialsApi.disableAutoSignIn(mCredentialsClient);
        }
    }
}
