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
package com.health.openscale.gui.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.SpannableStringBuilder;
import android.util.TypedValue;
import android.view.View;
import android.widget.RemoteViews;
import com.health.openscale.R;
import com.health.openscale.core.OpenScale;
import com.health.openscale.core.datatypes.ScaleMeasurement;
import com.health.openscale.gui.MainActivity;
import com.health.openscale.gui.measurement.MeasurementView;
import java.text.DateFormat;
import java.util.List;
import timber.log.Timber;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class WidgetProvider extends AppWidgetProvider {

    List<MeasurementView> measurementViews;

    public static final String getUserIdPreferenceName(int appWidgetId) {
        return String.format("widget_%d_userid", appWidgetId);
    }

    public static final String getMeasurementPreferenceName(int appWidgetId) {
        return String.format("widget_%d_measurement", appWidgetId);
    }

    private void updateWidget(Context context, AppWidgetManager appWidgetManager, int appWidgetId, Bundle newOptions) {
        if (!ListenerUtil.mutListener.listen(9273)) {
            // Make sure we use the correct language
            context = MainActivity.createBaseContext(context);
        }
        final int minWidth = newOptions.getInt(AppWidgetManager.OPTION_APPWIDGET_MIN_WIDTH);
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        int userId = prefs.getInt(getUserIdPreferenceName(appWidgetId), -1);
        String key = prefs.getString(getMeasurementPreferenceName(appWidgetId), "");
        if (!ListenerUtil.mutListener.listen(9274)) {
            Timber.d("Update widget %d (%s) for user %d, min width %ddp", appWidgetId, key, userId, minWidth);
        }
        if (!ListenerUtil.mutListener.listen(9276)) {
            if (measurementViews == null) {
                if (!ListenerUtil.mutListener.listen(9275)) {
                    measurementViews = MeasurementView.getMeasurementList(context, MeasurementView.DateTimeOrder.NONE);
                }
            }
        }
        MeasurementView measurementView = measurementViews.get(0);
        if (!ListenerUtil.mutListener.listen(9279)) {
            {
                long _loopCounter118 = 0;
                for (MeasurementView view : measurementViews) {
                    ListenerUtil.loopListener.listen("_loopCounter118", ++_loopCounter118);
                    if (!ListenerUtil.mutListener.listen(9278)) {
                        if (view.getKey().equals(key)) {
                            if (!ListenerUtil.mutListener.listen(9277)) {
                                measurementView = view;
                            }
                            break;
                        }
                    }
                }
            }
        }
        OpenScale openScale = OpenScale.getInstance();
        ScaleMeasurement latest = openScale.getLastScaleMeasurement(userId);
        if (!ListenerUtil.mutListener.listen(9281)) {
            if (latest != null) {
                ScaleMeasurement previous = openScale.getTupleOfScaleMeasurement(latest.getId())[0];
                if (!ListenerUtil.mutListener.listen(9280)) {
                    measurementView.loadFrom(latest, previous);
                }
            }
        }
        // From https://developer.android.com/guide/practices/ui_guidelines/widget_design
        final int twoCellsMinWidth = 110;
        final int thirdCellsMinWidth = 180;
        final int fourCellsMinWidth = 250;
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget);
        // Add some transparency to make the corners appear rounded
        int indicatorColor = measurementView.getIndicatorColor();
        if (!ListenerUtil.mutListener.listen(9282)) {
            indicatorColor = (180 << 24) | (indicatorColor & 0xffffff);
        }
        if (!ListenerUtil.mutListener.listen(9283)) {
            views.setInt(R.id.indicator_view, "setBackgroundColor", indicatorColor);
        }
        if (!ListenerUtil.mutListener.listen(9295)) {
            // Show icon in >= two cell mode
            if ((ListenerUtil.mutListener.listen(9288) ? (minWidth <= twoCellsMinWidth) : (ListenerUtil.mutListener.listen(9287) ? (minWidth > twoCellsMinWidth) : (ListenerUtil.mutListener.listen(9286) ? (minWidth < twoCellsMinWidth) : (ListenerUtil.mutListener.listen(9285) ? (minWidth != twoCellsMinWidth) : (ListenerUtil.mutListener.listen(9284) ? (minWidth == twoCellsMinWidth) : (minWidth >= twoCellsMinWidth))))))) {
                if (!ListenerUtil.mutListener.listen(9292)) {
                    views.setImageViewResource(R.id.widget_icon, measurementView.getIconResource());
                }
                if (!ListenerUtil.mutListener.listen(9293)) {
                    views.setViewVisibility(R.id.widget_icon, View.VISIBLE);
                }
                if (!ListenerUtil.mutListener.listen(9294)) {
                    views.setViewVisibility(R.id.widget_icon_vertical, View.GONE);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(9289)) {
                    views.setImageViewResource(R.id.widget_icon_vertical, measurementView.getIconResource());
                }
                if (!ListenerUtil.mutListener.listen(9290)) {
                    views.setViewVisibility(R.id.widget_icon_vertical, View.VISIBLE);
                }
                if (!ListenerUtil.mutListener.listen(9291)) {
                    views.setViewVisibility(R.id.widget_icon, View.GONE);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(9305)) {
            // Show measurement name in >= four cell mode
            if ((ListenerUtil.mutListener.listen(9300) ? (minWidth <= fourCellsMinWidth) : (ListenerUtil.mutListener.listen(9299) ? (minWidth > fourCellsMinWidth) : (ListenerUtil.mutListener.listen(9298) ? (minWidth < fourCellsMinWidth) : (ListenerUtil.mutListener.listen(9297) ? (minWidth != fourCellsMinWidth) : (ListenerUtil.mutListener.listen(9296) ? (minWidth == fourCellsMinWidth) : (minWidth >= fourCellsMinWidth))))))) {
                if (!ListenerUtil.mutListener.listen(9302)) {
                    views.setTextViewText(R.id.widget_name, measurementView.getName());
                }
                if (!ListenerUtil.mutListener.listen(9303)) {
                    views.setTextViewText(R.id.widget_date, latest != null ? DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.SHORT).format(latest.getDateTime()) : "");
                }
                if (!ListenerUtil.mutListener.listen(9304)) {
                    views.setViewVisibility(R.id.widget_name_date_layout, View.VISIBLE);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(9301)) {
                    views.setViewVisibility(R.id.widget_name_date_layout, View.GONE);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(9306)) {
            // Always show value and delta, but adjust font size based on widget width
            views.setTextViewText(R.id.widget_value, measurementView.getValueAsString(true));
        }
        SpannableStringBuilder delta = new SpannableStringBuilder();
        if (!ListenerUtil.mutListener.listen(9307)) {
            measurementView.appendDiffValue(delta, false);
        }
        if (!ListenerUtil.mutListener.listen(9308)) {
            views.setTextViewText(R.id.widget_delta, delta);
        }
        int textSize;
        if ((ListenerUtil.mutListener.listen(9313) ? (minWidth <= thirdCellsMinWidth) : (ListenerUtil.mutListener.listen(9312) ? (minWidth > thirdCellsMinWidth) : (ListenerUtil.mutListener.listen(9311) ? (minWidth < thirdCellsMinWidth) : (ListenerUtil.mutListener.listen(9310) ? (minWidth != thirdCellsMinWidth) : (ListenerUtil.mutListener.listen(9309) ? (minWidth == thirdCellsMinWidth) : (minWidth >= thirdCellsMinWidth))))))) {
            textSize = 18;
        } else if ((ListenerUtil.mutListener.listen(9318) ? (minWidth <= twoCellsMinWidth) : (ListenerUtil.mutListener.listen(9317) ? (minWidth > twoCellsMinWidth) : (ListenerUtil.mutListener.listen(9316) ? (minWidth < twoCellsMinWidth) : (ListenerUtil.mutListener.listen(9315) ? (minWidth != twoCellsMinWidth) : (ListenerUtil.mutListener.listen(9314) ? (minWidth == twoCellsMinWidth) : (minWidth >= twoCellsMinWidth))))))) {
            textSize = 17;
        } else {
            textSize = 12;
        }
        if (!ListenerUtil.mutListener.listen(9319)) {
            views.setTextViewTextSize(R.id.widget_value, TypedValue.COMPLEX_UNIT_DIP, textSize);
        }
        if (!ListenerUtil.mutListener.listen(9320)) {
            views.setTextViewTextSize(R.id.widget_delta, TypedValue.COMPLEX_UNIT_DIP, textSize);
        }
        // Start main activity when widget is clicked
        Intent intent = new Intent(context, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
        if (!ListenerUtil.mutListener.listen(9321)) {
            views.setOnClickPendingIntent(R.id.widget_layout, pendingIntent);
        }
        if (!ListenerUtil.mutListener.listen(9322)) {
            appWidgetManager.updateAppWidget(appWidgetId, views);
        }
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        if (!ListenerUtil.mutListener.listen(9324)) {
            {
                long _loopCounter119 = 0;
                for (int appWidgetId : appWidgetIds) {
                    ListenerUtil.loopListener.listen("_loopCounter119", ++_loopCounter119);
                    Bundle newOptions = appWidgetManager.getAppWidgetOptions(appWidgetId);
                    if (!ListenerUtil.mutListener.listen(9323)) {
                        updateWidget(context, appWidgetManager, appWidgetId, newOptions);
                    }
                }
            }
        }
    }

    @Override
    public void onAppWidgetOptionsChanged(Context context, AppWidgetManager appWidgetManager, int appWidgetId, Bundle newOptions) {
        if (!ListenerUtil.mutListener.listen(9325)) {
            updateWidget(context, appWidgetManager, appWidgetId, newOptions);
        }
    }

    @Override
    public void onDeleted(Context context, int[] appWidgetIds) {
        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(context).edit();
        if (!ListenerUtil.mutListener.listen(9328)) {
            {
                long _loopCounter120 = 0;
                for (int appWidgetId : appWidgetIds) {
                    ListenerUtil.loopListener.listen("_loopCounter120", ++_loopCounter120);
                    if (!ListenerUtil.mutListener.listen(9326)) {
                        editor.remove(getUserIdPreferenceName(appWidgetId));
                    }
                    if (!ListenerUtil.mutListener.listen(9327)) {
                        editor.remove(getMeasurementPreferenceName(appWidgetId));
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(9329)) {
            editor.apply();
        }
    }

    @Override
    public void onDisabled(Context context) {
        if (!ListenerUtil.mutListener.listen(9330)) {
            measurementViews = null;
        }
    }

    @Override
    public void onRestored(Context context, int[] oldWidgetIds, int[] newWidgetIds) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        if (!ListenerUtil.mutListener.listen(9343)) {
            {
                long _loopCounter121 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(9342) ? (i >= oldWidgetIds.length) : (ListenerUtil.mutListener.listen(9341) ? (i <= oldWidgetIds.length) : (ListenerUtil.mutListener.listen(9340) ? (i > oldWidgetIds.length) : (ListenerUtil.mutListener.listen(9339) ? (i != oldWidgetIds.length) : (ListenerUtil.mutListener.listen(9338) ? (i == oldWidgetIds.length) : (i < oldWidgetIds.length)))))); ++i) {
                    ListenerUtil.loopListener.listen("_loopCounter121", ++_loopCounter121);
                    String oldKey = getUserIdPreferenceName(oldWidgetIds[i]);
                    if (!ListenerUtil.mutListener.listen(9333)) {
                        if (prefs.contains(oldKey)) {
                            if (!ListenerUtil.mutListener.listen(9331)) {
                                editor.putInt(getUserIdPreferenceName(newWidgetIds[i]), prefs.getInt(oldKey, -1));
                            }
                            if (!ListenerUtil.mutListener.listen(9332)) {
                                editor.remove(oldKey);
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(9334)) {
                        oldKey = getMeasurementPreferenceName(oldWidgetIds[i]);
                    }
                    if (!ListenerUtil.mutListener.listen(9337)) {
                        if (prefs.contains(oldKey)) {
                            if (!ListenerUtil.mutListener.listen(9335)) {
                                editor.putString(getMeasurementPreferenceName(newWidgetIds[i]), prefs.getString(oldKey, ""));
                            }
                            if (!ListenerUtil.mutListener.listen(9336)) {
                                editor.remove(oldKey);
                            }
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(9344)) {
            editor.apply();
        }
    }
}
