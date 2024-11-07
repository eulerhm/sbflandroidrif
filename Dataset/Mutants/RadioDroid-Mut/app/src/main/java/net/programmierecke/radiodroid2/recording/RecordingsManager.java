package net.programmierecke.radiodroid2.recording;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Environment;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.preference.PreferenceManager;
import net.programmierecke.radiodroid2.BuildConfig;
import net.programmierecke.radiodroid2.R;
import net.programmierecke.radiodroid2.Utils;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Observable;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/* TODO: Actually have info about recording by storing them in the database and matching with files on disk.
 */
public class RecordingsManager {

    private static final String TAG = "Recordings";

    private DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd", Locale.US);

    private DateFormat timeFormatter = new SimpleDateFormat("HH-mm", Locale.US);

    private class RecordingsObservable extends Observable {

        @Override
        public synchronized boolean hasChanged() {
            return true;
        }
    }

    private Observable savedRecordingsObservable = new RecordingsObservable();

    private class RunningRecordableListener implements RecordableListener {

        private RunningRecordingInfo runningRecordingInfo;

        private boolean ended;

        private RunningRecordableListener(@NonNull RunningRecordingInfo runningRecordingInfo) {
            if (!ListenerUtil.mutListener.listen(1621)) {
                this.runningRecordingInfo = runningRecordingInfo;
            }
        }

        @Override
        public void onBytesAvailable(byte[] buffer, int offset, int length) {
            try {
                if (!ListenerUtil.mutListener.listen(1624)) {
                    runningRecordingInfo.getOutputStream().write(buffer, offset, length);
                }
                if (!ListenerUtil.mutListener.listen(1625)) {
                    runningRecordingInfo.setBytesWritten(runningRecordingInfo.getBytesWritten() + length);
                }
            } catch (IOException e) {
                if (!ListenerUtil.mutListener.listen(1622)) {
                    e.printStackTrace();
                }
                if (!ListenerUtil.mutListener.listen(1623)) {
                    runningRecordingInfo.getRecordable().stopRecording();
                }
            }
        }

        @Override
        public void onRecordingEnded() {
            if (!ListenerUtil.mutListener.listen(1626)) {
                if (ended) {
                    return;
                }
            }
            if (!ListenerUtil.mutListener.listen(1627)) {
                ended = true;
            }
            try {
                if (!ListenerUtil.mutListener.listen(1629)) {
                    runningRecordingInfo.getOutputStream().close();
                }
            } catch (IOException e) {
                if (!ListenerUtil.mutListener.listen(1628)) {
                    e.printStackTrace();
                }
            }
            if (!ListenerUtil.mutListener.listen(1630)) {
                RecordingsManager.this.stopRecording(runningRecordingInfo.getRecordable());
            }
        }
    }

    private Map<Recordable, RunningRecordingInfo> runningRecordings = new HashMap<>();

    private ArrayList<DataRecording> savedRecordings = new ArrayList<>();

    public void record(@NonNull Context context, @NonNull Recordable recordable) {
        if (!ListenerUtil.mutListener.listen(1631)) {
            if (!recordable.canRecord()) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(1648)) {
            if (!runningRecordings.containsKey(recordable)) {
                RunningRecordingInfo info = new RunningRecordingInfo();
                if (!ListenerUtil.mutListener.listen(1632)) {
                    info.setRecordable(recordable);
                }
                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
                final String fileNameFormat = prefs.getString("record_name_formatting", context.getString(R.string.settings_record_name_formatting_default));
                final Map<String, String> formattingArgs = new HashMap<>(recordable.getRecordNameFormattingArgs());
                Calendar calendar = Calendar.getInstance();
                if (!ListenerUtil.mutListener.listen(1633)) {
                    calendar.setTimeInMillis(System.currentTimeMillis());
                }
                final Date currentTime = calendar.getTime();
                String dateStr = dateFormatter.format(currentTime);
                String timeStr = timeFormatter.format(currentTime);
                if (!ListenerUtil.mutListener.listen(1634)) {
                    formattingArgs.put("date", dateStr);
                }
                if (!ListenerUtil.mutListener.listen(1635)) {
                    formattingArgs.put("time", timeStr);
                }
                final int recordNum = prefs.getInt("record_num", 1);
                if (!ListenerUtil.mutListener.listen(1636)) {
                    formattingArgs.put("index", Integer.toString(recordNum));
                }
                final String recordTitle = Utils.formatStringWithNamedArgs(fileNameFormat, formattingArgs);
                if (!ListenerUtil.mutListener.listen(1637)) {
                    info.setTitle(recordTitle);
                }
                if (!ListenerUtil.mutListener.listen(1638)) {
                    info.setFileName(String.format("%s.%s", recordTitle, recordable.getExtension()));
                }
                String filePath = RecordingsManager.getRecordDir() + "/" + info.getFileName();
                try {
                    if (!ListenerUtil.mutListener.listen(1640)) {
                        info.setOutputStream(new FileOutputStream(filePath));
                    }
                } catch (FileNotFoundException e) {
                    if (!ListenerUtil.mutListener.listen(1639)) {
                        e.printStackTrace();
                    }
                    return;
                }
                if (!ListenerUtil.mutListener.listen(1641)) {
                    recordable.startRecording(new RunningRecordableListener(info));
                }
                if (!ListenerUtil.mutListener.listen(1642)) {
                    runningRecordings.put(recordable, info);
                }
                if (!ListenerUtil.mutListener.listen(1647)) {
                    prefs.edit().putInt("record_num", (ListenerUtil.mutListener.listen(1646) ? (recordNum % 1) : (ListenerUtil.mutListener.listen(1645) ? (recordNum / 1) : (ListenerUtil.mutListener.listen(1644) ? (recordNum * 1) : (ListenerUtil.mutListener.listen(1643) ? (recordNum - 1) : (recordNum + 1)))))).apply();
                }
            }
        }
    }

