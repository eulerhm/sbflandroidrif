/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
 * Copyright (c) 2019-2021 Threema GmbH
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
package ch.threema.app.webclient.webrtc;

import androidx.annotation.AnyThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import org.saltyrtc.chunkedDc.Chunker;
import org.saltyrtc.chunkedDc.Unchunker;
import org.saltyrtc.client.crypto.CryptoException;
import org.saltyrtc.client.exceptions.OverflowException;
import org.saltyrtc.client.exceptions.ProtocolException;
import org.saltyrtc.client.exceptions.ValidationError;
import org.saltyrtc.client.keystore.Box;
import org.saltyrtc.tasks.webrtc.WebRTCTask;
import org.saltyrtc.tasks.webrtc.crypto.DataChannelCryptoContext;
import org.saltyrtc.tasks.webrtc.exceptions.IllegalStateError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.webrtc.DataChannel;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Objects;
import java.util.concurrent.ExecutionException;
import ch.threema.annotation.SameThread;
import ch.threema.app.webclient.exceptions.WouldBlockException;
import ch.threema.app.webrtc.FlowControlledDataChannel;
import ch.threema.logging.ThreemaLogger;
import java8.util.concurrent.CompletableFuture;
import java8.util.function.Function;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Wraps a flow-controlled (sender-side) data channel, applies additional
 * encryption and fragmentation/reassembly when sending/receiving depending
 * on the parameters provided.
 *
 * Important: The passed executor MUST always schedule execution on the same
 *            thread that is being used for any other method!
 */
@SameThread
public class DataChannelContext {

    private static final long MAX_CHUNK_SIZE = 256 * 1024;

    @NonNull
    private final Logger logger = LoggerFactory.getLogger(DataChannelContext.class);

    @NonNull
    private final DataChannel dc;

    @NonNull
    public final FlowControlledDataChannel fcdc;

    @Nullable
    private final DataChannelCryptoContext crypto;

    @Nullable
    private Unchunker unchunker;

    @NonNull
    private CompletableFuture<Void> queue;

    private final int chunkLength;

    private long messageId = 0;

    public DataChannelContext(@NonNull final String logPrefix, @NonNull final DataChannel dc, @NonNull final WebRTCTask task, final long maxMessageSize, @NonNull final Unchunker.MessageListener messageListener) {
        if (!ListenerUtil.mutListener.listen(64799)) {
            // Set logger prefix
            if (logger instanceof ThreemaLogger) {
                if (!ListenerUtil.mutListener.listen(64798)) {
                    ((ThreemaLogger) logger).setPrefix(logPrefix + "." + dc.label() + "/" + dc.id());
                }
            }
        }
        this.dc = dc;
        // Wrap as flow-controlled data channel
        this.fcdc = new FlowControlledDataChannel(logPrefix, dc);
        // Create crypto context
        this.crypto = task.createCryptoContext(dc.id());
        if (!ListenerUtil.mutListener.listen(64800)) {
            // Create unchunker
            this.unchunker = new Unchunker();
        }
        if (!ListenerUtil.mutListener.listen(64806)) {
            this.unchunker.onMessage(new Unchunker.MessageListener() {

                @Override
                @SameThread
                public void onMessage(ByteBuffer buffer) {
                    // Decrypt message
                    final Box box = new Box(buffer, DataChannelCryptoContext.NONCE_LENGTH);
                    try {
                        if (!ListenerUtil.mutListener.listen(64803)) {
                            buffer = ByteBuffer.wrap(Objects.requireNonNull(DataChannelContext.this.crypto).decrypt(box));
                        }
                    } catch (ValidationError | ProtocolException error) {
                        if (!ListenerUtil.mutListener.listen(64801)) {
                            logger.error("Invalid packet received", error);
                        }
                        return;
                    } catch (CryptoException error) {
                        if (!ListenerUtil.mutListener.listen(64802)) {
                            logger.error("Unable to decrypt", error);
                        }
                        return;
                    }
                    if (!ListenerUtil.mutListener.listen(64804)) {
                        // Hand out message
                        logger.debug("Incoming message of length {}", buffer.remaining());
                    }
                    if (!ListenerUtil.mutListener.listen(64805)) {
                        messageListener.onMessage(buffer);
                    }
                }
            });
        }
        // Because libwebrtc, that's why!
        this.chunkLength = (int) Math.min(maxMessageSize, MAX_CHUNK_SIZE);
        if (!ListenerUtil.mutListener.listen(64807)) {
            // Initialise queue
            this.queue = this.fcdc.ready();
        }
    }

