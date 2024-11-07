/*
 * Copyright (C) 2013 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.wordpress.android.widgets;

import android.content.Context;
import android.database.DataSetObservable;
import android.database.DataSetObserver;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.WrapperListAdapter;
import androidx.core.view.ViewCompat;
import java.util.ArrayList;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * A {@link GridView} that supports adding header rows in a
 * very similar way to {@link ListView}.
 * See {@link HeaderGridView#addHeaderView(View, Object, boolean)}
 */
public class HeaderGridView extends GridView {

    private static final String TAG = "HeaderGridView";

    /**
     * A class that represents a fixed view in a list, for example a header at the top
     * or a footer at the bottom.
     */
    private static class FixedViewInfo {

        /**
         * The view to add to the grid
         */
        public View view;

        public ViewGroup viewContainer;

        /**
         * The data backing the view. This is returned from {@link ListAdapter#getItem(int)}.
         */
        public Object data;

        /**
         * <code>true</code> if the fixed view should be selectable in the grid
         */
        public boolean isSelectable;
    }

    private ArrayList<FixedViewInfo> mHeaderViewInfos = new ArrayList<FixedViewInfo>();

    private void initHeaderGridView() {
        if (!ListenerUtil.mutListener.listen(28509)) {
            super.setClipChildren(false);
        }
    }

    public HeaderGridView(Context context) {
        super(context);
        if (!ListenerUtil.mutListener.listen(28510)) {
            initHeaderGridView();
        }
    }

