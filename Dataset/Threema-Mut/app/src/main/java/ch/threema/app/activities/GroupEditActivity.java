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

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import java.io.File;
import androidx.fragment.app.Fragment;
import ch.threema.app.R;
import ch.threema.app.dialogs.ContactEditDialog;
import ch.threema.app.services.ContactService;
import ch.threema.app.services.DeadlineListService;
import ch.threema.app.services.FileService;
import ch.threema.app.services.GroupService;
import ch.threema.app.services.UserService;
import ch.threema.app.utils.LogUtil;
import ch.threema.storage.models.GroupModel;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public abstract class GroupEditActivity extends ThreemaToolbarActivity {

    protected static final String DIALOG_TAG_GROUPNAME = "groupName";

    protected ContactService contactService;

    protected GroupService groupService;

    protected UserService userService;

    protected FileService fileService;

    protected DeadlineListService hiddenChatsListService;

    private File avatarFile = null;

    private boolean isAvatarRemoved = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(3189)) {
            super.onCreate(savedInstanceState);
        }
        try {
            if (!ListenerUtil.mutListener.listen(3191)) {
                this.contactService = this.serviceManager.getContactService();
            }
            if (!ListenerUtil.mutListener.listen(3192)) {
                this.groupService = this.serviceManager.getGroupService();
            }
            if (!ListenerUtil.mutListener.listen(3193)) {
                this.userService = this.serviceManager.getUserService();
            }
            if (!ListenerUtil.mutListener.listen(3194)) {
                this.fileService = this.serviceManager.getFileService();
            }
            if (!ListenerUtil.mutListener.listen(3195)) {
                this.hiddenChatsListService = this.serviceManager.getHiddenChatsListService();
            }
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(3190)) {
                LogUtil.exception(e, this);
            }
            return;
        }
    }

    protected void launchGroupSetNameAndAvatarDialog() {
        final int inputType = InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PERSON_NAME;
        if (!ListenerUtil.mutListener.listen(3196)) {
            ContactEditDialog.newInstance(R.string.edit_name, R.string.name, -1, inputType, avatarFile, isAvatarRemoved, GroupModel.GROUP_NAME_MAX_LENGTH_BYTES).show(getSupportFragmentManager(), DIALOG_TAG_GROUPNAME);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (!ListenerUtil.mutListener.listen(3197)) {
            super.onActivityResult(requestCode, resultCode, data);
        }
        try {
            Fragment fragment = getSupportFragmentManager().findFragmentByTag(DIALOG_TAG_GROUPNAME);
            if (!ListenerUtil.mutListener.listen(3200)) {
                if ((ListenerUtil.mutListener.listen(3198) ? (fragment != null || fragment.isAdded()) : (fragment != null && fragment.isAdded()))) {
                    if (!ListenerUtil.mutListener.listen(3199)) {
                        fragment.onActivityResult(requestCode, resultCode, data);
                    }
                }
            }
        } catch (Exception e) {
        }
    }
}
