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
package com.health.openscale.gui.table;

import android.content.res.Configuration;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableRow;
import android.widget.TextView;
import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.health.openscale.R;
import com.health.openscale.core.OpenScale;
import com.health.openscale.core.datatypes.ScaleMeasurement;
import com.health.openscale.gui.measurement.MeasurementEntryFragment;
import com.health.openscale.gui.measurement.MeasurementView;
import com.health.openscale.gui.measurement.UserMeasurementView;
import com.health.openscale.gui.utils.ColorUtil;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import static android.util.TypedValue.COMPLEX_UNIT_DIP;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class TableFragment extends Fragment {

    private View tableView;

    private LinearLayout tableHeaderView;

    private RecyclerView recyclerView;

    private MeasurementsAdapter adapter;

    private LinearLayoutManager layoutManager;

    private List<MeasurementView> measurementViews;

    public TableFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(9071)) {
            tableView = inflater.inflate(R.layout.fragment_table, container, false);
        }
        if (!ListenerUtil.mutListener.listen(9072)) {
            tableHeaderView = tableView.findViewById(R.id.tableHeaderView);
        }
        if (!ListenerUtil.mutListener.listen(9073)) {
            recyclerView = tableView.findViewById(R.id.tableDataView);
        }
        if (!ListenerUtil.mutListener.listen(9074)) {
            recyclerView.setHasFixedSize(true);
        }
        if (!ListenerUtil.mutListener.listen(9075)) {
            layoutManager = new LinearLayoutManager(getContext());
        }
        if (!ListenerUtil.mutListener.listen(9076)) {
            recyclerView.setLayoutManager(layoutManager);
        }
        if (!ListenerUtil.mutListener.listen(9077)) {
            recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), layoutManager.getOrientation()));
        }
        if (!ListenerUtil.mutListener.listen(9078)) {
            adapter = new MeasurementsAdapter();
        }
        if (!ListenerUtil.mutListener.listen(9079)) {
            recyclerView.setAdapter(adapter);
        }
        if (!ListenerUtil.mutListener.listen(9080)) {
            measurementViews = MeasurementView.getMeasurementList(getContext(), MeasurementView.DateTimeOrder.FIRST);
        }
        if (!ListenerUtil.mutListener.listen(9082)) {
            {
                long _loopCounter111 = 0;
                for (MeasurementView measurement : measurementViews) {
                    ListenerUtil.loopListener.listen("_loopCounter111", ++_loopCounter111);
                    if (!ListenerUtil.mutListener.listen(9081)) {
                        measurement.setUpdateViews(false);
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(9084)) {
            OpenScale.getInstance().getScaleMeasurementsLiveData().observe(getViewLifecycleOwner(), new Observer<List<ScaleMeasurement>>() {

                @Override
                public void onChanged(List<ScaleMeasurement> scaleMeasurements) {
                    if (!ListenerUtil.mutListener.listen(9083)) {
                        updateOnView(scaleMeasurements);
                    }
                }
            });
        }
        OnBackPressedCallback onBackPressedCallback = new OnBackPressedCallback(true) {

            @Override
            public void handleOnBackPressed() {
                if (!ListenerUtil.mutListener.listen(9085)) {
                    requireActivity().finish();
                }
            }
        };
        if (!ListenerUtil.mutListener.listen(9086)) {
            requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), onBackPressedCallback);
        }
        return tableView;
    }

    public void updateOnView(List<ScaleMeasurement> scaleMeasurementList) {
        if (!ListenerUtil.mutListener.listen(9087)) {
            tableHeaderView.removeAllViews();
        }
        final int iconHeight = pxImageDp(20);
        ArrayList<MeasurementView> visibleMeasurements = new ArrayList<>();
        if (!ListenerUtil.mutListener.listen(9096)) {
            {
                long _loopCounter112 = 0;
                for (MeasurementView measurement : measurementViews) {
                    ListenerUtil.loopListener.listen("_loopCounter112", ++_loopCounter112);
                    if (!ListenerUtil.mutListener.listen(9089)) {
                        if ((ListenerUtil.mutListener.listen(9088) ? (!measurement.isVisible() && measurement instanceof UserMeasurementView) : (!measurement.isVisible() || measurement instanceof UserMeasurementView))) {
                            continue;
                        }
                    }
                    ImageView headerIcon = new ImageView(tableView.getContext());
                    if (!ListenerUtil.mutListener.listen(9090)) {
                        headerIcon.setImageDrawable(measurement.getIcon());
                    }
                    if (!ListenerUtil.mutListener.listen(9091)) {
                        headerIcon.setColorFilter(ColorUtil.getTintColor(tableView.getContext()));
                    }
                    if (!ListenerUtil.mutListener.listen(9092)) {
                        headerIcon.setLayoutParams(new TableRow.LayoutParams(0, iconHeight, 1));
                    }
                    if (!ListenerUtil.mutListener.listen(9093)) {
                        headerIcon.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                    }
                    if (!ListenerUtil.mutListener.listen(9094)) {
                        tableHeaderView.addView(headerIcon);
                    }
                    if (!ListenerUtil.mutListener.listen(9095)) {
                        visibleMeasurements.add(measurement);
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(9097)) {
            adapter.setMeasurements(visibleMeasurements, scaleMeasurementList);
        }
    }

    private int pxImageDp(float dp) {
        return (int) ((ListenerUtil.mutListener.listen(9101) ? (dp % getResources().getDisplayMetrics().density) : (ListenerUtil.mutListener.listen(9100) ? (dp / getResources().getDisplayMetrics().density) : (ListenerUtil.mutListener.listen(9099) ? (dp - getResources().getDisplayMetrics().density) : (ListenerUtil.mutListener.listen(9098) ? (dp + getResources().getDisplayMetrics().density) : (dp * getResources().getDisplayMetrics().density))))) + 0.5f);
    }

    private class MeasurementsAdapter extends RecyclerView.Adapter<MeasurementsAdapter.ViewHolder> {

        public static final int VIEW_TYPE_MEASUREMENT = 0;

        public static final int VIEW_TYPE_YEAR = 1;

        public class ViewHolder extends RecyclerView.ViewHolder {

            public LinearLayout measurementView;

            public ViewHolder(LinearLayout view) {
                super(view);
                if (!ListenerUtil.mutListener.listen(9102)) {
                    measurementView = view;
                }
            }
        }

        private List<MeasurementView> visibleMeasurements;

        private List<ScaleMeasurement> scaleMeasurements;

        public void setMeasurements(List<MeasurementView> visibleMeasurements, List<ScaleMeasurement> scaleMeasurements) {
            if (!ListenerUtil.mutListener.listen(9103)) {
                this.visibleMeasurements = visibleMeasurements;
            }
            if (!ListenerUtil.mutListener.listen(9104)) {
                this.scaleMeasurements = new ArrayList<>(scaleMeasurements.size() + 10);
            }
            Calendar calendar = Calendar.getInstance();
            if (!ListenerUtil.mutListener.listen(9106)) {
                if (!scaleMeasurements.isEmpty()) {
                    if (!ListenerUtil.mutListener.listen(9105)) {
                        calendar.setTime(scaleMeasurements.get(0).getDateTime());
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(9107)) {
                calendar.set(calendar.get(Calendar.YEAR), 0, 1, 0, 0, 0);
            }
            if (!ListenerUtil.mutListener.listen(9108)) {
                calendar.set(calendar.MILLISECOND, 0);
            }
            // an extra "null" entry when the year changes.
            Date yearStart = calendar.getTime();
            if (!ListenerUtil.mutListener.listen(9120)) {
                {
                    long _loopCounter113 = 0;
                    for (int i = 0; (ListenerUtil.mutListener.listen(9119) ? (i >= scaleMeasurements.size()) : (ListenerUtil.mutListener.listen(9118) ? (i <= scaleMeasurements.size()) : (ListenerUtil.mutListener.listen(9117) ? (i > scaleMeasurements.size()) : (ListenerUtil.mutListener.listen(9116) ? (i != scaleMeasurements.size()) : (ListenerUtil.mutListener.listen(9115) ? (i == scaleMeasurements.size()) : (i < scaleMeasurements.size())))))); ++i) {
                        ListenerUtil.loopListener.listen("_loopCounter113", ++_loopCounter113);
                        final ScaleMeasurement measurement = scaleMeasurements.get(i);
                        if (!ListenerUtil.mutListener.listen(9113)) {
                            if (measurement.getDateTime().before(yearStart)) {
                                if (!ListenerUtil.mutListener.listen(9109)) {
                                    this.scaleMeasurements.add(null);
                                }
                                Calendar newCalendar = Calendar.getInstance();
                                if (!ListenerUtil.mutListener.listen(9110)) {
                                    newCalendar.setTime(measurement.getDateTime());
                                }
                                if (!ListenerUtil.mutListener.listen(9111)) {
                                    calendar.set(Calendar.YEAR, newCalendar.get(Calendar.YEAR));
                                }
                                if (!ListenerUtil.mutListener.listen(9112)) {
                                    yearStart = calendar.getTime();
                                }
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(9114)) {
                            this.scaleMeasurements.add(measurement);
                        }
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(9121)) {
                notifyDataSetChanged();
            }
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LinearLayout row = new LinearLayout(getContext());
            if (!ListenerUtil.mutListener.listen(9122)) {
                row.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            }
            final int screenSize = getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK;
            final boolean isSmallScreen = (ListenerUtil.mutListener.listen(9123) ? (screenSize != Configuration.SCREENLAYOUT_SIZE_XLARGE || screenSize != Configuration.SCREENLAYOUT_SIZE_LARGE) : (screenSize != Configuration.SCREENLAYOUT_SIZE_XLARGE && screenSize != Configuration.SCREENLAYOUT_SIZE_LARGE));
            final int count = (ListenerUtil.mutListener.listen(9128) ? (viewType >= VIEW_TYPE_YEAR) : (ListenerUtil.mutListener.listen(9127) ? (viewType <= VIEW_TYPE_YEAR) : (ListenerUtil.mutListener.listen(9126) ? (viewType > VIEW_TYPE_YEAR) : (ListenerUtil.mutListener.listen(9125) ? (viewType < VIEW_TYPE_YEAR) : (ListenerUtil.mutListener.listen(9124) ? (viewType != VIEW_TYPE_YEAR) : (viewType == VIEW_TYPE_YEAR)))))) ? 1 : visibleMeasurements.size();
            if (!ListenerUtil.mutListener.listen(9149)) {
                {
                    long _loopCounter114 = 0;
                    for (int i = 0; (ListenerUtil.mutListener.listen(9148) ? (i >= count) : (ListenerUtil.mutListener.listen(9147) ? (i <= count) : (ListenerUtil.mutListener.listen(9146) ? (i > count) : (ListenerUtil.mutListener.listen(9145) ? (i != count) : (ListenerUtil.mutListener.listen(9144) ? (i == count) : (i < count)))))); ++i) {
                        ListenerUtil.loopListener.listen("_loopCounter114", ++_loopCounter114);
                        TextView column = new TextView(getContext());
                        if (!ListenerUtil.mutListener.listen(9129)) {
                            column.setLayoutParams(new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1));
                        }
                        if (!ListenerUtil.mutListener.listen(9142)) {
                            if ((ListenerUtil.mutListener.listen(9134) ? (viewType >= VIEW_TYPE_MEASUREMENT) : (ListenerUtil.mutListener.listen(9133) ? (viewType <= VIEW_TYPE_MEASUREMENT) : (ListenerUtil.mutListener.listen(9132) ? (viewType > VIEW_TYPE_MEASUREMENT) : (ListenerUtil.mutListener.listen(9131) ? (viewType < VIEW_TYPE_MEASUREMENT) : (ListenerUtil.mutListener.listen(9130) ? (viewType != VIEW_TYPE_MEASUREMENT) : (viewType == VIEW_TYPE_MEASUREMENT))))))) {
                                if (!ListenerUtil.mutListener.listen(9138)) {
                                    column.setMinLines(2);
                                }
                                if (!ListenerUtil.mutListener.listen(9139)) {
                                    column.setGravity(Gravity.CENTER_HORIZONTAL);
                                }
                                if (!ListenerUtil.mutListener.listen(9141)) {
                                    if (isSmallScreen) {
                                        if (!ListenerUtil.mutListener.listen(9140)) {
                                            column.setTextSize(COMPLEX_UNIT_DIP, 9);
                                        }
                                    }
                                }
                            } else {
                                if (!ListenerUtil.mutListener.listen(9135)) {
                                    column.setPadding(0, 10, 0, 10);
                                }
                                if (!ListenerUtil.mutListener.listen(9136)) {
                                    column.setGravity(Gravity.CENTER);
                                }
                                if (!ListenerUtil.mutListener.listen(9137)) {
                                    column.setTextSize(COMPLEX_UNIT_DIP, 16);
                                }
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(9143)) {
                            row.addView(column);
                        }
                    }
                }
            }
            return new ViewHolder(row);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            LinearLayout row = holder.measurementView;
            final ScaleMeasurement measurement = scaleMeasurements.get(position);
            if (!ListenerUtil.mutListener.listen(9156)) {
                if (measurement == null) {
                    ScaleMeasurement nextMeasurement = scaleMeasurements.get((ListenerUtil.mutListener.listen(9153) ? (position % 1) : (ListenerUtil.mutListener.listen(9152) ? (position / 1) : (ListenerUtil.mutListener.listen(9151) ? (position * 1) : (ListenerUtil.mutListener.listen(9150) ? (position - 1) : (position + 1))))));
                    Calendar calendar = Calendar.getInstance();
                    if (!ListenerUtil.mutListener.listen(9154)) {
                        calendar.setTime(nextMeasurement.getDateTime());
                    }
                    TextView column = (TextView) row.getChildAt(0);
                    if (!ListenerUtil.mutListener.listen(9155)) {
                        column.setText(String.format("%d", calendar.get(Calendar.YEAR)));
                    }
                    return;
                }
            }
            ScaleMeasurement prevMeasurement = null;
            if (!ListenerUtil.mutListener.listen(9177)) {
                if ((ListenerUtil.mutListener.listen(9165) ? ((ListenerUtil.mutListener.listen(9160) ? (position % 1) : (ListenerUtil.mutListener.listen(9159) ? (position / 1) : (ListenerUtil.mutListener.listen(9158) ? (position * 1) : (ListenerUtil.mutListener.listen(9157) ? (position - 1) : (position + 1))))) >= scaleMeasurements.size()) : (ListenerUtil.mutListener.listen(9164) ? ((ListenerUtil.mutListener.listen(9160) ? (position % 1) : (ListenerUtil.mutListener.listen(9159) ? (position / 1) : (ListenerUtil.mutListener.listen(9158) ? (position * 1) : (ListenerUtil.mutListener.listen(9157) ? (position - 1) : (position + 1))))) <= scaleMeasurements.size()) : (ListenerUtil.mutListener.listen(9163) ? ((ListenerUtil.mutListener.listen(9160) ? (position % 1) : (ListenerUtil.mutListener.listen(9159) ? (position / 1) : (ListenerUtil.mutListener.listen(9158) ? (position * 1) : (ListenerUtil.mutListener.listen(9157) ? (position - 1) : (position + 1))))) > scaleMeasurements.size()) : (ListenerUtil.mutListener.listen(9162) ? ((ListenerUtil.mutListener.listen(9160) ? (position % 1) : (ListenerUtil.mutListener.listen(9159) ? (position / 1) : (ListenerUtil.mutListener.listen(9158) ? (position * 1) : (ListenerUtil.mutListener.listen(9157) ? (position - 1) : (position + 1))))) != scaleMeasurements.size()) : (ListenerUtil.mutListener.listen(9161) ? ((ListenerUtil.mutListener.listen(9160) ? (position % 1) : (ListenerUtil.mutListener.listen(9159) ? (position / 1) : (ListenerUtil.mutListener.listen(9158) ? (position * 1) : (ListenerUtil.mutListener.listen(9157) ? (position - 1) : (position + 1))))) == scaleMeasurements.size()) : ((ListenerUtil.mutListener.listen(9160) ? (position % 1) : (ListenerUtil.mutListener.listen(9159) ? (position / 1) : (ListenerUtil.mutListener.listen(9158) ? (position * 1) : (ListenerUtil.mutListener.listen(9157) ? (position - 1) : (position + 1))))) < scaleMeasurements.size()))))))) {
                    if (!ListenerUtil.mutListener.listen(9170)) {
                        prevMeasurement = scaleMeasurements.get((ListenerUtil.mutListener.listen(9169) ? (position % 1) : (ListenerUtil.mutListener.listen(9168) ? (position / 1) : (ListenerUtil.mutListener.listen(9167) ? (position * 1) : (ListenerUtil.mutListener.listen(9166) ? (position - 1) : (position + 1))))));
                    }
                    if (!ListenerUtil.mutListener.listen(9176)) {
                        if (prevMeasurement == null) {
                            if (!ListenerUtil.mutListener.listen(9175)) {
                                prevMeasurement = scaleMeasurements.get((ListenerUtil.mutListener.listen(9174) ? (position % 2) : (ListenerUtil.mutListener.listen(9173) ? (position / 2) : (ListenerUtil.mutListener.listen(9172) ? (position * 2) : (ListenerUtil.mutListener.listen(9171) ? (position - 2) : (position + 2))))));
                            }
                        }
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(9187)) {
                {
                    long _loopCounter115 = 0;
                    // Fill view with data
                    for (int i = 0; (ListenerUtil.mutListener.listen(9186) ? (i >= visibleMeasurements.size()) : (ListenerUtil.mutListener.listen(9185) ? (i <= visibleMeasurements.size()) : (ListenerUtil.mutListener.listen(9184) ? (i > visibleMeasurements.size()) : (ListenerUtil.mutListener.listen(9183) ? (i != visibleMeasurements.size()) : (ListenerUtil.mutListener.listen(9182) ? (i == visibleMeasurements.size()) : (i < visibleMeasurements.size())))))); ++i) {
                        ListenerUtil.loopListener.listen("_loopCounter115", ++_loopCounter115);
                        final MeasurementView view = visibleMeasurements.get(i);
                        if (!ListenerUtil.mutListener.listen(9178)) {
                            view.loadFrom(measurement, prevMeasurement);
                        }
                        SpannableStringBuilder string = new SpannableStringBuilder();
                        if (!ListenerUtil.mutListener.listen(9179)) {
                            string.append(view.getValueAsString(false));
                        }
                        if (!ListenerUtil.mutListener.listen(9180)) {
                            view.appendDiffValue(string, true);
                        }
                        TextView column = (TextView) row.getChildAt(i);
                        if (!ListenerUtil.mutListener.listen(9181)) {
                            column.setText(string);
                        }
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(9191)) {
                row.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        TableFragmentDirections.ActionNavTableToNavDataentry action = TableFragmentDirections.actionNavTableToNavDataentry();
                        if (!ListenerUtil.mutListener.listen(9188)) {
                            action.setMeasurementId(measurement.getId());
                        }
                        if (!ListenerUtil.mutListener.listen(9189)) {
                            action.setMode(MeasurementEntryFragment.DATA_ENTRY_MODE.VIEW);
                        }
                        if (!ListenerUtil.mutListener.listen(9190)) {
                            Navigation.findNavController(getActivity(), R.id.nav_host_fragment).navigate(action);
                        }
                    }
                });
            }
        }

        @Override
        public int getItemCount() {
            return scaleMeasurements == null ? 0 : scaleMeasurements.size();
        }

        @Override
        public int getItemViewType(int position) {
            return scaleMeasurements.get(position) != null ? VIEW_TYPE_MEASUREMENT : VIEW_TYPE_YEAR;
        }
    }
}
