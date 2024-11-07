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

public class EmojiDetailPopup extends PopupWindow implements View.OnClickListener {

    private ImageView originalImage;

    private FrameLayout topLayout;

    private View parentView;

    private EmojiManager emojiManager;

    private EmojiDetailPopupListener emojiDetailPopupListener;

    private int popupHeight, popupOffsetLeft;

    final int[] location = new int[2];

    public EmojiDetailPopup(final Context context, View parentView) {
        super(context);
        if (!ListenerUtil.mutListener.listen(14743)) {
            this.parentView = parentView;
        }
        if (!ListenerUtil.mutListener.listen(14744)) {
            this.emojiManager = EmojiManager.getInstance(context);
        }
        if (!ListenerUtil.mutListener.listen(14749)) {
            this.popupHeight = (ListenerUtil.mutListener.listen(14748) ? (2 % context.getResources().getDimensionPixelSize(R.dimen.emoji_popup_image_margin)) : (ListenerUtil.mutListener.listen(14747) ? (2 / context.getResources().getDimensionPixelSize(R.dimen.emoji_popup_image_margin)) : (ListenerUtil.mutListener.listen(14746) ? (2 - context.getResources().getDimensionPixelSize(R.dimen.emoji_popup_image_margin)) : (ListenerUtil.mutListener.listen(14745) ? (2 + context.getResources().getDimensionPixelSize(R.dimen.emoji_popup_image_margin)) : (2 * context.getResources().getDimensionPixelSize(R.dimen.emoji_popup_image_margin)))))) + context.getResources().getDimensionPixelSize(R.dimen.emoji_popup_cardview_margin_bottom) + context.getResources().getDimensionPixelSize(R.dimen.emoji_popup_emoji_size);
        }
        if (!ListenerUtil.mutListener.listen(14750)) {
            this.popupOffsetLeft = context.getResources().getDimensionPixelSize(R.dimen.emoji_popup_cardview_margin_horizontal);
        }
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (!ListenerUtil.mutListener.listen(14751)) {
            topLayout = (FrameLayout) layoutInflater.inflate(R.layout.popup_emoji_detail, null, true);
        }
        if (!ListenerUtil.mutListener.listen(14752)) {
            this.originalImage = topLayout.findViewById(R.id.image_original);
        }
        if (!ListenerUtil.mutListener.listen(14753)) {
            setContentView(topLayout);
        }
        if (!ListenerUtil.mutListener.listen(14754)) {
            setInputMethodMode(PopupWindow.INPUT_METHOD_NOT_NEEDED);
        }
        if (!ListenerUtil.mutListener.listen(14755)) {
            setWidth(FrameLayout.LayoutParams.WRAP_CONTENT);
        }
        if (!ListenerUtil.mutListener.listen(14756)) {
            setHeight(FrameLayout.LayoutParams.WRAP_CONTENT);
        }
        if (!ListenerUtil.mutListener.listen(14757)) {
            setBackgroundDrawable(new BitmapDrawable());
        }
        if (!ListenerUtil.mutListener.listen(14758)) {
            setAnimationStyle(0);
        }
        if (!ListenerUtil.mutListener.listen(14759)) {
            setOutsideTouchable(false);
        }
        if (!ListenerUtil.mutListener.listen(14760)) {
            setFocusable(true);
        }
    }

    public void show(final View originView, final String originalEmoji) {
        if (!ListenerUtil.mutListener.listen(14761)) {
            this.originalImage.setImageDrawable(emojiManager.getEmojiDrawable(originalEmoji));
        }
        if (!ListenerUtil.mutListener.listen(14762)) {
            this.originalImage.setTag(originalEmoji);
        }
        if (!ListenerUtil.mutListener.listen(14763)) {
            this.originalImage.setOnClickListener(this);
        }
        int[] originLocation = { 0, 0 };
        if (!ListenerUtil.mutListener.listen(14764)) {
            originView.getLocationInWindow(originLocation);
        }
        if (!ListenerUtil.mutListener.listen(14773)) {
            showAtLocation(parentView, Gravity.LEFT | Gravity.TOP, (ListenerUtil.mutListener.listen(14768) ? (originLocation[0] % this.popupOffsetLeft) : (ListenerUtil.mutListener.listen(14767) ? (originLocation[0] / this.popupOffsetLeft) : (ListenerUtil.mutListener.listen(14766) ? (originLocation[0] * this.popupOffsetLeft) : (ListenerUtil.mutListener.listen(14765) ? (originLocation[0] + this.popupOffsetLeft) : (originLocation[0] - this.popupOffsetLeft))))), (ListenerUtil.mutListener.listen(14772) ? (originLocation[1] % this.popupHeight) : (ListenerUtil.mutListener.listen(14771) ? (originLocation[1] / this.popupHeight) : (ListenerUtil.mutListener.listen(14770) ? (originLocation[1] * this.popupHeight) : (ListenerUtil.mutListener.listen(14769) ? (originLocation[1] + this.popupHeight) : (originLocation[1] - this.popupHeight))))));
        }
        if (!ListenerUtil.mutListener.listen(14777)) {
            getContentView().getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

                @Override
                public void onGlobalLayout() {
                    if (!ListenerUtil.mutListener.listen(14774)) {
                        getContentView().getViewTreeObserver().removeGlobalOnLayoutListener(this);
                    }
                    if (!ListenerUtil.mutListener.listen(14775)) {
                        AnimationUtil.getViewCenter(originView, getContentView(), location);
                    }
                    if (!ListenerUtil.mutListener.listen(14776)) {
                        AnimationUtil.popupAnimateIn(getContentView());
                    }
                }
            });
        }
    }

    @Override
    public void dismiss() {
        if (!ListenerUtil.mutListener.listen(14779)) {
            AnimationUtil.popupAnimateOut(getContentView(), new Runnable() {

                @Override
                public void run() {
                    if (!ListenerUtil.mutListener.listen(14778)) {
                        EmojiDetailPopup.super.dismiss();
                    }
                }
            });
        }
    }

    public void setListener(EmojiDetailPopupListener listener) {
        if (!ListenerUtil.mutListener.listen(14780)) {
            this.emojiDetailPopupListener = listener;
        }
    }

    @Override
    public void onClick(View v) {
        String emojiSequence = (String) v.getTag();
        if (!ListenerUtil.mutListener.listen(14782)) {
            if (this.emojiDetailPopupListener != null) {
                if (!ListenerUtil.mutListener.listen(14781)) {
                    this.emojiDetailPopupListener.onClick(emojiSequence);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(14783)) {
            dismiss();
        }
    }

    public interface EmojiDetailPopupListener {

        void onClick(String emoijSequence);
    }
}
