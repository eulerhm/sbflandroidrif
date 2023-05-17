/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
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
import androidx.annotation.Nullable;
import androidx.annotation.StringDef;
import androidx.annotation.WorkerThread;
import ch.threema.app.dialogs.ContactEditDialog;
import ch.threema.app.services.ContactService;
import ch.threema.app.services.UserService;
import ch.threema.app.utils.BitmapUtil;
import ch.threema.app.webclient.Protocol;
import ch.threema.app.webclient.services.instance.MessageDispatcher;
import ch.threema.app.webclient.services.instance.MessageReceiver;
import ch.threema.storage.models.ContactModel;
import static java.nio.charset.StandardCharsets.UTF_8;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Process update/profile requests from the browser.
 */
@WorkerThread
public class ModifyProfileHandler extends MessageReceiver {

    private static final Logger logger = LoggerFactory.getLogger(ModifyProfileHandler.class);

    private static final String FIELD_NICKNAME = "publicNickname";

    private static final String FIELD_AVATAR = "avatar";

    // Dispatchers
    private final MessageDispatcher responseDispatcher;

    // Services
    private final ContactService contactService;

    private final UserService userService;

    // Error codes
    @Retention(RetentionPolicy.SOURCE)
    @StringDef({ Protocol.ERROR_INVALID_AVATAR, Protocol.ERROR_VALUE_TOO_LONG, Protocol.ERROR_INTERNAL })
    private @interface ErrorCode {
    }

    private static class ModifyProfileException extends Exception {

        @ErrorCode
        String errorCode;

        ModifyProfileException(@ErrorCode String errorCode) {
            super();
            if (!ListenerUtil.mutListener.listen(63539)) {
                this.errorCode = errorCode;
            }
        }
    }

    @AnyThread
    public ModifyProfileHandler(MessageDispatcher responseDispatcher, ContactService contactService, UserService userService) {
        super(Protocol.SUB_TYPE_PROFILE);
        this.responseDispatcher = responseDispatcher;
        this.contactService = contactService;
        this.userService = userService;
    }

