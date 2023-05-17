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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import org.slf4j.Logger;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import ch.threema.app.utils.LogUtil;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class SdpPatcher {

    // Regex patterns
    private static final Pattern SDP_MEDIA_AUDIO_ANY_RE = Pattern.compile("m=audio ([^ ]+) ([^ ]+) (.+)");

    private static final Pattern SDP_RTPMAP_OPUS_RE = Pattern.compile("a=rtpmap:([^ ]+) opus.*");

    private static final Pattern SDP_RTPMAP_ANY_RE = Pattern.compile("a=rtpmap:([^ ]+) .*");

    private static final Pattern SDP_FMTP_ANY_RE = Pattern.compile("a=fmtp:([^ ]+) ([^ ]+)");

    private static final Pattern SDP_EXTMAP_ANY_RE = Pattern.compile("a=extmap:[^ ]+ (.*)");

    /**
     *  Whether this SDP is created locally and it is the offer, a local answer
     *  or a remote SDP.
     */
    public enum Type {

        LOCAL_OFFER, LOCAL_ANSWER_OR_REMOTE_SDP
    }

    /**
     *  RTP header extension configuration.
     */
    public enum RtpHeaderExtensionConfig {

        DISABLE, ENABLE_WITH_LEGACY_ONE_BYTE_HEADER_ONLY, ENABLE_WITH_ONE_AND_TWO_BYTE_HEADER
    }

    /**
     *  The SDP is invalid (for our cases, at least).
     */
    public static class InvalidSdpException extends Exception {

        InvalidSdpException(@NonNull final String description) {
            super(description);
        }
    }

    // Configuration fields
    @NonNull
    private Logger logger = LogUtil.NULL_LOGGER;

    @NonNull
    private RtpHeaderExtensionConfig rtpHeaderExtensionConfig = RtpHeaderExtensionConfig.DISABLE;

    /**
     *  Set a logger instance.
     */
    public SdpPatcher withLogger(final Logger logger) {
        if (!ListenerUtil.mutListener.listen(59800)) {
            this.logger = logger;
        }
        return this;
    }

    /**
     *  Set whether RTP header extensions should be enabled and in which mode.
     */
    public SdpPatcher withRtpHeaderExtensions(@NonNull final RtpHeaderExtensionConfig config) {
        if (!ListenerUtil.mutListener.listen(59801)) {
            this.rtpHeaderExtensionConfig = config;
        }
        return this;
    }

    /**
     *  Patch an SDP offer / answer with a few things that we want to enforce in Threema:
     *
     *  For all media lines:
     *
     *  - Remove audio level and frame marking header extensions
     *  - Remap extmap IDs (when offering)
     *
     *  For audio in specific:
     *
     *  - Only support Opus, remove all other codecs
     *  - Force CBR
     *
     *  The use of CBR (constant bit rate) will also suppress VAD (voice activity detection). For
     *  more security considerations regarding codec configuration, see RFC 6562:
     *  https://tools.ietf.org/html/rfc6562
     *
     *  Return the updated session description.
     */
    @NonNull
    public String patch(@NonNull final Type type, @NonNull final String sdp) throws InvalidSdpException, IOException {
        // First, find RTP payload type number for Opus codec
        final Matcher matcher = SDP_RTPMAP_OPUS_RE.matcher(sdp);
        final String payloadTypeOpus;
        if (matcher.find()) {
            payloadTypeOpus = Objects.requireNonNull(matcher.group(1));
        } else {
            throw new SdpPatcher.InvalidSdpException("a=rtpmap: [...] opus not found");
        }
        // Create context
        final SdpPatcherContext context = new SdpPatcherContext(type, this, payloadTypeOpus);
        // Iterate over all lines
        final StringBuilder lines = new StringBuilder();
        final BufferedReader reader = new BufferedReader(new StringReader(sdp));
        String lineStr;
        if (!ListenerUtil.mutListener.listen(59803)) {
            {
                long _loopCounter707 = 0;
                while ((lineStr = reader.readLine()) != null) {
                    ListenerUtil.loopListener.listen("_loopCounter707", ++_loopCounter707);
                    if (!ListenerUtil.mutListener.listen(59802)) {
                        SdpPatcher.handleLine(context, reader, lines, lineStr);
                    }
                }
            }
        }
        // Done, return lines
        return lines.toString();
    }

    /**
     *  SDP section type.
     */
    private enum SdpSection {

        GLOBAL(false), MEDIA_AUDIO(true), MEDIA_VIDEO(true), MEDIA_DATA_CHANNEL(false), MEDIA_UNKNOWN(false);

        public final boolean isRtpSection;

        SdpSection(final boolean isRtpSection) {
            this.isRtpSection = isRtpSection;
        }
    }

    /**
     *  Whether the line is to be accepted, rejected or rewritten.
     */
    private enum LineAction {

        ACCEPT, REJECT, REWRITE
    }

    /**
     *  An SDP line to accept, reject or rewrite.
     */
    private static class Line {

        @NonNull
        private String line;

        @Nullable
        private LineAction action;

        Line(@NonNull final String line) {
            if (!ListenerUtil.mutListener.listen(59804)) {
                this.line = line;
            }
        }

        @NonNull
        String get() {
            return this.line;
        }

        @NonNull
        LineAction accept() {
            if (!ListenerUtil.mutListener.listen(59805)) {
                if (this.action != null) {
                    throw new IllegalArgumentException("LineAction.action already set");
                }
            }
            if (!ListenerUtil.mutListener.listen(59806)) {
                this.action = LineAction.ACCEPT;
            }
            return this.action;
        }

        @NonNull
        LineAction reject() {
            if (!ListenerUtil.mutListener.listen(59807)) {
                if (this.action != null) {
                    throw new IllegalArgumentException("LineAction.action already set");
                }
            }
            if (!ListenerUtil.mutListener.listen(59808)) {
                this.action = LineAction.REJECT;
            }
            return this.action;
        }

        @NonNull
        LineAction rewrite(@NonNull final String line) {
            if (!ListenerUtil.mutListener.listen(59809)) {
                if (this.action != null) {
                    throw new IllegalArgumentException("LineAction.action already set");
                }
            }
            if (!ListenerUtil.mutListener.listen(59810)) {
                this.action = LineAction.REWRITE;
            }
            if (!ListenerUtil.mutListener.listen(59811)) {
                this.line = line;
            }
            return this.action;
        }

        @NonNull
        LineAction rewrite(@NonNull final StringBuilder builder) {
            return this.rewrite(builder.toString());
        }
    }

    /**
     *  RTP extension ID remapper.
     */
    private static class RtpExtensionIdRemapper {

        private int currentId;

        private int maxId;

        @NonNull
        private Map<String, Integer> extensionIdMap = new HashMap<>();

        private RtpExtensionIdRemapper(@NonNull final SdpPatcher config) {
            if (!ListenerUtil.mutListener.listen(59812)) {
                // See: RFC 5285 sec 4.2, sec 4.3
                this.currentId = 1;
            }
            if (!ListenerUtil.mutListener.listen(59816)) {
                switch(config.rtpHeaderExtensionConfig) {
                    case ENABLE_WITH_LEGACY_ONE_BYTE_HEADER_ONLY:
                        if (!ListenerUtil.mutListener.listen(59813)) {
                            this.maxId = 14;
                        }
                        break;
                    case ENABLE_WITH_ONE_AND_TWO_BYTE_HEADER:
                        if (!ListenerUtil.mutListener.listen(59814)) {
                            this.maxId = 255;
                        }
                        break;
                    default:
                        if (!ListenerUtil.mutListener.listen(59815)) {
                            this.maxId = 0;
                        }
                        break;
                }
            }
        }

        int assignId(final String uriAndAttributes) throws InvalidSdpException {
            // get all sorts of weird behaviour from the WebRTC stack.
            Integer id = this.extensionIdMap.get(uriAndAttributes);
            if (!ListenerUtil.mutListener.listen(59832)) {
                if (id == null) {
                    if (!ListenerUtil.mutListener.listen(59822)) {
                        // Check if exhausted
                        if ((ListenerUtil.mutListener.listen(59821) ? (this.currentId >= this.maxId) : (ListenerUtil.mutListener.listen(59820) ? (this.currentId <= this.maxId) : (ListenerUtil.mutListener.listen(59819) ? (this.currentId < this.maxId) : (ListenerUtil.mutListener.listen(59818) ? (this.currentId != this.maxId) : (ListenerUtil.mutListener.listen(59817) ? (this.currentId == this.maxId) : (this.currentId > this.maxId))))))) {
                            throw new InvalidSdpException("RTP extension IDs exhausted");
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(59823)) {
                        // Assign an ID
                        id = this.currentId++;
                    }
                    if (!ListenerUtil.mutListener.listen(59830)) {
                        // Let's just skip 15 to be safe, see: RFC 5285 sec 4.2
                        if ((ListenerUtil.mutListener.listen(59828) ? (this.currentId >= 15) : (ListenerUtil.mutListener.listen(59827) ? (this.currentId <= 15) : (ListenerUtil.mutListener.listen(59826) ? (this.currentId > 15) : (ListenerUtil.mutListener.listen(59825) ? (this.currentId < 15) : (ListenerUtil.mutListener.listen(59824) ? (this.currentId != 15) : (this.currentId == 15))))))) {
                            if (!ListenerUtil.mutListener.listen(59829)) {
                                ++this.currentId;
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(59831)) {
                        // Store URI and assigned ID
                        this.extensionIdMap.put(uriAndAttributes, id);
                    }
                }
            }
            return id;
        }
    }

    /**
     *  SDP patcher context storage.
     */
    private static class SdpPatcherContext {

        @NonNull
        private final Type type;

        @NonNull
        private final SdpPatcher config;

        @NonNull
        private final String payloadTypeOpus;

        @NonNull
        private final RtpExtensionIdRemapper rtpExtensionIdRemapper;

        @NonNull
        private SdpSection section;

        private SdpPatcherContext(@NonNull final Type type, @NonNull final SdpPatcher config, @NonNull final String payloadTypeOpus) {
            this.type = type;
            this.config = config;
            this.payloadTypeOpus = payloadTypeOpus;
            this.rtpExtensionIdRemapper = new RtpExtensionIdRemapper(config);
            if (!ListenerUtil.mutListener.listen(59833)) {
                this.section = SdpSection.GLOBAL;
            }
        }
    }

    /**
     *  Handle an SDP line.
     */
    private static void handleLine(@NonNull final SdpPatcherContext context, @NonNull final BufferedReader reader, @NonNull final StringBuilder lines, @NonNull String lineStr) throws InvalidSdpException, IOException {
        final SdpSection current = context.section;
        final Line line = new Line(lineStr);
        final LineAction action;
        // Introduce a new section or forward depending on the section type
        if (lineStr.startsWith("m=")) {
            action = SdpPatcher.handleSectionLine(context, line);
        } else {
            switch(context.section) {
                case GLOBAL:
                    action = SdpPatcher.handleGlobalLine(context, line);
                    break;
                case MEDIA_AUDIO:
                    action = SdpPatcher.handleAudioLine(context, line);
                    break;
                case MEDIA_VIDEO:
                    action = SdpPatcher.handleVideoLine(context, line);
                    break;
                case MEDIA_DATA_CHANNEL:
                    action = SdpPatcher.handleDataChannelLine(context, line);
                    break;
                default:
                    // a line within that section should never be parsed.
                    throw new InvalidSdpException(String.format(Locale.US, "Unknown section %s", current));
            }
        }
        if (!ListenerUtil.mutListener.listen(59837)) {
            // Execute line action
            switch(action) {
                // fallthrough
                case ACCEPT:
                case REWRITE:
                    if (!ListenerUtil.mutListener.listen(59834)) {
                        lines.append(line.get()).append("\r\n");
                    }
                    break;
                case REJECT:
                    if (!ListenerUtil.mutListener.listen(59836)) {
                        // Log
                        if (context.config.logger.isDebugEnabled()) {
                            if (!ListenerUtil.mutListener.listen(59835)) {
                                context.config.logger.debug("Rejected line: {}", line.get());
                            }
                        }
                    }
                    break;
                default:
                    throw new IllegalArgumentException(String.format(Locale.US, "Unknown line action %s", action));
            }
        }
        if (!ListenerUtil.mutListener.listen(59847)) {
            // we need to reject the remainder of the section.
            if ((ListenerUtil.mutListener.listen(59838) ? (current != context.section || action == LineAction.REJECT) : (current != context.section && action == LineAction.REJECT))) {
                final StringBuilder debug = context.config.logger.isDebugEnabled() ? new StringBuilder() : null;
                if (!ListenerUtil.mutListener.listen(59842)) {
                    {
                        long _loopCounter708 = 0;
                        while ((ListenerUtil.mutListener.listen(59841) ? ((lineStr = reader.readLine()) != null || !lineStr.startsWith("m=")) : ((lineStr = reader.readLine()) != null && !lineStr.startsWith("m=")))) {
                            ListenerUtil.loopListener.listen("_loopCounter708", ++_loopCounter708);
                            if (!ListenerUtil.mutListener.listen(59840)) {
                                if (debug != null) {
                                    if (!ListenerUtil.mutListener.listen(59839)) {
                                        debug.append(line.get());
                                    }
                                }
                            }
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(59844)) {
                    if (debug != null) {
                        if (!ListenerUtil.mutListener.listen(59843)) {
                            context.config.logger.debug("Rejected section:\n{}", debug);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(59846)) {
                    if (lineStr != null) {
                        if (!ListenerUtil.mutListener.listen(59845)) {
                            // the reader, we need to handle it here.
                            SdpPatcher.handleLine(context, reader, lines, lineStr);
                        }
                    }
                }
            }
        }
    }

    /**
     *  Handle a section line.
     */
    @NonNull
    private static LineAction handleSectionLine(@NonNull final SdpPatcherContext context, @NonNull final Line line) throws InvalidSdpException {
        String lineStr = line.get();
        final Matcher matcher;
        // Audio section
        if ((matcher = SDP_MEDIA_AUDIO_ANY_RE.matcher(lineStr)).matches()) {
            if (!ListenerUtil.mutListener.listen(59848)) {
                // Mark current section
                context.section = SdpSection.MEDIA_AUDIO;
            }
            // Parse media description line
            final String port = Objects.requireNonNull(matcher.group(1));
            final String proto = Objects.requireNonNull(matcher.group(2));
            final String payloadTypes = Objects.requireNonNull(matcher.group(3));
            if (!ListenerUtil.mutListener.listen(59849)) {
                // Make sure that the Opus payload type is contained here
                if (!Arrays.asList(payloadTypes.split(" ")).contains(context.payloadTypeOpus)) {
                    throw new InvalidSdpException(String.format(Locale.US, "Opus payload type (%s) not found in audio media description", context.payloadTypeOpus));
                }
            }
            // Rewrite with only the payload types that we want
            return line.rewrite(String.format(Locale.US, "m=audio %s %s %s", port, proto, context.payloadTypeOpus));
        }
        if (!ListenerUtil.mutListener.listen(59851)) {
            // Video section
            if (lineStr.startsWith("m=video")) {
                if (!ListenerUtil.mutListener.listen(59850)) {
                    // Accept
                    context.section = SdpSection.MEDIA_VIDEO;
                }
                return line.accept();
            }
        }
        if (!ListenerUtil.mutListener.listen(59854)) {
            // Data channel section
            if ((ListenerUtil.mutListener.listen(59852) ? (lineStr.startsWith("m=application") || lineStr.contains("DTLS/SCTP")) : (lineStr.startsWith("m=application") && lineStr.contains("DTLS/SCTP")))) {
                if (!ListenerUtil.mutListener.listen(59853)) {
                    // Accept
                    context.section = SdpSection.MEDIA_DATA_CHANNEL;
                }
                return line.accept();
            }
        }
        if (!ListenerUtil.mutListener.listen(59855)) {
            // Unknown section (reject)
            context.section = SdpSection.MEDIA_UNKNOWN;
        }
        return line.reject();
    }

    /**
     *  Handle global (non-media) section line.
     */
    @NonNull
    private static LineAction handleGlobalLine(@NonNull final SdpPatcherContext context, @NonNull final Line line) {
        return SdpPatcher.handleRtpAttributes(context, line);
    }

    /**
     *  Handle RTP attributes shared across global (non-media) and media sections.
     */
    @NonNull
    private static LineAction handleRtpAttributes(@NonNull final SdpPatcherContext context, @NonNull final Line line) {
        final String lineStr = line.get();
        if (!ListenerUtil.mutListener.listen(59857)) {
            // Reject one-/two-byte RTP header mixed mode, if requested
            if ((ListenerUtil.mutListener.listen(59856) ? (context.config.rtpHeaderExtensionConfig != RtpHeaderExtensionConfig.ENABLE_WITH_ONE_AND_TWO_BYTE_HEADER || lineStr.startsWith("a=extmap-allow-mixed")) : (context.config.rtpHeaderExtensionConfig != RtpHeaderExtensionConfig.ENABLE_WITH_ONE_AND_TWO_BYTE_HEADER && lineStr.startsWith("a=extmap-allow-mixed")))) {
                return line.reject();
            }
        }
        // Accept the rest
        return line.accept();
    }

    /**
     *  Handle audio section line.
     */
    @NonNull
    private static LineAction handleAudioLine(@NonNull final SdpPatcherContext context, @NonNull final Line line) throws InvalidSdpException {
        final String lineStr = line.get();
        Matcher matcher;
        // RTP mappings
        if ((matcher = SDP_RTPMAP_ANY_RE.matcher(lineStr)).matches()) {
            final String payloadType = Objects.requireNonNull(matcher.group(1));
            if (!ListenerUtil.mutListener.listen(59858)) {
                // Accept Opus RTP mappings, reject the rest
                if (payloadType.equals(context.payloadTypeOpus)) {
                    return line.accept();
                } else {
                    return line.reject();
                }
            }
        }
        // RTP format parameters
        if ((matcher = SDP_FMTP_ANY_RE.matcher(lineStr)).matches()) {
            final String payloadType = Objects.requireNonNull(matcher.group(1));
            final String paramString = Objects.requireNonNull(matcher.group(2));
            if (!ListenerUtil.mutListener.listen(59859)) {
                if (!payloadType.equals(context.payloadTypeOpus)) {
                    // Reject non-opus RTP format parameters
                    return line.reject();
                }
            }
            // Split parameters
            final String[] params = paramString.split(";");
            // Specify what params we want to change
            final Set<String> paramUpdates = new HashSet<>();
            if (!ListenerUtil.mutListener.listen(59860)) {
                paramUpdates.add("stereo");
            }
            if (!ListenerUtil.mutListener.listen(59861)) {
                paramUpdates.add("sprop-stereo");
            }
            if (!ListenerUtil.mutListener.listen(59862)) {
                paramUpdates.add("cbr");
            }
            // Write unchanged params
            StringBuilder builder = new StringBuilder();
            if (!ListenerUtil.mutListener.listen(59863)) {
                builder.append("a=fmtp:");
            }
            if (!ListenerUtil.mutListener.listen(59864)) {
                builder.append(context.payloadTypeOpus);
            }
            if (!ListenerUtil.mutListener.listen(59865)) {
                builder.append(" ");
            }
            if (!ListenerUtil.mutListener.listen(59870)) {
                {
                    long _loopCounter709 = 0;
                    for (String param : params) {
                        ListenerUtil.loopListener.listen("_loopCounter709", ++_loopCounter709);
                        final String key = param.split("=")[0];
                        if (!ListenerUtil.mutListener.listen(59869)) {
                            if ((ListenerUtil.mutListener.listen(59866) ? (!param.isEmpty() || !paramUpdates.contains(key)) : (!param.isEmpty() && !paramUpdates.contains(key)))) {
                                if (!ListenerUtil.mutListener.listen(59867)) {
                                    builder.append(param);
                                }
                                if (!ListenerUtil.mutListener.listen(59868)) {
                                    builder.append(";");
                                }
                            }
                        }
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(59871)) {
                // Write our custom params
                builder.append("stereo=0;sprop-stereo=0;cbr=1");
            }
            return line.rewrite(builder);
        }
        // Handle RTP header extensions
        if ((matcher = SDP_EXTMAP_ANY_RE.matcher(lineStr)).matches()) {
            final String uriAndAttributes = Objects.requireNonNull(matcher.group(1));
            return SdpPatcher.handleRtpHeaderExtensionLine(context, line, uriAndAttributes);
        }
        // Handle further common cases
        return SdpPatcher.handleRtpAttributes(context, line);
    }

    /**
     *  Handle video section line.
     */
    @NonNull
    private static LineAction handleVideoLine(@NonNull final SdpPatcherContext context, @NonNull final Line line) throws InvalidSdpException {
        final String lineStr = line.get();
        Matcher matcher;
        // Handle RTP header extensions
        if ((matcher = SDP_EXTMAP_ANY_RE.matcher(lineStr)).matches()) {
            final String uriAndAttributes = Objects.requireNonNull(matcher.group(1));
            return SdpPatcher.handleRtpHeaderExtensionLine(context, line, uriAndAttributes);
        }
        // Handle further common cases
        return SdpPatcher.handleRtpAttributes(context, line);
    }

    /**
     *  Handle data channel section line.
     */
    @NonNull
    private static LineAction handleDataChannelLine(@NonNull final SdpPatcherContext context, @NonNull final Line line) {
        // Data channel <3
        return line.accept();
    }

    @NonNull
    private static LineAction handleRtpHeaderExtensionLine(@NonNull final SdpPatcherContext context, @NonNull final Line line, @NonNull final String uriAndAttributes) throws InvalidSdpException {
        if (!ListenerUtil.mutListener.listen(59872)) {
            // Always reject if disabled
            if (context.config.rtpHeaderExtensionConfig == RtpHeaderExtensionConfig.DISABLE) {
                return line.reject();
            }
        }
        if (!ListenerUtil.mutListener.listen(59875)) {
            // Always reject some of the header extensions
            if ((ListenerUtil.mutListener.listen(59874) ? (// Audio level, only useful for SFU use cases, remove
            (ListenerUtil.mutListener.listen(59873) ? (uriAndAttributes.contains("urn:ietf:params:rtp-hdrext:ssrc-audio-level") && uriAndAttributes.contains("urn:ietf:params:rtp-hdrext:csrc-audio-level")) : (uriAndAttributes.contains("urn:ietf:params:rtp-hdrext:ssrc-audio-level") || uriAndAttributes.contains("urn:ietf:params:rtp-hdrext:csrc-audio-level"))) && // Frame marking, only useful for SFU use cases, remove
            uriAndAttributes.contains("http://tools.ietf.org/html/draft-ietf-avtext-framemarking-07")) : (// Audio level, only useful for SFU use cases, remove
            (ListenerUtil.mutListener.listen(59873) ? (uriAndAttributes.contains("urn:ietf:params:rtp-hdrext:ssrc-audio-level") && uriAndAttributes.contains("urn:ietf:params:rtp-hdrext:csrc-audio-level")) : (uriAndAttributes.contains("urn:ietf:params:rtp-hdrext:ssrc-audio-level") || uriAndAttributes.contains("urn:ietf:params:rtp-hdrext:csrc-audio-level"))) || // Frame marking, only useful for SFU use cases, remove
            uriAndAttributes.contains("http://tools.ietf.org/html/draft-ietf-avtext-framemarking-07")))) {
                return line.reject();
            }
        }
        if (!ListenerUtil.mutListener.listen(59876)) {
            // Require encryption for the remainder of headers
            if (uriAndAttributes.startsWith("urn:ietf:params:rtp-hdrext:encrypt")) {
                return SdpPatcher.remapRtpHeaderExtensionIfOutbound(context, line, uriAndAttributes);
            }
        }
        // Reject the rest
        return line.reject();
    }

    @NonNull
    private static LineAction remapRtpHeaderExtensionIfOutbound(@NonNull final SdpPatcherContext context, @NonNull final Line line, @NonNull final String uriAndAttributes) throws InvalidSdpException {
        // Rewrite if local offer, otherwise accept
        if (context.type == Type.LOCAL_OFFER) {
            return line.rewrite(String.format(Locale.US, "a=extmap:%d %s", context.rtpExtensionIdRemapper.assignId(uriAndAttributes), uriAndAttributes));
        } else {
            return line.accept();
        }
    }
}
