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
import android.content.Intent;
import android.os.AsyncTask;
import java.util.ArrayList;
import java.util.List;
import ch.threema.app.R;
import ch.threema.app.activities.GroupAddActivity;
import ch.threema.app.adapters.GroupListAdapter;
import ch.threema.app.services.GroupService;
import ch.threema.storage.models.GroupModel;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class GroupListFragment extends RecipientListFragment {

    @Override
    protected boolean isMultiSelectAllowed() {
        return multiSelect;
    }

    @Override
    protected String getBundleName() {
        return "GroupListState";
    }

    @Override
    protected int getEmptyText() {
        return R.string.no_matching_groups;
    }

    @Override
    protected int getAddIcon() {
        return R.drawable.ic_group_outline;
    }

    @Override
    protected int getAddText() {
        return R.string.title_addgroup;
    }

    @Override
    protected Intent getAddIntent() {
        return new Intent(getActivity(), GroupAddActivity.class);
    }

    @SuppressLint("StaticFieldLeak")
    @Override
    protected void createListAdapter(ArrayList<Integer> checkedItemPositions) {
        if (!ListenerUtil.mutListener.listen(27058)) {
            new AsyncTask<Void, Void, List<GroupModel>>() {

                @Override
                protected List<GroupModel> doInBackground(Void... voids) {
                    return groupService.getAll(new GroupService.GroupFilter() {

                        @Override
                        public boolean sortingByDate() {
                            return false;
                        }

                        @Override
                        public boolean sortingByName() {
                            return true;
                        }

                        @Override
                        public boolean sortingAscending() {
                            return true;
                        }

                        @Override
                        public boolean withDeleted() {
                            return false;
                        }

                        @Override
                        public boolean withDeserted() {
                            return false;
                        }
                    });
                }

                @Override
                protected void onPostExecute(List<GroupModel> groupModels) {
                    if (!ListenerUtil.mutListener.listen(27049)) {
                        adapter = new GroupListAdapter(activity, groupModels, checkedItemPositions, groupService);
                    }
                    if (!ListenerUtil.mutListener.listen(27050)) {
                        setListAdapter(adapter);
                    }
                    if (!ListenerUtil.mutListener.listen(27057)) {
                        if (listInstanceState != null) {
                            if (!ListenerUtil.mutListener.listen(27054)) {
                                if ((ListenerUtil.mutListener.listen(27052) ? ((ListenerUtil.mutListener.listen(27051) ? (isAdded() || getView() != null) : (isAdded() && getView() != null)) || getActivity() != null) : ((ListenerUtil.mutListener.listen(27051) ? (isAdded() || getView() != null) : (isAdded() && getView() != null)) && getActivity() != null))) {
                                    if (!ListenerUtil.mutListener.listen(27053)) {
                                        getListView().onRestoreInstanceState(listInstanceState);
                                    }
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(27055)) {
                                listInstanceState = null;
                            }
                            if (!ListenerUtil.mutListener.listen(27056)) {
                                restoreCheckedItems(checkedItemPositions);
                            }
                        }
                    }
                }
            }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        }
    }
}
