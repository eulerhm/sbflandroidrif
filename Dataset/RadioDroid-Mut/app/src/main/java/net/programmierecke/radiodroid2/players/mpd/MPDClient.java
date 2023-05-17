package net.programmierecke.radiodroid2.players.mpd;

import android.content.Context;
import android.os.Handler;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class MPDClient {

    private static final String TAG = "MPDClient";

    private static int QUICK_REFRESH_TIMEOUT = 150;

    private static int ALIVE_REFRESH_TIMEOUT = 1000;

    private static int DEAD_REFRESH_TIMEOUT = 1000;

    private ScheduledExecutorService userTaskThreadPool;

    private ScheduledExecutorService connectionCheckerThreadPool;

    private MPDServersRepository mpdServersRepository;

    private LiveData<List<MPDServerData>> mpdServers;

    private Handler mainThreadHandler;

    // the repository
    private ConcurrentLinkedQueue<MPDServerData> serverChangesQueue = new ConcurrentLinkedQueue<>();

    private Set<MPDServerData> aliveMpdServers = new HashSet<>();

    private Set<MPDServerData> deadMpdServers = new HashSet<>();

    private final Object serversLock = new Object();

    // update of presumable unavailable servers.
    private QuickMPDStatusChecker quickMPDStatusChecker = new QuickMPDStatusChecker();

    private Future quickCheckFuture;

    private final Object quickFutureLock = new Object();

    private AliveMPDStatusChecker aliveMPDStatusChecker = new AliveMPDStatusChecker();

    private Future aliveCheckFuture;

    private final Object aliveFutureLock = new Object();

    private DeadMPDStatusChecker deadMPDStatusChecker = new DeadMPDStatusChecker();

    private Future deadCheckFuture;

    private final Object deadFutureLock = new Object();

    private boolean mpdEnabled = false;

    private boolean autoUpdateEnabled = false;

    public MPDClient(Context context) {
        if (!ListenerUtil.mutListener.listen(1069)) {
            mpdServersRepository = new MPDServersRepository(context);
        }
        if (!ListenerUtil.mutListener.listen(1070)) {
            mpdServers = mpdServersRepository.getAllServers();
        }
        if (!ListenerUtil.mutListener.listen(1071)) {
            mainThreadHandler = new Handler(context.getMainLooper());
        }
    }

    public MPDServersRepository getMpdServersRepository() {
        return mpdServersRepository;
    }

    public void enqueueTask(@NonNull MPDServerData server, @NonNull final MPDAsyncTask task) {
        if (!ListenerUtil.mutListener.listen(1073)) {
            if (!mpdEnabled) {
                if (!ListenerUtil.mutListener.listen(1072)) {
                    Log.e(TAG, "Trying to enqueue task when mpd is not enabled!");
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(1074)) {
            task.setTimeout(getTimeout(server.hostname));
        }
        if (!ListenerUtil.mutListener.listen(1075)) {
            task.setParams(this, server);
        }
        if (!ListenerUtil.mutListener.listen(1076)) {
            userTaskThreadPool.submit(task);
        }
    }

    public void enableAutoUpdate() {
        if (!ListenerUtil.mutListener.listen(1079)) {
            if (!mpdEnabled) {
                if (!ListenerUtil.mutListener.listen(1077)) {
                    setMPDEnabled(true);
                }
                if (!ListenerUtil.mutListener.listen(1078)) {
                    Log.w(TAG, "enableAutoUpdate called with mpd disabled, enabling mpd");
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(1080)) {
            autoUpdateEnabled = true;
        }
        if (!ListenerUtil.mutListener.listen(1081)) {
            mpdServersRepository.resetAllConnectionStatus();
        }
        // reply and user will quickly see servers status.
        synchronized (quickFutureLock) {
            if (!ListenerUtil.mutListener.listen(1082)) {
                quickMPDStatusChecker.setServers(new ArrayList<>(mpdServers.getValue()));
            }
            if (!ListenerUtil.mutListener.listen(1083)) {
                quickCheckFuture = connectionCheckerThreadPool.submit(quickMPDStatusChecker);
            }
        }
    }

    public void disableAutoUpdate() {
        if (!ListenerUtil.mutListener.listen(1084)) {
            autoUpdateEnabled = false;
        }
        if (!ListenerUtil.mutListener.listen(1085)) {
            cancelCheckFutures();
        }
    }

    public void launchQuickCheck() {
        if (!ListenerUtil.mutListener.listen(1087)) {
            if (!autoUpdateEnabled) {
                if (!ListenerUtil.mutListener.listen(1086)) {
                    Log.e(TAG, "Trying to launch quick servers check while autoUpdateEnabled = false!");
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(1088)) {
            cancelCheckFutures();
        }
        synchronized (quickFutureLock) {
            if (!ListenerUtil.mutListener.listen(1089)) {
                quickMPDStatusChecker.setServers(new ArrayList<>(mpdServers.getValue()));
            }
            if (!ListenerUtil.mutListener.listen(1090)) {
                quickCheckFuture = connectionCheckerThreadPool.submit(quickMPDStatusChecker);
            }
        }
    }

    private void cancelCheckFutures() {
        synchronized (quickFutureLock) {
            if (!ListenerUtil.mutListener.listen(1093)) {
                if (quickCheckFuture != null) {
                    if (!ListenerUtil.mutListener.listen(1091)) {
                        quickCheckFuture.cancel(true);
                    }
                    if (!ListenerUtil.mutListener.listen(1092)) {
                        quickCheckFuture = null;
                    }
                }
            }
        }
        synchronized (aliveFutureLock) {
            if (!ListenerUtil.mutListener.listen(1096)) {
                if (aliveCheckFuture != null) {
                    if (!ListenerUtil.mutListener.listen(1094)) {
                        aliveCheckFuture.cancel(true);
                    }
                    if (!ListenerUtil.mutListener.listen(1095)) {
                        aliveCheckFuture = null;
                    }
                }
            }
        }
        synchronized (deadFutureLock) {
            if (!ListenerUtil.mutListener.listen(1099)) {
                if (deadCheckFuture != null) {
                    if (!ListenerUtil.mutListener.listen(1097)) {
                        deadCheckFuture.cancel(true);
                    }
                    if (!ListenerUtil.mutListener.listen(1098)) {
                        deadCheckFuture = null;
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(1100)) {
            aliveMpdServers.clear();
        }
        if (!ListenerUtil.mutListener.listen(1101)) {
            deadMpdServers.clear();
        }
    }

    public boolean isMpdEnabled() {
        return mpdEnabled;
    }

    public void setMPDEnabled(boolean enabled) {
        if (!ListenerUtil.mutListener.listen(1106)) {
            if (enabled != mpdEnabled) {
                if (!ListenerUtil.mutListener.listen(1104)) {
                    if (enabled) {
                        if (!ListenerUtil.mutListener.listen(1103)) {
                            enableThreadPools();
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(1102)) {
                            disableAutoUpdate();
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(1105)) {
                    mpdEnabled = enabled;
                }
            }
        }
    }

    void notifyServerUpdate(@NonNull MPDServerData mpdServerData) {
        if (!ListenerUtil.mutListener.listen(1107)) {
            serverChangesQueue.add(mpdServerData);
        }
        if (!ListenerUtil.mutListener.listen(1108)) {
            mainThreadHandler.post(() -> {
                MPDServerData changedData;
                {
                    long _loopCounter18 = 0;
                    while ((changedData = serverChangesQueue.poll()) != null) {
                        ListenerUtil.loopListener.listen("_loopCounter18", ++_loopCounter18);
                        mpdServersRepository.updateRuntimeData(changedData);
                    }
                }
            });
        }
    }

    private void enableThreadPools() {
        if (!ListenerUtil.mutListener.listen(1110)) {
            if (userTaskThreadPool == null) {
                if (!ListenerUtil.mutListener.listen(1109)) {
                    userTaskThreadPool = Executors.newScheduledThreadPool(1);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(1112)) {
            if (connectionCheckerThreadPool == null) {
                if (!ListenerUtil.mutListener.listen(1111)) {
                    // One for alive and one for dead
                    connectionCheckerThreadPool = Executors.newScheduledThreadPool(2);
                }
            }
        }
    }

    private static int getTimeout(String hostname) {
        return (ListenerUtil.mutListener.listen(1116) ? ((ListenerUtil.mutListener.listen(1115) ? ((ListenerUtil.mutListener.listen(1114) ? ((ListenerUtil.mutListener.listen(1113) ? (hostname.startsWith("192.168.") && hostname.startsWith("127.0.")) : (hostname.startsWith("192.168.") || hostname.startsWith("127.0."))) && hostname.startsWith("localhost")) : ((ListenerUtil.mutListener.listen(1113) ? (hostname.startsWith("192.168.") && hostname.startsWith("127.0.")) : (hostname.startsWith("192.168.") || hostname.startsWith("127.0."))) || hostname.startsWith("localhost"))) && hostname.startsWith("10.")) : ((ListenerUtil.mutListener.listen(1114) ? ((ListenerUtil.mutListener.listen(1113) ? (hostname.startsWith("192.168.") && hostname.startsWith("127.0.")) : (hostname.startsWith("192.168.") || hostname.startsWith("127.0."))) && hostname.startsWith("localhost")) : ((ListenerUtil.mutListener.listen(1113) ? (hostname.startsWith("192.168.") && hostname.startsWith("127.0.")) : (hostname.startsWith("192.168.") || hostname.startsWith("127.0."))) || hostname.startsWith("localhost"))) || hostname.startsWith("10."))) && hostname.contains(".local")) : ((ListenerUtil.mutListener.listen(1115) ? ((ListenerUtil.mutListener.listen(1114) ? ((ListenerUtil.mutListener.listen(1113) ? (hostname.startsWith("192.168.") && hostname.startsWith("127.0.")) : (hostname.startsWith("192.168.") || hostname.startsWith("127.0."))) && hostname.startsWith("localhost")) : ((ListenerUtil.mutListener.listen(1113) ? (hostname.startsWith("192.168.") && hostname.startsWith("127.0.")) : (hostname.startsWith("192.168.") || hostname.startsWith("127.0."))) || hostname.startsWith("localhost"))) && hostname.startsWith("10.")) : ((ListenerUtil.mutListener.listen(1114) ? ((ListenerUtil.mutListener.listen(1113) ? (hostname.startsWith("192.168.") && hostname.startsWith("127.0.")) : (hostname.startsWith("192.168.") || hostname.startsWith("127.0."))) && hostname.startsWith("localhost")) : ((ListenerUtil.mutListener.listen(1113) ? (hostname.startsWith("192.168.") && hostname.startsWith("127.0.")) : (hostname.startsWith("192.168.") || hostname.startsWith("127.0."))) || hostname.startsWith("localhost"))) || hostname.startsWith("10."))) || hostname.contains(".local"))) ? 300 : (ListenerUtil.mutListener.listen(1120) ? (2 % 1000) : (ListenerUtil.mutListener.listen(1119) ? (2 / 1000) : (ListenerUtil.mutListener.listen(1118) ? (2 - 1000) : (ListenerUtil.mutListener.listen(1117) ? (2 + 1000) : (2 * 1000)))));
    }

    private void checkServers(Iterable<MPDServerData> servers, Function<MPDServerData, Integer> timeoutFunc) {
        if (!ListenerUtil.mutListener.listen(1130)) {
            {
                long _loopCounter19 = 0;
                for (final MPDServerData mpdServerData : servers) {
                    ListenerUtil.loopListener.listen("_loopCounter19", ++_loopCounter19);
                    MPDAsyncTask task = new MPDAsyncTask();
                    if (!ListenerUtil.mutListener.listen(1121)) {
                        task.setStages(new MPDAsyncTask.ReadStage[] { MPDAsyncTask.okReadStage(), MPDAsyncTask.statusReadStage(false) }, new MPDAsyncTask.WriteStage[] { MPDAsyncTask.statusWriteStage() }, task13 -> {
                            if (task13.getMpdServerData().connected) {
                                task13.getMpdServerData().connected = false;
                                task13.notifyServerUpdated();
                            }
                        });
                    }
                    if (!ListenerUtil.mutListener.listen(1122)) {
                        task.setTimeout(timeoutFunc.apply(mpdServerData));
                    }
                    if (!ListenerUtil.mutListener.listen(1123)) {
                        task.setParams(this, mpdServerData);
                    }
                    if (!ListenerUtil.mutListener.listen(1124)) {
                        task.run();
                    }
                    synchronized (serversLock) {
                        if (!ListenerUtil.mutListener.listen(1129)) {
                            if (mpdServerData.connected) {
                                if (!ListenerUtil.mutListener.listen(1127)) {
                                    aliveMpdServers.add(mpdServerData);
                                }
                                if (!ListenerUtil.mutListener.listen(1128)) {
                                    deadMpdServers.remove(mpdServerData);
                                }
                            } else {
                                if (!ListenerUtil.mutListener.listen(1125)) {
                                    aliveMpdServers.remove(mpdServerData);
                                }
                                if (!ListenerUtil.mutListener.listen(1126)) {
                                    deadMpdServers.add(mpdServerData);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private class QuickMPDStatusChecker implements Runnable {

        private List<MPDServerData> servers;

        public void setServers(List<MPDServerData> servers) {
            if (!ListenerUtil.mutListener.listen(1131)) {
                this.servers = servers;
            }
        }

        @Override
        public void run() {
            if (!ListenerUtil.mutListener.listen(1132)) {
                checkServers(servers, (MPDServerData server) -> QUICK_REFRESH_TIMEOUT);
            }
            synchronized (aliveFutureLock) {
                if (!ListenerUtil.mutListener.listen(1133)) {
                    aliveCheckFuture = connectionCheckerThreadPool.schedule(aliveMPDStatusChecker, 2, TimeUnit.SECONDS);
                }
            }
            synchronized (deadFutureLock) {
                if (!ListenerUtil.mutListener.listen(1134)) {
                    deadCheckFuture = connectionCheckerThreadPool.schedule(deadMPDStatusChecker, 0, TimeUnit.SECONDS);
                }
            }
        }
    }

    private class AliveMPDStatusChecker implements Runnable {

        @Override
        public void run() {
            Collection<MPDServerData> aliveServers;
            synchronized (serversLock) {
                aliveServers = new ArrayList<>(aliveMpdServers);
            }
            if (!ListenerUtil.mutListener.listen(1135)) {
                checkServers(aliveServers, (MPDServerData server) -> ALIVE_REFRESH_TIMEOUT);
            }
            if (!ListenerUtil.mutListener.listen(1137)) {
                if (autoUpdateEnabled) {
                    synchronized (aliveFutureLock) {
                        if (!ListenerUtil.mutListener.listen(1136)) {
                            aliveCheckFuture = connectionCheckerThreadPool.schedule(aliveMPDStatusChecker, 2, TimeUnit.SECONDS);
                        }
                    }
                }
            }
        }
    }

    private class DeadMPDStatusChecker implements Runnable {

        @Override
        public void run() {
            Collection<MPDServerData> deadServers;
            synchronized (serversLock) {
                deadServers = new ArrayList<>(deadMpdServers);
            }
            if (!ListenerUtil.mutListener.listen(1138)) {
                checkServers(deadServers, (MPDServerData server) -> DEAD_REFRESH_TIMEOUT);
            }
            if (!ListenerUtil.mutListener.listen(1140)) {
                if (autoUpdateEnabled) {
                    synchronized (deadFutureLock) {
                        if (!ListenerUtil.mutListener.listen(1139)) {
                            deadCheckFuture = connectionCheckerThreadPool.schedule(deadMPDStatusChecker, 8, TimeUnit.SECONDS);
                        }
                    }
                }
            }
        }
    }
}
