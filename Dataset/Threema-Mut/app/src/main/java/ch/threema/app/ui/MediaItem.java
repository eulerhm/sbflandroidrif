/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
 * Copyright (c) 2019-2021 Threema GmbH
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

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import androidx.annotation.IntDef;
import androidx.annotation.Nullable;
import ch.threema.app.services.PreferenceService;
import ch.threema.app.utils.BitmapUtil;
import ch.threema.app.utils.MimeUtil;
import ch.threema.client.file.FileData;
import static ch.threema.app.services.PreferenceService.ImageScale_DEFAULT;
import static ch.threema.app.services.PreferenceService.VideoSize_DEFAULT;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * This class holds all meta information about a media item to be sent
 */
public class MediaItem implements Parcelable {

    @MediaType
    private int type;

    private Uri uri;

    private int rotation;

    private int exifRotation;

    private long durationMs;

    private String caption;

    private long startTimeMs;

    private long endTimeMs;

    @BitmapUtil.FlipType
    private int flip;

    @BitmapUtil.FlipType
    private int exifFlip;

    private String mimeType;

    @FileData.RenderingType
    int renderingType;

    // desired image scale
    @PreferenceService.ImageScale
    private int imageScale;

    // desired video scale factor
    @PreferenceService.VideoSize
    private int videoSize;

    private String filename;

    private boolean deleteAfterUse;

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({ TYPE_FILE, TYPE_IMAGE, TYPE_VIDEO, TYPE_IMAGE_CAM, TYPE_VIDEO_CAM, TYPE_GIF, TYPE_VOICEMESSAGE, TYPE_TEXT })
    public @interface MediaType {
    }

    public static final int TYPE_FILE = 0;

    public static final int TYPE_IMAGE = 1;

    public static final int TYPE_VIDEO = 2;

    public static final int TYPE_IMAGE_CAM = 3;

    public static final int TYPE_VIDEO_CAM = 4;

    public static final int TYPE_GIF = 5;

    public static final int TYPE_VOICEMESSAGE = 6;

    public static final int TYPE_TEXT = 7;

    public static final long TIME_UNDEFINED = Long.MIN_VALUE;

    public MediaItem(Uri uri, @MediaType int type) {
        if (!ListenerUtil.mutListener.listen(45874)) {
            init();
        }
        if (!ListenerUtil.mutListener.listen(45875)) {
            this.type = type;
        }
        if (!ListenerUtil.mutListener.listen(45876)) {
            this.uri = uri;
        }
    }

    public MediaItem(Uri uri, @MediaType int type, String mimeType, String caption) {
        if (!ListenerUtil.mutListener.listen(45877)) {
            init();
        }
        if (!ListenerUtil.mutListener.listen(45878)) {
            this.type = type;
        }
        if (!ListenerUtil.mutListener.listen(45879)) {
            this.uri = uri;
        }
        if (!ListenerUtil.mutListener.listen(45880)) {
            this.mimeType = mimeType;
        }
        if (!ListenerUtil.mutListener.listen(45881)) {
            this.caption = caption;
        }
    }

