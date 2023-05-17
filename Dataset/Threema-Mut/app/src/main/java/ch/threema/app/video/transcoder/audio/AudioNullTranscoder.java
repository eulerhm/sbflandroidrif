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
import android.media.MediaExtractor;
import android.media.MediaFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.nio.ByteBuffer;
import ch.threema.app.video.transcoder.VideoTranscoder;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Keep audio input track and return it unchanged to the muxer
 */
public class AudioNullTranscoder extends AbstractAudioTranscoder {

    private static final Logger logger = LoggerFactory.getLogger(AudioNullTranscoder.class);

    /**
     *  Time of the previously muxed sample.
     */
    private long previousSampleTime;

    private final MediaCodec.BufferInfo bufferInfo = new MediaCodec.BufferInfo();

    private ByteBuffer buffer;

    /**
     *  @param component The audio component that should be transcoded
     *  @param stats Transcoder Statistics
     *  @param trimEndTimeMs Trim time from the end in ms (!)
     */
    public AudioNullTranscoder(AudioComponent component, VideoTranscoder.Stats stats, long trimEndTimeMs) {
        super(component, stats, trimEndTimeMs);
    }

    @Override
    public boolean hasPendingIntermediateFrames() {
        // We don't have any intermediate frames which could be pending when done.
        return this.getState() != State.DONE;
    }

    @Override
    public void setup() {
        if (!ListenerUtil.mutListener.listen(56213)) {
            if (this.getState() != State.INITIAL) {
                throw new IllegalStateException("Setup may only be called on initialization");
            }
        }
        if (!ListenerUtil.mutListener.listen(56214)) {
            this.outputFormat = this.component.getTrackFormat();
        }
        if (!ListenerUtil.mutListener.listen(56215)) {
            this.buffer = ByteBuffer.allocate(this.outputFormat.getInteger(MediaFormat.KEY_MAX_INPUT_SIZE));
        }
        if (!ListenerUtil.mutListener.listen(56216)) {
            this.setState(State.WAITING_ON_MUXER);
        }
    }

