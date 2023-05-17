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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * A group message that has plain text as its contents.
 */
public class GroupTextMessage extends AbstractGroupMessage {

    private static final Logger logger = LoggerFactory.getLogger(GroupTextMessage.class);

    private String text;

    public GroupTextMessage() {
        super();
    }

    @Override
    public int getType() {
        return ProtocolDefines.MSGTYPE_GROUP_TEXT;
    }

    @Override
    public boolean shouldPush() {
        return true;
    }

    @Override
    public byte[] getBody() {
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            if (!ListenerUtil.mutListener.listen(68341)) {
                bos.write(getGroupCreator().getBytes(StandardCharsets.US_ASCII));
            }
            if (!ListenerUtil.mutListener.listen(68342)) {
                bos.write(getGroupId().getGroupId());
            }
            if (!ListenerUtil.mutListener.listen(68343)) {
                bos.write(text.getBytes(StandardCharsets.UTF_8));
            }
            return bos.toByteArray();
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(68340)) {
                logger.error(e.getMessage());
            }
            return null;
        }
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        if (!ListenerUtil.mutListener.listen(68344)) {
            this.text = text;
        }
    }
}
