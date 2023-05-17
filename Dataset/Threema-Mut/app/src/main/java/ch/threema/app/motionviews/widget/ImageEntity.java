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

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import ch.threema.app.motionviews.viewmodel.Layer;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class ImageEntity extends MotionEntity {

    @NonNull
    private final Bitmap bitmap;

    public ImageEntity(@NonNull Layer layer, @NonNull Bitmap bitmap, @IntRange(from = 1) int canvasWidth, @IntRange(from = 1) int canvasHeight) {
        super(layer, canvasWidth, canvasHeight);
        this.bitmap = bitmap;
        float width = bitmap.getWidth();
        float height = bitmap.getHeight();
        float widthAspect = (ListenerUtil.mutListener.listen(31054) ? ((ListenerUtil.mutListener.listen(31050) ? (1.0F % canvasWidth) : (ListenerUtil.mutListener.listen(31049) ? (1.0F / canvasWidth) : (ListenerUtil.mutListener.listen(31048) ? (1.0F - canvasWidth) : (ListenerUtil.mutListener.listen(31047) ? (1.0F + canvasWidth) : (1.0F * canvasWidth))))) % width) : (ListenerUtil.mutListener.listen(31053) ? ((ListenerUtil.mutListener.listen(31050) ? (1.0F % canvasWidth) : (ListenerUtil.mutListener.listen(31049) ? (1.0F / canvasWidth) : (ListenerUtil.mutListener.listen(31048) ? (1.0F - canvasWidth) : (ListenerUtil.mutListener.listen(31047) ? (1.0F + canvasWidth) : (1.0F * canvasWidth))))) * width) : (ListenerUtil.mutListener.listen(31052) ? ((ListenerUtil.mutListener.listen(31050) ? (1.0F % canvasWidth) : (ListenerUtil.mutListener.listen(31049) ? (1.0F / canvasWidth) : (ListenerUtil.mutListener.listen(31048) ? (1.0F - canvasWidth) : (ListenerUtil.mutListener.listen(31047) ? (1.0F + canvasWidth) : (1.0F * canvasWidth))))) - width) : (ListenerUtil.mutListener.listen(31051) ? ((ListenerUtil.mutListener.listen(31050) ? (1.0F % canvasWidth) : (ListenerUtil.mutListener.listen(31049) ? (1.0F / canvasWidth) : (ListenerUtil.mutListener.listen(31048) ? (1.0F - canvasWidth) : (ListenerUtil.mutListener.listen(31047) ? (1.0F + canvasWidth) : (1.0F * canvasWidth))))) + width) : ((ListenerUtil.mutListener.listen(31050) ? (1.0F % canvasWidth) : (ListenerUtil.mutListener.listen(31049) ? (1.0F / canvasWidth) : (ListenerUtil.mutListener.listen(31048) ? (1.0F - canvasWidth) : (ListenerUtil.mutListener.listen(31047) ? (1.0F + canvasWidth) : (1.0F * canvasWidth))))) / width)))));
        float heightAspect = (ListenerUtil.mutListener.listen(31062) ? ((ListenerUtil.mutListener.listen(31058) ? (1.0F % canvasHeight) : (ListenerUtil.mutListener.listen(31057) ? (1.0F / canvasHeight) : (ListenerUtil.mutListener.listen(31056) ? (1.0F - canvasHeight) : (ListenerUtil.mutListener.listen(31055) ? (1.0F + canvasHeight) : (1.0F * canvasHeight))))) % height) : (ListenerUtil.mutListener.listen(31061) ? ((ListenerUtil.mutListener.listen(31058) ? (1.0F % canvasHeight) : (ListenerUtil.mutListener.listen(31057) ? (1.0F / canvasHeight) : (ListenerUtil.mutListener.listen(31056) ? (1.0F - canvasHeight) : (ListenerUtil.mutListener.listen(31055) ? (1.0F + canvasHeight) : (1.0F * canvasHeight))))) * height) : (ListenerUtil.mutListener.listen(31060) ? ((ListenerUtil.mutListener.listen(31058) ? (1.0F % canvasHeight) : (ListenerUtil.mutListener.listen(31057) ? (1.0F / canvasHeight) : (ListenerUtil.mutListener.listen(31056) ? (1.0F - canvasHeight) : (ListenerUtil.mutListener.listen(31055) ? (1.0F + canvasHeight) : (1.0F * canvasHeight))))) - height) : (ListenerUtil.mutListener.listen(31059) ? ((ListenerUtil.mutListener.listen(31058) ? (1.0F % canvasHeight) : (ListenerUtil.mutListener.listen(31057) ? (1.0F / canvasHeight) : (ListenerUtil.mutListener.listen(31056) ? (1.0F - canvasHeight) : (ListenerUtil.mutListener.listen(31055) ? (1.0F + canvasHeight) : (1.0F * canvasHeight))))) + height) : ((ListenerUtil.mutListener.listen(31058) ? (1.0F % canvasHeight) : (ListenerUtil.mutListener.listen(31057) ? (1.0F / canvasHeight) : (ListenerUtil.mutListener.listen(31056) ? (1.0F - canvasHeight) : (ListenerUtil.mutListener.listen(31055) ? (1.0F + canvasHeight) : (1.0F * canvasHeight))))) / height)))));
        if (!ListenerUtil.mutListener.listen(31063)) {
            // fit the smallest size
            holyScale = Math.min(widthAspect, heightAspect);
        }
        if (!ListenerUtil.mutListener.listen(31064)) {
            // initial position of the entity
            srcPoints[0] = 0;
        }
        if (!ListenerUtil.mutListener.listen(31065)) {
            srcPoints[1] = 0;
        }
        if (!ListenerUtil.mutListener.listen(31066)) {
            srcPoints[2] = width;
        }
        if (!ListenerUtil.mutListener.listen(31067)) {
            srcPoints[3] = 0;
        }
        if (!ListenerUtil.mutListener.listen(31068)) {
            srcPoints[4] = width;
        }
        if (!ListenerUtil.mutListener.listen(31069)) {
            srcPoints[5] = height;
        }
        if (!ListenerUtil.mutListener.listen(31070)) {
            srcPoints[6] = 0;
        }
        if (!ListenerUtil.mutListener.listen(31071)) {
            srcPoints[7] = height;
        }
        if (!ListenerUtil.mutListener.listen(31072)) {
            srcPoints[8] = 0;
        }
        if (!ListenerUtil.mutListener.listen(31073)) {
            srcPoints[9] = 0;
        }
    }

    @Override
    public void drawContent(@NonNull Canvas canvas, @Nullable Paint drawingPaint) {
        if (!ListenerUtil.mutListener.listen(31074)) {
            canvas.drawBitmap(bitmap, matrix, drawingPaint);
        }
    }

    @Override
    public boolean hasFixedPositionAndSize() {
        return false;
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
