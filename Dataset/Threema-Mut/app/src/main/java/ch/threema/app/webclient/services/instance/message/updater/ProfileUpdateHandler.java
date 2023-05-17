/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
 * Copyright (c) 2018-2021 Threema GmbH
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
package ch.threema.app.webclient.services.instance.message.updater;

import android.graphics.Bitmap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import androidx.annotation.AnyThread;
import androidx.annotation.NonNull;
import androidx.annotation.WorkerThread;
import ch.threema.app.listeners.ProfileListener;
import ch.threema.app.managers.ListenerManager;
import ch.threema.app.services.ContactService;
import ch.threema.app.services.UserService;
import ch.threema.app.utils.BitmapUtil;
import ch.threema.app.utils.executor.HandlerExecutor;
import ch.threema.app.webclient.Protocol;
import ch.threema.app.webclient.converter.MsgpackObjectBuilder;
import ch.threema.app.webclient.converter.Profile;
import ch.threema.app.webclient.services.instance.MessageDispatcher;
import ch.threema.app.webclient.services.instance.MessageUpdater;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

@WorkerThread
public class ProfileUpdateHandler extends MessageUpdater {

    private static final Logger logger = LoggerFactory.getLogger(ProfileUpdateHandler.class);

    // Handler
    @NonNull
    private final HandlerExecutor handler;

    // Listeners
    private final ProfileListener listener;

    // Dispatchers
    @NonNull
    private MessageDispatcher updateDispatcher;

    // Services
    @NonNull
    private final UserService userService;

    @NonNull
    private final ContactService contactService;

    @AnyThread
    public ProfileUpdateHandler(@NonNull HandlerExecutor handler, @NonNull MessageDispatcher updateDispatcher, @NonNull UserService userService, @NonNull ContactService contactService) {
        super(Protocol.SUB_TYPE_PROFILE);
        this.handler = handler;
        if (!ListenerUtil.mutListener.listen(63764)) {
            this.updateDispatcher = updateDispatcher;
        }
        this.userService = userService;
        this.contactService = contactService;
        this.listener = new Listener();
    }

    @Override
    public void register() {
        if (!ListenerUtil.mutListener.listen(63765)) {
            logger.debug("register()");
        }
        if (!ListenerUtil.mutListener.listen(63766)) {
            ListenerManager.profileListeners.add(this.listener);
        }
    }

    /**
     *  This method can be safely called multiple times without any negative side effects
     */
    @Override
    public void unregister() {
        if (!ListenerUtil.mutListener.listen(63767)) {
            logger.debug("unregister()");
        }
        if (!ListenerUtil.mutListener.listen(63768)) {
            ListenerManager.profileListeners.remove(this.listener);
        }
    }

    /**
     *  Send the updated profile to the peer.
     */
    private void sendProfile(String nickname, boolean sendAvatar) {
        MsgpackObjectBuilder data;
        if (sendAvatar) {
            byte[] avatar = null;
            final Bitmap avatarBitmap = this.contactService.getAvatar(this.contactService.getMe(), true);
            if (!ListenerUtil.mutListener.listen(63770)) {
                if (avatarBitmap != null) {
                    if (!ListenerUtil.mutListener.listen(63769)) {
                        avatar = BitmapUtil.bitmapToByteArray(avatarBitmap, Protocol.FORMAT_AVATAR, Protocol.QUALITY_AVATAR_HIRES);
                    }
                }
            }
            data = Profile.convert(nickname, avatar);
        } else {
            data = Profile.convert(nickname);
        }
        if (!ListenerUtil.mutListener.listen(63771)) {
            // Send message
            logger.debug("Sending profile update");
        }
        if (!ListenerUtil.mutListener.listen(63772)) {
            this.send(this.updateDispatcher, data, null);
        }
    }

    @AnyThread
    private class Listener implements ProfileListener {

        @Override
        public void onAvatarChanged() {
            if (!ListenerUtil.mutListener.listen(63774)) {
                handler.post(new Runnable() {

                    @Override
                    @WorkerThread
                    public void run() {
                        if (!ListenerUtil.mutListener.listen(63773)) {
                            ProfileUpdateHandler.this.sendProfile(userService.getPublicNickname(), true);
                        }
                    }
                });
            }
        }

        @Override
        public void onAvatarRemoved() {
            if (!ListenerUtil.mutListener.listen(63775)) {
                this.onAvatarChanged();
            }
        }

        @Override
        public void onNicknameChanged(String newNickname) {
            if (!ListenerUtil.mutListener.listen(63777)) {
                handler.post(new Runnable() {

                    @Override
                    @WorkerThread
                    public void run() {
                        if (!ListenerUtil.mutListener.listen(63776)) {
                            ProfileUpdateHandler.this.sendProfile(newNickname, false);
                        }
                    }
                });
            }
        }
    }
}
