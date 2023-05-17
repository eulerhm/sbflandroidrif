/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
 * Copyright (c) 2014-2021 Threema GmbH
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
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import ch.threema.app.R;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class CountBoxView extends androidx.appcompat.widget.AppCompatTextView {

    public CountBoxView(Context context) {
        super(context);
        if (!ListenerUtil.mutListener.listen(44927)) {
            init(context, null);
        }
    }

    public CountBoxView(Context context, AttributeSet attrs) {
        super(context, attrs);
        if (!ListenerUtil.mutListener.listen(44928)) {
            init(context, attrs);
        }
    }

    public CountBoxView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        if (!ListenerUtil.mutListener.listen(44929)) {
            init(context, attrs);
        }
    }

    private void init(Context context, final AttributeSet attrs) {
        int paddingPx = context.getResources().getDimensionPixelSize(R.dimen.count_box_padding);
        float textSize = 0;
        int backgroundRes = R.drawable.count_box_background;
        if (!ListenerUtil.mutListener.listen(44942)) {
            if (attrs != null) {
                TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CountBoxView);
                if (!ListenerUtil.mutListener.listen(44941)) {
                    if (a != null) {
                        final int N = a.getIndexCount();
                        if (!ListenerUtil.mutListener.listen(44939)) {
                            {
                                long _loopCounter528 = 0;
                                for (int i = 0; (ListenerUtil.mutListener.listen(44938) ? (i >= N) : (ListenerUtil.mutListener.listen(44937) ? (i <= N) : (ListenerUtil.mutListener.listen(44936) ? (i > N) : (ListenerUtil.mutListener.listen(44935) ? (i != N) : (ListenerUtil.mutListener.listen(44934) ? (i == N) : (i < N)))))); ++i) {
                                    ListenerUtil.loopListener.listen("_loopCounter528", ++_loopCounter528);
                                    int attr = a.getIndex(i);
                                    if (!ListenerUtil.mutListener.listen(44933)) {
                                        switch(attr) {
                                            case R.styleable.CountBoxView_textSizeOverride:
                                                if (!ListenerUtil.mutListener.listen(44930)) {
                                                    textSize = a.getDimensionPixelSize(R.styleable.CountBoxView_textSizeOverride, 0);
                                                }
                                                break;
                                            case R.styleable.CountBoxView_backgroundOverride:
                                                if (!ListenerUtil.mutListener.listen(44931)) {
                                                    backgroundRes = a.getResourceId(R.styleable.CountBoxView_backgroundOverride, R.drawable.count_box_background);
                                                }
                                                break;
                                            case R.styleable.CountBoxView_paddingOverride:
                                                if (!ListenerUtil.mutListener.listen(44932)) {
                                                    paddingPx = a.getDimensionPixelSize(R.styleable.CountBoxView_paddingOverride, paddingPx);
                                                }
                                                break;
                                            default:
                                                break;
                                        }
                                    }
                                }
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(44940)) {
                            a.recycle();
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(44943)) {
            this.setPadding(paddingPx, 0, paddingPx, 0);
        }
        if (!ListenerUtil.mutListener.listen(44944)) {
            this.setSingleLine(true);
        }
        if (!ListenerUtil.mutListener.listen(44945)) {
            this.setEllipsize(TextUtils.TruncateAt.MARQUEE);
        }
        if (!ListenerUtil.mutListener.listen(44954)) {
            if (!this.isInEditMode()) {
                if (!ListenerUtil.mutListener.listen(44953)) {
                    if ((ListenerUtil.mutListener.listen(44950) ? (textSize >= 0) : (ListenerUtil.mutListener.listen(44949) ? (textSize <= 0) : (ListenerUtil.mutListener.listen(44948) ? (textSize < 0) : (ListenerUtil.mutListener.listen(44947) ? (textSize != 0) : (ListenerUtil.mutListener.listen(44946) ? (textSize == 0) : (textSize > 0))))))) {
                        if (!ListenerUtil.mutListener.listen(44952)) {
                            this.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(44951)) {
                            this.setTextAppearance(context, android.R.style.TextAppearance_Small);
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(44955)) {
            this.setTypeface(Typeface.DEFAULT_BOLD);
        }
        if (!ListenerUtil.mutListener.listen(44956)) {
            this.setTextColor(getResources().getColor(android.R.color.white));
        }
        if (!ListenerUtil.mutListener.listen(44957)) {
            this.setBackgroundResource(backgroundRes);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (!ListenerUtil.mutListener.listen(44958)) {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
        if (!ListenerUtil.mutListener.listen(44965)) {
            if ((ListenerUtil.mutListener.listen(44963) ? (getMeasuredWidth() >= getMeasuredHeight()) : (ListenerUtil.mutListener.listen(44962) ? (getMeasuredWidth() <= getMeasuredHeight()) : (ListenerUtil.mutListener.listen(44961) ? (getMeasuredWidth() > getMeasuredHeight()) : (ListenerUtil.mutListener.listen(44960) ? (getMeasuredWidth() != getMeasuredHeight()) : (ListenerUtil.mutListener.listen(44959) ? (getMeasuredWidth() == getMeasuredHeight()) : (getMeasuredWidth() < getMeasuredHeight()))))))) {
                if (!ListenerUtil.mutListener.listen(44964)) {
                    setMeasuredDimension(getMeasuredHeight(), getMeasuredHeight());
                }
            }
        }
    }
}
