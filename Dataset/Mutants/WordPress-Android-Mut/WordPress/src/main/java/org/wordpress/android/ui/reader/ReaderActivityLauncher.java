package org.wordpress.android.ui.reader;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.app.ActivityOptionsCompat;
import androidx.fragment.app.Fragment;
import org.jetbrains.annotations.NotNull;
import org.wordpress.android.R;
import org.wordpress.android.analytics.AnalyticsTracker;
import org.wordpress.android.models.ReaderPost;
import org.wordpress.android.models.ReaderTag;
import org.wordpress.android.ui.ActivityLauncher;
import org.wordpress.android.ui.RequestCodes;
import org.wordpress.android.ui.WPWebViewActivity;
import org.wordpress.android.ui.reader.ReaderPostPagerActivity.DirectOperation;
import org.wordpress.android.ui.reader.ReaderTypes.ReaderPostListType;
import org.wordpress.android.ui.reader.discover.interests.ReaderInterestsActivity;
import org.wordpress.android.ui.reader.discover.interests.ReaderInterestsFragment.EntryPoint;
import org.wordpress.android.ui.reader.tracker.ReaderTracker;
import org.wordpress.android.ui.reader.utils.ReaderUtils;
import org.wordpress.android.util.WPUrlUtils;
import java.util.EnumSet;
import static org.wordpress.android.ui.reader.discover.interests.ReaderInterestsFragment.READER_INTEREST_ENTRY_POINT;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class ReaderActivityLauncher {

    /*
     * show a single reader post in the detail view - simply calls showReaderPostPager
     * with a single post
     */
    public static void showReaderPostDetail(Context context, long blogId, long postId) {
        if (!ListenerUtil.mutListener.listen(20343)) {
            showReaderPostDetail(context, false, blogId, postId, null, 0, false, null);
        }
    }

    public static void showReaderPostDetail(Context context, boolean isFeed, long blogId, long postId, DirectOperation directOperation, int commentId, boolean isRelatedPost, String interceptedUri) {
        Intent intent = buildReaderPostDetailIntent(context, isFeed, blogId, postId, directOperation, commentId, isRelatedPost, interceptedUri);
        if (!ListenerUtil.mutListener.listen(20344)) {
            context.startActivity(intent);
        }
    }

    @NotNull
    public static Intent buildReaderPostDetailIntent(Context context, boolean isFeed, long blogId, long postId, DirectOperation directOperation, int commentId, boolean isRelatedPost, String interceptedUri) {
        Intent intent = new Intent(context, ReaderPostPagerActivity.class);
        if (!ListenerUtil.mutListener.listen(20345)) {
            intent.putExtra(ReaderConstants.ARG_IS_FEED, isFeed);
        }
        if (!ListenerUtil.mutListener.listen(20346)) {
            intent.putExtra(ReaderConstants.ARG_BLOG_ID, blogId);
        }
        if (!ListenerUtil.mutListener.listen(20347)) {
            intent.putExtra(ReaderConstants.ARG_POST_ID, postId);
        }
        if (!ListenerUtil.mutListener.listen(20348)) {
            intent.putExtra(ReaderConstants.ARG_DIRECT_OPERATION, directOperation);
        }
        if (!ListenerUtil.mutListener.listen(20349)) {
            intent.putExtra(ReaderConstants.ARG_COMMENT_ID, commentId);
        }
        if (!ListenerUtil.mutListener.listen(20350)) {
            intent.putExtra(ReaderConstants.ARG_IS_SINGLE_POST, true);
        }
        if (!ListenerUtil.mutListener.listen(20351)) {
            intent.putExtra(ReaderConstants.ARG_IS_RELATED_POST, isRelatedPost);
        }
        if (!ListenerUtil.mutListener.listen(20352)) {
            intent.putExtra(ReaderConstants.ARG_INTERCEPTED_URI, interceptedUri);
        }
        return intent;
    }

    /*
     * show pager view of posts with a specific tag - passed blogId/postId is the post
     * to select after the pager is populated
     */
    public static void showReaderPostPagerForTag(Context context, ReaderTag tag, ReaderPostListType postListType, long blogId, long postId) {
        if (!ListenerUtil.mutListener.listen(20353)) {
            if (tag == null) {
                return;
            }
        }
        Intent intent = new Intent(context, ReaderPostPagerActivity.class);
        if (!ListenerUtil.mutListener.listen(20354)) {
            intent.putExtra(ReaderConstants.ARG_POST_LIST_TYPE, postListType);
        }
        if (!ListenerUtil.mutListener.listen(20355)) {
            intent.putExtra(ReaderConstants.ARG_TAG, tag);
        }
        if (!ListenerUtil.mutListener.listen(20356)) {
            intent.putExtra(ReaderConstants.ARG_BLOG_ID, blogId);
        }
        if (!ListenerUtil.mutListener.listen(20357)) {
            intent.putExtra(ReaderConstants.ARG_POST_ID, postId);
        }
        if (!ListenerUtil.mutListener.listen(20358)) {
            context.startActivity(intent);
        }
    }

    /*
     * show pager view of posts in a specific blog
     */
    public static void showReaderPostPagerForBlog(Context context, long blogId, long postId) {
        Intent intent = new Intent(context, ReaderPostPagerActivity.class);
        if (!ListenerUtil.mutListener.listen(20359)) {
            intent.putExtra(ReaderConstants.ARG_POST_LIST_TYPE, ReaderPostListType.BLOG_PREVIEW);
        }
        if (!ListenerUtil.mutListener.listen(20360)) {
            intent.putExtra(ReaderConstants.ARG_BLOG_ID, blogId);
        }
        if (!ListenerUtil.mutListener.listen(20361)) {
            intent.putExtra(ReaderConstants.ARG_POST_ID, postId);
        }
        if (!ListenerUtil.mutListener.listen(20362)) {
            context.startActivity(intent);
        }
    }

    /*
     * show a list of posts in a specific blog or feed
     */
    public static void showReaderBlogOrFeedPreview(Context context, long siteId, long feedId, @Nullable Boolean isFollowed, String source, ReaderTracker readerTracker) {
        if (!ListenerUtil.mutListener.listen(20374)) {
            if ((ListenerUtil.mutListener.listen(20373) ? ((ListenerUtil.mutListener.listen(20367) ? (siteId >= 0) : (ListenerUtil.mutListener.listen(20366) ? (siteId <= 0) : (ListenerUtil.mutListener.listen(20365) ? (siteId > 0) : (ListenerUtil.mutListener.listen(20364) ? (siteId < 0) : (ListenerUtil.mutListener.listen(20363) ? (siteId != 0) : (siteId == 0)))))) || (ListenerUtil.mutListener.listen(20372) ? (feedId >= 0) : (ListenerUtil.mutListener.listen(20371) ? (feedId <= 0) : (ListenerUtil.mutListener.listen(20370) ? (feedId > 0) : (ListenerUtil.mutListener.listen(20369) ? (feedId < 0) : (ListenerUtil.mutListener.listen(20368) ? (feedId != 0) : (feedId == 0))))))) : ((ListenerUtil.mutListener.listen(20367) ? (siteId >= 0) : (ListenerUtil.mutListener.listen(20366) ? (siteId <= 0) : (ListenerUtil.mutListener.listen(20365) ? (siteId > 0) : (ListenerUtil.mutListener.listen(20364) ? (siteId < 0) : (ListenerUtil.mutListener.listen(20363) ? (siteId != 0) : (siteId == 0)))))) && (ListenerUtil.mutListener.listen(20372) ? (feedId >= 0) : (ListenerUtil.mutListener.listen(20371) ? (feedId <= 0) : (ListenerUtil.mutListener.listen(20370) ? (feedId > 0) : (ListenerUtil.mutListener.listen(20369) ? (feedId < 0) : (ListenerUtil.mutListener.listen(20368) ? (feedId != 0) : (feedId == 0))))))))) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(20375)) {
            readerTracker.trackBlog(AnalyticsTracker.Stat.READER_BLOG_PREVIEWED, siteId, feedId, isFollowed, source);
        }
        Intent intent = new Intent(context, ReaderPostListActivity.class);
        if (!ListenerUtil.mutListener.listen(20376)) {
            intent.putExtra(ReaderConstants.ARG_SOURCE, source);
        }
        if (!ListenerUtil.mutListener.listen(20380)) {
            if (ReaderUtils.isExternalFeed(siteId, feedId)) {
                if (!ListenerUtil.mutListener.listen(20378)) {
                    intent.putExtra(ReaderConstants.ARG_FEED_ID, feedId);
                }
                if (!ListenerUtil.mutListener.listen(20379)) {
                    intent.putExtra(ReaderConstants.ARG_IS_FEED, true);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(20377)) {
                    intent.putExtra(ReaderConstants.ARG_BLOG_ID, siteId);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(20381)) {
            intent.putExtra(ReaderConstants.ARG_POST_LIST_TYPE, ReaderPostListType.BLOG_PREVIEW);
        }
        if (!ListenerUtil.mutListener.listen(20382)) {
            context.startActivity(intent);
        }
    }

    public static void showReaderBlogPreview(Context context, ReaderPost post, String source, ReaderTracker readerTracker) {
        if (!ListenerUtil.mutListener.listen(20383)) {
            if (post == null) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(20384)) {
            showReaderBlogOrFeedPreview(context, post.blogId, post.feedId, post.isFollowedByCurrentUser, source, readerTracker);
        }
    }

    public static void showReaderBlogPreview(Context context, long siteId, @Nullable Boolean isFollowed, String source, ReaderTracker readerTracker) {
        if (!ListenerUtil.mutListener.listen(20385)) {
            showReaderBlogOrFeedPreview(context, siteId, 0, isFollowed, source, readerTracker);
        }
    }

    /*
     * show a list of posts with a specific tag
     */
    public static void showReaderTagPreview(Context context, @NonNull ReaderTag tag, String source, ReaderTracker readerTracker) {
        if (!ListenerUtil.mutListener.listen(20386)) {
            readerTracker.trackTag(AnalyticsTracker.Stat.READER_TAG_PREVIEWED, tag.getTagSlug(), source);
        }
        Intent intent = new Intent(context, ReaderPostListActivity.class);
        if (!ListenerUtil.mutListener.listen(20387)) {
            intent.putExtra(ReaderConstants.ARG_SOURCE, source);
        }
        if (!ListenerUtil.mutListener.listen(20388)) {
            intent.putExtra(ReaderConstants.ARG_TAG, tag);
        }
        if (!ListenerUtil.mutListener.listen(20389)) {
            intent.putExtra(ReaderConstants.ARG_POST_LIST_TYPE, ReaderPostListType.TAG_PREVIEW);
        }
        if (!ListenerUtil.mutListener.listen(20390)) {
            context.startActivity(intent);
        }
    }

    public static void showReaderSearch(Context context) {
        Intent intent = new Intent(context, ReaderSearchActivity.class);
        if (!ListenerUtil.mutListener.listen(20391)) {
            context.startActivity(intent);
        }
    }

    /*
     * show comments for the passed Ids
     */
    public static void showReaderComments(Context context, long blogId, long postId, String source) {
        if (!ListenerUtil.mutListener.listen(20392)) {
            showReaderComments(context, blogId, postId, null, 0, null, source);
        }
    }

    /*
     * show specific comment for the passed Ids
     */
    public static void showReaderComments(Context context, long blogId, long postId, long commentId, String source) {
        if (!ListenerUtil.mutListener.listen(20393)) {
            showReaderComments(context, blogId, postId, DirectOperation.COMMENT_JUMP, commentId, null, source);
        }
    }

    /**
     * Show comments for passed Ids and directly perform an action on a specifc comment
     *
     * @param context         context to use to start the activity
     * @param blogId          blog id
     * @param postId          post id
     * @param directOperation operation to perform on the specific comment. Can be null for no operation.
     * @param commentId       specific comment id to perform an action on
     * @param interceptedUri  URI to fall back into (i.e. to be able to open in external browser)
     */
    public static void showReaderComments(Context context, long blogId, long postId, DirectOperation directOperation, long commentId, String interceptedUri, String source) {
        Intent intent = buildShowReaderCommentsIntent(context, blogId, postId, directOperation, commentId, interceptedUri, source);
        if (!ListenerUtil.mutListener.listen(20394)) {
            context.startActivity(intent);
        }
    }

    public static void showReaderCommentsForResult(Fragment fragment, long blogId, long postId, String source) {
        if (!ListenerUtil.mutListener.listen(20395)) {
            showReaderCommentsForResult(fragment, blogId, postId, null, 0, null, source);
        }
    }

    public static void showReaderCommentsForResult(Fragment fragment, long blogId, long postId, DirectOperation directOperation, long commentId, String interceptedUri, String source) {
        Intent intent = buildShowReaderCommentsIntent(fragment.getContext(), blogId, postId, directOperation, commentId, interceptedUri, source);
        if (!ListenerUtil.mutListener.listen(20396)) {
            fragment.startActivityForResult(intent, RequestCodes.READER_FOLLOW_CONVERSATION);
        }
    }

    private static Intent buildShowReaderCommentsIntent(Context context, long blogId, long postId, DirectOperation directOperation, long commentId, String interceptedUri, String source) {
        Intent intent = new Intent(context, ReaderCommentListActivity.class);
        if (!ListenerUtil.mutListener.listen(20397)) {
            intent.putExtra(ReaderConstants.ARG_BLOG_ID, blogId);
        }
        if (!ListenerUtil.mutListener.listen(20398)) {
            intent.putExtra(ReaderConstants.ARG_POST_ID, postId);
        }
        if (!ListenerUtil.mutListener.listen(20399)) {
            intent.putExtra(ReaderConstants.ARG_DIRECT_OPERATION, directOperation);
        }
        if (!ListenerUtil.mutListener.listen(20400)) {
            intent.putExtra(ReaderConstants.ARG_COMMENT_ID, commentId);
        }
        if (!ListenerUtil.mutListener.listen(20401)) {
            intent.putExtra(ReaderConstants.ARG_INTERCEPTED_URI, interceptedUri);
        }
        if (!ListenerUtil.mutListener.listen(20402)) {
            intent.putExtra(ReaderConstants.ARG_SOURCE, source);
        }
        return intent;
    }

    /*
     * show users who liked a post
     */
    public static void showReaderLikingUsers(Context context, long blogId, long postId) {
        Intent intent = new Intent(context, ReaderUserListActivity.class);
        if (!ListenerUtil.mutListener.listen(20403)) {
            intent.putExtra(ReaderConstants.ARG_BLOG_ID, blogId);
        }
        if (!ListenerUtil.mutListener.listen(20404)) {
            intent.putExtra(ReaderConstants.ARG_POST_ID, postId);
        }
        if (!ListenerUtil.mutListener.listen(20405)) {
            context.startActivity(intent);
        }
    }

    /**
     * Presents the [ReaderPostNoSiteToReblog] activity
     *
     * @param activity the calling activity
     */
    public static void showNoSiteToReblog(Activity activity) {
        Intent intent = new Intent(activity, NoSiteToReblogActivity.class);
        if (!ListenerUtil.mutListener.listen(20406)) {
            activity.startActivityForResult(intent, RequestCodes.NO_REBLOG_SITE);
        }
    }

    /*
     * show followed tags & blogs
     */
    public static void showReaderSubs(Context context) {
        Intent intent = new Intent(context, ReaderSubsActivity.class);
        if (!ListenerUtil.mutListener.listen(20407)) {
            context.startActivity(intent);
        }
    }

    public static void showReaderSubs(Context context, int selectPosition) {
        Intent intent = new Intent(context, ReaderSubsActivity.class);
        if (!ListenerUtil.mutListener.listen(20408)) {
            intent.putExtra(ReaderConstants.ARG_SUBS_TAB_POSITION, selectPosition);
        }
        if (!ListenerUtil.mutListener.listen(20409)) {
            context.startActivity(intent);
        }
    }

    public static void showReaderInterests(Activity activity) {
        Intent intent = new Intent(activity, ReaderInterestsActivity.class);
        if (!ListenerUtil.mutListener.listen(20410)) {
            intent.putExtra(READER_INTEREST_ENTRY_POINT, EntryPoint.SETTINGS);
        }
        if (!ListenerUtil.mutListener.listen(20411)) {
            activity.startActivityForResult(intent, RequestCodes.READER_INTERESTS);
        }
    }

    /*
     * play an external video
     */
    public static void showReaderVideoViewer(Context context, String videoUrl) {
        if (!ListenerUtil.mutListener.listen(20413)) {
            if ((ListenerUtil.mutListener.listen(20412) ? (context == null && TextUtils.isEmpty(videoUrl)) : (context == null || TextUtils.isEmpty(videoUrl)))) {
                return;
            }
        }
        Intent intent = new Intent(context, ReaderVideoViewerActivity.class);
        if (!ListenerUtil.mutListener.listen(20414)) {
            intent.putExtra(ReaderConstants.ARG_VIDEO_URL, videoUrl);
        }
        if (!ListenerUtil.mutListener.listen(20415)) {
            context.startActivity(intent);
        }
    }

    /*
     * show the passed imageUrl in the fullscreen photo activity - optional content is the
     * content of the post the image is in, used by the activity to show all images in
     * the post
     */
    public enum PhotoViewerOption {

        IS_PRIVATE_IMAGE, IS_GALLERY_IMAGE
    }

    public static void showReaderPhotoViewer(Context context, String imageUrl, String content, View sourceView, EnumSet<PhotoViewerOption> imageOptions, int startX, int startY) {
        if (!ListenerUtil.mutListener.listen(20417)) {
            if ((ListenerUtil.mutListener.listen(20416) ? (context == null && TextUtils.isEmpty(imageUrl)) : (context == null || TextUtils.isEmpty(imageUrl)))) {
                return;
            }
        }
        boolean isPrivate = (ListenerUtil.mutListener.listen(20418) ? (imageOptions != null || imageOptions.contains(PhotoViewerOption.IS_PRIVATE_IMAGE)) : (imageOptions != null && imageOptions.contains(PhotoViewerOption.IS_PRIVATE_IMAGE)));
        boolean isGallery = (ListenerUtil.mutListener.listen(20419) ? (imageOptions != null || imageOptions.contains(PhotoViewerOption.IS_GALLERY_IMAGE)) : (imageOptions != null && imageOptions.contains(PhotoViewerOption.IS_GALLERY_IMAGE)));
        Intent intent = new Intent(context, ReaderPhotoViewerActivity.class);
        if (!ListenerUtil.mutListener.listen(20420)) {
            intent.putExtra(ReaderConstants.ARG_IMAGE_URL, imageUrl);
        }
        if (!ListenerUtil.mutListener.listen(20421)) {
            intent.putExtra(ReaderConstants.ARG_IS_PRIVATE, isPrivate);
        }
        if (!ListenerUtil.mutListener.listen(20422)) {
            intent.putExtra(ReaderConstants.ARG_IS_GALLERY, isGallery);
        }
        if (!ListenerUtil.mutListener.listen(20424)) {
            if (!TextUtils.isEmpty(content)) {
                if (!ListenerUtil.mutListener.listen(20423)) {
                    intent.putExtra(ReaderConstants.ARG_CONTENT, content);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(20428)) {
            if ((ListenerUtil.mutListener.listen(20425) ? (context instanceof Activity || sourceView != null) : (context instanceof Activity && sourceView != null))) {
                Activity activity = (Activity) context;
                ActivityOptionsCompat options = ActivityOptionsCompat.makeScaleUpAnimation(sourceView, startX, startY, 0, 0);
                if (!ListenerUtil.mutListener.listen(20427)) {
                    ActivityCompat.startActivity(activity, intent, options.toBundle());
                }
            } else {
                if (!ListenerUtil.mutListener.listen(20426)) {
                    context.startActivity(intent);
                }
            }
        }
    }

    public static void showReaderPhotoViewer(Context context, String imageUrl, EnumSet<PhotoViewerOption> imageOptions) {
        if (!ListenerUtil.mutListener.listen(20429)) {
            showReaderPhotoViewer(context, imageUrl, null, null, imageOptions, 0, 0);
        }
    }

    public enum OpenUrlType {

        INTERNAL, EXTERNAL
    }

    public static void openUrl(Context context, String url) {
        if (!ListenerUtil.mutListener.listen(20430)) {
            openUrl(context, url, OpenUrlType.INTERNAL);
        }
    }

    public static void openPost(Context context, ReaderPost post) {
        String url = post.getUrl();
        if (!ListenerUtil.mutListener.listen(20435)) {
            if ((ListenerUtil.mutListener.listen(20432) ? (WPUrlUtils.isWordPressCom(url) && ((ListenerUtil.mutListener.listen(20431) ? (post.isWP() || !post.isJetpack) : (post.isWP() && !post.isJetpack)))) : (WPUrlUtils.isWordPressCom(url) || ((ListenerUtil.mutListener.listen(20431) ? (post.isWP() || !post.isJetpack) : (post.isWP() && !post.isJetpack)))))) {
                if (!ListenerUtil.mutListener.listen(20434)) {
                    WPWebViewActivity.openUrlByUsingGlobalWPCOMCredentials(context, url);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(20433)) {
                    WPWebViewActivity.openURL(context, url, ReaderConstants.HTTP_REFERER_URL);
                }
            }
        }
    }

    public static void sharePost(Context context, ReaderPost post) throws ActivityNotFoundException {
        String url = (post.hasShortUrl() ? post.getShortUrl() : post.getUrl());
        Intent intent = new Intent(Intent.ACTION_SEND);
        if (!ListenerUtil.mutListener.listen(20436)) {
            intent.setType("text/plain");
        }
        if (!ListenerUtil.mutListener.listen(20437)) {
            intent.putExtra(Intent.EXTRA_TEXT, url);
        }
        if (!ListenerUtil.mutListener.listen(20438)) {
            intent.putExtra(Intent.EXTRA_SUBJECT, post.getTitle());
        }
        if (!ListenerUtil.mutListener.listen(20439)) {
            context.startActivity(Intent.createChooser(intent, context.getString(R.string.share_link)));
        }
    }

    public static void openUrl(Context context, String url, OpenUrlType openUrlType) {
        if (!ListenerUtil.mutListener.listen(20441)) {
            if ((ListenerUtil.mutListener.listen(20440) ? (context == null && TextUtils.isEmpty(url)) : (context == null || TextUtils.isEmpty(url)))) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(20444)) {
            if (openUrlType == OpenUrlType.INTERNAL) {
                if (!ListenerUtil.mutListener.listen(20443)) {
                    openUrlInternal(context, url);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(20442)) {
                    ActivityLauncher.openUrlExternal(context, url);
                }
            }
        }
    }

    /*
     * open the passed url in the app's internal WebView activity
     */
    private static void openUrlInternal(Context context, @NonNull String url) {
        if (!ListenerUtil.mutListener.listen(20447)) {
            // That won't work on wpcom sites with custom urls
            if (WPUrlUtils.isWordPressCom(url)) {
                if (!ListenerUtil.mutListener.listen(20446)) {
                    WPWebViewActivity.openUrlByUsingGlobalWPCOMCredentials(context, url);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(20445)) {
                    WPWebViewActivity.openURL(context, url, ReaderConstants.HTTP_REFERER_URL);
                }
            }
        }
    }
}
