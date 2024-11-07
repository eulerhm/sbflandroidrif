/*
 * Copyright (c) 2009 Andrew <andrewdubya@gmail.com>
 * Copyright (c) 2009 Nicolas Raoul <nicolas.raoul@gmail.com>
 * Copyright (c) 2009 Edu Zamora <edu.zasu@gmail.com>
 *
 * This program is free software; you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation; either version 3 of the License, or (at your option) any later
 * version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A
 * PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.ichi2.anki;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.Point;
import androidx.annotation.CheckResult;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.core.content.ContextCompat;
import timber.log.Timber;
import android.os.Environment;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import com.ichi2.libanki.utils.Time;
import com.ichi2.libanki.utils.TimeUtils;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.lang.ref.WeakReference;
import java.util.Stack;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Whiteboard allowing the user to draw the card's answer on the touchscreen.
 */
@SuppressLint("ViewConstructor")
public class Whiteboard extends View {

    private static final float TOUCH_TOLERANCE = 4;

    private final Paint mPaint;

    private final UndoStack mUndo = new UndoStack();

    private Bitmap mBitmap;

    private Canvas mCanvas;

    private final Path mPath;

    private final Paint mBitmapPaint;

    private final WeakReference<AbstractFlashcardViewer> mCardViewer;

    private float mX;

    private float mY;

    private float mSecondFingerX0;

    private float mSecondFingerY0;

    private float mSecondFingerX;

    private float mSecondFingerY;

    private int mSecondFingerPointerId;

    private boolean mSecondFingerWithinTapTolerance;

    private boolean mCurrentlyDrawing = false;

    private boolean mUndoModeActive = false;

    private final int foregroundColor;

    private final LinearLayout mColorPalette;

    @Nullable
    private OnPaintColorChangeListener mOnPaintColorChangeListener;

