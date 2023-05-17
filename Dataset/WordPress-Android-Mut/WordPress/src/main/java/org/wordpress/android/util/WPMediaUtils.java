package org.wordpress.android.util;

import android.app.Activity;
import android.content.ClipData;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.ViewConfiguration;
import android.webkit.MimeTypeMap;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import org.wordpress.android.R;
import org.wordpress.android.analytics.AnalyticsTracker;
import org.wordpress.android.fluxc.model.MediaModel;
import org.wordpress.android.fluxc.model.SiteModel;
import org.wordpress.android.fluxc.store.MediaStore.MediaError;
import org.wordpress.android.fluxc.store.media.MediaErrorSubType;
import org.wordpress.android.fluxc.store.media.MediaErrorSubType.MalformedMediaArgSubType;
import org.wordpress.android.fluxc.utils.MimeTypes;
import org.wordpress.android.fluxc.utils.MimeTypes.Plan;
import org.wordpress.android.imageeditor.preview.PreviewImageFragment;
import org.wordpress.android.imageeditor.preview.PreviewImageFragment.Companion.EditImageData;
import org.wordpress.android.ui.RequestCodes;
import org.wordpress.android.ui.mediapicker.MediaPickerFragment.ChooserContext;
import org.wordpress.android.ui.mediapicker.MediaPickerFragment.MediaPickerAction.OpenSystemPicker;
import org.wordpress.android.ui.prefs.AppPrefs;
import org.wordpress.android.ui.utils.UiHelpers;
import org.wordpress.android.util.AppLog.T;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class WPMediaUtils {

    public interface LaunchCameraCallback {

        void onMediaCapturePathReady(String mediaCapturePath);
    }

    // Max picture size will be 3000px wide. That's the maximum resolution you can set in the current picker.
    public static final int OPTIMIZE_IMAGE_MAX_SIZE = 3000;

    public static final int OPTIMIZE_IMAGE_ENCODER_QUALITY = 85;

    public static final int OPTIMIZE_VIDEO_MAX_WIDTH = 1280;

    public static final int OPTIMIZE_VIDEO_ENCODER_BITRATE_KB = 3000;

    public static Uri getOptimizedMedia(Context context, String path, boolean isVideo) {
        if (!ListenerUtil.mutListener.listen(28104)) {
            if (isVideo) {
                return null;
            }
        }
        if (!ListenerUtil.mutListener.listen(28105)) {
            if (!AppPrefs.isImageOptimize()) {
                return null;
            }
        }
        int resizeDimension = (ListenerUtil.mutListener.listen(28110) ? (AppPrefs.getImageOptimizeMaxSize() >= 1) : (ListenerUtil.mutListener.listen(28109) ? (AppPrefs.getImageOptimizeMaxSize() <= 1) : (ListenerUtil.mutListener.listen(28108) ? (AppPrefs.getImageOptimizeMaxSize() < 1) : (ListenerUtil.mutListener.listen(28107) ? (AppPrefs.getImageOptimizeMaxSize() != 1) : (ListenerUtil.mutListener.listen(28106) ? (AppPrefs.getImageOptimizeMaxSize() == 1) : (AppPrefs.getImageOptimizeMaxSize() > 1)))))) ? AppPrefs.getImageOptimizeMaxSize() : Integer.MAX_VALUE;
        int quality = AppPrefs.getImageOptimizeQuality();
        if (!ListenerUtil.mutListener.listen(28122)) {
            // do not optimize if original-size and 100% quality are set.
            if ((ListenerUtil.mutListener.listen(28121) ? ((ListenerUtil.mutListener.listen(28115) ? (resizeDimension >= Integer.MAX_VALUE) : (ListenerUtil.mutListener.listen(28114) ? (resizeDimension <= Integer.MAX_VALUE) : (ListenerUtil.mutListener.listen(28113) ? (resizeDimension > Integer.MAX_VALUE) : (ListenerUtil.mutListener.listen(28112) ? (resizeDimension < Integer.MAX_VALUE) : (ListenerUtil.mutListener.listen(28111) ? (resizeDimension != Integer.MAX_VALUE) : (resizeDimension == Integer.MAX_VALUE)))))) || (ListenerUtil.mutListener.listen(28120) ? (quality >= 100) : (ListenerUtil.mutListener.listen(28119) ? (quality <= 100) : (ListenerUtil.mutListener.listen(28118) ? (quality > 100) : (ListenerUtil.mutListener.listen(28117) ? (quality < 100) : (ListenerUtil.mutListener.listen(28116) ? (quality != 100) : (quality == 100))))))) : ((ListenerUtil.mutListener.listen(28115) ? (resizeDimension >= Integer.MAX_VALUE) : (ListenerUtil.mutListener.listen(28114) ? (resizeDimension <= Integer.MAX_VALUE) : (ListenerUtil.mutListener.listen(28113) ? (resizeDimension > Integer.MAX_VALUE) : (ListenerUtil.mutListener.listen(28112) ? (resizeDimension < Integer.MAX_VALUE) : (ListenerUtil.mutListener.listen(28111) ? (resizeDimension != Integer.MAX_VALUE) : (resizeDimension == Integer.MAX_VALUE)))))) && (ListenerUtil.mutListener.listen(28120) ? (quality >= 100) : (ListenerUtil.mutListener.listen(28119) ? (quality <= 100) : (ListenerUtil.mutListener.listen(28118) ? (quality > 100) : (ListenerUtil.mutListener.listen(28117) ? (quality < 100) : (ListenerUtil.mutListener.listen(28116) ? (quality != 100) : (quality == 100))))))))) {
                return null;
            }
        }
        String optimizedPath = ImageUtils.optimizeImage(context, path, resizeDimension, quality);
        if (!ListenerUtil.mutListener.listen(28126)) {
            if (optimizedPath == null) {
                if (!ListenerUtil.mutListener.listen(28124)) {
                    AppLog.e(AppLog.T.EDITOR, "Optimized picture was null!");
                }
                if (!ListenerUtil.mutListener.listen(28125)) {
                    AnalyticsTracker.track(AnalyticsTracker.Stat.MEDIA_PHOTO_OPTIMIZE_ERROR);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(28123)) {
                    AnalyticsTracker.track(AnalyticsTracker.Stat.MEDIA_PHOTO_OPTIMIZED);
                }
                return Uri.parse(optimizedPath);
            }
        }
        return null;
    }

    public static Uri fixOrientationIssue(Context context, String path, boolean isVideo) {
        if (!ListenerUtil.mutListener.listen(28127)) {
            if (isVideo) {
                return null;
            }
        }
        String rotatedPath = ImageUtils.rotateImageIfNecessary(context, path);
        if (!ListenerUtil.mutListener.listen(28128)) {
            if (rotatedPath != null) {
                return Uri.parse(rotatedPath);
            }
        }
        return null;
    }

    public static boolean isVideoOptimizationEnabled() {
        return AppPrefs.isVideoOptimize();
    }

    /**
     * Check if we should advertise image optimization feature for the current site.
     * <p>
     * The following condition need to be all true:
     * 1) Image optimization is OFF on the site.
     * 2) Didn't already ask to enable the feature.
     * 3) The user has granted storage access to the app.
     * This is because we don't want to ask so much things to users the first time they try to add a picture to the app.
     *
     * @param context The context
     * @return true if we should advertise the feature, false otherwise.
     */
    public static boolean shouldAdvertiseImageOptimization(final Context context) {
        boolean isPromoRequired = AppPrefs.isImageOptimizePromoRequired();
        if (!ListenerUtil.mutListener.listen(28129)) {
            if (!isPromoRequired) {
                return false;
            }
        }
        // Check we can access storage before asking for optimizing image
        boolean hasStoreAccess = ContextCompat.checkSelfPermission(context, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;
        if (!ListenerUtil.mutListener.listen(28130)) {
            if (!hasStoreAccess) {
                return false;
            }
        }
        // Check whether image optimization is already available for the site
        return !AppPrefs.isImageOptimize();
    }

    public interface OnAdvertiseImageOptimizationListener {

        void done();
    }

    public static void advertiseImageOptimization(final Context context, final OnAdvertiseImageOptimizationListener listener) {
        DialogInterface.OnClickListener onClickListener = new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (!ListenerUtil.mutListener.listen(28133)) {
                    if (which == DialogInterface.BUTTON_POSITIVE) {
                        if (!ListenerUtil.mutListener.listen(28132)) {
                            if (AppPrefs.isImageOptimize()) {
                            } else {
                                if (!ListenerUtil.mutListener.listen(28131)) {
                                    AppPrefs.setImageOptimize(true);
                                }
                            }
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(28134)) {
                    listener.done();
                }
            }
        };
        DialogInterface.OnCancelListener onCancelListener = new DialogInterface.OnCancelListener() {

            @Override
            public void onCancel(DialogInterface dialog) {
                if (!ListenerUtil.mutListener.listen(28135)) {
                    listener.done();
                }
            }
        };
        AlertDialog.Builder builder = new MaterialAlertDialogBuilder(context);
        if (!ListenerUtil.mutListener.listen(28136)) {
            builder.setTitle(org.wordpress.android.R.string.image_optimization_promo_title);
        }
        if (!ListenerUtil.mutListener.listen(28137)) {
            builder.setMessage(org.wordpress.android.R.string.image_optimization_promo_desc);
        }
        if (!ListenerUtil.mutListener.listen(28138)) {
            builder.setPositiveButton(R.string.turn_on, onClickListener);
        }
        if (!ListenerUtil.mutListener.listen(28139)) {
            builder.setNegativeButton(R.string.leave_off, onClickListener);
        }
        if (!ListenerUtil.mutListener.listen(28140)) {
            builder.setOnCancelListener(onCancelListener);
        }
        if (!ListenerUtil.mutListener.listen(28141)) {
            builder.show();
        }
        if (!ListenerUtil.mutListener.listen(28142)) {
            // Do not ask again
            AppPrefs.setImageOptimizePromoRequired(false);
        }
    }

    /**
     * Given a media error returns the error message to display on the UI.
     *
     * @param error The media error occurred
     * @return String The associated error message.
     */
    @Nullable
    public static String getErrorMessage(final Context context, final MediaModel media, final MediaError error) {
        if (!ListenerUtil.mutListener.listen(28144)) {
            if ((ListenerUtil.mutListener.listen(28143) ? (context == null && error == null) : (context == null || error == null))) {
                return null;
            }
        }
        if (!ListenerUtil.mutListener.listen(28147)) {
            switch(error.type) {
                case FS_READ_PERMISSION_DENIED:
                    return context.getString(R.string.error_media_insufficient_fs_permissions);
                case NOT_FOUND:
                    return context.getString(R.string.error_media_not_found);
                case AUTHORIZATION_REQUIRED:
                    return context.getString(R.string.media_error_no_permission_upload);
                case REQUEST_TOO_LARGE:
                    if (!ListenerUtil.mutListener.listen(28145)) {
                        if (media == null) {
                            return null;
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(28146)) {
                        if (media.isVideo()) {
                            return context.getString(R.string.media_error_http_too_large_video_upload);
                        } else {
                            return context.getString(R.string.media_error_http_too_large_photo_upload);
                        }
                    }
                case SERVER_ERROR:
                    return context.getString(R.string.media_error_internal_server_error);
                case TIMEOUT:
                    return context.getString(R.string.media_error_timeout);
                case CONNECTION_ERROR:
                    return context.getString(R.string.connection_to_server_lost);
                case EXCEEDS_FILESIZE_LIMIT:
                    return context.getString(R.string.media_error_exceeds_php_filesize);
                case EXCEEDS_MEMORY_LIMIT:
                    return context.getString(R.string.media_error_exceeds_memory_limit);
                case PARSE_ERROR:
                    return context.getString(R.string.error_media_parse_error);
                case GENERIC_ERROR:
                    return context.getString(R.string.error_generic_error);
                case EXCEEDS_SITE_SPACE_QUOTA_LIMIT:
                    return context.getString(R.string.error_media_not_enough_storage);
                case XMLRPC_OPERATION_NOT_ALLOWED:
                    return context.getString(R.string.error_media_xmlrpc_not_allowed);
                case XMLRPC_UPLOAD_ERROR:
                    return context.getString(R.string.error_media_xmlrcp_server_error);
                case MALFORMED_MEDIA_ARG:
                    return getMalformedMediaArgErrorMessage(context, error.mErrorSubType);
            }
        }
        return null;
    }

    @Nullable
    private static String getMalformedMediaArgErrorMessage(final Context context, @Nullable final MediaErrorSubType errorSubType) {
        if (!ListenerUtil.mutListener.listen(28148)) {
            if (!(errorSubType instanceof MalformedMediaArgSubType))
                return null;
        }
        String errorMessage = null;
        if (!ListenerUtil.mutListener.listen(28155)) {
            switch(((MalformedMediaArgSubType) errorSubType).getType()) {
                case MEDIA_WAS_NULL:
                    if (!ListenerUtil.mutListener.listen(28149)) {
                        errorMessage = context.getString(R.string.error_media_unexpected_null_value);
                    }
                    break;
                case UNSUPPORTED_MIME_TYPE:
                    if (!ListenerUtil.mutListener.listen(28150)) {
                        errorMessage = context.getString(R.string.error_media_file_type_not_allowed);
                    }
                    break;
                case NOT_VALID_LOCAL_FILE_PATH:
                    if (!ListenerUtil.mutListener.listen(28151)) {
                        errorMessage = context.getString(R.string.error_media_unexpected_empty_media_file_path);
                    }
                    break;
                case MEDIA_FILE_NOT_FOUND_LOCALLY:
                    if (!ListenerUtil.mutListener.listen(28152)) {
                        errorMessage = context.getString(R.string.error_media_could_not_find_media_in_path);
                    }
                    break;
                case DIRECTORY_PATH_SUPPLIED_FILE_NEEDED:
                    if (!ListenerUtil.mutListener.listen(28153)) {
                        errorMessage = context.getString(R.string.error_media_path_is_directory);
                    }
                    break;
                case NO_ERROR:
                    if (!ListenerUtil.mutListener.listen(28154)) {
                        errorMessage = null;
                    }
                    break;
            }
        }
        return errorMessage;
    }

    private static void showSDCardRequiredDialog(Context context) {
        AlertDialog.Builder dialogBuilder = new MaterialAlertDialogBuilder(context);
        if (!ListenerUtil.mutListener.listen(28156)) {
            dialogBuilder.setTitle(context.getResources().getText(R.string.sdcard_title));
        }
        if (!ListenerUtil.mutListener.listen(28157)) {
            dialogBuilder.setMessage(context.getResources().getText(R.string.sdcard_message));
        }
        if (!ListenerUtil.mutListener.listen(28159)) {
            dialogBuilder.setPositiveButton(context.getString(android.R.string.ok), new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialog, int whichButton) {
                    if (!ListenerUtil.mutListener.listen(28158)) {
                        dialog.dismiss();
                    }
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(28160)) {
            dialogBuilder.setCancelable(true);
        }
        if (!ListenerUtil.mutListener.listen(28161)) {
            dialogBuilder.create().show();
        }
    }

    public static void launchVideoLibrary(Activity activity, boolean multiSelect) {
        if (!ListenerUtil.mutListener.listen(28162)) {
            activity.startActivityForResult(prepareVideoLibraryIntent(activity, multiSelect), RequestCodes.VIDEO_LIBRARY);
        }
    }

    public static void launchMediaLibrary(Activity activity, boolean multiSelect) {
        if (!ListenerUtil.mutListener.listen(28163)) {
            activity.startActivityForResult(prepareMediaLibraryIntent(activity, multiSelect), RequestCodes.MEDIA_LIBRARY);
        }
    }

    public static Plan getSitePlanForMimeTypes(SiteModel site) {
        if (site.isWPCom()) {
            if (SiteUtils.onFreePlan(site)) {
                return Plan.WP_COM_FREE;
            } else {
                return Plan.WP_COM_PAID;
            }
        } else {
            return Plan.SELF_HOSTED;
        }
    }

    public static boolean isMimeTypeSupportedBySitePlan(SiteModel site, String mimeType) {
        return Arrays.asList(new MimeTypes().getAllTypes(getSitePlanForMimeTypes(site))).contains(mimeType);
    }

    public static void launchChooserWithContext(Activity activity, OpenSystemPicker openSystemPicker, UiHelpers uiHelpers, int requestCode) {
        if (!ListenerUtil.mutListener.listen(28164)) {
            activity.startActivityForResult(prepareChooserIntent(activity, openSystemPicker, uiHelpers), requestCode);
        }
    }

    private static Intent preparePictureLibraryIntent(Context context, boolean multiSelect) {
        return prepareIntent(context, multiSelect, Intent.ACTION_GET_CONTENT, "image/*", new MimeTypes().getImageTypesOnly(), R.string.pick_photo);
    }

    private static Intent prepareVideoLibraryIntent(Context context, boolean multiSelect) {
        return prepareIntent(context, multiSelect, Intent.ACTION_GET_CONTENT, "video/*", new MimeTypes().getVideoTypesOnly(), R.string.pick_video);
    }

    private static Intent prepareMediaLibraryIntent(Context context, boolean multiSelect) {
        return prepareIntent(context, multiSelect, Intent.ACTION_GET_CONTENT, "*/*", new MimeTypes().getVideoAndImageTypesOnly(), R.string.pick_media);
    }

    private static Intent prepareIntent(Context context, boolean multiSelect, String action, String intentType, String[] mimeTypes, @StringRes int title) {
        Intent intent = new Intent(action);
        if (!ListenerUtil.mutListener.listen(28165)) {
            intent.setType(intentType);
        }
        if (!ListenerUtil.mutListener.listen(28166)) {
            intent.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes);
        }
        if (!ListenerUtil.mutListener.listen(28168)) {
            if (multiSelect) {
                if (!ListenerUtil.mutListener.listen(28167)) {
                    intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                }
            }
        }
        return Intent.createChooser(intent, context.getString(title));
    }

    private static Intent prepareChooserIntent(Context context, OpenSystemPicker openSystemPicker, UiHelpers uiHelpers) {
        ChooserContext chooserContext = openSystemPicker.getChooserContext();
        Intent intent = new Intent(chooserContext.getIntentAction());
        if (!ListenerUtil.mutListener.listen(28169)) {
            intent.setType(chooserContext.getMediaTypeFilter());
        }
        if (!ListenerUtil.mutListener.listen(28170)) {
            intent.putExtra(Intent.EXTRA_MIME_TYPES, openSystemPicker.getMimeTypes().toArray(new String[0]));
        }
        if (!ListenerUtil.mutListener.listen(28172)) {
            if (openSystemPicker.getAllowMultipleSelection()) {
                if (!ListenerUtil.mutListener.listen(28171)) {
                    intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                }
            }
        }
        return Intent.createChooser(intent, uiHelpers.getTextOfUiString(context, chooserContext.getTitle()));
    }

    public static void launchVideoCamera(Activity activity) {
        if (!ListenerUtil.mutListener.listen(28173)) {
            activity.startActivityForResult(prepareVideoCameraIntent(), RequestCodes.TAKE_VIDEO);
        }
    }

    private static Intent prepareVideoCameraIntent() {
        return new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
    }

    public static void launchPictureLibrary(Activity activity, boolean multiSelect) {
        if (!ListenerUtil.mutListener.listen(28174)) {
            activity.startActivityForResult(preparePictureLibraryIntent(activity, multiSelect), RequestCodes.PICTURE_LIBRARY);
        }
    }

    private static Intent prepareGalleryIntent(String title) {
        Intent intent = new Intent(Intent.ACTION_PICK);
        if (!ListenerUtil.mutListener.listen(28175)) {
            intent.setType("image/*");
        }
        if (!ListenerUtil.mutListener.listen(28176)) {
            intent.putExtra(Intent.EXTRA_MIME_TYPES, new MimeTypes().getImageTypesOnly());
        }
        return Intent.createChooser(intent, title);
    }

    public static void launchCamera(Activity activity, String applicationId, LaunchCameraCallback callback) {
        Intent intent = prepareLaunchCamera(activity, applicationId, callback);
        if (!ListenerUtil.mutListener.listen(28178)) {
            if (intent != null) {
                if (!ListenerUtil.mutListener.listen(28177)) {
                    activity.startActivityForResult(intent, RequestCodes.TAKE_PHOTO);
                }
            }
        }
    }

    private static Intent prepareLaunchCamera(Context context, String applicationId, LaunchCameraCallback callback) {
        String state = android.os.Environment.getExternalStorageState();
        if (!state.equals(android.os.Environment.MEDIA_MOUNTED)) {
            if (!ListenerUtil.mutListener.listen(28179)) {
                showSDCardRequiredDialog(context);
            }
            return null;
        } else {
            try {
                return getLaunchCameraIntent(context, applicationId, callback);
            } catch (IOException e) {
                // No need to write log here
                return null;
            }
        }
    }

    private static Intent getLaunchCameraIntent(Context context, String applicationId, LaunchCameraCallback callback) throws IOException {
        File externalStoragePublicDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);
        String mediaCapturePath = externalStoragePublicDirectory + File.separator + "Camera" + File.separator + "wp-" + System.currentTimeMillis() + ".jpg";
        // make sure the directory we plan to store the recording in exists
        File directory = new File(mediaCapturePath).getParentFile();
        if (!ListenerUtil.mutListener.listen(28183)) {
            if ((ListenerUtil.mutListener.listen(28181) ? (directory == null && ((ListenerUtil.mutListener.listen(28180) ? (!directory.exists() || !directory.mkdirs()) : (!directory.exists() && !directory.mkdirs())))) : (directory == null || ((ListenerUtil.mutListener.listen(28180) ? (!directory.exists() || !directory.mkdirs()) : (!directory.exists() && !directory.mkdirs())))))) {
                try {
                    throw new IOException("Path to file could not be created: " + mediaCapturePath);
                } catch (IOException e) {
                    if (!ListenerUtil.mutListener.listen(28182)) {
                        AppLog.e(T.MEDIA, e);
                    }
                    throw e;
                }
            }
        }
        Uri fileUri;
        try {
            fileUri = FileProvider.getUriForFile(context, applicationId + ".provider", new File(mediaCapturePath));
        } catch (IllegalArgumentException e) {
            if (!ListenerUtil.mutListener.listen(28184)) {
                AppLog.e(T.MEDIA, "Cannot access the file planned to store the new media", e);
            }
            throw new IOException("Cannot access the file planned to store the new media");
        } catch (NullPointerException e) {
            if (!ListenerUtil.mutListener.listen(28185)) {
                AppLog.e(T.MEDIA, "Cannot access the file planned to store the new media - " + "FileProvider.getUriForFile cannot find a valid provider for the authority: " + applicationId + ".provider", e);
            }
            throw new IOException("Cannot access the file planned to store the new media");
        }
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (!ListenerUtil.mutListener.listen(28186)) {
            intent.putExtra(android.provider.MediaStore.EXTRA_OUTPUT, fileUri);
        }
        if (!ListenerUtil.mutListener.listen(28188)) {
            if (callback != null) {
                if (!ListenerUtil.mutListener.listen(28187)) {
                    callback.onMediaCapturePathReady(mediaCapturePath);
                }
            }
        }
        return intent;
    }

    private static Intent makePickOrCaptureIntent(Context context, String applicationId, LaunchCameraCallback callback) {
        Intent pickPhotoIntent = prepareGalleryIntent(context.getString(R.string.capture_or_pick_photo));
        if (!ListenerUtil.mutListener.listen(28190)) {
            if (DeviceUtils.getInstance().hasCamera(context)) {
                try {
                    Intent cameraIntent = getLaunchCameraIntent(context, applicationId, callback);
                    if (!ListenerUtil.mutListener.listen(28189)) {
                        pickPhotoIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, new Intent[] { cameraIntent });
                    }
                } catch (IOException e) {
                }
            }
        }
        return pickPhotoIntent;
    }

    public static int getPlaceholder(String url) {
        if (MediaUtils.isValidImage(url)) {
            return R.drawable.ic_image_white_24dp;
        } else if (MediaUtils.isDocument(url)) {
            return R.drawable.ic_pages_white_24dp;
        } else if (MediaUtils.isPowerpoint(url)) {
            return R.drawable.media_powerpoint;
        } else if (MediaUtils.isSpreadsheet(url)) {
            return R.drawable.media_spreadsheet;
        } else if (MediaUtils.isVideo(url)) {
            return R.drawable.ic_video_camera_white_24dp;
        } else if (MediaUtils.isAudio(url)) {
            return R.drawable.ic_audio_white_24dp;
        } else {
            return R.drawable.ic_image_multiple_white_24dp;
        }
    }

    public static boolean canDeleteMedia(MediaModel mediaModel) {
        String state = mediaModel.getUploadState();
        return (ListenerUtil.mutListener.listen(28192) ? (state == null && ((ListenerUtil.mutListener.listen(28191) ? (!state.equalsIgnoreCase("uploading") || !state.equalsIgnoreCase("deleted")) : (!state.equalsIgnoreCase("uploading") && !state.equalsIgnoreCase("deleted"))))) : (state == null || ((ListenerUtil.mutListener.listen(28191) ? (!state.equalsIgnoreCase("uploading") || !state.equalsIgnoreCase("deleted")) : (!state.equalsIgnoreCase("uploading") && !state.equalsIgnoreCase("deleted"))))));
    }

    /**
     * Returns a poster (thumbnail) URL given a VideoPress video URL
     *
     * @param videoUrl the remote URL to the VideoPress video
     */
    public static String getVideoPressVideoPosterFromURL(String videoUrl) {
        String posterUrl = "";
        if (!ListenerUtil.mutListener.listen(28200)) {
            if (videoUrl != null) {
                int fileTypeLocation = videoUrl.lastIndexOf(".");
                if (!ListenerUtil.mutListener.listen(28199)) {
                    if ((ListenerUtil.mutListener.listen(28197) ? (fileTypeLocation >= 0) : (ListenerUtil.mutListener.listen(28196) ? (fileTypeLocation <= 0) : (ListenerUtil.mutListener.listen(28195) ? (fileTypeLocation < 0) : (ListenerUtil.mutListener.listen(28194) ? (fileTypeLocation != 0) : (ListenerUtil.mutListener.listen(28193) ? (fileTypeLocation == 0) : (fileTypeLocation > 0))))))) {
                        if (!ListenerUtil.mutListener.listen(28198)) {
                            posterUrl = videoUrl.substring(0, fileTypeLocation) + "_std.original.jpg";
                        }
                    }
                }
            }
        }
        return posterUrl;
    }

    /*
     * passes a newly-created media file to the media scanner service so it's available to
     * the media content provider - use this after capturing or downloading media to ensure
     * that it appears in the stock Gallery app
     */
    public static void scanMediaFile(@NonNull Context context, @NonNull String localMediaPath) {
        if (!ListenerUtil.mutListener.listen(28202)) {
            MediaScannerConnection.scanFile(context, new String[] { localMediaPath }, null, new MediaScannerConnection.OnScanCompletedListener() {

                public void onScanCompleted(String path, Uri uri) {
                    if (!ListenerUtil.mutListener.listen(28201)) {
                        AppLog.d(T.MEDIA, "Media scanner finished scanning " + path);
                    }
                }
            });
        }
    }

    /*
     * returns true if the current user has permission to upload new media to the passed site
     */
    public static boolean currentUserCanUploadMedia(@NonNull SiteModel site) {
        boolean isSelfHosted = !site.isUsingWpComRestApi();
        // self-hosted sites don't have capabilities so always return true
        return (ListenerUtil.mutListener.listen(28203) ? (isSelfHosted && site.getHasCapabilityUploadFiles()) : (isSelfHosted || site.getHasCapabilityUploadFiles()));
    }

    public static boolean currentUserCanDeleteMedia(@NonNull SiteModel site) {
        return currentUserCanUploadMedia(site);
    }

    /*
     * returns the minimum distance for a fling which determines whether to disable loading
     * thumbnails in the media grid or photo picker - used to conserve memory usage during
     * a reasonably-sized fling
     */
    public static int getFlingDistanceToDisableThumbLoading(@NonNull Context context) {
        return (ListenerUtil.mutListener.listen(28207) ? (ViewConfiguration.get(context).getScaledMaximumFlingVelocity() % 2) : (ListenerUtil.mutListener.listen(28206) ? (ViewConfiguration.get(context).getScaledMaximumFlingVelocity() * 2) : (ListenerUtil.mutListener.listen(28205) ? (ViewConfiguration.get(context).getScaledMaximumFlingVelocity() - 2) : (ListenerUtil.mutListener.listen(28204) ? (ViewConfiguration.get(context).getScaledMaximumFlingVelocity() + 2) : (ViewConfiguration.get(context).getScaledMaximumFlingVelocity() / 2)))));
    }

    public interface MediaFetchDoNext {

        void doNext(Uri uri);
    }

    /**
     * Downloads the {@code mediaUri} and returns the {@link Uri} for the downloaded file
     * <p>
     * If the {@code mediaUri} is already in the the local store, no download will be done and the given
     * {@code mediaUri} will be returned instead. This may return null if the download fails.
     * <p>
     * The current thread is blocked until the download is finished.
     *
     * @return A local {@link Uri} or null if the download failed
     */
    @Nullable
    public static Uri fetchMedia(@NonNull Context context, @NonNull Uri mediaUri) {
        if (MediaUtils.isInMediaStore(mediaUri)) {
            return mediaUri;
        }
        try {
            // https://github.com/wordpress-mobile/WordPress-Android/issues/5818
            return MediaUtils.downloadExternalMedia(context, mediaUri);
        } catch (IllegalStateException e) {
            if (!ListenerUtil.mutListener.listen(28208)) {
                // Ref: https://github.com/wordpress-mobile/WordPress-Android/issues/5823
                AppLog.e(AppLog.T.UTILS, "Can't download the image at: " + mediaUri.toString() + " See issue #5823", e);
            }
            return null;
        }
    }

    /**
     * Downloads the given {@code mediaUri} and calls {@code listener} if successful
     * <p>
     * If the download fails, a {@link android.widget.Toast} will be shown.
     *
     * @return A {@link Boolean} indicating whether the download was successful
     */
    public static boolean fetchMediaAndDoNext(Context context, Uri mediaUri, MediaFetchDoNext listener) {
        final Uri downloadedUri = fetchMedia(context, mediaUri);
        if (downloadedUri != null) {
            if (!ListenerUtil.mutListener.listen(28210)) {
                listener.doNext(downloadedUri);
            }
            return true;
        } else {
            if (!ListenerUtil.mutListener.listen(28209)) {
                ToastUtils.showToast(context, R.string.error_downloading_image, ToastUtils.Duration.SHORT);
            }
            return false;
        }
    }

    public static List<Uri> retrieveImageEditorResult(Intent data) {
        if ((ListenerUtil.mutListener.listen(28211) ? (data != null || data.hasExtra(PreviewImageFragment.ARG_EDIT_IMAGE_DATA)) : (data != null && data.hasExtra(PreviewImageFragment.ARG_EDIT_IMAGE_DATA)))) {
            return convertEditImageOutputToListOfUris(data.getParcelableArrayListExtra(PreviewImageFragment.ARG_EDIT_IMAGE_DATA));
        } else {
            return new ArrayList<Uri>();
        }
    }

    private static List<Uri> convertEditImageOutputToListOfUris(List<EditImageData.OutputData> data) {
        List<Uri> uris = new ArrayList<>(data.size());
        if (!ListenerUtil.mutListener.listen(28213)) {
            {
                long _loopCounter422 = 0;
                for (EditImageData.OutputData item : data) {
                    ListenerUtil.loopListener.listen("_loopCounter422", ++_loopCounter422);
                    if (!ListenerUtil.mutListener.listen(28212)) {
                        uris.add(Uri.parse(item.getOutputFilePath()));
                    }
                }
            }
        }
        return uris;
    }

    public static List<Uri> retrieveMediaUris(Intent data) {
        ClipData clipData = data.getClipData();
        ArrayList<Uri> uriList = new ArrayList<>();
        if (!ListenerUtil.mutListener.listen(28222)) {
            if (clipData != null) {
                if (!ListenerUtil.mutListener.listen(28221)) {
                    {
                        long _loopCounter423 = 0;
                        for (int i = 0; (ListenerUtil.mutListener.listen(28220) ? (i >= clipData.getItemCount()) : (ListenerUtil.mutListener.listen(28219) ? (i <= clipData.getItemCount()) : (ListenerUtil.mutListener.listen(28218) ? (i > clipData.getItemCount()) : (ListenerUtil.mutListener.listen(28217) ? (i != clipData.getItemCount()) : (ListenerUtil.mutListener.listen(28216) ? (i == clipData.getItemCount()) : (i < clipData.getItemCount())))))); i++) {
                            ListenerUtil.loopListener.listen("_loopCounter423", ++_loopCounter423);
                            ClipData.Item item = clipData.getItemAt(i);
                            if (!ListenerUtil.mutListener.listen(28215)) {
                                uriList.add(item.getUri());
                            }
                        }
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(28214)) {
                    uriList.add(data.getData());
                }
            }
        }
        return uriList;
    }

    public static ArrayList<EditImageData.InputData> createListOfEditImageInputData(Context ctx, List<Uri> uris) {
        ArrayList<EditImageData.InputData> inputData = new ArrayList<>(uris.size());
        if (!ListenerUtil.mutListener.listen(28224)) {
            {
                long _loopCounter424 = 0;
                for (Uri uri : uris) {
                    ListenerUtil.loopListener.listen("_loopCounter424", ++_loopCounter424);
                    String outputFileExtension = getFileExtension(ctx, uri);
                    if (!ListenerUtil.mutListener.listen(28223)) {
                        inputData.add(new EditImageData.InputData(uri.toString(), null, outputFileExtension));
                    }
                }
            }
        }
        return inputData;
    }

    public static String getFileExtension(Context ctx, Uri uri) {
        String fileExtension;
        if ((ListenerUtil.mutListener.listen(28225) ? (uri.getScheme() != null || uri.getScheme().equals(ContentResolver.SCHEME_CONTENT)) : (uri.getScheme() != null && uri.getScheme().equals(ContentResolver.SCHEME_CONTENT)))) {
            ContentResolver cr = ctx.getContentResolver();
            String mimeType = cr.getType(uri);
            fileExtension = MimeTypeMap.getSingleton().getExtensionFromMimeType(mimeType);
        } else {
            fileExtension = MimeTypeMap.getFileExtensionFromUrl(uri.toString());
        }
        return fileExtension;
    }
}
