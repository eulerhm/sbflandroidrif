/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
 * Copyright (c) 2020-2021 Threema GmbH
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
package ch.threema.app.mediaattacher;

import android.Manifest;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.HapticFeedbackConstants;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;
import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.UiThread;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.widget.FitWindowsFrameLayout;
import androidx.appcompat.widget.PopupMenu;
import androidx.appcompat.widget.PopupMenuWrapper;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;
import ch.threema.app.R;
import ch.threema.app.ThreemaApplication;
import ch.threema.app.activities.EnterSerialActivity;
import ch.threema.app.activities.ThreemaActivity;
import ch.threema.app.activities.UnlockMasterKeyActivity;
import ch.threema.app.fragments.ComposeMessageFragment;
import ch.threema.app.managers.ServiceManager;
import ch.threema.app.services.GroupService;
import ch.threema.app.services.PreferenceService;
import ch.threema.app.ui.CheckableFrameLayout;
import ch.threema.app.ui.CheckableView;
import ch.threema.app.ui.EmptyRecyclerView;
import ch.threema.app.ui.MediaGridItemDecoration;
import ch.threema.app.ui.MediaItem;
import ch.threema.app.utils.ConfigUtils;
import ch.threema.app.utils.FileUtil;
import ch.threema.app.utils.IntentDataUtil;
import ch.threema.app.utils.LocaleUtil;
import ch.threema.localcrypto.MasterKey;
import me.zhanghai.android.fastscroll.FastScroller;
import me.zhanghai.android.fastscroll.FastScrollerBuilder;
import static ch.threema.app.mediaattacher.MediaFilterQuery.FILTER_MEDIA_BUCKET;
import static ch.threema.app.mediaattacher.MediaFilterQuery.FILTER_MEDIA_SELECTED;
import static ch.threema.app.mediaattacher.MediaFilterQuery.FILTER_MEDIA_TYPE;
import static com.google.android.material.bottomsheet.BottomSheetBehavior.STATE_COLLAPSED;
import static com.google.android.material.bottomsheet.BottomSheetBehavior.STATE_DRAGGING;
import static com.google.android.material.bottomsheet.BottomSheetBehavior.STATE_EXPANDED;
import static com.google.android.material.bottomsheet.BottomSheetBehavior.STATE_HIDDEN;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public abstract class MediaSelectionBaseActivity extends ThreemaActivity implements View.OnClickListener, MediaAttachAdapter.ItemClickListener {

    // Logging
    private static final Logger logger = LoggerFactory.getLogger(MediaSelectionBaseActivity.class);

    // Threema services
    protected ServiceManager serviceManager;

    protected PreferenceService preferenceService;

    protected GroupService groupService;

    public static final String KEY_BOTTOM_SHEET_STATE = "bottom_sheet_state";

    public static final String KEY_PREVIEW_MODE = "preview_mode";

    private static final String KEY_PREVIEW_ITEM_POSITION = "preview_item";

    protected static final int PERMISSION_REQUEST_ATTACH_FROM_GALLERY = 4;

    protected static final int PERMISSION_REQUEST_ATTACH_FILE = 5;

    protected static final int REQUEST_CODE_ATTACH_FROM_GALLERY = 2454;

    protected CoordinatorLayout rootView, gridContainer, pagerContainer;

    protected AppBarLayout appBarLayout;

    protected MaterialToolbar toolbar, previewToolbar;

    protected EmptyRecyclerView mediaAttachRecyclerView;

    protected FastScroller fastScroller;

    protected GridLayoutManager gridLayoutManager;

    protected ConstraintLayout bottomSheetLayout, previewBottomSheetLayout;

    protected ImageView dragHandle;

    protected FrameLayout controlPanel, dateView;

    protected LinearLayout menuTitleFrame;

    protected TextView dateTextView, menuTitle, previewFilenameTextView, previewDateTextView;

    protected DisplayMetrics displayMetrics;

    protected MenuItem selectFromGalleryItem;

    protected PopupMenu bucketFilterMenu;

    protected ViewPager2 previewPager;

    private CheckableView checkBox;

    protected MediaAttachViewModel mediaAttachViewModel;

    protected MediaAttachAdapter mediaAttachAdapter;

    protected ImagePreviewPagerAdapter imagePreviewPagerAdapter;

    protected int peekHeightNumElements = 1;

    @ColorInt
    private int savedStatusBarColor = 0;

    private boolean isDragging = false;

    private boolean bottomSheetScroll = false;

    private boolean isPreviewMode = false;

    BottomSheetBehavior<ConstraintLayout> bottomSheetBehavior, previewBottomSheetBehavior;

    // Locks
    private final Object filterMenuLock = new Object();

    private final Object previewLock = new Object();

    /* start lifecycle methods */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(29842)) {
            super.onCreate(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(29843)) {
            ConfigUtils.configureActivityTheme(this);
        }
        if (!ListenerUtil.mutListener.listen(29844)) {
            checkMasterKey();
        }
        if (!ListenerUtil.mutListener.listen(29845)) {
            initServices();
        }
        if (!ListenerUtil.mutListener.listen(29846)) {
            // set font size according to user preferences
            getTheme().applyStyle(preferenceService.getFontStyle(), true);
        }
        if (!ListenerUtil.mutListener.listen(29847)) {
            initActivity(savedInstanceState);
        }
    }

    @Override
    protected void onDestroy() {
        if (!ListenerUtil.mutListener.listen(29848)) {
            logger.debug("*** onDestroy");
        }
        if (!ListenerUtil.mutListener.listen(29849)) {
            super.onDestroy();
        }
    }

    @UiThread
    protected void handleSavedInstanceState(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(29860)) {
            if (savedInstanceState != null) {
                if (!ListenerUtil.mutListener.listen(29850)) {
                    onItemChecked(mediaAttachViewModel.getSelectedMediaItemsHashMap().size());
                }
                int bottomSheetStyleState = savedInstanceState.getInt(KEY_BOTTOM_SHEET_STATE);
                if (!ListenerUtil.mutListener.listen(29857)) {
                    if ((ListenerUtil.mutListener.listen(29855) ? (bottomSheetStyleState >= 0) : (ListenerUtil.mutListener.listen(29854) ? (bottomSheetStyleState <= 0) : (ListenerUtil.mutListener.listen(29853) ? (bottomSheetStyleState > 0) : (ListenerUtil.mutListener.listen(29852) ? (bottomSheetStyleState < 0) : (ListenerUtil.mutListener.listen(29851) ? (bottomSheetStyleState == 0) : (bottomSheetStyleState != 0))))))) {
                        if (!ListenerUtil.mutListener.listen(29856)) {
                            updateUI(bottomSheetStyleState);
                        }
                    }
                }
                boolean previewModeState = savedInstanceState.getBoolean(KEY_PREVIEW_MODE);
                int previewItemPosition = savedInstanceState.getInt(KEY_PREVIEW_ITEM_POSITION);
                if (!ListenerUtil.mutListener.listen(29859)) {
                    if (previewModeState) {
                        if (!ListenerUtil.mutListener.listen(29858)) {
                            startPreviewMode(previewItemPosition, 50);
                        }
                    }
                }
            }
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        if (!ListenerUtil.mutListener.listen(29861)) {
            super.onSaveInstanceState(outState);
        }
        if (!ListenerUtil.mutListener.listen(29862)) {
            outState.putInt(KEY_BOTTOM_SHEET_STATE, bottomSheetBehavior.getState());
        }
        if (!ListenerUtil.mutListener.listen(29863)) {
            outState.putBoolean(KEY_PREVIEW_MODE, isPreviewMode);
        }
        if (!ListenerUtil.mutListener.listen(29864)) {
            outState.putInt(KEY_PREVIEW_ITEM_POSITION, previewPager.getCurrentItem());
        }
    }

    /* start setup methods */
    protected void initActivity(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(29865)) {
            // The display metrics are used to query the size of the device display
            this.displayMetrics = ThreemaApplication.getAppContext().getResources().getDisplayMetrics();
        }
        if (!ListenerUtil.mutListener.listen(29866)) {
            // The view model handles data associated with this view
            this.mediaAttachViewModel = new ViewModelProvider(this).get(MediaAttachViewModel.class);
        }
        if (!ListenerUtil.mutListener.listen(29867)) {
            // Initialize UI
            this.setLayout();
        }
        if (!ListenerUtil.mutListener.listen(29868)) {
            this.setDropdownMenu();
        }
        if (!ListenerUtil.mutListener.listen(29869)) {
            this.setListeners();
        }
        if (!ListenerUtil.mutListener.listen(29870)) {
            this.toolbar.setOnMenuItemClickListener(item -> {
                if (item.getItemId() == R.id.menu_select_from_gallery) {
                    if (ConfigUtils.requestStoragePermissions(MediaSelectionBaseActivity.this, null, PERMISSION_REQUEST_ATTACH_FROM_GALLERY)) {
                        attachImageFromGallery();
                    }
                    return true;
                }
                return false;
            });
        }
        if (!ListenerUtil.mutListener.listen(29872)) {
            this.toolbar.setNavigationOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    if (!ListenerUtil.mutListener.listen(29871)) {
                        collapseBottomSheet();
                    }
                }
            });
        }
    }

    protected void initServices() {
        if (!ListenerUtil.mutListener.listen(29873)) {
            this.serviceManager = ThreemaApplication.getServiceManager();
        }
        if (!ListenerUtil.mutListener.listen(29878)) {
            if (serviceManager != null) {
                try {
                    if (!ListenerUtil.mutListener.listen(29876)) {
                        this.preferenceService = serviceManager.getPreferenceService();
                    }
                    if (!ListenerUtil.mutListener.listen(29877)) {
                        this.groupService = serviceManager.getGroupService();
                    }
                } catch (Exception e) {
                    if (!ListenerUtil.mutListener.listen(29874)) {
                        logger.error("Exception", e);
                    }
                    if (!ListenerUtil.mutListener.listen(29875)) {
                        finish();
                    }
                }
            }
        }
    }

    protected void setLayout() {
        if (!ListenerUtil.mutListener.listen(29879)) {
            setContentView(R.layout.activity_media_attach);
        }
        if (!ListenerUtil.mutListener.listen(29880)) {
            this.rootView = findViewById(R.id.coordinator);
        }
        if (!ListenerUtil.mutListener.listen(29881)) {
            this.appBarLayout = findViewById(R.id.appbar_layout);
        }
        if (!ListenerUtil.mutListener.listen(29882)) {
            this.toolbar = findViewById(R.id.toolbar);
        }
        if (!ListenerUtil.mutListener.listen(29883)) {
            this.selectFromGalleryItem = this.toolbar.getMenu().findItem(R.id.menu_select_from_gallery);
        }
        if (!ListenerUtil.mutListener.listen(29884)) {
            this.menuTitleFrame = findViewById(R.id.toolbar_title);
        }
        if (!ListenerUtil.mutListener.listen(29885)) {
            this.menuTitle = findViewById(R.id.toolbar_title_textview);
        }
        if (!ListenerUtil.mutListener.listen(29886)) {
            this.bottomSheetLayout = findViewById(R.id.bottom_sheet);
        }
        if (!ListenerUtil.mutListener.listen(29887)) {
            this.previewBottomSheetLayout = findViewById(R.id.preview_bottom_sheet);
        }
        if (!ListenerUtil.mutListener.listen(29888)) {
            this.mediaAttachRecyclerView = findViewById(R.id.media_grid_recycler);
        }
        if (!ListenerUtil.mutListener.listen(29889)) {
            this.dragHandle = findViewById(R.id.drag_handle);
        }
        if (!ListenerUtil.mutListener.listen(29890)) {
            this.controlPanel = findViewById(R.id.control_panel);
        }
        if (!ListenerUtil.mutListener.listen(29891)) {
            this.dateView = findViewById(R.id.date_separator_container);
        }
        if (!ListenerUtil.mutListener.listen(29892)) {
            this.dateTextView = findViewById(R.id.text_view);
        }
        if (!ListenerUtil.mutListener.listen(29893)) {
            this.gridContainer = findViewById(R.id.grid_container);
        }
        if (!ListenerUtil.mutListener.listen(29894)) {
            this.previewPager = findViewById(R.id.pager);
        }
        if (!ListenerUtil.mutListener.listen(29895)) {
            this.pagerContainer = findViewById(R.id.pager_container);
        }
        if (!ListenerUtil.mutListener.listen(29896)) {
            this.checkBox = findViewById(R.id.check_box);
        }
        if (!ListenerUtil.mutListener.listen(29897)) {
            this.previewFilenameTextView = findViewById(R.id.filename_view);
        }
        if (!ListenerUtil.mutListener.listen(29898)) {
            this.previewDateTextView = findViewById(R.id.date_view);
        }
        if (!ListenerUtil.mutListener.listen(29899)) {
            this.bottomSheetBehavior = BottomSheetBehavior.from(bottomSheetLayout);
        }
        if (!ListenerUtil.mutListener.listen(29900)) {
            this.previewBottomSheetBehavior = BottomSheetBehavior.from(previewBottomSheetLayout);
        }
        MaterialToolbar previewToolbar = findViewById(R.id.preview_toolbar);
        if (!ListenerUtil.mutListener.listen(29901)) {
            previewToolbar.setNavigationOnClickListener(v -> onBackPressed());
        }
        if (!ListenerUtil.mutListener.listen(29902)) {
            this.checkBox.setOnClickListener(v -> {
                checkBox.toggle();
                MediaAttachItem mediaItem = imagePreviewPagerAdapter.getItem(previewPager.getCurrentItem());
                if (checkBox.isChecked()) {
                    mediaAttachViewModel.addSelectedMediaItem(mediaItem.getId(), mediaItem);
                } else {
                    mediaAttachViewModel.removeSelectedMediaItem(mediaItem.getId());
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(29905)) {
            this.previewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {

                @Override
                public void onPageSelected(int position) {
                    if (!ListenerUtil.mutListener.listen(29903)) {
                        super.onPageSelected(position);
                    }
                    if (!ListenerUtil.mutListener.listen(29904)) {
                        updatePreviewInfo(position);
                    }
                }
            });
        }
        // fill background with transparent black to see chat behind drawer
        FitWindowsFrameLayout contentFrameLayout = (FitWindowsFrameLayout) ((ViewGroup) rootView.getParent()).getParent();
        if (!ListenerUtil.mutListener.listen(29906)) {
            contentFrameLayout.setOnClickListener(v -> finish());
        }
        if (!ListenerUtil.mutListener.listen(29913)) {
            // set status bar color
            if ((ListenerUtil.mutListener.listen(29911) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(29910) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(29909) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(29908) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(29907) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.LOLLIPOP) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP))))))) {
                if (!ListenerUtil.mutListener.listen(29912)) {
                    getWindow().setStatusBarColor(ConfigUtils.getColorFromAttribute(this, R.attr.attach_status_bar_color_collapsed));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(29937)) {
            // horizontal layout fill screen 2/3 with media selection layout
            if ((ListenerUtil.mutListener.listen(29914) ? (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE || !isInSplitScreenMode()) : (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE && !isInSplitScreenMode()))) {
                CoordinatorLayout bottomSheetContainer = findViewById(R.id.bottom_sheet_container);
                CoordinatorLayout.LayoutParams bottomSheetContainerParams = (CoordinatorLayout.LayoutParams) bottomSheetContainer.getLayoutParams();
                FrameLayout.LayoutParams attacherLayoutParams = (FrameLayout.LayoutParams) rootView.getLayoutParams();
                if (!ListenerUtil.mutListener.listen(29926)) {
                    attacherLayoutParams.width = (ListenerUtil.mutListener.listen(29925) ? ((ListenerUtil.mutListener.listen(29921) ? (displayMetrics.widthPixels % 2) : (ListenerUtil.mutListener.listen(29920) ? (displayMetrics.widthPixels / 2) : (ListenerUtil.mutListener.listen(29919) ? (displayMetrics.widthPixels - 2) : (ListenerUtil.mutListener.listen(29918) ? (displayMetrics.widthPixels + 2) : (displayMetrics.widthPixels * 2))))) % 3) : (ListenerUtil.mutListener.listen(29924) ? ((ListenerUtil.mutListener.listen(29921) ? (displayMetrics.widthPixels % 2) : (ListenerUtil.mutListener.listen(29920) ? (displayMetrics.widthPixels / 2) : (ListenerUtil.mutListener.listen(29919) ? (displayMetrics.widthPixels - 2) : (ListenerUtil.mutListener.listen(29918) ? (displayMetrics.widthPixels + 2) : (displayMetrics.widthPixels * 2))))) * 3) : (ListenerUtil.mutListener.listen(29923) ? ((ListenerUtil.mutListener.listen(29921) ? (displayMetrics.widthPixels % 2) : (ListenerUtil.mutListener.listen(29920) ? (displayMetrics.widthPixels / 2) : (ListenerUtil.mutListener.listen(29919) ? (displayMetrics.widthPixels - 2) : (ListenerUtil.mutListener.listen(29918) ? (displayMetrics.widthPixels + 2) : (displayMetrics.widthPixels * 2))))) - 3) : (ListenerUtil.mutListener.listen(29922) ? ((ListenerUtil.mutListener.listen(29921) ? (displayMetrics.widthPixels % 2) : (ListenerUtil.mutListener.listen(29920) ? (displayMetrics.widthPixels / 2) : (ListenerUtil.mutListener.listen(29919) ? (displayMetrics.widthPixels - 2) : (ListenerUtil.mutListener.listen(29918) ? (displayMetrics.widthPixels + 2) : (displayMetrics.widthPixels * 2))))) + 3) : ((ListenerUtil.mutListener.listen(29921) ? (displayMetrics.widthPixels % 2) : (ListenerUtil.mutListener.listen(29920) ? (displayMetrics.widthPixels / 2) : (ListenerUtil.mutListener.listen(29919) ? (displayMetrics.widthPixels - 2) : (ListenerUtil.mutListener.listen(29918) ? (displayMetrics.widthPixels + 2) : (displayMetrics.widthPixels * 2))))) / 3)))));
                }
                if (!ListenerUtil.mutListener.listen(29927)) {
                    attacherLayoutParams.gravity = Gravity.CENTER;
                }
                if (!ListenerUtil.mutListener.listen(29928)) {
                    bottomSheetContainerParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
                }
                if (!ListenerUtil.mutListener.listen(29929)) {
                    bottomSheetContainerParams.gravity = Gravity.CENTER;
                }
                if (!ListenerUtil.mutListener.listen(29930)) {
                    bottomSheetContainerParams.insetEdge = Gravity.CENTER;
                }
                if (!ListenerUtil.mutListener.listen(29931)) {
                    bottomSheetContainer.setLayoutParams(bottomSheetContainerParams);
                }
                if (!ListenerUtil.mutListener.listen(29932)) {
                    rootView.setLayoutParams(attacherLayoutParams);
                }
                if (!ListenerUtil.mutListener.listen(29933)) {
                    contentFrameLayout.setOnClickListener(v -> finish());
                }
                if (!ListenerUtil.mutListener.listen(29934)) {
                    this.gridLayoutManager = new GridLayoutManager(this, 4);
                }
                if (!ListenerUtil.mutListener.listen(29935)) {
                    this.mediaAttachRecyclerView.setLayoutManager(gridLayoutManager);
                }
                if (!ListenerUtil.mutListener.listen(29936)) {
                    this.peekHeightNumElements = 1;
                }
            } else {
                if (!ListenerUtil.mutListener.listen(29915)) {
                    this.gridLayoutManager = new GridLayoutManager(this, 3);
                }
                if (!ListenerUtil.mutListener.listen(29916)) {
                    this.mediaAttachRecyclerView.setLayoutManager(gridLayoutManager);
                }
                if (!ListenerUtil.mutListener.listen(29917)) {
                    this.peekHeightNumElements = isInSplitScreenMode() ? 1 : 2;
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(29938)) {
            // Set initial peek height
            this.updatePeekHeight();
        }
        if (!ListenerUtil.mutListener.listen(29939)) {
            // Listen for layout changes
            this.mediaAttachAdapter = new MediaAttachAdapter(this, this);
        }
        if (!ListenerUtil.mutListener.listen(29940)) {
            this.imagePreviewPagerAdapter = new ImagePreviewPagerAdapter(this);
        }
        if (!ListenerUtil.mutListener.listen(29941)) {
            this.previewPager.setOffscreenPageLimit(1);
        }
        if (!ListenerUtil.mutListener.listen(29942)) {
            this.mediaAttachRecyclerView.addItemDecoration(new MediaGridItemDecoration(getResources().getDimensionPixelSize(R.dimen.grid_spacing)));
        }
        if (!ListenerUtil.mutListener.listen(29943)) {
            this.mediaAttachRecyclerView.setAdapter(mediaAttachAdapter);
        }
        ProgressBar progressBar = (ProgressBar) getLayoutInflater().inflate(R.layout.item_progress, null);
        ConstraintSet set = new ConstraintSet();
        if (!ListenerUtil.mutListener.listen(29944)) {
            // set view id, else getId() returns -1
            progressBar.setId(View.generateViewId());
        }
        if (!ListenerUtil.mutListener.listen(29945)) {
            bottomSheetLayout.addView(progressBar, 0);
        }
        if (!ListenerUtil.mutListener.listen(29946)) {
            set.clone(bottomSheetLayout);
        }
        if (!ListenerUtil.mutListener.listen(29947)) {
            set.connect(progressBar.getId(), ConstraintSet.TOP, bottomSheetLayout.getId(), ConstraintSet.TOP, 60);
        }
        if (!ListenerUtil.mutListener.listen(29948)) {
            set.connect(progressBar.getId(), ConstraintSet.BOTTOM, bottomSheetLayout.getId(), ConstraintSet.BOTTOM, 60);
        }
        if (!ListenerUtil.mutListener.listen(29949)) {
            set.connect(progressBar.getId(), ConstraintSet.LEFT, bottomSheetLayout.getId(), ConstraintSet.LEFT, 60);
        }
        if (!ListenerUtil.mutListener.listen(29950)) {
            set.connect(progressBar.getId(), ConstraintSet.RIGHT, bottomSheetLayout.getId(), ConstraintSet.RIGHT, 60);
        }
        if (!ListenerUtil.mutListener.listen(29951)) {
            set.applyTo(bottomSheetLayout);
        }
        if (!ListenerUtil.mutListener.listen(29952)) {
            this.mediaAttachRecyclerView.setEmptyView(progressBar);
        }
        if (!ListenerUtil.mutListener.listen(29953)) {
            ConfigUtils.addIconsToOverflowMenu(this, this.toolbar.getMenu());
        }
        if (!ListenerUtil.mutListener.listen(29969)) {
            this.rootView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

                @Override
                public void onGlobalLayout() {
                    if (!ListenerUtil.mutListener.listen(29954)) {
                        rootView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    }
                    // adjust height of bottom sheet container to snap smack below toolbar
                    CoordinatorLayout bottomSheetContainer = findViewById(R.id.bottom_sheet_container);
                    int topMargin = (ListenerUtil.mutListener.listen(29966) ? ((ListenerUtil.mutListener.listen(29958) ? (toolbar.getHeight() % getResources().getDimensionPixelSize(R.dimen.drag_handle_height)) : (ListenerUtil.mutListener.listen(29957) ? (toolbar.getHeight() / getResources().getDimensionPixelSize(R.dimen.drag_handle_height)) : (ListenerUtil.mutListener.listen(29956) ? (toolbar.getHeight() * getResources().getDimensionPixelSize(R.dimen.drag_handle_height)) : (ListenerUtil.mutListener.listen(29955) ? (toolbar.getHeight() + getResources().getDimensionPixelSize(R.dimen.drag_handle_height)) : (toolbar.getHeight() - getResources().getDimensionPixelSize(R.dimen.drag_handle_height)))))) % ((ListenerUtil.mutListener.listen(29962) ? (getResources().getDimensionPixelSize(R.dimen.drag_handle_topbottom_margin) % 2) : (ListenerUtil.mutListener.listen(29961) ? (getResources().getDimensionPixelSize(R.dimen.drag_handle_topbottom_margin) / 2) : (ListenerUtil.mutListener.listen(29960) ? (getResources().getDimensionPixelSize(R.dimen.drag_handle_topbottom_margin) - 2) : (ListenerUtil.mutListener.listen(29959) ? (getResources().getDimensionPixelSize(R.dimen.drag_handle_topbottom_margin) + 2) : (getResources().getDimensionPixelSize(R.dimen.drag_handle_topbottom_margin) * 2))))))) : (ListenerUtil.mutListener.listen(29965) ? ((ListenerUtil.mutListener.listen(29958) ? (toolbar.getHeight() % getResources().getDimensionPixelSize(R.dimen.drag_handle_height)) : (ListenerUtil.mutListener.listen(29957) ? (toolbar.getHeight() / getResources().getDimensionPixelSize(R.dimen.drag_handle_height)) : (ListenerUtil.mutListener.listen(29956) ? (toolbar.getHeight() * getResources().getDimensionPixelSize(R.dimen.drag_handle_height)) : (ListenerUtil.mutListener.listen(29955) ? (toolbar.getHeight() + getResources().getDimensionPixelSize(R.dimen.drag_handle_height)) : (toolbar.getHeight() - getResources().getDimensionPixelSize(R.dimen.drag_handle_height)))))) / ((ListenerUtil.mutListener.listen(29962) ? (getResources().getDimensionPixelSize(R.dimen.drag_handle_topbottom_margin) % 2) : (ListenerUtil.mutListener.listen(29961) ? (getResources().getDimensionPixelSize(R.dimen.drag_handle_topbottom_margin) / 2) : (ListenerUtil.mutListener.listen(29960) ? (getResources().getDimensionPixelSize(R.dimen.drag_handle_topbottom_margin) - 2) : (ListenerUtil.mutListener.listen(29959) ? (getResources().getDimensionPixelSize(R.dimen.drag_handle_topbottom_margin) + 2) : (getResources().getDimensionPixelSize(R.dimen.drag_handle_topbottom_margin) * 2))))))) : (ListenerUtil.mutListener.listen(29964) ? ((ListenerUtil.mutListener.listen(29958) ? (toolbar.getHeight() % getResources().getDimensionPixelSize(R.dimen.drag_handle_height)) : (ListenerUtil.mutListener.listen(29957) ? (toolbar.getHeight() / getResources().getDimensionPixelSize(R.dimen.drag_handle_height)) : (ListenerUtil.mutListener.listen(29956) ? (toolbar.getHeight() * getResources().getDimensionPixelSize(R.dimen.drag_handle_height)) : (ListenerUtil.mutListener.listen(29955) ? (toolbar.getHeight() + getResources().getDimensionPixelSize(R.dimen.drag_handle_height)) : (toolbar.getHeight() - getResources().getDimensionPixelSize(R.dimen.drag_handle_height)))))) * ((ListenerUtil.mutListener.listen(29962) ? (getResources().getDimensionPixelSize(R.dimen.drag_handle_topbottom_margin) % 2) : (ListenerUtil.mutListener.listen(29961) ? (getResources().getDimensionPixelSize(R.dimen.drag_handle_topbottom_margin) / 2) : (ListenerUtil.mutListener.listen(29960) ? (getResources().getDimensionPixelSize(R.dimen.drag_handle_topbottom_margin) - 2) : (ListenerUtil.mutListener.listen(29959) ? (getResources().getDimensionPixelSize(R.dimen.drag_handle_topbottom_margin) + 2) : (getResources().getDimensionPixelSize(R.dimen.drag_handle_topbottom_margin) * 2))))))) : (ListenerUtil.mutListener.listen(29963) ? ((ListenerUtil.mutListener.listen(29958) ? (toolbar.getHeight() % getResources().getDimensionPixelSize(R.dimen.drag_handle_height)) : (ListenerUtil.mutListener.listen(29957) ? (toolbar.getHeight() / getResources().getDimensionPixelSize(R.dimen.drag_handle_height)) : (ListenerUtil.mutListener.listen(29956) ? (toolbar.getHeight() * getResources().getDimensionPixelSize(R.dimen.drag_handle_height)) : (ListenerUtil.mutListener.listen(29955) ? (toolbar.getHeight() + getResources().getDimensionPixelSize(R.dimen.drag_handle_height)) : (toolbar.getHeight() - getResources().getDimensionPixelSize(R.dimen.drag_handle_height)))))) + ((ListenerUtil.mutListener.listen(29962) ? (getResources().getDimensionPixelSize(R.dimen.drag_handle_topbottom_margin) % 2) : (ListenerUtil.mutListener.listen(29961) ? (getResources().getDimensionPixelSize(R.dimen.drag_handle_topbottom_margin) / 2) : (ListenerUtil.mutListener.listen(29960) ? (getResources().getDimensionPixelSize(R.dimen.drag_handle_topbottom_margin) - 2) : (ListenerUtil.mutListener.listen(29959) ? (getResources().getDimensionPixelSize(R.dimen.drag_handle_topbottom_margin) + 2) : (getResources().getDimensionPixelSize(R.dimen.drag_handle_topbottom_margin) * 2))))))) : ((ListenerUtil.mutListener.listen(29958) ? (toolbar.getHeight() % getResources().getDimensionPixelSize(R.dimen.drag_handle_height)) : (ListenerUtil.mutListener.listen(29957) ? (toolbar.getHeight() / getResources().getDimensionPixelSize(R.dimen.drag_handle_height)) : (ListenerUtil.mutListener.listen(29956) ? (toolbar.getHeight() * getResources().getDimensionPixelSize(R.dimen.drag_handle_height)) : (ListenerUtil.mutListener.listen(29955) ? (toolbar.getHeight() + getResources().getDimensionPixelSize(R.dimen.drag_handle_height)) : (toolbar.getHeight() - getResources().getDimensionPixelSize(R.dimen.drag_handle_height)))))) - ((ListenerUtil.mutListener.listen(29962) ? (getResources().getDimensionPixelSize(R.dimen.drag_handle_topbottom_margin) % 2) : (ListenerUtil.mutListener.listen(29961) ? (getResources().getDimensionPixelSize(R.dimen.drag_handle_topbottom_margin) / 2) : (ListenerUtil.mutListener.listen(29960) ? (getResources().getDimensionPixelSize(R.dimen.drag_handle_topbottom_margin) - 2) : (ListenerUtil.mutListener.listen(29959) ? (getResources().getDimensionPixelSize(R.dimen.drag_handle_topbottom_margin) + 2) : (getResources().getDimensionPixelSize(R.dimen.drag_handle_topbottom_margin) * 2)))))))))));
                    CoordinatorLayout.LayoutParams bottomSheetContainerLayoutParams = (CoordinatorLayout.LayoutParams) bottomSheetContainer.getLayoutParams();
                    if (!ListenerUtil.mutListener.listen(29967)) {
                        bottomSheetContainerLayoutParams.setMargins(0, topMargin, 0, 0);
                    }
                    if (!ListenerUtil.mutListener.listen(29968)) {
                        bottomSheetContainer.setLayoutParams(bottomSheetContainerLayoutParams);
                    }
                }
            });
        }
    }

    protected void setDropdownMenu() {
        if (!ListenerUtil.mutListener.listen(29970)) {
            this.bucketFilterMenu = new PopupMenuWrapper(this, menuTitle);
        }
        if (!ListenerUtil.mutListener.listen(29973)) {
            if (mediaAttachViewModel.getLastQuery() == null) {
                if (!ListenerUtil.mutListener.listen(29972)) {
                    menuTitle.setText(R.string.attach_gallery);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(29971)) {
                    menuTitle.setText(mediaAttachViewModel.getLastQuery());
                }
            }
        }
        MenuItem topMenuItem = bucketFilterMenu.getMenu().add(Menu.NONE, 0, 0, R.string.attach_gallery).setOnMenuItemClickListener(menuItem -> {
            setAllResultsGrid();
            return true;
        });
        if (!ListenerUtil.mutListener.listen(29974)) {
            topMenuItem.setIcon(R.drawable.ic_collections);
        }
        if (!ListenerUtil.mutListener.listen(29975)) {
            ConfigUtils.themeMenuItem(topMenuItem, ConfigUtils.getColorFromAttribute(this, R.attr.textColorSecondary));
        }
        // Fetch all media, add a unique menu item for each media storage bucket and media type group.
        final MutableLiveData<List<MediaAttachItem>> allMediaLiveData = mediaAttachViewModel.getAllMedia();
        if (!ListenerUtil.mutListener.listen(29976)) {
            allMediaLiveData.observe(this, mediaAttachItems -> {
                synchronized (filterMenuLock) {
                    // We need data!
                    if (mediaAttachItems == null || mediaAttachItems.isEmpty()) {
                        return;
                    }
                    // If menu is already filled, do nothing
                    final Menu menu = bucketFilterMenu.getMenu();
                    if (menu.size() > 1) {
                        logger.warn("Filter menu already contained {} entries, clearing all except first", menu.size());
                        {
                            long _loopCounter199 = 0;
                            for (int i = 1; i < menu.size(); i++) {
                                ListenerUtil.loopListener.listen("_loopCounter199", ++_loopCounter199);
                                menu.removeItem(i);
                            }
                        }
                    }
                    // Extract buckets and media types
                    final List<String> buckets = new ArrayList<>();
                    final TreeMap<String, Integer> mediaTypes = new TreeMap<>();
                    {
                        long _loopCounter200 = 0;
                        for (MediaAttachItem mediaItem : mediaAttachItems) {
                            ListenerUtil.loopListener.listen("_loopCounter200", ++_loopCounter200);
                            String bucket = mediaItem.getBucketName();
                            if (!TextUtils.isEmpty(bucket) && !buckets.contains(bucket)) {
                                buckets.add(mediaItem.getBucketName());
                            }
                            int type = mediaItem.getType();
                            if (!mediaTypes.containsValue(type)) {
                                String mediaTypeName = getMimeTypeTitle(type);
                                mediaTypes.put(mediaTypeName, type);
                            }
                        }
                    }
                    Collections.sort(buckets);
                    {
                        long _loopCounter201 = 0;
                        // Fill menu first media types sorted then folders/buckets sorted
                        for (Map.Entry<String, Integer> mediaType : mediaTypes.entrySet()) {
                            ListenerUtil.loopListener.listen("_loopCounter201", ++_loopCounter201);
                            MenuItem item = menu.add(mediaType.getKey()).setOnMenuItemClickListener(menuItem -> {
                                filterMediaByMimeType(menuItem.toString());
                                return true;
                            });
                            switch(mediaType.getValue()) {
                                case MediaItem.TYPE_IMAGE:
                                    item.setIcon(R.drawable.ic_image_outline);
                                    break;
                                case MediaItem.TYPE_VIDEO:
                                    item.setIcon(R.drawable.ic_movie_outline);
                                    break;
                                case MediaItem.TYPE_GIF:
                                    item.setIcon(R.drawable.ic_gif_24dp);
                                    break;
                            }
                            ConfigUtils.themeMenuItem(item, ConfigUtils.getColorFromAttribute(this, R.attr.textColorSecondary));
                        }
                    }
                    {
                        long _loopCounter202 = 0;
                        for (String bucket : buckets) {
                            ListenerUtil.loopListener.listen("_loopCounter202", ++_loopCounter202);
                            if (!TextUtils.isEmpty(bucket)) {
                                MenuItem item = menu.add(bucket).setOnMenuItemClickListener(menuItem -> {
                                    filterMediaByBucket(menuItem.toString());
                                    return true;
                                });
                                item.setIcon(R.drawable.ic_outline_folder_24);
                                ConfigUtils.themeMenuItem(item, ConfigUtils.getColorFromAttribute(this, R.attr.textColorSecondary));
                            }
                        }
                    }
                    // Enable menu
                    menuTitleFrame.setOnClickListener(view -> bucketFilterMenu.show());
                }
                // reset last recent filter if activity was destroyed by the system due to memory pressure etc.
                String savedQuery = mediaAttachViewModel.getLastQuery();
                Integer savedQueryType = mediaAttachViewModel.getLastQueryType();
                if (savedQueryType != null) {
                    switch(savedQueryType) {
                        case FILTER_MEDIA_TYPE:
                            filterMediaByMimeType(savedQuery);
                            break;
                        case FILTER_MEDIA_BUCKET:
                            filterMediaByBucket(savedQuery);
                            break;
                        case FILTER_MEDIA_SELECTED:
                            filterMediaBySelectedItems();
                            break;
                        default:
                            menuTitle.setText(R.string.filter_by_album);
                            break;
                    }
                }
            });
        }
    }

    private void updatePreviewInfo(int position) {
        MediaAttachItem mediaItem = imagePreviewPagerAdapter.getItem(position);
        if (!ListenerUtil.mutListener.listen(29977)) {
            checkBox.setChecked(mediaAttachViewModel.getSelectedMediaItemsHashMap().containsKey(mediaItem.getId()));
        }
        if (!ListenerUtil.mutListener.listen(29978)) {
            previewBottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        }
        if (!ListenerUtil.mutListener.listen(29979)) {
            previewFilenameTextView.setText(String.format("%s/%s", mediaItem.getBucketName(), mediaItem.getDisplayName()));
        }
        long taken = mediaItem.getDateTaken();
        // multiply because of format takes millis
        long modified = (ListenerUtil.mutListener.listen(29983) ? (mediaItem.getDateModified() % 1000) : (ListenerUtil.mutListener.listen(29982) ? (mediaItem.getDateModified() / 1000) : (ListenerUtil.mutListener.listen(29981) ? (mediaItem.getDateModified() - 1000) : (ListenerUtil.mutListener.listen(29980) ? (mediaItem.getDateModified() + 1000) : (mediaItem.getDateModified() * 1000)))));
        long added = (ListenerUtil.mutListener.listen(29987) ? (mediaItem.getDateAdded() % 1000) : (ListenerUtil.mutListener.listen(29986) ? (mediaItem.getDateAdded() / 1000) : (ListenerUtil.mutListener.listen(29985) ? (mediaItem.getDateAdded() - 1000) : (ListenerUtil.mutListener.listen(29984) ? (mediaItem.getDateAdded() + 1000) : (mediaItem.getDateAdded() * 1000)))));
        if (!ListenerUtil.mutListener.listen(30007)) {
            if ((ListenerUtil.mutListener.listen(29992) ? (taken >= 0) : (ListenerUtil.mutListener.listen(29991) ? (taken <= 0) : (ListenerUtil.mutListener.listen(29990) ? (taken > 0) : (ListenerUtil.mutListener.listen(29989) ? (taken < 0) : (ListenerUtil.mutListener.listen(29988) ? (taken == 0) : (taken != 0))))))) {
                if (!ListenerUtil.mutListener.listen(30006)) {
                    previewDateTextView.setText(String.format(getString(R.string.media_date_taken), LocaleUtil.formatTimeStampString(this, taken, false)));
                }
            } else if ((ListenerUtil.mutListener.listen(29997) ? (added >= 0) : (ListenerUtil.mutListener.listen(29996) ? (added <= 0) : (ListenerUtil.mutListener.listen(29995) ? (added > 0) : (ListenerUtil.mutListener.listen(29994) ? (added < 0) : (ListenerUtil.mutListener.listen(29993) ? (added == 0) : (added != 0))))))) {
                if (!ListenerUtil.mutListener.listen(30005)) {
                    previewDateTextView.setText(String.format(getString(R.string.media_date_added), LocaleUtil.formatTimeStampString(this, added, false)));
                }
            } else if ((ListenerUtil.mutListener.listen(30002) ? (modified >= 0) : (ListenerUtil.mutListener.listen(30001) ? (modified <= 0) : (ListenerUtil.mutListener.listen(30000) ? (modified > 0) : (ListenerUtil.mutListener.listen(29999) ? (modified < 0) : (ListenerUtil.mutListener.listen(29998) ? (modified == 0) : (modified != 0))))))) {
                if (!ListenerUtil.mutListener.listen(30004)) {
                    previewDateTextView.setText(String.format(getString(R.string.media_date_modified), LocaleUtil.formatTimeStampString(this, modified, false)));
                }
            } else {
                if (!ListenerUtil.mutListener.listen(30003)) {
                    previewDateTextView.setText(getString(R.string.media_date_unknown));
                }
            }
        }
    }

    /**
     *  If the media grid is enabled and all necessary permissions are granted,
     *  initialize and show it.
     */
    @UiThread
    protected void setInitialMediaGrid() {
        if (!ListenerUtil.mutListener.listen(30013)) {
            if (shouldShowMediaGrid()) {
                // check for previous filter selection to be reset
                Intent intent = getIntent();
                int queryType = 0;
                String query = null;
                if (!ListenerUtil.mutListener.listen(30010)) {
                    if (intent.hasExtra(ComposeMessageFragment.EXTRA_LAST_MEDIA_SEARCH_QUERY)) {
                        MediaFilterQuery lastFilter = IntentDataUtil.getLastMediaFilterFromIntent(intent);
                        if (!ListenerUtil.mutListener.listen(30008)) {
                            queryType = lastFilter.getType();
                        }
                        if (!ListenerUtil.mutListener.listen(30009)) {
                            query = lastFilter.getQuery();
                        }
                    }
                }
                // if we previously searched media in a chat we reset the filter, otherwise we post all media to grid view
                int finalPreviousQueryType = queryType;
                String finalPreviousQuery = query;
                if (!ListenerUtil.mutListener.listen(30011)) {
                    mediaAttachViewModel.getAllMedia().observe(this, allItems -> {
                        if (!allItems.isEmpty()) {
                            if (finalPreviousQuery != null) {
                                switch(finalPreviousQueryType) {
                                    case FILTER_MEDIA_TYPE:
                                        filterMediaByMimeType(finalPreviousQuery);
                                        break;
                                    case FILTER_MEDIA_BUCKET:
                                        filterMediaByBucket(finalPreviousQuery);
                                        break;
                                }
                            } else // finally set all media unless we remember a query in the viewmodel over orientation change
                            if (mediaAttachViewModel.getLastQueryType() == null) {
                                setAllResultsGrid();
                            }
                            // remove after receiving full list as we listen to current selected media afterwards to update the grid view
                            mediaAttachViewModel.getAllMedia().removeObservers(this);
                        }
                    });
                }
                if (!ListenerUtil.mutListener.listen(30012)) {
                    // Observe the LiveData for current selection, passing in this activity as the LifecycleOwner and Observer.
                    mediaAttachViewModel.getCurrentMedia().observe(this, currentlyShowingItems -> {
                        mediaAttachAdapter.setMediaItems(currentlyShowingItems);
                        imagePreviewPagerAdapter.setMediaItems(currentlyShowingItems);
                        // Data loaded, we can now properly calculate the peek height and set/reset UI to expanded state
                        updatePeekHeight();
                    });
                }
            }
        }
    }

    /**
     *  Check if the media attacher's selectable media grid can be shown
     *  @return true if option has been enabled by user and permissions are available
     */
    protected boolean shouldShowMediaGrid() {
        return (ListenerUtil.mutListener.listen(30014) ? (preferenceService.isShowImageAttachPreviewsEnabled() || ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) : (preferenceService.isShowImageAttachPreviewsEnabled() && ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED));
    }

    protected void setListeners() {
        if (!ListenerUtil.mutListener.listen(30015)) {
            this.appBarLayout.setOnClickListener(this);
        }
        if (!ListenerUtil.mutListener.listen(30016)) {
            bottomSheetBehavior.setExpandedOffset(50);
        }
        if (!ListenerUtil.mutListener.listen(30018)) {
            bottomSheetBehavior.addBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {

                @Override
                public void onStateChanged(@NonNull View bottomSheet, int newState) {
                    if (!ListenerUtil.mutListener.listen(30017)) {
                        updateUI(newState);
                    }
                }

                @Override
                public void onSlide(@NonNull View bottomSheet, float slideOffset) {
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(30032)) {
            mediaAttachRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

                @Override
                public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                    if (!ListenerUtil.mutListener.listen(30019)) {
                        super.onScrolled(recyclerView, dx, dy);
                    }
                    if (!ListenerUtil.mutListener.listen(30020)) {
                        setFirstVisibleItemDate();
                    }
                    if (!ListenerUtil.mutListener.listen(30024)) {
                        if ((ListenerUtil.mutListener.listen(30022) ? ((ListenerUtil.mutListener.listen(30021) ? (controlPanel.getTranslationY() == 0 || mediaAttachViewModel.getSelectedMediaItemsHashMap().isEmpty()) : (controlPanel.getTranslationY() == 0 && mediaAttachViewModel.getSelectedMediaItemsHashMap().isEmpty())) || bottomSheetBehavior.getState() == STATE_EXPANDED) : ((ListenerUtil.mutListener.listen(30021) ? (controlPanel.getTranslationY() == 0 || mediaAttachViewModel.getSelectedMediaItemsHashMap().isEmpty()) : (controlPanel.getTranslationY() == 0 && mediaAttachViewModel.getSelectedMediaItemsHashMap().isEmpty())) && bottomSheetBehavior.getState() == STATE_EXPANDED))) {
                            if (!ListenerUtil.mutListener.listen(30023)) {
                                controlPanel.animate().translationY(controlPanel.getHeight());
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(30031)) {
                        // make sure only bottom sheet or recylcerview is scrolling at a same time
                        if ((ListenerUtil.mutListener.listen(30025) ? (bottomSheetScroll || bottomSheetBehavior.getState() == STATE_EXPANDED) : (bottomSheetScroll && bottomSheetBehavior.getState() == STATE_EXPANDED))) {
                            if (!ListenerUtil.mutListener.listen(30029)) {
                                bottomSheetScroll = false;
                            }
                            if (!ListenerUtil.mutListener.listen(30030)) {
                                bottomSheetBehavior.setDraggable(false);
                            }
                        } else if ((ListenerUtil.mutListener.listen(30026) ? (!bottomSheetScroll || !recyclerView.canScrollVertically(-1)) : (!bottomSheetScroll && !recyclerView.canScrollVertically(-1)))) {
                            if (!ListenerUtil.mutListener.listen(30027)) {
                                bottomSheetScroll = true;
                            }
                            if (!ListenerUtil.mutListener.listen(30028)) {
                                bottomSheetBehavior.setDraggable(true);
                            }
                        }
                    }
                }
            });
        }
    }

    @Override
    public void onItemLongClick(View view, int position, MediaAttachItem mediaAttachItem) {
        if (!ListenerUtil.mutListener.listen(30033)) {
            view.performHapticFeedback(HapticFeedbackConstants.LONG_PRESS);
        }
        if (!ListenerUtil.mutListener.listen(30034)) {
            startPreviewMode(position, 0);
        }
    }

    @Override
    public void onBackPressed() {
        if (!ListenerUtil.mutListener.listen(30035)) {
            logger.debug("*** onBackPressed");
        }
        synchronized (previewLock) {
            if (!ListenerUtil.mutListener.listen(30066)) {
                if (pagerContainer.getVisibility() == View.VISIBLE) {
                    if (!ListenerUtil.mutListener.listen(30065)) {
                        if (isPreviewMode) {
                            if (!ListenerUtil.mutListener.listen(30037)) {
                                gridContainer.setVisibility(View.VISIBLE);
                            }
                            if (!ListenerUtil.mutListener.listen(30038)) {
                                pagerContainer.setVisibility(View.GONE);
                            }
                            if (!ListenerUtil.mutListener.listen(30039)) {
                                previewPager.setAdapter(null);
                            }
                            if (!ListenerUtil.mutListener.listen(30040)) {
                                mediaAttachAdapter.notifyDataSetChanged();
                            }
                            if (!ListenerUtil.mutListener.listen(30041)) {
                                onItemChecked(mediaAttachViewModel.getSelectedMediaItemsHashMap().size());
                            }
                            if (!ListenerUtil.mutListener.listen(30055)) {
                                if ((ListenerUtil.mutListener.listen(30046) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(30045) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(30044) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(30043) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(30042) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.LOLLIPOP) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP))))))) {
                                    if (!ListenerUtil.mutListener.listen(30047)) {
                                        getWindow().setStatusBarColor(savedStatusBarColor);
                                    }
                                    if (!ListenerUtil.mutListener.listen(30054)) {
                                        if ((ListenerUtil.mutListener.listen(30052) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.O) : (ListenerUtil.mutListener.listen(30051) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.O) : (ListenerUtil.mutListener.listen(30050) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) : (ListenerUtil.mutListener.listen(30049) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.O) : (ListenerUtil.mutListener.listen(30048) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.O) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O))))))) {
                                            if (!ListenerUtil.mutListener.listen(30053)) {
                                                getWindow().setNavigationBarColor(ConfigUtils.getColorFromAttribute(this, R.attr.attach_status_bar_color_expanded));
                                            }
                                        }
                                    }
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(30063)) {
                                if (ConfigUtils.getAppTheme(this) != ConfigUtils.THEME_DARK) {
                                    if (!ListenerUtil.mutListener.listen(30062)) {
                                        if ((ListenerUtil.mutListener.listen(30060) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(30059) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(30058) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(30057) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(30056) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.M) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M))))))) {
                                            if (!ListenerUtil.mutListener.listen(30061)) {
                                                getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
                                            }
                                        }
                                    }
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(30064)) {
                                isPreviewMode = false;
                            }
                        }
                    }
                } else {
                    if (!ListenerUtil.mutListener.listen(30036)) {
                        super.onBackPressed();
                    }
                }
            }
        }
    }

    private void startPreviewMode(int position, int delay) {
        if (!ListenerUtil.mutListener.listen(30067)) {
            logger.debug("*** startPreviewMode");
        }
        synchronized (previewLock) {
            if (!ListenerUtil.mutListener.listen(30099)) {
                if (!isPreviewMode) {
                    if (!ListenerUtil.mutListener.listen(30068)) {
                        pagerContainer.setVisibility(View.VISIBLE);
                    }
                    if (!ListenerUtil.mutListener.listen(30069)) {
                        previewPager.setAdapter(imagePreviewPagerAdapter);
                    }
                    if (!ListenerUtil.mutListener.listen(30070)) {
                        gridContainer.setVisibility(View.GONE);
                    }
                    if (!ListenerUtil.mutListener.listen(30071)) {
                        logger.debug("*** setStatusBarColor");
                    }
                    if (!ListenerUtil.mutListener.listen(30095)) {
                        toolbar.postDelayed(new Runnable() {

                            @Override
                            public void run() {
                                if (!ListenerUtil.mutListener.listen(30086)) {
                                    if ((ListenerUtil.mutListener.listen(30076) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(30075) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(30074) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(30073) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(30072) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.LOLLIPOP) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP))))))) {
                                        if (!ListenerUtil.mutListener.listen(30077)) {
                                            savedStatusBarColor = getWindow().getStatusBarColor();
                                        }
                                        if (!ListenerUtil.mutListener.listen(30078)) {
                                            getWindow().setStatusBarColor(getResources().getColor(R.color.gallery_background));
                                        }
                                        if (!ListenerUtil.mutListener.listen(30085)) {
                                            if ((ListenerUtil.mutListener.listen(30083) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.O) : (ListenerUtil.mutListener.listen(30082) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.O) : (ListenerUtil.mutListener.listen(30081) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) : (ListenerUtil.mutListener.listen(30080) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.O) : (ListenerUtil.mutListener.listen(30079) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.O) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O))))))) {
                                                if (!ListenerUtil.mutListener.listen(30084)) {
                                                    getWindow().setNavigationBarColor(getResources().getColor(R.color.gallery_background));
                                                }
                                            }
                                        }
                                    }
                                }
                                if (!ListenerUtil.mutListener.listen(30094)) {
                                    if (ConfigUtils.getAppTheme(MediaSelectionBaseActivity.this) != ConfigUtils.THEME_DARK) {
                                        if (!ListenerUtil.mutListener.listen(30093)) {
                                            if ((ListenerUtil.mutListener.listen(30091) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(30090) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(30089) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(30088) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(30087) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.M) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M))))))) {
                                                if (!ListenerUtil.mutListener.listen(30092)) {
                                                    getWindow().getDecorView().setSystemUiVisibility(getWindow().getDecorView().getSystemUiVisibility() & ~View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }, delay);
                    }
                    if (!ListenerUtil.mutListener.listen(30097)) {
                        previewPager.post(new Runnable() {

                            @Override
                            public void run() {
                                if (!ListenerUtil.mutListener.listen(30096)) {
                                    previewPager.setCurrentItem(position, false);
                                }
                            }
                        });
                    }
                    if (!ListenerUtil.mutListener.listen(30098)) {
                        isPreviewMode = true;
                    }
                }
            }
        }
    }

    public void setAllResultsGrid() {
        if (!ListenerUtil.mutListener.listen(30100)) {
            mediaAttachViewModel.setAllMedia();
        }
        if (!ListenerUtil.mutListener.listen(30101)) {
            menuTitle.setText(getResources().getString(R.string.attach_gallery));
        }
        if (!ListenerUtil.mutListener.listen(30102)) {
            mediaAttachViewModel.clearLastQuery();
        }
    }

    public void filterMediaByBucket(@NonNull String mediaBucket) {
        if (!ListenerUtil.mutListener.listen(30103)) {
            mediaAttachViewModel.setMediaByBucket(mediaBucket);
        }
        if (!ListenerUtil.mutListener.listen(30104)) {
            menuTitle.setText(mediaBucket);
        }
        if (!ListenerUtil.mutListener.listen(30105)) {
            mediaAttachViewModel.setlastQuery(FILTER_MEDIA_BUCKET, mediaBucket);
        }
    }

    public void filterMediaByMimeType(@NonNull String mimeTypeTitle) {
        int mimeTypeIndex = 0;
        if (!ListenerUtil.mutListener.listen(30109)) {
            if (mimeTypeTitle.equals(ThreemaApplication.getAppContext().getResources().getString(R.string.media_gallery_pictures))) {
                if (!ListenerUtil.mutListener.listen(30108)) {
                    mimeTypeIndex = MediaItem.TYPE_IMAGE;
                }
            } else if (mimeTypeTitle.equals(ThreemaApplication.getAppContext().getResources().getString(R.string.media_gallery_videos))) {
                if (!ListenerUtil.mutListener.listen(30107)) {
                    mimeTypeIndex = MediaItem.TYPE_VIDEO;
                }
            } else if (mimeTypeTitle.equals(ThreemaApplication.getAppContext().getResources().getString(R.string.media_gallery_gifs))) {
                if (!ListenerUtil.mutListener.listen(30106)) {
                    mimeTypeIndex = MediaItem.TYPE_GIF;
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(30116)) {
            if ((ListenerUtil.mutListener.listen(30114) ? (mimeTypeIndex >= 0) : (ListenerUtil.mutListener.listen(30113) ? (mimeTypeIndex <= 0) : (ListenerUtil.mutListener.listen(30112) ? (mimeTypeIndex > 0) : (ListenerUtil.mutListener.listen(30111) ? (mimeTypeIndex < 0) : (ListenerUtil.mutListener.listen(30110) ? (mimeTypeIndex == 0) : (mimeTypeIndex != 0))))))) {
                if (!ListenerUtil.mutListener.listen(30115)) {
                    mediaAttachViewModel.setMediaByType(mimeTypeIndex);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(30117)) {
            menuTitle.setText(mimeTypeTitle);
        }
        if (!ListenerUtil.mutListener.listen(30118)) {
            mediaAttachViewModel.setlastQuery(FILTER_MEDIA_TYPE, mimeTypeTitle);
        }
    }

    public void filterMediaBySelectedItems() {
        if (!ListenerUtil.mutListener.listen(30119)) {
            mediaAttachViewModel.setSelectedMedia();
        }
        if (!ListenerUtil.mutListener.listen(30120)) {
            menuTitle.setText(R.string.selected_media);
        }
        if (!ListenerUtil.mutListener.listen(30121)) {
            mediaAttachViewModel.setlastQuery(FILTER_MEDIA_SELECTED, null);
        }
    }

    public String getMimeTypeTitle(int mimeType) {
        switch(mimeType) {
            case (MediaItem.TYPE_IMAGE):
                return getResources().getString(R.string.media_gallery_pictures);
            case (MediaItem.TYPE_VIDEO):
                return getResources().getString(R.string.media_gallery_videos);
            case (MediaItem.TYPE_GIF):
                return getResources().getString(R.string.media_gallery_gifs);
            default:
                return null;
        }
    }

    public void updateUI(int state) {
        Animation animation;
        if (!ListenerUtil.mutListener.listen(30123)) {
            if (bottomSheetBehavior.getState() != state) {
                if (!ListenerUtil.mutListener.listen(30122)) {
                    bottomSheetBehavior.setState(state);
                }
            }
        }
        switch(state) {
            case STATE_HIDDEN:
                if (!ListenerUtil.mutListener.listen(30124)) {
                    finish();
                }
                break;
            case STATE_EXPANDED:
                if (!ListenerUtil.mutListener.listen(30125)) {
                    dateView.setVisibility(View.VISIBLE);
                }
                if (!ListenerUtil.mutListener.listen(30126)) {
                    dragHandle.setVisibility(View.INVISIBLE);
                }
                if (!ListenerUtil.mutListener.listen(30127)) {
                    setFirstVisibleItemDate();
                }
                if (!ListenerUtil.mutListener.listen(30128)) {
                    bucketFilterMenu.getMenu().setGroupVisible(Menu.NONE, true);
                }
                if (!ListenerUtil.mutListener.listen(30129)) {
                    menuTitleFrame.setClickable(true);
                }
                animation = toolbar.getAnimation();
                if (!ListenerUtil.mutListener.listen(30131)) {
                    if (animation != null) {
                        if (!ListenerUtil.mutListener.listen(30130)) {
                            animation.cancel();
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(30132)) {
                    toolbar.setAlpha(0f);
                }
                if (!ListenerUtil.mutListener.listen(30133)) {
                    toolbar.setVisibility(View.VISIBLE);
                }
                if (!ListenerUtil.mutListener.listen(30135)) {
                    toolbar.animate().alpha(1f).setDuration(100).setListener(new AnimatorListenerAdapter() {

                        @Override
                        public void onAnimationEnd(Animator animation) {
                            if (!ListenerUtil.mutListener.listen(30134)) {
                                toolbar.setVisibility(View.VISIBLE);
                            }
                        }
                    });
                }
                if (!ListenerUtil.mutListener.listen(30142)) {
                    if ((ListenerUtil.mutListener.listen(30140) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(30139) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(30138) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(30137) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(30136) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.LOLLIPOP) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP))))))) {
                        if (!ListenerUtil.mutListener.listen(30141)) {
                            toolbar.postDelayed(() -> getWindow().setStatusBarColor(ConfigUtils.getColorFromAttribute(this, R.attr.attach_status_bar_color_expanded)), 50);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(30146)) {
                    // hide
                    if ((ListenerUtil.mutListener.listen(30143) ? (mediaAttachViewModel.getSelectedMediaItemsHashMap().isEmpty() || controlPanel.getTranslationY() == 0) : (mediaAttachViewModel.getSelectedMediaItemsHashMap().isEmpty() && controlPanel.getTranslationY() == 0))) {
                        if (!ListenerUtil.mutListener.listen(30145)) {
                            controlPanel.animate().translationY(controlPanel.getHeight());
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(30144)) {
                            // show
                            controlPanel.animate().translationY(0);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(30155)) {
                    if ((ListenerUtil.mutListener.listen(30152) ? ((ListenerUtil.mutListener.listen(30151) ? (Build.VERSION.SDK_INT <= 21) : (ListenerUtil.mutListener.listen(30150) ? (Build.VERSION.SDK_INT > 21) : (ListenerUtil.mutListener.listen(30149) ? (Build.VERSION.SDK_INT < 21) : (ListenerUtil.mutListener.listen(30148) ? (Build.VERSION.SDK_INT != 21) : (ListenerUtil.mutListener.listen(30147) ? (Build.VERSION.SDK_INT == 21) : (Build.VERSION.SDK_INT >= 21)))))) || fastScroller == null) : ((ListenerUtil.mutListener.listen(30151) ? (Build.VERSION.SDK_INT <= 21) : (ListenerUtil.mutListener.listen(30150) ? (Build.VERSION.SDK_INT > 21) : (ListenerUtil.mutListener.listen(30149) ? (Build.VERSION.SDK_INT < 21) : (ListenerUtil.mutListener.listen(30148) ? (Build.VERSION.SDK_INT != 21) : (ListenerUtil.mutListener.listen(30147) ? (Build.VERSION.SDK_INT == 21) : (Build.VERSION.SDK_INT >= 21)))))) && fastScroller == null))) {
                        TypedValue value = new TypedValue();
                        if (!ListenerUtil.mutListener.listen(30153)) {
                            this.getTheme().resolveAttribute(R.attr.attach_media_thumb_drawable, value, true);
                        }
                        Drawable thumbDrawable = AppCompatResources.getDrawable(this, value.resourceId);
                        if (!ListenerUtil.mutListener.listen(30154)) {
                            fastScroller = new FastScrollerBuilder(MediaSelectionBaseActivity.this.mediaAttachRecyclerView).setThumbDrawable(Objects.requireNonNull(thumbDrawable)).setTrackDrawable(Objects.requireNonNull(AppCompatResources.getDrawable(this, R.drawable.fastscroll_track_media))).setPadding(0, 0, 0, 0).build();
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(30156)) {
                    isDragging = false;
                }
                break;
            case STATE_DRAGGING:
                if (!isDragging) {
                    if (!ListenerUtil.mutListener.listen(30157)) {
                        isDragging = true;
                    }
                    if (!ListenerUtil.mutListener.listen(30158)) {
                        dateView.setVisibility(View.GONE);
                    }
                    if (!ListenerUtil.mutListener.listen(30159)) {
                        dragHandle.setVisibility(View.VISIBLE);
                    }
                    animation = toolbar.getAnimation();
                    if (!ListenerUtil.mutListener.listen(30161)) {
                        if (animation != null) {
                            if (!ListenerUtil.mutListener.listen(30160)) {
                                animation.cancel();
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(30162)) {
                        toolbar.setAlpha(1f);
                    }
                    if (!ListenerUtil.mutListener.listen(30164)) {
                        toolbar.animate().alpha(0f).setDuration(100).setListener(new AnimatorListenerAdapter() {

                            @Override
                            public void onAnimationEnd(Animator animation) {
                                if (!ListenerUtil.mutListener.listen(30163)) {
                                    toolbar.setVisibility(View.GONE);
                                }
                            }
                        });
                    }
                    if (!ListenerUtil.mutListener.listen(30165)) {
                        toolbar.postDelayed(() -> {
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                getWindow().setStatusBarColor(ConfigUtils.getColorFromAttribute(this, R.attr.attach_status_bar_color_collapsed));
                            }
                        }, 50);
                    }
                }
                break;
            case STATE_COLLAPSED:
                if (!ListenerUtil.mutListener.listen(30166)) {
                    bottomSheetBehavior.setDraggable(true);
                }
                if (!ListenerUtil.mutListener.listen(30167)) {
                    bottomSheetScroll = true;
                }
                if (!ListenerUtil.mutListener.listen(30168)) {
                    dateView.setVisibility(View.GONE);
                }
                if (!ListenerUtil.mutListener.listen(30169)) {
                    bucketFilterMenu.getMenu().setGroupVisible(Menu.NONE, false);
                }
                if (!ListenerUtil.mutListener.listen(30170)) {
                    menuTitleFrame.setClickable(false);
                }
                if (!ListenerUtil.mutListener.listen(30171)) {
                    controlPanel.animate().translationY(0);
                }
                if (!ListenerUtil.mutListener.listen(30172)) {
                    isDragging = false;
                }
            default:
                break;
        }
    }

    /**
     *  Adjust the peek height of the bottom sheet to fit in the {@link #peekHeightNumElements}
     *  number of items vertically.
     */
    protected synchronized void updatePeekHeight() {
        if (!ListenerUtil.mutListener.listen(30173)) {
            logger.debug("*** updatePeekHeight");
        }
        if (!ListenerUtil.mutListener.listen(30218)) {
            if (shouldShowMediaGrid()) {
                final int numElements = this.peekHeightNumElements;
                if (!ListenerUtil.mutListener.listen(30176)) {
                    logger.debug("Update peek height ({} elements)", numElements);
                }
                int numItems = mediaAttachRecyclerView.getLayoutManager().getItemCount();
                if (!ListenerUtil.mutListener.listen(30177)) {
                    bottomSheetLayout.setVisibility(View.VISIBLE);
                }
                // Fetch some pixel dimensions we need for calculations below
                final Resources resources = this.getResources();
                final int controlPanelHeight = resources.getDimensionPixelSize(R.dimen.control_panel_height);
                final int controlPanelShadowHeight = resources.getDimensionPixelSize(R.dimen.media_attach_control_panel_shadow_size);
                final int dragHandleHeight = resources.getDimensionPixelSize(R.dimen.drag_handle_height);
                final int dragHandleTopBottomMargin = resources.getDimensionPixelSize(R.dimen.drag_handle_topbottom_margin);
                // are loaded, we can update the peek height.
                int peekHeight = (ListenerUtil.mutListener.listen(30193) ? ((ListenerUtil.mutListener.listen(30185) ? ((ListenerUtil.mutListener.listen(30181) ? (controlPanelHeight % controlPanelShadowHeight) : (ListenerUtil.mutListener.listen(30180) ? (controlPanelHeight / controlPanelShadowHeight) : (ListenerUtil.mutListener.listen(30179) ? (controlPanelHeight * controlPanelShadowHeight) : (ListenerUtil.mutListener.listen(30178) ? (controlPanelHeight + controlPanelShadowHeight) : (controlPanelHeight - controlPanelShadowHeight))))) % dragHandleHeight) : (ListenerUtil.mutListener.listen(30184) ? ((ListenerUtil.mutListener.listen(30181) ? (controlPanelHeight % controlPanelShadowHeight) : (ListenerUtil.mutListener.listen(30180) ? (controlPanelHeight / controlPanelShadowHeight) : (ListenerUtil.mutListener.listen(30179) ? (controlPanelHeight * controlPanelShadowHeight) : (ListenerUtil.mutListener.listen(30178) ? (controlPanelHeight + controlPanelShadowHeight) : (controlPanelHeight - controlPanelShadowHeight))))) / dragHandleHeight) : (ListenerUtil.mutListener.listen(30183) ? ((ListenerUtil.mutListener.listen(30181) ? (controlPanelHeight % controlPanelShadowHeight) : (ListenerUtil.mutListener.listen(30180) ? (controlPanelHeight / controlPanelShadowHeight) : (ListenerUtil.mutListener.listen(30179) ? (controlPanelHeight * controlPanelShadowHeight) : (ListenerUtil.mutListener.listen(30178) ? (controlPanelHeight + controlPanelShadowHeight) : (controlPanelHeight - controlPanelShadowHeight))))) * dragHandleHeight) : (ListenerUtil.mutListener.listen(30182) ? ((ListenerUtil.mutListener.listen(30181) ? (controlPanelHeight % controlPanelShadowHeight) : (ListenerUtil.mutListener.listen(30180) ? (controlPanelHeight / controlPanelShadowHeight) : (ListenerUtil.mutListener.listen(30179) ? (controlPanelHeight * controlPanelShadowHeight) : (ListenerUtil.mutListener.listen(30178) ? (controlPanelHeight + controlPanelShadowHeight) : (controlPanelHeight - controlPanelShadowHeight))))) - dragHandleHeight) : ((ListenerUtil.mutListener.listen(30181) ? (controlPanelHeight % controlPanelShadowHeight) : (ListenerUtil.mutListener.listen(30180) ? (controlPanelHeight / controlPanelShadowHeight) : (ListenerUtil.mutListener.listen(30179) ? (controlPanelHeight * controlPanelShadowHeight) : (ListenerUtil.mutListener.listen(30178) ? (controlPanelHeight + controlPanelShadowHeight) : (controlPanelHeight - controlPanelShadowHeight))))) + dragHandleHeight))))) % (ListenerUtil.mutListener.listen(30189) ? (dragHandleTopBottomMargin % 2) : (ListenerUtil.mutListener.listen(30188) ? (dragHandleTopBottomMargin / 2) : (ListenerUtil.mutListener.listen(30187) ? (dragHandleTopBottomMargin - 2) : (ListenerUtil.mutListener.listen(30186) ? (dragHandleTopBottomMargin + 2) : (dragHandleTopBottomMargin * 2)))))) : (ListenerUtil.mutListener.listen(30192) ? ((ListenerUtil.mutListener.listen(30185) ? ((ListenerUtil.mutListener.listen(30181) ? (controlPanelHeight % controlPanelShadowHeight) : (ListenerUtil.mutListener.listen(30180) ? (controlPanelHeight / controlPanelShadowHeight) : (ListenerUtil.mutListener.listen(30179) ? (controlPanelHeight * controlPanelShadowHeight) : (ListenerUtil.mutListener.listen(30178) ? (controlPanelHeight + controlPanelShadowHeight) : (controlPanelHeight - controlPanelShadowHeight))))) % dragHandleHeight) : (ListenerUtil.mutListener.listen(30184) ? ((ListenerUtil.mutListener.listen(30181) ? (controlPanelHeight % controlPanelShadowHeight) : (ListenerUtil.mutListener.listen(30180) ? (controlPanelHeight / controlPanelShadowHeight) : (ListenerUtil.mutListener.listen(30179) ? (controlPanelHeight * controlPanelShadowHeight) : (ListenerUtil.mutListener.listen(30178) ? (controlPanelHeight + controlPanelShadowHeight) : (controlPanelHeight - controlPanelShadowHeight))))) / dragHandleHeight) : (ListenerUtil.mutListener.listen(30183) ? ((ListenerUtil.mutListener.listen(30181) ? (controlPanelHeight % controlPanelShadowHeight) : (ListenerUtil.mutListener.listen(30180) ? (controlPanelHeight / controlPanelShadowHeight) : (ListenerUtil.mutListener.listen(30179) ? (controlPanelHeight * controlPanelShadowHeight) : (ListenerUtil.mutListener.listen(30178) ? (controlPanelHeight + controlPanelShadowHeight) : (controlPanelHeight - controlPanelShadowHeight))))) * dragHandleHeight) : (ListenerUtil.mutListener.listen(30182) ? ((ListenerUtil.mutListener.listen(30181) ? (controlPanelHeight % controlPanelShadowHeight) : (ListenerUtil.mutListener.listen(30180) ? (controlPanelHeight / controlPanelShadowHeight) : (ListenerUtil.mutListener.listen(30179) ? (controlPanelHeight * controlPanelShadowHeight) : (ListenerUtil.mutListener.listen(30178) ? (controlPanelHeight + controlPanelShadowHeight) : (controlPanelHeight - controlPanelShadowHeight))))) - dragHandleHeight) : ((ListenerUtil.mutListener.listen(30181) ? (controlPanelHeight % controlPanelShadowHeight) : (ListenerUtil.mutListener.listen(30180) ? (controlPanelHeight / controlPanelShadowHeight) : (ListenerUtil.mutListener.listen(30179) ? (controlPanelHeight * controlPanelShadowHeight) : (ListenerUtil.mutListener.listen(30178) ? (controlPanelHeight + controlPanelShadowHeight) : (controlPanelHeight - controlPanelShadowHeight))))) + dragHandleHeight))))) / (ListenerUtil.mutListener.listen(30189) ? (dragHandleTopBottomMargin % 2) : (ListenerUtil.mutListener.listen(30188) ? (dragHandleTopBottomMargin / 2) : (ListenerUtil.mutListener.listen(30187) ? (dragHandleTopBottomMargin - 2) : (ListenerUtil.mutListener.listen(30186) ? (dragHandleTopBottomMargin + 2) : (dragHandleTopBottomMargin * 2)))))) : (ListenerUtil.mutListener.listen(30191) ? ((ListenerUtil.mutListener.listen(30185) ? ((ListenerUtil.mutListener.listen(30181) ? (controlPanelHeight % controlPanelShadowHeight) : (ListenerUtil.mutListener.listen(30180) ? (controlPanelHeight / controlPanelShadowHeight) : (ListenerUtil.mutListener.listen(30179) ? (controlPanelHeight * controlPanelShadowHeight) : (ListenerUtil.mutListener.listen(30178) ? (controlPanelHeight + controlPanelShadowHeight) : (controlPanelHeight - controlPanelShadowHeight))))) % dragHandleHeight) : (ListenerUtil.mutListener.listen(30184) ? ((ListenerUtil.mutListener.listen(30181) ? (controlPanelHeight % controlPanelShadowHeight) : (ListenerUtil.mutListener.listen(30180) ? (controlPanelHeight / controlPanelShadowHeight) : (ListenerUtil.mutListener.listen(30179) ? (controlPanelHeight * controlPanelShadowHeight) : (ListenerUtil.mutListener.listen(30178) ? (controlPanelHeight + controlPanelShadowHeight) : (controlPanelHeight - controlPanelShadowHeight))))) / dragHandleHeight) : (ListenerUtil.mutListener.listen(30183) ? ((ListenerUtil.mutListener.listen(30181) ? (controlPanelHeight % controlPanelShadowHeight) : (ListenerUtil.mutListener.listen(30180) ? (controlPanelHeight / controlPanelShadowHeight) : (ListenerUtil.mutListener.listen(30179) ? (controlPanelHeight * controlPanelShadowHeight) : (ListenerUtil.mutListener.listen(30178) ? (controlPanelHeight + controlPanelShadowHeight) : (controlPanelHeight - controlPanelShadowHeight))))) * dragHandleHeight) : (ListenerUtil.mutListener.listen(30182) ? ((ListenerUtil.mutListener.listen(30181) ? (controlPanelHeight % controlPanelShadowHeight) : (ListenerUtil.mutListener.listen(30180) ? (controlPanelHeight / controlPanelShadowHeight) : (ListenerUtil.mutListener.listen(30179) ? (controlPanelHeight * controlPanelShadowHeight) : (ListenerUtil.mutListener.listen(30178) ? (controlPanelHeight + controlPanelShadowHeight) : (controlPanelHeight - controlPanelShadowHeight))))) - dragHandleHeight) : ((ListenerUtil.mutListener.listen(30181) ? (controlPanelHeight % controlPanelShadowHeight) : (ListenerUtil.mutListener.listen(30180) ? (controlPanelHeight / controlPanelShadowHeight) : (ListenerUtil.mutListener.listen(30179) ? (controlPanelHeight * controlPanelShadowHeight) : (ListenerUtil.mutListener.listen(30178) ? (controlPanelHeight + controlPanelShadowHeight) : (controlPanelHeight - controlPanelShadowHeight))))) + dragHandleHeight))))) * (ListenerUtil.mutListener.listen(30189) ? (dragHandleTopBottomMargin % 2) : (ListenerUtil.mutListener.listen(30188) ? (dragHandleTopBottomMargin / 2) : (ListenerUtil.mutListener.listen(30187) ? (dragHandleTopBottomMargin - 2) : (ListenerUtil.mutListener.listen(30186) ? (dragHandleTopBottomMargin + 2) : (dragHandleTopBottomMargin * 2)))))) : (ListenerUtil.mutListener.listen(30190) ? ((ListenerUtil.mutListener.listen(30185) ? ((ListenerUtil.mutListener.listen(30181) ? (controlPanelHeight % controlPanelShadowHeight) : (ListenerUtil.mutListener.listen(30180) ? (controlPanelHeight / controlPanelShadowHeight) : (ListenerUtil.mutListener.listen(30179) ? (controlPanelHeight * controlPanelShadowHeight) : (ListenerUtil.mutListener.listen(30178) ? (controlPanelHeight + controlPanelShadowHeight) : (controlPanelHeight - controlPanelShadowHeight))))) % dragHandleHeight) : (ListenerUtil.mutListener.listen(30184) ? ((ListenerUtil.mutListener.listen(30181) ? (controlPanelHeight % controlPanelShadowHeight) : (ListenerUtil.mutListener.listen(30180) ? (controlPanelHeight / controlPanelShadowHeight) : (ListenerUtil.mutListener.listen(30179) ? (controlPanelHeight * controlPanelShadowHeight) : (ListenerUtil.mutListener.listen(30178) ? (controlPanelHeight + controlPanelShadowHeight) : (controlPanelHeight - controlPanelShadowHeight))))) / dragHandleHeight) : (ListenerUtil.mutListener.listen(30183) ? ((ListenerUtil.mutListener.listen(30181) ? (controlPanelHeight % controlPanelShadowHeight) : (ListenerUtil.mutListener.listen(30180) ? (controlPanelHeight / controlPanelShadowHeight) : (ListenerUtil.mutListener.listen(30179) ? (controlPanelHeight * controlPanelShadowHeight) : (ListenerUtil.mutListener.listen(30178) ? (controlPanelHeight + controlPanelShadowHeight) : (controlPanelHeight - controlPanelShadowHeight))))) * dragHandleHeight) : (ListenerUtil.mutListener.listen(30182) ? ((ListenerUtil.mutListener.listen(30181) ? (controlPanelHeight % controlPanelShadowHeight) : (ListenerUtil.mutListener.listen(30180) ? (controlPanelHeight / controlPanelShadowHeight) : (ListenerUtil.mutListener.listen(30179) ? (controlPanelHeight * controlPanelShadowHeight) : (ListenerUtil.mutListener.listen(30178) ? (controlPanelHeight + controlPanelShadowHeight) : (controlPanelHeight - controlPanelShadowHeight))))) - dragHandleHeight) : ((ListenerUtil.mutListener.listen(30181) ? (controlPanelHeight % controlPanelShadowHeight) : (ListenerUtil.mutListener.listen(30180) ? (controlPanelHeight / controlPanelShadowHeight) : (ListenerUtil.mutListener.listen(30179) ? (controlPanelHeight * controlPanelShadowHeight) : (ListenerUtil.mutListener.listen(30178) ? (controlPanelHeight + controlPanelShadowHeight) : (controlPanelHeight - controlPanelShadowHeight))))) + dragHandleHeight))))) - (ListenerUtil.mutListener.listen(30189) ? (dragHandleTopBottomMargin % 2) : (ListenerUtil.mutListener.listen(30188) ? (dragHandleTopBottomMargin / 2) : (ListenerUtil.mutListener.listen(30187) ? (dragHandleTopBottomMargin - 2) : (ListenerUtil.mutListener.listen(30186) ? (dragHandleTopBottomMargin + 2) : (dragHandleTopBottomMargin * 2)))))) : ((ListenerUtil.mutListener.listen(30185) ? ((ListenerUtil.mutListener.listen(30181) ? (controlPanelHeight % controlPanelShadowHeight) : (ListenerUtil.mutListener.listen(30180) ? (controlPanelHeight / controlPanelShadowHeight) : (ListenerUtil.mutListener.listen(30179) ? (controlPanelHeight * controlPanelShadowHeight) : (ListenerUtil.mutListener.listen(30178) ? (controlPanelHeight + controlPanelShadowHeight) : (controlPanelHeight - controlPanelShadowHeight))))) % dragHandleHeight) : (ListenerUtil.mutListener.listen(30184) ? ((ListenerUtil.mutListener.listen(30181) ? (controlPanelHeight % controlPanelShadowHeight) : (ListenerUtil.mutListener.listen(30180) ? (controlPanelHeight / controlPanelShadowHeight) : (ListenerUtil.mutListener.listen(30179) ? (controlPanelHeight * controlPanelShadowHeight) : (ListenerUtil.mutListener.listen(30178) ? (controlPanelHeight + controlPanelShadowHeight) : (controlPanelHeight - controlPanelShadowHeight))))) / dragHandleHeight) : (ListenerUtil.mutListener.listen(30183) ? ((ListenerUtil.mutListener.listen(30181) ? (controlPanelHeight % controlPanelShadowHeight) : (ListenerUtil.mutListener.listen(30180) ? (controlPanelHeight / controlPanelShadowHeight) : (ListenerUtil.mutListener.listen(30179) ? (controlPanelHeight * controlPanelShadowHeight) : (ListenerUtil.mutListener.listen(30178) ? (controlPanelHeight + controlPanelShadowHeight) : (controlPanelHeight - controlPanelShadowHeight))))) * dragHandleHeight) : (ListenerUtil.mutListener.listen(30182) ? ((ListenerUtil.mutListener.listen(30181) ? (controlPanelHeight % controlPanelShadowHeight) : (ListenerUtil.mutListener.listen(30180) ? (controlPanelHeight / controlPanelShadowHeight) : (ListenerUtil.mutListener.listen(30179) ? (controlPanelHeight * controlPanelShadowHeight) : (ListenerUtil.mutListener.listen(30178) ? (controlPanelHeight + controlPanelShadowHeight) : (controlPanelHeight - controlPanelShadowHeight))))) - dragHandleHeight) : ((ListenerUtil.mutListener.listen(30181) ? (controlPanelHeight % controlPanelShadowHeight) : (ListenerUtil.mutListener.listen(30180) ? (controlPanelHeight / controlPanelShadowHeight) : (ListenerUtil.mutListener.listen(30179) ? (controlPanelHeight * controlPanelShadowHeight) : (ListenerUtil.mutListener.listen(30178) ? (controlPanelHeight + controlPanelShadowHeight) : (controlPanelHeight - controlPanelShadowHeight))))) + dragHandleHeight))))) + (ListenerUtil.mutListener.listen(30189) ? (dragHandleTopBottomMargin % 2) : (ListenerUtil.mutListener.listen(30188) ? (dragHandleTopBottomMargin / 2) : (ListenerUtil.mutListener.listen(30187) ? (dragHandleTopBottomMargin - 2) : (ListenerUtil.mutListener.listen(30186) ? (dragHandleTopBottomMargin + 2) : (dragHandleTopBottomMargin * 2))))))))));
                boolean peekHeightKnown;
                if ((ListenerUtil.mutListener.listen(30199) ? ((ListenerUtil.mutListener.listen(30198) ? (numItems >= 0) : (ListenerUtil.mutListener.listen(30197) ? (numItems <= 0) : (ListenerUtil.mutListener.listen(30196) ? (numItems < 0) : (ListenerUtil.mutListener.listen(30195) ? (numItems != 0) : (ListenerUtil.mutListener.listen(30194) ? (numItems == 0) : (numItems > 0)))))) || mediaAttachRecyclerView.getChildAt(0) != null) : ((ListenerUtil.mutListener.listen(30198) ? (numItems >= 0) : (ListenerUtil.mutListener.listen(30197) ? (numItems <= 0) : (ListenerUtil.mutListener.listen(30196) ? (numItems < 0) : (ListenerUtil.mutListener.listen(30195) ? (numItems != 0) : (ListenerUtil.mutListener.listen(30194) ? (numItems == 0) : (numItems > 0)))))) && mediaAttachRecyclerView.getChildAt(0) != null))) {
                    if (!ListenerUtil.mutListener.listen(30205)) {
                        // Child views are already here, we can calculate the total height
                        peekHeight += (ListenerUtil.mutListener.listen(30204) ? (mediaAttachRecyclerView.getChildAt(0).getHeight() % numElements) : (ListenerUtil.mutListener.listen(30203) ? (mediaAttachRecyclerView.getChildAt(0).getHeight() / numElements) : (ListenerUtil.mutListener.listen(30202) ? (mediaAttachRecyclerView.getChildAt(0).getHeight() - numElements) : (ListenerUtil.mutListener.listen(30201) ? (mediaAttachRecyclerView.getChildAt(0).getHeight() + numElements) : (mediaAttachRecyclerView.getChildAt(0).getHeight() * numElements)))));
                    }
                    if (!ListenerUtil.mutListener.listen(30210)) {
                        peekHeight += (ListenerUtil.mutListener.listen(30209) ? (MediaSelectionBaseActivity.this.getResources().getDimensionPixelSize(R.dimen.grid_spacing) % numElements) : (ListenerUtil.mutListener.listen(30208) ? (MediaSelectionBaseActivity.this.getResources().getDimensionPixelSize(R.dimen.grid_spacing) / numElements) : (ListenerUtil.mutListener.listen(30207) ? (MediaSelectionBaseActivity.this.getResources().getDimensionPixelSize(R.dimen.grid_spacing) - numElements) : (ListenerUtil.mutListener.listen(30206) ? (MediaSelectionBaseActivity.this.getResources().getDimensionPixelSize(R.dimen.grid_spacing) + numElements) : (MediaSelectionBaseActivity.this.getResources().getDimensionPixelSize(R.dimen.grid_spacing) * numElements)))));
                    }
                    peekHeightKnown = true;
                } else {
                    // Child views aren't initialized yet
                    peekHeightKnown = false;
                    if (!ListenerUtil.mutListener.listen(30200)) {
                        logger.debug("Peek height could not yet be determined, no items found");
                    }
                }
                if (!ListenerUtil.mutListener.listen(30212)) {
                    if (bottomSheetBehavior != null) {
                        if (!ListenerUtil.mutListener.listen(30211)) {
                            bottomSheetBehavior.setPeekHeight(peekHeight);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(30217)) {
                    // Recalculate the peek height when the layout changes the next time
                    if (!peekHeightKnown) {
                        if (!ListenerUtil.mutListener.listen(30216)) {
                            rootView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

                                @Override
                                public void onGlobalLayout() {
                                    if (!ListenerUtil.mutListener.listen(30213)) {
                                        logger.debug("onGlobalLayoutListener");
                                    }
                                    if (!ListenerUtil.mutListener.listen(30214)) {
                                        // Run only once
                                        rootView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                                    }
                                    if (!ListenerUtil.mutListener.listen(30215)) {
                                        // Update peek height again
                                        MediaSelectionBaseActivity.this.updatePeekHeight();
                                    }
                                }
                            });
                        }
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(30174)) {
                    bottomSheetBehavior.setPeekHeight(0);
                }
                if (!ListenerUtil.mutListener.listen(30175)) {
                    bottomSheetLayout.setVisibility(View.GONE);
                }
            }
        }
    }

    protected void setFirstVisibleItemDate() {
        int firstVisible = gridLayoutManager.findFirstVisibleItemPosition();
        if (!ListenerUtil.mutListener.listen(30226)) {
            if ((ListenerUtil.mutListener.listen(30223) ? (firstVisible <= 0) : (ListenerUtil.mutListener.listen(30222) ? (firstVisible > 0) : (ListenerUtil.mutListener.listen(30221) ? (firstVisible < 0) : (ListenerUtil.mutListener.listen(30220) ? (firstVisible != 0) : (ListenerUtil.mutListener.listen(30219) ? (firstVisible == 0) : (firstVisible >= 0))))))) {
                MediaAttachItem item = mediaAttachAdapter.getMediaItems().get(firstVisible);
                if (!ListenerUtil.mutListener.listen(30225)) {
                    dateView.post(() -> dateTextView.setText(LocaleUtil.formatDateRelative(MediaSelectionBaseActivity.this, item.getDateModified() * 1000)));
                }
            } else {
                if (!ListenerUtil.mutListener.listen(30224)) {
                    dateView.post(() -> {
                        dateTextView.setText(R.string.no_media_found_global);
                    });
                }
            }
        }
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (!ListenerUtil.mutListener.listen(30241)) {
            switch(id) {
                case R.id.collapsing_toolbar:
                    if (!ListenerUtil.mutListener.listen(30227)) {
                        finish();
                    }
                    break;
                // finish when clicking transparent area showing the chat behind the attacher
                case R.id.cancel:
                    if (!ListenerUtil.mutListener.listen(30238)) {
                        if (mediaAttachAdapter != null) {
                            if (!ListenerUtil.mutListener.listen(30235)) {
                                {
                                    long _loopCounter203 = 0;
                                    for (int childCount = mediaAttachRecyclerView.getChildCount(), i = 0; (ListenerUtil.mutListener.listen(30234) ? (i >= childCount) : (ListenerUtil.mutListener.listen(30233) ? (i <= childCount) : (ListenerUtil.mutListener.listen(30232) ? (i > childCount) : (ListenerUtil.mutListener.listen(30231) ? (i != childCount) : (ListenerUtil.mutListener.listen(30230) ? (i == childCount) : (i < childCount)))))); ++i) {
                                        ListenerUtil.loopListener.listen("_loopCounter203", ++_loopCounter203);
                                        final RecyclerView.ViewHolder holder = mediaAttachRecyclerView.getChildViewHolder(mediaAttachRecyclerView.getChildAt(i));
                                        if (!ListenerUtil.mutListener.listen(30229)) {
                                            if (mediaAttachViewModel.getSelectedMediaItemsHashMap().containsKey(((MediaAttachAdapter.MediaGalleryHolder) holder).itemId)) {
                                                final CheckableFrameLayout checkableFrameLayout = ((MediaAttachAdapter.MediaGalleryHolder) holder).contentView;
                                                if (!ListenerUtil.mutListener.listen(30228)) {
                                                    checkableFrameLayout.setChecked(false);
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(30236)) {
                                mediaAttachViewModel.clearSelection();
                            }
                            if (!ListenerUtil.mutListener.listen(30237)) {
                                onItemChecked(0);
                            }
                        }
                    }
                    break;
                case R.id.select_counter_button:
                    if (!ListenerUtil.mutListener.listen(30240)) {
                        if (mediaAttachAdapter != null) {
                            if (!ListenerUtil.mutListener.listen(30239)) {
                                filterMediaBySelectedItems();
                            }
                        }
                    }
                    break;
            }
        }
    }

    public abstract void onItemChecked(int count);

    protected void showPermissionRationale(int stringResource) {
        if (!ListenerUtil.mutListener.listen(30242)) {
            ConfigUtils.showPermissionRationale(this, rootView, stringResource);
        }
    }

    public void checkMasterKey() {
        MasterKey masterKey = ThreemaApplication.getMasterKey();
        if (!ListenerUtil.mutListener.listen(30249)) {
            if ((ListenerUtil.mutListener.listen(30243) ? (masterKey != null || masterKey.isLocked()) : (masterKey != null && masterKey.isLocked()))) {
                if (!ListenerUtil.mutListener.listen(30248)) {
                    startActivityForResult(new Intent(this, UnlockMasterKeyActivity.class), ThreemaActivity.ACTIVITY_ID_UNLOCK_MASTER_KEY);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(30247)) {
                    if ((ListenerUtil.mutListener.listen(30244) ? (ConfigUtils.isSerialLicensed() || !ConfigUtils.isSerialLicenseValid()) : (ConfigUtils.isSerialLicensed() && !ConfigUtils.isSerialLicenseValid()))) {
                        if (!ListenerUtil.mutListener.listen(30245)) {
                            startActivity(new Intent(this, EnterSerialActivity.class));
                        }
                        if (!ListenerUtil.mutListener.listen(30246)) {
                            finish();
                        }
                    }
                }
            }
        }
    }

    /**
     *  Return true if split screen / multi window mode is enabled.
     */
    protected boolean isInSplitScreenMode() {
        if ((ListenerUtil.mutListener.listen(30254) ? (Build.VERSION.SDK_INT <= 24) : (ListenerUtil.mutListener.listen(30253) ? (Build.VERSION.SDK_INT > 24) : (ListenerUtil.mutListener.listen(30252) ? (Build.VERSION.SDK_INT < 24) : (ListenerUtil.mutListener.listen(30251) ? (Build.VERSION.SDK_INT != 24) : (ListenerUtil.mutListener.listen(30250) ? (Build.VERSION.SDK_INT == 24) : (Build.VERSION.SDK_INT >= 24))))))) {
            return isInMultiWindowMode();
        } else {
            return false;
        }
    }

    protected void attachImageFromGallery() {
        if (!ListenerUtil.mutListener.listen(30255)) {
            FileUtil.selectFromGallery(this, null, REQUEST_CODE_ATTACH_FROM_GALLERY, true);
        }
    }

    protected void expandBottomSheet() {
        if (!ListenerUtil.mutListener.listen(30256)) {
            updateUI(BottomSheetBehavior.STATE_EXPANDED);
        }
    }

    protected void collapseBottomSheet() {
        Animation animation = toolbar.getAnimation();
        if (!ListenerUtil.mutListener.listen(30258)) {
            if (animation != null) {
                if (!ListenerUtil.mutListener.listen(30257)) {
                    animation.cancel();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(30259)) {
            dragHandle.setVisibility(View.VISIBLE);
        }
        if (!ListenerUtil.mutListener.listen(30260)) {
            toolbar.setVisibility(View.GONE);
        }
        if (!ListenerUtil.mutListener.listen(30261)) {
            toolbar.post(() -> {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    getWindow().setStatusBarColor(ConfigUtils.getColorFromAttribute(this, R.attr.attach_status_bar_color_collapsed));
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(30262)) {
            updateUI(STATE_COLLAPSED);
        }
    }
}
