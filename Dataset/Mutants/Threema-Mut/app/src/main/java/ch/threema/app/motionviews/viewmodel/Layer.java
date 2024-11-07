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
package ch.threema.app.motionviews.viewmodel;

import androidx.annotation.FloatRange;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class Layer {

    /**
     *  rotation relative to the layer center, in degrees
     */
    @FloatRange(from = 0.0F, to = 360.0F)
    private float rotationInDegrees;

    private float scale;

    /**
     *  top left X coordinate, relative to parent canvas
     */
    private float x;

    /**
     *  top left Y coordinate, relative to parent canvas
     */
    private float y;

    /**
     *  is layer flipped horizontally (by X-coordinate)
     */
    private boolean isFlipped;

    public Layer() {
        if (!ListenerUtil.mutListener.listen(30910)) {
            reset();
        }
    }

    protected void reset() {
        if (!ListenerUtil.mutListener.listen(30911)) {
            this.rotationInDegrees = 0.0F;
        }
        if (!ListenerUtil.mutListener.listen(30912)) {
            this.scale = 1.0F;
        }
        if (!ListenerUtil.mutListener.listen(30913)) {
            this.isFlipped = false;
        }
        if (!ListenerUtil.mutListener.listen(30914)) {
            this.x = 0.0F;
        }
        if (!ListenerUtil.mutListener.listen(30915)) {
            this.y = 0.0F;
        }
    }

    public void postScale(float scaleDiff) {
        float newVal = (ListenerUtil.mutListener.listen(30919) ? (scale % scaleDiff) : (ListenerUtil.mutListener.listen(30918) ? (scale / scaleDiff) : (ListenerUtil.mutListener.listen(30917) ? (scale * scaleDiff) : (ListenerUtil.mutListener.listen(30916) ? (scale - scaleDiff) : (scale + scaleDiff)))));
        if (!ListenerUtil.mutListener.listen(30932)) {
            if ((ListenerUtil.mutListener.listen(30930) ? ((ListenerUtil.mutListener.listen(30924) ? (newVal <= getMinScale()) : (ListenerUtil.mutListener.listen(30923) ? (newVal > getMinScale()) : (ListenerUtil.mutListener.listen(30922) ? (newVal < getMinScale()) : (ListenerUtil.mutListener.listen(30921) ? (newVal != getMinScale()) : (ListenerUtil.mutListener.listen(30920) ? (newVal == getMinScale()) : (newVal >= getMinScale())))))) || (ListenerUtil.mutListener.listen(30929) ? (newVal >= getMaxScale()) : (ListenerUtil.mutListener.listen(30928) ? (newVal > getMaxScale()) : (ListenerUtil.mutListener.listen(30927) ? (newVal < getMaxScale()) : (ListenerUtil.mutListener.listen(30926) ? (newVal != getMaxScale()) : (ListenerUtil.mutListener.listen(30925) ? (newVal == getMaxScale()) : (newVal <= getMaxScale()))))))) : ((ListenerUtil.mutListener.listen(30924) ? (newVal <= getMinScale()) : (ListenerUtil.mutListener.listen(30923) ? (newVal > getMinScale()) : (ListenerUtil.mutListener.listen(30922) ? (newVal < getMinScale()) : (ListenerUtil.mutListener.listen(30921) ? (newVal != getMinScale()) : (ListenerUtil.mutListener.listen(30920) ? (newVal == getMinScale()) : (newVal >= getMinScale())))))) && (ListenerUtil.mutListener.listen(30929) ? (newVal >= getMaxScale()) : (ListenerUtil.mutListener.listen(30928) ? (newVal > getMaxScale()) : (ListenerUtil.mutListener.listen(30927) ? (newVal < getMaxScale()) : (ListenerUtil.mutListener.listen(30926) ? (newVal != getMaxScale()) : (ListenerUtil.mutListener.listen(30925) ? (newVal == getMaxScale()) : (newVal <= getMaxScale()))))))))) {
                if (!ListenerUtil.mutListener.listen(30931)) {
                    scale = newVal;
                }
            }
        }
    }

    protected float getMaxScale() {
        return Limits.MAX_SCALE;
    }

    protected float getMinScale() {
        return Limits.MIN_SCALE;
    }

    public void postRotate(float rotationInDegreesDiff) {
        if (!ListenerUtil.mutListener.listen(30933)) {
            this.rotationInDegrees += rotationInDegreesDiff;
        }
        if (!ListenerUtil.mutListener.listen(30934)) {
            this.rotationInDegrees %= 360.0F;
        }
    }

    public void postTranslate(float dx, float dy) {
        if (!ListenerUtil.mutListener.listen(30935)) {
            this.x += dx;
        }
        if (!ListenerUtil.mutListener.listen(30936)) {
            this.y += dy;
        }
    }

    public void flip() {
        if (!ListenerUtil.mutListener.listen(30937)) {
            this.isFlipped = !isFlipped;
        }
    }

    public float initialScale() {
        return Limits.INITIAL_ENTITY_SCALE;
    }

    public float getRotationInDegrees() {
        return rotationInDegrees;
    }

    public void setRotationInDegrees(@FloatRange(from = 0.0, to = 360.0) float rotationInDegrees) {
        if (!ListenerUtil.mutListener.listen(30938)) {
            this.rotationInDegrees = rotationInDegrees;
        }
    }

    public float getScale() {
        return scale;
    }

    public void setScale(float scale) {
        if (!ListenerUtil.mutListener.listen(30939)) {
            this.scale = scale;
        }
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        if (!ListenerUtil.mutListener.listen(30940)) {
            this.x = x;
        }
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        if (!ListenerUtil.mutListener.listen(30941)) {
            this.y = y;
        }
    }

    public boolean isFlipped() {
        return isFlipped;
    }

    public void setFlipped(boolean flipped) {
        if (!ListenerUtil.mutListener.listen(30942)) {
            isFlipped = flipped;
        }
    }

    interface Limits {

        float MIN_SCALE = 0.06F;

        float MAX_SCALE = 4.0F;

        float INITIAL_ENTITY_SCALE = 0.3F;
    }
}
