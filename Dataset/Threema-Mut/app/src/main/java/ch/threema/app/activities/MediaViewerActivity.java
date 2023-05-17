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
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.util.SparseArray;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.core.app.ActivityCompat;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.PagerAdapter;
import ch.threema.app.R;
import ch.threema.app.ThreemaApplication;
import ch.threema.app.dialogs.ExpandableTextEntryDialog;
import ch.threema.app.emojis.EmojiMarkupUtil;
import ch.threema.app.fragments.mediaviews.AudioViewFragment;
import ch.threema.app.fragments.mediaviews.FileViewFragment;
import ch.threema.app.fragments.mediaviews.ImageViewFragment;
import ch.threema.app.fragments.mediaviews.MediaPlayerViewFragment;
import ch.threema.app.fragments.mediaviews.MediaViewFragment;
import ch.threema.app.fragments.mediaviews.VideoViewFragment;
import ch.threema.app.messagereceiver.MessageReceiver;
import ch.threema.app.services.ContactService;
import ch.threema.app.services.FileService;
import ch.threema.app.services.MessageService;
import ch.threema.app.ui.LockableViewPager;
import ch.threema.app.utils.AnimationUtil;
import ch.threema.app.utils.AppRestrictionUtil;
import ch.threema.app.utils.ConfigUtils;
import ch.threema.app.utils.FileUtil;
import ch.threema.app.utils.IconUtil;
import ch.threema.app.utils.IntentDataUtil;
import ch.threema.app.utils.MessageUtil;
import ch.threema.app.utils.MimeUtil;
import ch.threema.app.utils.NameUtil;
import ch.threema.app.utils.RuntimeUtil;
import ch.threema.app.utils.TestUtil;
import ch.threema.base.ThreemaException;
import ch.threema.storage.models.AbstractMessageModel;
import ch.threema.storage.models.DistributionListMessageModel;
import ch.threema.storage.models.GroupMessageModel;
import ch.threema.storage.models.MessageType;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class MediaViewerActivity extends ThreemaToolbarActivity implements ExpandableTextEntryDialog.ExpandableTextEntryDialogClickListener {

    private static final Logger logger = LoggerFactory.getLogger(MediaViewerActivity.class);

    private static final int PERMISSION_REQUEST_SAVE_MESSAGE = 1;

    private static final long LOADING_DELAY = 600;

    public static final int ACTIONBAR_TIMEOUT = 4000;

    public static final String EXTRA_ID_IMMEDIATE_PLAY = "play";

    public static final String EXTRA_ID_REVERSE_ORDER = "reverse";

    private LockableViewPager pager;

    private File currentMediaFile;

    private ActionBar actionBar;

    private AbstractMessageModel currentMessageModel;

    private MessageReceiver currentReceiver;

    private FileService fileService;

    private MessageService messageService;

    private ContactService contactService;

    private EmojiMarkupUtil emojiMarkupUtil;

    private List<AbstractMessageModel> messageModels;

    private int currentPosition = -1;

    private MediaViewFragment[] fragments;

    private File[] decryptedFileCache;

    private View captionContainer;

    private TextView caption;

    private final Handler loadingFragmentHandler = new Handler();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(4580)) {
            super.onCreate(savedInstanceState);
        }
    }

    @Override
    protected boolean initActivity(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(4581)) {
            logger.debug("initActivity");
        }
        if (!ListenerUtil.mutListener.listen(4582)) {
            showSystemUi();
        }
        if (!ListenerUtil.mutListener.listen(4584)) {
            if (!super.initActivity(savedInstanceState)) {
                if (!ListenerUtil.mutListener.listen(4583)) {
                    finish();
                }
                return false;
            }
        }
        if (!ListenerUtil.mutListener.listen(4586)) {
            if (!this.requiredInstances()) {
                if (!ListenerUtil.mutListener.listen(4585)) {
                    finish();
                }
                return false;
            }
        }
        Intent intent = getIntent();
        String t = IntentDataUtil.getAbstractMessageType(intent);
        int i = IntentDataUtil.getAbstractMessageId(intent);
        if (!ListenerUtil.mutListener.listen(4594)) {
            if ((ListenerUtil.mutListener.listen(4592) ? (TestUtil.empty(t) && (ListenerUtil.mutListener.listen(4591) ? (i >= 0) : (ListenerUtil.mutListener.listen(4590) ? (i > 0) : (ListenerUtil.mutListener.listen(4589) ? (i < 0) : (ListenerUtil.mutListener.listen(4588) ? (i != 0) : (ListenerUtil.mutListener.listen(4587) ? (i == 0) : (i <= 0))))))) : (TestUtil.empty(t) || (ListenerUtil.mutListener.listen(4591) ? (i >= 0) : (ListenerUtil.mutListener.listen(4590) ? (i > 0) : (ListenerUtil.mutListener.listen(4589) ? (i < 0) : (ListenerUtil.mutListener.listen(4588) ? (i != 0) : (ListenerUtil.mutListener.listen(4587) ? (i == 0) : (i <= 0))))))))) {
                if (!ListenerUtil.mutListener.listen(4593)) {
                    finish();
                }
                return false;
            }
        }
        if (!ListenerUtil.mutListener.listen(4595)) {
            this.emojiMarkupUtil = EmojiMarkupUtil.getInstance();
        }
        if (!ListenerUtil.mutListener.listen(4596)) {
            this.actionBar = getSupportActionBar();
        }
        if (!ListenerUtil.mutListener.listen(4598)) {
            if (this.actionBar == null) {
                if (!ListenerUtil.mutListener.listen(4597)) {
                    finish();
                }
                return false;
            }
        }
        if (!ListenerUtil.mutListener.listen(4599)) {
            this.actionBar.setDisplayHomeAsUpEnabled(true);
        }
        if (!ListenerUtil.mutListener.listen(4600)) {
            this.actionBar.setTitle(" ");
        }
        if (!ListenerUtil.mutListener.listen(4601)) {
            ViewCompat.setOnApplyWindowInsetsListener(getToolbar(), (v, insets) -> {
                FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) v.getLayoutParams();
                lp.topMargin = insets.getSystemWindowInsetTop();
                lp.leftMargin = insets.getSystemWindowInsetLeft();
                lp.rightMargin = insets.getSystemWindowInsetRight();
                v.setLayoutParams(lp);
                return insets;
            });
        }
        if (!ListenerUtil.mutListener.listen(4602)) {
            getToolbar().setTitleTextAppearance(this, R.style.TextAppearance_MediaViewer_Title);
        }
        if (!ListenerUtil.mutListener.listen(4603)) {
            getToolbar().setSubtitleTextAppearance(this, R.style.TextAppearance_MediaViewer_SubTitle);
        }
        if (!ListenerUtil.mutListener.listen(4604)) {
            adjustStatusBar();
        }
        if (!ListenerUtil.mutListener.listen(4605)) {
            this.caption = findViewById(R.id.caption);
        }
        if (!ListenerUtil.mutListener.listen(4606)) {
            this.captionContainer = findViewById(R.id.caption_container);
        }
        if (!ListenerUtil.mutListener.listen(4607)) {
            ViewCompat.setOnApplyWindowInsetsListener(this.captionContainer, (v, insets) -> {
                FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) v.getLayoutParams();
                params.setMargins(insets.getSystemWindowInsetLeft(), 0, insets.getSystemWindowInsetRight(), insets.getSystemWindowInsetBottom() + getResources().getDimensionPixelSize(R.dimen.mediaviewer_caption_border_bottom));
                v.setLayoutParams(params);
                return insets;
            });
        }
        if (!ListenerUtil.mutListener.listen(4608)) {
            setCaptionPosition();
        }
        if (!ListenerUtil.mutListener.listen(4609)) {
            this.currentMessageModel = IntentDataUtil.getAbstractMessageModel(intent, messageService);
        }
        try {
            if (!ListenerUtil.mutListener.listen(4612)) {
                this.currentReceiver = messageService.getMessageReceiver(this.currentMessageModel);
            }
        } catch (ThreemaException e) {
            if (!ListenerUtil.mutListener.listen(4610)) {
                logger.error("Exception", e);
            }
            if (!ListenerUtil.mutListener.listen(4611)) {
                finish();
            }
            return false;
        }
        if (!ListenerUtil.mutListener.listen(4614)) {
            if (!TestUtil.required(this.currentMessageModel, this.currentReceiver)) {
                if (!ListenerUtil.mutListener.listen(4613)) {
                    finish();
                }
                return false;
            }
        }
        // load all records of receiver to support list pager
        try {
            if (!ListenerUtil.mutListener.listen(4617)) {
                this.messageModels = this.currentReceiver.loadMessages(new MessageService.MessageFilter() {

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
                        return new MessageType[] { MessageType.IMAGE, MessageType.VIDEO, MessageType.FILE, MessageType.VOICEMESSAGE };
                    }

                    @Override
                    public int[] contentTypes() {
                        return null;
                    }
                });
            }
        } catch (Exception x) {
            if (!ListenerUtil.mutListener.listen(4615)) {
                logger.error("Exception", x);
            }
            if (!ListenerUtil.mutListener.listen(4616)) {
                finish();
            }
            return false;
        }
        if (!ListenerUtil.mutListener.listen(4639)) {
            if (intent.getBooleanExtra(EXTRA_ID_REVERSE_ORDER, false)) {
                if (!ListenerUtil.mutListener.listen(4626)) {
                    // reverse order
                    Collections.reverse(messageModels);
                }
                if (!ListenerUtil.mutListener.listen(4638)) {
                    {
                        long _loopCounter31 = 0;
                        for (int n = (ListenerUtil.mutListener.listen(4637) ? (messageModels.size() % 1) : (ListenerUtil.mutListener.listen(4636) ? (messageModels.size() / 1) : (ListenerUtil.mutListener.listen(4635) ? (messageModels.size() * 1) : (ListenerUtil.mutListener.listen(4634) ? (messageModels.size() + 1) : (messageModels.size() - 1))))); (ListenerUtil.mutListener.listen(4633) ? (n <= 0) : (ListenerUtil.mutListener.listen(4632) ? (n > 0) : (ListenerUtil.mutListener.listen(4631) ? (n < 0) : (ListenerUtil.mutListener.listen(4630) ? (n != 0) : (ListenerUtil.mutListener.listen(4629) ? (n == 0) : (n >= 0)))))); n--) {
                            ListenerUtil.loopListener.listen("_loopCounter31", ++_loopCounter31);
                            if (!ListenerUtil.mutListener.listen(4628)) {
                                if (this.messageModels.get(n).getId() == this.currentMessageModel.getId()) {
                                    if (!ListenerUtil.mutListener.listen(4627)) {
                                        this.currentPosition = n;
                                    }
                                    break;
                                }
                            }
                        }
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(4625)) {
                    {
                        long _loopCounter30 = 0;
                        for (int n = 0; (ListenerUtil.mutListener.listen(4624) ? (n >= this.messageModels.size()) : (ListenerUtil.mutListener.listen(4623) ? (n <= this.messageModels.size()) : (ListenerUtil.mutListener.listen(4622) ? (n > this.messageModels.size()) : (ListenerUtil.mutListener.listen(4621) ? (n != this.messageModels.size()) : (ListenerUtil.mutListener.listen(4620) ? (n == this.messageModels.size()) : (n < this.messageModels.size())))))); n++) {
                            ListenerUtil.loopListener.listen("_loopCounter30", ++_loopCounter30);
                            if (!ListenerUtil.mutListener.listen(4619)) {
                                if (this.messageModels.get(n).getId() == this.currentMessageModel.getId()) {
                                    if (!ListenerUtil.mutListener.listen(4618)) {
                                        this.currentPosition = n;
                                    }
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(4647)) {
            if ((ListenerUtil.mutListener.listen(4644) ? (currentPosition >= -1) : (ListenerUtil.mutListener.listen(4643) ? (currentPosition <= -1) : (ListenerUtil.mutListener.listen(4642) ? (currentPosition > -1) : (ListenerUtil.mutListener.listen(4641) ? (currentPosition < -1) : (ListenerUtil.mutListener.listen(4640) ? (currentPosition != -1) : (currentPosition == -1))))))) {
                if (!ListenerUtil.mutListener.listen(4645)) {
                    Toast.makeText(this, R.string.media_file_not_found, Toast.LENGTH_SHORT).show();
                }
                if (!ListenerUtil.mutListener.listen(4646)) {
                    finish();
                }
                return false;
            }
        }
        if (!ListenerUtil.mutListener.listen(4648)) {
            // create array
            this.fragments = new MediaViewFragment[this.messageModels.size()];
        }
        if (!ListenerUtil.mutListener.listen(4649)) {
            this.decryptedFileCache = new File[this.fragments.length];
        }
        if (!ListenerUtil.mutListener.listen(4650)) {
            // Instantiate a ViewPager and a PagerAdapter.
            this.pager = findViewById(R.id.pager);
        }
        if (!ListenerUtil.mutListener.listen(4652)) {
            this.pager.setOnPageChangeListener(new LockableViewPager.OnPageChangeListener() {

                @Override
                public void onPageScrolled(int i, float v, int i2) {
                }

                @Override
                public void onPageSelected(int i) {
                    if (!ListenerUtil.mutListener.listen(4651)) {
                        currentFragmentChanged(i);
                    }
                }

                @Override
                public void onPageScrollStateChanged(int i) {
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(4653)) {
            this.attachAdapter();
        }
        return true;
    }

    @Override
    public int getLayoutResource() {
        return R.layout.activity_media_viewer;
    }

    private void updateActionBarTitle(AbstractMessageModel messageModel) {
        String title = NameUtil.getDisplayNameOrNickname(this, messageModel, contactService);
        String subtitle = MessageUtil.getDisplayDate(this, messageModel, true);
        if (!ListenerUtil.mutListener.listen(4654)) {
            logger.debug("show updateActionBarTitle: " + title + " " + subtitle);
        }
        if (!ListenerUtil.mutListener.listen(4658)) {
            if (TestUtil.required(getToolbar(), title, subtitle)) {
                if (!ListenerUtil.mutListener.listen(4656)) {
                    getToolbar().setTitle(title);
                }
                if (!ListenerUtil.mutListener.listen(4657)) {
                    getToolbar().setSubtitle(subtitle);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(4655)) {
                    getToolbar().setTitle(null);
                }
            }
        }
        String captionText = MessageUtil.getCaptionText(messageModel);
        if (!ListenerUtil.mutListener.listen(4661)) {
            if (!TestUtil.empty(captionText)) {
                if (!ListenerUtil.mutListener.listen(4660)) {
                    this.caption.setText(emojiMarkupUtil.addMarkup(this, captionText));
                }
            } else {
                if (!ListenerUtil.mutListener.listen(4659)) {
                    this.caption.setText("");
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(4662)) {
            this.captionContainer.setVisibility(TestUtil.empty(captionText) ? View.GONE : View.VISIBLE);
        }
    }

    private void hideCurrentFragment() {
        if (!ListenerUtil.mutListener.listen(4676)) {
            if ((ListenerUtil.mutListener.listen(4673) ? ((ListenerUtil.mutListener.listen(4667) ? (this.currentPosition <= 0) : (ListenerUtil.mutListener.listen(4666) ? (this.currentPosition > 0) : (ListenerUtil.mutListener.listen(4665) ? (this.currentPosition < 0) : (ListenerUtil.mutListener.listen(4664) ? (this.currentPosition != 0) : (ListenerUtil.mutListener.listen(4663) ? (this.currentPosition == 0) : (this.currentPosition >= 0)))))) || (ListenerUtil.mutListener.listen(4672) ? (this.currentPosition >= this.messageModels.size()) : (ListenerUtil.mutListener.listen(4671) ? (this.currentPosition <= this.messageModels.size()) : (ListenerUtil.mutListener.listen(4670) ? (this.currentPosition > this.messageModels.size()) : (ListenerUtil.mutListener.listen(4669) ? (this.currentPosition != this.messageModels.size()) : (ListenerUtil.mutListener.listen(4668) ? (this.currentPosition == this.messageModels.size()) : (this.currentPosition < this.messageModels.size()))))))) : ((ListenerUtil.mutListener.listen(4667) ? (this.currentPosition <= 0) : (ListenerUtil.mutListener.listen(4666) ? (this.currentPosition > 0) : (ListenerUtil.mutListener.listen(4665) ? (this.currentPosition < 0) : (ListenerUtil.mutListener.listen(4664) ? (this.currentPosition != 0) : (ListenerUtil.mutListener.listen(4663) ? (this.currentPosition == 0) : (this.currentPosition >= 0)))))) && (ListenerUtil.mutListener.listen(4672) ? (this.currentPosition >= this.messageModels.size()) : (ListenerUtil.mutListener.listen(4671) ? (this.currentPosition <= this.messageModels.size()) : (ListenerUtil.mutListener.listen(4670) ? (this.currentPosition > this.messageModels.size()) : (ListenerUtil.mutListener.listen(4669) ? (this.currentPosition != this.messageModels.size()) : (ListenerUtil.mutListener.listen(4668) ? (this.currentPosition == this.messageModels.size()) : (this.currentPosition < this.messageModels.size()))))))))) {
                MediaViewFragment f = this.getFragmentByPosition(this.currentPosition);
                if (!ListenerUtil.mutListener.listen(4675)) {
                    if (f != null) {
                        if (!ListenerUtil.mutListener.listen(4674)) {
                            f.hide();
                        }
                    }
                }
            }
        }
    }

    private void currentFragmentChanged(final int imagePos) {
        if (!ListenerUtil.mutListener.listen(4677)) {
            this.loadingFragmentHandler.removeCallbacksAndMessages(null);
        }
        if (!ListenerUtil.mutListener.listen(4679)) {
            this.loadingFragmentHandler.postDelayed(new Runnable() {

                @Override
                public void run() {
                    if (!ListenerUtil.mutListener.listen(4678)) {
                        loadCurrentFrame(imagePos);
                    }
                }
            }, LOADING_DELAY);
        }
    }

    private void loadCurrentFrame(int imagePos) {
        if (!ListenerUtil.mutListener.listen(4680)) {
            this.hideCurrentFragment();
        }
        if (!ListenerUtil.mutListener.listen(4698)) {
            if ((ListenerUtil.mutListener.listen(4691) ? ((ListenerUtil.mutListener.listen(4685) ? (imagePos <= 0) : (ListenerUtil.mutListener.listen(4684) ? (imagePos > 0) : (ListenerUtil.mutListener.listen(4683) ? (imagePos < 0) : (ListenerUtil.mutListener.listen(4682) ? (imagePos != 0) : (ListenerUtil.mutListener.listen(4681) ? (imagePos == 0) : (imagePos >= 0)))))) || (ListenerUtil.mutListener.listen(4690) ? (imagePos >= this.messageModels.size()) : (ListenerUtil.mutListener.listen(4689) ? (imagePos <= this.messageModels.size()) : (ListenerUtil.mutListener.listen(4688) ? (imagePos > this.messageModels.size()) : (ListenerUtil.mutListener.listen(4687) ? (imagePos != this.messageModels.size()) : (ListenerUtil.mutListener.listen(4686) ? (imagePos == this.messageModels.size()) : (imagePos < this.messageModels.size()))))))) : ((ListenerUtil.mutListener.listen(4685) ? (imagePos <= 0) : (ListenerUtil.mutListener.listen(4684) ? (imagePos > 0) : (ListenerUtil.mutListener.listen(4683) ? (imagePos < 0) : (ListenerUtil.mutListener.listen(4682) ? (imagePos != 0) : (ListenerUtil.mutListener.listen(4681) ? (imagePos == 0) : (imagePos >= 0)))))) && (ListenerUtil.mutListener.listen(4690) ? (imagePos >= this.messageModels.size()) : (ListenerUtil.mutListener.listen(4689) ? (imagePos <= this.messageModels.size()) : (ListenerUtil.mutListener.listen(4688) ? (imagePos > this.messageModels.size()) : (ListenerUtil.mutListener.listen(4687) ? (imagePos != this.messageModels.size()) : (ListenerUtil.mutListener.listen(4686) ? (imagePos == this.messageModels.size()) : (imagePos < this.messageModels.size()))))))))) {
                if (!ListenerUtil.mutListener.listen(4692)) {
                    this.currentPosition = imagePos;
                }
                if (!ListenerUtil.mutListener.listen(4693)) {
                    this.currentMessageModel = this.messageModels.get(this.currentPosition);
                }
                if (!ListenerUtil.mutListener.listen(4694)) {
                    updateActionBarTitle(this.currentMessageModel);
                }
                final MediaViewFragment f = this.getCurrentFragment();
                if (!ListenerUtil.mutListener.listen(4697)) {
                    if (f != null) {
                        if (!ListenerUtil.mutListener.listen(4695)) {
                            RuntimeUtil.runOnUiThread(() -> {
                                logger.debug("showUI - loadCurrentFrame");
                                showUi();
                            });
                        }
                        if (!ListenerUtil.mutListener.listen(4696)) {
                            f.showDecrypted();
                        }
                    }
                }
            }
        }
    }

    @SuppressLint("RestrictedApi")
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!ListenerUtil.mutListener.listen(4699)) {
            super.onCreateOptionsMenu(menu);
        }
        if (!ListenerUtil.mutListener.listen(4700)) {
            getMenuInflater().inflate(R.menu.activity_media_viewer, menu);
        }
        try {
            MenuBuilder menuBuilder = (MenuBuilder) menu;
            if (!ListenerUtil.mutListener.listen(4701)) {
                menuBuilder.setOptionalIconsVisible(true);
            }
        } catch (Exception ignored) {
        }
        if (!ListenerUtil.mutListener.listen(4705)) {
            if (AppRestrictionUtil.isShareMediaDisabled(this)) {
                if (!ListenerUtil.mutListener.listen(4702)) {
                    menu.findItem(R.id.menu_save).setVisible(false);
                }
                if (!ListenerUtil.mutListener.listen(4703)) {
                    menu.findItem(R.id.menu_share).setVisible(false);
                }
                if (!ListenerUtil.mutListener.listen(4704)) {
                    menu.findItem(R.id.menu_view).setVisible(false);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(4707)) {
            if (getToolbar().getNavigationIcon() != null) {
                if (!ListenerUtil.mutListener.listen(4706)) {
                    getToolbar().getNavigationIcon().setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_IN);
                }
            }
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        final int itemId = item.getItemId();
        if (itemId == android.R.id.home) {
            if (!ListenerUtil.mutListener.listen(4714)) {
                finish();
            }
            return true;
        } else if (itemId == R.id.menu_save) {
            if (!ListenerUtil.mutListener.listen(4713)) {
                if (ConfigUtils.requestStoragePermissions(this, null, PERMISSION_REQUEST_SAVE_MESSAGE)) {
                    if (!ListenerUtil.mutListener.listen(4712)) {
                        saveMedia();
                    }
                }
            }
            return true;
        } else if (itemId == R.id.menu_view) {
            if (!ListenerUtil.mutListener.listen(4711)) {
                viewMediaInGallery();
            }
            return true;
        } else if (itemId == R.id.menu_share) {
            if (!ListenerUtil.mutListener.listen(4710)) {
                shareMedia();
            }
            return true;
        } else if (itemId == R.id.menu_gallery) {
            if (!ListenerUtil.mutListener.listen(4709)) {
                showGallery();
            }
            return true;
        } else if (itemId == R.id.menu_show_in_chat) {
            if (!ListenerUtil.mutListener.listen(4708)) {
                showInChat(this.currentMessageModel);
            }
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    private void saveMedia() {
        AbstractMessageModel messageModel = this.getCurrentMessageModel();
        if (!ListenerUtil.mutListener.listen(4718)) {
            if (TestUtil.required(this.fileService, messageModel)) {
                if (!ListenerUtil.mutListener.listen(4717)) {
                    if (currentMediaFile == null) {
                        if (!ListenerUtil.mutListener.listen(4716)) {
                            Toast.makeText(this, R.string.media_file_not_found, Toast.LENGTH_LONG).show();
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(4715)) {
                            this.fileService.saveMedia(this, null, new CopyOnWriteArrayList<>(Collections.singletonList(messageModel)), true);
                        }
                    }
                }
            }
        }
    }

    private void shareMedia() {
        AbstractMessageModel messageModel = this.getCurrentMessageModel();
        ExpandableTextEntryDialog alertDialog = ExpandableTextEntryDialog.newInstance(getString(R.string.share_media), R.string.add_caption_hint, messageModel.getCaption(), R.string.next, R.string.cancel, true);
        if (!ListenerUtil.mutListener.listen(4719)) {
            alertDialog.setData(messageModel);
        }
        if (!ListenerUtil.mutListener.listen(4720)) {
            alertDialog.show(getSupportFragmentManager(), null);
        }
    }

    @Override
    public void onYes(String tag, Object data, String text) {
        AbstractMessageModel messageModel = (AbstractMessageModel) data;
        Uri shareUri = fileService.copyToShareFile(messageModel, currentMediaFile);
        if (!ListenerUtil.mutListener.listen(4721)) {
            messageService.shareMediaMessages(this, new ArrayList<>(Collections.singletonList(messageModel)), new ArrayList<>(Collections.singletonList(shareUri)), text);
        }
    }

    @Override
    public void onNo(String tag) {
    }

    public void viewMediaInGallery() {
        AbstractMessageModel messageModel = this.getCurrentMessageModel();
        Uri shareUri = fileService.copyToShareFile(messageModel, currentMediaFile);
        if (!ListenerUtil.mutListener.listen(4722)) {
            messageService.viewMediaMessage(this, messageModel, shareUri);
        }
    }

    private void showGallery() {
        AbstractMessageModel messageModel = this.getCurrentMessageModel();
        if (!ListenerUtil.mutListener.listen(4731)) {
            if (messageModel != null) {
                Intent mediaGalleryIntent = new Intent(this, MediaGalleryActivity.class);
                if (!ListenerUtil.mutListener.listen(4723)) {
                    mediaGalleryIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                }
                if (!ListenerUtil.mutListener.listen(4727)) {
                    switch(this.currentReceiver.getType()) {
                        case MessageReceiver.Type_GROUP:
                            if (!ListenerUtil.mutListener.listen(4724)) {
                                mediaGalleryIntent.putExtra(ThreemaApplication.INTENT_DATA_GROUP, ((GroupMessageModel) messageModel).getGroupId());
                            }
                            break;
                        case MessageReceiver.Type_DISTRIBUTION_LIST:
                            if (!ListenerUtil.mutListener.listen(4725)) {
                                mediaGalleryIntent.putExtra(ThreemaApplication.INTENT_DATA_DISTRIBUTION_LIST, ((DistributionListMessageModel) messageModel).getDistributionListId());
                            }
                            break;
                        default:
                            if (!ListenerUtil.mutListener.listen(4726)) {
                                mediaGalleryIntent.putExtra(ThreemaApplication.INTENT_DATA_CONTACT, messageModel.getIdentity());
                            }
                    }
                }
                if (!ListenerUtil.mutListener.listen(4728)) {
                    IntentDataUtil.append(messageModel, mediaGalleryIntent);
                }
                if (!ListenerUtil.mutListener.listen(4729)) {
                    startActivity(mediaGalleryIntent);
                }
                if (!ListenerUtil.mutListener.listen(4730)) {
                    finish();
                }
            }
        }
    }

    private void showInChat(AbstractMessageModel messageModel) {
        if (!ListenerUtil.mutListener.listen(4732)) {
            if (messageModel == null) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(4733)) {
            AnimationUtil.startActivityForResult(this, null, IntentDataUtil.getJumpToMessageIntent(this, messageModel), ThreemaActivity.ACTIVITY_ID_COMPOSE_MESSAGE);
        }
        if (!ListenerUtil.mutListener.listen(4734)) {
            finish();
        }
    }

    private void hideSystemUi() {
        if (!ListenerUtil.mutListener.listen(4735)) {
            logger.debug("hideSystemUi");
        }
        if (!ListenerUtil.mutListener.listen(4739)) {
            if (getWindow() != null) {
                if (!ListenerUtil.mutListener.listen(4738)) {
                    if (!isDestroyed()) {
                        if (!ListenerUtil.mutListener.listen(4737)) {
                            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | // hide nav bar
                            View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | // hide status bar
                            View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_IMMERSIVE);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(4736)) {
                            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE);
                        }
                    }
                }
            }
        }
    }

    private void showSystemUi() {
        if (!ListenerUtil.mutListener.listen(4740)) {
            logger.debug("showSystemUi");
        }
        if (!ListenerUtil.mutListener.listen(4741)) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }
    }

    public void hideUi() {
        if (!ListenerUtil.mutListener.listen(4742)) {
            hideSystemUi();
        }
        if (!ListenerUtil.mutListener.listen(4743)) {
            actionBar.hide();
        }
        if (!ListenerUtil.mutListener.listen(4745)) {
            if (this.captionContainer != null) {
                if (!ListenerUtil.mutListener.listen(4744)) {
                    this.captionContainer.setVisibility(View.GONE);
                }
            }
        }
    }

    public void showUi() {
        if (!ListenerUtil.mutListener.listen(4746)) {
            logger.debug("showUI");
        }
        if (!ListenerUtil.mutListener.listen(4747)) {
            showSystemUi();
        }
        if (!ListenerUtil.mutListener.listen(4748)) {
            actionBar.show();
        }
        if (!ListenerUtil.mutListener.listen(4751)) {
            if ((ListenerUtil.mutListener.listen(4749) ? (this.captionContainer != null || !TestUtil.empty(caption.getText())) : (this.captionContainer != null && !TestUtil.empty(caption.getText())))) {
                if (!ListenerUtil.mutListener.listen(4750)) {
                    this.captionContainer.setVisibility(View.VISIBLE);
                }
            }
        }
    }

    @Override
    protected boolean checkInstances() {
        return TestUtil.required(this.messageService, this.fileService, this.contactService);
    }

    @Override
    protected void instantiate() {
        try {
            if (!ListenerUtil.mutListener.listen(4753)) {
                this.messageService = ThreemaApplication.getServiceManager().getMessageService();
            }
            if (!ListenerUtil.mutListener.listen(4754)) {
                this.fileService = ThreemaApplication.getServiceManager().getFileService();
            }
            if (!ListenerUtil.mutListener.listen(4755)) {
                this.contactService = ThreemaApplication.getServiceManager().getContactService();
            }
        } catch (ThreemaException e) {
            if (!ListenerUtil.mutListener.listen(4752)) {
                logger.error("Exception", e);
            }
        }
    }

    @Override
    public void onBackPressed() {
        // if in zoom mode,
        MediaViewFragment f = this.getCurrentFragment();
        if (!ListenerUtil.mutListener.listen(4758)) {
            if ((ListenerUtil.mutListener.listen(4756) ? (f == null && f.inquireClose()) : (f == null || f.inquireClose()))) {
                if (!ListenerUtil.mutListener.listen(4757)) {
                    super.onBackPressed();
                }
            }
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        if (!ListenerUtil.mutListener.listen(4759)) {
            // fixes https://code.google.com/p/android/issues/detail?id=19917
            super.onSaveInstanceState(outState);
        }
        if (!ListenerUtil.mutListener.listen(4761)) {
            if (outState.isEmpty()) {
                if (!ListenerUtil.mutListener.listen(4760)) {
                    outState.putBoolean("bug:fix", true);
                }
            }
        }
    }

    private AbstractMessageModel getCurrentMessageModel() {
        if (!ListenerUtil.mutListener.listen(4774)) {
            if ((ListenerUtil.mutListener.listen(4773) ? ((ListenerUtil.mutListener.listen(4767) ? (this.messageModels != null || (ListenerUtil.mutListener.listen(4766) ? (this.currentPosition <= 0) : (ListenerUtil.mutListener.listen(4765) ? (this.currentPosition > 0) : (ListenerUtil.mutListener.listen(4764) ? (this.currentPosition < 0) : (ListenerUtil.mutListener.listen(4763) ? (this.currentPosition != 0) : (ListenerUtil.mutListener.listen(4762) ? (this.currentPosition == 0) : (this.currentPosition >= 0))))))) : (this.messageModels != null && (ListenerUtil.mutListener.listen(4766) ? (this.currentPosition <= 0) : (ListenerUtil.mutListener.listen(4765) ? (this.currentPosition > 0) : (ListenerUtil.mutListener.listen(4764) ? (this.currentPosition < 0) : (ListenerUtil.mutListener.listen(4763) ? (this.currentPosition != 0) : (ListenerUtil.mutListener.listen(4762) ? (this.currentPosition == 0) : (this.currentPosition >= 0)))))))) || (ListenerUtil.mutListener.listen(4772) ? (this.currentPosition >= this.messageModels.size()) : (ListenerUtil.mutListener.listen(4771) ? (this.currentPosition <= this.messageModels.size()) : (ListenerUtil.mutListener.listen(4770) ? (this.currentPosition > this.messageModels.size()) : (ListenerUtil.mutListener.listen(4769) ? (this.currentPosition != this.messageModels.size()) : (ListenerUtil.mutListener.listen(4768) ? (this.currentPosition == this.messageModels.size()) : (this.currentPosition < this.messageModels.size()))))))) : ((ListenerUtil.mutListener.listen(4767) ? (this.messageModels != null || (ListenerUtil.mutListener.listen(4766) ? (this.currentPosition <= 0) : (ListenerUtil.mutListener.listen(4765) ? (this.currentPosition > 0) : (ListenerUtil.mutListener.listen(4764) ? (this.currentPosition < 0) : (ListenerUtil.mutListener.listen(4763) ? (this.currentPosition != 0) : (ListenerUtil.mutListener.listen(4762) ? (this.currentPosition == 0) : (this.currentPosition >= 0))))))) : (this.messageModels != null && (ListenerUtil.mutListener.listen(4766) ? (this.currentPosition <= 0) : (ListenerUtil.mutListener.listen(4765) ? (this.currentPosition > 0) : (ListenerUtil.mutListener.listen(4764) ? (this.currentPosition < 0) : (ListenerUtil.mutListener.listen(4763) ? (this.currentPosition != 0) : (ListenerUtil.mutListener.listen(4762) ? (this.currentPosition == 0) : (this.currentPosition >= 0)))))))) && (ListenerUtil.mutListener.listen(4772) ? (this.currentPosition >= this.messageModels.size()) : (ListenerUtil.mutListener.listen(4771) ? (this.currentPosition <= this.messageModels.size()) : (ListenerUtil.mutListener.listen(4770) ? (this.currentPosition > this.messageModels.size()) : (ListenerUtil.mutListener.listen(4769) ? (this.currentPosition != this.messageModels.size()) : (ListenerUtil.mutListener.listen(4768) ? (this.currentPosition == this.messageModels.size()) : (this.currentPosition < this.messageModels.size()))))))))) {
                return this.messageModels.get(this.currentPosition);
            }
        }
        return null;
    }

    private MediaViewFragment getCurrentFragment() {
        return this.getFragmentByPosition(this.currentPosition);
    }

    private MediaViewFragment getFragmentByPosition(int position) {
        if (!ListenerUtil.mutListener.listen(4787)) {
            if ((ListenerUtil.mutListener.listen(4786) ? ((ListenerUtil.mutListener.listen(4780) ? (this.fragments != null || (ListenerUtil.mutListener.listen(4779) ? (position <= 0) : (ListenerUtil.mutListener.listen(4778) ? (position > 0) : (ListenerUtil.mutListener.listen(4777) ? (position < 0) : (ListenerUtil.mutListener.listen(4776) ? (position != 0) : (ListenerUtil.mutListener.listen(4775) ? (position == 0) : (position >= 0))))))) : (this.fragments != null && (ListenerUtil.mutListener.listen(4779) ? (position <= 0) : (ListenerUtil.mutListener.listen(4778) ? (position > 0) : (ListenerUtil.mutListener.listen(4777) ? (position < 0) : (ListenerUtil.mutListener.listen(4776) ? (position != 0) : (ListenerUtil.mutListener.listen(4775) ? (position == 0) : (position >= 0)))))))) || (ListenerUtil.mutListener.listen(4785) ? (position >= this.fragments.length) : (ListenerUtil.mutListener.listen(4784) ? (position <= this.fragments.length) : (ListenerUtil.mutListener.listen(4783) ? (position > this.fragments.length) : (ListenerUtil.mutListener.listen(4782) ? (position != this.fragments.length) : (ListenerUtil.mutListener.listen(4781) ? (position == this.fragments.length) : (position < this.fragments.length))))))) : ((ListenerUtil.mutListener.listen(4780) ? (this.fragments != null || (ListenerUtil.mutListener.listen(4779) ? (position <= 0) : (ListenerUtil.mutListener.listen(4778) ? (position > 0) : (ListenerUtil.mutListener.listen(4777) ? (position < 0) : (ListenerUtil.mutListener.listen(4776) ? (position != 0) : (ListenerUtil.mutListener.listen(4775) ? (position == 0) : (position >= 0))))))) : (this.fragments != null && (ListenerUtil.mutListener.listen(4779) ? (position <= 0) : (ListenerUtil.mutListener.listen(4778) ? (position > 0) : (ListenerUtil.mutListener.listen(4777) ? (position < 0) : (ListenerUtil.mutListener.listen(4776) ? (position != 0) : (ListenerUtil.mutListener.listen(4775) ? (position == 0) : (position >= 0)))))))) && (ListenerUtil.mutListener.listen(4785) ? (position >= this.fragments.length) : (ListenerUtil.mutListener.listen(4784) ? (position <= this.fragments.length) : (ListenerUtil.mutListener.listen(4783) ? (position > this.fragments.length) : (ListenerUtil.mutListener.listen(4782) ? (position != this.fragments.length) : (ListenerUtil.mutListener.listen(4781) ? (position == this.fragments.length) : (position < this.fragments.length))))))))) {
                return this.fragments[position];
            }
        }
        return null;
    }

    private void attachAdapter() {
        // reset adapter!
        PagerAdapter pageAdapter = new ScreenSlidePagerAdapter(this, getSupportFragmentManager());
        if (!ListenerUtil.mutListener.listen(4788)) {
            this.pager.setAdapter(pageAdapter);
        }
        if (!ListenerUtil.mutListener.listen(4789)) {
            this.pager.setCurrentItem(this.currentPosition);
        }
        if (!ListenerUtil.mutListener.listen(4790)) {
            currentFragmentChanged(this.currentPosition);
        }
    }

    @Override
    protected void onDestroy() {
        if (!ListenerUtil.mutListener.listen(4791)) {
            // cleanup file cache
            loadingFragmentHandler.removeCallbacksAndMessages(null);
        }
        if (!ListenerUtil.mutListener.listen(4802)) {
            if (decryptedFileCache != null) {
                if (!ListenerUtil.mutListener.listen(4801)) {
                    {
                        long _loopCounter32 = 0;
                        for (int n = 0; (ListenerUtil.mutListener.listen(4800) ? (n >= this.decryptedFileCache.length) : (ListenerUtil.mutListener.listen(4799) ? (n <= this.decryptedFileCache.length) : (ListenerUtil.mutListener.listen(4798) ? (n > this.decryptedFileCache.length) : (ListenerUtil.mutListener.listen(4797) ? (n != this.decryptedFileCache.length) : (ListenerUtil.mutListener.listen(4796) ? (n == this.decryptedFileCache.length) : (n < this.decryptedFileCache.length)))))); n++) {
                            ListenerUtil.loopListener.listen("_loopCounter32", ++_loopCounter32);
                            if (!ListenerUtil.mutListener.listen(4795)) {
                                if ((ListenerUtil.mutListener.listen(4792) ? (this.decryptedFileCache[n] != null || this.decryptedFileCache[n].exists()) : (this.decryptedFileCache[n] != null && this.decryptedFileCache[n].exists()))) {
                                    if (!ListenerUtil.mutListener.listen(4793)) {
                                        FileUtil.deleteFileOrWarn(this.decryptedFileCache[n], "MediaViewerCache", logger);
                                    }
                                    if (!ListenerUtil.mutListener.listen(4794)) {
                                        this.decryptedFileCache[n] = null;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(4803)) {
            super.onDestroy();
        }
    }

    public AbstractMessageModel getMessageModel(int position) {
        return messageModels.get(position);
    }

    public File[] getDecryptedFileCache() {
        return this.decryptedFileCache;
    }

    /**
     *  Page Adapter that instantiates ImageViewFragments
     */
    public static class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {

        private final MediaViewerActivity a;

        private final FragmentManager mFragmentManager;

        private SparseArray<Fragment> mFragments;

        private FragmentTransaction mCurTransaction;

        public ScreenSlidePagerAdapter(MediaViewerActivity a, FragmentManager fm) {
            super(fm);
            this.a = a;
            mFragmentManager = fm;
            if (!ListenerUtil.mutListener.listen(4804)) {
                mFragments = new SparseArray<>();
            }
        }

        @SuppressLint("CommitTransaction")
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            Fragment fragment = getItem(position);
            if (!ListenerUtil.mutListener.listen(4806)) {
                if (mCurTransaction == null) {
                    if (!ListenerUtil.mutListener.listen(4805)) {
                        mCurTransaction = mFragmentManager.beginTransaction();
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(4807)) {
                mCurTransaction.add(container.getId(), fragment, "fragment:" + position);
            }
            if (!ListenerUtil.mutListener.listen(4808)) {
                mFragments.put(position, fragment);
            }
            return fragment;
        }

        @Override
        public boolean isViewFromObject(View view, Object fragment) {
            return ((Fragment) fragment).getView() == view;
        }

        @Override
        public Fragment getItem(final int position) {
            if (!ListenerUtil.mutListener.listen(4809)) {
                logger.debug("getItem " + position);
            }
            if (!ListenerUtil.mutListener.listen(4824)) {
                if (a.fragments[position] == null) {
                    final AbstractMessageModel messageModel = a.messageModels.get(position);
                    MediaViewFragment f;
                    Bundle args = new Bundle();
                    // check if caller wants the item to be played immediately
                    Intent intent = a.getIntent();
                    if (!ListenerUtil.mutListener.listen(4812)) {
                        if (intent.getExtras().getBoolean(EXTRA_ID_IMMEDIATE_PLAY, false)) {
                            if (!ListenerUtil.mutListener.listen(4810)) {
                                args.putBoolean(EXTRA_ID_IMMEDIATE_PLAY, true);
                            }
                            if (!ListenerUtil.mutListener.listen(4811)) {
                                intent.removeExtra(EXTRA_ID_IMMEDIATE_PLAY);
                            }
                        }
                    }
                    switch(messageModel.getType()) {
                        case VIDEO:
                            f = new VideoViewFragment();
                            break;
                        case FILE:
                            String mimeType = messageModel.getFileData().getMimeType();
                            if ((ListenerUtil.mutListener.listen(4813) ? (MimeUtil.isImageFile(mimeType) || !MimeUtil.isGifFile(mimeType)) : (MimeUtil.isImageFile(mimeType) && !MimeUtil.isGifFile(mimeType)))) {
                                f = new ImageViewFragment();
                            } else if (MimeUtil.isVideoFile(mimeType)) {
                                f = new VideoViewFragment();
                            } else if (IconUtil.getMimeIcon(mimeType) == R.drawable.ic_doc_audio) {
                                if ((ListenerUtil.mutListener.listen(4814) ? (MimeUtil.isMidiFile(mimeType) && MimeUtil.isFlacFile(mimeType)) : (MimeUtil.isMidiFile(mimeType) || MimeUtil.isFlacFile(mimeType)))) {
                                    f = new MediaPlayerViewFragment();
                                } else {
                                    f = new AudioViewFragment();
                                }
                            } else {
                                f = new FileViewFragment();
                            }
                            break;
                        case VOICEMESSAGE:
                            f = new AudioViewFragment();
                            break;
                        default:
                            f = new ImageViewFragment();
                    }
                    if (!ListenerUtil.mutListener.listen(4815)) {
                        args.putInt("position", position);
                    }
                    if (!ListenerUtil.mutListener.listen(4816)) {
                        f.setArguments(args);
                    }
                    if (!ListenerUtil.mutListener.listen(4819)) {
                        // lock page if media is open (image open = zoom)
                        f.setOnMediaOpenListener(new MediaViewFragment.OnMediaOpenListener() {

                            @Override
                            public void closed() {
                                if (!ListenerUtil.mutListener.listen(4817)) {
                                    a.pager.lock(false);
                                }
                            }

                            @Override
                            public void open() {
                                if (!ListenerUtil.mutListener.listen(4818)) {
                                    a.pager.lock(true);
                                }
                            }
                        });
                    }
                    if (!ListenerUtil.mutListener.listen(4822)) {
                        f.setOnImageLoaded(new MediaViewFragment.OnMediaLoadListener() {

                            @Override
                            public void decrypting() {
                                if (!ListenerUtil.mutListener.listen(4820)) {
                                    a.currentMediaFile = null;
                                }
                            }

                            @Override
                            public void decrypted(boolean success) {
                            }

                            @Override
                            public void loaded(File file) {
                                if (!ListenerUtil.mutListener.listen(4821)) {
                                    a.currentMediaFile = file;
                                }
                            }

                            @Override
                            public void thumbnailLoaded(Bitmap bitmap) {
                            }
                        });
                    }
                    if (!ListenerUtil.mutListener.listen(4823)) {
                        a.fragments[position] = f;
                    }
                }
            }
            return a.fragments[position];
        }

        @SuppressLint("CommitTransaction")
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            if (!ListenerUtil.mutListener.listen(4825)) {
                logger.debug("destroyItem " + position);
            }
            if (!ListenerUtil.mutListener.listen(4827)) {
                if (mCurTransaction == null) {
                    if (!ListenerUtil.mutListener.listen(4826)) {
                        mCurTransaction = mFragmentManager.beginTransaction();
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(4828)) {
                mCurTransaction.detach(mFragments.get(position));
            }
            if (!ListenerUtil.mutListener.listen(4829)) {
                mFragments.remove(position);
            }
            if (!ListenerUtil.mutListener.listen(4844)) {
                if ((ListenerUtil.mutListener.listen(4840) ? ((ListenerUtil.mutListener.listen(4834) ? (position <= 0) : (ListenerUtil.mutListener.listen(4833) ? (position > 0) : (ListenerUtil.mutListener.listen(4832) ? (position < 0) : (ListenerUtil.mutListener.listen(4831) ? (position != 0) : (ListenerUtil.mutListener.listen(4830) ? (position == 0) : (position >= 0)))))) || (ListenerUtil.mutListener.listen(4839) ? (position >= a.fragments.length) : (ListenerUtil.mutListener.listen(4838) ? (position <= a.fragments.length) : (ListenerUtil.mutListener.listen(4837) ? (position > a.fragments.length) : (ListenerUtil.mutListener.listen(4836) ? (position != a.fragments.length) : (ListenerUtil.mutListener.listen(4835) ? (position == a.fragments.length) : (position < a.fragments.length))))))) : ((ListenerUtil.mutListener.listen(4834) ? (position <= 0) : (ListenerUtil.mutListener.listen(4833) ? (position > 0) : (ListenerUtil.mutListener.listen(4832) ? (position < 0) : (ListenerUtil.mutListener.listen(4831) ? (position != 0) : (ListenerUtil.mutListener.listen(4830) ? (position == 0) : (position >= 0)))))) && (ListenerUtil.mutListener.listen(4839) ? (position >= a.fragments.length) : (ListenerUtil.mutListener.listen(4838) ? (position <= a.fragments.length) : (ListenerUtil.mutListener.listen(4837) ? (position > a.fragments.length) : (ListenerUtil.mutListener.listen(4836) ? (position != a.fragments.length) : (ListenerUtil.mutListener.listen(4835) ? (position == a.fragments.length) : (position < a.fragments.length))))))))) {
                    if (!ListenerUtil.mutListener.listen(4843)) {
                        if (TestUtil.required(a.fragments[position])) {
                            if (!ListenerUtil.mutListener.listen(4841)) {
                                // free memory
                                a.fragments[position].destroy();
                            }
                            if (!ListenerUtil.mutListener.listen(4842)) {
                                // remove from array
                                a.fragments[position] = null;
                            }
                        }
                    }
                }
            }
        }

        @Override
        public void finishUpdate(ViewGroup container) {
            if (!ListenerUtil.mutListener.listen(4848)) {
                if (mCurTransaction != null) {
                    if (!ListenerUtil.mutListener.listen(4845)) {
                        mCurTransaction.commitAllowingStateLoss();
                    }
                    if (!ListenerUtil.mutListener.listen(4846)) {
                        mCurTransaction = null;
                    }
                    if (!ListenerUtil.mutListener.listen(4847)) {
                        mFragmentManager.executePendingTransactions();
                    }
                }
            }
        }

        @Override
        public int getCount() {
            return a.messageModels.size();
        }

        @Override
        public Parcelable saveState() {
            if ((ListenerUtil.mutListener.listen(4853) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.N) : (ListenerUtil.mutListener.listen(4852) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.N) : (ListenerUtil.mutListener.listen(4851) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) : (ListenerUtil.mutListener.listen(4850) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.N) : (ListenerUtil.mutListener.listen(4849) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.N) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N))))))) {
                // fix TransactionTooLargeException
                Bundle bundle = (Bundle) super.saveState();
                if (!ListenerUtil.mutListener.listen(4855)) {
                    if (bundle != null) {
                        if (!ListenerUtil.mutListener.listen(4854)) {
                            bundle.putParcelableArray("states", null);
                        }
                    }
                }
                return bundle;
            } else {
                return super.saveState();
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (!ListenerUtil.mutListener.listen(4856)) {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
        if (!ListenerUtil.mutListener.listen(4867)) {
            switch(requestCode) {
                case PERMISSION_REQUEST_SAVE_MESSAGE:
                    if (!ListenerUtil.mutListener.listen(4866)) {
                        if ((ListenerUtil.mutListener.listen(4862) ? ((ListenerUtil.mutListener.listen(4861) ? (grantResults.length >= 0) : (ListenerUtil.mutListener.listen(4860) ? (grantResults.length <= 0) : (ListenerUtil.mutListener.listen(4859) ? (grantResults.length < 0) : (ListenerUtil.mutListener.listen(4858) ? (grantResults.length != 0) : (ListenerUtil.mutListener.listen(4857) ? (grantResults.length == 0) : (grantResults.length > 0)))))) || grantResults[0] == PackageManager.PERMISSION_GRANTED) : ((ListenerUtil.mutListener.listen(4861) ? (grantResults.length >= 0) : (ListenerUtil.mutListener.listen(4860) ? (grantResults.length <= 0) : (ListenerUtil.mutListener.listen(4859) ? (grantResults.length < 0) : (ListenerUtil.mutListener.listen(4858) ? (grantResults.length != 0) : (ListenerUtil.mutListener.listen(4857) ? (grantResults.length == 0) : (grantResults.length > 0)))))) && grantResults[0] == PackageManager.PERMISSION_GRANTED))) {
                            if (!ListenerUtil.mutListener.listen(4865)) {
                                saveMedia();
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(4864)) {
                                if (!ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                                    if (!ListenerUtil.mutListener.listen(4863)) {
                                        ConfigUtils.showPermissionRationale(this, findViewById(R.id.pager), R.string.permission_storage_required);
                                    }
                                }
                            }
                        }
                    }
            }
        }
    }

    private void setCaptionPosition() {
        if (!ListenerUtil.mutListener.listen(4878)) {
            if ((ListenerUtil.mutListener.listen(4872) ? (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(4871) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(4870) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(4869) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(4868) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.LOLLIPOP) : (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP))))))) {
                try {
                    ConfigUtils.NavigationBarDimensions dimensions = new ConfigUtils.NavigationBarDimensions();
                    if (!ListenerUtil.mutListener.listen(4874)) {
                        dimensions = ConfigUtils.getNavigationBarDimensions(getWindowManager(), dimensions);
                    }
                    if (!ListenerUtil.mutListener.listen(4877)) {
                        if (this.captionContainer != null) {
                            FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) this.captionContainer.getLayoutParams();
                            if (!ListenerUtil.mutListener.listen(4875)) {
                                params.setMargins(dimensions.width, 0, dimensions.width, dimensions.height + getResources().getDimensionPixelSize(R.dimen.mediaviewer_caption_border_bottom));
                            }
                            if (!ListenerUtil.mutListener.listen(4876)) {
                                this.captionContainer.setLayoutParams(params);
                            }
                        }
                    }
                } catch (Exception e) {
                    if (!ListenerUtil.mutListener.listen(4873)) {
                        logger.error("Exception", e);
                    }
                }
            }
        }
    }

    private void adjustStatusBar() {
        if (!ListenerUtil.mutListener.listen(4886)) {
            if ((ListenerUtil.mutListener.listen(4883) ? (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(4882) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(4881) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(4880) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(4879) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.LOLLIPOP) : (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP))))))) {
                FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) getToolbar().getLayoutParams();
                if (!ListenerUtil.mutListener.listen(4884)) {
                    lp.topMargin = ConfigUtils.getStatusBarHeight(this);
                }
                if (!ListenerUtil.mutListener.listen(4885)) {
                    getToolbar().setLayoutParams(lp);
                }
            }
        }
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        if (!ListenerUtil.mutListener.listen(4887)) {
            super.onConfigurationChanged(newConfig);
        }
        if (!ListenerUtil.mutListener.listen(4888)) {
            ConfigUtils.adjustToolbar(this, getToolbar());
        }
        if (!ListenerUtil.mutListener.listen(4889)) {
            adjustStatusBar();
        }
        if (!ListenerUtil.mutListener.listen(4890)) {
            setCaptionPosition();
        }
    }
}
