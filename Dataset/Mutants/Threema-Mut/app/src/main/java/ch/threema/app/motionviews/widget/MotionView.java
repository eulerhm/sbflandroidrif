/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
 * Copyright (c) 2017-2021 Threema GmbH
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
package ch.threema.app.motionviews.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.PointF;
import android.os.Build;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.FrameLayout;
import java.util.ArrayList;
import java.util.List;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.core.view.GestureDetectorCompat;
import ch.threema.app.R;
import ch.threema.app.motionviews.gestures.MoveGestureDetector;
import ch.threema.app.motionviews.gestures.RotateGestureDetector;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class MotionView extends FrameLayout {

    private TouchListener touchListener;

    public interface Constants {

        float SELECTED_LAYER_ALPHA = 0.15F;
    }

    public interface MotionViewCallback {

        void onEntitySelected(@Nullable MotionEntity entity);

        void onEntityDoubleTap(@NonNull MotionEntity entity);
    }

    // layers
    private final List<MotionEntity> entities = new ArrayList<>();

    @Nullable
    private MotionEntity selectedEntity;

    private Paint selectedLayerPaint;

    // callback
    @Nullable
    private MotionViewCallback motionViewCallback;

    // gesture detection
    private ScaleGestureDetector scaleGestureDetector;

    private RotateGestureDetector rotateGestureDetector;

    private MoveGestureDetector moveGestureDetector;

    private GestureDetectorCompat gestureDetectorCompat;

    // constructors
    public MotionView(Context context) {
        super(context);
        if (!ListenerUtil.mutListener.listen(31265)) {
            init(context);
        }
    }

    public MotionView(Context context, AttributeSet attrs) {
        super(context, attrs);
        if (!ListenerUtil.mutListener.listen(31266)) {
            init(context);
        }
    }

    public MotionView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        if (!ListenerUtil.mutListener.listen(31267)) {
            init(context);
        }
    }

    @SuppressWarnings("unused")
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public MotionView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        if (!ListenerUtil.mutListener.listen(31268)) {
            init(context);
        }
    }

    private void init(@NonNull Context context) {
        if (!ListenerUtil.mutListener.listen(31269)) {
            /* Typically, if you override {@link #onDraw(android.graphics.Canvas)}
         * you should clear this flag.
		 */
            setWillNotDraw(false);
        }
        if (!ListenerUtil.mutListener.listen(31270)) {
            selectedLayerPaint = new Paint();
        }
        if (!ListenerUtil.mutListener.listen(31275)) {
            selectedLayerPaint.setAlpha((int) ((ListenerUtil.mutListener.listen(31274) ? (255 % Constants.SELECTED_LAYER_ALPHA) : (ListenerUtil.mutListener.listen(31273) ? (255 / Constants.SELECTED_LAYER_ALPHA) : (ListenerUtil.mutListener.listen(31272) ? (255 - Constants.SELECTED_LAYER_ALPHA) : (ListenerUtil.mutListener.listen(31271) ? (255 + Constants.SELECTED_LAYER_ALPHA) : (255 * Constants.SELECTED_LAYER_ALPHA)))))));
        }
        if (!ListenerUtil.mutListener.listen(31276)) {
            selectedLayerPaint.setAntiAlias(true);
        }
        if (!ListenerUtil.mutListener.listen(31277)) {
            // init listeners
            this.scaleGestureDetector = new ScaleGestureDetector(context, new ScaleListener());
        }
        if (!ListenerUtil.mutListener.listen(31278)) {
            this.rotateGestureDetector = new RotateGestureDetector(context, new RotateListener());
        }
        if (!ListenerUtil.mutListener.listen(31279)) {
            this.moveGestureDetector = new MoveGestureDetector(context, new MoveListener());
        }
        if (!ListenerUtil.mutListener.listen(31280)) {
            this.gestureDetectorCompat = new GestureDetectorCompat(context, new TapsListener());
        }
        if (!ListenerUtil.mutListener.listen(31281)) {
            setOnTouchListener(onTouchListener);
        }
        if (!ListenerUtil.mutListener.listen(31282)) {
            updateUI();
        }
    }

    public MotionEntity getSelectedEntity() {
        return selectedEntity;
    }

    public List<MotionEntity> getEntities() {
        return entities;
    }

    public void setMotionViewCallback(@Nullable MotionViewCallback callback) {
        if (!ListenerUtil.mutListener.listen(31283)) {
            this.motionViewCallback = callback;
        }
    }

    public void addEntity(@Nullable MotionEntity entity) {
        if (!ListenerUtil.mutListener.listen(31289)) {
            if (entity != null) {
                if (!ListenerUtil.mutListener.listen(31284)) {
                    initEntityBorder(entity);
                }
                if (!ListenerUtil.mutListener.listen(31285)) {
                    entities.add(entity);
                }
                if (!ListenerUtil.mutListener.listen(31286)) {
                    selectEntity(entity, false);
                }
                if (!ListenerUtil.mutListener.listen(31287)) {
                    touchListener.onAdded(entity);
                }
                if (!ListenerUtil.mutListener.listen(31288)) {
                    unselectEntity();
                }
            }
        }
    }

    public void addEntityAndPosition(@Nullable MotionEntity entity) {
        if (!ListenerUtil.mutListener.listen(31295)) {
            if (entity != null) {
                if (!ListenerUtil.mutListener.listen(31290)) {
                    initEntityBorder(entity);
                }
                if (!ListenerUtil.mutListener.listen(31291)) {
                    initialTranslateAndScale(entity);
                }
                if (!ListenerUtil.mutListener.listen(31292)) {
                    entities.add(entity);
                }
                if (!ListenerUtil.mutListener.listen(31293)) {
                    selectEntity(entity, true);
                }
                if (!ListenerUtil.mutListener.listen(31294)) {
                    touchListener.onAdded(entity);
                }
            }
        }
    }

    private void initEntityBorder(@NonNull MotionEntity entity) {
        // init stroke
        int strokeSize = getResources().getDimensionPixelSize(R.dimen.imagepaint_overlay_select_stroke_width);
        int dashSize = getResources().getDimensionPixelSize(R.dimen.imagepaint_overlay_select_dash_size);
        Paint borderPaint = new Paint();
        if (!ListenerUtil.mutListener.listen(31296)) {
            borderPaint.setStyle(Paint.Style.STROKE);
        }
        if (!ListenerUtil.mutListener.listen(31297)) {
            borderPaint.setStrokeWidth(strokeSize);
        }
        if (!ListenerUtil.mutListener.listen(31298)) {
            borderPaint.setAntiAlias(true);
        }
        if (!ListenerUtil.mutListener.listen(31299)) {
            borderPaint.setColor(ContextCompat.getColor(getContext(), R.color.imagepaint_overlay_select_color));
        }
        if (!ListenerUtil.mutListener.listen(31300)) {
            borderPaint.setPathEffect(new DashPathEffect(new float[] { dashSize, dashSize }, 0));
        }
        if (!ListenerUtil.mutListener.listen(31301)) {
            entity.setBorderPaint(borderPaint);
        }
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        if (!ListenerUtil.mutListener.listen(31302)) {
            super.dispatchDraw(canvas);
        }
        if (!ListenerUtil.mutListener.listen(31304)) {
            // to draw below that - do it in onDraw(Canvas)
            if (selectedEntity != null) {
                if (!ListenerUtil.mutListener.listen(31303)) {
                    selectedEntity.draw(canvas, selectedLayerPaint);
                }
            }
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (!ListenerUtil.mutListener.listen(31305)) {
            drawAllEntities(canvas);
        }
        if (!ListenerUtil.mutListener.listen(31306)) {
            super.onDraw(canvas);
        }
    }

    /**
     *  draws all entities on the canvas
     *
     *  @param canvas Canvas where to draw all entities
     */
    private void drawAllEntities(Canvas canvas) {
        if (!ListenerUtil.mutListener.listen(31313)) {
            {
                long _loopCounter212 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(31312) ? (i >= entities.size()) : (ListenerUtil.mutListener.listen(31311) ? (i <= entities.size()) : (ListenerUtil.mutListener.listen(31310) ? (i > entities.size()) : (ListenerUtil.mutListener.listen(31309) ? (i != entities.size()) : (ListenerUtil.mutListener.listen(31308) ? (i == entities.size()) : (i < entities.size())))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter212", ++_loopCounter212);
                    if (!ListenerUtil.mutListener.listen(31307)) {
                        entities.get(i).draw(canvas, null);
                    }
                }
            }
        }
    }

    /**
     *  as a side effect - the method deselects Entity (if any selected)
     *
     *  @return bitmap with all the Entities at their current positions
     */
    public Bitmap getThumbnailImage() {
        if (!ListenerUtil.mutListener.listen(31314)) {
            selectEntity(null, false);
        }
        Bitmap bmp = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
        if (!ListenerUtil.mutListener.listen(31315)) {
            // which doesn't have transparent pixels, the background will be black
            bmp.eraseColor(Color.WHITE);
        }
        Canvas canvas = new Canvas(bmp);
        if (!ListenerUtil.mutListener.listen(31316)) {
            drawAllEntities(canvas);
        }
        return bmp;
    }

    private void updateUI() {
        if (!ListenerUtil.mutListener.listen(31317)) {
            invalidate();
        }
    }

    private void handleTranslate(PointF delta) {
        if (!ListenerUtil.mutListener.listen(31357)) {
            if ((ListenerUtil.mutListener.listen(31318) ? (selectedEntity != null || !selectedEntity.hasFixedPositionAndSize()) : (selectedEntity != null && !selectedEntity.hasFixedPositionAndSize()))) {
                float newCenterX = selectedEntity.absoluteCenterX() + delta.x;
                float newCenterY = selectedEntity.absoluteCenterY() + delta.y;
                // limit entity center to screen bounds
                boolean needUpdateUI = false;
                if (!ListenerUtil.mutListener.listen(31336)) {
                    if ((ListenerUtil.mutListener.listen(31329) ? ((ListenerUtil.mutListener.listen(31323) ? (newCenterX <= 0) : (ListenerUtil.mutListener.listen(31322) ? (newCenterX > 0) : (ListenerUtil.mutListener.listen(31321) ? (newCenterX < 0) : (ListenerUtil.mutListener.listen(31320) ? (newCenterX != 0) : (ListenerUtil.mutListener.listen(31319) ? (newCenterX == 0) : (newCenterX >= 0)))))) || (ListenerUtil.mutListener.listen(31328) ? (newCenterX >= getWidth()) : (ListenerUtil.mutListener.listen(31327) ? (newCenterX > getWidth()) : (ListenerUtil.mutListener.listen(31326) ? (newCenterX < getWidth()) : (ListenerUtil.mutListener.listen(31325) ? (newCenterX != getWidth()) : (ListenerUtil.mutListener.listen(31324) ? (newCenterX == getWidth()) : (newCenterX <= getWidth()))))))) : ((ListenerUtil.mutListener.listen(31323) ? (newCenterX <= 0) : (ListenerUtil.mutListener.listen(31322) ? (newCenterX > 0) : (ListenerUtil.mutListener.listen(31321) ? (newCenterX < 0) : (ListenerUtil.mutListener.listen(31320) ? (newCenterX != 0) : (ListenerUtil.mutListener.listen(31319) ? (newCenterX == 0) : (newCenterX >= 0)))))) && (ListenerUtil.mutListener.listen(31328) ? (newCenterX >= getWidth()) : (ListenerUtil.mutListener.listen(31327) ? (newCenterX > getWidth()) : (ListenerUtil.mutListener.listen(31326) ? (newCenterX < getWidth()) : (ListenerUtil.mutListener.listen(31325) ? (newCenterX != getWidth()) : (ListenerUtil.mutListener.listen(31324) ? (newCenterX == getWidth()) : (newCenterX <= getWidth()))))))))) {
                        if (!ListenerUtil.mutListener.listen(31334)) {
                            selectedEntity.getLayer().postTranslate((ListenerUtil.mutListener.listen(31333) ? (delta.x % getWidth()) : (ListenerUtil.mutListener.listen(31332) ? (delta.x * getWidth()) : (ListenerUtil.mutListener.listen(31331) ? (delta.x - getWidth()) : (ListenerUtil.mutListener.listen(31330) ? (delta.x + getWidth()) : (delta.x / getWidth()))))), 0.0F);
                        }
                        if (!ListenerUtil.mutListener.listen(31335)) {
                            needUpdateUI = true;
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(31354)) {
                    if ((ListenerUtil.mutListener.listen(31347) ? ((ListenerUtil.mutListener.listen(31341) ? (newCenterY <= 0) : (ListenerUtil.mutListener.listen(31340) ? (newCenterY > 0) : (ListenerUtil.mutListener.listen(31339) ? (newCenterY < 0) : (ListenerUtil.mutListener.listen(31338) ? (newCenterY != 0) : (ListenerUtil.mutListener.listen(31337) ? (newCenterY == 0) : (newCenterY >= 0)))))) || (ListenerUtil.mutListener.listen(31346) ? (newCenterY >= getHeight()) : (ListenerUtil.mutListener.listen(31345) ? (newCenterY > getHeight()) : (ListenerUtil.mutListener.listen(31344) ? (newCenterY < getHeight()) : (ListenerUtil.mutListener.listen(31343) ? (newCenterY != getHeight()) : (ListenerUtil.mutListener.listen(31342) ? (newCenterY == getHeight()) : (newCenterY <= getHeight()))))))) : ((ListenerUtil.mutListener.listen(31341) ? (newCenterY <= 0) : (ListenerUtil.mutListener.listen(31340) ? (newCenterY > 0) : (ListenerUtil.mutListener.listen(31339) ? (newCenterY < 0) : (ListenerUtil.mutListener.listen(31338) ? (newCenterY != 0) : (ListenerUtil.mutListener.listen(31337) ? (newCenterY == 0) : (newCenterY >= 0)))))) && (ListenerUtil.mutListener.listen(31346) ? (newCenterY >= getHeight()) : (ListenerUtil.mutListener.listen(31345) ? (newCenterY > getHeight()) : (ListenerUtil.mutListener.listen(31344) ? (newCenterY < getHeight()) : (ListenerUtil.mutListener.listen(31343) ? (newCenterY != getHeight()) : (ListenerUtil.mutListener.listen(31342) ? (newCenterY == getHeight()) : (newCenterY <= getHeight()))))))))) {
                        if (!ListenerUtil.mutListener.listen(31352)) {
                            selectedEntity.getLayer().postTranslate(0.0F, (ListenerUtil.mutListener.listen(31351) ? (delta.y % getHeight()) : (ListenerUtil.mutListener.listen(31350) ? (delta.y * getHeight()) : (ListenerUtil.mutListener.listen(31349) ? (delta.y - getHeight()) : (ListenerUtil.mutListener.listen(31348) ? (delta.y + getHeight()) : (delta.y / getHeight()))))));
                        }
                        if (!ListenerUtil.mutListener.listen(31353)) {
                            needUpdateUI = true;
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(31356)) {
                    if (needUpdateUI) {
                        if (!ListenerUtil.mutListener.listen(31355)) {
                            updateUI();
                        }
                    }
                }
            }
        }
    }

    private void initialTranslateAndScale(@NonNull MotionEntity entity) {
        if (!ListenerUtil.mutListener.listen(31358)) {
            entity.moveToCanvasCenter();
        }
        if (!ListenerUtil.mutListener.listen(31359)) {
            entity.getLayer().setScale(entity.getLayer().initialScale());
        }
    }

    private void selectEntity(@Nullable MotionEntity entity, boolean updateCallback) {
        if (!ListenerUtil.mutListener.listen(31361)) {
            if (selectedEntity != null) {
                if (!ListenerUtil.mutListener.listen(31360)) {
                    selectedEntity.setIsSelected(false);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(31363)) {
            if (entity != null) {
                if (!ListenerUtil.mutListener.listen(31362)) {
                    entity.setIsSelected(true);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(31364)) {
            selectedEntity = entity;
        }
        if (!ListenerUtil.mutListener.listen(31365)) {
            invalidate();
        }
        if (!ListenerUtil.mutListener.listen(31368)) {
            if ((ListenerUtil.mutListener.listen(31366) ? (updateCallback || motionViewCallback != null) : (updateCallback && motionViewCallback != null))) {
                if (!ListenerUtil.mutListener.listen(31367)) {
                    motionViewCallback.onEntitySelected(entity);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(31369)) {
            touchListener.onSelected(selectedEntity != null);
        }
    }

    public void unselectEntity() {
        if (!ListenerUtil.mutListener.listen(31371)) {
            if (selectedEntity != null) {
                if (!ListenerUtil.mutListener.listen(31370)) {
                    selectEntity(null, true);
                }
            }
        }
    }

    @Nullable
    private MotionEntity findEntityAtPoint(float x, float y) {
        MotionEntity selected = null;
        PointF p = new PointF(x, y);
        if (!ListenerUtil.mutListener.listen(31383)) {
            {
                long _loopCounter213 = 0;
                for (int i = (ListenerUtil.mutListener.listen(31382) ? (entities.size() % 1) : (ListenerUtil.mutListener.listen(31381) ? (entities.size() / 1) : (ListenerUtil.mutListener.listen(31380) ? (entities.size() * 1) : (ListenerUtil.mutListener.listen(31379) ? (entities.size() + 1) : (entities.size() - 1))))); (ListenerUtil.mutListener.listen(31378) ? (i <= 0) : (ListenerUtil.mutListener.listen(31377) ? (i > 0) : (ListenerUtil.mutListener.listen(31376) ? (i < 0) : (ListenerUtil.mutListener.listen(31375) ? (i != 0) : (ListenerUtil.mutListener.listen(31374) ? (i == 0) : (i >= 0)))))); i--) {
                    ListenerUtil.loopListener.listen("_loopCounter213", ++_loopCounter213);
                    if (!ListenerUtil.mutListener.listen(31373)) {
                        if (entities.get(i).pointInLayerRect(p)) {
                            if (!ListenerUtil.mutListener.listen(31372)) {
                                selected = entities.get(i);
                            }
                            break;
                        }
                    }
                }
            }
        }
        return selected;
    }

    private void updateSelectionOnTap(MotionEvent e) {
        MotionEntity entity = findEntityAtPoint(e.getX(), e.getY());
        if (!ListenerUtil.mutListener.listen(31384)) {
            selectEntity(entity, true);
        }
    }

    private void updateOnLongPress(MotionEvent e) {
        if (!ListenerUtil.mutListener.listen(31385)) {
            // if layer is currently selected and point inside layer - move it to front
            updateSelectionOnTap(e);
        }
        if (!ListenerUtil.mutListener.listen(31388)) {
            if (selectedEntity != null) {
                PointF p = new PointF(e.getX(), e.getY());
                if (!ListenerUtil.mutListener.listen(31387)) {
                    if (selectedEntity.pointInLayerRect(p)) {
                        if (!ListenerUtil.mutListener.listen(31386)) {
                            touchListener.onLongClick(selectedEntity, (int) e.getX(), (int) e.getY());
                        }
                    }
                }
            }
        }
    }

    private void bringLayerToFront(@NonNull MotionEntity entity) {
        if (!ListenerUtil.mutListener.listen(31391)) {
            // removing and adding brings layer to front
            if (entities.remove(entity)) {
                if (!ListenerUtil.mutListener.listen(31389)) {
                    entities.add(entity);
                }
                if (!ListenerUtil.mutListener.listen(31390)) {
                    invalidate();
                }
            }
        }
    }

    private void moveEntityToBack(@Nullable MotionEntity entity) {
        if (!ListenerUtil.mutListener.listen(31392)) {
            if (entity == null) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(31395)) {
            if (entities.remove(entity)) {
                if (!ListenerUtil.mutListener.listen(31393)) {
                    entities.add(0, entity);
                }
                if (!ListenerUtil.mutListener.listen(31394)) {
                    invalidate();
                }
            }
        }
    }

    public void flipSelectedEntity() {
        if (!ListenerUtil.mutListener.listen(31396)) {
            if (selectedEntity == null) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(31397)) {
            selectedEntity.getLayer().flip();
        }
        if (!ListenerUtil.mutListener.listen(31398)) {
            invalidate();
        }
    }

    public void moveSelectedEntityToFront() {
        if (!ListenerUtil.mutListener.listen(31399)) {
            if (selectedEntity == null) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(31400)) {
            bringLayerToFront(selectedEntity);
        }
    }

    public void moveSelectedBack() {
        if (!ListenerUtil.mutListener.listen(31401)) {
            moveEntityToBack(selectedEntity);
        }
    }

    public void deletedSelectedEntity() {
        if (!ListenerUtil.mutListener.listen(31402)) {
            if (selectedEntity == null) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(31403)) {
            touchListener.onDeleted(selectedEntity);
        }
        if (!ListenerUtil.mutListener.listen(31407)) {
            if (entities.remove(selectedEntity)) {
                if (!ListenerUtil.mutListener.listen(31404)) {
                    selectedEntity.release();
                }
                if (!ListenerUtil.mutListener.listen(31405)) {
                    selectedEntity = null;
                }
                if (!ListenerUtil.mutListener.listen(31406)) {
                    invalidate();
                }
            }
        }
    }

    public void deleteEntity(MotionEntity entity) {
        if (!ListenerUtil.mutListener.listen(31410)) {
            if (entities.contains(entity)) {
                if (!ListenerUtil.mutListener.listen(31408)) {
                    selectedEntity = entity;
                }
                if (!ListenerUtil.mutListener.listen(31409)) {
                    deletedSelectedEntity();
                }
            }
        }
    }

    // memory
    public void release() {
        if (!ListenerUtil.mutListener.listen(31412)) {
            {
                long _loopCounter214 = 0;
                for (MotionEntity entity : entities) {
                    ListenerUtil.loopListener.listen("_loopCounter214", ++_loopCounter214);
                    if (!ListenerUtil.mutListener.listen(31411)) {
                        entity.release();
                    }
                }
            }
        }
    }

    private final View.OnTouchListener onTouchListener = new View.OnTouchListener() {

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            if (!ListenerUtil.mutListener.listen(31415)) {
                switch(event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        if (!ListenerUtil.mutListener.listen(31413)) {
                            touchListener.onTouchDown();
                        }
                        break;
                    case MotionEvent.ACTION_UP:
                        if (!ListenerUtil.mutListener.listen(31414)) {
                            touchListener.onTouchUp();
                        }
                        break;
                    default:
                        break;
                }
            }
            if (!ListenerUtil.mutListener.listen(31420)) {
                if (scaleGestureDetector != null) {
                    if (!ListenerUtil.mutListener.listen(31416)) {
                        scaleGestureDetector.onTouchEvent(event);
                    }
                    if (!ListenerUtil.mutListener.listen(31417)) {
                        rotateGestureDetector.onTouchEvent(event);
                    }
                    if (!ListenerUtil.mutListener.listen(31418)) {
                        moveGestureDetector.onTouchEvent(event);
                    }
                    if (!ListenerUtil.mutListener.listen(31419)) {
                        gestureDetectorCompat.onTouchEvent(event);
                    }
                }
            }
            return true;
        }
    };

    private class TapsListener extends GestureDetector.SimpleOnGestureListener {

        @Override
        public boolean onDoubleTap(MotionEvent e) {
            if (!ListenerUtil.mutListener.listen(31423)) {
                if ((ListenerUtil.mutListener.listen(31421) ? (motionViewCallback != null || selectedEntity != null) : (motionViewCallback != null && selectedEntity != null))) {
                    if (!ListenerUtil.mutListener.listen(31422)) {
                        motionViewCallback.onEntityDoubleTap(selectedEntity);
                    }
                }
            }
            return true;
        }

        @Override
        public void onLongPress(MotionEvent e) {
            if (!ListenerUtil.mutListener.listen(31424)) {
                updateOnLongPress(e);
            }
        }

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            if (!ListenerUtil.mutListener.listen(31425)) {
                updateSelectionOnTap(e);
            }
            return true;
        }
    }

    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {

        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            if (!ListenerUtil.mutListener.listen(31433)) {
                if ((ListenerUtil.mutListener.listen(31426) ? (selectedEntity != null || !selectedEntity.hasFixedPositionAndSize()) : (selectedEntity != null && !selectedEntity.hasFixedPositionAndSize()))) {
                    float scaleFactorDiff = detector.getScaleFactor();
                    if (!ListenerUtil.mutListener.listen(31431)) {
                        selectedEntity.getLayer().postScale((ListenerUtil.mutListener.listen(31430) ? (scaleFactorDiff % 1.0F) : (ListenerUtil.mutListener.listen(31429) ? (scaleFactorDiff / 1.0F) : (ListenerUtil.mutListener.listen(31428) ? (scaleFactorDiff * 1.0F) : (ListenerUtil.mutListener.listen(31427) ? (scaleFactorDiff + 1.0F) : (scaleFactorDiff - 1.0F))))));
                    }
                    if (!ListenerUtil.mutListener.listen(31432)) {
                        updateUI();
                    }
                }
            }
            return true;
        }
    }

    private class RotateListener extends RotateGestureDetector.SimpleOnRotateGestureListener {

        @Override
        public boolean onRotate(RotateGestureDetector detector) {
            if (!ListenerUtil.mutListener.listen(31437)) {
                if ((ListenerUtil.mutListener.listen(31434) ? (selectedEntity != null || !selectedEntity.hasFixedPositionAndSize()) : (selectedEntity != null && !selectedEntity.hasFixedPositionAndSize()))) {
                    if (!ListenerUtil.mutListener.listen(31435)) {
                        selectedEntity.getLayer().postRotate(-detector.getRotationDegreesDelta());
                    }
                    if (!ListenerUtil.mutListener.listen(31436)) {
                        updateUI();
                    }
                }
            }
            return true;
        }
    }

    private class MoveListener extends MoveGestureDetector.SimpleOnMoveGestureListener {

        @Override
        public boolean onMove(MoveGestureDetector detector) {
            if (!ListenerUtil.mutListener.listen(31438)) {
                handleTranslate(detector.getFocusDelta());
            }
            return true;
        }
    }

    public void renderOverlay(Canvas canvas) {
        if (!ListenerUtil.mutListener.listen(31439)) {
            unselectEntity();
        }
        if (!ListenerUtil.mutListener.listen(31440)) {
            draw(canvas);
        }
    }

    public int getEntitiesCount() {
        return entities.size();
    }

    public void setTouchListener(TouchListener touchListener) {
        if (!ListenerUtil.mutListener.listen(31441)) {
            this.touchListener = touchListener;
        }
    }

    public interface TouchListener {

        void onSelected(boolean isSelected);

        void onLongClick(MotionEntity entity, int x, int y);

        void onAdded(MotionEntity entity);

        void onDeleted(MotionEntity entity);

        void onTouchUp();

        void onTouchDown();
    }
}
