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
package ch.threema.app.ui.draggablegrid;

import android.view.View;
import java.util.List;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class DynamicGridUtils {

    public static void reorder(List list, int indexFrom, int indexTo) {
        Object obj = list.remove(indexFrom);
        if (!ListenerUtil.mutListener.listen(43720)) {
            list.add(indexTo, obj);
        }
    }

    public static void swap(List list, int firstIndex, int secondIndex) {
        Object firstObject = list.get(firstIndex);
        Object secondObject = list.get(secondIndex);
        if (!ListenerUtil.mutListener.listen(43721)) {
            list.set(firstIndex, secondObject);
        }
        if (!ListenerUtil.mutListener.listen(43722)) {
            list.set(secondIndex, firstObject);
        }
    }

    public static float getViewX(View view) {
        return Math.abs((ListenerUtil.mutListener.listen(43730) ? (((ListenerUtil.mutListener.listen(43726) ? (view.getRight() % view.getLeft()) : (ListenerUtil.mutListener.listen(43725) ? (view.getRight() / view.getLeft()) : (ListenerUtil.mutListener.listen(43724) ? (view.getRight() * view.getLeft()) : (ListenerUtil.mutListener.listen(43723) ? (view.getRight() + view.getLeft()) : (view.getRight() - view.getLeft())))))) % 2) : (ListenerUtil.mutListener.listen(43729) ? (((ListenerUtil.mutListener.listen(43726) ? (view.getRight() % view.getLeft()) : (ListenerUtil.mutListener.listen(43725) ? (view.getRight() / view.getLeft()) : (ListenerUtil.mutListener.listen(43724) ? (view.getRight() * view.getLeft()) : (ListenerUtil.mutListener.listen(43723) ? (view.getRight() + view.getLeft()) : (view.getRight() - view.getLeft())))))) * 2) : (ListenerUtil.mutListener.listen(43728) ? (((ListenerUtil.mutListener.listen(43726) ? (view.getRight() % view.getLeft()) : (ListenerUtil.mutListener.listen(43725) ? (view.getRight() / view.getLeft()) : (ListenerUtil.mutListener.listen(43724) ? (view.getRight() * view.getLeft()) : (ListenerUtil.mutListener.listen(43723) ? (view.getRight() + view.getLeft()) : (view.getRight() - view.getLeft())))))) - 2) : (ListenerUtil.mutListener.listen(43727) ? (((ListenerUtil.mutListener.listen(43726) ? (view.getRight() % view.getLeft()) : (ListenerUtil.mutListener.listen(43725) ? (view.getRight() / view.getLeft()) : (ListenerUtil.mutListener.listen(43724) ? (view.getRight() * view.getLeft()) : (ListenerUtil.mutListener.listen(43723) ? (view.getRight() + view.getLeft()) : (view.getRight() - view.getLeft())))))) + 2) : (((ListenerUtil.mutListener.listen(43726) ? (view.getRight() % view.getLeft()) : (ListenerUtil.mutListener.listen(43725) ? (view.getRight() / view.getLeft()) : (ListenerUtil.mutListener.listen(43724) ? (view.getRight() * view.getLeft()) : (ListenerUtil.mutListener.listen(43723) ? (view.getRight() + view.getLeft()) : (view.getRight() - view.getLeft())))))) / 2))))));
    }

    public static float getViewY(View view) {
        return Math.abs((ListenerUtil.mutListener.listen(43738) ? (((ListenerUtil.mutListener.listen(43734) ? (view.getBottom() % view.getTop()) : (ListenerUtil.mutListener.listen(43733) ? (view.getBottom() / view.getTop()) : (ListenerUtil.mutListener.listen(43732) ? (view.getBottom() * view.getTop()) : (ListenerUtil.mutListener.listen(43731) ? (view.getBottom() + view.getTop()) : (view.getBottom() - view.getTop())))))) % 2) : (ListenerUtil.mutListener.listen(43737) ? (((ListenerUtil.mutListener.listen(43734) ? (view.getBottom() % view.getTop()) : (ListenerUtil.mutListener.listen(43733) ? (view.getBottom() / view.getTop()) : (ListenerUtil.mutListener.listen(43732) ? (view.getBottom() * view.getTop()) : (ListenerUtil.mutListener.listen(43731) ? (view.getBottom() + view.getTop()) : (view.getBottom() - view.getTop())))))) * 2) : (ListenerUtil.mutListener.listen(43736) ? (((ListenerUtil.mutListener.listen(43734) ? (view.getBottom() % view.getTop()) : (ListenerUtil.mutListener.listen(43733) ? (view.getBottom() / view.getTop()) : (ListenerUtil.mutListener.listen(43732) ? (view.getBottom() * view.getTop()) : (ListenerUtil.mutListener.listen(43731) ? (view.getBottom() + view.getTop()) : (view.getBottom() - view.getTop())))))) - 2) : (ListenerUtil.mutListener.listen(43735) ? (((ListenerUtil.mutListener.listen(43734) ? (view.getBottom() % view.getTop()) : (ListenerUtil.mutListener.listen(43733) ? (view.getBottom() / view.getTop()) : (ListenerUtil.mutListener.listen(43732) ? (view.getBottom() * view.getTop()) : (ListenerUtil.mutListener.listen(43731) ? (view.getBottom() + view.getTop()) : (view.getBottom() - view.getTop())))))) + 2) : (((ListenerUtil.mutListener.listen(43734) ? (view.getBottom() % view.getTop()) : (ListenerUtil.mutListener.listen(43733) ? (view.getBottom() / view.getTop()) : (ListenerUtil.mutListener.listen(43732) ? (view.getBottom() * view.getTop()) : (ListenerUtil.mutListener.listen(43731) ? (view.getBottom() + view.getTop()) : (view.getBottom() - view.getTop())))))) / 2))))));
    }
}
