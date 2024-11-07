/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
 * Copyright (c) 2013-2021 Threema GmbH
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
package ch.threema.app.activities;

import android.os.Bundle;
import android.util.SparseArray;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.StringRes;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import ch.threema.app.R;
import ch.threema.app.adapters.FilterableListAdapter;
import ch.threema.app.fragments.MemberListFragment;
import ch.threema.app.fragments.UserMemberListFragment;
import ch.threema.app.fragments.WorkUserMemberListFragment;
import ch.threema.app.services.ContactService;
import ch.threema.app.ui.ThreemaSearchView;
import ch.threema.app.utils.AnimationUtil;
import ch.threema.app.utils.ConfigUtils;
import ch.threema.app.utils.LogUtil;
import ch.threema.app.utils.NameUtil;
import ch.threema.app.utils.RuntimeUtil;
import ch.threema.app.utils.SnackbarUtil;
import ch.threema.storage.models.ContactModel;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public abstract class MemberChooseActivity extends ThreemaToolbarActivity implements SearchView.OnQueryTextListener, MemberListFragment.SelectionListener {

    private static final int FRAGMENT_USERS = 0;

    private static final int FRAGMENT_WORK_USERS = 1;

    private static final int NUM_FRAGMENTS = 2;

    private MemberChoosePagerAdapter memberChoosePagerAdapter;

    private MenuItem searchMenuItem;

    private ThreemaSearchView searchView;

    protected ContactService contactService;

    protected ArrayList<String> excludedIdentities = new ArrayList<>();

    protected ArrayList<String> preselectedIdentities = new ArrayList<>();

    private ViewPager viewPager;

    private ArrayList<Integer> tabs = new ArrayList<>(NUM_FRAGMENTS);

    private Snackbar snackbar;

    private View rootView;

    @Override
    public boolean onQueryTextSubmit(String query) {
        // Do something
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        int currentItem = viewPager.getCurrentItem();
        Fragment fragment = memberChoosePagerAdapter.getRegisteredFragment(currentItem);
        if (!ListenerUtil.mutListener.listen(4893)) {
            if (fragment != null) {
                FilterableListAdapter listAdapter = ((MemberListFragment) fragment).getAdapter();
                if (!ListenerUtil.mutListener.listen(4891)) {
                    // adapter can be null if it has not been initialized yet (runs in different thread)
                    if (listAdapter == null)
                        return false;
                }
                if (!ListenerUtil.mutListener.listen(4892)) {
                    listAdapter.getFilter().filter(newText);
                }
            }
        }
        return true;
    }

    public int getLayoutResource() {
        return R.layout.activity_member_choose_tabbed;
    }

    @Override
    protected boolean initActivity(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(4894)) {
            if (!super.initActivity(savedInstanceState)) {
                return false;
            }
        }
        ;
        // add notice, if desired
        ActionBar actionBar = getSupportActionBar();
        if (!ListenerUtil.mutListener.listen(4908)) {
            if (actionBar != null) {
                if (!ListenerUtil.mutListener.listen(4895)) {
                    actionBar.setDisplayHomeAsUpEnabled(true);
                }
                Toolbar toolbar = getToolbar();
                if (!ListenerUtil.mutListener.listen(4897)) {
                    if (toolbar != null) {
                        if (!ListenerUtil.mutListener.listen(4896)) {
                            actionBar.setTitle(null);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(4907)) {
                    if ((ListenerUtil.mutListener.listen(4902) ? (getNotice() >= 0) : (ListenerUtil.mutListener.listen(4901) ? (getNotice() <= 0) : (ListenerUtil.mutListener.listen(4900) ? (getNotice() > 0) : (ListenerUtil.mutListener.listen(4899) ? (getNotice() < 0) : (ListenerUtil.mutListener.listen(4898) ? (getNotice() == 0) : (getNotice() != 0))))))) {
                        final TextView noticeText = findViewById(R.id.notice_text);
                        final LinearLayout noticeLayout = findViewById(R.id.notice_layout);
                        if (!ListenerUtil.mutListener.listen(4903)) {
                            noticeText.setText(getNotice());
                        }
                        if (!ListenerUtil.mutListener.listen(4904)) {
                            noticeLayout.setVisibility(View.VISIBLE);
                        }
                        ImageView closeButton = findViewById(R.id.close_button);
                        if (!ListenerUtil.mutListener.listen(4906)) {
                            closeButton.setOnClickListener(new View.OnClickListener() {

                                @Override
                                public void onClick(View v) {
                                    if (!ListenerUtil.mutListener.listen(4905)) {
                                        AnimationUtil.collapse(noticeLayout);
                                    }
                                }
                            });
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(4909)) {
            this.rootView = findViewById(R.id.coordinator);
        }
        try {
            if (!ListenerUtil.mutListener.listen(4911)) {
                this.contactService = serviceManager.getContactService();
            }
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(4910)) {
                LogUtil.exception(e, this);
            }
            return false;
        }
        return true;
    }

    @MainThread
    protected void updateToolbarTitle(@StringRes int title, @StringRes int subtitle) {
        if (!ListenerUtil.mutListener.listen(4912)) {
            getToolbar().setTitle(title);
        }
        if (!ListenerUtil.mutListener.listen(4913)) {
            getToolbar().setSubtitle(subtitle);
        }
    }

    protected void initList() {
        final TabLayout tabLayout = findViewById(R.id.sliding_tabs);
        if (!ListenerUtil.mutListener.listen(4914)) {
            tabs.clear();
        }
        if (!ListenerUtil.mutListener.listen(4915)) {
            viewPager = findViewById(R.id.pager);
        }
        if (!ListenerUtil.mutListener.listen(4918)) {
            if ((ListenerUtil.mutListener.listen(4916) ? (viewPager == null && tabLayout == null) : (viewPager == null || tabLayout == null))) {
                if (!ListenerUtil.mutListener.listen(4917)) {
                    finish();
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(4919)) {
            tabLayout.clearOnTabSelectedListeners();
        }
        if (!ListenerUtil.mutListener.listen(4920)) {
            tabLayout.removeAllTabs();
        }
        if (!ListenerUtil.mutListener.listen(4921)) {
            viewPager.clearOnPageChangeListeners();
        }
        if (!ListenerUtil.mutListener.listen(4922)) {
            viewPager.setAdapter(null);
        }
        if (!ListenerUtil.mutListener.listen(4923)) {
            viewPager.removeAllViews();
        }
        if (!ListenerUtil.mutListener.listen(4926)) {
            if (ConfigUtils.isWorkBuild()) {
                if (!ListenerUtil.mutListener.listen(4924)) {
                    tabLayout.addTab(tabLayout.newTab().setIcon(ConfigUtils.getThemedDrawable(this, R.drawable.ic_work_outline)).setContentDescription(R.string.title_tab_work_users));
                }
                if (!ListenerUtil.mutListener.listen(4925)) {
                    tabs.add(FRAGMENT_WORK_USERS);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(4927)) {
            tabLayout.addTab(tabLayout.newTab().setIcon(ConfigUtils.getThemedDrawable(this, R.drawable.ic_person_outline)).setContentDescription(R.string.title_tab_users));
        }
        if (!ListenerUtil.mutListener.listen(4928)) {
            tabs.add(FRAGMENT_USERS);
        }
        if (!ListenerUtil.mutListener.listen(4929)) {
            // keeps inactive tabs from being destroyed causing all kinds of problems with lingering AsyncTasks on the the adapter
            viewPager.setVisibility(View.VISIBLE);
        }
        if (!ListenerUtil.mutListener.listen(4930)) {
            viewPager.setOffscreenPageLimit(1);
        }
        if (!ListenerUtil.mutListener.listen(4936)) {
            tabLayout.setVisibility((ListenerUtil.mutListener.listen(4935) ? (tabs.size() >= 1) : (ListenerUtil.mutListener.listen(4934) ? (tabs.size() <= 1) : (ListenerUtil.mutListener.listen(4933) ? (tabs.size() < 1) : (ListenerUtil.mutListener.listen(4932) ? (tabs.size() != 1) : (ListenerUtil.mutListener.listen(4931) ? (tabs.size() == 1) : (tabs.size() > 1)))))) ? View.VISIBLE : View.GONE);
        }
        if (!ListenerUtil.mutListener.listen(4937)) {
            tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        }
        if (!ListenerUtil.mutListener.listen(4938)) {
            memberChoosePagerAdapter = new MemberChoosePagerAdapter(getSupportFragmentManager());
        }
        if (!ListenerUtil.mutListener.listen(4939)) {
            viewPager.setAdapter(memberChoosePagerAdapter);
        }
        if (!ListenerUtil.mutListener.listen(4940)) {
            viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        }
        if (!ListenerUtil.mutListener.listen(4941)) {
            tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));
        }
        if (!ListenerUtil.mutListener.listen(4947)) {
            viewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {

                @Override
                public void onPageSelected(int position) {
                    if (!ListenerUtil.mutListener.listen(4945)) {
                        if (searchMenuItem != null) {
                            if (!ListenerUtil.mutListener.listen(4942)) {
                                searchMenuItem.collapseActionView();
                            }
                            if (!ListenerUtil.mutListener.listen(4944)) {
                                if (searchView != null) {
                                    if (!ListenerUtil.mutListener.listen(4943)) {
                                        searchView.setQuery("", false);
                                    }
                                }
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(4946)) {
                        invalidateOptionsMenu();
                    }
                }
            });
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!ListenerUtil.mutListener.listen(4948)) {
            super.onCreateOptionsMenu(menu);
        }
        if (!ListenerUtil.mutListener.listen(4949)) {
            // Inflate the menu; this adds items to the action bar if it is present.
            getMenuInflater().inflate(R.menu.activity_member_choose, menu);
        }
        if (!ListenerUtil.mutListener.listen(4951)) {
            if (!getAddNextButton()) {
                MenuItem checkItem = menu.findItem(R.id.menu_next);
                if (!ListenerUtil.mutListener.listen(4950)) {
                    checkItem.setVisible(false);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(4952)) {
            this.searchMenuItem = menu.findItem(R.id.menu_search_messages);
        }
        if (!ListenerUtil.mutListener.listen(4953)) {
            this.searchView = (ThreemaSearchView) this.searchMenuItem.getActionView();
        }
        if (!ListenerUtil.mutListener.listen(4957)) {
            if (this.searchView != null) {
                if (!ListenerUtil.mutListener.listen(4955)) {
                    this.searchView.setQueryHint(getString(R.string.hint_filter_list));
                }
                if (!ListenerUtil.mutListener.listen(4956)) {
                    this.searchView.setOnQueryTextListener(this);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(4954)) {
                    this.searchMenuItem.setVisible(false);
                }
            }
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (!ListenerUtil.mutListener.listen(4962)) {
            switch(item.getItemId()) {
                case android.R.id.home:
                    if (!ListenerUtil.mutListener.listen(4959)) {
                        if (getAddNextButton()) {
                            if (!ListenerUtil.mutListener.listen(4958)) {
                                finish();
                            }
                            return true;
                        }
                    }
                /* fallthrough */
                case R.id.menu_next:
                    if (!ListenerUtil.mutListener.listen(4961)) {
                        RuntimeUtil.runOnUiThread(new Runnable() {

                            @Override
                            public void run() {
                                if (!ListenerUtil.mutListener.listen(4960)) {
                                    menuNext(getSelectedContacts());
                                }
                            }
                        });
                    }
                    return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    protected List<ContactModel> getSelectedContacts() {
        Set<ContactModel> contacts = new HashSet<>();
        MemberListFragment fragment;
        {
            long _loopCounter33 = 0;
            for (int i = 0; (ListenerUtil.mutListener.listen(4969) ? (i >= NUM_FRAGMENTS) : (ListenerUtil.mutListener.listen(4968) ? (i <= NUM_FRAGMENTS) : (ListenerUtil.mutListener.listen(4967) ? (i > NUM_FRAGMENTS) : (ListenerUtil.mutListener.listen(4966) ? (i != NUM_FRAGMENTS) : (ListenerUtil.mutListener.listen(4965) ? (i == NUM_FRAGMENTS) : (i < NUM_FRAGMENTS)))))); i++) {
                ListenerUtil.loopListener.listen("_loopCounter33", ++_loopCounter33);
                fragment = (MemberListFragment) memberChoosePagerAdapter.getRegisteredFragment(i);
                if (!ListenerUtil.mutListener.listen(4964)) {
                    if (fragment != null) {
                        if (!ListenerUtil.mutListener.listen(4963)) {
                            contacts.addAll(fragment.getSelectedContacts());
                        }
                    }
                }
            }
        }
        return new ArrayList<>(contacts);
    }

    public class MemberChoosePagerAdapter extends FragmentPagerAdapter {

        // these globals are not persistent across orientation changes (at least in Android <= 4.1)!
        SparseArray<Fragment> registeredFragments = new SparseArray<Fragment>();

        public MemberChoosePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment = null;
            if (!ListenerUtil.mutListener.listen(4972)) {
                switch(tabs.get(position)) {
                    case FRAGMENT_USERS:
                        if (!ListenerUtil.mutListener.listen(4970)) {
                            fragment = new UserMemberListFragment();
                        }
                        break;
                    case FRAGMENT_WORK_USERS:
                        if (!ListenerUtil.mutListener.listen(4971)) {
                            fragment = new WorkUserMemberListFragment();
                        }
                        break;
                }
            }
            if (!ListenerUtil.mutListener.listen(4976)) {
                if (fragment != null) {
                    Bundle args = new Bundle();
                    if (!ListenerUtil.mutListener.listen(4973)) {
                        args.putStringArrayList(MemberListFragment.BUNDLE_ARG_EXCLUDED, excludedIdentities);
                    }
                    if (!ListenerUtil.mutListener.listen(4974)) {
                        args.putStringArrayList(MemberListFragment.BUNDLE_ARG_PRESELECTED, preselectedIdentities);
                    }
                    if (!ListenerUtil.mutListener.listen(4975)) {
                        fragment.setArguments(args);
                    }
                }
            }
            return fragment;
        }

        @Override
        public int getCount() {
            return tabs.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            if (!ListenerUtil.mutListener.listen(4977)) {
                switch(tabs.get(position)) {
                    case FRAGMENT_USERS:
                        return getString(R.string.title_tab_users).toUpperCase();
                    case FRAGMENT_WORK_USERS:
                        return getString(R.string.title_tab_work_users).toUpperCase();
                }
            }
            return null;
        }

        @NonNull
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            Fragment fragment = (Fragment) super.instantiateItem(container, position);
            if (!ListenerUtil.mutListener.listen(4978)) {
                registeredFragments.put(position, fragment);
            }
            return fragment;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            if (!ListenerUtil.mutListener.listen(4979)) {
                registeredFragments.remove(position);
            }
            if (!ListenerUtil.mutListener.listen(4980)) {
                super.destroyItem(container, position, object);
            }
        }

        public Fragment getRegisteredFragment(int position) {
            return registeredFragments.get(position);
        }
    }

    @Override
    public void onSelectionChanged() {
        List<ContactModel> contacts = getSelectedContacts();
        if (!ListenerUtil.mutListener.listen(4997)) {
            if ((ListenerUtil.mutListener.listen(4985) ? (contacts.size() >= 0) : (ListenerUtil.mutListener.listen(4984) ? (contacts.size() <= 0) : (ListenerUtil.mutListener.listen(4983) ? (contacts.size() < 0) : (ListenerUtil.mutListener.listen(4982) ? (contacts.size() != 0) : (ListenerUtil.mutListener.listen(4981) ? (contacts.size() == 0) : (contacts.size() > 0))))))) {
                if (!ListenerUtil.mutListener.listen(4992)) {
                    if (snackbar == null) {
                        if (!ListenerUtil.mutListener.listen(4989)) {
                            snackbar = SnackbarUtil.make(rootView, "", Snackbar.LENGTH_INDEFINITE, 4);
                        }
                        if (!ListenerUtil.mutListener.listen(4990)) {
                            snackbar.setBackgroundTint(ConfigUtils.getColorFromAttribute(this, R.attr.colorAccent));
                        }
                        if (!ListenerUtil.mutListener.listen(4991)) {
                            snackbar.getView().getLayoutParams().width = AppBarLayout.LayoutParams.MATCH_PARENT;
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(4993)) {
                    snackbar.setTextColor(ConfigUtils.getColorFromAttribute(this, R.attr.colorOnSecondary));
                }
                if (!ListenerUtil.mutListener.listen(4994)) {
                    snackbar.setText(getMemberNames());
                }
                if (!ListenerUtil.mutListener.listen(4996)) {
                    if (!snackbar.isShown()) {
                        if (!ListenerUtil.mutListener.listen(4995)) {
                            snackbar.show();
                        }
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(4988)) {
                    if ((ListenerUtil.mutListener.listen(4986) ? (snackbar != null || snackbar.isShown()) : (snackbar != null && snackbar.isShown()))) {
                        if (!ListenerUtil.mutListener.listen(4987)) {
                            snackbar.dismiss();
                        }
                    }
                }
            }
        }
    }

    private String getMemberNames() {
        StringBuilder builder = new StringBuilder();
        if (!ListenerUtil.mutListener.listen(5006)) {
            {
                long _loopCounter34 = 0;
                for (ContactModel contactModel : getSelectedContacts()) {
                    ListenerUtil.loopListener.listen("_loopCounter34", ++_loopCounter34);
                    if (!ListenerUtil.mutListener.listen(5004)) {
                        if ((ListenerUtil.mutListener.listen(5002) ? (builder.length() >= 0) : (ListenerUtil.mutListener.listen(5001) ? (builder.length() <= 0) : (ListenerUtil.mutListener.listen(5000) ? (builder.length() < 0) : (ListenerUtil.mutListener.listen(4999) ? (builder.length() != 0) : (ListenerUtil.mutListener.listen(4998) ? (builder.length() == 0) : (builder.length() > 0))))))) {
                            if (!ListenerUtil.mutListener.listen(5003)) {
                                builder.append(", ");
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(5005)) {
                        builder.append(NameUtil.getDisplayNameOrNickname(contactModel, true));
                    }
                }
            }
        }
        return builder.toString();
    }

    protected abstract boolean getAddNextButton();

    @MainThread
    protected abstract void initData(Bundle savedInstanceState);

    @StringRes
    protected abstract int getNotice();

    protected abstract void menuNext(List<ContactModel> selectedContacts);
}
