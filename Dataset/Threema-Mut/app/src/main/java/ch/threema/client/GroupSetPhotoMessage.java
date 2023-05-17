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
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * A group message sent by the group creator that causes a new photo to be set for the group.
 */
public class GroupSetPhotoMessage extends AbstractGroupMessage {

    private static final Logger logger = LoggerFactory.getLogger(GroupSetPhotoMessage.class);

    private byte[] blobId;

    private int size;

    private byte[] encryptionKey;

    public GroupSetPhotoMessage() {
        super();
    }

    @Override
    public int getType() {
        return ProtocolDefines.MSGTYPE_GROUP_SET_PHOTO;
    }

    @Override
    public byte[] getBody() {
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            if (!ListenerUtil.mutListener.listen(68333)) {
                bos.write(getGroupId().getGroupId());
            }
            if (!ListenerUtil.mutListener.listen(68334)) {
                bos.write(blobId);
            }
            if (!ListenerUtil.mutListener.listen(68335)) {
                EndianUtils.writeSwappedInteger(bos, size);
            }
            if (!ListenerUtil.mutListener.listen(68336)) {
                bos.write(encryptionKey);
            }
            return bos.toByteArray();
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(68332)) {
                logger.error(e.getMessage());
            }
            return null;
        }
    }

    public byte[] getBlobId() {
        return blobId;
    }

    public void setBlobId(byte[] blobId) {
        if (!ListenerUtil.mutListener.listen(68337)) {
            this.blobId = blobId;
        }
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        if (!ListenerUtil.mutListener.listen(68338)) {
            this.size = size;
        }
    }

    public byte[] getEncryptionKey() {
        return encryptionKey;
    }

    public void setEncryptionKey(byte[] encryptionKey) {
        if (!ListenerUtil.mutListener.listen(68339)) {
            this.encryptionKey = encryptionKey;
        }
    }
}
