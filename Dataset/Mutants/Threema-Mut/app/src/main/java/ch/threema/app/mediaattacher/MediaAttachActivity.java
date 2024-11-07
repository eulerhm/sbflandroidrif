/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
 * Copyright (c) 2020-2021 Threema GmbH
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
package ch.threema.app.mediaattacher;

import android.Manifest;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.location.Location;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import androidx.annotation.NonNull;
import androidx.annotation.UiThread;
import androidx.appcompat.widget.FitWindowsFrameLayout;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import ch.threema.app.R;
import ch.threema.app.ThreemaApplication;
import ch.threema.app.actions.LocationMessageSendAction;
import ch.threema.app.actions.SendAction;
import ch.threema.app.activities.SendMediaActivity;
import ch.threema.app.activities.ThreemaActivity;
import ch.threema.app.activities.ballot.BallotWizardActivity;
import ch.threema.app.camera.CameraUtil;
import ch.threema.app.dialogs.ExpandableTextEntryDialog;
import ch.threema.app.dialogs.GenericAlertDialog;
import ch.threema.app.fragments.ComposeMessageFragment;
import ch.threema.app.listeners.QRCodeScanListener;
import ch.threema.app.locationpicker.LocationPickerActivity;
import ch.threema.app.managers.ListenerManager;
import ch.threema.app.messagereceiver.DistributionListMessageReceiver;
import ch.threema.app.messagereceiver.GroupMessageReceiver;
import ch.threema.app.messagereceiver.MessageReceiver;
import ch.threema.app.services.MessageService;
import ch.threema.app.ui.MediaItem;
import ch.threema.app.ui.SingleToast;
import ch.threema.app.utils.AnimationUtil;
import ch.threema.app.utils.ConfigUtils;
import ch.threema.app.utils.FileUtil;
import ch.threema.app.utils.IntentDataUtil;
import ch.threema.app.utils.LocaleUtil;
import ch.threema.app.utils.MimeUtil;
import ch.threema.app.utils.QRScannerUtil;
import ch.threema.app.utils.RuntimeUtil;
import static ch.threema.app.ThreemaApplication.MAX_BLOB_SIZE;
import static ch.threema.app.utils.IntentDataUtil.INTENT_DATA_LOCATION_NAME;
import static com.google.android.material.bottomsheet.BottomSheetBehavior.STATE_EXPANDED;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class MediaAttachActivity extends MediaSelectionBaseActivity implements View.OnClickListener, MediaAttachAdapter.ItemClickListener, ExpandableTextEntryDialog.ExpandableTextEntryDialogClickListener, GenericAlertDialog.DialogClickListener {

    private static final Logger logger = LoggerFactory.getLogger(MediaAttachActivity.class);

    private static final int CONTACT_PICKER_INTENT = 33002;

    private static final int LOCATION_PICKER_INTENT = 33003;

    private static final int PERMISSION_REQUEST_LOCATION = 1;

    private static final int PERMISSION_REQUEST_ATTACH_CONTACT = 2;

    private static final int PERMISSION_REQUEST_QR_READER = 3;

    private static final int PERMISSION_REQUEST_ATTACH_FROM_EXTERNAL_CAMERA = 6;

    public static final String CONFIRM_TAG_REALLY_SEND_FILE = "reallySendFile";

    public static final String DIALOG_TAG_PREPARE_SEND_FILE = "prepSF";

    private ConstraintLayout sendPanel;

    private LinearLayout attachPanel;

    private ControlPanelButton attachGalleryButton, attachLocationButton, attachQRButton, attachBallotButton, attachContactButton, attachFileButton, sendButton, editButton, cancelButton, attachFromExternalCameraButton;

    private Button selectCounterButton;

    private ImageView moreArrowView;

    private HorizontalScrollView scrollView;

    private MessageReceiver messageReceiver;

    private MessageService messageService;

    /* start setup methods */
    @Override
    protected void initActivity(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(29369)) {
            super.initActivity(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(29370)) {
            this.handleIntent();
        }
        if (!ListenerUtil.mutListener.listen(29371)) {
            this.setControlPanelLayout();
        }
        if (!ListenerUtil.mutListener.listen(29372)) {
            this.setupControlPanelListeners();
        }
        if (!ListenerUtil.mutListener.listen(29373)) {
            this.setInitialMediaGrid();
        }
        if (!ListenerUtil.mutListener.listen(29374)) {
            this.handleSavedInstanceState(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(29387)) {
            this.scrollView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

                @Override
                public void onGlobalLayout() {
                    if (!ListenerUtil.mutListener.listen(29375)) {
                        rootView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    }
                    // of course, we do this only when some buttons are obscured
                    View child = (View) scrollView.getChildAt(0);
                    if (!ListenerUtil.mutListener.listen(29386)) {
                        if (child != null) {
                            int childWidth = (child).getWidth();
                            if (!ListenerUtil.mutListener.listen(29385)) {
                                if ((ListenerUtil.mutListener.listen(29380) ? (scrollView.getWidth() >= (childWidth + scrollView.getPaddingLeft() + scrollView.getPaddingRight())) : (ListenerUtil.mutListener.listen(29379) ? (scrollView.getWidth() <= (childWidth + scrollView.getPaddingLeft() + scrollView.getPaddingRight())) : (ListenerUtil.mutListener.listen(29378) ? (scrollView.getWidth() > (childWidth + scrollView.getPaddingLeft() + scrollView.getPaddingRight())) : (ListenerUtil.mutListener.listen(29377) ? (scrollView.getWidth() != (childWidth + scrollView.getPaddingLeft() + scrollView.getPaddingRight())) : (ListenerUtil.mutListener.listen(29376) ? (scrollView.getWidth() == (childWidth + scrollView.getPaddingLeft() + scrollView.getPaddingRight())) : (scrollView.getWidth() < (childWidth + scrollView.getPaddingLeft() + scrollView.getPaddingRight())))))))) {
                                    if (!ListenerUtil.mutListener.listen(29384)) {
                                        if (moreArrowView != null) {
                                            if (!ListenerUtil.mutListener.listen(29381)) {
                                                moreArrowView.setVisibility(View.VISIBLE);
                                            }
                                            if (!ListenerUtil.mutListener.listen(29383)) {
                                                moreArrowView.animate().alpha(0f).setStartDelay(1500).setDuration(500).setListener(new AnimatorListenerAdapter() {

                                                    @Override
                                                    public void onAnimationEnd(Animator animation) {
                                                        if (!ListenerUtil.mutListener.listen(29382)) {
                                                            moreArrowView.setVisibility(View.GONE);
                                                        }
                                                    }
                                                });
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
    }

    @Override
    protected void initServices() {
        if (!ListenerUtil.mutListener.listen(29388)) {
            super.initServices();
        }
        try {
            if (!ListenerUtil.mutListener.listen(29390)) {
                this.messageService = serviceManager.getMessageService();
            }
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(29389)) {
                logger.error("Exception", e);
            }
        }
    }

    public void handleIntent() {
        Intent intent = this.getIntent();
        if (!ListenerUtil.mutListener.listen(29391)) {
            this.messageReceiver = IntentDataUtil.getMessageReceiverFromIntent(this, intent);
        }
        if (!ListenerUtil.mutListener.listen(29394)) {
            if (this.messageReceiver == null) {
                if (!ListenerUtil.mutListener.listen(29392)) {
                    logger.error("invalid receiver");
                }
                if (!ListenerUtil.mutListener.listen(29393)) {
                    finish();
                }
            }
        }
    }

    public void setControlPanelLayout() {
        ViewStub stub = findViewById(R.id.stub);
        if (!ListenerUtil.mutListener.listen(29395)) {
            stub.setLayoutResource(R.layout.media_attach_control_panel);
        }
        if (!ListenerUtil.mutListener.listen(29396)) {
            stub.inflate();
        }
        if (!ListenerUtil.mutListener.listen(29397)) {
            this.controlPanel = findViewById(R.id.control_panel);
        }
        if (!ListenerUtil.mutListener.listen(29398)) {
            this.sendPanel = findViewById(R.id.send_panel);
        }
        if (!ListenerUtil.mutListener.listen(29399)) {
            this.attachPanel = findViewById(R.id.attach_options_container);
        }
        if (!ListenerUtil.mutListener.listen(29400)) {
            this.scrollView = findViewById(R.id.attach_panel);
        }
        if (!ListenerUtil.mutListener.listen(29401)) {
            // Horizontal buttons in the panel
            this.attachGalleryButton = attachPanel.findViewById(R.id.attach_gallery);
        }
        if (!ListenerUtil.mutListener.listen(29402)) {
            this.attachLocationButton = attachPanel.findViewById(R.id.attach_location);
        }
        if (!ListenerUtil.mutListener.listen(29403)) {
            this.attachFileButton = attachPanel.findViewById(R.id.attach_file);
        }
        if (!ListenerUtil.mutListener.listen(29404)) {
            this.attachQRButton = attachPanel.findViewById(R.id.attach_qr_code);
        }
        if (!ListenerUtil.mutListener.listen(29405)) {
            this.attachBallotButton = attachPanel.findViewById(R.id.attach_poll);
        }
        if (!ListenerUtil.mutListener.listen(29406)) {
            this.attachContactButton = attachPanel.findViewById(R.id.attach_contact);
        }
        if (!ListenerUtil.mutListener.listen(29407)) {
            this.attachFromExternalCameraButton = attachPanel.findViewById(R.id.attach_system_camera);
        }
        if (!ListenerUtil.mutListener.listen(29408)) {
            // Send/edit/cancel buttons
            this.sendButton = sendPanel.findViewById(R.id.send);
        }
        if (!ListenerUtil.mutListener.listen(29409)) {
            this.editButton = sendPanel.findViewById(R.id.edit);
        }
        if (!ListenerUtil.mutListener.listen(29410)) {
            this.cancelButton = sendPanel.findViewById(R.id.cancel);
        }
        if (!ListenerUtil.mutListener.listen(29411)) {
            this.selectCounterButton = sendPanel.findViewById(R.id.select_counter_button);
        }
        if (!ListenerUtil.mutListener.listen(29412)) {
            // Reset click listeners
            this.controlPanel.setOnClickListener(null);
        }
        if (!ListenerUtil.mutListener.listen(29413)) {
            this.sendPanel.setOnClickListener(null);
        }
        if (!ListenerUtil.mutListener.listen(29414)) {
            // additional decoration
            this.moreArrowView = findViewById(R.id.more_arrow);
        }
        if (!ListenerUtil.mutListener.listen(29417)) {
            this.moreArrowView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    if (!ListenerUtil.mutListener.listen(29416)) {
                        if (scrollView != null) {
                            if (!ListenerUtil.mutListener.listen(29415)) {
                                scrollView.smoothScrollTo(65535, 0);
                            }
                        }
                    }
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(29419)) {
            // If the media grid is shown, we don't need the gallery button
            if (shouldShowMediaGrid()) {
                if (!ListenerUtil.mutListener.listen(29418)) {
                    this.attachGalleryButton.setVisibility(View.GONE);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(29424)) {
            if ((ListenerUtil.mutListener.listen(29422) ? (messageReceiver instanceof DistributionListMessageReceiver && ((ListenerUtil.mutListener.listen(29421) ? ((ListenerUtil.mutListener.listen(29420) ? (messageReceiver instanceof GroupMessageReceiver || groupService != null) : (messageReceiver instanceof GroupMessageReceiver && groupService != null)) || groupService.isNotesGroup(((GroupMessageReceiver) messageReceiver).getGroup())) : ((ListenerUtil.mutListener.listen(29420) ? (messageReceiver instanceof GroupMessageReceiver || groupService != null) : (messageReceiver instanceof GroupMessageReceiver && groupService != null)) && groupService.isNotesGroup(((GroupMessageReceiver) messageReceiver).getGroup()))))) : (messageReceiver instanceof DistributionListMessageReceiver || ((ListenerUtil.mutListener.listen(29421) ? ((ListenerUtil.mutListener.listen(29420) ? (messageReceiver instanceof GroupMessageReceiver || groupService != null) : (messageReceiver instanceof GroupMessageReceiver && groupService != null)) || groupService.isNotesGroup(((GroupMessageReceiver) messageReceiver).getGroup())) : ((ListenerUtil.mutListener.listen(29420) ? (messageReceiver instanceof GroupMessageReceiver || groupService != null) : (messageReceiver instanceof GroupMessageReceiver && groupService != null)) && groupService.isNotesGroup(((GroupMessageReceiver) messageReceiver).getGroup()))))))) {
                if (!ListenerUtil.mutListener.listen(29423)) {
                    this.attachBallotButton.setVisibility(View.GONE);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(29427)) {
            if ((ListenerUtil.mutListener.listen(29425) ? (attachFromExternalCameraButton != null || !CameraUtil.isInternalCameraSupported()) : (attachFromExternalCameraButton != null && !CameraUtil.isInternalCameraSupported()))) {
                if (!ListenerUtil.mutListener.listen(29426)) {
                    this.attachFromExternalCameraButton.setVisibility(View.GONE);
                }
            }
        }
    }

    private void setupControlPanelListeners() {
        if (!ListenerUtil.mutListener.listen(29428)) {
            attachGalleryButton.setOnClickListener(this);
        }
        if (!ListenerUtil.mutListener.listen(29429)) {
            attachLocationButton.setOnClickListener(this);
        }
        if (!ListenerUtil.mutListener.listen(29430)) {
            attachFileButton.setOnClickListener(this);
        }
        if (!ListenerUtil.mutListener.listen(29431)) {
            attachQRButton.setOnClickListener(this);
        }
        if (!ListenerUtil.mutListener.listen(29432)) {
            attachBallotButton.setOnClickListener(this);
        }
        if (!ListenerUtil.mutListener.listen(29433)) {
            attachContactButton.setOnClickListener(this);
        }
        if (!ListenerUtil.mutListener.listen(29434)) {
            sendButton.setOnClickListener(this);
        }
        if (!ListenerUtil.mutListener.listen(29435)) {
            editButton.setOnClickListener(this);
        }
        if (!ListenerUtil.mutListener.listen(29436)) {
            cancelButton.setOnClickListener(this);
        }
        if (!ListenerUtil.mutListener.listen(29437)) {
            selectCounterButton.setOnClickListener(this);
        }
        if (!ListenerUtil.mutListener.listen(29438)) {
            attachFromExternalCameraButton.setOnClickListener(this);
        }
    }

    /* start section action methods */
    @Override
    public void onItemChecked(int count) {
        if (!ListenerUtil.mutListener.listen(29445)) {
            if (this.selectFromGalleryItem != null) {
                if (!ListenerUtil.mutListener.listen(29444)) {
                    this.selectFromGalleryItem.setVisible((ListenerUtil.mutListener.listen(29443) ? (count >= 0) : (ListenerUtil.mutListener.listen(29442) ? (count <= 0) : (ListenerUtil.mutListener.listen(29441) ? (count > 0) : (ListenerUtil.mutListener.listen(29440) ? (count < 0) : (ListenerUtil.mutListener.listen(29439) ? (count != 0) : (count == 0)))))));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(29505)) {
            if ((ListenerUtil.mutListener.listen(29450) ? (count >= 0) : (ListenerUtil.mutListener.listen(29449) ? (count <= 0) : (ListenerUtil.mutListener.listen(29448) ? (count < 0) : (ListenerUtil.mutListener.listen(29447) ? (count != 0) : (ListenerUtil.mutListener.listen(29446) ? (count == 0) : (count > 0))))))) {
                if (!ListenerUtil.mutListener.listen(29478)) {
                    if (moreArrowView != null) {
                        if (!ListenerUtil.mutListener.listen(29477)) {
                            moreArrowView.setVisibility(View.GONE);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(29492)) {
                    if (sendPanel.getVisibility() == View.GONE) {
                        if (!ListenerUtil.mutListener.listen(29479)) {
                            attachPanel.setVisibility(View.GONE);
                        }
                        if (!ListenerUtil.mutListener.listen(29480)) {
                            sendPanel.setVisibility(View.VISIBLE);
                        }
                        if (!ListenerUtil.mutListener.listen(29491)) {
                            // only slide up when previously hidden otherwise animate switch between panels
                            if (controlPanel.getTranslationY() != 0) {
                                if (!ListenerUtil.mutListener.listen(29490)) {
                                    controlPanel.animate().translationY(0).withEndAction(() -> bottomSheetLayout.setPadding(0, 0, 0, controlPanel.getHeight() - getResources().getDimensionPixelSize(R.dimen.media_attach_control_panel_shadow_size)));
                                }
                            } else {
                                if (!ListenerUtil.mutListener.listen(29481)) {
                                    AnimationUtil.bubbleAnimate(sendButton, 50);
                                }
                                if (!ListenerUtil.mutListener.listen(29482)) {
                                    AnimationUtil.bubbleAnimate(selectCounterButton, 50);
                                }
                                if (!ListenerUtil.mutListener.listen(29483)) {
                                    AnimationUtil.bubbleAnimate(editButton, 75);
                                }
                                if (!ListenerUtil.mutListener.listen(29484)) {
                                    AnimationUtil.bubbleAnimate(cancelButton, 100);
                                }
                                if (!ListenerUtil.mutListener.listen(29489)) {
                                    bottomSheetLayout.setPadding(0, 0, 0, (ListenerUtil.mutListener.listen(29488) ? (controlPanel.getHeight() % getResources().getDimensionPixelSize(R.dimen.media_attach_control_panel_shadow_size)) : (ListenerUtil.mutListener.listen(29487) ? (controlPanel.getHeight() / getResources().getDimensionPixelSize(R.dimen.media_attach_control_panel_shadow_size)) : (ListenerUtil.mutListener.listen(29486) ? (controlPanel.getHeight() * getResources().getDimensionPixelSize(R.dimen.media_attach_control_panel_shadow_size)) : (ListenerUtil.mutListener.listen(29485) ? (controlPanel.getHeight() + getResources().getDimensionPixelSize(R.dimen.media_attach_control_panel_shadow_size)) : (controlPanel.getHeight() - getResources().getDimensionPixelSize(R.dimen.media_attach_control_panel_shadow_size)))))));
                                }
                            }
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(29503)) {
                    if ((ListenerUtil.mutListener.listen(29497) ? (count >= SendMediaActivity.MAX_SELECTABLE_IMAGES) : (ListenerUtil.mutListener.listen(29496) ? (count <= SendMediaActivity.MAX_SELECTABLE_IMAGES) : (ListenerUtil.mutListener.listen(29495) ? (count < SendMediaActivity.MAX_SELECTABLE_IMAGES) : (ListenerUtil.mutListener.listen(29494) ? (count != SendMediaActivity.MAX_SELECTABLE_IMAGES) : (ListenerUtil.mutListener.listen(29493) ? (count == SendMediaActivity.MAX_SELECTABLE_IMAGES) : (count > SendMediaActivity.MAX_SELECTABLE_IMAGES))))))) {
                        if (!ListenerUtil.mutListener.listen(29501)) {
                            editButton.setAlpha(0.2f);
                        }
                        if (!ListenerUtil.mutListener.listen(29502)) {
                            editButton.setClickable(false);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(29498)) {
                            editButton.setAlpha(1.0f);
                        }
                        if (!ListenerUtil.mutListener.listen(29499)) {
                            editButton.setClickable(true);
                        }
                        if (!ListenerUtil.mutListener.listen(29500)) {
                            editButton.setLabelText(R.string.edit);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(29504)) {
                    selectCounterButton.setText(String.format(LocaleUtil.getCurrentLocale(this), "%d", count));
                }
            } else if (BottomSheetBehavior.from(bottomSheetLayout).getState() == STATE_EXPANDED) {
                if (!ListenerUtil.mutListener.listen(29473)) {
                    controlPanel.animate().translationY((ListenerUtil.mutListener.listen(29472) ? ((float) controlPanel.getHeight() % getResources().getDimensionPixelSize(R.dimen.media_attach_control_panel_shadow_size)) : (ListenerUtil.mutListener.listen(29471) ? ((float) controlPanel.getHeight() / getResources().getDimensionPixelSize(R.dimen.media_attach_control_panel_shadow_size)) : (ListenerUtil.mutListener.listen(29470) ? ((float) controlPanel.getHeight() * getResources().getDimensionPixelSize(R.dimen.media_attach_control_panel_shadow_size)) : (ListenerUtil.mutListener.listen(29469) ? ((float) controlPanel.getHeight() + getResources().getDimensionPixelSize(R.dimen.media_attach_control_panel_shadow_size)) : ((float) controlPanel.getHeight() - getResources().getDimensionPixelSize(R.dimen.media_attach_control_panel_shadow_size))))))).withEndAction(() -> {
                        sendPanel.setVisibility(View.GONE);
                        attachPanel.setVisibility(View.VISIBLE);
                    });
                }
                // animate padding change to avoid flicker
                ValueAnimator animator = ValueAnimator.ofInt(bottomSheetLayout.getPaddingBottom(), 0);
                if (!ListenerUtil.mutListener.listen(29474)) {
                    animator.addUpdateListener(valueAnimator -> bottomSheetLayout.setPadding(0, 0, 0, (Integer) valueAnimator.getAnimatedValue()));
                }
                if (!ListenerUtil.mutListener.listen(29475)) {
                    animator.setDuration(300);
                }
                if (!ListenerUtil.mutListener.listen(29476)) {
                    animator.start();
                }
            } else {
                if (!ListenerUtil.mutListener.listen(29451)) {
                    sendPanel.setVisibility(View.GONE);
                }
                if (!ListenerUtil.mutListener.listen(29452)) {
                    attachPanel.setVisibility(View.VISIBLE);
                }
                if (!ListenerUtil.mutListener.listen(29453)) {
                    bottomSheetLayout.setPadding(0, 0, 0, 0);
                }
                if (!ListenerUtil.mutListener.listen(29468)) {
                    if ((ListenerUtil.mutListener.listen(29458) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(29457) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(29456) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(29455) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(29454) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.LOLLIPOP) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP))))))) {
                        if (!ListenerUtil.mutListener.listen(29460)) {
                            if (attachGalleryButton.getVisibility() == View.VISIBLE) {
                                if (!ListenerUtil.mutListener.listen(29459)) {
                                    AnimationUtil.bubbleAnimate(attachGalleryButton, 25);
                                }
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(29461)) {
                            AnimationUtil.bubbleAnimate(attachFileButton, 25);
                        }
                        if (!ListenerUtil.mutListener.listen(29462)) {
                            AnimationUtil.bubbleAnimate(attachLocationButton, 50);
                        }
                        if (!ListenerUtil.mutListener.listen(29464)) {
                            if (attachBallotButton.getVisibility() == View.VISIBLE) {
                                if (!ListenerUtil.mutListener.listen(29463)) {
                                    AnimationUtil.bubbleAnimate(attachBallotButton, 50);
                                }
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(29465)) {
                            AnimationUtil.bubbleAnimate(attachContactButton, 75);
                        }
                        if (!ListenerUtil.mutListener.listen(29466)) {
                            AnimationUtil.bubbleAnimate(attachQRButton, 75);
                        }
                        if (!ListenerUtil.mutListener.listen(29467)) {
                            AnimationUtil.bubbleAnimate(attachFromExternalCameraButton, 100);
                        }
                    }
                }
            }
        }
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        if (!ListenerUtil.mutListener.listen(29506)) {
            super.onClick(v);
        }
        int id = v.getId();
        if (!ListenerUtil.mutListener.listen(29530)) {
            switch(id) {
                case R.id.attach_location:
                    if (!ListenerUtil.mutListener.listen(29510)) {
                        if (ConfigUtils.requestLocationPermissions(this, null, PERMISSION_REQUEST_LOCATION)) {
                            if (!ListenerUtil.mutListener.listen(29509)) {
                                if (!ConfigUtils.hasNoMapboxSupport()) {
                                    if (!ListenerUtil.mutListener.listen(29508)) {
                                        launchPlacePicker();
                                    }
                                } else {
                                    if (!ListenerUtil.mutListener.listen(29507)) {
                                        Toast.makeText(this, "Feature not available due to firmware error", Toast.LENGTH_LONG).show();
                                    }
                                }
                            }
                        }
                    }
                    break;
                case R.id.attach_file:
                    if (!ListenerUtil.mutListener.listen(29512)) {
                        if (ConfigUtils.requestStoragePermissions(this, null, PERMISSION_REQUEST_ATTACH_FILE)) {
                            if (!ListenerUtil.mutListener.listen(29511)) {
                                attachFile();
                            }
                        }
                    }
                    break;
                case R.id.attach_poll:
                    if (!ListenerUtil.mutListener.listen(29513)) {
                        createBallot();
                    }
                    break;
                case R.id.attach_qr_code:
                    if (!ListenerUtil.mutListener.listen(29515)) {
                        if (ConfigUtils.requestCameraPermissions(this, null, PERMISSION_REQUEST_QR_READER)) {
                            if (!ListenerUtil.mutListener.listen(29514)) {
                                attachQR(v);
                            }
                        }
                    }
                    break;
                case R.id.attach_contact:
                    if (!ListenerUtil.mutListener.listen(29517)) {
                        if (ConfigUtils.requestContactPermissions(this, null, PERMISSION_REQUEST_ATTACH_CONTACT)) {
                            if (!ListenerUtil.mutListener.listen(29516)) {
                                attachContact();
                            }
                        }
                    }
                    break;
                case R.id.edit:
                    if (!ListenerUtil.mutListener.listen(29519)) {
                        if (mediaAttachAdapter != null) {
                            if (!ListenerUtil.mutListener.listen(29518)) {
                                onEdit(mediaAttachViewModel.getSelectedMediaUris());
                            }
                        }
                    }
                    break;
                case R.id.send:
                    if (!ListenerUtil.mutListener.listen(29525)) {
                        if (mediaAttachAdapter != null) {
                            if (!ListenerUtil.mutListener.listen(29520)) {
                                v.setAlpha(0.3f);
                            }
                            if (!ListenerUtil.mutListener.listen(29521)) {
                                v.setClickable(false);
                            }
                            if (!ListenerUtil.mutListener.listen(29523)) {
                                // return last filter to potentially re-use it when attaching more media in compose fragment
                                if (mediaAttachViewModel.getLastQueryType() != null) {
                                    Intent resultIntent = IntentDataUtil.addLastMediaFilterToIntent(new Intent(), mediaAttachViewModel.getLastQuery(), mediaAttachViewModel.getLastQueryType());
                                    if (!ListenerUtil.mutListener.listen(29522)) {
                                        setResult(RESULT_OK, resultIntent);
                                    }
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(29524)) {
                                onSend(mediaAttachViewModel.getSelectedMediaUris());
                            }
                        }
                    }
                    break;
                case R.id.attach_gallery:
                    if (!ListenerUtil.mutListener.listen(29527)) {
                        if (ConfigUtils.requestStoragePermissions(this, null, PERMISSION_REQUEST_ATTACH_FROM_GALLERY)) {
                            if (!ListenerUtil.mutListener.listen(29526)) {
                                attachImageFromGallery();
                            }
                        }
                    }
                    break;
                case R.id.attach_system_camera:
                    if (!ListenerUtil.mutListener.listen(29529)) {
                        if (ConfigUtils.requestCameraPermissions(this, null, PERMISSION_REQUEST_ATTACH_FROM_EXTERNAL_CAMERA)) {
                            if (!ListenerUtil.mutListener.listen(29528)) {
                                attachFromExternalCamera();
                            }
                        }
                    }
                default:
                    break;
            }
        }
    }

    /* start section callback methods */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (!ListenerUtil.mutListener.listen(29531)) {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
        if (!ListenerUtil.mutListener.listen(29559)) {
            if ((ListenerUtil.mutListener.listen(29537) ? ((ListenerUtil.mutListener.listen(29536) ? (grantResults.length >= 0) : (ListenerUtil.mutListener.listen(29535) ? (grantResults.length <= 0) : (ListenerUtil.mutListener.listen(29534) ? (grantResults.length < 0) : (ListenerUtil.mutListener.listen(29533) ? (grantResults.length != 0) : (ListenerUtil.mutListener.listen(29532) ? (grantResults.length == 0) : (grantResults.length > 0)))))) || grantResults[0] == PackageManager.PERMISSION_GRANTED) : ((ListenerUtil.mutListener.listen(29536) ? (grantResults.length >= 0) : (ListenerUtil.mutListener.listen(29535) ? (grantResults.length <= 0) : (ListenerUtil.mutListener.listen(29534) ? (grantResults.length < 0) : (ListenerUtil.mutListener.listen(29533) ? (grantResults.length != 0) : (ListenerUtil.mutListener.listen(29532) ? (grantResults.length == 0) : (grantResults.length > 0)))))) && grantResults[0] == PackageManager.PERMISSION_GRANTED))) {
                if (!ListenerUtil.mutListener.listen(29558)) {
                    switch(requestCode) {
                        case PERMISSION_REQUEST_LOCATION:
                            if (!ListenerUtil.mutListener.listen(29549)) {
                                launchPlacePicker();
                            }
                            break;
                        case PERMISSION_REQUEST_ATTACH_CONTACT:
                            if (!ListenerUtil.mutListener.listen(29550)) {
                                attachContact();
                            }
                            break;
                        case PERMISSION_REQUEST_ATTACH_FILE:
                            if (!ListenerUtil.mutListener.listen(29551)) {
                                attachFile();
                            }
                            break;
                        case PERMISSION_REQUEST_QR_READER:
                            if (!ListenerUtil.mutListener.listen(29552)) {
                                attachQR(attachQRButton);
                            }
                            break;
                        case PERMISSION_REQUEST_ATTACH_FROM_GALLERY:
                            if (!ListenerUtil.mutListener.listen(29556)) {
                                if (preferenceService.isShowImageAttachPreviewsEnabled()) {
                                    if (!ListenerUtil.mutListener.listen(29554)) {
                                        finish();
                                    }
                                    if (!ListenerUtil.mutListener.listen(29555)) {
                                        startActivity(getIntent());
                                    }
                                } else {
                                    if (!ListenerUtil.mutListener.listen(29553)) {
                                        attachImageFromGallery();
                                    }
                                }
                            }
                            break;
                        case PERMISSION_REQUEST_ATTACH_FROM_EXTERNAL_CAMERA:
                            if (!ListenerUtil.mutListener.listen(29557)) {
                                attachFromExternalCamera();
                            }
                            break;
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(29548)) {
                    switch(requestCode) {
                        case PERMISSION_REQUEST_LOCATION:
                            if (!ListenerUtil.mutListener.listen(29539)) {
                                if (!ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)) {
                                    if (!ListenerUtil.mutListener.listen(29538)) {
                                        super.showPermissionRationale(R.string.permission_location_required);
                                    }
                                }
                            }
                            break;
                        case PERMISSION_REQUEST_ATTACH_CONTACT:
                            if (!ListenerUtil.mutListener.listen(29541)) {
                                if (!ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_CONTACTS)) {
                                    if (!ListenerUtil.mutListener.listen(29540)) {
                                        super.showPermissionRationale(R.string.permission_contacts_required);
                                    }
                                }
                            }
                            break;
                        case PERMISSION_REQUEST_QR_READER:
                            if (!ListenerUtil.mutListener.listen(29543)) {
                                if (!ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)) {
                                    if (!ListenerUtil.mutListener.listen(29542)) {
                                        super.showPermissionRationale(R.string.permission_camera_qr_required);
                                    }
                                }
                            }
                            break;
                        case PERMISSION_REQUEST_ATTACH_FROM_GALLERY:
                        case PERMISSION_REQUEST_ATTACH_FILE:
                            if (!ListenerUtil.mutListener.listen(29545)) {
                                if (!ActivityCompat.shouldShowRequestPermissionRationale(MediaAttachActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                                    if (!ListenerUtil.mutListener.listen(29544)) {
                                        showPermissionRationale(R.string.permission_storage_required);
                                    }
                                }
                            }
                            break;
                        case PERMISSION_REQUEST_ATTACH_FROM_EXTERNAL_CAMERA:
                            if (!ListenerUtil.mutListener.listen(29547)) {
                                if (!ActivityCompat.shouldShowRequestPermissionRationale(MediaAttachActivity.this, Manifest.permission.CAMERA)) {
                                    if (!ListenerUtil.mutListener.listen(29546)) {
                                        showPermissionRationale(R.string.permission_camera_photo_required);
                                    }
                                }
                            }
                    }
                }
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, final Intent intent) {
        if (!ListenerUtil.mutListener.listen(29560)) {
            super.onActivityResult(requestCode, resultCode, intent);
        }
        if (!ListenerUtil.mutListener.listen(29583)) {
            if (resultCode == Activity.RESULT_OK) {
                final String scanResult = QRScannerUtil.getInstance().parseActivityResult(this, requestCode, resultCode, intent);
                if (!ListenerUtil.mutListener.listen(29570)) {
                    if ((ListenerUtil.mutListener.listen(29566) ? (scanResult != null || (ListenerUtil.mutListener.listen(29565) ? (scanResult.length() >= 0) : (ListenerUtil.mutListener.listen(29564) ? (scanResult.length() <= 0) : (ListenerUtil.mutListener.listen(29563) ? (scanResult.length() < 0) : (ListenerUtil.mutListener.listen(29562) ? (scanResult.length() != 0) : (ListenerUtil.mutListener.listen(29561) ? (scanResult.length() == 0) : (scanResult.length() > 0))))))) : (scanResult != null && (ListenerUtil.mutListener.listen(29565) ? (scanResult.length() >= 0) : (ListenerUtil.mutListener.listen(29564) ? (scanResult.length() <= 0) : (ListenerUtil.mutListener.listen(29563) ? (scanResult.length() < 0) : (ListenerUtil.mutListener.listen(29562) ? (scanResult.length() != 0) : (ListenerUtil.mutListener.listen(29561) ? (scanResult.length() == 0) : (scanResult.length() > 0))))))))) {
                        if (!ListenerUtil.mutListener.listen(29568)) {
                            ListenerManager.qrCodeScanListener.handle(new ListenerManager.HandleListener<QRCodeScanListener>() {

                                @Override
                                public void handle(QRCodeScanListener listener) {
                                    if (!ListenerUtil.mutListener.listen(29567)) {
                                        listener.onScanCompleted(scanResult);
                                    }
                                }
                            });
                        }
                        if (!ListenerUtil.mutListener.listen(29569)) {
                            finish();
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(29582)) {
                    switch(requestCode) {
                        case LOCATION_PICKER_INTENT:
                            Location location = IntentDataUtil.getLocation(intent);
                            String poiName = intent.getStringExtra(INTENT_DATA_LOCATION_NAME);
                            if (!ListenerUtil.mutListener.listen(29571)) {
                                sendLocationMessage(location, poiName);
                            }
                            break;
                        case CONTACT_PICKER_INTENT:
                            if (!ListenerUtil.mutListener.listen(29572)) {
                                sendContact(intent.getData());
                            }
                            if (!ListenerUtil.mutListener.listen(29573)) {
                                finish();
                            }
                            break;
                        case REQUEST_CODE_ATTACH_FROM_GALLERY:
                            if (!ListenerUtil.mutListener.listen(29574)) {
                                onEdit(FileUtil.getUrisFromResult(intent, getContentResolver()));
                            }
                            break;
                        case ThreemaActivity.ACTIVITY_ID_CREATE_BALLOT:
                            if (!ListenerUtil.mutListener.listen(29575)) {
                                finish();
                            }
                            break;
                        case ThreemaActivity.ACTIVITY_ID_SEND_MEDIA:
                            // catch last media filter and forward to compose message fragment
                            Intent resultIntent = new Intent();
                            if (!ListenerUtil.mutListener.listen(29579)) {
                                if ((ListenerUtil.mutListener.listen(29576) ? (intent != null || intent.hasExtra(ComposeMessageFragment.EXTRA_LAST_MEDIA_TYPE_QUERY)) : (intent != null && intent.hasExtra(ComposeMessageFragment.EXTRA_LAST_MEDIA_TYPE_QUERY)))) {
                                    if (!ListenerUtil.mutListener.listen(29577)) {
                                        IntentDataUtil.addLastMediaFilterToIntent(resultIntent, intent.getStringExtra(ComposeMessageFragment.EXTRA_LAST_MEDIA_SEARCH_QUERY), intent.getIntExtra(ComposeMessageFragment.EXTRA_LAST_MEDIA_TYPE_QUERY, -1));
                                    }
                                    if (!ListenerUtil.mutListener.listen(29578)) {
                                        setResult(RESULT_OK, resultIntent);
                                    }
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(29580)) {
                                finish();
                            }
                            break;
                        case ThreemaActivity.ACTIVITY_ID_PICK_FILE:
                            if (!ListenerUtil.mutListener.listen(29581)) {
                                prepareSendFileMessage(FileUtil.getUrisFromResult(intent, getContentResolver()));
                            }
                            break;
                    }
                }
            }
        }
    }

    // expandable alert dialog listeners
    @Override
    public void onYes(String tag, Object data, String text) {
        if (!ListenerUtil.mutListener.listen(29593)) {
            if (DIALOG_TAG_PREPARE_SEND_FILE.equals(tag)) {
                ArrayList<Uri> uriList = (ArrayList<Uri>) data;
                ArrayList<String> captions = new ArrayList<>(uriList.size());
                int i = 0;
                if (!ListenerUtil.mutListener.listen(29591)) {
                    {
                        long _loopCounter190 = 0;
                        while ((ListenerUtil.mutListener.listen(29590) ? (i >= uriList.size()) : (ListenerUtil.mutListener.listen(29589) ? (i <= uriList.size()) : (ListenerUtil.mutListener.listen(29588) ? (i > uriList.size()) : (ListenerUtil.mutListener.listen(29587) ? (i != uriList.size()) : (ListenerUtil.mutListener.listen(29586) ? (i == uriList.size()) : (i < uriList.size()))))))) {
                            ListenerUtil.loopListener.listen("_loopCounter190", ++_loopCounter190);
                            if (!ListenerUtil.mutListener.listen(29584)) {
                                captions.add(text);
                            }
                            if (!ListenerUtil.mutListener.listen(29585)) {
                                i++;
                            }
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(29592)) {
                    sendFileMessage(uriList, captions);
                }
            }
        }
    }

    @Override
    public void onNo(String tag) {
        FitWindowsFrameLayout contentFrameLayout = (FitWindowsFrameLayout) ((ViewGroup) rootView.getParent()).getParent();
        if (!ListenerUtil.mutListener.listen(29594)) {
            contentFrameLayout.setVisibility(View.VISIBLE);
        }
    }

    // Generic Alert Dialog Listeners
    @Override
    public void onYes(String tag, Object data) {
        if (!ListenerUtil.mutListener.listen(29597)) {
            if (CONFIRM_TAG_REALLY_SEND_FILE.equals(tag)) {
                if (!ListenerUtil.mutListener.listen(29595)) {
                    preferenceService.setFileSendInfoShown(true);
                }
                if (!ListenerUtil.mutListener.listen(29596)) {
                    FileUtil.selectFile(this, null, new String[] { MimeUtil.MIME_TYPE_ANY }, ThreemaActivity.ACTIVITY_ID_PICK_FILE, true, MAX_BLOB_SIZE, null);
                }
            }
        }
    }

    @Override
    public void onNo(String tag, Object data) {
    }

    @UiThread
    public void onEdit(final ArrayList<Uri> uriList) {
        ArrayList<MediaItem> mediaItems = new ArrayList<>(uriList.size());
        {
            long _loopCounter191 = 0;
            for (Uri uri : uriList) {
                ListenerUtil.loopListener.listen("_loopCounter191", ++_loopCounter191);
                String mimeType = FileUtil.getMimeTypeFromUri(this, uri);
                if ((ListenerUtil.mutListener.listen(29598) ? (MimeUtil.isVideoFile(mimeType) && MimeUtil.isImageFile(mimeType)) : (MimeUtil.isVideoFile(mimeType) || MimeUtil.isImageFile(mimeType)))) {
                    try {
                        if (!ListenerUtil.mutListener.listen(29600)) {
                            logger.info("Number of taken persistable uri permissions" + getContentResolver().getPersistedUriPermissions().size());
                        }
                        if (!ListenerUtil.mutListener.listen(29601)) {
                            getContentResolver().takePersistableUriPermission(uri, Intent.FLAG_GRANT_READ_URI_PERMISSION);
                        }
                    } catch (Exception e) {
                        if (!ListenerUtil.mutListener.listen(29599)) {
                            logger.info("Unable to take persistable uri permission ", e);
                        }
                        uri = FileUtil.getFileUri(uri);
                    }
                    MediaItem mediaItem = new MediaItem(uri, mimeType, null);
                    if (!ListenerUtil.mutListener.listen(29602)) {
                        mediaItem.setFilename(FileUtil.getFilenameFromUri(getContentResolver(), mediaItem));
                    }
                    if (!ListenerUtil.mutListener.listen(29603)) {
                        mediaItems.add(mediaItem);
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(29615)) {
            if ((ListenerUtil.mutListener.listen(29608) ? (mediaItems.size() >= 0) : (ListenerUtil.mutListener.listen(29607) ? (mediaItems.size() <= 0) : (ListenerUtil.mutListener.listen(29606) ? (mediaItems.size() < 0) : (ListenerUtil.mutListener.listen(29605) ? (mediaItems.size() != 0) : (ListenerUtil.mutListener.listen(29604) ? (mediaItems.size() == 0) : (mediaItems.size() > 0))))))) {
                Intent intent = IntentDataUtil.addMessageReceiversToIntent(new Intent(this, SendMediaActivity.class), new MessageReceiver[] { this.messageReceiver });
                if (!ListenerUtil.mutListener.listen(29610)) {
                    intent.putExtra(SendMediaActivity.EXTRA_MEDIA_ITEMS, mediaItems);
                }
                if (!ListenerUtil.mutListener.listen(29611)) {
                    intent.putExtra(ThreemaApplication.INTENT_DATA_TEXT, messageReceiver.getDisplayName());
                }
                if (!ListenerUtil.mutListener.listen(29613)) {
                    // pass on last filter to potentially re-use it when adding more media items
                    if (mediaAttachViewModel.getLastQuery() != null) {
                        if (!ListenerUtil.mutListener.listen(29612)) {
                            intent = IntentDataUtil.addLastMediaFilterToIntent(intent, mediaAttachViewModel.getLastQuery(), mediaAttachViewModel.getLastQueryType());
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(29614)) {
                    AnimationUtil.startActivityForResult(this, null, intent, ThreemaActivity.ACTIVITY_ID_SEND_MEDIA);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(29609)) {
                    Toast.makeText(MediaAttachActivity.this, R.string.only_images_or_videos, Toast.LENGTH_LONG).show();
                }
            }
        }
    }

    @UiThread
    public void onSend(final ArrayList<Uri> list) {
        List<MediaItem> mediaItems = new ArrayList<>();
        if (!ListenerUtil.mutListener.listen(29616)) {
            if (!validateSendingPermission()) {
                return;
            }
        }
        {
            long _loopCounter192 = 0;
            for (Uri uri : list) {
                ListenerUtil.loopListener.listen("_loopCounter192", ++_loopCounter192);
                try {
                    if (!ListenerUtil.mutListener.listen(29618)) {
                        // log the number of permissions due to limit https://commonsware.com/blog/2020/06/13/count-your-saf-uri-permission-grants.html
                        logger.info("Number of taken persistable uri permissions" + getContentResolver().getPersistedUriPermissions().size());
                    }
                    if (!ListenerUtil.mutListener.listen(29619)) {
                        getContentResolver().takePersistableUriPermission(uri, Intent.FLAG_GRANT_READ_URI_PERMISSION);
                    }
                } catch (Exception e) {
                    if (!ListenerUtil.mutListener.listen(29617)) {
                        logger.info("Unable to take persistable uri permission ", e);
                    }
                    uri = FileUtil.getFileUri(uri);
                }
                MediaItem mediaItem = new MediaItem(uri, FileUtil.getMimeTypeFromUri(this, uri), null);
                if (!ListenerUtil.mutListener.listen(29620)) {
                    mediaItem.setFilename(FileUtil.getFilenameFromUri(getContentResolver(), mediaItem));
                }
                if (!ListenerUtil.mutListener.listen(29621)) {
                    mediaItems.add(mediaItem);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(29629)) {
            if ((ListenerUtil.mutListener.listen(29626) ? (mediaItems.size() >= 0) : (ListenerUtil.mutListener.listen(29625) ? (mediaItems.size() <= 0) : (ListenerUtil.mutListener.listen(29624) ? (mediaItems.size() < 0) : (ListenerUtil.mutListener.listen(29623) ? (mediaItems.size() != 0) : (ListenerUtil.mutListener.listen(29622) ? (mediaItems.size() == 0) : (mediaItems.size() > 0))))))) {
                if (!ListenerUtil.mutListener.listen(29627)) {
                    messageService.sendMediaAsync(mediaItems, Collections.singletonList(messageReceiver));
                }
                if (!ListenerUtil.mutListener.listen(29628)) {
                    finish();
                }
            }
        }
    }

    private void attachFile() {
        if (!ListenerUtil.mutListener.listen(29633)) {
            if ((ListenerUtil.mutListener.listen(29630) ? (preferenceService != null || !preferenceService.getFileSendInfoShown()) : (preferenceService != null && !preferenceService.getFileSendInfoShown()))) {
                GenericAlertDialog dialog = GenericAlertDialog.newInstance(R.string.send_as_files, R.string.send_as_files_warning, R.string.ok, R.string.cancel);
                if (!ListenerUtil.mutListener.listen(29632)) {
                    dialog.show(getSupportFragmentManager(), CONFIRM_TAG_REALLY_SEND_FILE);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(29631)) {
                    FileUtil.selectFile(this, null, new String[] { MimeUtil.MIME_TYPE_ANY }, ThreemaActivity.ACTIVITY_ID_PICK_FILE, true, MAX_BLOB_SIZE, null);
                }
            }
        }
    }

    private void attachFromExternalCamera() {
        Intent intent = IntentDataUtil.addMessageReceiversToIntent(new Intent(this, SendMediaActivity.class), new MessageReceiver[] { this.messageReceiver });
        if (!ListenerUtil.mutListener.listen(29634)) {
            intent.putExtra(ThreemaApplication.INTENT_DATA_TEXT, messageReceiver.getDisplayName());
        }
        if (!ListenerUtil.mutListener.listen(29635)) {
            intent.putExtra(ThreemaApplication.INTENT_DATA_PICK_FROM_CAMERA, true);
        }
        if (!ListenerUtil.mutListener.listen(29636)) {
            intent.putExtra(SendMediaActivity.EXTRA_USE_EXTERNAL_CAMERA, true);
        }
        if (!ListenerUtil.mutListener.listen(29637)) {
            AnimationUtil.startActivityForResult(this, null, intent, ThreemaActivity.ACTIVITY_ID_SEND_MEDIA);
        }
    }

    private void createBallot() {
        Intent intent = new Intent(this, BallotWizardActivity.class);
        if (!ListenerUtil.mutListener.listen(29638)) {
            IntentDataUtil.addMessageReceiverToIntent(intent, messageReceiver);
        }
        if (!ListenerUtil.mutListener.listen(29639)) {
            AnimationUtil.startActivityForResult(this, null, intent, ThreemaActivity.ACTIVITY_ID_CREATE_BALLOT);
        }
    }

    private void launchPlacePicker() {
        Intent intent = new Intent(this, LocationPickerActivity.class);
        if (!ListenerUtil.mutListener.listen(29640)) {
            AnimationUtil.startActivityForResult(this, null, intent, LOCATION_PICKER_INTENT);
        }
    }

    private void attachContact() {
        try {
            Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
            if (!ListenerUtil.mutListener.listen(29642)) {
                AnimationUtil.startActivityForResult(this, null, intent, CONTACT_PICKER_INTENT);
            }
        } catch (ActivityNotFoundException e) {
            if (!ListenerUtil.mutListener.listen(29641)) {
                SingleToast.getInstance().showShortText(getString(R.string.no_activity_for_mime_type));
            }
        }
    }

    private void attachQR(View v) {
        if (!ListenerUtil.mutListener.listen(29644)) {
            v.postDelayed(new Runnable() {

                @Override
                public void run() {
                    if (!ListenerUtil.mutListener.listen(29643)) {
                        QRScannerUtil.getInstance().initiateScan(MediaAttachActivity.this, true, null);
                    }
                }
            }, 200);
        }
    }

    private void prepareSendFileMessage(final ArrayList<Uri> uriList) {
        FitWindowsFrameLayout contentFrameLayout = (FitWindowsFrameLayout) ((ViewGroup) rootView.getParent()).getParent();
        if (!ListenerUtil.mutListener.listen(29645)) {
            contentFrameLayout.setVisibility(View.GONE);
        }
        ExpandableTextEntryDialog alertDialog = ExpandableTextEntryDialog.newInstance(getString(R.string.send_as_files), R.string.add_caption_hint, R.string.send, R.string.cancel, true);
        if (!ListenerUtil.mutListener.listen(29646)) {
            alertDialog.setData(uriList);
        }
        if (!ListenerUtil.mutListener.listen(29647)) {
            alertDialog.show(getSupportFragmentManager(), DIALOG_TAG_PREPARE_SEND_FILE);
        }
    }

    private void sendLocationMessage(final Location location, final String poiName) {
        if (!ListenerUtil.mutListener.listen(29648)) {
            if (!validateSendingPermission()) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(29649)) {
            new Thread(() -> LocationMessageSendAction.getInstance().sendLocationMessage(new MessageReceiver[] { messageReceiver }, location, poiName, new SendAction.ActionHandler() {

                @Override
                public void onError(String errorMessage) {
                }

                @Override
                public void onWarning(String warning, boolean continueAction) {
                }

                @Override
                public void onProgress(int progress, int total) {
                }

                @Override
                public void onCompleted() {
                    finish();
                }
            })).start();
        }
    }

    private void sendContact(Uri contactUri) {
        if (!ListenerUtil.mutListener.listen(29650)) {
            if (!validateSendingPermission()) {
                return;
            }
        }
        Cursor cursor = this.getContentResolver().query(contactUri, null, null, null, null);
        if (!ListenerUtil.mutListener.listen(29655)) {
            if ((ListenerUtil.mutListener.listen(29651) ? (cursor != null || cursor.moveToFirst()) : (cursor != null && cursor.moveToFirst()))) {
                String lookupKey = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.LOOKUP_KEY));
                if (!ListenerUtil.mutListener.listen(29653)) {
                    cursor.close();
                }
                Uri vcardUri = Uri.withAppendedPath(ContactsContract.Contacts.CONTENT_VCARD_URI, lookupKey);
                if (!ListenerUtil.mutListener.listen(29654)) {
                    sendFileMessage(new ArrayList<>(Collections.singletonList(vcardUri)), null);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(29652)) {
                    Toast.makeText(this, R.string.contact_not_found, Toast.LENGTH_LONG).show();
                }
            }
        }
    }

    /**
     *  Send file messages of any type
     *  @param uriList
     *  @param captions
     */
    private void sendFileMessage(final ArrayList<Uri> uriList, final ArrayList<String> captions) {
        if (!ListenerUtil.mutListener.listen(29656)) {
            if (!validateSendingPermission()) {
                return;
            }
        }
        List<MediaItem> mediaItems = new ArrayList<>(uriList.size());
        if (!ListenerUtil.mutListener.listen(29665)) {
            {
                long _loopCounter193 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(29664) ? (i >= uriList.size()) : (ListenerUtil.mutListener.listen(29663) ? (i <= uriList.size()) : (ListenerUtil.mutListener.listen(29662) ? (i > uriList.size()) : (ListenerUtil.mutListener.listen(29661) ? (i != uriList.size()) : (ListenerUtil.mutListener.listen(29660) ? (i == uriList.size()) : (i < uriList.size())))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter193", ++_loopCounter193);
                    MediaItem mediaItem = new MediaItem(uriList.get(i), MediaItem.TYPE_FILE);
                    if (!ListenerUtil.mutListener.listen(29658)) {
                        if (captions != null) {
                            if (!ListenerUtil.mutListener.listen(29657)) {
                                mediaItem.setCaption(captions.get(i));
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(29659)) {
                        mediaItems.add(mediaItem);
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(29666)) {
            messageService.sendMediaAsync(mediaItems, Collections.singletonList(messageReceiver));
        }
        if (!ListenerUtil.mutListener.listen(29667)) {
            finish();
        }
    }

    private boolean validateSendingPermission() {
        return (ListenerUtil.mutListener.listen(29668) ? (this.messageReceiver != null || this.messageReceiver.validateSendingPermission(errorResId -> RuntimeUtil.runOnUiThread(() -> SingleToast.getInstance().showLongText(getString(errorResId))))) : (this.messageReceiver != null && this.messageReceiver.validateSendingPermission(errorResId -> RuntimeUtil.runOnUiThread(() -> SingleToast.getInstance().showLongText(getString(errorResId))))));
    }
}
