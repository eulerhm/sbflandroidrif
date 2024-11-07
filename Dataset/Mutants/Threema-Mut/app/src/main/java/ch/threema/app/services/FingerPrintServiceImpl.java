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
package ch.threema.app.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.security.MessageDigest;
import java.util.HashMap;
import ch.threema.app.stores.IdentityStore;
import ch.threema.app.utils.LogUtil;
import ch.threema.client.Utils;
import ch.threema.storage.models.ContactModel;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class FingerPrintServiceImpl implements FingerPrintService {

    private static final Logger logger = LoggerFactory.getLogger(FingerPrintServiceImpl.class);

    private final ContactService contactService;

    private final IdentityStore identityStore;

    private HashMap<String, String> fingerPrintCache = new HashMap<>();

    public FingerPrintServiceImpl(ContactService contactService, IdentityStore identityStore) {
        this.contactService = contactService;
        this.identityStore = identityStore;
    }

    public String getFingerPrint(String identity) {
        return this.getFingerPrint(identity, false);
    }

    public String getFingerPrint(String identity, boolean reload) {
        if (!ListenerUtil.mutListener.listen(38133)) {
            if ((ListenerUtil.mutListener.listen(38122) ? (!fingerPrintCache.containsKey(identity) && reload) : (!fingerPrintCache.containsKey(identity) || reload))) {
                byte[] key = null;
                String storeIdentity = this.identityStore.getIdentity();
                if (!ListenerUtil.mutListener.listen(38127)) {
                    if ((ListenerUtil.mutListener.listen(38123) ? ((storeIdentity != null) || storeIdentity.equals(identity)) : ((storeIdentity != null) && storeIdentity.equals(identity)))) {
                        if (!ListenerUtil.mutListener.listen(38126)) {
                            // fingerprint of my identity
                            key = this.identityStore.getPublicKey();
                        }
                    } else {
                        ContactModel contact = this.contactService.getByIdentity(identity);
                        if (!ListenerUtil.mutListener.listen(38125)) {
                            if (contact != null) {
                                if (!ListenerUtil.mutListener.listen(38124)) {
                                    key = contact.getPublicKey();
                                }
                            }
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(38132)) {
                    if (key != null) {
                        String fingerPrint = "undefined/failed";
                        try {
                            MessageDigest md = MessageDigest.getInstance("SHA-256");
                            if (!ListenerUtil.mutListener.listen(38129)) {
                                md.update(key);
                            }
                            byte[] byteData = md.digest();
                            if (!ListenerUtil.mutListener.listen(38130)) {
                                fingerPrint = Utils.byteArrayToHexString(byteData).toLowerCase().substring(0, 32);
                            }
                        } catch (Exception e) {
                            if (!ListenerUtil.mutListener.listen(38128)) {
                                logger.error("Exception", e);
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(38131)) {
                            this.fingerPrintCache.put(identity, fingerPrint);
                        }
                        return fingerPrint;
                    }
                }
            }
        }
        return fingerPrintCache.get(identity);
    }
}
