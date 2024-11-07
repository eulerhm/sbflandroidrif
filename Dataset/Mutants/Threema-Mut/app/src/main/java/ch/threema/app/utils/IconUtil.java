/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
 * Copyright (c) 2015-2021 Threema GmbH
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

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.media.MediaMetadataRetriever;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Build;
import android.os.CancellationSignal;
import android.provider.DocumentsContract;
import android.provider.DocumentsContract.Document;
import android.provider.MediaStore;
import org.msgpack.core.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.HashMap;
import androidx.annotation.WorkerThread;
import ch.threema.app.R;
import ch.threema.app.ui.MediaItem;
import static android.media.MediaMetadataRetriever.OPTION_CLOSEST_SYNC;
import static ch.threema.app.services.MessageServiceImpl.THUMBNAIL_SIZE_PX;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class IconUtil {

    private static final Logger logger = LoggerFactory.getLogger(IconUtil.class);

    private static final HashMap<String, Integer> mimeIcons = new HashMap<>();

    private static void add(String mimeType, int resId) {
        if (!ListenerUtil.mutListener.listen(54179)) {
            if (mimeIcons.put(mimeType, resId) != null) {
                throw new RuntimeException(mimeType + " already registered!");
            }
        }
    }

    static {
        int icon;
        // Package
        icon = R.drawable.ic_doc_apk;
        if (!ListenerUtil.mutListener.listen(54180)) {
            add("application/vnd.android.package-archive", icon);
        }
        // Audio
        icon = R.drawable.ic_doc_audio;
        if (!ListenerUtil.mutListener.listen(54181)) {
            add("application/ogg", icon);
        }
        if (!ListenerUtil.mutListener.listen(54182)) {
            add("application/x-flac", icon);
        }
        // Certificate
        icon = R.drawable.ic_doc_certificate;
        if (!ListenerUtil.mutListener.listen(54183)) {
            add("application/pgp-keys", icon);
        }
        if (!ListenerUtil.mutListener.listen(54184)) {
            add("application/pgp-signature", icon);
        }
        if (!ListenerUtil.mutListener.listen(54185)) {
            add("application/x-pkcs12", icon);
        }
        if (!ListenerUtil.mutListener.listen(54186)) {
            add("application/x-pkcs7-certreqresp", icon);
        }
        if (!ListenerUtil.mutListener.listen(54187)) {
            add("application/x-pkcs7-crl", icon);
        }
        if (!ListenerUtil.mutListener.listen(54188)) {
            add("application/x-x509-ca-cert", icon);
        }
        if (!ListenerUtil.mutListener.listen(54189)) {
            add("application/x-x509-user-cert", icon);
        }
        if (!ListenerUtil.mutListener.listen(54190)) {
            add("application/x-pkcs7-certificates", icon);
        }
        if (!ListenerUtil.mutListener.listen(54191)) {
            add("application/x-pkcs7-mime", icon);
        }
        if (!ListenerUtil.mutListener.listen(54192)) {
            add("application/x-pkcs7-signature", icon);
        }
        // Source code
        icon = R.drawable.ic_doc_codes;
        if (!ListenerUtil.mutListener.listen(54193)) {
            add("application/rdf+xml", icon);
        }
        if (!ListenerUtil.mutListener.listen(54194)) {
            add("application/rss+xml", icon);
        }
        if (!ListenerUtil.mutListener.listen(54195)) {
            add("application/x-object", icon);
        }
        if (!ListenerUtil.mutListener.listen(54196)) {
            add("application/xhtml+xml", icon);
        }
        if (!ListenerUtil.mutListener.listen(54197)) {
            add("text/css", icon);
        }
        if (!ListenerUtil.mutListener.listen(54198)) {
            add("text/html", icon);
        }
        if (!ListenerUtil.mutListener.listen(54199)) {
            add("text/xml", icon);
        }
        if (!ListenerUtil.mutListener.listen(54200)) {
            add("text/x-c++hdr", icon);
        }
        if (!ListenerUtil.mutListener.listen(54201)) {
            add("text/x-c++src", icon);
        }
        if (!ListenerUtil.mutListener.listen(54202)) {
            add("text/x-chdr", icon);
        }
        if (!ListenerUtil.mutListener.listen(54203)) {
            add("text/x-csrc", icon);
        }
        if (!ListenerUtil.mutListener.listen(54204)) {
            add("text/x-dsrc", icon);
        }
        if (!ListenerUtil.mutListener.listen(54205)) {
            add("text/x-csh", icon);
        }
        if (!ListenerUtil.mutListener.listen(54206)) {
            add("text/x-haskell", icon);
        }
        if (!ListenerUtil.mutListener.listen(54207)) {
            add("text/x-java", icon);
        }
        if (!ListenerUtil.mutListener.listen(54208)) {
            add("text/x-literate-haskell", icon);
        }
        if (!ListenerUtil.mutListener.listen(54209)) {
            add("text/x-pascal", icon);
        }
        if (!ListenerUtil.mutListener.listen(54210)) {
            add("text/x-tcl", icon);
        }
        if (!ListenerUtil.mutListener.listen(54211)) {
            add("text/x-tex", icon);
        }
        if (!ListenerUtil.mutListener.listen(54212)) {
            add("application/x-latex", icon);
        }
        if (!ListenerUtil.mutListener.listen(54213)) {
            add("application/x-texinfo", icon);
        }
        if (!ListenerUtil.mutListener.listen(54214)) {
            add("application/atom+xml", icon);
        }
        if (!ListenerUtil.mutListener.listen(54215)) {
            add("application/ecmascript", icon);
        }
        if (!ListenerUtil.mutListener.listen(54216)) {
            add("application/json", icon);
        }
        if (!ListenerUtil.mutListener.listen(54217)) {
            add("application/javascript", icon);
        }
        if (!ListenerUtil.mutListener.listen(54218)) {
            add("application/xml", icon);
        }
        if (!ListenerUtil.mutListener.listen(54219)) {
            add("text/javascript", icon);
        }
        if (!ListenerUtil.mutListener.listen(54220)) {
            add("application/x-javascript", icon);
        }
        // Compressed
        icon = R.drawable.ic_doc_compressed;
        if (!ListenerUtil.mutListener.listen(54221)) {
            add("application/mac-binhex40", icon);
        }
        if (!ListenerUtil.mutListener.listen(54222)) {
            add("application/rar", icon);
        }
        if (!ListenerUtil.mutListener.listen(54223)) {
            add("application/zip", icon);
        }
        if (!ListenerUtil.mutListener.listen(54224)) {
            add("application/x-apple-diskimage", icon);
        }
        if (!ListenerUtil.mutListener.listen(54225)) {
            add("application/x-debian-package", icon);
        }
        if (!ListenerUtil.mutListener.listen(54226)) {
            add("application/x-gtar", icon);
        }
        if (!ListenerUtil.mutListener.listen(54227)) {
            add("application/x-iso9660-image", icon);
        }
        if (!ListenerUtil.mutListener.listen(54228)) {
            add("application/x-lha", icon);
        }
        if (!ListenerUtil.mutListener.listen(54229)) {
            add("application/x-lzh", icon);
        }
        if (!ListenerUtil.mutListener.listen(54230)) {
            add("application/x-lzx", icon);
        }
        if (!ListenerUtil.mutListener.listen(54231)) {
            add("application/x-stuffit", icon);
        }
        if (!ListenerUtil.mutListener.listen(54232)) {
            add("application/x-tar", icon);
        }
        if (!ListenerUtil.mutListener.listen(54233)) {
            add("application/x-webarchive", icon);
        }
        if (!ListenerUtil.mutListener.listen(54234)) {
            add("application/x-webarchive-xml", icon);
        }
        if (!ListenerUtil.mutListener.listen(54235)) {
            add("application/gzip", icon);
        }
        if (!ListenerUtil.mutListener.listen(54236)) {
            add("application/x-7z-compressed", icon);
        }
        if (!ListenerUtil.mutListener.listen(54237)) {
            add("application/x-deb", icon);
        }
        if (!ListenerUtil.mutListener.listen(54238)) {
            add("application/x-rar-compressed", icon);
        }
        // Contact
        icon = R.drawable.ic_doc_contact_am;
        if (!ListenerUtil.mutListener.listen(54239)) {
            add("text/x-vcard", icon);
        }
        if (!ListenerUtil.mutListener.listen(54240)) {
            add("text/vcard", icon);
        }
        // Event
        icon = R.drawable.ic_doc_event_am;
        if (!ListenerUtil.mutListener.listen(54241)) {
            add("text/calendar", icon);
        }
        if (!ListenerUtil.mutListener.listen(54242)) {
            add("text/x-vcalendar", icon);
        }
        // Font
        icon = R.drawable.ic_doc_font;
        if (!ListenerUtil.mutListener.listen(54243)) {
            add("application/x-font", icon);
        }
        if (!ListenerUtil.mutListener.listen(54244)) {
            add("application/font-woff", icon);
        }
        if (!ListenerUtil.mutListener.listen(54245)) {
            add("application/x-font-woff", icon);
        }
        if (!ListenerUtil.mutListener.listen(54246)) {
            add("application/x-font-ttf", icon);
        }
        // Image
        icon = R.drawable.ic_image_outline;
        if (!ListenerUtil.mutListener.listen(54247)) {
            add("application/vnd.oasis.opendocument.graphics", icon);
        }
        if (!ListenerUtil.mutListener.listen(54248)) {
            add("application/vnd.oasis.opendocument.graphics-template", icon);
        }
        if (!ListenerUtil.mutListener.listen(54249)) {
            add("application/vnd.oasis.opendocument.image", icon);
        }
        if (!ListenerUtil.mutListener.listen(54250)) {
            add("application/vnd.stardivision.draw", icon);
        }
        if (!ListenerUtil.mutListener.listen(54251)) {
            add("application/vnd.sun.xml.draw", icon);
        }
        if (!ListenerUtil.mutListener.listen(54252)) {
            add("application/vnd.sun.xml.draw.template", icon);
        }
        // PDF
        icon = R.drawable.ic_doc_pdf;
        if (!ListenerUtil.mutListener.listen(54253)) {
            add("application/pdf", icon);
        }
        // Presentation
        icon = R.drawable.ic_doc_presentation;
        if (!ListenerUtil.mutListener.listen(54254)) {
            add("application/vnd.stardivision.impress", icon);
        }
        if (!ListenerUtil.mutListener.listen(54255)) {
            add("application/vnd.sun.xml.impress", icon);
        }
        if (!ListenerUtil.mutListener.listen(54256)) {
            add("application/vnd.sun.xml.impress.template", icon);
        }
        if (!ListenerUtil.mutListener.listen(54257)) {
            add("application/x-kpresenter", icon);
        }
        if (!ListenerUtil.mutListener.listen(54258)) {
            add("application/vnd.oasis.opendocument.presentation", icon);
        }
        // Spreadsheet
        icon = R.drawable.ic_doc_spreadsheet_am;
        if (!ListenerUtil.mutListener.listen(54259)) {
            add("application/vnd.oasis.opendocument.spreadsheet", icon);
        }
        if (!ListenerUtil.mutListener.listen(54260)) {
            add("application/vnd.oasis.opendocument.spreadsheet-template", icon);
        }
        if (!ListenerUtil.mutListener.listen(54261)) {
            add("application/vnd.stardivision.calc", icon);
        }
        if (!ListenerUtil.mutListener.listen(54262)) {
            add("application/vnd.sun.xml.calc", icon);
        }
        if (!ListenerUtil.mutListener.listen(54263)) {
            add("application/vnd.sun.xml.calc.template", icon);
        }
        if (!ListenerUtil.mutListener.listen(54264)) {
            add("application/x-kspread", icon);
        }
        // Text
        icon = R.drawable.ic_doc_text_am;
        if (!ListenerUtil.mutListener.listen(54265)) {
            add("application/vnd.oasis.opendocument.text", icon);
        }
        if (!ListenerUtil.mutListener.listen(54266)) {
            add("application/vnd.oasis.opendocument.text-master", icon);
        }
        if (!ListenerUtil.mutListener.listen(54267)) {
            add("application/vnd.oasis.opendocument.text-template", icon);
        }
        if (!ListenerUtil.mutListener.listen(54268)) {
            add("application/vnd.oasis.opendocument.text-web", icon);
        }
        if (!ListenerUtil.mutListener.listen(54269)) {
            add("application/vnd.stardivision.writer", icon);
        }
        if (!ListenerUtil.mutListener.listen(54270)) {
            add("application/vnd.stardivision.writer-global", icon);
        }
        if (!ListenerUtil.mutListener.listen(54271)) {
            add("application/vnd.sun.xml.writer", icon);
        }
        if (!ListenerUtil.mutListener.listen(54272)) {
            add("application/vnd.sun.xml.writer.global", icon);
        }
        if (!ListenerUtil.mutListener.listen(54273)) {
            add("application/vnd.sun.xml.writer.template", icon);
        }
        if (!ListenerUtil.mutListener.listen(54274)) {
            add("application/x-abiword", icon);
        }
        if (!ListenerUtil.mutListener.listen(54275)) {
            add("application/x-kword", icon);
        }
        // Video
        icon = R.drawable.ic_movie_outline;
        if (!ListenerUtil.mutListener.listen(54276)) {
            add("application/x-quicktimeplayer", icon);
        }
        if (!ListenerUtil.mutListener.listen(54277)) {
            add("application/x-shockwave-flash", icon);
        }
        // Word
        icon = R.drawable.ic_doc_word;
        if (!ListenerUtil.mutListener.listen(54278)) {
            add("application/msword", icon);
        }
        if (!ListenerUtil.mutListener.listen(54279)) {
            add("application/vnd.openxmlformats-officedocument.wordprocessingml.document", icon);
        }
        if (!ListenerUtil.mutListener.listen(54280)) {
            add("application/vnd.openxmlformats-officedocument.wordprocessingml.template", icon);
        }
        // Excel
        icon = R.drawable.ic_doc_excel;
        if (!ListenerUtil.mutListener.listen(54281)) {
            add("application/vnd.ms-excel", icon);
        }
        if (!ListenerUtil.mutListener.listen(54282)) {
            add("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet", icon);
        }
        if (!ListenerUtil.mutListener.listen(54283)) {
            add("application/vnd.openxmlformats-officedocument.spreadsheetml.template", icon);
        }
        // Powerpoint
        icon = R.drawable.ic_doc_powerpoint;
        if (!ListenerUtil.mutListener.listen(54284)) {
            add("application/vnd.ms-powerpoint", icon);
        }
        if (!ListenerUtil.mutListener.listen(54285)) {
            add("application/vnd.openxmlformats-officedocument.presentationml.presentation", icon);
        }
        if (!ListenerUtil.mutListener.listen(54286)) {
            add("application/vnd.openxmlformats-officedocument.presentationml.template", icon);
        }
        if (!ListenerUtil.mutListener.listen(54287)) {
            add("application/vnd.openxmlformats-officedocument.presentationml.slideshow", icon);
        }
    }

    public static int getMimeIcon(String mimeType) {
        if (mimeType == null) {
            return R.drawable.ic_doc_generic_am;
        }
        // folder
        if (Document.MIME_TYPE_DIR.equals(mimeType)) {
            return R.drawable.ic_doc_folder;
        }
        // Look for exact match first
        Integer resId = mimeIcons.get(mimeType);
        if (resId != null) {
            return resId;
        }
        // Otherwise look for partial match
        final String typeOnly = mimeType.split("/")[0];
        if ("audio".equals(typeOnly)) {
            return R.drawable.ic_doc_audio;
        } else if ("image".equals(typeOnly)) {
            return R.drawable.ic_image_outline;
        } else if ("text".equals(typeOnly)) {
            return R.drawable.ic_doc_text_am;
        } else if ("video".equals(typeOnly)) {
            return R.drawable.ic_movie_outline;
        } else {
            return R.drawable.ic_doc_generic_am;
        }
    }

    @WorkerThread
    @Nullable
    public static Bitmap getThumbnailFromUri(Context context, Uri uri, int thumbSize, String mimeType, boolean ignoreExifRotate) {
        if (!ListenerUtil.mutListener.listen(54288)) {
            logger.debug("getThumbnailFromUri");
        }
        String docId = null;
        long imageId = -1;
        Bitmap thumbnailBitmap = null;
        ContentResolver contentResolver = context.getContentResolver();
        BitmapUtil.ExifOrientation exifOrientation = BitmapUtil.getExifOrientation(context, uri);
        if (!ListenerUtil.mutListener.listen(54327)) {
            if ((ListenerUtil.mutListener.listen(54291) ? ((ListenerUtil.mutListener.listen(54290) ? ((ListenerUtil.mutListener.listen(54289) ? (!MimeUtil.MIME_TYPE_IMAGE_JPG.equals(mimeType) || !MimeUtil.MIME_TYPE_IMAGE_PNG.equals(mimeType)) : (!MimeUtil.MIME_TYPE_IMAGE_JPG.equals(mimeType) && !MimeUtil.MIME_TYPE_IMAGE_PNG.equals(mimeType))) || !MimeUtil.MIME_TYPE_IMAGE_HEIF.equals(mimeType)) : ((ListenerUtil.mutListener.listen(54289) ? (!MimeUtil.MIME_TYPE_IMAGE_JPG.equals(mimeType) || !MimeUtil.MIME_TYPE_IMAGE_PNG.equals(mimeType)) : (!MimeUtil.MIME_TYPE_IMAGE_JPG.equals(mimeType) && !MimeUtil.MIME_TYPE_IMAGE_PNG.equals(mimeType))) && !MimeUtil.MIME_TYPE_IMAGE_HEIF.equals(mimeType))) || !MimeUtil.MIME_TYPE_IMAGE_HEIC.equals(mimeType)) : ((ListenerUtil.mutListener.listen(54290) ? ((ListenerUtil.mutListener.listen(54289) ? (!MimeUtil.MIME_TYPE_IMAGE_JPG.equals(mimeType) || !MimeUtil.MIME_TYPE_IMAGE_PNG.equals(mimeType)) : (!MimeUtil.MIME_TYPE_IMAGE_JPG.equals(mimeType) && !MimeUtil.MIME_TYPE_IMAGE_PNG.equals(mimeType))) || !MimeUtil.MIME_TYPE_IMAGE_HEIF.equals(mimeType)) : ((ListenerUtil.mutListener.listen(54289) ? (!MimeUtil.MIME_TYPE_IMAGE_JPG.equals(mimeType) || !MimeUtil.MIME_TYPE_IMAGE_PNG.equals(mimeType)) : (!MimeUtil.MIME_TYPE_IMAGE_JPG.equals(mimeType) && !MimeUtil.MIME_TYPE_IMAGE_PNG.equals(mimeType))) && !MimeUtil.MIME_TYPE_IMAGE_HEIF.equals(mimeType))) && !MimeUtil.MIME_TYPE_IMAGE_HEIC.equals(mimeType)))) {
                if (!ListenerUtil.mutListener.listen(54297)) {
                    if (DocumentsContract.isDocumentUri(context, uri)) {
                        // Note: these thumbnails MAY or MAY NOT have EXIF rotation already applied. So we can't use them for JPEG
                        Point thumbPoint = new Point(thumbSize, thumbSize);
                        try {
                            if (!ListenerUtil.mutListener.listen(54294)) {
                                thumbnailBitmap = DocumentsContract.getDocumentThumbnail(contentResolver, uri, thumbPoint, new CancellationSignal());
                            }
                            if (!ListenerUtil.mutListener.listen(54295)) {
                                if (thumbnailBitmap != null) {
                                    return thumbnailBitmap;
                                }
                            }
                        } catch (Exception e) {
                            if (!ListenerUtil.mutListener.listen(54293)) {
                                // ignore - no thumbnail found
                                logger.error("Exception", e);
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(54296)) {
                            // get id from document provider
                            docId = DocumentsContract.getDocumentId(uri);
                        }
                    } else if (ContentResolver.SCHEME_CONTENT.equalsIgnoreCase(uri.getScheme())) {
                        if (!ListenerUtil.mutListener.listen(54292)) {
                            docId = uri.getLastPathSegment();
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(54307)) {
                    if (!TestUtil.empty(docId)) {
                        final String[] split = docId.split(":");
                        if (!ListenerUtil.mutListener.listen(54306)) {
                            if ((ListenerUtil.mutListener.listen(54302) ? (split.length <= 2) : (ListenerUtil.mutListener.listen(54301) ? (split.length > 2) : (ListenerUtil.mutListener.listen(54300) ? (split.length < 2) : (ListenerUtil.mutListener.listen(54299) ? (split.length != 2) : (ListenerUtil.mutListener.listen(54298) ? (split.length == 2) : (split.length >= 2))))))) {
                                final String idString = split[1];
                                if (!ListenerUtil.mutListener.listen(54305)) {
                                    if (!TestUtil.empty(idString)) {
                                        try {
                                            if (!ListenerUtil.mutListener.listen(54304)) {
                                                imageId = Long.parseLong(idString);
                                            }
                                        } catch (NumberFormatException x) {
                                            if (!ListenerUtil.mutListener.listen(54303)) {
                                                logger.error("Exception", x);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(54317)) {
                    if ((ListenerUtil.mutListener.listen(54312) ? (imageId >= -1) : (ListenerUtil.mutListener.listen(54311) ? (imageId <= -1) : (ListenerUtil.mutListener.listen(54310) ? (imageId > -1) : (ListenerUtil.mutListener.listen(54309) ? (imageId < -1) : (ListenerUtil.mutListener.listen(54308) ? (imageId != -1) : (imageId == -1))))))) {
                        // query media store for thumbnail
                        String[] columns = new String[] { MediaStore.Images.Thumbnails._ID };
                        try (Cursor cursor = contentResolver.query(uri, columns, null, null, null)) {
                            if (!ListenerUtil.mutListener.listen(54316)) {
                                if ((ListenerUtil.mutListener.listen(54314) ? (cursor != null || cursor.moveToFirst()) : (cursor != null && cursor.moveToFirst()))) {
                                    if (!ListenerUtil.mutListener.listen(54315)) {
                                        imageId = cursor.getInt(cursor.getColumnIndex(MediaStore.Images.Thumbnails._ID));
                                    }
                                }
                            }
                        } catch (Exception e) {
                            if (!ListenerUtil.mutListener.listen(54313)) {
                                logger.error("Exception", e);
                            }
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(54318)) {
                    logger.debug("Thumbnail image id: " + imageId);
                }
                if (!ListenerUtil.mutListener.listen(54326)) {
                    if ((ListenerUtil.mutListener.listen(54323) ? (imageId >= 0) : (ListenerUtil.mutListener.listen(54322) ? (imageId <= 0) : (ListenerUtil.mutListener.listen(54321) ? (imageId < 0) : (ListenerUtil.mutListener.listen(54320) ? (imageId != 0) : (ListenerUtil.mutListener.listen(54319) ? (imageId == 0) : (imageId > 0))))))) {
                        // may throw java.lang.SecurityException
                        try {
                            if (!ListenerUtil.mutListener.listen(54325)) {
                                thumbnailBitmap = MediaStore.Images.Thumbnails.getThumbnail(contentResolver, imageId, MediaStore.Images.Thumbnails.MINI_KIND, null);
                            }
                        } catch (Exception e) {
                            if (!ListenerUtil.mutListener.listen(54324)) {
                                logger.error("Exception", e);
                            }
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(54330)) {
            if (thumbnailBitmap == null) {
                // PNGs or GIFs may contain transparency
                boolean mayContainTransparency = (ListenerUtil.mutListener.listen(54328) ? (MimeUtil.MIME_TYPE_IMAGE_PNG.equals(mimeType) && MimeUtil.MIME_TYPE_IMAGE_GIF.equals(mimeType)) : (MimeUtil.MIME_TYPE_IMAGE_PNG.equals(mimeType) || MimeUtil.MIME_TYPE_IMAGE_GIF.equals(mimeType)));
                if (!ListenerUtil.mutListener.listen(54329)) {
                    thumbnailBitmap = BitmapUtil.safeGetBitmapFromUri(context, uri, thumbSize, mayContainTransparency, !mayContainTransparency, true);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(54336)) {
            if ((ListenerUtil.mutListener.listen(54331) ? (thumbnailBitmap == null || MimeUtil.isVideoFile(mimeType)) : (thumbnailBitmap == null && MimeUtil.isVideoFile(mimeType)))) {
                if (!ListenerUtil.mutListener.listen(54332)) {
                    thumbnailBitmap = getVideoThumbnailFromUri(context, uri);
                }
                if (!ListenerUtil.mutListener.listen(54335)) {
                    if (thumbnailBitmap == null) {
                        String path = FileUtil.getRealPathFromURI(context, uri);
                        if (!ListenerUtil.mutListener.listen(54334)) {
                            if (path != null) {
                                if (!ListenerUtil.mutListener.listen(54333)) {
                                    thumbnailBitmap = ThumbnailUtils.createVideoThumbnail(path, MediaStore.Images.Thumbnails.MINI_KIND);
                                }
                            }
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(54340)) {
            if ((ListenerUtil.mutListener.listen(54339) ? ((ListenerUtil.mutListener.listen(54337) ? (thumbnailBitmap != null || !ignoreExifRotate) : (thumbnailBitmap != null && !ignoreExifRotate)) || ((ListenerUtil.mutListener.listen(54338) ? (exifOrientation.getRotation() != 0f && exifOrientation.getFlip() != 0f) : (exifOrientation.getRotation() != 0f || exifOrientation.getFlip() != 0f)))) : ((ListenerUtil.mutListener.listen(54337) ? (thumbnailBitmap != null || !ignoreExifRotate) : (thumbnailBitmap != null && !ignoreExifRotate)) && ((ListenerUtil.mutListener.listen(54338) ? (exifOrientation.getRotation() != 0f && exifOrientation.getFlip() != 0f) : (exifOrientation.getRotation() != 0f || exifOrientation.getFlip() != 0f)))))) {
                return BitmapUtil.rotateBitmap(thumbnailBitmap, exifOrientation.getRotation(), exifOrientation.getFlip());
            }
        }
        return thumbnailBitmap;
    }

    public static Bitmap getVideoThumbnailFromUri(Context context, Uri uri) {
        // do not use automatic resource management on MediaMetadataRetriever
        final MediaMetadataRetriever retriever = new MediaMetadataRetriever();
        try {
            if (!ListenerUtil.mutListener.listen(54343)) {
                retriever.setDataSource(context, uri);
            }
            if (!ListenerUtil.mutListener.listen(54349)) {
                if ((ListenerUtil.mutListener.listen(54348) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.O_MR1) : (ListenerUtil.mutListener.listen(54347) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.O_MR1) : (ListenerUtil.mutListener.listen(54346) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.O_MR1) : (ListenerUtil.mutListener.listen(54345) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.O_MR1) : (ListenerUtil.mutListener.listen(54344) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.O_MR1) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O_MR1))))))) {
                    return retriever.getScaledFrameAtTime(1, OPTION_CLOSEST_SYNC, THUMBNAIL_SIZE_PX, THUMBNAIL_SIZE_PX);
                } else {
                    return BitmapUtil.resizeBitmapExactlyToMaxWidth(retriever.getFrameAtTime(1), THUMBNAIL_SIZE_PX);
                }
            }
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(54341)) {
                // do not show the exception!
                logger.error("Exception", e);
            }
        } finally {
            if (!ListenerUtil.mutListener.listen(54342)) {
                retriever.release();
            }
        }
        return null;
    }

    @Nullable
    public static Bitmap getVideoThumbnailFromUri(Context context, MediaItem mediaItem) {
        long timeUs = mediaItem.getStartTimeMs() == 0 ? 1L : (ListenerUtil.mutListener.listen(54353) ? (mediaItem.getStartTimeMs() % 1000) : (ListenerUtil.mutListener.listen(54352) ? (mediaItem.getStartTimeMs() / 1000) : (ListenerUtil.mutListener.listen(54351) ? (mediaItem.getStartTimeMs() - 1000) : (ListenerUtil.mutListener.listen(54350) ? (mediaItem.getStartTimeMs() + 1000) : (mediaItem.getStartTimeMs() * 1000)))));
        // do not use automatic resource management on MediaMetadataRetriever
        final MediaMetadataRetriever retriever = new MediaMetadataRetriever();
        try {
            if (!ListenerUtil.mutListener.listen(54356)) {
                retriever.setDataSource(context, mediaItem.getUri());
            }
            // getScaledFrameAtTime() returns unfiltered bitmaps that look bad at low resolutions
            return BitmapUtil.resizeBitmapExactlyToMaxWidth(retriever.getFrameAtTime(timeUs), THUMBNAIL_SIZE_PX);
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(54354)) {
                // do not show the exception!
                logger.error("Exception", e);
            }
        } finally {
            if (!ListenerUtil.mutListener.listen(54355)) {
                retriever.release();
            }
        }
        return null;
    }
}
