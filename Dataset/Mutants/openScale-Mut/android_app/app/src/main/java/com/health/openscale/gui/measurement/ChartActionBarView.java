/* Copyright (C) 2019  olie.xdev <olie.xdev@googlemail.com>
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
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.health.openscale.gui.utils.ColorUtil;
import java.util.List;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class ChartActionBarView extends HorizontalScrollView {

    private LinearLayout actionBarView;

    private List<MeasurementView> measurementViews;

    private View.OnClickListener onActionClickListener;

    private boolean isInGraphKey;

    public ChartActionBarView(Context context) {
        super(context);
        if (!ListenerUtil.mutListener.listen(6439)) {
            init();
        }
    }

    public ChartActionBarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        if (!ListenerUtil.mutListener.listen(6440)) {
            init();
        }
    }

    public ChartActionBarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        if (!ListenerUtil.mutListener.listen(6441)) {
            init();
        }
    }

    private void init() {
        if (!ListenerUtil.mutListener.listen(6442)) {
            actionBarView = new LinearLayout(getContext());
        }
        if (!ListenerUtil.mutListener.listen(6443)) {
            actionBarView.setOrientation(LinearLayout.HORIZONTAL);
        }
        if (!ListenerUtil.mutListener.listen(6444)) {
            actionBarView.setBackgroundColor(ColorUtil.COLOR_BLACK);
        }
        if (!ListenerUtil.mutListener.listen(6445)) {
            measurementViews = MeasurementView.getMeasurementList(getContext(), MeasurementView.DateTimeOrder.NONE);
        }
        if (!ListenerUtil.mutListener.listen(6446)) {
            isInGraphKey = true;
        }
        if (!ListenerUtil.mutListener.listen(6447)) {
            onActionClickListener = null;
        }
        if (!ListenerUtil.mutListener.listen(6448)) {
            addView(actionBarView);
        }
        if (!ListenerUtil.mutListener.listen(6449)) {
            refreshFloatingActionsButtons();
        }
    }

    public void setOnActionClickListener(View.OnClickListener listener) {
        if (!ListenerUtil.mutListener.listen(6450)) {
            onActionClickListener = listener;
        }
    }

    public void setIsInGraphKey(boolean status) {
        if (!ListenerUtil.mutListener.listen(6451)) {
            isInGraphKey = status;
        }
        if (!ListenerUtil.mutListener.listen(6452)) {
            refreshFloatingActionsButtons();
        }
    }

    private void refreshFloatingActionsButtons() {
        if (!ListenerUtil.mutListener.listen(6453)) {
            actionBarView.removeAllViews();
        }
        if (!ListenerUtil.mutListener.listen(6457)) {
            {
                long _loopCounter68 = 0;
                for (MeasurementView view : measurementViews) {
                    ListenerUtil.loopListener.listen("_loopCounter68", ++_loopCounter68);
                    if (!ListenerUtil.mutListener.listen(6456)) {
                        if (view instanceof FloatMeasurementView) {
                            final FloatMeasurementView measurementView = (FloatMeasurementView) view;
                            if (!ListenerUtil.mutListener.listen(6455)) {
                                if (measurementView.isVisible()) {
                                    if (!ListenerUtil.mutListener.listen(6454)) {
                                        addActionButton(measurementView);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private void addActionButton(FloatMeasurementView measurementView) {
        FloatingActionButton actionButton = new FloatingActionButton(getContext());
        if (!ListenerUtil.mutListener.listen(6458)) {
            actionButton.setTag(measurementView.getKey());
        }
        if (!ListenerUtil.mutListener.listen(6459)) {
            actionButton.setColorFilter(Color.parseColor("#000000"));
        }
        if (!ListenerUtil.mutListener.listen(6460)) {
            actionButton.setImageDrawable(measurementView.getIcon());
        }
        if (!ListenerUtil.mutListener.listen(6461)) {
            actionButton.setClickable(true);
        }
        if (!ListenerUtil.mutListener.listen(6462)) {
            actionButton.setSize(FloatingActionButton.SIZE_MINI);
        }
        RelativeLayout.LayoutParams lay = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        if (!ListenerUtil.mutListener.listen(6463)) {
            lay.setMargins(0, 5, 20, 10);
        }
        if (!ListenerUtil.mutListener.listen(6464)) {
            actionButton.setLayoutParams(lay);
        }
        if (!ListenerUtil.mutListener.listen(6465)) {
            actionButton.setOnClickListener(new onActionClickListener());
        }
        if (!ListenerUtil.mutListener.listen(6468)) {
            if (isInGraphKey) {
                int color = measurementView.getSettings().isInGraph() ? measurementView.getColor() : ColorUtil.COLOR_GRAY;
                if (!ListenerUtil.mutListener.listen(6467)) {
                    actionButton.setBackgroundTintList(ColorStateList.valueOf(color));
                }
            } else {
                int color = measurementView.getSettings().isInOverviewGraph() ? measurementView.getColor() : ColorUtil.COLOR_GRAY;
                if (!ListenerUtil.mutListener.listen(6466)) {
                    actionButton.setBackgroundTintList(ColorStateList.valueOf(color));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(6469)) {
            actionBarView.addView(actionButton);
        }
    }

    private class onActionClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            FloatingActionButton actionButton = (FloatingActionButton) v;
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getContext());
            String key = String.valueOf(actionButton.getTag());
            MeasurementViewSettings settings = new MeasurementViewSettings(prefs, key);
            if (!ListenerUtil.mutListener.listen(6472)) {
                if (isInGraphKey) {
                    if (!ListenerUtil.mutListener.listen(6471)) {
                        prefs.edit().putBoolean(settings.getInGraphKey(), !settings.isInGraph()).apply();
                    }
                } else {
                    if (!ListenerUtil.mutListener.listen(6470)) {
                        prefs.edit().putBoolean(settings.getInOverviewGraphKey(), !settings.isInOverviewGraph()).apply();
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(6473)) {
                refreshFloatingActionsButtons();
            }
            if (!ListenerUtil.mutListener.listen(6475)) {
                if (onActionClickListener != null) {
                    if (!ListenerUtil.mutListener.listen(6474)) {
                        onActionClickListener.onClick(v);
                    }
                }
            }
        }
    }
}
