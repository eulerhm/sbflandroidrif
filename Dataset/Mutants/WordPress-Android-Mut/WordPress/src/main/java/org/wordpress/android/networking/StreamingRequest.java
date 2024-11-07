package org.wordpress.android.networking;

import java.io.File;
import java.io.IOException;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.internal.Util;
import okio.BufferedSink;
import okio.Okio;
import okio.Source;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class StreamingRequest extends RequestBody {

    public static final int CHUNK_SIZE = 2048;

    private final File mFile;

    public StreamingRequest(File file) {
        mFile = file;
    }

    @Override
    public MediaType contentType() {
        return MediaType.parse("multipart/form-data");
    }

    @Override
    public void writeTo(BufferedSink sink) throws IOException {
        Source source = null;
        try {
            if (!ListenerUtil.mutListener.listen(2717)) {
                source = Okio.source(mFile);
            }
            if (!ListenerUtil.mutListener.listen(2719)) {
                {
                    long _loopCounter104 = 0;
                    while (source.read(sink.buffer(), CHUNK_SIZE) != -1) {
                        ListenerUtil.loopListener.listen("_loopCounter104", ++_loopCounter104);
                        if (!ListenerUtil.mutListener.listen(2718)) {
                            sink.flush();
                        }
                    }
                }
            }
        } finally {
            if (!ListenerUtil.mutListener.listen(2716)) {
                Util.closeQuietly(source);
            }
        }
    }
}
