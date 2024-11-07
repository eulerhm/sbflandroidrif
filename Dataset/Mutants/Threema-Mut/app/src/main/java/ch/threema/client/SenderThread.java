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

import androidx.annotation.AnyThread;
import com.neilalexander.jnacl.NaCl;
import org.apache.commons.io.EndianUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.io.OutputStream;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import static com.neilalexander.jnacl.NaCl.BOXOVERHEAD;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Thread invoked by {@link ThreemaConnection} to send outgoing messages to the server while the
 * main ThreemaConnection thread is blocked for receiving incoming messages. Maintains a queue
 * so that new outgoing packets can be enqueued without waiting.
 */
class SenderThread extends Thread {

    private static final Logger logger = LoggerFactory.getLogger(SenderThread.class);

    private final OutputStream os;

    private final NaCl kclientTempServerTemp;

    private final NonceCounter clientNonce;

    private final BlockingQueue<Payload> sendQueue;

    private boolean running;

    /**
     *  Instantiate a new SenderThread instance.
     *
     *  @param os The {@link OutputStream} used to send data to server.
     *  @param kclientTempServerTemp The NaCl keypair used to communicate securely with the server.
     *  @param clientNonce The nonce counter associated with this server connection.
     */
    public SenderThread(OutputStream os, NaCl kclientTempServerTemp, NonceCounter clientNonce) {
        super("SenderThread");
        this.os = os;
        this.kclientTempServerTemp = kclientTempServerTemp;
        this.clientNonce = clientNonce;
        this.sendQueue = new LinkedBlockingQueue<>();
        if (!ListenerUtil.mutListener.listen(68740)) {
            this.running = true;
        }
    }

    /**
     *  Enqueue a new payload to be sent to the server as soon as possible. Note that the payload may
     *  be lost if the server connection breaks.
     */
    @AnyThread
    public void sendPayload(Payload payload) {
        if (!ListenerUtil.mutListener.listen(68741)) {
            this.sendQueue.add(payload);
        }
    }

