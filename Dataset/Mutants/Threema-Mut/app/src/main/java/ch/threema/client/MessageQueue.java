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

import androidx.annotation.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.Iterator;
import java.util.LinkedList;
import ch.threema.base.ThreemaException;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Message queue, used to send messages.
 *
 * This class is thread safe.
 */
public class MessageQueue implements MessageAckListener, ConnectionStateListener {

    private static final Logger logger = LoggerFactory.getLogger(MessageQueue.class);

    private final ContactStoreInterface contactStore;

    private final IdentityStoreInterface identityStore;

    private final ThreemaConnection con;

    private final LinkedList<BoxedMessage> queue;

    public MessageQueue(ContactStoreInterface contactStore, IdentityStoreInterface identityStore, ThreemaConnection con) {
        this.contactStore = contactStore;
        this.identityStore = identityStore;
        this.con = con;
        queue = new LinkedList<>();
        if (!ListenerUtil.mutListener.listen(68513)) {
            /* add ourselves as an ACK listener to the connection */
            con.addMessageAckListener(this);
        }
        if (!ListenerUtil.mutListener.listen(68514)) {
            con.addConnectionStateListener(this);
        }
    }

    public synchronized BoxedMessage enqueue(AbstractMessage message) throws ThreemaException {
        if (!ListenerUtil.mutListener.listen(68515)) {
            if (message == null)
                return null;
        }
        if (!ListenerUtil.mutListener.listen(68516)) {
            logger.debug("Enqueue message");
        }
        if (!ListenerUtil.mutListener.listen(68518)) {
            /* add missing attributes, if necessary */
            if (message.getFromIdentity() == null)
                if (!ListenerUtil.mutListener.listen(68517)) {
                    message.setFromIdentity(identityStore.getIdentity());
                }
        }
        if (!ListenerUtil.mutListener.listen(68520)) {
            if (message.getPushFromName() == null)
                if (!ListenerUtil.mutListener.listen(68519)) {
                    message.setPushFromName(identityStore.getPublicNickname());
                }
        }
        /* make box */
        BoxedMessage boxmsg = message.makeBox(contactStore, identityStore, this.con.getNonceFactory());
        if (!ListenerUtil.mutListener.listen(68521)) {
            if (boxmsg == null)
                return null;
        }
        // for the sake of efficiency: simply deduct overhead size
        final int overhead = ProtocolDefines.OVERHEAD_MSG_HDR + ProtocolDefines.OVERHEAD_NACL_BOX + ProtocolDefines.OVERHEAD_PKT_HDR;
        if (!ListenerUtil.mutListener.listen(68532)) {
            if ((ListenerUtil.mutListener.listen(68531) ? (boxmsg.getBox() != null || (ListenerUtil.mutListener.listen(68530) ? (boxmsg.getBox().length >= ((ListenerUtil.mutListener.listen(68525) ? (ProtocolDefines.MAX_PKT_LEN % overhead) : (ListenerUtil.mutListener.listen(68524) ? (ProtocolDefines.MAX_PKT_LEN / overhead) : (ListenerUtil.mutListener.listen(68523) ? (ProtocolDefines.MAX_PKT_LEN * overhead) : (ListenerUtil.mutListener.listen(68522) ? (ProtocolDefines.MAX_PKT_LEN + overhead) : (ProtocolDefines.MAX_PKT_LEN - overhead))))))) : (ListenerUtil.mutListener.listen(68529) ? (boxmsg.getBox().length <= ((ListenerUtil.mutListener.listen(68525) ? (ProtocolDefines.MAX_PKT_LEN % overhead) : (ListenerUtil.mutListener.listen(68524) ? (ProtocolDefines.MAX_PKT_LEN / overhead) : (ListenerUtil.mutListener.listen(68523) ? (ProtocolDefines.MAX_PKT_LEN * overhead) : (ListenerUtil.mutListener.listen(68522) ? (ProtocolDefines.MAX_PKT_LEN + overhead) : (ProtocolDefines.MAX_PKT_LEN - overhead))))))) : (ListenerUtil.mutListener.listen(68528) ? (boxmsg.getBox().length < ((ListenerUtil.mutListener.listen(68525) ? (ProtocolDefines.MAX_PKT_LEN % overhead) : (ListenerUtil.mutListener.listen(68524) ? (ProtocolDefines.MAX_PKT_LEN / overhead) : (ListenerUtil.mutListener.listen(68523) ? (ProtocolDefines.MAX_PKT_LEN * overhead) : (ListenerUtil.mutListener.listen(68522) ? (ProtocolDefines.MAX_PKT_LEN + overhead) : (ProtocolDefines.MAX_PKT_LEN - overhead))))))) : (ListenerUtil.mutListener.listen(68527) ? (boxmsg.getBox().length != ((ListenerUtil.mutListener.listen(68525) ? (ProtocolDefines.MAX_PKT_LEN % overhead) : (ListenerUtil.mutListener.listen(68524) ? (ProtocolDefines.MAX_PKT_LEN / overhead) : (ListenerUtil.mutListener.listen(68523) ? (ProtocolDefines.MAX_PKT_LEN * overhead) : (ListenerUtil.mutListener.listen(68522) ? (ProtocolDefines.MAX_PKT_LEN + overhead) : (ProtocolDefines.MAX_PKT_LEN - overhead))))))) : (ListenerUtil.mutListener.listen(68526) ? (boxmsg.getBox().length == ((ListenerUtil.mutListener.listen(68525) ? (ProtocolDefines.MAX_PKT_LEN % overhead) : (ListenerUtil.mutListener.listen(68524) ? (ProtocolDefines.MAX_PKT_LEN / overhead) : (ListenerUtil.mutListener.listen(68523) ? (ProtocolDefines.MAX_PKT_LEN * overhead) : (ListenerUtil.mutListener.listen(68522) ? (ProtocolDefines.MAX_PKT_LEN + overhead) : (ProtocolDefines.MAX_PKT_LEN - overhead))))))) : (boxmsg.getBox().length > ((ListenerUtil.mutListener.listen(68525) ? (ProtocolDefines.MAX_PKT_LEN % overhead) : (ListenerUtil.mutListener.listen(68524) ? (ProtocolDefines.MAX_PKT_LEN / overhead) : (ListenerUtil.mutListener.listen(68523) ? (ProtocolDefines.MAX_PKT_LEN * overhead) : (ListenerUtil.mutListener.listen(68522) ? (ProtocolDefines.MAX_PKT_LEN + overhead) : (ProtocolDefines.MAX_PKT_LEN - overhead))))))))))))) : (boxmsg.getBox() != null && (ListenerUtil.mutListener.listen(68530) ? (boxmsg.getBox().length >= ((ListenerUtil.mutListener.listen(68525) ? (ProtocolDefines.MAX_PKT_LEN % overhead) : (ListenerUtil.mutListener.listen(68524) ? (ProtocolDefines.MAX_PKT_LEN / overhead) : (ListenerUtil.mutListener.listen(68523) ? (ProtocolDefines.MAX_PKT_LEN * overhead) : (ListenerUtil.mutListener.listen(68522) ? (ProtocolDefines.MAX_PKT_LEN + overhead) : (ProtocolDefines.MAX_PKT_LEN - overhead))))))) : (ListenerUtil.mutListener.listen(68529) ? (boxmsg.getBox().length <= ((ListenerUtil.mutListener.listen(68525) ? (ProtocolDefines.MAX_PKT_LEN % overhead) : (ListenerUtil.mutListener.listen(68524) ? (ProtocolDefines.MAX_PKT_LEN / overhead) : (ListenerUtil.mutListener.listen(68523) ? (ProtocolDefines.MAX_PKT_LEN * overhead) : (ListenerUtil.mutListener.listen(68522) ? (ProtocolDefines.MAX_PKT_LEN + overhead) : (ProtocolDefines.MAX_PKT_LEN - overhead))))))) : (ListenerUtil.mutListener.listen(68528) ? (boxmsg.getBox().length < ((ListenerUtil.mutListener.listen(68525) ? (ProtocolDefines.MAX_PKT_LEN % overhead) : (ListenerUtil.mutListener.listen(68524) ? (ProtocolDefines.MAX_PKT_LEN / overhead) : (ListenerUtil.mutListener.listen(68523) ? (ProtocolDefines.MAX_PKT_LEN * overhead) : (ListenerUtil.mutListener.listen(68522) ? (ProtocolDefines.MAX_PKT_LEN + overhead) : (ProtocolDefines.MAX_PKT_LEN - overhead))))))) : (ListenerUtil.mutListener.listen(68527) ? (boxmsg.getBox().length != ((ListenerUtil.mutListener.listen(68525) ? (ProtocolDefines.MAX_PKT_LEN % overhead) : (ListenerUtil.mutListener.listen(68524) ? (ProtocolDefines.MAX_PKT_LEN / overhead) : (ListenerUtil.mutListener.listen(68523) ? (ProtocolDefines.MAX_PKT_LEN * overhead) : (ListenerUtil.mutListener.listen(68522) ? (ProtocolDefines.MAX_PKT_LEN + overhead) : (ProtocolDefines.MAX_PKT_LEN - overhead))))))) : (ListenerUtil.mutListener.listen(68526) ? (boxmsg.getBox().length == ((ListenerUtil.mutListener.listen(68525) ? (ProtocolDefines.MAX_PKT_LEN % overhead) : (ListenerUtil.mutListener.listen(68524) ? (ProtocolDefines.MAX_PKT_LEN / overhead) : (ListenerUtil.mutListener.listen(68523) ? (ProtocolDefines.MAX_PKT_LEN * overhead) : (ListenerUtil.mutListener.listen(68522) ? (ProtocolDefines.MAX_PKT_LEN + overhead) : (ProtocolDefines.MAX_PKT_LEN - overhead))))))) : (boxmsg.getBox().length > ((ListenerUtil.mutListener.listen(68525) ? (ProtocolDefines.MAX_PKT_LEN % overhead) : (ListenerUtil.mutListener.listen(68524) ? (ProtocolDefines.MAX_PKT_LEN / overhead) : (ListenerUtil.mutListener.listen(68523) ? (ProtocolDefines.MAX_PKT_LEN * overhead) : (ListenerUtil.mutListener.listen(68522) ? (ProtocolDefines.MAX_PKT_LEN + overhead) : (ProtocolDefines.MAX_PKT_LEN - overhead))))))))))))))) {
                throw new MessageTooLongException();
            }
        }
        if (!ListenerUtil.mutListener.listen(68540)) {
            if (con.getConnectionState() == ConnectionState.LOGGEDIN) {
                if (!ListenerUtil.mutListener.listen(68536)) {
                    logger.debug("Currently connected - sending message now");
                }
                if (!ListenerUtil.mutListener.listen(68537)) {
                    con.sendBoxedMessage(boxmsg);
                }
                if (!ListenerUtil.mutListener.listen(68539)) {
                    /* Only add to queue if we want an ACK for this message */
                    if (!message.isNoAck())
                        if (!ListenerUtil.mutListener.listen(68538)) {
                            queue.add(boxmsg);
                        }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(68535)) {
                    if (message.isImmediate()) {
                        if (!ListenerUtil.mutListener.listen(68534)) {
                            logger.debug("Discarding immediate message because not connected");
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(68533)) {
                            queue.add(boxmsg);
                        }
                    }
                }
            }
        }
        return boxmsg;
    }

