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
package ch.threema.base;

import ch.threema.client.Utils;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Base class for contacts to be used in Threema.
 */
public class Contact {

    public static final int CONTACT_NAME_MAX_LENGTH_BYTES = 256;

    private final String identity;

    private final byte[] publicKey;

    private String firstName;

    private String lastName;

    private VerificationLevel verificationLevel;

    public Contact(String identity, byte[] publicKey) {
        this.identity = identity;
        this.publicKey = publicKey;
        if (!ListenerUtil.mutListener.listen(65815)) {
            this.verificationLevel = VerificationLevel.UNVERIFIED;
        }
    }

    public String getIdentity() {
        return identity;
    }

    public byte[] getPublicKey() {
        return publicKey;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        if (!ListenerUtil.mutListener.listen(65816)) {
            this.firstName = Utils.truncateUTF8String(firstName, CONTACT_NAME_MAX_LENGTH_BYTES);
        }
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        if (!ListenerUtil.mutListener.listen(65817)) {
            this.lastName = Utils.truncateUTF8String(lastName, CONTACT_NAME_MAX_LENGTH_BYTES);
        }
    }

    public VerificationLevel getVerificationLevel() {
        return verificationLevel;
    }

    public void setVerificationLevel(VerificationLevel verificationLevel) {
        if (!ListenerUtil.mutListener.listen(65818)) {
            this.verificationLevel = verificationLevel;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(identity);
        if (!ListenerUtil.mutListener.listen(65819)) {
            sb.append(" (");
        }
        if (!ListenerUtil.mutListener.listen(65820)) {
            sb.append(Utils.byteArrayToHexString(publicKey));
        }
        if (!ListenerUtil.mutListener.listen(65821)) {
            sb.append(")");
        }
        if (!ListenerUtil.mutListener.listen(65827)) {
            if ((ListenerUtil.mutListener.listen(65822) ? (firstName != null && lastName != null) : (firstName != null || lastName != null))) {
                if (!ListenerUtil.mutListener.listen(65823)) {
                    sb.append(": ");
                }
                if (!ListenerUtil.mutListener.listen(65824)) {
                    sb.append(firstName);
                }
                if (!ListenerUtil.mutListener.listen(65825)) {
                    sb.append(" ");
                }
                if (!ListenerUtil.mutListener.listen(65826)) {
                    sb.append(lastName);
                }
            }
        }
        return sb.toString();
    }
}
