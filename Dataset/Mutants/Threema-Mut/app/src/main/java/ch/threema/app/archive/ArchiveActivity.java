/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
 * Copyright (c) 2019-2021 Threema GmbH
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
package ch.threema.app.archive;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.material.appbar.MaterialToolbar;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
import androidx.annotation.NonNull;
import androidx.appcompat.view.ActionMode;
import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import ch.threema.app.R;
import ch.threema.app.ThreemaApplication;
import ch.threema.app.activities.ThreemaActivity;
import ch.threema.app.activities.ThreemaToolbarActivity;
import ch.threema.app.asynctasks.DeleteConversationsAsyncTask;
import ch.threema.app.dialogs.GenericAlertDialog;
import ch.threema.app.listeners.ConversationListener;
import ch.threema.app.listeners.MessageListener;
import ch.threema.app.services.ConversationService;
import ch.threema.app.services.GroupService;
import ch.threema.app.ui.EmptyRecyclerView;
import ch.threema.app.ui.EmptyView;
import ch.threema.app.ui.ThreemaSearchView;
import ch.threema.app.utils.AnimationUtil;
import ch.threema.app.utils.ConfigUtils;
import ch.threema.app.utils.IntentDataUtil;
import ch.threema.app.utils.TestUtil;
import ch.threema.base.ThreemaException;
import ch.threema.storage.models.AbstractMessageModel;
import ch.threema.storage.models.ConversationModel;
import static ch.threema.app.managers.ListenerManager.conversationListeners;
import static ch.threema.app.managers.ListenerManager.messageListeners;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class ArchiveActivity extends ThreemaToolbarActivity implements GenericAlertDialog.DialogClickListener, SearchView.OnQueryTextListener {

    private static final Logger logger = LoggerFactory.getLogger(ArchiveActivity.class);

    private static final String DIALOG_TAG_REALLY_DELETE_CHATS = "delc";

    private ArchiveAdapter archiveAdapter;

    private ArchiveViewModel viewModel;

    private ActionMode actionMode = null;

    private ConversationService conversationService;

    private GroupService groupService;

    private EmptyRecyclerView recyclerView;

    public int getLayoutResource() {
        return R.layout.activity_archive;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(9766)) {
            super.onCreate(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(9767)) {
            conversationListeners.add(this.conversationListener);
        }
        if (!ListenerUtil.mutListener.listen(9768)) {
            messageListeners.add(this.messageListener);
        }
    }

    @Override
    protected void onDestroy() {
        if (!ListenerUtil.mutListener.listen(9769)) {
            conversationListeners.remove(this.conversationListener);
        }
        if (!ListenerUtil.mutListener.listen(9770)) {
            messageListeners.remove(this.messageListener);
        }
        if (!ListenerUtil.mutListener.listen(9771)) {
            super.onDestroy();
        }
    }

    @Override
    protected boolean initActivity(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(9772)) {
            if (!super.initActivity(savedInstanceState)) {
                return false;
            }
        }
        try {
            if (!ListenerUtil.mutListener.listen(9774)) {
                conversationService = serviceManager.getConversationService();
            }
            if (!ListenerUtil.mutListener.listen(9775)) {
                groupService = serviceManager.getGroupService();
            }
        } catch (ThreemaException e) {
            if (!ListenerUtil.mutListener.listen(9773)) {
                logger.error("Exception", e);
            }
            return false;
        }
        MaterialToolbar toolbar = findViewById(R.id.material_toolbar);
        if (!ListenerUtil.mutListener.listen(9776)) {
            toolbar.setNavigationOnClickListener(view -> finish());
        }
        if (!ListenerUtil.mutListener.listen(9777)) {
            toolbar.setTitle(R.string.archived_chats);
        }
        String filterQuery = getIntent().getStringExtra(ThreemaApplication.INTENT_DATA_ARCHIVE_FILTER);
        MenuItem filterMenu = toolbar.getMenu().findItem(R.id.menu_filter_archive);
        ThreemaSearchView searchView = (ThreemaSearchView) filterMenu.getActionView();
        if (!ListenerUtil.mutListener.listen(9784)) {
            if (searchView != null) {
                if (!ListenerUtil.mutListener.listen(9779)) {
                    searchView.setQueryHint(getString(R.string.hint_filter_list));
                }
                if (!ListenerUtil.mutListener.listen(9782)) {
                    if (!TestUtil.empty(filterQuery)) {
                        if (!ListenerUtil.mutListener.listen(9780)) {
                            filterMenu.expandActionView();
                        }
                        if (!ListenerUtil.mutListener.listen(9781)) {
                            searchView.setQuery(filterQuery, false);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(9783)) {
                    searchView.post(() -> searchView.setOnQueryTextListener(ArchiveActivity.this));
                }
            } else {
                if (!ListenerUtil.mutListener.listen(9778)) {
                    filterMenu.setVisible(false);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(9785)) {
            archiveAdapter = new ArchiveAdapter(this);
        }
        if (!ListenerUtil.mutListener.listen(9808)) {
            archiveAdapter.setOnClickItemListener(new ArchiveAdapter.OnClickItemListener() {

                @Override
                public void onClick(ConversationModel conversationModel, View view, int position) {
                    if (!ListenerUtil.mutListener.listen(9797)) {
                        if (actionMode != null) {
                            if (!ListenerUtil.mutListener.listen(9787)) {
                                archiveAdapter.toggleChecked(position);
                            }
                            if (!ListenerUtil.mutListener.listen(9796)) {
                                if ((ListenerUtil.mutListener.listen(9792) ? (archiveAdapter.getCheckedItemsCount() >= 0) : (ListenerUtil.mutListener.listen(9791) ? (archiveAdapter.getCheckedItemsCount() <= 0) : (ListenerUtil.mutListener.listen(9790) ? (archiveAdapter.getCheckedItemsCount() < 0) : (ListenerUtil.mutListener.listen(9789) ? (archiveAdapter.getCheckedItemsCount() != 0) : (ListenerUtil.mutListener.listen(9788) ? (archiveAdapter.getCheckedItemsCount() == 0) : (archiveAdapter.getCheckedItemsCount() > 0))))))) {
                                    if (!ListenerUtil.mutListener.listen(9795)) {
                                        if (actionMode != null) {
                                            if (!ListenerUtil.mutListener.listen(9794)) {
                                                actionMode.invalidate();
                                            }
                                        }
                                    }
                                } else {
                                    if (!ListenerUtil.mutListener.listen(9793)) {
                                        actionMode.finish();
                                    }
                                }
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(9786)) {
                                showConversation(conversationModel, view);
                            }
                        }
                    }
                }

                @Override
                public boolean onLongClick(ConversationModel conversationModel, View itemView, int position) {
                    if (!ListenerUtil.mutListener.listen(9799)) {
                        if (actionMode != null) {
                            if (!ListenerUtil.mutListener.listen(9798)) {
                                actionMode.finish();
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(9800)) {
                        archiveAdapter.toggleChecked(position);
                    }
                    if (!ListenerUtil.mutListener.listen(9807)) {
                        if ((ListenerUtil.mutListener.listen(9805) ? (archiveAdapter.getCheckedItemsCount() >= 0) : (ListenerUtil.mutListener.listen(9804) ? (archiveAdapter.getCheckedItemsCount() <= 0) : (ListenerUtil.mutListener.listen(9803) ? (archiveAdapter.getCheckedItemsCount() < 0) : (ListenerUtil.mutListener.listen(9802) ? (archiveAdapter.getCheckedItemsCount() != 0) : (ListenerUtil.mutListener.listen(9801) ? (archiveAdapter.getCheckedItemsCount() == 0) : (archiveAdapter.getCheckedItemsCount() > 0))))))) {
                            if (!ListenerUtil.mutListener.listen(9806)) {
                                actionMode = startSupportActionMode(new ArchiveAction());
                            }
                        }
                    }
                    return true;
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(9809)) {
            recyclerView = this.findViewById(R.id.recycler);
        }
        if (!ListenerUtil.mutListener.listen(9810)) {
            recyclerView.setHasFixedSize(true);
        }
        if (!ListenerUtil.mutListener.listen(9811)) {
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
        }
        if (!ListenerUtil.mutListener.listen(9812)) {
            recyclerView.setItemAnimator(new DefaultItemAnimator());
        }
        EmptyView emptyView = new EmptyView(this, ConfigUtils.getActionBarSize(this));
        if (!ListenerUtil.mutListener.listen(9813)) {
            emptyView.setup(R.string.no_archived_chats);
        }
        if (!ListenerUtil.mutListener.listen(9814)) {
            ((ViewGroup) recyclerView.getParent().getParent()).addView(emptyView);
        }
        if (!ListenerUtil.mutListener.listen(9815)) {
            recyclerView.setEmptyView(emptyView);
        }
        if (!ListenerUtil.mutListener.listen(9816)) {
            recyclerView.setAdapter(archiveAdapter);
        }
        if (!ListenerUtil.mutListener.listen(9817)) {
            // Get the ViewModel
            viewModel = new ViewModelProvider(this).get(ArchiveViewModel.class);
        }
        // Create the observer which updates the UI
        final Observer<List<ConversationModel>> conversationsObserver = new Observer<List<ConversationModel>>() {

            @Override
            public void onChanged(List<ConversationModel> newConversations) {
                if (!ListenerUtil.mutListener.listen(9818)) {
                    // Update the UI
                    archiveAdapter.setConversationModels(newConversations);
                }
                if (!ListenerUtil.mutListener.listen(9820)) {
                    if (actionMode != null) {
                        if (!ListenerUtil.mutListener.listen(9819)) {
                            actionMode.invalidate();
                        }
                    }
                }
            }
        };
        if (!ListenerUtil.mutListener.listen(9821)) {
            // Observe the LiveData, passing in this activity as the LifecycleOwner and the observer.
            viewModel.getConversationModels().observe(this, conversationsObserver);
        }
        if (!ListenerUtil.mutListener.listen(9824)) {
            if (!TestUtil.empty(filterQuery)) {
                if (!ListenerUtil.mutListener.listen(9823)) {
                    viewModel.filter(filterQuery);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(9822)) {
                    viewModel.onDataChanged();
                }
            }
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (!ListenerUtil.mutListener.listen(9826)) {
            switch(item.getItemId()) {
                case android.R.id.home:
                    if (!ListenerUtil.mutListener.listen(9825)) {
                        this.finish();
                    }
                    return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        if (!ListenerUtil.mutListener.listen(9827)) {
            viewModel.filter(newText);
        }
        return true;
    }

    public class ArchiveAction implements ActionMode.Callback {

        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            if (!ListenerUtil.mutListener.listen(9828)) {
                mode.getMenuInflater().inflate(R.menu.action_archive, menu);
            }
            if (!ListenerUtil.mutListener.listen(9829)) {
                ConfigUtils.themeMenu(menu, ConfigUtils.getColorFromAttribute(ArchiveActivity.this, R.attr.colorAccent));
            }
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            final int checked = archiveAdapter.getCheckedItemsCount();
            if (!ListenerUtil.mutListener.listen(9836)) {
                if ((ListenerUtil.mutListener.listen(9834) ? (checked >= 0) : (ListenerUtil.mutListener.listen(9833) ? (checked <= 0) : (ListenerUtil.mutListener.listen(9832) ? (checked < 0) : (ListenerUtil.mutListener.listen(9831) ? (checked != 0) : (ListenerUtil.mutListener.listen(9830) ? (checked == 0) : (checked > 0))))))) {
                    if (!ListenerUtil.mutListener.listen(9835)) {
                        mode.setTitle(Integer.toString(checked));
                    }
                    return true;
                }
            }
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            switch(item.getItemId()) {
                case R.id.menu_delete:
                    if (!ListenerUtil.mutListener.listen(9837)) {
                        delete(archiveAdapter.getCheckedItems());
                    }
                    return true;
                case R.id.menu_unarchive:
                    if (!ListenerUtil.mutListener.listen(9838)) {
                        unarchive(archiveAdapter.getCheckedItems());
                    }
                    return true;
                case R.id.menu_select_all:
                    if (!ListenerUtil.mutListener.listen(9839)) {
                        archiveAdapter.selectAll();
                    }
                    return true;
                default:
                    return false;
            }
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {
            if (!ListenerUtil.mutListener.listen(9840)) {
                archiveAdapter.clearCheckedItems();
            }
            if (!ListenerUtil.mutListener.listen(9841)) {
                actionMode = null;
            }
        }
    }

    @Override
    public void onBackPressed() {
        if (!ListenerUtil.mutListener.listen(9844)) {
            if (actionMode != null) {
                if (!ListenerUtil.mutListener.listen(9843)) {
                    actionMode.finish();
                }
            } else {
                if (!ListenerUtil.mutListener.listen(9842)) {
                    super.onBackPressed();
                }
            }
        }
    }

    private void showConversation(ConversationModel conversationModel, View v) {
        Intent intent = IntentDataUtil.getShowConversationIntent(conversationModel, this);
        if (!ListenerUtil.mutListener.listen(9845)) {
            if (intent == null) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(9846)) {
            AnimationUtil.startActivityForResult(this, ConfigUtils.isTabletLayout() ? null : v, intent, ThreemaActivity.ACTIVITY_ID_COMPOSE_MESSAGE);
        }
    }

    private void unarchive(List<ConversationModel> checkedItems) {
        if (!ListenerUtil.mutListener.listen(9847)) {
            conversationService.unarchive(checkedItems);
        }
        if (!ListenerUtil.mutListener.listen(9848)) {
            viewModel.onDataChanged();
        }
        if (!ListenerUtil.mutListener.listen(9850)) {
            if (actionMode != null) {
                if (!ListenerUtil.mutListener.listen(9849)) {
                    actionMode.finish();
                }
            }
        }
    }

    @SuppressLint("StringFormatInvalid")
    private void delete(List<ConversationModel> checkedItems) {
        String confirmText = String.format(getString(R.string.really_delete_thread_message), checkedItems.size());
        if (!ListenerUtil.mutListener.listen(9856)) {
            if ((ListenerUtil.mutListener.listen(9851) ? (checkedItems.size() == 1 || checkedItems.get(0).isGroupConversation()) : (checkedItems.size() == 1 && checkedItems.get(0).isGroupConversation()))) {
                if (!ListenerUtil.mutListener.listen(9855)) {
                    if (groupService.isGroupMember(checkedItems.get(0).getGroup())) {
                        if (!ListenerUtil.mutListener.listen(9854)) {
                            if (groupService.isGroupOwner(checkedItems.get(0).getGroup())) {
                                if (!ListenerUtil.mutListener.listen(9853)) {
                                    confirmText = getString(R.string.delete_my_group_message);
                                }
                            } else {
                                if (!ListenerUtil.mutListener.listen(9852)) {
                                    confirmText = getString(R.string.delete_group_message);
                                }
                            }
                        }
                    }
                }
            }
        }
        GenericAlertDialog dialog = GenericAlertDialog.newInstance(R.string.really_delete_thread, confirmText, R.string.ok, R.string.cancel);
        if (!ListenerUtil.mutListener.listen(9857)) {
            dialog.setData(checkedItems);
        }
        if (!ListenerUtil.mutListener.listen(9858)) {
            dialog.show(getSupportFragmentManager(), DIALOG_TAG_REALLY_DELETE_CHATS);
        }
    }

    private void reallyDelete(final List<ConversationModel> checkedItems) {
        synchronized (checkedItems) {
            if (!ListenerUtil.mutListener.listen(9863)) {
                new DeleteConversationsAsyncTask(getSupportFragmentManager(), checkedItems, findViewById(R.id.parent_layout), new Runnable() {

                    @Override
                    public void run() {
                        synchronized (checkedItems) {
                            if (!ListenerUtil.mutListener.listen(9859)) {
                                checkedItems.clear();
                            }
                            if (!ListenerUtil.mutListener.listen(9861)) {
                                if (actionMode != null) {
                                    if (!ListenerUtil.mutListener.listen(9860)) {
                                        actionMode.finish();
                                    }
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(9862)) {
                                viewModel.onDataChanged();
                            }
                        }
                    }
                }).execute();
            }
        }
    }

    @Override
    public void onYes(String tag, Object data) {
        if (!ListenerUtil.mutListener.listen(9864)) {
            reallyDelete((List<ConversationModel>) data);
        }
    }

    @Override
    public void onNo(String tag, Object data) {
    }

    private final ConversationListener conversationListener = new ConversationListener() {

        @Override
        public void onNew(final ConversationModel conversationModel) {
            if (!ListenerUtil.mutListener.listen(9871)) {
                // unarchive calls onNew()
                if ((ListenerUtil.mutListener.listen(9866) ? ((ListenerUtil.mutListener.listen(9865) ? (archiveAdapter != null || recyclerView != null) : (archiveAdapter != null && recyclerView != null)) || viewModel != null) : ((ListenerUtil.mutListener.listen(9865) ? (archiveAdapter != null || recyclerView != null) : (archiveAdapter != null && recyclerView != null)) && viewModel != null))) {
                    List<ConversationModel> conversationModels = viewModel.getConversationModels().getValue();
                    if (!ListenerUtil.mutListener.listen(9870)) {
                        if ((ListenerUtil.mutListener.listen(9867) ? (conversationModels != null || !conversationModels.contains(conversationModel)) : (conversationModels != null && !conversationModels.contains(conversationModel)))) {
                            int currentCount = archiveAdapter.getItemCount();
                            if (!ListenerUtil.mutListener.listen(9869)) {
                                if (conversationModels.size() != currentCount) {
                                    if (!ListenerUtil.mutListener.listen(9868)) {
                                        // adapter and repository disagree about count: refresh.
                                        viewModel.onDataChanged();
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        @Override
        public void onModified(final ConversationModel modifiedConversationModel, final Integer oldPosition) {
            if (!ListenerUtil.mutListener.listen(9877)) {
                if ((ListenerUtil.mutListener.listen(9873) ? ((ListenerUtil.mutListener.listen(9872) ? (archiveAdapter != null || recyclerView != null) : (archiveAdapter != null && recyclerView != null)) || viewModel != null) : ((ListenerUtil.mutListener.listen(9872) ? (archiveAdapter != null || recyclerView != null) : (archiveAdapter != null && recyclerView != null)) && viewModel != null))) {
                    List<ConversationModel> conversationModels = viewModel.getConversationModels().getValue();
                    if (!ListenerUtil.mutListener.listen(9876)) {
                        if ((ListenerUtil.mutListener.listen(9874) ? (conversationModels != null || conversationModels.contains(modifiedConversationModel)) : (conversationModels != null && conversationModels.contains(modifiedConversationModel)))) {
                            if (!ListenerUtil.mutListener.listen(9875)) {
                                viewModel.onDataChanged();
                            }
                        }
                    }
                }
            }
        }

        @Override
        public void onRemoved(final ConversationModel conversationModel) {
            if (!ListenerUtil.mutListener.listen(9883)) {
                if ((ListenerUtil.mutListener.listen(9879) ? ((ListenerUtil.mutListener.listen(9878) ? (archiveAdapter != null || recyclerView != null) : (archiveAdapter != null && recyclerView != null)) || viewModel != null) : ((ListenerUtil.mutListener.listen(9878) ? (archiveAdapter != null || recyclerView != null) : (archiveAdapter != null && recyclerView != null)) && viewModel != null))) {
                    List<ConversationModel> conversationModels = viewModel.getConversationModels().getValue();
                    if (!ListenerUtil.mutListener.listen(9882)) {
                        if ((ListenerUtil.mutListener.listen(9880) ? (conversationModels != null || conversationModels.contains(conversationModel)) : (conversationModels != null && conversationModels.contains(conversationModel)))) {
                            if (!ListenerUtil.mutListener.listen(9881)) {
                                viewModel.onDataChanged();
                            }
                        }
                    }
                }
            }
        }

        @Override
        public void onModifiedAll() {
            if (!ListenerUtil.mutListener.listen(9887)) {
                if ((ListenerUtil.mutListener.listen(9885) ? ((ListenerUtil.mutListener.listen(9884) ? (archiveAdapter != null || recyclerView != null) : (archiveAdapter != null && recyclerView != null)) || viewModel != null) : ((ListenerUtil.mutListener.listen(9884) ? (archiveAdapter != null || recyclerView != null) : (archiveAdapter != null && recyclerView != null)) && viewModel != null))) {
                    if (!ListenerUtil.mutListener.listen(9886)) {
                        viewModel.onDataChanged();
                    }
                }
            }
        }
    };

    private final MessageListener messageListener = new MessageListener() {

        @Override
        public void onNew(AbstractMessageModel newMessage) {
            if (!ListenerUtil.mutListener.listen(9891)) {
                if ((ListenerUtil.mutListener.listen(9889) ? ((ListenerUtil.mutListener.listen(9888) ? (!newMessage.isOutbox() || !newMessage.isStatusMessage()) : (!newMessage.isOutbox() && !newMessage.isStatusMessage())) || !newMessage.isRead()) : ((ListenerUtil.mutListener.listen(9888) ? (!newMessage.isOutbox() || !newMessage.isStatusMessage()) : (!newMessage.isOutbox() && !newMessage.isStatusMessage())) && !newMessage.isRead()))) {
                    if (!ListenerUtil.mutListener.listen(9890)) {
                        viewModel.onDataChanged();
                    }
                }
            }
        }

        @Override
        public void onModified(List<AbstractMessageModel> modifiedMessageModel) {
        }

        @Override
        public void onRemoved(AbstractMessageModel removedMessageModel) {
        }

        @Override
        public void onProgressChanged(AbstractMessageModel messageModel, int newProgress) {
        }
    };

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        if (!ListenerUtil.mutListener.listen(9892)) {
            super.onConfigurationChanged(newConfig);
        }
        if (!ListenerUtil.mutListener.listen(9893)) {
            ConfigUtils.adjustToolbar(this, getToolbar());
        }
    }
}
