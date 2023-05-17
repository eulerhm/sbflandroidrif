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
 * Copyright (C) 2010 ZXing authors
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

import android.graphics.Point;
import android.hardware.Camera;
import android.os.Handler;
import android.os.Message;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

final class PreviewCallback implements Camera.PreviewCallback {

    private static final String TAG = PreviewCallback.class.getSimpleName();

    private final CameraConfigurationManager configManager;

    private Handler previewHandler;

    private int previewMessage;

    PreviewCallback(CameraConfigurationManager configManager) {
        this.configManager = configManager;
    }

    void setHandler(Handler previewHandler, int previewMessage) {
        if (!ListenerUtil.mutListener.listen(33978)) {
            this.previewHandler = previewHandler;
        }
        if (!ListenerUtil.mutListener.listen(33979)) {
            this.previewMessage = previewMessage;
        }
    }

    @Override
    public void onPreviewFrame(byte[] data, Camera camera) {
        Point cameraResolution = configManager.getCameraResolution();
        Handler thePreviewHandler = previewHandler;
        if (!ListenerUtil.mutListener.listen(33988)) {
            if ((ListenerUtil.mutListener.listen(33980) ? (cameraResolution != null || thePreviewHandler != null) : (cameraResolution != null && thePreviewHandler != null))) {
                Message message;
                Point screenResolution = configManager.getScreenResolution();
                if ((ListenerUtil.mutListener.listen(33985) ? (screenResolution.x >= screenResolution.y) : (ListenerUtil.mutListener.listen(33984) ? (screenResolution.x <= screenResolution.y) : (ListenerUtil.mutListener.listen(33983) ? (screenResolution.x > screenResolution.y) : (ListenerUtil.mutListener.listen(33982) ? (screenResolution.x != screenResolution.y) : (ListenerUtil.mutListener.listen(33981) ? (screenResolution.x == screenResolution.y) : (screenResolution.x < screenResolution.y))))))) {
                    // portrait
                    message = thePreviewHandler.obtainMessage(previewMessage, cameraResolution.y, cameraResolution.x, data);
                } else {
                    // landscape
                    message = thePreviewHandler.obtainMessage(previewMessage, cameraResolution.x, cameraResolution.y, data);
                }
                if (!ListenerUtil.mutListener.listen(33986)) {
                    message.sendToTarget();
                }
                if (!ListenerUtil.mutListener.listen(33987)) {
                    previewHandler = null;
                }
            } else {
            }
        }
    }
}
