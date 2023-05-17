/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
 * Copyright (c) 2020-2021 Threema GmbH
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
package ch.threema.app.voip.util;

import com.google.protobuf.ByteString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import ch.threema.app.utils.RandomUtil;
import ch.threema.app.voip.signaling.ToSignalingMessage;
import ch.threema.protobuf.callsignaling.CallSignaling;
import ch.threema.protobuf.callsignaling.CallSignaling.VideoQualityProfile.QualityProfile;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Manage video quality profiles.
 */
public class VoipVideoParams implements ToSignalingMessage {

    private static final Logger logger = LoggerFactory.getLogger(VoipVideoParams.class);

    @Nullable
    private final QualityProfile profile;

    private final int maxBitrateKbps;

    private final int maxFps;

    private final int maxWidth, maxHeight;

    public static final int MIN_BITRATE = 200;

    public static final int MIN_FPS = 15;

    public static final int MIN_WIDTH = 320;

    public static final int MIN_HEIGHT = 240;

    private VoipVideoParams(@Nullable QualityProfile profile, int maxBitrateKbps, int maxFps, int maxWidth, int maxHeight) {
        this.profile = profile;
        this.maxBitrateKbps = maxBitrateKbps;
        this.maxFps = maxFps;
        this.maxWidth = maxWidth;
        this.maxHeight = maxHeight;
    }

    @Override
    public int getType() {
        return CallSignaling.Envelope.VIDEO_QUALITY_PROFILE_FIELD_NUMBER;
    }

    /**
     *  Return the profile name.
     */
    @Nullable
    public QualityProfile getProfile() {
        return profile;
    }

    /**
     *  Return the max allowed bitrate in kbps.
     */
    public int getMaxBitrateKbps() {
        return this.maxBitrateKbps;
    }

    /**
     *  Return the max framerate.
     */
    public int getMaxFps() {
        return this.maxFps;
    }

    /**
     *  Return the max width.
     */
    public int getMaxWidth() {
        return this.maxWidth;
    }

    /**
     *  Return the max width.
     */
    public int getMaxHeight() {
        return this.maxHeight;
    }

    @NonNull
    @Override
    public String toString() {
        return "VoipVideoParams{" + "profile=" + profile + ", " + maxBitrateKbps + "kbps" + ", " + maxFps + "fps" + ", " + maxWidth + "x" + maxHeight + '}';
    }

    /**
     *  Low bitrate profile:
     *
     *  - 400 kbps bitrate
     *  - 20 FPS
     *  - 960x540 px resolution
     */
    public static VoipVideoParams low() {
        return new VoipVideoParams(QualityProfile.LOW, 400, 20, 960, 540);
    }

    /**
     *  High bitrate profile:
     *
     *  - 2000 kbps bitrate
     *  - 25 FPS
     *  - 1280x720 px resolution
     */
    public static VoipVideoParams high() {
        return new VoipVideoParams(QualityProfile.HIGH, 2000, 25, 1280, 720);
    }

    /**
     *  Highest bitrate profile:
     *
     *  - 4000 kbps bitrate
     *  - 25 FPS
     *  - 1920x1080 px resolution
     */
    public static VoipVideoParams max() {
        return new VoipVideoParams(QualityProfile.MAX, 4000, 25, 1920, 1080);
    }

    /**
     *  Create a VoipVideoParams object from a string setting, depending on the current metering state.
     */
    @NonNull
    public static VoipVideoParams getParamsFromSetting(@Nullable String setting, @Nullable Boolean isMetered) {
        if (!ListenerUtil.mutListener.listen(60559)) {
            if (setting != null) {
                if (!ListenerUtil.mutListener.listen(60558)) {
                    switch(setting) {
                        case // AUTO
                        "0":
                            if (!ListenerUtil.mutListener.listen(60557)) {
                                if ((ListenerUtil.mutListener.listen(60556) ? (isMetered != null || isMetered) : (isMetered != null && isMetered))) {
                                    return VoipVideoParams.low();
                                } else {
                                    return VoipVideoParams.high();
                                }
                            }
                        case // LOW BANDWIDTH
                        "1":
                            return VoipVideoParams.low();
                        case // HIGHEST QUALITY
                        "2":
                            return VoipVideoParams.max();
                    }
                }
            }
        }
        // DEFAULT
        return VoipVideoParams.high();
    }

