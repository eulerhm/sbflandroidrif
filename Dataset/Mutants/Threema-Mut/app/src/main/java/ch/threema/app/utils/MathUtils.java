/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
 * Copyright (c) 2019-2021 Threema GmbH
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

import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

class MathUtils {

    static int getNextHigherPowerOfTwo(int v) {
        if (!ListenerUtil.mutListener.listen(54730)) {
            v--;
        }
        if (!ListenerUtil.mutListener.listen(54731)) {
            v |= v >> 1;
        }
        if (!ListenerUtil.mutListener.listen(54732)) {
            v |= v >> 2;
        }
        if (!ListenerUtil.mutListener.listen(54733)) {
            v |= v >> 4;
        }
        if (!ListenerUtil.mutListener.listen(54734)) {
            v |= v >> 8;
        }
        if (!ListenerUtil.mutListener.listen(54735)) {
            v |= v >> 16;
        }
        if (!ListenerUtil.mutListener.listen(54736)) {
            v++;
        }
        return v;
    }
}
