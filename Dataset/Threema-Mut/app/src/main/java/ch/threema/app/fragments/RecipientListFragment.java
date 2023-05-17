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
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.StringRes;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.ListFragment;
import ch.threema.app.R;
import ch.threema.app.ThreemaApplication;
import ch.threema.app.activities.RecipientListBaseActivity;
import ch.threema.app.adapters.FilterableListAdapter;
import ch.threema.app.managers.ServiceManager;
import ch.threema.app.services.ContactService;
import ch.threema.app.services.ConversationService;
import ch.threema.app.services.DeadlineListService;
import ch.threema.app.services.DistributionListService;
import ch.threema.app.services.GroupService;
import ch.threema.app.services.IdListService;
import ch.threema.app.services.PreferenceService;
import ch.threema.app.ui.CheckableConstraintLayout;
import ch.threema.app.ui.CheckableRelativeLayout;
import ch.threema.app.ui.DebouncedOnClickListener;
import ch.threema.app.ui.EmptyView;
import ch.threema.app.utils.ConfigUtils;
import ch.threema.app.utils.LogUtil;
import ch.threema.app.utils.NameUtil;
import ch.threema.app.utils.SnackbarUtil;
import ch.threema.base.ThreemaException;
import ch.threema.storage.models.ContactModel;
import ch.threema.storage.models.DistributionListModel;
import ch.threema.storage.models.GroupModel;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public abstract class RecipientListFragment extends ListFragment implements ListView.OnItemLongClickListener {

    public static final String ARGUMENT_MULTI_SELECT = "ms";

    protected ContactService contactService;

    protected GroupService groupService;

    protected DistributionListService distributionListService;

    protected ConversationService conversationService;

    protected PreferenceService preferenceService;

    protected IdListService blacklistService;

    protected DeadlineListService hiddenChatsListService;

    protected FragmentActivity activity;

    protected Parcelable listInstanceState;

    protected FloatingActionButton floatingActionButton;

    protected Snackbar snackbar;

    protected ProgressBar progressBar;

    protected View topLayout;

    protected boolean multiSelect = true;

    protected FilterableListAdapter adapter;

    private boolean isVisible = false;

    private static long selectionTime = 0;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(27989)) {
            activity = getActivity();
        }
        final ServiceManager serviceManager = ThreemaApplication.getServiceManager();
        try {
            if (!ListenerUtil.mutListener.listen(27991)) {
                contactService = serviceManager.getContactService();
            }
            if (!ListenerUtil.mutListener.listen(27992)) {
                groupService = serviceManager.getGroupService();
            }
            if (!ListenerUtil.mutListener.listen(27993)) {
                distributionListService = serviceManager.getDistributionListService();
            }
            if (!ListenerUtil.mutListener.listen(27994)) {
                blacklistService = serviceManager.getBlackListService();
            }
            if (!ListenerUtil.mutListener.listen(27995)) {
                conversationService = serviceManager.getConversationService();
            }
            if (!ListenerUtil.mutListener.listen(27996)) {
                preferenceService = serviceManager.getPreferenceService();
            }
            if (!ListenerUtil.mutListener.listen(27997)) {
                hiddenChatsListService = serviceManager.getHiddenChatsListService();
            }
        } catch (ThreemaException e) {
            if (!ListenerUtil.mutListener.listen(27990)) {
                LogUtil.exception(e, activity);
            }
            return null;
        }
        Bundle bundle = getArguments();
        if (!ListenerUtil.mutListener.listen(27999)) {
            if (bundle != null) {
                if (!ListenerUtil.mutListener.listen(27998)) {
                    multiSelect = bundle.getBoolean(ARGUMENT_MULTI_SELECT, true);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(28000)) {
            topLayout = inflater.inflate(R.layout.fragment_list, container, false);
        }
        return topLayout;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(28001)) {
            super.onActivityCreated(savedInstanceState);
        }
        ArrayList<Integer> checkedItemPositions = null;
        if (!ListenerUtil.mutListener.listen(28004)) {
            // recover after rotation
            if (savedInstanceState != null) {
                if (!ListenerUtil.mutListener.listen(28002)) {
                    this.listInstanceState = savedInstanceState.getParcelable(getBundleName());
                }
                if (!ListenerUtil.mutListener.listen(28003)) {
                    checkedItemPositions = savedInstanceState.getIntegerArrayList(getBundleName() + "c");
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(28005)) {
            createListAdapter(checkedItemPositions);
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(28006)) {
            super.onViewCreated(view, savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(28007)) {
            getListView().setDividerHeight(0);
        }
        if (!ListenerUtil.mutListener.listen(28008)) {
            getListView().setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        }
        if (!ListenerUtil.mutListener.listen(28022)) {
            if ((ListenerUtil.mutListener.listen(28014) ? (!multiSelect || (ListenerUtil.mutListener.listen(28013) ? (getAddText() >= 0) : (ListenerUtil.mutListener.listen(28012) ? (getAddText() <= 0) : (ListenerUtil.mutListener.listen(28011) ? (getAddText() > 0) : (ListenerUtil.mutListener.listen(28010) ? (getAddText() < 0) : (ListenerUtil.mutListener.listen(28009) ? (getAddText() == 0) : (getAddText() != 0))))))) : (!multiSelect && (ListenerUtil.mutListener.listen(28013) ? (getAddText() >= 0) : (ListenerUtil.mutListener.listen(28012) ? (getAddText() <= 0) : (ListenerUtil.mutListener.listen(28011) ? (getAddText() > 0) : (ListenerUtil.mutListener.listen(28010) ? (getAddText() < 0) : (ListenerUtil.mutListener.listen(28009) ? (getAddText() == 0) : (getAddText() != 0))))))))) {
                View headerView = View.inflate(activity, R.layout.header_recipient_list, null);
                if (!ListenerUtil.mutListener.listen(28015)) {
                    ((ImageView) headerView.findViewById(R.id.avatar_view)).setImageResource(getAddIcon());
                }
                if (!ListenerUtil.mutListener.listen(28016)) {
                    ((TextView) headerView.findViewById(R.id.text_view)).setText(getAddText());
                }
                if (!ListenerUtil.mutListener.listen(28020)) {
                    headerView.findViewById(R.id.container).setOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View v) {
                            Intent intent = getAddIntent();
                            if (!ListenerUtil.mutListener.listen(28019)) {
                                if (intent != null) {
                                    if (!ListenerUtil.mutListener.listen(28017)) {
                                        startActivity(intent);
                                    }
                                    if (!ListenerUtil.mutListener.listen(28018)) {
                                        activity.overridePendingTransition(R.anim.fast_fade_in, R.anim.fast_fade_out);
                                    }
                                }
                            }
                        }
                    });
                }
                if (!ListenerUtil.mutListener.listen(28021)) {
                    getListView().addHeaderView(headerView);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(28023)) {
            progressBar = view.findViewById(R.id.progress);
        }
        if (!ListenerUtil.mutListener.listen(28024)) {
            floatingActionButton = view.findViewById(R.id.floating);
        }
        if (!ListenerUtil.mutListener.listen(28029)) {
            if (isMultiSelectAllowed()) {
                if (!ListenerUtil.mutListener.listen(28026)) {
                    getListView().setOnItemLongClickListener(this);
                }
                if (!ListenerUtil.mutListener.listen(28028)) {
                    floatingActionButton.setOnClickListener(new DebouncedOnClickListener(500) {

                        @Override
                        public void onDebouncedClick(View v) {
                            if (!ListenerUtil.mutListener.listen(28027)) {
                                onFloatingActionButtonClick();
                            }
                        }
                    });
                }
            } else {
                if (!ListenerUtil.mutListener.listen(28025)) {
                    floatingActionButton.hide();
                }
            }
        }
    }

    @Override
    public void onListItemClick(@NonNull ListView l, @NonNull View v, int position, long id) {
        if (!ListenerUtil.mutListener.listen(28030)) {
            super.onListItemClick(l, v, position, id);
        }
        if (!ListenerUtil.mutListener.listen(28052)) {
            if (getListView().getChoiceMode() != AbsListView.CHOICE_MODE_MULTIPLE) {
                if (!ListenerUtil.mutListener.listen(28051)) {
                    if ((ListenerUtil.mutListener.listen(28047) ? ((ListenerUtil.mutListener.listen(28042) ? (System.currentTimeMillis() % selectionTime) : (ListenerUtil.mutListener.listen(28041) ? (System.currentTimeMillis() / selectionTime) : (ListenerUtil.mutListener.listen(28040) ? (System.currentTimeMillis() * selectionTime) : (ListenerUtil.mutListener.listen(28039) ? (System.currentTimeMillis() + selectionTime) : (System.currentTimeMillis() - selectionTime))))) >= 500) : (ListenerUtil.mutListener.listen(28046) ? ((ListenerUtil.mutListener.listen(28042) ? (System.currentTimeMillis() % selectionTime) : (ListenerUtil.mutListener.listen(28041) ? (System.currentTimeMillis() / selectionTime) : (ListenerUtil.mutListener.listen(28040) ? (System.currentTimeMillis() * selectionTime) : (ListenerUtil.mutListener.listen(28039) ? (System.currentTimeMillis() + selectionTime) : (System.currentTimeMillis() - selectionTime))))) <= 500) : (ListenerUtil.mutListener.listen(28045) ? ((ListenerUtil.mutListener.listen(28042) ? (System.currentTimeMillis() % selectionTime) : (ListenerUtil.mutListener.listen(28041) ? (System.currentTimeMillis() / selectionTime) : (ListenerUtil.mutListener.listen(28040) ? (System.currentTimeMillis() * selectionTime) : (ListenerUtil.mutListener.listen(28039) ? (System.currentTimeMillis() + selectionTime) : (System.currentTimeMillis() - selectionTime))))) < 500) : (ListenerUtil.mutListener.listen(28044) ? ((ListenerUtil.mutListener.listen(28042) ? (System.currentTimeMillis() % selectionTime) : (ListenerUtil.mutListener.listen(28041) ? (System.currentTimeMillis() / selectionTime) : (ListenerUtil.mutListener.listen(28040) ? (System.currentTimeMillis() * selectionTime) : (ListenerUtil.mutListener.listen(28039) ? (System.currentTimeMillis() + selectionTime) : (System.currentTimeMillis() - selectionTime))))) != 500) : (ListenerUtil.mutListener.listen(28043) ? ((ListenerUtil.mutListener.listen(28042) ? (System.currentTimeMillis() % selectionTime) : (ListenerUtil.mutListener.listen(28041) ? (System.currentTimeMillis() / selectionTime) : (ListenerUtil.mutListener.listen(28040) ? (System.currentTimeMillis() * selectionTime) : (ListenerUtil.mutListener.listen(28039) ? (System.currentTimeMillis() + selectionTime) : (System.currentTimeMillis() - selectionTime))))) == 500) : ((ListenerUtil.mutListener.listen(28042) ? (System.currentTimeMillis() % selectionTime) : (ListenerUtil.mutListener.listen(28041) ? (System.currentTimeMillis() / selectionTime) : (ListenerUtil.mutListener.listen(28040) ? (System.currentTimeMillis() * selectionTime) : (ListenerUtil.mutListener.listen(28039) ? (System.currentTimeMillis() + selectionTime) : (System.currentTimeMillis() - selectionTime))))) > 500))))))) {
                        if (!ListenerUtil.mutListener.listen(28048)) {
                            selectionTime = System.currentTimeMillis();
                        }
                        if (!ListenerUtil.mutListener.listen(28049)) {
                            getListView().setChoiceMode(AbsListView.CHOICE_MODE_NONE);
                        }
                        if (!ListenerUtil.mutListener.listen(28050)) {
                            onItemClick(v);
                        }
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(28038)) {
                    if ((ListenerUtil.mutListener.listen(28035) ? (adapter.getCheckedItemCount() >= 0) : (ListenerUtil.mutListener.listen(28034) ? (adapter.getCheckedItemCount() > 0) : (ListenerUtil.mutListener.listen(28033) ? (adapter.getCheckedItemCount() < 0) : (ListenerUtil.mutListener.listen(28032) ? (adapter.getCheckedItemCount() != 0) : (ListenerUtil.mutListener.listen(28031) ? (adapter.getCheckedItemCount() == 0) : (adapter.getCheckedItemCount() <= 0))))))) {
                        if (!ListenerUtil.mutListener.listen(28037)) {
                            stopMultiSelect();
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(28036)) {
                            updateMultiSelect();
                        }
                    }
                }
            }
        }
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View v, int position, long id) {
        if (!ListenerUtil.mutListener.listen(28053)) {
            startMultiSelect();
        }
        if (!ListenerUtil.mutListener.listen(28054)) {
            getListView().setItemChecked(position, true);
        }
        if (!ListenerUtil.mutListener.listen(28057)) {
            if (v instanceof CheckableConstraintLayout) {
                if (!ListenerUtil.mutListener.listen(28056)) {
                    ((CheckableConstraintLayout) v).setChecked(true);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(28055)) {
                    ((CheckableRelativeLayout) v).setChecked(true);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(28058)) {
            updateMultiSelect();
        }
        return true;
    }

    private void startMultiSelect() {
        if (!ListenerUtil.mutListener.listen(28059)) {
            getListView().setChoiceMode(AbsListView.CHOICE_MODE_MULTIPLE);
        }
        if (!ListenerUtil.mutListener.listen(28067)) {
            if (isVisible) {
                if (!ListenerUtil.mutListener.listen(28060)) {
                    snackbar = SnackbarUtil.make(topLayout, "", Snackbar.LENGTH_INDEFINITE, 4);
                }
                if (!ListenerUtil.mutListener.listen(28061)) {
                    snackbar.setBackgroundTint(ConfigUtils.getColorFromAttribute(getContext(), R.attr.colorAccent));
                }
                if (!ListenerUtil.mutListener.listen(28062)) {
                    snackbar.setTextColor(ConfigUtils.getColorFromAttribute(getContext(), R.attr.colorOnSecondary));
                }
                if (!ListenerUtil.mutListener.listen(28063)) {
                    snackbar.getView().getLayoutParams().width = AppBarLayout.LayoutParams.MATCH_PARENT;
                }
                if (!ListenerUtil.mutListener.listen(28064)) {
                    snackbar.show();
                }
                if (!ListenerUtil.mutListener.listen(28066)) {
                    snackbar.getView().post(new Runnable() {

                        @Override
                        public void run() {
                            if (!ListenerUtil.mutListener.listen(28065)) {
                                floatingActionButton.show();
                            }
                        }
                    });
                }
            }
        }
    }

    private void updateMultiSelect() {
        if (!ListenerUtil.mutListener.listen(28076)) {
            if (getListView().getChoiceMode() == AbsListView.CHOICE_MODE_MULTIPLE) {
                if (!ListenerUtil.mutListener.listen(28075)) {
                    if ((ListenerUtil.mutListener.listen(28072) ? (getAdapter().getCheckedItemCount() >= 0) : (ListenerUtil.mutListener.listen(28071) ? (getAdapter().getCheckedItemCount() <= 0) : (ListenerUtil.mutListener.listen(28070) ? (getAdapter().getCheckedItemCount() < 0) : (ListenerUtil.mutListener.listen(28069) ? (getAdapter().getCheckedItemCount() != 0) : (ListenerUtil.mutListener.listen(28068) ? (getAdapter().getCheckedItemCount() == 0) : (getAdapter().getCheckedItemCount() > 0))))))) {
                        if (!ListenerUtil.mutListener.listen(28074)) {
                            if (snackbar != null) {
                                if (!ListenerUtil.mutListener.listen(28073)) {
                                    snackbar.setText(getString(R.string.really_send, getRecipientList()));
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private void stopMultiSelect() {
        if (!ListenerUtil.mutListener.listen(28077)) {
            getListView().setChoiceMode(AbsListView.CHOICE_MODE_NONE);
        }
        if (!ListenerUtil.mutListener.listen(28080)) {
            if ((ListenerUtil.mutListener.listen(28078) ? (snackbar != null || snackbar.isShown()) : (snackbar != null && snackbar.isShown()))) {
                if (!ListenerUtil.mutListener.listen(28079)) {
                    snackbar.dismiss();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(28082)) {
            floatingActionButton.postDelayed(new Runnable() {

                @Override
                public void run() {
                    if (!ListenerUtil.mutListener.listen(28081)) {
                        floatingActionButton.hide();
                    }
                }
            }, 100);
        }
    }

    private void onItemClick(View v) {
        final Object object = adapter.getClickedItem(v);
        if (!ListenerUtil.mutListener.listen(28084)) {
            if (object != null) {
                if (!ListenerUtil.mutListener.listen(28083)) {
                    ((RecipientListBaseActivity) activity).prepareForwardingOrSharing(new ArrayList<>(Collections.singletonList(object)));
                }
            }
        }
    }

    private void onFloatingActionButtonClick() {
        final HashSet<?> objects = adapter.getCheckedItems();
        if (!ListenerUtil.mutListener.listen(28086)) {
            if (!objects.isEmpty()) {
                if (!ListenerUtil.mutListener.listen(28085)) {
                    ((RecipientListBaseActivity) activity).prepareForwardingOrSharing(new ArrayList<>(objects));
                }
            }
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        try {
            ListView listView = getListView();
            if (!ListenerUtil.mutListener.listen(28096)) {
                if (listView != null) {
                    if (!ListenerUtil.mutListener.listen(28087)) {
                        outState.putParcelable(getBundleName(), listView.onSaveInstanceState());
                    }
                    if (!ListenerUtil.mutListener.listen(28095)) {
                        // save checked items, if any
                        if ((ListenerUtil.mutListener.listen(28093) ? (listView.getChoiceMode() == AbsListView.CHOICE_MODE_MULTIPLE || (ListenerUtil.mutListener.listen(28092) ? (getAdapter().getCheckedItemCount() >= 0) : (ListenerUtil.mutListener.listen(28091) ? (getAdapter().getCheckedItemCount() <= 0) : (ListenerUtil.mutListener.listen(28090) ? (getAdapter().getCheckedItemCount() < 0) : (ListenerUtil.mutListener.listen(28089) ? (getAdapter().getCheckedItemCount() != 0) : (ListenerUtil.mutListener.listen(28088) ? (getAdapter().getCheckedItemCount() == 0) : (getAdapter().getCheckedItemCount() > 0))))))) : (listView.getChoiceMode() == AbsListView.CHOICE_MODE_MULTIPLE && (ListenerUtil.mutListener.listen(28092) ? (getAdapter().getCheckedItemCount() >= 0) : (ListenerUtil.mutListener.listen(28091) ? (getAdapter().getCheckedItemCount() <= 0) : (ListenerUtil.mutListener.listen(28090) ? (getAdapter().getCheckedItemCount() < 0) : (ListenerUtil.mutListener.listen(28089) ? (getAdapter().getCheckedItemCount() != 0) : (ListenerUtil.mutListener.listen(28088) ? (getAdapter().getCheckedItemCount() == 0) : (getAdapter().getCheckedItemCount() > 0))))))))) {
                            if (!ListenerUtil.mutListener.listen(28094)) {
                                outState.putIntegerArrayList(getBundleName() + "c", getAdapter().getCheckedItemPositions());
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
        }
        if (!ListenerUtil.mutListener.listen(28097)) {
            super.onSaveInstanceState(outState);
        }
    }

    protected void restoreCheckedItems(ArrayList<Integer> checkedItemPositions) {
        if (!ListenerUtil.mutListener.listen(28106)) {
            // restore previously checked items
            if ((ListenerUtil.mutListener.listen(28103) ? (checkedItemPositions != null || (ListenerUtil.mutListener.listen(28102) ? (checkedItemPositions.size() >= 0) : (ListenerUtil.mutListener.listen(28101) ? (checkedItemPositions.size() <= 0) : (ListenerUtil.mutListener.listen(28100) ? (checkedItemPositions.size() < 0) : (ListenerUtil.mutListener.listen(28099) ? (checkedItemPositions.size() != 0) : (ListenerUtil.mutListener.listen(28098) ? (checkedItemPositions.size() == 0) : (checkedItemPositions.size() > 0))))))) : (checkedItemPositions != null && (ListenerUtil.mutListener.listen(28102) ? (checkedItemPositions.size() >= 0) : (ListenerUtil.mutListener.listen(28101) ? (checkedItemPositions.size() <= 0) : (ListenerUtil.mutListener.listen(28100) ? (checkedItemPositions.size() < 0) : (ListenerUtil.mutListener.listen(28099) ? (checkedItemPositions.size() != 0) : (ListenerUtil.mutListener.listen(28098) ? (checkedItemPositions.size() == 0) : (checkedItemPositions.size() > 0))))))))) {
                if (!ListenerUtil.mutListener.listen(28104)) {
                    startMultiSelect();
                }
                if (!ListenerUtil.mutListener.listen(28105)) {
                    updateMultiSelect();
                }
            }
        }
    }

    private String getRecipientList() {
        StringBuilder builder = new StringBuilder();
        if (!ListenerUtil.mutListener.listen(28120)) {
            {
                long _loopCounter176 = 0;
                for (Object model : adapter.getCheckedItems()) {
                    ListenerUtil.loopListener.listen("_loopCounter176", ++_loopCounter176);
                    String name = null;
                    if (!ListenerUtil.mutListener.listen(28110)) {
                        if (model instanceof ContactModel) {
                            if (!ListenerUtil.mutListener.listen(28109)) {
                                name = NameUtil.getDisplayNameOrNickname((ContactModel) model, true);
                            }
                        } else if (model instanceof GroupModel) {
                            if (!ListenerUtil.mutListener.listen(28108)) {
                                name = NameUtil.getDisplayName((GroupModel) model, this.groupService);
                            }
                        } else if (model instanceof DistributionListModel) {
                            if (!ListenerUtil.mutListener.listen(28107)) {
                                name = NameUtil.getDisplayName((DistributionListModel) model, this.distributionListService);
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(28119)) {
                        if (name != null) {
                            if (!ListenerUtil.mutListener.listen(28117)) {
                                if ((ListenerUtil.mutListener.listen(28115) ? (builder.length() >= 0) : (ListenerUtil.mutListener.listen(28114) ? (builder.length() <= 0) : (ListenerUtil.mutListener.listen(28113) ? (builder.length() < 0) : (ListenerUtil.mutListener.listen(28112) ? (builder.length() != 0) : (ListenerUtil.mutListener.listen(28111) ? (builder.length() == 0) : (builder.length() > 0))))))) {
                                    if (!ListenerUtil.mutListener.listen(28116)) {
                                        builder.append(", ");
                                    }
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(28118)) {
                                builder.append(name);
                            }
                        }
                    }
                }
            }
        }
        return builder.toString();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        if (!ListenerUtil.mutListener.listen(28121)) {
            super.setUserVisibleHint(isVisibleToUser);
        }
        if (!ListenerUtil.mutListener.listen(28122)) {
            isVisible = isVisibleToUser;
        }
        if (!ListenerUtil.mutListener.listen(28130)) {
            if (isVisibleToUser) {
                if (!ListenerUtil.mutListener.listen(28129)) {
                    if ((ListenerUtil.mutListener.listen(28123) ? (isMultiSelectAllowed() || getView() != null) : (isMultiSelectAllowed() && getView() != null))) {
                        if (!ListenerUtil.mutListener.listen(28128)) {
                            if (getListView().getChoiceMode() == AbsListView.CHOICE_MODE_MULTIPLE) {
                                if (!ListenerUtil.mutListener.listen(28127)) {
                                    if ((ListenerUtil.mutListener.listen(28124) ? (snackbar == null && !snackbar.isShownOrQueued()) : (snackbar == null || !snackbar.isShownOrQueued()))) {
                                        if (!ListenerUtil.mutListener.listen(28125)) {
                                            startMultiSelect();
                                        }
                                        if (!ListenerUtil.mutListener.listen(28126)) {
                                            updateMultiSelect();
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public FilterableListAdapter getAdapter() {
        return adapter;
    }

    void setListAdapter(FilterableListAdapter adapter) {
        if (!ListenerUtil.mutListener.listen(28131)) {
            super.setListAdapter(adapter);
        }
        if (!ListenerUtil.mutListener.listen(28136)) {
            if (isAdded()) {
                try {
                    if (!ListenerUtil.mutListener.listen(28132)) {
                        progressBar.setVisibility(View.GONE);
                    }
                    // add text view if contact list is empty
                    EmptyView emptyView = new EmptyView(activity);
                    if (!ListenerUtil.mutListener.listen(28133)) {
                        emptyView.setup(getEmptyText());
                    }
                    if (!ListenerUtil.mutListener.listen(28134)) {
                        ((ViewGroup) getListView().getParent()).addView(emptyView);
                    }
                    if (!ListenerUtil.mutListener.listen(28135)) {
                        getListView().setEmptyView(emptyView);
                    }
                } catch (IllegalStateException ignored) {
                }
            }
        }
    }

    protected abstract void createListAdapter(ArrayList<Integer> checkedItems);

    protected abstract String getBundleName();

    @StringRes
    protected abstract int getEmptyText();

    @DrawableRes
    protected abstract int getAddIcon();

    @StringRes
    protected abstract int getAddText();

    protected abstract Intent getAddIntent();

    protected abstract boolean isMultiSelectAllowed();
}
