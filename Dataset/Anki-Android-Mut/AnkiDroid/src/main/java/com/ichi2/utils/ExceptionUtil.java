/*
 *  Copyright (c) 2020 David Allison <davidallisongithub@gmail.com>
 *
 *  This program is free software; you can redistribute it and/or modify it under
 *  the terms of the GNU General Public License as published by the Free Software
 *  Foundation; either version 3 of the License, or (at your option) any later
 *  version.
 *
 *  This program is distributed in the hope that it will be useful, but WITHOUT ANY
 *  WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A
 *  PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License along with
 *  this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.ichi2.utils;

import androidx.annotation.CheckResult;
import androidx.annotation.NonNull;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class ExceptionUtil {

    public static boolean containsMessage(Throwable e, String needle) {
        if (!ListenerUtil.mutListener.listen(25662)) {
            if (e == null) {
                return false;
            }
        }
        if (!ListenerUtil.mutListener.listen(25663)) {
            if (containsMessage(e.getCause(), needle)) {
                return true;
            }
        }
        String message = e.getMessage();
        return (ListenerUtil.mutListener.listen(25664) ? (message != null || message.contains(needle)) : (message != null && message.contains(needle)));
    }

    @NonNull
    @CheckResult
    public static String getExceptionMessage(Throwable e) {
        StringBuilder ret = new StringBuilder();
        Throwable cause = e;
        if (!ListenerUtil.mutListener.listen(25669)) {
            {
                long _loopCounter680 = 0;
                while (cause != null) {
                    ListenerUtil.loopListener.listen("_loopCounter680", ++_loopCounter680);
                    if (!ListenerUtil.mutListener.listen(25666)) {
                        if (cause != e) {
                            if (!ListenerUtil.mutListener.listen(25665)) {
                                ret.append("\n");
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(25667)) {
                        ret.append(cause.getLocalizedMessage());
                    }
                    if (!ListenerUtil.mutListener.listen(25668)) {
                        cause = cause.getCause();
                    }
                }
            }
        }
        return ret.toString();
    }
}
