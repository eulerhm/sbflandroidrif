/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
 * Copyright (c) 2015-2021 Threema GmbH
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
package ch.threema.storage;

import android.content.Context;
import android.text.format.DateUtils;
import android.widget.Toast;
import net.sqlcipher.DatabaseErrorHandler;
import net.sqlcipher.database.SQLiteDatabase;
import net.sqlcipher.database.SQLiteDatabaseHook;
import net.sqlcipher.database.SQLiteException;
import net.sqlcipher.database.SQLiteOpenHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.File;
import java.io.IOException;
import androidx.annotation.MainThread;
import ch.threema.app.exceptions.DatabaseMigrationFailedException;
import ch.threema.app.exceptions.DatabaseMigrationLockedException;
import ch.threema.app.services.UpdateSystemService;
import ch.threema.app.services.systemupdate.SystemUpdateToVersion10;
import ch.threema.app.services.systemupdate.SystemUpdateToVersion11;
import ch.threema.app.services.systemupdate.SystemUpdateToVersion12;
import ch.threema.app.services.systemupdate.SystemUpdateToVersion13;
import ch.threema.app.services.systemupdate.SystemUpdateToVersion14;
import ch.threema.app.services.systemupdate.SystemUpdateToVersion15;
import ch.threema.app.services.systemupdate.SystemUpdateToVersion16;
import ch.threema.app.services.systemupdate.SystemUpdateToVersion17;
import ch.threema.app.services.systemupdate.SystemUpdateToVersion18;
import ch.threema.app.services.systemupdate.SystemUpdateToVersion19;
import ch.threema.app.services.systemupdate.SystemUpdateToVersion20;
import ch.threema.app.services.systemupdate.SystemUpdateToVersion21;
import ch.threema.app.services.systemupdate.SystemUpdateToVersion23;
import ch.threema.app.services.systemupdate.SystemUpdateToVersion24;
import ch.threema.app.services.systemupdate.SystemUpdateToVersion25;
import ch.threema.app.services.systemupdate.SystemUpdateToVersion27;
import ch.threema.app.services.systemupdate.SystemUpdateToVersion28;
import ch.threema.app.services.systemupdate.SystemUpdateToVersion30;
import ch.threema.app.services.systemupdate.SystemUpdateToVersion31;
import ch.threema.app.services.systemupdate.SystemUpdateToVersion32;
import ch.threema.app.services.systemupdate.SystemUpdateToVersion33;
import ch.threema.app.services.systemupdate.SystemUpdateToVersion34;
import ch.threema.app.services.systemupdate.SystemUpdateToVersion35;
import ch.threema.app.services.systemupdate.SystemUpdateToVersion36;
import ch.threema.app.services.systemupdate.SystemUpdateToVersion37;
import ch.threema.app.services.systemupdate.SystemUpdateToVersion38;
import ch.threema.app.services.systemupdate.SystemUpdateToVersion39;
import ch.threema.app.services.systemupdate.SystemUpdateToVersion4;
import ch.threema.app.services.systemupdate.SystemUpdateToVersion40;
import ch.threema.app.services.systemupdate.SystemUpdateToVersion41;
import ch.threema.app.services.systemupdate.SystemUpdateToVersion42;
import ch.threema.app.services.systemupdate.SystemUpdateToVersion43;
import ch.threema.app.services.systemupdate.SystemUpdateToVersion44;
import ch.threema.app.services.systemupdate.SystemUpdateToVersion45;
import ch.threema.app.services.systemupdate.SystemUpdateToVersion46;
import ch.threema.app.services.systemupdate.SystemUpdateToVersion47;
import ch.threema.app.services.systemupdate.SystemUpdateToVersion48;
import ch.threema.app.services.systemupdate.SystemUpdateToVersion49;
import ch.threema.app.services.systemupdate.SystemUpdateToVersion50;
import ch.threema.app.services.systemupdate.SystemUpdateToVersion51;
import ch.threema.app.services.systemupdate.SystemUpdateToVersion52;
import ch.threema.app.services.systemupdate.SystemUpdateToVersion53;
import ch.threema.app.services.systemupdate.SystemUpdateToVersion54;
import ch.threema.app.services.systemupdate.SystemUpdateToVersion55;
import ch.threema.app.services.systemupdate.SystemUpdateToVersion56;
import ch.threema.app.services.systemupdate.SystemUpdateToVersion58;
import ch.threema.app.services.systemupdate.SystemUpdateToVersion59;
import ch.threema.app.services.systemupdate.SystemUpdateToVersion6;
import ch.threema.app.services.systemupdate.SystemUpdateToVersion60;
import ch.threema.app.services.systemupdate.SystemUpdateToVersion61;
import ch.threema.app.services.systemupdate.SystemUpdateToVersion62;
import ch.threema.app.services.systemupdate.SystemUpdateToVersion63;
import ch.threema.app.services.systemupdate.SystemUpdateToVersion64;
import ch.threema.app.services.systemupdate.SystemUpdateToVersion65;
import ch.threema.app.services.systemupdate.SystemUpdateToVersion7;
import ch.threema.app.services.systemupdate.SystemUpdateToVersion8;
import ch.threema.app.services.systemupdate.SystemUpdateToVersion9;
import ch.threema.app.utils.FileUtil;
import ch.threema.app.utils.RuntimeUtil;
import ch.threema.app.utils.TestUtil;
import ch.threema.storage.factories.BallotChoiceModelFactory;
import ch.threema.storage.factories.BallotModelFactory;
import ch.threema.storage.factories.BallotVoteModelFactory;
import ch.threema.storage.factories.ContactModelFactory;
import ch.threema.storage.factories.ConversationTagFactory;
import ch.threema.storage.factories.DistributionListMemberModelFactory;
import ch.threema.storage.factories.DistributionListMessageModelFactory;
import ch.threema.storage.factories.DistributionListModelFactory;
import ch.threema.storage.factories.GroupBallotModelFactory;
import ch.threema.storage.factories.GroupMemberModelFactory;
import ch.threema.storage.factories.GroupMessageModelFactory;
import ch.threema.storage.factories.GroupMessagePendingMessageIdModelFactory;
import ch.threema.storage.factories.GroupModelFactory;
import ch.threema.storage.factories.GroupRequestSyncLogModelFactory;
import ch.threema.storage.factories.IdentityBallotModelFactory;
import ch.threema.storage.factories.MessageModelFactory;
import ch.threema.storage.factories.ModelFactory;
import ch.threema.storage.factories.WebClientSessionModelFactory;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class DatabaseServiceNew extends SQLiteOpenHelper {

    private static final Logger logger = LoggerFactory.getLogger(DatabaseServiceNew.class);

    public static final String DATABASE_NAME = "threema.db";

    public static final String DATABASE_NAME_V4 = "threema4.db";

    public static final String DATABASE_BACKUP_EXT = ".backup";

    private static final int DATABASE_VERSION = 65;

    private final Context context;

    private final String key;

    private final UpdateSystemService updateSystemService;

    private ContactModelFactory contactModelFactory;

    private MessageModelFactory messageModelFactory;

    private GroupModelFactory groupModelFactory;

    private GroupMemberModelFactory groupMemberModelFactory;

    private GroupMessageModelFactory groupMessageModelFactory;

    private DistributionListModelFactory distributionListModelFactory;

    private DistributionListMemberModelFactory distributionListMemberModelFactory;

    private DistributionListMessageModelFactory distributionListMessageModelFactory;

    private GroupRequestSyncLogModelFactory groupRequestSyncLogModelFactory;

    private BallotModelFactory ballotModelFactory;

    private BallotChoiceModelFactory ballotChoiceModelFactory;

    private BallotVoteModelFactory ballotVoteModelFactory;

    private IdentityBallotModelFactory identityBallotModelFactory;

    private GroupBallotModelFactory groupBallotModelFactory;

    private GroupMessagePendingMessageIdModelFactory groupMessagePendingMessageIdModelFactory;

    private WebClientSessionModelFactory webClientSessionModelFactory;

    private ConversationTagFactory conversationTagFactory;

    public DatabaseServiceNew(final Context context, final String databaseKey, UpdateSystemService updateSystemService, int sqlcipherVersion) {
        super(context, (ListenerUtil.mutListener.listen(71059) ? (sqlcipherVersion >= 4) : (ListenerUtil.mutListener.listen(71058) ? (sqlcipherVersion <= 4) : (ListenerUtil.mutListener.listen(71057) ? (sqlcipherVersion > 4) : (ListenerUtil.mutListener.listen(71056) ? (sqlcipherVersion < 4) : (ListenerUtil.mutListener.listen(71055) ? (sqlcipherVersion != 4) : (sqlcipherVersion == 4)))))) ? DATABASE_NAME_V4 : DATABASE_NAME, null, DATABASE_VERSION, new SQLiteDatabaseHook() {

            @Override
            public void preKey(SQLiteDatabase sqLiteDatabase) {
                if (!ListenerUtil.mutListener.listen(71062)) {
                    if (sqlcipherVersion == 4) {
                        if (!ListenerUtil.mutListener.listen(71061)) {
                            sqLiteDatabase.rawExecSQL("PRAGMA cipher_default_kdf_iter = 1;");
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(71060)) {
                            sqLiteDatabase.rawExecSQL("PRAGMA cipher_default_page_size = 1024;" + "PRAGMA cipher_default_kdf_iter = 4000;" + "PRAGMA cipher_default_hmac_algorithm = HMAC_SHA1;" + "PRAGMA cipher_default_kdf_algorithm = PBKDF2_HMAC_SHA1;");
                        }
                    }
                }
            }

            @Override
            public void postKey(SQLiteDatabase sqLiteDatabase) {
                if (!ListenerUtil.mutListener.listen(71066)) {
                    if (sqlcipherVersion == 4) {
                        if (!ListenerUtil.mutListener.listen(71064)) {
                            sqLiteDatabase.rawExecSQL("PRAGMA kdf_iter = 1;");
                        }
                        if (!ListenerUtil.mutListener.listen(71065)) {
                            // turn off memory wiping for now due to https://github.com/sqlcipher/android-database-sqlcipher/issues/411
                            sqLiteDatabase.rawExecSQL("PRAGMA cipher_memory_security = OFF;");
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(71063)) {
                            sqLiteDatabase.rawExecSQL("PRAGMA cipher_page_size = 1024;" + "PRAGMA kdf_iter = 4000;" + "PRAGMA cipher_hmac_algorithm = HMAC_SHA1;" + "PRAGMA cipher_kdf_algorithm = PBKDF2_HMAC_SHA1;");
                        }
                    }
                }
            }
        }, new DatabaseErrorHandler() {

            @Override
            public void onCorruption(SQLiteDatabase sqLiteDatabase) {
                if (!ListenerUtil.mutListener.listen(71067)) {
                    logger.error("Database corrupted");
                }
                if (!ListenerUtil.mutListener.listen(71070)) {
                    RuntimeUtil.runOnUiThread(new Runnable() {

                        @Override
                        public void run() {
                            if (!ListenerUtil.mutListener.listen(71069)) {
                                if (context != null) {
                                    if (!ListenerUtil.mutListener.listen(71068)) {
                                        Toast.makeText(context, "Database corrupted. Please save all data!", Toast.LENGTH_LONG).show();
                                    }
                                }
                            }
                        }
                    });
                }
                if (!ListenerUtil.mutListener.listen(71072)) {
                    // close database
                    if (sqLiteDatabase.isOpen()) {
                        try {
                            if (!ListenerUtil.mutListener.listen(71071)) {
                                sqLiteDatabase.close();
                            }
                        } catch (Exception e) {
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(71073)) {
                    System.exit(2);
                }
            }
        });
        if (!ListenerUtil.mutListener.listen(71074)) {
            logger.info("instantiated");
        }
        this.updateSystemService = updateSystemService;
        this.context = context;
        if (!ListenerUtil.mutListener.listen(71075)) {
            SQLiteDatabase.loadLibs(context);
        }
        this.key = databaseKey;
    }

    public synchronized SQLiteDatabase getWritableDatabase() throws SQLiteException {
        return super.getWritableDatabase(this.key);
    }

    public synchronized SQLiteDatabase getReadableDatabase() {
        return super.getReadableDatabase(this.key);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        if (!ListenerUtil.mutListener.listen(71080)) {
            {
                long _loopCounter930 = 0;
                for (ModelFactory f : new ModelFactory[] { this.getContactModelFactory(), this.getMessageModelFactory(), this.getGroupModelFactory(), this.getGroupMemberModelFactory(), this.getGroupMessageModelFactory(), this.getDistributionListModelFactory(), this.getDistributionListMemberModelFactory(), this.getDistributionListMessageModelFactory(), this.getGroupRequestSyncLogModelFactory(), this.getBallotModelFactory(), this.getBallotChoiceModelFactory(), this.getBallotVoteModelFactory(), this.getIdentityBallotModelFactory(), this.getGroupBallotModelFactory(), this.getGroupMessagePendingMessageIdModelFactory(), this.getWebClientSessionModelFactory(), this.getConversationTagFactory() }) {
                    ListenerUtil.loopListener.listen("_loopCounter930", ++_loopCounter930);
                    String[] createTableStatement = f.getStatements();
                    if (!ListenerUtil.mutListener.listen(71079)) {
                        if (createTableStatement != null) {
                            if (!ListenerUtil.mutListener.listen(71078)) {
                                {
                                    long _loopCounter929 = 0;
                                    for (String statement : createTableStatement) {
                                        ListenerUtil.loopListener.listen("_loopCounter929", ++_loopCounter929);
                                        if (!ListenerUtil.mutListener.listen(71077)) {
                                            if (!TestUtil.empty(statement)) {
                                                if (!ListenerUtil.mutListener.listen(71076)) {
                                                    sqLiteDatabase.execSQL(statement);
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public ContactModelFactory getContactModelFactory() {
        if (!ListenerUtil.mutListener.listen(71082)) {
            if (this.contactModelFactory == null) {
                if (!ListenerUtil.mutListener.listen(71081)) {
                    this.contactModelFactory = new ContactModelFactory(this);
                }
            }
        }
        return this.contactModelFactory;
    }

    public MessageModelFactory getMessageModelFactory() {
        if (!ListenerUtil.mutListener.listen(71084)) {
            if (this.messageModelFactory == null) {
                if (!ListenerUtil.mutListener.listen(71083)) {
                    this.messageModelFactory = new MessageModelFactory(this);
                }
            }
        }
        return this.messageModelFactory;
    }

    public GroupModelFactory getGroupModelFactory() {
        if (!ListenerUtil.mutListener.listen(71086)) {
            if (this.groupModelFactory == null) {
                if (!ListenerUtil.mutListener.listen(71085)) {
                    this.groupModelFactory = new GroupModelFactory(this);
                }
            }
        }
        return this.groupModelFactory;
    }

    public GroupMemberModelFactory getGroupMemberModelFactory() {
        if (!ListenerUtil.mutListener.listen(71088)) {
            if (this.groupMemberModelFactory == null) {
                if (!ListenerUtil.mutListener.listen(71087)) {
                    this.groupMemberModelFactory = new GroupMemberModelFactory(this);
                }
            }
        }
        return this.groupMemberModelFactory;
    }

    public GroupMessageModelFactory getGroupMessageModelFactory() {
        if (!ListenerUtil.mutListener.listen(71090)) {
            if (this.groupMessageModelFactory == null) {
                if (!ListenerUtil.mutListener.listen(71089)) {
                    this.groupMessageModelFactory = new GroupMessageModelFactory(this);
                }
            }
        }
        return this.groupMessageModelFactory;
    }

    public DistributionListModelFactory getDistributionListModelFactory() {
        if (!ListenerUtil.mutListener.listen(71092)) {
            if (this.distributionListModelFactory == null) {
                if (!ListenerUtil.mutListener.listen(71091)) {
                    this.distributionListModelFactory = new DistributionListModelFactory(this);
                }
            }
        }
        return this.distributionListModelFactory;
    }

    public DistributionListMemberModelFactory getDistributionListMemberModelFactory() {
        if (!ListenerUtil.mutListener.listen(71094)) {
            if (this.distributionListMemberModelFactory == null) {
                if (!ListenerUtil.mutListener.listen(71093)) {
                    this.distributionListMemberModelFactory = new DistributionListMemberModelFactory(this);
                }
            }
        }
        return this.distributionListMemberModelFactory;
    }

    public DistributionListMessageModelFactory getDistributionListMessageModelFactory() {
        if (!ListenerUtil.mutListener.listen(71096)) {
            if (this.distributionListMessageModelFactory == null) {
                if (!ListenerUtil.mutListener.listen(71095)) {
                    this.distributionListMessageModelFactory = new DistributionListMessageModelFactory(this);
                }
            }
        }
        return this.distributionListMessageModelFactory;
    }

    public GroupRequestSyncLogModelFactory getGroupRequestSyncLogModelFactory() {
        if (!ListenerUtil.mutListener.listen(71098)) {
            if (this.groupRequestSyncLogModelFactory == null) {
                if (!ListenerUtil.mutListener.listen(71097)) {
                    this.groupRequestSyncLogModelFactory = new GroupRequestSyncLogModelFactory(this);
                }
            }
        }
        return this.groupRequestSyncLogModelFactory;
    }

    public BallotModelFactory getBallotModelFactory() {
        if (!ListenerUtil.mutListener.listen(71100)) {
            if (this.ballotModelFactory == null) {
                if (!ListenerUtil.mutListener.listen(71099)) {
                    this.ballotModelFactory = new BallotModelFactory(this);
                }
            }
        }
        return this.ballotModelFactory;
    }

    public BallotChoiceModelFactory getBallotChoiceModelFactory() {
        if (!ListenerUtil.mutListener.listen(71102)) {
            if (this.ballotChoiceModelFactory == null) {
                if (!ListenerUtil.mutListener.listen(71101)) {
                    this.ballotChoiceModelFactory = new BallotChoiceModelFactory(this);
                }
            }
        }
        return this.ballotChoiceModelFactory;
    }

    public BallotVoteModelFactory getBallotVoteModelFactory() {
        if (!ListenerUtil.mutListener.listen(71104)) {
            if (this.ballotVoteModelFactory == null) {
                if (!ListenerUtil.mutListener.listen(71103)) {
                    this.ballotVoteModelFactory = new BallotVoteModelFactory(this);
                }
            }
        }
        return this.ballotVoteModelFactory;
    }

    public IdentityBallotModelFactory getIdentityBallotModelFactory() {
        if (!ListenerUtil.mutListener.listen(71106)) {
            if (this.identityBallotModelFactory == null) {
                if (!ListenerUtil.mutListener.listen(71105)) {
                    this.identityBallotModelFactory = new IdentityBallotModelFactory(this);
                }
            }
        }
        return this.identityBallotModelFactory;
    }

    public GroupBallotModelFactory getGroupBallotModelFactory() {
        if (!ListenerUtil.mutListener.listen(71108)) {
            if (this.groupBallotModelFactory == null) {
                if (!ListenerUtil.mutListener.listen(71107)) {
                    this.groupBallotModelFactory = new GroupBallotModelFactory(this);
                }
            }
        }
        return this.groupBallotModelFactory;
    }

    public GroupMessagePendingMessageIdModelFactory getGroupMessagePendingMessageIdModelFactory() {
        if (!ListenerUtil.mutListener.listen(71110)) {
            if (this.groupMessagePendingMessageIdModelFactory == null) {
                if (!ListenerUtil.mutListener.listen(71109)) {
                    this.groupMessagePendingMessageIdModelFactory = new GroupMessagePendingMessageIdModelFactory(this);
                }
            }
        }
        return this.groupMessagePendingMessageIdModelFactory;
    }

    public WebClientSessionModelFactory getWebClientSessionModelFactory() {
        if (!ListenerUtil.mutListener.listen(71112)) {
            if (this.webClientSessionModelFactory == null) {
                if (!ListenerUtil.mutListener.listen(71111)) {
                    this.webClientSessionModelFactory = new WebClientSessionModelFactory(this);
                }
            }
        }
        return this.webClientSessionModelFactory;
    }

    public ConversationTagFactory getConversationTagFactory() {
        if (!ListenerUtil.mutListener.listen(71114)) {
            if (this.conversationTagFactory == null) {
                if (!ListenerUtil.mutListener.listen(71113)) {
                    this.conversationTagFactory = new ConversationTagFactory(this);
                }
            }
        }
        return this.conversationTagFactory;
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        if (!ListenerUtil.mutListener.listen(71115)) {
            logger.info("onUpgrade, version {} -> {}", oldVersion, newVersion);
        }
        if (!ListenerUtil.mutListener.listen(71122)) {
            if ((ListenerUtil.mutListener.listen(71120) ? (oldVersion >= 4) : (ListenerUtil.mutListener.listen(71119) ? (oldVersion <= 4) : (ListenerUtil.mutListener.listen(71118) ? (oldVersion > 4) : (ListenerUtil.mutListener.listen(71117) ? (oldVersion != 4) : (ListenerUtil.mutListener.listen(71116) ? (oldVersion == 4) : (oldVersion < 4))))))) {
                if (!ListenerUtil.mutListener.listen(71121)) {
                    this.updateSystemService.addUpdate(new SystemUpdateToVersion4(sqLiteDatabase));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(71129)) {
            if ((ListenerUtil.mutListener.listen(71127) ? (oldVersion >= 6) : (ListenerUtil.mutListener.listen(71126) ? (oldVersion <= 6) : (ListenerUtil.mutListener.listen(71125) ? (oldVersion > 6) : (ListenerUtil.mutListener.listen(71124) ? (oldVersion != 6) : (ListenerUtil.mutListener.listen(71123) ? (oldVersion == 6) : (oldVersion < 6))))))) {
                if (!ListenerUtil.mutListener.listen(71128)) {
                    this.updateSystemService.addUpdate(new SystemUpdateToVersion6(this.context, sqLiteDatabase));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(71136)) {
            if ((ListenerUtil.mutListener.listen(71134) ? (oldVersion >= 7) : (ListenerUtil.mutListener.listen(71133) ? (oldVersion <= 7) : (ListenerUtil.mutListener.listen(71132) ? (oldVersion > 7) : (ListenerUtil.mutListener.listen(71131) ? (oldVersion != 7) : (ListenerUtil.mutListener.listen(71130) ? (oldVersion == 7) : (oldVersion < 7))))))) {
                if (!ListenerUtil.mutListener.listen(71135)) {
                    this.updateSystemService.addUpdate(new SystemUpdateToVersion7(sqLiteDatabase));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(71143)) {
            if ((ListenerUtil.mutListener.listen(71141) ? (oldVersion >= 8) : (ListenerUtil.mutListener.listen(71140) ? (oldVersion <= 8) : (ListenerUtil.mutListener.listen(71139) ? (oldVersion > 8) : (ListenerUtil.mutListener.listen(71138) ? (oldVersion != 8) : (ListenerUtil.mutListener.listen(71137) ? (oldVersion == 8) : (oldVersion < 8))))))) {
                if (!ListenerUtil.mutListener.listen(71142)) {
                    this.updateSystemService.addUpdate(new SystemUpdateToVersion8(this, sqLiteDatabase));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(71150)) {
            if ((ListenerUtil.mutListener.listen(71148) ? (oldVersion >= 9) : (ListenerUtil.mutListener.listen(71147) ? (oldVersion <= 9) : (ListenerUtil.mutListener.listen(71146) ? (oldVersion > 9) : (ListenerUtil.mutListener.listen(71145) ? (oldVersion != 9) : (ListenerUtil.mutListener.listen(71144) ? (oldVersion == 9) : (oldVersion < 9))))))) {
                if (!ListenerUtil.mutListener.listen(71149)) {
                    this.updateSystemService.addUpdate(new SystemUpdateToVersion9(sqLiteDatabase));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(71157)) {
            if ((ListenerUtil.mutListener.listen(71155) ? (oldVersion >= 10) : (ListenerUtil.mutListener.listen(71154) ? (oldVersion <= 10) : (ListenerUtil.mutListener.listen(71153) ? (oldVersion > 10) : (ListenerUtil.mutListener.listen(71152) ? (oldVersion != 10) : (ListenerUtil.mutListener.listen(71151) ? (oldVersion == 10) : (oldVersion < 10))))))) {
                if (!ListenerUtil.mutListener.listen(71156)) {
                    this.updateSystemService.addUpdate(new SystemUpdateToVersion10(sqLiteDatabase));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(71164)) {
            if ((ListenerUtil.mutListener.listen(71162) ? (oldVersion >= 11) : (ListenerUtil.mutListener.listen(71161) ? (oldVersion <= 11) : (ListenerUtil.mutListener.listen(71160) ? (oldVersion > 11) : (ListenerUtil.mutListener.listen(71159) ? (oldVersion != 11) : (ListenerUtil.mutListener.listen(71158) ? (oldVersion == 11) : (oldVersion < 11))))))) {
                if (!ListenerUtil.mutListener.listen(71163)) {
                    this.updateSystemService.addUpdate(new SystemUpdateToVersion11(sqLiteDatabase));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(71171)) {
            if ((ListenerUtil.mutListener.listen(71169) ? (oldVersion >= 12) : (ListenerUtil.mutListener.listen(71168) ? (oldVersion <= 12) : (ListenerUtil.mutListener.listen(71167) ? (oldVersion > 12) : (ListenerUtil.mutListener.listen(71166) ? (oldVersion != 12) : (ListenerUtil.mutListener.listen(71165) ? (oldVersion == 12) : (oldVersion < 12))))))) {
                if (!ListenerUtil.mutListener.listen(71170)) {
                    this.updateSystemService.addUpdate(new SystemUpdateToVersion12(this.context, sqLiteDatabase));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(71178)) {
            if ((ListenerUtil.mutListener.listen(71176) ? (oldVersion >= 13) : (ListenerUtil.mutListener.listen(71175) ? (oldVersion <= 13) : (ListenerUtil.mutListener.listen(71174) ? (oldVersion > 13) : (ListenerUtil.mutListener.listen(71173) ? (oldVersion != 13) : (ListenerUtil.mutListener.listen(71172) ? (oldVersion == 13) : (oldVersion < 13))))))) {
                if (!ListenerUtil.mutListener.listen(71177)) {
                    this.updateSystemService.addUpdate(new SystemUpdateToVersion13(sqLiteDatabase));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(71185)) {
            if ((ListenerUtil.mutListener.listen(71183) ? (oldVersion >= 14) : (ListenerUtil.mutListener.listen(71182) ? (oldVersion <= 14) : (ListenerUtil.mutListener.listen(71181) ? (oldVersion > 14) : (ListenerUtil.mutListener.listen(71180) ? (oldVersion != 14) : (ListenerUtil.mutListener.listen(71179) ? (oldVersion == 14) : (oldVersion < 14))))))) {
                if (!ListenerUtil.mutListener.listen(71184)) {
                    this.updateSystemService.addUpdate(new SystemUpdateToVersion14());
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(71192)) {
            if ((ListenerUtil.mutListener.listen(71190) ? (oldVersion >= 15) : (ListenerUtil.mutListener.listen(71189) ? (oldVersion <= 15) : (ListenerUtil.mutListener.listen(71188) ? (oldVersion > 15) : (ListenerUtil.mutListener.listen(71187) ? (oldVersion != 15) : (ListenerUtil.mutListener.listen(71186) ? (oldVersion == 15) : (oldVersion < 15))))))) {
                if (!ListenerUtil.mutListener.listen(71191)) {
                    this.updateSystemService.addUpdate(new SystemUpdateToVersion15(this, sqLiteDatabase));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(71199)) {
            if ((ListenerUtil.mutListener.listen(71197) ? (oldVersion >= 16) : (ListenerUtil.mutListener.listen(71196) ? (oldVersion <= 16) : (ListenerUtil.mutListener.listen(71195) ? (oldVersion > 16) : (ListenerUtil.mutListener.listen(71194) ? (oldVersion != 16) : (ListenerUtil.mutListener.listen(71193) ? (oldVersion == 16) : (oldVersion < 16))))))) {
                if (!ListenerUtil.mutListener.listen(71198)) {
                    this.updateSystemService.addUpdate(new SystemUpdateToVersion16(sqLiteDatabase));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(71206)) {
            if ((ListenerUtil.mutListener.listen(71204) ? (oldVersion >= 17) : (ListenerUtil.mutListener.listen(71203) ? (oldVersion <= 17) : (ListenerUtil.mutListener.listen(71202) ? (oldVersion > 17) : (ListenerUtil.mutListener.listen(71201) ? (oldVersion != 17) : (ListenerUtil.mutListener.listen(71200) ? (oldVersion == 17) : (oldVersion < 17))))))) {
                if (!ListenerUtil.mutListener.listen(71205)) {
                    this.updateSystemService.addUpdate(new SystemUpdateToVersion17(sqLiteDatabase));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(71213)) {
            if ((ListenerUtil.mutListener.listen(71211) ? (oldVersion >= 18) : (ListenerUtil.mutListener.listen(71210) ? (oldVersion <= 18) : (ListenerUtil.mutListener.listen(71209) ? (oldVersion > 18) : (ListenerUtil.mutListener.listen(71208) ? (oldVersion != 18) : (ListenerUtil.mutListener.listen(71207) ? (oldVersion == 18) : (oldVersion < 18))))))) {
                if (!ListenerUtil.mutListener.listen(71212)) {
                    this.updateSystemService.addUpdate(new SystemUpdateToVersion18(sqLiteDatabase));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(71220)) {
            if ((ListenerUtil.mutListener.listen(71218) ? (oldVersion >= 19) : (ListenerUtil.mutListener.listen(71217) ? (oldVersion <= 19) : (ListenerUtil.mutListener.listen(71216) ? (oldVersion > 19) : (ListenerUtil.mutListener.listen(71215) ? (oldVersion != 19) : (ListenerUtil.mutListener.listen(71214) ? (oldVersion == 19) : (oldVersion < 19))))))) {
                if (!ListenerUtil.mutListener.listen(71219)) {
                    this.updateSystemService.addUpdate(new SystemUpdateToVersion19(sqLiteDatabase));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(71227)) {
            if ((ListenerUtil.mutListener.listen(71225) ? (oldVersion >= 20) : (ListenerUtil.mutListener.listen(71224) ? (oldVersion <= 20) : (ListenerUtil.mutListener.listen(71223) ? (oldVersion > 20) : (ListenerUtil.mutListener.listen(71222) ? (oldVersion != 20) : (ListenerUtil.mutListener.listen(71221) ? (oldVersion == 20) : (oldVersion < 20))))))) {
                if (!ListenerUtil.mutListener.listen(71226)) {
                    this.updateSystemService.addUpdate(new SystemUpdateToVersion20(sqLiteDatabase));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(71234)) {
            if ((ListenerUtil.mutListener.listen(71232) ? (oldVersion >= 21) : (ListenerUtil.mutListener.listen(71231) ? (oldVersion <= 21) : (ListenerUtil.mutListener.listen(71230) ? (oldVersion > 21) : (ListenerUtil.mutListener.listen(71229) ? (oldVersion != 21) : (ListenerUtil.mutListener.listen(71228) ? (oldVersion == 21) : (oldVersion < 21))))))) {
                if (!ListenerUtil.mutListener.listen(71233)) {
                    this.updateSystemService.addUpdate(new SystemUpdateToVersion21(this, sqLiteDatabase));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(71241)) {
            if ((ListenerUtil.mutListener.listen(71239) ? (oldVersion >= 23) : (ListenerUtil.mutListener.listen(71238) ? (oldVersion <= 23) : (ListenerUtil.mutListener.listen(71237) ? (oldVersion > 23) : (ListenerUtil.mutListener.listen(71236) ? (oldVersion != 23) : (ListenerUtil.mutListener.listen(71235) ? (oldVersion == 23) : (oldVersion < 23))))))) {
                if (!ListenerUtil.mutListener.listen(71240)) {
                    this.updateSystemService.addUpdate(new SystemUpdateToVersion23(sqLiteDatabase));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(71248)) {
            if ((ListenerUtil.mutListener.listen(71246) ? (oldVersion >= 24) : (ListenerUtil.mutListener.listen(71245) ? (oldVersion <= 24) : (ListenerUtil.mutListener.listen(71244) ? (oldVersion > 24) : (ListenerUtil.mutListener.listen(71243) ? (oldVersion != 24) : (ListenerUtil.mutListener.listen(71242) ? (oldVersion == 24) : (oldVersion < 24))))))) {
                if (!ListenerUtil.mutListener.listen(71247)) {
                    this.updateSystemService.addUpdate(new SystemUpdateToVersion24(sqLiteDatabase));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(71255)) {
            if ((ListenerUtil.mutListener.listen(71253) ? (oldVersion >= 25) : (ListenerUtil.mutListener.listen(71252) ? (oldVersion <= 25) : (ListenerUtil.mutListener.listen(71251) ? (oldVersion > 25) : (ListenerUtil.mutListener.listen(71250) ? (oldVersion != 25) : (ListenerUtil.mutListener.listen(71249) ? (oldVersion == 25) : (oldVersion < 25))))))) {
                if (!ListenerUtil.mutListener.listen(71254)) {
                    this.updateSystemService.addUpdate(new SystemUpdateToVersion25(this, sqLiteDatabase));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(71262)) {
            if ((ListenerUtil.mutListener.listen(71260) ? (oldVersion >= 27) : (ListenerUtil.mutListener.listen(71259) ? (oldVersion <= 27) : (ListenerUtil.mutListener.listen(71258) ? (oldVersion > 27) : (ListenerUtil.mutListener.listen(71257) ? (oldVersion != 27) : (ListenerUtil.mutListener.listen(71256) ? (oldVersion == 27) : (oldVersion < 27))))))) {
                if (!ListenerUtil.mutListener.listen(71261)) {
                    this.updateSystemService.addUpdate(new SystemUpdateToVersion27(sqLiteDatabase));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(71269)) {
            if ((ListenerUtil.mutListener.listen(71267) ? (oldVersion >= 28) : (ListenerUtil.mutListener.listen(71266) ? (oldVersion <= 28) : (ListenerUtil.mutListener.listen(71265) ? (oldVersion > 28) : (ListenerUtil.mutListener.listen(71264) ? (oldVersion != 28) : (ListenerUtil.mutListener.listen(71263) ? (oldVersion == 28) : (oldVersion < 28))))))) {
                if (!ListenerUtil.mutListener.listen(71268)) {
                    this.updateSystemService.addUpdate(new SystemUpdateToVersion28(sqLiteDatabase));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(71276)) {
            if ((ListenerUtil.mutListener.listen(71274) ? (oldVersion >= 30) : (ListenerUtil.mutListener.listen(71273) ? (oldVersion <= 30) : (ListenerUtil.mutListener.listen(71272) ? (oldVersion > 30) : (ListenerUtil.mutListener.listen(71271) ? (oldVersion != 30) : (ListenerUtil.mutListener.listen(71270) ? (oldVersion == 30) : (oldVersion < 30))))))) {
                if (!ListenerUtil.mutListener.listen(71275)) {
                    this.updateSystemService.addUpdate(new SystemUpdateToVersion30(sqLiteDatabase));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(71283)) {
            if ((ListenerUtil.mutListener.listen(71281) ? (oldVersion >= 31) : (ListenerUtil.mutListener.listen(71280) ? (oldVersion <= 31) : (ListenerUtil.mutListener.listen(71279) ? (oldVersion > 31) : (ListenerUtil.mutListener.listen(71278) ? (oldVersion != 31) : (ListenerUtil.mutListener.listen(71277) ? (oldVersion == 31) : (oldVersion < 31))))))) {
                if (!ListenerUtil.mutListener.listen(71282)) {
                    this.updateSystemService.addUpdate(new SystemUpdateToVersion31(this.context));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(71290)) {
            if ((ListenerUtil.mutListener.listen(71288) ? (oldVersion >= 32) : (ListenerUtil.mutListener.listen(71287) ? (oldVersion <= 32) : (ListenerUtil.mutListener.listen(71286) ? (oldVersion > 32) : (ListenerUtil.mutListener.listen(71285) ? (oldVersion != 32) : (ListenerUtil.mutListener.listen(71284) ? (oldVersion == 32) : (oldVersion < 32))))))) {
                if (!ListenerUtil.mutListener.listen(71289)) {
                    this.updateSystemService.addUpdate(new SystemUpdateToVersion32(sqLiteDatabase));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(71297)) {
            if ((ListenerUtil.mutListener.listen(71295) ? (oldVersion >= 33) : (ListenerUtil.mutListener.listen(71294) ? (oldVersion <= 33) : (ListenerUtil.mutListener.listen(71293) ? (oldVersion > 33) : (ListenerUtil.mutListener.listen(71292) ? (oldVersion != 33) : (ListenerUtil.mutListener.listen(71291) ? (oldVersion == 33) : (oldVersion < 33))))))) {
                if (!ListenerUtil.mutListener.listen(71296)) {
                    this.updateSystemService.addUpdate(new SystemUpdateToVersion33(this, sqLiteDatabase));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(71304)) {
            if ((ListenerUtil.mutListener.listen(71302) ? (oldVersion >= 34) : (ListenerUtil.mutListener.listen(71301) ? (oldVersion <= 34) : (ListenerUtil.mutListener.listen(71300) ? (oldVersion > 34) : (ListenerUtil.mutListener.listen(71299) ? (oldVersion != 34) : (ListenerUtil.mutListener.listen(71298) ? (oldVersion == 34) : (oldVersion < 34))))))) {
                if (!ListenerUtil.mutListener.listen(71303)) {
                    this.updateSystemService.addUpdate(new SystemUpdateToVersion34(sqLiteDatabase));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(71311)) {
            if ((ListenerUtil.mutListener.listen(71309) ? (oldVersion >= 35) : (ListenerUtil.mutListener.listen(71308) ? (oldVersion <= 35) : (ListenerUtil.mutListener.listen(71307) ? (oldVersion > 35) : (ListenerUtil.mutListener.listen(71306) ? (oldVersion != 35) : (ListenerUtil.mutListener.listen(71305) ? (oldVersion == 35) : (oldVersion < 35))))))) {
                if (!ListenerUtil.mutListener.listen(71310)) {
                    this.updateSystemService.addUpdate(new SystemUpdateToVersion35(sqLiteDatabase));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(71318)) {
            if ((ListenerUtil.mutListener.listen(71316) ? (oldVersion >= 36) : (ListenerUtil.mutListener.listen(71315) ? (oldVersion <= 36) : (ListenerUtil.mutListener.listen(71314) ? (oldVersion > 36) : (ListenerUtil.mutListener.listen(71313) ? (oldVersion != 36) : (ListenerUtil.mutListener.listen(71312) ? (oldVersion == 36) : (oldVersion < 36))))))) {
                if (!ListenerUtil.mutListener.listen(71317)) {
                    this.updateSystemService.addUpdate(new SystemUpdateToVersion36(sqLiteDatabase));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(71325)) {
            if ((ListenerUtil.mutListener.listen(71323) ? (oldVersion >= 37) : (ListenerUtil.mutListener.listen(71322) ? (oldVersion <= 37) : (ListenerUtil.mutListener.listen(71321) ? (oldVersion > 37) : (ListenerUtil.mutListener.listen(71320) ? (oldVersion != 37) : (ListenerUtil.mutListener.listen(71319) ? (oldVersion == 37) : (oldVersion < 37))))))) {
                if (!ListenerUtil.mutListener.listen(71324)) {
                    this.updateSystemService.addUpdate(new SystemUpdateToVersion37(this, sqLiteDatabase));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(71332)) {
            if ((ListenerUtil.mutListener.listen(71330) ? (oldVersion >= 38) : (ListenerUtil.mutListener.listen(71329) ? (oldVersion <= 38) : (ListenerUtil.mutListener.listen(71328) ? (oldVersion > 38) : (ListenerUtil.mutListener.listen(71327) ? (oldVersion != 38) : (ListenerUtil.mutListener.listen(71326) ? (oldVersion == 38) : (oldVersion < 38))))))) {
                if (!ListenerUtil.mutListener.listen(71331)) {
                    this.updateSystemService.addUpdate(new SystemUpdateToVersion38(this, sqLiteDatabase));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(71339)) {
            if ((ListenerUtil.mutListener.listen(71337) ? (oldVersion >= 39) : (ListenerUtil.mutListener.listen(71336) ? (oldVersion <= 39) : (ListenerUtil.mutListener.listen(71335) ? (oldVersion > 39) : (ListenerUtil.mutListener.listen(71334) ? (oldVersion != 39) : (ListenerUtil.mutListener.listen(71333) ? (oldVersion == 39) : (oldVersion < 39))))))) {
                if (!ListenerUtil.mutListener.listen(71338)) {
                    this.updateSystemService.addUpdate(new SystemUpdateToVersion39());
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(71346)) {
            if ((ListenerUtil.mutListener.listen(71344) ? (oldVersion >= 40) : (ListenerUtil.mutListener.listen(71343) ? (oldVersion <= 40) : (ListenerUtil.mutListener.listen(71342) ? (oldVersion > 40) : (ListenerUtil.mutListener.listen(71341) ? (oldVersion != 40) : (ListenerUtil.mutListener.listen(71340) ? (oldVersion == 40) : (oldVersion < 40))))))) {
                if (!ListenerUtil.mutListener.listen(71345)) {
                    this.updateSystemService.addUpdate(new SystemUpdateToVersion40(sqLiteDatabase));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(71353)) {
            if ((ListenerUtil.mutListener.listen(71351) ? (oldVersion >= 41) : (ListenerUtil.mutListener.listen(71350) ? (oldVersion <= 41) : (ListenerUtil.mutListener.listen(71349) ? (oldVersion > 41) : (ListenerUtil.mutListener.listen(71348) ? (oldVersion != 41) : (ListenerUtil.mutListener.listen(71347) ? (oldVersion == 41) : (oldVersion < 41))))))) {
                if (!ListenerUtil.mutListener.listen(71352)) {
                    this.updateSystemService.addUpdate(new SystemUpdateToVersion41(sqLiteDatabase));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(71360)) {
            if ((ListenerUtil.mutListener.listen(71358) ? (oldVersion >= 42) : (ListenerUtil.mutListener.listen(71357) ? (oldVersion <= 42) : (ListenerUtil.mutListener.listen(71356) ? (oldVersion > 42) : (ListenerUtil.mutListener.listen(71355) ? (oldVersion != 42) : (ListenerUtil.mutListener.listen(71354) ? (oldVersion == 42) : (oldVersion < 42))))))) {
                if (!ListenerUtil.mutListener.listen(71359)) {
                    this.updateSystemService.addUpdate(new SystemUpdateToVersion42(sqLiteDatabase));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(71367)) {
            if ((ListenerUtil.mutListener.listen(71365) ? (oldVersion >= 43) : (ListenerUtil.mutListener.listen(71364) ? (oldVersion <= 43) : (ListenerUtil.mutListener.listen(71363) ? (oldVersion > 43) : (ListenerUtil.mutListener.listen(71362) ? (oldVersion != 43) : (ListenerUtil.mutListener.listen(71361) ? (oldVersion == 43) : (oldVersion < 43))))))) {
                if (!ListenerUtil.mutListener.listen(71366)) {
                    this.updateSystemService.addUpdate(new SystemUpdateToVersion43(sqLiteDatabase));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(71374)) {
            if ((ListenerUtil.mutListener.listen(71372) ? (oldVersion >= 44) : (ListenerUtil.mutListener.listen(71371) ? (oldVersion <= 44) : (ListenerUtil.mutListener.listen(71370) ? (oldVersion > 44) : (ListenerUtil.mutListener.listen(71369) ? (oldVersion != 44) : (ListenerUtil.mutListener.listen(71368) ? (oldVersion == 44) : (oldVersion < 44))))))) {
                if (!ListenerUtil.mutListener.listen(71373)) {
                    this.updateSystemService.addUpdate(new SystemUpdateToVersion44(sqLiteDatabase));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(71381)) {
            if ((ListenerUtil.mutListener.listen(71379) ? (oldVersion >= 45) : (ListenerUtil.mutListener.listen(71378) ? (oldVersion <= 45) : (ListenerUtil.mutListener.listen(71377) ? (oldVersion > 45) : (ListenerUtil.mutListener.listen(71376) ? (oldVersion != 45) : (ListenerUtil.mutListener.listen(71375) ? (oldVersion == 45) : (oldVersion < 45))))))) {
                if (!ListenerUtil.mutListener.listen(71380)) {
                    this.updateSystemService.addUpdate(new SystemUpdateToVersion45(this, sqLiteDatabase));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(71388)) {
            if ((ListenerUtil.mutListener.listen(71386) ? (oldVersion >= 46) : (ListenerUtil.mutListener.listen(71385) ? (oldVersion <= 46) : (ListenerUtil.mutListener.listen(71384) ? (oldVersion > 46) : (ListenerUtil.mutListener.listen(71383) ? (oldVersion != 46) : (ListenerUtil.mutListener.listen(71382) ? (oldVersion == 46) : (oldVersion < 46))))))) {
                if (!ListenerUtil.mutListener.listen(71387)) {
                    this.updateSystemService.addUpdate(new SystemUpdateToVersion46());
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(71395)) {
            if ((ListenerUtil.mutListener.listen(71393) ? (oldVersion >= 47) : (ListenerUtil.mutListener.listen(71392) ? (oldVersion <= 47) : (ListenerUtil.mutListener.listen(71391) ? (oldVersion > 47) : (ListenerUtil.mutListener.listen(71390) ? (oldVersion != 47) : (ListenerUtil.mutListener.listen(71389) ? (oldVersion == 47) : (oldVersion < 47))))))) {
                if (!ListenerUtil.mutListener.listen(71394)) {
                    this.updateSystemService.addUpdate(new SystemUpdateToVersion47(sqLiteDatabase));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(71402)) {
            if ((ListenerUtil.mutListener.listen(71400) ? (oldVersion >= 48) : (ListenerUtil.mutListener.listen(71399) ? (oldVersion <= 48) : (ListenerUtil.mutListener.listen(71398) ? (oldVersion > 48) : (ListenerUtil.mutListener.listen(71397) ? (oldVersion != 48) : (ListenerUtil.mutListener.listen(71396) ? (oldVersion == 48) : (oldVersion < 48))))))) {
                if (!ListenerUtil.mutListener.listen(71401)) {
                    this.updateSystemService.addUpdate(new SystemUpdateToVersion48(this.context));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(71409)) {
            if ((ListenerUtil.mutListener.listen(71407) ? (oldVersion >= 49) : (ListenerUtil.mutListener.listen(71406) ? (oldVersion <= 49) : (ListenerUtil.mutListener.listen(71405) ? (oldVersion > 49) : (ListenerUtil.mutListener.listen(71404) ? (oldVersion != 49) : (ListenerUtil.mutListener.listen(71403) ? (oldVersion == 49) : (oldVersion < 49))))))) {
                if (!ListenerUtil.mutListener.listen(71408)) {
                    this.updateSystemService.addUpdate(new SystemUpdateToVersion49(sqLiteDatabase));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(71416)) {
            if ((ListenerUtil.mutListener.listen(71414) ? (oldVersion >= 50) : (ListenerUtil.mutListener.listen(71413) ? (oldVersion <= 50) : (ListenerUtil.mutListener.listen(71412) ? (oldVersion > 50) : (ListenerUtil.mutListener.listen(71411) ? (oldVersion != 50) : (ListenerUtil.mutListener.listen(71410) ? (oldVersion == 50) : (oldVersion < 50))))))) {
                if (!ListenerUtil.mutListener.listen(71415)) {
                    this.updateSystemService.addUpdate(new SystemUpdateToVersion50(sqLiteDatabase));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(71423)) {
            if ((ListenerUtil.mutListener.listen(71421) ? (oldVersion >= 51) : (ListenerUtil.mutListener.listen(71420) ? (oldVersion <= 51) : (ListenerUtil.mutListener.listen(71419) ? (oldVersion > 51) : (ListenerUtil.mutListener.listen(71418) ? (oldVersion != 51) : (ListenerUtil.mutListener.listen(71417) ? (oldVersion == 51) : (oldVersion < 51))))))) {
                if (!ListenerUtil.mutListener.listen(71422)) {
                    this.updateSystemService.addUpdate(new SystemUpdateToVersion51(sqLiteDatabase));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(71430)) {
            if ((ListenerUtil.mutListener.listen(71428) ? (oldVersion >= 52) : (ListenerUtil.mutListener.listen(71427) ? (oldVersion <= 52) : (ListenerUtil.mutListener.listen(71426) ? (oldVersion > 52) : (ListenerUtil.mutListener.listen(71425) ? (oldVersion != 52) : (ListenerUtil.mutListener.listen(71424) ? (oldVersion == 52) : (oldVersion < 52))))))) {
                if (!ListenerUtil.mutListener.listen(71429)) {
                    this.updateSystemService.addUpdate(new SystemUpdateToVersion52(sqLiteDatabase));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(71437)) {
            if ((ListenerUtil.mutListener.listen(71435) ? (oldVersion >= 53) : (ListenerUtil.mutListener.listen(71434) ? (oldVersion <= 53) : (ListenerUtil.mutListener.listen(71433) ? (oldVersion > 53) : (ListenerUtil.mutListener.listen(71432) ? (oldVersion != 53) : (ListenerUtil.mutListener.listen(71431) ? (oldVersion == 53) : (oldVersion < 53))))))) {
                if (!ListenerUtil.mutListener.listen(71436)) {
                    this.updateSystemService.addUpdate(new SystemUpdateToVersion53());
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(71444)) {
            if ((ListenerUtil.mutListener.listen(71442) ? (oldVersion >= 54) : (ListenerUtil.mutListener.listen(71441) ? (oldVersion <= 54) : (ListenerUtil.mutListener.listen(71440) ? (oldVersion > 54) : (ListenerUtil.mutListener.listen(71439) ? (oldVersion != 54) : (ListenerUtil.mutListener.listen(71438) ? (oldVersion == 54) : (oldVersion < 54))))))) {
                if (!ListenerUtil.mutListener.listen(71443)) {
                    this.updateSystemService.addUpdate(new SystemUpdateToVersion54(this.context));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(71451)) {
            if ((ListenerUtil.mutListener.listen(71449) ? (oldVersion >= 55) : (ListenerUtil.mutListener.listen(71448) ? (oldVersion <= 55) : (ListenerUtil.mutListener.listen(71447) ? (oldVersion > 55) : (ListenerUtil.mutListener.listen(71446) ? (oldVersion != 55) : (ListenerUtil.mutListener.listen(71445) ? (oldVersion == 55) : (oldVersion < 55))))))) {
                if (!ListenerUtil.mutListener.listen(71450)) {
                    this.updateSystemService.addUpdate(new SystemUpdateToVersion55());
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(71458)) {
            if ((ListenerUtil.mutListener.listen(71456) ? (oldVersion >= 56) : (ListenerUtil.mutListener.listen(71455) ? (oldVersion <= 56) : (ListenerUtil.mutListener.listen(71454) ? (oldVersion > 56) : (ListenerUtil.mutListener.listen(71453) ? (oldVersion != 56) : (ListenerUtil.mutListener.listen(71452) ? (oldVersion == 56) : (oldVersion < 56))))))) {
                if (!ListenerUtil.mutListener.listen(71457)) {
                    this.updateSystemService.addUpdate(new SystemUpdateToVersion56(sqLiteDatabase));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(71465)) {
            if ((ListenerUtil.mutListener.listen(71463) ? (oldVersion >= 58) : (ListenerUtil.mutListener.listen(71462) ? (oldVersion <= 58) : (ListenerUtil.mutListener.listen(71461) ? (oldVersion > 58) : (ListenerUtil.mutListener.listen(71460) ? (oldVersion != 58) : (ListenerUtil.mutListener.listen(71459) ? (oldVersion == 58) : (oldVersion < 58))))))) {
                if (!ListenerUtil.mutListener.listen(71464)) {
                    this.updateSystemService.addUpdate(new SystemUpdateToVersion58(sqLiteDatabase));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(71472)) {
            if ((ListenerUtil.mutListener.listen(71470) ? (oldVersion >= 59) : (ListenerUtil.mutListener.listen(71469) ? (oldVersion <= 59) : (ListenerUtil.mutListener.listen(71468) ? (oldVersion > 59) : (ListenerUtil.mutListener.listen(71467) ? (oldVersion != 59) : (ListenerUtil.mutListener.listen(71466) ? (oldVersion == 59) : (oldVersion < 59))))))) {
                if (!ListenerUtil.mutListener.listen(71471)) {
                    this.updateSystemService.addUpdate(new SystemUpdateToVersion59(sqLiteDatabase));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(71479)) {
            if ((ListenerUtil.mutListener.listen(71477) ? (oldVersion >= 60) : (ListenerUtil.mutListener.listen(71476) ? (oldVersion <= 60) : (ListenerUtil.mutListener.listen(71475) ? (oldVersion > 60) : (ListenerUtil.mutListener.listen(71474) ? (oldVersion != 60) : (ListenerUtil.mutListener.listen(71473) ? (oldVersion == 60) : (oldVersion < 60))))))) {
                if (!ListenerUtil.mutListener.listen(71478)) {
                    this.updateSystemService.addUpdate(new SystemUpdateToVersion60(sqLiteDatabase));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(71486)) {
            if ((ListenerUtil.mutListener.listen(71484) ? (oldVersion >= 61) : (ListenerUtil.mutListener.listen(71483) ? (oldVersion <= 61) : (ListenerUtil.mutListener.listen(71482) ? (oldVersion > 61) : (ListenerUtil.mutListener.listen(71481) ? (oldVersion != 61) : (ListenerUtil.mutListener.listen(71480) ? (oldVersion == 61) : (oldVersion < 61))))))) {
                if (!ListenerUtil.mutListener.listen(71485)) {
                    this.updateSystemService.addUpdate(new SystemUpdateToVersion61(sqLiteDatabase));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(71493)) {
            if ((ListenerUtil.mutListener.listen(71491) ? (oldVersion >= 62) : (ListenerUtil.mutListener.listen(71490) ? (oldVersion <= 62) : (ListenerUtil.mutListener.listen(71489) ? (oldVersion > 62) : (ListenerUtil.mutListener.listen(71488) ? (oldVersion != 62) : (ListenerUtil.mutListener.listen(71487) ? (oldVersion == 62) : (oldVersion < 62))))))) {
                if (!ListenerUtil.mutListener.listen(71492)) {
                    this.updateSystemService.addUpdate(new SystemUpdateToVersion62(sqLiteDatabase));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(71500)) {
            if ((ListenerUtil.mutListener.listen(71498) ? (oldVersion >= 63) : (ListenerUtil.mutListener.listen(71497) ? (oldVersion <= 63) : (ListenerUtil.mutListener.listen(71496) ? (oldVersion > 63) : (ListenerUtil.mutListener.listen(71495) ? (oldVersion != 63) : (ListenerUtil.mutListener.listen(71494) ? (oldVersion == 63) : (oldVersion < 63))))))) {
                if (!ListenerUtil.mutListener.listen(71499)) {
                    this.updateSystemService.addUpdate(new SystemUpdateToVersion63(this.context));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(71507)) {
            if ((ListenerUtil.mutListener.listen(71505) ? (oldVersion >= 64) : (ListenerUtil.mutListener.listen(71504) ? (oldVersion <= 64) : (ListenerUtil.mutListener.listen(71503) ? (oldVersion > 64) : (ListenerUtil.mutListener.listen(71502) ? (oldVersion != 64) : (ListenerUtil.mutListener.listen(71501) ? (oldVersion == 64) : (oldVersion < 64))))))) {
                if (!ListenerUtil.mutListener.listen(71506)) {
                    this.updateSystemService.addUpdate(new SystemUpdateToVersion64(this.context));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(71514)) {
            if ((ListenerUtil.mutListener.listen(71512) ? (oldVersion >= 65) : (ListenerUtil.mutListener.listen(71511) ? (oldVersion <= 65) : (ListenerUtil.mutListener.listen(71510) ? (oldVersion > 65) : (ListenerUtil.mutListener.listen(71509) ? (oldVersion != 65) : (ListenerUtil.mutListener.listen(71508) ? (oldVersion == 65) : (oldVersion < 65))))))) {
                if (!ListenerUtil.mutListener.listen(71513)) {
                    this.updateSystemService.addUpdate(new SystemUpdateToVersion65(this.context));
                }
            }
        }
    }

    public void executeNull() throws SQLiteException {
        if (!ListenerUtil.mutListener.listen(71515)) {
            this.getWritableDatabase().rawExecSQL("SELECT NULL");
        }
    }

    @MainThread
    public static synchronized void tryMigrateToV4(Context context, final String databaseKey) throws DatabaseMigrationFailedException, DatabaseMigrationLockedException {
        File oldDatabaseFile = context.getDatabasePath(DATABASE_NAME);
        File newDatabaseFile = context.getDatabasePath(DATABASE_NAME_V4);
        final boolean[] migrateSuccess = { false };
        if (!ListenerUtil.mutListener.listen(71516)) {
            logger.info("check if v4 database migration is necessary");
        }
        if (!ListenerUtil.mutListener.listen(71582)) {
            if (oldDatabaseFile.exists()) {
                File lockfile = new File(context.getFilesDir(), ".dbv4-lock");
                if (!ListenerUtil.mutListener.listen(71537)) {
                    if (lockfile.exists()) {
                        long lastModified = lockfile.lastModified();
                        long now = System.currentTimeMillis();
                        if (!ListenerUtil.mutListener.listen(71536)) {
                            if ((ListenerUtil.mutListener.listen(71531) ? (((ListenerUtil.mutListener.listen(71522) ? (now % lastModified) : (ListenerUtil.mutListener.listen(71521) ? (now / lastModified) : (ListenerUtil.mutListener.listen(71520) ? (now * lastModified) : (ListenerUtil.mutListener.listen(71519) ? (now + lastModified) : (now - lastModified)))))) >= ((ListenerUtil.mutListener.listen(71526) ? (5 % DateUtils.MINUTE_IN_MILLIS) : (ListenerUtil.mutListener.listen(71525) ? (5 / DateUtils.MINUTE_IN_MILLIS) : (ListenerUtil.mutListener.listen(71524) ? (5 - DateUtils.MINUTE_IN_MILLIS) : (ListenerUtil.mutListener.listen(71523) ? (5 + DateUtils.MINUTE_IN_MILLIS) : (5 * DateUtils.MINUTE_IN_MILLIS))))))) : (ListenerUtil.mutListener.listen(71530) ? (((ListenerUtil.mutListener.listen(71522) ? (now % lastModified) : (ListenerUtil.mutListener.listen(71521) ? (now / lastModified) : (ListenerUtil.mutListener.listen(71520) ? (now * lastModified) : (ListenerUtil.mutListener.listen(71519) ? (now + lastModified) : (now - lastModified)))))) <= ((ListenerUtil.mutListener.listen(71526) ? (5 % DateUtils.MINUTE_IN_MILLIS) : (ListenerUtil.mutListener.listen(71525) ? (5 / DateUtils.MINUTE_IN_MILLIS) : (ListenerUtil.mutListener.listen(71524) ? (5 - DateUtils.MINUTE_IN_MILLIS) : (ListenerUtil.mutListener.listen(71523) ? (5 + DateUtils.MINUTE_IN_MILLIS) : (5 * DateUtils.MINUTE_IN_MILLIS))))))) : (ListenerUtil.mutListener.listen(71529) ? (((ListenerUtil.mutListener.listen(71522) ? (now % lastModified) : (ListenerUtil.mutListener.listen(71521) ? (now / lastModified) : (ListenerUtil.mutListener.listen(71520) ? (now * lastModified) : (ListenerUtil.mutListener.listen(71519) ? (now + lastModified) : (now - lastModified)))))) < ((ListenerUtil.mutListener.listen(71526) ? (5 % DateUtils.MINUTE_IN_MILLIS) : (ListenerUtil.mutListener.listen(71525) ? (5 / DateUtils.MINUTE_IN_MILLIS) : (ListenerUtil.mutListener.listen(71524) ? (5 - DateUtils.MINUTE_IN_MILLIS) : (ListenerUtil.mutListener.listen(71523) ? (5 + DateUtils.MINUTE_IN_MILLIS) : (5 * DateUtils.MINUTE_IN_MILLIS))))))) : (ListenerUtil.mutListener.listen(71528) ? (((ListenerUtil.mutListener.listen(71522) ? (now % lastModified) : (ListenerUtil.mutListener.listen(71521) ? (now / lastModified) : (ListenerUtil.mutListener.listen(71520) ? (now * lastModified) : (ListenerUtil.mutListener.listen(71519) ? (now + lastModified) : (now - lastModified)))))) != ((ListenerUtil.mutListener.listen(71526) ? (5 % DateUtils.MINUTE_IN_MILLIS) : (ListenerUtil.mutListener.listen(71525) ? (5 / DateUtils.MINUTE_IN_MILLIS) : (ListenerUtil.mutListener.listen(71524) ? (5 - DateUtils.MINUTE_IN_MILLIS) : (ListenerUtil.mutListener.listen(71523) ? (5 + DateUtils.MINUTE_IN_MILLIS) : (5 * DateUtils.MINUTE_IN_MILLIS))))))) : (ListenerUtil.mutListener.listen(71527) ? (((ListenerUtil.mutListener.listen(71522) ? (now % lastModified) : (ListenerUtil.mutListener.listen(71521) ? (now / lastModified) : (ListenerUtil.mutListener.listen(71520) ? (now * lastModified) : (ListenerUtil.mutListener.listen(71519) ? (now + lastModified) : (now - lastModified)))))) == ((ListenerUtil.mutListener.listen(71526) ? (5 % DateUtils.MINUTE_IN_MILLIS) : (ListenerUtil.mutListener.listen(71525) ? (5 / DateUtils.MINUTE_IN_MILLIS) : (ListenerUtil.mutListener.listen(71524) ? (5 - DateUtils.MINUTE_IN_MILLIS) : (ListenerUtil.mutListener.listen(71523) ? (5 + DateUtils.MINUTE_IN_MILLIS) : (5 * DateUtils.MINUTE_IN_MILLIS))))))) : (((ListenerUtil.mutListener.listen(71522) ? (now % lastModified) : (ListenerUtil.mutListener.listen(71521) ? (now / lastModified) : (ListenerUtil.mutListener.listen(71520) ? (now * lastModified) : (ListenerUtil.mutListener.listen(71519) ? (now + lastModified) : (now - lastModified)))))) > ((ListenerUtil.mutListener.listen(71526) ? (5 % DateUtils.MINUTE_IN_MILLIS) : (ListenerUtil.mutListener.listen(71525) ? (5 / DateUtils.MINUTE_IN_MILLIS) : (ListenerUtil.mutListener.listen(71524) ? (5 - DateUtils.MINUTE_IN_MILLIS) : (ListenerUtil.mutListener.listen(71523) ? (5 + DateUtils.MINUTE_IN_MILLIS) : (5 * DateUtils.MINUTE_IN_MILLIS))))))))))))) {
                                if (!ListenerUtil.mutListener.listen(71533)) {
                                    FileUtil.deleteFileOrWarn(lockfile, "Lockfile", logger);
                                }
                                if (!ListenerUtil.mutListener.listen(71535)) {
                                    if (newDatabaseFile.exists()) {
                                        if (!ListenerUtil.mutListener.listen(71534)) {
                                            FileUtil.deleteFileOrWarn(newDatabaseFile, "New Database File", logger);
                                        }
                                    }
                                }
                            } else {
                                if (!ListenerUtil.mutListener.listen(71532)) {
                                    logger.info("Lockfile exists...exiting");
                                }
                                throw new DatabaseMigrationLockedException();
                            }
                        }
                    }
                }
                try {
                    if (!ListenerUtil.mutListener.listen(71539)) {
                        FileUtil.createNewFileOrLog(lockfile, logger);
                    }
                } catch (IOException e) {
                    if (!ListenerUtil.mutListener.listen(71538)) {
                        logger.error("Exception", e);
                    }
                }
                if (!ListenerUtil.mutListener.listen(71580)) {
                    if (!newDatabaseFile.exists()) {
                        if (!ListenerUtil.mutListener.listen(71547)) {
                            logger.info("Database migration to v4 required");
                        }
                        long usableSpace = oldDatabaseFile.getUsableSpace();
                        long fileSize = oldDatabaseFile.length();
                        if (!ListenerUtil.mutListener.listen(71558)) {
                            if ((ListenerUtil.mutListener.listen(71556) ? (usableSpace >= ((ListenerUtil.mutListener.listen(71551) ? (fileSize % 2) : (ListenerUtil.mutListener.listen(71550) ? (fileSize / 2) : (ListenerUtil.mutListener.listen(71549) ? (fileSize - 2) : (ListenerUtil.mutListener.listen(71548) ? (fileSize + 2) : (fileSize * 2))))))) : (ListenerUtil.mutListener.listen(71555) ? (usableSpace <= ((ListenerUtil.mutListener.listen(71551) ? (fileSize % 2) : (ListenerUtil.mutListener.listen(71550) ? (fileSize / 2) : (ListenerUtil.mutListener.listen(71549) ? (fileSize - 2) : (ListenerUtil.mutListener.listen(71548) ? (fileSize + 2) : (fileSize * 2))))))) : (ListenerUtil.mutListener.listen(71554) ? (usableSpace > ((ListenerUtil.mutListener.listen(71551) ? (fileSize % 2) : (ListenerUtil.mutListener.listen(71550) ? (fileSize / 2) : (ListenerUtil.mutListener.listen(71549) ? (fileSize - 2) : (ListenerUtil.mutListener.listen(71548) ? (fileSize + 2) : (fileSize * 2))))))) : (ListenerUtil.mutListener.listen(71553) ? (usableSpace != ((ListenerUtil.mutListener.listen(71551) ? (fileSize % 2) : (ListenerUtil.mutListener.listen(71550) ? (fileSize / 2) : (ListenerUtil.mutListener.listen(71549) ? (fileSize - 2) : (ListenerUtil.mutListener.listen(71548) ? (fileSize + 2) : (fileSize * 2))))))) : (ListenerUtil.mutListener.listen(71552) ? (usableSpace == ((ListenerUtil.mutListener.listen(71551) ? (fileSize % 2) : (ListenerUtil.mutListener.listen(71550) ? (fileSize / 2) : (ListenerUtil.mutListener.listen(71549) ? (fileSize - 2) : (ListenerUtil.mutListener.listen(71548) ? (fileSize + 2) : (fileSize * 2))))))) : (usableSpace < ((ListenerUtil.mutListener.listen(71551) ? (fileSize % 2) : (ListenerUtil.mutListener.listen(71550) ? (fileSize / 2) : (ListenerUtil.mutListener.listen(71549) ? (fileSize - 2) : (ListenerUtil.mutListener.listen(71548) ? (fileSize + 2) : (fileSize * 2))))))))))))) {
                                if (!ListenerUtil.mutListener.listen(71557)) {
                                    FileUtil.deleteFileOrWarn(lockfile, "Lockfile", logger);
                                }
                                throw new DatabaseMigrationFailedException("Not enough space left on device");
                            }
                        }
                        Thread migrateThread = new Thread(new Runnable() {

                            @Override
                            public void run() {
                                try {
                                    if (!ListenerUtil.mutListener.listen(71562)) {
                                        // migrate
                                        SQLiteDatabase.loadLibs(context);
                                    }
                                    SQLiteDatabaseHook hook = new SQLiteDatabaseHook() {

                                        @Override
                                        public void preKey(SQLiteDatabase sqLiteDatabase) {
                                        }

                                        @Override
                                        public void postKey(SQLiteDatabase sqLiteDatabase) {
                                            if (!ListenerUtil.mutListener.listen(71563)) {
                                                // old settings
                                                sqLiteDatabase.rawExecSQL("PRAGMA cipher_page_size = 1024;" + "PRAGMA kdf_iter = 4000;" + "PRAGMA cipher_hmac_algorithm = HMAC_SHA1;" + "PRAGMA cipher_kdf_algorithm = PBKDF2_HMAC_SHA1;");
                                            }
                                        }
                                    };
                                    final int databaseVersion;
                                    try (SQLiteDatabase database = SQLiteDatabase.openOrCreateDatabase(oldDatabaseFile.getAbsolutePath(), databaseKey, null, hook)) {
                                        if (database.isOpen()) {
                                            databaseVersion = database.getVersion();
                                            if (!ListenerUtil.mutListener.listen(71564)) {
                                                logger.info("Original database version: {}", databaseVersion);
                                            }
                                            if (!ListenerUtil.mutListener.listen(71565)) {
                                                database.rawExecSQL("PRAGMA key = '" + databaseKey + "';" + "PRAGMA cipher_page_size = 1024;" + "PRAGMA kdf_iter = 4000;" + "PRAGMA cipher_hmac_algorithm = HMAC_SHA1;" + "PRAGMA cipher_kdf_algorithm = PBKDF2_HMAC_SHA1;" + "ATTACH DATABASE '" + newDatabaseFile.getAbsolutePath() + "' AS threema4 KEY '" + databaseKey + "';" + "PRAGMA threema4.kdf_iter = 1;" + "PRAGMA threema4.cipher_memory_security = OFF;" + "SELECT sqlcipher_export('threema4');" + "PRAGMA threema4.user_version = " + databaseVersion + ";" + "DETACH DATABASE threema4;");
                                            }
                                            if (!ListenerUtil.mutListener.listen(71566)) {
                                                database.close();
                                            }
                                            if (!ListenerUtil.mutListener.listen(71567)) {
                                                logger.info("Database successfully migrated");
                                            }
                                            if (!ListenerUtil.mutListener.listen(71569)) {
                                                if (checkNewDatabase(newDatabaseFile, databaseKey, databaseVersion)) {
                                                    if (!ListenerUtil.mutListener.listen(71568)) {
                                                        migrateSuccess[0] = true;
                                                    }
                                                }
                                            }
                                        }
                                    }
                                } catch (Exception e) {
                                    if (!ListenerUtil.mutListener.listen(71559)) {
                                        logger.info("Database migration FAILED");
                                    }
                                    if (!ListenerUtil.mutListener.listen(71560)) {
                                        logger.error("Exception", e);
                                    }
                                    if (!ListenerUtil.mutListener.listen(71561)) {
                                        FileUtil.deleteFileOrWarn(newDatabaseFile, "New Database File", logger);
                                    }
                                }
                            }
                        });
                        if (!ListenerUtil.mutListener.listen(71570)) {
                            migrateThread.start();
                        }
                        try {
                            if (!ListenerUtil.mutListener.listen(71573)) {
                                migrateThread.join();
                            }
                        } catch (InterruptedException e) {
                            if (!ListenerUtil.mutListener.listen(71571)) {
                                logger.error("Exception", e);
                            }
                            if (!ListenerUtil.mutListener.listen(71572)) {
                                migrateSuccess[0] = false;
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(71579)) {
                            if (migrateSuccess[0]) {
                                if (!ListenerUtil.mutListener.listen(71577)) {
                                    Toast.makeText(context, "Database successfully migrated", Toast.LENGTH_LONG).show();
                                }
                                if (!ListenerUtil.mutListener.listen(71578)) {
                                    logger.info("Migration finished");
                                }
                            } else {
                                if (!ListenerUtil.mutListener.listen(71574)) {
                                    logger.info("Migration failed");
                                }
                                if (!ListenerUtil.mutListener.listen(71575)) {
                                    FileUtil.deleteFileOrWarn(newDatabaseFile, "New Database File", logger);
                                }
                                if (!ListenerUtil.mutListener.listen(71576)) {
                                    FileUtil.deleteFileOrWarn(lockfile, "New Database File", logger);
                                }
                                throw new DatabaseMigrationFailedException();
                            }
                        }
                    } else {
                        try {
                            if (!ListenerUtil.mutListener.listen(71543)) {
                                SQLiteDatabase.loadLibs(context);
                            }
                            if (!ListenerUtil.mutListener.listen(71546)) {
                                if (checkNewDatabase(newDatabaseFile, databaseKey, DATABASE_VERSION)) {
                                    if (!ListenerUtil.mutListener.listen(71544)) {
                                        logger.info("Delete old format database");
                                    }
                                    if (!ListenerUtil.mutListener.listen(71545)) {
                                        FileUtil.deleteFileOrWarn(oldDatabaseFile, "Old Database File", logger);
                                    }
                                } else {
                                    throw new Exception();
                                }
                            }
                        } catch (Exception e) {
                            if (!ListenerUtil.mutListener.listen(71540)) {
                                logger.info("Database checking FAILED");
                            }
                            if (!ListenerUtil.mutListener.listen(71541)) {
                                FileUtil.deleteFileOrWarn(newDatabaseFile, "New Database File", logger);
                            }
                            if (!ListenerUtil.mutListener.listen(71542)) {
                                FileUtil.deleteFileOrWarn(lockfile, "Lockfile", logger);
                            }
                            throw new DatabaseMigrationFailedException();
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(71581)) {
                    FileUtil.deleteFileOrWarn(lockfile, "Lockfile", logger);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(71517)) {
                    logger.info("No old database file found. No migration necessary");
                }
                if (!ListenerUtil.mutListener.listen(71518)) {
                    logger.info("New database file exists = {}", newDatabaseFile.exists());
                }
            }
        }
    }

    private static boolean checkNewDatabase(File newDatabaseFile, String databaseKey, int databaseVersion) {
        try (SQLiteDatabase newDatabase = SQLiteDatabase.openDatabase(newDatabaseFile.getAbsolutePath(), databaseKey, null, 0, new SQLiteDatabaseHook() {

            @Override
            public void preKey(SQLiteDatabase sqLiteDatabase) {
                if (!ListenerUtil.mutListener.listen(71583)) {
                    sqLiteDatabase.rawExecSQL("PRAGMA cipher_default_kdf_iter = 1;");
                }
            }

            @Override
            public void postKey(SQLiteDatabase sqLiteDatabase) {
                if (!ListenerUtil.mutListener.listen(71584)) {
                    sqLiteDatabase.rawExecSQL("PRAGMA kdf_iter = 1;" + "PRAGMA cipher_memory_security = OFF;");
                }
            }
        })) {
            if (!ListenerUtil.mutListener.listen(71590)) {
                if (newDatabase.isOpen()) {
                    if (!ListenerUtil.mutListener.listen(71589)) {
                        if (newDatabase.getVersion() == databaseVersion) {
                            if (!ListenerUtil.mutListener.listen(71587)) {
                                newDatabase.rawExecSQL("SELECT NULL;");
                            }
                            if (!ListenerUtil.mutListener.listen(71588)) {
                                logger.info("New database successfully checked. Version set to {}", databaseVersion);
                            }
                            return true;
                        } else {
                            if (!ListenerUtil.mutListener.listen(71586)) {
                                logger.info("Database version mismatch. old = {} new = {}", databaseVersion, newDatabase.getVersion());
                            }
                        }
                    }
                } else {
                    if (!ListenerUtil.mutListener.listen(71585)) {
                        logger.info("Could not open new database");
                    }
                }
            }
        }
        return false;
    }
}
