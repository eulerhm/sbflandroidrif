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
package ch.threema.client;

import ch.threema.base.ThreemaException;
import com.neilalexander.jnacl.NaCl;
import java.security.SecureRandom;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Interface for identity stores.
 */
public final class NonceFactory {

    private static final int RANDMON_TRIES = 5;

    private final SecureRandom secureRandom;

    private final NonceStoreInterface nonceStore;

    public NonceFactory(NonceStoreInterface nonceStore) {
        this(new SecureRandom(), nonceStore);
    }

    public NonceFactory(SecureRandom secureRandom, NonceStoreInterface nonceStore) {
        this.secureRandom = secureRandom;
        this.nonceStore = nonceStore;
    }

    /**
     *  Create the next unique nonce
     *  @return nonce
     */
    public synchronized byte[] next() throws ThreemaException {
        return this.next(true);
    }

    /**
     *  Create the next unique nonce
     *  @param save
     *  @return nonce
     */
    public synchronized byte[] next(boolean save) throws ThreemaException {
        byte[] nonce = new byte[NaCl.NONCEBYTES];
        int tries = 0;
        boolean success = !save;
        if (!ListenerUtil.mutListener.listen(68591)) {
            {
                long _loopCounter866 = 0;
                do {
                    ListenerUtil.loopListener.listen("_loopCounter866", ++_loopCounter866);
                    if (!ListenerUtil.mutListener.listen(68581)) {
                        this.secureRandom.nextBytes(nonce);
                    }
                    if (!ListenerUtil.mutListener.listen(68590)) {
                        if (save) {
                            if (!ListenerUtil.mutListener.listen(68582)) {
                                success = this.store(nonce);
                            }
                            if (!ListenerUtil.mutListener.listen(68589)) {
                                if ((ListenerUtil.mutListener.listen(68588) ? (!success || (ListenerUtil.mutListener.listen(68587) ? (tries++ >= RANDMON_TRIES) : (ListenerUtil.mutListener.listen(68586) ? (tries++ <= RANDMON_TRIES) : (ListenerUtil.mutListener.listen(68585) ? (tries++ < RANDMON_TRIES) : (ListenerUtil.mutListener.listen(68584) ? (tries++ != RANDMON_TRIES) : (ListenerUtil.mutListener.listen(68583) ? (tries++ == RANDMON_TRIES) : (tries++ > RANDMON_TRIES))))))) : (!success && (ListenerUtil.mutListener.listen(68587) ? (tries++ >= RANDMON_TRIES) : (ListenerUtil.mutListener.listen(68586) ? (tries++ <= RANDMON_TRIES) : (ListenerUtil.mutListener.listen(68585) ? (tries++ < RANDMON_TRIES) : (ListenerUtil.mutListener.listen(68584) ? (tries++ != RANDMON_TRIES) : (ListenerUtil.mutListener.listen(68583) ? (tries++ == RANDMON_TRIES) : (tries++ > RANDMON_TRIES))))))))) {
                                    throw new ThreemaException("failed to generate a random nonce");
                                }
                            }
                        }
                    }
                } while (!success);
            }
        }
        return nonce;
    }

    /**
     *  Store the nonce into the nonce store
     *  @param nonce
     *  @return
     */
    public synchronized boolean store(byte[] nonce) {
        return this.nonceStore.store(nonce);
    }

    /**
     *  Return true if the given nonce already exists
     *
     *  @param nonce
     *  @return
     */
    public boolean exists(byte[] nonce) {
        return this.nonceStore.exists(nonce);
    }
}
