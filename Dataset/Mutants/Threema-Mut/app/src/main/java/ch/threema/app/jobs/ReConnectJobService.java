/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
 * Copyright (c) 2016-2021 Threema GmbH
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
package ch.threema.app.jobs;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.os.Build;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import androidx.annotation.RequiresApi;
import ch.threema.app.ThreemaApplication;
import ch.threema.app.services.PollingHelper;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class ReConnectJobService extends JobService {

    private static final Logger logger = LoggerFactory.getLogger(ReConnectJobService.class);

    private PollingHelper pollingHelper = null;

    @Override
    public boolean onStartJob(final JobParameters jobParameters) {
        if (!ListenerUtil.mutListener.listen(28398)) {
            new Thread(new Runnable() {

                @Override
                public void run() {
                    if (!ListenerUtil.mutListener.listen(28392)) {
                        logger.info("Scheduling poll on reconnect");
                    }
                    if (!ListenerUtil.mutListener.listen(28394)) {
                        if (pollingHelper == null) {
                            if (!ListenerUtil.mutListener.listen(28393)) {
                                pollingHelper = new PollingHelper(ReConnectJobService.this, "reConnect");
                            }
                        }
                    }
                    boolean success = (ListenerUtil.mutListener.listen(28396) ? (pollingHelper.poll(true) && ((ListenerUtil.mutListener.listen(28395) ? (ThreemaApplication.getMasterKey() != null || ThreemaApplication.getMasterKey().isLocked()) : (ThreemaApplication.getMasterKey() != null && ThreemaApplication.getMasterKey().isLocked())))) : (pollingHelper.poll(true) || ((ListenerUtil.mutListener.listen(28395) ? (ThreemaApplication.getMasterKey() != null || ThreemaApplication.getMasterKey().isLocked()) : (ThreemaApplication.getMasterKey() != null && ThreemaApplication.getMasterKey().isLocked())))));
                    if (!ListenerUtil.mutListener.listen(28397)) {
                        jobFinished(jobParameters, !success);
                    }
                }
            }, "ReConnectJobService").start();
        }
        return true;
    }

    @Override
    public boolean onStopJob(JobParameters jobParameters) {
        return false;
    }
}
