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
import android.graphics.Typeface;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.TextView;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * To be used with ViewPager to provide a tab indicator component which give constant feedback as to
 * the user's scroll progress.
 * <p>
 * To use the component, simply add it to your view hierarchy. Then in your
 * {@link android.app.Activity} or {@link androidx.fragment.app.Fragment} call
 * {@link #setViewPager(ViewPager)} providing it the ViewPager this layout is being used for.
 * <p>
 * The colors can be customized in two ways. The first and simplest is to provide an array of colors
 * via {@link #setSelectedIndicatorColors(int...)} and {@link #setDividerColors(int...)}. The
 * alternative is via the {@link TabColorizer} interface which provides you complete control over
 * which color is used for any individual position.
 * <p>
 * The views used as tabs can be customized by calling {@link #setCustomTabView(int, int)},
 * providing the layout ID of your custom layout.
 */
public class SlidingTabLayout extends HorizontalScrollView {

    /**
     * Allows complete control over the colors drawn in the tab layout. Set with
     * {@link #setCustomTabColorizer(TabColorizer)}.
     */
    public interface TabColorizer {

        /**
         * @return return the color of the indicator used when {@code position} is selected.
         */
        int getIndicatorColor(int position);

        /**
         * @return return the color of the divider drawn to the right of {@code position}.
         */
        int getDividerColor(int position);
    }

    private static final int TITLE_OFFSET_DIPS = 24;

    private static final int TAB_VIEW_PADDING_DIPS = 16;

    private static final int TAB_VIEW_TEXT_SIZE_SP = 12;

    private final int mTitleOffset;

    private int mTabViewLayoutId;

    private int mTabViewTextViewId;

    private ViewPager mViewPager;

    private ViewPager.OnPageChangeListener mViewPagerPageChangeListener;

    private final SlidingTabStrip mTabStrip;

    public SlidingTabLayout(Context context) {
        this(context, null);
    }

    public SlidingTabLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SlidingTabLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        if (!ListenerUtil.mutListener.listen(25211)) {
            // Disable the Scroll Bar
            setHorizontalScrollBarEnabled(false);
        }
        if (!ListenerUtil.mutListener.listen(25212)) {
            // Make sure that the Tab Strips fills this View
            setFillViewport(true);
        }
        mTitleOffset = (int) ((ListenerUtil.mutListener.listen(25216) ? (TITLE_OFFSET_DIPS % getResources().getDisplayMetrics().density) : (ListenerUtil.mutListener.listen(25215) ? (TITLE_OFFSET_DIPS / getResources().getDisplayMetrics().density) : (ListenerUtil.mutListener.listen(25214) ? (TITLE_OFFSET_DIPS - getResources().getDisplayMetrics().density) : (ListenerUtil.mutListener.listen(25213) ? (TITLE_OFFSET_DIPS + getResources().getDisplayMetrics().density) : (TITLE_OFFSET_DIPS * getResources().getDisplayMetrics().density))))));
        mTabStrip = new SlidingTabStrip(context);
        if (!ListenerUtil.mutListener.listen(25217)) {
            addView(mTabStrip, LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        }
    }

    /**
     * Set the custom {@link TabColorizer} to be used.
     *
     * If you only require simple custmisation then you can use
     * {@link #setSelectedIndicatorColors(int...)} and {@link #setDividerColors(int...)} to achieve
     * similar effects.
     */
    public void setCustomTabColorizer(TabColorizer tabColorizer) {
        if (!ListenerUtil.mutListener.listen(25218)) {
            mTabStrip.setCustomTabColorizer(tabColorizer);
        }
    }

    /**
     * Sets the colors to be used for indicating the selected tab. These colors are treated as a
     * circular array. Providing one color will mean that all tabs are indicated with the same color.
     */
    public void setSelectedIndicatorColors(int... colors) {
        if (!ListenerUtil.mutListener.listen(25219)) {
            mTabStrip.setSelectedIndicatorColors(colors);
        }
    }

    /**
     * Sets the colors to be used for tab dividers. These colors are treated as a circular array.
     * Providing one color will mean that all tabs are indicated with the same color.
     */
    public void setDividerColors(int... colors) {
        if (!ListenerUtil.mutListener.listen(25220)) {
            mTabStrip.setDividerColors(colors);
        }
    }

    /**
     * Set the {@link ViewPager.OnPageChangeListener}. When using {@link SlidingTabLayout} you are
     * required to set any {@link ViewPager.OnPageChangeListener} through this method. This is so
     * that the layout can update it's scroll position correctly.
     *
     * @see ViewPager#setOnPageChangeListener(ViewPager.OnPageChangeListener)
     */
    public void setOnPageChangeListener(ViewPager.OnPageChangeListener listener) {
        if (!ListenerUtil.mutListener.listen(25221)) {
            mViewPagerPageChangeListener = listener;
        }
    }

    /**
     * Set the custom layout to be inflated for the tab views.
     *
     * @param layoutResId Layout id to be inflated
     * @param textViewId id of the {@link TextView} in the inflated view
     */
    public void setCustomTabView(int layoutResId, int textViewId) {
        if (!ListenerUtil.mutListener.listen(25222)) {
            mTabViewLayoutId = layoutResId;
        }
        if (!ListenerUtil.mutListener.listen(25223)) {
            mTabViewTextViewId = textViewId;
        }
    }

    /**
     * Sets the associated view pager. Note that the assumption here is that the pager content
     * (number of tabs and tab titles) does not change after this call has been made.
     */
    public void setViewPager(ViewPager viewPager) {
        if (!ListenerUtil.mutListener.listen(25224)) {
            mTabStrip.removeAllViews();
        }
        if (!ListenerUtil.mutListener.listen(25225)) {
            mViewPager = viewPager;
        }
        if (!ListenerUtil.mutListener.listen(25228)) {
            if (viewPager != null) {
                if (!ListenerUtil.mutListener.listen(25226)) {
                    viewPager.addOnPageChangeListener(new InternalViewPagerListener());
                }
                if (!ListenerUtil.mutListener.listen(25227)) {
                    populateTabStrip();
                }
            }
        }
    }

    /**
     * Create a default view to be used for tabs. This is called if a custom tab view is not set via
     * {@link #setCustomTabView(int, int)}.
     */
    protected TextView createDefaultTabView(Context context) {
        TextView textView = new FixedTextView(context);
        if (!ListenerUtil.mutListener.listen(25229)) {
            textView.setGravity(Gravity.CENTER);
        }
        if (!ListenerUtil.mutListener.listen(25230)) {
            textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, TAB_VIEW_TEXT_SIZE_SP);
        }
        if (!ListenerUtil.mutListener.listen(25231)) {
            textView.setTypeface(Typeface.DEFAULT_BOLD);
        }
        // use the Theme's selectableItemBackground to ensure that the View has a pressed state
        TypedValue outValue = new TypedValue();
        if (!ListenerUtil.mutListener.listen(25232)) {
            getContext().getTheme().resolveAttribute(android.R.attr.selectableItemBackground, outValue, true);
        }
        if (!ListenerUtil.mutListener.listen(25233)) {
            textView.setBackgroundResource(outValue.resourceId);
        }
        if (!ListenerUtil.mutListener.listen(25234)) {
            // enable all-caps to match the Action Bar tab style
            textView.setAllCaps(true);
        }
        int padding = (int) ((ListenerUtil.mutListener.listen(25238) ? (TAB_VIEW_PADDING_DIPS % getResources().getDisplayMetrics().density) : (ListenerUtil.mutListener.listen(25237) ? (TAB_VIEW_PADDING_DIPS / getResources().getDisplayMetrics().density) : (ListenerUtil.mutListener.listen(25236) ? (TAB_VIEW_PADDING_DIPS - getResources().getDisplayMetrics().density) : (ListenerUtil.mutListener.listen(25235) ? (TAB_VIEW_PADDING_DIPS + getResources().getDisplayMetrics().density) : (TAB_VIEW_PADDING_DIPS * getResources().getDisplayMetrics().density))))));
        if (!ListenerUtil.mutListener.listen(25239)) {
            textView.setPadding(padding, padding, padding, padding);
        }
        return textView;
    }

    private void populateTabStrip() {
        final PagerAdapter adapter = mViewPager.getAdapter();
        final View.OnClickListener tabClickListener = new TabClickListener();
        if (!ListenerUtil.mutListener.listen(25261)) {
            {
                long _loopCounter670 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(25260) ? (i >= adapter.getCount()) : (ListenerUtil.mutListener.listen(25259) ? (i <= adapter.getCount()) : (ListenerUtil.mutListener.listen(25258) ? (i > adapter.getCount()) : (ListenerUtil.mutListener.listen(25257) ? (i != adapter.getCount()) : (ListenerUtil.mutListener.listen(25256) ? (i == adapter.getCount()) : (i < adapter.getCount())))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter670", ++_loopCounter670);
                    View tabView = null;
                    TextView tabTitleView = null;
                    if (!ListenerUtil.mutListener.listen(25247)) {
                        if ((ListenerUtil.mutListener.listen(25244) ? (mTabViewLayoutId >= 0) : (ListenerUtil.mutListener.listen(25243) ? (mTabViewLayoutId <= 0) : (ListenerUtil.mutListener.listen(25242) ? (mTabViewLayoutId > 0) : (ListenerUtil.mutListener.listen(25241) ? (mTabViewLayoutId < 0) : (ListenerUtil.mutListener.listen(25240) ? (mTabViewLayoutId == 0) : (mTabViewLayoutId != 0))))))) {
                            if (!ListenerUtil.mutListener.listen(25245)) {
                                // If there is a custom tab view layout id set, try and inflate it
                                tabView = LayoutInflater.from(getContext()).inflate(mTabViewLayoutId, mTabStrip, false);
                            }
                            if (!ListenerUtil.mutListener.listen(25246)) {
                                tabTitleView = tabView.findViewById(mTabViewTextViewId);
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(25249)) {
                        if (tabView == null) {
                            if (!ListenerUtil.mutListener.listen(25248)) {
                                tabView = createDefaultTabView(getContext());
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(25252)) {
                        if ((ListenerUtil.mutListener.listen(25250) ? (tabTitleView == null || tabView instanceof TextView) : (tabTitleView == null && tabView instanceof TextView))) {
                            if (!ListenerUtil.mutListener.listen(25251)) {
                                tabTitleView = (TextView) tabView;
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(25253)) {
                        tabTitleView.setText(adapter.getPageTitle(i));
                    }
                    if (!ListenerUtil.mutListener.listen(25254)) {
                        tabView.setOnClickListener(tabClickListener);
                    }
                    if (!ListenerUtil.mutListener.listen(25255)) {
                        mTabStrip.addView(tabView);
                    }
                }
            }
        }
    }

    @Override
    protected void onAttachedToWindow() {
        if (!ListenerUtil.mutListener.listen(25262)) {
            super.onAttachedToWindow();
        }
        if (!ListenerUtil.mutListener.listen(25264)) {
            if (mViewPager != null) {
                if (!ListenerUtil.mutListener.listen(25263)) {
                    scrollToTab(mViewPager.getCurrentItem(), 0);
                }
            }
        }
    }

    private void scrollToTab(int tabIndex, int positionOffset) {
        final int tabStripChildCount = mTabStrip.getChildCount();
        if (!ListenerUtil.mutListener.listen(25276)) {
            if ((ListenerUtil.mutListener.listen(25275) ? ((ListenerUtil.mutListener.listen(25269) ? (tabIndex >= 0) : (ListenerUtil.mutListener.listen(25268) ? (tabIndex <= 0) : (ListenerUtil.mutListener.listen(25267) ? (tabIndex > 0) : (ListenerUtil.mutListener.listen(25266) ? (tabIndex != 0) : (ListenerUtil.mutListener.listen(25265) ? (tabIndex == 0) : (tabIndex < 0)))))) && (ListenerUtil.mutListener.listen(25274) ? (tabIndex <= tabStripChildCount) : (ListenerUtil.mutListener.listen(25273) ? (tabIndex > tabStripChildCount) : (ListenerUtil.mutListener.listen(25272) ? (tabIndex < tabStripChildCount) : (ListenerUtil.mutListener.listen(25271) ? (tabIndex != tabStripChildCount) : (ListenerUtil.mutListener.listen(25270) ? (tabIndex == tabStripChildCount) : (tabIndex >= tabStripChildCount))))))) : ((ListenerUtil.mutListener.listen(25269) ? (tabIndex >= 0) : (ListenerUtil.mutListener.listen(25268) ? (tabIndex <= 0) : (ListenerUtil.mutListener.listen(25267) ? (tabIndex > 0) : (ListenerUtil.mutListener.listen(25266) ? (tabIndex != 0) : (ListenerUtil.mutListener.listen(25265) ? (tabIndex == 0) : (tabIndex < 0)))))) || (ListenerUtil.mutListener.listen(25274) ? (tabIndex <= tabStripChildCount) : (ListenerUtil.mutListener.listen(25273) ? (tabIndex > tabStripChildCount) : (ListenerUtil.mutListener.listen(25272) ? (tabIndex < tabStripChildCount) : (ListenerUtil.mutListener.listen(25271) ? (tabIndex != tabStripChildCount) : (ListenerUtil.mutListener.listen(25270) ? (tabIndex == tabStripChildCount) : (tabIndex >= tabStripChildCount))))))))) {
                return;
            }
        }
        View selectedChild = mTabStrip.getChildAt(tabIndex);
        if (!ListenerUtil.mutListener.listen(25291)) {
            if (selectedChild != null) {
                int targetScrollX = selectedChild.getLeft() + positionOffset;
                if (!ListenerUtil.mutListener.listen(25289)) {
                    if ((ListenerUtil.mutListener.listen(25287) ? ((ListenerUtil.mutListener.listen(25281) ? (tabIndex >= 0) : (ListenerUtil.mutListener.listen(25280) ? (tabIndex <= 0) : (ListenerUtil.mutListener.listen(25279) ? (tabIndex < 0) : (ListenerUtil.mutListener.listen(25278) ? (tabIndex != 0) : (ListenerUtil.mutListener.listen(25277) ? (tabIndex == 0) : (tabIndex > 0)))))) && (ListenerUtil.mutListener.listen(25286) ? (positionOffset >= 0) : (ListenerUtil.mutListener.listen(25285) ? (positionOffset <= 0) : (ListenerUtil.mutListener.listen(25284) ? (positionOffset < 0) : (ListenerUtil.mutListener.listen(25283) ? (positionOffset != 0) : (ListenerUtil.mutListener.listen(25282) ? (positionOffset == 0) : (positionOffset > 0))))))) : ((ListenerUtil.mutListener.listen(25281) ? (tabIndex >= 0) : (ListenerUtil.mutListener.listen(25280) ? (tabIndex <= 0) : (ListenerUtil.mutListener.listen(25279) ? (tabIndex < 0) : (ListenerUtil.mutListener.listen(25278) ? (tabIndex != 0) : (ListenerUtil.mutListener.listen(25277) ? (tabIndex == 0) : (tabIndex > 0)))))) || (ListenerUtil.mutListener.listen(25286) ? (positionOffset >= 0) : (ListenerUtil.mutListener.listen(25285) ? (positionOffset <= 0) : (ListenerUtil.mutListener.listen(25284) ? (positionOffset < 0) : (ListenerUtil.mutListener.listen(25283) ? (positionOffset != 0) : (ListenerUtil.mutListener.listen(25282) ? (positionOffset == 0) : (positionOffset > 0))))))))) {
                        if (!ListenerUtil.mutListener.listen(25288)) {
                            // If we're not at the first child and are mid-scroll, make sure we obey the offset
                            targetScrollX -= mTitleOffset;
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(25290)) {
                    scrollTo(targetScrollX, 0);
                }
            }
        }
    }

    private class InternalViewPagerListener implements ViewPager.OnPageChangeListener {

        private int mScrollState;

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            int tabStripChildCount = mTabStrip.getChildCount();
            if (!ListenerUtil.mutListener.listen(25303)) {
                if ((ListenerUtil.mutListener.listen(25302) ? (((ListenerUtil.mutListener.listen(25296) ? (position >= 0) : (ListenerUtil.mutListener.listen(25295) ? (position <= 0) : (ListenerUtil.mutListener.listen(25294) ? (position > 0) : (ListenerUtil.mutListener.listen(25293) ? (position != 0) : (ListenerUtil.mutListener.listen(25292) ? (position == 0) : (position < 0))))))) && ((ListenerUtil.mutListener.listen(25301) ? (position <= tabStripChildCount) : (ListenerUtil.mutListener.listen(25300) ? (position > tabStripChildCount) : (ListenerUtil.mutListener.listen(25299) ? (position < tabStripChildCount) : (ListenerUtil.mutListener.listen(25298) ? (position != tabStripChildCount) : (ListenerUtil.mutListener.listen(25297) ? (position == tabStripChildCount) : (position >= tabStripChildCount)))))))) : (((ListenerUtil.mutListener.listen(25296) ? (position >= 0) : (ListenerUtil.mutListener.listen(25295) ? (position <= 0) : (ListenerUtil.mutListener.listen(25294) ? (position > 0) : (ListenerUtil.mutListener.listen(25293) ? (position != 0) : (ListenerUtil.mutListener.listen(25292) ? (position == 0) : (position < 0))))))) || ((ListenerUtil.mutListener.listen(25301) ? (position <= tabStripChildCount) : (ListenerUtil.mutListener.listen(25300) ? (position > tabStripChildCount) : (ListenerUtil.mutListener.listen(25299) ? (position < tabStripChildCount) : (ListenerUtil.mutListener.listen(25298) ? (position != tabStripChildCount) : (ListenerUtil.mutListener.listen(25297) ? (position == tabStripChildCount) : (position >= tabStripChildCount)))))))))) {
                    return;
                }
            }
            if (!ListenerUtil.mutListener.listen(25304)) {
                mTabStrip.onViewPagerPageChanged(position, positionOffset);
            }
            View selectedTitle = mTabStrip.getChildAt(position);
            int extraOffset = (selectedTitle != null) ? (int) ((ListenerUtil.mutListener.listen(25308) ? (positionOffset % selectedTitle.getWidth()) : (ListenerUtil.mutListener.listen(25307) ? (positionOffset / selectedTitle.getWidth()) : (ListenerUtil.mutListener.listen(25306) ? (positionOffset - selectedTitle.getWidth()) : (ListenerUtil.mutListener.listen(25305) ? (positionOffset + selectedTitle.getWidth()) : (positionOffset * selectedTitle.getWidth())))))) : 0;
            if (!ListenerUtil.mutListener.listen(25309)) {
                scrollToTab(position, extraOffset);
            }
            if (!ListenerUtil.mutListener.listen(25311)) {
                if (mViewPagerPageChangeListener != null) {
                    if (!ListenerUtil.mutListener.listen(25310)) {
                        mViewPagerPageChangeListener.onPageScrolled(position, positionOffset, positionOffsetPixels);
                    }
                }
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {
            if (!ListenerUtil.mutListener.listen(25312)) {
                mScrollState = state;
            }
            if (!ListenerUtil.mutListener.listen(25314)) {
                if (mViewPagerPageChangeListener != null) {
                    if (!ListenerUtil.mutListener.listen(25313)) {
                        mViewPagerPageChangeListener.onPageScrollStateChanged(state);
                    }
                }
            }
        }

        @Override
        public void onPageSelected(int position) {
            if (!ListenerUtil.mutListener.listen(25317)) {
                if (mScrollState == ViewPager.SCROLL_STATE_IDLE) {
                    if (!ListenerUtil.mutListener.listen(25315)) {
                        mTabStrip.onViewPagerPageChanged(position, 0f);
                    }
                    if (!ListenerUtil.mutListener.listen(25316)) {
                        scrollToTab(position, 0);
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(25319)) {
                if (mViewPagerPageChangeListener != null) {
                    if (!ListenerUtil.mutListener.listen(25318)) {
                        mViewPagerPageChangeListener.onPageSelected(position);
                    }
                }
            }
        }
    }

    private class TabClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            if (!ListenerUtil.mutListener.listen(25327)) {
                {
                    long _loopCounter671 = 0;
                    for (int i = 0; (ListenerUtil.mutListener.listen(25326) ? (i >= mTabStrip.getChildCount()) : (ListenerUtil.mutListener.listen(25325) ? (i <= mTabStrip.getChildCount()) : (ListenerUtil.mutListener.listen(25324) ? (i > mTabStrip.getChildCount()) : (ListenerUtil.mutListener.listen(25323) ? (i != mTabStrip.getChildCount()) : (ListenerUtil.mutListener.listen(25322) ? (i == mTabStrip.getChildCount()) : (i < mTabStrip.getChildCount())))))); i++) {
                        ListenerUtil.loopListener.listen("_loopCounter671", ++_loopCounter671);
                        if (!ListenerUtil.mutListener.listen(25321)) {
                            if (v == mTabStrip.getChildAt(i)) {
                                if (!ListenerUtil.mutListener.listen(25320)) {
                                    mViewPager.setCurrentItem(i);
                                }
                                return;
                            }
                        }
                    }
                }
            }
        }
    }
}