    /**
     *  When the peer sends a profile, pick the lower quality settings of the two.
     *
     *  Note: If both parameters specify a named profile, then the lower of the two profiles
     *  is initialized with the current default values. Only if one or both profiles are
     *  non-named (unknown), then the actual values are being considered.
     *
     *  When comparing the raw values, these are clamped on the lower end to the LOW profile.
     *
     *  The MAX profile is only selected if the network is not relayed.
     *
     *  If {@param peerParams} is null, return the current profile.
     *
     *  @throws RuntimeException if a common profile could not be determined.
     *      This indicates a bug in the implementation.
     */
    @NonNull
    public VoipVideoParams findCommonProfile(@Nullable VoipVideoParams peerParams, @Nullable Boolean networkIsRelayed) throws RuntimeException {
        if (peerParams == null) {
            return this;
        }
        if (!ListenerUtil.mutListener.listen(60560)) {
            logger.debug("findCommonProfile: this={} peer={} relayed={}", this.profile, peerParams.profile, networkIsRelayed);
        }
        if ((ListenerUtil.mutListener.listen(60563) ? ((ListenerUtil.mutListener.listen(60562) ? ((ListenerUtil.mutListener.listen(60561) ? (this.profile == null && this.profile == QualityProfile.UNRECOGNIZED) : (this.profile == null || this.profile == QualityProfile.UNRECOGNIZED)) && peerParams.getProfile() == null) : ((ListenerUtil.mutListener.listen(60561) ? (this.profile == null && this.profile == QualityProfile.UNRECOGNIZED) : (this.profile == null || this.profile == QualityProfile.UNRECOGNIZED)) || peerParams.getProfile() == null)) && peerParams.getProfile() == QualityProfile.UNRECOGNIZED) : ((ListenerUtil.mutListener.listen(60562) ? ((ListenerUtil.mutListener.listen(60561) ? (this.profile == null && this.profile == QualityProfile.UNRECOGNIZED) : (this.profile == null || this.profile == QualityProfile.UNRECOGNIZED)) && peerParams.getProfile() == null) : ((ListenerUtil.mutListener.listen(60561) ? (this.profile == null && this.profile == QualityProfile.UNRECOGNIZED) : (this.profile == null || this.profile == QualityProfile.UNRECOGNIZED)) || peerParams.getProfile() == null)) || peerParams.getProfile() == QualityProfile.UNRECOGNIZED))) {
            return new VoipVideoParams(null, Math.max(Math.min(this.maxBitrateKbps, peerParams.getMaxBitrateKbps()), MIN_BITRATE), Math.max(Math.min(this.maxFps, peerParams.getMaxFps()), MIN_FPS), Math.max(Math.min(this.maxWidth, peerParams.getMaxWidth()), MIN_WIDTH), Math.max(Math.min(this.maxHeight, peerParams.getMaxHeight()), MIN_HEIGHT));
        } else if ((ListenerUtil.mutListener.listen(60564) ? (this.profile == QualityProfile.LOW && peerParams.profile == QualityProfile.LOW) : (this.profile == QualityProfile.LOW || peerParams.profile == QualityProfile.LOW))) {
            return VoipVideoParams.low();
        } else if ((ListenerUtil.mutListener.listen(60565) ? (this.profile == QualityProfile.HIGH && peerParams.profile == QualityProfile.HIGH) : (this.profile == QualityProfile.HIGH || peerParams.profile == QualityProfile.HIGH))) {
            return VoipVideoParams.high();
        } else if ((ListenerUtil.mutListener.listen(60566) ? (this.profile == QualityProfile.MAX && peerParams.profile == QualityProfile.MAX) : (this.profile == QualityProfile.MAX || peerParams.profile == QualityProfile.MAX))) {
            // Prevent MAX profile if relay is being used
            return Boolean.TRUE.equals(networkIsRelayed) ? VoipVideoParams.high() : VoipVideoParams.max();
        } else {
            throw new RuntimeException("Cannot find common profile");
        }
    }

