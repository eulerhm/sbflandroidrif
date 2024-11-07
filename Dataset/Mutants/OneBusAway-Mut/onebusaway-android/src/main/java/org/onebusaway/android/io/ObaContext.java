/*
 * Copyright (C) 2012 Paul Watts (paulcwatts@gmail.com)
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

import org.onebusaway.android.R;
import org.onebusaway.android.app.Application;
import org.onebusaway.android.io.elements.ObaRegion;
import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import java.net.MalformedURLException;
import java.net.URL;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class ObaContext {

    private static final String TAG = "ObaContext";

    private String mApiKey = "v1_BktoDJ2gJlu6nLM6LsT9H8IUbWc=cGF1bGN3YXR0c0BnbWFpbC5jb20=";

    private int mAppVer = 0;

    private String mAppUid = null;

    private ObaConnectionFactory mConnectionFactory = ObaDefaultConnectionFactory.getInstance();

    private ObaRegion mRegion;

    public ObaContext() {
    }

    public void setAppInfo(int version, String uuid) {
        if (!ListenerUtil.mutListener.listen(8497)) {
            mAppVer = version;
        }
        if (!ListenerUtil.mutListener.listen(8498)) {
            mAppUid = uuid;
        }
    }

    public void setAppInfo(Uri.Builder builder) {
        if (!ListenerUtil.mutListener.listen(8505)) {
            if ((ListenerUtil.mutListener.listen(8503) ? (mAppVer >= 0) : (ListenerUtil.mutListener.listen(8502) ? (mAppVer <= 0) : (ListenerUtil.mutListener.listen(8501) ? (mAppVer > 0) : (ListenerUtil.mutListener.listen(8500) ? (mAppVer < 0) : (ListenerUtil.mutListener.listen(8499) ? (mAppVer == 0) : (mAppVer != 0))))))) {
                if (!ListenerUtil.mutListener.listen(8504)) {
                    builder.appendQueryParameter("app_ver", String.valueOf(mAppVer));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(8507)) {
            if (mAppUid != null) {
                if (!ListenerUtil.mutListener.listen(8506)) {
                    builder.appendQueryParameter("app_uid", mAppUid);
                }
            }
        }
    }

    public void setApiKey(String apiKey) {
        if (!ListenerUtil.mutListener.listen(8508)) {
            mApiKey = apiKey;
        }
    }

    public String getApiKey() {
        return mApiKey;
    }

    public void setRegion(ObaRegion region) {
        if (!ListenerUtil.mutListener.listen(8509)) {
            mRegion = region;
        }
    }

    public ObaRegion getRegion() {
        return mRegion;
    }

    /**
     * Connection factory
     */
    public ObaConnectionFactory setConnectionFactory(ObaConnectionFactory factory) {
        ObaConnectionFactory prev = mConnectionFactory;
        if (!ListenerUtil.mutListener.listen(8510)) {
            mConnectionFactory = factory;
        }
        return prev;
    }

    public ObaConnectionFactory getConnectionFactory() {
        return mConnectionFactory;
    }

    public void setBaseUrl(Context context, Uri.Builder builder) {
        // If there is a custom preference, then use that.
        String serverName = Application.get().getCustomApiUrl();
        if (!ListenerUtil.mutListener.listen(8516)) {
            if ((ListenerUtil.mutListener.listen(8511) ? (!TextUtils.isEmpty(serverName) && mRegion != null) : (!TextUtils.isEmpty(serverName) || mRegion != null))) {
                if (!ListenerUtil.mutListener.listen(8515)) {
                    setUrl(context, builder, serverName);
                }
            } else {
                String fallBack = "api.pugetsound.onebusaway.org";
                if (!ListenerUtil.mutListener.listen(8512)) {
                    Log.e(TAG, "Accessing default fallback '" + fallBack + "' ...this is wrong!!");
                }
                if (!ListenerUtil.mutListener.listen(8513)) {
                    // Current fallback for existing users?
                    builder.scheme("http");
                }
                if (!ListenerUtil.mutListener.listen(8514)) {
                    builder.authority(fallBack);
                }
            }
        }
    }

    public void setBaseOtpUrl(Context context, Uri.Builder builder) {
        // Use the custom OTP url if vailable
        String otpBaseUrl = Application.get().getCustomOtpApiUrl();
        if (!ListenerUtil.mutListener.listen(8520)) {
            if (TextUtils.isEmpty(otpBaseUrl)) {
                if (!ListenerUtil.mutListener.listen(8518)) {
                    // Use the current region OTP base URL
                    otpBaseUrl = Application.get().getCurrentRegion().getOtpBaseUrl();
                }
                if (!ListenerUtil.mutListener.listen(8519)) {
                    Log.d(TAG, "Using default region OTP API URL '" + otpBaseUrl + "'.");
                }
            } else {
                if (!ListenerUtil.mutListener.listen(8517)) {
                    Log.d(TAG, "Using custom OTP API URL set by user '" + otpBaseUrl + "'.");
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(8521)) {
            setUrl(context, builder, otpBaseUrl);
        }
    }

    /**
     * Set a URL to the Uri.Builder. This method was created to avoid repeating the same logic for
     * 'setBasetOtpUrl' and 'setBaseUrl' methods.
     *
     * @param context used to get android resources
     * @param builder the Uri.Builder to set the url
     * @param serverName the url to be used.
     */
    private void setUrl(Context context, Uri.Builder builder, String serverName) {
        Uri baseUrl = null;
        if (!ListenerUtil.mutListener.listen(8527)) {
            if (!TextUtils.isEmpty(serverName)) {
                if (!ListenerUtil.mutListener.listen(8524)) {
                    // and all OTP requests should extend RequestBase
                    Log.d(TAG, "Using API URL '" + serverName + "'.");
                }
                try {
                    // URI.parse() doesn't tell us if the scheme is missing, so use URL() instead (#126)
                    URL url = new URL(serverName);
                } catch (MalformedURLException e) {
                    if (!ListenerUtil.mutListener.listen(8525)) {
                        // Assume HTTPS scheme, since without a scheme the Uri won't parse the authority
                        serverName = context.getString(R.string.https_prefix) + serverName;
                    }
                }
                if (!ListenerUtil.mutListener.listen(8526)) {
                    baseUrl = Uri.parse(serverName);
                }
            } else if (mRegion != null) {
                if (!ListenerUtil.mutListener.listen(8522)) {
                    Log.d(TAG, "Using region base URL '" + mRegion.getObaBaseUrl() + "'.");
                }
                if (!ListenerUtil.mutListener.listen(8523)) {
                    baseUrl = Uri.parse(mRegion.getObaBaseUrl());
                }
            }
        }
        // Copy partial path (if one exists) from the base URL
        Uri.Builder path = new Uri.Builder();
        if (!ListenerUtil.mutListener.listen(8528)) {
            path.encodedPath(baseUrl.getEncodedPath());
        }
        if (!ListenerUtil.mutListener.listen(8529)) {
            // Then, tack on the rest of the REST API method path from the Uri.Builder that was passed in
            path.appendEncodedPath(builder.build().getPath());
        }
        if (!ListenerUtil.mutListener.listen(8530)) {
            // Finally, overwrite builder that was passed in with the full URL
            builder.scheme(baseUrl.getScheme());
        }
        if (!ListenerUtil.mutListener.listen(8531)) {
            builder.encodedAuthority(baseUrl.getEncodedAuthority());
        }
        if (!ListenerUtil.mutListener.listen(8532)) {
            builder.encodedPath(path.build().getEncodedPath());
        }
    }

    @Override
    public ObaContext clone() {
        ObaContext result = new ObaContext();
        if (!ListenerUtil.mutListener.listen(8533)) {
            result.setApiKey(mApiKey);
        }
        if (!ListenerUtil.mutListener.listen(8534)) {
            result.setAppInfo(mAppVer, mAppUid);
        }
        if (!ListenerUtil.mutListener.listen(8535)) {
            result.setConnectionFactory(mConnectionFactory);
        }
        return result;
    }
}
