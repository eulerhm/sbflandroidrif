package net.programmierecke.radiodroid2;

import android.app.Activity;
import android.app.Application;
import android.content.*;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.*;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.preference.PreferenceManager;
import net.programmierecke.radiodroid2.history.TrackHistoryRepository;
import net.programmierecke.radiodroid2.players.mpd.MPDClient;
import net.programmierecke.radiodroid2.service.PauseReason;
import net.programmierecke.radiodroid2.service.PlayerService;
import net.programmierecke.radiodroid2.service.PlayerServiceUtil;
import net.programmierecke.radiodroid2.station.DataRadioStation;
import net.programmierecke.radiodroid2.station.StationActions;
import net.programmierecke.radiodroid2.station.live.StreamLiveInfo;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class FragmentPlayerSmall extends Fragment {

    private TrackHistoryRepository trackHistoryRepository;

    public enum Role {

        HEADER, PLAYER
    }

    public interface Callback {

        void onToggle();
    }

    private MPDClient mpdClient;

    private BroadcastReceiver updateUIReceiver;

    private Callback callback;

    private Role role = Role.PLAYER;

    private TextView textViewStationName;

    private TextView textViewLiveInfo;

    private TextView textViewLiveInfoBig;

    private ImageView imageViewIcon;

    private ImageButton buttonPlay;

    private ImageButton buttonMore;

    private boolean firstPlayAttempted = false;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.layout_player_small, container, false);
        RadioDroidApp radioDroidApp = (RadioDroidApp) requireActivity().getApplication();
        if (!ListenerUtil.mutListener.listen(3658)) {
            mpdClient = radioDroidApp.getMpdClient();
        }
        if (!ListenerUtil.mutListener.listen(3659)) {
            trackHistoryRepository = radioDroidApp.getTrackHistoryRepository();
        }
        if (!ListenerUtil.mutListener.listen(3664)) {
            updateUIReceiver = new BroadcastReceiver() {

                @Override
                public void onReceive(Context context, Intent intent) {
                    if (!ListenerUtil.mutListener.listen(3663)) {
                        switch(intent.getAction()) {
                            // }
                            case PlayerService.PLAYER_SERVICE_STATE_CHANGE:
                                {
                                    if (!ListenerUtil.mutListener.listen(3660)) {
                                        fullUpdate();
                                    }
                                }
                            case PlayerService.PLAYER_SERVICE_META_UPDATE:
                                {
                                    if (!ListenerUtil.mutListener.listen(3661)) {
                                        fullUpdate();
                                    }
                                }
                            case PlayerService.PLAYER_SERVICE_BOUND:
                                {
                                    if (!ListenerUtil.mutListener.listen(3662)) {
                                        tryPlayAtStart();
                                    }
                                }
                        }
                    }
                }
            };
        }
        if (!ListenerUtil.mutListener.listen(3665)) {
            textViewStationName = view.findViewById(R.id.textViewStationName);
        }
        if (!ListenerUtil.mutListener.listen(3666)) {
            textViewLiveInfo = view.findViewById(R.id.textViewLiveInfo);
        }
        if (!ListenerUtil.mutListener.listen(3667)) {
            textViewLiveInfoBig = view.findViewById(R.id.textViewLiveInfoBig);
        }
        if (!ListenerUtil.mutListener.listen(3668)) {
            imageViewIcon = view.findViewById(R.id.playerRadioImage);
        }
        if (!ListenerUtil.mutListener.listen(3669)) {
            buttonPlay = view.findViewById(R.id.buttonPlay);
        }
        if (!ListenerUtil.mutListener.listen(3670)) {
            buttonMore = view.findViewById(R.id.buttonMore);
        }
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(3671)) {
            super.onActivityCreated(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(3672)) {
            requireActivity().getApplication().registerActivityLifecycleCallbacks(new LifecycleCallbacks());
        }
        if (!ListenerUtil.mutListener.listen(3673)) {
            buttonPlay.setOnClickListener(v -> {
                if (PlayerServiceUtil.isPlaying()) {
                    if (PlayerServiceUtil.isRecording()) {
                        PlayerServiceUtil.stopRecording();
                    }
                    PlayerServiceUtil.pause(PauseReason.USER);
                } else {
                    playLastFromHistory();
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(3674)) {
            buttonMore.setOnClickListener(view -> {
                DataRadioStation station = Utils.getCurrentOrLastStation(requireContext());
                if (station == null) {
                    return;
                }
                RadioDroidApp radioDroidApp = (RadioDroidApp) requireActivity().getApplication();
                FavouriteManager favouriteManager = radioDroidApp.getFavouriteManager();
                boolean isInFavorites = favouriteManager.has(station.StationUuid);
                showPlayerMenu(station, isInFavorites);
            });
        }
        if (!ListenerUtil.mutListener.listen(3675)) {
            requireView().setOnClickListener(view -> {
                if (callback != null) {
                    callback.onToggle();
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(3676)) {
            tryPlayAtStart();
        }
        if (!ListenerUtil.mutListener.listen(3677)) {
            fullUpdate();
        }
        if (!ListenerUtil.mutListener.listen(3678)) {
            setupStationIcon();
        }
    }

    @Override
    public void onResume() {
        if (!ListenerUtil.mutListener.listen(3679)) {
            super.onResume();
        }
        IntentFilter filter = new IntentFilter();
        if (!ListenerUtil.mutListener.listen(3680)) {
            filter.addAction(PlayerService.PLAYER_SERVICE_STATE_CHANGE);
        }
        if (!ListenerUtil.mutListener.listen(3681)) {
            filter.addAction(PlayerService.PLAYER_SERVICE_META_UPDATE);
        }
        if (!ListenerUtil.mutListener.listen(3682)) {
            filter.addAction(PlayerService.PLAYER_SERVICE_BOUND);
        }
        if (!ListenerUtil.mutListener.listen(3683)) {
            LocalBroadcastManager.getInstance(requireContext()).registerReceiver(updateUIReceiver, filter);
        }
        if (!ListenerUtil.mutListener.listen(3684)) {
            fullUpdate();
        }
    }

    @Override
    public void onPause() {
        if (!ListenerUtil.mutListener.listen(3685)) {
            super.onPause();
        }
        if (!ListenerUtil.mutListener.listen(3686)) {
            LocalBroadcastManager.getInstance(requireContext()).unregisterReceiver(updateUIReceiver);
        }
    }

    @Override
    public void onDestroy() {
        if (!ListenerUtil.mutListener.listen(3687)) {
            super.onDestroy();
        }
    }

    public void setCallback(Callback callback) {
        if (!ListenerUtil.mutListener.listen(3688)) {
            this.callback = callback;
        }
    }

    public void setRole(Role role) {
        if (!ListenerUtil.mutListener.listen(3689)) {
            this.role = role;
        }
        if (!ListenerUtil.mutListener.listen(3690)) {
            fullUpdate();
        }
    }

    private void playLastFromHistory() {
        RadioDroidApp radioDroidApp = (RadioDroidApp) requireActivity().getApplication();
        DataRadioStation station = PlayerServiceUtil.getCurrentStation();
        if (!ListenerUtil.mutListener.listen(3692)) {
            if (station == null) {
                HistoryManager historyManager = radioDroidApp.getHistoryManager();
                if (!ListenerUtil.mutListener.listen(3691)) {
                    station = historyManager.getFirst();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(3695)) {
            if ((ListenerUtil.mutListener.listen(3693) ? (station != null || !PlayerServiceUtil.isPlaying()) : (station != null && !PlayerServiceUtil.isPlaying()))) {
                if (!ListenerUtil.mutListener.listen(3694)) {
                    Utils.showPlaySelection(radioDroidApp, station, getActivity().getSupportFragmentManager());
                }
            }
        }
    }

    private void tryPlayAtStart() {
        boolean play = false;
        if (!ListenerUtil.mutListener.listen(3700)) {
            if ((ListenerUtil.mutListener.listen(3696) ? (!firstPlayAttempted || PlayerServiceUtil.isServiceBound()) : (!firstPlayAttempted && PlayerServiceUtil.isServiceBound()))) {
                if (!ListenerUtil.mutListener.listen(3697)) {
                    firstPlayAttempted = true;
                }
                if (!ListenerUtil.mutListener.listen(3699)) {
                    if (!PlayerServiceUtil.isPlaying()) {
                        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(requireContext().getApplicationContext());
                        if (!ListenerUtil.mutListener.listen(3698)) {
                            play = sharedPreferences.getBoolean("auto_play_on_startup", false);
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(3702)) {
            if (play) {
                if (!ListenerUtil.mutListener.listen(3701)) {
                    playLastFromHistory();
                }
            }
        }
    }

    private void setupStationIcon() {
        boolean useCircularIcons = PreferenceManager.getDefaultSharedPreferences(requireContext().getApplicationContext()).getBoolean("circular_icons", false);
        if (!ListenerUtil.mutListener.listen(3704)) {
            if (useCircularIcons) {
                if (!ListenerUtil.mutListener.listen(3703)) {
                    imageViewIcon.setBackgroundColor(requireContext().getResources().getColor(android.R.color.black));
                }
            }
        }
        ImageView transparentCircle = requireView().findViewById(R.id.transparentCircle);
        if (!ListenerUtil.mutListener.listen(3705)) {
            transparentCircle.setVisibility(useCircularIcons ? View.VISIBLE : View.GONE);
        }
    }

    private void fullUpdate() {
        if (!ListenerUtil.mutListener.listen(3710)) {
            if (PlayerServiceUtil.isPlaying()) {
                if (!ListenerUtil.mutListener.listen(3708)) {
                    buttonPlay.setImageResource(R.drawable.ic_pause_circle);
                }
                if (!ListenerUtil.mutListener.listen(3709)) {
                    buttonPlay.setContentDescription(getResources().getString(R.string.detail_pause));
                }
            } else {
                if (!ListenerUtil.mutListener.listen(3706)) {
                    buttonPlay.setImageResource(R.drawable.ic_play_circle);
                }
                if (!ListenerUtil.mutListener.listen(3707)) {
                    buttonPlay.setContentDescription(getResources().getString(R.string.detail_play));
                }
            }
        }
        DataRadioStation station = Utils.getCurrentOrLastStation(requireContext());
        final String stationName = station != null ? station.Name : "";
        if (!ListenerUtil.mutListener.listen(3711)) {
            textViewStationName.setText(stationName);
        }
        StreamLiveInfo liveInfo = PlayerServiceUtil.getMetadataLive();
        String streamTitle = liveInfo.getTitle();
        if (!ListenerUtil.mutListener.listen(3717)) {
            if (!TextUtils.isEmpty(streamTitle)) {
                if (!ListenerUtil.mutListener.listen(3714)) {
                    textViewLiveInfo.setVisibility(View.VISIBLE);
                }
                if (!ListenerUtil.mutListener.listen(3715)) {
                    textViewLiveInfo.setText(streamTitle);
                }
                if (!ListenerUtil.mutListener.listen(3716)) {
                    textViewStationName.setGravity(Gravity.BOTTOM);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(3712)) {
                    textViewLiveInfo.setVisibility(View.GONE);
                }
                if (!ListenerUtil.mutListener.listen(3713)) {
                    textViewStationName.setGravity(Gravity.CENTER_VERTICAL);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(3718)) {
            textViewLiveInfoBig.setText(stationName);
        }
        if (!ListenerUtil.mutListener.listen(3725)) {
            if (!Utils.shouldLoadIcons(getContext())) {
                if (!ListenerUtil.mutListener.listen(3724)) {
                    imageViewIcon.setVisibility(View.GONE);
                }
            } else if ((ListenerUtil.mutListener.listen(3719) ? (station != null || station.hasIcon()) : (station != null && station.hasIcon()))) {
                if (!ListenerUtil.mutListener.listen(3722)) {
                    imageViewIcon.setVisibility(View.VISIBLE);
                }
                if (!ListenerUtil.mutListener.listen(3723)) {
                    PlayerServiceUtil.getStationIcon(imageViewIcon, station.IconUrl);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(3720)) {
                    imageViewIcon.setVisibility(View.VISIBLE);
                }
                if (!ListenerUtil.mutListener.listen(3721)) {
                    imageViewIcon.setImageResource(R.drawable.ic_launcher);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(3735)) {
            if (role == Role.PLAYER) {
                if (!ListenerUtil.mutListener.listen(3731)) {
                    buttonPlay.setVisibility(View.VISIBLE);
                }
                if (!ListenerUtil.mutListener.listen(3732)) {
                    buttonMore.setVisibility(View.GONE);
                }
                if (!ListenerUtil.mutListener.listen(3733)) {
                    textViewStationName.setVisibility(View.VISIBLE);
                }
                if (!ListenerUtil.mutListener.listen(3734)) {
                    textViewLiveInfoBig.setVisibility(View.GONE);
                }
            } else if (role == Role.HEADER) {
                if (!ListenerUtil.mutListener.listen(3726)) {
                    buttonPlay.setVisibility(View.GONE);
                }
                if (!ListenerUtil.mutListener.listen(3727)) {
                    buttonMore.setVisibility(View.VISIBLE);
                }
                if (!ListenerUtil.mutListener.listen(3728)) {
                    textViewLiveInfo.setVisibility(View.GONE);
                }
                if (!ListenerUtil.mutListener.listen(3729)) {
                    textViewStationName.setVisibility(View.GONE);
                }
                if (!ListenerUtil.mutListener.listen(3730)) {
                    textViewLiveInfoBig.setVisibility(View.VISIBLE);
                }
            }
        }
    }

    private void showPlayerMenu(@NonNull final DataRadioStation currentStation, final boolean stationIsInFavourites) {
        final PopupMenu dropDownMenu = new PopupMenu(getContext(), buttonMore);
        if (!ListenerUtil.mutListener.listen(3736)) {
            dropDownMenu.getMenuInflater().inflate(R.menu.menu_player, dropDownMenu.getMenu());
        }
        if (!ListenerUtil.mutListener.listen(3737)) {
            dropDownMenu.setOnMenuItemClickListener(menuItem -> {
                switch(menuItem.getItemId()) {
                    case R.id.action_homepage:
                        {
                            StationActions.showWebLinks(requireActivity(), currentStation);
                            break;
                        }
                    case R.id.action_share:
                        {
                            StationActions.share(requireContext(), currentStation);
                            break;
                        }
                    case R.id.action_set_alarm:
                        {
                            StationActions.setAsAlarm(requireActivity(), currentStation);
                            break;
                        }
                    case R.id.action_delete_stream_history:
                        {
                            trackHistoryRepository.deleteHistory();
                            break;
                        }
                }
                return true;
            });
        }
        if (!ListenerUtil.mutListener.listen(3738)) {
            dropDownMenu.show();
        }
    }

    class LifecycleCallbacks implements Application.ActivityLifecycleCallbacks {

        @Override
        public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        }

        @Override
        public void onActivityStarted(Activity activity) {
            Context ctx = getContext();
            if (!ListenerUtil.mutListener.listen(3739)) {
                if (ctx == null) {
                    return;
                }
            }
            if (!ListenerUtil.mutListener.listen(3740)) {
                tryPlayAtStart();
            }
        }

        @Override
        public void onActivityResumed(Activity activity) {
        }

        @Override
        public void onActivityPaused(Activity activity) {
        }

        @Override
        public void onActivityStopped(Activity activity) {
        }

        @Override
        public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
        }

        @Override
        public void onActivityDestroyed(Activity activity) {
        }
    }
}
