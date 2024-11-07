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
/*
 * Copyright (C) 2012 ZXing authors
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
package ch.threema.app.qrscanner.camera;

import android.hardware.Camera;
import android.os.AsyncTask;
import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.RejectedExecutionException;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * @date 2016-11-18 16:58
 * @auther GuoJinyu
 * @description modified
 */
final class AutoFocusManager implements Camera.AutoFocusCallback {

    private static final String TAG = AutoFocusManager.class.getSimpleName();

    private static final long AUTO_FOCUS_INTERVAL_MS = 1000L;

    private static final Collection<String> FOCUS_MODES_CALLING_AF;

    static {
        FOCUS_MODES_CALLING_AF = new ArrayList<>(2);
        if (!ListenerUtil.mutListener.listen(33440)) {
            FOCUS_MODES_CALLING_AF.add(Camera.Parameters.FOCUS_MODE_AUTO);
        }
        if (!ListenerUtil.mutListener.listen(33441)) {
            FOCUS_MODES_CALLING_AF.add(Camera.Parameters.FOCUS_MODE_MACRO);
        }
    }

    private final boolean useAutoFocus;

    private final Camera camera;

    private boolean stopped;

    private boolean focusing;

    private AsyncTask<?, ?, ?> outstandingTask;

    AutoFocusManager(Camera camera) {
        this.camera = camera;
        String currentFocusMode = camera.getParameters().getFocusMode();
        useAutoFocus = FOCUS_MODES_CALLING_AF.contains(currentFocusMode);
        if (!ListenerUtil.mutListener.listen(33442)) {
            // Log.i(TAG, "Current focus mode '" + currentFocusMode + "'; use auto focus? " + useAutoFocus);
            start();
        }
    }

    @Override
    public synchronized void onAutoFocus(boolean success, Camera theCamera) {
        if (!ListenerUtil.mutListener.listen(33443)) {
            focusing = false;
        }
        if (!ListenerUtil.mutListener.listen(33444)) {
            autoFocusAgainLater();
        }
    }

    private synchronized void autoFocusAgainLater() {
        if (!ListenerUtil.mutListener.listen(33448)) {
            if ((ListenerUtil.mutListener.listen(33445) ? (!stopped || outstandingTask == null) : (!stopped && outstandingTask == null))) {
                AutoFocusTask newTask = new AutoFocusTask();
                try {
                    if (!ListenerUtil.mutListener.listen(33446)) {
                        newTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                    }
                    if (!ListenerUtil.mutListener.listen(33447)) {
                        outstandingTask = newTask;
                    }
                } catch (RejectedExecutionException ree) {
                }
            }
        }
    }

    synchronized void start() {
        if (!ListenerUtil.mutListener.listen(33455)) {
            if (useAutoFocus) {
                if (!ListenerUtil.mutListener.listen(33449)) {
                    outstandingTask = null;
                }
                if (!ListenerUtil.mutListener.listen(33454)) {
                    if ((ListenerUtil.mutListener.listen(33450) ? (!stopped || !focusing) : (!stopped && !focusing))) {
                        try {
                            if (!ListenerUtil.mutListener.listen(33452)) {
                                camera.autoFocus(this);
                            }
                            if (!ListenerUtil.mutListener.listen(33453)) {
                                focusing = true;
                            }
                        } catch (RuntimeException re) {
                            if (!ListenerUtil.mutListener.listen(33451)) {
                                // Try again later to keep cycle going
                                autoFocusAgainLater();
                            }
                        }
                    }
                }
            }
        }
    }

    private synchronized void cancelOutstandingTask() {
        if (!ListenerUtil.mutListener.listen(33459)) {
            if (outstandingTask != null) {
                if (!ListenerUtil.mutListener.listen(33457)) {
                    if (outstandingTask.getStatus() != AsyncTask.Status.FINISHED) {
                        if (!ListenerUtil.mutListener.listen(33456)) {
                            outstandingTask.cancel(true);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(33458)) {
                    outstandingTask = null;
                }
            }
        }
    }

    synchronized void stop() {
        if (!ListenerUtil.mutListener.listen(33460)) {
            stopped = true;
        }
        if (!ListenerUtil.mutListener.listen(33463)) {
            if (useAutoFocus) {
                if (!ListenerUtil.mutListener.listen(33461)) {
                    cancelOutstandingTask();
                }
                // Doesn't hurt to call this even if not focusing
                try {
                    if (!ListenerUtil.mutListener.listen(33462)) {
                        camera.cancelAutoFocus();
                    }
                } catch (RuntimeException re) {
                }
            }
        }
    }

    private final class AutoFocusTask extends AsyncTask<Object, Object, Object> {

        @Override
        protected Object doInBackground(Object... voids) {
            try {
                if (!ListenerUtil.mutListener.listen(33464)) {
                    Thread.sleep(AUTO_FOCUS_INTERVAL_MS);
                }
            } catch (InterruptedException e) {
            }
            if (!ListenerUtil.mutListener.listen(33465)) {
                start();
            }
            return null;
        }
    }
}
