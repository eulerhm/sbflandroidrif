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
package ch.threema.app.ui;

import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.format.DateUtils;
import ch.threema.app.services.UserService;
import ch.threema.app.utils.TestUtil;
import ch.threema.storage.models.ContactModel;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class TypingIndicatorTextWatcher implements TextWatcher {

    private static final long TYPING_SEND_TIMEOUT = 10 * DateUtils.SECOND_IN_MILLIS;

    private final Handler typingIndicatorHandler = new Handler();

    private final UserService userService;

    private final ContactModel contactModel;

    private boolean isTypingSent = false;

    private String previousText = "";

    private final Runnable sendStoppedTyping = new Runnable() {

        public void run() {
            if (!ListenerUtil.mutListener.listen(47722)) {
                if (isTypingSent) {
                    if (!ListenerUtil.mutListener.listen(47720)) {
                        isTypingSent = false;
                    }
                    if (!ListenerUtil.mutListener.listen(47721)) {
                        userService.isTyping(contactModel.getIdentity(), false);
                    }
                }
            }
        }
    };

    public TypingIndicatorTextWatcher(UserService userService, ContactModel contactModel) {
        this.userService = userService;
        this.contactModel = contactModel;
    }

    public void stopTyping() {
        if (!ListenerUtil.mutListener.listen(47723)) {
            sendStoppedTyping.run();
        }
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        if (!ListenerUtil.mutListener.listen(47729)) {
            if (textHasChanged(charSequence)) {
                if (!ListenerUtil.mutListener.listen(47728)) {
                    if (!isTypingSent) {
                        if (!ListenerUtil.mutListener.listen(47727)) {
                            new Thread(new Runnable() {

                                @Override
                                public void run() {
                                    if (!ListenerUtil.mutListener.listen(47725)) {
                                        isTypingSent = true;
                                    }
                                    if (!ListenerUtil.mutListener.listen(47726)) {
                                        userService.isTyping(contactModel.getIdentity(), true);
                                    }
                                }
                            }).start();
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(47724)) {
                            // stop end typing sending handler
                            killEvents();
                        }
                    }
                }
            }
        }
    }

    public void killEvents() {
        if (!ListenerUtil.mutListener.listen(47730)) {
            typingIndicatorHandler.removeCallbacks(sendStoppedTyping);
        }
    }

    @Override
    public void afterTextChanged(Editable editable) {
        if (!ListenerUtil.mutListener.listen(47734)) {
            if ((ListenerUtil.mutListener.listen(47731) ? (editable != null || editable.length() == 0) : (editable != null && editable.length() == 0))) {
                if (!ListenerUtil.mutListener.listen(47733)) {
                    typingIndicatorHandler.post(sendStoppedTyping);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(47732)) {
                    typingIndicatorHandler.postDelayed(sendStoppedTyping, TYPING_SEND_TIMEOUT);
                }
            }
        }
    }

    private boolean textHasChanged(CharSequence charSequence) {
        if (!ListenerUtil.mutListener.listen(47737)) {
            if (charSequence != null) {
                if (!ListenerUtil.mutListener.listen(47736)) {
                    if (!TestUtil.compare(charSequence.toString(), this.previousText)) {
                        if (!ListenerUtil.mutListener.listen(47735)) {
                            this.previousText = charSequence.toString();
                        }
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
