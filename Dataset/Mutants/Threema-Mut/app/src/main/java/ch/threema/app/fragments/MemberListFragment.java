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

import android.app.Activity;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.ProgressBar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;
import java.util.HashSet;
import androidx.annotation.StringRes;
import androidx.fragment.app.ListFragment;
import ch.threema.app.R;
import ch.threema.app.ThreemaApplication;
import ch.threema.app.activities.GroupAddActivity;
import ch.threema.app.activities.MemberChooseActivity;
import ch.threema.app.activities.ProfilePicRecipientsActivity;
import ch.threema.app.adapters.FilterableListAdapter;
import ch.threema.app.managers.ServiceManager;
import ch.threema.app.services.ContactService;
import ch.threema.app.services.ConversationService;
import ch.threema.app.services.DeadlineListService;
import ch.threema.app.services.DistributionListService;
import ch.threema.app.services.GroupService;
import ch.threema.app.services.IdListService;
import ch.threema.app.services.PreferenceService;
import ch.threema.app.ui.EmptyView;
import ch.threema.app.utils.LogUtil;
import ch.threema.base.ThreemaException;
import ch.threema.storage.models.ContactModel;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public abstract class MemberListFragment extends ListFragment {

    public static final String BUNDLE_ARG_PRESELECTED = "pres";

    public static final String BUNDLE_ARG_EXCLUDED = "excl";

    protected ContactService contactService;

    protected GroupService groupService;

    protected DistributionListService distributionListService;

    protected ConversationService conversationService;

    protected PreferenceService preferenceService;

    protected IdListService blacklistService;

    protected DeadlineListService hiddenChatsListService;

    protected Activity activity;

    protected Parcelable listInstanceState;

    protected FloatingActionButton floatingActionButton;

    protected ArrayList<String> preselectedIdentities = new ArrayList<>();

    protected ArrayList<String> excludedIdentities = new ArrayList<>();

    protected ProgressBar progressBar;

    protected View topLayout;

    protected FilterableListAdapter adapter;

    private SelectionListener selectionListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(27059)) {
            activity = getActivity();
        }
        if (!ListenerUtil.mutListener.listen(27060)) {
            selectionListener = (MemberChooseActivity) activity;
        }
        final ServiceManager serviceManager = ThreemaApplication.getServiceManager();
        try {
            if (!ListenerUtil.mutListener.listen(27062)) {
                contactService = serviceManager.getContactService();
            }
            if (!ListenerUtil.mutListener.listen(27063)) {
                groupService = serviceManager.getGroupService();
            }
            if (!ListenerUtil.mutListener.listen(27064)) {
                distributionListService = serviceManager.getDistributionListService();
            }
            if (!ListenerUtil.mutListener.listen(27065)) {
                blacklistService = serviceManager.getBlackListService();
            }
            if (!ListenerUtil.mutListener.listen(27066)) {
                conversationService = serviceManager.getConversationService();
            }
            if (!ListenerUtil.mutListener.listen(27067)) {
                preferenceService = serviceManager.getPreferenceService();
            }
            if (!ListenerUtil.mutListener.listen(27068)) {
                hiddenChatsListService = serviceManager.getHiddenChatsListService();
            }
        } catch (ThreemaException e) {
            if (!ListenerUtil.mutListener.listen(27061)) {
                LogUtil.exception(e, getActivity());
            }
            return null;
        }
        Bundle bundle = getArguments();
        if (!ListenerUtil.mutListener.listen(27071)) {
            if (bundle != null) {
                if (!ListenerUtil.mutListener.listen(27069)) {
                    preselectedIdentities = bundle.getStringArrayList(BUNDLE_ARG_PRESELECTED);
                }
                if (!ListenerUtil.mutListener.listen(27070)) {
                    excludedIdentities = bundle.getStringArrayList(BUNDLE_ARG_EXCLUDED);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(27072)) {
            topLayout = inflater.inflate(R.layout.fragment_list, container, false);
        }
        return topLayout;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(27073)) {
            super.onActivityCreated(savedInstanceState);
        }
        ArrayList<Integer> checkedItemPositions = null;
        if (!ListenerUtil.mutListener.listen(27076)) {
            // recover after rotation
            if (savedInstanceState != null) {
                if (!ListenerUtil.mutListener.listen(27074)) {
                    this.listInstanceState = savedInstanceState.getParcelable(getBundleName());
                }
                if (!ListenerUtil.mutListener.listen(27075)) {
                    checkedItemPositions = savedInstanceState.getIntegerArrayList(getBundleName() + "c");
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(27077)) {
            createListAdapter(checkedItemPositions, preselectedIdentities, excludedIdentities, activity instanceof GroupAddActivity, activity instanceof ProfilePicRecipientsActivity);
        }
        if (!ListenerUtil.mutListener.listen(27078)) {
            preselectedIdentities = null;
        }
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(27079)) {
            super.onViewCreated(view, savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(27080)) {
            getListView().setDividerHeight(0);
        }
        if (!ListenerUtil.mutListener.listen(27081)) {
            getListView().setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        }
        if (!ListenerUtil.mutListener.listen(27082)) {
            getListView().setChoiceMode(AbsListView.CHOICE_MODE_MULTIPLE);
        }
        if (!ListenerUtil.mutListener.listen(27083)) {
            progressBar = view.findViewById(R.id.progress);
        }
        if (!ListenerUtil.mutListener.listen(27084)) {
            floatingActionButton = view.findViewById(R.id.floating);
        }
        if (!ListenerUtil.mutListener.listen(27085)) {
            floatingActionButton.hide();
        }
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        if (!ListenerUtil.mutListener.listen(27086)) {
            super.onListItemClick(l, v, position, id);
        }
        if (!ListenerUtil.mutListener.listen(27087)) {
            selectionListener.onSelectionChanged();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        try {
            ListView listView = getListView();
            if (!ListenerUtil.mutListener.listen(27097)) {
                if (listView != null) {
                    if (!ListenerUtil.mutListener.listen(27088)) {
                        outState.putParcelable(getBundleName(), listView.onSaveInstanceState());
                    }
                    if (!ListenerUtil.mutListener.listen(27096)) {
                        // save checked items, if any
                        if ((ListenerUtil.mutListener.listen(27094) ? (listView.getChoiceMode() == AbsListView.CHOICE_MODE_MULTIPLE || (ListenerUtil.mutListener.listen(27093) ? (getAdapter().getCheckedItemCount() >= 0) : (ListenerUtil.mutListener.listen(27092) ? (getAdapter().getCheckedItemCount() <= 0) : (ListenerUtil.mutListener.listen(27091) ? (getAdapter().getCheckedItemCount() < 0) : (ListenerUtil.mutListener.listen(27090) ? (getAdapter().getCheckedItemCount() != 0) : (ListenerUtil.mutListener.listen(27089) ? (getAdapter().getCheckedItemCount() == 0) : (getAdapter().getCheckedItemCount() > 0))))))) : (listView.getChoiceMode() == AbsListView.CHOICE_MODE_MULTIPLE && (ListenerUtil.mutListener.listen(27093) ? (getAdapter().getCheckedItemCount() >= 0) : (ListenerUtil.mutListener.listen(27092) ? (getAdapter().getCheckedItemCount() <= 0) : (ListenerUtil.mutListener.listen(27091) ? (getAdapter().getCheckedItemCount() < 0) : (ListenerUtil.mutListener.listen(27090) ? (getAdapter().getCheckedItemCount() != 0) : (ListenerUtil.mutListener.listen(27089) ? (getAdapter().getCheckedItemCount() == 0) : (getAdapter().getCheckedItemCount() > 0))))))))) {
                            if (!ListenerUtil.mutListener.listen(27095)) {
                                outState.putIntegerArrayList(getBundleName() + "c", getAdapter().getCheckedItemPositions());
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
        }
        if (!ListenerUtil.mutListener.listen(27098)) {
            super.onSaveInstanceState(outState);
        }
    }

    protected void onAdapterCreated() {
        if (!ListenerUtil.mutListener.listen(27099)) {
            selectionListener.onSelectionChanged();
        }
    }

    public HashSet<ContactModel> getSelectedContacts() {
        if (!ListenerUtil.mutListener.listen(27100)) {
            if (getAdapter() != null) {
                return (HashSet<ContactModel>) getAdapter().getCheckedItems();
            }
        }
        return new HashSet<>();
    }

    public FilterableListAdapter getAdapter() {
        return adapter;
    }

    void setListAdapter(FilterableListAdapter adapter) {
        if (!ListenerUtil.mutListener.listen(27101)) {
            super.setListAdapter(adapter);
        }
        if (!ListenerUtil.mutListener.listen(27106)) {
            if (isAdded()) {
                try {
                    if (!ListenerUtil.mutListener.listen(27102)) {
                        progressBar.setVisibility(View.GONE);
                    }
                    // add text view if contact list is empty
                    EmptyView emptyView = new EmptyView(activity);
                    if (!ListenerUtil.mutListener.listen(27103)) {
                        emptyView.setup(getEmptyText());
                    }
                    if (!ListenerUtil.mutListener.listen(27104)) {
                        ((ViewGroup) getListView().getParent()).addView(emptyView);
                    }
                    if (!ListenerUtil.mutListener.listen(27105)) {
                        getListView().setEmptyView(emptyView);
                    }
                } catch (IllegalStateException ignored) {
                }
            }
        }
    }

    public interface SelectionListener {

        void onSelectionChanged();
    }

    protected abstract void createListAdapter(ArrayList<Integer> checkedItems, ArrayList<String> preselectedIdentities, ArrayList<String> excludedIdentities, boolean group, boolean profilePics);

    protected abstract String getBundleName();

    @StringRes
    protected abstract int getEmptyText();
}
