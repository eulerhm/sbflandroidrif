/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
 * Copyright (c) 2019-2021 Threema GmbH
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
package ch.threema.app.video.transcoder.audio;

import android.media.MediaCodec;
import android.media.MediaCodecInfo;
import android.media.MediaCodecList;
import android.media.MediaExtractor;
import android.media.MediaFormat;
import android.os.Build;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.nio.ByteBuffer;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import ch.threema.app.video.transcoder.VideoTranscoder;
import ch.threema.app.video.transcoder.MediaComponent;
import java8.util.Optional;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Transcode an audio track to another format.
 *
 * Based on https://github.com/groupme/android-video-kit
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
// we use various deprecated methods to support older versions.
@SuppressWarnings("deprecation")
public class AudioFormatTranscoder extends AbstractAudioTranscoder {

    private static final Logger logger = LoggerFactory.getLogger(AudioFormatTranscoder.class);

    private static final int TIMEOUT_USEC = VideoTranscoder.TIMEOUT_USEC;

    /**
     *  Requested output format for the transcoder.
     */
    private final int outputAudioBitrate;

    private MediaCodec encoder;

    private MediaCodec decoder;

    /**
     *  Decoder input buffer access for for Android before {@link Build.VERSION_CODES#LOLLIPOP}
     */
    private ByteBuffer[] decoderInputBuffers;

    /**
     *  Decoder output buffer access for for Android before {@link Build.VERSION_CODES#LOLLIPOP}
     */
    private ByteBuffer[] decoderOutputBuffers;

    /**
     *  Encoder input buffer access for for Android before {@link Build.VERSION_CODES#LOLLIPOP}
     */
    private ByteBuffer[] encoderInputBuffers;

    /**
     *  Encoder output buffer access for for Android before {@link Build.VERSION_CODES#LOLLIPOP}
     */
    private ByteBuffer[] encoderOutputBuffers;

    /**
     *  Information about the last decoder output buffer that was made available.
     */
    private MediaCodec.BufferInfo decoderOutputBufferInfo;

    /**
     *  Information about the last encoder output buffer that was made available.
     */
    private MediaCodec.BufferInfo encoderOutputBufferInfo;

    private boolean extractorDone;

    /**
     *  Next decoder output buffer that should be encoded
     */
    @NonNull
    private Optional<Integer> decoderOutputBufferNextIndex = Optional.empty();

    private boolean encoderDone = false;

    private int resendRetryCount = 0;

    /**
     *  Keeps track of the last appended audio time, so that we do not append out-of-order audio.
     */
    private long previousPresentationTime = -1;

    @Override
    public boolean hasPendingIntermediateFrames() {
        return this.decoderOutputBufferNextIndex.isPresent();
    }

    /**
     *  @param component The audio component that should be transcoded
     *  @param stats Transcoder Statistics
     *  @param trimEndTimeMs Trim time from the end in ms (!)
     *  @param outputAudioBitrate Target bitrate for the output audio
     */
    public AudioFormatTranscoder(AudioComponent component, VideoTranscoder.Stats stats, long trimEndTimeMs, int outputAudioBitrate) {
        super(component, stats, trimEndTimeMs);
        this.outputAudioBitrate = outputAudioBitrate;
    }

    @Override
    public void setup() throws IOException, UnsupportedAudioFormatException {
        if (!ListenerUtil.mutListener.listen(55989)) {
            if (this.getState() != State.INITIAL) {
                throw new IllegalStateException("Setup may only be called on initialization");
            }
        }
        MediaFormat inputFormat = this.component.getTrackFormat();
        if (!ListenerUtil.mutListener.listen(55990)) {
            if (inputFormat == null) {
                throw new UnsupportedAudioFormatException("No input audio format could be detected");
            }
        }
        if (!ListenerUtil.mutListener.listen(55991)) {
            if (!inputFormat.containsKey(MediaFormat.KEY_SAMPLE_RATE)) {
                // Observed on SM-A530F
                throw new UnsupportedAudioFormatException("Audio format not properly supported by device manufacturer");
            }
        }
        if (!ListenerUtil.mutListener.listen(55992)) {
            // Setup De/Encoder
            this.setupAudioDecoder(inputFormat);
        }
        if (!ListenerUtil.mutListener.listen(55993)) {
            this.setupAudioEncoder(inputFormat);
        }
        if (!ListenerUtil.mutListener.listen(55994)) {
            this.setState(State.DETECTING_INPUT_FORMAT);
        }
    }

