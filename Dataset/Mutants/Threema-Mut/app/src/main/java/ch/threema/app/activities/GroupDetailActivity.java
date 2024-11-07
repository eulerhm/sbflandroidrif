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

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.app.ActivityOptionsCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.palette.graphics.Palette;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import ch.threema.app.R;
import ch.threema.app.ThreemaApplication;
import ch.threema.app.adapters.GroupDetailAdapter;
import ch.threema.app.asynctasks.DeleteGroupAsyncTask;
import ch.threema.app.asynctasks.DeleteMyGroupAsyncTask;
import ch.threema.app.asynctasks.LeaveGroupAsyncTask;
import ch.threema.app.dialogs.GenericAlertDialog;
import ch.threema.app.dialogs.GenericProgressDialog;
import ch.threema.app.dialogs.SelectorDialog;
import ch.threema.app.dialogs.SimpleStringAlertDialog;
import ch.threema.app.dialogs.TextEntryDialog;
import ch.threema.app.emojis.EmojiEditText;
import ch.threema.app.exceptions.FileSystemNotPresentException;
import ch.threema.app.listeners.ContactListener;
import ch.threema.app.listeners.ContactSettingsListener;
import ch.threema.app.listeners.GroupListener;
import ch.threema.app.managers.ListenerManager;
import ch.threema.app.services.DeviceService;
import ch.threema.app.services.IdListService;
import ch.threema.app.services.license.LicenseService;
import ch.threema.app.ui.AvatarEditView;
import ch.threema.app.ui.GroupDetailViewModel;
import ch.threema.app.ui.ResumePauseHandler;
import ch.threema.app.utils.AppRestrictionUtil;
import ch.threema.app.utils.BitmapUtil;
import ch.threema.app.utils.ConfigUtils;
import ch.threema.app.utils.ContactUtil;
import ch.threema.app.utils.DialogUtil;
import ch.threema.app.utils.IntentDataUtil;
import ch.threema.app.utils.LogUtil;
import ch.threema.app.utils.NameUtil;
import ch.threema.app.utils.RuntimeUtil;
import ch.threema.app.utils.TestUtil;
import ch.threema.app.voip.util.VoipUtil;
import ch.threema.storage.models.ContactModel;
import ch.threema.storage.models.GroupModel;
import static ch.threema.app.dialogs.ContactEditDialog.CONTACT_AVATAR_HEIGHT_PX;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class GroupDetailActivity extends GroupEditActivity implements SelectorDialog.SelectorDialogClickListener, GenericAlertDialog.DialogClickListener, TextEntryDialog.TextEntryDialogClickListener {

    private static final Logger logger = LoggerFactory.getLogger(GroupDetailActivity.class);

    private final int MODE_EDIT = 1;

    private final int MODE_READONLY = 2;

    private static final String DIALOG_TAG_LEAVE_GROUP = "leaveGroup";

    private static final String DIALOG_TAG_UPDATE_GROUP = "updateGroup";

    private static final String DIALOG_TAG_QUIT = "quit";

    private static final String DIALOG_TAG_CHOOSE_ACTION = "chooseAction";

    private static final String DIALOG_TAG_RESYNC_GROUP = "resyncGroup";

    private static final String DIALOG_TAG_DELETE_GROUP = "delG";

    private static final String DIALOG_TAG_CLONE_GROUP = "cg";

    private static final String DIALOG_TAG_CLONE_GROUP_CONFIRM = "cgc";

    private static final String DIALOG_TAG_CLONING_GROUP = "cgi";

    private static final String RUN_ON_ACTIVE_RELOAD = "reload";

    private static final int SELECTOR_OPTION_CONTACT_DETAIL = 0;

    private static final int SELECTOR_OPTION_CHAT = 1;

    private static final int SELECTOR_OPTION_CALL = 2;

    private static final int SELECTOR_OPTION_REMOVE = 3;

    private int operationMode;

    private int groupId;

    private EmojiEditText groupNameEditText;

    private boolean hasChanges = false;

    private String myIdentity;

    private GroupModel groupModel;

    private RecyclerView groupDetailRecyclerView;

    private GroupDetailAdapter groupDetailAdapter;

    private CollapsingToolbarLayout collapsingToolbar;

    private ResumePauseHandler resumePauseHandler;

    private DeviceService deviceService;

    private IdListService blackListIdentityService;

    private LicenseService licenseService;

    private AvatarEditView avatarEditView;

    private GroupDetailViewModel groupDetailViewModel;

    private ExtendedFloatingActionButton floatingActionButton;

    private final ResumePauseHandler.RunIfActive runIfActiveUpdate = new ResumePauseHandler.RunIfActive() {

        @Override
        public void runOnUiThread() {
            if (!ListenerUtil.mutListener.listen(2884)) {
                groupDetailViewModel.setGroupName(groupModel.getName());
            }
            if (!ListenerUtil.mutListener.listen(2885)) {
                setTitle();
            }
            if (!ListenerUtil.mutListener.listen(2886)) {
                groupDetailViewModel.setGroupIdentities(groupService.getGroupIdentities(groupModel));
            }
            if (!ListenerUtil.mutListener.listen(2887)) {
                sortGroupMembers();
            }
            if (!ListenerUtil.mutListener.listen(2888)) {
                setScrimColor();
            }
        }
    };

    private final AvatarEditView.AvatarEditListener avatarEditViewListener = new AvatarEditView.AvatarEditListener() {

        @Override
        public void onAvatarSet(File avatarFile1) {
            if (!ListenerUtil.mutListener.listen(2889)) {
                groupDetailViewModel.setAvatarFile(avatarFile1);
            }
            if (!ListenerUtil.mutListener.listen(2890)) {
                groupDetailViewModel.setIsAvatarRemoved(false);
            }
            if (!ListenerUtil.mutListener.listen(2891)) {
                setScrimColor();
            }
        }

        @Override
        public void onAvatarRemoved() {
            if (!ListenerUtil.mutListener.listen(2892)) {
                groupDetailViewModel.setAvatarFile(null);
            }
            if (!ListenerUtil.mutListener.listen(2893)) {
                groupDetailViewModel.setIsAvatarRemoved(true);
            }
            if (!ListenerUtil.mutListener.listen(2894)) {
                avatarEditView.setDefaultAvatar(null, groupModel);
            }
            if (!ListenerUtil.mutListener.listen(2895)) {
                setScrimColor();
            }
        }
    };

    private class SelectorInfo {

        public View view;

        public ContactModel contactModel;

        public ArrayList<Integer> optionsMap;
    }

    private ContactSettingsListener contactSettingsListener = new ContactSettingsListener() {

        @Override
        public void onSortingChanged() {
        }

        @Override
        public void onNameFormatChanged() {
        }

        @Override
        public void onAvatarSettingChanged() {
            if (!ListenerUtil.mutListener.listen(2896)) {
                resumePauseHandler.runOnActive(RUN_ON_ACTIVE_RELOAD, runIfActiveUpdate);
            }
        }

        @Override
        public void onInactiveContactsSettingChanged() {
        }

        @Override
        public void onNotificationSettingChanged(String uid) {
        }
    };

    private ContactListener contactListener = new ContactListener() {

        @Override
        public void onModified(ContactModel modifiedContactModel) {
            if (!ListenerUtil.mutListener.listen(2897)) {
                resumePauseHandler.runOnActive(RUN_ON_ACTIVE_RELOAD, runIfActiveUpdate);
            }
        }

        @Override
        public void onAvatarChanged(ContactModel contactModel) {
            if (!ListenerUtil.mutListener.listen(2898)) {
                this.onModified(contactModel);
            }
        }

        @Override
        public boolean handle(String identity) {
            return groupDetailViewModel.containsModel(identity);
        }
    };

    private GroupListener groupListener = new GroupListener() {

        @Override
        public void onCreate(GroupModel newGroupModel) {
            if (!ListenerUtil.mutListener.listen(2899)) {
                resumePauseHandler.runOnActive(RUN_ON_ACTIVE_RELOAD, runIfActiveUpdate);
            }
        }

        @Override
        public void onRename(GroupModel groupModel) {
            if (!ListenerUtil.mutListener.listen(2900)) {
                resumePauseHandler.runOnActive(RUN_ON_ACTIVE_RELOAD, runIfActiveUpdate);
            }
        }

        @Override
        public void onUpdatePhoto(GroupModel groupModel) {
            if (!ListenerUtil.mutListener.listen(2901)) {
                resumePauseHandler.runOnActive(RUN_ON_ACTIVE_RELOAD, runIfActiveUpdate);
            }
        }

        @Override
        public void onRemove(GroupModel groupModel) {
            if (!ListenerUtil.mutListener.listen(2902)) {
                resumePauseHandler.runOnActive(RUN_ON_ACTIVE_RELOAD, runIfActiveUpdate);
            }
        }

        @Override
        public void onNewMember(GroupModel group, String newIdentity, int previousMemberCount) {
            if (!ListenerUtil.mutListener.listen(2903)) {
                resumePauseHandler.runOnActive(RUN_ON_ACTIVE_RELOAD, runIfActiveUpdate);
            }
        }

        @Override
        public void onMemberLeave(GroupModel group, String identity, int previousMemberCount) {
            if (!ListenerUtil.mutListener.listen(2906)) {
                if (identity.equals(myIdentity)) {
                    if (!ListenerUtil.mutListener.listen(2905)) {
                        finishUp();
                    }
                } else {
                    if (!ListenerUtil.mutListener.listen(2904)) {
                        resumePauseHandler.runOnActive(RUN_ON_ACTIVE_RELOAD, runIfActiveUpdate);
                    }
                }
            }
        }

        @Override
        public void onMemberKicked(GroupModel group, String identity, int previousMemberCount) {
            if (!ListenerUtil.mutListener.listen(2909)) {
                if (identity.equals(myIdentity)) {
                    if (!ListenerUtil.mutListener.listen(2908)) {
                        finishUp();
                    }
                } else {
                    if (!ListenerUtil.mutListener.listen(2907)) {
                        resumePauseHandler.runOnActive(RUN_ON_ACTIVE_RELOAD, runIfActiveUpdate);
                    }
                }
            }
        }

        @Override
        public void onUpdate(GroupModel groupModel) {
        }

        @Override
        public void onLeave(GroupModel groupModel) {
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(2910)) {
            super.onCreate(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(2911)) {
            this.myIdentity = userService.getIdentity();
        }
        final ActionBar actionBar = getSupportActionBar();
        if (!ListenerUtil.mutListener.listen(2913)) {
            if (actionBar == null) {
                if (!ListenerUtil.mutListener.listen(2912)) {
                    finishUp();
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(2914)) {
            ConfigUtils.configureTransparentStatusBar(this);
        }
        if (!ListenerUtil.mutListener.listen(2915)) {
            this.resumePauseHandler = ResumePauseHandler.getByActivity(this, this);
        }
        if (!ListenerUtil.mutListener.listen(2916)) {
            this.groupDetailViewModel = new ViewModelProvider(this).get(GroupDetailViewModel.class);
        }
        final Toolbar toolbar = findViewById(R.id.toolbar);
        LinearLayout doneButton = toolbar.findViewById(R.id.action_done);
        if (!ListenerUtil.mutListener.listen(2917)) {
            this.avatarEditView = findViewById(R.id.avatar_edit_view);
        }
        if (!ListenerUtil.mutListener.listen(2918)) {
            this.collapsingToolbar = findViewById(R.id.collapsing_toolbar);
        }
        if (!ListenerUtil.mutListener.listen(2919)) {
            this.floatingActionButton = findViewById(R.id.floating);
        }
        if (!ListenerUtil.mutListener.listen(2920)) {
            this.groupDetailRecyclerView = findViewById(R.id.group_members_list);
        }
        if (!ListenerUtil.mutListener.listen(2921)) {
            this.collapsingToolbar.setTitle(" ");
        }
        if (!ListenerUtil.mutListener.listen(2922)) {
            this.groupNameEditText = findViewById(R.id.group_title);
        }
        try {
            if (!ListenerUtil.mutListener.listen(2925)) {
                this.deviceService = serviceManager.getDeviceService();
            }
            if (!ListenerUtil.mutListener.listen(2926)) {
                this.blackListIdentityService = serviceManager.getBlackListService();
            }
            if (!ListenerUtil.mutListener.listen(2927)) {
                this.licenseService = serviceManager.getLicenseService();
            }
        } catch (FileSystemNotPresentException e) {
            if (!ListenerUtil.mutListener.listen(2923)) {
                logger.error("Exception", e);
            }
            if (!ListenerUtil.mutListener.listen(2924)) {
                finishUp();
            }
            return;
        }
        if (!ListenerUtil.mutListener.listen(2931)) {
            if ((ListenerUtil.mutListener.listen(2929) ? ((ListenerUtil.mutListener.listen(2928) ? (this.deviceService == null && this.blackListIdentityService == null) : (this.deviceService == null || this.blackListIdentityService == null)) && this.licenseService == null) : ((ListenerUtil.mutListener.listen(2928) ? (this.deviceService == null && this.blackListIdentityService == null) : (this.deviceService == null || this.blackListIdentityService == null)) || this.licenseService == null))) {
                if (!ListenerUtil.mutListener.listen(2930)) {
                    finishUp();
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(2932)) {
            groupId = getIntent().getIntExtra(ThreemaApplication.INTENT_DATA_GROUP, 0);
        }
        if (!ListenerUtil.mutListener.listen(2939)) {
            if ((ListenerUtil.mutListener.listen(2937) ? (this.groupId >= 0) : (ListenerUtil.mutListener.listen(2936) ? (this.groupId <= 0) : (ListenerUtil.mutListener.listen(2935) ? (this.groupId > 0) : (ListenerUtil.mutListener.listen(2934) ? (this.groupId < 0) : (ListenerUtil.mutListener.listen(2933) ? (this.groupId != 0) : (this.groupId == 0))))))) {
                if (!ListenerUtil.mutListener.listen(2938)) {
                    finishUp();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(2940)) {
            this.groupModel = groupService.getById(this.groupId);
        }
        if (!ListenerUtil.mutListener.listen(2943)) {
            if (savedInstanceState == null) {
                if (!ListenerUtil.mutListener.listen(2941)) {
                    // new instance
                    this.groupDetailViewModel.setGroupContacts(this.contactService.getByIdentities(groupService.getGroupIdentities(this.groupModel)));
                }
                if (!ListenerUtil.mutListener.listen(2942)) {
                    this.groupDetailViewModel.setGroupName(this.groupModel.getName());
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(2944)) {
            this.avatarEditView.setHires(true);
        }
        if (!ListenerUtil.mutListener.listen(2949)) {
            if (groupDetailViewModel.getIsAvatarRemoved()) {
                if (!ListenerUtil.mutListener.listen(2948)) {
                    this.avatarEditView.setDefaultAvatar(null, groupModel);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(2947)) {
                    if (groupDetailViewModel.getAvatarFile() != null) {
                        if (!ListenerUtil.mutListener.listen(2946)) {
                            this.avatarEditView.setAvatarFile(groupDetailViewModel.getAvatarFile());
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(2945)) {
                            this.avatarEditView.loadAvatarForModel(null, groupModel);
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(2950)) {
            this.avatarEditView.setListener(this.avatarEditViewListener);
        }
        if (!ListenerUtil.mutListener.listen(2962)) {
            ((AppBarLayout) findViewById(R.id.appbar)).addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {

                @Override
                public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                    if (!ListenerUtil.mutListener.listen(2951)) {
                        logger.debug("Vertical offset: " + verticalOffset);
                    }
                    if (!ListenerUtil.mutListener.listen(2961)) {
                        if ((ListenerUtil.mutListener.listen(2956) ? (verticalOffset >= 0) : (ListenerUtil.mutListener.listen(2955) ? (verticalOffset <= 0) : (ListenerUtil.mutListener.listen(2954) ? (verticalOffset > 0) : (ListenerUtil.mutListener.listen(2953) ? (verticalOffset < 0) : (ListenerUtil.mutListener.listen(2952) ? (verticalOffset != 0) : (verticalOffset == 0))))))) {
                            if (!ListenerUtil.mutListener.listen(2960)) {
                                if (!floatingActionButton.isExtended()) {
                                    if (!ListenerUtil.mutListener.listen(2959)) {
                                        floatingActionButton.extend();
                                    }
                                }
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(2958)) {
                                if (floatingActionButton.isExtended()) {
                                    if (!ListenerUtil.mutListener.listen(2957)) {
                                        floatingActionButton.shrink();
                                    }
                                }
                            }
                        }
                    }
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(2963)) {
            this.sortGroupMembers();
        }
        if (!ListenerUtil.mutListener.listen(2964)) {
            setTitle();
        }
        if (!ListenerUtil.mutListener.listen(2983)) {
            if (this.groupService.isGroupOwner(this.groupModel)) {
                if (!ListenerUtil.mutListener.listen(2973)) {
                    operationMode = MODE_EDIT;
                }
                if (!ListenerUtil.mutListener.listen(2975)) {
                    doneButton.setOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View v) {
                            if (!ListenerUtil.mutListener.listen(2974)) {
                                saveGroupSettings();
                            }
                        }
                    });
                }
                if (!ListenerUtil.mutListener.listen(2981)) {
                    floatingActionButton.setOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View v) {
                            if (!ListenerUtil.mutListener.listen(2980)) {
                                if ((ListenerUtil.mutListener.listen(2976) ? (groupService != null || groupService.isGroupOwner(groupModel)) : (groupService != null && groupService.isGroupOwner(groupModel)))) {
                                    Intent intent = new Intent(GroupDetailActivity.this, GroupAddActivity.class);
                                    if (!ListenerUtil.mutListener.listen(2977)) {
                                        IntentDataUtil.append(groupModel, intent);
                                    }
                                    if (!ListenerUtil.mutListener.listen(2978)) {
                                        IntentDataUtil.append(groupDetailViewModel.getGroupContacts(), intent);
                                    }
                                    if (!ListenerUtil.mutListener.listen(2979)) {
                                        startActivityForResult(intent, ThreemaActivity.ACTIVITY_ID_GROUP_ADD);
                                    }
                                }
                            }
                        }
                    });
                }
                if (!ListenerUtil.mutListener.listen(2982)) {
                    groupNameEditText.setMaxByteSize(GroupModel.GROUP_NAME_MAX_LENGTH_BYTES);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(2965)) {
                    operationMode = MODE_READONLY;
                }
                if (!ListenerUtil.mutListener.listen(2966)) {
                    doneButton.setVisibility(View.GONE);
                }
                if (!ListenerUtil.mutListener.listen(2967)) {
                    groupNameEditText.setFocusable(false);
                }
                if (!ListenerUtil.mutListener.listen(2968)) {
                    groupNameEditText.setClickable(false);
                }
                if (!ListenerUtil.mutListener.listen(2969)) {
                    groupNameEditText.setFocusableInTouchMode(false);
                }
                if (!ListenerUtil.mutListener.listen(2970)) {
                    groupNameEditText.setBackground(null);
                }
                if (!ListenerUtil.mutListener.listen(2971)) {
                    floatingActionButton.hide();
                }
                if (!ListenerUtil.mutListener.listen(2972)) {
                    actionBar.setDisplayHomeAsUpEnabled(true);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(2984)) {
            this.groupDetailRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        }
        if (!ListenerUtil.mutListener.listen(2985)) {
            setupAdapter();
        }
        if (!ListenerUtil.mutListener.listen(2986)) {
            this.groupDetailRecyclerView.setAdapter(this.groupDetailAdapter);
        }
        final Observer<List<ContactModel>> groupMemberObserver = new Observer<List<ContactModel>>() {

            @Override
            public void onChanged(List<ContactModel> groupMembers) {
                if (!ListenerUtil.mutListener.listen(2987)) {
                    // Update the UI
                    groupDetailAdapter.setContactModels(groupMembers);
                }
            }
        };
        if (!ListenerUtil.mutListener.listen(2988)) {
            // Observe the LiveData, passing in this activity as the LifecycleOwner and the observer.
            groupDetailViewModel.getGroupMembers().observe(this, groupMemberObserver);
        }
        if (!ListenerUtil.mutListener.listen(2989)) {
            groupDetailViewModel.onDataChanged();
        }
        if (!ListenerUtil.mutListener.listen(2990)) {
            setScrimColor();
        }
        if (!ListenerUtil.mutListener.listen(2991)) {
            updateFloatingActionButton();
        }
        if (!ListenerUtil.mutListener.listen(2993)) {
            if (toolbar.getNavigationIcon() != null) {
                if (!ListenerUtil.mutListener.listen(2992)) {
                    toolbar.getNavigationIcon().setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_IN);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(2994)) {
            ListenerManager.contactSettingsListeners.add(this.contactSettingsListener);
        }
        if (!ListenerUtil.mutListener.listen(2995)) {
            ListenerManager.groupListeners.add(this.groupListener);
        }
        if (!ListenerUtil.mutListener.listen(2996)) {
            ListenerManager.contactListeners.add(this.contactListener);
        }
    }

    private void setScrimColor() {
        if (!ListenerUtil.mutListener.listen(3005)) {
            new Thread(new Runnable() {

                @Override
                public void run() {
                    @ColorInt
                    int color = getResources().getColor(R.color.material_grey_600);
                    if (!ListenerUtil.mutListener.listen(2999)) {
                        if (groupModel != null) {
                            Bitmap bitmap;
                            if (groupDetailViewModel.getAvatarFile() != null) {
                                bitmap = BitmapUtil.safeGetBitmapFromUri(GroupDetailActivity.this, Uri.fromFile(groupDetailViewModel.getAvatarFile()), CONTACT_AVATAR_HEIGHT_PX, true);
                            } else {
                                bitmap = groupService.getAvatar(groupModel, false);
                            }
                            if (!ListenerUtil.mutListener.listen(2998)) {
                                if (bitmap != null) {
                                    Palette palette = Palette.from(bitmap).generate();
                                    if (!ListenerUtil.mutListener.listen(2997)) {
                                        color = palette.getDarkVibrantColor(getResources().getColor(R.color.material_grey_600));
                                    }
                                }
                            }
                        }
                    }
                    @ColorInt
                    final int scrimColor = color;
                    if (!ListenerUtil.mutListener.listen(3004)) {
                        RuntimeUtil.runOnUiThread(new Runnable() {

                            @Override
                            public void run() {
                                if (!ListenerUtil.mutListener.listen(3003)) {
                                    if ((ListenerUtil.mutListener.listen(3000) ? (!isFinishing() || !isDestroyed()) : (!isFinishing() && !isDestroyed()))) {
                                        if (!ListenerUtil.mutListener.listen(3001)) {
                                            collapsingToolbar.setContentScrimColor(scrimColor);
                                        }
                                        if (!ListenerUtil.mutListener.listen(3002)) {
                                            collapsingToolbar.setStatusBarScrimColor(scrimColor);
                                        }
                                    }
                                }
                            }
                        });
                    }
                }
            }).start();
        }
    }

    private void setupAdapter() {
        if (!ListenerUtil.mutListener.listen(3006)) {
            this.groupDetailAdapter = new GroupDetailAdapter(this, this.groupModel);
        }
        if (!ListenerUtil.mutListener.listen(3009)) {
            this.groupDetailAdapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {

                @Override
                public void onChanged() {
                    if (!ListenerUtil.mutListener.listen(3007)) {
                        super.onChanged();
                    }
                    if (!ListenerUtil.mutListener.listen(3008)) {
                        updateFloatingActionButton();
                    }
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(3031)) {
            this.groupDetailAdapter.setOnClickListener(new GroupDetailAdapter.OnClickListener() {

                @Override
                public void onItemClick(View v, ContactModel contactModel) {
                    if (!ListenerUtil.mutListener.listen(3030)) {
                        if (contactModel != null) {
                            String identity = contactModel.getIdentity();
                            String shortName = NameUtil.getShortName(contactModel);
                            ArrayList<String> items = new ArrayList<>();
                            ArrayList<Integer> optionsMap = new ArrayList<>();
                            if (!ListenerUtil.mutListener.listen(3010)) {
                                items.add(getString(R.string.show_contact));
                            }
                            if (!ListenerUtil.mutListener.listen(3011)) {
                                optionsMap.add(SELECTOR_OPTION_CONTACT_DETAIL);
                            }
                            if (!ListenerUtil.mutListener.listen(3029)) {
                                if (!TestUtil.compare(myIdentity, identity)) {
                                    if (!ListenerUtil.mutListener.listen(3012)) {
                                        items.add(String.format(getString(R.string.chat_with), shortName));
                                    }
                                    if (!ListenerUtil.mutListener.listen(3013)) {
                                        optionsMap.add(SELECTOR_OPTION_CHAT);
                                    }
                                    if (!ListenerUtil.mutListener.listen(3017)) {
                                        if ((ListenerUtil.mutListener.listen(3014) ? (ContactUtil.canReceiveVoipMessages(contactModel, blackListIdentityService) || ConfigUtils.isCallsEnabled(GroupDetailActivity.this, preferenceService, licenseService)) : (ContactUtil.canReceiveVoipMessages(contactModel, blackListIdentityService) && ConfigUtils.isCallsEnabled(GroupDetailActivity.this, preferenceService, licenseService)))) {
                                            if (!ListenerUtil.mutListener.listen(3015)) {
                                                items.add(String.format(getString(R.string.call_with), shortName));
                                            }
                                            if (!ListenerUtil.mutListener.listen(3016)) {
                                                optionsMap.add(SELECTOR_OPTION_CALL);
                                            }
                                        }
                                    }
                                    if (!ListenerUtil.mutListener.listen(3022)) {
                                        if (operationMode == MODE_EDIT) {
                                            if (!ListenerUtil.mutListener.listen(3021)) {
                                                if ((ListenerUtil.mutListener.listen(3018) ? (groupModel != null || !TestUtil.compare(groupModel.getCreatorIdentity(), identity)) : (groupModel != null && !TestUtil.compare(groupModel.getCreatorIdentity(), identity)))) {
                                                    if (!ListenerUtil.mutListener.listen(3019)) {
                                                        items.add(String.format(getString(R.string.kick_user_from_group), shortName));
                                                    }
                                                    if (!ListenerUtil.mutListener.listen(3020)) {
                                                        optionsMap.add(SELECTOR_OPTION_REMOVE);
                                                    }
                                                }
                                            }
                                        }
                                    }
                                    SelectorDialog selectorDialog = SelectorDialog.newInstance(null, items, null);
                                    SelectorInfo selectorInfo = new SelectorInfo();
                                    if (!ListenerUtil.mutListener.listen(3023)) {
                                        selectorInfo.contactModel = contactModel;
                                    }
                                    if (!ListenerUtil.mutListener.listen(3024)) {
                                        selectorInfo.view = v;
                                    }
                                    if (!ListenerUtil.mutListener.listen(3025)) {
                                        selectorInfo.optionsMap = optionsMap;
                                    }
                                    if (!ListenerUtil.mutListener.listen(3026)) {
                                        selectorDialog.setData(selectorInfo);
                                    }
                                    try {
                                        if (!ListenerUtil.mutListener.listen(3028)) {
                                            selectorDialog.show(getSupportFragmentManager(), DIALOG_TAG_CHOOSE_ACTION);
                                        }
                                    } catch (IllegalStateException e) {
                                        if (!ListenerUtil.mutListener.listen(3027)) {
                                            logger.error("Exception", e);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            });
        }
    }

    @Override
    public int getLayoutResource() {
        return R.layout.activity_group_detail;
    }

    private void setTitle() {
        if (!ListenerUtil.mutListener.listen(3034)) {
            if (TestUtil.empty(groupDetailViewModel.getGroupName())) {
                if (!ListenerUtil.mutListener.listen(3033)) {
                    this.groupNameEditText.setText(groupService.getMembersString(this.groupModel));
                }
            } else {
                if (!ListenerUtil.mutListener.listen(3032)) {
                    this.groupNameEditText.setText(groupDetailViewModel.getGroupName());
                }
            }
        }
    }

    private void launchContactDetail(View view, String identity) {
        if (!ListenerUtil.mutListener.listen(3038)) {
            if (!this.myIdentity.equals(identity)) {
                Intent intent = new Intent(GroupDetailActivity.this, ContactDetailActivity.class);
                if (!ListenerUtil.mutListener.listen(3035)) {
                    intent.putExtra(ThreemaApplication.INTENT_DATA_CONTACT, identity);
                }
                if (!ListenerUtil.mutListener.listen(3036)) {
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                }
                ActivityOptionsCompat options = ActivityOptionsCompat.makeScaleUpAnimation(view, 0, 0, view.getWidth(), view.getHeight());
                if (!ListenerUtil.mutListener.listen(3037)) {
                    ActivityCompat.startActivityForResult(this, intent, ThreemaActivity.ACTIVITY_ID_CONTACT_DETAIL, options.toBundle());
                }
            }
        }
    }

    private void sortGroupMembers() {
        List<ContactModel> contactModels = groupDetailViewModel.getGroupContacts();
        if (!ListenerUtil.mutListener.listen(3039)) {
            Collections.sort(contactModels, (model1, model2) -> ContactUtil.getSafeNameString(model1, preferenceService).compareTo(ContactUtil.getSafeNameString(model2, preferenceService)));
        }
        if (!ListenerUtil.mutListener.listen(3040)) {
            groupDetailViewModel.setGroupContacts(contactModels);
        }
    }

    private void removeMemberFromGroup(final ContactModel contactModel) {
        if (!ListenerUtil.mutListener.listen(3043)) {
            if (contactModel != null) {
                if (!ListenerUtil.mutListener.listen(3041)) {
                    this.groupDetailViewModel.removeGroupContact(contactModel);
                }
                if (!ListenerUtil.mutListener.listen(3042)) {
                    this.hasChanges = true;
                }
            }
        }
    }

    @Override
    public void onPause() {
        if (!ListenerUtil.mutListener.listen(3044)) {
            super.onPause();
        }
        if (!ListenerUtil.mutListener.listen(3046)) {
            if (this.resumePauseHandler != null) {
                if (!ListenerUtil.mutListener.listen(3045)) {
                    this.resumePauseHandler.onPause();
                }
            }
        }
    }

    @Override
    public void onResume() {
        if (!ListenerUtil.mutListener.listen(3048)) {
            if (this.resumePauseHandler != null) {
                if (!ListenerUtil.mutListener.listen(3047)) {
                    this.resumePauseHandler.onResume();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(3049)) {
            super.onResume();
        }
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem groupSyncMenu = menu.findItem(R.id.menu_resync);
        MenuItem leaveGroupMenu = menu.findItem(R.id.menu_leave_group);
        MenuItem deleteGroupMenu = menu.findItem(R.id.menu_delete_group);
        MenuItem mediaGalleryMenu = menu.findItem(R.id.menu_gallery);
        MenuItem cloneMenu = menu.findItem(R.id.menu_clone_group);
        if (!ListenerUtil.mutListener.listen(3051)) {
            if (AppRestrictionUtil.isCreateGroupDisabled(this)) {
                if (!ListenerUtil.mutListener.listen(3050)) {
                    cloneMenu.setVisible(false);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(3056)) {
            if (groupModel != null) {
                if (!ListenerUtil.mutListener.listen(3052)) {
                    leaveGroupMenu.setVisible(true);
                }
                if (!ListenerUtil.mutListener.listen(3053)) {
                    deleteGroupMenu.setVisible(true);
                }
                if (!ListenerUtil.mutListener.listen(3055)) {
                    if (groupService.isGroupOwner(this.groupModel)) {
                        if (!ListenerUtil.mutListener.listen(3054)) {
                            // MODE_EDIT
                            groupSyncMenu.setVisible(true);
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(3060)) {
            if ((ListenerUtil.mutListener.listen(3057) ? (groupModel != null || !hiddenChatsListService.has(groupService.getUniqueIdString(this.groupModel))) : (groupModel != null && !hiddenChatsListService.has(groupService.getUniqueIdString(this.groupModel))))) {
                if (!ListenerUtil.mutListener.listen(3059)) {
                    mediaGalleryMenu.setVisible(true);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(3058)) {
                    mediaGalleryMenu.setVisible(false);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(3067)) {
            if ((ListenerUtil.mutListener.listen(3065) ? (operationMode >= MODE_READONLY) : (ListenerUtil.mutListener.listen(3064) ? (operationMode <= MODE_READONLY) : (ListenerUtil.mutListener.listen(3063) ? (operationMode > MODE_READONLY) : (ListenerUtil.mutListener.listen(3062) ? (operationMode < MODE_READONLY) : (ListenerUtil.mutListener.listen(3061) ? (operationMode == MODE_READONLY) : (operationMode != MODE_READONLY))))))) {
                if (!ListenerUtil.mutListener.listen(3066)) {
                    menu.findItem(R.id.action_send_message).setVisible(false);
                }
            }
        }
        return super.onPrepareOptionsMenu(menu);
    }

    @SuppressLint("RestrictedApi")
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!ListenerUtil.mutListener.listen(3068)) {
            super.onCreateOptionsMenu(menu);
        }
        if (!ListenerUtil.mutListener.listen(3069)) {
            getMenuInflater().inflate(R.menu.activity_group_detail, menu);
        }
        try {
            MenuBuilder menuBuilder = (MenuBuilder) menu;
            if (!ListenerUtil.mutListener.listen(3070)) {
                menuBuilder.setOptionalIconsVisible(true);
            }
        } catch (Exception ignored) {
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (!ListenerUtil.mutListener.listen(3091)) {
            switch(item.getItemId()) {
                case android.R.id.home:
                    if (!ListenerUtil.mutListener.listen(3071)) {
                        finishUp();
                    }
                    return true;
                case R.id.action_send_message:
                    if (!ListenerUtil.mutListener.listen(3077)) {
                        if (groupModel != null) {
                            Intent intent = new Intent(this, ComposeMessageActivity.class);
                            if (!ListenerUtil.mutListener.listen(3072)) {
                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            }
                            if (!ListenerUtil.mutListener.listen(3073)) {
                                intent.putExtra(ThreemaApplication.INTENT_DATA_GROUP, groupId);
                            }
                            if (!ListenerUtil.mutListener.listen(3074)) {
                                intent.putExtra(ThreemaApplication.INTENT_DATA_EDITFOCUS, Boolean.TRUE);
                            }
                            if (!ListenerUtil.mutListener.listen(3075)) {
                                startActivity(intent);
                            }
                            if (!ListenerUtil.mutListener.listen(3076)) {
                                finish();
                            }
                        }
                    }
                    break;
                case R.id.menu_resync:
                    if (!ListenerUtil.mutListener.listen(3078)) {
                        this.syncGroup();
                    }
                    break;
                case R.id.menu_leave_group:
                    int leaveMessageRes = operationMode == MODE_READONLY ? R.string.really_leave_group_message : R.string.really_leave_group_admin_message;
                    if (!ListenerUtil.mutListener.listen(3079)) {
                        GenericAlertDialog.newInstance(R.string.action_leave_group, Html.fromHtml(getString(leaveMessageRes)), R.string.ok, R.string.cancel).show(getSupportFragmentManager(), DIALOG_TAG_LEAVE_GROUP);
                    }
                    break;
                case R.id.menu_delete_group:
                    if (!ListenerUtil.mutListener.listen(3080)) {
                        GenericAlertDialog.newInstance(R.string.action_delete_group, groupService.isGroupOwner(groupModel) ? R.string.delete_my_group_message : R.string.delete_group_message, R.string.ok, R.string.cancel).show(getSupportFragmentManager(), DIALOG_TAG_DELETE_GROUP);
                    }
                    break;
                case R.id.menu_gallery:
                    if (!ListenerUtil.mutListener.listen(3089)) {
                        if ((ListenerUtil.mutListener.listen(3086) ? ((ListenerUtil.mutListener.listen(3085) ? (groupId >= 0) : (ListenerUtil.mutListener.listen(3084) ? (groupId <= 0) : (ListenerUtil.mutListener.listen(3083) ? (groupId < 0) : (ListenerUtil.mutListener.listen(3082) ? (groupId != 0) : (ListenerUtil.mutListener.listen(3081) ? (groupId == 0) : (groupId > 0)))))) || !hiddenChatsListService.has(groupService.getUniqueIdString(this.groupModel))) : ((ListenerUtil.mutListener.listen(3085) ? (groupId >= 0) : (ListenerUtil.mutListener.listen(3084) ? (groupId <= 0) : (ListenerUtil.mutListener.listen(3083) ? (groupId < 0) : (ListenerUtil.mutListener.listen(3082) ? (groupId != 0) : (ListenerUtil.mutListener.listen(3081) ? (groupId == 0) : (groupId > 0)))))) && !hiddenChatsListService.has(groupService.getUniqueIdString(this.groupModel))))) {
                            Intent mediaGalleryIntent = new Intent(this, MediaGalleryActivity.class);
                            if (!ListenerUtil.mutListener.listen(3087)) {
                                mediaGalleryIntent.putExtra(ThreemaApplication.INTENT_DATA_GROUP, groupId);
                            }
                            if (!ListenerUtil.mutListener.listen(3088)) {
                                startActivity(mediaGalleryIntent);
                            }
                        }
                    }
                    break;
                case R.id.menu_clone_group:
                    if (!ListenerUtil.mutListener.listen(3090)) {
                        GenericAlertDialog.newInstance(R.string.action_clone_group, R.string.clone_group_message, R.string.yes, R.string.no).show(getSupportFragmentManager(), DIALOG_TAG_CLONE_GROUP_CONFIRM);
                    }
                    break;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private void leaveGroupAndQuit() {
        if (!ListenerUtil.mutListener.listen(3092)) {
            new LeaveGroupAsyncTask(groupModel, groupService, this, null, this::finishUp).execute();
        }
    }

    private void deleteGroupAndQuit() {
        if (!ListenerUtil.mutListener.listen(3095)) {
            if (groupService.isGroupOwner(groupModel)) {
                if (!ListenerUtil.mutListener.listen(3094)) {
                    new DeleteMyGroupAsyncTask(groupModel, groupService, this, null, this::navigateHome).execute();
                }
            } else {
                if (!ListenerUtil.mutListener.listen(3093)) {
                    new DeleteGroupAsyncTask(groupModel, groupService, this, null, this::navigateHome).execute();
                }
            }
        }
    }

    @SuppressLint("StaticFieldLeak")
    private void cloneGroup(final String newGroupName) {
        if (!ListenerUtil.mutListener.listen(3105)) {
            new AsyncTask<Void, Void, GroupModel>() {

                @Override
                protected void onPreExecute() {
                    if (!ListenerUtil.mutListener.listen(3096)) {
                        GenericProgressDialog.newInstance(R.string.action_clone_group, R.string.please_wait).show(getSupportFragmentManager(), DIALOG_TAG_CLONING_GROUP);
                    }
                }

                @Override
                protected GroupModel doInBackground(Void... params) {
                    GroupModel model;
                    try {
                        Bitmap avatar = groupService.getAvatar(groupModel, true);
                        model = groupService.createGroup(newGroupName, groupService.getGroupIdentities(groupModel), avatar);
                    } catch (Exception e) {
                        if (!ListenerUtil.mutListener.listen(3097)) {
                            logger.error("Exception", e);
                        }
                        return null;
                    }
                    return model;
                }

                @Override
                protected void onPostExecute(GroupModel newModel) {
                    if (!ListenerUtil.mutListener.listen(3098)) {
                        DialogUtil.dismissDialog(getSupportFragmentManager(), DIALOG_TAG_CLONING_GROUP, true);
                    }
                    if (!ListenerUtil.mutListener.listen(3104)) {
                        if (newModel != null) {
                            Intent intent = new Intent(GroupDetailActivity.this, ComposeMessageActivity.class);
                            if (!ListenerUtil.mutListener.listen(3100)) {
                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            }
                            if (!ListenerUtil.mutListener.listen(3101)) {
                                intent.putExtra(ThreemaApplication.INTENT_DATA_GROUP, newModel.getId());
                            }
                            if (!ListenerUtil.mutListener.listen(3102)) {
                                startActivity(intent);
                            }
                            if (!ListenerUtil.mutListener.listen(3103)) {
                                finishUp();
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(3099)) {
                                Toast.makeText(GroupDetailActivity.this, getString(R.string.error_creating_group) + ": " + getString(R.string.internet_connection_required), Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                }
            }.execute();
        }
    }

    private void showConversation(String identity) {
        Intent intent = new Intent(this, ComposeMessageActivity.class);
        if (!ListenerUtil.mutListener.listen(3106)) {
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        }
        if (!ListenerUtil.mutListener.listen(3107)) {
            intent.putExtra(ThreemaApplication.INTENT_DATA_CONTACT, identity);
        }
        if (!ListenerUtil.mutListener.listen(3108)) {
            startActivity(intent);
        }
    }

    private void syncGroup() {
        if (!ListenerUtil.mutListener.listen(3116)) {
            if (this.groupService != null) {
                if (!ListenerUtil.mutListener.listen(3109)) {
                    GenericProgressDialog.newInstance(R.string.resync_group, R.string.please_wait).show(getSupportFragmentManager(), DIALOG_TAG_RESYNC_GROUP);
                }
                if (!ListenerUtil.mutListener.listen(3115)) {
                    new Thread(new Runnable() {

                        @Override
                        public void run() {
                            try {
                                if (!ListenerUtil.mutListener.listen(3112)) {
                                    groupService.sendSync(groupModel);
                                }
                                if (!ListenerUtil.mutListener.listen(3113)) {
                                    RuntimeUtil.runOnUiThread(() -> Toast.makeText(GroupDetailActivity.this, getString(R.string.group_was_synchronized), Toast.LENGTH_SHORT).show());
                                }
                                if (!ListenerUtil.mutListener.listen(3114)) {
                                    RuntimeUtil.runOnUiThread(() -> DialogUtil.dismissDialog(getSupportFragmentManager(), DIALOG_TAG_RESYNC_GROUP, true));
                                }
                            } catch (Exception x) {
                                if (!ListenerUtil.mutListener.listen(3110)) {
                                    RuntimeUtil.runOnUiThread(() -> DialogUtil.dismissDialog(getSupportFragmentManager(), DIALOG_TAG_RESYNC_GROUP, true));
                                }
                                if (!ListenerUtil.mutListener.listen(3111)) {
                                    LogUtil.exception(x, GroupDetailActivity.this);
                                }
                            }
                        }
                    }).start();
                }
            }
        }
    }

    @SuppressLint("StaticFieldLeak")
    private void saveGroupSettings() {
        if (!ListenerUtil.mutListener.listen(3119)) {
            if (groupNameEditText.getText() != null) {
                if (!ListenerUtil.mutListener.listen(3118)) {
                    this.groupDetailViewModel.setGroupName(groupNameEditText.getText().toString());
                }
            } else {
                if (!ListenerUtil.mutListener.listen(3117)) {
                    this.groupDetailViewModel.setGroupName("");
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(3127)) {
            new AsyncTask<Void, Void, GroupModel>() {

                @Override
                protected void onPreExecute() {
                    if (!ListenerUtil.mutListener.listen(3120)) {
                        GenericProgressDialog.newInstance(R.string.updating_group, R.string.please_wait).show(getSupportFragmentManager(), DIALOG_TAG_UPDATE_GROUP);
                    }
                }

                @Override
                protected GroupModel doInBackground(Void... params) {
                    GroupModel model;
                    if (!ListenerUtil.mutListener.listen(3121)) {
                        if (!deviceService.isOnline()) {
                            return null;
                        }
                    }
                    try {
                        Bitmap avatar = groupDetailViewModel.getAvatarFile() != null ? BitmapFactory.decodeFile(groupDetailViewModel.getAvatarFile().getPath()) : null;
                        model = groupService.updateGroup(groupModel, groupDetailViewModel.getGroupName(), groupDetailViewModel.getGroupIdentities(), avatar, groupDetailViewModel.getIsAvatarRemoved());
                    } catch (Exception x) {
                        if (!ListenerUtil.mutListener.listen(3122)) {
                            logger.error("Exception", x);
                        }
                        return null;
                    }
                    return model;
                }

                @Override
                protected void onPostExecute(GroupModel newModel) {
                    if (!ListenerUtil.mutListener.listen(3123)) {
                        DialogUtil.dismissDialog(getSupportFragmentManager(), DIALOG_TAG_UPDATE_GROUP, true);
                    }
                    if (!ListenerUtil.mutListener.listen(3126)) {
                        if (newModel != null) {
                            if (!ListenerUtil.mutListener.listen(3125)) {
                                finishUp();
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(3124)) {
                                SimpleStringAlertDialog.newInstance(R.string.updating_group, getString(R.string.error_creating_group) + ": " + getString(R.string.internet_connection_required)).show(getSupportFragmentManager(), "er");
                            }
                        }
                    }
                }
            }.execute();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (!ListenerUtil.mutListener.listen(3135)) {
            if (resultCode == Activity.RESULT_OK) {
                if (!ListenerUtil.mutListener.listen(3134)) {
                    switch(requestCode) {
                        case ThreemaActivity.ACTIVITY_ID_GROUP_ADD:
                            if (!ListenerUtil.mutListener.listen(3128)) {
                                // some users were added
                                groupDetailViewModel.addGroupContacts(IntentDataUtil.getContactIdentities(data));
                            }
                            if (!ListenerUtil.mutListener.listen(3129)) {
                                sortGroupMembers();
                            }
                            if (!ListenerUtil.mutListener.listen(3130)) {
                                this.hasChanges = true;
                            }
                            break;
                        default:
                            if (!ListenerUtil.mutListener.listen(3132)) {
                                if (this.avatarEditView != null) {
                                    if (!ListenerUtil.mutListener.listen(3131)) {
                                        this.avatarEditView.onActivityResult(requestCode, resultCode, data);
                                    }
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(3133)) {
                                super.onActivityResult(requestCode, resultCode, data);
                            }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(3137)) {
            if (requestCode == ThreemaActivity.ACTIVITY_ID_CONTACT_DETAIL) {
                if (!ListenerUtil.mutListener.listen(3136)) {
                    // contacts may have been edited
                    sortGroupMembers();
                }
            }
        }
    }

    @Override
    public void onDestroy() {
        if (!ListenerUtil.mutListener.listen(3138)) {
            ListenerManager.contactSettingsListeners.remove(this.contactSettingsListener);
        }
        if (!ListenerUtil.mutListener.listen(3139)) {
            ListenerManager.groupListeners.remove(this.groupListener);
        }
        if (!ListenerUtil.mutListener.listen(3140)) {
            ListenerManager.contactListeners.remove(this.contactListener);
        }
        if (!ListenerUtil.mutListener.listen(3142)) {
            if (this.resumePauseHandler != null) {
                if (!ListenerUtil.mutListener.listen(3141)) {
                    this.resumePauseHandler.onDestroy(this);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(3143)) {
            super.onDestroy();
        }
    }

    @Override
    public void onClick(String tag, int which, Object object) {
        SelectorInfo selectorInfo = (SelectorInfo) object;
        if (!ListenerUtil.mutListener.listen(3150)) {
            // click on selector
            if (selectorInfo.contactModel != null) {
                if (!ListenerUtil.mutListener.listen(3149)) {
                    switch(selectorInfo.optionsMap.get(which)) {
                        case SELECTOR_OPTION_CONTACT_DETAIL:
                            if (!ListenerUtil.mutListener.listen(3144)) {
                                launchContactDetail(selectorInfo.view, selectorInfo.contactModel.getIdentity());
                            }
                            break;
                        case SELECTOR_OPTION_CHAT:
                            if (!ListenerUtil.mutListener.listen(3145)) {
                                showConversation(selectorInfo.contactModel.getIdentity());
                            }
                            if (!ListenerUtil.mutListener.listen(3146)) {
                                finishUp();
                            }
                            break;
                        case SELECTOR_OPTION_REMOVE:
                            if (!ListenerUtil.mutListener.listen(3147)) {
                                removeMemberFromGroup(selectorInfo.contactModel);
                            }
                            break;
                        case SELECTOR_OPTION_CALL:
                            if (!ListenerUtil.mutListener.listen(3148)) {
                                VoipUtil.initiateCall(this, selectorInfo.contactModel, false, null);
                            }
                            break;
                        default:
                            break;
                    }
                }
            }
        }
    }

    @Override
    public void onCancel(String tag) {
    }

    @Override
    public void onYes(String tag, String text) {
        if (!ListenerUtil.mutListener.listen(3152)) {
            // text entry dialog
            switch(tag) {
                case DIALOG_TAG_CLONE_GROUP:
                    if (!ListenerUtil.mutListener.listen(3151)) {
                        cloneGroup(text);
                    }
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public void onNo(String tag) {
    }

    @Override
    public void onNeutral(String tag) {
    }

    @Override
    public void onYes(String tag, Object data) {
        if (!ListenerUtil.mutListener.listen(3157)) {
            switch(tag) {
                case DIALOG_TAG_LEAVE_GROUP:
                    if (!ListenerUtil.mutListener.listen(3153)) {
                        leaveGroupAndQuit();
                    }
                    break;
                case DIALOG_TAG_DELETE_GROUP:
                    if (!ListenerUtil.mutListener.listen(3154)) {
                        deleteGroupAndQuit();
                    }
                    break;
                case DIALOG_TAG_QUIT:
                    if (!ListenerUtil.mutListener.listen(3155)) {
                        saveGroupSettings();
                    }
                    break;
                case DIALOG_TAG_CLONE_GROUP_CONFIRM:
                    if (!ListenerUtil.mutListener.listen(3156)) {
                        TextEntryDialog.newInstance(R.string.action_clone_group, R.string.name, R.string.ok, R.string.cancel, groupModel.getName(), 0, 0).show(getSupportFragmentManager(), DIALOG_TAG_CLONE_GROUP);
                    }
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public void onBackPressed() {
        if (!ListenerUtil.mutListener.listen(3166)) {
            if ((ListenerUtil.mutListener.listen(3163) ? ((ListenerUtil.mutListener.listen(3162) ? (this.operationMode >= MODE_EDIT) : (ListenerUtil.mutListener.listen(3161) ? (this.operationMode <= MODE_EDIT) : (ListenerUtil.mutListener.listen(3160) ? (this.operationMode > MODE_EDIT) : (ListenerUtil.mutListener.listen(3159) ? (this.operationMode < MODE_EDIT) : (ListenerUtil.mutListener.listen(3158) ? (this.operationMode != MODE_EDIT) : (this.operationMode == MODE_EDIT)))))) || this.hasChanges) : ((ListenerUtil.mutListener.listen(3162) ? (this.operationMode >= MODE_EDIT) : (ListenerUtil.mutListener.listen(3161) ? (this.operationMode <= MODE_EDIT) : (ListenerUtil.mutListener.listen(3160) ? (this.operationMode > MODE_EDIT) : (ListenerUtil.mutListener.listen(3159) ? (this.operationMode < MODE_EDIT) : (ListenerUtil.mutListener.listen(3158) ? (this.operationMode != MODE_EDIT) : (this.operationMode == MODE_EDIT)))))) && this.hasChanges))) {
                if (!ListenerUtil.mutListener.listen(3165)) {
                    GenericAlertDialog.newInstance(R.string.save_changes, R.string.save_group_changes, R.string.yes, R.string.no, false).show(getSupportFragmentManager(), DIALOG_TAG_QUIT);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(3164)) {
                    finishUp();
                }
            }
        }
    }

    @Override
    public void onNo(String tag, Object data) {
        if (!ListenerUtil.mutListener.listen(3168)) {
            switch(tag) {
                case DIALOG_TAG_QUIT:
                    if (!ListenerUtil.mutListener.listen(3167)) {
                        finishUp();
                    }
                    break;
                default:
                    break;
            }
        }
    }

    private void updateFloatingActionButton() {
        if (!ListenerUtil.mutListener.listen(3180)) {
            if ((ListenerUtil.mutListener.listen(3170) ? ((ListenerUtil.mutListener.listen(3169) ? (this.floatingActionButton != null || this.groupService != null) : (this.floatingActionButton != null && this.groupService != null)) || this.groupDetailAdapter != null) : ((ListenerUtil.mutListener.listen(3169) ? (this.floatingActionButton != null || this.groupService != null) : (this.floatingActionButton != null && this.groupService != null)) && this.groupDetailAdapter != null))) {
                if (!ListenerUtil.mutListener.listen(3179)) {
                    if (this.groupService.isGroupOwner(this.groupModel)) {
                        if (!ListenerUtil.mutListener.listen(3178)) {
                            if ((ListenerUtil.mutListener.listen(3175) ? (this.groupDetailAdapter.getItemCount() >= getResources().getInteger(R.integer.max_group_size)) : (ListenerUtil.mutListener.listen(3174) ? (this.groupDetailAdapter.getItemCount() <= getResources().getInteger(R.integer.max_group_size)) : (ListenerUtil.mutListener.listen(3173) ? (this.groupDetailAdapter.getItemCount() < getResources().getInteger(R.integer.max_group_size)) : (ListenerUtil.mutListener.listen(3172) ? (this.groupDetailAdapter.getItemCount() != getResources().getInteger(R.integer.max_group_size)) : (ListenerUtil.mutListener.listen(3171) ? (this.groupDetailAdapter.getItemCount() == getResources().getInteger(R.integer.max_group_size)) : (this.groupDetailAdapter.getItemCount() > getResources().getInteger(R.integer.max_group_size)))))))) {
                                if (!ListenerUtil.mutListener.listen(3177)) {
                                    this.floatingActionButton.hide();
                                }
                            } else {
                                if (!ListenerUtil.mutListener.listen(3176)) {
                                    this.floatingActionButton.show();
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private void finishUp() {
        if (!ListenerUtil.mutListener.listen(3181)) {
            finish();
        }
    }

    private void navigateHome() {
        Intent intent = new Intent(GroupDetailActivity.this, HomeActivity.class);
        if (!ListenerUtil.mutListener.listen(3182)) {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        }
        if (!ListenerUtil.mutListener.listen(3183)) {
            startActivity(intent);
        }
        if (!ListenerUtil.mutListener.listen(3184)) {
            ActivityCompat.finishAffinity(GroupDetailActivity.this);
        }
        if (!ListenerUtil.mutListener.listen(3185)) {
            overridePendingTransition(0, 0);
        }
    }

    /* callbacks from MyAvatarView */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (!ListenerUtil.mutListener.listen(3186)) {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
        if (!ListenerUtil.mutListener.listen(3188)) {
            if (this.avatarEditView != null) {
                if (!ListenerUtil.mutListener.listen(3187)) {
                    this.avatarEditView.onRequestPermissionsResult(requestCode, permissions, grantResults);
                }
            }
        }
    }
}
