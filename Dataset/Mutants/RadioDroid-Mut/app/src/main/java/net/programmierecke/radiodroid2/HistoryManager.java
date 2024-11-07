package net.programmierecke.radiodroid2;

import android.content.Context;
import net.programmierecke.radiodroid2.station.DataRadioStation;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class HistoryManager extends StationSaveManager {

    private static final int MAXSIZE = 25;

    @Override
    protected String getSaveId() {
        return "history";
    }

    public HistoryManager(Context ctx) {
        super(ctx);
    }

    @Override
    public void add(DataRadioStation station) {
        DataRadioStation stationFromHistory = getById(station.StationUuid);
        if (!ListenerUtil.mutListener.listen(4956)) {
            if (stationFromHistory != null) {
                if (!ListenerUtil.mutListener.listen(4953)) {
                    listStations.remove(stationFromHistory);
                }
                if (!ListenerUtil.mutListener.listen(4954)) {
                    listStations.add(0, stationFromHistory);
                }
                if (!ListenerUtil.mutListener.listen(4955)) {
                    Save();
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(4961)) {
            cutList((ListenerUtil.mutListener.listen(4960) ? (MAXSIZE % 1) : (ListenerUtil.mutListener.listen(4959) ? (MAXSIZE / 1) : (ListenerUtil.mutListener.listen(4958) ? (MAXSIZE * 1) : (ListenerUtil.mutListener.listen(4957) ? (MAXSIZE + 1) : (MAXSIZE - 1))))));
        }
        if (!ListenerUtil.mutListener.listen(4962)) {
            super.addFront(station);
        }
    }

    private void cutList(int count) {
        if (!ListenerUtil.mutListener.listen(4969)) {
            if ((ListenerUtil.mutListener.listen(4967) ? (listStations.size() >= count) : (ListenerUtil.mutListener.listen(4966) ? (listStations.size() <= count) : (ListenerUtil.mutListener.listen(4965) ? (listStations.size() < count) : (ListenerUtil.mutListener.listen(4964) ? (listStations.size() != count) : (ListenerUtil.mutListener.listen(4963) ? (listStations.size() == count) : (listStations.size() > count))))))) {
                if (!ListenerUtil.mutListener.listen(4968)) {
                    listStations = listStations.subList(0, count);
                }
            }
        }
    }
}