    /**
     *  Send a message asynchronously via this channel's write queue. The
     *  message will be fragmented into chunks.
     */
    @NonNull
    public CompletableFuture<Void> sendAsync(@NonNull final ByteBuffer buffer) {
        return this.enqueue(new Runnable() {

            @Override
            @AnyThread
            public void run() {
                if (!ListenerUtil.mutListener.listen(64808)) {
                    DataChannelContext.this.sendSync(buffer);
                }
            }
        });
    }

    /**
     *  Send a message synchronously from the same thread. Will throw if the message would block
     *  the thread.
     */
    public void sendSyncUnsafe(@NonNull final ByteBuffer buffer) throws WouldBlockException {
        if (!ListenerUtil.mutListener.listen(64810)) {
            if (!this.fcdc.ready().isDone()) {
                throw new WouldBlockException();
            } else {
                if (!ListenerUtil.mutListener.listen(64809)) {
                    this.sendSync(buffer);
                }
            }
        }
    }

    /**
     *  Send a message synchronously, fragmented into chunks.
     *
     *  Important: This may only be called from the future queue or synchronously from the worker
     *             thread.
     */
    @AnyThread
    private synchronized void sendSync(@NonNull ByteBuffer buffer) {
        try {
            if (!ListenerUtil.mutListener.listen(64815)) {
                logger.debug("Outgoing message of length {}", buffer.remaining());
            }
            // Encrypt message
            final Box box = Objects.requireNonNull(this.crypto).encrypt(bufferToBytes(buffer));
            if (!ListenerUtil.mutListener.listen(64816)) {
                buffer = ByteBuffer.wrap(box.toBytes());
            }
            // Write chunks
            final Chunker chunker = new Chunker(this.messageId++, buffer, this.chunkLength);
            if (!ListenerUtil.mutListener.listen(64822)) {
                {
                    long _loopCounter786 = 0;
                    while (chunker.hasNext()) {
                        ListenerUtil.loopListener.listen("_loopCounter786", ++_loopCounter786);
                        // Note: This will block!
                        try {
                            if (!ListenerUtil.mutListener.listen(64818)) {
                                this.fcdc.ready().get();
                            }
                        } catch (InterruptedException | ExecutionException e) {
                            if (!ListenerUtil.mutListener.listen(64817)) {
                                logger.error("Error while waiting for fcdc.ready()", e);
                            }
                            return;
                        }
                        if (!ListenerUtil.mutListener.listen(64819)) {
                            buffer = chunker.next();
                        }
                        // Write chunk
                        final DataChannel.Buffer chunk = new DataChannel.Buffer(buffer, true);
                        if (!ListenerUtil.mutListener.listen(64820)) {
                            logger.debug("Outgoing chunk of length {}", chunk.data.remaining());
                        }
                        if (!ListenerUtil.mutListener.listen(64821)) {
                            this.fcdc.write(chunk);
                        }
                    }
                }
            }
        } catch (OverflowException error) {
            if (!ListenerUtil.mutListener.listen(64811)) {
                logger.error("CSN overflow", error);
            }
            if (!ListenerUtil.mutListener.listen(64812)) {
                this.close();
            }
        } catch (CryptoException error) {
            if (!ListenerUtil.mutListener.listen(64813)) {
                logger.error("Unable to encrypt", error);
            }
            if (!ListenerUtil.mutListener.listen(64814)) {
                this.close();
            }
        }
    }

