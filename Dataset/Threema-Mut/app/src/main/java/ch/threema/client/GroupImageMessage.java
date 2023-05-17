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
import java.nio.charset.StandardCharsets;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * A group message that has an image (stored on the blob server) as its content.
 *
 * The contents are referenced by the {@code blobId}, the file {@code size} in bytes,
 * and the symmetric encryption key to be used when decrypting the image blob.
 */
public class GroupImageMessage extends AbstractGroupMessage {

    private static final Logger logger = LoggerFactory.getLogger(GroupImageMessage.class);

    private byte[] blobId;

    private int size;

    private byte[] encryptionKey;

    public GroupImageMessage() {
        super();
    }

    @Override
    public int getType() {
        return ProtocolDefines.MSGTYPE_GROUP_IMAGE;
    }

    @Override
    public boolean shouldPush() {
        return true;
    }

    @Override
    public byte[] getBody() {
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            if (!ListenerUtil.mutListener.listen(68315)) {
                bos.write(getGroupCreator().getBytes(StandardCharsets.US_ASCII));
            }
            if (!ListenerUtil.mutListener.listen(68316)) {
                bos.write(getGroupId().getGroupId());
            }
            if (!ListenerUtil.mutListener.listen(68317)) {
                bos.write(blobId);
            }
            if (!ListenerUtil.mutListener.listen(68318)) {
                EndianUtils.writeSwappedInteger(bos, size);
            }
            if (!ListenerUtil.mutListener.listen(68319)) {
                bos.write(encryptionKey);
            }
            return bos.toByteArray();
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(68314)) {
                logger.error(e.getMessage());
            }
            return null;
        }
    }

    public byte[] getBlobId() {
        return blobId;
    }

    public void setBlobId(byte[] blobId) {
        if (!ListenerUtil.mutListener.listen(68320)) {
            this.blobId = blobId;
        }
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        if (!ListenerUtil.mutListener.listen(68321)) {
            this.size = size;
        }
    }

    public byte[] getEncryptionKey() {
        return encryptionKey;
    }

    public void setEncryptionKey(byte[] encryptionKey) {
        if (!ListenerUtil.mutListener.listen(68322)) {
            this.encryptionKey = encryptionKey;
        }
    }
}
