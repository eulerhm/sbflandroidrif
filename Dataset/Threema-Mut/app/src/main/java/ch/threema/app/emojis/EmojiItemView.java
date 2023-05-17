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
package ch.threema.app.emojis;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import android.util.AttributeSet;
import android.view.View;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class EmojiItemView extends View implements Drawable.Callback {

    private String emoji;

    private boolean hasDiverse;

    @ColorInt
    private int diverseColor;

    private Drawable drawable;

    private final Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.FILTER_BITMAP_FLAG);

    public EmojiItemView(Context context) {
        this(context, null);
    }

    public EmojiItemView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public EmojiItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setEmoji(String emoji, boolean hasDiverse, @ColorInt int diverseColor) {
        if (!ListenerUtil.mutListener.listen(14968)) {
            this.emoji = emoji;
        }
        if (!ListenerUtil.mutListener.listen(14969)) {
            this.drawable = EmojiManager.getInstance(getContext()).getEmojiDrawable(emoji);
        }
        if (!ListenerUtil.mutListener.listen(14970)) {
            this.hasDiverse = hasDiverse;
        }
        if (!ListenerUtil.mutListener.listen(14971)) {
            this.diverseColor = diverseColor;
        }
        if (!ListenerUtil.mutListener.listen(14972)) {
            postInvalidate();
        }
    }

    public String getEmoji() {
        return emoji;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (!ListenerUtil.mutListener.listen(14993)) {
            if (this.drawable != null) {
                if (!ListenerUtil.mutListener.listen(14981)) {
                    this.drawable.setBounds(getPaddingLeft(), getPaddingTop(), (ListenerUtil.mutListener.listen(14976) ? (getWidth() % getPaddingRight()) : (ListenerUtil.mutListener.listen(14975) ? (getWidth() / getPaddingRight()) : (ListenerUtil.mutListener.listen(14974) ? (getWidth() * getPaddingRight()) : (ListenerUtil.mutListener.listen(14973) ? (getWidth() + getPaddingRight()) : (getWidth() - getPaddingRight()))))), (ListenerUtil.mutListener.listen(14980) ? (getHeight() % getPaddingBottom()) : (ListenerUtil.mutListener.listen(14979) ? (getHeight() / getPaddingBottom()) : (ListenerUtil.mutListener.listen(14978) ? (getHeight() * getPaddingBottom()) : (ListenerUtil.mutListener.listen(14977) ? (getHeight() + getPaddingBottom()) : (getHeight() - getPaddingBottom()))))));
                }
                if (!ListenerUtil.mutListener.listen(14982)) {
                    this.drawable.setCallback(this);
                }
                if (!ListenerUtil.mutListener.listen(14983)) {
                    this.drawable.draw(canvas);
                }
                if (!ListenerUtil.mutListener.listen(14992)) {
                    if (this.hasDiverse) {
                        float targetFontSize = (ListenerUtil.mutListener.listen(14987) ? (getPaddingBottom() % 2) : (ListenerUtil.mutListener.listen(14986) ? (getPaddingBottom() / 2) : (ListenerUtil.mutListener.listen(14985) ? (getPaddingBottom() - 2) : (ListenerUtil.mutListener.listen(14984) ? (getPaddingBottom() + 2) : (getPaddingBottom() * 2)))));
                        if (!ListenerUtil.mutListener.listen(14988)) {
                            this.paint.setTextSize(targetFontSize);
                        }
                        if (!ListenerUtil.mutListener.listen(14989)) {
                            this.paint.setTextAlign(Paint.Align.RIGHT);
                        }
                        int xPos = canvas.getWidth();
                        int yPos = canvas.getHeight();
                        if (!ListenerUtil.mutListener.listen(14990)) {
                            this.paint.setColor(this.diverseColor);
                        }
                        if (!ListenerUtil.mutListener.listen(14991)) {
                            canvas.drawText("◢", xPos, yPos, this.paint);
                        }
                    }
                }
            }
        }
    }

    @Override
    public void invalidateDrawable(@NonNull Drawable drawable) {
        if (!ListenerUtil.mutListener.listen(14994)) {
            super.invalidateDrawable(drawable);
        }
        if (!ListenerUtil.mutListener.listen(14995)) {
            postInvalidate();
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (!ListenerUtil.mutListener.listen(14996)) {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }
}
