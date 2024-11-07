package net.programmierecke.radiodroid2.alarm;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ToggleButton;
import androidx.appcompat.widget.SwitchCompat;
import net.programmierecke.radiodroid2.BuildConfig;
import net.programmierecke.radiodroid2.R;
import net.programmierecke.radiodroid2.RadioDroidApp;
import java.util.Locale;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class ItemAdapterRadioAlarm extends ArrayAdapter<DataRadioStationAlarm> {

    private Context context;

    private RadioAlarmManager ram;

    public ItemAdapterRadioAlarm(Context context) {
        super(context, R.layout.list_item_alarm);
        if (!ListenerUtil.mutListener.listen(171)) {
            this.context = context;
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        RadioDroidApp radioDroidApp = (RadioDroidApp) context.getApplicationContext();
        if (!ListenerUtil.mutListener.listen(172)) {
            ram = radioDroidApp.getAlarmManager();
        }
        final DataRadioStationAlarm aData = getItem(position);
        View v = convertView;
        LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (!ListenerUtil.mutListener.listen(174)) {
            if (v == null) {
                if (!ListenerUtil.mutListener.listen(173)) {
                    v = vi.inflate(R.layout.list_item_alarm, null);
                }
            }
        }
        TextView tvStation = (TextView) v.findViewById(R.id.textViewStation);
        TextView tvTime = (TextView) v.findViewById(R.id.textViewTime);
        SwitchCompat s = (SwitchCompat) v.findViewById(R.id.switch1);
        ImageButton b = (ImageButton) v.findViewById(R.id.buttonDeleteAlarm);
        final ImageButton buttonRepeating = (ImageButton) v.findViewById(R.id.checkboxRepeating);
        final LinearLayout repeatDaysView = (LinearLayout) v.findViewById(R.id.repeatDaysView);
        if (!ListenerUtil.mutListener.listen(181)) {
            if ((ListenerUtil.mutListener.listen(179) ? (repeatDaysView.getChildCount() >= 1) : (ListenerUtil.mutListener.listen(178) ? (repeatDaysView.getChildCount() <= 1) : (ListenerUtil.mutListener.listen(177) ? (repeatDaysView.getChildCount() > 1) : (ListenerUtil.mutListener.listen(176) ? (repeatDaysView.getChildCount() != 1) : (ListenerUtil.mutListener.listen(175) ? (repeatDaysView.getChildCount() == 1) : (repeatDaysView.getChildCount() < 1))))))) {
                if (!ListenerUtil.mutListener.listen(180)) {
                    populateWeekDayButtons(aData, vi, repeatDaysView);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(183)) {
            buttonRepeating.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    if (!ListenerUtil.mutListener.listen(182)) {
                        ram.toggleRepeating(aData.id);
                    }
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(186)) {
            if (b != null) {
                if (!ListenerUtil.mutListener.listen(185)) {
                    b.setOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View v) {
                            if (!ListenerUtil.mutListener.listen(184)) {
                                ram.remove(aData.id);
                            }
                        }
                    });
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(188)) {
            if (tvStation != null) {
                if (!ListenerUtil.mutListener.listen(187)) {
                    tvStation.setText(aData.station.Name);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(190)) {
            if (tvTime != null) {
                if (!ListenerUtil.mutListener.listen(189)) {
                    tvTime.setText(String.format(Locale.getDefault(), "%02d:%02d", aData.hour, aData.minute));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(196)) {
            if (s != null) {
                if (!ListenerUtil.mutListener.listen(191)) {
                    s.setChecked(aData.enabled);
                }
                if (!ListenerUtil.mutListener.listen(195)) {
                    s.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                            if (!ListenerUtil.mutListener.listen(193)) {
                                if (BuildConfig.DEBUG) {
                                    if (!ListenerUtil.mutListener.listen(192)) {
                                        Log.d("ALARM", "new state:" + isChecked);
                                    }
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(194)) {
                                ram.setEnabled(aData.id, isChecked);
                            }
                        }
                    });
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(197)) {
            repeatDaysView.setVisibility(aData.repeating ? View.VISIBLE : View.GONE);
        }
        if (!ListenerUtil.mutListener.listen(198)) {
            buttonRepeating.setContentDescription(this.context.getResources().getString(aData.repeating ? R.string.image_button_dont_repeat : R.string.image_button_repeat));
        }
        return v;
    }

    private void populateWeekDayButtons(final DataRadioStationAlarm aData, LayoutInflater vi, LinearLayout repeatDays) {
        String[] mShortWeekDayStrings = this.context.getResources().getStringArray(R.array.weekdays);
        if (!ListenerUtil.mutListener.listen(214)) {
            {
                long _loopCounter1 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(213) ? (i >= 7) : (ListenerUtil.mutListener.listen(212) ? (i <= 7) : (ListenerUtil.mutListener.listen(211) ? (i > 7) : (ListenerUtil.mutListener.listen(210) ? (i != 7) : (ListenerUtil.mutListener.listen(209) ? (i == 7) : (i < 7)))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter1", ++_loopCounter1);
                    final ViewGroup viewgroup = (ViewGroup) vi.inflate(R.layout.day_button, repeatDays, false);
                    final ToggleButton button = (ToggleButton) viewgroup.getChildAt(0);
                    if (!ListenerUtil.mutListener.listen(199)) {
                        repeatDays.addView(viewgroup);
                    }
                    if (!ListenerUtil.mutListener.listen(200)) {
                        button.setId(i);
                    }
                    if (!ListenerUtil.mutListener.listen(201)) {
                        button.setText(mShortWeekDayStrings[i]);
                    }
                    if (!ListenerUtil.mutListener.listen(202)) {
                        button.setTextOn(mShortWeekDayStrings[i]);
                    }
                    if (!ListenerUtil.mutListener.listen(203)) {
                        button.setTextOff(mShortWeekDayStrings[i]);
                    }
                    if (!ListenerUtil.mutListener.listen(204)) {
                        button.setContentDescription(mShortWeekDayStrings[i]);
                    }
                    if (!ListenerUtil.mutListener.listen(206)) {
                        if (aData.weekDays.contains(i)) {
                            if (!ListenerUtil.mutListener.listen(205)) {
                                button.setChecked(true);
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(208)) {
                        button.setOnClickListener(new View.OnClickListener() {

                            @Override
                            public void onClick(View view) {
                                int bid = button.getId();
                                if (!ListenerUtil.mutListener.listen(207)) {
                                    ram.changeWeekDays(aData.id, bid);
                                }
                            }
                        });
                    }
                }
            }
        }
    }
}
