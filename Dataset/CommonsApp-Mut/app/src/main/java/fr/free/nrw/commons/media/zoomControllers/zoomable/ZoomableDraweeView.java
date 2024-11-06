package fr.free.nrw.commons.media.zoomControllers.zoomable;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.graphics.drawable.Animatable;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import androidx.annotation.Nullable;
import androidx.core.view.ScrollingView;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.logging.FLog;
import com.facebook.drawee.controller.AbstractDraweeController;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.drawee.controller.ControllerListener;
import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.generic.GenericDraweeHierarchyInflater;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.DraweeView;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * DraweeView that has zoomable capabilities.
 *
 * <p>Once the image loads, pinch-to-zoom and translation gestures are enabled.
 */
public class ZoomableDraweeView extends DraweeView<GenericDraweeHierarchy> implements ScrollingView {

    private static final Class<?> TAG = ZoomableDraweeView.class;

    private static final float HUGE_IMAGE_SCALE_FACTOR_THRESHOLD = 1.1f;

    private final RectF mImageBounds = new RectF();

    private final RectF mViewBounds = new RectF();

    private DraweeController mHugeImageController;

    private ZoomableController mZoomableController;

    private GestureDetector mTapGestureDetector;

    private boolean mAllowTouchInterceptionWhileZoomed = true;

    private boolean mIsDialtoneEnabled = false;

    private boolean mZoomingEnabled = true;

    private TransformationListener transformationListener;

    private final ControllerListener mControllerListener = new BaseControllerListener<Object>() {

        @Override
        public void onFinalImageSet(String id, @Nullable Object imageInfo, @Nullable Animatable animatable) {
            if (!ListenerUtil.mutListener.listen(8252)) {
                ZoomableDraweeView.this.onFinalImageSet();
            }
        }

        @Override
        public void onRelease(String id) {
            if (!ListenerUtil.mutListener.listen(8253)) {
                ZoomableDraweeView.this.onRelease();
            }
        }
    };

    private final ZoomableController.Listener mZoomableListener = new ZoomableController.Listener() {

        @Override
        public void onTransformBegin(Matrix transform) {
        }

        @Override
        public void onTransformChanged(Matrix transform) {
            if (!ListenerUtil.mutListener.listen(8254)) {
                ZoomableDraweeView.this.onTransformChanged(transform);
            }
        }

        @Override
        public void onTransformEnd(Matrix transform) {
            if (!ListenerUtil.mutListener.listen(8256)) {
                if (null != transformationListener) {
                    if (!ListenerUtil.mutListener.listen(8255)) {
                        transformationListener.onTransformationEnd();
                    }
                }
            }
        }
    };

    public void setTransformationListener(TransformationListener transformationListener) {
        if (!ListenerUtil.mutListener.listen(8257)) {
            this.transformationListener = transformationListener;
        }
    }

    private final GestureListenerWrapper mTapListenerWrapper = new GestureListenerWrapper();

    public ZoomableDraweeView(Context context, GenericDraweeHierarchy hierarchy) {
        super(context);
        if (!ListenerUtil.mutListener.listen(8258)) {
            setHierarchy(hierarchy);
        }
        if (!ListenerUtil.mutListener.listen(8259)) {
            init();
        }
    }

    public ZoomableDraweeView(Context context) {
        super(context);
        if (!ListenerUtil.mutListener.listen(8260)) {
            inflateHierarchy(context, null);
        }
        if (!ListenerUtil.mutListener.listen(8261)) {
            init();
        }
    }

