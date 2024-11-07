/*
 * Copyright (C) 2011-2016 The Android Open Source Project, University of South Florida
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
package org.onebusaway.android.ui;

import org.onebusaway.android.R;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * This is copied from the support package that allows us to use our own IDs,
 * because being able to specify our own custom view and still use all of this
 * good switching code is helpful.
 */
public class ListFragment extends Fragment {

    private final Handler mHandler = new Handler();

    private final Runnable mRequestFocus = new Runnable() {

        public void run() {
            if (!ListenerUtil.mutListener.listen(3126)) {
                mList.focusableViewAvailable(mList);
            }
        }
    };

    private final AdapterView.OnItemClickListener mOnClickListener = new AdapterView.OnItemClickListener() {

        public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
            if (!ListenerUtil.mutListener.listen(3127)) {
                onListItemClick((ListView) parent, v, position, id);
            }
        }
    };

    ListAdapter mAdapter;

    ListView mList;

    View mEmptyView;

    TextView mStandardEmptyView;

    View mProgressContainer;

    View mRefreshProgressContainer;

    View mListContainer;

    CharSequence mEmptyText;

    boolean mListShown;

    public ListFragment() {
    }

    /**
     * Provide default implementation to return a simple list view.  Subclasses
     * can override to replace with their own layout.  If doing so, the
     * returned view hierarchy <em>must</em> have a ListView whose id
     * is {@link android.R.id#list android.R.id.list} and can optionally
     * have a sibling view id {@link android.R.id#empty android.R.id.empty}
     * that is to be shown when the list is empty.
     *
     * <p>If you are overriding this method with your own custom content,
     * consider including the standard layout {@link android.R.layout#list_content}
     * in your layout file, so that you continue to retain all of the standard
     * behavior of ListFragment.  In particular, this is currently the only
     * way to have the built-in indeterminant progress state be shown.
     */
    @Override
    @SuppressWarnings("deprecation")
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final Context context = getActivity();
        FrameLayout root = new FrameLayout(context);
        LinearLayout pframe = new LinearLayout(context);
        if (!ListenerUtil.mutListener.listen(3128)) {
            pframe.setId(R.id.loading);
        }
        if (!ListenerUtil.mutListener.listen(3129)) {
            pframe.setOrientation(LinearLayout.VERTICAL);
        }
        if (!ListenerUtil.mutListener.listen(3130)) {
            pframe.setVisibility(View.GONE);
        }
        if (!ListenerUtil.mutListener.listen(3131)) {
            pframe.setGravity(Gravity.CENTER);
        }
        ProgressBar progress = new ProgressBar(context, null, android.R.attr.progressBarStyleLarge);
        if (!ListenerUtil.mutListener.listen(3132)) {
            pframe.addView(progress, new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        }
        if (!ListenerUtil.mutListener.listen(3133)) {
            root.addView(pframe, new FrameLayout.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT));
        }
        FrameLayout lframe = new FrameLayout(context);
        if (!ListenerUtil.mutListener.listen(3134)) {
            lframe.setId(R.id.listContainer);
        }
        TextView tv = new TextView(getActivity());
        if (!ListenerUtil.mutListener.listen(3135)) {
            tv.setId(R.id.internalEmpty);
        }
        if (!ListenerUtil.mutListener.listen(3136)) {
            tv.setGravity(Gravity.CENTER);
        }
        if (!ListenerUtil.mutListener.listen(3137)) {
            lframe.addView(tv, new FrameLayout.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT));
        }
        ListView lv = new ListView(getActivity());
        if (!ListenerUtil.mutListener.listen(3138)) {
            lv.setId(android.R.id.list);
        }
        if (!ListenerUtil.mutListener.listen(3139)) {
            lv.setDrawSelectorOnTop(false);
        }
        if (!ListenerUtil.mutListener.listen(3140)) {
            lframe.addView(lv, new FrameLayout.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT));
        }
        if (!ListenerUtil.mutListener.listen(3141)) {
            root.addView(lframe, new FrameLayout.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT));
        }
        if (!ListenerUtil.mutListener.listen(3142)) {
            root.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT));
        }
        return root;
    }

    /**
     * Attach to list view once the view hierarchy has been created.
     */
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(3143)) {
            super.onViewCreated(view, savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(3144)) {
            ensureList();
        }
    }

    /**
     * Detach from list view.
     */
    @Override
    public void onDestroyView() {
        if (!ListenerUtil.mutListener.listen(3145)) {
            mHandler.removeCallbacks(mRequestFocus);
        }
        if (!ListenerUtil.mutListener.listen(3146)) {
            mList = null;
        }
        if (!ListenerUtil.mutListener.listen(3147)) {
            mListShown = false;
        }
        if (!ListenerUtil.mutListener.listen(3148)) {
            mEmptyView = mProgressContainer = mRefreshProgressContainer = mListContainer = null;
        }
        if (!ListenerUtil.mutListener.listen(3149)) {
            mStandardEmptyView = null;
        }
        if (!ListenerUtil.mutListener.listen(3150)) {
            super.onDestroyView();
        }
    }

    /**
     * This method will be called when an item in the list is selected.
     * Subclasses should override. Subclasses can call
     * getListView().getItemAtPosition(position) if they need to access the
     * data associated with the selected item.
     *
     * @param l        The ListView where the click happened
     * @param v        The view that was clicked within the ListView
     * @param position The position of the view in the list
     * @param id       The row id of the item that was clicked
     */
    public void onListItemClick(ListView l, View v, int position, long id) {
    }

    /**
     * Provide the cursor for the list view.
     */
    public void setListAdapter(ListAdapter adapter) {
        boolean hadAdapter = mAdapter != null;
        if (!ListenerUtil.mutListener.listen(3151)) {
            mAdapter = adapter;
        }
        if (!ListenerUtil.mutListener.listen(3156)) {
            if (mList != null) {
                if (!ListenerUtil.mutListener.listen(3152)) {
                    mList.setAdapter(adapter);
                }
                if (!ListenerUtil.mutListener.listen(3155)) {
                    if ((ListenerUtil.mutListener.listen(3153) ? (!mListShown || !hadAdapter) : (!mListShown && !hadAdapter))) {
                        if (!ListenerUtil.mutListener.listen(3154)) {
                            // adapter.  It is now time to show it.
                            setListShown(true, getView().getWindowToken() != null);
                        }
                    }
                }
            }
        }
    }

    /**
     * Set the currently selected list item to the specified
     * position with the adapter's data
     */
    public void setSelection(int position) {
        if (!ListenerUtil.mutListener.listen(3157)) {
            ensureList();
        }
        if (!ListenerUtil.mutListener.listen(3158)) {
            mList.setSelection(position);
        }
    }

    /**
     * Get the position of the currently selected list item.
     */
    public int getSelectedItemPosition() {
        if (!ListenerUtil.mutListener.listen(3159)) {
            ensureList();
        }
        return mList.getSelectedItemPosition();
    }

    /**
     * Get the cursor row ID of the currently selected list item.
     */
    public long getSelectedItemId() {
        if (!ListenerUtil.mutListener.listen(3160)) {
            ensureList();
        }
        return mList.getSelectedItemId();
    }

    /**
     * Get the activity's list view widget.
     */
    public ListView getListView() {
        if (!ListenerUtil.mutListener.listen(3161)) {
            ensureList();
        }
        return mList;
    }

    /**
     * The default content for a ListFragment has a TextView that can
     * be shown when the list is empty.  If you would like to have it
     * shown, call this method to supply the text it should use.
     */
    public void setEmptyText(CharSequence text) {
        if (!ListenerUtil.mutListener.listen(3162)) {
            ensureList();
        }
        if (!ListenerUtil.mutListener.listen(3163)) {
            if (mStandardEmptyView == null) {
                throw new IllegalStateException("Can't be used with a custom content view");
            }
        }
        if (!ListenerUtil.mutListener.listen(3164)) {
            mStandardEmptyView.setText(text);
        }
        if (!ListenerUtil.mutListener.listen(3166)) {
            if (mEmptyText == null) {
                if (!ListenerUtil.mutListener.listen(3165)) {
                    mList.setEmptyView(mStandardEmptyView);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(3167)) {
            mEmptyText = text;
        }
    }

    /**
     * Control whether the list is being displayed.  You can make it not
     * displayed if you are waiting for the initial data to show in it.  During
     * this time an indeterminant progress indicator will be shown instead.
     *
     * <p>Applications do not normally need to use this themselves.  The default
     * behavior of ListFragment is to start with the list not being shown, only
     * showing it once an adapter is given with {@link #setListAdapter(ListAdapter)}.
     * If the list at that point had not been shown, when it does get shown
     * it will be do without the user ever seeing the hidden state.
     *
     * @param shown If true, the list view is shown; if false, the progress
     *              indicator.  The initial value is true.
     */
    public void setListShown(boolean shown) {
        if (!ListenerUtil.mutListener.listen(3168)) {
            setListShown(shown, true);
        }
    }

    /**
     * Like {@link #setListShown(boolean)}, but no animation is used when
     * transitioning from the previous state.
     */
    public void setListShownNoAnimation(boolean shown) {
        if (!ListenerUtil.mutListener.listen(3169)) {
            setListShown(shown, false);
        }
    }

    /**
     * Control whether the list is being displayed.  You can make it not
     * displayed if you are waiting for the initial data to show in it.  During
     * this time an indeterminant progress indicator will be shown instead.
     *
     * @param shown   If true, the list view is shown; if false, the progress
     *                indicator.  The initial value is true.
     * @param animate If true, an animation will be used to transition to the
     *                new state.
     */
    private void setListShown(boolean shown, boolean animate) {
        if (!ListenerUtil.mutListener.listen(3170)) {
            ensureList();
        }
        if (!ListenerUtil.mutListener.listen(3171)) {
            if (mProgressContainer == null) {
                throw new IllegalStateException("Can't be used with a custom content view");
            }
        }
        if (!ListenerUtil.mutListener.listen(3172)) {
            if (mListShown == shown) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(3173)) {
            mListShown = shown;
        }
        if (!ListenerUtil.mutListener.listen(3188)) {
            if (shown) {
                if (!ListenerUtil.mutListener.listen(3185)) {
                    if (animate) {
                        if (!ListenerUtil.mutListener.listen(3183)) {
                            mProgressContainer.startAnimation(AnimationUtils.loadAnimation(getActivity(), android.R.anim.fade_out));
                        }
                        if (!ListenerUtil.mutListener.listen(3184)) {
                            mListContainer.startAnimation(AnimationUtils.loadAnimation(getActivity(), android.R.anim.fade_in));
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(3181)) {
                            mProgressContainer.clearAnimation();
                        }
                        if (!ListenerUtil.mutListener.listen(3182)) {
                            mListContainer.clearAnimation();
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(3186)) {
                    mProgressContainer.setVisibility(View.GONE);
                }
                if (!ListenerUtil.mutListener.listen(3187)) {
                    mListContainer.setVisibility(View.VISIBLE);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(3178)) {
                    if (animate) {
                        if (!ListenerUtil.mutListener.listen(3176)) {
                            mProgressContainer.startAnimation(AnimationUtils.loadAnimation(getActivity(), android.R.anim.fade_in));
                        }
                        if (!ListenerUtil.mutListener.listen(3177)) {
                            mListContainer.startAnimation(AnimationUtils.loadAnimation(getActivity(), android.R.anim.fade_out));
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(3174)) {
                            mProgressContainer.clearAnimation();
                        }
                        if (!ListenerUtil.mutListener.listen(3175)) {
                            mListContainer.clearAnimation();
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(3179)) {
                    mProgressContainer.setVisibility(View.VISIBLE);
                }
                if (!ListenerUtil.mutListener.listen(3180)) {
                    mListContainer.setVisibility(View.GONE);
                }
            }
        }
    }

    /**
     * Get the ListAdapter associated with this activity's ListView.
     */
    public ListAdapter getListAdapter() {
        return mAdapter;
    }

    private void ensureList() {
        if (!ListenerUtil.mutListener.listen(3189)) {
            if (mList != null) {
                return;
            }
        }
        View root = getView();
        if (!ListenerUtil.mutListener.listen(3190)) {
            if (root == null) {
                throw new IllegalStateException("Content view not yet created");
            }
        }
        if (!ListenerUtil.mutListener.listen(3209)) {
            if (root instanceof ListView) {
                if (!ListenerUtil.mutListener.listen(3208)) {
                    mList = (ListView) root;
                }
            } else {
                if (!ListenerUtil.mutListener.listen(3191)) {
                    mStandardEmptyView = (TextView) root.findViewById(R.id.internalEmpty);
                }
                if (!ListenerUtil.mutListener.listen(3194)) {
                    if (mStandardEmptyView == null) {
                        if (!ListenerUtil.mutListener.listen(3193)) {
                            mEmptyView = root.findViewById(android.R.id.empty);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(3192)) {
                            mStandardEmptyView.setVisibility(View.GONE);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(3195)) {
                    mProgressContainer = root.findViewById(R.id.loading);
                }
                if (!ListenerUtil.mutListener.listen(3196)) {
                    mRefreshProgressContainer = root.findViewById(R.id.refresh_loading);
                }
                // Set %50 transparency to progress bar
                ProgressBar progressBar = (ProgressBar) root.findViewById(R.id.refresh_progress_small);
                if (!ListenerUtil.mutListener.listen(3199)) {
                    if ((ListenerUtil.mutListener.listen(3197) ? (progressBar != null || progressBar.getIndeterminateDrawable() != null) : (progressBar != null && progressBar.getIndeterminateDrawable() != null))) {
                        if (!ListenerUtil.mutListener.listen(3198)) {
                            progressBar.getIndeterminateDrawable().setAlpha(128);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(3200)) {
                    mListContainer = root.findViewById(R.id.listContainer);
                }
                View rawListView = root.findViewById(android.R.id.list);
                if (!ListenerUtil.mutListener.listen(3202)) {
                    if (!(rawListView instanceof ListView)) {
                        if (!ListenerUtil.mutListener.listen(3201)) {
                            if (rawListView == null) {
                                throw new RuntimeException("Your content must have a ListView whose id attribute is " + "'android.R.id.list'");
                            }
                        }
                        throw new RuntimeException("Content has view with id attribute 'android.R.id.list' " + "that is not a ListView class");
                    }
                }
                if (!ListenerUtil.mutListener.listen(3203)) {
                    mList = (ListView) rawListView;
                }
                if (!ListenerUtil.mutListener.listen(3207)) {
                    if (mEmptyView != null) {
                        if (!ListenerUtil.mutListener.listen(3206)) {
                            mList.setEmptyView(mEmptyView);
                        }
                    } else if (mEmptyText != null) {
                        if (!ListenerUtil.mutListener.listen(3204)) {
                            mStandardEmptyView.setText(mEmptyText);
                        }
                        if (!ListenerUtil.mutListener.listen(3205)) {
                            mList.setEmptyView(mStandardEmptyView);
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(3210)) {
            mListShown = true;
        }
        if (!ListenerUtil.mutListener.listen(3211)) {
            mList.setOnItemClickListener(mOnClickListener);
        }
        if (!ListenerUtil.mutListener.listen(3216)) {
            if (mAdapter != null) {
                ListAdapter adapter = mAdapter;
                if (!ListenerUtil.mutListener.listen(3214)) {
                    mAdapter = null;
                }
                if (!ListenerUtil.mutListener.listen(3215)) {
                    setListAdapter(adapter);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(3213)) {
                    // have our data right away and start with the progress indicator.
                    if (mProgressContainer != null) {
                        if (!ListenerUtil.mutListener.listen(3212)) {
                            setListShown(false, false);
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(3217)) {
            mHandler.post(mRequestFocus);
        }
    }

    protected void showProgress(boolean visibility) {
        if (!ListenerUtil.mutListener.listen(3218)) {
            if (mRefreshProgressContainer == null)
                return;
        }
        if (!ListenerUtil.mutListener.listen(3221)) {
            if (visibility) {
                if (!ListenerUtil.mutListener.listen(3220)) {
                    mRefreshProgressContainer.setVisibility(View.VISIBLE);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(3219)) {
                    mRefreshProgressContainer.setVisibility(View.GONE);
                }
            }
        }
    }
}
