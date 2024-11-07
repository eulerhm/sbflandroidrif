/**
 * *************************************************************************************
 *  Copyright (c) 2013 Bibek Shrestha <bibekshrestha@gmail.com>                          *
 *  Copyright (c) 2013 Zaur Molotnikov <qutorial@gmail.com>                              *
 *  Copyright (c) 2013 Nicolas Raoul <nicolas.raoul@gmail.com>                           *
 *  Copyright (c) 2013 Flavio Lerda <flerda@gmail.com>                                   *
 *  Copyright (c) 2020 Mike Hardy <github@mikehardy.net>                                 *
 *                                                                                       *
 *  This program is free software; you can redistribute it and/or modify it under        *
 *  the terms of the GNU General Public License as published by the Free Software        *
 *  Foundation; either version 3 of the License, or (at your option) any later           *
 *  version.                                                                             *
 *                                                                                       *
 *  This program is distributed in the hope that it will be useful, but WITHOUT ANY      *
 *  WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A      *
 *  PARTICULAR PURPOSE. See the GNU General Public License for more details.             *
 *                                                                                       *
 *  You should have received a copy of the GNU General Public License along with         *
 *  this program.  If not, see <http://www.gnu.org/licenses/>.                           *
 * **************************************************************************************
 */
package com.ichi2.anki.multimediacard.fields;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ClipData;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.core.content.ContentResolverCompat;
import androidx.core.content.FileProvider;
import android.text.TextUtils;
import android.text.format.Formatter;
import android.provider.DocumentsContract;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import com.ichi2.anki.AnkiDroidApp;
import com.ichi2.anki.R;
import com.ichi2.anki.UIUtils;
import com.ichi2.compat.CompatHelper;
import com.ichi2.ui.FixedEditText;
import com.ichi2.utils.BitmapUtil;
import com.ichi2.utils.ExifUtil;
import com.ichi2.utils.FileUtil;
import com.ichi2.utils.Permissions;
import com.afollestad.materialdialogs.MaterialDialog;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import androidx.core.util.Pair;
import timber.log.Timber;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class BasicImageFieldController extends FieldControllerBase implements IFieldController {

    @VisibleForTesting
    static final int ACTIVITY_SELECT_IMAGE = 1;

    private static final int ACTIVITY_TAKE_PICTURE = 2;

    private static final int ACTIVITY_CROP_PICTURE = 3;

    private static final int IMAGE_SAVE_MAX_WIDTH = 1920;

    private ImageView mImagePreview;

    private TextView mImageFileSize;

    private TextView mImageFileSizeWarning;

    private ImageViewModel mViewModel = new ImageViewModel(null, null);

    // save the latest path to prevent from cropping or taking photo action canceled
    @Nullable
    private String mPreviousImagePath;

    @Nullable
    private Uri mPreviousImageUri;

    // system provided 'External Cache Dir' with "temp-photos" on it
    @Nullable
    private String mAnkiCacheDirectory;

    // e.g.  '/self/primary/Android/data/com.ichi2.anki.AnkiDroid/cache/temp-photos'
    private DisplayMetrics mMetrics = null;

    private Button mCropButton = null;

    private int getMaxImageSize() {
        DisplayMetrics metrics = getDisplayMetrics();
        int height = metrics.heightPixels;
        int width = metrics.widthPixels;
        return (int) Math.min((ListenerUtil.mutListener.listen(1573) ? (height % 0.4) : (ListenerUtil.mutListener.listen(1572) ? (height / 0.4) : (ListenerUtil.mutListener.listen(1571) ? (height - 0.4) : (ListenerUtil.mutListener.listen(1570) ? (height + 0.4) : (height * 0.4))))), (ListenerUtil.mutListener.listen(1577) ? (width % 0.6) : (ListenerUtil.mutListener.listen(1576) ? (width / 0.6) : (ListenerUtil.mutListener.listen(1575) ? (width - 0.6) : (ListenerUtil.mutListener.listen(1574) ? (width + 0.6) : (width * 0.6))))));
    }

    public void loadInstanceState(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(1579)) {
            if (savedInstanceState == null) {
                if (!ListenerUtil.mutListener.listen(1578)) {
                    Timber.i("loadInstanceState but null so nothing to load");
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(1580)) {
            Timber.i("loadInstanceState loading saved state...");
        }
        if (!ListenerUtil.mutListener.listen(1581)) {
            mViewModel = ImageViewModel.fromBundle(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(1582)) {
            mPreviousImagePath = savedInstanceState.getString("mPreviousImagePath");
        }
        if (!ListenerUtil.mutListener.listen(1583)) {
            mPreviousImageUri = savedInstanceState.getParcelable("mPreviousImageUri");
        }
    }

    @Override
    public Bundle saveInstanceState() {
        if (!ListenerUtil.mutListener.listen(1584)) {
            Timber.d("saveInstanceState");
        }
        Bundle savedInstanceState = new Bundle();
        if (!ListenerUtil.mutListener.listen(1585)) {
            mViewModel.enrich(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(1586)) {
            savedInstanceState.putString("mPreviousImagePath", mPreviousImagePath);
        }
        if (!ListenerUtil.mutListener.listen(1587)) {
            savedInstanceState.putParcelable("mPreviousImageUri", mPreviousImageUri);
        }
        return savedInstanceState;
    }

    @Override
    public void createUI(Context context, LinearLayout layout) {
        if (!ListenerUtil.mutListener.listen(1588)) {
            Timber.d("createUI()");
        }
        if (!ListenerUtil.mutListener.listen(1589)) {
            mViewModel = mViewModel.replaceNullValues(mField, mActivity);
        }
        if (!ListenerUtil.mutListener.listen(1590)) {
            mImagePreview = new ImageView(mActivity);
        }
        File externalCacheDirRoot = context.getExternalCacheDir();
        if (!ListenerUtil.mutListener.listen(1593)) {
            if (externalCacheDirRoot == null) {
                if (!ListenerUtil.mutListener.listen(1591)) {
                    Timber.e("createUI() unable to get external cache directory");
                }
                if (!ListenerUtil.mutListener.listen(1592)) {
                    showSomethingWentWrong();
                }
                return;
            }
        }
        File externalCacheDir = new File(externalCacheDirRoot.getAbsolutePath() + "/temp-photos");
        if (!ListenerUtil.mutListener.listen(1597)) {
            if ((ListenerUtil.mutListener.listen(1594) ? (!externalCacheDir.exists() || !externalCacheDir.mkdir()) : (!externalCacheDir.exists() && !externalCacheDir.mkdir()))) {
                if (!ListenerUtil.mutListener.listen(1595)) {
                    Timber.e("createUI() externalCacheDir did not exist and could not be created");
                }
                if (!ListenerUtil.mutListener.listen(1596)) {
                    showSomethingWentWrong();
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(1598)) {
            mAnkiCacheDirectory = externalCacheDir.getAbsolutePath();
        }
        LinearLayout.LayoutParams p = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, android.view.ViewGroup.LayoutParams.WRAP_CONTENT);
        if (!ListenerUtil.mutListener.listen(1599)) {
            drawUIComponents(context);
        }
        if (!ListenerUtil.mutListener.listen(1600)) {
            mCropButton = new Button(mActivity);
        }
        if (!ListenerUtil.mutListener.listen(1601)) {
            mCropButton.setText(gtxt(R.string.crop_button));
        }
        if (!ListenerUtil.mutListener.listen(1602)) {
            mCropButton.setOnClickListener(v -> mViewModel = requestCrop(mViewModel));
        }
        if (!ListenerUtil.mutListener.listen(1603)) {
            mCropButton.setVisibility(View.INVISIBLE);
        }
        Button mBtnGallery = new Button(mActivity);
        if (!ListenerUtil.mutListener.listen(1604)) {
            mBtnGallery.setText(gtxt(R.string.multimedia_editor_image_field_editing_galery));
        }
        if (!ListenerUtil.mutListener.listen(1605)) {
            mBtnGallery.setOnClickListener(v -> {
                Intent i = new Intent(Intent.ACTION_PICK);
                i.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                mActivity.startActivityForResultWithoutAnimation(i, ACTIVITY_SELECT_IMAGE);
            });
        }
        Button mBtnCamera = new Button(mActivity);
        if (!ListenerUtil.mutListener.listen(1606)) {
            mBtnCamera.setText(gtxt(R.string.multimedia_editor_image_field_editing_photo));
        }
        if (!ListenerUtil.mutListener.listen(1607)) {
            mBtnCamera.setOnClickListener(v -> mViewModel = captureImage(context));
        }
        if (!ListenerUtil.mutListener.listen(1609)) {
            if (!canUseCamera(context)) {
                if (!ListenerUtil.mutListener.listen(1608)) {
                    mBtnCamera.setVisibility(View.INVISIBLE);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(1610)) {
            setPreviewImage(mViewModel.mImagePath, getMaxImageSize());
        }
        if (!ListenerUtil.mutListener.listen(1611)) {
            layout.addView(mImagePreview, ViewGroup.LayoutParams.MATCH_PARENT, p);
        }
        if (!ListenerUtil.mutListener.listen(1612)) {
            layout.addView(mImageFileSize, ViewGroup.LayoutParams.MATCH_PARENT);
        }
        if (!ListenerUtil.mutListener.listen(1613)) {
            layout.addView(mImageFileSizeWarning, ViewGroup.LayoutParams.MATCH_PARENT);
        }
        if (!ListenerUtil.mutListener.listen(1614)) {
            layout.addView(mBtnGallery, ViewGroup.LayoutParams.MATCH_PARENT);
        }
        if (!ListenerUtil.mutListener.listen(1615)) {
            layout.addView(mBtnCamera, ViewGroup.LayoutParams.MATCH_PARENT);
        }
        if (!ListenerUtil.mutListener.listen(1616)) {
            layout.addView(mCropButton, ViewGroup.LayoutParams.MATCH_PARENT);
        }
    }

    @SuppressLint("UnsupportedChromeOsCameraSystemFeature")
    private boolean canUseCamera(Context context) {
        if (!ListenerUtil.mutListener.listen(1617)) {
            if (!Permissions.canUseCamera(context)) {
                return false;
            }
        }
        PackageManager pm = context.getPackageManager();
        if (!ListenerUtil.mutListener.listen(1619)) {
            if (((ListenerUtil.mutListener.listen(1618) ? (!pm.hasSystemFeature(PackageManager.FEATURE_CAMERA) || !pm.hasSystemFeature(PackageManager.FEATURE_CAMERA_FRONT)) : (!pm.hasSystemFeature(PackageManager.FEATURE_CAMERA) && !pm.hasSystemFeature(PackageManager.FEATURE_CAMERA_FRONT))))) {
                return false;
            }
        }
        // Some hardware has no camera or reports yes but has zero (e.g., cheap devices, and Chromebook emulator)
        CameraManager cameraManager = (CameraManager) AnkiDroidApp.getInstance().getApplicationContext().getSystemService(Context.CAMERA_SERVICE);
        try {
            if (!ListenerUtil.mutListener.listen(1626)) {
                if (cameraManager != null) {
                    return (ListenerUtil.mutListener.listen(1625) ? (cameraManager.getCameraIdList().length >= 0) : (ListenerUtil.mutListener.listen(1624) ? (cameraManager.getCameraIdList().length <= 0) : (ListenerUtil.mutListener.listen(1623) ? (cameraManager.getCameraIdList().length < 0) : (ListenerUtil.mutListener.listen(1622) ? (cameraManager.getCameraIdList().length != 0) : (ListenerUtil.mutListener.listen(1621) ? (cameraManager.getCameraIdList().length == 0) : (cameraManager.getCameraIdList().length > 0))))));
                }
            }
        } catch (CameraAccessException e) {
            if (!ListenerUtil.mutListener.listen(1620)) {
                Timber.e(e, "Unable to enumerate cameras");
            }
        }
        return false;
    }

    private ImageViewModel captureImage(Context context) {
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        File image;
        ImageViewModel toReturn = mViewModel;
        try {
            if (!ListenerUtil.mutListener.listen(1628)) {
                saveImageForRevert();
            }
            // Create a new image for the camera result to land in, clear the URI
            image = createNewCacheImageFile();
            Uri imageUri = getUriForFile(image);
            if (!ListenerUtil.mutListener.listen(1629)) {
                toReturn = new ImageViewModel(image.getPath(), imageUri);
            }
            if (!ListenerUtil.mutListener.listen(1630)) {
                cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
            }
            if (!ListenerUtil.mutListener.listen(1638)) {
                // https://medium.com/@quiro91/sharing-files-through-intents-part-2-fixing-the-permissions-before-lollipop-ceb9bb0eec3a
                if ((ListenerUtil.mutListener.listen(1635) ? (CompatHelper.getSdkVersion() >= Build.VERSION_CODES.LOLLIPOP_MR1) : (ListenerUtil.mutListener.listen(1634) ? (CompatHelper.getSdkVersion() <= Build.VERSION_CODES.LOLLIPOP_MR1) : (ListenerUtil.mutListener.listen(1633) ? (CompatHelper.getSdkVersion() > Build.VERSION_CODES.LOLLIPOP_MR1) : (ListenerUtil.mutListener.listen(1632) ? (CompatHelper.getSdkVersion() != Build.VERSION_CODES.LOLLIPOP_MR1) : (ListenerUtil.mutListener.listen(1631) ? (CompatHelper.getSdkVersion() == Build.VERSION_CODES.LOLLIPOP_MR1) : (CompatHelper.getSdkVersion() < Build.VERSION_CODES.LOLLIPOP_MR1))))))) {
                    if (!ListenerUtil.mutListener.listen(1636)) {
                        cameraIntent.setClipData(ClipData.newRawUri("", imageUri));
                    }
                    if (!ListenerUtil.mutListener.listen(1637)) {
                        cameraIntent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION | Intent.FLAG_GRANT_READ_URI_PERMISSION);
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(1642)) {
                if (cameraIntent.resolveActivity(context.getPackageManager()) == null) {
                    if (!ListenerUtil.mutListener.listen(1639)) {
                        Timber.w("Device has a camera, but no app to handle ACTION_IMAGE_CAPTURE Intent");
                    }
                    if (!ListenerUtil.mutListener.listen(1640)) {
                        showSomethingWentWrong();
                    }
                    if (!ListenerUtil.mutListener.listen(1641)) {
                        onActivityResult(ACTIVITY_TAKE_PICTURE, Activity.RESULT_CANCELED, null);
                    }
                    return toReturn;
                }
            }
            try {
                if (!ListenerUtil.mutListener.listen(1646)) {
                    mActivity.startActivityForResultWithoutAnimation(cameraIntent, ACTIVITY_TAKE_PICTURE);
                }
            } catch (Exception e) {
                if (!ListenerUtil.mutListener.listen(1643)) {
                    Timber.w(e, "Unable to take picture");
                }
                if (!ListenerUtil.mutListener.listen(1644)) {
                    showSomethingWentWrong();
                }
                if (!ListenerUtil.mutListener.listen(1645)) {
                    onActivityResult(ACTIVITY_TAKE_PICTURE, Activity.RESULT_CANCELED, null);
                }
            }
        } catch (IOException e) {
            if (!ListenerUtil.mutListener.listen(1627)) {
                Timber.w(e, "mBtnCamera::onClickListener() unable to prepare file and launch camera");
            }
        }
        return toReturn;
    }

    private void saveImageForRevert() {
        if (!ListenerUtil.mutListener.listen(1650)) {
            if (!mViewModel.isPreExistingImage) {
                if (!ListenerUtil.mutListener.listen(1647)) {
                    deletePreviousImage();
                }
                if (!ListenerUtil.mutListener.listen(1648)) {
                    mPreviousImagePath = mViewModel.mImagePath;
                }
                if (!ListenerUtil.mutListener.listen(1649)) {
                    mPreviousImageUri = mViewModel.mImageUri;
                }
            }
        }
    }

    private void deletePreviousImage() {
        if (!ListenerUtil.mutListener.listen(1653)) {
            // Store the old image path for deletion / error handling if the user cancels
            if ((ListenerUtil.mutListener.listen(1651) ? (mPreviousImagePath != null || !(new File(mPreviousImagePath).delete())) : (mPreviousImagePath != null && !(new File(mPreviousImagePath).delete())))) {
                if (!ListenerUtil.mutListener.listen(1652)) {
                    Timber.i("deletePreviousImage() unable to delete previous image file");
                }
            }
        }
    }

    private File createNewCacheImageFile() throws IOException {
        return createNewCacheImageFile("jpg");
    }

    private File createNewCacheImageFile(@NonNull String extension) throws IOException {
        File storageDir = new File(mAnkiCacheDirectory);
        return File.createTempFile("img", "." + extension, storageDir);
    }

    private void drawUIComponents(Context context) {
        DisplayMetrics metrics = getDisplayMetrics();
        int height = metrics.heightPixels;
        int width = metrics.widthPixels;
        if (!ListenerUtil.mutListener.listen(1654)) {
            mImagePreview = new ImageView(mActivity);
        }
        if (!ListenerUtil.mutListener.listen(1655)) {
            mImagePreview.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        }
        if (!ListenerUtil.mutListener.listen(1656)) {
            mImagePreview.setAdjustViewBounds(true);
        }
        if (!ListenerUtil.mutListener.listen(1661)) {
            mImagePreview.setMaxHeight((int) Math.round((ListenerUtil.mutListener.listen(1660) ? (height % 0.4) : (ListenerUtil.mutListener.listen(1659) ? (height / 0.4) : (ListenerUtil.mutListener.listen(1658) ? (height - 0.4) : (ListenerUtil.mutListener.listen(1657) ? (height + 0.4) : (height * 0.4)))))));
        }
        if (!ListenerUtil.mutListener.listen(1666)) {
            mImagePreview.setMaxWidth((int) Math.round((ListenerUtil.mutListener.listen(1665) ? (width % 0.6) : (ListenerUtil.mutListener.listen(1664) ? (width / 0.6) : (ListenerUtil.mutListener.listen(1663) ? (width - 0.6) : (ListenerUtil.mutListener.listen(1662) ? (width + 0.6) : (width * 0.6)))))));
        }
        if (!ListenerUtil.mutListener.listen(1667)) {
            mImageFileSize = new FixedEditText(context);
        }
        if (!ListenerUtil.mutListener.listen(1672)) {
            mImageFileSize.setMaxWidth((int) Math.round((ListenerUtil.mutListener.listen(1671) ? (width % 0.6) : (ListenerUtil.mutListener.listen(1670) ? (width / 0.6) : (ListenerUtil.mutListener.listen(1669) ? (width - 0.6) : (ListenerUtil.mutListener.listen(1668) ? (width + 0.6) : (width * 0.6)))))));
        }
        if (!ListenerUtil.mutListener.listen(1673)) {
            mImageFileSize.setEnabled(false);
        }
        if (!ListenerUtil.mutListener.listen(1674)) {
            mImageFileSize.setGravity(Gravity.CENTER_HORIZONTAL);
        }
        if (!ListenerUtil.mutListener.listen(1675)) {
            mImageFileSize.setBackground(null);
        }
        if (!ListenerUtil.mutListener.listen(1676)) {
            mImageFileSize.setVisibility(View.GONE);
        }
        if (!ListenerUtil.mutListener.listen(1677)) {
            // there's an action that they can take.
            mImageFileSizeWarning = new FixedEditText(context);
        }
        if (!ListenerUtil.mutListener.listen(1682)) {
            mImageFileSizeWarning.setMaxWidth((int) Math.round((ListenerUtil.mutListener.listen(1681) ? (width % 0.6) : (ListenerUtil.mutListener.listen(1680) ? (width / 0.6) : (ListenerUtil.mutListener.listen(1679) ? (width - 0.6) : (ListenerUtil.mutListener.listen(1678) ? (width + 0.6) : (width * 0.6)))))));
        }
        if (!ListenerUtil.mutListener.listen(1683)) {
            mImageFileSizeWarning.setEnabled(false);
        }
        if (!ListenerUtil.mutListener.listen(1684)) {
            // Orange-Red
            mImageFileSizeWarning.setTextColor(Color.parseColor("#FF4500"));
        }
        if (!ListenerUtil.mutListener.listen(1685)) {
            mImageFileSizeWarning.setGravity(Gravity.CENTER_HORIZONTAL);
        }
        if (!ListenerUtil.mutListener.listen(1686)) {
            mImageFileSizeWarning.setVisibility(View.GONE);
        }
        if (!ListenerUtil.mutListener.listen(1687)) {
            mImageFileSize.setBackground(null);
        }
        if (!ListenerUtil.mutListener.listen(1688)) {
            mImageFileSizeWarning.setText(R.string.multimedia_editor_image_compression_failed);
        }
    }

    private String gtxt(int id) {
        return mActivity.getText(id).toString();
    }

    private DisplayMetrics getDisplayMetrics() {
        if (!ListenerUtil.mutListener.listen(1691)) {
            if (mMetrics == null) {
                if (!ListenerUtil.mutListener.listen(1689)) {
                    mMetrics = new DisplayMetrics();
                }
                if (!ListenerUtil.mutListener.listen(1690)) {
                    mActivity.getWindowManager().getDefaultDisplay().getMetrics(mMetrics);
                }
            }
        }
        return mMetrics;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (!ListenerUtil.mutListener.listen(1692)) {
            Timber.d("onActivityResult()");
        }
        if (!ListenerUtil.mutListener.listen(1704)) {
            if (resultCode != Activity.RESULT_OK) {
                if (!ListenerUtil.mutListener.listen(1693)) {
                    Timber.d("Activity was not successful");
                }
                if (!ListenerUtil.mutListener.listen(1696)) {
                    // Restore the old version of the image if the user cancelled
                    switch(requestCode) {
                        case ACTIVITY_TAKE_PICTURE:
                        case ACTIVITY_CROP_PICTURE:
                            if (!ListenerUtil.mutListener.listen(1695)) {
                                if (!TextUtils.isEmpty(mPreviousImagePath)) {
                                    if (!ListenerUtil.mutListener.listen(1694)) {
                                        revertToPreviousImage();
                                    }
                                }
                            }
                            break;
                        default:
                            break;
                    }
                }
                if (!ListenerUtil.mutListener.listen(1703)) {
                    // Some apps send this back with app-specific data, direct the user to another app
                    if ((ListenerUtil.mutListener.listen(1701) ? (resultCode <= Activity.RESULT_FIRST_USER) : (ListenerUtil.mutListener.listen(1700) ? (resultCode > Activity.RESULT_FIRST_USER) : (ListenerUtil.mutListener.listen(1699) ? (resultCode < Activity.RESULT_FIRST_USER) : (ListenerUtil.mutListener.listen(1698) ? (resultCode != Activity.RESULT_FIRST_USER) : (ListenerUtil.mutListener.listen(1697) ? (resultCode == Activity.RESULT_FIRST_USER) : (resultCode >= Activity.RESULT_FIRST_USER))))))) {
                        if (!ListenerUtil.mutListener.listen(1702)) {
                            UIUtils.showThemedToast(mActivity, mActivity.getString(R.string.activity_result_unexpected), true);
                        }
                    }
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(1705)) {
            mImageFileSizeWarning.setVisibility(View.GONE);
        }
        if (!ListenerUtil.mutListener.listen(1729)) {
            if ((ListenerUtil.mutListener.listen(1710) ? (requestCode >= ACTIVITY_SELECT_IMAGE) : (ListenerUtil.mutListener.listen(1709) ? (requestCode <= ACTIVITY_SELECT_IMAGE) : (ListenerUtil.mutListener.listen(1708) ? (requestCode > ACTIVITY_SELECT_IMAGE) : (ListenerUtil.mutListener.listen(1707) ? (requestCode < ACTIVITY_SELECT_IMAGE) : (ListenerUtil.mutListener.listen(1706) ? (requestCode != ACTIVITY_SELECT_IMAGE) : (requestCode == ACTIVITY_SELECT_IMAGE))))))) {
                try {
                    if (!ListenerUtil.mutListener.listen(1727)) {
                        handleSelectImageIntent(data);
                    }
                    if (!ListenerUtil.mutListener.listen(1728)) {
                        mImageFileSizeWarning.setVisibility(View.GONE);
                    }
                } catch (Exception e) {
                    if (!ListenerUtil.mutListener.listen(1724)) {
                        AnkiDroidApp.sendExceptionReport(e, "BasicImageFieldController - handleSelectImageIntent");
                    }
                    if (!ListenerUtil.mutListener.listen(1725)) {
                        Timber.e(e, "Failed to select image");
                    }
                    if (!ListenerUtil.mutListener.listen(1726)) {
                        showSomethingWentWrong();
                    }
                    return;
                }
            } else if ((ListenerUtil.mutListener.listen(1715) ? (requestCode >= ACTIVITY_TAKE_PICTURE) : (ListenerUtil.mutListener.listen(1714) ? (requestCode <= ACTIVITY_TAKE_PICTURE) : (ListenerUtil.mutListener.listen(1713) ? (requestCode > ACTIVITY_TAKE_PICTURE) : (ListenerUtil.mutListener.listen(1712) ? (requestCode < ACTIVITY_TAKE_PICTURE) : (ListenerUtil.mutListener.listen(1711) ? (requestCode != ACTIVITY_TAKE_PICTURE) : (requestCode == ACTIVITY_TAKE_PICTURE))))))) {
                if (!ListenerUtil.mutListener.listen(1723)) {
                    handleTakePictureResult();
                }
            } else if ((ListenerUtil.mutListener.listen(1720) ? (requestCode >= ACTIVITY_CROP_PICTURE) : (ListenerUtil.mutListener.listen(1719) ? (requestCode <= ACTIVITY_CROP_PICTURE) : (ListenerUtil.mutListener.listen(1718) ? (requestCode > ACTIVITY_CROP_PICTURE) : (ListenerUtil.mutListener.listen(1717) ? (requestCode < ACTIVITY_CROP_PICTURE) : (ListenerUtil.mutListener.listen(1716) ? (requestCode != ACTIVITY_CROP_PICTURE) : (requestCode == ACTIVITY_CROP_PICTURE))))))) {
                if (!ListenerUtil.mutListener.listen(1722)) {
                    handleCropResult();
                }
            } else {
                if (!ListenerUtil.mutListener.listen(1721)) {
                    Timber.w("Unhandled request code: %d", requestCode);
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(1730)) {
            setPreviewImage(mViewModel.mImagePath, getMaxImageSize());
        }
    }

    private void revertToPreviousImage() {
        if (!ListenerUtil.mutListener.listen(1731)) {
            mViewModel.deleteImagePath();
        }
        if (!ListenerUtil.mutListener.listen(1732)) {
            mViewModel = new ImageViewModel(mPreviousImagePath, mPreviousImageUri);
        }
        if (!ListenerUtil.mutListener.listen(1733)) {
            mField.setImagePath(mPreviousImagePath);
        }
        if (!ListenerUtil.mutListener.listen(1734)) {
            mPreviousImagePath = null;
        }
        if (!ListenerUtil.mutListener.listen(1735)) {
            mPreviousImageUri = null;
        }
    }

    private void showSomethingWentWrong() {
        if (!ListenerUtil.mutListener.listen(1736)) {
            UIUtils.showThemedToast(mActivity, mActivity.getResources().getString(R.string.multimedia_editor_something_wrong), false);
        }
    }

    private void handleSelectImageIntent(Intent data) {
        if (!ListenerUtil.mutListener.listen(1739)) {
            if (data == null) {
                if (!ListenerUtil.mutListener.listen(1737)) {
                    Timber.e("handleSelectImageIntent() no intent provided");
                }
                if (!ListenerUtil.mutListener.listen(1738)) {
                    showSomethingWentWrong();
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(1740)) {
            Timber.i("handleSelectImageIntent() Intent: %s. extras: %s", data, data.getExtras() == null ? "null" : TextUtils.join(", ", data.getExtras().keySet()));
        }
        Uri selectedImage = getImageUri(mActivity, data);
        if (!ListenerUtil.mutListener.listen(1743)) {
            if (selectedImage == null) {
                if (!ListenerUtil.mutListener.listen(1741)) {
                    Timber.w("handleSelectImageIntent() selectedImage was null");
                }
                if (!ListenerUtil.mutListener.listen(1742)) {
                    showSomethingWentWrong();
                }
                return;
            }
        }
        File internalizedPick = internalizeUri(selectedImage);
        if (!ListenerUtil.mutListener.listen(1749)) {
            if (internalizedPick == null) {
                String urlImagePath = getImageInfoFromUri(mActivity, selectedImage).first;
                if (!ListenerUtil.mutListener.listen(1744)) {
                    mViewModel = new ImageViewModel(urlImagePath, selectedImage);
                }
                if (!ListenerUtil.mutListener.listen(1745)) {
                    mField.setImagePath(urlImagePath);
                }
                if (!ListenerUtil.mutListener.listen(1746)) {
                    mField.setHasTemporaryMedia(false);
                }
                if (!ListenerUtil.mutListener.listen(1747)) {
                    Timber.w("handleSelectImageIntent() unable to internalize image from Uri %s", selectedImage);
                }
                if (!ListenerUtil.mutListener.listen(1748)) {
                    showSomethingWentWrong();
                }
                return;
            }
        }
        String imagePath = internalizedPick.getAbsolutePath();
        if (!ListenerUtil.mutListener.listen(1750)) {
            mViewModel = new ImageViewModel(imagePath, getUriForFile(internalizedPick));
        }
        if (!ListenerUtil.mutListener.listen(1751)) {
            setTemporaryMedia(imagePath);
        }
        if (!ListenerUtil.mutListener.listen(1752)) {
            Timber.i("handleSelectImageIntent() Decoded image: '%s'", imagePath);
        }
    }

    @Nullable
    private File internalizeUri(Uri uri) {
        File internalFile;
        Pair<String, String> uriFileInfo = getImageInfoFromUri(mActivity, uri);
        String filePath = uriFileInfo.first;
        String displayName = uriFileInfo.second;
        // Use the display name from the image info to create a new file with correct extension
        if (uriFileInfo.second == null) {
            if (!ListenerUtil.mutListener.listen(1753)) {
                Timber.w("internalizeUri() unable to get file name");
            }
            if (!ListenerUtil.mutListener.listen(1754)) {
                showSomethingWentWrong();
            }
            return null;
        }
        String uriFileExtension = uriFileInfo.second.substring(uriFileInfo.second.lastIndexOf('.') + 1);
        try {
            internalFile = createNewCacheImageFile(uriFileExtension);
        } catch (IOException e) {
            if (!ListenerUtil.mutListener.listen(1755)) {
                Timber.w(e, "internalizeUri() failed to create new file with extension %s", uriFileExtension);
            }
            if (!ListenerUtil.mutListener.listen(1756)) {
                showSomethingWentWrong();
            }
            return null;
        }
        try {
            File returnFile = FileUtil.internalizeUri(uri, filePath, internalFile, mActivity.getContentResolver());
            if (!ListenerUtil.mutListener.listen(1758)) {
                Timber.d("internalizeUri successful. Returning internalFile.");
            }
            return returnFile;
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(1757)) {
                showSomethingWentWrong();
            }
            return null;
        }
    }

    @Override
    public void onFocusLost() {
    }

    @Override
    public void onDone() {
        if (!ListenerUtil.mutListener.listen(1759)) {
            deletePreviousImage();
        }
    }

    /**
     * Rotate and compress the image, with the side effect of current image being backed by a new file
     *
     * @return true if successful, false indicates the current image is likely not usable, revert if possible
     */
    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    private boolean rotateAndCompress(String imagePath, ImageViewModel imageViewModel) {
        if (!ListenerUtil.mutListener.listen(1760)) {
            Timber.d("rotateAndCompress() on %s", imagePath);
        }
        // Set the rotation of the camera image and save as png
        File f = new File(imagePath);
        if (!ListenerUtil.mutListener.listen(1761)) {
            Timber.d("rotateAndCompress in path %s has size %d", f.getAbsolutePath(), f.length());
        }
        // Load into a bitmap with max size of 1920 pixels and rotate if necessary
        Bitmap b = BitmapUtil.decodeFile(f, IMAGE_SAVE_MAX_WIDTH);
        if (!ListenerUtil.mutListener.listen(1763)) {
            if (b == null) {
                if (!ListenerUtil.mutListener.listen(1762)) {
                    // And display a warning to push users to compress manually.
                    Timber.w("rotateAndCompress() unable to decode file %s", imagePath);
                }
                return false;
            }
        }
        FileOutputStream out = null;
        try {
            File outFile = createNewCacheImageFile();
            if (!ListenerUtil.mutListener.listen(1769)) {
                out = new FileOutputStream(outFile);
            }
            if (!ListenerUtil.mutListener.listen(1770)) {
                b = ExifUtil.rotateFromCamera(f, b);
            }
            if (!ListenerUtil.mutListener.listen(1771)) {
                b.compress(Bitmap.CompressFormat.JPEG, 90, out);
            }
            if (!ListenerUtil.mutListener.listen(1773)) {
                if (!f.delete()) {
                    if (!ListenerUtil.mutListener.listen(1772)) {
                        Timber.w("rotateAndCompress() delete of pre-compressed image failed %s", imagePath);
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(1774)) {
                imagePath = outFile.getAbsolutePath();
            }
            if (!ListenerUtil.mutListener.listen(1775)) {
                mViewModel = imageViewModel.rotateAndCompressTo(imagePath, getUriForFile(outFile));
            }
            if (!ListenerUtil.mutListener.listen(1776)) {
                mField.setImagePath(imagePath);
            }
            if (!ListenerUtil.mutListener.listen(1777)) {
                Timber.d("rotateAndCompress out path %s has size %d", imagePath, outFile.length());
            }
        } catch (FileNotFoundException e) {
            if (!ListenerUtil.mutListener.listen(1764)) {
                Timber.w(e, "rotateAndCompress() File not found for image compression %s", imagePath);
            }
        } catch (IOException e) {
            if (!ListenerUtil.mutListener.listen(1765)) {
                Timber.w(e, "rotateAndCompress() create file failed for file %s", imagePath);
            }
        } finally {
            try {
                if (!ListenerUtil.mutListener.listen(1768)) {
                    if (out != null) {
                        if (!ListenerUtil.mutListener.listen(1767)) {
                            out.close();
                        }
                    }
                }
            } catch (IOException e) {
                if (!ListenerUtil.mutListener.listen(1766)) {
                    Timber.w(e, "rotateAndCompress() Unable to clean up image compression output stream");
                }
            }
        }
        return true;
    }

    private void setPreviewImage(@Nullable String imagePath, int maxsize) {
        if (!ListenerUtil.mutListener.listen(1780)) {
            if ((ListenerUtil.mutListener.listen(1778) ? (imagePath != null || !"".equals(imagePath)) : (imagePath != null && !"".equals(imagePath)))) {
                File f = new File(imagePath);
                if (!ListenerUtil.mutListener.listen(1779)) {
                    setImagePreview(f, maxsize);
                }
            }
        }
    }

    @VisibleForTesting
    void setImagePreview(File f, int maxsize) {
        Bitmap b = BitmapUtil.decodeFile(f, maxsize);
        if (!ListenerUtil.mutListener.listen(1782)) {
            if (b == null) {
                if (!ListenerUtil.mutListener.listen(1781)) {
                    Timber.i("setImagePreview() could not process image %s", f.getPath());
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(1783)) {
            Timber.d("setPreviewImage path %s has size %d", f.getAbsolutePath(), f.length());
        }
        if (!ListenerUtil.mutListener.listen(1784)) {
            b = ExifUtil.rotateFromCamera(f, b);
        }
        if (!ListenerUtil.mutListener.listen(1785)) {
            onValidImage(b, Formatter.formatFileSize(mActivity, f.length()));
        }
    }

    private void onValidImage(Bitmap b, String fileSize) {
        if (!ListenerUtil.mutListener.listen(1786)) {
            mImagePreview.setImageBitmap(b);
        }
        if (!ListenerUtil.mutListener.listen(1787)) {
            mImageFileSize.setVisibility(View.VISIBLE);
        }
        if (!ListenerUtil.mutListener.listen(1788)) {
            mImageFileSize.setText(fileSize);
        }
        if (!ListenerUtil.mutListener.listen(1789)) {
            mCropButton.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onDestroy() {
        ImageView imageView = mImagePreview;
        if (!ListenerUtil.mutListener.listen(1790)) {
            BitmapUtil.freeImageView(imageView);
        }
        if (!ListenerUtil.mutListener.listen(1791)) {
            mCropButton.setVisibility(View.INVISIBLE);
        }
    }

    private void handleTakePictureResult() {
        if (!ListenerUtil.mutListener.listen(1792)) {
            Timber.d("handleTakePictureResult");
        }
        if (!ListenerUtil.mutListener.listen(1794)) {
            if (!rotateAndCompress()) {
                if (!ListenerUtil.mutListener.listen(1793)) {
                    Timber.i("handleTakePictureResult appears to have an invalid picture");
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(1795)) {
            showCropDialog(mActivity.getString(R.string.crop_image), null);
        }
    }

    /**
     * Invoke system crop function
     */
    private ImageViewModel requestCrop(ImageViewModel viewModel) {
        ImageViewModel ret = viewModel;
        if (!ListenerUtil.mutListener.listen(1797)) {
            if (!ret.isValid()) {
                if (!ListenerUtil.mutListener.listen(1796)) {
                    Timber.w("requestCrop() but mImagePath or mImageUri is null");
                }
                return ret;
            }
        }
        if (!ListenerUtil.mutListener.listen(1798)) {
            Timber.d("photoCrop() with path/uri %s/%s", ret.mImagePath, ret.mImageUri);
        }
        // Pre-create a file in our cache for the cropping application to put results in
        File image;
        try {
            image = createNewCacheImageFile();
        } catch (IOException e) {
            if (!ListenerUtil.mutListener.listen(1799)) {
                Timber.w(e, "requestCrop() unable to create new file to drop crop results into");
            }
            if (!ListenerUtil.mutListener.listen(1800)) {
                showSomethingWentWrong();
            }
            return ret;
        }
        if (!ListenerUtil.mutListener.listen(1801)) {
            saveImageForRevert();
        }
        // This must be the file URL it will not work with a content URI
        String imagePath = image.getPath();
        Uri imageUri = Uri.fromFile(image);
        if (!ListenerUtil.mutListener.listen(1802)) {
            ret = viewModel.beforeCrop(imagePath, imageUri);
        }
        if (!ListenerUtil.mutListener.listen(1803)) {
            setTemporaryMedia(imagePath);
        }
        if (!ListenerUtil.mutListener.listen(1804)) {
            Timber.d("requestCrop()  destination image has path/uri %s/%s", ret.mImagePath, ret.mImageUri);
        }
        // Intent intent = new Intent(Intent.ACTION_EDIT);  // edit (vs crop) would be even better, but it fails differently and needs lots of testing
        Intent intent = new Intent("com.android.camera.action.CROP");
        if (!ListenerUtil.mutListener.listen(1805)) {
            intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        }
        if (!ListenerUtil.mutListener.listen(1806)) {
            intent.setDataAndType(mPreviousImageUri, "image/*");
        }
        if (!ListenerUtil.mutListener.listen(1807)) {
            intent.putExtra("return-data", false);
        }
        if (!ListenerUtil.mutListener.listen(1808)) {
            intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        }
        if (!ListenerUtil.mutListener.listen(1809)) {
            // worked w/crop but not edit
            intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        }
        if (!ListenerUtil.mutListener.listen(1810)) {
            // no face detection
            intent.putExtra("noFaceDetection", true);
        }
        try {
            if (!ListenerUtil.mutListener.listen(1814)) {
                mActivity.startActivityForResultWithoutAnimation(Intent.createChooser(intent, null), ACTIVITY_CROP_PICTURE);
            }
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(1811)) {
                Timber.w(e, "requestCrop unable to start cropping activity for Uri %s", mPreviousImageUri);
            }
            if (!ListenerUtil.mutListener.listen(1812)) {
                showSomethingWentWrong();
            }
            if (!ListenerUtil.mutListener.listen(1813)) {
                onActivityResult(ACTIVITY_CROP_PICTURE, Activity.RESULT_CANCELED, null);
            }
        }
        return ret;
    }

    private void setTemporaryMedia(String imagePath) {
        if (!ListenerUtil.mutListener.listen(1815)) {
            mField.setImagePath(imagePath);
        }
        if (!ListenerUtil.mutListener.listen(1816)) {
            mField.setHasTemporaryMedia(true);
        }
    }

    public void showCropDialog(String content, @Nullable MaterialDialog.SingleButtonCallback negativeCallBack) {
        if (!ListenerUtil.mutListener.listen(1818)) {
            if (!mViewModel.isValid()) {
                if (!ListenerUtil.mutListener.listen(1817)) {
                    Timber.w("showCropDialog called with null URI or Path");
                }
                return;
            }
        }
        MaterialDialog.Builder builder = new MaterialDialog.Builder(mActivity).content(content).positiveText(R.string.dialog_ok).negativeText(R.string.dialog_no).onPositive((dialog, which) -> mViewModel = requestCrop(mViewModel));
        if (!ListenerUtil.mutListener.listen(1820)) {
            if (negativeCallBack != null) {
                if (!ListenerUtil.mutListener.listen(1819)) {
                    builder.onNegative(negativeCallBack);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(1821)) {
            builder.build().show();
        }
    }

    private void handleCropResult() {
        if (!ListenerUtil.mutListener.listen(1822)) {
            Timber.d("handleCropResult");
        }
        if (!ListenerUtil.mutListener.listen(1824)) {
            if (!rotateAndCompress()) {
                if (!ListenerUtil.mutListener.listen(1823)) {
                    Timber.i("handleCropResult() appears to have an invalid file, reverting");
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(1825)) {
            Timber.d("handleCropResult() = image path now %s", mField.getImagePath());
        }
    }

    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    private boolean rotateAndCompress() {
        if (!ListenerUtil.mutListener.listen(1829)) {
            if (!rotateAndCompress(mViewModel.mImagePath, mViewModel)) {
                if (!ListenerUtil.mutListener.listen(1826)) {
                    mImageFileSizeWarning.setVisibility(View.VISIBLE);
                }
                if (!ListenerUtil.mutListener.listen(1827)) {
                    revertToPreviousImage();
                }
                if (!ListenerUtil.mutListener.listen(1828)) {
                    showSomethingWentWrong();
                }
                return false;
            }
        }
        if (!ListenerUtil.mutListener.listen(1830)) {
            mField.setHasTemporaryMedia(true);
        }
        return true;
    }

    private Uri getUriForFile(File file) {
        return getUriForFile(file, mActivity);
    }

    /**
     * Get Uri based on current image path
     *
     * @param file the file to get URI for
     * @return current image path's uri
     */
    private static Uri getUriForFile(File file, Context mActivity) {
        if (!ListenerUtil.mutListener.listen(1831)) {
            Timber.d("getUriForFile() %s", file);
        }
        try {
            if (!ListenerUtil.mutListener.listen(1839)) {
                if ((ListenerUtil.mutListener.listen(1838) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.N) : (ListenerUtil.mutListener.listen(1837) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.N) : (ListenerUtil.mutListener.listen(1836) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) : (ListenerUtil.mutListener.listen(1835) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.N) : (ListenerUtil.mutListener.listen(1834) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.N) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N))))))) {
                    return FileProvider.getUriForFile(mActivity, mActivity.getApplicationContext().getPackageName() + ".apkgfileprovider", file);
                }
            }
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(1832)) {
                // #6628 - What would cause this? Is the fallback is effective? Telemetry to diagnose more:
                Timber.w(e, "getUriForFile failed on %s - attempting fallback", file);
            }
            if (!ListenerUtil.mutListener.listen(1833)) {
                AnkiDroidApp.sendExceptionReport(e, "BasicImageFieldController", "Unexpected getUriForFile failure on " + file, true);
            }
        }
        return Uri.fromFile(file);
    }

    /**
     * Get image uri that adapts various model
     *
     * @return image uri
     */
    @Nullable
    private Uri getImageUri(Context context, Intent data) {
        if (!ListenerUtil.mutListener.listen(1840)) {
            Timber.d("getImageUri for data %s", data);
        }
        Uri uri = data.getData();
        if (!ListenerUtil.mutListener.listen(1842)) {
            if (uri == null) {
                if (!ListenerUtil.mutListener.listen(1841)) {
                    UIUtils.showThemedToast(context, context.getString(R.string.select_image_failed), false);
                }
            }
        }
        return uri;
    }

    /**
     * Get image information based on uri and selection args
     *
     * @return Pair<String, String>: first file path (null if does not exist), second display name (null if does not exist)
     */
    @NonNull
    private Pair<String, String> getImageInfoFromUri(Context context, Uri uri) {
        if (!ListenerUtil.mutListener.listen(1843)) {
            Timber.d("getImagePathFromUri() URI: %s", uri);
        }
        Pair<String, String> imageInfo = new Pair<>(null, null);
        if (!ListenerUtil.mutListener.listen(1854)) {
            if (DocumentsContract.isDocumentUri(context, uri)) {
                String docId = DocumentsContract.getDocumentId(uri);
                if (!ListenerUtil.mutListener.listen(1853)) {
                    if ("com.android.providers.media.documents".equals(uri.getAuthority())) {
                        String id = docId.split(":")[1];
                        String selection = MediaStore.Images.Media._ID + "=" + id;
                        if (!ListenerUtil.mutListener.listen(1852)) {
                            imageInfo = getImageInfoFromContentResolver(context, MediaStore.Images.Media.EXTERNAL_CONTENT_URI, selection);
                        }
                    } else if ("com.android.providers.downloads.documents".equals(uri.getAuthority())) {
                        Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.parseLong(docId));
                        if (!ListenerUtil.mutListener.listen(1851)) {
                            imageInfo = getImageInfoFromContentResolver(context, contentUri, null);
                        }
                    }
                }
            } else if ("content".equalsIgnoreCase(uri.getScheme())) {
                if (!ListenerUtil.mutListener.listen(1850)) {
                    imageInfo = getImageInfoFromContentResolver(context, uri, null);
                }
            } else if ("file".equalsIgnoreCase(uri.getScheme())) {
                if (!ListenerUtil.mutListener.listen(1849)) {
                    if (uri.getPath() != null) {
                        String[] pathParts = uri.getPath().split("/");
                        if (!ListenerUtil.mutListener.listen(1848)) {
                            imageInfo = new Pair<>(uri.getPath(), pathParts[(ListenerUtil.mutListener.listen(1847) ? (pathParts.length % 1) : (ListenerUtil.mutListener.listen(1846) ? (pathParts.length / 1) : (ListenerUtil.mutListener.listen(1845) ? (pathParts.length * 1) : (ListenerUtil.mutListener.listen(1844) ? (pathParts.length + 1) : (pathParts.length - 1)))))]);
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(1855)) {
            Timber.d("getImagePathFromUri() returning path/name %s/%s", imageInfo.first, imageInfo.second);
        }
        return imageInfo;
    }

    /**
     * Get image information based on uri and selection args
     *
     * @return string[] 0: file path (null if does not exist), 1: display name (null if does not exist)
     */
    // TODO Tracked in https://github.com/ankidroid/Anki-Android/issues/7014
    @SuppressWarnings("deprecation")
    @NonNull
    private Pair<String, String> getImageInfoFromContentResolver(Context context, Uri uri, String selection) {
        if (!ListenerUtil.mutListener.listen(1856)) {
            Timber.d("getImagePathFromContentResolver() %s", uri);
        }
        String[] filePathColumns = { MediaStore.MediaColumns.DATA, MediaStore.MediaColumns.DISPLAY_NAME };
        Cursor cursor = ContentResolverCompat.query(context.getContentResolver(), uri, filePathColumns, selection, null, null, null);
        if (!ListenerUtil.mutListener.listen(1859)) {
            if (cursor == null) {
                if (!ListenerUtil.mutListener.listen(1857)) {
                    Timber.w("getImageInfoFromContentResolver() cursor was null");
                }
                if (!ListenerUtil.mutListener.listen(1858)) {
                    showSomethingWentWrong();
                }
                return new Pair<>(null, null);
            }
        }
        if (!ListenerUtil.mutListener.listen(1862)) {
            if (!cursor.moveToFirst()) {
                if (!ListenerUtil.mutListener.listen(1860)) {
                    // TODO: #5909, it would be best to instrument this to see if we can fix the failure
                    Timber.w("getImageInfoFromContentResolver() cursor had no data");
                }
                if (!ListenerUtil.mutListener.listen(1861)) {
                    showSomethingWentWrong();
                }
                return new Pair<>(null, null);
            }
        }
        Pair<String, String> imageInfo = new Pair<>(cursor.getString(cursor.getColumnIndex(filePathColumns[0])), cursor.getString(cursor.getColumnIndex(filePathColumns[1])));
        if (!ListenerUtil.mutListener.listen(1863)) {
            cursor.close();
        }
        if (!ListenerUtil.mutListener.listen(1864)) {
            Timber.d("getImageInfoFromContentResolver() decoded image info path/name %s/%s", imageInfo.first, imageInfo.second);
        }
        return imageInfo;
    }

    public boolean isShowingPreview() {
        return mImageFileSize.getVisibility() == View.VISIBLE;
    }

    private static class ImageViewModel {

        @Nullable
        public final String mImagePath;

        @Nullable
        public final Uri mImageUri;

        public boolean isPreExistingImage = false;

        private ImageViewModel(@Nullable String mImagePath, @Nullable Uri mImageUri) {
            this.mImagePath = mImagePath;
            this.mImageUri = mImageUri;
        }

        public static ImageViewModel fromBundle(Bundle savedInstanceState) {
            String mImagePath = savedInstanceState.getString("mImagePath");
            Uri mImageUri = savedInstanceState.getParcelable("mImageUri");
            return new ImageViewModel(mImagePath, mImageUri);
        }

        public void enrich(Bundle savedInstanceState) {
            if (!ListenerUtil.mutListener.listen(1865)) {
                savedInstanceState.putString("mImagePath", mImagePath);
            }
            if (!ListenerUtil.mutListener.listen(1866)) {
                savedInstanceState.putParcelable("mImageUri", mImageUri);
            }
        }

        @SuppressWarnings("BooleanMethodIsAlwaysInverted")
        public boolean isValid() {
            return (ListenerUtil.mutListener.listen(1867) ? (mImagePath != null || mImageUri != null) : (mImagePath != null && mImageUri != null));
        }

        public ImageViewModel replaceNullValues(IField mField, Context context) {
            String newImagePath = mImagePath;
            Uri newImageUri = mImageUri;
            if (!ListenerUtil.mutListener.listen(1869)) {
                if (newImagePath == null) {
                    if (!ListenerUtil.mutListener.listen(1868)) {
                        newImagePath = mField.getImagePath();
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(1872)) {
                if ((ListenerUtil.mutListener.listen(1870) ? (newImageUri == null || newImagePath != null) : (newImageUri == null && newImagePath != null))) {
                    if (!ListenerUtil.mutListener.listen(1871)) {
                        newImageUri = getUriForFile(new File(newImagePath), context);
                    }
                }
            }
            ImageViewModel ivm = new ImageViewModel(newImagePath, newImageUri);
            if (!ListenerUtil.mutListener.listen(1873)) {
                ivm.isPreExistingImage = true;
            }
            return ivm;
        }

        public void deleteImagePath() {
            if (!ListenerUtil.mutListener.listen(1877)) {
                if ((ListenerUtil.mutListener.listen(1874) ? (mImagePath != null || new File(mImagePath).exists()) : (mImagePath != null && new File(mImagePath).exists()))) {
                    if (!ListenerUtil.mutListener.listen(1876)) {
                        if (!new File(mImagePath).delete()) {
                            if (!ListenerUtil.mutListener.listen(1875)) {
                                Timber.i("revertToPreviousImage() had existing image, but delete failed");
                            }
                        }
                    }
                }
            }
        }

        public ImageViewModel beforeCrop(String imagePath, Uri imageUri) {
            return new ImageViewModel(imagePath, imageUri);
        }

        public ImageViewModel rotateAndCompressTo(String imagePath, Uri uri) {
            return new ImageViewModel(imagePath, uri);
        }
    }
}
