package org.wordpress.android.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import org.wordpress.android.R;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class WPLinearLayoutSizeBound extends LinearLayout {

    private final int mMaxWidth;

    private final int mMaxHeight;

    public WPLinearLayoutSizeBound(Context context) {
        super(context);
        mMaxWidth = 0;
        mMaxHeight = 0;
    }

    public WPLinearLayoutSizeBound(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.WPLinearLayoutSizeBound);
        mMaxWidth = a.getDimensionPixelSize(R.styleable.WPLinearLayoutSizeBound_maxWidth, Integer.MAX_VALUE);
        mMaxHeight = a.getDimensionPixelSize(R.styleable.WPLinearLayoutSizeBound_maxHeight, Integer.MAX_VALUE);
        if (!ListenerUtil.mutListener.listen(29014)) {
            a.recycle();
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int measuredWidth = MeasureSpec.getSize(widthMeasureSpec);
        if (!ListenerUtil.mutListener.listen(29027)) {
            if ((ListenerUtil.mutListener.listen(29025) ? ((ListenerUtil.mutListener.listen(29019) ? (mMaxWidth >= 0) : (ListenerUtil.mutListener.listen(29018) ? (mMaxWidth <= 0) : (ListenerUtil.mutListener.listen(29017) ? (mMaxWidth < 0) : (ListenerUtil.mutListener.listen(29016) ? (mMaxWidth != 0) : (ListenerUtil.mutListener.listen(29015) ? (mMaxWidth == 0) : (mMaxWidth > 0)))))) || (ListenerUtil.mutListener.listen(29024) ? (mMaxWidth >= measuredWidth) : (ListenerUtil.mutListener.listen(29023) ? (mMaxWidth <= measuredWidth) : (ListenerUtil.mutListener.listen(29022) ? (mMaxWidth > measuredWidth) : (ListenerUtil.mutListener.listen(29021) ? (mMaxWidth != measuredWidth) : (ListenerUtil.mutListener.listen(29020) ? (mMaxWidth == measuredWidth) : (mMaxWidth < measuredWidth))))))) : ((ListenerUtil.mutListener.listen(29019) ? (mMaxWidth >= 0) : (ListenerUtil.mutListener.listen(29018) ? (mMaxWidth <= 0) : (ListenerUtil.mutListener.listen(29017) ? (mMaxWidth < 0) : (ListenerUtil.mutListener.listen(29016) ? (mMaxWidth != 0) : (ListenerUtil.mutListener.listen(29015) ? (mMaxWidth == 0) : (mMaxWidth > 0)))))) && (ListenerUtil.mutListener.listen(29024) ? (mMaxWidth >= measuredWidth) : (ListenerUtil.mutListener.listen(29023) ? (mMaxWidth <= measuredWidth) : (ListenerUtil.mutListener.listen(29022) ? (mMaxWidth > measuredWidth) : (ListenerUtil.mutListener.listen(29021) ? (mMaxWidth != measuredWidth) : (ListenerUtil.mutListener.listen(29020) ? (mMaxWidth == measuredWidth) : (mMaxWidth < measuredWidth))))))))) {
                int measureMode = MeasureSpec.getMode(widthMeasureSpec);
                if (!ListenerUtil.mutListener.listen(29026)) {
                    widthMeasureSpec = MeasureSpec.makeMeasureSpec(mMaxWidth, measureMode);
                }
            }
        }
        int measuredHeight = MeasureSpec.getSize(heightMeasureSpec);
        if (!ListenerUtil.mutListener.listen(29040)) {
            if ((ListenerUtil.mutListener.listen(29038) ? ((ListenerUtil.mutListener.listen(29032) ? (mMaxHeight >= 0) : (ListenerUtil.mutListener.listen(29031) ? (mMaxHeight <= 0) : (ListenerUtil.mutListener.listen(29030) ? (mMaxHeight < 0) : (ListenerUtil.mutListener.listen(29029) ? (mMaxHeight != 0) : (ListenerUtil.mutListener.listen(29028) ? (mMaxHeight == 0) : (mMaxHeight > 0)))))) || (ListenerUtil.mutListener.listen(29037) ? (mMaxHeight >= measuredHeight) : (ListenerUtil.mutListener.listen(29036) ? (mMaxHeight <= measuredHeight) : (ListenerUtil.mutListener.listen(29035) ? (mMaxHeight > measuredHeight) : (ListenerUtil.mutListener.listen(29034) ? (mMaxHeight != measuredHeight) : (ListenerUtil.mutListener.listen(29033) ? (mMaxHeight == measuredHeight) : (mMaxHeight < measuredHeight))))))) : ((ListenerUtil.mutListener.listen(29032) ? (mMaxHeight >= 0) : (ListenerUtil.mutListener.listen(29031) ? (mMaxHeight <= 0) : (ListenerUtil.mutListener.listen(29030) ? (mMaxHeight < 0) : (ListenerUtil.mutListener.listen(29029) ? (mMaxHeight != 0) : (ListenerUtil.mutListener.listen(29028) ? (mMaxHeight == 0) : (mMaxHeight > 0)))))) && (ListenerUtil.mutListener.listen(29037) ? (mMaxHeight >= measuredHeight) : (ListenerUtil.mutListener.listen(29036) ? (mMaxHeight <= measuredHeight) : (ListenerUtil.mutListener.listen(29035) ? (mMaxHeight > measuredHeight) : (ListenerUtil.mutListener.listen(29034) ? (mMaxHeight != measuredHeight) : (ListenerUtil.mutListener.listen(29033) ? (mMaxHeight == measuredHeight) : (mMaxHeight < measuredHeight))))))))) {
                int measureMode = MeasureSpec.getMode(heightMeasureSpec);
                if (!ListenerUtil.mutListener.listen(29039)) {
                    heightMeasureSpec = MeasureSpec.makeMeasureSpec(mMaxHeight, measureMode);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(29041)) {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }
}
