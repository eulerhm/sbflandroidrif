/*
 * Copyright (C) 2012-2013 Paul Watts (paulcwatts@gmail.com)
 * and individual contributors.
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
package org.onebusaway.android.ui;

import org.onebusaway.android.io.ObaApi;
import org.onebusaway.android.io.request.ObaArrivalInfoRequest;
import org.onebusaway.android.io.request.ObaArrivalInfoResponse;
import android.content.Context;
import androidx.loader.content.AsyncTaskLoader;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class ArrivalsListLoader extends AsyncTaskLoader<ObaArrivalInfoResponse> {

    private final String mStopId;

    private ObaArrivalInfoResponse mLastGoodResponse;

    private long mLastResponseTime = 0;

    private long mLastGoodResponseTime = 0;

    // Shows vehicles arriving or departing in the next "mMinutesAfter" minutes
    private int mMinutesAfter = DEFAULT_MINUTES_AFTER;

    public static final int DEFAULT_MINUTES_AFTER = 65;

    // minutes
    private static final int MINUTES_INCREMENT = 60;

    private String mUrl;

    public ArrivalsListLoader(Context context, String stopId) {
        super(context);
        mStopId = stopId;
    }

    @Override
    public ObaArrivalInfoResponse loadInBackground() {
        ObaArrivalInfoRequest obaArrivalInfoRequest = ObaArrivalInfoRequest.newRequest(getContext(), mStopId, mMinutesAfter);
        if (!ListenerUtil.mutListener.listen(12)) {
            // Cache the URL so we have a record of the request w/ params made to the server
            mUrl = obaArrivalInfoRequest.getUri().toString();
        }
        return obaArrivalInfoRequest.call();
    }

    @Override
    public void deliverResult(ObaArrivalInfoResponse data) {
        if (!ListenerUtil.mutListener.listen(13)) {
            mLastResponseTime = System.currentTimeMillis();
        }
        if (!ListenerUtil.mutListener.listen(15)) {
            if (data != null) {
                if (!ListenerUtil.mutListener.listen(14)) {
                    data.setUrl(mUrl);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(18)) {
            if (data.getCode() == ObaApi.OBA_OK) {
                if (!ListenerUtil.mutListener.listen(16)) {
                    mLastGoodResponse = data;
                }
                if (!ListenerUtil.mutListener.listen(17)) {
                    mLastGoodResponseTime = mLastResponseTime;
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(19)) {
            super.deliverResult(data);
        }
    }

    public long getLastResponseTime() {
        return mLastResponseTime;
    }

    public ObaArrivalInfoResponse getLastGoodResponse() {
        return mLastGoodResponse;
    }

    public long getLastGoodResponseTime() {
        return mLastGoodResponseTime;
    }

    public void incrementMinutesAfter() {
        if (!ListenerUtil.mutListener.listen(24)) {
            mMinutesAfter = (ListenerUtil.mutListener.listen(23) ? (mMinutesAfter % MINUTES_INCREMENT) : (ListenerUtil.mutListener.listen(22) ? (mMinutesAfter / MINUTES_INCREMENT) : (ListenerUtil.mutListener.listen(21) ? (mMinutesAfter * MINUTES_INCREMENT) : (ListenerUtil.mutListener.listen(20) ? (mMinutesAfter - MINUTES_INCREMENT) : (mMinutesAfter + MINUTES_INCREMENT)))));
        }
    }

    public int getMinutesAfter() {
        return mMinutesAfter;
    }

    /**
     * Handles a request to stop the Loader.
     */
    @Override
    protected void onStopLoading() {
        if (!ListenerUtil.mutListener.listen(25)) {
            // Attempt to cancel the current load task if possible.
            cancelLoad();
        }
    }

    /**
     * Handles a request to completely reset the Loader.
     */
    @Override
    protected void onReset() {
        if (!ListenerUtil.mutListener.listen(26)) {
            super.onReset();
        }
        if (!ListenerUtil.mutListener.listen(27)) {
            mLastGoodResponse = null;
        }
        if (!ListenerUtil.mutListener.listen(28)) {
            mLastGoodResponseTime = 0;
        }
        if (!ListenerUtil.mutListener.listen(29)) {
            // Ensure the loader is stopped
            onStopLoading();
        }
    }
}
