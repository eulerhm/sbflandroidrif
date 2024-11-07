/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
 * Copyright (c) 2018-2021 Threema GmbH
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License, version 3,
 * as published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program. If not, see <https://www.gnu.org/licenses/>.
 */
package ch.threema.app.preference;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.List;
import androidx.annotation.IdRes;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.XmlRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Lifecycle.State;
import androidx.preference.Preference;
import ch.threema.app.R;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * @author [大前良介 (OHMAE Ryosuke)](mailto:ryo@mm2d.net)
 */
public class PreferenceActivityCompatDelegate {

    public interface Connector {

        void onBuildHeaders(@NonNull List<Header> target);

        boolean onIsMultiPane();

        boolean isValidFragment(@Nullable String fragmentName);
    }

    public static final long HEADER_ID_UNDEFINED = -1;

    private static final String HEADERS_TAG = ":android:headers";

    private static final String CUR_HEADER_TAG = ":android:cur_header";

    private static final String BACK_STACK_PREFS = ":android:prefs";

    @NonNull
    private final FragmentActivity mActivity;

    @NonNull
    private final Connector mConnector;

    @NonNull
    private final OnItemClickListener mOnClickListener = (parent, view, position, id) -> onListItemClick(position);

    @NonNull
    private final ArrayList<Header> mHeaders = new ArrayList<>();

    private ListAdapter mAdapter;

    private ListView mList;

    private boolean mFinishedStart = false;

    private FrameLayout mListFooter;

    private ViewGroup mPrefsContainer;

    private ViewGroup mHeadersContainer;

    private boolean mSinglePane;

    private Header mCurHeader;

    private final Handler mHandler = new Handler();

    private Fragment mFragment;

    private final Runnable mRequestFocus = new Runnable() {

        public void run() {
            if (!ListenerUtil.mutListener.listen(31865)) {
                mList.focusableViewAvailable(mList);
            }
        }
    };

