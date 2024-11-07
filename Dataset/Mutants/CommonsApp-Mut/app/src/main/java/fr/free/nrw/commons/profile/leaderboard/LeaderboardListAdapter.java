package fr.free.nrw.commons.profile.leaderboard;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import com.facebook.drawee.view.SimpleDraweeView;
import fr.free.nrw.commons.R;
import fr.free.nrw.commons.Utils;
import fr.free.nrw.commons.profile.ProfileActivity;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * This class extends RecyclerView.Adapter and creates the List section of the leaderboard
 */
public class LeaderboardListAdapter extends PagedListAdapter<LeaderboardList, LeaderboardListAdapter.ListViewHolder> {

    public LeaderboardListAdapter() {
        super(LeaderboardList.DIFF_CALLBACK);
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {

        TextView rank;

        SimpleDraweeView avatar;

        TextView username;

        TextView count;

        public ListViewHolder(View itemView) {
            super(itemView);
            if (!ListenerUtil.mutListener.listen(5348)) {
                this.rank = itemView.findViewById(R.id.user_rank);
            }
            if (!ListenerUtil.mutListener.listen(5349)) {
                this.avatar = itemView.findViewById(R.id.user_avatar);
            }
            if (!ListenerUtil.mutListener.listen(5350)) {
                this.username = itemView.findViewById(R.id.user_name);
            }
            if (!ListenerUtil.mutListener.listen(5351)) {
                this.count = itemView.findViewById(R.id.user_count);
            }
        }

        /**
         * This method will return the Context
         * @return Context
         */
        public Context getContext() {
            return itemView.getContext();
        }
    }

    /**
     * Overrides the onCreateViewHolder and inflates the recyclerview list item layout
     * @param parent
     * @param viewType
     * @return
     */
    @NonNull
    @Override
    public LeaderboardListAdapter.ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.leaderboard_list_element, parent, false);
        return new ListViewHolder(view);
    }

    /**
     * Overrides the onBindViewHolder Set the view at the specific position with the specific value
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull LeaderboardListAdapter.ListViewHolder holder, int position) {
        TextView rank = holder.rank;
        SimpleDraweeView avatar = holder.avatar;
        TextView username = holder.username;
        TextView count = holder.count;
        if (!ListenerUtil.mutListener.listen(5352)) {
            rank.setText(getItem(position).getRank().toString());
        }
        if (!ListenerUtil.mutListener.listen(5353)) {
            avatar.setImageURI(Uri.parse(getItem(position).getAvatar()));
        }
        if (!ListenerUtil.mutListener.listen(5354)) {
            username.setText(getItem(position).getUsername());
        }
        if (!ListenerUtil.mutListener.listen(5355)) {
            count.setText(getItem(position).getCategoryCount().toString());
        }
        if (!ListenerUtil.mutListener.listen(5356)) {
            /*
          Now that we have our in app profile-section, lets take the user there
         */
            holder.itemView.setOnClickListener(view -> {
                if (view.getContext() instanceof ProfileActivity) {
                    ((Activity) (view.getContext())).finish();
                }
                ProfileActivity.startYourself(view.getContext(), getItem(position).getUsername(), true);
            });
        }
    }
}