    /**
     *  Enqueue an operation to be run in order on this channel's write queue.
     */
    private CompletableFuture<Void> enqueue(@NonNull final Runnable operation) {
        if (!ListenerUtil.mutListener.listen(64823)) {
            this.queue = this.queue.thenRunAsync(operation);
        }
        if (!ListenerUtil.mutListener.listen(64828)) {
            this.queue.exceptionally(new Function<Throwable, Void>() {

                @Override
                @AnyThread
                public Void apply(@NonNull final Throwable error) {
                    if (!ListenerUtil.mutListener.listen(64826)) {
                        // abruptly.
                        if ((ListenerUtil.mutListener.listen(64824) ? (error.getCause() instanceof IllegalStateException && error.getCause() instanceof IllegalStateError) : (error.getCause() instanceof IllegalStateException || error.getCause() instanceof IllegalStateError))) {
                            if (!ListenerUtil.mutListener.listen(64825)) {
                                logger.info("Write queue aborted: {}", error.getMessage());
                            }
                            return null;
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(64827)) {
                        // Complain!
                        logger.error("Exception in write queue", error);
                    }
                    return null;
                }
            });
        }
        return this.queue;
    }

    /**
     *  Hand in a chunk for reassembly.
     *
     *  @param buffer The chunk to be added to the reassembly buffer.
     */
    public void receive(@NonNull ByteBuffer buffer) {
        if (!ListenerUtil.mutListener.listen(64829)) {
            logger.debug("Incoming chunk of length {}", buffer.remaining());
        }
        if (!ListenerUtil.mutListener.listen(64831)) {
            // Ensure we can reassemble
            if (this.unchunker == null) {
                if (!ListenerUtil.mutListener.listen(64830)) {
                    logger.warn("Unchunker has been removed");
                }
                return;
            }
        }
        // Reassemble
        try {
            if (!ListenerUtil.mutListener.listen(64834)) {
                this.unchunker.add(buffer);
            }
        } catch (OutOfMemoryError error) {
            if (!ListenerUtil.mutListener.listen(64832)) {
                // Delete unchunker
                logger.warn("Removing unchunker due to out of memory error");
            }
            if (!ListenerUtil.mutListener.listen(64833)) {
                this.unchunker = null;
            }
            // Rethrow
            throw error;
        }
    }

    /**
     *  Convert a ByteBuffer to a byte array.
     */
    @NonNull
    private static byte[] bufferToBytes(@NonNull final ByteBuffer buffer) {
        // TODO: Fix the crypto API to use ByteBuffer - this is terrible.
        byte[] bytes = buffer.array();
        if (!ListenerUtil.mutListener.listen(64847)) {
            if ((ListenerUtil.mutListener.listen(64845) ? ((ListenerUtil.mutListener.listen(64839) ? (buffer.position() >= 0) : (ListenerUtil.mutListener.listen(64838) ? (buffer.position() <= 0) : (ListenerUtil.mutListener.listen(64837) ? (buffer.position() > 0) : (ListenerUtil.mutListener.listen(64836) ? (buffer.position() < 0) : (ListenerUtil.mutListener.listen(64835) ? (buffer.position() == 0) : (buffer.position() != 0)))))) && (ListenerUtil.mutListener.listen(64844) ? (buffer.remaining() >= bytes.length) : (ListenerUtil.mutListener.listen(64843) ? (buffer.remaining() <= bytes.length) : (ListenerUtil.mutListener.listen(64842) ? (buffer.remaining() > bytes.length) : (ListenerUtil.mutListener.listen(64841) ? (buffer.remaining() < bytes.length) : (ListenerUtil.mutListener.listen(64840) ? (buffer.remaining() == bytes.length) : (buffer.remaining() != bytes.length))))))) : ((ListenerUtil.mutListener.listen(64839) ? (buffer.position() >= 0) : (ListenerUtil.mutListener.listen(64838) ? (buffer.position() <= 0) : (ListenerUtil.mutListener.listen(64837) ? (buffer.position() > 0) : (ListenerUtil.mutListener.listen(64836) ? (buffer.position() < 0) : (ListenerUtil.mutListener.listen(64835) ? (buffer.position() == 0) : (buffer.position() != 0)))))) || (ListenerUtil.mutListener.listen(64844) ? (buffer.remaining() >= bytes.length) : (ListenerUtil.mutListener.listen(64843) ? (buffer.remaining() <= bytes.length) : (ListenerUtil.mutListener.listen(64842) ? (buffer.remaining() > bytes.length) : (ListenerUtil.mutListener.listen(64841) ? (buffer.remaining() < bytes.length) : (ListenerUtil.mutListener.listen(64840) ? (buffer.remaining() == bytes.length) : (buffer.remaining() != bytes.length))))))))) {
                if (!ListenerUtil.mutListener.listen(64846)) {
                    bytes = Arrays.copyOf(buffer.array(), buffer.remaining());
                }
            }
        }
        return bytes;
    }

    /**
     *  Close the underlying data channel.
     */
    @AnyThread
    public void close() {
        if (!ListenerUtil.mutListener.listen(64848)) {
            this.dc.close();
        }
    }
}
