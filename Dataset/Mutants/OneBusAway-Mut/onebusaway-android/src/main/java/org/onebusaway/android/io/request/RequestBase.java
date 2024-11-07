/*
 * Copyright (C) 2010-2016 Paul Watts (paulcwatts@gmail.com)
 * University of South Florida (cagricetin@mail.usf.edu)
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
package org.onebusaway.android.io.request;

import org.onebusaway.android.io.ObaApi;
import org.onebusaway.android.io.ObaConnection;
import org.onebusaway.android.io.ObaContext;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.util.Log;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;
import java.net.HttpURLConnection;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * The base class for Oba requests.
 *
 * @author Paul Watts (paulcwatts@gmail.com)
 */
public class RequestBase {

    private static final String TAG = "RequestBase";

    protected final Uri mUri;

    protected final String mPostData;

    protected RequestBase(Uri uri) {
        mUri = uri;
        mPostData = null;
    }

    protected RequestBase(Uri uri, String postData) {
        mUri = uri;
        mPostData = postData;
    }

    public Uri getUri() {
        return mUri;
    }

    public static class BuilderBase {

        protected static final String BASE_PATH = "api/where";

        protected final Uri.Builder mBuilder;

        protected ObaContext mObaContext;

        protected Context mContext;

        protected boolean mIsOtp = false;

        protected BuilderBase(Context context, String path) {
            this(context, null, path);
        }

        protected BuilderBase(Context context, ObaContext obaContext, String path) {
            if (!ListenerUtil.mutListener.listen(8445)) {
                mContext = context;
            }
            if (!ListenerUtil.mutListener.listen(8446)) {
                mObaContext = obaContext;
            }
            mBuilder = new Uri.Builder();
            if (!ListenerUtil.mutListener.listen(8447)) {
                mBuilder.path(path);
            }
        }

        protected static String getPathWithId(String pathElement, String id) {
            StringBuilder builder = new StringBuilder(BASE_PATH);
            if (!ListenerUtil.mutListener.listen(8448)) {
                builder.append(pathElement);
            }
            if (!ListenerUtil.mutListener.listen(8449)) {
                builder.append(Uri.encode(id));
            }
            if (!ListenerUtil.mutListener.listen(8450)) {
                builder.append(".json");
            }
            return builder.toString();
        }

        protected Uri buildUri() {
            ObaContext context = (mObaContext != null) ? mObaContext : ObaApi.getDefaultContext();
            if (!ListenerUtil.mutListener.listen(8456)) {
                if (mIsOtp) {
                    if (!ListenerUtil.mutListener.listen(8455)) {
                        context.setBaseOtpUrl(mContext, mBuilder);
                    }
                } else {
                    if (!ListenerUtil.mutListener.listen(8451)) {
                        context.setBaseUrl(mContext, mBuilder);
                    }
                    if (!ListenerUtil.mutListener.listen(8452)) {
                        context.setAppInfo(mBuilder);
                    }
                    if (!ListenerUtil.mutListener.listen(8453)) {
                        mBuilder.appendQueryParameter("version", "2");
                    }
                    if (!ListenerUtil.mutListener.listen(8454)) {
                        mBuilder.appendQueryParameter("key", context.getApiKey());
                    }
                }
            }
            return mBuilder.build();
        }

        public ObaContext getObaContext() {
            if (!ListenerUtil.mutListener.listen(8458)) {
                if (mObaContext == null) {
                    if (!ListenerUtil.mutListener.listen(8457)) {
                        mObaContext = ObaApi.getDefaultContext().clone();
                    }
                }
            }
            return mObaContext;
        }

        protected void setIsOtp(Boolean isOtp) {
            if (!ListenerUtil.mutListener.listen(8459)) {
                mIsOtp = isOtp;
            }
        }
    }

    /**
     * Subclass for BuilderBase that can handle post data as well.
     *
     * @author paulw
     */
    public static class PostBuilderBase extends BuilderBase {

        protected final Uri.Builder mPostData;

        protected PostBuilderBase(Context context, String path) {
            super(context, path);
            mPostData = new Uri.Builder();
        }

        public String buildPostData() {
            return mPostData.build().getEncodedQuery();
        }
    }

    protected <T> T call(Class<T> cls) {
        ObaApi.SerializationHandler handler = ObaApi.getSerializer(cls);
        ObaConnection conn = null;
        try {
            if (!ListenerUtil.mutListener.listen(8464)) {
                conn = ObaApi.getDefaultContext().getConnectionFactory().newConnection(mUri);
            }
            Reader reader;
            if (mPostData != null) {
                reader = conn.post(mPostData);
            } else {
                if ((ListenerUtil.mutListener.listen(8469) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.GINGERBREAD) : (ListenerUtil.mutListener.listen(8468) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.GINGERBREAD) : (ListenerUtil.mutListener.listen(8467) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.GINGERBREAD) : (ListenerUtil.mutListener.listen(8466) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.GINGERBREAD) : (ListenerUtil.mutListener.listen(8465) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.GINGERBREAD) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD))))))) {
                    // before you read the response???
                    int responseCode = conn.getResponseCode();
                    if ((ListenerUtil.mutListener.listen(8474) ? (responseCode >= HttpURLConnection.HTTP_OK) : (ListenerUtil.mutListener.listen(8473) ? (responseCode <= HttpURLConnection.HTTP_OK) : (ListenerUtil.mutListener.listen(8472) ? (responseCode > HttpURLConnection.HTTP_OK) : (ListenerUtil.mutListener.listen(8471) ? (responseCode < HttpURLConnection.HTTP_OK) : (ListenerUtil.mutListener.listen(8470) ? (responseCode == HttpURLConnection.HTTP_OK) : (responseCode != HttpURLConnection.HTTP_OK))))))) {
                        return handler.createFromError(cls, responseCode, "");
                    }
                }
                reader = conn.get();
            }
            T t = handler.deserialize(reader, cls);
            if (!ListenerUtil.mutListener.listen(8476)) {
                if (t == null) {
                    if (!ListenerUtil.mutListener.listen(8475)) {
                        t = handler.createFromError(cls, ObaApi.OBA_INTERNAL_ERROR, "Json error");
                    }
                }
            }
            return t;
        } catch (FileNotFoundException e) {
            if (!ListenerUtil.mutListener.listen(8460)) {
                Log.e(TAG, e.toString());
            }
            return handler.createFromError(cls, ObaApi.OBA_NOT_FOUND, e.toString());
        } catch (IOException e) {
            if (!ListenerUtil.mutListener.listen(8461)) {
                Log.e(TAG, e.toString());
            }
            return handler.createFromError(cls, ObaApi.OBA_IO_EXCEPTION, e.toString());
        } finally {
            if (!ListenerUtil.mutListener.listen(8463)) {
                if (conn != null) {
                    if (!ListenerUtil.mutListener.listen(8462)) {
                        conn.disconnect();
                    }
                }
            }
        }
    }
}
