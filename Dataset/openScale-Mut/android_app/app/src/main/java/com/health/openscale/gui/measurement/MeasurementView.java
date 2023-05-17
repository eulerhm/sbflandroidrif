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

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Space;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.preference.PreferenceManager;
import androidx.preference.PreferenceScreen;
import com.health.openscale.R;
import com.health.openscale.core.OpenScale;
import com.health.openscale.core.datatypes.ScaleMeasurement;
import com.health.openscale.core.datatypes.ScaleUser;
import com.health.openscale.core.evaluation.EvaluationResult;
import com.health.openscale.gui.utils.ColorUtil;
import java.util.ArrayList;
import java.util.List;
import static com.health.openscale.gui.measurement.MeasurementView.MeasurementViewMode.ADD;
import static com.health.openscale.gui.measurement.MeasurementView.MeasurementViewMode.EDIT;
import static com.health.openscale.gui.measurement.MeasurementView.MeasurementViewMode.STATISTIC;
import static com.health.openscale.gui.measurement.MeasurementView.MeasurementViewMode.VIEW;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public abstract class MeasurementView extends TableLayout {

    public enum MeasurementViewMode {

        VIEW, EDIT, ADD, STATISTIC
    }

    public static final String PREF_MEASUREMENT_ORDER = "measurementOrder";

    private MeasurementViewSettings settings;

    private TableRow measurementRow;

    private ImageView iconView;

    private GradientDrawable iconViewBackground;

    private int iconId;

    private TextView nameView;

    private TextView valueView;

    private LinearLayout incDecLayout;

    private ImageView editModeView;

    private ImageView indicatorView;

    private TableRow evaluatorRow;

    private LinearGaugeView evaluatorView;

    private MeasurementViewUpdateListener updateListener = null;

    private MeasurementViewMode measurementMode = VIEW;

    private boolean updateViews = true;

    public MeasurementView(Context context, int textId, int iconId) {
        super(context);
        if (!ListenerUtil.mutListener.listen(7709)) {
            this.iconId = iconId;
        }
        if (!ListenerUtil.mutListener.listen(7710)) {
            initView(context);
        }
        if (!ListenerUtil.mutListener.listen(7711)) {
            nameView.setText(textId);
        }
    }

    public enum DateTimeOrder {

        FIRST, LAST, NONE
    }

    public static List<MeasurementView> getMeasurementList(Context context, DateTimeOrder dateTimeOrder) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        final List<MeasurementView> sorted = new ArrayList<>();
        if (!ListenerUtil.mutListener.listen(7714)) {
            if (dateTimeOrder == DateTimeOrder.FIRST) {
                if (!ListenerUtil.mutListener.listen(7712)) {
                    sorted.add(new DateMeasurementView(context));
                }
                if (!ListenerUtil.mutListener.listen(7713)) {
                    sorted.add(new TimeMeasurementView(context));
                }
            }
        }
        {
            final List<MeasurementView> unsorted = new ArrayList<>();
            if (!ListenerUtil.mutListener.listen(7715)) {
                unsorted.add(new WeightMeasurementView(context));
            }
            if (!ListenerUtil.mutListener.listen(7716)) {
                unsorted.add(new BMIMeasurementView(context));
            }
            if (!ListenerUtil.mutListener.listen(7717)) {
                unsorted.add(new WaterMeasurementView(context));
            }
            if (!ListenerUtil.mutListener.listen(7718)) {
                unsorted.add(new MuscleMeasurementView(context));
            }
            if (!ListenerUtil.mutListener.listen(7719)) {
                unsorted.add(new LBMMeasurementView(context));
            }
            if (!ListenerUtil.mutListener.listen(7720)) {
                unsorted.add(new FatMeasurementView(context));
            }
            if (!ListenerUtil.mutListener.listen(7721)) {
                unsorted.add(new BoneMeasurementView(context));
            }
            if (!ListenerUtil.mutListener.listen(7722)) {
                unsorted.add(new VisceralFatMeasurementView(context));
            }
            if (!ListenerUtil.mutListener.listen(7723)) {
                unsorted.add(new WaistMeasurementView(context));
            }
            if (!ListenerUtil.mutListener.listen(7724)) {
                unsorted.add(new WHtRMeasurementView(context));
            }
            if (!ListenerUtil.mutListener.listen(7725)) {
                unsorted.add(new HipMeasurementView(context));
            }
            if (!ListenerUtil.mutListener.listen(7726)) {
                unsorted.add(new WHRMeasurementView(context));
            }
            if (!ListenerUtil.mutListener.listen(7727)) {
                unsorted.add(new ChestMeasurementView(context));
            }
            if (!ListenerUtil.mutListener.listen(7728)) {
                unsorted.add(new ThighMeasurementView(context));
            }
            if (!ListenerUtil.mutListener.listen(7729)) {
                unsorted.add(new BicepsMeasurementView(context));
            }
            if (!ListenerUtil.mutListener.listen(7730)) {
                unsorted.add(new NeckMeasurementView(context));
            }
            if (!ListenerUtil.mutListener.listen(7731)) {
                unsorted.add(new FatCaliperMeasurementView(context));
            }
            if (!ListenerUtil.mutListener.listen(7732)) {
                unsorted.add(new Caliper1MeasurementView(context));
            }
            if (!ListenerUtil.mutListener.listen(7733)) {
                unsorted.add(new Caliper2MeasurementView(context));
            }
            if (!ListenerUtil.mutListener.listen(7734)) {
                unsorted.add(new Caliper3MeasurementView(context));
            }
            if (!ListenerUtil.mutListener.listen(7735)) {
                unsorted.add(new BMRMeasurementView(context));
            }
            if (!ListenerUtil.mutListener.listen(7736)) {
                unsorted.add(new TDEEMeasurementView(context));
            }
            if (!ListenerUtil.mutListener.listen(7737)) {
                unsorted.add(new CaloriesMeasurementView(context));
            }
            if (!ListenerUtil.mutListener.listen(7738)) {
                unsorted.add(new CommentMeasurementView(context));
            }
            if (!ListenerUtil.mutListener.listen(7739)) {
                unsorted.add(new UserMeasurementView(context));
            }
            // Get sort order
            final String[] sortOrder = TextUtils.split(prefs.getString(PREF_MEASUREMENT_ORDER, ""), ",");
            if (!ListenerUtil.mutListener.listen(7744)) {
                {
                    long _loopCounter89 = 0;
                    // Move views from unsorted to sorted in the correct order
                    for (String key : sortOrder) {
                        ListenerUtil.loopListener.listen("_loopCounter89", ++_loopCounter89);
                        if (!ListenerUtil.mutListener.listen(7743)) {
                            {
                                long _loopCounter88 = 0;
                                for (MeasurementView measurement : unsorted) {
                                    ListenerUtil.loopListener.listen("_loopCounter88", ++_loopCounter88);
                                    if (!ListenerUtil.mutListener.listen(7742)) {
                                        if (key.equals(measurement.getKey())) {
                                            if (!ListenerUtil.mutListener.listen(7740)) {
                                                sorted.add(measurement);
                                            }
                                            if (!ListenerUtil.mutListener.listen(7741)) {
                                                unsorted.remove(measurement);
                                            }
                                            break;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(7745)) {
                // Any new views end up at the end
                sorted.addAll(unsorted);
            }
        }
        if (!ListenerUtil.mutListener.listen(7748)) {
            if (dateTimeOrder == DateTimeOrder.LAST) {
                if (!ListenerUtil.mutListener.listen(7746)) {
                    sorted.add(new DateMeasurementView(context));
                }
                if (!ListenerUtil.mutListener.listen(7747)) {
                    sorted.add(new TimeMeasurementView(context));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(7750)) {
            {
                long _loopCounter90 = 0;
                for (MeasurementView measurement : sorted) {
                    ListenerUtil.loopListener.listen("_loopCounter90", ++_loopCounter90);
                    if (!ListenerUtil.mutListener.listen(7749)) {
                        measurement.setVisible(measurement.getSettings().isEnabled());
                    }
                }
            }
        }
        return sorted;
    }

    public static void saveMeasurementViewsOrder(Context context, List<MeasurementView> measurementViews) {
        ArrayList<String> order = new ArrayList<>();
        if (!ListenerUtil.mutListener.listen(7752)) {
            {
                long _loopCounter91 = 0;
                for (MeasurementView measurement : measurementViews) {
                    ListenerUtil.loopListener.listen("_loopCounter91", ++_loopCounter91);
                    if (!ListenerUtil.mutListener.listen(7751)) {
                        order.add(measurement.getKey());
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(7753)) {
            PreferenceManager.getDefaultSharedPreferences(context).edit().putString(PREF_MEASUREMENT_ORDER, TextUtils.join(",", order)).apply();
        }
    }

    private void initView(Context context) {
        if (!ListenerUtil.mutListener.listen(7754)) {
            measurementRow = new TableRow(context);
        }
        if (!ListenerUtil.mutListener.listen(7755)) {
            iconView = new ImageView(context);
        }
        if (!ListenerUtil.mutListener.listen(7756)) {
            iconViewBackground = new GradientDrawable();
        }
        if (!ListenerUtil.mutListener.listen(7757)) {
            nameView = new TextView(context);
        }
        if (!ListenerUtil.mutListener.listen(7758)) {
            valueView = new TextView(context);
        }
        if (!ListenerUtil.mutListener.listen(7759)) {
            editModeView = new ImageView(context);
        }
        if (!ListenerUtil.mutListener.listen(7760)) {
            indicatorView = new ImageView(context);
        }
        if (!ListenerUtil.mutListener.listen(7761)) {
            evaluatorRow = new TableRow(context);
        }
        if (!ListenerUtil.mutListener.listen(7762)) {
            evaluatorView = new LinearGaugeView(context);
        }
        if (!ListenerUtil.mutListener.listen(7763)) {
            incDecLayout = new LinearLayout(context);
        }
        if (!ListenerUtil.mutListener.listen(7764)) {
            measurementRow.setLayoutParams(new TableRow.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT, 1.0f));
        }
        if (!ListenerUtil.mutListener.listen(7765)) {
            measurementRow.setGravity(Gravity.CENTER);
        }
        if (!ListenerUtil.mutListener.listen(7766)) {
            measurementRow.addView(iconView);
        }
        if (!ListenerUtil.mutListener.listen(7767)) {
            measurementRow.addView(nameView);
        }
        if (!ListenerUtil.mutListener.listen(7768)) {
            measurementRow.addView(valueView);
        }
        if (!ListenerUtil.mutListener.listen(7769)) {
            measurementRow.addView(incDecLayout);
        }
        if (!ListenerUtil.mutListener.listen(7770)) {
            measurementRow.addView(editModeView);
        }
        if (!ListenerUtil.mutListener.listen(7771)) {
            measurementRow.addView(indicatorView);
        }
        if (!ListenerUtil.mutListener.listen(7772)) {
            addView(measurementRow);
        }
        if (!ListenerUtil.mutListener.listen(7773)) {
            addView(evaluatorRow);
        }
        if (!ListenerUtil.mutListener.listen(7774)) {
            iconViewBackground.setColor(ColorUtil.COLOR_GRAY);
        }
        if (!ListenerUtil.mutListener.listen(7775)) {
            iconViewBackground.setShape(GradientDrawable.OVAL);
        }
        if (!ListenerUtil.mutListener.listen(7776)) {
            iconViewBackground.setGradientRadius(iconView.getWidth());
        }
        if (!ListenerUtil.mutListener.listen(7777)) {
            iconView.setImageResource(iconId);
        }
        if (!ListenerUtil.mutListener.listen(7778)) {
            iconView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        }
        if (!ListenerUtil.mutListener.listen(7779)) {
            iconView.setPadding(25, 25, 25, 25);
        }
        if (!ListenerUtil.mutListener.listen(7780)) {
            iconView.setColorFilter(ColorUtil.COLOR_BLACK);
        }
        if (!ListenerUtil.mutListener.listen(7781)) {
            iconView.setBackground(iconViewBackground);
        }
        TableRow.LayoutParams iconLayout = new TableRow.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        if (!ListenerUtil.mutListener.listen(7782)) {
            iconLayout.setMargins(10, 5, 10, 5);
        }
        if (!ListenerUtil.mutListener.listen(7783)) {
            iconView.setLayoutParams(iconLayout);
        }
        if (!ListenerUtil.mutListener.listen(7784)) {
            nameView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
        }
        if (!ListenerUtil.mutListener.listen(7785)) {
            nameView.setLines(2);
        }
        if (!ListenerUtil.mutListener.listen(7786)) {
            nameView.setLayoutParams(new TableRow.LayoutParams(0, LayoutParams.WRAP_CONTENT, 0.55f));
        }
        if (!ListenerUtil.mutListener.listen(7787)) {
            valueView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
        }
        if (!ListenerUtil.mutListener.listen(7788)) {
            valueView.setGravity(Gravity.RIGHT | Gravity.CENTER);
        }
        if (!ListenerUtil.mutListener.listen(7789)) {
            valueView.setPadding(0, 0, 20, 0);
        }
        if (!ListenerUtil.mutListener.listen(7790)) {
            valueView.setLayoutParams(new TableRow.LayoutParams(0, LayoutParams.WRAP_CONTENT, 0.29f));
        }
        if (!ListenerUtil.mutListener.listen(7791)) {
            incDecLayout.setOrientation(VERTICAL);
        }
        if (!ListenerUtil.mutListener.listen(7792)) {
            incDecLayout.setVisibility(View.GONE);
        }
        if (!ListenerUtil.mutListener.listen(7793)) {
            incDecLayout.setPadding(0, 0, 0, 0);
        }
        if (!ListenerUtil.mutListener.listen(7794)) {
            incDecLayout.setLayoutParams(new TableRow.LayoutParams(0, LayoutParams.MATCH_PARENT, 0.05f));
        }
        if (!ListenerUtil.mutListener.listen(7795)) {
            editModeView.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_editable));
        }
        if (!ListenerUtil.mutListener.listen(7796)) {
            editModeView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        }
        if (!ListenerUtil.mutListener.listen(7797)) {
            editModeView.setVisibility(View.GONE);
        }
        if (!ListenerUtil.mutListener.listen(7798)) {
            editModeView.setColorFilter(getForegroundColor());
        }
        if (!ListenerUtil.mutListener.listen(7799)) {
            indicatorView.setLayoutParams(new TableRow.LayoutParams(0, LayoutParams.MATCH_PARENT, 0.01f));
        }
        if (!ListenerUtil.mutListener.listen(7800)) {
            indicatorView.setBackgroundColor(Color.GRAY);
        }
        if (!ListenerUtil.mutListener.listen(7801)) {
            evaluatorRow.setLayoutParams(new TableRow.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT, 1.0f));
        }
        if (!ListenerUtil.mutListener.listen(7802)) {
            evaluatorRow.addView(new Space(context));
        }
        if (!ListenerUtil.mutListener.listen(7803)) {
            evaluatorRow.addView(evaluatorView);
        }
        Space spaceAfterEvaluatorView = new Space(context);
        if (!ListenerUtil.mutListener.listen(7804)) {
            evaluatorRow.addView(spaceAfterEvaluatorView);
        }
        if (!ListenerUtil.mutListener.listen(7805)) {
            evaluatorRow.setVisibility(View.GONE);
        }
        if (!ListenerUtil.mutListener.listen(7806)) {
            evaluatorView.setLayoutParams(new TableRow.LayoutParams(0, LayoutParams.WRAP_CONTENT, 0.99f));
        }
        if (!ListenerUtil.mutListener.listen(7807)) {
            spaceAfterEvaluatorView.setLayoutParams(new TableRow.LayoutParams(0, LayoutParams.WRAP_CONTENT, 0.01f));
        }
        if (!ListenerUtil.mutListener.listen(7808)) {
            setOnClickListener(new onClickListenerEvaluation());
        }
    }

    protected LinearLayout getIncDecLayout() {
        return incDecLayout;
    }

    public void setOnUpdateListener(MeasurementViewUpdateListener listener) {
        if (!ListenerUtil.mutListener.listen(7809)) {
            updateListener = listener;
        }
    }

    public void setUpdateViews(boolean update) {
        if (!ListenerUtil.mutListener.listen(7810)) {
            updateViews = update;
        }
    }

    protected boolean getUpdateViews() {
        return updateViews;
    }

    public abstract String getKey();

    public MeasurementViewSettings getSettings() {
        if (!ListenerUtil.mutListener.listen(7812)) {
            if (settings == null) {
                if (!ListenerUtil.mutListener.listen(7811)) {
                    settings = new MeasurementViewSettings(PreferenceManager.getDefaultSharedPreferences(getContext()), getKey());
                }
            }
        }
        return settings;
    }

    public abstract void loadFrom(ScaleMeasurement measurement, ScaleMeasurement previousMeasurement);

    public abstract void saveTo(ScaleMeasurement measurement);

    public abstract void clearIn(ScaleMeasurement measurement);

    public abstract void restoreState(Bundle state);

    public abstract void saveState(Bundle state);

    public CharSequence getName() {
        return nameView.getText();
    }

    public abstract String getValueAsString(boolean withUnit);

    public void appendDiffValue(SpannableStringBuilder builder, boolean newLine) {
    }

    public Drawable getIcon() {
        return iconView.getDrawable();
    }

    public int getIconResource() {
        return iconId;
    }

    public void setBackgroundIconColor(int color) {
        if (!ListenerUtil.mutListener.listen(7813)) {
            iconViewBackground.setColor(color);
        }
    }

    protected boolean isEditable() {
        return true;
    }

    public void setEditMode(MeasurementViewMode mode) {
        if (!ListenerUtil.mutListener.listen(7814)) {
            measurementMode = mode;
        }
        if (!ListenerUtil.mutListener.listen(7815)) {
            nameView.setGravity(Gravity.LEFT | (mode == ADD ? Gravity.CENTER : Gravity.TOP));
        }
        if (!ListenerUtil.mutListener.listen(7816)) {
            valueView.setGravity(Gravity.CENTER | (mode == STATISTIC ? 0 : Gravity.RIGHT));
        }
        if (!ListenerUtil.mutListener.listen(7831)) {
            switch(mode) {
                case VIEW:
                    if (!ListenerUtil.mutListener.listen(7817)) {
                        indicatorView.setVisibility(View.VISIBLE);
                    }
                    if (!ListenerUtil.mutListener.listen(7818)) {
                        editModeView.setVisibility(View.GONE);
                    }
                    if (!ListenerUtil.mutListener.listen(7819)) {
                        incDecLayout.setVisibility(View.GONE);
                    }
                    if (!ListenerUtil.mutListener.listen(7820)) {
                        nameView.setVisibility(View.VISIBLE);
                    }
                    break;
                case EDIT:
                case ADD:
                    if (!ListenerUtil.mutListener.listen(7821)) {
                        indicatorView.setVisibility(View.GONE);
                    }
                    if (!ListenerUtil.mutListener.listen(7822)) {
                        editModeView.setVisibility(View.VISIBLE);
                    }
                    if (!ListenerUtil.mutListener.listen(7823)) {
                        incDecLayout.setVisibility(View.VISIBLE);
                    }
                    if (!ListenerUtil.mutListener.listen(7824)) {
                        nameView.setVisibility(View.VISIBLE);
                    }
                    if (!ListenerUtil.mutListener.listen(7826)) {
                        if (!isEditable()) {
                            if (!ListenerUtil.mutListener.listen(7825)) {
                                editModeView.setVisibility(View.INVISIBLE);
                            }
                        }
                    }
                    break;
                case STATISTIC:
                    if (!ListenerUtil.mutListener.listen(7827)) {
                        indicatorView.setVisibility(View.GONE);
                    }
                    if (!ListenerUtil.mutListener.listen(7828)) {
                        incDecLayout.setVisibility(View.GONE);
                    }
                    if (!ListenerUtil.mutListener.listen(7829)) {
                        editModeView.setVisibility(View.GONE);
                    }
                    if (!ListenerUtil.mutListener.listen(7830)) {
                        nameView.setVisibility(View.GONE);
                    }
                    break;
            }
        }
    }

    protected MeasurementViewMode getMeasurementMode() {
        return measurementMode;
    }

    protected void setValueView(String text, boolean callListener) {
        if (!ListenerUtil.mutListener.listen(7833)) {
            if (updateViews) {
                if (!ListenerUtil.mutListener.listen(7832)) {
                    valueView.setText(text);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(7836)) {
            if ((ListenerUtil.mutListener.listen(7834) ? (callListener || updateListener != null) : (callListener && updateListener != null))) {
                if (!ListenerUtil.mutListener.listen(7835)) {
                    updateListener.onMeasurementViewUpdate(this);
                }
            }
        }
    }

    protected void setNameView(CharSequence text) {
        if (!ListenerUtil.mutListener.listen(7838)) {
            if (updateViews) {
                if (!ListenerUtil.mutListener.listen(7837)) {
                    nameView.setText(text);
                }
            }
        }
    }

    public int getForegroundColor() {
        return ColorUtil.getTintColor(getContext());
    }

    public int getIndicatorColor() {
        ColorDrawable background = (ColorDrawable) indicatorView.getBackground();
        return background.getColor();
    }

    protected void showEvaluatorRow(boolean show) {
        if (!ListenerUtil.mutListener.listen(7841)) {
            if (show) {
                if (!ListenerUtil.mutListener.listen(7840)) {
                    evaluatorRow.setVisibility(View.VISIBLE);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(7839)) {
                    evaluatorRow.setVisibility(View.GONE);
                }
            }
        }
    }

    public void setExpand(boolean state) {
        if (!ListenerUtil.mutListener.listen(7842)) {
            showEvaluatorRow(false);
        }
    }

    public void setVisible(boolean isVisible) {
        if (!ListenerUtil.mutListener.listen(7845)) {
            if (isVisible) {
                if (!ListenerUtil.mutListener.listen(7844)) {
                    measurementRow.setVisibility(View.VISIBLE);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(7843)) {
                    measurementRow.setVisibility(View.GONE);
                }
            }
        }
    }

    public boolean isVisible() {
        if (!ListenerUtil.mutListener.listen(7846)) {
            if (measurementRow.getVisibility() == View.GONE) {
                return false;
            }
        }
        return true;
    }

    protected void setEvaluationView(EvaluationResult evalResult) {
        if (!ListenerUtil.mutListener.listen(7847)) {
            if (!updateViews) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(7850)) {
            if (evalResult == null) {
                if (!ListenerUtil.mutListener.listen(7848)) {
                    evaluatorView.setLimits(-1.0f, -1.0f);
                }
                if (!ListenerUtil.mutListener.listen(7849)) {
                    indicatorView.setBackgroundColor(Color.GRAY);
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(7851)) {
            evaluatorView.setLimits(evalResult.lowLimit, evalResult.highLimit);
        }
        if (!ListenerUtil.mutListener.listen(7852)) {
            evaluatorView.setValue(evalResult.value);
        }
        if (!ListenerUtil.mutListener.listen(7857)) {
            switch(evalResult.eval_state) {
                case LOW:
                    if (!ListenerUtil.mutListener.listen(7853)) {
                        indicatorView.setBackgroundColor(ColorUtil.COLOR_BLUE);
                    }
                    break;
                case NORMAL:
                    if (!ListenerUtil.mutListener.listen(7854)) {
                        indicatorView.setBackgroundColor(ColorUtil.COLOR_GREEN);
                    }
                    break;
                case HIGH:
                    if (!ListenerUtil.mutListener.listen(7855)) {
                        indicatorView.setBackgroundColor(ColorUtil.COLOR_RED);
                    }
                    break;
                case UNDEFINED:
                    if (!ListenerUtil.mutListener.listen(7856)) {
                        indicatorView.setBackgroundColor(Color.GRAY);
                    }
                    break;
            }
        }
    }

    protected ScaleUser getScaleUser() {
        OpenScale openScale = OpenScale.getInstance();
        return openScale.getSelectedScaleUser();
    }

    public String getPreferenceSummary() {
        return "";
    }

    public boolean hasExtraPreferences() {
        return false;
    }

    public void prepareExtraPreferencesScreen(PreferenceScreen screen) {
    }

    protected abstract View getInputView();

    protected abstract boolean validateAndSetInput(View view);

    private MeasurementView getNextView() {
        ViewGroup parent = (ViewGroup) getParent();
        if (!ListenerUtil.mutListener.listen(7865)) {
            {
                long _loopCounter92 = 0;
                for (int i = parent.indexOfChild(this) + 1; (ListenerUtil.mutListener.listen(7864) ? (i >= parent.getChildCount()) : (ListenerUtil.mutListener.listen(7863) ? (i <= parent.getChildCount()) : (ListenerUtil.mutListener.listen(7862) ? (i > parent.getChildCount()) : (ListenerUtil.mutListener.listen(7861) ? (i != parent.getChildCount()) : (ListenerUtil.mutListener.listen(7860) ? (i == parent.getChildCount()) : (i < parent.getChildCount())))))); ++i) {
                    ListenerUtil.loopListener.listen("_loopCounter92", ++_loopCounter92);
                    MeasurementView next = (MeasurementView) parent.getChildAt(i);
                    if (!ListenerUtil.mutListener.listen(7859)) {
                        if ((ListenerUtil.mutListener.listen(7858) ? (next.isVisible() || next.isEditable()) : (next.isVisible() && next.isEditable()))) {
                            return next;
                        }
                    }
                }
            }
        }
        return null;
    }

    private void prepareInputDialog(final AlertDialog dialog) {
        if (!ListenerUtil.mutListener.listen(7866)) {
            dialog.setTitle(getName());
        }
        if (!ListenerUtil.mutListener.listen(7867)) {
            dialog.setIcon(getIcon());
        }
        final View input = getInputView();
        FrameLayout fl = dialog.findViewById(android.R.id.custom);
        if (!ListenerUtil.mutListener.listen(7868)) {
            fl.removeAllViews();
        }
        if (!ListenerUtil.mutListener.listen(7869)) {
            fl.addView(input, new LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        }
        View.OnClickListener clickListener = new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if (!ListenerUtil.mutListener.listen(7871)) {
                    if ((ListenerUtil.mutListener.listen(7870) ? (view == dialog.getButton(DialogInterface.BUTTON_POSITIVE) || !validateAndSetInput(input)) : (view == dialog.getButton(DialogInterface.BUTTON_POSITIVE) && !validateAndSetInput(input)))) {
                        return;
                    }
                }
                if (!ListenerUtil.mutListener.listen(7872)) {
                    dialog.dismiss();
                }
            }
        };
        if (!ListenerUtil.mutListener.listen(7873)) {
            dialog.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener(clickListener);
        }
        if (!ListenerUtil.mutListener.listen(7874)) {
            dialog.getButton(DialogInterface.BUTTON_NEGATIVE).setOnClickListener(clickListener);
        }
        final MeasurementView next = getNextView();
        if (!ListenerUtil.mutListener.listen(7879)) {
            if (next != null) {
                if (!ListenerUtil.mutListener.listen(7878)) {
                    dialog.getButton(DialogInterface.BUTTON_NEUTRAL).setOnClickListener(new OnClickListener() {

                        @Override
                        public void onClick(View v) {
                            if (!ListenerUtil.mutListener.listen(7877)) {
                                if (validateAndSetInput(input)) {
                                    if (!ListenerUtil.mutListener.listen(7876)) {
                                        next.prepareInputDialog(dialog);
                                    }
                                }
                            }
                        }
                    });
                }
            } else {
                if (!ListenerUtil.mutListener.listen(7875)) {
                    dialog.getButton(DialogInterface.BUTTON_NEUTRAL).setVisibility(GONE);
                }
            }
        }
    }

    private void showInputDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        if (!ListenerUtil.mutListener.listen(7880)) {
            builder.setTitle(getName());
        }
        if (!ListenerUtil.mutListener.listen(7881)) {
            builder.setIcon(getIcon());
        }
        if (!ListenerUtil.mutListener.listen(7882)) {
            // the soft input (if needed).
            builder.setView(new EditText(getContext()));
        }
        if (!ListenerUtil.mutListener.listen(7883)) {
            builder.setPositiveButton(R.string.label_ok, null);
        }
        if (!ListenerUtil.mutListener.listen(7884)) {
            builder.setNegativeButton(R.string.label_cancel, null);
        }
        if (!ListenerUtil.mutListener.listen(7885)) {
            builder.setNeutralButton(R.string.label_next, null);
        }
        final AlertDialog dialog = builder.create();
        if (!ListenerUtil.mutListener.listen(7887)) {
            dialog.setOnShowListener(new DialogInterface.OnShowListener() {

                @Override
                public void onShow(DialogInterface dialogInterface) {
                    if (!ListenerUtil.mutListener.listen(7886)) {
                        prepareInputDialog(dialog);
                    }
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(7888)) {
            dialog.show();
        }
    }

    private class onClickListenerEvaluation implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            if (!ListenerUtil.mutListener.listen(7889)) {
                if (getMeasurementMode() == STATISTIC) {
                    return;
                }
            }
            if (!ListenerUtil.mutListener.listen(7893)) {
                if ((ListenerUtil.mutListener.listen(7890) ? (getMeasurementMode() == EDIT && getMeasurementMode() == ADD) : (getMeasurementMode() == EDIT || getMeasurementMode() == ADD))) {
                    if (!ListenerUtil.mutListener.listen(7892)) {
                        if (isEditable()) {
                            if (!ListenerUtil.mutListener.listen(7891)) {
                                showInputDialog();
                            }
                        }
                    }
                    return;
                }
            }
            if (!ListenerUtil.mutListener.listen(7894)) {
                setExpand(evaluatorRow.getVisibility() != View.VISIBLE);
            }
        }
    }
}
