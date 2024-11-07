/*
 * Copyright (C) 2011-2014 Paul Watts (paulcwatts@gmail.com),
 * University of South Florida (sjbarbeau@gmail.com), and individual contributors.
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
package org.onebusaway.android.map;

import org.onebusaway.android.util.LocationUtils;
import android.location.Location;
import android.os.Handler;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Because the map object doesn't seem to have callbacks when the map
 * center or zoom is changed, we have our own watcher for it.
 *
 * @author paulw
 */
public class MapWatcher {

    public interface Listener {

        public void onMapCenterChanging();

        public void onMapCenterChanged();

        public void onMapZoomChanging();

        public void onMapZoomChanged();
    }

    private static final int WAIT_TIME = 1000;

    private static final int POLL_TIME = 250;

    private final MapModeController.ObaMapView mObaMapView;

    private final Handler mHandler;

    private final Listener mListener;

    private Location mCurrentCenter;

    private long mCurrentCenterMillis;

    private float mCurrentZoom;

    private long mCurrentZoomMillis;

    private final Runnable mChecker = new Runnable() {

        @Override
        public void run() {
            Location newCenter = mObaMapView.getMapCenterAsLocation();
            float newZoom = mObaMapView.getZoomLevelAsFloat();
            final boolean centerChanged = !LocationUtils.fuzzyEquals(newCenter, mCurrentCenter);
            final boolean zoomChanged = (ListenerUtil.mutListener.listen(10206) ? (newZoom >= mCurrentZoom) : (ListenerUtil.mutListener.listen(10205) ? (newZoom <= mCurrentZoom) : (ListenerUtil.mutListener.listen(10204) ? (newZoom > mCurrentZoom) : (ListenerUtil.mutListener.listen(10203) ? (newZoom < mCurrentZoom) : (ListenerUtil.mutListener.listen(10202) ? (newZoom == mCurrentZoom) : (newZoom != mCurrentZoom))))));
            final long now = System.currentTimeMillis();
            if (!ListenerUtil.mutListener.listen(10227)) {
                if (centerChanged) {
                    if (!ListenerUtil.mutListener.listen(10224)) {
                        mCurrentCenterMillis = now;
                    }
                    if (!ListenerUtil.mutListener.listen(10225)) {
                        mListener.onMapCenterChanging();
                    }
                    if (!ListenerUtil.mutListener.listen(10226)) {
                        mCurrentCenter = newCenter;
                    }
                } else if ((ListenerUtil.mutListener.listen(10221) ? ((ListenerUtil.mutListener.listen(10211) ? (mCurrentCenterMillis >= 0) : (ListenerUtil.mutListener.listen(10210) ? (mCurrentCenterMillis <= 0) : (ListenerUtil.mutListener.listen(10209) ? (mCurrentCenterMillis > 0) : (ListenerUtil.mutListener.listen(10208) ? (mCurrentCenterMillis < 0) : (ListenerUtil.mutListener.listen(10207) ? (mCurrentCenterMillis == 0) : (mCurrentCenterMillis != 0)))))) || (ListenerUtil.mutListener.listen(10220) ? (((ListenerUtil.mutListener.listen(10215) ? (now % mCurrentCenterMillis) : (ListenerUtil.mutListener.listen(10214) ? (now / mCurrentCenterMillis) : (ListenerUtil.mutListener.listen(10213) ? (now * mCurrentCenterMillis) : (ListenerUtil.mutListener.listen(10212) ? (now + mCurrentCenterMillis) : (now - mCurrentCenterMillis)))))) >= WAIT_TIME) : (ListenerUtil.mutListener.listen(10219) ? (((ListenerUtil.mutListener.listen(10215) ? (now % mCurrentCenterMillis) : (ListenerUtil.mutListener.listen(10214) ? (now / mCurrentCenterMillis) : (ListenerUtil.mutListener.listen(10213) ? (now * mCurrentCenterMillis) : (ListenerUtil.mutListener.listen(10212) ? (now + mCurrentCenterMillis) : (now - mCurrentCenterMillis)))))) <= WAIT_TIME) : (ListenerUtil.mutListener.listen(10218) ? (((ListenerUtil.mutListener.listen(10215) ? (now % mCurrentCenterMillis) : (ListenerUtil.mutListener.listen(10214) ? (now / mCurrentCenterMillis) : (ListenerUtil.mutListener.listen(10213) ? (now * mCurrentCenterMillis) : (ListenerUtil.mutListener.listen(10212) ? (now + mCurrentCenterMillis) : (now - mCurrentCenterMillis)))))) < WAIT_TIME) : (ListenerUtil.mutListener.listen(10217) ? (((ListenerUtil.mutListener.listen(10215) ? (now % mCurrentCenterMillis) : (ListenerUtil.mutListener.listen(10214) ? (now / mCurrentCenterMillis) : (ListenerUtil.mutListener.listen(10213) ? (now * mCurrentCenterMillis) : (ListenerUtil.mutListener.listen(10212) ? (now + mCurrentCenterMillis) : (now - mCurrentCenterMillis)))))) != WAIT_TIME) : (ListenerUtil.mutListener.listen(10216) ? (((ListenerUtil.mutListener.listen(10215) ? (now % mCurrentCenterMillis) : (ListenerUtil.mutListener.listen(10214) ? (now / mCurrentCenterMillis) : (ListenerUtil.mutListener.listen(10213) ? (now * mCurrentCenterMillis) : (ListenerUtil.mutListener.listen(10212) ? (now + mCurrentCenterMillis) : (now - mCurrentCenterMillis)))))) == WAIT_TIME) : (((ListenerUtil.mutListener.listen(10215) ? (now % mCurrentCenterMillis) : (ListenerUtil.mutListener.listen(10214) ? (now / mCurrentCenterMillis) : (ListenerUtil.mutListener.listen(10213) ? (now * mCurrentCenterMillis) : (ListenerUtil.mutListener.listen(10212) ? (now + mCurrentCenterMillis) : (now - mCurrentCenterMillis)))))) > WAIT_TIME))))))) : ((ListenerUtil.mutListener.listen(10211) ? (mCurrentCenterMillis >= 0) : (ListenerUtil.mutListener.listen(10210) ? (mCurrentCenterMillis <= 0) : (ListenerUtil.mutListener.listen(10209) ? (mCurrentCenterMillis > 0) : (ListenerUtil.mutListener.listen(10208) ? (mCurrentCenterMillis < 0) : (ListenerUtil.mutListener.listen(10207) ? (mCurrentCenterMillis == 0) : (mCurrentCenterMillis != 0)))))) && (ListenerUtil.mutListener.listen(10220) ? (((ListenerUtil.mutListener.listen(10215) ? (now % mCurrentCenterMillis) : (ListenerUtil.mutListener.listen(10214) ? (now / mCurrentCenterMillis) : (ListenerUtil.mutListener.listen(10213) ? (now * mCurrentCenterMillis) : (ListenerUtil.mutListener.listen(10212) ? (now + mCurrentCenterMillis) : (now - mCurrentCenterMillis)))))) >= WAIT_TIME) : (ListenerUtil.mutListener.listen(10219) ? (((ListenerUtil.mutListener.listen(10215) ? (now % mCurrentCenterMillis) : (ListenerUtil.mutListener.listen(10214) ? (now / mCurrentCenterMillis) : (ListenerUtil.mutListener.listen(10213) ? (now * mCurrentCenterMillis) : (ListenerUtil.mutListener.listen(10212) ? (now + mCurrentCenterMillis) : (now - mCurrentCenterMillis)))))) <= WAIT_TIME) : (ListenerUtil.mutListener.listen(10218) ? (((ListenerUtil.mutListener.listen(10215) ? (now % mCurrentCenterMillis) : (ListenerUtil.mutListener.listen(10214) ? (now / mCurrentCenterMillis) : (ListenerUtil.mutListener.listen(10213) ? (now * mCurrentCenterMillis) : (ListenerUtil.mutListener.listen(10212) ? (now + mCurrentCenterMillis) : (now - mCurrentCenterMillis)))))) < WAIT_TIME) : (ListenerUtil.mutListener.listen(10217) ? (((ListenerUtil.mutListener.listen(10215) ? (now % mCurrentCenterMillis) : (ListenerUtil.mutListener.listen(10214) ? (now / mCurrentCenterMillis) : (ListenerUtil.mutListener.listen(10213) ? (now * mCurrentCenterMillis) : (ListenerUtil.mutListener.listen(10212) ? (now + mCurrentCenterMillis) : (now - mCurrentCenterMillis)))))) != WAIT_TIME) : (ListenerUtil.mutListener.listen(10216) ? (((ListenerUtil.mutListener.listen(10215) ? (now % mCurrentCenterMillis) : (ListenerUtil.mutListener.listen(10214) ? (now / mCurrentCenterMillis) : (ListenerUtil.mutListener.listen(10213) ? (now * mCurrentCenterMillis) : (ListenerUtil.mutListener.listen(10212) ? (now + mCurrentCenterMillis) : (now - mCurrentCenterMillis)))))) == WAIT_TIME) : (((ListenerUtil.mutListener.listen(10215) ? (now % mCurrentCenterMillis) : (ListenerUtil.mutListener.listen(10214) ? (now / mCurrentCenterMillis) : (ListenerUtil.mutListener.listen(10213) ? (now * mCurrentCenterMillis) : (ListenerUtil.mutListener.listen(10212) ? (now + mCurrentCenterMillis) : (now - mCurrentCenterMillis)))))) > WAIT_TIME))))))))) {
                    if (!ListenerUtil.mutListener.listen(10222)) {
                        mListener.onMapCenterChanged();
                    }
                    if (!ListenerUtil.mutListener.listen(10223)) {
                        mCurrentCenterMillis = 0;
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(10248)) {
                if (zoomChanged) {
                    if (!ListenerUtil.mutListener.listen(10245)) {
                        mCurrentZoomMillis = now;
                    }
                    if (!ListenerUtil.mutListener.listen(10246)) {
                        mListener.onMapZoomChanging();
                    }
                    if (!ListenerUtil.mutListener.listen(10247)) {
                        mCurrentZoom = newZoom;
                    }
                } else if ((ListenerUtil.mutListener.listen(10242) ? ((ListenerUtil.mutListener.listen(10232) ? (mCurrentZoomMillis >= 0) : (ListenerUtil.mutListener.listen(10231) ? (mCurrentZoomMillis <= 0) : (ListenerUtil.mutListener.listen(10230) ? (mCurrentZoomMillis > 0) : (ListenerUtil.mutListener.listen(10229) ? (mCurrentZoomMillis < 0) : (ListenerUtil.mutListener.listen(10228) ? (mCurrentZoomMillis == 0) : (mCurrentZoomMillis != 0)))))) || (ListenerUtil.mutListener.listen(10241) ? (((ListenerUtil.mutListener.listen(10236) ? (now % mCurrentZoomMillis) : (ListenerUtil.mutListener.listen(10235) ? (now / mCurrentZoomMillis) : (ListenerUtil.mutListener.listen(10234) ? (now * mCurrentZoomMillis) : (ListenerUtil.mutListener.listen(10233) ? (now + mCurrentZoomMillis) : (now - mCurrentZoomMillis)))))) >= WAIT_TIME) : (ListenerUtil.mutListener.listen(10240) ? (((ListenerUtil.mutListener.listen(10236) ? (now % mCurrentZoomMillis) : (ListenerUtil.mutListener.listen(10235) ? (now / mCurrentZoomMillis) : (ListenerUtil.mutListener.listen(10234) ? (now * mCurrentZoomMillis) : (ListenerUtil.mutListener.listen(10233) ? (now + mCurrentZoomMillis) : (now - mCurrentZoomMillis)))))) <= WAIT_TIME) : (ListenerUtil.mutListener.listen(10239) ? (((ListenerUtil.mutListener.listen(10236) ? (now % mCurrentZoomMillis) : (ListenerUtil.mutListener.listen(10235) ? (now / mCurrentZoomMillis) : (ListenerUtil.mutListener.listen(10234) ? (now * mCurrentZoomMillis) : (ListenerUtil.mutListener.listen(10233) ? (now + mCurrentZoomMillis) : (now - mCurrentZoomMillis)))))) < WAIT_TIME) : (ListenerUtil.mutListener.listen(10238) ? (((ListenerUtil.mutListener.listen(10236) ? (now % mCurrentZoomMillis) : (ListenerUtil.mutListener.listen(10235) ? (now / mCurrentZoomMillis) : (ListenerUtil.mutListener.listen(10234) ? (now * mCurrentZoomMillis) : (ListenerUtil.mutListener.listen(10233) ? (now + mCurrentZoomMillis) : (now - mCurrentZoomMillis)))))) != WAIT_TIME) : (ListenerUtil.mutListener.listen(10237) ? (((ListenerUtil.mutListener.listen(10236) ? (now % mCurrentZoomMillis) : (ListenerUtil.mutListener.listen(10235) ? (now / mCurrentZoomMillis) : (ListenerUtil.mutListener.listen(10234) ? (now * mCurrentZoomMillis) : (ListenerUtil.mutListener.listen(10233) ? (now + mCurrentZoomMillis) : (now - mCurrentZoomMillis)))))) == WAIT_TIME) : (((ListenerUtil.mutListener.listen(10236) ? (now % mCurrentZoomMillis) : (ListenerUtil.mutListener.listen(10235) ? (now / mCurrentZoomMillis) : (ListenerUtil.mutListener.listen(10234) ? (now * mCurrentZoomMillis) : (ListenerUtil.mutListener.listen(10233) ? (now + mCurrentZoomMillis) : (now - mCurrentZoomMillis)))))) > WAIT_TIME))))))) : ((ListenerUtil.mutListener.listen(10232) ? (mCurrentZoomMillis >= 0) : (ListenerUtil.mutListener.listen(10231) ? (mCurrentZoomMillis <= 0) : (ListenerUtil.mutListener.listen(10230) ? (mCurrentZoomMillis > 0) : (ListenerUtil.mutListener.listen(10229) ? (mCurrentZoomMillis < 0) : (ListenerUtil.mutListener.listen(10228) ? (mCurrentZoomMillis == 0) : (mCurrentZoomMillis != 0)))))) && (ListenerUtil.mutListener.listen(10241) ? (((ListenerUtil.mutListener.listen(10236) ? (now % mCurrentZoomMillis) : (ListenerUtil.mutListener.listen(10235) ? (now / mCurrentZoomMillis) : (ListenerUtil.mutListener.listen(10234) ? (now * mCurrentZoomMillis) : (ListenerUtil.mutListener.listen(10233) ? (now + mCurrentZoomMillis) : (now - mCurrentZoomMillis)))))) >= WAIT_TIME) : (ListenerUtil.mutListener.listen(10240) ? (((ListenerUtil.mutListener.listen(10236) ? (now % mCurrentZoomMillis) : (ListenerUtil.mutListener.listen(10235) ? (now / mCurrentZoomMillis) : (ListenerUtil.mutListener.listen(10234) ? (now * mCurrentZoomMillis) : (ListenerUtil.mutListener.listen(10233) ? (now + mCurrentZoomMillis) : (now - mCurrentZoomMillis)))))) <= WAIT_TIME) : (ListenerUtil.mutListener.listen(10239) ? (((ListenerUtil.mutListener.listen(10236) ? (now % mCurrentZoomMillis) : (ListenerUtil.mutListener.listen(10235) ? (now / mCurrentZoomMillis) : (ListenerUtil.mutListener.listen(10234) ? (now * mCurrentZoomMillis) : (ListenerUtil.mutListener.listen(10233) ? (now + mCurrentZoomMillis) : (now - mCurrentZoomMillis)))))) < WAIT_TIME) : (ListenerUtil.mutListener.listen(10238) ? (((ListenerUtil.mutListener.listen(10236) ? (now % mCurrentZoomMillis) : (ListenerUtil.mutListener.listen(10235) ? (now / mCurrentZoomMillis) : (ListenerUtil.mutListener.listen(10234) ? (now * mCurrentZoomMillis) : (ListenerUtil.mutListener.listen(10233) ? (now + mCurrentZoomMillis) : (now - mCurrentZoomMillis)))))) != WAIT_TIME) : (ListenerUtil.mutListener.listen(10237) ? (((ListenerUtil.mutListener.listen(10236) ? (now % mCurrentZoomMillis) : (ListenerUtil.mutListener.listen(10235) ? (now / mCurrentZoomMillis) : (ListenerUtil.mutListener.listen(10234) ? (now * mCurrentZoomMillis) : (ListenerUtil.mutListener.listen(10233) ? (now + mCurrentZoomMillis) : (now - mCurrentZoomMillis)))))) == WAIT_TIME) : (((ListenerUtil.mutListener.listen(10236) ? (now % mCurrentZoomMillis) : (ListenerUtil.mutListener.listen(10235) ? (now / mCurrentZoomMillis) : (ListenerUtil.mutListener.listen(10234) ? (now * mCurrentZoomMillis) : (ListenerUtil.mutListener.listen(10233) ? (now + mCurrentZoomMillis) : (now - mCurrentZoomMillis)))))) > WAIT_TIME))))))))) {
                    if (!ListenerUtil.mutListener.listen(10243)) {
                        mListener.onMapZoomChanged();
                    }
                    if (!ListenerUtil.mutListener.listen(10244)) {
                        mCurrentZoomMillis = 0;
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(10249)) {
                mHandler.postDelayed(mChecker, POLL_TIME);
            }
        }
    };

    public MapWatcher(MapModeController.ObaMapView view, Listener listener) {
        mObaMapView = view;
        mHandler = new Handler();
        mListener = listener;
    }

    /**
     * Start watching.
     */
    public void start() {
        if (!ListenerUtil.mutListener.listen(10250)) {
            mCurrentCenter = mObaMapView.getMapCenterAsLocation();
        }
        if (!ListenerUtil.mutListener.listen(10251)) {
            mCurrentZoom = mObaMapView.getZoomLevelAsFloat();
        }
        if (!ListenerUtil.mutListener.listen(10252)) {
            mHandler.postDelayed(mChecker, POLL_TIME);
        }
    }

    /**
     * Stop watching.
     */
    public void stop() {
        if (!ListenerUtil.mutListener.listen(10253)) {
            mHandler.removeCallbacks(mChecker);
        }
    }

    /**
     * Check to see if anything changed now.
     * Fires the listener events if so.
     */
    public void checkNow() {
        if (!ListenerUtil.mutListener.listen(10254)) {
            mHandler.removeCallbacks(mChecker);
        }
        if (!ListenerUtil.mutListener.listen(10255)) {
            mChecker.run();
        }
    }
}