    public ZoomableDraweeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        if (!ListenerUtil.mutListener.listen(8262)) {
            inflateHierarchy(context, attrs);
        }
        if (!ListenerUtil.mutListener.listen(8263)) {
            init();
        }
    }

    public ZoomableDraweeView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        if (!ListenerUtil.mutListener.listen(8264)) {
            inflateHierarchy(context, attrs);
        }
        if (!ListenerUtil.mutListener.listen(8265)) {
            init();
        }
    }

    protected void inflateHierarchy(Context context, @Nullable AttributeSet attrs) {
        Resources resources = context.getResources();
        GenericDraweeHierarchyBuilder builder = new GenericDraweeHierarchyBuilder(resources).setActualImageScaleType(ScalingUtils.ScaleType.FIT_CENTER);
        if (!ListenerUtil.mutListener.listen(8266)) {
            GenericDraweeHierarchyInflater.updateBuilder(builder, context, attrs);
        }
        if (!ListenerUtil.mutListener.listen(8267)) {
            setAspectRatio(builder.getDesiredAspectRatio());
        }
        if (!ListenerUtil.mutListener.listen(8268)) {
            setHierarchy(builder.build());
        }
    }

    private void init() {
        if (!ListenerUtil.mutListener.listen(8269)) {
            mZoomableController = createZoomableController();
        }
        if (!ListenerUtil.mutListener.listen(8270)) {
            mZoomableController.setListener(mZoomableListener);
        }
        if (!ListenerUtil.mutListener.listen(8271)) {
            mTapGestureDetector = new GestureDetector(getContext(), mTapListenerWrapper);
        }
    }

    public void setIsDialtoneEnabled(boolean isDialtoneEnabled) {
        if (!ListenerUtil.mutListener.listen(8272)) {
            mIsDialtoneEnabled = isDialtoneEnabled;
        }
    }

    /**
     * Gets the original image bounds, in view-absolute coordinates.
     *
     * <p>The original image bounds are those reported by the hierarchy. The hierarchy itself may
     * apply scaling on its own (e.g. due to scale type) so the reported bounds are not necessarily
     * the same as the actual bitmap dimensions. In other words, the original image bounds correspond
     * to the image bounds within this view when no zoomable transformation is applied, but including
     * the potential scaling of the hierarchy. Having the actual bitmap dimensions abstracted away
     * from this view greatly simplifies implementation because the actual bitmap may change (e.g.
     * when a high-res image arrives and replaces the previously set low-res image). With proper
     * hierarchy scaling (e.g. FIT_CENTER), this underlying change will not affect this view nor the
     * zoomable transformation in any way.
     */
    protected void getImageBounds(RectF outBounds) {
        if (!ListenerUtil.mutListener.listen(8273)) {
            getHierarchy().getActualImageBounds(outBounds);
        }
    }

    /**
     * Gets the bounds used to limit the translation, in view-absolute coordinates.
     *
     * <p>These bounds are passed to the zoomable controller in order to limit the translation. The
     * image is attempted to be centered within the limit bounds if the transformed image is smaller.
     * There will be no empty spaces within the limit bounds if the transformed image is bigger. This
     * applies to each dimension (horizontal and vertical) independently.
     *
     * <p>Unless overridden by a subclass, these bounds are same as the view bounds.
     */
    protected void getLimitBounds(RectF outBounds) {
        if (!ListenerUtil.mutListener.listen(8274)) {
            outBounds.set(0, 0, getWidth(), getHeight());
        }
    }

    /**
     * Sets a custom zoomable controller, instead of using the default one.
     */
    public void setZoomableController(ZoomableController zoomableController) {
        if (!ListenerUtil.mutListener.listen(8275)) {
            Preconditions.checkNotNull(zoomableController);
        }
        if (!ListenerUtil.mutListener.listen(8276)) {
            mZoomableController.setListener(null);
        }
        if (!ListenerUtil.mutListener.listen(8277)) {
            mZoomableController = zoomableController;
        }
        if (!ListenerUtil.mutListener.listen(8278)) {
            mZoomableController.setListener(mZoomableListener);
        }
    }

    /**
     * Gets the zoomable controller.
     *
     * <p>Zoomable controller can be used to zoom to point, or to map point from view to image
     * coordinates for instance.
     */
    public ZoomableController getZoomableController() {
        return mZoomableController;
    }

    /**
     * Check whether the parent view can intercept touch events while zoomed. This can be used, for
     * example, to swipe between images in a view pager while zoomed.
     *
     * @return true if touch events can be intercepted
     */
    public boolean allowsTouchInterceptionWhileZoomed() {
        return mAllowTouchInterceptionWhileZoomed;
    }

    /**
     * If this is set to true, parent views can intercept touch events while the view is zoomed. For
     * example, this can be used to swipe between images in a view pager while zoomed.
     *
     * @param allowTouchInterceptionWhileZoomed true if the parent needs to intercept touches
     */
    public void setAllowTouchInterceptionWhileZoomed(boolean allowTouchInterceptionWhileZoomed) {
        if (!ListenerUtil.mutListener.listen(8279)) {
            mAllowTouchInterceptionWhileZoomed = allowTouchInterceptionWhileZoomed;
        }
    }

    /**
     * Sets the tap listener.
     */
    public void setTapListener(GestureDetector.SimpleOnGestureListener tapListener) {
        if (!ListenerUtil.mutListener.listen(8280)) {
            mTapListenerWrapper.setListener(tapListener);
        }
    }

    /**
     * Sets whether long-press tap detection is enabled. Unfortunately, long-press conflicts with
     * onDoubleTapEvent.
     */
    public void setIsLongpressEnabled(boolean enabled) {
        if (!ListenerUtil.mutListener.listen(8281)) {
            mTapGestureDetector.setIsLongpressEnabled(enabled);
        }
    }

    public void setZoomingEnabled(boolean zoomingEnabled) {
        if (!ListenerUtil.mutListener.listen(8282)) {
            mZoomingEnabled = zoomingEnabled;
        }
        if (!ListenerUtil.mutListener.listen(8283)) {
            mZoomableController.setEnabled(false);
        }
    }

    /**
     * Sets the image controller.
     */
    @Override
    public void setController(@Nullable DraweeController controller) {
        if (!ListenerUtil.mutListener.listen(8284)) {
            setControllers(controller, null);
        }
    }

    /**
     * Sets the controllers for the normal and huge image.
     *
     * <p>The huge image controller is used after the image gets scaled above a certain threshold.
     *
     * <p>IMPORTANT: in order to avoid a flicker when switching to the huge image, the huge image
     * controller should have the normal-image-uri set as its low-res-uri.
     *
     * @param controller controller to be initially used
     * @param hugeImageController controller to be used after the client starts zooming-in
     */
    public void setControllers(@Nullable DraweeController controller, @Nullable DraweeController hugeImageController) {
        if (!ListenerUtil.mutListener.listen(8285)) {
            setControllersInternal(null, null);
        }
        if (!ListenerUtil.mutListener.listen(8286)) {
            mZoomableController.setEnabled(false);
        }
        if (!ListenerUtil.mutListener.listen(8287)) {
            setControllersInternal(controller, hugeImageController);
        }
    }

    private void setControllersInternal(@Nullable DraweeController controller, @Nullable DraweeController hugeImageController) {
        if (!ListenerUtil.mutListener.listen(8288)) {
            removeControllerListener(getController());
        }
        if (!ListenerUtil.mutListener.listen(8289)) {
            addControllerListener(controller);
        }
        if (!ListenerUtil.mutListener.listen(8290)) {
            mHugeImageController = hugeImageController;
        }
        if (!ListenerUtil.mutListener.listen(8291)) {
            super.setController(controller);
        }
    }

    private void maybeSetHugeImageController() {
        if (!ListenerUtil.mutListener.listen(8299)) {
            if ((ListenerUtil.mutListener.listen(8297) ? (mHugeImageController != null || (ListenerUtil.mutListener.listen(8296) ? (mZoomableController.getScaleFactor() >= HUGE_IMAGE_SCALE_FACTOR_THRESHOLD) : (ListenerUtil.mutListener.listen(8295) ? (mZoomableController.getScaleFactor() <= HUGE_IMAGE_SCALE_FACTOR_THRESHOLD) : (ListenerUtil.mutListener.listen(8294) ? (mZoomableController.getScaleFactor() < HUGE_IMAGE_SCALE_FACTOR_THRESHOLD) : (ListenerUtil.mutListener.listen(8293) ? (mZoomableController.getScaleFactor() != HUGE_IMAGE_SCALE_FACTOR_THRESHOLD) : (ListenerUtil.mutListener.listen(8292) ? (mZoomableController.getScaleFactor() == HUGE_IMAGE_SCALE_FACTOR_THRESHOLD) : (mZoomableController.getScaleFactor() > HUGE_IMAGE_SCALE_FACTOR_THRESHOLD))))))) : (mHugeImageController != null && (ListenerUtil.mutListener.listen(8296) ? (mZoomableController.getScaleFactor() >= HUGE_IMAGE_SCALE_FACTOR_THRESHOLD) : (ListenerUtil.mutListener.listen(8295) ? (mZoomableController.getScaleFactor() <= HUGE_IMAGE_SCALE_FACTOR_THRESHOLD) : (ListenerUtil.mutListener.listen(8294) ? (mZoomableController.getScaleFactor() < HUGE_IMAGE_SCALE_FACTOR_THRESHOLD) : (ListenerUtil.mutListener.listen(8293) ? (mZoomableController.getScaleFactor() != HUGE_IMAGE_SCALE_FACTOR_THRESHOLD) : (ListenerUtil.mutListener.listen(8292) ? (mZoomableController.getScaleFactor() == HUGE_IMAGE_SCALE_FACTOR_THRESHOLD) : (mZoomableController.getScaleFactor() > HUGE_IMAGE_SCALE_FACTOR_THRESHOLD))))))))) {
                if (!ListenerUtil.mutListener.listen(8298)) {
                    setControllersInternal(mHugeImageController, null);
                }
            }
        }
    }

    private void removeControllerListener(DraweeController controller) {
        if (!ListenerUtil.mutListener.listen(8301)) {
            if (controller instanceof AbstractDraweeController) {
                if (!ListenerUtil.mutListener.listen(8300)) {
                    ((AbstractDraweeController) controller).removeControllerListener(mControllerListener);
                }
            }
        }
    }

    private void addControllerListener(DraweeController controller) {
        if (!ListenerUtil.mutListener.listen(8303)) {
            if (controller instanceof AbstractDraweeController) {
                if (!ListenerUtil.mutListener.listen(8302)) {
                    ((AbstractDraweeController) controller).addControllerListener(mControllerListener);
                }
            }
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int saveCount = canvas.save();
        if (!ListenerUtil.mutListener.listen(8304)) {
            canvas.concat(mZoomableController.getTransform());
        }
        try {
            if (!ListenerUtil.mutListener.listen(8308)) {
                super.onDraw(canvas);
            }
        } catch (Exception e) {
            DraweeController controller = getController();
            if (!ListenerUtil.mutListener.listen(8307)) {
                if ((ListenerUtil.mutListener.listen(8305) ? (controller != null || controller instanceof AbstractDraweeController) : (controller != null && controller instanceof AbstractDraweeController))) {
                    Object callerContext = ((AbstractDraweeController) controller).getCallerContext();
                    if (!ListenerUtil.mutListener.listen(8306)) {
                        if (callerContext != null) {
                            throw new RuntimeException(String.format("Exception in onDraw, callerContext=%s", callerContext.toString()), e);
                        }
                    }
                }
            }
            throw e;
        }
        if (!ListenerUtil.mutListener.listen(8309)) {
            canvas.restoreToCount(saveCount);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int a = event.getActionMasked();
        if (!ListenerUtil.mutListener.listen(8310)) {
            FLog.v(getLogTag(), "onTouchEvent: %d, view %x, received", a, this.hashCode());
        }
        if (!ListenerUtil.mutListener.listen(8313)) {
            if ((ListenerUtil.mutListener.listen(8311) ? (!mIsDialtoneEnabled || mTapGestureDetector.onTouchEvent(event)) : (!mIsDialtoneEnabled && mTapGestureDetector.onTouchEvent(event)))) {
                if (!ListenerUtil.mutListener.listen(8312)) {
                    FLog.v(getLogTag(), "onTouchEvent: %d, view %x, handled by tap gesture detector", a, this.hashCode());
                }
                return true;
            }
        }
        if (!ListenerUtil.mutListener.listen(8319)) {
            if ((ListenerUtil.mutListener.listen(8314) ? (!mIsDialtoneEnabled || mZoomableController.onTouchEvent(event)) : (!mIsDialtoneEnabled && mZoomableController.onTouchEvent(event)))) {
                if (!ListenerUtil.mutListener.listen(8315)) {
                    FLog.v(getLogTag(), "onTouchEvent: %d, view %x, handled by zoomable controller", a, this.hashCode());
                }
                if (!ListenerUtil.mutListener.listen(8318)) {
                    if ((ListenerUtil.mutListener.listen(8316) ? (!mAllowTouchInterceptionWhileZoomed || !mZoomableController.isIdentity()) : (!mAllowTouchInterceptionWhileZoomed && !mZoomableController.isIdentity()))) {
                        if (!ListenerUtil.mutListener.listen(8317)) {
                            getParent().requestDisallowInterceptTouchEvent(true);
                        }
                    }
                }
                return true;
            }
        }
        if (!ListenerUtil.mutListener.listen(8321)) {
            if (super.onTouchEvent(event)) {
                if (!ListenerUtil.mutListener.listen(8320)) {
                    FLog.v(getLogTag(), "onTouchEvent: %d, view %x, handled by the super", a, this.hashCode());
                }
                return true;
            }
        }
        // To prevent that we explicitly send one last cancel event when returning false.
        MotionEvent cancelEvent = MotionEvent.obtain(event);
        if (!ListenerUtil.mutListener.listen(8322)) {
            cancelEvent.setAction(MotionEvent.ACTION_CANCEL);
        }
        if (!ListenerUtil.mutListener.listen(8323)) {
            mTapGestureDetector.onTouchEvent(cancelEvent);
        }
        if (!ListenerUtil.mutListener.listen(8324)) {
            mZoomableController.onTouchEvent(cancelEvent);
        }
        if (!ListenerUtil.mutListener.listen(8325)) {
            cancelEvent.recycle();
        }
        return false;
    }

    @Override
    public int computeHorizontalScrollRange() {
        return mZoomableController.computeHorizontalScrollRange();
    }

    @Override
    public int computeHorizontalScrollOffset() {
        return mZoomableController.computeHorizontalScrollOffset();
    }

    @Override
    public int computeHorizontalScrollExtent() {
        return mZoomableController.computeHorizontalScrollExtent();
    }

    @Override
    public int computeVerticalScrollRange() {
        return mZoomableController.computeVerticalScrollRange();
    }

    @Override
    public int computeVerticalScrollOffset() {
        return mZoomableController.computeVerticalScrollOffset();
    }

    @Override
    public int computeVerticalScrollExtent() {
        return mZoomableController.computeVerticalScrollExtent();
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        if (!ListenerUtil.mutListener.listen(8326)) {
            FLog.v(getLogTag(), "onLayout: view %x", this.hashCode());
        }
        if (!ListenerUtil.mutListener.listen(8327)) {
            super.onLayout(changed, left, top, right, bottom);
        }
        if (!ListenerUtil.mutListener.listen(8328)) {
            updateZoomableControllerBounds();
        }
    }

    private void onFinalImageSet() {
        if (!ListenerUtil.mutListener.listen(8329)) {
            FLog.v(getLogTag(), "onFinalImageSet: view %x", this.hashCode());
        }
        if (!ListenerUtil.mutListener.listen(8333)) {
            if ((ListenerUtil.mutListener.listen(8330) ? (!mZoomableController.isEnabled() || mZoomingEnabled) : (!mZoomableController.isEnabled() && mZoomingEnabled))) {
                if (!ListenerUtil.mutListener.listen(8331)) {
                    mZoomableController.setEnabled(true);
                }
                if (!ListenerUtil.mutListener.listen(8332)) {
                    updateZoomableControllerBounds();
                }
            }
        }
    }

    private void onRelease() {
        if (!ListenerUtil.mutListener.listen(8334)) {
            FLog.v(getLogTag(), "onRelease: view %x", this.hashCode());
        }
        if (!ListenerUtil.mutListener.listen(8335)) {
            mZoomableController.setEnabled(false);
        }
    }

    protected void onTransformChanged(Matrix transform) {
        if (!ListenerUtil.mutListener.listen(8336)) {
            FLog.v(getLogTag(), "onTransformChanged: view %x, transform: %s", this.hashCode(), transform);
        }
        if (!ListenerUtil.mutListener.listen(8337)) {
            maybeSetHugeImageController();
        }
        if (!ListenerUtil.mutListener.listen(8338)) {
            invalidate();
        }
    }

    protected void updateZoomableControllerBounds() {
        if (!ListenerUtil.mutListener.listen(8339)) {
            getImageBounds(mImageBounds);
        }
        if (!ListenerUtil.mutListener.listen(8340)) {
            getLimitBounds(mViewBounds);
        }
        if (!ListenerUtil.mutListener.listen(8341)) {
            mZoomableController.setImageBounds(mImageBounds);
        }
        if (!ListenerUtil.mutListener.listen(8342)) {
            mZoomableController.setViewBounds(mViewBounds);
        }
        if (!ListenerUtil.mutListener.listen(8343)) {
            FLog.v(getLogTag(), "updateZoomableControllerBounds: view %x, view bounds: %s, image bounds: %s", this.hashCode(), mViewBounds, mImageBounds);
        }
    }

    protected Class<?> getLogTag() {
        return TAG;
    }

    protected ZoomableController createZoomableController() {
        return AnimatedZoomableController.newInstance();
    }

    /**
     * Use this, If someone is willing to listen to scale change
     */
    public interface TransformationListener {

        void onTransformationEnd();
    }
}
