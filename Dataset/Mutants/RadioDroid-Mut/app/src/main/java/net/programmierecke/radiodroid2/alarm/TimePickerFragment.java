package net.programmierecke.radiodroid2.alarm;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.widget.TimePicker;
import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import net.programmierecke.radiodroid2.Utils;
import java.util.Calendar;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {

    private TimePickerDialog.OnTimeSetListener callback;

    private int initialHour;

    private int initialMinute;

    public TimePickerFragment() {
        final Calendar c = Calendar.getInstance();
        if (!ListenerUtil.mutListener.listen(378)) {
            this.initialHour = c.get(Calendar.HOUR_OF_DAY);
        }
        if (!ListenerUtil.mutListener.listen(379)) {
            this.initialMinute = c.get(Calendar.MINUTE);
        }
    }

    public TimePickerFragment(int initialHour, int initialMinute) {
        if (!ListenerUtil.mutListener.listen(380)) {
            this.initialHour = initialHour;
        }
        if (!ListenerUtil.mutListener.listen(381)) {
            this.initialMinute = initialMinute;
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Create a new instance of TimePickerDialog and return it
        return new TimePickerDialog(getActivity(), Utils.getTimePickerThemeResId(getActivity()), this, initialHour, initialMinute, DateFormat.is24HourFormat(getActivity()));
    }

    public void setCallback(TimePickerDialog.OnTimeSetListener callback) {
        if (!ListenerUtil.mutListener.listen(382)) {
            this.callback = callback;
        }
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        if (!ListenerUtil.mutListener.listen(385)) {
            // this is needed, because on some devices onTimeSet is called twice!!
            if (callback != null) {
                if (!ListenerUtil.mutListener.listen(383)) {
                    callback.onTimeSet(view, hourOfDay, minute);
                }
                if (!ListenerUtil.mutListener.listen(384)) {
                    callback = null;
                }
            }
        }
    }
}
