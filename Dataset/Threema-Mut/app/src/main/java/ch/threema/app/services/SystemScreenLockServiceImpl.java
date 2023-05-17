/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
 * Copyright (c) 2016-2021 Threema GmbH
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
package ch.threema.app.services;

import android.app.Activity;
import android.app.KeyguardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.security.keystore.KeyGenParameterSpec;
import android.security.keystore.KeyProperties;
import android.widget.Toast;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.ProviderException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import ch.threema.app.R;
import ch.threema.app.activities.ThreemaActivity;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class SystemScreenLockServiceImpl implements SystemScreenLockService {

    private static final Logger logger = LoggerFactory.getLogger(SystemScreenLockServiceImpl.class);

    /* Alias for our key in the Android Key Store. */
    private static final String ANDROID_KEY_STORE = "AndroidKeyStore";

    private static final String KEY_NAME = "threema_pinlock_key";

    private static final byte[] SECRET_BYTE_ARRAY = new byte[] { 4, 5, 1, 4, 9, 6 };

    private static final int AUTHENTICATION_DURATION_SECONDS = 3;

    private static long lastAuthenticationTimeStamp = 0;

    private KeyguardManager keyguardManager;

    private final LockAppService lockAppService;

    private final PreferenceService preferenceService;

    private final Context context;

    public SystemScreenLockServiceImpl(Context context, LockAppService lockAppService, PreferenceService preferenceService) {
        this.context = context;
        this.lockAppService = lockAppService;
        this.preferenceService = preferenceService;
        if (!ListenerUtil.mutListener.listen(41111)) {
            this.keyguardManager = (KeyguardManager) context.getSystemService(Context.KEYGUARD_SERVICE);
        }
        if (!ListenerUtil.mutListener.listen(41120)) {
            if ((ListenerUtil.mutListener.listen(41116) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(41115) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(41114) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(41113) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(41112) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.M) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M))))))) {
                if (!ListenerUtil.mutListener.listen(41119)) {
                    if ((ListenerUtil.mutListener.listen(41117) ? (keyguardManager != null || keyguardManager.isDeviceSecure()) : (keyguardManager != null && keyguardManager.isDeviceSecure()))) {
                        if (!ListenerUtil.mutListener.listen(41118)) {
                            createKey();
                        }
                    }
                }
            }
        }
    }

    @Override
    public boolean systemUnlock(Activity currentActivity) {
        if (!ListenerUtil.mutListener.listen(41133)) {
            if ((ListenerUtil.mutListener.listen(41125) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(41124) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(41123) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(41122) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(41121) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.M) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M))))))) {
                if (!ListenerUtil.mutListener.listen(41132)) {
                    if (!keyguardManager.isDeviceSecure()) {
                        if (!ListenerUtil.mutListener.listen(41126)) {
                            // User has disabled lockscreen in the meantime. Show a message that the user hasn't set up a lock screen.
                            Toast.makeText(context, R.string.no_lockscreen_set, Toast.LENGTH_LONG).show();
                        }
                        if (!ListenerUtil.mutListener.listen(41131)) {
                            // allow access anyway
                            if (lockAppService != null) {
                                if (!ListenerUtil.mutListener.listen(41127)) {
                                    lockAppService.unlock(null);
                                }
                                if (!ListenerUtil.mutListener.listen(41130)) {
                                    if (preferenceService != null) {
                                        if (!ListenerUtil.mutListener.listen(41128)) {
                                            // disable setting
                                            preferenceService.setLockMechanism(PreferenceService.LockingMech_NONE);
                                        }
                                        if (!ListenerUtil.mutListener.listen(41129)) {
                                            preferenceService.setPrivateChatsHidden(false);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                return tryEncrypt(currentActivity, ThreemaActivity.ACTIVITY_ID_CONFIRM_DEVICE_CREDENTIALS);
            }
        }
        return true;
    }

    @Override
    public void setAuthenticated(boolean authenticated) {
        if (!ListenerUtil.mutListener.listen(41136)) {
            if (authenticated) {
                if (!ListenerUtil.mutListener.listen(41135)) {
                    lastAuthenticationTimeStamp = System.currentTimeMillis();
                }
            } else {
                if (!ListenerUtil.mutListener.listen(41134)) {
                    lastAuthenticationTimeStamp = 0;
                }
            }
        }
    }

    /**
     *  Tries to encrypt some data with the generated key in {@link #createKey} which is
     *  only works if the user has just authenticated via device credentials.
     */
    @Override
    public boolean tryEncrypt(Activity activity, int id) {
        if (!ListenerUtil.mutListener.listen(41166)) {
            if ((ListenerUtil.mutListener.listen(41141) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(41140) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(41139) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(41138) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(41137) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.M) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M))))))) {
                if (!ListenerUtil.mutListener.listen(41165)) {
                    if ((ListenerUtil.mutListener.listen(41154) ? (lastAuthenticationTimeStamp >= (ListenerUtil.mutListener.listen(41149) ? (System.currentTimeMillis() % ((ListenerUtil.mutListener.listen(41145) ? (AUTHENTICATION_DURATION_SECONDS % 1000) : (ListenerUtil.mutListener.listen(41144) ? (AUTHENTICATION_DURATION_SECONDS / 1000) : (ListenerUtil.mutListener.listen(41143) ? (AUTHENTICATION_DURATION_SECONDS - 1000) : (ListenerUtil.mutListener.listen(41142) ? (AUTHENTICATION_DURATION_SECONDS + 1000) : (AUTHENTICATION_DURATION_SECONDS * 1000))))))) : (ListenerUtil.mutListener.listen(41148) ? (System.currentTimeMillis() / ((ListenerUtil.mutListener.listen(41145) ? (AUTHENTICATION_DURATION_SECONDS % 1000) : (ListenerUtil.mutListener.listen(41144) ? (AUTHENTICATION_DURATION_SECONDS / 1000) : (ListenerUtil.mutListener.listen(41143) ? (AUTHENTICATION_DURATION_SECONDS - 1000) : (ListenerUtil.mutListener.listen(41142) ? (AUTHENTICATION_DURATION_SECONDS + 1000) : (AUTHENTICATION_DURATION_SECONDS * 1000))))))) : (ListenerUtil.mutListener.listen(41147) ? (System.currentTimeMillis() * ((ListenerUtil.mutListener.listen(41145) ? (AUTHENTICATION_DURATION_SECONDS % 1000) : (ListenerUtil.mutListener.listen(41144) ? (AUTHENTICATION_DURATION_SECONDS / 1000) : (ListenerUtil.mutListener.listen(41143) ? (AUTHENTICATION_DURATION_SECONDS - 1000) : (ListenerUtil.mutListener.listen(41142) ? (AUTHENTICATION_DURATION_SECONDS + 1000) : (AUTHENTICATION_DURATION_SECONDS * 1000))))))) : (ListenerUtil.mutListener.listen(41146) ? (System.currentTimeMillis() + ((ListenerUtil.mutListener.listen(41145) ? (AUTHENTICATION_DURATION_SECONDS % 1000) : (ListenerUtil.mutListener.listen(41144) ? (AUTHENTICATION_DURATION_SECONDS / 1000) : (ListenerUtil.mutListener.listen(41143) ? (AUTHENTICATION_DURATION_SECONDS - 1000) : (ListenerUtil.mutListener.listen(41142) ? (AUTHENTICATION_DURATION_SECONDS + 1000) : (AUTHENTICATION_DURATION_SECONDS * 1000))))))) : (System.currentTimeMillis() - ((ListenerUtil.mutListener.listen(41145) ? (AUTHENTICATION_DURATION_SECONDS % 1000) : (ListenerUtil.mutListener.listen(41144) ? (AUTHENTICATION_DURATION_SECONDS / 1000) : (ListenerUtil.mutListener.listen(41143) ? (AUTHENTICATION_DURATION_SECONDS - 1000) : (ListenerUtil.mutListener.listen(41142) ? (AUTHENTICATION_DURATION_SECONDS + 1000) : (AUTHENTICATION_DURATION_SECONDS * 1000)))))))))))) : (ListenerUtil.mutListener.listen(41153) ? (lastAuthenticationTimeStamp <= (ListenerUtil.mutListener.listen(41149) ? (System.currentTimeMillis() % ((ListenerUtil.mutListener.listen(41145) ? (AUTHENTICATION_DURATION_SECONDS % 1000) : (ListenerUtil.mutListener.listen(41144) ? (AUTHENTICATION_DURATION_SECONDS / 1000) : (ListenerUtil.mutListener.listen(41143) ? (AUTHENTICATION_DURATION_SECONDS - 1000) : (ListenerUtil.mutListener.listen(41142) ? (AUTHENTICATION_DURATION_SECONDS + 1000) : (AUTHENTICATION_DURATION_SECONDS * 1000))))))) : (ListenerUtil.mutListener.listen(41148) ? (System.currentTimeMillis() / ((ListenerUtil.mutListener.listen(41145) ? (AUTHENTICATION_DURATION_SECONDS % 1000) : (ListenerUtil.mutListener.listen(41144) ? (AUTHENTICATION_DURATION_SECONDS / 1000) : (ListenerUtil.mutListener.listen(41143) ? (AUTHENTICATION_DURATION_SECONDS - 1000) : (ListenerUtil.mutListener.listen(41142) ? (AUTHENTICATION_DURATION_SECONDS + 1000) : (AUTHENTICATION_DURATION_SECONDS * 1000))))))) : (ListenerUtil.mutListener.listen(41147) ? (System.currentTimeMillis() * ((ListenerUtil.mutListener.listen(41145) ? (AUTHENTICATION_DURATION_SECONDS % 1000) : (ListenerUtil.mutListener.listen(41144) ? (AUTHENTICATION_DURATION_SECONDS / 1000) : (ListenerUtil.mutListener.listen(41143) ? (AUTHENTICATION_DURATION_SECONDS - 1000) : (ListenerUtil.mutListener.listen(41142) ? (AUTHENTICATION_DURATION_SECONDS + 1000) : (AUTHENTICATION_DURATION_SECONDS * 1000))))))) : (ListenerUtil.mutListener.listen(41146) ? (System.currentTimeMillis() + ((ListenerUtil.mutListener.listen(41145) ? (AUTHENTICATION_DURATION_SECONDS % 1000) : (ListenerUtil.mutListener.listen(41144) ? (AUTHENTICATION_DURATION_SECONDS / 1000) : (ListenerUtil.mutListener.listen(41143) ? (AUTHENTICATION_DURATION_SECONDS - 1000) : (ListenerUtil.mutListener.listen(41142) ? (AUTHENTICATION_DURATION_SECONDS + 1000) : (AUTHENTICATION_DURATION_SECONDS * 1000))))))) : (System.currentTimeMillis() - ((ListenerUtil.mutListener.listen(41145) ? (AUTHENTICATION_DURATION_SECONDS % 1000) : (ListenerUtil.mutListener.listen(41144) ? (AUTHENTICATION_DURATION_SECONDS / 1000) : (ListenerUtil.mutListener.listen(41143) ? (AUTHENTICATION_DURATION_SECONDS - 1000) : (ListenerUtil.mutListener.listen(41142) ? (AUTHENTICATION_DURATION_SECONDS + 1000) : (AUTHENTICATION_DURATION_SECONDS * 1000)))))))))))) : (ListenerUtil.mutListener.listen(41152) ? (lastAuthenticationTimeStamp > (ListenerUtil.mutListener.listen(41149) ? (System.currentTimeMillis() % ((ListenerUtil.mutListener.listen(41145) ? (AUTHENTICATION_DURATION_SECONDS % 1000) : (ListenerUtil.mutListener.listen(41144) ? (AUTHENTICATION_DURATION_SECONDS / 1000) : (ListenerUtil.mutListener.listen(41143) ? (AUTHENTICATION_DURATION_SECONDS - 1000) : (ListenerUtil.mutListener.listen(41142) ? (AUTHENTICATION_DURATION_SECONDS + 1000) : (AUTHENTICATION_DURATION_SECONDS * 1000))))))) : (ListenerUtil.mutListener.listen(41148) ? (System.currentTimeMillis() / ((ListenerUtil.mutListener.listen(41145) ? (AUTHENTICATION_DURATION_SECONDS % 1000) : (ListenerUtil.mutListener.listen(41144) ? (AUTHENTICATION_DURATION_SECONDS / 1000) : (ListenerUtil.mutListener.listen(41143) ? (AUTHENTICATION_DURATION_SECONDS - 1000) : (ListenerUtil.mutListener.listen(41142) ? (AUTHENTICATION_DURATION_SECONDS + 1000) : (AUTHENTICATION_DURATION_SECONDS * 1000))))))) : (ListenerUtil.mutListener.listen(41147) ? (System.currentTimeMillis() * ((ListenerUtil.mutListener.listen(41145) ? (AUTHENTICATION_DURATION_SECONDS % 1000) : (ListenerUtil.mutListener.listen(41144) ? (AUTHENTICATION_DURATION_SECONDS / 1000) : (ListenerUtil.mutListener.listen(41143) ? (AUTHENTICATION_DURATION_SECONDS - 1000) : (ListenerUtil.mutListener.listen(41142) ? (AUTHENTICATION_DURATION_SECONDS + 1000) : (AUTHENTICATION_DURATION_SECONDS * 1000))))))) : (ListenerUtil.mutListener.listen(41146) ? (System.currentTimeMillis() + ((ListenerUtil.mutListener.listen(41145) ? (AUTHENTICATION_DURATION_SECONDS % 1000) : (ListenerUtil.mutListener.listen(41144) ? (AUTHENTICATION_DURATION_SECONDS / 1000) : (ListenerUtil.mutListener.listen(41143) ? (AUTHENTICATION_DURATION_SECONDS - 1000) : (ListenerUtil.mutListener.listen(41142) ? (AUTHENTICATION_DURATION_SECONDS + 1000) : (AUTHENTICATION_DURATION_SECONDS * 1000))))))) : (System.currentTimeMillis() - ((ListenerUtil.mutListener.listen(41145) ? (AUTHENTICATION_DURATION_SECONDS % 1000) : (ListenerUtil.mutListener.listen(41144) ? (AUTHENTICATION_DURATION_SECONDS / 1000) : (ListenerUtil.mutListener.listen(41143) ? (AUTHENTICATION_DURATION_SECONDS - 1000) : (ListenerUtil.mutListener.listen(41142) ? (AUTHENTICATION_DURATION_SECONDS + 1000) : (AUTHENTICATION_DURATION_SECONDS * 1000)))))))))))) : (ListenerUtil.mutListener.listen(41151) ? (lastAuthenticationTimeStamp != (ListenerUtil.mutListener.listen(41149) ? (System.currentTimeMillis() % ((ListenerUtil.mutListener.listen(41145) ? (AUTHENTICATION_DURATION_SECONDS % 1000) : (ListenerUtil.mutListener.listen(41144) ? (AUTHENTICATION_DURATION_SECONDS / 1000) : (ListenerUtil.mutListener.listen(41143) ? (AUTHENTICATION_DURATION_SECONDS - 1000) : (ListenerUtil.mutListener.listen(41142) ? (AUTHENTICATION_DURATION_SECONDS + 1000) : (AUTHENTICATION_DURATION_SECONDS * 1000))))))) : (ListenerUtil.mutListener.listen(41148) ? (System.currentTimeMillis() / ((ListenerUtil.mutListener.listen(41145) ? (AUTHENTICATION_DURATION_SECONDS % 1000) : (ListenerUtil.mutListener.listen(41144) ? (AUTHENTICATION_DURATION_SECONDS / 1000) : (ListenerUtil.mutListener.listen(41143) ? (AUTHENTICATION_DURATION_SECONDS - 1000) : (ListenerUtil.mutListener.listen(41142) ? (AUTHENTICATION_DURATION_SECONDS + 1000) : (AUTHENTICATION_DURATION_SECONDS * 1000))))))) : (ListenerUtil.mutListener.listen(41147) ? (System.currentTimeMillis() * ((ListenerUtil.mutListener.listen(41145) ? (AUTHENTICATION_DURATION_SECONDS % 1000) : (ListenerUtil.mutListener.listen(41144) ? (AUTHENTICATION_DURATION_SECONDS / 1000) : (ListenerUtil.mutListener.listen(41143) ? (AUTHENTICATION_DURATION_SECONDS - 1000) : (ListenerUtil.mutListener.listen(41142) ? (AUTHENTICATION_DURATION_SECONDS + 1000) : (AUTHENTICATION_DURATION_SECONDS * 1000))))))) : (ListenerUtil.mutListener.listen(41146) ? (System.currentTimeMillis() + ((ListenerUtil.mutListener.listen(41145) ? (AUTHENTICATION_DURATION_SECONDS % 1000) : (ListenerUtil.mutListener.listen(41144) ? (AUTHENTICATION_DURATION_SECONDS / 1000) : (ListenerUtil.mutListener.listen(41143) ? (AUTHENTICATION_DURATION_SECONDS - 1000) : (ListenerUtil.mutListener.listen(41142) ? (AUTHENTICATION_DURATION_SECONDS + 1000) : (AUTHENTICATION_DURATION_SECONDS * 1000))))))) : (System.currentTimeMillis() - ((ListenerUtil.mutListener.listen(41145) ? (AUTHENTICATION_DURATION_SECONDS % 1000) : (ListenerUtil.mutListener.listen(41144) ? (AUTHENTICATION_DURATION_SECONDS / 1000) : (ListenerUtil.mutListener.listen(41143) ? (AUTHENTICATION_DURATION_SECONDS - 1000) : (ListenerUtil.mutListener.listen(41142) ? (AUTHENTICATION_DURATION_SECONDS + 1000) : (AUTHENTICATION_DURATION_SECONDS * 1000)))))))))))) : (ListenerUtil.mutListener.listen(41150) ? (lastAuthenticationTimeStamp == (ListenerUtil.mutListener.listen(41149) ? (System.currentTimeMillis() % ((ListenerUtil.mutListener.listen(41145) ? (AUTHENTICATION_DURATION_SECONDS % 1000) : (ListenerUtil.mutListener.listen(41144) ? (AUTHENTICATION_DURATION_SECONDS / 1000) : (ListenerUtil.mutListener.listen(41143) ? (AUTHENTICATION_DURATION_SECONDS - 1000) : (ListenerUtil.mutListener.listen(41142) ? (AUTHENTICATION_DURATION_SECONDS + 1000) : (AUTHENTICATION_DURATION_SECONDS * 1000))))))) : (ListenerUtil.mutListener.listen(41148) ? (System.currentTimeMillis() / ((ListenerUtil.mutListener.listen(41145) ? (AUTHENTICATION_DURATION_SECONDS % 1000) : (ListenerUtil.mutListener.listen(41144) ? (AUTHENTICATION_DURATION_SECONDS / 1000) : (ListenerUtil.mutListener.listen(41143) ? (AUTHENTICATION_DURATION_SECONDS - 1000) : (ListenerUtil.mutListener.listen(41142) ? (AUTHENTICATION_DURATION_SECONDS + 1000) : (AUTHENTICATION_DURATION_SECONDS * 1000))))))) : (ListenerUtil.mutListener.listen(41147) ? (System.currentTimeMillis() * ((ListenerUtil.mutListener.listen(41145) ? (AUTHENTICATION_DURATION_SECONDS % 1000) : (ListenerUtil.mutListener.listen(41144) ? (AUTHENTICATION_DURATION_SECONDS / 1000) : (ListenerUtil.mutListener.listen(41143) ? (AUTHENTICATION_DURATION_SECONDS - 1000) : (ListenerUtil.mutListener.listen(41142) ? (AUTHENTICATION_DURATION_SECONDS + 1000) : (AUTHENTICATION_DURATION_SECONDS * 1000))))))) : (ListenerUtil.mutListener.listen(41146) ? (System.currentTimeMillis() + ((ListenerUtil.mutListener.listen(41145) ? (AUTHENTICATION_DURATION_SECONDS % 1000) : (ListenerUtil.mutListener.listen(41144) ? (AUTHENTICATION_DURATION_SECONDS / 1000) : (ListenerUtil.mutListener.listen(41143) ? (AUTHENTICATION_DURATION_SECONDS - 1000) : (ListenerUtil.mutListener.listen(41142) ? (AUTHENTICATION_DURATION_SECONDS + 1000) : (AUTHENTICATION_DURATION_SECONDS * 1000))))))) : (System.currentTimeMillis() - ((ListenerUtil.mutListener.listen(41145) ? (AUTHENTICATION_DURATION_SECONDS % 1000) : (ListenerUtil.mutListener.listen(41144) ? (AUTHENTICATION_DURATION_SECONDS / 1000) : (ListenerUtil.mutListener.listen(41143) ? (AUTHENTICATION_DURATION_SECONDS - 1000) : (ListenerUtil.mutListener.listen(41142) ? (AUTHENTICATION_DURATION_SECONDS + 1000) : (AUTHENTICATION_DURATION_SECONDS * 1000)))))))))))) : (lastAuthenticationTimeStamp < (ListenerUtil.mutListener.listen(41149) ? (System.currentTimeMillis() % ((ListenerUtil.mutListener.listen(41145) ? (AUTHENTICATION_DURATION_SECONDS % 1000) : (ListenerUtil.mutListener.listen(41144) ? (AUTHENTICATION_DURATION_SECONDS / 1000) : (ListenerUtil.mutListener.listen(41143) ? (AUTHENTICATION_DURATION_SECONDS - 1000) : (ListenerUtil.mutListener.listen(41142) ? (AUTHENTICATION_DURATION_SECONDS + 1000) : (AUTHENTICATION_DURATION_SECONDS * 1000))))))) : (ListenerUtil.mutListener.listen(41148) ? (System.currentTimeMillis() / ((ListenerUtil.mutListener.listen(41145) ? (AUTHENTICATION_DURATION_SECONDS % 1000) : (ListenerUtil.mutListener.listen(41144) ? (AUTHENTICATION_DURATION_SECONDS / 1000) : (ListenerUtil.mutListener.listen(41143) ? (AUTHENTICATION_DURATION_SECONDS - 1000) : (ListenerUtil.mutListener.listen(41142) ? (AUTHENTICATION_DURATION_SECONDS + 1000) : (AUTHENTICATION_DURATION_SECONDS * 1000))))))) : (ListenerUtil.mutListener.listen(41147) ? (System.currentTimeMillis() * ((ListenerUtil.mutListener.listen(41145) ? (AUTHENTICATION_DURATION_SECONDS % 1000) : (ListenerUtil.mutListener.listen(41144) ? (AUTHENTICATION_DURATION_SECONDS / 1000) : (ListenerUtil.mutListener.listen(41143) ? (AUTHENTICATION_DURATION_SECONDS - 1000) : (ListenerUtil.mutListener.listen(41142) ? (AUTHENTICATION_DURATION_SECONDS + 1000) : (AUTHENTICATION_DURATION_SECONDS * 1000))))))) : (ListenerUtil.mutListener.listen(41146) ? (System.currentTimeMillis() + ((ListenerUtil.mutListener.listen(41145) ? (AUTHENTICATION_DURATION_SECONDS % 1000) : (ListenerUtil.mutListener.listen(41144) ? (AUTHENTICATION_DURATION_SECONDS / 1000) : (ListenerUtil.mutListener.listen(41143) ? (AUTHENTICATION_DURATION_SECONDS - 1000) : (ListenerUtil.mutListener.listen(41142) ? (AUTHENTICATION_DURATION_SECONDS + 1000) : (AUTHENTICATION_DURATION_SECONDS * 1000))))))) : (System.currentTimeMillis() - ((ListenerUtil.mutListener.listen(41145) ? (AUTHENTICATION_DURATION_SECONDS % 1000) : (ListenerUtil.mutListener.listen(41144) ? (AUTHENTICATION_DURATION_SECONDS / 1000) : (ListenerUtil.mutListener.listen(41143) ? (AUTHENTICATION_DURATION_SECONDS - 1000) : (ListenerUtil.mutListener.listen(41142) ? (AUTHENTICATION_DURATION_SECONDS + 1000) : (AUTHENTICATION_DURATION_SECONDS * 1000)))))))))))))))))) {
                        try {
                            KeyStore keyStore = KeyStore.getInstance(ANDROID_KEY_STORE);
                            if (!ListenerUtil.mutListener.listen(41161)) {
                                keyStore.load(null);
                            }
                            SecretKey secretKey = (SecretKey) keyStore.getKey(KEY_NAME, null);
                            Cipher cipher = Cipher.getInstance(KeyProperties.KEY_ALGORITHM_AES + "/" + KeyProperties.BLOCK_MODE_CBC + "/" + KeyProperties.ENCRYPTION_PADDING_PKCS7);
                            if (!ListenerUtil.mutListener.listen(41162)) {
                                // the last AUTHENTICATION_DURATION_SECONDS seconds.
                                cipher.init(Cipher.ENCRYPT_MODE, secretKey);
                            }
                            if (!ListenerUtil.mutListener.listen(41163)) {
                                cipher.doFinal(SECRET_BYTE_ARRAY);
                            }
                            if (!ListenerUtil.mutListener.listen(41164)) {
                                // If the user has recently authenticated, you will reach here.
                                showAlreadyAuthenticated();
                            }
                            return true;
                        } catch (UnrecoverableKeyException e) {
                            if (!ListenerUtil.mutListener.listen(41156)) {
                                // java.security.UnrecoverableKeyException: Failed to obtain information about key on OnePlus phone
                                Toast.makeText(activity, "Error in Android Key Store implementation. Please contact your phone manufacturer and try again later", Toast.LENGTH_LONG).show();
                            }
                            if (!ListenerUtil.mutListener.listen(41157)) {
                                logger.error("Exception", e);
                            }
                        } catch (InvalidKeyException e) {
                            if (!ListenerUtil.mutListener.listen(41158)) {
                                // User is not authenticated, let's authenticate with device credentials.
                                showAuthenticationScreen(activity, id);
                            }
                        } catch (BadPaddingException e) {
                            if (!ListenerUtil.mutListener.listen(41159)) {
                                showAuthenticationScreen(activity, id);
                            }
                        } catch (IllegalBlockSizeException | KeyStoreException | CertificateException | IOException | NoSuchPaddingException | NoSuchAlgorithmException e) {
                            if (!ListenerUtil.mutListener.listen(41160)) {
                                logger.error("Exception", e);
                            }
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(41155)) {
                            showAlreadyAuthenticated();
                        }
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     *  Creates a symmetric key in the Android Key Store which can only be used after the user has
     *  authenticated with device credentials within the last X seconds.
     */
    private boolean createKey() {
        if (!ListenerUtil.mutListener.listen(41176)) {
            if ((ListenerUtil.mutListener.listen(41171) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(41170) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(41169) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(41168) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(41167) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.M) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M))))))) {
                // This will most likely be a registration step for the user when they are setting up your app.
                try {
                    KeyStore keyStore = KeyStore.getInstance(ANDROID_KEY_STORE);
                    if (!ListenerUtil.mutListener.listen(41173)) {
                        keyStore.load(null);
                    }
                    KeyGenerator keyGenerator = KeyGenerator.getInstance(KeyProperties.KEY_ALGORITHM_AES, ANDROID_KEY_STORE);
                    if (!ListenerUtil.mutListener.listen(41174)) {
                        // and the constrains (purposes) in the constructor of the Builder
                        keyGenerator.init(new KeyGenParameterSpec.Builder(KEY_NAME, KeyProperties.PURPOSE_ENCRYPT | KeyProperties.PURPOSE_DECRYPT).setBlockModes(KeyProperties.BLOCK_MODE_CBC).setUserAuthenticationRequired(true).setUserAuthenticationValidityDurationSeconds(AUTHENTICATION_DURATION_SECONDS).setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_PKCS7).build());
                    }
                    if (!ListenerUtil.mutListener.listen(41175)) {
                        keyGenerator.generateKey();
                    }
                } catch (NoSuchAlgorithmException | NoSuchProviderException | InvalidAlgorithmParameterException | KeyStoreException | CertificateException | IOException | ProviderException e) {
                    if (!ListenerUtil.mutListener.listen(41172)) {
                        logger.error("Exception", e);
                    }
                    return false;
                }
            }
        }
        return true;
    }

    private void showAuthenticationScreen(Activity activity, int id) {
        if (!ListenerUtil.mutListener.listen(41177)) {
            logger.debug("showAuthenticationScreen");
        }
        if (!ListenerUtil.mutListener.listen(41186)) {
            if ((ListenerUtil.mutListener.listen(41182) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(41181) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(41180) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(41179) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(41178) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.M) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M))))))) {
                // we will provide a generic one for you if you leave it null
                Intent intent = keyguardManager.createConfirmDeviceCredentialIntent(null, null);
                if (!ListenerUtil.mutListener.listen(41185)) {
                    if (intent != null) {
                        if (!ListenerUtil.mutListener.listen(41183)) {
                            activity.startActivityForResult(intent, id);
                        }
                        if (!ListenerUtil.mutListener.listen(41184)) {
                            activity.overridePendingTransition(0, 0);
                        }
                    }
                }
            }
        }
    }

    private void showAlreadyAuthenticated() {
        if (!ListenerUtil.mutListener.listen(41187)) {
            logger.debug("AlreadyAuthenticated");
        }
        if (!ListenerUtil.mutListener.listen(41189)) {
            if (lockAppService != null) {
                if (!ListenerUtil.mutListener.listen(41188)) {
                    lockAppService.unlock(null);
                }
            }
        }
    }
}
