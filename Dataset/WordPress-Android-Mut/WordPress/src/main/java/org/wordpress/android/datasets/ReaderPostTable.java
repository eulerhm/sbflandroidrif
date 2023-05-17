package org.wordpress.android.datasets;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.text.TextUtils;
import android.util.Pair;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import org.greenrobot.eventbus.EventBus;
import org.wordpress.android.R;
import org.wordpress.android.WordPress;
import org.wordpress.android.models.ReaderCardType;
import org.wordpress.android.models.ReaderPost;
import org.wordpress.android.models.ReaderPostList;
import org.wordpress.android.models.ReaderTag;
import org.wordpress.android.models.ReaderTagList;
import org.wordpress.android.models.ReaderTagType;
import org.wordpress.android.ui.reader.ReaderConstants;
import org.wordpress.android.ui.reader.actions.ReaderActions;
import org.wordpress.android.ui.reader.models.ReaderBlogIdPostId;
import org.wordpress.android.ui.reader.models.ReaderBlogIdPostIdList;
import org.wordpress.android.ui.reader.repository.ReaderRepositoryEvent.ReaderPostTableActionEnded;
import org.wordpress.android.ui.reader.utils.ReaderUtils;
import org.wordpress.android.util.AppLog;
import org.wordpress.android.util.SqlUtils;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * tbl_posts contains all reader posts - the primary key is pseudo_id + tag_name + tag_type,
 * which allows the same post to appear in multiple streams (ex: it can exist in followed
 * sites, liked posts, and tag streams). note that posts in a specific blog or feed are
 * stored here with an empty tag_name.
 */
public class ReaderPostTable {

    private static final String COLUMN_NAMES = // 1
    "post_id," + // 2
    "blog_id," + // 3
    "feed_id," + // 4
    "feed_item_id," + // 5
    "pseudo_id," + // 6
    "author_name," + // 7
    "author_first_name," + // 8
    "author_id," + // 9
    "title," + // 10
    "text," + // 11
    "excerpt," + // 12
    "format," + // 13
    "url," + // 14
    "short_url," + // 15
    "blog_name," + // 16
    "blog_url," + // 17
    "blog_image_url," + // 18
    "featured_image," + // 19
    "featured_video," + // 20
    "post_avatar," + // 21
    "score," + // 22
    "date_published," + // 23
    "date_liked," + // 24
    "date_tagged," + // 25
    "num_replies," + // 26
    "num_likes," + // 27
    "is_liked," + // 28
    "is_followed," + // 29
    "is_comments_open," + // 30
    "is_external," + // 31
    "is_private," + // 32
    "is_videopress," + // 33
    "is_jetpack," + // 34
    "primary_tag," + // 35
    "secondary_tag," + // 36
    "attachments_json," + // 37
    "discover_json," + // 38
    "xpost_post_id," + // 39
    "xpost_blog_id," + // 40
    "railcar_json," + // 41
    "tag_name," + // 42
    "tag_type," + // 43
    "has_gap_marker," + // 44
    "card_type," + // 45
    "use_excerpt," + // 46
    "is_bookmarked," + // 47
    "is_private_atomic," + // 48
    "tags," + // 49
    "organization_id," + // 50
    "is_seen," + // 51
    "is_seen_supported," + // 52
    "author_blog_id," + // 53
    "author_blog_url";

    // used when querying multiple rows and skipping text column
    private static final String COLUMN_NAMES_NO_TEXT = // 1
    "post_id," + // 2
    "blog_id," + // 3
    "feed_id," + // 4
    "feed_item_id," + // 5
    "author_id," + // 6
    "pseudo_id," + // 7
    "author_name," + // 8
    "author_first_name," + // 9
    "blog_name," + // 10
    "blog_url," + // 11
    "blog_image_url," + // 12
    "excerpt," + // 13
    "format," + // 14
    "featured_image," + // 15
    "featured_video," + // 16
    "title," + // 17
    "url," + // 18
    "short_url," + // 19
    "post_avatar," + // 20
    "score," + // 21
    "date_published," + // 22
    "date_liked," + // 23
    "date_tagged," + // 24
    "num_replies," + // 25
    "num_likes," + // 26
    "is_liked," + // 27
    "is_followed," + // 28
    "is_comments_open," + // 29
    "is_external," + // 30
    "is_private," + // 31
    "is_videopress," + // 32
    "is_jetpack," + // 33
    "primary_tag," + // 34
    "secondary_tag," + // 35
    "attachments_json," + // 36
    "discover_json," + // 37
    "xpost_post_id," + // 38
    "xpost_blog_id," + // 39
    "railcar_json," + // 40
    "tag_name," + // 41
    "tag_type," + // 42
    "has_gap_marker," + // 43
    "card_type," + // 44
    "use_excerpt," + // 45
    "is_bookmarked," + // 46
    "is_private_atomic," + // 47
    "tags," + // 48
    "organization_id," + // 49
    "is_seen," + // 50
    "is_seen_supported," + // 51
    "author_blog_id," + // 52
    "author_blog_url";

    protected static void createTables(SQLiteDatabase db) {
        if (!ListenerUtil.mutListener.listen(619)) {
            db.execSQL("CREATE TABLE tbl_posts (" + " post_id INTEGER DEFAULT 0," + " blog_id INTEGER DEFAULT 0," + " feed_id INTEGER DEFAULT 0," + " feed_item_id INTEGER DEFAULT 0," + " pseudo_id TEXT NOT NULL," + " author_name TEXT," + " author_first_name TEXT," + " author_id INTEGER DEFAULT 0," + " title  TEXT," + " text TEXT," + " excerpt TEXT," + " format TEXT," + " url TEXT," + " short_url TEXT," + " blog_name TEXT," + " blog_url TEXT," + " blog_image_url TEXT," + " featured_image TEXT," + " featured_video TEXT," + " post_avatar TEXT," + " score REAL DEFAULT 0," + " date_published TEXT," + " date_liked TEXT," + " date_tagged TEXT," + " num_replies INTEGER DEFAULT 0," + " num_likes INTEGER DEFAULT 0," + " is_liked INTEGER DEFAULT 0," + " is_followed INTEGER DEFAULT 0," + " is_comments_open INTEGER DEFAULT 0," + " is_external INTEGER DEFAULT 0," + " is_private INTEGER DEFAULT 0," + " is_videopress INTEGER DEFAULT 0," + " is_jetpack INTEGER DEFAULT 0," + " primary_tag TEXT," + " secondary_tag TEXT," + " attachments_json TEXT," + " discover_json TEXT," + " xpost_post_id INTEGER DEFAULT 0," + " xpost_blog_id INTEGER DEFAULT 0," + " railcar_json TEXT," + " tag_name TEXT NOT NULL COLLATE NOCASE," + " tag_type INTEGER DEFAULT 0," + " has_gap_marker INTEGER DEFAULT 0," + " card_type TEXT," + " use_excerpt INTEGER DEFAULT 0," + " is_bookmarked INTEGER DEFAULT 0," + " is_private_atomic INTEGER DEFAULT 0," + " tags TEXT," + " organization_id INTEGER DEFAULT 0," + " is_seen INTEGER DEFAULT 0," + " is_seen_supported INTEGER DEFAULT 0," + " author_blog_id INTEGER DEFAULT 0," + " author_blog_url TEXT," + " PRIMARY KEY (pseudo_id, tag_name, tag_type)" + ")");
        }
        if (!ListenerUtil.mutListener.listen(620)) {
            db.execSQL("CREATE INDEX idx_posts_post_id_blog_id ON tbl_posts(post_id, blog_id)");
        }
        if (!ListenerUtil.mutListener.listen(621)) {
            db.execSQL("CREATE INDEX idx_posts_date_published ON tbl_posts(date_published)");
        }
        if (!ListenerUtil.mutListener.listen(622)) {
            db.execSQL("CREATE INDEX idx_posts_date_tagged ON tbl_posts(date_tagged)");
        }
        if (!ListenerUtil.mutListener.listen(623)) {
            db.execSQL("CREATE INDEX idx_posts_tag_name ON tbl_posts(tag_name)");
        }
    }

    protected static void dropTables(SQLiteDatabase db) {
        if (!ListenerUtil.mutListener.listen(624)) {
            db.execSQL("DROP TABLE IF EXISTS tbl_posts");
        }
    }

    protected static void reset(SQLiteDatabase db) {
        if (!ListenerUtil.mutListener.listen(625)) {
            dropTables(db);
        }
        if (!ListenerUtil.mutListener.listen(626)) {
            createTables(db);
        }
    }

