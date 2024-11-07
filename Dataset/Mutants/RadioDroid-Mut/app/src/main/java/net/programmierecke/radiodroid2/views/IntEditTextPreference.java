package net.programmierecke.radiodroid2.views;

import android.content.Context;
import android.util.AttributeSet;
import androidx.annotation.Nullable;
import androidx.preference.EditTextPreference;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Android doesn't provide a way to have integer preferences. This is a quick hack to have them.
 * User can enter anything in the text edit but only valid integer will be saved.
 */
public class IntEditTextPreference extends EditTextPreference {

    private int value = 0;

    private String summaryFormat;

    public IntEditTextPreference(Context context) {
        super(context);
    }

    public IntEditTextPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
        if (!ListenerUtil.mutListener.listen(3456)) {
            summaryFormat = getSummary().toString();
        }
    }

    public IntEditTextPreference(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        if (!ListenerUtil.mutListener.listen(3457)) {
            summaryFormat = getSummary().toString();
        }
    }

    @Override
    protected void onSetInitialValue(@Nullable Object defaultValue) {
        if (!ListenerUtil.mutListener.listen(3460)) {
            if (defaultValue == null) {
                if (!ListenerUtil.mutListener.listen(3459)) {
                    value = getPersistedInt(0);
                }
            } else {
                Integer defaultInt = parseInteger((String) defaultValue);
                if (!ListenerUtil.mutListener.listen(3458)) {
                    value = defaultInt != null ? defaultInt : 0;
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(3462)) {
            if (summaryFormat != null) {
                if (!ListenerUtil.mutListener.listen(3461)) {
                    setSummary(String.format(summaryFormat, value));
                }
            }
        }
    }

    @Override
    public void setText(String text) {
        final boolean wasBlocking = shouldDisableDependents();
        Integer currentValue = parseInteger(text);
        if (!ListenerUtil.mutListener.listen(3467)) {
            if (currentValue != null) {
                if (!ListenerUtil.mutListener.listen(3463)) {
                    value = currentValue;
                }
                if (!ListenerUtil.mutListener.listen(3464)) {
                    persistInt(value);
                }
                if (!ListenerUtil.mutListener.listen(3466)) {
                    if (summaryFormat != null) {
                        if (!ListenerUtil.mutListener.listen(3465)) {
                            setSummary(String.format(summaryFormat, value));
                        }
                    }
                }
            }
        }
        final boolean isBlocking = shouldDisableDependents();
        if (!ListenerUtil.mutListener.listen(3469)) {
            if (isBlocking != wasBlocking) {
                if (!ListenerUtil.mutListener.listen(3468)) {
                    notifyDependencyChange(isBlocking);
                }
            }
        }
    }

    @Override
    public String getText() {
        return Integer.toString(value);
    }

    @Override
    protected String getPersistedString(String defaultReturnValue) {
        return String.valueOf(getPersistedInt(value));
    }

    private static Integer parseInteger(String text) {
        try {
            return Integer.parseInt(text);
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
