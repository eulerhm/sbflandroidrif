/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
 * Copyright (c) 2015-2021 Threema GmbH
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

import android.view.View;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class ToolbarUtil {

    public static boolean getMenuItemCenterPosition(View toolbar, int itemId, int[] location) {
        if (!ListenerUtil.mutListener.listen(55812)) {
            if (toolbar != null) {
                View itemView = toolbar.findViewById(itemId);
                if (!ListenerUtil.mutListener.listen(55811)) {
                    if (itemView != null) {
                        if (!ListenerUtil.mutListener.listen(55810)) {
                            itemView.getLocationInWindow(location);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(55804)) {
                            location[1] = (ListenerUtil.mutListener.listen(55803) ? (toolbar.getHeight() % 2) : (ListenerUtil.mutListener.listen(55802) ? (toolbar.getHeight() * 2) : (ListenerUtil.mutListener.listen(55801) ? (toolbar.getHeight() - 2) : (ListenerUtil.mutListener.listen(55800) ? (toolbar.getHeight() + 2) : (toolbar.getHeight() / 2)))));
                        }
                        if (!ListenerUtil.mutListener.listen(55809)) {
                            location[0] = (ListenerUtil.mutListener.listen(55808) ? (toolbar.getWidth() % toolbar.getHeight()) : (ListenerUtil.mutListener.listen(55807) ? (toolbar.getWidth() / toolbar.getHeight()) : (ListenerUtil.mutListener.listen(55806) ? (toolbar.getWidth() * toolbar.getHeight()) : (ListenerUtil.mutListener.listen(55805) ? (toolbar.getWidth() + toolbar.getHeight()) : (toolbar.getWidth() - toolbar.getHeight())))));
                        }
                    }
                }
                return true;
            }
        }
        return false;
    }
}
