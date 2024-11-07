/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
 * Copyright (c) 2014-2021 Threema GmbH
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
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.SparseBooleanArray;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.google.android.material.snackbar.Snackbar;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import ch.threema.app.R;
import ch.threema.app.ThreemaApplication;
import ch.threema.app.adapters.MediaGalleryAdapter;
import ch.threema.app.adapters.MediaGallerySpinnerAdapter;
import ch.threema.app.cache.ThumbnailCache;
import ch.threema.app.dialogs.CancelableHorizontalProgressDialog;
import ch.threema.app.dialogs.GenericAlertDialog;
import ch.threema.app.managers.ServiceManager;
import ch.threema.app.messagereceiver.MessageReceiver;
import ch.threema.app.services.ContactService;
import ch.threema.app.services.DistributionListService;
import ch.threema.app.services.FileService;
import ch.threema.app.services.GroupService;
import ch.threema.app.services.MessageService;
import ch.threema.app.ui.EmptyView;
import ch.threema.app.ui.FastScrollGridView;
import ch.threema.app.utils.AnimationUtil;
import ch.threema.app.utils.AppRestrictionUtil;
import ch.threema.app.utils.ConfigUtils;
import ch.threema.app.utils.DialogUtil;
import ch.threema.app.utils.FileUtil;
import ch.threema.app.utils.IntentDataUtil;
import ch.threema.app.utils.LocaleUtil;
import ch.threema.app.utils.LogUtil;
import ch.threema.app.utils.NameUtil;
import ch.threema.app.utils.RuntimeUtil;
import ch.threema.app.utils.TestUtil;
import ch.threema.storage.models.AbstractMessageModel;
import ch.threema.storage.models.ContactModel;
import ch.threema.storage.models.DistributionListModel;
import ch.threema.storage.models.GroupModel;
import ch.threema.storage.models.MessageType;
import ch.threema.storage.models.data.MessageContentsType;
import static ch.threema.app.fragments.ComposeMessageFragment.SCROLLBUTTON_VIEW_TIMEOUT;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class MediaGalleryActivity extends ThreemaToolbarActivity implements AdapterView.OnItemClickListener, ActionBar.OnNavigationListener, GenericAlertDialog.DialogClickListener, FastScrollGridView.ScrollListener {

    private static final Logger logger = LoggerFactory.getLogger(MediaGalleryActivity.class);

    private ThumbnailCache<?> thumbnailCache = null;

    private MediaGalleryAdapter mediaGalleryAdapter;

    private MessageReceiver messageReceiver;

    private String actionBarTitle;

    private SpinnerMessageFilter spinnerMessageFilter;

    private MediaGallerySpinnerAdapter spinnerAdapter;

    private List<AbstractMessageModel> values;

    private FastScrollGridView gridView;

    private EmptyView emptyView;

    private TypedArray mediaTypeArray;

    private int currentType;

    private ActionMode actionMode = null;

    private AbstractMessageModel initialMessageModel = null;

    private TextView dateTextView;

    private FrameLayout dateView;

    public FileService fileService;

    public MessageService messageService;

    public ContactService contactService;

    public GroupService groupService;

    public DistributionListService distributionListService;

    private final Handler dateViewHandler = new Handler();

    private final Runnable dateViewTask = () -> RuntimeUtil.runOnUiThread(() -> {
        if (dateView != null && dateView.getVisibility() == View.VISIBLE) {
            AnimationUtil.slideOutAnimation(dateView, false, 1f, null);
        }
    });

    private static final int TYPE_ALL = 0;

    private static final int TYPE_IMAGE = 1;

    private static final int TYPE_VIDEO = 2;

    private static final int TYPE_AUDIO = 3;

    private static final int TYPE_FILE = 4;

    private static final String DELETE_MESSAGES_CONFIRM_TAG = "reallydelete";

    private static final String DIALOG_TAG_DELETING_MEDIA = "dmm";

    private static final int PERMISSION_REQUEST_SAVE_MESSAGE = 88;

    private static class SpinnerMessageFilter implements MessageService.MessageFilter {

        @MessageContentsType
        private int[] filter = null;

        public void setFilterByType(int spinnerMessageType) {
            if (!ListenerUtil.mutListener.listen(4317)) {
                switch(spinnerMessageType) {
                    case TYPE_ALL:
                        if (!ListenerUtil.mutListener.listen(4312)) {
                            this.filter = new int[] { MessageContentsType.IMAGE, MessageContentsType.VIDEO, MessageContentsType.AUDIO, MessageContentsType.FILE, MessageContentsType.GIF, MessageContentsType.VOICE_MESSAGE };
                        }
                        break;
                    case TYPE_IMAGE:
                        if (!ListenerUtil.mutListener.listen(4313)) {
                            this.filter = new int[] { MessageContentsType.IMAGE };
                        }
                        break;
                    case TYPE_VIDEO:
                        if (!ListenerUtil.mutListener.listen(4314)) {
                            this.filter = new int[] { MessageContentsType.VIDEO, MessageContentsType.GIF };
                        }
                        break;
                    case TYPE_AUDIO:
                        if (!ListenerUtil.mutListener.listen(4315)) {
                            this.filter = new int[] { MessageContentsType.AUDIO, MessageContentsType.VOICE_MESSAGE };
                        }
                        break;
                    case TYPE_FILE:
                        if (!ListenerUtil.mutListener.listen(4316)) {
                            this.filter = new int[] { MessageContentsType.FILE };
                        }
                        break;
                    default:
                        break;
                }
            }
        }

        @Override
        public long getPageSize() {
            return 0;
        }

        @Override
        public Integer getPageReferenceId() {
            return null;
        }

        @Override
        public boolean withStatusMessages() {
            return false;
        }

        @Override
        public boolean withUnsaved() {
            return false;
        }

        @Override
        public boolean onlyUnread() {
            return false;
        }

        @Override
        public boolean onlyDownloaded() {
            return true;
        }

        @Override
        public MessageType[] types() {
            return null;
        }

        @Override
        @MessageContentsType
        public int[] contentTypes() {
            return this.filter;
        }
    }

    @Override
    public int getLayoutResource() {
        return R.layout.activity_media_gallery;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(4318)) {
            super.onCreate(savedInstanceState);
        }
    }

    @Override
    protected boolean initActivity(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(4319)) {
            logger.debug("initActivity");
        }
        if (!ListenerUtil.mutListener.listen(4320)) {
            // set font size according to user preferences
            getTheme().applyStyle(preferenceService.getFontStyle(), true);
        }
        if (!ListenerUtil.mutListener.listen(4321)) {
            if (!super.initActivity(savedInstanceState)) {
                return false;
            }
        }
        if (!ListenerUtil.mutListener.listen(4323)) {
            if (!this.requiredInstances()) {
                if (!ListenerUtil.mutListener.listen(4322)) {
                    this.finish();
                }
                return false;
            }
        }
        if (!ListenerUtil.mutListener.listen(4324)) {
            currentType = TYPE_ALL;
        }
        if (!ListenerUtil.mutListener.listen(4325)) {
            this.gridView = findViewById(R.id.gridview);
        }
        if (!ListenerUtil.mutListener.listen(4326)) {
            this.gridView.setChoiceMode(AbsListView.CHOICE_MODE_MULTIPLE_MODAL);
        }
        if (!ListenerUtil.mutListener.listen(4351)) {
            this.gridView.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {

                @Override
                public void onItemCheckedStateChanged(android.view.ActionMode mode, int position, long id, boolean checked) {
                    final int count = gridView.getCheckedItemCount();
                    if (!ListenerUtil.mutListener.listen(4333)) {
                        if ((ListenerUtil.mutListener.listen(4331) ? (count >= 0) : (ListenerUtil.mutListener.listen(4330) ? (count <= 0) : (ListenerUtil.mutListener.listen(4329) ? (count < 0) : (ListenerUtil.mutListener.listen(4328) ? (count != 0) : (ListenerUtil.mutListener.listen(4327) ? (count == 0) : (count > 0))))))) {
                            if (!ListenerUtil.mutListener.listen(4332)) {
                                mode.setTitle(Integer.toString(count));
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(4340)) {
                        if (actionMode != null) {
                            if (!ListenerUtil.mutListener.listen(4339)) {
                                actionMode.getMenu().findItem(R.id.menu_show_in_chat).setVisible((ListenerUtil.mutListener.listen(4338) ? (count >= 1) : (ListenerUtil.mutListener.listen(4337) ? (count <= 1) : (ListenerUtil.mutListener.listen(4336) ? (count > 1) : (ListenerUtil.mutListener.listen(4335) ? (count < 1) : (ListenerUtil.mutListener.listen(4334) ? (count != 1) : (count == 1)))))));
                            }
                        }
                    }
                }

                @Override
                public boolean onCreateActionMode(android.view.ActionMode mode, Menu menu) {
                    if (!ListenerUtil.mutListener.listen(4341)) {
                        mode.getMenuInflater().inflate(R.menu.action_media_gallery, menu);
                    }
                    if (!ListenerUtil.mutListener.listen(4342)) {
                        actionMode = mode;
                    }
                    if (!ListenerUtil.mutListener.listen(4343)) {
                        ConfigUtils.themeMenu(menu, ConfigUtils.getColorFromAttribute(MediaGalleryActivity.this, R.attr.colorAccent));
                    }
                    if (!ListenerUtil.mutListener.listen(4345)) {
                        if (AppRestrictionUtil.isShareMediaDisabled(MediaGalleryActivity.this)) {
                            if (!ListenerUtil.mutListener.listen(4344)) {
                                menu.findItem(R.id.menu_message_save).setVisible(false);
                            }
                        }
                    }
                    return true;
                }

                @Override
                public boolean onPrepareActionMode(android.view.ActionMode mode, Menu menu) {
                    if (!ListenerUtil.mutListener.listen(4346)) {
                        mode.setTitle(Integer.toString(gridView.getCheckedItemCount()));
                    }
                    return false;
                }

                @Override
                public boolean onActionItemClicked(android.view.ActionMode mode, MenuItem item) {
                    switch(item.getItemId()) {
                        case R.id.menu_message_discard:
                            if (!ListenerUtil.mutListener.listen(4347)) {
                                discardMessages();
                            }
                            return true;
                        case R.id.menu_message_save:
                            if (!ListenerUtil.mutListener.listen(4348)) {
                                saveMessages();
                            }
                            return true;
                        case R.id.menu_show_in_chat:
                            if (!ListenerUtil.mutListener.listen(4349)) {
                                showInChat();
                            }
                            return true;
                        default:
                            return false;
                    }
                }

                @Override
                public void onDestroyActionMode(android.view.ActionMode mode) {
                    if (!ListenerUtil.mutListener.listen(4350)) {
                        actionMode = null;
                    }
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(4352)) {
            this.gridView.setOnItemClickListener(this);
        }
        if (!ListenerUtil.mutListener.listen(4353)) {
            this.gridView.setNumColumns(ConfigUtils.isLandscape(this) ? 5 : 3);
        }
        if (!ListenerUtil.mutListener.listen(4354)) {
            this.gridView.setScrollListener(this);
        }
        if (!ListenerUtil.mutListener.listen(4355)) {
            processIntent(getIntent());
        }
        ActionBar actionBar = getSupportActionBar();
        if (!ListenerUtil.mutListener.listen(4358)) {
            if (actionBar == null) {
                if (!ListenerUtil.mutListener.listen(4356)) {
                    logger.debug("no action bar");
                }
                if (!ListenerUtil.mutListener.listen(4357)) {
                    finish();
                }
                return false;
            }
        }
        if (!ListenerUtil.mutListener.listen(4359)) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        if (!ListenerUtil.mutListener.listen(4360)) {
            actionBar.setDisplayShowTitleEnabled(false);
        }
        if (!ListenerUtil.mutListener.listen(4361)) {
            // add text view if contact list is empty
            this.mediaTypeArray = getResources().obtainTypedArray(R.array.media_gallery_spinner);
        }
        if (!ListenerUtil.mutListener.listen(4362)) {
            this.spinnerAdapter = new MediaGallerySpinnerAdapter(actionBar.getThemedContext(), getResources().getStringArray(R.array.media_gallery_spinner), this.actionBarTitle);
        }
        if (!ListenerUtil.mutListener.listen(4363)) {
            actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
        }
        if (!ListenerUtil.mutListener.listen(4364)) {
            actionBar.setListNavigationCallbacks(spinnerAdapter, this);
        }
        if (!ListenerUtil.mutListener.listen(4365)) {
            actionBar.setSelectedNavigationItem(this.currentType);
        }
        if (!ListenerUtil.mutListener.listen(4366)) {
            this.spinnerMessageFilter = new SpinnerMessageFilter();
        }
        if (!ListenerUtil.mutListener.listen(4367)) {
            this.spinnerMessageFilter.setFilterByType(this.currentType);
        }
        if (!ListenerUtil.mutListener.listen(4368)) {
            this.thumbnailCache = new ThumbnailCache<Integer>(null);
        }
        FrameLayout frameLayout = findViewById(R.id.frame_parent);
        if (!ListenerUtil.mutListener.listen(4369)) {
            this.emptyView = new EmptyView(this);
        }
        if (!ListenerUtil.mutListener.listen(4370)) {
            this.emptyView.setColorsInt(ConfigUtils.getColorFromAttribute(this, android.R.attr.windowBackground), ConfigUtils.getColorFromAttribute(this, R.attr.textColorPrimary));
        }
        if (!ListenerUtil.mutListener.listen(4371)) {
            this.emptyView.setup(getString(R.string.no_media_found_generic));
        }
        if (!ListenerUtil.mutListener.listen(4372)) {
            frameLayout.addView(this.emptyView);
        }
        if (!ListenerUtil.mutListener.listen(4373)) {
            this.gridView.setEmptyView(this.emptyView);
        }
        if (!ListenerUtil.mutListener.listen(4374)) {
            this.dateView = findViewById(R.id.date_separator_container);
        }
        if (!ListenerUtil.mutListener.listen(4375)) {
            this.dateTextView = findViewById(R.id.text_view);
        }
        if (!ListenerUtil.mutListener.listen(4378)) {
            if ((ListenerUtil.mutListener.listen(4376) ? (savedInstanceState == null && mediaGalleryAdapter == null) : (savedInstanceState == null || mediaGalleryAdapter == null))) {
                if (!ListenerUtil.mutListener.listen(4377)) {
                    setupAdapters(this.currentType, true);
                }
            }
        }
        return true;
    }

    private void showInChat() {
        if (!ListenerUtil.mutListener.listen(4379)) {
            if (getSelectedMessages().size() != 1) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(4380)) {
            AnimationUtil.startActivityForResult(this, null, IntentDataUtil.getJumpToMessageIntent(this, getSelectedMessages().get(0)), ThreemaActivity.ACTIVITY_ID_COMPOSE_MESSAGE);
        }
        if (!ListenerUtil.mutListener.listen(4381)) {
            finish();
        }
    }

    @Override
    protected void onDestroy() {
        if (!ListenerUtil.mutListener.listen(4383)) {
            if (this.thumbnailCache != null) {
                if (!ListenerUtil.mutListener.listen(4382)) {
                    this.thumbnailCache.flush();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(4384)) {
            super.onDestroy();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!ListenerUtil.mutListener.listen(4385)) {
            super.onCreateOptionsMenu(menu);
        }
        if (!ListenerUtil.mutListener.listen(4386)) {
            // Inflate the menu; this adds items to the action bar if it is present.
            getMenuInflater().inflate(R.menu.activity_media_gallery, menu);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (!ListenerUtil.mutListener.listen(4389)) {
            switch(item.getItemId()) {
                case android.R.id.home:
                    if (!ListenerUtil.mutListener.listen(4387)) {
                        finish();
                    }
                    break;
                case R.id.menu_message_select_all:
                    if (!ListenerUtil.mutListener.listen(4388)) {
                        selectAllMessages();
                    }
                    break;
            }
        }
        return true;
    }

    private void processIntent(Intent intent) {
        if (!ListenerUtil.mutListener.listen(4399)) {
            if (intent.hasExtra(ThreemaApplication.INTENT_DATA_GROUP)) {
                int groupId = intent.getIntExtra(ThreemaApplication.INTENT_DATA_GROUP, 0);
                GroupModel groupModel = this.groupService.getById(groupId);
                if (!ListenerUtil.mutListener.listen(4397)) {
                    messageReceiver = this.groupService.createReceiver(groupModel);
                }
                if (!ListenerUtil.mutListener.listen(4398)) {
                    actionBarTitle = groupModel.getName();
                }
            } else if (intent.hasExtra(ThreemaApplication.INTENT_DATA_DISTRIBUTION_LIST)) {
                DistributionListModel distributionListModel = distributionListService.getById(intent.getIntExtra(ThreemaApplication.INTENT_DATA_DISTRIBUTION_LIST, 0));
                try {
                    if (!ListenerUtil.mutListener.listen(4395)) {
                        messageReceiver = distributionListService.createReceiver(distributionListModel);
                    }
                } catch (Exception e) {
                    if (!ListenerUtil.mutListener.listen(4394)) {
                        logger.error("Exception", e);
                    }
                }
                if (!ListenerUtil.mutListener.listen(4396)) {
                    actionBarTitle = distributionListModel.getName();
                }
            } else {
                String identity = intent.getStringExtra(ThreemaApplication.INTENT_DATA_CONTACT);
                if (!ListenerUtil.mutListener.listen(4391)) {
                    if (identity == null) {
                        if (!ListenerUtil.mutListener.listen(4390)) {
                            finish();
                        }
                    }
                }
                ContactModel contactModel = this.contactService.getByIdentity(identity);
                if (!ListenerUtil.mutListener.listen(4392)) {
                    messageReceiver = this.contactService.createReceiver(contactModel);
                }
                if (!ListenerUtil.mutListener.listen(4393)) {
                    actionBarTitle = NameUtil.getDisplayNameOrNickname(contactModel, true);
                }
            }
        }
        String type = IntentDataUtil.getAbstractMessageType(intent);
        int id = IntentDataUtil.getAbstractMessageId(intent);
        if (!ListenerUtil.mutListener.listen(4407)) {
            if ((ListenerUtil.mutListener.listen(4405) ? (type != null || (ListenerUtil.mutListener.listen(4404) ? (id >= 0) : (ListenerUtil.mutListener.listen(4403) ? (id <= 0) : (ListenerUtil.mutListener.listen(4402) ? (id > 0) : (ListenerUtil.mutListener.listen(4401) ? (id < 0) : (ListenerUtil.mutListener.listen(4400) ? (id == 0) : (id != 0))))))) : (type != null && (ListenerUtil.mutListener.listen(4404) ? (id >= 0) : (ListenerUtil.mutListener.listen(4403) ? (id <= 0) : (ListenerUtil.mutListener.listen(4402) ? (id > 0) : (ListenerUtil.mutListener.listen(4401) ? (id < 0) : (ListenerUtil.mutListener.listen(4400) ? (id == 0) : (id != 0))))))))) {
                if (!ListenerUtil.mutListener.listen(4406)) {
                    initialMessageModel = messageService.getMessageModelFromId(id, type);
                }
            }
        }
    }

    private void setupAdapters(int newType, boolean force) {
        if (!ListenerUtil.mutListener.listen(4439)) {
            if ((ListenerUtil.mutListener.listen(4413) ? ((ListenerUtil.mutListener.listen(4412) ? (this.currentType >= newType) : (ListenerUtil.mutListener.listen(4411) ? (this.currentType <= newType) : (ListenerUtil.mutListener.listen(4410) ? (this.currentType > newType) : (ListenerUtil.mutListener.listen(4409) ? (this.currentType < newType) : (ListenerUtil.mutListener.listen(4408) ? (this.currentType == newType) : (this.currentType != newType)))))) && force) : ((ListenerUtil.mutListener.listen(4412) ? (this.currentType >= newType) : (ListenerUtil.mutListener.listen(4411) ? (this.currentType <= newType) : (ListenerUtil.mutListener.listen(4410) ? (this.currentType > newType) : (ListenerUtil.mutListener.listen(4409) ? (this.currentType < newType) : (ListenerUtil.mutListener.listen(4408) ? (this.currentType == newType) : (this.currentType != newType)))))) || force))) {
                if (!ListenerUtil.mutListener.listen(4414)) {
                    this.values = this.getMessages(this.messageReceiver);
                }
                if (!ListenerUtil.mutListener.listen(4425)) {
                    if ((ListenerUtil.mutListener.listen(4415) ? (this.values == null && this.values.isEmpty()) : (this.values == null || this.values.isEmpty()))) {
                        if (!ListenerUtil.mutListener.listen(4424)) {
                            if (this.emptyView != null) {
                                if (!ListenerUtil.mutListener.listen(4423)) {
                                    if ((ListenerUtil.mutListener.listen(4420) ? (newType >= TYPE_ALL) : (ListenerUtil.mutListener.listen(4419) ? (newType <= TYPE_ALL) : (ListenerUtil.mutListener.listen(4418) ? (newType > TYPE_ALL) : (ListenerUtil.mutListener.listen(4417) ? (newType < TYPE_ALL) : (ListenerUtil.mutListener.listen(4416) ? (newType != TYPE_ALL) : (newType == TYPE_ALL))))))) {
                                        if (!ListenerUtil.mutListener.listen(4422)) {
                                            this.emptyView.setup(getString(R.string.no_media_found_generic));
                                        }
                                    } else {
                                        if (!ListenerUtil.mutListener.listen(4421)) {
                                            this.emptyView.setup(String.format(getString(R.string.no_media_found), getString(this.mediaTypeArray.getResourceId(newType, -1))));
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(4426)) {
                    this.mediaGalleryAdapter = new MediaGalleryAdapter(this, values, this.fileService, this.thumbnailCache);
                }
                if (!ListenerUtil.mutListener.listen(4427)) {
                    this.gridView.setAdapter(this.mediaGalleryAdapter);
                }
                if (!ListenerUtil.mutListener.listen(4438)) {
                    if (initialMessageModel != null) {
                        if (!ListenerUtil.mutListener.listen(4437)) {
                            this.gridView.post(new Runnable() {

                                @Override
                                public void run() {
                                    if (!ListenerUtil.mutListener.listen(4435)) {
                                        {
                                            long _loopCounter26 = 0;
                                            for (int position = 0; (ListenerUtil.mutListener.listen(4434) ? (position >= values.size()) : (ListenerUtil.mutListener.listen(4433) ? (position <= values.size()) : (ListenerUtil.mutListener.listen(4432) ? (position > values.size()) : (ListenerUtil.mutListener.listen(4431) ? (position != values.size()) : (ListenerUtil.mutListener.listen(4430) ? (position == values.size()) : (position < values.size())))))); position++) {
                                                ListenerUtil.loopListener.listen("_loopCounter26", ++_loopCounter26);
                                                if (!ListenerUtil.mutListener.listen(4429)) {
                                                    if (values.get(position).getId() == initialMessageModel.getId()) {
                                                        if (!ListenerUtil.mutListener.listen(4428)) {
                                                            gridView.setSelection(position);
                                                        }
                                                        break;
                                                    }
                                                }
                                            }
                                        }
                                    }
                                    if (!ListenerUtil.mutListener.listen(4436)) {
                                        initialMessageModel = null;
                                    }
                                }
                            });
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(4440)) {
            this.currentType = newType;
        }
        if (!ListenerUtil.mutListener.listen(4441)) {
            resetSpinnerAdapter(newType);
        }
    }

    private void resetSpinnerAdapter(int type) {
        if (!ListenerUtil.mutListener.listen(4446)) {
            if ((ListenerUtil.mutListener.listen(4443) ? ((ListenerUtil.mutListener.listen(4442) ? (this.spinnerAdapter != null || this.mediaTypeArray != null) : (this.spinnerAdapter != null && this.mediaTypeArray != null)) || this.values != null) : ((ListenerUtil.mutListener.listen(4442) ? (this.spinnerAdapter != null || this.mediaTypeArray != null) : (this.spinnerAdapter != null && this.mediaTypeArray != null)) && this.values != null))) {
                if (!ListenerUtil.mutListener.listen(4444)) {
                    this.spinnerAdapter.setSubtitle(getString(this.mediaTypeArray.getResourceId(type, -1)) + " (" + this.values.size() + ")");
                }
                if (!ListenerUtil.mutListener.listen(4445)) {
                    this.spinnerAdapter.notifyDataSetChanged();
                }
            }
        }
    }

    private List<AbstractMessageModel> getMessages(MessageReceiver<AbstractMessageModel> receiver) {
        List<AbstractMessageModel> values = null;
        try {
            if (!ListenerUtil.mutListener.listen(4448)) {
                values = receiver.loadMessages(this.spinnerMessageFilter);
            }
        } catch (SQLException e) {
            if (!ListenerUtil.mutListener.listen(4447)) {
                logger.error("Exception", e);
            }
        }
        return values;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
        final AbstractMessageModel m = this.mediaGalleryAdapter.getItem(position);
        ProgressBar progressBar = view.findViewById(R.id.progress_decoding);
        if (!ListenerUtil.mutListener.listen(4458)) {
            switch(mediaGalleryAdapter.getItemViewType(position)) {
                case MediaGalleryAdapter.TYPE_IMAGE:
                    if (!ListenerUtil.mutListener.listen(4449)) {
                        // internal viewer
                        showInMediaFragment(m, view);
                    }
                    break;
                case MediaGalleryAdapter.TYPE_VIDEO:
                    if (!ListenerUtil.mutListener.listen(4450)) {
                        showInMediaFragment(m, view);
                    }
                    break;
                case MediaGalleryAdapter.TYPE_AUDIO:
                    if (!ListenerUtil.mutListener.listen(4451)) {
                        showInMediaFragment(m, view);
                    }
                    break;
                case MediaGalleryAdapter.TYPE_FILE:
                    if (!ListenerUtil.mutListener.listen(4457)) {
                        if ((ListenerUtil.mutListener.listen(4454) ? (m != null || ((ListenerUtil.mutListener.listen(4453) ? ((ListenerUtil.mutListener.listen(4452) ? (FileUtil.isImageFile(m.getFileData()) && FileUtil.isVideoFile(m.getFileData())) : (FileUtil.isImageFile(m.getFileData()) || FileUtil.isVideoFile(m.getFileData()))) && FileUtil.isAudioFile(m.getFileData())) : ((ListenerUtil.mutListener.listen(4452) ? (FileUtil.isImageFile(m.getFileData()) && FileUtil.isVideoFile(m.getFileData())) : (FileUtil.isImageFile(m.getFileData()) || FileUtil.isVideoFile(m.getFileData()))) || FileUtil.isAudioFile(m.getFileData()))))) : (m != null && ((ListenerUtil.mutListener.listen(4453) ? ((ListenerUtil.mutListener.listen(4452) ? (FileUtil.isImageFile(m.getFileData()) && FileUtil.isVideoFile(m.getFileData())) : (FileUtil.isImageFile(m.getFileData()) || FileUtil.isVideoFile(m.getFileData()))) && FileUtil.isAudioFile(m.getFileData())) : ((ListenerUtil.mutListener.listen(4452) ? (FileUtil.isImageFile(m.getFileData()) && FileUtil.isVideoFile(m.getFileData())) : (FileUtil.isImageFile(m.getFileData()) || FileUtil.isVideoFile(m.getFileData()))) || FileUtil.isAudioFile(m.getFileData()))))))) {
                            if (!ListenerUtil.mutListener.listen(4456)) {
                                showInMediaFragment(m, view);
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(4455)) {
                                decodeAndShowFile(m, view, progressBar);
                            }
                        }
                    }
                    break;
            }
        }
    }

    @Override
    public boolean onNavigationItemSelected(int itemPosition, long itemId) {
        if (!ListenerUtil.mutListener.listen(4459)) {
            this.spinnerMessageFilter.setFilterByType(itemPosition);
        }
        if (!ListenerUtil.mutListener.listen(4460)) {
            setupAdapters(itemPosition, false);
        }
        return true;
    }

    @Override
    public void onScroll(int firstVisibleItem) {
        if (!ListenerUtil.mutListener.listen(4475)) {
            if (this.mediaGalleryAdapter != null) {
                if (!ListenerUtil.mutListener.listen(4469)) {
                    if ((ListenerUtil.mutListener.listen(4467) ? ((ListenerUtil.mutListener.listen(4461) ? (dateView.getVisibility() != View.VISIBLE || mediaGalleryAdapter != null) : (dateView.getVisibility() != View.VISIBLE && mediaGalleryAdapter != null)) || (ListenerUtil.mutListener.listen(4466) ? (mediaGalleryAdapter.getCount() >= 0) : (ListenerUtil.mutListener.listen(4465) ? (mediaGalleryAdapter.getCount() <= 0) : (ListenerUtil.mutListener.listen(4464) ? (mediaGalleryAdapter.getCount() < 0) : (ListenerUtil.mutListener.listen(4463) ? (mediaGalleryAdapter.getCount() != 0) : (ListenerUtil.mutListener.listen(4462) ? (mediaGalleryAdapter.getCount() == 0) : (mediaGalleryAdapter.getCount() > 0))))))) : ((ListenerUtil.mutListener.listen(4461) ? (dateView.getVisibility() != View.VISIBLE || mediaGalleryAdapter != null) : (dateView.getVisibility() != View.VISIBLE && mediaGalleryAdapter != null)) && (ListenerUtil.mutListener.listen(4466) ? (mediaGalleryAdapter.getCount() >= 0) : (ListenerUtil.mutListener.listen(4465) ? (mediaGalleryAdapter.getCount() <= 0) : (ListenerUtil.mutListener.listen(4464) ? (mediaGalleryAdapter.getCount() < 0) : (ListenerUtil.mutListener.listen(4463) ? (mediaGalleryAdapter.getCount() != 0) : (ListenerUtil.mutListener.listen(4462) ? (mediaGalleryAdapter.getCount() == 0) : (mediaGalleryAdapter.getCount() > 0))))))))) {
                        if (!ListenerUtil.mutListener.listen(4468)) {
                            AnimationUtil.slideInAnimation(dateView, false, 200);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(4470)) {
                    dateViewHandler.removeCallbacks(dateViewTask);
                }
                if (!ListenerUtil.mutListener.listen(4471)) {
                    dateViewHandler.postDelayed(dateViewTask, SCROLLBUTTON_VIEW_TIMEOUT);
                }
                final AbstractMessageModel messageModel = this.mediaGalleryAdapter.getItem(firstVisibleItem);
                if (!ListenerUtil.mutListener.listen(4474)) {
                    if (messageModel != null) {
                        final Date createdAt = messageModel.getCreatedAt();
                        if (!ListenerUtil.mutListener.listen(4473)) {
                            if (createdAt != null) {
                                if (!ListenerUtil.mutListener.listen(4472)) {
                                    dateView.post(() -> {
                                        dateTextView.setText(LocaleUtil.formatDateRelative(this, createdAt.getTime()));
                                    });
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private void selectAllMessages() {
        if (!ListenerUtil.mutListener.listen(4495)) {
            if (gridView != null) {
                if (!ListenerUtil.mutListener.listen(4494)) {
                    if (gridView.getCount() == gridView.getCheckedItemCount()) {
                        if (!ListenerUtil.mutListener.listen(4493)) {
                            if (actionMode != null) {
                                if (!ListenerUtil.mutListener.listen(4492)) {
                                    actionMode.finish();
                                }
                            }
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(4489)) {
                            {
                                long _loopCounter27 = 0;
                                for (int i = 0; (ListenerUtil.mutListener.listen(4488) ? (i >= gridView.getCount()) : (ListenerUtil.mutListener.listen(4487) ? (i <= gridView.getCount()) : (ListenerUtil.mutListener.listen(4486) ? (i > gridView.getCount()) : (ListenerUtil.mutListener.listen(4485) ? (i != gridView.getCount()) : (ListenerUtil.mutListener.listen(4484) ? (i == gridView.getCount()) : (i < gridView.getCount())))))); i++) {
                                    ListenerUtil.loopListener.listen("_loopCounter27", ++_loopCounter27);
                                    if (!ListenerUtil.mutListener.listen(4483)) {
                                        if ((ListenerUtil.mutListener.listen(4481) ? ((ListenerUtil.mutListener.listen(4480) ? (currentType >= TYPE_ALL) : (ListenerUtil.mutListener.listen(4479) ? (currentType <= TYPE_ALL) : (ListenerUtil.mutListener.listen(4478) ? (currentType > TYPE_ALL) : (ListenerUtil.mutListener.listen(4477) ? (currentType < TYPE_ALL) : (ListenerUtil.mutListener.listen(4476) ? (currentType != TYPE_ALL) : (currentType == TYPE_ALL)))))) && mediaGalleryAdapter.getItemViewType(i) == currentType) : ((ListenerUtil.mutListener.listen(4480) ? (currentType >= TYPE_ALL) : (ListenerUtil.mutListener.listen(4479) ? (currentType <= TYPE_ALL) : (ListenerUtil.mutListener.listen(4478) ? (currentType > TYPE_ALL) : (ListenerUtil.mutListener.listen(4477) ? (currentType < TYPE_ALL) : (ListenerUtil.mutListener.listen(4476) ? (currentType != TYPE_ALL) : (currentType == TYPE_ALL)))))) || mediaGalleryAdapter.getItemViewType(i) == currentType))) {
                                            if (!ListenerUtil.mutListener.listen(4482)) {
                                                gridView.setItemChecked(i, true);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(4491)) {
                            if (actionMode != null) {
                                if (!ListenerUtil.mutListener.listen(4490)) {
                                    actionMode.invalidate();
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private void discardMessages() {
        List<AbstractMessageModel> selectedMessages = getSelectedMessages();
        GenericAlertDialog dialog = GenericAlertDialog.newInstance(R.string.really_delete_message_title, String.format(getString(R.string.really_delete_media), selectedMessages.size()), R.string.delete_message, R.string.cancel);
        if (!ListenerUtil.mutListener.listen(4496)) {
            dialog.setData(selectedMessages);
        }
        if (!ListenerUtil.mutListener.listen(4497)) {
            dialog.show(getSupportFragmentManager(), DELETE_MESSAGES_CONFIRM_TAG);
        }
    }

    private void saveMessages() {
        if (!ListenerUtil.mutListener.listen(4500)) {
            if (ConfigUtils.requestStoragePermissions(this, null, PERMISSION_REQUEST_SAVE_MESSAGE)) {
                if (!ListenerUtil.mutListener.listen(4498)) {
                    fileService.saveMedia(this, gridView, new CopyOnWriteArrayList<>(getSelectedMessages()), true);
                }
                if (!ListenerUtil.mutListener.listen(4499)) {
                    actionMode.finish();
                }
            }
        }
    }

    private List<AbstractMessageModel> getSelectedMessages() {
        List<AbstractMessageModel> selectedMessages = new ArrayList<>();
        SparseBooleanArray checkedItems = gridView.getCheckedItemPositions();
        final int size = checkedItems.size();
        if (!ListenerUtil.mutListener.listen(4508)) {
            {
                long _loopCounter28 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(4507) ? (i >= size) : (ListenerUtil.mutListener.listen(4506) ? (i <= size) : (ListenerUtil.mutListener.listen(4505) ? (i > size) : (ListenerUtil.mutListener.listen(4504) ? (i != size) : (ListenerUtil.mutListener.listen(4503) ? (i == size) : (i < size)))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter28", ++_loopCounter28);
                    final int index = checkedItems.keyAt(i);
                    if (!ListenerUtil.mutListener.listen(4502)) {
                        if (checkedItems.valueAt(i)) {
                            if (!ListenerUtil.mutListener.listen(4501)) {
                                selectedMessages.add(mediaGalleryAdapter.getItem(index));
                            }
                        }
                    }
                }
            }
        }
        return selectedMessages;
    }

    @SuppressLint("StaticFieldLeak")
    private void reallyDiscardMessages(final CopyOnWriteArrayList<AbstractMessageModel> selectedMessages) {
        if (!ListenerUtil.mutListener.listen(4532)) {
            new AsyncTask<Void, Integer, Void>() {

                boolean cancelled = false;

                @Override
                protected void onPreExecute() {
                    if (!ListenerUtil.mutListener.listen(4517)) {
                        if ((ListenerUtil.mutListener.listen(4513) ? (selectedMessages.size() >= 10) : (ListenerUtil.mutListener.listen(4512) ? (selectedMessages.size() <= 10) : (ListenerUtil.mutListener.listen(4511) ? (selectedMessages.size() < 10) : (ListenerUtil.mutListener.listen(4510) ? (selectedMessages.size() != 10) : (ListenerUtil.mutListener.listen(4509) ? (selectedMessages.size() == 10) : (selectedMessages.size() > 10))))))) {
                            CancelableHorizontalProgressDialog dialog = CancelableHorizontalProgressDialog.newInstance(R.string.deleting_messages, 0, R.string.cancel, selectedMessages.size());
                            if (!ListenerUtil.mutListener.listen(4515)) {
                                dialog.setOnCancelListener(new DialogInterface.OnClickListener() {

                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        if (!ListenerUtil.mutListener.listen(4514)) {
                                            cancelled = true;
                                        }
                                    }
                                });
                            }
                            if (!ListenerUtil.mutListener.listen(4516)) {
                                dialog.show(getSupportFragmentManager(), DIALOG_TAG_DELETING_MEDIA);
                            }
                        }
                    }
                }

                @Override
                protected Void doInBackground(Void... params) {
                    int i = 0;
                    Iterator<AbstractMessageModel> checkedItemsIterator = selectedMessages.iterator();
                    if (!ListenerUtil.mutListener.listen(4525)) {
                        {
                            long _loopCounter29 = 0;
                            while ((ListenerUtil.mutListener.listen(4524) ? (checkedItemsIterator.hasNext() || !cancelled) : (checkedItemsIterator.hasNext() && !cancelled))) {
                                ListenerUtil.loopListener.listen("_loopCounter29", ++_loopCounter29);
                                if (!ListenerUtil.mutListener.listen(4518)) {
                                    publishProgress(i++);
                                }
                                try {
                                    final AbstractMessageModel messageModel = checkedItemsIterator.next();
                                    if (!ListenerUtil.mutListener.listen(4523)) {
                                        if (messageModel != null) {
                                            if (!ListenerUtil.mutListener.listen(4520)) {
                                                messageService.remove(messageModel);
                                            }
                                            if (!ListenerUtil.mutListener.listen(4522)) {
                                                RuntimeUtil.runOnUiThread(new Runnable() {

                                                    @Override
                                                    public void run() {
                                                        if (!ListenerUtil.mutListener.listen(4521)) {
                                                            mediaGalleryAdapter.remove(messageModel);
                                                        }
                                                    }
                                                });
                                            }
                                        }
                                    }
                                } catch (Exception e) {
                                    if (!ListenerUtil.mutListener.listen(4519)) {
                                        logger.error("Exception", e);
                                    }
                                }
                            }
                        }
                    }
                    return null;
                }

                @Override
                protected void onPostExecute(Void result) {
                    if (!ListenerUtil.mutListener.listen(4526)) {
                        DialogUtil.dismissDialog(getSupportFragmentManager(), DIALOG_TAG_DELETING_MEDIA, true);
                    }
                    if (!ListenerUtil.mutListener.listen(4527)) {
                        Snackbar.make(gridView, R.string.message_deleted, Snackbar.LENGTH_LONG).show();
                    }
                    if (!ListenerUtil.mutListener.listen(4529)) {
                        if (actionMode != null) {
                            if (!ListenerUtil.mutListener.listen(4528)) {
                                actionMode.finish();
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(4530)) {
                        resetSpinnerAdapter(currentType);
                    }
                }

                @Override
                protected void onProgressUpdate(Integer... index) {
                    if (!ListenerUtil.mutListener.listen(4531)) {
                        DialogUtil.updateProgress(getSupportFragmentManager(), DIALOG_TAG_DELETING_MEDIA, index[0] + 1);
                    }
                }
            }.execute();
        }
    }

    @Override
    public void onYes(String tag, Object data) {
        if (!ListenerUtil.mutListener.listen(4533)) {
            reallyDiscardMessages(new CopyOnWriteArrayList<>((ArrayList<AbstractMessageModel>) data));
        }
    }

    @Override
    public void onNo(String tag, Object data) {
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        final int topmost;
        if (this.gridView != null) {
            View topChild = this.gridView.getChildAt(0);
            if (topChild != null) {
                if ((ListenerUtil.mutListener.listen(4538) ? (topChild.getTop() >= 0) : (ListenerUtil.mutListener.listen(4537) ? (topChild.getTop() <= 0) : (ListenerUtil.mutListener.listen(4536) ? (topChild.getTop() > 0) : (ListenerUtil.mutListener.listen(4535) ? (topChild.getTop() != 0) : (ListenerUtil.mutListener.listen(4534) ? (topChild.getTop() == 0) : (topChild.getTop() < 0))))))) {
                    topmost = this.gridView.getFirstVisiblePosition() + 1;
                } else {
                    topmost = this.gridView.getFirstVisiblePosition();
                }
            } else {
                topmost = 0;
            }
        } else {
            topmost = 0;
        }
        if (!ListenerUtil.mutListener.listen(4539)) {
            super.onConfigurationChanged(newConfig);
        }
        if (!ListenerUtil.mutListener.listen(4541)) {
            if (this.gridView != null) {
                if (!ListenerUtil.mutListener.listen(4540)) {
                    this.gridView.post(() -> {
                        gridView.setNumColumns(ConfigUtils.isLandscape(MediaGalleryActivity.this) ? 5 : 3);
                        gridView.setSelection(topmost);
                    });
                }
            }
        }
    }

    private void hideProgressBar(final ProgressBar progressBar) {
        if (!ListenerUtil.mutListener.listen(4543)) {
            if (progressBar != null) {
                if (!ListenerUtil.mutListener.listen(4542)) {
                    RuntimeUtil.runOnUiThread(() -> progressBar.setVisibility(View.GONE));
                }
            }
        }
    }

    private void showProgressBar(final ProgressBar progressBar) {
        if (!ListenerUtil.mutListener.listen(4545)) {
            if (progressBar != null) {
                if (!ListenerUtil.mutListener.listen(4544)) {
                    RuntimeUtil.runOnUiThread(() -> progressBar.setVisibility(View.VISIBLE));
                }
            }
        }
    }

    public void decodeAndShowFile(final AbstractMessageModel m, final View v, final ProgressBar progressBar) {
        if (!ListenerUtil.mutListener.listen(4546)) {
            showProgressBar(progressBar);
        }
        if (!ListenerUtil.mutListener.listen(4552)) {
            fileService.loadDecryptedMessageFile(m, new FileService.OnDecryptedFileComplete() {

                @Override
                public void complete(File decodedFile) {
                    if (!ListenerUtil.mutListener.listen(4547)) {
                        hideProgressBar(progressBar);
                    }
                    if (!ListenerUtil.mutListener.listen(4548)) {
                        messageService.viewMediaMessage(getApplicationContext(), m, fileService.getShareFileUri(decodedFile, null));
                    }
                }

                @Override
                public void error(String message) {
                    if (!ListenerUtil.mutListener.listen(4549)) {
                        hideProgressBar(progressBar);
                    }
                    if (!ListenerUtil.mutListener.listen(4551)) {
                        if (!TestUtil.empty(message)) {
                            if (!ListenerUtil.mutListener.listen(4550)) {
                                logger.error(message, MediaGalleryActivity.this);
                            }
                        }
                    }
                }
            });
        }
    }

    public void showInMediaFragment(final AbstractMessageModel m, final View v) {
        Intent intent = new Intent(this, MediaViewerActivity.class);
        if (!ListenerUtil.mutListener.listen(4553)) {
            IntentDataUtil.append(m, intent);
        }
        if (!ListenerUtil.mutListener.listen(4554)) {
            intent.putExtra(MediaViewerActivity.EXTRA_ID_IMMEDIATE_PLAY, true);
        }
        if (!ListenerUtil.mutListener.listen(4555)) {
            intent.putExtra(MediaViewerActivity.EXTRA_ID_REVERSE_ORDER, false);
        }
        if (!ListenerUtil.mutListener.listen(4556)) {
            AnimationUtil.startActivityForResult(this, v, intent, ACTIVITY_ID_MEDIA_VIEWER);
        }
    }

    @Override
    protected boolean checkInstances() {
        return (ListenerUtil.mutListener.listen(4557) ? (TestUtil.required(this.fileService, this.messageService, this.groupService, this.distributionListService, this.contactService) || super.checkInstances()) : (TestUtil.required(this.fileService, this.messageService, this.groupService, this.distributionListService, this.contactService) && super.checkInstances()));
    }

    @Override
    protected void instantiate() {
        if (!ListenerUtil.mutListener.listen(4558)) {
            super.instantiate();
        }
        ServiceManager serviceManager = ThreemaApplication.getServiceManager();
        if (!ListenerUtil.mutListener.listen(4565)) {
            if (serviceManager != null) {
                try {
                    if (!ListenerUtil.mutListener.listen(4560)) {
                        this.fileService = serviceManager.getFileService();
                    }
                    if (!ListenerUtil.mutListener.listen(4561)) {
                        this.messageService = serviceManager.getMessageService();
                    }
                    if (!ListenerUtil.mutListener.listen(4562)) {
                        this.groupService = serviceManager.getGroupService();
                    }
                    if (!ListenerUtil.mutListener.listen(4563)) {
                        this.distributionListService = serviceManager.getDistributionListService();
                    }
                    if (!ListenerUtil.mutListener.listen(4564)) {
                        this.contactService = serviceManager.getContactService();
                    }
                } catch (Exception e) {
                    if (!ListenerUtil.mutListener.listen(4559)) {
                        LogUtil.exception(e, this);
                    }
                }
            }
        }
    }

    @Override
    @TargetApi(23)
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (!ListenerUtil.mutListener.listen(4566)) {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
        if (!ListenerUtil.mutListener.listen(4578)) {
            if ((ListenerUtil.mutListener.listen(4572) ? ((ListenerUtil.mutListener.listen(4571) ? (grantResults.length >= 0) : (ListenerUtil.mutListener.listen(4570) ? (grantResults.length <= 0) : (ListenerUtil.mutListener.listen(4569) ? (grantResults.length < 0) : (ListenerUtil.mutListener.listen(4568) ? (grantResults.length != 0) : (ListenerUtil.mutListener.listen(4567) ? (grantResults.length == 0) : (grantResults.length > 0)))))) || grantResults[0] == PackageManager.PERMISSION_GRANTED) : ((ListenerUtil.mutListener.listen(4571) ? (grantResults.length >= 0) : (ListenerUtil.mutListener.listen(4570) ? (grantResults.length <= 0) : (ListenerUtil.mutListener.listen(4569) ? (grantResults.length < 0) : (ListenerUtil.mutListener.listen(4568) ? (grantResults.length != 0) : (ListenerUtil.mutListener.listen(4567) ? (grantResults.length == 0) : (grantResults.length > 0)))))) && grantResults[0] == PackageManager.PERMISSION_GRANTED))) {
                if (!ListenerUtil.mutListener.listen(4577)) {
                    switch(requestCode) {
                        case PERMISSION_REQUEST_SAVE_MESSAGE:
                            if (!ListenerUtil.mutListener.listen(4576)) {
                                fileService.saveMedia(this, gridView, new CopyOnWriteArrayList<>(getSelectedMessages()), true);
                            }
                            break;
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(4575)) {
                    switch(requestCode) {
                        case PERMISSION_REQUEST_SAVE_MESSAGE:
                            if (!ListenerUtil.mutListener.listen(4574)) {
                                if (!shouldShowRequestPermissionRationale(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                                    if (!ListenerUtil.mutListener.listen(4573)) {
                                        ConfigUtils.showPermissionRationale(this, gridView, R.string.permission_storage_required);
                                    }
                                }
                            }
                            break;
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(4579)) {
            actionMode.finish();
        }
    }
}
