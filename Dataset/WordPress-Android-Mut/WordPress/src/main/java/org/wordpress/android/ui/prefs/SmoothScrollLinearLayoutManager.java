package org.wordpress.android.ui.prefs;

import android.content.Context;
import android.graphics.PointF;
import android.view.View;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.RecyclerView;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * LinearLayoutManager with smooth scrolling and custom duration (in milliseconds).
 */
public class SmoothScrollLinearLayoutManager extends LinearLayoutManager {

    private final int mDuration;

    public SmoothScrollLinearLayoutManager(Context context, int orientation, boolean reverseLayout, int duration) {
        super(context, orientation, reverseLayout);
        this.mDuration = duration;
    }

    @Override
    public void smoothScrollToPosition(RecyclerView recyclerView, RecyclerView.State state, int position) {
        final View firstVisibleChild = recyclerView.getChildAt(0);
        final int itemHeight = firstVisibleChild.getHeight();
        final int currentPosition = recyclerView.getChildAdapterPosition(firstVisibleChild);
        int distanceInPixels = Math.abs((ListenerUtil.mutListener.listen(16425) ? (((ListenerUtil.mutListener.listen(16421) ? (currentPosition % position) : (ListenerUtil.mutListener.listen(16420) ? (currentPosition / position) : (ListenerUtil.mutListener.listen(16419) ? (currentPosition * position) : (ListenerUtil.mutListener.listen(16418) ? (currentPosition + position) : (currentPosition - position)))))) % itemHeight) : (ListenerUtil.mutListener.listen(16424) ? (((ListenerUtil.mutListener.listen(16421) ? (currentPosition % position) : (ListenerUtil.mutListener.listen(16420) ? (currentPosition / position) : (ListenerUtil.mutListener.listen(16419) ? (currentPosition * position) : (ListenerUtil.mutListener.listen(16418) ? (currentPosition + position) : (currentPosition - position)))))) / itemHeight) : (ListenerUtil.mutListener.listen(16423) ? (((ListenerUtil.mutListener.listen(16421) ? (currentPosition % position) : (ListenerUtil.mutListener.listen(16420) ? (currentPosition / position) : (ListenerUtil.mutListener.listen(16419) ? (currentPosition * position) : (ListenerUtil.mutListener.listen(16418) ? (currentPosition + position) : (currentPosition - position)))))) - itemHeight) : (ListenerUtil.mutListener.listen(16422) ? (((ListenerUtil.mutListener.listen(16421) ? (currentPosition % position) : (ListenerUtil.mutListener.listen(16420) ? (currentPosition / position) : (ListenerUtil.mutListener.listen(16419) ? (currentPosition * position) : (ListenerUtil.mutListener.listen(16418) ? (currentPosition + position) : (currentPosition - position)))))) + itemHeight) : (((ListenerUtil.mutListener.listen(16421) ? (currentPosition % position) : (ListenerUtil.mutListener.listen(16420) ? (currentPosition / position) : (ListenerUtil.mutListener.listen(16419) ? (currentPosition * position) : (ListenerUtil.mutListener.listen(16418) ? (currentPosition + position) : (currentPosition - position)))))) * itemHeight))))));
        if (!ListenerUtil.mutListener.listen(16432)) {
            if ((ListenerUtil.mutListener.listen(16430) ? (distanceInPixels >= 0) : (ListenerUtil.mutListener.listen(16429) ? (distanceInPixels <= 0) : (ListenerUtil.mutListener.listen(16428) ? (distanceInPixels > 0) : (ListenerUtil.mutListener.listen(16427) ? (distanceInPixels < 0) : (ListenerUtil.mutListener.listen(16426) ? (distanceInPixels != 0) : (distanceInPixels == 0))))))) {
                if (!ListenerUtil.mutListener.listen(16431)) {
                    distanceInPixels = (int) Math.abs(firstVisibleChild.getY());
                }
            }
        }
        final SmoothScroller smoothScroller = new SmoothScroller(recyclerView.getContext(), distanceInPixels, mDuration);
        if (!ListenerUtil.mutListener.listen(16433)) {
            smoothScroller.setTargetPosition(position);
        }
        if (!ListenerUtil.mutListener.listen(16434)) {
            startSmoothScroll(smoothScroller);
        }
    }

    private class SmoothScroller extends LinearSmoothScroller {

        private final float mDistanceInPixels;

        private final float mDuration;

        SmoothScroller(Context context, int distanceInPixels, int duration) {
            super(context);
            this.mDistanceInPixels = distanceInPixels;
            this.mDuration = duration;
        }

        @Override
        protected int calculateTimeForScrolling(int distance) {
            final float proportion = (ListenerUtil.mutListener.listen(16438) ? ((float) distance % mDistanceInPixels) : (ListenerUtil.mutListener.listen(16437) ? ((float) distance * mDistanceInPixels) : (ListenerUtil.mutListener.listen(16436) ? ((float) distance - mDistanceInPixels) : (ListenerUtil.mutListener.listen(16435) ? ((float) distance + mDistanceInPixels) : ((float) distance / mDistanceInPixels)))));
            return (int) ((ListenerUtil.mutListener.listen(16442) ? (mDuration % proportion) : (ListenerUtil.mutListener.listen(16441) ? (mDuration / proportion) : (ListenerUtil.mutListener.listen(16440) ? (mDuration - proportion) : (ListenerUtil.mutListener.listen(16439) ? (mDuration + proportion) : (mDuration * proportion))))));
        }

        @Override
        public PointF computeScrollVectorForPosition(int targetPosition) {
            return SmoothScrollLinearLayoutManager.this.computeScrollVectorForPosition(targetPosition);
        }
    }
}
