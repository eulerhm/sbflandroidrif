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

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;
import com.android.colorpicker.ColorPickerSwatch.OnColorSelectedListener;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import ch.threema.app.R;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * A dialog which takes in as input an array of colors and creates a palette allowing the user to
 * select a specific color swatch, which invokes a listener.
 */
public class ColorPickerDialog extends DialogFragment implements OnColorSelectedListener {

    public static final int SIZE_LARGE = 1;

    public static final int SIZE_SMALL = 2;

    protected AlertDialog mAlertDialog;

    protected static final String KEY_TITLE_ID = "title_id";

    protected static final String KEY_COLORS = "colors";

    protected static final String KEY_COLOR_CONTENT_DESCRIPTIONS = "color_content_descriptions";

    protected static final String KEY_SELECTED_COLOR = "selected_color";

    protected static final String KEY_COLUMNS = "columns";

    protected static final String KEY_SIZE = "size";

    protected int mTitleResId = R.string.color_picker_default_title;

    protected int[] mColors = null;

    protected String[] mColorContentDescriptions = null;

    protected int mSelectedColor;

    protected int mColumns;

    protected int mSize;

    private ColorPickerPalette mPalette;

    private ProgressBar mProgress;

    protected OnColorSelectedListener mListener;

    public ColorPickerDialog() {
    }

    public static ColorPickerDialog newInstance(int titleResId, int[] colors, int selectedColor, int columns, int size) {
        ColorPickerDialog ret = new ColorPickerDialog();
        if (!ListenerUtil.mutListener.listen(71700)) {
            ret.initialize(titleResId, colors, selectedColor, columns, size);
        }
        return ret;
    }

    public void initialize(int titleResId, int[] colors, int selectedColor, int columns, int size) {
        if (!ListenerUtil.mutListener.listen(71701)) {
            setArguments(titleResId, columns, size);
        }
        if (!ListenerUtil.mutListener.listen(71702)) {
            setColors(colors, selectedColor);
        }
    }

    public void setArguments(int titleResId, int columns, int size) {
        Bundle bundle = new Bundle();
        if (!ListenerUtil.mutListener.listen(71703)) {
            bundle.putInt(KEY_TITLE_ID, titleResId);
        }
        if (!ListenerUtil.mutListener.listen(71704)) {
            bundle.putInt(KEY_COLUMNS, columns);
        }
        if (!ListenerUtil.mutListener.listen(71705)) {
            bundle.putInt(KEY_SIZE, size);
        }
        if (!ListenerUtil.mutListener.listen(71706)) {
            setArguments(bundle);
        }
    }

