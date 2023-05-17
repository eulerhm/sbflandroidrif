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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import org.msgpack.core.MessageBufferPacker;
import java.io.IOException;
import java.util.List;
import ch.threema.annotation.SameThread;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * The MsgpackObjectBuilder allows to dynamically create a Msgpack packet.
 *
 * Use the `put` method to insert new key-value-pairs. The insertion order will be preserved.
 *
 * Use the `maybePut` method to insert new key-value pairs only if the value is non-null.
 *
 * You can also use another `MsgpackBuilder` instance as value.
 */
@SameThread
public class MsgpackObjectBuilder extends MsgpackBuilder {

    private static class Instruction implements MsgpackBuilder.Instruction {

        @NonNull
        final MsgpackBuilder.InstructionType type;

        @NonNull
        final String key;

        @Nullable
        final Object value;

        Instruction(@NonNull MsgpackBuilder.InstructionType type, @NonNull String key, @Nullable Object value) {
            this.type = type;
            this.key = key;
            this.value = value;
        }

        @Nullable
        @Override
        public Object getValue() {
            return this.value;
        }

        @NonNull
        @Override
        public MsgpackBuilder.InstructionType getType() {
            return this.type;
        }
    }

    public MsgpackObjectBuilder put(@NonNull String key, @Nullable String value) {
        if (!ListenerUtil.mutListener.listen(62920)) {
            this.addInstruction(new Instruction(InstructionType.PACK_STRING, key, value));
        }
        return this;
    }

    public MsgpackObjectBuilder put(@NonNull String key, @Nullable Integer value) {
        if (!ListenerUtil.mutListener.listen(62921)) {
            this.addInstruction(new Instruction(InstructionType.PACK_INTEGER, key, value));
        }
        return this;
    }

    public MsgpackObjectBuilder put(@NonNull String key, @Nullable Long value) {
        if (!ListenerUtil.mutListener.listen(62922)) {
            this.addInstruction(new Instruction(InstructionType.PACK_LONG, key, value));
        }
        return this;
    }

    public MsgpackObjectBuilder put(@NonNull String key, @Nullable Boolean value) {
        if (!ListenerUtil.mutListener.listen(62923)) {
            this.addInstruction(new Instruction(InstructionType.PACK_BOOLEAN, key, value));
        }
        return this;
    }

    public MsgpackObjectBuilder put(@NonNull String key, @Nullable Double value) {
        if (!ListenerUtil.mutListener.listen(62924)) {
            this.addInstruction(new Instruction(InstructionType.PACK_DOUBLE, key, value));
        }
        return this;
    }

    public MsgpackObjectBuilder put(@NonNull String key, @Nullable Float value) {
        if (!ListenerUtil.mutListener.listen(62925)) {
            this.addInstruction(new Instruction(InstructionType.PACK_FLOAT, key, value));
        }
        return this;
    }

    public MsgpackObjectBuilder put(@NonNull String key, @Nullable byte[] value) {
        if (!ListenerUtil.mutListener.listen(62926)) {
            this.addInstruction(new Instruction(InstructionType.PACK_BYTES, key, value));
        }
        return this;
    }

    public MsgpackObjectBuilder put(@NonNull String key, @NonNull MsgpackBuilder value) {
        if (!ListenerUtil.mutListener.listen(62927)) {
            this.addInstruction(new Instruction(InstructionType.PACK_PAYLOAD, key, value));
        }
        return this;
    }

    public MsgpackObjectBuilder put(@NonNull String key, @NonNull List<MsgpackBuilder> values) {
        if (!ListenerUtil.mutListener.listen(62928)) {
            this.addInstruction(new Instruction(InstructionType.PACK_PAYLOAD_LIST, key, values));
        }
        return this;
    }

    public MsgpackObjectBuilder maybePut(@NonNull String key, @Nullable String value) {
        if (!ListenerUtil.mutListener.listen(62930)) {
            if (value != null) {
                if (!ListenerUtil.mutListener.listen(62929)) {
                    this.put(key, value);
                }
            }
        }
        return this;
    }

    public MsgpackObjectBuilder maybePut(@NonNull String key, @Nullable String value, boolean putIf) {
        if (!ListenerUtil.mutListener.listen(62932)) {
            if (putIf) {
                if (!ListenerUtil.mutListener.listen(62931)) {
                    this.put(key, value);
                }
            }
        }
        return this;
    }

    public MsgpackObjectBuilder maybePut(@NonNull String key, @Nullable Integer value) {
        if (!ListenerUtil.mutListener.listen(62934)) {
            if (value != null) {
                if (!ListenerUtil.mutListener.listen(62933)) {
                    this.put(key, value);
                }
            }
        }
        return this;
    }

    public MsgpackObjectBuilder maybePut(@NonNull String key, @Nullable Long value) {
        if (!ListenerUtil.mutListener.listen(62936)) {
            if (value != null) {
                if (!ListenerUtil.mutListener.listen(62935)) {
                    this.put(key, value);
                }
            }
        }
        return this;
    }

    public MsgpackObjectBuilder maybePut(@NonNull String key, @Nullable Boolean value) {
        if (!ListenerUtil.mutListener.listen(62938)) {
            if (value != null) {
                if (!ListenerUtil.mutListener.listen(62937)) {
                    this.put(key, value);
                }
            }
        }
        return this;
    }

    public MsgpackObjectBuilder maybePut(@NonNull String key, @Nullable Float value) {
        if (!ListenerUtil.mutListener.listen(62940)) {
            if (value != null) {
                if (!ListenerUtil.mutListener.listen(62939)) {
                    this.put(key, value);
                }
            }
        }
        return this;
    }

    public MsgpackObjectBuilder maybePut(@NonNull String key, @Nullable byte[] value) {
        if (!ListenerUtil.mutListener.listen(62942)) {
            if (value != null) {
                if (!ListenerUtil.mutListener.listen(62941)) {
                    this.put(key, value);
                }
            }
        }
        return this;
    }

    public MsgpackObjectBuilder maybePut(@NonNull String key, @Nullable MsgpackBuilder value) {
        if (!ListenerUtil.mutListener.listen(62944)) {
            if (value != null) {
                if (!ListenerUtil.mutListener.listen(62943)) {
                    this.put(key, value);
                }
            }
        }
        return this;
    }

    public MsgpackObjectBuilder maybePut(@NonNull String key, @Nullable List<MsgpackBuilder> values) {
        if (!ListenerUtil.mutListener.listen(62946)) {
            if (values != null) {
                if (!ListenerUtil.mutListener.listen(62945)) {
                    this.put(key, values);
                }
            }
        }
        return this;
    }

    public MsgpackObjectBuilder putNull(@NonNull String key) {
        if (!ListenerUtil.mutListener.listen(62947)) {
            this.addInstruction(new Instruction(InstructionType.PACK_PAYLOAD, /* hack */
            key, null));
        }
        return this;
    }

    @Override
    MsgpackObjectBuilder init(MessageBufferPacker packer, int instructionSize) throws IOException {
        if (!ListenerUtil.mutListener.listen(62948)) {
            packer.packMapHeader(instructionSize);
        }
        return this;
    }

    @Override
    MsgpackObjectBuilder initInstruction(MessageBufferPacker packer, MsgpackBuilder.Instruction instruction) throws IOException {
        if (!ListenerUtil.mutListener.listen(62949)) {
            packer.packString(((Instruction) instruction).key);
        }
        return this;
    }

    /**
     *  Return true if no values have been added to the builder.
     */
    public boolean isEmpty() {
        return this.instructionCount() == 0;
    }
}
