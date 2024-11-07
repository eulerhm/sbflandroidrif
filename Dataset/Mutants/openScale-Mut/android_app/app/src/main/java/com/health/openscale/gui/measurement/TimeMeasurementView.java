/* Copyright (C) 2014  olie.xdev <olie.xdev@googlemail.com>
*
*    This program is free software: you can redistribute it and/or modify
*    it under the terms of the GNU General Public License as published by
*    the Free Software Foundation, either version 3 of the License, or
*    (at your option) any later version.
*
*    This program is distributed in the hope that it will be useful,
*    but WITHOUT ANY WARRANTY; without even the implied warranty of
*    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
*    GNU General Public License for more details.
*
*    You should have received a copy of the GNU General Public License
*    along with this program.  If not, see <http://www.gnu.org/licenses/>
*/
package com.health.openscale.gui.measurement;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TimePicker;
import com.health.openscale.R;
import com.health.openscale.core.datatypes.ScaleMeasurement;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class TimeMeasurementView extends MeasurementView {

    // Don't change key value, it may be stored persistent in preferences
    public static final String KEY = "time";

    private final DateFormat timeFormat;

    private Date time;

    public TimeMeasurementView(Context context) {
        super(context, R.string.label_time, R.drawable.ic_daysleft);
        timeFormat = android.text.format.DateFormat.getTimeFormat(context);
    }

    @Override
    public String getKey() {
        return KEY;
    }

    private void setValue(Date newTime, boolean callListener) {
        if (!ListenerUtil.mutListener.listen(7910)) {
            if (!newTime.equals(time)) {
                if (!ListenerUtil.mutListener.listen(7907)) {
                    time = newTime;
                }
                if (!ListenerUtil.mutListener.listen(7909)) {
                    if (getUpdateViews()) {
                        if (!ListenerUtil.mutListener.listen(7908)) {
                            setValueView(timeFormat.format(time), callListener);
                        }
                    }
                }
            }
        }
    }

    @Override
    public void loadFrom(ScaleMeasurement measurement, ScaleMeasurement previousMeasurement) {
        if (!ListenerUtil.mutListener.listen(7911)) {
            setValue(measurement.getDateTime(), false);
        }
    }

    @Override
    public void saveTo(ScaleMeasurement measurement) {
        Calendar target = Calendar.getInstance();
        if (!ListenerUtil.mutListener.listen(7912)) {
            target.setTime(measurement.getDateTime());
        }
        Calendar source = Calendar.getInstance();
        if (!ListenerUtil.mutListener.listen(7913)) {
            source.setTime(time);
        }
        if (!ListenerUtil.mutListener.listen(7914)) {
            target.set(Calendar.HOUR_OF_DAY, source.get(Calendar.HOUR_OF_DAY));
        }
        if (!ListenerUtil.mutListener.listen(7915)) {
            target.set(Calendar.MINUTE, source.get(Calendar.MINUTE));
        }
        if (!ListenerUtil.mutListener.listen(7916)) {
            target.set(Calendar.SECOND, 0);
        }
        if (!ListenerUtil.mutListener.listen(7917)) {
            target.set(Calendar.MILLISECOND, 0);
        }
        if (!ListenerUtil.mutListener.listen(7918)) {
            measurement.setDateTime(target.getTime());
        }
    }

    @Override
    public void clearIn(ScaleMeasurement measurement) {
    }

    @Override
    public void restoreState(Bundle state) {
        if (!ListenerUtil.mutListener.listen(7919)) {
            setValue(new Date(state.getLong(getKey())), true);
        }
    }

    @Override
    public void saveState(Bundle state) {
        if (!ListenerUtil.mutListener.listen(7920)) {
            state.putLong(getKey(), time.getTime());
        }
    }

    @Override
    public String getValueAsString(boolean withUnit) {
        return timeFormat.format(time);
    }

    @Override
    protected View getInputView() {
        TimePicker timePicker = new TimePicker(getContext());
        if (!ListenerUtil.mutListener.listen(7921)) {
            timePicker.setPadding(0, 15, 0, 0);
        }
        Calendar cal = Calendar.getInstance();
        if (!ListenerUtil.mutListener.listen(7922)) {
            cal.setTime(time);
        }
        if (!ListenerUtil.mutListener.listen(7923)) {
            timePicker.setCurrentHour(cal.get(Calendar.HOUR_OF_DAY));
        }
        if (!ListenerUtil.mutListener.listen(7924)) {
            timePicker.setCurrentMinute(cal.get(Calendar.MINUTE));
        }
        if (!ListenerUtil.mutListener.listen(7925)) {
            timePicker.setIs24HourView(android.text.format.DateFormat.is24HourFormat(getContext()));
        }
        return timePicker;
    }

    @Override
    protected boolean validateAndSetInput(View view) {
        TimePicker timePicker = (TimePicker) view;
        Calendar cal = Calendar.getInstance();
        if (!ListenerUtil.mutListener.listen(7926)) {
            cal.setTime(time);
        }
        if (!ListenerUtil.mutListener.listen(7927)) {
            cal.set(Calendar.HOUR_OF_DAY, timePicker.getCurrentHour());
        }
        if (!ListenerUtil.mutListener.listen(7928)) {
            cal.set(Calendar.MINUTE, timePicker.getCurrentMinute());
        }
        if (!ListenerUtil.mutListener.listen(7929)) {
            cal.set(Calendar.SECOND, 0);
        }
        if (!ListenerUtil.mutListener.listen(7930)) {
            cal.set(Calendar.MILLISECOND, 0);
        }
        if (!ListenerUtil.mutListener.listen(7931)) {
            setValue(cal.getTime(), true);
        }
        return true;
    }
}
