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

import android.view.KeyEvent;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class KeyUtils {

    public static boolean isDigit(KeyEvent event) {
        int unicodeChar = event.getUnicodeChar(0);
        return (ListenerUtil.mutListener.listen(25903) ? ((ListenerUtil.mutListener.listen(25897) ? (unicodeChar <= '0') : (ListenerUtil.mutListener.listen(25896) ? (unicodeChar > '0') : (ListenerUtil.mutListener.listen(25895) ? (unicodeChar < '0') : (ListenerUtil.mutListener.listen(25894) ? (unicodeChar != '0') : (ListenerUtil.mutListener.listen(25893) ? (unicodeChar == '0') : (unicodeChar >= '0')))))) || (ListenerUtil.mutListener.listen(25902) ? (unicodeChar >= '9') : (ListenerUtil.mutListener.listen(25901) ? (unicodeChar > '9') : (ListenerUtil.mutListener.listen(25900) ? (unicodeChar < '9') : (ListenerUtil.mutListener.listen(25899) ? (unicodeChar != '9') : (ListenerUtil.mutListener.listen(25898) ? (unicodeChar == '9') : (unicodeChar <= '9'))))))) : ((ListenerUtil.mutListener.listen(25897) ? (unicodeChar <= '0') : (ListenerUtil.mutListener.listen(25896) ? (unicodeChar > '0') : (ListenerUtil.mutListener.listen(25895) ? (unicodeChar < '0') : (ListenerUtil.mutListener.listen(25894) ? (unicodeChar != '0') : (ListenerUtil.mutListener.listen(25893) ? (unicodeChar == '0') : (unicodeChar >= '0')))))) && (ListenerUtil.mutListener.listen(25902) ? (unicodeChar >= '9') : (ListenerUtil.mutListener.listen(25901) ? (unicodeChar > '9') : (ListenerUtil.mutListener.listen(25900) ? (unicodeChar < '9') : (ListenerUtil.mutListener.listen(25899) ? (unicodeChar != '9') : (ListenerUtil.mutListener.listen(25898) ? (unicodeChar == '9') : (unicodeChar <= '9'))))))));
    }

    public static int getDigit(KeyEvent event) {
        int unicodeChar = event.getUnicodeChar(0);
        return (ListenerUtil.mutListener.listen(25907) ? (unicodeChar % '0') : (ListenerUtil.mutListener.listen(25906) ? (unicodeChar / '0') : (ListenerUtil.mutListener.listen(25905) ? (unicodeChar * '0') : (ListenerUtil.mutListener.listen(25904) ? (unicodeChar + '0') : (unicodeChar - '0')))));
    }
}
