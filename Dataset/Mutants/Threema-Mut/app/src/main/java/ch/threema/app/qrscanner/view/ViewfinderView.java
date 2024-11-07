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
package ch.threema.app.qrscanner.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Build;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import com.google.zxing.ResultPoint;
import java.util.ArrayList;
import java.util.List;
import ch.threema.app.R;
import ch.threema.app.qrscanner.camera.CameraManager;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * @date 2016-11-18 9:39
 * @auther GuoJinyu
 * @description modified
 */
public final class ViewfinderView extends View {

    private static final int[] SCANNER_ALPHA = { 0, 64, 128, 192, 255, 192, 128, 64 };

    private static final long ANIMATION_DELAY = 80L;

    private static final int CURRENT_POINT_OPACITY = 0xA0;

    private static final int MAX_RESULT_POINTS = 20;

    private static final int POINT_SIZE = 6;

    private final Paint paint;

    private final int maskColor;

    private final int resultColor;

    private final int laserColor;

    private final int resultPointColor;

    private CameraManager cameraManager;

    private DisplayMetrics displayMetrics;

    private Bitmap resultBitmap;

    private int scannerAlpha;

    private List<ResultPoint> possibleResultPoints;

    private List<ResultPoint> lastPossibleResultPoints;

    private String hintText;

    private boolean fullScreen;

