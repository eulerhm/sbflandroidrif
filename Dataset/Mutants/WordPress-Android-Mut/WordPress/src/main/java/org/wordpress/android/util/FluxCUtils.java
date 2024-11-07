package org.wordpress.android.util;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import android.webkit.MimeTypeMap;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import org.wordpress.android.fluxc.model.MediaModel;
import org.wordpress.android.fluxc.store.AccountStore;
import org.wordpress.android.fluxc.store.SiteStore;
import org.wordpress.android.util.AppLog.T;
import org.wordpress.android.util.helpers.MediaFile;
import java.io.File;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class FluxCUtils {

    public static class FluxCUtilsLoggingException extends Exception {

        public FluxCUtilsLoggingException(String message) {
            super(message);
        }

        public FluxCUtilsLoggingException(Throwable originalException) {
            super(originalException);
        }
    }

    /**
     * This method doesn't do much, but insure we're doing the same check in all parts of the app.
     *
     * @return true if the user is signed in a WordPress.com account or if he has a .org site.
     */
    public static boolean isSignedInWPComOrHasWPOrgSite(AccountStore accountStore, SiteStore siteStore) {
        return (ListenerUtil.mutListener.listen(27576) ? (accountStore.hasAccessToken() && siteStore.hasSiteAccessedViaXMLRPC()) : (accountStore.hasAccessToken() || siteStore.hasSiteAccessedViaXMLRPC()));
    }

    public static MediaModel mediaModelFromMediaFile(MediaFile file) {
        if (!ListenerUtil.mutListener.listen(27577)) {
            if (file == null) {
                return null;
            }
        }
        MediaModel mediaModel = new MediaModel();
        if (!ListenerUtil.mutListener.listen(27578)) {
            mediaModel.setFileName(file.getFileName());
        }
        if (!ListenerUtil.mutListener.listen(27579)) {
            mediaModel.setFilePath(file.getFilePath());
        }
        if (!ListenerUtil.mutListener.listen(27580)) {
            mediaModel.setFileExtension(org.wordpress.android.fluxc.utils.MediaUtils.getExtension(file.getFilePath()));
        }
        if (!ListenerUtil.mutListener.listen(27581)) {
            mediaModel.setMimeType(file.getMimeType());
        }
        if (!ListenerUtil.mutListener.listen(27582)) {
            mediaModel.setThumbnailUrl(file.getThumbnailURL());
        }
        if (!ListenerUtil.mutListener.listen(27583)) {
            mediaModel.setUrl(file.getFileURL());
        }
        if (!ListenerUtil.mutListener.listen(27584)) {
            mediaModel.setTitle(file.getTitle());
        }
        if (!ListenerUtil.mutListener.listen(27585)) {
            mediaModel.setDescription(file.getDescription());
        }
        if (!ListenerUtil.mutListener.listen(27586)) {
            mediaModel.setCaption(file.getCaption());
        }
        if (!ListenerUtil.mutListener.listen(27587)) {
            mediaModel.setMediaId(file.getMediaId() != null ? Long.valueOf(file.getMediaId()) : 0);
        }
        if (!ListenerUtil.mutListener.listen(27588)) {
            mediaModel.setId(file.getId());
        }
        if (!ListenerUtil.mutListener.listen(27589)) {
            mediaModel.setUploadState(file.getUploadState());
        }
        if (!ListenerUtil.mutListener.listen(27590)) {
            mediaModel.setLocalSiteId(Integer.valueOf(file.getBlogId()));
        }
        if (!ListenerUtil.mutListener.listen(27591)) {
            mediaModel.setVideoPressGuid(ShortcodeUtils.getVideoPressIdFromShortCode(file.getVideoPressShortCode()));
        }
        return mediaModel;
    }

    public static MediaFile mediaFileFromMediaModel(MediaModel media) {
        if (!ListenerUtil.mutListener.listen(27592)) {
            if (media == null) {
                return null;
            }
        }
        MediaFile mediaFile = new MediaFile();
        if (!ListenerUtil.mutListener.listen(27593)) {
            mediaFile.setBlogId(String.valueOf(media.getLocalSiteId()));
        }
        if (!ListenerUtil.mutListener.listen(27599)) {
            mediaFile.setMediaId((ListenerUtil.mutListener.listen(27598) ? (media.getMediaId() >= 0) : (ListenerUtil.mutListener.listen(27597) ? (media.getMediaId() <= 0) : (ListenerUtil.mutListener.listen(27596) ? (media.getMediaId() < 0) : (ListenerUtil.mutListener.listen(27595) ? (media.getMediaId() != 0) : (ListenerUtil.mutListener.listen(27594) ? (media.getMediaId() == 0) : (media.getMediaId() > 0)))))) ? String.valueOf(media.getMediaId()) : null);
        }
        if (!ListenerUtil.mutListener.listen(27600)) {
            mediaFile.setId(media.getId());
        }
        if (!ListenerUtil.mutListener.listen(27601)) {
            mediaFile.setFileName(media.getFileName());
        }
        if (!ListenerUtil.mutListener.listen(27602)) {
            mediaFile.setFilePath(media.getFilePath());
        }
        if (!ListenerUtil.mutListener.listen(27603)) {
            mediaFile.setMimeType(media.getMimeType());
        }
        if (!ListenerUtil.mutListener.listen(27604)) {
            mediaFile.setThumbnailURL(media.getThumbnailUrl());
        }
        if (!ListenerUtil.mutListener.listen(27605)) {
            mediaFile.setFileURL(media.getUrl());
        }
        if (!ListenerUtil.mutListener.listen(27606)) {
            mediaFile.setTitle(media.getTitle());
        }
        if (!ListenerUtil.mutListener.listen(27607)) {
            mediaFile.setDescription(media.getDescription());
        }
        if (!ListenerUtil.mutListener.listen(27608)) {
            mediaFile.setCaption(media.getCaption());
        }
        if (!ListenerUtil.mutListener.listen(27609)) {
            mediaFile.setUploadState(media.getUploadState());
        }
        if (!ListenerUtil.mutListener.listen(27610)) {
            mediaFile.setVideo(org.wordpress.android.fluxc.utils.MediaUtils.isVideoMimeType(media.getMimeType()));
        }
        if (!ListenerUtil.mutListener.listen(27611)) {
            mediaFile.setVideoPressShortCode(ShortcodeUtils.getVideoPressShortcodeFromId(media.getVideoPressGuid()));
        }
        if (!ListenerUtil.mutListener.listen(27612)) {
            mediaFile.setHeight(media.getHeight());
        }
        if (!ListenerUtil.mutListener.listen(27613)) {
            mediaFile.setWidth(media.getWidth());
        }
        return mediaFile;
    }

    /**
     * This method returns a FluxC MediaModel from a device media URI
     *
     * @return MediaModel or null in case of problems reading the URI
     */
    public static MediaModel mediaModelFromLocalUri(@NonNull Context context, @NonNull Uri uri, @Nullable String mimeType, @NonNull org.wordpress.android.fluxc.store.MediaStore mediaStore, int localSiteId) {
        String path = MediaUtils.getRealPathFromURI(context, uri);
        if (!ListenerUtil.mutListener.listen(27615)) {
            if (TextUtils.isEmpty(path)) {
                if (!ListenerUtil.mutListener.listen(27614)) {
                    AppLog.d(T.UTILS, "The input URI " + uri.toString() + " can't be read.");
                }
                return null;
            }
        }
        File file = new File(path);
        if (!ListenerUtil.mutListener.listen(27617)) {
            if (!file.exists()) {
                if (!ListenerUtil.mutListener.listen(27616)) {
                    AppLog.d(T.UTILS, "The input URI " + uri.toString() + ", converted locally to " + path + " doesn't exist.");
                }
                return null;
            }
        }
        MediaModel media = mediaStore.instantiateMediaModel();
        String filename = org.wordpress.android.fluxc.utils.MediaUtils.getFileName(path);
        if (!ListenerUtil.mutListener.listen(27619)) {
            if (filename == null)
                if (!ListenerUtil.mutListener.listen(27618)) {
                    filename = "";
                }
        }
        String fileExtension = org.wordpress.android.fluxc.utils.MediaUtils.getExtension(path);
        if (!ListenerUtil.mutListener.listen(27625)) {
            if (TextUtils.isEmpty(mimeType)) {
                if (!ListenerUtil.mutListener.listen(27620)) {
                    mimeType = UrlUtils.getUrlMimeType(uri.toString());
                }
                if (!ListenerUtil.mutListener.listen(27624)) {
                    if (mimeType == null) {
                        if (!ListenerUtil.mutListener.listen(27621)) {
                            mimeType = MimeTypeMap.getSingleton().getMimeTypeFromExtension(fileExtension);
                        }
                        if (!ListenerUtil.mutListener.listen(27623)) {
                            if (mimeType == null) {
                                if (!ListenerUtil.mutListener.listen(27622)) {
                                    // Default to image jpeg
                                    mimeType = "image/jpeg";
                                }
                            }
                        }
                    }
                }
            }
        }
        String title = filename;
        if (!ListenerUtil.mutListener.listen(27632)) {
            // Remove extension from title
            if ((ListenerUtil.mutListener.listen(27626) ? (fileExtension != null || title.contains("." + fileExtension)) : (fileExtension != null && title.contains("." + fileExtension)))) {
                if (!ListenerUtil.mutListener.listen(27631)) {
                    title = title.substring(0, (ListenerUtil.mutListener.listen(27630) ? (title.lastIndexOf(fileExtension) % 1) : (ListenerUtil.mutListener.listen(27629) ? (title.lastIndexOf(fileExtension) / 1) : (ListenerUtil.mutListener.listen(27628) ? (title.lastIndexOf(fileExtension) * 1) : (ListenerUtil.mutListener.listen(27627) ? (title.lastIndexOf(fileExtension) + 1) : (title.lastIndexOf(fileExtension) - 1))))));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(27635)) {
            // If file extension is null, upload won't work on wordpress.com
            if (fileExtension == null) {
                if (!ListenerUtil.mutListener.listen(27633)) {
                    fileExtension = MimeTypeMap.getSingleton().getExtensionFromMimeType(mimeType);
                }
                if (!ListenerUtil.mutListener.listen(27634)) {
                    filename += "." + fileExtension;
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(27636)) {
            media.setFileName(filename);
        }
        if (!ListenerUtil.mutListener.listen(27637)) {
            media.setTitle(title);
        }
        if (!ListenerUtil.mutListener.listen(27638)) {
            media.setFilePath(path);
        }
        if (!ListenerUtil.mutListener.listen(27639)) {
            media.setLocalSiteId(localSiteId);
        }
        if (!ListenerUtil.mutListener.listen(27640)) {
            media.setFileExtension(fileExtension);
        }
        if (!ListenerUtil.mutListener.listen(27641)) {
            media.setMimeType(mimeType);
        }
        if (!ListenerUtil.mutListener.listen(27642)) {
            media.setUploadState(MediaModel.MediaUploadState.QUEUED);
        }
        if (!ListenerUtil.mutListener.listen(27647)) {
            media.setUploadDate(DateTimeUtils.iso8601UTCFromTimestamp((ListenerUtil.mutListener.listen(27646) ? (System.currentTimeMillis() % 1000) : (ListenerUtil.mutListener.listen(27645) ? (System.currentTimeMillis() * 1000) : (ListenerUtil.mutListener.listen(27644) ? (System.currentTimeMillis() - 1000) : (ListenerUtil.mutListener.listen(27643) ? (System.currentTimeMillis() + 1000) : (System.currentTimeMillis() / 1000)))))));
        }
        return media;
    }
}
