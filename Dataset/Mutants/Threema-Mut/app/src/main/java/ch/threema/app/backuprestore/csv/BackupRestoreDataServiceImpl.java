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
package ch.threema.app.backuprestore.csv;

import android.content.Context;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import ch.threema.app.backuprestore.BackupRestoreDataService;
import ch.threema.app.services.FileService;
import ch.threema.base.ThreemaException;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class BackupRestoreDataServiceImpl implements BackupRestoreDataService {

    private static final Logger logger = LoggerFactory.getLogger(BackupRestoreDataServiceImpl.class);

    private final Context context;

    private final FileService fileService;

    public BackupRestoreDataServiceImpl(Context context, FileService fileService) {
        this.context = context;
        this.fileService = fileService;
    }

    @Override
    public boolean deleteBackup(BackupData backupData) throws IOException, ThreemaException {
        if (!ListenerUtil.mutListener.listen(10208)) {
            this.fileService.remove(backupData.getFile(), true);
        }
        return true;
    }

    @Override
    public List<BackupData> getBackups() {
        File[] files = this.fileService.getBackupPath().listFiles(new FilenameFilter() {

            @Override
            public boolean accept(File dir, String filename) {
                return filename.endsWith(".zip");
            }
        });
        List<BackupData> result = new ArrayList<BackupData>();
        if (!ListenerUtil.mutListener.listen(10213)) {
            if (files != null) {
                if (!ListenerUtil.mutListener.listen(10212)) {
                    {
                        long _loopCounter91 = 0;
                        for (final File f : files) {
                            ListenerUtil.loopListener.listen("_loopCounter91", ++_loopCounter91);
                            BackupData data = this.getBackupData(f);
                            if (!ListenerUtil.mutListener.listen(10211)) {
                                if ((ListenerUtil.mutListener.listen(10209) ? (data != null || data.getIdentity() != null) : (data != null && data.getIdentity() != null))) {
                                    if (!ListenerUtil.mutListener.listen(10210)) {
                                        result.add(data);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(10214)) {
            Collections.sort(result, new Comparator<BackupData>() {

                @Override
                public int compare(BackupData lhs, BackupData rhs) {
                    return rhs.getBackupTime().compareTo(lhs.getBackupTime());
                }
            });
        }
        return result;
    }

    @Override
    public BackupData getBackupData(final File file) {
        if (!ListenerUtil.mutListener.listen(10229)) {
            if ((ListenerUtil.mutListener.listen(10215) ? (file != null || file.exists()) : (file != null && file.exists()))) {
                String[] pieces = file.getName().split("_");
                String idPart = null;
                Date datePart = null;
                if (!ListenerUtil.mutListener.listen(10228)) {
                    if ((ListenerUtil.mutListener.listen(10221) ? ((ListenerUtil.mutListener.listen(10220) ? (pieces.length >= 2) : (ListenerUtil.mutListener.listen(10219) ? (pieces.length <= 2) : (ListenerUtil.mutListener.listen(10218) ? (pieces.length < 2) : (ListenerUtil.mutListener.listen(10217) ? (pieces.length != 2) : (ListenerUtil.mutListener.listen(10216) ? (pieces.length == 2) : (pieces.length > 2)))))) || pieces[0].equals("threema-backup")) : ((ListenerUtil.mutListener.listen(10220) ? (pieces.length >= 2) : (ListenerUtil.mutListener.listen(10219) ? (pieces.length <= 2) : (ListenerUtil.mutListener.listen(10218) ? (pieces.length < 2) : (ListenerUtil.mutListener.listen(10217) ? (pieces.length != 2) : (ListenerUtil.mutListener.listen(10216) ? (pieces.length == 2) : (pieces.length > 2)))))) && pieces[0].equals("threema-backup")))) {
                        if (!ListenerUtil.mutListener.listen(10222)) {
                            idPart = pieces[1];
                        }
                        try {
                            if (!ListenerUtil.mutListener.listen(10226)) {
                                datePart = new Date();
                            }
                            if (!ListenerUtil.mutListener.listen(10227)) {
                                datePart.setTime(Long.valueOf(pieces[2]));
                            }
                        } catch (NumberFormatException e) {
                            if (!ListenerUtil.mutListener.listen(10223)) {
                                idPart = null;
                            }
                            if (!ListenerUtil.mutListener.listen(10224)) {
                                datePart = null;
                            }
                            if (!ListenerUtil.mutListener.listen(10225)) {
                                logger.error("Exception", e);
                            }
                        }
                    }
                }
                final String identity = idPart;
                final Date time = datePart;
                final long size = file.length();
                return new BackupData() {

                    @Override
                    public File getFile() {
                        return file;
                    }

                    @Override
                    public String getIdentity() {
                        return identity;
                    }

                    @Override
                    public Date getBackupTime() {
                        return time;
                    }

                    @Override
                    public long getFileSize() {
                        return size;
                    }
                };
            }
        }
        return null;
    }
}