    private void setupAudioDecoder(@NonNull MediaFormat inputFormat) throws IOException, UnsupportedAudioFormatException {
        if (!ListenerUtil.mutListener.listen(55995)) {
            logger.debug("audio decoder: set sample rate to {}", inputFormat.getInteger(MediaFormat.KEY_SAMPLE_RATE));
        }
        if (!ListenerUtil.mutListener.listen(55999)) {
            if ((ListenerUtil.mutListener.listen(55996) ? (logger.isDebugEnabled() || inputFormat.containsKey(MediaFormat.KEY_BIT_RATE)) : (logger.isDebugEnabled() && inputFormat.containsKey(MediaFormat.KEY_BIT_RATE)))) {
                if (!ListenerUtil.mutListener.listen(55998)) {
                    logger.debug("audio decoder: set bit rate to {}", inputFormat.getInteger(MediaFormat.KEY_BIT_RATE));
                }
            } else {
                if (!ListenerUtil.mutListener.listen(55997)) {
                    logger.debug("audio decoder: decoding unknown bit rate");
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(56007)) {
            if ((ListenerUtil.mutListener.listen(56004) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(56003) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(56002) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(56001) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(56000) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.LOLLIPOP) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP))))))) {
                if (!ListenerUtil.mutListener.listen(56006)) {
                    this.decoder = this.getDecoderFor(inputFormat);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(56005)) {
                    this.decoder = MediaCodec.createDecoderByType(VideoTranscoder.getMimeTypeFor(inputFormat));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(56008)) {
            this.decoder.configure(inputFormat, null, null, 0);
        }
        if (!ListenerUtil.mutListener.listen(56009)) {
            this.decoder.start();
        }
        if (!ListenerUtil.mutListener.listen(56017)) {
            if ((ListenerUtil.mutListener.listen(56014) ? (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(56013) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(56012) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(56011) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(56010) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.LOLLIPOP) : (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP))))))) {
                if (!ListenerUtil.mutListener.listen(56015)) {
                    this.decoderInputBuffers = this.decoder.getInputBuffers();
                }
                if (!ListenerUtil.mutListener.listen(56016)) {
                    this.decoderOutputBuffers = this.decoder.getOutputBuffers();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(56018)) {
            this.decoderOutputBufferInfo = new MediaCodec.BufferInfo();
        }
    }

    private void setupAudioEncoder(MediaFormat inputFormat) throws IOException {
        int sampleRate = inputFormat.getInteger(MediaFormat.KEY_SAMPLE_RATE);
        int channelCount = inputFormat.getInteger(MediaFormat.KEY_CHANNEL_COUNT);
        if (!ListenerUtil.mutListener.listen(56019)) {
            this.outputFormat = MediaFormat.createAudioFormat(VideoTranscoder.Defaults.OUTPUT_AUDIO_MIME_TYPE, sampleRate, channelCount);
        }
        if (!ListenerUtil.mutListener.listen(56020)) {
            this.outputFormat.setInteger(MediaFormat.KEY_BIT_RATE, this.outputAudioBitrate);
        }
        if (!ListenerUtil.mutListener.listen(56021)) {
            this.outputFormat.setInteger(MediaFormat.KEY_AAC_PROFILE, VideoTranscoder.Defaults.OUTPUT_AUDIO_AAC_PROFILE);
        }
        MediaCodecInfo codecInfo = VideoTranscoder.selectCodec(VideoTranscoder.Defaults.OUTPUT_AUDIO_MIME_TYPE);
        if (!ListenerUtil.mutListener.listen(56022)) {
            logger.debug("audio encoder: set sample rate to {}", this.outputFormat.getInteger(MediaFormat.KEY_SAMPLE_RATE));
        }
        if (!ListenerUtil.mutListener.listen(56023)) {
            logger.debug("audio encoder: set bit rate to {}", this.outputFormat.getInteger(MediaFormat.KEY_BIT_RATE));
        }
        if (!ListenerUtil.mutListener.listen(56026)) {
            if (this.encoder == null) {
                if (!ListenerUtil.mutListener.listen(56025)) {
                    this.encoder = MediaCodec.createByCodecName(codecInfo.getName());
                }
            } else {
                if (!ListenerUtil.mutListener.listen(56024)) {
                    this.encoder.stop();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(56027)) {
            this.encoder.configure(this.outputFormat, null, null, MediaCodec.CONFIGURE_FLAG_ENCODE);
        }
        if (!ListenerUtil.mutListener.listen(56028)) {
            this.encoder.start();
        }
        if (!ListenerUtil.mutListener.listen(56036)) {
            if ((ListenerUtil.mutListener.listen(56033) ? (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(56032) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(56031) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(56030) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(56029) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.LOLLIPOP) : (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP))))))) {
                if (!ListenerUtil.mutListener.listen(56034)) {
                    this.encoderInputBuffers = this.encoder.getInputBuffers();
                }
                if (!ListenerUtil.mutListener.listen(56035)) {
                    this.encoderOutputBuffers = this.encoder.getOutputBuffers();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(56037)) {
            this.encoderOutputBufferInfo = new MediaCodec.BufferInfo();
        }
    }

    /**
     *  Detect the most optimal decoder. This method is only available with
     *  Android SDK >= {@link Build.VERSION_CODES#LOLLIPOP}
     *
     *  @throws UnsupportedAudioFormatException if there is no decoder for this format available.
     *  @throws IOException If the codec creation failed.
     */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private MediaCodec getDecoderFor(MediaFormat inputFormat) throws UnsupportedAudioFormatException, IOException {
        final MediaCodecList mediaCodecList = new MediaCodecList(MediaCodecList.ALL_CODECS);
        if (!ListenerUtil.mutListener.listen(56039)) {
            if (Build.VERSION.SDK_INT == Build.VERSION_CODES.LOLLIPOP) {
                if (!ListenerUtil.mutListener.listen(56038)) {
                    // Workaround for Framework bug, see {@link MediaCodecList#findDecoderForFormat)
                    inputFormat.setString(MediaFormat.KEY_FRAME_RATE, null);
                }
            }
        }
        @Nullable
        final String codec = mediaCodecList.findDecoderForFormat(inputFormat);
        if (!ListenerUtil.mutListener.listen(56041)) {
            if (codec == null) {
                if (!ListenerUtil.mutListener.listen(56040)) {
                    logger.warn("Could not find a codec for input format {}", inputFormat);
                }
                throw new UnsupportedAudioFormatException(inputFormat);
            }
        }
        return MediaCodec.createByCodecName(codec);
    }

    @Override
    public void step() throws UnsupportedAudioFormatException {
        if (!ListenerUtil.mutListener.listen(56043)) {
            if ((ListenerUtil.mutListener.listen(56042) ? (this.getState() == State.INITIAL && this.getState() == State.DONE) : (this.getState() == State.INITIAL || this.getState() == State.DONE))) {
                throw new IllegalStateException(String.format("Calling an audio transcoding step is not allowed in state %s", this.getState()));
            }
        }
        if (!ListenerUtil.mutListener.listen(56045)) {
            if (this.getState() == State.WAITING_ON_MUXER) {
                if (!ListenerUtil.mutListener.listen(56044)) {
                    logger.debug("Skipping transcoding step, waiting for muxer to be injected.");
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(56047)) {
            // ready to mux the frames.
            if (!this.extractorDone) {
                if (!ListenerUtil.mutListener.listen(56046)) {
                    this.extractorDone = this.pipeExtractorFrameToDecoder(this.decoder, this.decoderInputBuffers, this.component);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(56049)) {
            // Poll output frames from the audio decoder.
            if (this.decoderOutputBufferNextIndex.isEmpty()) {
                if (!ListenerUtil.mutListener.listen(56048)) {
                    this.pollAudioFromDecoder(this.decoderOutputBufferInfo);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(56051)) {
            // Feed the pending audio buffer to the audio encoder
            if (this.decoderOutputBufferNextIndex.isPresent()) {
                if (!ListenerUtil.mutListener.listen(56050)) {
                    this.pipeDecoderFrameToEncoder(this.decoderOutputBufferInfo);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(56055)) {
            // Poll frames from audio encoder and send them to the muxer
            if (!this.encoderDone) {
                if (!ListenerUtil.mutListener.listen(56052)) {
                    this.encoderDone = this.pipeEncoderFrameToMuxer(this.encoderOutputBufferInfo);
                }
                if (!ListenerUtil.mutListener.listen(56054)) {
                    if (this.encoderDone) {
                        if (!ListenerUtil.mutListener.listen(56053)) {
                            this.setState(State.DONE);
                        }
                    }
                }
            }
        }
    }

    /**
     *  Extract and feed to decoder.
     *
     *  @return Finished. True when it extracts the last frame.
     */
    private boolean pipeExtractorFrameToDecoder(MediaCodec decoder, ByteBuffer[] buffers, MediaComponent component) {
        final int decoderInputBufferIndex = decoder.dequeueInputBuffer(TIMEOUT_USEC);
        if (!ListenerUtil.mutListener.listen(56057)) {
            if (decoderInputBufferIndex == MediaCodec.INFO_TRY_AGAIN_LATER) {
                if (!ListenerUtil.mutListener.listen(56056)) {
                    logger.debug("no audio decoder input buffer");
                }
                return false;
            }
        }
        if (!ListenerUtil.mutListener.listen(56058)) {
            logger.trace("audio extractor: returned input buffer: {}", decoderInputBufferIndex);
        }
        MediaExtractor extractor = component.getMediaExtractor();
        int chunkSize = extractor.readSampleData((ListenerUtil.mutListener.listen(56063) ? (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(56062) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(56061) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(56060) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(56059) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.LOLLIPOP) : (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP)))))) ? buffers[decoderInputBufferIndex] : decoder.getInputBuffer(decoderInputBufferIndex), 0);
        long sampleTime = extractor.getSampleTime();
        if (!ListenerUtil.mutListener.listen(56064)) {
            logger.trace("audio extractor: returned buffer of chunkSize {}", chunkSize);
        }
        if (!ListenerUtil.mutListener.listen(56065)) {
            logger.trace("audio extractor: returned buffer for sampleTime {}", sampleTime);
        }
        if (!ListenerUtil.mutListener.listen(56079)) {
            if ((ListenerUtil.mutListener.listen(56076) ? ((ListenerUtil.mutListener.listen(56070) ? (this.trimEndTimeUs >= 0) : (ListenerUtil.mutListener.listen(56069) ? (this.trimEndTimeUs <= 0) : (ListenerUtil.mutListener.listen(56068) ? (this.trimEndTimeUs < 0) : (ListenerUtil.mutListener.listen(56067) ? (this.trimEndTimeUs != 0) : (ListenerUtil.mutListener.listen(56066) ? (this.trimEndTimeUs == 0) : (this.trimEndTimeUs > 0)))))) || (ListenerUtil.mutListener.listen(56075) ? (sampleTime >= this.trimEndTimeUs) : (ListenerUtil.mutListener.listen(56074) ? (sampleTime <= this.trimEndTimeUs) : (ListenerUtil.mutListener.listen(56073) ? (sampleTime < this.trimEndTimeUs) : (ListenerUtil.mutListener.listen(56072) ? (sampleTime != this.trimEndTimeUs) : (ListenerUtil.mutListener.listen(56071) ? (sampleTime == this.trimEndTimeUs) : (sampleTime > this.trimEndTimeUs))))))) : ((ListenerUtil.mutListener.listen(56070) ? (this.trimEndTimeUs >= 0) : (ListenerUtil.mutListener.listen(56069) ? (this.trimEndTimeUs <= 0) : (ListenerUtil.mutListener.listen(56068) ? (this.trimEndTimeUs < 0) : (ListenerUtil.mutListener.listen(56067) ? (this.trimEndTimeUs != 0) : (ListenerUtil.mutListener.listen(56066) ? (this.trimEndTimeUs == 0) : (this.trimEndTimeUs > 0)))))) && (ListenerUtil.mutListener.listen(56075) ? (sampleTime >= this.trimEndTimeUs) : (ListenerUtil.mutListener.listen(56074) ? (sampleTime <= this.trimEndTimeUs) : (ListenerUtil.mutListener.listen(56073) ? (sampleTime < this.trimEndTimeUs) : (ListenerUtil.mutListener.listen(56072) ? (sampleTime != this.trimEndTimeUs) : (ListenerUtil.mutListener.listen(56071) ? (sampleTime == this.trimEndTimeUs) : (sampleTime > this.trimEndTimeUs))))))))) {
                if (!ListenerUtil.mutListener.listen(56077)) {
                    logger.debug("audio extractor: The current sample is over the trim time. Lets stop.");
                }
                if (!ListenerUtil.mutListener.listen(56078)) {
                    decoder.queueInputBuffer(decoderInputBufferIndex, 0, 0, 0, MediaCodec.BUFFER_FLAG_END_OF_STREAM);
                }
                return true;
            }
        }
        if (!ListenerUtil.mutListener.listen(56087)) {
            if ((ListenerUtil.mutListener.listen(56084) ? (chunkSize <= 0) : (ListenerUtil.mutListener.listen(56083) ? (chunkSize > 0) : (ListenerUtil.mutListener.listen(56082) ? (chunkSize < 0) : (ListenerUtil.mutListener.listen(56081) ? (chunkSize != 0) : (ListenerUtil.mutListener.listen(56080) ? (chunkSize == 0) : (chunkSize >= 0))))))) {
                if (!ListenerUtil.mutListener.listen(56085)) {
                    decoder.queueInputBuffer(decoderInputBufferIndex, 0, chunkSize, sampleTime, extractor.getSampleFlags());
                }
                if (!ListenerUtil.mutListener.listen(56086)) {
                    this.stats.incrementExtractedFrameCount(component);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(56098)) {
            if (!extractor.advance()) {
                if (!ListenerUtil.mutListener.listen(56088)) {
                    logger.debug("audio extractor: EOS");
                }
                try {
                    if (!ListenerUtil.mutListener.listen(56097)) {
                        decoder.queueInputBuffer(decoderInputBufferIndex, 0, 0, 0, MediaCodec.BUFFER_FLAG_END_OF_STREAM);
                    }
                } catch (Exception e) {
                    if (!ListenerUtil.mutListener.listen(56089)) {
                        // BUFFER_FLAG_END_OF_STREAM is set on non-empty buffers.
                        this.resendRetryCount++;
                    }
                    if (!ListenerUtil.mutListener.listen(56096)) {
                        if ((ListenerUtil.mutListener.listen(56094) ? (this.resendRetryCount >= 5) : (ListenerUtil.mutListener.listen(56093) ? (this.resendRetryCount <= 5) : (ListenerUtil.mutListener.listen(56092) ? (this.resendRetryCount > 5) : (ListenerUtil.mutListener.listen(56091) ? (this.resendRetryCount != 5) : (ListenerUtil.mutListener.listen(56090) ? (this.resendRetryCount == 5) : (this.resendRetryCount < 5))))))) {
                            return this.pipeExtractorFrameToDecoder(decoder, buffers, component);
                        } else {
                            if (!ListenerUtil.mutListener.listen(56095)) {
                                this.resendRetryCount = 0;
                            }
                            throw e;
                        }
                    }
                }
                return true;
            }
        }
        return false;
    }

    private void pollAudioFromDecoder(MediaCodec.BufferInfo audioDecoderOutputBufferInfo) throws UnsupportedAudioFormatException {
        final int decoderOutputBufferIndex;
        try {
            decoderOutputBufferIndex = this.decoder.dequeueOutputBuffer(audioDecoderOutputBufferInfo, TIMEOUT_USEC);
        } catch (IllegalStateException exception) {
            if (!ListenerUtil.mutListener.listen(56099)) {
                // the codec.
                logger.warn("Decoder input buffer could not be dequeued.");
            }
            throw new UnsupportedAudioFormatException("Decoder error: " + exception.getMessage(), exception);
        }
        if (!ListenerUtil.mutListener.listen(56101)) {
            if (decoderOutputBufferIndex == MediaCodec.INFO_TRY_AGAIN_LATER) {
                if (!ListenerUtil.mutListener.listen(56100)) {
                    logger.debug("audio decoder: no output buffer");
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(56110)) {
            if (decoderOutputBufferIndex == MediaCodec.INFO_OUTPUT_BUFFERS_CHANGED) {
                if (!ListenerUtil.mutListener.listen(56102)) {
                    logger.debug("audio decoder: output buffers changed");
                }
                if (!ListenerUtil.mutListener.listen(56109)) {
                    if ((ListenerUtil.mutListener.listen(56107) ? (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(56106) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(56105) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(56104) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(56103) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.LOLLIPOP) : (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP))))))) {
                        if (!ListenerUtil.mutListener.listen(56108)) {
                            this.decoderOutputBuffers = this.decoder.getOutputBuffers();
                        }
                    }
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(56115)) {
            if (decoderOutputBufferIndex == MediaCodec.INFO_OUTPUT_FORMAT_CHANGED) {
                MediaFormat decoderOutputFormat = this.decoder.getOutputFormat();
                if (!ListenerUtil.mutListener.listen(56111)) {
                    logger.debug("audio decoder: output format changed: {}", decoderOutputFormat);
                }
                try {
                    if (!ListenerUtil.mutListener.listen(56113)) {
                        this.setupAudioEncoder(decoderOutputFormat);
                    }
                    if (!ListenerUtil.mutListener.listen(56114)) {
                        this.setState(State.DETECTING_OUTPUT_FORMAT);
                    }
                } catch (IOException e) {
                    if (!ListenerUtil.mutListener.listen(56112)) {
                        logger.error("Reconfiguring encoder media format failed");
                    }
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(56116)) {
            logger.trace("audio decoder: returned output buffer: {}", decoderOutputBufferIndex);
        }
        if (!ListenerUtil.mutListener.listen(56117)) {
            logger.trace("audio decoder: returned buffer of size {}", audioDecoderOutputBufferInfo.size);
        }
        if (!ListenerUtil.mutListener.listen(56120)) {
            if ((audioDecoderOutputBufferInfo.flags & MediaCodec.BUFFER_FLAG_CODEC_CONFIG) != 0) {
                if (!ListenerUtil.mutListener.listen(56118)) {
                    logger.debug("audio decoder: codec config buffer");
                }
                if (!ListenerUtil.mutListener.listen(56119)) {
                    this.decoder.releaseOutputBuffer(decoderOutputBufferIndex, false);
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(56121)) {
            logger.trace("audio decoder: returned buffer for time {}", audioDecoderOutputBufferInfo.presentationTimeUs);
        }
        if (!ListenerUtil.mutListener.listen(56122)) {
            logger.trace("audio decoder: output buffer is now pending: {}", decoderOutputBufferIndex);
        }
        if (!ListenerUtil.mutListener.listen(56123)) {
            this.decoderOutputBufferNextIndex = Optional.of(decoderOutputBufferIndex);
        }
        if (!ListenerUtil.mutListener.listen(56124)) {
            this.stats.audioDecodedFrameCount++;
        }
    }

    private void pipeDecoderFrameToEncoder(MediaCodec.BufferInfo audioDecoderOutputBufferInfo) {
        if (!ListenerUtil.mutListener.listen(56125)) {
            logger.trace("audio decoder: attempting to process pending buffer: {}", this.decoderOutputBufferNextIndex.get());
        }
        int encoderInputBufferIndex = this.encoder.dequeueInputBuffer(TIMEOUT_USEC);
        if (!ListenerUtil.mutListener.listen(56127)) {
            if (encoderInputBufferIndex == MediaCodec.INFO_TRY_AGAIN_LATER) {
                if (!ListenerUtil.mutListener.listen(56126)) {
                    logger.debug("no audio encoder input buffer");
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(56128)) {
            logger.trace("audio encoder: returned input buffer: {}", encoderInputBufferIndex);
        }
        ByteBuffer encoderInputBuffer = (ListenerUtil.mutListener.listen(56133) ? (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(56132) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(56131) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(56130) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(56129) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.LOLLIPOP) : (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP)))))) ? this.encoderInputBuffers[encoderInputBufferIndex] : this.encoder.getInputBuffer(encoderInputBufferIndex);
        int chunkSize = Math.min(audioDecoderOutputBufferInfo.size, encoderInputBuffer.capacity());
        long presentationTime = audioDecoderOutputBufferInfo.presentationTimeUs;
        if (!ListenerUtil.mutListener.listen(56134)) {
            logger.trace("audio decoder: processing pending buffer: {}", this.decoderOutputBufferNextIndex.get());
        }
        if (!ListenerUtil.mutListener.listen(56135)) {
            logger.trace("audio decoder: pending buffer of size {}", chunkSize);
        }
        if (!ListenerUtil.mutListener.listen(56136)) {
            logger.trace("audio decoder: pending buffer for time {}", presentationTime);
        }
        if (!ListenerUtil.mutListener.listen(56152)) {
            if ((ListenerUtil.mutListener.listen(56141) ? (chunkSize <= 0) : (ListenerUtil.mutListener.listen(56140) ? (chunkSize > 0) : (ListenerUtil.mutListener.listen(56139) ? (chunkSize < 0) : (ListenerUtil.mutListener.listen(56138) ? (chunkSize != 0) : (ListenerUtil.mutListener.listen(56137) ? (chunkSize == 0) : (chunkSize >= 0))))))) {
                ByteBuffer decoderOutputBuffer = (ListenerUtil.mutListener.listen(56146) ? (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(56145) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(56144) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(56143) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(56142) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.LOLLIPOP) : (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP)))))) ? this.decoderOutputBuffers[this.decoderOutputBufferNextIndex.get()].duplicate() : this.decoder.getOutputBuffer(this.decoderOutputBufferNextIndex.get()).duplicate();
                if (!ListenerUtil.mutListener.listen(56147)) {
                    decoderOutputBuffer.position(audioDecoderOutputBufferInfo.offset);
                }
                if (!ListenerUtil.mutListener.listen(56148)) {
                    decoderOutputBuffer.limit(audioDecoderOutputBufferInfo.offset + chunkSize);
                }
                if (!ListenerUtil.mutListener.listen(56149)) {
                    encoderInputBuffer.position(0);
                }
                if (!ListenerUtil.mutListener.listen(56150)) {
                    encoderInputBuffer.put(decoderOutputBuffer);
                }
                if (!ListenerUtil.mutListener.listen(56151)) {
                    this.encoder.queueInputBuffer(encoderInputBufferIndex, 0, chunkSize, presentationTime, audioDecoderOutputBufferInfo.flags);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(56153)) {
            this.decoder.releaseOutputBuffer(this.decoderOutputBufferNextIndex.get(), false);
        }
        if (!ListenerUtil.mutListener.listen(56154)) {
            this.decoderOutputBufferNextIndex = Optional.empty();
        }
        if (!ListenerUtil.mutListener.listen(56156)) {
            if ((audioDecoderOutputBufferInfo.flags & MediaCodec.BUFFER_FLAG_END_OF_STREAM) != 0) {
                if (!ListenerUtil.mutListener.listen(56155)) {
                    logger.debug("audio decoder: EOS");
                }
            }
        }
    }

    private boolean pipeEncoderFrameToMuxer(MediaCodec.BufferInfo audioEncoderOutputBufferInfo) {
        int encoderOutputBufferIndex = this.encoder.dequeueOutputBuffer(audioEncoderOutputBufferInfo, TIMEOUT_USEC);
        if (!ListenerUtil.mutListener.listen(56158)) {
            if (encoderOutputBufferIndex == MediaCodec.INFO_TRY_AGAIN_LATER) {
                if (!ListenerUtil.mutListener.listen(56157)) {
                    logger.debug("no audio encoder output buffer");
                }
                return false;
            }
        }
        if (!ListenerUtil.mutListener.listen(56167)) {
            if (encoderOutputBufferIndex == MediaCodec.INFO_OUTPUT_BUFFERS_CHANGED) {
                if (!ListenerUtil.mutListener.listen(56159)) {
                    logger.debug("audio encoder: output buffers changed");
                }
                if (!ListenerUtil.mutListener.listen(56166)) {
                    if ((ListenerUtil.mutListener.listen(56164) ? (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(56163) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(56162) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(56161) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(56160) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.LOLLIPOP) : (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP))))))) {
                        if (!ListenerUtil.mutListener.listen(56165)) {
                            this.encoderOutputBuffers = this.encoder.getOutputBuffers();
                        }
                    }
                }
                return false;
            }
        }
        if (!ListenerUtil.mutListener.listen(56174)) {
            if (encoderOutputBufferIndex == MediaCodec.INFO_OUTPUT_FORMAT_CHANGED) {
                if (!ListenerUtil.mutListener.listen(56168)) {
                    if (this.muxer != null) {
                        throw new IllegalStateException("audio encoder format may not be changed after muxer is initialized");
                    }
                }
                if (!ListenerUtil.mutListener.listen(56169)) {
                    this.outputFormat = this.encoder.getOutputFormat();
                }
                if (!ListenerUtil.mutListener.listen(56170)) {
                    logger.debug("audio encoder: output format changed to {}", this.outputFormat);
                }
                if (!ListenerUtil.mutListener.listen(56173)) {
                    if (this.getState() == State.DETECTING_OUTPUT_FORMAT) {
                        if (!ListenerUtil.mutListener.listen(56172)) {
                            this.setState(State.WAITING_ON_MUXER);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(56171)) {
                            logger.debug("audio encoder: preliminary output format change detected, not switching state");
                        }
                    }
                }
                return false;
            }
        }
        if (!ListenerUtil.mutListener.listen(56177)) {
            if ((audioEncoderOutputBufferInfo.flags & MediaCodec.BUFFER_FLAG_CODEC_CONFIG) != 0) {
                if (!ListenerUtil.mutListener.listen(56175)) {
                    logger.debug("audio encoder: codec config buffer");
                }
                if (!ListenerUtil.mutListener.listen(56176)) {
                    // Simply ignore codec config buffers.
                    this.encoder.releaseOutputBuffer(encoderOutputBufferIndex, false);
                }
                return false;
            }
        }
        if (!ListenerUtil.mutListener.listen(56178)) {
            logger.trace("audio encoder: returned output buffer: {}", encoderOutputBufferIndex);
        }
        if (!ListenerUtil.mutListener.listen(56179)) {
            logger.trace("audio encoder: returned buffer of size {}", audioEncoderOutputBufferInfo.size);
        }
        if (!ListenerUtil.mutListener.listen(56180)) {
            logger.trace("audio encoder: returned buffer for time {}", audioEncoderOutputBufferInfo.presentationTimeUs);
        }
        if (!ListenerUtil.mutListener.listen(56195)) {
            if (audioEncoderOutputBufferInfo.size != 0) {
                ByteBuffer encoderOutputBuffer = (ListenerUtil.mutListener.listen(56185) ? (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(56184) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(56183) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(56182) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(56181) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.LOLLIPOP) : (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP)))))) ? this.encoderOutputBuffers[encoderOutputBufferIndex] : this.encoder.getOutputBuffer(encoderOutputBufferIndex);
                if (!ListenerUtil.mutListener.listen(56194)) {
                    if ((ListenerUtil.mutListener.listen(56190) ? (audioEncoderOutputBufferInfo.presentationTimeUs <= this.previousPresentationTime) : (ListenerUtil.mutListener.listen(56189) ? (audioEncoderOutputBufferInfo.presentationTimeUs > this.previousPresentationTime) : (ListenerUtil.mutListener.listen(56188) ? (audioEncoderOutputBufferInfo.presentationTimeUs < this.previousPresentationTime) : (ListenerUtil.mutListener.listen(56187) ? (audioEncoderOutputBufferInfo.presentationTimeUs != this.previousPresentationTime) : (ListenerUtil.mutListener.listen(56186) ? (audioEncoderOutputBufferInfo.presentationTimeUs == this.previousPresentationTime) : (audioEncoderOutputBufferInfo.presentationTimeUs >= this.previousPresentationTime))))))) {
                        if (!ListenerUtil.mutListener.listen(56192)) {
                            this.previousPresentationTime = audioEncoderOutputBufferInfo.presentationTimeUs;
                        }
                        if (!ListenerUtil.mutListener.listen(56193)) {
                            this.muxer.writeSampleData(this.muxerTrack.get(), encoderOutputBuffer, audioEncoderOutputBufferInfo);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(56191)) {
                            // skip old audio, as this only results in quality reduction.
                            logger.debug("audio encoder: presentationTimeUs {} < previousPresentationTime {}", audioEncoderOutputBufferInfo.presentationTimeUs, this.previousPresentationTime);
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(56196)) {
            this.encoder.releaseOutputBuffer(encoderOutputBufferIndex, false);
        }
        if (!ListenerUtil.mutListener.listen(56198)) {
            if ((audioEncoderOutputBufferInfo.flags & MediaCodec.BUFFER_FLAG_END_OF_STREAM) != 0) {
                if (!ListenerUtil.mutListener.listen(56197)) {
                    logger.debug("audio encoder: EOS");
                }
                return true;
            }
        }
        if (!ListenerUtil.mutListener.listen(56199)) {
            this.stats.audioEncodedFrameCount++;
        }
        return false;
    }

    @Override
    public void cleanup() throws Exception {
        if (!ListenerUtil.mutListener.listen(56200)) {
            super.cleanup();
        }
        // Collect root cause exception without aborting cleanup
        Exception firstException = null;
        try {
            if (!ListenerUtil.mutListener.listen(56205)) {
                if (this.decoder != null) {
                    if (!ListenerUtil.mutListener.listen(56203)) {
                        this.decoder.stop();
                    }
                    if (!ListenerUtil.mutListener.listen(56204)) {
                        this.decoder.release();
                    }
                }
            }
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(56201)) {
                logger.error("error while releasing decoder", e);
            }
            if (!ListenerUtil.mutListener.listen(56202)) {
                firstException = e;
            }
        }
        try {
            if (!ListenerUtil.mutListener.listen(56211)) {
                if (this.encoder != null) {
                    if (!ListenerUtil.mutListener.listen(56209)) {
                        this.encoder.stop();
                    }
                    if (!ListenerUtil.mutListener.listen(56210)) {
                        this.encoder.release();
                    }
                }
            }
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(56206)) {
                logger.error("error while releasing encoder", e);
            }
            if (!ListenerUtil.mutListener.listen(56208)) {
                if (firstException == null) {
                    if (!ListenerUtil.mutListener.listen(56207)) {
                        firstException = e;
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(56212)) {
            if (firstException != null) {
                throw firstException;
            }
        }
    }
}
