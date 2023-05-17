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

import android.content.Context;
import net.sqlcipher.database.SQLiteDatabase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Arrays;
import androidx.annotation.NonNull;
import ch.threema.app.ThreemaApplication;
import ch.threema.app.collections.Functional;
import ch.threema.app.collections.IPredicateNonNull;
import ch.threema.app.exceptions.FileSystemNotPresentException;
import ch.threema.app.managers.ServiceManager;
import ch.threema.app.services.ContactService;
import ch.threema.app.services.PreferenceService;
import ch.threema.app.services.UpdateSystemService;
import ch.threema.app.services.UserService;
import ch.threema.app.utils.SynchronizeContactsUtil;
import ch.threema.localcrypto.MasterKeyLockedException;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class SystemUpdateToVersion12 implements UpdateSystemService.SystemUpdate {

    private static final Logger logger = LoggerFactory.getLogger(SystemUpdateToVersion12.class);

    private final Context context;

    private final SQLiteDatabase sqLiteDatabase;

    public SystemUpdateToVersion12(Context context, SQLiteDatabase sqLiteDatabase) {
        this.context = context;
        this.sqLiteDatabase = sqLiteDatabase;
    }

    @Override
    public boolean runDirectly() {
        // update db first
        String[] messageTableColumnNames = sqLiteDatabase.rawQuery("SELECT * FROM contacts LIMIT 0", null).getColumnNames();
        boolean hasField = Functional.select(Arrays.asList(messageTableColumnNames), new IPredicateNonNull<String>() {

            @Override
            public boolean apply(@NonNull String type) {
                return type.equals("isSynchronized");
            }
        }) != null;
        if (!ListenerUtil.mutListener.listen(36093)) {
            if (!hasField) {
                if (!ListenerUtil.mutListener.listen(36092)) {
                    sqLiteDatabase.rawExecSQL("ALTER TABLE contacts ADD COLUMN isSynchronized TINYINT(1) DEFAULT 0");
                }
            }
        }
        return true;
    }

    @Override
    public boolean runASync() {
        if (!ListenerUtil.mutListener.listen(36094)) {
            // make a manually sync
            SynchronizeContactsUtil.startDirectly();
        }
        ServiceManager serviceManager = ThreemaApplication.getServiceManager();
        if (!ListenerUtil.mutListener.listen(36100)) {
            if (serviceManager != null) {
                try {
                    ContactService contactService = serviceManager.getContactService();
                    PreferenceService preferenceService = serviceManager.getPreferenceService();
                    if (!ListenerUtil.mutListener.listen(36099)) {
                        if ((ListenerUtil.mutListener.listen(36096) ? (preferenceService != null || preferenceService.isSyncContacts()) : (preferenceService != null && preferenceService.isSyncContacts()))) {
                            UserService userService = serviceManager.getUserService();
                            if (!ListenerUtil.mutListener.listen(36098)) {
                                if (userService != null) {
                                    if (!ListenerUtil.mutListener.listen(36097)) {
                                        userService.enableAccountAutoSync(true);
                                    }
                                }
                            }
                        }
                    }
                } catch (MasterKeyLockedException | FileSystemNotPresentException e) {
                    if (!ListenerUtil.mutListener.listen(36095)) {
                        logger.error("Exception", e);
                    }
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public String getText() {
        return "version 12";
    }
}
