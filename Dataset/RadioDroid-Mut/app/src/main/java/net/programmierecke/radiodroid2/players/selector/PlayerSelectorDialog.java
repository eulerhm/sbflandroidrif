package net.programmierecke.radiodroid2.players.selector;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.lifecycle.LiveData;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import net.programmierecke.radiodroid2.R;
import net.programmierecke.radiodroid2.RadioDroidApp;
import net.programmierecke.radiodroid2.players.mpd.MPDClient;
import net.programmierecke.radiodroid2.players.mpd.MPDServerData;
import net.programmierecke.radiodroid2.players.mpd.MPDServersRepository;
import net.programmierecke.radiodroid2.service.PlayerService;
import net.programmierecke.radiodroid2.station.DataRadioStation;
import java.util.List;
import static net.programmierecke.radiodroid2.Utils.parseIntWithDefault;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class PlayerSelectorDialog extends BottomSheetDialogFragment {

    public static final String FRAGMENT_TAG = "mpd_servers_dialog_fragment";

    private MPDClient mpdClient;

    private DataRadioStation stationToPlay;

    private BroadcastReceiver updateUIReceiver;

    private RecyclerView recyclerViewServers;

    private PlayerSelectorAdapter playerSelectorAdapter;

    private MPDServersRepository serversRepository;

    private Button btnEnableMPD;

    private Button btnAddMPDServer;

    public PlayerSelectorDialog(@NonNull MPDClient mpdClient) {
        if (!ListenerUtil.mutListener.listen(1352)) {
            this.mpdClient = mpdClient;
        }
    }

    public PlayerSelectorDialog(@NonNull MPDClient mpdClient, @NonNull DataRadioStation stationToPlay) {
        if (!ListenerUtil.mutListener.listen(1353)) {
            this.mpdClient = mpdClient;
        }
        if (!ListenerUtil.mutListener.listen(1354)) {
            this.stationToPlay = stationToPlay;
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(1355)) {
            setRetainInstance(true);
        }
        View view = inflater.inflate(R.layout.dialog_mpd_servers, container, false);
        RadioDroidApp radioDroidApp = (RadioDroidApp) requireActivity().getApplication();
        if (!ListenerUtil.mutListener.listen(1356)) {
            serversRepository = radioDroidApp.getMpdClient().getMpdServersRepository();
        }
        if (!ListenerUtil.mutListener.listen(1357)) {
            recyclerViewServers = view.findViewById(R.id.recyclerViewMPDServers);
        }
        GridLayoutManager llm = new GridLayoutManager(getContext(), 2, RecyclerView.VERTICAL, false);
        if (!ListenerUtil.mutListener.listen(1358)) {
            recyclerViewServers.setLayoutManager(llm);
        }
        if (!ListenerUtil.mutListener.listen(1359)) {
            playerSelectorAdapter = new PlayerSelectorAdapter(requireContext(), stationToPlay);
        }
        if (!ListenerUtil.mutListener.listen(1362)) {
            playerSelectorAdapter.setActionListener(new PlayerSelectorAdapter.ActionListener() {

                @Override
                public void editServer(@NonNull MPDServerData mpdServerData) {
                    if (!ListenerUtil.mutListener.listen(1360)) {
                        editOrAddServer(new MPDServerData(mpdServerData));
                    }
                }

                @Override
                public void removeServer(@NonNull MPDServerData mpdServerData) {
                    if (!ListenerUtil.mutListener.listen(1361)) {
                        serversRepository.removeServer(mpdServerData);
                    }
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(1363)) {
            recyclerViewServers.setAdapter(playerSelectorAdapter);
        }
        if (!ListenerUtil.mutListener.listen(1364)) {
            btnEnableMPD = view.findViewById(R.id.btnEnableMPD);
        }
        if (!ListenerUtil.mutListener.listen(1365)) {
            btnAddMPDServer = view.findViewById(R.id.btnAddMPDServer);
        }
        if (!ListenerUtil.mutListener.listen(1366)) {
            btnEnableMPD.setOnClickListener(view12 -> {
                boolean mpdEnabled = !mpdClient.isMpdEnabled();
                mpdClient.setMPDEnabled(mpdEnabled);
                if (mpdEnabled) {
                    mpdClient.enableAutoUpdate();
                } else {
                    mpdClient.disableAutoUpdate();
                }
                updateEnableMpdButton();
            });
        }
        if (!ListenerUtil.mutListener.listen(1367)) {
            btnAddMPDServer.setOnClickListener(view1 -> editOrAddServer(null));
        }
        LiveData<List<MPDServerData>> servers = serversRepository.getAllServers();
        if (!ListenerUtil.mutListener.listen(1368)) {
            servers.observe(this, mpdServers -> playerSelectorAdapter.setEntries(mpdServers));
        }
        if (!ListenerUtil.mutListener.listen(1371)) {
            updateUIReceiver = new BroadcastReceiver() {

                @Override
                public void onReceive(Context context, Intent intent) {
                    if (!ListenerUtil.mutListener.listen(1370)) {
                        if (PlayerService.PLAYER_SERVICE_STATE_CHANGE.equals(intent.getAction())) {
                            if (!ListenerUtil.mutListener.listen(1369)) {
                                playerSelectorAdapter.notifyRadioDroidPlaybackStateChanged();
                            }
                        }
                    }
                }
            };
        }
        return view;
    }

    @Override
    public void onResume() {
        if (!ListenerUtil.mutListener.listen(1372)) {
            super.onResume();
        }
        if (!ListenerUtil.mutListener.listen(1374)) {
            if (mpdClient.isMpdEnabled()) {
                if (!ListenerUtil.mutListener.listen(1373)) {
                    mpdClient.enableAutoUpdate();
                }
            }
        }
        IntentFilter filter = new IntentFilter();
        if (!ListenerUtil.mutListener.listen(1375)) {
            filter.addAction(PlayerService.PLAYER_SERVICE_STATE_CHANGE);
        }
        if (!ListenerUtil.mutListener.listen(1376)) {
            LocalBroadcastManager.getInstance(requireContext()).registerReceiver(updateUIReceiver, filter);
        }
        if (!ListenerUtil.mutListener.listen(1377)) {
            updateEnableMpdButton();
        }
    }

    @Override
    public void onPause() {
        if (!ListenerUtil.mutListener.listen(1378)) {
            super.onPause();
        }
        if (!ListenerUtil.mutListener.listen(1379)) {
            mpdClient.disableAutoUpdate();
        }
        if (!ListenerUtil.mutListener.listen(1380)) {
            LocalBroadcastManager.getInstance(requireContext()).unregisterReceiver(updateUIReceiver);
        }
    }

    private void updateEnableMpdButton() {
        if (!ListenerUtil.mutListener.listen(1383)) {
            if (mpdClient.isMpdEnabled()) {
                if (!ListenerUtil.mutListener.listen(1382)) {
                    btnEnableMPD.setText(R.string.action_disable_mpd);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(1381)) {
                    btnEnableMPD.setText(R.string.action_enable_mpd);
                }
            }
        }
    }

    private void editOrAddServer(@Nullable final MPDServerData server) {
        LayoutInflater inflater = getLayoutInflater();
        View server_view = inflater.inflate(R.layout.layout_server_alert, null);
        final EditText editName = server_view.findViewById(R.id.mpd_server_name);
        final EditText editHostnameH = server_view.findViewById(R.id.mpd_server_hostname);
        final EditText editPassword = server_view.findViewById(R.id.mpd_server_password);
        final EditText editPort = server_view.findViewById(R.id.mpd_server_port);
        if (!ListenerUtil.mutListener.listen(1387)) {
            if (server != null) {
                if (!ListenerUtil.mutListener.listen(1384)) {
                    editName.setText(server.name);
                }
                if (!ListenerUtil.mutListener.listen(1385)) {
                    editHostnameH.setText(server.hostname);
                }
                if (!ListenerUtil.mutListener.listen(1386)) {
                    editPort.setText(String.valueOf(server.port));
                }
            }
        }
        final AlertDialog dialog = new AlertDialog.Builder(requireContext()).setView(server_view).setPositiveButton(R.string.alert_select_mpd_server_save, null).setNeutralButton(R.string.alert_select_mpd_server_remove, null).setTitle(R.string.alert_add_or_edit_mpd_server).create();
        if (!ListenerUtil.mutListener.listen(1388)) {
            dialog.setOnShowListener(dialogInterface -> {
                Button btnPositive = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
                Button btnRemove = dialog.getButton(AlertDialog.BUTTON_NEUTRAL);
                btnPositive.setOnClickListener(v -> {
                    String serverName = editName.getText().toString().trim();
                    String hostname = editHostnameH.getText().toString().trim();
                    String password = editPassword.getText().toString().trim();
                    int port = parseIntWithDefault(editPort.getText().toString().trim(), 0);
                    if (serverName.isEmpty() || hostname.isEmpty() || port == 0) {
                        return;
                    }
                    if (server != null) {
                        server.name = serverName;
                        server.hostname = hostname;
                        server.port = port;
                        server.password = password;
                        serversRepository.updatePersistentData(server);
                    } else {
                        MPDServerData server1 = new MPDServerData(serverName, hostname, port, password);
                        serversRepository.addServer(server1);
                    }
                    mpdClient.launchQuickCheck();
                    dialog.cancel();
                });
                btnRemove.setOnClickListener(v -> {
                    if (server != null) {
                        serversRepository.removeServer(server);
                        mpdClient.launchQuickCheck();
                    }
                    dialog.cancel();
                });
            });
        }
        if (!ListenerUtil.mutListener.listen(1389)) {
            editName.setOnFocusChangeListener((v, hasFocus) -> {
                if (hasFocus)
                    dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
            });
        }
        if (!ListenerUtil.mutListener.listen(1390)) {
            editName.requestFocus();
        }
        if (!ListenerUtil.mutListener.listen(1391)) {
            dialog.show();
        }
    }
}
