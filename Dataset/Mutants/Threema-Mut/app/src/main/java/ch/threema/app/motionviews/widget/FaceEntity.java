/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
 * Copyright (c) 2021 Threema GmbH
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

import android.graphics.Bitmap;
import android.graphics.PointF;
import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import ch.threema.app.motionviews.FaceItem;
import ch.threema.app.motionviews.viewmodel.Layer;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public abstract class FaceEntity extends MotionEntity {

    public static final float BLUR_RADIUS = 1.5f;

    @NonNull
    protected final FaceItem faceItem;

    @NonNull
    protected final Bitmap bitmap;

    public FaceEntity(@NonNull Layer layer, @NonNull FaceItem faceItem, @IntRange(from = 1) int originalImageWidth, @IntRange(from = 1) int originalImageHeight, @IntRange(from = 1) int canvasWidth, @IntRange(from = 1) int canvasHeight) {
        super(layer, canvasWidth, canvasHeight);
        this.faceItem = faceItem;
        this.bitmap = faceItem.getBitmap();
        float width = (ListenerUtil.mutListener.listen(30973) ? (bitmap.getWidth() % faceItem.getPreScale()) : (ListenerUtil.mutListener.listen(30972) ? (bitmap.getWidth() / faceItem.getPreScale()) : (ListenerUtil.mutListener.listen(30971) ? (bitmap.getWidth() - faceItem.getPreScale()) : (ListenerUtil.mutListener.listen(30970) ? (bitmap.getWidth() + faceItem.getPreScale()) : (bitmap.getWidth() * faceItem.getPreScale())))));
        float height = (ListenerUtil.mutListener.listen(30977) ? (bitmap.getHeight() % faceItem.getPreScale()) : (ListenerUtil.mutListener.listen(30976) ? (bitmap.getHeight() / faceItem.getPreScale()) : (ListenerUtil.mutListener.listen(30975) ? (bitmap.getHeight() - faceItem.getPreScale()) : (ListenerUtil.mutListener.listen(30974) ? (bitmap.getHeight() + faceItem.getPreScale()) : (bitmap.getHeight() * faceItem.getPreScale())))));
        if (!ListenerUtil.mutListener.listen(30978)) {
            // initial position of the entity
            srcPoints[0] = 0;
        }
        if (!ListenerUtil.mutListener.listen(30979)) {
            srcPoints[1] = 0;
        }
        if (!ListenerUtil.mutListener.listen(30980)) {
            srcPoints[2] = width;
        }
        if (!ListenerUtil.mutListener.listen(30981)) {
            srcPoints[3] = 0;
        }
        if (!ListenerUtil.mutListener.listen(30982)) {
            srcPoints[4] = width;
        }
        if (!ListenerUtil.mutListener.listen(30983)) {
            srcPoints[5] = height;
        }
        if (!ListenerUtil.mutListener.listen(30984)) {
            srcPoints[6] = 0;
        }
        if (!ListenerUtil.mutListener.listen(30985)) {
            srcPoints[7] = height;
        }
        if (!ListenerUtil.mutListener.listen(30986)) {
            srcPoints[8] = 0;
        }
        if (!ListenerUtil.mutListener.listen(30987)) {
            srcPoints[9] = 0;
        }
        float widthAspect = (ListenerUtil.mutListener.listen(30995) ? ((ListenerUtil.mutListener.listen(30991) ? (1.0F % canvasWidth) : (ListenerUtil.mutListener.listen(30990) ? (1.0F / canvasWidth) : (ListenerUtil.mutListener.listen(30989) ? (1.0F - canvasWidth) : (ListenerUtil.mutListener.listen(30988) ? (1.0F + canvasWidth) : (1.0F * canvasWidth))))) % width) : (ListenerUtil.mutListener.listen(30994) ? ((ListenerUtil.mutListener.listen(30991) ? (1.0F % canvasWidth) : (ListenerUtil.mutListener.listen(30990) ? (1.0F / canvasWidth) : (ListenerUtil.mutListener.listen(30989) ? (1.0F - canvasWidth) : (ListenerUtil.mutListener.listen(30988) ? (1.0F + canvasWidth) : (1.0F * canvasWidth))))) * width) : (ListenerUtil.mutListener.listen(30993) ? ((ListenerUtil.mutListener.listen(30991) ? (1.0F % canvasWidth) : (ListenerUtil.mutListener.listen(30990) ? (1.0F / canvasWidth) : (ListenerUtil.mutListener.listen(30989) ? (1.0F - canvasWidth) : (ListenerUtil.mutListener.listen(30988) ? (1.0F + canvasWidth) : (1.0F * canvasWidth))))) - width) : (ListenerUtil.mutListener.listen(30992) ? ((ListenerUtil.mutListener.listen(30991) ? (1.0F % canvasWidth) : (ListenerUtil.mutListener.listen(30990) ? (1.0F / canvasWidth) : (ListenerUtil.mutListener.listen(30989) ? (1.0F - canvasWidth) : (ListenerUtil.mutListener.listen(30988) ? (1.0F + canvasWidth) : (1.0F * canvasWidth))))) + width) : ((ListenerUtil.mutListener.listen(30991) ? (1.0F % canvasWidth) : (ListenerUtil.mutListener.listen(30990) ? (1.0F / canvasWidth) : (ListenerUtil.mutListener.listen(30989) ? (1.0F - canvasWidth) : (ListenerUtil.mutListener.listen(30988) ? (1.0F + canvasWidth) : (1.0F * canvasWidth))))) / width)))));
        float heightAspect = (ListenerUtil.mutListener.listen(31003) ? ((ListenerUtil.mutListener.listen(30999) ? (1.0F % canvasHeight) : (ListenerUtil.mutListener.listen(30998) ? (1.0F / canvasHeight) : (ListenerUtil.mutListener.listen(30997) ? (1.0F - canvasHeight) : (ListenerUtil.mutListener.listen(30996) ? (1.0F + canvasHeight) : (1.0F * canvasHeight))))) % height) : (ListenerUtil.mutListener.listen(31002) ? ((ListenerUtil.mutListener.listen(30999) ? (1.0F % canvasHeight) : (ListenerUtil.mutListener.listen(30998) ? (1.0F / canvasHeight) : (ListenerUtil.mutListener.listen(30997) ? (1.0F - canvasHeight) : (ListenerUtil.mutListener.listen(30996) ? (1.0F + canvasHeight) : (1.0F * canvasHeight))))) * height) : (ListenerUtil.mutListener.listen(31001) ? ((ListenerUtil.mutListener.listen(30999) ? (1.0F % canvasHeight) : (ListenerUtil.mutListener.listen(30998) ? (1.0F / canvasHeight) : (ListenerUtil.mutListener.listen(30997) ? (1.0F - canvasHeight) : (ListenerUtil.mutListener.listen(30996) ? (1.0F + canvasHeight) : (1.0F * canvasHeight))))) - height) : (ListenerUtil.mutListener.listen(31000) ? ((ListenerUtil.mutListener.listen(30999) ? (1.0F % canvasHeight) : (ListenerUtil.mutListener.listen(30998) ? (1.0F / canvasHeight) : (ListenerUtil.mutListener.listen(30997) ? (1.0F - canvasHeight) : (ListenerUtil.mutListener.listen(30996) ? (1.0F + canvasHeight) : (1.0F * canvasHeight))))) + height) : ((ListenerUtil.mutListener.listen(30999) ? (1.0F % canvasHeight) : (ListenerUtil.mutListener.listen(30998) ? (1.0F / canvasHeight) : (ListenerUtil.mutListener.listen(30997) ? (1.0F - canvasHeight) : (ListenerUtil.mutListener.listen(30996) ? (1.0F + canvasHeight) : (1.0F * canvasHeight))))) / height)))));
        if (!ListenerUtil.mutListener.listen(31004)) {
            // fit the smallest size
            holyScale = Math.min(widthAspect, heightAspect);
        }
        float canvasScaleX = (ListenerUtil.mutListener.listen(31008) ? ((float) canvasWidth % originalImageWidth) : (ListenerUtil.mutListener.listen(31007) ? ((float) canvasWidth * originalImageWidth) : (ListenerUtil.mutListener.listen(31006) ? ((float) canvasWidth - originalImageWidth) : (ListenerUtil.mutListener.listen(31005) ? ((float) canvasWidth + originalImageWidth) : ((float) canvasWidth / originalImageWidth)))));
        float canvasScaleY = (ListenerUtil.mutListener.listen(31012) ? ((float) canvasHeight % originalImageHeight) : (ListenerUtil.mutListener.listen(31011) ? ((float) canvasHeight * originalImageHeight) : (ListenerUtil.mutListener.listen(31010) ? ((float) canvasHeight - originalImageHeight) : (ListenerUtil.mutListener.listen(31009) ? ((float) canvasHeight + originalImageHeight) : ((float) canvasHeight / originalImageHeight)))));
        PointF midPoint = new PointF();
        if (!ListenerUtil.mutListener.listen(31013)) {
            faceItem.getFace().getMidPoint(midPoint);
        }
        if (!ListenerUtil.mutListener.listen(31018)) {
            midPoint.x = (ListenerUtil.mutListener.listen(31017) ? (midPoint.x % canvasScaleX) : (ListenerUtil.mutListener.listen(31016) ? (midPoint.x / canvasScaleX) : (ListenerUtil.mutListener.listen(31015) ? (midPoint.x - canvasScaleX) : (ListenerUtil.mutListener.listen(31014) ? (midPoint.x + canvasScaleX) : (midPoint.x * canvasScaleX)))));
        }
        if (!ListenerUtil.mutListener.listen(31023)) {
            midPoint.y = (ListenerUtil.mutListener.listen(31022) ? (midPoint.y % canvasScaleY) : (ListenerUtil.mutListener.listen(31021) ? (midPoint.y / canvasScaleY) : (ListenerUtil.mutListener.listen(31020) ? (midPoint.y - canvasScaleY) : (ListenerUtil.mutListener.listen(31019) ? (midPoint.y + canvasScaleY) : (midPoint.y * canvasScaleY)))));
        }
        float diameter = (ListenerUtil.mutListener.listen(31035) ? ((ListenerUtil.mutListener.listen(31027) ? (faceItem.getFace().eyesDistance() % canvasScaleX) : (ListenerUtil.mutListener.listen(31026) ? (faceItem.getFace().eyesDistance() / canvasScaleX) : (ListenerUtil.mutListener.listen(31025) ? (faceItem.getFace().eyesDistance() - canvasScaleX) : (ListenerUtil.mutListener.listen(31024) ? (faceItem.getFace().eyesDistance() + canvasScaleX) : (faceItem.getFace().eyesDistance() * canvasScaleX))))) % ((ListenerUtil.mutListener.listen(31031) ? (2f % BLUR_RADIUS) : (ListenerUtil.mutListener.listen(31030) ? (2f / BLUR_RADIUS) : (ListenerUtil.mutListener.listen(31029) ? (2f - BLUR_RADIUS) : (ListenerUtil.mutListener.listen(31028) ? (2f + BLUR_RADIUS) : (2f * BLUR_RADIUS))))))) : (ListenerUtil.mutListener.listen(31034) ? ((ListenerUtil.mutListener.listen(31027) ? (faceItem.getFace().eyesDistance() % canvasScaleX) : (ListenerUtil.mutListener.listen(31026) ? (faceItem.getFace().eyesDistance() / canvasScaleX) : (ListenerUtil.mutListener.listen(31025) ? (faceItem.getFace().eyesDistance() - canvasScaleX) : (ListenerUtil.mutListener.listen(31024) ? (faceItem.getFace().eyesDistance() + canvasScaleX) : (faceItem.getFace().eyesDistance() * canvasScaleX))))) / ((ListenerUtil.mutListener.listen(31031) ? (2f % BLUR_RADIUS) : (ListenerUtil.mutListener.listen(31030) ? (2f / BLUR_RADIUS) : (ListenerUtil.mutListener.listen(31029) ? (2f - BLUR_RADIUS) : (ListenerUtil.mutListener.listen(31028) ? (2f + BLUR_RADIUS) : (2f * BLUR_RADIUS))))))) : (ListenerUtil.mutListener.listen(31033) ? ((ListenerUtil.mutListener.listen(31027) ? (faceItem.getFace().eyesDistance() % canvasScaleX) : (ListenerUtil.mutListener.listen(31026) ? (faceItem.getFace().eyesDistance() / canvasScaleX) : (ListenerUtil.mutListener.listen(31025) ? (faceItem.getFace().eyesDistance() - canvasScaleX) : (ListenerUtil.mutListener.listen(31024) ? (faceItem.getFace().eyesDistance() + canvasScaleX) : (faceItem.getFace().eyesDistance() * canvasScaleX))))) - ((ListenerUtil.mutListener.listen(31031) ? (2f % BLUR_RADIUS) : (ListenerUtil.mutListener.listen(31030) ? (2f / BLUR_RADIUS) : (ListenerUtil.mutListener.listen(31029) ? (2f - BLUR_RADIUS) : (ListenerUtil.mutListener.listen(31028) ? (2f + BLUR_RADIUS) : (2f * BLUR_RADIUS))))))) : (ListenerUtil.mutListener.listen(31032) ? ((ListenerUtil.mutListener.listen(31027) ? (faceItem.getFace().eyesDistance() % canvasScaleX) : (ListenerUtil.mutListener.listen(31026) ? (faceItem.getFace().eyesDistance() / canvasScaleX) : (ListenerUtil.mutListener.listen(31025) ? (faceItem.getFace().eyesDistance() - canvasScaleX) : (ListenerUtil.mutListener.listen(31024) ? (faceItem.getFace().eyesDistance() + canvasScaleX) : (faceItem.getFace().eyesDistance() * canvasScaleX))))) + ((ListenerUtil.mutListener.listen(31031) ? (2f % BLUR_RADIUS) : (ListenerUtil.mutListener.listen(31030) ? (2f / BLUR_RADIUS) : (ListenerUtil.mutListener.listen(31029) ? (2f - BLUR_RADIUS) : (ListenerUtil.mutListener.listen(31028) ? (2f + BLUR_RADIUS) : (2f * BLUR_RADIUS))))))) : ((ListenerUtil.mutListener.listen(31027) ? (faceItem.getFace().eyesDistance() % canvasScaleX) : (ListenerUtil.mutListener.listen(31026) ? (faceItem.getFace().eyesDistance() / canvasScaleX) : (ListenerUtil.mutListener.listen(31025) ? (faceItem.getFace().eyesDistance() - canvasScaleX) : (ListenerUtil.mutListener.listen(31024) ? (faceItem.getFace().eyesDistance() + canvasScaleX) : (faceItem.getFace().eyesDistance() * canvasScaleX))))) * ((ListenerUtil.mutListener.listen(31031) ? (2f % BLUR_RADIUS) : (ListenerUtil.mutListener.listen(31030) ? (2f / BLUR_RADIUS) : (ListenerUtil.mutListener.listen(31029) ? (2f - BLUR_RADIUS) : (ListenerUtil.mutListener.listen(31028) ? (2f + BLUR_RADIUS) : (2f * BLUR_RADIUS)))))))))));
        if (!ListenerUtil.mutListener.listen(31036)) {
            moveCenterTo(midPoint);
        }
        if (!ListenerUtil.mutListener.listen(31046)) {
            getLayer().setScale((ListenerUtil.mutListener.listen(31045) ? (diameter % ((ListenerUtil.mutListener.listen(31041) ? (originalImageWidth >= originalImageHeight) : (ListenerUtil.mutListener.listen(31040) ? (originalImageWidth <= originalImageHeight) : (ListenerUtil.mutListener.listen(31039) ? (originalImageWidth < originalImageHeight) : (ListenerUtil.mutListener.listen(31038) ? (originalImageWidth != originalImageHeight) : (ListenerUtil.mutListener.listen(31037) ? (originalImageWidth == originalImageHeight) : (originalImageWidth > originalImageHeight)))))) ? canvasHeight : canvasWidth)) : (ListenerUtil.mutListener.listen(31044) ? (diameter * ((ListenerUtil.mutListener.listen(31041) ? (originalImageWidth >= originalImageHeight) : (ListenerUtil.mutListener.listen(31040) ? (originalImageWidth <= originalImageHeight) : (ListenerUtil.mutListener.listen(31039) ? (originalImageWidth < originalImageHeight) : (ListenerUtil.mutListener.listen(31038) ? (originalImageWidth != originalImageHeight) : (ListenerUtil.mutListener.listen(31037) ? (originalImageWidth == originalImageHeight) : (originalImageWidth > originalImageHeight)))))) ? canvasHeight : canvasWidth)) : (ListenerUtil.mutListener.listen(31043) ? (diameter - ((ListenerUtil.mutListener.listen(31041) ? (originalImageWidth >= originalImageHeight) : (ListenerUtil.mutListener.listen(31040) ? (originalImageWidth <= originalImageHeight) : (ListenerUtil.mutListener.listen(31039) ? (originalImageWidth < originalImageHeight) : (ListenerUtil.mutListener.listen(31038) ? (originalImageWidth != originalImageHeight) : (ListenerUtil.mutListener.listen(31037) ? (originalImageWidth == originalImageHeight) : (originalImageWidth > originalImageHeight)))))) ? canvasHeight : canvasWidth)) : (ListenerUtil.mutListener.listen(31042) ? (diameter + ((ListenerUtil.mutListener.listen(31041) ? (originalImageWidth >= originalImageHeight) : (ListenerUtil.mutListener.listen(31040) ? (originalImageWidth <= originalImageHeight) : (ListenerUtil.mutListener.listen(31039) ? (originalImageWidth < originalImageHeight) : (ListenerUtil.mutListener.listen(31038) ? (originalImageWidth != originalImageHeight) : (ListenerUtil.mutListener.listen(31037) ? (originalImageWidth == originalImageHeight) : (originalImageWidth > originalImageHeight)))))) ? canvasHeight : canvasWidth)) : (diameter / ((ListenerUtil.mutListener.listen(31041) ? (originalImageWidth >= originalImageHeight) : (ListenerUtil.mutListener.listen(31040) ? (originalImageWidth <= originalImageHeight) : (ListenerUtil.mutListener.listen(31039) ? (originalImageWidth < originalImageHeight) : (ListenerUtil.mutListener.listen(31038) ? (originalImageWidth != originalImageHeight) : (ListenerUtil.mutListener.listen(31037) ? (originalImageWidth == originalImageHeight) : (originalImageWidth > originalImageHeight)))))) ? canvasHeight : canvasWidth)))))));
        }
    }

    @Override
    public boolean hasFixedPositionAndSize() {
        return true;
    }

    @Override
    public int getWidth() {
        return bitmap.getWidth();
    }

    @Override
    public int getHeight() {
        return bitmap.getHeight();
    }
}
