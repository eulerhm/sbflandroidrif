/**
 * *************************************************************************************
 *  Copyright (c) 2013 Houssam Salem <houssam.salem.au@gmail.com>                        *
 *                                                                                       *
 *  This program is free software; you can redistribute it and/or modify it under        *
 *  the terms of the GNU General Public License as published by the Free Software        *
 *  Foundation; either version 3 of the License, or (at your option) any later           *
 *  version.                                                                             *
 *                                                                                       *
 *  This program is distributed in the hope that it will be useful, but WITHOUT ANY      *
 *  WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A      *
 *  PARTICULAR PURPOSE. See the GNU General Public License for more details.             *
 *                                                                                       *
 *  You should have received a copy of the GNU General Public License along with         *
 *  this program.  If not, see <http://www.gnu.org/licenses/>.                           *
 * **************************************************************************************
 */
package com.ichi2.preferences;

import android.content.Context;
import android.text.InputType;
import android.text.TextUtils;
import android.util.AttributeSet;
import com.ichi2.anki.AnkiDroidApp;
import com.ichi2.anki.R;
import com.ichi2.anki.UIUtils;
import com.ichi2.utils.JSONArray;
import com.ichi2.utils.JSONException;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

// TODO Tracked in https://github.com/ankidroid/Anki-Android/issues/5019
@SuppressWarnings("deprecation")
public class StepsPreference extends android.preference.EditTextPreference {

    private final boolean mAllowEmpty;

    public StepsPreference(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mAllowEmpty = getAllowEmptyFromAttributes(attrs);
        if (!ListenerUtil.mutListener.listen(24741)) {
            updateSettings();
        }
    }

