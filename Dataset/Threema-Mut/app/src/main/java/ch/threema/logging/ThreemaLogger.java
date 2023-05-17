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
package ch.threema.logging;

import android.util.Log;
import org.slf4j.helpers.MarkerIgnoringBase;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import ch.threema.logging.backend.LogBackend;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * The Threema logger. It logs all messages using the registered backend(s).
 */
@SuppressWarnings("unused")
public class ThreemaLogger extends MarkerIgnoringBase {

    @NonNull
    private final String tag;

    @NonNull
    private final List<LogBackend> backends = new ArrayList<>();

    @Nullable
    private String prefix = null;

    ThreemaLogger(@NonNull String logTag, @NonNull LogBackend backend) {
        this.tag = logTag;
        if (!ListenerUtil.mutListener.listen(69502)) {
            this.backends.add(backend);
        }
    }

    ThreemaLogger(@NonNull String logTag, @NonNull LogBackend[] backends) {
        this.tag = logTag;
        if (!ListenerUtil.mutListener.listen(69503)) {
            this.backends.addAll(Arrays.asList(backends));
        }
    }

    ThreemaLogger(@NonNull String logTag, @NonNull List<LogBackend> backends) {
        this.tag = logTag;
        if (!ListenerUtil.mutListener.listen(69504)) {
            this.backends.addAll(backends);
        }
    }

