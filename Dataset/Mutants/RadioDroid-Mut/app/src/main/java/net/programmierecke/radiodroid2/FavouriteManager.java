package net.programmierecke.radiodroid2;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ShortcutInfo;
import android.content.pm.ShortcutManager;
import android.os.Build;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import net.programmierecke.radiodroid2.station.DataRadioStation;
import java.util.ArrayList;
import static java.lang.Math.min;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class FavouriteManager extends StationSaveManager {

    @Override
    protected String getSaveId() {
        return "favourites";
    }

    public FavouriteManager(Context ctx) {
        super(ctx);
        if (!ListenerUtil.mutListener.listen(4206)) {
            setStationStatusListener((station, favourite) -> {
                Intent local = new Intent();
                local.setAction(DataRadioStation.RADIO_STATION_LOCAL_INFO_CHAGED);
                local.putExtra(DataRadioStation.RADIO_STATION_UUID, station.StationUuid);
                LocalBroadcastManager.getInstance(ctx).sendBroadcast(local);
            });
        }
    }

    @Override
    public void add(DataRadioStation station) {
        if (!ListenerUtil.mutListener.listen(4208)) {
            if (!has(station.StationUuid)) {
                if (!ListenerUtil.mutListener.listen(4207)) {
                    super.add(station);
                }
            }
        }
    }

    @Override
    public void restore(DataRadioStation station, int pos) {
        if (!ListenerUtil.mutListener.listen(4210)) {
            if (!has(station.StationUuid)) {
                if (!ListenerUtil.mutListener.listen(4209)) {
                    super.restore(station, pos);
                }
            }
        }
    }

    @Override
    void Load() {
        if (!ListenerUtil.mutListener.listen(4211)) {
            super.Load();
        }
        if (!ListenerUtil.mutListener.listen(4212)) {
            updateShortcuts();
        }
    }

    @Override
    void Save() {
        if (!ListenerUtil.mutListener.listen(4213)) {
            super.Save();
        }
        if (!ListenerUtil.mutListener.listen(4214)) {
            updateShortcuts();
        }
    }

    public void updateShortcuts() {
        if (!ListenerUtil.mutListener.listen(4228)) {
            if ((ListenerUtil.mutListener.listen(4220) ? ((ListenerUtil.mutListener.listen(4219) ? (Build.VERSION.SDK_INT <= 25) : (ListenerUtil.mutListener.listen(4218) ? (Build.VERSION.SDK_INT > 25) : (ListenerUtil.mutListener.listen(4217) ? (Build.VERSION.SDK_INT < 25) : (ListenerUtil.mutListener.listen(4216) ? (Build.VERSION.SDK_INT != 25) : (ListenerUtil.mutListener.listen(4215) ? (Build.VERSION.SDK_INT == 25) : (Build.VERSION.SDK_INT >= 25)))))) || !BuildConfig.IS_TESTING.get()) : ((ListenerUtil.mutListener.listen(4219) ? (Build.VERSION.SDK_INT <= 25) : (ListenerUtil.mutListener.listen(4218) ? (Build.VERSION.SDK_INT > 25) : (ListenerUtil.mutListener.listen(4217) ? (Build.VERSION.SDK_INT < 25) : (ListenerUtil.mutListener.listen(4216) ? (Build.VERSION.SDK_INT != 25) : (ListenerUtil.mutListener.listen(4215) ? (Build.VERSION.SDK_INT == 25) : (Build.VERSION.SDK_INT >= 25)))))) && !BuildConfig.IS_TESTING.get()))) {
                int number = min(listStations.size(), ActivityMain.MAX_DYNAMIC_LAUNCHER_SHORTCUTS);
                SetDynamicAppLauncherShortcuts setDynamicAppLauncherShortcuts = new SetDynamicAppLauncherShortcuts(number);
                if (!ListenerUtil.mutListener.listen(4227)) {
                    {
                        long _loopCounter50 = 0;
                        for (int i = 0; (ListenerUtil.mutListener.listen(4226) ? (i >= number) : (ListenerUtil.mutListener.listen(4225) ? (i <= number) : (ListenerUtil.mutListener.listen(4224) ? (i > number) : (ListenerUtil.mutListener.listen(4223) ? (i != number) : (ListenerUtil.mutListener.listen(4222) ? (i == number) : (i < number)))))); i++) {
                            ListenerUtil.loopListener.listen("_loopCounter50", ++_loopCounter50);
                            if (!ListenerUtil.mutListener.listen(4221)) {
                                listStations.get(i).prepareShortcut(context, setDynamicAppLauncherShortcuts);
                            }
                        }
                    }
                }
            }
        }
    }

    @TargetApi(25)
    class SetDynamicAppLauncherShortcuts implements DataRadioStation.ShortcutReadyListener {

        ArrayList<ShortcutInfo> shortcuts;

        int expectedNumber;

        SetDynamicAppLauncherShortcuts(int expectedNumber) {
            if (!ListenerUtil.mutListener.listen(4229)) {
                this.expectedNumber = expectedNumber;
            }
            if (!ListenerUtil.mutListener.listen(4230)) {
                shortcuts = new ArrayList<ShortcutInfo>(expectedNumber);
            }
        }

        @Override
        public void onShortcutReadyListener(ShortcutInfo shortcut) {
            if (!ListenerUtil.mutListener.listen(4231)) {
                shortcuts.add(shortcut);
            }
            if (!ListenerUtil.mutListener.listen(4239)) {
                if ((ListenerUtil.mutListener.listen(4236) ? (shortcuts.size() <= expectedNumber) : (ListenerUtil.mutListener.listen(4235) ? (shortcuts.size() > expectedNumber) : (ListenerUtil.mutListener.listen(4234) ? (shortcuts.size() < expectedNumber) : (ListenerUtil.mutListener.listen(4233) ? (shortcuts.size() != expectedNumber) : (ListenerUtil.mutListener.listen(4232) ? (shortcuts.size() == expectedNumber) : (shortcuts.size() >= expectedNumber))))))) {
                    ShortcutManager shortcutManager = context.getApplicationContext().getSystemService(ShortcutManager.class);
                    if (!ListenerUtil.mutListener.listen(4237)) {
                        shortcutManager.removeAllDynamicShortcuts();
                    }
                    if (!ListenerUtil.mutListener.listen(4238)) {
                        shortcutManager.setDynamicShortcuts(shortcuts);
                    }
                }
            }
        }
    }
}
