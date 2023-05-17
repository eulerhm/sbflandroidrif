package org.wordpress.android.util;

import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;
import androidx.annotation.NonNull;
import org.wordpress.android.R;
import org.wordpress.android.ui.prefs.AppPrefs;
import org.wordpress.android.ui.prefs.AppPrefs.UndeletablePrefKey;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class SmartToast {

    public enum SmartToastType {

        COMMENTS_LONG_PRESS(UndeletablePrefKey.SMART_TOAST_COMMENTS_LONG_PRESS_USAGE_COUNTER, UndeletablePrefKey.SMART_TOAST_COMMENTS_LONG_PRESS_TOAST_COUNTER);

        // key which stores the number of times the feature associated with the smart toast has been used
        private final UndeletablePrefKey mUsageKey;

        // key which stores the number of times the toast associated with the smart toast type has been shown
        private final UndeletablePrefKey mShownKey;

        SmartToastType(@NonNull UndeletablePrefKey usageKey, @NonNull UndeletablePrefKey shownKey) {
            this.mUsageKey = usageKey;
            this.mShownKey = shownKey;
        }
    }

    private static final int MIN_TIMES_TO_USE_FEATURE = 3;

    private static final int MAX_TIMES_TO_SHOW_TOAST = 2;

    public static void reset() {
        if (!ListenerUtil.mutListener.listen(27912)) {
            {
                long _loopCounter417 = 0;
                for (SmartToastType type : SmartToastType.values()) {
                    ListenerUtil.loopListener.listen("_loopCounter417", ++_loopCounter417);
                    if (!ListenerUtil.mutListener.listen(27910)) {
                        AppPrefs.setInt(type.mShownKey, 0);
                    }
                    if (!ListenerUtil.mutListener.listen(27911)) {
                        AppPrefs.setInt(type.mUsageKey, 0);
                    }
                }
            }
        }
    }

    public static boolean show(@NonNull Context context, @NonNull SmartToastType type) {
        // limit the number of times to show the toast
        int numTimesShown = AppPrefs.getInt(type.mShownKey);
        if (!ListenerUtil.mutListener.listen(27918)) {
            if ((ListenerUtil.mutListener.listen(27917) ? (numTimesShown <= MAX_TIMES_TO_SHOW_TOAST) : (ListenerUtil.mutListener.listen(27916) ? (numTimesShown > MAX_TIMES_TO_SHOW_TOAST) : (ListenerUtil.mutListener.listen(27915) ? (numTimesShown < MAX_TIMES_TO_SHOW_TOAST) : (ListenerUtil.mutListener.listen(27914) ? (numTimesShown != MAX_TIMES_TO_SHOW_TOAST) : (ListenerUtil.mutListener.listen(27913) ? (numTimesShown == MAX_TIMES_TO_SHOW_TOAST) : (numTimesShown >= MAX_TIMES_TO_SHOW_TOAST))))))) {
                return false;
            }
        }
        // don't show the toast until the user has used this feature a few times
        int numTypesFeatureUsed = AppPrefs.getInt(type.mUsageKey);
        if (!ListenerUtil.mutListener.listen(27919)) {
            numTypesFeatureUsed++;
        }
        if (!ListenerUtil.mutListener.listen(27920)) {
            AppPrefs.setInt(type.mUsageKey, numTypesFeatureUsed);
        }
        if (!ListenerUtil.mutListener.listen(27926)) {
            if ((ListenerUtil.mutListener.listen(27925) ? (numTypesFeatureUsed >= MIN_TIMES_TO_USE_FEATURE) : (ListenerUtil.mutListener.listen(27924) ? (numTypesFeatureUsed <= MIN_TIMES_TO_USE_FEATURE) : (ListenerUtil.mutListener.listen(27923) ? (numTypesFeatureUsed > MIN_TIMES_TO_USE_FEATURE) : (ListenerUtil.mutListener.listen(27922) ? (numTypesFeatureUsed != MIN_TIMES_TO_USE_FEATURE) : (ListenerUtil.mutListener.listen(27921) ? (numTypesFeatureUsed == MIN_TIMES_TO_USE_FEATURE) : (numTypesFeatureUsed < MIN_TIMES_TO_USE_FEATURE))))))) {
                return false;
            }
        }
        int stringResId;
        switch(type) {
            case COMMENTS_LONG_PRESS:
                stringResId = R.string.smart_toast_comments_long_press;
                break;
            default:
                return false;
        }
        int yOffset = context.getResources().getDimensionPixelOffset(R.dimen.smart_toast_offset_y);
        Toast toast = Toast.makeText(context, context.getString(stringResId), Toast.LENGTH_LONG);
        if (!ListenerUtil.mutListener.listen(27927)) {
            toast.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM, 0, yOffset);
        }
        if (!ListenerUtil.mutListener.listen(27928)) {
            toast.show();
        }
        if (!ListenerUtil.mutListener.listen(27929)) {
            numTimesShown++;
        }
        if (!ListenerUtil.mutListener.listen(27930)) {
            AppPrefs.setInt(type.mShownKey, numTimesShown);
        }
        return true;
    }

    /*
     * prevent the passed smart toast type from being shown by setting its counter to the max - this should be
     * used to disable long press toasts when the user long presses to multiselect since they already know
     * they can do it
     */
    public static void disableSmartToast(@NonNull SmartToastType type) {
        if (!ListenerUtil.mutListener.listen(27931)) {
            AppPrefs.setInt(type.mShownKey, MAX_TIMES_TO_SHOW_TOAST);
        }
    }
}
