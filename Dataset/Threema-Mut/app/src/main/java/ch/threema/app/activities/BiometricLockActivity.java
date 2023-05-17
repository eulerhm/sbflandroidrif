/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
 * Copyright (c) 2019-2021 Threema GmbH
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
package ch.threema.app.activities;

import android.app.Activity;
import android.app.KeyguardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.biometric.BiometricPrompt;
import ch.threema.app.R;
import ch.threema.app.ThreemaApplication;
import ch.threema.app.managers.ServiceManager;
import ch.threema.app.services.LockAppService;
import ch.threema.app.services.PreferenceService;
import ch.threema.app.services.SystemScreenLockService;
import ch.threema.app.utils.BiometricUtil;
import ch.threema.app.utils.ConfigUtils;
import ch.threema.app.utils.NavigationUtil;
import ch.threema.app.utils.RuntimeUtil;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class BiometricLockActivity extends ThreemaAppCompatActivity {

    private static final Logger logger = LoggerFactory.getLogger(BiometricLockActivity.class);

    private static final int REQUEST_CODE_SYSTEM_SCREENLOCK_CHECK = 551;

    public static final String INTENT_DATA_AUTHENTICATION_TYPE = "auth_type";

    private LockAppService lockAppService;

    private PreferenceService preferenceService;

    private SystemScreenLockService systemScreenLockService;

    private boolean isCheckOnly = false;

    private String authenticationType = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(1853)) {
            logger.debug("onCreate");
        }
        if (!ListenerUtil.mutListener.listen(1855)) {
            if (ConfigUtils.getAppTheme(this) == ConfigUtils.THEME_DARK) {
                if (!ListenerUtil.mutListener.listen(1854)) {
                    setTheme(R.style.Theme_Threema_BiometricUnlock_Dark);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(1856)) {
            super.onCreate(savedInstanceState);
        }
        ServiceManager serviceManager = ThreemaApplication.getServiceManager();
        if (!ListenerUtil.mutListener.listen(1858)) {
            if (serviceManager == null) {
                if (!ListenerUtil.mutListener.listen(1857)) {
                    finish();
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(1859)) {
            preferenceService = serviceManager.getPreferenceService();
        }
        if (!ListenerUtil.mutListener.listen(1860)) {
            lockAppService = serviceManager.getLockAppService();
        }
        if (!ListenerUtil.mutListener.listen(1861)) {
            systemScreenLockService = serviceManager.getScreenLockService();
        }
        if (!ListenerUtil.mutListener.listen(1862)) {
            setContentView(R.layout.activity_biometric_lock);
        }
        if (!ListenerUtil.mutListener.listen(1863)) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_SECURE);
        }
        if (!ListenerUtil.mutListener.listen(1864)) {
            isCheckOnly = getIntent().getBooleanExtra(ThreemaApplication.INTENT_DATA_CHECK_ONLY, false);
        }
        if (!ListenerUtil.mutListener.listen(1866)) {
            if (getIntent().hasExtra(INTENT_DATA_AUTHENTICATION_TYPE)) {
                if (!ListenerUtil.mutListener.listen(1865)) {
                    authenticationType = getIntent().getStringExtra(INTENT_DATA_AUTHENTICATION_TYPE);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(1868)) {
            if (authenticationType == null) {
                if (!ListenerUtil.mutListener.listen(1867)) {
                    authenticationType = preferenceService.getLockMechanism();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(1871)) {
            if ((ListenerUtil.mutListener.listen(1869) ? (!lockAppService.isLocked() || !isCheckOnly) : (!lockAppService.isLocked() && !isCheckOnly))) {
                if (!ListenerUtil.mutListener.listen(1870)) {
                    finish();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(1876)) {
            switch(authenticationType) {
                case PreferenceService.LockingMech_SYSTEM:
                    if (!ListenerUtil.mutListener.listen(1872)) {
                        showSystemScreenLock();
                    }
                    break;
                case PreferenceService.LockingMech_BIOMETRIC:
                    if (!ListenerUtil.mutListener.listen(1875)) {
                        if (BiometricUtil.isBiometricsSupported(this)) {
                            if (!ListenerUtil.mutListener.listen(1874)) {
                                showBiometricPrompt();
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(1873)) {
                                // no enrolled fingerprints - try system screen lock
                                showSystemScreenLock();
                            }
                        }
                    }
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public void finish() {
        if (!ListenerUtil.mutListener.listen(1877)) {
            logger.debug("finish");
        }
        try {
            if (!ListenerUtil.mutListener.listen(1878)) {
                super.finish();
            }
            if (!ListenerUtil.mutListener.listen(1879)) {
                overridePendingTransition(0, 0);
            }
        } catch (Exception ignored) {
        }
    }

    private void showBiometricPrompt() {
        KeyguardManager keyguardManager = (KeyguardManager) getSystemService(Context.KEYGUARD_SERVICE);
        BiometricPrompt.PromptInfo.Builder promptInfoBuilder = new BiometricPrompt.PromptInfo.Builder().setTitle(getString(R.string.prefs_title_access_protection)).setSubtitle(getString(R.string.biometric_enter_authentication)).setConfirmationRequired(false);
        if (!ListenerUtil.mutListener.listen(1889)) {
            if ((ListenerUtil.mutListener.listen(1886) ? ((ListenerUtil.mutListener.listen(1885) ? ((ListenerUtil.mutListener.listen(1884) ? (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) : (ListenerUtil.mutListener.listen(1883) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.P) : (ListenerUtil.mutListener.listen(1882) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.P) : (ListenerUtil.mutListener.listen(1881) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.P) : (ListenerUtil.mutListener.listen(1880) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.P) : (Build.VERSION.SDK_INT > Build.VERSION_CODES.P)))))) || keyguardManager != null) : ((ListenerUtil.mutListener.listen(1884) ? (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) : (ListenerUtil.mutListener.listen(1883) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.P) : (ListenerUtil.mutListener.listen(1882) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.P) : (ListenerUtil.mutListener.listen(1881) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.P) : (ListenerUtil.mutListener.listen(1880) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.P) : (Build.VERSION.SDK_INT > Build.VERSION_CODES.P)))))) && keyguardManager != null)) || keyguardManager.isDeviceSecure()) : ((ListenerUtil.mutListener.listen(1885) ? ((ListenerUtil.mutListener.listen(1884) ? (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) : (ListenerUtil.mutListener.listen(1883) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.P) : (ListenerUtil.mutListener.listen(1882) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.P) : (ListenerUtil.mutListener.listen(1881) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.P) : (ListenerUtil.mutListener.listen(1880) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.P) : (Build.VERSION.SDK_INT > Build.VERSION_CODES.P)))))) || keyguardManager != null) : ((ListenerUtil.mutListener.listen(1884) ? (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) : (ListenerUtil.mutListener.listen(1883) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.P) : (ListenerUtil.mutListener.listen(1882) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.P) : (ListenerUtil.mutListener.listen(1881) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.P) : (ListenerUtil.mutListener.listen(1880) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.P) : (Build.VERSION.SDK_INT > Build.VERSION_CODES.P)))))) && keyguardManager != null)) && keyguardManager.isDeviceSecure()))) {
                if (!ListenerUtil.mutListener.listen(1888)) {
                    // allow fallback to device credentials such as PIN, passphrase or pattern
                    promptInfoBuilder.setDeviceCredentialAllowed(true);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(1887)) {
                    promptInfoBuilder.setNegativeButtonText(getString(R.string.cancel));
                }
            }
        }
        BiometricPrompt.PromptInfo promptInfo = promptInfoBuilder.build();
        BiometricPrompt biometricPrompt = new BiometricPrompt(this, new RuntimeUtil.MainThreadExecutor(), new BiometricPrompt.AuthenticationCallback() {

            @Override
            public void onAuthenticationError(int errorCode, @NonNull CharSequence errString) {
                if (!ListenerUtil.mutListener.listen(1890)) {
                    super.onAuthenticationError(errorCode, errString);
                }
                if (!ListenerUtil.mutListener.listen(1893)) {
                    if ((ListenerUtil.mutListener.listen(1891) ? (errorCode != BiometricPrompt.ERROR_USER_CANCELED || errorCode != BiometricPrompt.ERROR_NEGATIVE_BUTTON) : (errorCode != BiometricPrompt.ERROR_USER_CANCELED && errorCode != BiometricPrompt.ERROR_NEGATIVE_BUTTON))) {
                        if (!ListenerUtil.mutListener.listen(1892)) {
                            Toast.makeText(BiometricLockActivity.this, errString + " (" + errorCode + ")", Toast.LENGTH_LONG).show();
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(1894)) {
                    BiometricLockActivity.this.onAuthenticationFailed();
                }
            }

            @Override
            public void onAuthenticationSucceeded(@NonNull BiometricPrompt.AuthenticationResult result) {
                if (!ListenerUtil.mutListener.listen(1895)) {
                    super.onAuthenticationSucceeded(result);
                }
                if (!ListenerUtil.mutListener.listen(1896)) {
                    BiometricLockActivity.this.onAuthenticationSuccess();
                }
            }

            @Override
            public void onAuthenticationFailed() {
                if (!ListenerUtil.mutListener.listen(1897)) {
                    super.onAuthenticationFailed();
                }
                if (!ListenerUtil.mutListener.listen(1898)) {
                    BiometricLockActivity.this.onAuthenticationFailed();
                }
            }
        });
        if (!ListenerUtil.mutListener.listen(1899)) {
            biometricPrompt.authenticate(promptInfo);
        }
    }

    private void showSystemScreenLock() {
        if (!ListenerUtil.mutListener.listen(1900)) {
            logger.debug("showSystemScreenLock");
        }
        if (!ListenerUtil.mutListener.listen(1905)) {
            if (isCheckOnly) {
                if (!ListenerUtil.mutListener.listen(1904)) {
                    if (systemScreenLockService.tryEncrypt(this, REQUEST_CODE_SYSTEM_SCREENLOCK_CHECK)) {
                        if (!ListenerUtil.mutListener.listen(1903)) {
                            onAuthenticationSuccess();
                        }
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(1902)) {
                    if (systemScreenLockService.systemUnlock(this)) {
                        if (!ListenerUtil.mutListener.listen(1901)) {
                            onAuthenticationSuccess();
                        }
                    }
                }
            }
        }
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        if (!ListenerUtil.mutListener.listen(1906)) {
            super.onWindowFocusChanged(hasFocus);
        }
        if (!ListenerUtil.mutListener.listen(1908)) {
            if (hasFocus) {
                if (!ListenerUtil.mutListener.listen(1907)) {
                    getWindow().getDecorView().setSystemUiVisibility(// content doesn't resize when the system bars hide and show.
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_FULLSCREEN);
                }
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (!ListenerUtil.mutListener.listen(1909)) {
            logger.debug("onActivityResult requestCode: " + requestCode + " result: " + resultCode);
        }
        if (!ListenerUtil.mutListener.listen(1910)) {
            super.onActivityResult(requestCode, resultCode, data);
        }
        if (!ListenerUtil.mutListener.listen(1920)) {
            if ((ListenerUtil.mutListener.listen(1916) ? (requestCode == ThreemaActivity.ACTIVITY_ID_CONFIRM_DEVICE_CREDENTIALS && (ListenerUtil.mutListener.listen(1915) ? (requestCode >= REQUEST_CODE_SYSTEM_SCREENLOCK_CHECK) : (ListenerUtil.mutListener.listen(1914) ? (requestCode <= REQUEST_CODE_SYSTEM_SCREENLOCK_CHECK) : (ListenerUtil.mutListener.listen(1913) ? (requestCode > REQUEST_CODE_SYSTEM_SCREENLOCK_CHECK) : (ListenerUtil.mutListener.listen(1912) ? (requestCode < REQUEST_CODE_SYSTEM_SCREENLOCK_CHECK) : (ListenerUtil.mutListener.listen(1911) ? (requestCode != REQUEST_CODE_SYSTEM_SCREENLOCK_CHECK) : (requestCode == REQUEST_CODE_SYSTEM_SCREENLOCK_CHECK))))))) : (requestCode == ThreemaActivity.ACTIVITY_ID_CONFIRM_DEVICE_CREDENTIALS || (ListenerUtil.mutListener.listen(1915) ? (requestCode >= REQUEST_CODE_SYSTEM_SCREENLOCK_CHECK) : (ListenerUtil.mutListener.listen(1914) ? (requestCode <= REQUEST_CODE_SYSTEM_SCREENLOCK_CHECK) : (ListenerUtil.mutListener.listen(1913) ? (requestCode > REQUEST_CODE_SYSTEM_SCREENLOCK_CHECK) : (ListenerUtil.mutListener.listen(1912) ? (requestCode < REQUEST_CODE_SYSTEM_SCREENLOCK_CHECK) : (ListenerUtil.mutListener.listen(1911) ? (requestCode != REQUEST_CODE_SYSTEM_SCREENLOCK_CHECK) : (requestCode == REQUEST_CODE_SYSTEM_SCREENLOCK_CHECK))))))))) {
                if (!ListenerUtil.mutListener.listen(1919)) {
                    // Challenge completed, proceed with using cipher
                    if (resultCode != Activity.RESULT_CANCELED) {
                        if (!ListenerUtil.mutListener.listen(1918)) {
                            onAuthenticationSuccess();
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(1917)) {
                            // The user canceled or didn’t complete the lock screen
                            onAuthenticationFailed();
                        }
                    }
                }
            }
        }
    }

    private void onAuthenticationSuccess() {
        if (!ListenerUtil.mutListener.listen(1921)) {
            logger.debug("Authentication successful");
        }
        if (!ListenerUtil.mutListener.listen(1923)) {
            if (!isCheckOnly) {
                if (!ListenerUtil.mutListener.listen(1922)) {
                    lockAppService.unlock(null);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(1924)) {
            this.setResult(RESULT_OK);
        }
        if (!ListenerUtil.mutListener.listen(1925)) {
            this.finish();
        }
    }

    private void onAuthenticationFailed() {
        if (!ListenerUtil.mutListener.listen(1926)) {
            logger.debug("Authentication failed");
        }
        if (!ListenerUtil.mutListener.listen(1928)) {
            if (!isCheckOnly) {
                if (!ListenerUtil.mutListener.listen(1927)) {
                    NavigationUtil.navigateToLauncher(this);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(1929)) {
            this.setResult(RESULT_CANCELED);
        }
        if (!ListenerUtil.mutListener.listen(1930)) {
            this.finish();
        }
    }
}
