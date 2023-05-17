/*
 * Copyright (C) 2013 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.android.colorpicker;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import ch.threema.app.R;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Creates a circular swatch of a specified color.  Adds a checkmark if marked as checked.
 */
public class ColorPickerSwatch extends FrameLayout implements View.OnClickListener {

    private int mColor;

    private ImageView mSwatchImage;

    private ImageView mCheckmarkImage;

    private OnColorSelectedListener mOnColorSelectedListener;

    /**
     * Interface for a callback when a color square is selected.
     */
    public interface OnColorSelectedListener {

        /**
         * Called when a specific color square has been selected.
         */
        public void onColorSelected(int color);
    }

    public ColorPickerSwatch(Context context, int color, boolean checked, OnColorSelectedListener listener) {
        super(context);
        if (!ListenerUtil.mutListener.listen(71878)) {
            mColor = color;
        }
        if (!ListenerUtil.mutListener.listen(71879)) {
            mOnColorSelectedListener = listener;
        }
        if (!ListenerUtil.mutListener.listen(71880)) {
            LayoutInflater.from(context).inflate(R.layout.color_picker_swatch, this);
        }
        if (!ListenerUtil.mutListener.listen(71881)) {
            mSwatchImage = findViewById(R.id.color_picker_swatch);
        }
        if (!ListenerUtil.mutListener.listen(71882)) {
            mCheckmarkImage = findViewById(R.id.color_picker_checkmark);
        }
        if (!ListenerUtil.mutListener.listen(71883)) {
            setColor(color);
        }
        if (!ListenerUtil.mutListener.listen(71884)) {
            setChecked(checked);
        }
        if (!ListenerUtil.mutListener.listen(71885)) {
            setOnClickListener(this);
        }
    }

    protected void setColor(int color) {
        Drawable[] colorDrawable = new Drawable[] { getContext().getResources().getDrawable(R.drawable.color_picker_swatch) };
        if (!ListenerUtil.mutListener.listen(71886)) {
            mSwatchImage.setImageDrawable(new ColorStateDrawable(colorDrawable, color));
        }
    }

    private void setChecked(boolean checked) {
        if (!ListenerUtil.mutListener.listen(71889)) {
            if (checked) {
                if (!ListenerUtil.mutListener.listen(71888)) {
                    mCheckmarkImage.setVisibility(View.VISIBLE);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(71887)) {
                    mCheckmarkImage.setVisibility(View.GONE);
                }
            }
        }
    }

    @Override
    public void onClick(View v) {
        if (!ListenerUtil.mutListener.listen(71891)) {
            if (mOnColorSelectedListener != null) {
                if (!ListenerUtil.mutListener.listen(71890)) {
                    mOnColorSelectedListener.onColorSelected(mColor);
                }
            }
        }
    }
}
