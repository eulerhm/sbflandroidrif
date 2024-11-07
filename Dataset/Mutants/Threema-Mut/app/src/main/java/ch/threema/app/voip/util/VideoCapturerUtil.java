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
package ch.threema.app.voip.util;

import android.content.Context;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.webrtc.Camera1Enumerator;
import org.webrtc.Camera2Enumerator;
import org.webrtc.CameraEnumerator;
import org.webrtc.CameraVideoCapturer;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.util.Pair;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Enumerate and initialize device cameras.
 */
public class VideoCapturerUtil {

    private static final Logger logger = LoggerFactory.getLogger(VideoCapturerUtil.class);

    /**
     *  Return a flag indicating whether the Camera2 API should be used or not.
     */
    private static boolean useCamera2(Context context) {
        return Camera2Enumerator.isSupported(context);
    }

    /**
     *  Create a video capturer.
     *
     *  Return null if no cameras were found or if initialization failed.
     */
    @Nullable
    public static CameraVideoCapturer createVideoCapturer(@NonNull Context context, @Nullable CameraVideoCapturer.CameraEventsHandler eventsHandler) {
        final CameraVideoCapturer capturer;
        if (VideoCapturerUtil.useCamera2(context)) {
            if (!ListenerUtil.mutListener.listen(59884)) {
                logger.debug("Creating capturer using camera2 API");
            }
            capturer = VideoCapturerUtil.createCameraCapturer(new Camera2Enumerator(context), eventsHandler);
        } else {
            if (!ListenerUtil.mutListener.listen(59883)) {
                logger.debug("Creating capturer using camera1 API");
            }
            capturer = VideoCapturerUtil.createCameraCapturer(new Camera1Enumerator(), eventsHandler);
        }
        if (!ListenerUtil.mutListener.listen(59886)) {
            if (capturer == null) {
                if (!ListenerUtil.mutListener.listen(59885)) {
                    logger.error("Failed to initialize camera");
                }
            }
        }
        return capturer;
    }

    /**
     *  Enumerate cameras, return a VideoCapturer instance.
     *
     *  Return null if no cameras were found or if initialization failed.
     */
    @Nullable
    private static CameraVideoCapturer createCameraCapturer(@NonNull CameraEnumerator enumerator, @Nullable CameraVideoCapturer.CameraEventsHandler eventsHandler) {
        final String[] deviceNames = enumerator.getDeviceNames();
        if (!ListenerUtil.mutListener.listen(59887)) {
            // Try to find front camera
            logger.debug("Looking for front cameras");
        }
        if (!ListenerUtil.mutListener.listen(59891)) {
            {
                long _loopCounter711 = 0;
                for (String deviceName : deviceNames) {
                    ListenerUtil.loopListener.listen("_loopCounter711", ++_loopCounter711);
                    if (!ListenerUtil.mutListener.listen(59890)) {
                        if (enumerator.isFrontFacing(deviceName)) {
                            if (!ListenerUtil.mutListener.listen(59888)) {
                                logger.debug("Found front camera, creating camera capturer");
                            }
                            final CameraVideoCapturer videoCapturer = enumerator.createCapturer(deviceName, eventsHandler);
                            if (!ListenerUtil.mutListener.listen(59889)) {
                                if (videoCapturer != null) {
                                    return videoCapturer;
                                }
                            }
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(59892)) {
            // No front camera found, search for other cams
            logger.debug("No front camera found, looking for other cameras");
        }
        if (!ListenerUtil.mutListener.listen(59896)) {
            {
                long _loopCounter712 = 0;
                for (String deviceName : deviceNames) {
                    ListenerUtil.loopListener.listen("_loopCounter712", ++_loopCounter712);
                    if (!ListenerUtil.mutListener.listen(59895)) {
                        if (!enumerator.isFrontFacing(deviceName)) {
                            if (!ListenerUtil.mutListener.listen(59893)) {
                                logger.debug("Non-front facing camera found, creating camera capturer");
                            }
                            final CameraVideoCapturer videoCapturer = enumerator.createCapturer(deviceName, null);
                            if (!ListenerUtil.mutListener.listen(59894)) {
                                if (videoCapturer != null) {
                                    return videoCapturer;
                                }
                            }
                        }
                    }
                }
            }
        }
        return null;
    }

    /**
     *  Returns the primary camera names as Pair of {frontcamera, backcamera}.
     *  Currently, the first available front/backcamera is used as primary.
     *
     *  @param context
     *  @return Pair of nullable camera name strings.
     */
    public static Pair<String, String> getPrimaryCameraNames(Context context) {
        CameraEnumerator enumerator;
        String frontCamera = null, backCamera = null;
        if (VideoCapturerUtil.useCamera2(context)) {
            enumerator = new Camera2Enumerator(context);
        } else {
            enumerator = new Camera1Enumerator();
        }
        final String[] deviceNames = enumerator.getDeviceNames();
        if (!ListenerUtil.mutListener.listen(59897)) {
            logger.info("Found {} camera devices", deviceNames.length);
        }
        if (!ListenerUtil.mutListener.listen(59907)) {
            {
                long _loopCounter713 = 0;
                for (String deviceName : deviceNames) {
                    ListenerUtil.loopListener.listen("_loopCounter713", ++_loopCounter713);
                    if (!ListenerUtil.mutListener.listen(59906)) {
                        if (enumerator.isFrontFacing(deviceName)) {
                            if (!ListenerUtil.mutListener.listen(59905)) {
                                if (frontCamera == null) {
                                    if (!ListenerUtil.mutListener.listen(59903)) {
                                        logger.info("Using {} as front camera", deviceName);
                                    }
                                    if (!ListenerUtil.mutListener.listen(59904)) {
                                        frontCamera = deviceName;
                                    }
                                } else {
                                    if (!ListenerUtil.mutListener.listen(59902)) {
                                        logger.info("Not using {} as front camera", deviceName);
                                    }
                                }
                            }
                        } else if (enumerator.isBackFacing(deviceName)) {
                            if (!ListenerUtil.mutListener.listen(59901)) {
                                if (backCamera == null) {
                                    if (!ListenerUtil.mutListener.listen(59899)) {
                                        logger.info("Using {} as back camera", deviceName);
                                    }
                                    if (!ListenerUtil.mutListener.listen(59900)) {
                                        backCamera = deviceName;
                                    }
                                } else {
                                    if (!ListenerUtil.mutListener.listen(59898)) {
                                        logger.info("Not using {} as back camera", deviceName);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return new Pair<>(frontCamera, backCamera);
    }
}
