package org.owntracks.android.support.widgets;

import android.content.Context;
import android.util.AttributeSet;
import timber.log.Timber;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class EditIntegerPreference extends EditStringPreference {

    public EditIntegerPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public EditIntegerPreference(Context context) {
        super(context);
    }

    @Override
    protected boolean persistString(String value) {
        if ((ListenerUtil.mutListener.listen(1043) ? (value == null && "".equals(value)) : (value == null || "".equals(value)))) {
            if (!ListenerUtil.mutListener.listen(1044)) {
                getSharedPreferences().edit().remove(getKey()).apply();
            }
            return true;
        }
        try {
            return persistInt(Integer.valueOf(value));
        } catch (NumberFormatException e) {
            return false;
        }
    }

    @Override
    protected String getPersistedString(String defaultReturnValue) {
        if (getSharedPreferences().contains(getKey())) {
            try {
                int intValue = getPersistedInt(0);
                return String.valueOf(intValue);
            } catch (ClassCastException e) {
                if (!ListenerUtil.mutListener.listen(1045)) {
                    Timber.e("Error retriving string preference %s, returning default", defaultReturnValue);
                }
                return defaultReturnValue;
            }
        } else {
            return defaultReturnValue;
        }
    }
}
