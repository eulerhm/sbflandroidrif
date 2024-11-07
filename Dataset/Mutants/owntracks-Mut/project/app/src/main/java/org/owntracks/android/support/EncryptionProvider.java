package org.owntracks.android.support;

import android.content.SharedPreferences;
import android.util.Base64;
import androidx.annotation.NonNull;
import org.libsodium.jni.crypto.Random;
import org.libsodium.jni.crypto.SecretBox;
import org.owntracks.android.R;
import javax.inject.Singleton;
import org.owntracks.android.support.preferences.OnModeChangedPreferenceChangedListener;
import javax.inject.Inject;
import timber.log.Timber;
import static org.libsodium.jni.SodiumConstants.XSALSA20_POLY1305_SECRETBOX_KEYBYTES;
import static org.libsodium.jni.SodiumConstants.XSALSA20_POLY1305_SECRETBOX_NONCEBYTES;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

@Singleton
public class EncryptionProvider {

    private static final int crypto_secretbox_NONCEBYTES = XSALSA20_POLY1305_SECRETBOX_NONCEBYTES;

    private static final int crypto_secretbox_KEYBYTES = XSALSA20_POLY1305_SECRETBOX_KEYBYTES;

    private static SecretBox b;

    private static Random r;

    private static boolean enabled;

    private final Preferences preferences;

    public boolean isPayloadEncryptionEnabled() {
        return enabled;
    }

