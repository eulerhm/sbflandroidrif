package org.wordpress.android.datasets;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import org.greenrobot.eventbus.EventBus;
import org.wordpress.android.WordPress;
import org.wordpress.android.models.ReaderPostList;
import org.wordpress.android.models.ReaderTag;
import org.wordpress.android.models.ReaderTagList;
import org.wordpress.android.models.ReaderTagType;
import org.wordpress.android.ui.reader.repository.ReaderRepositoryEvent.ReaderPostTableActionEnded;
import org.wordpress.android.ui.reader.utils.ReaderUtils;
import org.wordpress.android.util.AppLog;
import org.wordpress.android.util.AppLog.T;
import org.wordpress.android.util.DateTimeUtils;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Date;
import java.util.Locale;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * database for all reader information
 */
public class ReaderDatabase extends SQLiteOpenHelper {

    protected static final String DB_NAME = "wpreader.db";

    private static final int DB_VERSION = 153;

    // do not change this value
    private static final int DB_LAST_VERSION_WITHOUT_MIGRATION_SCRIPT = 136;

    /*
     * database singleton
     */
    private static ReaderDatabase mReaderDb;

    private static final Object DB_LOCK = new Object();

    public static ReaderDatabase getDatabase() {
        if (!ListenerUtil.mutListener.listen(424)) {
            if (mReaderDb == null) {
                synchronized (DB_LOCK) {
                    if (!ListenerUtil.mutListener.listen(423)) {
                        if (mReaderDb == null) {
                            if (!ListenerUtil.mutListener.listen(421)) {
                                mReaderDb = new ReaderDatabase(WordPress.getContext());
                            }
                            if (!ListenerUtil.mutListener.listen(422)) {
                                // (open will fail if app calls getReadableDb() first)
                                mReaderDb.getWritableDatabase();
                            }
                        }
                    }
                }
            }
        }
        return mReaderDb;
    }

    public static SQLiteDatabase getReadableDb() {
        return getDatabase().getReadableDatabase();
    }

    public static SQLiteDatabase getWritableDb() {
        return getDatabase().getWritableDatabase();
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        if (!ListenerUtil.mutListener.listen(425)) {
            super.onOpen(db);
        }
    }

