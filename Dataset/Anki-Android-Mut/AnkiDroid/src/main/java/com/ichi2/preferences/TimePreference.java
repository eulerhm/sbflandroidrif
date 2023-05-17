package com.ichi2.preferences;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TimePicker;
import com.ichi2.compat.CompatHelper;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

// TODO Tracked in https://github.com/ankidroid/Anki-Android/issues/5019
@SuppressWarnings("deprecation")
public class TimePreference extends android.preference.DialogPreference {

    public static final String DEFAULT_VALUE = "00:00";

    private TimePicker timePicker;

    private int hours;

    private int minutes;

    public TimePreference(Context context, AttributeSet attrs) {
        super(context, attrs);
        if (!ListenerUtil.mutListener.listen(24773)) {
            setPositiveButtonText(android.R.string.ok);
        }
        if (!ListenerUtil.mutListener.listen(24774)) {
            setNegativeButtonText(android.R.string.cancel);
        }
    }

    @Override
    protected View onCreateDialogView() {
        if (!ListenerUtil.mutListener.listen(24775)) {
            timePicker = new TimePicker(getContext());
        }
        if (!ListenerUtil.mutListener.listen(24776)) {
            timePicker.setIs24HourView(true);
        }
        return timePicker;
    }

    @Override
    protected void onSetInitialValue(boolean restorePersistedValue, Object defaultValue) {
        String time;
        if (restorePersistedValue) {
            if (null == defaultValue) {
                time = getPersistedString(DEFAULT_VALUE);
            } else {
                time = getPersistedString(defaultValue.toString());
            }
        } else {
            time = defaultValue.toString();
        }
        if (!ListenerUtil.mutListener.listen(24777)) {
            hours = parseHours(time);
        }
        if (!ListenerUtil.mutListener.listen(24778)) {
            minutes = parseMinutes(time);
        }
    }

    @Override
    protected void onBindDialogView(View view) {
        if (!ListenerUtil.mutListener.listen(24779)) {
            super.onBindDialogView(view);
        }
        if (!ListenerUtil.mutListener.listen(24780)) {
            CompatHelper.getCompat().setTime(timePicker, hours, minutes);
        }
    }

    @Override
    protected void onDialogClosed(boolean positiveResult) {
        if (!ListenerUtil.mutListener.listen(24781)) {
            super.onDialogClosed(positiveResult);
        }
        if (!ListenerUtil.mutListener.listen(24786)) {
            if (positiveResult) {
                if (!ListenerUtil.mutListener.listen(24782)) {
                    hours = CompatHelper.getCompat().getHour(timePicker);
                }
                if (!ListenerUtil.mutListener.listen(24783)) {
                    minutes = CompatHelper.getCompat().getMinute(timePicker);
                }
                final String time = String.format("%1$02d:%2$02d", hours, minutes);
                if (!ListenerUtil.mutListener.listen(24785)) {
                    if (callChangeListener(time)) {
                        if (!ListenerUtil.mutListener.listen(24784)) {
                            persistString(time);
                        }
                    }
                }
            }
        }
    }

    public static int parseHours(String time) {
        return (Integer.parseInt(time.split(":")[0]));
    }

    public static int parseMinutes(String time) {
        return (Integer.parseInt(time.split(":")[1]));
    }
}
