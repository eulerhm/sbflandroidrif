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
package ch.threema.app.webclient.services.instance.message.receiver;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import org.msgpack.core.MessagePackException;
import org.msgpack.value.Value;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Map;
import androidx.annotation.AnyThread;
import androidx.annotation.StringDef;
import androidx.annotation.WorkerThread;
import ch.threema.app.dialogs.ContactEditDialog;
import ch.threema.app.services.ContactService;
import ch.threema.app.utils.BitmapUtil;
import ch.threema.app.utils.ContactUtil;
import ch.threema.app.webclient.Protocol;
import ch.threema.app.webclient.converter.Contact;
import ch.threema.app.webclient.converter.MsgpackObjectBuilder;
import ch.threema.app.webclient.exceptions.ConversionException;
import ch.threema.app.webclient.services.instance.MessageDispatcher;
import ch.threema.app.webclient.services.instance.MessageReceiver;
import ch.threema.storage.models.ContactModel;
import static java.nio.charset.StandardCharsets.UTF_8;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Process update/contact requests from the browser.
 */
@WorkerThread
public class ModifyContactHandler extends MessageReceiver {

    private static final Logger logger = LoggerFactory.getLogger(ModifyContactHandler.class);

    private static final String FIELD_FIRST_NAME = "firstName";

    private static final String FIELD_LAST_NAME = "lastName";

    @Retention(RetentionPolicy.SOURCE)
    @StringDef({ Protocol.ERROR_INVALID_CONTACT, Protocol.ERROR_NOT_ALLOWED_LINKED, Protocol.ERROR_NOT_ALLOWED_BUSINESS, Protocol.ERROR_VALUE_TOO_LONG, Protocol.ERROR_INTERNAL })
    private @interface ErrorCode {
    }

    private final MessageDispatcher dispatcher;

    private final ContactService contactService;

    @AnyThread
    public ModifyContactHandler(MessageDispatcher dispatcher, ContactService contactService) {
        super(Protocol.SUB_TYPE_CONTACT);
        this.dispatcher = dispatcher;
        this.contactService = contactService;
    }

