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
 * A profile picture uploaded as a blob
 *
 * The contents are referenced by the {@code blobId}, the file {@code size} in bytes,
 * and the nonce to be used when decrypting the image blob.
 */
public class ContactSetPhotoMessage extends AbstractMessage {

    private static final Logger logger = LoggerFactory.getLogger(ContactSetPhotoMessage.class);

    private byte[] blobId;

    private int size;

    private byte[] encryptionKey;

    public ContactSetPhotoMessage() {
        super();
    }

    @Override
    public int getType() {
        return ProtocolDefines.MSGTYPE_CONTACT_SET_PHOTO;
    }

    @Override
    public byte[] getBody() {
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            if (!ListenerUtil.mutListener.listen(68279)) {
                bos.write(blobId);
            }
            if (!ListenerUtil.mutListener.listen(68280)) {
                EndianUtils.writeSwappedInteger(bos, size);
            }
            if (!ListenerUtil.mutListener.listen(68281)) {
                bos.write(encryptionKey);
            }
            return bos.toByteArray();
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(68278)) {
                logger.error(e.getMessage());
            }
            return null;
        }
    }

    public byte[] getBlobId() {
        return blobId;
    }

    public void setBlobId(byte[] blobId) {
        if (!ListenerUtil.mutListener.listen(68282)) {
            this.blobId = blobId;
        }
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        if (!ListenerUtil.mutListener.listen(68283)) {
            this.size = size;
        }
    }

    public byte[] getEncryptionKey() {
        return encryptionKey;
    }

    public void setEncryptionKey(byte[] encryptionKey) {
        if (!ListenerUtil.mutListener.listen(68284)) {
            this.encryptionKey = encryptionKey;
        }
    }
}
