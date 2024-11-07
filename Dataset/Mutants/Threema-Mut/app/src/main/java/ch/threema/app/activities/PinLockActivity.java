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

import android.content.res.Configuration;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.SystemClock;
import android.text.InputFilter;
import android.text.InputType;
import android.view.KeyEvent;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.TextView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.security.MessageDigest;
import ch.threema.app.R;
import ch.threema.app.ThreemaApplication;
import ch.threema.app.managers.ServiceManager;
import ch.threema.app.services.LockAppService;
import ch.threema.app.services.PreferenceService;
import ch.threema.app.utils.ConfigUtils;
import ch.threema.app.utils.EditTextUtil;
import ch.threema.app.utils.NavigationUtil;
import ch.threema.app.utils.RuntimeUtil;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class PinLockActivity extends ThreemaActivity {

    private static final Logger logger = LoggerFactory.getLogger(PinLockActivity.class);

    private static final String KEY_NUM_WRONG_CONFIRM_ATTEMPTS = "num_wrong_attempts";

    private static final long ERROR_MESSAGE_TIMEOUT = 3000;

    private static final int FAILED_ATTEMPTS_BEFORE_TIMEOUT = 3;

    private static final long FAILED_ATTEMPT_COUNTDOWN_INTERVAL_MS = 1000L;

    private static final int DEFAULT_LOCKOUT_TIMEOUT = 30 * 1000;

    private TextView passwordEntry;

    private TextView headerTextView;

    private TextView detailsTextView;

    private TextView errorTextView;

    private int numWrongConfirmAttempts;

    private Handler handler = new Handler();

    private CountDownTimer countDownTimer;

    private boolean isCheckOnly;

    private String pinPreset;

    private LockAppService lockAppService;

    private PreferenceService preferenceService;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(5235)) {
            super.onCreate(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(5236)) {
            logger.debug("onCreate");
        }
        if (!ListenerUtil.mutListener.listen(5237)) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_SECURE);
        }
        if (!ListenerUtil.mutListener.listen(5238)) {
            ConfigUtils.configureActivityTheme(this);
        }
        if (!ListenerUtil.mutListener.listen(5239)) {
            isCheckOnly = getIntent().getBooleanExtra(ThreemaApplication.INTENT_DATA_CHECK_ONLY, false);
        }
        if (!ListenerUtil.mutListener.listen(5240)) {
            pinPreset = getIntent().getStringExtra(ThreemaApplication.INTENT_DATA_PIN);
        }
        ServiceManager serviceManager = ThreemaApplication.getServiceManager();
        if (!ListenerUtil.mutListener.listen(5242)) {
            if (serviceManager == null) {
                if (!ListenerUtil.mutListener.listen(5241)) {
                    finish();
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(5243)) {
            preferenceService = serviceManager.getPreferenceService();
        }
        if (!ListenerUtil.mutListener.listen(5244)) {
            lockAppService = serviceManager.getLockAppService();
        }
        if (!ListenerUtil.mutListener.listen(5247)) {
            if ((ListenerUtil.mutListener.listen(5245) ? (!lockAppService.isLocked() || !isCheckOnly) : (!lockAppService.isLocked() && !isCheckOnly))) {
                if (!ListenerUtil.mutListener.listen(5246)) {
                    finish();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(5249)) {
            if (savedInstanceState != null) {
                if (!ListenerUtil.mutListener.listen(5248)) {
                    numWrongConfirmAttempts = savedInstanceState.getInt(KEY_NUM_WRONG_CONFIRM_ATTEMPTS, 0);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(5250)) {
            setContentView(R.layout.activity_pin_lock);
        }
        if (!ListenerUtil.mutListener.listen(5251)) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE | WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        }
        if (!ListenerUtil.mutListener.listen(5252)) {
            passwordEntry = findViewById(R.id.password_entry);
        }
        if (!ListenerUtil.mutListener.listen(5257)) {
            passwordEntry.setOnEditorActionListener(new TextView.OnEditorActionListener() {

                @Override
                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                    if (!ListenerUtil.mutListener.listen(5256)) {
                        // Check if this was the result of hitting the enter or "done" key
                        if ((ListenerUtil.mutListener.listen(5254) ? ((ListenerUtil.mutListener.listen(5253) ? (actionId == EditorInfo.IME_NULL && actionId == EditorInfo.IME_ACTION_DONE) : (actionId == EditorInfo.IME_NULL || actionId == EditorInfo.IME_ACTION_DONE)) && actionId == EditorInfo.IME_ACTION_NEXT) : ((ListenerUtil.mutListener.listen(5253) ? (actionId == EditorInfo.IME_NULL && actionId == EditorInfo.IME_ACTION_DONE) : (actionId == EditorInfo.IME_NULL || actionId == EditorInfo.IME_ACTION_DONE)) || actionId == EditorInfo.IME_ACTION_NEXT))) {
                            if (!ListenerUtil.mutListener.listen(5255)) {
                                handleNext();
                            }
                            return true;
                        }
                    }
                    return false;
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(5258)) {
            passwordEntry.setFilters(new InputFilter[] { new InputFilter.LengthFilter(ThreemaApplication.MAX_PIN_LENGTH) });
        }
        if (!ListenerUtil.mutListener.listen(5259)) {
            headerTextView = findViewById(R.id.headerText);
        }
        if (!ListenerUtil.mutListener.listen(5260)) {
            detailsTextView = findViewById(R.id.detailsText);
        }
        if (!ListenerUtil.mutListener.listen(5261)) {
            errorTextView = findViewById(R.id.errorText);
        }
        if (!ListenerUtil.mutListener.listen(5262)) {
            headerTextView.setText(R.string.confirm_your_pin);
        }
        if (!ListenerUtil.mutListener.listen(5263)) {
            detailsTextView.setText(R.string.pinentry_enter_pin);
        }
        if (!ListenerUtil.mutListener.listen(5264)) {
            passwordEntry.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_VARIATION_PASSWORD);
        }
        Button cancelButton = findViewById(R.id.cancelButton);
        if (!ListenerUtil.mutListener.listen(5265)) {
            cancelButton.setOnClickListener(v -> quit());
        }
    }

    @Override
    protected boolean isPinLockable() {
        return false;
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        if (!ListenerUtil.mutListener.listen(5266)) {
            super.onConfigurationChanged(newConfig);
        }
    }

    @Override
    public void onPause() {
        if (!ListenerUtil.mutListener.listen(5267)) {
            super.onPause();
        }
        if (!ListenerUtil.mutListener.listen(5270)) {
            if (countDownTimer != null) {
                if (!ListenerUtil.mutListener.listen(5268)) {
                    countDownTimer.cancel();
                }
                if (!ListenerUtil.mutListener.listen(5269)) {
                    countDownTimer = null;
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(5271)) {
            overridePendingTransition(0, 0);
        }
    }

    @Override
    public void onResume() {
        if (!ListenerUtil.mutListener.listen(5272)) {
            super.onResume();
        }
        if (!ListenerUtil.mutListener.listen(5275)) {
            if ((ListenerUtil.mutListener.listen(5273) ? (!lockAppService.isLocked() || !isCheckOnly) : (!lockAppService.isLocked() && !isCheckOnly))) {
                if (!ListenerUtil.mutListener.listen(5274)) {
                    finish();
                }
            }
        }
        long deadline = getLockoutAttemptDeadline();
        if (!ListenerUtil.mutListener.listen(5282)) {
            if ((ListenerUtil.mutListener.listen(5280) ? (deadline >= 0) : (ListenerUtil.mutListener.listen(5279) ? (deadline <= 0) : (ListenerUtil.mutListener.listen(5278) ? (deadline > 0) : (ListenerUtil.mutListener.listen(5277) ? (deadline < 0) : (ListenerUtil.mutListener.listen(5276) ? (deadline == 0) : (deadline != 0))))))) {
                if (!ListenerUtil.mutListener.listen(5281)) {
                    handleAttemptLockout(deadline);
                }
            }
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        if (!ListenerUtil.mutListener.listen(5283)) {
            super.onSaveInstanceState(outState);
        }
        if (!ListenerUtil.mutListener.listen(5284)) {
            outState.putInt(KEY_NUM_WRONG_CONFIRM_ATTEMPTS, numWrongConfirmAttempts);
        }
    }

    @Override
    public void onBackPressed() {
        if (!ListenerUtil.mutListener.listen(5285)) {
            quit();
        }
    }

    private void quit() {
        if (!ListenerUtil.mutListener.listen(5286)) {
            EditTextUtil.hideSoftKeyboard(passwordEntry);
        }
        if (!ListenerUtil.mutListener.listen(5290)) {
            if (isCheckOnly) {
                if (!ListenerUtil.mutListener.listen(5288)) {
                    setResult(RESULT_CANCELED);
                }
                if (!ListenerUtil.mutListener.listen(5289)) {
                    finish();
                }
            } else {
                if (!ListenerUtil.mutListener.listen(5287)) {
                    NavigationUtil.navigateToLauncher(this);
                }
            }
        }
    }

    private void handleNext() {
        final String pin = passwordEntry.getText().toString();
        if (!ListenerUtil.mutListener.listen(5307)) {
            // use MessageDigest for a timing-safe comparison
            if ((ListenerUtil.mutListener.listen(5292) ? (lockAppService.unlock(pin) && (ListenerUtil.mutListener.listen(5291) ? (pinPreset != null || MessageDigest.isEqual(pin.getBytes(), pinPreset.getBytes())) : (pinPreset != null && MessageDigest.isEqual(pin.getBytes(), pinPreset.getBytes())))) : (lockAppService.unlock(pin) || (ListenerUtil.mutListener.listen(5291) ? (pinPreset != null || MessageDigest.isEqual(pin.getBytes(), pinPreset.getBytes())) : (pinPreset != null && MessageDigest.isEqual(pin.getBytes(), pinPreset.getBytes())))))) {
                if (!ListenerUtil.mutListener.listen(5304)) {
                    EditTextUtil.hideSoftKeyboard(passwordEntry);
                }
                if (!ListenerUtil.mutListener.listen(5305)) {
                    setResult(RESULT_OK);
                }
                if (!ListenerUtil.mutListener.listen(5306)) {
                    finish();
                }
            } else {
                if (!ListenerUtil.mutListener.listen(5295)) {
                    if (isCheckOnly) {
                        if (!ListenerUtil.mutListener.listen(5293)) {
                            passwordEntry.setEnabled(false);
                        }
                        if (!ListenerUtil.mutListener.listen(5294)) {
                            handler.postDelayed(() -> RuntimeUtil.runOnUiThread(this::finish), 1000);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(5303)) {
                    if ((ListenerUtil.mutListener.listen(5300) ? (++numWrongConfirmAttempts <= FAILED_ATTEMPTS_BEFORE_TIMEOUT) : (ListenerUtil.mutListener.listen(5299) ? (++numWrongConfirmAttempts > FAILED_ATTEMPTS_BEFORE_TIMEOUT) : (ListenerUtil.mutListener.listen(5298) ? (++numWrongConfirmAttempts < FAILED_ATTEMPTS_BEFORE_TIMEOUT) : (ListenerUtil.mutListener.listen(5297) ? (++numWrongConfirmAttempts != FAILED_ATTEMPTS_BEFORE_TIMEOUT) : (ListenerUtil.mutListener.listen(5296) ? (++numWrongConfirmAttempts == FAILED_ATTEMPTS_BEFORE_TIMEOUT) : (++numWrongConfirmAttempts >= FAILED_ATTEMPTS_BEFORE_TIMEOUT))))))) {
                        // TODO default value
                        long deadline = setLockoutAttemptDeadline(DEFAULT_LOCKOUT_TIMEOUT);
                        if (!ListenerUtil.mutListener.listen(5302)) {
                            handleAttemptLockout(deadline);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(5301)) {
                            showError(R.string.pinentry_wrong_pin);
                        }
                    }
                }
            }
        }
    }

    private void handleAttemptLockout(long elapsedRealtimeDeadline) {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        if (!ListenerUtil.mutListener.listen(5308)) {
            passwordEntry.setEnabled(false);
        }
        if (!ListenerUtil.mutListener.listen(5321)) {
            countDownTimer = new CountDownTimer((ListenerUtil.mutListener.listen(5320) ? (elapsedRealtimeDeadline % elapsedRealtime) : (ListenerUtil.mutListener.listen(5319) ? (elapsedRealtimeDeadline / elapsedRealtime) : (ListenerUtil.mutListener.listen(5318) ? (elapsedRealtimeDeadline * elapsedRealtime) : (ListenerUtil.mutListener.listen(5317) ? (elapsedRealtimeDeadline + elapsedRealtime) : (elapsedRealtimeDeadline - elapsedRealtime))))), FAILED_ATTEMPT_COUNTDOWN_INTERVAL_MS) {

                @Override
                public void onTick(long millisUntilFinished) {
                    final int secondsCountdown = (int) ((ListenerUtil.mutListener.listen(5312) ? (millisUntilFinished % 1000) : (ListenerUtil.mutListener.listen(5311) ? (millisUntilFinished * 1000) : (ListenerUtil.mutListener.listen(5310) ? (millisUntilFinished - 1000) : (ListenerUtil.mutListener.listen(5309) ? (millisUntilFinished + 1000) : (millisUntilFinished / 1000))))));
                    if (!ListenerUtil.mutListener.listen(5313)) {
                        showError(String.format(getString(R.string.too_many_incorrect_attempts), Integer.toString(secondsCountdown)), 0);
                    }
                }

                @Override
                public void onFinish() {
                    if (!ListenerUtil.mutListener.listen(5314)) {
                        passwordEntry.setEnabled(true);
                    }
                    if (!ListenerUtil.mutListener.listen(5315)) {
                        errorTextView.setText("");
                    }
                    if (!ListenerUtil.mutListener.listen(5316)) {
                        numWrongConfirmAttempts = 0;
                    }
                }
            }.start();
        }
    }

    private void showError(int msg) {
        if (!ListenerUtil.mutListener.listen(5322)) {
            showError(msg, ERROR_MESSAGE_TIMEOUT);
        }
    }

    private final Runnable resetErrorRunnable = new Runnable() {

        public void run() {
            if (!ListenerUtil.mutListener.listen(5323)) {
                errorTextView.setText("");
            }
        }
    };

    private void showError(CharSequence msg, long timeout) {
        if (!ListenerUtil.mutListener.listen(5324)) {
            errorTextView.setText(msg);
        }
        if (!ListenerUtil.mutListener.listen(5325)) {
            errorTextView.announceForAccessibility(errorTextView.getText());
        }
        if (!ListenerUtil.mutListener.listen(5326)) {
            passwordEntry.setText(null);
        }
        if (!ListenerUtil.mutListener.listen(5327)) {
            handler.removeCallbacks(resetErrorRunnable);
        }
        if (!ListenerUtil.mutListener.listen(5334)) {
            if ((ListenerUtil.mutListener.listen(5332) ? (timeout >= 0) : (ListenerUtil.mutListener.listen(5331) ? (timeout <= 0) : (ListenerUtil.mutListener.listen(5330) ? (timeout > 0) : (ListenerUtil.mutListener.listen(5329) ? (timeout < 0) : (ListenerUtil.mutListener.listen(5328) ? (timeout == 0) : (timeout != 0))))))) {
                if (!ListenerUtil.mutListener.listen(5333)) {
                    handler.postDelayed(resetErrorRunnable, timeout);
                }
            }
        }
    }

    private void showError(int msg, long timeout) {
        if (!ListenerUtil.mutListener.listen(5335)) {
            showError(getText(msg), timeout);
        }
    }

    /**
     *  Set and store the lockout deadline, meaning the user can't attempt his/her unlock
     *  pattern until the deadline has passed.
     *
     *  @return the chosen deadline.
     */
    public long setLockoutAttemptDeadline(int timeoutMs) {
        final long deadline = SystemClock.elapsedRealtime() + timeoutMs;
        if (!ListenerUtil.mutListener.listen(5336)) {
            preferenceService.setLockoutDeadline(deadline);
        }
        if (!ListenerUtil.mutListener.listen(5337)) {
            preferenceService.setLockoutTimeout(timeoutMs);
        }
        return deadline;
    }

    /**
     *  @return The elapsed time in millis in the future when the user is allowed to
     *  attempt to enter his/her lock pattern, or 0 if the user is welcome to
     *  enter a pattern.
     */
    public long getLockoutAttemptDeadline() {
        if (!ListenerUtil.mutListener.listen(5338)) {
            if (isCheckOnly) {
                return 0L;
            }
        }
        final long deadline = preferenceService.getLockoutDeadline();
        final long timeoutMs = preferenceService.getLockoutTimeout();
        final long now = SystemClock.elapsedRealtime();
        if (!ListenerUtil.mutListener.listen(5354)) {
            if ((ListenerUtil.mutListener.listen(5353) ? ((ListenerUtil.mutListener.listen(5343) ? (deadline >= now) : (ListenerUtil.mutListener.listen(5342) ? (deadline <= now) : (ListenerUtil.mutListener.listen(5341) ? (deadline > now) : (ListenerUtil.mutListener.listen(5340) ? (deadline != now) : (ListenerUtil.mutListener.listen(5339) ? (deadline == now) : (deadline < now)))))) && (ListenerUtil.mutListener.listen(5352) ? (deadline >= ((ListenerUtil.mutListener.listen(5347) ? (now % timeoutMs) : (ListenerUtil.mutListener.listen(5346) ? (now / timeoutMs) : (ListenerUtil.mutListener.listen(5345) ? (now * timeoutMs) : (ListenerUtil.mutListener.listen(5344) ? (now - timeoutMs) : (now + timeoutMs))))))) : (ListenerUtil.mutListener.listen(5351) ? (deadline <= ((ListenerUtil.mutListener.listen(5347) ? (now % timeoutMs) : (ListenerUtil.mutListener.listen(5346) ? (now / timeoutMs) : (ListenerUtil.mutListener.listen(5345) ? (now * timeoutMs) : (ListenerUtil.mutListener.listen(5344) ? (now - timeoutMs) : (now + timeoutMs))))))) : (ListenerUtil.mutListener.listen(5350) ? (deadline < ((ListenerUtil.mutListener.listen(5347) ? (now % timeoutMs) : (ListenerUtil.mutListener.listen(5346) ? (now / timeoutMs) : (ListenerUtil.mutListener.listen(5345) ? (now * timeoutMs) : (ListenerUtil.mutListener.listen(5344) ? (now - timeoutMs) : (now + timeoutMs))))))) : (ListenerUtil.mutListener.listen(5349) ? (deadline != ((ListenerUtil.mutListener.listen(5347) ? (now % timeoutMs) : (ListenerUtil.mutListener.listen(5346) ? (now / timeoutMs) : (ListenerUtil.mutListener.listen(5345) ? (now * timeoutMs) : (ListenerUtil.mutListener.listen(5344) ? (now - timeoutMs) : (now + timeoutMs))))))) : (ListenerUtil.mutListener.listen(5348) ? (deadline == ((ListenerUtil.mutListener.listen(5347) ? (now % timeoutMs) : (ListenerUtil.mutListener.listen(5346) ? (now / timeoutMs) : (ListenerUtil.mutListener.listen(5345) ? (now * timeoutMs) : (ListenerUtil.mutListener.listen(5344) ? (now - timeoutMs) : (now + timeoutMs))))))) : (deadline > ((ListenerUtil.mutListener.listen(5347) ? (now % timeoutMs) : (ListenerUtil.mutListener.listen(5346) ? (now / timeoutMs) : (ListenerUtil.mutListener.listen(5345) ? (now * timeoutMs) : (ListenerUtil.mutListener.listen(5344) ? (now - timeoutMs) : (now + timeoutMs))))))))))))) : ((ListenerUtil.mutListener.listen(5343) ? (deadline >= now) : (ListenerUtil.mutListener.listen(5342) ? (deadline <= now) : (ListenerUtil.mutListener.listen(5341) ? (deadline > now) : (ListenerUtil.mutListener.listen(5340) ? (deadline != now) : (ListenerUtil.mutListener.listen(5339) ? (deadline == now) : (deadline < now)))))) || (ListenerUtil.mutListener.listen(5352) ? (deadline >= ((ListenerUtil.mutListener.listen(5347) ? (now % timeoutMs) : (ListenerUtil.mutListener.listen(5346) ? (now / timeoutMs) : (ListenerUtil.mutListener.listen(5345) ? (now * timeoutMs) : (ListenerUtil.mutListener.listen(5344) ? (now - timeoutMs) : (now + timeoutMs))))))) : (ListenerUtil.mutListener.listen(5351) ? (deadline <= ((ListenerUtil.mutListener.listen(5347) ? (now % timeoutMs) : (ListenerUtil.mutListener.listen(5346) ? (now / timeoutMs) : (ListenerUtil.mutListener.listen(5345) ? (now * timeoutMs) : (ListenerUtil.mutListener.listen(5344) ? (now - timeoutMs) : (now + timeoutMs))))))) : (ListenerUtil.mutListener.listen(5350) ? (deadline < ((ListenerUtil.mutListener.listen(5347) ? (now % timeoutMs) : (ListenerUtil.mutListener.listen(5346) ? (now / timeoutMs) : (ListenerUtil.mutListener.listen(5345) ? (now * timeoutMs) : (ListenerUtil.mutListener.listen(5344) ? (now - timeoutMs) : (now + timeoutMs))))))) : (ListenerUtil.mutListener.listen(5349) ? (deadline != ((ListenerUtil.mutListener.listen(5347) ? (now % timeoutMs) : (ListenerUtil.mutListener.listen(5346) ? (now / timeoutMs) : (ListenerUtil.mutListener.listen(5345) ? (now * timeoutMs) : (ListenerUtil.mutListener.listen(5344) ? (now - timeoutMs) : (now + timeoutMs))))))) : (ListenerUtil.mutListener.listen(5348) ? (deadline == ((ListenerUtil.mutListener.listen(5347) ? (now % timeoutMs) : (ListenerUtil.mutListener.listen(5346) ? (now / timeoutMs) : (ListenerUtil.mutListener.listen(5345) ? (now * timeoutMs) : (ListenerUtil.mutListener.listen(5344) ? (now - timeoutMs) : (now + timeoutMs))))))) : (deadline > ((ListenerUtil.mutListener.listen(5347) ? (now % timeoutMs) : (ListenerUtil.mutListener.listen(5346) ? (now / timeoutMs) : (ListenerUtil.mutListener.listen(5345) ? (now * timeoutMs) : (ListenerUtil.mutListener.listen(5344) ? (now - timeoutMs) : (now + timeoutMs))))))))))))))) {
                return 0L;
            }
        }
        return deadline;
    }
}
