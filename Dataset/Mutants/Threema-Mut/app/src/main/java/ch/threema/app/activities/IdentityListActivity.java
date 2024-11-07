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

import android.os.Bundle;
import android.os.Parcelable;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.view.ActionMode;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import ch.threema.app.R;
import ch.threema.app.ThreemaApplication;
import ch.threema.app.adapters.IdentityListAdapter;
import ch.threema.app.dialogs.TextEntryDialog;
import ch.threema.app.exceptions.FileSystemNotPresentException;
import ch.threema.app.listeners.ContactListener;
import ch.threema.app.managers.ListenerManager;
import ch.threema.app.services.ContactService;
import ch.threema.app.services.IdListService;
import ch.threema.app.ui.EmptyRecyclerView;
import ch.threema.app.ui.EmptyView;
import ch.threema.app.utils.ConfigUtils;
import ch.threema.client.ProtocolDefines;
import ch.threema.localcrypto.MasterKeyLockedException;
import ch.threema.storage.models.ContactModel;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public abstract class IdentityListActivity extends ThreemaToolbarActivity implements TextEntryDialog.TextEntryDialogClickListener {

    private static final String BUNDLE_RECYCLER_LAYOUT = "recycler";

    private static final String BUNDLE_SELECTED_ITEM = "item";

    private IdentityListAdapter adapter;

    private ActionMode actionMode = null;

    private Bundle savedInstanceState;

    private String[] blackListedIdentities;

    private EmptyRecyclerView recyclerView;

    private ContactService contactService;

    protected abstract IdListService getIdentityListService();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(3742)) {
            super.onCreate(savedInstanceState);
        }
        try {
            if (!ListenerUtil.mutListener.listen(3744)) {
                contactService = ThreemaApplication.getServiceManager().getContactService();
            }
        } catch (MasterKeyLockedException | FileSystemNotPresentException e) {
            if (!ListenerUtil.mutListener.listen(3743)) {
                finish();
            }
        }
        if (!ListenerUtil.mutListener.listen(3745)) {
            this.savedInstanceState = savedInstanceState;
        }
        ActionBar actionBar = getSupportActionBar();
        if (!ListenerUtil.mutListener.listen(3746)) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        if (!ListenerUtil.mutListener.listen(3747)) {
            actionBar.setTitle(this.getTitleText());
        }
        if (!ListenerUtil.mutListener.listen(3748)) {
            recyclerView = this.findViewById(R.id.recycler);
        }
        if (!ListenerUtil.mutListener.listen(3749)) {
            recyclerView.setHasFixedSize(true);
        }
        if (!ListenerUtil.mutListener.listen(3750)) {
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
        }
        if (!ListenerUtil.mutListener.listen(3751)) {
            recyclerView.setItemAnimator(new DefaultItemAnimator());
        }
        if (!ListenerUtil.mutListener.listen(3752)) {
            recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        }
        ExtendedFloatingActionButton floatingActionButton = findViewById(R.id.floating);
        if (!ListenerUtil.mutListener.listen(3753)) {
            floatingActionButton.show();
        }
        if (!ListenerUtil.mutListener.listen(3755)) {
            floatingActionButton.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    if (!ListenerUtil.mutListener.listen(3754)) {
                        startExclude();
                    }
                }
            });
        }
        // add text view if contact list is empty
        EmptyView emptyView = new EmptyView(this);
        if (!ListenerUtil.mutListener.listen(3756)) {
            emptyView.setup(this.getBlankListText());
        }
        if (!ListenerUtil.mutListener.listen(3757)) {
            ((ViewGroup) recyclerView.getParent()).addView(emptyView);
        }
        if (!ListenerUtil.mutListener.listen(3758)) {
            recyclerView.setEmptyView(emptyView);
        }
        if (!ListenerUtil.mutListener.listen(3775)) {
            recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    if (!ListenerUtil.mutListener.listen(3759)) {
                        super.onScrolled(recyclerView, dx, dy);
                    }
                    if (!ListenerUtil.mutListener.listen(3774)) {
                        if ((ListenerUtil.mutListener.listen(3764) ? (dy >= 0) : (ListenerUtil.mutListener.listen(3763) ? (dy <= 0) : (ListenerUtil.mutListener.listen(3762) ? (dy < 0) : (ListenerUtil.mutListener.listen(3761) ? (dy != 0) : (ListenerUtil.mutListener.listen(3760) ? (dy == 0) : (dy > 0))))))) {
                            if (!ListenerUtil.mutListener.listen(3773)) {
                                // Scroll Down
                                if (floatingActionButton.isShown()) {
                                    if (!ListenerUtil.mutListener.listen(3772)) {
                                        floatingActionButton.shrink();
                                    }
                                }
                            }
                        } else if ((ListenerUtil.mutListener.listen(3769) ? (dy >= 0) : (ListenerUtil.mutListener.listen(3768) ? (dy <= 0) : (ListenerUtil.mutListener.listen(3767) ? (dy > 0) : (ListenerUtil.mutListener.listen(3766) ? (dy != 0) : (ListenerUtil.mutListener.listen(3765) ? (dy == 0) : (dy < 0))))))) {
                            if (!ListenerUtil.mutListener.listen(3771)) {
                                // Scroll Up
                                if (floatingActionButton.isShown()) {
                                    if (!ListenerUtil.mutListener.listen(3770)) {
                                        floatingActionButton.extend();
                                    }
                                }
                            }
                        }
                    }
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(3776)) {
            this.updateListAdapter();
        }
    }

    @Override
    public int getLayoutResource() {
        return R.layout.activity_recycler_toolbar;
    }

    protected abstract String getBlankListText();

    protected abstract String getTitleText();

    private void updateListAdapter() {
        if (!ListenerUtil.mutListener.listen(3777)) {
            if (this.getIdentityListService() == null) {
                // do nothing;
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(3778)) {
            this.blackListedIdentities = this.getIdentityListService().getAll();
        }
        List<IdentityListAdapter.Entity> blackList = new ArrayList<>();
        if (!ListenerUtil.mutListener.listen(3780)) {
            {
                long _loopCounter22 = 0;
                for (String s : this.blackListedIdentities) {
                    ListenerUtil.loopListener.listen("_loopCounter22", ++_loopCounter22);
                    if (!ListenerUtil.mutListener.listen(3779)) {
                        blackList.add(new IdentityListAdapter.Entity(s));
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(3790)) {
            if (adapter == null) {
                if (!ListenerUtil.mutListener.listen(3781)) {
                    adapter = new IdentityListAdapter(this);
                }
                if (!ListenerUtil.mutListener.listen(3788)) {
                    adapter.setOnItemClickListener(new IdentityListAdapter.OnItemClickListener() {

                        @Override
                        public void onItemClick(IdentityListAdapter.Entity entity) {
                            if (!ListenerUtil.mutListener.listen(3787)) {
                                if (entity.equals(adapter.getSelected())) {
                                    if (!ListenerUtil.mutListener.listen(3786)) {
                                        if (actionMode != null) {
                                            if (!ListenerUtil.mutListener.listen(3785)) {
                                                actionMode.finish();
                                            }
                                        }
                                    }
                                } else {
                                    if (!ListenerUtil.mutListener.listen(3782)) {
                                        adapter.setSelected(entity);
                                    }
                                    if (!ListenerUtil.mutListener.listen(3784)) {
                                        if (actionMode == null) {
                                            if (!ListenerUtil.mutListener.listen(3783)) {
                                                actionMode = startSupportActionMode(new IdentityListAction());
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    });
                }
                if (!ListenerUtil.mutListener.listen(3789)) {
                    recyclerView.setAdapter(adapter);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(3791)) {
            adapter.setData(blackList);
        }
        if (!ListenerUtil.mutListener.listen(3800)) {
            // restore after rotate
            if (savedInstanceState != null) {
                Parcelable savedRecyclerLayoutState = savedInstanceState.getParcelable(BUNDLE_RECYCLER_LAYOUT);
                if (!ListenerUtil.mutListener.listen(3792)) {
                    recyclerView.getLayoutManager().onRestoreInstanceState(savedRecyclerLayoutState);
                }
                String selectedIdentity = savedInstanceState.getString(BUNDLE_SELECTED_ITEM);
                if (!ListenerUtil.mutListener.listen(3798)) {
                    if (selectedIdentity != null) {
                        Iterator<IdentityListAdapter.Entity> iterator = blackList.iterator();
                        if (!ListenerUtil.mutListener.listen(3797)) {
                            {
                                long _loopCounter23 = 0;
                                while (iterator.hasNext()) {
                                    ListenerUtil.loopListener.listen("_loopCounter23", ++_loopCounter23);
                                    IdentityListAdapter.Entity entity = iterator.next();
                                    if (!ListenerUtil.mutListener.listen(3795)) {
                                        if (selectedIdentity.equals(entity.getText())) {
                                            if (!ListenerUtil.mutListener.listen(3793)) {
                                                adapter.setSelected(entity);
                                            }
                                            if (!ListenerUtil.mutListener.listen(3794)) {
                                                this.actionMode = startSupportActionMode(new IdentityListAction());
                                            }
                                            break;
                                        }
                                    }
                                    if (!ListenerUtil.mutListener.listen(3796)) {
                                        iterator.remove();
                                    }
                                }
                            }
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(3799)) {
                    savedInstanceState = null;
                }
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (!ListenerUtil.mutListener.listen(3802)) {
            switch(item.getItemId()) {
                case android.R.id.home:
                    if (!ListenerUtil.mutListener.listen(3801)) {
                        finish();
                    }
                    break;
            }
        }
        return true;
    }

    private void startExclude() {
        if (!ListenerUtil.mutListener.listen(3804)) {
            if (actionMode != null) {
                if (!ListenerUtil.mutListener.listen(3803)) {
                    actionMode.finish();
                }
            }
        }
        DialogFragment dialogFragment = TextEntryDialog.newInstance(R.string.title_enter_id, R.string.enter_id_hint, R.string.ok, R.string.cancel, "", InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_CAP_CHARACTERS | InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS, TextEntryDialog.INPUT_FILTER_TYPE_IDENTITY);
        if (!ListenerUtil.mutListener.listen(3805)) {
            dialogFragment.show(getSupportFragmentManager(), "excludeDialog");
        }
    }

    private void excludeIdentity(String identity) {
        if (!ListenerUtil.mutListener.listen(3806)) {
            if (this.getIdentityListService() == null) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(3807)) {
            // add identity to list!
            this.getIdentityListService().add(identity);
        }
        if (!ListenerUtil.mutListener.listen(3808)) {
            fireOnModifiedContact(identity);
        }
        if (!ListenerUtil.mutListener.listen(3809)) {
            this.updateListAdapter();
        }
    }

    private void removeIdentity(String identity) {
        if (!ListenerUtil.mutListener.listen(3810)) {
            if (this.getIdentityListService() == null) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(3811)) {
            // remove identity from list!
            this.getIdentityListService().remove(identity);
        }
        if (!ListenerUtil.mutListener.listen(3812)) {
            fireOnModifiedContact(identity);
        }
        if (!ListenerUtil.mutListener.listen(3813)) {
            this.updateListAdapter();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        if (!ListenerUtil.mutListener.listen(3814)) {
            super.onSaveInstanceState(outState);
        }
        if (!ListenerUtil.mutListener.listen(3819)) {
            if ((ListenerUtil.mutListener.listen(3815) ? (recyclerView != null || adapter != null) : (recyclerView != null && adapter != null))) {
                if (!ListenerUtil.mutListener.listen(3816)) {
                    outState.putParcelable(BUNDLE_RECYCLER_LAYOUT, recyclerView.getLayoutManager().onSaveInstanceState());
                }
                if (!ListenerUtil.mutListener.listen(3818)) {
                    if (adapter.getSelected() != null) {
                        if (!ListenerUtil.mutListener.listen(3817)) {
                            outState.putString(BUNDLE_SELECTED_ITEM, adapter.getSelected().getText());
                        }
                    }
                }
            }
        }
    }

    public class IdentityListAction implements ActionMode.Callback {

        public IdentityListAction() {
        }

        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            if (!ListenerUtil.mutListener.listen(3820)) {
                mode.getMenuInflater().inflate(R.menu.action_identity_list, menu);
            }
            if (!ListenerUtil.mutListener.listen(3821)) {
                ConfigUtils.themeMenu(menu, ConfigUtils.getColorFromAttribute(IdentityListActivity.this, R.attr.colorAccent));
            }
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            switch(item.getItemId()) {
                case R.id.menu_identity_remove:
                    IdentityListAdapter.Entity selectedEntity = adapter.getSelected();
                    if (!ListenerUtil.mutListener.listen(3823)) {
                        if (selectedEntity != null) {
                            if (!ListenerUtil.mutListener.listen(3822)) {
                                removeIdentity(selectedEntity.getText());
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(3824)) {
                        mode.finish();
                    }
                    return true;
                default:
                    return false;
            }
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {
            if (!ListenerUtil.mutListener.listen(3825)) {
                actionMode = null;
            }
            if (!ListenerUtil.mutListener.listen(3826)) {
                adapter.clearSelection();
            }
        }
    }

    private void fireOnModifiedContact(final String identity) {
        if (!ListenerUtil.mutListener.listen(3830)) {
            if (contactService != null) {
                if (!ListenerUtil.mutListener.listen(3829)) {
                    ListenerManager.contactListeners.handle(new ListenerManager.HandleListener<ContactListener>() {

                        @Override
                        public void handle(ContactListener listener) {
                            ContactModel contactModel = contactService.getByIdentity(identity);
                            if (!ListenerUtil.mutListener.listen(3828)) {
                                if (contactModel != null) {
                                    if (!ListenerUtil.mutListener.listen(3827)) {
                                        listener.onModified(contactModel);
                                    }
                                }
                            }
                        }
                    });
                }
            }
        }
    }

    public void onYes(String tag, final String text) {
        if (!ListenerUtil.mutListener.listen(3833)) {
            if ((ListenerUtil.mutListener.listen(3831) ? (text != null || text.length() == ProtocolDefines.IDENTITY_LEN) : (text != null && text.length() == ProtocolDefines.IDENTITY_LEN))) {
                if (!ListenerUtil.mutListener.listen(3832)) {
                    excludeIdentity(text);
                }
            }
        }
    }

    @Override
    public void onNo(String tag) {
    }

    @Override
    public void onNeutral(String tag) {
    }
}