    @Nullable
    public static VoipVideoParams fromSignalingMessage(@NonNull CallSignaling.VideoQualityProfile profile) {
        if (!ListenerUtil.mutListener.listen(60567)) {
            switch(profile.getProfile()) {
                case LOW:
                    return VoipVideoParams.low();
                case HIGH:
                    return VoipVideoParams.high();
                case MAX:
                    return VoipVideoParams.max();
            }
        }
        if (!ListenerUtil.mutListener.listen(60568)) {
            logger.warn("Unknown video profile: {} ({})", profile.getProfile(), profile.getProfileValue());
        }
        // Validate them.
        final int maxBitrate = profile.getMaxBitrateKbps();
        if (!ListenerUtil.mutListener.listen(60575)) {
            if ((ListenerUtil.mutListener.listen(60573) ? (maxBitrate >= 0) : (ListenerUtil.mutListener.listen(60572) ? (maxBitrate <= 0) : (ListenerUtil.mutListener.listen(60571) ? (maxBitrate > 0) : (ListenerUtil.mutListener.listen(60570) ? (maxBitrate < 0) : (ListenerUtil.mutListener.listen(60569) ? (maxBitrate != 0) : (maxBitrate == 0))))))) {
                if (!ListenerUtil.mutListener.listen(60574)) {
                    logger.warn("Received message with 0 maxBitrate");
                }
                return null;
            }
        }
        final int maxFps = profile.getMaxFps();
        if (!ListenerUtil.mutListener.listen(60582)) {
            if ((ListenerUtil.mutListener.listen(60580) ? (maxFps >= 0) : (ListenerUtil.mutListener.listen(60579) ? (maxFps <= 0) : (ListenerUtil.mutListener.listen(60578) ? (maxFps > 0) : (ListenerUtil.mutListener.listen(60577) ? (maxFps < 0) : (ListenerUtil.mutListener.listen(60576) ? (maxFps != 0) : (maxFps == 0))))))) {
                if (!ListenerUtil.mutListener.listen(60581)) {
                    logger.warn("Received message with 0 maxFps");
                }
                return null;
            }
        }
        if (!ListenerUtil.mutListener.listen(60584)) {
            if (!profile.hasMaxResolution()) {
                if (!ListenerUtil.mutListener.listen(60583)) {
                    logger.warn("Received message without max resolution");
                }
                return null;
            }
        }
        final CallSignaling.Resolution resolution = profile.getMaxResolution();
        if (!ListenerUtil.mutListener.listen(60587)) {
            if ((ListenerUtil.mutListener.listen(60585) ? (resolution.getWidth() == 0 && resolution.getHeight() == 0) : (resolution.getWidth() == 0 || resolution.getHeight() == 0))) {
                if (!ListenerUtil.mutListener.listen(60586)) {
                    logger.warn("Received message with 0 width or height");
                }
                return null;
            }
        }
        return new VoipVideoParams(QualityProfile.UNRECOGNIZED, maxBitrate, maxFps, resolution.getWidth(), resolution.getHeight());
    }

    @Override
    @NonNull
    public CallSignaling.Envelope toSignalingMessage() {
        final CallSignaling.Resolution.Builder resolution = CallSignaling.Resolution.newBuilder().setWidth(this.maxWidth).setHeight(this.maxHeight);
        final CallSignaling.VideoQualityProfile.Builder profile = CallSignaling.VideoQualityProfile.newBuilder().setProfile(this.profile).setMaxBitrateKbps(this.maxBitrateKbps).setMaxFps(this.maxFps).setMaxResolution(resolution);
        return CallSignaling.Envelope.newBuilder().setPadding(ByteString.copyFrom(RandomUtil.generateRandomPadding(0, 255))).setVideoQualityProfile(profile).build();
    }
}
