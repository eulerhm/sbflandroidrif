package net.programmierecke.radiodroid2.players.mpd;

import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class MPDAsyncTask implements Runnable {

    private static String TAG = "MPDAsyncTask";

    public interface ReadStage {

        boolean onRead(@NonNull MPDAsyncTask task, @NonNull String result);
    }

    public interface WriteStage {

        boolean onWrite(@NonNull MPDAsyncTask task, @NonNull BufferedWriter bufferedWriter) throws IOException;
    }

    public interface FailureCallback {

        void onFailure(@NonNull MPDAsyncTask task);
    }

    private LinkedList<ReadStage> readStages;

    private LinkedList<WriteStage> writeStages;

    private FailureCallback failureCallback;

    private long timeoutMs;

    private MPDServerData mpdServerData;

    private MPDClient mpdClient;

    public MPDAsyncTask() {
    }

    protected void setStages(ReadStage[] readStages, WriteStage[] writeStages, @Nullable FailureCallback failureCallback) {
        if (!ListenerUtil.mutListener.listen(1039)) {
            this.readStages = new LinkedList<>(Arrays.asList(readStages));
        }
        if (!ListenerUtil.mutListener.listen(1040)) {
            this.writeStages = new LinkedList<>(Arrays.asList(writeStages));
        }
        if (!ListenerUtil.mutListener.listen(1041)) {
            this.failureCallback = failureCallback;
        }
    }

    protected void setTimeout(long timeoutMs) {
        if (!ListenerUtil.mutListener.listen(1042)) {
            this.timeoutMs = timeoutMs;
        }
    }

    protected void fail() {
        if (!ListenerUtil.mutListener.listen(1044)) {
            if (failureCallback != null) {
                if (!ListenerUtil.mutListener.listen(1043)) {
                    failureCallback.onFailure(MPDAsyncTask.this);
                }
            }
        }
    }

    @Override
    public void run() {
        try {
            if (!ListenerUtil.mutListener.listen(1048)) {
                if (!TextUtils.isEmpty(mpdServerData.password)) {
                    if (!ListenerUtil.mutListener.listen(1046)) {
                        this.readStages.addFirst(okReadStage());
                    }
                    if (!ListenerUtil.mutListener.listen(1047)) {
                        this.writeStages.addFirst(loginWriteStage(mpdServerData.password));
                    }
                }
            }
            Socket s = new Socket();
            if (!ListenerUtil.mutListener.listen(1049)) {
                s.connect(new InetSocketAddress(mpdServerData.hostname, mpdServerData.port), (int) timeoutMs);
            }
            BufferedReader reader = new BufferedReader(new InputStreamReader(s.getInputStream(), Charset.forName("UTF-8")));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(s.getOutputStream(), Charset.forName("UTF-8")));
            if (!ListenerUtil.mutListener.listen(1050)) {
                onConnected(reader, writer);
            }
            if (!ListenerUtil.mutListener.listen(1051)) {
                reader.close();
            }
            if (!ListenerUtil.mutListener.listen(1052)) {
                writer.close();
            }
            if (!ListenerUtil.mutListener.listen(1053)) {
                s.close();
            }
        } catch (IOException ex) {
            if (!ListenerUtil.mutListener.listen(1045)) {
                fail();
            }
        }
    }

    private void onConnected(@NonNull BufferedReader reader, @NonNull BufferedWriter writer) throws IOException {
        CharBuffer readBuffer = CharBuffer.allocate(1024);
        boolean c = true;
        if (!ListenerUtil.mutListener.listen(1065)) {
            {
                long _loopCounter17 = 0;
                while (c) {
                    ListenerUtil.loopListener.listen("_loopCounter17", ++_loopCounter17);
                    if (!ListenerUtil.mutListener.listen(1054)) {
                        readBuffer.clear();
                    }
                    ReadStage readStage = MPDAsyncTask.this.readStages.poll();
                    if (!ListenerUtil.mutListener.listen(1059)) {
                        if (readStage != null) {
                            int read = reader.read(readBuffer);
                            if (!ListenerUtil.mutListener.listen(1056)) {
                                readBuffer.position(0);
                            }
                            if (!ListenerUtil.mutListener.listen(1057)) {
                                Log.d(TAG, readBuffer.toString());
                            }
                            if (!ListenerUtil.mutListener.listen(1058)) {
                                c = readStage.onRead(MPDAsyncTask.this, readBuffer.toString());
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(1055)) {
                                c = false;
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(1064)) {
                        if (c) {
                            WriteStage writeStage = MPDAsyncTask.this.writeStages.poll();
                            if (!ListenerUtil.mutListener.listen(1063)) {
                                if (writeStage != null) {
                                    if (!ListenerUtil.mutListener.listen(1061)) {
                                        c = writeStage.onWrite(MPDAsyncTask.this, writer);
                                    }
                                    if (!ListenerUtil.mutListener.listen(1062)) {
                                        writer.flush();
                                    }
                                } else {
                                    if (!ListenerUtil.mutListener.listen(1060)) {
                                        c = false;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public void setParams(@NonNull MPDClient mpdClient, @NonNull MPDServerData mpdServerData) {
        if (!ListenerUtil.mutListener.listen(1066)) {
            this.mpdClient = mpdClient;
        }
        if (!ListenerUtil.mutListener.listen(1067)) {
            this.mpdServerData = new MPDServerData(mpdServerData);
        }
    }

    public MPDServerData getMpdServerData() {
        return mpdServerData;
    }

    public void notifyServerUpdated() {
        if (!ListenerUtil.mutListener.listen(1068)) {
            mpdClient.notifyServerUpdate(mpdServerData);
        }
    }

    protected static MPDAsyncTask.ReadStage okReadStage() {
        return (task, result) -> {
            boolean ok = result.startsWith("OK");
            if (!ok) {
                task.fail();
            }
            return ok;
        };
    }

    protected static MPDAsyncTask.WriteStage statusWriteStage() {
        return (task, bufferedWriter) -> {
            bufferedWriter.write("status\n");
            return true;
        };
    }

    protected static MPDAsyncTask.WriteStage loginWriteStage(String password) {
        return (task, bufferedWriter) -> {
            bufferedWriter.write("password " + password + "\n");
            return true;
        };
    }

    protected static MPDAsyncTask.ReadStage statusReadStage(boolean c) {
        return (task, result) -> {
            task.getMpdServerData().updateStatus(result);
            task.notifyServerUpdated();
            return c;
        };
    }
}
