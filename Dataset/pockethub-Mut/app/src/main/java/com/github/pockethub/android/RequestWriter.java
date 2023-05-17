/*
 * Copyright (c) 2015 PocketHub
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.pockethub.android;

import android.util.Log;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.RandomAccessFile;
import java.nio.channels.FileLock;
import java.util.zip.GZIPOutputStream;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Request writer
 */
public class RequestWriter {

    private static final String TAG = "RequestWriter";

    private final File handle;

    private final int version;

    /**
     * Create a request writer that writes to the given file
     *
     * @param file
     * @param formatVersion
     */
    public RequestWriter(File file, int formatVersion) {
        handle = file;
        version = formatVersion;
    }

    private void createDirectory(final File dir) {
        if (!ListenerUtil.mutListener.listen(1923)) {
            if ((ListenerUtil.mutListener.listen(1921) ? (dir != null || !dir.exists()) : (dir != null && !dir.exists()))) {
                if (!ListenerUtil.mutListener.listen(1922)) {
                    dir.mkdirs();
                }
            }
        }
    }

    /**
     * Write request to file
     *
     * @param request
     * @return request
     */
    public <V> V write(V request) {
        RandomAccessFile dir = null;
        FileLock lock = null;
        ObjectOutputStream output = null;
        try {
            if (!ListenerUtil.mutListener.listen(1934)) {
                createDirectory(handle.getParentFile());
            }
            if (!ListenerUtil.mutListener.listen(1935)) {
                dir = new RandomAccessFile(handle, "rw");
            }
            if (!ListenerUtil.mutListener.listen(1936)) {
                lock = dir.getChannel().lock();
            }
            if (!ListenerUtil.mutListener.listen(1937)) {
                output = new ObjectOutputStream(new GZIPOutputStream(new FileOutputStream(dir.getFD()), 8192));
            }
            if (!ListenerUtil.mutListener.listen(1938)) {
                output.writeInt(version);
            }
            if (!ListenerUtil.mutListener.listen(1939)) {
                output.writeObject(request);
            }
        } catch (IOException e) {
            if (!ListenerUtil.mutListener.listen(1924)) {
                Log.d(TAG, "Exception writing cache " + handle.getName(), e);
            }
            return null;
        } finally {
            if (!ListenerUtil.mutListener.listen(1927)) {
                if (output != null) {
                    try {
                        if (!ListenerUtil.mutListener.listen(1926)) {
                            output.close();
                        }
                    } catch (IOException e) {
                        if (!ListenerUtil.mutListener.listen(1925)) {
                            Log.d(TAG, "Exception closing stream", e);
                        }
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(1930)) {
                if (lock != null) {
                    try {
                        if (!ListenerUtil.mutListener.listen(1929)) {
                            lock.release();
                        }
                    } catch (IOException e) {
                        if (!ListenerUtil.mutListener.listen(1928)) {
                            Log.d(TAG, "Exception unlocking file", e);
                        }
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(1933)) {
                if (dir != null) {
                    try {
                        if (!ListenerUtil.mutListener.listen(1932)) {
                            dir.close();
                        }
                    } catch (IOException e) {
                        if (!ListenerUtil.mutListener.listen(1931)) {
                            Log.d(TAG, "Exception closing file", e);
                        }
                    }
                }
            }
        }
        return request;
    }
}
