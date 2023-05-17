package net.programmierecke.radiodroid2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import net.programmierecke.radiodroid2.station.DataRadioStation;
import org.json.JSONArray;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Observable;
import java.util.Vector;
import info.debatty.java.stringsimilarity.Cosine;
import okhttp3.OkHttpClient;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class StationSaveManager extends Observable {

    protected interface StationStatusListener {

        void onStationStatusChanged(DataRadioStation station, boolean favourite);
    }

    Context context;

    List<DataRadioStation> listStations = new ArrayList<DataRadioStation>();

    protected StationStatusListener stationStatusListener;

    public StationSaveManager(Context ctx) {
        if (!ListenerUtil.mutListener.listen(5043)) {
            this.context = ctx;
        }
        if (!ListenerUtil.mutListener.listen(5044)) {
            Load();
        }
    }

    protected String getSaveId() {
        return "default";
    }

    protected void setStationStatusListener(StationStatusListener stationStatusListener) {
        if (!ListenerUtil.mutListener.listen(5045)) {
            this.stationStatusListener = stationStatusListener;
        }
    }

    public void add(DataRadioStation station) {
        if (!ListenerUtil.mutListener.listen(5046)) {
            listStations.add(station);
        }
        if (!ListenerUtil.mutListener.listen(5047)) {
            Save();
        }
        if (!ListenerUtil.mutListener.listen(5048)) {
            notifyObservers();
        }
        if (!ListenerUtil.mutListener.listen(5050)) {
            if (stationStatusListener != null) {
                if (!ListenerUtil.mutListener.listen(5049)) {
                    stationStatusListener.onStationStatusChanged(station, true);
                }
            }
        }
    }

    public void addMultiple(List<DataRadioStation> stations) {
        if (!ListenerUtil.mutListener.listen(5052)) {
            {
                long _loopCounter61 = 0;
                for (DataRadioStation station_new : stations) {
                    ListenerUtil.loopListener.listen("_loopCounter61", ++_loopCounter61);
                    if (!ListenerUtil.mutListener.listen(5051)) {
                        listStations.add(station_new);
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(5053)) {
            Save();
        }
        if (!ListenerUtil.mutListener.listen(5054)) {
            notifyObservers();
        }
    }

    public void replaceList(List<DataRadioStation> stations_new) {
        if (!ListenerUtil.mutListener.listen(5063)) {
            {
                long _loopCounter63 = 0;
                for (DataRadioStation station_new : stations_new) {
                    ListenerUtil.loopListener.listen("_loopCounter63", ++_loopCounter63);
                    if (!ListenerUtil.mutListener.listen(5062)) {
                        {
                            long _loopCounter62 = 0;
                            for (int i = 0; (ListenerUtil.mutListener.listen(5061) ? (i >= listStations.size()) : (ListenerUtil.mutListener.listen(5060) ? (i <= listStations.size()) : (ListenerUtil.mutListener.listen(5059) ? (i > listStations.size()) : (ListenerUtil.mutListener.listen(5058) ? (i != listStations.size()) : (ListenerUtil.mutListener.listen(5057) ? (i == listStations.size()) : (i < listStations.size())))))); i++) {
                                ListenerUtil.loopListener.listen("_loopCounter62", ++_loopCounter62);
                                if (!ListenerUtil.mutListener.listen(5056)) {
                                    if (listStations.get(i).StationUuid.equals(station_new.StationUuid)) {
                                        if (!ListenerUtil.mutListener.listen(5055)) {
                                            listStations.set(i, station_new);
                                        }
                                        break;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(5064)) {
            Save();
        }
        if (!ListenerUtil.mutListener.listen(5065)) {
            notifyObservers();
        }
    }

    public void addFront(DataRadioStation station) {
        if (!ListenerUtil.mutListener.listen(5066)) {
            listStations.add(0, station);
        }
        if (!ListenerUtil.mutListener.listen(5067)) {
            Save();
        }
        if (!ListenerUtil.mutListener.listen(5068)) {
            notifyObservers();
        }
        if (!ListenerUtil.mutListener.listen(5070)) {
            if (stationStatusListener != null) {
                if (!ListenerUtil.mutListener.listen(5069)) {
                    stationStatusListener.onStationStatusChanged(station, true);
                }
            }
        }
    }

    public DataRadioStation getLast() {
        if (!ListenerUtil.mutListener.listen(5075)) {
            if (!listStations.isEmpty()) {
                return listStations.get((ListenerUtil.mutListener.listen(5074) ? (listStations.size() % 1) : (ListenerUtil.mutListener.listen(5073) ? (listStations.size() / 1) : (ListenerUtil.mutListener.listen(5072) ? (listStations.size() * 1) : (ListenerUtil.mutListener.listen(5071) ? (listStations.size() + 1) : (listStations.size() - 1))))));
            }
        }
        return null;
    }

    public DataRadioStation getFirst() {
        if (!ListenerUtil.mutListener.listen(5076)) {
            if (!listStations.isEmpty()) {
                return listStations.get(0);
            }
        }
        return null;
    }

    public DataRadioStation getById(String id) {
        if (!ListenerUtil.mutListener.listen(5078)) {
            {
                long _loopCounter64 = 0;
                for (DataRadioStation station : listStations) {
                    ListenerUtil.loopListener.listen("_loopCounter64", ++_loopCounter64);
                    if (!ListenerUtil.mutListener.listen(5077)) {
                        if (id.equals(station.StationUuid)) {
                            return station;
                        }
                    }
                }
            }
        }
        return null;
    }

    public DataRadioStation getNextById(String id) {
        if (!ListenerUtil.mutListener.listen(5079)) {
            if (listStations.isEmpty())
                return null;
        }
        if (!ListenerUtil.mutListener.listen(5094)) {
            {
                long _loopCounter65 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(5093) ? (i >= (ListenerUtil.mutListener.listen(5088) ? (listStations.size() % 1) : (ListenerUtil.mutListener.listen(5087) ? (listStations.size() / 1) : (ListenerUtil.mutListener.listen(5086) ? (listStations.size() * 1) : (ListenerUtil.mutListener.listen(5085) ? (listStations.size() + 1) : (listStations.size() - 1)))))) : (ListenerUtil.mutListener.listen(5092) ? (i <= (ListenerUtil.mutListener.listen(5088) ? (listStations.size() % 1) : (ListenerUtil.mutListener.listen(5087) ? (listStations.size() / 1) : (ListenerUtil.mutListener.listen(5086) ? (listStations.size() * 1) : (ListenerUtil.mutListener.listen(5085) ? (listStations.size() + 1) : (listStations.size() - 1)))))) : (ListenerUtil.mutListener.listen(5091) ? (i > (ListenerUtil.mutListener.listen(5088) ? (listStations.size() % 1) : (ListenerUtil.mutListener.listen(5087) ? (listStations.size() / 1) : (ListenerUtil.mutListener.listen(5086) ? (listStations.size() * 1) : (ListenerUtil.mutListener.listen(5085) ? (listStations.size() + 1) : (listStations.size() - 1)))))) : (ListenerUtil.mutListener.listen(5090) ? (i != (ListenerUtil.mutListener.listen(5088) ? (listStations.size() % 1) : (ListenerUtil.mutListener.listen(5087) ? (listStations.size() / 1) : (ListenerUtil.mutListener.listen(5086) ? (listStations.size() * 1) : (ListenerUtil.mutListener.listen(5085) ? (listStations.size() + 1) : (listStations.size() - 1)))))) : (ListenerUtil.mutListener.listen(5089) ? (i == (ListenerUtil.mutListener.listen(5088) ? (listStations.size() % 1) : (ListenerUtil.mutListener.listen(5087) ? (listStations.size() / 1) : (ListenerUtil.mutListener.listen(5086) ? (listStations.size() * 1) : (ListenerUtil.mutListener.listen(5085) ? (listStations.size() + 1) : (listStations.size() - 1)))))) : (i < (ListenerUtil.mutListener.listen(5088) ? (listStations.size() % 1) : (ListenerUtil.mutListener.listen(5087) ? (listStations.size() / 1) : (ListenerUtil.mutListener.listen(5086) ? (listStations.size() * 1) : (ListenerUtil.mutListener.listen(5085) ? (listStations.size() + 1) : (listStations.size() - 1))))))))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter65", ++_loopCounter65);
                    if (!ListenerUtil.mutListener.listen(5084)) {
                        if (listStations.get(i).StationUuid.equals(id)) {
                            return listStations.get((ListenerUtil.mutListener.listen(5083) ? (i % 1) : (ListenerUtil.mutListener.listen(5082) ? (i / 1) : (ListenerUtil.mutListener.listen(5081) ? (i * 1) : (ListenerUtil.mutListener.listen(5080) ? (i - 1) : (i + 1))))));
                        }
                    }
                }
            }
        }
        return listStations.get(0);
    }

    public DataRadioStation getPreviousById(String id) {
        if (!ListenerUtil.mutListener.listen(5095)) {
            if (listStations.isEmpty())
                return null;
        }
        if (!ListenerUtil.mutListener.listen(5106)) {
            {
                long _loopCounter66 = 0;
                for (int i = 1; (ListenerUtil.mutListener.listen(5105) ? (i >= listStations.size()) : (ListenerUtil.mutListener.listen(5104) ? (i <= listStations.size()) : (ListenerUtil.mutListener.listen(5103) ? (i > listStations.size()) : (ListenerUtil.mutListener.listen(5102) ? (i != listStations.size()) : (ListenerUtil.mutListener.listen(5101) ? (i == listStations.size()) : (i < listStations.size())))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter66", ++_loopCounter66);
                    if (!ListenerUtil.mutListener.listen(5100)) {
                        if (listStations.get(i).StationUuid.equals(id)) {
                            return listStations.get((ListenerUtil.mutListener.listen(5099) ? (i % 1) : (ListenerUtil.mutListener.listen(5098) ? (i / 1) : (ListenerUtil.mutListener.listen(5097) ? (i * 1) : (ListenerUtil.mutListener.listen(5096) ? (i + 1) : (i - 1))))));
                        }
                    }
                }
            }
        }
        return listStations.get((ListenerUtil.mutListener.listen(5110) ? (listStations.size() % 1) : (ListenerUtil.mutListener.listen(5109) ? (listStations.size() / 1) : (ListenerUtil.mutListener.listen(5108) ? (listStations.size() * 1) : (ListenerUtil.mutListener.listen(5107) ? (listStations.size() + 1) : (listStations.size() - 1))))));
    }

    public void moveWithoutNotify(int fromPos, int toPos) {
        if (!ListenerUtil.mutListener.listen(5119)) {
            Collections.rotate(listStations.subList(Math.min(fromPos, toPos), (ListenerUtil.mutListener.listen(5114) ? (Math.max(fromPos, toPos) % 1) : (ListenerUtil.mutListener.listen(5113) ? (Math.max(fromPos, toPos) / 1) : (ListenerUtil.mutListener.listen(5112) ? (Math.max(fromPos, toPos) * 1) : (ListenerUtil.mutListener.listen(5111) ? (Math.max(fromPos, toPos) - 1) : (Math.max(fromPos, toPos) + 1)))))), Integer.signum((ListenerUtil.mutListener.listen(5118) ? (fromPos % toPos) : (ListenerUtil.mutListener.listen(5117) ? (fromPos / toPos) : (ListenerUtil.mutListener.listen(5116) ? (fromPos * toPos) : (ListenerUtil.mutListener.listen(5115) ? (fromPos + toPos) : (fromPos - toPos)))))));
        }
    }

    public void move(int fromPos, int toPos) {
        if (!ListenerUtil.mutListener.listen(5120)) {
            moveWithoutNotify(fromPos, toPos);
        }
        if (!ListenerUtil.mutListener.listen(5121)) {
            notifyObservers();
        }
    }

    @Nullable
    public DataRadioStation getBestNameMatch(String query) {
        DataRadioStation bestStation = null;
        if (!ListenerUtil.mutListener.listen(5122)) {
            query = query.toUpperCase();
        }
        double smallesDistance = Double.MAX_VALUE;
        // must be in the loop for some measures (e.g. Sift4)
        Cosine distMeasure = new Cosine();
        if (!ListenerUtil.mutListener.listen(5131)) {
            {
                long _loopCounter67 = 0;
                for (DataRadioStation station : listStations) {
                    ListenerUtil.loopListener.listen("_loopCounter67", ++_loopCounter67);
                    double distance = distMeasure.distance(station.Name.toUpperCase(), query);
                    if (!ListenerUtil.mutListener.listen(5130)) {
                        if ((ListenerUtil.mutListener.listen(5127) ? (distance >= smallesDistance) : (ListenerUtil.mutListener.listen(5126) ? (distance <= smallesDistance) : (ListenerUtil.mutListener.listen(5125) ? (distance > smallesDistance) : (ListenerUtil.mutListener.listen(5124) ? (distance != smallesDistance) : (ListenerUtil.mutListener.listen(5123) ? (distance == smallesDistance) : (distance < smallesDistance))))))) {
                            if (!ListenerUtil.mutListener.listen(5128)) {
                                bestStation = station;
                            }
                            if (!ListenerUtil.mutListener.listen(5129)) {
                                smallesDistance = distance;
                            }
                        }
                    }
                }
            }
        }
        return bestStation;
    }

    public int remove(String id) {
        if (!ListenerUtil.mutListener.listen(5143)) {
            {
                long _loopCounter68 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(5142) ? (i >= listStations.size()) : (ListenerUtil.mutListener.listen(5141) ? (i <= listStations.size()) : (ListenerUtil.mutListener.listen(5140) ? (i > listStations.size()) : (ListenerUtil.mutListener.listen(5139) ? (i != listStations.size()) : (ListenerUtil.mutListener.listen(5138) ? (i == listStations.size()) : (i < listStations.size())))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter68", ++_loopCounter68);
                    DataRadioStation station = listStations.get(i);
                    if (!ListenerUtil.mutListener.listen(5137)) {
                        if (station.StationUuid.equals(id)) {
                            if (!ListenerUtil.mutListener.listen(5132)) {
                                listStations.remove(i);
                            }
                            if (!ListenerUtil.mutListener.listen(5133)) {
                                Save();
                            }
                            if (!ListenerUtil.mutListener.listen(5134)) {
                                notifyObservers();
                            }
                            if (!ListenerUtil.mutListener.listen(5136)) {
                                if (stationStatusListener != null) {
                                    if (!ListenerUtil.mutListener.listen(5135)) {
                                        stationStatusListener.onStationStatusChanged(station, false);
                                    }
                                }
                            }
                            return i;
                        }
                    }
                }
            }
        }
        return -1;
    }

    public void restore(DataRadioStation station, int pos) {
        if (!ListenerUtil.mutListener.listen(5144)) {
            listStations.add(pos, station);
        }
        if (!ListenerUtil.mutListener.listen(5145)) {
            Save();
        }
        if (!ListenerUtil.mutListener.listen(5146)) {
            notifyObservers();
        }
        if (!ListenerUtil.mutListener.listen(5148)) {
            if (stationStatusListener != null) {
                if (!ListenerUtil.mutListener.listen(5147)) {
                    stationStatusListener.onStationStatusChanged(station, false);
                }
            }
        }
    }

    public void clear() {
        List<DataRadioStation> oldStation = listStations;
        if (!ListenerUtil.mutListener.listen(5149)) {
            listStations = new ArrayList<>();
        }
        if (!ListenerUtil.mutListener.listen(5150)) {
            Save();
        }
        if (!ListenerUtil.mutListener.listen(5151)) {
            notifyObservers();
        }
        if (!ListenerUtil.mutListener.listen(5154)) {
            if (stationStatusListener != null) {
                if (!ListenerUtil.mutListener.listen(5153)) {
                    {
                        long _loopCounter69 = 0;
                        for (DataRadioStation station : oldStation) {
                            ListenerUtil.loopListener.listen("_loopCounter69", ++_loopCounter69);
                            if (!ListenerUtil.mutListener.listen(5152)) {
                                stationStatusListener.onStationStatusChanged(station, false);
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public boolean hasChanged() {
        return true;
    }

    public int size() {
        return listStations.size();
    }

    public boolean isEmpty() {
        return listStations.size() == 0;
    }

    public boolean has(String id) {
        DataRadioStation station = getById(id);
        return station != null;
    }

    private boolean hasInvalidUuids() {
        if (!ListenerUtil.mutListener.listen(5156)) {
            {
                long _loopCounter70 = 0;
                for (DataRadioStation station : listStations) {
                    ListenerUtil.loopListener.listen("_loopCounter70", ++_loopCounter70);
                    if (!ListenerUtil.mutListener.listen(5155)) {
                        if (!station.hasValidUuid()) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public List<DataRadioStation> getList() {
        return Collections.unmodifiableList(listStations);
    }

    private void refreshStationsFromServer() {
        final RadioDroidApp radioDroidApp = (RadioDroidApp) context.getApplicationContext();
        final OkHttpClient httpClient = radioDroidApp.getHttpClient();
        if (!ListenerUtil.mutListener.listen(5157)) {
            LocalBroadcastManager.getInstance(context).sendBroadcast(new Intent(ActivityMain.ACTION_SHOW_LOADING));
        }
        if (!ListenerUtil.mutListener.listen(5174)) {
            new AsyncTask<Void, Void, ArrayList<DataRadioStation>>() {

                private ArrayList<DataRadioStation> savedStations;

                @Override
                protected void onPreExecute() {
                    if (!ListenerUtil.mutListener.listen(5158)) {
                        savedStations = new ArrayList<>(listStations);
                    }
                }

                @Override
                protected ArrayList<DataRadioStation> doInBackground(Void... params) {
                    ArrayList<DataRadioStation> stationsToRemove = new ArrayList<>();
                    if (!ListenerUtil.mutListener.listen(5168)) {
                        {
                            long _loopCounter71 = 0;
                            for (DataRadioStation station : savedStations) {
                                ListenerUtil.loopListener.listen("_loopCounter71", ++_loopCounter71);
                                if (!ListenerUtil.mutListener.listen(5167)) {
                                    if ((ListenerUtil.mutListener.listen(5165) ? ((ListenerUtil.mutListener.listen(5159) ? (!station.refresh(httpClient, context) || !station.hasValidUuid()) : (!station.refresh(httpClient, context) && !station.hasValidUuid())) || (ListenerUtil.mutListener.listen(5164) ? (station.RefreshRetryCount >= DataRadioStation.MAX_REFRESH_RETRIES) : (ListenerUtil.mutListener.listen(5163) ? (station.RefreshRetryCount <= DataRadioStation.MAX_REFRESH_RETRIES) : (ListenerUtil.mutListener.listen(5162) ? (station.RefreshRetryCount < DataRadioStation.MAX_REFRESH_RETRIES) : (ListenerUtil.mutListener.listen(5161) ? (station.RefreshRetryCount != DataRadioStation.MAX_REFRESH_RETRIES) : (ListenerUtil.mutListener.listen(5160) ? (station.RefreshRetryCount == DataRadioStation.MAX_REFRESH_RETRIES) : (station.RefreshRetryCount > DataRadioStation.MAX_REFRESH_RETRIES))))))) : ((ListenerUtil.mutListener.listen(5159) ? (!station.refresh(httpClient, context) || !station.hasValidUuid()) : (!station.refresh(httpClient, context) && !station.hasValidUuid())) && (ListenerUtil.mutListener.listen(5164) ? (station.RefreshRetryCount >= DataRadioStation.MAX_REFRESH_RETRIES) : (ListenerUtil.mutListener.listen(5163) ? (station.RefreshRetryCount <= DataRadioStation.MAX_REFRESH_RETRIES) : (ListenerUtil.mutListener.listen(5162) ? (station.RefreshRetryCount < DataRadioStation.MAX_REFRESH_RETRIES) : (ListenerUtil.mutListener.listen(5161) ? (station.RefreshRetryCount != DataRadioStation.MAX_REFRESH_RETRIES) : (ListenerUtil.mutListener.listen(5160) ? (station.RefreshRetryCount == DataRadioStation.MAX_REFRESH_RETRIES) : (station.RefreshRetryCount > DataRadioStation.MAX_REFRESH_RETRIES))))))))) {
                                        if (!ListenerUtil.mutListener.listen(5166)) {
                                            stationsToRemove.add(station);
                                        }
                                    }
                                }
                            }
                        }
                    }
                    return stationsToRemove;
                }

                @Override
                protected void onPostExecute(ArrayList<DataRadioStation> stationsToRemove) {
                    if (!ListenerUtil.mutListener.listen(5169)) {
                        listStations.removeAll(stationsToRemove);
                    }
                    if (!ListenerUtil.mutListener.listen(5170)) {
                        Save();
                    }
                    if (!ListenerUtil.mutListener.listen(5171)) {
                        notifyObservers();
                    }
                    if (!ListenerUtil.mutListener.listen(5172)) {
                        LocalBroadcastManager.getInstance(context).sendBroadcast(new Intent(ActivityMain.ACTION_HIDE_LOADING));
                    }
                    if (!ListenerUtil.mutListener.listen(5173)) {
                        super.onPostExecute(stationsToRemove);
                    }
                }
            }.execute();
        }
    }

    void Load() {
        if (!ListenerUtil.mutListener.listen(5175)) {
            listStations.clear();
        }
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
        String str = sharedPref.getString(getSaveId(), null);
        if (!ListenerUtil.mutListener.listen(5181)) {
            if (str != null) {
                List<DataRadioStation> arr = DataRadioStation.DecodeJson(str);
                if (!ListenerUtil.mutListener.listen(5177)) {
                    listStations.addAll(arr);
                }
                if (!ListenerUtil.mutListener.listen(5180)) {
                    if ((ListenerUtil.mutListener.listen(5178) ? (hasInvalidUuids() || Utils.hasAnyConnection(context)) : (hasInvalidUuids() && Utils.hasAnyConnection(context)))) {
                        if (!ListenerUtil.mutListener.listen(5179)) {
                            refreshStationsFromServer();
                        }
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(5176)) {
                    Log.w("SAVE", "Load() no stations to load");
                }
            }
        }
    }

    void Save() {
        JSONArray arr = new JSONArray();
        if (!ListenerUtil.mutListener.listen(5183)) {
            {
                long _loopCounter72 = 0;
                for (DataRadioStation station : listStations) {
                    ListenerUtil.loopListener.listen("_loopCounter72", ++_loopCounter72);
                    if (!ListenerUtil.mutListener.listen(5182)) {
                        arr.put(station.toJson());
                    }
                }
            }
        }
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPref.edit();
        String str = arr.toString();
        if (!ListenerUtil.mutListener.listen(5185)) {
            if (BuildConfig.DEBUG) {
                if (!ListenerUtil.mutListener.listen(5184)) {
                    Log.d("SAVE", "wrote: " + str);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(5186)) {
            editor.putString(getSaveId(), str);
        }
        if (!ListenerUtil.mutListener.listen(5187)) {
            editor.commit();
        }
    }

    public static String getSaveDir() {
        String path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC) + "";
        File folder = new File(path);
        if (!ListenerUtil.mutListener.listen(5190)) {
            if (!folder.exists()) {
                if (!ListenerUtil.mutListener.listen(5189)) {
                    if (!folder.mkdirs()) {
                        if (!ListenerUtil.mutListener.listen(5188)) {
                            Log.e("SAVE", "could not create dir:" + path);
                        }
                    }
                }
            }
        }
        return path;
    }

    public void SaveM3U(final String filePath, final String fileName) {
        Toast toast = Toast.makeText(context, context.getResources().getString(R.string.notify_save_playlist_now, filePath, fileName), Toast.LENGTH_LONG);
        if (!ListenerUtil.mutListener.listen(5191)) {
            toast.show();
        }
        if (!ListenerUtil.mutListener.listen(5198)) {
            new AsyncTask<Void, Void, Boolean>() {

                @Override
                protected Boolean doInBackground(Void... params) {
                    return SaveM3UInternal(filePath, fileName);
                }

                @Override
                protected void onPostExecute(Boolean result) {
                    if (!ListenerUtil.mutListener.listen(5196)) {
                        if (result.booleanValue()) {
                            if (!ListenerUtil.mutListener.listen(5194)) {
                                Log.i("SAVE", "OK");
                            }
                            Toast toast = Toast.makeText(context, context.getResources().getString(R.string.notify_save_playlist_ok, filePath, fileName), Toast.LENGTH_LONG);
                            if (!ListenerUtil.mutListener.listen(5195)) {
                                toast.show();
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(5192)) {
                                Log.i("SAVE", "NOK");
                            }
                            Toast toast = Toast.makeText(context, context.getResources().getString(R.string.notify_save_playlist_nok, filePath, fileName), Toast.LENGTH_LONG);
                            if (!ListenerUtil.mutListener.listen(5193)) {
                                toast.show();
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(5197)) {
                        super.onPostExecute(result);
                    }
                }
            }.execute();
        }
    }

    public void LoadM3U(final String filePath, final String fileName) {
        Toast toast = Toast.makeText(context, context.getResources().getString(R.string.notify_load_playlist_now, filePath, fileName), Toast.LENGTH_LONG);
        if (!ListenerUtil.mutListener.listen(5199)) {
            toast.show();
        }
        if (!ListenerUtil.mutListener.listen(5208)) {
            new AsyncTask<Void, Void, List<DataRadioStation>>() {

                @Override
                protected List<DataRadioStation> doInBackground(Void... params) {
                    return LoadM3UInternal(filePath, fileName);
                }

                @Override
                protected void onPostExecute(List<DataRadioStation> result) {
                    if (!ListenerUtil.mutListener.listen(5205)) {
                        if (result != null) {
                            if (!ListenerUtil.mutListener.listen(5202)) {
                                Log.i("LOAD", "Loaded " + result.size() + "stations");
                            }
                            if (!ListenerUtil.mutListener.listen(5203)) {
                                addMultiple(result);
                            }
                            Toast toast = Toast.makeText(context, context.getResources().getString(R.string.notify_load_playlist_ok, result.size(), filePath, fileName), Toast.LENGTH_LONG);
                            if (!ListenerUtil.mutListener.listen(5204)) {
                                toast.show();
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(5200)) {
                                Log.e("LOAD", "Load failed");
                            }
                            Toast toast = Toast.makeText(context, context.getResources().getString(R.string.notify_load_playlist_nok, filePath, fileName), Toast.LENGTH_LONG);
                            if (!ListenerUtil.mutListener.listen(5201)) {
                                toast.show();
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(5206)) {
                        notifyObservers();
                    }
                    if (!ListenerUtil.mutListener.listen(5207)) {
                        super.onPostExecute(result);
                    }
                }
            }.execute();
        }
    }

    protected final String M3U_PREFIX = "#RADIOBROWSERUUID:";

    boolean SaveM3UInternal(String filePath, String fileName) {
        final RadioDroidApp radioDroidApp = (RadioDroidApp) context.getApplicationContext();
        final OkHttpClient httpClient = radioDroidApp.getHttpClient();
        try {
            File f = new File(filePath, fileName);
            BufferedWriter bw = new BufferedWriter(new FileWriter(f, false));
            if (!ListenerUtil.mutListener.listen(5210)) {
                bw.write("#EXTM3U\n");
            }
            if (!ListenerUtil.mutListener.listen(5214)) {
                {
                    long _loopCounter73 = 0;
                    for (DataRadioStation station : listStations) {
                        ListenerUtil.loopListener.listen("_loopCounter73", ++_loopCounter73);
                        if (!ListenerUtil.mutListener.listen(5211)) {
                            // if (result != null) {
                            bw.write(M3U_PREFIX + station.StationUuid + "\n");
                        }
                        if (!ListenerUtil.mutListener.listen(5212)) {
                            bw.write("#EXTINF:-1," + station.Name + "\n");
                        }
                        if (!ListenerUtil.mutListener.listen(5213)) {
                            bw.write(station.StreamUrl + "\n\n");
                        }
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(5215)) {
                bw.flush();
            }
            if (!ListenerUtil.mutListener.listen(5216)) {
                bw.close();
            }
            if (!ListenerUtil.mutListener.listen(5224)) {
                if ((ListenerUtil.mutListener.listen(5221) ? (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) : (ListenerUtil.mutListener.listen(5220) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.KITKAT) : (ListenerUtil.mutListener.listen(5219) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) : (ListenerUtil.mutListener.listen(5218) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.KITKAT) : (ListenerUtil.mutListener.listen(5217) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.KITKAT) : (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT))))))) {
                    if (!ListenerUtil.mutListener.listen(5223)) {
                        context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_MOUNTED, Uri.parse("file://" + Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC))));
                    }
                } else {
                    if (!ListenerUtil.mutListener.listen(5222)) {
                        MediaScannerConnection.scanFile(context, new String[] { f.getAbsolutePath() }, null, null);
                    }
                }
            }
            return true;
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(5209)) {
                Log.e("Exception", "File write failed: " + e.toString());
            }
            return false;
        }
    }

    List<DataRadioStation> LoadM3UInternal(String filePath, String fileName) {
        try {
            File f = new File(filePath, fileName);
            BufferedReader br = new BufferedReader(new FileReader(f));
            String line;
            final RadioDroidApp radioDroidApp = (RadioDroidApp) context.getApplicationContext();
            final OkHttpClient httpClient = radioDroidApp.getHttpClient();
            ArrayList<String> listUuids = new ArrayList<String>();
            if (!ListenerUtil.mutListener.listen(5229)) {
                {
                    long _loopCounter74 = 0;
                    while ((line = br.readLine()) != null) {
                        ListenerUtil.loopListener.listen("_loopCounter74", ++_loopCounter74);
                        if (!ListenerUtil.mutListener.listen(5228)) {
                            if (line.startsWith(M3U_PREFIX)) {
                                try {
                                    String uuid = line.substring(M3U_PREFIX.length()).trim();
                                    if (!ListenerUtil.mutListener.listen(5227)) {
                                        listUuids.add(uuid);
                                    }
                                } catch (Exception e) {
                                    if (!ListenerUtil.mutListener.listen(5226)) {
                                        Log.e("LOAD", e.toString());
                                    }
                                }
                            }
                        }
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(5230)) {
                br.close();
            }
            List<DataRadioStation> listStationsNew = Utils.getStationsByUuid(httpClient, context, listUuids);
            if (!ListenerUtil.mutListener.listen(5231)) {
                if (listStationsNew != null) {
                    return listStationsNew;
                }
            }
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(5225)) {
                Log.e("LOAD", "File write failed: " + e.toString());
            }
            return null;
        }
        List<DataRadioStation> loadedItems = new ArrayList<>();
        return loadedItems;
    }
}
