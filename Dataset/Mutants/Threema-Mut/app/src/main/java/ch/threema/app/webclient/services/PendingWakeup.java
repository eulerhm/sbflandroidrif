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
package ch.threema.app.webclient.services;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import ch.threema.annotation.SameThread;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * POJO for pending wakeups.
 */
@SameThread
class PendingWakeup {

    @NonNull
    final String publicKeySha256String;

    @Nullable
    String affiliationId;

    long expiration;

    PendingWakeup(@NonNull final String publicKeySha256String, @Nullable final String affiliationId, final long expiration) {
        this.publicKeySha256String = publicKeySha256String;
        if (!ListenerUtil.mutListener.listen(64384)) {
            this.affiliationId = affiliationId;
        }
        if (!ListenerUtil.mutListener.listen(64385)) {
            this.expiration = expiration;
        }
    }
}
