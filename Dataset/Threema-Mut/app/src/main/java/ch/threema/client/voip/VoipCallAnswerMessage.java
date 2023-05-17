/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema Java Client
 * Copyright (c) 2017-2021 Threema GmbH
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
package ch.threema.client.voip;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.ByteArrayOutputStream;
import ch.threema.client.ProtocolDefines;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class VoipCallAnswerMessage extends VoipMessage {

    private static final Logger logger = LoggerFactory.getLogger(VoipCallAnswerMessage.class);

    private VoipCallAnswerData callAnswerData;

    public VoipCallAnswerMessage() {
        super();
    }

    public VoipCallAnswerMessage setData(VoipCallAnswerData callAnswerData) {
        if (!ListenerUtil.mutListener.listen(66155)) {
            this.callAnswerData = callAnswerData;
        }
        return this;
    }

    public VoipCallAnswerData getData() {
        return this.callAnswerData;
    }

    @Override
    public byte[] getBody() {
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            if (!ListenerUtil.mutListener.listen(66157)) {
                this.callAnswerData.write(bos);
            }
            return bos.toByteArray();
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(66156)) {
                logger.error(e.getMessage());
            }
            return null;
        }
    }

    @Override
    public int getType() {
        return ProtocolDefines.MSGTYPE_VOIP_CALL_ANSWER;
    }
}