    private final Runnable mBuildHeaders = new Runnable() {

        @Override
        public void run() {
            if (!ListenerUtil.mutListener.listen(31866)) {
                mHeaders.clear();
            }
            if (!ListenerUtil.mutListener.listen(31867)) {
                mConnector.onBuildHeaders(mHeaders);
            }
            if (!ListenerUtil.mutListener.listen(31869)) {
                if (mAdapter instanceof BaseAdapter) {
                    if (!ListenerUtil.mutListener.listen(31868)) {
                        ((BaseAdapter) mAdapter).notifyDataSetChanged();
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(31872)) {
                if (mCurHeader != null) {
                    final Header mappedHeader = findBestMatchingHeader(mCurHeader, mHeaders);
                    if (!ListenerUtil.mutListener.listen(31871)) {
                        if (mappedHeader != null) {
                            if (!ListenerUtil.mutListener.listen(31870)) {
                                setSelectedHeader(mappedHeader);
                            }
                        }
                    }
                }
            }
        }
    };

    public PreferenceActivityCompatDelegate(@NonNull final FragmentActivity activity, @NonNull final Connector connector) {
        mActivity = activity;
        mConnector = connector;
    }

    @NonNull
    private Context getContext() {
        return mActivity;
    }

    @NonNull
    private Resources getResources() {
        return mActivity.getResources();
    }

    private boolean isResumed() {
        return mActivity.getLifecycle().getCurrentState() == State.RESUMED;
    }

    @NonNull
    private FragmentManager getFragmentManager() {
        return mActivity.getSupportFragmentManager();
    }

    @Nullable
    private CharSequence getTitle() {
        return mActivity.getTitle();
    }

    private void setTitle(@Nullable final CharSequence title) {
        if (!ListenerUtil.mutListener.listen(31873)) {
            mActivity.setTitle(title);
        }
    }

    private void setContentView(@LayoutRes final int layoutResID) {
        if (!ListenerUtil.mutListener.listen(31874)) {
            mActivity.setContentView(layoutResID);
        }
    }

    @Nullable
    private <T extends View> T findViewById(@IdRes final int id) {
        return mActivity.findViewById(id);
    }

    public void onCreate(@Nullable final Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(31875)) {
            setContentView(R.layout.pref_content);
        }
        if (!ListenerUtil.mutListener.listen(31876)) {
            mList = findViewById(R.id.list);
        }
        if (!ListenerUtil.mutListener.listen(31877)) {
            mList.setOnItemClickListener(mOnClickListener);
        }
        if (!ListenerUtil.mutListener.listen(31879)) {
            if (mFinishedStart) {
                if (!ListenerUtil.mutListener.listen(31878)) {
                    setListAdapter(mAdapter);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(31880)) {
            mHandler.post(mRequestFocus);
        }
        if (!ListenerUtil.mutListener.listen(31881)) {
            mFinishedStart = true;
        }
        if (!ListenerUtil.mutListener.listen(31882)) {
            mListFooter = findViewById(R.id.list_footer);
        }
        if (!ListenerUtil.mutListener.listen(31883)) {
            mPrefsContainer = findViewById(R.id.prefs_frame);
        }
        if (!ListenerUtil.mutListener.listen(31884)) {
            mHeadersContainer = findViewById(R.id.headers);
        }
        if (!ListenerUtil.mutListener.listen(31885)) {
            mSinglePane = !mConnector.onIsMultiPane();
        }
        if (!ListenerUtil.mutListener.listen(31912)) {
            if (savedInstanceState != null) {
                final ArrayList<Header> headers = savedInstanceState.getParcelableArrayList(HEADERS_TAG);
                if (!ListenerUtil.mutListener.listen(31911)) {
                    if (headers != null) {
                        if (!ListenerUtil.mutListener.listen(31896)) {
                            mHeaders.addAll(headers);
                        }
                        final int curHeader = savedInstanceState.getInt(CUR_HEADER_TAG, (int) HEADER_ID_UNDEFINED);
                        if (!ListenerUtil.mutListener.listen(31910)) {
                            if ((ListenerUtil.mutListener.listen(31907) ? ((ListenerUtil.mutListener.listen(31901) ? (curHeader <= 0) : (ListenerUtil.mutListener.listen(31900) ? (curHeader > 0) : (ListenerUtil.mutListener.listen(31899) ? (curHeader < 0) : (ListenerUtil.mutListener.listen(31898) ? (curHeader != 0) : (ListenerUtil.mutListener.listen(31897) ? (curHeader == 0) : (curHeader >= 0)))))) || (ListenerUtil.mutListener.listen(31906) ? (curHeader >= mHeaders.size()) : (ListenerUtil.mutListener.listen(31905) ? (curHeader <= mHeaders.size()) : (ListenerUtil.mutListener.listen(31904) ? (curHeader > mHeaders.size()) : (ListenerUtil.mutListener.listen(31903) ? (curHeader != mHeaders.size()) : (ListenerUtil.mutListener.listen(31902) ? (curHeader == mHeaders.size()) : (curHeader < mHeaders.size()))))))) : ((ListenerUtil.mutListener.listen(31901) ? (curHeader <= 0) : (ListenerUtil.mutListener.listen(31900) ? (curHeader > 0) : (ListenerUtil.mutListener.listen(31899) ? (curHeader < 0) : (ListenerUtil.mutListener.listen(31898) ? (curHeader != 0) : (ListenerUtil.mutListener.listen(31897) ? (curHeader == 0) : (curHeader >= 0)))))) && (ListenerUtil.mutListener.listen(31906) ? (curHeader >= mHeaders.size()) : (ListenerUtil.mutListener.listen(31905) ? (curHeader <= mHeaders.size()) : (ListenerUtil.mutListener.listen(31904) ? (curHeader > mHeaders.size()) : (ListenerUtil.mutListener.listen(31903) ? (curHeader != mHeaders.size()) : (ListenerUtil.mutListener.listen(31902) ? (curHeader == mHeaders.size()) : (curHeader < mHeaders.size()))))))))) {
                                if (!ListenerUtil.mutListener.listen(31909)) {
                                    setSelectedHeader(mHeaders.get(curHeader));
                                }
                            } else if (!mSinglePane) {
                                if (!ListenerUtil.mutListener.listen(31908)) {
                                    switchToHeader(onGetInitialHeader());
                                }
                            }
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(31895)) {
                            showBreadCrumbs(getTitle());
                        }
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(31886)) {
                    mConnector.onBuildHeaders(mHeaders);
                }
                if (!ListenerUtil.mutListener.listen(31894)) {
                    if ((ListenerUtil.mutListener.listen(31892) ? (!mSinglePane || (ListenerUtil.mutListener.listen(31891) ? (mHeaders.size() >= 0) : (ListenerUtil.mutListener.listen(31890) ? (mHeaders.size() <= 0) : (ListenerUtil.mutListener.listen(31889) ? (mHeaders.size() < 0) : (ListenerUtil.mutListener.listen(31888) ? (mHeaders.size() != 0) : (ListenerUtil.mutListener.listen(31887) ? (mHeaders.size() == 0) : (mHeaders.size() > 0))))))) : (!mSinglePane && (ListenerUtil.mutListener.listen(31891) ? (mHeaders.size() >= 0) : (ListenerUtil.mutListener.listen(31890) ? (mHeaders.size() <= 0) : (ListenerUtil.mutListener.listen(31889) ? (mHeaders.size() < 0) : (ListenerUtil.mutListener.listen(31888) ? (mHeaders.size() != 0) : (ListenerUtil.mutListener.listen(31887) ? (mHeaders.size() == 0) : (mHeaders.size() > 0))))))))) {
                        if (!ListenerUtil.mutListener.listen(31893)) {
                            switchToHeader(onGetInitialHeader());
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(31921)) {
            if ((ListenerUtil.mutListener.listen(31917) ? (mHeaders.size() >= 0) : (ListenerUtil.mutListener.listen(31916) ? (mHeaders.size() <= 0) : (ListenerUtil.mutListener.listen(31915) ? (mHeaders.size() < 0) : (ListenerUtil.mutListener.listen(31914) ? (mHeaders.size() != 0) : (ListenerUtil.mutListener.listen(31913) ? (mHeaders.size() == 0) : (mHeaders.size() > 0))))))) {
                if (!ListenerUtil.mutListener.listen(31918)) {
                    setListAdapter(new HeaderAdapter(getContext(), mHeaders));
                }
                if (!ListenerUtil.mutListener.listen(31920)) {
                    if (!mSinglePane) {
                        if (!ListenerUtil.mutListener.listen(31919)) {
                            mList.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(31932)) {
            if (mSinglePane) {
                if (!ListenerUtil.mutListener.listen(31931)) {
                    if (mCurHeader != null) {
                        if (!ListenerUtil.mutListener.listen(31930)) {
                            mHeadersContainer.setVisibility(View.GONE);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(31929)) {
                            mPrefsContainer.setVisibility(View.GONE);
                        }
                    }
                }
            } else if ((ListenerUtil.mutListener.listen(31927) ? ((ListenerUtil.mutListener.listen(31926) ? (mHeaders.size() >= 0) : (ListenerUtil.mutListener.listen(31925) ? (mHeaders.size() <= 0) : (ListenerUtil.mutListener.listen(31924) ? (mHeaders.size() < 0) : (ListenerUtil.mutListener.listen(31923) ? (mHeaders.size() != 0) : (ListenerUtil.mutListener.listen(31922) ? (mHeaders.size() == 0) : (mHeaders.size() > 0)))))) || mCurHeader != null) : ((ListenerUtil.mutListener.listen(31926) ? (mHeaders.size() >= 0) : (ListenerUtil.mutListener.listen(31925) ? (mHeaders.size() <= 0) : (ListenerUtil.mutListener.listen(31924) ? (mHeaders.size() < 0) : (ListenerUtil.mutListener.listen(31923) ? (mHeaders.size() != 0) : (ListenerUtil.mutListener.listen(31922) ? (mHeaders.size() == 0) : (mHeaders.size() > 0)))))) && mCurHeader != null))) {
                if (!ListenerUtil.mutListener.listen(31928)) {
                    setSelectedHeader(mCurHeader);
                }
            }
        }
    }

    public void onDestroy() {
        if (!ListenerUtil.mutListener.listen(31933)) {
            mHandler.removeCallbacks(mBuildHeaders);
        }
        if (!ListenerUtil.mutListener.listen(31934)) {
            mHandler.removeCallbacks(mRequestFocus);
        }
    }

    public void onSaveInstanceState(@NonNull final Bundle outState) {
        if (!ListenerUtil.mutListener.listen(31949)) {
            if ((ListenerUtil.mutListener.listen(31939) ? (mHeaders.size() >= 0) : (ListenerUtil.mutListener.listen(31938) ? (mHeaders.size() <= 0) : (ListenerUtil.mutListener.listen(31937) ? (mHeaders.size() < 0) : (ListenerUtil.mutListener.listen(31936) ? (mHeaders.size() != 0) : (ListenerUtil.mutListener.listen(31935) ? (mHeaders.size() == 0) : (mHeaders.size() > 0))))))) {
                if (!ListenerUtil.mutListener.listen(31940)) {
                    outState.putParcelableArrayList(HEADERS_TAG, mHeaders);
                }
                if (!ListenerUtil.mutListener.listen(31948)) {
                    if (mCurHeader != null) {
                        final int index = mHeaders.indexOf(mCurHeader);
                        if (!ListenerUtil.mutListener.listen(31947)) {
                            if ((ListenerUtil.mutListener.listen(31945) ? (index <= 0) : (ListenerUtil.mutListener.listen(31944) ? (index > 0) : (ListenerUtil.mutListener.listen(31943) ? (index < 0) : (ListenerUtil.mutListener.listen(31942) ? (index != 0) : (ListenerUtil.mutListener.listen(31941) ? (index == 0) : (index >= 0))))))) {
                                if (!ListenerUtil.mutListener.listen(31946)) {
                                    outState.putInt(CUR_HEADER_TAG, index);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public void onRestoreInstanceState(@NonNull final Bundle state) {
        if (!ListenerUtil.mutListener.listen(31952)) {
            if (!mSinglePane) {
                if (!ListenerUtil.mutListener.listen(31951)) {
                    if (mCurHeader != null) {
                        if (!ListenerUtil.mutListener.listen(31950)) {
                            setSelectedHeader(mCurHeader);
                        }
                    }
                }
            }
        }
    }

    public boolean onBackPressed() {
        final FragmentManager manager = getFragmentManager();
        if (!ListenerUtil.mutListener.listen(31953)) {
            if (!mSinglePane) {
                return false;
            }
        }
        if (!ListenerUtil.mutListener.listen(31954)) {
            /*
		if (manager.getBackStackEntryCount() > 0) {
			manager.popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
		}
*/
            if (mCurHeader == null) {
                return false;
            }
        }
        if (!ListenerUtil.mutListener.listen(31957)) {
            if (mFragment != null) {
                if (!ListenerUtil.mutListener.listen(31955)) {
                    manager.beginTransaction().remove(mFragment).commitAllowingStateLoss();
                }
                if (!ListenerUtil.mutListener.listen(31956)) {
                    mFragment = null;
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(31958)) {
            mCurHeader = null;
        }
        if (!ListenerUtil.mutListener.listen(31959)) {
            mPrefsContainer.setVisibility(View.GONE);
        }
        if (!ListenerUtil.mutListener.listen(31960)) {
            mHeadersContainer.setVisibility(View.VISIBLE);
        }
        if (!ListenerUtil.mutListener.listen(31961)) {
            showBreadCrumbs(getTitle());
        }
        if (!ListenerUtil.mutListener.listen(31962)) {
            mList.clearChoices();
        }
        return true;
    }

    private void setListAdapter(final ListAdapter adapter) {
        if (!ListenerUtil.mutListener.listen(31963)) {
            mAdapter = adapter;
        }
        if (!ListenerUtil.mutListener.listen(31964)) {
            mList.setAdapter(adapter);
        }
    }

    public int getSelectedItemPosition() {
        return mList.getSelectedItemPosition();
    }

    public boolean hasHeaders() {
        return (ListenerUtil.mutListener.listen(31965) ? (mHeadersContainer != null || mHeadersContainer.getVisibility() == View.VISIBLE) : (mHeadersContainer != null && mHeadersContainer.getVisibility() == View.VISIBLE));
    }

    @NonNull
    public List<Header> getHeaders() {
        return mHeaders;
    }

    public boolean isMultiPane() {
        return !mSinglePane;
    }

    @NonNull
    private Header onGetInitialHeader() {
        {
            long _loopCounter221 = 0;
            for (int i = 0; (ListenerUtil.mutListener.listen(31970) ? (i >= mHeaders.size()) : (ListenerUtil.mutListener.listen(31969) ? (i <= mHeaders.size()) : (ListenerUtil.mutListener.listen(31968) ? (i > mHeaders.size()) : (ListenerUtil.mutListener.listen(31967) ? (i != mHeaders.size()) : (ListenerUtil.mutListener.listen(31966) ? (i == mHeaders.size()) : (i < mHeaders.size())))))); i++) {
                ListenerUtil.loopListener.listen("_loopCounter221", ++_loopCounter221);
                final Header h = mHeaders.get(i);
                if (h.fragment != null) {
                    return h;
                }
            }
        }
        throw new IllegalStateException("Must have at least one header with a fragment");
    }

    public void invalidateHeaders() {
        if (!ListenerUtil.mutListener.listen(31971)) {
            mHandler.removeCallbacks(mBuildHeaders);
        }
        if (!ListenerUtil.mutListener.listen(31972)) {
            mHandler.post(mBuildHeaders);
        }
    }

    public void loadHeadersFromResource(@XmlRes final int resId, @NonNull final List<Header> target) {
        if (!ListenerUtil.mutListener.listen(31973)) {
            HeaderLoader.loadFromResource(getContext(), resId, target);
        }
    }

    public void setListFooter(@NonNull final View view) {
        if (!ListenerUtil.mutListener.listen(31974)) {
            mListFooter.removeAllViews();
        }
        if (!ListenerUtil.mutListener.listen(31975)) {
            mListFooter.addView(view, new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT));
        }
    }

    private void onListItemClick(final int position) {
        if (!ListenerUtil.mutListener.listen(31976)) {
            if (!isResumed()) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(31979)) {
            if (mAdapter != null) {
                final Object item = mAdapter.getItem(position);
                if (!ListenerUtil.mutListener.listen(31978)) {
                    if (item instanceof Header)
                        if (!ListenerUtil.mutListener.listen(31977)) {
                            onHeaderClick((Header) item);
                        }
                }
            }
        }
    }

    private void onHeaderClick(@NonNull final Header header) {
        if (!ListenerUtil.mutListener.listen(31982)) {
            if (header.fragment != null) {
                if (!ListenerUtil.mutListener.listen(31981)) {
                    switchToHeader(header);
                }
            } else if (header.intent != null) {
                if (!ListenerUtil.mutListener.listen(31980)) {
                    getContext().startActivity(header.intent);
                }
            }
        }
    }

    public void switchToHeader(@NonNull final Header header) {
        if (!ListenerUtil.mutListener.listen(31986)) {
            if (mCurHeader == header) {
                if (!ListenerUtil.mutListener.listen(31985)) {
                    getFragmentManager().popBackStack(BACK_STACK_PREFS, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(31983)) {
                    if (header.fragment == null) {
                        throw new IllegalStateException("can't switch to header that has no fragment");
                    }
                }
                if (!ListenerUtil.mutListener.listen(31984)) {
                    mHandler.post(() -> {
                        switchToHeaderInner(header.fragment, header.fragmentArguments);
                        setSelectedHeader(header);
                    });
                }
            }
        }
    }

    private void switchToHeaderInner(@NonNull final String fragmentName, @Nullable final Bundle args) {
        final FragmentManager fragmentManager = getFragmentManager();
        if (!ListenerUtil.mutListener.listen(31987)) {
            fragmentManager.popBackStack(BACK_STACK_PREFS, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }
        if (!ListenerUtil.mutListener.listen(31988)) {
            if (!mConnector.isValidFragment(fragmentName)) {
                throw new IllegalArgumentException("Invalid fragment for this activity: " + fragmentName);
            }
        }
        if (!ListenerUtil.mutListener.listen(31989)) {
            mFragment = Fragment.instantiate(getContext(), fragmentName, args);
        }
        if (!ListenerUtil.mutListener.listen(31990)) {
            fragmentManager.beginTransaction().setTransition(FragmentTransaction.TRANSIT_NONE).replace(R.id.prefs, mFragment).commitAllowingStateLoss();
        }
        if (!ListenerUtil.mutListener.listen(31994)) {
            if ((ListenerUtil.mutListener.listen(31991) ? (mSinglePane || mPrefsContainer.getVisibility() == View.GONE) : (mSinglePane && mPrefsContainer.getVisibility() == View.GONE))) {
                if (!ListenerUtil.mutListener.listen(31992)) {
                    mPrefsContainer.setVisibility(View.VISIBLE);
                }
                if (!ListenerUtil.mutListener.listen(31993)) {
                    mHeadersContainer.setVisibility(View.GONE);
                }
            }
        }
    }

    private void setSelectedHeader(@NonNull final Header header) {
        if (!ListenerUtil.mutListener.listen(31995)) {
            mCurHeader = header;
        }
        final int index = mHeaders.indexOf(header);
        if (!ListenerUtil.mutListener.listen(32003)) {
            if ((ListenerUtil.mutListener.listen(32000) ? (index <= 0) : (ListenerUtil.mutListener.listen(31999) ? (index > 0) : (ListenerUtil.mutListener.listen(31998) ? (index < 0) : (ListenerUtil.mutListener.listen(31997) ? (index != 0) : (ListenerUtil.mutListener.listen(31996) ? (index == 0) : (index >= 0))))))) {
                if (!ListenerUtil.mutListener.listen(32002)) {
                    mList.setItemChecked(index, true);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(32001)) {
                    mList.clearChoices();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(32004)) {
            showBreadCrumbs(header);
        }
    }

    private void showBreadCrumbs(@NonNull final Header header) {
        final Resources resources = getResources();
        CharSequence title = header.getBreadCrumbTitle(resources);
        if (!ListenerUtil.mutListener.listen(32006)) {
            if (title == null)
                if (!ListenerUtil.mutListener.listen(32005)) {
                    title = header.getTitle(resources);
                }
        }
        if (!ListenerUtil.mutListener.listen(32008)) {
            if (title == null)
                if (!ListenerUtil.mutListener.listen(32007)) {
                    title = getTitle();
                }
        }
        if (!ListenerUtil.mutListener.listen(32009)) {
            showBreadCrumbs(title);
        }
    }

    private void showBreadCrumbs(@Nullable final CharSequence title) {
        if (!ListenerUtil.mutListener.listen(32010)) {
            setTitle(title);
        }
    }

    public void startPreferenceFragment(@NonNull final Preference pref) {
        final Fragment fragment = Fragment.instantiate(getContext(), pref.getFragment(), pref.getExtras());
        if (!ListenerUtil.mutListener.listen(32011)) {
            getFragmentManager().beginTransaction().replace(R.id.prefs, fragment).setBreadCrumbTitle(pref.getTitle()).setTransition(FragmentTransaction.TRANSIT_NONE).addToBackStack(BACK_STACK_PREFS).commitAllowingStateLoss();
        }
    }

    @Nullable
    private Header findBestMatchingHeader(@NonNull final Header current, @NonNull final ArrayList<Header> from) {
        final ArrayList<Header> matches = new ArrayList<>();
        if (!ListenerUtil.mutListener.listen(32024)) {
            {
                long _loopCounter222 = 0;
                for (final Header oh : from) {
                    ListenerUtil.loopListener.listen("_loopCounter222", ++_loopCounter222);
                    if (!ListenerUtil.mutListener.listen(32016)) {
                        if ((ListenerUtil.mutListener.listen(32013) ? (current == oh && ((ListenerUtil.mutListener.listen(32012) ? (current.id != HEADER_ID_UNDEFINED || current.id == oh.id) : (current.id != HEADER_ID_UNDEFINED && current.id == oh.id)))) : (current == oh || ((ListenerUtil.mutListener.listen(32012) ? (current.id != HEADER_ID_UNDEFINED || current.id == oh.id) : (current.id != HEADER_ID_UNDEFINED && current.id == oh.id)))))) {
                            if (!ListenerUtil.mutListener.listen(32014)) {
                                matches.clear();
                            }
                            if (!ListenerUtil.mutListener.listen(32015)) {
                                matches.add(oh);
                            }
                            break;
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(32023)) {
                        if (current.fragment != null) {
                            if (!ListenerUtil.mutListener.listen(32022)) {
                                if (current.fragment.equals(oh.fragment)) {
                                    if (!ListenerUtil.mutListener.listen(32021)) {
                                        matches.add(oh);
                                    }
                                }
                            }
                        } else if (current.intent != null) {
                            if (!ListenerUtil.mutListener.listen(32020)) {
                                if (current.intent.equals(oh.intent)) {
                                    if (!ListenerUtil.mutListener.listen(32019)) {
                                        matches.add(oh);
                                    }
                                }
                            }
                        } else if (current.title != null) {
                            if (!ListenerUtil.mutListener.listen(32018)) {
                                if (current.title.equals(oh.title)) {
                                    if (!ListenerUtil.mutListener.listen(32017)) {
                                        matches.add(oh);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(32025)) {
            if (matches.size() == 1) {
                return matches.get(0);
            }
        }
        if (!ListenerUtil.mutListener.listen(32032)) {
            {
                long _loopCounter223 = 0;
                for (final Header oh : matches) {
                    ListenerUtil.loopListener.listen("_loopCounter223", ++_loopCounter223);
                    if (!ListenerUtil.mutListener.listen(32027)) {
                        if ((ListenerUtil.mutListener.listen(32026) ? (current.fragmentArguments != null || current.fragmentArguments.equals(oh.fragmentArguments)) : (current.fragmentArguments != null && current.fragmentArguments.equals(oh.fragmentArguments)))) {
                            return oh;
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(32029)) {
                        if ((ListenerUtil.mutListener.listen(32028) ? (current.extras != null || current.extras.equals(oh.extras)) : (current.extras != null && current.extras.equals(oh.extras)))) {
                            return oh;
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(32031)) {
                        if ((ListenerUtil.mutListener.listen(32030) ? (current.title != null || current.title.equals(oh.title)) : (current.title != null && current.title.equals(oh.title)))) {
                            return oh;
                        }
                    }
                }
            }
        }
        return null;
    }
}
