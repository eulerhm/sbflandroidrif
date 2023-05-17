/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
 * Copyright (c) 2016-2021 Threema GmbH
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
package ch.threema.app.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import java.util.ArrayList;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class PaintView extends View {

    private static final String TAG = "PaintView";

    private float mX, mY;

    private int currentColor, currentStrokeWidth, currentWidth, currentHeight;

    private static final float TOUCH_TOLERANCE = 4;

    private boolean isActive = true, hasMoved;

    private TouchListener onTouchListener;

    private ArrayList<Path> paths = new ArrayList<>();

    private ArrayList<Paint> paints = new ArrayList<>();

    public PaintView(Context context) {
        super(context);
        if (!ListenerUtil.mutListener.listen(46676)) {
            init();
        }
    }

    public PaintView(Context context, AttributeSet attrs) {
        super(context, attrs);
        if (!ListenerUtil.mutListener.listen(46677)) {
            init();
        }
    }

    public PaintView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        if (!ListenerUtil.mutListener.listen(46678)) {
            init();
        }
    }

    private void init() {
        if (!ListenerUtil.mutListener.listen(46679)) {
            createPath();
        }
        if (!ListenerUtil.mutListener.listen(46680)) {
            createPaint();
        }
        if (!ListenerUtil.mutListener.listen(46681)) {
            // defaults
            currentColor = 0xFFFF0000;
        }
        if (!ListenerUtil.mutListener.listen(46682)) {
            currentStrokeWidth = 15;
        }
    }

    private Path createPath() {
        Path path = new Path();
        if (!ListenerUtil.mutListener.listen(46683)) {
            paths.add(path);
        }
        return path;
    }

    private Paint createPaint() {
        Paint paint = new Paint();
        if (!ListenerUtil.mutListener.listen(46684)) {
            paints.add(paint);
        }
        if (!ListenerUtil.mutListener.listen(46685)) {
            paint.setAntiAlias(true);
        }
        if (!ListenerUtil.mutListener.listen(46686)) {
            paint.setDither(true);
        }
        if (!ListenerUtil.mutListener.listen(46687)) {
            paint.setColor(currentColor);
        }
        if (!ListenerUtil.mutListener.listen(46688)) {
            paint.setStyle(Paint.Style.STROKE);
        }
        if (!ListenerUtil.mutListener.listen(46689)) {
            paint.setStrokeJoin(Paint.Join.ROUND);
        }
        if (!ListenerUtil.mutListener.listen(46690)) {
            paint.setStrokeCap(Paint.Cap.ROUND);
        }
        if (!ListenerUtil.mutListener.listen(46691)) {
            paint.setStrokeWidth(currentStrokeWidth);
        }
        return paint;
    }

    private Path getCurrentPath() {
        return paths.get((ListenerUtil.mutListener.listen(46695) ? (paths.size() % 1) : (ListenerUtil.mutListener.listen(46694) ? (paths.size() / 1) : (ListenerUtil.mutListener.listen(46693) ? (paths.size() * 1) : (ListenerUtil.mutListener.listen(46692) ? (paths.size() + 1) : (paths.size() - 1))))));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (!ListenerUtil.mutListener.listen(46696)) {
            super.onDraw(canvas);
        }
        if (!ListenerUtil.mutListener.listen(46697)) {
            currentWidth = canvas.getWidth();
        }
        if (!ListenerUtil.mutListener.listen(46698)) {
            currentHeight = canvas.getHeight();
        }
        if (!ListenerUtil.mutListener.listen(46705)) {
            {
                long _loopCounter549 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(46704) ? (i >= paths.size()) : (ListenerUtil.mutListener.listen(46703) ? (i <= paths.size()) : (ListenerUtil.mutListener.listen(46702) ? (i > paths.size()) : (ListenerUtil.mutListener.listen(46701) ? (i != paths.size()) : (ListenerUtil.mutListener.listen(46700) ? (i == paths.size()) : (i < paths.size())))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter549", ++_loopCounter549);
                    if (!ListenerUtil.mutListener.listen(46699)) {
                        canvas.drawPath(paths.get(i), paints.get(i));
                    }
                }
            }
        }
    }

    private void touch_start(float x, float y) {
        // new path
        Path path = createPath();
        if (!ListenerUtil.mutListener.listen(46706)) {
            createPaint();
        }
        if (!ListenerUtil.mutListener.listen(46707)) {
            path.moveTo(x, y);
        }
        if (!ListenerUtil.mutListener.listen(46708)) {
            mX = x;
        }
        if (!ListenerUtil.mutListener.listen(46709)) {
            mY = y;
        }
        if (!ListenerUtil.mutListener.listen(46710)) {
            hasMoved = false;
        }
    }

    private void touch_move(float x, float y) {
        if (!ListenerUtil.mutListener.listen(46731)) {
            if (isRealMovement(x, y)) {
                if (!ListenerUtil.mutListener.listen(46727)) {
                    getCurrentPath().quadTo(mX, mY, (ListenerUtil.mutListener.listen(46718) ? (((ListenerUtil.mutListener.listen(46714) ? (x % mX) : (ListenerUtil.mutListener.listen(46713) ? (x / mX) : (ListenerUtil.mutListener.listen(46712) ? (x * mX) : (ListenerUtil.mutListener.listen(46711) ? (x - mX) : (x + mX)))))) % 2) : (ListenerUtil.mutListener.listen(46717) ? (((ListenerUtil.mutListener.listen(46714) ? (x % mX) : (ListenerUtil.mutListener.listen(46713) ? (x / mX) : (ListenerUtil.mutListener.listen(46712) ? (x * mX) : (ListenerUtil.mutListener.listen(46711) ? (x - mX) : (x + mX)))))) * 2) : (ListenerUtil.mutListener.listen(46716) ? (((ListenerUtil.mutListener.listen(46714) ? (x % mX) : (ListenerUtil.mutListener.listen(46713) ? (x / mX) : (ListenerUtil.mutListener.listen(46712) ? (x * mX) : (ListenerUtil.mutListener.listen(46711) ? (x - mX) : (x + mX)))))) - 2) : (ListenerUtil.mutListener.listen(46715) ? (((ListenerUtil.mutListener.listen(46714) ? (x % mX) : (ListenerUtil.mutListener.listen(46713) ? (x / mX) : (ListenerUtil.mutListener.listen(46712) ? (x * mX) : (ListenerUtil.mutListener.listen(46711) ? (x - mX) : (x + mX)))))) + 2) : (((ListenerUtil.mutListener.listen(46714) ? (x % mX) : (ListenerUtil.mutListener.listen(46713) ? (x / mX) : (ListenerUtil.mutListener.listen(46712) ? (x * mX) : (ListenerUtil.mutListener.listen(46711) ? (x - mX) : (x + mX)))))) / 2))))), (ListenerUtil.mutListener.listen(46726) ? (((ListenerUtil.mutListener.listen(46722) ? (y % mY) : (ListenerUtil.mutListener.listen(46721) ? (y / mY) : (ListenerUtil.mutListener.listen(46720) ? (y * mY) : (ListenerUtil.mutListener.listen(46719) ? (y - mY) : (y + mY)))))) % 2) : (ListenerUtil.mutListener.listen(46725) ? (((ListenerUtil.mutListener.listen(46722) ? (y % mY) : (ListenerUtil.mutListener.listen(46721) ? (y / mY) : (ListenerUtil.mutListener.listen(46720) ? (y * mY) : (ListenerUtil.mutListener.listen(46719) ? (y - mY) : (y + mY)))))) * 2) : (ListenerUtil.mutListener.listen(46724) ? (((ListenerUtil.mutListener.listen(46722) ? (y % mY) : (ListenerUtil.mutListener.listen(46721) ? (y / mY) : (ListenerUtil.mutListener.listen(46720) ? (y * mY) : (ListenerUtil.mutListener.listen(46719) ? (y - mY) : (y + mY)))))) - 2) : (ListenerUtil.mutListener.listen(46723) ? (((ListenerUtil.mutListener.listen(46722) ? (y % mY) : (ListenerUtil.mutListener.listen(46721) ? (y / mY) : (ListenerUtil.mutListener.listen(46720) ? (y * mY) : (ListenerUtil.mutListener.listen(46719) ? (y - mY) : (y + mY)))))) + 2) : (((ListenerUtil.mutListener.listen(46722) ? (y % mY) : (ListenerUtil.mutListener.listen(46721) ? (y / mY) : (ListenerUtil.mutListener.listen(46720) ? (y * mY) : (ListenerUtil.mutListener.listen(46719) ? (y - mY) : (y + mY)))))) / 2))))));
                }
                if (!ListenerUtil.mutListener.listen(46728)) {
                    mX = x;
                }
                if (!ListenerUtil.mutListener.listen(46729)) {
                    mY = y;
                }
                if (!ListenerUtil.mutListener.listen(46730)) {
                    hasMoved = true;
                }
            }
        }
    }

    private void touch_up(float x, float y) {
        if (!ListenerUtil.mutListener.listen(46742)) {
            if ((ListenerUtil.mutListener.listen(46732) ? (isRealMovement(x, y) && hasMoved) : (isRealMovement(x, y) || hasMoved))) {
                if (!ListenerUtil.mutListener.listen(46740)) {
                    getCurrentPath().lineTo(mX, mY);
                }
                if (!ListenerUtil.mutListener.listen(46741)) {
                    onTouchListener.onAdded();
                }
            } else {
                int pathIndex = (ListenerUtil.mutListener.listen(46736) ? (paths.size() % 1) : (ListenerUtil.mutListener.listen(46735) ? (paths.size() / 1) : (ListenerUtil.mutListener.listen(46734) ? (paths.size() * 1) : (ListenerUtil.mutListener.listen(46733) ? (paths.size() + 1) : (paths.size() - 1)))));
                if (!ListenerUtil.mutListener.listen(46737)) {
                    paths.remove(pathIndex);
                }
                if (!ListenerUtil.mutListener.listen(46738)) {
                    paints.remove(pathIndex);
                }
                if (!ListenerUtil.mutListener.listen(46739)) {
                    invalidate();
                }
            }
        }
    }

    private boolean isRealMovement(float x, float y) {
        float dx = Math.abs((ListenerUtil.mutListener.listen(46746) ? (x % mX) : (ListenerUtil.mutListener.listen(46745) ? (x / mX) : (ListenerUtil.mutListener.listen(46744) ? (x * mX) : (ListenerUtil.mutListener.listen(46743) ? (x + mX) : (x - mX))))));
        float dy = Math.abs((ListenerUtil.mutListener.listen(46750) ? (y % mY) : (ListenerUtil.mutListener.listen(46749) ? (y / mY) : (ListenerUtil.mutListener.listen(46748) ? (y * mY) : (ListenerUtil.mutListener.listen(46747) ? (y + mY) : (y - mY))))));
        return ((ListenerUtil.mutListener.listen(46761) ? ((ListenerUtil.mutListener.listen(46755) ? (dx <= TOUCH_TOLERANCE) : (ListenerUtil.mutListener.listen(46754) ? (dx > TOUCH_TOLERANCE) : (ListenerUtil.mutListener.listen(46753) ? (dx < TOUCH_TOLERANCE) : (ListenerUtil.mutListener.listen(46752) ? (dx != TOUCH_TOLERANCE) : (ListenerUtil.mutListener.listen(46751) ? (dx == TOUCH_TOLERANCE) : (dx >= TOUCH_TOLERANCE)))))) && (ListenerUtil.mutListener.listen(46760) ? (dy <= TOUCH_TOLERANCE) : (ListenerUtil.mutListener.listen(46759) ? (dy > TOUCH_TOLERANCE) : (ListenerUtil.mutListener.listen(46758) ? (dy < TOUCH_TOLERANCE) : (ListenerUtil.mutListener.listen(46757) ? (dy != TOUCH_TOLERANCE) : (ListenerUtil.mutListener.listen(46756) ? (dy == TOUCH_TOLERANCE) : (dy >= TOUCH_TOLERANCE))))))) : ((ListenerUtil.mutListener.listen(46755) ? (dx <= TOUCH_TOLERANCE) : (ListenerUtil.mutListener.listen(46754) ? (dx > TOUCH_TOLERANCE) : (ListenerUtil.mutListener.listen(46753) ? (dx < TOUCH_TOLERANCE) : (ListenerUtil.mutListener.listen(46752) ? (dx != TOUCH_TOLERANCE) : (ListenerUtil.mutListener.listen(46751) ? (dx == TOUCH_TOLERANCE) : (dx >= TOUCH_TOLERANCE)))))) || (ListenerUtil.mutListener.listen(46760) ? (dy <= TOUCH_TOLERANCE) : (ListenerUtil.mutListener.listen(46759) ? (dy > TOUCH_TOLERANCE) : (ListenerUtil.mutListener.listen(46758) ? (dy < TOUCH_TOLERANCE) : (ListenerUtil.mutListener.listen(46757) ? (dy != TOUCH_TOLERANCE) : (ListenerUtil.mutListener.listen(46756) ? (dy == TOUCH_TOLERANCE) : (dy >= TOUCH_TOLERANCE)))))))));
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (!ListenerUtil.mutListener.listen(46762)) {
            if (!isActive) {
                return false;
            }
        }
        float x = event.getX();
        float y = event.getY();
        if (!ListenerUtil.mutListener.listen(46768)) {
            switch(event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    if (!ListenerUtil.mutListener.listen(46763)) {
                        touch_start(x, y);
                    }
                    if (!ListenerUtil.mutListener.listen(46764)) {
                        this.onTouchListener.onTouchDown();
                    }
                    break;
                case MotionEvent.ACTION_MOVE:
                    if (!ListenerUtil.mutListener.listen(46765)) {
                        touch_move(x, y);
                    }
                    break;
                case MotionEvent.ACTION_UP:
                    if (!ListenerUtil.mutListener.listen(46766)) {
                        touch_up(x, y);
                    }
                    if (!ListenerUtil.mutListener.listen(46767)) {
                        this.onTouchListener.onTouchUp();
                    }
                    break;
                default:
                    return false;
            }
        }
        if (!ListenerUtil.mutListener.listen(46769)) {
            invalidate();
        }
        return true;
    }

    public void undo() {
        int pathIndex = (ListenerUtil.mutListener.listen(46773) ? (paths.size() % 1) : (ListenerUtil.mutListener.listen(46772) ? (paths.size() / 1) : (ListenerUtil.mutListener.listen(46771) ? (paths.size() * 1) : (ListenerUtil.mutListener.listen(46770) ? (paths.size() + 1) : (paths.size() - 1)))));
        if (!ListenerUtil.mutListener.listen(46783)) {
            if ((ListenerUtil.mutListener.listen(46778) ? (pathIndex >= 0) : (ListenerUtil.mutListener.listen(46777) ? (pathIndex <= 0) : (ListenerUtil.mutListener.listen(46776) ? (pathIndex < 0) : (ListenerUtil.mutListener.listen(46775) ? (pathIndex != 0) : (ListenerUtil.mutListener.listen(46774) ? (pathIndex == 0) : (pathIndex > 0))))))) {
                if (!ListenerUtil.mutListener.listen(46779)) {
                    paths.remove(pathIndex);
                }
                if (!ListenerUtil.mutListener.listen(46780)) {
                    paints.remove(pathIndex);
                }
                if (!ListenerUtil.mutListener.listen(46781)) {
                    invalidate();
                }
                if (!ListenerUtil.mutListener.listen(46782)) {
                    onTouchListener.onDeleted();
                }
            }
        }
    }

    public void renderOverlay(Canvas combinedCanvas, int srcWidth, int srcHeight) {
        // render overlay to original canvas
        float factorX = (ListenerUtil.mutListener.listen(46787) ? ((float) combinedCanvas.getWidth() % (float) srcWidth) : (ListenerUtil.mutListener.listen(46786) ? ((float) combinedCanvas.getWidth() * (float) srcWidth) : (ListenerUtil.mutListener.listen(46785) ? ((float) combinedCanvas.getWidth() - (float) srcWidth) : (ListenerUtil.mutListener.listen(46784) ? ((float) combinedCanvas.getWidth() + (float) srcWidth) : ((float) combinedCanvas.getWidth() / (float) srcWidth)))));
        float factorY = (ListenerUtil.mutListener.listen(46791) ? ((float) combinedCanvas.getHeight() % (float) srcHeight) : (ListenerUtil.mutListener.listen(46790) ? ((float) combinedCanvas.getHeight() * (float) srcHeight) : (ListenerUtil.mutListener.listen(46789) ? ((float) combinedCanvas.getHeight() - (float) srcHeight) : (ListenerUtil.mutListener.listen(46788) ? ((float) combinedCanvas.getHeight() + (float) srcHeight) : ((float) combinedCanvas.getHeight() / (float) srcHeight)))));
        Matrix matrix = new Matrix();
        if (!ListenerUtil.mutListener.listen(46792)) {
            matrix.setScale(factorX, factorY);
        }
        if (!ListenerUtil.mutListener.listen(46805)) {
            {
                long _loopCounter550 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(46804) ? (i >= paths.size()) : (ListenerUtil.mutListener.listen(46803) ? (i <= paths.size()) : (ListenerUtil.mutListener.listen(46802) ? (i > paths.size()) : (ListenerUtil.mutListener.listen(46801) ? (i != paths.size()) : (ListenerUtil.mutListener.listen(46800) ? (i == paths.size()) : (i < paths.size())))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter550", ++_loopCounter550);
                    Path path = paths.get(i);
                    Path scaledPath = new Path();
                    if (!ListenerUtil.mutListener.listen(46793)) {
                        path.transform(matrix, scaledPath);
                    }
                    Paint paint = paints.get(i);
                    Paint scaledPaint = new Paint(paint);
                    if (!ListenerUtil.mutListener.listen(46798)) {
                        scaledPaint.setStrokeWidth((ListenerUtil.mutListener.listen(46797) ? (scaledPaint.getStrokeWidth() % factorX) : (ListenerUtil.mutListener.listen(46796) ? (scaledPaint.getStrokeWidth() / factorX) : (ListenerUtil.mutListener.listen(46795) ? (scaledPaint.getStrokeWidth() - factorX) : (ListenerUtil.mutListener.listen(46794) ? (scaledPaint.getStrokeWidth() + factorX) : (scaledPaint.getStrokeWidth() * factorX))))));
                    }
                    if (!ListenerUtil.mutListener.listen(46799)) {
                        combinedCanvas.drawPath(scaledPath, scaledPaint);
                    }
                }
            }
        }
    }

    public void recalculate(int newWidth, int newHeight) {
        if (!ListenerUtil.mutListener.listen(46841)) {
            if ((ListenerUtil.mutListener.listen(46816) ? ((ListenerUtil.mutListener.listen(46810) ? (currentHeight >= 0) : (ListenerUtil.mutListener.listen(46809) ? (currentHeight <= 0) : (ListenerUtil.mutListener.listen(46808) ? (currentHeight > 0) : (ListenerUtil.mutListener.listen(46807) ? (currentHeight < 0) : (ListenerUtil.mutListener.listen(46806) ? (currentHeight == 0) : (currentHeight != 0)))))) || (ListenerUtil.mutListener.listen(46815) ? (currentWidth >= 0) : (ListenerUtil.mutListener.listen(46814) ? (currentWidth <= 0) : (ListenerUtil.mutListener.listen(46813) ? (currentWidth > 0) : (ListenerUtil.mutListener.listen(46812) ? (currentWidth < 0) : (ListenerUtil.mutListener.listen(46811) ? (currentWidth == 0) : (currentWidth != 0))))))) : ((ListenerUtil.mutListener.listen(46810) ? (currentHeight >= 0) : (ListenerUtil.mutListener.listen(46809) ? (currentHeight <= 0) : (ListenerUtil.mutListener.listen(46808) ? (currentHeight > 0) : (ListenerUtil.mutListener.listen(46807) ? (currentHeight < 0) : (ListenerUtil.mutListener.listen(46806) ? (currentHeight == 0) : (currentHeight != 0)))))) && (ListenerUtil.mutListener.listen(46815) ? (currentWidth >= 0) : (ListenerUtil.mutListener.listen(46814) ? (currentWidth <= 0) : (ListenerUtil.mutListener.listen(46813) ? (currentWidth > 0) : (ListenerUtil.mutListener.listen(46812) ? (currentWidth < 0) : (ListenerUtil.mutListener.listen(46811) ? (currentWidth == 0) : (currentWidth != 0))))))))) {
                float factorX = (ListenerUtil.mutListener.listen(46820) ? ((float) newWidth % (float) currentWidth) : (ListenerUtil.mutListener.listen(46819) ? ((float) newWidth * (float) currentWidth) : (ListenerUtil.mutListener.listen(46818) ? ((float) newWidth - (float) currentWidth) : (ListenerUtil.mutListener.listen(46817) ? ((float) newWidth + (float) currentWidth) : ((float) newWidth / (float) currentWidth)))));
                float factorY = (ListenerUtil.mutListener.listen(46824) ? ((float) newHeight % (float) currentHeight) : (ListenerUtil.mutListener.listen(46823) ? ((float) newHeight * (float) currentHeight) : (ListenerUtil.mutListener.listen(46822) ? ((float) newHeight - (float) currentHeight) : (ListenerUtil.mutListener.listen(46821) ? ((float) newHeight + (float) currentHeight) : ((float) newHeight / (float) currentHeight)))));
                Matrix matrix = new Matrix();
                if (!ListenerUtil.mutListener.listen(46825)) {
                    matrix.setScale(factorX, factorY);
                }
                if (!ListenerUtil.mutListener.listen(46839)) {
                    {
                        long _loopCounter551 = 0;
                        for (int i = 0; (ListenerUtil.mutListener.listen(46838) ? (i >= paths.size()) : (ListenerUtil.mutListener.listen(46837) ? (i <= paths.size()) : (ListenerUtil.mutListener.listen(46836) ? (i > paths.size()) : (ListenerUtil.mutListener.listen(46835) ? (i != paths.size()) : (ListenerUtil.mutListener.listen(46834) ? (i == paths.size()) : (i < paths.size())))))); i++) {
                            ListenerUtil.loopListener.listen("_loopCounter551", ++_loopCounter551);
                            Path path = paths.get(i);
                            Path scaledPath = new Path();
                            if (!ListenerUtil.mutListener.listen(46826)) {
                                path.transform(matrix, scaledPath);
                            }
                            if (!ListenerUtil.mutListener.listen(46827)) {
                                paths.get(i).set(scaledPath);
                            }
                            Paint paint = paints.get(i);
                            Paint scaledPaint = new Paint(paint);
                            if (!ListenerUtil.mutListener.listen(46832)) {
                                scaledPaint.setStrokeWidth((ListenerUtil.mutListener.listen(46831) ? (scaledPaint.getStrokeWidth() % factorX) : (ListenerUtil.mutListener.listen(46830) ? (scaledPaint.getStrokeWidth() / factorX) : (ListenerUtil.mutListener.listen(46829) ? (scaledPaint.getStrokeWidth() - factorX) : (ListenerUtil.mutListener.listen(46828) ? (scaledPaint.getStrokeWidth() + factorX) : (scaledPaint.getStrokeWidth() * factorX))))));
                            }
                            if (!ListenerUtil.mutListener.listen(46833)) {
                                paints.get(i).set(scaledPaint);
                            }
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(46840)) {
                    invalidate();
                }
            }
        }
    }

    public void setColor(int color) {
        if (!ListenerUtil.mutListener.listen(46842)) {
            currentColor = color;
        }
    }

    public void setStrokeWidth(int width) {
        if (!ListenerUtil.mutListener.listen(46843)) {
            currentStrokeWidth = width;
        }
    }

    public void setActive(boolean active) {
        if (!ListenerUtil.mutListener.listen(46844)) {
            isActive = active;
        }
    }

    public boolean getActive() {
        return isActive;
    }

    public int getNumPaths() {
        return (ListenerUtil.mutListener.listen(46848) ? (paths.size() % 1) : (ListenerUtil.mutListener.listen(46847) ? (paths.size() / 1) : (ListenerUtil.mutListener.listen(46846) ? (paths.size() * 1) : (ListenerUtil.mutListener.listen(46845) ? (paths.size() + 1) : (paths.size() - 1)))));
    }

    public void setTouchListener(TouchListener touchListener) {
        if (!ListenerUtil.mutListener.listen(46849)) {
            this.onTouchListener = touchListener;
        }
    }

    public interface TouchListener {

        void onTouchUp();

        void onTouchDown();

        void onAdded();

        void onDeleted();
    }
}
