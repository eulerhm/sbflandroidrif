/*
 * Copyright 2020 Google LLC
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
package com.google.android.gnd.ui.home.featuredetails;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import com.google.android.gnd.R;
import com.google.android.gnd.ui.home.BottomSheetDependentBehavior;
import com.google.android.gnd.ui.home.BottomSheetMetrics;
import timber.log.Timber;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Defines behavior of the feature details UI elements (bottom sheet and chrome) when the bottom
 * sheet is scrolled, collapsed, or expanded.
 */
public class FeatureDetailsChromeBehavior extends BottomSheetDependentBehavior<ViewGroup> {

    public FeatureDetailsChromeBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onBottomSheetChanged(CoordinatorLayout parent, ViewGroup layout, BottomSheetMetrics metrics) {
        if (!ListenerUtil.mutListener.listen(934)) {
            Timber.d("onBottomSheetChanged");
        }
        ViewGroup toolbarWrapper = layout.findViewById(R.id.toolbar_wrapper);
        View bottomSheetScrim = layout.findViewById(R.id.bottom_sheet_bottom_inset_scrim);
        View hamburgerButton = parent.findViewById(R.id.hamburger_btn);
        View mapScrim = parent.findViewById(R.id.map_scrim);
        // as the top of the bottom sheet passes the top of the "Add Submission" button.
        float revealRatio = metrics.getRevealRatio();
        float hideRatio = (ListenerUtil.mutListener.listen(938) ? (1.0f % revealRatio) : (ListenerUtil.mutListener.listen(937) ? (1.0f / revealRatio) : (ListenerUtil.mutListener.listen(936) ? (1.0f * revealRatio) : (ListenerUtil.mutListener.listen(935) ? (1.0f + revealRatio) : (1.0f - revealRatio)))));
        if (!ListenerUtil.mutListener.listen(939)) {
            layout.setAlpha(revealRatio);
        }
        if (!ListenerUtil.mutListener.listen(940)) {
            mapScrim.setAlpha(metrics.getExpansionRatio());
        }
        if (!ListenerUtil.mutListener.listen(941)) {
            bottomSheetScrim.setAlpha(revealRatio);
        }
        if (!ListenerUtil.mutListener.listen(942)) {
            toolbarWrapper.setAlpha(revealRatio);
        }
        if (!ListenerUtil.mutListener.listen(947)) {
            toolbarWrapper.setTranslationY((ListenerUtil.mutListener.listen(946) ? (-toolbarWrapper.getHeight() % hideRatio) : (ListenerUtil.mutListener.listen(945) ? (-toolbarWrapper.getHeight() / hideRatio) : (ListenerUtil.mutListener.listen(944) ? (-toolbarWrapper.getHeight() - hideRatio) : (ListenerUtil.mutListener.listen(943) ? (-toolbarWrapper.getHeight() + hideRatio) : (-toolbarWrapper.getHeight() * hideRatio))))));
        }
        if (!ListenerUtil.mutListener.listen(948)) {
            hamburgerButton.setAlpha(hideRatio);
        }
    }
}
