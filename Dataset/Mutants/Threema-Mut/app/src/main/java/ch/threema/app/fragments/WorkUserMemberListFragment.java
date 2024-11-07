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
package ch.threema.app.fragments;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.widget.AbsListView;
import java.util.ArrayList;
import java.util.List;
import androidx.annotation.NonNull;
import ch.threema.app.R;
import ch.threema.app.adapters.UserListAdapter;
import ch.threema.app.collections.Functional;
import ch.threema.app.collections.IPredicateNonNull;
import ch.threema.app.services.ContactService;
import ch.threema.app.utils.ContactUtil;
import ch.threema.client.ThreemaFeature;
import ch.threema.storage.models.ContactModel;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class WorkUserMemberListFragment extends MemberListFragment {

    @Override
    protected String getBundleName() {
        return "WorkerUserMemberListState";
    }

    @Override
    protected int getEmptyText() {
        return R.string.no_matching_work_contacts;
    }

    @SuppressLint("StaticFieldLeak")
    @Override
    protected void createListAdapter(ArrayList<Integer> checkedItemPositions, final ArrayList<String> preselectedIdentities, ArrayList<String> excludedIdentities, boolean groups, boolean profilePics) {
        if (!ListenerUtil.mutListener.listen(28194)) {
            new AsyncTask<Void, Void, List<ContactModel>>() {

                @Override
                protected List<ContactModel> doInBackground(Void... voids) {
                    final ContactModel.State[] contactStates;
                    if (preferenceService.showInactiveContacts()) {
                        contactStates = new ContactModel.State[] { ContactModel.State.ACTIVE, ContactModel.State.INACTIVE };
                    } else {
                        contactStates = new ContactModel.State[] { ContactModel.State.ACTIVE };
                    }
                    List<ContactModel> contactModels = Functional.filter(contactService.find(new ContactService.Filter() {

                        @Override
                        public ContactModel.State[] states() {
                            return contactStates;
                        }

                        @Override
                        public Integer requiredFeature() {
                            return groups ? ThreemaFeature.GROUP_CHAT : null;
                        }

                        @Override
                        public Boolean fetchMissingFeatureLevel() {
                            return groups;
                        }

                        @Override
                        public Boolean includeMyself() {
                            return false;
                        }

                        @Override
                        public Boolean includeHidden() {
                            return false;
                        }
                    }), new IPredicateNonNull<ContactModel>() {

                        @Override
                        public boolean apply(@NonNull ContactModel type) {
                            return (ListenerUtil.mutListener.listen(28181) ? (type.isWork() || ((ListenerUtil.mutListener.listen(28180) ? (!profilePics && ContactUtil.canReceiveProfilePics(type)) : (!profilePics || ContactUtil.canReceiveProfilePics(type))))) : (type.isWork() && ((ListenerUtil.mutListener.listen(28180) ? (!profilePics && ContactUtil.canReceiveProfilePics(type)) : (!profilePics || ContactUtil.canReceiveProfilePics(type))))));
                        }
                    });
                    if (!ListenerUtil.mutListener.listen(28183)) {
                        if (excludedIdentities != null) {
                            if (!ListenerUtil.mutListener.listen(28182)) {
                                // exclude existing ids
                                contactModels = Functional.filter(contactModels, new IPredicateNonNull<ContactModel>() {

                                    @Override
                                    public boolean apply(@NonNull ContactModel contactModel) {
                                        return !excludedIdentities.contains(contactModel.getIdentity());
                                    }
                                });
                            }
                        }
                    }
                    return contactModels;
                }

                @Override
                protected void onPostExecute(List<ContactModel> contactModels) {
                    if (!ListenerUtil.mutListener.listen(28184)) {
                        adapter = new UserListAdapter(activity, contactModels, preselectedIdentities, checkedItemPositions, contactService, blacklistService, hiddenChatsListService);
                    }
                    if (!ListenerUtil.mutListener.listen(28185)) {
                        setListAdapter(adapter);
                    }
                    if (!ListenerUtil.mutListener.listen(28186)) {
                        getListView().setChoiceMode(AbsListView.CHOICE_MODE_MULTIPLE);
                    }
                    if (!ListenerUtil.mutListener.listen(28192)) {
                        if (listInstanceState != null) {
                            if (!ListenerUtil.mutListener.listen(28190)) {
                                if ((ListenerUtil.mutListener.listen(28188) ? ((ListenerUtil.mutListener.listen(28187) ? (isAdded() || getView() != null) : (isAdded() && getView() != null)) || getActivity() != null) : ((ListenerUtil.mutListener.listen(28187) ? (isAdded() || getView() != null) : (isAdded() && getView() != null)) && getActivity() != null))) {
                                    if (!ListenerUtil.mutListener.listen(28189)) {
                                        getListView().onRestoreInstanceState(listInstanceState);
                                    }
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(28191)) {
                                listInstanceState = null;
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(28193)) {
                        onAdapterCreated();
                    }
                }
            }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        }
    }
}