    @Override
    protected void receive(Map<String, Value> message) throws MessagePackException {
        if (!ListenerUtil.mutListener.listen(63540)) {
            logger.debug("Received update profile message");
        }
        // Get data and args
        final Map<String, Value> data = this.getData(message, false);
        final Map<String, Value> args = this.getArguments(message, false);
        if (!ListenerUtil.mutListener.listen(63542)) {
            // Process args
            if (!args.containsKey(Protocol.ARGUMENT_TEMPORARY_ID)) {
                if (!ListenerUtil.mutListener.listen(63541)) {
                    logger.error("Invalid profile update request, temporaryId not set");
                }
                return;
            }
        }
        final String temporaryId = args.get(Protocol.ARGUMENT_TEMPORARY_ID).asStringValue().toString();
        try {
            if (!ListenerUtil.mutListener.listen(63546)) {
                if (data.containsKey(FIELD_NICKNAME)) {
                    final String nickname = data.get(FIELD_NICKNAME).asStringValue().toString();
                    if (!ListenerUtil.mutListener.listen(63545)) {
                        this.processNickname(nickname);
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(63550)) {
                if (data.containsKey(FIELD_AVATAR)) {
                    final Value value = data.get(FIELD_AVATAR);
                    if (!ListenerUtil.mutListener.listen(63549)) {
                        if (value.isNilValue()) {
                            if (!ListenerUtil.mutListener.listen(63548)) {
                                this.processAvatar(null);
                            }
                        } else {
                            final byte[] avatar = value.asBinaryValue().asByteArray();
                            if (!ListenerUtil.mutListener.listen(63547)) {
                                this.processAvatar(avatar);
                            }
                        }
                    }
                }
            }
        } catch (ModifyProfileException e) {
            if (!ListenerUtil.mutListener.listen(63543)) {
                logger.error("Profile was not updated (" + e.errorCode + ")", e);
            }
            if (!ListenerUtil.mutListener.listen(63544)) {
                this.sendConfirmActionFailure(this.responseDispatcher, temporaryId, e.errorCode);
            }
        }
        if (!ListenerUtil.mutListener.listen(63551)) {
            logger.debug("Profile was updated");
        }
        if (!ListenerUtil.mutListener.listen(63552)) {
            this.sendConfirmActionSuccess(this.responseDispatcher, temporaryId);
        }
    }

    /**
     *  Update the nickname.
     */
    private void processNickname(String nickname) throws ModifyProfileException {
        if (!ListenerUtil.mutListener.listen(63558)) {
            if ((ListenerUtil.mutListener.listen(63557) ? (nickname.getBytes(UTF_8).length >= Protocol.LIMIT_BYTES_PUBLIC_NICKNAME) : (ListenerUtil.mutListener.listen(63556) ? (nickname.getBytes(UTF_8).length <= Protocol.LIMIT_BYTES_PUBLIC_NICKNAME) : (ListenerUtil.mutListener.listen(63555) ? (nickname.getBytes(UTF_8).length < Protocol.LIMIT_BYTES_PUBLIC_NICKNAME) : (ListenerUtil.mutListener.listen(63554) ? (nickname.getBytes(UTF_8).length != Protocol.LIMIT_BYTES_PUBLIC_NICKNAME) : (ListenerUtil.mutListener.listen(63553) ? (nickname.getBytes(UTF_8).length == Protocol.LIMIT_BYTES_PUBLIC_NICKNAME) : (nickname.getBytes(UTF_8).length > Protocol.LIMIT_BYTES_PUBLIC_NICKNAME))))))) {
                throw new ModifyProfileException(Protocol.ERROR_VALUE_TOO_LONG);
            }
        }
        if (!ListenerUtil.mutListener.listen(63559)) {
            this.userService.setPublicNickname(nickname);
        }
    }

    /**
     *  Update the avatar.
     */
    private void processAvatar(@Nullable byte[] avatarBytes) throws ModifyProfileException {
        final ContactModel me = this.contactService.getMe();
        if (!ListenerUtil.mutListener.listen(63561)) {
            // If avatar bytes are null, delete own avatar.
            if (avatarBytes == null) {
                if (!ListenerUtil.mutListener.listen(63560)) {
                    this.contactService.removeAvatar(me);
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(63568)) {
            // Validate bytes
            if ((ListenerUtil.mutListener.listen(63566) ? (avatarBytes.length >= 0) : (ListenerUtil.mutListener.listen(63565) ? (avatarBytes.length <= 0) : (ListenerUtil.mutListener.listen(63564) ? (avatarBytes.length > 0) : (ListenerUtil.mutListener.listen(63563) ? (avatarBytes.length < 0) : (ListenerUtil.mutListener.listen(63562) ? (avatarBytes.length != 0) : (avatarBytes.length == 0))))))) {
                if (!ListenerUtil.mutListener.listen(63567)) {
                    logger.warn("Avatar bytes are empty");
                }
                throw new ModifyProfileException(Protocol.ERROR_INVALID_AVATAR);
            }
        }
        // Decode avatar
        final Bitmap avatar = BitmapFactory.decodeByteArray(avatarBytes, 0, avatarBytes.length);
        // Resize to max allowed size
        final Bitmap resized = BitmapUtil.resizeBitmap(avatar, ContactEditDialog.CONTACT_AVATAR_WIDTH_PX, ContactEditDialog.CONTACT_AVATAR_HEIGHT_PX);
        // Set the avatar
        try {
            final byte[] converted = BitmapUtil.bitmapToByteArray(resized, Bitmap.CompressFormat.PNG, 100);
            if (!ListenerUtil.mutListener.listen(63570)) {
                this.contactService.setAvatar(this.contactService.getMe(), converted);
            }
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(63569)) {
                logger.error("Could not update own avatar", e);
            }
            throw new ModifyProfileException(Protocol.ERROR_INTERNAL);
        }
    }

    @Override
    protected boolean maybeNeedsConnection() {
        // We don't need to send any messages as a result of modifying the profile.
        return false;
    }
}
