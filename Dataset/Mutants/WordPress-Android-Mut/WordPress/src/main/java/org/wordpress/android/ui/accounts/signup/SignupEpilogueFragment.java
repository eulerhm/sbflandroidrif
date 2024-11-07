package org.wordpress.android.ui.accounts.signup;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.view.ContextThemeWrapper;
import androidx.core.widget.NestedScrollView;
import androidx.core.widget.NestedScrollView.OnScrollChangeListener;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.yalantis.ucrop.UCrop;
import com.yalantis.ucrop.UCropActivity;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.jetbrains.annotations.NotNull;
import org.wordpress.android.R;
import org.wordpress.android.WordPress;
import org.wordpress.android.analytics.AnalyticsTracker;
import org.wordpress.android.analytics.AnalyticsTracker.Stat;
import org.wordpress.android.fluxc.Dispatcher;
import org.wordpress.android.fluxc.action.AccountAction;
import org.wordpress.android.fluxc.generated.AccountActionBuilder;
import org.wordpress.android.fluxc.store.AccountStore;
import org.wordpress.android.fluxc.store.AccountStore.AccountUsernameActionType;
import org.wordpress.android.fluxc.store.AccountStore.OnAccountChanged;
import org.wordpress.android.fluxc.store.AccountStore.OnUsernameChanged;
import org.wordpress.android.fluxc.store.AccountStore.PushAccountSettingsPayload;
import org.wordpress.android.fluxc.store.AccountStore.PushUsernamePayload;
import org.wordpress.android.login.LoginBaseFormFragment;
import org.wordpress.android.login.widgets.WPLoginInputRow;
import org.wordpress.android.networking.GravatarApi;
import org.wordpress.android.ui.FullScreenDialogFragment;
import org.wordpress.android.ui.FullScreenDialogFragment.OnConfirmListener;
import org.wordpress.android.ui.FullScreenDialogFragment.OnDismissListener;
import org.wordpress.android.ui.FullScreenDialogFragment.OnShownListener;
import org.wordpress.android.ui.RequestCodes;
import org.wordpress.android.ui.accounts.UnifiedLoginTracker;
import org.wordpress.android.ui.accounts.UnifiedLoginTracker.Click;
import org.wordpress.android.ui.accounts.UnifiedLoginTracker.Step;
import org.wordpress.android.ui.photopicker.MediaPickerConstants;
import org.wordpress.android.ui.photopicker.MediaPickerLauncher;
import org.wordpress.android.ui.photopicker.PhotoPickerActivity;
import org.wordpress.android.ui.photopicker.PhotoPickerActivity.PhotoPickerMediaSource;
import org.wordpress.android.ui.prefs.AppPrefsWrapper;
import org.wordpress.android.ui.reader.services.update.ReaderUpdateLogic;
import org.wordpress.android.ui.reader.services.update.ReaderUpdateServiceStarter;
import org.wordpress.android.util.AppLog;
import org.wordpress.android.util.AppLog.T;
import org.wordpress.android.util.extensions.ContextExtensionsKt;
import org.wordpress.android.util.GravatarUtils;
import org.wordpress.android.util.MediaUtils;
import org.wordpress.android.util.StringUtils;
import org.wordpress.android.util.ToastUtils;
import org.wordpress.android.util.extensions.ViewExtensionsKt;
import org.wordpress.android.util.WPMediaUtils;
import org.wordpress.android.util.image.ImageManager;
import org.wordpress.android.util.image.ImageManager.RequestListener;
import org.wordpress.android.util.image.ImageType;
import org.wordpress.android.widgets.WPTextView;
import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import javax.inject.Inject;
import static org.wordpress.android.analytics.AnalyticsTracker.Stat.SIGNUP_EMAIL_EPILOGUE_GRAVATAR_GALLERY_PICKED;
import static org.wordpress.android.analytics.AnalyticsTracker.Stat.SIGNUP_EMAIL_EPILOGUE_GRAVATAR_SHOT_NEW;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class SignupEpilogueFragment extends LoginBaseFormFragment<SignupEpilogueListener> implements OnConfirmListener, OnDismissListener, OnShownListener {

    private EditText mEditTextDisplayName;

    private EditText mEditTextUsername;

    private FullScreenDialogFragment mDialog;

    private SignupEpilogueListener mSignupEpilogueListener;

    protected ImageView mHeaderAvatarAdd;

    protected String mDisplayName;

    protected String mEmailAddress;

    protected String mPhotoUrl;

    protected String mUsername;

    protected WPLoginInputRow mInputPassword;

    protected ImageView mHeaderAvatar;

    protected WPTextView mHeaderDisplayName;

    protected WPTextView mHeaderEmailAddress;

    protected View mBottomShadow;

    protected NestedScrollView mScrollView;

    protected boolean mIsAvatarAdded;

    protected boolean mIsEmailSignup;

    private boolean mIsUpdatingDisplayName = false;

    private boolean mIsUpdatingPassword = false;

    private boolean mHasUpdatedPassword = false;

    private boolean mHasMadeUpdates = false;

    private static final String ARG_DISPLAY_NAME = "ARG_DISPLAY_NAME";

    private static final String ARG_EMAIL_ADDRESS = "ARG_EMAIL_ADDRESS";

    private static final String ARG_IS_EMAIL_SIGNUP = "ARG_IS_EMAIL_SIGNUP";

    private static final String ARG_PHOTO_URL = "ARG_PHOTO_URL";

    private static final String ARG_USERNAME = "ARG_USERNAME";

    private static final String KEY_DISPLAY_NAME = "KEY_DISPLAY_NAME";

    private static final String KEY_EMAIL_ADDRESS = "KEY_EMAIL_ADDRESS";

    private static final String KEY_IS_AVATAR_ADDED = "KEY_IS_AVATAR_ADDED";

    private static final String KEY_PHOTO_URL = "KEY_PHOTO_URL";

    private static final String KEY_USERNAME = "KEY_USERNAME";

    private static final String KEY_IS_UPDATING_DISPLAY_NAME = "KEY_IS_UPDATING_DISPLAY_NAME";

    private static final String KEY_IS_UPDATING_PASSWORD = "KEY_IS_UPDATING_PASSWORD";

    private static final String KEY_HAS_UPDATED_PASSWORD = "KEY_HAS_UPDATED_PASSWORD";

    private static final String KEY_HAS_MADE_UPDATES = "KEY_HAS_MADE_UPDATES";

    private static final String SOURCE = "source";

    private static final String SOURCE_SIGNUP_EPILOGUE = "signup_epilogue";

    public static final String TAG = "signup_epilogue_fragment_tag";

    @Inject
    protected AccountStore mAccount;

    @Inject
    protected Dispatcher mDispatcher;

    @Inject
    protected ImageManager mImageManager;

    @Inject
    protected AppPrefsWrapper mAppPrefsWrapper;

    @Inject
    protected UnifiedLoginTracker mUnifiedLoginTracker;

    @Inject
    protected SignupUtils mSignupUtils;

    @Inject
    protected MediaPickerLauncher mMediaPickerLauncher;

    public static SignupEpilogueFragment newInstance(String displayName, String emailAddress, String photoUrl, String username, boolean isEmailSignup) {
        SignupEpilogueFragment signupEpilogueFragment = new SignupEpilogueFragment();
        Bundle args = new Bundle();
        if (!ListenerUtil.mutListener.listen(3589)) {
            args.putString(ARG_DISPLAY_NAME, displayName);
        }
        if (!ListenerUtil.mutListener.listen(3590)) {
            args.putString(ARG_EMAIL_ADDRESS, emailAddress);
        }
        if (!ListenerUtil.mutListener.listen(3591)) {
            args.putString(ARG_PHOTO_URL, photoUrl);
        }
        if (!ListenerUtil.mutListener.listen(3592)) {
            args.putString(ARG_USERNAME, username);
        }
        if (!ListenerUtil.mutListener.listen(3593)) {
            args.putBoolean(ARG_IS_EMAIL_SIGNUP, isEmailSignup);
        }
        if (!ListenerUtil.mutListener.listen(3594)) {
            signupEpilogueFragment.setArguments(args);
        }
        return signupEpilogueFragment;
    }

    @Override
    @LayoutRes
    protected int getContentLayout() {
        // no content layout; entire view is inflated in createMainView
        return 0;
    }

    @Override
    @LayoutRes
    protected int getProgressBarText() {
        return R.string.signup_updating_account;
    }

    @Override
    protected void setupLabel(@NonNull TextView label) {
    }

    @Override
    protected ViewGroup createMainView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return (ViewGroup) inflater.inflate(R.layout.signup_epilogue, container, false);
    }

    @Override
    protected void setupContent(ViewGroup rootView) {
        final FrameLayout headerAvatarLayout = rootView.findViewById(R.id.login_epilogue_header_avatar_layout);
        if (!ListenerUtil.mutListener.listen(3595)) {
            headerAvatarLayout.setEnabled(mIsEmailSignup);
        }
        if (!ListenerUtil.mutListener.listen(3598)) {
            headerAvatarLayout.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    if (!ListenerUtil.mutListener.listen(3596)) {
                        mUnifiedLoginTracker.trackClick(Click.SELECT_AVATAR);
                    }
                    if (!ListenerUtil.mutListener.listen(3597)) {
                        mMediaPickerLauncher.showGravatarPicker(SignupEpilogueFragment.this);
                    }
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(3600)) {
            headerAvatarLayout.setOnLongClickListener(new View.OnLongClickListener() {

                @Override
                public boolean onLongClick(View view) {
                    if (!ListenerUtil.mutListener.listen(3599)) {
                        ToastUtils.showToast(getActivity(), getString(R.string.content_description_add_avatar), ToastUtils.Duration.SHORT);
                    }
                    return true;
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(3601)) {
            ViewExtensionsKt.redirectContextClickToLongPressListener(headerAvatarLayout);
        }
        if (!ListenerUtil.mutListener.listen(3602)) {
            mHeaderAvatarAdd = rootView.findViewById(R.id.login_epilogue_header_avatar_add);
        }
        if (!ListenerUtil.mutListener.listen(3603)) {
            mHeaderAvatarAdd.setVisibility(mIsEmailSignup ? View.VISIBLE : View.GONE);
        }
        if (!ListenerUtil.mutListener.listen(3604)) {
            mHeaderAvatar = rootView.findViewById(R.id.login_epilogue_header_avatar);
        }
        if (!ListenerUtil.mutListener.listen(3605)) {
            mHeaderDisplayName = rootView.findViewById(R.id.login_epilogue_header_title);
        }
        if (!ListenerUtil.mutListener.listen(3606)) {
            mHeaderDisplayName.setText(mDisplayName);
        }
        if (!ListenerUtil.mutListener.listen(3607)) {
            mHeaderEmailAddress = rootView.findViewById(R.id.login_epilogue_header_subtitle);
        }
        if (!ListenerUtil.mutListener.listen(3608)) {
            mHeaderEmailAddress.setText(mEmailAddress);
        }
        WPLoginInputRow inputDisplayName = rootView.findViewById(R.id.signup_epilogue_input_display);
        if (!ListenerUtil.mutListener.listen(3609)) {
            mEditTextDisplayName = inputDisplayName.getEditText();
        }
        if (!ListenerUtil.mutListener.listen(3610)) {
            mEditTextDisplayName.setText(mDisplayName);
        }
        if (!ListenerUtil.mutListener.listen(3613)) {
            mEditTextDisplayName.addTextChangedListener(new TextWatcher() {

                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                }

                @Override
                public void afterTextChanged(Editable s) {
                    if (!ListenerUtil.mutListener.listen(3611)) {
                        mDisplayName = s.toString();
                    }
                    if (!ListenerUtil.mutListener.listen(3612)) {
                        mHeaderDisplayName.setText(mDisplayName);
                    }
                }
            });
        }
        WPLoginInputRow inputUsername = rootView.findViewById(R.id.signup_epilogue_input_username);
        if (!ListenerUtil.mutListener.listen(3614)) {
            mEditTextUsername = inputUsername.getEditText();
        }
        if (!ListenerUtil.mutListener.listen(3615)) {
            mEditTextUsername.setText(mUsername);
        }
        if (!ListenerUtil.mutListener.listen(3618)) {
            mEditTextUsername.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    if (!ListenerUtil.mutListener.listen(3616)) {
                        mUnifiedLoginTracker.trackClick(Click.EDIT_USERNAME);
                    }
                    if (!ListenerUtil.mutListener.listen(3617)) {
                        launchDialog();
                    }
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(3621)) {
            mEditTextUsername.setOnFocusChangeListener(new View.OnFocusChangeListener() {

                @Override
                public void onFocusChange(View view, boolean hasFocus) {
                    if (!ListenerUtil.mutListener.listen(3620)) {
                        if (hasFocus) {
                            if (!ListenerUtil.mutListener.listen(3619)) {
                                launchDialog();
                            }
                        }
                    }
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(3623)) {
            mEditTextUsername.setOnKeyListener(new View.OnKeyListener() {

                @Override
                public boolean onKey(View view, int keyCode, KeyEvent event) {
                    // This allows the username changer to launch using the keyboard.
                    return !((ListenerUtil.mutListener.listen(3622) ? (keyCode == KeyEvent.KEYCODE_ENTER && keyCode == KeyEvent.KEYCODE_TAB) : (keyCode == KeyEvent.KEYCODE_ENTER || keyCode == KeyEvent.KEYCODE_TAB)));
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(3624)) {
            mInputPassword = rootView.findViewById(R.id.signup_epilogue_input_password);
        }
        if (!ListenerUtil.mutListener.listen(3625)) {
            mInputPassword.setVisibility(mIsEmailSignup ? View.VISIBLE : View.GONE);
        }
        final WPTextView passwordDetail = rootView.findViewById(R.id.signup_epilogue_input_password_detail);
        if (!ListenerUtil.mutListener.listen(3626)) {
            passwordDetail.setVisibility(mIsEmailSignup ? View.VISIBLE : View.GONE);
        }
        if (!ListenerUtil.mutListener.listen(3627)) {
            // Set focus on static text field to avoid showing keyboard on start.
            mHeaderEmailAddress.requestFocus();
        }
        if (!ListenerUtil.mutListener.listen(3628)) {
            mBottomShadow = rootView.findViewById(R.id.bottom_shadow);
        }
        if (!ListenerUtil.mutListener.listen(3629)) {
            mScrollView = rootView.findViewById(R.id.scroll_view);
        }
        if (!ListenerUtil.mutListener.listen(3630)) {
            mScrollView.setOnScrollChangeListener((OnScrollChangeListener) (v, scrollX, scrollY, oldScrollX, oldScrollY) -> showBottomShadowIfNeeded());
        }
        if (!ListenerUtil.mutListener.listen(3633)) {
            // We must use onGlobalLayout here otherwise canScrollVertically will always return false
            mScrollView.getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener() {

                @Override
                public void onGlobalLayout() {
                    if (!ListenerUtil.mutListener.listen(3631)) {
                        mScrollView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    }
                    if (!ListenerUtil.mutListener.listen(3632)) {
                        showBottomShadowIfNeeded();
                    }
                }
            });
        }
    }

    private void showBottomShadowIfNeeded() {
        if (!ListenerUtil.mutListener.listen(3636)) {
            if (mScrollView != null) {
                final boolean canScrollDown = mScrollView.canScrollVertically(1);
                if (!ListenerUtil.mutListener.listen(3635)) {
                    if (mBottomShadow != null) {
                        if (!ListenerUtil.mutListener.listen(3634)) {
                            mBottomShadow.setVisibility(canScrollDown ? View.VISIBLE : View.GONE);
                        }
                    }
                }
            }
        }
    }

    @Override
    protected void setupBottomButton(Button button) {
        if (!ListenerUtil.mutListener.listen(3639)) {
            button.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    if (!ListenerUtil.mutListener.listen(3637)) {
                        mUnifiedLoginTracker.trackClick(Click.CONTINUE);
                    }
                    if (!ListenerUtil.mutListener.listen(3638)) {
                        updateAccountOrContinue();
                    }
                }
            });
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(3640)) {
            super.onCreate(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(3641)) {
            ((WordPress) getActivity().getApplication()).component().inject(this);
        }
        if (!ListenerUtil.mutListener.listen(3642)) {
            mDispatcher.dispatch(AccountActionBuilder.newFetchAccountAction());
        }
        if (!ListenerUtil.mutListener.listen(3643)) {
            mDisplayName = getArguments().getString(ARG_DISPLAY_NAME);
        }
        if (!ListenerUtil.mutListener.listen(3644)) {
            mEmailAddress = getArguments().getString(ARG_EMAIL_ADDRESS);
        }
        if (!ListenerUtil.mutListener.listen(3645)) {
            mPhotoUrl = StringUtils.notNullStr(getArguments().getString(ARG_PHOTO_URL));
        }
        if (!ListenerUtil.mutListener.listen(3646)) {
            mUsername = getArguments().getString(ARG_USERNAME);
        }
        if (!ListenerUtil.mutListener.listen(3647)) {
            mIsEmailSignup = getArguments().getBoolean(ARG_IS_EMAIL_SIGNUP);
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(3648)) {
            super.onActivityCreated(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(3676)) {
            if (savedInstanceState == null) {
                if (!ListenerUtil.mutListener.listen(3666)) {
                    // Start loading reader tags so they will be available asap
                    ReaderUpdateServiceStarter.startService(WordPress.getContext(), EnumSet.of(ReaderUpdateLogic.UpdateTask.TAGS));
                }
                if (!ListenerUtil.mutListener.listen(3667)) {
                    mUnifiedLoginTracker.track(Step.SUCCESS);
                }
                if (!ListenerUtil.mutListener.listen(3675)) {
                    if (mIsEmailSignup) {
                        if (!ListenerUtil.mutListener.listen(3671)) {
                            AnalyticsTracker.track(AnalyticsTracker.Stat.SIGNUP_EMAIL_EPILOGUE_VIEWED);
                        }
                        if (!ListenerUtil.mutListener.listen(3674)) {
                            // email does not exist in account store.
                            if (TextUtils.isEmpty(mAccountStore.getAccount().getEmail())) {
                                if (!ListenerUtil.mutListener.listen(3673)) {
                                    startProgress(false);
                                }
                            } else {
                                if (!ListenerUtil.mutListener.listen(3672)) {
                                    // Skip progress and populate views when email does exist in account store.
                                    populateViews();
                                }
                            }
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(3668)) {
                            AnalyticsTracker.track(AnalyticsTracker.Stat.SIGNUP_SOCIAL_EPILOGUE_VIEWED);
                        }
                        if (!ListenerUtil.mutListener.listen(3669)) {
                            new DownloadAvatarAndUploadGravatarThread(mPhotoUrl, mEmailAddress, mAccount.getAccessToken()).start();
                        }
                        if (!ListenerUtil.mutListener.listen(3670)) {
                            mImageManager.loadIntoCircle(mHeaderAvatar, ImageType.AVATAR_WITHOUT_BACKGROUND, mPhotoUrl);
                        }
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(3649)) {
                    mDialog = (FullScreenDialogFragment) getFragmentManager().findFragmentByTag(FullScreenDialogFragment.TAG);
                }
                if (!ListenerUtil.mutListener.listen(3652)) {
                    if (mDialog != null) {
                        if (!ListenerUtil.mutListener.listen(3650)) {
                            mDialog.setOnConfirmListener(this);
                        }
                        if (!ListenerUtil.mutListener.listen(3651)) {
                            mDialog.setOnDismissListener(this);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(3653)) {
                    mDisplayName = savedInstanceState.getString(KEY_DISPLAY_NAME);
                }
                if (!ListenerUtil.mutListener.listen(3654)) {
                    mUsername = savedInstanceState.getString(KEY_USERNAME);
                }
                if (!ListenerUtil.mutListener.listen(3655)) {
                    mIsAvatarAdded = savedInstanceState.getBoolean(KEY_IS_AVATAR_ADDED);
                }
                if (!ListenerUtil.mutListener.listen(3660)) {
                    if (mIsEmailSignup) {
                        if (!ListenerUtil.mutListener.listen(3656)) {
                            mPhotoUrl = StringUtils.notNullStr(savedInstanceState.getString(KEY_PHOTO_URL));
                        }
                        if (!ListenerUtil.mutListener.listen(3657)) {
                            mEmailAddress = savedInstanceState.getString(KEY_EMAIL_ADDRESS);
                        }
                        if (!ListenerUtil.mutListener.listen(3658)) {
                            mHeaderEmailAddress.setText(mEmailAddress);
                        }
                        if (!ListenerUtil.mutListener.listen(3659)) {
                            mHeaderAvatarAdd.setVisibility(mIsAvatarAdded ? View.GONE : View.VISIBLE);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(3661)) {
                    mImageManager.loadIntoCircle(mHeaderAvatar, ImageType.AVATAR_WITHOUT_BACKGROUND, mPhotoUrl);
                }
                if (!ListenerUtil.mutListener.listen(3662)) {
                    mIsUpdatingDisplayName = savedInstanceState.getBoolean(KEY_IS_UPDATING_DISPLAY_NAME);
                }
                if (!ListenerUtil.mutListener.listen(3663)) {
                    mIsUpdatingPassword = savedInstanceState.getBoolean(KEY_IS_UPDATING_PASSWORD);
                }
                if (!ListenerUtil.mutListener.listen(3664)) {
                    mHasUpdatedPassword = savedInstanceState.getBoolean(KEY_HAS_UPDATED_PASSWORD);
                }
                if (!ListenerUtil.mutListener.listen(3665)) {
                    mHasMadeUpdates = savedInstanceState.getBoolean(KEY_HAS_MADE_UPDATES);
                }
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (!ListenerUtil.mutListener.listen(3677)) {
            super.onActivityResult(requestCode, resultCode, data);
        }
        if (!ListenerUtil.mutListener.listen(3700)) {
            if (isAdded()) {
                if (!ListenerUtil.mutListener.listen(3699)) {
                    switch(resultCode) {
                        case Activity.RESULT_OK:
                            if (!ListenerUtil.mutListener.listen(3696)) {
                                switch(requestCode) {
                                    case RequestCodes.PHOTO_PICKER:
                                        if (!ListenerUtil.mutListener.listen(3692)) {
                                            if (data != null) {
                                                String[] mediaUriStringsArray = data.getStringArrayExtra(MediaPickerConstants.EXTRA_MEDIA_URIS);
                                                if (!ListenerUtil.mutListener.listen(3691)) {
                                                    if ((ListenerUtil.mutListener.listen(3683) ? (mediaUriStringsArray != null || (ListenerUtil.mutListener.listen(3682) ? (mediaUriStringsArray.length >= 0) : (ListenerUtil.mutListener.listen(3681) ? (mediaUriStringsArray.length <= 0) : (ListenerUtil.mutListener.listen(3680) ? (mediaUriStringsArray.length < 0) : (ListenerUtil.mutListener.listen(3679) ? (mediaUriStringsArray.length != 0) : (ListenerUtil.mutListener.listen(3678) ? (mediaUriStringsArray.length == 0) : (mediaUriStringsArray.length > 0))))))) : (mediaUriStringsArray != null && (ListenerUtil.mutListener.listen(3682) ? (mediaUriStringsArray.length >= 0) : (ListenerUtil.mutListener.listen(3681) ? (mediaUriStringsArray.length <= 0) : (ListenerUtil.mutListener.listen(3680) ? (mediaUriStringsArray.length < 0) : (ListenerUtil.mutListener.listen(3679) ? (mediaUriStringsArray.length != 0) : (ListenerUtil.mutListener.listen(3678) ? (mediaUriStringsArray.length == 0) : (mediaUriStringsArray.length > 0))))))))) {
                                                        PhotoPickerMediaSource source = PhotoPickerMediaSource.fromString(data.getStringExtra(MediaPickerConstants.EXTRA_MEDIA_SOURCE));
                                                        AnalyticsTracker.Stat stat = source == PhotoPickerActivity.PhotoPickerMediaSource.ANDROID_CAMERA ? SIGNUP_EMAIL_EPILOGUE_GRAVATAR_SHOT_NEW : SIGNUP_EMAIL_EPILOGUE_GRAVATAR_GALLERY_PICKED;
                                                        if (!ListenerUtil.mutListener.listen(3685)) {
                                                            AnalyticsTracker.track(stat);
                                                        }
                                                        Uri imageUri = Uri.parse(mediaUriStringsArray[0]);
                                                        if (!ListenerUtil.mutListener.listen(3690)) {
                                                            if (imageUri != null) {
                                                                boolean wasSuccess = WPMediaUtils.fetchMediaAndDoNext(getActivity(), imageUri, new WPMediaUtils.MediaFetchDoNext() {

                                                                    @Override
                                                                    public void doNext(Uri uri) {
                                                                        if (!ListenerUtil.mutListener.listen(3687)) {
                                                                            startCropActivity(uri);
                                                                        }
                                                                    }
                                                                });
                                                                if (!ListenerUtil.mutListener.listen(3689)) {
                                                                    if (!wasSuccess) {
                                                                        if (!ListenerUtil.mutListener.listen(3688)) {
                                                                            AppLog.e(T.UTILS, "Can't download picked or captured image");
                                                                        }
                                                                    }
                                                                }
                                                            } else {
                                                                if (!ListenerUtil.mutListener.listen(3686)) {
                                                                    AppLog.e(T.UTILS, "Can't parse media string");
                                                                }
                                                            }
                                                        }
                                                    } else {
                                                        if (!ListenerUtil.mutListener.listen(3684)) {
                                                            AppLog.e(T.UTILS, "Can't resolve picked or captured image");
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                        break;
                                    case UCrop.REQUEST_CROP:
                                        if (!ListenerUtil.mutListener.listen(3693)) {
                                            AnalyticsTracker.track(AnalyticsTracker.Stat.SIGNUP_EMAIL_EPILOGUE_GRAVATAR_CROPPED);
                                        }
                                        if (!ListenerUtil.mutListener.listen(3695)) {
                                            WPMediaUtils.fetchMediaAndDoNext(getActivity(), UCrop.getOutput(data), new WPMediaUtils.MediaFetchDoNext() {

                                                @Override
                                                public void doNext(Uri uri) {
                                                    if (!ListenerUtil.mutListener.listen(3694)) {
                                                        startGravatarUpload(MediaUtils.getRealPathFromURI(getActivity(), uri));
                                                    }
                                                }
                                            });
                                        }
                                        break;
                                }
                            }
                            break;
                        case UCrop.RESULT_ERROR:
                            if (!ListenerUtil.mutListener.listen(3697)) {
                                AppLog.e(T.NUX, "Image cropping failed", UCrop.getError(data));
                            }
                            if (!ListenerUtil.mutListener.listen(3698)) {
                                ToastUtils.showToast(getActivity(), R.string.error_cropping_image, ToastUtils.Duration.SHORT);
                            }
                            break;
                    }
                }
            }
        }
    }

    @Override
    public void onAttach(Context context) {
        if (!ListenerUtil.mutListener.listen(3701)) {
            super.onAttach(context);
        }
        if (!ListenerUtil.mutListener.listen(3703)) {
            if (context instanceof SignupEpilogueListener) {
                if (!ListenerUtil.mutListener.listen(3702)) {
                    mSignupEpilogueListener = (SignupEpilogueListener) context;
                }
            } else {
                throw new RuntimeException(context.toString() + " must implement SignupEpilogueListener");
            }
        }
    }

    @Override
    public void onConfirm(@Nullable Bundle result) {
        if (!ListenerUtil.mutListener.listen(3706)) {
            if (result != null) {
                if (!ListenerUtil.mutListener.listen(3704)) {
                    mUsername = result.getString(UsernameChangerFullScreenDialogFragment.RESULT_USERNAME);
                }
                if (!ListenerUtil.mutListener.listen(3705)) {
                    mEditTextUsername.setText(mUsername);
                }
            }
        }
    }

    @Override
    public void onDismiss() {
        Map<String, String> props = new HashMap<>();
        if (!ListenerUtil.mutListener.listen(3707)) {
            props.put(SOURCE, SOURCE_SIGNUP_EPILOGUE);
        }
        if (!ListenerUtil.mutListener.listen(3708)) {
            AnalyticsTracker.track(Stat.CHANGE_USERNAME_DISMISSED, props);
        }
        if (!ListenerUtil.mutListener.listen(3709)) {
            mDialog = null;
        }
    }

    @Override
    public void onShown() {
        Map<String, String> props = new HashMap<>();
        if (!ListenerUtil.mutListener.listen(3710)) {
            props.put(SOURCE, SOURCE_SIGNUP_EPILOGUE);
        }
        if (!ListenerUtil.mutListener.listen(3711)) {
            AnalyticsTracker.track(AnalyticsTracker.Stat.CHANGE_USERNAME_DISPLAYED, props);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        if (!ListenerUtil.mutListener.listen(3712)) {
            super.onSaveInstanceState(outState);
        }
        if (!ListenerUtil.mutListener.listen(3713)) {
            outState.putString(KEY_PHOTO_URL, mPhotoUrl);
        }
        if (!ListenerUtil.mutListener.listen(3714)) {
            outState.putString(KEY_DISPLAY_NAME, mDisplayName);
        }
        if (!ListenerUtil.mutListener.listen(3715)) {
            outState.putString(KEY_EMAIL_ADDRESS, mEmailAddress);
        }
        if (!ListenerUtil.mutListener.listen(3716)) {
            outState.putString(KEY_USERNAME, mUsername);
        }
        if (!ListenerUtil.mutListener.listen(3717)) {
            outState.putBoolean(KEY_IS_AVATAR_ADDED, mIsAvatarAdded);
        }
        if (!ListenerUtil.mutListener.listen(3718)) {
            outState.putBoolean(KEY_IS_UPDATING_DISPLAY_NAME, mIsUpdatingDisplayName);
        }
        if (!ListenerUtil.mutListener.listen(3719)) {
            outState.putBoolean(KEY_IS_UPDATING_PASSWORD, mIsUpdatingPassword);
        }
        if (!ListenerUtil.mutListener.listen(3720)) {
            outState.putBoolean(KEY_HAS_UPDATED_PASSWORD, mHasUpdatedPassword);
        }
        if (!ListenerUtil.mutListener.listen(3721)) {
            outState.putBoolean(KEY_HAS_MADE_UPDATES, mHasMadeUpdates);
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
    }

    @Override
    public void afterTextChanged(Editable s) {
    }

    @Override
    protected void onHelp() {
    }

    @Override
    protected void onLoginFinished() {
        if (!ListenerUtil.mutListener.listen(3722)) {
            endProgress();
        }
    }

    @SuppressWarnings("unused")
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onAccountChanged(OnAccountChanged event) {
        if (!ListenerUtil.mutListener.listen(3743)) {
            if (event.isError()) {
                if (!ListenerUtil.mutListener.listen(3737)) {
                    if (mIsUpdatingDisplayName) {
                        if (!ListenerUtil.mutListener.listen(3735)) {
                            mIsUpdatingDisplayName = false;
                        }
                        if (!ListenerUtil.mutListener.listen(3736)) {
                            AnalyticsTracker.track(mIsEmailSignup ? AnalyticsTracker.Stat.SIGNUP_EMAIL_EPILOGUE_UPDATE_DISPLAY_NAME_FAILED : AnalyticsTracker.Stat.SIGNUP_SOCIAL_EPILOGUE_UPDATE_DISPLAY_NAME_FAILED);
                        }
                    } else if (mIsUpdatingPassword) {
                        if (!ListenerUtil.mutListener.listen(3734)) {
                            mIsUpdatingPassword = false;
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(3738)) {
                    AppLog.e(T.API, "SignupEpilogueFragment.onAccountChanged: " + event.error.type + " - " + event.error.message);
                }
                if (!ListenerUtil.mutListener.listen(3739)) {
                    endProgress();
                }
                if (!ListenerUtil.mutListener.listen(3742)) {
                    if (isPasswordInErrorMessage(event.error.message)) {
                        if (!ListenerUtil.mutListener.listen(3741)) {
                            showErrorDialogWithCloseButton(event.error.message);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(3740)) {
                            showErrorDialog(getString(R.string.signup_epilogue_error_generic));
                        }
                    }
                }
            } else if ((ListenerUtil.mutListener.listen(3724) ? ((ListenerUtil.mutListener.listen(3723) ? (mIsEmailSignup || event.causeOfChange == AccountAction.FETCH_ACCOUNT) : (mIsEmailSignup && event.causeOfChange == AccountAction.FETCH_ACCOUNT)) || !TextUtils.isEmpty(mAccountStore.getAccount().getEmail())) : ((ListenerUtil.mutListener.listen(3723) ? (mIsEmailSignup || event.causeOfChange == AccountAction.FETCH_ACCOUNT) : (mIsEmailSignup && event.causeOfChange == AccountAction.FETCH_ACCOUNT)) && !TextUtils.isEmpty(mAccountStore.getAccount().getEmail())))) {
                if (!ListenerUtil.mutListener.listen(3732)) {
                    endProgress();
                }
                if (!ListenerUtil.mutListener.listen(3733)) {
                    populateViews();
                }
            } else if (event.causeOfChange == AccountAction.PUSH_SETTINGS) {
                if (!ListenerUtil.mutListener.listen(3725)) {
                    mHasMadeUpdates = true;
                }
                if (!ListenerUtil.mutListener.listen(3730)) {
                    if (mIsUpdatingDisplayName) {
                        if (!ListenerUtil.mutListener.listen(3728)) {
                            mIsUpdatingDisplayName = false;
                        }
                        if (!ListenerUtil.mutListener.listen(3729)) {
                            AnalyticsTracker.track(mIsEmailSignup ? AnalyticsTracker.Stat.SIGNUP_EMAIL_EPILOGUE_UPDATE_DISPLAY_NAME_SUCCEEDED : AnalyticsTracker.Stat.SIGNUP_SOCIAL_EPILOGUE_UPDATE_DISPLAY_NAME_SUCCEEDED);
                        }
                    } else if (mIsUpdatingPassword) {
                        if (!ListenerUtil.mutListener.listen(3726)) {
                            mIsUpdatingPassword = false;
                        }
                        if (!ListenerUtil.mutListener.listen(3727)) {
                            mHasUpdatedPassword = true;
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(3731)) {
                    updateAccountOrContinue();
                }
            }
        }
    }

    @SuppressWarnings("unused")
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onUsernameChanged(OnUsernameChanged event) {
        if (!ListenerUtil.mutListener.listen(3751)) {
            if (event.isError()) {
                if (!ListenerUtil.mutListener.listen(3747)) {
                    AnalyticsTracker.track(mIsEmailSignup ? AnalyticsTracker.Stat.SIGNUP_EMAIL_EPILOGUE_UPDATE_USERNAME_FAILED : AnalyticsTracker.Stat.SIGNUP_SOCIAL_EPILOGUE_UPDATE_USERNAME_FAILED);
                }
                if (!ListenerUtil.mutListener.listen(3748)) {
                    AppLog.e(T.API, "SignupEpilogueFragment.onUsernameChanged: " + event.error.type + " - " + event.error.message);
                }
                if (!ListenerUtil.mutListener.listen(3749)) {
                    endProgress();
                }
                if (!ListenerUtil.mutListener.listen(3750)) {
                    showErrorDialog(getString(R.string.signup_epilogue_error_generic));
                }
            } else {
                if (!ListenerUtil.mutListener.listen(3744)) {
                    mHasMadeUpdates = true;
                }
                if (!ListenerUtil.mutListener.listen(3745)) {
                    AnalyticsTracker.track(mIsEmailSignup ? AnalyticsTracker.Stat.SIGNUP_EMAIL_EPILOGUE_UPDATE_USERNAME_SUCCEEDED : AnalyticsTracker.Stat.SIGNUP_SOCIAL_EPILOGUE_UPDATE_USERNAME_SUCCEEDED);
                }
                if (!ListenerUtil.mutListener.listen(3746)) {
                    updateAccountOrContinue();
                }
            }
        }
    }

    protected boolean changedDisplayName() {
        return !TextUtils.equals(mAccount.getAccount().getDisplayName(), mDisplayName);
    }

    protected boolean changedPassword() {
        return !TextUtils.isEmpty(mInputPassword.getEditText().getText().toString());
    }

    protected boolean changedUsername() {
        return !TextUtils.equals(mAccount.getAccount().getUserName(), mUsername);
    }

    private boolean isPasswordInErrorMessage(String message) {
        String lowercaseMessage = message.toLowerCase(Locale.getDefault());
        String lowercasePassword = getString(R.string.password).toLowerCase(Locale.getDefault());
        return lowercaseMessage.contains(lowercasePassword);
    }

    protected void launchDialog() {
        if (!ListenerUtil.mutListener.listen(3752)) {
            AnalyticsTracker.track(mIsEmailSignup ? AnalyticsTracker.Stat.SIGNUP_EMAIL_EPILOGUE_USERNAME_TAPPED : AnalyticsTracker.Stat.SIGNUP_SOCIAL_EPILOGUE_USERNAME_TAPPED);
        }
        final Bundle bundle = UsernameChangerFullScreenDialogFragment.newBundle(mEditTextDisplayName.getText().toString(), mEditTextUsername.getText().toString());
        if (!ListenerUtil.mutListener.listen(3753)) {
            mDialog = new FullScreenDialogFragment.Builder(getContext()).setTitle(R.string.username_changer_title).setAction(R.string.username_changer_action).setToolbarTheme(R.style.ThemeOverlay_LoginFlow_Toolbar).setOnConfirmListener(this).setOnDismissListener(this).setOnShownListener(this).setContent(UsernameChangerFullScreenDialogFragment.class, bundle).build();
        }
        if (!ListenerUtil.mutListener.listen(3754)) {
            mDialog.show(getActivity().getSupportFragmentManager(), FullScreenDialogFragment.TAG);
        }
    }

    protected void loadAvatar(final String avatarUrl, String injectFilePath) {
        final boolean newAvatarUploaded = (ListenerUtil.mutListener.listen(3755) ? (injectFilePath != null || !injectFilePath.isEmpty()) : (injectFilePath != null && !injectFilePath.isEmpty()));
        if (!ListenerUtil.mutListener.listen(3758)) {
            if (newAvatarUploaded) {
                if (!ListenerUtil.mutListener.listen(3756)) {
                    // Remove specific URL entry from bitmap cache. Update it via injected request cache.
                    WordPress.getBitmapCache().removeSimilar(avatarUrl);
                }
                if (!ListenerUtil.mutListener.listen(3757)) {
                    // Changing the signature invalidates Glide's cache
                    mAppPrefsWrapper.setAvatarVersion(mAppPrefsWrapper.getAvatarVersion() + 1);
                }
            }
        }
        Bitmap bitmap = WordPress.getBitmapCache().get(avatarUrl);
        if (!ListenerUtil.mutListener.listen(3767)) {
            // which temporary saves the new image into a local bitmap cache.
            if (bitmap != null) {
                if (!ListenerUtil.mutListener.listen(3766)) {
                    mImageManager.load(mHeaderAvatar, bitmap);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(3765)) {
                    mImageManager.loadIntoCircle(mHeaderAvatar, ImageType.AVATAR_WITHOUT_BACKGROUND, newAvatarUploaded ? injectFilePath : avatarUrl, new RequestListener<Drawable>() {

                        @Override
                        public void onLoadFailed(@Nullable Exception e, @Nullable Object model) {
                            if (!ListenerUtil.mutListener.listen(3759)) {
                                AppLog.e(T.NUX, "Uploading image to Gravatar succeeded, but setting image view failed");
                            }
                            if (!ListenerUtil.mutListener.listen(3760)) {
                                showErrorDialogWithCloseButton(getString(R.string.signup_epilogue_error_avatar_view));
                            }
                        }

                        @Override
                        public void onResourceReady(@NotNull Drawable resource, @Nullable Object model) {
                            if (!ListenerUtil.mutListener.listen(3764)) {
                                if ((ListenerUtil.mutListener.listen(3761) ? (newAvatarUploaded || resource instanceof BitmapDrawable) : (newAvatarUploaded && resource instanceof BitmapDrawable))) {
                                    Bitmap bitmap = ((BitmapDrawable) resource).getBitmap();
                                    if (!ListenerUtil.mutListener.listen(3762)) {
                                        // create a copy since the original bitmap may by automatically recycled
                                        bitmap = bitmap.copy(bitmap.getConfig(), true);
                                    }
                                    if (!ListenerUtil.mutListener.listen(3763)) {
                                        WordPress.getBitmapCache().put(avatarUrl, bitmap);
                                    }
                                }
                            }
                        }
                    }, mAppPrefsWrapper.getAvatarVersion());
                }
            }
        }
    }

    private void populateViews() {
        if (!ListenerUtil.mutListener.listen(3768)) {
            mEmailAddress = mAccountStore.getAccount().getEmail();
        }
        if (!ListenerUtil.mutListener.listen(3769)) {
            mDisplayName = mSignupUtils.createDisplayNameFromEmail(mEmailAddress);
        }
        if (!ListenerUtil.mutListener.listen(3770)) {
            mUsername = !TextUtils.isEmpty(mAccountStore.getAccount().getUserName()) ? mAccountStore.getAccount().getUserName() : mSignupUtils.createUsernameFromEmail(mEmailAddress);
        }
        if (!ListenerUtil.mutListener.listen(3771)) {
            mHeaderDisplayName.setText(mDisplayName);
        }
        if (!ListenerUtil.mutListener.listen(3772)) {
            mHeaderEmailAddress.setText(mEmailAddress);
        }
        if (!ListenerUtil.mutListener.listen(3773)) {
            mEditTextDisplayName.setText(mDisplayName);
        }
        if (!ListenerUtil.mutListener.listen(3774)) {
            mEditTextUsername.setText(mUsername);
        }
        // Set fragment arguments to know if account should be updated when values change.
        Bundle args = new Bundle();
        if (!ListenerUtil.mutListener.listen(3775)) {
            args.putString(ARG_DISPLAY_NAME, mDisplayName);
        }
        if (!ListenerUtil.mutListener.listen(3776)) {
            args.putString(ARG_EMAIL_ADDRESS, mEmailAddress);
        }
        if (!ListenerUtil.mutListener.listen(3777)) {
            args.putString(ARG_PHOTO_URL, mPhotoUrl);
        }
        if (!ListenerUtil.mutListener.listen(3778)) {
            args.putString(ARG_USERNAME, mUsername);
        }
        if (!ListenerUtil.mutListener.listen(3779)) {
            args.putBoolean(ARG_IS_EMAIL_SIGNUP, mIsEmailSignup);
        }
        if (!ListenerUtil.mutListener.listen(3780)) {
            setArguments(args);
        }
    }

    protected void showErrorDialog(String message) {
        DialogInterface.OnClickListener dialogListener = new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (!ListenerUtil.mutListener.listen(3783)) {
                    switch(which) {
                        case DialogInterface.BUTTON_NEGATIVE:
                            if (!ListenerUtil.mutListener.listen(3781)) {
                                undoChanges();
                            }
                            break;
                        case DialogInterface.BUTTON_POSITIVE:
                            if (!ListenerUtil.mutListener.listen(3782)) {
                                updateAccountOrContinue();
                            }
                            break;
                    }
                }
            }
        };
        AlertDialog dialog = new MaterialAlertDialogBuilder(getActivity()).setMessage(message).setNeutralButton(R.string.login_error_button, dialogListener).setNegativeButton(R.string.signup_epilogue_error_button_negative, dialogListener).setPositiveButton(R.string.signup_epilogue_error_button_positive, dialogListener).create();
        if (!ListenerUtil.mutListener.listen(3784)) {
            dialog.show();
        }
    }

    protected void showErrorDialogWithCloseButton(String message) {
        AlertDialog dialog = new MaterialAlertDialogBuilder(getActivity()).setMessage(message).setPositiveButton(R.string.login_error_button, null).create();
        if (!ListenerUtil.mutListener.listen(3785)) {
            dialog.show();
        }
    }

    protected void startCropActivity(Uri uri) {
        final Context baseContext = getActivity();
        if (!ListenerUtil.mutListener.listen(3793)) {
            if (baseContext != null) {
                final Context context = new ContextThemeWrapper(baseContext, R.style.WordPress_NoActionBar);
                UCrop.Options options = new UCrop.Options();
                if (!ListenerUtil.mutListener.listen(3786)) {
                    options.setShowCropGrid(false);
                }
                if (!ListenerUtil.mutListener.listen(3787)) {
                    options.setStatusBarColor(ContextExtensionsKt.getColorFromAttribute(context, android.R.attr.statusBarColor));
                }
                if (!ListenerUtil.mutListener.listen(3788)) {
                    options.setToolbarColor(ContextExtensionsKt.getColorFromAttribute(context, R.attr.wpColorAppBar));
                }
                if (!ListenerUtil.mutListener.listen(3789)) {
                    options.setToolbarWidgetColor(ContextExtensionsKt.getColorFromAttribute(context, R.attr.colorOnSurface));
                }
                if (!ListenerUtil.mutListener.listen(3790)) {
                    options.setAllowedGestures(UCropActivity.SCALE, UCropActivity.NONE, UCropActivity.NONE);
                }
                if (!ListenerUtil.mutListener.listen(3791)) {
                    options.setHideBottomControls(true);
                }
                if (!ListenerUtil.mutListener.listen(3792)) {
                    UCrop.of(uri, Uri.fromFile(new File(context.getCacheDir(), "cropped.jpg"))).withAspectRatio(1, 1).withOptions(options).start(context, this);
                }
            }
        }
    }

    protected void startGravatarUpload(final String filePath) {
        if (!ListenerUtil.mutListener.listen(3807)) {
            if (!TextUtils.isEmpty(filePath)) {
                final File file = new File(filePath);
                if (!ListenerUtil.mutListener.listen(3806)) {
                    if (file.exists()) {
                        if (!ListenerUtil.mutListener.listen(3796)) {
                            startProgress(false);
                        }
                        if (!ListenerUtil.mutListener.listen(3805)) {
                            GravatarApi.uploadGravatar(file, mAccountStore.getAccount().getEmail(), mAccountStore.getAccessToken(), new GravatarApi.GravatarUploadListener() {

                                @Override
                                public void onSuccess() {
                                    if (!ListenerUtil.mutListener.listen(3797)) {
                                        endProgress();
                                    }
                                    if (!ListenerUtil.mutListener.listen(3798)) {
                                        mPhotoUrl = GravatarUtils.fixGravatarUrl(mAccount.getAccount().getAvatarUrl(), getResources().getDimensionPixelSize(R.dimen.avatar_sz_large));
                                    }
                                    if (!ListenerUtil.mutListener.listen(3799)) {
                                        loadAvatar(mPhotoUrl, filePath);
                                    }
                                    if (!ListenerUtil.mutListener.listen(3800)) {
                                        mHeaderAvatarAdd.setVisibility(View.GONE);
                                    }
                                    if (!ListenerUtil.mutListener.listen(3801)) {
                                        mIsAvatarAdded = true;
                                    }
                                }

                                @Override
                                public void onError() {
                                    if (!ListenerUtil.mutListener.listen(3802)) {
                                        endProgress();
                                    }
                                    if (!ListenerUtil.mutListener.listen(3803)) {
                                        showErrorDialogWithCloseButton(getString(R.string.signup_epilogue_error_avatar));
                                    }
                                    if (!ListenerUtil.mutListener.listen(3804)) {
                                        AppLog.e(T.NUX, "Uploading image to Gravatar failed");
                                    }
                                }
                            });
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(3795)) {
                            ToastUtils.showToast(getActivity(), R.string.error_locating_image, ToastUtils.Duration.SHORT);
                        }
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(3794)) {
                    ToastUtils.showToast(getActivity(), R.string.error_locating_image, ToastUtils.Duration.SHORT);
                }
            }
        }
    }

    protected void undoChanges() {
        if (!ListenerUtil.mutListener.listen(3808)) {
            mDisplayName = !TextUtils.isEmpty(mAccountStore.getAccount().getDisplayName()) ? mAccountStore.getAccount().getDisplayName() : getArguments().getString(ARG_DISPLAY_NAME);
        }
        if (!ListenerUtil.mutListener.listen(3809)) {
            mEditTextDisplayName.setText(mDisplayName);
        }
        if (!ListenerUtil.mutListener.listen(3810)) {
            mUsername = !TextUtils.isEmpty(mAccountStore.getAccount().getUserName()) ? mAccountStore.getAccount().getUserName() : getArguments().getString(ARG_USERNAME);
        }
        if (!ListenerUtil.mutListener.listen(3811)) {
            mEditTextUsername.setText(mUsername);
        }
        if (!ListenerUtil.mutListener.listen(3812)) {
            mInputPassword.getEditText().setText("");
        }
        if (!ListenerUtil.mutListener.listen(3813)) {
            updateAccountOrContinue();
        }
    }

    protected void updateAccountOrContinue() {
        if (!ListenerUtil.mutListener.listen(3827)) {
            if (changedUsername()) {
                if (!ListenerUtil.mutListener.listen(3825)) {
                    startProgressIfNeeded();
                }
                if (!ListenerUtil.mutListener.listen(3826)) {
                    updateUsername();
                }
            } else if (changedDisplayName()) {
                if (!ListenerUtil.mutListener.listen(3822)) {
                    startProgressIfNeeded();
                }
                if (!ListenerUtil.mutListener.listen(3823)) {
                    mIsUpdatingDisplayName = true;
                }
                if (!ListenerUtil.mutListener.listen(3824)) {
                    updateDisplayName();
                }
            } else if ((ListenerUtil.mutListener.listen(3814) ? (changedPassword() || !mHasUpdatedPassword) : (changedPassword() && !mHasUpdatedPassword))) {
                if (!ListenerUtil.mutListener.listen(3819)) {
                    startProgressIfNeeded();
                }
                if (!ListenerUtil.mutListener.listen(3820)) {
                    mIsUpdatingPassword = true;
                }
                if (!ListenerUtil.mutListener.listen(3821)) {
                    updatePassword();
                }
            } else if (mSignupEpilogueListener != null) {
                if (!ListenerUtil.mutListener.listen(3816)) {
                    if (!mHasMadeUpdates) {
                        if (!ListenerUtil.mutListener.listen(3815)) {
                            AnalyticsTracker.track(mIsEmailSignup ? AnalyticsTracker.Stat.SIGNUP_EMAIL_EPILOGUE_UNCHANGED : AnalyticsTracker.Stat.SIGNUP_SOCIAL_EPILOGUE_UNCHANGED);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(3817)) {
                    endProgressIfNeeded();
                }
                if (!ListenerUtil.mutListener.listen(3818)) {
                    mSignupEpilogueListener.onContinue();
                }
            }
        }
    }

    private void updateDisplayName() {
        PushAccountSettingsPayload payload = new PushAccountSettingsPayload();
        if (!ListenerUtil.mutListener.listen(3828)) {
            payload.params = new HashMap<>();
        }
        if (!ListenerUtil.mutListener.listen(3829)) {
            payload.params.put("display_name", mDisplayName);
        }
        if (!ListenerUtil.mutListener.listen(3830)) {
            mDispatcher.dispatch(AccountActionBuilder.newPushSettingsAction(payload));
        }
    }

    private void updatePassword() {
        PushAccountSettingsPayload payload = new PushAccountSettingsPayload();
        if (!ListenerUtil.mutListener.listen(3831)) {
            payload.params = new HashMap<>();
        }
        if (!ListenerUtil.mutListener.listen(3832)) {
            payload.params.put("password", mInputPassword.getEditText().getText().toString());
        }
        if (!ListenerUtil.mutListener.listen(3833)) {
            mDispatcher.dispatch(AccountActionBuilder.newPushSettingsAction(payload));
        }
    }

    private void updateUsername() {
        PushUsernamePayload payload = new PushUsernamePayload(mUsername, AccountUsernameActionType.KEEP_OLD_SITE_AND_ADDRESS);
        if (!ListenerUtil.mutListener.listen(3834)) {
            mDispatcher.dispatch(AccountActionBuilder.newPushUsernameAction(payload));
        }
    }

    private class DownloadAvatarAndUploadGravatarThread extends Thread {

        private String mEmail;

        private String mToken;

        private String mUrl;

        DownloadAvatarAndUploadGravatarThread(String url, String email, String token) {
            if (!ListenerUtil.mutListener.listen(3835)) {
                mUrl = url;
            }
            if (!ListenerUtil.mutListener.listen(3836)) {
                mEmail = email;
            }
            if (!ListenerUtil.mutListener.listen(3837)) {
                mToken = token;
            }
        }

        @Override
        public void run() {
            try {
                Uri uri = MediaUtils.downloadExternalMedia(getContext(), Uri.parse(mUrl));
                File file = new File(new URI(uri.toString()));
                if (!ListenerUtil.mutListener.listen(3841)) {
                    GravatarApi.uploadGravatar(file, mEmail, mToken, new GravatarApi.GravatarUploadListener() {

                        @Override
                        public void onSuccess() {
                            if (!ListenerUtil.mutListener.listen(3839)) {
                                AppLog.i(T.NUX, "Google avatar download and Gravatar upload succeeded.");
                            }
                        }

                        @Override
                        public void onError() {
                            if (!ListenerUtil.mutListener.listen(3840)) {
                                AppLog.i(T.NUX, "Google avatar download and Gravatar upload failed.");
                            }
                        }
                    });
                }
            } catch (NullPointerException | URISyntaxException exception) {
                if (!ListenerUtil.mutListener.listen(3838)) {
                    AppLog.e(T.NUX, "Google avatar download and Gravatar upload failed - " + exception.toString() + " - " + exception.getMessage());
                }
            }
        }
    }
}
