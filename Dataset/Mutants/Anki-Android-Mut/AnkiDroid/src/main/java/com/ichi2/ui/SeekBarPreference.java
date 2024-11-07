/* The following code was written by Matthew Wiggins
 * and is released under the APACHE 2.0 license
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * adjusted by Norbert Nagold 2011 <norbert.nagold@gmail.com>
 */
package com.ichi2.ui;

import android.app.AlertDialog;
import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import com.ichi2.anki.AnkiDroidApp;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

// TODO Tracked in https://github.com/ankidroid/Anki-Android/issues/5019
@SuppressWarnings("deprecation")
public class SeekBarPreference extends android.preference.DialogPreference implements SeekBar.OnSeekBarChangeListener {

    private static final String androidns = "http://schemas.android.com/apk/res/android";

    private SeekBar mSeekBar;

    private TextView mValueText;

    private final Context mContext;

    private final String mSuffix;

    private final int mDefault;

    private final int mMax;

    private final int mMin;

    private final int mInterval;

    private int mValue = 0;

    public SeekBarPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        mSuffix = attrs.getAttributeValue(androidns, "text");
        mDefault = attrs.getAttributeIntValue(androidns, "defaultValue", 0);
        mMax = attrs.getAttributeIntValue(androidns, "max", 100);
        mMin = attrs.getAttributeIntValue(AnkiDroidApp.XML_CUSTOM_NAMESPACE, "min", 0);
        mInterval = attrs.getAttributeIntValue(AnkiDroidApp.XML_CUSTOM_NAMESPACE, "interval", 1);
    }

    @Override
    protected View onCreateDialogView() {
        LinearLayout layout = new LinearLayout(mContext);
        if (!ListenerUtil.mutListener.listen(25131)) {
            layout.setOrientation(LinearLayout.VERTICAL);
        }
        if (!ListenerUtil.mutListener.listen(25132)) {
            layout.setPadding(6, 6, 6, 6);
        }
        if (!ListenerUtil.mutListener.listen(25133)) {
            mValueText = new FixedTextView(mContext);
        }
        if (!ListenerUtil.mutListener.listen(25134)) {
            mValueText.setGravity(Gravity.CENTER_HORIZONTAL);
        }
        if (!ListenerUtil.mutListener.listen(25135)) {
            mValueText.setTextSize(32);
        }
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        if (!ListenerUtil.mutListener.listen(25136)) {
            layout.addView(mValueText, params);
        }
        if (!ListenerUtil.mutListener.listen(25137)) {
            mSeekBar = new SeekBar(mContext);
        }
        if (!ListenerUtil.mutListener.listen(25138)) {
            mSeekBar.setOnSeekBarChangeListener(this);
        }
        if (!ListenerUtil.mutListener.listen(25139)) {
            layout.addView(mSeekBar, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        }
        if (!ListenerUtil.mutListener.listen(25141)) {
            if (shouldPersist()) {
                if (!ListenerUtil.mutListener.listen(25140)) {
                    mValue = getPersistedInt(mDefault);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(25150)) {
            mSeekBar.setMax((ListenerUtil.mutListener.listen(25149) ? (((ListenerUtil.mutListener.listen(25145) ? (mMax % mMin) : (ListenerUtil.mutListener.listen(25144) ? (mMax / mMin) : (ListenerUtil.mutListener.listen(25143) ? (mMax * mMin) : (ListenerUtil.mutListener.listen(25142) ? (mMax + mMin) : (mMax - mMin)))))) % mInterval) : (ListenerUtil.mutListener.listen(25148) ? (((ListenerUtil.mutListener.listen(25145) ? (mMax % mMin) : (ListenerUtil.mutListener.listen(25144) ? (mMax / mMin) : (ListenerUtil.mutListener.listen(25143) ? (mMax * mMin) : (ListenerUtil.mutListener.listen(25142) ? (mMax + mMin) : (mMax - mMin)))))) * mInterval) : (ListenerUtil.mutListener.listen(25147) ? (((ListenerUtil.mutListener.listen(25145) ? (mMax % mMin) : (ListenerUtil.mutListener.listen(25144) ? (mMax / mMin) : (ListenerUtil.mutListener.listen(25143) ? (mMax * mMin) : (ListenerUtil.mutListener.listen(25142) ? (mMax + mMin) : (mMax - mMin)))))) - mInterval) : (ListenerUtil.mutListener.listen(25146) ? (((ListenerUtil.mutListener.listen(25145) ? (mMax % mMin) : (ListenerUtil.mutListener.listen(25144) ? (mMax / mMin) : (ListenerUtil.mutListener.listen(25143) ? (mMax * mMin) : (ListenerUtil.mutListener.listen(25142) ? (mMax + mMin) : (mMax - mMin)))))) + mInterval) : (((ListenerUtil.mutListener.listen(25145) ? (mMax % mMin) : (ListenerUtil.mutListener.listen(25144) ? (mMax / mMin) : (ListenerUtil.mutListener.listen(25143) ? (mMax * mMin) : (ListenerUtil.mutListener.listen(25142) ? (mMax + mMin) : (mMax - mMin)))))) / mInterval))))));
        }
        if (!ListenerUtil.mutListener.listen(25159)) {
            mSeekBar.setProgress((ListenerUtil.mutListener.listen(25158) ? (((ListenerUtil.mutListener.listen(25154) ? (mValue % mMin) : (ListenerUtil.mutListener.listen(25153) ? (mValue / mMin) : (ListenerUtil.mutListener.listen(25152) ? (mValue * mMin) : (ListenerUtil.mutListener.listen(25151) ? (mValue + mMin) : (mValue - mMin)))))) % mInterval) : (ListenerUtil.mutListener.listen(25157) ? (((ListenerUtil.mutListener.listen(25154) ? (mValue % mMin) : (ListenerUtil.mutListener.listen(25153) ? (mValue / mMin) : (ListenerUtil.mutListener.listen(25152) ? (mValue * mMin) : (ListenerUtil.mutListener.listen(25151) ? (mValue + mMin) : (mValue - mMin)))))) * mInterval) : (ListenerUtil.mutListener.listen(25156) ? (((ListenerUtil.mutListener.listen(25154) ? (mValue % mMin) : (ListenerUtil.mutListener.listen(25153) ? (mValue / mMin) : (ListenerUtil.mutListener.listen(25152) ? (mValue * mMin) : (ListenerUtil.mutListener.listen(25151) ? (mValue + mMin) : (mValue - mMin)))))) - mInterval) : (ListenerUtil.mutListener.listen(25155) ? (((ListenerUtil.mutListener.listen(25154) ? (mValue % mMin) : (ListenerUtil.mutListener.listen(25153) ? (mValue / mMin) : (ListenerUtil.mutListener.listen(25152) ? (mValue * mMin) : (ListenerUtil.mutListener.listen(25151) ? (mValue + mMin) : (mValue - mMin)))))) + mInterval) : (((ListenerUtil.mutListener.listen(25154) ? (mValue % mMin) : (ListenerUtil.mutListener.listen(25153) ? (mValue / mMin) : (ListenerUtil.mutListener.listen(25152) ? (mValue * mMin) : (ListenerUtil.mutListener.listen(25151) ? (mValue + mMin) : (mValue - mMin)))))) / mInterval))))));
        }
        String t = String.valueOf(mValue);
        if (!ListenerUtil.mutListener.listen(25160)) {
            mValueText.setText(mSuffix == null ? t : t + mSuffix);
        }
        return layout;
    }

    @Override
    protected void onBindDialogView(View v) {
        if (!ListenerUtil.mutListener.listen(25161)) {
            super.onBindDialogView(v);
        }
        if (!ListenerUtil.mutListener.listen(25170)) {
            mSeekBar.setMax((ListenerUtil.mutListener.listen(25169) ? (((ListenerUtil.mutListener.listen(25165) ? (mMax % mMin) : (ListenerUtil.mutListener.listen(25164) ? (mMax / mMin) : (ListenerUtil.mutListener.listen(25163) ? (mMax * mMin) : (ListenerUtil.mutListener.listen(25162) ? (mMax + mMin) : (mMax - mMin)))))) % mInterval) : (ListenerUtil.mutListener.listen(25168) ? (((ListenerUtil.mutListener.listen(25165) ? (mMax % mMin) : (ListenerUtil.mutListener.listen(25164) ? (mMax / mMin) : (ListenerUtil.mutListener.listen(25163) ? (mMax * mMin) : (ListenerUtil.mutListener.listen(25162) ? (mMax + mMin) : (mMax - mMin)))))) * mInterval) : (ListenerUtil.mutListener.listen(25167) ? (((ListenerUtil.mutListener.listen(25165) ? (mMax % mMin) : (ListenerUtil.mutListener.listen(25164) ? (mMax / mMin) : (ListenerUtil.mutListener.listen(25163) ? (mMax * mMin) : (ListenerUtil.mutListener.listen(25162) ? (mMax + mMin) : (mMax - mMin)))))) - mInterval) : (ListenerUtil.mutListener.listen(25166) ? (((ListenerUtil.mutListener.listen(25165) ? (mMax % mMin) : (ListenerUtil.mutListener.listen(25164) ? (mMax / mMin) : (ListenerUtil.mutListener.listen(25163) ? (mMax * mMin) : (ListenerUtil.mutListener.listen(25162) ? (mMax + mMin) : (mMax - mMin)))))) + mInterval) : (((ListenerUtil.mutListener.listen(25165) ? (mMax % mMin) : (ListenerUtil.mutListener.listen(25164) ? (mMax / mMin) : (ListenerUtil.mutListener.listen(25163) ? (mMax * mMin) : (ListenerUtil.mutListener.listen(25162) ? (mMax + mMin) : (mMax - mMin)))))) / mInterval))))));
        }
        if (!ListenerUtil.mutListener.listen(25179)) {
            mSeekBar.setProgress((ListenerUtil.mutListener.listen(25178) ? (((ListenerUtil.mutListener.listen(25174) ? (mValue % mMin) : (ListenerUtil.mutListener.listen(25173) ? (mValue / mMin) : (ListenerUtil.mutListener.listen(25172) ? (mValue * mMin) : (ListenerUtil.mutListener.listen(25171) ? (mValue + mMin) : (mValue - mMin)))))) % mInterval) : (ListenerUtil.mutListener.listen(25177) ? (((ListenerUtil.mutListener.listen(25174) ? (mValue % mMin) : (ListenerUtil.mutListener.listen(25173) ? (mValue / mMin) : (ListenerUtil.mutListener.listen(25172) ? (mValue * mMin) : (ListenerUtil.mutListener.listen(25171) ? (mValue + mMin) : (mValue - mMin)))))) * mInterval) : (ListenerUtil.mutListener.listen(25176) ? (((ListenerUtil.mutListener.listen(25174) ? (mValue % mMin) : (ListenerUtil.mutListener.listen(25173) ? (mValue / mMin) : (ListenerUtil.mutListener.listen(25172) ? (mValue * mMin) : (ListenerUtil.mutListener.listen(25171) ? (mValue + mMin) : (mValue - mMin)))))) - mInterval) : (ListenerUtil.mutListener.listen(25175) ? (((ListenerUtil.mutListener.listen(25174) ? (mValue % mMin) : (ListenerUtil.mutListener.listen(25173) ? (mValue / mMin) : (ListenerUtil.mutListener.listen(25172) ? (mValue * mMin) : (ListenerUtil.mutListener.listen(25171) ? (mValue + mMin) : (mValue - mMin)))))) + mInterval) : (((ListenerUtil.mutListener.listen(25174) ? (mValue % mMin) : (ListenerUtil.mutListener.listen(25173) ? (mValue / mMin) : (ListenerUtil.mutListener.listen(25172) ? (mValue * mMin) : (ListenerUtil.mutListener.listen(25171) ? (mValue + mMin) : (mValue - mMin)))))) / mInterval))))));
        }
    }

    @Override
    protected void onSetInitialValue(boolean restore, Object defaultValue) {
        if (!ListenerUtil.mutListener.listen(25180)) {
            super.onSetInitialValue(restore, defaultValue);
        }
        if (!ListenerUtil.mutListener.listen(25181)) {
            mValue = getPersistedInt(mDefault);
        }
        if (!ListenerUtil.mutListener.listen(25184)) {
            if (restore) {
                if (!ListenerUtil.mutListener.listen(25183)) {
                    mValue = shouldPersist() ? getPersistedInt(mDefault) : 0;
                }
            } else {
                if (!ListenerUtil.mutListener.listen(25182)) {
                    mValue = (Integer) defaultValue;
                }
            }
        }
    }

    public void onProgressChanged(SeekBar seek, int value, boolean fromTouch) {
        if (!ListenerUtil.mutListener.listen(25195)) {
            if (fromTouch) {
                if (!ListenerUtil.mutListener.listen(25193)) {
                    mValue = (ListenerUtil.mutListener.listen(25192) ? (((ListenerUtil.mutListener.listen(25188) ? (value % mInterval) : (ListenerUtil.mutListener.listen(25187) ? (value / mInterval) : (ListenerUtil.mutListener.listen(25186) ? (value - mInterval) : (ListenerUtil.mutListener.listen(25185) ? (value + mInterval) : (value * mInterval)))))) % mMin) : (ListenerUtil.mutListener.listen(25191) ? (((ListenerUtil.mutListener.listen(25188) ? (value % mInterval) : (ListenerUtil.mutListener.listen(25187) ? (value / mInterval) : (ListenerUtil.mutListener.listen(25186) ? (value - mInterval) : (ListenerUtil.mutListener.listen(25185) ? (value + mInterval) : (value * mInterval)))))) / mMin) : (ListenerUtil.mutListener.listen(25190) ? (((ListenerUtil.mutListener.listen(25188) ? (value % mInterval) : (ListenerUtil.mutListener.listen(25187) ? (value / mInterval) : (ListenerUtil.mutListener.listen(25186) ? (value - mInterval) : (ListenerUtil.mutListener.listen(25185) ? (value + mInterval) : (value * mInterval)))))) * mMin) : (ListenerUtil.mutListener.listen(25189) ? (((ListenerUtil.mutListener.listen(25188) ? (value % mInterval) : (ListenerUtil.mutListener.listen(25187) ? (value / mInterval) : (ListenerUtil.mutListener.listen(25186) ? (value - mInterval) : (ListenerUtil.mutListener.listen(25185) ? (value + mInterval) : (value * mInterval)))))) - mMin) : (((ListenerUtil.mutListener.listen(25188) ? (value % mInterval) : (ListenerUtil.mutListener.listen(25187) ? (value / mInterval) : (ListenerUtil.mutListener.listen(25186) ? (value - mInterval) : (ListenerUtil.mutListener.listen(25185) ? (value + mInterval) : (value * mInterval)))))) + mMin)))));
                }
                String t = String.valueOf(mValue);
                if (!ListenerUtil.mutListener.listen(25194)) {
                    mValueText.setText(mSuffix == null ? t : t + mSuffix);
                }
            }
        }
    }

    public int getValue() {
        if ((ListenerUtil.mutListener.listen(25200) ? (mValue >= 0) : (ListenerUtil.mutListener.listen(25199) ? (mValue <= 0) : (ListenerUtil.mutListener.listen(25198) ? (mValue > 0) : (ListenerUtil.mutListener.listen(25197) ? (mValue < 0) : (ListenerUtil.mutListener.listen(25196) ? (mValue != 0) : (mValue == 0))))))) {
            return getPersistedInt(mDefault);
        } else {
            return mValue;
        }
    }

    public void setValue(int value) {
        if (!ListenerUtil.mutListener.listen(25201)) {
            mValue = value;
        }
        if (!ListenerUtil.mutListener.listen(25202)) {
            persistInt(value);
        }
    }

    public void onStartTrackingTouch(SeekBar seek) {
    }

    public void onStopTrackingTouch(SeekBar seek) {
        if (!ListenerUtil.mutListener.listen(25204)) {
            if (shouldPersist()) {
                if (!ListenerUtil.mutListener.listen(25203)) {
                    persistInt(mValue);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(25205)) {
            callChangeListener(mValue);
        }
        if (!ListenerUtil.mutListener.listen(25206)) {
            this.getDialog().dismiss();
        }
    }

    @Override
    protected void onPrepareDialogBuilder(AlertDialog.Builder builder) {
        if (!ListenerUtil.mutListener.listen(25207)) {
            super.onPrepareDialogBuilder(builder);
        }
        if (!ListenerUtil.mutListener.listen(25208)) {
            builder.setNegativeButton(null, null);
        }
        if (!ListenerUtil.mutListener.listen(25209)) {
            builder.setPositiveButton(null, null);
        }
        if (!ListenerUtil.mutListener.listen(25210)) {
            builder.setTitle(null);
        }
    }
}
