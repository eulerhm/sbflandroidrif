package org.owntracks.android.support;

import androidx.annotation.NonNull;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import javax.inject.Singleton;
import org.owntracks.android.model.messages.MessageBase;
import org.owntracks.android.model.messages.MessageEncrypted;
import java.io.IOException;
import java.io.InputStream;
import javax.inject.Inject;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

@Singleton
public class Parser {

    private static ObjectMapper defaultMapper;

    private static ObjectMapper arrayCompatMapper;

    private final EncryptionProvider encryptionProvider;

    @Inject
    public Parser(EncryptionProvider encryptionProvider) {
        this.encryptionProvider = encryptionProvider;
        if (!ListenerUtil.mutListener.listen(1299)) {
            defaultMapper = new ObjectMapper();
        }
        if (!ListenerUtil.mutListener.listen(1300)) {
            defaultMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        }
        if (!ListenerUtil.mutListener.listen(1301)) {
            defaultMapper.configure(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS, true);
        }
        if (!ListenerUtil.mutListener.listen(1302)) {
            arrayCompatMapper = new ObjectMapper();
        }
        if (!ListenerUtil.mutListener.listen(1303)) {
            arrayCompatMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
        }
        if (!ListenerUtil.mutListener.listen(1304)) {
            arrayCompatMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        }
    }

    public String toJsonPlainPretty(@NonNull MessageBase message) throws IOException {
        return defaultMapper.writerWithDefaultPrettyPrinter().writeValueAsString(message).replaceAll("\\r\\n", "\n");
    }

    public String toJsonPlain(@NonNull MessageBase message) throws IOException {
        return defaultMapper.writeValueAsString(message);
    }

    private byte[] toJsonPlainBytes(@NonNull MessageBase message) throws IOException {
        return defaultMapper.writeValueAsBytes(message);
    }

    public String toJson(@NonNull MessageBase message) throws IOException {
        return encryptString(toJsonPlain(message));
    }

    public byte[] toJsonBytes(@NonNull MessageBase message) throws IOException {
        return encryptBytes(toJsonPlainBytes(message));
    }

    public MessageBase fromJson(@NonNull String input) throws IOException, EncryptionException {
        return decrypt(defaultMapper.readValue(input, MessageBase.class));
    }

    // Accepts {plain} as byte array
    public MessageBase fromJson(@NonNull byte[] input) throws IOException, EncryptionException {
        return decrypt(defaultMapper.readValue(input, MessageBase.class));
    }

    // Accepts 1) [{plain},{plain},...], 2) {plain}, 3) {encrypted, data:[{plain}, {plain}, ...]} as input stream
    public MessageBase[] fromJson(@NonNull InputStream input) throws IOException, EncryptionException {
        return decrypt(arrayCompatMapper.readValue(input, MessageBase[].class));
    }

    private MessageBase[] decrypt(MessageBase[] a) throws IOException, EncryptionException {
        if (!ListenerUtil.mutListener.listen(1305)) {
            // Recorder compatiblity, encrypted messages with data array
            if (a == null)
                throw new IOException("null array");
        }
        if ((ListenerUtil.mutListener.listen(1306) ? (a.length == 1 || a[0] instanceof MessageEncrypted) : (a.length == 1 && a[0] instanceof MessageEncrypted))) {
            if (!ListenerUtil.mutListener.listen(1307)) {
                if (!encryptionProvider.isPayloadEncryptionEnabled())
                    throw new EncryptionException("received encrypted message but payload encryption is not enabled");
            }
            return defaultMapper.readValue(encryptionProvider.decrypt(((MessageEncrypted) a[0]).getData()), MessageBase[].class);
        } else {
            // single message wrapped in array by mapper or array of messages
            return a;
        }
    }

    private MessageBase decrypt(MessageBase m) throws IOException, EncryptionException {
        if (!ListenerUtil.mutListener.listen(1309)) {
            if (m instanceof MessageEncrypted) {
                if (!ListenerUtil.mutListener.listen(1308)) {
                    if (!encryptionProvider.isPayloadEncryptionEnabled())
                        throw new EncryptionException("received encrypted message but payload encryption is not enabled");
                }
                return defaultMapper.readValue(encryptionProvider.decrypt(((MessageEncrypted) m).getData()), MessageBase.class);
            }
        }
        return m;
    }

    private String encryptString(@NonNull String input) throws IOException {
        if (!ListenerUtil.mutListener.listen(1311)) {
            if (encryptionProvider.isPayloadEncryptionEnabled()) {
                MessageEncrypted m = new MessageEncrypted();
                if (!ListenerUtil.mutListener.listen(1310)) {
                    m.setData(encryptionProvider.encrypt(input));
                }
                return defaultMapper.writeValueAsString(m);
            }
        }
        return input;
    }

    private byte[] encryptBytes(@NonNull byte[] input) throws IOException {
        if (!ListenerUtil.mutListener.listen(1313)) {
            if (encryptionProvider.isPayloadEncryptionEnabled()) {
                MessageEncrypted m = new MessageEncrypted();
                if (!ListenerUtil.mutListener.listen(1312)) {
                    m.setData(encryptionProvider.encrypt(input));
                }
                return defaultMapper.writeValueAsBytes(m);
            }
        }
        return input;
    }

    public static class EncryptionException extends Exception {

        EncryptionException(String s) {
            super(s);
        }
    }
}
