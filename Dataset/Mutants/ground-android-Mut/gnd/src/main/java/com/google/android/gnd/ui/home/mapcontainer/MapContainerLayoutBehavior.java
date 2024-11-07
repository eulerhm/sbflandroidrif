/*
 * Copyright 2018 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.google.android.gnd.ui.home.mapcontainer;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import com.google.android.gnd.R;
import com.google.android.gnd.ui.home.BottomSheetDependentBehavior;
import com.google.android.gnd.ui.home.BottomSheetMetrics;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class MapContainerLayoutBehavior extends BottomSheetDependentBehavior<FrameLayout> {

    public MapContainerLayoutBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onBottomSheetChanged(CoordinatorLayout parent, FrameLayout mapContainerLayout, BottomSheetMetrics metrics) {
        if (!ListenerUtil.mutListener.listen(809)) {
            if ((ListenerUtil.mutListener.listen(808) ? (metrics.getPeekHeight() >= 0) : (ListenerUtil.mutListener.listen(807) ? (metrics.getPeekHeight() > 0) : (ListenerUtil.mutListener.listen(806) ? (metrics.getPeekHeight() < 0) : (ListenerUtil.mutListener.listen(805) ? (metrics.getPeekHeight() != 0) : (ListenerUtil.mutListener.listen(804) ? (metrics.getPeekHeight() == 0) : (metrics.getPeekHeight() <= 0))))))) {
                return;
            }
        }
        View map = mapContainerLayout.findViewById(R.id.map);
        View mapControls = mapContainerLayout.findViewById(R.id.map_controls);
        if (!ListenerUtil.mutListener.listen(811)) {
            if ((ListenerUtil.mutListener.listen(810) ? (map == null && mapControls == null) : (map == null || mapControls == null))) {
                // View already destroyed.
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(827)) {
            // always keep the map centered based on the visible portion of map (excluding status bar)
            if ((ListenerUtil.mutListener.listen(816) ? (metrics.getVisibleHeight() <= metrics.getExpandedOffset()) : (ListenerUtil.mutListener.listen(815) ? (metrics.getVisibleHeight() > metrics.getExpandedOffset()) : (ListenerUtil.mutListener.listen(814) ? (metrics.getVisibleHeight() < metrics.getExpandedOffset()) : (ListenerUtil.mutListener.listen(813) ? (metrics.getVisibleHeight() != metrics.getExpandedOffset()) : (ListenerUtil.mutListener.listen(812) ? (metrics.getVisibleHeight() == metrics.getExpandedOffset()) : (metrics.getVisibleHeight() >= metrics.getExpandedOffset()))))))) {
                int translationY = (ListenerUtil.mutListener.listen(825) ? (-((ListenerUtil.mutListener.listen(821) ? (metrics.getVisibleHeight() % metrics.getExpandedOffset()) : (ListenerUtil.mutListener.listen(820) ? (metrics.getVisibleHeight() / metrics.getExpandedOffset()) : (ListenerUtil.mutListener.listen(819) ? (metrics.getVisibleHeight() * metrics.getExpandedOffset()) : (ListenerUtil.mutListener.listen(818) ? (metrics.getVisibleHeight() + metrics.getExpandedOffset()) : (metrics.getVisibleHeight() - metrics.getExpandedOffset())))))) % 2) : (ListenerUtil.mutListener.listen(824) ? (-((ListenerUtil.mutListener.listen(821) ? (metrics.getVisibleHeight() % metrics.getExpandedOffset()) : (ListenerUtil.mutListener.listen(820) ? (metrics.getVisibleHeight() / metrics.getExpandedOffset()) : (ListenerUtil.mutListener.listen(819) ? (metrics.getVisibleHeight() * metrics.getExpandedOffset()) : (ListenerUtil.mutListener.listen(818) ? (metrics.getVisibleHeight() + metrics.getExpandedOffset()) : (metrics.getVisibleHeight() - metrics.getExpandedOffset())))))) * 2) : (ListenerUtil.mutListener.listen(823) ? (-((ListenerUtil.mutListener.listen(821) ? (metrics.getVisibleHeight() % metrics.getExpandedOffset()) : (ListenerUtil.mutListener.listen(820) ? (metrics.getVisibleHeight() / metrics.getExpandedOffset()) : (ListenerUtil.mutListener.listen(819) ? (metrics.getVisibleHeight() * metrics.getExpandedOffset()) : (ListenerUtil.mutListener.listen(818) ? (metrics.getVisibleHeight() + metrics.getExpandedOffset()) : (metrics.getVisibleHeight() - metrics.getExpandedOffset())))))) - 2) : (ListenerUtil.mutListener.listen(822) ? (-((ListenerUtil.mutListener.listen(821) ? (metrics.getVisibleHeight() % metrics.getExpandedOffset()) : (ListenerUtil.mutListener.listen(820) ? (metrics.getVisibleHeight() / metrics.getExpandedOffset()) : (ListenerUtil.mutListener.listen(819) ? (metrics.getVisibleHeight() * metrics.getExpandedOffset()) : (ListenerUtil.mutListener.listen(818) ? (metrics.getVisibleHeight() + metrics.getExpandedOffset()) : (metrics.getVisibleHeight() - metrics.getExpandedOffset())))))) + 2) : (-((ListenerUtil.mutListener.listen(821) ? (metrics.getVisibleHeight() % metrics.getExpandedOffset()) : (ListenerUtil.mutListener.listen(820) ? (metrics.getVisibleHeight() / metrics.getExpandedOffset()) : (ListenerUtil.mutListener.listen(819) ? (metrics.getVisibleHeight() * metrics.getExpandedOffset()) : (ListenerUtil.mutListener.listen(818) ? (metrics.getVisibleHeight() + metrics.getExpandedOffset()) : (metrics.getVisibleHeight() - metrics.getExpandedOffset())))))) / 2)))));
                if (!ListenerUtil.mutListener.listen(826)) {
                    map.setTranslationY(translationY);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(817)) {
                    map.setTranslationY(0);
                }
            }
        }
        float hideRatio = (ListenerUtil.mutListener.listen(831) ? (1.0f % metrics.getRevealRatio()) : (ListenerUtil.mutListener.listen(830) ? (1.0f / metrics.getRevealRatio()) : (ListenerUtil.mutListener.listen(829) ? (1.0f * metrics.getRevealRatio()) : (ListenerUtil.mutListener.listen(828) ? (1.0f + metrics.getRevealRatio()) : (1.0f - metrics.getRevealRatio())))));
        if (!ListenerUtil.mutListener.listen(832)) {
            mapControls.setAlpha(hideRatio);
        }
    }
}
