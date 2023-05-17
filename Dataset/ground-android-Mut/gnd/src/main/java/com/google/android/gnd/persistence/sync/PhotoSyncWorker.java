/*
 * Copyright 2020 Google LLC
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
import com.google.android.gnd.persistence.remote.RemoteStorageManager;
import com.google.android.gnd.system.NotificationManager;
import com.google.firebase.crashlytics.FirebaseCrashlytics;
import dagger.assisted.Assisted;
import dagger.assisted.AssistedInject;
import java.io.File;
import java.io.FileNotFoundException;
import timber.log.Timber;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * A worker that uploads photos from submissions to the FirestoreStorage in the background. The
 * source file and remote destination path are provided in a {@link Data} object. This worker should
 * only run when the device has a network connection.
 */
@HiltWorker
public class PhotoSyncWorker extends BaseWorker {

    private static final String SOURCE_FILE_PATH_PARAM_KEY = "sourceFilePath";

    private static final String DESTINATION_PATH_PARAM_KEY = "destinationPath";

    private final RemoteStorageManager remoteStorageManager;

    private final String localSourcePath;

    private final String remoteDestinationPath;

    @AssistedInject
    public PhotoSyncWorker(@Assisted @NonNull Context context, @Assisted @NonNull WorkerParameters workerParams, RemoteStorageManager remoteStorageManager, NotificationManager notificationManager) {
        super(context, workerParams, notificationManager, PhotoSyncWorker.class.hashCode());
        this.remoteStorageManager = remoteStorageManager;
        this.localSourcePath = workerParams.getInputData().getString(SOURCE_FILE_PATH_PARAM_KEY);
        this.remoteDestinationPath = workerParams.getInputData().getString(DESTINATION_PATH_PARAM_KEY);
    }

    static Data createInputData(String sourceFilePath, String destinationPath) {
        return new Data.Builder().putString(SOURCE_FILE_PATH_PARAM_KEY, sourceFilePath).putString(DESTINATION_PATH_PARAM_KEY, destinationPath).build();
    }

    @NonNull
    @Override
    public Result doWork() {
        if (!ListenerUtil.mutListener.listen(1365)) {
            Timber.d("Attempting photo sync: %s, %s", localSourcePath, remoteDestinationPath);
        }
        File file = new File(localSourcePath);
        if (file.exists()) {
            if (!ListenerUtil.mutListener.listen(1369)) {
                Timber.d("Starting photo upload: %s, %s", localSourcePath, remoteDestinationPath);
            }
            try {
                if (!ListenerUtil.mutListener.listen(1373)) {
                    remoteStorageManager.uploadMediaFromFile(file, remoteDestinationPath).compose(this::notifyTransferState).blockingForEach(this::sendNotification);
                }
                return Result.success();
            } catch (Exception e) {
                if (!ListenerUtil.mutListener.listen(1370)) {
                    FirebaseCrashlytics.getInstance().log("Photo sync failed");
                }
                if (!ListenerUtil.mutListener.listen(1371)) {
                    FirebaseCrashlytics.getInstance().recordException(e);
                }
                if (!ListenerUtil.mutListener.listen(1372)) {
                    Timber.e(e, "Photo sync failed: %s %s", localSourcePath, remoteDestinationPath);
                }
                return Result.retry();
            }
        } else {
            if (!ListenerUtil.mutListener.listen(1366)) {
                FirebaseCrashlytics.getInstance().log("Photo missing on local device");
            }
            if (!ListenerUtil.mutListener.listen(1367)) {
                FirebaseCrashlytics.getInstance().recordException(new FileNotFoundException());
            }
            if (!ListenerUtil.mutListener.listen(1368)) {
                Timber.e("Photo not found %s, %s", localSourcePath, remoteDestinationPath);
            }
            return Result.failure();
        }
    }

    @Override
    public String getNotificationTitle() {
        return getApplicationContext().getString(R.string.uploading_photos);
    }
}
