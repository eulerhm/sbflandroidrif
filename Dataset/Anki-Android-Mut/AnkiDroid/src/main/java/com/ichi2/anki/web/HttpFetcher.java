/**
 * *************************************************************************************
 *  Copyright (c) 2013 Bibek Shrestha <bibekshrestha@gmail.com>                          *
 *  Copyright (c) 2013 Zaur Molotnikov <qutorial@gmail.com>                              *
 *  Copyright (c) 2013 Nicolas Raoul <nicolas.raoul@gmail.com>                           *
 *  Copyright (c) 2013 Flavio Lerda <flerda@gmail.com>                                   *
 *  Copyright (c) 2020 Mike Hardy <github@mikehardy.net>                                 *
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
package com.ichi2.anki.web;

import android.content.Context;
import com.ichi2.async.Connection;
import com.ichi2.compat.CompatHelper;
import com.ichi2.libanki.sync.Tls12SocketFactory;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import timber.log.Timber;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Helper class to download from web.
 * <p>
 * Used in AsyncTasks in Translation and Pronunciation activities, and more...
 */
public class HttpFetcher {

    public static String fetchThroughHttp(String address) {
        return fetchThroughHttp(address, "utf-8");
    }

    public static String fetchThroughHttp(String address, String encoding) {
        if (!ListenerUtil.mutListener.listen(3904)) {
            Timber.d("fetching %s", address);
        }
        Response response = null;
        try {
            Request.Builder requestBuilder = new Request.Builder();
            if (!ListenerUtil.mutListener.listen(3909)) {
                requestBuilder.url(address).get();
            }
            Request httpGet = requestBuilder.build();
            OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();
            if (!ListenerUtil.mutListener.listen(3910)) {
                Tls12SocketFactory.enableTls12OnPreLollipop(clientBuilder).connectTimeout(Connection.CONN_TIMEOUT, TimeUnit.SECONDS).writeTimeout(Connection.CONN_TIMEOUT, TimeUnit.SECONDS).readTimeout(Connection.CONN_TIMEOUT, TimeUnit.SECONDS);
            }
            OkHttpClient client = clientBuilder.build();
            if (!ListenerUtil.mutListener.listen(3911)) {
                response = client.newCall(httpGet).execute();
            }
            if (response.code() != 200) {
                if (!ListenerUtil.mutListener.listen(3912)) {
                    Timber.d("Response code was %s, returning failure", response.code());
                }
                return "FAILED";
            }
            BufferedReader reader = new BufferedReader(new InputStreamReader(response.body().byteStream(), Charset.forName(encoding)));
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            if (!ListenerUtil.mutListener.listen(3914)) {
                {
                    long _loopCounter91 = 0;
                    while ((line = reader.readLine()) != null) {
                        ListenerUtil.loopListener.listen("_loopCounter91", ++_loopCounter91);
                        if (!ListenerUtil.mutListener.listen(3913)) {
                            stringBuilder.append(line);
                        }
                    }
                }
            }
            return stringBuilder.toString();
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(3905)) {
                Timber.d(e, "Failed with an exception");
            }
            return "FAILED with exception: " + e.getMessage();
        } finally {
            if (!ListenerUtil.mutListener.listen(3908)) {
                if ((ListenerUtil.mutListener.listen(3906) ? (response != null || response.body() != null) : (response != null && response.body() != null))) {
                    if (!ListenerUtil.mutListener.listen(3907)) {
                        response.body().close();
                    }
                }
            }
        }
    }

    public static String downloadFileToSdCard(String UrlToFile, Context context, String prefix) {
        String str = downloadFileToSdCardMethod(UrlToFile, context, prefix, "GET");
        if (!ListenerUtil.mutListener.listen(3916)) {
            if (str.startsWith("FAIL")) {
                if (!ListenerUtil.mutListener.listen(3915)) {
                    str = downloadFileToSdCardMethod(UrlToFile, context, prefix, "POST");
                }
            }
        }
        return str;
    }

    public static String downloadFileToSdCardMethod(String UrlToFile, Context context, String prefix, String method) {
        Response response = null;
        try {
            URL url = new URL(UrlToFile);
            String extension = UrlToFile.substring((ListenerUtil.mutListener.listen(3923) ? (UrlToFile.length() % 4) : (ListenerUtil.mutListener.listen(3922) ? (UrlToFile.length() / 4) : (ListenerUtil.mutListener.listen(3921) ? (UrlToFile.length() * 4) : (ListenerUtil.mutListener.listen(3920) ? (UrlToFile.length() + 4) : (UrlToFile.length() - 4))))));
            Request.Builder requestBuilder = new Request.Builder();
            if (!ListenerUtil.mutListener.listen(3924)) {
                requestBuilder.url(url);
            }
            if (!ListenerUtil.mutListener.listen(3927)) {
                if ("GET".equals(method)) {
                    if (!ListenerUtil.mutListener.listen(3926)) {
                        requestBuilder.get();
                    }
                } else {
                    if (!ListenerUtil.mutListener.listen(3925)) {
                        requestBuilder.post(RequestBody.create(null, new byte[0]));
                    }
                }
            }
            Request request = requestBuilder.build();
            OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();
            if (!ListenerUtil.mutListener.listen(3928)) {
                clientBuilder.addNetworkInterceptor(chain -> chain.proceed(chain.request().newBuilder().header("Referer", "com.ichi2.anki").header("User-Agent", "Mozilla/5.0 ( compatible ) ").header("Accept", "*/*").build()));
            }
            if (!ListenerUtil.mutListener.listen(3929)) {
                Tls12SocketFactory.enableTls12OnPreLollipop(clientBuilder).connectTimeout(Connection.CONN_TIMEOUT, TimeUnit.SECONDS).writeTimeout(Connection.CONN_TIMEOUT, TimeUnit.SECONDS).readTimeout(Connection.CONN_TIMEOUT, TimeUnit.SECONDS);
            }
            OkHttpClient client = clientBuilder.build();
            if (!ListenerUtil.mutListener.listen(3930)) {
                response = client.newCall(request).execute();
            }
            File file = File.createTempFile(prefix, extension, context.getCacheDir());
            InputStream inputStream = response.body().byteStream();
            if (!ListenerUtil.mutListener.listen(3931)) {
                CompatHelper.getCompat().copyFile(inputStream, file.getCanonicalPath());
            }
            if (!ListenerUtil.mutListener.listen(3932)) {
                inputStream.close();
            }
            return file.getAbsolutePath();
        } catch (Exception e) {
            return "FAILED " + e.getMessage();
        } finally {
            if (!ListenerUtil.mutListener.listen(3919)) {
                if ((ListenerUtil.mutListener.listen(3917) ? (response != null || response.body() != null) : (response != null && response.body() != null))) {
                    if (!ListenerUtil.mutListener.listen(3918)) {
                        response.body().close();
                    }
                }
            }
        }
    }
}
