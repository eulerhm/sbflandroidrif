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
package ch.threema.app.activities;

import android.app.NotificationManager;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.android.material.textfield.TextInputLayout;
import java.util.Arrays;
import ch.threema.app.R;
import ch.threema.app.ThreemaApplication;
import ch.threema.app.dialogs.GenericProgressDialog;
import ch.threema.app.services.PassphraseService;
import ch.threema.app.ui.ThreemaTextInputEditText;
import ch.threema.app.utils.ConfigUtils;
import ch.threema.app.utils.DialogUtil;
import ch.threema.app.utils.EditTextUtil;
import ch.threema.app.utils.RuntimeUtil;
import ch.threema.localcrypto.MasterKey;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

// this should NOT extend ThreemaToolbarActivity
public class UnlockMasterKeyActivity extends ThreemaActivity {

    private static final String DIALOG_TAG_UNLOCKING = "dtu";

    private ThreemaTextInputEditText passphraseText;

    private TextInputLayout passphraseLayout;

    private ImageView unlockButton;

    private MasterKey masterKey = ThreemaApplication.getMasterKey();

    public void onCreate(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(7138)) {
            super.onCreate(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(7139)) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_SECURE);
        }
        if (!ListenerUtil.mutListener.listen(7140)) {
            setContentView(R.layout.activity_unlock_masterkey);
        }
        TextView infoText = findViewById(R.id.unlock_info);
        TypedArray array = getTheme().obtainStyledAttributes(new int[] { android.R.attr.textColorSecondary });
        if (!ListenerUtil.mutListener.listen(7141)) {
            infoText.getCompoundDrawables()[0].setColorFilter(array.getColor(0, -1), PorterDuff.Mode.SRC_IN);
        }
        if (!ListenerUtil.mutListener.listen(7142)) {
            array.recycle();
        }
        if (!ListenerUtil.mutListener.listen(7143)) {
            passphraseLayout = findViewById(R.id.passphrase_layout);
        }
        if (!ListenerUtil.mutListener.listen(7144)) {
            passphraseText = findViewById(R.id.passphrase);
        }
        if (!ListenerUtil.mutListener.listen(7145)) {
            passphraseText.addTextChangedListener(new PasswordWatcher());
        }
        if (!ListenerUtil.mutListener.listen(7150)) {
            passphraseText.setOnKeyListener(new View.OnKeyListener() {

                @Override
                public boolean onKey(View v, int keyCode, KeyEvent event) {
                    if (!ListenerUtil.mutListener.listen(7149)) {
                        if ((ListenerUtil.mutListener.listen(7146) ? (event.getAction() == KeyEvent.ACTION_DOWN || keyCode == KeyEvent.KEYCODE_ENTER) : (event.getAction() == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER))) {
                            if (!ListenerUtil.mutListener.listen(7148)) {
                                if (isValidEntry(passphraseText)) {
                                    if (!ListenerUtil.mutListener.listen(7147)) {
                                        doUnlock();
                                    }
                                }
                            }
                            return true;
                        }
                    }
                    return false;
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(7155)) {
            passphraseText.setOnEditorActionListener(new TextView.OnEditorActionListener() {

                @Override
                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                    boolean handled = false;
                    if (!ListenerUtil.mutListener.listen(7154)) {
                        if (actionId == EditorInfo.IME_ACTION_GO) {
                            if (!ListenerUtil.mutListener.listen(7153)) {
                                if (isValidEntry(passphraseText)) {
                                    if (!ListenerUtil.mutListener.listen(7151)) {
                                        doUnlock();
                                    }
                                    if (!ListenerUtil.mutListener.listen(7152)) {
                                        handled = true;
                                    }
                                }
                            }
                        }
                    }
                    return handled;
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(7156)) {
            unlockButton = findViewById(R.id.unlock_button);
        }
        if (!ListenerUtil.mutListener.listen(7158)) {
            unlockButton.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    if (!ListenerUtil.mutListener.listen(7157)) {
                        doUnlock();
                    }
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(7159)) {
            unlockButton.setClickable(false);
        }
        if (!ListenerUtil.mutListener.listen(7160)) {
            unlockButton.setEnabled(false);
        }
    }

    @Override
    protected void onApplyThemeResource(Resources.Theme theme, int resid, boolean first) {
        if (!ListenerUtil.mutListener.listen(7163)) {
            if (ConfigUtils.getAppTheme(this) == ConfigUtils.THEME_DARK) {
                if (!ListenerUtil.mutListener.listen(7162)) {
                    theme.applyStyle(R.style.Theme_Threema_WithToolbar_Dark, true);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(7161)) {
                    super.onApplyThemeResource(theme, resid, first);
                }
            }
        }
    }

    @Override
    protected void onResume() {
        if (!ListenerUtil.mutListener.listen(7164)) {
            super.onResume();
        }
        if (!ListenerUtil.mutListener.listen(7167)) {
            // check if the key is unlocked!
            if ((ListenerUtil.mutListener.listen(7165) ? (!this.justCheck() || !this.masterKey.isLocked()) : (!this.justCheck() && !this.masterKey.isLocked()))) {
                if (!ListenerUtil.mutListener.listen(7166)) {
                    this.finish();
                }
            }
        }
    }

    private void doUnlock() {
        if (!ListenerUtil.mutListener.listen(7168)) {
            unlockButton.setEnabled(false);
        }
        if (!ListenerUtil.mutListener.listen(7169)) {
            unlockButton.setClickable(false);
        }
        if (!ListenerUtil.mutListener.listen(7170)) {
            // hide keyboard to make error message visible on low resolution displays
            EditTextUtil.hideSoftKeyboard(this.passphraseText);
        }
        if (!ListenerUtil.mutListener.listen(7171)) {
            this.unlock(this.passphraseText.getPassphrase());
        }
    }

    public class PasswordWatcher implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        @Override
        public void afterTextChanged(Editable s) {
            if (!ListenerUtil.mutListener.listen(7183)) {
                if (unlockButton != null) {
                    if (!ListenerUtil.mutListener.listen(7180)) {
                        if ((ListenerUtil.mutListener.listen(7178) ? ((ListenerUtil.mutListener.listen(7172) ? (passphraseText != null || passphraseText.getText() != null) : (passphraseText != null && passphraseText.getText() != null)) || (ListenerUtil.mutListener.listen(7177) ? (passphraseText.getText().length() >= 0) : (ListenerUtil.mutListener.listen(7176) ? (passphraseText.getText().length() <= 0) : (ListenerUtil.mutListener.listen(7175) ? (passphraseText.getText().length() < 0) : (ListenerUtil.mutListener.listen(7174) ? (passphraseText.getText().length() != 0) : (ListenerUtil.mutListener.listen(7173) ? (passphraseText.getText().length() == 0) : (passphraseText.getText().length() > 0))))))) : ((ListenerUtil.mutListener.listen(7172) ? (passphraseText != null || passphraseText.getText() != null) : (passphraseText != null && passphraseText.getText() != null)) && (ListenerUtil.mutListener.listen(7177) ? (passphraseText.getText().length() >= 0) : (ListenerUtil.mutListener.listen(7176) ? (passphraseText.getText().length() <= 0) : (ListenerUtil.mutListener.listen(7175) ? (passphraseText.getText().length() < 0) : (ListenerUtil.mutListener.listen(7174) ? (passphraseText.getText().length() != 0) : (ListenerUtil.mutListener.listen(7173) ? (passphraseText.getText().length() == 0) : (passphraseText.getText().length() > 0))))))))) {
                            if (!ListenerUtil.mutListener.listen(7179)) {
                                passphraseLayout.setError(null);
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(7181)) {
                        unlockButton.setEnabled(isValidEntry(passphraseText));
                    }
                    if (!ListenerUtil.mutListener.listen(7182)) {
                        unlockButton.setClickable(isValidEntry(passphraseText));
                    }
                }
            }
        }
    }

    private boolean isValidEntry(EditText passphraseText) {
        return (ListenerUtil.mutListener.listen(7190) ? ((ListenerUtil.mutListener.listen(7184) ? (passphraseText != null || passphraseText.getText() != null) : (passphraseText != null && passphraseText.getText() != null)) || (ListenerUtil.mutListener.listen(7189) ? (passphraseText.getText().length() <= 8) : (ListenerUtil.mutListener.listen(7188) ? (passphraseText.getText().length() > 8) : (ListenerUtil.mutListener.listen(7187) ? (passphraseText.getText().length() < 8) : (ListenerUtil.mutListener.listen(7186) ? (passphraseText.getText().length() != 8) : (ListenerUtil.mutListener.listen(7185) ? (passphraseText.getText().length() == 8) : (passphraseText.getText().length() >= 8))))))) : ((ListenerUtil.mutListener.listen(7184) ? (passphraseText != null || passphraseText.getText() != null) : (passphraseText != null && passphraseText.getText() != null)) && (ListenerUtil.mutListener.listen(7189) ? (passphraseText.getText().length() <= 8) : (ListenerUtil.mutListener.listen(7188) ? (passphraseText.getText().length() > 8) : (ListenerUtil.mutListener.listen(7187) ? (passphraseText.getText().length() < 8) : (ListenerUtil.mutListener.listen(7186) ? (passphraseText.getText().length() != 8) : (ListenerUtil.mutListener.listen(7185) ? (passphraseText.getText().length() == 8) : (passphraseText.getText().length() >= 8))))))));
    }

    private void unlock(final char[] passphrase) {
        final boolean justCheck = this.justCheck();
        if (!ListenerUtil.mutListener.listen(7202)) {
            if ((ListenerUtil.mutListener.listen(7191) ? (justCheck && this.masterKey.isLocked()) : (justCheck || this.masterKey.isLocked()))) {
                if (!ListenerUtil.mutListener.listen(7193)) {
                    // only change on master key!
                    GenericProgressDialog.newInstance(R.string.masterkey_unlocking, R.string.please_wait).show(getSupportFragmentManager(), DIALOG_TAG_UNLOCKING);
                }
                if (!ListenerUtil.mutListener.listen(7201)) {
                    new Thread(new Runnable() {

                        @Override
                        public void run() {
                            boolean isValid;
                            if (justCheck) {
                                isValid = masterKey.checkPassphrase(passphrase);
                            } else {
                                isValid = masterKey.unlock(passphrase);
                            }
                            if (!ListenerUtil.mutListener.listen(7194)) {
                                // clear passphrase
                                Arrays.fill(passphrase, ' ');
                            }
                            if (!ListenerUtil.mutListener.listen(7199)) {
                                if (!isValid) {
                                    if (!ListenerUtil.mutListener.listen(7198)) {
                                        RuntimeUtil.runOnUiThread(() -> {
                                            passphraseLayout.setError(getString(R.string.invalid_passphrase));
                                            passphraseText.setText("");
                                        });
                                    }
                                } else {
                                    if (!ListenerUtil.mutListener.listen(7197)) {
                                        if (justCheck) {
                                            if (!ListenerUtil.mutListener.listen(7196)) {
                                                RuntimeUtil.runOnUiThread(() -> {
                                                    UnlockMasterKeyActivity.this.setResult(RESULT_OK);
                                                    UnlockMasterKeyActivity.this.finish();
                                                });
                                            }
                                        } else {
                                            if (!ListenerUtil.mutListener.listen(7195)) {
                                                // finish after unlock
                                                RuntimeUtil.runOnUiThread(() -> {
                                                    ThreemaApplication.reset();
                                                    new Thread(() -> {
                                                        /* trigger a connection now - as there was no identity before the master key was unlocked */
                                                        ThreemaApplication.getServiceManager().getLifetimeService().acquireConnection("UnlockMasterKey");
                                                    }).start();
                                                    // cancel all notifications...if any
                                                    NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                                                    notificationManager.cancelAll();
                                                    /* show persistent notification */
                                                    PassphraseService.start(UnlockMasterKeyActivity.this.getApplicationContext());
                                                    UnlockMasterKeyActivity.this.finish();
                                                });
                                            }
                                        }
                                    }
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(7200)) {
                                RuntimeUtil.runOnUiThread(() -> DialogUtil.dismissDialog(getSupportFragmentManager(), DIALOG_TAG_UNLOCKING, true));
                            }
                        }
                    }).start();
                }
            } else {
                if (!ListenerUtil.mutListener.listen(7192)) {
                    this.finish();
                }
            }
        }
    }

    private boolean justCheck() {
        return getIntent().getBooleanExtra(ThreemaApplication.INTENT_DATA_PASSPHRASE_CHECK, false);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        if (!ListenerUtil.mutListener.listen(7203)) {
            // activity when the keyboard is opened or orientation changes
            super.onConfigurationChanged(newConfig);
        }
    }
}
