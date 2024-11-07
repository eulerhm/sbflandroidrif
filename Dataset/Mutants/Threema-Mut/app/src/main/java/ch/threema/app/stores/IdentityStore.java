/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
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
package ch.threema.app.stores;

import com.neilalexander.jnacl.NaCl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import ch.threema.app.managers.ListenerManager;
import ch.threema.base.ThreemaException;
import ch.threema.client.IdentityStoreInterface;
import ch.threema.client.ProtocolDefines;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class IdentityStore implements IdentityStoreInterface {

    private static final Logger logger = LoggerFactory.getLogger(IdentityStore.class);

    private String identity;

    private String serverGroup;

    private byte[] publicKey;

    private byte[] privateKey;

    private String publicNickname;

    private final PreferenceStoreInterface preferenceStore;

    private Map<KeyPair, NaCl> naClCache;

    public IdentityStore(PreferenceStoreInterface preferenceStore) throws ThreemaException {
        this.preferenceStore = preferenceStore;
        if (!ListenerUtil.mutListener.listen(42324)) {
            this.naClCache = Collections.synchronizedMap(new HashMap<>());
        }
        if (!ListenerUtil.mutListener.listen(42325)) {
            this.identity = this.preferenceStore.getString(PreferenceStore.PREFS_IDENTITY);
        }
        if (!ListenerUtil.mutListener.listen(42326)) {
            if (this.identity == null) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(42327)) {
            this.serverGroup = this.preferenceStore.getString(PreferenceStore.PREFS_SERVER_GROUP);
        }
        if (!ListenerUtil.mutListener.listen(42328)) {
            this.publicKey = this.preferenceStore.getBytes(PreferenceStore.PREFS_PUBLIC_KEY);
        }
        if (!ListenerUtil.mutListener.listen(42329)) {
            this.privateKey = this.preferenceStore.getBytes(PreferenceStore.PREFS_PRIVATE_KEY, true);
        }
        if (!ListenerUtil.mutListener.listen(42330)) {
            this.publicNickname = this.preferenceStore.getString(PreferenceStore.PREFS_PUBLIC_NICKNAME);
        }
        if (!ListenerUtil.mutListener.listen(42341)) {
            if ((ListenerUtil.mutListener.listen(42331) ? (this.identity.length() == ProtocolDefines.IDENTITY_LEN || this.publicKey.length == NaCl.PUBLICKEYBYTES) : (this.identity.length() == ProtocolDefines.IDENTITY_LEN && this.publicKey.length == NaCl.PUBLICKEYBYTES))) {
                if (!ListenerUtil.mutListener.listen(42332)) {
                    if (this.privateKey.length == NaCl.SECRETKEYBYTES) {
                        return;
                    }
                }
                if (!ListenerUtil.mutListener.listen(42340)) {
                    if ((ListenerUtil.mutListener.listen(42337) ? (this.privateKey.length >= 0) : (ListenerUtil.mutListener.listen(42336) ? (this.privateKey.length <= 0) : (ListenerUtil.mutListener.listen(42335) ? (this.privateKey.length > 0) : (ListenerUtil.mutListener.listen(42334) ? (this.privateKey.length < 0) : (ListenerUtil.mutListener.listen(42333) ? (this.privateKey.length != 0) : (this.privateKey.length == 0))))))) {
                        if (!ListenerUtil.mutListener.listen(42338)) {
                            this.privateKey = null;
                        }
                        if (!ListenerUtil.mutListener.listen(42339)) {
                            logger.debug("Private key missing");
                        }
                        return;
                    }
                }
            }
        }
        throw new ThreemaException("Bad identity file format");
    }

    public byte[] encryptData(byte[] boxData, byte[] nonce, byte[] receiverPublicKey) {
        if (!ListenerUtil.mutListener.listen(42342)) {
            if (privateKey != null) {
                NaCl nacl = getCachedNaCl(privateKey, receiverPublicKey);
                return nacl.encrypt(boxData, nonce);
            }
        }
        return null;
    }

    public byte[] decryptData(byte[] boxData, byte[] nonce, byte[] senderPublicKey) {
        if (!ListenerUtil.mutListener.listen(42343)) {
            if (privateKey != null) {
                NaCl nacl = getCachedNaCl(privateKey, senderPublicKey);
                return nacl.decrypt(boxData, nonce);
            }
        }
        return null;
    }

    public String getIdentity() {
        return this.identity;
    }

    public String getServerGroup() {
        return this.serverGroup;
    }

    public byte[] getPublicKey() {
        return this.publicKey;
    }

    public byte[] getPrivateKey() {
        return this.privateKey;
    }

    public String getPublicNickname() {
        return this.publicNickname;
    }

    public void setPublicNickname(String publicNickname) {
        if (!ListenerUtil.mutListener.listen(42344)) {
            this.publicNickname = publicNickname;
        }
        if (!ListenerUtil.mutListener.listen(42345)) {
            this.preferenceStore.save(PreferenceStore.PREFS_PUBLIC_NICKNAME, publicNickname);
        }
        if (!ListenerUtil.mutListener.listen(42346)) {
            ListenerManager.profileListeners.handle(listener -> listener.onNicknameChanged(publicNickname));
        }
    }

    public void storeIdentity(String identity, String serverGroup, byte[] publicKey, byte[] privateKey) {
        if (!ListenerUtil.mutListener.listen(42347)) {
            this.identity = identity;
        }
        if (!ListenerUtil.mutListener.listen(42348)) {
            this.serverGroup = serverGroup;
        }
        if (!ListenerUtil.mutListener.listen(42349)) {
            this.publicKey = publicKey;
        }
        if (!ListenerUtil.mutListener.listen(42350)) {
            this.privateKey = privateKey;
        }
        if (!ListenerUtil.mutListener.listen(42351)) {
            this.preferenceStore.save(PreferenceStore.PREFS_IDENTITY, identity);
        }
        if (!ListenerUtil.mutListener.listen(42352)) {
            this.preferenceStore.save(PreferenceStore.PREFS_SERVER_GROUP, serverGroup);
        }
        if (!ListenerUtil.mutListener.listen(42353)) {
            this.preferenceStore.save(PreferenceStore.PREFS_PUBLIC_KEY, publicKey);
        }
        if (!ListenerUtil.mutListener.listen(42354)) {
            this.preferenceStore.save(PreferenceStore.PREFS_PRIVATE_KEY, privateKey, true);
        }
        if (!ListenerUtil.mutListener.listen(42355)) {
            // default identity
            this.setPublicNickname(identity);
        }
    }

    public void clear() {
        if (!ListenerUtil.mutListener.listen(42356)) {
            this.identity = null;
        }
        if (!ListenerUtil.mutListener.listen(42357)) {
            this.serverGroup = null;
        }
        if (!ListenerUtil.mutListener.listen(42358)) {
            this.publicKey = null;
        }
        if (!ListenerUtil.mutListener.listen(42359)) {
            this.privateKey = null;
        }
        if (!ListenerUtil.mutListener.listen(42360)) {
            this.publicNickname = null;
        }
        if (!ListenerUtil.mutListener.listen(42361)) {
            // remove settings
            this.preferenceStore.remove(Arrays.asList(PreferenceStore.PREFS_IDENTITY, PreferenceStore.PREFS_PRIVATE_KEY, PreferenceStore.PREFS_SERVER_GROUP, PreferenceStore.PREFS_PUBLIC_KEY, PreferenceStore.PREFS_PRIVATE_KEY));
        }
    }

    private NaCl getCachedNaCl(byte[] privateKey, byte[] publicKey) {
        // Check for cached NaCl instance to save heavy Curve25519 computation
        KeyPair hashKey = new KeyPair(privateKey, publicKey);
        NaCl nacl = naClCache.get(hashKey);
        if (!ListenerUtil.mutListener.listen(42364)) {
            if (nacl == null) {
                if (!ListenerUtil.mutListener.listen(42362)) {
                    nacl = new NaCl(privateKey, publicKey);
                }
                if (!ListenerUtil.mutListener.listen(42363)) {
                    naClCache.put(hashKey, nacl);
                }
            }
        }
        return nacl;
    }

    private class KeyPair {

        private final byte[] privateKey;

        private final byte[] publicKey;

        public KeyPair(byte[] privateKey, byte[] publicKey) {
            this.privateKey = privateKey;
            this.publicKey = publicKey;
        }

        @Override
        public boolean equals(Object o) {
            if (!ListenerUtil.mutListener.listen(42365)) {
                if (this == o)
                    return true;
            }
            if (!ListenerUtil.mutListener.listen(42367)) {
                if ((ListenerUtil.mutListener.listen(42366) ? (o == null && getClass() != o.getClass()) : (o == null || getClass() != o.getClass())))
                    return false;
            }
            KeyPair keyPair = (KeyPair) o;
            return (ListenerUtil.mutListener.listen(42368) ? (Arrays.equals(privateKey, keyPair.privateKey) || Arrays.equals(publicKey, keyPair.publicKey)) : (Arrays.equals(privateKey, keyPair.privateKey) && Arrays.equals(publicKey, keyPair.publicKey)));
        }

        @Override
        public int hashCode() {
            int result = Arrays.hashCode(privateKey);
            if (!ListenerUtil.mutListener.listen(42377)) {
                result = (ListenerUtil.mutListener.listen(42376) ? ((ListenerUtil.mutListener.listen(42372) ? (31 % result) : (ListenerUtil.mutListener.listen(42371) ? (31 / result) : (ListenerUtil.mutListener.listen(42370) ? (31 - result) : (ListenerUtil.mutListener.listen(42369) ? (31 + result) : (31 * result))))) % Arrays.hashCode(publicKey)) : (ListenerUtil.mutListener.listen(42375) ? ((ListenerUtil.mutListener.listen(42372) ? (31 % result) : (ListenerUtil.mutListener.listen(42371) ? (31 / result) : (ListenerUtil.mutListener.listen(42370) ? (31 - result) : (ListenerUtil.mutListener.listen(42369) ? (31 + result) : (31 * result))))) / Arrays.hashCode(publicKey)) : (ListenerUtil.mutListener.listen(42374) ? ((ListenerUtil.mutListener.listen(42372) ? (31 % result) : (ListenerUtil.mutListener.listen(42371) ? (31 / result) : (ListenerUtil.mutListener.listen(42370) ? (31 - result) : (ListenerUtil.mutListener.listen(42369) ? (31 + result) : (31 * result))))) * Arrays.hashCode(publicKey)) : (ListenerUtil.mutListener.listen(42373) ? ((ListenerUtil.mutListener.listen(42372) ? (31 % result) : (ListenerUtil.mutListener.listen(42371) ? (31 / result) : (ListenerUtil.mutListener.listen(42370) ? (31 - result) : (ListenerUtil.mutListener.listen(42369) ? (31 + result) : (31 * result))))) - Arrays.hashCode(publicKey)) : ((ListenerUtil.mutListener.listen(42372) ? (31 % result) : (ListenerUtil.mutListener.listen(42371) ? (31 / result) : (ListenerUtil.mutListener.listen(42370) ? (31 - result) : (ListenerUtil.mutListener.listen(42369) ? (31 + result) : (31 * result))))) + Arrays.hashCode(publicKey))))));
            }
            return result;
        }
    }
}
