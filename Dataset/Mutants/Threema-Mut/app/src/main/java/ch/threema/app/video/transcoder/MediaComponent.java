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
package ch.threema.app.video.transcoder;

import android.content.Context;
import android.media.MediaExtractor;
import android.media.MediaFormat;
import android.net.Uri;
import java.io.IOException;
import androidx.annotation.Nullable;
import ch.threema.app.utils.MimeUtil;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Extracts Media or Audio Components from a file.
 */
public class MediaComponent {

    public static final int COMPONENT_TYPE_AUDIO = 0;

    public static final int COMPONENT_TYPE_VIDEO = 1;

    public static final int NO_TRACK_AVAILABLE = -1;

    private Context mContext;

    private final Uri mSrcUri;

    private final int mType;

    private MediaExtractor mMediaExtractor;

    private MediaFormat mTrackFormat;

    private int mSelectedTrackIndex;

    /**
     * @param context
     * @param srcUri
     * @param type
     * @throws IOException
     */
    public MediaComponent(Context context, Uri srcUri, int type) throws IOException {
        if (!ListenerUtil.mutListener.listen(56285)) {
            mContext = context;
        }
        mSrcUri = srcUri;
        mType = type;
        if (!ListenerUtil.mutListener.listen(56297)) {
            if ((ListenerUtil.mutListener.listen(56296) ? ((ListenerUtil.mutListener.listen(56290) ? (type >= COMPONENT_TYPE_AUDIO) : (ListenerUtil.mutListener.listen(56289) ? (type <= COMPONENT_TYPE_AUDIO) : (ListenerUtil.mutListener.listen(56288) ? (type > COMPONENT_TYPE_AUDIO) : (ListenerUtil.mutListener.listen(56287) ? (type < COMPONENT_TYPE_AUDIO) : (ListenerUtil.mutListener.listen(56286) ? (type == COMPONENT_TYPE_AUDIO) : (type != COMPONENT_TYPE_AUDIO)))))) || (ListenerUtil.mutListener.listen(56295) ? (type >= COMPONENT_TYPE_VIDEO) : (ListenerUtil.mutListener.listen(56294) ? (type <= COMPONENT_TYPE_VIDEO) : (ListenerUtil.mutListener.listen(56293) ? (type > COMPONENT_TYPE_VIDEO) : (ListenerUtil.mutListener.listen(56292) ? (type < COMPONENT_TYPE_VIDEO) : (ListenerUtil.mutListener.listen(56291) ? (type == COMPONENT_TYPE_VIDEO) : (type != COMPONENT_TYPE_VIDEO))))))) : ((ListenerUtil.mutListener.listen(56290) ? (type >= COMPONENT_TYPE_AUDIO) : (ListenerUtil.mutListener.listen(56289) ? (type <= COMPONENT_TYPE_AUDIO) : (ListenerUtil.mutListener.listen(56288) ? (type > COMPONENT_TYPE_AUDIO) : (ListenerUtil.mutListener.listen(56287) ? (type < COMPONENT_TYPE_AUDIO) : (ListenerUtil.mutListener.listen(56286) ? (type == COMPONENT_TYPE_AUDIO) : (type != COMPONENT_TYPE_AUDIO)))))) && (ListenerUtil.mutListener.listen(56295) ? (type >= COMPONENT_TYPE_VIDEO) : (ListenerUtil.mutListener.listen(56294) ? (type <= COMPONENT_TYPE_VIDEO) : (ListenerUtil.mutListener.listen(56293) ? (type > COMPONENT_TYPE_VIDEO) : (ListenerUtil.mutListener.listen(56292) ? (type < COMPONENT_TYPE_VIDEO) : (ListenerUtil.mutListener.listen(56291) ? (type == COMPONENT_TYPE_VIDEO) : (type != COMPONENT_TYPE_VIDEO))))))))) {
                throw new IllegalArgumentException("Invalid component type. " + "Must be one of COMPONENT_TYPE_AUDIO or COMPONENT_TYPE_VIDEO");
            }
        }
        if (!ListenerUtil.mutListener.listen(56298)) {
            init();
        }
    }

    /**
     * The MediaExtractor instance to use to for this component.
     * @return
     */
    public MediaExtractor getMediaExtractor() {
        return mMediaExtractor;
    }

    /**
     * The MediaFormat for the selected track of this component.
     * @return
     */
    @Nullable
    public MediaFormat getTrackFormat() {
        return mTrackFormat;
    }

