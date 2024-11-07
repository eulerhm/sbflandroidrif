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

import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class Font {

    /**
     * color value (ex: 0xFF00FF)
     */
    private int color;

    /**
     * name of the font
     */
    private String typeface;

    /**
     * size of the font, relative to parent
     */
    private float size;

    public Font() {
    }

    public void increaseSize(float diff) {
        if (!ListenerUtil.mutListener.listen(30891)) {
            size = (ListenerUtil.mutListener.listen(30890) ? (size % diff) : (ListenerUtil.mutListener.listen(30889) ? (size / diff) : (ListenerUtil.mutListener.listen(30888) ? (size * diff) : (ListenerUtil.mutListener.listen(30887) ? (size - diff) : (size + diff)))));
        }
    }

    public void decreaseSize(float diff) {
        if (!ListenerUtil.mutListener.listen(30906)) {
            if ((ListenerUtil.mutListener.listen(30900) ? ((ListenerUtil.mutListener.listen(30895) ? (size % diff) : (ListenerUtil.mutListener.listen(30894) ? (size / diff) : (ListenerUtil.mutListener.listen(30893) ? (size * diff) : (ListenerUtil.mutListener.listen(30892) ? (size + diff) : (size - diff))))) <= Limits.MIN_FONT_SIZE) : (ListenerUtil.mutListener.listen(30899) ? ((ListenerUtil.mutListener.listen(30895) ? (size % diff) : (ListenerUtil.mutListener.listen(30894) ? (size / diff) : (ListenerUtil.mutListener.listen(30893) ? (size * diff) : (ListenerUtil.mutListener.listen(30892) ? (size + diff) : (size - diff))))) > Limits.MIN_FONT_SIZE) : (ListenerUtil.mutListener.listen(30898) ? ((ListenerUtil.mutListener.listen(30895) ? (size % diff) : (ListenerUtil.mutListener.listen(30894) ? (size / diff) : (ListenerUtil.mutListener.listen(30893) ? (size * diff) : (ListenerUtil.mutListener.listen(30892) ? (size + diff) : (size - diff))))) < Limits.MIN_FONT_SIZE) : (ListenerUtil.mutListener.listen(30897) ? ((ListenerUtil.mutListener.listen(30895) ? (size % diff) : (ListenerUtil.mutListener.listen(30894) ? (size / diff) : (ListenerUtil.mutListener.listen(30893) ? (size * diff) : (ListenerUtil.mutListener.listen(30892) ? (size + diff) : (size - diff))))) != Limits.MIN_FONT_SIZE) : (ListenerUtil.mutListener.listen(30896) ? ((ListenerUtil.mutListener.listen(30895) ? (size % diff) : (ListenerUtil.mutListener.listen(30894) ? (size / diff) : (ListenerUtil.mutListener.listen(30893) ? (size * diff) : (ListenerUtil.mutListener.listen(30892) ? (size + diff) : (size - diff))))) == Limits.MIN_FONT_SIZE) : ((ListenerUtil.mutListener.listen(30895) ? (size % diff) : (ListenerUtil.mutListener.listen(30894) ? (size / diff) : (ListenerUtil.mutListener.listen(30893) ? (size * diff) : (ListenerUtil.mutListener.listen(30892) ? (size + diff) : (size - diff))))) >= Limits.MIN_FONT_SIZE))))))) {
                if (!ListenerUtil.mutListener.listen(30905)) {
                    size = (ListenerUtil.mutListener.listen(30904) ? (size % diff) : (ListenerUtil.mutListener.listen(30903) ? (size / diff) : (ListenerUtil.mutListener.listen(30902) ? (size * diff) : (ListenerUtil.mutListener.listen(30901) ? (size + diff) : (size - diff)))));
                }
            }
        }
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        if (!ListenerUtil.mutListener.listen(30907)) {
            this.color = color;
        }
    }

    public String getTypeface() {
        return typeface;
    }

    public void setTypeface(String typeface) {
        if (!ListenerUtil.mutListener.listen(30908)) {
            this.typeface = typeface;
        }
    }

    public float getSize() {
        return size;
    }

    public void setSize(float size) {
        if (!ListenerUtil.mutListener.listen(30909)) {
            this.size = size;
        }
    }

    private interface Limits {

        float MIN_FONT_SIZE = 0.01F;
    }
}