    /*
     * resets (clears) the reader database
     */
    public static void reset(boolean retainBookmarkedPosts) {
        // object hasn't been created yet
        SQLiteDatabase db = getWritableDb();
        if (!ListenerUtil.mutListener.listen(433)) {
            if ((ListenerUtil.mutListener.listen(426) ? (retainBookmarkedPosts || ReaderPostTable.hasBookmarkedPosts()) : (retainBookmarkedPosts && ReaderPostTable.hasBookmarkedPosts()))) {
                ReaderTagList tags = ReaderTagTable.getBookmarkTags();
                if (!ListenerUtil.mutListener.listen(432)) {
                    if (!tags.isEmpty()) {
                        ReaderPostList bookmarkedPosts = ReaderPostTable.getPostsWithTag(tags.get(0), 0, false);
                        if (!ListenerUtil.mutListener.listen(427)) {
                            db.beginTransaction();
                        }
                        try {
                            if (!ListenerUtil.mutListener.listen(429)) {
                                getDatabase().reset(db);
                            }
                            if (!ListenerUtil.mutListener.listen(430)) {
                                ReaderPostTable.addOrUpdatePosts(tags.get(0), bookmarkedPosts);
                            }
                            if (!ListenerUtil.mutListener.listen(431)) {
                                db.setTransactionSuccessful();
                            }
                        } finally {
                            if (!ListenerUtil.mutListener.listen(428)) {
                                db.endTransaction();
                            }
                        }
                        return;
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(434)) {
            getDatabase().reset(db);
        }
    }

    public ReaderDatabase(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        if (!ListenerUtil.mutListener.listen(435)) {
            createAllTables(db);
        }
    }

    @SuppressWarnings({ "FallThrough" })
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (!ListenerUtil.mutListener.listen(436)) {
            AppLog.i(T.READER, "Upgrading database from version " + oldVersion + " to version " + newVersion + " IN PROGRESS");
        }
        int currentVersion = oldVersion;
        if (!ListenerUtil.mutListener.listen(444)) {
            if ((ListenerUtil.mutListener.listen(441) ? (currentVersion >= DB_LAST_VERSION_WITHOUT_MIGRATION_SCRIPT) : (ListenerUtil.mutListener.listen(440) ? (currentVersion > DB_LAST_VERSION_WITHOUT_MIGRATION_SCRIPT) : (ListenerUtil.mutListener.listen(439) ? (currentVersion < DB_LAST_VERSION_WITHOUT_MIGRATION_SCRIPT) : (ListenerUtil.mutListener.listen(438) ? (currentVersion != DB_LAST_VERSION_WITHOUT_MIGRATION_SCRIPT) : (ListenerUtil.mutListener.listen(437) ? (currentVersion == DB_LAST_VERSION_WITHOUT_MIGRATION_SCRIPT) : (currentVersion <= DB_LAST_VERSION_WITHOUT_MIGRATION_SCRIPT))))))) {
                if (!ListenerUtil.mutListener.listen(442)) {
                    // versions 0 - 136 didn't support migration scripts, so we can safely drop and recreate all tables
                    reset(db);
                }
                if (!ListenerUtil.mutListener.listen(443)) {
                    currentVersion = newVersion;
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(480)) {
            switch(currentVersion) {
                case 136:
                    if (!ListenerUtil.mutListener.listen(445)) {
                        // no-op
                        currentVersion++;
                    }
                case 137:
                    if (!ListenerUtil.mutListener.listen(446)) {
                        db.execSQL("ALTER TABLE tbl_posts ADD is_private_atomic BOOLEAN;");
                    }
                    if (!ListenerUtil.mutListener.listen(447)) {
                        currentVersion++;
                    }
                case 138:
                    if (!ListenerUtil.mutListener.listen(448)) {
                        ReaderDiscoverCardsTable.INSTANCE.createTable(db);
                    }
                    if (!ListenerUtil.mutListener.listen(449)) {
                        currentVersion++;
                    }
                case 139:
                    if (!ListenerUtil.mutListener.listen(450)) {
                        db.execSQL("DROP TABLE IF EXISTS tbl_tags_recommended;");
                    }
                    if (!ListenerUtil.mutListener.listen(451)) {
                        currentVersion++;
                    }
                case 140:
                    if (!ListenerUtil.mutListener.listen(452)) {
                        db.execSQL("ALTER TABLE tbl_posts ADD tags TEXT;");
                    }
                    if (!ListenerUtil.mutListener.listen(453)) {
                        currentVersion++;
                    }
                case 141:
                    String[] args = { Integer.toString(ReaderTagType.FOLLOWED.toInt()) };
                    if (!ListenerUtil.mutListener.listen(454)) {
                        db.execSQL("DELETE FROM tbl_tags WHERE tag_type=?", args);
                    }
                    if (!ListenerUtil.mutListener.listen(455)) {
                        currentVersion++;
                    }
                case 142:
                    if (!ListenerUtil.mutListener.listen(456)) {
                        db.execSQL("DROP TABLE IF EXISTS tbl_recommended_blogs;");
                    }
                    if (!ListenerUtil.mutListener.listen(457)) {
                        currentVersion++;
                    }
                case 143:
                    if (!ListenerUtil.mutListener.listen(458)) {
                        // removed additions of deprecated tbl_posts.is_wpforteams_site
                        currentVersion++;
                    }
                case 144:
                    if (!ListenerUtil.mutListener.listen(459)) {
                        // removed additions of deprecated tbl_blog_info.is_wp_for_teams
                        currentVersion++;
                    }
                case 145:
                    if (!ListenerUtil.mutListener.listen(460)) {
                        db.execSQL("ALTER TABLE tbl_blog_info ADD organization_id INTEGER;");
                    }
                    if (!ListenerUtil.mutListener.listen(461)) {
                        db.execSQL("ALTER TABLE tbl_posts ADD organization_id INTEGER;");
                    }
                    if (!ListenerUtil.mutListener.listen(462)) {
                        currentVersion++;
                    }
                case 146:
                    if (!ListenerUtil.mutListener.listen(463)) {
                        db.execSQL("ALTER TABLE tbl_blog_info ADD unseen_count INTEGER;");
                    }
                    if (!ListenerUtil.mutListener.listen(464)) {
                        currentVersion++;
                    }
                case 147:
                    if (!ListenerUtil.mutListener.listen(465)) {
                        db.execSQL("ALTER TABLE tbl_posts ADD is_seen BOOLEAN;");
                    }
                    if (!ListenerUtil.mutListener.listen(466)) {
                        currentVersion++;
                    }
                case 148:
                    if (!ListenerUtil.mutListener.listen(467)) {
                        db.execSQL("ALTER TABLE tbl_posts ADD is_seen_supported BOOLEAN;");
                    }
                    if (!ListenerUtil.mutListener.listen(468)) {
                        currentVersion++;
                    }
                case 149:
                    if (!ListenerUtil.mutListener.listen(469)) {
                        db.execSQL("ALTER TABLE tbl_posts ADD author_blog_id INTEGER;");
                    }
                    if (!ListenerUtil.mutListener.listen(470)) {
                        db.execSQL("ALTER TABLE tbl_posts ADD author_blog_url TEXT;");
                    }
                    if (!ListenerUtil.mutListener.listen(471)) {
                        currentVersion++;
                    }
                case 150:
                    String followedSitesTagSlug = ReaderUtils.sanitizeWithDashes(ReaderTag.TAG_TITLE_FOLLOWED_SITES);
                    if (!ListenerUtil.mutListener.listen(472)) {
                        db.execSQL("DELETE FROM tbl_posts WHERE tag_name=?", new String[] { followedSitesTagSlug });
                    }
                    if (!ListenerUtil.mutListener.listen(473)) {
                        db.execSQL("DELETE FROM tbl_posts WHERE tag_name='' AND tag_type=0");
                    }
                    if (!ListenerUtil.mutListener.listen(474)) {
                        db.execSQL("UPDATE tbl_tags SET date_updated=? WHERE tag_slug=? AND tag_type=?", new String[] { DateTimeUtils.iso8601FromDate(new Date(0)), followedSitesTagSlug, Integer.toString(ReaderTagType.DEFAULT.toInt()) });
                    }
                    if (!ListenerUtil.mutListener.listen(475)) {
                        currentVersion++;
                    }
                case 151:
                    if (!ListenerUtil.mutListener.listen(476)) {
                        db.execSQL("ALTER TABLE tbl_comments ADD short_url TEXT;");
                    }
                    if (!ListenerUtil.mutListener.listen(477)) {
                        currentVersion++;
                    }
                case 152:
                    if (!ListenerUtil.mutListener.listen(478)) {
                        db.execSQL("ALTER TABLE tbl_comments ADD author_email TEXT;");
                    }
                    if (!ListenerUtil.mutListener.listen(479)) {
                        currentVersion++;
                    }
            }
        }
        if (!ListenerUtil.mutListener.listen(486)) {
            if ((ListenerUtil.mutListener.listen(485) ? (currentVersion >= newVersion) : (ListenerUtil.mutListener.listen(484) ? (currentVersion <= newVersion) : (ListenerUtil.mutListener.listen(483) ? (currentVersion > newVersion) : (ListenerUtil.mutListener.listen(482) ? (currentVersion < newVersion) : (ListenerUtil.mutListener.listen(481) ? (currentVersion == newVersion) : (currentVersion != newVersion))))))) {
                throw new RuntimeException("Migration from version " + oldVersion + " to version " + newVersion + " FAILED. ");
            }
        }
        if (!ListenerUtil.mutListener.listen(487)) {
            AppLog.i(T.READER, "Upgrading database from version " + oldVersion + " to version " + newVersion + " SUCCEEDED");
        }
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (!ListenerUtil.mutListener.listen(488)) {
            // IMPORTANT: do NOT call super() here - doing so throws a SQLiteException
            AppLog.w(T.READER, "Downgrading database from version " + oldVersion + " to version " + newVersion);
        }
        if (!ListenerUtil.mutListener.listen(489)) {
            reset(db);
        }
    }

    private void createAllTables(SQLiteDatabase db) {
        if (!ListenerUtil.mutListener.listen(490)) {
            ReaderCommentTable.createTables(db);
        }
        if (!ListenerUtil.mutListener.listen(491)) {
            ReaderLikeTable.createTables(db);
        }
        if (!ListenerUtil.mutListener.listen(492)) {
            ReaderPostTable.createTables(db);
        }
        if (!ListenerUtil.mutListener.listen(493)) {
            ReaderTagTable.createTables(db);
        }
        if (!ListenerUtil.mutListener.listen(494)) {
            ReaderUserTable.createTables(db);
        }
        if (!ListenerUtil.mutListener.listen(495)) {
            ReaderThumbnailTable.createTables(db);
        }
        if (!ListenerUtil.mutListener.listen(496)) {
            ReaderBlogTable.createTables(db);
        }
        if (!ListenerUtil.mutListener.listen(497)) {
            ReaderSearchTable.createTables(db);
        }
        if (!ListenerUtil.mutListener.listen(498)) {
            ReaderDiscoverCardsTable.INSTANCE.createTable(db);
        }
    }

    private void dropAllTables(SQLiteDatabase db) {
        if (!ListenerUtil.mutListener.listen(499)) {
            ReaderCommentTable.dropTables(db);
        }
        if (!ListenerUtil.mutListener.listen(500)) {
            ReaderLikeTable.dropTables(db);
        }
        if (!ListenerUtil.mutListener.listen(501)) {
            ReaderPostTable.dropTables(db);
        }
        if (!ListenerUtil.mutListener.listen(502)) {
            ReaderTagTable.dropTables(db);
        }
        if (!ListenerUtil.mutListener.listen(503)) {
            ReaderUserTable.dropTables(db);
        }
        if (!ListenerUtil.mutListener.listen(504)) {
            ReaderThumbnailTable.dropTables(db);
        }
        if (!ListenerUtil.mutListener.listen(505)) {
            ReaderBlogTable.dropTables(db);
        }
        if (!ListenerUtil.mutListener.listen(506)) {
            ReaderSearchTable.dropTables(db);
        }
        if (!ListenerUtil.mutListener.listen(507)) {
            ReaderDiscoverCardsTable.INSTANCE.dropTables(db);
        }
    }

    /*
     * drop & recreate all tables (essentially clears the db of all data)
     */
    private void reset(SQLiteDatabase db) {
        if (!ListenerUtil.mutListener.listen(508)) {
            db.beginTransaction();
        }
        try {
            if (!ListenerUtil.mutListener.listen(510)) {
                dropAllTables(db);
            }
            if (!ListenerUtil.mutListener.listen(511)) {
                createAllTables(db);
            }
            if (!ListenerUtil.mutListener.listen(512)) {
                db.setTransactionSuccessful();
            }
        } finally {
            if (!ListenerUtil.mutListener.listen(509)) {
                db.endTransaction();
            }
        }
    }

    /*
     * purge older/unattached data - use purgeAsync() to do this in the background
     */
    private static void purge() {
        SQLiteDatabase db = getWritableDb();
        if (!ListenerUtil.mutListener.listen(513)) {
            db.beginTransaction();
        }
        try {
            int numPostsDeleted = ReaderPostTable.purge(db);
            if (!ListenerUtil.mutListener.listen(542)) {
                // don't bother purging other data unless posts were purged
                if ((ListenerUtil.mutListener.listen(519) ? (numPostsDeleted >= 0) : (ListenerUtil.mutListener.listen(518) ? (numPostsDeleted <= 0) : (ListenerUtil.mutListener.listen(517) ? (numPostsDeleted < 0) : (ListenerUtil.mutListener.listen(516) ? (numPostsDeleted != 0) : (ListenerUtil.mutListener.listen(515) ? (numPostsDeleted == 0) : (numPostsDeleted > 0))))))) {
                    if (!ListenerUtil.mutListener.listen(520)) {
                        AppLog.i(T.READER, String.format(Locale.ENGLISH, "%d total posts purged", numPostsDeleted));
                    }
                    // purge unattached comments
                    int numCommentsDeleted = ReaderCommentTable.purge(db);
                    if (!ListenerUtil.mutListener.listen(527)) {
                        if ((ListenerUtil.mutListener.listen(525) ? (numCommentsDeleted >= 0) : (ListenerUtil.mutListener.listen(524) ? (numCommentsDeleted <= 0) : (ListenerUtil.mutListener.listen(523) ? (numCommentsDeleted < 0) : (ListenerUtil.mutListener.listen(522) ? (numCommentsDeleted != 0) : (ListenerUtil.mutListener.listen(521) ? (numCommentsDeleted == 0) : (numCommentsDeleted > 0))))))) {
                            if (!ListenerUtil.mutListener.listen(526)) {
                                AppLog.i(T.READER, String.format(Locale.ENGLISH, "%d comments purged", numCommentsDeleted));
                            }
                        }
                    }
                    // purge unattached likes
                    int numLikesDeleted = ReaderLikeTable.purge(db);
                    if (!ListenerUtil.mutListener.listen(534)) {
                        if ((ListenerUtil.mutListener.listen(532) ? (numLikesDeleted >= 0) : (ListenerUtil.mutListener.listen(531) ? (numLikesDeleted <= 0) : (ListenerUtil.mutListener.listen(530) ? (numLikesDeleted < 0) : (ListenerUtil.mutListener.listen(529) ? (numLikesDeleted != 0) : (ListenerUtil.mutListener.listen(528) ? (numLikesDeleted == 0) : (numLikesDeleted > 0))))))) {
                            if (!ListenerUtil.mutListener.listen(533)) {
                                AppLog.i(T.READER, String.format(Locale.ENGLISH, "%d likes purged", numLikesDeleted));
                            }
                        }
                    }
                    // purge unattached thumbnails
                    int numThumbsPurged = ReaderThumbnailTable.purge(db);
                    if (!ListenerUtil.mutListener.listen(541)) {
                        if ((ListenerUtil.mutListener.listen(539) ? (numThumbsPurged >= 0) : (ListenerUtil.mutListener.listen(538) ? (numThumbsPurged <= 0) : (ListenerUtil.mutListener.listen(537) ? (numThumbsPurged < 0) : (ListenerUtil.mutListener.listen(536) ? (numThumbsPurged != 0) : (ListenerUtil.mutListener.listen(535) ? (numThumbsPurged == 0) : (numThumbsPurged > 0))))))) {
                            if (!ListenerUtil.mutListener.listen(540)) {
                                AppLog.i(T.READER, String.format(Locale.ENGLISH, "%d thumbnails purged", numThumbsPurged));
                            }
                        }
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(543)) {
                db.setTransactionSuccessful();
            }
            if (!ListenerUtil.mutListener.listen(550)) {
                if ((ListenerUtil.mutListener.listen(548) ? (numPostsDeleted >= 0) : (ListenerUtil.mutListener.listen(547) ? (numPostsDeleted <= 0) : (ListenerUtil.mutListener.listen(546) ? (numPostsDeleted < 0) : (ListenerUtil.mutListener.listen(545) ? (numPostsDeleted != 0) : (ListenerUtil.mutListener.listen(544) ? (numPostsDeleted == 0) : (numPostsDeleted > 0))))))) {
                    if (!ListenerUtil.mutListener.listen(549)) {
                        EventBus.getDefault().post(ReaderPostTableActionEnded.INSTANCE);
                    }
                }
            }
        } finally {
            if (!ListenerUtil.mutListener.listen(514)) {
                db.endTransaction();
            }
        }
    }

    public static void purgeAsync() {
        if (!ListenerUtil.mutListener.listen(552)) {
            new Thread() {

                @Override
                public void run() {
                    if (!ListenerUtil.mutListener.listen(551)) {
                        purge();
                    }
                }
            }.start();
        }
    }

    /*
     * used during development to copy database to external storage so we can access it via DDMS
     */
    private void copyDatabase(SQLiteDatabase db) {
        String copyFrom = db.getPath();
        String copyTo = WordPress.getContext().getExternalFilesDir(null).getAbsolutePath() + "/" + DB_NAME;
        try {
            InputStream input = new FileInputStream(copyFrom);
            OutputStream output = new FileOutputStream(copyTo);
            byte[] buffer = new byte[1024];
            int length;
            if (!ListenerUtil.mutListener.listen(560)) {
                {
                    long _loopCounter16 = 0;
                    while ((ListenerUtil.mutListener.listen(559) ? ((length = input.read(buffer)) >= 0) : (ListenerUtil.mutListener.listen(558) ? ((length = input.read(buffer)) <= 0) : (ListenerUtil.mutListener.listen(557) ? ((length = input.read(buffer)) < 0) : (ListenerUtil.mutListener.listen(556) ? ((length = input.read(buffer)) != 0) : (ListenerUtil.mutListener.listen(555) ? ((length = input.read(buffer)) == 0) : ((length = input.read(buffer)) > 0))))))) {
                        ListenerUtil.loopListener.listen("_loopCounter16", ++_loopCounter16);
                        if (!ListenerUtil.mutListener.listen(554)) {
                            output.write(buffer, 0, length);
                        }
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(561)) {
                output.flush();
            }
            if (!ListenerUtil.mutListener.listen(562)) {
                output.close();
            }
            if (!ListenerUtil.mutListener.listen(563)) {
                input.close();
            }
        } catch (IOException e) {
            if (!ListenerUtil.mutListener.listen(553)) {
                AppLog.e(T.DB, "failed to copy reader database", e);
            }
        }
    }
}
