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

import android.text.TextUtils;
import android.util.Log;
import com.google.android.vending.licensing.util.Base64;
import com.google.android.vending.licensing.util.Base64DecoderException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Contains data related to a licensing request and methods to verify
 * and process the response.
 */
class LicenseValidator {

    private static final String TAG = "LicenseValidator";

    // Server response codes.
    private static final int LICENSED = 0x0;

    private static final int NOT_LICENSED = 0x1;

    private static final int LICENSED_OLD_KEY = 0x2;

    private static final int ERROR_NOT_MARKET_MANAGED = 0x3;

    private static final int ERROR_SERVER_FAILURE = 0x4;

    private static final int ERROR_OVER_QUOTA = 0x5;

    private static final int ERROR_CONTACTING_SERVER = 0x101;

    private static final int ERROR_INVALID_PACKAGE_NAME = 0x102;

    private static final int ERROR_NON_MATCHING_UID = 0x103;

    private final Policy mPolicy;

    private final LicenseCheckerCallback mCallback;

    private final int mNonce;

    private final String mPackageName;

    private final String mVersionCode;

    private final DeviceLimiter mDeviceLimiter;

    LicenseValidator(Policy policy, DeviceLimiter deviceLimiter, LicenseCheckerCallback callback, int nonce, String packageName, String versionCode) {
        mPolicy = policy;
        mDeviceLimiter = deviceLimiter;
        mCallback = callback;
        mNonce = nonce;
        mPackageName = packageName;
        mVersionCode = versionCode;
    }

    public LicenseCheckerCallback getCallback() {
        return mCallback;
    }

    public int getNonce() {
        return mNonce;
    }

    public String getPackageName() {
        return mPackageName;
    }

    private static final String SIGNATURE_ALGORITHM = "SHA1withRSA";

