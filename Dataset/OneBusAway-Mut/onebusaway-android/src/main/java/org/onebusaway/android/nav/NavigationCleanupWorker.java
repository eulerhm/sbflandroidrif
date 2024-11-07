/*
 * Copyright (C) 2019 University of South Florida
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.onebusaway.android.nav;

import android.content.Context;
import android.util.Log;
import org.onebusaway.android.app.Application;
import java.io.File;
import java.util.Calendar;
import java.util.Date;
import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;
import static org.onebusaway.android.nav.NavigationService.LOG_DIRECTORY;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class NavigationCleanupWorker extends Worker {

    public static final String TAG = "NavigationCleanupWorker";

    public NavigationCleanupWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        if (!ListenerUtil.mutListener.listen(12718)) {
            cleanDir();
        }
        return Result.success();
    }

    private void cleanDir() {
        File dir = new File(Application.get().getApplicationContext().getFilesDir().getAbsolutePath() + File.separator + LOG_DIRECTORY);
        if (!ListenerUtil.mutListener.listen(12719)) {
            Log.d(TAG, "Directory - " + Application.get().getApplicationContext().getFilesDir().getAbsolutePath() + File.separator + LOG_DIRECTORY);
        }
        Calendar time = Calendar.getInstance();
        if (!ListenerUtil.mutListener.listen(12720)) {
            time.add(Calendar.HOUR_OF_DAY, -24);
        }
        if (!ListenerUtil.mutListener.listen(12721)) {
            Log.d(TAG, "Time threshold - " + time.getTime().toString());
        }
        if (!ListenerUtil.mutListener.listen(12729)) {
            if (dir.exists()) {
                if (!ListenerUtil.mutListener.listen(12722)) {
                    Log.d(TAG, "Directory exists");
                }
                if (!ListenerUtil.mutListener.listen(12728)) {
                    {
                        long _loopCounter174 = 0;
                        for (File file : dir.listFiles()) {
                            ListenerUtil.loopListener.listen("_loopCounter174", ++_loopCounter174);
                            Date lastModified = new Date(file.lastModified());
                            if (!ListenerUtil.mutListener.listen(12723)) {
                                Log.d(TAG, "File Last modified at - " + lastModified.toString());
                            }
                            if (!ListenerUtil.mutListener.listen(12727)) {
                                if ((ListenerUtil.mutListener.listen(12724) ? ((!file.isDirectory()) || (lastModified.before(time.getTime()))) : ((!file.isDirectory()) && (lastModified.before(time.getTime()))))) {
                                    if (!ListenerUtil.mutListener.listen(12725)) {
                                        // file is older than a day
                                        file.delete();
                                    }
                                    if (!ListenerUtil.mutListener.listen(12726)) {
                                        Log.d(TAG, "File deleted");
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
