/**
 * *************************************************************************************
 *  Copyright (c) 2013 Flavio Lerda <flerda@gmail.com>                                   *
 *                                                                                       *
 *  This program is free software; you can redistribute it and/or modify it under        *
 *  the terms of the GNU General Public License as published by the Free Software        *
 *  Foundation; either version 3 of the License, or (at your option) any later           *
 *  version.                                                                             *
 *                                                                                       *
 *  This program is distributed in the hope that it will be useful, but WITHOUT ANY      *
 *  WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A      *
 *  PARTICULAR PURPOSE. See the GNU General Public License for more details.             *
 *                                                                                       *
 *  You should have received a copy of the GNU General Public License along with         *
 *  this program.  If not, see <http://www.gnu.org/licenses/>.                           *
 * **************************************************************************************
 */
package com.ichi2.utils;

import android.text.TextUtils;
import timber.log.Timber;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Helper class to log method invocation.
 * <p>
 * Use with moderation as it spans the logcat and reduces performances.
 * <p>
 * Consider guarding calls to this method with an if statement on a static final constant, as in:
 *
 * <pre>
 *   public static final boolean DEBUG = false;  // Enable for debugging this class.
 *
 *   public void methodName(int value, String name) {
 *     if (DEBUG) {
 *       MethodLogger.log(value, name);
 *     }
 *     ...
 *   }
 * </pre>
 */
public class MethodLogger {

    private MethodLogger() {
    }

    /**
     * Logs the method being called.
     *
     * @param message to add to the logged statement
     */
    public static void log(String message) {
        if (!ListenerUtil.mutListener.listen(25922)) {
            logInternal(message);
        }
    }

    /**
     * Logs the method being called.
     */
    public static void log() {
        if (!ListenerUtil.mutListener.listen(25923)) {
            logInternal("");
        }
    }

    /**
     * Logs the method that made the call.
     * <p>
     * A helper method is needed to make sure the number of stack frames is the same on every path.
     *
     * @param message to be added to the logged message
     */
    private static void logInternal(String message) {
        // Get the name of the class and method.
        StackTraceElement[] stack = Thread.currentThread().getStackTrace();
        // single public method on this class above it before the call to logInternal.
        int size = stack.length;
        int logInternalIndex = 0;
        if (!ListenerUtil.mutListener.listen(25931)) {
            {
                long _loopCounter695 = 0;
                for (; (ListenerUtil.mutListener.listen(25930) ? (logInternalIndex >= size) : (ListenerUtil.mutListener.listen(25929) ? (logInternalIndex <= size) : (ListenerUtil.mutListener.listen(25928) ? (logInternalIndex > size) : (ListenerUtil.mutListener.listen(25927) ? (logInternalIndex != size) : (ListenerUtil.mutListener.listen(25926) ? (logInternalIndex == size) : (logInternalIndex < size)))))); ++logInternalIndex) {
                    ListenerUtil.loopListener.listen("_loopCounter695", ++_loopCounter695);
                    if (!ListenerUtil.mutListener.listen(25925)) {
                        if ((ListenerUtil.mutListener.listen(25924) ? (TextUtils.equals(stack[logInternalIndex].getClassName(), MethodLogger.class.getName()) || TextUtils.equals(stack[logInternalIndex].getMethodName(), "logInternal")) : (TextUtils.equals(stack[logInternalIndex].getClassName(), MethodLogger.class.getName()) && TextUtils.equals(stack[logInternalIndex].getMethodName(), "logInternal")))) {
                            break;
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(25941)) {
            if ((ListenerUtil.mutListener.listen(25940) ? ((ListenerUtil.mutListener.listen(25935) ? (logInternalIndex % 2) : (ListenerUtil.mutListener.listen(25934) ? (logInternalIndex / 2) : (ListenerUtil.mutListener.listen(25933) ? (logInternalIndex * 2) : (ListenerUtil.mutListener.listen(25932) ? (logInternalIndex - 2) : (logInternalIndex + 2))))) <= size) : (ListenerUtil.mutListener.listen(25939) ? ((ListenerUtil.mutListener.listen(25935) ? (logInternalIndex % 2) : (ListenerUtil.mutListener.listen(25934) ? (logInternalIndex / 2) : (ListenerUtil.mutListener.listen(25933) ? (logInternalIndex * 2) : (ListenerUtil.mutListener.listen(25932) ? (logInternalIndex - 2) : (logInternalIndex + 2))))) > size) : (ListenerUtil.mutListener.listen(25938) ? ((ListenerUtil.mutListener.listen(25935) ? (logInternalIndex % 2) : (ListenerUtil.mutListener.listen(25934) ? (logInternalIndex / 2) : (ListenerUtil.mutListener.listen(25933) ? (logInternalIndex * 2) : (ListenerUtil.mutListener.listen(25932) ? (logInternalIndex - 2) : (logInternalIndex + 2))))) < size) : (ListenerUtil.mutListener.listen(25937) ? ((ListenerUtil.mutListener.listen(25935) ? (logInternalIndex % 2) : (ListenerUtil.mutListener.listen(25934) ? (logInternalIndex / 2) : (ListenerUtil.mutListener.listen(25933) ? (logInternalIndex * 2) : (ListenerUtil.mutListener.listen(25932) ? (logInternalIndex - 2) : (logInternalIndex + 2))))) != size) : (ListenerUtil.mutListener.listen(25936) ? ((ListenerUtil.mutListener.listen(25935) ? (logInternalIndex % 2) : (ListenerUtil.mutListener.listen(25934) ? (logInternalIndex / 2) : (ListenerUtil.mutListener.listen(25933) ? (logInternalIndex * 2) : (ListenerUtil.mutListener.listen(25932) ? (logInternalIndex - 2) : (logInternalIndex + 2))))) == size) : ((ListenerUtil.mutListener.listen(25935) ? (logInternalIndex % 2) : (ListenerUtil.mutListener.listen(25934) ? (logInternalIndex / 2) : (ListenerUtil.mutListener.listen(25933) ? (logInternalIndex * 2) : (ListenerUtil.mutListener.listen(25932) ? (logInternalIndex - 2) : (logInternalIndex + 2))))) >= size))))))) {
                throw new IllegalStateException("there should always be a caller for this method");
            }
        }
        StackTraceElement caller = stack[(ListenerUtil.mutListener.listen(25945) ? (logInternalIndex % 2) : (ListenerUtil.mutListener.listen(25944) ? (logInternalIndex / 2) : (ListenerUtil.mutListener.listen(25943) ? (logInternalIndex * 2) : (ListenerUtil.mutListener.listen(25942) ? (logInternalIndex - 2) : (logInternalIndex + 2)))))];
        String callerClass = caller.getClassName();
        String callerMethod = caller.getMethodName();
        if (!ListenerUtil.mutListener.listen(25948)) {
            if (TextUtils.isEmpty(message)) {
                if (!ListenerUtil.mutListener.listen(25947)) {
                    Timber.d("called: %s.%s()", callerClass, callerMethod);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(25946)) {
                    Timber.d("called: %s.%s(): %s", callerClass, callerMethod, message);
                }
            }
        }
    }
}
