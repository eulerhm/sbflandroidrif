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
package ch.threema.app.services.systemupdate;

import net.sqlcipher.Cursor;
import net.sqlcipher.database.SQLiteDatabase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.File;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import ch.threema.app.ThreemaApplication;
import ch.threema.app.services.UpdateSystemService;
import ch.threema.app.utils.FileUtil;
import ch.threema.app.utils.TestUtil;
import ch.threema.client.Base32;
import static android.provider.MediaStore.MEDIA_IGNORE_FILENAME;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * add profile pic field to normal, group and distribution list message models
 */
public class SystemUpdateToVersion42 extends UpdateToVersion implements UpdateSystemService.SystemUpdate {

    private static final Logger logger = LoggerFactory.getLogger(SystemUpdateToVersion42.class);

    private final SQLiteDatabase sqLiteDatabase;

    public SystemUpdateToVersion42(SQLiteDatabase sqLiteDatabase) {
        this.sqLiteDatabase = sqLiteDatabase;
    }

    @Override
    public boolean runDirectly() {
        return true;
    }

    @Override
    public boolean runASync() {
        final File appPath = new File(ThreemaApplication.getAppContext().getExternalFilesDir(null), "data");
        final File avatarPath = new File(appPath, "/.avatar");
        final File wallpaperPath = new File(appPath.getPath() + "/.wallpaper");
        Cursor contacts = this.sqLiteDatabase.rawQuery("SELECT identity FROM contacts", null);
        if (!ListenerUtil.mutListener.listen(36222)) {
            if (contacts != null) {
                if (!ListenerUtil.mutListener.listen(36220)) {
                    {
                        long _loopCounter338 = 0;
                        while (contacts.moveToNext()) {
                            ListenerUtil.loopListener.listen("_loopCounter338", ++_loopCounter338);
                            final String identity = contacts.getString(0);
                            if (!ListenerUtil.mutListener.listen(36219)) {
                                if (!TestUtil.empty(identity)) {
                                    if (!ListenerUtil.mutListener.listen(36216)) {
                                        migratePictureFile(avatarPath, ".c-", null, "c-" + identity, identity);
                                    }
                                    if (!ListenerUtil.mutListener.listen(36217)) {
                                        migratePictureFile(avatarPath, ".p-", null, "c-" + identity, identity);
                                    }
                                    if (!ListenerUtil.mutListener.listen(36218)) {
                                        migratePictureFile(wallpaperPath, ".w-", ".w", "c-" + identity, null);
                                    }
                                }
                            }
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(36221)) {
                    contacts.close();
                }
            }
        }
        Cursor groups = this.sqLiteDatabase.rawQuery("SELECT id FROM m_group", null);
        if (!ListenerUtil.mutListener.listen(36232)) {
            if (groups != null) {
                if (!ListenerUtil.mutListener.listen(36230)) {
                    {
                        long _loopCounter339 = 0;
                        while (groups.moveToNext()) {
                            ListenerUtil.loopListener.listen("_loopCounter339", ++_loopCounter339);
                            final int id = groups.getInt(0);
                            if (!ListenerUtil.mutListener.listen(36229)) {
                                if ((ListenerUtil.mutListener.listen(36227) ? (id <= 0) : (ListenerUtil.mutListener.listen(36226) ? (id > 0) : (ListenerUtil.mutListener.listen(36225) ? (id < 0) : (ListenerUtil.mutListener.listen(36224) ? (id != 0) : (ListenerUtil.mutListener.listen(36223) ? (id == 0) : (id >= 0))))))) {
                                    if (!ListenerUtil.mutListener.listen(36228)) {
                                        migratePictureFile(wallpaperPath, ".w-", ".w", "g-" + String.valueOf(id), null);
                                    }
                                }
                            }
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(36231)) {
                    groups.close();
                }
            }
        }
        // delete obsolete distribution list wallpaper
        File distributionListWallpaper = new File(wallpaperPath, ".w0" + MEDIA_IGNORE_FILENAME);
        try {
            if (!ListenerUtil.mutListener.listen(36233)) {
                FileUtil.deleteFileOrWarn(distributionListWallpaper, "", logger);
            }
        } catch (Exception e) {
        }
        return true;
    }

    private boolean migratePictureFile(File path, String filePrefix, String oldFilePrefix, String rawUniqueId, String oldRawUniqueId) {
        String filename_old = (oldFilePrefix != null ? oldFilePrefix : filePrefix) + String.valueOf(oldRawUniqueId != null ? oldRawUniqueId : rawUniqueId).hashCode() + MEDIA_IGNORE_FILENAME;
        String filename_new;
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            if (!ListenerUtil.mutListener.listen(36235)) {
                messageDigest.update(rawUniqueId.getBytes());
            }
            filename_new = filePrefix + Base32.encode(messageDigest.digest()) + MEDIA_IGNORE_FILENAME;
        } catch (NoSuchAlgorithmException e) {
            if (!ListenerUtil.mutListener.listen(36234)) {
                logger.error("Exception", e);
            }
            return false;
        }
        File oldFile = new File(path, filename_old);
        File newFile = new File(path, filename_new);
        if (!ListenerUtil.mutListener.listen(36240)) {
            if (oldFile.exists()) {
                try {
                    if (!ListenerUtil.mutListener.listen(36239)) {
                        if (newFile.exists()) {
                            if (!ListenerUtil.mutListener.listen(36238)) {
                                FileUtil.deleteFileOrWarn(oldFile, "", logger);
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(36237)) {
                                if (!oldFile.renameTo(newFile)) {
                                    if (!ListenerUtil.mutListener.listen(36236)) {
                                        logger.debug("Failed to rename file");
                                    }
                                }
                            }
                        }
                    }
                } catch (Exception e) {
                }
            }
        }
        return true;
    }

    @Override
    public String getText() {
        return "version 42";
    }
}
