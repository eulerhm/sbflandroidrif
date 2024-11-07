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

import android.animation.LayoutTransition;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Date;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.UiThread;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import ch.threema.app.R;
import ch.threema.app.ThreemaApplication;
import ch.threema.app.activities.ExportIDActivity;
import ch.threema.app.activities.ProfilePicRecipientsActivity;
import ch.threema.app.activities.ThreemaActivity;
import ch.threema.app.asynctasks.DeleteIdentityAsyncTask;
import ch.threema.app.asynctasks.LinkWithEmailAsyncTask;
import ch.threema.app.dialogs.GenericAlertDialog;
import ch.threema.app.dialogs.GenericProgressDialog;
import ch.threema.app.dialogs.PasswordEntryDialog;
import ch.threema.app.dialogs.SimpleStringAlertDialog;
import ch.threema.app.dialogs.TextEntryDialog;
import ch.threema.app.emojis.EmojiTextView;
import ch.threema.app.listeners.ProfileListener;
import ch.threema.app.listeners.SMSVerificationListener;
import ch.threema.app.managers.ListenerManager;
import ch.threema.app.managers.ServiceManager;
import ch.threema.app.routines.CheckIdentityRoutine;
import ch.threema.app.services.ContactService;
import ch.threema.app.services.FileService;
import ch.threema.app.services.FingerPrintService;
import ch.threema.app.services.LocaleService;
import ch.threema.app.services.PreferenceService;
import ch.threema.app.services.UserService;
import ch.threema.app.ui.AvatarEditView;
import ch.threema.app.ui.ImagePopup;
import ch.threema.app.ui.QRCodePopup;
import ch.threema.app.utils.AnimationUtil;
import ch.threema.app.utils.AppRestrictionUtil;
import ch.threema.app.utils.ConfigUtils;
import ch.threema.app.utils.DialogUtil;
import ch.threema.app.utils.HiddenChatUtil;
import ch.threema.app.utils.LocaleUtil;
import ch.threema.app.utils.LogUtil;
import ch.threema.app.utils.RuntimeUtil;
import ch.threema.app.utils.ShareUtil;
import ch.threema.app.utils.TestUtil;
import ch.threema.base.ThreemaException;
import ch.threema.client.LinkMobileNoException;
import ch.threema.client.ProtocolDefines;
import ch.threema.localcrypto.MasterKeyLockedException;
import static ch.threema.app.ThreemaApplication.EMAIL_LINKED_PLACEHOLDER;
import static ch.threema.app.ThreemaApplication.PHONE_LINKED_PLACEHOLDER;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class MyIDFragment extends MainFragment implements View.OnClickListener, GenericAlertDialog.DialogClickListener, TextEntryDialog.TextEntryDialogClickListener, PasswordEntryDialog.PasswordEntryDialogClickListener {

    private static final Logger logger = LoggerFactory.getLogger(MyIDFragment.class);

    private static final int MAX_REVOCATION_PASSWORD_LENGTH = 256;

    private static final int LOCK_CHECK_REVOCATION = 33;

    private static final int LOCK_CHECK_DELETE_ID = 34;

    private static final int LOCK_CHECK_EXPORT_ID = 35;

    private ServiceManager serviceManager;

    private UserService userService;

    private PreferenceService preferenceService;

    private FingerPrintService fingerPrintService;

    private LocaleService localeService;

    private ContactService contactService;

    private FileService fileService;

    private AvatarEditView avatarView;

    private EmojiTextView nicknameTextView;

    private boolean hidden = false;

    private View fragmentView;

    private boolean isReadonlyProfile = false;

    private boolean isDisabledProfilePicReleaseSettings = false;

    private static final String DIALOG_TAG_EDIT_NICKNAME = "cedit";

    private static final String DIALOG_TAG_SET_REVOCATION_KEY = "setRevocationKey";

    private static final String DIALOG_TAG_LINKED_EMAIL = "linkedEmail";

    private static final String DIALOG_TAG_LINKED_MOBILE = "linkedMobile";

    private static final String DIALOG_TAG_REALLY_DELETE = "reallyDeleteId";

    private static final String DIALOG_TAG_DELETE_ID = "deleteId";

    private static final String DIALOG_TAG_LINKED_MOBILE_CONFIRM = "cfm";

    private static final String DIALOG_TAG_REVOKING = "revk";

    private final SMSVerificationListener smsVerificationListener = new SMSVerificationListener() {

        @Override
        public void onVerified() {
            if (!ListenerUtil.mutListener.listen(27726)) {
                RuntimeUtil.runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        if (!ListenerUtil.mutListener.listen(27725)) {
                            updatePendingState(getView(), false);
                        }
                    }
                });
            }
        }

        @Override
        public void onVerificationStarted() {
            if (!ListenerUtil.mutListener.listen(27728)) {
                RuntimeUtil.runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        if (!ListenerUtil.mutListener.listen(27727)) {
                            updatePendingState(getView(), false);
                        }
                    }
                });
            }
        }
    };

    private final ProfileListener profileListener = new ProfileListener() {

        @Override
        public void onAvatarChanged() {
            if (!ListenerUtil.mutListener.listen(27738)) {
                // a profile picture has been set so it's safe to assume user wants others to see his pic
                if (!isDisabledProfilePicReleaseSettings) {
                    if (!ListenerUtil.mutListener.listen(27737)) {
                        if ((ListenerUtil.mutListener.listen(27729) ? (preferenceService != null || preferenceService.getProfilePicRelease() == PreferenceService.PROFILEPIC_RELEASE_NOBODY) : (preferenceService != null && preferenceService.getProfilePicRelease() == PreferenceService.PROFILEPIC_RELEASE_NOBODY))) {
                            if (!ListenerUtil.mutListener.listen(27730)) {
                                preferenceService.setProfilePicRelease(PreferenceService.PROFILEPIC_RELEASE_EVERYONE);
                            }
                            if (!ListenerUtil.mutListener.listen(27736)) {
                                RuntimeUtil.runOnUiThread(new Runnable() {

                                    @Override
                                    public void run() {
                                        if (!ListenerUtil.mutListener.listen(27735)) {
                                            if ((ListenerUtil.mutListener.listen(27732) ? ((ListenerUtil.mutListener.listen(27731) ? (isAdded() || !isDetached()) : (isAdded() && !isDetached())) || fragmentView != null) : ((ListenerUtil.mutListener.listen(27731) ? (isAdded() || !isDetached()) : (isAdded() && !isDetached())) && fragmentView != null))) {
                                                AppCompatSpinner spinner = fragmentView.findViewById(R.id.picrelease_spinner);
                                                if (!ListenerUtil.mutListener.listen(27734)) {
                                                    if (spinner != null) {
                                                        if (!ListenerUtil.mutListener.listen(27733)) {
                                                            spinner.setSelection(preferenceService.getProfilePicRelease());
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                });
                            }
                        }
                    }
                }
            }
        }

        @Override
        public void onAvatarRemoved() {
        }

        @Override
        public void onNicknameChanged(String newNickname) {
            if (!ListenerUtil.mutListener.listen(27739)) {
                RuntimeUtil.runOnUiThread(() -> reloadNickname());
            }
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(27740)) {
            super.onCreate(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(27741)) {
            setRetainInstance(true);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(27743)) {
            if (!this.requiredInstances()) {
                if (!ListenerUtil.mutListener.listen(27742)) {
                    logger.error("could not instantiate required objects");
                }
                return null;
            }
        }
        if (!ListenerUtil.mutListener.listen(27744)) {
            fragmentView = getView();
        }
        if (!ListenerUtil.mutListener.listen(27801)) {
            if (fragmentView == null) {
                if (!ListenerUtil.mutListener.listen(27745)) {
                    fragmentView = inflater.inflate(R.layout.fragment_my_id, container, false);
                }
                if (!ListenerUtil.mutListener.listen(27746)) {
                    this.updatePendingState(fragmentView, true);
                }
                LayoutTransition l = new LayoutTransition();
                if (!ListenerUtil.mutListener.listen(27747)) {
                    l.enableTransitionType(LayoutTransition.CHANGING);
                }
                ViewGroup viewGroup = fragmentView.findViewById(R.id.fragment_id_container);
                if (!ListenerUtil.mutListener.listen(27748)) {
                    viewGroup.setLayoutTransition(l);
                }
                if (!ListenerUtil.mutListener.listen(27754)) {
                    if (ConfigUtils.isWorkRestricted()) {
                        Boolean value = AppRestrictionUtil.getBooleanRestriction(getString(R.string.restriction__readonly_profile));
                        if (!ListenerUtil.mutListener.listen(27750)) {
                            if (value != null) {
                                if (!ListenerUtil.mutListener.listen(27749)) {
                                    isReadonlyProfile = value;
                                }
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(27751)) {
                            value = AppRestrictionUtil.getBooleanRestriction(getString(R.string.restriction__disable_send_profile_picture));
                        }
                        if (!ListenerUtil.mutListener.listen(27753)) {
                            if (value != null) {
                                if (!ListenerUtil.mutListener.listen(27752)) {
                                    isDisabledProfilePicReleaseSettings = value;
                                }
                            }
                        }
                    }
                }
                TextView textView = fragmentView.findViewById(R.id.keyfingerprint);
                if (!ListenerUtil.mutListener.listen(27755)) {
                    textView.setText(fingerPrintService.getFingerPrint(getIdentity()));
                }
                if (!ListenerUtil.mutListener.listen(27758)) {
                    fragmentView.findViewById(R.id.policy_explain).setVisibility((ListenerUtil.mutListener.listen(27757) ? ((ListenerUtil.mutListener.listen(27756) ? (isReadonlyProfile && AppRestrictionUtil.isBackupsDisabled(ThreemaApplication.getAppContext())) : (isReadonlyProfile || AppRestrictionUtil.isBackupsDisabled(ThreemaApplication.getAppContext()))) && AppRestrictionUtil.isIdBackupsDisabled(ThreemaApplication.getAppContext())) : ((ListenerUtil.mutListener.listen(27756) ? (isReadonlyProfile && AppRestrictionUtil.isBackupsDisabled(ThreemaApplication.getAppContext())) : (isReadonlyProfile || AppRestrictionUtil.isBackupsDisabled(ThreemaApplication.getAppContext()))) || AppRestrictionUtil.isIdBackupsDisabled(ThreemaApplication.getAppContext()))) ? View.VISIBLE : View.GONE);
                }
                final ImageView picReleaseConfImageView = fragmentView.findViewById(R.id.picrelease_config);
                if (!ListenerUtil.mutListener.listen(27759)) {
                    picReleaseConfImageView.setOnClickListener(this);
                }
                if (!ListenerUtil.mutListener.listen(27760)) {
                    picReleaseConfImageView.setVisibility(preferenceService.getProfilePicRelease() == PreferenceService.PROFILEPIC_RELEASE_SOME ? View.VISIBLE : View.GONE);
                }
                if (!ListenerUtil.mutListener.listen(27761)) {
                    configureEditWithButton(fragmentView.findViewById(R.id.linked_email_layout), fragmentView.findViewById(R.id.change_email), isReadonlyProfile);
                }
                if (!ListenerUtil.mutListener.listen(27762)) {
                    configureEditWithButton(fragmentView.findViewById(R.id.linked_mobile_layout), fragmentView.findViewById(R.id.change_mobile), isReadonlyProfile);
                }
                if (!ListenerUtil.mutListener.listen(27763)) {
                    configureEditWithButton(fragmentView.findViewById(R.id.delete_id_layout), fragmentView.findViewById(R.id.delete_id), isReadonlyProfile);
                }
                if (!ListenerUtil.mutListener.listen(27764)) {
                    configureEditWithButton(fragmentView.findViewById(R.id.revocation_key_layout), fragmentView.findViewById(R.id.revocation_key), isReadonlyProfile);
                }
                if (!ListenerUtil.mutListener.listen(27766)) {
                    configureEditWithButton(fragmentView.findViewById(R.id.export_id_layout), fragmentView.findViewById(R.id.export_id), ((ListenerUtil.mutListener.listen(27765) ? (AppRestrictionUtil.isBackupsDisabled(ThreemaApplication.getAppContext()) && AppRestrictionUtil.isIdBackupsDisabled(ThreemaApplication.getAppContext())) : (AppRestrictionUtil.isBackupsDisabled(ThreemaApplication.getAppContext()) || AppRestrictionUtil.isIdBackupsDisabled(ThreemaApplication.getAppContext())))));
                }
                if (!ListenerUtil.mutListener.listen(27771)) {
                    if ((ListenerUtil.mutListener.listen(27767) ? (userService != null || userService.getIdentity() != null) : (userService != null && userService.getIdentity() != null))) {
                        if (!ListenerUtil.mutListener.listen(27768)) {
                            ((TextView) fragmentView.findViewById(R.id.my_id)).setText(userService.getIdentity());
                        }
                        if (!ListenerUtil.mutListener.listen(27769)) {
                            fragmentView.findViewById(R.id.my_id_share).setOnClickListener(this);
                        }
                        if (!ListenerUtil.mutListener.listen(27770)) {
                            fragmentView.findViewById(R.id.my_id_qr).setOnClickListener(this);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(27772)) {
                    this.avatarView = fragmentView.findViewById(R.id.avatar_edit_view);
                }
                if (!ListenerUtil.mutListener.listen(27773)) {
                    this.avatarView.setFragment(this);
                }
                if (!ListenerUtil.mutListener.listen(27774)) {
                    this.avatarView.setIsMyProfilePicture(true);
                }
                if (!ListenerUtil.mutListener.listen(27775)) {
                    this.avatarView.setContactModel(contactService.getMe());
                }
                if (!ListenerUtil.mutListener.listen(27776)) {
                    this.nicknameTextView = fragmentView.findViewById(R.id.nickname);
                }
                if (!ListenerUtil.mutListener.listen(27781)) {
                    if (isReadonlyProfile) {
                        if (!ListenerUtil.mutListener.listen(27779)) {
                            this.fragmentView.findViewById(R.id.profile_edit).setVisibility(View.GONE);
                        }
                        if (!ListenerUtil.mutListener.listen(27780)) {
                            this.avatarView.setEditable(false);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(27777)) {
                            this.fragmentView.findViewById(R.id.profile_edit).setVisibility(View.VISIBLE);
                        }
                        if (!ListenerUtil.mutListener.listen(27778)) {
                            this.fragmentView.findViewById(R.id.profile_edit).setOnClickListener(this);
                        }
                    }
                }
                AppCompatSpinner spinner = fragmentView.findViewById(R.id.picrelease_spinner);
                ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(), R.array.picrelease_choices, android.R.layout.simple_spinner_item);
                if (!ListenerUtil.mutListener.listen(27782)) {
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                }
                if (!ListenerUtil.mutListener.listen(27783)) {
                    spinner.setAdapter(adapter);
                }
                if (!ListenerUtil.mutListener.listen(27784)) {
                    spinner.setSelection(preferenceService.getProfilePicRelease());
                }
                if (!ListenerUtil.mutListener.listen(27795)) {
                    spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            int oldPosition = preferenceService.getProfilePicRelease();
                            if (!ListenerUtil.mutListener.listen(27785)) {
                                preferenceService.setProfilePicRelease(position);
                            }
                            if (!ListenerUtil.mutListener.listen(27786)) {
                                picReleaseConfImageView.setVisibility(position == PreferenceService.PROFILEPIC_RELEASE_SOME ? View.VISIBLE : View.GONE);
                            }
                            if (!ListenerUtil.mutListener.listen(27794)) {
                                if ((ListenerUtil.mutListener.listen(27792) ? (position == PreferenceService.PROFILEPIC_RELEASE_SOME || (ListenerUtil.mutListener.listen(27791) ? (position >= oldPosition) : (ListenerUtil.mutListener.listen(27790) ? (position <= oldPosition) : (ListenerUtil.mutListener.listen(27789) ? (position > oldPosition) : (ListenerUtil.mutListener.listen(27788) ? (position < oldPosition) : (ListenerUtil.mutListener.listen(27787) ? (position == oldPosition) : (position != oldPosition))))))) : (position == PreferenceService.PROFILEPIC_RELEASE_SOME && (ListenerUtil.mutListener.listen(27791) ? (position >= oldPosition) : (ListenerUtil.mutListener.listen(27790) ? (position <= oldPosition) : (ListenerUtil.mutListener.listen(27789) ? (position > oldPosition) : (ListenerUtil.mutListener.listen(27788) ? (position < oldPosition) : (ListenerUtil.mutListener.listen(27787) ? (position == oldPosition) : (position != oldPosition))))))))) {
                                    if (!ListenerUtil.mutListener.listen(27793)) {
                                        launchProfilePictureRecipientsSelector(view);
                                    }
                                }
                            }
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                        }
                    });
                }
                if (!ListenerUtil.mutListener.listen(27799)) {
                    if (isDisabledProfilePicReleaseSettings) {
                        if (!ListenerUtil.mutListener.listen(27796)) {
                            fragmentView.findViewById(R.id.picrelease_spinner).setVisibility(View.GONE);
                        }
                        if (!ListenerUtil.mutListener.listen(27797)) {
                            fragmentView.findViewById(R.id.picrelease_config).setVisibility(View.GONE);
                        }
                        if (!ListenerUtil.mutListener.listen(27798)) {
                            fragmentView.findViewById(R.id.picrelease_text).setVisibility(View.GONE);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(27800)) {
                    reloadNickname();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(27802)) {
            ListenerManager.profileListeners.add(this.profileListener);
        }
        return fragmentView;
    }

    @Override
    public void onStart() {
        if (!ListenerUtil.mutListener.listen(27803)) {
            super.onStart();
        }
        if (!ListenerUtil.mutListener.listen(27804)) {
            ListenerManager.smsVerificationListeners.add(this.smsVerificationListener);
        }
    }

    @Override
    public void onStop() {
        if (!ListenerUtil.mutListener.listen(27805)) {
            ListenerManager.smsVerificationListeners.remove(this.smsVerificationListener);
        }
        if (!ListenerUtil.mutListener.listen(27806)) {
            super.onStop();
        }
    }

    @Override
    public void onDestroyView() {
        if (!ListenerUtil.mutListener.listen(27807)) {
            ListenerManager.profileListeners.remove(this.profileListener);
        }
        if (!ListenerUtil.mutListener.listen(27808)) {
            super.onDestroyView();
        }
    }

    private void updatePendingState(final View fragmentView, boolean force) {
        if (!ListenerUtil.mutListener.listen(27809)) {
            logger.debug("*** updatePendingState");
        }
        if (!ListenerUtil.mutListener.listen(27810)) {
            if (!this.requiredInstances()) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(27813)) {
            // update texts and enforce another update if the status of one value is pending
            if ((ListenerUtil.mutListener.listen(27811) ? (updatePendingStateTexts(fragmentView) && force) : (updatePendingStateTexts(fragmentView) || force))) {
                if (!ListenerUtil.mutListener.listen(27812)) {
                    new Thread(new CheckIdentityRoutine(userService, success -> {
                        // update after routine
                        RuntimeUtil.runOnUiThread(new Runnable() {

                            @Override
                            public void run() {
                                updatePendingStateTexts(fragmentView);
                            }
                        });
                    })).start();
                }
            }
        }
    }

    @SuppressLint("StaticFieldLeak")
    private boolean updatePendingStateTexts(View fragmentView) {
        boolean pending = false;
        if (!ListenerUtil.mutListener.listen(27814)) {
            logger.debug("*** updatePendingStateTexts");
        }
        if (!ListenerUtil.mutListener.listen(27815)) {
            if (!this.requiredInstances()) {
                return false;
            }
        }
        if (!ListenerUtil.mutListener.listen(27818)) {
            if ((ListenerUtil.mutListener.listen(27817) ? ((ListenerUtil.mutListener.listen(27816) ? (!isAdded() && isDetached()) : (!isAdded() || isDetached())) && isRemoving()) : ((ListenerUtil.mutListener.listen(27816) ? (!isAdded() && isDetached()) : (!isAdded() || isDetached())) || isRemoving()))) {
                return false;
            }
        }
        // update email linked text
        TextView linkedEmailText = fragmentView.findViewById(R.id.linked_email);
        String email = this.userService.getLinkedEmail();
        if (!ListenerUtil.mutListener.listen(27819)) {
            email = EMAIL_LINKED_PLACEHOLDER.equals(email) ? getString(R.string.unchanged) : email;
        }
        if (!ListenerUtil.mutListener.listen(27824)) {
            switch(userService.getEmailLinkingState()) {
                case UserService.LinkingState_LINKED:
                    if (!ListenerUtil.mutListener.listen(27820)) {
                        linkedEmailText.setText(email + " (" + getString(R.string.verified) + ")");
                    }
                    // nothing;
                    break;
                case UserService.LinkingState_PENDING:
                    if (!ListenerUtil.mutListener.listen(27821)) {
                        linkedEmailText.setText(email + " (" + getString(R.string.pending) + ")");
                    }
                    if (!ListenerUtil.mutListener.listen(27822)) {
                        pending = true;
                    }
                    break;
                default:
                    if (!ListenerUtil.mutListener.listen(27823)) {
                        linkedEmailText.setText(getString(R.string.not_linked));
                    }
            }
        }
        if (!ListenerUtil.mutListener.listen(27825)) {
            linkedEmailText.invalidate();
        }
        // update mobile text
        TextView linkedMobileText = fragmentView.findViewById(R.id.linked_mobile);
        if (!ListenerUtil.mutListener.listen(27826)) {
            // default
            linkedMobileText.setText(getString(R.string.not_linked));
        }
        String mobileNumber = this.userService.getLinkedMobile();
        if (!ListenerUtil.mutListener.listen(27827)) {
            mobileNumber = PHONE_LINKED_PLACEHOLDER.equals(mobileNumber) ? getString(R.string.unchanged) : mobileNumber;
        }
        if (!ListenerUtil.mutListener.listen(27850)) {
            switch(userService.getMobileLinkingState()) {
                case UserService.LinkingState_LINKED:
                    if (!ListenerUtil.mutListener.listen(27837)) {
                        if (mobileNumber != null) {
                            final String newMobileNumber = mobileNumber;
                            if (!ListenerUtil.mutListener.listen(27836)) {
                                // lookup phone numbers asynchronously
                                new AsyncTask<TextView, Void, String>() {

                                    private TextView textView;

                                    @Override
                                    protected String doInBackground(TextView... params) {
                                        if (!ListenerUtil.mutListener.listen(27828)) {
                                            textView = params[0];
                                        }
                                        if (!ListenerUtil.mutListener.listen(27830)) {
                                            if ((ListenerUtil.mutListener.listen(27829) ? (isAdded() || getContext() != null) : (isAdded() && getContext() != null))) {
                                                final String verified = getContext().getString(R.string.verified);
                                                return localeService.getHRPhoneNumber(newMobileNumber) + " (" + verified + ")";
                                            }
                                        }
                                        return null;
                                    }

                                    @Override
                                    protected void onPostExecute(String result) {
                                        if (!ListenerUtil.mutListener.listen(27835)) {
                                            if ((ListenerUtil.mutListener.listen(27833) ? ((ListenerUtil.mutListener.listen(27832) ? ((ListenerUtil.mutListener.listen(27831) ? (isAdded() || !isDetached()) : (isAdded() && !isDetached())) || !isRemoving()) : ((ListenerUtil.mutListener.listen(27831) ? (isAdded() || !isDetached()) : (isAdded() && !isDetached())) && !isRemoving())) || getContext() != null) : ((ListenerUtil.mutListener.listen(27832) ? ((ListenerUtil.mutListener.listen(27831) ? (isAdded() || !isDetached()) : (isAdded() && !isDetached())) || !isRemoving()) : ((ListenerUtil.mutListener.listen(27831) ? (isAdded() || !isDetached()) : (isAdded() && !isDetached())) && !isRemoving())) && getContext() != null))) {
                                                if (!ListenerUtil.mutListener.listen(27834)) {
                                                    textView.setText(result);
                                                }
                                            }
                                        }
                                    }
                                }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, linkedMobileText);
                            }
                        }
                    }
                    break;
                case UserService.LinkingState_PENDING:
                    if (!ListenerUtil.mutListener.listen(27838)) {
                        pending = true;
                    }
                    final String newMobileNumber = this.userService.getLinkedMobile(true);
                    if (!ListenerUtil.mutListener.listen(27849)) {
                        if (newMobileNumber != null) {
                            if (!ListenerUtil.mutListener.listen(27848)) {
                                new AsyncTask<TextView, Void, String>() {

                                    private TextView textView;

                                    @Override
                                    protected String doInBackground(TextView... params) {
                                        if (!ListenerUtil.mutListener.listen(27842)) {
                                            if ((ListenerUtil.mutListener.listen(27840) ? ((ListenerUtil.mutListener.listen(27839) ? (isAdded() || getContext() != null) : (isAdded() && getContext() != null)) || params != null) : ((ListenerUtil.mutListener.listen(27839) ? (isAdded() || getContext() != null) : (isAdded() && getContext() != null)) && params != null))) {
                                                if (!ListenerUtil.mutListener.listen(27841)) {
                                                    textView = params[0];
                                                }
                                                return (localeService != null ? localeService.getHRPhoneNumber(newMobileNumber) : "") + " (" + getContext().getString(R.string.pending) + ")";
                                            }
                                        }
                                        return null;
                                    }

                                    @Override
                                    protected void onPostExecute(String result) {
                                        if (!ListenerUtil.mutListener.listen(27847)) {
                                            if ((ListenerUtil.mutListener.listen(27845) ? ((ListenerUtil.mutListener.listen(27844) ? ((ListenerUtil.mutListener.listen(27843) ? (isAdded() || !isDetached()) : (isAdded() && !isDetached())) || !isRemoving()) : ((ListenerUtil.mutListener.listen(27843) ? (isAdded() || !isDetached()) : (isAdded() && !isDetached())) && !isRemoving())) || getContext() != null) : ((ListenerUtil.mutListener.listen(27844) ? ((ListenerUtil.mutListener.listen(27843) ? (isAdded() || !isDetached()) : (isAdded() && !isDetached())) || !isRemoving()) : ((ListenerUtil.mutListener.listen(27843) ? (isAdded() || !isDetached()) : (isAdded() && !isDetached())) && !isRemoving())) && getContext() != null))) {
                                                if (!ListenerUtil.mutListener.listen(27846)) {
                                                    textView.setText(result);
                                                }
                                            }
                                        }
                                    }
                                }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, linkedMobileText);
                            }
                        }
                    }
                    break;
                default:
            }
        }
        if (!ListenerUtil.mutListener.listen(27851)) {
            linkedMobileText.invalidate();
        }
        // revocation key
        TextView revocationKey = fragmentView.findViewById(R.id.revocation_key_sum);
        if (!ListenerUtil.mutListener.listen(27863)) {
            new AsyncTask<TextView, Void, String>() {

                private TextView textView;

                @Override
                protected String doInBackground(TextView... params) {
                    if (!ListenerUtil.mutListener.listen(27857)) {
                        if (isAdded()) {
                            if (!ListenerUtil.mutListener.listen(27852)) {
                                textView = params[0];
                            }
                            Date revocationKeyLastSet = userService.getLastRevocationKeySet();
                            if (!ListenerUtil.mutListener.listen(27856)) {
                                if ((ListenerUtil.mutListener.listen(27854) ? ((ListenerUtil.mutListener.listen(27853) ? (!isDetached() || !isRemoving()) : (!isDetached() && !isRemoving())) || getContext() != null) : ((ListenerUtil.mutListener.listen(27853) ? (!isDetached() || !isRemoving()) : (!isDetached() && !isRemoving())) && getContext() != null))) {
                                    if (!ListenerUtil.mutListener.listen(27855)) {
                                        if (revocationKeyLastSet != null) {
                                            return getContext().getString(R.string.revocation_key_set_at, LocaleUtil.formatTimeStampString(getContext(), revocationKeyLastSet.getTime(), true));
                                        } else {
                                            return getContext().getString(R.string.revocation_key_not_set);
                                        }
                                    }
                                }
                            }
                        }
                    }
                    return null;
                }

                @Override
                protected void onPostExecute(String result) {
                    if (!ListenerUtil.mutListener.listen(27862)) {
                        if ((ListenerUtil.mutListener.listen(27860) ? ((ListenerUtil.mutListener.listen(27859) ? ((ListenerUtil.mutListener.listen(27858) ? (isAdded() || !isDetached()) : (isAdded() && !isDetached())) || !isRemoving()) : ((ListenerUtil.mutListener.listen(27858) ? (isAdded() || !isDetached()) : (isAdded() && !isDetached())) && !isRemoving())) || getContext() != null) : ((ListenerUtil.mutListener.listen(27859) ? ((ListenerUtil.mutListener.listen(27858) ? (isAdded() || !isDetached()) : (isAdded() && !isDetached())) || !isRemoving()) : ((ListenerUtil.mutListener.listen(27858) ? (isAdded() || !isDetached()) : (isAdded() && !isDetached())) && !isRemoving())) && getContext() != null))) {
                            if (!ListenerUtil.mutListener.listen(27861)) {
                                textView.setText(result);
                            }
                        }
                    }
                }
            }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, revocationKey);
        }
        return pending;
    }

    private void configureEditWithButton(RelativeLayout l, ImageView button, boolean disable) {
        if (!ListenerUtil.mutListener.listen(27866)) {
            if (disable) {
                if (!ListenerUtil.mutListener.listen(27865)) {
                    button.setVisibility(View.INVISIBLE);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(27864)) {
                    button.setOnClickListener(this);
                }
            }
        }
    }

    private String getIdentity() {
        if (!this.requiredInstances()) {
            return "undefined";
        }
        if (userService.hasIdentity()) {
            return userService.getIdentity();
        } else {
            return "undefined";
        }
    }

    private void deleteIdentity() {
        if (!ListenerUtil.mutListener.listen(27867)) {
            if (!this.requiredInstances()) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(27869)) {
            new DeleteIdentityAsyncTask(getFragmentManager(), new Runnable() {

                @Override
                public void run() {
                    if (!ListenerUtil.mutListener.listen(27868)) {
                        System.exit(0);
                    }
                }
            }).execute();
        }
    }

    private void setRevocationPassword() {
        DialogFragment dialogFragment = PasswordEntryDialog.newInstance(R.string.revocation_key_title, R.string.revocation_explain, R.string.password_hint, R.string.ok, R.string.cancel, 8, MAX_REVOCATION_PASSWORD_LENGTH, R.string.backup_password_again_summary, 0, 0);
        if (!ListenerUtil.mutListener.listen(27870)) {
            dialogFragment.setTargetFragment(this, 0);
        }
        if (!ListenerUtil.mutListener.listen(27871)) {
            dialogFragment.show(getFragmentManager(), DIALOG_TAG_SET_REVOCATION_KEY);
        }
    }

    @Override
    public void onClick(View v) {
        int neutral;
        switch(v.getId()) {
            case R.id.change_email:
                neutral = 0;
                if (this.userService.getEmailLinkingState() != UserService.LinkingState_NONE) {
                    neutral = R.string.unlink;
                }
                TextEntryDialog textEntryDialog = TextEntryDialog.newInstance(R.string.wizard2_email_linking, R.string.wizard2_email_hint, R.string.ok, neutral, R.string.cancel, userService.getLinkedEmail(), InputType.TYPE_TEXT_VARIATION_WEB_EMAIL_ADDRESS, TextEntryDialog.INPUT_FILTER_TYPE_NONE);
                if (!ListenerUtil.mutListener.listen(27872)) {
                    textEntryDialog.setTargetFragment(this, 0);
                }
                if (!ListenerUtil.mutListener.listen(27873)) {
                    textEntryDialog.show(getFragmentManager(), DIALOG_TAG_LINKED_EMAIL);
                }
                break;
            case R.id.change_mobile:
                String presetNumber = serviceManager.getLocaleService().getHRPhoneNumber(userService.getLinkedMobile());
                neutral = 0;
                if (this.userService.getMobileLinkingState() != UserService.LinkingState_NONE) {
                    neutral = R.string.unlink;
                } else {
                    if (!ListenerUtil.mutListener.listen(27874)) {
                        presetNumber = localeService.getCountryCodePhonePrefix();
                    }
                    if (!ListenerUtil.mutListener.listen(27876)) {
                        if (!TestUtil.empty(presetNumber)) {
                            if (!ListenerUtil.mutListener.listen(27875)) {
                                presetNumber += " ";
                            }
                        }
                    }
                }
                TextEntryDialog textEntryDialog1 = TextEntryDialog.newInstance(R.string.wizard2_phone_linking, R.string.wizard2_phone_hint, R.string.ok, neutral, R.string.cancel, presetNumber, InputType.TYPE_CLASS_PHONE, TextEntryDialog.INPUT_FILTER_TYPE_PHONE);
                if (!ListenerUtil.mutListener.listen(27877)) {
                    textEntryDialog1.setTargetFragment(this, 0);
                }
                if (!ListenerUtil.mutListener.listen(27878)) {
                    textEntryDialog1.show(getFragmentManager(), DIALOG_TAG_LINKED_MOBILE);
                }
                break;
            case R.id.revocation_key:
                if (!ListenerUtil.mutListener.listen(27881)) {
                    if (!preferenceService.getLockMechanism().equals(PreferenceService.LockingMech_NONE)) {
                        if (!ListenerUtil.mutListener.listen(27880)) {
                            HiddenChatUtil.launchLockCheckDialog(null, this, preferenceService, LOCK_CHECK_REVOCATION);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(27879)) {
                            setRevocationPassword();
                        }
                    }
                }
                break;
            case R.id.delete_id:
                if (!ListenerUtil.mutListener.listen(27884)) {
                    // ask for pin before entering
                    if (!preferenceService.getLockMechanism().equals(PreferenceService.LockingMech_NONE)) {
                        if (!ListenerUtil.mutListener.listen(27883)) {
                            HiddenChatUtil.launchLockCheckDialog(null, this, preferenceService, LOCK_CHECK_DELETE_ID);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(27882)) {
                            confirmIdDelete();
                        }
                    }
                }
                break;
            case R.id.export_id:
                if (!ListenerUtil.mutListener.listen(27887)) {
                    // ask for pin before entering
                    if (!preferenceService.getLockMechanism().equals(PreferenceService.LockingMech_NONE)) {
                        if (!ListenerUtil.mutListener.listen(27886)) {
                            HiddenChatUtil.launchLockCheckDialog(null, this, preferenceService, LOCK_CHECK_EXPORT_ID);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(27885)) {
                            startActivity(new Intent(getContext(), ExportIDActivity.class));
                        }
                    }
                }
                break;
            case R.id.picrelease_config:
                if (!ListenerUtil.mutListener.listen(27888)) {
                    launchProfilePictureRecipientsSelector(v);
                }
                break;
            case R.id.profile_edit:
                TextEntryDialog nicknameEditDialog = TextEntryDialog.newInstance(R.string.set_nickname_title, R.string.wizard3_nickname_hint, R.string.ok, 0, R.string.cancel, userService.getPublicNickname(), InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS, 0, ProtocolDefines.PUSH_FROM_LEN);
                ;
                if (!ListenerUtil.mutListener.listen(27889)) {
                    nicknameEditDialog.setTargetFragment(this, 0);
                }
                if (!ListenerUtil.mutListener.listen(27890)) {
                    nicknameEditDialog.show(getFragmentManager(), DIALOG_TAG_EDIT_NICKNAME);
                }
                break;
            case R.id.my_id_qr:
                if (!ListenerUtil.mutListener.listen(27891)) {
                    new QRCodePopup(getContext(), getActivity().getWindow().getDecorView(), getActivity()).show(v, null);
                }
                break;
            case R.id.avatar:
                if (!ListenerUtil.mutListener.listen(27892)) {
                    launchContactImageZoom(v);
                }
                break;
            case R.id.my_id_share:
                if (!ListenerUtil.mutListener.listen(27893)) {
                    ShareUtil.shareContact(getContext(), null);
                }
                break;
        }
    }

    private void launchContactImageZoom(View v) {
        if (!ListenerUtil.mutListener.listen(27896)) {
            if (getView() != null) {
                View rootView = getView().findViewById(R.id.main_content);
                if (!ListenerUtil.mutListener.listen(27895)) {
                    if (fileService.hasContactAvatarFile(contactService.getMe())) {
                        ImagePopup detailPopup = new ImagePopup(getContext(), rootView, rootView.getWidth(), rootView.getHeight());
                        if (!ListenerUtil.mutListener.listen(27894)) {
                            detailPopup.show(v, contactService.getAvatar(contactService.getMe(), true), userService.getPublicNickname());
                        }
                    }
                }
            }
        }
    }

    private void launchProfilePictureRecipientsSelector(View v) {
        if (!ListenerUtil.mutListener.listen(27897)) {
            AnimationUtil.startActivityForResult(getActivity(), v, new Intent(getContext(), ProfilePicRecipientsActivity.class), 55);
        }
    }

    private void confirmIdDelete() {
        DialogFragment dialogFragment = GenericAlertDialog.newInstance(R.string.delete_id_title, R.string.delete_id_message, R.string.delete_id_title, R.string.cancel);
        if (!ListenerUtil.mutListener.listen(27898)) {
            ((GenericAlertDialog) dialogFragment).setTargetFragment(this);
        }
        if (!ListenerUtil.mutListener.listen(27899)) {
            dialogFragment.show(getFragmentManager(), DIALOG_TAG_DELETE_ID);
        }
    }

    @SuppressLint("StaticFieldLeak")
    private void launchMobileVerification(final String normalizedPhoneNumber) {
        if (!ListenerUtil.mutListener.listen(27911)) {
            new AsyncTask<Void, Void, String>() {

                @Override
                protected String doInBackground(Void... params) {
                    try {
                        if (!ListenerUtil.mutListener.listen(27901)) {
                            userService.linkWithMobileNumber(normalizedPhoneNumber);
                        }
                    } catch (LinkMobileNoException e) {
                        return e.getMessage();
                    } catch (Exception e) {
                        if (!ListenerUtil.mutListener.listen(27900)) {
                            logger.error("Exception", e);
                        }
                        return e.getMessage();
                    }
                    return null;
                }

                @Override
                protected void onPostExecute(String result) {
                    if (!ListenerUtil.mutListener.listen(27910)) {
                        if ((ListenerUtil.mutListener.listen(27904) ? ((ListenerUtil.mutListener.listen(27903) ? ((ListenerUtil.mutListener.listen(27902) ? (isAdded() || !isDetached()) : (isAdded() && !isDetached())) || !isRemoving()) : ((ListenerUtil.mutListener.listen(27902) ? (isAdded() || !isDetached()) : (isAdded() && !isDetached())) && !isRemoving())) || getContext() != null) : ((ListenerUtil.mutListener.listen(27903) ? ((ListenerUtil.mutListener.listen(27902) ? (isAdded() || !isDetached()) : (isAdded() && !isDetached())) || !isRemoving()) : ((ListenerUtil.mutListener.listen(27902) ? (isAdded() || !isDetached()) : (isAdded() && !isDetached())) && !isRemoving())) && getContext() != null))) {
                            if (!ListenerUtil.mutListener.listen(27909)) {
                                if (TestUtil.empty(result)) {
                                    if (!ListenerUtil.mutListener.listen(27908)) {
                                        Toast.makeText(getContext(), R.string.verification_started, Toast.LENGTH_LONG).show();
                                    }
                                } else {
                                    FragmentManager fragmentManager = getFragmentManager();
                                    if (!ListenerUtil.mutListener.listen(27907)) {
                                        if (fragmentManager != null) {
                                            if (!ListenerUtil.mutListener.listen(27905)) {
                                                updatePendingStateTexts(getView());
                                            }
                                            if (!ListenerUtil.mutListener.listen(27906)) {
                                                SimpleStringAlertDialog.newInstance(R.string.verify_title, result).show(fragmentManager, "ve");
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }.execute();
        }
    }

    @UiThread
    private void reloadNickname() {
        if (!ListenerUtil.mutListener.listen(27912)) {
            this.nicknameTextView.setText(!TestUtil.empty(userService.getPublicNickname()) ? userService.getPublicNickname() : userService.getIdentity());
        }
    }

    @SuppressLint("StaticFieldLeak")
    private void setRevocationKey(String text) {
        if (!ListenerUtil.mutListener.listen(27919)) {
            new AsyncTask<Void, Void, Boolean>() {

                @Override
                protected void onPreExecute() {
                    if (!ListenerUtil.mutListener.listen(27913)) {
                        GenericProgressDialog.newInstance(R.string.revocation_key_title, R.string.please_wait).show(getFragmentManager(), DIALOG_TAG_REVOKING);
                    }
                }

                @Override
                protected Boolean doInBackground(Void... voids) {
                    try {
                        return userService.setRevocationKey(text);
                    } catch (Exception x) {
                        if (!ListenerUtil.mutListener.listen(27914)) {
                            logger.error("Exception", x);
                        }
                    }
                    return false;
                }

                @Override
                protected void onPostExecute(Boolean success) {
                    if (!ListenerUtil.mutListener.listen(27915)) {
                        updatePendingStateTexts(getView());
                    }
                    if (!ListenerUtil.mutListener.listen(27916)) {
                        DialogUtil.dismissDialog(getFragmentManager(), DIALOG_TAG_REVOKING, true);
                    }
                    if (!ListenerUtil.mutListener.listen(27918)) {
                        if (!success) {
                            if (!ListenerUtil.mutListener.listen(27917)) {
                                Toast.makeText(getContext(), getString(R.string.error) + ": " + getString(R.string.revocation_key_not_set), Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                }
            }.execute();
        }
    }

    @Override
    public void onYes(String tag, Object data) {
        if (!ListenerUtil.mutListener.listen(27924)) {
            switch(tag) {
                case DIALOG_TAG_DELETE_ID:
                    GenericAlertDialog dialogFragment = GenericAlertDialog.newInstance(R.string.delete_id_title, R.string.delete_id_message2, R.string.delete_id_title, R.string.cancel);
                    if (!ListenerUtil.mutListener.listen(27920)) {
                        dialogFragment.setTargetFragment(this);
                    }
                    if (!ListenerUtil.mutListener.listen(27921)) {
                        dialogFragment.show(getFragmentManager(), DIALOG_TAG_REALLY_DELETE);
                    }
                    break;
                case DIALOG_TAG_REALLY_DELETE:
                    if (!ListenerUtil.mutListener.listen(27922)) {
                        deleteIdentity();
                    }
                    break;
                case DIALOG_TAG_LINKED_MOBILE_CONFIRM:
                    if (!ListenerUtil.mutListener.listen(27923)) {
                        launchMobileVerification((String) data);
                    }
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public void onNo(String tag, Object data) {
    }

    @Override
    public void onYes(String tag, String text) {
        if (!ListenerUtil.mutListener.listen(27935)) {
            switch(tag) {
                case DIALOG_TAG_LINKED_MOBILE:
                    final String normalizedPhoneNumber = localeService.getNormalizedPhoneNumber(text);
                    GenericAlertDialog alertDialog = GenericAlertDialog.newInstance(R.string.wizard2_phone_number_confirm_title, String.format(getString(R.string.wizard2_phone_number_confirm), normalizedPhoneNumber), R.string.ok, R.string.cancel);
                    if (!ListenerUtil.mutListener.listen(27925)) {
                        alertDialog.setData(normalizedPhoneNumber);
                    }
                    if (!ListenerUtil.mutListener.listen(27926)) {
                        alertDialog.setTargetFragment(this);
                    }
                    if (!ListenerUtil.mutListener.listen(27927)) {
                        alertDialog.show(getFragmentManager(), DIALOG_TAG_LINKED_MOBILE_CONFIRM);
                    }
                    break;
                case DIALOG_TAG_LINKED_EMAIL:
                    if (!ListenerUtil.mutListener.listen(27928)) {
                        new LinkWithEmailAsyncTask(getContext(), getFragmentManager(), text, () -> updatePendingStateTexts(getView())).execute();
                    }
                    break;
                case DIALOG_TAG_EDIT_NICKNAME:
                    if (!ListenerUtil.mutListener.listen(27933)) {
                        // Update public nickname
                        if ((ListenerUtil.mutListener.listen(27929) ? (text != null || !text.equals(userService.getPublicNickname())) : (text != null && !text.equals(userService.getPublicNickname())))) {
                            if (!ListenerUtil.mutListener.listen(27931)) {
                                if ("".equals(text.trim())) {
                                    if (!ListenerUtil.mutListener.listen(27930)) {
                                        text = userService.getIdentity();
                                    }
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(27932)) {
                                userService.setPublicNickname(text);
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(27934)) {
                        reloadNickname();
                    }
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public void onYes(String tag, final String text, boolean isChecked, Object data) {
        if (!ListenerUtil.mutListener.listen(27937)) {
            switch(tag) {
                case DIALOG_TAG_SET_REVOCATION_KEY:
                    if (!ListenerUtil.mutListener.listen(27936)) {
                        setRevocationKey(text);
                    }
            }
        }
    }

    @Override
    public void onNo(String tag) {
    }

    @Override
    public void onNeutral(String tag) {
        if (!ListenerUtil.mutListener.listen(27940)) {
            switch(tag) {
                case DIALOG_TAG_LINKED_MOBILE:
                    if (!ListenerUtil.mutListener.listen(27938)) {
                        new Thread(() -> {
                            try {
                                userService.unlinkMobileNumber();
                            } catch (Exception e) {
                                LogUtil.exception(e, getActivity());
                            } finally {
                                RuntimeUtil.runOnUiThread(() -> updatePendingStateTexts(getView()));
                            }
                        }).start();
                    }
                    break;
                case DIALOG_TAG_LINKED_EMAIL:
                    if (!ListenerUtil.mutListener.listen(27939)) {
                        new LinkWithEmailAsyncTask(getContext(), getFragmentManager(), "", () -> updatePendingStateTexts(getView())).execute();
                    }
                    break;
                default:
                    break;
            }
        }
    }

    protected final boolean requiredInstances() {
        if (!ListenerUtil.mutListener.listen(27942)) {
            if (!this.checkInstances()) {
                if (!ListenerUtil.mutListener.listen(27941)) {
                    this.instantiate();
                }
            }
        }
        return this.checkInstances();
    }

    protected boolean checkInstances() {
        return TestUtil.required(this.serviceManager, this.fileService, this.userService, this.preferenceService, this.localeService, this.fingerPrintService);
    }

    protected void instantiate() {
        if (!ListenerUtil.mutListener.listen(27943)) {
            this.serviceManager = ThreemaApplication.getServiceManager();
        }
        if (!ListenerUtil.mutListener.listen(27952)) {
            if (this.serviceManager != null) {
                try {
                    if (!ListenerUtil.mutListener.listen(27946)) {
                        this.contactService = this.serviceManager.getContactService();
                    }
                    if (!ListenerUtil.mutListener.listen(27947)) {
                        this.userService = this.serviceManager.getUserService();
                    }
                    if (!ListenerUtil.mutListener.listen(27948)) {
                        this.fileService = this.serviceManager.getFileService();
                    }
                    if (!ListenerUtil.mutListener.listen(27949)) {
                        this.preferenceService = this.serviceManager.getPreferenceService();
                    }
                    if (!ListenerUtil.mutListener.listen(27950)) {
                        this.localeService = this.serviceManager.getLocaleService();
                    }
                    if (!ListenerUtil.mutListener.listen(27951)) {
                        this.fingerPrintService = this.serviceManager.getFingerPrintService();
                    }
                } catch (MasterKeyLockedException e) {
                    if (!ListenerUtil.mutListener.listen(27944)) {
                        logger.debug("Master Key locked!");
                    }
                } catch (ThreemaException e) {
                    if (!ListenerUtil.mutListener.listen(27945)) {
                        logger.error("Exception", e);
                    }
                }
            }
        }
    }

    public void onLogoClicked() {
        if (!ListenerUtil.mutListener.listen(27955)) {
            if (getView() != null) {
                NestedScrollView scrollView = getView().findViewById(R.id.fragment_id_container);
                if (!ListenerUtil.mutListener.listen(27954)) {
                    if (scrollView != null) {
                        if (!ListenerUtil.mutListener.listen(27953)) {
                            scrollView.scrollTo(0, 0);
                        }
                    }
                }
            }
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        if (!ListenerUtil.mutListener.listen(27956)) {
            super.onHiddenChanged(hidden);
        }
        if (!ListenerUtil.mutListener.listen(27959)) {
            if ((ListenerUtil.mutListener.listen(27957) ? (!hidden || hidden != this.hidden) : (!hidden && hidden != this.hidden))) {
                if (!ListenerUtil.mutListener.listen(27958)) {
                    updatePendingState(getView(), false);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(27960)) {
            this.hidden = hidden;
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        if (!ListenerUtil.mutListener.listen(27961)) {
            logger.info("saveInstance");
        }
        if (!ListenerUtil.mutListener.listen(27962)) {
            super.onSaveInstanceState(outState);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (!ListenerUtil.mutListener.listen(27963)) {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
        if (!ListenerUtil.mutListener.listen(27965)) {
            if (this.avatarView != null) {
                if (!ListenerUtil.mutListener.listen(27964)) {
                    this.avatarView.onRequestPermissionsResult(requestCode, permissions, grantResults);
                }
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (!ListenerUtil.mutListener.listen(27966)) {
            super.onActivityResult(requestCode, resultCode, intent);
        }
        if (!ListenerUtil.mutListener.listen(27977)) {
            switch(requestCode) {
                case ThreemaActivity.ACTIVITY_ID_VERIFY_MOBILE:
                    if (!ListenerUtil.mutListener.listen(27967)) {
                        if (resultCode != Activity.RESULT_OK) {
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(27968)) {
                        updatePendingState(getView(), false);
                    }
                    break;
                case LOCK_CHECK_DELETE_ID:
                    if (!ListenerUtil.mutListener.listen(27970)) {
                        if (resultCode == Activity.RESULT_OK) {
                            if (!ListenerUtil.mutListener.listen(27969)) {
                                confirmIdDelete();
                            }
                        }
                    }
                    break;
                case LOCK_CHECK_EXPORT_ID:
                    if (!ListenerUtil.mutListener.listen(27972)) {
                        if (resultCode == Activity.RESULT_OK) {
                            if (!ListenerUtil.mutListener.listen(27971)) {
                                startActivity(new Intent(getContext(), ExportIDActivity.class));
                            }
                        }
                    }
                    break;
                case LOCK_CHECK_REVOCATION:
                    if (!ListenerUtil.mutListener.listen(27974)) {
                        if (resultCode == Activity.RESULT_OK) {
                            if (!ListenerUtil.mutListener.listen(27973)) {
                                setRevocationPassword();
                            }
                        }
                    }
                    break;
                default:
                    if (!ListenerUtil.mutListener.listen(27976)) {
                        if (this.avatarView != null) {
                            if (!ListenerUtil.mutListener.listen(27975)) {
                                this.avatarView.onActivityResult(requestCode, resultCode, intent);
                            }
                        }
                    }
                    break;
            }
        }
    }
}
