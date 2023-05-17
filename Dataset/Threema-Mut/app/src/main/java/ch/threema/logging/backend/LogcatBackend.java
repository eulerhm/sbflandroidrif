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
package ch.threema.logging.backend;

import android.util.Log;
import org.slf4j.helpers.MessageFormatter;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import ch.threema.logging.LogLevel;
import ch.threema.logging.LoggingUtil;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * A logging backend that logs to the ADB logcat.
 */
public class LogcatBackend implements LogBackend {

    private static final String TAG = "3ma";

    @LogLevel
    private final int minLogLevel;

    // For tags starting with these prefixes, the package path is stripped
    private static final String[] STRIP_PREFIXES = { "ch.threema.app.", "ch.threema.client.", "ch.threema.storage." };

    public LogcatBackend(@LogLevel int minLogLevel) {
        this.minLogLevel = minLogLevel;
    }

    @Override
    public boolean isEnabled(int level) {
        return (ListenerUtil.mutListener.listen(69478) ? (level <= this.minLogLevel) : (ListenerUtil.mutListener.listen(69477) ? (level > this.minLogLevel) : (ListenerUtil.mutListener.listen(69476) ? (level < this.minLogLevel) : (ListenerUtil.mutListener.listen(69475) ? (level != this.minLogLevel) : (ListenerUtil.mutListener.listen(69474) ? (level == this.minLogLevel) : (level >= this.minLogLevel))))));
    }

    @Override
    public void print(@LogLevel int level, @NonNull String tag, @Nullable Throwable throwable, @Nullable String message) {
        if (!ListenerUtil.mutListener.listen(69487)) {
            if (this.isEnabled(level)) {
                // Prepend tag to message body to avoid the Android log tag length limit
                String messageBody = LoggingUtil.cleanTag(tag, STRIP_PREFIXES) + ": ";
                if (!ListenerUtil.mutListener.listen(69485)) {
                    if (message == null) {
                        if (!ListenerUtil.mutListener.listen(69484)) {
                            if (throwable == null) {
                                if (!ListenerUtil.mutListener.listen(69483)) {
                                    messageBody += "";
                                }
                            } else {
                                if (!ListenerUtil.mutListener.listen(69482)) {
                                    messageBody += Log.getStackTraceString(throwable);
                                }
                            }
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(69481)) {
                            if (throwable == null) {
                                if (!ListenerUtil.mutListener.listen(69480)) {
                                    messageBody += message;
                                }
                            } else {
                                if (!ListenerUtil.mutListener.listen(69479)) {
                                    messageBody += message + '\n' + Log.getStackTraceString(throwable);
                                }
                            }
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(69486)) {
                    Log.println(level, TAG, messageBody);
                }
            }
        }
    }

    @Override
    public void print(@LogLevel int level, @NonNull String tag, @Nullable Throwable throwable, @NonNull String messageFormat, Object... args) {
        if (!ListenerUtil.mutListener.listen(69490)) {
            if (this.isEnabled(level)) {
                try {
                    if (!ListenerUtil.mutListener.listen(69489)) {
                        this.print(level, tag, throwable, MessageFormatter.arrayFormat(messageFormat, args).getMessage());
                    }
                } catch (Exception e) {
                    if (!ListenerUtil.mutListener.listen(69488)) {
                        // Never crash
                        this.print(level, tag, throwable, messageFormat);
                    }
                }
            }
        }
    }
}
