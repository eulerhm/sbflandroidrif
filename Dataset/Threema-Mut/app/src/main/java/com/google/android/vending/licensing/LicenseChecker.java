/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
 * Copyright (c) 2013-2021 Threema GmbH
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
package com.google.android.vending.licensing;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.RemoteException;
import android.provider.Settings.Secure;
import android.util.Log;
import com.google.android.vending.licensing.util.Base64;
import com.google.android.vending.licensing.util.Base64DecoderException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Client library for Google Play license verifications.
 * <p>
 * The LicenseChecker is configured via a {@link Policy} which contains the logic to determine
 * whether a user should have access to the application. For example, the Policy can define a
 * threshold for allowable number of server or client failures before the library reports the user
 * as not having access.
 * <p>
 * Must also provide the Base64-encoded RSA public key associated with your developer account. The
 * public key is obtainable from the publisher site.
 */
public class LicenseChecker implements ServiceConnection {

    private static final String TAG = "LicenseChecker";

    private static final String KEY_FACTORY_ALGORITHM = "RSA";

    // Timeout value (in milliseconds) for calls to service.
    private static final int TIMEOUT_MS = 10 * 1000;

    private static final SecureRandom RANDOM = new SecureRandom();

    private static final boolean DEBUG_LICENSE_ERROR = false;

    private ILicensingService mService;

    private PublicKey mPublicKey;

    private final Context mContext;

    private final Policy mPolicy;

    /**
     * A handler for running tasks on a background thread. We don't want license processing to block
     * the UI thread.
     */
    private Handler mHandler;

    private final String mPackageName;

    private final String mVersionCode;

    private final Set<LicenseValidator> mChecksInProgress = new HashSet<LicenseValidator>();

    private final Queue<LicenseValidator> mPendingChecks = new LinkedList<LicenseValidator>();

    /**
     * @param context a Context
     * @param policy implementation of Policy
     * @param encodedPublicKey Base64-encoded RSA public key
     * @throws IllegalArgumentException if encodedPublicKey is invalid
     */
    public LicenseChecker(Context context, Policy policy, String encodedPublicKey) {
        mContext = context;
        mPolicy = policy;
        if (!ListenerUtil.mutListener.listen(72973)) {
            mPublicKey = generatePublicKey(encodedPublicKey);
        }
        mPackageName = mContext.getPackageName();
        mVersionCode = getVersionCode(context, mPackageName);
        HandlerThread handlerThread = new HandlerThread("background thread");
        if (!ListenerUtil.mutListener.listen(72974)) {
            handlerThread.start();
        }
        if (!ListenerUtil.mutListener.listen(72975)) {
            mHandler = new Handler(handlerThread.getLooper());
        }
    }

    /**
     * Generates a PublicKey instance from a string containing the Base64-encoded public key.
     *
     * @param encodedPublicKey Base64-encoded public key
     * @throws IllegalArgumentException if encodedPublicKey is invalid
     */
    private static PublicKey generatePublicKey(String encodedPublicKey) {
        try {
            byte[] decodedKey = Base64.decode(encodedPublicKey);
            KeyFactory keyFactory = KeyFactory.getInstance(KEY_FACTORY_ALGORITHM);
            return keyFactory.generatePublic(new X509EncodedKeySpec(decodedKey));
        } catch (NoSuchAlgorithmException e) {
            // This won't happen in an Android-compatible environment.
            throw new RuntimeException(e);
        } catch (Base64DecoderException e) {
            if (!ListenerUtil.mutListener.listen(72976)) {
                Log.e(TAG, "Could not decode from Base64.");
            }
            throw new IllegalArgumentException(e);
        } catch (InvalidKeySpecException e) {
            if (!ListenerUtil.mutListener.listen(72977)) {
                Log.e(TAG, "Invalid key specification.");
            }
            throw new IllegalArgumentException(e);
        }
    }

