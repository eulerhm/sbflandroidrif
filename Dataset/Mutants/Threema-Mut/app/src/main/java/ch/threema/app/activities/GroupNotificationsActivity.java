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
import androidx.annotation.UiThread;
import ch.threema.app.ThreemaApplication;
import ch.threema.storage.models.GroupModel;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class GroupNotificationsActivity extends NotificationsActivity {

    private GroupModel groupModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(3201)) {
            super.onCreate(savedInstanceState);
        }
        int groupId = getIntent().getIntExtra(ThreemaApplication.INTENT_DATA_GROUP, 0);
        if (!ListenerUtil.mutListener.listen(3208)) {
            if ((ListenerUtil.mutListener.listen(3206) ? (groupId >= 0) : (ListenerUtil.mutListener.listen(3205) ? (groupId <= 0) : (ListenerUtil.mutListener.listen(3204) ? (groupId > 0) : (ListenerUtil.mutListener.listen(3203) ? (groupId < 0) : (ListenerUtil.mutListener.listen(3202) ? (groupId != 0) : (groupId == 0))))))) {
                if (!ListenerUtil.mutListener.listen(3207)) {
                    finish();
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(3209)) {
            groupModel = groupService.getById(groupId);
        }
        if (!ListenerUtil.mutListener.listen(3210)) {
            uid = groupService.getUniqueIdString(groupModel);
        }
        if (!ListenerUtil.mutListener.listen(3211)) {
            refreshSettings();
        }
    }

    public void refreshSettings() {
        if (!ListenerUtil.mutListener.listen(3212)) {
            defaultRingtone = ringtoneService.getDefaultGroupRingtone();
        }
        if (!ListenerUtil.mutListener.listen(3213)) {
            selectedRingtone = ringtoneService.getGroupRingtone(uid);
        }
        if (!ListenerUtil.mutListener.listen(3214)) {
            super.refreshSettings();
        }
    }

    @Override
    void notifySettingsChanged() {
        if (!ListenerUtil.mutListener.listen(3215)) {
            this.conversationService.refresh(this.groupModel);
        }
    }

    @Override
    @UiThread
    protected void updateUI() {
        if (!ListenerUtil.mutListener.listen(3216)) {
            super.updateUI();
        }
    }

    @Override
    protected void setupButtons() {
        if (!ListenerUtil.mutListener.listen(3217)) {
            super.setupButtons();
        }
        if (!ListenerUtil.mutListener.listen(3218)) {
            radioSilentExceptMentions.setVisibility(View.VISIBLE);
        }
    }
}