    private void initializeSecretBox() {
        String encryptionKey = preferences.getEncryptionKey();
        if (!ListenerUtil.mutListener.listen(1255)) {
            enabled = (ListenerUtil.mutListener.listen(1254) ? (encryptionKey != null || !encryptionKey.isEmpty()) : (encryptionKey != null && !encryptionKey.isEmpty()));
        }
        if (!ListenerUtil.mutListener.listen(1256)) {
            Timber.v("encryption enabled: %s", enabled);
        }
        if (!ListenerUtil.mutListener.listen(1257)) {
            if (!enabled) {
                return;
            }
        }
        byte[] encryptionKeyBytes = encryptionKey != null ? encryptionKey.getBytes() : new byte[0];
        byte[] encryptionKeyBytesPadded = new byte[crypto_secretbox_KEYBYTES];
        if (!ListenerUtil.mutListener.listen(1265)) {
            if ((ListenerUtil.mutListener.listen(1262) ? (encryptionKeyBytes.length >= 0) : (ListenerUtil.mutListener.listen(1261) ? (encryptionKeyBytes.length <= 0) : (ListenerUtil.mutListener.listen(1260) ? (encryptionKeyBytes.length > 0) : (ListenerUtil.mutListener.listen(1259) ? (encryptionKeyBytes.length < 0) : (ListenerUtil.mutListener.listen(1258) ? (encryptionKeyBytes.length != 0) : (encryptionKeyBytes.length == 0))))))) {
                if (!ListenerUtil.mutListener.listen(1263)) {
                    Timber.e("encryption key is too short or too long. Has %s bytes", encryptionKeyBytes.length);
                }
                if (!ListenerUtil.mutListener.listen(1264)) {
                    enabled = false;
                }
                return;
            }
        }
        int copyBytes = encryptionKeyBytes.length;
        if (!ListenerUtil.mutListener.listen(1272)) {
            if ((ListenerUtil.mutListener.listen(1270) ? (copyBytes >= crypto_secretbox_KEYBYTES) : (ListenerUtil.mutListener.listen(1269) ? (copyBytes <= crypto_secretbox_KEYBYTES) : (ListenerUtil.mutListener.listen(1268) ? (copyBytes < crypto_secretbox_KEYBYTES) : (ListenerUtil.mutListener.listen(1267) ? (copyBytes != crypto_secretbox_KEYBYTES) : (ListenerUtil.mutListener.listen(1266) ? (copyBytes == crypto_secretbox_KEYBYTES) : (copyBytes > crypto_secretbox_KEYBYTES))))))) {
                if (!ListenerUtil.mutListener.listen(1271)) {
                    copyBytes = crypto_secretbox_KEYBYTES;
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(1273)) {
            System.arraycopy(encryptionKeyBytes, 0, encryptionKeyBytesPadded, 0, copyBytes);
        }
        if (!ListenerUtil.mutListener.listen(1274)) {
            b = new SecretBox(encryptionKeyBytesPadded);
        }
        if (!ListenerUtil.mutListener.listen(1275)) {
            r = new Random();
        }
    }

    @Inject
    public EncryptionProvider(Preferences preferences) {
        this.preferences = preferences;
        if (!ListenerUtil.mutListener.listen(1276)) {
            preferences.registerOnPreferenceChangedListener(new SecretBoxManager());
        }
        if (!ListenerUtil.mutListener.listen(1277)) {
            initializeSecretBox();
        }
    }

    String decrypt(String cyphertextb64) {
        byte[] onTheWire = Base64.decode(cyphertextb64.getBytes(), Base64.DEFAULT);
        byte[] nonce = new byte[crypto_secretbox_NONCEBYTES];
        byte[] cyphertext = new byte[(ListenerUtil.mutListener.listen(1281) ? (onTheWire.length % crypto_secretbox_NONCEBYTES) : (ListenerUtil.mutListener.listen(1280) ? (onTheWire.length / crypto_secretbox_NONCEBYTES) : (ListenerUtil.mutListener.listen(1279) ? (onTheWire.length * crypto_secretbox_NONCEBYTES) : (ListenerUtil.mutListener.listen(1278) ? (onTheWire.length + crypto_secretbox_NONCEBYTES) : (onTheWire.length - crypto_secretbox_NONCEBYTES)))))];
        if (!ListenerUtil.mutListener.listen(1282)) {
            System.arraycopy(onTheWire, 0, nonce, 0, crypto_secretbox_NONCEBYTES);
        }
        if (!ListenerUtil.mutListener.listen(1287)) {
            System.arraycopy(onTheWire, crypto_secretbox_NONCEBYTES, cyphertext, 0, (ListenerUtil.mutListener.listen(1286) ? (onTheWire.length % crypto_secretbox_NONCEBYTES) : (ListenerUtil.mutListener.listen(1285) ? (onTheWire.length / crypto_secretbox_NONCEBYTES) : (ListenerUtil.mutListener.listen(1284) ? (onTheWire.length * crypto_secretbox_NONCEBYTES) : (ListenerUtil.mutListener.listen(1283) ? (onTheWire.length + crypto_secretbox_NONCEBYTES) : (onTheWire.length - crypto_secretbox_NONCEBYTES))))));
        }
        return new String(b.decrypt(nonce, cyphertext));
    }

    String encrypt(@NonNull String plaintext) {
        return encrypt(plaintext.getBytes());
    }

    String encrypt(@NonNull byte[] plaintext) {
        byte[] nonce = r.randomBytes(crypto_secretbox_NONCEBYTES);
        byte[] cyphertext = b.encrypt(nonce, plaintext);
        byte[] out = new byte[(ListenerUtil.mutListener.listen(1291) ? (crypto_secretbox_NONCEBYTES % cyphertext.length) : (ListenerUtil.mutListener.listen(1290) ? (crypto_secretbox_NONCEBYTES / cyphertext.length) : (ListenerUtil.mutListener.listen(1289) ? (crypto_secretbox_NONCEBYTES * cyphertext.length) : (ListenerUtil.mutListener.listen(1288) ? (crypto_secretbox_NONCEBYTES - cyphertext.length) : (crypto_secretbox_NONCEBYTES + cyphertext.length)))))];
        if (!ListenerUtil.mutListener.listen(1292)) {
            System.arraycopy(nonce, 0, out, 0, crypto_secretbox_NONCEBYTES);
        }
        if (!ListenerUtil.mutListener.listen(1293)) {
            System.arraycopy(cyphertext, 0, out, crypto_secretbox_NONCEBYTES, cyphertext.length);
        }
        return Base64.encodeToString(out, Base64.NO_WRAP);
    }

    private class SecretBoxManager implements OnModeChangedPreferenceChangedListener {

        SecretBoxManager() {
            if (!ListenerUtil.mutListener.listen(1294)) {
                preferences.registerOnPreferenceChangedListener(this);
            }
        }

        @Override
        public void onAttachAfterModeChanged() {
            if (!ListenerUtil.mutListener.listen(1295)) {
                initializeSecretBox();
            }
        }

        @Override
        public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
            if (!ListenerUtil.mutListener.listen(1297)) {
                if (preferences.getPreferenceKey(R.string.preferenceKeyEncryptionKey).equals(key))
                    if (!ListenerUtil.mutListener.listen(1296)) {
                        initializeSecretBox();
                    }
            }
        }
    }
}