    public Whiteboard(AbstractFlashcardViewer cardViewer, boolean inverted) {
        super(cardViewer, null);
        mCardViewer = new WeakReference<>(cardViewer);
        Button whitePenColorButton = cardViewer.findViewById(R.id.pen_color_white);
        Button blackPenColorButton = cardViewer.findViewById(R.id.pen_color_black);
        if (!inverted) {
            if (!ListenerUtil.mutListener.listen(12383)) {
                whitePenColorButton.setVisibility(View.GONE);
            }
            if (!ListenerUtil.mutListener.listen(12384)) {
                blackPenColorButton.setOnClickListener(this::onClick);
            }
            foregroundColor = Color.BLACK;
        } else {
            if (!ListenerUtil.mutListener.listen(12381)) {
                blackPenColorButton.setVisibility(View.GONE);
            }
            if (!ListenerUtil.mutListener.listen(12382)) {
                whitePenColorButton.setOnClickListener(this::onClick);
            }
            foregroundColor = Color.WHITE;
        }
        mPaint = new Paint();
        if (!ListenerUtil.mutListener.listen(12385)) {
            mPaint.setAntiAlias(true);
        }
        if (!ListenerUtil.mutListener.listen(12386)) {
            mPaint.setDither(true);
        }
        if (!ListenerUtil.mutListener.listen(12387)) {
            mPaint.setColor(foregroundColor);
        }
        if (!ListenerUtil.mutListener.listen(12388)) {
            mPaint.setStyle(Paint.Style.STROKE);
        }
        if (!ListenerUtil.mutListener.listen(12389)) {
            mPaint.setStrokeJoin(Paint.Join.ROUND);
        }
        if (!ListenerUtil.mutListener.listen(12390)) {
            mPaint.setStrokeCap(Paint.Cap.ROUND);
        }
        int wbStrokeWidth = AnkiDroidApp.getSharedPrefs(cardViewer).getInt("whiteBoardStrokeWidth", 6);
        if (!ListenerUtil.mutListener.listen(12391)) {
            mPaint.setStrokeWidth((float) wbStrokeWidth);
        }
        if (!ListenerUtil.mutListener.listen(12392)) {
            createBitmap();
        }
        mPath = new Path();
        mBitmapPaint = new Paint(Paint.DITHER_FLAG);
        // selecting pen color to draw
        mColorPalette = cardViewer.findViewById(R.id.whiteboard_pen_color);
        if (!ListenerUtil.mutListener.listen(12393)) {
            cardViewer.findViewById(R.id.pen_color_red).setOnClickListener(this::onClick);
        }
        if (!ListenerUtil.mutListener.listen(12394)) {
            cardViewer.findViewById(R.id.pen_color_green).setOnClickListener(this::onClick);
        }
        if (!ListenerUtil.mutListener.listen(12395)) {
            cardViewer.findViewById(R.id.pen_color_blue).setOnClickListener(this::onClick);
        }
        if (!ListenerUtil.mutListener.listen(12396)) {
            cardViewer.findViewById(R.id.pen_color_yellow).setOnClickListener(this::onClick);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (!ListenerUtil.mutListener.listen(12397)) {
            super.onDraw(canvas);
        }
        if (!ListenerUtil.mutListener.listen(12398)) {
            canvas.drawColor(0);
        }
        if (!ListenerUtil.mutListener.listen(12399)) {
            canvas.drawBitmap(mBitmap, 0, 0, mBitmapPaint);
        }
        if (!ListenerUtil.mutListener.listen(12400)) {
            canvas.drawPath(mPath, mPaint);
        }
    }

    /**
     * Handle motion events to draw using the touch screen or to interact with the flashcard behind
     * the whiteboard by using a second finger.
     *
     * @param event The motion event.
     * @return True if the event was handled, false otherwise
     */
    public boolean handleTouchEvent(MotionEvent event) {
        return (ListenerUtil.mutListener.listen(12401) ? (handleDrawEvent(event) && handleMultiTouchEvent(event)) : (handleDrawEvent(event) || handleMultiTouchEvent(event)));
    }

    /**
     * Handle motion events to draw using the touch screen. Only simple touch events are processed,
     * a multitouch event aborts to current stroke.
     *
     * @param event The motion event.
     * @return True if the event was handled, false otherwise or when drawing was aborted due to
     *              detection of a multitouch event.
     */
    private boolean handleDrawEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        switch(event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                if (!ListenerUtil.mutListener.listen(12402)) {
                    drawStart(x, y);
                }
                if (!ListenerUtil.mutListener.listen(12403)) {
                    invalidate();
                }
                return true;
            case MotionEvent.ACTION_MOVE:
                if (mCurrentlyDrawing) {
                    if (!ListenerUtil.mutListener.listen(12410)) {
                        {
                            long _loopCounter202 = 0;
                            for (int i = 0; (ListenerUtil.mutListener.listen(12409) ? (i >= event.getHistorySize()) : (ListenerUtil.mutListener.listen(12408) ? (i <= event.getHistorySize()) : (ListenerUtil.mutListener.listen(12407) ? (i > event.getHistorySize()) : (ListenerUtil.mutListener.listen(12406) ? (i != event.getHistorySize()) : (ListenerUtil.mutListener.listen(12405) ? (i == event.getHistorySize()) : (i < event.getHistorySize())))))); i++) {
                                ListenerUtil.loopListener.listen("_loopCounter202", ++_loopCounter202);
                                if (!ListenerUtil.mutListener.listen(12404)) {
                                    drawAlong(event.getHistoricalX(i), event.getHistoricalY(i));
                                }
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(12411)) {
                        drawAlong(x, y);
                    }
                    if (!ListenerUtil.mutListener.listen(12412)) {
                        invalidate();
                    }
                    return true;
                }
                return false;
            case MotionEvent.ACTION_UP:
                if (mCurrentlyDrawing) {
                    if (!ListenerUtil.mutListener.listen(12413)) {
                        drawFinish();
                    }
                    if (!ListenerUtil.mutListener.listen(12414)) {
                        invalidate();
                    }
                    return true;
                }
                return false;
            case MotionEvent.ACTION_POINTER_DOWN:
                if (!ListenerUtil.mutListener.listen(12416)) {
                    if (mCurrentlyDrawing) {
                        if (!ListenerUtil.mutListener.listen(12415)) {
                            drawAbort();
                        }
                    }
                }
                return false;
            default:
                return false;
        }
    }

    // Parse multitouch input to scroll the card behind the whiteboard or click on elements
    private boolean handleMultiTouchEvent(MotionEvent event) {
        if (!ListenerUtil.mutListener.listen(12419)) {
            if (event.getPointerCount() == 2) {
                if (!ListenerUtil.mutListener.listen(12418)) {
                    switch(event.getActionMasked()) {
                        case MotionEvent.ACTION_POINTER_DOWN:
                            if (!ListenerUtil.mutListener.listen(12417)) {
                                reinitializeSecondFinger(event);
                            }
                            return true;
                        case MotionEvent.ACTION_MOVE:
                            return trySecondFingerScroll(event);
                        case MotionEvent.ACTION_POINTER_UP:
                            return trySecondFingerClick(event);
                        default:
                            return false;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Clear the whiteboard.
     */
    public void clear() {
        if (!ListenerUtil.mutListener.listen(12420)) {
            mUndoModeActive = false;
        }
        if (!ListenerUtil.mutListener.listen(12421)) {
            mBitmap.eraseColor(0);
        }
        if (!ListenerUtil.mutListener.listen(12422)) {
            mUndo.clear();
        }
        if (!ListenerUtil.mutListener.listen(12423)) {
            invalidate();
        }
        if (!ListenerUtil.mutListener.listen(12425)) {
            if (mCardViewer.get() != null) {
                if (!ListenerUtil.mutListener.listen(12424)) {
                    mCardViewer.get().supportInvalidateOptionsMenu();
                }
            }
        }
    }

    /**
     * Undo the last stroke
     */
    public void undo() {
        if (!ListenerUtil.mutListener.listen(12426)) {
            mUndo.pop();
        }
        if (!ListenerUtil.mutListener.listen(12427)) {
            mUndo.apply();
        }
        if (!ListenerUtil.mutListener.listen(12430)) {
            if ((ListenerUtil.mutListener.listen(12428) ? (undoEmpty() || mCardViewer.get() != null) : (undoEmpty() && mCardViewer.get() != null))) {
                if (!ListenerUtil.mutListener.listen(12429)) {
                    mCardViewer.get().supportInvalidateOptionsMenu();
                }
            }
        }
    }

    /**
     * @return Whether there are strokes to undo
     */
    public boolean undoEmpty() {
        return mUndo.empty();
    }

    /**
     * @return true if the undo queue has had any strokes added to it since the last clear
     */
    public boolean isUndoModeActive() {
        return mUndoModeActive;
    }

    private void createBitmap(int w, int h) {
        if (!ListenerUtil.mutListener.listen(12431)) {
            mBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        }
        if (!ListenerUtil.mutListener.listen(12432)) {
            mCanvas = new Canvas(mBitmap);
        }
        if (!ListenerUtil.mutListener.listen(12433)) {
            clear();
        }
    }

    private void createBitmap() {
        // To fix issue #1336, just make the whiteboard big and square.
        final Point p = getDisplayDimensions();
        int bitmapSize = Math.max(p.x, p.y);
        if (!ListenerUtil.mutListener.listen(12434)) {
            createBitmap(bitmapSize, bitmapSize);
        }
    }

    private void drawStart(float x, float y) {
        if (!ListenerUtil.mutListener.listen(12435)) {
            mCurrentlyDrawing = true;
        }
        if (!ListenerUtil.mutListener.listen(12436)) {
            mPath.reset();
        }
        if (!ListenerUtil.mutListener.listen(12437)) {
            mPath.moveTo(x, y);
        }
        if (!ListenerUtil.mutListener.listen(12438)) {
            mX = x;
        }
        if (!ListenerUtil.mutListener.listen(12439)) {
            mY = y;
        }
    }

    private void drawAlong(float x, float y) {
        float dx = Math.abs((ListenerUtil.mutListener.listen(12443) ? (x % mX) : (ListenerUtil.mutListener.listen(12442) ? (x / mX) : (ListenerUtil.mutListener.listen(12441) ? (x * mX) : (ListenerUtil.mutListener.listen(12440) ? (x + mX) : (x - mX))))));
        float dy = Math.abs((ListenerUtil.mutListener.listen(12447) ? (y % mY) : (ListenerUtil.mutListener.listen(12446) ? (y / mY) : (ListenerUtil.mutListener.listen(12445) ? (y * mY) : (ListenerUtil.mutListener.listen(12444) ? (y + mY) : (y - mY))))));
        if (!ListenerUtil.mutListener.listen(12478)) {
            if ((ListenerUtil.mutListener.listen(12458) ? ((ListenerUtil.mutListener.listen(12452) ? (dx <= TOUCH_TOLERANCE) : (ListenerUtil.mutListener.listen(12451) ? (dx > TOUCH_TOLERANCE) : (ListenerUtil.mutListener.listen(12450) ? (dx < TOUCH_TOLERANCE) : (ListenerUtil.mutListener.listen(12449) ? (dx != TOUCH_TOLERANCE) : (ListenerUtil.mutListener.listen(12448) ? (dx == TOUCH_TOLERANCE) : (dx >= TOUCH_TOLERANCE)))))) && (ListenerUtil.mutListener.listen(12457) ? (dy <= TOUCH_TOLERANCE) : (ListenerUtil.mutListener.listen(12456) ? (dy > TOUCH_TOLERANCE) : (ListenerUtil.mutListener.listen(12455) ? (dy < TOUCH_TOLERANCE) : (ListenerUtil.mutListener.listen(12454) ? (dy != TOUCH_TOLERANCE) : (ListenerUtil.mutListener.listen(12453) ? (dy == TOUCH_TOLERANCE) : (dy >= TOUCH_TOLERANCE))))))) : ((ListenerUtil.mutListener.listen(12452) ? (dx <= TOUCH_TOLERANCE) : (ListenerUtil.mutListener.listen(12451) ? (dx > TOUCH_TOLERANCE) : (ListenerUtil.mutListener.listen(12450) ? (dx < TOUCH_TOLERANCE) : (ListenerUtil.mutListener.listen(12449) ? (dx != TOUCH_TOLERANCE) : (ListenerUtil.mutListener.listen(12448) ? (dx == TOUCH_TOLERANCE) : (dx >= TOUCH_TOLERANCE)))))) || (ListenerUtil.mutListener.listen(12457) ? (dy <= TOUCH_TOLERANCE) : (ListenerUtil.mutListener.listen(12456) ? (dy > TOUCH_TOLERANCE) : (ListenerUtil.mutListener.listen(12455) ? (dy < TOUCH_TOLERANCE) : (ListenerUtil.mutListener.listen(12454) ? (dy != TOUCH_TOLERANCE) : (ListenerUtil.mutListener.listen(12453) ? (dy == TOUCH_TOLERANCE) : (dy >= TOUCH_TOLERANCE))))))))) {
                if (!ListenerUtil.mutListener.listen(12475)) {
                    mPath.quadTo(mX, mY, (ListenerUtil.mutListener.listen(12466) ? (((ListenerUtil.mutListener.listen(12462) ? (x % mX) : (ListenerUtil.mutListener.listen(12461) ? (x / mX) : (ListenerUtil.mutListener.listen(12460) ? (x * mX) : (ListenerUtil.mutListener.listen(12459) ? (x - mX) : (x + mX)))))) % 2) : (ListenerUtil.mutListener.listen(12465) ? (((ListenerUtil.mutListener.listen(12462) ? (x % mX) : (ListenerUtil.mutListener.listen(12461) ? (x / mX) : (ListenerUtil.mutListener.listen(12460) ? (x * mX) : (ListenerUtil.mutListener.listen(12459) ? (x - mX) : (x + mX)))))) * 2) : (ListenerUtil.mutListener.listen(12464) ? (((ListenerUtil.mutListener.listen(12462) ? (x % mX) : (ListenerUtil.mutListener.listen(12461) ? (x / mX) : (ListenerUtil.mutListener.listen(12460) ? (x * mX) : (ListenerUtil.mutListener.listen(12459) ? (x - mX) : (x + mX)))))) - 2) : (ListenerUtil.mutListener.listen(12463) ? (((ListenerUtil.mutListener.listen(12462) ? (x % mX) : (ListenerUtil.mutListener.listen(12461) ? (x / mX) : (ListenerUtil.mutListener.listen(12460) ? (x * mX) : (ListenerUtil.mutListener.listen(12459) ? (x - mX) : (x + mX)))))) + 2) : (((ListenerUtil.mutListener.listen(12462) ? (x % mX) : (ListenerUtil.mutListener.listen(12461) ? (x / mX) : (ListenerUtil.mutListener.listen(12460) ? (x * mX) : (ListenerUtil.mutListener.listen(12459) ? (x - mX) : (x + mX)))))) / 2))))), (ListenerUtil.mutListener.listen(12474) ? (((ListenerUtil.mutListener.listen(12470) ? (y % mY) : (ListenerUtil.mutListener.listen(12469) ? (y / mY) : (ListenerUtil.mutListener.listen(12468) ? (y * mY) : (ListenerUtil.mutListener.listen(12467) ? (y - mY) : (y + mY)))))) % 2) : (ListenerUtil.mutListener.listen(12473) ? (((ListenerUtil.mutListener.listen(12470) ? (y % mY) : (ListenerUtil.mutListener.listen(12469) ? (y / mY) : (ListenerUtil.mutListener.listen(12468) ? (y * mY) : (ListenerUtil.mutListener.listen(12467) ? (y - mY) : (y + mY)))))) * 2) : (ListenerUtil.mutListener.listen(12472) ? (((ListenerUtil.mutListener.listen(12470) ? (y % mY) : (ListenerUtil.mutListener.listen(12469) ? (y / mY) : (ListenerUtil.mutListener.listen(12468) ? (y * mY) : (ListenerUtil.mutListener.listen(12467) ? (y - mY) : (y + mY)))))) - 2) : (ListenerUtil.mutListener.listen(12471) ? (((ListenerUtil.mutListener.listen(12470) ? (y % mY) : (ListenerUtil.mutListener.listen(12469) ? (y / mY) : (ListenerUtil.mutListener.listen(12468) ? (y * mY) : (ListenerUtil.mutListener.listen(12467) ? (y - mY) : (y + mY)))))) + 2) : (((ListenerUtil.mutListener.listen(12470) ? (y % mY) : (ListenerUtil.mutListener.listen(12469) ? (y / mY) : (ListenerUtil.mutListener.listen(12468) ? (y * mY) : (ListenerUtil.mutListener.listen(12467) ? (y - mY) : (y + mY)))))) / 2))))));
                }
                if (!ListenerUtil.mutListener.listen(12476)) {
                    mX = x;
                }
                if (!ListenerUtil.mutListener.listen(12477)) {
                    mY = y;
                }
            }
        }
    }

    private void drawFinish() {
        if (!ListenerUtil.mutListener.listen(12479)) {
            mCurrentlyDrawing = false;
        }
        PathMeasure pm = new PathMeasure(mPath, false);
        if (!ListenerUtil.mutListener.listen(12480)) {
            mPath.lineTo(mX, mY);
        }
        Paint paint = new Paint(mPaint);
        WhiteboardAction action = (ListenerUtil.mutListener.listen(12485) ? (pm.getLength() >= 0) : (ListenerUtil.mutListener.listen(12484) ? (pm.getLength() <= 0) : (ListenerUtil.mutListener.listen(12483) ? (pm.getLength() < 0) : (ListenerUtil.mutListener.listen(12482) ? (pm.getLength() != 0) : (ListenerUtil.mutListener.listen(12481) ? (pm.getLength() == 0) : (pm.getLength() > 0)))))) ? new DrawPath(new Path(mPath), paint) : new DrawPoint(mX, mY, paint);
        if (!ListenerUtil.mutListener.listen(12486)) {
            action.apply(mCanvas);
        }
        if (!ListenerUtil.mutListener.listen(12487)) {
            mUndo.add(action);
        }
        if (!ListenerUtil.mutListener.listen(12488)) {
            mUndoModeActive = true;
        }
        if (!ListenerUtil.mutListener.listen(12489)) {
            // kill the path so we don't double draw
            mPath.reset();
        }
        if (!ListenerUtil.mutListener.listen(12497)) {
            if ((ListenerUtil.mutListener.listen(12495) ? ((ListenerUtil.mutListener.listen(12494) ? (mUndo.size() >= 1) : (ListenerUtil.mutListener.listen(12493) ? (mUndo.size() <= 1) : (ListenerUtil.mutListener.listen(12492) ? (mUndo.size() > 1) : (ListenerUtil.mutListener.listen(12491) ? (mUndo.size() < 1) : (ListenerUtil.mutListener.listen(12490) ? (mUndo.size() != 1) : (mUndo.size() == 1)))))) || mCardViewer.get() != null) : ((ListenerUtil.mutListener.listen(12494) ? (mUndo.size() >= 1) : (ListenerUtil.mutListener.listen(12493) ? (mUndo.size() <= 1) : (ListenerUtil.mutListener.listen(12492) ? (mUndo.size() > 1) : (ListenerUtil.mutListener.listen(12491) ? (mUndo.size() < 1) : (ListenerUtil.mutListener.listen(12490) ? (mUndo.size() != 1) : (mUndo.size() == 1)))))) && mCardViewer.get() != null))) {
                if (!ListenerUtil.mutListener.listen(12496)) {
                    mCardViewer.get().supportInvalidateOptionsMenu();
                }
            }
        }
    }

    private void drawAbort() {
        if (!ListenerUtil.mutListener.listen(12498)) {
            drawFinish();
        }
        if (!ListenerUtil.mutListener.listen(12499)) {
            undo();
        }
    }

    // a second finger
    private void reinitializeSecondFinger(MotionEvent event) {
        if (!ListenerUtil.mutListener.listen(12500)) {
            mSecondFingerWithinTapTolerance = true;
        }
        if (!ListenerUtil.mutListener.listen(12501)) {
            mSecondFingerPointerId = event.getPointerId(event.getActionIndex());
        }
        if (!ListenerUtil.mutListener.listen(12502)) {
            mSecondFingerX0 = event.getX(event.findPointerIndex(mSecondFingerPointerId));
        }
        if (!ListenerUtil.mutListener.listen(12503)) {
            mSecondFingerY0 = event.getY(event.findPointerIndex(mSecondFingerPointerId));
        }
    }

    private boolean updateSecondFinger(MotionEvent event) {
        int pointerIndex = event.findPointerIndex(mSecondFingerPointerId);
        if (!ListenerUtil.mutListener.listen(12532)) {
            if ((ListenerUtil.mutListener.listen(12508) ? (pointerIndex >= -1) : (ListenerUtil.mutListener.listen(12507) ? (pointerIndex <= -1) : (ListenerUtil.mutListener.listen(12506) ? (pointerIndex < -1) : (ListenerUtil.mutListener.listen(12505) ? (pointerIndex != -1) : (ListenerUtil.mutListener.listen(12504) ? (pointerIndex == -1) : (pointerIndex > -1))))))) {
                if (!ListenerUtil.mutListener.listen(12509)) {
                    mSecondFingerX = event.getX(pointerIndex);
                }
                if (!ListenerUtil.mutListener.listen(12510)) {
                    mSecondFingerY = event.getY(pointerIndex);
                }
                float dx = Math.abs((ListenerUtil.mutListener.listen(12514) ? (mSecondFingerX0 % mSecondFingerX) : (ListenerUtil.mutListener.listen(12513) ? (mSecondFingerX0 / mSecondFingerX) : (ListenerUtil.mutListener.listen(12512) ? (mSecondFingerX0 * mSecondFingerX) : (ListenerUtil.mutListener.listen(12511) ? (mSecondFingerX0 + mSecondFingerX) : (mSecondFingerX0 - mSecondFingerX))))));
                float dy = Math.abs((ListenerUtil.mutListener.listen(12518) ? (mSecondFingerY0 % mSecondFingerY) : (ListenerUtil.mutListener.listen(12517) ? (mSecondFingerY0 / mSecondFingerY) : (ListenerUtil.mutListener.listen(12516) ? (mSecondFingerY0 * mSecondFingerY) : (ListenerUtil.mutListener.listen(12515) ? (mSecondFingerY0 + mSecondFingerY) : (mSecondFingerY0 - mSecondFingerY))))));
                if (!ListenerUtil.mutListener.listen(12531)) {
                    if ((ListenerUtil.mutListener.listen(12529) ? ((ListenerUtil.mutListener.listen(12523) ? (dx <= TOUCH_TOLERANCE) : (ListenerUtil.mutListener.listen(12522) ? (dx > TOUCH_TOLERANCE) : (ListenerUtil.mutListener.listen(12521) ? (dx < TOUCH_TOLERANCE) : (ListenerUtil.mutListener.listen(12520) ? (dx != TOUCH_TOLERANCE) : (ListenerUtil.mutListener.listen(12519) ? (dx == TOUCH_TOLERANCE) : (dx >= TOUCH_TOLERANCE)))))) && (ListenerUtil.mutListener.listen(12528) ? (dy <= TOUCH_TOLERANCE) : (ListenerUtil.mutListener.listen(12527) ? (dy > TOUCH_TOLERANCE) : (ListenerUtil.mutListener.listen(12526) ? (dy < TOUCH_TOLERANCE) : (ListenerUtil.mutListener.listen(12525) ? (dy != TOUCH_TOLERANCE) : (ListenerUtil.mutListener.listen(12524) ? (dy == TOUCH_TOLERANCE) : (dy >= TOUCH_TOLERANCE))))))) : ((ListenerUtil.mutListener.listen(12523) ? (dx <= TOUCH_TOLERANCE) : (ListenerUtil.mutListener.listen(12522) ? (dx > TOUCH_TOLERANCE) : (ListenerUtil.mutListener.listen(12521) ? (dx < TOUCH_TOLERANCE) : (ListenerUtil.mutListener.listen(12520) ? (dx != TOUCH_TOLERANCE) : (ListenerUtil.mutListener.listen(12519) ? (dx == TOUCH_TOLERANCE) : (dx >= TOUCH_TOLERANCE)))))) || (ListenerUtil.mutListener.listen(12528) ? (dy <= TOUCH_TOLERANCE) : (ListenerUtil.mutListener.listen(12527) ? (dy > TOUCH_TOLERANCE) : (ListenerUtil.mutListener.listen(12526) ? (dy < TOUCH_TOLERANCE) : (ListenerUtil.mutListener.listen(12525) ? (dy != TOUCH_TOLERANCE) : (ListenerUtil.mutListener.listen(12524) ? (dy == TOUCH_TOLERANCE) : (dy >= TOUCH_TOLERANCE))))))))) {
                        if (!ListenerUtil.mutListener.listen(12530)) {
                            mSecondFingerWithinTapTolerance = false;
                        }
                    }
                }
                return true;
            }
        }
        return false;
    }

    // if so, forward a click action and return true
    private boolean trySecondFingerClick(MotionEvent event) {
        if (!ListenerUtil.mutListener.listen(12537)) {
            if (mSecondFingerPointerId == event.getPointerId(event.getActionIndex())) {
                if (!ListenerUtil.mutListener.listen(12533)) {
                    updateSecondFinger(event);
                }
                AbstractFlashcardViewer cardViewer = mCardViewer.get();
                if (!ListenerUtil.mutListener.listen(12536)) {
                    if ((ListenerUtil.mutListener.listen(12534) ? (mSecondFingerWithinTapTolerance || cardViewer != null) : (mSecondFingerWithinTapTolerance && cardViewer != null))) {
                        if (!ListenerUtil.mutListener.listen(12535)) {
                            cardViewer.tapOnCurrentCard((int) mSecondFingerX, (int) mSecondFingerY);
                        }
                        return true;
                    }
                }
            }
        }
        return false;
    }

    // in this case perform a scroll action
    private boolean trySecondFingerScroll(MotionEvent event) {
        if (!ListenerUtil.mutListener.listen(12553)) {
            if ((ListenerUtil.mutListener.listen(12538) ? (updateSecondFinger(event) || !mSecondFingerWithinTapTolerance) : (updateSecondFinger(event) && !mSecondFingerWithinTapTolerance))) {
                int dy = (int) ((ListenerUtil.mutListener.listen(12542) ? (mSecondFingerY0 % mSecondFingerY) : (ListenerUtil.mutListener.listen(12541) ? (mSecondFingerY0 / mSecondFingerY) : (ListenerUtil.mutListener.listen(12540) ? (mSecondFingerY0 * mSecondFingerY) : (ListenerUtil.mutListener.listen(12539) ? (mSecondFingerY0 + mSecondFingerY) : (mSecondFingerY0 - mSecondFingerY))))));
                AbstractFlashcardViewer cardViewer = mCardViewer.get();
                if (!ListenerUtil.mutListener.listen(12552)) {
                    if ((ListenerUtil.mutListener.listen(12548) ? ((ListenerUtil.mutListener.listen(12547) ? (dy >= 0) : (ListenerUtil.mutListener.listen(12546) ? (dy <= 0) : (ListenerUtil.mutListener.listen(12545) ? (dy > 0) : (ListenerUtil.mutListener.listen(12544) ? (dy < 0) : (ListenerUtil.mutListener.listen(12543) ? (dy == 0) : (dy != 0)))))) || cardViewer != null) : ((ListenerUtil.mutListener.listen(12547) ? (dy >= 0) : (ListenerUtil.mutListener.listen(12546) ? (dy <= 0) : (ListenerUtil.mutListener.listen(12545) ? (dy > 0) : (ListenerUtil.mutListener.listen(12544) ? (dy < 0) : (ListenerUtil.mutListener.listen(12543) ? (dy == 0) : (dy != 0)))))) && cardViewer != null))) {
                        if (!ListenerUtil.mutListener.listen(12549)) {
                            cardViewer.scrollCurrentCardBy(dy);
                        }
                        if (!ListenerUtil.mutListener.listen(12550)) {
                            mSecondFingerX0 = mSecondFingerX;
                        }
                        if (!ListenerUtil.mutListener.listen(12551)) {
                            mSecondFingerY0 = mSecondFingerY;
                        }
                    }
                }
                return true;
            }
        }
        return false;
    }

    private static Point getDisplayDimensions() {
        Display display = ((WindowManager) AnkiDroidApp.getInstance().getApplicationContext().getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        Point point = new Point();
        if (!ListenerUtil.mutListener.listen(12554)) {
            display.getSize(point);
        }
        return point;
    }

    public void onClick(View view) {
        int id = view.getId();
        if (!ListenerUtil.mutListener.listen(12561)) {
            if (id == R.id.pen_color_white) {
                if (!ListenerUtil.mutListener.listen(12560)) {
                    setPenColor(Color.WHITE);
                }
            } else if (id == R.id.pen_color_black) {
                if (!ListenerUtil.mutListener.listen(12559)) {
                    setPenColor(Color.BLACK);
                }
            } else if (id == R.id.pen_color_red) {
                int redPenColor = ContextCompat.getColor(getContext(), R.color.material_red_500);
                if (!ListenerUtil.mutListener.listen(12558)) {
                    setPenColor(redPenColor);
                }
            } else if (id == R.id.pen_color_green) {
                int greenPenColor = ContextCompat.getColor(getContext(), R.color.material_green_500);
                if (!ListenerUtil.mutListener.listen(12557)) {
                    setPenColor(greenPenColor);
                }
            } else if (id == R.id.pen_color_blue) {
                int bluePenColor = ContextCompat.getColor(getContext(), R.color.material_blue_500);
                if (!ListenerUtil.mutListener.listen(12556)) {
                    setPenColor(bluePenColor);
                }
            } else if (id == R.id.pen_color_yellow) {
                int yellowPenColor = ContextCompat.getColor(getContext(), R.color.material_yellow_500);
                if (!ListenerUtil.mutListener.listen(12555)) {
                    setPenColor(yellowPenColor);
                }
            }
        }
    }

    public void setPenColor(int color) {
        if (!ListenerUtil.mutListener.listen(12562)) {
            Timber.d("Setting pen color to %d", color);
        }
        if (!ListenerUtil.mutListener.listen(12563)) {
            mPaint.setColor(color);
        }
        if (!ListenerUtil.mutListener.listen(12564)) {
            mColorPalette.setVisibility(View.GONE);
        }
        if (!ListenerUtil.mutListener.listen(12566)) {
            if (mOnPaintColorChangeListener != null) {
                if (!ListenerUtil.mutListener.listen(12565)) {
                    mOnPaintColorChangeListener.onPaintColorChange(color);
                }
            }
        }
    }

    @VisibleForTesting
    public int getPenColor() {
        return mPaint.getColor();
    }

    public void setOnPaintColorChangeListener(@Nullable OnPaintColorChangeListener mOnPaintColorChangeListener) {
        if (!ListenerUtil.mutListener.listen(12567)) {
            this.mOnPaintColorChangeListener = mOnPaintColorChangeListener;
        }
    }

    /**
     * Keep a stack of all points and paths so that the last stroke can be undone
     * pop() removes the last stroke from the stack, and apply() redraws it to whiteboard.
     */
    private class UndoStack {

        private final Stack<WhiteboardAction> mStack = new Stack<>();

        public void add(WhiteboardAction action) {
            if (!ListenerUtil.mutListener.listen(12568)) {
                mStack.add(action);
            }
        }

        public void clear() {
            if (!ListenerUtil.mutListener.listen(12569)) {
                mStack.clear();
            }
        }

        public int size() {
            return mStack.size();
        }

        public void pop() {
            if (!ListenerUtil.mutListener.listen(12570)) {
                mStack.pop();
            }
        }

        public void apply() {
            if (!ListenerUtil.mutListener.listen(12571)) {
                mBitmap.eraseColor(0);
            }
            if (!ListenerUtil.mutListener.listen(12573)) {
                {
                    long _loopCounter203 = 0;
                    for (WhiteboardAction action : mStack) {
                        ListenerUtil.loopListener.listen("_loopCounter203", ++_loopCounter203);
                        if (!ListenerUtil.mutListener.listen(12572)) {
                            action.apply(mCanvas);
                        }
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(12574)) {
                invalidate();
            }
        }

        public boolean empty() {
            return mStack.empty();
        }
    }

    private interface WhiteboardAction {

        void apply(@NonNull Canvas canvas);
    }

    private static class DrawPoint implements WhiteboardAction {

        private final float mX;

        private final float mY;

        private final Paint mPaint;

        public DrawPoint(float x, float y, Paint paint) {
            mX = x;
            mY = y;
            mPaint = paint;
        }

        @Override
        public void apply(@NonNull Canvas canvas) {
            if (!ListenerUtil.mutListener.listen(12575)) {
                canvas.drawPoint(mX, mY, mPaint);
            }
        }
    }

    private static class DrawPath implements WhiteboardAction {

        private final Path mPath;

        private final Paint mPaint;

        public DrawPath(Path path, Paint paint) {
            mPath = path;
            mPaint = paint;
        }

        @Override
        public void apply(@NonNull Canvas canvas) {
            if (!ListenerUtil.mutListener.listen(12576)) {
                canvas.drawPath(mPath, mPaint);
            }
        }
    }

    public boolean isCurrentlyDrawing() {
        return mCurrentlyDrawing;
    }

    // TODO Tracked in https://github.com/ankidroid/Anki-Android/issues/5304
    @SuppressWarnings({ "deprecation", "RedundantSuppression" })
    protected String saveWhiteboard(Time time) throws FileNotFoundException {
        Bitmap bitmap = Bitmap.createBitmap(this.getWidth(), this.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        File pictures = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        File ankiDroidFolder = new File(pictures, "AnkiDroid");
        if (!ListenerUtil.mutListener.listen(12578)) {
            if (!ankiDroidFolder.exists()) {
                if (!ListenerUtil.mutListener.listen(12577)) {
                    // noinspection ResultOfMethodCallIgnored
                    ankiDroidFolder.mkdirs();
                }
            }
        }
        String baseFileName = "Whiteboard";
        String timeStamp = TimeUtils.getTimestamp(time);
        String finalFileName = baseFileName + timeStamp + ".png";
        File saveWhiteboardImageFile = new File(ankiDroidFolder, finalFileName);
        if (!ListenerUtil.mutListener.listen(12581)) {
            if (foregroundColor != Color.BLACK) {
                if (!ListenerUtil.mutListener.listen(12580)) {
                    canvas.drawColor(Color.BLACK);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(12579)) {
                    canvas.drawColor(Color.WHITE);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(12582)) {
            this.draw(canvas);
        }
        if (!ListenerUtil.mutListener.listen(12583)) {
            bitmap.compress(Bitmap.CompressFormat.JPEG, 95, new FileOutputStream(saveWhiteboardImageFile));
        }
        return saveWhiteboardImageFile.getAbsolutePath();
    }

    @VisibleForTesting
    @CheckResult
    protected int getForegroundColor() {
        return foregroundColor;
    }

    public interface OnPaintColorChangeListener {

        void onPaintColorChange(@Nullable Integer color);
    }
}
