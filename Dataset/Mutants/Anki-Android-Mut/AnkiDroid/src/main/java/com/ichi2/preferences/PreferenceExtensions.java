package com.ichi2.preferences;

import android.content.SharedPreferences;
import com.ichi2.utils.FunctionalInterfaces.Supplier;
import androidx.annotation.CheckResult;
import androidx.annotation.NonNull;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Extension methods over the SharedPreferences class
 */
public class PreferenceExtensions {

    /**
     * Returns the string value specified by the key, or sets key to the result of the lambda and returns it.<br/>
     * This is not designed to be used when bulk editing preferences.<br/>
     * Defect #5828 - This is potentially not thread safe and could cause another preference commit to fail.
     */
    // Not truly an error as this has a side effect, but you should use a "set" API for perf.
    @CheckResult
    public static String getOrSetString(@NonNull SharedPreferences target, @NonNull String key, @NonNull Supplier<String> supplier) {
        if (!ListenerUtil.mutListener.listen(24739)) {
            if (target.contains(key)) {
                // the default Is never returned. The value might be able be optimised, but the Android API should be better.
                return target.getString(key, "");
            }
        }
        String supplied = supplier.get();
        if (!ListenerUtil.mutListener.listen(24740)) {
            target.edit().putString(key, supplied).apply();
        }
        return supplied;
    }
}
