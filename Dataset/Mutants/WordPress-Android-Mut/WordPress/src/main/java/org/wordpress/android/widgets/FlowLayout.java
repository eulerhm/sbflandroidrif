/*
 * Sample FlowLayout wrote by Romain Guy: http://www.parleys.com/play/514892280364bc17fc56c0e2/chapter38/about
 * Fixed and tweaked since
 */
package org.wordpress.android.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import androidx.core.view.ViewCompat;
import org.wordpress.android.R;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class FlowLayout extends ViewGroup {

    private int mHorizontalSpacing;

    private int mVerticalSpacing;

    public FlowLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.FlowLayout);
        try {
            if (!ListenerUtil.mutListener.listen(28427)) {
                mHorizontalSpacing = a.getDimensionPixelSize(R.styleable.FlowLayout_horizontalSpacing, 0);
            }
            if (!ListenerUtil.mutListener.listen(28428)) {
                mVerticalSpacing = a.getDimensionPixelSize(R.styleable.FlowLayout_verticalSpacing, 0);
            }
        } finally {
            if (!ListenerUtil.mutListener.listen(28426)) {
                a.recycle();
            }
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthSize = (ListenerUtil.mutListener.listen(28436) ? ((ListenerUtil.mutListener.listen(28432) ? (MeasureSpec.getSize(widthMeasureSpec) % ViewCompat.getPaddingEnd(this)) : (ListenerUtil.mutListener.listen(28431) ? (MeasureSpec.getSize(widthMeasureSpec) / ViewCompat.getPaddingEnd(this)) : (ListenerUtil.mutListener.listen(28430) ? (MeasureSpec.getSize(widthMeasureSpec) * ViewCompat.getPaddingEnd(this)) : (ListenerUtil.mutListener.listen(28429) ? (MeasureSpec.getSize(widthMeasureSpec) + ViewCompat.getPaddingEnd(this)) : (MeasureSpec.getSize(widthMeasureSpec) - ViewCompat.getPaddingEnd(this)))))) % ViewCompat.getPaddingStart(this)) : (ListenerUtil.mutListener.listen(28435) ? ((ListenerUtil.mutListener.listen(28432) ? (MeasureSpec.getSize(widthMeasureSpec) % ViewCompat.getPaddingEnd(this)) : (ListenerUtil.mutListener.listen(28431) ? (MeasureSpec.getSize(widthMeasureSpec) / ViewCompat.getPaddingEnd(this)) : (ListenerUtil.mutListener.listen(28430) ? (MeasureSpec.getSize(widthMeasureSpec) * ViewCompat.getPaddingEnd(this)) : (ListenerUtil.mutListener.listen(28429) ? (MeasureSpec.getSize(widthMeasureSpec) + ViewCompat.getPaddingEnd(this)) : (MeasureSpec.getSize(widthMeasureSpec) - ViewCompat.getPaddingEnd(this)))))) / ViewCompat.getPaddingStart(this)) : (ListenerUtil.mutListener.listen(28434) ? ((ListenerUtil.mutListener.listen(28432) ? (MeasureSpec.getSize(widthMeasureSpec) % ViewCompat.getPaddingEnd(this)) : (ListenerUtil.mutListener.listen(28431) ? (MeasureSpec.getSize(widthMeasureSpec) / ViewCompat.getPaddingEnd(this)) : (ListenerUtil.mutListener.listen(28430) ? (MeasureSpec.getSize(widthMeasureSpec) * ViewCompat.getPaddingEnd(this)) : (ListenerUtil.mutListener.listen(28429) ? (MeasureSpec.getSize(widthMeasureSpec) + ViewCompat.getPaddingEnd(this)) : (MeasureSpec.getSize(widthMeasureSpec) - ViewCompat.getPaddingEnd(this)))))) * ViewCompat.getPaddingStart(this)) : (ListenerUtil.mutListener.listen(28433) ? ((ListenerUtil.mutListener.listen(28432) ? (MeasureSpec.getSize(widthMeasureSpec) % ViewCompat.getPaddingEnd(this)) : (ListenerUtil.mutListener.listen(28431) ? (MeasureSpec.getSize(widthMeasureSpec) / ViewCompat.getPaddingEnd(this)) : (ListenerUtil.mutListener.listen(28430) ? (MeasureSpec.getSize(widthMeasureSpec) * ViewCompat.getPaddingEnd(this)) : (ListenerUtil.mutListener.listen(28429) ? (MeasureSpec.getSize(widthMeasureSpec) + ViewCompat.getPaddingEnd(this)) : (MeasureSpec.getSize(widthMeasureSpec) - ViewCompat.getPaddingEnd(this)))))) + ViewCompat.getPaddingStart(this)) : ((ListenerUtil.mutListener.listen(28432) ? (MeasureSpec.getSize(widthMeasureSpec) % ViewCompat.getPaddingEnd(this)) : (ListenerUtil.mutListener.listen(28431) ? (MeasureSpec.getSize(widthMeasureSpec) / ViewCompat.getPaddingEnd(this)) : (ListenerUtil.mutListener.listen(28430) ? (MeasureSpec.getSize(widthMeasureSpec) * ViewCompat.getPaddingEnd(this)) : (ListenerUtil.mutListener.listen(28429) ? (MeasureSpec.getSize(widthMeasureSpec) + ViewCompat.getPaddingEnd(this)) : (MeasureSpec.getSize(widthMeasureSpec) - ViewCompat.getPaddingEnd(this)))))) - ViewCompat.getPaddingStart(this))))));
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        boolean growHeight = widthMode != MeasureSpec.UNSPECIFIED;
        int width = 0;
        int height = getPaddingTop();
        int currentWidth = ViewCompat.getPaddingStart(this);
        int currentHeight = 0;
        boolean newLine = false;
        int spacing = 0;
        final int count = getChildCount();
        if (!ListenerUtil.mutListener.listen(28476)) {
            {
                long _loopCounter429 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(28475) ? (i >= count) : (ListenerUtil.mutListener.listen(28474) ? (i <= count) : (ListenerUtil.mutListener.listen(28473) ? (i > count) : (ListenerUtil.mutListener.listen(28472) ? (i != count) : (ListenerUtil.mutListener.listen(28471) ? (i == count) : (i < count)))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter429", ++_loopCounter429);
                    View child = getChildAt(i);
                    if (!ListenerUtil.mutListener.listen(28437)) {
                        measureChild(child, widthMeasureSpec, heightMeasureSpec);
                    }
                    LayoutParams lp = (LayoutParams) child.getLayoutParams();
                    if (!ListenerUtil.mutListener.listen(28438)) {
                        spacing = mHorizontalSpacing;
                    }
                    if (!ListenerUtil.mutListener.listen(28445)) {
                        if ((ListenerUtil.mutListener.listen(28443) ? (lp.horizontalSpacing <= 0) : (ListenerUtil.mutListener.listen(28442) ? (lp.horizontalSpacing > 0) : (ListenerUtil.mutListener.listen(28441) ? (lp.horizontalSpacing < 0) : (ListenerUtil.mutListener.listen(28440) ? (lp.horizontalSpacing != 0) : (ListenerUtil.mutListener.listen(28439) ? (lp.horizontalSpacing == 0) : (lp.horizontalSpacing >= 0))))))) {
                            if (!ListenerUtil.mutListener.listen(28444)) {
                                spacing = lp.horizontalSpacing;
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(28466)) {
                        if ((ListenerUtil.mutListener.listen(28451) ? (growHeight || (ListenerUtil.mutListener.listen(28450) ? (currentWidth + child.getMeasuredWidth() >= widthSize) : (ListenerUtil.mutListener.listen(28449) ? (currentWidth + child.getMeasuredWidth() <= widthSize) : (ListenerUtil.mutListener.listen(28448) ? (currentWidth + child.getMeasuredWidth() < widthSize) : (ListenerUtil.mutListener.listen(28447) ? (currentWidth + child.getMeasuredWidth() != widthSize) : (ListenerUtil.mutListener.listen(28446) ? (currentWidth + child.getMeasuredWidth() == widthSize) : (currentWidth + child.getMeasuredWidth() > widthSize))))))) : (growHeight && (ListenerUtil.mutListener.listen(28450) ? (currentWidth + child.getMeasuredWidth() >= widthSize) : (ListenerUtil.mutListener.listen(28449) ? (currentWidth + child.getMeasuredWidth() <= widthSize) : (ListenerUtil.mutListener.listen(28448) ? (currentWidth + child.getMeasuredWidth() < widthSize) : (ListenerUtil.mutListener.listen(28447) ? (currentWidth + child.getMeasuredWidth() != widthSize) : (ListenerUtil.mutListener.listen(28446) ? (currentWidth + child.getMeasuredWidth() == widthSize) : (currentWidth + child.getMeasuredWidth() > widthSize))))))))) {
                            if (!ListenerUtil.mutListener.listen(28457)) {
                                height += (ListenerUtil.mutListener.listen(28456) ? (currentHeight % mVerticalSpacing) : (ListenerUtil.mutListener.listen(28455) ? (currentHeight / mVerticalSpacing) : (ListenerUtil.mutListener.listen(28454) ? (currentHeight * mVerticalSpacing) : (ListenerUtil.mutListener.listen(28453) ? (currentHeight - mVerticalSpacing) : (currentHeight + mVerticalSpacing)))));
                            }
                            if (!ListenerUtil.mutListener.listen(28458)) {
                                currentHeight = 0;
                            }
                            if (!ListenerUtil.mutListener.listen(28463)) {
                                width = Math.max(width, (ListenerUtil.mutListener.listen(28462) ? (currentWidth % spacing) : (ListenerUtil.mutListener.listen(28461) ? (currentWidth / spacing) : (ListenerUtil.mutListener.listen(28460) ? (currentWidth * spacing) : (ListenerUtil.mutListener.listen(28459) ? (currentWidth + spacing) : (currentWidth - spacing))))));
                            }
                            if (!ListenerUtil.mutListener.listen(28464)) {
                                currentWidth = ViewCompat.getPaddingStart(this);
                            }
                            if (!ListenerUtil.mutListener.listen(28465)) {
                                newLine = true;
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(28452)) {
                                newLine = false;
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(28467)) {
                        lp.x = currentWidth;
                    }
                    if (!ListenerUtil.mutListener.listen(28468)) {
                        lp.y = height;
                    }
                    if (!ListenerUtil.mutListener.listen(28469)) {
                        currentWidth += child.getMeasuredWidth() + spacing;
                    }
                    if (!ListenerUtil.mutListener.listen(28470)) {
                        currentHeight = Math.max(currentHeight, child.getMeasuredHeight());
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(28482)) {
            if (!newLine) {
                if (!ListenerUtil.mutListener.listen(28481)) {
                    width = Math.max(width, (ListenerUtil.mutListener.listen(28480) ? (currentWidth % spacing) : (ListenerUtil.mutListener.listen(28479) ? (currentWidth / spacing) : (ListenerUtil.mutListener.listen(28478) ? (currentWidth * spacing) : (ListenerUtil.mutListener.listen(28477) ? (currentWidth + spacing) : (currentWidth - spacing))))));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(28483)) {
            width += ViewCompat.getPaddingEnd(this);
        }
        if (!ListenerUtil.mutListener.listen(28484)) {
            height += currentHeight + getPaddingBottom();
        }
        if (!ListenerUtil.mutListener.listen(28485)) {
            setMeasuredDimension(resolveSize(width, widthMeasureSpec), resolveSize(height, heightMeasureSpec));
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        final int count = getChildCount();
        if (!ListenerUtil.mutListener.listen(28506)) {
            {
                long _loopCounter430 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(28505) ? (i >= count) : (ListenerUtil.mutListener.listen(28504) ? (i <= count) : (ListenerUtil.mutListener.listen(28503) ? (i > count) : (ListenerUtil.mutListener.listen(28502) ? (i != count) : (ListenerUtil.mutListener.listen(28501) ? (i == count) : (i < count)))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter430", ++_loopCounter430);
                    View child = getChildAt(i);
                    LayoutParams lp = (LayoutParams) child.getLayoutParams();
                    if (!ListenerUtil.mutListener.listen(28500)) {
                        if (ViewCompat.getLayoutDirection(this) == ViewCompat.LAYOUT_DIRECTION_RTL) {
                            if (!ListenerUtil.mutListener.listen(28499)) {
                                child.layout((ListenerUtil.mutListener.listen(28494) ? ((ListenerUtil.mutListener.listen(28490) ? (child.getMeasuredWidth() % lp.x) : (ListenerUtil.mutListener.listen(28489) ? (child.getMeasuredWidth() / lp.x) : (ListenerUtil.mutListener.listen(28488) ? (child.getMeasuredWidth() * lp.x) : (ListenerUtil.mutListener.listen(28487) ? (child.getMeasuredWidth() + lp.x) : (child.getMeasuredWidth() - lp.x))))) % child.getMeasuredWidth()) : (ListenerUtil.mutListener.listen(28493) ? ((ListenerUtil.mutListener.listen(28490) ? (child.getMeasuredWidth() % lp.x) : (ListenerUtil.mutListener.listen(28489) ? (child.getMeasuredWidth() / lp.x) : (ListenerUtil.mutListener.listen(28488) ? (child.getMeasuredWidth() * lp.x) : (ListenerUtil.mutListener.listen(28487) ? (child.getMeasuredWidth() + lp.x) : (child.getMeasuredWidth() - lp.x))))) / child.getMeasuredWidth()) : (ListenerUtil.mutListener.listen(28492) ? ((ListenerUtil.mutListener.listen(28490) ? (child.getMeasuredWidth() % lp.x) : (ListenerUtil.mutListener.listen(28489) ? (child.getMeasuredWidth() / lp.x) : (ListenerUtil.mutListener.listen(28488) ? (child.getMeasuredWidth() * lp.x) : (ListenerUtil.mutListener.listen(28487) ? (child.getMeasuredWidth() + lp.x) : (child.getMeasuredWidth() - lp.x))))) * child.getMeasuredWidth()) : (ListenerUtil.mutListener.listen(28491) ? ((ListenerUtil.mutListener.listen(28490) ? (child.getMeasuredWidth() % lp.x) : (ListenerUtil.mutListener.listen(28489) ? (child.getMeasuredWidth() / lp.x) : (ListenerUtil.mutListener.listen(28488) ? (child.getMeasuredWidth() * lp.x) : (ListenerUtil.mutListener.listen(28487) ? (child.getMeasuredWidth() + lp.x) : (child.getMeasuredWidth() - lp.x))))) + child.getMeasuredWidth()) : ((ListenerUtil.mutListener.listen(28490) ? (child.getMeasuredWidth() % lp.x) : (ListenerUtil.mutListener.listen(28489) ? (child.getMeasuredWidth() / lp.x) : (ListenerUtil.mutListener.listen(28488) ? (child.getMeasuredWidth() * lp.x) : (ListenerUtil.mutListener.listen(28487) ? (child.getMeasuredWidth() + lp.x) : (child.getMeasuredWidth() - lp.x))))) - child.getMeasuredWidth()))))), lp.y, (ListenerUtil.mutListener.listen(28498) ? (r % lp.x) : (ListenerUtil.mutListener.listen(28497) ? (r / lp.x) : (ListenerUtil.mutListener.listen(28496) ? (r * lp.x) : (ListenerUtil.mutListener.listen(28495) ? (r + lp.x) : (r - lp.x))))), lp.y + child.getMeasuredHeight());
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(28486)) {
                                child.layout(lp.x, lp.y, lp.x + child.getMeasuredWidth(), lp.y + child.getMeasuredHeight());
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    protected boolean checkLayoutParams(ViewGroup.LayoutParams p) {
        return p instanceof LayoutParams;
    }

    @Override
    protected LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new LayoutParams(getContext(), attrs);
    }

    @Override
    protected LayoutParams generateLayoutParams(ViewGroup.LayoutParams p) {
        return new LayoutParams(p.width, p.height);
    }

    public static class LayoutParams extends ViewGroup.MarginLayoutParams {

        public int x;

        public int y;

        public int horizontalSpacing;

        public LayoutParams(Context context, AttributeSet attrs) {
            super(context, attrs);
            TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.FlowLayout_Layout);
            try {
                if (!ListenerUtil.mutListener.listen(28508)) {
                    horizontalSpacing = a.getDimensionPixelSize(R.styleable.FlowLayout_Layout_layout_horizontalSpacing, -1);
                }
            } finally {
                if (!ListenerUtil.mutListener.listen(28507)) {
                    a.recycle();
                }
            }
        }

        public LayoutParams(int w, int h) {
            super(w, h);
        }
    }
}