    public StepsPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
        mAllowEmpty = getAllowEmptyFromAttributes(attrs);
        if (!ListenerUtil.mutListener.listen(24742)) {
            updateSettings();
        }
    }

    public StepsPreference(Context context) {
        super(context);
        mAllowEmpty = getAllowEmptyFromAttributes(null);
        if (!ListenerUtil.mutListener.listen(24743)) {
            updateSettings();
        }
    }

    /**
     * Update settings to show a numeric keyboard instead of the default keyboard.
     * <p>
     * This method should only be called once from the constructor.
     */
    private void updateSettings() {
        if (!ListenerUtil.mutListener.listen(24744)) {
            // Use the number pad but still allow normal text for spaces and decimals.
            getEditText().setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_CLASS_TEXT);
        }
    }

    @Override
    protected void onDialogClosed(boolean positiveResult) {
        if (!ListenerUtil.mutListener.listen(24750)) {
            if (positiveResult) {
                String validated = getValidatedStepsInput(getEditText().getText().toString());
                if (!ListenerUtil.mutListener.listen(24749)) {
                    if (validated == null) {
                        if (!ListenerUtil.mutListener.listen(24748)) {
                            UIUtils.showThemedToast(getContext(), getContext().getResources().getString(R.string.steps_error), false);
                        }
                    } else if ((ListenerUtil.mutListener.listen(24745) ? (TextUtils.isEmpty(validated) || !mAllowEmpty) : (TextUtils.isEmpty(validated) && !mAllowEmpty))) {
                        if (!ListenerUtil.mutListener.listen(24747)) {
                            UIUtils.showThemedToast(getContext(), getContext().getResources().getString(R.string.steps_min_error), false);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(24746)) {
                            setText(validated);
                        }
                    }
                }
            }
        }
    }

    /**
     * Check if the string is a valid format for steps and return that string, reformatted for better usability if
     * needed.
     *
     * @param steps User input in text editor.
     * @return The correctly formatted string or null if the input is not valid.
     */
    private String getValidatedStepsInput(String steps) {
        JSONArray stepsAr = convertToJSON(steps);
        if (stepsAr == null) {
            return null;
        } else {
            StringBuilder sb = new StringBuilder();
            if (!ListenerUtil.mutListener.listen(24752)) {
                {
                    long _loopCounter662 = 0;
                    for (String step : stepsAr.stringIterable()) {
                        ListenerUtil.loopListener.listen("_loopCounter662", ++_loopCounter662);
                        if (!ListenerUtil.mutListener.listen(24751)) {
                            sb.append(step).append(" ");
                        }
                    }
                }
            }
            return sb.toString().trim();
        }
    }

    /**
     * Convert steps format.
     *
     * @param a JSONArray representation of steps.
     * @return The steps as a space-separated string.
     */
    public static String convertFromJSON(JSONArray a) {
        StringBuilder sb = new StringBuilder();
        if (!ListenerUtil.mutListener.listen(24754)) {
            {
                long _loopCounter663 = 0;
                for (String s : a.stringIterable()) {
                    ListenerUtil.loopListener.listen("_loopCounter663", ++_loopCounter663);
                    if (!ListenerUtil.mutListener.listen(24753)) {
                        sb.append(s).append(" ");
                    }
                }
            }
        }
        return sb.toString().trim();
    }

    /**
     * Convert steps format. For better usability, rounded floats are converted to integers (e.g., 1.0 is converted to
     * 1).
     *
     * @param steps String representation of steps.
     * @return The steps as a JSONArray or null if the steps are not valid.
     */
    public static JSONArray convertToJSON(String steps) {
        JSONArray stepsAr = new JSONArray();
        if (!ListenerUtil.mutListener.listen(24755)) {
            steps = steps.trim();
        }
        if (!ListenerUtil.mutListener.listen(24756)) {
            if (TextUtils.isEmpty(steps)) {
                return stepsAr;
            }
        }
        try {
            if (!ListenerUtil.mutListener.listen(24771)) {
                {
                    long _loopCounter664 = 0;
                    for (String s : steps.split("\\s+")) {
                        ListenerUtil.loopListener.listen("_loopCounter664", ++_loopCounter664);
                        float f = Float.parseFloat(s);
                        if (!ListenerUtil.mutListener.listen(24762)) {
                            // 0 or less is not a valid step.
                            if ((ListenerUtil.mutListener.listen(24761) ? (f >= 0) : (ListenerUtil.mutListener.listen(24760) ? (f > 0) : (ListenerUtil.mutListener.listen(24759) ? (f < 0) : (ListenerUtil.mutListener.listen(24758) ? (f != 0) : (ListenerUtil.mutListener.listen(24757) ? (f == 0) : (f <= 0))))))) {
                                return null;
                            }
                        }
                        // Use whole numbers if we can (but still allow decimals)
                        int i = (int) f;
                        if (!ListenerUtil.mutListener.listen(24770)) {
                            if ((ListenerUtil.mutListener.listen(24767) ? (i >= f) : (ListenerUtil.mutListener.listen(24766) ? (i <= f) : (ListenerUtil.mutListener.listen(24765) ? (i > f) : (ListenerUtil.mutListener.listen(24764) ? (i < f) : (ListenerUtil.mutListener.listen(24763) ? (i != f) : (i == f))))))) {
                                if (!ListenerUtil.mutListener.listen(24769)) {
                                    stepsAr.put(i);
                                }
                            } else {
                                if (!ListenerUtil.mutListener.listen(24768)) {
                                    stepsAr.put(f);
                                }
                            }
                        }
                    }
                }
            }
        } catch (NumberFormatException | JSONException e) {
            // Can't serialize float. Value likely too big/small.
            return null;
        }
        return stepsAr;
    }

    private boolean getAllowEmptyFromAttributes(AttributeSet attrs) {
        if (!ListenerUtil.mutListener.listen(24772)) {
            if (attrs == null) {
                return true;
            }
        }
        return attrs.getAttributeBooleanValue(AnkiDroidApp.XML_CUSTOM_NAMESPACE, "allowEmpty", true);
    }
}
