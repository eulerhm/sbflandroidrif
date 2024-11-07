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
import android.animation.Animator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewStub;
import android.view.ViewTreeObserver;
import android.view.inputmethod.EditorInfo;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.material.snackbar.Snackbar;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import androidx.annotation.NonNull;
import androidx.annotation.UiThread;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.view.ContextThemeWrapper;
import androidx.appcompat.widget.PopupMenu;
import androidx.core.app.ActivityCompat;
import androidx.core.view.OnApplyWindowInsetsListener;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.interpolator.view.animation.FastOutSlowInInterpolator;
import ch.threema.app.R;
import ch.threema.app.ThreemaApplication;
import ch.threema.app.adapters.SendMediaGridAdapter;
import ch.threema.app.camera.CameraActivity;
import ch.threema.app.camera.CameraUtil;
import ch.threema.app.camera.VideoEditView;
import ch.threema.app.dialogs.GenericAlertDialog;
import ch.threema.app.emojis.EmojiButton;
import ch.threema.app.emojis.EmojiPicker;
import ch.threema.app.mediaattacher.MediaFilterQuery;
import ch.threema.app.mediaattacher.MediaSelectionActivity;
import ch.threema.app.messagereceiver.MessageReceiver;
import ch.threema.app.services.DeadlineListService;
import ch.threema.app.services.FileService;
import ch.threema.app.services.MessageService;
import ch.threema.app.services.PreferenceService;
import ch.threema.app.ui.ComposeEditText;
import ch.threema.app.ui.DebouncedOnClickListener;
import ch.threema.app.ui.DebouncedOnMenuItemClickListener;
import ch.threema.app.ui.MediaItem;
import ch.threema.app.ui.SendButton;
import ch.threema.app.ui.draggablegrid.DynamicGridView;
import ch.threema.app.utils.AnimationUtil;
import ch.threema.app.utils.BitmapUtil;
import ch.threema.app.utils.BitmapWorkerTask;
import ch.threema.app.utils.BitmapWorkerTaskParams;
import ch.threema.app.utils.ConfigUtils;
import ch.threema.app.utils.EditTextUtil;
import ch.threema.app.utils.FileUtil;
import ch.threema.app.utils.IntentDataUtil;
import ch.threema.app.utils.MimeUtil;
import ch.threema.app.utils.TestUtil;
import ch.threema.app.video.VideoTimelineCache;
import ch.threema.base.ThreemaException;
import pl.droidsonroids.gif.GifImageView;
import static ch.threema.app.adapters.SendMediaGridAdapter.VIEW_TYPE_ADD;
import static ch.threema.app.adapters.SendMediaGridAdapter.VIEW_TYPE_NORMAL;
import static ch.threema.app.ui.MediaItem.TYPE_GIF;
import static ch.threema.app.ui.MediaItem.TYPE_IMAGE;
import static ch.threema.app.ui.MediaItem.TYPE_IMAGE_CAM;
import static ch.threema.app.utils.BitmapUtil.FLIP_HORIZONTAL;
import static ch.threema.app.utils.BitmapUtil.FLIP_VERTICAL;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class SendMediaActivity extends ThreemaToolbarActivity implements GridView.OnItemClickListener, GenericAlertDialog.DialogClickListener, ThreemaToolbarActivity.OnSoftKeyboardChangedListener {

    private static final Logger logger = LoggerFactory.getLogger(SendMediaActivity.class);

    private static final String STATE_BIGIMAGE_POS = "bigimage_pos";

    private static final String STATE_ITEMS = "items";

    private static final String STATE_CROP_FILE = "cropfile";

    private static final String STATE_CAMERA_FILE = "cameraFile";

    private static final String STATE_VIDEO_FILE = "vidFile";

    public static final String EXTRA_URLILIST = "urilist";

    public static final String EXTRA_MEDIA_ITEMS = "mediaitems";

    public static final String EXTRA_USE_EXTERNAL_CAMERA = "extcam";

    public static final int MAX_SELECTABLE_IMAGES = 10;

    private static final String DIALOG_TAG_QUIT_CONFIRM = "qc";

    private static final long IMAGE_ANIMATION_DURATION_MS = 180;

    private static final int PERMISSION_REQUEST_CAMERA = 100;

    private SendMediaGridAdapter sendMediaGridAdapter;

    private DynamicGridView gridView;

    private ImageView bigImageView;

    private GifImageView bigGifImageView;

    private ProgressBar bigProgressBar;

    private ArrayList<MessageReceiver> messageReceivers;

    private FileService fileService;

    private MessageService messageService;

    private File cropFile = null;

    private ArrayList<MediaItem> mediaItems = new ArrayList<>();

    private ComposeEditText captionEditText;

    private LinearLayout activityParentLayout;

    private EmojiPicker emojiPicker;

    private ImageButton cameraButton;

    private String cameraFilePath, videoFilePath;

    private boolean pickFromCamera, hasChanges = false;

    private View backgroundLayout;

    private int parentWidth = 0, parentHeight = 0;

    private int bigImagePos = 0;

    private boolean useExternalCamera;

    private VideoEditView videoEditView;

    private MenuItem settingsItem;

    private MediaFilterQuery lastMediaFilter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(5908)) {
            backgroundLayout = null;
        }
        if (!ListenerUtil.mutListener.listen(5909)) {
            super.onCreate(savedInstanceState);
        }
    }

    @Override
    protected boolean initActivity(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(5910)) {
            if (!super.initActivity(savedInstanceState)) {
                return false;
            }
        }
        if (!ListenerUtil.mutListener.listen(5955)) {
            if (preferenceService.getEmojiStyle() != PreferenceService.EmojiStyle_ANDROID) {
                if (!ListenerUtil.mutListener.listen(5953)) {
                    if ((ListenerUtil.mutListener.listen(5915) ? (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(5914) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(5913) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(5912) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(5911) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.LOLLIPOP) : (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP))))))) {
                        if (!ListenerUtil.mutListener.listen(5952)) {
                            findViewById(R.id.activity_parent).getRootView().getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

                                @Override
                                public void onGlobalLayout() {
                                    DisplayMetrics metrics = new DisplayMetrics();
                                    if (!ListenerUtil.mutListener.listen(5931)) {
                                        // get dimensions of usable display space with decorations (status bar / navigation bar) subtracted
                                        getWindowManager().getDefaultDisplay().getMetrics(metrics);
                                    }
                                    int usableHeight = metrics.heightPixels;
                                    int statusBarHeight = ConfigUtils.getStatusBarHeight(SendMediaActivity.this);
                                    int rootViewHeight = findViewById(R.id.activity_parent).getHeight();
                                    if (!ListenerUtil.mutListener.listen(5951)) {
                                        if ((ListenerUtil.mutListener.listen(5940) ? ((ListenerUtil.mutListener.listen(5935) ? (rootViewHeight % statusBarHeight) : (ListenerUtil.mutListener.listen(5934) ? (rootViewHeight / statusBarHeight) : (ListenerUtil.mutListener.listen(5933) ? (rootViewHeight * statusBarHeight) : (ListenerUtil.mutListener.listen(5932) ? (rootViewHeight - statusBarHeight) : (rootViewHeight + statusBarHeight))))) >= usableHeight) : (ListenerUtil.mutListener.listen(5939) ? ((ListenerUtil.mutListener.listen(5935) ? (rootViewHeight % statusBarHeight) : (ListenerUtil.mutListener.listen(5934) ? (rootViewHeight / statusBarHeight) : (ListenerUtil.mutListener.listen(5933) ? (rootViewHeight * statusBarHeight) : (ListenerUtil.mutListener.listen(5932) ? (rootViewHeight - statusBarHeight) : (rootViewHeight + statusBarHeight))))) <= usableHeight) : (ListenerUtil.mutListener.listen(5938) ? ((ListenerUtil.mutListener.listen(5935) ? (rootViewHeight % statusBarHeight) : (ListenerUtil.mutListener.listen(5934) ? (rootViewHeight / statusBarHeight) : (ListenerUtil.mutListener.listen(5933) ? (rootViewHeight * statusBarHeight) : (ListenerUtil.mutListener.listen(5932) ? (rootViewHeight - statusBarHeight) : (rootViewHeight + statusBarHeight))))) > usableHeight) : (ListenerUtil.mutListener.listen(5937) ? ((ListenerUtil.mutListener.listen(5935) ? (rootViewHeight % statusBarHeight) : (ListenerUtil.mutListener.listen(5934) ? (rootViewHeight / statusBarHeight) : (ListenerUtil.mutListener.listen(5933) ? (rootViewHeight * statusBarHeight) : (ListenerUtil.mutListener.listen(5932) ? (rootViewHeight - statusBarHeight) : (rootViewHeight + statusBarHeight))))) < usableHeight) : (ListenerUtil.mutListener.listen(5936) ? ((ListenerUtil.mutListener.listen(5935) ? (rootViewHeight % statusBarHeight) : (ListenerUtil.mutListener.listen(5934) ? (rootViewHeight / statusBarHeight) : (ListenerUtil.mutListener.listen(5933) ? (rootViewHeight * statusBarHeight) : (ListenerUtil.mutListener.listen(5932) ? (rootViewHeight - statusBarHeight) : (rootViewHeight + statusBarHeight))))) != usableHeight) : ((ListenerUtil.mutListener.listen(5935) ? (rootViewHeight % statusBarHeight) : (ListenerUtil.mutListener.listen(5934) ? (rootViewHeight / statusBarHeight) : (ListenerUtil.mutListener.listen(5933) ? (rootViewHeight * statusBarHeight) : (ListenerUtil.mutListener.listen(5932) ? (rootViewHeight - statusBarHeight) : (rootViewHeight + statusBarHeight))))) == usableHeight))))))) {
                                            if (!ListenerUtil.mutListener.listen(5950)) {
                                                onSoftKeyboardClosed();
                                            }
                                        } else {
                                            if (!ListenerUtil.mutListener.listen(5949)) {
                                                onSoftKeyboardOpened((ListenerUtil.mutListener.listen(5948) ? ((ListenerUtil.mutListener.listen(5944) ? (usableHeight % statusBarHeight) : (ListenerUtil.mutListener.listen(5943) ? (usableHeight / statusBarHeight) : (ListenerUtil.mutListener.listen(5942) ? (usableHeight * statusBarHeight) : (ListenerUtil.mutListener.listen(5941) ? (usableHeight + statusBarHeight) : (usableHeight - statusBarHeight))))) % rootViewHeight) : (ListenerUtil.mutListener.listen(5947) ? ((ListenerUtil.mutListener.listen(5944) ? (usableHeight % statusBarHeight) : (ListenerUtil.mutListener.listen(5943) ? (usableHeight / statusBarHeight) : (ListenerUtil.mutListener.listen(5942) ? (usableHeight * statusBarHeight) : (ListenerUtil.mutListener.listen(5941) ? (usableHeight + statusBarHeight) : (usableHeight - statusBarHeight))))) / rootViewHeight) : (ListenerUtil.mutListener.listen(5946) ? ((ListenerUtil.mutListener.listen(5944) ? (usableHeight % statusBarHeight) : (ListenerUtil.mutListener.listen(5943) ? (usableHeight / statusBarHeight) : (ListenerUtil.mutListener.listen(5942) ? (usableHeight * statusBarHeight) : (ListenerUtil.mutListener.listen(5941) ? (usableHeight + statusBarHeight) : (usableHeight - statusBarHeight))))) * rootViewHeight) : (ListenerUtil.mutListener.listen(5945) ? ((ListenerUtil.mutListener.listen(5944) ? (usableHeight % statusBarHeight) : (ListenerUtil.mutListener.listen(5943) ? (usableHeight / statusBarHeight) : (ListenerUtil.mutListener.listen(5942) ? (usableHeight * statusBarHeight) : (ListenerUtil.mutListener.listen(5941) ? (usableHeight + statusBarHeight) : (usableHeight - statusBarHeight))))) + rootViewHeight) : ((ListenerUtil.mutListener.listen(5944) ? (usableHeight % statusBarHeight) : (ListenerUtil.mutListener.listen(5943) ? (usableHeight / statusBarHeight) : (ListenerUtil.mutListener.listen(5942) ? (usableHeight * statusBarHeight) : (ListenerUtil.mutListener.listen(5941) ? (usableHeight + statusBarHeight) : (usableHeight - statusBarHeight))))) - rootViewHeight))))));
                                            }
                                        }
                                    }
                                }
                            });
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(5930)) {
                            ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.activity_parent).getRootView(), new OnApplyWindowInsetsListener() {

                                @Override
                                public WindowInsetsCompat onApplyWindowInsets(View v, WindowInsetsCompat insets) {
                                    if (!ListenerUtil.mutListener.listen(5916)) {
                                        logger.info("%%% system window top " + insets.getSystemWindowInsetTop() + " bottom " + insets.getSystemWindowInsetBottom());
                                    }
                                    if (!ListenerUtil.mutListener.listen(5917)) {
                                        logger.info("%%% stable insets top " + insets.getStableInsetTop() + " bottom " + insets.getStableInsetBottom());
                                    }
                                    if (!ListenerUtil.mutListener.listen(5929)) {
                                        if ((ListenerUtil.mutListener.listen(5922) ? (insets.getSystemWindowInsetBottom() >= insets.getStableInsetBottom()) : (ListenerUtil.mutListener.listen(5921) ? (insets.getSystemWindowInsetBottom() > insets.getStableInsetBottom()) : (ListenerUtil.mutListener.listen(5920) ? (insets.getSystemWindowInsetBottom() < insets.getStableInsetBottom()) : (ListenerUtil.mutListener.listen(5919) ? (insets.getSystemWindowInsetBottom() != insets.getStableInsetBottom()) : (ListenerUtil.mutListener.listen(5918) ? (insets.getSystemWindowInsetBottom() == insets.getStableInsetBottom()) : (insets.getSystemWindowInsetBottom() <= insets.getStableInsetBottom()))))))) {
                                            if (!ListenerUtil.mutListener.listen(5928)) {
                                                onSoftKeyboardClosed();
                                            }
                                        } else {
                                            if (!ListenerUtil.mutListener.listen(5927)) {
                                                onSoftKeyboardOpened((ListenerUtil.mutListener.listen(5926) ? (insets.getSystemWindowInsetBottom() % insets.getStableInsetBottom()) : (ListenerUtil.mutListener.listen(5925) ? (insets.getSystemWindowInsetBottom() / insets.getStableInsetBottom()) : (ListenerUtil.mutListener.listen(5924) ? (insets.getSystemWindowInsetBottom() * insets.getStableInsetBottom()) : (ListenerUtil.mutListener.listen(5923) ? (insets.getSystemWindowInsetBottom() + insets.getStableInsetBottom()) : (insets.getSystemWindowInsetBottom() - insets.getStableInsetBottom()))))));
                                            }
                                        }
                                    }
                                    return insets;
                                }
                            });
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(5954)) {
                    addOnSoftKeyboardChangedListener(this);
                }
            }
        }
        final ActionBar actionBar = getSupportActionBar();
        if (!ListenerUtil.mutListener.listen(5957)) {
            if (actionBar == null) {
                if (!ListenerUtil.mutListener.listen(5956)) {
                    finish();
                }
                return false;
            }
        }
        if (!ListenerUtil.mutListener.listen(5958)) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        DeadlineListService hiddenChatsListService;
        try {
            if (!ListenerUtil.mutListener.listen(5961)) {
                this.fileService = ThreemaApplication.getServiceManager().getFileService();
            }
            if (!ListenerUtil.mutListener.listen(5962)) {
                this.messageService = ThreemaApplication.getServiceManager().getMessageService();
            }
            hiddenChatsListService = ThreemaApplication.getServiceManager().getHiddenChatsListService();
        } catch (NullPointerException | ThreemaException e) {
            if (!ListenerUtil.mutListener.listen(5959)) {
                logger.error("Exception", e);
            }
            if (!ListenerUtil.mutListener.listen(5960)) {
                finish();
            }
            return false;
        }
        if (!ListenerUtil.mutListener.listen(5965)) {
            if (hiddenChatsListService == null) {
                if (!ListenerUtil.mutListener.listen(5963)) {
                    logger.error("HiddenChatsListService not available.");
                }
                if (!ListenerUtil.mutListener.listen(5964)) {
                    finish();
                }
                return false;
            }
        }
        if (!ListenerUtil.mutListener.listen(5966)) {
            this.activityParentLayout = findViewById(R.id.activity_parent);
        }
        Intent intent = getIntent();
        if (!ListenerUtil.mutListener.listen(5967)) {
            this.pickFromCamera = intent.getBooleanExtra(ThreemaApplication.INTENT_DATA_PICK_FROM_CAMERA, false);
        }
        if (!ListenerUtil.mutListener.listen(5968)) {
            this.useExternalCamera = intent.getBooleanExtra(EXTRA_USE_EXTERNAL_CAMERA, false);
        }
        if (!ListenerUtil.mutListener.listen(5969)) {
            this.messageReceivers = IntentDataUtil.getMessageReceiversFromIntent(intent);
        }
        if (!ListenerUtil.mutListener.listen(5970)) {
            // check if we previously filtered media in MediaAttachActivity to reuse the filter when adding additional media items
            this.lastMediaFilter = IntentDataUtil.getLastMediaFilterFromIntent(intent);
        }
        if (!ListenerUtil.mutListener.listen(5973)) {
            if ((ListenerUtil.mutListener.listen(5971) ? (this.pickFromCamera || savedInstanceState == null) : (this.pickFromCamera && savedInstanceState == null))) {
                if (!ListenerUtil.mutListener.listen(5972)) {
                    launchCamera();
                }
            }
        }
        ArrayList<Uri> urilist = intent.getParcelableArrayListExtra(EXTRA_URLILIST);
        if (!ListenerUtil.mutListener.listen(5975)) {
            if (urilist != null) {
                if (!ListenerUtil.mutListener.listen(5974)) {
                    intent.removeExtra(EXTRA_URLILIST);
                }
            }
        }
        List<MediaItem> mediaItems = intent.getParcelableArrayListExtra(EXTRA_MEDIA_ITEMS);
        if (!ListenerUtil.mutListener.listen(5977)) {
            if (mediaItems != null) {
                if (!ListenerUtil.mutListener.listen(5976)) {
                    intent.removeExtra(EXTRA_MEDIA_ITEMS);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(5978)) {
            setResult(RESULT_CANCELED);
        }
        boolean allReceiverChatsAreHidden = true;
        if (!ListenerUtil.mutListener.listen(5985)) {
            {
                long _loopCounter49 = 0;
                for (MessageReceiver messageReceiver : messageReceivers) {
                    ListenerUtil.loopListener.listen("_loopCounter49", ++_loopCounter49);
                    if (!ListenerUtil.mutListener.listen(5981)) {
                        messageReceiver.validateSendingPermission(new MessageReceiver.OnSendingPermissionDenied() {

                            @Override
                            public void denied(int errorResId) {
                                if (!ListenerUtil.mutListener.listen(5979)) {
                                    messageReceivers.remove(messageReceiver);
                                }
                                if (!ListenerUtil.mutListener.listen(5980)) {
                                    Toast.makeText(getApplicationContext(), errorResId, Toast.LENGTH_LONG).show();
                                }
                            }
                        });
                    }
                    if (!ListenerUtil.mutListener.listen(5984)) {
                        if ((ListenerUtil.mutListener.listen(5982) ? (allReceiverChatsAreHidden || !hiddenChatsListService.has(messageReceiver.getUniqueIdString())) : (allReceiverChatsAreHidden && !hiddenChatsListService.has(messageReceiver.getUniqueIdString())))) {
                            if (!ListenerUtil.mutListener.listen(5983)) {
                                allReceiverChatsAreHidden = false;
                            }
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(5992)) {
            if ((ListenerUtil.mutListener.listen(5990) ? (this.messageReceivers.size() >= 1) : (ListenerUtil.mutListener.listen(5989) ? (this.messageReceivers.size() <= 1) : (ListenerUtil.mutListener.listen(5988) ? (this.messageReceivers.size() > 1) : (ListenerUtil.mutListener.listen(5987) ? (this.messageReceivers.size() != 1) : (ListenerUtil.mutListener.listen(5986) ? (this.messageReceivers.size() == 1) : (this.messageReceivers.size() < 1))))))) {
                if (!ListenerUtil.mutListener.listen(5991)) {
                    finish();
                }
                return false;
            }
        }
        if (!ListenerUtil.mutListener.listen(5999)) {
            if (savedInstanceState != null) {
                if (!ListenerUtil.mutListener.listen(5993)) {
                    this.bigImagePos = savedInstanceState.getInt(STATE_BIGIMAGE_POS, 0);
                }
                if (!ListenerUtil.mutListener.listen(5994)) {
                    this.mediaItems = savedInstanceState.getParcelableArrayList(STATE_ITEMS);
                }
                if (!ListenerUtil.mutListener.listen(5995)) {
                    this.cameraFilePath = savedInstanceState.getString(STATE_CAMERA_FILE);
                }
                if (!ListenerUtil.mutListener.listen(5996)) {
                    this.videoFilePath = savedInstanceState.getString(STATE_VIDEO_FILE);
                }
                Uri cropUri = savedInstanceState.getParcelable(STATE_CROP_FILE);
                if (!ListenerUtil.mutListener.listen(5998)) {
                    if (cropUri != null) {
                        if (!ListenerUtil.mutListener.listen(5997)) {
                            this.cropFile = new File(cropUri.getPath());
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(6000)) {
            this.bigImageView = findViewById(R.id.preview_image);
        }
        if (!ListenerUtil.mutListener.listen(6001)) {
            this.bigGifImageView = findViewById(R.id.gif_image);
        }
        if (!ListenerUtil.mutListener.listen(6002)) {
            this.videoEditView = findViewById(R.id.video_edit_view);
        }
        if (!ListenerUtil.mutListener.listen(6003)) {
            this.bigProgressBar = findViewById(R.id.progress);
        }
        if (!ListenerUtil.mutListener.listen(6004)) {
            this.captionEditText = findViewById(R.id.caption_edittext);
        }
        if (!ListenerUtil.mutListener.listen(6014)) {
            this.captionEditText.addTextChangedListener(new TextWatcher() {

                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    if (!ListenerUtil.mutListener.listen(6005)) {
                        ThreemaApplication.activityUserInteract(SendMediaActivity.this);
                    }
                }

                @Override
                public void afterTextChanged(Editable s) {
                    if (!ListenerUtil.mutListener.listen(6013)) {
                        if ((ListenerUtil.mutListener.listen(6011) ? (s != null || (ListenerUtil.mutListener.listen(6010) ? (bigImagePos >= SendMediaActivity.this.mediaItems.size()) : (ListenerUtil.mutListener.listen(6009) ? (bigImagePos <= SendMediaActivity.this.mediaItems.size()) : (ListenerUtil.mutListener.listen(6008) ? (bigImagePos > SendMediaActivity.this.mediaItems.size()) : (ListenerUtil.mutListener.listen(6007) ? (bigImagePos != SendMediaActivity.this.mediaItems.size()) : (ListenerUtil.mutListener.listen(6006) ? (bigImagePos == SendMediaActivity.this.mediaItems.size()) : (bigImagePos < SendMediaActivity.this.mediaItems.size()))))))) : (s != null && (ListenerUtil.mutListener.listen(6010) ? (bigImagePos >= SendMediaActivity.this.mediaItems.size()) : (ListenerUtil.mutListener.listen(6009) ? (bigImagePos <= SendMediaActivity.this.mediaItems.size()) : (ListenerUtil.mutListener.listen(6008) ? (bigImagePos > SendMediaActivity.this.mediaItems.size()) : (ListenerUtil.mutListener.listen(6007) ? (bigImagePos != SendMediaActivity.this.mediaItems.size()) : (ListenerUtil.mutListener.listen(6006) ? (bigImagePos == SendMediaActivity.this.mediaItems.size()) : (bigImagePos < SendMediaActivity.this.mediaItems.size()))))))))) {
                            if (!ListenerUtil.mutListener.listen(6012)) {
                                SendMediaActivity.this.mediaItems.get(bigImagePos).setCaption(s.toString());
                            }
                        }
                    }
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(6015)) {
            this.cameraButton = findViewById(R.id.camera_button);
        }
        if (!ListenerUtil.mutListener.listen(6016)) {
            this.cameraButton.setOnClickListener(v -> launchCamera());
        }
        if (!ListenerUtil.mutListener.listen(6017)) {
            this.gridView = findViewById(R.id.gridview);
        }
        if (!ListenerUtil.mutListener.listen(6018)) {
            this.gridView.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);
        }
        if (!ListenerUtil.mutListener.listen(6019)) {
            this.gridView.setOnItemClickListener(this);
        }
        if (!ListenerUtil.mutListener.listen(6020)) {
            this.gridView.setOnItemLongClickListener((parent, view, position, id) -> {
                if (this.sendMediaGridAdapter.getItemViewType(position) == VIEW_TYPE_NORMAL) {
                    gridView.startEditMode(position);
                }
                return true;
            });
        }
        if (!ListenerUtil.mutListener.listen(6069)) {
            this.gridView.setOnDragListener(new DynamicGridView.OnDragListener() {

                @Override
                public void onDragStarted(int position) {
                    if (!ListenerUtil.mutListener.listen(6021)) {
                        logger.debug("drag started at position " + position);
                    }
                }

                @Override
                public void onDragPositionsChanged(int oldPosition, int newPosition) {
                    if (!ListenerUtil.mutListener.listen(6022)) {
                        logger.debug("drag item position changed from {} to {}", oldPosition, newPosition);
                    }
                    if (!ListenerUtil.mutListener.listen(6068)) {
                        if ((ListenerUtil.mutListener.listen(6027) ? (newPosition >= SendMediaActivity.this.mediaItems.size()) : (ListenerUtil.mutListener.listen(6026) ? (newPosition <= SendMediaActivity.this.mediaItems.size()) : (ListenerUtil.mutListener.listen(6025) ? (newPosition > SendMediaActivity.this.mediaItems.size()) : (ListenerUtil.mutListener.listen(6024) ? (newPosition != SendMediaActivity.this.mediaItems.size()) : (ListenerUtil.mutListener.listen(6023) ? (newPosition == SendMediaActivity.this.mediaItems.size()) : (newPosition < SendMediaActivity.this.mediaItems.size()))))))) {
                            if (!ListenerUtil.mutListener.listen(6066)) {
                                if (Math.abs((ListenerUtil.mutListener.listen(6031) ? (newPosition % oldPosition) : (ListenerUtil.mutListener.listen(6030) ? (newPosition / oldPosition) : (ListenerUtil.mutListener.listen(6029) ? (newPosition * oldPosition) : (ListenerUtil.mutListener.listen(6028) ? (newPosition + oldPosition) : (newPosition - oldPosition)))))) == 1) {
                                    if (!ListenerUtil.mutListener.listen(6065)) {
                                        Collections.swap(SendMediaActivity.this.mediaItems, oldPosition, newPosition);
                                    }
                                } else {
                                    if (!ListenerUtil.mutListener.listen(6064)) {
                                        if ((ListenerUtil.mutListener.listen(6036) ? (newPosition >= oldPosition) : (ListenerUtil.mutListener.listen(6035) ? (newPosition <= oldPosition) : (ListenerUtil.mutListener.listen(6034) ? (newPosition < oldPosition) : (ListenerUtil.mutListener.listen(6033) ? (newPosition != oldPosition) : (ListenerUtil.mutListener.listen(6032) ? (newPosition == oldPosition) : (newPosition > oldPosition))))))) {
                                            if (!ListenerUtil.mutListener.listen(6063)) {
                                                {
                                                    long _loopCounter51 = 0;
                                                    for (int i = oldPosition; (ListenerUtil.mutListener.listen(6062) ? (i >= newPosition) : (ListenerUtil.mutListener.listen(6061) ? (i <= newPosition) : (ListenerUtil.mutListener.listen(6060) ? (i > newPosition) : (ListenerUtil.mutListener.listen(6059) ? (i != newPosition) : (ListenerUtil.mutListener.listen(6058) ? (i == newPosition) : (i < newPosition)))))); i++) {
                                                        ListenerUtil.loopListener.listen("_loopCounter51", ++_loopCounter51);
                                                        if (!ListenerUtil.mutListener.listen(6057)) {
                                                            Collections.swap(SendMediaActivity.this.mediaItems, i, (ListenerUtil.mutListener.listen(6056) ? (i % 1) : (ListenerUtil.mutListener.listen(6055) ? (i / 1) : (ListenerUtil.mutListener.listen(6054) ? (i * 1) : (ListenerUtil.mutListener.listen(6053) ? (i - 1) : (i + 1))))));
                                                        }
                                                    }
                                                }
                                            }
                                        } else if ((ListenerUtil.mutListener.listen(6041) ? (newPosition >= oldPosition) : (ListenerUtil.mutListener.listen(6040) ? (newPosition <= oldPosition) : (ListenerUtil.mutListener.listen(6039) ? (newPosition > oldPosition) : (ListenerUtil.mutListener.listen(6038) ? (newPosition != oldPosition) : (ListenerUtil.mutListener.listen(6037) ? (newPosition == oldPosition) : (newPosition < oldPosition))))))) {
                                            if (!ListenerUtil.mutListener.listen(6052)) {
                                                {
                                                    long _loopCounter50 = 0;
                                                    for (int i = oldPosition; (ListenerUtil.mutListener.listen(6051) ? (i >= newPosition) : (ListenerUtil.mutListener.listen(6050) ? (i <= newPosition) : (ListenerUtil.mutListener.listen(6049) ? (i < newPosition) : (ListenerUtil.mutListener.listen(6048) ? (i != newPosition) : (ListenerUtil.mutListener.listen(6047) ? (i == newPosition) : (i > newPosition)))))); i--) {
                                                        ListenerUtil.loopListener.listen("_loopCounter50", ++_loopCounter50);
                                                        if (!ListenerUtil.mutListener.listen(6046)) {
                                                            Collections.swap(SendMediaActivity.this.mediaItems, i, (ListenerUtil.mutListener.listen(6045) ? (i % 1) : (ListenerUtil.mutListener.listen(6044) ? (i / 1) : (ListenerUtil.mutListener.listen(6043) ? (i * 1) : (ListenerUtil.mutListener.listen(6042) ? (i + 1) : (i - 1))))));
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(6067)) {
                                bigImagePos = newPosition;
                            }
                        }
                    }
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(6073)) {
            this.gridView.setOnDropListener(new DynamicGridView.OnDropListener() {

                @Override
                public void onActionDrop() {
                    if (!ListenerUtil.mutListener.listen(6072)) {
                        if (gridView.isEditMode()) {
                            if (!ListenerUtil.mutListener.listen(6070)) {
                                gridView.stopEditMode();
                            }
                            if (!ListenerUtil.mutListener.listen(6071)) {
                                showBigImage(bigImagePos);
                            }
                        }
                    }
                }
            });
        }
        EmojiButton emojiButton = findViewById(R.id.emoji_button);
        if (!ListenerUtil.mutListener.listen(6113)) {
            if (preferenceService.getEmojiStyle() != PreferenceService.EmojiStyle_ANDROID) {
                if (!ListenerUtil.mutListener.listen(6091)) {
                    emojiButton.setOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View v) {
                            if (!ListenerUtil.mutListener.listen(6090)) {
                                if (isSoftKeyboardOpen()) {
                                    if (!ListenerUtil.mutListener.listen(6087)) {
                                        runOnSoftKeyboardClose(new Runnable() {

                                            @Override
                                            public void run() {
                                                if (!ListenerUtil.mutListener.listen(6086)) {
                                                    if (emojiPicker != null) {
                                                        if (!ListenerUtil.mutListener.listen(6085)) {
                                                            emojiPicker.show(loadStoredSoftKeyboardHeight());
                                                        }
                                                    }
                                                }
                                            }
                                        });
                                    }
                                    if (!ListenerUtil.mutListener.listen(6089)) {
                                        captionEditText.post(new Runnable() {

                                            @Override
                                            public void run() {
                                                if (!ListenerUtil.mutListener.listen(6088)) {
                                                    EditTextUtil.hideSoftKeyboard(captionEditText);
                                                }
                                            }
                                        });
                                    }
                                } else {
                                    if (!ListenerUtil.mutListener.listen(6084)) {
                                        if (emojiPicker != null) {
                                            if (!ListenerUtil.mutListener.listen(6083)) {
                                                if (emojiPicker.isShown()) {
                                                    if (!ListenerUtil.mutListener.listen(6082)) {
                                                        if ((ListenerUtil.mutListener.listen(6077) ? (ConfigUtils.isLandscape(SendMediaActivity.this) || !ConfigUtils.isTabletLayout()) : (ConfigUtils.isLandscape(SendMediaActivity.this) && !ConfigUtils.isTabletLayout()))) {
                                                            if (!ListenerUtil.mutListener.listen(6081)) {
                                                                emojiPicker.hide();
                                                            }
                                                        } else {
                                                            if (!ListenerUtil.mutListener.listen(6078)) {
                                                                openSoftKeyboard(emojiPicker, captionEditText);
                                                            }
                                                            if (!ListenerUtil.mutListener.listen(6080)) {
                                                                if (getResources().getConfiguration().keyboard == Configuration.KEYBOARD_QWERTY) {
                                                                    if (!ListenerUtil.mutListener.listen(6079)) {
                                                                        emojiPicker.hide();
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                } else {
                                                    if (!ListenerUtil.mutListener.listen(6076)) {
                                                        emojiPicker.show(loadStoredSoftKeyboardHeight());
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    });
                }
                if (!ListenerUtil.mutListener.listen(6092)) {
                    this.emojiPicker = (EmojiPicker) ((ViewStub) findViewById(R.id.emoji_stub)).inflate();
                }
                if (!ListenerUtil.mutListener.listen(6093)) {
                    this.emojiPicker.init(this);
                }
                if (!ListenerUtil.mutListener.listen(6094)) {
                    emojiButton.attach(this.emojiPicker, true);
                }
                if (!ListenerUtil.mutListener.listen(6097)) {
                    this.emojiPicker.setEmojiKeyListener(new EmojiPicker.EmojiKeyListener() {

                        @Override
                        public void onBackspaceClick() {
                            if (!ListenerUtil.mutListener.listen(6095)) {
                                captionEditText.dispatchKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_DEL));
                            }
                        }

                        @Override
                        public void onEmojiClick(String emojiCodeString) {
                            if (!ListenerUtil.mutListener.listen(6096)) {
                                captionEditText.addEmoji(emojiCodeString);
                            }
                        }
                    });
                }
                if (!ListenerUtil.mutListener.listen(6104)) {
                    this.captionEditText.setOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View v) {
                            if (!ListenerUtil.mutListener.listen(6103)) {
                                if (emojiPicker != null) {
                                    if (!ListenerUtil.mutListener.listen(6102)) {
                                        if (emojiPicker.isShown()) {
                                            if (!ListenerUtil.mutListener.listen(6101)) {
                                                if ((ListenerUtil.mutListener.listen(6098) ? (ConfigUtils.isLandscape(SendMediaActivity.this) || !ConfigUtils.isTabletLayout()) : (ConfigUtils.isLandscape(SendMediaActivity.this) && !ConfigUtils.isTabletLayout()))) {
                                                    if (!ListenerUtil.mutListener.listen(6100)) {
                                                        emojiPicker.hide();
                                                    }
                                                } else {
                                                    if (!ListenerUtil.mutListener.listen(6099)) {
                                                        openSoftKeyboard(emojiPicker, captionEditText);
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    });
                }
                if (!ListenerUtil.mutListener.listen(6111)) {
                    this.captionEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {

                        @Override
                        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                            if (!ListenerUtil.mutListener.listen(6110)) {
                                if ((ListenerUtil.mutListener.listen(6108) ? ((actionId == EditorInfo.IME_ACTION_SEND) && ((ListenerUtil.mutListener.listen(6107) ? ((ListenerUtil.mutListener.listen(6106) ? ((ListenerUtil.mutListener.listen(6105) ? (event != null || event.getAction() == KeyEvent.ACTION_DOWN) : (event != null && event.getAction() == KeyEvent.ACTION_DOWN)) || event.getKeyCode() == KeyEvent.KEYCODE_ENTER) : ((ListenerUtil.mutListener.listen(6105) ? (event != null || event.getAction() == KeyEvent.ACTION_DOWN) : (event != null && event.getAction() == KeyEvent.ACTION_DOWN)) && event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || preferenceService.isEnterToSend()) : ((ListenerUtil.mutListener.listen(6106) ? ((ListenerUtil.mutListener.listen(6105) ? (event != null || event.getAction() == KeyEvent.ACTION_DOWN) : (event != null && event.getAction() == KeyEvent.ACTION_DOWN)) || event.getKeyCode() == KeyEvent.KEYCODE_ENTER) : ((ListenerUtil.mutListener.listen(6105) ? (event != null || event.getAction() == KeyEvent.ACTION_DOWN) : (event != null && event.getAction() == KeyEvent.ACTION_DOWN)) && event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) && preferenceService.isEnterToSend())))) : ((actionId == EditorInfo.IME_ACTION_SEND) || ((ListenerUtil.mutListener.listen(6107) ? ((ListenerUtil.mutListener.listen(6106) ? ((ListenerUtil.mutListener.listen(6105) ? (event != null || event.getAction() == KeyEvent.ACTION_DOWN) : (event != null && event.getAction() == KeyEvent.ACTION_DOWN)) || event.getKeyCode() == KeyEvent.KEYCODE_ENTER) : ((ListenerUtil.mutListener.listen(6105) ? (event != null || event.getAction() == KeyEvent.ACTION_DOWN) : (event != null && event.getAction() == KeyEvent.ACTION_DOWN)) && event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || preferenceService.isEnterToSend()) : ((ListenerUtil.mutListener.listen(6106) ? ((ListenerUtil.mutListener.listen(6105) ? (event != null || event.getAction() == KeyEvent.ACTION_DOWN) : (event != null && event.getAction() == KeyEvent.ACTION_DOWN)) || event.getKeyCode() == KeyEvent.KEYCODE_ENTER) : ((ListenerUtil.mutListener.listen(6105) ? (event != null || event.getAction() == KeyEvent.ACTION_DOWN) : (event != null && event.getAction() == KeyEvent.ACTION_DOWN)) && event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) && preferenceService.isEnterToSend())))))) {
                                    if (!ListenerUtil.mutListener.listen(6109)) {
                                        sendMedia();
                                    }
                                    return true;
                                }
                            }
                            return false;
                        }
                    });
                }
                if (!ListenerUtil.mutListener.listen(6112)) {
                    emojiButton.setColorFilter(getResources().getColor(android.R.color.white));
                }
            } else {
                if (!ListenerUtil.mutListener.listen(6074)) {
                    emojiButton.setVisibility(View.GONE);
                }
                if (!ListenerUtil.mutListener.listen(6075)) {
                    this.captionEditText.setPadding(getResources().getDimensionPixelSize(R.dimen.no_emoji_button_padding_left), this.captionEditText.getPaddingTop(), this.captionEditText.getPaddingRight(), this.captionEditText.getPaddingBottom());
                }
            }
        }
        String recipients = getIntent().getStringExtra(ThreemaApplication.INTENT_DATA_TEXT);
        if (!ListenerUtil.mutListener.listen(6119)) {
            if (!TestUtil.empty(recipients)) {
                if (!ListenerUtil.mutListener.listen(6114)) {
                    this.captionEditText.setHint(getString(R.string.send_to, recipients));
                }
                if (!ListenerUtil.mutListener.listen(6118)) {
                    this.captionEditText.addTextChangedListener(new TextWatcher() {

                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                        }

                        @Override
                        public void afterTextChanged(Editable s) {
                            if (!ListenerUtil.mutListener.listen(6117)) {
                                if ((ListenerUtil.mutListener.listen(6115) ? (s == null && s.length() == 0) : (s == null || s.length() == 0))) {
                                    if (!ListenerUtil.mutListener.listen(6116)) {
                                        captionEditText.setHint(getString(R.string.send_to, recipients));
                                    }
                                }
                            }
                        }
                    });
                }
            }
        }
        SendButton sendButton = findViewById(R.id.send_button);
        if (!ListenerUtil.mutListener.listen(6126)) {
            sendButton.setOnClickListener(new DebouncedOnClickListener(500) {

                @Override
                public void onDebouncedClick(View v) {
                    if (!ListenerUtil.mutListener.listen(6120)) {
                        // avoid duplicates
                        v.setEnabled(false);
                    }
                    if (!ListenerUtil.mutListener.listen(6121)) {
                        AnimationUtil.zoomOutAnimate(v);
                    }
                    if (!ListenerUtil.mutListener.listen(6124)) {
                        if ((ListenerUtil.mutListener.listen(6122) ? (emojiPicker != null || emojiPicker.isShown()) : (emojiPicker != null && emojiPicker.isShown()))) {
                            if (!ListenerUtil.mutListener.listen(6123)) {
                                emojiPicker.hide();
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(6125)) {
                        sendMedia();
                    }
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(6127)) {
            sendButton.setEnabled(true);
        }
        if (!ListenerUtil.mutListener.listen(6128)) {
            this.backgroundLayout = findViewById(R.id.background_layout);
        }
        final ViewTreeObserver observer = backgroundLayout.getViewTreeObserver();
        if (!ListenerUtil.mutListener.listen(6131)) {
            observer.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

                @Override
                public void onGlobalLayout() {
                    if (!ListenerUtil.mutListener.listen(6129)) {
                        backgroundLayout.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    }
                    if (!ListenerUtil.mutListener.listen(6130)) {
                        initUi(backgroundLayout, urilist, mediaItems);
                    }
                }
            });
        }
        return true;
    }

    private void initUi(View backgroundLayout, List<Uri> urilist, List<MediaItem> mediaItems) {
        if (!ListenerUtil.mutListener.listen(6132)) {
            parentWidth = backgroundLayout.getWidth();
        }
        if (!ListenerUtil.mutListener.listen(6133)) {
            parentHeight = backgroundLayout.getHeight();
        }
        int itemWidth = (ListenerUtil.mutListener.listen(6145) ? (((ListenerUtil.mutListener.listen(6141) ? ((ListenerUtil.mutListener.listen(6137) ? (parentWidth % getResources().getDimensionPixelSize(R.dimen.preview_gridview_padding_right)) : (ListenerUtil.mutListener.listen(6136) ? (parentWidth / getResources().getDimensionPixelSize(R.dimen.preview_gridview_padding_right)) : (ListenerUtil.mutListener.listen(6135) ? (parentWidth * getResources().getDimensionPixelSize(R.dimen.preview_gridview_padding_right)) : (ListenerUtil.mutListener.listen(6134) ? (parentWidth + getResources().getDimensionPixelSize(R.dimen.preview_gridview_padding_right)) : (parentWidth - getResources().getDimensionPixelSize(R.dimen.preview_gridview_padding_right)))))) % getResources().getDimensionPixelSize(R.dimen.preview_gridview_padding_left)) : (ListenerUtil.mutListener.listen(6140) ? ((ListenerUtil.mutListener.listen(6137) ? (parentWidth % getResources().getDimensionPixelSize(R.dimen.preview_gridview_padding_right)) : (ListenerUtil.mutListener.listen(6136) ? (parentWidth / getResources().getDimensionPixelSize(R.dimen.preview_gridview_padding_right)) : (ListenerUtil.mutListener.listen(6135) ? (parentWidth * getResources().getDimensionPixelSize(R.dimen.preview_gridview_padding_right)) : (ListenerUtil.mutListener.listen(6134) ? (parentWidth + getResources().getDimensionPixelSize(R.dimen.preview_gridview_padding_right)) : (parentWidth - getResources().getDimensionPixelSize(R.dimen.preview_gridview_padding_right)))))) / getResources().getDimensionPixelSize(R.dimen.preview_gridview_padding_left)) : (ListenerUtil.mutListener.listen(6139) ? ((ListenerUtil.mutListener.listen(6137) ? (parentWidth % getResources().getDimensionPixelSize(R.dimen.preview_gridview_padding_right)) : (ListenerUtil.mutListener.listen(6136) ? (parentWidth / getResources().getDimensionPixelSize(R.dimen.preview_gridview_padding_right)) : (ListenerUtil.mutListener.listen(6135) ? (parentWidth * getResources().getDimensionPixelSize(R.dimen.preview_gridview_padding_right)) : (ListenerUtil.mutListener.listen(6134) ? (parentWidth + getResources().getDimensionPixelSize(R.dimen.preview_gridview_padding_right)) : (parentWidth - getResources().getDimensionPixelSize(R.dimen.preview_gridview_padding_right)))))) * getResources().getDimensionPixelSize(R.dimen.preview_gridview_padding_left)) : (ListenerUtil.mutListener.listen(6138) ? ((ListenerUtil.mutListener.listen(6137) ? (parentWidth % getResources().getDimensionPixelSize(R.dimen.preview_gridview_padding_right)) : (ListenerUtil.mutListener.listen(6136) ? (parentWidth / getResources().getDimensionPixelSize(R.dimen.preview_gridview_padding_right)) : (ListenerUtil.mutListener.listen(6135) ? (parentWidth * getResources().getDimensionPixelSize(R.dimen.preview_gridview_padding_right)) : (ListenerUtil.mutListener.listen(6134) ? (parentWidth + getResources().getDimensionPixelSize(R.dimen.preview_gridview_padding_right)) : (parentWidth - getResources().getDimensionPixelSize(R.dimen.preview_gridview_padding_right)))))) + getResources().getDimensionPixelSize(R.dimen.preview_gridview_padding_left)) : ((ListenerUtil.mutListener.listen(6137) ? (parentWidth % getResources().getDimensionPixelSize(R.dimen.preview_gridview_padding_right)) : (ListenerUtil.mutListener.listen(6136) ? (parentWidth / getResources().getDimensionPixelSize(R.dimen.preview_gridview_padding_right)) : (ListenerUtil.mutListener.listen(6135) ? (parentWidth * getResources().getDimensionPixelSize(R.dimen.preview_gridview_padding_right)) : (ListenerUtil.mutListener.listen(6134) ? (parentWidth + getResources().getDimensionPixelSize(R.dimen.preview_gridview_padding_right)) : (parentWidth - getResources().getDimensionPixelSize(R.dimen.preview_gridview_padding_right)))))) - getResources().getDimensionPixelSize(R.dimen.preview_gridview_padding_left))))))) % getResources().getInteger(R.integer.gridview_num_columns)) : (ListenerUtil.mutListener.listen(6144) ? (((ListenerUtil.mutListener.listen(6141) ? ((ListenerUtil.mutListener.listen(6137) ? (parentWidth % getResources().getDimensionPixelSize(R.dimen.preview_gridview_padding_right)) : (ListenerUtil.mutListener.listen(6136) ? (parentWidth / getResources().getDimensionPixelSize(R.dimen.preview_gridview_padding_right)) : (ListenerUtil.mutListener.listen(6135) ? (parentWidth * getResources().getDimensionPixelSize(R.dimen.preview_gridview_padding_right)) : (ListenerUtil.mutListener.listen(6134) ? (parentWidth + getResources().getDimensionPixelSize(R.dimen.preview_gridview_padding_right)) : (parentWidth - getResources().getDimensionPixelSize(R.dimen.preview_gridview_padding_right)))))) % getResources().getDimensionPixelSize(R.dimen.preview_gridview_padding_left)) : (ListenerUtil.mutListener.listen(6140) ? ((ListenerUtil.mutListener.listen(6137) ? (parentWidth % getResources().getDimensionPixelSize(R.dimen.preview_gridview_padding_right)) : (ListenerUtil.mutListener.listen(6136) ? (parentWidth / getResources().getDimensionPixelSize(R.dimen.preview_gridview_padding_right)) : (ListenerUtil.mutListener.listen(6135) ? (parentWidth * getResources().getDimensionPixelSize(R.dimen.preview_gridview_padding_right)) : (ListenerUtil.mutListener.listen(6134) ? (parentWidth + getResources().getDimensionPixelSize(R.dimen.preview_gridview_padding_right)) : (parentWidth - getResources().getDimensionPixelSize(R.dimen.preview_gridview_padding_right)))))) / getResources().getDimensionPixelSize(R.dimen.preview_gridview_padding_left)) : (ListenerUtil.mutListener.listen(6139) ? ((ListenerUtil.mutListener.listen(6137) ? (parentWidth % getResources().getDimensionPixelSize(R.dimen.preview_gridview_padding_right)) : (ListenerUtil.mutListener.listen(6136) ? (parentWidth / getResources().getDimensionPixelSize(R.dimen.preview_gridview_padding_right)) : (ListenerUtil.mutListener.listen(6135) ? (parentWidth * getResources().getDimensionPixelSize(R.dimen.preview_gridview_padding_right)) : (ListenerUtil.mutListener.listen(6134) ? (parentWidth + getResources().getDimensionPixelSize(R.dimen.preview_gridview_padding_right)) : (parentWidth - getResources().getDimensionPixelSize(R.dimen.preview_gridview_padding_right)))))) * getResources().getDimensionPixelSize(R.dimen.preview_gridview_padding_left)) : (ListenerUtil.mutListener.listen(6138) ? ((ListenerUtil.mutListener.listen(6137) ? (parentWidth % getResources().getDimensionPixelSize(R.dimen.preview_gridview_padding_right)) : (ListenerUtil.mutListener.listen(6136) ? (parentWidth / getResources().getDimensionPixelSize(R.dimen.preview_gridview_padding_right)) : (ListenerUtil.mutListener.listen(6135) ? (parentWidth * getResources().getDimensionPixelSize(R.dimen.preview_gridview_padding_right)) : (ListenerUtil.mutListener.listen(6134) ? (parentWidth + getResources().getDimensionPixelSize(R.dimen.preview_gridview_padding_right)) : (parentWidth - getResources().getDimensionPixelSize(R.dimen.preview_gridview_padding_right)))))) + getResources().getDimensionPixelSize(R.dimen.preview_gridview_padding_left)) : ((ListenerUtil.mutListener.listen(6137) ? (parentWidth % getResources().getDimensionPixelSize(R.dimen.preview_gridview_padding_right)) : (ListenerUtil.mutListener.listen(6136) ? (parentWidth / getResources().getDimensionPixelSize(R.dimen.preview_gridview_padding_right)) : (ListenerUtil.mutListener.listen(6135) ? (parentWidth * getResources().getDimensionPixelSize(R.dimen.preview_gridview_padding_right)) : (ListenerUtil.mutListener.listen(6134) ? (parentWidth + getResources().getDimensionPixelSize(R.dimen.preview_gridview_padding_right)) : (parentWidth - getResources().getDimensionPixelSize(R.dimen.preview_gridview_padding_right)))))) - getResources().getDimensionPixelSize(R.dimen.preview_gridview_padding_left))))))) * getResources().getInteger(R.integer.gridview_num_columns)) : (ListenerUtil.mutListener.listen(6143) ? (((ListenerUtil.mutListener.listen(6141) ? ((ListenerUtil.mutListener.listen(6137) ? (parentWidth % getResources().getDimensionPixelSize(R.dimen.preview_gridview_padding_right)) : (ListenerUtil.mutListener.listen(6136) ? (parentWidth / getResources().getDimensionPixelSize(R.dimen.preview_gridview_padding_right)) : (ListenerUtil.mutListener.listen(6135) ? (parentWidth * getResources().getDimensionPixelSize(R.dimen.preview_gridview_padding_right)) : (ListenerUtil.mutListener.listen(6134) ? (parentWidth + getResources().getDimensionPixelSize(R.dimen.preview_gridview_padding_right)) : (parentWidth - getResources().getDimensionPixelSize(R.dimen.preview_gridview_padding_right)))))) % getResources().getDimensionPixelSize(R.dimen.preview_gridview_padding_left)) : (ListenerUtil.mutListener.listen(6140) ? ((ListenerUtil.mutListener.listen(6137) ? (parentWidth % getResources().getDimensionPixelSize(R.dimen.preview_gridview_padding_right)) : (ListenerUtil.mutListener.listen(6136) ? (parentWidth / getResources().getDimensionPixelSize(R.dimen.preview_gridview_padding_right)) : (ListenerUtil.mutListener.listen(6135) ? (parentWidth * getResources().getDimensionPixelSize(R.dimen.preview_gridview_padding_right)) : (ListenerUtil.mutListener.listen(6134) ? (parentWidth + getResources().getDimensionPixelSize(R.dimen.preview_gridview_padding_right)) : (parentWidth - getResources().getDimensionPixelSize(R.dimen.preview_gridview_padding_right)))))) / getResources().getDimensionPixelSize(R.dimen.preview_gridview_padding_left)) : (ListenerUtil.mutListener.listen(6139) ? ((ListenerUtil.mutListener.listen(6137) ? (parentWidth % getResources().getDimensionPixelSize(R.dimen.preview_gridview_padding_right)) : (ListenerUtil.mutListener.listen(6136) ? (parentWidth / getResources().getDimensionPixelSize(R.dimen.preview_gridview_padding_right)) : (ListenerUtil.mutListener.listen(6135) ? (parentWidth * getResources().getDimensionPixelSize(R.dimen.preview_gridview_padding_right)) : (ListenerUtil.mutListener.listen(6134) ? (parentWidth + getResources().getDimensionPixelSize(R.dimen.preview_gridview_padding_right)) : (parentWidth - getResources().getDimensionPixelSize(R.dimen.preview_gridview_padding_right)))))) * getResources().getDimensionPixelSize(R.dimen.preview_gridview_padding_left)) : (ListenerUtil.mutListener.listen(6138) ? ((ListenerUtil.mutListener.listen(6137) ? (parentWidth % getResources().getDimensionPixelSize(R.dimen.preview_gridview_padding_right)) : (ListenerUtil.mutListener.listen(6136) ? (parentWidth / getResources().getDimensionPixelSize(R.dimen.preview_gridview_padding_right)) : (ListenerUtil.mutListener.listen(6135) ? (parentWidth * getResources().getDimensionPixelSize(R.dimen.preview_gridview_padding_right)) : (ListenerUtil.mutListener.listen(6134) ? (parentWidth + getResources().getDimensionPixelSize(R.dimen.preview_gridview_padding_right)) : (parentWidth - getResources().getDimensionPixelSize(R.dimen.preview_gridview_padding_right)))))) + getResources().getDimensionPixelSize(R.dimen.preview_gridview_padding_left)) : ((ListenerUtil.mutListener.listen(6137) ? (parentWidth % getResources().getDimensionPixelSize(R.dimen.preview_gridview_padding_right)) : (ListenerUtil.mutListener.listen(6136) ? (parentWidth / getResources().getDimensionPixelSize(R.dimen.preview_gridview_padding_right)) : (ListenerUtil.mutListener.listen(6135) ? (parentWidth * getResources().getDimensionPixelSize(R.dimen.preview_gridview_padding_right)) : (ListenerUtil.mutListener.listen(6134) ? (parentWidth + getResources().getDimensionPixelSize(R.dimen.preview_gridview_padding_right)) : (parentWidth - getResources().getDimensionPixelSize(R.dimen.preview_gridview_padding_right)))))) - getResources().getDimensionPixelSize(R.dimen.preview_gridview_padding_left))))))) - getResources().getInteger(R.integer.gridview_num_columns)) : (ListenerUtil.mutListener.listen(6142) ? (((ListenerUtil.mutListener.listen(6141) ? ((ListenerUtil.mutListener.listen(6137) ? (parentWidth % getResources().getDimensionPixelSize(R.dimen.preview_gridview_padding_right)) : (ListenerUtil.mutListener.listen(6136) ? (parentWidth / getResources().getDimensionPixelSize(R.dimen.preview_gridview_padding_right)) : (ListenerUtil.mutListener.listen(6135) ? (parentWidth * getResources().getDimensionPixelSize(R.dimen.preview_gridview_padding_right)) : (ListenerUtil.mutListener.listen(6134) ? (parentWidth + getResources().getDimensionPixelSize(R.dimen.preview_gridview_padding_right)) : (parentWidth - getResources().getDimensionPixelSize(R.dimen.preview_gridview_padding_right)))))) % getResources().getDimensionPixelSize(R.dimen.preview_gridview_padding_left)) : (ListenerUtil.mutListener.listen(6140) ? ((ListenerUtil.mutListener.listen(6137) ? (parentWidth % getResources().getDimensionPixelSize(R.dimen.preview_gridview_padding_right)) : (ListenerUtil.mutListener.listen(6136) ? (parentWidth / getResources().getDimensionPixelSize(R.dimen.preview_gridview_padding_right)) : (ListenerUtil.mutListener.listen(6135) ? (parentWidth * getResources().getDimensionPixelSize(R.dimen.preview_gridview_padding_right)) : (ListenerUtil.mutListener.listen(6134) ? (parentWidth + getResources().getDimensionPixelSize(R.dimen.preview_gridview_padding_right)) : (parentWidth - getResources().getDimensionPixelSize(R.dimen.preview_gridview_padding_right)))))) / getResources().getDimensionPixelSize(R.dimen.preview_gridview_padding_left)) : (ListenerUtil.mutListener.listen(6139) ? ((ListenerUtil.mutListener.listen(6137) ? (parentWidth % getResources().getDimensionPixelSize(R.dimen.preview_gridview_padding_right)) : (ListenerUtil.mutListener.listen(6136) ? (parentWidth / getResources().getDimensionPixelSize(R.dimen.preview_gridview_padding_right)) : (ListenerUtil.mutListener.listen(6135) ? (parentWidth * getResources().getDimensionPixelSize(R.dimen.preview_gridview_padding_right)) : (ListenerUtil.mutListener.listen(6134) ? (parentWidth + getResources().getDimensionPixelSize(R.dimen.preview_gridview_padding_right)) : (parentWidth - getResources().getDimensionPixelSize(R.dimen.preview_gridview_padding_right)))))) * getResources().getDimensionPixelSize(R.dimen.preview_gridview_padding_left)) : (ListenerUtil.mutListener.listen(6138) ? ((ListenerUtil.mutListener.listen(6137) ? (parentWidth % getResources().getDimensionPixelSize(R.dimen.preview_gridview_padding_right)) : (ListenerUtil.mutListener.listen(6136) ? (parentWidth / getResources().getDimensionPixelSize(R.dimen.preview_gridview_padding_right)) : (ListenerUtil.mutListener.listen(6135) ? (parentWidth * getResources().getDimensionPixelSize(R.dimen.preview_gridview_padding_right)) : (ListenerUtil.mutListener.listen(6134) ? (parentWidth + getResources().getDimensionPixelSize(R.dimen.preview_gridview_padding_right)) : (parentWidth - getResources().getDimensionPixelSize(R.dimen.preview_gridview_padding_right)))))) + getResources().getDimensionPixelSize(R.dimen.preview_gridview_padding_left)) : ((ListenerUtil.mutListener.listen(6137) ? (parentWidth % getResources().getDimensionPixelSize(R.dimen.preview_gridview_padding_right)) : (ListenerUtil.mutListener.listen(6136) ? (parentWidth / getResources().getDimensionPixelSize(R.dimen.preview_gridview_padding_right)) : (ListenerUtil.mutListener.listen(6135) ? (parentWidth * getResources().getDimensionPixelSize(R.dimen.preview_gridview_padding_right)) : (ListenerUtil.mutListener.listen(6134) ? (parentWidth + getResources().getDimensionPixelSize(R.dimen.preview_gridview_padding_right)) : (parentWidth - getResources().getDimensionPixelSize(R.dimen.preview_gridview_padding_right)))))) - getResources().getDimensionPixelSize(R.dimen.preview_gridview_padding_left))))))) + getResources().getInteger(R.integer.gridview_num_columns)) : (((ListenerUtil.mutListener.listen(6141) ? ((ListenerUtil.mutListener.listen(6137) ? (parentWidth % getResources().getDimensionPixelSize(R.dimen.preview_gridview_padding_right)) : (ListenerUtil.mutListener.listen(6136) ? (parentWidth / getResources().getDimensionPixelSize(R.dimen.preview_gridview_padding_right)) : (ListenerUtil.mutListener.listen(6135) ? (parentWidth * getResources().getDimensionPixelSize(R.dimen.preview_gridview_padding_right)) : (ListenerUtil.mutListener.listen(6134) ? (parentWidth + getResources().getDimensionPixelSize(R.dimen.preview_gridview_padding_right)) : (parentWidth - getResources().getDimensionPixelSize(R.dimen.preview_gridview_padding_right)))))) % getResources().getDimensionPixelSize(R.dimen.preview_gridview_padding_left)) : (ListenerUtil.mutListener.listen(6140) ? ((ListenerUtil.mutListener.listen(6137) ? (parentWidth % getResources().getDimensionPixelSize(R.dimen.preview_gridview_padding_right)) : (ListenerUtil.mutListener.listen(6136) ? (parentWidth / getResources().getDimensionPixelSize(R.dimen.preview_gridview_padding_right)) : (ListenerUtil.mutListener.listen(6135) ? (parentWidth * getResources().getDimensionPixelSize(R.dimen.preview_gridview_padding_right)) : (ListenerUtil.mutListener.listen(6134) ? (parentWidth + getResources().getDimensionPixelSize(R.dimen.preview_gridview_padding_right)) : (parentWidth - getResources().getDimensionPixelSize(R.dimen.preview_gridview_padding_right)))))) / getResources().getDimensionPixelSize(R.dimen.preview_gridview_padding_left)) : (ListenerUtil.mutListener.listen(6139) ? ((ListenerUtil.mutListener.listen(6137) ? (parentWidth % getResources().getDimensionPixelSize(R.dimen.preview_gridview_padding_right)) : (ListenerUtil.mutListener.listen(6136) ? (parentWidth / getResources().getDimensionPixelSize(R.dimen.preview_gridview_padding_right)) : (ListenerUtil.mutListener.listen(6135) ? (parentWidth * getResources().getDimensionPixelSize(R.dimen.preview_gridview_padding_right)) : (ListenerUtil.mutListener.listen(6134) ? (parentWidth + getResources().getDimensionPixelSize(R.dimen.preview_gridview_padding_right)) : (parentWidth - getResources().getDimensionPixelSize(R.dimen.preview_gridview_padding_right)))))) * getResources().getDimensionPixelSize(R.dimen.preview_gridview_padding_left)) : (ListenerUtil.mutListener.listen(6138) ? ((ListenerUtil.mutListener.listen(6137) ? (parentWidth % getResources().getDimensionPixelSize(R.dimen.preview_gridview_padding_right)) : (ListenerUtil.mutListener.listen(6136) ? (parentWidth / getResources().getDimensionPixelSize(R.dimen.preview_gridview_padding_right)) : (ListenerUtil.mutListener.listen(6135) ? (parentWidth * getResources().getDimensionPixelSize(R.dimen.preview_gridview_padding_right)) : (ListenerUtil.mutListener.listen(6134) ? (parentWidth + getResources().getDimensionPixelSize(R.dimen.preview_gridview_padding_right)) : (parentWidth - getResources().getDimensionPixelSize(R.dimen.preview_gridview_padding_right)))))) + getResources().getDimensionPixelSize(R.dimen.preview_gridview_padding_left)) : ((ListenerUtil.mutListener.listen(6137) ? (parentWidth % getResources().getDimensionPixelSize(R.dimen.preview_gridview_padding_right)) : (ListenerUtil.mutListener.listen(6136) ? (parentWidth / getResources().getDimensionPixelSize(R.dimen.preview_gridview_padding_right)) : (ListenerUtil.mutListener.listen(6135) ? (parentWidth * getResources().getDimensionPixelSize(R.dimen.preview_gridview_padding_right)) : (ListenerUtil.mutListener.listen(6134) ? (parentWidth + getResources().getDimensionPixelSize(R.dimen.preview_gridview_padding_right)) : (parentWidth - getResources().getDimensionPixelSize(R.dimen.preview_gridview_padding_right)))))) - getResources().getDimensionPixelSize(R.dimen.preview_gridview_padding_left))))))) / getResources().getInteger(R.integer.gridview_num_columns))))));
        SendMediaGridAdapter.ClickListener clickListener = new SendMediaGridAdapter.ClickListener() {

            @Override
            public void onDeleteKeyClicked(MediaItem item) {
                if (!ListenerUtil.mutListener.listen(6146)) {
                    removeItem(item);
                }
            }
        };
        if (!ListenerUtil.mutListener.listen(6147)) {
            this.sendMediaGridAdapter = new SendMediaGridAdapter(this, this.mediaItems, itemWidth, clickListener);
        }
        if (!ListenerUtil.mutListener.listen(6148)) {
            this.gridView.setAdapter(this.sendMediaGridAdapter);
        }
        if (!ListenerUtil.mutListener.listen(6170)) {
            // add first image
            if ((ListenerUtil.mutListener.listen(6153) ? (this.mediaItems.size() >= 0) : (ListenerUtil.mutListener.listen(6152) ? (this.mediaItems.size() > 0) : (ListenerUtil.mutListener.listen(6151) ? (this.mediaItems.size() < 0) : (ListenerUtil.mutListener.listen(6150) ? (this.mediaItems.size() != 0) : (ListenerUtil.mutListener.listen(6149) ? (this.mediaItems.size() == 0) : (this.mediaItems.size() <= 0))))))) {
                if (!ListenerUtil.mutListener.listen(6169)) {
                    if (!this.pickFromCamera) {
                        if (!ListenerUtil.mutListener.listen(6168)) {
                            if ((ListenerUtil.mutListener.listen(6159) ? (urilist != null || (ListenerUtil.mutListener.listen(6158) ? (urilist.size() >= 0) : (ListenerUtil.mutListener.listen(6157) ? (urilist.size() <= 0) : (ListenerUtil.mutListener.listen(6156) ? (urilist.size() < 0) : (ListenerUtil.mutListener.listen(6155) ? (urilist.size() != 0) : (ListenerUtil.mutListener.listen(6154) ? (urilist.size() == 0) : (urilist.size() > 0))))))) : (urilist != null && (ListenerUtil.mutListener.listen(6158) ? (urilist.size() >= 0) : (ListenerUtil.mutListener.listen(6157) ? (urilist.size() <= 0) : (ListenerUtil.mutListener.listen(6156) ? (urilist.size() < 0) : (ListenerUtil.mutListener.listen(6155) ? (urilist.size() != 0) : (ListenerUtil.mutListener.listen(6154) ? (urilist.size() == 0) : (urilist.size() > 0))))))))) {
                                if (!ListenerUtil.mutListener.listen(6167)) {
                                    addItemsByUriList(urilist);
                                }
                            } else if ((ListenerUtil.mutListener.listen(6165) ? (mediaItems != null || (ListenerUtil.mutListener.listen(6164) ? (mediaItems.size() >= 0) : (ListenerUtil.mutListener.listen(6163) ? (mediaItems.size() <= 0) : (ListenerUtil.mutListener.listen(6162) ? (mediaItems.size() < 0) : (ListenerUtil.mutListener.listen(6161) ? (mediaItems.size() != 0) : (ListenerUtil.mutListener.listen(6160) ? (mediaItems.size() == 0) : (mediaItems.size() > 0))))))) : (mediaItems != null && (ListenerUtil.mutListener.listen(6164) ? (mediaItems.size() >= 0) : (ListenerUtil.mutListener.listen(6163) ? (mediaItems.size() <= 0) : (ListenerUtil.mutListener.listen(6162) ? (mediaItems.size() < 0) : (ListenerUtil.mutListener.listen(6161) ? (mediaItems.size() != 0) : (ListenerUtil.mutListener.listen(6160) ? (mediaItems.size() == 0) : (mediaItems.size() > 0))))))))) {
                                if (!ListenerUtil.mutListener.listen(6166)) {
                                    addItemsByMediaItem(mediaItems);
                                }
                            }
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(6174)) {
            if (this.pickFromCamera) {
                if (!ListenerUtil.mutListener.listen(6173)) {
                    if (this.backgroundLayout != null) {
                        if (!ListenerUtil.mutListener.listen(6172)) {
                            this.backgroundLayout.postDelayed(() -> backgroundLayout.setVisibility(View.VISIBLE), 500);
                        }
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(6171)) {
                    this.backgroundLayout.setVisibility(View.VISIBLE);
                }
            }
        }
    }

    private void showSettingsDropDown(final View view, final MediaItem mediaItem) {
        Context contextWrapper = new ContextThemeWrapper(this, R.style.Threema_PopupMenuStyle);
        PopupMenu popup = new PopupMenu(contextWrapper, view);
        if (!ListenerUtil.mutListener.listen(6175)) {
            popup.setOnMenuItemClickListener(item -> {
                mediaItem.setImageScale(item.getOrder());
                return true;
            });
        }
        if (!ListenerUtil.mutListener.listen(6176)) {
            popup.inflate(R.menu.view_image_settings);
        }
        @PreferenceService.ImageScale
        int currentScale = mediaItem.getImageScale();
        if (!ListenerUtil.mutListener.listen(6178)) {
            if (currentScale == PreferenceService.ImageScale_DEFAULT) {
                if (!ListenerUtil.mutListener.listen(6177)) {
                    currentScale = preferenceService.getImageScale();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(6179)) {
            popup.getMenu().getItem(currentScale).setChecked(true);
        }
        if (!ListenerUtil.mutListener.listen(6180)) {
            popup.show();
        }
    }

    private void launchCamera() {
        if (!ListenerUtil.mutListener.listen(6182)) {
            if (ConfigUtils.requestCameraPermissions(this, null, PERMISSION_REQUEST_CAMERA)) {
                if (!ListenerUtil.mutListener.listen(6181)) {
                    reallyLaunchCamera();
                }
            }
        }
    }

    @SuppressLint("UnsupportedChromeOsCameraSystemFeature")
    private void reallyLaunchCamera() {
        File cameraFile = null;
        File videoFile = null;
        try {
            if (!ListenerUtil.mutListener.listen(6185)) {
                cameraFile = fileService.createTempFile(".camera", ".jpg", !ConfigUtils.useContentUris());
            }
            if (!ListenerUtil.mutListener.listen(6186)) {
                this.cameraFilePath = cameraFile.getCanonicalPath();
            }
            if (!ListenerUtil.mutListener.listen(6187)) {
                videoFile = fileService.createTempFile(".video", ".mp4", !ConfigUtils.useContentUris());
            }
            if (!ListenerUtil.mutListener.listen(6188)) {
                this.videoFilePath = videoFile.getCanonicalPath();
            }
        } catch (IOException e) {
            if (!ListenerUtil.mutListener.listen(6183)) {
                logger.error("Exception", e);
            }
            if (!ListenerUtil.mutListener.listen(6184)) {
                finish();
            }
        }
        final Intent cameraIntent;
        final int requestCode;
        if ((ListenerUtil.mutListener.listen(6189) ? (CameraUtil.isInternalCameraSupported() || !useExternalCamera) : (CameraUtil.isInternalCameraSupported() && !useExternalCamera))) {
            // use internal camera
            cameraIntent = new Intent(this, CameraActivity.class);
            if (!ListenerUtil.mutListener.listen(6197)) {
                cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, cameraFilePath);
            }
            if (!ListenerUtil.mutListener.listen(6198)) {
                cameraIntent.putExtra(CameraActivity.EXTRA_VIDEO_OUTPUT, videoFilePath);
            }
            requestCode = ThreemaActivity.ACTIVITY_ID_PICK_CAMERA_INTERNAL;
        } else {
            // use external camera
            PackageManager packageManager = getPackageManager();
            if (!ListenerUtil.mutListener.listen(6194)) {
                if ((ListenerUtil.mutListener.listen(6191) ? (packageManager == null && !((ListenerUtil.mutListener.listen(6190) ? (packageManager.hasSystemFeature(PackageManager.FEATURE_CAMERA) && packageManager.hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY)) : (packageManager.hasSystemFeature(PackageManager.FEATURE_CAMERA) || packageManager.hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY))))) : (packageManager == null || !((ListenerUtil.mutListener.listen(6190) ? (packageManager.hasSystemFeature(PackageManager.FEATURE_CAMERA) && packageManager.hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY)) : (packageManager.hasSystemFeature(PackageManager.FEATURE_CAMERA) || packageManager.hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY))))))) {
                    if (!ListenerUtil.mutListener.listen(6192)) {
                        Toast.makeText(getApplicationContext(), R.string.no_camera_installed, Toast.LENGTH_LONG).show();
                    }
                    if (!ListenerUtil.mutListener.listen(6193)) {
                        finish();
                    }
                    return;
                }
            }
            cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            if (!ListenerUtil.mutListener.listen(6195)) {
                cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, fileService.getShareFileUri(cameraFile, null));
            }
            if (!ListenerUtil.mutListener.listen(6196)) {
                cameraIntent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            }
            requestCode = ThreemaActivity.ACTIVITY_ID_PICK_CAMERA_EXTERNAL;
        }
        try {
            if (!ListenerUtil.mutListener.listen(6201)) {
                ConfigUtils.setRequestedOrientation(this, ConfigUtils.getCurrentScreenOrientation(this));
            }
            if (!ListenerUtil.mutListener.listen(6202)) {
                startActivityForResult(cameraIntent, requestCode);
            }
            if (!ListenerUtil.mutListener.listen(6203)) {
                overridePendingTransition(0, 0);
            }
        } catch (ActivityNotFoundException e) {
            if (!ListenerUtil.mutListener.listen(6199)) {
                logger.error("Exception", e);
            }
            if (!ListenerUtil.mutListener.listen(6200)) {
                finish();
            }
        }
    }

    private void addImage() {
        // FileUtil.selectFile(SendMediaActivity.this, null, "image/*", ThreemaActivity.ACTIVITY_ID_PICK_IMAGE, true, 0, null);
        Intent intent = new Intent(getApplicationContext(), MediaSelectionActivity.class);
        if (!ListenerUtil.mutListener.listen(6205)) {
            // pass last media filter to open the chooser with the same selection.
            if (lastMediaFilter != null) {
                if (!ListenerUtil.mutListener.listen(6204)) {
                    intent = IntentDataUtil.addLastMediaFilterToIntent(intent, this.lastMediaFilter);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(6206)) {
            startActivityForResult(intent, ThreemaActivity.ACTIVITY_ID_PICK_MEDIA);
        }
    }

    @Override
    protected void onResume() {
        if (!ListenerUtil.mutListener.listen(6207)) {
            super.onResume();
        }
        if (!ListenerUtil.mutListener.listen(6208)) {
            // load main image
            this.backgroundLayout = findViewById(R.id.background_layout);
        }
        if (!ListenerUtil.mutListener.listen(6211)) {
            if (this.backgroundLayout != null) {
                if (!ListenerUtil.mutListener.listen(6210)) {
                    this.backgroundLayout.post(new Runnable() {

                        @Override
                        public void run() {
                            if (!ListenerUtil.mutListener.listen(6209)) {
                                showBigImage(bigImagePos);
                            }
                        }
                    });
                }
            }
        }
    }

    @Override
    public int getLayoutResource() {
        return R.layout.activity_send_media;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        if (!ListenerUtil.mutListener.listen(6212)) {
            updateMenu();
        }
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!ListenerUtil.mutListener.listen(6213)) {
            getToolbar().setTitle(R.string.send_media);
        }
        if (!ListenerUtil.mutListener.listen(6214)) {
            getMenuInflater().inflate(R.menu.activity_send_media, menu);
        }
        if (!ListenerUtil.mutListener.listen(6215)) {
            settingsItem = menu.findItem(R.id.settings);
        }
        if (!ListenerUtil.mutListener.listen(6216)) {
            settingsItem.setOnMenuItemClickListener(item -> {
                new Handler().post(() -> {
                    final View v = findViewById(R.id.settings);
                    if (v != null) {
                        showSettingsDropDown(v, SendMediaActivity.this.mediaItems.get(bigImagePos));
                    }
                });
                return true;
            });
        }
        if (!ListenerUtil.mutListener.listen(6228)) {
            menu.findItem(R.id.flip).setOnMenuItemClickListener(new DebouncedOnMenuItemClickListener((ListenerUtil.mutListener.listen(6227) ? (IMAGE_ANIMATION_DURATION_MS % 2) : (ListenerUtil.mutListener.listen(6226) ? (IMAGE_ANIMATION_DURATION_MS / 2) : (ListenerUtil.mutListener.listen(6225) ? (IMAGE_ANIMATION_DURATION_MS - 2) : (ListenerUtil.mutListener.listen(6224) ? (IMAGE_ANIMATION_DURATION_MS + 2) : (IMAGE_ANIMATION_DURATION_MS * 2)))))) {

                @Override
                public boolean onDebouncedMenuItemClick(MenuItem item) {
                    if (!ListenerUtil.mutListener.listen(6223)) {
                        if ((ListenerUtil.mutListener.listen(6221) ? (bigImagePos >= SendMediaActivity.this.mediaItems.size()) : (ListenerUtil.mutListener.listen(6220) ? (bigImagePos <= SendMediaActivity.this.mediaItems.size()) : (ListenerUtil.mutListener.listen(6219) ? (bigImagePos > SendMediaActivity.this.mediaItems.size()) : (ListenerUtil.mutListener.listen(6218) ? (bigImagePos != SendMediaActivity.this.mediaItems.size()) : (ListenerUtil.mutListener.listen(6217) ? (bigImagePos == SendMediaActivity.this.mediaItems.size()) : (bigImagePos < SendMediaActivity.this.mediaItems.size()))))))) {
                            if (!ListenerUtil.mutListener.listen(6222)) {
                                prepareFlip();
                            }
                            return true;
                        }
                    }
                    return false;
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(6240)) {
            menu.findItem(R.id.rotate).setOnMenuItemClickListener(new DebouncedOnMenuItemClickListener((ListenerUtil.mutListener.listen(6239) ? (IMAGE_ANIMATION_DURATION_MS % 2) : (ListenerUtil.mutListener.listen(6238) ? (IMAGE_ANIMATION_DURATION_MS / 2) : (ListenerUtil.mutListener.listen(6237) ? (IMAGE_ANIMATION_DURATION_MS - 2) : (ListenerUtil.mutListener.listen(6236) ? (IMAGE_ANIMATION_DURATION_MS + 2) : (IMAGE_ANIMATION_DURATION_MS * 2)))))) {

                @Override
                public boolean onDebouncedMenuItemClick(MenuItem item) {
                    if (!ListenerUtil.mutListener.listen(6235)) {
                        if ((ListenerUtil.mutListener.listen(6233) ? (bigImagePos >= SendMediaActivity.this.mediaItems.size()) : (ListenerUtil.mutListener.listen(6232) ? (bigImagePos <= SendMediaActivity.this.mediaItems.size()) : (ListenerUtil.mutListener.listen(6231) ? (bigImagePos > SendMediaActivity.this.mediaItems.size()) : (ListenerUtil.mutListener.listen(6230) ? (bigImagePos != SendMediaActivity.this.mediaItems.size()) : (ListenerUtil.mutListener.listen(6229) ? (bigImagePos == SendMediaActivity.this.mediaItems.size()) : (bigImagePos < SendMediaActivity.this.mediaItems.size()))))))) {
                            if (!ListenerUtil.mutListener.listen(6234)) {
                                prepareRotate();
                            }
                            return true;
                        }
                    }
                    return false;
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(6241)) {
            menu.findItem(R.id.crop).setOnMenuItemClickListener(item -> {
                if (bigImagePos < SendMediaActivity.this.mediaItems.size()) {
                    cropImage();
                    return true;
                }
                return false;
            });
        }
        if (!ListenerUtil.mutListener.listen(6242)) {
            menu.findItem(R.id.edit).setOnMenuItemClickListener(item -> {
                if (bigImagePos < SendMediaActivity.this.mediaItems.size()) {
                    editImage();
                    return true;
                }
                return false;
            });
        }
        if (!ListenerUtil.mutListener.listen(6244)) {
            if (getToolbar().getNavigationIcon() != null) {
                if (!ListenerUtil.mutListener.listen(6243)) {
                    getToolbar().getNavigationIcon().setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_IN);
                }
            }
        }
        return super.onCreateOptionsMenu(menu);
    }

    private void prepareRotate() {
        if (!ListenerUtil.mutListener.listen(6245)) {
            if (bigImageView.getDrawable() == null) {
                return;
            }
        }
        int oldRotation = SendMediaActivity.this.mediaItems.get(bigImagePos).getRotation();
        int newRotation = (ListenerUtil.mutListener.listen(6253) ? (((ListenerUtil.mutListener.listen(6249) ? (oldRotation % 90) : (ListenerUtil.mutListener.listen(6248) ? (oldRotation / 90) : (ListenerUtil.mutListener.listen(6247) ? (oldRotation * 90) : (ListenerUtil.mutListener.listen(6246) ? (oldRotation + 90) : (oldRotation - 90)))))) / 360) : (ListenerUtil.mutListener.listen(6252) ? (((ListenerUtil.mutListener.listen(6249) ? (oldRotation % 90) : (ListenerUtil.mutListener.listen(6248) ? (oldRotation / 90) : (ListenerUtil.mutListener.listen(6247) ? (oldRotation * 90) : (ListenerUtil.mutListener.listen(6246) ? (oldRotation + 90) : (oldRotation - 90)))))) * 360) : (ListenerUtil.mutListener.listen(6251) ? (((ListenerUtil.mutListener.listen(6249) ? (oldRotation % 90) : (ListenerUtil.mutListener.listen(6248) ? (oldRotation / 90) : (ListenerUtil.mutListener.listen(6247) ? (oldRotation * 90) : (ListenerUtil.mutListener.listen(6246) ? (oldRotation + 90) : (oldRotation - 90)))))) - 360) : (ListenerUtil.mutListener.listen(6250) ? (((ListenerUtil.mutListener.listen(6249) ? (oldRotation % 90) : (ListenerUtil.mutListener.listen(6248) ? (oldRotation / 90) : (ListenerUtil.mutListener.listen(6247) ? (oldRotation * 90) : (ListenerUtil.mutListener.listen(6246) ? (oldRotation + 90) : (oldRotation - 90)))))) + 360) : (((ListenerUtil.mutListener.listen(6249) ? (oldRotation % 90) : (ListenerUtil.mutListener.listen(6248) ? (oldRotation / 90) : (ListenerUtil.mutListener.listen(6247) ? (oldRotation * 90) : (ListenerUtil.mutListener.listen(6246) ? (oldRotation + 90) : (oldRotation - 90)))))) % 360)))));
        int height = bigImageView.getDrawable().getBounds().width();
        int width = bigImageView.getDrawable().getBounds().height();
        float screenAspectRatio = (ListenerUtil.mutListener.listen(6257) ? ((float) parentWidth % (float) parentHeight) : (ListenerUtil.mutListener.listen(6256) ? ((float) parentWidth * (float) parentHeight) : (ListenerUtil.mutListener.listen(6255) ? ((float) parentWidth - (float) parentHeight) : (ListenerUtil.mutListener.listen(6254) ? ((float) parentWidth + (float) parentHeight) : ((float) parentWidth / (float) parentHeight)))));
        float imageAspectRatio = (ListenerUtil.mutListener.listen(6261) ? ((float) width % (float) height) : (ListenerUtil.mutListener.listen(6260) ? ((float) width * (float) height) : (ListenerUtil.mutListener.listen(6259) ? ((float) width - (float) height) : (ListenerUtil.mutListener.listen(6258) ? ((float) width + (float) height) : ((float) width / (float) height)))));
        float scalingFactor;
        if ((ListenerUtil.mutListener.listen(6266) ? (screenAspectRatio >= imageAspectRatio) : (ListenerUtil.mutListener.listen(6265) ? (screenAspectRatio <= imageAspectRatio) : (ListenerUtil.mutListener.listen(6264) ? (screenAspectRatio < imageAspectRatio) : (ListenerUtil.mutListener.listen(6263) ? (screenAspectRatio != imageAspectRatio) : (ListenerUtil.mutListener.listen(6262) ? (screenAspectRatio == imageAspectRatio) : (screenAspectRatio > imageAspectRatio))))))) {
            scalingFactor = (ListenerUtil.mutListener.listen(6274) ? ((float) parentHeight % (float) height) : (ListenerUtil.mutListener.listen(6273) ? ((float) parentHeight * (float) height) : (ListenerUtil.mutListener.listen(6272) ? ((float) parentHeight - (float) height) : (ListenerUtil.mutListener.listen(6271) ? ((float) parentHeight + (float) height) : ((float) parentHeight / (float) height)))));
        } else {
            scalingFactor = (ListenerUtil.mutListener.listen(6270) ? ((float) parentWidth % (float) width) : (ListenerUtil.mutListener.listen(6269) ? ((float) parentWidth * (float) width) : (ListenerUtil.mutListener.listen(6268) ? ((float) parentWidth - (float) width) : (ListenerUtil.mutListener.listen(6267) ? ((float) parentWidth + (float) width) : ((float) parentWidth / (float) width)))));
        }
        if (!ListenerUtil.mutListener.listen(6279)) {
            bigImageView.animate().rotationBy(-90f).scaleX(scalingFactor).scaleY(scalingFactor).setDuration(IMAGE_ANIMATION_DURATION_MS).setInterpolator(new FastOutSlowInInterpolator()).setListener(new Animator.AnimatorListener() {

                @Override
                public void onAnimationStart(Animator animation) {
                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    if (!ListenerUtil.mutListener.listen(6275)) {
                        SendMediaActivity.this.mediaItems.get(bigImagePos).setRotation(newRotation);
                    }
                    if (!ListenerUtil.mutListener.listen(6276)) {
                        showBigImage(bigImagePos, false);
                    }
                    if (!ListenerUtil.mutListener.listen(6277)) {
                        sendMediaGridAdapter.notifyDataSetChanged();
                    }
                    if (!ListenerUtil.mutListener.listen(6278)) {
                        hasChanges = true;
                    }
                }

                @Override
                public void onAnimationCancel(Animator animation) {
                }

                @Override
                public void onAnimationRepeat(Animator animation) {
                }
            });
        }
    }

    private void prepareFlip() {
        if (!ListenerUtil.mutListener.listen(6280)) {
            if (bigImageView.getDrawable() == null) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(6285)) {
            bigImageView.animate().rotationY(180f).setDuration(IMAGE_ANIMATION_DURATION_MS).setInterpolator(new FastOutSlowInInterpolator()).setListener(new Animator.AnimatorListener() {

                @Override
                public void onAnimationStart(Animator animation) {
                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    if (!ListenerUtil.mutListener.listen(6281)) {
                        flip(SendMediaActivity.this.mediaItems.get(bigImagePos));
                    }
                    if (!ListenerUtil.mutListener.listen(6282)) {
                        showBigImage(bigImagePos, false);
                    }
                    if (!ListenerUtil.mutListener.listen(6283)) {
                        sendMediaGridAdapter.notifyDataSetChanged();
                    }
                    if (!ListenerUtil.mutListener.listen(6284)) {
                        hasChanges = true;
                    }
                }

                @Override
                public void onAnimationCancel(Animator animation) {
                }

                @Override
                public void onAnimationRepeat(Animator animation) {
                }
            });
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (!ListenerUtil.mutListener.listen(6287)) {
            switch(item.getItemId()) {
                case android.R.id.home:
                    if (!ListenerUtil.mutListener.listen(6286)) {
                        confirmQuit();
                    }
                    break;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private void flip(MediaItem item) {
        int currentFlip = mediaItems.get(bigImagePos).getFlip();
        if (!ListenerUtil.mutListener.listen(6295)) {
            if ((ListenerUtil.mutListener.listen(6288) ? (item.getRotation() == 90 && item.getRotation() == 270) : (item.getRotation() == 90 || item.getRotation() == 270))) {
                if (!ListenerUtil.mutListener.listen(6294)) {
                    if ((currentFlip & FLIP_VERTICAL) == FLIP_VERTICAL) {
                        if (!ListenerUtil.mutListener.listen(6293)) {
                            // clear vertical flag
                            currentFlip &= ~FLIP_VERTICAL;
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(6292)) {
                            currentFlip |= FLIP_VERTICAL;
                        }
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(6291)) {
                    if ((currentFlip & FLIP_HORIZONTAL) == FLIP_HORIZONTAL) {
                        if (!ListenerUtil.mutListener.listen(6290)) {
                            // clear horizontal flag
                            currentFlip &= ~FLIP_HORIZONTAL;
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(6289)) {
                            currentFlip |= FLIP_HORIZONTAL;
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(6296)) {
            mediaItems.get(bigImagePos).setFlip(currentFlip);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (!ListenerUtil.mutListener.listen(6299)) {
            if (this.sendMediaGridAdapter.getItemViewType(position) == VIEW_TYPE_ADD) {
                if (!ListenerUtil.mutListener.listen(6298)) {
                    addImage();
                }
            } else {
                if (!ListenerUtil.mutListener.listen(6297)) {
                    showBigImage(position);
                }
            }
        }
    }

    @SuppressLint("StaticFieldLeak")
    private void addItemsByUriList(List<Uri> uriList) {
        if (!ListenerUtil.mutListener.listen(6344)) {
            if ((ListenerUtil.mutListener.listen(6304) ? (uriList.size() >= 0) : (ListenerUtil.mutListener.listen(6303) ? (uriList.size() <= 0) : (ListenerUtil.mutListener.listen(6302) ? (uriList.size() < 0) : (ListenerUtil.mutListener.listen(6301) ? (uriList.size() != 0) : (ListenerUtil.mutListener.listen(6300) ? (uriList.size() == 0) : (uriList.size() > 0))))))) {
                if (!ListenerUtil.mutListener.listen(6343)) {
                    new AsyncTask<Void, Void, List<MediaItem>>() {

                        boolean capacityExceeded = false;

                        @Override
                        protected void onPreExecute() {
                            if (!ListenerUtil.mutListener.listen(6311)) {
                                if ((ListenerUtil.mutListener.listen(6309) ? (mediaItems.size() + uriList.size() >= MAX_SELECTABLE_IMAGES) : (ListenerUtil.mutListener.listen(6308) ? (mediaItems.size() + uriList.size() <= MAX_SELECTABLE_IMAGES) : (ListenerUtil.mutListener.listen(6307) ? (mediaItems.size() + uriList.size() < MAX_SELECTABLE_IMAGES) : (ListenerUtil.mutListener.listen(6306) ? (mediaItems.size() + uriList.size() != MAX_SELECTABLE_IMAGES) : (ListenerUtil.mutListener.listen(6305) ? (mediaItems.size() + uriList.size() == MAX_SELECTABLE_IMAGES) : (mediaItems.size() + uriList.size() > MAX_SELECTABLE_IMAGES))))))) {
                                    if (!ListenerUtil.mutListener.listen(6310)) {
                                        Snackbar.make((View) gridView.getParent(), String.format(getString(R.string.max_images_reached), MAX_SELECTABLE_IMAGES), Snackbar.LENGTH_LONG).show();
                                    }
                                }
                            }
                        }

                        @Override
                        protected List<MediaItem> doInBackground(Void... voids) {
                            List<MediaItem> itemList = new ArrayList<>();
                            int numExistingItems = mediaItems.size();
                            if (!ListenerUtil.mutListener.listen(6331)) {
                                {
                                    long _loopCounter52 = 0;
                                    for (Uri uri : uriList) {
                                        ListenerUtil.loopListener.listen("_loopCounter52", ++_loopCounter52);
                                        if (!ListenerUtil.mutListener.listen(6330)) {
                                            if (uri != null) {
                                                if (!ListenerUtil.mutListener.listen(6313)) {
                                                    if ((ListenerUtil.mutListener.listen(6312) ? (isDuplicate(mediaItems, uri) && isDuplicate(itemList, uri)) : (isDuplicate(mediaItems, uri) || isDuplicate(itemList, uri)))) {
                                                        continue;
                                                    }
                                                }
                                                if (!ListenerUtil.mutListener.listen(6320)) {
                                                    if ((ListenerUtil.mutListener.listen(6318) ? (numExistingItems + itemList.size() <= MAX_SELECTABLE_IMAGES) : (ListenerUtil.mutListener.listen(6317) ? (numExistingItems + itemList.size() > MAX_SELECTABLE_IMAGES) : (ListenerUtil.mutListener.listen(6316) ? (numExistingItems + itemList.size() < MAX_SELECTABLE_IMAGES) : (ListenerUtil.mutListener.listen(6315) ? (numExistingItems + itemList.size() != MAX_SELECTABLE_IMAGES) : (ListenerUtil.mutListener.listen(6314) ? (numExistingItems + itemList.size() == MAX_SELECTABLE_IMAGES) : (numExistingItems + itemList.size() >= MAX_SELECTABLE_IMAGES))))))) {
                                                        if (!ListenerUtil.mutListener.listen(6319)) {
                                                            capacityExceeded = true;
                                                        }
                                                        break;
                                                    }
                                                }
                                                Uri fixedUri = FileUtil.getFixedContentUri(getApplicationContext(), uri);
                                                String typeUtil = FileUtil.getMimeTypeFromUri(getApplicationContext(), fixedUri);
                                                int type;
                                                if (MimeUtil.isVideoFile(typeUtil)) {
                                                    type = MediaItem.TYPE_VIDEO;
                                                } else if (MimeUtil.isGifFile(typeUtil)) {
                                                    type = MediaItem.TYPE_GIF;
                                                } else {
                                                    type = MediaItem.TYPE_IMAGE;
                                                }
                                                if (!ListenerUtil.mutListener.listen(6321)) {
                                                    logger.debug("type is ");
                                                }
                                                BitmapUtil.ExifOrientation exifOrientation = BitmapUtil.getExifOrientation(getApplicationContext(), fixedUri);
                                                MediaItem mediaItem = new MediaItem(fixedUri, type);
                                                if (!ListenerUtil.mutListener.listen(6322)) {
                                                    mediaItem.setExifRotation((int) exifOrientation.getRotation());
                                                }
                                                if (!ListenerUtil.mutListener.listen(6323)) {
                                                    mediaItem.setExifFlip(exifOrientation.getFlip());
                                                }
                                                if (!ListenerUtil.mutListener.listen(6324)) {
                                                    mediaItem.setCaption("");
                                                }
                                                if (!ListenerUtil.mutListener.listen(6328)) {
                                                    if (MimeUtil.isVideoFile(typeUtil)) {
                                                        // do not use automatic resource management on MediaMetadataRetriever
                                                        MediaMetadataRetriever metaDataRetriever = new MediaMetadataRetriever();
                                                        try {
                                                            if (!ListenerUtil.mutListener.listen(6326)) {
                                                                metaDataRetriever.setDataSource(ThreemaApplication.getAppContext(), mediaItem.getUri());
                                                            }
                                                            if (!ListenerUtil.mutListener.listen(6327)) {
                                                                mediaItem.setDurationMs(Integer.parseInt(metaDataRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION)));
                                                            }
                                                        } catch (Exception ignored) {
                                                        } finally {
                                                            if (!ListenerUtil.mutListener.listen(6325)) {
                                                                metaDataRetriever.release();
                                                            }
                                                        }
                                                    }
                                                }
                                                if (!ListenerUtil.mutListener.listen(6329)) {
                                                    itemList.add(mediaItem);
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                            return itemList;
                        }

                        @Override
                        protected void onPostExecute(List<MediaItem> itemList) {
                            if (!ListenerUtil.mutListener.listen(6333)) {
                                if (sendMediaGridAdapter != null) {
                                    if (!ListenerUtil.mutListener.listen(6332)) {
                                        sendMediaGridAdapter.add(itemList);
                                    }
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(6334)) {
                                mediaItems.addAll(itemList);
                            }
                            if (!ListenerUtil.mutListener.listen(6336)) {
                                if (capacityExceeded) {
                                    if (!ListenerUtil.mutListener.listen(6335)) {
                                        Snackbar.make((View) gridView.getParent(), String.format(getString(R.string.max_images_reached), MAX_SELECTABLE_IMAGES), Snackbar.LENGTH_LONG).show();
                                    }
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(6337)) {
                                updateMenu();
                            }
                            if (!ListenerUtil.mutListener.listen(6342)) {
                                showBigImage((ListenerUtil.mutListener.listen(6341) ? (mediaItems.size() % 1) : (ListenerUtil.mutListener.listen(6340) ? (mediaItems.size() / 1) : (ListenerUtil.mutListener.listen(6339) ? (mediaItems.size() * 1) : (ListenerUtil.mutListener.listen(6338) ? (mediaItems.size() + 1) : (mediaItems.size() - 1))))));
                            }
                        }
                    }.execute();
                }
            }
        }
    }

    @SuppressLint("StaticFieldLeak")
    private void addItemsByMediaItem(List<MediaItem> incomingMediaItems) {
        if (!ListenerUtil.mutListener.listen(6375)) {
            if ((ListenerUtil.mutListener.listen(6349) ? (incomingMediaItems.size() >= 0) : (ListenerUtil.mutListener.listen(6348) ? (incomingMediaItems.size() <= 0) : (ListenerUtil.mutListener.listen(6347) ? (incomingMediaItems.size() < 0) : (ListenerUtil.mutListener.listen(6346) ? (incomingMediaItems.size() != 0) : (ListenerUtil.mutListener.listen(6345) ? (incomingMediaItems.size() == 0) : (incomingMediaItems.size() > 0))))))) {
                if (!ListenerUtil.mutListener.listen(6374)) {
                    new AsyncTask<Void, Void, List<MediaItem>>() {

                        @Override
                        protected List<MediaItem> doInBackground(Void... voids) {
                            if (!ListenerUtil.mutListener.listen(6357)) {
                                {
                                    long _loopCounter53 = 0;
                                    for (MediaItem incomingMediaItem : incomingMediaItems) {
                                        ListenerUtil.loopListener.listen("_loopCounter53", ++_loopCounter53);
                                        if (!ListenerUtil.mutListener.listen(6356)) {
                                            if (incomingMediaItem.getUri() != null) {
                                                BitmapUtil.ExifOrientation exifOrientation = BitmapUtil.getExifOrientation(getApplicationContext(), incomingMediaItem.getUri());
                                                if (!ListenerUtil.mutListener.listen(6350)) {
                                                    incomingMediaItem.setExifRotation((int) exifOrientation.getRotation());
                                                }
                                                if (!ListenerUtil.mutListener.listen(6351)) {
                                                    incomingMediaItem.setExifFlip(exifOrientation.getFlip());
                                                }
                                                if (!ListenerUtil.mutListener.listen(6355)) {
                                                    if (MimeUtil.isVideoFile(incomingMediaItem.getMimeType())) {
                                                        // do not use automatic resource management on MediaMetadataRetriever
                                                        MediaMetadataRetriever metaDataRetriever = new MediaMetadataRetriever();
                                                        try {
                                                            if (!ListenerUtil.mutListener.listen(6353)) {
                                                                metaDataRetriever.setDataSource(ThreemaApplication.getAppContext(), incomingMediaItem.getUri());
                                                            }
                                                            if (!ListenerUtil.mutListener.listen(6354)) {
                                                                incomingMediaItem.setDurationMs(Integer.parseInt(metaDataRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION)));
                                                            }
                                                        } catch (Exception ignored) {
                                                        } finally {
                                                            if (!ListenerUtil.mutListener.listen(6352)) {
                                                                metaDataRetriever.release();
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                            return incomingMediaItems;
                        }

                        @Override
                        protected void onPostExecute(List<MediaItem> itemList) {
                            if (!ListenerUtil.mutListener.listen(6373)) {
                                if ((ListenerUtil.mutListener.listen(6362) ? (mediaItems.size() + itemList.size() >= MAX_SELECTABLE_IMAGES) : (ListenerUtil.mutListener.listen(6361) ? (mediaItems.size() + itemList.size() <= MAX_SELECTABLE_IMAGES) : (ListenerUtil.mutListener.listen(6360) ? (mediaItems.size() + itemList.size() < MAX_SELECTABLE_IMAGES) : (ListenerUtil.mutListener.listen(6359) ? (mediaItems.size() + itemList.size() != MAX_SELECTABLE_IMAGES) : (ListenerUtil.mutListener.listen(6358) ? (mediaItems.size() + itemList.size() == MAX_SELECTABLE_IMAGES) : (mediaItems.size() + itemList.size() > MAX_SELECTABLE_IMAGES))))))) {
                                    if (!ListenerUtil.mutListener.listen(6372)) {
                                        Snackbar.make((View) gridView.getParent(), String.format(getString(R.string.max_images_reached), MAX_SELECTABLE_IMAGES), Snackbar.LENGTH_LONG).show();
                                    }
                                } else {
                                    if (!ListenerUtil.mutListener.listen(6364)) {
                                        if (sendMediaGridAdapter != null) {
                                            if (!ListenerUtil.mutListener.listen(6363)) {
                                                sendMediaGridAdapter.add(itemList);
                                            }
                                        }
                                    }
                                    if (!ListenerUtil.mutListener.listen(6365)) {
                                        mediaItems.addAll(itemList);
                                    }
                                    if (!ListenerUtil.mutListener.listen(6366)) {
                                        updateMenu();
                                    }
                                    if (!ListenerUtil.mutListener.listen(6371)) {
                                        showBigImage((ListenerUtil.mutListener.listen(6370) ? (mediaItems.size() % 1) : (ListenerUtil.mutListener.listen(6369) ? (mediaItems.size() / 1) : (ListenerUtil.mutListener.listen(6368) ? (mediaItems.size() * 1) : (ListenerUtil.mutListener.listen(6367) ? (mediaItems.size() + 1) : (mediaItems.size() - 1))))));
                                    }
                                }
                            }
                        }
                    }.execute();
                }
            }
        }
    }

    @SuppressLint("NewApi")
    @Override
    public void onActivityResult(int requestCode, int resultCode, final Intent intent) {
        if (!ListenerUtil.mutListener.listen(6426)) {
            if (resultCode == Activity.RESULT_OK) {
                if (!ListenerUtil.mutListener.listen(6383)) {
                    hasChanges = true;
                }
                if (!ListenerUtil.mutListener.listen(6425)) {
                    switch(requestCode) {
                        case CropImageActivity.REQUEST_CROP:
                        case ThreemaActivity.ACTIVITY_ID_PAINT:
                            if (!ListenerUtil.mutListener.listen(6391)) {
                                backgroundLayout.post(new Runnable() {

                                    @Override
                                    public void run() {
                                        if (!ListenerUtil.mutListener.listen(6384)) {
                                            sendMediaGridAdapter.remove(mediaItems.get(bigImagePos));
                                        }
                                        if (!ListenerUtil.mutListener.listen(6385)) {
                                            mediaItems.get(bigImagePos).setUri(Uri.fromFile(cropFile));
                                        }
                                        if (!ListenerUtil.mutListener.listen(6386)) {
                                            mediaItems.get(bigImagePos).setRotation(0);
                                        }
                                        if (!ListenerUtil.mutListener.listen(6387)) {
                                            mediaItems.get(bigImagePos).setExifRotation(0);
                                        }
                                        if (!ListenerUtil.mutListener.listen(6388)) {
                                            mediaItems.get(bigImagePos).setFlip(BitmapUtil.FLIP_NONE);
                                        }
                                        if (!ListenerUtil.mutListener.listen(6389)) {
                                            mediaItems.get(bigImagePos).setExifFlip(BitmapUtil.FLIP_NONE);
                                        }
                                        if (!ListenerUtil.mutListener.listen(6390)) {
                                            sendMediaGridAdapter.add(bigImagePos, mediaItems.get(bigImagePos));
                                        }
                                    }
                                });
                            }
                            break;
                        case ThreemaActivity.ACTIVITY_ID_PICK_CAMERA_EXTERNAL:
                        case ThreemaActivity.ACTIVITY_ID_PICK_CAMERA_INTERNAL:
                            if (!ListenerUtil.mutListener.listen(6392)) {
                                ConfigUtils.setRequestedOrientation(this, ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);
                            }
                            if (!ListenerUtil.mutListener.listen(6414)) {
                                if ((ListenerUtil.mutListener.listen(6394) ? ((ListenerUtil.mutListener.listen(6393) ? (ConfigUtils.supportsVideoCapture() || intent != null) : (ConfigUtils.supportsVideoCapture() && intent != null)) || intent.getBooleanExtra(CameraActivity.EXTRA_VIDEO_RESULT, false)) : ((ListenerUtil.mutListener.listen(6393) ? (ConfigUtils.supportsVideoCapture() || intent != null) : (ConfigUtils.supportsVideoCapture() && intent != null)) && intent.getBooleanExtra(CameraActivity.EXTRA_VIDEO_RESULT, false)))) {
                                    if (!ListenerUtil.mutListener.listen(6413)) {
                                        // it's a video file
                                        if (!TestUtil.empty(this.videoFilePath)) {
                                            File videoFile = new File(this.videoFilePath);
                                            if (!ListenerUtil.mutListener.listen(6412)) {
                                                if ((ListenerUtil.mutListener.listen(6409) ? (videoFile.exists() || (ListenerUtil.mutListener.listen(6408) ? (videoFile.length() >= 0) : (ListenerUtil.mutListener.listen(6407) ? (videoFile.length() <= 0) : (ListenerUtil.mutListener.listen(6406) ? (videoFile.length() < 0) : (ListenerUtil.mutListener.listen(6405) ? (videoFile.length() != 0) : (ListenerUtil.mutListener.listen(6404) ? (videoFile.length() == 0) : (videoFile.length() > 0))))))) : (videoFile.exists() && (ListenerUtil.mutListener.listen(6408) ? (videoFile.length() >= 0) : (ListenerUtil.mutListener.listen(6407) ? (videoFile.length() <= 0) : (ListenerUtil.mutListener.listen(6406) ? (videoFile.length() < 0) : (ListenerUtil.mutListener.listen(6405) ? (videoFile.length() != 0) : (ListenerUtil.mutListener.listen(6404) ? (videoFile.length() == 0) : (videoFile.length() > 0))))))))) {
                                                    final Uri videoUri = Uri.fromFile(videoFile);
                                                    if (!ListenerUtil.mutListener.listen(6411)) {
                                                        if (videoUri != null) {
                                                            final int position = addItemFromCamera(MediaItem.TYPE_VIDEO_CAM, videoUri, null);
                                                            if (!ListenerUtil.mutListener.listen(6410)) {
                                                                showBigImage(position);
                                                            }
                                                            break;
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                } else {
                                    if (!ListenerUtil.mutListener.listen(6403)) {
                                        if (!TestUtil.empty(this.cameraFilePath)) {
                                            final Uri cameraUri = Uri.fromFile(new File(this.cameraFilePath));
                                            if (!ListenerUtil.mutListener.listen(6402)) {
                                                if (cameraUri != null) {
                                                    BitmapUtil.ExifOrientation exifOrientation = null;
                                                    if (!ListenerUtil.mutListener.listen(6400)) {
                                                        if (requestCode == ThreemaActivity.ACTIVITY_ID_PICK_CAMERA_EXTERNAL) {
                                                            if (!ListenerUtil.mutListener.listen(6399)) {
                                                                exifOrientation = BitmapUtil.getExifOrientation(this, cameraUri);
                                                            }
                                                        } else {
                                                            if (!ListenerUtil.mutListener.listen(6396)) {
                                                                if (bigImageView != null) {
                                                                    if (!ListenerUtil.mutListener.listen(6395)) {
                                                                        bigImageView.setVisibility(View.GONE);
                                                                    }
                                                                }
                                                            }
                                                            if (!ListenerUtil.mutListener.listen(6398)) {
                                                                if (bigGifImageView != null) {
                                                                    if (!ListenerUtil.mutListener.listen(6397)) {
                                                                        bigGifImageView.setVisibility(View.GONE);
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                    final int position = addItemFromCamera(MediaItem.TYPE_IMAGE_CAM, cameraUri, exifOrientation);
                                                    if (!ListenerUtil.mutListener.listen(6401)) {
                                                        showBigImage(position);
                                                    }
                                                    break;
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(6421)) {
                                if ((ListenerUtil.mutListener.listen(6419) ? (mediaItems.size() >= 0) : (ListenerUtil.mutListener.listen(6418) ? (mediaItems.size() > 0) : (ListenerUtil.mutListener.listen(6417) ? (mediaItems.size() < 0) : (ListenerUtil.mutListener.listen(6416) ? (mediaItems.size() != 0) : (ListenerUtil.mutListener.listen(6415) ? (mediaItems.size() == 0) : (mediaItems.size() <= 0))))))) {
                                    if (!ListenerUtil.mutListener.listen(6420)) {
                                        finish();
                                    }
                                }
                            }
                            break;
                        case ThreemaActivity.ACTIVITY_ID_PICK_MEDIA:
                            ArrayList<MediaItem> mediaItemsList = intent.getParcelableArrayListExtra(EXTRA_MEDIA_ITEMS);
                            if (!ListenerUtil.mutListener.listen(6423)) {
                                if (mediaItemsList != null) {
                                    if (!ListenerUtil.mutListener.listen(6422)) {
                                        addItemsByMediaItem(mediaItemsList);
                                    }
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(6424)) {
                                // update last media filter used to add media items.
                                this.lastMediaFilter = IntentDataUtil.getLastMediaFilterFromIntent(intent);
                            }
                        default:
                            break;
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(6382)) {
                    if ((ListenerUtil.mutListener.listen(6380) ? (mediaItems.size() >= 0) : (ListenerUtil.mutListener.listen(6379) ? (mediaItems.size() > 0) : (ListenerUtil.mutListener.listen(6378) ? (mediaItems.size() < 0) : (ListenerUtil.mutListener.listen(6377) ? (mediaItems.size() != 0) : (ListenerUtil.mutListener.listen(6376) ? (mediaItems.size() == 0) : (mediaItems.size() <= 0))))))) {
                        if (!ListenerUtil.mutListener.listen(6381)) {
                            finish();
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(6427)) {
            super.onActivityResult(requestCode, resultCode, intent);
        }
    }

    @UiThread
    private void sendMedia() {
        if (!ListenerUtil.mutListener.listen(6433)) {
            if ((ListenerUtil.mutListener.listen(6432) ? (mediaItems.size() >= 1) : (ListenerUtil.mutListener.listen(6431) ? (mediaItems.size() <= 1) : (ListenerUtil.mutListener.listen(6430) ? (mediaItems.size() > 1) : (ListenerUtil.mutListener.listen(6429) ? (mediaItems.size() != 1) : (ListenerUtil.mutListener.listen(6428) ? (mediaItems.size() == 1) : (mediaItems.size() < 1))))))) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(6434)) {
            messageService.sendMediaAsync(mediaItems, messageReceivers, null);
        }
        if (!ListenerUtil.mutListener.listen(6437)) {
            // return last media filter to chat via intermediate hop through MediaAttachActivity
            if (lastMediaFilter != null) {
                Intent lastMediaSelectionResult = IntentDataUtil.addLastMediaFilterToIntent(new Intent(), this.lastMediaFilter);
                if (!ListenerUtil.mutListener.listen(6436)) {
                    setResult(RESULT_OK, lastMediaSelectionResult);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(6435)) {
                    setResult(RESULT_OK);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(6438)) {
            finish();
        }
    }

    private void removeItem(MediaItem item) {
        if (!ListenerUtil.mutListener.listen(6440)) {
            if (sendMediaGridAdapter != null) {
                if (!ListenerUtil.mutListener.listen(6439)) {
                    sendMediaGridAdapter.remove(item);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(6441)) {
            mediaItems.remove(item);
        }
        if (!ListenerUtil.mutListener.listen(6450)) {
            if ((ListenerUtil.mutListener.listen(6446) ? (mediaItems.size() >= 0) : (ListenerUtil.mutListener.listen(6445) ? (mediaItems.size() <= 0) : (ListenerUtil.mutListener.listen(6444) ? (mediaItems.size() < 0) : (ListenerUtil.mutListener.listen(6443) ? (mediaItems.size() != 0) : (ListenerUtil.mutListener.listen(6442) ? (mediaItems.size() == 0) : (mediaItems.size() > 0))))))) {
                int newSelectedItem = 0;
                if (!ListenerUtil.mutListener.listen(6448)) {
                    showBigImage(newSelectedItem);
                }
                if (!ListenerUtil.mutListener.listen(6449)) {
                    updateMenu();
                }
            } else {
                if (!ListenerUtil.mutListener.listen(6447)) {
                    // no items left - goodbye
                    finish();
                }
            }
        }
    }

    @UiThread
    private int addItemFromCamera(int type, @NonNull Uri imageUri, BitmapUtil.ExifOrientation exifOrientation) {
        if (!ListenerUtil.mutListener.listen(6457)) {
            if ((ListenerUtil.mutListener.listen(6455) ? (mediaItems.size() <= MAX_SELECTABLE_IMAGES) : (ListenerUtil.mutListener.listen(6454) ? (mediaItems.size() > MAX_SELECTABLE_IMAGES) : (ListenerUtil.mutListener.listen(6453) ? (mediaItems.size() < MAX_SELECTABLE_IMAGES) : (ListenerUtil.mutListener.listen(6452) ? (mediaItems.size() != MAX_SELECTABLE_IMAGES) : (ListenerUtil.mutListener.listen(6451) ? (mediaItems.size() == MAX_SELECTABLE_IMAGES) : (mediaItems.size() >= MAX_SELECTABLE_IMAGES))))))) {
                if (!ListenerUtil.mutListener.listen(6456)) {
                    Snackbar.make((View) gridView.getParent(), String.format(getString(R.string.max_images_reached), MAX_SELECTABLE_IMAGES), Snackbar.LENGTH_LONG).show();
                }
            }
        }
        MediaItem item = new MediaItem(imageUri, type);
        if (!ListenerUtil.mutListener.listen(6460)) {
            if (exifOrientation != null) {
                if (!ListenerUtil.mutListener.listen(6458)) {
                    item.setExifRotation((int) exifOrientation.getRotation());
                }
                if (!ListenerUtil.mutListener.listen(6459)) {
                    item.setExifFlip(exifOrientation.getFlip());
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(6463)) {
            if (type == MediaItem.TYPE_VIDEO_CAM) {
                if (!ListenerUtil.mutListener.listen(6462)) {
                    item.setMimeType(MimeUtil.MIME_TYPE_VIDEO_MP4);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(6461)) {
                    item.setMimeType(MimeUtil.MIME_TYPE_IMAGE_JPG);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(6465)) {
            if (ContentResolver.SCHEME_FILE.equalsIgnoreCase(imageUri.getScheme())) {
                if (!ListenerUtil.mutListener.listen(6464)) {
                    item.setDeleteAfterUse(true);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(6467)) {
            if (sendMediaGridAdapter != null) {
                if (!ListenerUtil.mutListener.listen(6466)) {
                    sendMediaGridAdapter.add(item);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(6468)) {
            mediaItems.add(item);
        }
        return (ListenerUtil.mutListener.listen(6472) ? (mediaItems.size() % 1) : (ListenerUtil.mutListener.listen(6471) ? (mediaItems.size() / 1) : (ListenerUtil.mutListener.listen(6470) ? (mediaItems.size() * 1) : (ListenerUtil.mutListener.listen(6469) ? (mediaItems.size() + 1) : (mediaItems.size() - 1)))));
    }

    private void cropImage() {
        Uri imageUri = mediaItems.get(bigImagePos).getUri();
        try {
            if (!ListenerUtil.mutListener.listen(6474)) {
                cropFile = fileService.createTempFile(".crop", ".png");
            }
            Intent intent = new Intent(this, CropImageActivity.class);
            if (!ListenerUtil.mutListener.listen(6475)) {
                intent.setData(imageUri);
            }
            if (!ListenerUtil.mutListener.listen(6476)) {
                intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(cropFile));
            }
            if (!ListenerUtil.mutListener.listen(6477)) {
                intent.putExtra(ThreemaApplication.EXTRA_ORIENTATION, mediaItems.get(bigImagePos).getRotation());
            }
            if (!ListenerUtil.mutListener.listen(6478)) {
                intent.putExtra(ThreemaApplication.EXTRA_FLIP, mediaItems.get(bigImagePos).getFlip());
            }
            if (!ListenerUtil.mutListener.listen(6479)) {
                intent.putExtra(ThreemaApplication.EXTRA_EXIF_ORIENTATION, mediaItems.get(bigImagePos).getExifRotation());
            }
            if (!ListenerUtil.mutListener.listen(6480)) {
                intent.putExtra(ThreemaApplication.EXTRA_EXIF_FLIP, mediaItems.get(bigImagePos).getExifFlip());
            }
            if (!ListenerUtil.mutListener.listen(6481)) {
                intent.putExtra(CropImageActivity.FORCE_DARK_THEME, true);
            }
            if (!ListenerUtil.mutListener.listen(6482)) {
                startActivityForResult(intent, CropImageActivity.REQUEST_CROP);
            }
            if (!ListenerUtil.mutListener.listen(6483)) {
                overridePendingTransition(R.anim.medium_fade_in, R.anim.medium_fade_out);
            }
        } catch (IOException e) {
            if (!ListenerUtil.mutListener.listen(6473)) {
                logger.debug("Unable to create temp file for crop");
            }
        }
    }

    private void editImage() {
        try {
            if (!ListenerUtil.mutListener.listen(6485)) {
                cropFile = fileService.createTempFile(".edit", ".png");
            }
            Intent intent = new Intent(this, ImagePaintActivity.class);
            if (!ListenerUtil.mutListener.listen(6486)) {
                intent.putExtra(Intent.EXTRA_STREAM, mediaItems.get(bigImagePos));
            }
            if (!ListenerUtil.mutListener.listen(6487)) {
                intent.putExtra(ThreemaApplication.EXTRA_OUTPUT_FILE, Uri.fromFile(cropFile));
            }
            if (!ListenerUtil.mutListener.listen(6488)) {
                intent.putExtra(ThreemaApplication.EXTRA_ORIENTATION, mediaItems.get(bigImagePos).getRotation());
            }
            if (!ListenerUtil.mutListener.listen(6489)) {
                intent.putExtra(ThreemaApplication.EXTRA_FLIP, mediaItems.get(bigImagePos).getFlip());
            }
            if (!ListenerUtil.mutListener.listen(6490)) {
                intent.putExtra(ThreemaApplication.EXTRA_EXIF_ORIENTATION, mediaItems.get(bigImagePos).getExifRotation());
            }
            if (!ListenerUtil.mutListener.listen(6491)) {
                intent.putExtra(ThreemaApplication.EXTRA_EXIF_FLIP, mediaItems.get(bigImagePos).getExifFlip());
            }
            if (!ListenerUtil.mutListener.listen(6492)) {
                startActivityForResult(intent, ThreemaActivity.ACTIVITY_ID_PAINT);
            }
            if (!ListenerUtil.mutListener.listen(6493)) {
                overridePendingTransition(0, R.anim.slow_fade_out);
            }
        } catch (IOException e) {
            if (!ListenerUtil.mutListener.listen(6484)) {
                logger.debug("Unable to create temp file for crop");
            }
        }
    }

    private void selectImage(final int position) {
        if (!ListenerUtil.mutListener.listen(6497)) {
            if (gridView != null) {
                if (!ListenerUtil.mutListener.listen(6496)) {
                    gridView.post(new Runnable() {

                        @Override
                        public void run() {
                            try {
                                if (!ListenerUtil.mutListener.listen(6495)) {
                                    gridView.setItemChecked(position, true);
                                }
                            } catch (Exception e) {
                                if (!ListenerUtil.mutListener.listen(6494)) {
                                    logger.error("Exception", e);
                                }
                            }
                        }
                    });
                }
            }
        }
    }

    private void updateMenu() {
        if (!ListenerUtil.mutListener.listen(6504)) {
            if (this.cameraButton != null) {
                if (!ListenerUtil.mutListener.listen(6503)) {
                    this.cameraButton.setVisibility((ListenerUtil.mutListener.listen(6502) ? (this.mediaItems.size() >= MAX_SELECTABLE_IMAGES) : (ListenerUtil.mutListener.listen(6501) ? (this.mediaItems.size() <= MAX_SELECTABLE_IMAGES) : (ListenerUtil.mutListener.listen(6500) ? (this.mediaItems.size() > MAX_SELECTABLE_IMAGES) : (ListenerUtil.mutListener.listen(6499) ? (this.mediaItems.size() != MAX_SELECTABLE_IMAGES) : (ListenerUtil.mutListener.listen(6498) ? (this.mediaItems.size() == MAX_SELECTABLE_IMAGES) : (this.mediaItems.size() < MAX_SELECTABLE_IMAGES)))))) ? View.VISIBLE : View.GONE);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(6515)) {
            if ((ListenerUtil.mutListener.listen(6509) ? (mediaItems.size() >= 0) : (ListenerUtil.mutListener.listen(6508) ? (mediaItems.size() <= 0) : (ListenerUtil.mutListener.listen(6507) ? (mediaItems.size() < 0) : (ListenerUtil.mutListener.listen(6506) ? (mediaItems.size() != 0) : (ListenerUtil.mutListener.listen(6505) ? (mediaItems.size() == 0) : (mediaItems.size() > 0))))))) {
                boolean canEdit = (ListenerUtil.mutListener.listen(6511) ? (mediaItems.get(bigImagePos).getType() == TYPE_IMAGE && mediaItems.get(bigImagePos).getType() == TYPE_IMAGE_CAM) : (mediaItems.get(bigImagePos).getType() == TYPE_IMAGE || mediaItems.get(bigImagePos).getType() == TYPE_IMAGE_CAM));
                boolean canSettings = mediaItems.get(bigImagePos).getType() == TYPE_IMAGE;
                if (!ListenerUtil.mutListener.listen(6512)) {
                    getToolbar().getMenu().setGroupVisible(R.id.group_tools, canEdit);
                }
                if (!ListenerUtil.mutListener.listen(6514)) {
                    if (settingsItem != null) {
                        if (!ListenerUtil.mutListener.listen(6513)) {
                            settingsItem.setVisible(canSettings);
                        }
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(6510)) {
                    getToolbar().getMenu().setGroupVisible(R.id.group_tools, false);
                }
            }
        }
    }

    private void showBigVideo(MediaItem item) {
        if (!ListenerUtil.mutListener.listen(6526)) {
            if ((ListenerUtil.mutListener.listen(6520) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(6519) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(6518) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(6517) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(6516) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.LOLLIPOP) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP))))))) {
                if (!ListenerUtil.mutListener.listen(6521)) {
                    this.bigImageView.setVisibility(View.GONE);
                }
                if (!ListenerUtil.mutListener.listen(6522)) {
                    this.bigGifImageView.setVisibility(View.GONE);
                }
                if (!ListenerUtil.mutListener.listen(6523)) {
                    this.videoEditView.setVisibility(View.VISIBLE);
                }
                if (!ListenerUtil.mutListener.listen(6524)) {
                    this.videoEditView.setVideo(item);
                }
                if (!ListenerUtil.mutListener.listen(6525)) {
                    logger.debug("show video " + item.getDurationMs());
                }
            }
        }
    }

    private void showBigImage(final int position) {
        if (!ListenerUtil.mutListener.listen(6527)) {
            showBigImage(position, true);
        }
    }

    private void showBigImage(final int position, boolean showProgressBar) {
        if (!ListenerUtil.mutListener.listen(6528)) {
            logger.debug("showBigImage: " + position);
        }
        if (!ListenerUtil.mutListener.listen(6534)) {
            if ((ListenerUtil.mutListener.listen(6533) ? (mediaItems.size() >= 0) : (ListenerUtil.mutListener.listen(6532) ? (mediaItems.size() > 0) : (ListenerUtil.mutListener.listen(6531) ? (mediaItems.size() < 0) : (ListenerUtil.mutListener.listen(6530) ? (mediaItems.size() != 0) : (ListenerUtil.mutListener.listen(6529) ? (mediaItems.size() == 0) : (mediaItems.size() <= 0))))))) {
                return;
            }
        }
        MediaItem item = mediaItems.get(position);
        if (!ListenerUtil.mutListener.listen(6535)) {
            bigImagePos = position;
        }
        if (!ListenerUtil.mutListener.listen(6536)) {
            updateMenu();
        }
        if (!ListenerUtil.mutListener.listen(6567)) {
            if ((ListenerUtil.mutListener.listen(6537) ? (item.getType() == MediaItem.TYPE_VIDEO && item.getType() == MediaItem.TYPE_VIDEO_CAM) : (item.getType() == MediaItem.TYPE_VIDEO || item.getType() == MediaItem.TYPE_VIDEO_CAM))) {
                if (!ListenerUtil.mutListener.listen(6566)) {
                    showBigVideo(item);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(6538)) {
                    this.videoEditView.setVisibility(View.GONE);
                }
                if (!ListenerUtil.mutListener.listen(6565)) {
                    if (item.getType() == TYPE_GIF) {
                        if (!ListenerUtil.mutListener.listen(6560)) {
                            bigProgressBar.setVisibility(View.GONE);
                        }
                        if (!ListenerUtil.mutListener.listen(6561)) {
                            bigImageView.setVisibility(View.GONE);
                        }
                        try {
                            if (!ListenerUtil.mutListener.listen(6563)) {
                                bigGifImageView.setImageURI(item.getUri());
                            }
                            if (!ListenerUtil.mutListener.listen(6564)) {
                                bigGifImageView.setVisibility(View.VISIBLE);
                            }
                        } catch (Exception e) {
                            if (!ListenerUtil.mutListener.listen(6562)) {
                                // may crash with a SecurityException on some exotic devices
                                logger.error("Error setting GIF", e);
                            }
                        }
                    } else {
                        BitmapWorkerTaskParams bitmapParams = new BitmapWorkerTaskParams();
                        if (!ListenerUtil.mutListener.listen(6539)) {
                            bitmapParams.imageUri = item.getUri();
                        }
                        if (!ListenerUtil.mutListener.listen(6540)) {
                            bitmapParams.width = parentWidth;
                        }
                        if (!ListenerUtil.mutListener.listen(6541)) {
                            bitmapParams.height = parentHeight;
                        }
                        if (!ListenerUtil.mutListener.listen(6542)) {
                            bitmapParams.contentResolver = getContentResolver();
                        }
                        if (!ListenerUtil.mutListener.listen(6543)) {
                            bitmapParams.mutable = false;
                        }
                        if (!ListenerUtil.mutListener.listen(6544)) {
                            bitmapParams.flip = item.getFlip();
                        }
                        if (!ListenerUtil.mutListener.listen(6545)) {
                            bitmapParams.orientation = item.getRotation();
                        }
                        if (!ListenerUtil.mutListener.listen(6546)) {
                            bitmapParams.exifFlip = item.getExifFlip();
                        }
                        if (!ListenerUtil.mutListener.listen(6547)) {
                            bitmapParams.exifOrientation = item.getExifRotation();
                        }
                        if (!ListenerUtil.mutListener.listen(6548)) {
                            logger.debug("showBigImage uri: " + bitmapParams.imageUri);
                        }
                        if (!ListenerUtil.mutListener.listen(6550)) {
                            if (showProgressBar) {
                                if (!ListenerUtil.mutListener.listen(6549)) {
                                    bigProgressBar.setVisibility(View.VISIBLE);
                                }
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(6559)) {
                            // load main image
                            new BitmapWorkerTask(bigImageView) {

                                @Override
                                protected void onPostExecute(Bitmap bitmap) {
                                    if (!ListenerUtil.mutListener.listen(6551)) {
                                        super.onPostExecute(bitmap);
                                    }
                                    if (!ListenerUtil.mutListener.listen(6552)) {
                                        bigProgressBar.setVisibility(View.GONE);
                                    }
                                    if (!ListenerUtil.mutListener.listen(6553)) {
                                        bigImageView.setRotation(0f);
                                    }
                                    if (!ListenerUtil.mutListener.listen(6554)) {
                                        bigImageView.setScaleX(1f);
                                    }
                                    if (!ListenerUtil.mutListener.listen(6555)) {
                                        bigImageView.setScaleY(1f);
                                    }
                                    if (!ListenerUtil.mutListener.listen(6556)) {
                                        bigImageView.setRotationY(0f);
                                    }
                                    if (!ListenerUtil.mutListener.listen(6557)) {
                                        bigImageView.setVisibility(View.VISIBLE);
                                    }
                                    if (!ListenerUtil.mutListener.listen(6558)) {
                                        bigGifImageView.setVisibility(View.GONE);
                                    }
                                }
                            }.execute(bitmapParams);
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(6568)) {
            selectImage(bigImagePos);
        }
        if (!ListenerUtil.mutListener.listen(6569)) {
            updateMenu();
        }
        String caption = item.getCaption();
        if (!ListenerUtil.mutListener.listen(6570)) {
            captionEditText.setText(null);
        }
        if (!ListenerUtil.mutListener.listen(6572)) {
            if (!TestUtil.empty(caption)) {
                if (!ListenerUtil.mutListener.listen(6571)) {
                    captionEditText.append(caption);
                }
            }
        }
    }

    @Override
    public void onBackPressed() {
        if (!ListenerUtil.mutListener.listen(6578)) {
            if ((ListenerUtil.mutListener.listen(6573) ? (emojiPicker != null || emojiPicker.isShown()) : (emojiPicker != null && emojiPicker.isShown()))) {
                if (!ListenerUtil.mutListener.listen(6577)) {
                    emojiPicker.hide();
                }
            } else {
                if (!ListenerUtil.mutListener.listen(6576)) {
                    if (gridView.isEditMode()) {
                        if (!ListenerUtil.mutListener.listen(6575)) {
                            gridView.stopEditMode();
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(6574)) {
                            confirmQuit();
                        }
                    }
                }
            }
        }
    }

    private void confirmQuit() {
        if (!ListenerUtil.mutListener.listen(6581)) {
            if (hasChanges) {
                GenericAlertDialog dialogFragment = GenericAlertDialog.newInstance(R.string.send_media, R.string.discard_changes, R.string.yes, R.string.no);
                if (!ListenerUtil.mutListener.listen(6580)) {
                    dialogFragment.show(getSupportFragmentManager(), DIALOG_TAG_QUIT_CONFIRM);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(6579)) {
                    finish();
                }
            }
        }
    }

    private boolean isDuplicate(List<MediaItem> list, Uri uri) {
        if (!ListenerUtil.mutListener.listen(6589)) {
            {
                long _loopCounter54 = 0;
                // do not allow the same image twice
                for (int j = 0; (ListenerUtil.mutListener.listen(6588) ? (j >= list.size()) : (ListenerUtil.mutListener.listen(6587) ? (j <= list.size()) : (ListenerUtil.mutListener.listen(6586) ? (j > list.size()) : (ListenerUtil.mutListener.listen(6585) ? (j != list.size()) : (ListenerUtil.mutListener.listen(6584) ? (j == list.size()) : (j < list.size())))))); j++) {
                    ListenerUtil.loopListener.listen("_loopCounter54", ++_loopCounter54);
                    if (!ListenerUtil.mutListener.listen(6583)) {
                        if (list.get(j).getUri().equals(uri)) {
                            if (!ListenerUtil.mutListener.listen(6582)) {
                                Snackbar.make((View) gridView.getParent(), getString(R.string.image_already_added), Snackbar.LENGTH_LONG).show();
                            }
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    @Override
    protected void onDestroy() {
        if (!ListenerUtil.mutListener.listen(6590)) {
            new Thread(() -> VideoTimelineCache.getInstance().flush()).start();
        }
        if (!ListenerUtil.mutListener.listen(6592)) {
            if (preferenceService.getEmojiStyle() != PreferenceService.EmojiStyle_ANDROID) {
                if (!ListenerUtil.mutListener.listen(6591)) {
                    removeAllListeners();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(6593)) {
            super.onDestroy();
        }
    }

    @Override
    public void onYes(String tag, Object data) {
        if (!ListenerUtil.mutListener.listen(6594)) {
            finish();
        }
    }

    @Override
    public void onNo(String tag, Object data) {
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        if (!ListenerUtil.mutListener.listen(6595)) {
            outState.putInt(STATE_BIGIMAGE_POS, this.bigImagePos);
        }
        if (!ListenerUtil.mutListener.listen(6596)) {
            outState.putParcelableArrayList(STATE_ITEMS, this.mediaItems);
        }
        if (!ListenerUtil.mutListener.listen(6597)) {
            outState.putString(STATE_CAMERA_FILE, this.cameraFilePath);
        }
        if (!ListenerUtil.mutListener.listen(6598)) {
            outState.putString(STATE_VIDEO_FILE, this.videoFilePath);
        }
        if (!ListenerUtil.mutListener.listen(6600)) {
            if (this.cropFile != null) {
                if (!ListenerUtil.mutListener.listen(6599)) {
                    outState.putParcelable(STATE_CROP_FILE, Uri.fromFile(this.cropFile));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(6601)) {
            super.onSaveInstanceState(outState);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (!ListenerUtil.mutListener.listen(6602)) {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
        if (!ListenerUtil.mutListener.listen(6614)) {
            if ((ListenerUtil.mutListener.listen(6608) ? ((ListenerUtil.mutListener.listen(6607) ? (grantResults.length >= 0) : (ListenerUtil.mutListener.listen(6606) ? (grantResults.length <= 0) : (ListenerUtil.mutListener.listen(6605) ? (grantResults.length < 0) : (ListenerUtil.mutListener.listen(6604) ? (grantResults.length != 0) : (ListenerUtil.mutListener.listen(6603) ? (grantResults.length == 0) : (grantResults.length > 0)))))) || grantResults[0] == PackageManager.PERMISSION_GRANTED) : ((ListenerUtil.mutListener.listen(6607) ? (grantResults.length >= 0) : (ListenerUtil.mutListener.listen(6606) ? (grantResults.length <= 0) : (ListenerUtil.mutListener.listen(6605) ? (grantResults.length < 0) : (ListenerUtil.mutListener.listen(6604) ? (grantResults.length != 0) : (ListenerUtil.mutListener.listen(6603) ? (grantResults.length == 0) : (grantResults.length > 0)))))) && grantResults[0] == PackageManager.PERMISSION_GRANTED))) {
                if (!ListenerUtil.mutListener.listen(6613)) {
                    switch(requestCode) {
                        case PERMISSION_REQUEST_CAMERA:
                            if (!ListenerUtil.mutListener.listen(6612)) {
                                reallyLaunchCamera();
                            }
                            break;
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(6611)) {
                    switch(requestCode) {
                        case PERMISSION_REQUEST_CAMERA:
                            if (!ListenerUtil.mutListener.listen(6610)) {
                                if (!ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)) {
                                    if (!ListenerUtil.mutListener.listen(6609)) {
                                        ConfigUtils.showPermissionRationale(this, activityParentLayout, R.string.permission_camera_photo_required);
                                    }
                                }
                            }
                            break;
                    }
                }
            }
        }
    }

    @Override
    public void onKeyboardShown() {
        if (!ListenerUtil.mutListener.listen(6617)) {
            if ((ListenerUtil.mutListener.listen(6615) ? (emojiPicker != null || emojiPicker.isShown()) : (emojiPicker != null && emojiPicker.isShown()))) {
                if (!ListenerUtil.mutListener.listen(6616)) {
                    emojiPicker.hide();
                }
            }
        }
    }

    @Override
    public void onKeyboardHidden() {
    }
}