    @Override
    public void step() {
        if (!ListenerUtil.mutListener.listen(56218)) {
            if ((ListenerUtil.mutListener.listen(56217) ? (this.getState() == State.INITIAL && this.getState() == State.DONE) : (this.getState() == State.INITIAL || this.getState() == State.DONE))) {
                throw new IllegalStateException(String.format("Calling an audio transcoding step is not allowed in state %s", this.getState()));
            }
        }
        if (!ListenerUtil.mutListener.listen(56220)) {
            if (this.getState() == State.WAITING_ON_MUXER) {
                if (!ListenerUtil.mutListener.listen(56219)) {
                    logger.debug("Skipping transcoding step, waiting for muxer to be injected.");
                }
                return;
            }
        }
        MediaExtractor extractor = this.component.getMediaExtractor();
        final int sampleSize = extractor.readSampleData(this.buffer, 0);
        if (!ListenerUtil.mutListener.listen(56221)) {
            this.bufferInfo.set(0, sampleSize, extractor.getSampleTime(), extractor.getSampleFlags());
        }
        if (!ListenerUtil.mutListener.listen(56222)) {
            logger.trace("audio extractor: returned buffer of chunkSize {}", sampleSize);
        }
        if (!ListenerUtil.mutListener.listen(56223)) {
            logger.trace("audio extractor: returned buffer for sampleTime {}", this.bufferInfo.presentationTimeUs);
        }
        if (!ListenerUtil.mutListener.listen(56237)) {
            if ((ListenerUtil.mutListener.listen(56234) ? ((ListenerUtil.mutListener.listen(56228) ? (this.trimEndTimeUs >= 0) : (ListenerUtil.mutListener.listen(56227) ? (this.trimEndTimeUs <= 0) : (ListenerUtil.mutListener.listen(56226) ? (this.trimEndTimeUs < 0) : (ListenerUtil.mutListener.listen(56225) ? (this.trimEndTimeUs != 0) : (ListenerUtil.mutListener.listen(56224) ? (this.trimEndTimeUs == 0) : (this.trimEndTimeUs > 0)))))) || (ListenerUtil.mutListener.listen(56233) ? (this.bufferInfo.presentationTimeUs >= this.trimEndTimeUs) : (ListenerUtil.mutListener.listen(56232) ? (this.bufferInfo.presentationTimeUs <= this.trimEndTimeUs) : (ListenerUtil.mutListener.listen(56231) ? (this.bufferInfo.presentationTimeUs < this.trimEndTimeUs) : (ListenerUtil.mutListener.listen(56230) ? (this.bufferInfo.presentationTimeUs != this.trimEndTimeUs) : (ListenerUtil.mutListener.listen(56229) ? (this.bufferInfo.presentationTimeUs == this.trimEndTimeUs) : (this.bufferInfo.presentationTimeUs > this.trimEndTimeUs))))))) : ((ListenerUtil.mutListener.listen(56228) ? (this.trimEndTimeUs >= 0) : (ListenerUtil.mutListener.listen(56227) ? (this.trimEndTimeUs <= 0) : (ListenerUtil.mutListener.listen(56226) ? (this.trimEndTimeUs < 0) : (ListenerUtil.mutListener.listen(56225) ? (this.trimEndTimeUs != 0) : (ListenerUtil.mutListener.listen(56224) ? (this.trimEndTimeUs == 0) : (this.trimEndTimeUs > 0)))))) && (ListenerUtil.mutListener.listen(56233) ? (this.bufferInfo.presentationTimeUs >= this.trimEndTimeUs) : (ListenerUtil.mutListener.listen(56232) ? (this.bufferInfo.presentationTimeUs <= this.trimEndTimeUs) : (ListenerUtil.mutListener.listen(56231) ? (this.bufferInfo.presentationTimeUs < this.trimEndTimeUs) : (ListenerUtil.mutListener.listen(56230) ? (this.bufferInfo.presentationTimeUs != this.trimEndTimeUs) : (ListenerUtil.mutListener.listen(56229) ? (this.bufferInfo.presentationTimeUs == this.trimEndTimeUs) : (this.bufferInfo.presentationTimeUs > this.trimEndTimeUs))))))))) {
                if (!ListenerUtil.mutListener.listen(56235)) {
                    logger.debug("audio extractor: The current sample is over the trim time. Lets stop.");
                }
                if (!ListenerUtil.mutListener.listen(56236)) {
                    this.setState(State.DONE);
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(56253)) {
            if ((ListenerUtil.mutListener.listen(56242) ? (sampleSize <= 0) : (ListenerUtil.mutListener.listen(56241) ? (sampleSize > 0) : (ListenerUtil.mutListener.listen(56240) ? (sampleSize < 0) : (ListenerUtil.mutListener.listen(56239) ? (sampleSize != 0) : (ListenerUtil.mutListener.listen(56238) ? (sampleSize == 0) : (sampleSize >= 0))))))) {
                if (!ListenerUtil.mutListener.listen(56243)) {
                    this.stats.incrementExtractedFrameCount(this.component);
                }
                if (!ListenerUtil.mutListener.listen(56252)) {
                    if ((ListenerUtil.mutListener.listen(56248) ? (this.bufferInfo.presentationTimeUs <= this.previousSampleTime) : (ListenerUtil.mutListener.listen(56247) ? (this.bufferInfo.presentationTimeUs > this.previousSampleTime) : (ListenerUtil.mutListener.listen(56246) ? (this.bufferInfo.presentationTimeUs < this.previousSampleTime) : (ListenerUtil.mutListener.listen(56245) ? (this.bufferInfo.presentationTimeUs != this.previousSampleTime) : (ListenerUtil.mutListener.listen(56244) ? (this.bufferInfo.presentationTimeUs == this.previousSampleTime) : (this.bufferInfo.presentationTimeUs >= this.previousSampleTime))))))) {
                        if (!ListenerUtil.mutListener.listen(56250)) {
                            this.previousSampleTime = this.bufferInfo.presentationTimeUs;
                        }
                        if (!ListenerUtil.mutListener.listen(56251)) {
                            this.muxer.writeSampleData(this.muxerTrack.get(), this.buffer, this.bufferInfo);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(56249)) {
                            // skip old audio, as this only results in quality reduction.
                            logger.debug("audio muxer: presentationTimeUs {} < previousPresentationTime {}", this.bufferInfo.presentationTimeUs, this.previousSampleTime);
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(56255)) {
            if (!extractor.advance()) {
                if (!ListenerUtil.mutListener.listen(56254)) {
                    this.setState(State.DONE);
                }
            }
        }
    }
}
