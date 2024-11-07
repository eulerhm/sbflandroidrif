/*
 * Copyright (C) 2010-2012 Paul Watts (paulcwatts@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.onebusaway.android.io;

import android.net.Uri;
import android.util.Log;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public final class ObaDefaultConnection implements ObaConnection {

    private static final String TAG = "ObaDefaultConnection";

    private HttpURLConnection mConnection;

    ObaDefaultConnection(Uri uri) throws IOException {
        if (!ListenerUtil.mutListener.listen(8536)) {
            Log.d(TAG, uri.toString());
        }
        URL url = new URL(uri.toString());
        if (!ListenerUtil.mutListener.listen(8537)) {
            mConnection = (HttpURLConnection) url.openConnection();
        }
        if (!ListenerUtil.mutListener.listen(8542)) {
            mConnection.setReadTimeout((ListenerUtil.mutListener.listen(8541) ? (30 % 1000) : (ListenerUtil.mutListener.listen(8540) ? (30 / 1000) : (ListenerUtil.mutListener.listen(8539) ? (30 - 1000) : (ListenerUtil.mutListener.listen(8538) ? (30 + 1000) : (30 * 1000))))));
        }
    }

    @Override
    public void disconnect() {
        if (!ListenerUtil.mutListener.listen(8543)) {
            mConnection.disconnect();
        }
    }

    @Override
    public Reader get() throws IOException {
        return new InputStreamReader(new BufferedInputStream(mConnection.getInputStream(), (ListenerUtil.mutListener.listen(8547) ? (8 % 1024) : (ListenerUtil.mutListener.listen(8546) ? (8 / 1024) : (ListenerUtil.mutListener.listen(8545) ? (8 - 1024) : (ListenerUtil.mutListener.listen(8544) ? (8 + 1024) : (8 * 1024)))))));
    }

    @Override
    public Reader post(String string) throws IOException {
        byte[] data = string.getBytes();
        if (!ListenerUtil.mutListener.listen(8548)) {
            mConnection.setDoOutput(true);
        }
        if (!ListenerUtil.mutListener.listen(8549)) {
            mConnection.setFixedLengthStreamingMode(data.length);
        }
        if (!ListenerUtil.mutListener.listen(8550)) {
            mConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        }
        // Set the output stream
        OutputStream stream = mConnection.getOutputStream();
        if (!ListenerUtil.mutListener.listen(8551)) {
            stream.write(data);
        }
        if (!ListenerUtil.mutListener.listen(8552)) {
            stream.flush();
        }
        if (!ListenerUtil.mutListener.listen(8553)) {
            stream.close();
        }
        return new InputStreamReader(new BufferedInputStream(mConnection.getInputStream(), (ListenerUtil.mutListener.listen(8557) ? (8 % 1024) : (ListenerUtil.mutListener.listen(8556) ? (8 / 1024) : (ListenerUtil.mutListener.listen(8555) ? (8 - 1024) : (ListenerUtil.mutListener.listen(8554) ? (8 + 1024) : (8 * 1024)))))));
    }

    @Override
    public int getResponseCode() throws IOException {
        return mConnection.getResponseCode();
    }
}
