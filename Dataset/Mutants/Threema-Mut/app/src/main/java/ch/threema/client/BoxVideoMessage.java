/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema Java Client
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
package ch.threema.client;

import org.apache.commons.io.EndianUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * A message that has a video including thumbnail (stored on the blob server) as its content.
 *
 * The contents are referenced by the {@code videoBlobId}/{@code thumbnailBlobId},
 * the {@code videoSize}/{@code thumbnailSize} in bytes, and the {@code encryptionKey}
 * to be used when decrypting the video blob.
 *
 * The thumbnail uses the same key, the nonces are as follows:
 *
 * Video:     0x000000000000000000000000000000000000000000000001
 * Thumbnail: 0x000000000000000000000000000000000000000000000002
 */
public class BoxVideoMessage extends AbstractMessage {

    private static final Logger logger = LoggerFactory.getLogger(BoxVideoMessage.class);

    private int duration;

    private byte[] videoBlobId;

    private int videoSize;

    private byte[] thumbnailBlobId;

    private int thumbnailSize;

    private byte[] encryptionKey;

    public BoxVideoMessage() {
        super();
    }

    @Override
    public int getType() {
        return ProtocolDefines.MSGTYPE_VIDEO;
    }

    @Override
    public boolean shouldPush() {
        return true;
    }

    @Override
    public byte[] getBody() {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            if (!ListenerUtil.mutListener.listen(69270)) {
                EndianUtils.writeSwappedShort(bos, (short) duration);
            }
            if (!ListenerUtil.mutListener.listen(69271)) {
                bos.write(videoBlobId);
            }
            if (!ListenerUtil.mutListener.listen(69272)) {
                EndianUtils.writeSwappedInteger(bos, videoSize);
            }
            if (!ListenerUtil.mutListener.listen(69273)) {
                bos.write(thumbnailBlobId);
            }
            if (!ListenerUtil.mutListener.listen(69274)) {
                EndianUtils.writeSwappedInteger(bos, thumbnailSize);
            }
            if (!ListenerUtil.mutListener.listen(69275)) {
                bos.write(encryptionKey);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return bos.toByteArray();
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        if (!ListenerUtil.mutListener.listen(69276)) {
            this.duration = duration;
        }
    }

    public byte[] getVideoBlobId() {
        return videoBlobId;
    }

    public void setVideoBlobId(byte[] videoBlobId) {
        if (!ListenerUtil.mutListener.listen(69277)) {
            this.videoBlobId = videoBlobId;
        }
    }

    public int getVideoSize() {
        return videoSize;
    }

    public void setVideoSize(int videoSize) {
        if (!ListenerUtil.mutListener.listen(69278)) {
            this.videoSize = videoSize;
        }
    }

    public byte[] getThumbnailBlobId() {
        return thumbnailBlobId;
    }

    public void setThumbnailBlobId(byte[] thumbnailBlobId) {
        if (!ListenerUtil.mutListener.listen(69279)) {
            this.thumbnailBlobId = thumbnailBlobId;
        }
    }

    public int getThumbnailSize() {
        return thumbnailSize;
    }

    public void setThumbnailSize(int thumbnailSize) {
        if (!ListenerUtil.mutListener.listen(69280)) {
            this.thumbnailSize = thumbnailSize;
        }
    }

    public byte[] getEncryptionKey() {
        return encryptionKey;
    }

    public void setEncryptionKey(byte[] encryptionKey) {
        if (!ListenerUtil.mutListener.listen(69281)) {
            this.encryptionKey = encryptionKey;
        }
    }
}
