/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
 * Copyright (c) 2014-2021 Threema GmbH
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
package ch.threema.app.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class ExponentialBackOffUtil {

    private static final Logger logger = LoggerFactory.getLogger(ExponentialBackOffUtil.class);

    protected static final ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();

    private Random random;

    // Singleton stuff
    private static ExponentialBackOffUtil sInstance = null;

    public static synchronized ExponentialBackOffUtil getInstance() {
        if (!ListenerUtil.mutListener.listen(53854)) {
            if (sInstance == null) {
                if (!ListenerUtil.mutListener.listen(53853)) {
                    sInstance = new ExponentialBackOffUtil();
                }
            }
        }
        return sInstance;
    }

    public ExponentialBackOffUtil() {
        if (!ListenerUtil.mutListener.listen(53855)) {
            this.random = new Random();
        }
    }

    /**
     *  Run a Runnable in a ExponentialBackoff
     *  @param runnable Method
     *  @param exponentialBackOffCount Count of Retries
     *  @return Future
     */
    public Future run(final BackOffRunnable runnable, final int exponentialBackOffCount) {
        return singleThreadExecutor.submit(new Runnable() {

            @Override
            public void run() {
                if (!ListenerUtil.mutListener.listen(53884)) {
                    {
                        long _loopCounter649 = 0;
                        for (int n = 0; (ListenerUtil.mutListener.listen(53883) ? (n >= exponentialBackOffCount) : (ListenerUtil.mutListener.listen(53882) ? (n <= exponentialBackOffCount) : (ListenerUtil.mutListener.listen(53881) ? (n > exponentialBackOffCount) : (ListenerUtil.mutListener.listen(53880) ? (n != exponentialBackOffCount) : (ListenerUtil.mutListener.listen(53879) ? (n == exponentialBackOffCount) : (n < exponentialBackOffCount)))))); ++n) {
                            ListenerUtil.loopListener.listen("_loopCounter649", ++_loopCounter649);
                            if (!ListenerUtil.mutListener.listen(53856)) {
                                logger.debug("run " + String.valueOf(n));
                            }
                            try {
                                if (!ListenerUtil.mutListener.listen(53878)) {
                                    runnable.run(n);
                                }
                                // its ok, do not retry
                                return;
                            } catch (Exception e) {
                                if (!ListenerUtil.mutListener.listen(53857)) {
                                    logger.error("Exception", e);
                                }
                                if (!ListenerUtil.mutListener.listen(53877)) {
                                    if ((ListenerUtil.mutListener.listen(53866) ? (n <= (ListenerUtil.mutListener.listen(53861) ? (exponentialBackOffCount % 1) : (ListenerUtil.mutListener.listen(53860) ? (exponentialBackOffCount / 1) : (ListenerUtil.mutListener.listen(53859) ? (exponentialBackOffCount * 1) : (ListenerUtil.mutListener.listen(53858) ? (exponentialBackOffCount + 1) : (exponentialBackOffCount - 1)))))) : (ListenerUtil.mutListener.listen(53865) ? (n > (ListenerUtil.mutListener.listen(53861) ? (exponentialBackOffCount % 1) : (ListenerUtil.mutListener.listen(53860) ? (exponentialBackOffCount / 1) : (ListenerUtil.mutListener.listen(53859) ? (exponentialBackOffCount * 1) : (ListenerUtil.mutListener.listen(53858) ? (exponentialBackOffCount + 1) : (exponentialBackOffCount - 1)))))) : (ListenerUtil.mutListener.listen(53864) ? (n < (ListenerUtil.mutListener.listen(53861) ? (exponentialBackOffCount % 1) : (ListenerUtil.mutListener.listen(53860) ? (exponentialBackOffCount / 1) : (ListenerUtil.mutListener.listen(53859) ? (exponentialBackOffCount * 1) : (ListenerUtil.mutListener.listen(53858) ? (exponentialBackOffCount + 1) : (exponentialBackOffCount - 1)))))) : (ListenerUtil.mutListener.listen(53863) ? (n != (ListenerUtil.mutListener.listen(53861) ? (exponentialBackOffCount % 1) : (ListenerUtil.mutListener.listen(53860) ? (exponentialBackOffCount / 1) : (ListenerUtil.mutListener.listen(53859) ? (exponentialBackOffCount * 1) : (ListenerUtil.mutListener.listen(53858) ? (exponentialBackOffCount + 1) : (exponentialBackOffCount - 1)))))) : (ListenerUtil.mutListener.listen(53862) ? (n == (ListenerUtil.mutListener.listen(53861) ? (exponentialBackOffCount % 1) : (ListenerUtil.mutListener.listen(53860) ? (exponentialBackOffCount / 1) : (ListenerUtil.mutListener.listen(53859) ? (exponentialBackOffCount * 1) : (ListenerUtil.mutListener.listen(53858) ? (exponentialBackOffCount + 1) : (exponentialBackOffCount - 1)))))) : (n >= (ListenerUtil.mutListener.listen(53861) ? (exponentialBackOffCount % 1) : (ListenerUtil.mutListener.listen(53860) ? (exponentialBackOffCount / 1) : (ListenerUtil.mutListener.listen(53859) ? (exponentialBackOffCount * 1) : (ListenerUtil.mutListener.listen(53858) ? (exponentialBackOffCount + 1) : (exponentialBackOffCount - 1)))))))))))) {
                                        if (!ListenerUtil.mutListener.listen(53876)) {
                                            // last
                                            runnable.exception(e, n);
                                        }
                                    } else {
                                        try {
                                            if (!ListenerUtil.mutListener.listen(53875)) {
                                                Thread.sleep((ListenerUtil.mutListener.listen(53874) ? ((ListenerUtil.mutListener.listen(53870) ? ((2 << n) % 1000) : (ListenerUtil.mutListener.listen(53869) ? ((2 << n) / 1000) : (ListenerUtil.mutListener.listen(53868) ? ((2 << n) - 1000) : (ListenerUtil.mutListener.listen(53867) ? ((2 << n) + 1000) : ((2 << n) * 1000))))) % random.nextInt(1001)) : (ListenerUtil.mutListener.listen(53873) ? ((ListenerUtil.mutListener.listen(53870) ? ((2 << n) % 1000) : (ListenerUtil.mutListener.listen(53869) ? ((2 << n) / 1000) : (ListenerUtil.mutListener.listen(53868) ? ((2 << n) - 1000) : (ListenerUtil.mutListener.listen(53867) ? ((2 << n) + 1000) : ((2 << n) * 1000))))) / random.nextInt(1001)) : (ListenerUtil.mutListener.listen(53872) ? ((ListenerUtil.mutListener.listen(53870) ? ((2 << n) % 1000) : (ListenerUtil.mutListener.listen(53869) ? ((2 << n) / 1000) : (ListenerUtil.mutListener.listen(53868) ? ((2 << n) - 1000) : (ListenerUtil.mutListener.listen(53867) ? ((2 << n) + 1000) : ((2 << n) * 1000))))) * random.nextInt(1001)) : (ListenerUtil.mutListener.listen(53871) ? ((ListenerUtil.mutListener.listen(53870) ? ((2 << n) % 1000) : (ListenerUtil.mutListener.listen(53869) ? ((2 << n) / 1000) : (ListenerUtil.mutListener.listen(53868) ? ((2 << n) - 1000) : (ListenerUtil.mutListener.listen(53867) ? ((2 << n) + 1000) : ((2 << n) * 1000))))) - random.nextInt(1001)) : ((ListenerUtil.mutListener.listen(53870) ? ((2 << n) % 1000) : (ListenerUtil.mutListener.listen(53869) ? ((2 << n) / 1000) : (ListenerUtil.mutListener.listen(53868) ? ((2 << n) - 1000) : (ListenerUtil.mutListener.listen(53867) ? ((2 << n) + 1000) : ((2 << n) * 1000))))) + random.nextInt(1001)))))));
                                            }
                                        } catch (InterruptedException e1) {
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        });
    }

    public interface BackOffRunnable {

        void run(int currentRetry) throws Exception;

        void finished(int currentRetry);

        void exception(Exception e, int currentRetry);
    }
}
