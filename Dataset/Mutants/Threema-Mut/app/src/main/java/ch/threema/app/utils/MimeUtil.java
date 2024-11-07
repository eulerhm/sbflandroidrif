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

import android.content.Context;
import java.lang.annotation.Retention;
import java.util.HashMap;
import java.util.Map;
import androidx.annotation.IntDef;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import ch.threema.app.R;
import ch.threema.app.exceptions.MalformedMimeTypeException;
import ch.threema.app.ui.MediaItem;
import ch.threema.storage.models.AbstractMessageModel;
import ch.threema.storage.models.data.MessageContentsType;
import ch.threema.storage.models.data.media.FileDataModel;
import static ch.threema.app.ui.MediaItem.TYPE_FILE;
import static ch.threema.app.ui.MediaItem.TYPE_GIF;
import static ch.threema.app.ui.MediaItem.TYPE_IMAGE;
import static ch.threema.app.ui.MediaItem.TYPE_VIDEO;
import static ch.threema.app.ui.MediaItem.TYPE_VOICEMESSAGE;
import static ch.threema.client.file.FileData.RENDERING_MEDIA;
import static java.lang.annotation.RetentionPolicy.SOURCE;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class MimeUtil {

    public static final String MIME_TYPE_ANY = "*/*";

    public static final String MIME_TYPE_VIDEO = "video/*";

    public static final String MIME_TYPE_AUDIO = "audio/*";

    public static final String MIME_TYPE_IMAGE = "image/*";

    public static final String MIME_TYPE_IMAGE_JPG = "image/jpeg";

    public static final String MIME_TYPE_IMAGE_PNG = "image/png";

    public static final String MIME_TYPE_IMAGE_GIF = "image/gif";

    public static final String MIME_TYPE_IMAGE_HEIF = "image/heif";

    public static final String MIME_TYPE_IMAGE_HEIC = "image/heic";

    public static final String MIME_TYPE_IMAGE_TIFF = "image/tiff";

    public static final String MIME_TYPE_VIDEO_MPEG = "video/mpeg";

    public static final String MIME_TYPE_VIDEO_MP4 = "video/mp4";

    public static final String MIME_TYPE_VIDEO_AVC = "video/avc";

    public static final String MIME_TYPE_AUDIO_AAC = "audio/aac";

    public static final String MIME_TYPE_AUDIO_MIDI = "audio/midi";

    public static final String MIME_TYPE_AUDIO_XMIDI = "audio/x-midi";

    public static final String MIME_TYPE_AUDIO_FLAC = "audio/flac";

    public static final String MIME_TYPE_AUDIO_XFLAC = "audio/x-flac";

    public static final String MIME_TYPE_ZIP = "application/zip";

    public static final String MIME_TYPE_PDF = "application/pdf";

    public static final String MIME_TYPE_VCARD = "text/x-vcard";

    public static final String MIME_TYPE_VCARD_ALT = "text/vcard";

    public static final String MIME_TYPE_TEXT = "text/plain";

    public static final String MIME_TYPE_HTML = "text/html";

    public static final String MIME_TYPE_DEFAULT = "application/octet-stream";

    public static final String MIME_VIDEO = "video/";

    public static final String MIME_AUDIO = "audio/";

    @Retention(SOURCE)
    @IntDef({ MIME_TYPE_VIDEO_IND, MIME_TYPE_IMAGES_IND, MIME_TYPE_GIF_IND })
    public @interface NavigationMode {
    }

    public static final int MIME_TYPE_VIDEO_IND = 101;

    public static final int MIME_TYPE_IMAGES_IND = 102;

    public static final int MIME_TYPE_GIF_IND = 103;

    // map from icon resource id to string resource id
    protected static final Map<Integer, Integer> mimeToDescription = new HashMap<Integer, Integer>() {

        {
            if (!ListenerUtil.mutListener.listen(54943)) {
                put(R.drawable.ic_doc_apk, R.string.mime_android_apk);
            }
            if (!ListenerUtil.mutListener.listen(54944)) {
                put(R.drawable.ic_doc_audio, R.string.mime_audio);
            }
            if (!ListenerUtil.mutListener.listen(54945)) {
                put(R.drawable.ic_doc_certificate, R.string.mime_certificate);
            }
            if (!ListenerUtil.mutListener.listen(54946)) {
                put(R.drawable.ic_doc_codes, R.string.mime_codes);
            }
            if (!ListenerUtil.mutListener.listen(54947)) {
                put(R.drawable.ic_doc_compressed, R.string.mime_compressed);
            }
            if (!ListenerUtil.mutListener.listen(54948)) {
                put(R.drawable.ic_doc_contact_am, R.string.mime_contact);
            }
            if (!ListenerUtil.mutListener.listen(54949)) {
                put(R.drawable.ic_doc_event_am, R.string.mime_event);
            }
            if (!ListenerUtil.mutListener.listen(54950)) {
                put(R.drawable.ic_doc_font, R.string.mime_font);
            }
            if (!ListenerUtil.mutListener.listen(54951)) {
                put(R.drawable.ic_image_outline, R.string.mime_image);
            }
            if (!ListenerUtil.mutListener.listen(54952)) {
                put(R.drawable.ic_doc_pdf, R.string.mime_pdf);
            }
            if (!ListenerUtil.mutListener.listen(54953)) {
                put(R.drawable.ic_doc_presentation, R.string.mime_presentation);
            }
            if (!ListenerUtil.mutListener.listen(54954)) {
                put(R.drawable.ic_doc_spreadsheet_am, R.string.mime_spreadsheet);
            }
            if (!ListenerUtil.mutListener.listen(54955)) {
                put(R.drawable.ic_doc_text_am, R.string.mime_text);
            }
            if (!ListenerUtil.mutListener.listen(54956)) {
                put(R.drawable.ic_movie_outline, R.string.mime_video);
            }
            if (!ListenerUtil.mutListener.listen(54957)) {
                put(R.drawable.ic_doc_word, R.string.mime_word);
            }
            if (!ListenerUtil.mutListener.listen(54958)) {
                put(R.drawable.ic_doc_excel, R.string.mime_spreadsheet);
            }
            if (!ListenerUtil.mutListener.listen(54959)) {
                put(R.drawable.ic_doc_powerpoint, R.string.mime_presentation);
            }
        }
    };

    public static String getMimeDescription(Context context, String mimeType) {
        int iconRes = IconUtil.getMimeIcon(mimeType);
        if ((ListenerUtil.mutListener.listen(54960) ? (iconRes == R.drawable.ic_doc_generic_am && iconRes == R.drawable.ic_doc_folder) : (iconRes == R.drawable.ic_doc_generic_am || iconRes == R.drawable.ic_doc_folder))) {
            return mimeType;
        } else {
            return context.getString(mimeToDescription.get(iconRes));
        }
    }

    public static boolean isMidiFile(String mimeType) {
        return (ListenerUtil.mutListener.listen(54961) ? (mimeType.startsWith(MIME_TYPE_AUDIO_MIDI) && mimeType.startsWith(MIME_TYPE_AUDIO_XMIDI)) : (mimeType.startsWith(MIME_TYPE_AUDIO_MIDI) || mimeType.startsWith(MIME_TYPE_AUDIO_XMIDI)));
    }

    public static boolean isFlacFile(@NonNull String mimeType) {
        return (ListenerUtil.mutListener.listen(54962) ? (mimeType.startsWith(MIME_TYPE_AUDIO_FLAC) && mimeType.startsWith(MIME_TYPE_AUDIO_XFLAC)) : (mimeType.startsWith(MIME_TYPE_AUDIO_FLAC) || mimeType.startsWith(MIME_TYPE_AUDIO_XFLAC)));
    }

    public static boolean isImageFile(@Nullable String mimeType) {
        return (ListenerUtil.mutListener.listen(54963) ? (mimeType != null || mimeType.startsWith("image/")) : (mimeType != null && mimeType.startsWith("image/")));
    }

    public static boolean isLabelableImageFile(@Nullable String mimeType) {
        return (ListenerUtil.mutListener.listen(54968) ? (mimeType != null || ((ListenerUtil.mutListener.listen(54967) ? ((ListenerUtil.mutListener.listen(54966) ? ((ListenerUtil.mutListener.listen(54965) ? ((ListenerUtil.mutListener.listen(54964) ? (mimeType.startsWith(MIME_TYPE_IMAGE_PNG) && mimeType.startsWith(MIME_TYPE_IMAGE_JPG)) : (mimeType.startsWith(MIME_TYPE_IMAGE_PNG) || mimeType.startsWith(MIME_TYPE_IMAGE_JPG))) && mimeType.startsWith(MIME_TYPE_IMAGE_GIF)) : ((ListenerUtil.mutListener.listen(54964) ? (mimeType.startsWith(MIME_TYPE_IMAGE_PNG) && mimeType.startsWith(MIME_TYPE_IMAGE_JPG)) : (mimeType.startsWith(MIME_TYPE_IMAGE_PNG) || mimeType.startsWith(MIME_TYPE_IMAGE_JPG))) || mimeType.startsWith(MIME_TYPE_IMAGE_GIF))) && mimeType.startsWith(MIME_TYPE_IMAGE_HEIF)) : ((ListenerUtil.mutListener.listen(54965) ? ((ListenerUtil.mutListener.listen(54964) ? (mimeType.startsWith(MIME_TYPE_IMAGE_PNG) && mimeType.startsWith(MIME_TYPE_IMAGE_JPG)) : (mimeType.startsWith(MIME_TYPE_IMAGE_PNG) || mimeType.startsWith(MIME_TYPE_IMAGE_JPG))) && mimeType.startsWith(MIME_TYPE_IMAGE_GIF)) : ((ListenerUtil.mutListener.listen(54964) ? (mimeType.startsWith(MIME_TYPE_IMAGE_PNG) && mimeType.startsWith(MIME_TYPE_IMAGE_JPG)) : (mimeType.startsWith(MIME_TYPE_IMAGE_PNG) || mimeType.startsWith(MIME_TYPE_IMAGE_JPG))) || mimeType.startsWith(MIME_TYPE_IMAGE_GIF))) || mimeType.startsWith(MIME_TYPE_IMAGE_HEIF))) && mimeType.startsWith(MIME_TYPE_IMAGE_HEIC)) : ((ListenerUtil.mutListener.listen(54966) ? ((ListenerUtil.mutListener.listen(54965) ? ((ListenerUtil.mutListener.listen(54964) ? (mimeType.startsWith(MIME_TYPE_IMAGE_PNG) && mimeType.startsWith(MIME_TYPE_IMAGE_JPG)) : (mimeType.startsWith(MIME_TYPE_IMAGE_PNG) || mimeType.startsWith(MIME_TYPE_IMAGE_JPG))) && mimeType.startsWith(MIME_TYPE_IMAGE_GIF)) : ((ListenerUtil.mutListener.listen(54964) ? (mimeType.startsWith(MIME_TYPE_IMAGE_PNG) && mimeType.startsWith(MIME_TYPE_IMAGE_JPG)) : (mimeType.startsWith(MIME_TYPE_IMAGE_PNG) || mimeType.startsWith(MIME_TYPE_IMAGE_JPG))) || mimeType.startsWith(MIME_TYPE_IMAGE_GIF))) && mimeType.startsWith(MIME_TYPE_IMAGE_HEIF)) : ((ListenerUtil.mutListener.listen(54965) ? ((ListenerUtil.mutListener.listen(54964) ? (mimeType.startsWith(MIME_TYPE_IMAGE_PNG) && mimeType.startsWith(MIME_TYPE_IMAGE_JPG)) : (mimeType.startsWith(MIME_TYPE_IMAGE_PNG) || mimeType.startsWith(MIME_TYPE_IMAGE_JPG))) && mimeType.startsWith(MIME_TYPE_IMAGE_GIF)) : ((ListenerUtil.mutListener.listen(54964) ? (mimeType.startsWith(MIME_TYPE_IMAGE_PNG) && mimeType.startsWith(MIME_TYPE_IMAGE_JPG)) : (mimeType.startsWith(MIME_TYPE_IMAGE_PNG) || mimeType.startsWith(MIME_TYPE_IMAGE_JPG))) || mimeType.startsWith(MIME_TYPE_IMAGE_GIF))) || mimeType.startsWith(MIME_TYPE_IMAGE_HEIF))) || mimeType.startsWith(MIME_TYPE_IMAGE_HEIC))))) : (mimeType != null && ((ListenerUtil.mutListener.listen(54967) ? ((ListenerUtil.mutListener.listen(54966) ? ((ListenerUtil.mutListener.listen(54965) ? ((ListenerUtil.mutListener.listen(54964) ? (mimeType.startsWith(MIME_TYPE_IMAGE_PNG) && mimeType.startsWith(MIME_TYPE_IMAGE_JPG)) : (mimeType.startsWith(MIME_TYPE_IMAGE_PNG) || mimeType.startsWith(MIME_TYPE_IMAGE_JPG))) && mimeType.startsWith(MIME_TYPE_IMAGE_GIF)) : ((ListenerUtil.mutListener.listen(54964) ? (mimeType.startsWith(MIME_TYPE_IMAGE_PNG) && mimeType.startsWith(MIME_TYPE_IMAGE_JPG)) : (mimeType.startsWith(MIME_TYPE_IMAGE_PNG) || mimeType.startsWith(MIME_TYPE_IMAGE_JPG))) || mimeType.startsWith(MIME_TYPE_IMAGE_GIF))) && mimeType.startsWith(MIME_TYPE_IMAGE_HEIF)) : ((ListenerUtil.mutListener.listen(54965) ? ((ListenerUtil.mutListener.listen(54964) ? (mimeType.startsWith(MIME_TYPE_IMAGE_PNG) && mimeType.startsWith(MIME_TYPE_IMAGE_JPG)) : (mimeType.startsWith(MIME_TYPE_IMAGE_PNG) || mimeType.startsWith(MIME_TYPE_IMAGE_JPG))) && mimeType.startsWith(MIME_TYPE_IMAGE_GIF)) : ((ListenerUtil.mutListener.listen(54964) ? (mimeType.startsWith(MIME_TYPE_IMAGE_PNG) && mimeType.startsWith(MIME_TYPE_IMAGE_JPG)) : (mimeType.startsWith(MIME_TYPE_IMAGE_PNG) || mimeType.startsWith(MIME_TYPE_IMAGE_JPG))) || mimeType.startsWith(MIME_TYPE_IMAGE_GIF))) || mimeType.startsWith(MIME_TYPE_IMAGE_HEIF))) && mimeType.startsWith(MIME_TYPE_IMAGE_HEIC)) : ((ListenerUtil.mutListener.listen(54966) ? ((ListenerUtil.mutListener.listen(54965) ? ((ListenerUtil.mutListener.listen(54964) ? (mimeType.startsWith(MIME_TYPE_IMAGE_PNG) && mimeType.startsWith(MIME_TYPE_IMAGE_JPG)) : (mimeType.startsWith(MIME_TYPE_IMAGE_PNG) || mimeType.startsWith(MIME_TYPE_IMAGE_JPG))) && mimeType.startsWith(MIME_TYPE_IMAGE_GIF)) : ((ListenerUtil.mutListener.listen(54964) ? (mimeType.startsWith(MIME_TYPE_IMAGE_PNG) && mimeType.startsWith(MIME_TYPE_IMAGE_JPG)) : (mimeType.startsWith(MIME_TYPE_IMAGE_PNG) || mimeType.startsWith(MIME_TYPE_IMAGE_JPG))) || mimeType.startsWith(MIME_TYPE_IMAGE_GIF))) && mimeType.startsWith(MIME_TYPE_IMAGE_HEIF)) : ((ListenerUtil.mutListener.listen(54965) ? ((ListenerUtil.mutListener.listen(54964) ? (mimeType.startsWith(MIME_TYPE_IMAGE_PNG) && mimeType.startsWith(MIME_TYPE_IMAGE_JPG)) : (mimeType.startsWith(MIME_TYPE_IMAGE_PNG) || mimeType.startsWith(MIME_TYPE_IMAGE_JPG))) && mimeType.startsWith(MIME_TYPE_IMAGE_GIF)) : ((ListenerUtil.mutListener.listen(54964) ? (mimeType.startsWith(MIME_TYPE_IMAGE_PNG) && mimeType.startsWith(MIME_TYPE_IMAGE_JPG)) : (mimeType.startsWith(MIME_TYPE_IMAGE_PNG) || mimeType.startsWith(MIME_TYPE_IMAGE_JPG))) || mimeType.startsWith(MIME_TYPE_IMAGE_GIF))) || mimeType.startsWith(MIME_TYPE_IMAGE_HEIF))) || mimeType.startsWith(MIME_TYPE_IMAGE_HEIC))))));
    }

    public static boolean isStaticImageFile(@Nullable String mimeType) {
        return (ListenerUtil.mutListener.listen(54973) ? (mimeType != null || ((ListenerUtil.mutListener.listen(54972) ? ((ListenerUtil.mutListener.listen(54971) ? ((ListenerUtil.mutListener.listen(54970) ? ((ListenerUtil.mutListener.listen(54969) ? (mimeType.startsWith(MIME_TYPE_IMAGE_PNG) && mimeType.startsWith(MIME_TYPE_IMAGE_JPG)) : (mimeType.startsWith(MIME_TYPE_IMAGE_PNG) || mimeType.startsWith(MIME_TYPE_IMAGE_JPG))) && mimeType.startsWith(MIME_TYPE_IMAGE_HEIF)) : ((ListenerUtil.mutListener.listen(54969) ? (mimeType.startsWith(MIME_TYPE_IMAGE_PNG) && mimeType.startsWith(MIME_TYPE_IMAGE_JPG)) : (mimeType.startsWith(MIME_TYPE_IMAGE_PNG) || mimeType.startsWith(MIME_TYPE_IMAGE_JPG))) || mimeType.startsWith(MIME_TYPE_IMAGE_HEIF))) && mimeType.startsWith(MIME_TYPE_IMAGE_HEIC)) : ((ListenerUtil.mutListener.listen(54970) ? ((ListenerUtil.mutListener.listen(54969) ? (mimeType.startsWith(MIME_TYPE_IMAGE_PNG) && mimeType.startsWith(MIME_TYPE_IMAGE_JPG)) : (mimeType.startsWith(MIME_TYPE_IMAGE_PNG) || mimeType.startsWith(MIME_TYPE_IMAGE_JPG))) && mimeType.startsWith(MIME_TYPE_IMAGE_HEIF)) : ((ListenerUtil.mutListener.listen(54969) ? (mimeType.startsWith(MIME_TYPE_IMAGE_PNG) && mimeType.startsWith(MIME_TYPE_IMAGE_JPG)) : (mimeType.startsWith(MIME_TYPE_IMAGE_PNG) || mimeType.startsWith(MIME_TYPE_IMAGE_JPG))) || mimeType.startsWith(MIME_TYPE_IMAGE_HEIF))) || mimeType.startsWith(MIME_TYPE_IMAGE_HEIC))) && mimeType.startsWith(MIME_TYPE_IMAGE_TIFF)) : ((ListenerUtil.mutListener.listen(54971) ? ((ListenerUtil.mutListener.listen(54970) ? ((ListenerUtil.mutListener.listen(54969) ? (mimeType.startsWith(MIME_TYPE_IMAGE_PNG) && mimeType.startsWith(MIME_TYPE_IMAGE_JPG)) : (mimeType.startsWith(MIME_TYPE_IMAGE_PNG) || mimeType.startsWith(MIME_TYPE_IMAGE_JPG))) && mimeType.startsWith(MIME_TYPE_IMAGE_HEIF)) : ((ListenerUtil.mutListener.listen(54969) ? (mimeType.startsWith(MIME_TYPE_IMAGE_PNG) && mimeType.startsWith(MIME_TYPE_IMAGE_JPG)) : (mimeType.startsWith(MIME_TYPE_IMAGE_PNG) || mimeType.startsWith(MIME_TYPE_IMAGE_JPG))) || mimeType.startsWith(MIME_TYPE_IMAGE_HEIF))) && mimeType.startsWith(MIME_TYPE_IMAGE_HEIC)) : ((ListenerUtil.mutListener.listen(54970) ? ((ListenerUtil.mutListener.listen(54969) ? (mimeType.startsWith(MIME_TYPE_IMAGE_PNG) && mimeType.startsWith(MIME_TYPE_IMAGE_JPG)) : (mimeType.startsWith(MIME_TYPE_IMAGE_PNG) || mimeType.startsWith(MIME_TYPE_IMAGE_JPG))) && mimeType.startsWith(MIME_TYPE_IMAGE_HEIF)) : ((ListenerUtil.mutListener.listen(54969) ? (mimeType.startsWith(MIME_TYPE_IMAGE_PNG) && mimeType.startsWith(MIME_TYPE_IMAGE_JPG)) : (mimeType.startsWith(MIME_TYPE_IMAGE_PNG) || mimeType.startsWith(MIME_TYPE_IMAGE_JPG))) || mimeType.startsWith(MIME_TYPE_IMAGE_HEIF))) || mimeType.startsWith(MIME_TYPE_IMAGE_HEIC))) || mimeType.startsWith(MIME_TYPE_IMAGE_TIFF))))) : (mimeType != null && ((ListenerUtil.mutListener.listen(54972) ? ((ListenerUtil.mutListener.listen(54971) ? ((ListenerUtil.mutListener.listen(54970) ? ((ListenerUtil.mutListener.listen(54969) ? (mimeType.startsWith(MIME_TYPE_IMAGE_PNG) && mimeType.startsWith(MIME_TYPE_IMAGE_JPG)) : (mimeType.startsWith(MIME_TYPE_IMAGE_PNG) || mimeType.startsWith(MIME_TYPE_IMAGE_JPG))) && mimeType.startsWith(MIME_TYPE_IMAGE_HEIF)) : ((ListenerUtil.mutListener.listen(54969) ? (mimeType.startsWith(MIME_TYPE_IMAGE_PNG) && mimeType.startsWith(MIME_TYPE_IMAGE_JPG)) : (mimeType.startsWith(MIME_TYPE_IMAGE_PNG) || mimeType.startsWith(MIME_TYPE_IMAGE_JPG))) || mimeType.startsWith(MIME_TYPE_IMAGE_HEIF))) && mimeType.startsWith(MIME_TYPE_IMAGE_HEIC)) : ((ListenerUtil.mutListener.listen(54970) ? ((ListenerUtil.mutListener.listen(54969) ? (mimeType.startsWith(MIME_TYPE_IMAGE_PNG) && mimeType.startsWith(MIME_TYPE_IMAGE_JPG)) : (mimeType.startsWith(MIME_TYPE_IMAGE_PNG) || mimeType.startsWith(MIME_TYPE_IMAGE_JPG))) && mimeType.startsWith(MIME_TYPE_IMAGE_HEIF)) : ((ListenerUtil.mutListener.listen(54969) ? (mimeType.startsWith(MIME_TYPE_IMAGE_PNG) && mimeType.startsWith(MIME_TYPE_IMAGE_JPG)) : (mimeType.startsWith(MIME_TYPE_IMAGE_PNG) || mimeType.startsWith(MIME_TYPE_IMAGE_JPG))) || mimeType.startsWith(MIME_TYPE_IMAGE_HEIF))) || mimeType.startsWith(MIME_TYPE_IMAGE_HEIC))) && mimeType.startsWith(MIME_TYPE_IMAGE_TIFF)) : ((ListenerUtil.mutListener.listen(54971) ? ((ListenerUtil.mutListener.listen(54970) ? ((ListenerUtil.mutListener.listen(54969) ? (mimeType.startsWith(MIME_TYPE_IMAGE_PNG) && mimeType.startsWith(MIME_TYPE_IMAGE_JPG)) : (mimeType.startsWith(MIME_TYPE_IMAGE_PNG) || mimeType.startsWith(MIME_TYPE_IMAGE_JPG))) && mimeType.startsWith(MIME_TYPE_IMAGE_HEIF)) : ((ListenerUtil.mutListener.listen(54969) ? (mimeType.startsWith(MIME_TYPE_IMAGE_PNG) && mimeType.startsWith(MIME_TYPE_IMAGE_JPG)) : (mimeType.startsWith(MIME_TYPE_IMAGE_PNG) || mimeType.startsWith(MIME_TYPE_IMAGE_JPG))) || mimeType.startsWith(MIME_TYPE_IMAGE_HEIF))) && mimeType.startsWith(MIME_TYPE_IMAGE_HEIC)) : ((ListenerUtil.mutListener.listen(54970) ? ((ListenerUtil.mutListener.listen(54969) ? (mimeType.startsWith(MIME_TYPE_IMAGE_PNG) && mimeType.startsWith(MIME_TYPE_IMAGE_JPG)) : (mimeType.startsWith(MIME_TYPE_IMAGE_PNG) || mimeType.startsWith(MIME_TYPE_IMAGE_JPG))) && mimeType.startsWith(MIME_TYPE_IMAGE_HEIF)) : ((ListenerUtil.mutListener.listen(54969) ? (mimeType.startsWith(MIME_TYPE_IMAGE_PNG) && mimeType.startsWith(MIME_TYPE_IMAGE_JPG)) : (mimeType.startsWith(MIME_TYPE_IMAGE_PNG) || mimeType.startsWith(MIME_TYPE_IMAGE_JPG))) || mimeType.startsWith(MIME_TYPE_IMAGE_HEIF))) || mimeType.startsWith(MIME_TYPE_IMAGE_HEIC))) || mimeType.startsWith(MIME_TYPE_IMAGE_TIFF))))));
    }

    public static boolean isVideoFile(@Nullable String mimeType) {
        return (ListenerUtil.mutListener.listen(54974) ? (mimeType != null || mimeType.startsWith("video/")) : (mimeType != null && mimeType.startsWith("video/")));
    }

    public static boolean isAudioFile(@Nullable String mimeType) {
        return (ListenerUtil.mutListener.listen(54975) ? (mimeType != null || mimeType.startsWith("audio/")) : (mimeType != null && mimeType.startsWith("audio/")));
    }

    public static boolean isTextFile(@Nullable String mimeType) {
        return (ListenerUtil.mutListener.listen(54976) ? (mimeType != null || mimeType.startsWith(MIME_TYPE_TEXT)) : (mimeType != null && mimeType.startsWith(MIME_TYPE_TEXT)));
    }

    public static boolean isGifFile(@Nullable String mimeType) {
        return (ListenerUtil.mutListener.listen(54977) ? (mimeType != null || mimeType.startsWith(MIME_TYPE_IMAGE_GIF)) : (mimeType != null && mimeType.startsWith(MIME_TYPE_IMAGE_GIF)));
    }

    public static boolean isPdfFile(@Nullable String mimeType) {
        return (ListenerUtil.mutListener.listen(54978) ? (mimeType != null || mimeType.startsWith(MIME_TYPE_PDF)) : (mimeType != null && mimeType.startsWith(MIME_TYPE_PDF)));
    }

    public static boolean isContactFile(@Nullable String mimeType) {
        return (ListenerUtil.mutListener.listen(54980) ? (mimeType != null || ((ListenerUtil.mutListener.listen(54979) ? (mimeType.startsWith(MIME_TYPE_VCARD) && mimeType.startsWith(MIME_TYPE_VCARD_ALT)) : (mimeType.startsWith(MIME_TYPE_VCARD) || mimeType.startsWith(MIME_TYPE_VCARD_ALT))))) : (mimeType != null && ((ListenerUtil.mutListener.listen(54979) ? (mimeType.startsWith(MIME_TYPE_VCARD) && mimeType.startsWith(MIME_TYPE_VCARD_ALT)) : (mimeType.startsWith(MIME_TYPE_VCARD) || mimeType.startsWith(MIME_TYPE_VCARD_ALT))))));
    }

    @NonNull
    private static String getType(String mimeType) throws MalformedMimeTypeException {
        if (mimeType != null) {
            String[] parts = mimeType.split("/");
            if ((ListenerUtil.mutListener.listen(54991) ? ((ListenerUtil.mutListener.listen(54985) ? (parts.length >= 2) : (ListenerUtil.mutListener.listen(54984) ? (parts.length <= 2) : (ListenerUtil.mutListener.listen(54983) ? (parts.length > 2) : (ListenerUtil.mutListener.listen(54982) ? (parts.length < 2) : (ListenerUtil.mutListener.listen(54981) ? (parts.length != 2) : (parts.length == 2)))))) || (ListenerUtil.mutListener.listen(54990) ? (parts[0].length() >= 0) : (ListenerUtil.mutListener.listen(54989) ? (parts[0].length() <= 0) : (ListenerUtil.mutListener.listen(54988) ? (parts[0].length() < 0) : (ListenerUtil.mutListener.listen(54987) ? (parts[0].length() != 0) : (ListenerUtil.mutListener.listen(54986) ? (parts[0].length() == 0) : (parts[0].length() > 0))))))) : ((ListenerUtil.mutListener.listen(54985) ? (parts.length >= 2) : (ListenerUtil.mutListener.listen(54984) ? (parts.length <= 2) : (ListenerUtil.mutListener.listen(54983) ? (parts.length > 2) : (ListenerUtil.mutListener.listen(54982) ? (parts.length < 2) : (ListenerUtil.mutListener.listen(54981) ? (parts.length != 2) : (parts.length == 2)))))) && (ListenerUtil.mutListener.listen(54990) ? (parts[0].length() >= 0) : (ListenerUtil.mutListener.listen(54989) ? (parts[0].length() <= 0) : (ListenerUtil.mutListener.listen(54988) ? (parts[0].length() < 0) : (ListenerUtil.mutListener.listen(54987) ? (parts[0].length() != 0) : (ListenerUtil.mutListener.listen(54986) ? (parts[0].length() == 0) : (parts[0].length() > 0))))))))) {
                return parts[0];
            }
        }
        throw new MalformedMimeTypeException();
    }

    @NonNull
    private static String getSubType(String mimeType) throws MalformedMimeTypeException {
        if (mimeType != null) {
            String[] parts = mimeType.split("/");
            if ((ListenerUtil.mutListener.listen(55002) ? ((ListenerUtil.mutListener.listen(54996) ? (parts.length >= 2) : (ListenerUtil.mutListener.listen(54995) ? (parts.length <= 2) : (ListenerUtil.mutListener.listen(54994) ? (parts.length > 2) : (ListenerUtil.mutListener.listen(54993) ? (parts.length < 2) : (ListenerUtil.mutListener.listen(54992) ? (parts.length != 2) : (parts.length == 2)))))) || (ListenerUtil.mutListener.listen(55001) ? (parts[1].length() >= 0) : (ListenerUtil.mutListener.listen(55000) ? (parts[1].length() <= 0) : (ListenerUtil.mutListener.listen(54999) ? (parts[1].length() < 0) : (ListenerUtil.mutListener.listen(54998) ? (parts[1].length() != 0) : (ListenerUtil.mutListener.listen(54997) ? (parts[1].length() == 0) : (parts[1].length() > 0))))))) : ((ListenerUtil.mutListener.listen(54996) ? (parts.length >= 2) : (ListenerUtil.mutListener.listen(54995) ? (parts.length <= 2) : (ListenerUtil.mutListener.listen(54994) ? (parts.length > 2) : (ListenerUtil.mutListener.listen(54993) ? (parts.length < 2) : (ListenerUtil.mutListener.listen(54992) ? (parts.length != 2) : (parts.length == 2)))))) && (ListenerUtil.mutListener.listen(55001) ? (parts[1].length() >= 0) : (ListenerUtil.mutListener.listen(55000) ? (parts[1].length() <= 0) : (ListenerUtil.mutListener.listen(54999) ? (parts[1].length() < 0) : (ListenerUtil.mutListener.listen(54998) ? (parts[1].length() != 0) : (ListenerUtil.mutListener.listen(54997) ? (parts[1].length() == 0) : (parts[1].length() > 0))))))))) {
                String subType = parts[1];
                if (!ListenerUtil.mutListener.listen(55003)) {
                    // strip parameter part
                    parts = subType.split(";");
                }
                if (!ListenerUtil.mutListener.listen(55016)) {
                    if ((ListenerUtil.mutListener.listen(55014) ? ((ListenerUtil.mutListener.listen(55008) ? (parts.length >= 1) : (ListenerUtil.mutListener.listen(55007) ? (parts.length <= 1) : (ListenerUtil.mutListener.listen(55006) ? (parts.length < 1) : (ListenerUtil.mutListener.listen(55005) ? (parts.length != 1) : (ListenerUtil.mutListener.listen(55004) ? (parts.length == 1) : (parts.length > 1)))))) || (ListenerUtil.mutListener.listen(55013) ? (parts[1].length() >= 0) : (ListenerUtil.mutListener.listen(55012) ? (parts[1].length() <= 0) : (ListenerUtil.mutListener.listen(55011) ? (parts[1].length() < 0) : (ListenerUtil.mutListener.listen(55010) ? (parts[1].length() != 0) : (ListenerUtil.mutListener.listen(55009) ? (parts[1].length() == 0) : (parts[1].length() > 0))))))) : ((ListenerUtil.mutListener.listen(55008) ? (parts.length >= 1) : (ListenerUtil.mutListener.listen(55007) ? (parts.length <= 1) : (ListenerUtil.mutListener.listen(55006) ? (parts.length < 1) : (ListenerUtil.mutListener.listen(55005) ? (parts.length != 1) : (ListenerUtil.mutListener.listen(55004) ? (parts.length == 1) : (parts.length > 1)))))) && (ListenerUtil.mutListener.listen(55013) ? (parts[1].length() >= 0) : (ListenerUtil.mutListener.listen(55012) ? (parts[1].length() <= 0) : (ListenerUtil.mutListener.listen(55011) ? (parts[1].length() < 0) : (ListenerUtil.mutListener.listen(55010) ? (parts[1].length() != 0) : (ListenerUtil.mutListener.listen(55009) ? (parts[1].length() == 0) : (parts[1].length() > 0))))))))) {
                        if (!ListenerUtil.mutListener.listen(55015)) {
                            subType = parts[0];
                        }
                    }
                }
                return subType;
            }
        }
        throw new MalformedMimeTypeException();
    }

    @NonNull
    public static String getCommonMimeType(@NonNull String mimeType1, @NonNull String mimeType2) {
        try {
            if (!ListenerUtil.mutListener.listen(55018)) {
                if (getType(mimeType1).equals(getType(mimeType2))) {
                    if (!ListenerUtil.mutListener.listen(55017)) {
                        if (getSubType(mimeType1).equals(getSubType(mimeType2))) {
                            return mimeType1;
                        }
                    }
                    return getType(mimeType1) + "/*";
                }
            }
        } catch (MalformedMimeTypeException ignored) {
        }
        return MIME_TYPE_ANY;
    }

    @MessageContentsType
    public static int getContentTypeFromFileData(@NonNull FileDataModel fileDataModel) {
        String mimeType = fileDataModel.getMimeType();
        int messageContentsType = MessageContentsType.FILE;
        if (!ListenerUtil.mutListener.listen(55032)) {
            if ((ListenerUtil.mutListener.listen(55023) ? (mimeType.length() >= 0) : (ListenerUtil.mutListener.listen(55022) ? (mimeType.length() <= 0) : (ListenerUtil.mutListener.listen(55021) ? (mimeType.length() < 0) : (ListenerUtil.mutListener.listen(55020) ? (mimeType.length() != 0) : (ListenerUtil.mutListener.listen(55019) ? (mimeType.length() == 0) : (mimeType.length() > 0))))))) {
                if (!ListenerUtil.mutListener.listen(55031)) {
                    if (MimeUtil.isGifFile(mimeType)) {
                        if (!ListenerUtil.mutListener.listen(55030)) {
                            messageContentsType = MessageContentsType.GIF;
                        }
                    } else if (MimeUtil.isImageFile(mimeType)) {
                        if (!ListenerUtil.mutListener.listen(55029)) {
                            messageContentsType = MessageContentsType.IMAGE;
                        }
                    } else if (MimeUtil.isVideoFile(mimeType)) {
                        if (!ListenerUtil.mutListener.listen(55028)) {
                            messageContentsType = MessageContentsType.VIDEO;
                        }
                    } else if (MimeUtil.isAudioFile(mimeType)) {
                        if (!ListenerUtil.mutListener.listen(55027)) {
                            if (fileDataModel.getRenderingType() == RENDERING_MEDIA) {
                                if (!ListenerUtil.mutListener.listen(55026)) {
                                    messageContentsType = MessageContentsType.VOICE_MESSAGE;
                                }
                            } else {
                                if (!ListenerUtil.mutListener.listen(55025)) {
                                    messageContentsType = MessageContentsType.AUDIO;
                                }
                            }
                        }
                    } else if (MimeUtil.isContactFile(mimeType)) {
                        if (!ListenerUtil.mutListener.listen(55024)) {
                            messageContentsType = MessageContentsType.CONTACT;
                        }
                    }
                }
            }
        }
        return messageContentsType;
    }

    @NonNull
    public static String getMimeTypeFromMessageModel(AbstractMessageModel messageModel) {
        String mimeType;
        switch(messageModel.getType()) {
            case IMAGE:
                mimeType = MimeUtil.MIME_TYPE_IMAGE_JPG;
                break;
            case VIDEO:
                mimeType = MimeUtil.MIME_TYPE_VIDEO_AVC;
                break;
            case VOICEMESSAGE:
                mimeType = MimeUtil.MIME_TYPE_AUDIO_AAC;
                break;
            case TEXT:
                mimeType = MimeUtil.MIME_TYPE_TEXT;
                break;
            case FILE:
                mimeType = messageModel.getFileData().getMimeType();
                break;
            default:
                mimeType = MimeUtil.MIME_TYPE_DEFAULT;
                break;
        }
        return mimeType;
    }

    @MediaItem.MediaType
    public static int getMediaTypeFromMimeType(String mimeType) {
        if (!ListenerUtil.mutListener.listen(55035)) {
            if (MimeUtil.isImageFile(mimeType)) {
                if (!ListenerUtil.mutListener.listen(55034)) {
                    if (MimeUtil.isGifFile(mimeType)) {
                        return TYPE_GIF;
                    } else {
                        return TYPE_IMAGE;
                    }
                }
            } else if (MimeUtil.isVideoFile(mimeType)) {
                return TYPE_VIDEO;
            } else if ((ListenerUtil.mutListener.listen(55033) ? (MimeUtil.isAudioFile(mimeType) || mimeType.startsWith(MimeUtil.MIME_TYPE_AUDIO_AAC)) : (MimeUtil.isAudioFile(mimeType) && mimeType.startsWith(MimeUtil.MIME_TYPE_AUDIO_AAC)))) {
                return TYPE_VOICEMESSAGE;
            }
        }
        return TYPE_FILE;
    }
}
