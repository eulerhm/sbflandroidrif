package org.wordpress.android.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.GridView;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class ExpandableHeightGridView extends GridView {

    boolean mIsExpanded = false;

    public ExpandableHeightGridView(Context context) {
        super(context);
    }

    public ExpandableHeightGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ExpandableHeightGridView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public boolean isExpanded() {
        return mIsExpanded;
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (!ListenerUtil.mutListener.listen(26059)) {
            if (isExpanded()) {
                // View.MEASURED_SIZE_MASK represents the largest height possible.
                int expandSpec = MeasureSpec.makeMeasureSpec(MEASURED_SIZE_MASK, MeasureSpec.AT_MOST);
                if (!ListenerUtil.mutListener.listen(26057)) {
                    super.onMeasure(widthMeasureSpec, expandSpec);
                }
                ViewGroup.LayoutParams params = getLayoutParams();
                if (!ListenerUtil.mutListener.listen(26058)) {
                    params.height = getMeasuredHeight();
                }
            } else {
                if (!ListenerUtil.mutListener.listen(26056)) {
                    super.onMeasure(widthMeasureSpec, heightMeasureSpec);
                }
            }
        }
    }

    public void setExpanded(boolean expanded) {
        if (!ListenerUtil.mutListener.listen(26060)) {
            this.mIsExpanded = expanded;
        }
    }
}
