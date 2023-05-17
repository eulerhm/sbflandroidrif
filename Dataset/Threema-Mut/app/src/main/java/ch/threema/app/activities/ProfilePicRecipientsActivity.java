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
package ch.threema.app.activities;

import android.os.Bundle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import ch.threema.app.R;
import ch.threema.app.services.IdListService;
import ch.threema.app.utils.LogUtil;
import ch.threema.storage.models.ContactModel;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class ProfilePicRecipientsActivity extends MemberChooseActivity {

    private IdListService profilePicRecipientsService;

    @Override
    public void onCreate(final Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(5355)) {
            super.onCreate(savedInstanceState);
        }
    }

    @Override
    protected boolean initActivity(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(5356)) {
            if (!super.initActivity(savedInstanceState)) {
                return false;
            }
        }
        try {
            if (!ListenerUtil.mutListener.listen(5358)) {
                this.profilePicRecipientsService = serviceManager.getProfilePicRecipientsService();
            }
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(5357)) {
                LogUtil.exception(e, this);
            }
            return false;
        }
        if (!ListenerUtil.mutListener.listen(5359)) {
            initData(savedInstanceState);
        }
        return true;
    }

    @Override
    protected int getNotice() {
        return R.string.prefs_sum_receive_profilepics_recipients_list;
    }

    @Override
    protected boolean getAddNextButton() {
        return false;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(5368)) {
            if (savedInstanceState == null) {
                String[] ids = profilePicRecipientsService.getAll();
                if (!ListenerUtil.mutListener.listen(5367)) {
                    if ((ListenerUtil.mutListener.listen(5365) ? (ids != null || (ListenerUtil.mutListener.listen(5364) ? (ids.length >= 0) : (ListenerUtil.mutListener.listen(5363) ? (ids.length <= 0) : (ListenerUtil.mutListener.listen(5362) ? (ids.length < 0) : (ListenerUtil.mutListener.listen(5361) ? (ids.length != 0) : (ListenerUtil.mutListener.listen(5360) ? (ids.length == 0) : (ids.length > 0))))))) : (ids != null && (ListenerUtil.mutListener.listen(5364) ? (ids.length >= 0) : (ListenerUtil.mutListener.listen(5363) ? (ids.length <= 0) : (ListenerUtil.mutListener.listen(5362) ? (ids.length < 0) : (ListenerUtil.mutListener.listen(5361) ? (ids.length != 0) : (ListenerUtil.mutListener.listen(5360) ? (ids.length == 0) : (ids.length > 0))))))))) {
                        if (!ListenerUtil.mutListener.listen(5366)) {
                            preselectedIdentities = new ArrayList<>(Arrays.asList(ids));
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(5369)) {
            updateToolbarTitle(R.string.profile_picture, R.string.title_choose_recipient);
        }
        if (!ListenerUtil.mutListener.listen(5370)) {
            initList();
        }
    }

    @Override
    protected void menuNext(List<ContactModel> selectedContacts) {
        if (!ListenerUtil.mutListener.listen(5387)) {
            if ((ListenerUtil.mutListener.listen(5375) ? (selectedContacts.size() >= 0) : (ListenerUtil.mutListener.listen(5374) ? (selectedContacts.size() <= 0) : (ListenerUtil.mutListener.listen(5373) ? (selectedContacts.size() < 0) : (ListenerUtil.mutListener.listen(5372) ? (selectedContacts.size() != 0) : (ListenerUtil.mutListener.listen(5371) ? (selectedContacts.size() == 0) : (selectedContacts.size() > 0))))))) {
                List<String> ids = new ArrayList<>(selectedContacts.size());
                if (!ListenerUtil.mutListener.listen(5378)) {
                    {
                        long _loopCounter37 = 0;
                        for (ContactModel contactModel : selectedContacts) {
                            ListenerUtil.loopListener.listen("_loopCounter37", ++_loopCounter37);
                            if (!ListenerUtil.mutListener.listen(5377)) {
                                if (contactModel != null) {
                                    if (!ListenerUtil.mutListener.listen(5376)) {
                                        ids.add(contactModel.getIdentity());
                                    }
                                }
                            }
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(5386)) {
                    if ((ListenerUtil.mutListener.listen(5383) ? (ids.size() >= 0) : (ListenerUtil.mutListener.listen(5382) ? (ids.size() <= 0) : (ListenerUtil.mutListener.listen(5381) ? (ids.size() < 0) : (ListenerUtil.mutListener.listen(5380) ? (ids.size() != 0) : (ListenerUtil.mutListener.listen(5379) ? (ids.size() == 0) : (ids.size() > 0))))))) {
                        if (!ListenerUtil.mutListener.listen(5384)) {
                            profilePicRecipientsService.addAll(ids.toArray(new String[ids.size()]));
                        }
                        if (!ListenerUtil.mutListener.listen(5385)) {
                            finish();
                        }
                        return;
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(5388)) {
            profilePicRecipientsService.removeAll();
        }
        if (!ListenerUtil.mutListener.listen(5389)) {
            finish();
        }
    }

    @Override
    public void onBackPressed() {
        if (!ListenerUtil.mutListener.listen(5390)) {
            this.menuNext(getSelectedContacts());
        }
    }
}