    @Override
    public void run() {
        if (!ListenerUtil.mutListener.listen(68742)) {
            logger.info("Started");
        }
        if (!ListenerUtil.mutListener.listen(68762)) {
            {
                long _loopCounter872 = 0;
                while (running) {
                    ListenerUtil.loopListener.listen("_loopCounter872", ++_loopCounter872);
                    // so we don't need to explicitly check `Thread.interrupted()` here.
                    try {
                        if (!ListenerUtil.mutListener.listen(68745)) {
                            logger.info("Get payload from SendQueue.");
                        }
                        Payload payload = sendQueue.take();
                        if (!ListenerUtil.mutListener.listen(68746)) {
                            logger.info("{} entries left", sendQueue.size());
                        }
                        /* encrypt payload */
                        byte[] pktdata = payload.makePacket();
                        if (!ListenerUtil.mutListener.listen(68757)) {
                            if ((ListenerUtil.mutListener.listen(68755) ? (pktdata.length >= ((ListenerUtil.mutListener.listen(68750) ? (ProtocolDefines.MAX_PKT_LEN % BOXOVERHEAD) : (ListenerUtil.mutListener.listen(68749) ? (ProtocolDefines.MAX_PKT_LEN / BOXOVERHEAD) : (ListenerUtil.mutListener.listen(68748) ? (ProtocolDefines.MAX_PKT_LEN * BOXOVERHEAD) : (ListenerUtil.mutListener.listen(68747) ? (ProtocolDefines.MAX_PKT_LEN + BOXOVERHEAD) : (ProtocolDefines.MAX_PKT_LEN - BOXOVERHEAD))))))) : (ListenerUtil.mutListener.listen(68754) ? (pktdata.length <= ((ListenerUtil.mutListener.listen(68750) ? (ProtocolDefines.MAX_PKT_LEN % BOXOVERHEAD) : (ListenerUtil.mutListener.listen(68749) ? (ProtocolDefines.MAX_PKT_LEN / BOXOVERHEAD) : (ListenerUtil.mutListener.listen(68748) ? (ProtocolDefines.MAX_PKT_LEN * BOXOVERHEAD) : (ListenerUtil.mutListener.listen(68747) ? (ProtocolDefines.MAX_PKT_LEN + BOXOVERHEAD) : (ProtocolDefines.MAX_PKT_LEN - BOXOVERHEAD))))))) : (ListenerUtil.mutListener.listen(68753) ? (pktdata.length < ((ListenerUtil.mutListener.listen(68750) ? (ProtocolDefines.MAX_PKT_LEN % BOXOVERHEAD) : (ListenerUtil.mutListener.listen(68749) ? (ProtocolDefines.MAX_PKT_LEN / BOXOVERHEAD) : (ListenerUtil.mutListener.listen(68748) ? (ProtocolDefines.MAX_PKT_LEN * BOXOVERHEAD) : (ListenerUtil.mutListener.listen(68747) ? (ProtocolDefines.MAX_PKT_LEN + BOXOVERHEAD) : (ProtocolDefines.MAX_PKT_LEN - BOXOVERHEAD))))))) : (ListenerUtil.mutListener.listen(68752) ? (pktdata.length != ((ListenerUtil.mutListener.listen(68750) ? (ProtocolDefines.MAX_PKT_LEN % BOXOVERHEAD) : (ListenerUtil.mutListener.listen(68749) ? (ProtocolDefines.MAX_PKT_LEN / BOXOVERHEAD) : (ListenerUtil.mutListener.listen(68748) ? (ProtocolDefines.MAX_PKT_LEN * BOXOVERHEAD) : (ListenerUtil.mutListener.listen(68747) ? (ProtocolDefines.MAX_PKT_LEN + BOXOVERHEAD) : (ProtocolDefines.MAX_PKT_LEN - BOXOVERHEAD))))))) : (ListenerUtil.mutListener.listen(68751) ? (pktdata.length == ((ListenerUtil.mutListener.listen(68750) ? (ProtocolDefines.MAX_PKT_LEN % BOXOVERHEAD) : (ListenerUtil.mutListener.listen(68749) ? (ProtocolDefines.MAX_PKT_LEN / BOXOVERHEAD) : (ListenerUtil.mutListener.listen(68748) ? (ProtocolDefines.MAX_PKT_LEN * BOXOVERHEAD) : (ListenerUtil.mutListener.listen(68747) ? (ProtocolDefines.MAX_PKT_LEN + BOXOVERHEAD) : (ProtocolDefines.MAX_PKT_LEN - BOXOVERHEAD))))))) : (pktdata.length > ((ListenerUtil.mutListener.listen(68750) ? (ProtocolDefines.MAX_PKT_LEN % BOXOVERHEAD) : (ListenerUtil.mutListener.listen(68749) ? (ProtocolDefines.MAX_PKT_LEN / BOXOVERHEAD) : (ListenerUtil.mutListener.listen(68748) ? (ProtocolDefines.MAX_PKT_LEN * BOXOVERHEAD) : (ListenerUtil.mutListener.listen(68747) ? (ProtocolDefines.MAX_PKT_LEN + BOXOVERHEAD) : (ProtocolDefines.MAX_PKT_LEN - BOXOVERHEAD))))))))))))) {
                                if (!ListenerUtil.mutListener.listen(68756)) {
                                    logger.info("Packet is too big ({}) - cannot send", pktdata.length);
                                }
                                continue;
                            }
                        }
                        byte[] pktBox = kclientTempServerTemp.encrypt(pktdata, clientNonce.nextNonce());
                        if (!ListenerUtil.mutListener.listen(68758)) {
                            EndianUtils.writeSwappedShort(os, (short) pktBox.length);
                        }
                        if (!ListenerUtil.mutListener.listen(68759)) {
                            os.write(pktBox);
                        }
                        if (!ListenerUtil.mutListener.listen(68760)) {
                            os.flush();
                        }
                        if (!ListenerUtil.mutListener.listen(68761)) {
                            logger.info("Message payload successfully sent. Size = {} - Type = {}", pktBox.length, Utils.byteToHex((byte) payload.getType(), true, true));
                        }
                    } catch (InterruptedException e) {
                        if (!ListenerUtil.mutListener.listen(68743)) {
                            logger.info("Interrupted");
                        }
                        break;
                    } catch (IOException e) {
                        if (!ListenerUtil.mutListener.listen(68744)) {
                            logger.info("Exception in sender thread", e);
                        }
                        break;
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(68763)) {
            logger.info("Ended");
        }
    }

    /**
     *  Shut down the sender thread.
     */
    public void shutdown() {
        if (!ListenerUtil.mutListener.listen(68764)) {
            running = false;
        }
        if (!ListenerUtil.mutListener.listen(68765)) {
            interrupt();
        }
    }
}
