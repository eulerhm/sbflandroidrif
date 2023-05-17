/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
 * Copyright (c) 2017-2021 Threema GmbH
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

import android.content.ContentValues;
import android.content.Context;
import android.widget.Toast;
import net.sqlcipher.Cursor;
import net.sqlcipher.SQLException;
import net.sqlcipher.database.SQLiteDatabase;
import net.sqlcipher.database.SQLiteDatabaseHook;
import net.sqlcipher.database.SQLiteOpenHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.File;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import ch.threema.app.exceptions.DatabaseMigrationFailedException;
import ch.threema.app.utils.FileUtil;
import ch.threema.client.IdentityStoreInterface;
import ch.threema.client.NonceStoreInterface;
import ch.threema.client.Utils;
import ch.threema.localcrypto.MasterKey;
import ch.threema.localcrypto.MasterKeyLockedException;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class NonceDatabaseBlobService extends SQLiteOpenHelper implements NonceStoreInterface {

    private static final Logger logger = LoggerFactory.getLogger(NonceDatabaseBlobService.class);

    public static final String DATABASE_NAME = "threema-nonce-blob.db";

    public static final String DATABASE_NAME_V4 = "threema-nonce-blob4.db";

    private static final int DATABASE_VERSION = 1;

    private final String key;

    private final IdentityStoreInterface identityStore;

    public NonceDatabaseBlobService(final Context context, final MasterKey masterKey, final int nonceSqlCipherVersion, IdentityStoreInterface identityStore) throws MasterKeyLockedException {
        super(context, (ListenerUtil.mutListener.listen(71628) ? (nonceSqlCipherVersion >= 4) : (ListenerUtil.mutListener.listen(71627) ? (nonceSqlCipherVersion <= 4) : (ListenerUtil.mutListener.listen(71626) ? (nonceSqlCipherVersion > 4) : (ListenerUtil.mutListener.listen(71625) ? (nonceSqlCipherVersion < 4) : (ListenerUtil.mutListener.listen(71624) ? (nonceSqlCipherVersion != 4) : (nonceSqlCipherVersion == 4)))))) ? DATABASE_NAME_V4 : DATABASE_NAME, null, DATABASE_VERSION, new SQLiteDatabaseHook() {

            @Override
            public void preKey(SQLiteDatabase sqLiteDatabase) {
                if (!ListenerUtil.mutListener.listen(71630)) {
                    if (nonceSqlCipherVersion == 3) {
                        if (!ListenerUtil.mutListener.listen(71629)) {
                            sqLiteDatabase.rawExecSQL("PRAGMA cipher_default_page_size = 1024;" + "PRAGMA cipher_default_kdf_iter = 4000;" + "PRAGMA cipher_default_hmac_algorithm = HMAC_SHA1;" + "PRAGMA cipher_default_kdf_algorithm = PBKDF2_HMAC_SHA1;");
                        }
                    }
                }
            }

            @Override
            public void postKey(SQLiteDatabase sqLiteDatabase) {
                if (!ListenerUtil.mutListener.listen(71633)) {
                    if (nonceSqlCipherVersion == 4) {
                        if (!ListenerUtil.mutListener.listen(71632)) {
                            // turn off memory wiping for now due to https://github.com/sqlcipher/android-database-sqlcipher/issues/411
                            sqLiteDatabase.rawExecSQL("PRAGMA cipher_memory_security = OFF;");
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(71631)) {
                            sqLiteDatabase.rawExecSQL("PRAGMA cipher_page_size = 1024;" + "PRAGMA kdf_iter = 4000;" + "PRAGMA cipher_hmac_algorithm = HMAC_SHA1;" + "PRAGMA cipher_kdf_algorithm = PBKDF2_HMAC_SHA1;");
                        }
                    }
                }
            }
        });
        this.key = (ListenerUtil.mutListener.listen(71638) ? (nonceSqlCipherVersion >= 3) : (ListenerUtil.mutListener.listen(71637) ? (nonceSqlCipherVersion <= 3) : (ListenerUtil.mutListener.listen(71636) ? (nonceSqlCipherVersion > 3) : (ListenerUtil.mutListener.listen(71635) ? (nonceSqlCipherVersion < 3) : (ListenerUtil.mutListener.listen(71634) ? (nonceSqlCipherVersion != 3) : (nonceSqlCipherVersion == 3)))))) ? "x\"" + Utils.byteArrayToHexString(masterKey.getKey()) + "\"" : "";
        this.identityStore = identityStore;
    }

    public synchronized SQLiteDatabase getWritableDatabase() {
        return super.getWritableDatabase(this.key);
    }

    public synchronized SQLiteDatabase getReadableDatabase() {
        return super.getReadableDatabase(this.key);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        if (!ListenerUtil.mutListener.listen(71639)) {
            sqLiteDatabase.execSQL("CREATE TABLE `threema_nonce` (`nonce` BLOB PRIMARY KEY)");
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }

    @Override
    public boolean exists(byte[] nonce) {
        boolean hasRecord = false;
        Cursor c = this.getReadableDatabase().rawQuery("SELECT COUNT(*) FROM `threema_nonce` WHERE `nonce` = x'" + Utils.byteArrayToHexString(nonce) + "' OR `nonce` = x'" + Utils.byteArrayToHexString(this.hashNonce(nonce)) + "'", null);
        if (!ListenerUtil.mutListener.listen(71648)) {
            if (c != null) {
                if (!ListenerUtil.mutListener.listen(71646)) {
                    if (c.moveToFirst()) {
                        if (!ListenerUtil.mutListener.listen(71645)) {
                            hasRecord = (ListenerUtil.mutListener.listen(71644) ? (c.getInt(0) >= 0) : (ListenerUtil.mutListener.listen(71643) ? (c.getInt(0) <= 0) : (ListenerUtil.mutListener.listen(71642) ? (c.getInt(0) < 0) : (ListenerUtil.mutListener.listen(71641) ? (c.getInt(0) != 0) : (ListenerUtil.mutListener.listen(71640) ? (c.getInt(0) == 0) : (c.getInt(0) > 0))))));
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(71647)) {
                    c.close();
                }
            }
        }
        return hasRecord;
    }

    @Override
    public boolean store(byte[] nonce) {
        ContentValues c = new ContentValues();
        if (!ListenerUtil.mutListener.listen(71649)) {
            c.put("nonce", this.hashNonce(nonce));
        }
        try {
            return (ListenerUtil.mutListener.listen(71655) ? (this.getWritableDatabase().insertOrThrow("threema_nonce", null, c) <= 1) : (ListenerUtil.mutListener.listen(71654) ? (this.getWritableDatabase().insertOrThrow("threema_nonce", null, c) > 1) : (ListenerUtil.mutListener.listen(71653) ? (this.getWritableDatabase().insertOrThrow("threema_nonce", null, c) < 1) : (ListenerUtil.mutListener.listen(71652) ? (this.getWritableDatabase().insertOrThrow("threema_nonce", null, c) != 1) : (ListenerUtil.mutListener.listen(71651) ? (this.getWritableDatabase().insertOrThrow("threema_nonce", null, c) == 1) : (this.getWritableDatabase().insertOrThrow("threema_nonce", null, c) >= 1))))));
        } catch (SQLException x) {
            if (!ListenerUtil.mutListener.listen(71650)) {
                // ignore exception
                logger.error("Exception", x);
            }
        }
        return false;
    }

    public long getCount() {
        long size = 0;
        Cursor c = this.getReadableDatabase().rawQuery("SELECT COUNT(*) FROM `threema_nonce`", null);
        if (!ListenerUtil.mutListener.listen(71659)) {
            if (c != null) {
                if (!ListenerUtil.mutListener.listen(71657)) {
                    if (c.moveToFirst()) {
                        if (!ListenerUtil.mutListener.listen(71656)) {
                            size = c.getLong(0);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(71658)) {
                    c.close();
                }
            }
        }
        return size;
    }

    public static void tryMigrateToV4(Context context, final String databaseKey) throws DatabaseMigrationFailedException {
        File oldDatabaseFile = context.getDatabasePath(DATABASE_NAME);
        File newDatabaseFile = context.getDatabasePath(DATABASE_NAME_V4);
        if (!ListenerUtil.mutListener.listen(71688)) {
            if (oldDatabaseFile.exists()) {
                if (!ListenerUtil.mutListener.listen(71687)) {
                    if (!newDatabaseFile.exists()) {
                        if (!ListenerUtil.mutListener.listen(71662)) {
                            logger.debug("Nonce database migration to v4 required");
                        }
                        long usableSpace = oldDatabaseFile.getUsableSpace();
                        long fileSize = oldDatabaseFile.length();
                        if (!ListenerUtil.mutListener.listen(71672)) {
                            if ((ListenerUtil.mutListener.listen(71671) ? (usableSpace >= ((ListenerUtil.mutListener.listen(71666) ? (fileSize % 2) : (ListenerUtil.mutListener.listen(71665) ? (fileSize / 2) : (ListenerUtil.mutListener.listen(71664) ? (fileSize - 2) : (ListenerUtil.mutListener.listen(71663) ? (fileSize + 2) : (fileSize * 2))))))) : (ListenerUtil.mutListener.listen(71670) ? (usableSpace <= ((ListenerUtil.mutListener.listen(71666) ? (fileSize % 2) : (ListenerUtil.mutListener.listen(71665) ? (fileSize / 2) : (ListenerUtil.mutListener.listen(71664) ? (fileSize - 2) : (ListenerUtil.mutListener.listen(71663) ? (fileSize + 2) : (fileSize * 2))))))) : (ListenerUtil.mutListener.listen(71669) ? (usableSpace > ((ListenerUtil.mutListener.listen(71666) ? (fileSize % 2) : (ListenerUtil.mutListener.listen(71665) ? (fileSize / 2) : (ListenerUtil.mutListener.listen(71664) ? (fileSize - 2) : (ListenerUtil.mutListener.listen(71663) ? (fileSize + 2) : (fileSize * 2))))))) : (ListenerUtil.mutListener.listen(71668) ? (usableSpace != ((ListenerUtil.mutListener.listen(71666) ? (fileSize % 2) : (ListenerUtil.mutListener.listen(71665) ? (fileSize / 2) : (ListenerUtil.mutListener.listen(71664) ? (fileSize - 2) : (ListenerUtil.mutListener.listen(71663) ? (fileSize + 2) : (fileSize * 2))))))) : (ListenerUtil.mutListener.listen(71667) ? (usableSpace == ((ListenerUtil.mutListener.listen(71666) ? (fileSize % 2) : (ListenerUtil.mutListener.listen(71665) ? (fileSize / 2) : (ListenerUtil.mutListener.listen(71664) ? (fileSize - 2) : (ListenerUtil.mutListener.listen(71663) ? (fileSize + 2) : (fileSize * 2))))))) : (usableSpace < ((ListenerUtil.mutListener.listen(71666) ? (fileSize % 2) : (ListenerUtil.mutListener.listen(71665) ? (fileSize / 2) : (ListenerUtil.mutListener.listen(71664) ? (fileSize - 2) : (ListenerUtil.mutListener.listen(71663) ? (fileSize + 2) : (fileSize * 2))))))))))))) {
                                throw new DatabaseMigrationFailedException("Not enough space left on device");
                            }
                        }
                        try {
                            // migrate
                            SQLiteDatabaseHook hook = new SQLiteDatabaseHook() {

                                @Override
                                public void preKey(SQLiteDatabase sqLiteDatabase) {
                                }

                                @Override
                                public void postKey(SQLiteDatabase sqLiteDatabase) {
                                    if (!ListenerUtil.mutListener.listen(71676)) {
                                        // old settings
                                        sqLiteDatabase.rawExecSQL("PRAGMA cipher_page_size = 1024;" + "PRAGMA kdf_iter = 4000;" + "PRAGMA cipher_hmac_algorithm = HMAC_SHA1;" + "PRAGMA cipher_kdf_algorithm = PBKDF2_HMAC_SHA1;");
                                    }
                                }
                            };
                            try (SQLiteDatabase database = SQLiteDatabase.openOrCreateDatabase(oldDatabaseFile.getAbsolutePath(), databaseKey, null, hook)) {
                                if (!ListenerUtil.mutListener.listen(71686)) {
                                    if (database.isOpen()) {
                                        if (!ListenerUtil.mutListener.listen(71677)) {
                                            database.rawExecSQL("PRAGMA key = '" + databaseKey + "';" + "PRAGMA cipher_page_size = 1024;" + "PRAGMA kdf_iter = 4000;" + "PRAGMA cipher_hmac_algorithm = HMAC_SHA1;" + "PRAGMA cipher_kdf_algorithm = PBKDF2_HMAC_SHA1;" + "ATTACH DATABASE '" + newDatabaseFile.getAbsolutePath() + "' AS nonce4 KEY '';" + "PRAGMA nonce4.cipher_memory_security = OFF;" + "SELECT sqlcipher_export('nonce4');" + "PRAGMA nonce4.user_version = " + DATABASE_VERSION + ";" + "DETACH DATABASE nonce4;");
                                        }
                                        if (!ListenerUtil.mutListener.listen(71678)) {
                                            database.close();
                                        }
                                        if (!ListenerUtil.mutListener.listen(71679)) {
                                            logger.debug("Nonce database successfully migrated");
                                        }
                                        // test new database
                                        try (SQLiteDatabase newDatabase = SQLiteDatabase.openDatabase(newDatabaseFile.getAbsolutePath(), "", null, 0, new SQLiteDatabaseHook() {

                                            @Override
                                            public void preKey(SQLiteDatabase sqLiteDatabase) {
                                            }

                                            @Override
                                            public void postKey(SQLiteDatabase sqLiteDatabase) {
                                                if (!ListenerUtil.mutListener.listen(71680)) {
                                                    sqLiteDatabase.rawExecSQL("PRAGMA cipher_memory_security = OFF;");
                                                }
                                            }
                                        })) {
                                            if (!ListenerUtil.mutListener.listen(71685)) {
                                                if (newDatabase.isOpen()) {
                                                    if (!ListenerUtil.mutListener.listen(71682)) {
                                                        newDatabase.rawExecSQL("SELECT NULL;");
                                                    }
                                                    if (!ListenerUtil.mutListener.listen(71683)) {
                                                        logger.debug("New nonce database successfully checked");
                                                    }
                                                    if (!ListenerUtil.mutListener.listen(71684)) {
                                                        Toast.makeText(context, "Database successfully migrated", Toast.LENGTH_LONG).show();
                                                    }
                                                } else {
                                                    if (!ListenerUtil.mutListener.listen(71681)) {
                                                        logger.debug("Could not open new nonce database");
                                                    }
                                                    throw new DatabaseMigrationFailedException();
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        } catch (Exception e) {
                            if (!ListenerUtil.mutListener.listen(71673)) {
                                logger.debug("Nonce database migration FAILED");
                            }
                            if (!ListenerUtil.mutListener.listen(71674)) {
                                logger.error("Exception", e);
                            }
                            if (!ListenerUtil.mutListener.listen(71675)) {
                                FileUtil.deleteFileOrWarn(newDatabaseFile, "New Nonce Database File", logger);
                            }
                            throw new DatabaseMigrationFailedException();
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(71660)) {
                            logger.debug("Delete old format nonce database");
                        }
                        if (!ListenerUtil.mutListener.listen(71661)) {
                            FileUtil.deleteFileOrWarn(oldDatabaseFile, "Old Nonce Database File", logger);
                        }
                    }
                }
            }
        }
    }

    private byte[] hashNonce(byte[] nonce) {
        // This serves to make it impossible to correlate the nonce DBs of users to determine whether they have been communicating. */
        String identity = identityStore.getIdentity();
        if (identity == null) {
            return nonce;
        }
        try {
            Mac mobileNoMac = Mac.getInstance("HmacSHA256");
            if (!ListenerUtil.mutListener.listen(71689)) {
                mobileNoMac.init(new SecretKeySpec(identity.getBytes(), "HmacSHA256"));
            }
            return mobileNoMac.doFinal(nonce);
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            throw new RuntimeException(e);
        }
    }
}
