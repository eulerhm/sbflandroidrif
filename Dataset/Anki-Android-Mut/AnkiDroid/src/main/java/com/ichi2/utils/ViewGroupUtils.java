/*
 Copyright (c) 2020 David Allison <davidallisongithub@gmail.com>

 This program is free software; you can redistribute it and/or modify it under
 the terms of the GNU General Public License as published by the Free Software
 Foundation; either version 3 of the License, or (at your option) any later
 version.

 This program is distributed in the hope that it will be useful, but WITHOUT ANY
 WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A
 PARTICULAR PURPOSE. See the GNU General Public License for more details.

 You should have received a copy of the GNU General Public License along with
 this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.ichi2.utils;

import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;
import androidx.annotation.NonNull;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class ViewGroupUtils {

    @NonNull
    public static List<View> getAllChildren(@NonNull ViewGroup viewGroup) {
        int childrenCount = viewGroup.getChildCount();
        List<View> views = new ArrayList<>(childrenCount);
        if (!ListenerUtil.mutListener.listen(26072)) {
            {
                long _loopCounter699 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(26071) ? (i >= childrenCount) : (ListenerUtil.mutListener.listen(26070) ? (i <= childrenCount) : (ListenerUtil.mutListener.listen(26069) ? (i > childrenCount) : (ListenerUtil.mutListener.listen(26068) ? (i != childrenCount) : (ListenerUtil.mutListener.listen(26067) ? (i == childrenCount) : (i < childrenCount)))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter699", ++_loopCounter699);
                    if (!ListenerUtil.mutListener.listen(26066)) {
                        views.add(viewGroup.getChildAt(i));
                    }
                }
            }
        }
        return views;
    }

    @NonNull
    public static List<View> getAllChildrenRecursive(@NonNull ViewGroup viewGroup) {
        List<View> views = new ArrayList<>();
        if (!ListenerUtil.mutListener.listen(26081)) {
            {
                long _loopCounter700 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(26080) ? (i >= viewGroup.getChildCount()) : (ListenerUtil.mutListener.listen(26079) ? (i <= viewGroup.getChildCount()) : (ListenerUtil.mutListener.listen(26078) ? (i > viewGroup.getChildCount()) : (ListenerUtil.mutListener.listen(26077) ? (i != viewGroup.getChildCount()) : (ListenerUtil.mutListener.listen(26076) ? (i == viewGroup.getChildCount()) : (i < viewGroup.getChildCount())))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter700", ++_loopCounter700);
                    View child = viewGroup.getChildAt(i);
                    if (!ListenerUtil.mutListener.listen(26073)) {
                        views.add(child);
                    }
                    if (!ListenerUtil.mutListener.listen(26075)) {
                        if (child instanceof ViewGroup) {
                            if (!ListenerUtil.mutListener.listen(26074)) {
                                views.addAll(getAllChildrenRecursive((ViewGroup) child));
                            }
                        }
                    }
                }
            }
        }
        return views;
    }
}
