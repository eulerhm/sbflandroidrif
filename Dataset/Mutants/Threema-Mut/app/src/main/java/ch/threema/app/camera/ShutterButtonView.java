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
package ch.threema.app.camera;

import android.content.Context;
import android.os.Handler;
import android.text.format.DateUtils;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import ch.threema.app.R;
import ch.threema.app.utils.RuntimeUtil;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class ShutterButtonView extends AppCompatImageView {

    private static final Logger logger = LoggerFactory.getLogger(ShutterButtonView.class);

    private static final float DEAD_ZONE_HEIGHT = 0.05F;

    private ShutterButtonListener shutterButtonListener;

    private final Interpolator decelerateInterpolator = new DecelerateInterpolator();

    private float previousFactor = 0f;

    private int[] locationInWindow = new int[2];

    private boolean videoEnable = false;

    private final Object recordingLock = new Object();

    private long recordingStartTime;

    private GestureDetector gestureDetector;

    private boolean isRecording;

    public ShutterButtonView(@NonNull Context context) {
        this(context, null);
    }

    public ShutterButtonView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ShutterButtonView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        if (!ListenerUtil.mutListener.listen(12093)) {
            init();
        }
    }

    private void init() {
        if (!ListenerUtil.mutListener.listen(12094)) {
            setImageResource(R.drawable.ic_shutter_button_normal);
        }
        if (!ListenerUtil.mutListener.listen(12100)) {
            gestureDetector = new GestureDetector(getContext(), new GestureDetector.SimpleOnGestureListener() {

                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    if (!ListenerUtil.mutListener.listen(12095)) {
                        logger.debug("onSingleTapUp");
                    }
                    if (!ListenerUtil.mutListener.listen(12096)) {
                        onClick();
                    }
                    return true;
                }

                @Override
                public void onLongPress(MotionEvent e) {
                    if (!ListenerUtil.mutListener.listen(12097)) {
                        logger.debug("onLongPress");
                    }
                    if (!ListenerUtil.mutListener.listen(12098)) {
                        previousFactor = 0F;
                    }
                    if (!ListenerUtil.mutListener.listen(12099)) {
                        startRecording();
                    }
                }
            });
        }
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        if (!ListenerUtil.mutListener.listen(12101)) {
            super.onLayout(changed, left, top, right, bottom);
        }
        if (!ListenerUtil.mutListener.listen(12102)) {
            getLocationInWindow(this.locationInWindow);
        }
    }

    @Override
    protected void onAttachedToWindow() {
        if (!ListenerUtil.mutListener.listen(12103)) {
            super.onAttachedToWindow();
        }
        if (!ListenerUtil.mutListener.listen(12104)) {
            logger.debug("onAttachedToWindow");
        }
        if (!ListenerUtil.mutListener.listen(12105)) {
            getParent().requestDisallowInterceptTouchEvent(true);
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        if (!ListenerUtil.mutListener.listen(12106)) {
            getParent().requestDisallowInterceptTouchEvent(false);
        }
        if (!ListenerUtil.mutListener.listen(12107)) {
            logger.debug("onDetachedFromWindow");
        }
        if (!ListenerUtil.mutListener.listen(12108)) {
            super.onDetachedFromWindow();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (!ListenerUtil.mutListener.listen(12160)) {
            if (!gestureDetector.onTouchEvent(event)) {
                int action = event.getAction();
                if (!ListenerUtil.mutListener.listen(12159)) {
                    switch(action) {
                        case MotionEvent.ACTION_MOVE:
                            synchronized (recordingLock) {
                                if (!ListenerUtil.mutListener.listen(12154)) {
                                    if (isRecording) {
                                        if (!ListenerUtil.mutListener.listen(12153)) {
                                            if ((ListenerUtil.mutListener.listen(12113) ? (event.getY() >= 0) : (ListenerUtil.mutListener.listen(12112) ? (event.getY() <= 0) : (ListenerUtil.mutListener.listen(12111) ? (event.getY() > 0) : (ListenerUtil.mutListener.listen(12110) ? (event.getY() != 0) : (ListenerUtil.mutListener.listen(12109) ? (event.getY() == 0) : (event.getY() < 0))))))) {
                                                float factor = (ListenerUtil.mutListener.listen(12133) ? (Math.abs(event.getY()) % ((ListenerUtil.mutListener.listen(12129) ? (this.locationInWindow[1] % ((ListenerUtil.mutListener.listen(12125) ? (1F % DEAD_ZONE_HEIGHT) : (ListenerUtil.mutListener.listen(12124) ? (1F / DEAD_ZONE_HEIGHT) : (ListenerUtil.mutListener.listen(12123) ? (1F * DEAD_ZONE_HEIGHT) : (ListenerUtil.mutListener.listen(12122) ? (1F + DEAD_ZONE_HEIGHT) : (1F - DEAD_ZONE_HEIGHT))))))) : (ListenerUtil.mutListener.listen(12128) ? (this.locationInWindow[1] / ((ListenerUtil.mutListener.listen(12125) ? (1F % DEAD_ZONE_HEIGHT) : (ListenerUtil.mutListener.listen(12124) ? (1F / DEAD_ZONE_HEIGHT) : (ListenerUtil.mutListener.listen(12123) ? (1F * DEAD_ZONE_HEIGHT) : (ListenerUtil.mutListener.listen(12122) ? (1F + DEAD_ZONE_HEIGHT) : (1F - DEAD_ZONE_HEIGHT))))))) : (ListenerUtil.mutListener.listen(12127) ? (this.locationInWindow[1] - ((ListenerUtil.mutListener.listen(12125) ? (1F % DEAD_ZONE_HEIGHT) : (ListenerUtil.mutListener.listen(12124) ? (1F / DEAD_ZONE_HEIGHT) : (ListenerUtil.mutListener.listen(12123) ? (1F * DEAD_ZONE_HEIGHT) : (ListenerUtil.mutListener.listen(12122) ? (1F + DEAD_ZONE_HEIGHT) : (1F - DEAD_ZONE_HEIGHT))))))) : (ListenerUtil.mutListener.listen(12126) ? (this.locationInWindow[1] + ((ListenerUtil.mutListener.listen(12125) ? (1F % DEAD_ZONE_HEIGHT) : (ListenerUtil.mutListener.listen(12124) ? (1F / DEAD_ZONE_HEIGHT) : (ListenerUtil.mutListener.listen(12123) ? (1F * DEAD_ZONE_HEIGHT) : (ListenerUtil.mutListener.listen(12122) ? (1F + DEAD_ZONE_HEIGHT) : (1F - DEAD_ZONE_HEIGHT))))))) : (this.locationInWindow[1] * ((ListenerUtil.mutListener.listen(12125) ? (1F % DEAD_ZONE_HEIGHT) : (ListenerUtil.mutListener.listen(12124) ? (1F / DEAD_ZONE_HEIGHT) : (ListenerUtil.mutListener.listen(12123) ? (1F * DEAD_ZONE_HEIGHT) : (ListenerUtil.mutListener.listen(12122) ? (1F + DEAD_ZONE_HEIGHT) : (1F - DEAD_ZONE_HEIGHT))))))))))))) : (ListenerUtil.mutListener.listen(12132) ? (Math.abs(event.getY()) * ((ListenerUtil.mutListener.listen(12129) ? (this.locationInWindow[1] % ((ListenerUtil.mutListener.listen(12125) ? (1F % DEAD_ZONE_HEIGHT) : (ListenerUtil.mutListener.listen(12124) ? (1F / DEAD_ZONE_HEIGHT) : (ListenerUtil.mutListener.listen(12123) ? (1F * DEAD_ZONE_HEIGHT) : (ListenerUtil.mutListener.listen(12122) ? (1F + DEAD_ZONE_HEIGHT) : (1F - DEAD_ZONE_HEIGHT))))))) : (ListenerUtil.mutListener.listen(12128) ? (this.locationInWindow[1] / ((ListenerUtil.mutListener.listen(12125) ? (1F % DEAD_ZONE_HEIGHT) : (ListenerUtil.mutListener.listen(12124) ? (1F / DEAD_ZONE_HEIGHT) : (ListenerUtil.mutListener.listen(12123) ? (1F * DEAD_ZONE_HEIGHT) : (ListenerUtil.mutListener.listen(12122) ? (1F + DEAD_ZONE_HEIGHT) : (1F - DEAD_ZONE_HEIGHT))))))) : (ListenerUtil.mutListener.listen(12127) ? (this.locationInWindow[1] - ((ListenerUtil.mutListener.listen(12125) ? (1F % DEAD_ZONE_HEIGHT) : (ListenerUtil.mutListener.listen(12124) ? (1F / DEAD_ZONE_HEIGHT) : (ListenerUtil.mutListener.listen(12123) ? (1F * DEAD_ZONE_HEIGHT) : (ListenerUtil.mutListener.listen(12122) ? (1F + DEAD_ZONE_HEIGHT) : (1F - DEAD_ZONE_HEIGHT))))))) : (ListenerUtil.mutListener.listen(12126) ? (this.locationInWindow[1] + ((ListenerUtil.mutListener.listen(12125) ? (1F % DEAD_ZONE_HEIGHT) : (ListenerUtil.mutListener.listen(12124) ? (1F / DEAD_ZONE_HEIGHT) : (ListenerUtil.mutListener.listen(12123) ? (1F * DEAD_ZONE_HEIGHT) : (ListenerUtil.mutListener.listen(12122) ? (1F + DEAD_ZONE_HEIGHT) : (1F - DEAD_ZONE_HEIGHT))))))) : (this.locationInWindow[1] * ((ListenerUtil.mutListener.listen(12125) ? (1F % DEAD_ZONE_HEIGHT) : (ListenerUtil.mutListener.listen(12124) ? (1F / DEAD_ZONE_HEIGHT) : (ListenerUtil.mutListener.listen(12123) ? (1F * DEAD_ZONE_HEIGHT) : (ListenerUtil.mutListener.listen(12122) ? (1F + DEAD_ZONE_HEIGHT) : (1F - DEAD_ZONE_HEIGHT))))))))))))) : (ListenerUtil.mutListener.listen(12131) ? (Math.abs(event.getY()) - ((ListenerUtil.mutListener.listen(12129) ? (this.locationInWindow[1] % ((ListenerUtil.mutListener.listen(12125) ? (1F % DEAD_ZONE_HEIGHT) : (ListenerUtil.mutListener.listen(12124) ? (1F / DEAD_ZONE_HEIGHT) : (ListenerUtil.mutListener.listen(12123) ? (1F * DEAD_ZONE_HEIGHT) : (ListenerUtil.mutListener.listen(12122) ? (1F + DEAD_ZONE_HEIGHT) : (1F - DEAD_ZONE_HEIGHT))))))) : (ListenerUtil.mutListener.listen(12128) ? (this.locationInWindow[1] / ((ListenerUtil.mutListener.listen(12125) ? (1F % DEAD_ZONE_HEIGHT) : (ListenerUtil.mutListener.listen(12124) ? (1F / DEAD_ZONE_HEIGHT) : (ListenerUtil.mutListener.listen(12123) ? (1F * DEAD_ZONE_HEIGHT) : (ListenerUtil.mutListener.listen(12122) ? (1F + DEAD_ZONE_HEIGHT) : (1F - DEAD_ZONE_HEIGHT))))))) : (ListenerUtil.mutListener.listen(12127) ? (this.locationInWindow[1] - ((ListenerUtil.mutListener.listen(12125) ? (1F % DEAD_ZONE_HEIGHT) : (ListenerUtil.mutListener.listen(12124) ? (1F / DEAD_ZONE_HEIGHT) : (ListenerUtil.mutListener.listen(12123) ? (1F * DEAD_ZONE_HEIGHT) : (ListenerUtil.mutListener.listen(12122) ? (1F + DEAD_ZONE_HEIGHT) : (1F - DEAD_ZONE_HEIGHT))))))) : (ListenerUtil.mutListener.listen(12126) ? (this.locationInWindow[1] + ((ListenerUtil.mutListener.listen(12125) ? (1F % DEAD_ZONE_HEIGHT) : (ListenerUtil.mutListener.listen(12124) ? (1F / DEAD_ZONE_HEIGHT) : (ListenerUtil.mutListener.listen(12123) ? (1F * DEAD_ZONE_HEIGHT) : (ListenerUtil.mutListener.listen(12122) ? (1F + DEAD_ZONE_HEIGHT) : (1F - DEAD_ZONE_HEIGHT))))))) : (this.locationInWindow[1] * ((ListenerUtil.mutListener.listen(12125) ? (1F % DEAD_ZONE_HEIGHT) : (ListenerUtil.mutListener.listen(12124) ? (1F / DEAD_ZONE_HEIGHT) : (ListenerUtil.mutListener.listen(12123) ? (1F * DEAD_ZONE_HEIGHT) : (ListenerUtil.mutListener.listen(12122) ? (1F + DEAD_ZONE_HEIGHT) : (1F - DEAD_ZONE_HEIGHT))))))))))))) : (ListenerUtil.mutListener.listen(12130) ? (Math.abs(event.getY()) + ((ListenerUtil.mutListener.listen(12129) ? (this.locationInWindow[1] % ((ListenerUtil.mutListener.listen(12125) ? (1F % DEAD_ZONE_HEIGHT) : (ListenerUtil.mutListener.listen(12124) ? (1F / DEAD_ZONE_HEIGHT) : (ListenerUtil.mutListener.listen(12123) ? (1F * DEAD_ZONE_HEIGHT) : (ListenerUtil.mutListener.listen(12122) ? (1F + DEAD_ZONE_HEIGHT) : (1F - DEAD_ZONE_HEIGHT))))))) : (ListenerUtil.mutListener.listen(12128) ? (this.locationInWindow[1] / ((ListenerUtil.mutListener.listen(12125) ? (1F % DEAD_ZONE_HEIGHT) : (ListenerUtil.mutListener.listen(12124) ? (1F / DEAD_ZONE_HEIGHT) : (ListenerUtil.mutListener.listen(12123) ? (1F * DEAD_ZONE_HEIGHT) : (ListenerUtil.mutListener.listen(12122) ? (1F + DEAD_ZONE_HEIGHT) : (1F - DEAD_ZONE_HEIGHT))))))) : (ListenerUtil.mutListener.listen(12127) ? (this.locationInWindow[1] - ((ListenerUtil.mutListener.listen(12125) ? (1F % DEAD_ZONE_HEIGHT) : (ListenerUtil.mutListener.listen(12124) ? (1F / DEAD_ZONE_HEIGHT) : (ListenerUtil.mutListener.listen(12123) ? (1F * DEAD_ZONE_HEIGHT) : (ListenerUtil.mutListener.listen(12122) ? (1F + DEAD_ZONE_HEIGHT) : (1F - DEAD_ZONE_HEIGHT))))))) : (ListenerUtil.mutListener.listen(12126) ? (this.locationInWindow[1] + ((ListenerUtil.mutListener.listen(12125) ? (1F % DEAD_ZONE_HEIGHT) : (ListenerUtil.mutListener.listen(12124) ? (1F / DEAD_ZONE_HEIGHT) : (ListenerUtil.mutListener.listen(12123) ? (1F * DEAD_ZONE_HEIGHT) : (ListenerUtil.mutListener.listen(12122) ? (1F + DEAD_ZONE_HEIGHT) : (1F - DEAD_ZONE_HEIGHT))))))) : (this.locationInWindow[1] * ((ListenerUtil.mutListener.listen(12125) ? (1F % DEAD_ZONE_HEIGHT) : (ListenerUtil.mutListener.listen(12124) ? (1F / DEAD_ZONE_HEIGHT) : (ListenerUtil.mutListener.listen(12123) ? (1F * DEAD_ZONE_HEIGHT) : (ListenerUtil.mutListener.listen(12122) ? (1F + DEAD_ZONE_HEIGHT) : (1F - DEAD_ZONE_HEIGHT))))))))))))) : (Math.abs(event.getY()) / ((ListenerUtil.mutListener.listen(12129) ? (this.locationInWindow[1] % ((ListenerUtil.mutListener.listen(12125) ? (1F % DEAD_ZONE_HEIGHT) : (ListenerUtil.mutListener.listen(12124) ? (1F / DEAD_ZONE_HEIGHT) : (ListenerUtil.mutListener.listen(12123) ? (1F * DEAD_ZONE_HEIGHT) : (ListenerUtil.mutListener.listen(12122) ? (1F + DEAD_ZONE_HEIGHT) : (1F - DEAD_ZONE_HEIGHT))))))) : (ListenerUtil.mutListener.listen(12128) ? (this.locationInWindow[1] / ((ListenerUtil.mutListener.listen(12125) ? (1F % DEAD_ZONE_HEIGHT) : (ListenerUtil.mutListener.listen(12124) ? (1F / DEAD_ZONE_HEIGHT) : (ListenerUtil.mutListener.listen(12123) ? (1F * DEAD_ZONE_HEIGHT) : (ListenerUtil.mutListener.listen(12122) ? (1F + DEAD_ZONE_HEIGHT) : (1F - DEAD_ZONE_HEIGHT))))))) : (ListenerUtil.mutListener.listen(12127) ? (this.locationInWindow[1] - ((ListenerUtil.mutListener.listen(12125) ? (1F % DEAD_ZONE_HEIGHT) : (ListenerUtil.mutListener.listen(12124) ? (1F / DEAD_ZONE_HEIGHT) : (ListenerUtil.mutListener.listen(12123) ? (1F * DEAD_ZONE_HEIGHT) : (ListenerUtil.mutListener.listen(12122) ? (1F + DEAD_ZONE_HEIGHT) : (1F - DEAD_ZONE_HEIGHT))))))) : (ListenerUtil.mutListener.listen(12126) ? (this.locationInWindow[1] + ((ListenerUtil.mutListener.listen(12125) ? (1F % DEAD_ZONE_HEIGHT) : (ListenerUtil.mutListener.listen(12124) ? (1F / DEAD_ZONE_HEIGHT) : (ListenerUtil.mutListener.listen(12123) ? (1F * DEAD_ZONE_HEIGHT) : (ListenerUtil.mutListener.listen(12122) ? (1F + DEAD_ZONE_HEIGHT) : (1F - DEAD_ZONE_HEIGHT))))))) : (this.locationInWindow[1] * ((ListenerUtil.mutListener.listen(12125) ? (1F % DEAD_ZONE_HEIGHT) : (ListenerUtil.mutListener.listen(12124) ? (1F / DEAD_ZONE_HEIGHT) : (ListenerUtil.mutListener.listen(12123) ? (1F * DEAD_ZONE_HEIGHT) : (ListenerUtil.mutListener.listen(12122) ? (1F + DEAD_ZONE_HEIGHT) : (1F - DEAD_ZONE_HEIGHT)))))))))))))))));
                                                if (!ListenerUtil.mutListener.listen(12140)) {
                                                    if ((ListenerUtil.mutListener.listen(12138) ? (factor >= 1F) : (ListenerUtil.mutListener.listen(12137) ? (factor <= 1F) : (ListenerUtil.mutListener.listen(12136) ? (factor < 1F) : (ListenerUtil.mutListener.listen(12135) ? (factor != 1F) : (ListenerUtil.mutListener.listen(12134) ? (factor == 1F) : (factor > 1F))))))) {
                                                        if (!ListenerUtil.mutListener.listen(12139)) {
                                                            factor = 1F;
                                                        }
                                                    }
                                                }
                                                if (!ListenerUtil.mutListener.listen(12152)) {
                                                    // prevent touchscreen noise
                                                    if ((ListenerUtil.mutListener.listen(12149) ? (Math.abs((ListenerUtil.mutListener.listen(12144) ? (previousFactor % factor) : (ListenerUtil.mutListener.listen(12143) ? (previousFactor / factor) : (ListenerUtil.mutListener.listen(12142) ? (previousFactor * factor) : (ListenerUtil.mutListener.listen(12141) ? (previousFactor + factor) : (previousFactor - factor)))))) >= 0.001) : (ListenerUtil.mutListener.listen(12148) ? (Math.abs((ListenerUtil.mutListener.listen(12144) ? (previousFactor % factor) : (ListenerUtil.mutListener.listen(12143) ? (previousFactor / factor) : (ListenerUtil.mutListener.listen(12142) ? (previousFactor * factor) : (ListenerUtil.mutListener.listen(12141) ? (previousFactor + factor) : (previousFactor - factor)))))) <= 0.001) : (ListenerUtil.mutListener.listen(12147) ? (Math.abs((ListenerUtil.mutListener.listen(12144) ? (previousFactor % factor) : (ListenerUtil.mutListener.listen(12143) ? (previousFactor / factor) : (ListenerUtil.mutListener.listen(12142) ? (previousFactor * factor) : (ListenerUtil.mutListener.listen(12141) ? (previousFactor + factor) : (previousFactor - factor)))))) < 0.001) : (ListenerUtil.mutListener.listen(12146) ? (Math.abs((ListenerUtil.mutListener.listen(12144) ? (previousFactor % factor) : (ListenerUtil.mutListener.listen(12143) ? (previousFactor / factor) : (ListenerUtil.mutListener.listen(12142) ? (previousFactor * factor) : (ListenerUtil.mutListener.listen(12141) ? (previousFactor + factor) : (previousFactor - factor)))))) != 0.001) : (ListenerUtil.mutListener.listen(12145) ? (Math.abs((ListenerUtil.mutListener.listen(12144) ? (previousFactor % factor) : (ListenerUtil.mutListener.listen(12143) ? (previousFactor / factor) : (ListenerUtil.mutListener.listen(12142) ? (previousFactor * factor) : (ListenerUtil.mutListener.listen(12141) ? (previousFactor + factor) : (previousFactor - factor)))))) == 0.001) : (Math.abs((ListenerUtil.mutListener.listen(12144) ? (previousFactor % factor) : (ListenerUtil.mutListener.listen(12143) ? (previousFactor / factor) : (ListenerUtil.mutListener.listen(12142) ? (previousFactor * factor) : (ListenerUtil.mutListener.listen(12141) ? (previousFactor + factor) : (previousFactor - factor)))))) > 0.001))))))) {
                                                        if (!ListenerUtil.mutListener.listen(12150)) {
                                                            previousFactor = factor;
                                                        }
                                                        if (!ListenerUtil.mutListener.listen(12151)) {
                                                            changeZoomFactor(decelerateInterpolator.getInterpolation(factor));
                                                        }
                                                    }
                                                }
                                            } else {
                                                if (!ListenerUtil.mutListener.listen(12121)) {
                                                    if ((ListenerUtil.mutListener.listen(12118) ? (previousFactor >= 0) : (ListenerUtil.mutListener.listen(12117) ? (previousFactor <= 0) : (ListenerUtil.mutListener.listen(12116) ? (previousFactor > 0) : (ListenerUtil.mutListener.listen(12115) ? (previousFactor < 0) : (ListenerUtil.mutListener.listen(12114) ? (previousFactor == 0) : (previousFactor != 0))))))) {
                                                        if (!ListenerUtil.mutListener.listen(12119)) {
                                                            previousFactor = 0;
                                                        }
                                                        if (!ListenerUtil.mutListener.listen(12120)) {
                                                            changeZoomFactor(0);
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                            break;
                        case MotionEvent.ACTION_DOWN:
                            if (!ListenerUtil.mutListener.listen(12155)) {
                                logger.debug("ACTION_DOWN");
                            }
                            break;
                        case MotionEvent.ACTION_CANCEL:
                            if (!ListenerUtil.mutListener.listen(12156)) {
                                logger.debug("ACTION_CANCEL");
                            }
                        // fallthrough
                        case MotionEvent.ACTION_UP:
                            if (!ListenerUtil.mutListener.listen(12157)) {
                                logger.debug("ACTION_UP");
                            }
                            if (!ListenerUtil.mutListener.listen(12158)) {
                                stopRecording();
                            }
                            break;
                    }
                }
                return true;
            }
        }
        return super.onTouchEvent(event);
    }

    public void reset() {
        synchronized (recordingLock) {
            if (!ListenerUtil.mutListener.listen(12161)) {
                isRecording = false;
            }
            if (!ListenerUtil.mutListener.listen(12162)) {
                setImageResource(R.drawable.ic_shutter_button_normal);
            }
        }
    }

    public void setVideoEnable(boolean enable) {
        if (!ListenerUtil.mutListener.listen(12163)) {
            videoEnable = enable;
        }
    }

    public void setShutterButtonListener(@Nullable ShutterButtonListener shutterButtonListener) {
        synchronized (recordingLock) {
            if (!ListenerUtil.mutListener.listen(12165)) {
                if (!isRecording) {
                    if (!ListenerUtil.mutListener.listen(12164)) {
                        this.shutterButtonListener = shutterButtonListener;
                    }
                }
            }
        }
    }

    private void startRecording() {
        if (!ListenerUtil.mutListener.listen(12171)) {
            if (videoEnable) {
                synchronized (recordingLock) {
                    if (!ListenerUtil.mutListener.listen(12170)) {
                        if (!isRecording) {
                            if (!ListenerUtil.mutListener.listen(12166)) {
                                setImageResource(R.drawable.ic_shutter_button_recording);
                            }
                            if (!ListenerUtil.mutListener.listen(12167)) {
                                isRecording = true;
                            }
                            if (!ListenerUtil.mutListener.listen(12168)) {
                                shutterButtonListener.onRecordStart();
                            }
                            if (!ListenerUtil.mutListener.listen(12169)) {
                                recordingStartTime = System.currentTimeMillis();
                            }
                        }
                    }
                }
            }
        }
    }

    private void stopRecording() {
        if (!ListenerUtil.mutListener.listen(12192)) {
            if (videoEnable) {
                synchronized (recordingLock) {
                    if (!ListenerUtil.mutListener.listen(12191)) {
                        if (isRecording) {
                            long recordingLength = (ListenerUtil.mutListener.listen(12175) ? (System.currentTimeMillis() % recordingStartTime) : (ListenerUtil.mutListener.listen(12174) ? (System.currentTimeMillis() / recordingStartTime) : (ListenerUtil.mutListener.listen(12173) ? (System.currentTimeMillis() * recordingStartTime) : (ListenerUtil.mutListener.listen(12172) ? (System.currentTimeMillis() + recordingStartTime) : (System.currentTimeMillis() - recordingStartTime)))));
                            if (!ListenerUtil.mutListener.listen(12190)) {
                                // record at least 1 second to avoid race conditions in camerax code
                                if ((ListenerUtil.mutListener.listen(12180) ? (recordingLength >= DateUtils.SECOND_IN_MILLIS) : (ListenerUtil.mutListener.listen(12179) ? (recordingLength <= DateUtils.SECOND_IN_MILLIS) : (ListenerUtil.mutListener.listen(12178) ? (recordingLength > DateUtils.SECOND_IN_MILLIS) : (ListenerUtil.mutListener.listen(12177) ? (recordingLength != DateUtils.SECOND_IN_MILLIS) : (ListenerUtil.mutListener.listen(12176) ? (recordingLength == DateUtils.SECOND_IN_MILLIS) : (recordingLength < DateUtils.SECOND_IN_MILLIS))))))) {
                                    if (!ListenerUtil.mutListener.listen(12182)) {
                                        setImageResource(R.drawable.ic_shutter_button_normal);
                                    }
                                    if (!ListenerUtil.mutListener.listen(12189)) {
                                        new Handler().postDelayed(new Runnable() {

                                            @Override
                                            public void run() {
                                                if (!ListenerUtil.mutListener.listen(12184)) {
                                                    RuntimeUtil.runOnUiThread(new Runnable() {

                                                        @Override
                                                        public void run() {
                                                            if (!ListenerUtil.mutListener.listen(12183)) {
                                                                endRecording();
                                                            }
                                                        }
                                                    });
                                                }
                                            }
                                        }, (ListenerUtil.mutListener.listen(12188) ? (DateUtils.SECOND_IN_MILLIS % recordingLength) : (ListenerUtil.mutListener.listen(12187) ? (DateUtils.SECOND_IN_MILLIS / recordingLength) : (ListenerUtil.mutListener.listen(12186) ? (DateUtils.SECOND_IN_MILLIS * recordingLength) : (ListenerUtil.mutListener.listen(12185) ? (DateUtils.SECOND_IN_MILLIS + recordingLength) : (DateUtils.SECOND_IN_MILLIS - recordingLength))))));
                                    }
                                } else {
                                    if (!ListenerUtil.mutListener.listen(12181)) {
                                        endRecording();
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private void endRecording() {
        if (!ListenerUtil.mutListener.listen(12193)) {
            shutterButtonListener.onRecordEnd();
        }
        if (!ListenerUtil.mutListener.listen(12194)) {
            reset();
        }
    }

    private void changeZoomFactor(final float factor) {
        if (!ListenerUtil.mutListener.listen(12202)) {
            if (videoEnable) {
                synchronized (recordingLock) {
                    if (!ListenerUtil.mutListener.listen(12201)) {
                        if (isRecording) {
                            if (!ListenerUtil.mutListener.listen(12200)) {
                                shutterButtonListener.onZoomChanged((ListenerUtil.mutListener.listen(12199) ? (factor >= 0) : (ListenerUtil.mutListener.listen(12198) ? (factor <= 0) : (ListenerUtil.mutListener.listen(12197) ? (factor > 0) : (ListenerUtil.mutListener.listen(12196) ? (factor != 0) : (ListenerUtil.mutListener.listen(12195) ? (factor == 0) : (factor < 0)))))) ? 0 : factor);
                            }
                        }
                    }
                }
            }
        }
    }

    private void onClick() {
        if (!ListenerUtil.mutListener.listen(12206)) {
            if (!isRecording) {
                if (!ListenerUtil.mutListener.listen(12204)) {
                    shutterButtonListener.onClick();
                }
                if (!ListenerUtil.mutListener.listen(12205)) {
                    performClick();
                }
            } else {
                if (!ListenerUtil.mutListener.listen(12203)) {
                    stopRecording();
                }
            }
        }
    }

    /**
     *  Simulate a button click, including a small delay while it is being pressed to trigger the
     *  appropriate animations.
     */
    public void simulateClick() {
        if (!ListenerUtil.mutListener.listen(12207)) {
            onClick();
        }
        if (!ListenerUtil.mutListener.listen(12208)) {
            setPressed(true);
        }
        if (!ListenerUtil.mutListener.listen(12209)) {
            invalidate();
        }
        if (!ListenerUtil.mutListener.listen(12210)) {
            postDelayed(() -> {
                invalidate();
                setPressed(false);
            }, 50);
        }
    }

    interface ShutterButtonListener {

        void onRecordStart();

        void onRecordEnd();

        void onZoomChanged(float zoomFactor);

        void onClick();
    }
}
