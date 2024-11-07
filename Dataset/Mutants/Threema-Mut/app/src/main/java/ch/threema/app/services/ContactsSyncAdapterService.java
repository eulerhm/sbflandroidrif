/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
 * Copyright (c) 2013-2021 Threema GmbH
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
package ch.threema.app.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ch.threema.app.adapters.ContactsSyncAdapter;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class ContactsSyncAdapterService extends Service {

    private static final Logger logger = LoggerFactory.getLogger(ContactsSyncAdapterService.class);

    private ContactsSyncAdapter contactsSyncAdapter = null;

    private static final Object syncAdapterLock = new Object();

    @Override
    public void onCreate() {
        if (!ListenerUtil.mutListener.listen(37174)) {
            super.onCreate();
        }
        synchronized (syncAdapterLock) {
            if (!ListenerUtil.mutListener.listen(37176)) {
                if (contactsSyncAdapter == null) {
                    if (!ListenerUtil.mutListener.listen(37175)) {
                        contactsSyncAdapter = new ContactsSyncAdapter(getApplicationContext(), true);
                    }
                }
            }
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return contactsSyncAdapter.getSyncAdapterBinder();
    }
}