    /*
     * purge table of unattached/older posts - no need to wrap this in a transaction since it's
     * only called from ReaderDatabase.purge() which already creates a transaction
     */
    protected static int purge(SQLiteDatabase db) {
        // delete posts attached to tags that no longer exist
        int numDeleted = db.delete("tbl_posts", "tag_name NOT IN (SELECT DISTINCT tag_name FROM tbl_tags)", null);
        // delete excess posts on a per-tag basis
        ReaderTagList tags = ReaderTagTable.getAllTags();
        if (!ListenerUtil.mutListener.listen(628)) {
            {
                long _loopCounter21 = 0;
                for (ReaderTag tag : tags) {
                    ListenerUtil.loopListener.listen("_loopCounter21", ++_loopCounter21);
                    if (!ListenerUtil.mutListener.listen(627)) {
                        numDeleted += purgePostsForTag(db, tag);
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(629)) {
            numDeleted += purgeUnbookmarkedPostsWithBookmarkTag();
        }
        if (!ListenerUtil.mutListener.listen(630)) {
            // delete search results
            numDeleted += purgeSearchResults(db);
        }
        return numDeleted;
    }

    /**
     * When the user unbookmarks a post, we keep the row in the database, but we just change the is_bookmarked flag
     * to false, so we can show "undo" items in the saved posts list. This method purges database from such rows.
     */
    public static int purgeUnbookmarkedPostsWithBookmarkTag() {
        int numDeleted = 0;
        ReaderTagList tags = ReaderTagTable.getAllTags();
        if (!ListenerUtil.mutListener.listen(633)) {
            {
                long _loopCounter22 = 0;
                for (ReaderTag tag : tags) {
                    ListenerUtil.loopListener.listen("_loopCounter22", ++_loopCounter22);
                    if (!ListenerUtil.mutListener.listen(632)) {
                        if (tag.isBookmarked()) {
                            // delete posts which has a bookmark tag but is_bookmarked flag is false
                            String[] args = { tag.getTagSlug(), Integer.toString(tag.tagType.toInt()) };
                            if (!ListenerUtil.mutListener.listen(631)) {
                                numDeleted += ReaderDatabase.getWritableDb().delete("tbl_posts", "tag_name=? AND tag_type=? AND is_bookmarked=0", args);
                            }
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(640)) {
            if ((ListenerUtil.mutListener.listen(638) ? (numDeleted >= 0) : (ListenerUtil.mutListener.listen(637) ? (numDeleted <= 0) : (ListenerUtil.mutListener.listen(636) ? (numDeleted < 0) : (ListenerUtil.mutListener.listen(635) ? (numDeleted != 0) : (ListenerUtil.mutListener.listen(634) ? (numDeleted == 0) : (numDeleted > 0))))))) {
                if (!ListenerUtil.mutListener.listen(639)) {
                    EventBus.getDefault().post(ReaderPostTableActionEnded.INSTANCE);
                }
            }
        }
        return numDeleted;
    }

    /*
     * purge excess posts in the passed tag
     */
    private static final int MAX_POSTS_PER_TAG = ReaderConstants.READER_MAX_POSTS_TO_DISPLAY;

    private static int purgePostsForTag(SQLiteDatabase db, ReaderTag tag) {
        int numPosts = getNumPostsWithTag(tag);
        if (!ListenerUtil.mutListener.listen(646)) {
            if ((ListenerUtil.mutListener.listen(645) ? (numPosts >= MAX_POSTS_PER_TAG) : (ListenerUtil.mutListener.listen(644) ? (numPosts > MAX_POSTS_PER_TAG) : (ListenerUtil.mutListener.listen(643) ? (numPosts < MAX_POSTS_PER_TAG) : (ListenerUtil.mutListener.listen(642) ? (numPosts != MAX_POSTS_PER_TAG) : (ListenerUtil.mutListener.listen(641) ? (numPosts == MAX_POSTS_PER_TAG) : (numPosts <= MAX_POSTS_PER_TAG))))))) {
                return 0;
            }
        }
        String tagName = tag.getTagSlug();
        String tagType = Integer.toString(tag.tagType.toInt());
        String[] args = { tagName, tagType, tagName, tagType, Integer.toString(MAX_POSTS_PER_TAG) };
        String where = "tag_name=? AND tag_type=? AND pseudo_id NOT IN (SELECT DISTINCT pseudo_id FROM tbl_posts WHERE " + "tag_name=? AND tag_type=? ORDER BY " + getSortColumnForTag(tag) + " DESC LIMIT ?)";
        int numDeleted = db.delete("tbl_posts", where, args);
        if (!ListenerUtil.mutListener.listen(647)) {
            AppLog.d(AppLog.T.READER, String.format(Locale.ENGLISH, "reader post table > purged %d posts in tag %s", numDeleted, tag.getTagNameForLog()));
        }
        return numDeleted;
    }

    /*
     * purge all posts that were retained from previous searches
     */
    private static int purgeSearchResults(SQLiteDatabase db) {
        String[] args = { Integer.toString(ReaderTagType.SEARCH.toInt()) };
        return db.delete("tbl_posts", "tag_type=?", args);
    }

    public static int getNumPostsInBlog(long blogId) {
        if (!ListenerUtil.mutListener.listen(653)) {
            if ((ListenerUtil.mutListener.listen(652) ? (blogId >= 0) : (ListenerUtil.mutListener.listen(651) ? (blogId <= 0) : (ListenerUtil.mutListener.listen(650) ? (blogId > 0) : (ListenerUtil.mutListener.listen(649) ? (blogId < 0) : (ListenerUtil.mutListener.listen(648) ? (blogId != 0) : (blogId == 0))))))) {
                return 0;
            }
        }
        return SqlUtils.intForQuery(ReaderDatabase.getReadableDb(), "SELECT count(*) FROM tbl_posts WHERE blog_id=? AND tag_name='' AND tag_type=0", new String[] { Long.toString(blogId) });
    }

    public static int getNumPostsInFeed(long feedId) {
        if (!ListenerUtil.mutListener.listen(659)) {
            if ((ListenerUtil.mutListener.listen(658) ? (feedId >= 0) : (ListenerUtil.mutListener.listen(657) ? (feedId <= 0) : (ListenerUtil.mutListener.listen(656) ? (feedId > 0) : (ListenerUtil.mutListener.listen(655) ? (feedId < 0) : (ListenerUtil.mutListener.listen(654) ? (feedId != 0) : (feedId == 0))))))) {
                return 0;
            }
        }
        return SqlUtils.intForQuery(ReaderDatabase.getReadableDb(), "SELECT count(*) FROM tbl_posts WHERE feed_id=? AND tag_name='' AND tag_type=0", new String[] { Long.toString(feedId) });
    }

    public static int getNumPostsWithTag(ReaderTag tag) {
        if (!ListenerUtil.mutListener.listen(660)) {
            if (tag == null) {
                return 0;
            }
        }
        String[] args = { tag.getTagSlug(), Integer.toString(tag.tagType.toInt()) };
        return SqlUtils.intForQuery(ReaderDatabase.getReadableDb(), "SELECT count(*) FROM tbl_posts WHERE tag_name=? AND tag_type=?", args);
    }

    public static void updatePost(@NonNull ReaderPost post) {
        // necessary because a post can exist multiple times in the table with different tags
        ContentValues values = new ContentValues();
        if (!ListenerUtil.mutListener.listen(661)) {
            values.put("title", post.getTitle());
        }
        if (!ListenerUtil.mutListener.listen(662)) {
            values.put("text", post.getText());
        }
        if (!ListenerUtil.mutListener.listen(663)) {
            values.put("excerpt", post.getExcerpt());
        }
        if (!ListenerUtil.mutListener.listen(664)) {
            values.put("num_replies", post.numReplies);
        }
        if (!ListenerUtil.mutListener.listen(665)) {
            values.put("num_likes", post.numLikes);
        }
        if (!ListenerUtil.mutListener.listen(666)) {
            values.put("is_liked", post.isLikedByCurrentUser);
        }
        if (!ListenerUtil.mutListener.listen(667)) {
            values.put("is_followed", post.isFollowedByCurrentUser);
        }
        if (!ListenerUtil.mutListener.listen(668)) {
            values.put("is_comments_open", post.isCommentsOpen);
        }
        if (!ListenerUtil.mutListener.listen(669)) {
            values.put("use_excerpt", post.useExcerpt);
        }
        if (!ListenerUtil.mutListener.listen(670)) {
            ReaderDatabase.getWritableDb().update("tbl_posts", values, "pseudo_id=?", new String[] { post.getPseudoId() });
        }
        ReaderPostList posts = new ReaderPostList();
        if (!ListenerUtil.mutListener.listen(671)) {
            posts.add(post);
        }
        if (!ListenerUtil.mutListener.listen(672)) {
            addOrUpdatePosts(null, posts);
        }
    }

    public static void addPost(@NonNull ReaderPost post) {
        ReaderPostList posts = new ReaderPostList();
        if (!ListenerUtil.mutListener.listen(673)) {
            posts.add(post);
        }
        if (!ListenerUtil.mutListener.listen(674)) {
            addOrUpdatePosts(null, posts);
        }
    }

    @Nullable
    public static ReaderPost getBlogPost(long blogId, long postId, boolean excludeTextColumn) {
        return getPost("blog_id=? AND post_id=?", new String[] { Long.toString(blogId), Long.toString(postId) }, excludeTextColumn);
    }

    @Nullable
    public static ReaderPost getBlogPost(String blogSlug, String postSlug, boolean excludeTextColumn) {
        return getPost("blog_url LIKE ? AND url LIKE ?", new String[] { "%//" + blogSlug, "%/" + postSlug + "/" }, excludeTextColumn);
    }

    @Nullable
    public static ReaderPost getFeedPost(long feedId, long feedItemId, boolean excludeTextColumn) {
        return getPost("feed_id=? AND feed_item_id=?", new String[] { Long.toString(feedId), Long.toString(feedItemId) }, excludeTextColumn);
    }

    @Nullable
    private static ReaderPost getPost(String where, String[] args, boolean excludeTextColumn) {
        String columns = (excludeTextColumn ? COLUMN_NAMES_NO_TEXT : "*");
        String sql = "SELECT " + columns + " FROM tbl_posts WHERE " + where + " LIMIT 1";
        Cursor c = ReaderDatabase.getReadableDb().rawQuery(sql, args);
        try {
            if (!c.moveToFirst()) {
                return null;
            }
            return getPostFromCursor(c);
        } finally {
            if (!ListenerUtil.mutListener.listen(675)) {
                SqlUtils.closeCursor(c);
            }
        }
    }

    public static String getPostTitle(long blogId, long postId) {
        String[] args = { Long.toString(blogId), Long.toString(postId) };
        return SqlUtils.stringForQuery(ReaderDatabase.getReadableDb(), "SELECT title FROM tbl_posts WHERE blog_id=? AND post_id=?", args);
    }

    public static String getPostBlogName(long blogId, long postId) {
        String[] args = { Long.toString(blogId), Long.toString(postId) };
        return SqlUtils.stringForQuery(ReaderDatabase.getReadableDb(), "SELECT blog_name FROM tbl_posts WHERE blog_id=? AND post_id=?", args);
    }

    public static String getPostText(long blogId, long postId) {
        String[] args = { Long.toString(blogId), Long.toString(postId) };
        return SqlUtils.stringForQuery(ReaderDatabase.getReadableDb(), "SELECT text FROM tbl_posts WHERE blog_id=? AND post_id=?", args);
    }

    public static boolean postExists(long blogId, long postId) {
        String[] args = { Long.toString(blogId), Long.toString(postId) };
        return SqlUtils.boolForQuery(ReaderDatabase.getReadableDb(), "SELECT 1 FROM tbl_posts WHERE blog_id=? AND post_id=?", args);
    }

    private static boolean postExistsForReaderTag(long blogId, long postId, ReaderTag readerTag) {
        String[] args = { Long.toString(blogId), Long.toString(postId), readerTag.getTagSlug(), Integer.toString(readerTag.tagType.toInt()) };
        return SqlUtils.boolForQuery(ReaderDatabase.getReadableDb(), "SELECT 1 FROM tbl_posts WHERE blog_id=? AND post_id=? AND tag_name=? AND tag_type=?", args);
    }

    /*
     * returns whether any of the passed posts are new or changed - used after posts are retrieved
     */
    public static ReaderActions.UpdateResult comparePosts(ReaderPostList posts) {
        if (!ListenerUtil.mutListener.listen(677)) {
            if ((ListenerUtil.mutListener.listen(676) ? (posts == null && posts.size() == 0) : (posts == null || posts.size() == 0))) {
                return ReaderActions.UpdateResult.UNCHANGED;
            }
        }
        boolean hasChanges = false;
        if (!ListenerUtil.mutListener.listen(681)) {
            {
                long _loopCounter23 = 0;
                for (ReaderPost post : posts) {
                    ListenerUtil.loopListener.listen("_loopCounter23", ++_loopCounter23);
                    ReaderPost existingPost = getBlogPost(post.blogId, post.postId, true);
                    if (!ListenerUtil.mutListener.listen(680)) {
                        if (existingPost == null) {
                            return ReaderActions.UpdateResult.HAS_NEW;
                        } else if ((ListenerUtil.mutListener.listen(678) ? (!hasChanges || !post.isSamePost(existingPost)) : (!hasChanges && !post.isSamePost(existingPost)))) {
                            if (!ListenerUtil.mutListener.listen(679)) {
                                hasChanges = true;
                            }
                        }
                    }
                }
            }
        }
        return (hasChanges ? ReaderActions.UpdateResult.CHANGED : ReaderActions.UpdateResult.UNCHANGED);
    }

    /*
     * returns true if any posts in the passed list exist in this list for the given tag
     */
    public static boolean hasOverlap(ReaderPostList posts, ReaderTag tag) {
        if (!ListenerUtil.mutListener.listen(683)) {
            {
                long _loopCounter24 = 0;
                for (ReaderPost post : posts) {
                    ListenerUtil.loopListener.listen("_loopCounter24", ++_loopCounter24);
                    if (!ListenerUtil.mutListener.listen(682)) {
                        if (postExistsForReaderTag(post.blogId, post.postId, tag)) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    /*
     * returns the #comments known to exist for this post (ie: #comments the server says this post has), which
     * may differ from ReaderCommentTable.getNumCommentsForPost (which returns # local comments for this post)
     */
    public static int getNumCommentsForPost(ReaderPost post) {
        if (!ListenerUtil.mutListener.listen(684)) {
            if (post == null) {
                return 0;
            }
        }
        return getNumCommentsForPost(post.blogId, post.postId);
    }

    public static int getNumCommentsForPost(long blogId, long postId) {
        String[] args = new String[] { Long.toString(blogId), Long.toString(postId) };
        return SqlUtils.intForQuery(ReaderDatabase.getReadableDb(), "SELECT num_replies FROM tbl_posts WHERE blog_id=? AND post_id=?", args);
    }

    public static void setNumCommentsForPost(long blogId, long postId, int numComments) {
        ContentValues values = new ContentValues();
        if (!ListenerUtil.mutListener.listen(685)) {
            values.put("num_replies", numComments);
        }
        if (!ListenerUtil.mutListener.listen(686)) {
            update(blogId, postId, values);
        }
    }

    public static void incNumCommentsForPost(long blogId, long postId) {
        int numComments = getNumCommentsForPost(blogId, postId);
        if (!ListenerUtil.mutListener.listen(687)) {
            numComments++;
        }
        if (!ListenerUtil.mutListener.listen(688)) {
            setNumCommentsForPost(blogId, postId, numComments);
        }
    }

    public static void decrementNumCommentsForPost(long blogId, long postId) {
        int numComments = getNumCommentsForPost(blogId, postId);
        if (!ListenerUtil.mutListener.listen(697)) {
            if ((ListenerUtil.mutListener.listen(693) ? (numComments >= 0) : (ListenerUtil.mutListener.listen(692) ? (numComments <= 0) : (ListenerUtil.mutListener.listen(691) ? (numComments < 0) : (ListenerUtil.mutListener.listen(690) ? (numComments != 0) : (ListenerUtil.mutListener.listen(689) ? (numComments == 0) : (numComments > 0))))))) {
                if (!ListenerUtil.mutListener.listen(695)) {
                    numComments--;
                }
                if (!ListenerUtil.mutListener.listen(696)) {
                    setNumCommentsForPost(blogId, postId, numComments);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(694)) {
                    AppLog.d(AppLog.T.READER, "Failed to decrement the number of post comments because they are 0");
                }
            }
        }
    }

    /*
     * returns the #likes known to exist for this post (ie: #likes the server says this post has), which
     * may differ from ReaderPostTable.getNumLikesForPost (which returns # local likes for this post)
     */
    public static int getNumLikesForPost(long blogId, long postId) {
        String[] args = { Long.toString(blogId), Long.toString(postId) };
        return SqlUtils.intForQuery(ReaderDatabase.getReadableDb(), "SELECT num_likes FROM tbl_posts WHERE blog_id=? AND post_id=?", args);
    }

    public static boolean isPostLikedByCurrentUser(ReaderPost post) {
        return (ListenerUtil.mutListener.listen(698) ? (post != null || isPostLikedByCurrentUser(post.blogId, post.postId)) : (post != null && isPostLikedByCurrentUser(post.blogId, post.postId)));
    }

    public static boolean isPostLikedByCurrentUser(long blogId, long postId) {
        String[] args = new String[] { Long.toString(blogId), Long.toString(postId) };
        return SqlUtils.boolForQuery(ReaderDatabase.getReadableDb(), "SELECT is_liked FROM tbl_posts WHERE blog_id=? AND post_id=?", args);
    }

    /*
     * updates both the like count for a post and whether it's liked by the current user
     */
    public static void setLikesForPost(ReaderPost post, int numLikes, boolean isLikedByCurrentUser) {
        if (!ListenerUtil.mutListener.listen(699)) {
            if (post == null) {
                return;
            }
        }
        ContentValues values = new ContentValues();
        if (!ListenerUtil.mutListener.listen(700)) {
            values.put("num_likes", numLikes);
        }
        if (!ListenerUtil.mutListener.listen(701)) {
            values.put("is_liked", SqlUtils.boolToSql(isLikedByCurrentUser));
        }
        if (!ListenerUtil.mutListener.listen(702)) {
            update(post.blogId, post.postId, values);
        }
    }

    public static void setBookmarkFlag(long blogId, long postId, boolean bookmark) {
        ContentValues values = new ContentValues();
        if (!ListenerUtil.mutListener.listen(703)) {
            values.put("is_bookmarked", SqlUtils.boolToSql(bookmark));
        }
        if (!ListenerUtil.mutListener.listen(704)) {
            update(blogId, postId, values);
        }
    }

    public static boolean hasBookmarkedPosts() {
        String sql = "SELECT 1 FROM tbl_posts WHERE is_bookmarked != 0 LIMIT 1";
        return SqlUtils.boolForQuery(ReaderDatabase.getReadableDb(), sql, null);
    }

    private static void update(long blogId, long postId, ContentValues values) {
        String[] args = { Long.toString(blogId), Long.toString(postId) };
        if (!ListenerUtil.mutListener.listen(705)) {
            ReaderDatabase.getWritableDb().update("tbl_posts", values, "blog_id=? AND post_id=?", args);
        }
        if (!ListenerUtil.mutListener.listen(706)) {
            EventBus.getDefault().post(ReaderPostTableActionEnded.INSTANCE);
        }
    }

    public static boolean isPostFollowed(ReaderPost post) {
        if (!ListenerUtil.mutListener.listen(707)) {
            if (post == null) {
                return false;
            }
        }
        String[] args = new String[] { Long.toString(post.blogId), Long.toString(post.postId) };
        return SqlUtils.boolForQuery(ReaderDatabase.getReadableDb(), "SELECT is_followed FROM tbl_posts WHERE blog_id=? AND post_id=?", args);
    }

    public static boolean isPostSeen(ReaderPost post) {
        String[] args = new String[] { Long.toString(post.blogId), Long.toString(post.postId) };
        return SqlUtils.boolForQuery(ReaderDatabase.getReadableDb(), "SELECT is_seen FROM tbl_posts WHERE blog_id=? AND post_id=?", args);
    }

    public static void setPostSeenStatus(ReaderPost post, boolean isSeen) {
        SQLiteDatabase db = ReaderDatabase.getWritableDb();
        if (!ListenerUtil.mutListener.listen(708)) {
            db.beginTransaction();
        }
        try {
            String sql = "UPDATE tbl_posts SET is_seen=" + SqlUtils.boolToSql(isSeen) + " WHERE blog_id=? AND post_id=?";
            if (!ListenerUtil.mutListener.listen(710)) {
                db.execSQL(sql, new String[] { Long.toString(post.blogId), Long.toString(post.postId) });
            }
            if (!ListenerUtil.mutListener.listen(711)) {
                db.setTransactionSuccessful();
            }
            if (!ListenerUtil.mutListener.listen(712)) {
                EventBus.getDefault().post(ReaderPostTableActionEnded.INSTANCE);
            }
        } finally {
            if (!ListenerUtil.mutListener.listen(709)) {
                db.endTransaction();
            }
        }
    }

    public static int deletePostsWithTag(final ReaderTag tag) {
        if (!ListenerUtil.mutListener.listen(713)) {
            if (tag == null) {
                return 0;
            }
        }
        String[] args = { tag.getTagSlug(), Integer.toString(tag.tagType.toInt()) };
        int rowsDeleted = ReaderDatabase.getWritableDb().delete("tbl_posts", "tag_name=? AND tag_type=?", args);
        if (!ListenerUtil.mutListener.listen(720)) {
            if ((ListenerUtil.mutListener.listen(718) ? (rowsDeleted >= 0) : (ListenerUtil.mutListener.listen(717) ? (rowsDeleted <= 0) : (ListenerUtil.mutListener.listen(716) ? (rowsDeleted < 0) : (ListenerUtil.mutListener.listen(715) ? (rowsDeleted != 0) : (ListenerUtil.mutListener.listen(714) ? (rowsDeleted == 0) : (rowsDeleted > 0))))))) {
                if (!ListenerUtil.mutListener.listen(719)) {
                    EventBus.getDefault().post(ReaderPostTableActionEnded.INSTANCE);
                }
            }
        }
        return rowsDeleted;
    }

    public static int removeTagsFromPost(long blogId, long postId, final ReaderTagType tagType) {
        if (!ListenerUtil.mutListener.listen(721)) {
            if (tagType == null) {
                return 0;
            }
        }
        String[] args = { Integer.toString(tagType.toInt()), Long.toString(blogId), Long.toString(postId) };
        int rowsDeleted = ReaderDatabase.getWritableDb().delete("tbl_posts", "tag_type=? AND blog_id=? AND post_id=?", args);
        if (!ListenerUtil.mutListener.listen(728)) {
            if ((ListenerUtil.mutListener.listen(726) ? (rowsDeleted >= 0) : (ListenerUtil.mutListener.listen(725) ? (rowsDeleted <= 0) : (ListenerUtil.mutListener.listen(724) ? (rowsDeleted < 0) : (ListenerUtil.mutListener.listen(723) ? (rowsDeleted != 0) : (ListenerUtil.mutListener.listen(722) ? (rowsDeleted == 0) : (rowsDeleted > 0))))))) {
                if (!ListenerUtil.mutListener.listen(727)) {
                    EventBus.getDefault().post(ReaderPostTableActionEnded.INSTANCE);
                }
            }
        }
        return rowsDeleted;
    }

    public static int deletePostsInBlog(long blogId) {
        String[] args = { Long.toString(blogId) };
        int rowsDeleted = ReaderDatabase.getWritableDb().delete("tbl_posts", "blog_id = ?", args);
        if (!ListenerUtil.mutListener.listen(735)) {
            if ((ListenerUtil.mutListener.listen(733) ? (rowsDeleted >= 0) : (ListenerUtil.mutListener.listen(732) ? (rowsDeleted <= 0) : (ListenerUtil.mutListener.listen(731) ? (rowsDeleted < 0) : (ListenerUtil.mutListener.listen(730) ? (rowsDeleted != 0) : (ListenerUtil.mutListener.listen(729) ? (rowsDeleted == 0) : (rowsDeleted > 0))))))) {
                if (!ListenerUtil.mutListener.listen(734)) {
                    EventBus.getDefault().post(ReaderPostTableActionEnded.INSTANCE);
                }
            }
        }
        return rowsDeleted;
    }

    public static void deletePost(long blogId, long postId) {
        String[] args = new String[] { Long.toString(blogId), Long.toString(postId) };
        if (!ListenerUtil.mutListener.listen(736)) {
            ReaderDatabase.getWritableDb().delete("tbl_posts", "blog_id=? AND post_id=?", args);
        }
        if (!ListenerUtil.mutListener.listen(737)) {
            EventBus.getDefault().post(ReaderPostTableActionEnded.INSTANCE);
        }
    }

    /*
     * ensure that posts in blogs that are no longer followed don't have their followed status
     * set to true
     */
    public static void updateFollowedStatus() {
        SQLiteStatement statement = ReaderDatabase.getWritableDb().compileStatement("UPDATE tbl_posts SET is_followed = 0" + " WHERE is_followed != 0" + " AND blog_id NOT IN (SELECT DISTINCT blog_id FROM tbl_blog_info WHERE is_followed != 0)");
        try {
            int count = statement.executeUpdateDelete();
            if (!ListenerUtil.mutListener.listen(746)) {
                if ((ListenerUtil.mutListener.listen(743) ? (count >= 0) : (ListenerUtil.mutListener.listen(742) ? (count <= 0) : (ListenerUtil.mutListener.listen(741) ? (count < 0) : (ListenerUtil.mutListener.listen(740) ? (count != 0) : (ListenerUtil.mutListener.listen(739) ? (count == 0) : (count > 0))))))) {
                    if (!ListenerUtil.mutListener.listen(744)) {
                        AppLog.d(AppLog.T.READER, String.format(Locale.ENGLISH, "reader post table > marked %d posts unfollowed", count));
                    }
                    if (!ListenerUtil.mutListener.listen(745)) {
                        EventBus.getDefault().post(ReaderPostTableActionEnded.INSTANCE);
                    }
                }
            }
        } finally {
            if (!ListenerUtil.mutListener.listen(738)) {
                statement.close();
            }
        }
    }

    /*
     * returns the iso8601 date of the oldest post with the passed tag
     */
    public static String getOldestDateWithTag(final ReaderTag tag) {
        if (!ListenerUtil.mutListener.listen(747)) {
            if (tag == null) {
                return "";
            }
        }
        // date field depends on the tag
        String dateColumn = getSortColumnForTag(tag);
        String sql = "SELECT " + dateColumn + " FROM tbl_posts" + " WHERE tag_name=? AND tag_type=?" + " ORDER BY " + dateColumn + " LIMIT 1";
        String[] args = { tag.getTagSlug(), Integer.toString(tag.tagType.toInt()) };
        return SqlUtils.stringForQuery(ReaderDatabase.getReadableDb(), sql, args);
    }

    /*
     * returns the iso8601 pub date of the oldest post in the passed blog
     */
    public static String getOldestPubDateInBlog(long blogId) {
        String sql = "SELECT date_published FROM tbl_posts" + " WHERE blog_id=? AND tag_name='' AND tag_type=0" + " ORDER BY date_published LIMIT 1";
        return SqlUtils.stringForQuery(ReaderDatabase.getReadableDb(), sql, new String[] { Long.toString(blogId) });
    }

    public static String getOldestPubDateInFeed(long feedId) {
        String sql = "SELECT date_published FROM tbl_posts" + " WHERE feed_id=? AND tag_name='' AND tag_type=0" + " ORDER BY date_published LIMIT 1";
        return SqlUtils.stringForQuery(ReaderDatabase.getReadableDb(), sql, new String[] { Long.toString(feedId) });
    }

    public static void removeGapMarkerForTag(final ReaderTag tag) {
        if (!ListenerUtil.mutListener.listen(748)) {
            if (tag == null) {
                return;
            }
        }
        String[] args = { tag.getTagSlug(), Integer.toString(tag.tagType.toInt()) };
        String sql = "UPDATE tbl_posts SET has_gap_marker=0 WHERE has_gap_marker!=0 AND tag_name=? AND tag_type=?";
        if (!ListenerUtil.mutListener.listen(749)) {
            ReaderDatabase.getWritableDb().execSQL(sql, args);
        }
        if (!ListenerUtil.mutListener.listen(750)) {
            EventBus.getDefault().post(ReaderPostTableActionEnded.INSTANCE);
        }
    }

    /*
     * returns the blogId/postId of the post with the passed tag that has a gap marker, or null if none exists
     */
    public static ReaderBlogIdPostId getGapMarkerIdsForTag(final ReaderTag tag) {
        if (tag == null) {
            return null;
        }
        String[] args = { tag.getTagSlug(), Integer.toString(tag.tagType.toInt()) };
        String sql = "SELECT blog_id, post_id FROM tbl_posts WHERE has_gap_marker!=0 AND tag_name=? AND tag_type=?";
        Cursor cursor = ReaderDatabase.getReadableDb().rawQuery(sql, args);
        try {
            if (cursor.moveToFirst()) {
                long blogId = cursor.getLong(0);
                long postId = cursor.getLong(1);
                return new ReaderBlogIdPostId(blogId, postId);
            } else {
                return null;
            }
        } finally {
            if (!ListenerUtil.mutListener.listen(751)) {
                SqlUtils.closeCursor(cursor);
            }
        }
    }

    public static void setGapMarkerForTag(long blogId, long postId, ReaderTag tag) {
        if (!ListenerUtil.mutListener.listen(752)) {
            if (tag == null) {
                return;
            }
        }
        String[] args = { Long.toString(blogId), Long.toString(postId), tag.getTagSlug(), Integer.toString(tag.tagType.toInt()) };
        String sql = "UPDATE tbl_posts SET has_gap_marker=1 WHERE blog_id=? AND post_id=? AND tag_name=? AND tag_type=?";
        if (!ListenerUtil.mutListener.listen(753)) {
            ReaderDatabase.getWritableDb().execSQL(sql, args);
        }
        if (!ListenerUtil.mutListener.listen(754)) {
            EventBus.getDefault().post(ReaderPostTableActionEnded.INSTANCE);
        }
    }

    public static String getGapMarkerDateForTag(ReaderTag tag) {
        ReaderBlogIdPostId ids = getGapMarkerIdsForTag(tag);
        if (!ListenerUtil.mutListener.listen(755)) {
            if (ids == null) {
                return null;
            }
        }
        String dateColumn = getSortColumnForTag(tag);
        String[] args = { Long.toString(ids.getBlogId()), Long.toString(ids.getPostId()) };
        String sql = "SELECT " + dateColumn + " FROM tbl_posts WHERE blog_id=? AND post_id=?";
        return SqlUtils.stringForQuery(ReaderDatabase.getReadableDb(), sql, args);
    }

    /*
     * the column posts are sorted by depends on the type of tag stream being displayed:
     *
     * liked posts sort by the date the post was liked
     * followed posts sort by the date the post was published
     * search results sort by score
     * tagged posts sort by the date the post was tagged
     */
    private static String getSortColumnForTag(ReaderTag tag) {
        if (tag.isPostsILike()) {
            return "date_liked";
        } else if (tag.isFollowedSites()) {
            return "date_published";
        } else if (tag.tagType == ReaderTagType.SEARCH) {
            return "score";
        } else if ((ListenerUtil.mutListener.listen(756) ? (tag.isTagTopic() && tag.isBookmarked()) : (tag.isTagTopic() || tag.isBookmarked()))) {
            return "date_tagged";
        } else {
            return "date_published";
        }
    }

    /*
     * delete posts with the passed tag that come before the one with the gap marker for
     * this tag - note this may leave some stray posts in tbl_posts, but these will
     * be cleaned up by the next purge
     */
    public static void deletePostsBeforeGapMarkerForTag(ReaderTag tag) {
        String gapMarkerDate = getGapMarkerDateForTag(tag);
        if (!ListenerUtil.mutListener.listen(757)) {
            if (TextUtils.isEmpty(gapMarkerDate)) {
                return;
            }
        }
        String dateColumn = getSortColumnForTag(tag);
        String[] args = { tag.getTagSlug(), Integer.toString(tag.tagType.toInt()), gapMarkerDate };
        String where = "tag_name=? AND tag_type=? AND " + dateColumn + " < ?";
        int numDeleted = ReaderDatabase.getWritableDb().delete("tbl_posts", where, args);
        if (!ListenerUtil.mutListener.listen(765)) {
            if ((ListenerUtil.mutListener.listen(762) ? (numDeleted >= 0) : (ListenerUtil.mutListener.listen(761) ? (numDeleted <= 0) : (ListenerUtil.mutListener.listen(760) ? (numDeleted < 0) : (ListenerUtil.mutListener.listen(759) ? (numDeleted != 0) : (ListenerUtil.mutListener.listen(758) ? (numDeleted == 0) : (numDeleted > 0))))))) {
                if (!ListenerUtil.mutListener.listen(763)) {
                    AppLog.d(AppLog.T.READER, "removed " + numDeleted + " posts older than gap marker");
                }
                if (!ListenerUtil.mutListener.listen(764)) {
                    EventBus.getDefault().post(ReaderPostTableActionEnded.INSTANCE);
                }
            }
        }
    }

    public static void setFollowStatusForPostsInBlog(long blogId, boolean isFollowed) {
        if (!ListenerUtil.mutListener.listen(766)) {
            setFollowStatusForPosts(blogId, 0, isFollowed);
        }
    }

    public static void setFollowStatusForPostsInFeed(long feedId, boolean isFollowed) {
        if (!ListenerUtil.mutListener.listen(767)) {
            setFollowStatusForPosts(0, feedId, isFollowed);
        }
    }

    private static void setFollowStatusForPosts(long blogId, long feedId, boolean isFollowed) {
        if (!ListenerUtil.mutListener.listen(779)) {
            if ((ListenerUtil.mutListener.listen(778) ? ((ListenerUtil.mutListener.listen(772) ? (blogId >= 0) : (ListenerUtil.mutListener.listen(771) ? (blogId <= 0) : (ListenerUtil.mutListener.listen(770) ? (blogId > 0) : (ListenerUtil.mutListener.listen(769) ? (blogId < 0) : (ListenerUtil.mutListener.listen(768) ? (blogId != 0) : (blogId == 0)))))) || (ListenerUtil.mutListener.listen(777) ? (feedId >= 0) : (ListenerUtil.mutListener.listen(776) ? (feedId <= 0) : (ListenerUtil.mutListener.listen(775) ? (feedId > 0) : (ListenerUtil.mutListener.listen(774) ? (feedId < 0) : (ListenerUtil.mutListener.listen(773) ? (feedId != 0) : (feedId == 0))))))) : ((ListenerUtil.mutListener.listen(772) ? (blogId >= 0) : (ListenerUtil.mutListener.listen(771) ? (blogId <= 0) : (ListenerUtil.mutListener.listen(770) ? (blogId > 0) : (ListenerUtil.mutListener.listen(769) ? (blogId < 0) : (ListenerUtil.mutListener.listen(768) ? (blogId != 0) : (blogId == 0)))))) && (ListenerUtil.mutListener.listen(777) ? (feedId >= 0) : (ListenerUtil.mutListener.listen(776) ? (feedId <= 0) : (ListenerUtil.mutListener.listen(775) ? (feedId > 0) : (ListenerUtil.mutListener.listen(774) ? (feedId < 0) : (ListenerUtil.mutListener.listen(773) ? (feedId != 0) : (feedId == 0))))))))) {
                return;
            }
        }
        SQLiteDatabase db = ReaderDatabase.getWritableDb();
        if (!ListenerUtil.mutListener.listen(780)) {
            db.beginTransaction();
        }
        try {
            if (!ListenerUtil.mutListener.listen(789)) {
                if ((ListenerUtil.mutListener.listen(786) ? (blogId >= 0) : (ListenerUtil.mutListener.listen(785) ? (blogId <= 0) : (ListenerUtil.mutListener.listen(784) ? (blogId > 0) : (ListenerUtil.mutListener.listen(783) ? (blogId < 0) : (ListenerUtil.mutListener.listen(782) ? (blogId == 0) : (blogId != 0))))))) {
                    String sql = "UPDATE tbl_posts SET is_followed=" + SqlUtils.boolToSql(isFollowed) + " WHERE blog_id=?";
                    if (!ListenerUtil.mutListener.listen(788)) {
                        db.execSQL(sql, new String[] { Long.toString(blogId) });
                    }
                } else {
                    String sql = "UPDATE tbl_posts SET is_followed=" + SqlUtils.boolToSql(isFollowed) + " WHERE feed_id=?";
                    if (!ListenerUtil.mutListener.listen(787)) {
                        db.execSQL(sql, new String[] { Long.toString(feedId) });
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(798)) {
                // if blog/feed is no longer followed, remove its posts tagged with "Followed Sites" or "P2"
                if (!isFollowed) {
                    if (!ListenerUtil.mutListener.listen(797)) {
                        if ((ListenerUtil.mutListener.listen(794) ? (blogId >= 0) : (ListenerUtil.mutListener.listen(793) ? (blogId <= 0) : (ListenerUtil.mutListener.listen(792) ? (blogId > 0) : (ListenerUtil.mutListener.listen(791) ? (blogId < 0) : (ListenerUtil.mutListener.listen(790) ? (blogId == 0) : (blogId != 0))))))) {
                            if (!ListenerUtil.mutListener.listen(796)) {
                                db.delete("tbl_posts", "blog_id=? AND (tag_name=? OR tag_name=?)", new String[] { Long.toString(blogId), ReaderTag.TAG_TITLE_FOLLOWED_SITES, ReaderTag.TAG_SLUG_P2 });
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(795)) {
                                db.delete("tbl_posts", "feed_id=? AND (tag_name=? OR tag_name=?)", new String[] { Long.toString(feedId), ReaderTag.TAG_TITLE_FOLLOWED_SITES, ReaderTag.TAG_SLUG_P2 });
                            }
                        }
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(799)) {
                db.setTransactionSuccessful();
            }
            if (!ListenerUtil.mutListener.listen(800)) {
                EventBus.getDefault().post(ReaderPostTableActionEnded.INSTANCE);
            }
        } finally {
            if (!ListenerUtil.mutListener.listen(781)) {
                db.endTransaction();
            }
        }
    }

    /**
     * Android's CursorWindow has a max size of 2MB per row which can be exceeded
     * with a very large text column, causing an IllegalStateException when the
     * row is read - prevent this by limiting the amount of text that's stored in
     * the text column - note that this situation very rarely occurs
     * http://bit.ly/2Fs7B78
     * http://bit.ly/2oOKCJc
     */
    private static final int MAX_TEXT_LEN = (1024 * 1024) / 2;

    private static String maxText(final ReaderPost post) {
        if ((ListenerUtil.mutListener.listen(805) ? (post.getText().length() >= MAX_TEXT_LEN) : (ListenerUtil.mutListener.listen(804) ? (post.getText().length() > MAX_TEXT_LEN) : (ListenerUtil.mutListener.listen(803) ? (post.getText().length() < MAX_TEXT_LEN) : (ListenerUtil.mutListener.listen(802) ? (post.getText().length() != MAX_TEXT_LEN) : (ListenerUtil.mutListener.listen(801) ? (post.getText().length() == MAX_TEXT_LEN) : (post.getText().length() <= MAX_TEXT_LEN))))))) {
            return post.getText();
        }
        // with a link to the full article
        if (post.hasExcerpt()) {
            if (!ListenerUtil.mutListener.listen(807)) {
                AppLog.w(AppLog.T.READER, "reader post table > max text exceeded, storing excerpt");
            }
            return "<p>" + post.getExcerpt() + "</p>" + String.format("<p style='text-align:center'><a href='%s'>%s</a></p>", post.getUrl(), WordPress.getContext().getString(R.string.reader_label_view_original));
        } else {
            if (!ListenerUtil.mutListener.listen(806)) {
                AppLog.w(AppLog.T.READER, "reader post table > max text exceeded, storing truncated text");
            }
            return post.getText().substring(0, MAX_TEXT_LEN);
        }
    }

    public static void addOrUpdatePosts(final ReaderTag tag, ReaderPostList posts) {
        if (!ListenerUtil.mutListener.listen(809)) {
            if ((ListenerUtil.mutListener.listen(808) ? (posts == null && posts.size() == 0) : (posts == null || posts.size() == 0))) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(810)) {
            updateIsBookmarkedField(posts);
        }
        SQLiteDatabase db = ReaderDatabase.getWritableDb();
        SQLiteStatement stmtPosts = db.compileStatement("INSERT OR REPLACE INTO tbl_posts (" + COLUMN_NAMES + ") VALUES (?1,?2,?3,?4,?5,?6,?7,?8,?9,?10,?11,?12,?13,?14,?15,?16,?17,?18,?19,?20,?21,?22,?23,?24," + "?25,?26,?27,?28,?29,?30,?31,?32,?33,?34,?35,?36,?37,?38,?39,?40,?41,?42,?43,?44, ?45, ?46, ?47," + "?48,?49,?50,?51,?52,?53)");
        if (!ListenerUtil.mutListener.listen(811)) {
            db.beginTransaction();
        }
        try {
            String tagName = (tag != null ? tag.getTagSlug() : "");
            int tagType = (tag != null ? tag.tagType.toInt() : 0);
            ReaderBlogIdPostId postWithGapMarker = getGapMarkerIdsForTag(tag);
            if (!ListenerUtil.mutListener.listen(870)) {
                {
                    long _loopCounter25 = 0;
                    for (ReaderPost post : posts) {
                        ListenerUtil.loopListener.listen("_loopCounter25", ++_loopCounter25);
                        // keep the gapMarker flag
                        boolean hasGapMarker = (ListenerUtil.mutListener.listen(815) ? ((ListenerUtil.mutListener.listen(814) ? (postWithGapMarker != null || postWithGapMarker.getPostId() == post.postId) : (postWithGapMarker != null && postWithGapMarker.getPostId() == post.postId)) || postWithGapMarker.getBlogId() == post.blogId) : ((ListenerUtil.mutListener.listen(814) ? (postWithGapMarker != null || postWithGapMarker.getPostId() == post.postId) : (postWithGapMarker != null && postWithGapMarker.getPostId() == post.postId)) && postWithGapMarker.getBlogId() == post.blogId));
                        if (!ListenerUtil.mutListener.listen(816)) {
                            stmtPosts.bindLong(1, post.postId);
                        }
                        if (!ListenerUtil.mutListener.listen(817)) {
                            stmtPosts.bindLong(2, post.blogId);
                        }
                        if (!ListenerUtil.mutListener.listen(818)) {
                            stmtPosts.bindLong(3, post.feedId);
                        }
                        if (!ListenerUtil.mutListener.listen(819)) {
                            stmtPosts.bindLong(4, post.feedItemId);
                        }
                        if (!ListenerUtil.mutListener.listen(820)) {
                            stmtPosts.bindString(5, post.getPseudoId());
                        }
                        if (!ListenerUtil.mutListener.listen(821)) {
                            stmtPosts.bindString(6, post.getAuthorName());
                        }
                        if (!ListenerUtil.mutListener.listen(822)) {
                            stmtPosts.bindString(7, post.getAuthorFirstName());
                        }
                        if (!ListenerUtil.mutListener.listen(823)) {
                            stmtPosts.bindLong(8, post.authorId);
                        }
                        if (!ListenerUtil.mutListener.listen(824)) {
                            stmtPosts.bindString(9, post.getTitle());
                        }
                        if (!ListenerUtil.mutListener.listen(825)) {
                            stmtPosts.bindString(10, maxText(post));
                        }
                        if (!ListenerUtil.mutListener.listen(826)) {
                            stmtPosts.bindString(11, post.getExcerpt());
                        }
                        if (!ListenerUtil.mutListener.listen(827)) {
                            stmtPosts.bindString(12, post.getFormat());
                        }
                        if (!ListenerUtil.mutListener.listen(828)) {
                            stmtPosts.bindString(13, post.getUrl());
                        }
                        if (!ListenerUtil.mutListener.listen(829)) {
                            stmtPosts.bindString(14, post.getShortUrl());
                        }
                        if (!ListenerUtil.mutListener.listen(830)) {
                            stmtPosts.bindString(15, post.getBlogName());
                        }
                        if (!ListenerUtil.mutListener.listen(831)) {
                            stmtPosts.bindString(16, post.getBlogUrl());
                        }
                        if (!ListenerUtil.mutListener.listen(832)) {
                            stmtPosts.bindString(17, post.getBlogImageUrl());
                        }
                        if (!ListenerUtil.mutListener.listen(833)) {
                            stmtPosts.bindString(18, post.getFeaturedImage());
                        }
                        if (!ListenerUtil.mutListener.listen(834)) {
                            stmtPosts.bindString(19, post.getFeaturedVideo());
                        }
                        if (!ListenerUtil.mutListener.listen(835)) {
                            stmtPosts.bindString(20, post.getPostAvatar());
                        }
                        if (!ListenerUtil.mutListener.listen(836)) {
                            stmtPosts.bindDouble(21, post.score);
                        }
                        if (!ListenerUtil.mutListener.listen(837)) {
                            stmtPosts.bindString(22, post.getDatePublished());
                        }
                        if (!ListenerUtil.mutListener.listen(838)) {
                            stmtPosts.bindString(23, post.getDateLiked());
                        }
                        if (!ListenerUtil.mutListener.listen(839)) {
                            stmtPosts.bindString(24, post.getDateTagged());
                        }
                        if (!ListenerUtil.mutListener.listen(840)) {
                            stmtPosts.bindLong(25, post.numReplies);
                        }
                        if (!ListenerUtil.mutListener.listen(841)) {
                            stmtPosts.bindLong(26, post.numLikes);
                        }
                        if (!ListenerUtil.mutListener.listen(842)) {
                            stmtPosts.bindLong(27, SqlUtils.boolToSql(post.isLikedByCurrentUser));
                        }
                        if (!ListenerUtil.mutListener.listen(843)) {
                            stmtPosts.bindLong(28, SqlUtils.boolToSql(post.isFollowedByCurrentUser));
                        }
                        if (!ListenerUtil.mutListener.listen(844)) {
                            stmtPosts.bindLong(29, SqlUtils.boolToSql(post.isCommentsOpen));
                        }
                        if (!ListenerUtil.mutListener.listen(845)) {
                            stmtPosts.bindLong(30, SqlUtils.boolToSql(post.isExternal));
                        }
                        if (!ListenerUtil.mutListener.listen(846)) {
                            stmtPosts.bindLong(31, SqlUtils.boolToSql(post.isPrivate));
                        }
                        if (!ListenerUtil.mutListener.listen(847)) {
                            stmtPosts.bindLong(32, SqlUtils.boolToSql(post.isVideoPress));
                        }
                        if (!ListenerUtil.mutListener.listen(848)) {
                            stmtPosts.bindLong(33, SqlUtils.boolToSql(post.isJetpack));
                        }
                        if (!ListenerUtil.mutListener.listen(849)) {
                            stmtPosts.bindString(34, post.getPrimaryTag());
                        }
                        if (!ListenerUtil.mutListener.listen(850)) {
                            stmtPosts.bindString(35, post.getSecondaryTag());
                        }
                        if (!ListenerUtil.mutListener.listen(851)) {
                            stmtPosts.bindString(36, post.getAttachmentsJson());
                        }
                        if (!ListenerUtil.mutListener.listen(852)) {
                            stmtPosts.bindString(37, post.getDiscoverJson());
                        }
                        if (!ListenerUtil.mutListener.listen(853)) {
                            stmtPosts.bindLong(38, post.xpostPostId);
                        }
                        if (!ListenerUtil.mutListener.listen(854)) {
                            stmtPosts.bindLong(39, post.xpostBlogId);
                        }
                        if (!ListenerUtil.mutListener.listen(855)) {
                            stmtPosts.bindString(40, post.getRailcarJson());
                        }
                        if (!ListenerUtil.mutListener.listen(856)) {
                            stmtPosts.bindString(41, tagName);
                        }
                        if (!ListenerUtil.mutListener.listen(857)) {
                            stmtPosts.bindLong(42, tagType);
                        }
                        if (!ListenerUtil.mutListener.listen(858)) {
                            stmtPosts.bindLong(43, SqlUtils.boolToSql(hasGapMarker));
                        }
                        if (!ListenerUtil.mutListener.listen(859)) {
                            stmtPosts.bindString(44, ReaderCardType.toString(post.getCardType()));
                        }
                        if (!ListenerUtil.mutListener.listen(860)) {
                            stmtPosts.bindLong(45, SqlUtils.boolToSql(post.useExcerpt));
                        }
                        if (!ListenerUtil.mutListener.listen(861)) {
                            stmtPosts.bindLong(46, SqlUtils.boolToSql(post.isBookmarked));
                        }
                        if (!ListenerUtil.mutListener.listen(862)) {
                            stmtPosts.bindLong(47, SqlUtils.boolToSql(post.isPrivateAtomic));
                        }
                        if (!ListenerUtil.mutListener.listen(863)) {
                            stmtPosts.bindString(48, ReaderUtils.getCommaSeparatedTagSlugs(post.getTags()));
                        }
                        if (!ListenerUtil.mutListener.listen(864)) {
                            stmtPosts.bindLong(49, post.organizationId);
                        }
                        if (!ListenerUtil.mutListener.listen(865)) {
                            stmtPosts.bindLong(50, SqlUtils.boolToSql(post.isSeen));
                        }
                        if (!ListenerUtil.mutListener.listen(866)) {
                            stmtPosts.bindLong(51, SqlUtils.boolToSql(post.isSeenSupported));
                        }
                        if (!ListenerUtil.mutListener.listen(867)) {
                            stmtPosts.bindLong(52, post.authorBlogId);
                        }
                        if (!ListenerUtil.mutListener.listen(868)) {
                            stmtPosts.bindString(53, post.getAuthorBlogUrl());
                        }
                        if (!ListenerUtil.mutListener.listen(869)) {
                            stmtPosts.execute();
                        }
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(871)) {
                db.setTransactionSuccessful();
            }
            if (!ListenerUtil.mutListener.listen(872)) {
                EventBus.getDefault().post(ReaderPostTableActionEnded.INSTANCE);
            }
        } finally {
            if (!ListenerUtil.mutListener.listen(812)) {
                db.endTransaction();
            }
            if (!ListenerUtil.mutListener.listen(813)) {
                SqlUtils.closeStatement(stmtPosts);
            }
        }
    }

    public static ReaderPostList getPostsWithTag(ReaderTag tag, int maxPosts, boolean excludeTextColumn) {
        if (tag == null) {
            return new ReaderPostList();
        }
        String columns = (excludeTextColumn ? COLUMN_NAMES_NO_TEXT : "*");
        String sql = "SELECT " + columns + " FROM tbl_posts WHERE tag_name=? AND tag_type=?";
        if (!ListenerUtil.mutListener.listen(876)) {
            if (tag.tagType == ReaderTagType.DEFAULT) {
                if (!ListenerUtil.mutListener.listen(875)) {
                    // longer followed if this is "Followed Sites"
                    if (tag.isPostsILike()) {
                        if (!ListenerUtil.mutListener.listen(874)) {
                            sql += " AND is_liked != 0";
                        }
                    } else if (tag.isFollowedSites()) {
                        if (!ListenerUtil.mutListener.listen(873)) {
                            sql += " AND is_followed != 0";
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(877)) {
            sql += " ORDER BY " + getSortColumnForTag(tag) + " DESC";
        }
        if (!ListenerUtil.mutListener.listen(884)) {
            if ((ListenerUtil.mutListener.listen(882) ? (maxPosts >= 0) : (ListenerUtil.mutListener.listen(881) ? (maxPosts <= 0) : (ListenerUtil.mutListener.listen(880) ? (maxPosts < 0) : (ListenerUtil.mutListener.listen(879) ? (maxPosts != 0) : (ListenerUtil.mutListener.listen(878) ? (maxPosts == 0) : (maxPosts > 0))))))) {
                if (!ListenerUtil.mutListener.listen(883)) {
                    sql += " LIMIT " + maxPosts;
                }
            }
        }
        String[] args = { tag.getTagSlug(), Integer.toString(tag.tagType.toInt()) };
        Cursor cursor = ReaderDatabase.getReadableDb().rawQuery(sql, args);
        try {
            return getPostListFromCursor(cursor);
        } finally {
            if (!ListenerUtil.mutListener.listen(885)) {
                SqlUtils.closeCursor(cursor);
            }
        }
    }

    public static ReaderPostList getPostsInBlog(long blogId, int maxPosts, boolean excludeTextColumn) {
        String columns = (excludeTextColumn ? COLUMN_NAMES_NO_TEXT : "*");
        String sql = "SELECT " + columns + " FROM tbl_posts WHERE blog_id=? AND tag_name='' AND tag_type=0" + " ORDER BY date_published DESC";
        if (!ListenerUtil.mutListener.listen(892)) {
            if ((ListenerUtil.mutListener.listen(890) ? (maxPosts >= 0) : (ListenerUtil.mutListener.listen(889) ? (maxPosts <= 0) : (ListenerUtil.mutListener.listen(888) ? (maxPosts < 0) : (ListenerUtil.mutListener.listen(887) ? (maxPosts != 0) : (ListenerUtil.mutListener.listen(886) ? (maxPosts == 0) : (maxPosts > 0))))))) {
                if (!ListenerUtil.mutListener.listen(891)) {
                    sql += " LIMIT " + maxPosts;
                }
            }
        }
        Cursor cursor = ReaderDatabase.getReadableDb().rawQuery(sql, new String[] { Long.toString(blogId) });
        try {
            return getPostListFromCursor(cursor);
        } finally {
            if (!ListenerUtil.mutListener.listen(893)) {
                SqlUtils.closeCursor(cursor);
            }
        }
    }

    public static Map<Pair<String, ReaderTagType>, ReaderPostList> getTagPostMap(long blogId) {
        String sql = "SELECT * FROM tbl_posts WHERE blog_id=?";
        Cursor cursor = ReaderDatabase.getReadableDb().rawQuery(sql, new String[] { Long.toString(blogId) });
        try {
            return getTagPostMapFromCursor(cursor);
        } finally {
            if (!ListenerUtil.mutListener.listen(894)) {
                SqlUtils.closeCursor(cursor);
            }
        }
    }

    public static ReaderPostList getPostsInFeed(long feedId, int maxPosts, boolean excludeTextColumn) {
        String columns = (excludeTextColumn ? COLUMN_NAMES_NO_TEXT : "*");
        String sql = "SELECT " + columns + " FROM tbl_posts WHERE feed_id=? AND tag_name='' AND tag_type=0" + " ORDER BY date_published DESC";
        if (!ListenerUtil.mutListener.listen(901)) {
            if ((ListenerUtil.mutListener.listen(899) ? (maxPosts >= 0) : (ListenerUtil.mutListener.listen(898) ? (maxPosts <= 0) : (ListenerUtil.mutListener.listen(897) ? (maxPosts < 0) : (ListenerUtil.mutListener.listen(896) ? (maxPosts != 0) : (ListenerUtil.mutListener.listen(895) ? (maxPosts == 0) : (maxPosts > 0))))))) {
                if (!ListenerUtil.mutListener.listen(900)) {
                    sql += " LIMIT " + maxPosts;
                }
            }
        }
        Cursor cursor = ReaderDatabase.getReadableDb().rawQuery(sql, new String[] { Long.toString(feedId) });
        try {
            return getPostListFromCursor(cursor);
        } finally {
            if (!ListenerUtil.mutListener.listen(902)) {
                SqlUtils.closeCursor(cursor);
            }
        }
    }

    /*
     * same as getPostsWithTag() but only returns the blogId/postId pairs
     */
    public static ReaderBlogIdPostIdList getBlogIdPostIdsWithTag(ReaderTag tag, int maxPosts) {
        if (!ListenerUtil.mutListener.listen(903)) {
            if (tag == null) {
                return new ReaderBlogIdPostIdList();
            }
        }
        String sql = "SELECT blog_id, post_id FROM tbl_posts WHERE tag_name=? AND tag_type=?";
        if (!ListenerUtil.mutListener.listen(907)) {
            if (tag.tagType == ReaderTagType.DEFAULT) {
                if (!ListenerUtil.mutListener.listen(906)) {
                    if (tag.isPostsILike()) {
                        if (!ListenerUtil.mutListener.listen(905)) {
                            sql += " AND is_liked != 0";
                        }
                    } else if (tag.isFollowedSites()) {
                        if (!ListenerUtil.mutListener.listen(904)) {
                            sql += " AND is_followed != 0";
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(908)) {
            sql += " ORDER BY " + getSortColumnForTag(tag) + " DESC";
        }
        if (!ListenerUtil.mutListener.listen(915)) {
            if ((ListenerUtil.mutListener.listen(913) ? (maxPosts >= 0) : (ListenerUtil.mutListener.listen(912) ? (maxPosts <= 0) : (ListenerUtil.mutListener.listen(911) ? (maxPosts < 0) : (ListenerUtil.mutListener.listen(910) ? (maxPosts != 0) : (ListenerUtil.mutListener.listen(909) ? (maxPosts == 0) : (maxPosts > 0))))))) {
                if (!ListenerUtil.mutListener.listen(914)) {
                    sql += " LIMIT " + maxPosts;
                }
            }
        }
        String[] args = { tag.getTagSlug(), Integer.toString(tag.tagType.toInt()) };
        return getBlogIdPostIds(sql, args);
    }

    private static ReaderBlogIdPostIdList getBlogIdPostIdsWithTagType(ReaderTagType tagType, int maxPosts) {
        if (!ListenerUtil.mutListener.listen(916)) {
            if (tagType == null) {
                return new ReaderBlogIdPostIdList();
            }
        }
        String sql = "SELECT blog_id, post_id FROM tbl_posts WHERE tag_type=?";
        if (!ListenerUtil.mutListener.listen(923)) {
            if ((ListenerUtil.mutListener.listen(921) ? (maxPosts >= 0) : (ListenerUtil.mutListener.listen(920) ? (maxPosts <= 0) : (ListenerUtil.mutListener.listen(919) ? (maxPosts < 0) : (ListenerUtil.mutListener.listen(918) ? (maxPosts != 0) : (ListenerUtil.mutListener.listen(917) ? (maxPosts == 0) : (maxPosts > 0))))))) {
                if (!ListenerUtil.mutListener.listen(922)) {
                    sql += " LIMIT " + maxPosts;
                }
            }
        }
        String[] args = { Integer.toString(tagType.toInt()) };
        return getBlogIdPostIds(sql, args);
    }

    private static ReaderBlogIdPostIdList getBlogIdPostIds(@NonNull String sql, @NonNull String[] args) {
        ReaderBlogIdPostIdList idList = new ReaderBlogIdPostIdList();
        Cursor cursor = ReaderDatabase.getReadableDb().rawQuery(sql, args);
        try {
            if (!ListenerUtil.mutListener.listen(928)) {
                if ((ListenerUtil.mutListener.listen(925) ? (cursor != null || cursor.moveToFirst()) : (cursor != null && cursor.moveToFirst()))) {
                    if (!ListenerUtil.mutListener.listen(927)) {
                        {
                            long _loopCounter26 = 0;
                            do {
                                ListenerUtil.loopListener.listen("_loopCounter26", ++_loopCounter26);
                                if (!ListenerUtil.mutListener.listen(926)) {
                                    idList.add(new ReaderBlogIdPostId(cursor.getLong(0), cursor.getLong(1)));
                                }
                            } while (cursor.moveToNext());
                        }
                    }
                }
            }
            return idList;
        } finally {
            if (!ListenerUtil.mutListener.listen(924)) {
                SqlUtils.closeCursor(cursor);
            }
        }
    }

    /*
     * same as getPostsInBlog() but only returns the blogId/postId pairs
     */
    public static ReaderBlogIdPostIdList getBlogIdPostIdsInBlog(long blogId, int maxPosts) {
        String sql = "SELECT post_id FROM tbl_posts WHERE blog_id=? AND tag_name='' AND tag_type=0" + " ORDER BY date_published DESC";
        if (!ListenerUtil.mutListener.listen(935)) {
            if ((ListenerUtil.mutListener.listen(933) ? (maxPosts >= 0) : (ListenerUtil.mutListener.listen(932) ? (maxPosts <= 0) : (ListenerUtil.mutListener.listen(931) ? (maxPosts < 0) : (ListenerUtil.mutListener.listen(930) ? (maxPosts != 0) : (ListenerUtil.mutListener.listen(929) ? (maxPosts == 0) : (maxPosts > 0))))))) {
                if (!ListenerUtil.mutListener.listen(934)) {
                    sql += " LIMIT " + maxPosts;
                }
            }
        }
        Cursor cursor = ReaderDatabase.getReadableDb().rawQuery(sql, new String[] { Long.toString(blogId) });
        try {
            ReaderBlogIdPostIdList idList = new ReaderBlogIdPostIdList();
            if (!ListenerUtil.mutListener.listen(940)) {
                if ((ListenerUtil.mutListener.listen(937) ? (cursor != null || cursor.moveToFirst()) : (cursor != null && cursor.moveToFirst()))) {
                    if (!ListenerUtil.mutListener.listen(939)) {
                        {
                            long _loopCounter27 = 0;
                            do {
                                ListenerUtil.loopListener.listen("_loopCounter27", ++_loopCounter27);
                                if (!ListenerUtil.mutListener.listen(938)) {
                                    idList.add(new ReaderBlogIdPostId(blogId, cursor.getLong(0)));
                                }
                            } while (cursor.moveToNext());
                        }
                    }
                }
            }
            return idList;
        } finally {
            if (!ListenerUtil.mutListener.listen(936)) {
                SqlUtils.closeCursor(cursor);
            }
        }
    }

    private static Pair<String, ReaderTagType> getTagNameAndTypeFromCursor(Cursor c) {
        if (!ListenerUtil.mutListener.listen(941)) {
            if (c == null) {
                throw new IllegalArgumentException("getPostFromCursor > null cursor");
            }
        }
        return new Pair<>(c.getString(c.getColumnIndexOrThrow("tag_name")), ReaderTagType.fromInt(c.getInt(c.getColumnIndexOrThrow("tag_type"))));
    }

    private static ReaderPost getPostFromCursor(Cursor c) {
        if (!ListenerUtil.mutListener.listen(942)) {
            if (c == null) {
                throw new IllegalArgumentException("getPostFromCursor > null cursor");
            }
        }
        ReaderPost post = new ReaderPost();
        // text column is skipped when retrieving multiple rows
        int idxText = c.getColumnIndex("text");
        if (!ListenerUtil.mutListener.listen(949)) {
            if ((ListenerUtil.mutListener.listen(947) ? (idxText >= -1) : (ListenerUtil.mutListener.listen(946) ? (idxText <= -1) : (ListenerUtil.mutListener.listen(945) ? (idxText < -1) : (ListenerUtil.mutListener.listen(944) ? (idxText != -1) : (ListenerUtil.mutListener.listen(943) ? (idxText == -1) : (idxText > -1))))))) {
                if (!ListenerUtil.mutListener.listen(948)) {
                    post.setText(c.getString(idxText));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(950)) {
            post.postId = c.getLong(c.getColumnIndexOrThrow("post_id"));
        }
        if (!ListenerUtil.mutListener.listen(951)) {
            post.blogId = c.getLong(c.getColumnIndexOrThrow("blog_id"));
        }
        if (!ListenerUtil.mutListener.listen(952)) {
            post.feedId = c.getLong(c.getColumnIndexOrThrow("feed_id"));
        }
        if (!ListenerUtil.mutListener.listen(953)) {
            post.feedItemId = c.getLong(c.getColumnIndexOrThrow("feed_item_id"));
        }
        if (!ListenerUtil.mutListener.listen(954)) {
            post.authorId = c.getLong(c.getColumnIndexOrThrow("author_id"));
        }
        if (!ListenerUtil.mutListener.listen(955)) {
            post.setPseudoId(c.getString(c.getColumnIndexOrThrow("pseudo_id")));
        }
        if (!ListenerUtil.mutListener.listen(956)) {
            post.setAuthorName(c.getString(c.getColumnIndexOrThrow("author_name")));
        }
        if (!ListenerUtil.mutListener.listen(957)) {
            post.setAuthorFirstName(c.getString(c.getColumnIndexOrThrow("author_first_name")));
        }
        if (!ListenerUtil.mutListener.listen(958)) {
            post.setBlogName(c.getString(c.getColumnIndexOrThrow("blog_name")));
        }
        if (!ListenerUtil.mutListener.listen(959)) {
            post.setBlogUrl(c.getString(c.getColumnIndexOrThrow("blog_url")));
        }
        if (!ListenerUtil.mutListener.listen(960)) {
            post.setBlogImageUrl(c.getString(c.getColumnIndexOrThrow("blog_image_url")));
        }
        if (!ListenerUtil.mutListener.listen(961)) {
            post.setExcerpt(c.getString(c.getColumnIndexOrThrow("excerpt")));
        }
        if (!ListenerUtil.mutListener.listen(962)) {
            post.setFormat(c.getString(c.getColumnIndexOrThrow("format")));
        }
        if (!ListenerUtil.mutListener.listen(963)) {
            post.setFeaturedImage(c.getString(c.getColumnIndexOrThrow("featured_image")));
        }
        if (!ListenerUtil.mutListener.listen(964)) {
            post.setFeaturedVideo(c.getString(c.getColumnIndexOrThrow("featured_video")));
        }
        if (!ListenerUtil.mutListener.listen(965)) {
            post.setTitle(c.getString(c.getColumnIndexOrThrow("title")));
        }
        if (!ListenerUtil.mutListener.listen(966)) {
            post.setUrl(c.getString(c.getColumnIndexOrThrow("url")));
        }
        if (!ListenerUtil.mutListener.listen(967)) {
            post.setShortUrl(c.getString(c.getColumnIndexOrThrow("short_url")));
        }
        if (!ListenerUtil.mutListener.listen(968)) {
            post.setPostAvatar(c.getString(c.getColumnIndexOrThrow("post_avatar")));
        }
        if (!ListenerUtil.mutListener.listen(969)) {
            post.setDatePublished(c.getString(c.getColumnIndexOrThrow("date_published")));
        }
        if (!ListenerUtil.mutListener.listen(970)) {
            post.setDateLiked(c.getString(c.getColumnIndexOrThrow("date_liked")));
        }
        if (!ListenerUtil.mutListener.listen(971)) {
            post.setDateTagged(c.getString(c.getColumnIndexOrThrow("date_tagged")));
        }
        if (!ListenerUtil.mutListener.listen(972)) {
            post.score = c.getDouble(c.getColumnIndexOrThrow("score"));
        }
        if (!ListenerUtil.mutListener.listen(973)) {
            post.numReplies = c.getInt(c.getColumnIndexOrThrow("num_replies"));
        }
        if (!ListenerUtil.mutListener.listen(974)) {
            post.numLikes = c.getInt(c.getColumnIndexOrThrow("num_likes"));
        }
        if (!ListenerUtil.mutListener.listen(975)) {
            post.isLikedByCurrentUser = SqlUtils.sqlToBool(c.getInt(c.getColumnIndexOrThrow("is_liked")));
        }
        if (!ListenerUtil.mutListener.listen(976)) {
            post.isFollowedByCurrentUser = SqlUtils.sqlToBool(c.getInt(c.getColumnIndexOrThrow("is_followed")));
        }
        if (!ListenerUtil.mutListener.listen(977)) {
            post.isCommentsOpen = SqlUtils.sqlToBool(c.getInt(c.getColumnIndexOrThrow("is_comments_open")));
        }
        if (!ListenerUtil.mutListener.listen(978)) {
            post.isExternal = SqlUtils.sqlToBool(c.getInt(c.getColumnIndexOrThrow("is_external")));
        }
        if (!ListenerUtil.mutListener.listen(979)) {
            post.isPrivate = SqlUtils.sqlToBool(c.getInt(c.getColumnIndexOrThrow("is_private")));
        }
        if (!ListenerUtil.mutListener.listen(980)) {
            post.isPrivateAtomic = SqlUtils.sqlToBool(c.getInt(c.getColumnIndexOrThrow("is_private_atomic")));
        }
        if (!ListenerUtil.mutListener.listen(981)) {
            post.isVideoPress = SqlUtils.sqlToBool(c.getInt(c.getColumnIndexOrThrow("is_videopress")));
        }
        if (!ListenerUtil.mutListener.listen(982)) {
            post.isJetpack = SqlUtils.sqlToBool(c.getInt(c.getColumnIndexOrThrow("is_jetpack")));
        }
        if (!ListenerUtil.mutListener.listen(983)) {
            post.isBookmarked = SqlUtils.sqlToBool(c.getInt(c.getColumnIndexOrThrow("is_bookmarked")));
        }
        if (!ListenerUtil.mutListener.listen(984)) {
            post.setPrimaryTag(c.getString(c.getColumnIndexOrThrow("primary_tag")));
        }
        if (!ListenerUtil.mutListener.listen(985)) {
            post.setSecondaryTag(c.getString(c.getColumnIndexOrThrow("secondary_tag")));
        }
        if (!ListenerUtil.mutListener.listen(986)) {
            post.setAttachmentsJson(c.getString(c.getColumnIndexOrThrow("attachments_json")));
        }
        if (!ListenerUtil.mutListener.listen(987)) {
            post.setDiscoverJson(c.getString(c.getColumnIndexOrThrow("discover_json")));
        }
        if (!ListenerUtil.mutListener.listen(988)) {
            post.xpostPostId = c.getLong(c.getColumnIndexOrThrow("xpost_post_id"));
        }
        if (!ListenerUtil.mutListener.listen(989)) {
            post.xpostBlogId = c.getLong(c.getColumnIndexOrThrow("xpost_blog_id"));
        }
        if (!ListenerUtil.mutListener.listen(990)) {
            post.setRailcarJson(c.getString(c.getColumnIndexOrThrow("railcar_json")));
        }
        if (!ListenerUtil.mutListener.listen(991)) {
            post.setCardType(ReaderCardType.fromString(c.getString(c.getColumnIndexOrThrow("card_type"))));
        }
        if (!ListenerUtil.mutListener.listen(992)) {
            post.useExcerpt = SqlUtils.sqlToBool(c.getInt(c.getColumnIndexOrThrow("use_excerpt")));
        }
        if (!ListenerUtil.mutListener.listen(993)) {
            post.isSeen = SqlUtils.sqlToBool(c.getInt(c.getColumnIndexOrThrow("is_seen")));
        }
        if (!ListenerUtil.mutListener.listen(994)) {
            post.isSeenSupported = SqlUtils.sqlToBool(c.getInt(c.getColumnIndexOrThrow("is_seen_supported")));
        }
        String commaSeparatedTags = (c.getString(c.getColumnIndexOrThrow("tags")));
        if (!ListenerUtil.mutListener.listen(996)) {
            if (commaSeparatedTags != null) {
                if (!ListenerUtil.mutListener.listen(995)) {
                    post.setTags(ReaderUtils.getTagsFromCommaSeparatedSlugs(commaSeparatedTags));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(997)) {
            post.organizationId = c.getInt(c.getColumnIndexOrThrow("organization_id"));
        }
        if (!ListenerUtil.mutListener.listen(998)) {
            post.authorBlogId = c.getLong(c.getColumnIndexOrThrow("author_blog_id"));
        }
        if (!ListenerUtil.mutListener.listen(999)) {
            post.setAuthorBlogUrl(c.getString(c.getColumnIndexOrThrow("author_blog_url")));
        }
        return post;
    }

    private static ReaderPostList getPostListFromCursor(Cursor cursor) {
        ReaderPostList posts = new ReaderPostList();
        try {
            if (!ListenerUtil.mutListener.listen(1004)) {
                if ((ListenerUtil.mutListener.listen(1001) ? (cursor != null || cursor.moveToFirst()) : (cursor != null && cursor.moveToFirst()))) {
                    if (!ListenerUtil.mutListener.listen(1003)) {
                        {
                            long _loopCounter28 = 0;
                            do {
                                ListenerUtil.loopListener.listen("_loopCounter28", ++_loopCounter28);
                                if (!ListenerUtil.mutListener.listen(1002)) {
                                    posts.add(getPostFromCursor(cursor));
                                }
                            } while (cursor.moveToNext());
                        }
                    }
                }
            }
        } catch (IllegalStateException e) {
            if (!ListenerUtil.mutListener.listen(1000)) {
                AppLog.e(AppLog.T.READER, e);
            }
        }
        return posts;
    }

    private static Map<Pair<String, ReaderTagType>, ReaderPostList> getTagPostMapFromCursor(Cursor cursor) {
        Map<Pair<String, ReaderTagType>, ReaderPostList> posts = new LinkedHashMap<>();
        try {
            if (!ListenerUtil.mutListener.listen(1011)) {
                if ((ListenerUtil.mutListener.listen(1006) ? (cursor != null || cursor.moveToFirst()) : (cursor != null && cursor.moveToFirst()))) {
                    if (!ListenerUtil.mutListener.listen(1010)) {
                        {
                            long _loopCounter29 = 0;
                            do {
                                ListenerUtil.loopListener.listen("_loopCounter29", ++_loopCounter29);
                                ReaderPost post = getPostFromCursor(cursor);
                                Pair<String, ReaderTagType> tagNameAndType = getTagNameAndTypeFromCursor(cursor);
                                if (!ListenerUtil.mutListener.listen(1008)) {
                                    if (!posts.containsKey(tagNameAndType)) {
                                        if (!ListenerUtil.mutListener.listen(1007)) {
                                            posts.put(tagNameAndType, new ReaderPostList());
                                        }
                                    }
                                }
                                if (!ListenerUtil.mutListener.listen(1009)) {
                                    Objects.requireNonNull(posts.get(tagNameAndType)).add(post);
                                }
                            } while (cursor.moveToNext());
                        }
                    }
                }
            }
        } catch (IllegalStateException e) {
            if (!ListenerUtil.mutListener.listen(1005)) {
                AppLog.e(AppLog.T.READER, e);
            }
        }
        return posts;
    }

    /**
     * Currently "is_bookmarked" field is not supported by the server, therefore posts from the server have always
     * is_bookmarked set to false. This method is a workaround which makes sure, that the field is always up to date
     * and synced across all instances(rows) of each post.
     */
    private static void updateIsBookmarkedField(final ReaderPostList posts) {
        ReaderBlogIdPostIdList bookmarkedPosts = getBookmarkedPostIds();
        if (!ListenerUtil.mutListener.listen(1015)) {
            {
                long _loopCounter31 = 0;
                for (ReaderPost post : posts) {
                    ListenerUtil.loopListener.listen("_loopCounter31", ++_loopCounter31);
                    if (!ListenerUtil.mutListener.listen(1014)) {
                        {
                            long _loopCounter30 = 0;
                            for (ReaderBlogIdPostId bookmarkedPostId : bookmarkedPosts) {
                                ListenerUtil.loopListener.listen("_loopCounter30", ++_loopCounter30);
                                if (!ListenerUtil.mutListener.listen(1013)) {
                                    if (isBookmarkedPost(post, bookmarkedPostId))
                                        if (!ListenerUtil.mutListener.listen(1012)) {
                                            post.isBookmarked = true;
                                        }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private static boolean isBookmarkedPost(ReaderPost post, ReaderBlogIdPostId bookmarkedPostId) {
        return (ListenerUtil.mutListener.listen(1016) ? (post.blogId == bookmarkedPostId.getBlogId() || post.postId == bookmarkedPostId.getPostId()) : (post.blogId == bookmarkedPostId.getBlogId() && post.postId == bookmarkedPostId.getPostId()));
    }

    public static void updateBookmarkedPostPseudoId(final ReaderPostList posts) {
        ReaderBlogIdPostIdList bookmarkedPosts = getBookmarkedPostIds();
        if (!ListenerUtil.mutListener.listen(1020)) {
            {
                long _loopCounter33 = 0;
                for (ReaderPost post : posts) {
                    ListenerUtil.loopListener.listen("_loopCounter33", ++_loopCounter33);
                    if (!ListenerUtil.mutListener.listen(1019)) {
                        {
                            long _loopCounter32 = 0;
                            for (ReaderBlogIdPostId bookmarkedPostId : bookmarkedPosts) {
                                ListenerUtil.loopListener.listen("_loopCounter32", ++_loopCounter32);
                                if (!ListenerUtil.mutListener.listen(1018)) {
                                    if (isBookmarkedPost(post, bookmarkedPostId))
                                        if (!ListenerUtil.mutListener.listen(1017)) {
                                            updateBookmarkedPostPseudoId(post, bookmarkedPostId);
                                        }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public static void updateBookmarkedPostPseudoId(ReaderPost post, ReaderBlogIdPostId bookmarkedPostIds) {
        ReaderPost bookmarkedPost = getBlogPost(bookmarkedPostIds.getBlogId(), bookmarkedPostIds.getPostId(), true);
        if (!ListenerUtil.mutListener.listen(1026)) {
            if ((ListenerUtil.mutListener.listen(1021) ? (bookmarkedPost != null || !bookmarkedPost.getPseudoId().equals(post.getPseudoId())) : (bookmarkedPost != null && !bookmarkedPost.getPseudoId().equals(post.getPseudoId())))) {
                SQLiteDatabase db = ReaderDatabase.getWritableDb();
                if (!ListenerUtil.mutListener.listen(1022)) {
                    db.beginTransaction();
                }
                try {
                    String sql = "UPDATE tbl_posts SET pseudo_id=? WHERE blog_id=? AND post_id=? AND tag_type=?";
                    if (!ListenerUtil.mutListener.listen(1024)) {
                        db.execSQL(sql, new String[] { post.getPseudoId(), Long.toString(post.blogId), Long.toString(post.postId), Integer.toString(ReaderTagType.BOOKMARKED.toInt()) });
                    }
                    if (!ListenerUtil.mutListener.listen(1025)) {
                        db.setTransactionSuccessful();
                    }
                } finally {
                    if (!ListenerUtil.mutListener.listen(1023)) {
                        db.endTransaction();
                    }
                }
            }
        }
    }

    private static ReaderBlogIdPostIdList getBookmarkedPostIds() {
        return getBlogIdPostIdsWithTagType(ReaderTagType.BOOKMARKED, 99999);
    }
}