    public HeaderGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
        if (!ListenerUtil.mutListener.listen(28511)) {
            initHeaderGridView();
        }
    }

    public HeaderGridView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        if (!ListenerUtil.mutListener.listen(28512)) {
            initHeaderGridView();
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (!ListenerUtil.mutListener.listen(28513)) {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
        ListAdapter adapter = getAdapter();
        if (!ListenerUtil.mutListener.listen(28516)) {
            if ((ListenerUtil.mutListener.listen(28514) ? (adapter != null || adapter instanceof HeaderViewGridAdapter) : (adapter != null && adapter instanceof HeaderViewGridAdapter))) {
                if (!ListenerUtil.mutListener.listen(28515)) {
                    ((HeaderViewGridAdapter) adapter).setNumColumns(getNumColumns());
                }
            }
        }
    }

    @Override
    public void setClipChildren(boolean clipChildren) {
    }

    /**
     * Add a fixed view to appear at the top of the grid. If addHeaderView is
     * called more than once, the views will appear in the order they were
     * added. Views added using this call can take focus if they want.
     * <p>
     * NOTE: Call this before calling setAdapter. This is so HeaderGridView can wrap
     * the supplied cursor with one that will also account for header views.
     *
     * @param v The view to add.
     * @param data Data to associate with this view
     * @param isSelectable whether the item is selectable
     */
    public void addHeaderView(View v, Object data, boolean isSelectable) {
        ListAdapter adapter = getAdapter();
        if (!ListenerUtil.mutListener.listen(28518)) {
            if ((ListenerUtil.mutListener.listen(28517) ? (adapter != null || !(adapter instanceof HeaderViewGridAdapter)) : (adapter != null && !(adapter instanceof HeaderViewGridAdapter)))) {
                throw new IllegalStateException("Cannot add header view to grid -- setAdapter has already been called.");
            }
        }
        FixedViewInfo info = new FixedViewInfo();
        FrameLayout fl = new FullWidthFixedViewLayout(getContext());
        if (!ListenerUtil.mutListener.listen(28519)) {
            fl.addView(v);
        }
        if (!ListenerUtil.mutListener.listen(28520)) {
            info.view = v;
        }
        if (!ListenerUtil.mutListener.listen(28521)) {
            info.viewContainer = fl;
        }
        if (!ListenerUtil.mutListener.listen(28522)) {
            info.data = data;
        }
        if (!ListenerUtil.mutListener.listen(28523)) {
            info.isSelectable = isSelectable;
        }
        if (!ListenerUtil.mutListener.listen(28524)) {
            mHeaderViewInfos.add(info);
        }
        if (!ListenerUtil.mutListener.listen(28526)) {
            // we need to notify the observer
            if (adapter != null) {
                if (!ListenerUtil.mutListener.listen(28525)) {
                    ((HeaderViewGridAdapter) adapter).notifyDataSetChanged();
                }
            }
        }
    }

    /**
     * Add a fixed view to appear at the top of the grid. If addHeaderView is
     * called more than once, the views will appear in the order they were
     * added. Views added using this call can take focus if they want.
     * <p>
     * NOTE: Call this before calling setAdapter. This is so HeaderGridView can wrap
     * the supplied cursor with one that will also account for header views.
     *
     * @param v The view to add.
     */
    public void addHeaderView(View v) {
        if (!ListenerUtil.mutListener.listen(28527)) {
            addHeaderView(v, null, true);
        }
    }

    public int getHeaderViewCount() {
        return mHeaderViewInfos.size();
    }

    /**
     * Removes a previously-added header view.
     *
     * @param v The view to remove
     * @return true if the view was removed, false if the view was not a header
     * view
     */
    public boolean removeHeaderView(View v) {
        if (!ListenerUtil.mutListener.listen(28537)) {
            if ((ListenerUtil.mutListener.listen(28532) ? (mHeaderViewInfos.size() >= 0) : (ListenerUtil.mutListener.listen(28531) ? (mHeaderViewInfos.size() <= 0) : (ListenerUtil.mutListener.listen(28530) ? (mHeaderViewInfos.size() < 0) : (ListenerUtil.mutListener.listen(28529) ? (mHeaderViewInfos.size() != 0) : (ListenerUtil.mutListener.listen(28528) ? (mHeaderViewInfos.size() == 0) : (mHeaderViewInfos.size() > 0))))))) {
                boolean result = false;
                ListAdapter adapter = getAdapter();
                if (!ListenerUtil.mutListener.listen(28535)) {
                    if ((ListenerUtil.mutListener.listen(28533) ? (adapter != null || ((HeaderViewGridAdapter) adapter).removeHeader(v)) : (adapter != null && ((HeaderViewGridAdapter) adapter).removeHeader(v)))) {
                        if (!ListenerUtil.mutListener.listen(28534)) {
                            result = true;
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(28536)) {
                    removeFixedViewInfo(v, mHeaderViewInfos);
                }
                return result;
            }
        }
        return false;
    }

    private void removeFixedViewInfo(View v, ArrayList<FixedViewInfo> where) {
        int len = where.size();
        if (!ListenerUtil.mutListener.listen(28545)) {
            {
                long _loopCounter431 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(28544) ? (i >= len) : (ListenerUtil.mutListener.listen(28543) ? (i <= len) : (ListenerUtil.mutListener.listen(28542) ? (i > len) : (ListenerUtil.mutListener.listen(28541) ? (i != len) : (ListenerUtil.mutListener.listen(28540) ? (i == len) : (i < len)))))); ++i) {
                    ListenerUtil.loopListener.listen("_loopCounter431", ++_loopCounter431);
                    FixedViewInfo info = where.get(i);
                    if (!ListenerUtil.mutListener.listen(28539)) {
                        if (info.view == v) {
                            if (!ListenerUtil.mutListener.listen(28538)) {
                                where.remove(i);
                            }
                            break;
                        }
                    }
                }
            }
        }
    }

    @Override
    public void setAdapter(ListAdapter adapter) {
        if (!ListenerUtil.mutListener.listen(28560)) {
            if ((ListenerUtil.mutListener.listen(28550) ? (mHeaderViewInfos.size() >= 0) : (ListenerUtil.mutListener.listen(28549) ? (mHeaderViewInfos.size() <= 0) : (ListenerUtil.mutListener.listen(28548) ? (mHeaderViewInfos.size() < 0) : (ListenerUtil.mutListener.listen(28547) ? (mHeaderViewInfos.size() != 0) : (ListenerUtil.mutListener.listen(28546) ? (mHeaderViewInfos.size() == 0) : (mHeaderViewInfos.size() > 0))))))) {
                HeaderViewGridAdapter hadapter = new HeaderViewGridAdapter(mHeaderViewInfos, adapter);
                int numColumns = getNumColumns();
                if (!ListenerUtil.mutListener.listen(28558)) {
                    if ((ListenerUtil.mutListener.listen(28556) ? (numColumns >= 1) : (ListenerUtil.mutListener.listen(28555) ? (numColumns <= 1) : (ListenerUtil.mutListener.listen(28554) ? (numColumns < 1) : (ListenerUtil.mutListener.listen(28553) ? (numColumns != 1) : (ListenerUtil.mutListener.listen(28552) ? (numColumns == 1) : (numColumns > 1))))))) {
                        if (!ListenerUtil.mutListener.listen(28557)) {
                            hadapter.setNumColumns(numColumns);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(28559)) {
                    super.setAdapter(hadapter);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(28551)) {
                    super.setAdapter(adapter);
                }
            }
        }
    }

    private class FullWidthFixedViewLayout extends FrameLayout {

        FullWidthFixedViewLayout(Context context) {
            super(context);
        }

        @Override
        protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
            int targetWidth = (ListenerUtil.mutListener.listen(28568) ? ((ListenerUtil.mutListener.listen(28564) ? (HeaderGridView.this.getMeasuredWidth() % ViewCompat.getPaddingStart(HeaderGridView.this)) : (ListenerUtil.mutListener.listen(28563) ? (HeaderGridView.this.getMeasuredWidth() / ViewCompat.getPaddingStart(HeaderGridView.this)) : (ListenerUtil.mutListener.listen(28562) ? (HeaderGridView.this.getMeasuredWidth() * ViewCompat.getPaddingStart(HeaderGridView.this)) : (ListenerUtil.mutListener.listen(28561) ? (HeaderGridView.this.getMeasuredWidth() + ViewCompat.getPaddingStart(HeaderGridView.this)) : (HeaderGridView.this.getMeasuredWidth() - ViewCompat.getPaddingStart(HeaderGridView.this)))))) % ViewCompat.getPaddingEnd(HeaderGridView.this)) : (ListenerUtil.mutListener.listen(28567) ? ((ListenerUtil.mutListener.listen(28564) ? (HeaderGridView.this.getMeasuredWidth() % ViewCompat.getPaddingStart(HeaderGridView.this)) : (ListenerUtil.mutListener.listen(28563) ? (HeaderGridView.this.getMeasuredWidth() / ViewCompat.getPaddingStart(HeaderGridView.this)) : (ListenerUtil.mutListener.listen(28562) ? (HeaderGridView.this.getMeasuredWidth() * ViewCompat.getPaddingStart(HeaderGridView.this)) : (ListenerUtil.mutListener.listen(28561) ? (HeaderGridView.this.getMeasuredWidth() + ViewCompat.getPaddingStart(HeaderGridView.this)) : (HeaderGridView.this.getMeasuredWidth() - ViewCompat.getPaddingStart(HeaderGridView.this)))))) / ViewCompat.getPaddingEnd(HeaderGridView.this)) : (ListenerUtil.mutListener.listen(28566) ? ((ListenerUtil.mutListener.listen(28564) ? (HeaderGridView.this.getMeasuredWidth() % ViewCompat.getPaddingStart(HeaderGridView.this)) : (ListenerUtil.mutListener.listen(28563) ? (HeaderGridView.this.getMeasuredWidth() / ViewCompat.getPaddingStart(HeaderGridView.this)) : (ListenerUtil.mutListener.listen(28562) ? (HeaderGridView.this.getMeasuredWidth() * ViewCompat.getPaddingStart(HeaderGridView.this)) : (ListenerUtil.mutListener.listen(28561) ? (HeaderGridView.this.getMeasuredWidth() + ViewCompat.getPaddingStart(HeaderGridView.this)) : (HeaderGridView.this.getMeasuredWidth() - ViewCompat.getPaddingStart(HeaderGridView.this)))))) * ViewCompat.getPaddingEnd(HeaderGridView.this)) : (ListenerUtil.mutListener.listen(28565) ? ((ListenerUtil.mutListener.listen(28564) ? (HeaderGridView.this.getMeasuredWidth() % ViewCompat.getPaddingStart(HeaderGridView.this)) : (ListenerUtil.mutListener.listen(28563) ? (HeaderGridView.this.getMeasuredWidth() / ViewCompat.getPaddingStart(HeaderGridView.this)) : (ListenerUtil.mutListener.listen(28562) ? (HeaderGridView.this.getMeasuredWidth() * ViewCompat.getPaddingStart(HeaderGridView.this)) : (ListenerUtil.mutListener.listen(28561) ? (HeaderGridView.this.getMeasuredWidth() + ViewCompat.getPaddingStart(HeaderGridView.this)) : (HeaderGridView.this.getMeasuredWidth() - ViewCompat.getPaddingStart(HeaderGridView.this)))))) + ViewCompat.getPaddingEnd(HeaderGridView.this)) : ((ListenerUtil.mutListener.listen(28564) ? (HeaderGridView.this.getMeasuredWidth() % ViewCompat.getPaddingStart(HeaderGridView.this)) : (ListenerUtil.mutListener.listen(28563) ? (HeaderGridView.this.getMeasuredWidth() / ViewCompat.getPaddingStart(HeaderGridView.this)) : (ListenerUtil.mutListener.listen(28562) ? (HeaderGridView.this.getMeasuredWidth() * ViewCompat.getPaddingStart(HeaderGridView.this)) : (ListenerUtil.mutListener.listen(28561) ? (HeaderGridView.this.getMeasuredWidth() + ViewCompat.getPaddingStart(HeaderGridView.this)) : (HeaderGridView.this.getMeasuredWidth() - ViewCompat.getPaddingStart(HeaderGridView.this)))))) - ViewCompat.getPaddingEnd(HeaderGridView.this))))));
            if (!ListenerUtil.mutListener.listen(28569)) {
                widthMeasureSpec = MeasureSpec.makeMeasureSpec(targetWidth, MeasureSpec.getMode(widthMeasureSpec));
            }
            if (!ListenerUtil.mutListener.listen(28570)) {
                super.onMeasure(widthMeasureSpec, heightMeasureSpec);
            }
        }
    }

    /**
     * ListAdapter used when a HeaderGridView has header views. This ListAdapter
     * wraps another one and also keeps track of the header views and their
     * associated data objects.
     *<p>This is intended as a base class; you will probably not need to
     * use this class directly in your own code.
     */
    private static class HeaderViewGridAdapter implements WrapperListAdapter, Filterable {

        // or headers changing, which changes the number of placeholders needed
        private final DataSetObservable mDataSetObservable = new DataSetObservable();

        private final ListAdapter mAdapter;

        private int mNumColumns = 1;

        // This ArrayList is assumed to NOT be null.
        ArrayList<FixedViewInfo> mHeaderViewInfos;

        boolean mAreAllFixedViewsSelectable;

        private final boolean mIsFilterable;

        HeaderViewGridAdapter(ArrayList<FixedViewInfo> headerViewInfos, ListAdapter adapter) {
            mAdapter = adapter;
            mIsFilterable = adapter instanceof Filterable;
            if (!ListenerUtil.mutListener.listen(28571)) {
                if (headerViewInfos == null) {
                    throw new IllegalArgumentException("headerViewInfos cannot be null");
                }
            }
            if (!ListenerUtil.mutListener.listen(28572)) {
                mHeaderViewInfos = headerViewInfos;
            }
            if (!ListenerUtil.mutListener.listen(28573)) {
                mAreAllFixedViewsSelectable = areAllListInfosSelectable(mHeaderViewInfos);
            }
        }

        public int getHeadersCount() {
            return mHeaderViewInfos.size();
        }

        @Override
        public boolean isEmpty() {
            return (ListenerUtil.mutListener.listen(28580) ? (((ListenerUtil.mutListener.listen(28574) ? (mAdapter == null && mAdapter.isEmpty()) : (mAdapter == null || mAdapter.isEmpty()))) || (ListenerUtil.mutListener.listen(28579) ? (getHeadersCount() >= 0) : (ListenerUtil.mutListener.listen(28578) ? (getHeadersCount() <= 0) : (ListenerUtil.mutListener.listen(28577) ? (getHeadersCount() > 0) : (ListenerUtil.mutListener.listen(28576) ? (getHeadersCount() < 0) : (ListenerUtil.mutListener.listen(28575) ? (getHeadersCount() != 0) : (getHeadersCount() == 0))))))) : (((ListenerUtil.mutListener.listen(28574) ? (mAdapter == null && mAdapter.isEmpty()) : (mAdapter == null || mAdapter.isEmpty()))) && (ListenerUtil.mutListener.listen(28579) ? (getHeadersCount() >= 0) : (ListenerUtil.mutListener.listen(28578) ? (getHeadersCount() <= 0) : (ListenerUtil.mutListener.listen(28577) ? (getHeadersCount() > 0) : (ListenerUtil.mutListener.listen(28576) ? (getHeadersCount() < 0) : (ListenerUtil.mutListener.listen(28575) ? (getHeadersCount() != 0) : (getHeadersCount() == 0))))))));
        }

        public void setNumColumns(int numColumns) {
            if (!ListenerUtil.mutListener.listen(28586)) {
                if ((ListenerUtil.mutListener.listen(28585) ? (numColumns >= 1) : (ListenerUtil.mutListener.listen(28584) ? (numColumns <= 1) : (ListenerUtil.mutListener.listen(28583) ? (numColumns > 1) : (ListenerUtil.mutListener.listen(28582) ? (numColumns != 1) : (ListenerUtil.mutListener.listen(28581) ? (numColumns == 1) : (numColumns < 1))))))) {
                    throw new IllegalArgumentException("Number of columns must be 1 or more");
                }
            }
            if (!ListenerUtil.mutListener.listen(28594)) {
                if ((ListenerUtil.mutListener.listen(28591) ? (mNumColumns >= numColumns) : (ListenerUtil.mutListener.listen(28590) ? (mNumColumns <= numColumns) : (ListenerUtil.mutListener.listen(28589) ? (mNumColumns > numColumns) : (ListenerUtil.mutListener.listen(28588) ? (mNumColumns < numColumns) : (ListenerUtil.mutListener.listen(28587) ? (mNumColumns == numColumns) : (mNumColumns != numColumns))))))) {
                    if (!ListenerUtil.mutListener.listen(28592)) {
                        mNumColumns = numColumns;
                    }
                    if (!ListenerUtil.mutListener.listen(28593)) {
                        notifyDataSetChanged();
                    }
                }
            }
        }

        private boolean areAllListInfosSelectable(ArrayList<FixedViewInfo> infos) {
            if (!ListenerUtil.mutListener.listen(28597)) {
                if (infos != null) {
                    if (!ListenerUtil.mutListener.listen(28596)) {
                        {
                            long _loopCounter432 = 0;
                            for (FixedViewInfo info : infos) {
                                ListenerUtil.loopListener.listen("_loopCounter432", ++_loopCounter432);
                                if (!ListenerUtil.mutListener.listen(28595)) {
                                    if (!info.isSelectable) {
                                        return false;
                                    }
                                }
                            }
                        }
                    }
                }
            }
            return true;
        }

        public boolean removeHeader(View v) {
            if (!ListenerUtil.mutListener.listen(28607)) {
                {
                    long _loopCounter433 = 0;
                    for (int i = 0; (ListenerUtil.mutListener.listen(28606) ? (i >= mHeaderViewInfos.size()) : (ListenerUtil.mutListener.listen(28605) ? (i <= mHeaderViewInfos.size()) : (ListenerUtil.mutListener.listen(28604) ? (i > mHeaderViewInfos.size()) : (ListenerUtil.mutListener.listen(28603) ? (i != mHeaderViewInfos.size()) : (ListenerUtil.mutListener.listen(28602) ? (i == mHeaderViewInfos.size()) : (i < mHeaderViewInfos.size())))))); i++) {
                        ListenerUtil.loopListener.listen("_loopCounter433", ++_loopCounter433);
                        FixedViewInfo info = mHeaderViewInfos.get(i);
                        if (!ListenerUtil.mutListener.listen(28601)) {
                            if (info.view == v) {
                                if (!ListenerUtil.mutListener.listen(28598)) {
                                    mHeaderViewInfos.remove(i);
                                }
                                if (!ListenerUtil.mutListener.listen(28599)) {
                                    mAreAllFixedViewsSelectable = areAllListInfosSelectable(mHeaderViewInfos);
                                }
                                if (!ListenerUtil.mutListener.listen(28600)) {
                                    mDataSetObservable.notifyChanged();
                                }
                                return true;
                            }
                        }
                    }
                }
            }
            return false;
        }

        @Override
        public int getCount() {
            if (mAdapter != null) {
                return (ListenerUtil.mutListener.listen(28615) ? (getHeadersCount() % mNumColumns) : (ListenerUtil.mutListener.listen(28614) ? (getHeadersCount() / mNumColumns) : (ListenerUtil.mutListener.listen(28613) ? (getHeadersCount() - mNumColumns) : (ListenerUtil.mutListener.listen(28612) ? (getHeadersCount() + mNumColumns) : (getHeadersCount() * mNumColumns))))) + mAdapter.getCount();
            } else {
                return (ListenerUtil.mutListener.listen(28611) ? (getHeadersCount() % mNumColumns) : (ListenerUtil.mutListener.listen(28610) ? (getHeadersCount() / mNumColumns) : (ListenerUtil.mutListener.listen(28609) ? (getHeadersCount() - mNumColumns) : (ListenerUtil.mutListener.listen(28608) ? (getHeadersCount() + mNumColumns) : (getHeadersCount() * mNumColumns)))));
            }
        }

        @Override
        public boolean areAllItemsEnabled() {
            if (mAdapter != null) {
                return (ListenerUtil.mutListener.listen(28616) ? (mAreAllFixedViewsSelectable || mAdapter.areAllItemsEnabled()) : (mAreAllFixedViewsSelectable && mAdapter.areAllItemsEnabled()));
            } else {
                return true;
            }
        }

        @Override
        public boolean isEnabled(int position) {
            // Header (negative positions will throw an ArrayIndexOutOfBoundsException)
            int numHeadersAndPlaceholders = (ListenerUtil.mutListener.listen(28620) ? (getHeadersCount() % mNumColumns) : (ListenerUtil.mutListener.listen(28619) ? (getHeadersCount() / mNumColumns) : (ListenerUtil.mutListener.listen(28618) ? (getHeadersCount() - mNumColumns) : (ListenerUtil.mutListener.listen(28617) ? (getHeadersCount() + mNumColumns) : (getHeadersCount() * mNumColumns)))));
            if ((ListenerUtil.mutListener.listen(28625) ? (position >= numHeadersAndPlaceholders) : (ListenerUtil.mutListener.listen(28624) ? (position <= numHeadersAndPlaceholders) : (ListenerUtil.mutListener.listen(28623) ? (position > numHeadersAndPlaceholders) : (ListenerUtil.mutListener.listen(28622) ? (position != numHeadersAndPlaceholders) : (ListenerUtil.mutListener.listen(28621) ? (position == numHeadersAndPlaceholders) : (position < numHeadersAndPlaceholders))))))) {
                return (ListenerUtil.mutListener.listen(28639) ? (((ListenerUtil.mutListener.listen(28634) ? ((ListenerUtil.mutListener.listen(28629) ? (position / mNumColumns) : (ListenerUtil.mutListener.listen(28628) ? (position * mNumColumns) : (ListenerUtil.mutListener.listen(28627) ? (position - mNumColumns) : (ListenerUtil.mutListener.listen(28626) ? (position + mNumColumns) : (position % mNumColumns))))) >= 0) : (ListenerUtil.mutListener.listen(28633) ? ((ListenerUtil.mutListener.listen(28629) ? (position / mNumColumns) : (ListenerUtil.mutListener.listen(28628) ? (position * mNumColumns) : (ListenerUtil.mutListener.listen(28627) ? (position - mNumColumns) : (ListenerUtil.mutListener.listen(28626) ? (position + mNumColumns) : (position % mNumColumns))))) <= 0) : (ListenerUtil.mutListener.listen(28632) ? ((ListenerUtil.mutListener.listen(28629) ? (position / mNumColumns) : (ListenerUtil.mutListener.listen(28628) ? (position * mNumColumns) : (ListenerUtil.mutListener.listen(28627) ? (position - mNumColumns) : (ListenerUtil.mutListener.listen(28626) ? (position + mNumColumns) : (position % mNumColumns))))) > 0) : (ListenerUtil.mutListener.listen(28631) ? ((ListenerUtil.mutListener.listen(28629) ? (position / mNumColumns) : (ListenerUtil.mutListener.listen(28628) ? (position * mNumColumns) : (ListenerUtil.mutListener.listen(28627) ? (position - mNumColumns) : (ListenerUtil.mutListener.listen(28626) ? (position + mNumColumns) : (position % mNumColumns))))) < 0) : (ListenerUtil.mutListener.listen(28630) ? ((ListenerUtil.mutListener.listen(28629) ? (position / mNumColumns) : (ListenerUtil.mutListener.listen(28628) ? (position * mNumColumns) : (ListenerUtil.mutListener.listen(28627) ? (position - mNumColumns) : (ListenerUtil.mutListener.listen(28626) ? (position + mNumColumns) : (position % mNumColumns))))) != 0) : ((ListenerUtil.mutListener.listen(28629) ? (position / mNumColumns) : (ListenerUtil.mutListener.listen(28628) ? (position * mNumColumns) : (ListenerUtil.mutListener.listen(28627) ? (position - mNumColumns) : (ListenerUtil.mutListener.listen(28626) ? (position + mNumColumns) : (position % mNumColumns))))) == 0))))))) || mHeaderViewInfos.get((ListenerUtil.mutListener.listen(28638) ? (position % mNumColumns) : (ListenerUtil.mutListener.listen(28637) ? (position * mNumColumns) : (ListenerUtil.mutListener.listen(28636) ? (position - mNumColumns) : (ListenerUtil.mutListener.listen(28635) ? (position + mNumColumns) : (position / mNumColumns)))))).isSelectable) : (((ListenerUtil.mutListener.listen(28634) ? ((ListenerUtil.mutListener.listen(28629) ? (position / mNumColumns) : (ListenerUtil.mutListener.listen(28628) ? (position * mNumColumns) : (ListenerUtil.mutListener.listen(28627) ? (position - mNumColumns) : (ListenerUtil.mutListener.listen(28626) ? (position + mNumColumns) : (position % mNumColumns))))) >= 0) : (ListenerUtil.mutListener.listen(28633) ? ((ListenerUtil.mutListener.listen(28629) ? (position / mNumColumns) : (ListenerUtil.mutListener.listen(28628) ? (position * mNumColumns) : (ListenerUtil.mutListener.listen(28627) ? (position - mNumColumns) : (ListenerUtil.mutListener.listen(28626) ? (position + mNumColumns) : (position % mNumColumns))))) <= 0) : (ListenerUtil.mutListener.listen(28632) ? ((ListenerUtil.mutListener.listen(28629) ? (position / mNumColumns) : (ListenerUtil.mutListener.listen(28628) ? (position * mNumColumns) : (ListenerUtil.mutListener.listen(28627) ? (position - mNumColumns) : (ListenerUtil.mutListener.listen(28626) ? (position + mNumColumns) : (position % mNumColumns))))) > 0) : (ListenerUtil.mutListener.listen(28631) ? ((ListenerUtil.mutListener.listen(28629) ? (position / mNumColumns) : (ListenerUtil.mutListener.listen(28628) ? (position * mNumColumns) : (ListenerUtil.mutListener.listen(28627) ? (position - mNumColumns) : (ListenerUtil.mutListener.listen(28626) ? (position + mNumColumns) : (position % mNumColumns))))) < 0) : (ListenerUtil.mutListener.listen(28630) ? ((ListenerUtil.mutListener.listen(28629) ? (position / mNumColumns) : (ListenerUtil.mutListener.listen(28628) ? (position * mNumColumns) : (ListenerUtil.mutListener.listen(28627) ? (position - mNumColumns) : (ListenerUtil.mutListener.listen(28626) ? (position + mNumColumns) : (position % mNumColumns))))) != 0) : ((ListenerUtil.mutListener.listen(28629) ? (position / mNumColumns) : (ListenerUtil.mutListener.listen(28628) ? (position * mNumColumns) : (ListenerUtil.mutListener.listen(28627) ? (position - mNumColumns) : (ListenerUtil.mutListener.listen(28626) ? (position + mNumColumns) : (position % mNumColumns))))) == 0))))))) && mHeaderViewInfos.get((ListenerUtil.mutListener.listen(28638) ? (position % mNumColumns) : (ListenerUtil.mutListener.listen(28637) ? (position * mNumColumns) : (ListenerUtil.mutListener.listen(28636) ? (position - mNumColumns) : (ListenerUtil.mutListener.listen(28635) ? (position + mNumColumns) : (position / mNumColumns)))))).isSelectable));
            }
            // Adapter
            final int adjPosition = (ListenerUtil.mutListener.listen(28643) ? (position % numHeadersAndPlaceholders) : (ListenerUtil.mutListener.listen(28642) ? (position / numHeadersAndPlaceholders) : (ListenerUtil.mutListener.listen(28641) ? (position * numHeadersAndPlaceholders) : (ListenerUtil.mutListener.listen(28640) ? (position + numHeadersAndPlaceholders) : (position - numHeadersAndPlaceholders)))));
            int adapterCount = 0;
            if (mAdapter != null) {
                if (!ListenerUtil.mutListener.listen(28644)) {
                    adapterCount = mAdapter.getCount();
                }
                if ((ListenerUtil.mutListener.listen(28649) ? (adjPosition >= adapterCount) : (ListenerUtil.mutListener.listen(28648) ? (adjPosition <= adapterCount) : (ListenerUtil.mutListener.listen(28647) ? (adjPosition > adapterCount) : (ListenerUtil.mutListener.listen(28646) ? (adjPosition != adapterCount) : (ListenerUtil.mutListener.listen(28645) ? (adjPosition == adapterCount) : (adjPosition < adapterCount))))))) {
                    return mAdapter.isEnabled(adjPosition);
                }
            }
            throw new ArrayIndexOutOfBoundsException(position);
        }

        @Override
        public Object getItem(int position) {
            // Header (negative positions will throw an ArrayIndexOutOfBoundsException)
            int numHeadersAndPlaceholders = (ListenerUtil.mutListener.listen(28653) ? (getHeadersCount() % mNumColumns) : (ListenerUtil.mutListener.listen(28652) ? (getHeadersCount() / mNumColumns) : (ListenerUtil.mutListener.listen(28651) ? (getHeadersCount() - mNumColumns) : (ListenerUtil.mutListener.listen(28650) ? (getHeadersCount() + mNumColumns) : (getHeadersCount() * mNumColumns)))));
            if ((ListenerUtil.mutListener.listen(28658) ? (position >= numHeadersAndPlaceholders) : (ListenerUtil.mutListener.listen(28657) ? (position <= numHeadersAndPlaceholders) : (ListenerUtil.mutListener.listen(28656) ? (position > numHeadersAndPlaceholders) : (ListenerUtil.mutListener.listen(28655) ? (position != numHeadersAndPlaceholders) : (ListenerUtil.mutListener.listen(28654) ? (position == numHeadersAndPlaceholders) : (position < numHeadersAndPlaceholders))))))) {
                if ((ListenerUtil.mutListener.listen(28667) ? ((ListenerUtil.mutListener.listen(28662) ? (position / mNumColumns) : (ListenerUtil.mutListener.listen(28661) ? (position * mNumColumns) : (ListenerUtil.mutListener.listen(28660) ? (position - mNumColumns) : (ListenerUtil.mutListener.listen(28659) ? (position + mNumColumns) : (position % mNumColumns))))) >= 0) : (ListenerUtil.mutListener.listen(28666) ? ((ListenerUtil.mutListener.listen(28662) ? (position / mNumColumns) : (ListenerUtil.mutListener.listen(28661) ? (position * mNumColumns) : (ListenerUtil.mutListener.listen(28660) ? (position - mNumColumns) : (ListenerUtil.mutListener.listen(28659) ? (position + mNumColumns) : (position % mNumColumns))))) <= 0) : (ListenerUtil.mutListener.listen(28665) ? ((ListenerUtil.mutListener.listen(28662) ? (position / mNumColumns) : (ListenerUtil.mutListener.listen(28661) ? (position * mNumColumns) : (ListenerUtil.mutListener.listen(28660) ? (position - mNumColumns) : (ListenerUtil.mutListener.listen(28659) ? (position + mNumColumns) : (position % mNumColumns))))) > 0) : (ListenerUtil.mutListener.listen(28664) ? ((ListenerUtil.mutListener.listen(28662) ? (position / mNumColumns) : (ListenerUtil.mutListener.listen(28661) ? (position * mNumColumns) : (ListenerUtil.mutListener.listen(28660) ? (position - mNumColumns) : (ListenerUtil.mutListener.listen(28659) ? (position + mNumColumns) : (position % mNumColumns))))) < 0) : (ListenerUtil.mutListener.listen(28663) ? ((ListenerUtil.mutListener.listen(28662) ? (position / mNumColumns) : (ListenerUtil.mutListener.listen(28661) ? (position * mNumColumns) : (ListenerUtil.mutListener.listen(28660) ? (position - mNumColumns) : (ListenerUtil.mutListener.listen(28659) ? (position + mNumColumns) : (position % mNumColumns))))) != 0) : ((ListenerUtil.mutListener.listen(28662) ? (position / mNumColumns) : (ListenerUtil.mutListener.listen(28661) ? (position * mNumColumns) : (ListenerUtil.mutListener.listen(28660) ? (position - mNumColumns) : (ListenerUtil.mutListener.listen(28659) ? (position + mNumColumns) : (position % mNumColumns))))) == 0))))))) {
                    return mHeaderViewInfos.get((ListenerUtil.mutListener.listen(28671) ? (position % mNumColumns) : (ListenerUtil.mutListener.listen(28670) ? (position * mNumColumns) : (ListenerUtil.mutListener.listen(28669) ? (position - mNumColumns) : (ListenerUtil.mutListener.listen(28668) ? (position + mNumColumns) : (position / mNumColumns)))))).data;
                }
                return null;
            }
            // Adapter
            final int adjPosition = (ListenerUtil.mutListener.listen(28675) ? (position % numHeadersAndPlaceholders) : (ListenerUtil.mutListener.listen(28674) ? (position / numHeadersAndPlaceholders) : (ListenerUtil.mutListener.listen(28673) ? (position * numHeadersAndPlaceholders) : (ListenerUtil.mutListener.listen(28672) ? (position + numHeadersAndPlaceholders) : (position - numHeadersAndPlaceholders)))));
            int adapterCount = 0;
            if (mAdapter != null) {
                if (!ListenerUtil.mutListener.listen(28676)) {
                    adapterCount = mAdapter.getCount();
                }
                if ((ListenerUtil.mutListener.listen(28681) ? (adjPosition >= adapterCount) : (ListenerUtil.mutListener.listen(28680) ? (adjPosition <= adapterCount) : (ListenerUtil.mutListener.listen(28679) ? (adjPosition > adapterCount) : (ListenerUtil.mutListener.listen(28678) ? (adjPosition != adapterCount) : (ListenerUtil.mutListener.listen(28677) ? (adjPosition == adapterCount) : (adjPosition < adapterCount))))))) {
                    return mAdapter.getItem(adjPosition);
                }
            }
            throw new ArrayIndexOutOfBoundsException(position);
        }

        @Override
        public long getItemId(int position) {
            int numHeadersAndPlaceholders = (ListenerUtil.mutListener.listen(28685) ? (getHeadersCount() % mNumColumns) : (ListenerUtil.mutListener.listen(28684) ? (getHeadersCount() / mNumColumns) : (ListenerUtil.mutListener.listen(28683) ? (getHeadersCount() - mNumColumns) : (ListenerUtil.mutListener.listen(28682) ? (getHeadersCount() + mNumColumns) : (getHeadersCount() * mNumColumns)))));
            if (!ListenerUtil.mutListener.listen(28702)) {
                if ((ListenerUtil.mutListener.listen(28691) ? (mAdapter != null || (ListenerUtil.mutListener.listen(28690) ? (position <= numHeadersAndPlaceholders) : (ListenerUtil.mutListener.listen(28689) ? (position > numHeadersAndPlaceholders) : (ListenerUtil.mutListener.listen(28688) ? (position < numHeadersAndPlaceholders) : (ListenerUtil.mutListener.listen(28687) ? (position != numHeadersAndPlaceholders) : (ListenerUtil.mutListener.listen(28686) ? (position == numHeadersAndPlaceholders) : (position >= numHeadersAndPlaceholders))))))) : (mAdapter != null && (ListenerUtil.mutListener.listen(28690) ? (position <= numHeadersAndPlaceholders) : (ListenerUtil.mutListener.listen(28689) ? (position > numHeadersAndPlaceholders) : (ListenerUtil.mutListener.listen(28688) ? (position < numHeadersAndPlaceholders) : (ListenerUtil.mutListener.listen(28687) ? (position != numHeadersAndPlaceholders) : (ListenerUtil.mutListener.listen(28686) ? (position == numHeadersAndPlaceholders) : (position >= numHeadersAndPlaceholders))))))))) {
                    int adjPosition = (ListenerUtil.mutListener.listen(28695) ? (position % numHeadersAndPlaceholders) : (ListenerUtil.mutListener.listen(28694) ? (position / numHeadersAndPlaceholders) : (ListenerUtil.mutListener.listen(28693) ? (position * numHeadersAndPlaceholders) : (ListenerUtil.mutListener.listen(28692) ? (position + numHeadersAndPlaceholders) : (position - numHeadersAndPlaceholders)))));
                    int adapterCount = mAdapter.getCount();
                    if (!ListenerUtil.mutListener.listen(28701)) {
                        if ((ListenerUtil.mutListener.listen(28700) ? (adjPosition >= adapterCount) : (ListenerUtil.mutListener.listen(28699) ? (adjPosition <= adapterCount) : (ListenerUtil.mutListener.listen(28698) ? (adjPosition > adapterCount) : (ListenerUtil.mutListener.listen(28697) ? (adjPosition != adapterCount) : (ListenerUtil.mutListener.listen(28696) ? (adjPosition == adapterCount) : (adjPosition < adapterCount))))))) {
                            return mAdapter.getItemId(adjPosition);
                        }
                    }
                }
            }
            return -1;
        }

        @Override
        public boolean hasStableIds() {
            if (!ListenerUtil.mutListener.listen(28703)) {
                if (mAdapter != null) {
                    return mAdapter.hasStableIds();
                }
            }
            return false;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // Header (negative positions will throw an ArrayIndexOutOfBoundsException)
            int numHeadersAndPlaceholders = (ListenerUtil.mutListener.listen(28707) ? (getHeadersCount() % mNumColumns) : (ListenerUtil.mutListener.listen(28706) ? (getHeadersCount() / mNumColumns) : (ListenerUtil.mutListener.listen(28705) ? (getHeadersCount() - mNumColumns) : (ListenerUtil.mutListener.listen(28704) ? (getHeadersCount() + mNumColumns) : (getHeadersCount() * mNumColumns)))));
            if ((ListenerUtil.mutListener.listen(28712) ? (position >= numHeadersAndPlaceholders) : (ListenerUtil.mutListener.listen(28711) ? (position <= numHeadersAndPlaceholders) : (ListenerUtil.mutListener.listen(28710) ? (position > numHeadersAndPlaceholders) : (ListenerUtil.mutListener.listen(28709) ? (position != numHeadersAndPlaceholders) : (ListenerUtil.mutListener.listen(28708) ? (position == numHeadersAndPlaceholders) : (position < numHeadersAndPlaceholders))))))) {
                View headerViewContainer = mHeaderViewInfos.get((ListenerUtil.mutListener.listen(28716) ? (position % mNumColumns) : (ListenerUtil.mutListener.listen(28715) ? (position * mNumColumns) : (ListenerUtil.mutListener.listen(28714) ? (position - mNumColumns) : (ListenerUtil.mutListener.listen(28713) ? (position + mNumColumns) : (position / mNumColumns)))))).viewContainer;
                if ((ListenerUtil.mutListener.listen(28725) ? ((ListenerUtil.mutListener.listen(28720) ? (position / mNumColumns) : (ListenerUtil.mutListener.listen(28719) ? (position * mNumColumns) : (ListenerUtil.mutListener.listen(28718) ? (position - mNumColumns) : (ListenerUtil.mutListener.listen(28717) ? (position + mNumColumns) : (position % mNumColumns))))) >= 0) : (ListenerUtil.mutListener.listen(28724) ? ((ListenerUtil.mutListener.listen(28720) ? (position / mNumColumns) : (ListenerUtil.mutListener.listen(28719) ? (position * mNumColumns) : (ListenerUtil.mutListener.listen(28718) ? (position - mNumColumns) : (ListenerUtil.mutListener.listen(28717) ? (position + mNumColumns) : (position % mNumColumns))))) <= 0) : (ListenerUtil.mutListener.listen(28723) ? ((ListenerUtil.mutListener.listen(28720) ? (position / mNumColumns) : (ListenerUtil.mutListener.listen(28719) ? (position * mNumColumns) : (ListenerUtil.mutListener.listen(28718) ? (position - mNumColumns) : (ListenerUtil.mutListener.listen(28717) ? (position + mNumColumns) : (position % mNumColumns))))) > 0) : (ListenerUtil.mutListener.listen(28722) ? ((ListenerUtil.mutListener.listen(28720) ? (position / mNumColumns) : (ListenerUtil.mutListener.listen(28719) ? (position * mNumColumns) : (ListenerUtil.mutListener.listen(28718) ? (position - mNumColumns) : (ListenerUtil.mutListener.listen(28717) ? (position + mNumColumns) : (position % mNumColumns))))) < 0) : (ListenerUtil.mutListener.listen(28721) ? ((ListenerUtil.mutListener.listen(28720) ? (position / mNumColumns) : (ListenerUtil.mutListener.listen(28719) ? (position * mNumColumns) : (ListenerUtil.mutListener.listen(28718) ? (position - mNumColumns) : (ListenerUtil.mutListener.listen(28717) ? (position + mNumColumns) : (position % mNumColumns))))) != 0) : ((ListenerUtil.mutListener.listen(28720) ? (position / mNumColumns) : (ListenerUtil.mutListener.listen(28719) ? (position * mNumColumns) : (ListenerUtil.mutListener.listen(28718) ? (position - mNumColumns) : (ListenerUtil.mutListener.listen(28717) ? (position + mNumColumns) : (position % mNumColumns))))) == 0))))))) {
                    return headerViewContainer;
                } else {
                    if (!ListenerUtil.mutListener.listen(28727)) {
                        if (convertView == null) {
                            if (!ListenerUtil.mutListener.listen(28726)) {
                                convertView = new View(parent.getContext());
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(28728)) {
                        // in a row to determine the height for the entire row.
                        convertView.setVisibility(View.INVISIBLE);
                    }
                    if (!ListenerUtil.mutListener.listen(28729)) {
                        convertView.setMinimumHeight(headerViewContainer.getHeight());
                    }
                    return convertView;
                }
            }
            // Adapter
            final int adjPosition = (ListenerUtil.mutListener.listen(28733) ? (position % numHeadersAndPlaceholders) : (ListenerUtil.mutListener.listen(28732) ? (position / numHeadersAndPlaceholders) : (ListenerUtil.mutListener.listen(28731) ? (position * numHeadersAndPlaceholders) : (ListenerUtil.mutListener.listen(28730) ? (position + numHeadersAndPlaceholders) : (position - numHeadersAndPlaceholders)))));
            int adapterCount = 0;
            if (mAdapter != null) {
                if (!ListenerUtil.mutListener.listen(28734)) {
                    adapterCount = mAdapter.getCount();
                }
                if ((ListenerUtil.mutListener.listen(28739) ? (adjPosition >= adapterCount) : (ListenerUtil.mutListener.listen(28738) ? (adjPosition <= adapterCount) : (ListenerUtil.mutListener.listen(28737) ? (adjPosition > adapterCount) : (ListenerUtil.mutListener.listen(28736) ? (adjPosition != adapterCount) : (ListenerUtil.mutListener.listen(28735) ? (adjPosition == adapterCount) : (adjPosition < adapterCount))))))) {
                    return mAdapter.getView(adjPosition, convertView, parent);
                }
            }
            throw new ArrayIndexOutOfBoundsException(position);
        }

        @Override
        public int getItemViewType(int position) {
            int numHeadersAndPlaceholders = (ListenerUtil.mutListener.listen(28743) ? (getHeadersCount() % mNumColumns) : (ListenerUtil.mutListener.listen(28742) ? (getHeadersCount() / mNumColumns) : (ListenerUtil.mutListener.listen(28741) ? (getHeadersCount() - mNumColumns) : (ListenerUtil.mutListener.listen(28740) ? (getHeadersCount() + mNumColumns) : (getHeadersCount() * mNumColumns)))));
            if (!ListenerUtil.mutListener.listen(28759)) {
                if ((ListenerUtil.mutListener.listen(28758) ? ((ListenerUtil.mutListener.listen(28748) ? (position >= numHeadersAndPlaceholders) : (ListenerUtil.mutListener.listen(28747) ? (position <= numHeadersAndPlaceholders) : (ListenerUtil.mutListener.listen(28746) ? (position > numHeadersAndPlaceholders) : (ListenerUtil.mutListener.listen(28745) ? (position != numHeadersAndPlaceholders) : (ListenerUtil.mutListener.listen(28744) ? (position == numHeadersAndPlaceholders) : (position < numHeadersAndPlaceholders)))))) || ((ListenerUtil.mutListener.listen(28757) ? ((ListenerUtil.mutListener.listen(28752) ? (position / mNumColumns) : (ListenerUtil.mutListener.listen(28751) ? (position * mNumColumns) : (ListenerUtil.mutListener.listen(28750) ? (position - mNumColumns) : (ListenerUtil.mutListener.listen(28749) ? (position + mNumColumns) : (position % mNumColumns))))) >= 0) : (ListenerUtil.mutListener.listen(28756) ? ((ListenerUtil.mutListener.listen(28752) ? (position / mNumColumns) : (ListenerUtil.mutListener.listen(28751) ? (position * mNumColumns) : (ListenerUtil.mutListener.listen(28750) ? (position - mNumColumns) : (ListenerUtil.mutListener.listen(28749) ? (position + mNumColumns) : (position % mNumColumns))))) <= 0) : (ListenerUtil.mutListener.listen(28755) ? ((ListenerUtil.mutListener.listen(28752) ? (position / mNumColumns) : (ListenerUtil.mutListener.listen(28751) ? (position * mNumColumns) : (ListenerUtil.mutListener.listen(28750) ? (position - mNumColumns) : (ListenerUtil.mutListener.listen(28749) ? (position + mNumColumns) : (position % mNumColumns))))) > 0) : (ListenerUtil.mutListener.listen(28754) ? ((ListenerUtil.mutListener.listen(28752) ? (position / mNumColumns) : (ListenerUtil.mutListener.listen(28751) ? (position * mNumColumns) : (ListenerUtil.mutListener.listen(28750) ? (position - mNumColumns) : (ListenerUtil.mutListener.listen(28749) ? (position + mNumColumns) : (position % mNumColumns))))) < 0) : (ListenerUtil.mutListener.listen(28753) ? ((ListenerUtil.mutListener.listen(28752) ? (position / mNumColumns) : (ListenerUtil.mutListener.listen(28751) ? (position * mNumColumns) : (ListenerUtil.mutListener.listen(28750) ? (position - mNumColumns) : (ListenerUtil.mutListener.listen(28749) ? (position + mNumColumns) : (position % mNumColumns))))) == 0) : ((ListenerUtil.mutListener.listen(28752) ? (position / mNumColumns) : (ListenerUtil.mutListener.listen(28751) ? (position * mNumColumns) : (ListenerUtil.mutListener.listen(28750) ? (position - mNumColumns) : (ListenerUtil.mutListener.listen(28749) ? (position + mNumColumns) : (position % mNumColumns))))) != 0)))))))) : ((ListenerUtil.mutListener.listen(28748) ? (position >= numHeadersAndPlaceholders) : (ListenerUtil.mutListener.listen(28747) ? (position <= numHeadersAndPlaceholders) : (ListenerUtil.mutListener.listen(28746) ? (position > numHeadersAndPlaceholders) : (ListenerUtil.mutListener.listen(28745) ? (position != numHeadersAndPlaceholders) : (ListenerUtil.mutListener.listen(28744) ? (position == numHeadersAndPlaceholders) : (position < numHeadersAndPlaceholders)))))) && ((ListenerUtil.mutListener.listen(28757) ? ((ListenerUtil.mutListener.listen(28752) ? (position / mNumColumns) : (ListenerUtil.mutListener.listen(28751) ? (position * mNumColumns) : (ListenerUtil.mutListener.listen(28750) ? (position - mNumColumns) : (ListenerUtil.mutListener.listen(28749) ? (position + mNumColumns) : (position % mNumColumns))))) >= 0) : (ListenerUtil.mutListener.listen(28756) ? ((ListenerUtil.mutListener.listen(28752) ? (position / mNumColumns) : (ListenerUtil.mutListener.listen(28751) ? (position * mNumColumns) : (ListenerUtil.mutListener.listen(28750) ? (position - mNumColumns) : (ListenerUtil.mutListener.listen(28749) ? (position + mNumColumns) : (position % mNumColumns))))) <= 0) : (ListenerUtil.mutListener.listen(28755) ? ((ListenerUtil.mutListener.listen(28752) ? (position / mNumColumns) : (ListenerUtil.mutListener.listen(28751) ? (position * mNumColumns) : (ListenerUtil.mutListener.listen(28750) ? (position - mNumColumns) : (ListenerUtil.mutListener.listen(28749) ? (position + mNumColumns) : (position % mNumColumns))))) > 0) : (ListenerUtil.mutListener.listen(28754) ? ((ListenerUtil.mutListener.listen(28752) ? (position / mNumColumns) : (ListenerUtil.mutListener.listen(28751) ? (position * mNumColumns) : (ListenerUtil.mutListener.listen(28750) ? (position - mNumColumns) : (ListenerUtil.mutListener.listen(28749) ? (position + mNumColumns) : (position % mNumColumns))))) < 0) : (ListenerUtil.mutListener.listen(28753) ? ((ListenerUtil.mutListener.listen(28752) ? (position / mNumColumns) : (ListenerUtil.mutListener.listen(28751) ? (position * mNumColumns) : (ListenerUtil.mutListener.listen(28750) ? (position - mNumColumns) : (ListenerUtil.mutListener.listen(28749) ? (position + mNumColumns) : (position % mNumColumns))))) == 0) : ((ListenerUtil.mutListener.listen(28752) ? (position / mNumColumns) : (ListenerUtil.mutListener.listen(28751) ? (position * mNumColumns) : (ListenerUtil.mutListener.listen(28750) ? (position - mNumColumns) : (ListenerUtil.mutListener.listen(28749) ? (position + mNumColumns) : (position % mNumColumns))))) != 0)))))))))) {
                    // Placeholders get the last view type number
                    return mAdapter != null ? mAdapter.getViewTypeCount() : 1;
                }
            }
            if (!ListenerUtil.mutListener.listen(28776)) {
                if ((ListenerUtil.mutListener.listen(28765) ? (mAdapter != null || (ListenerUtil.mutListener.listen(28764) ? (position <= numHeadersAndPlaceholders) : (ListenerUtil.mutListener.listen(28763) ? (position > numHeadersAndPlaceholders) : (ListenerUtil.mutListener.listen(28762) ? (position < numHeadersAndPlaceholders) : (ListenerUtil.mutListener.listen(28761) ? (position != numHeadersAndPlaceholders) : (ListenerUtil.mutListener.listen(28760) ? (position == numHeadersAndPlaceholders) : (position >= numHeadersAndPlaceholders))))))) : (mAdapter != null && (ListenerUtil.mutListener.listen(28764) ? (position <= numHeadersAndPlaceholders) : (ListenerUtil.mutListener.listen(28763) ? (position > numHeadersAndPlaceholders) : (ListenerUtil.mutListener.listen(28762) ? (position < numHeadersAndPlaceholders) : (ListenerUtil.mutListener.listen(28761) ? (position != numHeadersAndPlaceholders) : (ListenerUtil.mutListener.listen(28760) ? (position == numHeadersAndPlaceholders) : (position >= numHeadersAndPlaceholders))))))))) {
                    int adjPosition = (ListenerUtil.mutListener.listen(28769) ? (position % numHeadersAndPlaceholders) : (ListenerUtil.mutListener.listen(28768) ? (position / numHeadersAndPlaceholders) : (ListenerUtil.mutListener.listen(28767) ? (position * numHeadersAndPlaceholders) : (ListenerUtil.mutListener.listen(28766) ? (position + numHeadersAndPlaceholders) : (position - numHeadersAndPlaceholders)))));
                    int adapterCount = mAdapter.getCount();
                    if (!ListenerUtil.mutListener.listen(28775)) {
                        if ((ListenerUtil.mutListener.listen(28774) ? (adjPosition >= adapterCount) : (ListenerUtil.mutListener.listen(28773) ? (adjPosition <= adapterCount) : (ListenerUtil.mutListener.listen(28772) ? (adjPosition > adapterCount) : (ListenerUtil.mutListener.listen(28771) ? (adjPosition != adapterCount) : (ListenerUtil.mutListener.listen(28770) ? (adjPosition == adapterCount) : (adjPosition < adapterCount))))))) {
                            return mAdapter.getItemViewType(adjPosition);
                        }
                    }
                }
            }
            return AdapterView.ITEM_VIEW_TYPE_HEADER_OR_FOOTER;
        }

        @Override
        public int getViewTypeCount() {
            if (!ListenerUtil.mutListener.listen(28777)) {
                if (mAdapter != null) {
                    return mAdapter.getViewTypeCount() + 1;
                }
            }
            return 2;
        }

        @Override
        public void registerDataSetObserver(DataSetObserver observer) {
            if (!ListenerUtil.mutListener.listen(28778)) {
                mDataSetObservable.registerObserver(observer);
            }
            if (!ListenerUtil.mutListener.listen(28780)) {
                if (mAdapter != null) {
                    if (!ListenerUtil.mutListener.listen(28779)) {
                        mAdapter.registerDataSetObserver(observer);
                    }
                }
            }
        }

        @Override
        public void unregisterDataSetObserver(DataSetObserver observer) {
            if (!ListenerUtil.mutListener.listen(28781)) {
                mDataSetObservable.unregisterObserver(observer);
            }
            if (!ListenerUtil.mutListener.listen(28783)) {
                if (mAdapter != null) {
                    if (!ListenerUtil.mutListener.listen(28782)) {
                        mAdapter.unregisterDataSetObserver(observer);
                    }
                }
            }
        }

        @Override
        public Filter getFilter() {
            if (!ListenerUtil.mutListener.listen(28784)) {
                if (mIsFilterable) {
                    return ((Filterable) mAdapter).getFilter();
                }
            }
            return null;
        }

        @Override
        public ListAdapter getWrappedAdapter() {
            return mAdapter;
        }

        public void notifyDataSetChanged() {
            if (!ListenerUtil.mutListener.listen(28785)) {
                mDataSetObservable.notifyChanged();
            }
        }
    }
}
