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
package ch.threema.app.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import androidx.annotation.NonNull;
import ch.threema.app.R;
import ch.threema.app.ThreemaApplication;
import ch.threema.app.dialogs.GenericAlertDialog;
import ch.threema.app.dialogs.ShowOnceDialog;
import ch.threema.app.services.GroupService;
import ch.threema.app.utils.AppRestrictionUtil;
import ch.threema.app.utils.IntentDataUtil;
import ch.threema.app.utils.LogUtil;
import ch.threema.storage.models.ContactModel;
import ch.threema.storage.models.GroupModel;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class GroupAddActivity extends MemberChooseActivity implements GenericAlertDialog.DialogClickListener {

    private static final String BUNDLE_EXISTING_MEMBERS = "ExMem";

    private static final String DIALOG_TAG_NO_MEMBERS = "NoMem";

    private static final String DIALOG_TAG_NOTE_GROUP_HOWTO = "note_group_hint";

    private GroupService groupService;

    private GroupModel groupModel;

    private boolean appendMembers;

    @Override
    protected boolean initActivity(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(2819)) {
            if (!super.initActivity(savedInstanceState)) {
                return false;
            }
        }
        if (!ListenerUtil.mutListener.listen(2821)) {
            if (AppRestrictionUtil.isCreateGroupDisabled(this)) {
                if (!ListenerUtil.mutListener.listen(2820)) {
                    Toast.makeText(this, R.string.disabled_by_policy_short, Toast.LENGTH_LONG).show();
                }
                return false;
            }
        }
        try {
            if (!ListenerUtil.mutListener.listen(2823)) {
                this.groupService = serviceManager.getGroupService();
            }
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(2822)) {
                LogUtil.exception(e, this);
            }
            return false;
        }
        if (!ListenerUtil.mutListener.listen(2824)) {
            initData(savedInstanceState);
        }
        return true;
    }

    @Override
    protected int getNotice() {
        return 0;
    }

    @Override
    protected boolean getAddNextButton() {
        return true;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(2825)) {
            this.appendMembers = false;
        }
        if (!ListenerUtil.mutListener.listen(2826)) {
            this.excludedIdentities = new ArrayList<>();
        }
        try {
            int groupId = IntentDataUtil.getGroupId(this.getIntent());
            if (!ListenerUtil.mutListener.listen(2845)) {
                if ((ListenerUtil.mutListener.listen(2833) ? (this.groupService != null || (ListenerUtil.mutListener.listen(2832) ? (groupId >= 0) : (ListenerUtil.mutListener.listen(2831) ? (groupId <= 0) : (ListenerUtil.mutListener.listen(2830) ? (groupId < 0) : (ListenerUtil.mutListener.listen(2829) ? (groupId != 0) : (ListenerUtil.mutListener.listen(2828) ? (groupId == 0) : (groupId > 0))))))) : (this.groupService != null && (ListenerUtil.mutListener.listen(2832) ? (groupId >= 0) : (ListenerUtil.mutListener.listen(2831) ? (groupId <= 0) : (ListenerUtil.mutListener.listen(2830) ? (groupId < 0) : (ListenerUtil.mutListener.listen(2829) ? (groupId != 0) : (ListenerUtil.mutListener.listen(2828) ? (groupId == 0) : (groupId > 0))))))))) {
                    if (!ListenerUtil.mutListener.listen(2834)) {
                        this.groupModel = this.groupService.getById(groupId);
                    }
                    if (!ListenerUtil.mutListener.listen(2836)) {
                        this.appendMembers = ((ListenerUtil.mutListener.listen(2835) ? (this.groupModel != null || this.groupService.isGroupOwner(this.groupModel)) : (this.groupModel != null && this.groupService.isGroupOwner(this.groupModel))));
                    }
                    String[] excluded = IntentDataUtil.getContactIdentities(this.getIntent());
                    if (!ListenerUtil.mutListener.listen(2844)) {
                        if ((ListenerUtil.mutListener.listen(2842) ? (excluded != null || (ListenerUtil.mutListener.listen(2841) ? (excluded.length >= 0) : (ListenerUtil.mutListener.listen(2840) ? (excluded.length <= 0) : (ListenerUtil.mutListener.listen(2839) ? (excluded.length < 0) : (ListenerUtil.mutListener.listen(2838) ? (excluded.length != 0) : (ListenerUtil.mutListener.listen(2837) ? (excluded.length == 0) : (excluded.length > 0))))))) : (excluded != null && (ListenerUtil.mutListener.listen(2841) ? (excluded.length >= 0) : (ListenerUtil.mutListener.listen(2840) ? (excluded.length <= 0) : (ListenerUtil.mutListener.listen(2839) ? (excluded.length < 0) : (ListenerUtil.mutListener.listen(2838) ? (excluded.length != 0) : (ListenerUtil.mutListener.listen(2837) ? (excluded.length == 0) : (excluded.length > 0))))))))) {
                            if (!ListenerUtil.mutListener.listen(2843)) {
                                this.excludedIdentities = new ArrayList<>(Arrays.asList(excluded));
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(2827)) {
                LogUtil.exception(e, this);
            }
            return;
        }
        if (!ListenerUtil.mutListener.listen(2848)) {
            if (appendMembers) {
                if (!ListenerUtil.mutListener.listen(2847)) {
                    updateToolbarTitle(R.string.add_group_members, R.string.title_select_contacts);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(2846)) {
                    updateToolbarTitle(R.string.title_addgroup, R.string.title_select_contacts);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(2849)) {
            initList();
        }
        if (!ListenerUtil.mutListener.listen(2851)) {
            if (!appendMembers) {
                if (!ListenerUtil.mutListener.listen(2850)) {
                    ShowOnceDialog.newInstance(R.string.title_select_contacts, R.string.note_group_howto).show(getSupportFragmentManager(), DIALOG_TAG_NOTE_GROUP_HOWTO);
                }
            }
        }
    }

    @Override
    protected void menuNext(final List<ContactModel> selectedContacts) {
        // user counts as one contact
        final int previousContacts = this.appendMembers ? excludedIdentities.size() : 1;
        if (!ListenerUtil.mutListener.listen(2870)) {
            if ((ListenerUtil.mutListener.listen(2856) ? (selectedContacts.size() <= ThreemaApplication.MIN_GROUP_MEMBERS_COUNT) : (ListenerUtil.mutListener.listen(2855) ? (selectedContacts.size() > ThreemaApplication.MIN_GROUP_MEMBERS_COUNT) : (ListenerUtil.mutListener.listen(2854) ? (selectedContacts.size() < ThreemaApplication.MIN_GROUP_MEMBERS_COUNT) : (ListenerUtil.mutListener.listen(2853) ? (selectedContacts.size() != ThreemaApplication.MIN_GROUP_MEMBERS_COUNT) : (ListenerUtil.mutListener.listen(2852) ? (selectedContacts.size() == ThreemaApplication.MIN_GROUP_MEMBERS_COUNT) : (selectedContacts.size() >= ThreemaApplication.MIN_GROUP_MEMBERS_COUNT))))))) {
                if (!ListenerUtil.mutListener.listen(2869)) {
                    if ((ListenerUtil.mutListener.listen(2862) ? ((previousContacts + selectedContacts.size()) >= getResources().getInteger(R.integer.max_group_size)) : (ListenerUtil.mutListener.listen(2861) ? ((previousContacts + selectedContacts.size()) <= getResources().getInteger(R.integer.max_group_size)) : (ListenerUtil.mutListener.listen(2860) ? ((previousContacts + selectedContacts.size()) < getResources().getInteger(R.integer.max_group_size)) : (ListenerUtil.mutListener.listen(2859) ? ((previousContacts + selectedContacts.size()) != getResources().getInteger(R.integer.max_group_size)) : (ListenerUtil.mutListener.listen(2858) ? ((previousContacts + selectedContacts.size()) == getResources().getInteger(R.integer.max_group_size)) : ((previousContacts + selectedContacts.size()) > getResources().getInteger(R.integer.max_group_size)))))))) {
                        if (!ListenerUtil.mutListener.listen(2868)) {
                            Toast.makeText(this, String.format(getString(R.string.group_select_max), (ListenerUtil.mutListener.listen(2867) ? (getResources().getInteger(R.integer.max_group_size) % previousContacts) : (ListenerUtil.mutListener.listen(2866) ? (getResources().getInteger(R.integer.max_group_size) / previousContacts) : (ListenerUtil.mutListener.listen(2865) ? (getResources().getInteger(R.integer.max_group_size) * previousContacts) : (ListenerUtil.mutListener.listen(2864) ? (getResources().getInteger(R.integer.max_group_size) + previousContacts) : (getResources().getInteger(R.integer.max_group_size) - previousContacts)))))), Toast.LENGTH_LONG).show();
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(2863)) {
                            createOrUpdateGroup(selectedContacts);
                        }
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(2857)) {
                    GenericAlertDialog.newInstance(R.string.title_addgroup, R.string.group_create_no_members, R.string.yes, R.string.no).show(getSupportFragmentManager(), DIALOG_TAG_NO_MEMBERS);
                }
            }
        }
    }

    private void createOrUpdateGroup(@NonNull final List<ContactModel> selectedContacts) {
        if (!ListenerUtil.mutListener.listen(2876)) {
            // ok!
            if (this.groupModel != null) {
                // edit group mode
                Intent intent = new Intent();
                if (!ListenerUtil.mutListener.listen(2873)) {
                    IntentDataUtil.append(selectedContacts, intent);
                }
                if (!ListenerUtil.mutListener.listen(2874)) {
                    setResult(RESULT_OK, intent);
                }
                if (!ListenerUtil.mutListener.listen(2875)) {
                    finish();
                }
            } else {
                // new group mode
                Intent nextIntent = new Intent(this, GroupAdd2Activity.class);
                if (!ListenerUtil.mutListener.listen(2871)) {
                    IntentDataUtil.append(selectedContacts, nextIntent);
                }
                if (!ListenerUtil.mutListener.listen(2872)) {
                    startActivityForResult(nextIntent, ThreemaActivity.ACTIVITY_ID_GROUP_ADD);
                }
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (!ListenerUtil.mutListener.listen(2877)) {
            super.onActivityResult(requestCode, resultCode, data);
        }
        if (!ListenerUtil.mutListener.listen(2880)) {
            switch(requestCode) {
                case ThreemaActivity.ACTIVITY_ID_GROUP_ADD:
                    if (!ListenerUtil.mutListener.listen(2879)) {
                        if (resultCode != RESULT_CANCELED) {
                            if (!ListenerUtil.mutListener.listen(2878)) {
                                finish();
                            }
                        }
                    }
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        if (!ListenerUtil.mutListener.listen(2881)) {
            super.onSaveInstanceState(outState);
        }
        if (!ListenerUtil.mutListener.listen(2882)) {
            outState.putStringArrayList(BUNDLE_EXISTING_MEMBERS, this.excludedIdentities);
        }
    }

    @Override
    public void onYes(String tag, Object data) {
        if (!ListenerUtil.mutListener.listen(2883)) {
            createOrUpdateGroup(new ArrayList<>());
        }
    }

    @Override
    public void onNo(String tag, Object data) {
    }
}