    public void stopRecording(@NonNull Recordable recordable) {
        if (!ListenerUtil.mutListener.listen(1649)) {
            recordable.stopRecording();
        }
        if (!ListenerUtil.mutListener.listen(1650)) {
            runningRecordings.remove(recordable);
        }
        if (!ListenerUtil.mutListener.listen(1651)) {
            updateRecordingsList();
        }
    }

    public RunningRecordingInfo getRecordingInfo(Recordable recordable) {
        return runningRecordings.get(recordable);
    }

    public Map<Recordable, RunningRecordingInfo> getRunningRecordings() {
        return Collections.unmodifiableMap(runningRecordings);
    }

    public List<DataRecording> getSavedRecordings() {
        return new ArrayList<>(savedRecordings);
    }

    public Observable getSavedRecordingsObservable() {
        return savedRecordingsObservable;
    }

    public static String getRecordDir() {
        String pathRecordings = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC) + "/Recordings";
        File folder = new File(pathRecordings);
        if (!ListenerUtil.mutListener.listen(1654)) {
            if (!folder.exists()) {
                if (!ListenerUtil.mutListener.listen(1653)) {
                    if (!folder.mkdirs()) {
                        if (!ListenerUtil.mutListener.listen(1652)) {
                            Log.e(TAG, "could not create dir:" + pathRecordings);
                        }
                    }
                }
            }
        }
        return pathRecordings;
    }

    public void updateRecordingsList() {
        String path = getRecordDir();
        if (!ListenerUtil.mutListener.listen(1656)) {
            if (BuildConfig.DEBUG) {
                if (!ListenerUtil.mutListener.listen(1655)) {
                    Log.d(TAG, "Updating recordings from " + path);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(1657)) {
            savedRecordings.clear();
        }
        File folder = new File(path);
        File[] files = folder.listFiles();
        if (!ListenerUtil.mutListener.listen(1664)) {
            if (files != null) {
                if (!ListenerUtil.mutListener.listen(1662)) {
                    {
                        long _loopCounter31 = 0;
                        for (File f : files) {
                            ListenerUtil.loopListener.listen("_loopCounter31", ++_loopCounter31);
                            DataRecording dr = new DataRecording();
                            if (!ListenerUtil.mutListener.listen(1659)) {
                                dr.Name = f.getName();
                            }
                            if (!ListenerUtil.mutListener.listen(1660)) {
                                dr.Time = new Date(f.lastModified());
                            }
                            if (!ListenerUtil.mutListener.listen(1661)) {
                                savedRecordings.add(dr);
                            }
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(1663)) {
                    Collections.sort(savedRecordings, (o1, o2) -> Long.compare(o2.Time.getTime(), o1.Time.getTime()));
                }
            } else {
                if (!ListenerUtil.mutListener.listen(1658)) {
                    Log.e(TAG, "Could not enumerate files in recordings directory");
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(1665)) {
            savedRecordingsObservable.notifyObservers();
        }
    }
}
