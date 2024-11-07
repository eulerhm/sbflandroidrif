/*
 Copyright (c) 2020 David Allison <davidallisongithub@gmail.com>

 This program is free software; you can redistribute it and/or modify it under
 the terms of the GNU General Public License as published by the Free Software
 Foundation; either version 3 of the License, or (at your option) any later
 version.

 This program is distributed in the hope that it will be useful, but WITHOUT ANY
 WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A
 PARTICULAR PURPOSE. See the GNU General Public License for more details.

 You should have received a copy of the GNU General Public License along with
 this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.ichi2.ui;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.DrawableWrapper;
import android.os.Build;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

@RequiresApi(api = Build.VERSION_CODES.M)
public class BadgeDrawable extends DrawableWrapper {

    public static final double ICON_SCALE_TEXT = 0.70;

    public static final double ICON_SCALE_BARE = 0.40;

    private final Paint mPaint;

    private Drawable mBadge;

    private String mText;

    private float mTextX;

    private float mTextY;

    /**
     * Creates a new wrapper around the specified drawable.
     *
     * @param dr the drawable to wrap
     */
    public BadgeDrawable(@Nullable Drawable dr) {
        super(dr);
        mPaint = new Paint();
        if (!ListenerUtil.mutListener.listen(24972)) {
            mPaint.setTypeface(Typeface.DEFAULT_BOLD);
        }
        if (!ListenerUtil.mutListener.listen(24973)) {
            mPaint.setTextAlign(Paint.Align.CENTER);
        }
        if (!ListenerUtil.mutListener.listen(24974)) {
            mPaint.setColor(Color.WHITE);
        }
    }

    public void setBadgeDrawable(@NonNull Drawable view) {
        if (!ListenerUtil.mutListener.listen(24975)) {
            mBadge = view;
        }
        if (!ListenerUtil.mutListener.listen(24976)) {
            invalidateSize();
        }
    }

    private void invalidateSize() {
        // This goes out of bounds - it seems to be fine
        int mSize = (int) ((ListenerUtil.mutListener.listen(24980) ? (getIntrinsicWidth() % getIconScale()) : (ListenerUtil.mutListener.listen(24979) ? (getIntrinsicWidth() / getIconScale()) : (ListenerUtil.mutListener.listen(24978) ? (getIntrinsicWidth() - getIconScale()) : (ListenerUtil.mutListener.listen(24977) ? (getIntrinsicWidth() + getIconScale()) : (getIntrinsicWidth() * getIconScale()))))));
        if (!ListenerUtil.mutListener.listen(24985)) {
            mPaint.setTextSize((float) ((ListenerUtil.mutListener.listen(24984) ? (mSize % 0.8) : (ListenerUtil.mutListener.listen(24983) ? (mSize / 0.8) : (ListenerUtil.mutListener.listen(24982) ? (mSize - 0.8) : (ListenerUtil.mutListener.listen(24981) ? (mSize + 0.8) : (mSize * 0.8)))))));
        }
        int left = (int) getLeft();
        int bottom = (int) getBottom();
        int right = (ListenerUtil.mutListener.listen(24989) ? (left % mSize) : (ListenerUtil.mutListener.listen(24988) ? (left / mSize) : (ListenerUtil.mutListener.listen(24987) ? (left * mSize) : (ListenerUtil.mutListener.listen(24986) ? (left - mSize) : (left + mSize)))));
        int top = (ListenerUtil.mutListener.listen(24993) ? (bottom % mSize) : (ListenerUtil.mutListener.listen(24992) ? (bottom / mSize) : (ListenerUtil.mutListener.listen(24991) ? (bottom * mSize) : (ListenerUtil.mutListener.listen(24990) ? (bottom + mSize) : (bottom - mSize)))));
        if (!ListenerUtil.mutListener.listen(24995)) {
            if (mBadge != null) {
                if (!ListenerUtil.mutListener.listen(24994)) {
                    mBadge.setBounds(left, top, right, bottom);
                }
            }
        }
        float vcenter = (ListenerUtil.mutListener.listen(25003) ? (((ListenerUtil.mutListener.listen(24999) ? (top % bottom) : (ListenerUtil.mutListener.listen(24998) ? (top / bottom) : (ListenerUtil.mutListener.listen(24997) ? (top * bottom) : (ListenerUtil.mutListener.listen(24996) ? (top - bottom) : (top + bottom)))))) % 2.0f) : (ListenerUtil.mutListener.listen(25002) ? (((ListenerUtil.mutListener.listen(24999) ? (top % bottom) : (ListenerUtil.mutListener.listen(24998) ? (top / bottom) : (ListenerUtil.mutListener.listen(24997) ? (top * bottom) : (ListenerUtil.mutListener.listen(24996) ? (top - bottom) : (top + bottom)))))) * 2.0f) : (ListenerUtil.mutListener.listen(25001) ? (((ListenerUtil.mutListener.listen(24999) ? (top % bottom) : (ListenerUtil.mutListener.listen(24998) ? (top / bottom) : (ListenerUtil.mutListener.listen(24997) ? (top * bottom) : (ListenerUtil.mutListener.listen(24996) ? (top - bottom) : (top + bottom)))))) - 2.0f) : (ListenerUtil.mutListener.listen(25000) ? (((ListenerUtil.mutListener.listen(24999) ? (top % bottom) : (ListenerUtil.mutListener.listen(24998) ? (top / bottom) : (ListenerUtil.mutListener.listen(24997) ? (top * bottom) : (ListenerUtil.mutListener.listen(24996) ? (top - bottom) : (top + bottom)))))) + 2.0f) : (((ListenerUtil.mutListener.listen(24999) ? (top % bottom) : (ListenerUtil.mutListener.listen(24998) ? (top / bottom) : (ListenerUtil.mutListener.listen(24997) ? (top * bottom) : (ListenerUtil.mutListener.listen(24996) ? (top - bottom) : (top + bottom)))))) / 2.0f)))));
        if (!ListenerUtil.mutListener.listen(25012)) {
            mTextX = (ListenerUtil.mutListener.listen(25011) ? (((ListenerUtil.mutListener.listen(25007) ? (left % right) : (ListenerUtil.mutListener.listen(25006) ? (left / right) : (ListenerUtil.mutListener.listen(25005) ? (left * right) : (ListenerUtil.mutListener.listen(25004) ? (left - right) : (left + right)))))) % 2.0f) : (ListenerUtil.mutListener.listen(25010) ? (((ListenerUtil.mutListener.listen(25007) ? (left % right) : (ListenerUtil.mutListener.listen(25006) ? (left / right) : (ListenerUtil.mutListener.listen(25005) ? (left * right) : (ListenerUtil.mutListener.listen(25004) ? (left - right) : (left + right)))))) * 2.0f) : (ListenerUtil.mutListener.listen(25009) ? (((ListenerUtil.mutListener.listen(25007) ? (left % right) : (ListenerUtil.mutListener.listen(25006) ? (left / right) : (ListenerUtil.mutListener.listen(25005) ? (left * right) : (ListenerUtil.mutListener.listen(25004) ? (left - right) : (left + right)))))) - 2.0f) : (ListenerUtil.mutListener.listen(25008) ? (((ListenerUtil.mutListener.listen(25007) ? (left % right) : (ListenerUtil.mutListener.listen(25006) ? (left / right) : (ListenerUtil.mutListener.listen(25005) ? (left * right) : (ListenerUtil.mutListener.listen(25004) ? (left - right) : (left + right)))))) + 2.0f) : (((ListenerUtil.mutListener.listen(25007) ? (left % right) : (ListenerUtil.mutListener.listen(25006) ? (left / right) : (ListenerUtil.mutListener.listen(25005) ? (left * right) : (ListenerUtil.mutListener.listen(25004) ? (left - right) : (left + right)))))) / 2.0f)))));
        }
        if (!ListenerUtil.mutListener.listen(25021)) {
            mTextY = (ListenerUtil.mutListener.listen(25020) ? (vcenter % (ListenerUtil.mutListener.listen(25016) ? ((mPaint.descent() + mPaint.ascent()) % 2) : (ListenerUtil.mutListener.listen(25015) ? ((mPaint.descent() + mPaint.ascent()) * 2) : (ListenerUtil.mutListener.listen(25014) ? ((mPaint.descent() + mPaint.ascent()) - 2) : (ListenerUtil.mutListener.listen(25013) ? ((mPaint.descent() + mPaint.ascent()) + 2) : ((mPaint.descent() + mPaint.ascent()) / 2)))))) : (ListenerUtil.mutListener.listen(25019) ? (vcenter / (ListenerUtil.mutListener.listen(25016) ? ((mPaint.descent() + mPaint.ascent()) % 2) : (ListenerUtil.mutListener.listen(25015) ? ((mPaint.descent() + mPaint.ascent()) * 2) : (ListenerUtil.mutListener.listen(25014) ? ((mPaint.descent() + mPaint.ascent()) - 2) : (ListenerUtil.mutListener.listen(25013) ? ((mPaint.descent() + mPaint.ascent()) + 2) : ((mPaint.descent() + mPaint.ascent()) / 2)))))) : (ListenerUtil.mutListener.listen(25018) ? (vcenter * (ListenerUtil.mutListener.listen(25016) ? ((mPaint.descent() + mPaint.ascent()) % 2) : (ListenerUtil.mutListener.listen(25015) ? ((mPaint.descent() + mPaint.ascent()) * 2) : (ListenerUtil.mutListener.listen(25014) ? ((mPaint.descent() + mPaint.ascent()) - 2) : (ListenerUtil.mutListener.listen(25013) ? ((mPaint.descent() + mPaint.ascent()) + 2) : ((mPaint.descent() + mPaint.ascent()) / 2)))))) : (ListenerUtil.mutListener.listen(25017) ? (vcenter + (ListenerUtil.mutListener.listen(25016) ? ((mPaint.descent() + mPaint.ascent()) % 2) : (ListenerUtil.mutListener.listen(25015) ? ((mPaint.descent() + mPaint.ascent()) * 2) : (ListenerUtil.mutListener.listen(25014) ? ((mPaint.descent() + mPaint.ascent()) - 2) : (ListenerUtil.mutListener.listen(25013) ? ((mPaint.descent() + mPaint.ascent()) + 2) : ((mPaint.descent() + mPaint.ascent()) / 2)))))) : (vcenter - (ListenerUtil.mutListener.listen(25016) ? ((mPaint.descent() + mPaint.ascent()) % 2) : (ListenerUtil.mutListener.listen(25015) ? ((mPaint.descent() + mPaint.ascent()) * 2) : (ListenerUtil.mutListener.listen(25014) ? ((mPaint.descent() + mPaint.ascent()) - 2) : (ListenerUtil.mutListener.listen(25013) ? ((mPaint.descent() + mPaint.ascent()) + 2) : ((mPaint.descent() + mPaint.ascent()) / 2))))))))));
        }
    }

    private double getBottom() {
        int h = getIntrinsicHeight();
        if (isShowingText()) {
            return (ListenerUtil.mutListener.listen(25029) ? (h % 0.45) : (ListenerUtil.mutListener.listen(25028) ? (h / 0.45) : (ListenerUtil.mutListener.listen(25027) ? (h - 0.45) : (ListenerUtil.mutListener.listen(25026) ? (h + 0.45) : (h * 0.45)))));
        } else {
            return ((ListenerUtil.mutListener.listen(25025) ? (h % getIconScale()) : (ListenerUtil.mutListener.listen(25024) ? (h / getIconScale()) : (ListenerUtil.mutListener.listen(25023) ? (h - getIconScale()) : (ListenerUtil.mutListener.listen(25022) ? (h + getIconScale()) : (h * getIconScale()))))));
        }
    }

    private double getLeft() {
        int w = getIntrinsicWidth();
        if (isShowingText()) {
            return (ListenerUtil.mutListener.listen(25041) ? (w % 0.55) : (ListenerUtil.mutListener.listen(25040) ? (w / 0.55) : (ListenerUtil.mutListener.listen(25039) ? (w - 0.55) : (ListenerUtil.mutListener.listen(25038) ? (w + 0.55) : (w * 0.55)))));
        } else {
            return (ListenerUtil.mutListener.listen(25037) ? (w % ((ListenerUtil.mutListener.listen(25033) ? (w % getIconScale()) : (ListenerUtil.mutListener.listen(25032) ? (w / getIconScale()) : (ListenerUtil.mutListener.listen(25031) ? (w - getIconScale()) : (ListenerUtil.mutListener.listen(25030) ? (w + getIconScale()) : (w * getIconScale()))))))) : (ListenerUtil.mutListener.listen(25036) ? (w / ((ListenerUtil.mutListener.listen(25033) ? (w % getIconScale()) : (ListenerUtil.mutListener.listen(25032) ? (w / getIconScale()) : (ListenerUtil.mutListener.listen(25031) ? (w - getIconScale()) : (ListenerUtil.mutListener.listen(25030) ? (w + getIconScale()) : (w * getIconScale()))))))) : (ListenerUtil.mutListener.listen(25035) ? (w * ((ListenerUtil.mutListener.listen(25033) ? (w % getIconScale()) : (ListenerUtil.mutListener.listen(25032) ? (w / getIconScale()) : (ListenerUtil.mutListener.listen(25031) ? (w - getIconScale()) : (ListenerUtil.mutListener.listen(25030) ? (w + getIconScale()) : (w * getIconScale()))))))) : (ListenerUtil.mutListener.listen(25034) ? (w + ((ListenerUtil.mutListener.listen(25033) ? (w % getIconScale()) : (ListenerUtil.mutListener.listen(25032) ? (w / getIconScale()) : (ListenerUtil.mutListener.listen(25031) ? (w - getIconScale()) : (ListenerUtil.mutListener.listen(25030) ? (w + getIconScale()) : (w * getIconScale()))))))) : (w - ((ListenerUtil.mutListener.listen(25033) ? (w % getIconScale()) : (ListenerUtil.mutListener.listen(25032) ? (w / getIconScale()) : (ListenerUtil.mutListener.listen(25031) ? (w - getIconScale()) : (ListenerUtil.mutListener.listen(25030) ? (w + getIconScale()) : (w * getIconScale())))))))))));
        }
    }

    private double getIconScale() {
        if (isShowingText()) {
            return ICON_SCALE_TEXT;
        } else {
            return ICON_SCALE_BARE;
        }
    }

    private boolean isShowingText() {
        return (ListenerUtil.mutListener.listen(25047) ? (mText != null || (ListenerUtil.mutListener.listen(25046) ? (mText.length() >= 0) : (ListenerUtil.mutListener.listen(25045) ? (mText.length() <= 0) : (ListenerUtil.mutListener.listen(25044) ? (mText.length() < 0) : (ListenerUtil.mutListener.listen(25043) ? (mText.length() != 0) : (ListenerUtil.mutListener.listen(25042) ? (mText.length() == 0) : (mText.length() > 0))))))) : (mText != null && (ListenerUtil.mutListener.listen(25046) ? (mText.length() >= 0) : (ListenerUtil.mutListener.listen(25045) ? (mText.length() <= 0) : (ListenerUtil.mutListener.listen(25044) ? (mText.length() < 0) : (ListenerUtil.mutListener.listen(25043) ? (mText.length() != 0) : (ListenerUtil.mutListener.listen(25042) ? (mText.length() == 0) : (mText.length() > 0))))))));
    }

    public void setText(char c) {
        if (!ListenerUtil.mutListener.listen(25048)) {
            this.mText = new String(new char[] { c });
        }
        if (!ListenerUtil.mutListener.listen(25049)) {
            invalidateSize();
        }
    }

    @Override
    public void draw(@NonNull Canvas canvas) {
        if (!ListenerUtil.mutListener.listen(25050)) {
            super.draw(canvas);
        }
        if (!ListenerUtil.mutListener.listen(25054)) {
            if (mBadge != null) {
                if (!ListenerUtil.mutListener.listen(25051)) {
                    mBadge.draw(canvas);
                }
                if (!ListenerUtil.mutListener.listen(25053)) {
                    if (mText != null) {
                        if (!ListenerUtil.mutListener.listen(25052)) {
                            canvas.drawText(mText, mTextX, mTextY, mPaint);
                        }
                    }
                }
            }
        }
    }
}