    public synchronized boolean isQueued(MessageId messageId) {
        if (!ListenerUtil.mutListener.listen(68542)) {
            {
                long _loopCounter859 = 0;
                for (BoxedMessage boxmsg : queue) {
                    ListenerUtil.loopListener.listen("_loopCounter859", ++_loopCounter859);
                    if (!ListenerUtil.mutListener.listen(68541)) {
                        if (boxmsg.getMessageId().equals(messageId))
                            return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     *  Remove a message from the message queue if it's in the queue
     *  @param messageId MessageID of the message to remove
     *  @return true if a message has been dequeued, false otherwise
     */
    public synchronized boolean dequeue(MessageId messageId) {
        Iterator<BoxedMessage> it = queue.iterator();
        if (!ListenerUtil.mutListener.listen(68546)) {
            {
                long _loopCounter860 = 0;
                while (it.hasNext()) {
                    ListenerUtil.loopListener.listen("_loopCounter860", ++_loopCounter860);
                    MessageId id = it.next().getMessageId();
                    if (!ListenerUtil.mutListener.listen(68545)) {
                        if ((ListenerUtil.mutListener.listen(68543) ? (id != null || id.equals(messageId)) : (id != null && id.equals(messageId)))) {
                            if (!ListenerUtil.mutListener.listen(68544)) {
                                it.remove();
                            }
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public synchronized void processAck(@NonNull MessageAck messageAck) {
        if (!ListenerUtil.mutListener.listen(68547)) {
            logger.debug("Processing server ack for message ID {} from {}", messageAck.getMessageId(), messageAck.getRecipientId());
        }
        // Find this message in the queue and remove it
        final Iterator<BoxedMessage> it = queue.iterator();
        if (!ListenerUtil.mutListener.listen(68551)) {
            {
                long _loopCounter861 = 0;
                while (it.hasNext()) {
                    ListenerUtil.loopListener.listen("_loopCounter861", ++_loopCounter861);
                    final BoxedMessage next = it.next();
                    if (!ListenerUtil.mutListener.listen(68550)) {
                        // Compare both message ID and recipient ID
                        if ((ListenerUtil.mutListener.listen(68548) ? (next.getMessageId().equals(messageAck.getMessageId()) || next.getToIdentity().equals(messageAck.getRecipientId())) : (next.getMessageId().equals(messageAck.getMessageId()) && next.getToIdentity().equals(messageAck.getRecipientId())))) {
                            if (!ListenerUtil.mutListener.listen(68549)) {
                                it.remove();
                            }
                            return;
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(68552)) {
            logger.warn("Message ID {} from {} not found in queue", messageAck.getMessageId(), messageAck.getRecipientId());
        }
    }

    public synchronized int getQueueSize() {
        return queue.size();
    }

    private synchronized void processQueue() {
        if (!ListenerUtil.mutListener.listen(68553)) {
            logger.info("Processing queue");
        }
        if (!ListenerUtil.mutListener.listen(68555)) {
            {
                long _loopCounter862 = 0;
                /* Send all messages in our queue */
                for (BoxedMessage boxmsg : queue) {
                    ListenerUtil.loopListener.listen("_loopCounter862", ++_loopCounter862);
                    if (!ListenerUtil.mutListener.listen(68554)) {
                        con.sendBoxedMessage(boxmsg);
                    }
                }
            }
        }
    }

    public void updateConnectionState(ConnectionState connectionState, InetSocketAddress socketAddress) {
        if (!ListenerUtil.mutListener.listen(68557)) {
            if (connectionState == ConnectionState.LOGGEDIN)
                if (!ListenerUtil.mutListener.listen(68556)) {
                    processQueue();
                }
        }
    }

    public synchronized void serializeToStream(OutputStream os) throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(os);
        if (!ListenerUtil.mutListener.listen(68559)) {
            {
                long _loopCounter863 = 0;
                for (BoxedMessage msg : queue) {
                    ListenerUtil.loopListener.listen("_loopCounter863", ++_loopCounter863);
                    if (!ListenerUtil.mutListener.listen(68558)) {
                        oos.writeObject(msg);
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(68560)) {
            oos.close();
        }
    }

    public synchronized void unserializeFromStream(InputStream is) throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(is);
        String myId = identityStore.getIdentity();
        if (!ListenerUtil.mutListener.listen(68561)) {
            if (myId == null)
                return;
        }
        if (!ListenerUtil.mutListener.listen(68564)) {
            {
                long _loopCounter864 = 0;
                while (true) {
                    ListenerUtil.loopListener.listen("_loopCounter864", ++_loopCounter864);
                    try {
                        BoxedMessage msg = (BoxedMessage) ois.readObject();
                        if (!ListenerUtil.mutListener.listen(68562)) {
                            /* make sure this message matches our own current ID (the user may have switched IDs in the meantime) */
                            if (!myId.equals(msg.getFromIdentity()))
                                continue;
                        }
                        if (!ListenerUtil.mutListener.listen(68563)) {
                            queue.add(msg);
                        }
                    } catch (EOFException e) {
                        break;
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(68565)) {
            ois.close();
        }
        if (!ListenerUtil.mutListener.listen(68566)) {
            processQueue();
        }
    }
}
