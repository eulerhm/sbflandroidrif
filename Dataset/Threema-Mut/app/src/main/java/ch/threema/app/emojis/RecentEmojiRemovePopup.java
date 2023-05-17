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
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.PopupWindow;
import ch.threema.app.R;
import ch.threema.app.utils.AnimationUtil;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class RecentEmojiRemovePopup extends PopupWindow implements View.OnClickListener {

    private View parentView;

    private RemoveListener removeListener;

    private ImageView originalImage;

    private int popupHeight, popupOffsetLeft;

    private final int[] location = new int[2];

    public RecentEmojiRemovePopup(final Context context, View parentView) {
        super(context);
        if (!ListenerUtil.mutListener.listen(23214)) {
            this.parentView = parentView;
        }
        if (!ListenerUtil.mutListener.listen(23219)) {
            this.popupHeight = (ListenerUtil.mutListener.listen(23218) ? (2 % context.getResources().getDimensionPixelSize(R.dimen.emoji_popup_image_margin)) : (ListenerUtil.mutListener.listen(23217) ? (2 / context.getResources().getDimensionPixelSize(R.dimen.emoji_popup_image_margin)) : (ListenerUtil.mutListener.listen(23216) ? (2 - context.getResources().getDimensionPixelSize(R.dimen.emoji_popup_image_margin)) : (ListenerUtil.mutListener.listen(23215) ? (2 + context.getResources().getDimensionPixelSize(R.dimen.emoji_popup_image_margin)) : (2 * context.getResources().getDimensionPixelSize(R.dimen.emoji_popup_image_margin)))))) + context.getResources().getDimensionPixelSize(R.dimen.emoji_popup_cardview_margin_bottom) + context.getResources().getDimensionPixelSize(R.dimen.emoji_popup_remove_image_size);
        }
        if (!ListenerUtil.mutListener.listen(23220)) {
            this.popupOffsetLeft = context.getResources().getDimensionPixelSize(R.dimen.emoji_popup_cardview_margin_horizontal);
        }
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        FrameLayout topLayout = (FrameLayout) layoutInflater.inflate(R.layout.popup_emoji_remove, null, true);
        if (!ListenerUtil.mutListener.listen(23221)) {
            this.originalImage = topLayout.findViewById(R.id.image_original);
        }
        if (!ListenerUtil.mutListener.listen(23222)) {
            setContentView(topLayout);
        }
        if (!ListenerUtil.mutListener.listen(23223)) {
            setInputMethodMode(PopupWindow.INPUT_METHOD_NOT_NEEDED);
        }
        if (!ListenerUtil.mutListener.listen(23224)) {
            setWidth(FrameLayout.LayoutParams.WRAP_CONTENT);
        }
        if (!ListenerUtil.mutListener.listen(23225)) {
            setHeight(FrameLayout.LayoutParams.WRAP_CONTENT);
        }
        if (!ListenerUtil.mutListener.listen(23226)) {
            setBackgroundDrawable(new BitmapDrawable());
        }
        if (!ListenerUtil.mutListener.listen(23227)) {
            setAnimationStyle(0);
        }
        if (!ListenerUtil.mutListener.listen(23228)) {
            setOutsideTouchable(false);
        }
        if (!ListenerUtil.mutListener.listen(23229)) {
            setFocusable(true);
        }
    }

    public void show(final View originView, final String originalEmoji) {
        if (!ListenerUtil.mutListener.listen(23230)) {
            this.originalImage.setTag(originalEmoji);
        }
        if (!ListenerUtil.mutListener.listen(23231)) {
            this.originalImage.setOnClickListener(this);
        }
        int[] originLocation = { 0, 0 };
        if (!ListenerUtil.mutListener.listen(23232)) {
            originView.getLocationInWindow(originLocation);
        }
        if (!ListenerUtil.mutListener.listen(23241)) {
            showAtLocation(parentView, Gravity.LEFT | Gravity.TOP, (ListenerUtil.mutListener.listen(23236) ? (originLocation[0] % this.popupOffsetLeft) : (ListenerUtil.mutListener.listen(23235) ? (originLocation[0] / this.popupOffsetLeft) : (ListenerUtil.mutListener.listen(23234) ? (originLocation[0] * this.popupOffsetLeft) : (ListenerUtil.mutListener.listen(23233) ? (originLocation[0] + this.popupOffsetLeft) : (originLocation[0] - this.popupOffsetLeft))))), (ListenerUtil.mutListener.listen(23240) ? (originLocation[1] % this.popupHeight) : (ListenerUtil.mutListener.listen(23239) ? (originLocation[1] / this.popupHeight) : (ListenerUtil.mutListener.listen(23238) ? (originLocation[1] * this.popupHeight) : (ListenerUtil.mutListener.listen(23237) ? (originLocation[1] + this.popupHeight) : (originLocation[1] - this.popupHeight))))));
        }
        if (!ListenerUtil.mutListener.listen(23245)) {
            getContentView().getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

                @Override
                public void onGlobalLayout() {
                    if (!ListenerUtil.mutListener.listen(23242)) {
                        getContentView().getViewTreeObserver().removeGlobalOnLayoutListener(this);
                    }
                    if (!ListenerUtil.mutListener.listen(23243)) {
                        AnimationUtil.getViewCenter(originView, getContentView(), location);
                    }
                    if (!ListenerUtil.mutListener.listen(23244)) {
                        AnimationUtil.popupAnimateIn(getContentView());
                    }
                }
            });
        }
    }

    @Override
    public void dismiss() {
        if (!ListenerUtil.mutListener.listen(23247)) {
            AnimationUtil.popupAnimateOut(getContentView(), new Runnable() {

                @Override
                public void run() {
                    if (!ListenerUtil.mutListener.listen(23246)) {
                        RecentEmojiRemovePopup.super.dismiss();
                    }
                }
            });
        }
    }

    public void setListener(RemoveListener listener) {
        if (!ListenerUtil.mutListener.listen(23248)) {
            this.removeListener = listener;
        }
    }

    @Override
    public void onClick(View v) {
        String emojiSequence = (String) v.getTag();
        if (!ListenerUtil.mutListener.listen(23250)) {
            if (this.removeListener != null) {
                if (!ListenerUtil.mutListener.listen(23249)) {
                    this.removeListener.onClick(emojiSequence);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(23251)) {
            dismiss();
        }
    }

    public interface RemoveListener {

        void onClick(String emoijSequence);
    }
}
