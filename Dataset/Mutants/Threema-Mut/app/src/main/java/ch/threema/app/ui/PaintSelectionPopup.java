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
package ch.threema.app.ui;

import android.content.Context;
import android.os.Build;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import ch.threema.app.R;
import ch.threema.app.utils.AnimationUtil;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class PaintSelectionPopup extends PopupWindow implements View.OnClickListener {

    public static final int TAG_REMOVE = 1;

    public static final int TAG_FLIP = 2;

    public static final int TAG_TO_FRONT = 3;

    private FrameLayout removeView, flipView, tofrontView;

    private View parentView;

    private PaintSelectPopupListener paintSelectPopupListener;

    private final int[] location = new int[2];

    public PaintSelectionPopup(Context context, View parentView) {
        super(context);
        if (!ListenerUtil.mutListener.listen(46633)) {
            this.parentView = parentView;
        }
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        LinearLayout topLayout = (LinearLayout) layoutInflater.inflate(R.layout.popup_paint_selection, null, true);
        if (!ListenerUtil.mutListener.listen(46634)) {
            this.removeView = topLayout.findViewById(R.id.remove_layout);
        }
        if (!ListenerUtil.mutListener.listen(46635)) {
            this.flipView = topLayout.findViewById(R.id.flip_layout);
        }
        if (!ListenerUtil.mutListener.listen(46636)) {
            this.tofrontView = topLayout.findViewById(R.id.tofront_layout);
        }
        if (!ListenerUtil.mutListener.listen(46637)) {
            setContentView(topLayout);
        }
        if (!ListenerUtil.mutListener.listen(46638)) {
            setInputMethodMode(PopupWindow.INPUT_METHOD_NOT_NEEDED);
        }
        if (!ListenerUtil.mutListener.listen(46639)) {
            setWidth(LinearLayout.LayoutParams.WRAP_CONTENT);
        }
        if (!ListenerUtil.mutListener.listen(46640)) {
            setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
        }
        if (!ListenerUtil.mutListener.listen(46641)) {
            setAnimationStyle(0);
        }
        if (!ListenerUtil.mutListener.listen(46642)) {
            setOutsideTouchable(false);
        }
        if (!ListenerUtil.mutListener.listen(46643)) {
            setFocusable(true);
        }
        if (!ListenerUtil.mutListener.listen(46650)) {
            if ((ListenerUtil.mutListener.listen(46648) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(46647) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(46646) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(46645) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(46644) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.LOLLIPOP) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP))))))) {
                if (!ListenerUtil.mutListener.listen(46649)) {
                    setElevation(10);
                }
            }
        }
    }

    public void show(int x, int y, boolean allowReordering) {
        if (!ListenerUtil.mutListener.listen(46651)) {
            this.removeView.setOnClickListener(this);
        }
        if (!ListenerUtil.mutListener.listen(46652)) {
            this.removeView.setTag(TAG_REMOVE);
        }
        if (!ListenerUtil.mutListener.listen(46661)) {
            if (allowReordering) {
                if (!ListenerUtil.mutListener.listen(46655)) {
                    this.flipView.setVisibility(View.VISIBLE);
                }
                if (!ListenerUtil.mutListener.listen(46656)) {
                    this.flipView.setOnClickListener(this);
                }
                if (!ListenerUtil.mutListener.listen(46657)) {
                    this.flipView.setTag(TAG_FLIP);
                }
                if (!ListenerUtil.mutListener.listen(46658)) {
                    this.tofrontView.setVisibility(View.VISIBLE);
                }
                if (!ListenerUtil.mutListener.listen(46659)) {
                    this.tofrontView.setOnClickListener(this);
                }
                if (!ListenerUtil.mutListener.listen(46660)) {
                    this.tofrontView.setTag(TAG_TO_FRONT);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(46653)) {
                    this.flipView.setVisibility(View.GONE);
                }
                if (!ListenerUtil.mutListener.listen(46654)) {
                    this.tofrontView.setVisibility(View.GONE);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(46663)) {
            if (this.paintSelectPopupListener != null) {
                if (!ListenerUtil.mutListener.listen(46662)) {
                    this.paintSelectPopupListener.onOpen();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(46664)) {
            showAtLocation(parentView, Gravity.LEFT | Gravity.TOP, x, y);
        }
        if (!ListenerUtil.mutListener.listen(46667)) {
            getContentView().getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

                @Override
                public void onGlobalLayout() {
                    if (!ListenerUtil.mutListener.listen(46665)) {
                        getContentView().getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    }
                    if (!ListenerUtil.mutListener.listen(46666)) {
                        AnimationUtil.popupAnimateIn(getContentView());
                    }
                }
            });
        }
    }

    @Override
    public void dismiss() {
        if (!ListenerUtil.mutListener.listen(46669)) {
            if (this.paintSelectPopupListener != null) {
                if (!ListenerUtil.mutListener.listen(46668)) {
                    this.paintSelectPopupListener.onClose();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(46671)) {
            AnimationUtil.popupAnimateOut(getContentView(), new Runnable() {

                @Override
                public void run() {
                    if (!ListenerUtil.mutListener.listen(46670)) {
                        PaintSelectionPopup.super.dismiss();
                    }
                }
            });
        }
    }

    public void setListener(PaintSelectPopupListener listener) {
        if (!ListenerUtil.mutListener.listen(46672)) {
            this.paintSelectPopupListener = listener;
        }
    }

    @Override
    public void onClick(View v) {
        if (!ListenerUtil.mutListener.listen(46675)) {
            if (paintSelectPopupListener != null) {
                if (!ListenerUtil.mutListener.listen(46673)) {
                    paintSelectPopupListener.onItemSelected((int) v.getTag());
                }
                if (!ListenerUtil.mutListener.listen(46674)) {
                    dismiss();
                }
            }
        }
    }

    public interface PaintSelectPopupListener {

        void onItemSelected(int tag);

        void onOpen();

        void onClose();
    }
}