    /**
     * Verifies the response from server and calls appropriate callback method.
     *
     * @param publicKey public key associated with the developer account
     * @param responseCode server response code
     * @param signedData signed data from server
     * @param signature server signature
     */
    public void verify(PublicKey publicKey, int responseCode, String signedData, String signature) {
        String userId = null;
        // Skip signature check for unsuccessful requests
        ResponseData data = null;
        if (!ListenerUtil.mutListener.listen(73091)) {
            if ((ListenerUtil.mutListener.listen(73060) ? ((ListenerUtil.mutListener.listen(73054) ? ((ListenerUtil.mutListener.listen(73048) ? (responseCode >= LICENSED) : (ListenerUtil.mutListener.listen(73047) ? (responseCode <= LICENSED) : (ListenerUtil.mutListener.listen(73046) ? (responseCode > LICENSED) : (ListenerUtil.mutListener.listen(73045) ? (responseCode < LICENSED) : (ListenerUtil.mutListener.listen(73044) ? (responseCode != LICENSED) : (responseCode == LICENSED)))))) && (ListenerUtil.mutListener.listen(73053) ? (responseCode >= NOT_LICENSED) : (ListenerUtil.mutListener.listen(73052) ? (responseCode <= NOT_LICENSED) : (ListenerUtil.mutListener.listen(73051) ? (responseCode > NOT_LICENSED) : (ListenerUtil.mutListener.listen(73050) ? (responseCode < NOT_LICENSED) : (ListenerUtil.mutListener.listen(73049) ? (responseCode != NOT_LICENSED) : (responseCode == NOT_LICENSED))))))) : ((ListenerUtil.mutListener.listen(73048) ? (responseCode >= LICENSED) : (ListenerUtil.mutListener.listen(73047) ? (responseCode <= LICENSED) : (ListenerUtil.mutListener.listen(73046) ? (responseCode > LICENSED) : (ListenerUtil.mutListener.listen(73045) ? (responseCode < LICENSED) : (ListenerUtil.mutListener.listen(73044) ? (responseCode != LICENSED) : (responseCode == LICENSED)))))) || (ListenerUtil.mutListener.listen(73053) ? (responseCode >= NOT_LICENSED) : (ListenerUtil.mutListener.listen(73052) ? (responseCode <= NOT_LICENSED) : (ListenerUtil.mutListener.listen(73051) ? (responseCode > NOT_LICENSED) : (ListenerUtil.mutListener.listen(73050) ? (responseCode < NOT_LICENSED) : (ListenerUtil.mutListener.listen(73049) ? (responseCode != NOT_LICENSED) : (responseCode == NOT_LICENSED)))))))) && (ListenerUtil.mutListener.listen(73059) ? (responseCode >= LICENSED_OLD_KEY) : (ListenerUtil.mutListener.listen(73058) ? (responseCode <= LICENSED_OLD_KEY) : (ListenerUtil.mutListener.listen(73057) ? (responseCode > LICENSED_OLD_KEY) : (ListenerUtil.mutListener.listen(73056) ? (responseCode < LICENSED_OLD_KEY) : (ListenerUtil.mutListener.listen(73055) ? (responseCode != LICENSED_OLD_KEY) : (responseCode == LICENSED_OLD_KEY))))))) : ((ListenerUtil.mutListener.listen(73054) ? ((ListenerUtil.mutListener.listen(73048) ? (responseCode >= LICENSED) : (ListenerUtil.mutListener.listen(73047) ? (responseCode <= LICENSED) : (ListenerUtil.mutListener.listen(73046) ? (responseCode > LICENSED) : (ListenerUtil.mutListener.listen(73045) ? (responseCode < LICENSED) : (ListenerUtil.mutListener.listen(73044) ? (responseCode != LICENSED) : (responseCode == LICENSED)))))) && (ListenerUtil.mutListener.listen(73053) ? (responseCode >= NOT_LICENSED) : (ListenerUtil.mutListener.listen(73052) ? (responseCode <= NOT_LICENSED) : (ListenerUtil.mutListener.listen(73051) ? (responseCode > NOT_LICENSED) : (ListenerUtil.mutListener.listen(73050) ? (responseCode < NOT_LICENSED) : (ListenerUtil.mutListener.listen(73049) ? (responseCode != NOT_LICENSED) : (responseCode == NOT_LICENSED))))))) : ((ListenerUtil.mutListener.listen(73048) ? (responseCode >= LICENSED) : (ListenerUtil.mutListener.listen(73047) ? (responseCode <= LICENSED) : (ListenerUtil.mutListener.listen(73046) ? (responseCode > LICENSED) : (ListenerUtil.mutListener.listen(73045) ? (responseCode < LICENSED) : (ListenerUtil.mutListener.listen(73044) ? (responseCode != LICENSED) : (responseCode == LICENSED)))))) || (ListenerUtil.mutListener.listen(73053) ? (responseCode >= NOT_LICENSED) : (ListenerUtil.mutListener.listen(73052) ? (responseCode <= NOT_LICENSED) : (ListenerUtil.mutListener.listen(73051) ? (responseCode > NOT_LICENSED) : (ListenerUtil.mutListener.listen(73050) ? (responseCode < NOT_LICENSED) : (ListenerUtil.mutListener.listen(73049) ? (responseCode != NOT_LICENSED) : (responseCode == NOT_LICENSED)))))))) || (ListenerUtil.mutListener.listen(73059) ? (responseCode >= LICENSED_OLD_KEY) : (ListenerUtil.mutListener.listen(73058) ? (responseCode <= LICENSED_OLD_KEY) : (ListenerUtil.mutListener.listen(73057) ? (responseCode > LICENSED_OLD_KEY) : (ListenerUtil.mutListener.listen(73056) ? (responseCode < LICENSED_OLD_KEY) : (ListenerUtil.mutListener.listen(73055) ? (responseCode != LICENSED_OLD_KEY) : (responseCode == LICENSED_OLD_KEY))))))))) {
                // Verify signature.
                try {
                    if (!ListenerUtil.mutListener.listen(73066)) {
                        if (TextUtils.isEmpty(signedData)) {
                            if (!ListenerUtil.mutListener.listen(73064)) {
                                Log.e(TAG, "Signature verification failed: signedData is empty. " + "(Device not signed-in to any Google accounts?)");
                            }
                            if (!ListenerUtil.mutListener.listen(73065)) {
                                handleInvalidResponse();
                            }
                            return;
                        }
                    }
                    Signature sig = Signature.getInstance(SIGNATURE_ALGORITHM);
                    if (!ListenerUtil.mutListener.listen(73067)) {
                        sig.initVerify(publicKey);
                    }
                    if (!ListenerUtil.mutListener.listen(73068)) {
                        sig.update(signedData.getBytes());
                    }
                    if (!ListenerUtil.mutListener.listen(73071)) {
                        if (!sig.verify(Base64.decode(signature))) {
                            if (!ListenerUtil.mutListener.listen(73069)) {
                                Log.e(TAG, "Signature verification failed.");
                            }
                            if (!ListenerUtil.mutListener.listen(73070)) {
                                handleInvalidResponse();
                            }
                            return;
                        }
                    }
                } catch (NoSuchAlgorithmException e) {
                    // This can't happen on an Android compatible device.
                    throw new RuntimeException(e);
                } catch (InvalidKeyException e) {
                    if (!ListenerUtil.mutListener.listen(73061)) {
                        handleApplicationError(LicenseCheckerCallback.ERROR_INVALID_PUBLIC_KEY);
                    }
                    return;
                } catch (SignatureException e) {
                    throw new RuntimeException(e);
                } catch (Base64DecoderException e) {
                    if (!ListenerUtil.mutListener.listen(73062)) {
                        Log.e(TAG, "Could not Base64-decode signature.");
                    }
                    if (!ListenerUtil.mutListener.listen(73063)) {
                        handleInvalidResponse();
                    }
                    return;
                }
                // Parse and validate response.
                try {
                    if (!ListenerUtil.mutListener.listen(73074)) {
                        data = ResponseData.parse(signedData, signature);
                    }
                } catch (IllegalArgumentException e) {
                    if (!ListenerUtil.mutListener.listen(73072)) {
                        Log.e(TAG, "Could not parse response.");
                    }
                    if (!ListenerUtil.mutListener.listen(73073)) {
                        handleInvalidResponse();
                    }
                    return;
                }
                if (!ListenerUtil.mutListener.listen(73077)) {
                    if (data.responseCode != responseCode) {
                        if (!ListenerUtil.mutListener.listen(73075)) {
                            Log.e(TAG, "Response codes don't match.");
                        }
                        if (!ListenerUtil.mutListener.listen(73076)) {
                            handleInvalidResponse();
                        }
                        return;
                    }
                }
                if (!ListenerUtil.mutListener.listen(73080)) {
                    if (data.nonce != mNonce) {
                        if (!ListenerUtil.mutListener.listen(73078)) {
                            Log.e(TAG, "Nonce doesn't match.");
                        }
                        if (!ListenerUtil.mutListener.listen(73079)) {
                            handleInvalidResponse();
                        }
                        return;
                    }
                }
                if (!ListenerUtil.mutListener.listen(73083)) {
                    if (!data.packageName.equals(mPackageName)) {
                        if (!ListenerUtil.mutListener.listen(73081)) {
                            Log.e(TAG, "Package name doesn't match.");
                        }
                        if (!ListenerUtil.mutListener.listen(73082)) {
                            handleInvalidResponse();
                        }
                        return;
                    }
                }
                if (!ListenerUtil.mutListener.listen(73086)) {
                    if (!data.versionCode.equals(mVersionCode)) {
                        if (!ListenerUtil.mutListener.listen(73084)) {
                            Log.e(TAG, "Version codes don't match.");
                        }
                        if (!ListenerUtil.mutListener.listen(73085)) {
                            handleInvalidResponse();
                        }
                        return;
                    }
                }
                if (!ListenerUtil.mutListener.listen(73087)) {
                    // Application-specific user identifier.
                    userId = data.userId;
                }
                if (!ListenerUtil.mutListener.listen(73090)) {
                    if (TextUtils.isEmpty(userId)) {
                        if (!ListenerUtil.mutListener.listen(73088)) {
                            Log.e(TAG, "User identifier is empty.");
                        }
                        if (!ListenerUtil.mutListener.listen(73089)) {
                            handleInvalidResponse();
                        }
                        return;
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(73105)) {
            switch(responseCode) {
                case LICENSED:
                case LICENSED_OLD_KEY:
                    int limiterResponse = mDeviceLimiter.isDeviceAllowed(userId);
                    if (!ListenerUtil.mutListener.listen(73092)) {
                        handleResponse(limiterResponse, data);
                    }
                    break;
                case NOT_LICENSED:
                    if (!ListenerUtil.mutListener.listen(73093)) {
                        handleResponse(Policy.NOT_LICENSED, data);
                    }
                    break;
                case ERROR_CONTACTING_SERVER:
                    if (!ListenerUtil.mutListener.listen(73094)) {
                        Log.w(TAG, "Error contacting licensing server.");
                    }
                    if (!ListenerUtil.mutListener.listen(73095)) {
                        handleResponse(Policy.RETRY, data);
                    }
                    break;
                case ERROR_SERVER_FAILURE:
                    if (!ListenerUtil.mutListener.listen(73096)) {
                        Log.w(TAG, "An error has occurred on the licensing server.");
                    }
                    if (!ListenerUtil.mutListener.listen(73097)) {
                        handleResponse(Policy.RETRY, data);
                    }
                    break;
                case ERROR_OVER_QUOTA:
                    if (!ListenerUtil.mutListener.listen(73098)) {
                        Log.w(TAG, "Licensing server is refusing to talk to this device, over quota.");
                    }
                    if (!ListenerUtil.mutListener.listen(73099)) {
                        handleResponse(Policy.RETRY, data);
                    }
                    break;
                case ERROR_INVALID_PACKAGE_NAME:
                    if (!ListenerUtil.mutListener.listen(73100)) {
                        handleApplicationError(LicenseCheckerCallback.ERROR_INVALID_PACKAGE_NAME);
                    }
                    break;
                case ERROR_NON_MATCHING_UID:
                    if (!ListenerUtil.mutListener.listen(73101)) {
                        handleApplicationError(LicenseCheckerCallback.ERROR_NON_MATCHING_UID);
                    }
                    break;
                case ERROR_NOT_MARKET_MANAGED:
                    if (!ListenerUtil.mutListener.listen(73102)) {
                        handleApplicationError(LicenseCheckerCallback.ERROR_NOT_MARKET_MANAGED);
                    }
                    break;
                default:
                    if (!ListenerUtil.mutListener.listen(73103)) {
                        Log.e(TAG, "Unknown response code for license check.");
                    }
                    if (!ListenerUtil.mutListener.listen(73104)) {
                        handleInvalidResponse();
                    }
            }
        }
    }

    /**
     * Confers with policy and calls appropriate callback method.
     *
     * @param response
     * @param rawData
     */
    private void handleResponse(int response, ResponseData rawData) {
        if (!ListenerUtil.mutListener.listen(73106)) {
            // Update policy data and increment retry counter (if needed)
            mPolicy.processServerResponse(response, rawData);
        }
        if (!ListenerUtil.mutListener.listen(73109)) {
            // access.
            if (mPolicy.allowAccess()) {
                if (!ListenerUtil.mutListener.listen(73108)) {
                    mCallback.allow(response);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(73107)) {
                    mCallback.dontAllow(response);
                }
            }
        }
    }

    private void handleApplicationError(int code) {
        if (!ListenerUtil.mutListener.listen(73110)) {
            mCallback.applicationError(code);
        }
    }

    private void handleInvalidResponse() {
        if (!ListenerUtil.mutListener.listen(73111)) {
            mCallback.dontAllow(Policy.NOT_LICENSED);
        }
    }
}
