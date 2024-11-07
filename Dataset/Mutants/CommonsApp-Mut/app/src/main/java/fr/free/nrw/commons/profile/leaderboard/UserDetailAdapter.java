package fr.free.nrw.commons.profile.leaderboard;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.internal.DebouncingOnClickListener;
import com.facebook.drawee.view.SimpleDraweeView;
import fr.free.nrw.commons.BuildConfig;
import fr.free.nrw.commons.R;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * This class extends RecyclerView.Adapter and creates the UserDetail section of the leaderboard
 */
public class UserDetailAdapter extends RecyclerView.Adapter<UserDetailAdapter.DataViewHolder> {

    private LeaderboardResponse leaderboardResponse;

    /**
     * Stores the username of currently logged in user.
     */
    private String currentlyLoggedInUserName = null;

    public UserDetailAdapter(LeaderboardResponse leaderboardResponse) {
        if (!ListenerUtil.mutListener.listen(5493)) {
            this.leaderboardResponse = leaderboardResponse;
        }
    }

    public class DataViewHolder extends RecyclerView.ViewHolder {

        private TextView rank;

        private SimpleDraweeView avatar;

        private TextView username;

        private TextView count;

        public DataViewHolder(@NonNull View itemView) {
            super(itemView);
            if (!ListenerUtil.mutListener.listen(5494)) {
                this.rank = itemView.findViewById(R.id.rank);
            }
            if (!ListenerUtil.mutListener.listen(5495)) {
                this.avatar = itemView.findViewById(R.id.avatar);
            }
            if (!ListenerUtil.mutListener.listen(5496)) {
                this.username = itemView.findViewById(R.id.username);
            }
            if (!ListenerUtil.mutListener.listen(5497)) {
                this.count = itemView.findViewById(R.id.count);
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
     * Overrides the onCreateViewHolder and sets the view with leaderboard user element layout
     * @param parent
     * @param viewType
     * @return
     */
    @NonNull
    @Override
    public UserDetailAdapter.DataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.leaderboard_user_element, parent, false);
        return new DataViewHolder(view);
    }

    /**
     * Overrides the onBindViewHolder Set the view at the specific position with the specific value
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull UserDetailAdapter.DataViewHolder holder, int position) {
        TextView rank = holder.rank;
        SimpleDraweeView avatar = holder.avatar;
        TextView username = holder.username;
        TextView count = holder.count;
        if (!ListenerUtil.mutListener.listen(5498)) {
            rank.setText(String.format("%s %d", holder.getContext().getResources().getString(R.string.rank_prefix), leaderboardResponse.getRank()));
        }
        if (!ListenerUtil.mutListener.listen(5499)) {
            avatar.setImageURI(Uri.parse(leaderboardResponse.getAvatar()));
        }
        if (!ListenerUtil.mutListener.listen(5500)) {
            username.setText(leaderboardResponse.getUsername());
        }
        if (!ListenerUtil.mutListener.listen(5501)) {
            count.setText(String.format("%s %d", holder.getContext().getResources().getString(R.string.count_prefix), leaderboardResponse.getCategoryCount()));
        }
        if (!ListenerUtil.mutListener.listen(5504)) {
            // fixing: https://github.com/commons-app/apps-android-commons/issues/47747
            if (currentlyLoggedInUserName == null) {
                // If the current login username has not been fetched yet, then fetch it.
                final AccountManager accountManager = AccountManager.get(username.getContext());
                final Account[] allAccounts = accountManager.getAccountsByType(BuildConfig.ACCOUNT_TYPE);
                if (!ListenerUtil.mutListener.listen(5503)) {
                    if (allAccounts.length != 0) {
                        if (!ListenerUtil.mutListener.listen(5502)) {
                            currentlyLoggedInUserName = allAccounts[0].name;
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(5508)) {
            if ((ListenerUtil.mutListener.listen(5505) ? (currentlyLoggedInUserName != null || currentlyLoggedInUserName.equals(leaderboardResponse.getUsername())) : (currentlyLoggedInUserName != null && currentlyLoggedInUserName.equals(leaderboardResponse.getUsername())))) {
                if (!ListenerUtil.mutListener.listen(5507)) {
                    avatar.setOnClickListener(new DebouncingOnClickListener() {

                        @Override
                        public void doClick(View v) {
                            if (!ListenerUtil.mutListener.listen(5506)) {
                                Toast.makeText(v.getContext(), R.string.set_up_avatar_toast_string, Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }
            }
        }
    }

    @Override
    public int getItemCount() {
        return 1;
    }
}
