/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
 * Copyright (c) 2013-2021 Threema GmbH
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

import android.content.Context;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class LoggingUEH implements Thread.UncaughtExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(LoggingUEH.class);

    private final Thread.UncaughtExceptionHandler defaultUEH;

    private Context context;

    private Runnable runOnUncaughtException;

    public LoggingUEH(Context context) {
        this.defaultUEH = Thread.getDefaultUncaughtExceptionHandler();
        if (!ListenerUtil.mutListener.listen(54717)) {
            this.context = context;
        }
    }

    public void setRunOnUncaughtException(Runnable runOnUncaughtException) {
        if (!ListenerUtil.mutListener.listen(54718)) {
            this.runOnUncaughtException = runOnUncaughtException;
        }
    }

    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        if (!ListenerUtil.mutListener.listen(54719)) {
            logger.error("Uncaught exception", ex);
        }
        if (!ListenerUtil.mutListener.listen(54721)) {
            if (runOnUncaughtException != null)
                if (!ListenerUtil.mutListener.listen(54720)) {
                    runOnUncaughtException.run();
                }
        }
        if (!ListenerUtil.mutListener.listen(54723)) {
            if (defaultUEH != null) {
                if (!ListenerUtil.mutListener.listen(54722)) {
                    defaultUEH.uncaughtException(thread, ex);
                }
            }
        }
    }
}
