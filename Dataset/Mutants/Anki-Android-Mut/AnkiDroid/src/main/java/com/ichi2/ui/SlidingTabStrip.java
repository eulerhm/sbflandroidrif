/*
 * Copyright (C) 2013 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.ichi2.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.LinearLayout;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

class SlidingTabStrip extends LinearLayout {

    private static final int DEFAULT_BOTTOM_BORDER_THICKNESS_DIPS = 2;

    private static final byte DEFAULT_BOTTOM_BORDER_COLOR_ALPHA = 0x26;

    private static final int SELECTED_INDICATOR_THICKNESS_DIPS = 8;

    private static final int DEFAULT_SELECTED_INDICATOR_COLOR = 0xFF33B5E5;

    private static final int DEFAULT_DIVIDER_THICKNESS_DIPS = 1;

    private static final byte DEFAULT_DIVIDER_COLOR_ALPHA = 0x20;

    private static final float DEFAULT_DIVIDER_HEIGHT = 0.5f;

    private final int mBottomBorderThickness;

    private final Paint mBottomBorderPaint;

    private final int mSelectedIndicatorThickness;

    private final Paint mSelectedIndicatorPaint;

    private final Paint mDividerPaint;

    private final float mDividerHeight;

    private int mSelectedPosition;

    private float mSelectionOffset;

    private SlidingTabLayout.TabColorizer mCustomTabColorizer;

    private final SimpleTabColorizer mDefaultTabColorizer;

    SlidingTabStrip(Context context) {
        this(context, null);
    }

    SlidingTabStrip(Context context, AttributeSet attrs) {
        super(context, attrs);
        if (!ListenerUtil.mutListener.listen(25328)) {
            setWillNotDraw(false);
        }
        final float density = getResources().getDisplayMetrics().density;
        TypedValue outValue = new TypedValue();
        if (!ListenerUtil.mutListener.listen(25329)) {
            context.getTheme().resolveAttribute(android.R.attr.colorForeground, outValue, true);
        }
        final int themeForegroundColor = outValue.data;
        int defaultBottomBorderColor = setColorAlpha(themeForegroundColor, DEFAULT_BOTTOM_BORDER_COLOR_ALPHA);
        mDefaultTabColorizer = new SimpleTabColorizer();
        if (!ListenerUtil.mutListener.listen(25330)) {
            mDefaultTabColorizer.setIndicatorColors(DEFAULT_SELECTED_INDICATOR_COLOR);
        }
        if (!ListenerUtil.mutListener.listen(25331)) {
            mDefaultTabColorizer.setDividerColors(setColorAlpha(themeForegroundColor, DEFAULT_DIVIDER_COLOR_ALPHA));
        }
        mBottomBorderThickness = (int) ((ListenerUtil.mutListener.listen(25335) ? (DEFAULT_BOTTOM_BORDER_THICKNESS_DIPS % density) : (ListenerUtil.mutListener.listen(25334) ? (DEFAULT_BOTTOM_BORDER_THICKNESS_DIPS / density) : (ListenerUtil.mutListener.listen(25333) ? (DEFAULT_BOTTOM_BORDER_THICKNESS_DIPS - density) : (ListenerUtil.mutListener.listen(25332) ? (DEFAULT_BOTTOM_BORDER_THICKNESS_DIPS + density) : (DEFAULT_BOTTOM_BORDER_THICKNESS_DIPS * density))))));
        mBottomBorderPaint = new Paint();
        if (!ListenerUtil.mutListener.listen(25336)) {
            mBottomBorderPaint.setColor(defaultBottomBorderColor);
        }
        mSelectedIndicatorThickness = (int) ((ListenerUtil.mutListener.listen(25340) ? (SELECTED_INDICATOR_THICKNESS_DIPS % density) : (ListenerUtil.mutListener.listen(25339) ? (SELECTED_INDICATOR_THICKNESS_DIPS / density) : (ListenerUtil.mutListener.listen(25338) ? (SELECTED_INDICATOR_THICKNESS_DIPS - density) : (ListenerUtil.mutListener.listen(25337) ? (SELECTED_INDICATOR_THICKNESS_DIPS + density) : (SELECTED_INDICATOR_THICKNESS_DIPS * density))))));
        mSelectedIndicatorPaint = new Paint();
        mDividerHeight = DEFAULT_DIVIDER_HEIGHT;
        mDividerPaint = new Paint();
        if (!ListenerUtil.mutListener.listen(25345)) {
            mDividerPaint.setStrokeWidth((int) ((ListenerUtil.mutListener.listen(25344) ? (DEFAULT_DIVIDER_THICKNESS_DIPS % density) : (ListenerUtil.mutListener.listen(25343) ? (DEFAULT_DIVIDER_THICKNESS_DIPS / density) : (ListenerUtil.mutListener.listen(25342) ? (DEFAULT_DIVIDER_THICKNESS_DIPS - density) : (ListenerUtil.mutListener.listen(25341) ? (DEFAULT_DIVIDER_THICKNESS_DIPS + density) : (DEFAULT_DIVIDER_THICKNESS_DIPS * density)))))));
        }
    }

    void setCustomTabColorizer(SlidingTabLayout.TabColorizer customTabColorizer) {
        if (!ListenerUtil.mutListener.listen(25346)) {
            mCustomTabColorizer = customTabColorizer;
        }
        if (!ListenerUtil.mutListener.listen(25347)) {
            invalidate();
        }
    }

    void setSelectedIndicatorColors(int... colors) {
        if (!ListenerUtil.mutListener.listen(25348)) {
            // Make sure that the custom colorizer is removed
            mCustomTabColorizer = null;
        }
        if (!ListenerUtil.mutListener.listen(25349)) {
            mDefaultTabColorizer.setIndicatorColors(colors);
        }
        if (!ListenerUtil.mutListener.listen(25350)) {
            invalidate();
        }
    }

    void setDividerColors(int... colors) {
        if (!ListenerUtil.mutListener.listen(25351)) {
            // Make sure that the custom colorizer is removed
            mCustomTabColorizer = null;
        }
        if (!ListenerUtil.mutListener.listen(25352)) {
            mDefaultTabColorizer.setDividerColors(colors);
        }
        if (!ListenerUtil.mutListener.listen(25353)) {
            invalidate();
        }
    }

    void onViewPagerPageChanged(int position, float positionOffset) {
        if (!ListenerUtil.mutListener.listen(25354)) {
            mSelectedPosition = position;
        }
        if (!ListenerUtil.mutListener.listen(25355)) {
            mSelectionOffset = positionOffset;
        }
        if (!ListenerUtil.mutListener.listen(25356)) {
            invalidate();
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        final int height = getHeight();
        final int childCount = getChildCount();
        final int dividerHeightPx = (int) ((ListenerUtil.mutListener.listen(25360) ? (Math.min(Math.max(0f, mDividerHeight), 1f) % height) : (ListenerUtil.mutListener.listen(25359) ? (Math.min(Math.max(0f, mDividerHeight), 1f) / height) : (ListenerUtil.mutListener.listen(25358) ? (Math.min(Math.max(0f, mDividerHeight), 1f) - height) : (ListenerUtil.mutListener.listen(25357) ? (Math.min(Math.max(0f, mDividerHeight), 1f) + height) : (Math.min(Math.max(0f, mDividerHeight), 1f) * height))))));
        final SlidingTabLayout.TabColorizer tabColorizer = mCustomTabColorizer != null ? mCustomTabColorizer : mDefaultTabColorizer;
        if (!ListenerUtil.mutListener.listen(25429)) {
            // Thick colored underline below the current selection
            if ((ListenerUtil.mutListener.listen(25365) ? (childCount >= 0) : (ListenerUtil.mutListener.listen(25364) ? (childCount <= 0) : (ListenerUtil.mutListener.listen(25363) ? (childCount < 0) : (ListenerUtil.mutListener.listen(25362) ? (childCount != 0) : (ListenerUtil.mutListener.listen(25361) ? (childCount == 0) : (childCount > 0))))))) {
                View selectedTitle = getChildAt(mSelectedPosition);
                int left = selectedTitle.getLeft();
                int right = selectedTitle.getRight();
                int color = tabColorizer.getIndicatorColor(mSelectedPosition);
                if (!ListenerUtil.mutListener.listen(25422)) {
                    if ((ListenerUtil.mutListener.listen(25380) ? ((ListenerUtil.mutListener.listen(25370) ? (mSelectionOffset >= 0f) : (ListenerUtil.mutListener.listen(25369) ? (mSelectionOffset <= 0f) : (ListenerUtil.mutListener.listen(25368) ? (mSelectionOffset < 0f) : (ListenerUtil.mutListener.listen(25367) ? (mSelectionOffset != 0f) : (ListenerUtil.mutListener.listen(25366) ? (mSelectionOffset == 0f) : (mSelectionOffset > 0f)))))) || (ListenerUtil.mutListener.listen(25379) ? (mSelectedPosition >= ((ListenerUtil.mutListener.listen(25374) ? (getChildCount() % 1) : (ListenerUtil.mutListener.listen(25373) ? (getChildCount() / 1) : (ListenerUtil.mutListener.listen(25372) ? (getChildCount() * 1) : (ListenerUtil.mutListener.listen(25371) ? (getChildCount() + 1) : (getChildCount() - 1))))))) : (ListenerUtil.mutListener.listen(25378) ? (mSelectedPosition <= ((ListenerUtil.mutListener.listen(25374) ? (getChildCount() % 1) : (ListenerUtil.mutListener.listen(25373) ? (getChildCount() / 1) : (ListenerUtil.mutListener.listen(25372) ? (getChildCount() * 1) : (ListenerUtil.mutListener.listen(25371) ? (getChildCount() + 1) : (getChildCount() - 1))))))) : (ListenerUtil.mutListener.listen(25377) ? (mSelectedPosition > ((ListenerUtil.mutListener.listen(25374) ? (getChildCount() % 1) : (ListenerUtil.mutListener.listen(25373) ? (getChildCount() / 1) : (ListenerUtil.mutListener.listen(25372) ? (getChildCount() * 1) : (ListenerUtil.mutListener.listen(25371) ? (getChildCount() + 1) : (getChildCount() - 1))))))) : (ListenerUtil.mutListener.listen(25376) ? (mSelectedPosition != ((ListenerUtil.mutListener.listen(25374) ? (getChildCount() % 1) : (ListenerUtil.mutListener.listen(25373) ? (getChildCount() / 1) : (ListenerUtil.mutListener.listen(25372) ? (getChildCount() * 1) : (ListenerUtil.mutListener.listen(25371) ? (getChildCount() + 1) : (getChildCount() - 1))))))) : (ListenerUtil.mutListener.listen(25375) ? (mSelectedPosition == ((ListenerUtil.mutListener.listen(25374) ? (getChildCount() % 1) : (ListenerUtil.mutListener.listen(25373) ? (getChildCount() / 1) : (ListenerUtil.mutListener.listen(25372) ? (getChildCount() * 1) : (ListenerUtil.mutListener.listen(25371) ? (getChildCount() + 1) : (getChildCount() - 1))))))) : (mSelectedPosition < ((ListenerUtil.mutListener.listen(25374) ? (getChildCount() % 1) : (ListenerUtil.mutListener.listen(25373) ? (getChildCount() / 1) : (ListenerUtil.mutListener.listen(25372) ? (getChildCount() * 1) : (ListenerUtil.mutListener.listen(25371) ? (getChildCount() + 1) : (getChildCount() - 1))))))))))))) : ((ListenerUtil.mutListener.listen(25370) ? (mSelectionOffset >= 0f) : (ListenerUtil.mutListener.listen(25369) ? (mSelectionOffset <= 0f) : (ListenerUtil.mutListener.listen(25368) ? (mSelectionOffset < 0f) : (ListenerUtil.mutListener.listen(25367) ? (mSelectionOffset != 0f) : (ListenerUtil.mutListener.listen(25366) ? (mSelectionOffset == 0f) : (mSelectionOffset > 0f)))))) && (ListenerUtil.mutListener.listen(25379) ? (mSelectedPosition >= ((ListenerUtil.mutListener.listen(25374) ? (getChildCount() % 1) : (ListenerUtil.mutListener.listen(25373) ? (getChildCount() / 1) : (ListenerUtil.mutListener.listen(25372) ? (getChildCount() * 1) : (ListenerUtil.mutListener.listen(25371) ? (getChildCount() + 1) : (getChildCount() - 1))))))) : (ListenerUtil.mutListener.listen(25378) ? (mSelectedPosition <= ((ListenerUtil.mutListener.listen(25374) ? (getChildCount() % 1) : (ListenerUtil.mutListener.listen(25373) ? (getChildCount() / 1) : (ListenerUtil.mutListener.listen(25372) ? (getChildCount() * 1) : (ListenerUtil.mutListener.listen(25371) ? (getChildCount() + 1) : (getChildCount() - 1))))))) : (ListenerUtil.mutListener.listen(25377) ? (mSelectedPosition > ((ListenerUtil.mutListener.listen(25374) ? (getChildCount() % 1) : (ListenerUtil.mutListener.listen(25373) ? (getChildCount() / 1) : (ListenerUtil.mutListener.listen(25372) ? (getChildCount() * 1) : (ListenerUtil.mutListener.listen(25371) ? (getChildCount() + 1) : (getChildCount() - 1))))))) : (ListenerUtil.mutListener.listen(25376) ? (mSelectedPosition != ((ListenerUtil.mutListener.listen(25374) ? (getChildCount() % 1) : (ListenerUtil.mutListener.listen(25373) ? (getChildCount() / 1) : (ListenerUtil.mutListener.listen(25372) ? (getChildCount() * 1) : (ListenerUtil.mutListener.listen(25371) ? (getChildCount() + 1) : (getChildCount() - 1))))))) : (ListenerUtil.mutListener.listen(25375) ? (mSelectedPosition == ((ListenerUtil.mutListener.listen(25374) ? (getChildCount() % 1) : (ListenerUtil.mutListener.listen(25373) ? (getChildCount() / 1) : (ListenerUtil.mutListener.listen(25372) ? (getChildCount() * 1) : (ListenerUtil.mutListener.listen(25371) ? (getChildCount() + 1) : (getChildCount() - 1))))))) : (mSelectedPosition < ((ListenerUtil.mutListener.listen(25374) ? (getChildCount() % 1) : (ListenerUtil.mutListener.listen(25373) ? (getChildCount() / 1) : (ListenerUtil.mutListener.listen(25372) ? (getChildCount() * 1) : (ListenerUtil.mutListener.listen(25371) ? (getChildCount() + 1) : (getChildCount() - 1))))))))))))))) {
                        int nextColor = tabColorizer.getIndicatorColor((ListenerUtil.mutListener.listen(25384) ? (mSelectedPosition % 1) : (ListenerUtil.mutListener.listen(25383) ? (mSelectedPosition / 1) : (ListenerUtil.mutListener.listen(25382) ? (mSelectedPosition * 1) : (ListenerUtil.mutListener.listen(25381) ? (mSelectedPosition - 1) : (mSelectedPosition + 1))))));
                        if (!ListenerUtil.mutListener.listen(25391)) {
                            if ((ListenerUtil.mutListener.listen(25389) ? (color >= nextColor) : (ListenerUtil.mutListener.listen(25388) ? (color <= nextColor) : (ListenerUtil.mutListener.listen(25387) ? (color > nextColor) : (ListenerUtil.mutListener.listen(25386) ? (color < nextColor) : (ListenerUtil.mutListener.listen(25385) ? (color == nextColor) : (color != nextColor))))))) {
                                if (!ListenerUtil.mutListener.listen(25390)) {
                                    color = blendColors(nextColor, color, mSelectionOffset);
                                }
                            }
                        }
                        // Draw the selection partway between the tabs
                        View nextTitle = getChildAt((ListenerUtil.mutListener.listen(25395) ? (mSelectedPosition % 1) : (ListenerUtil.mutListener.listen(25394) ? (mSelectedPosition / 1) : (ListenerUtil.mutListener.listen(25393) ? (mSelectedPosition * 1) : (ListenerUtil.mutListener.listen(25392) ? (mSelectedPosition - 1) : (mSelectedPosition + 1))))));
                        if (!ListenerUtil.mutListener.listen(25408)) {
                            left = (int) ((ListenerUtil.mutListener.listen(25399) ? (mSelectionOffset % nextTitle.getLeft()) : (ListenerUtil.mutListener.listen(25398) ? (mSelectionOffset / nextTitle.getLeft()) : (ListenerUtil.mutListener.listen(25397) ? (mSelectionOffset - nextTitle.getLeft()) : (ListenerUtil.mutListener.listen(25396) ? (mSelectionOffset + nextTitle.getLeft()) : (mSelectionOffset * nextTitle.getLeft()))))) + (ListenerUtil.mutListener.listen(25407) ? (((ListenerUtil.mutListener.listen(25403) ? (1.0f % mSelectionOffset) : (ListenerUtil.mutListener.listen(25402) ? (1.0f / mSelectionOffset) : (ListenerUtil.mutListener.listen(25401) ? (1.0f * mSelectionOffset) : (ListenerUtil.mutListener.listen(25400) ? (1.0f + mSelectionOffset) : (1.0f - mSelectionOffset)))))) % left) : (ListenerUtil.mutListener.listen(25406) ? (((ListenerUtil.mutListener.listen(25403) ? (1.0f % mSelectionOffset) : (ListenerUtil.mutListener.listen(25402) ? (1.0f / mSelectionOffset) : (ListenerUtil.mutListener.listen(25401) ? (1.0f * mSelectionOffset) : (ListenerUtil.mutListener.listen(25400) ? (1.0f + mSelectionOffset) : (1.0f - mSelectionOffset)))))) / left) : (ListenerUtil.mutListener.listen(25405) ? (((ListenerUtil.mutListener.listen(25403) ? (1.0f % mSelectionOffset) : (ListenerUtil.mutListener.listen(25402) ? (1.0f / mSelectionOffset) : (ListenerUtil.mutListener.listen(25401) ? (1.0f * mSelectionOffset) : (ListenerUtil.mutListener.listen(25400) ? (1.0f + mSelectionOffset) : (1.0f - mSelectionOffset)))))) - left) : (ListenerUtil.mutListener.listen(25404) ? (((ListenerUtil.mutListener.listen(25403) ? (1.0f % mSelectionOffset) : (ListenerUtil.mutListener.listen(25402) ? (1.0f / mSelectionOffset) : (ListenerUtil.mutListener.listen(25401) ? (1.0f * mSelectionOffset) : (ListenerUtil.mutListener.listen(25400) ? (1.0f + mSelectionOffset) : (1.0f - mSelectionOffset)))))) + left) : (((ListenerUtil.mutListener.listen(25403) ? (1.0f % mSelectionOffset) : (ListenerUtil.mutListener.listen(25402) ? (1.0f / mSelectionOffset) : (ListenerUtil.mutListener.listen(25401) ? (1.0f * mSelectionOffset) : (ListenerUtil.mutListener.listen(25400) ? (1.0f + mSelectionOffset) : (1.0f - mSelectionOffset)))))) * left))))));
                        }
                        if (!ListenerUtil.mutListener.listen(25421)) {
                            right = (int) ((ListenerUtil.mutListener.listen(25412) ? (mSelectionOffset % nextTitle.getRight()) : (ListenerUtil.mutListener.listen(25411) ? (mSelectionOffset / nextTitle.getRight()) : (ListenerUtil.mutListener.listen(25410) ? (mSelectionOffset - nextTitle.getRight()) : (ListenerUtil.mutListener.listen(25409) ? (mSelectionOffset + nextTitle.getRight()) : (mSelectionOffset * nextTitle.getRight()))))) + (ListenerUtil.mutListener.listen(25420) ? (((ListenerUtil.mutListener.listen(25416) ? (1.0f % mSelectionOffset) : (ListenerUtil.mutListener.listen(25415) ? (1.0f / mSelectionOffset) : (ListenerUtil.mutListener.listen(25414) ? (1.0f * mSelectionOffset) : (ListenerUtil.mutListener.listen(25413) ? (1.0f + mSelectionOffset) : (1.0f - mSelectionOffset)))))) % right) : (ListenerUtil.mutListener.listen(25419) ? (((ListenerUtil.mutListener.listen(25416) ? (1.0f % mSelectionOffset) : (ListenerUtil.mutListener.listen(25415) ? (1.0f / mSelectionOffset) : (ListenerUtil.mutListener.listen(25414) ? (1.0f * mSelectionOffset) : (ListenerUtil.mutListener.listen(25413) ? (1.0f + mSelectionOffset) : (1.0f - mSelectionOffset)))))) / right) : (ListenerUtil.mutListener.listen(25418) ? (((ListenerUtil.mutListener.listen(25416) ? (1.0f % mSelectionOffset) : (ListenerUtil.mutListener.listen(25415) ? (1.0f / mSelectionOffset) : (ListenerUtil.mutListener.listen(25414) ? (1.0f * mSelectionOffset) : (ListenerUtil.mutListener.listen(25413) ? (1.0f + mSelectionOffset) : (1.0f - mSelectionOffset)))))) - right) : (ListenerUtil.mutListener.listen(25417) ? (((ListenerUtil.mutListener.listen(25416) ? (1.0f % mSelectionOffset) : (ListenerUtil.mutListener.listen(25415) ? (1.0f / mSelectionOffset) : (ListenerUtil.mutListener.listen(25414) ? (1.0f * mSelectionOffset) : (ListenerUtil.mutListener.listen(25413) ? (1.0f + mSelectionOffset) : (1.0f - mSelectionOffset)))))) + right) : (((ListenerUtil.mutListener.listen(25416) ? (1.0f % mSelectionOffset) : (ListenerUtil.mutListener.listen(25415) ? (1.0f / mSelectionOffset) : (ListenerUtil.mutListener.listen(25414) ? (1.0f * mSelectionOffset) : (ListenerUtil.mutListener.listen(25413) ? (1.0f + mSelectionOffset) : (1.0f - mSelectionOffset)))))) * right))))));
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(25423)) {
                    mSelectedIndicatorPaint.setColor(color);
                }
                if (!ListenerUtil.mutListener.listen(25428)) {
                    canvas.drawRect(left, (ListenerUtil.mutListener.listen(25427) ? (height % mSelectedIndicatorThickness) : (ListenerUtil.mutListener.listen(25426) ? (height / mSelectedIndicatorThickness) : (ListenerUtil.mutListener.listen(25425) ? (height * mSelectedIndicatorThickness) : (ListenerUtil.mutListener.listen(25424) ? (height + mSelectedIndicatorThickness) : (height - mSelectedIndicatorThickness))))), right, height, mSelectedIndicatorPaint);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(25434)) {
            // Thin underline along the entire bottom edge
            canvas.drawRect(0, (ListenerUtil.mutListener.listen(25433) ? (height % mBottomBorderThickness) : (ListenerUtil.mutListener.listen(25432) ? (height / mBottomBorderThickness) : (ListenerUtil.mutListener.listen(25431) ? (height * mBottomBorderThickness) : (ListenerUtil.mutListener.listen(25430) ? (height + mBottomBorderThickness) : (height - mBottomBorderThickness))))), getWidth(), height, mBottomBorderPaint);
        }
        // Vertical separators between the titles
        int separatorTop = (ListenerUtil.mutListener.listen(25442) ? (((ListenerUtil.mutListener.listen(25438) ? (height % dividerHeightPx) : (ListenerUtil.mutListener.listen(25437) ? (height / dividerHeightPx) : (ListenerUtil.mutListener.listen(25436) ? (height * dividerHeightPx) : (ListenerUtil.mutListener.listen(25435) ? (height + dividerHeightPx) : (height - dividerHeightPx)))))) % 2) : (ListenerUtil.mutListener.listen(25441) ? (((ListenerUtil.mutListener.listen(25438) ? (height % dividerHeightPx) : (ListenerUtil.mutListener.listen(25437) ? (height / dividerHeightPx) : (ListenerUtil.mutListener.listen(25436) ? (height * dividerHeightPx) : (ListenerUtil.mutListener.listen(25435) ? (height + dividerHeightPx) : (height - dividerHeightPx)))))) * 2) : (ListenerUtil.mutListener.listen(25440) ? (((ListenerUtil.mutListener.listen(25438) ? (height % dividerHeightPx) : (ListenerUtil.mutListener.listen(25437) ? (height / dividerHeightPx) : (ListenerUtil.mutListener.listen(25436) ? (height * dividerHeightPx) : (ListenerUtil.mutListener.listen(25435) ? (height + dividerHeightPx) : (height - dividerHeightPx)))))) - 2) : (ListenerUtil.mutListener.listen(25439) ? (((ListenerUtil.mutListener.listen(25438) ? (height % dividerHeightPx) : (ListenerUtil.mutListener.listen(25437) ? (height / dividerHeightPx) : (ListenerUtil.mutListener.listen(25436) ? (height * dividerHeightPx) : (ListenerUtil.mutListener.listen(25435) ? (height + dividerHeightPx) : (height - dividerHeightPx)))))) + 2) : (((ListenerUtil.mutListener.listen(25438) ? (height % dividerHeightPx) : (ListenerUtil.mutListener.listen(25437) ? (height / dividerHeightPx) : (ListenerUtil.mutListener.listen(25436) ? (height * dividerHeightPx) : (ListenerUtil.mutListener.listen(25435) ? (height + dividerHeightPx) : (height - dividerHeightPx)))))) / 2)))));
        if (!ListenerUtil.mutListener.listen(25458)) {
            {
                long _loopCounter672 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(25457) ? (i >= (ListenerUtil.mutListener.listen(25452) ? (childCount % 1) : (ListenerUtil.mutListener.listen(25451) ? (childCount / 1) : (ListenerUtil.mutListener.listen(25450) ? (childCount * 1) : (ListenerUtil.mutListener.listen(25449) ? (childCount + 1) : (childCount - 1)))))) : (ListenerUtil.mutListener.listen(25456) ? (i <= (ListenerUtil.mutListener.listen(25452) ? (childCount % 1) : (ListenerUtil.mutListener.listen(25451) ? (childCount / 1) : (ListenerUtil.mutListener.listen(25450) ? (childCount * 1) : (ListenerUtil.mutListener.listen(25449) ? (childCount + 1) : (childCount - 1)))))) : (ListenerUtil.mutListener.listen(25455) ? (i > (ListenerUtil.mutListener.listen(25452) ? (childCount % 1) : (ListenerUtil.mutListener.listen(25451) ? (childCount / 1) : (ListenerUtil.mutListener.listen(25450) ? (childCount * 1) : (ListenerUtil.mutListener.listen(25449) ? (childCount + 1) : (childCount - 1)))))) : (ListenerUtil.mutListener.listen(25454) ? (i != (ListenerUtil.mutListener.listen(25452) ? (childCount % 1) : (ListenerUtil.mutListener.listen(25451) ? (childCount / 1) : (ListenerUtil.mutListener.listen(25450) ? (childCount * 1) : (ListenerUtil.mutListener.listen(25449) ? (childCount + 1) : (childCount - 1)))))) : (ListenerUtil.mutListener.listen(25453) ? (i == (ListenerUtil.mutListener.listen(25452) ? (childCount % 1) : (ListenerUtil.mutListener.listen(25451) ? (childCount / 1) : (ListenerUtil.mutListener.listen(25450) ? (childCount * 1) : (ListenerUtil.mutListener.listen(25449) ? (childCount + 1) : (childCount - 1)))))) : (i < (ListenerUtil.mutListener.listen(25452) ? (childCount % 1) : (ListenerUtil.mutListener.listen(25451) ? (childCount / 1) : (ListenerUtil.mutListener.listen(25450) ? (childCount * 1) : (ListenerUtil.mutListener.listen(25449) ? (childCount + 1) : (childCount - 1))))))))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter672", ++_loopCounter672);
                    View child = getChildAt(i);
                    if (!ListenerUtil.mutListener.listen(25443)) {
                        mDividerPaint.setColor(tabColorizer.getDividerColor(i));
                    }
                    if (!ListenerUtil.mutListener.listen(25448)) {
                        canvas.drawLine(child.getRight(), separatorTop, child.getRight(), (ListenerUtil.mutListener.listen(25447) ? (separatorTop % dividerHeightPx) : (ListenerUtil.mutListener.listen(25446) ? (separatorTop / dividerHeightPx) : (ListenerUtil.mutListener.listen(25445) ? (separatorTop * dividerHeightPx) : (ListenerUtil.mutListener.listen(25444) ? (separatorTop - dividerHeightPx) : (separatorTop + dividerHeightPx))))), mDividerPaint);
                    }
                }
            }
        }
    }

    /**
     * Set the alpha value of the {@code color} to be the given {@code alpha} value.
     */
    private static int setColorAlpha(int color, byte alpha) {
        return Color.argb(alpha, Color.red(color), Color.green(color), Color.blue(color));
    }

    /**
     * Blend {@code color1} and {@code color2} using the given ratio.
     *
     * @param ratio of which to blend. 1.0 will return {@code color1}, 0.5 will give an even blend,
     *              0.0 will return {@code color2}.
     */
    private static int blendColors(int color1, int color2, float ratio) {
        final float inverseRation = (ListenerUtil.mutListener.listen(25462) ? (1f % ratio) : (ListenerUtil.mutListener.listen(25461) ? (1f / ratio) : (ListenerUtil.mutListener.listen(25460) ? (1f * ratio) : (ListenerUtil.mutListener.listen(25459) ? (1f + ratio) : (1f - ratio)))));
        float r = ((ListenerUtil.mutListener.listen(25466) ? (Color.red(color1) % ratio) : (ListenerUtil.mutListener.listen(25465) ? (Color.red(color1) / ratio) : (ListenerUtil.mutListener.listen(25464) ? (Color.red(color1) - ratio) : (ListenerUtil.mutListener.listen(25463) ? (Color.red(color1) + ratio) : (Color.red(color1) * ratio)))))) + ((ListenerUtil.mutListener.listen(25470) ? (Color.red(color2) % inverseRation) : (ListenerUtil.mutListener.listen(25469) ? (Color.red(color2) / inverseRation) : (ListenerUtil.mutListener.listen(25468) ? (Color.red(color2) - inverseRation) : (ListenerUtil.mutListener.listen(25467) ? (Color.red(color2) + inverseRation) : (Color.red(color2) * inverseRation))))));
        float g = ((ListenerUtil.mutListener.listen(25474) ? (Color.green(color1) % ratio) : (ListenerUtil.mutListener.listen(25473) ? (Color.green(color1) / ratio) : (ListenerUtil.mutListener.listen(25472) ? (Color.green(color1) - ratio) : (ListenerUtil.mutListener.listen(25471) ? (Color.green(color1) + ratio) : (Color.green(color1) * ratio)))))) + ((ListenerUtil.mutListener.listen(25478) ? (Color.green(color2) % inverseRation) : (ListenerUtil.mutListener.listen(25477) ? (Color.green(color2) / inverseRation) : (ListenerUtil.mutListener.listen(25476) ? (Color.green(color2) - inverseRation) : (ListenerUtil.mutListener.listen(25475) ? (Color.green(color2) + inverseRation) : (Color.green(color2) * inverseRation))))));
        float b = ((ListenerUtil.mutListener.listen(25482) ? (Color.blue(color1) % ratio) : (ListenerUtil.mutListener.listen(25481) ? (Color.blue(color1) / ratio) : (ListenerUtil.mutListener.listen(25480) ? (Color.blue(color1) - ratio) : (ListenerUtil.mutListener.listen(25479) ? (Color.blue(color1) + ratio) : (Color.blue(color1) * ratio)))))) + ((ListenerUtil.mutListener.listen(25486) ? (Color.blue(color2) % inverseRation) : (ListenerUtil.mutListener.listen(25485) ? (Color.blue(color2) / inverseRation) : (ListenerUtil.mutListener.listen(25484) ? (Color.blue(color2) - inverseRation) : (ListenerUtil.mutListener.listen(25483) ? (Color.blue(color2) + inverseRation) : (Color.blue(color2) * inverseRation))))));
        return Color.rgb((int) r, (int) g, (int) b);
    }

    private static class SimpleTabColorizer implements SlidingTabLayout.TabColorizer {

        private int[] mIndicatorColors;

        private int[] mDividerColors;

        @Override
        public final int getIndicatorColor(int position) {
            return mIndicatorColors[(ListenerUtil.mutListener.listen(25490) ? (position / mIndicatorColors.length) : (ListenerUtil.mutListener.listen(25489) ? (position * mIndicatorColors.length) : (ListenerUtil.mutListener.listen(25488) ? (position - mIndicatorColors.length) : (ListenerUtil.mutListener.listen(25487) ? (position + mIndicatorColors.length) : (position % mIndicatorColors.length)))))];
        }

        @Override
        public final int getDividerColor(int position) {
            return mDividerColors[(ListenerUtil.mutListener.listen(25494) ? (position / mDividerColors.length) : (ListenerUtil.mutListener.listen(25493) ? (position * mDividerColors.length) : (ListenerUtil.mutListener.listen(25492) ? (position - mDividerColors.length) : (ListenerUtil.mutListener.listen(25491) ? (position + mDividerColors.length) : (position % mDividerColors.length)))))];
        }

        void setIndicatorColors(int... colors) {
            if (!ListenerUtil.mutListener.listen(25495)) {
                mIndicatorColors = colors;
            }
        }

        void setDividerColors(int... colors) {
            if (!ListenerUtil.mutListener.listen(25496)) {
                mDividerColors = colors;
            }
        }
    }
}
