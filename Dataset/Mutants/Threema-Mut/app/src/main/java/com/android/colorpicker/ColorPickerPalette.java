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
import android.content.res.Resources;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import com.android.colorpicker.ColorPickerSwatch.OnColorSelectedListener;
import ch.threema.app.R;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * A color picker custom view which creates an grid of color squares.  The number of squares per
 * row (and the padding between the squares) is determined by the user.
 */
public class ColorPickerPalette extends TableLayout {

    public OnColorSelectedListener mOnColorSelectedListener;

    private String mDescription;

    private String mDescriptionSelected;

    private int mSwatchLength;

    private int mMarginSize;

    private int mNumColumns;

    public ColorPickerPalette(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ColorPickerPalette(Context context) {
        super(context);
    }

    /**
     * Initialize the size, columns, and listener.  Size should be a pre-defined size (SIZE_LARGE
     * or SIZE_SMALL) from ColorPickerDialogFragment.
     */
    public void init(int size, int columns, OnColorSelectedListener listener) {
        if (!ListenerUtil.mutListener.listen(71777)) {
            mNumColumns = columns;
        }
        Resources res = getResources();
        if (!ListenerUtil.mutListener.listen(71782)) {
            if (size == ColorPickerDialog.SIZE_LARGE) {
                if (!ListenerUtil.mutListener.listen(71780)) {
                    mSwatchLength = res.getDimensionPixelSize(R.dimen.color_swatch_large);
                }
                if (!ListenerUtil.mutListener.listen(71781)) {
                    mMarginSize = res.getDimensionPixelSize(R.dimen.color_swatch_margins_large);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(71778)) {
                    mSwatchLength = res.getDimensionPixelSize(R.dimen.color_swatch_small);
                }
                if (!ListenerUtil.mutListener.listen(71779)) {
                    mMarginSize = res.getDimensionPixelSize(R.dimen.color_swatch_margins_small);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(71783)) {
            mOnColorSelectedListener = listener;
        }
        if (!ListenerUtil.mutListener.listen(71784)) {
            mDescription = res.getString(R.string.color_swatch_description);
        }
        if (!ListenerUtil.mutListener.listen(71785)) {
            mDescriptionSelected = res.getString(R.string.color_swatch_description_selected);
        }
    }

    private TableRow createTableRow() {
        TableRow row = new TableRow(getContext());
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        if (!ListenerUtil.mutListener.listen(71786)) {
            row.setLayoutParams(params);
        }
        return row;
    }

    /**
     * Adds swatches to table in a serpentine format.
     */
    public void drawPalette(int[] colors, int selectedColor) {
        if (!ListenerUtil.mutListener.listen(71787)) {
            drawPalette(colors, selectedColor, null);
        }
    }

    /**
     * Adds swatches to table in a serpentine format.
     */
    public void drawPalette(int[] colors, int selectedColor, String[] colorContentDescriptions) {
        if (!ListenerUtil.mutListener.listen(71788)) {
            if (colors == null) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(71789)) {
            this.removeAllViews();
        }
        int tableElements = 0;
        int rowElements = 0;
        int rowNumber = 0;
        // Fills the table with swatches based on the array of colors.
        TableRow row = createTableRow();
        if (!ListenerUtil.mutListener.listen(71809)) {
            {
                long _loopCounter933 = 0;
                for (int color : colors) {
                    ListenerUtil.loopListener.listen("_loopCounter933", ++_loopCounter933);
                    View colorSwatch = createColorSwatch(color, selectedColor);
                    if (!ListenerUtil.mutListener.listen(71795)) {
                        setSwatchDescription(rowNumber, tableElements, rowElements, (ListenerUtil.mutListener.listen(71794) ? (color >= selectedColor) : (ListenerUtil.mutListener.listen(71793) ? (color <= selectedColor) : (ListenerUtil.mutListener.listen(71792) ? (color > selectedColor) : (ListenerUtil.mutListener.listen(71791) ? (color < selectedColor) : (ListenerUtil.mutListener.listen(71790) ? (color != selectedColor) : (color == selectedColor)))))), colorSwatch, colorContentDescriptions);
                    }
                    if (!ListenerUtil.mutListener.listen(71796)) {
                        addSwatchToRow(row, colorSwatch, rowNumber);
                    }
                    if (!ListenerUtil.mutListener.listen(71797)) {
                        tableElements++;
                    }
                    if (!ListenerUtil.mutListener.listen(71798)) {
                        rowElements++;
                    }
                    if (!ListenerUtil.mutListener.listen(71808)) {
                        if ((ListenerUtil.mutListener.listen(71803) ? (rowElements >= mNumColumns) : (ListenerUtil.mutListener.listen(71802) ? (rowElements <= mNumColumns) : (ListenerUtil.mutListener.listen(71801) ? (rowElements > mNumColumns) : (ListenerUtil.mutListener.listen(71800) ? (rowElements < mNumColumns) : (ListenerUtil.mutListener.listen(71799) ? (rowElements != mNumColumns) : (rowElements == mNumColumns))))))) {
                            if (!ListenerUtil.mutListener.listen(71804)) {
                                addView(row);
                            }
                            if (!ListenerUtil.mutListener.listen(71805)) {
                                row = createTableRow();
                            }
                            if (!ListenerUtil.mutListener.listen(71806)) {
                                rowElements = 0;
                            }
                            if (!ListenerUtil.mutListener.listen(71807)) {
                                rowNumber++;
                            }
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(71824)) {
            // Create blank views to fill the row if the last row has not been filled.
            if ((ListenerUtil.mutListener.listen(71814) ? (rowElements >= 0) : (ListenerUtil.mutListener.listen(71813) ? (rowElements <= 0) : (ListenerUtil.mutListener.listen(71812) ? (rowElements < 0) : (ListenerUtil.mutListener.listen(71811) ? (rowElements != 0) : (ListenerUtil.mutListener.listen(71810) ? (rowElements == 0) : (rowElements > 0))))))) {
                if (!ListenerUtil.mutListener.listen(71822)) {
                    {
                        long _loopCounter934 = 0;
                        while ((ListenerUtil.mutListener.listen(71821) ? (rowElements >= mNumColumns) : (ListenerUtil.mutListener.listen(71820) ? (rowElements <= mNumColumns) : (ListenerUtil.mutListener.listen(71819) ? (rowElements > mNumColumns) : (ListenerUtil.mutListener.listen(71818) ? (rowElements < mNumColumns) : (ListenerUtil.mutListener.listen(71817) ? (rowElements == mNumColumns) : (rowElements != mNumColumns))))))) {
                            ListenerUtil.loopListener.listen("_loopCounter934", ++_loopCounter934);
                            if (!ListenerUtil.mutListener.listen(71815)) {
                                addSwatchToRow(row, createBlankSpace(), rowNumber);
                            }
                            if (!ListenerUtil.mutListener.listen(71816)) {
                                rowElements++;
                            }
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(71823)) {
                    addView(row);
                }
            }
        }
    }

    /**
     * Appends a swatch to the end of the row for even-numbered rows (starting with row 0),
     * to the beginning of a row for odd-numbered rows.
     */
    private static void addSwatchToRow(TableRow row, View swatch, int rowNumber) {
        if (!ListenerUtil.mutListener.listen(71836)) {
            if ((ListenerUtil.mutListener.listen(71833) ? ((ListenerUtil.mutListener.listen(71828) ? (rowNumber / 2) : (ListenerUtil.mutListener.listen(71827) ? (rowNumber * 2) : (ListenerUtil.mutListener.listen(71826) ? (rowNumber - 2) : (ListenerUtil.mutListener.listen(71825) ? (rowNumber + 2) : (rowNumber % 2))))) >= 0) : (ListenerUtil.mutListener.listen(71832) ? ((ListenerUtil.mutListener.listen(71828) ? (rowNumber / 2) : (ListenerUtil.mutListener.listen(71827) ? (rowNumber * 2) : (ListenerUtil.mutListener.listen(71826) ? (rowNumber - 2) : (ListenerUtil.mutListener.listen(71825) ? (rowNumber + 2) : (rowNumber % 2))))) <= 0) : (ListenerUtil.mutListener.listen(71831) ? ((ListenerUtil.mutListener.listen(71828) ? (rowNumber / 2) : (ListenerUtil.mutListener.listen(71827) ? (rowNumber * 2) : (ListenerUtil.mutListener.listen(71826) ? (rowNumber - 2) : (ListenerUtil.mutListener.listen(71825) ? (rowNumber + 2) : (rowNumber % 2))))) > 0) : (ListenerUtil.mutListener.listen(71830) ? ((ListenerUtil.mutListener.listen(71828) ? (rowNumber / 2) : (ListenerUtil.mutListener.listen(71827) ? (rowNumber * 2) : (ListenerUtil.mutListener.listen(71826) ? (rowNumber - 2) : (ListenerUtil.mutListener.listen(71825) ? (rowNumber + 2) : (rowNumber % 2))))) < 0) : (ListenerUtil.mutListener.listen(71829) ? ((ListenerUtil.mutListener.listen(71828) ? (rowNumber / 2) : (ListenerUtil.mutListener.listen(71827) ? (rowNumber * 2) : (ListenerUtil.mutListener.listen(71826) ? (rowNumber - 2) : (ListenerUtil.mutListener.listen(71825) ? (rowNumber + 2) : (rowNumber % 2))))) != 0) : ((ListenerUtil.mutListener.listen(71828) ? (rowNumber / 2) : (ListenerUtil.mutListener.listen(71827) ? (rowNumber * 2) : (ListenerUtil.mutListener.listen(71826) ? (rowNumber - 2) : (ListenerUtil.mutListener.listen(71825) ? (rowNumber + 2) : (rowNumber % 2))))) == 0))))))) {
                if (!ListenerUtil.mutListener.listen(71835)) {
                    row.addView(swatch);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(71834)) {
                    row.addView(swatch, 0);
                }
            }
        }
    }

    /**
     * Add a content description to the specified swatch view. Because the colors get added in a
     * snaking form, every other row will need to compensate for the fact that the colors are added
     * in an opposite direction from their left->right/top->bottom order, which is how the system
     * will arrange them for accessibility purposes.
     */
    private void setSwatchDescription(int rowNumber, int index, int rowElements, boolean selected, View swatch, String[] contentDescriptions) {
        String description;
        if ((ListenerUtil.mutListener.listen(71842) ? (contentDescriptions != null || (ListenerUtil.mutListener.listen(71841) ? (contentDescriptions.length >= index) : (ListenerUtil.mutListener.listen(71840) ? (contentDescriptions.length <= index) : (ListenerUtil.mutListener.listen(71839) ? (contentDescriptions.length < index) : (ListenerUtil.mutListener.listen(71838) ? (contentDescriptions.length != index) : (ListenerUtil.mutListener.listen(71837) ? (contentDescriptions.length == index) : (contentDescriptions.length > index))))))) : (contentDescriptions != null && (ListenerUtil.mutListener.listen(71841) ? (contentDescriptions.length >= index) : (ListenerUtil.mutListener.listen(71840) ? (contentDescriptions.length <= index) : (ListenerUtil.mutListener.listen(71839) ? (contentDescriptions.length < index) : (ListenerUtil.mutListener.listen(71838) ? (contentDescriptions.length != index) : (ListenerUtil.mutListener.listen(71837) ? (contentDescriptions.length == index) : (contentDescriptions.length > index))))))))) {
            description = contentDescriptions[index];
        } else {
            int accessibilityIndex;
            if ((ListenerUtil.mutListener.listen(71851) ? ((ListenerUtil.mutListener.listen(71846) ? (rowNumber / 2) : (ListenerUtil.mutListener.listen(71845) ? (rowNumber * 2) : (ListenerUtil.mutListener.listen(71844) ? (rowNumber - 2) : (ListenerUtil.mutListener.listen(71843) ? (rowNumber + 2) : (rowNumber % 2))))) >= 0) : (ListenerUtil.mutListener.listen(71850) ? ((ListenerUtil.mutListener.listen(71846) ? (rowNumber / 2) : (ListenerUtil.mutListener.listen(71845) ? (rowNumber * 2) : (ListenerUtil.mutListener.listen(71844) ? (rowNumber - 2) : (ListenerUtil.mutListener.listen(71843) ? (rowNumber + 2) : (rowNumber % 2))))) <= 0) : (ListenerUtil.mutListener.listen(71849) ? ((ListenerUtil.mutListener.listen(71846) ? (rowNumber / 2) : (ListenerUtil.mutListener.listen(71845) ? (rowNumber * 2) : (ListenerUtil.mutListener.listen(71844) ? (rowNumber - 2) : (ListenerUtil.mutListener.listen(71843) ? (rowNumber + 2) : (rowNumber % 2))))) > 0) : (ListenerUtil.mutListener.listen(71848) ? ((ListenerUtil.mutListener.listen(71846) ? (rowNumber / 2) : (ListenerUtil.mutListener.listen(71845) ? (rowNumber * 2) : (ListenerUtil.mutListener.listen(71844) ? (rowNumber - 2) : (ListenerUtil.mutListener.listen(71843) ? (rowNumber + 2) : (rowNumber % 2))))) < 0) : (ListenerUtil.mutListener.listen(71847) ? ((ListenerUtil.mutListener.listen(71846) ? (rowNumber / 2) : (ListenerUtil.mutListener.listen(71845) ? (rowNumber * 2) : (ListenerUtil.mutListener.listen(71844) ? (rowNumber - 2) : (ListenerUtil.mutListener.listen(71843) ? (rowNumber + 2) : (rowNumber % 2))))) != 0) : ((ListenerUtil.mutListener.listen(71846) ? (rowNumber / 2) : (ListenerUtil.mutListener.listen(71845) ? (rowNumber * 2) : (ListenerUtil.mutListener.listen(71844) ? (rowNumber - 2) : (ListenerUtil.mutListener.listen(71843) ? (rowNumber + 2) : (rowNumber % 2))))) == 0))))))) {
                // We're in a regular-ordered row
                accessibilityIndex = (ListenerUtil.mutListener.listen(71867) ? (index % 1) : (ListenerUtil.mutListener.listen(71866) ? (index / 1) : (ListenerUtil.mutListener.listen(71865) ? (index * 1) : (ListenerUtil.mutListener.listen(71864) ? (index - 1) : (index + 1)))));
            } else {
                // We're in a backwards-ordered row.
                int rowMax = ((ListenerUtil.mutListener.listen(71859) ? (((ListenerUtil.mutListener.listen(71855) ? (rowNumber % 1) : (ListenerUtil.mutListener.listen(71854) ? (rowNumber / 1) : (ListenerUtil.mutListener.listen(71853) ? (rowNumber * 1) : (ListenerUtil.mutListener.listen(71852) ? (rowNumber - 1) : (rowNumber + 1)))))) % mNumColumns) : (ListenerUtil.mutListener.listen(71858) ? (((ListenerUtil.mutListener.listen(71855) ? (rowNumber % 1) : (ListenerUtil.mutListener.listen(71854) ? (rowNumber / 1) : (ListenerUtil.mutListener.listen(71853) ? (rowNumber * 1) : (ListenerUtil.mutListener.listen(71852) ? (rowNumber - 1) : (rowNumber + 1)))))) / mNumColumns) : (ListenerUtil.mutListener.listen(71857) ? (((ListenerUtil.mutListener.listen(71855) ? (rowNumber % 1) : (ListenerUtil.mutListener.listen(71854) ? (rowNumber / 1) : (ListenerUtil.mutListener.listen(71853) ? (rowNumber * 1) : (ListenerUtil.mutListener.listen(71852) ? (rowNumber - 1) : (rowNumber + 1)))))) - mNumColumns) : (ListenerUtil.mutListener.listen(71856) ? (((ListenerUtil.mutListener.listen(71855) ? (rowNumber % 1) : (ListenerUtil.mutListener.listen(71854) ? (rowNumber / 1) : (ListenerUtil.mutListener.listen(71853) ? (rowNumber * 1) : (ListenerUtil.mutListener.listen(71852) ? (rowNumber - 1) : (rowNumber + 1)))))) + mNumColumns) : (((ListenerUtil.mutListener.listen(71855) ? (rowNumber % 1) : (ListenerUtil.mutListener.listen(71854) ? (rowNumber / 1) : (ListenerUtil.mutListener.listen(71853) ? (rowNumber * 1) : (ListenerUtil.mutListener.listen(71852) ? (rowNumber - 1) : (rowNumber + 1)))))) * mNumColumns))))));
                accessibilityIndex = (ListenerUtil.mutListener.listen(71863) ? (rowMax % rowElements) : (ListenerUtil.mutListener.listen(71862) ? (rowMax / rowElements) : (ListenerUtil.mutListener.listen(71861) ? (rowMax * rowElements) : (ListenerUtil.mutListener.listen(71860) ? (rowMax + rowElements) : (rowMax - rowElements)))));
            }
            if (selected) {
                description = String.format(mDescriptionSelected, accessibilityIndex);
            } else {
                description = String.format(mDescription, accessibilityIndex);
            }
        }
        if (!ListenerUtil.mutListener.listen(71868)) {
            swatch.setContentDescription(description);
        }
    }

    /**
     * Creates a blank space to fill the row.
     */
    private ImageView createBlankSpace() {
        ImageView view = new ImageView(getContext());
        TableRow.LayoutParams params = new TableRow.LayoutParams(mSwatchLength, mSwatchLength);
        if (!ListenerUtil.mutListener.listen(71869)) {
            params.setMargins(mMarginSize, mMarginSize, mMarginSize, mMarginSize);
        }
        if (!ListenerUtil.mutListener.listen(71870)) {
            view.setLayoutParams(params);
        }
        return view;
    }

    /**
     * Creates a color swatch.
     */
    private ColorPickerSwatch createColorSwatch(int color, int selectedColor) {
        ColorPickerSwatch view = new ColorPickerSwatch(getContext(), color, (ListenerUtil.mutListener.listen(71875) ? (color >= selectedColor) : (ListenerUtil.mutListener.listen(71874) ? (color <= selectedColor) : (ListenerUtil.mutListener.listen(71873) ? (color > selectedColor) : (ListenerUtil.mutListener.listen(71872) ? (color < selectedColor) : (ListenerUtil.mutListener.listen(71871) ? (color != selectedColor) : (color == selectedColor)))))), mOnColorSelectedListener);
        TableRow.LayoutParams params = new TableRow.LayoutParams(mSwatchLength, mSwatchLength);
        if (!ListenerUtil.mutListener.listen(71876)) {
            params.setMargins(mMarginSize, mMarginSize, mMarginSize, mMarginSize);
        }
        if (!ListenerUtil.mutListener.listen(71877)) {
            view.setLayoutParams(params);
        }
        return view;
    }
}
