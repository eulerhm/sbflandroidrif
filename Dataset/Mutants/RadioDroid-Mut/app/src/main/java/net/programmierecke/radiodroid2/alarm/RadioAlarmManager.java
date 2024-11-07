package net.programmierecke.radiodroid2.alarm;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.preference.PreferenceManager;
import android.util.Log;
import net.programmierecke.radiodroid2.BuildConfig;
import net.programmierecke.radiodroid2.station.DataRadioStation;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Observable;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class RadioAlarmManager {

    private static final int ONE_DAY_IN_MILLIS = 24 * 60 * 60 * 1000;

    private Context context;

    private List<DataRadioStationAlarm> list = new ArrayList<DataRadioStationAlarm>();

    private class AlarmsObservable extends Observable {

        @Override
        public synchronized boolean hasChanged() {
            return true;
        }
    }

    private Observable savedAlarmsObservable = new AlarmsObservable();

    public RadioAlarmManager(Context context) {
        if (!ListenerUtil.mutListener.listen(215)) {
            this.context = context;
        }
        if (!ListenerUtil.mutListener.listen(216)) {
            load();
        }
    }

    public Observable getSavedAlarmsObservable() {
        return savedAlarmsObservable;
    }

    public void add(DataRadioStation station, int hour, int minute) {
        if (!ListenerUtil.mutListener.listen(218)) {
            if (BuildConfig.DEBUG) {
                if (!ListenerUtil.mutListener.listen(217)) {
                    Log.d("ALARM", "added station:" + station.Name);
                }
            }
        }
        DataRadioStationAlarm alarm = new DataRadioStationAlarm();
        if (!ListenerUtil.mutListener.listen(219)) {
            alarm.station = station;
        }
        if (!ListenerUtil.mutListener.listen(220)) {
            alarm.hour = hour;
        }
        if (!ListenerUtil.mutListener.listen(221)) {
            alarm.minute = minute;
        }
        if (!ListenerUtil.mutListener.listen(222)) {
            alarm.weekDays = new ArrayList<>();
        }
        if (!ListenerUtil.mutListener.listen(223)) {
            alarm.id = getFreeId();
        }
        if (!ListenerUtil.mutListener.listen(224)) {
            list.add(alarm);
        }
        if (!ListenerUtil.mutListener.listen(225)) {
            save();
        }
        if (!ListenerUtil.mutListener.listen(226)) {
            setEnabled(alarm.id, true);
        }
    }

    public DataRadioStationAlarm[] getList() {
        return list.toArray(new DataRadioStationAlarm[0]);
    }

    int getFreeId() {
        int i = 0;
        if (!ListenerUtil.mutListener.listen(228)) {
            {
                long _loopCounter2 = 0;
                while (!checkIdFree(i)) {
                    ListenerUtil.loopListener.listen("_loopCounter2", ++_loopCounter2);
                    if (!ListenerUtil.mutListener.listen(227)) {
                        i++;
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(230)) {
            if (BuildConfig.DEBUG) {
                if (!ListenerUtil.mutListener.listen(229)) {
                    Log.d("ALARM", "new free id:" + i);
                }
            }
        }
        return i;
    }

    boolean checkIdFree(int id) {
        if (!ListenerUtil.mutListener.listen(232)) {
            {
                long _loopCounter3 = 0;
                for (DataRadioStationAlarm alarm : list) {
                    ListenerUtil.loopListener.listen("_loopCounter3", ++_loopCounter3);
                    if (!ListenerUtil.mutListener.listen(231)) {
                        if (alarm.id == id) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    void save() {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPref.edit();
        String items = "";
        if (!ListenerUtil.mutListener.listen(244)) {
            {
                long _loopCounter4 = 0;
                for (DataRadioStationAlarm alarm : list) {
                    ListenerUtil.loopListener.listen("_loopCounter4", ++_loopCounter4);
                    if (!ListenerUtil.mutListener.listen(234)) {
                        if (BuildConfig.DEBUG) {
                            if (!ListenerUtil.mutListener.listen(233)) {
                                Log.d("ALARM", "save item:" + alarm.id + "/" + alarm.station.Name);
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(235)) {
                        editor.putString("alarm." + alarm.id + ".station", alarm.station.toJson().toString());
                    }
                    if (!ListenerUtil.mutListener.listen(236)) {
                        editor.putInt("alarm." + alarm.id + ".timeHour", alarm.hour);
                    }
                    if (!ListenerUtil.mutListener.listen(237)) {
                        editor.putInt("alarm." + alarm.id + ".timeMinutes", alarm.minute);
                    }
                    if (!ListenerUtil.mutListener.listen(238)) {
                        editor.putBoolean("alarm." + alarm.id + ".enabled", alarm.enabled);
                    }
                    if (!ListenerUtil.mutListener.listen(239)) {
                        editor.putBoolean("alarm." + alarm.id + ".repeating", alarm.repeating);
                    }
                    Gson gson = new Gson();
                    String weekdaysString = gson.toJson(alarm.weekDays);
                    if (!ListenerUtil.mutListener.listen(240)) {
                        editor.putString("alarm." + alarm.id + ".weekDays", weekdaysString);
                    }
                    if (!ListenerUtil.mutListener.listen(243)) {
                        if (items.equals("")) {
                            if (!ListenerUtil.mutListener.listen(242)) {
                                items = "" + alarm.id;
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(241)) {
                                items = items + "," + alarm.id;
                            }
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(245)) {
            editor.putString("alarm.ids", items);
        }
        if (!ListenerUtil.mutListener.listen(246)) {
            editor.commit();
        }
        if (!ListenerUtil.mutListener.listen(247)) {
            savedAlarmsObservable.notifyObservers();
        }
    }

    public void load() {
        if (!ListenerUtil.mutListener.listen(248)) {
            list.clear();
        }
        if (!ListenerUtil.mutListener.listen(250)) {
            if (BuildConfig.DEBUG) {
                if (!ListenerUtil.mutListener.listen(249)) {
                    Log.d("ALARM", "load()");
                }
            }
        }
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
        String ids = sharedPref.getString("alarm.ids", "");
        if (!ListenerUtil.mutListener.listen(265)) {
            if (!ids.equals("")) {
                String[] idsArr = ids.split(",");
                if (!ListenerUtil.mutListener.listen(253)) {
                    if (BuildConfig.DEBUG) {
                        if (!ListenerUtil.mutListener.listen(252)) {
                            Log.d("ALARM", "load() - " + idsArr.length);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(264)) {
                    {
                        long _loopCounter5 = 0;
                        for (String id : idsArr) {
                            ListenerUtil.loopListener.listen("_loopCounter5", ++_loopCounter5);
                            DataRadioStationAlarm alarm = new DataRadioStationAlarm();
                            if (!ListenerUtil.mutListener.listen(254)) {
                                alarm.station = DataRadioStation.DecodeJsonSingle(sharedPref.getString("alarm." + id + ".station", null));
                            }
                            String weekDaysString = sharedPref.getString("alarm." + id + ".weekDays", "[]");
                            Gson gson = new Gson();
                            if (!ListenerUtil.mutListener.listen(255)) {
                                alarm.weekDays = gson.fromJson(weekDaysString, new TypeToken<List<Integer>>() {
                                }.getType());
                            }
                            if (!ListenerUtil.mutListener.listen(256)) {
                                alarm.hour = sharedPref.getInt("alarm." + id + ".timeHour", 0);
                            }
                            if (!ListenerUtil.mutListener.listen(257)) {
                                alarm.minute = sharedPref.getInt("alarm." + id + ".timeMinutes", 0);
                            }
                            if (!ListenerUtil.mutListener.listen(258)) {
                                alarm.enabled = sharedPref.getBoolean("alarm." + id + ".enabled", false);
                            }
                            if (!ListenerUtil.mutListener.listen(259)) {
                                alarm.repeating = sharedPref.getBoolean("alarm." + id + ".repeating", false);
                            }
                            try {
                                if (!ListenerUtil.mutListener.listen(261)) {
                                    alarm.id = Integer.parseInt(id);
                                }
                                if (!ListenerUtil.mutListener.listen(263)) {
                                    if (alarm.station != null) {
                                        if (!ListenerUtil.mutListener.listen(262)) {
                                            list.add(alarm);
                                        }
                                    }
                                }
                            } catch (Exception e) {
                                if (!ListenerUtil.mutListener.listen(260)) {
                                    Log.e("ALARM", "could not decode:" + id);
                                }
                            }
                        }
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(251)) {
                    Log.w("ALARM", "empty load() string");
                }
            }
        }
    }

    public void setEnabled(int alarmId, boolean enabled) {
        DataRadioStationAlarm alarm = getById(alarmId);
        if (!ListenerUtil.mutListener.listen(272)) {
            if (alarm != null) {
                if (!ListenerUtil.mutListener.listen(271)) {
                    if (enabled != alarm.enabled) {
                        if (!ListenerUtil.mutListener.listen(266)) {
                            alarm.enabled = enabled;
                        }
                        if (!ListenerUtil.mutListener.listen(267)) {
                            save();
                        }
                        if (!ListenerUtil.mutListener.listen(270)) {
                            if (enabled) {
                                if (!ListenerUtil.mutListener.listen(269)) {
                                    start(alarmId);
                                }
                            } else {
                                if (!ListenerUtil.mutListener.listen(268)) {
                                    stop(alarmId);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    DataRadioStationAlarm getById(int id) {
        if (!ListenerUtil.mutListener.listen(274)) {
            {
                long _loopCounter6 = 0;
                for (DataRadioStationAlarm alarm : list) {
                    ListenerUtil.loopListener.listen("_loopCounter6", ++_loopCounter6);
                    if (!ListenerUtil.mutListener.listen(273)) {
                        if (id == alarm.id) {
                            return alarm;
                        }
                    }
                }
            }
        }
        return null;
    }

    void start(int alarmId) {
        DataRadioStationAlarm alarm = getById(alarmId);
        if (!ListenerUtil.mutListener.listen(339)) {
            if (alarm != null) {
                if (!ListenerUtil.mutListener.listen(275)) {
                    stop(alarmId);
                }
                Intent intent = new Intent(context, AlarmReceiver.class);
                if (!ListenerUtil.mutListener.listen(276)) {
                    intent.putExtra("id", alarmId);
                }
                PendingIntent alarmIntent = PendingIntent.getBroadcast(context, alarmId, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                AlarmManager alarmMgr = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
                Calendar calendar = Calendar.getInstance();
                if (!ListenerUtil.mutListener.listen(277)) {
                    calendar.setTimeInMillis(System.currentTimeMillis());
                }
                if (!ListenerUtil.mutListener.listen(278)) {
                    calendar.set(Calendar.HOUR_OF_DAY, alarm.hour);
                }
                if (!ListenerUtil.mutListener.listen(279)) {
                    calendar.set(Calendar.MINUTE, alarm.minute);
                }
                if (!ListenerUtil.mutListener.listen(280)) {
                    calendar.set(Calendar.SECOND, 0);
                }
                if (!ListenerUtil.mutListener.listen(297)) {
                    // add 1 min, to ignore already fired events
                    if ((ListenerUtil.mutListener.listen(289) ? (calendar.getTimeInMillis() >= (ListenerUtil.mutListener.listen(284) ? (System.currentTimeMillis() % 60) : (ListenerUtil.mutListener.listen(283) ? (System.currentTimeMillis() / 60) : (ListenerUtil.mutListener.listen(282) ? (System.currentTimeMillis() * 60) : (ListenerUtil.mutListener.listen(281) ? (System.currentTimeMillis() - 60) : (System.currentTimeMillis() + 60)))))) : (ListenerUtil.mutListener.listen(288) ? (calendar.getTimeInMillis() <= (ListenerUtil.mutListener.listen(284) ? (System.currentTimeMillis() % 60) : (ListenerUtil.mutListener.listen(283) ? (System.currentTimeMillis() / 60) : (ListenerUtil.mutListener.listen(282) ? (System.currentTimeMillis() * 60) : (ListenerUtil.mutListener.listen(281) ? (System.currentTimeMillis() - 60) : (System.currentTimeMillis() + 60)))))) : (ListenerUtil.mutListener.listen(287) ? (calendar.getTimeInMillis() > (ListenerUtil.mutListener.listen(284) ? (System.currentTimeMillis() % 60) : (ListenerUtil.mutListener.listen(283) ? (System.currentTimeMillis() / 60) : (ListenerUtil.mutListener.listen(282) ? (System.currentTimeMillis() * 60) : (ListenerUtil.mutListener.listen(281) ? (System.currentTimeMillis() - 60) : (System.currentTimeMillis() + 60)))))) : (ListenerUtil.mutListener.listen(286) ? (calendar.getTimeInMillis() != (ListenerUtil.mutListener.listen(284) ? (System.currentTimeMillis() % 60) : (ListenerUtil.mutListener.listen(283) ? (System.currentTimeMillis() / 60) : (ListenerUtil.mutListener.listen(282) ? (System.currentTimeMillis() * 60) : (ListenerUtil.mutListener.listen(281) ? (System.currentTimeMillis() - 60) : (System.currentTimeMillis() + 60)))))) : (ListenerUtil.mutListener.listen(285) ? (calendar.getTimeInMillis() == (ListenerUtil.mutListener.listen(284) ? (System.currentTimeMillis() % 60) : (ListenerUtil.mutListener.listen(283) ? (System.currentTimeMillis() / 60) : (ListenerUtil.mutListener.listen(282) ? (System.currentTimeMillis() * 60) : (ListenerUtil.mutListener.listen(281) ? (System.currentTimeMillis() - 60) : (System.currentTimeMillis() + 60)))))) : (calendar.getTimeInMillis() < (ListenerUtil.mutListener.listen(284) ? (System.currentTimeMillis() % 60) : (ListenerUtil.mutListener.listen(283) ? (System.currentTimeMillis() / 60) : (ListenerUtil.mutListener.listen(282) ? (System.currentTimeMillis() * 60) : (ListenerUtil.mutListener.listen(281) ? (System.currentTimeMillis() - 60) : (System.currentTimeMillis() + 60)))))))))))) {
                        if (!ListenerUtil.mutListener.listen(291)) {
                            if (BuildConfig.DEBUG) {
                                if (!ListenerUtil.mutListener.listen(290)) {
                                    Log.d("ALARM", "moved ahead one day");
                                }
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(296)) {
                            calendar.setTimeInMillis((ListenerUtil.mutListener.listen(295) ? (calendar.getTimeInMillis() % ONE_DAY_IN_MILLIS) : (ListenerUtil.mutListener.listen(294) ? (calendar.getTimeInMillis() / ONE_DAY_IN_MILLIS) : (ListenerUtil.mutListener.listen(293) ? (calendar.getTimeInMillis() * ONE_DAY_IN_MILLIS) : (ListenerUtil.mutListener.listen(292) ? (calendar.getTimeInMillis() - ONE_DAY_IN_MILLIS) : (calendar.getTimeInMillis() + ONE_DAY_IN_MILLIS))))));
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(317)) {
                    if (alarm.repeating) {
                        Integer currentDayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
                        if (!ListenerUtil.mutListener.listen(298)) {
                            Collections.sort(alarm.weekDays);
                        }
                        Integer limiter = 6;
                        if (!ListenerUtil.mutListener.listen(316)) {
                            {
                                long _loopCounter7 = 0;
                                while ((ListenerUtil.mutListener.listen(315) ? (!alarm.weekDays.contains((ListenerUtil.mutListener.listen(309) ? (currentDayOfWeek % 1) : (ListenerUtil.mutListener.listen(308) ? (currentDayOfWeek / 1) : (ListenerUtil.mutListener.listen(307) ? (currentDayOfWeek * 1) : (ListenerUtil.mutListener.listen(306) ? (currentDayOfWeek + 1) : (currentDayOfWeek - 1)))))) || (ListenerUtil.mutListener.listen(314) ? (limiter >= 0) : (ListenerUtil.mutListener.listen(313) ? (limiter <= 0) : (ListenerUtil.mutListener.listen(312) ? (limiter < 0) : (ListenerUtil.mutListener.listen(311) ? (limiter != 0) : (ListenerUtil.mutListener.listen(310) ? (limiter == 0) : (limiter > 0))))))) : (!alarm.weekDays.contains((ListenerUtil.mutListener.listen(309) ? (currentDayOfWeek % 1) : (ListenerUtil.mutListener.listen(308) ? (currentDayOfWeek / 1) : (ListenerUtil.mutListener.listen(307) ? (currentDayOfWeek * 1) : (ListenerUtil.mutListener.listen(306) ? (currentDayOfWeek + 1) : (currentDayOfWeek - 1)))))) && (ListenerUtil.mutListener.listen(314) ? (limiter >= 0) : (ListenerUtil.mutListener.listen(313) ? (limiter <= 0) : (ListenerUtil.mutListener.listen(312) ? (limiter < 0) : (ListenerUtil.mutListener.listen(311) ? (limiter != 0) : (ListenerUtil.mutListener.listen(310) ? (limiter == 0) : (limiter > 0))))))))) {
                                    ListenerUtil.loopListener.listen("_loopCounter7", ++_loopCounter7);
                                    if (!ListenerUtil.mutListener.listen(303)) {
                                        calendar.setTimeInMillis((ListenerUtil.mutListener.listen(302) ? (calendar.getTimeInMillis() % ONE_DAY_IN_MILLIS) : (ListenerUtil.mutListener.listen(301) ? (calendar.getTimeInMillis() / ONE_DAY_IN_MILLIS) : (ListenerUtil.mutListener.listen(300) ? (calendar.getTimeInMillis() * ONE_DAY_IN_MILLIS) : (ListenerUtil.mutListener.listen(299) ? (calendar.getTimeInMillis() - ONE_DAY_IN_MILLIS) : (calendar.getTimeInMillis() + ONE_DAY_IN_MILLIS))))));
                                    }
                                    if (!ListenerUtil.mutListener.listen(304)) {
                                        currentDayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
                                    }
                                    if (!ListenerUtil.mutListener.listen(305)) {
                                        limiter--;
                                    }
                                }
                            }
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(318)) {
                    Log.d("ALARM", "started:" + alarmId + " " + calendar.get(Calendar.DAY_OF_WEEK) + " " + calendar.get(Calendar.DAY_OF_MONTH) + "." + calendar.get(Calendar.MONTH) + " " + calendar.get(Calendar.HOUR_OF_DAY) + ":" + calendar.get(Calendar.MINUTE));
                }
                if (!ListenerUtil.mutListener.listen(338)) {
                    if ((ListenerUtil.mutListener.listen(323) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(322) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(321) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(320) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(319) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.LOLLIPOP) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP))))))) {
                        if (!ListenerUtil.mutListener.listen(336)) {
                            if (BuildConfig.DEBUG) {
                                if (!ListenerUtil.mutListener.listen(335)) {
                                    Log.d("ALARM", "START setAlarmClock");
                                }
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(337)) {
                            alarmMgr.setAlarmClock(new AlarmManager.AlarmClockInfo(calendar.getTimeInMillis(), alarmIntent), alarmIntent);
                        }
                    } else if ((ListenerUtil.mutListener.listen(328) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.KITKAT) : (ListenerUtil.mutListener.listen(327) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) : (ListenerUtil.mutListener.listen(326) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) : (ListenerUtil.mutListener.listen(325) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.KITKAT) : (ListenerUtil.mutListener.listen(324) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.KITKAT) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT))))))) {
                        if (!ListenerUtil.mutListener.listen(333)) {
                            if (BuildConfig.DEBUG) {
                                if (!ListenerUtil.mutListener.listen(332)) {
                                    Log.d("ALARM", "START setExact");
                                }
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(334)) {
                            alarmMgr.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), alarmIntent);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(330)) {
                            if (BuildConfig.DEBUG) {
                                if (!ListenerUtil.mutListener.listen(329)) {
                                    Log.d("ALARM", "START set");
                                }
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(331)) {
                            alarmMgr.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), alarmIntent);
                        }
                    }
                }
            }
        }
    }

    void stop(int alarmId) {
        DataRadioStationAlarm alarm = getById(alarmId);
        if (!ListenerUtil.mutListener.listen(343)) {
            if (alarm != null) {
                if (!ListenerUtil.mutListener.listen(341)) {
                    if (BuildConfig.DEBUG) {
                        if (!ListenerUtil.mutListener.listen(340)) {
                            Log.d("ALARM", "stopped:" + alarmId);
                        }
                    }
                }
                Intent intent = new Intent(context, AlarmReceiver.class);
                PendingIntent alarmIntent = PendingIntent.getBroadcast(context, alarmId, intent, 0);
                AlarmManager alarmMgr = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
                if (!ListenerUtil.mutListener.listen(342)) {
                    alarmMgr.cancel(alarmIntent);
                }
            }
        }
    }

    public void changeTime(int alarmId, int hourOfDay, int minute) {
        DataRadioStationAlarm alarm = getById(alarmId);
        if (!ListenerUtil.mutListener.listen(350)) {
            if (alarm != null) {
                if (!ListenerUtil.mutListener.listen(344)) {
                    alarm.hour = hourOfDay;
                }
                if (!ListenerUtil.mutListener.listen(345)) {
                    alarm.minute = minute;
                }
                if (!ListenerUtil.mutListener.listen(346)) {
                    save();
                }
                if (!ListenerUtil.mutListener.listen(349)) {
                    if (alarm.enabled) {
                        if (!ListenerUtil.mutListener.listen(347)) {
                            stop(alarmId);
                        }
                        if (!ListenerUtil.mutListener.listen(348)) {
                            start(alarmId);
                        }
                    }
                }
            }
        }
    }

    public void changeWeekDays(int alarmId, int weekday) {
        DataRadioStationAlarm alarm = getById(alarmId);
        if (!ListenerUtil.mutListener.listen(363)) {
            if (alarm != null) {
                if (!ListenerUtil.mutListener.listen(352)) {
                    if (alarm.weekDays == null) {
                        if (!ListenerUtil.mutListener.listen(351)) {
                            alarm.weekDays = new ArrayList<>();
                        }
                    }
                }
                int position = alarm.weekDays.indexOf(weekday);
                if (!ListenerUtil.mutListener.listen(360)) {
                    if ((ListenerUtil.mutListener.listen(357) ? (position >= -1) : (ListenerUtil.mutListener.listen(356) ? (position <= -1) : (ListenerUtil.mutListener.listen(355) ? (position > -1) : (ListenerUtil.mutListener.listen(354) ? (position < -1) : (ListenerUtil.mutListener.listen(353) ? (position != -1) : (position == -1))))))) {
                        if (!ListenerUtil.mutListener.listen(359)) {
                            alarm.weekDays.add(weekday);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(358)) {
                            alarm.weekDays.remove(position);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(361)) {
                    save();
                }
                if (!ListenerUtil.mutListener.listen(362)) {
                    start(alarmId);
                }
            }
        }
    }

    public void remove(int id) {
        DataRadioStationAlarm alarm = getById(id);
        if (!ListenerUtil.mutListener.listen(367)) {
            if (alarm != null) {
                if (!ListenerUtil.mutListener.listen(364)) {
                    stop(id);
                }
                if (!ListenerUtil.mutListener.listen(365)) {
                    list.remove(alarm);
                }
                if (!ListenerUtil.mutListener.listen(366)) {
                    save();
                }
            }
        }
    }

    public DataRadioStation getStation(int stationId) {
        DataRadioStationAlarm alarm = getById(stationId);
        if (!ListenerUtil.mutListener.listen(368)) {
            if (alarm != null) {
                return alarm.station;
            }
        }
        return null;
    }

    public void resetAllAlarms() {
        if (!ListenerUtil.mutListener.listen(373)) {
            {
                long _loopCounter8 = 0;
                for (DataRadioStationAlarm alarm : list) {
                    ListenerUtil.loopListener.listen("_loopCounter8", ++_loopCounter8);
                    if (!ListenerUtil.mutListener.listen(372)) {
                        if (alarm.enabled) {
                            if (!ListenerUtil.mutListener.listen(370)) {
                                if (BuildConfig.DEBUG) {
                                    if (!ListenerUtil.mutListener.listen(369)) {
                                        Log.d("ALARM", "started alarm with id:" + alarm.id);
                                    }
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(371)) {
                                start(alarm.id);
                            }
                        }
                    }
                }
            }
        }
    }

    public void toggleRepeating(int id) {
        DataRadioStationAlarm alarm = getById(id);
        if (!ListenerUtil.mutListener.listen(377)) {
            if (alarm != null) {
                if (!ListenerUtil.mutListener.listen(374)) {
                    alarm.repeating = !alarm.repeating;
                }
                if (!ListenerUtil.mutListener.listen(375)) {
                    save();
                }
                if (!ListenerUtil.mutListener.listen(376)) {
                    start(id);
                }
            }
        }
    }
}
