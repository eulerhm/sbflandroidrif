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
import java.nio.charset.StandardCharsets;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * A group message that has a video including thumbnail (stored on the blob server) as its content.
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
public class GroupVideoMessage extends AbstractGroupMessage {

    private static final Logger logger = LoggerFactory.getLogger(GroupVideoMessage.class);

    private int duration;

    private byte[] videoBlobId;

    private int videoSize;

    private byte[] thumbnailBlobId;

    private int thumbnailSize;

    private byte[] encryptionKey;

    public GroupVideoMessage() {
        super();
    }

    @Override
    public int getType() {
        return ProtocolDefines.MSGTYPE_GROUP_VIDEO;
    }

    @Override
    public boolean shouldPush() {
        return true;
    }

    @Override
    public byte[] getBody() {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            if (!ListenerUtil.mutListener.listen(68345)) {
                bos.write(getGroupCreator().getBytes(StandardCharsets.US_ASCII));
            }
            if (!ListenerUtil.mutListener.listen(68346)) {
                bos.write(getGroupId().getGroupId());
            }
            if (!ListenerUtil.mutListener.listen(68347)) {
                EndianUtils.writeSwappedShort(bos, (short) duration);
            }
            if (!ListenerUtil.mutListener.listen(68348)) {
                bos.write(videoBlobId);
            }
            if (!ListenerUtil.mutListener.listen(68349)) {
                EndianUtils.writeSwappedInteger(bos, videoSize);
            }
            if (!ListenerUtil.mutListener.listen(68350)) {
                bos.write(thumbnailBlobId);
            }
            if (!ListenerUtil.mutListener.listen(68351)) {
                EndianUtils.writeSwappedInteger(bos, thumbnailSize);
            }
            if (!ListenerUtil.mutListener.listen(68352)) {
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
        if (!ListenerUtil.mutListener.listen(68353)) {
            this.duration = duration;
        }
    }

    public byte[] getVideoBlobId() {
        return videoBlobId;
    }

    public void setVideoBlobId(byte[] videoBlobId) {
        if (!ListenerUtil.mutListener.listen(68354)) {
            this.videoBlobId = videoBlobId;
        }
    }

    public int getVideoSize() {
        return videoSize;
    }

    public void setVideoSize(int videoSize) {
        if (!ListenerUtil.mutListener.listen(68355)) {
            this.videoSize = videoSize;
        }
    }

    public byte[] getThumbnailBlobId() {
        return thumbnailBlobId;
    }

    public void setThumbnailBlobId(byte[] thumbnailBlobId) {
        if (!ListenerUtil.mutListener.listen(68356)) {
            this.thumbnailBlobId = thumbnailBlobId;
        }
    }

    public int getThumbnailSize() {
        return thumbnailSize;
    }

    public void setThumbnailSize(int thumbnailSize) {
        if (!ListenerUtil.mutListener.listen(68357)) {
            this.thumbnailSize = thumbnailSize;
        }
    }

    public byte[] getEncryptionKey() {
        return encryptionKey;
    }

    public void setEncryptionKey(byte[] encryptionKey) {
        if (!ListenerUtil.mutListener.listen(68358)) {
            this.encryptionKey = encryptionKey;
        }
    }
}
