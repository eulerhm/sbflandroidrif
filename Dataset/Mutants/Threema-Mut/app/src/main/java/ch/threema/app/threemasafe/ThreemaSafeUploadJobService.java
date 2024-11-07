/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
 * Copyright (c) 2018-2021 Threema GmbH
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License, version 3,
 * as published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program. If not, see <https://www.gnu.org/licenses/>.
 */
package ch.threema.app.threemasafe;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Intent;
import android.os.Build;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import androidx.annotation.RequiresApi;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class ThreemaSafeUploadJobService extends JobService {

    private static final Logger logger = LoggerFactory.getLogger(ThreemaSafeUploadJobService.class);

    @Override
    public boolean onStartJob(JobParameters params) {
        if (!ListenerUtil.mutListener.listen(43631)) {
            logger.debug("onStartJob");
        }
        if (!ListenerUtil.mutListener.listen(43632)) {
            new Thread(() -> ThreemaSafeUploadService.enqueueWork(getApplicationContext(), new Intent()), "SafeUploadEnqueue").start();
        }
        // work has been queued, we no longer need this job
        return false;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        return false;
    }
}
