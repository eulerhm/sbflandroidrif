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

import android.Manifest;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.File;
import java.util.Date;
import java.util.List;
import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.UiThread;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.palette.graphics.Palette;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import ch.threema.app.R;
import ch.threema.app.ThreemaApplication;
import ch.threema.app.adapters.ContactDetailAdapter;
import ch.threema.app.dialogs.ContactEditDialog;
import ch.threema.app.dialogs.GenericAlertDialog;
import ch.threema.app.dialogs.GenericProgressDialog;
import ch.threema.app.dialogs.SimpleStringAlertDialog;
import ch.threema.app.listeners.ContactListener;
import ch.threema.app.listeners.ContactSettingsListener;
import ch.threema.app.listeners.GroupListener;
import ch.threema.app.managers.ListenerManager;
import ch.threema.app.messagereceiver.MessageReceiver;
import ch.threema.app.services.ContactService;
import ch.threema.app.services.DeadlineListService;
import ch.threema.app.services.GroupService;
import ch.threema.app.services.IdListService;
import ch.threema.app.services.MessageService;
import ch.threema.app.services.PreferenceService;
import ch.threema.app.services.QRCodeService;
import ch.threema.app.services.license.LicenseService;
import ch.threema.app.ui.AvatarEditView;
import ch.threema.app.ui.ResumePauseHandler;
import ch.threema.app.ui.TooltipPopup;
import ch.threema.app.utils.AndroidContactUtil;
import ch.threema.app.utils.AppRestrictionUtil;
import ch.threema.app.utils.ConfigUtils;
import ch.threema.app.utils.ContactUtil;
import ch.threema.app.utils.DialogUtil;
import ch.threema.app.utils.LogUtil;
import ch.threema.app.utils.NameUtil;
import ch.threema.app.utils.QRScannerUtil;
import ch.threema.app.utils.RuntimeUtil;
import ch.threema.app.utils.ShareUtil;
import ch.threema.app.utils.TestUtil;
import ch.threema.app.utils.ViewUtil;
import ch.threema.app.voip.services.VoipStateService;
import ch.threema.app.voip.util.VoipUtil;
import ch.threema.base.VerificationLevel;
import ch.threema.storage.models.ContactModel;
import ch.threema.storage.models.GroupModel;
import static ch.threema.app.utils.QRScannerUtil.REQUEST_CODE_QR_SCANNER;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class ContactDetailActivity extends ThreemaToolbarActivity implements LifecycleOwner, GenericAlertDialog.DialogClickListener, ContactEditDialog.ContactEditDialogClickListener {

    private static final Logger logger = LoggerFactory.getLogger(ContactDetailActivity.class);

    private static final String DIALOG_TAG_EDIT = "cedit";

    private static final String DIALOG_TAG_DELETE_CONTACT = "deleteContact";

    private static final String DIALOG_TAG_EXCLUDE_CONTACT = "excludeContact";

    private static final String DIALOG_TAG_DELETING_CONTACT = "dliC";

    private static final String DIALOG_TAG_ADD_CONTACT = "dac";

    private static final String DIALOG_TAG_CONFIRM_BLOCK = "block";

    private static final int PERMISSION_REQUEST_CAMERA = 2;

    private static final String RUN_ON_ACTIVE_RELOAD = "reload";

    private static final String RUN_ON_ACTIVE_RELOAD_GROUP = "reload_group";

    private ContactModel contact;

    private String identity;

    private ContactService contactService;

    private GroupService groupService;

    private IdListService blackListIdentityService, profilePicRecipientsService;

    private MessageService messageService;

    private DeadlineListService hiddenChatsListService;

    private LicenseService licenseService;

    private VoipStateService voipStateService;

    private MenuItem blockMenuItem = null, profilePicItem = null, profilePicSendItem = null, callItem = null;

    private boolean isReadonly;

    private ResumePauseHandler resumePauseHandler;

    private RecyclerView contactDetailRecyclerView;

    private AvatarEditView avatarEditView;

    private FloatingActionButton floatingActionButton;

    private TextView contactTitle;

    private CollapsingToolbarLayout collapsingToolbar;

    private List<GroupModel> groupList;

    private boolean isDisabledProfilePicReleaseSettings = false;

    private View workIcon;

    private final ResumePauseHandler.RunIfActive runIfActiveUpdate = new ResumePauseHandler.RunIfActive() {

        @Override
        public void runOnUiThread() {
            if (!ListenerUtil.mutListener.listen(2021)) {
                reload();
            }
            if (!ListenerUtil.mutListener.listen(2022)) {
                groupList = groupService.getGroupsByIdentity(identity);
            }
            if (!ListenerUtil.mutListener.listen(2023)) {
                contactDetailRecyclerView.setAdapter(setupAdapter());
            }
        }
    };

    private final ResumePauseHandler.RunIfActive runIfActiveGroupUpdate = new ResumePauseHandler.RunIfActive() {

        @Override
        public void runOnUiThread() {
            if (!ListenerUtil.mutListener.listen(2024)) {
                groupList = groupService.getGroupsByIdentity(identity);
            }
            if (!ListenerUtil.mutListener.listen(2025)) {
                contactDetailRecyclerView.setAdapter(setupAdapter());
            }
        }
    };

    private final ContactSettingsListener contactSettingsListener = new ContactSettingsListener() {

        @Override
        public void onSortingChanged() {
        }

        @Override
        public void onNameFormatChanged() {
            if (!ListenerUtil.mutListener.listen(2026)) {
                resumePauseHandler.runOnActive(RUN_ON_ACTIVE_RELOAD, runIfActiveUpdate);
            }
        }

        @Override
        public void onAvatarSettingChanged() {
        }

        @Override
        public void onInactiveContactsSettingChanged() {
        }

        @Override
        public void onNotificationSettingChanged(String uid) {
        }
    };

    private final ContactListener contactListener = new ContactListener() {

        @Override
        public void onModified(ContactModel modifiedContactModel) {
            if (!ListenerUtil.mutListener.listen(2027)) {
                RuntimeUtil.runOnUiThread(() -> {
                    updateBlockMenu();
                });
            }
            if (!ListenerUtil.mutListener.listen(2028)) {
                resumePauseHandler.runOnActive(RUN_ON_ACTIVE_RELOAD, runIfActiveUpdate);
            }
        }

        @Override
        public void onAvatarChanged(ContactModel contactModel) {
            if (!ListenerUtil.mutListener.listen(2029)) {
                RuntimeUtil.runOnUiThread(() -> updateProfilepicMenu());
            }
            if (!ListenerUtil.mutListener.listen(2030)) {
                resumePauseHandler.runOnActive(RUN_ON_ACTIVE_RELOAD, runIfActiveUpdate);
            }
        }

        @Override
        public void onRemoved(ContactModel removedContactModel) {
            if (!ListenerUtil.mutListener.listen(2031)) {
                // whaat, finish!
                RuntimeUtil.runOnUiThread(() -> finish());
            }
        }

        @Override
        public boolean handle(String identity) {
            return TestUtil.compare(contact.getIdentity(), identity);
        }
    };

    private final GroupListener groupListener = new GroupListener() {

        @Override
        public void onCreate(GroupModel newGroupModel) {
            if (!ListenerUtil.mutListener.listen(2032)) {
                resumePauseHandler.runOnActive(RUN_ON_ACTIVE_RELOAD_GROUP, runIfActiveGroupUpdate);
            }
        }

        @Override
        public void onRename(GroupModel groupModel) {
            if (!ListenerUtil.mutListener.listen(2033)) {
                resumePauseHandler.runOnActive(RUN_ON_ACTIVE_RELOAD_GROUP, runIfActiveGroupUpdate);
            }
        }

        @Override
        public void onUpdatePhoto(GroupModel groupModel) {
            if (!ListenerUtil.mutListener.listen(2034)) {
                resumePauseHandler.runOnActive(RUN_ON_ACTIVE_RELOAD_GROUP, runIfActiveGroupUpdate);
            }
        }

        @Override
        public void onRemove(GroupModel groupModel) {
            if (!ListenerUtil.mutListener.listen(2035)) {
                resumePauseHandler.runOnActive(RUN_ON_ACTIVE_RELOAD_GROUP, runIfActiveGroupUpdate);
            }
        }

        @Override
        public void onNewMember(GroupModel group, String newIdentity, int previousMemberCount) {
            if (!ListenerUtil.mutListener.listen(2037)) {
                if (newIdentity.equals(identity)) {
                    if (!ListenerUtil.mutListener.listen(2036)) {
                        resumePauseHandler.runOnActive(RUN_ON_ACTIVE_RELOAD_GROUP, runIfActiveGroupUpdate);
                    }
                }
            }
        }

        @Override
        public void onMemberLeave(GroupModel group, String leftIdentity, int previousMemberCount) {
            if (!ListenerUtil.mutListener.listen(2039)) {
                if (leftIdentity.equals(identity)) {
                    if (!ListenerUtil.mutListener.listen(2038)) {
                        resumePauseHandler.runOnActive(RUN_ON_ACTIVE_RELOAD_GROUP, runIfActiveGroupUpdate);
                    }
                }
            }
        }

        @Override
        public void onMemberKicked(GroupModel group, String kickedIdentity, int previousMemberCount) {
            if (!ListenerUtil.mutListener.listen(2041)) {
                if (kickedIdentity.equals(identity)) {
                    if (!ListenerUtil.mutListener.listen(2040)) {
                        resumePauseHandler.runOnActive(RUN_ON_ACTIVE_RELOAD_GROUP, runIfActiveGroupUpdate);
                    }
                }
            }
        }

        @Override
        public void onUpdate(GroupModel groupModel) {
        }

        @Override
        public void onLeave(GroupModel groupModel) {
            if (!ListenerUtil.mutListener.listen(2042)) {
                resumePauseHandler.runOnActive(RUN_ON_ACTIVE_RELOAD_GROUP, runIfActiveGroupUpdate);
            }
        }
    };

    @Override
    public int getLayoutResource() {
        return R.layout.activity_contact_detail;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(2043)) {
            super.onCreate(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(2044)) {
            this.identity = this.getIntent().getStringExtra(ThreemaApplication.INTENT_DATA_CONTACT);
        }
        if (!ListenerUtil.mutListener.listen(2053)) {
            if ((ListenerUtil.mutListener.listen(2050) ? (this.identity == null && (ListenerUtil.mutListener.listen(2049) ? (this.identity.length() >= 0) : (ListenerUtil.mutListener.listen(2048) ? (this.identity.length() <= 0) : (ListenerUtil.mutListener.listen(2047) ? (this.identity.length() > 0) : (ListenerUtil.mutListener.listen(2046) ? (this.identity.length() < 0) : (ListenerUtil.mutListener.listen(2045) ? (this.identity.length() != 0) : (this.identity.length() == 0))))))) : (this.identity == null || (ListenerUtil.mutListener.listen(2049) ? (this.identity.length() >= 0) : (ListenerUtil.mutListener.listen(2048) ? (this.identity.length() <= 0) : (ListenerUtil.mutListener.listen(2047) ? (this.identity.length() > 0) : (ListenerUtil.mutListener.listen(2046) ? (this.identity.length() < 0) : (ListenerUtil.mutListener.listen(2045) ? (this.identity.length() != 0) : (this.identity.length() == 0))))))))) {
                if (!ListenerUtil.mutListener.listen(2051)) {
                    logger.error("no identity", this);
                }
                if (!ListenerUtil.mutListener.listen(2052)) {
                    this.finish();
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(2055)) {
            if (this.identity.equals(getMyIdentity())) {
                if (!ListenerUtil.mutListener.listen(2054)) {
                    this.finish();
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(2056)) {
            ConfigUtils.configureTransparentStatusBar(this);
        }
        final ActionBar actionBar = getSupportActionBar();
        if (!ListenerUtil.mutListener.listen(2058)) {
            if (actionBar != null) {
                if (!ListenerUtil.mutListener.listen(2057)) {
                    actionBar.setDisplayHomeAsUpEnabled(true);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(2059)) {
            this.resumePauseHandler = ResumePauseHandler.getByActivity(this, this);
        }
        try {
            if (!ListenerUtil.mutListener.listen(2062)) {
                this.contactService = serviceManager.getContactService();
            }
            if (!ListenerUtil.mutListener.listen(2063)) {
                this.blackListIdentityService = serviceManager.getBlackListService();
            }
            if (!ListenerUtil.mutListener.listen(2064)) {
                this.profilePicRecipientsService = serviceManager.getProfilePicRecipientsService();
            }
            if (!ListenerUtil.mutListener.listen(2065)) {
                this.groupService = serviceManager.getGroupService();
            }
            if (!ListenerUtil.mutListener.listen(2066)) {
                this.messageService = serviceManager.getMessageService();
            }
            if (!ListenerUtil.mutListener.listen(2067)) {
                this.hiddenChatsListService = serviceManager.getHiddenChatsListService();
            }
            if (!ListenerUtil.mutListener.listen(2068)) {
                this.licenseService = serviceManager.getLicenseService();
            }
            if (!ListenerUtil.mutListener.listen(2069)) {
                this.voipStateService = serviceManager.getVoipStateService();
            }
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(2060)) {
                LogUtil.exception(e, this);
            }
            if (!ListenerUtil.mutListener.listen(2061)) {
                this.finish();
            }
            return;
        }
        if (!ListenerUtil.mutListener.listen(2070)) {
            this.collapsingToolbar = findViewById(R.id.collapsing_toolbar);
        }
        if (!ListenerUtil.mutListener.listen(2071)) {
            this.collapsingToolbar.setTitle(" ");
        }
        if (!ListenerUtil.mutListener.listen(2074)) {
            if (this.contactService == null) {
                if (!ListenerUtil.mutListener.listen(2072)) {
                    logger.error("no contact service", this);
                }
                if (!ListenerUtil.mutListener.listen(2073)) {
                    finish();
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(2075)) {
            this.contact = this.contactService.getByIdentity(this.identity);
        }
        if (!ListenerUtil.mutListener.listen(2078)) {
            if (this.contact == null) {
                if (!ListenerUtil.mutListener.listen(2076)) {
                    Toast.makeText(this, R.string.contact_not_found, Toast.LENGTH_LONG).show();
                }
                if (!ListenerUtil.mutListener.listen(2077)) {
                    this.finish();
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(2079)) {
            this.avatarEditView = findViewById(R.id.avatar_edit_view);
        }
        if (!ListenerUtil.mutListener.listen(2080)) {
            this.avatarEditView.setHires(true);
        }
        if (!ListenerUtil.mutListener.listen(2081)) {
            this.avatarEditView.setContactModel(contact);
        }
        if (!ListenerUtil.mutListener.listen(2082)) {
            this.isReadonly = getIntent().getBooleanExtra(ThreemaApplication.INTENT_DATA_CONTACT_READONLY, false);
        }
        if (!ListenerUtil.mutListener.listen(2083)) {
            this.contactDetailRecyclerView = findViewById(R.id.contact_group_list);
        }
        if (!ListenerUtil.mutListener.listen(2086)) {
            if (this.contactDetailRecyclerView == null) {
                if (!ListenerUtil.mutListener.listen(2084)) {
                    logger.error("list not available");
                }
                if (!ListenerUtil.mutListener.listen(2085)) {
                    this.finish();
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(2087)) {
            this.contactTitle = findViewById(R.id.contact_title);
        }
        if (!ListenerUtil.mutListener.listen(2088)) {
            this.workIcon = findViewById(R.id.work_icon);
        }
        if (!ListenerUtil.mutListener.listen(2089)) {
            ViewUtil.show(workIcon, contactService.showBadge(contact));
        }
        if (!ListenerUtil.mutListener.listen(2090)) {
            this.workIcon.setContentDescription(getString(ConfigUtils.isWorkBuild() ? R.string.private_contact : R.string.threema_work_contact));
        }
        if (!ListenerUtil.mutListener.listen(2091)) {
            this.groupList = this.groupService.getGroupsByIdentity(this.identity);
        }
        if (!ListenerUtil.mutListener.listen(2094)) {
            if (ConfigUtils.isWorkRestricted()) {
                Boolean value = AppRestrictionUtil.getBooleanRestriction(getString(R.string.restriction__disable_send_profile_picture));
                if (!ListenerUtil.mutListener.listen(2093)) {
                    if (value != null) {
                        if (!ListenerUtil.mutListener.listen(2092)) {
                            isDisabledProfilePicReleaseSettings = value;
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(2095)) {
            this.contactDetailRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        }
        if (!ListenerUtil.mutListener.listen(2096)) {
            this.contactDetailRecyclerView.setAdapter(setupAdapter());
        }
        if (!ListenerUtil.mutListener.listen(2106)) {
            if (this.contact.isHidden()) {
                if (!ListenerUtil.mutListener.listen(2104)) {
                    this.reload();
                }
                if (!ListenerUtil.mutListener.listen(2105)) {
                    GenericAlertDialog.newInstance(R.string.menu_add_contact, String.format(getString(R.string.contact_add_confirm), NameUtil.getDisplayNameOrNickname(contact, true)), R.string.yes, R.string.no).show(getSupportFragmentManager(), DIALOG_TAG_ADD_CONTACT);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(2097)) {
                    onCreateLocal();
                }
                if (!ListenerUtil.mutListener.listen(2098)) {
                    this.reload();
                }
                if (!ListenerUtil.mutListener.listen(2103)) {
                    if (savedInstanceState == null) {
                        if (!ListenerUtil.mutListener.listen(2102)) {
                            if ((ListenerUtil.mutListener.listen(2099) ? (!ConfigUtils.isWorkBuild() || contactService.showBadge(contact)) : (!ConfigUtils.isWorkBuild() && contactService.showBadge(contact)))) {
                                if (!ListenerUtil.mutListener.listen(2101)) {
                                    if (!preferenceService.getIsWorkHintTooltipShown()) {
                                        if (!ListenerUtil.mutListener.listen(2100)) {
                                            showWorkTooltip();
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

    private void onCreateLocal() {
        if (!ListenerUtil.mutListener.listen(2107)) {
            ListenerManager.contactListeners.add(this.contactListener);
        }
        if (!ListenerUtil.mutListener.listen(2108)) {
            ListenerManager.contactSettingsListeners.add(this.contactSettingsListener);
        }
        if (!ListenerUtil.mutListener.listen(2109)) {
            ListenerManager.groupListeners.add(this.groupListener);
        }
        if (!ListenerUtil.mutListener.listen(2110)) {
            this.floatingActionButton = findViewById(R.id.floating);
        }
        if (!ListenerUtil.mutListener.listen(2112)) {
            this.floatingActionButton.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    if (!ListenerUtil.mutListener.listen(2111)) {
                        openContactEditor();
                    }
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(2116)) {
            if ((ListenerUtil.mutListener.listen(2113) ? (preferenceService.isSyncContacts() || ContactUtil.isSynchronized(contact)) : (preferenceService.isSyncContacts() && ContactUtil.isSynchronized(contact)))) {
                if (!ListenerUtil.mutListener.listen(2114)) {
                    floatingActionButton.setContentDescription(getString(R.string.edit));
                }
                if (!ListenerUtil.mutListener.listen(2115)) {
                    floatingActionButton.setImageResource(R.drawable.ic_outline_contacts_app_24);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(2118)) {
            if (getToolbar().getNavigationIcon() != null) {
                if (!ListenerUtil.mutListener.listen(2117)) {
                    getToolbar().getNavigationIcon().setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_IN);
                }
            }
        }
    }

    private void showWorkTooltip() {
        if (!ListenerUtil.mutListener.listen(2119)) {
            workIcon.postDelayed(() -> {
                int[] location = new int[2];
                workIcon.getLocationOnScreen(location);
                location[0] += workIcon.getWidth() / 2;
                location[1] += workIcon.getHeight();
                final TooltipPopup workTooltipPopup = new TooltipPopup(this, R.string.preferences__tooltip_work_hint_shown, R.layout.popup_tooltip_top_left_work, this, new Intent(this, WorkExplainActivity.class));
                workTooltipPopup.show(this, workIcon, getString(R.string.tooltip_work_hint), TooltipPopup.ALIGN_BELOW_ANCHOR_ARROW_LEFT, location, 0);
                final AppBarLayout appBarLayout = findViewById(R.id.appbar);
                if (appBarLayout != null) {
                    appBarLayout.addOnOffsetChangedListener(new AppBarLayout.BaseOnOffsetChangedListener() {

                        @Override
                        public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                            workTooltipPopup.dismiss(false);
                            appBarLayout.removeOnOffsetChangedListener(this);
                        }
                    });
                }
                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        RuntimeUtil.runOnUiThread(new Runnable() {

                            @Override
                            public void run() {
                                workTooltipPopup.dismiss(false);
                            }
                        });
                    }
                }, 4000);
            }, 1000);
        }
    }

    private ContactDetailAdapter setupAdapter() {
        ContactDetailAdapter groupMembershipAdapter = new ContactDetailAdapter(this, this.groupList, contact);
        if (!ListenerUtil.mutListener.listen(2123)) {
            groupMembershipAdapter.setOnClickListener(new ContactDetailAdapter.OnClickListener() {

                @Override
                public void onItemClick(View v, GroupModel groupModel) {
                    Intent intent = new Intent(ContactDetailActivity.this, GroupDetailActivity.class);
                    if (!ListenerUtil.mutListener.listen(2120)) {
                        intent.putExtra(ThreemaApplication.INTENT_DATA_GROUP, groupModel.getId());
                    }
                    if (!ListenerUtil.mutListener.listen(2121)) {
                        startActivityForResult(intent, ThreemaActivity.ACTIVITY_ID_GROUP_DETAIL);
                    }
                }

                @Override
                public void onVerificationInfoClick(View v) {
                    Intent intent = new Intent(ContactDetailActivity.this, VerificationLevelActivity.class);
                    if (!ListenerUtil.mutListener.listen(2122)) {
                        startActivity(intent);
                    }
                }
            });
        }
        return groupMembershipAdapter;
    }

    @Override
    protected void onDestroy() {
        if (!ListenerUtil.mutListener.listen(2125)) {
            if (floatingActionButton != null) {
                if (!ListenerUtil.mutListener.listen(2124)) {
                    floatingActionButton.hide();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(2126)) {
            ListenerManager.contactListeners.remove(this.contactListener);
        }
        if (!ListenerUtil.mutListener.listen(2127)) {
            ListenerManager.contactSettingsListeners.remove(this.contactSettingsListener);
        }
        if (!ListenerUtil.mutListener.listen(2128)) {
            ListenerManager.groupListeners.remove(this.groupListener);
        }
        if (!ListenerUtil.mutListener.listen(2130)) {
            if (this.resumePauseHandler != null) {
                if (!ListenerUtil.mutListener.listen(2129)) {
                    this.resumePauseHandler.onDestroy(this);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(2131)) {
            super.onDestroy();
        }
    }

    @Override
    public void onPause() {
        if (!ListenerUtil.mutListener.listen(2132)) {
            super.onPause();
        }
        if (!ListenerUtil.mutListener.listen(2134)) {
            if (this.resumePauseHandler != null) {
                if (!ListenerUtil.mutListener.listen(2133)) {
                    this.resumePauseHandler.onPause();
                }
            }
        }
    }

    private void openContactEditor() {
        if (!ListenerUtil.mutListener.listen(2137)) {
            if (contact != null) {
                if (!ListenerUtil.mutListener.listen(2136)) {
                    if (!AndroidContactUtil.getInstance().openContactEditor(this, contact)) {
                        if (!ListenerUtil.mutListener.listen(2135)) {
                            editName();
                        }
                    }
                }
            }
        }
    }

    private void setScrimColor() {
        if (!ListenerUtil.mutListener.listen(2146)) {
            new Thread(new Runnable() {

                @Override
                public void run() {
                    @ColorInt
                    int color = getResources().getColor(R.color.material_grey_600);
                    if (!ListenerUtil.mutListener.listen(2140)) {
                        if (contact != null) {
                            final Bitmap bitmap = contactService.getAvatar(contact, false);
                            if (!ListenerUtil.mutListener.listen(2139)) {
                                if (bitmap != null) {
                                    Palette palette = Palette.from(bitmap).generate();
                                    if (!ListenerUtil.mutListener.listen(2138)) {
                                        color = palette.getDarkVibrantColor(getResources().getColor(R.color.material_grey_600));
                                    }
                                }
                            }
                        }
                    }
                    @ColorInt
                    final int scrimColor = color;
                    if (!ListenerUtil.mutListener.listen(2145)) {
                        RuntimeUtil.runOnUiThread(new Runnable() {

                            @Override
                            public void run() {
                                if (!ListenerUtil.mutListener.listen(2144)) {
                                    if ((ListenerUtil.mutListener.listen(2141) ? (!isFinishing() || !isDestroyed()) : (!isFinishing() && !isDestroyed()))) {
                                        if (!ListenerUtil.mutListener.listen(2142)) {
                                            collapsingToolbar.setContentScrimColor(scrimColor);
                                        }
                                        if (!ListenerUtil.mutListener.listen(2143)) {
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

    private void reload() {
        if (!ListenerUtil.mutListener.listen(2147)) {
            this.contactTitle.setText(NameUtil.getDisplayNameOrNickname(contact, true));
        }
        if (!ListenerUtil.mutListener.listen(2148)) {
            setScrimColor();
        }
    }

    @Override
    public void onResume() {
        if (!ListenerUtil.mutListener.listen(2150)) {
            if (this.resumePauseHandler != null) {
                if (!ListenerUtil.mutListener.listen(2149)) {
                    this.resumePauseHandler.onResume();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(2151)) {
            super.onResume();
        }
    }

    private void removeContact() {
        GenericAlertDialog dialogFragment = GenericAlertDialog.newInstance(R.string.delete_contact_action, R.string.really_delete_contact, R.string.ok, R.string.cancel);
        if (!ListenerUtil.mutListener.listen(2152)) {
            dialogFragment.setData(contact);
        }
        if (!ListenerUtil.mutListener.listen(2153)) {
            dialogFragment.show(getSupportFragmentManager(), DIALOG_TAG_DELETE_CONTACT);
        }
    }

    private void removeContactConfirmed(final boolean addToExcludeList, final ContactModel contactModel) {
        if (!ListenerUtil.mutListener.listen(2162)) {
            new AsyncTask<Void, Void, Boolean>() {

                @Override
                protected void onPreExecute() {
                    if (!ListenerUtil.mutListener.listen(2154)) {
                        GenericProgressDialog.newInstance(R.string.deleting_contact, R.string.please_wait).show(getSupportFragmentManager(), DIALOG_TAG_DELETING_CONTACT);
                    }
                }

                @Override
                protected Boolean doInBackground(Void... params) {
                    if (!ListenerUtil.mutListener.listen(2157)) {
                        if (addToExcludeList) {
                            IdListService excludeFromSyncListService = ContactDetailActivity.this.serviceManager.getExcludedSyncIdentitiesService();
                            if (!ListenerUtil.mutListener.listen(2156)) {
                                if (excludeFromSyncListService != null) {
                                    if (!ListenerUtil.mutListener.listen(2155)) {
                                        excludeFromSyncListService.add(contactModel.getIdentity());
                                    }
                                }
                            }
                        }
                    }
                    return contactService.remove(contactModel);
                }

                @Override
                protected void onPostExecute(Boolean success) {
                    if (!ListenerUtil.mutListener.listen(2158)) {
                        DialogUtil.dismissDialog(getSupportFragmentManager(), DIALOG_TAG_DELETING_CONTACT, true);
                    }
                    if (!ListenerUtil.mutListener.listen(2161)) {
                        if (!success) {
                            if (!ListenerUtil.mutListener.listen(2160)) {
                                Toast.makeText(ContactDetailActivity.this, "Failed to remove contact", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(2159)) {
                                finishAndGoHome();
                            }
                        }
                    }
                }
            }.execute();
        }
    }

    private String getZeroLengthToNull(String v) {
        return (ListenerUtil.mutListener.listen(2168) ? (v == null && (ListenerUtil.mutListener.listen(2167) ? (v.length() >= 0) : (ListenerUtil.mutListener.listen(2166) ? (v.length() <= 0) : (ListenerUtil.mutListener.listen(2165) ? (v.length() > 0) : (ListenerUtil.mutListener.listen(2164) ? (v.length() < 0) : (ListenerUtil.mutListener.listen(2163) ? (v.length() != 0) : (v.length() == 0))))))) : (v == null || (ListenerUtil.mutListener.listen(2167) ? (v.length() >= 0) : (ListenerUtil.mutListener.listen(2166) ? (v.length() <= 0) : (ListenerUtil.mutListener.listen(2165) ? (v.length() > 0) : (ListenerUtil.mutListener.listen(2164) ? (v.length() < 0) : (ListenerUtil.mutListener.listen(2163) ? (v.length() != 0) : (v.length() == 0)))))))) ? null : v;
    }

    private void editName() {
        ContactEditDialog contactEditDialog = ContactEditDialog.newInstance(contact);
        if (!ListenerUtil.mutListener.listen(2169)) {
            contactEditDialog.show(getSupportFragmentManager(), DIALOG_TAG_EDIT);
        }
    }

    @SuppressLint("RestrictedApi")
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!ListenerUtil.mutListener.listen(2170)) {
            super.onCreateOptionsMenu(menu);
        }
        if (!ListenerUtil.mutListener.listen(2171)) {
            if (isFinishing()) {
                return false;
            }
        }
        if (!ListenerUtil.mutListener.listen(2172)) {
            getMenuInflater().inflate(R.menu.activity_contact_detail, menu);
        }
        try {
            MenuBuilder menuBuilder = (MenuBuilder) menu;
            if (!ListenerUtil.mutListener.listen(2173)) {
                menuBuilder.setOptionalIconsVisible(true);
            }
        } catch (Exception ignored) {
        }
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        if (!ListenerUtil.mutListener.listen(2174)) {
            if (isFinishing()) {
                return false;
            }
        }
        if (!ListenerUtil.mutListener.listen(2177)) {
            // display verification level in action bar
            if ((ListenerUtil.mutListener.listen(2175) ? (contact != null || contact.getVerificationLevel() != VerificationLevel.FULLY_VERIFIED) : (contact != null && contact.getVerificationLevel() != VerificationLevel.FULLY_VERIFIED))) {
                MenuItem menuItem = menu.findItem(R.id.action_scan_id);
                if (!ListenerUtil.mutListener.listen(2176)) {
                    menuItem.setVisible(true);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(2179)) {
            if (isReadonly) {
                if (!ListenerUtil.mutListener.listen(2178)) {
                    menu.findItem(R.id.action_send_message).setVisible(false);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(2180)) {
            this.blockMenuItem = menu.findItem(R.id.action_block_contact);
        }
        if (!ListenerUtil.mutListener.listen(2181)) {
            updateBlockMenu();
        }
        if (!ListenerUtil.mutListener.listen(2182)) {
            this.profilePicSendItem = menu.findItem(R.id.action_send_profilepic);
        }
        if (!ListenerUtil.mutListener.listen(2183)) {
            this.profilePicItem = menu.findItem(R.id.action_add_profilepic_recipient);
        }
        if (!ListenerUtil.mutListener.listen(2184)) {
            updateProfilepicMenu();
        }
        if (!ListenerUtil.mutListener.listen(2185)) {
            this.callItem = menu.findItem(R.id.menu_threema_call);
        }
        if (!ListenerUtil.mutListener.listen(2186)) {
            updateVoipCallMenuItem(null);
        }
        MenuItem galleryMenuItem = menu.findItem(R.id.menu_gallery);
        if (!ListenerUtil.mutListener.listen(2188)) {
            if (hiddenChatsListService.has(contactService.getUniqueIdString(contact))) {
                if (!ListenerUtil.mutListener.listen(2187)) {
                    galleryMenuItem.setVisible(false);
                }
            }
        }
        return super.onPrepareOptionsMenu(menu);
    }

    @UiThread
    private void updateVoipCallMenuItem(final Boolean newState) {
        if (!ListenerUtil.mutListener.listen(2194)) {
            if (callItem != null) {
                if (!ListenerUtil.mutListener.listen(2193)) {
                    if ((ListenerUtil.mutListener.listen(2189) ? (ContactUtil.canReceiveVoipMessages(contact, blackListIdentityService) || ConfigUtils.isCallsEnabled(ContactDetailActivity.this, preferenceService, licenseService)) : (ContactUtil.canReceiveVoipMessages(contact, blackListIdentityService) && ConfigUtils.isCallsEnabled(ContactDetailActivity.this, preferenceService, licenseService)))) {
                        if (!ListenerUtil.mutListener.listen(2191)) {
                            logger.debug("updateVoipMenu newState " + newState);
                        }
                        if (!ListenerUtil.mutListener.listen(2192)) {
                            callItem.setVisible(newState != null ? newState : voipStateService.getCallState().isIdle());
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(2190)) {
                            callItem.setVisible(false);
                        }
                    }
                }
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (!ListenerUtil.mutListener.listen(2219)) {
            switch(item.getItemId()) {
                case R.id.action_send_message:
                    if (!ListenerUtil.mutListener.listen(2200)) {
                        if (identity != null) {
                            Intent intent = new Intent(this, ComposeMessageActivity.class);
                            if (!ListenerUtil.mutListener.listen(2195)) {
                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            }
                            if (!ListenerUtil.mutListener.listen(2196)) {
                                intent.putExtra(ThreemaApplication.INTENT_DATA_CONTACT, identity);
                            }
                            if (!ListenerUtil.mutListener.listen(2197)) {
                                intent.putExtra(ThreemaApplication.INTENT_DATA_EDITFOCUS, Boolean.TRUE);
                            }
                            if (!ListenerUtil.mutListener.listen(2198)) {
                                startActivity(intent);
                            }
                            if (!ListenerUtil.mutListener.listen(2199)) {
                                finish();
                            }
                        }
                    }
                    break;
                case R.id.action_remove_contact:
                    if (!ListenerUtil.mutListener.listen(2201)) {
                        removeContact();
                    }
                    break;
                case R.id.action_scan_id:
                    if (!ListenerUtil.mutListener.listen(2203)) {
                        if (ConfigUtils.requestCameraPermissions(this, null, PERMISSION_REQUEST_CAMERA)) {
                            if (!ListenerUtil.mutListener.listen(2202)) {
                                scanQR();
                            }
                        }
                    }
                    break;
                case R.id.menu_threema_call:
                    if (!ListenerUtil.mutListener.listen(2204)) {
                        VoipUtil.initiateCall(this, contact, false, null);
                    }
                    break;
                case R.id.action_block_contact:
                    if (!ListenerUtil.mutListener.listen(2208)) {
                        if ((ListenerUtil.mutListener.listen(2205) ? (this.blackListIdentityService != null || this.blackListIdentityService.has(this.contact.getIdentity())) : (this.blackListIdentityService != null && this.blackListIdentityService.has(this.contact.getIdentity())))) {
                            if (!ListenerUtil.mutListener.listen(2207)) {
                                blockContact();
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(2206)) {
                                GenericAlertDialog.newInstance(R.string.block_contact, R.string.really_block_contact, R.string.yes, R.string.no).show(getSupportFragmentManager(), DIALOG_TAG_CONFIRM_BLOCK);
                            }
                        }
                    }
                    break;
                case R.id.action_share_contact:
                    if (!ListenerUtil.mutListener.listen(2209)) {
                        ShareUtil.shareContact(this, contact);
                    }
                    break;
                case R.id.menu_gallery:
                    if (!ListenerUtil.mutListener.listen(2212)) {
                        if (!hiddenChatsListService.has(contactService.getUniqueIdString(contact))) {
                            Intent mediaGalleryIntent = new Intent(this, MediaGalleryActivity.class);
                            if (!ListenerUtil.mutListener.listen(2210)) {
                                mediaGalleryIntent.putExtra(ThreemaApplication.INTENT_DATA_CONTACT, identity);
                            }
                            if (!ListenerUtil.mutListener.listen(2211)) {
                                startActivity(mediaGalleryIntent);
                            }
                        }
                    }
                    break;
                case R.id.action_add_profilepic_recipient:
                    if (!ListenerUtil.mutListener.listen(2215)) {
                        if (!profilePicRecipientsService.has(contact.getIdentity())) {
                            if (!ListenerUtil.mutListener.listen(2214)) {
                                profilePicRecipientsService.add(contact.getIdentity());
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(2213)) {
                                profilePicRecipientsService.remove(contact.getIdentity());
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(2216)) {
                        updateProfilepicMenu();
                    }
                    break;
                case R.id.action_send_profilepic:
                    if (!ListenerUtil.mutListener.listen(2217)) {
                        sendProfilePic();
                    }
                    break;
                default:
                    if (!ListenerUtil.mutListener.listen(2218)) {
                        finishUp();
                    }
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private void sendProfilePic() {
        if (!ListenerUtil.mutListener.listen(2220)) {
            contact.setProfilePicSentDate(new Date(0));
        }
        if (!ListenerUtil.mutListener.listen(2221)) {
            contactService.save(contact);
        }
        if (!ListenerUtil.mutListener.listen(2224)) {
            new AsyncTask<Void, Void, Boolean>() {

                @Override
                protected Boolean doInBackground(Void... params) {
                    MessageReceiver messageReceiver = contactService.createReceiver(contact);
                    return messageService.sendProfilePicture(new MessageReceiver[] { messageReceiver });
                }

                @Override
                protected void onPostExecute(Boolean aBoolean) {
                    if (!ListenerUtil.mutListener.listen(2223)) {
                        if (aBoolean) {
                            if (!ListenerUtil.mutListener.listen(2222)) {
                                Toast.makeText(ThreemaApplication.getAppContext(), R.string.profile_picture_sent, Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                }
            }.execute();
        }
    }

    private void blockContact() {
        if (!ListenerUtil.mutListener.listen(2226)) {
            if (this.blackListIdentityService != null) {
                if (!ListenerUtil.mutListener.listen(2225)) {
                    this.blackListIdentityService.toggle(this, this.contact);
                }
            }
        }
    }

    private void updateBlockMenu() {
        if (!ListenerUtil.mutListener.listen(2231)) {
            if (this.blockMenuItem != null) {
                if (!ListenerUtil.mutListener.listen(2230)) {
                    if ((ListenerUtil.mutListener.listen(2227) ? (blackListIdentityService != null || blackListIdentityService.has(contact.getIdentity())) : (blackListIdentityService != null && blackListIdentityService.has(contact.getIdentity())))) {
                        if (!ListenerUtil.mutListener.listen(2229)) {
                            blockMenuItem.setTitle(R.string.unblock_contact);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(2228)) {
                            blockMenuItem.setTitle(R.string.block_contact);
                        }
                    }
                }
            }
        }
    }

    private void updateProfilepicMenu() {
        if (!ListenerUtil.mutListener.listen(2253)) {
            if ((ListenerUtil.mutListener.listen(2232) ? (this.profilePicItem != null || this.profilePicSendItem != null) : (this.profilePicItem != null && this.profilePicSendItem != null))) {
                if (!ListenerUtil.mutListener.listen(2235)) {
                    if (isDisabledProfilePicReleaseSettings) {
                        if (!ListenerUtil.mutListener.listen(2233)) {
                            this.profilePicItem.setVisible(false);
                        }
                        if (!ListenerUtil.mutListener.listen(2234)) {
                            this.profilePicSendItem.setVisible(false);
                        }
                        return;
                    }
                }
                if (!ListenerUtil.mutListener.listen(2252)) {
                    switch(preferenceService.getProfilePicRelease()) {
                        case PreferenceService.PROFILEPIC_RELEASE_EVERYONE:
                            if (!ListenerUtil.mutListener.listen(2236)) {
                                this.profilePicItem.setVisible(false);
                            }
                            if (!ListenerUtil.mutListener.listen(2237)) {
                                this.profilePicSendItem.setVisible(ContactUtil.canReceiveProfilePics(contact));
                            }
                            break;
                        case PreferenceService.PROFILEPIC_RELEASE_SOME:
                            if (!ListenerUtil.mutListener.listen(2249)) {
                                if (ContactUtil.canReceiveProfilePics(contact)) {
                                    if (!ListenerUtil.mutListener.listen(2247)) {
                                        if ((ListenerUtil.mutListener.listen(2240) ? (profilePicRecipientsService != null || profilePicRecipientsService.has(contact.getIdentity())) : (profilePicRecipientsService != null && profilePicRecipientsService.has(contact.getIdentity())))) {
                                            if (!ListenerUtil.mutListener.listen(2244)) {
                                                profilePicItem.setTitle(R.string.menu_send_profilpic_off);
                                            }
                                            if (!ListenerUtil.mutListener.listen(2245)) {
                                                profilePicItem.setIcon(R.drawable.ic_person_remove_outline);
                                            }
                                            if (!ListenerUtil.mutListener.listen(2246)) {
                                                profilePicSendItem.setVisible(true);
                                            }
                                        } else {
                                            if (!ListenerUtil.mutListener.listen(2241)) {
                                                profilePicItem.setTitle(R.string.menu_send_profilpic);
                                            }
                                            if (!ListenerUtil.mutListener.listen(2242)) {
                                                profilePicItem.setIcon(R.drawable.ic_person_add_outline);
                                            }
                                            if (!ListenerUtil.mutListener.listen(2243)) {
                                                profilePicSendItem.setVisible(false);
                                            }
                                        }
                                    }
                                    if (!ListenerUtil.mutListener.listen(2248)) {
                                        this.profilePicItem.setVisible(true);
                                    }
                                } else {
                                    if (!ListenerUtil.mutListener.listen(2238)) {
                                        this.profilePicSendItem.setVisible(false);
                                    }
                                    if (!ListenerUtil.mutListener.listen(2239)) {
                                        this.profilePicItem.setVisible(false);
                                    }
                                }
                            }
                            break;
                        case PreferenceService.PROFILEPIC_RELEASE_NOBODY:
                            if (!ListenerUtil.mutListener.listen(2250)) {
                                this.profilePicItem.setVisible(false);
                            }
                            if (!ListenerUtil.mutListener.listen(2251)) {
                                this.profilePicSendItem.setVisible(false);
                            }
                            break;
                        default:
                            break;
                    }
                }
            }
        }
    }

    private void scanQR() {
        if (!ListenerUtil.mutListener.listen(2254)) {
            QRScannerUtil.getInstance().initiateScan(this, false, null);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (!ListenerUtil.mutListener.listen(2255)) {
            super.onActivityResult(requestCode, resultCode, intent);
        }
        try {
            Fragment fragment = getSupportFragmentManager().findFragmentByTag(DIALOG_TAG_EDIT);
            if (!ListenerUtil.mutListener.listen(2258)) {
                if ((ListenerUtil.mutListener.listen(2256) ? (fragment != null || fragment.isAdded()) : (fragment != null && fragment.isAdded()))) {
                    if (!ListenerUtil.mutListener.listen(2257)) {
                        fragment.onActivityResult(requestCode, resultCode, intent);
                    }
                }
            }
        } catch (Exception e) {
        }
        if (!ListenerUtil.mutListener.listen(2270)) {
            switch(requestCode) {
                case ACTIVITY_ID_GROUP_DETAIL:
                    if (!ListenerUtil.mutListener.listen(2259)) {
                        // contacts may have been edited
                        this.groupList = this.groupService.getGroupsByIdentity(this.identity);
                    }
                    if (!ListenerUtil.mutListener.listen(2260)) {
                        contactDetailRecyclerView.setAdapter(setupAdapter());
                    }
                    break;
                case REQUEST_CODE_QR_SCANNER:
                    QRCodeService.QRCodeContentResult qrRes = QRScannerUtil.getInstance().parseActivityResult(this, requestCode, resultCode, intent, this.serviceManager.getQRCodeService());
                    if (!ListenerUtil.mutListener.listen(2267)) {
                        if (qrRes != null) {
                            if (!ListenerUtil.mutListener.listen(2263)) {
                                if ((ListenerUtil.mutListener.listen(2261) ? (qrRes.getExpirationDate() != null || qrRes.getExpirationDate().before(new Date())) : (qrRes.getExpirationDate() != null && qrRes.getExpirationDate().before(new Date())))) {
                                    if (!ListenerUtil.mutListener.listen(2262)) {
                                        SimpleStringAlertDialog.newInstance(R.string.title_adduser, getString(R.string.expired_barcode)).show(getSupportFragmentManager(), "expiredId");
                                    }
                                    return;
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(2265)) {
                                if (!TestUtil.compare(identity, qrRes.getIdentity())) {
                                    if (!ListenerUtil.mutListener.listen(2264)) {
                                        SimpleStringAlertDialog.newInstance(R.string.scan_id_mismatch_title, getString(R.string.scan_id_mismatch_message)).show(getSupportFragmentManager(), "scanId");
                                    }
                                    return;
                                }
                            }
                            int contactVerification = this.contactService.updateContactVerification(identity, qrRes.getPublicKey());
                            int txt;
                            switch(contactVerification) {
                                case ContactService.ContactVerificationResult_ALREADY_VERIFIED:
                                    txt = R.string.scan_duplicate;
                                    break;
                                case ContactService.ContactVerificationResult_VERIFIED:
                                    txt = R.string.scan_successful;
                                    break;
                                default:
                                    txt = R.string.id_mismatch;
                            }
                            if (!ListenerUtil.mutListener.listen(2266)) {
                                SimpleStringAlertDialog.newInstance(R.string.scan_id, getString(txt)).show(getSupportFragmentManager(), "scanId");
                            }
                        }
                    }
                    break;
                default:
                    if (!ListenerUtil.mutListener.listen(2269)) {
                        if (this.avatarEditView != null) {
                            if (!ListenerUtil.mutListener.listen(2268)) {
                                this.avatarEditView.onActivityResult(requestCode, resultCode, intent);
                            }
                        }
                    }
                    break;
            }
        }
    }

    void deleteContact(ContactModel contactModel) {
        IdListService excludeFromSyncListService = this.serviceManager.getExcludedSyncIdentitiesService();
        if (!ListenerUtil.mutListener.listen(2276)) {
            // second question, if the contact is a synced contact
            if ((ListenerUtil.mutListener.listen(2272) ? ((ListenerUtil.mutListener.listen(2271) ? (ContactUtil.isSynchronized(contactModel) || excludeFromSyncListService != null) : (ContactUtil.isSynchronized(contactModel) && excludeFromSyncListService != null)) || !excludeFromSyncListService.has(contactModel.getIdentity())) : ((ListenerUtil.mutListener.listen(2271) ? (ContactUtil.isSynchronized(contactModel) || excludeFromSyncListService != null) : (ContactUtil.isSynchronized(contactModel) && excludeFromSyncListService != null)) && !excludeFromSyncListService.has(contactModel.getIdentity())))) {
                GenericAlertDialog dialogFragment = GenericAlertDialog.newInstance(R.string.delete_contact_action, R.string.want_to_add_to_exclude_list, R.string.yes, R.string.no);
                if (!ListenerUtil.mutListener.listen(2274)) {
                    dialogFragment.setData(contact);
                }
                if (!ListenerUtil.mutListener.listen(2275)) {
                    dialogFragment.show(getSupportFragmentManager(), DIALOG_TAG_EXCLUDE_CONTACT);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(2273)) {
                    removeContactConfirmed(false, contactModel);
                }
            }
        }
    }

    void unhideContact(ContactModel contactModel) {
        if (!ListenerUtil.mutListener.listen(2277)) {
            contactService.setIsHidden(contactModel.getIdentity(), false);
        }
        if (!ListenerUtil.mutListener.listen(2278)) {
            onCreateLocal();
        }
        if (!ListenerUtil.mutListener.listen(2279)) {
            reload();
        }
    }

    @Override
    public void onYes(String tag, Object data) {
        if (!ListenerUtil.mutListener.listen(2284)) {
            switch(tag) {
                case DIALOG_TAG_DELETE_CONTACT:
                    if (!ListenerUtil.mutListener.listen(2280)) {
                        deleteContact((ContactModel) data);
                    }
                    break;
                case DIALOG_TAG_EXCLUDE_CONTACT:
                    if (!ListenerUtil.mutListener.listen(2281)) {
                        removeContactConfirmed(true, (ContactModel) data);
                    }
                    break;
                case DIALOG_TAG_ADD_CONTACT:
                    if (!ListenerUtil.mutListener.listen(2282)) {
                        unhideContact(this.contact);
                    }
                    break;
                case DIALOG_TAG_CONFIRM_BLOCK:
                    if (!ListenerUtil.mutListener.listen(2283)) {
                        blockContact();
                    }
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public void onNo(String tag, Object data) {
        if (!ListenerUtil.mutListener.listen(2287)) {
            switch(tag) {
                case DIALOG_TAG_EXCLUDE_CONTACT:
                    if (!ListenerUtil.mutListener.listen(2285)) {
                        removeContactConfirmed(false, (ContactModel) data);
                    }
                    break;
                case DIALOG_TAG_ADD_CONTACT:
                    if (!ListenerUtil.mutListener.listen(2286)) {
                        finish();
                    }
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public void onYes(String tag, String text1, String text2, File croppedAvatarFile) {
        String firstName = this.getZeroLengthToNull(text1);
        String lastName = this.getZeroLengthToNull(text2);
        String existingFirstName = this.getZeroLengthToNull(contact.getFirstName());
        String existingLastName = this.getZeroLengthToNull(contact.getLastName());
        if (!ListenerUtil.mutListener.listen(2290)) {
            if ((ListenerUtil.mutListener.listen(2288) ? (!TestUtil.compare(firstName, existingFirstName) && !TestUtil.compare(lastName, existingLastName)) : (!TestUtil.compare(firstName, existingFirstName) || !TestUtil.compare(lastName, existingLastName)))) {
                if (!ListenerUtil.mutListener.listen(2289)) {
                    // only save contact stuff if the name has changed!
                    this.contactService.setName(this.contact, firstName, lastName);
                }
            }
        }
    }

    @Override
    public void onNo(String tag) {
    }

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (!ListenerUtil.mutListener.listen(2291)) {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
        if (!ListenerUtil.mutListener.listen(2306)) {
            if ((ListenerUtil.mutListener.listen(2296) ? (requestCode >= PERMISSION_REQUEST_CAMERA) : (ListenerUtil.mutListener.listen(2295) ? (requestCode <= PERMISSION_REQUEST_CAMERA) : (ListenerUtil.mutListener.listen(2294) ? (requestCode > PERMISSION_REQUEST_CAMERA) : (ListenerUtil.mutListener.listen(2293) ? (requestCode < PERMISSION_REQUEST_CAMERA) : (ListenerUtil.mutListener.listen(2292) ? (requestCode != PERMISSION_REQUEST_CAMERA) : (requestCode == PERMISSION_REQUEST_CAMERA))))))) {
                if (!ListenerUtil.mutListener.listen(2305)) {
                    if ((ListenerUtil.mutListener.listen(2302) ? ((ListenerUtil.mutListener.listen(2301) ? (grantResults.length >= 0) : (ListenerUtil.mutListener.listen(2300) ? (grantResults.length <= 0) : (ListenerUtil.mutListener.listen(2299) ? (grantResults.length < 0) : (ListenerUtil.mutListener.listen(2298) ? (grantResults.length != 0) : (ListenerUtil.mutListener.listen(2297) ? (grantResults.length == 0) : (grantResults.length > 0)))))) || grantResults[0] == PackageManager.PERMISSION_GRANTED) : ((ListenerUtil.mutListener.listen(2301) ? (grantResults.length >= 0) : (ListenerUtil.mutListener.listen(2300) ? (grantResults.length <= 0) : (ListenerUtil.mutListener.listen(2299) ? (grantResults.length < 0) : (ListenerUtil.mutListener.listen(2298) ? (grantResults.length != 0) : (ListenerUtil.mutListener.listen(2297) ? (grantResults.length == 0) : (grantResults.length > 0)))))) && grantResults[0] == PackageManager.PERMISSION_GRANTED))) {
                        if (!ListenerUtil.mutListener.listen(2304)) {
                            scanQR();
                        }
                    } else if (!shouldShowRequestPermissionRationale(Manifest.permission.CAMERA)) {
                        if (!ListenerUtil.mutListener.listen(2303)) {
                            ConfigUtils.showPermissionRationale(this, findViewById(R.id.main_content), R.string.permission_camera_qr_required);
                        }
                    }
                }
            }
        }
    }

    @Override
    public void onBackPressed() {
        if (!ListenerUtil.mutListener.listen(2307)) {
            finishUp();
        }
    }

    private void finishUp() {
        if (!ListenerUtil.mutListener.listen(2308)) {
            finish();
        }
    }

    private void finishAndGoHome() {
        if (!ListenerUtil.mutListener.listen(2310)) {
            if ((ListenerUtil.mutListener.listen(2309) ? (isFinishing() && isDestroyed()) : (isFinishing() || isDestroyed()))) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(2311)) {
            navigateUpTo(new Intent(this, HomeActivity.class));
        }
        if (!ListenerUtil.mutListener.listen(2312)) {
            overridePendingTransition(R.anim.fast_fade_in, R.anim.fast_fade_out);
        }
    }
}