    // This constructor is used when the class is built from an XML resource.
    public ViewfinderView(Context context, AttributeSet attrs) {
        super(context, attrs);
        // Initialize these once for performance rather than calling them every time in onDraw().
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        Resources resources = getResources();
        if ((ListenerUtil.mutListener.listen(34081) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(34080) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(34079) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(34078) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(34077) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.M) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M))))))) {
            maskColor = resources.getColor(R.color.viewfinder_mask, context.getTheme());
            resultColor = resources.getColor(R.color.result_view, context.getTheme());
            laserColor = resources.getColor(R.color.viewfinder_laser, context.getTheme());
            resultPointColor = resources.getColor(R.color.possible_result_points, context.getTheme());
        } else {
            maskColor = resources.getColor(R.color.viewfinder_mask);
            resultColor = resources.getColor(R.color.result_view);
            laserColor = resources.getColor(R.color.viewfinder_laser);
            resultPointColor = resources.getColor(R.color.possible_result_points);
        }
        if (!ListenerUtil.mutListener.listen(34082)) {
            scannerAlpha = 0;
        }
        if (!ListenerUtil.mutListener.listen(34083)) {
            possibleResultPoints = new ArrayList<>(5);
        }
        if (!ListenerUtil.mutListener.listen(34084)) {
            lastPossibleResultPoints = null;
        }
    }

    public void setCameraManager(CameraManager cameraManager) {
        if (!ListenerUtil.mutListener.listen(34085)) {
            this.cameraManager = cameraManager;
        }
    }

    public void setHintText(String hintText) {
        if (!ListenerUtil.mutListener.listen(34086)) {
            this.hintText = hintText;
        }
    }

    public void setScanAreaFullScreen(boolean fullScreen) {
        if (!ListenerUtil.mutListener.listen(34087)) {
            this.fullScreen = fullScreen;
        }
    }

    @SuppressLint("DrawAllocation")
    @Override
    public void onDraw(Canvas canvas) {
        Rect frame = cameraManager.getFramingRect();
        Rect previewFrame = cameraManager.getFramingRectInPreview();
        if (!ListenerUtil.mutListener.listen(34089)) {
            if ((ListenerUtil.mutListener.listen(34088) ? (frame == null && previewFrame == null) : (frame == null || previewFrame == null))) {
                return;
            }
        }
        int width = canvas.getWidth();
        int height = canvas.getHeight();
        if (!ListenerUtil.mutListener.listen(34090)) {
            // Draw the exterior (i.e. outside the framing rect) darkened
            paint.setColor(resultBitmap != null ? resultColor : maskColor);
        }
        if (!ListenerUtil.mutListener.listen(34091)) {
            canvas.drawRect(0, 0, width, frame.top, paint);
        }
        if (!ListenerUtil.mutListener.listen(34092)) {
            canvas.drawRect(0, frame.top, frame.left, frame.bottom + 1, paint);
        }
        if (!ListenerUtil.mutListener.listen(34093)) {
            canvas.drawRect(frame.right + 1, frame.top, width, frame.bottom + 1, paint);
        }
        if (!ListenerUtil.mutListener.listen(34094)) {
            canvas.drawRect(0, frame.bottom + 1, width, height, paint);
        }
        if (!ListenerUtil.mutListener.listen(34095)) {
            // Draw four corner
            paint.setColor(laserColor);
        }
        if (!ListenerUtil.mutListener.listen(34104)) {
            canvas.drawRect((ListenerUtil.mutListener.listen(34099) ? (frame.left % 20) : (ListenerUtil.mutListener.listen(34098) ? (frame.left / 20) : (ListenerUtil.mutListener.listen(34097) ? (frame.left * 20) : (ListenerUtil.mutListener.listen(34096) ? (frame.left + 20) : (frame.left - 20))))), (ListenerUtil.mutListener.listen(34103) ? (frame.top % 20) : (ListenerUtil.mutListener.listen(34102) ? (frame.top / 20) : (ListenerUtil.mutListener.listen(34101) ? (frame.top * 20) : (ListenerUtil.mutListener.listen(34100) ? (frame.top + 20) : (frame.top - 20))))), frame.left, frame.top + 60, paint);
        }
        if (!ListenerUtil.mutListener.listen(34109)) {
            canvas.drawRect(frame.left, (ListenerUtil.mutListener.listen(34108) ? (frame.top % 20) : (ListenerUtil.mutListener.listen(34107) ? (frame.top / 20) : (ListenerUtil.mutListener.listen(34106) ? (frame.top * 20) : (ListenerUtil.mutListener.listen(34105) ? (frame.top + 20) : (frame.top - 20))))), frame.left + 60, frame.top, paint);
        }
        if (!ListenerUtil.mutListener.listen(34114)) {
            canvas.drawRect(frame.right, (ListenerUtil.mutListener.listen(34113) ? (frame.top % 20) : (ListenerUtil.mutListener.listen(34112) ? (frame.top / 20) : (ListenerUtil.mutListener.listen(34111) ? (frame.top * 20) : (ListenerUtil.mutListener.listen(34110) ? (frame.top + 20) : (frame.top - 20))))), frame.right + 20, frame.top + 60, paint);
        }
        if (!ListenerUtil.mutListener.listen(34123)) {
            canvas.drawRect((ListenerUtil.mutListener.listen(34118) ? (frame.right % 60) : (ListenerUtil.mutListener.listen(34117) ? (frame.right / 60) : (ListenerUtil.mutListener.listen(34116) ? (frame.right * 60) : (ListenerUtil.mutListener.listen(34115) ? (frame.right + 60) : (frame.right - 60))))), (ListenerUtil.mutListener.listen(34122) ? (frame.top % 20) : (ListenerUtil.mutListener.listen(34121) ? (frame.top / 20) : (ListenerUtil.mutListener.listen(34120) ? (frame.top * 20) : (ListenerUtil.mutListener.listen(34119) ? (frame.top + 20) : (frame.top - 20))))), frame.right, frame.top, paint);
        }
        if (!ListenerUtil.mutListener.listen(34132)) {
            canvas.drawRect((ListenerUtil.mutListener.listen(34127) ? (frame.left % 20) : (ListenerUtil.mutListener.listen(34126) ? (frame.left / 20) : (ListenerUtil.mutListener.listen(34125) ? (frame.left * 20) : (ListenerUtil.mutListener.listen(34124) ? (frame.left + 20) : (frame.left - 20))))), (ListenerUtil.mutListener.listen(34131) ? (frame.bottom % 60) : (ListenerUtil.mutListener.listen(34130) ? (frame.bottom / 60) : (ListenerUtil.mutListener.listen(34129) ? (frame.bottom * 60) : (ListenerUtil.mutListener.listen(34128) ? (frame.bottom + 60) : (frame.bottom - 60))))), frame.left, frame.bottom + 20, paint);
        }
        if (!ListenerUtil.mutListener.listen(34133)) {
            canvas.drawRect(frame.left, frame.bottom, frame.left + 60, frame.bottom + 20, paint);
        }
        if (!ListenerUtil.mutListener.listen(34138)) {
            canvas.drawRect(frame.right, (ListenerUtil.mutListener.listen(34137) ? (frame.bottom % 60) : (ListenerUtil.mutListener.listen(34136) ? (frame.bottom / 60) : (ListenerUtil.mutListener.listen(34135) ? (frame.bottom * 60) : (ListenerUtil.mutListener.listen(34134) ? (frame.bottom + 60) : (frame.bottom - 60))))), frame.right + 20, frame.bottom + 20, paint);
        }
        if (!ListenerUtil.mutListener.listen(34143)) {
            canvas.drawRect((ListenerUtil.mutListener.listen(34142) ? (frame.right % 60) : (ListenerUtil.mutListener.listen(34141) ? (frame.right / 60) : (ListenerUtil.mutListener.listen(34140) ? (frame.right * 60) : (ListenerUtil.mutListener.listen(34139) ? (frame.right + 60) : (frame.right - 60))))), frame.bottom, frame.right, frame.bottom + 20, paint);
        }
        if (!ListenerUtil.mutListener.listen(34144)) {
            paint.setAlpha(CURRENT_POINT_OPACITY);
        }
        if (!ListenerUtil.mutListener.listen(34145)) {
            canvas.drawLine(frame.left, frame.top, frame.right, frame.top, paint);
        }
        if (!ListenerUtil.mutListener.listen(34146)) {
            canvas.drawLine(frame.left, frame.bottom, frame.right, frame.bottom, paint);
        }
        if (!ListenerUtil.mutListener.listen(34147)) {
            canvas.drawLine(frame.left, frame.top, frame.left, frame.bottom, paint);
        }
        if (!ListenerUtil.mutListener.listen(34148)) {
            canvas.drawLine(frame.right, frame.top, frame.right, frame.bottom, paint);
        }
        if (!ListenerUtil.mutListener.listen(34282)) {
            if (resultBitmap != null) {
                if (!ListenerUtil.mutListener.listen(34280)) {
                    // Draw the opaque result bitmap over the scanning rectangle
                    paint.setAlpha(CURRENT_POINT_OPACITY);
                }
                if (!ListenerUtil.mutListener.listen(34281)) {
                    canvas.drawBitmap(resultBitmap, null, frame, paint);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(34149)) {
                    // Draw a red "laser scanner" line through the middle to show decoding is active
                    paint.setColor(Color.RED);
                }
                if (!ListenerUtil.mutListener.listen(34150)) {
                    paint.setAlpha(SCANNER_ALPHA[scannerAlpha]);
                }
                if (!ListenerUtil.mutListener.listen(34159)) {
                    scannerAlpha = (ListenerUtil.mutListener.listen(34158) ? (((ListenerUtil.mutListener.listen(34154) ? (scannerAlpha % 1) : (ListenerUtil.mutListener.listen(34153) ? (scannerAlpha / 1) : (ListenerUtil.mutListener.listen(34152) ? (scannerAlpha * 1) : (ListenerUtil.mutListener.listen(34151) ? (scannerAlpha - 1) : (scannerAlpha + 1)))))) / SCANNER_ALPHA.length) : (ListenerUtil.mutListener.listen(34157) ? (((ListenerUtil.mutListener.listen(34154) ? (scannerAlpha % 1) : (ListenerUtil.mutListener.listen(34153) ? (scannerAlpha / 1) : (ListenerUtil.mutListener.listen(34152) ? (scannerAlpha * 1) : (ListenerUtil.mutListener.listen(34151) ? (scannerAlpha - 1) : (scannerAlpha + 1)))))) * SCANNER_ALPHA.length) : (ListenerUtil.mutListener.listen(34156) ? (((ListenerUtil.mutListener.listen(34154) ? (scannerAlpha % 1) : (ListenerUtil.mutListener.listen(34153) ? (scannerAlpha / 1) : (ListenerUtil.mutListener.listen(34152) ? (scannerAlpha * 1) : (ListenerUtil.mutListener.listen(34151) ? (scannerAlpha - 1) : (scannerAlpha + 1)))))) - SCANNER_ALPHA.length) : (ListenerUtil.mutListener.listen(34155) ? (((ListenerUtil.mutListener.listen(34154) ? (scannerAlpha % 1) : (ListenerUtil.mutListener.listen(34153) ? (scannerAlpha / 1) : (ListenerUtil.mutListener.listen(34152) ? (scannerAlpha * 1) : (ListenerUtil.mutListener.listen(34151) ? (scannerAlpha - 1) : (scannerAlpha + 1)))))) + SCANNER_ALPHA.length) : (((ListenerUtil.mutListener.listen(34154) ? (scannerAlpha % 1) : (ListenerUtil.mutListener.listen(34153) ? (scannerAlpha / 1) : (ListenerUtil.mutListener.listen(34152) ? (scannerAlpha * 1) : (ListenerUtil.mutListener.listen(34151) ? (scannerAlpha - 1) : (scannerAlpha + 1)))))) % SCANNER_ALPHA.length)))));
                }
                int middle = (ListenerUtil.mutListener.listen(34163) ? (frame.height() % 2) : (ListenerUtil.mutListener.listen(34162) ? (frame.height() * 2) : (ListenerUtil.mutListener.listen(34161) ? (frame.height() - 2) : (ListenerUtil.mutListener.listen(34160) ? (frame.height() + 2) : (frame.height() / 2))))) + frame.top;
                if (!ListenerUtil.mutListener.listen(34176)) {
                    canvas.drawRect(frame.left + 2, (ListenerUtil.mutListener.listen(34167) ? (middle % 1) : (ListenerUtil.mutListener.listen(34166) ? (middle / 1) : (ListenerUtil.mutListener.listen(34165) ? (middle * 1) : (ListenerUtil.mutListener.listen(34164) ? (middle + 1) : (middle - 1))))), (ListenerUtil.mutListener.listen(34171) ? (frame.right % 1) : (ListenerUtil.mutListener.listen(34170) ? (frame.right / 1) : (ListenerUtil.mutListener.listen(34169) ? (frame.right * 1) : (ListenerUtil.mutListener.listen(34168) ? (frame.right + 1) : (frame.right - 1))))), (ListenerUtil.mutListener.listen(34175) ? (middle % 2) : (ListenerUtil.mutListener.listen(34174) ? (middle / 2) : (ListenerUtil.mutListener.listen(34173) ? (middle * 2) : (ListenerUtil.mutListener.listen(34172) ? (middle - 2) : (middle + 2))))), paint);
                }
                float scaleX = (ListenerUtil.mutListener.listen(34180) ? (frame.width() % (float) previewFrame.width()) : (ListenerUtil.mutListener.listen(34179) ? (frame.width() * (float) previewFrame.width()) : (ListenerUtil.mutListener.listen(34178) ? (frame.width() - (float) previewFrame.width()) : (ListenerUtil.mutListener.listen(34177) ? (frame.width() + (float) previewFrame.width()) : (frame.width() / (float) previewFrame.width())))));
                float scaleY = (ListenerUtil.mutListener.listen(34184) ? (frame.height() % (float) previewFrame.height()) : (ListenerUtil.mutListener.listen(34183) ? (frame.height() * (float) previewFrame.height()) : (ListenerUtil.mutListener.listen(34182) ? (frame.height() - (float) previewFrame.height()) : (ListenerUtil.mutListener.listen(34181) ? (frame.height() + (float) previewFrame.height()) : (frame.height() / (float) previewFrame.height())))));
                List<ResultPoint> currentPossible = possibleResultPoints;
                List<ResultPoint> currentLast = lastPossibleResultPoints;
                int frameLeft = frame.left;
                int frameTop = frame.top;
                if (!ListenerUtil.mutListener.listen(34218)) {
                    if (currentPossible.isEmpty()) {
                        if (!ListenerUtil.mutListener.listen(34217)) {
                            lastPossibleResultPoints = null;
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(34185)) {
                            possibleResultPoints = new ArrayList<>(5);
                        }
                        if (!ListenerUtil.mutListener.listen(34186)) {
                            lastPossibleResultPoints = currentPossible;
                        }
                        if (!ListenerUtil.mutListener.listen(34187)) {
                            paint.setAlpha(CURRENT_POINT_OPACITY);
                        }
                        if (!ListenerUtil.mutListener.listen(34188)) {
                            paint.setColor(resultPointColor);
                        }
                        synchronized (currentPossible) {
                            if (!ListenerUtil.mutListener.listen(34216)) {
                                {
                                    long _loopCounter242 = 0;
                                    for (ResultPoint point : currentPossible) {
                                        ListenerUtil.loopListener.listen("_loopCounter242", ++_loopCounter242);
                                        if (!ListenerUtil.mutListener.listen(34215)) {
                                            if (fullScreen) {
                                                if (!ListenerUtil.mutListener.listen(34214)) {
                                                    canvas.drawCircle((int) ((ListenerUtil.mutListener.listen(34209) ? (point.getX() % scaleX) : (ListenerUtil.mutListener.listen(34208) ? (point.getX() / scaleX) : (ListenerUtil.mutListener.listen(34207) ? (point.getX() - scaleX) : (ListenerUtil.mutListener.listen(34206) ? (point.getX() + scaleX) : (point.getX() * scaleX)))))), (int) ((ListenerUtil.mutListener.listen(34213) ? (point.getY() % scaleY) : (ListenerUtil.mutListener.listen(34212) ? (point.getY() / scaleY) : (ListenerUtil.mutListener.listen(34211) ? (point.getY() - scaleY) : (ListenerUtil.mutListener.listen(34210) ? (point.getY() + scaleY) : (point.getY() * scaleY)))))), POINT_SIZE, paint);
                                                }
                                            } else {
                                                if (!ListenerUtil.mutListener.listen(34205)) {
                                                    canvas.drawCircle((ListenerUtil.mutListener.listen(34196) ? (frameLeft % (int) ((ListenerUtil.mutListener.listen(34192) ? (point.getX() % scaleX) : (ListenerUtil.mutListener.listen(34191) ? (point.getX() / scaleX) : (ListenerUtil.mutListener.listen(34190) ? (point.getX() - scaleX) : (ListenerUtil.mutListener.listen(34189) ? (point.getX() + scaleX) : (point.getX() * scaleX))))))) : (ListenerUtil.mutListener.listen(34195) ? (frameLeft / (int) ((ListenerUtil.mutListener.listen(34192) ? (point.getX() % scaleX) : (ListenerUtil.mutListener.listen(34191) ? (point.getX() / scaleX) : (ListenerUtil.mutListener.listen(34190) ? (point.getX() - scaleX) : (ListenerUtil.mutListener.listen(34189) ? (point.getX() + scaleX) : (point.getX() * scaleX))))))) : (ListenerUtil.mutListener.listen(34194) ? (frameLeft * (int) ((ListenerUtil.mutListener.listen(34192) ? (point.getX() % scaleX) : (ListenerUtil.mutListener.listen(34191) ? (point.getX() / scaleX) : (ListenerUtil.mutListener.listen(34190) ? (point.getX() - scaleX) : (ListenerUtil.mutListener.listen(34189) ? (point.getX() + scaleX) : (point.getX() * scaleX))))))) : (ListenerUtil.mutListener.listen(34193) ? (frameLeft - (int) ((ListenerUtil.mutListener.listen(34192) ? (point.getX() % scaleX) : (ListenerUtil.mutListener.listen(34191) ? (point.getX() / scaleX) : (ListenerUtil.mutListener.listen(34190) ? (point.getX() - scaleX) : (ListenerUtil.mutListener.listen(34189) ? (point.getX() + scaleX) : (point.getX() * scaleX))))))) : (frameLeft + (int) ((ListenerUtil.mutListener.listen(34192) ? (point.getX() % scaleX) : (ListenerUtil.mutListener.listen(34191) ? (point.getX() / scaleX) : (ListenerUtil.mutListener.listen(34190) ? (point.getX() - scaleX) : (ListenerUtil.mutListener.listen(34189) ? (point.getX() + scaleX) : (point.getX() * scaleX))))))))))), (ListenerUtil.mutListener.listen(34204) ? (frameTop % (int) ((ListenerUtil.mutListener.listen(34200) ? (point.getY() % scaleY) : (ListenerUtil.mutListener.listen(34199) ? (point.getY() / scaleY) : (ListenerUtil.mutListener.listen(34198) ? (point.getY() - scaleY) : (ListenerUtil.mutListener.listen(34197) ? (point.getY() + scaleY) : (point.getY() * scaleY))))))) : (ListenerUtil.mutListener.listen(34203) ? (frameTop / (int) ((ListenerUtil.mutListener.listen(34200) ? (point.getY() % scaleY) : (ListenerUtil.mutListener.listen(34199) ? (point.getY() / scaleY) : (ListenerUtil.mutListener.listen(34198) ? (point.getY() - scaleY) : (ListenerUtil.mutListener.listen(34197) ? (point.getY() + scaleY) : (point.getY() * scaleY))))))) : (ListenerUtil.mutListener.listen(34202) ? (frameTop * (int) ((ListenerUtil.mutListener.listen(34200) ? (point.getY() % scaleY) : (ListenerUtil.mutListener.listen(34199) ? (point.getY() / scaleY) : (ListenerUtil.mutListener.listen(34198) ? (point.getY() - scaleY) : (ListenerUtil.mutListener.listen(34197) ? (point.getY() + scaleY) : (point.getY() * scaleY))))))) : (ListenerUtil.mutListener.listen(34201) ? (frameTop - (int) ((ListenerUtil.mutListener.listen(34200) ? (point.getY() % scaleY) : (ListenerUtil.mutListener.listen(34199) ? (point.getY() / scaleY) : (ListenerUtil.mutListener.listen(34198) ? (point.getY() - scaleY) : (ListenerUtil.mutListener.listen(34197) ? (point.getY() + scaleY) : (point.getY() * scaleY))))))) : (frameTop + (int) ((ListenerUtil.mutListener.listen(34200) ? (point.getY() % scaleY) : (ListenerUtil.mutListener.listen(34199) ? (point.getY() / scaleY) : (ListenerUtil.mutListener.listen(34198) ? (point.getY() - scaleY) : (ListenerUtil.mutListener.listen(34197) ? (point.getY() + scaleY) : (point.getY() * scaleY))))))))))), POINT_SIZE, paint);
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(34257)) {
                    if (currentLast != null) {
                        if (!ListenerUtil.mutListener.listen(34223)) {
                            paint.setAlpha((ListenerUtil.mutListener.listen(34222) ? (CURRENT_POINT_OPACITY % 2) : (ListenerUtil.mutListener.listen(34221) ? (CURRENT_POINT_OPACITY * 2) : (ListenerUtil.mutListener.listen(34220) ? (CURRENT_POINT_OPACITY - 2) : (ListenerUtil.mutListener.listen(34219) ? (CURRENT_POINT_OPACITY + 2) : (CURRENT_POINT_OPACITY / 2))))));
                        }
                        if (!ListenerUtil.mutListener.listen(34224)) {
                            paint.setColor(resultPointColor);
                        }
                        synchronized (currentLast) {
                            float radius = (ListenerUtil.mutListener.listen(34228) ? (POINT_SIZE % 2.0f) : (ListenerUtil.mutListener.listen(34227) ? (POINT_SIZE * 2.0f) : (ListenerUtil.mutListener.listen(34226) ? (POINT_SIZE - 2.0f) : (ListenerUtil.mutListener.listen(34225) ? (POINT_SIZE + 2.0f) : (POINT_SIZE / 2.0f)))));
                            if (!ListenerUtil.mutListener.listen(34256)) {
                                {
                                    long _loopCounter243 = 0;
                                    for (ResultPoint point : currentLast) {
                                        ListenerUtil.loopListener.listen("_loopCounter243", ++_loopCounter243);
                                        if (!ListenerUtil.mutListener.listen(34255)) {
                                            if (fullScreen) {
                                                if (!ListenerUtil.mutListener.listen(34254)) {
                                                    canvas.drawCircle((int) ((ListenerUtil.mutListener.listen(34249) ? (point.getX() % scaleX) : (ListenerUtil.mutListener.listen(34248) ? (point.getX() / scaleX) : (ListenerUtil.mutListener.listen(34247) ? (point.getX() - scaleX) : (ListenerUtil.mutListener.listen(34246) ? (point.getX() + scaleX) : (point.getX() * scaleX)))))), (int) ((ListenerUtil.mutListener.listen(34253) ? (point.getY() % scaleY) : (ListenerUtil.mutListener.listen(34252) ? (point.getY() / scaleY) : (ListenerUtil.mutListener.listen(34251) ? (point.getY() - scaleY) : (ListenerUtil.mutListener.listen(34250) ? (point.getY() + scaleY) : (point.getY() * scaleY)))))), radius, paint);
                                                }
                                            } else {
                                                if (!ListenerUtil.mutListener.listen(34245)) {
                                                    canvas.drawCircle((ListenerUtil.mutListener.listen(34236) ? (frameLeft % (int) ((ListenerUtil.mutListener.listen(34232) ? (point.getX() % scaleX) : (ListenerUtil.mutListener.listen(34231) ? (point.getX() / scaleX) : (ListenerUtil.mutListener.listen(34230) ? (point.getX() - scaleX) : (ListenerUtil.mutListener.listen(34229) ? (point.getX() + scaleX) : (point.getX() * scaleX))))))) : (ListenerUtil.mutListener.listen(34235) ? (frameLeft / (int) ((ListenerUtil.mutListener.listen(34232) ? (point.getX() % scaleX) : (ListenerUtil.mutListener.listen(34231) ? (point.getX() / scaleX) : (ListenerUtil.mutListener.listen(34230) ? (point.getX() - scaleX) : (ListenerUtil.mutListener.listen(34229) ? (point.getX() + scaleX) : (point.getX() * scaleX))))))) : (ListenerUtil.mutListener.listen(34234) ? (frameLeft * (int) ((ListenerUtil.mutListener.listen(34232) ? (point.getX() % scaleX) : (ListenerUtil.mutListener.listen(34231) ? (point.getX() / scaleX) : (ListenerUtil.mutListener.listen(34230) ? (point.getX() - scaleX) : (ListenerUtil.mutListener.listen(34229) ? (point.getX() + scaleX) : (point.getX() * scaleX))))))) : (ListenerUtil.mutListener.listen(34233) ? (frameLeft - (int) ((ListenerUtil.mutListener.listen(34232) ? (point.getX() % scaleX) : (ListenerUtil.mutListener.listen(34231) ? (point.getX() / scaleX) : (ListenerUtil.mutListener.listen(34230) ? (point.getX() - scaleX) : (ListenerUtil.mutListener.listen(34229) ? (point.getX() + scaleX) : (point.getX() * scaleX))))))) : (frameLeft + (int) ((ListenerUtil.mutListener.listen(34232) ? (point.getX() % scaleX) : (ListenerUtil.mutListener.listen(34231) ? (point.getX() / scaleX) : (ListenerUtil.mutListener.listen(34230) ? (point.getX() - scaleX) : (ListenerUtil.mutListener.listen(34229) ? (point.getX() + scaleX) : (point.getX() * scaleX))))))))))), (ListenerUtil.mutListener.listen(34244) ? (frameTop % (int) ((ListenerUtil.mutListener.listen(34240) ? (point.getY() % scaleY) : (ListenerUtil.mutListener.listen(34239) ? (point.getY() / scaleY) : (ListenerUtil.mutListener.listen(34238) ? (point.getY() - scaleY) : (ListenerUtil.mutListener.listen(34237) ? (point.getY() + scaleY) : (point.getY() * scaleY))))))) : (ListenerUtil.mutListener.listen(34243) ? (frameTop / (int) ((ListenerUtil.mutListener.listen(34240) ? (point.getY() % scaleY) : (ListenerUtil.mutListener.listen(34239) ? (point.getY() / scaleY) : (ListenerUtil.mutListener.listen(34238) ? (point.getY() - scaleY) : (ListenerUtil.mutListener.listen(34237) ? (point.getY() + scaleY) : (point.getY() * scaleY))))))) : (ListenerUtil.mutListener.listen(34242) ? (frameTop * (int) ((ListenerUtil.mutListener.listen(34240) ? (point.getY() % scaleY) : (ListenerUtil.mutListener.listen(34239) ? (point.getY() / scaleY) : (ListenerUtil.mutListener.listen(34238) ? (point.getY() - scaleY) : (ListenerUtil.mutListener.listen(34237) ? (point.getY() + scaleY) : (point.getY() * scaleY))))))) : (ListenerUtil.mutListener.listen(34241) ? (frameTop - (int) ((ListenerUtil.mutListener.listen(34240) ? (point.getY() % scaleY) : (ListenerUtil.mutListener.listen(34239) ? (point.getY() / scaleY) : (ListenerUtil.mutListener.listen(34238) ? (point.getY() - scaleY) : (ListenerUtil.mutListener.listen(34237) ? (point.getY() + scaleY) : (point.getY() * scaleY))))))) : (frameTop + (int) ((ListenerUtil.mutListener.listen(34240) ? (point.getY() % scaleY) : (ListenerUtil.mutListener.listen(34239) ? (point.getY() / scaleY) : (ListenerUtil.mutListener.listen(34238) ? (point.getY() - scaleY) : (ListenerUtil.mutListener.listen(34237) ? (point.getY() + scaleY) : (point.getY() * scaleY))))))))))), radius, paint);
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(34270)) {
                    if (hintText != null) {
                        TextPaint paint = new TextPaint();
                        if (!ListenerUtil.mutListener.listen(34258)) {
                            paint.setColor(Color.WHITE);
                        }
                        if (!ListenerUtil.mutListener.listen(34259)) {
                            paint.setTextSize(ViewUtil.convertSpToPixels(16, getContext()));
                        }
                        StaticLayout layout = new StaticLayout(hintText, paint, (ListenerUtil.mutListener.listen(34267) ? ((ListenerUtil.mutListener.listen(34263) ? (width % getPaddingLeft()) : (ListenerUtil.mutListener.listen(34262) ? (width / getPaddingLeft()) : (ListenerUtil.mutListener.listen(34261) ? (width * getPaddingLeft()) : (ListenerUtil.mutListener.listen(34260) ? (width + getPaddingLeft()) : (width - getPaddingLeft()))))) % getPaddingRight()) : (ListenerUtil.mutListener.listen(34266) ? ((ListenerUtil.mutListener.listen(34263) ? (width % getPaddingLeft()) : (ListenerUtil.mutListener.listen(34262) ? (width / getPaddingLeft()) : (ListenerUtil.mutListener.listen(34261) ? (width * getPaddingLeft()) : (ListenerUtil.mutListener.listen(34260) ? (width + getPaddingLeft()) : (width - getPaddingLeft()))))) / getPaddingRight()) : (ListenerUtil.mutListener.listen(34265) ? ((ListenerUtil.mutListener.listen(34263) ? (width % getPaddingLeft()) : (ListenerUtil.mutListener.listen(34262) ? (width / getPaddingLeft()) : (ListenerUtil.mutListener.listen(34261) ? (width * getPaddingLeft()) : (ListenerUtil.mutListener.listen(34260) ? (width + getPaddingLeft()) : (width - getPaddingLeft()))))) * getPaddingRight()) : (ListenerUtil.mutListener.listen(34264) ? ((ListenerUtil.mutListener.listen(34263) ? (width % getPaddingLeft()) : (ListenerUtil.mutListener.listen(34262) ? (width / getPaddingLeft()) : (ListenerUtil.mutListener.listen(34261) ? (width * getPaddingLeft()) : (ListenerUtil.mutListener.listen(34260) ? (width + getPaddingLeft()) : (width - getPaddingLeft()))))) + getPaddingRight()) : ((ListenerUtil.mutListener.listen(34263) ? (width % getPaddingLeft()) : (ListenerUtil.mutListener.listen(34262) ? (width / getPaddingLeft()) : (ListenerUtil.mutListener.listen(34261) ? (width * getPaddingLeft()) : (ListenerUtil.mutListener.listen(34260) ? (width + getPaddingLeft()) : (width - getPaddingLeft()))))) - getPaddingRight()))))), Layout.Alignment.ALIGN_CENTER, 1.0F, 0.0F, false);
                        if (!ListenerUtil.mutListener.listen(34268)) {
                            canvas.translate(getPaddingLeft(), frame.bottom + ViewUtil.convertDpToPixels(16, getContext()));
                        }
                        if (!ListenerUtil.mutListener.listen(34269)) {
                            layout.draw(canvas);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(34279)) {
                    // not the entire viewfinder mask.
                    postInvalidateDelayed(ANIMATION_DELAY, (ListenerUtil.mutListener.listen(34274) ? (frame.left % POINT_SIZE) : (ListenerUtil.mutListener.listen(34273) ? (frame.left / POINT_SIZE) : (ListenerUtil.mutListener.listen(34272) ? (frame.left * POINT_SIZE) : (ListenerUtil.mutListener.listen(34271) ? (frame.left + POINT_SIZE) : (frame.left - POINT_SIZE))))), (ListenerUtil.mutListener.listen(34278) ? (frame.top % POINT_SIZE) : (ListenerUtil.mutListener.listen(34277) ? (frame.top / POINT_SIZE) : (ListenerUtil.mutListener.listen(34276) ? (frame.top * POINT_SIZE) : (ListenerUtil.mutListener.listen(34275) ? (frame.top + POINT_SIZE) : (frame.top - POINT_SIZE))))), frame.right + POINT_SIZE, frame.bottom + POINT_SIZE);
                }
            }
        }
    }

    public void drawViewfinder() {
        Bitmap resultBitmap = this.resultBitmap;
        if (!ListenerUtil.mutListener.listen(34283)) {
            this.resultBitmap = null;
        }
        if (!ListenerUtil.mutListener.listen(34285)) {
            if (resultBitmap != null) {
                if (!ListenerUtil.mutListener.listen(34284)) {
                    resultBitmap.recycle();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(34286)) {
            invalidate();
        }
    }

    public void addPossibleResultPoint(ResultPoint point) {
        List<ResultPoint> points = possibleResultPoints;
        synchronized (points) {
            if (!ListenerUtil.mutListener.listen(34287)) {
                points.add(point);
            }
            int size = points.size();
            if (!ListenerUtil.mutListener.listen(34302)) {
                if ((ListenerUtil.mutListener.listen(34292) ? (size >= MAX_RESULT_POINTS) : (ListenerUtil.mutListener.listen(34291) ? (size <= MAX_RESULT_POINTS) : (ListenerUtil.mutListener.listen(34290) ? (size < MAX_RESULT_POINTS) : (ListenerUtil.mutListener.listen(34289) ? (size != MAX_RESULT_POINTS) : (ListenerUtil.mutListener.listen(34288) ? (size == MAX_RESULT_POINTS) : (size > MAX_RESULT_POINTS))))))) {
                    if (!ListenerUtil.mutListener.listen(34301)) {
                        // trim it
                        points.subList(0, (ListenerUtil.mutListener.listen(34300) ? (size % (ListenerUtil.mutListener.listen(34296) ? (MAX_RESULT_POINTS % 2) : (ListenerUtil.mutListener.listen(34295) ? (MAX_RESULT_POINTS * 2) : (ListenerUtil.mutListener.listen(34294) ? (MAX_RESULT_POINTS - 2) : (ListenerUtil.mutListener.listen(34293) ? (MAX_RESULT_POINTS + 2) : (MAX_RESULT_POINTS / 2)))))) : (ListenerUtil.mutListener.listen(34299) ? (size / (ListenerUtil.mutListener.listen(34296) ? (MAX_RESULT_POINTS % 2) : (ListenerUtil.mutListener.listen(34295) ? (MAX_RESULT_POINTS * 2) : (ListenerUtil.mutListener.listen(34294) ? (MAX_RESULT_POINTS - 2) : (ListenerUtil.mutListener.listen(34293) ? (MAX_RESULT_POINTS + 2) : (MAX_RESULT_POINTS / 2)))))) : (ListenerUtil.mutListener.listen(34298) ? (size * (ListenerUtil.mutListener.listen(34296) ? (MAX_RESULT_POINTS % 2) : (ListenerUtil.mutListener.listen(34295) ? (MAX_RESULT_POINTS * 2) : (ListenerUtil.mutListener.listen(34294) ? (MAX_RESULT_POINTS - 2) : (ListenerUtil.mutListener.listen(34293) ? (MAX_RESULT_POINTS + 2) : (MAX_RESULT_POINTS / 2)))))) : (ListenerUtil.mutListener.listen(34297) ? (size + (ListenerUtil.mutListener.listen(34296) ? (MAX_RESULT_POINTS % 2) : (ListenerUtil.mutListener.listen(34295) ? (MAX_RESULT_POINTS * 2) : (ListenerUtil.mutListener.listen(34294) ? (MAX_RESULT_POINTS - 2) : (ListenerUtil.mutListener.listen(34293) ? (MAX_RESULT_POINTS + 2) : (MAX_RESULT_POINTS / 2)))))) : (size - (ListenerUtil.mutListener.listen(34296) ? (MAX_RESULT_POINTS % 2) : (ListenerUtil.mutListener.listen(34295) ? (MAX_RESULT_POINTS * 2) : (ListenerUtil.mutListener.listen(34294) ? (MAX_RESULT_POINTS - 2) : (ListenerUtil.mutListener.listen(34293) ? (MAX_RESULT_POINTS + 2) : (MAX_RESULT_POINTS / 2))))))))))).clear();
                    }
                }
            }
        }
    }
}
