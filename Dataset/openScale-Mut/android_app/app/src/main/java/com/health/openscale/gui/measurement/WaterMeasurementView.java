/* Copyright (C) 2014  olie.xdev <olie.xdev@googlemail.com>
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
import android.graphics.Color;
import androidx.preference.ListPreference;
import com.health.openscale.R;
import com.health.openscale.core.bodymetric.EstimatedWaterMetric;
import com.health.openscale.core.datatypes.ScaleMeasurement;
import com.health.openscale.core.evaluation.EvaluationResult;
import com.health.openscale.core.evaluation.EvaluationSheet;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class WaterMeasurementView extends FloatMeasurementView {

    // Don't change key value, it may be stored persistent in preferences
    public static final String KEY = "water";

    public WaterMeasurementView(Context context) {
        super(context, R.string.label_water, R.drawable.ic_water);
    }

    @Override
    public String getKey() {
        return KEY;
    }

    @Override
    protected boolean supportsPercentageToAbsoluteWeightConversion() {
        return true;
    }

    @Override
    protected float getMeasurementValue(ScaleMeasurement measurement) {
        return measurement.getWater();
    }

    @Override
    protected void setMeasurementValue(float value, ScaleMeasurement measurement) {
        if (!ListenerUtil.mutListener.listen(7964)) {
            measurement.setWater(value);
        }
    }

    @Override
    public String getUnit() {
        if (!ListenerUtil.mutListener.listen(7965)) {
            if (shouldConvertPercentageToAbsoluteWeight()) {
                return getScaleUser().getScaleUnit().toString();
            }
        }
        return "%";
    }

    @Override
    protected float getMaxValue() {
        return maybeConvertPercentageToAbsoluteWeight(80);
    }

    @Override
    public int getColor() {
        return Color.parseColor("#33B5E5");
    }

    @Override
    protected boolean isEstimationSupported() {
        return true;
    }

    @Override
    protected void prepareEstimationFormulaPreference(ListPreference preference) {
        String[] entries = new String[EstimatedWaterMetric.FORMULA.values().length];
        String[] values = new String[entries.length];
        int idx = 0;
        if (!ListenerUtil.mutListener.listen(7969)) {
            {
                long _loopCounter94 = 0;
                for (EstimatedWaterMetric.FORMULA formula : EstimatedWaterMetric.FORMULA.values()) {
                    ListenerUtil.loopListener.listen("_loopCounter94", ++_loopCounter94);
                    if (!ListenerUtil.mutListener.listen(7966)) {
                        entries[idx] = EstimatedWaterMetric.getEstimatedMetric(formula).getName();
                    }
                    if (!ListenerUtil.mutListener.listen(7967)) {
                        values[idx] = formula.name();
                    }
                    if (!ListenerUtil.mutListener.listen(7968)) {
                        ++idx;
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(7970)) {
            preference.setEntries(entries);
        }
        if (!ListenerUtil.mutListener.listen(7971)) {
            preference.setEntryValues(values);
        }
    }

    @Override
    protected EvaluationResult evaluateSheet(EvaluationSheet evalSheet, float value) {
        return evalSheet.evaluateBodyWater(value);
    }
}