    public void print(@LogLevel int level, @Nullable Throwable throwable, @Nullable String message) {
        if (!ListenerUtil.mutListener.listen(69506)) {
            if (this.prefix != null) {
                if (!ListenerUtil.mutListener.listen(69505)) {
                    message = this.prefix + ": " + message;
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(69508)) {
            {
                long _loopCounter895 = 0;
                for (LogBackend backend : this.backends) {
                    ListenerUtil.loopListener.listen("_loopCounter895", ++_loopCounter895);
                    if (!ListenerUtil.mutListener.listen(69507)) {
                        backend.print(level, this.tag, throwable, message);
                    }
                }
            }
        }
    }

    public void print(@LogLevel int level, @Nullable Throwable throwable, @NonNull String messageFormat, Object... args) {
        if (!ListenerUtil.mutListener.listen(69510)) {
            if (this.prefix != null) {
                if (!ListenerUtil.mutListener.listen(69509)) {
                    messageFormat = this.prefix + ": " + messageFormat;
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(69512)) {
            {
                long _loopCounter896 = 0;
                for (LogBackend backend : this.backends) {
                    ListenerUtil.loopListener.listen("_loopCounter896", ++_loopCounter896);
                    if (!ListenerUtil.mutListener.listen(69511)) {
                        backend.print(level, this.tag, throwable, messageFormat, args);
                    }
                }
            }
        }
    }

    public void setPrefix(@Nullable String prefix) {
        if (!ListenerUtil.mutListener.listen(69513)) {
            this.prefix = prefix;
        }
    }

    @Override
    public boolean isTraceEnabled() {
        return true;
    }

    @Override
    public boolean isDebugEnabled() {
        return true;
    }

    @Override
    public boolean isInfoEnabled() {
        return true;
    }

    @Override
    public boolean isWarnEnabled() {
        return true;
    }

    @Override
    public boolean isErrorEnabled() {
        return true;
    }

    @Override
    public void trace(String msg) {
        if (!ListenerUtil.mutListener.listen(69514)) {
            this.print(Log.VERBOSE, null, msg);
        }
    }

    @Override
    public void trace(String format, Object arg) {
        if (!ListenerUtil.mutListener.listen(69515)) {
            this.print(Log.VERBOSE, null, format, arg);
        }
    }

    @Override
    public void trace(String format, Object arg1, Object arg2) {
        if (!ListenerUtil.mutListener.listen(69516)) {
            this.print(Log.VERBOSE, null, format, arg1, arg2);
        }
    }

    @Override
    public void trace(String format, Object... arguments) {
        if (!ListenerUtil.mutListener.listen(69517)) {
            this.print(Log.VERBOSE, null, format, arguments);
        }
    }

    @Override
    public void trace(String msg, Throwable t) {
        if (!ListenerUtil.mutListener.listen(69518)) {
            this.print(Log.VERBOSE, t, msg);
        }
    }

    @Override
    public void debug(String msg) {
        if (!ListenerUtil.mutListener.listen(69519)) {
            this.print(Log.DEBUG, null, msg);
        }
    }

    @Override
    public void debug(String format, Object arg) {
        if (!ListenerUtil.mutListener.listen(69520)) {
            this.print(Log.DEBUG, null, format, arg);
        }
    }

    @Override
    public void debug(String format, Object arg1, Object arg2) {
        if (!ListenerUtil.mutListener.listen(69521)) {
            this.print(Log.DEBUG, null, format, arg1, arg2);
        }
    }

    @Override
    public void debug(String format, Object... arguments) {
        if (!ListenerUtil.mutListener.listen(69522)) {
            this.print(Log.DEBUG, null, format, arguments);
        }
    }

    @Override
    public void debug(String msg, Throwable t) {
        if (!ListenerUtil.mutListener.listen(69523)) {
            this.print(Log.DEBUG, t, msg);
        }
    }

    @Override
    public void info(String msg) {
        if (!ListenerUtil.mutListener.listen(69524)) {
            this.print(Log.INFO, null, msg);
        }
    }

    @Override
    public void info(String format, Object arg) {
        if (!ListenerUtil.mutListener.listen(69525)) {
            this.print(Log.INFO, null, format, arg);
        }
    }

    @Override
    public void info(String format, Object arg1, Object arg2) {
        if (!ListenerUtil.mutListener.listen(69526)) {
            this.print(Log.INFO, null, format, arg1, arg2);
        }
    }

    @Override
    public void info(String format, Object... arguments) {
        if (!ListenerUtil.mutListener.listen(69527)) {
            this.print(Log.INFO, null, format, arguments);
        }
    }

    @Override
    public void info(String msg, Throwable t) {
        if (!ListenerUtil.mutListener.listen(69528)) {
            this.print(Log.INFO, t, msg);
        }
    }

    @Override
    public void warn(String msg) {
        if (!ListenerUtil.mutListener.listen(69529)) {
            this.print(Log.WARN, null, msg);
        }
    }

    @Override
    public void warn(String format, Object arg) {
        if (!ListenerUtil.mutListener.listen(69530)) {
            this.print(Log.WARN, null, format, arg);
        }
    }

    @Override
    public void warn(String format, Object arg1, Object arg2) {
        if (!ListenerUtil.mutListener.listen(69531)) {
            this.print(Log.WARN, null, format, arg1, arg2);
        }
    }

    @Override
    public void warn(String format, Object... arguments) {
        if (!ListenerUtil.mutListener.listen(69532)) {
            this.print(Log.WARN, null, format, arguments);
        }
    }

    @Override
    public void warn(String msg, Throwable t) {
        if (!ListenerUtil.mutListener.listen(69533)) {
            this.print(Log.WARN, t, msg);
        }
    }

    @Override
    public void error(String msg) {
        if (!ListenerUtil.mutListener.listen(69534)) {
            this.print(Log.ERROR, null, msg);
        }
    }

    @Override
    public void error(String format, Object arg) {
        if (!ListenerUtil.mutListener.listen(69535)) {
            this.print(Log.ERROR, null, format, arg);
        }
    }

    @Override
    public void error(String format, Object arg1, Object arg2) {
        if (!ListenerUtil.mutListener.listen(69536)) {
            this.print(Log.ERROR, null, format, arg1, arg2);
        }
    }

    @Override
    public void error(String format, Object... arguments) {
        if (!ListenerUtil.mutListener.listen(69537)) {
            this.print(Log.ERROR, null, format, arguments);
        }
    }

    @Override
    public void error(String msg, @Nullable Throwable t) {
        if (!ListenerUtil.mutListener.listen(69538)) {
            this.print(Log.ERROR, t, msg);
        }
    }
}
