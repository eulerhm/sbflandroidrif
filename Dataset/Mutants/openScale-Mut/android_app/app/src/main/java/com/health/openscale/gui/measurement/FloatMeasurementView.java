/* Copyright (C) 2018 Erik Johansson <erik@ejohansson.se>
*
*    This program is free software: you can redistribute it and/or modify
*    it under the terms of the GNU General Public License as published by
*    the Free Software Foundation, either version 3 of the License, or
*    (at your option) any later version.
*
*    This program is distributed in the hope that it will be useful,
*    but WITHOUT ANY WARRANTY; without even the implied warranty of
*    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
*    GNU General Public License for more details.
*
*    You should have received a copy of the GNU General Public License
*    along with this program.  If not, see <http://www.gnu.org/licenses/>
*/
package com.health.openscale.gui.measurement;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableRow;
import android.widget.TextView;
import androidx.preference.CheckBoxPreference;
import androidx.preference.ListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import androidx.preference.PreferenceViewHolder;
import androidx.preference.SwitchPreference;
import com.health.openscale.R;
import com.health.openscale.core.datatypes.ScaleMeasurement;
import com.health.openscale.core.evaluation.EvaluationResult;
import com.health.openscale.core.evaluation.EvaluationSheet;
import com.health.openscale.core.utils.Converters;
import java.util.Date;
import java.util.Locale;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public abstract class FloatMeasurementView extends MeasurementView {

    private static final char SYMBOL_UP = '\u279a';

    private static final char SYMBOL_NEUTRAL = '\u2799';

    private static final char SYMBOL_DOWN = '\u2798';

    private static final float NO_VALUE = -1.0f;

    private static final float AUTO_VALUE = -2.0f;

    private static float INC_DEC_DELTA = 0.1f;

    private Date dateTime;

    private float value = NO_VALUE;

    private float previousValue = NO_VALUE;

    private float userConvertedWeight;

    private EvaluationResult evaluationResult;

    private String nameText;

    private Button incButton;

    private Button decButton;

    public FloatMeasurementView(Context context, int textId, int iconId) {
        super(context, textId, iconId);
        if (!ListenerUtil.mutListener.listen(6858)) {
            initView(context);
        }
        if (!ListenerUtil.mutListener.listen(6859)) {
            nameText = getResources().getString(textId);
        }
    }

    private void initView(Context context) {
        if (!ListenerUtil.mutListener.listen(6860)) {
            setBackgroundIconColor(getColor());
        }
        if (!ListenerUtil.mutListener.listen(6861)) {
            incButton = new Button(context);
        }
        if (!ListenerUtil.mutListener.listen(6862)) {
            decButton = new Button(context);
        }
        LinearLayout incDecLayout = getIncDecLayout();
        if (!ListenerUtil.mutListener.listen(6863)) {
            incDecLayout.addView(incButton);
        }
        if (!ListenerUtil.mutListener.listen(6864)) {
            incDecLayout.addView(decButton);
        }
        if (!ListenerUtil.mutListener.listen(6865)) {
            incButton.setText("+");
        }
        if (!ListenerUtil.mutListener.listen(6866)) {
            incButton.setBackgroundColor(Color.TRANSPARENT);
        }
        if (!ListenerUtil.mutListener.listen(6867)) {
            incButton.setPadding(0, 0, 0, 0);
        }
        if (!ListenerUtil.mutListener.listen(6868)) {
            incButton.setLayoutParams(new TableRow.LayoutParams(LayoutParams.MATCH_PARENT, 0, 0.50f));
        }
        if (!ListenerUtil.mutListener.listen(6870)) {
            incButton.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {
                    if (!ListenerUtil.mutListener.listen(6869)) {
                        incValue();
                    }
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(6872)) {
            incButton.setOnTouchListener(new RepeatListener(400, 100, new OnClickListener() {

                @Override
                public void onClick(View view) {
                    if (!ListenerUtil.mutListener.listen(6871)) {
                        incValue();
                    }
                }
            }));
        }
        if (!ListenerUtil.mutListener.listen(6873)) {
            incButton.setVisibility(View.GONE);
        }
        if (!ListenerUtil.mutListener.listen(6874)) {
            decButton.setText("-");
        }
        if (!ListenerUtil.mutListener.listen(6875)) {
            decButton.setBackgroundColor(Color.TRANSPARENT);
        }
        if (!ListenerUtil.mutListener.listen(6876)) {
            decButton.setPadding(0, 0, 0, 0);
        }
        if (!ListenerUtil.mutListener.listen(6877)) {
            decButton.setLayoutParams(new TableRow.LayoutParams(LayoutParams.MATCH_PARENT, 0, 0.50f));
        }
        if (!ListenerUtil.mutListener.listen(6879)) {
            decButton.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {
                    if (!ListenerUtil.mutListener.listen(6878)) {
                        decValue();
                    }
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(6881)) {
            decButton.setOnTouchListener(new RepeatListener(400, 100, new OnClickListener() {

                @Override
                public void onClick(View view) {
                    if (!ListenerUtil.mutListener.listen(6880)) {
                        decValue();
                    }
                }
            }));
        }
        if (!ListenerUtil.mutListener.listen(6882)) {
            decButton.setVisibility(View.GONE);
        }
    }

    private float clampValue(float value) {
        return Math.max(0.0f, Math.min(getMaxValue(), value));
    }

    private float roundValue(float value) {
        final float factor = (float) Math.pow(10, getDecimalPlaces());
        return (ListenerUtil.mutListener.listen(6890) ? (Math.round((ListenerUtil.mutListener.listen(6886) ? (value % factor) : (ListenerUtil.mutListener.listen(6885) ? (value / factor) : (ListenerUtil.mutListener.listen(6884) ? (value - factor) : (ListenerUtil.mutListener.listen(6883) ? (value + factor) : (value * factor)))))) % factor) : (ListenerUtil.mutListener.listen(6889) ? (Math.round((ListenerUtil.mutListener.listen(6886) ? (value % factor) : (ListenerUtil.mutListener.listen(6885) ? (value / factor) : (ListenerUtil.mutListener.listen(6884) ? (value - factor) : (ListenerUtil.mutListener.listen(6883) ? (value + factor) : (value * factor)))))) * factor) : (ListenerUtil.mutListener.listen(6888) ? (Math.round((ListenerUtil.mutListener.listen(6886) ? (value % factor) : (ListenerUtil.mutListener.listen(6885) ? (value / factor) : (ListenerUtil.mutListener.listen(6884) ? (value - factor) : (ListenerUtil.mutListener.listen(6883) ? (value + factor) : (value * factor)))))) - factor) : (ListenerUtil.mutListener.listen(6887) ? (Math.round((ListenerUtil.mutListener.listen(6886) ? (value % factor) : (ListenerUtil.mutListener.listen(6885) ? (value / factor) : (ListenerUtil.mutListener.listen(6884) ? (value - factor) : (ListenerUtil.mutListener.listen(6883) ? (value + factor) : (value * factor)))))) + factor) : (Math.round((ListenerUtil.mutListener.listen(6886) ? (value % factor) : (ListenerUtil.mutListener.listen(6885) ? (value / factor) : (ListenerUtil.mutListener.listen(6884) ? (value - factor) : (ListenerUtil.mutListener.listen(6883) ? (value + factor) : (value * factor)))))) / factor)))));
    }

    private void setValueInner(float newValue, boolean callListener) {
        if (!ListenerUtil.mutListener.listen(6891)) {
            value = newValue;
        }
        if (!ListenerUtil.mutListener.listen(6892)) {
            evaluationResult = null;
        }
        if (!ListenerUtil.mutListener.listen(6893)) {
            if (!getUpdateViews()) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(6907)) {
            if ((ListenerUtil.mutListener.listen(6898) ? (value >= AUTO_VALUE) : (ListenerUtil.mutListener.listen(6897) ? (value <= AUTO_VALUE) : (ListenerUtil.mutListener.listen(6896) ? (value > AUTO_VALUE) : (ListenerUtil.mutListener.listen(6895) ? (value < AUTO_VALUE) : (ListenerUtil.mutListener.listen(6894) ? (value != AUTO_VALUE) : (value == AUTO_VALUE))))))) {
                if (!ListenerUtil.mutListener.listen(6906)) {
                    setValueView(getContext().getString(R.string.label_automatic), false);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(6899)) {
                    setValueView(formatValue(value, true), callListener);
                }
                if (!ListenerUtil.mutListener.listen(6905)) {
                    if (getMeasurementMode() != MeasurementViewMode.ADD) {
                        final float evalValue = maybeConvertToOriginalValue(value);
                        EvaluationSheet evalSheet = new EvaluationSheet(getScaleUser(), dateTime);
                        if (!ListenerUtil.mutListener.listen(6900)) {
                            evaluationResult = evaluateSheet(evalSheet, evalValue);
                        }
                        if (!ListenerUtil.mutListener.listen(6904)) {
                            if (evaluationResult != null) {
                                if (!ListenerUtil.mutListener.listen(6901)) {
                                    evaluationResult.value = value;
                                }
                                if (!ListenerUtil.mutListener.listen(6902)) {
                                    evaluationResult.lowLimit = maybeConvertValue(evaluationResult.lowLimit);
                                }
                                if (!ListenerUtil.mutListener.listen(6903)) {
                                    evaluationResult.highLimit = maybeConvertValue(evaluationResult.highLimit);
                                }
                            }
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(6908)) {
            setEvaluationView(evaluationResult);
        }
    }

    private void setPreviousValueInner(float newPreviousValue) {
        if (!ListenerUtil.mutListener.listen(6909)) {
            previousValue = newPreviousValue;
        }
        if (!ListenerUtil.mutListener.listen(6910)) {
            if (!getUpdateViews()) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(6939)) {
            if ((ListenerUtil.mutListener.listen(6915) ? (previousValue <= 0.0f) : (ListenerUtil.mutListener.listen(6914) ? (previousValue > 0.0f) : (ListenerUtil.mutListener.listen(6913) ? (previousValue < 0.0f) : (ListenerUtil.mutListener.listen(6912) ? (previousValue != 0.0f) : (ListenerUtil.mutListener.listen(6911) ? (previousValue == 0.0f) : (previousValue >= 0.0f))))))) {
                final float diff = (ListenerUtil.mutListener.listen(6920) ? (value % previousValue) : (ListenerUtil.mutListener.listen(6919) ? (value / previousValue) : (ListenerUtil.mutListener.listen(6918) ? (value * previousValue) : (ListenerUtil.mutListener.listen(6917) ? (value + previousValue) : (value - previousValue)))));
                char symbol;
                if ((ListenerUtil.mutListener.listen(6925) ? (diff >= 0.0) : (ListenerUtil.mutListener.listen(6924) ? (diff <= 0.0) : (ListenerUtil.mutListener.listen(6923) ? (diff < 0.0) : (ListenerUtil.mutListener.listen(6922) ? (diff != 0.0) : (ListenerUtil.mutListener.listen(6921) ? (diff == 0.0) : (diff > 0.0))))))) {
                    symbol = SYMBOL_UP;
                } else if ((ListenerUtil.mutListener.listen(6930) ? (diff >= 0.0) : (ListenerUtil.mutListener.listen(6929) ? (diff <= 0.0) : (ListenerUtil.mutListener.listen(6928) ? (diff > 0.0) : (ListenerUtil.mutListener.listen(6927) ? (diff != 0.0) : (ListenerUtil.mutListener.listen(6926) ? (diff == 0.0) : (diff < 0.0))))))) {
                    symbol = SYMBOL_DOWN;
                } else {
                    symbol = SYMBOL_NEUTRAL;
                }
                SpannableStringBuilder text = new SpannableStringBuilder(nameText);
                if (!ListenerUtil.mutListener.listen(6931)) {
                    text.append("\n");
                }
                int start = text.length();
                if (!ListenerUtil.mutListener.listen(6932)) {
                    text.append(symbol);
                }
                if (!ListenerUtil.mutListener.listen(6933)) {
                    text.setSpan(new ForegroundColorSpan(Color.GRAY), start, text.length(), Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
                }
                if (!ListenerUtil.mutListener.listen(6934)) {
                    start = text.length();
                }
                if (!ListenerUtil.mutListener.listen(6935)) {
                    text.append(' ');
                }
                if (!ListenerUtil.mutListener.listen(6936)) {
                    text.append(formatValue(diff, true));
                }
                if (!ListenerUtil.mutListener.listen(6937)) {
                    text.setSpan(new RelativeSizeSpan(0.8f), start, text.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                }
                if (!ListenerUtil.mutListener.listen(6938)) {
                    setNameView(text);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(6916)) {
                    setNameView(nameText);
                }
            }
        }
    }

    private void setValue(float newValue, float newPreviousValue, boolean callListener) {
        final boolean valueChanged = (ListenerUtil.mutListener.listen(6944) ? (newValue >= value) : (ListenerUtil.mutListener.listen(6943) ? (newValue <= value) : (ListenerUtil.mutListener.listen(6942) ? (newValue > value) : (ListenerUtil.mutListener.listen(6941) ? (newValue < value) : (ListenerUtil.mutListener.listen(6940) ? (newValue == value) : (newValue != value))))));
        final boolean previousValueChanged = (ListenerUtil.mutListener.listen(6949) ? (newPreviousValue >= previousValue) : (ListenerUtil.mutListener.listen(6948) ? (newPreviousValue <= previousValue) : (ListenerUtil.mutListener.listen(6947) ? (newPreviousValue > previousValue) : (ListenerUtil.mutListener.listen(6946) ? (newPreviousValue < previousValue) : (ListenerUtil.mutListener.listen(6945) ? (newPreviousValue == previousValue) : (newPreviousValue != previousValue))))));
        if (!ListenerUtil.mutListener.listen(6951)) {
            if (valueChanged) {
                if (!ListenerUtil.mutListener.listen(6950)) {
                    setValueInner(newValue, callListener);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(6954)) {
            if ((ListenerUtil.mutListener.listen(6952) ? (valueChanged && previousValueChanged) : (valueChanged || previousValueChanged))) {
                if (!ListenerUtil.mutListener.listen(6953)) {
                    setPreviousValueInner(newPreviousValue);
                }
            }
        }
    }

    private void incValue() {
        if (!ListenerUtil.mutListener.listen(6959)) {
            setValue(clampValue((ListenerUtil.mutListener.listen(6958) ? (value % INC_DEC_DELTA) : (ListenerUtil.mutListener.listen(6957) ? (value / INC_DEC_DELTA) : (ListenerUtil.mutListener.listen(6956) ? (value * INC_DEC_DELTA) : (ListenerUtil.mutListener.listen(6955) ? (value - INC_DEC_DELTA) : (value + INC_DEC_DELTA)))))), previousValue, true);
        }
    }

    private void decValue() {
        if (!ListenerUtil.mutListener.listen(6964)) {
            setValue(clampValue((ListenerUtil.mutListener.listen(6963) ? (value % INC_DEC_DELTA) : (ListenerUtil.mutListener.listen(6962) ? (value / INC_DEC_DELTA) : (ListenerUtil.mutListener.listen(6961) ? (value * INC_DEC_DELTA) : (ListenerUtil.mutListener.listen(6960) ? (value + INC_DEC_DELTA) : (value - INC_DEC_DELTA)))))), previousValue, true);
        }
    }

    private String formatValue(float value, boolean withUnit) {
        final String format = String.format(Locale.getDefault(), "%%.%df%s", getDecimalPlaces(), (ListenerUtil.mutListener.listen(6965) ? (withUnit || !getUnit().isEmpty()) : (withUnit && !getUnit().isEmpty())) ? " %s" : "");
        return String.format(Locale.getDefault(), format, value, getUnit());
    }

    protected String formatValue(float value) {
        return formatValue(value, false);
    }

    protected abstract float getMeasurementValue(ScaleMeasurement measurement);

    protected abstract void setMeasurementValue(float value, ScaleMeasurement measurement);

    public float getConvertedMeasurementValue(ScaleMeasurement measurement) {
        if (!ListenerUtil.mutListener.listen(6966)) {
            updateUserConvertedWeight(measurement);
        }
        float convertedValue = getMeasurementValue(measurement);
        if (!ListenerUtil.mutListener.listen(6967)) {
            convertedValue = maybeConvertValue(convertedValue);
        }
        if (!ListenerUtil.mutListener.listen(6968)) {
            convertedValue = clampValue(convertedValue);
        }
        if (!ListenerUtil.mutListener.listen(6969)) {
            convertedValue = roundValue(convertedValue);
        }
        return convertedValue;
    }

    public abstract String getUnit();

    protected abstract float getMaxValue();

    protected int getDecimalPlaces() {
        return 2;
    }

    public abstract int getColor();

    protected boolean isEstimationSupported() {
        return false;
    }

    protected void prepareEstimationFormulaPreference(ListPreference preference) {
    }

    protected abstract EvaluationResult evaluateSheet(EvaluationSheet evalSheet, float value);

    private boolean useAutoValue() {
        return (ListenerUtil.mutListener.listen(6971) ? ((ListenerUtil.mutListener.listen(6970) ? (isEstimationSupported() || getSettings().isEstimationEnabled()) : (isEstimationSupported() && getSettings().isEstimationEnabled())) || getMeasurementMode() == MeasurementViewMode.ADD) : ((ListenerUtil.mutListener.listen(6970) ? (isEstimationSupported() || getSettings().isEstimationEnabled()) : (isEstimationSupported() && getSettings().isEstimationEnabled())) && getMeasurementMode() == MeasurementViewMode.ADD));
    }

    // Only one of these can return true
    protected boolean supportsAbsoluteWeightToPercentageConversion() {
        return false;
    }

    protected boolean supportsPercentageToAbsoluteWeightConversion() {
        return false;
    }

    private boolean supportsConversion() {
        return (ListenerUtil.mutListener.listen(6972) ? (supportsAbsoluteWeightToPercentageConversion() && supportsPercentageToAbsoluteWeightConversion()) : (supportsAbsoluteWeightToPercentageConversion() || supportsPercentageToAbsoluteWeightConversion()));
    }

    protected boolean shouldConvertAbsoluteWeightToPercentage() {
        return (ListenerUtil.mutListener.listen(6973) ? (supportsAbsoluteWeightToPercentageConversion() || getSettings().isPercentageEnabled()) : (supportsAbsoluteWeightToPercentageConversion() && getSettings().isPercentageEnabled()));
    }

    protected boolean shouldConvertPercentageToAbsoluteWeight() {
        return (ListenerUtil.mutListener.listen(6974) ? (supportsPercentageToAbsoluteWeightConversion() || !getSettings().isPercentageEnabled()) : (supportsPercentageToAbsoluteWeightConversion() && !getSettings().isPercentageEnabled()));
    }

    private boolean shouldConvert() {
        return (ListenerUtil.mutListener.listen(6975) ? (shouldConvertAbsoluteWeightToPercentage() && shouldConvertPercentageToAbsoluteWeight()) : (shouldConvertAbsoluteWeightToPercentage() || shouldConvertPercentageToAbsoluteWeight()));
    }

    private float makeAbsoluteWeight(float percentage) {
        return (ListenerUtil.mutListener.listen(6983) ? ((ListenerUtil.mutListener.listen(6979) ? (userConvertedWeight % 100.0f) : (ListenerUtil.mutListener.listen(6978) ? (userConvertedWeight * 100.0f) : (ListenerUtil.mutListener.listen(6977) ? (userConvertedWeight - 100.0f) : (ListenerUtil.mutListener.listen(6976) ? (userConvertedWeight + 100.0f) : (userConvertedWeight / 100.0f))))) % percentage) : (ListenerUtil.mutListener.listen(6982) ? ((ListenerUtil.mutListener.listen(6979) ? (userConvertedWeight % 100.0f) : (ListenerUtil.mutListener.listen(6978) ? (userConvertedWeight * 100.0f) : (ListenerUtil.mutListener.listen(6977) ? (userConvertedWeight - 100.0f) : (ListenerUtil.mutListener.listen(6976) ? (userConvertedWeight + 100.0f) : (userConvertedWeight / 100.0f))))) / percentage) : (ListenerUtil.mutListener.listen(6981) ? ((ListenerUtil.mutListener.listen(6979) ? (userConvertedWeight % 100.0f) : (ListenerUtil.mutListener.listen(6978) ? (userConvertedWeight * 100.0f) : (ListenerUtil.mutListener.listen(6977) ? (userConvertedWeight - 100.0f) : (ListenerUtil.mutListener.listen(6976) ? (userConvertedWeight + 100.0f) : (userConvertedWeight / 100.0f))))) - percentage) : (ListenerUtil.mutListener.listen(6980) ? ((ListenerUtil.mutListener.listen(6979) ? (userConvertedWeight % 100.0f) : (ListenerUtil.mutListener.listen(6978) ? (userConvertedWeight * 100.0f) : (ListenerUtil.mutListener.listen(6977) ? (userConvertedWeight - 100.0f) : (ListenerUtil.mutListener.listen(6976) ? (userConvertedWeight + 100.0f) : (userConvertedWeight / 100.0f))))) + percentage) : ((ListenerUtil.mutListener.listen(6979) ? (userConvertedWeight % 100.0f) : (ListenerUtil.mutListener.listen(6978) ? (userConvertedWeight * 100.0f) : (ListenerUtil.mutListener.listen(6977) ? (userConvertedWeight - 100.0f) : (ListenerUtil.mutListener.listen(6976) ? (userConvertedWeight + 100.0f) : (userConvertedWeight / 100.0f))))) * percentage)))));
    }

    private float makeRelativeWeight(float absolute) {
        return (ListenerUtil.mutListener.listen(6991) ? ((ListenerUtil.mutListener.listen(6987) ? (100.0f % userConvertedWeight) : (ListenerUtil.mutListener.listen(6986) ? (100.0f * userConvertedWeight) : (ListenerUtil.mutListener.listen(6985) ? (100.0f - userConvertedWeight) : (ListenerUtil.mutListener.listen(6984) ? (100.0f + userConvertedWeight) : (100.0f / userConvertedWeight))))) % absolute) : (ListenerUtil.mutListener.listen(6990) ? ((ListenerUtil.mutListener.listen(6987) ? (100.0f % userConvertedWeight) : (ListenerUtil.mutListener.listen(6986) ? (100.0f * userConvertedWeight) : (ListenerUtil.mutListener.listen(6985) ? (100.0f - userConvertedWeight) : (ListenerUtil.mutListener.listen(6984) ? (100.0f + userConvertedWeight) : (100.0f / userConvertedWeight))))) / absolute) : (ListenerUtil.mutListener.listen(6989) ? ((ListenerUtil.mutListener.listen(6987) ? (100.0f % userConvertedWeight) : (ListenerUtil.mutListener.listen(6986) ? (100.0f * userConvertedWeight) : (ListenerUtil.mutListener.listen(6985) ? (100.0f - userConvertedWeight) : (ListenerUtil.mutListener.listen(6984) ? (100.0f + userConvertedWeight) : (100.0f / userConvertedWeight))))) - absolute) : (ListenerUtil.mutListener.listen(6988) ? ((ListenerUtil.mutListener.listen(6987) ? (100.0f % userConvertedWeight) : (ListenerUtil.mutListener.listen(6986) ? (100.0f * userConvertedWeight) : (ListenerUtil.mutListener.listen(6985) ? (100.0f - userConvertedWeight) : (ListenerUtil.mutListener.listen(6984) ? (100.0f + userConvertedWeight) : (100.0f / userConvertedWeight))))) + absolute) : ((ListenerUtil.mutListener.listen(6987) ? (100.0f % userConvertedWeight) : (ListenerUtil.mutListener.listen(6986) ? (100.0f * userConvertedWeight) : (ListenerUtil.mutListener.listen(6985) ? (100.0f - userConvertedWeight) : (ListenerUtil.mutListener.listen(6984) ? (100.0f + userConvertedWeight) : (100.0f / userConvertedWeight))))) * absolute)))));
    }

    protected float maybeConvertAbsoluteWeightToPercentage(float value) {
        if (!ListenerUtil.mutListener.listen(6992)) {
            if (shouldConvertAbsoluteWeightToPercentage()) {
                return makeRelativeWeight(value);
            }
        }
        return value;
    }

    protected float maybeConvertPercentageToAbsoluteWeight(float value) {
        if (!ListenerUtil.mutListener.listen(6993)) {
            if (shouldConvertPercentageToAbsoluteWeight()) {
                return makeAbsoluteWeight(value);
            }
        }
        return value;
    }

    private float maybeConvertValue(float value) {
        if (!ListenerUtil.mutListener.listen(6994)) {
            if (shouldConvertAbsoluteWeightToPercentage()) {
                return makeRelativeWeight(value);
            }
        }
        if (!ListenerUtil.mutListener.listen(6995)) {
            if (shouldConvertPercentageToAbsoluteWeight()) {
                return makeAbsoluteWeight(value);
            }
        }
        return value;
    }

    private float maybeConvertToOriginalValue(float value) {
        if (!ListenerUtil.mutListener.listen(6996)) {
            if (shouldConvertAbsoluteWeightToPercentage()) {
                return makeAbsoluteWeight(value);
            }
        }
        if (!ListenerUtil.mutListener.listen(6997)) {
            if (shouldConvertPercentageToAbsoluteWeight()) {
                return makeRelativeWeight(value);
            }
        }
        return value;
    }

    private void updateUserConvertedWeight(ScaleMeasurement measurement) {
        if (!ListenerUtil.mutListener.listen(7000)) {
            if (shouldConvert()) {
                if (!ListenerUtil.mutListener.listen(6999)) {
                    // Make sure weight is never 0 to avoid division by 0
                    userConvertedWeight = Math.max(1.0f, Converters.fromKilogram(measurement.getWeight(), getScaleUser().getScaleUnit()));
                }
            } else {
                if (!ListenerUtil.mutListener.listen(6998)) {
                    // Only valid when a conversion is enabled
                    userConvertedWeight = -1.0f;
                }
            }
        }
    }

    @Override
    public void loadFrom(ScaleMeasurement measurement, ScaleMeasurement previousMeasurement) {
        if (!ListenerUtil.mutListener.listen(7001)) {
            dateTime = measurement.getDateTime();
        }
        float newValue = AUTO_VALUE;
        float newPreviousValue = NO_VALUE;
        if (!ListenerUtil.mutListener.listen(7006)) {
            if (!useAutoValue()) {
                if (!ListenerUtil.mutListener.listen(7002)) {
                    newValue = getConvertedMeasurementValue(measurement);
                }
                if (!ListenerUtil.mutListener.listen(7005)) {
                    if (previousMeasurement != null) {
                        float saveUserConvertedWeight = userConvertedWeight;
                        if (!ListenerUtil.mutListener.listen(7003)) {
                            newPreviousValue = getConvertedMeasurementValue(previousMeasurement);
                        }
                        if (!ListenerUtil.mutListener.listen(7004)) {
                            userConvertedWeight = saveUserConvertedWeight;
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(7007)) {
            setValue(newValue, newPreviousValue, false);
        }
    }

    @Override
    public void saveTo(ScaleMeasurement measurement) {
        if (!ListenerUtil.mutListener.listen(7011)) {
            if (!useAutoValue()) {
                if (!ListenerUtil.mutListener.listen(7009)) {
                    if (shouldConvert()) {
                        if (!ListenerUtil.mutListener.listen(7008)) {
                            // Make sure to use the current weight to get a correct value
                            updateUserConvertedWeight(measurement);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(7010)) {
                    // May need to convert back to original value before saving
                    setMeasurementValue(maybeConvertToOriginalValue(value), measurement);
                }
            }
        }
    }

    @Override
    public void clearIn(ScaleMeasurement measurement) {
        if (!ListenerUtil.mutListener.listen(7012)) {
            setMeasurementValue(0.0f, measurement);
        }
    }

    @Override
    public void restoreState(Bundle state) {
        if (!ListenerUtil.mutListener.listen(7013)) {
            setValue(state.getFloat(getKey()), previousValue, true);
        }
    }

    @Override
    public void saveState(Bundle state) {
        if (!ListenerUtil.mutListener.listen(7014)) {
            state.putFloat(getKey(), value);
        }
    }

    @Override
    public String getValueAsString(boolean withUnit) {
        if (!ListenerUtil.mutListener.listen(7015)) {
            if (useAutoValue()) {
                return getContext().getString(R.string.label_automatic);
            }
        }
        return formatValue(value, withUnit);
    }

    public float getValue() {
        return value;
    }

    @Override
    public CharSequence getName() {
        return nameText;
    }

    protected void setName(int textId) {
        if (!ListenerUtil.mutListener.listen(7016)) {
            nameText = getResources().getString(textId);
        }
        if (!ListenerUtil.mutListener.listen(7017)) {
            setNameView(nameText);
        }
    }

    @Override
    public void appendDiffValue(SpannableStringBuilder text, boolean newLine) {
        if (!ListenerUtil.mutListener.listen(7023)) {
            if ((ListenerUtil.mutListener.listen(7022) ? (previousValue >= 0.0f) : (ListenerUtil.mutListener.listen(7021) ? (previousValue <= 0.0f) : (ListenerUtil.mutListener.listen(7020) ? (previousValue > 0.0f) : (ListenerUtil.mutListener.listen(7019) ? (previousValue != 0.0f) : (ListenerUtil.mutListener.listen(7018) ? (previousValue == 0.0f) : (previousValue < 0.0f))))))) {
                return;
            }
        }
        char symbol;
        int color;
        final float diff = (ListenerUtil.mutListener.listen(7027) ? (value % previousValue) : (ListenerUtil.mutListener.listen(7026) ? (value / previousValue) : (ListenerUtil.mutListener.listen(7025) ? (value * previousValue) : (ListenerUtil.mutListener.listen(7024) ? (value + previousValue) : (value - previousValue)))));
        if ((ListenerUtil.mutListener.listen(7032) ? (diff >= 0.0f) : (ListenerUtil.mutListener.listen(7031) ? (diff <= 0.0f) : (ListenerUtil.mutListener.listen(7030) ? (diff < 0.0f) : (ListenerUtil.mutListener.listen(7029) ? (diff != 0.0f) : (ListenerUtil.mutListener.listen(7028) ? (diff == 0.0f) : (diff > 0.0f))))))) {
            symbol = SYMBOL_UP;
            color = Color.GREEN;
        } else if ((ListenerUtil.mutListener.listen(7037) ? (diff >= 0.0f) : (ListenerUtil.mutListener.listen(7036) ? (diff <= 0.0f) : (ListenerUtil.mutListener.listen(7035) ? (diff > 0.0f) : (ListenerUtil.mutListener.listen(7034) ? (diff != 0.0f) : (ListenerUtil.mutListener.listen(7033) ? (diff == 0.0f) : (diff < 0.0f))))))) {
            symbol = SYMBOL_DOWN;
            color = Color.RED;
        } else {
            symbol = SYMBOL_NEUTRAL;
            color = Color.GRAY;
        }
        // change color depending on if you are going towards or away from your weight goal
        if (this instanceof WeightMeasurementView) {
            if ((ListenerUtil.mutListener.listen(7042) ? (diff >= 0.0f) : (ListenerUtil.mutListener.listen(7041) ? (diff <= 0.0f) : (ListenerUtil.mutListener.listen(7040) ? (diff < 0.0f) : (ListenerUtil.mutListener.listen(7039) ? (diff != 0.0f) : (ListenerUtil.mutListener.listen(7038) ? (diff == 0.0f) : (diff > 0.0f))))))) {
                color = ((ListenerUtil.mutListener.listen(7057) ? (value >= getScaleUser().getGoalWeight()) : (ListenerUtil.mutListener.listen(7056) ? (value <= getScaleUser().getGoalWeight()) : (ListenerUtil.mutListener.listen(7055) ? (value < getScaleUser().getGoalWeight()) : (ListenerUtil.mutListener.listen(7054) ? (value != getScaleUser().getGoalWeight()) : (ListenerUtil.mutListener.listen(7053) ? (value == getScaleUser().getGoalWeight()) : (value > getScaleUser().getGoalWeight()))))))) ? Color.RED : Color.GREEN;
            } else if ((ListenerUtil.mutListener.listen(7047) ? (diff >= 0.0f) : (ListenerUtil.mutListener.listen(7046) ? (diff <= 0.0f) : (ListenerUtil.mutListener.listen(7045) ? (diff > 0.0f) : (ListenerUtil.mutListener.listen(7044) ? (diff != 0.0f) : (ListenerUtil.mutListener.listen(7043) ? (diff == 0.0f) : (diff < 0.0f))))))) {
                color = ((ListenerUtil.mutListener.listen(7052) ? (value >= getScaleUser().getGoalWeight()) : (ListenerUtil.mutListener.listen(7051) ? (value <= getScaleUser().getGoalWeight()) : (ListenerUtil.mutListener.listen(7050) ? (value > getScaleUser().getGoalWeight()) : (ListenerUtil.mutListener.listen(7049) ? (value != getScaleUser().getGoalWeight()) : (ListenerUtil.mutListener.listen(7048) ? (value == getScaleUser().getGoalWeight()) : (value < getScaleUser().getGoalWeight()))))))) ? Color.RED : Color.GREEN;
            }
        }
        final float evalValue = maybeConvertToOriginalValue(value);
        EvaluationSheet evalSheet = new EvaluationSheet(getScaleUser(), dateTime);
        if (!ListenerUtil.mutListener.listen(7058)) {
            evaluationResult = evaluateSheet(evalSheet, evalValue);
        }
        if (evaluationResult != null) {
            switch(evaluationResult.eval_state) {
                case LOW:
                    color = ((ListenerUtil.mutListener.listen(7063) ? (diff >= 0.0f) : (ListenerUtil.mutListener.listen(7062) ? (diff <= 0.0f) : (ListenerUtil.mutListener.listen(7061) ? (diff < 0.0f) : (ListenerUtil.mutListener.listen(7060) ? (diff != 0.0f) : (ListenerUtil.mutListener.listen(7059) ? (diff == 0.0f) : (diff > 0.0f))))))) ? Color.GREEN : Color.RED;
                    break;
                case HIGH:
                    color = ((ListenerUtil.mutListener.listen(7068) ? (diff >= 0.0f) : (ListenerUtil.mutListener.listen(7067) ? (diff <= 0.0f) : (ListenerUtil.mutListener.listen(7066) ? (diff > 0.0f) : (ListenerUtil.mutListener.listen(7065) ? (diff != 0.0f) : (ListenerUtil.mutListener.listen(7064) ? (diff == 0.0f) : (diff < 0.0f))))))) ? Color.GREEN : Color.RED;
                    break;
                case NORMAL:
                    color = Color.GREEN;
                    break;
            }
        }
        if (!ListenerUtil.mutListener.listen(7070)) {
            if (newLine) {
                if (!ListenerUtil.mutListener.listen(7069)) {
                    text.append('\n');
                }
            }
        }
        int start = text.length();
        if (!ListenerUtil.mutListener.listen(7071)) {
            text.append(symbol);
        }
        if (!ListenerUtil.mutListener.listen(7072)) {
            text.setSpan(new ForegroundColorSpan(color), start, text.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        if (!ListenerUtil.mutListener.listen(7073)) {
            text.append(' ');
        }
        if (!ListenerUtil.mutListener.listen(7074)) {
            start = text.length();
        }
        if (!ListenerUtil.mutListener.listen(7075)) {
            text.append(formatValue(diff));
        }
        if (!ListenerUtil.mutListener.listen(7076)) {
            text.setSpan(new RelativeSizeSpan(0.8f), start, text.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
    }

    @Override
    protected boolean isEditable() {
        if (!ListenerUtil.mutListener.listen(7077)) {
            if (useAutoValue()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void setEditMode(MeasurementViewMode mode) {
        if (!ListenerUtil.mutListener.listen(7078)) {
            super.setEditMode(mode);
        }
        if (!ListenerUtil.mutListener.listen(7084)) {
            if ((ListenerUtil.mutListener.listen(7079) ? (mode == MeasurementViewMode.VIEW && !isEditable()) : (mode == MeasurementViewMode.VIEW || !isEditable()))) {
                if (!ListenerUtil.mutListener.listen(7082)) {
                    incButton.setVisibility(View.GONE);
                }
                if (!ListenerUtil.mutListener.listen(7083)) {
                    decButton.setVisibility(View.GONE);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(7080)) {
                    incButton.setVisibility(View.VISIBLE);
                }
                if (!ListenerUtil.mutListener.listen(7081)) {
                    decButton.setVisibility(View.VISIBLE);
                }
            }
        }
    }

    @Override
    public void setExpand(boolean state) {
        final boolean show = (ListenerUtil.mutListener.listen(7086) ? ((ListenerUtil.mutListener.listen(7085) ? (state || isVisible()) : (state && isVisible())) || evaluationResult != null) : ((ListenerUtil.mutListener.listen(7085) ? (state || isVisible()) : (state && isVisible())) && evaluationResult != null));
        if (!ListenerUtil.mutListener.listen(7087)) {
            showEvaluatorRow(show);
        }
    }

    @Override
    public String getPreferenceSummary() {
        MeasurementViewSettings settings = getSettings();
        Resources res = getResources();
        final String separator = ", ";
        String summary = "";
        if (!ListenerUtil.mutListener.listen(7090)) {
            if ((ListenerUtil.mutListener.listen(7088) ? (supportsConversion() || settings.isPercentageEnabled()) : (supportsConversion() && settings.isPercentageEnabled()))) {
                if (!ListenerUtil.mutListener.listen(7089)) {
                    summary += res.getString(R.string.label_percent) + separator;
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(7093)) {
            if ((ListenerUtil.mutListener.listen(7091) ? (isEstimationSupported() || settings.isEstimationEnabled()) : (isEstimationSupported() && settings.isEstimationEnabled()))) {
                if (!ListenerUtil.mutListener.listen(7092)) {
                    summary += res.getString(R.string.label_estimated) + separator;
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(7098)) {
            if (!summary.isEmpty()) {
                return summary.substring(0, (ListenerUtil.mutListener.listen(7097) ? (summary.length() % separator.length()) : (ListenerUtil.mutListener.listen(7096) ? (summary.length() / separator.length()) : (ListenerUtil.mutListener.listen(7095) ? (summary.length() * separator.length()) : (ListenerUtil.mutListener.listen(7094) ? (summary.length() + separator.length()) : (summary.length() - separator.length()))))));
            }
        }
        return "";
    }

    @Override
    public boolean hasExtraPreferences() {
        return true;
    }

    private class ListPreferenceWithNeutralButton extends ListPreference {

        ListPreferenceWithNeutralButton(Context context) {
            super(context);
            if (!ListenerUtil.mutListener.listen(7099)) {
                setWidgetLayoutResource(R.layout.preference_info);
            }
        }

        @Override
        public void onBindViewHolder(PreferenceViewHolder holder) {
            if (!ListenerUtil.mutListener.listen(7100)) {
                super.onBindViewHolder(holder);
            }
            ImageView helpView = (ImageView) holder.findViewById(R.id.helpView);
            if (!ListenerUtil.mutListener.listen(7102)) {
                helpView.setOnClickListener(new OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        if (!ListenerUtil.mutListener.listen(7101)) {
                            getContext().startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/oliexdev/openScale/wiki/Body-metric-estimations")));
                        }
                    }
                });
            }
        }
    }

    @Override
    public void prepareExtraPreferencesScreen(PreferenceScreen screen) {
        MeasurementViewSettings settings = getSettings();
        CheckBoxPreference rightAxis = new CheckBoxPreference(screen.getContext());
        if (!ListenerUtil.mutListener.listen(7103)) {
            rightAxis.setKey(settings.getOnRightAxisKey());
        }
        if (!ListenerUtil.mutListener.listen(7104)) {
            rightAxis.setTitle(R.string.label_is_on_right_axis);
        }
        if (!ListenerUtil.mutListener.listen(7105)) {
            rightAxis.setPersistent(true);
        }
        if (!ListenerUtil.mutListener.listen(7106)) {
            rightAxis.setDefaultValue(settings.isOnRightAxis());
        }
        if (!ListenerUtil.mutListener.listen(7107)) {
            screen.addPreference(rightAxis);
        }
        if (!ListenerUtil.mutListener.listen(7113)) {
            if (supportsConversion()) {
                SwitchPreference percentage = new SwitchPreference(screen.getContext());
                if (!ListenerUtil.mutListener.listen(7108)) {
                    percentage.setKey(settings.getPercentageEnabledKey());
                }
                if (!ListenerUtil.mutListener.listen(7109)) {
                    percentage.setTitle(R.string.label_measurement_in_percent);
                }
                if (!ListenerUtil.mutListener.listen(7110)) {
                    percentage.setPersistent(true);
                }
                if (!ListenerUtil.mutListener.listen(7111)) {
                    percentage.setDefaultValue(settings.isPercentageEnabled());
                }
                if (!ListenerUtil.mutListener.listen(7112)) {
                    screen.addPreference(percentage);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(7140)) {
            if (isEstimationSupported()) {
                final CheckBoxPreference estimate = new CheckBoxPreference(screen.getContext());
                if (!ListenerUtil.mutListener.listen(7114)) {
                    estimate.setKey(settings.getEstimationEnabledKey());
                }
                if (!ListenerUtil.mutListener.listen(7115)) {
                    estimate.setTitle(R.string.label_estimate_measurement);
                }
                if (!ListenerUtil.mutListener.listen(7116)) {
                    estimate.setSummary(R.string.label_estimate_measurement_summary);
                }
                if (!ListenerUtil.mutListener.listen(7117)) {
                    estimate.setPersistent(true);
                }
                if (!ListenerUtil.mutListener.listen(7118)) {
                    estimate.setDefaultValue(settings.isEstimationEnabled());
                }
                if (!ListenerUtil.mutListener.listen(7119)) {
                    screen.addPreference(estimate);
                }
                final ListPreference formula = new ListPreferenceWithNeutralButton(screen.getContext());
                if (!ListenerUtil.mutListener.listen(7120)) {
                    formula.setKey(settings.getEstimationFormulaKey());
                }
                if (!ListenerUtil.mutListener.listen(7121)) {
                    formula.setTitle(R.string.label_estimation_formula);
                }
                if (!ListenerUtil.mutListener.listen(7122)) {
                    formula.setPersistent(true);
                }
                if (!ListenerUtil.mutListener.listen(7123)) {
                    formula.setDefaultValue(settings.getEstimationFormula());
                }
                if (!ListenerUtil.mutListener.listen(7124)) {
                    prepareEstimationFormulaPreference(formula);
                }
                if (!ListenerUtil.mutListener.listen(7125)) {
                    formula.setEnabled(estimate.isChecked());
                }
                if (!ListenerUtil.mutListener.listen(7126)) {
                    formula.setSummary(formula.getEntries()[formula.findIndexOfValue(settings.getEstimationFormula())]);
                }
                if (!ListenerUtil.mutListener.listen(7134)) {
                    formula.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {

                        @Override
                        public boolean onPreferenceChange(Preference preference, Object newValue) {
                            ListPreference list = (ListPreference) preference;
                            int idx = list.findIndexOfValue((String) newValue);
                            if (!ListenerUtil.mutListener.listen(7132)) {
                                if ((ListenerUtil.mutListener.listen(7131) ? (idx >= -1) : (ListenerUtil.mutListener.listen(7130) ? (idx <= -1) : (ListenerUtil.mutListener.listen(7129) ? (idx > -1) : (ListenerUtil.mutListener.listen(7128) ? (idx < -1) : (ListenerUtil.mutListener.listen(7127) ? (idx != -1) : (idx == -1))))))) {
                                    return false;
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(7133)) {
                                preference.setSummary(list.getEntries()[idx]);
                            }
                            return true;
                        }
                    });
                }
                if (!ListenerUtil.mutListener.listen(7138)) {
                    estimate.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {

                        @Override
                        public boolean onPreferenceChange(Preference preference, Object newValue) {
                            if (!ListenerUtil.mutListener.listen(7137)) {
                                if ((Boolean) newValue == true) {
                                    if (!ListenerUtil.mutListener.listen(7136)) {
                                        formula.setEnabled(true);
                                    }
                                } else {
                                    if (!ListenerUtil.mutListener.listen(7135)) {
                                        formula.setEnabled(false);
                                    }
                                }
                            }
                            return true;
                        }
                    });
                }
                if (!ListenerUtil.mutListener.listen(7139)) {
                    screen.addPreference(formula);
                }
            }
        }
    }

    private float validateAndGetInput(View view) {
        EditText editText = view.findViewById(R.id.float_input);
        String text = editText.getText().toString();
        float newValue = -1;
        if (!ListenerUtil.mutListener.listen(7142)) {
            if (text.isEmpty()) {
                if (!ListenerUtil.mutListener.listen(7141)) {
                    editText.setError(getResources().getString(R.string.error_value_required));
                }
                return newValue;
            }
        }
        try {
            if (!ListenerUtil.mutListener.listen(7144)) {
                newValue = Float.valueOf(text.replace(',', '.'));
            }
        } catch (NumberFormatException ex) {
            if (!ListenerUtil.mutListener.listen(7143)) {
                newValue = -1;
            }
        }
        if (!ListenerUtil.mutListener.listen(7158)) {
            if ((ListenerUtil.mutListener.listen(7155) ? ((ListenerUtil.mutListener.listen(7149) ? (newValue >= 0) : (ListenerUtil.mutListener.listen(7148) ? (newValue <= 0) : (ListenerUtil.mutListener.listen(7147) ? (newValue > 0) : (ListenerUtil.mutListener.listen(7146) ? (newValue != 0) : (ListenerUtil.mutListener.listen(7145) ? (newValue == 0) : (newValue < 0)))))) && (ListenerUtil.mutListener.listen(7154) ? (newValue >= getMaxValue()) : (ListenerUtil.mutListener.listen(7153) ? (newValue <= getMaxValue()) : (ListenerUtil.mutListener.listen(7152) ? (newValue < getMaxValue()) : (ListenerUtil.mutListener.listen(7151) ? (newValue != getMaxValue()) : (ListenerUtil.mutListener.listen(7150) ? (newValue == getMaxValue()) : (newValue > getMaxValue()))))))) : ((ListenerUtil.mutListener.listen(7149) ? (newValue >= 0) : (ListenerUtil.mutListener.listen(7148) ? (newValue <= 0) : (ListenerUtil.mutListener.listen(7147) ? (newValue > 0) : (ListenerUtil.mutListener.listen(7146) ? (newValue != 0) : (ListenerUtil.mutListener.listen(7145) ? (newValue == 0) : (newValue < 0)))))) || (ListenerUtil.mutListener.listen(7154) ? (newValue >= getMaxValue()) : (ListenerUtil.mutListener.listen(7153) ? (newValue <= getMaxValue()) : (ListenerUtil.mutListener.listen(7152) ? (newValue < getMaxValue()) : (ListenerUtil.mutListener.listen(7151) ? (newValue != getMaxValue()) : (ListenerUtil.mutListener.listen(7150) ? (newValue == getMaxValue()) : (newValue > getMaxValue()))))))))) {
                if (!ListenerUtil.mutListener.listen(7156)) {
                    editText.setError(getResources().getString(R.string.error_value_range));
                }
                if (!ListenerUtil.mutListener.listen(7157)) {
                    newValue = -1;
                }
            }
        }
        return newValue;
    }

    @Override
    protected View getInputView() {
        final LinearLayout view = (LinearLayout) LayoutInflater.from(getContext()).inflate(R.layout.float_input_view, null);
        final EditText input = view.findViewById(R.id.float_input);
        if (!ListenerUtil.mutListener.listen(7159)) {
            input.setText(formatValue(value));
        }
        final TextView unit = view.findViewById(R.id.float_input_unit);
        if (!ListenerUtil.mutListener.listen(7160)) {
            unit.setText(getUnit());
        }
        if (!ListenerUtil.mutListener.listen(7168)) {
            if ((ListenerUtil.mutListener.listen(7165) ? (getDecimalPlaces() >= 0) : (ListenerUtil.mutListener.listen(7164) ? (getDecimalPlaces() <= 0) : (ListenerUtil.mutListener.listen(7163) ? (getDecimalPlaces() > 0) : (ListenerUtil.mutListener.listen(7162) ? (getDecimalPlaces() < 0) : (ListenerUtil.mutListener.listen(7161) ? (getDecimalPlaces() != 0) : (getDecimalPlaces() == 0))))))) {
                if (!ListenerUtil.mutListener.listen(7167)) {
                    INC_DEC_DELTA = 10.0f;
                }
            } else {
                if (!ListenerUtil.mutListener.listen(7166)) {
                    INC_DEC_DELTA = 0.1f;
                }
            }
        }
        View.OnClickListener onClickListener = new View.OnClickListener() {

            @Override
            public void onClick(View button) {
                float newValue = validateAndGetInput(view);
                if (!ListenerUtil.mutListener.listen(7174)) {
                    if ((ListenerUtil.mutListener.listen(7173) ? (newValue >= 0) : (ListenerUtil.mutListener.listen(7172) ? (newValue <= 0) : (ListenerUtil.mutListener.listen(7171) ? (newValue > 0) : (ListenerUtil.mutListener.listen(7170) ? (newValue != 0) : (ListenerUtil.mutListener.listen(7169) ? (newValue == 0) : (newValue < 0))))))) {
                        return;
                    }
                }
                if (!ListenerUtil.mutListener.listen(7177)) {
                    if (button.getId() == R.id.btn_inc) {
                        if (!ListenerUtil.mutListener.listen(7176)) {
                            newValue += INC_DEC_DELTA;
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(7175)) {
                            newValue -= INC_DEC_DELTA;
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(7178)) {
                    input.setText(formatValue(clampValue(newValue)));
                }
                if (!ListenerUtil.mutListener.listen(7179)) {
                    input.selectAll();
                }
            }
        };
        RepeatListener repeatListener = new RepeatListener(400, 100, onClickListener);
        final Button inc = view.findViewById(R.id.btn_inc);
        if (!ListenerUtil.mutListener.listen(7180)) {
            inc.setText("\u25b2 +" + formatValue(INC_DEC_DELTA));
        }
        if (!ListenerUtil.mutListener.listen(7181)) {
            inc.setOnClickListener(onClickListener);
        }
        if (!ListenerUtil.mutListener.listen(7182)) {
            inc.setOnTouchListener(repeatListener);
        }
        final Button dec = view.findViewById(R.id.btn_dec);
        if (!ListenerUtil.mutListener.listen(7183)) {
            dec.setText("\u25bc -" + formatValue(INC_DEC_DELTA));
        }
        if (!ListenerUtil.mutListener.listen(7184)) {
            dec.setOnClickListener(onClickListener);
        }
        if (!ListenerUtil.mutListener.listen(7185)) {
            dec.setOnTouchListener(repeatListener);
        }
        return view;
    }

    @Override
    protected boolean validateAndSetInput(View view) {
        float newValue = validateAndGetInput(view);
        if (!ListenerUtil.mutListener.listen(7192)) {
            if ((ListenerUtil.mutListener.listen(7190) ? (newValue <= 0) : (ListenerUtil.mutListener.listen(7189) ? (newValue > 0) : (ListenerUtil.mutListener.listen(7188) ? (newValue < 0) : (ListenerUtil.mutListener.listen(7187) ? (newValue != 0) : (ListenerUtil.mutListener.listen(7186) ? (newValue == 0) : (newValue >= 0))))))) {
                if (!ListenerUtil.mutListener.listen(7191)) {
                    setValue(newValue, previousValue, true);
                }
                return true;
            }
        }
        return false;
    }

    private class RepeatListener implements OnTouchListener {

        private final Handler handler = new Handler();

        private int initialInterval;

        private final int normalInterval;

        private final OnClickListener clickListener;

        private final Runnable handlerRunnable = new Runnable() {

            @Override
            public void run() {
                if (!ListenerUtil.mutListener.listen(7193)) {
                    handler.postDelayed(this, normalInterval);
                }
                if (!ListenerUtil.mutListener.listen(7194)) {
                    clickListener.onClick(downView);
                }
            }
        };

        private View downView;

        /**
         * RepeatListener cyclically runs a clickListener, emulating keyboard-like behaviour. First
         * click is fired immediately, next one after the initialInterval, and subsequent ones after the normalInterval.
         *
         * @param initialInterval The interval after first click event
         * @param normalInterval The interval after second and subsequent click events
         * @param clickListener The OnClickListener, that will be called periodically
         */
        public RepeatListener(int initialInterval, int normalInterval, OnClickListener clickListener) {
            if (!ListenerUtil.mutListener.listen(7195)) {
                if (clickListener == null) {
                    throw new IllegalArgumentException("null runnable");
                }
            }
            if (!ListenerUtil.mutListener.listen(7207)) {
                if ((ListenerUtil.mutListener.listen(7206) ? ((ListenerUtil.mutListener.listen(7200) ? (initialInterval >= 0) : (ListenerUtil.mutListener.listen(7199) ? (initialInterval <= 0) : (ListenerUtil.mutListener.listen(7198) ? (initialInterval > 0) : (ListenerUtil.mutListener.listen(7197) ? (initialInterval != 0) : (ListenerUtil.mutListener.listen(7196) ? (initialInterval == 0) : (initialInterval < 0)))))) && (ListenerUtil.mutListener.listen(7205) ? (normalInterval >= 0) : (ListenerUtil.mutListener.listen(7204) ? (normalInterval <= 0) : (ListenerUtil.mutListener.listen(7203) ? (normalInterval > 0) : (ListenerUtil.mutListener.listen(7202) ? (normalInterval != 0) : (ListenerUtil.mutListener.listen(7201) ? (normalInterval == 0) : (normalInterval < 0))))))) : ((ListenerUtil.mutListener.listen(7200) ? (initialInterval >= 0) : (ListenerUtil.mutListener.listen(7199) ? (initialInterval <= 0) : (ListenerUtil.mutListener.listen(7198) ? (initialInterval > 0) : (ListenerUtil.mutListener.listen(7197) ? (initialInterval != 0) : (ListenerUtil.mutListener.listen(7196) ? (initialInterval == 0) : (initialInterval < 0)))))) || (ListenerUtil.mutListener.listen(7205) ? (normalInterval >= 0) : (ListenerUtil.mutListener.listen(7204) ? (normalInterval <= 0) : (ListenerUtil.mutListener.listen(7203) ? (normalInterval > 0) : (ListenerUtil.mutListener.listen(7202) ? (normalInterval != 0) : (ListenerUtil.mutListener.listen(7201) ? (normalInterval == 0) : (normalInterval < 0))))))))) {
                    throw new IllegalArgumentException("negative interval");
                }
            }
            if (!ListenerUtil.mutListener.listen(7208)) {
                this.initialInterval = initialInterval;
            }
            this.normalInterval = normalInterval;
            this.clickListener = clickListener;
        }

        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (!ListenerUtil.mutListener.listen(7217)) {
                switch(motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        if (!ListenerUtil.mutListener.listen(7209)) {
                            handler.removeCallbacks(handlerRunnable);
                        }
                        if (!ListenerUtil.mutListener.listen(7210)) {
                            handler.postDelayed(handlerRunnable, initialInterval);
                        }
                        if (!ListenerUtil.mutListener.listen(7211)) {
                            downView = view;
                        }
                        if (!ListenerUtil.mutListener.listen(7212)) {
                            downView.setPressed(true);
                        }
                        if (!ListenerUtil.mutListener.listen(7213)) {
                            clickListener.onClick(view);
                        }
                        return true;
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_CANCEL:
                        if (!ListenerUtil.mutListener.listen(7214)) {
                            handler.removeCallbacks(handlerRunnable);
                        }
                        if (!ListenerUtil.mutListener.listen(7215)) {
                            downView.setPressed(false);
                        }
                        if (!ListenerUtil.mutListener.listen(7216)) {
                            downView = null;
                        }
                        return true;
                }
            }
            return false;
        }
    }
}
