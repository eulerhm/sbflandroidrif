package net.programmierecke.radiodroid2.alarm;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TimePicker;
import androidx.fragment.app.Fragment;
import net.programmierecke.radiodroid2.R;
import net.programmierecke.radiodroid2.RadioDroidApp;
import java.util.Observer;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class FragmentAlarm extends Fragment implements TimePickerDialog.OnTimeSetListener {

    private RadioAlarmManager ram;

    private ItemAdapterRadioAlarm adapterRadioAlarm;

    private ListView lvAlarms;

    private Observer alarmsObserver;

    public FragmentAlarm() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        RadioDroidApp radioDroidApp = (RadioDroidApp) getActivity().getApplication();
        if (!ListenerUtil.mutListener.listen(150)) {
            ram = radioDroidApp.getAlarmManager();
        }
        View view = inflater.inflate(R.layout.layout_alarms, container, false);
        if (!ListenerUtil.mutListener.listen(151)) {
            adapterRadioAlarm = new ItemAdapterRadioAlarm(getActivity());
        }
        if (!ListenerUtil.mutListener.listen(152)) {
            lvAlarms = view.findViewById(R.id.listViewAlarms);
        }
        if (!ListenerUtil.mutListener.listen(153)) {
            lvAlarms.setAdapter(adapterRadioAlarm);
        }
        if (!ListenerUtil.mutListener.listen(154)) {
            lvAlarms.setClickable(true);
        }
        if (!ListenerUtil.mutListener.listen(157)) {
            lvAlarms.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Object anObject = parent.getItemAtPosition(position);
                    if (!ListenerUtil.mutListener.listen(156)) {
                        if (anObject instanceof DataRadioStationAlarm) {
                            if (!ListenerUtil.mutListener.listen(155)) {
                                ClickOnItem((DataRadioStationAlarm) anObject);
                            }
                        }
                    }
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(158)) {
            alarmsObserver = (o, arg) -> RefreshListAndView();
        }
        return view;
    }

    @Override
    public void onResume() {
        if (!ListenerUtil.mutListener.listen(159)) {
            super.onResume();
        }
        if (!ListenerUtil.mutListener.listen(160)) {
            RefreshListAndView();
        }
        if (!ListenerUtil.mutListener.listen(161)) {
            ram.getSavedAlarmsObservable().addObserver(alarmsObserver);
        }
    }

    @Override
    public void onPause() {
        if (!ListenerUtil.mutListener.listen(162)) {
            super.onPause();
        }
        if (!ListenerUtil.mutListener.listen(163)) {
            ram.getSavedAlarmsObservable().deleteObserver(alarmsObserver);
        }
    }

    private void RefreshListAndView() {
        if (!ListenerUtil.mutListener.listen(164)) {
            adapterRadioAlarm.clear();
        }
        if (!ListenerUtil.mutListener.listen(165)) {
            adapterRadioAlarm.addAll(ram.getList());
        }
    }

    DataRadioStationAlarm clickedAlarm = null;

    private void ClickOnItem(DataRadioStationAlarm anObject) {
        if (!ListenerUtil.mutListener.listen(166)) {
            clickedAlarm = anObject;
        }
        TimePickerFragment newFragment = new TimePickerFragment(clickedAlarm.hour, clickedAlarm.minute);
        if (!ListenerUtil.mutListener.listen(167)) {
            newFragment.setCallback(this);
        }
        if (!ListenerUtil.mutListener.listen(168)) {
            newFragment.show(getActivity().getSupportFragmentManager(), "timePicker");
        }
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        if (!ListenerUtil.mutListener.listen(169)) {
            ram.changeTime(clickedAlarm.id, hourOfDay, minute);
        }
        if (!ListenerUtil.mutListener.listen(170)) {
            view.invalidate();
        }
    }

    public RadioAlarmManager getRam() {
        return ram;
    }
}
