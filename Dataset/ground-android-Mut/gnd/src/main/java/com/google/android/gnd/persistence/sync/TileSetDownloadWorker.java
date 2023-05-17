/*
 * Copyright 2019 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.google.android.gnd.persistence.sync;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.hilt.work.HiltWorker;
import androidx.work.Data;
import androidx.work.WorkerParameters;
import com.google.android.gnd.R;
import com.google.android.gnd.model.basemap.tile.TileSet;
import com.google.android.gnd.model.basemap.tile.TileSet.State;
import com.google.android.gnd.persistence.local.LocalDataStore;
import com.google.android.gnd.persistence.remote.TransferProgress;
import com.google.android.gnd.system.NotificationManager;
import com.google.common.collect.ImmutableList;
import dagger.assisted.Assisted;
import dagger.assisted.AssistedInject;
import io.reactivex.Completable;
import io.reactivex.Observable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import timber.log.Timber;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * A worker that downloads files to the device in the background. The target URL and file name are
 * provided in a {@link Data} object. This worker should only run when the device has a network
 * connection.
 */
@HiltWorker
public class TileSetDownloadWorker extends BaseWorker {

    private static final int BUFFER_SIZE = 4096;

    private final Context context;

    private final LocalDataStore localDataStore;

    @AssistedInject
    public TileSetDownloadWorker(@Assisted @NonNull Context context, @Assisted @NonNull WorkerParameters params, LocalDataStore localDataStore, NotificationManager notificationManager) {
        super(context, params, notificationManager, TileSetDownloadWorker.class.hashCode());
        this.context = context;
        this.localDataStore = localDataStore;
    }

    /**
     * Given a tile, downloads the given {@param tile}'s source file and saves it to the device's app
     * storage. Optional HTTP request header {@param requestProperties} may be provided.
     */
    private void downloadTileFile(TileSet tileSet, Map<String, String> requestProperties) throws TileSetDownloadException {
        int mode = Context.MODE_PRIVATE;
        try {
            URL url = new URL(tileSet.getUrl());
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            if (!ListenerUtil.mutListener.listen(1389)) {
                if (!requestProperties.isEmpty()) {
                    if (!ListenerUtil.mutListener.listen(1387)) {
                        {
                            long _loopCounter42 = 0;
                            for (Map.Entry<String, String> property : requestProperties.entrySet()) {
                                ListenerUtil.loopListener.listen("_loopCounter42", ++_loopCounter42);
                                if (!ListenerUtil.mutListener.listen(1386)) {
                                    connection.setRequestProperty(property.getKey(), property.getValue());
                                }
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(1388)) {
                        mode = Context.MODE_APPEND;
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(1390)) {
                connection.connect();
            }
            try (InputStream is = connection.getInputStream();
                FileOutputStream fos = context.openFileOutput(tileSet.getPath(), mode)) {
                byte[] byteChunk = new byte[BUFFER_SIZE];
                int n;
                if (!ListenerUtil.mutListener.listen(1397)) {
                    {
                        long _loopCounter43 = 0;
                        while ((ListenerUtil.mutListener.listen(1396) ? ((n = is.read(byteChunk)) >= 0) : (ListenerUtil.mutListener.listen(1395) ? ((n = is.read(byteChunk)) <= 0) : (ListenerUtil.mutListener.listen(1394) ? ((n = is.read(byteChunk)) < 0) : (ListenerUtil.mutListener.listen(1393) ? ((n = is.read(byteChunk)) != 0) : (ListenerUtil.mutListener.listen(1392) ? ((n = is.read(byteChunk)) == 0) : ((n = is.read(byteChunk)) > 0))))))) {
                            ListenerUtil.loopListener.listen("_loopCounter43", ++_loopCounter43);
                            if (!ListenerUtil.mutListener.listen(1391)) {
                                fos.write(byteChunk, 0, n);
                            }
                        }
                    }
                }
            }
        } catch (IOException e) {
            throw new TileSetDownloadException("Failed to download tile", e);
        }
    }

    /**
     * Update a tile's state in the database and initiate a download of the tile source file.
     */
    private Completable downloadTileSet(TileSet tileSet) {
        Map<String, String> requestProperties = new HashMap<>();
        if (!ListenerUtil.mutListener.listen(1399)) {
            // For more info see: https://developer.mozilla.org/en-US/docs/Web/HTTP/Headers/Range
            if (tileSet.getState() == State.IN_PROGRESS) {
                File existingTileFile = new File(context.getFilesDir(), tileSet.getPath());
                if (!ListenerUtil.mutListener.listen(1398)) {
                    requestProperties.put("Range", "bytes=" + existingTileFile.length() + "-");
                }
            }
        }
        return localDataStore.insertOrUpdateTileSet(tileSet.toBuilder().setState(TileSet.State.IN_PROGRESS).build()).andThen(Completable.fromRunnable(() -> {
            downloadTileFile(tileSet, requestProperties);
        })).onErrorResumeNext(e -> {
            Timber.d(e, "Failed to download tile: %s", tileSet);
            return localDataStore.insertOrUpdateTileSet(tileSet.toBuilder().setState(State.FAILED).build());
        }).andThen(localDataStore.insertOrUpdateTileSet(tileSet.toBuilder().setState(State.DOWNLOADED).build()));
    }

    /**
     * Verifies that {@param tile} marked as {@code Tile.State.DOWNLOADED} in the local database still
     * exists in the app's storage. If the tile's source file isn't present, initiates a download of
     * source file.
     */
    private Completable downloadIfNotFound(TileSet tileSet) {
        File file = new File(context.getFilesDir(), tileSet.getPath());
        if (!ListenerUtil.mutListener.listen(1400)) {
            if (file.exists()) {
                return Completable.complete();
            }
        }
        return downloadTileSet(tileSet);
    }

    private Completable processTileSets(ImmutableList<TileSet> pendingTileSets) {
        return Observable.fromIterable(pendingTileSets).doOnNext(tile -> sendNotification(TransferProgress.inProgress(pendingTileSets.size(), pendingTileSets.indexOf(tile) + 1))).flatMapCompletable(t -> {
            switch(t.getState()) {
                case DOWNLOADED:
                    return downloadIfNotFound(t);
                case PENDING:
                case IN_PROGRESS:
                case FAILED:
                default:
                    return downloadTileSet(t);
            }
        }).compose(this::notifyTransferState);
    }

    /**
     * Given a tile identifier, downloads a tile source file and saves it to the app's file storage.
     * If the tile source file already exists on the device, this method returns {@code
     * Result.success()} and does not re-download the file.
     */
    @NonNull
    @Override
    public Result doWork() {
        ImmutableList<TileSet> pendingTileSets = localDataStore.getPendingTileSets().blockingGet();
        // In this case, we return a result immediately to stop the worker.
        if (pendingTileSets == null) {
            return Result.success();
        }
        if (!ListenerUtil.mutListener.listen(1401)) {
            Timber.d("Downloading tiles: %s", pendingTileSets);
        }
        try {
            if (!ListenerUtil.mutListener.listen(1403)) {
                processTileSets(pendingTileSets).blockingAwait();
            }
            return Result.success();
        } catch (Throwable t) {
            if (!ListenerUtil.mutListener.listen(1402)) {
                Timber.d(t, "Downloads for tiles failed: %s", pendingTileSets);
            }
            return Result.failure();
        }
    }

    @Override
    public String getNotificationTitle() {
        return getApplicationContext().getString(R.string.downloading_tiles);
    }

    static class TileSetDownloadException extends RuntimeException {

        TileSetDownloadException(String msg, Throwable e) {
            super(msg, e);
        }
    }
}
