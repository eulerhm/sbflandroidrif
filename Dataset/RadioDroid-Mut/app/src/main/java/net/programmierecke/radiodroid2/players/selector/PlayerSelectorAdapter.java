package net.programmierecke.radiodroid2.players.selector;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;
import net.programmierecke.radiodroid2.CastHandler;
import net.programmierecke.radiodroid2.R;
import net.programmierecke.radiodroid2.RadioDroidApp;
import net.programmierecke.radiodroid2.Utils;
import net.programmierecke.radiodroid2.players.PlayStationTask;
import net.programmierecke.radiodroid2.players.mpd.MPDClient;
import net.programmierecke.radiodroid2.players.mpd.MPDServerData;
import net.programmierecke.radiodroid2.players.mpd.tasks.MPDChangeVolumeTask;
import net.programmierecke.radiodroid2.players.mpd.tasks.MPDPauseTask;
import net.programmierecke.radiodroid2.players.mpd.tasks.MPDResumeTask;
import net.programmierecke.radiodroid2.players.mpd.tasks.MPDStopTask;
import net.programmierecke.radiodroid2.service.PauseReason;
import net.programmierecke.radiodroid2.service.PlayerService;
import net.programmierecke.radiodroid2.service.PlayerServiceUtil;
import net.programmierecke.radiodroid2.station.DataRadioStation;
import java.util.ArrayList;
import java.util.List;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class PlayerSelectorAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    interface ActionListener {

        void editServer(@NonNull MPDServerData mpdServerData);

        void removeServer(@NonNull MPDServerData mpdServerData);
    }

    private class MPDServerItemViewHolder extends RecyclerView.ViewHolder {

        final ImageView imgConnectionStatus;

        final TextView textViewServerName;

        final ImageButton btnPlay;

        final ImageButton btnStop;

        final ImageButton btnMore;

        final TextView textViewNoConnection;

        final AppCompatImageButton btnDecreaseVolume;

        final AppCompatImageButton btnIncreaseVolume;

        final TextView textViewCurrentVolume;

        MPDServerData mpdServerData;

        private MPDServerItemViewHolder(@NonNull View itemView) {
            super(itemView);
            imgConnectionStatus = itemView.findViewById(R.id.imgConnectionStatus);
            textViewServerName = itemView.findViewById(R.id.textViewMPDName);
            btnPlay = itemView.findViewById(R.id.buttonPlay);
            btnStop = itemView.findViewById(R.id.buttonStop);
            btnMore = itemView.findViewById(R.id.buttonMore);
            textViewNoConnection = itemView.findViewById(R.id.textViewNoConnection);
            btnDecreaseVolume = itemView.findViewById(R.id.buttonMPDDecreaseVolume);
            textViewCurrentVolume = itemView.findViewById(R.id.textViewMPDVolume);
            btnIncreaseVolume = itemView.findViewById(R.id.buttonMPDIncreaseVolume);
        }
    }

    /* Represents either "Play in RadioDroid" or "Play in external player" */
    private class PlayerItemViewHolder extends RecyclerView.ViewHolder {

        final TextView textViewDescription;

        final ImageButton btnPlay;

        public PlayerItemViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewDescription = itemView.findViewById(R.id.textViewDescription);
            btnPlay = itemView.findViewById(R.id.buttonPlay);
        }
    }

    private final LayoutInflater inflater;

    private final Context context;

    private final boolean showPlayInExternal;

    private final boolean warnOnMeteredConnection;

    private int fixedViewsCount;

    private List<Integer> viewTypes = new ArrayList<>();

    private DataRadioStation stationToPlay;

    private ActionListener actionListener;

    private MPDClient mpdClient;

    private List<MPDServerData> mpdServers;

    protected PlayerSelectorAdapter(@NonNull Context context, @Nullable DataRadioStation stationToPlay) {
        RadioDroidApp radioDroidApp = (RadioDroidApp) context.getApplicationContext();
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        if (!ListenerUtil.mutListener.listen(1261)) {
            this.mpdClient = radioDroidApp.getMpdClient();
        }
        if (!ListenerUtil.mutListener.listen(1262)) {
            this.stationToPlay = stationToPlay;
        }
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
        showPlayInExternal = (ListenerUtil.mutListener.listen(1263) ? (sharedPref.getBoolean("play_external", false) || stationToPlay != null) : (sharedPref.getBoolean("play_external", false) && stationToPlay != null));
        warnOnMeteredConnection = sharedPref.getBoolean(PlayerService.METERED_CONNECTION_WARNING_KEY, false);
        if (!ListenerUtil.mutListener.listen(1264)) {
            fixedViewsCount = 0;
        }
        if (!ListenerUtil.mutListener.listen(1267)) {
            if (stationToPlay != null) {
                if (!ListenerUtil.mutListener.listen(1265)) {
                    fixedViewsCount++;
                }
                if (!ListenerUtil.mutListener.listen(1266)) {
                    viewTypes.add(PlayerType.RADIODROID.getValue());
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(1270)) {
            if (showPlayInExternal) {
                if (!ListenerUtil.mutListener.listen(1268)) {
                    fixedViewsCount++;
                }
                if (!ListenerUtil.mutListener.listen(1269)) {
                    viewTypes.add(PlayerType.EXTERNAL.getValue());
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(1273)) {
            if (radioDroidApp.getCastHandler().isCastSessionAvailable()) {
                if (!ListenerUtil.mutListener.listen(1271)) {
                    fixedViewsCount++;
                }
                if (!ListenerUtil.mutListener.listen(1272)) {
                    viewTypes.add(PlayerType.CAST.getValue());
                }
            }
        }
    }

    public void setActionListener(ActionListener actionListener) {
        if (!ListenerUtil.mutListener.listen(1274)) {
            this.actionListener = actionListener;
        }
    }

    public void notifyRadioDroidPlaybackStateChanged() {
        if (!ListenerUtil.mutListener.listen(1282)) {
            if (stationToPlay != null) {
                int pos = viewTypes.indexOf(PlayerType.RADIODROID.getValue());
                if (!ListenerUtil.mutListener.listen(1281)) {
                    if ((ListenerUtil.mutListener.listen(1279) ? (pos >= -1) : (ListenerUtil.mutListener.listen(1278) ? (pos <= -1) : (ListenerUtil.mutListener.listen(1277) ? (pos > -1) : (ListenerUtil.mutListener.listen(1276) ? (pos < -1) : (ListenerUtil.mutListener.listen(1275) ? (pos == -1) : (pos != -1))))))) {
                        if (!ListenerUtil.mutListener.listen(1280)) {
                            notifyItemChanged(pos);
                        }
                    }
                }
            }
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (!ListenerUtil.mutListener.listen(1283)) {
            if (viewType != PlayerType.MPD_SERVER.getValue()) {
                View itemView = inflater.inflate(R.layout.list_item_play_in, parent, false);
                return new PlayerItemViewHolder(itemView);
            }
        }
        View itemView = inflater.inflate(R.layout.list_item_mpd_server, parent, false);
        return new MPDServerItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, int position) {
        if (!ListenerUtil.mutListener.listen(1286)) {
            if (holder.getItemViewType() == PlayerType.MPD_SERVER.getValue()) {
                if (!ListenerUtil.mutListener.listen(1285)) {
                    bindViewHolder((MPDServerItemViewHolder) holder, position);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(1284)) {
                    bindViewHolder((PlayerItemViewHolder) holder, position);
                }
            }
        }
    }

    private void bindViewHolder(@NonNull final PlayerItemViewHolder holder, int position) {
        if (!ListenerUtil.mutListener.listen(1298)) {
            if (holder.getItemViewType() == PlayerType.RADIODROID.getValue()) {
                if (!ListenerUtil.mutListener.listen(1291)) {
                    holder.textViewDescription.setText(R.string.app_name);
                }
                if (!ListenerUtil.mutListener.listen(1296)) {
                    if (PlayerServiceUtil.isPlaying()) {
                        if (!ListenerUtil.mutListener.listen(1294)) {
                            holder.btnPlay.setImageResource(R.drawable.ic_pause_circle);
                        }
                        if (!ListenerUtil.mutListener.listen(1295)) {
                            holder.btnPlay.setContentDescription(context.getResources().getString(R.string.detail_pause));
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(1292)) {
                            holder.btnPlay.setImageResource(R.drawable.ic_play_circle);
                        }
                        if (!ListenerUtil.mutListener.listen(1293)) {
                            holder.btnPlay.setContentDescription(context.getString(R.string.detail_play));
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(1297)) {
                    holder.btnPlay.setOnClickListener(view -> {
                        if (PlayerServiceUtil.isPlaying()) {
                            if (PlayerServiceUtil.isRecording()) {
                                PlayerServiceUtil.stopRecording();
                            }
                            PlayerServiceUtil.pause(PauseReason.USER);
                        } else {
                            Utils.playAndWarnIfMetered((RadioDroidApp) context.getApplicationContext(), stationToPlay, PlayerType.RADIODROID, () -> Utils.play((RadioDroidApp) context.getApplicationContext(), stationToPlay));
                        }
                    });
                }
            } else if (holder.getItemViewType() == PlayerType.EXTERNAL.getValue()) {
                if (!ListenerUtil.mutListener.listen(1289)) {
                    holder.textViewDescription.setText(R.string.action_play_in_external);
                }
                if (!ListenerUtil.mutListener.listen(1290)) {
                    holder.btnPlay.setOnClickListener(v -> Utils.playAndWarnIfMetered((RadioDroidApp) context.getApplicationContext(), stationToPlay, PlayerType.EXTERNAL, () -> PlayStationTask.playExternal(stationToPlay, context).execute()));
                }
            } else if (holder.getItemViewType() == PlayerType.CAST.getValue()) {
                if (!ListenerUtil.mutListener.listen(1287)) {
                    holder.textViewDescription.setText(R.string.media_route_menu_title);
                }
                if (!ListenerUtil.mutListener.listen(1288)) {
                    holder.btnPlay.setOnClickListener(view -> PlayStationTask.playCAST(stationToPlay, context).execute());
                }
            }
        }
    }

    private void bindViewHolder(@NonNull final MPDServerItemViewHolder holder, int position) {
        final MPDServerData mpdServerData = mpdServers.get(translatePosition(position));
        if (!ListenerUtil.mutListener.listen(1299)) {
            holder.mpdServerData = mpdServerData;
        }
        if (!ListenerUtil.mutListener.listen(1300)) {
            holder.textViewServerName.setText(mpdServerData.name);
        }
        if (!ListenerUtil.mutListener.listen(1310)) {
            if (holder.mpdServerData.connected) {
                if (!ListenerUtil.mutListener.listen(1305)) {
                    holder.btnPlay.setVisibility(View.VISIBLE);
                }
                if (!ListenerUtil.mutListener.listen(1306)) {
                    holder.textViewNoConnection.setVisibility(View.GONE);
                }
                if (!ListenerUtil.mutListener.listen(1307)) {
                    holder.textViewCurrentVolume.setText(Integer.toString(mpdServerData.volume));
                }
                if (!ListenerUtil.mutListener.listen(1308)) {
                    holder.textViewCurrentVolume.setVisibility(View.VISIBLE);
                }
                if (!ListenerUtil.mutListener.listen(1309)) {
                    holder.imgConnectionStatus.setImageResource(R.drawable.ic_mpd_connected_24dp);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(1301)) {
                    holder.btnPlay.setVisibility(View.GONE);
                }
                if (!ListenerUtil.mutListener.listen(1302)) {
                    holder.textViewCurrentVolume.setVisibility(View.GONE);
                }
                if (!ListenerUtil.mutListener.listen(1303)) {
                    holder.textViewNoConnection.setVisibility(View.VISIBLE);
                }
                if (!ListenerUtil.mutListener.listen(1304)) {
                    holder.imgConnectionStatus.setImageResource(R.drawable.ic_mpd_disconnected_24dp);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(1314)) {
            if ((ListenerUtil.mutListener.listen(1312) ? ((ListenerUtil.mutListener.listen(1311) ? (holder.mpdServerData.connected || stationToPlay == null) : (holder.mpdServerData.connected && stationToPlay == null)) || holder.mpdServerData.status != MPDServerData.Status.Playing) : ((ListenerUtil.mutListener.listen(1311) ? (holder.mpdServerData.connected || stationToPlay == null) : (holder.mpdServerData.connected && stationToPlay == null)) && holder.mpdServerData.status != MPDServerData.Status.Playing))) {
                if (!ListenerUtil.mutListener.listen(1313)) {
                    holder.btnPlay.setVisibility(View.GONE);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(1319)) {
            if ((ListenerUtil.mutListener.listen(1315) ? (holder.mpdServerData.connected || holder.mpdServerData.status != MPDServerData.Status.Idle) : (holder.mpdServerData.connected && holder.mpdServerData.status != MPDServerData.Status.Idle))) {
                if (!ListenerUtil.mutListener.listen(1317)) {
                    holder.btnStop.setVisibility(View.VISIBLE);
                }
                if (!ListenerUtil.mutListener.listen(1318)) {
                    holder.btnStop.setOnClickListener(view -> mpdClient.enqueueTask(mpdServerData, new MPDStopTask(null)));
                }
            } else {
                if (!ListenerUtil.mutListener.listen(1316)) {
                    holder.btnStop.setVisibility(View.GONE);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(1327)) {
            if ((ListenerUtil.mutListener.listen(1320) ? (holder.mpdServerData.connected || holder.mpdServerData.status != MPDServerData.Status.Idle) : (holder.mpdServerData.connected && holder.mpdServerData.status != MPDServerData.Status.Idle))) {
                if (!ListenerUtil.mutListener.listen(1323)) {
                    holder.btnDecreaseVolume.setVisibility(View.VISIBLE);
                }
                if (!ListenerUtil.mutListener.listen(1324)) {
                    holder.btnIncreaseVolume.setVisibility(View.VISIBLE);
                }
                if (!ListenerUtil.mutListener.listen(1325)) {
                    holder.btnDecreaseVolume.setOnClickListener(view -> mpdClient.enqueueTask(mpdServerData, new MPDChangeVolumeTask(-10, null, mpdServerData)));
                }
                if (!ListenerUtil.mutListener.listen(1326)) {
                    holder.btnIncreaseVolume.setOnClickListener(view -> mpdClient.enqueueTask(mpdServerData, new MPDChangeVolumeTask(10, null, mpdServerData)));
                }
            } else {
                if (!ListenerUtil.mutListener.listen(1321)) {
                    holder.btnDecreaseVolume.setVisibility(View.INVISIBLE);
                }
                if (!ListenerUtil.mutListener.listen(1322)) {
                    holder.btnIncreaseVolume.setVisibility(View.INVISIBLE);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(1328)) {
            holder.btnMore.setOnClickListener(view -> {
                final PopupMenu dropDownMenu = new PopupMenu(context, holder.btnMore);
                dropDownMenu.getMenuInflater().inflate(R.menu.menu_mpd_server, dropDownMenu.getMenu());
                if (stationToPlay == null) {
                    dropDownMenu.getMenu().findItem(R.id.action_play).setVisible(false);
                    dropDownMenu.getMenu().findItem(R.id.action_pause).setVisible(false);
                } else {
                    if (holder.mpdServerData.status != MPDServerData.Status.Playing) {
                        dropDownMenu.getMenu().findItem(R.id.action_pause).setVisible(false);
                    } else {
                        dropDownMenu.getMenu().findItem(R.id.action_play).setVisible(false);
                    }
                }
                dropDownMenu.setOnMenuItemClickListener(menuItem -> {
                    switch(menuItem.getItemId()) {
                        case R.id.action_edit:
                            {
                                if (actionListener != null) {
                                    actionListener.editServer(mpdServerData);
                                }
                                break;
                            }
                        case R.id.action_remove:
                            {
                                if (actionListener != null) {
                                    actionListener.removeServer(mpdServerData);
                                }
                                break;
                            }
                        case R.id.action_play:
                            {
                                PlayStationTask.playMPD(mpdClient, mpdServerData, stationToPlay, context).execute();
                                break;
                            }
                        case R.id.action_pause:
                            {
                                mpdClient.enqueueTask(mpdServerData, new MPDPauseTask(null));
                                break;
                            }
                    }
                    return true;
                });
                dropDownMenu.show();
            });
        }
        if (!ListenerUtil.mutListener.listen(1339)) {
            if (holder.mpdServerData.connected) {
                if (!ListenerUtil.mutListener.listen(1334)) {
                    if (stationToPlay != null) {
                        if (!ListenerUtil.mutListener.listen(1329)) {
                            holder.btnPlay.setContentDescription(context.getResources().getString(R.string.detail_play));
                        }
                        if (!ListenerUtil.mutListener.listen(1330)) {
                            holder.btnPlay.setImageResource(R.drawable.ic_play_circle);
                        }
                        if (!ListenerUtil.mutListener.listen(1333)) {
                            if (mpdServerData.status != MPDServerData.Status.Playing) {
                                if (!ListenerUtil.mutListener.listen(1332)) {
                                    holder.btnPlay.setOnClickListener(view -> PlayStationTask.playMPD(mpdClient, mpdServerData, stationToPlay, context).execute());
                                }
                            } else {
                                if (!ListenerUtil.mutListener.listen(1331)) {
                                    holder.btnPlay.setOnClickListener(view -> mpdClient.enqueueTask(mpdServerData, new MPDResumeTask(null)));
                                }
                            }
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(1338)) {
                    if (mpdServerData.status == MPDServerData.Status.Playing) {
                        if (!ListenerUtil.mutListener.listen(1335)) {
                            holder.btnPlay.setContentDescription(context.getResources().getString(R.string.detail_pause));
                        }
                        if (!ListenerUtil.mutListener.listen(1336)) {
                            holder.btnPlay.setImageResource(R.drawable.ic_pause_circle);
                        }
                        if (!ListenerUtil.mutListener.listen(1337)) {
                            holder.btnPlay.setOnClickListener(view -> mpdClient.enqueueTask(mpdServerData, new MPDPauseTask(null)));
                        }
                    }
                }
            }
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (!ListenerUtil.mutListener.listen(1345)) {
            if ((ListenerUtil.mutListener.listen(1344) ? (position <= fixedViewsCount) : (ListenerUtil.mutListener.listen(1343) ? (position > fixedViewsCount) : (ListenerUtil.mutListener.listen(1342) ? (position < fixedViewsCount) : (ListenerUtil.mutListener.listen(1341) ? (position != fixedViewsCount) : (ListenerUtil.mutListener.listen(1340) ? (position == fixedViewsCount) : (position >= fixedViewsCount))))))) {
                return PlayerType.MPD_SERVER.getValue();
            }
        }
        return viewTypes.get(position);
    }

    void setEntries(List<MPDServerData> mpdServers) {
        if (!ListenerUtil.mutListener.listen(1346)) {
            this.mpdServers = mpdServers;
        }
        if (!ListenerUtil.mutListener.listen(1347)) {
            notifyDataSetChanged();
        }
    }

    @Override
    public int getItemCount() {
        return mpdServers.size() + fixedViewsCount;
    }

    private int translatePosition(int position) {
        return (ListenerUtil.mutListener.listen(1351) ? (position % fixedViewsCount) : (ListenerUtil.mutListener.listen(1350) ? (position / fixedViewsCount) : (ListenerUtil.mutListener.listen(1349) ? (position * fixedViewsCount) : (ListenerUtil.mutListener.listen(1348) ? (position + fixedViewsCount) : (position - fixedViewsCount)))));
    }

    private static DiffUtil.ItemCallback<MPDServerData> DIFF_CALLBACK = new DiffUtil.ItemCallback<MPDServerData>() {

        @Override
        public boolean areItemsTheSame(MPDServerData oldEntry, MPDServerData newEntry) {
            return oldEntry.id == newEntry.id;
        }

        @Override
        public boolean areContentsTheSame(MPDServerData oldEntry, @NonNull MPDServerData newEntry) {
            return oldEntry.contentEquals(newEntry);
        }
    };
}
