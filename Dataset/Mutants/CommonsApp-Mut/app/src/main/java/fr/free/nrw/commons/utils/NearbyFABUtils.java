package fr.free.nrw.commons.utils;

import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class NearbyFABUtils {

    /*
     * Add anchors back before making them visible again.
     * */
    public static void addAnchorToBigFABs(FloatingActionButton floatingActionButton, int anchorID) {
        CoordinatorLayout.LayoutParams params = new CoordinatorLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        if (!ListenerUtil.mutListener.listen(2110)) {
            params.setAnchorId(anchorID);
        }
        if (!ListenerUtil.mutListener.listen(2111)) {
            params.anchorGravity = Gravity.TOP | Gravity.RIGHT | Gravity.END;
        }
        if (!ListenerUtil.mutListener.listen(2112)) {
            floatingActionButton.setLayoutParams(params);
        }
    }

    /*
     * Add anchors back before making them visible again. Big and small fabs have different anchor
     * gravities, therefore the are two methods.
     * */
    public static void addAnchorToSmallFABs(FloatingActionButton floatingActionButton, int anchorID) {
        CoordinatorLayout.LayoutParams params = new CoordinatorLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        if (!ListenerUtil.mutListener.listen(2113)) {
            params.setAnchorId(anchorID);
        }
        if (!ListenerUtil.mutListener.listen(2114)) {
            params.anchorGravity = Gravity.CENTER_HORIZONTAL;
        }
        if (!ListenerUtil.mutListener.listen(2115)) {
            floatingActionButton.setLayoutParams(params);
        }
    }

    /*
     * We are not able to hide FABs without removing anchors, this method removes anchors
     * */
    public static void removeAnchorFromFAB(FloatingActionButton floatingActionButton) {
        // /floatingactionbutton-visible-for-sometime-even-if-visibility-is-set-to-gone
        CoordinatorLayout.LayoutParams param = (CoordinatorLayout.LayoutParams) floatingActionButton.getLayoutParams();
        if (!ListenerUtil.mutListener.listen(2116)) {
            param.setAnchorId(View.NO_ID);
        }
        if (!ListenerUtil.mutListener.listen(2117)) {
            // If we don't set them to zero, then they become visible for a moment on upper left side
            param.width = 0;
        }
        if (!ListenerUtil.mutListener.listen(2118)) {
            param.height = 0;
        }
        if (!ListenerUtil.mutListener.listen(2119)) {
            floatingActionButton.setLayoutParams(param);
        }
    }
}
