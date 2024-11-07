/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
 * Copyright (c) 2016-2021 Threema GmbH
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
import java.util.HashMap;
import ch.threema.app.R;
import ch.threema.app.utils.AnimationUtil;
import ch.threema.app.utils.ConfigUtils;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class DiverseEmojiPopup extends PopupWindow implements View.OnClickListener {

    private static String TAG = "DiverseEmojiPopup";

    private Context context;

    private ImageView originalImage, type1Image, type3Image, type4Image, type5Image, type6Image;

    private FrameLayout topLayout;

    private View parentView, originView;

    private EmojiManager emojiManager;

    private DiverseEmojiPopupListener diverseEmojiPopupListener;

    private HashMap<String, String> diverseEmojiPrefs;

    private int popupHeight, popupOffsetLeft;

    final int[] location = new int[2];

    public DiverseEmojiPopup(Context context, View parentView) {
        super(context);
        if (!ListenerUtil.mutListener.listen(14637)) {
            this.context = context;
        }
        if (!ListenerUtil.mutListener.listen(14638)) {
            this.parentView = parentView;
        }
        if (!ListenerUtil.mutListener.listen(14639)) {
            this.emojiManager = EmojiManager.getInstance(context);
        }
        if (!ListenerUtil.mutListener.listen(14644)) {
            this.popupHeight = (ListenerUtil.mutListener.listen(14643) ? (2 % context.getResources().getDimensionPixelSize(R.dimen.emoji_popup_image_margin)) : (ListenerUtil.mutListener.listen(14642) ? (2 / context.getResources().getDimensionPixelSize(R.dimen.emoji_popup_image_margin)) : (ListenerUtil.mutListener.listen(14641) ? (2 - context.getResources().getDimensionPixelSize(R.dimen.emoji_popup_image_margin)) : (ListenerUtil.mutListener.listen(14640) ? (2 + context.getResources().getDimensionPixelSize(R.dimen.emoji_popup_image_margin)) : (2 * context.getResources().getDimensionPixelSize(R.dimen.emoji_popup_image_margin)))))) + context.getResources().getDimensionPixelSize(R.dimen.emoji_popup_cardview_margin_bottom) + context.getResources().getDimensionPixelSize(R.dimen.emoji_picker_emoji_size);
        }
        if (!ListenerUtil.mutListener.listen(14645)) {
            this.popupOffsetLeft = context.getResources().getDimensionPixelSize(R.dimen.emoji_popup_cardview_margin_horizontal);
        }
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (!ListenerUtil.mutListener.listen(14646)) {
            topLayout = (FrameLayout) layoutInflater.inflate(R.layout.popup_diverse_emoji, null, true);
        }
        if (!ListenerUtil.mutListener.listen(14647)) {
            this.originalImage = topLayout.findViewById(R.id.image_original);
        }
        if (!ListenerUtil.mutListener.listen(14648)) {
            this.type1Image = topLayout.findViewById(R.id.image_type1);
        }
        if (!ListenerUtil.mutListener.listen(14649)) {
            this.type3Image = topLayout.findViewById(R.id.image_type3);
        }
        if (!ListenerUtil.mutListener.listen(14650)) {
            this.type4Image = topLayout.findViewById(R.id.image_type4);
        }
        if (!ListenerUtil.mutListener.listen(14651)) {
            this.type5Image = topLayout.findViewById(R.id.image_type5);
        }
        if (!ListenerUtil.mutListener.listen(14652)) {
            this.type6Image = topLayout.findViewById(R.id.image_type6);
        }
        if (!ListenerUtil.mutListener.listen(14653)) {
            setContentView(topLayout);
        }
        if (!ListenerUtil.mutListener.listen(14654)) {
            setInputMethodMode(PopupWindow.INPUT_METHOD_NOT_NEEDED);
        }
        if (!ListenerUtil.mutListener.listen(14655)) {
            setWidth(FrameLayout.LayoutParams.WRAP_CONTENT);
        }
        if (!ListenerUtil.mutListener.listen(14656)) {
            setHeight(FrameLayout.LayoutParams.WRAP_CONTENT);
        }
        if (!ListenerUtil.mutListener.listen(14657)) {
            setBackgroundDrawable(new BitmapDrawable());
        }
        if (!ListenerUtil.mutListener.listen(14658)) {
            setAnimationStyle(0);
        }
        if (!ListenerUtil.mutListener.listen(14659)) {
            setOutsideTouchable(false);
        }
        if (!ListenerUtil.mutListener.listen(14660)) {
            setFocusable(true);
        }
    }

    public void show(final View originView, final String originalEmoji, final HashMap<String, String> diverseEmojiPrefs) {
        EmojiInfo originalEmojiInfo = EmojiUtil.getEmojiInfo(originalEmoji);
        if (!ListenerUtil.mutListener.listen(14661)) {
            this.diverseEmojiPrefs = diverseEmojiPrefs;
        }
        if (!ListenerUtil.mutListener.listen(14663)) {
            if ((ListenerUtil.mutListener.listen(14662) ? (originalEmojiInfo == null && originalEmojiInfo.diversities.length != 5) : (originalEmojiInfo == null || originalEmojiInfo.diversities.length != 5))) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(14664)) {
            this.originView = originView;
        }
        if (!ListenerUtil.mutListener.listen(14665)) {
            this.originalImage.setImageDrawable(emojiManager.getEmojiDrawable(originalEmoji));
        }
        if (!ListenerUtil.mutListener.listen(14666)) {
            this.originalImage.setTag(originalEmoji);
        }
        if (!ListenerUtil.mutListener.listen(14667)) {
            this.originalImage.setOnClickListener(this);
        }
        if (!ListenerUtil.mutListener.listen(14668)) {
            this.type1Image.setImageDrawable(emojiManager.getEmojiDrawable(originalEmojiInfo.diversities[0]));
        }
        if (!ListenerUtil.mutListener.listen(14669)) {
            this.type1Image.setTag(originalEmojiInfo.diversities[0]);
        }
        if (!ListenerUtil.mutListener.listen(14670)) {
            this.type1Image.setOnClickListener(this);
        }
        if (!ListenerUtil.mutListener.listen(14671)) {
            this.type3Image.setImageDrawable(emojiManager.getEmojiDrawable(originalEmojiInfo.diversities[1]));
        }
        if (!ListenerUtil.mutListener.listen(14672)) {
            this.type3Image.setTag(originalEmojiInfo.diversities[1]);
        }
        if (!ListenerUtil.mutListener.listen(14673)) {
            this.type3Image.setOnClickListener(this);
        }
        if (!ListenerUtil.mutListener.listen(14674)) {
            this.type4Image.setImageDrawable(emojiManager.getEmojiDrawable(originalEmojiInfo.diversities[2]));
        }
        if (!ListenerUtil.mutListener.listen(14675)) {
            this.type4Image.setTag(originalEmojiInfo.diversities[2]);
        }
        if (!ListenerUtil.mutListener.listen(14676)) {
            this.type4Image.setOnClickListener(this);
        }
        if (!ListenerUtil.mutListener.listen(14677)) {
            this.type5Image.setImageDrawable(emojiManager.getEmojiDrawable(originalEmojiInfo.diversities[3]));
        }
        if (!ListenerUtil.mutListener.listen(14678)) {
            this.type5Image.setTag(originalEmojiInfo.diversities[3]);
        }
        if (!ListenerUtil.mutListener.listen(14679)) {
            this.type5Image.setOnClickListener(this);
        }
        if (!ListenerUtil.mutListener.listen(14680)) {
            this.type6Image.setImageDrawable(emojiManager.getEmojiDrawable(originalEmojiInfo.diversities[4]));
        }
        if (!ListenerUtil.mutListener.listen(14681)) {
            this.type6Image.setTag(originalEmojiInfo.diversities[4]);
        }
        if (!ListenerUtil.mutListener.listen(14682)) {
            this.type6Image.setOnClickListener(this);
        }
        if (!ListenerUtil.mutListener.listen(14684)) {
            if (this.diverseEmojiPopupListener != null) {
                if (!ListenerUtil.mutListener.listen(14683)) {
                    this.diverseEmojiPopupListener.onOpen();
                }
            }
        }
        int[] originLocation = { 0, 0 };
        if (!ListenerUtil.mutListener.listen(14685)) {
            originView.getLocationInWindow(originLocation);
        }
        if (!ListenerUtil.mutListener.listen(14694)) {
            showAtLocation(parentView, Gravity.LEFT | Gravity.TOP, (ListenerUtil.mutListener.listen(14689) ? (originLocation[0] % this.popupOffsetLeft) : (ListenerUtil.mutListener.listen(14688) ? (originLocation[0] / this.popupOffsetLeft) : (ListenerUtil.mutListener.listen(14687) ? (originLocation[0] * this.popupOffsetLeft) : (ListenerUtil.mutListener.listen(14686) ? (originLocation[0] + this.popupOffsetLeft) : (originLocation[0] - this.popupOffsetLeft))))), (ListenerUtil.mutListener.listen(14693) ? (originLocation[1] % this.popupHeight) : (ListenerUtil.mutListener.listen(14692) ? (originLocation[1] / this.popupHeight) : (ListenerUtil.mutListener.listen(14691) ? (originLocation[1] * this.popupHeight) : (ListenerUtil.mutListener.listen(14690) ? (originLocation[1] + this.popupHeight) : (originLocation[1] - this.popupHeight))))));
        }
        if (!ListenerUtil.mutListener.listen(14698)) {
            getContentView().getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

                @Override
                public void onGlobalLayout() {
                    if (!ListenerUtil.mutListener.listen(14695)) {
                        getContentView().getViewTreeObserver().removeGlobalOnLayoutListener(this);
                    }
                    if (!ListenerUtil.mutListener.listen(14696)) {
                        AnimationUtil.getViewCenter(originView, getContentView(), location);
                    }
                    if (!ListenerUtil.mutListener.listen(14697)) {
                        AnimationUtil.popupAnimateIn(getContentView());
                    }
                }
            });
        }
    }

    @Override
    public void dismiss() {
        if (!ListenerUtil.mutListener.listen(14700)) {
            if (this.diverseEmojiPopupListener != null) {
                if (!ListenerUtil.mutListener.listen(14699)) {
                    this.diverseEmojiPopupListener.onClose();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(14702)) {
            AnimationUtil.popupAnimateOut(getContentView(), new Runnable() {

                @Override
                public void run() {
                    if (!ListenerUtil.mutListener.listen(14701)) {
                        DiverseEmojiPopup.super.dismiss();
                    }
                }
            });
        }
    }

    public void setListener(DiverseEmojiPopupListener listener) {
        if (!ListenerUtil.mutListener.listen(14703)) {
            this.diverseEmojiPopupListener = listener;
        }
    }

    @Override
    public void onClick(View v) {
        if (!ListenerUtil.mutListener.listen(14708)) {
            if (diverseEmojiPopupListener != null) {
                String emojiSequence = (String) v.getTag();
                if (!ListenerUtil.mutListener.listen(14704)) {
                    diverseEmojiPopupListener.onDiverseEmojiClick((String) this.originalImage.getTag(), emojiSequence);
                }
                EmojiItemView emojiView = (EmojiItemView) originView;
                if (!ListenerUtil.mutListener.listen(14706)) {
                    if (emojiView != null) {
                        if (!ListenerUtil.mutListener.listen(14705)) {
                            emojiView.setEmoji(diverseEmojiPrefs.containsKey(emojiSequence) ? diverseEmojiPrefs.get(emojiSequence) : emojiSequence, true, ConfigUtils.getColorFromAttribute(context, R.attr.emoji_picker_hint));
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(14707)) {
                    dismiss();
                }
            }
        }
    }

    public interface DiverseEmojiPopupListener {

        void onDiverseEmojiClick(String originalEmojiSequence, String emoijSequence);

        void onOpen();

        void onClose();
    }
}
