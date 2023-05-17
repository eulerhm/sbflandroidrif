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
 * Copyright (C) 2008 ZXing authors
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
package ch.threema.app.qrscanner.activity;

import android.os.Handler;
import android.os.Message;
import com.google.zxing.Result;
import ch.threema.app.R;
import ch.threema.app.qrscanner.camera.CameraManager;
import ch.threema.app.qrscanner.decode.DecodeThread;
import ch.threema.app.qrscanner.view.ViewfinderResultPointCallback;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * @date 2016-11-18 10:30
 * @auther GuoJinyu
 * @description modified
 */
final class CaptureActivityHandler extends Handler {

    private static final String TAG = CaptureActivityHandler.class.getSimpleName();

    private final CaptureActivity activity;

    private final DecodeThread decodeThread;

    private final CameraManager cameraManager;

    private State state;

    CaptureActivityHandler(CaptureActivity activity, CameraManager cameraManager) {
        this.activity = activity;
        decodeThread = new DecodeThread(activity, new ViewfinderResultPointCallback(activity.getViewfinderView()));
        if (!ListenerUtil.mutListener.listen(33345)) {
            decodeThread.start();
        }
        if (!ListenerUtil.mutListener.listen(33346)) {
            state = State.SUCCESS;
        }
        // Start ourselves capturing previews and decoding.
        this.cameraManager = cameraManager;
        if (!ListenerUtil.mutListener.listen(33347)) {
            cameraManager.startPreview();
        }
        if (!ListenerUtil.mutListener.listen(33348)) {
            restartPreviewAndDecode();
        }
    }

    @Override
    public void handleMessage(Message message) {
        if (!ListenerUtil.mutListener.listen(33353)) {
            if (message.what == R.id.decode_succeeded) {
                if (!ListenerUtil.mutListener.listen(33351)) {
                    state = State.SUCCESS;
                }
                if (!ListenerUtil.mutListener.listen(33352)) {
                    activity.handleDecode((Result) message.obj);
                }
            } else if (message.what == R.id.decode_failed) {
                if (!ListenerUtil.mutListener.listen(33349)) {
                    // We're decoding as fast as possible, so when one decode fails, start another.
                    state = State.PREVIEW;
                }
                if (!ListenerUtil.mutListener.listen(33350)) {
                    cameraManager.requestPreviewFrame(decodeThread.getHandler(), R.id.decode);
                }
            }
        }
    }

    void quitSynchronously() {
        if (!ListenerUtil.mutListener.listen(33354)) {
            state = State.DONE;
        }
        if (!ListenerUtil.mutListener.listen(33355)) {
            cameraManager.stopPreview();
        }
        Message quit = Message.obtain(decodeThread.getHandler(), R.id.quit);
        if (!ListenerUtil.mutListener.listen(33356)) {
            quit.sendToTarget();
        }
        try {
            if (!ListenerUtil.mutListener.listen(33357)) {
                // Wait at most half a second; should be enough time, and onPause() will timeout quickly
                decodeThread.join(500L);
            }
        } catch (InterruptedException e) {
        }
        if (!ListenerUtil.mutListener.listen(33358)) {
            // Be absolutely sure we don't send any queued up messages
            removeMessages(R.id.decode_succeeded);
        }
        if (!ListenerUtil.mutListener.listen(33359)) {
            removeMessages(R.id.decode_failed);
        }
    }

    private void restartPreviewAndDecode() {
        if (!ListenerUtil.mutListener.listen(33363)) {
            if (state == State.SUCCESS) {
                if (!ListenerUtil.mutListener.listen(33360)) {
                    state = State.PREVIEW;
                }
                if (!ListenerUtil.mutListener.listen(33361)) {
                    cameraManager.requestPreviewFrame(decodeThread.getHandler(), R.id.decode);
                }
                if (!ListenerUtil.mutListener.listen(33362)) {
                    activity.drawViewfinder();
                }
            }
        }
    }

    private enum State {

        PREVIEW, SUCCESS, DONE
    }
}
