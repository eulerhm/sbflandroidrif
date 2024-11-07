/**
 * *************************************************************************************
 *  Copyright (c) 2019 Mike Hardy <github@mikehardy.net>                                 *
 *                                                                                       *
 *  This program is free software; you can redistribute it and/or modify it under        *
 *  the terms of the GNU General Public License as published by the Free Software        *
 *  Foundation; either version 3 of the License, or (at your option) any later           *
 *  version.                                                                             *
 *                                                                                       *
 *  This program is distributed in the hope that it will be useful, but WITHOUT ANY      *
 *  WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A      *
 *  PARTICULAR PURPOSE. See the GNU General Public License for more details.             *
 *                                                                                       *
 *  You should have received a copy of the GNU General Public License along with         *
 *  this program.  If not, see <http://www.gnu.org/licenses/>.                           *
 * **************************************************************************************
 */
package com.ichi2.libanki.sync;

import java.io.File;
import java.io.IOException;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.internal.Util;
import okio.BufferedSink;
import okio.Okio;
import okio.Source;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

// without a separate object. I believe we will have to move to API21+ for that to be possible
public class CountingFileRequestBody extends RequestBody {

    // okio.Segment.SIZE
    private static final int SEGMENT_SIZE = 2048;

    private final File file;

    private final ProgressListener listener;

    private final String contentType;

    public CountingFileRequestBody(File file, String contentType, ProgressListener listener) {
        this.file = file;
        this.contentType = contentType;
        this.listener = listener;
    }

    @Override
    public long contentLength() {
        return file.length();
    }

    @Override
    public MediaType contentType() {
        return MediaType.parse(contentType);
    }

    @Override
    public void writeTo(BufferedSink sink) throws IOException {
        Source source = null;
        try {
            if (!ListenerUtil.mutListener.listen(19735)) {
                source = Okio.source(file);
            }
            long read;
            if (!ListenerUtil.mutListener.listen(19743)) {
                {
                    long _loopCounter389 = 0;
                    while ((ListenerUtil.mutListener.listen(19742) ? ((read = source.read(sink.buffer(), SEGMENT_SIZE)) >= -1) : (ListenerUtil.mutListener.listen(19741) ? ((read = source.read(sink.buffer(), SEGMENT_SIZE)) <= -1) : (ListenerUtil.mutListener.listen(19740) ? ((read = source.read(sink.buffer(), SEGMENT_SIZE)) > -1) : (ListenerUtil.mutListener.listen(19739) ? ((read = source.read(sink.buffer(), SEGMENT_SIZE)) < -1) : (ListenerUtil.mutListener.listen(19738) ? ((read = source.read(sink.buffer(), SEGMENT_SIZE)) == -1) : ((read = source.read(sink.buffer(), SEGMENT_SIZE)) != -1))))))) {
                        ListenerUtil.loopListener.listen("_loopCounter389", ++_loopCounter389);
                        if (!ListenerUtil.mutListener.listen(19736)) {
                            sink.flush();
                        }
                        if (!ListenerUtil.mutListener.listen(19737)) {
                            this.listener.transferred(read);
                        }
                    }
                }
            }
        } finally {
            if (!ListenerUtil.mutListener.listen(19734)) {
                Util.closeQuietly(source);
            }
        }
    }

    public interface ProgressListener {

        void transferred(long num);
    }
}
