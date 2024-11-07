package org.wordpress.android.ui.media;

import android.Manifest;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuItem.OnActionExpandListener;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.view.ActionMode;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.SearchView.OnQueryTextListener;
import androidx.core.view.MenuItemCompat;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentManager.OnBackStackChangedListener;
import androidx.fragment.app.FragmentTransaction;
import com.google.android.material.tabs.TabLayout;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.jetbrains.annotations.NotNull;
import org.wordpress.android.BuildConfig;
import org.wordpress.android.R;
import org.wordpress.android.WordPress;
import org.wordpress.android.analytics.AnalyticsTracker;
import org.wordpress.android.fluxc.Dispatcher;
import org.wordpress.android.fluxc.generated.MediaActionBuilder;
import org.wordpress.android.fluxc.generated.SiteActionBuilder;
import org.wordpress.android.fluxc.model.MediaModel;
import org.wordpress.android.fluxc.model.MediaModel.MediaUploadState;
import org.wordpress.android.fluxc.model.SiteModel;
import org.wordpress.android.fluxc.store.MediaStore;
import org.wordpress.android.fluxc.store.MediaStore.CancelMediaPayload;
import org.wordpress.android.fluxc.store.MediaStore.OnMediaChanged;
import org.wordpress.android.fluxc.store.MediaStore.OnMediaListFetched;
import org.wordpress.android.fluxc.store.MediaStore.OnMediaUploaded;
import org.wordpress.android.fluxc.store.QuickStartStore.QuickStartExistingSiteTask;
import org.wordpress.android.fluxc.store.SiteStore;
import org.wordpress.android.fluxc.store.SiteStore.OnSiteChanged;
import org.wordpress.android.push.NotificationType;
import org.wordpress.android.ui.ActivityId;
import org.wordpress.android.ui.LocaleAwareActivity;
import org.wordpress.android.ui.RequestCodes;
import org.wordpress.android.ui.media.MediaGridFragment.MediaFilter;
import org.wordpress.android.ui.media.MediaGridFragment.MediaGridListener;
import org.wordpress.android.ui.media.services.MediaDeleteService;
import org.wordpress.android.ui.mysite.SelectedSiteRepository;
import org.wordpress.android.ui.mysite.cards.quickstart.QuickStartRepository;
import org.wordpress.android.ui.notifications.SystemNotificationsTracker;
import org.wordpress.android.ui.photopicker.MediaPickerConstants;
import org.wordpress.android.ui.photopicker.MediaPickerLauncher;
import org.wordpress.android.ui.plans.PlansConstants;
import org.wordpress.android.ui.uploads.UploadService;
import org.wordpress.android.ui.uploads.UploadUtilsWrapper;
import org.wordpress.android.util.ActivityUtils;
import org.wordpress.android.util.AppLog;
import org.wordpress.android.util.DisplayUtils;
import org.wordpress.android.util.FluxCUtils;
import org.wordpress.android.util.FormatUtils;
import org.wordpress.android.util.ListUtils;
import org.wordpress.android.util.MediaUtils;
import org.wordpress.android.util.MediaUtilsWrapper;
import org.wordpress.android.util.NetworkUtils;
import org.wordpress.android.util.PermissionUtils;
import org.wordpress.android.util.ToastUtils;
import org.wordpress.android.util.WPMediaUtils;
import org.wordpress.android.util.WPPermissionUtils;
import org.wordpress.android.util.analytics.AnalyticsUtils;
import org.wordpress.android.widgets.AppRatingDialog;
import org.wordpress.android.widgets.QuickStartFocusPoint;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.inject.Inject;
import static org.wordpress.android.analytics.AnalyticsTracker.Stat.APP_REVIEWS_EVENT_INCREMENTED_BY_UPLOADING_MEDIA;
import static org.wordpress.android.push.NotificationsProcessingService.ARG_NOTIFICATION_TYPE;
import static org.wordpress.android.util.ToastUtils.Duration.LONG;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * The main activity in which the user can browse their media.
 */
public class MediaBrowserActivity extends LocaleAwareActivity implements MediaGridListener, OnQueryTextListener, OnActionExpandListener, WPMediaUtils.LaunchCameraCallback {

    public static final String ARG_BROWSER_TYPE = "media_browser_type";

    public static final String ARG_FILTER = "filter";

    public static final String RESULT_IDS = "result_ids";

    private static final String SAVED_QUERY = "SAVED_QUERY";

    private static final String BUNDLE_MEDIA_CAPTURE_PATH = "mediaCapturePath";

    private static final String SHOW_AUDIO_TAB = "showAudioTab";

    @Inject
    Dispatcher mDispatcher;

    @Inject
    MediaStore mMediaStore;

    @Inject
    SiteStore mSiteStore;

    @Inject
    UploadUtilsWrapper mUploadUtilsWrapper;

    @Inject
    SystemNotificationsTracker mSystemNotificationsTracker;

    @Inject
    MediaPickerLauncher mMediaPickerLauncher;

    @Inject
    MediaUtilsWrapper mMediaUtilsWrapper;

    @Inject
    QuickStartRepository mQuickStartRepository;

    @Inject
    SelectedSiteRepository mSelectedSiteRepository;

    private SiteModel mSite;

    private MediaGridFragment mMediaGridFragment;

    private SearchView mSearchView;

    private MenuItem mSearchMenuItem;

    private Menu mMenu;

    private TabLayout mTabLayout;

    private RelativeLayout mQuotaBar;

    private TextView mQuotaText;

    private MediaDeleteService.MediaDeleteBinder mDeleteService;

    private boolean mDeleteServiceBound;

    private String mQuery;

    private String mMediaCapturePath;

    private MediaBrowserType mBrowserType;

    private AddMenuItem mLastAddMediaItemClicked;

    private MenuItem menuNewMediaItem;

    private QuickStartFocusPoint mMenuNewMediaQuickStartFocusPoint;

    private boolean mShowAudioTab;

    private enum AddMenuItem {

