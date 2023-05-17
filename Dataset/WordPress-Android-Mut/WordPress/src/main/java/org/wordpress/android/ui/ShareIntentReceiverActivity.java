package org.wordpress.android.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.core.app.TaskStackBuilder;
import org.wordpress.android.R;
import org.wordpress.android.WordPress;
import org.wordpress.android.analytics.AnalyticsTracker;
import org.wordpress.android.fluxc.model.SiteModel;
import org.wordpress.android.fluxc.store.AccountStore;
import org.wordpress.android.fluxc.store.SiteStore;
import org.wordpress.android.ui.ShareIntentReceiverFragment.ShareAction;
import org.wordpress.android.ui.ShareIntentReceiverFragment.ShareIntentFragmentListener;
import org.wordpress.android.ui.main.WPMainActivity;
import org.wordpress.android.ui.media.MediaBrowserActivity;
import org.wordpress.android.ui.media.MediaBrowserType;
import org.wordpress.android.util.FluxCUtils;
import org.wordpress.android.util.MediaUtils;
import org.wordpress.android.util.PermissionUtils;
import org.wordpress.android.util.ToastUtils;
import org.wordpress.android.util.WPPermissionUtils;
import org.wordpress.android.util.analytics.AnalyticsUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * An activity to handle share intents, since there are multiple actions possible.
 * If the user is not logged in, redirects the user to the LoginFlow. When the user is logged in,
 * displays ShareIntentReceiverFragment. The fragment lets the user choose which blog to share to.
 * Moreover it lists what actions the user can perform and redirects the user to the activity,
 * along with the content passed in the intent.
 */
public class ShareIntentReceiverActivity extends LocaleAwareActivity implements ShareIntentFragmentListener {

    private static final String SHARE_LAST_USED_BLOG_ID_KEY = "wp-settings-share-last-used-text-blogid";

    private static final String KEY_SELECTED_SITE_LOCAL_ID = "KEY_SELECTED_SITE_LOCAL_ID";

    private static final String KEY_SHARE_ACTION_ID = "KEY_SHARE_ACTION_ID";

    private static final String KEY_LOCAL_MEDIA_URIS = "KEY_LOCAL_MEDIA_URIS";

    @Inject
    AccountStore mAccountStore;

    @Inject
    SiteStore mSiteStore;

    private int mClickedSiteLocalId;

    private String mShareActionName;

    private ArrayList<Uri> mLocalMediaUris = new ArrayList<>();

    @Override
    protected void onNewIntent(Intent intent) {
        if (!ListenerUtil.mutListener.listen(26486)) {
            super.onNewIntent(intent);
        }
        if (!ListenerUtil.mutListener.listen(26487)) {
            setIntent(intent);
        }
        if (!ListenerUtil.mutListener.listen(26488)) {
            refreshContent();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(26489)) {
            super.onCreate(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(26490)) {
            ((WordPress) getApplication()).component().inject(this);
        }
        if (!ListenerUtil.mutListener.listen(26491)) {
            setContentView(R.layout.share_intent_receiver_activity);
        }
        if (!ListenerUtil.mutListener.listen(26494)) {
            if (savedInstanceState == null) {
                if (!ListenerUtil.mutListener.listen(26493)) {
                    refreshContent();
                }
            } else {
                if (!ListenerUtil.mutListener.listen(26492)) {
                    loadState(savedInstanceState);
                }
            }
        }
    }

    private void refreshContent() {
        if (!ListenerUtil.mutListener.listen(26503)) {
            if (FluxCUtils.isSignedInWPComOrHasWPOrgSite(mAccountStore, mSiteStore)) {
                List<SiteModel> visibleSites = mSiteStore.getVisibleSites();
                if (!ListenerUtil.mutListener.listen(26496)) {
                    downloadExternalMedia();
                }
                if (!ListenerUtil.mutListener.listen(26502)) {
                    if (visibleSites.size() == 0) {
                        if (!ListenerUtil.mutListener.listen(26500)) {
                            ToastUtils.showToast(this, R.string.cant_share_no_visible_blog, ToastUtils.Duration.LONG);
                        }
                        if (!ListenerUtil.mutListener.listen(26501)) {
                            finish();
                        }
                    } else if ((ListenerUtil.mutListener.listen(26497) ? (visibleSites.size() == 1 || isSharingText()) : (visibleSites.size() == 1 && isSharingText()))) {
                        if (!ListenerUtil.mutListener.listen(26499)) {
                            // if text/plain and only one blog, then don't show the fragment, share it directly to a new post
                            share(ShareAction.SHARE_TO_POST, visibleSites.get(0).getId());
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(26498)) {
                            // display a fragment with list of sites and list of actions the user can perform
                            initShareFragment();
                        }
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(26495)) {
                    // start the login flow and wait onActivityResult
                    ActivityLauncher.loginForShareIntent(this);
                }
            }
        }
    }

    private void downloadExternalMedia() {
        if (!ListenerUtil.mutListener.listen(26507)) {
            if (Intent.ACTION_SEND_MULTIPLE.equals(getIntent().getAction())) {
                ArrayList<Uri> externalUris = getIntent().getParcelableArrayListExtra((Intent.EXTRA_STREAM));
                if (!ListenerUtil.mutListener.listen(26506)) {
                    {
                        long _loopCounter397 = 0;
                        for (Uri uri : externalUris) {
                            ListenerUtil.loopListener.listen("_loopCounter397", ++_loopCounter397);
                            if (!ListenerUtil.mutListener.listen(26505)) {
                                mLocalMediaUris.add(MediaUtils.downloadExternalMedia(this, uri));
                            }
                        }
                    }
                }
            } else if (Intent.ACTION_SEND.equals(getIntent().getAction())) {
                Uri externalUri = getIntent().getParcelableExtra(Intent.EXTRA_STREAM);
                if (!ListenerUtil.mutListener.listen(26504)) {
                    mLocalMediaUris.add(MediaUtils.downloadExternalMedia(this, externalUri));
                }
            }
        }
    }

    private void initShareFragment() {
        ShareIntentReceiverFragment shareIntentReceiverFragment = ShareIntentReceiverFragment.newInstance(!isSharingText(), loadLastUsedBlogLocalId());
        if (!ListenerUtil.mutListener.listen(26508)) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, shareIntentReceiverFragment, ShareIntentReceiverFragment.TAG).commit();
        }
    }

