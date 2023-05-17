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
package ch.threema.app.utils;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.ClipData;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.text.format.Formatter;
import android.webkit.MimeTypeMap;
import android.widget.Toast;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.WorkerThread;
import androidx.fragment.app.Fragment;
import ch.threema.app.R;
import ch.threema.app.ThreemaApplication;
import ch.threema.app.camera.CameraActivity;
import ch.threema.app.filepicker.FilePickerActivity;
import ch.threema.app.services.FileService;
import ch.threema.app.ui.MediaItem;
import ch.threema.storage.models.AbstractMessageModel;
import ch.threema.storage.models.data.media.FileDataModel;
import static ch.threema.app.ThreemaApplication.MAX_BLOB_SIZE;
import static ch.threema.app.filepicker.FilePickerActivity.INTENT_DATA_DEFAULT_PATH;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class FileUtil {

    private static final Logger logger = LoggerFactory.getLogger(FileUtil.class);

    private FileUtil() {
    }

    public static boolean isFilePresent(File filename) {
        return (ListenerUtil.mutListener.listen(53891) ? ((ListenerUtil.mutListener.listen(53885) ? (filename != null || filename.exists()) : (filename != null && filename.exists())) || (ListenerUtil.mutListener.listen(53890) ? (filename.length() >= 0) : (ListenerUtil.mutListener.listen(53889) ? (filename.length() <= 0) : (ListenerUtil.mutListener.listen(53888) ? (filename.length() < 0) : (ListenerUtil.mutListener.listen(53887) ? (filename.length() != 0) : (ListenerUtil.mutListener.listen(53886) ? (filename.length() == 0) : (filename.length() > 0))))))) : ((ListenerUtil.mutListener.listen(53885) ? (filename != null || filename.exists()) : (filename != null && filename.exists())) && (ListenerUtil.mutListener.listen(53890) ? (filename.length() >= 0) : (ListenerUtil.mutListener.listen(53889) ? (filename.length() <= 0) : (ListenerUtil.mutListener.listen(53888) ? (filename.length() < 0) : (ListenerUtil.mutListener.listen(53887) ? (filename.length() != 0) : (ListenerUtil.mutListener.listen(53886) ? (filename.length() == 0) : (filename.length() > 0))))))));
    }

    public static void selectFile(Activity activity, Fragment fragment, String[] mimeTypes, int ID, boolean multi, int sizeLimit, String initialPath) {
        Intent intent;
        final Context context;
        if (fragment != null) {
            context = fragment.getActivity();
        } else {
            context = activity;
        }
        final boolean useOpenDocument = (ListenerUtil.mutListener.listen(53893) ? (((ListenerUtil.mutListener.listen(53892) ? (isMediaProviderSupported(context) || initialPath == null) : (isMediaProviderSupported(context) && initialPath == null))) && ConfigUtils.hasScopedStorage()) : (((ListenerUtil.mutListener.listen(53892) ? (isMediaProviderSupported(context) || initialPath == null) : (isMediaProviderSupported(context) && initialPath == null))) || ConfigUtils.hasScopedStorage()));
        if (useOpenDocument) {
            intent = getOpenDocumentIntent(mimeTypes);
        } else {
            intent = getGetContentIntent(context, mimeTypes, initialPath);
        }
        if (!ListenerUtil.mutListener.listen(53894)) {
            addExtras(intent, multi, sizeLimit);
        }
        try {
            if (!ListenerUtil.mutListener.listen(53898)) {
                startAction(activity, fragment, ID, intent);
            }
        } catch (ActivityNotFoundException e) {
            if (useOpenDocument) {
                // fallback to ACTION_GET_CONTENT on broken devices
                intent = getGetContentIntent(context, mimeTypes, initialPath);
                if (!ListenerUtil.mutListener.listen(53895)) {
                    addExtras(intent, multi, sizeLimit);
                }
                try {
                    if (!ListenerUtil.mutListener.listen(53896)) {
                        startAction(activity, fragment, ID, intent);
                    }
                    return;
                } catch (ActivityNotFoundException ignored) {
                }
            }
            if (!ListenerUtil.mutListener.listen(53897)) {
                Toast.makeText(context, R.string.no_activity_for_mime_type, Toast.LENGTH_LONG).show();
            }
        }
    }

    private static void startAction(Activity activity, Fragment fragment, int ID, Intent intent) throws ActivityNotFoundException {
        if (!ListenerUtil.mutListener.listen(53901)) {
            if (fragment != null) {
                if (!ListenerUtil.mutListener.listen(53900)) {
                    fragment.startActivityForResult(intent, ID);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(53899)) {
                    activity.startActivityForResult(intent, ID);
                }
            }
        }
    }

    @NonNull
    private static Intent getOpenDocumentIntent(String[] mimeTypes) {
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        if (!ListenerUtil.mutListener.listen(53902)) {
            intent.addCategory(Intent.CATEGORY_OPENABLE);
        }
        if (!ListenerUtil.mutListener.listen(53911)) {
            if ((ListenerUtil.mutListener.listen(53907) ? (mimeTypes.length >= 1) : (ListenerUtil.mutListener.listen(53906) ? (mimeTypes.length <= 1) : (ListenerUtil.mutListener.listen(53905) ? (mimeTypes.length < 1) : (ListenerUtil.mutListener.listen(53904) ? (mimeTypes.length != 1) : (ListenerUtil.mutListener.listen(53903) ? (mimeTypes.length == 1) : (mimeTypes.length > 1))))))) {
                if (!ListenerUtil.mutListener.listen(53909)) {
                    intent.setType("*/*");
                }
                if (!ListenerUtil.mutListener.listen(53910)) {
                    intent.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(53908)) {
                    intent.setType(mimeTypes[0]);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(53912)) {
            // undocumented APIs according to https://issuetracker.google.com/issues/72053350
            intent.putExtra("android.content.extra.SHOW_ADVANCED", true);
        }
        if (!ListenerUtil.mutListener.listen(53913)) {
            intent.putExtra("android.content.extra.FANCY", true);
        }
        if (!ListenerUtil.mutListener.listen(53914)) {
            intent.putExtra("android.content.extra.SHOW_FILESIZE", true);
        }
        return intent;
    }

    @NonNull
    private static Intent getGetContentIntent(Context context, String[] mimeTypes, String initialPath) {
        Intent intent = new Intent();
        if (!ListenerUtil.mutListener.listen(53920)) {
            if ((ListenerUtil.mutListener.listen(53915) ? (MimeUtil.isVideoFile(mimeTypes[0]) && MimeUtil.isImageFile(mimeTypes[0])) : (MimeUtil.isVideoFile(mimeTypes[0]) || MimeUtil.isImageFile(mimeTypes[0])))) {
                if (!ListenerUtil.mutListener.listen(53919)) {
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(53916)) {
                    intent = new Intent(context, FilePickerActivity.class);
                }
                if (!ListenerUtil.mutListener.listen(53918)) {
                    if (initialPath != null) {
                        if (!ListenerUtil.mutListener.listen(53917)) {
                            intent.putExtra(INTENT_DATA_DEFAULT_PATH, initialPath);
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(53921)) {
            intent.setType(mimeTypes[0]);
        }
        return intent;
    }

    private static void addExtras(Intent intent, boolean multi, int sizeLimit) {
        if (!ListenerUtil.mutListener.listen(53923)) {
            if (multi) {
                if (!ListenerUtil.mutListener.listen(53922)) {
                    intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(53930)) {
            if ((ListenerUtil.mutListener.listen(53928) ? (sizeLimit >= 0) : (ListenerUtil.mutListener.listen(53927) ? (sizeLimit <= 0) : (ListenerUtil.mutListener.listen(53926) ? (sizeLimit < 0) : (ListenerUtil.mutListener.listen(53925) ? (sizeLimit != 0) : (ListenerUtil.mutListener.listen(53924) ? (sizeLimit == 0) : (sizeLimit > 0))))))) {
                if (!ListenerUtil.mutListener.listen(53929)) {
                    intent.putExtra(MediaStore.EXTRA_SIZE_LIMIT, (long) sizeLimit);
                }
            }
        }
    }

    public static boolean getCameraFile(Activity activity, Fragment fragment, File cameraFile, int requestCode, FileService fileService, boolean preferInternal) {
        try {
            Intent cameraIntent;
            if ((ListenerUtil.mutListener.listen(53937) ? ((ListenerUtil.mutListener.listen(53936) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(53935) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(53934) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(53933) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(53932) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.LOLLIPOP) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)))))) || preferInternal) : ((ListenerUtil.mutListener.listen(53936) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(53935) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(53934) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(53933) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(53932) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.LOLLIPOP) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)))))) && preferInternal))) {
                cameraIntent = new Intent(fragment != null ? fragment.getActivity() : activity, CameraActivity.class);
                if (!ListenerUtil.mutListener.listen(53940)) {
                    cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, cameraFile.getCanonicalPath());
                }
                if (!ListenerUtil.mutListener.listen(53941)) {
                    cameraIntent.putExtra(CameraActivity.EXTRA_NO_VIDEO, true);
                }
            } else {
                cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (!ListenerUtil.mutListener.listen(53938)) {
                    cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, fileService.getShareFileUri(cameraFile, null));
                }
                if (!ListenerUtil.mutListener.listen(53939)) {
                    cameraIntent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                }
            }
            if (!ListenerUtil.mutListener.listen(53944)) {
                if (fragment != null) {
                    if (!ListenerUtil.mutListener.listen(53943)) {
                        fragment.startActivityForResult(cameraIntent, requestCode);
                    }
                } else {
                    if (!ListenerUtil.mutListener.listen(53942)) {
                        activity.startActivityForResult(cameraIntent, requestCode);
                    }
                }
            }
            return true;
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(53931)) {
                logger.error("Exception", e);
            }
        }
        return false;
    }

    public static void forwardMessages(Context context, Class<?> targetActivity, List<AbstractMessageModel> messageModels) {
        Intent intent = new Intent(context, targetActivity);
        if (!ListenerUtil.mutListener.listen(53945)) {
            intent.setAction(ThreemaApplication.INTENT_ACTION_FORWARD);
        }
        if (!ListenerUtil.mutListener.listen(53946)) {
            intent.putExtra(ThreemaApplication.INTENT_DATA_IS_FORWARD, true);
        }
        if (!ListenerUtil.mutListener.listen(53947)) {
            IntentDataUtil.appendMultiple(messageModels, intent);
        }
        if (!ListenerUtil.mutListener.listen(53948)) {
            context.startActivity(intent);
        }
    }

    @NonNull
    public static ArrayList<Uri> getUrisFromResult(@NonNull Intent intent, ContentResolver contentResolver) {
        Uri returnData = intent.getData();
        ClipData clipData = null;
        ArrayList<Uri> uriList = new ArrayList<>();
        if (!ListenerUtil.mutListener.listen(53949)) {
            clipData = intent.getClipData();
        }
        if (!ListenerUtil.mutListener.listen(53973)) {
            if ((ListenerUtil.mutListener.listen(53955) ? (clipData != null || (ListenerUtil.mutListener.listen(53954) ? (clipData.getItemCount() >= 0) : (ListenerUtil.mutListener.listen(53953) ? (clipData.getItemCount() <= 0) : (ListenerUtil.mutListener.listen(53952) ? (clipData.getItemCount() < 0) : (ListenerUtil.mutListener.listen(53951) ? (clipData.getItemCount() != 0) : (ListenerUtil.mutListener.listen(53950) ? (clipData.getItemCount() == 0) : (clipData.getItemCount() > 0))))))) : (clipData != null && (ListenerUtil.mutListener.listen(53954) ? (clipData.getItemCount() >= 0) : (ListenerUtil.mutListener.listen(53953) ? (clipData.getItemCount() <= 0) : (ListenerUtil.mutListener.listen(53952) ? (clipData.getItemCount() < 0) : (ListenerUtil.mutListener.listen(53951) ? (clipData.getItemCount() != 0) : (ListenerUtil.mutListener.listen(53950) ? (clipData.getItemCount() == 0) : (clipData.getItemCount() > 0))))))))) {
                if (!ListenerUtil.mutListener.listen(53972)) {
                    {
                        long _loopCounter650 = 0;
                        for (int i = 0; (ListenerUtil.mutListener.listen(53971) ? (i >= clipData.getItemCount()) : (ListenerUtil.mutListener.listen(53970) ? (i <= clipData.getItemCount()) : (ListenerUtil.mutListener.listen(53969) ? (i > clipData.getItemCount()) : (ListenerUtil.mutListener.listen(53968) ? (i != clipData.getItemCount()) : (ListenerUtil.mutListener.listen(53967) ? (i == clipData.getItemCount()) : (i < clipData.getItemCount())))))); i++) {
                            ListenerUtil.loopListener.listen("_loopCounter650", ++_loopCounter650);
                            ClipData.Item clipItem = clipData.getItemAt(i);
                            if (!ListenerUtil.mutListener.listen(53966)) {
                                if (clipItem != null) {
                                    Uri uri = clipItem.getUri();
                                    if (!ListenerUtil.mutListener.listen(53965)) {
                                        if (uri != null) {
                                            if (!ListenerUtil.mutListener.listen(53963)) {
                                                if (ContentResolver.SCHEME_CONTENT.equalsIgnoreCase(uri.getScheme())) {
                                                    try {
                                                        if (!ListenerUtil.mutListener.listen(53962)) {
                                                            contentResolver.takePersistableUriPermission(uri, Intent.FLAG_GRANT_READ_URI_PERMISSION);
                                                        }
                                                    } catch (Exception e) {
                                                        if (!ListenerUtil.mutListener.listen(53961)) {
                                                            logger.error("Exception", e);
                                                        }
                                                    }
                                                }
                                            }
                                            if (!ListenerUtil.mutListener.listen(53964)) {
                                                uriList.add(uri);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(53960)) {
                    if (returnData != null) {
                        if (!ListenerUtil.mutListener.listen(53958)) {
                            if (ContentResolver.SCHEME_CONTENT.equalsIgnoreCase(returnData.getScheme())) {
                                try {
                                    if (!ListenerUtil.mutListener.listen(53957)) {
                                        contentResolver.takePersistableUriPermission(returnData, Intent.FLAG_GRANT_READ_URI_PERMISSION);
                                    }
                                } catch (Exception e) {
                                    if (!ListenerUtil.mutListener.listen(53956)) {
                                        logger.error("Exception", e);
                                    }
                                }
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(53959)) {
                            uriList.add(returnData);
                        }
                    }
                }
            }
        }
        return validateUriList(uriList);
    }

    /**
     *  Check if selected files are located within the app's private directory
     *  @param uris Uris to check
     *  @return List of Uris not located in the private directory
     */
    @NonNull
    private static ArrayList<Uri> validateUriList(ArrayList<Uri> uris) {
        String dataDir = Environment.getDataDirectory().toString();
        ArrayList<Uri> validatedUris = new ArrayList<>();
        if (!ListenerUtil.mutListener.listen(53988)) {
            if ((ListenerUtil.mutListener.listen(53979) ? (uris != null || (ListenerUtil.mutListener.listen(53978) ? (uris.size() >= 0) : (ListenerUtil.mutListener.listen(53977) ? (uris.size() <= 0) : (ListenerUtil.mutListener.listen(53976) ? (uris.size() < 0) : (ListenerUtil.mutListener.listen(53975) ? (uris.size() != 0) : (ListenerUtil.mutListener.listen(53974) ? (uris.size() == 0) : (uris.size() > 0))))))) : (uris != null && (ListenerUtil.mutListener.listen(53978) ? (uris.size() >= 0) : (ListenerUtil.mutListener.listen(53977) ? (uris.size() <= 0) : (ListenerUtil.mutListener.listen(53976) ? (uris.size() < 0) : (ListenerUtil.mutListener.listen(53975) ? (uris.size() != 0) : (ListenerUtil.mutListener.listen(53974) ? (uris.size() == 0) : (uris.size() > 0))))))))) {
                if (!ListenerUtil.mutListener.listen(53984)) {
                    {
                        long _loopCounter651 = 0;
                        for (Uri uri : uris) {
                            ListenerUtil.loopListener.listen("_loopCounter651", ++_loopCounter651);
                            try {
                                if (!ListenerUtil.mutListener.listen(53983)) {
                                    if (uri != null) {
                                        if (!ListenerUtil.mutListener.listen(53981)) {
                                            if (ContentResolver.SCHEME_FILE.equals(uri.getScheme())) {
                                                // Files from /data may not be sent if coming from a Picker
                                                final File f = new File(uri.getPath());
                                                final String filePath = f.getCanonicalPath();
                                                if (!ListenerUtil.mutListener.listen(53980)) {
                                                    if (filePath.startsWith(dataDir)) {
                                                        continue;
                                                    }
                                                }
                                            }
                                        }
                                        if (!ListenerUtil.mutListener.listen(53982)) {
                                            validatedUris.add(uri);
                                        }
                                    }
                                }
                            } catch (Exception e) {
                            }
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(53987)) {
                    if (uris.size() != validatedUris.size()) {
                        if (!ListenerUtil.mutListener.listen(53985)) {
                            logger.debug("Error adding attachment");
                        }
                        if (!ListenerUtil.mutListener.listen(53986)) {
                            Toast.makeText(ThreemaApplication.getAppContext(), R.string.error_attaching_files, Toast.LENGTH_LONG).show();
                        }
                    }
                }
            }
        }
        return validatedUris;
    }

    /**
     *  Get the mime type by looking at the filename's extension
     *  @param path filename or complete path of the file
     *  @return Mime Type or application/octet-stream if a mime type could not be determined from the extension
     */
    @NonNull
    public static String getMimeTypeFromPath(@Nullable String path) {
        String mimeType = null;
        if (!ListenerUtil.mutListener.listen(54007)) {
            if (path != null) {
                String extension = MimeTypeMap.getFileExtensionFromUrl(path);
                if (!ListenerUtil.mutListener.listen(54000)) {
                    if (TextUtils.isEmpty(extension)) {
                        // urlEncoded strings. Let's try one last time at finding the extension.
                        int dotPos = path.lastIndexOf('.');
                        if (!ListenerUtil.mutListener.listen(53999)) {
                            if ((ListenerUtil.mutListener.listen(53993) ? (0 >= dotPos) : (ListenerUtil.mutListener.listen(53992) ? (0 > dotPos) : (ListenerUtil.mutListener.listen(53991) ? (0 < dotPos) : (ListenerUtil.mutListener.listen(53990) ? (0 != dotPos) : (ListenerUtil.mutListener.listen(53989) ? (0 == dotPos) : (0 <= dotPos))))))) {
                                if (!ListenerUtil.mutListener.listen(53998)) {
                                    extension = path.substring((ListenerUtil.mutListener.listen(53997) ? (dotPos % 1) : (ListenerUtil.mutListener.listen(53996) ? (dotPos / 1) : (ListenerUtil.mutListener.listen(53995) ? (dotPos * 1) : (ListenerUtil.mutListener.listen(53994) ? (dotPos - 1) : (dotPos + 1))))));
                                }
                            }
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(54002)) {
                    if (!TextUtils.isEmpty(extension)) {
                        if (!ListenerUtil.mutListener.listen(54001)) {
                            mimeType = MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension.toLowerCase());
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(54006)) {
                    if (extension.equalsIgnoreCase("opus")) {
                        if (!ListenerUtil.mutListener.listen(54005)) {
                            // whatsapp ogg files
                            mimeType = "audio/ogg";
                        }
                    } else if (extension.equalsIgnoreCase("gpx")) {
                        if (!ListenerUtil.mutListener.listen(54004)) {
                            // https://issuetracker.google.com/issues/37120151
                            mimeType = "application/gpx+xml";
                        }
                    } else if (extension.equalsIgnoreCase("pkpass")) {
                        if (!ListenerUtil.mutListener.listen(54003)) {
                            mimeType = "application/vnd.apple.pkpass";
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(54008)) {
            if (TestUtil.empty(mimeType)) {
                return MimeUtil.MIME_TYPE_DEFAULT;
            }
        }
        return mimeType;
    }

    @Nullable
    public static String getMimeTypeFromUri(@NonNull Context context, @Nullable Uri uri) {
        if (!ListenerUtil.mutListener.listen(54011)) {
            if (uri != null) {
                ContentResolver contentResolver = context.getContentResolver();
                String type = contentResolver.getType(uri);
                if (!ListenerUtil.mutListener.listen(54010)) {
                    if ((ListenerUtil.mutListener.listen(54009) ? (TestUtil.empty(type) && MimeUtil.MIME_TYPE_DEFAULT.equals(type)) : (TestUtil.empty(type) || MimeUtil.MIME_TYPE_DEFAULT.equals(type)))) {
                        // path = FileUtil.getRealPathFromURI(context, uri);
                        String filename = FileUtil.getFilenameFromUri(contentResolver, uri);
                        return getMimeTypeFromPath(filename);
                    }
                }
                return type;
            }
        }
        return null;
    }

    private static boolean isMediaProviderSupported(Context context) {
        final PackageManager pm = context.getPackageManager();
        // Pick up provider with action string
        final Intent i = new Intent(DocumentsContract.PROVIDER_INTERFACE);
        final List<ResolveInfo> providers = pm.queryIntentContentProviders(i, 0);
        if (!ListenerUtil.mutListener.listen(54015)) {
            {
                long _loopCounter652 = 0;
                for (ResolveInfo info : providers) {
                    ListenerUtil.loopListener.listen("_loopCounter652", ++_loopCounter652);
                    if (!ListenerUtil.mutListener.listen(54014)) {
                        if ((ListenerUtil.mutListener.listen(54012) ? (info != null || info.providerInfo != null) : (info != null && info.providerInfo != null))) {
                            final String authority = info.providerInfo.authority;
                            if (!ListenerUtil.mutListener.listen(54013)) {
                                if (isMediaDocument(Uri.parse(ContentResolver.SCHEME_CONTENT + "://" + authority)))
                                    return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    /*
	* Some content uri returned by systemUI file picker create intermittent permission problems
	* To fix this, we convert it in a file uri
	 */
    public static Uri getFixedContentUri(Context context, Uri inUri) {
        if (!ListenerUtil.mutListener.listen(54025)) {
            if ((ListenerUtil.mutListener.listen(54020) ? (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(54019) ? (android.os.Build.VERSION.SDK_INT <= android.os.Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(54018) ? (android.os.Build.VERSION.SDK_INT > android.os.Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(54017) ? (android.os.Build.VERSION.SDK_INT != android.os.Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(54016) ? (android.os.Build.VERSION.SDK_INT == android.os.Build.VERSION_CODES.M) : (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.M))))))) {
                if (!ListenerUtil.mutListener.listen(54024)) {
                    if ((ListenerUtil.mutListener.listen(54021) ? (ContentResolver.SCHEME_CONTENT.equalsIgnoreCase(inUri.getScheme()) || inUri.toString().toUpperCase().contains("%3A")) : (ContentResolver.SCHEME_CONTENT.equalsIgnoreCase(inUri.getScheme()) && inUri.toString().toUpperCase().contains("%3A")))) {
                        String path = getRealPathFromURI(context, inUri);
                        if (!ListenerUtil.mutListener.listen(54023)) {
                            if (!TestUtil.empty(path)) {
                                File file = new File(path);
                                if (!ListenerUtil.mutListener.listen(54022)) {
                                    if (file.exists()) {
                                        return Uri.fromFile(file);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return inUri;
    }

    @Nullable
    public static String getRealPathFromURI(final Context context, final Uri uri) {
        if (!ListenerUtil.mutListener.listen(54037)) {
            // DocumentProvider
            if (DocumentsContract.isDocumentUri(context, uri)) {
                if (!ListenerUtil.mutListener.listen(54036)) {
                    // ExternalStorageProvider
                    if (isExternalStorageDocument(uri)) {
                        final String docId = DocumentsContract.getDocumentId(uri);
                        final String[] split = docId.split(":");
                        final String type = split[0];
                        if (!ListenerUtil.mutListener.listen(54035)) {
                            if ("primary".equalsIgnoreCase(type)) {
                                return Environment.getExternalStorageDirectory() + "/" + split[1];
                            }
                        }
                    } else // DownloadsProvider
                    if (isDownloadsDocument(uri)) {
                        final String id = DocumentsContract.getDocumentId(uri);
                        if (!ListenerUtil.mutListener.listen(54034)) {
                            if (id != null) {
                                if (!ListenerUtil.mutListener.listen(54033)) {
                                    if (id.startsWith("raw:/")) {
                                        return id.substring(4);
                                    } else {
                                        try {
                                            final Uri contentUri = ContentUris.withAppendedId(Uri.parse(ContentResolver.SCHEME_CONTENT + "://downloads/public_downloads"), Long.parseLong(id));
                                            return getDataColumn(context, contentUri, null, null);
                                        } catch (NumberFormatException e) {
                                            if (!ListenerUtil.mutListener.listen(54032)) {
                                                logger.info("Unable to extract document ID. Giving up.");
                                            }
                                        }
                                    }
                                }
                            } else {
                                if (!ListenerUtil.mutListener.listen(54031)) {
                                    logger.info("No document ID. Giving up.");
                                }
                            }
                        }
                    } else // MediaProvider
                    if (isMediaDocument(uri)) {
                        final String docId = DocumentsContract.getDocumentId(uri);
                        final String[] split = docId.split(":");
                        final String type = split[0];
                        Uri contentUri = null;
                        if (!ListenerUtil.mutListener.listen(54030)) {
                            if ("image".equals(type)) {
                                if (!ListenerUtil.mutListener.listen(54029)) {
                                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                                }
                            } else if ("video".equals(type)) {
                                if (!ListenerUtil.mutListener.listen(54028)) {
                                    contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                                }
                            } else if ("audio".equals(type)) {
                                if (!ListenerUtil.mutListener.listen(54027)) {
                                    contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                                }
                            }
                        }
                        final String selection = "_id=?";
                        final String[] selectionArgs = new String[] { split[1] };
                        return getDataColumn(context, contentUri, selection, selectionArgs);
                    }
                }
            } else if (ContentResolver.SCHEME_CONTENT.equalsIgnoreCase(uri.getScheme())) {
                if (!ListenerUtil.mutListener.listen(54026)) {
                    // Return the remote address
                    if (isGooglePhotosUri(uri)) {
                        return uri.getLastPathSegment();
                    }
                }
                return getDataColumn(context, uri, null, null);
            } else // File
            if (ContentResolver.SCHEME_FILE.equalsIgnoreCase(uri.getScheme())) {
                return uri.getPath();
            }
        }
        return null;
    }

    private static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    private static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    private static boolean isMediaDocument(final Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    private static boolean isGooglePhotosUri(Uri uri) {
        return "com.google.android.apps.photos.content".equals(uri.getAuthority());
    }

    @Nullable
    private static String getDataColumn(Context context, Uri uri, String selection, String[] selectionArgs) {
        String data = null;
        Cursor cursor = null;
        final String column = "_data";
        final String[] projection = { column };
        try {
            if (!ListenerUtil.mutListener.listen(54040)) {
                cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs, null);
            }
            if (!ListenerUtil.mutListener.listen(54043)) {
                if ((ListenerUtil.mutListener.listen(54041) ? (cursor != null || cursor.moveToFirst()) : (cursor != null && cursor.moveToFirst()))) {
                    final int column_index = cursor.getColumnIndexOrThrow(column);
                    if (!ListenerUtil.mutListener.listen(54042)) {
                        data = cursor.getString(column_index);
                    }
                }
            }
        } catch (Exception e) {
        } finally {
            if (!ListenerUtil.mutListener.listen(54039)) {
                if (cursor != null)
                    if (!ListenerUtil.mutListener.listen(54038)) {
                        cursor.close();
                    }
            }
        }
        return data;
    }

    public static boolean isAnimGif(ContentResolver contentResolver, Uri uri) {
        if (uri == null) {
            return false;
        }
        byte[] buffer = new byte[4];
        try (InputStream is = contentResolver.openInputStream(uri)) {
            if (!ListenerUtil.mutListener.listen(54045)) {
                is.read(buffer);
            }
            return isAnimGif(buffer);
        } catch (Exception x) {
            if (!ListenerUtil.mutListener.listen(54044)) {
                logger.error("Exception", x);
            }
            return false;
        }
    }

    private static boolean isAnimGif(byte[] buffer) {
        return (ListenerUtil.mutListener.listen(54075) ? ((ListenerUtil.mutListener.listen(54051) ? (buffer != null || (ListenerUtil.mutListener.listen(54050) ? (buffer.length <= 4) : (ListenerUtil.mutListener.listen(54049) ? (buffer.length > 4) : (ListenerUtil.mutListener.listen(54048) ? (buffer.length < 4) : (ListenerUtil.mutListener.listen(54047) ? (buffer.length != 4) : (ListenerUtil.mutListener.listen(54046) ? (buffer.length == 4) : (buffer.length >= 4))))))) : (buffer != null && (ListenerUtil.mutListener.listen(54050) ? (buffer.length <= 4) : (ListenerUtil.mutListener.listen(54049) ? (buffer.length > 4) : (ListenerUtil.mutListener.listen(54048) ? (buffer.length < 4) : (ListenerUtil.mutListener.listen(54047) ? (buffer.length != 4) : (ListenerUtil.mutListener.listen(54046) ? (buffer.length == 4) : (buffer.length >= 4)))))))) || ((ListenerUtil.mutListener.listen(54074) ? ((ListenerUtil.mutListener.listen(54068) ? ((ListenerUtil.mutListener.listen(54062) ? ((ListenerUtil.mutListener.listen(54056) ? (buffer[0] >= 0x47) : (ListenerUtil.mutListener.listen(54055) ? (buffer[0] <= 0x47) : (ListenerUtil.mutListener.listen(54054) ? (buffer[0] > 0x47) : (ListenerUtil.mutListener.listen(54053) ? (buffer[0] < 0x47) : (ListenerUtil.mutListener.listen(54052) ? (buffer[0] != 0x47) : (buffer[0] == 0x47)))))) || (ListenerUtil.mutListener.listen(54061) ? (buffer[1] >= 0x49) : (ListenerUtil.mutListener.listen(54060) ? (buffer[1] <= 0x49) : (ListenerUtil.mutListener.listen(54059) ? (buffer[1] > 0x49) : (ListenerUtil.mutListener.listen(54058) ? (buffer[1] < 0x49) : (ListenerUtil.mutListener.listen(54057) ? (buffer[1] != 0x49) : (buffer[1] == 0x49))))))) : ((ListenerUtil.mutListener.listen(54056) ? (buffer[0] >= 0x47) : (ListenerUtil.mutListener.listen(54055) ? (buffer[0] <= 0x47) : (ListenerUtil.mutListener.listen(54054) ? (buffer[0] > 0x47) : (ListenerUtil.mutListener.listen(54053) ? (buffer[0] < 0x47) : (ListenerUtil.mutListener.listen(54052) ? (buffer[0] != 0x47) : (buffer[0] == 0x47)))))) && (ListenerUtil.mutListener.listen(54061) ? (buffer[1] >= 0x49) : (ListenerUtil.mutListener.listen(54060) ? (buffer[1] <= 0x49) : (ListenerUtil.mutListener.listen(54059) ? (buffer[1] > 0x49) : (ListenerUtil.mutListener.listen(54058) ? (buffer[1] < 0x49) : (ListenerUtil.mutListener.listen(54057) ? (buffer[1] != 0x49) : (buffer[1] == 0x49)))))))) || (ListenerUtil.mutListener.listen(54067) ? (buffer[2] >= 0x46) : (ListenerUtil.mutListener.listen(54066) ? (buffer[2] <= 0x46) : (ListenerUtil.mutListener.listen(54065) ? (buffer[2] > 0x46) : (ListenerUtil.mutListener.listen(54064) ? (buffer[2] < 0x46) : (ListenerUtil.mutListener.listen(54063) ? (buffer[2] != 0x46) : (buffer[2] == 0x46))))))) : ((ListenerUtil.mutListener.listen(54062) ? ((ListenerUtil.mutListener.listen(54056) ? (buffer[0] >= 0x47) : (ListenerUtil.mutListener.listen(54055) ? (buffer[0] <= 0x47) : (ListenerUtil.mutListener.listen(54054) ? (buffer[0] > 0x47) : (ListenerUtil.mutListener.listen(54053) ? (buffer[0] < 0x47) : (ListenerUtil.mutListener.listen(54052) ? (buffer[0] != 0x47) : (buffer[0] == 0x47)))))) || (ListenerUtil.mutListener.listen(54061) ? (buffer[1] >= 0x49) : (ListenerUtil.mutListener.listen(54060) ? (buffer[1] <= 0x49) : (ListenerUtil.mutListener.listen(54059) ? (buffer[1] > 0x49) : (ListenerUtil.mutListener.listen(54058) ? (buffer[1] < 0x49) : (ListenerUtil.mutListener.listen(54057) ? (buffer[1] != 0x49) : (buffer[1] == 0x49))))))) : ((ListenerUtil.mutListener.listen(54056) ? (buffer[0] >= 0x47) : (ListenerUtil.mutListener.listen(54055) ? (buffer[0] <= 0x47) : (ListenerUtil.mutListener.listen(54054) ? (buffer[0] > 0x47) : (ListenerUtil.mutListener.listen(54053) ? (buffer[0] < 0x47) : (ListenerUtil.mutListener.listen(54052) ? (buffer[0] != 0x47) : (buffer[0] == 0x47)))))) && (ListenerUtil.mutListener.listen(54061) ? (buffer[1] >= 0x49) : (ListenerUtil.mutListener.listen(54060) ? (buffer[1] <= 0x49) : (ListenerUtil.mutListener.listen(54059) ? (buffer[1] > 0x49) : (ListenerUtil.mutListener.listen(54058) ? (buffer[1] < 0x49) : (ListenerUtil.mutListener.listen(54057) ? (buffer[1] != 0x49) : (buffer[1] == 0x49)))))))) && (ListenerUtil.mutListener.listen(54067) ? (buffer[2] >= 0x46) : (ListenerUtil.mutListener.listen(54066) ? (buffer[2] <= 0x46) : (ListenerUtil.mutListener.listen(54065) ? (buffer[2] > 0x46) : (ListenerUtil.mutListener.listen(54064) ? (buffer[2] < 0x46) : (ListenerUtil.mutListener.listen(54063) ? (buffer[2] != 0x46) : (buffer[2] == 0x46)))))))) || (ListenerUtil.mutListener.listen(54073) ? (buffer[3] >= 0x38) : (ListenerUtil.mutListener.listen(54072) ? (buffer[3] <= 0x38) : (ListenerUtil.mutListener.listen(54071) ? (buffer[3] > 0x38) : (ListenerUtil.mutListener.listen(54070) ? (buffer[3] < 0x38) : (ListenerUtil.mutListener.listen(54069) ? (buffer[3] != 0x38) : (buffer[3] == 0x38))))))) : ((ListenerUtil.mutListener.listen(54068) ? ((ListenerUtil.mutListener.listen(54062) ? ((ListenerUtil.mutListener.listen(54056) ? (buffer[0] >= 0x47) : (ListenerUtil.mutListener.listen(54055) ? (buffer[0] <= 0x47) : (ListenerUtil.mutListener.listen(54054) ? (buffer[0] > 0x47) : (ListenerUtil.mutListener.listen(54053) ? (buffer[0] < 0x47) : (ListenerUtil.mutListener.listen(54052) ? (buffer[0] != 0x47) : (buffer[0] == 0x47)))))) || (ListenerUtil.mutListener.listen(54061) ? (buffer[1] >= 0x49) : (ListenerUtil.mutListener.listen(54060) ? (buffer[1] <= 0x49) : (ListenerUtil.mutListener.listen(54059) ? (buffer[1] > 0x49) : (ListenerUtil.mutListener.listen(54058) ? (buffer[1] < 0x49) : (ListenerUtil.mutListener.listen(54057) ? (buffer[1] != 0x49) : (buffer[1] == 0x49))))))) : ((ListenerUtil.mutListener.listen(54056) ? (buffer[0] >= 0x47) : (ListenerUtil.mutListener.listen(54055) ? (buffer[0] <= 0x47) : (ListenerUtil.mutListener.listen(54054) ? (buffer[0] > 0x47) : (ListenerUtil.mutListener.listen(54053) ? (buffer[0] < 0x47) : (ListenerUtil.mutListener.listen(54052) ? (buffer[0] != 0x47) : (buffer[0] == 0x47)))))) && (ListenerUtil.mutListener.listen(54061) ? (buffer[1] >= 0x49) : (ListenerUtil.mutListener.listen(54060) ? (buffer[1] <= 0x49) : (ListenerUtil.mutListener.listen(54059) ? (buffer[1] > 0x49) : (ListenerUtil.mutListener.listen(54058) ? (buffer[1] < 0x49) : (ListenerUtil.mutListener.listen(54057) ? (buffer[1] != 0x49) : (buffer[1] == 0x49)))))))) || (ListenerUtil.mutListener.listen(54067) ? (buffer[2] >= 0x46) : (ListenerUtil.mutListener.listen(54066) ? (buffer[2] <= 0x46) : (ListenerUtil.mutListener.listen(54065) ? (buffer[2] > 0x46) : (ListenerUtil.mutListener.listen(54064) ? (buffer[2] < 0x46) : (ListenerUtil.mutListener.listen(54063) ? (buffer[2] != 0x46) : (buffer[2] == 0x46))))))) : ((ListenerUtil.mutListener.listen(54062) ? ((ListenerUtil.mutListener.listen(54056) ? (buffer[0] >= 0x47) : (ListenerUtil.mutListener.listen(54055) ? (buffer[0] <= 0x47) : (ListenerUtil.mutListener.listen(54054) ? (buffer[0] > 0x47) : (ListenerUtil.mutListener.listen(54053) ? (buffer[0] < 0x47) : (ListenerUtil.mutListener.listen(54052) ? (buffer[0] != 0x47) : (buffer[0] == 0x47)))))) || (ListenerUtil.mutListener.listen(54061) ? (buffer[1] >= 0x49) : (ListenerUtil.mutListener.listen(54060) ? (buffer[1] <= 0x49) : (ListenerUtil.mutListener.listen(54059) ? (buffer[1] > 0x49) : (ListenerUtil.mutListener.listen(54058) ? (buffer[1] < 0x49) : (ListenerUtil.mutListener.listen(54057) ? (buffer[1] != 0x49) : (buffer[1] == 0x49))))))) : ((ListenerUtil.mutListener.listen(54056) ? (buffer[0] >= 0x47) : (ListenerUtil.mutListener.listen(54055) ? (buffer[0] <= 0x47) : (ListenerUtil.mutListener.listen(54054) ? (buffer[0] > 0x47) : (ListenerUtil.mutListener.listen(54053) ? (buffer[0] < 0x47) : (ListenerUtil.mutListener.listen(54052) ? (buffer[0] != 0x47) : (buffer[0] == 0x47)))))) && (ListenerUtil.mutListener.listen(54061) ? (buffer[1] >= 0x49) : (ListenerUtil.mutListener.listen(54060) ? (buffer[1] <= 0x49) : (ListenerUtil.mutListener.listen(54059) ? (buffer[1] > 0x49) : (ListenerUtil.mutListener.listen(54058) ? (buffer[1] < 0x49) : (ListenerUtil.mutListener.listen(54057) ? (buffer[1] != 0x49) : (buffer[1] == 0x49)))))))) && (ListenerUtil.mutListener.listen(54067) ? (buffer[2] >= 0x46) : (ListenerUtil.mutListener.listen(54066) ? (buffer[2] <= 0x46) : (ListenerUtil.mutListener.listen(54065) ? (buffer[2] > 0x46) : (ListenerUtil.mutListener.listen(54064) ? (buffer[2] < 0x46) : (ListenerUtil.mutListener.listen(54063) ? (buffer[2] != 0x46) : (buffer[2] == 0x46)))))))) && (ListenerUtil.mutListener.listen(54073) ? (buffer[3] >= 0x38) : (ListenerUtil.mutListener.listen(54072) ? (buffer[3] <= 0x38) : (ListenerUtil.mutListener.listen(54071) ? (buffer[3] > 0x38) : (ListenerUtil.mutListener.listen(54070) ? (buffer[3] < 0x38) : (ListenerUtil.mutListener.listen(54069) ? (buffer[3] != 0x38) : (buffer[3] == 0x38)))))))))) : ((ListenerUtil.mutListener.listen(54051) ? (buffer != null || (ListenerUtil.mutListener.listen(54050) ? (buffer.length <= 4) : (ListenerUtil.mutListener.listen(54049) ? (buffer.length > 4) : (ListenerUtil.mutListener.listen(54048) ? (buffer.length < 4) : (ListenerUtil.mutListener.listen(54047) ? (buffer.length != 4) : (ListenerUtil.mutListener.listen(54046) ? (buffer.length == 4) : (buffer.length >= 4))))))) : (buffer != null && (ListenerUtil.mutListener.listen(54050) ? (buffer.length <= 4) : (ListenerUtil.mutListener.listen(54049) ? (buffer.length > 4) : (ListenerUtil.mutListener.listen(54048) ? (buffer.length < 4) : (ListenerUtil.mutListener.listen(54047) ? (buffer.length != 4) : (ListenerUtil.mutListener.listen(54046) ? (buffer.length == 4) : (buffer.length >= 4)))))))) && ((ListenerUtil.mutListener.listen(54074) ? ((ListenerUtil.mutListener.listen(54068) ? ((ListenerUtil.mutListener.listen(54062) ? ((ListenerUtil.mutListener.listen(54056) ? (buffer[0] >= 0x47) : (ListenerUtil.mutListener.listen(54055) ? (buffer[0] <= 0x47) : (ListenerUtil.mutListener.listen(54054) ? (buffer[0] > 0x47) : (ListenerUtil.mutListener.listen(54053) ? (buffer[0] < 0x47) : (ListenerUtil.mutListener.listen(54052) ? (buffer[0] != 0x47) : (buffer[0] == 0x47)))))) || (ListenerUtil.mutListener.listen(54061) ? (buffer[1] >= 0x49) : (ListenerUtil.mutListener.listen(54060) ? (buffer[1] <= 0x49) : (ListenerUtil.mutListener.listen(54059) ? (buffer[1] > 0x49) : (ListenerUtil.mutListener.listen(54058) ? (buffer[1] < 0x49) : (ListenerUtil.mutListener.listen(54057) ? (buffer[1] != 0x49) : (buffer[1] == 0x49))))))) : ((ListenerUtil.mutListener.listen(54056) ? (buffer[0] >= 0x47) : (ListenerUtil.mutListener.listen(54055) ? (buffer[0] <= 0x47) : (ListenerUtil.mutListener.listen(54054) ? (buffer[0] > 0x47) : (ListenerUtil.mutListener.listen(54053) ? (buffer[0] < 0x47) : (ListenerUtil.mutListener.listen(54052) ? (buffer[0] != 0x47) : (buffer[0] == 0x47)))))) && (ListenerUtil.mutListener.listen(54061) ? (buffer[1] >= 0x49) : (ListenerUtil.mutListener.listen(54060) ? (buffer[1] <= 0x49) : (ListenerUtil.mutListener.listen(54059) ? (buffer[1] > 0x49) : (ListenerUtil.mutListener.listen(54058) ? (buffer[1] < 0x49) : (ListenerUtil.mutListener.listen(54057) ? (buffer[1] != 0x49) : (buffer[1] == 0x49)))))))) || (ListenerUtil.mutListener.listen(54067) ? (buffer[2] >= 0x46) : (ListenerUtil.mutListener.listen(54066) ? (buffer[2] <= 0x46) : (ListenerUtil.mutListener.listen(54065) ? (buffer[2] > 0x46) : (ListenerUtil.mutListener.listen(54064) ? (buffer[2] < 0x46) : (ListenerUtil.mutListener.listen(54063) ? (buffer[2] != 0x46) : (buffer[2] == 0x46))))))) : ((ListenerUtil.mutListener.listen(54062) ? ((ListenerUtil.mutListener.listen(54056) ? (buffer[0] >= 0x47) : (ListenerUtil.mutListener.listen(54055) ? (buffer[0] <= 0x47) : (ListenerUtil.mutListener.listen(54054) ? (buffer[0] > 0x47) : (ListenerUtil.mutListener.listen(54053) ? (buffer[0] < 0x47) : (ListenerUtil.mutListener.listen(54052) ? (buffer[0] != 0x47) : (buffer[0] == 0x47)))))) || (ListenerUtil.mutListener.listen(54061) ? (buffer[1] >= 0x49) : (ListenerUtil.mutListener.listen(54060) ? (buffer[1] <= 0x49) : (ListenerUtil.mutListener.listen(54059) ? (buffer[1] > 0x49) : (ListenerUtil.mutListener.listen(54058) ? (buffer[1] < 0x49) : (ListenerUtil.mutListener.listen(54057) ? (buffer[1] != 0x49) : (buffer[1] == 0x49))))))) : ((ListenerUtil.mutListener.listen(54056) ? (buffer[0] >= 0x47) : (ListenerUtil.mutListener.listen(54055) ? (buffer[0] <= 0x47) : (ListenerUtil.mutListener.listen(54054) ? (buffer[0] > 0x47) : (ListenerUtil.mutListener.listen(54053) ? (buffer[0] < 0x47) : (ListenerUtil.mutListener.listen(54052) ? (buffer[0] != 0x47) : (buffer[0] == 0x47)))))) && (ListenerUtil.mutListener.listen(54061) ? (buffer[1] >= 0x49) : (ListenerUtil.mutListener.listen(54060) ? (buffer[1] <= 0x49) : (ListenerUtil.mutListener.listen(54059) ? (buffer[1] > 0x49) : (ListenerUtil.mutListener.listen(54058) ? (buffer[1] < 0x49) : (ListenerUtil.mutListener.listen(54057) ? (buffer[1] != 0x49) : (buffer[1] == 0x49)))))))) && (ListenerUtil.mutListener.listen(54067) ? (buffer[2] >= 0x46) : (ListenerUtil.mutListener.listen(54066) ? (buffer[2] <= 0x46) : (ListenerUtil.mutListener.listen(54065) ? (buffer[2] > 0x46) : (ListenerUtil.mutListener.listen(54064) ? (buffer[2] < 0x46) : (ListenerUtil.mutListener.listen(54063) ? (buffer[2] != 0x46) : (buffer[2] == 0x46)))))))) || (ListenerUtil.mutListener.listen(54073) ? (buffer[3] >= 0x38) : (ListenerUtil.mutListener.listen(54072) ? (buffer[3] <= 0x38) : (ListenerUtil.mutListener.listen(54071) ? (buffer[3] > 0x38) : (ListenerUtil.mutListener.listen(54070) ? (buffer[3] < 0x38) : (ListenerUtil.mutListener.listen(54069) ? (buffer[3] != 0x38) : (buffer[3] == 0x38))))))) : ((ListenerUtil.mutListener.listen(54068) ? ((ListenerUtil.mutListener.listen(54062) ? ((ListenerUtil.mutListener.listen(54056) ? (buffer[0] >= 0x47) : (ListenerUtil.mutListener.listen(54055) ? (buffer[0] <= 0x47) : (ListenerUtil.mutListener.listen(54054) ? (buffer[0] > 0x47) : (ListenerUtil.mutListener.listen(54053) ? (buffer[0] < 0x47) : (ListenerUtil.mutListener.listen(54052) ? (buffer[0] != 0x47) : (buffer[0] == 0x47)))))) || (ListenerUtil.mutListener.listen(54061) ? (buffer[1] >= 0x49) : (ListenerUtil.mutListener.listen(54060) ? (buffer[1] <= 0x49) : (ListenerUtil.mutListener.listen(54059) ? (buffer[1] > 0x49) : (ListenerUtil.mutListener.listen(54058) ? (buffer[1] < 0x49) : (ListenerUtil.mutListener.listen(54057) ? (buffer[1] != 0x49) : (buffer[1] == 0x49))))))) : ((ListenerUtil.mutListener.listen(54056) ? (buffer[0] >= 0x47) : (ListenerUtil.mutListener.listen(54055) ? (buffer[0] <= 0x47) : (ListenerUtil.mutListener.listen(54054) ? (buffer[0] > 0x47) : (ListenerUtil.mutListener.listen(54053) ? (buffer[0] < 0x47) : (ListenerUtil.mutListener.listen(54052) ? (buffer[0] != 0x47) : (buffer[0] == 0x47)))))) && (ListenerUtil.mutListener.listen(54061) ? (buffer[1] >= 0x49) : (ListenerUtil.mutListener.listen(54060) ? (buffer[1] <= 0x49) : (ListenerUtil.mutListener.listen(54059) ? (buffer[1] > 0x49) : (ListenerUtil.mutListener.listen(54058) ? (buffer[1] < 0x49) : (ListenerUtil.mutListener.listen(54057) ? (buffer[1] != 0x49) : (buffer[1] == 0x49)))))))) || (ListenerUtil.mutListener.listen(54067) ? (buffer[2] >= 0x46) : (ListenerUtil.mutListener.listen(54066) ? (buffer[2] <= 0x46) : (ListenerUtil.mutListener.listen(54065) ? (buffer[2] > 0x46) : (ListenerUtil.mutListener.listen(54064) ? (buffer[2] < 0x46) : (ListenerUtil.mutListener.listen(54063) ? (buffer[2] != 0x46) : (buffer[2] == 0x46))))))) : ((ListenerUtil.mutListener.listen(54062) ? ((ListenerUtil.mutListener.listen(54056) ? (buffer[0] >= 0x47) : (ListenerUtil.mutListener.listen(54055) ? (buffer[0] <= 0x47) : (ListenerUtil.mutListener.listen(54054) ? (buffer[0] > 0x47) : (ListenerUtil.mutListener.listen(54053) ? (buffer[0] < 0x47) : (ListenerUtil.mutListener.listen(54052) ? (buffer[0] != 0x47) : (buffer[0] == 0x47)))))) || (ListenerUtil.mutListener.listen(54061) ? (buffer[1] >= 0x49) : (ListenerUtil.mutListener.listen(54060) ? (buffer[1] <= 0x49) : (ListenerUtil.mutListener.listen(54059) ? (buffer[1] > 0x49) : (ListenerUtil.mutListener.listen(54058) ? (buffer[1] < 0x49) : (ListenerUtil.mutListener.listen(54057) ? (buffer[1] != 0x49) : (buffer[1] == 0x49))))))) : ((ListenerUtil.mutListener.listen(54056) ? (buffer[0] >= 0x47) : (ListenerUtil.mutListener.listen(54055) ? (buffer[0] <= 0x47) : (ListenerUtil.mutListener.listen(54054) ? (buffer[0] > 0x47) : (ListenerUtil.mutListener.listen(54053) ? (buffer[0] < 0x47) : (ListenerUtil.mutListener.listen(54052) ? (buffer[0] != 0x47) : (buffer[0] == 0x47)))))) && (ListenerUtil.mutListener.listen(54061) ? (buffer[1] >= 0x49) : (ListenerUtil.mutListener.listen(54060) ? (buffer[1] <= 0x49) : (ListenerUtil.mutListener.listen(54059) ? (buffer[1] > 0x49) : (ListenerUtil.mutListener.listen(54058) ? (buffer[1] < 0x49) : (ListenerUtil.mutListener.listen(54057) ? (buffer[1] != 0x49) : (buffer[1] == 0x49)))))))) && (ListenerUtil.mutListener.listen(54067) ? (buffer[2] >= 0x46) : (ListenerUtil.mutListener.listen(54066) ? (buffer[2] <= 0x46) : (ListenerUtil.mutListener.listen(54065) ? (buffer[2] > 0x46) : (ListenerUtil.mutListener.listen(54064) ? (buffer[2] < 0x46) : (ListenerUtil.mutListener.listen(54063) ? (buffer[2] != 0x46) : (buffer[2] == 0x46)))))))) && (ListenerUtil.mutListener.listen(54073) ? (buffer[3] >= 0x38) : (ListenerUtil.mutListener.listen(54072) ? (buffer[3] <= 0x38) : (ListenerUtil.mutListener.listen(54071) ? (buffer[3] > 0x38) : (ListenerUtil.mutListener.listen(54070) ? (buffer[3] < 0x38) : (ListenerUtil.mutListener.listen(54069) ? (buffer[3] != 0x38) : (buffer[3] == 0x38)))))))))));
    }

    public static boolean isImageFile(FileDataModel fileDataModel) {
        return (ListenerUtil.mutListener.listen(54076) ? (fileDataModel != null || (MimeUtil.isImageFile(fileDataModel.getMimeType()))) : (fileDataModel != null && (MimeUtil.isImageFile(fileDataModel.getMimeType()))));
    }

    public static boolean isVideoFile(FileDataModel fileDataModel) {
        return (ListenerUtil.mutListener.listen(54077) ? (fileDataModel != null || (MimeUtil.isVideoFile(fileDataModel.getMimeType()))) : (fileDataModel != null && (MimeUtil.isVideoFile(fileDataModel.getMimeType()))));
    }

    public static boolean isAudioFile(FileDataModel fileDataModel) {
        return (ListenerUtil.mutListener.listen(54078) ? (fileDataModel != null || (MimeUtil.isAudioFile(fileDataModel.getMimeType()))) : (fileDataModel != null && (MimeUtil.isAudioFile(fileDataModel.getMimeType()))));
    }

    public static String getFileMessageDatePrefix(Context context, AbstractMessageModel messageModel, String fileType) {
        if ((ListenerUtil.mutListener.listen(54079) ? (messageModel.getFileData() == null && messageModel.getFileData().getFileSize() == 0) : (messageModel.getFileData() == null || messageModel.getFileData().getFileSize() == 0))) {
            return "";
        }
        if (messageModel.getFileData().isDownloaded()) {
            return "";
        }
        if (fileType != null) {
            String datePrefixString = Formatter.formatShortFileSize(context, messageModel.getFileData().getFileSize());
            if (!ListenerUtil.mutListener.listen(54082)) {
                if (messageModel.isOutbox()) {
                    if (!ListenerUtil.mutListener.listen(54081)) {
                        datePrefixString = fileType + " | " + datePrefixString;
                    }
                } else {
                    if (!ListenerUtil.mutListener.listen(54080)) {
                        datePrefixString += " | " + fileType;
                    }
                }
            }
            return datePrefixString;
        } else {
            return Formatter.formatShortFileSize(context, messageModel.getFileData().getFileSize());
        }
    }

    @NonNull
    public static String getMediaFilenamePrefix(@NonNull AbstractMessageModel messageModel) {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd-HHmmss", Locale.getDefault());
        return "threema-" + format.format(messageModel.getCreatedAt()) + "-" + messageModel.getApiMessageId();
    }

    @NonNull
    public static String getMediaFilenamePrefix() {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd-HHmmssSSS", Locale.getDefault());
        return "threema-" + format.format(System.currentTimeMillis());
    }

    /**
     *  Return a default filename keeping in account specified mime type
     *  @param mimeType the mime type to generate a filename for
     *  @return a filename with an extension
     */
    @NonNull
    public static String getDefaultFilename(@Nullable String mimeType) {
        if (!ListenerUtil.mutListener.listen(54084)) {
            if (TestUtil.empty(mimeType)) {
                if (!ListenerUtil.mutListener.listen(54083)) {
                    mimeType = MimeUtil.MIME_TYPE_DEFAULT;
                }
            }
        }
        String extension = MimeTypeMap.getSingleton().getExtensionFromMimeType(mimeType);
        return getMediaFilenamePrefix() + "." + extension;
    }

    public static String sanitizeFileName(String filename) {
        if (!ListenerUtil.mutListener.listen(54085)) {
            if (!TestUtil.empty(filename)) {
                return filename.replaceAll("[:/*\"?|<>' ]", "_");
            }
        }
        return null;
    }

    @WorkerThread
    public static boolean copyFile(@NonNull File source, @NonNull File dest) {
        try (InputStream inputStream = new FileInputStream(source);
            OutputStream outputStream = new FileOutputStream(dest)) {
            if (!ListenerUtil.mutListener.listen(54087)) {
                IOUtils.copy(inputStream, outputStream);
            }
            return true;
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(54086)) {
                logger.error("Exception", e);
            }
        }
        return false;
    }

    @WorkerThread
    public static boolean copyFile(@NonNull Uri source, @NonNull File dest, @NonNull ContentResolver contentResolver) {
        try (InputStream inputStream = contentResolver.openInputStream(source);
            OutputStream outputStream = new FileOutputStream(dest)) {
            if (!ListenerUtil.mutListener.listen(54090)) {
                if (inputStream != null) {
                    if (!ListenerUtil.mutListener.listen(54089)) {
                        IOUtils.copy(inputStream, outputStream);
                    }
                    return true;
                }
            }
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(54088)) {
                logger.error("Exception", e);
            }
        }
        return false;
    }

    /**
     *  Attempt to delete a file. If deleting fails, log a warning using the specified logger.
     *
     *  Note: Do not use this if error recovery is important!
     *
     *  @param file The file that should be deleted
     *  @param description The description of the file (e.g. "message queue database")
     *  @param logger The logger to use
     */
    public static void deleteFileOrWarn(@NonNull File file, @Nullable String description, @NonNull Logger logger) {
        if (!ListenerUtil.mutListener.listen(54092)) {
            if (!file.delete()) {
                if (!ListenerUtil.mutListener.listen(54091)) {
                    logger.warn("Could not delete {}", description);
                }
            }
        }
    }

    /**
     *  See {@link #deleteFileOrWarn(File, String, Logger)}
     */
    public static void deleteFileOrWarn(@NonNull String path, @Nullable String description, @NonNull Logger logger) {
        if (!ListenerUtil.mutListener.listen(54093)) {
            FileUtil.deleteFileOrWarn(new File(path), description, logger);
        }
    }

    /**
     *  Create a new file or re-use existing file. Log if file already exists.
     *  @param file The file that should be created or re-used
     *  @param logger The logger facility to use
     */
    public static void createNewFileOrLog(@NonNull File file, @NonNull Logger logger) throws IOException {
        if (!ListenerUtil.mutListener.listen(54095)) {
            if (!file.createNewFile()) {
                if (!ListenerUtil.mutListener.listen(54094)) {
                    logger.debug("File {} already exists", file.getAbsolutePath());
                }
            }
        }
    }

    /**
     *  Try to generated a File with the given filename in the given path
     *  If a file of the same name exists, add a number to the filename (possibly between name and extension)
     *  @param destPath Destination path
     *  @param destFilename Desired filename
     *  @return File object
     */
    public static File getUniqueFile(String destPath, String destFilename) {
        File destFile = new File(destPath, destFilename);
        String extension = MimeTypeMap.getFileExtensionFromUrl(destFilename);
        if (!ListenerUtil.mutListener.listen(54097)) {
            if (!TestUtil.empty(extension)) {
                if (!ListenerUtil.mutListener.listen(54096)) {
                    extension = "." + extension;
                }
            }
        }
        String filePart = destFilename.substring(0, (ListenerUtil.mutListener.listen(54101) ? (destFilename.length() % extension.length()) : (ListenerUtil.mutListener.listen(54100) ? (destFilename.length() / extension.length()) : (ListenerUtil.mutListener.listen(54099) ? (destFilename.length() * extension.length()) : (ListenerUtil.mutListener.listen(54098) ? (destFilename.length() + extension.length()) : (destFilename.length() - extension.length()))))));
        int i = 0;
        if (!ListenerUtil.mutListener.listen(54105)) {
            {
                long _loopCounter653 = 0;
                while (destFile.exists()) {
                    ListenerUtil.loopListener.listen("_loopCounter653", ++_loopCounter653);
                    if (!ListenerUtil.mutListener.listen(54102)) {
                        i++;
                    }
                    if (!ListenerUtil.mutListener.listen(54103)) {
                        destFile = new File(destPath, filePart + " (" + i + ")" + extension);
                    }
                    if (!ListenerUtil.mutListener.listen(54104)) {
                        if (!destFile.exists()) {
                            break;
                        }
                    }
                }
            }
        }
        return destFile;
    }

    /**
     *  Returns the filename of the object referred to by mediaItem. If no filename can be found, generate one
     *  @param contentResolver ContentResolver
     *  @param mediaItem MediaItem representing the source file
     *  @return A filename
     */
    @NonNull
    public static String getFilenameFromUri(@NonNull ContentResolver contentResolver, @NonNull MediaItem mediaItem) {
        String filename = getFilenameFromUri(contentResolver, mediaItem.getUri());
        if (!ListenerUtil.mutListener.listen(54107)) {
            if (TextUtils.isEmpty(filename)) {
                if (!ListenerUtil.mutListener.listen(54106)) {
                    filename = getDefaultFilename(mediaItem.getMimeType());
                }
            }
        }
        return filename;
    }

    /**
     *  Returns the filename of the object referred to by uri by querying the content resolver
     *  @param contentResolver ContentResolver
     *  @param uri Uri pointing at the object
     *  @return A filename or null if none is found
     */
    @Nullable
    public static String getFilenameFromUri(ContentResolver contentResolver, Uri uri) {
        String filename = null;
        if (!ListenerUtil.mutListener.listen(54113)) {
            if (ContentResolver.SCHEME_FILE.equalsIgnoreCase(uri.getScheme())) {
                if (!ListenerUtil.mutListener.listen(54112)) {
                    filename = uri.getLastPathSegment();
                }
            } else {
                try (final Cursor cursor = contentResolver.query(uri, null, null, null, null)) {
                    if (!ListenerUtil.mutListener.listen(54111)) {
                        if ((ListenerUtil.mutListener.listen(54109) ? (cursor != null || cursor.moveToNext()) : (cursor != null && cursor.moveToNext()))) {
                            if (!ListenerUtil.mutListener.listen(54110)) {
                                filename = cursor.getString(cursor.getColumnIndex(MediaStore.MediaColumns.DISPLAY_NAME));
                            }
                        }
                    }
                } catch (Exception e) {
                    if (!ListenerUtil.mutListener.listen(54108)) {
                        logger.error("Unable to query Content Resolver", e);
                    }
                }
            }
        }
        return filename;
    }

    /**
     *  Try to get a file uri from a content uri to maintain access to a file across two activities.
     *  NOTE: This hack will probably stop working in API 30
     *  @param uri content uri to resolve
     *  @return file uri, if a file path could be resolved
     */
    public static Uri getFileUri(Uri uri) {
        String path = FileUtil.getRealPathFromURI(ThreemaApplication.getAppContext(), uri);
        if (!ListenerUtil.mutListener.listen(54115)) {
            if (path != null) {
                File file = new File(path);
                if (!ListenerUtil.mutListener.listen(54114)) {
                    if (file.canRead()) {
                        return Uri.fromFile(file);
                    }
                }
            }
        }
        return uri;
    }

    /**
     *  Select a file from a gallery app. Shows a selector first to allow for choosing the desired gallery app or SystemUIs file picker.
     *  Does not necessarily need file permissions as a modern gallery app will return a content Uri with a temporary permission to access the file
     *  @param activity Activity where the result of the selection should end up
     *  @param fragment Fragment where the result of the selection should end up
     *  @param requestCode Request code to use for result
     *  @param includeVideo Whether to include the possibility to select video files (if supported by app)
     */
    public static void selectFromGallery(@Nullable Activity activity, @Nullable Fragment fragment, int requestCode, boolean includeVideo) {
        if (!ListenerUtil.mutListener.listen(54117)) {
            if (activity == null) {
                if (!ListenerUtil.mutListener.listen(54116)) {
                    activity = fragment.getActivity();
                }
            }
        }
        try {
            Intent startIntent;
            Intent getContentIntent = new Intent();
            if (!ListenerUtil.mutListener.listen(54120)) {
                getContentIntent.setType(includeVideo ? MimeUtil.MIME_TYPE_VIDEO : MimeUtil.MIME_TYPE_IMAGE);
            }
            if (!ListenerUtil.mutListener.listen(54121)) {
                getContentIntent.setAction(Intent.ACTION_GET_CONTENT);
            }
            if (!ListenerUtil.mutListener.listen(54122)) {
                getContentIntent.addCategory(Intent.CATEGORY_OPENABLE);
            }
            if (!ListenerUtil.mutListener.listen(54123)) {
                getContentIntent.addFlags(Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION | Intent.FLAG_GRANT_READ_URI_PERMISSION);
            }
            if (!ListenerUtil.mutListener.listen(54124)) {
                getContentIntent.putExtra(MediaStore.EXTRA_SIZE_LIMIT, MAX_BLOB_SIZE);
            }
            if (includeVideo) {
                Intent pickIntent = new Intent(Intent.ACTION_PICK);
                if (!ListenerUtil.mutListener.listen(54125)) {
                    pickIntent.setType(MimeUtil.MIME_TYPE_IMAGE);
                }
                if (ConfigUtils.isXiaomiDevice()) {
                    startIntent = getContentIntent;
                } else {
                    startIntent = Intent.createChooser(pickIntent, activity.getString(R.string.select_from_gallery));
                    if (!ListenerUtil.mutListener.listen(54126)) {
                        startIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, new Intent[] { getContentIntent });
                    }
                }
            } else {
                startIntent = getContentIntent;
            }
            if (!ListenerUtil.mutListener.listen(54129)) {
                if (fragment != null) {
                    if (!ListenerUtil.mutListener.listen(54128)) {
                        fragment.startActivityForResult(startIntent, requestCode);
                    }
                } else {
                    if (!ListenerUtil.mutListener.listen(54127)) {
                        activity.startActivityForResult(startIntent, requestCode);
                    }
                }
            }
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(54118)) {
                logger.debug("Exception", e);
            }
            if (!ListenerUtil.mutListener.listen(54119)) {
                Toast.makeText(activity, R.string.no_activity_for_mime_type, Toast.LENGTH_SHORT).show();
            }
        }
    }
}
