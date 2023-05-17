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

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;
import androidx.annotation.NonNull;
import ch.threema.app.R;
import ch.threema.app.activities.RecipientListBaseActivity;
import ch.threema.app.adapters.RecentListAdapter;
import ch.threema.app.services.ConversationService;
import ch.threema.storage.models.ConversationModel;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class RecentListFragment extends RecipientListFragment {

    private boolean showDistributionLists;

    @Override
    protected boolean isMultiSelectAllowed() {
        return multiSelect;
    }

    @Override
    protected String getBundleName() {
        return "RecentListState";
    }

    @Override
    protected int getEmptyText() {
        return R.string.no_recent_conversations;
    }

    @Override
    protected int getAddIcon() {
        return 0;
    }

    @Override
    protected int getAddText() {
        return 0;
    }

    @Override
    protected Intent getAddIntent() {
        return null;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(27978)) {
            showDistributionLists = ((RecipientListBaseActivity) getActivity()).getShowDistributionLists();
        }
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    protected void createListAdapter(ArrayList<Integer> checkedItemPositions) {
        ConversationService.Filter filter = new ConversationService.Filter() {

            @Override
            public boolean onlyUnread() {
                return false;
            }

            @Override
            public boolean noDistributionLists() {
                return !showDistributionLists;
            }

            @Override
            public boolean noHiddenChats() {
                return preferenceService.isPrivateChatsHidden();
            }

            @Override
            public boolean noInvalid() {
                return true;
            }
        };
        // create a copied list!
        final List<ConversationModel> all;
        final List<ConversationModel> original = conversationService.getAll(false, filter);
        synchronized (original) {
            // create a copy)
            all = new ArrayList<>(original.size());
            if (!ListenerUtil.mutListener.listen(27979)) {
                all.addAll(original);
            }
        }
        if (!ListenerUtil.mutListener.listen(27980)) {
            adapter = new RecentListAdapter(activity, all, checkedItemPositions, contactService, groupService, distributionListService);
        }
        if (!ListenerUtil.mutListener.listen(27981)) {
            setListAdapter(adapter);
        }
        if (!ListenerUtil.mutListener.listen(27988)) {
            if (listInstanceState != null) {
                if (!ListenerUtil.mutListener.listen(27985)) {
                    if ((ListenerUtil.mutListener.listen(27983) ? ((ListenerUtil.mutListener.listen(27982) ? (isAdded() || getView() != null) : (isAdded() && getView() != null)) || getActivity() != null) : ((ListenerUtil.mutListener.listen(27982) ? (isAdded() || getView() != null) : (isAdded() && getView() != null)) && getActivity() != null))) {
                        if (!ListenerUtil.mutListener.listen(27984)) {
                            getListView().onRestoreInstanceState(listInstanceState);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(27986)) {
                    listInstanceState = null;
                }
                if (!ListenerUtil.mutListener.listen(27987)) {
                    restoreCheckedItems(checkedItemPositions);
                }
            }
        }
    }
}