    private void loadState(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(26509)) {
            mClickedSiteLocalId = savedInstanceState.getInt(KEY_SELECTED_SITE_LOCAL_ID);
        }
        if (!ListenerUtil.mutListener.listen(26510)) {
            mShareActionName = savedInstanceState.getString(KEY_SHARE_ACTION_ID);
        }
        if (!ListenerUtil.mutListener.listen(26511)) {
            mLocalMediaUris = savedInstanceState.getParcelableArrayList(KEY_LOCAL_MEDIA_URIS);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        if (!ListenerUtil.mutListener.listen(26512)) {
            super.onSaveInstanceState(outState);
        }
        if (!ListenerUtil.mutListener.listen(26513)) {
            outState.putInt(KEY_SELECTED_SITE_LOCAL_ID, mClickedSiteLocalId);
        }
        if (!ListenerUtil.mutListener.listen(26514)) {
            outState.putString(KEY_SHARE_ACTION_ID, mShareActionName);
        }
        if (!ListenerUtil.mutListener.listen(26515)) {
            outState.putParcelableArrayList(KEY_LOCAL_MEDIA_URIS, mLocalMediaUris);
        }
    }

    private int loadLastUsedBlogLocalId() {
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(this);
        return settings.getInt(SHARE_LAST_USED_BLOG_ID_KEY, -1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (!ListenerUtil.mutListener.listen(26516)) {
            super.onActivityResult(requestCode, resultCode, data);
        }
        if (!ListenerUtil.mutListener.listen(26520)) {
            if (requestCode == RequestCodes.DO_LOGIN) {
                if (!ListenerUtil.mutListener.listen(26519)) {
                    if (resultCode == RESULT_OK) {
                        if (!ListenerUtil.mutListener.listen(26518)) {
                            // login successful
                            refreshContent();
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(26517)) {
                            finish();
                        }
                    }
                }
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (!ListenerUtil.mutListener.listen(26521)) {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
        boolean allGranted = WPPermissionUtils.setPermissionListAsked(this, requestCode, permissions, grantResults, true);
        if (!ListenerUtil.mutListener.listen(26525)) {
            if ((ListenerUtil.mutListener.listen(26522) ? (allGranted || requestCode == WPPermissionUtils.SHARE_MEDIA_PERMISSION_REQUEST_CODE) : (allGranted && requestCode == WPPermissionUtils.SHARE_MEDIA_PERMISSION_REQUEST_CODE))) {
                if (!ListenerUtil.mutListener.listen(26524)) {
                    // permissions granted
                    share(ShareAction.valueOf(mShareActionName), mClickedSiteLocalId);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(26523)) {
                    Toast.makeText(this, R.string.share_media_permission_required, Toast.LENGTH_LONG).show();
                }
            }
        }
    }

    @Override
    public void share(ShareAction shareAction, int selectedSiteLocalId) {
        if (!ListenerUtil.mutListener.listen(26530)) {
            if (checkAndRequestPermissions()) {
                if (!ListenerUtil.mutListener.listen(26528)) {
                    bumpAnalytics(shareAction, selectedSiteLocalId);
                }
                Intent intent = new Intent(this, shareAction.targetClass);
                if (!ListenerUtil.mutListener.listen(26529)) {
                    startActivityAndFinish(intent, selectedSiteLocalId);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(26526)) {
                    mShareActionName = shareAction.name();
                }
                if (!ListenerUtil.mutListener.listen(26527)) {
                    mClickedSiteLocalId = selectedSiteLocalId;
                }
            }
        }
    }

    private boolean isSharingText() {
        return "text/plain".equals(getIntent().getType());
    }

    private boolean checkAndRequestPermissions() {
        if (!ListenerUtil.mutListener.listen(26532)) {
            if (!isSharingText()) {
                if (!ListenerUtil.mutListener.listen(26531)) {
                    // If we're sharing media, we must check we have Storage permission (needed for media upload).
                    if (!PermissionUtils.checkAndRequestStoragePermission(this, WPPermissionUtils.SHARE_MEDIA_PERMISSION_REQUEST_CODE)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private void startActivityAndFinish(@NonNull Intent intent, int mSelectedSiteLocalId) {
        String action = getIntent().getAction();
        if (!ListenerUtil.mutListener.listen(26533)) {
            intent.setAction(action);
        }
        if (!ListenerUtil.mutListener.listen(26534)) {
            intent.setType(getIntent().getType());
        }
        if (!ListenerUtil.mutListener.listen(26535)) {
            intent.putExtra(WordPress.SITE, mSiteStore.getSiteByLocalId(mSelectedSiteLocalId));
        }
        if (!ListenerUtil.mutListener.listen(26536)) {
            intent.putExtra(MediaBrowserActivity.ARG_BROWSER_TYPE, MediaBrowserType.BROWSER);
        }
        if (!ListenerUtil.mutListener.listen(26537)) {
            intent.putExtra(Intent.EXTRA_TEXT, getIntent().getStringExtra(Intent.EXTRA_TEXT));
        }
        if (!ListenerUtil.mutListener.listen(26538)) {
            intent.putExtra(Intent.EXTRA_SUBJECT, getIntent().getStringExtra(Intent.EXTRA_SUBJECT));
        }
        if (!ListenerUtil.mutListener.listen(26541)) {
            if (Intent.ACTION_SEND_MULTIPLE.equals(action)) {
                if (!ListenerUtil.mutListener.listen(26540)) {
                    intent.putExtra(Intent.EXTRA_STREAM, mLocalMediaUris);
                }
            } else if (Intent.ACTION_SEND.equals(action)) {
                if (!ListenerUtil.mutListener.listen(26539)) {
                    intent.putExtra(Intent.EXTRA_STREAM, mLocalMediaUris.get(0));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(26542)) {
            // save preferences
            PreferenceManager.getDefaultSharedPreferences(this).edit().putInt(SHARE_LAST_USED_BLOG_ID_KEY, mSelectedSiteLocalId).apply();
        }
        if (!ListenerUtil.mutListener.listen(26543)) {
            startActivityWithSyntheticBackstack(intent);
        }
        if (!ListenerUtil.mutListener.listen(26544)) {
            finish();
        }
    }

    private void startActivityWithSyntheticBackstack(@NonNull Intent intent) {
        Intent parentIntent = new Intent(this, WPMainActivity.class);
        if (!ListenerUtil.mutListener.listen(26545)) {
            parentIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        if (!ListenerUtil.mutListener.listen(26546)) {
            parentIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        }
        if (!ListenerUtil.mutListener.listen(26547)) {
            parentIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        }
        if (!ListenerUtil.mutListener.listen(26548)) {
            TaskStackBuilder.create(this).addNextIntent(parentIntent).addNextIntent(intent).startActivities();
        }
    }

    private void bumpAnalytics(ShareAction shareAction, int selectedSiteLocalId) {
        SiteModel selectedSite = mSiteStore.getSiteByLocalId(selectedSiteLocalId);
        int numberOfMediaShared = countMedia();
        Map<String, Object> analyticsProperties = new HashMap<>();
        if (!ListenerUtil.mutListener.listen(26549)) {
            analyticsProperties.put("number_of_media_shared", numberOfMediaShared);
        }
        if (!ListenerUtil.mutListener.listen(26550)) {
            analyticsProperties.put("share_to", shareAction.analyticsName);
        }
        if (!ListenerUtil.mutListener.listen(26551)) {
            AnalyticsUtils.trackWithSiteDetails(AnalyticsTracker.Stat.SHARE_TO_WP_SUCCEEDED, selectedSite, analyticsProperties);
        }
        if (!ListenerUtil.mutListener.listen(26553)) {
            if (doesContainMediaAndWasSharedToMediaLibrary(shareAction, numberOfMediaShared)) {
                if (!ListenerUtil.mutListener.listen(26552)) {
                    trackMediaAddedToMediaLibrary(selectedSite);
                }
            }
        }
    }

    private void trackMediaAddedToMediaLibrary(SiteModel selectedSite) {
        if (!ListenerUtil.mutListener.listen(26557)) {
            {
                long _loopCounter398 = 0;
                for (Uri uri : mLocalMediaUris) {
                    ListenerUtil.loopListener.listen("_loopCounter398", ++_loopCounter398);
                    if (!ListenerUtil.mutListener.listen(26556)) {
                        if (uri != null) {
                            String mimeType = getContentResolver().getType(uri);
                            boolean isVideo = (ListenerUtil.mutListener.listen(26554) ? (mimeType != null || mimeType.startsWith("video")) : (mimeType != null && mimeType.startsWith("video")));
                            Map<String, Object> properties = AnalyticsUtils.getMediaProperties(this, isVideo, uri, null);
                            AnalyticsTracker.Stat mediaTypeTrack = isVideo ? AnalyticsTracker.Stat.MEDIA_LIBRARY_ADDED_VIDEO : AnalyticsTracker.Stat.MEDIA_LIBRARY_ADDED_PHOTO;
                            if (!ListenerUtil.mutListener.listen(26555)) {
                                AnalyticsUtils.trackWithSiteDetails(mediaTypeTrack, selectedSite, properties);
                            }
                        }
                    }
                }
            }
        }
    }

    private boolean doesContainMediaAndWasSharedToMediaLibrary(ShareAction shareAction, int numberOfMediaShared) {
        return (ListenerUtil.mutListener.listen(26564) ? ((ListenerUtil.mutListener.listen(26558) ? (shareAction != null || shareAction.analyticsName.equals(ShareAction.SHARE_TO_MEDIA_LIBRARY.analyticsName)) : (shareAction != null && shareAction.analyticsName.equals(ShareAction.SHARE_TO_MEDIA_LIBRARY.analyticsName))) || (ListenerUtil.mutListener.listen(26563) ? (numberOfMediaShared >= 0) : (ListenerUtil.mutListener.listen(26562) ? (numberOfMediaShared <= 0) : (ListenerUtil.mutListener.listen(26561) ? (numberOfMediaShared < 0) : (ListenerUtil.mutListener.listen(26560) ? (numberOfMediaShared != 0) : (ListenerUtil.mutListener.listen(26559) ? (numberOfMediaShared == 0) : (numberOfMediaShared > 0))))))) : ((ListenerUtil.mutListener.listen(26558) ? (shareAction != null || shareAction.analyticsName.equals(ShareAction.SHARE_TO_MEDIA_LIBRARY.analyticsName)) : (shareAction != null && shareAction.analyticsName.equals(ShareAction.SHARE_TO_MEDIA_LIBRARY.analyticsName))) && (ListenerUtil.mutListener.listen(26563) ? (numberOfMediaShared >= 0) : (ListenerUtil.mutListener.listen(26562) ? (numberOfMediaShared <= 0) : (ListenerUtil.mutListener.listen(26561) ? (numberOfMediaShared < 0) : (ListenerUtil.mutListener.listen(26560) ? (numberOfMediaShared != 0) : (ListenerUtil.mutListener.listen(26559) ? (numberOfMediaShared == 0) : (numberOfMediaShared > 0))))))));
    }

    private int countMedia() {
        int mediaShared = 0;
        if (!ListenerUtil.mutListener.listen(26568)) {
            if (!isSharingText()) {
                String action = getIntent().getAction();
                if (!ListenerUtil.mutListener.listen(26567)) {
                    if (Intent.ACTION_SEND_MULTIPLE.equals(action)) {
                        if (!ListenerUtil.mutListener.listen(26566)) {
                            // Multiple pictures share to WP
                            mediaShared = mLocalMediaUris.size();
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(26565)) {
                            mediaShared = 1;
                        }
                    }
                }
            }
        }
        return mediaShared;
    }
}
