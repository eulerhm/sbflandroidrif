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
package ch.threema.client.file;

import ch.threema.client.AbstractGroupMessage;
import ch.threema.client.ProtocolDefines;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class GroupFileMessage extends AbstractGroupMessage implements FileMessageInterface {

    private static final Logger logger = LoggerFactory.getLogger(GroupFileMessage.class);

    private FileData fileData;

    public GroupFileMessage() {
        super();
    }

    @Override
    public boolean shouldPush() {
        return true;
    }

    @Override
    public void setData(FileData ballotData) {
        if (!ListenerUtil.mutListener.listen(66073)) {
            this.fileData = ballotData;
        }
    }

    @Override
    public FileData getData() {
        return this.fileData;
    }

    @Override
    public byte[] getBody() {
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            if (!ListenerUtil.mutListener.listen(66075)) {
                // ballot stuff
                bos.write(this.getGroupCreator().getBytes(StandardCharsets.US_ASCII));
            }
            if (!ListenerUtil.mutListener.listen(66076)) {
                bos.write(this.getGroupId().getGroupId());
            }
            if (!ListenerUtil.mutListener.listen(66077)) {
                this.fileData.write(bos);
            }
            return bos.toByteArray();
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(66074)) {
                logger.error(e.getMessage());
            }
            return null;
        }
    }

    @Override
    public int getType() {
        return ProtocolDefines.MSGTYPE_GROUP_FILE;
    }
}
