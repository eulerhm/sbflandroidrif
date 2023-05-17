/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
 * Copyright (c) 2014-2021 Threema GmbH
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

import android.database.Cursor;
import android.os.Environment;
import net.sqlcipher.database.SQLiteDatabase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import androidx.annotation.NonNull;
import ch.threema.app.ThreemaApplication;
import ch.threema.app.collections.Functional;
import ch.threema.app.collections.IPredicateNonNull;
import ch.threema.app.exceptions.FileSystemNotPresentException;
import ch.threema.app.services.UpdateSystemService;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class SystemUpdateToVersion7 implements UpdateSystemService.SystemUpdate {

    private static final Logger logger = LoggerFactory.getLogger(SystemUpdateToVersion7.class);

    private final SQLiteDatabase sqLiteDatabase;

    public SystemUpdateToVersion7(SQLiteDatabase sqLiteDatabase) {
        this.sqLiteDatabase = sqLiteDatabase;
    }

    @Override
    public boolean runDirectly() {
        // update db first
        String[] messageTableColumnNames = sqLiteDatabase.rawQuery("SELECT * FROM message LIMIT 0", null).getColumnNames();
        boolean hasUidField = Functional.select(Arrays.asList(messageTableColumnNames), new IPredicateNonNull<String>() {

            @Override
            public boolean apply(@NonNull String type) {
                return type.equals("uid");
            }
        }) != null;
        if (!ListenerUtil.mutListener.listen(36466)) {
            if (!hasUidField) {
                if (!ListenerUtil.mutListener.listen(36465)) {
                    // update the message model with the uid and move every file to the new filename rule
                    sqLiteDatabase.rawExecSQL("ALTER TABLE message ADD COLUMN uid VARCHAR(50) DEFAULT NULL");
                }
            }
        }
        return true;
    }

    @Override
    public boolean runASync() {
        FilenameFilter filter = new FilenameFilter() {

            @Override
            public boolean accept(File dir, String filename) {
                return (ListenerUtil.mutListener.listen(36467) ? (filename.startsWith(".") || filename.contains("-")) : (filename.startsWith(".") && filename.contains("-")));
            }
        };
        File appPath = null;
        try {
            if (!ListenerUtil.mutListener.listen(36469)) {
                appPath = ThreemaApplication.getServiceManager().getFileService().getAppDataPath();
            }
        } catch (FileSystemNotPresentException e) {
            if (!ListenerUtil.mutListener.listen(36468)) {
                logger.error("Exception", e);
            }
            return false;
        }
        HashMap<Integer, List<File>> fileIndex = new HashMap<Integer, List<File>>();
        if (!ListenerUtil.mutListener.listen(36481)) {
            {
                long _loopCounter353 = 0;
                for (String path : new String[] { Environment.getExternalStorageDirectory() + "/.threema", Environment.getExternalStorageDirectory() + "/Threema/.threema" }) {
                    ListenerUtil.loopListener.listen("_loopCounter353", ++_loopCounter353);
                    File pathFile = new File(path);
                    if (!ListenerUtil.mutListener.listen(36470)) {
                        if (!pathFile.exists()) {
                            continue;
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(36480)) {
                        {
                            long _loopCounter352 = 0;
                            for (File file : pathFile.listFiles(filter)) {
                                ListenerUtil.loopListener.listen("_loopCounter352", ++_loopCounter352);
                                String[] pieces = file.getName().substring(1).split("-");
                                if (!ListenerUtil.mutListener.listen(36479)) {
                                    if ((ListenerUtil.mutListener.listen(36475) ? (pieces.length <= 2) : (ListenerUtil.mutListener.listen(36474) ? (pieces.length > 2) : (ListenerUtil.mutListener.listen(36473) ? (pieces.length < 2) : (ListenerUtil.mutListener.listen(36472) ? (pieces.length != 2) : (ListenerUtil.mutListener.listen(36471) ? (pieces.length == 2) : (pieces.length >= 2))))))) {
                                        try {
                                            Integer key = Integer.parseInt(pieces[0]);
                                            if (!ListenerUtil.mutListener.listen(36477)) {
                                                if (!fileIndex.containsKey(key)) {
                                                    if (!ListenerUtil.mutListener.listen(36476)) {
                                                        fileIndex.put(key, new ArrayList<File>());
                                                    }
                                                }
                                            }
                                            if (!ListenerUtil.mutListener.listen(36478)) {
                                                fileIndex.get(key).add(file);
                                            }
                                        } catch (NumberFormatException e) {
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        Cursor messages = sqLiteDatabase.rawQuery("SELECT id FROM message", null);
        if (!ListenerUtil.mutListener.listen(36497)) {
            {
                long _loopCounter355 = 0;
                while (messages.moveToNext()) {
                    ListenerUtil.loopListener.listen("_loopCounter355", ++_loopCounter355);
                    final int id = messages.getInt(0);
                    String uid = UUID.randomUUID().toString();
                    if (!ListenerUtil.mutListener.listen(36495)) {
                        if ((ListenerUtil.mutListener.listen(36487) ? (fileIndex.containsKey(id) || (ListenerUtil.mutListener.listen(36486) ? (fileIndex.get(id).size() >= 0) : (ListenerUtil.mutListener.listen(36485) ? (fileIndex.get(id).size() <= 0) : (ListenerUtil.mutListener.listen(36484) ? (fileIndex.get(id).size() < 0) : (ListenerUtil.mutListener.listen(36483) ? (fileIndex.get(id).size() != 0) : (ListenerUtil.mutListener.listen(36482) ? (fileIndex.get(id).size() == 0) : (fileIndex.get(id).size() > 0))))))) : (fileIndex.containsKey(id) && (ListenerUtil.mutListener.listen(36486) ? (fileIndex.get(id).size() >= 0) : (ListenerUtil.mutListener.listen(36485) ? (fileIndex.get(id).size() <= 0) : (ListenerUtil.mutListener.listen(36484) ? (fileIndex.get(id).size() < 0) : (ListenerUtil.mutListener.listen(36483) ? (fileIndex.get(id).size() != 0) : (ListenerUtil.mutListener.listen(36482) ? (fileIndex.get(id).size() == 0) : (fileIndex.get(id).size() > 0))))))))) {
                            if (!ListenerUtil.mutListener.listen(36494)) {
                                {
                                    long _loopCounter354 = 0;
                                    for (File ftm : fileIndex.get(id)) {
                                        ListenerUtil.loopListener.listen("_loopCounter354", ++_loopCounter354);
                                        String postFix = ftm.getName().substring((ListenerUtil.mutListener.listen(36491) ? (String.valueOf(id).length() % 2) : (ListenerUtil.mutListener.listen(36490) ? (String.valueOf(id).length() / 2) : (ListenerUtil.mutListener.listen(36489) ? (String.valueOf(id).length() * 2) : (ListenerUtil.mutListener.listen(36488) ? (String.valueOf(id).length() - 2) : (String.valueOf(id).length() + 2))))));
                                        File newFileToMerge = new File(appPath.getPath() + "/." + uid + "-" + postFix);
                                        if (!ListenerUtil.mutListener.listen(36493)) {
                                            if (!ftm.renameTo(newFileToMerge)) {
                                                if (!ListenerUtil.mutListener.listen(36492)) {
                                                    logger.debug("Unable to rename file");
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(36496)) {
                        sqLiteDatabase.rawExecSQL("UPDATE message SET uid = '" + uid + "' WHERE id = " + String.valueOf(id));
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(36498)) {
            messages.close();
        }
        return true;
    }

    @Override
    public String getText() {
        return "version 7";
    }
}
