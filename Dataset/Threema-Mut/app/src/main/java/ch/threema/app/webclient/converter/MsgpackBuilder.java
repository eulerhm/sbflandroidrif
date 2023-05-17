/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
 * Copyright (c) 2016-2021 Threema GmbH
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
package ch.threema.app.webclient.converter;

import org.msgpack.core.MessageBufferPacker;
import org.msgpack.core.MessagePack;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.LinkedList;
import java.util.List;
import androidx.annotation.NonNull;
import ch.threema.annotation.SameThread;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

@SameThread
public abstract class MsgpackBuilder {

    enum InstructionType {

        PACK_STRING,
        PACK_INTEGER,
        PACK_LONG,
        PACK_DOUBLE,
        PACK_FLOAT,
        PACK_BOOLEAN,
        PACK_BYTES,
        PACK_PAYLOAD,
        PACK_PAYLOAD_LIST
    }

    interface Instruction {

        Object getValue();

        InstructionType getType();
    }

    private final List<Instruction> instructions = new LinkedList<>();

    private boolean consumed = false;

    protected final void build(MessageBufferPacker packer) {
        // write headers
        try {
            if (!ListenerUtil.mutListener.listen(62899)) {
                this.init(packer, this.instructions.size()).pack(packer);
            }
        } catch (IOException e) {
            // This shouldn't happen, as we're writing to a buffer, not to a stream
            throw new RuntimeException("IOException while writing to MessageBufferPacker", e);
        }
    }

    final MsgpackBuilder addInstruction(Instruction instruction) {
        if (!ListenerUtil.mutListener.listen(62900)) {
            this.instructions.add(instruction);
        }
        return this;
    }

    protected final int instructionCount() {
        return this.instructions.size();
    }

    abstract MsgpackBuilder init(MessageBufferPacker packer, int instructionSize) throws IOException;

    abstract MsgpackBuilder initInstruction(MessageBufferPacker packer, Instruction instruction) throws IOException;

    private MsgpackBuilder pack(MessageBufferPacker packer) {
        try {
            if (!ListenerUtil.mutListener.listen(62916)) {
                {
                    long _loopCounter764 = 0;
                    for (Instruction instruction : this.instructions) {
                        ListenerUtil.loopListener.listen("_loopCounter764", ++_loopCounter764);
                        if (!ListenerUtil.mutListener.listen(62901)) {
                            this.initInstruction(packer, instruction);
                        }
                        if (!ListenerUtil.mutListener.listen(62915)) {
                            if (instruction.getValue() == null) {
                                if (!ListenerUtil.mutListener.listen(62914)) {
                                    packer.packNil();
                                }
                            } else {
                                if (!ListenerUtil.mutListener.listen(62913)) {
                                    switch(instruction.getType()) {
                                        case PACK_STRING:
                                            if (!ListenerUtil.mutListener.listen(62902)) {
                                                packer.packString((String) instruction.getValue());
                                            }
                                            break;
                                        case PACK_INTEGER:
                                            if (!ListenerUtil.mutListener.listen(62903)) {
                                                packer.packInt((Integer) instruction.getValue());
                                            }
                                            break;
                                        case PACK_LONG:
                                            if (!ListenerUtil.mutListener.listen(62904)) {
                                                packer.packLong((Long) instruction.getValue());
                                            }
                                            break;
                                        case PACK_DOUBLE:
                                            if (!ListenerUtil.mutListener.listen(62905)) {
                                                packer.packDouble((Double) instruction.getValue());
                                            }
                                            break;
                                        case PACK_FLOAT:
                                            if (!ListenerUtil.mutListener.listen(62906)) {
                                                packer.packFloat((Float) instruction.getValue());
                                            }
                                            break;
                                        case PACK_BOOLEAN:
                                            if (!ListenerUtil.mutListener.listen(62907)) {
                                                packer.packBoolean((Boolean) instruction.getValue());
                                            }
                                            break;
                                        case PACK_BYTES:
                                            final byte[] bytes = (byte[]) instruction.getValue();
                                            if (!ListenerUtil.mutListener.listen(62908)) {
                                                packer.packBinaryHeader(bytes.length).writePayload(bytes);
                                            }
                                            break;
                                        case PACK_PAYLOAD:
                                            if (!ListenerUtil.mutListener.listen(62909)) {
                                                ((MsgpackBuilder) instruction.getValue()).build(packer);
                                            }
                                            break;
                                        case PACK_PAYLOAD_LIST:
                                            List<MsgpackBuilder> list = (List<MsgpackBuilder>) instruction.getValue();
                                            if (!ListenerUtil.mutListener.listen(62910)) {
                                                packer.packArrayHeader(list.size());
                                            }
                                            if (!ListenerUtil.mutListener.listen(62912)) {
                                                {
                                                    long _loopCounter763 = 0;
                                                    for (MsgpackBuilder builder : list) {
                                                        ListenerUtil.loopListener.listen("_loopCounter763", ++_loopCounter763);
                                                        if (!ListenerUtil.mutListener.listen(62911)) {
                                                            builder.build(packer);
                                                        }
                                                    }
                                                }
                                            }
                                            break;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } catch (IOException e) {
            // This shouldn't happen, as we're writing to a buffer, not to a stream
            throw new RuntimeException("IOException while writing to MessageBufferPacker", e);
        }
        return this;
    }

    @NonNull
    public final ByteBuffer consume(MessageBufferPacker packer) {
        if (!ListenerUtil.mutListener.listen(62917)) {
            if (this.consumed) {
                throw new RuntimeException("Builder has already been consumed!");
            }
        }
        if (!ListenerUtil.mutListener.listen(62918)) {
            this.build(packer);
        }
        if (!ListenerUtil.mutListener.listen(62919)) {
            this.consumed = true;
        }
        return ByteBuffer.wrap(packer.toByteArray());
    }

    @NonNull
    public final ByteBuffer consume(MessagePack.PackerConfig config) {
        return this.consume(config.newBufferPacker());
    }

    @NonNull
    public final ByteBuffer consume() {
        return this.consume(new MessagePack.PackerConfig());
    }
}