        ITEM_CAPTURE_PHOTO, ITEM_CAPTURE_VIDEO, ITEM_CHOOSE_FILE, ITEM_CHOOSE_STOCK_MEDIA, ITEM_CHOOSE_GIF
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(6439)) {
            super.onCreate(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(6440)) {
            ((WordPress) getApplication()).component().inject(this);
        }
        if (!ListenerUtil.mutListener.listen(6454)) {
            if (savedInstanceState == null) {
                if (!ListenerUtil.mutListener.listen(6446)) {
                    mSite = (SiteModel) getIntent().getSerializableExtra(WordPress.SITE);
                }
                if (!ListenerUtil.mutListener.listen(6447)) {
                    mBrowserType = (MediaBrowserType) getIntent().getSerializableExtra(ARG_BROWSER_TYPE);
                }
                if (!ListenerUtil.mutListener.listen(6453)) {
                    mShowAudioTab = (ListenerUtil.mutListener.listen(6452) ? (mMediaStore.getSiteAudio(mSite).size() >= 0) : (ListenerUtil.mutListener.listen(6451) ? (mMediaStore.getSiteAudio(mSite).size() <= 0) : (ListenerUtil.mutListener.listen(6450) ? (mMediaStore.getSiteAudio(mSite).size() < 0) : (ListenerUtil.mutListener.listen(6449) ? (mMediaStore.getSiteAudio(mSite).size() != 0) : (ListenerUtil.mutListener.listen(6448) ? (mMediaStore.getSiteAudio(mSite).size() == 0) : (mMediaStore.getSiteAudio(mSite).size() > 0))))));
                }
            } else {
                if (!ListenerUtil.mutListener.listen(6441)) {
                    mSite = (SiteModel) savedInstanceState.getSerializable(WordPress.SITE);
                }
                if (!ListenerUtil.mutListener.listen(6442)) {
                    mBrowserType = (MediaBrowserType) savedInstanceState.getSerializable(ARG_BROWSER_TYPE);
                }
                if (!ListenerUtil.mutListener.listen(6443)) {
                    mMediaCapturePath = savedInstanceState.getString(BUNDLE_MEDIA_CAPTURE_PATH);
                }
                if (!ListenerUtil.mutListener.listen(6444)) {
                    mQuery = savedInstanceState.getString(SAVED_QUERY);
                }
                if (!ListenerUtil.mutListener.listen(6445)) {
                    mShowAudioTab = savedInstanceState.getBoolean(SHOW_AUDIO_TAB);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(6457)) {
            if (mSite == null) {
                if (!ListenerUtil.mutListener.listen(6455)) {
                    ToastUtils.showToast(this, R.string.blog_not_found, ToastUtils.Duration.SHORT);
                }
                if (!ListenerUtil.mutListener.listen(6456)) {
                    finish();
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(6460)) {
            if (mBrowserType == null) {
                if (!ListenerUtil.mutListener.listen(6458)) {
                    // default to browser mode if missing type
                    AppLog.w(AppLog.T.MEDIA, "MediaBrowserType is null. Defaulting to MediaBrowserType.BROWSER mode.");
                }
                if (!ListenerUtil.mutListener.listen(6459)) {
                    mBrowserType = MediaBrowserType.BROWSER;
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(6461)) {
            setContentView(R.layout.media_browser_activity);
        }
        if (!ListenerUtil.mutListener.listen(6462)) {
            setSupportActionBar(findViewById(R.id.toolbar_main));
        }
        ActionBar actionBar = getSupportActionBar();
        if (!ListenerUtil.mutListener.listen(6466)) {
            if (actionBar != null) {
                if (!ListenerUtil.mutListener.listen(6463)) {
                    actionBar.setDisplayShowTitleEnabled(true);
                }
                if (!ListenerUtil.mutListener.listen(6464)) {
                    actionBar.setDisplayHomeAsUpEnabled(true);
                }
                if (!ListenerUtil.mutListener.listen(6465)) {
                    actionBar.setTitle(R.string.wp_media_title);
                }
            }
        }
        FragmentManager fm = getSupportFragmentManager();
        if (!ListenerUtil.mutListener.listen(6467)) {
            fm.addOnBackStackChangedListener(mOnBackStackChangedListener);
        }
        if (!ListenerUtil.mutListener.listen(6468)) {
            // if media was shared add it to the library
            handleSharedMedia();
        }
        if (!ListenerUtil.mutListener.listen(6469)) {
            mTabLayout = findViewById(R.id.tab_layout);
        }
        if (!ListenerUtil.mutListener.listen(6470)) {
            setupTabs();
        }
        MediaFilter filter;
        if (mBrowserType.isSingleImagePicker()) {
            filter = MediaFilter.FILTER_IMAGES;
        } else if (mBrowserType == MediaBrowserType.GUTENBERG_IMAGE_PICKER) {
            filter = MediaFilter.FILTER_IMAGES;
        } else if (mBrowserType == MediaBrowserType.GUTENBERG_VIDEO_PICKER) {
            filter = MediaFilter.FILTER_VIDEOS;
        } else if (mBrowserType == MediaBrowserType.GUTENBERG_SINGLE_AUDIO_FILE_PICKER) {
            filter = MediaFilter.FILTER_AUDIO;
        } else if (savedInstanceState != null) {
            filter = (MediaFilter) savedInstanceState.getSerializable(ARG_FILTER);
        } else {
            filter = MediaFilter.FILTER_ALL;
        }
        if (!ListenerUtil.mutListener.listen(6471)) {
            mMediaGridFragment = (MediaGridFragment) fm.findFragmentByTag(MediaGridFragment.TAG);
        }
        if (!ListenerUtil.mutListener.listen(6475)) {
            if (mMediaGridFragment == null) {
                if (!ListenerUtil.mutListener.listen(6473)) {
                    mMediaGridFragment = MediaGridFragment.newInstance(mSite, mBrowserType, filter);
                }
                if (!ListenerUtil.mutListener.listen(6474)) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.media_browser_container, mMediaGridFragment, MediaGridFragment.TAG).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).commit();
                }
            } else {
                if (!ListenerUtil.mutListener.listen(6472)) {
                    setFilter(filter);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(6476)) {
            mQuotaBar = findViewById(R.id.quota_bar);
        }
        if (!ListenerUtil.mutListener.listen(6477)) {
            mQuotaText = findViewById(R.id.quota_text);
        }
        if (!ListenerUtil.mutListener.listen(6478)) {
            showQuota(true);
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        if (!ListenerUtil.mutListener.listen(6479)) {
            super.onNewIntent(intent);
        }
        if (!ListenerUtil.mutListener.listen(6482)) {
            if (intent.hasExtra(ARG_NOTIFICATION_TYPE)) {
                NotificationType notificationType = (NotificationType) intent.getSerializableExtra(ARG_NOTIFICATION_TYPE);
                if (!ListenerUtil.mutListener.listen(6481)) {
                    if (notificationType != null) {
                        if (!ListenerUtil.mutListener.listen(6480)) {
                            mSystemNotificationsTracker.trackTappedNotification(notificationType);
                        }
                    }
                }
            }
        }
    }

    private void formatQuotaDiskSpace() {
        final String[] units = new String[] { getString(R.string.file_size_in_bytes), getString(R.string.file_size_in_kilobytes), getString(R.string.file_size_in_megabytes), getString(R.string.file_size_in_gigabytes), getString(R.string.file_size_in_terabytes) };
        String quota;
        if (mSite.getPlanId() == PlansConstants.BUSINESS_PLAN_ID) {
            String space = FormatUtils.formatFileSize(mSite.getSpaceUsed(), units);
            quota = String.format(getString(R.string.site_settings_quota_space_unlimited), space);
        } else {
            String percentage = FormatUtils.formatPercentageLimit100((ListenerUtil.mutListener.listen(6486) ? (mSite.getSpacePercentUsed() % 100) : (ListenerUtil.mutListener.listen(6485) ? (mSite.getSpacePercentUsed() * 100) : (ListenerUtil.mutListener.listen(6484) ? (mSite.getSpacePercentUsed() - 100) : (ListenerUtil.mutListener.listen(6483) ? (mSite.getSpacePercentUsed() + 100) : (mSite.getSpacePercentUsed() / 100))))), true);
            String space = FormatUtils.formatFileSize(mSite.getSpaceAllowed(), units);
            quota = String.format(getString(R.string.site_settings_quota_space_value), percentage, space);
        }
        if (!ListenerUtil.mutListener.listen(6487)) {
            mQuotaText.setText(getString(R.string.media_space_used, quota));
        }
        if (!ListenerUtil.mutListener.listen(6493)) {
            mQuotaText.setTextColor(getResources().getColor((ListenerUtil.mutListener.listen(6492) ? (mSite.getSpacePercentUsed() >= 90) : (ListenerUtil.mutListener.listen(6491) ? (mSite.getSpacePercentUsed() <= 90) : (ListenerUtil.mutListener.listen(6490) ? (mSite.getSpacePercentUsed() < 90) : (ListenerUtil.mutListener.listen(6489) ? (mSite.getSpacePercentUsed() != 90) : (ListenerUtil.mutListener.listen(6488) ? (mSite.getSpacePercentUsed() == 90) : (mSite.getSpacePercentUsed() > 90)))))) ? R.color.error_50 : R.color.neutral));
        }
    }

    private void showQuota(boolean show) {
        if (!ListenerUtil.mutListener.listen(6500)) {
            if (!mBrowserType.canFilter()) {
                if (!ListenerUtil.mutListener.listen(6499)) {
                    mQuotaBar.setVisibility(View.GONE);
                }
            } else if ((ListenerUtil.mutListener.listen(6495) ? ((ListenerUtil.mutListener.listen(6494) ? (show || mSite != null) : (show && mSite != null)) || mSite.hasDiskSpaceQuotaInformation()) : ((ListenerUtil.mutListener.listen(6494) ? (show || mSite != null) : (show && mSite != null)) && mSite.hasDiskSpaceQuotaInformation()))) {
                if (!ListenerUtil.mutListener.listen(6497)) {
                    mQuotaBar.setVisibility(View.VISIBLE);
                }
                if (!ListenerUtil.mutListener.listen(6498)) {
                    formatQuotaDiskSpace();
                }
            } else if (!show) {
                if (!ListenerUtil.mutListener.listen(6496)) {
                    mQuotaBar.setVisibility(View.GONE);
                }
            }
        }
    }

    public MediaDeleteService getMediaDeleteService() {
        if (!ListenerUtil.mutListener.listen(6501)) {
            if (mDeleteService == null) {
                return null;
            }
        }
        return mDeleteService.getService();
    }

    /*
     * only show tabs when the user can filter the media by type
     */
    private boolean shouldShowTabs() {
        return mBrowserType.canFilter();
    }

    private void enableTabs(boolean enable) {
        if (!ListenerUtil.mutListener.listen(6502)) {
            if (!shouldShowTabs())
                return;
        }
        if (!ListenerUtil.mutListener.listen(6507)) {
            if ((ListenerUtil.mutListener.listen(6503) ? (enable || mTabLayout.getVisibility() != View.VISIBLE) : (enable && mTabLayout.getVisibility() != View.VISIBLE))) {
                if (!ListenerUtil.mutListener.listen(6506)) {
                    mTabLayout.setVisibility(View.VISIBLE);
                }
            } else if ((ListenerUtil.mutListener.listen(6504) ? (!enable || mTabLayout.getVisibility() == View.VISIBLE) : (!enable && mTabLayout.getVisibility() == View.VISIBLE))) {
                if (!ListenerUtil.mutListener.listen(6505)) {
                    mTabLayout.setVisibility(View.GONE);
                }
            }
        }
    }

    private void setupTabs() {
        if (!ListenerUtil.mutListener.listen(6543)) {
            if (shouldShowTabs()) {
                if (!ListenerUtil.mutListener.listen(6509)) {
                    mTabLayout.removeAllTabs();
                }
                if (!ListenerUtil.mutListener.listen(6510)) {
                    // FILTER_ALL
                    mTabLayout.addTab(mTabLayout.newTab().setText(R.string.media_all));
                }
                if (!ListenerUtil.mutListener.listen(6511)) {
                    // FILTER_IMAGES
                    mTabLayout.addTab(mTabLayout.newTab().setText(R.string.media_images));
                }
                if (!ListenerUtil.mutListener.listen(6512)) {
                    // FILTER_DOCUMENTS
                    mTabLayout.addTab(mTabLayout.newTab().setText(R.string.media_documents));
                }
                if (!ListenerUtil.mutListener.listen(6513)) {
                    // FILTER_VIDEOS
                    mTabLayout.addTab(mTabLayout.newTab().setText(R.string.media_videos));
                }
                if (!ListenerUtil.mutListener.listen(6515)) {
                    if (mShowAudioTab) {
                        if (!ListenerUtil.mutListener.listen(6514)) {
                            // FILTER_AUDIO
                            mTabLayout.addTab(mTabLayout.newTab().setText(R.string.media_audio));
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(6516)) {
                    mTabLayout.clearOnTabSelectedListeners();
                }
                if (!ListenerUtil.mutListener.listen(6519)) {
                    mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

                        @Override
                        public void onTabSelected(TabLayout.Tab tab) {
                            if (!ListenerUtil.mutListener.listen(6517)) {
                                setFilter(getFilterForPosition(tab.getPosition()));
                            }
                        }

                        @Override
                        public void onTabUnselected(TabLayout.Tab tab) {
                        }

                        @Override
                        public void onTabReselected(TabLayout.Tab tab) {
                            if (!ListenerUtil.mutListener.listen(6518)) {
                                setFilter(getFilterForPosition(tab.getPosition()));
                            }
                        }
                    });
                }
                if (!ListenerUtil.mutListener.listen(6542)) {
                    // tabMode is set to scrollable in layout, set to fixed if there's enough space to show them all
                    mTabLayout.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

                        @Override
                        public void onGlobalLayout() {
                            if (!ListenerUtil.mutListener.listen(6520)) {
                                mTabLayout.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                            }
                            if (!ListenerUtil.mutListener.listen(6541)) {
                                if ((ListenerUtil.mutListener.listen(6525) ? (mTabLayout.getChildCount() >= 0) : (ListenerUtil.mutListener.listen(6524) ? (mTabLayout.getChildCount() <= 0) : (ListenerUtil.mutListener.listen(6523) ? (mTabLayout.getChildCount() < 0) : (ListenerUtil.mutListener.listen(6522) ? (mTabLayout.getChildCount() != 0) : (ListenerUtil.mutListener.listen(6521) ? (mTabLayout.getChildCount() == 0) : (mTabLayout.getChildCount() > 0))))))) {
                                    int tabLayoutWidth = 0;
                                    LinearLayout tabFirstChild = (LinearLayout) mTabLayout.getChildAt(0);
                                    if (!ListenerUtil.mutListener.listen(6532)) {
                                        {
                                            long _loopCounter144 = 0;
                                            for (int i = 0; (ListenerUtil.mutListener.listen(6531) ? (i >= mTabLayout.getTabCount()) : (ListenerUtil.mutListener.listen(6530) ? (i <= mTabLayout.getTabCount()) : (ListenerUtil.mutListener.listen(6529) ? (i > mTabLayout.getTabCount()) : (ListenerUtil.mutListener.listen(6528) ? (i != mTabLayout.getTabCount()) : (ListenerUtil.mutListener.listen(6527) ? (i == mTabLayout.getTabCount()) : (i < mTabLayout.getTabCount())))))); i++) {
                                                ListenerUtil.loopListener.listen("_loopCounter144", ++_loopCounter144);
                                                LinearLayout tabView = (LinearLayout) (tabFirstChild.getChildAt(i));
                                                if (!ListenerUtil.mutListener.listen(6526)) {
                                                    tabLayoutWidth += (tabView.getMeasuredWidth() + ViewCompat.getPaddingStart(tabView) + ViewCompat.getPaddingEnd(tabView));
                                                }
                                            }
                                        }
                                    }
                                    int displayWidth = DisplayUtils.getWindowPixelWidth(MediaBrowserActivity.this);
                                    if (!ListenerUtil.mutListener.listen(6540)) {
                                        if ((ListenerUtil.mutListener.listen(6537) ? (tabLayoutWidth >= displayWidth) : (ListenerUtil.mutListener.listen(6536) ? (tabLayoutWidth <= displayWidth) : (ListenerUtil.mutListener.listen(6535) ? (tabLayoutWidth > displayWidth) : (ListenerUtil.mutListener.listen(6534) ? (tabLayoutWidth != displayWidth) : (ListenerUtil.mutListener.listen(6533) ? (tabLayoutWidth == displayWidth) : (tabLayoutWidth < displayWidth))))))) {
                                            if (!ListenerUtil.mutListener.listen(6538)) {
                                                mTabLayout.setTabMode(TabLayout.MODE_FIXED);
                                            }
                                            if (!ListenerUtil.mutListener.listen(6539)) {
                                                mTabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    });
                }
            } else {
                if (!ListenerUtil.mutListener.listen(6508)) {
                    mTabLayout.setVisibility(View.GONE);
                }
            }
        }
    }

    private void refreshTabs() {
        boolean hasAudio = (ListenerUtil.mutListener.listen(6548) ? (mMediaStore.getSiteAudio(mSite).size() >= 0) : (ListenerUtil.mutListener.listen(6547) ? (mMediaStore.getSiteAudio(mSite).size() <= 0) : (ListenerUtil.mutListener.listen(6546) ? (mMediaStore.getSiteAudio(mSite).size() < 0) : (ListenerUtil.mutListener.listen(6545) ? (mMediaStore.getSiteAudio(mSite).size() != 0) : (ListenerUtil.mutListener.listen(6544) ? (mMediaStore.getSiteAudio(mSite).size() == 0) : (mMediaStore.getSiteAudio(mSite).size() > 0))))));
        if (!ListenerUtil.mutListener.listen(6551)) {
            if (mShowAudioTab != hasAudio) {
                if (!ListenerUtil.mutListener.listen(6549)) {
                    mShowAudioTab = hasAudio;
                }
                if (!ListenerUtil.mutListener.listen(6550)) {
                    setupTabs();
                }
            }
        }
    }

    private int getPositionForFilter(@NonNull MediaFilter filter) {
        return filter.getValue();
    }

    private MediaFilter getFilterForPosition(int position) {
        if (!ListenerUtil.mutListener.listen(6553)) {
            {
                long _loopCounter145 = 0;
                for (MediaFilter filter : MediaFilter.values()) {
                    ListenerUtil.loopListener.listen("_loopCounter145", ++_loopCounter145);
                    if (!ListenerUtil.mutListener.listen(6552)) {
                        if (filter.getValue() == position) {
                            return filter;
                        }
                    }
                }
            }
        }
        return MediaFilter.FILTER_ALL;
    }

    private void setFilter(@NonNull MediaFilter filter) {
        int position = getPositionForFilter(filter);
        if (!ListenerUtil.mutListener.listen(6557)) {
            if ((ListenerUtil.mutListener.listen(6555) ? ((ListenerUtil.mutListener.listen(6554) ? (shouldShowTabs() || mTabLayout != null) : (shouldShowTabs() && mTabLayout != null)) || mTabLayout.getSelectedTabPosition() != position) : ((ListenerUtil.mutListener.listen(6554) ? (shouldShowTabs() || mTabLayout != null) : (shouldShowTabs() && mTabLayout != null)) && mTabLayout.getSelectedTabPosition() != position))) {
                if (!ListenerUtil.mutListener.listen(6556)) {
                    mTabLayout.setScrollPosition(position, 0f, true);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(6561)) {
            if ((ListenerUtil.mutListener.listen(6559) ? (mMediaGridFragment != null || ((ListenerUtil.mutListener.listen(6558) ? (mMediaGridFragment.getFilter() != filter && mMediaGridFragment.isEmpty()) : (mMediaGridFragment.getFilter() != filter || mMediaGridFragment.isEmpty())))) : (mMediaGridFragment != null && ((ListenerUtil.mutListener.listen(6558) ? (mMediaGridFragment.getFilter() != filter && mMediaGridFragment.isEmpty()) : (mMediaGridFragment.getFilter() != filter || mMediaGridFragment.isEmpty())))))) {
                if (!ListenerUtil.mutListener.listen(6560)) {
                    mMediaGridFragment.setFilter(filter);
                }
            }
        }
    }

    @Override
    public void onStart() {
        if (!ListenerUtil.mutListener.listen(6562)) {
            super.onStart();
        }
        if (!ListenerUtil.mutListener.listen(6563)) {
            registerReceiver(mReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
        }
        if (!ListenerUtil.mutListener.listen(6564)) {
            mDispatcher.register(this);
        }
        if (!ListenerUtil.mutListener.listen(6565)) {
            EventBus.getDefault().register(this);
        }
    }

    @Override
    protected void onPause() {
        if (!ListenerUtil.mutListener.listen(6566)) {
            super.onPause();
        }
        if (!ListenerUtil.mutListener.listen(6569)) {
            if (mSearchMenuItem != null) {
                String tempQuery = mQuery;
                if (!ListenerUtil.mutListener.listen(6567)) {
                    MenuItemCompat.collapseActionView(mSearchMenuItem);
                }
                if (!ListenerUtil.mutListener.listen(6568)) {
                    mQuery = tempQuery;
                }
            }
        }
    }

    @Override
    protected void onResume() {
        if (!ListenerUtil.mutListener.listen(6570)) {
            super.onResume();
        }
        if (!ListenerUtil.mutListener.listen(6571)) {
            startMediaDeleteService(null);
        }
        if (!ListenerUtil.mutListener.listen(6572)) {
            ActivityId.trackLastActivity(ActivityId.MEDIA);
        }
    }

    @Override
    public void onStop() {
        if (!ListenerUtil.mutListener.listen(6573)) {
            EventBus.getDefault().unregister(this);
        }
        if (!ListenerUtil.mutListener.listen(6574)) {
            unregisterReceiver(mReceiver);
        }
        if (!ListenerUtil.mutListener.listen(6575)) {
            mDispatcher.unregister(this);
        }
        if (!ListenerUtil.mutListener.listen(6576)) {
            super.onStop();
        }
    }

    @Override
    protected void onDestroy() {
        if (!ListenerUtil.mutListener.listen(6577)) {
            super.onDestroy();
        }
        if (!ListenerUtil.mutListener.listen(6578)) {
            doUnbindDeleteService();
        }
    }

    @Override
    protected void onSaveInstanceState(@NotNull Bundle outState) {
        if (!ListenerUtil.mutListener.listen(6579)) {
            super.onSaveInstanceState(outState);
        }
        if (!ListenerUtil.mutListener.listen(6580)) {
            outState.putString(SAVED_QUERY, mQuery);
        }
        if (!ListenerUtil.mutListener.listen(6581)) {
            outState.putSerializable(WordPress.SITE, mSite);
        }
        if (!ListenerUtil.mutListener.listen(6582)) {
            outState.putSerializable(ARG_BROWSER_TYPE, mBrowserType);
        }
        if (!ListenerUtil.mutListener.listen(6583)) {
            outState.putBoolean(SHOW_AUDIO_TAB, mShowAudioTab);
        }
        if (!ListenerUtil.mutListener.listen(6585)) {
            if (mMediaGridFragment != null) {
                if (!ListenerUtil.mutListener.listen(6584)) {
                    outState.putSerializable(ARG_FILTER, mMediaGridFragment.getFilter());
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(6587)) {
            if (!TextUtils.isEmpty(mMediaCapturePath)) {
                if (!ListenerUtil.mutListener.listen(6586)) {
                    outState.putString(BUNDLE_MEDIA_CAPTURE_PATH, mMediaCapturePath);
                }
            }
        }
    }

    private void getMediaFromDeviceAndTrack(Uri videoUri, int requestCode) {
        final String mimeType = getContentResolver().getType(videoUri);
        if (!ListenerUtil.mutListener.listen(6590)) {
            if (mMediaUtilsWrapper.isProhibitedVideoDuration(this, mSite, videoUri)) {
                if (!ListenerUtil.mutListener.listen(6589)) {
                    ToastUtils.showToast(this, R.string.error_media_video_duration_exceeds_limit, LONG);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(6588)) {
                    fetchMediaAndDoNext(videoUri, requestCode, mimeType);
                }
            }
        }
    }

    private void fetchMediaAndDoNext(Uri imageUri, int requestCode, String mimeType) {
        if (!ListenerUtil.mutListener.listen(6591)) {
            WPMediaUtils.fetchMediaAndDoNext(this, imageUri, uri -> queueFileForUpload(getOptimizedPictureIfNecessary(uri), mimeType));
        }
        if (!ListenerUtil.mutListener.listen(6592)) {
            trackAddMediaFromDeviceEvents(false, requestCode == RequestCodes.VIDEO_LIBRARY, imageUri);
        }
    }

    private void checkRecordedVideoDurationBeforeUploadAndTrack() {
        Uri uri = MediaUtils.getLastRecordedVideoUri(this);
        if (!ListenerUtil.mutListener.listen(6595)) {
            if (mMediaUtilsWrapper.isProhibitedVideoDuration(this, mSite, uri)) {
                if (!ListenerUtil.mutListener.listen(6594)) {
                    ToastUtils.showToast(this, R.string.error_media_video_duration_exceeds_limit, LONG);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(6593)) {
                    queueFileForUpload(uri, getContentResolver().getType(uri));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(6596)) {
            trackAddMediaFromDeviceEvents(true, true, uri);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (!ListenerUtil.mutListener.listen(6597)) {
            super.onActivityResult(requestCode, resultCode, data);
        }
        if (!ListenerUtil.mutListener.listen(6620)) {
            switch(requestCode) {
                case RequestCodes.PICTURE_LIBRARY:
                case RequestCodes.VIDEO_LIBRARY:
                case RequestCodes.FILE_LIBRARY:
                case RequestCodes.AUDIO_LIBRARY:
                    if (!ListenerUtil.mutListener.listen(6602)) {
                        if ((ListenerUtil.mutListener.listen(6598) ? (resultCode == Activity.RESULT_OK || data != null) : (resultCode == Activity.RESULT_OK && data != null))) {
                            if (!ListenerUtil.mutListener.listen(6601)) {
                                if (data.hasExtra(MediaPickerConstants.EXTRA_MEDIA_URIS)) {
                                    List<Uri> uris = convertStringArrayIntoUrisList(data.getStringArrayExtra(MediaPickerConstants.EXTRA_MEDIA_URIS));
                                    if (!ListenerUtil.mutListener.listen(6600)) {
                                        {
                                            long _loopCounter146 = 0;
                                            for (Uri uri : uris) {
                                                ListenerUtil.loopListener.listen("_loopCounter146", ++_loopCounter146);
                                                if (!ListenerUtil.mutListener.listen(6599)) {
                                                    getMediaFromDeviceAndTrack(uri, requestCode);
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    break;
                case RequestCodes.TAKE_PHOTO:
                    if (!ListenerUtil.mutListener.listen(6607)) {
                        if (resultCode == Activity.RESULT_OK) {
                            if (!ListenerUtil.mutListener.listen(6603)) {
                                WPMediaUtils.scanMediaFile(this, mMediaCapturePath);
                            }
                            Uri uri = getOptimizedPictureIfNecessary(Uri.parse(mMediaCapturePath));
                            if (!ListenerUtil.mutListener.listen(6604)) {
                                mMediaCapturePath = null;
                            }
                            if (!ListenerUtil.mutListener.listen(6605)) {
                                queueFileForUpload(uri, getContentResolver().getType(uri));
                            }
                            if (!ListenerUtil.mutListener.listen(6606)) {
                                trackAddMediaFromDeviceEvents(true, false, uri);
                            }
                        }
                    }
                    break;
                case RequestCodes.TAKE_VIDEO:
                    if (!ListenerUtil.mutListener.listen(6609)) {
                        if (resultCode == Activity.RESULT_OK) {
                            if (!ListenerUtil.mutListener.listen(6608)) {
                                checkRecordedVideoDurationBeforeUploadAndTrack();
                            }
                        }
                    }
                    break;
                case RequestCodes.MEDIA_SETTINGS:
                    if (!ListenerUtil.mutListener.listen(6612)) {
                        if (resultCode == MediaSettingsActivity.RESULT_MEDIA_DELETED) {
                            if (!ListenerUtil.mutListener.listen(6610)) {
                                reloadMediaGrid();
                            }
                            if (!ListenerUtil.mutListener.listen(6611)) {
                                refreshTabs();
                            }
                        }
                    }
                    break;
                case RequestCodes.STOCK_MEDIA_PICKER_MULTI_SELECT:
                    if (!ListenerUtil.mutListener.listen(6614)) {
                        if (resultCode == RESULT_OK) {
                            if (!ListenerUtil.mutListener.listen(6613)) {
                                reloadMediaGrid();
                            }
                        }
                    }
                    break;
                case RequestCodes.GIF_PICKER_SINGLE_SELECT:
                case RequestCodes.GIF_PICKER_MULTI_SELECT:
                    if (!ListenerUtil.mutListener.listen(6619)) {
                        if ((ListenerUtil.mutListener.listen(6615) ? (resultCode == RESULT_OK || data.hasExtra(MediaPickerConstants.EXTRA_SAVED_MEDIA_MODEL_LOCAL_IDS)) : (resultCode == RESULT_OK && data.hasExtra(MediaPickerConstants.EXTRA_SAVED_MEDIA_MODEL_LOCAL_IDS)))) {
                            int[] mediaLocalIds = data.getIntArrayExtra(MediaPickerConstants.EXTRA_SAVED_MEDIA_MODEL_LOCAL_IDS);
                            ArrayList<MediaModel> mediaModels = new ArrayList<>();
                            if (!ListenerUtil.mutListener.listen(6617)) {
                                {
                                    long _loopCounter147 = 0;
                                    for (int localId : mediaLocalIds) {
                                        ListenerUtil.loopListener.listen("_loopCounter147", ++_loopCounter147);
                                        if (!ListenerUtil.mutListener.listen(6616)) {
                                            mediaModels.add(mMediaStore.getMediaWithLocalId(localId));
                                        }
                                    }
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(6618)) {
                                addMediaToUploadService(mediaModels);
                            }
                        }
                    }
                    break;
            }
        }
    }

    private List<Uri> convertStringArrayIntoUrisList(String[] stringArray) {
        List<Uri> uris = new ArrayList<>(stringArray.length);
        if (!ListenerUtil.mutListener.listen(6622)) {
            {
                long _loopCounter148 = 0;
                for (String stringUri : stringArray) {
                    ListenerUtil.loopListener.listen("_loopCounter148", ++_loopCounter148);
                    if (!ListenerUtil.mutListener.listen(6621)) {
                        uris.add(Uri.parse(stringUri));
                    }
                }
            }
        }
        return uris;
    }

    /**
     * Analytics about new media
     *
     * @param isNewMedia Whether is a fresh (just taken) photo/video or not
     * @param isVideo Whether is a video or not
     * @param uri The URI of the media on the device, or null
     */
    private void trackAddMediaFromDeviceEvents(boolean isNewMedia, boolean isVideo, Uri uri) {
        if (!ListenerUtil.mutListener.listen(6624)) {
            if (uri == null) {
                if (!ListenerUtil.mutListener.listen(6623)) {
                    AppLog.e(AppLog.T.MEDIA, "Cannot track new media event if mediaURI is null!!");
                }
                return;
            }
        }
        Map<String, Object> properties = AnalyticsUtils.getMediaProperties(this, isVideo, uri, null);
        if (!ListenerUtil.mutListener.listen(6625)) {
            properties.put("via", isNewMedia ? "device_camera" : "device_library");
        }
        if (!ListenerUtil.mutListener.listen(6628)) {
            if (isVideo) {
                if (!ListenerUtil.mutListener.listen(6627)) {
                    AnalyticsTracker.track(AnalyticsTracker.Stat.MEDIA_LIBRARY_ADDED_VIDEO, properties);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(6626)) {
                    AnalyticsTracker.track(AnalyticsTracker.Stat.MEDIA_LIBRARY_ADDED_PHOTO, properties);
                }
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] results) {
        if (!ListenerUtil.mutListener.listen(6629)) {
            super.onRequestPermissionsResult(requestCode, permissions, results);
        }
        boolean allGranted = WPPermissionUtils.setPermissionListAsked(this, requestCode, permissions, results, true);
        if (!ListenerUtil.mutListener.listen(6632)) {
            if ((ListenerUtil.mutListener.listen(6630) ? (allGranted || requestCode == WPPermissionUtils.MEDIA_BROWSER_PERMISSION_REQUEST_CODE) : (allGranted && requestCode == WPPermissionUtils.MEDIA_BROWSER_PERMISSION_REQUEST_CODE))) {
                if (!ListenerUtil.mutListener.listen(6631)) {
                    doAddMediaItemClicked(mLastAddMediaItemClicked);
                }
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!ListenerUtil.mutListener.listen(6633)) {
            mMenu = menu;
        }
        if (!ListenerUtil.mutListener.listen(6634)) {
            getMenuInflater().inflate(R.menu.media_browser, menu);
        }
        if (!ListenerUtil.mutListener.listen(6635)) {
            mSearchMenuItem = menu.findItem(R.id.menu_search);
        }
        if (!ListenerUtil.mutListener.listen(6636)) {
            mSearchMenuItem.setOnActionExpandListener(this);
        }
        if (!ListenerUtil.mutListener.listen(6637)) {
            mSearchView = (SearchView) mSearchMenuItem.getActionView();
        }
        if (!ListenerUtil.mutListener.listen(6638)) {
            mSearchView.setOnQueryTextListener(this);
        }
        if (!ListenerUtil.mutListener.listen(6639)) {
            mSearchView.setMaxWidth(Integer.MAX_VALUE);
        }
        if (!ListenerUtil.mutListener.listen(6640)) {
            menuNewMediaItem = menu.findItem(R.id.menu_new_media);
        }
        if (!ListenerUtil.mutListener.listen(6641)) {
            mMenuNewMediaQuickStartFocusPoint = menuNewMediaItem.getActionView().findViewById(R.id.menu_add_media_quick_start_focus_point);
        }
        if (!ListenerUtil.mutListener.listen(6642)) {
            menuNewMediaItem.getActionView().setOnClickListener(v -> {
                showAddMediaPopup();
                completeUploadMediaQuickStartTask();
                updateMenuNewMediaQuickStartFocusPoint(false);
            });
        }
        if (!ListenerUtil.mutListener.listen(6648)) {
            // open search bar if we were searching for something before
            if ((ListenerUtil.mutListener.listen(6644) ? ((ListenerUtil.mutListener.listen(6643) ? (!TextUtils.isEmpty(mQuery) || mMediaGridFragment != null) : (!TextUtils.isEmpty(mQuery) && mMediaGridFragment != null)) || mMediaGridFragment.isVisible()) : ((ListenerUtil.mutListener.listen(6643) ? (!TextUtils.isEmpty(mQuery) || mMediaGridFragment != null) : (!TextUtils.isEmpty(mQuery) && mMediaGridFragment != null)) && mMediaGridFragment.isVisible()))) {
                // temporary hold onto query
                String tempQuery = mQuery;
                if (!ListenerUtil.mutListener.listen(6645)) {
                    // this will reset mQuery
                    MenuItemCompat.expandActionView(mSearchMenuItem);
                }
                if (!ListenerUtil.mutListener.listen(6646)) {
                    onQueryTextSubmit(tempQuery);
                }
                if (!ListenerUtil.mutListener.listen(6647)) {
                    mSearchView.setQuery(mQuery, true);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(6652)) {
            // hide "add media" if the user doesn't have upload permission or this is a multiselect picker
            if ((ListenerUtil.mutListener.listen(6649) ? (mBrowserType.canMultiselect() && !WPMediaUtils.currentUserCanUploadMedia(mSite)) : (mBrowserType.canMultiselect() || !WPMediaUtils.currentUserCanUploadMedia(mSite)))) {
                if (!ListenerUtil.mutListener.listen(6650)) {
                    menuNewMediaItem.setVisible(false);
                }
                if (!ListenerUtil.mutListener.listen(6651)) {
                    mMediaGridFragment.showActionableEmptyViewButton(false);
                }
            }
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (!ListenerUtil.mutListener.listen(6662)) {
            switch(item.getItemId()) {
                case android.R.id.home:
                    if (!ListenerUtil.mutListener.listen(6653)) {
                        onBackPressed();
                    }
                    return true;
                case R.id.menu_new_media:
                    // Do Nothing (handled in action view click listener)
                    return true;
                case R.id.menu_search:
                    if (!ListenerUtil.mutListener.listen(6654)) {
                        mSearchMenuItem = item;
                    }
                    if (!ListenerUtil.mutListener.listen(6655)) {
                        mSearchMenuItem.setOnActionExpandListener(this);
                    }
                    if (!ListenerUtil.mutListener.listen(6656)) {
                        mSearchMenuItem.expandActionView();
                    }
                    if (!ListenerUtil.mutListener.listen(6657)) {
                        mSearchView = (SearchView) item.getActionView();
                    }
                    if (!ListenerUtil.mutListener.listen(6658)) {
                        mSearchView.setOnQueryTextListener(this);
                    }
                    if (!ListenerUtil.mutListener.listen(6661)) {
                        // load last saved query
                        if (!TextUtils.isEmpty(mQuery)) {
                            if (!ListenerUtil.mutListener.listen(6659)) {
                                onQueryTextSubmit(mQuery);
                            }
                            if (!ListenerUtil.mutListener.listen(6660)) {
                                mSearchView.setQuery(mQuery, true);
                            }
                        }
                    }
                    return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onMenuItemActionExpand(MenuItem item) {
        if (!ListenerUtil.mutListener.listen(6663)) {
            mMenu.findItem(R.id.menu_new_media).setVisible(false);
        }
        if (!ListenerUtil.mutListener.listen(6664)) {
            mMediaGridFragment.showActionableEmptyViewButton(false);
        }
        if (!ListenerUtil.mutListener.listen(6665)) {
            enableTabs(false);
        }
        if (!ListenerUtil.mutListener.listen(6666)) {
            showQuota(false);
        }
        if (!ListenerUtil.mutListener.listen(6668)) {
            // load last search query
            if (!TextUtils.isEmpty(mQuery)) {
                if (!ListenerUtil.mutListener.listen(6667)) {
                    onQueryTextChange(mQuery);
                }
            }
        }
        return true;
    }

    @Override
    public boolean onMenuItemActionCollapse(MenuItem item) {
        if (!ListenerUtil.mutListener.listen(6669)) {
            mMenu.findItem(R.id.menu_new_media).setVisible(true);
        }
        if (!ListenerUtil.mutListener.listen(6670)) {
            mMediaGridFragment.showActionableEmptyViewButton(true);
        }
        if (!ListenerUtil.mutListener.listen(6671)) {
            invalidateOptionsMenu();
        }
        if (!ListenerUtil.mutListener.listen(6672)) {
            enableTabs(true);
        }
        if (!ListenerUtil.mutListener.listen(6673)) {
            showQuota(true);
        }
        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        if (!ListenerUtil.mutListener.listen(6675)) {
            if (mMediaGridFragment != null) {
                if (!ListenerUtil.mutListener.listen(6674)) {
                    mMediaGridFragment.search(query);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(6676)) {
            mQuery = query;
        }
        if (!ListenerUtil.mutListener.listen(6677)) {
            mSearchView.clearFocus();
        }
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        if (!ListenerUtil.mutListener.listen(6679)) {
            if (mMediaGridFragment != null) {
                if (!ListenerUtil.mutListener.listen(6678)) {
                    mMediaGridFragment.search(newText);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(6680)) {
            mQuery = newText;
        }
        return true;
    }

    @Override
    public void onMediaItemSelected(int localMediaId, boolean isLongClick) {
        MediaModel media = mMediaStore.getMediaWithLocalId(localMediaId);
        if (!ListenerUtil.mutListener.listen(6683)) {
            if (media == null) {
                if (!ListenerUtil.mutListener.listen(6681)) {
                    AppLog.w(AppLog.T.MEDIA, "Media browser > unable to load localMediaId = " + localMediaId);
                }
                if (!ListenerUtil.mutListener.listen(6682)) {
                    ToastUtils.showToast(this, R.string.error_media_load);
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(6684)) {
            // do nothing for failed uploads
            if (MediaUploadState.fromString(media.getUploadState()) == MediaUploadState.FAILED) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(6697)) {
            // when long tapped (to mimic native photo picker)
            if ((ListenerUtil.mutListener.listen(6687) ? ((ListenerUtil.mutListener.listen(6685) ? (mBrowserType.isBrowser() || !isLongClick) : (mBrowserType.isBrowser() && !isLongClick)) && (ListenerUtil.mutListener.listen(6686) ? (mBrowserType.isPicker() || isLongClick) : (mBrowserType.isPicker() && isLongClick))) : ((ListenerUtil.mutListener.listen(6685) ? (mBrowserType.isBrowser() || !isLongClick) : (mBrowserType.isBrowser() && !isLongClick)) || (ListenerUtil.mutListener.listen(6686) ? (mBrowserType.isPicker() || isLongClick) : (mBrowserType.isPicker() && isLongClick))))) {
                if (!ListenerUtil.mutListener.listen(6696)) {
                    showMediaSettings(media);
                }
            } else if ((ListenerUtil.mutListener.listen(6691) ? (((ListenerUtil.mutListener.listen(6690) ? ((ListenerUtil.mutListener.listen(6689) ? ((ListenerUtil.mutListener.listen(6688) ? (mBrowserType.isSingleImagePicker() && mBrowserType.isSingleMediaPicker()) : (mBrowserType.isSingleImagePicker() || mBrowserType.isSingleMediaPicker())) && mBrowserType.isSingleFilePicker()) : ((ListenerUtil.mutListener.listen(6688) ? (mBrowserType.isSingleImagePicker() && mBrowserType.isSingleMediaPicker()) : (mBrowserType.isSingleImagePicker() || mBrowserType.isSingleMediaPicker())) || mBrowserType.isSingleFilePicker())) && mBrowserType.isSingleAudioFilePicker()) : ((ListenerUtil.mutListener.listen(6689) ? ((ListenerUtil.mutListener.listen(6688) ? (mBrowserType.isSingleImagePicker() && mBrowserType.isSingleMediaPicker()) : (mBrowserType.isSingleImagePicker() || mBrowserType.isSingleMediaPicker())) && mBrowserType.isSingleFilePicker()) : ((ListenerUtil.mutListener.listen(6688) ? (mBrowserType.isSingleImagePicker() && mBrowserType.isSingleMediaPicker()) : (mBrowserType.isSingleImagePicker() || mBrowserType.isSingleMediaPicker())) || mBrowserType.isSingleFilePicker())) || mBrowserType.isSingleAudioFilePicker()))) || !isLongClick) : (((ListenerUtil.mutListener.listen(6690) ? ((ListenerUtil.mutListener.listen(6689) ? ((ListenerUtil.mutListener.listen(6688) ? (mBrowserType.isSingleImagePicker() && mBrowserType.isSingleMediaPicker()) : (mBrowserType.isSingleImagePicker() || mBrowserType.isSingleMediaPicker())) && mBrowserType.isSingleFilePicker()) : ((ListenerUtil.mutListener.listen(6688) ? (mBrowserType.isSingleImagePicker() && mBrowserType.isSingleMediaPicker()) : (mBrowserType.isSingleImagePicker() || mBrowserType.isSingleMediaPicker())) || mBrowserType.isSingleFilePicker())) && mBrowserType.isSingleAudioFilePicker()) : ((ListenerUtil.mutListener.listen(6689) ? ((ListenerUtil.mutListener.listen(6688) ? (mBrowserType.isSingleImagePicker() && mBrowserType.isSingleMediaPicker()) : (mBrowserType.isSingleImagePicker() || mBrowserType.isSingleMediaPicker())) && mBrowserType.isSingleFilePicker()) : ((ListenerUtil.mutListener.listen(6688) ? (mBrowserType.isSingleImagePicker() && mBrowserType.isSingleMediaPicker()) : (mBrowserType.isSingleImagePicker() || mBrowserType.isSingleMediaPicker())) || mBrowserType.isSingleFilePicker())) || mBrowserType.isSingleAudioFilePicker()))) && !isLongClick))) {
                // if we're picking a single media item, we're done
                Intent intent = new Intent();
                ArrayList<Long> remoteMediaIds = new ArrayList<>();
                if (!ListenerUtil.mutListener.listen(6692)) {
                    remoteMediaIds.add(media.getMediaId());
                }
                if (!ListenerUtil.mutListener.listen(6693)) {
                    intent.putExtra(RESULT_IDS, ListUtils.toLongArray(remoteMediaIds));
                }
                if (!ListenerUtil.mutListener.listen(6694)) {
                    setResult(RESULT_OK, intent);
                }
                if (!ListenerUtil.mutListener.listen(6695)) {
                    finish();
                }
            }
        }
    }

    @Override
    public void onMediaRequestRetry(int localMediaId) {
        MediaModel media = mMediaStore.getMediaWithLocalId(localMediaId);
        if (!ListenerUtil.mutListener.listen(6700)) {
            if (media != null) {
                if (!ListenerUtil.mutListener.listen(6699)) {
                    addMediaToUploadService(media);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(6698)) {
                    ToastUtils.showToast(this, R.string.error_media_not_found);
                }
            }
        }
    }

    @Override
    public void onMediaRequestDelete(int localMediaId) {
        ArrayList<Integer> ids = new ArrayList<>();
        if (!ListenerUtil.mutListener.listen(6701)) {
            ids.add(localMediaId);
        }
        if (!ListenerUtil.mutListener.listen(6702)) {
            deleteMedia(ids);
        }
    }

    private void showMediaSettings(@NonNull MediaModel media) {
        List<MediaModel> mediaList = mMediaGridFragment.getFilteredMedia();
        ArrayList<String> idList = new ArrayList<>();
        if (!ListenerUtil.mutListener.listen(6704)) {
            {
                long _loopCounter149 = 0;
                for (MediaModel thisMedia : mediaList) {
                    ListenerUtil.loopListener.listen("_loopCounter149", ++_loopCounter149);
                    if (!ListenerUtil.mutListener.listen(6703)) {
                        idList.add(Integer.toString(thisMedia.getId()));
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(6705)) {
            MediaSettingsActivity.showForResult(this, mSite, media, idList);
        }
    }

    @Override
    public void onMediaCapturePathReady(String mediaCapturePath) {
        if (!ListenerUtil.mutListener.listen(6706)) {
            mMediaCapturePath = mediaCapturePath;
        }
    }

    private void showMediaToastError(@StringRes int message, @Nullable String messageDetail) {
        if (!ListenerUtil.mutListener.listen(6707)) {
            if (isFinishing()) {
                return;
            }
        }
        String errorMessage = getString(message);
        if (!ListenerUtil.mutListener.listen(6709)) {
            if (!TextUtils.isEmpty(messageDetail)) {
                if (!ListenerUtil.mutListener.listen(6708)) {
                    errorMessage += ". " + messageDetail;
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(6710)) {
            ToastUtils.showToast(this, errorMessage, LONG);
        }
    }

    @SuppressWarnings("unused")
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMediaChanged(OnMediaChanged event) {
        if (!ListenerUtil.mutListener.listen(6711)) {
            AppLog.d(AppLog.T.MEDIA, "MediaBrowser onMediaChanged > " + event.cause);
        }
        if (!ListenerUtil.mutListener.listen(6714)) {
            if (event.isError()) {
                if (!ListenerUtil.mutListener.listen(6712)) {
                    AppLog.w(AppLog.T.MEDIA, "Received onMediaChanged error: " + event.error.type + " - " + event.error.message);
                }
                if (!ListenerUtil.mutListener.listen(6713)) {
                    showMediaToastError(R.string.media_generic_error, event.error.message);
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(6718)) {
            switch(event.cause) {
                case DELETE_MEDIA:
                    if (!ListenerUtil.mutListener.listen(6717)) {
                        if (event.mediaList != null) {
                            if (!ListenerUtil.mutListener.listen(6716)) {
                                {
                                    long _loopCounter150 = 0;
                                    // If the media was deleted, remove it from multi select if it was selected
                                    for (MediaModel mediaModel : event.mediaList) {
                                        ListenerUtil.loopListener.listen("_loopCounter150", ++_loopCounter150);
                                        int localMediaId = mediaModel.getId();
                                        if (!ListenerUtil.mutListener.listen(6715)) {
                                            mMediaGridFragment.removeFromMultiSelect(localMediaId);
                                        }
                                    }
                                }
                            }
                        }
                    }
                    break;
            }
        }
        if (!ListenerUtil.mutListener.listen(6722)) {
            if ((ListenerUtil.mutListener.listen(6719) ? (event.mediaList != null || event.mediaList.size() == 1) : (event.mediaList != null && event.mediaList.size() == 1))) {
                if (!ListenerUtil.mutListener.listen(6721)) {
                    updateMediaGridItem(event.mediaList.get(0), true);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(6720)) {
                    reloadMediaGrid();
                }
            }
        }
    }

    @SuppressWarnings("unused")
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMediaUploaded(OnMediaUploaded event) {
        if (!ListenerUtil.mutListener.listen(6723)) {
            mDispatcher.dispatch(SiteActionBuilder.newFetchSiteAction(mSite));
        }
        if (!ListenerUtil.mutListener.listen(6726)) {
            if (event.media != null) {
                if (!ListenerUtil.mutListener.listen(6725)) {
                    updateMediaGridItem(event.media, event.isError());
                }
            } else {
                if (!ListenerUtil.mutListener.listen(6724)) {
                    reloadMediaGrid();
                }
            }
        }
    }

    @SuppressWarnings("unused")
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMediaListFetched(OnMediaListFetched event) {
        if (!ListenerUtil.mutListener.listen(6727)) {
            if (event.isError()) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(6728)) {
            refreshTabs();
        }
    }

    @Override
    public void onSupportActionModeStarted(@NonNull ActionMode mode) {
        if (!ListenerUtil.mutListener.listen(6729)) {
            super.onSupportActionModeStarted(mode);
        }
        if (!ListenerUtil.mutListener.listen(6730)) {
            enableTabs(false);
        }
        if (!ListenerUtil.mutListener.listen(6731)) {
            showQuota(false);
        }
    }

    @Override
    public void onSupportActionModeFinished(@NonNull ActionMode mode) {
        if (!ListenerUtil.mutListener.listen(6732)) {
            super.onSupportActionModeFinished(mode);
        }
        if (!ListenerUtil.mutListener.listen(6733)) {
            enableTabs(true);
        }
        if (!ListenerUtil.mutListener.listen(6734)) {
            showQuota(true);
        }
    }

    // TODO: in a future PR this and startMediaDeleteService() can be simplified since multiselect delete was dropped
    private void deleteMedia(final ArrayList<Integer> ids) {
        final ArrayList<MediaModel> mediaToDelete = new ArrayList<>();
        int processedItemCount = 0;
        if (!ListenerUtil.mutListener.listen(6745)) {
            {
                long _loopCounter151 = 0;
                for (int currentId : ids) {
                    ListenerUtil.loopListener.listen("_loopCounter151", ++_loopCounter151);
                    MediaModel mediaModel = mMediaStore.getMediaWithLocalId(currentId);
                    if (!ListenerUtil.mutListener.listen(6735)) {
                        if (mediaModel == null) {
                            continue;
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(6737)) {
                        // if uploading, first issue a cancel upload command
                        if (UploadService.isPendingOrInProgressMediaUpload(mediaModel)) {
                            CancelMediaPayload payload = new CancelMediaPayload(mSite, mediaModel, false);
                            if (!ListenerUtil.mutListener.listen(6736)) {
                                mDispatcher.dispatch(MediaActionBuilder.newCancelMediaUploadAction(payload));
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(6743)) {
                        if ((ListenerUtil.mutListener.listen(6738) ? (mediaModel.getUploadState() != null || MediaUtils.isLocalFile(mediaModel.getUploadState().toLowerCase(Locale.ROOT))) : (mediaModel.getUploadState() != null && MediaUtils.isLocalFile(mediaModel.getUploadState().toLowerCase(Locale.ROOT))))) {
                            if (!ListenerUtil.mutListener.listen(6742)) {
                                mDispatcher.dispatch(MediaActionBuilder.newRemoveMediaAction(mediaModel));
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(6739)) {
                                mediaToDelete.add(mediaModel);
                            }
                            if (!ListenerUtil.mutListener.listen(6740)) {
                                mediaModel.setUploadState(MediaUploadState.DELETING);
                            }
                            if (!ListenerUtil.mutListener.listen(6741)) {
                                mDispatcher.dispatch(MediaActionBuilder.newUpdateMediaAction(mediaModel));
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(6744)) {
                        processedItemCount++;
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(6752)) {
            if ((ListenerUtil.mutListener.listen(6750) ? (processedItemCount >= ids.size()) : (ListenerUtil.mutListener.listen(6749) ? (processedItemCount <= ids.size()) : (ListenerUtil.mutListener.listen(6748) ? (processedItemCount > ids.size()) : (ListenerUtil.mutListener.listen(6747) ? (processedItemCount < ids.size()) : (ListenerUtil.mutListener.listen(6746) ? (processedItemCount == ids.size()) : (processedItemCount != ids.size()))))))) {
                if (!ListenerUtil.mutListener.listen(6751)) {
                    ToastUtils.showToast(this, R.string.cannot_delete_multi_media_items, LONG);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(6754)) {
            // and then refresh the grid
            if (!mediaToDelete.isEmpty()) {
                if (!ListenerUtil.mutListener.listen(6753)) {
                    startMediaDeleteService(mediaToDelete);
                }
            }
        }
    }

    private void uploadList(List<Uri> uriList) {
        if (!ListenerUtil.mutListener.listen(6756)) {
            if ((ListenerUtil.mutListener.listen(6755) ? (uriList == null && uriList.size() == 0) : (uriList == null || uriList.size() == 0))) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(6759)) {
            {
                long _loopCounter152 = 0;
                for (Uri uri : uriList) {
                    ListenerUtil.loopListener.listen("_loopCounter152", ++_loopCounter152);
                    if (!ListenerUtil.mutListener.listen(6758)) {
                        if (uri != null) {
                            if (!ListenerUtil.mutListener.listen(6757)) {
                                WPMediaUtils.fetchMediaAndDoNext(this, uri, downloadedUri -> queueFileForUpload(getOptimizedPictureIfNecessary(downloadedUri), getContentResolver().getType(downloadedUri)));
                            }
                        }
                    }
                }
            }
        }
    }

    private final OnBackStackChangedListener mOnBackStackChangedListener = () -> ActivityUtils.hideKeyboard(MediaBrowserActivity.this);

    private void doBindDeleteService(Intent intent) {
        if (!ListenerUtil.mutListener.listen(6760)) {
            mDeleteServiceBound = bindService(intent, mDeleteConnection, Context.BIND_AUTO_CREATE | Context.BIND_ABOVE_CLIENT);
        }
    }

    private void doUnbindDeleteService() {
        if (!ListenerUtil.mutListener.listen(6763)) {
            if (mDeleteServiceBound) {
                if (!ListenerUtil.mutListener.listen(6761)) {
                    unbindService(mDeleteConnection);
                }
                if (!ListenerUtil.mutListener.listen(6762)) {
                    mDeleteServiceBound = false;
                }
            }
        }
    }

    private final ServiceConnection mDeleteConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName className, IBinder service) {
            if (!ListenerUtil.mutListener.listen(6764)) {
                mDeleteService = (MediaDeleteService.MediaDeleteBinder) service;
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName arg0) {
            if (!ListenerUtil.mutListener.listen(6765)) {
                mDeleteService = null;
            }
        }
    };

    private final BroadcastReceiver mReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (!ListenerUtil.mutListener.listen(6768)) {
                if (ConnectivityManager.CONNECTIVITY_ACTION.equals(intent.getAction())) {
                    if (!ListenerUtil.mutListener.listen(6767)) {
                        // Coming from zero connection. Continue what's pending for delete
                        if (mMediaStore.hasSiteMediaToDelete(mSite)) {
                            if (!ListenerUtil.mutListener.listen(6766)) {
                                startMediaDeleteService(null);
                            }
                        }
                    }
                }
            }
        }
    };

    public void showAddMediaPopup() {
        View anchor = findViewById(R.id.menu_new_media);
        PopupMenu popup = new PopupMenu(this, anchor);
        if (!ListenerUtil.mutListener.listen(6769)) {
            popup.getMenu().add(R.string.photo_picker_capture_photo).setOnMenuItemClickListener(item -> {
                doAddMediaItemClicked(AddMenuItem.ITEM_CAPTURE_PHOTO);
                return true;
            });
        }
        if (!ListenerUtil.mutListener.listen(6771)) {
            if (!mBrowserType.isSingleImagePicker()) {
                if (!ListenerUtil.mutListener.listen(6770)) {
                    popup.getMenu().add(R.string.photo_picker_capture_video).setOnMenuItemClickListener(item -> {
                        doAddMediaItemClicked(AddMenuItem.ITEM_CAPTURE_VIDEO);
                        return true;
                    });
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(6772)) {
            popup.getMenu().add(R.string.photo_picker_choose_file).setOnMenuItemClickListener(item -> {
                doAddMediaItemClicked(AddMenuItem.ITEM_CHOOSE_FILE);
                return true;
            });
        }
        if (!ListenerUtil.mutListener.listen(6775)) {
            if ((ListenerUtil.mutListener.listen(6773) ? (mBrowserType.isBrowser() || mSite.isUsingWpComRestApi()) : (mBrowserType.isBrowser() && mSite.isUsingWpComRestApi()))) {
                if (!ListenerUtil.mutListener.listen(6774)) {
                    popup.getMenu().add(R.string.photo_picker_stock_media).setOnMenuItemClickListener(item -> {
                        doAddMediaItemClicked(AddMenuItem.ITEM_CHOOSE_STOCK_MEDIA);
                        return true;
                    });
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(6777)) {
            if (mBrowserType.isBrowser()) {
                if (!ListenerUtil.mutListener.listen(6776)) {
                    popup.getMenu().add(R.string.photo_picker_gif).setOnMenuItemClickListener(item -> {
                        doAddMediaItemClicked(AddMenuItem.ITEM_CHOOSE_GIF);
                        return true;
                    });
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(6778)) {
            popup.show();
        }
    }

    private void completeUploadMediaQuickStartTask() {
        if (!ListenerUtil.mutListener.listen(6781)) {
            if ((ListenerUtil.mutListener.listen(6779) ? (mSelectedSiteRepository.getSelectedSite() != null || mQuickStartRepository.isPendingTask(QuickStartExistingSiteTask.UPLOAD_MEDIA)) : (mSelectedSiteRepository.getSelectedSite() != null && mQuickStartRepository.isPendingTask(QuickStartExistingSiteTask.UPLOAD_MEDIA)))) {
                if (!ListenerUtil.mutListener.listen(6780)) {
                    mQuickStartRepository.completeTask(QuickStartExistingSiteTask.UPLOAD_MEDIA);
                }
            }
        }
    }

    private void doAddMediaItemClicked(@NonNull AddMenuItem item) {
        if (!ListenerUtil.mutListener.listen(6782)) {
            mLastAddMediaItemClicked = item;
        }
        if (!ListenerUtil.mutListener.listen(6785)) {
            // stock photos item requires no permission, all other items do
            if (item != AddMenuItem.ITEM_CHOOSE_STOCK_MEDIA) {
                String[] permissions;
                if ((ListenerUtil.mutListener.listen(6783) ? (item == AddMenuItem.ITEM_CAPTURE_PHOTO && item == AddMenuItem.ITEM_CAPTURE_VIDEO) : (item == AddMenuItem.ITEM_CAPTURE_PHOTO || item == AddMenuItem.ITEM_CAPTURE_VIDEO))) {
                    permissions = new String[] { Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE };
                } else {
                    permissions = new String[] { Manifest.permission.WRITE_EXTERNAL_STORAGE };
                }
                if (!ListenerUtil.mutListener.listen(6784)) {
                    if (!PermissionUtils.checkAndRequestPermissions(this, WPPermissionUtils.MEDIA_BROWSER_PERMISSION_REQUEST_CODE, permissions)) {
                        return;
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(6791)) {
            switch(item) {
                case ITEM_CAPTURE_PHOTO:
                    if (!ListenerUtil.mutListener.listen(6786)) {
                        WPMediaUtils.launchCamera(this, BuildConfig.APPLICATION_ID, this);
                    }
                    break;
                case ITEM_CAPTURE_VIDEO:
                    if (!ListenerUtil.mutListener.listen(6787)) {
                        WPMediaUtils.launchVideoCamera(this);
                    }
                    break;
                case ITEM_CHOOSE_FILE:
                    if (!ListenerUtil.mutListener.listen(6788)) {
                        mMediaPickerLauncher.showFilePicker(this, true, mSite);
                    }
                    break;
                case ITEM_CHOOSE_STOCK_MEDIA:
                    if (!ListenerUtil.mutListener.listen(6789)) {
                        mMediaPickerLauncher.showStockMediaPickerForResult(this, mSite, RequestCodes.STOCK_MEDIA_PICKER_MULTI_SELECT, true);
                    }
                    break;
                case ITEM_CHOOSE_GIF:
                    if (!ListenerUtil.mutListener.listen(6790)) {
                        mMediaPickerLauncher.showGifPickerForResult(this, mSite, true);
                    }
                    break;
            }
        }
    }

    private Uri getOptimizedPictureIfNecessary(Uri originalUri) {
        String filePath = MediaUtils.getRealPathFromURI(this, originalUri);
        if (!ListenerUtil.mutListener.listen(6792)) {
            if (TextUtils.isEmpty(filePath)) {
                return originalUri;
            }
        }
        Uri optimizedMedia = WPMediaUtils.getOptimizedMedia(this, filePath, false);
        if (!ListenerUtil.mutListener.listen(6795)) {
            if (optimizedMedia != null) {
                return optimizedMedia;
            } else {
                if (!ListenerUtil.mutListener.listen(6794)) {
                    // Fix for the rotation issue https://github.com/wordpress-mobile/WordPress-Android/issues/5737
                    if (!mSite.isWPCom()) {
                        // If it's not wpcom we must rotate the picture locally
                        Uri rotatedMedia = WPMediaUtils.fixOrientationIssue(this, filePath, false);
                        if (!ListenerUtil.mutListener.listen(6793)) {
                            if (rotatedMedia != null) {
                                return rotatedMedia;
                            }
                        }
                    }
                }
            }
        }
        return originalUri;
    }

    private void addMediaToUploadService(@NonNull MediaModel media) {
        ArrayList<MediaModel> mediaList = new ArrayList<>();
        if (!ListenerUtil.mutListener.listen(6796)) {
            mediaList.add(media);
        }
        if (!ListenerUtil.mutListener.listen(6797)) {
            addMediaToUploadService(mediaList);
        }
    }

    private void addMediaToUploadService(@NonNull ArrayList<MediaModel> mediaModels) {
        if (!ListenerUtil.mutListener.listen(6800)) {
            // Start the upload service if it's not started and fill the media queue
            if (!NetworkUtils.isNetworkAvailable(this)) {
                if (!ListenerUtil.mutListener.listen(6798)) {
                    AppLog.v(AppLog.T.MEDIA, "Unable to start UploadService, internet connection required.");
                }
                if (!ListenerUtil.mutListener.listen(6799)) {
                    ToastUtils.showToast(this, R.string.no_network_message, ToastUtils.Duration.SHORT);
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(6801)) {
            UploadService.uploadMedia(this, mediaModels);
        }
        if (!ListenerUtil.mutListener.listen(6802)) {
            AppRatingDialog.INSTANCE.incrementInteractions(APP_REVIEWS_EVENT_INCREMENTED_BY_UPLOADING_MEDIA);
        }
    }

    private void queueFileForUpload(Uri uri, String mimeType) {
        MediaModel media = FluxCUtils.mediaModelFromLocalUri(this, uri, mimeType, mMediaStore, mSite.getId());
        if (!ListenerUtil.mutListener.listen(6804)) {
            if (media == null) {
                if (!ListenerUtil.mutListener.listen(6803)) {
                    ToastUtils.showToast(this, R.string.file_not_found, ToastUtils.Duration.SHORT);
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(6805)) {
            mDispatcher.dispatch(MediaActionBuilder.newUpdateMediaAction(media));
        }
        if (!ListenerUtil.mutListener.listen(6806)) {
            addMediaToUploadService(media);
        }
        if (!ListenerUtil.mutListener.listen(6807)) {
            updateMediaGridItem(media, false);
        }
    }

    private void handleSharedMedia() {
        Intent intent = getIntent();
        final List<Uri> multiStream;
        if (Intent.ACTION_SEND_MULTIPLE.equals(intent.getAction())) {
            multiStream = intent.getParcelableArrayListExtra((Intent.EXTRA_STREAM));
        } else if (Intent.ACTION_SEND.equals(intent.getAction())) {
            multiStream = new ArrayList<>();
            if (!ListenerUtil.mutListener.listen(6808)) {
                multiStream.add(intent.getParcelableExtra(Intent.EXTRA_STREAM));
            }
        } else {
            multiStream = null;
        }
        if (!ListenerUtil.mutListener.listen(6810)) {
            if (multiStream != null) {
                if (!ListenerUtil.mutListener.listen(6809)) {
                    uploadList(multiStream);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(6811)) {
            // clear the intent's action, so that in case the user rotates, we don't re-upload the same files
            getIntent().setAction(null);
        }
    }

    private void startMediaDeleteService(ArrayList<MediaModel> mediaToDelete) {
        if (!ListenerUtil.mutListener.listen(6813)) {
            if (!NetworkUtils.isNetworkAvailable(this)) {
                if (!ListenerUtil.mutListener.listen(6812)) {
                    AppLog.v(AppLog.T.MEDIA, "Unable to start MediaDeleteService, internet connection required.");
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(6823)) {
            if (mDeleteService != null) {
                if (!ListenerUtil.mutListener.listen(6822)) {
                    if ((ListenerUtil.mutListener.listen(6819) ? (mediaToDelete != null || !mediaToDelete.isEmpty()) : (mediaToDelete != null && !mediaToDelete.isEmpty()))) {
                        if (!ListenerUtil.mutListener.listen(6821)) {
                            {
                                long _loopCounter153 = 0;
                                for (MediaModel media : mediaToDelete) {
                                    ListenerUtil.loopListener.listen("_loopCounter153", ++_loopCounter153);
                                    if (!ListenerUtil.mutListener.listen(6820)) {
                                        mDeleteService.addMediaToDeleteQueue(media);
                                    }
                                }
                            }
                        }
                    }
                }
            } else {
                Intent intent = new Intent(this, MediaDeleteService.class);
                if (!ListenerUtil.mutListener.listen(6814)) {
                    intent.putExtra(MediaDeleteService.SITE_KEY, mSite);
                }
                if (!ListenerUtil.mutListener.listen(6817)) {
                    if (mediaToDelete != null) {
                        if (!ListenerUtil.mutListener.listen(6815)) {
                            intent.putExtra(MediaDeleteService.MEDIA_LIST_KEY, mediaToDelete);
                        }
                        if (!ListenerUtil.mutListener.listen(6816)) {
                            doBindDeleteService(intent);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(6818)) {
                    startService(intent);
                }
            }
        }
    }

    private void updateMediaGridItem(@NonNull MediaModel media, boolean forceUpdate) {
        if (!ListenerUtil.mutListener.listen(6827)) {
            if (mMediaGridFragment != null) {
                if (!ListenerUtil.mutListener.listen(6826)) {
                    if (mMediaStore.getMediaWithLocalId(media.getId()) != null) {
                        if (!ListenerUtil.mutListener.listen(6825)) {
                            mMediaGridFragment.updateMediaItem(media, forceUpdate);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(6824)) {
                            mMediaGridFragment.removeMediaItem(media);
                        }
                    }
                }
            }
        }
    }

    private void updateMediaGridForTheseMedia(List<MediaModel> mediaModelList) {
        if (!ListenerUtil.mutListener.listen(6830)) {
            if (mediaModelList != null) {
                if (!ListenerUtil.mutListener.listen(6829)) {
                    {
                        long _loopCounter154 = 0;
                        for (MediaModel media : mediaModelList) {
                            ListenerUtil.loopListener.listen("_loopCounter154", ++_loopCounter154);
                            if (!ListenerUtil.mutListener.listen(6828)) {
                                updateMediaGridItem(media, true);
                            }
                        }
                    }
                }
            }
        }
    }

    private void reloadMediaGrid() {
        if (!ListenerUtil.mutListener.listen(6833)) {
            if (mMediaGridFragment != null) {
                if (!ListenerUtil.mutListener.listen(6831)) {
                    mMediaGridFragment.reload();
                }
                if (!ListenerUtil.mutListener.listen(6832)) {
                    mDispatcher.dispatch(SiteActionBuilder.newFetchSiteAction(mSite));
                }
            }
        }
    }

    @SuppressWarnings("unused")
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onSiteChanged(OnSiteChanged event) {
        SiteModel site = mSiteStore.getSiteByLocalId(mSite.getId());
        if (!ListenerUtil.mutListener.listen(6836)) {
            if (site != null) {
                if (!ListenerUtil.mutListener.listen(6834)) {
                    mSite = site;
                }
                if (!ListenerUtil.mutListener.listen(6835)) {
                    showQuota(true);
                }
            }
        }
    }

    @SuppressWarnings("unused")
    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onEventMainThread(UploadService.UploadErrorEvent event) {
        if (!ListenerUtil.mutListener.listen(6837)) {
            EventBus.getDefault().removeStickyEvent(event);
        }
        if (!ListenerUtil.mutListener.listen(6842)) {
            if ((ListenerUtil.mutListener.listen(6838) ? (event.mediaModelList != null || !event.mediaModelList.isEmpty()) : (event.mediaModelList != null && !event.mediaModelList.isEmpty()))) {
                if (!ListenerUtil.mutListener.listen(6840)) {
                    mUploadUtilsWrapper.onMediaUploadedSnackbarHandler(this, findViewById(R.id.tab_layout), true, (ListenerUtil.mutListener.listen(6839) ? (!TextUtils.isEmpty(event.errorMessage) || event.errorMessage.contains(getString(R.string.error_media_quota_exceeded))) : (!TextUtils.isEmpty(event.errorMessage) && event.errorMessage.contains(getString(R.string.error_media_quota_exceeded)))) ? null : event.mediaModelList, mSite, event.errorMessage);
                }
                if (!ListenerUtil.mutListener.listen(6841)) {
                    updateMediaGridForTheseMedia(event.mediaModelList);
                }
            }
        }
    }

    @SuppressWarnings("unused")
    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onEventMainThread(UploadService.UploadMediaSuccessEvent event) {
        if (!ListenerUtil.mutListener.listen(6843)) {
            EventBus.getDefault().removeStickyEvent(event);
        }
        if (!ListenerUtil.mutListener.listen(6847)) {
            if ((ListenerUtil.mutListener.listen(6844) ? (event.mediaModelList != null || !event.mediaModelList.isEmpty()) : (event.mediaModelList != null && !event.mediaModelList.isEmpty()))) {
                if (!ListenerUtil.mutListener.listen(6845)) {
                    mUploadUtilsWrapper.onMediaUploadedSnackbarHandler(this, findViewById(R.id.tab_layout), false, event.mediaModelList, mSite, event.successMessage);
                }
                if (!ListenerUtil.mutListener.listen(6846)) {
                    updateMediaGridForTheseMedia(event.mediaModelList);
                }
            }
        }
    }

    public void updateMenuNewMediaQuickStartFocusPoint(boolean shouldShow) {
        if (!ListenerUtil.mutListener.listen(6850)) {
            if ((ListenerUtil.mutListener.listen(6848) ? (mMenuNewMediaQuickStartFocusPoint != null || menuNewMediaItem.isVisible()) : (mMenuNewMediaQuickStartFocusPoint != null && menuNewMediaItem.isVisible()))) {
                if (!ListenerUtil.mutListener.listen(6849)) {
                    mMenuNewMediaQuickStartFocusPoint.setVisibleOrGone(shouldShow);
                }
            }
        }
    }
}