    @Override
    protected void receive(Map<String, Value> message) throws MessagePackException {
        if (!ListenerUtil.mutListener.listen(63388)) {
            logger.debug("Received update contact message");
        }
        // Process args
        final Map<String, Value> args = this.getArguments(message, false);
        if (!ListenerUtil.mutListener.listen(63391)) {
            if ((ListenerUtil.mutListener.listen(63389) ? (!args.containsKey(Protocol.ARGUMENT_TEMPORARY_ID) && !args.containsKey(Protocol.ARGUMENT_IDENTITY)) : (!args.containsKey(Protocol.ARGUMENT_TEMPORARY_ID) || !args.containsKey(Protocol.ARGUMENT_IDENTITY)))) {
                if (!ListenerUtil.mutListener.listen(63390)) {
                    logger.error("Invalid contact update request, identity or temporaryId not set");
                }
                return;
            }
        }
        final String identity = args.get(Protocol.ARGUMENT_IDENTITY).asStringValue().toString();
        final String temporaryId = args.get(Protocol.ARGUMENT_TEMPORARY_ID).asStringValue().toString();
        // Validate identity
        final ContactModel contactModel = this.contactService.getByIdentity(identity);
        if (!ListenerUtil.mutListener.listen(63393)) {
            if (contactModel == null) {
                if (!ListenerUtil.mutListener.listen(63392)) {
                    this.failed(identity, temporaryId, Protocol.ERROR_INVALID_CONTACT);
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(63395)) {
            if (ContactUtil.isLinked(contactModel)) {
                if (!ListenerUtil.mutListener.listen(63394)) {
                    this.failed(identity, temporaryId, Protocol.ERROR_NOT_ALLOWED_LINKED);
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(63396)) {
            contactModel.setIsHidden(false);
        }
        // Process data
        final Map<String, Value> data = this.getData(message, false);
        if (!ListenerUtil.mutListener.listen(63405)) {
            if (data.containsKey(FIELD_FIRST_NAME)) {
                final String firstName = this.getValueString(data.get(FIELD_FIRST_NAME));
                if (!ListenerUtil.mutListener.listen(63403)) {
                    if ((ListenerUtil.mutListener.listen(63401) ? (firstName.getBytes(UTF_8).length >= Protocol.LIMIT_BYTES_FIRST_NAME) : (ListenerUtil.mutListener.listen(63400) ? (firstName.getBytes(UTF_8).length <= Protocol.LIMIT_BYTES_FIRST_NAME) : (ListenerUtil.mutListener.listen(63399) ? (firstName.getBytes(UTF_8).length < Protocol.LIMIT_BYTES_FIRST_NAME) : (ListenerUtil.mutListener.listen(63398) ? (firstName.getBytes(UTF_8).length != Protocol.LIMIT_BYTES_FIRST_NAME) : (ListenerUtil.mutListener.listen(63397) ? (firstName.getBytes(UTF_8).length == Protocol.LIMIT_BYTES_FIRST_NAME) : (firstName.getBytes(UTF_8).length > Protocol.LIMIT_BYTES_FIRST_NAME))))))) {
                        if (!ListenerUtil.mutListener.listen(63402)) {
                            this.failed(identity, temporaryId, Protocol.ERROR_VALUE_TOO_LONG);
                        }
                        return;
                    }
                }
                if (!ListenerUtil.mutListener.listen(63404)) {
                    contactModel.setFirstName(firstName);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(63414)) {
            if (data.containsKey(FIELD_LAST_NAME)) {
                final String lastName = this.getValueString(data.get(FIELD_LAST_NAME));
                if (!ListenerUtil.mutListener.listen(63412)) {
                    if ((ListenerUtil.mutListener.listen(63410) ? (lastName.getBytes(UTF_8).length >= Protocol.LIMIT_BYTES_LAST_NAME) : (ListenerUtil.mutListener.listen(63409) ? (lastName.getBytes(UTF_8).length <= Protocol.LIMIT_BYTES_LAST_NAME) : (ListenerUtil.mutListener.listen(63408) ? (lastName.getBytes(UTF_8).length < Protocol.LIMIT_BYTES_LAST_NAME) : (ListenerUtil.mutListener.listen(63407) ? (lastName.getBytes(UTF_8).length != Protocol.LIMIT_BYTES_LAST_NAME) : (ListenerUtil.mutListener.listen(63406) ? (lastName.getBytes(UTF_8).length == Protocol.LIMIT_BYTES_LAST_NAME) : (lastName.getBytes(UTF_8).length > Protocol.LIMIT_BYTES_LAST_NAME))))))) {
                        if (!ListenerUtil.mutListener.listen(63411)) {
                            this.failed(identity, temporaryId, Protocol.ERROR_VALUE_TOO_LONG);
                        }
                        return;
                    }
                }
                if (!ListenerUtil.mutListener.listen(63413)) {
                    contactModel.setLastName(lastName);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(63430)) {
            // Update avatar
            if (data.containsKey(Protocol.ARGUMENT_AVATAR)) {
                if (!ListenerUtil.mutListener.listen(63429)) {
                    if (ContactUtil.isChannelContact(contactModel)) {
                        if (!ListenerUtil.mutListener.listen(63428)) {
                            this.failed(identity, temporaryId, Protocol.ERROR_NOT_ALLOWED_BUSINESS);
                        }
                        return;
                    } else {
                        try {
                            final Value avatarValue = data.get(Protocol.ARGUMENT_AVATAR);
                            if (!ListenerUtil.mutListener.listen(63427)) {
                                if ((ListenerUtil.mutListener.listen(63417) ? (avatarValue == null && avatarValue.isNilValue()) : (avatarValue == null || avatarValue.isNilValue()))) {
                                    if (!ListenerUtil.mutListener.listen(63426)) {
                                        // Clear avatar
                                        this.contactService.removeAvatar(contactModel);
                                    }
                                } else {
                                    // Set avatar
                                    final byte[] bmp = avatarValue.asBinaryValue().asByteArray();
                                    if (!ListenerUtil.mutListener.listen(63425)) {
                                        if ((ListenerUtil.mutListener.listen(63422) ? (bmp.length >= 0) : (ListenerUtil.mutListener.listen(63421) ? (bmp.length <= 0) : (ListenerUtil.mutListener.listen(63420) ? (bmp.length < 0) : (ListenerUtil.mutListener.listen(63419) ? (bmp.length != 0) : (ListenerUtil.mutListener.listen(63418) ? (bmp.length == 0) : (bmp.length > 0))))))) {
                                            Bitmap avatar = BitmapFactory.decodeByteArray(bmp, 0, bmp.length);
                                            if (!ListenerUtil.mutListener.listen(63423)) {
                                                // Resize to max allowed size
                                                avatar = BitmapUtil.resizeBitmap(avatar, ContactEditDialog.CONTACT_AVATAR_WIDTH_PX, ContactEditDialog.CONTACT_AVATAR_HEIGHT_PX);
                                            }
                                            if (!ListenerUtil.mutListener.listen(63424)) {
                                                this.contactService.setAvatar(contactModel, // Without quality loss
                                                BitmapUtil.bitmapToByteArray(avatar, Bitmap.CompressFormat.PNG, 100));
                                            }
                                        }
                                    }
                                }
                            }
                        } catch (Exception e) {
                            if (!ListenerUtil.mutListener.listen(63415)) {
                                logger.error("Failed to save avatar", e);
                            }
                            if (!ListenerUtil.mutListener.listen(63416)) {
                                this.failed(identity, temporaryId, Protocol.ERROR_INTERNAL);
                            }
                            return;
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(63431)) {
            // Save the contact model
            this.contactService.save(contactModel);
        }
        if (!ListenerUtil.mutListener.listen(63432)) {
            // Return updated contact
            this.success(identity, temporaryId, contactModel);
        }
    }

    /**
     *  Respond with the modified contact model.
     */
    private void success(String threemaId, String temporaryId, ContactModel contact) {
        if (!ListenerUtil.mutListener.listen(63433)) {
            logger.debug("Respond modify contact success");
        }
        try {
            if (!ListenerUtil.mutListener.listen(63435)) {
                this.send(this.dispatcher, new MsgpackObjectBuilder().put(Protocol.SUB_TYPE_RECEIVER, Contact.convert(contact)), new MsgpackObjectBuilder().put(Protocol.ARGUMENT_SUCCESS, true).put(Protocol.ARGUMENT_IDENTITY, threemaId).put(Protocol.ARGUMENT_TEMPORARY_ID, temporaryId));
            }
        } catch (ConversionException e) {
            if (!ListenerUtil.mutListener.listen(63434)) {
                logger.error("Exception", e);
            }
        }
    }

    /**
     *  Respond with an error code.
     */
    private void failed(String threemaId, String temporaryId, @ErrorCode String errorCode) {
        if (!ListenerUtil.mutListener.listen(63436)) {
            logger.warn("Respond modify contact failed ({})", errorCode);
        }
        if (!ListenerUtil.mutListener.listen(63437)) {
            this.send(this.dispatcher, new MsgpackObjectBuilder().putNull(Protocol.SUB_TYPE_RECEIVER), new MsgpackObjectBuilder().put(Protocol.ARGUMENT_SUCCESS, false).put(Protocol.ARGUMENT_ERROR, errorCode).put(Protocol.ARGUMENT_IDENTITY, threemaId).put(Protocol.ARGUMENT_TEMPORARY_ID, temporaryId));
        }
    }

    @Override
    protected boolean maybeNeedsConnection() {
        return false;
    }
}