    public void setOnColorSelectedListener(OnColorSelectedListener listener) {
        if (!ListenerUtil.mutListener.listen(71707)) {
            mListener = listener;
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(71708)) {
            super.onCreate(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(71712)) {
            if (getArguments() != null) {
                if (!ListenerUtil.mutListener.listen(71709)) {
                    mTitleResId = getArguments().getInt(KEY_TITLE_ID);
                }
                if (!ListenerUtil.mutListener.listen(71710)) {
                    mColumns = getArguments().getInt(KEY_COLUMNS);
                }
                if (!ListenerUtil.mutListener.listen(71711)) {
                    mSize = getArguments().getInt(KEY_SIZE);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(71716)) {
            if (savedInstanceState != null) {
                if (!ListenerUtil.mutListener.listen(71713)) {
                    mColors = savedInstanceState.getIntArray(KEY_COLORS);
                }
                if (!ListenerUtil.mutListener.listen(71714)) {
                    mSelectedColor = (Integer) savedInstanceState.getSerializable(KEY_SELECTED_COLOR);
                }
                if (!ListenerUtil.mutListener.listen(71715)) {
                    mColorContentDescriptions = savedInstanceState.getStringArray(KEY_COLOR_CONTENT_DESCRIPTIONS);
                }
            }
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.color_picker_dialog, null);
        if (!ListenerUtil.mutListener.listen(71717)) {
            mProgress = view.findViewById(android.R.id.progress);
        }
        if (!ListenerUtil.mutListener.listen(71718)) {
            mPalette = view.findViewById(R.id.color_picker);
        }
        if (!ListenerUtil.mutListener.listen(71719)) {
            view.findViewById(R.id.scroll_view).setBackgroundColor(android.R.attr.windowBackground);
        }
        if (!ListenerUtil.mutListener.listen(71720)) {
            mPalette.init(mSize, mColumns, this);
        }
        if (!ListenerUtil.mutListener.listen(71722)) {
            if (mColors != null) {
                if (!ListenerUtil.mutListener.listen(71721)) {
                    showPaletteView();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(71723)) {
            mAlertDialog = new MaterialAlertDialogBuilder(getActivity(), R.style.Theme_Threema_Colorpicker).setTitle(mTitleResId).setView(view).create();
        }
        return mAlertDialog;
    }

    @Override
    public void onColorSelected(int color) {
        if (!ListenerUtil.mutListener.listen(71725)) {
            if (mListener != null) {
                if (!ListenerUtil.mutListener.listen(71724)) {
                    mListener.onColorSelected(color);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(71727)) {
            if (getTargetFragment() instanceof OnColorSelectedListener) {
                final OnColorSelectedListener listener = (OnColorSelectedListener) getTargetFragment();
                if (!ListenerUtil.mutListener.listen(71726)) {
                    listener.onColorSelected(color);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(71735)) {
            if ((ListenerUtil.mutListener.listen(71732) ? (color >= mSelectedColor) : (ListenerUtil.mutListener.listen(71731) ? (color <= mSelectedColor) : (ListenerUtil.mutListener.listen(71730) ? (color > mSelectedColor) : (ListenerUtil.mutListener.listen(71729) ? (color < mSelectedColor) : (ListenerUtil.mutListener.listen(71728) ? (color == mSelectedColor) : (color != mSelectedColor))))))) {
                if (!ListenerUtil.mutListener.listen(71733)) {
                    mSelectedColor = color;
                }
                if (!ListenerUtil.mutListener.listen(71734)) {
                    // Redraw palette to show checkmark on newly selected color before dismissing.
                    mPalette.drawPalette(mColors, mSelectedColor);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(71736)) {
            dismiss();
        }
    }

    public void showPaletteView() {
        if (!ListenerUtil.mutListener.listen(71741)) {
            if ((ListenerUtil.mutListener.listen(71737) ? (mProgress != null || mPalette != null) : (mProgress != null && mPalette != null))) {
                if (!ListenerUtil.mutListener.listen(71738)) {
                    mProgress.setVisibility(View.GONE);
                }
                if (!ListenerUtil.mutListener.listen(71739)) {
                    refreshPalette();
                }
                if (!ListenerUtil.mutListener.listen(71740)) {
                    mPalette.setVisibility(View.VISIBLE);
                }
            }
        }
    }

    public void showProgressBarView() {
        if (!ListenerUtil.mutListener.listen(71745)) {
            if ((ListenerUtil.mutListener.listen(71742) ? (mProgress != null || mPalette != null) : (mProgress != null && mPalette != null))) {
                if (!ListenerUtil.mutListener.listen(71743)) {
                    mProgress.setVisibility(View.VISIBLE);
                }
                if (!ListenerUtil.mutListener.listen(71744)) {
                    mPalette.setVisibility(View.GONE);
                }
            }
        }
    }

    public void setColors(int[] colors, int selectedColor) {
        if (!ListenerUtil.mutListener.listen(71755)) {
            if ((ListenerUtil.mutListener.listen(71751) ? (mColors != colors && (ListenerUtil.mutListener.listen(71750) ? (mSelectedColor >= selectedColor) : (ListenerUtil.mutListener.listen(71749) ? (mSelectedColor <= selectedColor) : (ListenerUtil.mutListener.listen(71748) ? (mSelectedColor > selectedColor) : (ListenerUtil.mutListener.listen(71747) ? (mSelectedColor < selectedColor) : (ListenerUtil.mutListener.listen(71746) ? (mSelectedColor == selectedColor) : (mSelectedColor != selectedColor))))))) : (mColors != colors || (ListenerUtil.mutListener.listen(71750) ? (mSelectedColor >= selectedColor) : (ListenerUtil.mutListener.listen(71749) ? (mSelectedColor <= selectedColor) : (ListenerUtil.mutListener.listen(71748) ? (mSelectedColor > selectedColor) : (ListenerUtil.mutListener.listen(71747) ? (mSelectedColor < selectedColor) : (ListenerUtil.mutListener.listen(71746) ? (mSelectedColor == selectedColor) : (mSelectedColor != selectedColor))))))))) {
                if (!ListenerUtil.mutListener.listen(71752)) {
                    mColors = colors;
                }
                if (!ListenerUtil.mutListener.listen(71753)) {
                    mSelectedColor = selectedColor;
                }
                if (!ListenerUtil.mutListener.listen(71754)) {
                    refreshPalette();
                }
            }
        }
    }

    public void setColors(int[] colors) {
        if (!ListenerUtil.mutListener.listen(71758)) {
            if (mColors != colors) {
                if (!ListenerUtil.mutListener.listen(71756)) {
                    mColors = colors;
                }
                if (!ListenerUtil.mutListener.listen(71757)) {
                    refreshPalette();
                }
            }
        }
    }

    public void setSelectedColor(int color) {
        if (!ListenerUtil.mutListener.listen(71766)) {
            if ((ListenerUtil.mutListener.listen(71763) ? (mSelectedColor >= color) : (ListenerUtil.mutListener.listen(71762) ? (mSelectedColor <= color) : (ListenerUtil.mutListener.listen(71761) ? (mSelectedColor > color) : (ListenerUtil.mutListener.listen(71760) ? (mSelectedColor < color) : (ListenerUtil.mutListener.listen(71759) ? (mSelectedColor == color) : (mSelectedColor != color))))))) {
                if (!ListenerUtil.mutListener.listen(71764)) {
                    mSelectedColor = color;
                }
                if (!ListenerUtil.mutListener.listen(71765)) {
                    refreshPalette();
                }
            }
        }
    }

    public void setColorContentDescriptions(String[] colorContentDescriptions) {
        if (!ListenerUtil.mutListener.listen(71769)) {
            if (mColorContentDescriptions != colorContentDescriptions) {
                if (!ListenerUtil.mutListener.listen(71767)) {
                    mColorContentDescriptions = colorContentDescriptions;
                }
                if (!ListenerUtil.mutListener.listen(71768)) {
                    refreshPalette();
                }
            }
        }
    }

    private void refreshPalette() {
        if (!ListenerUtil.mutListener.listen(71772)) {
            if ((ListenerUtil.mutListener.listen(71770) ? (mPalette != null || mColors != null) : (mPalette != null && mColors != null))) {
                if (!ListenerUtil.mutListener.listen(71771)) {
                    mPalette.drawPalette(mColors, mSelectedColor, mColorContentDescriptions);
                }
            }
        }
    }

    public int[] getColors() {
        return mColors;
    }

    public int getSelectedColor() {
        return mSelectedColor;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        if (!ListenerUtil.mutListener.listen(71773)) {
            super.onSaveInstanceState(outState);
        }
        if (!ListenerUtil.mutListener.listen(71774)) {
            outState.putIntArray(KEY_COLORS, mColors);
        }
        if (!ListenerUtil.mutListener.listen(71775)) {
            outState.putSerializable(KEY_SELECTED_COLOR, mSelectedColor);
        }
        if (!ListenerUtil.mutListener.listen(71776)) {
            outState.putStringArray(KEY_COLOR_CONTENT_DESCRIPTIONS, mColorContentDescriptions);
        }
    }
}
