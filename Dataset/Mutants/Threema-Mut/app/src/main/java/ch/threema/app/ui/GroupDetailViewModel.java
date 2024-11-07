/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
 * Copyright (c) 2020-2021 Threema GmbH
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
package ch.threema.app.ui;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;
import ch.threema.app.ThreemaApplication;
import ch.threema.app.services.ContactService;
import ch.threema.storage.models.ContactModel;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class GroupDetailViewModel extends ViewModel {

    private static final String KEY_AVATAR_FILE = "avatar";

    private static final String KEY_GROUP_NAME = "name";

    private static final String KEY_GROUP_IDENTITIES = "contacts";

    private static final String KEY_AVATAR_REMOVED = "isRemoved";

    private SavedStateHandle savedState;

    private ContactService contactService;

    private MutableLiveData<List<ContactModel>> groupMembers;

    public GroupDetailViewModel(SavedStateHandle savedStateHandle) {
        if (!ListenerUtil.mutListener.listen(45181)) {
            this.savedState = savedStateHandle;
        }
        try {
            if (!ListenerUtil.mutListener.listen(45182)) {
                this.contactService = ThreemaApplication.getServiceManager().getContactService();
            }
        } catch (Exception e) {
        }
        if (!ListenerUtil.mutListener.listen(45183)) {
            this.groupMembers = new MutableLiveData<List<ContactModel>>() {

                @Nullable
                @Override
                public List<ContactModel> getValue() {
                    return getGroupContacts();
                }
            };
        }
    }

    public File getAvatarFile() {
        return this.savedState.get(KEY_AVATAR_FILE);
    }

    public void setAvatarFile(File avatarFile) {
        if (!ListenerUtil.mutListener.listen(45184)) {
            this.savedState.set(KEY_AVATAR_FILE, avatarFile);
        }
    }

    public boolean getIsAvatarRemoved() {
        Boolean isRemoved = this.savedState.get(KEY_AVATAR_REMOVED);
        if (!ListenerUtil.mutListener.listen(45185)) {
            if (isRemoved != null) {
                return isRemoved;
            }
        }
        return false;
    }

    public void setIsAvatarRemoved(boolean isRemoved) {
        if (!ListenerUtil.mutListener.listen(45186)) {
            this.savedState.set(KEY_AVATAR_REMOVED, isRemoved);
        }
    }

    public String getGroupName() {
        return this.savedState.get(KEY_GROUP_NAME);
    }

    public void setGroupName(String groupName) {
        if (!ListenerUtil.mutListener.listen(45187)) {
            this.savedState.set(KEY_GROUP_NAME, groupName);
        }
    }

    public List<ContactModel> getGroupContacts() {
        return addGroupMembersToList(new ArrayList<>(), getGroupIdentities());
    }

    public String[] getGroupIdentities() {
        return this.savedState.get(KEY_GROUP_IDENTITIES);
    }

    public void setGroupContacts(List<ContactModel> groupContacts) {
        if (!ListenerUtil.mutListener.listen(45188)) {
            setGroupIdentities(getIdentitiesFromContactModels(groupContacts));
        }
    }

    public void removeGroupContact(ContactModel contactModel) {
        List<ContactModel> contactModels = getGroupContacts();
        if (!ListenerUtil.mutListener.listen(45189)) {
            contactModels.remove(contactModel);
        }
        if (!ListenerUtil.mutListener.listen(45190)) {
            setGroupContacts(contactModels);
        }
    }

    public void addGroupContacts(@Nullable String[] contactIdentities) {
        if (!ListenerUtil.mutListener.listen(45198)) {
            if ((ListenerUtil.mutListener.listen(45196) ? (contactIdentities != null || (ListenerUtil.mutListener.listen(45195) ? (contactIdentities.length >= 0) : (ListenerUtil.mutListener.listen(45194) ? (contactIdentities.length <= 0) : (ListenerUtil.mutListener.listen(45193) ? (contactIdentities.length < 0) : (ListenerUtil.mutListener.listen(45192) ? (contactIdentities.length != 0) : (ListenerUtil.mutListener.listen(45191) ? (contactIdentities.length == 0) : (contactIdentities.length > 0))))))) : (contactIdentities != null && (ListenerUtil.mutListener.listen(45195) ? (contactIdentities.length >= 0) : (ListenerUtil.mutListener.listen(45194) ? (contactIdentities.length <= 0) : (ListenerUtil.mutListener.listen(45193) ? (contactIdentities.length < 0) : (ListenerUtil.mutListener.listen(45192) ? (contactIdentities.length != 0) : (ListenerUtil.mutListener.listen(45191) ? (contactIdentities.length == 0) : (contactIdentities.length > 0))))))))) {
                if (!ListenerUtil.mutListener.listen(45197)) {
                    setGroupContacts(addGroupMembersToList(getGroupContacts(), contactIdentities));
                }
            }
        }
    }

    public void setGroupIdentities(String[] groupIdentities) {
        if (!ListenerUtil.mutListener.listen(45199)) {
            this.savedState.set(KEY_GROUP_IDENTITIES, groupIdentities);
        }
        if (!ListenerUtil.mutListener.listen(45200)) {
            onDataChanged();
        }
    }

    private String[] getIdentitiesFromContactModels(@NonNull List<ContactModel> groupContacts) {
        final ArrayList<String> identities = new ArrayList<>();
        if (!ListenerUtil.mutListener.listen(45202)) {
            {
                long _loopCounter531 = 0;
                for (ContactModel groupContact : groupContacts) {
                    ListenerUtil.loopListener.listen("_loopCounter531", ++_loopCounter531);
                    if (!ListenerUtil.mutListener.listen(45201)) {
                        identities.add(groupContact.getIdentity());
                    }
                }
            }
        }
        return identities.toArray(new String[identities.size()]);
    }

    private List<ContactModel> addGroupMembersToList(@NonNull List<ContactModel> contacts, @Nullable String[] contactIds) {
        if (!ListenerUtil.mutListener.listen(45212)) {
            if ((ListenerUtil.mutListener.listen(45208) ? (contactIds != null || (ListenerUtil.mutListener.listen(45207) ? (contactIds.length >= 0) : (ListenerUtil.mutListener.listen(45206) ? (contactIds.length <= 0) : (ListenerUtil.mutListener.listen(45205) ? (contactIds.length < 0) : (ListenerUtil.mutListener.listen(45204) ? (contactIds.length != 0) : (ListenerUtil.mutListener.listen(45203) ? (contactIds.length == 0) : (contactIds.length > 0))))))) : (contactIds != null && (ListenerUtil.mutListener.listen(45207) ? (contactIds.length >= 0) : (ListenerUtil.mutListener.listen(45206) ? (contactIds.length <= 0) : (ListenerUtil.mutListener.listen(45205) ? (contactIds.length < 0) : (ListenerUtil.mutListener.listen(45204) ? (contactIds.length != 0) : (ListenerUtil.mutListener.listen(45203) ? (contactIds.length == 0) : (contactIds.length > 0))))))))) {
                if (!ListenerUtil.mutListener.listen(45211)) {
                    {
                        long _loopCounter532 = 0;
                        for (String contactId : contactIds) {
                            ListenerUtil.loopListener.listen("_loopCounter532", ++_loopCounter532);
                            if (!ListenerUtil.mutListener.listen(45210)) {
                                if (!containsModel(contacts, contactId)) {
                                    if (!ListenerUtil.mutListener.listen(45209)) {
                                        contacts.add(contactService.getByIdentity(contactId));
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return contacts;
    }

    public boolean containsModel(List<ContactModel> contacts, String contactId) {
        if (!ListenerUtil.mutListener.listen(45221)) {
            // prevent duplicates - we can't compare models
            if ((ListenerUtil.mutListener.listen(45218) ? (contacts != null || (ListenerUtil.mutListener.listen(45217) ? (contacts.size() >= 0) : (ListenerUtil.mutListener.listen(45216) ? (contacts.size() <= 0) : (ListenerUtil.mutListener.listen(45215) ? (contacts.size() < 0) : (ListenerUtil.mutListener.listen(45214) ? (contacts.size() != 0) : (ListenerUtil.mutListener.listen(45213) ? (contacts.size() == 0) : (contacts.size() > 0))))))) : (contacts != null && (ListenerUtil.mutListener.listen(45217) ? (contacts.size() >= 0) : (ListenerUtil.mutListener.listen(45216) ? (contacts.size() <= 0) : (ListenerUtil.mutListener.listen(45215) ? (contacts.size() < 0) : (ListenerUtil.mutListener.listen(45214) ? (contacts.size() != 0) : (ListenerUtil.mutListener.listen(45213) ? (contacts.size() == 0) : (contacts.size() > 0))))))))) {
                if (!ListenerUtil.mutListener.listen(45220)) {
                    {
                        long _loopCounter533 = 0;
                        for (ContactModel contact : contacts) {
                            ListenerUtil.loopListener.listen("_loopCounter533", ++_loopCounter533);
                            if (!ListenerUtil.mutListener.listen(45219)) {
                                if (contact.getIdentity().equals(contactId)) {
                                    return true;
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    public boolean containsModel(@NonNull String contactId) {
        String[] identities = getGroupIdentities();
        if (!ListenerUtil.mutListener.listen(45222)) {
            if (identities != null) {
                return Arrays.asList(getGroupIdentities()).contains(contactId);
            }
        }
        return false;
    }

    public LiveData<List<ContactModel>> getGroupMembers() {
        return this.groupMembers;
    }

    @SuppressLint("StaticFieldLeak")
    public void onDataChanged() {
        if (!ListenerUtil.mutListener.listen(45224)) {
            new AsyncTask<String, Void, Void>() {

                @Override
                protected Void doInBackground(String... strings) {
                    if (!ListenerUtil.mutListener.listen(45223)) {
                        groupMembers.postValue(getGroupContacts());
                    }
                    return null;
                }
            }.execute();
        }
    }
}
