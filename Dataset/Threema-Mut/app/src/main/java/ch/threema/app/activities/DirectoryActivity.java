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
package ch.threema.app.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.ArrayList;
import java.util.List;
import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.UiThread;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.LiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import ch.threema.app.R;
import ch.threema.app.adapters.DirectoryAdapter;
import ch.threema.app.asynctasks.AddContactAsyncTask;
import ch.threema.app.dialogs.MultiChoiceSelectorDialog;
import ch.threema.app.services.ContactService;
import ch.threema.app.ui.DirectoryDataSourceFactory;
import ch.threema.app.ui.DirectoryHeaderItemDecoration;
import ch.threema.app.ui.EmptyRecyclerView;
import ch.threema.app.ui.EmptyView;
import ch.threema.app.ui.ThreemaSearchView;
import ch.threema.app.utils.ConfigUtils;
import ch.threema.app.utils.IntentDataUtil;
import ch.threema.app.utils.LogUtil;
import ch.threema.app.utils.TestUtil;
import ch.threema.client.work.WorkDirectoryCategory;
import ch.threema.client.work.WorkDirectoryContact;
import ch.threema.client.work.WorkOrganization;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class DirectoryActivity extends ThreemaToolbarActivity implements ThreemaSearchView.OnQueryTextListener, MultiChoiceSelectorDialog.SelectorDialogClickListener {

    private static final Logger logger = LoggerFactory.getLogger(DirectoryActivity.class);

    private static final int API_DIRECTORY_PAGE_SIZE = 3;

    // ms
    private static final long QUERY_TIMEOUT = 1000;

    private static final String DIALOG_TAG_CATEGORY_SELECTOR = "cs";

    public static final String EXTRA_ANIMATE_OUT = "anim";

    private ContactService contactService;

    private boolean sortByFirstName;

    private DirectoryAdapter directoryAdapter;

    private DirectoryDataSourceFactory directoryDataSourceFactory;

    private EmptyRecyclerView recyclerView;

    private ChipGroup chipGroup;

    private List<WorkDirectoryCategory> categoryList = new ArrayList<>();

    private List<WorkDirectoryCategory> checkedCategories = new ArrayList<>();

    private String queryText;

    @ColorInt
    int categorySpanColor;

    @ColorInt
    int categorySpanTextColor;

    private Handler queryHandler = new Handler();

    private Runnable queryTask = new Runnable() {

        @Override
        public void run() {
            if (!ListenerUtil.mutListener.listen(2415)) {
                directoryDataSourceFactory.postLiveData.getValue().setQueryText(queryText);
            }
            if (!ListenerUtil.mutListener.listen(2416)) {
                directoryDataSourceFactory.postLiveData.getValue().invalidate();
            }
        }
    };

    @Override
    public boolean onQueryTextSubmit(String query) {
        // Do something
        return true;
    }

    @SuppressLint("StaticFieldLeak")
    @Override
    public boolean onQueryTextChange(String newText) {
        if (!ListenerUtil.mutListener.listen(2417)) {
            queryText = newText;
        }
        if (!ListenerUtil.mutListener.listen(2418)) {
            queryHandler.removeCallbacks(queryTask);
        }
        if (!ListenerUtil.mutListener.listen(2419)) {
            queryHandler.postDelayed(queryTask, QUERY_TIMEOUT);
        }
        return true;
    }

    public int getLayoutResource() {
        return R.layout.activity_directory;
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected boolean initActivity(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(2420)) {
            if (!super.initActivity(savedInstanceState)) {
                return false;
            }
        }
        ;
        ActionBar actionBar = getSupportActionBar();
        if (!ListenerUtil.mutListener.listen(2425)) {
            if (actionBar != null) {
                if (!ListenerUtil.mutListener.listen(2421)) {
                    actionBar.setDisplayHomeAsUpEnabled(true);
                }
                Toolbar toolbar = getToolbar();
                if (!ListenerUtil.mutListener.listen(2424)) {
                    if (toolbar != null) {
                        if (!ListenerUtil.mutListener.listen(2422)) {
                            actionBar.setTitle(null);
                        }
                        if (!ListenerUtil.mutListener.listen(2423)) {
                            toolbar.setTitle(R.string.directory_title);
                        }
                    }
                }
            }
        }
        try {
            if (!ListenerUtil.mutListener.listen(2427)) {
                this.contactService = serviceManager.getContactService();
            }
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(2426)) {
                LogUtil.exception(e, this);
            }
            return false;
        }
        if (!ListenerUtil.mutListener.listen(2428)) {
            if (preferenceService == null) {
                return false;
            }
        }
        if (!ListenerUtil.mutListener.listen(2430)) {
            if (!ConfigUtils.isWorkDirectoryEnabled()) {
                if (!ListenerUtil.mutListener.listen(2429)) {
                    Toast.makeText(this, getString(R.string.disabled_by_policy_short), Toast.LENGTH_LONG).show();
                }
                return false;
            }
        }
        if (!ListenerUtil.mutListener.listen(2431)) {
            categoryList = preferenceService.getWorkDirectoryCategories();
        }
        if (!ListenerUtil.mutListener.listen(2440)) {
            if ((ListenerUtil.mutListener.listen(2436) ? (categoryList.size() >= 0) : (ListenerUtil.mutListener.listen(2435) ? (categoryList.size() <= 0) : (ListenerUtil.mutListener.listen(2434) ? (categoryList.size() < 0) : (ListenerUtil.mutListener.listen(2433) ? (categoryList.size() != 0) : (ListenerUtil.mutListener.listen(2432) ? (categoryList.size() == 0) : (categoryList.size() > 0))))))) {
                if (!ListenerUtil.mutListener.listen(2439)) {
                    if (ConfigUtils.getAppTheme(this) == ConfigUtils.THEME_DARK) {
                        if (!ListenerUtil.mutListener.listen(2438)) {
                            ConfigUtils.themeImageView(this, findViewById(R.id.category_selector_button));
                        }
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(2437)) {
                    findViewById(R.id.category_selector_button).setVisibility(View.GONE);
                }
            }
        }
        WorkOrganization workOrganization = preferenceService.getWorkOrganization();
        if (!ListenerUtil.mutListener.listen(2444)) {
            if ((ListenerUtil.mutListener.listen(2441) ? (workOrganization != null || !TestUtil.empty(workOrganization.getName())) : (workOrganization != null && !TestUtil.empty(workOrganization.getName())))) {
                if (!ListenerUtil.mutListener.listen(2442)) {
                    logger.info("Organization: " + workOrganization.getName());
                }
                if (!ListenerUtil.mutListener.listen(2443)) {
                    getToolbar().setTitle(workOrganization.getName());
                }
            }
        }
        ThreemaSearchView searchView = findViewById(R.id.search);
        if (!ListenerUtil.mutListener.listen(2445)) {
            searchView.setOnQueryTextListener(this);
        }
        if (!ListenerUtil.mutListener.listen(2446)) {
            sortByFirstName = preferenceService.isContactListSortingFirstName();
        }
        if (!ListenerUtil.mutListener.listen(2447)) {
            chipGroup = findViewById(R.id.chip_group);
        }
        if (!ListenerUtil.mutListener.listen(2448)) {
            categorySpanColor = ConfigUtils.getColorFromAttribute(this, R.attr.mention_background);
        }
        if (!ListenerUtil.mutListener.listen(2449)) {
            categorySpanTextColor = ConfigUtils.getColorFromAttribute(this, R.attr.mention_text_color);
        }
        if (!ListenerUtil.mutListener.listen(2450)) {
            recyclerView = this.findViewById(R.id.recycler);
        }
        if (!ListenerUtil.mutListener.listen(2451)) {
            recyclerView.setHasFixedSize(true);
        }
        if (!ListenerUtil.mutListener.listen(2452)) {
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
        }
        if (!ListenerUtil.mutListener.listen(2453)) {
            recyclerView.setItemAnimator(new DefaultItemAnimator());
        }
        EmptyView emptyView = new EmptyView(this, getResources().getDimensionPixelSize(R.dimen.directory_search_bar_height) + ConfigUtils.getActionBarSize(this));
        if (!ListenerUtil.mutListener.listen(2454)) {
            emptyView.setup(R.string.directory_empty_view_text);
        }
        if (!ListenerUtil.mutListener.listen(2455)) {
            ((ViewGroup) recyclerView.getParent().getParent()).addView(emptyView);
        }
        if (!ListenerUtil.mutListener.listen(2456)) {
            recyclerView.setEmptyView(emptyView);
        }
        DirectoryHeaderItemDecoration headerItemDecoration = new DirectoryHeaderItemDecoration(getResources().getDimensionPixelSize(R.dimen.directory_header_height), true, getSectionCallback());
        if (!ListenerUtil.mutListener.listen(2457)) {
            recyclerView.addItemDecoration(headerItemDecoration);
        }
        if (!ListenerUtil.mutListener.listen(2458)) {
            directoryAdapter = new DirectoryAdapter(this, preferenceService, contactService, categoryList);
        }
        if (!ListenerUtil.mutListener.listen(2462)) {
            directoryAdapter.setOnClickItemListener(new DirectoryAdapter.OnClickItemListener() {

                @Override
                public void onClick(WorkDirectoryContact workDirectoryContact, int position) {
                    if (!ListenerUtil.mutListener.listen(2459)) {
                        launchContact(workDirectoryContact, position);
                    }
                }

                @Override
                public void onAdd(WorkDirectoryContact workDirectoryContact, final int position) {
                    if (!ListenerUtil.mutListener.listen(2461)) {
                        addContact(workDirectoryContact, new Runnable() {

                            @Override
                            public void run() {
                                if (!ListenerUtil.mutListener.listen(2460)) {
                                    directoryAdapter.notifyItemChanged(position);
                                }
                            }
                        });
                    }
                }
            });
        }
        // initial page size
        PagedList.Config config = new PagedList.Config.Builder().setPageSize(API_DIRECTORY_PAGE_SIZE).build();
        if (!ListenerUtil.mutListener.listen(2463)) {
            directoryDataSourceFactory = new DirectoryDataSourceFactory();
        }
        LiveData<PagedList<WorkDirectoryContact>> contacts = new LivePagedListBuilder(directoryDataSourceFactory, config).build();
        if (!ListenerUtil.mutListener.listen(2464)) {
            contacts.observe(this, workDirectoryContacts -> directoryAdapter.submitList(workDirectoryContacts));
        }
        if (!ListenerUtil.mutListener.listen(2465)) {
            recyclerView.setAdapter(directoryAdapter);
        }
        if (!ListenerUtil.mutListener.listen(2466)) {
            findViewById(R.id.category_selector_button).setOnClickListener(this::selectCategories);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (!ListenerUtil.mutListener.listen(2468)) {
            switch(item.getItemId()) {
                case android.R.id.home:
                    if (!ListenerUtil.mutListener.listen(2467)) {
                        this.finish();
                    }
                    return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private void openContact(String identity) {
        Intent intent = new Intent(DirectoryActivity.this, ComposeMessageActivity.class);
        if (!ListenerUtil.mutListener.listen(2469)) {
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        }
        if (!ListenerUtil.mutListener.listen(2470)) {
            intent.setData((Uri.parse("foobar://" + SystemClock.elapsedRealtime())));
        }
        if (!ListenerUtil.mutListener.listen(2471)) {
            IntentDataUtil.append(identity, intent);
        }
        if (!ListenerUtil.mutListener.listen(2472)) {
            startActivity(intent);
        }
        if (!ListenerUtil.mutListener.listen(2473)) {
            overridePendingTransition(R.anim.slide_in_right_short, R.anim.slide_out_left_short);
        }
    }

    private void launchContact(final WorkDirectoryContact workDirectoryContact, final int position) {
        if (!ListenerUtil.mutListener.listen(2481)) {
            if (workDirectoryContact.threemaId != null) {
                if (!ListenerUtil.mutListener.listen(2480)) {
                    if (contactService.getByIdentity(workDirectoryContact.threemaId) == null) {
                        if (!ListenerUtil.mutListener.listen(2479)) {
                            addContact(workDirectoryContact, new Runnable() {

                                @Override
                                public void run() {
                                    if (!ListenerUtil.mutListener.listen(2477)) {
                                        openContact(workDirectoryContact.threemaId);
                                    }
                                    if (!ListenerUtil.mutListener.listen(2478)) {
                                        directoryAdapter.notifyItemChanged(position);
                                    }
                                }
                            });
                        }
                    } else if (workDirectoryContact.threemaId.equalsIgnoreCase(contactService.getMe().getIdentity())) {
                        if (!ListenerUtil.mutListener.listen(2476)) {
                            Toast.makeText(this, R.string.me_myself_and_i, Toast.LENGTH_LONG).show();
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(2475)) {
                            openContact(workDirectoryContact.threemaId);
                        }
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(2474)) {
                    Toast.makeText(this, R.string.contact_not_found, Toast.LENGTH_LONG).show();
                }
            }
        }
    }

    private void addContact(final WorkDirectoryContact workDirectoryContact, Runnable runAfter) {
        if (!ListenerUtil.mutListener.listen(2482)) {
            new AddContactAsyncTask(workDirectoryContact.firstName, workDirectoryContact.lastName, workDirectoryContact.threemaId, true, runAfter).execute();
        }
    }

    private DirectoryHeaderItemDecoration.HeaderCallback getSectionCallback() {
        return new DirectoryHeaderItemDecoration.HeaderCallback() {

            @Override
            public boolean isHeader(int position) {
                if (!ListenerUtil.mutListener.listen(2488)) {
                    if ((ListenerUtil.mutListener.listen(2487) ? (position >= 0) : (ListenerUtil.mutListener.listen(2486) ? (position <= 0) : (ListenerUtil.mutListener.listen(2485) ? (position > 0) : (ListenerUtil.mutListener.listen(2484) ? (position != 0) : (ListenerUtil.mutListener.listen(2483) ? (position == 0) : (position < 0))))))) {
                        return false;
                    }
                }
                if (!ListenerUtil.mutListener.listen(2494)) {
                    if ((ListenerUtil.mutListener.listen(2493) ? (position >= 0) : (ListenerUtil.mutListener.listen(2492) ? (position <= 0) : (ListenerUtil.mutListener.listen(2491) ? (position > 0) : (ListenerUtil.mutListener.listen(2490) ? (position < 0) : (ListenerUtil.mutListener.listen(2489) ? (position != 0) : (position == 0))))))) {
                        return true;
                    }
                }
                PagedList<WorkDirectoryContact> list = directoryAdapter.getCurrentList();
                if (!ListenerUtil.mutListener.listen(2500)) {
                    if ((ListenerUtil.mutListener.listen(2499) ? (position >= list.size()) : (ListenerUtil.mutListener.listen(2498) ? (position <= list.size()) : (ListenerUtil.mutListener.listen(2497) ? (position < list.size()) : (ListenerUtil.mutListener.listen(2496) ? (position != list.size()) : (ListenerUtil.mutListener.listen(2495) ? (position == list.size()) : (position > list.size()))))))) {
                        return false;
                    }
                }
                return !list.get(position).getInitial(sortByFirstName).equals(list.get((ListenerUtil.mutListener.listen(2504) ? (position % 1) : (ListenerUtil.mutListener.listen(2503) ? (position / 1) : (ListenerUtil.mutListener.listen(2502) ? (position * 1) : (ListenerUtil.mutListener.listen(2501) ? (position + 1) : (position - 1)))))).getInitial(sortByFirstName));
            }

            @Override
            public CharSequence getHeaderText(int position) {
                PagedList<WorkDirectoryContact> list = directoryAdapter.getCurrentList();
                return (ListenerUtil.mutListener.listen(2509) ? (position <= 0) : (ListenerUtil.mutListener.listen(2508) ? (position > 0) : (ListenerUtil.mutListener.listen(2507) ? (position < 0) : (ListenerUtil.mutListener.listen(2506) ? (position != 0) : (ListenerUtil.mutListener.listen(2505) ? (position == 0) : (position >= 0)))))) ? list.get(position).getInitial(sortByFirstName) : "TODO";
            }
        };
    }

    public void selectCategories(View view) {
        String[] categoryNames = new String[categoryList.size()];
        boolean[] categoryChecked = new boolean[categoryList.size()];
        int i = 0;
        if (!ListenerUtil.mutListener.listen(2513)) {
            {
                long _loopCounter13 = 0;
                for (WorkDirectoryCategory category : categoryList) {
                    ListenerUtil.loopListener.listen("_loopCounter13", ++_loopCounter13);
                    if (!ListenerUtil.mutListener.listen(2510)) {
                        categoryNames[i] = category.getName();
                    }
                    if (!ListenerUtil.mutListener.listen(2511)) {
                        categoryChecked[i] = checkedCategories.contains(category);
                    }
                    if (!ListenerUtil.mutListener.listen(2512)) {
                        i++;
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(2514)) {
            MultiChoiceSelectorDialog.newInstance(getString(R.string.work_select_categories), categoryNames, categoryChecked).show(getSupportFragmentManager(), DIALOG_TAG_CATEGORY_SELECTOR);
        }
    }

    @UiThread
    private void updateSelectedCategories() {
        int activeCategories = 0;
        if (!ListenerUtil.mutListener.listen(2515)) {
            chipGroup.removeAllViews();
        }
        if (!ListenerUtil.mutListener.listen(2540)) {
            {
                long _loopCounter15 = 0;
                for (WorkDirectoryCategory checkedCategory : checkedCategories) {
                    ListenerUtil.loopListener.listen("_loopCounter15", ++_loopCounter15);
                    if (!ListenerUtil.mutListener.listen(2539)) {
                        if (!TextUtils.isEmpty(checkedCategory.name)) {
                            if (!ListenerUtil.mutListener.listen(2516)) {
                                activeCategories++;
                            }
                            Chip chip = new Chip(this);
                            if (!ListenerUtil.mutListener.listen(2524)) {
                                if ((ListenerUtil.mutListener.listen(2521) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(2520) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(2519) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(2518) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(2517) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.M) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M))))))) {
                                    if (!ListenerUtil.mutListener.listen(2523)) {
                                        chip.setTextAppearance(R.style.TextAppearance_Chip_Ballot);
                                    }
                                } else {
                                    if (!ListenerUtil.mutListener.listen(2522)) {
                                        chip.setTextSize(14);
                                    }
                                }
                            }
                            ColorStateList foregroundColor, backgroundColor;
                            if (ConfigUtils.getAppTheme(this) == ConfigUtils.THEME_DARK) {
                                foregroundColor = ColorStateList.valueOf(ConfigUtils.getColorFromAttribute(this, R.attr.textColorPrimary));
                                backgroundColor = ColorStateList.valueOf(ConfigUtils.getColorFromAttribute(this, R.attr.colorAccent));
                            } else {
                                foregroundColor = ColorStateList.valueOf(ConfigUtils.getColorFromAttribute(this, R.attr.colorAccent));
                                backgroundColor = foregroundColor.withAlpha(0x1A);
                            }
                            if (!ListenerUtil.mutListener.listen(2525)) {
                                chip.setTextColor(foregroundColor);
                            }
                            if (!ListenerUtil.mutListener.listen(2526)) {
                                chip.setChipBackgroundColor(backgroundColor);
                            }
                            if (!ListenerUtil.mutListener.listen(2527)) {
                                chip.setText(checkedCategory.name);
                            }
                            if (!ListenerUtil.mutListener.listen(2528)) {
                                chip.setCloseIconVisible(true);
                            }
                            if (!ListenerUtil.mutListener.listen(2529)) {
                                chip.setTag(checkedCategory.id);
                            }
                            if (!ListenerUtil.mutListener.listen(2530)) {
                                chip.setCloseIconTint(foregroundColor);
                            }
                            if (!ListenerUtil.mutListener.listen(2537)) {
                                chip.setOnCloseIconClickListener(new View.OnClickListener() {

                                    @Override
                                    public void onClick(View v) {
                                        String categoryId = (String) v.getTag();
                                        if (!ListenerUtil.mutListener.listen(2536)) {
                                            if (!TextUtils.isEmpty(categoryId)) {
                                                if (!ListenerUtil.mutListener.listen(2535)) {
                                                    {
                                                        long _loopCounter14 = 0;
                                                        for (WorkDirectoryCategory checkedCategory : checkedCategories) {
                                                            ListenerUtil.loopListener.listen("_loopCounter14", ++_loopCounter14);
                                                            if (!ListenerUtil.mutListener.listen(2534)) {
                                                                if (categoryId.equals(checkedCategory.getId())) {
                                                                    if (!ListenerUtil.mutListener.listen(2531)) {
                                                                        checkedCategories.remove(checkedCategory);
                                                                    }
                                                                    if (!ListenerUtil.mutListener.listen(2532)) {
                                                                        chipGroup.removeView(v);
                                                                    }
                                                                    if (!ListenerUtil.mutListener.listen(2533)) {
                                                                        updateDirectory();
                                                                    }
                                                                    break;
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                });
                            }
                            if (!ListenerUtil.mutListener.listen(2538)) {
                                chipGroup.addView(chip);
                            }
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(2546)) {
            chipGroup.setVisibility((ListenerUtil.mutListener.listen(2545) ? (activeCategories >= 0) : (ListenerUtil.mutListener.listen(2544) ? (activeCategories <= 0) : (ListenerUtil.mutListener.listen(2543) ? (activeCategories > 0) : (ListenerUtil.mutListener.listen(2542) ? (activeCategories < 0) : (ListenerUtil.mutListener.listen(2541) ? (activeCategories != 0) : (activeCategories == 0)))))) ? View.GONE : View.VISIBLE);
        }
        if (!ListenerUtil.mutListener.listen(2547)) {
            updateDirectory();
        }
    }

    private void updateDirectory() {
        if (!ListenerUtil.mutListener.listen(2548)) {
            directoryDataSourceFactory.postLiveData.getValue().setQueryCategories(checkedCategories);
        }
        if (!ListenerUtil.mutListener.listen(2549)) {
            directoryDataSourceFactory.postLiveData.getValue().invalidate();
        }
    }

    @Override
    public void onYes(String tag, boolean[] checkedItems) {
        if (!ListenerUtil.mutListener.listen(2550)) {
            checkedCategories.clear();
        }
        if (!ListenerUtil.mutListener.listen(2558)) {
            {
                long _loopCounter16 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(2557) ? (i >= checkedItems.length) : (ListenerUtil.mutListener.listen(2556) ? (i <= checkedItems.length) : (ListenerUtil.mutListener.listen(2555) ? (i > checkedItems.length) : (ListenerUtil.mutListener.listen(2554) ? (i != checkedItems.length) : (ListenerUtil.mutListener.listen(2553) ? (i == checkedItems.length) : (i < checkedItems.length)))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter16", ++_loopCounter16);
                    if (!ListenerUtil.mutListener.listen(2552)) {
                        if (checkedItems[i]) {
                            if (!ListenerUtil.mutListener.listen(2551)) {
                                checkedCategories.add(categoryList.get(i));
                            }
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(2559)) {
            /* TODO only update if selected items have changed
		if (!Arrays.equals(this.checkedCategories, checkedItems)) {
			this.checkedCategories = checkedItems; */
            updateSelectedCategories();
        }
    }

    @Override
    public void onCancel(String tag) {
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        if (!ListenerUtil.mutListener.listen(2560)) {
            super.onConfigurationChanged(newConfig);
        }
        if (!ListenerUtil.mutListener.listen(2563)) {
            if (recyclerView != null) {
                if (!ListenerUtil.mutListener.listen(2561)) {
                    recyclerView.removeItemDecorationAt(0);
                }
                DirectoryHeaderItemDecoration headerItemDecoration = new DirectoryHeaderItemDecoration(getResources().getDimensionPixelSize(R.dimen.directory_header_height), true, getSectionCallback());
                if (!ListenerUtil.mutListener.listen(2562)) {
                    recyclerView.addItemDecoration(headerItemDecoration);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(2564)) {
            ConfigUtils.adjustToolbar(this, getToolbar());
        }
    }

    @Override
    public void onBackPressed() {
        if (!ListenerUtil.mutListener.listen(2565)) {
            this.finish();
        }
    }

    @Override
    public void finish() {
        boolean animateOut = getIntent().getBooleanExtra(EXTRA_ANIMATE_OUT, false);
        if (!ListenerUtil.mutListener.listen(2566)) {
            super.finish();
        }
        if (!ListenerUtil.mutListener.listen(2568)) {
            if (animateOut) {
                if (!ListenerUtil.mutListener.listen(2567)) {
                    overridePendingTransition(R.anim.slide_in_left_short, R.anim.slide_out_right_short);
                }
            }
        }
    }
}
