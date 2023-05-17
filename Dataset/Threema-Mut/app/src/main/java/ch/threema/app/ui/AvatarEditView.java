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
package ch.threema.app.ui;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.provider.MediaStore;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.ref.WeakReference;
import androidx.annotation.IntDef;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.UiThread;
import androidx.annotation.WorkerThread;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.ContextThemeWrapper;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.view.menu.MenuPopupHelper;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;
import ch.threema.app.R;
import ch.threema.app.ThreemaApplication;
import ch.threema.app.activities.CropImageActivity;
import ch.threema.app.listeners.ContactListener;
import ch.threema.app.managers.ListenerManager;
import ch.threema.app.services.ContactService;
import ch.threema.app.services.FileService;
import ch.threema.app.services.GroupService;
import ch.threema.app.services.PreferenceService;
import ch.threema.app.utils.AvatarConverterUtil;
import ch.threema.app.utils.BitmapUtil;
import ch.threema.app.utils.ColorUtil;
import ch.threema.app.utils.ConfigUtils;
import ch.threema.app.utils.ContactUtil;
import ch.threema.app.utils.FileUtil;
import ch.threema.app.utils.RuntimeUtil;
import ch.threema.app.utils.TestUtil;
import ch.threema.storage.models.ContactModel;
import ch.threema.storage.models.GroupModel;
import static android.app.Activity.RESULT_OK;
import static ch.threema.app.dialogs.ContactEditDialog.CONTACT_AVATAR_HEIGHT_PX;
import static ch.threema.app.dialogs.ContactEditDialog.CONTACT_AVATAR_WIDTH_PX;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class AvatarEditView extends FrameLayout implements DefaultLifecycleObserver, View.OnClickListener, View.OnLongClickListener {

    private static final Logger logger = LoggerFactory.getLogger(AvatarEditView.class);

    private static final int REQUEST_CODE_FILE_SELECTOR_ID = 43320;

    private static final int REQUEST_CODE_CAMERA_PERMISSION = 43321;

    private static final int REQUEST_CODE_CAMERA = 43322;

    private static final int REQUEST_CODE_CROP = 43323;

    private ContactService contactService;

    private GroupService groupService;

    private FileService fileService;

    private PreferenceService preferenceService;

    private ImageView avatarImage, avatarEditOverlay;

    private WeakReference<AvatarEditListener> listenerRef = new WeakReference<>(null);

    private boolean hires, isEditable, isMyProfilePicture;

    // the hosting fragment
    private WeakReference<Fragment> fragmentRef = new WeakReference<>(null);

    private WeakReference<AppCompatActivity> activityRef = new WeakReference<>(null);

    // the VieModel containing all data for this view
    public AvatarEditViewModel avatarData;

    // the type of avatar
    @Retention(RetentionPolicy.SOURCE)
    @IntDef({ AVATAR_TYPE_CONTACT, AVATAR_TYPE_GROUP, AVATAR_TYPE_NOTES })
    public @interface AvatarTypeDef {
    }

    public static final int AVATAR_TYPE_CONTACT = 0;

    public static final int AVATAR_TYPE_GROUP = 1;

    public static final int AVATAR_TYPE_NOTES = 2;

    public AvatarEditView(@NonNull Context context) {
        super(context);
        if (!ListenerUtil.mutListener.listen(44427)) {
            init(context);
        }
    }

    public AvatarEditView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        if (!ListenerUtil.mutListener.listen(44428)) {
            init(context);
        }
    }

    public AvatarEditView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        if (!ListenerUtil.mutListener.listen(44429)) {
            init(context);
        }
    }

    private void init(Context context) {
        if (!ListenerUtil.mutListener.listen(44430)) {
            getActivity().getLifecycle().addObserver(this);
        }
        if (!ListenerUtil.mutListener.listen(44431)) {
            avatarData = new ViewModelProvider(getActivity()).get(AvatarEditViewModel.class);
        }
        try {
            if (!ListenerUtil.mutListener.listen(44433)) {
                contactService = ThreemaApplication.getServiceManager().getContactService();
            }
            if (!ListenerUtil.mutListener.listen(44434)) {
                groupService = ThreemaApplication.getServiceManager().getGroupService();
            }
            if (!ListenerUtil.mutListener.listen(44435)) {
                fileService = ThreemaApplication.getServiceManager().getFileService();
            }
            if (!ListenerUtil.mutListener.listen(44436)) {
                preferenceService = ThreemaApplication.getServiceManager().getPreferenceService();
            }
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(44432)) {
                logger.error("Exception", e);
            }
            return;
        }
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (!ListenerUtil.mutListener.listen(44437)) {
            inflater.inflate(R.layout.view_avatar_edit, this);
        }
        if (!ListenerUtil.mutListener.listen(44438)) {
            this.avatarImage = findViewById(R.id.avatar_view);
        }
        if (!ListenerUtil.mutListener.listen(44439)) {
            this.avatarImage.setClickable(true);
        }
        if (!ListenerUtil.mutListener.listen(44440)) {
            this.avatarImage.setFocusable(true);
        }
        if (!ListenerUtil.mutListener.listen(44441)) {
            this.avatarImage.setOnClickListener(this);
        }
        if (!ListenerUtil.mutListener.listen(44442)) {
            this.avatarImage.setOnLongClickListener(this);
        }
        if (!ListenerUtil.mutListener.listen(44443)) {
            this.avatarEditOverlay = findViewById(R.id.avatar_edit);
        }
        if (!ListenerUtil.mutListener.listen(44444)) {
            this.avatarEditOverlay.setVisibility(View.VISIBLE);
        }
        if (!ListenerUtil.mutListener.listen(44445)) {
            this.isEditable = true;
        }
    }

    private final ContactListener contactListener = new ContactListener() {

        @Override
        public void onModified(ContactModel modifiedContactModel) {
            if (!ListenerUtil.mutListener.listen(44446)) {
                RuntimeUtil.runOnUiThread(() -> loadAvatarForModel(modifiedContactModel, null));
            }
        }

        @Override
        public void onAvatarChanged(ContactModel contactModel) {
        }

        @Override
        public void onRemoved(ContactModel removedContactModel) {
        }

        @Override
        public boolean handle(String identity) {
            if (!ListenerUtil.mutListener.listen(44447)) {
                if (avatarData.getContactModel() != null) {
                    return TestUtil.compare(avatarData.getContactModel().getIdentity(), identity);
                }
            }
            return false;
        }
    };

    /**
     *  Load saved avatar for the specified model - do not call this if changes are to be deferred
     */
    @SuppressLint("StaticFieldLeak")
    @UiThread
    public synchronized void loadAvatarForModel(ContactModel contactModel, GroupModel groupModel) {
        if (!ListenerUtil.mutListener.listen(44455)) {
            new AsyncTask<Void, Void, Bitmap>() {

                @Override
                protected Bitmap doInBackground(Void... params) {
                    if (contactModel != null) {
                        return contactService.getAvatar(avatarData.getContactModel(), hires);
                    } else if (groupModel != null) {
                        Bitmap groupAvatar = groupService.getAvatar(groupModel, hires);
                        if (!ListenerUtil.mutListener.listen(44449)) {
                            if (groupAvatar == null) {
                                if (!ListenerUtil.mutListener.listen(44448)) {
                                    groupAvatar = groupService.getDefaultAvatar(groupModel, hires);
                                }
                            }
                        }
                        return groupAvatar;
                    } else {
                        return groupService.getDefaultAvatar(groupModel, hires);
                    }
                }

                @Override
                protected void onPostExecute(Bitmap bitmap) {
                    if (!ListenerUtil.mutListener.listen(44451)) {
                        if (avatarImage != null) {
                            if (!ListenerUtil.mutListener.listen(44450)) {
                                setAvatarBitmap(bitmap);
                            }
                        }
                    }
                    boolean editable = isAvatarEditable();
                    if (!ListenerUtil.mutListener.listen(44452)) {
                        avatarImage.setClickable(editable);
                    }
                    if (!ListenerUtil.mutListener.listen(44453)) {
                        avatarImage.setFocusable(editable);
                    }
                    if (!ListenerUtil.mutListener.listen(44454)) {
                        avatarEditOverlay.setVisibility(editable ? View.VISIBLE : View.GONE);
                    }
                }
            }.execute();
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        if (!ListenerUtil.mutListener.listen(44456)) {
            // ListenerManager.profileListeners.remove(this.profileListener);
            super.onDetachedFromWindow();
        }
    }

    @Override
    protected void onAttachedToWindow() {
        if (!ListenerUtil.mutListener.listen(44457)) {
            super.onAttachedToWindow();
        }
    }

    @Nullable
    private AppCompatActivity getActivity() {
        return getActivity(getContext());
    }

    @Nullable
    private AppCompatActivity getActivity(@NonNull Context context) {
        if (!ListenerUtil.mutListener.listen(44462)) {
            if (activityRef.get() == null) {
                if (!ListenerUtil.mutListener.listen(44461)) {
                    if (context instanceof ContextWrapper) {
                        if (!ListenerUtil.mutListener.listen(44460)) {
                            if (context instanceof AppCompatActivity) {
                                if (!ListenerUtil.mutListener.listen(44459)) {
                                    activityRef = new WeakReference<>((AppCompatActivity) context);
                                }
                            } else {
                                if (!ListenerUtil.mutListener.listen(44458)) {
                                    activityRef = new WeakReference<>(getActivity(((ContextWrapper) context).getBaseContext()));
                                }
                            }
                        }
                    }
                }
            }
        }
        return activityRef.get();
    }

    @SuppressLint("RestrictedApi")
    @Override
    public void onClick(View v) {
        if (!ListenerUtil.mutListener.listen(44463)) {
            if (!isAvatarEditable()) {
                return;
            }
        }
        MenuBuilder menuBuilder = new MenuBuilder(getContext());
        if (!ListenerUtil.mutListener.listen(44464)) {
            new MenuInflater(getContext()).inflate(R.menu.view_avatar_edit, menuBuilder);
        }
        if (!ListenerUtil.mutListener.listen(44465)) {
            ConfigUtils.themeMenu(menuBuilder, ConfigUtils.getColorFromAttribute(getContext(), R.attr.textColorSecondary));
        }
        if (!ListenerUtil.mutListener.listen(44467)) {
            if (!hasAvatar()) {
                if (!ListenerUtil.mutListener.listen(44466)) {
                    menuBuilder.removeItem(R.id.menu_remove_picture);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(44473)) {
            menuBuilder.setCallback(new MenuBuilder.Callback() {

                @Override
                public boolean onMenuItemSelected(MenuBuilder menu, MenuItem item) {
                    if (!ListenerUtil.mutListener.listen(44472)) {
                        switch(item.getItemId()) {
                            case R.id.menu_take_photo:
                                if (!ListenerUtil.mutListener.listen(44469)) {
                                    if (ConfigUtils.requestCameraPermissions(getActivity(), getFragment(), REQUEST_CODE_CAMERA_PERMISSION)) {
                                        if (!ListenerUtil.mutListener.listen(44468)) {
                                            openCamera();
                                        }
                                    }
                                }
                                break;
                            case R.id.menu_select_from_gallery:
                                if (!ListenerUtil.mutListener.listen(44470)) {
                                    FileUtil.selectFromGallery(getActivity(), getFragment(), REQUEST_CODE_FILE_SELECTOR_ID, false);
                                }
                                break;
                            case R.id.menu_remove_picture:
                                if (!ListenerUtil.mutListener.listen(44471)) {
                                    removeAvatar();
                                }
                                break;
                            default:
                                return false;
                        }
                    }
                    return true;
                }

                @Override
                public void onMenuModeChange(MenuBuilder menu) {
                }
            });
        }
        Context wrapper = new ContextThemeWrapper(getContext(), ConfigUtils.getAppTheme(getContext()) == ConfigUtils.THEME_DARK ? R.style.AppBaseTheme_Dark : R.style.AppBaseTheme);
        MenuPopupHelper optionsMenu = new MenuPopupHelper(wrapper, menuBuilder, avatarEditOverlay);
        if (!ListenerUtil.mutListener.listen(44474)) {
            optionsMenu.setForceShowIcon(true);
        }
        if (!ListenerUtil.mutListener.listen(44475)) {
            optionsMenu.show();
        }
    }

    @Override
    public boolean onLongClick(View v) {
        View parent = getRootView();
        if (!ListenerUtil.mutListener.listen(44477)) {
            new AsyncTask<Void, Void, Bitmap>() {

                @Override
                protected Bitmap doInBackground(Void... voids) {
                    return getCurrentAvatarBitmap(true);
                }

                @Override
                protected void onPostExecute(Bitmap bitmap) {
                    ImagePopup detailPopup = new ImagePopup(getContext(), parent, R.layout.popup_image_nomargin);
                    if (!ListenerUtil.mutListener.listen(44476)) {
                        detailPopup.show(AvatarEditView.this, bitmap, null);
                    }
                }
            }.execute();
        }
        return false;
    }

    @UiThread
    @SuppressLint("StaticFieldLeak")
    private void removeAvatar() {
        if (!ListenerUtil.mutListener.listen(44478)) {
            loadDefaultAvatar(avatarData.getContactModel(), avatarData.getGroupModel());
        }
        if (!ListenerUtil.mutListener.listen(44486)) {
            if (listenerRef.get() != null) {
                if (!ListenerUtil.mutListener.listen(44485)) {
                    listenerRef.get().onAvatarRemoved();
                }
            } else {
                if (!ListenerUtil.mutListener.listen(44484)) {
                    new AsyncTask<Void, Void, Void>() {

                        @Override
                        protected Void doInBackground(Void... voids) {
                            if (!ListenerUtil.mutListener.listen(44482)) {
                                if (avatarData.getContactModel() != null) {
                                    if (!ListenerUtil.mutListener.listen(44480)) {
                                        contactService.removeAvatar(avatarData.getContactModel());
                                    }
                                    if (!ListenerUtil.mutListener.listen(44481)) {
                                        fileService.removeContactPhoto(avatarData.getContactModel());
                                    }
                                } else if (avatarData.getGroupModel() != null) {
                                    if (!ListenerUtil.mutListener.listen(44479)) {
                                        saveGroupAvatar(null, true);
                                    }
                                }
                            }
                            return null;
                        }

                        @Override
                        protected void onPostExecute(Void aVoid) {
                            if (!ListenerUtil.mutListener.listen(44483)) {
                                loadAvatarForModel(avatarData.getContactModel(), avatarData.getGroupModel());
                            }
                        }
                    }.execute();
                }
            }
        }
    }

    private void loadDefaultAvatar(ContactModel contactModel, GroupModel groupModel) {
        if (!ListenerUtil.mutListener.listen(44489)) {
            if (contactModel != null) {
                if (!ListenerUtil.mutListener.listen(44488)) {
                    setAvatarBitmap(contactService.getDefaultAvatar(avatarData.getContactModel(), hires));
                }
            } else if (groupModel != null) {
                if (!ListenerUtil.mutListener.listen(44487)) {
                    setAvatarBitmap(groupService.getDefaultAvatar(avatarData.getGroupModel(), hires));
                }
            }
        }
    }

    /**
     *  Save avatar bitmap data to group model
     *  @param avatar
     *  @param removeAvatar
     */
    @WorkerThread
    public void saveGroupAvatar(Bitmap avatar, boolean removeAvatar) {
        if (!ListenerUtil.mutListener.listen(44493)) {
            if ((ListenerUtil.mutListener.listen(44490) ? (avatar != null && removeAvatar) : (avatar != null || removeAvatar))) {
                try {
                    if (!ListenerUtil.mutListener.listen(44492)) {
                        groupService.updateGroup(avatarData.getGroupModel(), null, null, avatar, removeAvatar);
                    }
                } catch (Exception x) {
                    if (!ListenerUtil.mutListener.listen(44491)) {
                        logger.error("Exception", x);
                    }
                }
            }
        }
    }

    private void openCamera() {
        try {
            if (!ListenerUtil.mutListener.listen(44495)) {
                avatarData.setCameraFile(fileService.createTempFile(".camera", ".jpg", !ConfigUtils.useContentUris()));
            }
            if (!ListenerUtil.mutListener.listen(44496)) {
                FileUtil.getCameraFile(getActivity(), getFragment(), avatarData.getCameraFile(), REQUEST_CODE_CAMERA, fileService, true);
            }
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(44494)) {
                logger.error("Exception", e);
            }
        }
    }

    private void doCrop(File srcFile, int orientation) {
        try {
            if (!ListenerUtil.mutListener.listen(44498)) {
                avatarData.setCroppedFile(fileService.createTempFile(".avatar", ".jpg"));
            }
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(44497)) {
                logger.error("Exception", e);
            }
        }
        Intent intent = new Intent(getActivity(), CropImageActivity.class);
        if (!ListenerUtil.mutListener.listen(44499)) {
            intent.setData(Uri.fromFile(srcFile));
        }
        if (!ListenerUtil.mutListener.listen(44500)) {
            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(avatarData.getCroppedFile()));
        }
        if (!ListenerUtil.mutListener.listen(44501)) {
            intent.putExtra(CropImageActivity.EXTRA_MAX_X, CONTACT_AVATAR_WIDTH_PX);
        }
        if (!ListenerUtil.mutListener.listen(44502)) {
            intent.putExtra(CropImageActivity.EXTRA_MAX_Y, CONTACT_AVATAR_HEIGHT_PX);
        }
        if (!ListenerUtil.mutListener.listen(44503)) {
            intent.putExtra(CropImageActivity.EXTRA_ASPECT_X, 1);
        }
        if (!ListenerUtil.mutListener.listen(44504)) {
            intent.putExtra(CropImageActivity.EXTRA_ASPECT_Y, 1);
        }
        if (!ListenerUtil.mutListener.listen(44505)) {
            intent.putExtra(CropImageActivity.EXTRA_OVAL, true);
        }
        if (!ListenerUtil.mutListener.listen(44506)) {
            intent.putExtra(ThreemaApplication.EXTRA_ORIENTATION, orientation);
        }
        if (!ListenerUtil.mutListener.listen(44509)) {
            if (getFragment() != null) {
                if (!ListenerUtil.mutListener.listen(44508)) {
                    getFragment().startActivityForResult(intent, REQUEST_CODE_CROP);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(44507)) {
                    getActivity().startActivityForResult(intent, REQUEST_CODE_CROP);
                }
            }
        }
    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (!ListenerUtil.mutListener.listen(44528)) {
            if ((ListenerUtil.mutListener.listen(44514) ? (requestCode >= REQUEST_CODE_CAMERA_PERMISSION) : (ListenerUtil.mutListener.listen(44513) ? (requestCode <= REQUEST_CODE_CAMERA_PERMISSION) : (ListenerUtil.mutListener.listen(44512) ? (requestCode > REQUEST_CODE_CAMERA_PERMISSION) : (ListenerUtil.mutListener.listen(44511) ? (requestCode < REQUEST_CODE_CAMERA_PERMISSION) : (ListenerUtil.mutListener.listen(44510) ? (requestCode != REQUEST_CODE_CAMERA_PERMISSION) : (requestCode == REQUEST_CODE_CAMERA_PERMISSION))))))) {
                if (!ListenerUtil.mutListener.listen(44527)) {
                    if ((ListenerUtil.mutListener.listen(44520) ? ((ListenerUtil.mutListener.listen(44519) ? (grantResults.length >= 0) : (ListenerUtil.mutListener.listen(44518) ? (grantResults.length <= 0) : (ListenerUtil.mutListener.listen(44517) ? (grantResults.length < 0) : (ListenerUtil.mutListener.listen(44516) ? (grantResults.length != 0) : (ListenerUtil.mutListener.listen(44515) ? (grantResults.length == 0) : (grantResults.length > 0)))))) || grantResults[0] == PackageManager.PERMISSION_GRANTED) : ((ListenerUtil.mutListener.listen(44519) ? (grantResults.length >= 0) : (ListenerUtil.mutListener.listen(44518) ? (grantResults.length <= 0) : (ListenerUtil.mutListener.listen(44517) ? (grantResults.length < 0) : (ListenerUtil.mutListener.listen(44516) ? (grantResults.length != 0) : (ListenerUtil.mutListener.listen(44515) ? (grantResults.length == 0) : (grantResults.length > 0)))))) && grantResults[0] == PackageManager.PERMISSION_GRANTED))) {
                        if (!ListenerUtil.mutListener.listen(44526)) {
                            openCamera();
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(44525)) {
                            if ((ListenerUtil.mutListener.listen(44523) ? (((ListenerUtil.mutListener.listen(44521) ? (getActivity() != null || !ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.CAMERA)) : (getActivity() != null && !ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.CAMERA)))) && ((ListenerUtil.mutListener.listen(44522) ? (getFragment() != null || !getFragment().shouldShowRequestPermissionRationale(Manifest.permission.CAMERA)) : (getFragment() != null && !getFragment().shouldShowRequestPermissionRationale(Manifest.permission.CAMERA))))) : (((ListenerUtil.mutListener.listen(44521) ? (getActivity() != null || !ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.CAMERA)) : (getActivity() != null && !ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.CAMERA)))) || ((ListenerUtil.mutListener.listen(44522) ? (getFragment() != null || !getFragment().shouldShowRequestPermissionRationale(Manifest.permission.CAMERA)) : (getFragment() != null && !getFragment().shouldShowRequestPermissionRationale(Manifest.permission.CAMERA))))))) {
                                if (!ListenerUtil.mutListener.listen(44524)) {
                                    ConfigUtils.showPermissionRationale(getContext(), null, R.string.permission_camera_photo_required);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @SuppressLint("StaticFieldLeak")
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (!ListenerUtil.mutListener.listen(44561)) {
            if (resultCode == RESULT_OK) {
                if (!ListenerUtil.mutListener.listen(44560)) {
                    switch(requestCode) {
                        case REQUEST_CODE_FILE_SELECTOR_ID:
                            if (!ListenerUtil.mutListener.listen(44535)) {
                                // return from image picker
                                if ((ListenerUtil.mutListener.listen(44529) ? (intent != null || intent.getData() != null) : (intent != null && intent.getData() != null))) {
                                    try {
                                        if (!ListenerUtil.mutListener.listen(44531)) {
                                            avatarData.setCameraFile(fileService.createTempFile(".camera", ".jpg", !ConfigUtils.useContentUris()));
                                        }
                                        try (InputStream is = getActivity().getContentResolver().openInputStream(intent.getData());
                                            FileOutputStream fos = new FileOutputStream(avatarData.getCameraFile())) {
                                            if (!ListenerUtil.mutListener.listen(44533)) {
                                                if (is != null) {
                                                    if (!ListenerUtil.mutListener.listen(44532)) {
                                                        IOUtils.copy(is, fos);
                                                    }
                                                } else {
                                                    throw new Exception("Unable to open input stream");
                                                }
                                            }
                                        }
                                        if (!ListenerUtil.mutListener.listen(44534)) {
                                            doCrop(avatarData.getCameraFile(), 0);
                                        }
                                    } catch (Exception e) {
                                        if (!ListenerUtil.mutListener.listen(44530)) {
                                            logger.error("Exception", e);
                                        }
                                    }
                                }
                            }
                            break;
                        case REQUEST_CODE_CROP:
                            Bitmap bitmap = null;
                            if (!ListenerUtil.mutListener.listen(44554)) {
                                if ((ListenerUtil.mutListener.listen(44542) ? ((ListenerUtil.mutListener.listen(44536) ? (avatarData.getCroppedFile() != null || avatarData.getCroppedFile().exists()) : (avatarData.getCroppedFile() != null && avatarData.getCroppedFile().exists())) || (ListenerUtil.mutListener.listen(44541) ? (avatarData.getCroppedFile().length() >= 0) : (ListenerUtil.mutListener.listen(44540) ? (avatarData.getCroppedFile().length() <= 0) : (ListenerUtil.mutListener.listen(44539) ? (avatarData.getCroppedFile().length() < 0) : (ListenerUtil.mutListener.listen(44538) ? (avatarData.getCroppedFile().length() != 0) : (ListenerUtil.mutListener.listen(44537) ? (avatarData.getCroppedFile().length() == 0) : (avatarData.getCroppedFile().length() > 0))))))) : ((ListenerUtil.mutListener.listen(44536) ? (avatarData.getCroppedFile() != null || avatarData.getCroppedFile().exists()) : (avatarData.getCroppedFile() != null && avatarData.getCroppedFile().exists())) && (ListenerUtil.mutListener.listen(44541) ? (avatarData.getCroppedFile().length() >= 0) : (ListenerUtil.mutListener.listen(44540) ? (avatarData.getCroppedFile().length() <= 0) : (ListenerUtil.mutListener.listen(44539) ? (avatarData.getCroppedFile().length() < 0) : (ListenerUtil.mutListener.listen(44538) ? (avatarData.getCroppedFile().length() != 0) : (ListenerUtil.mutListener.listen(44537) ? (avatarData.getCroppedFile().length() == 0) : (avatarData.getCroppedFile().length() > 0))))))))) {
                                    if (!ListenerUtil.mutListener.listen(44543)) {
                                        bitmap = BitmapUtil.safeGetBitmapFromUri(getActivity(), Uri.fromFile(avatarData.getCroppedFile()), CONTACT_AVATAR_HEIGHT_PX, true);
                                    }
                                    if (!ListenerUtil.mutListener.listen(44553)) {
                                        if (bitmap != null) {
                                            if (!ListenerUtil.mutListener.listen(44552)) {
                                                if (listenerRef.get() != null) {
                                                    if (!ListenerUtil.mutListener.listen(44551)) {
                                                        listenerRef.get().onAvatarSet(avatarData.getCroppedFile());
                                                    }
                                                } else {
                                                    if (!ListenerUtil.mutListener.listen(44550)) {
                                                        if (this.avatarData.getContactModel() != null) {
                                                            try {
                                                                if (!ListenerUtil.mutListener.listen(44548)) {
                                                                    contactService.setAvatar(this.avatarData.getContactModel(), avatarData.getCroppedFile());
                                                                }
                                                                if (!ListenerUtil.mutListener.listen(44549)) {
                                                                    loadAvatarForModel(this.avatarData.getContactModel(), null);
                                                                }
                                                            } catch (Exception e) {
                                                                if (!ListenerUtil.mutListener.listen(44547)) {
                                                                    logger.error("Exception", e);
                                                                }
                                                            }
                                                        } else if (avatarData.getGroupModel() != null) {
                                                            if (!ListenerUtil.mutListener.listen(44546)) {
                                                                new AsyncTask<Bitmap, Void, Void>() {

                                                                    @Override
                                                                    protected Void doInBackground(Bitmap... bitmaps) {
                                                                        if (!ListenerUtil.mutListener.listen(44544)) {
                                                                            saveGroupAvatar(bitmaps[0], false);
                                                                        }
                                                                        return null;
                                                                    }

                                                                    @Override
                                                                    protected void onPostExecute(Void aVoid) {
                                                                        if (!ListenerUtil.mutListener.listen(44545)) {
                                                                            loadAvatarForModel(null, avatarData.getGroupModel());
                                                                        }
                                                                    }
                                                                }.execute(bitmap);
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(44558)) {
                                if (bitmap == null) {
                                    if (!ListenerUtil.mutListener.listen(44557)) {
                                        new AsyncTask<Void, Void, Bitmap>() {

                                            @Override
                                            protected Bitmap doInBackground(Void... voids) {
                                                return getCurrentAvatarBitmap(false);
                                            }

                                            @Override
                                            protected void onPostExecute(Bitmap bitmap) {
                                                if (!ListenerUtil.mutListener.listen(44556)) {
                                                    setAvatarBitmap(bitmap);
                                                }
                                            }
                                        }.execute();
                                    }
                                } else {
                                    if (!ListenerUtil.mutListener.listen(44555)) {
                                        setAvatarBitmap(bitmap);
                                    }
                                }
                            }
                            break;
                        case REQUEST_CODE_CAMERA:
                            int cameraRotation = 0;
                            if (!ListenerUtil.mutListener.listen(44559)) {
                                doCrop(avatarData.getCameraFile(), cameraRotation);
                            }
                            break;
                    }
                }
            }
        }
    }

    @WorkerThread
    @Nullable
    private Bitmap getCurrentAvatarBitmap(boolean hires) {
        if (!ListenerUtil.mutListener.listen(44562)) {
            if (this.avatarData.getContactModel() != null) {
                return contactService.getAvatar(this.avatarData.getContactModel(), hires);
            } else if (this.avatarData.getGroupModel() != null) {
                return groupService.getAvatar(this.avatarData.getGroupModel(), hires);
            }
        }
        return null;
    }

    @UiThread
    private void setAvatarBitmap(@Nullable Bitmap bitmap) {
        if (!ListenerUtil.mutListener.listen(44580)) {
            if (bitmap != null) {
                if (!ListenerUtil.mutListener.listen(44570)) {
                    if (hires) {
                        if (!ListenerUtil.mutListener.listen(44569)) {
                            if (isMyProfilePicture) {
                                if (!ListenerUtil.mutListener.listen(44568)) {
                                    this.avatarImage.setImageDrawable(AvatarConverterUtil.convertToRound(getResources(), bitmap));
                                }
                            } else {
                                if (!ListenerUtil.mutListener.listen(44567)) {
                                    this.avatarImage.setImageBitmap(bitmap);
                                }
                            }
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(44566)) {
                            this.avatarImage.setImageDrawable(AvatarConverterUtil.convertToRound(getResources(), bitmap));
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(44578)) {
                    if ((ListenerUtil.mutListener.listen(44575) ? (ColorUtil.getInstance().calculateBrightness(bitmap, 2) >= 100) : (ListenerUtil.mutListener.listen(44574) ? (ColorUtil.getInstance().calculateBrightness(bitmap, 2) <= 100) : (ListenerUtil.mutListener.listen(44573) ? (ColorUtil.getInstance().calculateBrightness(bitmap, 2) < 100) : (ListenerUtil.mutListener.listen(44572) ? (ColorUtil.getInstance().calculateBrightness(bitmap, 2) != 100) : (ListenerUtil.mutListener.listen(44571) ? (ColorUtil.getInstance().calculateBrightness(bitmap, 2) == 100) : (ColorUtil.getInstance().calculateBrightness(bitmap, 2) > 100))))))) {
                        if (!ListenerUtil.mutListener.listen(44577)) {
                            this.avatarImage.setColorFilter(getResources().getColor(R.color.material_grey_300), PorterDuff.Mode.DARKEN);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(44576)) {
                            this.avatarImage.clearColorFilter();
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(44579)) {
                    this.avatarImage.invalidate();
                }
            } else {
                if (!ListenerUtil.mutListener.listen(44565)) {
                    if ((ListenerUtil.mutListener.listen(44563) ? (this.avatarData.getGroupModel() == null || this.avatarData.getContactModel() == null) : (this.avatarData.getGroupModel() == null && this.avatarData.getContactModel() == null))) {
                        if (!ListenerUtil.mutListener.listen(44564)) {
                            this.avatarImage.setColorFilter(ConfigUtils.getColorFromAttribute(getContext(), R.attr.textColorSecondary), PorterDuff.Mode.SRC_IN);
                        }
                    }
                }
            }
        }
    }

    /**
     *  Check if an avatar is currently set for this entity
     *  @return true if an avatar is set, false if currently no avatar is set
     */
    private boolean hasAvatar() {
        if (!ListenerUtil.mutListener.listen(44582)) {
            if (this.avatarData.getContactModel() != null) {
                return (ListenerUtil.mutListener.listen(44581) ? (fileService.hasContactAvatarFile(this.avatarData.getContactModel()) && fileService.hasContactPhotoFile(this.avatarData.getContactModel())) : (fileService.hasContactAvatarFile(this.avatarData.getContactModel()) || fileService.hasContactPhotoFile(this.avatarData.getContactModel())));
            } else if (this.avatarData.getGroupModel() != null) {
                return fileService.hasGroupAvatarFile(this.avatarData.getGroupModel());
            }
        }
        return false;
    }

    /**
     *  Check if the avatar of this entity can be edited
     *  @return true if user can set an avatar
     */
    private boolean isAvatarEditable() {
        if (!ListenerUtil.mutListener.listen(44587)) {
            if (this.avatarData.getContactModel() != null) {
                return (ListenerUtil.mutListener.listen(44586) ? ((ListenerUtil.mutListener.listen(44584) ? (isEditable || ContactUtil.canHaveCustomAvatar(this.avatarData.getContactModel())) : (isEditable && ContactUtil.canHaveCustomAvatar(this.avatarData.getContactModel()))) || !((ListenerUtil.mutListener.listen(44585) ? (preferenceService.getProfilePicReceive() || fileService.hasContactPhotoFile(this.avatarData.getContactModel())) : (preferenceService.getProfilePicReceive() && fileService.hasContactPhotoFile(this.avatarData.getContactModel()))))) : ((ListenerUtil.mutListener.listen(44584) ? (isEditable || ContactUtil.canHaveCustomAvatar(this.avatarData.getContactModel())) : (isEditable && ContactUtil.canHaveCustomAvatar(this.avatarData.getContactModel()))) && !((ListenerUtil.mutListener.listen(44585) ? (preferenceService.getProfilePicReceive() || fileService.hasContactPhotoFile(this.avatarData.getContactModel())) : (preferenceService.getProfilePicReceive() && fileService.hasContactPhotoFile(this.avatarData.getContactModel()))))));
            } else if (this.avatarData.getGroupModel() != null) {
                return (ListenerUtil.mutListener.listen(44583) ? (isEditable || groupService.isGroupOwner(this.avatarData.getGroupModel())) : (isEditable && groupService.isGroupOwner(this.avatarData.getGroupModel())));
            }
        }
        if (!ListenerUtil.mutListener.listen(44589)) {
            // we have neither a group model nor a contact model => user is creating a new group
            if ((ListenerUtil.mutListener.listen(44588) ? (this.avatarData.getContactModel() == null || this.avatarData.getGroupModel() == null) : (this.avatarData.getContactModel() == null && this.avatarData.getGroupModel() == null))) {
                return isEditable;
            }
        }
        return false;
    }

    public void setFragment(@NonNull Fragment fragment) {
        if (!ListenerUtil.mutListener.listen(44590)) {
            this.fragmentRef = new WeakReference<>(fragment);
        }
    }

    @Nullable
    public Fragment getFragment() {
        return this.fragmentRef.get();
    }

    public void setContactModel(@NonNull ContactModel contactModel) {
        if (!ListenerUtil.mutListener.listen(44591)) {
            this.avatarData.setContactModel(contactModel);
        }
        if (!ListenerUtil.mutListener.listen(44592)) {
            loadAvatarForModel(contactModel, null);
        }
    }

    /**
     *  Set GroupModel that represents this avatar
     *  @param groupModel GroupModel
     */
    public void setGroupModel(@NonNull GroupModel groupModel) {
        if (!ListenerUtil.mutListener.listen(44593)) {
            this.avatarData.setGroupModel(groupModel);
        }
        if (!ListenerUtil.mutListener.listen(44594)) {
            loadAvatarForModel(null, groupModel);
        }
    }

    public void setAvatarFile(File avatarFile) {
        if (!ListenerUtil.mutListener.listen(44605)) {
            if ((ListenerUtil.mutListener.listen(44601) ? ((ListenerUtil.mutListener.listen(44595) ? (avatarFile != null || avatarFile.exists()) : (avatarFile != null && avatarFile.exists())) || (ListenerUtil.mutListener.listen(44600) ? (avatarFile.length() >= 0) : (ListenerUtil.mutListener.listen(44599) ? (avatarFile.length() <= 0) : (ListenerUtil.mutListener.listen(44598) ? (avatarFile.length() < 0) : (ListenerUtil.mutListener.listen(44597) ? (avatarFile.length() != 0) : (ListenerUtil.mutListener.listen(44596) ? (avatarFile.length() == 0) : (avatarFile.length() > 0))))))) : ((ListenerUtil.mutListener.listen(44595) ? (avatarFile != null || avatarFile.exists()) : (avatarFile != null && avatarFile.exists())) && (ListenerUtil.mutListener.listen(44600) ? (avatarFile.length() >= 0) : (ListenerUtil.mutListener.listen(44599) ? (avatarFile.length() <= 0) : (ListenerUtil.mutListener.listen(44598) ? (avatarFile.length() < 0) : (ListenerUtil.mutListener.listen(44597) ? (avatarFile.length() != 0) : (ListenerUtil.mutListener.listen(44596) ? (avatarFile.length() == 0) : (avatarFile.length() > 0))))))))) {
                if (!ListenerUtil.mutListener.listen(44602)) {
                    this.avatarData.setCroppedFile(avatarFile);
                }
                Bitmap bitmap = BitmapUtil.safeGetBitmapFromUri(getActivity(), Uri.fromFile(avatarData.getCroppedFile()), CONTACT_AVATAR_HEIGHT_PX, hires);
                if (!ListenerUtil.mutListener.listen(44604)) {
                    if (bitmap != null) {
                        if (!ListenerUtil.mutListener.listen(44603)) {
                            setAvatarBitmap(bitmap);
                        }
                    }
                }
            }
        }
    }

    /**
     *  Set whether the avatar is editable (i.e. is clickable and gets an overlaid photo button or not)
     *  @param avatarEditable Desired status
     */
    public void setEditable(boolean avatarEditable) {
        if (!ListenerUtil.mutListener.listen(44606)) {
            this.isEditable = avatarEditable;
        }
        if (!ListenerUtil.mutListener.listen(44607)) {
            this.avatarEditOverlay.setVisibility(avatarEditable ? View.VISIBLE : View.GONE);
        }
        if (!ListenerUtil.mutListener.listen(44608)) {
            this.avatarImage.setClickable(avatarEditable);
        }
        if (!ListenerUtil.mutListener.listen(44609)) {
            this.avatarImage.setFocusable(avatarEditable);
        }
    }

    /**
     *  Sets a listener to be notified when changes have been performed by the user
     *  If no listener has been set, any changes will apply immediately, otherwise it's up to the listener to update the underlying data
     *  @param listener AvatarEditListener that wants to know about changes
     */
    public void setListener(AvatarEditListener listener) {
        if (!ListenerUtil.mutListener.listen(44610)) {
            this.listenerRef = new WeakReference<>(listener);
        }
    }

    public void setHires(boolean hires) {
        if (!ListenerUtil.mutListener.listen(44611)) {
            this.hires = hires;
        }
        if (!ListenerUtil.mutListener.listen(44612)) {
            this.avatarImage.setScaleType(ImageView.ScaleType.CENTER_CROP);
        }
        if (!ListenerUtil.mutListener.listen(44620)) {
            if ((ListenerUtil.mutListener.listen(44617) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(44616) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(44615) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(44614) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(44613) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.M) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M))))))) {
                if (!ListenerUtil.mutListener.listen(44618)) {
                    this.avatarEditOverlay.setForeground(getContext().getDrawable(R.drawable.selector_avatar));
                }
                if (!ListenerUtil.mutListener.listen(44619)) {
                    this.avatarImage.setForeground(null);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(44621)) {
            this.avatarImage.setClickable(false);
        }
        if (!ListenerUtil.mutListener.listen(44622)) {
            this.avatarImage.setFocusable(false);
        }
        if (!ListenerUtil.mutListener.listen(44623)) {
            this.avatarImage.setOnClickListener(null);
        }
        if (!ListenerUtil.mutListener.listen(44624)) {
            this.avatarEditOverlay.setClickable(true);
        }
        if (!ListenerUtil.mutListener.listen(44625)) {
            this.avatarEditOverlay.setFocusable(true);
        }
        if (!ListenerUtil.mutListener.listen(44626)) {
            this.avatarEditOverlay.setOnClickListener(this);
        }
    }

    public void setIsMyProfilePicture(boolean isMyProfilePicture) {
        if (!ListenerUtil.mutListener.listen(44627)) {
            this.isMyProfilePicture = isMyProfilePicture;
        }
        if (!ListenerUtil.mutListener.listen(44628)) {
            setHires(true);
        }
    }

    public void setDefaultAvatar(ContactModel contactModel, GroupModel groupModel) {
        if (!ListenerUtil.mutListener.listen(44629)) {
            loadDefaultAvatar(contactModel, groupModel);
        }
    }

    /**
     *  Set avatar representing a contact or group that is yet to be created and thus has no color defined
     *  @param avatarType Type of avatar
     */
    public void setUndefinedAvatar(@AvatarTypeDef int avatarType) {
        if (!ListenerUtil.mutListener.listen(44637)) {
            if ((ListenerUtil.mutListener.listen(44634) ? (avatarType >= AVATAR_TYPE_CONTACT) : (ListenerUtil.mutListener.listen(44633) ? (avatarType <= AVATAR_TYPE_CONTACT) : (ListenerUtil.mutListener.listen(44632) ? (avatarType > AVATAR_TYPE_CONTACT) : (ListenerUtil.mutListener.listen(44631) ? (avatarType < AVATAR_TYPE_CONTACT) : (ListenerUtil.mutListener.listen(44630) ? (avatarType != AVATAR_TYPE_CONTACT) : (avatarType == AVATAR_TYPE_CONTACT))))))) {
                if (!ListenerUtil.mutListener.listen(44636)) {
                    setAvatarBitmap(contactService.getNeutralAvatar(hires));
                }
            } else {
                if (!ListenerUtil.mutListener.listen(44635)) {
                    setAvatarBitmap(groupService.getNeutralAvatar(hires));
                }
            }
        }
    }

    @Override
    public void onCreate(@NonNull LifecycleOwner owner) {
        if (!ListenerUtil.mutListener.listen(44638)) {
            ListenerManager.contactListeners.add(this.contactListener);
        }
    }

    @Override
    public void onDestroy(@NonNull LifecycleOwner owner) {
        if (!ListenerUtil.mutListener.listen(44639)) {
            ListenerManager.contactListeners.remove(this.contactListener);
        }
    }

    public interface AvatarEditListener {

        void onAvatarSet(File avatarFile);

        void onAvatarRemoved();
    }
}