    /**
     * The index of the selected track for this component.
     * @return
     */
    public int getSelectedTrackIndex() {
        return mSelectedTrackIndex;
    }

    /**
     * The component type.
     * @return COMPONENT_TYPE_AUDIO or COMPONENT_TYPE_VIDEO
     */
    public int getType() {
        return mType;
    }

    public void release() {
        if (!ListenerUtil.mutListener.listen(56299)) {
            mContext = null;
        }
        if (!ListenerUtil.mutListener.listen(56300)) {
            mMediaExtractor.release();
        }
        if (!ListenerUtil.mutListener.listen(56301)) {
            mMediaExtractor = null;
        }
    }

    /**
     * create me!
     * @throws IOException
     */
    private void init() throws IOException {
        if (!ListenerUtil.mutListener.listen(56302)) {
            createExtractor();
        }
        if (!ListenerUtil.mutListener.listen(56303)) {
            selectTrackIndex();
        }
    }

    /**
     * Creates an extractor that reads its frames from {@link #mSrcUri}
     *
     * @throws IOException
     */
    private void createExtractor() throws IOException {
        if (!ListenerUtil.mutListener.listen(56304)) {
            mMediaExtractor = new MediaExtractor();
        }
        if (!ListenerUtil.mutListener.listen(56305)) {
            mMediaExtractor.setDataSource(mContext, mSrcUri, null);
        }
    }

