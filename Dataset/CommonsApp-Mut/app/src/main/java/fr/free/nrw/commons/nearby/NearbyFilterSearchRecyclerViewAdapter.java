package fr.free.nrw.commons.nearby;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import fr.free.nrw.commons.R;
import fr.free.nrw.commons.nearby.presenter.NearbyParentFragmentPresenter;
import java.util.Collections;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class NearbyFilterSearchRecyclerViewAdapter extends RecyclerView.Adapter<NearbyFilterSearchRecyclerViewAdapter.RecyclerViewHolder> implements Filterable {

    private final LayoutInflater inflater;

    private Context context;

    private ArrayList<Label> labels;

    private ArrayList<Label> displayedLabels;

    public ArrayList<Label> selectedLabels = new ArrayList<>();

    private int state;

    private Callback callback;

    RecyclerView.SmoothScroller smoothScroller;

    public void setCallback(Callback callback) {
        if (!ListenerUtil.mutListener.listen(3724)) {
            this.callback = callback;
        }
    }

    public NearbyFilterSearchRecyclerViewAdapter(Context context, ArrayList<Label> labels, RecyclerView recyclerView) {
        if (!ListenerUtil.mutListener.listen(3725)) {
            this.context = context;
        }
        if (!ListenerUtil.mutListener.listen(3726)) {
            this.labels = labels;
        }
        if (!ListenerUtil.mutListener.listen(3727)) {
            this.displayedLabels = labels;
        }
        if (!ListenerUtil.mutListener.listen(3728)) {
            smoothScroller = new LinearSmoothScroller(context) {

                @Override
                protected int getVerticalSnapPreference() {
                    return LinearSmoothScroller.SNAP_TO_START;
                }
            };
        }
        inflater = LayoutInflater.from(context);
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {

        public TextView placeTypeLabel;

        public ImageView placeTypeIcon;

        public LinearLayout placeTypeLayout;

        public RecyclerViewHolder(View view) {
            super(view);
            if (!ListenerUtil.mutListener.listen(3729)) {
                placeTypeLabel = view.findViewById(R.id.place_text);
            }
            if (!ListenerUtil.mutListener.listen(3730)) {
                placeTypeIcon = view.findViewById(R.id.place_icon);
            }
            if (!ListenerUtil.mutListener.listen(3731)) {
                placeTypeLayout = view.findViewById(R.id.search_list_item);
            }
        }
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(callback.isDarkTheme() ? R.layout.nearby_search_list_item_dark : R.layout.nearby_search_list_item, parent, false);
        return new RecyclerViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        Label label = displayedLabels.get(position);
        if (!ListenerUtil.mutListener.listen(3732)) {
            holder.placeTypeIcon.setImageResource(label.getIcon());
        }
        if (!ListenerUtil.mutListener.listen(3733)) {
            holder.placeTypeLabel.setText(label.toString());
        }
        if (!ListenerUtil.mutListener.listen(3734)) {
            holder.placeTypeLayout.setSelected(label.isSelected());
        }
        if (!ListenerUtil.mutListener.listen(3735)) {
            holder.placeTypeLayout.setOnClickListener(view -> {
                callback.setCheckboxUnknown();
                if (label.isSelected()) {
                    selectedLabels.remove(label);
                } else {
                    selectedLabels.add(label);
                }
                label.setSelected(!label.isSelected());
                holder.placeTypeLayout.setSelected(label.isSelected());
                callback.filterByMarkerType(selectedLabels, 0, false, false);
            });
        }
    }

    @Override
    public long getItemId(int position) {
        return displayedLabels.get(position).hashCode();
    }

    @Override
    public int getItemCount() {
        return displayedLabels.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults results = new FilterResults();
                ArrayList<Label> filteredArrayList = new ArrayList<>();
                if (!ListenerUtil.mutListener.listen(3737)) {
                    if (labels == null) {
                        if (!ListenerUtil.mutListener.listen(3736)) {
                            labels = new ArrayList<>(displayedLabels);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(3747)) {
                    if ((ListenerUtil.mutListener.listen(3738) ? (constraint == null && constraint.length() == 0) : (constraint == null || constraint.length() == 0))) {
                        if (!ListenerUtil.mutListener.listen(3745)) {
                            // set the Original result to return
                            results.count = labels.size();
                        }
                        if (!ListenerUtil.mutListener.listen(3746)) {
                            results.values = labels;
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(3739)) {
                            constraint = constraint.toString().toLowerCase();
                        }
                        if (!ListenerUtil.mutListener.listen(3742)) {
                            {
                                long _loopCounter54 = 0;
                                for (Label label : labels) {
                                    ListenerUtil.loopListener.listen("_loopCounter54", ++_loopCounter54);
                                    String data = label.toString();
                                    if (!ListenerUtil.mutListener.listen(3741)) {
                                        if (data.toLowerCase().startsWith(constraint.toString())) {
                                            if (!ListenerUtil.mutListener.listen(3740)) {
                                                filteredArrayList.add(Label.fromText(label.getText()));
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(3743)) {
                            // set the Filtered result to return
                            results.count = filteredArrayList.size();
                        }
                        if (!ListenerUtil.mutListener.listen(3744)) {
                            results.values = filteredArrayList;
                        }
                    }
                }
                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                if (!ListenerUtil.mutListener.listen(3748)) {
                    // has the filtered values
                    displayedLabels = (ArrayList<Label>) results.values;
                }
                if (!ListenerUtil.mutListener.listen(3749)) {
                    // notifies the data with new filtered values
                    notifyDataSetChanged();
                }
            }
        };
    }

    public void setRecyclerViewAdapterItemsGreyedOut() {
        if (!ListenerUtil.mutListener.listen(3750)) {
            state = CheckBoxTriStates.UNCHECKED;
        }
        if (!ListenerUtil.mutListener.listen(3753)) {
            {
                long _loopCounter55 = 0;
                for (Label label : labels) {
                    ListenerUtil.loopListener.listen("_loopCounter55", ++_loopCounter55);
                    if (!ListenerUtil.mutListener.listen(3751)) {
                        label.setSelected(false);
                    }
                    if (!ListenerUtil.mutListener.listen(3752)) {
                        selectedLabels.remove(label);
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(3754)) {
            notifyDataSetChanged();
        }
    }

    public void setRecyclerViewAdapterAllSelected() {
        if (!ListenerUtil.mutListener.listen(3755)) {
            state = CheckBoxTriStates.CHECKED;
        }
        if (!ListenerUtil.mutListener.listen(3759)) {
            {
                long _loopCounter56 = 0;
                for (Label label : labels) {
                    ListenerUtil.loopListener.listen("_loopCounter56", ++_loopCounter56);
                    if (!ListenerUtil.mutListener.listen(3756)) {
                        label.setSelected(true);
                    }
                    if (!ListenerUtil.mutListener.listen(3758)) {
                        if (!selectedLabels.contains(label)) {
                            if (!ListenerUtil.mutListener.listen(3757)) {
                                selectedLabels.add(label);
                            }
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(3760)) {
            notifyDataSetChanged();
        }
    }

    public interface Callback {

        void setCheckboxUnknown();

        void filterByMarkerType(ArrayList<Label> selectedLabels, int i, boolean b, boolean b1);

        boolean isDarkTheme();
    }
}