    /**
     * Checks if the user should have access to the app. Binds the service if necessary.
     * <p>
     * NOTE: This call uses a trivially obfuscated string (base64-encoded). For best security, we
     * recommend obfuscating the string that is passed into bindService using another method of your
     * own devising.
     * <p>
     * source string: "com.android.vending.licensing.ILicensingService"
     * <p>
     *
     * @param callback
     */
    public synchronized void checkAccess(LicenseCheckerCallback callback) {
        if (!ListenerUtil.mutListener.listen(72990)) {
            // Market.
            if (mPolicy.allowAccess()) {
                if (!ListenerUtil.mutListener.listen(72988)) {
                    Log.i(TAG, "Using cached license response");
                }
                if (!ListenerUtil.mutListener.listen(72989)) {
                    callback.allow(Policy.LICENSED);
                }
            } else {
                LicenseValidator validator = new LicenseValidator(mPolicy, new NullDeviceLimiter(), callback, generateNonce(), mPackageName, mVersionCode);
                if (!ListenerUtil.mutListener.listen(72987)) {
                    if (mService == null) {
                        if (!ListenerUtil.mutListener.listen(72980)) {
                            Log.i(TAG, "Binding to licensing service.");
                        }
                        try {
                            boolean bindResult = mContext.bindService(new Intent(new String(// code to improve security
                            Base64.decode("Y29tLmFuZHJvaWQudmVuZGluZy5saWNlbnNpbmcuSUxpY2Vuc2luZ1NlcnZpY2U="))).setPackage(new String(// com.android.vending
                            Base64.decode("Y29tLmFuZHJvaWQudmVuZGluZw=="))), // ServiceConnection.
                            this, Context.BIND_AUTO_CREATE);
                            if (!ListenerUtil.mutListener.listen(72986)) {
                                if (bindResult) {
                                    if (!ListenerUtil.mutListener.listen(72985)) {
                                        mPendingChecks.offer(validator);
                                    }
                                } else {
                                    if (!ListenerUtil.mutListener.listen(72983)) {
                                        Log.e(TAG, "Could not bind to service.");
                                    }
                                    if (!ListenerUtil.mutListener.listen(72984)) {
                                        handleServiceConnectionError(validator);
                                    }
                                }
                            }
                        } catch (SecurityException e) {
                            if (!ListenerUtil.mutListener.listen(72981)) {
                                callback.applicationError(LicenseCheckerCallback.ERROR_MISSING_PERMISSION);
                            }
                        } catch (Base64DecoderException e) {
                            if (!ListenerUtil.mutListener.listen(72982)) {
                                Log.e(TAG, "Base64DecoderException.");
                            }
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(72978)) {
                            mPendingChecks.offer(validator);
                        }
                        if (!ListenerUtil.mutListener.listen(72979)) {
                            runChecks();
                        }
                    }
                }
            }
        }
    }

