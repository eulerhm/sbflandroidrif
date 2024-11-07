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
package ch.threema.app.activities;

import android.os.Bundle;
import android.view.View;
import ch.threema.app.ThreemaApplication;
import ch.threema.app.utils.TestUtil;
import ch.threema.storage.models.ContactModel;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class ContactNotificationsActivity extends NotificationsActivity {

    private String identity;

    private ContactModel contactModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(2313)) {
            super.onCreate(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(2314)) {
            this.identity = getIntent().getStringExtra(ThreemaApplication.INTENT_DATA_CONTACT);
        }
        if (!ListenerUtil.mutListener.listen(2316)) {
            if (TestUtil.empty(this.identity)) {
                if (!ListenerUtil.mutListener.listen(2315)) {
                    finish();
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(2317)) {
            this.contactModel = contactService.getByIdentity(identity);
        }
        if (!ListenerUtil.mutListener.listen(2318)) {
            this.uid = contactService.getUniqueIdString(contactModel);
        }
        if (!ListenerUtil.mutListener.listen(2319)) {
            refreshSettings();
        }
    }

    public void refreshSettings() {
        if (!ListenerUtil.mutListener.listen(2320)) {
            defaultRingtone = ringtoneService.getDefaultContactRingtone();
        }
        if (!ListenerUtil.mutListener.listen(2321)) {
            selectedRingtone = ringtoneService.getContactRingtone(uid);
        }
        if (!ListenerUtil.mutListener.listen(2322)) {
            super.refreshSettings();
        }
    }

    @Override
    void notifySettingsChanged() {
        if (!ListenerUtil.mutListener.listen(2323)) {
            this.conversationService.refresh(this.contactModel);
        }
    }

    @Override
    protected void setupButtons() {
        if (!ListenerUtil.mutListener.listen(2324)) {
            super.setupButtons();
        }
        if (!ListenerUtil.mutListener.listen(2325)) {
            radioSilentExceptMentions.setVisibility(View.GONE);
        }
    }
}
