package net.programmierecke.radiodroid2.history;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.paging.PagedList;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;
import net.programmierecke.radiodroid2.R;
import net.programmierecke.radiodroid2.Utils;
import net.programmierecke.radiodroid2.service.PlayerServiceUtil;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class TrackHistoryAdapter extends PagedListAdapter<TrackHistoryEntry, TrackHistoryAdapter.TrackHistoryItemViewHolder> {

    class TrackHistoryItemViewHolder extends RecyclerView.ViewHolder {

        final View rootview;

        final ImageView imageViewStationIcon;

        final TextView textViewTrackName;

        final TextView textViewTrackArtist;

        private TrackHistoryItemViewHolder(View itemView) {
            super(itemView);
            rootview = itemView;
            imageViewStationIcon = itemView.findViewById(R.id.imageViewStationIcon);
            textViewTrackName = itemView.findViewById(R.id.textViewTrackName);
            textViewTrackArtist = itemView.findViewById(R.id.textViewTrackArtist);
        }
    }

    private Context context;

    private FragmentActivity activity;

    private final LayoutInflater inflater;

    private boolean shouldLoadIcons;

    private Drawable stationImagePlaceholder;

    public TrackHistoryAdapter(FragmentActivity activity) {
        super(DIFF_CALLBACK);
        if (!ListenerUtil.mutListener.listen(411)) {
            this.activity = activity;
        }
        if (!ListenerUtil.mutListener.listen(412)) {
            this.context = activity;
        }
        inflater = LayoutInflater.from(context);
        if (!ListenerUtil.mutListener.listen(413)) {
            stationImagePlaceholder = ContextCompat.getDrawable(context, R.drawable.ic_photo_24dp);
        }
    }

    @NonNull
    @Override
    public TrackHistoryItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.list_item_history_track_item, parent, false);
        return new TrackHistoryItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final TrackHistoryItemViewHolder holder, int position) {
        final TrackHistoryEntry historyEntry = getItem(position);
        if (!ListenerUtil.mutListener.listen(414)) {
            // null if a placeholder
            if (historyEntry == null) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(419)) {
            if (shouldLoadIcons) {
                if (!ListenerUtil.mutListener.listen(418)) {
                    if (!TextUtils.isEmpty(historyEntry.stationIconUrl)) {
                        if (!ListenerUtil.mutListener.listen(417)) {
                            // setupIcon(useCircularIcons, holder.imageViewIcon, holder.transparentImageView);
                            PlayerServiceUtil.getStationIcon(holder.imageViewStationIcon, historyEntry.stationIconUrl);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(416)) {
                            holder.imageViewStationIcon.setImageDrawable(stationImagePlaceholder);
                        }
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(415)) {
                    holder.imageViewStationIcon.setVisibility(View.GONE);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(420)) {
            holder.textViewTrackName.setText(historyEntry.track);
        }
        if (!ListenerUtil.mutListener.listen(421)) {
            holder.textViewTrackArtist.setText(historyEntry.artist);
        }
        if (!ListenerUtil.mutListener.listen(422)) {
            holder.textViewTrackName.setSelected(true);
        }
        if (!ListenerUtil.mutListener.listen(423)) {
            holder.textViewTrackArtist.setSelected(true);
        }
        if (!ListenerUtil.mutListener.listen(424)) {
            holder.rootview.setOnClickListener(view -> showTrackInfoDialog(historyEntry));
        }
    }

    @Override
    public void submitList(PagedList<TrackHistoryEntry> pagedList) {
        if (!ListenerUtil.mutListener.listen(425)) {
            shouldLoadIcons = Utils.shouldLoadIcons(context);
        }
        if (!ListenerUtil.mutListener.listen(426)) {
            super.submitList(pagedList);
        }
    }

    private void showTrackInfoDialog(final TrackHistoryEntry historyEntry) {
        TrackHistoryInfoDialog trackHistoryInfoDialog = new TrackHistoryInfoDialog(historyEntry);
        if (!ListenerUtil.mutListener.listen(427)) {
            trackHistoryInfoDialog.show(activity.getSupportFragmentManager(), TrackHistoryInfoDialog.FRAGMENT_TAG);
        }
    }

    private static DiffUtil.ItemCallback<TrackHistoryEntry> DIFF_CALLBACK = new DiffUtil.ItemCallback<TrackHistoryEntry>() {

        @Override
        public boolean areItemsTheSame(TrackHistoryEntry oldEntry, TrackHistoryEntry newEntry) {
            return oldEntry.uid == newEntry.uid;
        }

        @Override
        public boolean areContentsTheSame(TrackHistoryEntry oldEntry, @NonNull TrackHistoryEntry newEntry) {
            return oldEntry.equals(newEntry);
        }
    };
}
