package org.wordpress.android.ui;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.core.app.TaskStackBuilder;
import androidx.fragment.app.Fragment;
import com.wordpress.stories.compose.frame.FrameSaveNotifier;
import com.wordpress.stories.compose.frame.StorySaveEvents.StorySaveResult;
import org.wordpress.android.R;
import org.wordpress.android.WordPress;
import org.wordpress.android.analytics.AnalyticsTracker;
import org.wordpress.android.analytics.AnalyticsTracker.Stat;
import org.wordpress.android.datasets.ReaderPostTable;
import org.wordpress.android.fluxc.model.LocalOrRemoteId.LocalId;
import org.wordpress.android.fluxc.model.PostImmutableModel;
import org.wordpress.android.fluxc.model.PostModel;
import org.wordpress.android.fluxc.model.SiteModel;
import org.wordpress.android.fluxc.model.bloggingprompts.BloggingPromptModel;
import org.wordpress.android.fluxc.model.page.PageModel;
import org.wordpress.android.fluxc.network.utils.StatsGranularity;
import org.wordpress.android.imageeditor.EditImageActivity;
import org.wordpress.android.imageeditor.preview.PreviewImageFragment.Companion.EditImageData;
import org.wordpress.android.login.LoginMode;
import org.wordpress.android.models.ReaderPost;
import org.wordpress.android.networking.SSLCertsViewActivity;
import org.wordpress.android.push.NotificationType;
import org.wordpress.android.ui.accounts.HelpActivity;
import org.wordpress.android.ui.accounts.HelpActivity.Origin;
import org.wordpress.android.ui.accounts.LoginActivity;
import org.wordpress.android.ui.accounts.LoginEpilogueActivity;
import org.wordpress.android.ui.accounts.PostSignupInterstitialActivity;
import org.wordpress.android.ui.accounts.SignupEpilogueActivity;
import org.wordpress.android.ui.activitylog.detail.ActivityLogDetailActivity;
import org.wordpress.android.ui.activitylog.list.ActivityLogListActivity;
import org.wordpress.android.ui.comments.unified.UnifiedCommentsActivity;
import org.wordpress.android.ui.comments.unified.UnifiedCommentsDetailsActivity;
import org.wordpress.android.ui.debug.cookies.DebugCookiesActivity;
import org.wordpress.android.ui.domains.DomainRegistrationActivity;
import org.wordpress.android.ui.domains.DomainRegistrationActivity.DomainRegistrationPurpose;
import org.wordpress.android.ui.domains.DomainsDashboardActivity;
import org.wordpress.android.ui.engagement.EngagedPeopleListActivity;
import org.wordpress.android.ui.engagement.EngagementNavigationSource;
import org.wordpress.android.ui.engagement.HeaderData;
import org.wordpress.android.ui.engagement.ListScenario;
import org.wordpress.android.ui.engagement.ListScenarioType;
import org.wordpress.android.ui.history.HistoryDetailActivity;
import org.wordpress.android.ui.history.HistoryDetailContainerFragment;
import org.wordpress.android.ui.history.HistoryListItem.Revision;
import org.wordpress.android.ui.jetpack.backup.download.BackupDownloadActivity;
import org.wordpress.android.ui.jetpack.restore.RestoreActivity;
import org.wordpress.android.ui.jetpack.scan.ScanActivity;
import org.wordpress.android.ui.jetpack.scan.details.ThreatDetailsActivity;
import org.wordpress.android.ui.jetpack.scan.history.ScanHistoryActivity;
import org.wordpress.android.ui.main.MeActivity;
import org.wordpress.android.ui.main.SitePickerActivity;
import org.wordpress.android.ui.main.SitePickerAdapter.SitePickerMode;
import org.wordpress.android.ui.main.WPMainActivity;
import org.wordpress.android.ui.media.MediaBrowserActivity;
import org.wordpress.android.ui.media.MediaBrowserType;
import org.wordpress.android.ui.pages.PageParentActivity;
import org.wordpress.android.ui.pages.PagesActivity;
import org.wordpress.android.ui.people.PeopleManagementActivity;
import org.wordpress.android.ui.photopicker.MediaPickerConstants;
import org.wordpress.android.ui.photopicker.PhotoPickerActivity;
import org.wordpress.android.ui.plans.PlansActivity;
import org.wordpress.android.ui.plugins.PluginBrowserActivity;
import org.wordpress.android.ui.plugins.PluginDetailActivity;
import org.wordpress.android.ui.plugins.PluginUtils;
import org.wordpress.android.ui.posts.EditPostActivity;
import org.wordpress.android.ui.posts.JetpackSecuritySettingsActivity;
import org.wordpress.android.ui.posts.PostListType;
import org.wordpress.android.ui.posts.PostUtils;
import org.wordpress.android.ui.posts.PostUtils.EntryPoint;
import org.wordpress.android.ui.posts.PostsListActivity;
import org.wordpress.android.ui.posts.RemotePreviewLogicHelper.RemotePreviewType;
import org.wordpress.android.ui.prefs.AccountSettingsActivity;
import org.wordpress.android.ui.prefs.AppSettingsActivity;
import org.wordpress.android.ui.prefs.BlogPreferencesActivity;
import org.wordpress.android.ui.prefs.MyProfileActivity;
import org.wordpress.android.ui.prefs.categories.detail.CategoryDetailActivity;
import org.wordpress.android.ui.prefs.categories.list.CategoriesListActivity;
import org.wordpress.android.ui.prefs.notifications.NotificationsSettingsActivity;
import org.wordpress.android.ui.publicize.PublicizeListActivity;
import org.wordpress.android.ui.qrcodeauth.QRCodeAuthActivity;
import org.wordpress.android.ui.reader.ReaderActivityLauncher;
import org.wordpress.android.ui.reader.ReaderConstants;
import org.wordpress.android.ui.sitecreation.SiteCreationActivity;
import org.wordpress.android.ui.sitecreation.misc.SiteCreationSource;
import org.wordpress.android.ui.stats.StatsConnectJetpackActivity;
import org.wordpress.android.ui.stats.StatsConstants;
import org.wordpress.android.ui.stats.StatsTimeframe;
import org.wordpress.android.ui.stats.StatsViewType;
import org.wordpress.android.ui.stats.refresh.StatsActivity;
import org.wordpress.android.ui.stats.refresh.StatsActivity.StatsLaunchedFrom;
import org.wordpress.android.ui.stats.refresh.StatsViewAllActivity;
import org.wordpress.android.ui.stats.refresh.lists.StatsListViewModel.StatsSection;
import org.wordpress.android.ui.stats.refresh.lists.detail.StatsDetailActivity;
import org.wordpress.android.ui.stats.refresh.lists.sections.granular.SelectedDateProvider.SelectedDate;
import org.wordpress.android.ui.stats.refresh.lists.sections.insights.management.InsightsManagementActivity;
import org.wordpress.android.ui.stockmedia.StockMediaPickerActivity;
import org.wordpress.android.ui.stories.StoryComposerActivity;
import org.wordpress.android.ui.suggestion.SuggestionActivity;
import org.wordpress.android.ui.suggestion.SuggestionType;
import org.wordpress.android.ui.themes.ThemeBrowserActivity;
import org.wordpress.android.util.AppLog;
import org.wordpress.android.util.AppLog.T;
import org.wordpress.android.util.ToastUtils;
import org.wordpress.android.util.UrlUtils;
import org.wordpress.android.util.WPActivityUtils;
import org.wordpress.android.util.analytics.AnalyticsUtils;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static com.wordpress.stories.util.BundleUtilsKt.KEY_STORY_INDEX;
import static com.wordpress.stories.util.BundleUtilsKt.KEY_STORY_SAVE_RESULT;
import static org.wordpress.android.analytics.AnalyticsTracker.ACTIVITY_LOG_ACTIVITY_ID_KEY;
import static org.wordpress.android.analytics.AnalyticsTracker.Stat.POST_LIST_ACCESS_ERROR;
import static org.wordpress.android.analytics.AnalyticsTracker.Stat.READER_ARTICLE_DETAIL_REBLOGGED;
import static org.wordpress.android.analytics.AnalyticsTracker.Stat.READER_ARTICLE_REBLOGGED;
import static org.wordpress.android.analytics.AnalyticsTracker.Stat.STATS_ACCESS_ERROR;
import static org.wordpress.android.editor.gutenberg.GutenbergEditorFragment.ARG_STORY_BLOCK_ID;
import static org.wordpress.android.imageeditor.preview.PreviewImageFragment.ARG_EDIT_IMAGE_DATA;
import static org.wordpress.android.login.LoginMode.JETPACK_LOGIN_ONLY;
import static org.wordpress.android.login.LoginMode.WPCOM_LOGIN_ONLY;
import static org.wordpress.android.push.NotificationsProcessingService.ARG_NOTIFICATION_TYPE;
import static org.wordpress.android.ui.WPWebViewActivity.ENCODING_UTF8;
import static org.wordpress.android.ui.jetpack.backup.download.BackupDownloadViewModelKt.KEY_BACKUP_DOWNLOAD_ACTIVITY_ID_KEY;
import static org.wordpress.android.ui.jetpack.restore.RestoreViewModelKt.KEY_RESTORE_ACTIVITY_ID_KEY;
import static org.wordpress.android.ui.jetpack.scan.ScanFragment.ARG_THREAT_ID;
import static org.wordpress.android.ui.media.MediaBrowserActivity.ARG_BROWSER_TYPE;
import static org.wordpress.android.ui.pages.PagesActivityKt.EXTRA_PAGE_REMOTE_ID_KEY;
import static org.wordpress.android.ui.stories.StoryComposerActivity.KEY_ALL_UNFLATTENED_LOADED_SLIDES;
import static org.wordpress.android.ui.stories.StoryComposerActivity.KEY_LAUNCHED_FROM_GUTENBERG;
import static org.wordpress.android.ui.stories.StoryComposerActivity.KEY_POST_LOCAL_ID;
import static org.wordpress.android.viewmodel.activitylog.ActivityLogDetailViewModelKt.ACTIVITY_LOG_ARE_BUTTONS_VISIBLE_KEY;
import static org.wordpress.android.viewmodel.activitylog.ActivityLogDetailViewModelKt.ACTIVITY_LOG_ID_KEY;
import static org.wordpress.android.viewmodel.activitylog.ActivityLogDetailViewModelKt.ACTIVITY_LOG_IS_RESTORE_HIDDEN_KEY;
import static org.wordpress.android.viewmodel.activitylog.ActivityLogViewModelKt.ACTIVITY_LOG_REWINDABLE_ONLY_KEY;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class ActivityLauncher {

    public static final String SOURCE_TRACK_EVENT_PROPERTY_KEY = "source";

    public static final String BACKUP_TRACK_EVENT_PROPERTY_VALUE = "backup";

    public static final String ACTIVITY_LOG_TRACK_EVENT_PROPERTY_VALUE = "activity_log";

    private static final String CATEGORY_DETAIL_ID = "category_detail_key";

    public static void showMainActivityAndLoginEpilogue(Activity activity, ArrayList<Integer> oldSitesIds, boolean doLoginUpdate) {
        Intent intent = new Intent(activity, WPMainActivity.class);
        if (!ListenerUtil.mutListener.listen(25323)) {
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        }
        if (!ListenerUtil.mutListener.listen(25324)) {
            intent.putExtra(WPMainActivity.ARG_DO_LOGIN_UPDATE, doLoginUpdate);
        }
        if (!ListenerUtil.mutListener.listen(25325)) {
            intent.putExtra(WPMainActivity.ARG_SHOW_LOGIN_EPILOGUE, true);
        }
        if (!ListenerUtil.mutListener.listen(25326)) {
            intent.putIntegerArrayListExtra(WPMainActivity.ARG_OLD_SITES_IDS, oldSitesIds);
        }
        if (!ListenerUtil.mutListener.listen(25327)) {
            activity.startActivity(intent);
        }
    }

    public static void showMainActivityAndSignupEpilogue(Activity activity, String name, String email, String photoUrl, String username) {
        Intent intent = new Intent(activity, WPMainActivity.class);
        if (!ListenerUtil.mutListener.listen(25328)) {
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        }
        if (!ListenerUtil.mutListener.listen(25329)) {
            intent.putExtra(WPMainActivity.ARG_SHOW_SIGNUP_EPILOGUE, true);
        }
        if (!ListenerUtil.mutListener.listen(25330)) {
            intent.putExtra(SignupEpilogueActivity.EXTRA_SIGNUP_DISPLAY_NAME, name);
        }
        if (!ListenerUtil.mutListener.listen(25331)) {
            intent.putExtra(SignupEpilogueActivity.EXTRA_SIGNUP_EMAIL_ADDRESS, email);
        }
        if (!ListenerUtil.mutListener.listen(25332)) {
            intent.putExtra(SignupEpilogueActivity.EXTRA_SIGNUP_PHOTO_URL, photoUrl);
        }
        if (!ListenerUtil.mutListener.listen(25333)) {
            intent.putExtra(SignupEpilogueActivity.EXTRA_SIGNUP_USERNAME, username);
        }
        if (!ListenerUtil.mutListener.listen(25334)) {
            activity.startActivity(intent);
        }
    }

    /**
     * Presents the site picker and expects the selection result
     *
     * @param activity the activity that starts the site picker and expects the result
     * @param site     the preselected site
     */
    public static void showSitePickerForResult(Activity activity, SiteModel site) {
        Intent intent = createSitePickerIntent(activity, site, SitePickerMode.DEFAULT_MODE);
        if (!ListenerUtil.mutListener.listen(25335)) {
            activity.startActivityForResult(intent, RequestCodes.SITE_PICKER);
        }
    }

    /**
     * Presents the site picker and expects the selection result
     *
     * @param fragment the fragment that starts the site picker and expects the result
     * @param site     the preselected site
     * @param mode     site picker mode
     */
    public static void showSitePickerForResult(Fragment fragment, SiteModel site, SitePickerMode mode) {
        Intent intent = createSitePickerIntent(fragment.getContext(), site, mode);
        if (!ListenerUtil.mutListener.listen(25336)) {
            fragment.startActivityForResult(intent, RequestCodes.SITE_PICKER);
        }
    }

    /**
     * Creates a site picker intent
     *
     * @param context the context to use for the intent creation
     * @param site    the preselected site
     * @param mode    site picker mode
     * @return the site picker intent
     */
    private static Intent createSitePickerIntent(Context context, SiteModel site, SitePickerMode mode) {
        Intent intent = new Intent(context, SitePickerActivity.class);
        if (!ListenerUtil.mutListener.listen(25337)) {
            intent.putExtra(SitePickerActivity.KEY_SITE_LOCAL_ID, site.getId());
        }
        if (!ListenerUtil.mutListener.listen(25338)) {
            intent.putExtra(SitePickerActivity.KEY_SITE_PICKER_MODE, mode);
        }
        return intent;
    }

    /**
     * Use {@link org.wordpress.android.ui.photopicker.MediaPickerLauncher::showPhotoPickerForResult}  instead
     */
    @Deprecated
    public static void showPhotoPickerForResult(Activity activity, @NonNull MediaBrowserType browserType, @Nullable SiteModel site, @Nullable Integer localPostId) {
        Intent intent = createShowPhotoPickerIntent(activity, browserType, site, localPostId);
        if (!ListenerUtil.mutListener.listen(25339)) {
            activity.startActivityForResult(intent, RequestCodes.PHOTO_PICKER);
        }
    }

    private static Intent createShowPhotoPickerIntent(Context context, @NonNull MediaBrowserType browserType, @Nullable SiteModel site, @Nullable Integer localPostId) {
        Intent intent = new Intent(context, PhotoPickerActivity.class);
        if (!ListenerUtil.mutListener.listen(25340)) {
            intent.putExtra(ARG_BROWSER_TYPE, browserType);
        }
        if (!ListenerUtil.mutListener.listen(25342)) {
            if (site != null) {
                if (!ListenerUtil.mutListener.listen(25341)) {
                    intent.putExtra(WordPress.SITE, site);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(25344)) {
            if (localPostId != null) {
                if (!ListenerUtil.mutListener.listen(25343)) {
                    intent.putExtra(MediaPickerConstants.LOCAL_POST_ID, localPostId.intValue());
                }
            }
        }
        return intent;
    }

    /**
     * Use {@link org.wordpress.android.ui.photopicker.MediaPickerLauncher::showStockMediaPickerForResult}  instead
     */
    @Deprecated
    public static void showStockMediaPickerForResult(Activity activity, @NonNull SiteModel site, int requestCode) {
        Map<String, String> properties = new HashMap<>();
        if (!ListenerUtil.mutListener.listen(25345)) {
            properties.put("from", activity.getClass().getSimpleName());
        }
        if (!ListenerUtil.mutListener.listen(25346)) {
            AnalyticsTracker.track(AnalyticsTracker.Stat.STOCK_MEDIA_ACCESSED, properties);
        }
        Intent intent = new Intent(activity, StockMediaPickerActivity.class);
        if (!ListenerUtil.mutListener.listen(25347)) {
            intent.putExtra(WordPress.SITE, site);
        }
        if (!ListenerUtil.mutListener.listen(25348)) {
            intent.putExtra(StockMediaPickerActivity.KEY_REQUEST_CODE, requestCode);
        }
        if (!ListenerUtil.mutListener.listen(25349)) {
            activity.startActivityForResult(intent, requestCode);
        }
    }

    public static void startJetpackInstall(Context context, JetpackConnectionSource source, SiteModel site) {
        Intent intent = new Intent(context, JetpackRemoteInstallActivity.class);
        if (!ListenerUtil.mutListener.listen(25350)) {
            intent.putExtra(WordPress.SITE, site);
        }
        if (!ListenerUtil.mutListener.listen(25351)) {
            intent.putExtra(JetpackRemoteInstallFragment.TRACKING_SOURCE_KEY, source);
        }
        if (!ListenerUtil.mutListener.listen(25352)) {
            context.startActivity(intent);
        }
    }

    public static void continueJetpackConnect(Context context, JetpackConnectionSource source, SiteModel site) {
        if (!ListenerUtil.mutListener.listen(25355)) {
            switch(source) {
                case NOTIFICATIONS:
                    if (!ListenerUtil.mutListener.listen(25353)) {
                        continueJetpackConnectForNotifications(context, site);
                    }
                    break;
                case STATS:
                    if (!ListenerUtil.mutListener.listen(25354)) {
                        continueJetpackConnectForStats(context, site);
                    }
                    break;
            }
        }
    }

    private static void continueJetpackConnectForNotifications(Context context, SiteModel site) {
        Intent intent = new Intent(context, WPMainActivity.class);
        if (!ListenerUtil.mutListener.listen(25356)) {
            intent.putExtra(WordPress.SITE, site);
        }
        if (!ListenerUtil.mutListener.listen(25357)) {
            intent.putExtra(WPMainActivity.ARG_CONTINUE_JETPACK_CONNECT, true);
        }
        if (!ListenerUtil.mutListener.listen(25358)) {
            context.startActivity(intent);
        }
    }

    private static void continueJetpackConnectForStats(Context context, SiteModel site) {
        Intent intent = new Intent(context, StatsConnectJetpackActivity.class);
        if (!ListenerUtil.mutListener.listen(25359)) {
            intent.putExtra(WordPress.SITE, site);
        }
        if (!ListenerUtil.mutListener.listen(25360)) {
            intent.putExtra(StatsConnectJetpackActivity.ARG_CONTINUE_JETPACK_CONNECT, true);
        }
        if (!ListenerUtil.mutListener.listen(25361)) {
            context.startActivity(intent);
        }
    }

    public static void viewNotifications(Context context) {
        Intent intent = new Intent(context, WPMainActivity.class);
        if (!ListenerUtil.mutListener.listen(25362)) {
            intent.putExtra(WPMainActivity.ARG_OPEN_PAGE, WPMainActivity.ARG_NOTIFICATIONS);
        }
        if (!ListenerUtil.mutListener.listen(25363)) {
            intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        }
        if (!ListenerUtil.mutListener.listen(25364)) {
            context.startActivity(intent);
        }
    }

    public static void viewNotificationsInNewStack(Context context) {
        Intent intent = getMainActivityInNewStack(context);
        if (!ListenerUtil.mutListener.listen(25365)) {
            intent.putExtra(WPMainActivity.ARG_OPEN_PAGE, WPMainActivity.ARG_NOTIFICATIONS);
        }
        if (!ListenerUtil.mutListener.listen(25366)) {
            context.startActivity(intent);
        }
    }

    public static void viewMySiteInNewStack(Context context) {
        Intent intent = getMainActivityInNewStack(context);
        if (!ListenerUtil.mutListener.listen(25367)) {
            intent.putExtra(WPMainActivity.ARG_OPEN_PAGE, WPMainActivity.ARG_MY_SITE);
        }
        if (!ListenerUtil.mutListener.listen(25368)) {
            context.startActivity(intent);
        }
    }

    public static void viewReader(Context context) {
        Intent intent = new Intent(context, WPMainActivity.class);
        if (!ListenerUtil.mutListener.listen(25369)) {
            intent.putExtra(WPMainActivity.ARG_OPEN_PAGE, WPMainActivity.ARG_READER);
        }
        if (!ListenerUtil.mutListener.listen(25370)) {
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        }
        if (!ListenerUtil.mutListener.listen(25371)) {
            context.startActivity(intent);
        }
    }

    public static void viewReaderInNewStack(Context context) {
        Intent intent = getMainActivityInNewStack(context);
        if (!ListenerUtil.mutListener.listen(25372)) {
            intent.putExtra(WPMainActivity.ARG_OPEN_PAGE, WPMainActivity.ARG_READER);
        }
        if (!ListenerUtil.mutListener.listen(25373)) {
            context.startActivity(intent);
        }
    }

    public static void viewPostDeeplinkInNewStack(Context context, Uri uri) {
        Intent mainActivityIntent = getMainActivityInNewStack(context).putExtra(WPMainActivity.ARG_OPEN_PAGE, WPMainActivity.ARG_READER);
        Intent viewPostIntent = new Intent(ReaderConstants.ACTION_VIEW_POST, uri);
        if (!ListenerUtil.mutListener.listen(25374)) {
            TaskStackBuilder.create(context).addNextIntent(mainActivityIntent).addNextIntent(viewPostIntent).startActivities();
        }
    }

    public static void viewReaderPostDetailInNewStack(Context context, long blogId, long postId, Uri uri) {
        Intent mainActivityIntent = getMainActivityInNewStack(context).putExtra(WPMainActivity.ARG_OPEN_PAGE, WPMainActivity.ARG_READER);
        Intent viewPostIntent = ReaderActivityLauncher.buildReaderPostDetailIntent(context, false, blogId, postId, null, 0, false, uri.toString());
        if (!ListenerUtil.mutListener.listen(25375)) {
            TaskStackBuilder.create(context).addNextIntent(mainActivityIntent).addNextIntent(viewPostIntent).startActivities();
        }
    }

    public static void openEditorInNewStack(Context context) {
        Intent intent = getMainActivityInNewStack(context);
        if (!ListenerUtil.mutListener.listen(25376)) {
            intent.putExtra(WPMainActivity.ARG_OPEN_PAGE, WPMainActivity.ARG_EDITOR);
        }
        if (!ListenerUtil.mutListener.listen(25377)) {
            context.startActivity(intent);
        }
    }

    public static Intent openEditorWithBloggingPrompt(@NonNull final Context context, final int promptId, final EntryPoint entryPoint) {
        final Intent intent = getMainActivityInNewStack(context);
        if (!ListenerUtil.mutListener.listen(25378)) {
            intent.putExtra(WPMainActivity.ARG_OPEN_PAGE, WPMainActivity.ARG_EDITOR);
        }
        if (!ListenerUtil.mutListener.listen(25379)) {
            intent.putExtra(WPMainActivity.ARG_EDITOR_PROMPT_ID, promptId);
        }
        if (!ListenerUtil.mutListener.listen(25380)) {
            intent.putExtra(WPMainActivity.ARG_EDITOR_ORIGIN, entryPoint);
        }
        return intent;
    }

    public static Intent openEditorWithPromptAndDismissNotificationIntent(@NonNull final Context context, final int notificationId, final BloggingPromptModel bloggingPrompt, @Nullable final Stat stat, final EntryPoint entryPoint) {
        final Intent intent = getMainActivityInNewStack(context);
        if (!ListenerUtil.mutListener.listen(25381)) {
            intent.putExtra(WPMainActivity.ARG_OPEN_PAGE, WPMainActivity.ARG_EDITOR);
        }
        if (!ListenerUtil.mutListener.listen(25382)) {
            intent.putExtra(WPMainActivity.ARG_EDITOR_PROMPT_ID, bloggingPrompt.getId());
        }
        if (!ListenerUtil.mutListener.listen(25383)) {
            intent.putExtra(WPMainActivity.ARG_DISMISS_NOTIFICATION, notificationId);
        }
        if (!ListenerUtil.mutListener.listen(25384)) {
            intent.putExtra(WPMainActivity.ARG_EDITOR_ORIGIN, entryPoint);
        }
        if (!ListenerUtil.mutListener.listen(25385)) {
            intent.putExtra(WPMainActivity.ARG_STAT_TO_TRACK, stat);
        }
        return intent;
    }

    public static void openEditorForSiteInNewStack(Context context, @NonNull SiteModel site) {
        TaskStackBuilder taskStackBuilder = TaskStackBuilder.create(context);
        Intent mainActivityIntent = getMainActivityInNewStack(context);
        Intent editorIntent = new Intent(context, EditPostActivity.class);
        if (!ListenerUtil.mutListener.listen(25386)) {
            editorIntent.putExtra(WordPress.SITE, site);
        }
        if (!ListenerUtil.mutListener.listen(25387)) {
            editorIntent.putExtra(EditPostActivity.EXTRA_IS_PAGE, false);
        }
        if (!ListenerUtil.mutListener.listen(25388)) {
            taskStackBuilder.addNextIntent(mainActivityIntent);
        }
        if (!ListenerUtil.mutListener.listen(25389)) {
            taskStackBuilder.addNextIntent(editorIntent);
        }
        if (!ListenerUtil.mutListener.listen(25390)) {
            taskStackBuilder.startActivities();
        }
    }

    public static void openEditorForPostInNewStack(Context context, @NonNull SiteModel site, int localPostId) {
        TaskStackBuilder taskStackBuilder = TaskStackBuilder.create(context);
        Intent mainActivityIntent = getMainActivityInNewStack(context);
        Intent editorIntent = new Intent(context, EditPostActivity.class);
        if (!ListenerUtil.mutListener.listen(25391)) {
            editorIntent.putExtra(WordPress.SITE, site);
        }
        if (!ListenerUtil.mutListener.listen(25392)) {
            editorIntent.putExtra(EditPostActivity.EXTRA_POST_LOCAL_ID, localPostId);
        }
        if (!ListenerUtil.mutListener.listen(25393)) {
            editorIntent.putExtra(EditPostActivity.EXTRA_IS_PAGE, false);
        }
        if (!ListenerUtil.mutListener.listen(25394)) {
            taskStackBuilder.addNextIntent(mainActivityIntent);
        }
        if (!ListenerUtil.mutListener.listen(25395)) {
            taskStackBuilder.addNextIntent(editorIntent);
        }
        if (!ListenerUtil.mutListener.listen(25396)) {
            taskStackBuilder.startActivities();
        }
    }

    /**
     * Opens the editor and passes the information needed for a reblog action
     *
     * @param activity the calling activity
     * @param site     the site on which the post should be reblogged
     * @param post     the post to be reblogged
     */
    public static void openEditorForReblog(Activity activity, @Nullable SiteModel site, @Nullable ReaderPost post, PagePostCreationSourcesDetail reblogSource) {
        if (!ListenerUtil.mutListener.listen(25398)) {
            if (post == null) {
                if (!ListenerUtil.mutListener.listen(25397)) {
                    ToastUtils.showToast(activity, R.string.post_not_found, ToastUtils.Duration.SHORT);
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(25400)) {
            if (site == null) {
                if (!ListenerUtil.mutListener.listen(25399)) {
                    ToastUtils.showToast(activity, R.string.blog_not_found, ToastUtils.Duration.SHORT);
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(25401)) {
            AnalyticsUtils.trackWithReblogDetails(reblogSource == PagePostCreationSourcesDetail.POST_FROM_REBLOG ? READER_ARTICLE_REBLOGGED : READER_ARTICLE_DETAIL_REBLOGGED, post.blogId, post.postId, site.getSiteId());
        }
        Intent editorIntent = new Intent(activity, EditPostActivity.class);
        if (!ListenerUtil.mutListener.listen(25402)) {
            editorIntent.putExtra(EditPostActivity.EXTRA_REBLOG_POST_TITLE, post.getTitle());
        }
        if (!ListenerUtil.mutListener.listen(25403)) {
            editorIntent.putExtra(EditPostActivity.EXTRA_REBLOG_POST_QUOTE, post.getExcerpt());
        }
        if (!ListenerUtil.mutListener.listen(25404)) {
            editorIntent.putExtra(EditPostActivity.EXTRA_REBLOG_POST_IMAGE, post.getFeaturedImage());
        }
        if (!ListenerUtil.mutListener.listen(25405)) {
            editorIntent.putExtra(EditPostActivity.EXTRA_REBLOG_POST_CITATION, post.getUrl());
        }
        if (!ListenerUtil.mutListener.listen(25406)) {
            editorIntent.setAction(EditPostActivity.ACTION_REBLOG);
        }
        if (!ListenerUtil.mutListener.listen(25407)) {
            addNewPostForResult(editorIntent, activity, site, false, reblogSource, -1, null);
        }
    }

    public static void viewStatsInNewStack(Context context, SiteModel site) {
        if (!ListenerUtil.mutListener.listen(25408)) {
            viewStatsInNewStack(context, site, null);
        }
    }

    public static void viewStatsInNewStack(Context context, SiteModel site, @Nullable StatsTimeframe statsTimeframe) {
        if (!ListenerUtil.mutListener.listen(25409)) {
            viewStatsInNewStack(context, site, statsTimeframe, null);
        }
    }

    public static void viewStatsInNewStack(Context context, SiteModel site, @Nullable StatsTimeframe statsTimeframe, @Nullable String period) {
        if (!ListenerUtil.mutListener.listen(25411)) {
            if (site == null) {
                if (!ListenerUtil.mutListener.listen(25410)) {
                    handleMissingSite(context);
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(25412)) {
            runIntentOverMainActivityInNewStack(context, StatsActivity.buildIntent(context, site, statsTimeframe, period));
        }
    }

    private static void handleMissingSite(Context context) {
        if (!ListenerUtil.mutListener.listen(25413)) {
            AppLog.e(T.STATS, "SiteModel is null when opening the stats from the deeplink.");
        }
        if (!ListenerUtil.mutListener.listen(25414)) {
            AnalyticsTracker.track(STATS_ACCESS_ERROR, ActivityLauncher.class.getName(), "NullPointerException", "Failed to open Stats from the deeplink because of the null SiteModel");
        }
        if (!ListenerUtil.mutListener.listen(25415)) {
            ToastUtils.showToast(context, R.string.stats_cannot_be_started, ToastUtils.Duration.SHORT);
        }
    }

    private static void runIntentOverMainActivityInNewStack(Context context, Intent intent) {
        if (!ListenerUtil.mutListener.listen(25416)) {
            buildIntentOverMainActivityInNewStack(context, intent).startActivities();
        }
    }

    public static PendingIntent buildStatsPendingIntentOverMainActivityInNewStack(Context context, SiteModel site, @Nullable StatsTimeframe timeframe, @Nullable String period, @Nullable NotificationType type, int requestCode, int flags) {
        return buildPendingIntentOverMainActivityInNewStack(context, StatsActivity.buildIntent(context, site, timeframe, period, type), requestCode, flags);
    }

    private static PendingIntent buildPendingIntentOverMainActivityInNewStack(Context context, Intent intent, int requestCode, int flags) {
        return buildIntentOverMainActivityInNewStack(context, intent).getPendingIntent(requestCode, flags);
    }

    private static TaskStackBuilder buildIntentOverMainActivityInNewStack(Context context, Intent intent) {
        TaskStackBuilder taskStackBuilder = TaskStackBuilder.create(context);
        Intent mainActivityIntent = getMainActivityInNewStack(context);
        if (!ListenerUtil.mutListener.listen(25417)) {
            taskStackBuilder.addNextIntent(mainActivityIntent);
        }
        if (!ListenerUtil.mutListener.listen(25418)) {
            taskStackBuilder.addNextIntent(intent);
        }
        return taskStackBuilder;
    }

    public static void viewStatsInNewStack(Context context) {
        Intent intent = getMainActivityInNewStack(context);
        if (!ListenerUtil.mutListener.listen(25419)) {
            intent.putExtra(WPMainActivity.ARG_OPEN_PAGE, WPMainActivity.ARG_STATS);
        }
        if (!ListenerUtil.mutListener.listen(25420)) {
            context.startActivity(intent);
        }
    }

    public static void viewStatsForTimeframeInNewStack(Context context, StatsTimeframe statsTimeframe) {
        Intent intent = getMainActivityInNewStack(context);
        if (!ListenerUtil.mutListener.listen(25421)) {
            intent.putExtra(WPMainActivity.ARG_OPEN_PAGE, WPMainActivity.ARG_STATS);
        }
        if (!ListenerUtil.mutListener.listen(25422)) {
            intent.putExtra(WPMainActivity.ARG_STATS_TIMEFRAME, statsTimeframe);
        }
        if (!ListenerUtil.mutListener.listen(25423)) {
            context.startActivity(intent);
        }
    }

    private static Intent getMainActivityInNewStack(Context context) {
        Intent mainActivityIntent = new Intent(context, WPMainActivity.class);
        if (!ListenerUtil.mutListener.listen(25424)) {
            mainActivityIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        return mainActivityIntent;
    }

    public static void viewSavedPostsListInReader(Context context) {
        if (!ListenerUtil.mutListener.listen(25425)) {
            ReaderPostTable.purgeUnbookmarkedPostsWithBookmarkTag();
        }
        Intent intent = new Intent(context, WPMainActivity.class);
        if (!ListenerUtil.mutListener.listen(25426)) {
            intent.putExtra(WPMainActivity.ARG_OPEN_PAGE, WPMainActivity.ARG_READER);
        }
        if (!ListenerUtil.mutListener.listen(25427)) {
            intent.putExtra(WPMainActivity.ARG_READER_BOOKMARK_TAB, true);
        }
        if (!ListenerUtil.mutListener.listen(25428)) {
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        }
        if (!ListenerUtil.mutListener.listen(25429)) {
            context.startActivity(intent);
        }
    }

    public static void viewBlogStats(Context context, SiteModel site) {
        if (!ListenerUtil.mutListener.listen(25434)) {
            if (site == null) {
                if (!ListenerUtil.mutListener.listen(25431)) {
                    AppLog.e(T.STATS, "SiteModel is null when opening the stats.");
                }
                if (!ListenerUtil.mutListener.listen(25432)) {
                    AnalyticsTracker.track(STATS_ACCESS_ERROR, ActivityLauncher.class.getName(), "NullPointerException", "Failed to open Stats because of the null SiteModel");
                }
                if (!ListenerUtil.mutListener.listen(25433)) {
                    ToastUtils.showToast(context, R.string.stats_cannot_be_started, ToastUtils.Duration.SHORT);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(25430)) {
                    StatsActivity.start(context, site);
                }
            }
        }
    }

    public static void openBlogStats(Context context, SiteModel site) {
        if (!ListenerUtil.mutListener.listen(25441)) {
            if (site == null) {
                if (!ListenerUtil.mutListener.listen(25438)) {
                    AppLog.e(T.STATS, "SiteModel is null when opening the stats.");
                }
                if (!ListenerUtil.mutListener.listen(25439)) {
                    AnalyticsTracker.track(STATS_ACCESS_ERROR, ActivityLauncher.class.getName(), "NullPointerException", "Failed to open Stats because of the null SiteModel");
                }
                if (!ListenerUtil.mutListener.listen(25440)) {
                    ToastUtils.showToast(context, R.string.stats_cannot_be_started, ToastUtils.Duration.SHORT);
                }
            } else {
                Intent intent = new Intent(context, StatsActivity.class);
                if (!ListenerUtil.mutListener.listen(25435)) {
                    intent.putExtra(StatsActivity.ARG_LAUNCHED_FROM, StatsLaunchedFrom.FEATURE_ANNOUNCEMENT);
                }
                if (!ListenerUtil.mutListener.listen(25436)) {
                    intent.putExtra(WordPress.SITE, site);
                }
                if (!ListenerUtil.mutListener.listen(25437)) {
                    context.startActivity(intent);
                }
            }
        }
    }

    public static void viewBlogStatsForTimeframe(Context context, SiteModel site, StatsTimeframe statsTimeframe) {
        if (!ListenerUtil.mutListener.listen(25446)) {
            if (site == null) {
                if (!ListenerUtil.mutListener.listen(25443)) {
                    AppLog.e(T.STATS, "SiteModel is null when opening the stats.");
                }
                if (!ListenerUtil.mutListener.listen(25444)) {
                    AnalyticsTracker.track(STATS_ACCESS_ERROR, ActivityLauncher.class.getName(), "NullPointerException", "Failed to open Stats because of the null SiteModel");
                }
                if (!ListenerUtil.mutListener.listen(25445)) {
                    ToastUtils.showToast(context, R.string.stats_cannot_be_started, ToastUtils.Duration.SHORT);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(25442)) {
                    StatsActivity.start(context, site, statsTimeframe);
                }
            }
        }
    }

    public static void viewAllTabbedInsightsStats(Context context, StatsViewType statsType, int selectedTab, int localSiteId) {
        if (!ListenerUtil.mutListener.listen(25447)) {
            StatsViewAllActivity.startForTabbedInsightsStats(context, statsType, selectedTab, localSiteId);
        }
    }

    public static void viewAllInsightsStats(Context context, StatsViewType statsType, int localSiteId) {
        if (!ListenerUtil.mutListener.listen(25448)) {
            StatsViewAllActivity.startForInsights(context, statsType, localSiteId);
        }
    }

    public static void viewAllGranularStats(Context context, StatsGranularity granularity, SelectedDate selectedDate, StatsViewType statsType, int localSiteId) {
        if (!ListenerUtil.mutListener.listen(25449)) {
            StatsViewAllActivity.startForGranularStats(context, statsType, granularity, selectedDate, localSiteId);
        }
    }

    public static void viewInsightsManagement(Context context, int localSiteId) {
        Intent intent = new Intent(context, InsightsManagementActivity.class);
        if (!ListenerUtil.mutListener.listen(25450)) {
            intent.putExtra(WordPress.LOCAL_SITE_ID, localSiteId);
        }
        if (!ListenerUtil.mutListener.listen(25451)) {
            context.startActivity(intent);
        }
    }

    public static void viewBlogStatsAfterJetpackSetup(Context context, SiteModel site) {
        if (!ListenerUtil.mutListener.listen(25455)) {
            if (site == null) {
                if (!ListenerUtil.mutListener.listen(25452)) {
                    AppLog.e(T.STATS, "SiteModel is null when opening the stats.");
                }
                if (!ListenerUtil.mutListener.listen(25453)) {
                    AnalyticsTracker.track(STATS_ACCESS_ERROR, ActivityLauncher.class.getName(), "NullPointerException", "Failed to open Stats because of the null SiteModel");
                }
                if (!ListenerUtil.mutListener.listen(25454)) {
                    ToastUtils.showToast(context, R.string.stats_cannot_be_started, ToastUtils.Duration.SHORT);
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(25456)) {
            StatsActivity.start(context, site);
        }
    }

    public static void viewConnectJetpackForStats(Context context, SiteModel site) {
        Intent intent = new Intent(context, StatsConnectJetpackActivity.class);
        if (!ListenerUtil.mutListener.listen(25457)) {
            intent.putExtra(WordPress.SITE, site);
        }
        if (!ListenerUtil.mutListener.listen(25458)) {
            context.startActivity(intent);
        }
    }

    public static void viewBlogPlans(Context context, SiteModel site) {
        Intent intent = new Intent(context, PlansActivity.class);
        if (!ListenerUtil.mutListener.listen(25459)) {
            intent.putExtra(WordPress.SITE, site);
        }
        if (!ListenerUtil.mutListener.listen(25460)) {
            context.startActivity(intent);
        }
        if (!ListenerUtil.mutListener.listen(25461)) {
            AnalyticsUtils.trackWithSiteDetails(Stat.OPENED_PLANS, site);
        }
    }

    public static void viewCurrentBlogPosts(Context context, SiteModel site) {
        if (!ListenerUtil.mutListener.listen(25462)) {
            viewCurrentBlogPostsOfType(context, site, null);
        }
    }

    public static void viewCurrentBlogPostsOfType(Context context, SiteModel site, PostListType postListType) {
        if (!ListenerUtil.mutListener.listen(25466)) {
            if (site == null) {
                if (!ListenerUtil.mutListener.listen(25463)) {
                    AppLog.e(T.POSTS, "Site cannot be null when opening posts");
                }
                if (!ListenerUtil.mutListener.listen(25464)) {
                    AnalyticsTracker.track(POST_LIST_ACCESS_ERROR, ActivityLauncher.class.getName(), "NullPointerException", "Failed to open Posts because of the null SiteModel");
                }
                if (!ListenerUtil.mutListener.listen(25465)) {
                    ToastUtils.showToast(context, R.string.posts_cannot_be_started, ToastUtils.Duration.SHORT);
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(25469)) {
            if (postListType == null) {
                if (!ListenerUtil.mutListener.listen(25468)) {
                    context.startActivity(PostsListActivity.buildIntent(context, site));
                }
            } else {
                if (!ListenerUtil.mutListener.listen(25467)) {
                    context.startActivity(PostsListActivity.buildIntent(context, site, postListType, false, null));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(25470)) {
            AnalyticsUtils.trackWithSiteDetails(AnalyticsTracker.Stat.OPENED_POSTS, site);
        }
    }

    public static void viewCurrentBlogMedia(Context context, SiteModel site) {
        Intent intent = new Intent(context, MediaBrowserActivity.class);
        if (!ListenerUtil.mutListener.listen(25471)) {
            intent.putExtra(WordPress.SITE, site);
        }
        if (!ListenerUtil.mutListener.listen(25472)) {
            intent.putExtra(ARG_BROWSER_TYPE, MediaBrowserType.BROWSER);
        }
        if (!ListenerUtil.mutListener.listen(25473)) {
            context.startActivity(intent);
        }
        if (!ListenerUtil.mutListener.listen(25474)) {
            AnalyticsUtils.trackWithSiteDetails(AnalyticsTracker.Stat.OPENED_MEDIA_LIBRARY, site);
        }
    }

    public static void viewCurrentBlogPages(@NonNull Context context, @NonNull SiteModel site) {
        Intent intent = new Intent(context, PagesActivity.class);
        if (!ListenerUtil.mutListener.listen(25475)) {
            intent.putExtra(WordPress.SITE, site);
        }
        if (!ListenerUtil.mutListener.listen(25476)) {
            context.startActivity(intent);
        }
        if (!ListenerUtil.mutListener.listen(25477)) {
            AnalyticsUtils.trackWithSiteDetails(AnalyticsTracker.Stat.OPENED_PAGES, site);
        }
    }

    public static void viewPageParentForResult(@NonNull Fragment fragment, @NonNull PageModel page) {
        Intent intent = new Intent(fragment.getContext(), PageParentActivity.class);
        if (!ListenerUtil.mutListener.listen(25478)) {
            intent.putExtra(WordPress.SITE, page.getSite());
        }
        if (!ListenerUtil.mutListener.listen(25479)) {
            intent.putExtra(EXTRA_PAGE_REMOTE_ID_KEY, page.getRemoteId());
        }
        if (!ListenerUtil.mutListener.listen(25480)) {
            fragment.startActivityForResult(intent, RequestCodes.PAGE_PARENT);
        }
        if (!ListenerUtil.mutListener.listen(25481)) {
            AnalyticsUtils.trackWithSiteDetails(AnalyticsTracker.Stat.OPENED_PAGE_PARENT, page.getSite());
        }
    }

    public static void viewUnifiedComments(Context context, SiteModel site) {
        Intent intent = new Intent(context, UnifiedCommentsActivity.class);
        if (!ListenerUtil.mutListener.listen(25482)) {
            intent.putExtra(WordPress.SITE, site);
        }
        if (!ListenerUtil.mutListener.listen(25483)) {
            context.startActivity(intent);
        }
        if (!ListenerUtil.mutListener.listen(25484)) {
            AnalyticsUtils.trackWithSiteDetails(AnalyticsTracker.Stat.OPENED_COMMENTS, site);
        }
    }

    public static void viewUnifiedCommentsDetails(Context context, SiteModel site) {
        Intent intent = new Intent(context, UnifiedCommentsDetailsActivity.class);
        if (!ListenerUtil.mutListener.listen(25485)) {
            intent.putExtra(WordPress.SITE, site);
        }
        if (!ListenerUtil.mutListener.listen(25486)) {
            context.startActivity(intent);
        }
    }

    public static void viewCurrentBlogThemes(Context context, SiteModel site) {
        Intent intent = new Intent(context, ThemeBrowserActivity.class);
        if (!ListenerUtil.mutListener.listen(25487)) {
            intent.putExtra(WordPress.SITE, site);
        }
        if (!ListenerUtil.mutListener.listen(25488)) {
            context.startActivity(intent);
        }
        if (!ListenerUtil.mutListener.listen(25489)) {
            AnalyticsUtils.trackWithSiteDetails(AnalyticsTracker.Stat.THEMES_ACCESSED_THEMES_BROWSER, site);
        }
    }

    public static void viewCurrentBlogPeople(Context context, SiteModel site) {
        Intent intent = new Intent(context, PeopleManagementActivity.class);
        if (!ListenerUtil.mutListener.listen(25490)) {
            intent.putExtra(WordPress.SITE, site);
        }
        if (!ListenerUtil.mutListener.listen(25491)) {
            context.startActivity(intent);
        }
        if (!ListenerUtil.mutListener.listen(25492)) {
            AnalyticsUtils.trackWithSiteDetails(AnalyticsTracker.Stat.OPENED_PEOPLE_MANAGEMENT, site);
        }
    }

    public static void viewPluginBrowser(Context context, SiteModel site) {
        if (!ListenerUtil.mutListener.listen(25496)) {
            if (PluginUtils.isPluginFeatureAvailable(site)) {
                if (!ListenerUtil.mutListener.listen(25493)) {
                    AnalyticsUtils.trackWithSiteDetails(AnalyticsTracker.Stat.OPENED_PLUGIN_DIRECTORY, site);
                }
                Intent intent = new Intent(context, PluginBrowserActivity.class);
                if (!ListenerUtil.mutListener.listen(25494)) {
                    intent.putExtra(WordPress.SITE, site);
                }
                if (!ListenerUtil.mutListener.listen(25495)) {
                    context.startActivity(intent);
                }
            }
        }
    }

    public static void viewPluginDetail(Activity context, SiteModel site, String slug) {
        if (!ListenerUtil.mutListener.listen(25501)) {
            if (PluginUtils.isPluginFeatureAvailable(site)) {
                if (!ListenerUtil.mutListener.listen(25497)) {
                    AnalyticsUtils.trackWithSiteDetails(AnalyticsTracker.Stat.OPENED_PLUGIN_DETAIL, site);
                }
                Intent intent = new Intent(context, PluginDetailActivity.class);
                if (!ListenerUtil.mutListener.listen(25498)) {
                    intent.putExtra(WordPress.SITE, site);
                }
                if (!ListenerUtil.mutListener.listen(25499)) {
                    intent.putExtra(PluginDetailActivity.KEY_PLUGIN_SLUG, slug);
                }
                if (!ListenerUtil.mutListener.listen(25500)) {
                    context.startActivity(intent);
                }
            }
        }
    }

    public static void viewDomainsDashboardActivity(Activity activity, @NonNull SiteModel site) {
        Intent intent = new Intent(activity, DomainsDashboardActivity.class);
        if (!ListenerUtil.mutListener.listen(25502)) {
            intent.putExtra(WordPress.SITE, site);
        }
        if (!ListenerUtil.mutListener.listen(25503)) {
            activity.startActivity(intent);
        }
    }

    public static void viewDomainRegistrationActivityForResult(Activity activity, @NonNull SiteModel site, @NonNull DomainRegistrationPurpose purpose) {
        Intent intent = createDomainRegistrationActivityIntent(activity, site, purpose);
        if (!ListenerUtil.mutListener.listen(25504)) {
            activity.startActivityForResult(intent, RequestCodes.DOMAIN_REGISTRATION);
        }
    }

    public static void viewDomainRegistrationActivityForResult(Fragment fragment, @NonNull SiteModel site, @NonNull DomainRegistrationPurpose purpose) {
        Intent intent = createDomainRegistrationActivityIntent(fragment.getContext(), site, purpose);
        if (!ListenerUtil.mutListener.listen(25505)) {
            fragment.startActivityForResult(intent, RequestCodes.DOMAIN_REGISTRATION);
        }
    }

    private static Intent createDomainRegistrationActivityIntent(Context context, @NonNull SiteModel site, @NonNull DomainRegistrationPurpose purpose) {
        Intent intent = new Intent(context, DomainRegistrationActivity.class);
        if (!ListenerUtil.mutListener.listen(25506)) {
            intent.putExtra(WordPress.SITE, site);
        }
        if (!ListenerUtil.mutListener.listen(25507)) {
            intent.putExtra(DomainRegistrationActivity.DOMAIN_REGISTRATION_PURPOSE_KEY, purpose);
        }
        return intent;
    }

    public static void viewActivityLogList(Activity activity, SiteModel site) {
        if (!ListenerUtil.mutListener.listen(25509)) {
            if (site == null) {
                if (!ListenerUtil.mutListener.listen(25508)) {
                    ToastUtils.showToast(activity, R.string.blog_not_found, ToastUtils.Duration.SHORT);
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(25510)) {
            AnalyticsUtils.trackWithSiteDetails(AnalyticsTracker.Stat.ACTIVITY_LOG_LIST_OPENED, site);
        }
        Intent intent = new Intent(activity, ActivityLogListActivity.class);
        if (!ListenerUtil.mutListener.listen(25511)) {
            intent.putExtra(WordPress.SITE, site);
        }
        if (!ListenerUtil.mutListener.listen(25512)) {
            activity.startActivity(intent);
        }
    }

    public static void viewBackupList(Activity activity, SiteModel site) {
        if (!ListenerUtil.mutListener.listen(25514)) {
            if (site == null) {
                if (!ListenerUtil.mutListener.listen(25513)) {
                    ToastUtils.showToast(activity, R.string.blog_not_found, ToastUtils.Duration.SHORT);
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(25515)) {
            AnalyticsUtils.trackWithSiteDetails(AnalyticsTracker.Stat.JETPACK_BACKUP_LIST_OPENED, site);
        }
        Intent intent = new Intent(activity, ActivityLogListActivity.class);
        if (!ListenerUtil.mutListener.listen(25516)) {
            intent.putExtra(WordPress.SITE, site);
        }
        if (!ListenerUtil.mutListener.listen(25517)) {
            intent.putExtra(ACTIVITY_LOG_REWINDABLE_ONLY_KEY, true);
        }
        if (!ListenerUtil.mutListener.listen(25518)) {
            activity.startActivity(intent);
        }
    }

    public static void viewActivityLogDetailForResult(Activity activity, SiteModel site, String activityId, boolean isButtonVisible, boolean isRestoreHidden, boolean rewindableOnly) {
        Map<String, Object> properties = new HashMap<>();
        if (!ListenerUtil.mutListener.listen(25519)) {
            properties.put(ACTIVITY_LOG_ACTIVITY_ID_KEY, activityId);
        }
        String source;
        if (rewindableOnly) {
            source = BACKUP_TRACK_EVENT_PROPERTY_VALUE;
        } else {
            source = ACTIVITY_LOG_TRACK_EVENT_PROPERTY_VALUE;
        }
        if (!ListenerUtil.mutListener.listen(25520)) {
            properties.put(SOURCE_TRACK_EVENT_PROPERTY_KEY, source);
        }
        if (!ListenerUtil.mutListener.listen(25521)) {
            AnalyticsUtils.trackWithSiteDetails(AnalyticsTracker.Stat.ACTIVITY_LOG_DETAIL_OPENED, site, properties);
        }
        Intent intent = new Intent(activity, ActivityLogDetailActivity.class);
        if (!ListenerUtil.mutListener.listen(25522)) {
            intent.putExtra(WordPress.SITE, site);
        }
        if (!ListenerUtil.mutListener.listen(25523)) {
            intent.putExtra(ACTIVITY_LOG_ID_KEY, activityId);
        }
        if (!ListenerUtil.mutListener.listen(25524)) {
            intent.putExtra(ACTIVITY_LOG_ARE_BUTTONS_VISIBLE_KEY, isButtonVisible);
        }
        if (!ListenerUtil.mutListener.listen(25525)) {
            intent.putExtra(ACTIVITY_LOG_IS_RESTORE_HIDDEN_KEY, isRestoreHidden);
        }
        if (!ListenerUtil.mutListener.listen(25526)) {
            intent.putExtra(SOURCE_TRACK_EVENT_PROPERTY_KEY, source);
        }
        if (!ListenerUtil.mutListener.listen(25527)) {
            activity.startActivityForResult(intent, RequestCodes.ACTIVITY_LOG_DETAIL);
        }
    }

    public static void viewScan(Activity activity, SiteModel site) {
        if (!ListenerUtil.mutListener.listen(25529)) {
            if (site == null) {
                if (!ListenerUtil.mutListener.listen(25528)) {
                    ToastUtils.showToast(activity, R.string.blog_not_found, ToastUtils.Duration.SHORT);
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(25530)) {
            AnalyticsTracker.track(Stat.JETPACK_SCAN_ACCESSED);
        }
        Intent intent = new Intent(activity, ScanActivity.class);
        if (!ListenerUtil.mutListener.listen(25531)) {
            intent.putExtra(WordPress.SITE, site);
        }
        if (!ListenerUtil.mutListener.listen(25532)) {
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        }
        if (!ListenerUtil.mutListener.listen(25533)) {
            activity.startActivity(intent);
        }
    }

    public static void viewScanRequestScanState(Activity activity, SiteModel site, @StringRes int messageRes) {
        if (!ListenerUtil.mutListener.listen(25535)) {
            if (site == null) {
                if (!ListenerUtil.mutListener.listen(25534)) {
                    ToastUtils.showToast(activity, R.string.blog_not_found, ToastUtils.Duration.SHORT);
                }
                return;
            }
        }
        Intent intent = new Intent(activity, ScanActivity.class);
        if (!ListenerUtil.mutListener.listen(25536)) {
            intent.putExtra(WordPress.SITE, site);
        }
        if (!ListenerUtil.mutListener.listen(25537)) {
            intent.putExtra(ScanActivity.REQUEST_SCAN_STATE, messageRes);
        }
        if (!ListenerUtil.mutListener.listen(25538)) {
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        }
        if (!ListenerUtil.mutListener.listen(25539)) {
            activity.startActivity(intent);
        }
    }

    public static void viewScanRequestFixState(Activity activity, SiteModel site, long threatId) {
        if (!ListenerUtil.mutListener.listen(25541)) {
            if (site == null) {
                if (!ListenerUtil.mutListener.listen(25540)) {
                    ToastUtils.showToast(activity, R.string.blog_not_found, ToastUtils.Duration.SHORT);
                }
                return;
            }
        }
        Intent intent = new Intent(activity, ScanActivity.class);
        if (!ListenerUtil.mutListener.listen(25542)) {
            intent.putExtra(WordPress.SITE, site);
        }
        if (!ListenerUtil.mutListener.listen(25543)) {
            intent.putExtra(ScanActivity.REQUEST_FIX_STATE, threatId);
        }
        if (!ListenerUtil.mutListener.listen(25544)) {
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        }
        if (!ListenerUtil.mutListener.listen(25545)) {
            activity.startActivity(intent);
        }
    }

    public static void viewScanHistory(Activity activity, SiteModel site) {
        if (!ListenerUtil.mutListener.listen(25547)) {
            if (site == null) {
                if (!ListenerUtil.mutListener.listen(25546)) {
                    ToastUtils.showToast(activity, R.string.blog_not_found, ToastUtils.Duration.SHORT);
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(25548)) {
            AnalyticsTracker.track(Stat.JETPACK_SCAN_HISTORY_ACCESSED);
        }
        Intent intent = new Intent(activity, ScanHistoryActivity.class);
        if (!ListenerUtil.mutListener.listen(25549)) {
            intent.putExtra(WordPress.SITE, site);
        }
        if (!ListenerUtil.mutListener.listen(25550)) {
            activity.startActivity(intent);
        }
    }

    public static void viewThreatDetails(@NonNull Fragment fragment, SiteModel site, @NonNull Long threatId) {
        Intent intent = new Intent(fragment.getContext(), ThreatDetailsActivity.class);
        if (!ListenerUtil.mutListener.listen(25551)) {
            intent.putExtra(ARG_THREAT_ID, threatId);
        }
        if (!ListenerUtil.mutListener.listen(25552)) {
            intent.putExtra(WordPress.SITE, site);
        }
        if (!ListenerUtil.mutListener.listen(25553)) {
            fragment.startActivity(intent);
        }
    }

    public static void viewBlogSettingsForResult(Activity activity, SiteModel site) {
        Intent intent = new Intent(activity, BlogPreferencesActivity.class);
        if (!ListenerUtil.mutListener.listen(25554)) {
            intent.putExtra(WordPress.SITE, site);
        }
        if (!ListenerUtil.mutListener.listen(25555)) {
            activity.startActivityForResult(intent, RequestCodes.SITE_SETTINGS);
        }
        if (!ListenerUtil.mutListener.listen(25556)) {
            AnalyticsUtils.trackWithSiteDetails(AnalyticsTracker.Stat.OPENED_BLOG_SETTINGS, site);
        }
    }

    public static void viewBlogSharing(Context context, SiteModel site) {
        Intent intent = new Intent(context, PublicizeListActivity.class);
        if (!ListenerUtil.mutListener.listen(25557)) {
            intent.putExtra(WordPress.SITE, site);
        }
        if (!ListenerUtil.mutListener.listen(25558)) {
            context.startActivity(intent);
        }
        if (!ListenerUtil.mutListener.listen(25559)) {
            AnalyticsUtils.trackWithSiteDetails(Stat.OPENED_SHARING_MANAGEMENT, site);
        }
    }

    public static void viewCurrentSite(Context context, SiteModel site, boolean openFromHeader) {
        AnalyticsTracker.Stat stat = openFromHeader ? AnalyticsTracker.Stat.OPENED_VIEW_SITE_FROM_HEADER : AnalyticsTracker.Stat.OPENED_VIEW_SITE;
        if (!ListenerUtil.mutListener.listen(25560)) {
            AnalyticsUtils.trackWithSiteDetails(stat, site);
        }
        if (!ListenerUtil.mutListener.listen(25569)) {
            if (site == null) {
                if (!ListenerUtil.mutListener.listen(25568)) {
                    ToastUtils.showToast(context, R.string.blog_not_found, ToastUtils.Duration.SHORT);
                }
            } else if (site.getUrl() == null) {
                if (!ListenerUtil.mutListener.listen(25566)) {
                    ToastUtils.showToast(context, R.string.blog_not_found, ToastUtils.Duration.SHORT);
                }
                if (!ListenerUtil.mutListener.listen(25567)) {
                    AppLog.w(AppLog.T.UTILS, "Site URL is null. Login URL: " + site.getLoginUrl());
                }
            } else {
                String siteUrl = site.getUrl();
                if (!ListenerUtil.mutListener.listen(25565)) {
                    if (site.isWPCom()) {
                        if (!ListenerUtil.mutListener.listen(25564)) {
                            // Show wp.com sites authenticated
                            WPWebViewActivity.openUrlByUsingGlobalWPCOMCredentials(context, siteUrl, true);
                        }
                    } else if ((ListenerUtil.mutListener.listen(25561) ? (!TextUtils.isEmpty(site.getUsername()) || !TextUtils.isEmpty(site.getPassword())) : (!TextUtils.isEmpty(site.getUsername()) && !TextUtils.isEmpty(site.getPassword())))) {
                        if (!ListenerUtil.mutListener.listen(25563)) {
                            // Show self-hosted sites as authenticated since we should have the username & password
                            WPWebViewActivity.openUrlByUsingBlogCredentials(context, site, null, siteUrl, new String[] {}, false, true, false);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(25562)) {
                            // connected through REST API.
                            WPWebViewActivity.openURL(context, siteUrl, true, site.isPrivateWPComAtomic() ? site.getSiteId() : 0);
                        }
                    }
                }
            }
        }
    }

    public static void viewBlogAdmin(Context context, SiteModel site) {
        if (!ListenerUtil.mutListener.listen(25572)) {
            if ((ListenerUtil.mutListener.listen(25570) ? (site == null && site.getAdminUrl() == null) : (site == null || site.getAdminUrl() == null))) {
                if (!ListenerUtil.mutListener.listen(25571)) {
                    ToastUtils.showToast(context, R.string.blog_not_found, ToastUtils.Duration.SHORT);
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(25573)) {
            AnalyticsUtils.trackWithSiteDetails(AnalyticsTracker.Stat.OPENED_VIEW_ADMIN, site);
        }
        if (!ListenerUtil.mutListener.listen(25574)) {
            openUrlExternal(context, site.getAdminUrl());
        }
    }

    public static void addNewPostForResult(Activity activity, SiteModel site, boolean isPromo, PagePostCreationSourcesDetail source, final int promptId, final EntryPoint entryPoint) {
        if (!ListenerUtil.mutListener.listen(25575)) {
            addNewPostForResult(new Intent(activity, EditPostActivity.class), activity, site, isPromo, source, promptId, entryPoint);
        }
    }

    public static void addNewPostForResult(Intent intent, Activity activity, SiteModel site, boolean isPromo, PagePostCreationSourcesDetail source, final int promptId, final EntryPoint entryPoint) {
        if (!ListenerUtil.mutListener.listen(25576)) {
            if (site == null) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(25577)) {
            intent.putExtra(WordPress.SITE, site);
        }
        if (!ListenerUtil.mutListener.listen(25578)) {
            intent.putExtra(EditPostActivity.EXTRA_IS_PAGE, false);
        }
        if (!ListenerUtil.mutListener.listen(25579)) {
            intent.putExtra(EditPostActivity.EXTRA_IS_PROMO, isPromo);
        }
        if (!ListenerUtil.mutListener.listen(25580)) {
            intent.putExtra(AnalyticsUtils.EXTRA_CREATION_SOURCE_DETAIL, source);
        }
        if (!ListenerUtil.mutListener.listen(25581)) {
            intent.putExtra(EditPostActivity.EXTRA_PROMPT_ID, promptId);
        }
        if (!ListenerUtil.mutListener.listen(25582)) {
            intent.putExtra(EditPostActivity.EXTRA_ENTRY_POINT, entryPoint);
        }
        if (!ListenerUtil.mutListener.listen(25583)) {
            activity.startActivityForResult(intent, RequestCodes.EDIT_POST);
        }
    }

    public static void addNewStoryForResult(Activity activity, SiteModel site, PagePostCreationSourcesDetail source) {
        if (!ListenerUtil.mutListener.listen(25584)) {
            if (site == null) {
                return;
            }
        }
        Intent intent = new Intent(activity, StoryComposerActivity.class);
        if (!ListenerUtil.mutListener.listen(25585)) {
            intent.putExtra(WordPress.SITE, site);
        }
        if (!ListenerUtil.mutListener.listen(25586)) {
            intent.putExtra(AnalyticsUtils.EXTRA_CREATION_SOURCE_DETAIL, source);
        }
        if (!ListenerUtil.mutListener.listen(25587)) {
            intent.putExtra(MediaPickerConstants.EXTRA_LAUNCH_WPSTORIES_CAMERA_REQUESTED, true);
        }
        if (!ListenerUtil.mutListener.listen(25588)) {
            activity.startActivityForResult(intent, RequestCodes.CREATE_STORY);
        }
    }

    public static void addNewStoryWithMediaIdsForResult(Activity activity, SiteModel site, PagePostCreationSourcesDetail source, long[] mediaIds) {
        if (!ListenerUtil.mutListener.listen(25589)) {
            if (site == null) {
                return;
            }
        }
        Intent intent = new Intent(activity, StoryComposerActivity.class);
        if (!ListenerUtil.mutListener.listen(25590)) {
            intent.putExtra(WordPress.SITE, site);
        }
        if (!ListenerUtil.mutListener.listen(25591)) {
            intent.putExtra(MediaBrowserActivity.RESULT_IDS, mediaIds);
        }
        if (!ListenerUtil.mutListener.listen(25592)) {
            intent.putExtra(AnalyticsUtils.EXTRA_CREATION_SOURCE_DETAIL, source);
        }
        if (!ListenerUtil.mutListener.listen(25593)) {
            activity.startActivityForResult(intent, RequestCodes.CREATE_STORY);
        }
    }

    public static void addNewStoryWithMediaUrisForResult(Activity activity, SiteModel site, PagePostCreationSourcesDetail source, String[] mediaUris) {
        if (!ListenerUtil.mutListener.listen(25594)) {
            if (site == null) {
                return;
            }
        }
        Intent intent = new Intent(activity, StoryComposerActivity.class);
        if (!ListenerUtil.mutListener.listen(25595)) {
            intent.putExtra(WordPress.SITE, site);
        }
        if (!ListenerUtil.mutListener.listen(25596)) {
            intent.putExtra(MediaPickerConstants.EXTRA_MEDIA_URIS, mediaUris);
        }
        if (!ListenerUtil.mutListener.listen(25597)) {
            intent.putExtra(AnalyticsUtils.EXTRA_CREATION_SOURCE_DETAIL, source);
        }
        if (!ListenerUtil.mutListener.listen(25598)) {
            activity.startActivityForResult(intent, RequestCodes.CREATE_STORY);
        }
    }

    public static void editStoryWithMediaIdsForResult(Activity activity, SiteModel site, long[] mediaIds, boolean launchingFromGutenberg) {
        if (!ListenerUtil.mutListener.listen(25599)) {
            if (site == null) {
                return;
            }
        }
        Intent intent = new Intent(activity, StoryComposerActivity.class);
        if (!ListenerUtil.mutListener.listen(25600)) {
            intent.putExtra(WordPress.SITE, site);
        }
        if (!ListenerUtil.mutListener.listen(25601)) {
            intent.putExtra(MediaBrowserActivity.RESULT_IDS, mediaIds);
        }
        if (!ListenerUtil.mutListener.listen(25602)) {
            activity.startActivityForResult(intent, RequestCodes.EDIT_STORY);
        }
    }

    public static void editStoryForResult(Activity activity, SiteModel site, LocalId localPostId, int storyIndex, boolean allStorySlidesAreEditable, boolean launchedFromGutenberg, String storyBlockId) {
        if (!ListenerUtil.mutListener.listen(25603)) {
            if (site == null) {
                return;
            }
        }
        Intent intent = new Intent(activity, StoryComposerActivity.class);
        if (!ListenerUtil.mutListener.listen(25604)) {
            intent.putExtra(WordPress.SITE, site);
        }
        if (!ListenerUtil.mutListener.listen(25605)) {
            intent.putExtra(KEY_POST_LOCAL_ID, localPostId.getValue());
        }
        if (!ListenerUtil.mutListener.listen(25606)) {
            intent.putExtra(KEY_STORY_INDEX, storyIndex);
        }
        if (!ListenerUtil.mutListener.listen(25607)) {
            intent.putExtra(KEY_LAUNCHED_FROM_GUTENBERG, launchedFromGutenberg);
        }
        if (!ListenerUtil.mutListener.listen(25608)) {
            intent.putExtra(KEY_ALL_UNFLATTENED_LOADED_SLIDES, allStorySlidesAreEditable);
        }
        if (!ListenerUtil.mutListener.listen(25609)) {
            intent.putExtra(ARG_STORY_BLOCK_ID, storyBlockId);
        }
        if (!ListenerUtil.mutListener.listen(25610)) {
            activity.startActivityForResult(intent, RequestCodes.EDIT_STORY);
        }
    }

    public static void editEmptyStoryForResult(Activity activity, SiteModel site, LocalId localPostId, int storyIndex, String storyBlockId) {
        if (!ListenerUtil.mutListener.listen(25611)) {
            if (site == null) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(25612)) {
            AnalyticsTracker.track(Stat.STORY_BLOCK_ADD_MEDIA_TAPPED);
        }
        Intent intent = new Intent(activity, StoryComposerActivity.class);
        if (!ListenerUtil.mutListener.listen(25613)) {
            intent.putExtra(WordPress.SITE, site);
        }
        if (!ListenerUtil.mutListener.listen(25614)) {
            intent.putExtra(KEY_LAUNCHED_FROM_GUTENBERG, true);
        }
        if (!ListenerUtil.mutListener.listen(25615)) {
            intent.putExtra(MediaPickerConstants.EXTRA_LAUNCH_WPSTORIES_MEDIA_PICKER_REQUESTED, true);
        }
        if (!ListenerUtil.mutListener.listen(25616)) {
            intent.putExtra(KEY_POST_LOCAL_ID, localPostId.getValue());
        }
        if (!ListenerUtil.mutListener.listen(25617)) {
            intent.putExtra(KEY_STORY_INDEX, storyIndex);
        }
        if (!ListenerUtil.mutListener.listen(25618)) {
            intent.putExtra(ARG_STORY_BLOCK_ID, storyBlockId);
        }
        if (!ListenerUtil.mutListener.listen(25619)) {
            activity.startActivityForResult(intent, RequestCodes.EDIT_STORY);
        }
    }

    public static void editPostOrPageForResult(Activity activity, SiteModel site, PostModel post) {
        if (!ListenerUtil.mutListener.listen(25620)) {
            editPostOrPageForResult(new Intent(activity, EditPostActivity.class), activity, site, post.getId(), false);
        }
    }

    public static void editPostOrPageForResult(Activity activity, SiteModel site, PostModel post, boolean loadAutoSaveRevision) {
        if (!ListenerUtil.mutListener.listen(25621)) {
            editPostOrPageForResult(new Intent(activity, EditPostActivity.class), activity, site, post.getId(), loadAutoSaveRevision);
        }
    }

    public static void editPostOrPageForResult(Intent intent, Activity activity, SiteModel site, int postLocalId) {
        if (!ListenerUtil.mutListener.listen(25622)) {
            editPostOrPageForResult(intent, activity, site, postLocalId, false);
        }
    }

    public static void editPostOrPageForResult(Intent intent, Activity activity, SiteModel site, int postLocalId, boolean loadAutoSaveRevision) {
        if (!ListenerUtil.mutListener.listen(25623)) {
            if (site == null) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(25624)) {
            intent.putExtra(WordPress.SITE, site);
        }
        if (!ListenerUtil.mutListener.listen(25625)) {
            // However, we still want to keep passing the SiteModel to avoid confusion around local & remote ids.
            intent.putExtra(EditPostActivity.EXTRA_POST_LOCAL_ID, postLocalId);
        }
        if (!ListenerUtil.mutListener.listen(25626)) {
            intent.putExtra(EditPostActivity.EXTRA_LOAD_AUTO_SAVE_REVISION, loadAutoSaveRevision);
        }
        if (!ListenerUtil.mutListener.listen(25627)) {
            activity.startActivityForResult(intent, RequestCodes.EDIT_POST);
        }
    }

    public static void editPageForResult(@NonNull Fragment fragment, @NonNull SiteModel site, int pageLocalId, boolean loadAutoSaveRevision) {
        Intent intent = new Intent(fragment.getContext(), EditPostActivity.class);
        if (!ListenerUtil.mutListener.listen(25628)) {
            editPageForResult(intent, fragment, site, pageLocalId, loadAutoSaveRevision, RequestCodes.EDIT_POST);
        }
    }

    public static void editPageForResult(Intent intent, @NonNull Fragment fragment, @NonNull SiteModel site, int pageLocalId, boolean loadAutoSaveRevision) {
        if (!ListenerUtil.mutListener.listen(25629)) {
            editPageForResult(intent, fragment, site, pageLocalId, loadAutoSaveRevision, RequestCodes.EDIT_POST);
        }
    }

    public static void editLandingPageForResult(@NonNull Fragment fragment, @NonNull SiteModel site, int homeLocalId, boolean isNewSite) {
        Intent intent = new Intent(fragment.getContext(), EditPostActivity.class);
        if (!ListenerUtil.mutListener.listen(25630)) {
            intent.putExtra(EditPostActivity.EXTRA_IS_LANDING_EDITOR, true);
        }
        if (!ListenerUtil.mutListener.listen(25631)) {
            intent.putExtra(EditPostActivity.EXTRA_IS_LANDING_EDITOR_OPENED_FOR_NEW_SITE, isNewSite);
        }
        if (!ListenerUtil.mutListener.listen(25632)) {
            editPageForResult(intent, fragment, site, homeLocalId, false, RequestCodes.EDIT_LANDING_PAGE);
        }
    }

    public static void editPageForResult(Intent intent, @NonNull Fragment fragment, @NonNull SiteModel site, int pageLocalId, boolean loadAutoSaveRevision, int requestCode) {
        if (!ListenerUtil.mutListener.listen(25633)) {
            intent.putExtra(WordPress.SITE, site);
        }
        if (!ListenerUtil.mutListener.listen(25634)) {
            intent.putExtra(EditPostActivity.EXTRA_POST_LOCAL_ID, pageLocalId);
        }
        if (!ListenerUtil.mutListener.listen(25635)) {
            intent.putExtra(EditPostActivity.EXTRA_LOAD_AUTO_SAVE_REVISION, loadAutoSaveRevision);
        }
        if (!ListenerUtil.mutListener.listen(25636)) {
            fragment.startActivityForResult(intent, requestCode);
        }
    }

    public static void addNewPageForResult(@NonNull Activity activity, @NonNull SiteModel site, @NonNull String title, @NonNull String content, @Nullable String template, @NonNull PagePostCreationSourcesDetail source) {
        Intent intent = new Intent(activity, EditPostActivity.class);
        if (!ListenerUtil.mutListener.listen(25637)) {
            intent.putExtra(WordPress.SITE, site);
        }
        if (!ListenerUtil.mutListener.listen(25638)) {
            intent.putExtra(EditPostActivity.EXTRA_IS_PAGE, true);
        }
        if (!ListenerUtil.mutListener.listen(25639)) {
            intent.putExtra(EditPostActivity.EXTRA_IS_PROMO, false);
        }
        if (!ListenerUtil.mutListener.listen(25640)) {
            intent.putExtra(EditPostActivity.EXTRA_PAGE_TITLE, title);
        }
        if (!ListenerUtil.mutListener.listen(25641)) {
            intent.putExtra(EditPostActivity.EXTRA_PAGE_CONTENT, content);
        }
        if (!ListenerUtil.mutListener.listen(25642)) {
            intent.putExtra(EditPostActivity.EXTRA_PAGE_TEMPLATE, template);
        }
        if (!ListenerUtil.mutListener.listen(25643)) {
            intent.putExtra(AnalyticsUtils.EXTRA_CREATION_SOURCE_DETAIL, source);
        }
        if (!ListenerUtil.mutListener.listen(25644)) {
            activity.startActivityForResult(intent, RequestCodes.EDIT_POST);
        }
    }

    public static void addNewPageForResult(@NonNull Fragment fragment, @NonNull SiteModel site, @NonNull String title, @NonNull String content, @Nullable String template, @NonNull PagePostCreationSourcesDetail source) {
        Intent intent = new Intent(fragment.getContext(), EditPostActivity.class);
        if (!ListenerUtil.mutListener.listen(25645)) {
            intent.putExtra(WordPress.SITE, site);
        }
        if (!ListenerUtil.mutListener.listen(25646)) {
            intent.putExtra(EditPostActivity.EXTRA_IS_PAGE, true);
        }
        if (!ListenerUtil.mutListener.listen(25647)) {
            intent.putExtra(EditPostActivity.EXTRA_IS_PROMO, false);
        }
        if (!ListenerUtil.mutListener.listen(25648)) {
            intent.putExtra(EditPostActivity.EXTRA_PAGE_TITLE, title);
        }
        if (!ListenerUtil.mutListener.listen(25649)) {
            intent.putExtra(EditPostActivity.EXTRA_PAGE_CONTENT, content);
        }
        if (!ListenerUtil.mutListener.listen(25650)) {
            intent.putExtra(EditPostActivity.EXTRA_PAGE_TEMPLATE, template);
        }
        if (!ListenerUtil.mutListener.listen(25651)) {
            intent.putExtra(AnalyticsUtils.EXTRA_CREATION_SOURCE_DETAIL, source);
        }
        if (!ListenerUtil.mutListener.listen(25652)) {
            fragment.startActivityForResult(intent, RequestCodes.EDIT_POST);
        }
    }

    public static void viewHistoryDetailForResult(@NonNull final Activity activity, @NonNull final Revision revision, @NonNull final long[] previousRevisionsIds, final long postId, final long siteId) {
        Intent intent = new Intent(activity, HistoryDetailActivity.class);
        if (!ListenerUtil.mutListener.listen(25653)) {
            intent.putExtra(HistoryDetailContainerFragment.EXTRA_CURRENT_REVISION, revision);
        }
        final Bundle extras = new Bundle();
        if (!ListenerUtil.mutListener.listen(25654)) {
            extras.putLongArray(HistoryDetailContainerFragment.EXTRA_PREVIOUS_REVISIONS_IDS, previousRevisionsIds);
        }
        if (!ListenerUtil.mutListener.listen(25655)) {
            extras.putLong(HistoryDetailContainerFragment.EXTRA_POST_ID, postId);
        }
        if (!ListenerUtil.mutListener.listen(25656)) {
            extras.putLong(HistoryDetailContainerFragment.EXTRA_SITE_ID, siteId);
        }
        if (!ListenerUtil.mutListener.listen(25657)) {
            intent.putExtras(extras);
        }
        if (!ListenerUtil.mutListener.listen(25658)) {
            activity.startActivityForResult(intent, RequestCodes.HISTORY_DETAIL);
        }
    }

    /*
     * Load the post preview as an authenticated URL so stats aren't bumped
     */
    public static void browsePostOrPage(Context context, SiteModel site, PostImmutableModel post) {
        if (!ListenerUtil.mutListener.listen(25659)) {
            browsePostOrPageEx(context, site, post, RemotePreviewType.NOT_A_REMOTE_PREVIEW);
        }
    }

    public static void previewPostOrPageForResult(Activity activity, SiteModel site, PostImmutableModel post, RemotePreviewType remotePreviewType) {
        if (!ListenerUtil.mutListener.listen(25660)) {
            browsePostOrPageEx(activity, site, post, remotePreviewType);
        }
    }

    private static void browsePostOrPageEx(Context context, SiteModel site, PostImmutableModel post, RemotePreviewType remotePreviewType) {
        if (!ListenerUtil.mutListener.listen(25663)) {
            if ((ListenerUtil.mutListener.listen(25662) ? ((ListenerUtil.mutListener.listen(25661) ? (site == null && post == null) : (site == null || post == null)) && TextUtils.isEmpty(post.getLink())) : ((ListenerUtil.mutListener.listen(25661) ? (site == null && post == null) : (site == null || post == null)) || TextUtils.isEmpty(post.getLink())))) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(25665)) {
            if ((ListenerUtil.mutListener.listen(25664) ? (remotePreviewType == RemotePreviewType.REMOTE_PREVIEW_WITH_REMOTE_AUTO_SAVE || TextUtils.isEmpty(post.getAutoSavePreviewUrl())) : (remotePreviewType == RemotePreviewType.REMOTE_PREVIEW_WITH_REMOTE_AUTO_SAVE && TextUtils.isEmpty(post.getAutoSavePreviewUrl())))) {
                return;
            }
        }
        String url = PostUtils.getPreviewUrlForPost(remotePreviewType, post);
        String shareableUrl = post.getLink();
        String shareSubject = post.getTitle();
        boolean startPreviewForResult = remotePreviewType != RemotePreviewType.NOT_A_REMOTE_PREVIEW;
        if (!ListenerUtil.mutListener.listen(25672)) {
            if (site.isWPCom()) {
                if (!ListenerUtil.mutListener.listen(25671)) {
                    WPWebViewActivity.openPostUrlByUsingGlobalWPCOMCredentials(context, url, shareableUrl, shareSubject, true, startPreviewForResult);
                }
            } else if ((ListenerUtil.mutListener.listen(25666) ? (site.isWPComAtomic() || !site.isPrivateWPComAtomic()) : (site.isWPComAtomic() && !site.isPrivateWPComAtomic()))) {
                if (!ListenerUtil.mutListener.listen(25670)) {
                    openAtomicBlogPostPreview(context, url, site.getLoginUrl(), site.getFrameNonce());
                }
            } else if ((ListenerUtil.mutListener.listen(25667) ? (site.isJetpackConnected() || site.isUsingWpComRestApi()) : (site.isJetpackConnected() && site.isUsingWpComRestApi()))) {
                if (!ListenerUtil.mutListener.listen(25669)) {
                    WPWebViewActivity.openJetpackBlogPostPreview(context, url, shareableUrl, shareSubject, site.getFrameNonce(), true, startPreviewForResult, site.isPrivateWPComAtomic() ? site.getSiteId() : 0);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(25668)) {
                    // Ref: https://github.com/wordpress-mobile/WordPress-Android/issues/4873
                    WPWebViewActivity.openUrlByUsingBlogCredentials(context, site, post, url, new String[] { post.getLink() }, true, true, startPreviewForResult);
                }
            }
        }
    }

    private static void openAtomicBlogPostPreview(Context context, String url, String authenticationUrl, String frameNonce) {
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            if (!ListenerUtil.mutListener.listen(25674)) {
                intent.setData(Uri.parse(authenticationUrl + "?redirect_to=" + URLEncoder.encode(url + "&frame-nonce=" + UrlUtils.urlEncode(frameNonce), ENCODING_UTF8)));
            }
            if (!ListenerUtil.mutListener.listen(25675)) {
                context.startActivity(intent);
            }
        } catch (UnsupportedEncodingException e) {
            if (!ListenerUtil.mutListener.listen(25673)) {
                e.printStackTrace();
            }
        }
    }

    public static void showActionableEmptyView(Context context, WPWebViewUsageCategory actionableState, String postTitle) {
        if (!ListenerUtil.mutListener.listen(25676)) {
            WPWebViewActivity.openActionableEmptyViewDirectly(context, actionableState, postTitle);
        }
    }

    public static void viewMyProfile(Context context) {
        Intent intent = new Intent(context, MyProfileActivity.class);
        if (!ListenerUtil.mutListener.listen(25677)) {
            AnalyticsTracker.track(AnalyticsTracker.Stat.OPENED_MY_PROFILE);
        }
        if (!ListenerUtil.mutListener.listen(25678)) {
            context.startActivity(intent);
        }
    }

    public static void viewMeActivityForResult(Activity activity) {
        Intent intent = new Intent(activity, MeActivity.class);
        if (!ListenerUtil.mutListener.listen(25679)) {
            AnalyticsTracker.track(AnalyticsTracker.Stat.ME_ACCESSED);
        }
        if (!ListenerUtil.mutListener.listen(25680)) {
            activity.startActivityForResult(intent, RequestCodes.APP_SETTINGS);
        }
    }

    public static void viewPostLikesListActivity(Activity activity, long siteId, long postId, HeaderData headerData, EngagementNavigationSource source) {
        Intent intent = new Intent(activity, EngagedPeopleListActivity.class);
        if (!ListenerUtil.mutListener.listen(25681)) {
            intent.putExtra(EngagedPeopleListActivity.KEY_LIST_SCENARIO, new ListScenario(ListScenarioType.LOAD_POST_LIKES, source, siteId, postId, 0L, "", headerData));
        }
        if (!ListenerUtil.mutListener.listen(25682)) {
            activity.startActivity(intent);
        }
    }

    public static void viewAccountSettings(Context context) {
        Intent intent = new Intent(context, AccountSettingsActivity.class);
        if (!ListenerUtil.mutListener.listen(25683)) {
            AnalyticsTracker.track(AnalyticsTracker.Stat.OPENED_ACCOUNT_SETTINGS);
        }
        if (!ListenerUtil.mutListener.listen(25684)) {
            context.startActivity(intent);
        }
    }

    public static void viewAppSettingsForResult(Activity activity) {
        Intent intent = new Intent(activity, AppSettingsActivity.class);
        if (!ListenerUtil.mutListener.listen(25685)) {
            AnalyticsTracker.track(AnalyticsTracker.Stat.OPENED_APP_SETTINGS);
        }
        if (!ListenerUtil.mutListener.listen(25686)) {
            activity.startActivityForResult(intent, RequestCodes.APP_SETTINGS);
        }
    }

    public static void viewNotificationsSettings(Activity activity) {
        Intent intent = new Intent(activity, NotificationsSettingsActivity.class);
        if (!ListenerUtil.mutListener.listen(25687)) {
            activity.startActivity(intent);
        }
    }

    public static void viewJetpackSecuritySettings(Activity activity, SiteModel site) {
        if (!ListenerUtil.mutListener.listen(25688)) {
            AnalyticsTracker.track(Stat.SITE_SETTINGS_JETPACK_SECURITY_SETTINGS_VIEWED);
        }
        Intent intent = new Intent(activity, JetpackSecuritySettingsActivity.class);
        if (!ListenerUtil.mutListener.listen(25689)) {
            intent.putExtra(WordPress.SITE, site);
        }
        if (!ListenerUtil.mutListener.listen(25690)) {
            activity.startActivity(intent);
        }
    }

    public static void viewStories(Activity activity, SiteModel site, StorySaveResult event) {
        Intent intent = new Intent(activity, StoryComposerActivity.class);
        if (!ListenerUtil.mutListener.listen(25691)) {
            intent.putExtra(KEY_STORY_SAVE_RESULT, event);
        }
        if (!ListenerUtil.mutListener.listen(25692)) {
            intent.putExtra(WordPress.SITE, site);
        }
        if (!ListenerUtil.mutListener.listen(25693)) {
            // errored story but the error notification will remain existing in the system dashboard)
            intent.setAction(String.valueOf(FrameSaveNotifier.getNotificationIdForError(StoryComposerActivity.BASE_FRAME_MEDIA_ERROR_NOTIFICATION_ID, event.getStoryIndex())));
        }
        if (!ListenerUtil.mutListener.listen(25694)) {
            activity.startActivity(intent);
        }
    }

    public static void viewJetpackSecuritySettingsForResult(Activity activity, SiteModel site) {
        if (!ListenerUtil.mutListener.listen(25695)) {
            AnalyticsTracker.track(Stat.SITE_SETTINGS_JETPACK_SECURITY_SETTINGS_VIEWED);
        }
        Intent intent = new Intent(activity, JetpackSecuritySettingsActivity.class);
        if (!ListenerUtil.mutListener.listen(25696)) {
            intent.putExtra(WordPress.SITE, site);
        }
        if (!ListenerUtil.mutListener.listen(25697)) {
            activity.startActivityForResult(intent, JetpackSecuritySettingsActivity.JETPACK_SECURITY_SETTINGS_REQUEST_CODE);
        }
    }

    public static void viewHelpAndSupportInNewStack(@NonNull Context context, @NonNull Origin origin, @Nullable SiteModel selectedSite, @Nullable List<String> extraSupportTags) {
        Map<String, String> properties = new HashMap<>();
        if (!ListenerUtil.mutListener.listen(25698)) {
            properties.put("origin", origin.name());
        }
        if (!ListenerUtil.mutListener.listen(25699)) {
            AnalyticsTracker.track(Stat.SUPPORT_OPENED, properties);
        }
        TaskStackBuilder taskStackBuilder = TaskStackBuilder.create(context);
        Intent mainActivityIntent = getMainActivityInNewStack(context);
        if (!ListenerUtil.mutListener.listen(25700)) {
            mainActivityIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        }
        Intent meIntent = new Intent(context, MeActivity.class);
        if (!ListenerUtil.mutListener.listen(25701)) {
            meIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        }
        Intent helpIntent = HelpActivity.createIntent(context, origin, selectedSite, extraSupportTags);
        if (!ListenerUtil.mutListener.listen(25702)) {
            taskStackBuilder.addNextIntent(mainActivityIntent);
        }
        if (!ListenerUtil.mutListener.listen(25703)) {
            taskStackBuilder.addNextIntent(meIntent);
        }
        if (!ListenerUtil.mutListener.listen(25704)) {
            taskStackBuilder.addNextIntent(helpIntent);
        }
        if (!ListenerUtil.mutListener.listen(25705)) {
            taskStackBuilder.startActivities();
        }
    }

    public static void viewHelpAndSupport(@NonNull Context context, @NonNull Origin origin, @Nullable SiteModel selectedSite, @Nullable List<String> extraSupportTags) {
        Map<String, String> properties = new HashMap<>();
        if (!ListenerUtil.mutListener.listen(25706)) {
            properties.put("origin", origin.name());
        }
        if (!ListenerUtil.mutListener.listen(25707)) {
            AnalyticsTracker.track(Stat.SUPPORT_OPENED, properties);
        }
        if (!ListenerUtil.mutListener.listen(25708)) {
            context.startActivity(HelpActivity.createIntent(context, origin, selectedSite, extraSupportTags));
        }
    }

    public static void viewZendeskTickets(@NonNull Context context, @Nullable SiteModel selectedSite) {
        if (!ListenerUtil.mutListener.listen(25709)) {
            viewHelpAndSupportInNewStack(context, Origin.ZENDESK_NOTIFICATION, selectedSite, null);
        }
    }

    public static void viewSSLCerts(Context context, String certificateString) {
        Intent intent = new Intent(context, SSLCertsViewActivity.class);
        if (!ListenerUtil.mutListener.listen(25710)) {
            intent.putExtra(SSLCertsViewActivity.CERT_DETAILS_KEYS, certificateString.replaceAll("\n", "<br/>"));
        }
        if (!ListenerUtil.mutListener.listen(25711)) {
            context.startActivity(intent);
        }
    }

    public static void newBlogForResult(Activity activity, SiteCreationSource source) {
        Intent intent = new Intent(activity, SiteCreationActivity.class);
        if (!ListenerUtil.mutListener.listen(25712)) {
            intent.putExtra(SiteCreationActivity.ARG_CREATE_SITE_SOURCE, source.getLabel());
        }
        if (!ListenerUtil.mutListener.listen(25713)) {
            activity.startActivityForResult(intent, RequestCodes.CREATE_SITE);
        }
    }

    public static void showMainActivityAndSiteCreationActivity(Activity activity, SiteCreationSource source) {
        // WPMainActivity, we must start it this way.
        final Intent intent = createMainActivityAndSiteCreationActivityIntent(activity, null, source);
        if (!ListenerUtil.mutListener.listen(25714)) {
            activity.startActivity(intent);
        }
    }

    @NonNull
    public static Intent createMainActivityAndSiteCreationActivityIntent(Context context, @Nullable NotificationType notificationType, SiteCreationSource source) {
        final Intent intent = new Intent(context, WPMainActivity.class);
        if (!ListenerUtil.mutListener.listen(25715)) {
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        }
        if (!ListenerUtil.mutListener.listen(25716)) {
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        }
        if (!ListenerUtil.mutListener.listen(25717)) {
            intent.putExtra(WPMainActivity.ARG_SHOW_SITE_CREATION, true);
        }
        if (!ListenerUtil.mutListener.listen(25718)) {
            intent.putExtra(WPMainActivity.ARG_SITE_CREATION_SOURCE, source.getLabel());
        }
        if (!ListenerUtil.mutListener.listen(25720)) {
            if (notificationType != null) {
                if (!ListenerUtil.mutListener.listen(25719)) {
                    intent.putExtra(ARG_NOTIFICATION_TYPE, notificationType);
                }
            }
        }
        return intent;
    }

    @NonNull
    public static Intent createMainActivityAndShowBloggingPromptsOnboardingActivityIntent(final Context context, @Nullable final NotificationType notificationType, final int notificationId) {
        final Intent intent = new Intent(context, WPMainActivity.class);
        if (!ListenerUtil.mutListener.listen(25721)) {
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        }
        if (!ListenerUtil.mutListener.listen(25722)) {
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        }
        if (!ListenerUtil.mutListener.listen(25723)) {
            intent.putExtra(WPMainActivity.ARG_BLOGGING_PROMPTS_ONBOARDING, true);
        }
        if (!ListenerUtil.mutListener.listen(25724)) {
            intent.putExtra(WPMainActivity.ARG_DISMISS_NOTIFICATION, notificationId);
        }
        if (!ListenerUtil.mutListener.listen(25726)) {
            if (notificationType != null) {
                if (!ListenerUtil.mutListener.listen(25725)) {
                    intent.putExtra(ARG_NOTIFICATION_TYPE, notificationType);
                }
            }
        }
        return intent;
    }

    public static void showSignInForResult(Activity activity) {
        if (!ListenerUtil.mutListener.listen(25727)) {
            showSignInForResult(activity, false);
        }
    }

    public static void showSignInForResult(Activity activity, boolean clearTop) {
        Intent intent = new Intent(activity, LoginActivity.class);
        if (!ListenerUtil.mutListener.listen(25729)) {
            if (clearTop) {
                if (!ListenerUtil.mutListener.listen(25728)) {
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(25730)) {
            activity.startActivityForResult(intent, RequestCodes.ADD_ACCOUNT);
        }
    }

    public static void showSignInForResultWpComOnly(Activity activity) {
        Intent intent = new Intent(activity, LoginActivity.class);
        if (!ListenerUtil.mutListener.listen(25731)) {
            WPCOM_LOGIN_ONLY.putInto(intent);
        }
        if (!ListenerUtil.mutListener.listen(25732)) {
            activity.startActivityForResult(intent, RequestCodes.ADD_ACCOUNT);
        }
    }

    public static void showSignInForResultJetpackOnly(Activity activity) {
        Intent intent = new Intent(activity, LoginActivity.class);
        if (!ListenerUtil.mutListener.listen(25733)) {
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        }
        if (!ListenerUtil.mutListener.listen(25734)) {
            JETPACK_LOGIN_ONLY.putInto(intent);
        }
        if (!ListenerUtil.mutListener.listen(25735)) {
            activity.startActivityForResult(intent, RequestCodes.ADD_ACCOUNT);
        }
    }

    public static void showLoginEpilogue(Activity activity, boolean doLoginUpdate, ArrayList<Integer> oldSitesIds, boolean isSiteCreationEnabled) {
        Intent intent = new Intent(activity, LoginEpilogueActivity.class);
        if (!ListenerUtil.mutListener.listen(25736)) {
            intent.putExtra(LoginEpilogueActivity.EXTRA_DO_LOGIN_UPDATE, doLoginUpdate);
        }
        if (!ListenerUtil.mutListener.listen(25737)) {
            intent.putIntegerArrayListExtra(LoginEpilogueActivity.ARG_OLD_SITES_IDS, oldSitesIds);
        }
        if (!ListenerUtil.mutListener.listen(25740)) {
            if (isSiteCreationEnabled) {
                if (!ListenerUtil.mutListener.listen(25739)) {
                    activity.startActivityForResult(intent, RequestCodes.LOGIN_EPILOGUE);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(25738)) {
                    activity.startActivity(intent);
                }
            }
        }
    }

    public static void showLoginEpilogueForResult(Activity activity, ArrayList<Integer> oldSitesIds, boolean doLoginUpdate) {
        Intent intent = new Intent(activity, LoginEpilogueActivity.class);
        if (!ListenerUtil.mutListener.listen(25741)) {
            intent.putExtra(LoginEpilogueActivity.EXTRA_DO_LOGIN_UPDATE, doLoginUpdate);
        }
        if (!ListenerUtil.mutListener.listen(25742)) {
            intent.putExtra(LoginEpilogueActivity.EXTRA_SHOW_AND_RETURN, true);
        }
        if (!ListenerUtil.mutListener.listen(25743)) {
            intent.putIntegerArrayListExtra(LoginEpilogueActivity.ARG_OLD_SITES_IDS, oldSitesIds);
        }
        if (!ListenerUtil.mutListener.listen(25744)) {
            activity.startActivityForResult(intent, RequestCodes.SHOW_LOGIN_EPILOGUE_AND_RETURN);
        }
    }

    public static void showSignupEpilogue(Activity activity, String name, String email, String photoUrl, String username, boolean isEmail) {
        Intent intent = new Intent(activity, SignupEpilogueActivity.class);
        if (!ListenerUtil.mutListener.listen(25745)) {
            intent.putExtra(SignupEpilogueActivity.EXTRA_SIGNUP_DISPLAY_NAME, name);
        }
        if (!ListenerUtil.mutListener.listen(25746)) {
            intent.putExtra(SignupEpilogueActivity.EXTRA_SIGNUP_EMAIL_ADDRESS, email);
        }
        if (!ListenerUtil.mutListener.listen(25747)) {
            intent.putExtra(SignupEpilogueActivity.EXTRA_SIGNUP_PHOTO_URL, photoUrl);
        }
        if (!ListenerUtil.mutListener.listen(25748)) {
            intent.putExtra(SignupEpilogueActivity.EXTRA_SIGNUP_USERNAME, username);
        }
        if (!ListenerUtil.mutListener.listen(25749)) {
            intent.putExtra(SignupEpilogueActivity.EXTRA_SIGNUP_IS_EMAIL, isEmail);
        }
        if (!ListenerUtil.mutListener.listen(25750)) {
            activity.startActivity(intent);
        }
    }

    public static void showSignupEpilogueForResult(Activity activity, String name, String email, String photoUrl, String username, boolean isEmail) {
        Intent intent = new Intent(activity, SignupEpilogueActivity.class);
        if (!ListenerUtil.mutListener.listen(25751)) {
            intent.putExtra(SignupEpilogueActivity.EXTRA_SIGNUP_DISPLAY_NAME, name);
        }
        if (!ListenerUtil.mutListener.listen(25752)) {
            intent.putExtra(SignupEpilogueActivity.EXTRA_SIGNUP_EMAIL_ADDRESS, email);
        }
        if (!ListenerUtil.mutListener.listen(25753)) {
            intent.putExtra(SignupEpilogueActivity.EXTRA_SIGNUP_PHOTO_URL, photoUrl);
        }
        if (!ListenerUtil.mutListener.listen(25754)) {
            intent.putExtra(SignupEpilogueActivity.EXTRA_SIGNUP_USERNAME, username);
        }
        if (!ListenerUtil.mutListener.listen(25755)) {
            intent.putExtra(SignupEpilogueActivity.EXTRA_SIGNUP_IS_EMAIL, isEmail);
        }
        if (!ListenerUtil.mutListener.listen(25756)) {
            activity.startActivityForResult(intent, RequestCodes.SHOW_SIGNUP_EPILOGUE_AND_RETURN);
        }
    }

    public static void showPostSignupInterstitial(Context context) {
        final Intent parentIntent = new Intent(context, WPMainActivity.class);
        if (!ListenerUtil.mutListener.listen(25757)) {
            parentIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        }
        if (!ListenerUtil.mutListener.listen(25758)) {
            parentIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        }
        final Intent intent = new Intent(context, PostSignupInterstitialActivity.class);
        if (!ListenerUtil.mutListener.listen(25759)) {
            TaskStackBuilder.create(context).addNextIntent(parentIntent).addNextIntent(intent).startActivities();
        }
    }

    public static void viewStatsSinglePostDetails(Context context, SiteModel site, PostModel post) {
        if (!ListenerUtil.mutListener.listen(25761)) {
            if ((ListenerUtil.mutListener.listen(25760) ? (post == null && site == null) : (post == null || site == null))) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(25762)) {
            StatsDetailActivity.Companion.start(context, site, post.getRemotePostId(), StatsConstants.ITEM_TYPE_POST, post.getTitle(), post.getLink());
        }
    }

    public static void viewInsightsDetail(Context context, StatsSection statsSection, StatsViewType statsViewType, StatsGranularity granularity, SelectedDate selectedDate, int localSiteId) {
        if (!ListenerUtil.mutListener.listen(25763)) {
            StatsDetailActivity.startForInsightsDetail(context, statsSection, statsViewType, granularity, selectedDate, localSiteId);
        }
    }

    public static void showSetBloggingReminders(Context context, SiteModel site) {
        final Intent intent = getMainActivityInNewStack(context);
        if (!ListenerUtil.mutListener.listen(25764)) {
            intent.putExtra(WPMainActivity.ARG_OPEN_BLOGGING_REMINDERS, true);
        }
        if (!ListenerUtil.mutListener.listen(25765)) {
            intent.putExtra(WPMainActivity.ARG_SELECTED_SITE, site.getId());
        }
        if (!ListenerUtil.mutListener.listen(25766)) {
            context.startActivity(intent);
        }
    }

    public static void showSchedulingPost(Context context, SiteModel site) {
        Intent intent = PostsListActivity.buildIntent(context, site, PostListType.DRAFTS, false, null);
        if (!ListenerUtil.mutListener.listen(25767)) {
            context.startActivity(intent);
        }
    }

    public static void viewMediaPickerForResult(Activity activity, @NonNull SiteModel site, @NonNull MediaBrowserType browserType) {
        Intent intent = new Intent(activity, MediaBrowserActivity.class);
        if (!ListenerUtil.mutListener.listen(25768)) {
            intent.putExtra(WordPress.SITE, site);
        }
        if (!ListenerUtil.mutListener.listen(25769)) {
            intent.putExtra(ARG_BROWSER_TYPE, browserType);
        }
        int requestCode;
        if (browserType.canMultiselect()) {
            requestCode = RequestCodes.MULTI_SELECT_MEDIA_PICKER;
        } else {
            requestCode = RequestCodes.SINGLE_SELECT_MEDIA_PICKER;
        }
        if (!ListenerUtil.mutListener.listen(25770)) {
            activity.startActivityForResult(intent, requestCode);
        }
    }

    public static void viewSuggestionsForResult(@NonNull Activity activity, @NonNull SiteModel site, @NonNull SuggestionType type) {
        Intent intent = new Intent(activity, SuggestionActivity.class);
        if (!ListenerUtil.mutListener.listen(25771)) {
            intent.putExtra(SuggestionActivity.INTENT_KEY_SITE_MODEL, site);
        }
        if (!ListenerUtil.mutListener.listen(25772)) {
            intent.putExtra(SuggestionActivity.INTENT_KEY_SUGGESTION_TYPE, type);
        }
        if (!ListenerUtil.mutListener.listen(25773)) {
            activity.startActivityForResult(intent, RequestCodes.SELECTED_USER_MENTION);
        }
    }

    public static void addSelfHostedSiteForResult(Activity activity) {
        Intent intent;
        intent = new Intent(activity, LoginActivity.class);
        if (!ListenerUtil.mutListener.listen(25774)) {
            LoginMode.SELFHOSTED_ONLY.putInto(intent);
        }
        if (!ListenerUtil.mutListener.listen(25775)) {
            activity.startActivityForResult(intent, RequestCodes.ADD_ACCOUNT);
        }
    }

    public static void loginForDeeplink(Activity activity) {
        Intent intent;
        intent = new Intent(activity, LoginActivity.class);
        if (!ListenerUtil.mutListener.listen(25776)) {
            LoginMode.WPCOM_LOGIN_DEEPLINK.putInto(intent);
        }
        if (!ListenerUtil.mutListener.listen(25777)) {
            activity.startActivityForResult(intent, RequestCodes.DO_LOGIN);
        }
    }

    public static void loginForShareIntent(Activity activity) {
        Intent intent = new Intent(activity, LoginActivity.class);
        if (!ListenerUtil.mutListener.listen(25778)) {
            LoginMode.SHARE_INTENT.putInto(intent);
        }
        if (!ListenerUtil.mutListener.listen(25779)) {
            activity.startActivityForResult(intent, RequestCodes.DO_LOGIN);
        }
    }

    public static void loginWithoutMagicLink(Activity activity) {
        Intent intent;
        intent = new Intent(activity, LoginActivity.class);
        if (!ListenerUtil.mutListener.listen(25780)) {
            LoginMode.WPCOM_LOGIN_DEEPLINK.putInto(intent);
        }
        if (!ListenerUtil.mutListener.listen(25781)) {
            activity.startActivityForResult(intent, RequestCodes.DO_LOGIN);
        }
    }

    public static void loginForJetpackStats(Fragment fragment) {
        Intent intent = new Intent(fragment.getActivity(), LoginActivity.class);
        if (!ListenerUtil.mutListener.listen(25782)) {
            LoginMode.JETPACK_STATS.putInto(intent);
        }
        if (!ListenerUtil.mutListener.listen(25783)) {
            fragment.startActivityForResult(intent, RequestCodes.DO_LOGIN);
        }
    }

    /*
     * open the passed url in the device's external browser
     */
    public static void openUrlExternal(Context context, @NonNull String url) {
        Uri uri = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        if (!ListenerUtil.mutListener.listen(25784)) {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        try {
            if (!ListenerUtil.mutListener.listen(25792)) {
                // We'll re-enable them later - see callers of WPActivityUtils#enableReaderDeeplinks.
                WPActivityUtils.disableReaderDeeplinks(context);
            }
            if (!ListenerUtil.mutListener.listen(25793)) {
                context.startActivity(intent);
            }
        } catch (ActivityNotFoundException e) {
            if (!ListenerUtil.mutListener.listen(25785)) {
                ToastUtils.showToast(context, context.getString(R.string.cant_open_url), ToastUtils.Duration.LONG);
            }
            if (!ListenerUtil.mutListener.listen(25786)) {
                AppLog.e(AppLog.T.UTILS, "No default app available on the device to open the link: " + url, e);
            }
        } catch (SecurityException se) {
            if (!ListenerUtil.mutListener.listen(25787)) {
                AppLog.e(AppLog.T.UTILS, "Error opening url in default browser. Url: " + url, se);
            }
            List<ResolveInfo> infos = context.getPackageManager().queryIntentActivities(intent, 0);
            if (!ListenerUtil.mutListener.listen(25791)) {
                if (infos.size() == 1) {
                    if (!ListenerUtil.mutListener.listen(25789)) {
                        // there's only one handler and apparently it caused the exception so, just inform and bail
                        AppLog.d(AppLog.T.UTILS, "Only one url handler found so, bailing.");
                    }
                    if (!ListenerUtil.mutListener.listen(25790)) {
                        ToastUtils.showToast(context, context.getString(R.string.cant_open_url));
                    }
                } else {
                    Intent chooser = Intent.createChooser(intent, context.getString(R.string.error_please_choose_browser));
                    if (!ListenerUtil.mutListener.listen(25788)) {
                        context.startActivity(chooser);
                    }
                }
            }
        }
    }

    public static void openStatsUrl(Context context, @NonNull String url) {
        if (!ListenerUtil.mutListener.listen(25804)) {
            if ((ListenerUtil.mutListener.listen(25794) ? (url.startsWith("https://wordpress.com/my-stats") && url.startsWith("http://wordpress.com/my-stats")) : (url.startsWith("https://wordpress.com/my-stats") || url.startsWith("http://wordpress.com/my-stats")))) {
                if (!ListenerUtil.mutListener.listen(25797)) {
                    // make sure to load the no-chrome version of Stats over https
                    url = UrlUtils.makeHttps(url);
                }
                if (!ListenerUtil.mutListener.listen(25802)) {
                    if (url.contains("?")) {
                        if (!ListenerUtil.mutListener.listen(25801)) {
                            // add the no chrome parameters if not available
                            if ((ListenerUtil.mutListener.listen(25799) ? (!url.contains("?no-chrome") || !url.contains("&no-chrome")) : (!url.contains("?no-chrome") && !url.contains("&no-chrome")))) {
                                if (!ListenerUtil.mutListener.listen(25800)) {
                                    url += "&no-chrome";
                                }
                            }
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(25798)) {
                            url += "?no-chrome";
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(25803)) {
                    WPWebViewActivity.openUrlByUsingGlobalWPCOMCredentials(context, url);
                }
            } else if ((ListenerUtil.mutListener.listen(25795) ? (url.startsWith("https") && url.startsWith("http")) : (url.startsWith("https") || url.startsWith("http")))) {
                if (!ListenerUtil.mutListener.listen(25796)) {
                    WPWebViewActivity.openURL(context, url);
                }
            }
        }
    }

    public static void openImageEditor(Activity activity, ArrayList<EditImageData.InputData> input) {
        Intent intent = new Intent(activity, EditImageActivity.class);
        if (!ListenerUtil.mutListener.listen(25805)) {
            intent.putParcelableArrayListExtra(ARG_EDIT_IMAGE_DATA, input);
        }
        if (!ListenerUtil.mutListener.listen(25806)) {
            activity.startActivityForResult(intent, RequestCodes.IMAGE_EDITOR_EDIT_IMAGE);
        }
    }

    public static void viewPagesInNewStack(Context context, SiteModel site) {
        if (!ListenerUtil.mutListener.listen(25808)) {
            if (site == null) {
                if (!ListenerUtil.mutListener.listen(25807)) {
                    ToastUtils.showToast(context, R.string.pages_cannot_be_started, ToastUtils.Duration.SHORT);
                }
                return;
            }
        }
        TaskStackBuilder taskStackBuilder = TaskStackBuilder.create(context);
        Intent mainActivityIntent = getMainActivityInNewStack(context);
        Intent pagesIntent = new Intent(context, PagesActivity.class);
        if (!ListenerUtil.mutListener.listen(25809)) {
            pagesIntent.putExtra(WordPress.SITE, site);
        }
        if (!ListenerUtil.mutListener.listen(25810)) {
            taskStackBuilder.addNextIntent(mainActivityIntent);
        }
        if (!ListenerUtil.mutListener.listen(25811)) {
            taskStackBuilder.addNextIntent(pagesIntent);
        }
        if (!ListenerUtil.mutListener.listen(25812)) {
            taskStackBuilder.startActivities();
        }
    }

    public static void viewPagesInNewStack(Context context) {
        Intent intent = getMainActivityInNewStack(context);
        if (!ListenerUtil.mutListener.listen(25813)) {
            intent.putExtra(WPMainActivity.ARG_OPEN_PAGE, WPMainActivity.ARG_PAGES);
        }
        if (!ListenerUtil.mutListener.listen(25814)) {
            context.startActivity(intent);
        }
    }

    public static void showBackupDownloadForResult(Activity activity, @NonNull SiteModel site, String activityId, int resultCode, String source) {
        Map<String, String> properties = new HashMap<>();
        if (!ListenerUtil.mutListener.listen(25815)) {
            properties.put(SOURCE_TRACK_EVENT_PROPERTY_KEY, source);
        }
        if (!ListenerUtil.mutListener.listen(25816)) {
            AnalyticsTracker.track(Stat.JETPACK_BACKUP_DOWNLOAD_OPENED, properties);
        }
        Intent intent = new Intent(activity, BackupDownloadActivity.class);
        if (!ListenerUtil.mutListener.listen(25817)) {
            intent.putExtra(WordPress.SITE, site);
        }
        if (!ListenerUtil.mutListener.listen(25818)) {
            intent.putExtra(KEY_BACKUP_DOWNLOAD_ACTIVITY_ID_KEY, activityId);
        }
        if (!ListenerUtil.mutListener.listen(25819)) {
            activity.startActivityForResult(intent, resultCode);
        }
    }

    public static void shareBackupDownloadFileLink(Context context, String url) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        if (!ListenerUtil.mutListener.listen(25820)) {
            intent.setType("text/plain");
        }
        if (!ListenerUtil.mutListener.listen(25821)) {
            intent.putExtra(Intent.EXTRA_TEXT, url);
        }
        if (!ListenerUtil.mutListener.listen(25822)) {
            context.startActivity(Intent.createChooser(intent, context.getString(R.string.share_link)));
        }
    }

    public static void downloadBackupDownloadFile(Context context, String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        if (!ListenerUtil.mutListener.listen(25823)) {
            context.startActivity(intent);
        }
    }

    public static void showRestoreForResult(Activity activity, @NonNull SiteModel site, String activityId, int resultCode, String source) {
        Map<String, String> properties = new HashMap<>();
        if (!ListenerUtil.mutListener.listen(25824)) {
            properties.put(SOURCE_TRACK_EVENT_PROPERTY_KEY, source);
        }
        if (!ListenerUtil.mutListener.listen(25825)) {
            AnalyticsTracker.track(Stat.JETPACK_RESTORE_OPENED, properties);
        }
        Intent intent = new Intent(activity, RestoreActivity.class);
        if (!ListenerUtil.mutListener.listen(25826)) {
            intent.putExtra(WordPress.SITE, site);
        }
        if (!ListenerUtil.mutListener.listen(25827)) {
            intent.putExtra(KEY_RESTORE_ACTIVITY_ID_KEY, activityId);
        }
        if (!ListenerUtil.mutListener.listen(25828)) {
            activity.startActivityForResult(intent, resultCode);
        }
    }

    public static void showCategoriesList(@NonNull Context context, @NonNull SiteModel site) {
        Intent intent = new Intent(context, CategoriesListActivity.class);
        if (!ListenerUtil.mutListener.listen(25829)) {
            intent.putExtra(WordPress.SITE, site);
        }
        if (!ListenerUtil.mutListener.listen(25830)) {
            context.startActivity(intent);
        }
    }

    public static void showCategoryDetail(@NonNull Context context, @Nullable Long categoryId) {
        Intent intent = new Intent(context, CategoryDetailActivity.class);
        if (!ListenerUtil.mutListener.listen(25831)) {
            intent.putExtra(CATEGORY_DETAIL_ID, categoryId);
        }
        if (!ListenerUtil.mutListener.listen(25832)) {
            context.startActivity(intent);
        }
    }

    public static void viewDebugCookies(@NonNull Context context) {
        if (!ListenerUtil.mutListener.listen(25833)) {
            context.startActivity(new Intent(context, DebugCookiesActivity.class));
        }
    }

    public static void startQRCodeAuthFlow(@NonNull Context context) {
        if (!ListenerUtil.mutListener.listen(25834)) {
            QRCodeAuthActivity.start(context);
        }
    }

    public static void startQRCodeAuthFlowInNewStack(@NonNull Context context, @NonNull String uri) {
        TaskStackBuilder taskStackBuilder = TaskStackBuilder.create(context);
        Intent mainActivityIntent = getMainActivityInNewStack(context);
        if (!ListenerUtil.mutListener.listen(25835)) {
            mainActivityIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        }
        Intent meIntent = new Intent(context, MeActivity.class);
        if (!ListenerUtil.mutListener.listen(25836)) {
            meIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        }
        Intent qrcodeAuthFlowIntent = QRCodeAuthActivity.newIntent(context, uri, true);
        if (!ListenerUtil.mutListener.listen(25837)) {
            taskStackBuilder.addNextIntent(mainActivityIntent);
        }
        if (!ListenerUtil.mutListener.listen(25838)) {
            taskStackBuilder.addNextIntent(meIntent);
        }
        if (!ListenerUtil.mutListener.listen(25839)) {
            taskStackBuilder.addNextIntent(qrcodeAuthFlowIntent);
        }
        if (!ListenerUtil.mutListener.listen(25840)) {
            taskStackBuilder.startActivities();
        }
    }
}
