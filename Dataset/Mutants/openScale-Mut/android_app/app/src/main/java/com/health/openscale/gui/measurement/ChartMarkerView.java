/* Copyright (C) 2018  olie.xdev <olie.xdev@googlemail.com>
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

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.Layout;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.AlignmentSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.widget.TextView;
import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.utils.MPPointF;
import com.health.openscale.R;
import com.health.openscale.core.datatypes.ScaleMeasurement;
import com.health.openscale.gui.utils.ColorUtil;
import java.text.DateFormat;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

@SuppressLint("ViewConstructor")
public class ChartMarkerView extends MarkerView {

    private final TextView markerTextField;

    public ChartMarkerView(Context context, int layoutResource) {
        super(context, layoutResource);
        markerTextField = findViewById(R.id.markerTextField);
    }

    @Override
    public void refreshContent(Entry e, Highlight highlight) {
        Object[] extraData = (Object[]) e.getData();
        ScaleMeasurement measurement = (ScaleMeasurement) extraData[0];
        ScaleMeasurement prevMeasurement = (ScaleMeasurement) extraData[1];
        FloatMeasurementView measurementView = (FloatMeasurementView) extraData[2];
        SpannableStringBuilder markerText = new SpannableStringBuilder();
        if (!ListenerUtil.mutListener.listen(6482)) {
            if (measurement != null) {
                if (!ListenerUtil.mutListener.listen(6476)) {
                    measurementView.loadFrom(measurement, prevMeasurement);
                }
                DateFormat dateFormat = DateFormat.getDateInstance();
                if (!ListenerUtil.mutListener.listen(6477)) {
                    markerText.append(dateFormat.format(measurement.getDateTime()));
                }
                if (!ListenerUtil.mutListener.listen(6478)) {
                    markerText.setSpan(new RelativeSizeSpan(0.8f), 0, markerText.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                }
                if (!ListenerUtil.mutListener.listen(6479)) {
                    markerText.append("\n");
                }
                if (!ListenerUtil.mutListener.listen(6481)) {
                    if (measurement.isAverageValue()) {
                        if (!ListenerUtil.mutListener.listen(6480)) {
                            markerText.append(getContext().getString(R.string.label_trend) + " ");
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(6483)) {
            markerText.append(measurementView.getValueAsString(true));
        }
        if (!ListenerUtil.mutListener.listen(6493)) {
            if (prevMeasurement != null) {
                if (!ListenerUtil.mutListener.listen(6484)) {
                    markerText.append("\n");
                }
                int textPosAfterSymbol = markerText.length() + 1;
                if (!ListenerUtil.mutListener.listen(6485)) {
                    measurementView.appendDiffValue(markerText, false);
                }
                if (!ListenerUtil.mutListener.listen(6492)) {
                    // set color diff value to text color
                    if ((ListenerUtil.mutListener.listen(6490) ? (markerText.length() >= textPosAfterSymbol) : (ListenerUtil.mutListener.listen(6489) ? (markerText.length() <= textPosAfterSymbol) : (ListenerUtil.mutListener.listen(6488) ? (markerText.length() < textPosAfterSymbol) : (ListenerUtil.mutListener.listen(6487) ? (markerText.length() != textPosAfterSymbol) : (ListenerUtil.mutListener.listen(6486) ? (markerText.length() == textPosAfterSymbol) : (markerText.length() > textPosAfterSymbol))))))) {
                        if (!ListenerUtil.mutListener.listen(6491)) {
                            markerText.setSpan(new ForegroundColorSpan(ColorUtil.COLOR_WHITE), textPosAfterSymbol, markerText.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(6494)) {
            markerText.setSpan(new AlignmentSpan.Standard(Layout.Alignment.ALIGN_CENTER), 0, markerText.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        if (!ListenerUtil.mutListener.listen(6495)) {
            markerTextField.setText(markerText);
        }
        if (!ListenerUtil.mutListener.listen(6496)) {
            super.refreshContent(e, highlight);
        }
    }

    @Override
    public MPPointF getOffset() {
        return new MPPointF(-((ListenerUtil.mutListener.listen(6500) ? (getWidth() % 2f) : (ListenerUtil.mutListener.listen(6499) ? (getWidth() * 2f) : (ListenerUtil.mutListener.listen(6498) ? (getWidth() - 2f) : (ListenerUtil.mutListener.listen(6497) ? (getWidth() + 2f) : (getWidth() / 2f)))))), (ListenerUtil.mutListener.listen(6504) ? (-getHeight() % 5f) : (ListenerUtil.mutListener.listen(6503) ? (-getHeight() / 5f) : (ListenerUtil.mutListener.listen(6502) ? (-getHeight() * 5f) : (ListenerUtil.mutListener.listen(6501) ? (-getHeight() + 5f) : (-getHeight() - 5f))))));
    }
}
