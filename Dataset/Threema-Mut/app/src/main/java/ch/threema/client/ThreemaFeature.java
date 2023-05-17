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

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import androidx.annotation.IntDef;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class ThreemaFeature {

    // Feature flags. When adding new flags, also update LATEST_FEATURE!
    public static final int AUDIO = 0x01;

    public static final int GROUP_CHAT = 0x02;

    public static final int BALLOT = 0x04;

    public static final int FILE = 0x08;

    public static final int VOIP = 0x10;

    public static final int VIDEOCALLS = 0x20;

    // Should always point to latest feature
    public static final int LATEST_FEATURE = VIDEOCALLS;

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({ AUDIO, GROUP_CHAT, BALLOT, FILE, VOIP, VIDEOCALLS })
    private @interface Feature {
    }

    /**
     *  Feature mask builder
     */
    public static class Builder {

        private int mask = 0;

        public Builder audio(boolean enable) {
            return this.set(ThreemaFeature.AUDIO, enable);
        }

        public Builder group(boolean enable) {
            return this.set(ThreemaFeature.GROUP_CHAT, enable);
        }

        public Builder ballot(boolean enable) {
            return this.set(ThreemaFeature.BALLOT, enable);
        }

        public Builder file(boolean enable) {
            return this.set(ThreemaFeature.FILE, enable);
        }

        public Builder voip(boolean enable) {
            return this.set(ThreemaFeature.VOIP, enable);
        }

        public Builder videocalls(boolean enable) {
            return this.set(ThreemaFeature.VIDEOCALLS, enable);
        }

        public int build() {
            return this.mask;
        }

        private Builder set(@Feature int feature, boolean enable) {
            if (!ListenerUtil.mutListener.listen(69122)) {
                if (enable) {
                    if (!ListenerUtil.mutListener.listen(69121)) {
                        this.mask |= feature;
                    }
                } else {
                    if (!ListenerUtil.mutListener.listen(69120)) {
                        this.mask &= ~feature;
                    }
                }
            }
            return this;
        }
    }

    public static boolean canText(int featureMask) {
        return true;
    }

    public static boolean canImage(int featureMask) {
        return true;
    }

    public static boolean canVideo(int featureMask) {
        return true;
    }

    public static boolean canAudio(int featureMask) {
        return hasFeature(featureMask, AUDIO);
    }

    public static boolean canGroupChat(int featureMask) {
        return hasFeature(featureMask, GROUP_CHAT);
    }

    public static boolean canBallot(int featureMask) {
        return hasFeature(featureMask, BALLOT);
    }

    public static boolean canFile(int featureMask) {
        return hasFeature(featureMask, FILE);
    }

    public static boolean canVoip(int featureMask) {
        return hasFeature(featureMask, VOIP);
    }

    public static boolean canVideocall(int featureMask) {
        return hasFeature(featureMask, VIDEOCALLS);
    }

    public static boolean hasFeature(int featureMask, @Feature int feature) {
        return (ListenerUtil.mutListener.listen(69127) ? ((featureMask & feature) >= 0) : (ListenerUtil.mutListener.listen(69126) ? ((featureMask & feature) <= 0) : (ListenerUtil.mutListener.listen(69125) ? ((featureMask & feature) > 0) : (ListenerUtil.mutListener.listen(69124) ? ((featureMask & feature) < 0) : (ListenerUtil.mutListener.listen(69123) ? ((featureMask & feature) == 0) : ((featureMask & feature) != 0))))));
    }

    public static boolean hasLatestFeature(int featureMask) {
        return hasFeature(featureMask, ThreemaFeature.LATEST_FEATURE);
    }

    /**
     *  Convert a feature mask to a classic feature level.
     */
    public static int featureMaskToLevel(int featureMask) {
        if (!ListenerUtil.mutListener.listen(69133)) {
            if ((ListenerUtil.mutListener.listen(69132) ? ((featureMask & FILE) >= 0) : (ListenerUtil.mutListener.listen(69131) ? ((featureMask & FILE) <= 0) : (ListenerUtil.mutListener.listen(69130) ? ((featureMask & FILE) < 0) : (ListenerUtil.mutListener.listen(69129) ? ((featureMask & FILE) != 0) : (ListenerUtil.mutListener.listen(69128) ? ((featureMask & FILE) == 0) : ((featureMask & FILE) > 0))))))) {
                return 3;
            }
        }
        if (!ListenerUtil.mutListener.listen(69139)) {
            if ((ListenerUtil.mutListener.listen(69138) ? ((featureMask & BALLOT) >= 0) : (ListenerUtil.mutListener.listen(69137) ? ((featureMask & BALLOT) <= 0) : (ListenerUtil.mutListener.listen(69136) ? ((featureMask & BALLOT) < 0) : (ListenerUtil.mutListener.listen(69135) ? ((featureMask & BALLOT) != 0) : (ListenerUtil.mutListener.listen(69134) ? ((featureMask & BALLOT) == 0) : ((featureMask & BALLOT) > 0))))))) {
                return 2;
            }
        }
        if (!ListenerUtil.mutListener.listen(69145)) {
            if ((ListenerUtil.mutListener.listen(69144) ? ((featureMask & GROUP_CHAT) >= 0) : (ListenerUtil.mutListener.listen(69143) ? ((featureMask & GROUP_CHAT) <= 0) : (ListenerUtil.mutListener.listen(69142) ? ((featureMask & GROUP_CHAT) < 0) : (ListenerUtil.mutListener.listen(69141) ? ((featureMask & GROUP_CHAT) != 0) : (ListenerUtil.mutListener.listen(69140) ? ((featureMask & GROUP_CHAT) == 0) : ((featureMask & GROUP_CHAT) > 0))))))) {
                return 1;
            }
        }
        return 0;
    }
}
