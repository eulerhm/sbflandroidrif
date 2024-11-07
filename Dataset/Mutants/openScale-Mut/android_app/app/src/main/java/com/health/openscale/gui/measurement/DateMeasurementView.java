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

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import com.health.openscale.R;
import com.health.openscale.core.datatypes.ScaleMeasurement;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class DateMeasurementView extends MeasurementView {

    // Don't change key value, it may be stored persistent in preferences
    public static final String KEY = "date";

    private final DateFormat dateFormat;

    private Date date;

    public DateMeasurementView(Context context) {
        super(context, R.string.label_date, R.drawable.ic_lastmonth);
        dateFormat = DateFormat.getDateInstance();
    }

    @Override
    public String getKey() {
        return KEY;
    }

    private void setValue(Date newDate, boolean callListener) {
        if (!ListenerUtil.mutListener.listen(6837)) {
            if (!newDate.equals(date)) {
                if (!ListenerUtil.mutListener.listen(6834)) {
                    date = newDate;
                }
                if (!ListenerUtil.mutListener.listen(6836)) {
                    if (getUpdateViews()) {
                        if (!ListenerUtil.mutListener.listen(6835)) {
                            setValueView(dateFormat.format(date), callListener);
                        }
                    }
                }
            }
        }
    }

    @Override
    public void loadFrom(ScaleMeasurement measurement, ScaleMeasurement previousMeasurement) {
        if (!ListenerUtil.mutListener.listen(6838)) {
            setValue(measurement.getDateTime(), false);
        }
    }

    @Override
    public void saveTo(ScaleMeasurement measurement) {
        Calendar target = Calendar.getInstance();
        if (!ListenerUtil.mutListener.listen(6839)) {
            target.setTime(measurement.getDateTime());
        }
        Calendar source = Calendar.getInstance();
        if (!ListenerUtil.mutListener.listen(6840)) {
            source.setTime(date);
        }
        if (!ListenerUtil.mutListener.listen(6841)) {
            target.set(source.get(Calendar.YEAR), source.get(Calendar.MONTH), source.get(Calendar.DAY_OF_MONTH));
        }
        if (!ListenerUtil.mutListener.listen(6842)) {
            measurement.setDateTime(target.getTime());
        }
    }

    @Override
    public void clearIn(ScaleMeasurement measurement) {
    }

    @Override
    public void restoreState(Bundle state) {
        if (!ListenerUtil.mutListener.listen(6843)) {
            setValue(new Date(state.getLong(getKey())), true);
        }
    }

    @Override
    public void saveState(Bundle state) {
        if (!ListenerUtil.mutListener.listen(6844)) {
            state.putLong(getKey(), date.getTime());
        }
    }

    @Override
    public String getValueAsString(boolean withUnit) {
        return dateFormat.format(date);
    }

    @Override
    protected View getInputView() {
        Calendar cal = Calendar.getInstance();
        if (!ListenerUtil.mutListener.listen(6845)) {
            cal.setTime(date);
        }
        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), null, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH));
        return datePickerDialog.getDatePicker();
    }

    @Override
    protected boolean validateAndSetInput(View view) {
        DatePicker datePicker = (DatePicker) view;
        Calendar cal = Calendar.getInstance();
        if (!ListenerUtil.mutListener.listen(6846)) {
            cal.setTime(date);
        }
        if (!ListenerUtil.mutListener.listen(6847)) {
            cal.set(datePicker.getYear(), datePicker.getMonth(), datePicker.getDayOfMonth());
        }
        if (!ListenerUtil.mutListener.listen(6848)) {
            setValue(cal.getTime(), true);
        }
        return true;
    }
}
