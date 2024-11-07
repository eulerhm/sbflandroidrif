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
package ch.threema.storage.models.data.media;

import android.util.JsonReader;
import android.util.JsonWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.StringReader;
import java.io.StringWriter;
import androidx.annotation.NonNull;
import ch.threema.app.utils.StringConversionUtil;
import ch.threema.client.Utils;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class VideoDataModel implements MediaMessageDataInterface {

    private static final Logger logger = LoggerFactory.getLogger(VideoDataModel.class);

    private int duration, videoSize;

    private byte[] videoBlobId;

    private byte[] encryptionKey;

    private boolean isDownloaded;

    private VideoDataModel() {
    }

    public VideoDataModel(int duration, int videoSize, byte[] videoBlobId, byte[] encryptedKey) {
        if (!ListenerUtil.mutListener.listen(70744)) {
            this.duration = duration;
        }
        if (!ListenerUtil.mutListener.listen(70745)) {
            this.videoBlobId = videoBlobId;
        }
        if (!ListenerUtil.mutListener.listen(70746)) {
            this.encryptionKey = encryptedKey;
        }
        if (!ListenerUtil.mutListener.listen(70747)) {
            this.isDownloaded = false;
        }
        if (!ListenerUtil.mutListener.listen(70748)) {
            this.videoSize = videoSize;
        }
    }

    public VideoDataModel(int duration, int videoSize, boolean isDownloaded) {
        if (!ListenerUtil.mutListener.listen(70749)) {
            this.duration = duration;
        }
        if (!ListenerUtil.mutListener.listen(70750)) {
            this.videoSize = videoSize;
        }
        if (!ListenerUtil.mutListener.listen(70751)) {
            this.isDownloaded = isDownloaded;
        }
        if (!ListenerUtil.mutListener.listen(70752)) {
            this.videoBlobId = new byte[0];
        }
        if (!ListenerUtil.mutListener.listen(70753)) {
            this.encryptionKey = new byte[0];
        }
    }

    public int getDuration() {
        return this.duration;
    }

    public int getVideoSize() {
        return this.videoSize;
    }

    @Override
    public byte[] getBlobId() {
        return this.videoBlobId;
    }

    @Override
    public byte[] getEncryptionKey() {
        return this.encryptionKey;
    }

    @Override
    public boolean isDownloaded() {
        return this.isDownloaded;
    }

    @Override
    public void isDownloaded(boolean isDownloaded) {
        if (!ListenerUtil.mutListener.listen(70754)) {
            this.isDownloaded = isDownloaded;
        }
    }

    @Override
    public byte[] getNonce() {
        return new byte[0];
    }

    public String getDurationString() {
        try {
            int duration = getDuration();
            if (!ListenerUtil.mutListener.listen(70760)) {
                if ((ListenerUtil.mutListener.listen(70759) ? (duration >= 0) : (ListenerUtil.mutListener.listen(70758) ? (duration <= 0) : (ListenerUtil.mutListener.listen(70757) ? (duration < 0) : (ListenerUtil.mutListener.listen(70756) ? (duration != 0) : (ListenerUtil.mutListener.listen(70755) ? (duration == 0) : (duration > 0))))))) {
                    return StringConversionUtil.secondsToString(duration, false);
                }
            }
        } catch (Exception ignored) {
        }
        return null;
    }

    public void fromString(@NonNull String s) {
        JsonReader r = new JsonReader(new StringReader(s));
        try {
            if (!ListenerUtil.mutListener.listen(70762)) {
                r.beginArray();
            }
            if (!ListenerUtil.mutListener.listen(70763)) {
                this.duration = r.nextInt();
            }
            if (!ListenerUtil.mutListener.listen(70764)) {
                this.isDownloaded = r.nextBoolean();
            }
            if (!ListenerUtil.mutListener.listen(70765)) {
                this.encryptionKey = Utils.hexStringToByteArray(r.nextString());
            }
            if (!ListenerUtil.mutListener.listen(70766)) {
                this.videoBlobId = Utils.hexStringToByteArray(r.nextString());
            }
            if (!ListenerUtil.mutListener.listen(70768)) {
                if (r.hasNext()) {
                    if (!ListenerUtil.mutListener.listen(70767)) {
                        this.videoSize = r.nextInt();
                    }
                }
            }
        } catch (Exception x) {
            if (!ListenerUtil.mutListener.listen(70761)) {
                logger.error("Exception", x);
            }
        }
    }

    @Override
    public String toString() {
        StringWriter sw = new StringWriter();
        JsonWriter j = new JsonWriter(sw);
        try {
            if (!ListenerUtil.mutListener.listen(70770)) {
                j.beginArray();
            }
            if (!ListenerUtil.mutListener.listen(70771)) {
                j.value(this.getDuration()).value(this.isDownloaded()).value(Utils.byteArrayToHexString(this.getEncryptionKey())).value(Utils.byteArrayToHexString(this.getBlobId())).value(this.getVideoSize());
            }
            if (!ListenerUtil.mutListener.listen(70772)) {
                j.endArray();
            }
        } catch (Exception x) {
            if (!ListenerUtil.mutListener.listen(70769)) {
                logger.error("Exception", x);
            }
            return null;
        }
        return sw.toString();
    }

    /**
     *  Convert a FileDataModel (containing video data) to a VideoDataModel.
     *
     *  This method should only be used for backwards compatibility!
     */
    public static VideoDataModel fromFileData(@NonNull FileDataModel fileDataModel) {
        final int duration = (int) Math.min(fileDataModel.getDuration(), (long) Integer.MAX_VALUE);
        final int size = (int) Math.min(fileDataModel.getFileSize(), (long) Integer.MAX_VALUE);
        return new VideoDataModel(duration, size, fileDataModel.getBlobId(), fileDataModel.getEncryptionKey());
    }

    public static VideoDataModel create(@NonNull String s) {
        VideoDataModel m = new VideoDataModel();
        if (!ListenerUtil.mutListener.listen(70773)) {
            m.fromString(s);
        }
        return m;
    }
}
