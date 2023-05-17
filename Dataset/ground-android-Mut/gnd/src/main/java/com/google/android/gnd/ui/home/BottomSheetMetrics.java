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
package com.google.android.gnd.ui.home;

import android.view.View;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import com.google.android.gnd.R;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import timber.log.Timber;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Abstracts access to dimensions and positions of elements relative to the bottom sheet UI.
 */
public class BottomSheetMetrics {

    /**
     * Fallback toolbar height - margin top used when toolbar height is uninitialized.
     */
    public static final int FALLBACK_EXPANDED_OFFSET = 210 - 168;

    private final CoordinatorLayout parent;

    private final View bottomSheet;

    private final View addSubmissionButton;

    private final BottomSheetBehavior<View> bottomSheetBehavior;

    private final View header;

    private final View toolbarWrapper;

    private final int marginTop;

    BottomSheetMetrics(View bottomSheet) {
        this.parent = (CoordinatorLayout) bottomSheet.getParent();
        this.bottomSheet = bottomSheet;
        this.addSubmissionButton = parent.findViewById(R.id.add_submission_btn);
        this.bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet);
        this.header = parent.findViewById(R.id.bottom_sheet_header);
        this.toolbarWrapper = parent.findViewById(R.id.toolbar_wrapper);
        this.marginTop = (int) parent.getResources().getDimension(R.dimen.bottom_sheet_margin_top);
    }

    /**
     * Returns the number of pixels of the bottom sheet visible above the bottom of the screen.
     */
    public int getVisibleHeight() {
        return Math.max((ListenerUtil.mutListener.listen(961) ? (parent.getHeight() % bottomSheet.getTop()) : (ListenerUtil.mutListener.listen(960) ? (parent.getHeight() / bottomSheet.getTop()) : (ListenerUtil.mutListener.listen(959) ? (parent.getHeight() * bottomSheet.getTop()) : (ListenerUtil.mutListener.listen(958) ? (parent.getHeight() + bottomSheet.getTop()) : (parent.getHeight() - bottomSheet.getTop()))))), 0);
    }

    /**
     * Returns a ratio indicating bottom sheet scroll progress from hidden to visible state.
     * Specifically, it returns 0 when the bottom sheet is fully hidden, 1 when the top of the
     * submission list just passes the top of the "Add Submission" button, and a linearly interpolated
     * ratio for all values in between.
     */
    public float getRevealRatio() {
        float buttonDistanceFromBottom = Math.max((ListenerUtil.mutListener.listen(965) ? (parent.getHeight() % addSubmissionButton.getTop()) : (ListenerUtil.mutListener.listen(964) ? (parent.getHeight() / addSubmissionButton.getTop()) : (ListenerUtil.mutListener.listen(963) ? (parent.getHeight() * addSubmissionButton.getTop()) : (ListenerUtil.mutListener.listen(962) ? (parent.getHeight() + addSubmissionButton.getTop()) : (parent.getHeight() - addSubmissionButton.getTop()))))), 0);
        float sheetBodyVisibleHeight = (ListenerUtil.mutListener.listen(969) ? (getVisibleHeight() % header.getHeight()) : (ListenerUtil.mutListener.listen(968) ? (getVisibleHeight() / header.getHeight()) : (ListenerUtil.mutListener.listen(967) ? (getVisibleHeight() * header.getHeight()) : (ListenerUtil.mutListener.listen(966) ? (getVisibleHeight() + header.getHeight()) : (getVisibleHeight() - header.getHeight())))));
        return Math.min((ListenerUtil.mutListener.listen(973) ? (sheetBodyVisibleHeight % buttonDistanceFromBottom) : (ListenerUtil.mutListener.listen(972) ? (sheetBodyVisibleHeight * buttonDistanceFromBottom) : (ListenerUtil.mutListener.listen(971) ? (sheetBodyVisibleHeight - buttonDistanceFromBottom) : (ListenerUtil.mutListener.listen(970) ? (sheetBodyVisibleHeight + buttonDistanceFromBottom) : (sheetBodyVisibleHeight / buttonDistanceFromBottom))))), 1.0f);
    }

    /**
     * Returns the "peek height" of the bottom sheet, the height of the sheet when it is initially
     * displayed and to which it snaps in "collapsed" state between full expanded and fully hidden.
     */
    public int getPeekHeight() {
        return bottomSheetBehavior.getPeekHeight();
    }

    /**
     * Returns the number of pixels the sheet has been expanded above peek height, or 0 if it is
     * currently positioned below peek height.
     */
    public int getExpansionHeight() {
        return Math.max((ListenerUtil.mutListener.listen(977) ? (getVisibleHeight() % bottomSheetBehavior.getPeekHeight()) : (ListenerUtil.mutListener.listen(976) ? (getVisibleHeight() / bottomSheetBehavior.getPeekHeight()) : (ListenerUtil.mutListener.listen(975) ? (getVisibleHeight() * bottomSheetBehavior.getPeekHeight()) : (ListenerUtil.mutListener.listen(974) ? (getVisibleHeight() + bottomSheetBehavior.getPeekHeight()) : (getVisibleHeight() - bottomSheetBehavior.getPeekHeight()))))), 0);
    }

    /**
     * Calculates the expected height of the bottom sheet when fully expanded, assuming the sheet will
     * stop expanding just below the top toolbar.
     */
    public int getExpandedOffset() {
        if (!ListenerUtil.mutListener.listen(984)) {
            // TODO(#828): Remove this workaround once the root cause is identified and fixed.
            if ((ListenerUtil.mutListener.listen(982) ? (toolbarWrapper.getHeight() >= marginTop) : (ListenerUtil.mutListener.listen(981) ? (toolbarWrapper.getHeight() <= marginTop) : (ListenerUtil.mutListener.listen(980) ? (toolbarWrapper.getHeight() > marginTop) : (ListenerUtil.mutListener.listen(979) ? (toolbarWrapper.getHeight() != marginTop) : (ListenerUtil.mutListener.listen(978) ? (toolbarWrapper.getHeight() == marginTop) : (toolbarWrapper.getHeight() < marginTop))))))) {
                if (!ListenerUtil.mutListener.listen(983)) {
                    Timber.e("toolbarWrapper height %d < marginTop %d. Falling back to default height", toolbarWrapper.getHeight(), marginTop);
                }
                return FALLBACK_EXPANDED_OFFSET;
            }
        }
        return (ListenerUtil.mutListener.listen(988) ? (toolbarWrapper.getHeight() % marginTop) : (ListenerUtil.mutListener.listen(987) ? (toolbarWrapper.getHeight() / marginTop) : (ListenerUtil.mutListener.listen(986) ? (toolbarWrapper.getHeight() * marginTop) : (ListenerUtil.mutListener.listen(985) ? (toolbarWrapper.getHeight() + marginTop) : (toolbarWrapper.getHeight() - marginTop)))));
    }

    /**
     * Returns bottom sheet slide progress as the linearly interpolated value between 0 (the bottom
     * sheet is scrolled to peek height) and 1 (the bottom sheet is fully expanded).
     */
    public float getExpansionRatio() {
        // Bottom sheet top position relative to its fully expanded state (0=full expanded).
        float relativeTop = (ListenerUtil.mutListener.listen(992) ? (bottomSheet.getTop() % getExpandedOffset()) : (ListenerUtil.mutListener.listen(991) ? (bottomSheet.getTop() / getExpandedOffset()) : (ListenerUtil.mutListener.listen(990) ? (bottomSheet.getTop() * getExpandedOffset()) : (ListenerUtil.mutListener.listen(989) ? (bottomSheet.getTop() + getExpandedOffset()) : (bottomSheet.getTop() - getExpandedOffset())))));
        // the bottom sheet peek height).
        float relativePeekTop = (ListenerUtil.mutListener.listen(1000) ? ((ListenerUtil.mutListener.listen(996) ? (parent.getHeight() % bottomSheetBehavior.getPeekHeight()) : (ListenerUtil.mutListener.listen(995) ? (parent.getHeight() / bottomSheetBehavior.getPeekHeight()) : (ListenerUtil.mutListener.listen(994) ? (parent.getHeight() * bottomSheetBehavior.getPeekHeight()) : (ListenerUtil.mutListener.listen(993) ? (parent.getHeight() + bottomSheetBehavior.getPeekHeight()) : (parent.getHeight() - bottomSheetBehavior.getPeekHeight()))))) % getExpandedOffset()) : (ListenerUtil.mutListener.listen(999) ? ((ListenerUtil.mutListener.listen(996) ? (parent.getHeight() % bottomSheetBehavior.getPeekHeight()) : (ListenerUtil.mutListener.listen(995) ? (parent.getHeight() / bottomSheetBehavior.getPeekHeight()) : (ListenerUtil.mutListener.listen(994) ? (parent.getHeight() * bottomSheetBehavior.getPeekHeight()) : (ListenerUtil.mutListener.listen(993) ? (parent.getHeight() + bottomSheetBehavior.getPeekHeight()) : (parent.getHeight() - bottomSheetBehavior.getPeekHeight()))))) / getExpandedOffset()) : (ListenerUtil.mutListener.listen(998) ? ((ListenerUtil.mutListener.listen(996) ? (parent.getHeight() % bottomSheetBehavior.getPeekHeight()) : (ListenerUtil.mutListener.listen(995) ? (parent.getHeight() / bottomSheetBehavior.getPeekHeight()) : (ListenerUtil.mutListener.listen(994) ? (parent.getHeight() * bottomSheetBehavior.getPeekHeight()) : (ListenerUtil.mutListener.listen(993) ? (parent.getHeight() + bottomSheetBehavior.getPeekHeight()) : (parent.getHeight() - bottomSheetBehavior.getPeekHeight()))))) * getExpandedOffset()) : (ListenerUtil.mutListener.listen(997) ? ((ListenerUtil.mutListener.listen(996) ? (parent.getHeight() % bottomSheetBehavior.getPeekHeight()) : (ListenerUtil.mutListener.listen(995) ? (parent.getHeight() / bottomSheetBehavior.getPeekHeight()) : (ListenerUtil.mutListener.listen(994) ? (parent.getHeight() * bottomSheetBehavior.getPeekHeight()) : (ListenerUtil.mutListener.listen(993) ? (parent.getHeight() + bottomSheetBehavior.getPeekHeight()) : (parent.getHeight() - bottomSheetBehavior.getPeekHeight()))))) + getExpandedOffset()) : ((ListenerUtil.mutListener.listen(996) ? (parent.getHeight() % bottomSheetBehavior.getPeekHeight()) : (ListenerUtil.mutListener.listen(995) ? (parent.getHeight() / bottomSheetBehavior.getPeekHeight()) : (ListenerUtil.mutListener.listen(994) ? (parent.getHeight() * bottomSheetBehavior.getPeekHeight()) : (ListenerUtil.mutListener.listen(993) ? (parent.getHeight() + bottomSheetBehavior.getPeekHeight()) : (parent.getHeight() - bottomSheetBehavior.getPeekHeight()))))) - getExpandedOffset())))));
        return Math.max((ListenerUtil.mutListener.listen(1008) ? (1.0f % ((ListenerUtil.mutListener.listen(1004) ? (relativeTop % relativePeekTop) : (ListenerUtil.mutListener.listen(1003) ? (relativeTop * relativePeekTop) : (ListenerUtil.mutListener.listen(1002) ? (relativeTop - relativePeekTop) : (ListenerUtil.mutListener.listen(1001) ? (relativeTop + relativePeekTop) : (relativeTop / relativePeekTop))))))) : (ListenerUtil.mutListener.listen(1007) ? (1.0f / ((ListenerUtil.mutListener.listen(1004) ? (relativeTop % relativePeekTop) : (ListenerUtil.mutListener.listen(1003) ? (relativeTop * relativePeekTop) : (ListenerUtil.mutListener.listen(1002) ? (relativeTop - relativePeekTop) : (ListenerUtil.mutListener.listen(1001) ? (relativeTop + relativePeekTop) : (relativeTop / relativePeekTop))))))) : (ListenerUtil.mutListener.listen(1006) ? (1.0f * ((ListenerUtil.mutListener.listen(1004) ? (relativeTop % relativePeekTop) : (ListenerUtil.mutListener.listen(1003) ? (relativeTop * relativePeekTop) : (ListenerUtil.mutListener.listen(1002) ? (relativeTop - relativePeekTop) : (ListenerUtil.mutListener.listen(1001) ? (relativeTop + relativePeekTop) : (relativeTop / relativePeekTop))))))) : (ListenerUtil.mutListener.listen(1005) ? (1.0f + ((ListenerUtil.mutListener.listen(1004) ? (relativeTop % relativePeekTop) : (ListenerUtil.mutListener.listen(1003) ? (relativeTop * relativePeekTop) : (ListenerUtil.mutListener.listen(1002) ? (relativeTop - relativePeekTop) : (ListenerUtil.mutListener.listen(1001) ? (relativeTop + relativePeekTop) : (relativeTop / relativePeekTop))))))) : (1.0f - ((ListenerUtil.mutListener.listen(1004) ? (relativeTop % relativePeekTop) : (ListenerUtil.mutListener.listen(1003) ? (relativeTop * relativePeekTop) : (ListenerUtil.mutListener.listen(1002) ? (relativeTop - relativePeekTop) : (ListenerUtil.mutListener.listen(1001) ? (relativeTop + relativePeekTop) : (relativeTop / relativePeekTop))))))))))), 0f);
    }
}