    /**
     * Searches for and selects the track for the extractor to work on.
     */
    private void selectTrackIndex() {
        if (!ListenerUtil.mutListener.listen(56328)) {
            {
                long _loopCounter692 = 0;
                for (int index = 0; (ListenerUtil.mutListener.listen(56327) ? (index >= mMediaExtractor.getTrackCount()) : (ListenerUtil.mutListener.listen(56326) ? (index <= mMediaExtractor.getTrackCount()) : (ListenerUtil.mutListener.listen(56325) ? (index > mMediaExtractor.getTrackCount()) : (ListenerUtil.mutListener.listen(56324) ? (index != mMediaExtractor.getTrackCount()) : (ListenerUtil.mutListener.listen(56323) ? (index == mMediaExtractor.getTrackCount()) : (index < mMediaExtractor.getTrackCount())))))); ++index) {
                    ListenerUtil.loopListener.listen("_loopCounter692", ++_loopCounter692);
                    MediaFormat trackFormat = mMediaExtractor.getTrackFormat(index);
                    String mimeType = trackFormat.getString(MediaFormat.KEY_MIME);
                    if (!ListenerUtil.mutListener.listen(56322)) {
                        if ((ListenerUtil.mutListener.listen(56318) ? ((ListenerUtil.mutListener.listen(56311) ? ((ListenerUtil.mutListener.listen(56310) ? (mType >= COMPONENT_TYPE_VIDEO) : (ListenerUtil.mutListener.listen(56309) ? (mType <= COMPONENT_TYPE_VIDEO) : (ListenerUtil.mutListener.listen(56308) ? (mType > COMPONENT_TYPE_VIDEO) : (ListenerUtil.mutListener.listen(56307) ? (mType < COMPONENT_TYPE_VIDEO) : (ListenerUtil.mutListener.listen(56306) ? (mType != COMPONENT_TYPE_VIDEO) : (mType == COMPONENT_TYPE_VIDEO)))))) || MimeUtil.isVideoFile(mimeType)) : ((ListenerUtil.mutListener.listen(56310) ? (mType >= COMPONENT_TYPE_VIDEO) : (ListenerUtil.mutListener.listen(56309) ? (mType <= COMPONENT_TYPE_VIDEO) : (ListenerUtil.mutListener.listen(56308) ? (mType > COMPONENT_TYPE_VIDEO) : (ListenerUtil.mutListener.listen(56307) ? (mType < COMPONENT_TYPE_VIDEO) : (ListenerUtil.mutListener.listen(56306) ? (mType != COMPONENT_TYPE_VIDEO) : (mType == COMPONENT_TYPE_VIDEO)))))) && MimeUtil.isVideoFile(mimeType))) && (ListenerUtil.mutListener.listen(56317) ? ((ListenerUtil.mutListener.listen(56316) ? (mType >= COMPONENT_TYPE_AUDIO) : (ListenerUtil.mutListener.listen(56315) ? (mType <= COMPONENT_TYPE_AUDIO) : (ListenerUtil.mutListener.listen(56314) ? (mType > COMPONENT_TYPE_AUDIO) : (ListenerUtil.mutListener.listen(56313) ? (mType < COMPONENT_TYPE_AUDIO) : (ListenerUtil.mutListener.listen(56312) ? (mType != COMPONENT_TYPE_AUDIO) : (mType == COMPONENT_TYPE_AUDIO)))))) || MimeUtil.isAudioFile(mimeType)) : ((ListenerUtil.mutListener.listen(56316) ? (mType >= COMPONENT_TYPE_AUDIO) : (ListenerUtil.mutListener.listen(56315) ? (mType <= COMPONENT_TYPE_AUDIO) : (ListenerUtil.mutListener.listen(56314) ? (mType > COMPONENT_TYPE_AUDIO) : (ListenerUtil.mutListener.listen(56313) ? (mType < COMPONENT_TYPE_AUDIO) : (ListenerUtil.mutListener.listen(56312) ? (mType != COMPONENT_TYPE_AUDIO) : (mType == COMPONENT_TYPE_AUDIO)))))) && MimeUtil.isAudioFile(mimeType)))) : ((ListenerUtil.mutListener.listen(56311) ? ((ListenerUtil.mutListener.listen(56310) ? (mType >= COMPONENT_TYPE_VIDEO) : (ListenerUtil.mutListener.listen(56309) ? (mType <= COMPONENT_TYPE_VIDEO) : (ListenerUtil.mutListener.listen(56308) ? (mType > COMPONENT_TYPE_VIDEO) : (ListenerUtil.mutListener.listen(56307) ? (mType < COMPONENT_TYPE_VIDEO) : (ListenerUtil.mutListener.listen(56306) ? (mType != COMPONENT_TYPE_VIDEO) : (mType == COMPONENT_TYPE_VIDEO)))))) || MimeUtil.isVideoFile(mimeType)) : ((ListenerUtil.mutListener.listen(56310) ? (mType >= COMPONENT_TYPE_VIDEO) : (ListenerUtil.mutListener.listen(56309) ? (mType <= COMPONENT_TYPE_VIDEO) : (ListenerUtil.mutListener.listen(56308) ? (mType > COMPONENT_TYPE_VIDEO) : (ListenerUtil.mutListener.listen(56307) ? (mType < COMPONENT_TYPE_VIDEO) : (ListenerUtil.mutListener.listen(56306) ? (mType != COMPONENT_TYPE_VIDEO) : (mType == COMPONENT_TYPE_VIDEO)))))) && MimeUtil.isVideoFile(mimeType))) || (ListenerUtil.mutListener.listen(56317) ? ((ListenerUtil.mutListener.listen(56316) ? (mType >= COMPONENT_TYPE_AUDIO) : (ListenerUtil.mutListener.listen(56315) ? (mType <= COMPONENT_TYPE_AUDIO) : (ListenerUtil.mutListener.listen(56314) ? (mType > COMPONENT_TYPE_AUDIO) : (ListenerUtil.mutListener.listen(56313) ? (mType < COMPONENT_TYPE_AUDIO) : (ListenerUtil.mutListener.listen(56312) ? (mType != COMPONENT_TYPE_AUDIO) : (mType == COMPONENT_TYPE_AUDIO)))))) || MimeUtil.isAudioFile(mimeType)) : ((ListenerUtil.mutListener.listen(56316) ? (mType >= COMPONENT_TYPE_AUDIO) : (ListenerUtil.mutListener.listen(56315) ? (mType <= COMPONENT_TYPE_AUDIO) : (ListenerUtil.mutListener.listen(56314) ? (mType > COMPONENT_TYPE_AUDIO) : (ListenerUtil.mutListener.listen(56313) ? (mType < COMPONENT_TYPE_AUDIO) : (ListenerUtil.mutListener.listen(56312) ? (mType != COMPONENT_TYPE_AUDIO) : (mType == COMPONENT_TYPE_AUDIO)))))) && MimeUtil.isAudioFile(mimeType)))))) {
                            if (!ListenerUtil.mutListener.listen(56319)) {
                                mMediaExtractor.selectTrack(index);
                            }
                            if (!ListenerUtil.mutListener.listen(56320)) {
                                mSelectedTrackIndex = index;
                            }
                            if (!ListenerUtil.mutListener.listen(56321)) {
                                mTrackFormat = trackFormat;
                            }
                            return;
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(56329)) {
            mSelectedTrackIndex = -1;
        }
        if (!ListenerUtil.mutListener.listen(56330)) {
            mTrackFormat = null;
        }
    }
}