    public MediaItem(Uri uri, String mimeType, String caption) {
        if (!ListenerUtil.mutListener.listen(45882)) {
            init();
        }
        if (!ListenerUtil.mutListener.listen(45883)) {
            this.type = MimeUtil.getMediaTypeFromMimeType(mimeType);
        }
        if (!ListenerUtil.mutListener.listen(45890)) {
            if ((ListenerUtil.mutListener.listen(45888) ? (this.type >= TYPE_FILE) : (ListenerUtil.mutListener.listen(45887) ? (this.type <= TYPE_FILE) : (ListenerUtil.mutListener.listen(45886) ? (this.type > TYPE_FILE) : (ListenerUtil.mutListener.listen(45885) ? (this.type < TYPE_FILE) : (ListenerUtil.mutListener.listen(45884) ? (this.type != TYPE_FILE) : (this.type == TYPE_FILE))))))) {
                if (!ListenerUtil.mutListener.listen(45889)) {
                    this.renderingType = FileData.RENDERING_DEFAULT;
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(45891)) {
            this.uri = uri;
        }
        if (!ListenerUtil.mutListener.listen(45892)) {
            this.mimeType = mimeType;
        }
        if (!ListenerUtil.mutListener.listen(45893)) {
            this.caption = caption;
        }
    }

    private void init() {
        if (!ListenerUtil.mutListener.listen(45894)) {
            this.rotation = 0;
        }
        if (!ListenerUtil.mutListener.listen(45895)) {
            this.exifRotation = 0;
        }
        if (!ListenerUtil.mutListener.listen(45896)) {
            this.durationMs = 0;
        }
        if (!ListenerUtil.mutListener.listen(45897)) {
            this.caption = null;
        }
        if (!ListenerUtil.mutListener.listen(45898)) {
            this.startTimeMs = 0;
        }
        if (!ListenerUtil.mutListener.listen(45899)) {
            this.endTimeMs = TIME_UNDEFINED;
        }
        if (!ListenerUtil.mutListener.listen(45900)) {
            this.flip = BitmapUtil.FLIP_NONE;
        }
        if (!ListenerUtil.mutListener.listen(45901)) {
            this.exifFlip = BitmapUtil.FLIP_NONE;
        }
        if (!ListenerUtil.mutListener.listen(45902)) {
            this.mimeType = MimeUtil.MIME_TYPE_DEFAULT;
        }
        if (!ListenerUtil.mutListener.listen(45903)) {
            this.renderingType = FileData.RENDERING_MEDIA;
        }
        if (!ListenerUtil.mutListener.listen(45904)) {
            this.imageScale = ImageScale_DEFAULT;
        }
        if (!ListenerUtil.mutListener.listen(45905)) {
            this.videoSize = VideoSize_DEFAULT;
        }
        if (!ListenerUtil.mutListener.listen(45906)) {
            this.filename = null;
        }
        if (!ListenerUtil.mutListener.listen(45907)) {
            this.deleteAfterUse = false;
        }
    }

    public MediaItem(Parcel in) {
        if (!ListenerUtil.mutListener.listen(45908)) {
            type = in.readInt();
        }
        if (!ListenerUtil.mutListener.listen(45909)) {
            uri = in.readParcelable(Uri.class.getClassLoader());
        }
        if (!ListenerUtil.mutListener.listen(45910)) {
            rotation = in.readInt();
        }
        if (!ListenerUtil.mutListener.listen(45911)) {
            exifRotation = in.readInt();
        }
        if (!ListenerUtil.mutListener.listen(45912)) {
            durationMs = in.readLong();
        }
        if (!ListenerUtil.mutListener.listen(45913)) {
            caption = in.readString();
        }
        if (!ListenerUtil.mutListener.listen(45914)) {
            startTimeMs = in.readLong();
        }
        if (!ListenerUtil.mutListener.listen(45915)) {
            endTimeMs = in.readLong();
        }
        if (!ListenerUtil.mutListener.listen(45916)) {
            flip = in.readInt();
        }
        if (!ListenerUtil.mutListener.listen(45917)) {
            exifFlip = in.readInt();
        }
        if (!ListenerUtil.mutListener.listen(45918)) {
            mimeType = in.readString();
        }
        if (!ListenerUtil.mutListener.listen(45919)) {
            renderingType = in.readInt();
        }
        if (!ListenerUtil.mutListener.listen(45920)) {
            imageScale = in.readInt();
        }
        if (!ListenerUtil.mutListener.listen(45921)) {
            videoSize = in.readInt();
        }
        if (!ListenerUtil.mutListener.listen(45922)) {
            filename = in.readString();
        }
        if (!ListenerUtil.mutListener.listen(45923)) {
            deleteAfterUse = in.readInt() != 0;
        }
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (!ListenerUtil.mutListener.listen(45924)) {
            dest.writeInt(type);
        }
        if (!ListenerUtil.mutListener.listen(45925)) {
            dest.writeParcelable(uri, flags);
        }
        if (!ListenerUtil.mutListener.listen(45926)) {
            dest.writeInt(rotation);
        }
        if (!ListenerUtil.mutListener.listen(45927)) {
            dest.writeInt(exifRotation);
        }
        if (!ListenerUtil.mutListener.listen(45928)) {
            dest.writeLong(durationMs);
        }
        if (!ListenerUtil.mutListener.listen(45929)) {
            dest.writeString(caption);
        }
        if (!ListenerUtil.mutListener.listen(45930)) {
            dest.writeLong(startTimeMs);
        }
        if (!ListenerUtil.mutListener.listen(45931)) {
            dest.writeLong(endTimeMs);
        }
        if (!ListenerUtil.mutListener.listen(45932)) {
            dest.writeInt(flip);
        }
        if (!ListenerUtil.mutListener.listen(45933)) {
            dest.writeInt(exifFlip);
        }
        if (!ListenerUtil.mutListener.listen(45934)) {
            dest.writeString(mimeType);
        }
        if (!ListenerUtil.mutListener.listen(45935)) {
            dest.writeInt(renderingType);
        }
        if (!ListenerUtil.mutListener.listen(45936)) {
            dest.writeInt(imageScale);
        }
        if (!ListenerUtil.mutListener.listen(45937)) {
            dest.writeInt(videoSize);
        }
        if (!ListenerUtil.mutListener.listen(45938)) {
            dest.writeString(filename);
        }
        if (!ListenerUtil.mutListener.listen(45939)) {
            dest.writeInt(deleteAfterUse ? 1 : 0);
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<MediaItem> CREATOR = new Creator<MediaItem>() {

        @Override
        public MediaItem createFromParcel(Parcel in) {
            return new MediaItem(in);
        }

        @Override
        public MediaItem[] newArray(int size) {
            return new MediaItem[size];
        }
    };

    @MediaType
    public int getType() {
        return type;
    }

    public void setType(@MediaType int type) {
        if (!ListenerUtil.mutListener.listen(45940)) {
            this.type = type;
        }
    }

    public Uri getUri() {
        return uri;
    }

    public void setUri(Uri uri) {
        if (!ListenerUtil.mutListener.listen(45941)) {
            this.uri = uri;
        }
    }

    public int getRotation() {
        return rotation;
    }

    public void setRotation(int rotation) {
        if (!ListenerUtil.mutListener.listen(45942)) {
            this.rotation = rotation;
        }
    }

    public int getExifRotation() {
        return exifRotation;
    }

    public void setExifRotation(int exifRotation) {
        if (!ListenerUtil.mutListener.listen(45943)) {
            this.exifRotation = exifRotation;
        }
    }

    public long getDurationMs() {
        return durationMs;
    }

    public void setDurationMs(long durationMs) {
        if (!ListenerUtil.mutListener.listen(45944)) {
            this.durationMs = durationMs;
        }
    }

    @Nullable
    public String getCaption() {
        return caption;
    }

    public void setCaption(@Nullable String caption) {
        if (!ListenerUtil.mutListener.listen(45945)) {
            this.caption = caption;
        }
    }

    public long getStartTimeMs() {
        return startTimeMs;
    }

    public void setStartTimeMs(long startTimeMs) {
        if (!ListenerUtil.mutListener.listen(45946)) {
            this.startTimeMs = startTimeMs;
        }
    }

    public long getEndTimeMs() {
        return endTimeMs;
    }

    public void setEndTimeMs(long endTimeMs) {
        if (!ListenerUtil.mutListener.listen(45947)) {
            this.endTimeMs = endTimeMs;
        }
    }

    public int getFlip() {
        return flip;
    }

    public void setFlip(int flip) {
        if (!ListenerUtil.mutListener.listen(45948)) {
            this.flip = flip;
        }
    }

    @BitmapUtil.FlipType
    public int getExifFlip() {
        return exifFlip;
    }

    public void setExifFlip(@BitmapUtil.FlipType int exifFlip) {
        if (!ListenerUtil.mutListener.listen(45949)) {
            this.exifFlip = exifFlip;
        }
    }

    /**
     *  get MimeType override
     *  @return
     */
    public String getMimeType() {
        return mimeType;
    }

    /**
     *  set MimeType override
     *  @param mimeType
     */
    public void setMimeType(String mimeType) {
        if (!ListenerUtil.mutListener.listen(45950)) {
            this.mimeType = mimeType;
        }
    }

    @FileData.RenderingType
    public int getRenderingType() {
        return renderingType;
    }

    public void setRenderingType(@FileData.RenderingType int renderingType) {
        if (!ListenerUtil.mutListener.listen(45951)) {
            this.renderingType = renderingType;
        }
    }

    @PreferenceService.ImageScale
    public int getImageScale() {
        return imageScale;
    }

    public void setImageScale(@PreferenceService.ImageScale int imageScale) {
        if (!ListenerUtil.mutListener.listen(45952)) {
            this.imageScale = imageScale;
        }
    }

    @PreferenceService.VideoSize
    public int getVideoSize() {
        return videoSize;
    }

    public void setVideoSize(@PreferenceService.VideoSize int videoSize) {
        if (!ListenerUtil.mutListener.listen(45953)) {
            this.videoSize = videoSize;
        }
    }

    @Nullable
    public String getFilename() {
        return filename;
    }

    public void setFilename(@Nullable String filename) {
        if (!ListenerUtil.mutListener.listen(45954)) {
            this.filename = filename;
        }
    }

    public boolean getDeleteAfterUse() {
        return deleteAfterUse;
    }

    /**
     *  Set this flag if the file is temporary and can be deleted after use
     *  @param deleteAfterUse 1 to signal the file is expendable, 0 otherwise
     */
    public void setDeleteAfterUse(boolean deleteAfterUse) {
        if (!ListenerUtil.mutListener.listen(45955)) {
            this.deleteAfterUse = deleteAfterUse;
        }
    }
}