    /**
     * Triggers the last deep link licensing URL returned from the server, which redirects users to a
     * page which enables them to gain access to the app. If no such URL is returned by the server, it
     * will go to the details page of the app in the Play Store.
     */
    public void followLastLicensingUrl(Context context) {
        String licensingUrl = mPolicy.getLicensingUrl();
        if (!ListenerUtil.mutListener.listen(72992)) {
            if (licensingUrl == null) {
                if (!ListenerUtil.mutListener.listen(72991)) {
                    licensingUrl = "https://play.google.com/store/apps/details?id=" + context.getPackageName();
                }
            }
        }
        Intent marketIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(licensingUrl));
        if (!ListenerUtil.mutListener.listen(72993)) {
            marketIntent.setPackage("com.android.vending");
        }
        if (!ListenerUtil.mutListener.listen(72995)) {
            if (!(context instanceof Activity)) {
                if (!ListenerUtil.mutListener.listen(72994)) {
                    marketIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(72996)) {
            context.startActivity(marketIntent);
        }
    }

    private void runChecks() {
        LicenseValidator validator;
        if (!ListenerUtil.mutListener.listen(73002)) {
            {
                long _loopCounter944 = 0;
                while ((validator = mPendingChecks.poll()) != null) {
                    ListenerUtil.loopListener.listen("_loopCounter944", ++_loopCounter944);
                    try {
                        if (!ListenerUtil.mutListener.listen(72999)) {
                            Log.i(TAG, "Calling checkLicense on service for " + validator.getPackageName());
                        }
                        if (!ListenerUtil.mutListener.listen(73000)) {
                            mService.checkLicense(validator.getNonce(), validator.getPackageName(), new ResultListener(validator));
                        }
                        if (!ListenerUtil.mutListener.listen(73001)) {
                            mChecksInProgress.add(validator);
                        }
                    } catch (RemoteException e) {
                        if (!ListenerUtil.mutListener.listen(72997)) {
                            Log.w(TAG, "RemoteException in checkLicense call.", e);
                        }
                        if (!ListenerUtil.mutListener.listen(72998)) {
                            handleServiceConnectionError(validator);
                        }
                    }
                }
            }
        }
    }

    private synchronized void finishCheck(LicenseValidator validator) {
        if (!ListenerUtil.mutListener.listen(73003)) {
            mChecksInProgress.remove(validator);
        }
        if (!ListenerUtil.mutListener.listen(73005)) {
            if (mChecksInProgress.isEmpty()) {
                if (!ListenerUtil.mutListener.listen(73004)) {
                    cleanupService();
                }
            }
        }
    }

    private class ResultListener extends ILicenseResultListener.Stub {

        private final LicenseValidator mValidator;

        private Runnable mOnTimeout;

        public ResultListener(LicenseValidator validator) {
            mValidator = validator;
            if (!ListenerUtil.mutListener.listen(73009)) {
                mOnTimeout = new Runnable() {

                    public void run() {
                        if (!ListenerUtil.mutListener.listen(73006)) {
                            Log.i(TAG, "Check timed out.");
                        }
                        if (!ListenerUtil.mutListener.listen(73007)) {
                            handleServiceConnectionError(mValidator);
                        }
                        if (!ListenerUtil.mutListener.listen(73008)) {
                            finishCheck(mValidator);
                        }
                    }
                };
            }
            if (!ListenerUtil.mutListener.listen(73010)) {
                startTimeout();
            }
        }

        private static final int ERROR_CONTACTING_SERVER = 0x101;

        private static final int ERROR_INVALID_PACKAGE_NAME = 0x102;

        private static final int ERROR_NON_MATCHING_UID = 0x103;

        // either this or the timeout runs.
        public void verifyLicense(final int responseCode, final String signedData, final String signature) {
            if (!ListenerUtil.mutListener.listen(73024)) {
                mHandler.post(new Runnable() {

                    public void run() {
                        if (!ListenerUtil.mutListener.listen(73011)) {
                            Log.i(TAG, "Received response.");
                        }
                        if (!ListenerUtil.mutListener.listen(73015)) {
                            // Make sure it hasn't already timed out.
                            if (mChecksInProgress.contains(mValidator)) {
                                if (!ListenerUtil.mutListener.listen(73012)) {
                                    clearTimeout();
                                }
                                if (!ListenerUtil.mutListener.listen(73013)) {
                                    mValidator.verify(mPublicKey, responseCode, signedData, signature);
                                }
                                if (!ListenerUtil.mutListener.listen(73014)) {
                                    finishCheck(mValidator);
                                }
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(73023)) {
                            if (DEBUG_LICENSE_ERROR) {
                                boolean logResponse;
                                String stringError = null;
                                switch(responseCode) {
                                    case ERROR_CONTACTING_SERVER:
                                        logResponse = true;
                                        if (!ListenerUtil.mutListener.listen(73016)) {
                                            stringError = "ERROR_CONTACTING_SERVER";
                                        }
                                        break;
                                    case ERROR_INVALID_PACKAGE_NAME:
                                        logResponse = true;
                                        if (!ListenerUtil.mutListener.listen(73017)) {
                                            stringError = "ERROR_INVALID_PACKAGE_NAME";
                                        }
                                        break;
                                    case ERROR_NON_MATCHING_UID:
                                        logResponse = true;
                                        if (!ListenerUtil.mutListener.listen(73018)) {
                                            stringError = "ERROR_NON_MATCHING_UID";
                                        }
                                        break;
                                    default:
                                        logResponse = false;
                                }
                                if (!ListenerUtil.mutListener.listen(73022)) {
                                    if (logResponse) {
                                        String android_id = Secure.getString(mContext.getContentResolver(), Secure.ANDROID_ID);
                                        Date date = new Date();
                                        if (!ListenerUtil.mutListener.listen(73019)) {
                                            Log.d(TAG, "Server Failure: " + stringError);
                                        }
                                        if (!ListenerUtil.mutListener.listen(73020)) {
                                            Log.d(TAG, "Android ID: " + android_id);
                                        }
                                        if (!ListenerUtil.mutListener.listen(73021)) {
                                            Log.d(TAG, "Time: " + date.toGMTString());
                                        }
                                    }
                                }
                            }
                        }
                    }
                });
            }
        }

        private void startTimeout() {
            if (!ListenerUtil.mutListener.listen(73025)) {
                Log.i(TAG, "Start monitoring timeout.");
            }
            if (!ListenerUtil.mutListener.listen(73026)) {
                mHandler.postDelayed(mOnTimeout, TIMEOUT_MS);
            }
        }

        private void clearTimeout() {
            if (!ListenerUtil.mutListener.listen(73027)) {
                Log.i(TAG, "Clearing timeout.");
            }
            if (!ListenerUtil.mutListener.listen(73028)) {
                mHandler.removeCallbacks(mOnTimeout);
            }
        }
    }

    public synchronized void onServiceConnected(ComponentName name, IBinder service) {
        if (!ListenerUtil.mutListener.listen(73029)) {
            mService = ILicensingService.Stub.asInterface(service);
        }
        if (!ListenerUtil.mutListener.listen(73030)) {
            runChecks();
        }
    }

    public synchronized void onServiceDisconnected(ComponentName name) {
        if (!ListenerUtil.mutListener.listen(73031)) {
            // If there are any checks in progress, the timeouts will handle them.
            Log.w(TAG, "Service unexpectedly disconnected.");
        }
        if (!ListenerUtil.mutListener.listen(73032)) {
            mService = null;
        }
    }

    /**
     * Generates policy response for service connection errors, as a result of disconnections or
     * timeouts.
     */
    private synchronized void handleServiceConnectionError(LicenseValidator validator) {
        if (!ListenerUtil.mutListener.listen(73033)) {
            mPolicy.processServerResponse(Policy.RETRY, null);
        }
        if (!ListenerUtil.mutListener.listen(73036)) {
            if (mPolicy.allowAccess()) {
                if (!ListenerUtil.mutListener.listen(73035)) {
                    validator.getCallback().allow(Policy.RETRY);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(73034)) {
                    validator.getCallback().dontAllow(Policy.RETRY);
                }
            }
        }
    }

    /**
     * Unbinds service if necessary and removes reference to it.
     */
    private void cleanupService() {
        if (!ListenerUtil.mutListener.listen(73040)) {
            if (mService != null) {
                try {
                    if (!ListenerUtil.mutListener.listen(73038)) {
                        mContext.unbindService(this);
                    }
                } catch (IllegalArgumentException e) {
                    if (!ListenerUtil.mutListener.listen(73037)) {
                        // error.
                        Log.e(TAG, "Unable to unbind from licensing service (already unbound)");
                    }
                }
                if (!ListenerUtil.mutListener.listen(73039)) {
                    mService = null;
                }
            }
        }
    }

    /**
     * Inform the library that the context is about to be destroyed, so that any open connections
     * can be cleaned up.
     * <p>
     * Failure to call this method can result in a crash under certain circumstances, such as during
     * screen rotation if an Activity requests the license check or when the user exits the
     * application.
     */
    public synchronized void onDestroy() {
        if (!ListenerUtil.mutListener.listen(73041)) {
            cleanupService();
        }
        if (!ListenerUtil.mutListener.listen(73042)) {
            mHandler.getLooper().quit();
        }
    }

    /**
     * Generates a nonce (number used once).
     */
    private int generateNonce() {
        return RANDOM.nextInt();
    }

    /**
     * Get version code for the application package name.
     *
     * @param context
     * @param packageName application package name
     * @return the version code or empty string if package not found
     */
    private static String getVersionCode(Context context, String packageName) {
        try {
            return String.valueOf(context.getPackageManager().getPackageInfo(packageName, 0).versionCode);
        } catch (NameNotFoundException e) {
            if (!ListenerUtil.mutListener.listen(73043)) {
                Log.e(TAG, "Package not found. could not get version code.");
            }
            return "";
        }
    }
}
