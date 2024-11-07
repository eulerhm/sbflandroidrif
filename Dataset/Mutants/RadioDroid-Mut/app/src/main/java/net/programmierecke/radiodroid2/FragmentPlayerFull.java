package net.programmierecke.radiodroid2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.Group;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.paging.PagedList;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import com.squareup.picasso.Picasso;
import net.programmierecke.radiodroid2.history.TrackHistoryAdapter;
import net.programmierecke.radiodroid2.history.TrackHistoryEntry;
import net.programmierecke.radiodroid2.history.TrackHistoryRepository;
import net.programmierecke.radiodroid2.history.TrackHistoryViewModel;
import net.programmierecke.radiodroid2.recording.Recordable;
import net.programmierecke.radiodroid2.recording.RecordingsAdapter;
import net.programmierecke.radiodroid2.recording.RecordingsManager;
import net.programmierecke.radiodroid2.recording.RunningRecordingInfo;
import net.programmierecke.radiodroid2.service.PauseReason;
import net.programmierecke.radiodroid2.service.PlayerService;
import net.programmierecke.radiodroid2.service.PlayerServiceUtil;
import net.programmierecke.radiodroid2.station.DataRadioStation;
import net.programmierecke.radiodroid2.station.StationActions;
import net.programmierecke.radiodroid2.station.live.ShoutcastInfo;
import net.programmierecke.radiodroid2.station.live.StreamLiveInfo;
import net.programmierecke.radiodroid2.station.live.metadata.TrackMetadata;
import net.programmierecke.radiodroid2.station.live.metadata.TrackMetadataCallback;
import net.programmierecke.radiodroid2.station.live.metadata.TrackMetadataSearcher;
import net.programmierecke.radiodroid2.utils.RefreshHandler;
import net.programmierecke.radiodroid2.views.RecyclerAwareNestedScrollView;
import net.programmierecke.radiodroid2.views.TagsView;
import java.lang.ref.WeakReference;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class FragmentPlayerFull extends Fragment {

    private final String TAG = "FragmentPlayerFull";

    private static final int PERM_REQ_STORAGE_RECORD = 1001;

    /**
     * Fragment may be a part of another view which could be dragged/scrolled
     * and certain hacks may require the fragment to request them to stop
     * intercepting touch events to not end up confused.
     */
    public interface TouchInterceptListener {

        void requestDisallowInterceptTouchEvent(boolean disallow);
    }

    private TouchInterceptListener touchInterceptListener;

    private BroadcastReceiver updateUIReceiver;

    private boolean initialized = false;

    private RefreshHandler refreshHandler = new RefreshHandler();

    private TimedUpdateTask timedUpdateTask = new TimedUpdateTask(this);

    // 1 second
    private static final int TIMED_UPDATE_INTERVAL = 1000;

    private PlayerTrackMetadataCallback trackMetadataCallback;

    private TrackMetadataCallback.FailureType trackMetadataLastFailureType = null;

    private StreamLiveInfo lastLiveInfoForTrackMetadata = null;

    private RecordingsManager recordingsManager;

    private java.util.Observer recordingsObserver;

    private FavouriteManager favouriteManager;

    private FavouritesObserver favouritesObserver = new FavouritesObserver();

    private TrackHistoryRepository trackHistoryRepository;

    private TrackHistoryAdapter trackHistoryAdapter;

    private RecordingsAdapter recordingsAdapter;

    private boolean storagePermissionsDenied = false;

    private RecyclerAwareNestedScrollView scrollViewContent;

    private ViewPager pagerArtAndInfo;

    private ArtAndInfoPagerAdapter artAndInfoPagerAdapter;

    private TextView textViewGeneralInfo;

    private TextView textViewTimePlayed;

    private TextView textViewNetworkUsageInfo;

    private TextView textViewTimeCached;

    private Group groupRecordings;

    private ImageView imgRecordingIcon;

    private TextView textViewRecordingSize;

    private TextView textViewRecordingName;

    private ViewPager pagerHistoryAndRecordings;

    private HistoryAndRecordsPagerAdapter historyAndRecordsPagerAdapter;

    private TrackHistoryViewModel trackHistoryViewModel;

    private ImageButton btnPlay;

    private ImageButton btnPrev;

    private ImageButton btnNext;

    private ImageButton btnRecord;

    private ImageButton btnFavourite;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        RadioDroidApp radioDroidApp = (RadioDroidApp) requireActivity().getApplication();
        if (!ListenerUtil.mutListener.listen(4396)) {
            recordingsManager = radioDroidApp.getRecordingsManager();
        }
        if (!ListenerUtil.mutListener.listen(4397)) {
            recordingsObserver = (observable, o) -> updateRecordings();
        }
        if (!ListenerUtil.mutListener.listen(4398)) {
            favouriteManager = radioDroidApp.getFavouriteManager();
        }
        if (!ListenerUtil.mutListener.listen(4399)) {
            trackHistoryAdapter = new TrackHistoryAdapter(requireActivity());
        }
        if (!ListenerUtil.mutListener.listen(4407)) {
            trackHistoryAdapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {

                public void onItemRangeInserted(int positionStart, int itemCount) {
                    final LinearLayoutManager lm = (LinearLayoutManager) historyAndRecordsPagerAdapter.recyclerViewSongHistory.getLayoutManager();
                    if (!ListenerUtil.mutListener.listen(4406)) {
                        if ((ListenerUtil.mutListener.listen(4404) ? (lm.findFirstVisibleItemPosition() >= 2) : (ListenerUtil.mutListener.listen(4403) ? (lm.findFirstVisibleItemPosition() <= 2) : (ListenerUtil.mutListener.listen(4402) ? (lm.findFirstVisibleItemPosition() > 2) : (ListenerUtil.mutListener.listen(4401) ? (lm.findFirstVisibleItemPosition() != 2) : (ListenerUtil.mutListener.listen(4400) ? (lm.findFirstVisibleItemPosition() == 2) : (lm.findFirstVisibleItemPosition() < 2))))))) {
                            if (!ListenerUtil.mutListener.listen(4405)) {
                                historyAndRecordsPagerAdapter.recyclerViewSongHistory.scrollToPosition(0);
                            }
                        }
                    }
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(4408)) {
            trackHistoryRepository = radioDroidApp.getTrackHistoryRepository();
        }
        if (!ListenerUtil.mutListener.listen(4411)) {
            updateUIReceiver = new BroadcastReceiver() {

                @Override
                public void onReceive(Context context, Intent intent) {
                    if (!ListenerUtil.mutListener.listen(4410)) {
                        switch(intent.getAction()) {
                            // }
                            case PlayerService.PLAYER_SERVICE_STATE_CHANGE:
                                {
                                }
                            case PlayerService.PLAYER_SERVICE_META_UPDATE:
                                {
                                    if (!ListenerUtil.mutListener.listen(4409)) {
                                        fullUpdate();
                                    }
                                }
                        }
                    }
                }
            };
        }
        View view = inflater.inflate(R.layout.layout_player_full, container, false);
        if (!ListenerUtil.mutListener.listen(4412)) {
            scrollViewContent = view.findViewById(R.id.scrollViewContent);
        }
        if (!ListenerUtil.mutListener.listen(4413)) {
            pagerArtAndInfo = view.findViewById(R.id.pagerArtAndInfo);
        }
        if (!ListenerUtil.mutListener.listen(4414)) {
            artAndInfoPagerAdapter = new ArtAndInfoPagerAdapter(requireContext(), pagerArtAndInfo);
        }
        if (!ListenerUtil.mutListener.listen(4415)) {
            pagerArtAndInfo.setAdapter(artAndInfoPagerAdapter);
        }
        if (!ListenerUtil.mutListener.listen(4447)) {
            /* A hack to make horizontal ViewPager play nice with vertical ScrollView
         * Credits to https://stackoverflow.com/a/16224484/1741638
         */
            pagerArtAndInfo.setOnTouchListener(new View.OnTouchListener() {

                private static final int DRAG_THRESHOLD = 30;

                private int downX;

                private int downY;

                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    if (!ListenerUtil.mutListener.listen(4446)) {
                        switch(event.getAction()) {
                            case MotionEvent.ACTION_DOWN:
                                if (!ListenerUtil.mutListener.listen(4416)) {
                                    downX = (int) event.getRawX();
                                }
                                if (!ListenerUtil.mutListener.listen(4417)) {
                                    downY = (int) event.getRawY();
                                }
                                break;
                            case MotionEvent.ACTION_MOVE:
                                int distanceX = Math.abs((ListenerUtil.mutListener.listen(4421) ? ((int) event.getRawX() % downX) : (ListenerUtil.mutListener.listen(4420) ? ((int) event.getRawX() / downX) : (ListenerUtil.mutListener.listen(4419) ? ((int) event.getRawX() * downX) : (ListenerUtil.mutListener.listen(4418) ? ((int) event.getRawX() + downX) : ((int) event.getRawX() - downX))))));
                                int distanceY = Math.abs((ListenerUtil.mutListener.listen(4425) ? ((int) event.getRawY() % downY) : (ListenerUtil.mutListener.listen(4424) ? ((int) event.getRawY() / downY) : (ListenerUtil.mutListener.listen(4423) ? ((int) event.getRawY() * downY) : (ListenerUtil.mutListener.listen(4422) ? ((int) event.getRawY() + downY) : ((int) event.getRawY() - downY))))));
                                if (!ListenerUtil.mutListener.listen(4441)) {
                                    if ((ListenerUtil.mutListener.listen(4436) ? ((ListenerUtil.mutListener.listen(4430) ? (distanceX >= distanceY) : (ListenerUtil.mutListener.listen(4429) ? (distanceX <= distanceY) : (ListenerUtil.mutListener.listen(4428) ? (distanceX < distanceY) : (ListenerUtil.mutListener.listen(4427) ? (distanceX != distanceY) : (ListenerUtil.mutListener.listen(4426) ? (distanceX == distanceY) : (distanceX > distanceY)))))) || (ListenerUtil.mutListener.listen(4435) ? (distanceX >= DRAG_THRESHOLD) : (ListenerUtil.mutListener.listen(4434) ? (distanceX <= DRAG_THRESHOLD) : (ListenerUtil.mutListener.listen(4433) ? (distanceX < DRAG_THRESHOLD) : (ListenerUtil.mutListener.listen(4432) ? (distanceX != DRAG_THRESHOLD) : (ListenerUtil.mutListener.listen(4431) ? (distanceX == DRAG_THRESHOLD) : (distanceX > DRAG_THRESHOLD))))))) : ((ListenerUtil.mutListener.listen(4430) ? (distanceX >= distanceY) : (ListenerUtil.mutListener.listen(4429) ? (distanceX <= distanceY) : (ListenerUtil.mutListener.listen(4428) ? (distanceX < distanceY) : (ListenerUtil.mutListener.listen(4427) ? (distanceX != distanceY) : (ListenerUtil.mutListener.listen(4426) ? (distanceX == distanceY) : (distanceX > distanceY)))))) && (ListenerUtil.mutListener.listen(4435) ? (distanceX >= DRAG_THRESHOLD) : (ListenerUtil.mutListener.listen(4434) ? (distanceX <= DRAG_THRESHOLD) : (ListenerUtil.mutListener.listen(4433) ? (distanceX < DRAG_THRESHOLD) : (ListenerUtil.mutListener.listen(4432) ? (distanceX != DRAG_THRESHOLD) : (ListenerUtil.mutListener.listen(4431) ? (distanceX == DRAG_THRESHOLD) : (distanceX > DRAG_THRESHOLD))))))))) {
                                        if (!ListenerUtil.mutListener.listen(4437)) {
                                            pagerArtAndInfo.getParent().requestDisallowInterceptTouchEvent(true);
                                        }
                                        if (!ListenerUtil.mutListener.listen(4438)) {
                                            scrollViewContent.getParent().requestDisallowInterceptTouchEvent(false);
                                        }
                                        if (!ListenerUtil.mutListener.listen(4440)) {
                                            if (touchInterceptListener != null) {
                                                if (!ListenerUtil.mutListener.listen(4439)) {
                                                    touchInterceptListener.requestDisallowInterceptTouchEvent(true);
                                                }
                                            }
                                        }
                                    }
                                }
                                break;
                            case MotionEvent.ACTION_UP:
                                if (!ListenerUtil.mutListener.listen(4442)) {
                                    scrollViewContent.getParent().requestDisallowInterceptTouchEvent(false);
                                }
                                if (!ListenerUtil.mutListener.listen(4443)) {
                                    pagerArtAndInfo.getParent().requestDisallowInterceptTouchEvent(false);
                                }
                                if (!ListenerUtil.mutListener.listen(4445)) {
                                    if (touchInterceptListener != null) {
                                        if (!ListenerUtil.mutListener.listen(4444)) {
                                            touchInterceptListener.requestDisallowInterceptTouchEvent(false);
                                        }
                                    }
                                }
                                break;
                        }
                    }
                    return false;
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(4448)) {
            textViewGeneralInfo = view.findViewById(R.id.textViewGeneralInfo);
        }
        if (!ListenerUtil.mutListener.listen(4449)) {
            textViewTimePlayed = view.findViewById(R.id.textViewTimePlayed);
        }
        if (!ListenerUtil.mutListener.listen(4450)) {
            textViewNetworkUsageInfo = view.findViewById(R.id.textViewNetworkUsageInfo);
        }
        if (!ListenerUtil.mutListener.listen(4451)) {
            textViewTimeCached = view.findViewById(R.id.textViewTimeCached);
        }
        if (!ListenerUtil.mutListener.listen(4452)) {
            groupRecordings = view.findViewById(R.id.group_recording_info);
        }
        if (!ListenerUtil.mutListener.listen(4453)) {
            imgRecordingIcon = view.findViewById(R.id.imgRecordingIcon);
        }
        if (!ListenerUtil.mutListener.listen(4454)) {
            textViewRecordingSize = view.findViewById(R.id.textViewRecordingSize);
        }
        if (!ListenerUtil.mutListener.listen(4455)) {
            textViewRecordingName = view.findViewById(R.id.textViewRecordingName);
        }
        if (!ListenerUtil.mutListener.listen(4456)) {
            pagerHistoryAndRecordings = view.findViewById(R.id.pagerHistoryAndRecordings);
        }
        if (!ListenerUtil.mutListener.listen(4457)) {
            historyAndRecordsPagerAdapter = new HistoryAndRecordsPagerAdapter(requireContext(), pagerHistoryAndRecordings);
        }
        if (!ListenerUtil.mutListener.listen(4458)) {
            pagerHistoryAndRecordings.setAdapter(historyAndRecordsPagerAdapter);
        }
        if (!ListenerUtil.mutListener.listen(4459)) {
            btnPlay = view.findViewById(R.id.buttonPlay);
        }
        if (!ListenerUtil.mutListener.listen(4460)) {
            btnPrev = view.findViewById(R.id.buttonPrev);
        }
        if (!ListenerUtil.mutListener.listen(4461)) {
            btnNext = view.findViewById(R.id.buttonNext);
        }
        if (!ListenerUtil.mutListener.listen(4462)) {
            btnRecord = view.findViewById(R.id.buttonRecord);
        }
        if (!ListenerUtil.mutListener.listen(4463)) {
            btnFavourite = view.findViewById(R.id.buttonFavorite);
        }
        if (!ListenerUtil.mutListener.listen(4464)) {
            historyAndRecordsPagerAdapter.recyclerViewSongHistory.setAdapter(trackHistoryAdapter);
        }
        LinearLayoutManager llmHistory = new LinearLayoutManager(getContext());
        if (!ListenerUtil.mutListener.listen(4465)) {
            llmHistory.setOrientation(RecyclerView.VERTICAL);
        }
        if (!ListenerUtil.mutListener.listen(4466)) {
            historyAndRecordsPagerAdapter.recyclerViewSongHistory.setLayoutManager(llmHistory);
        }
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(historyAndRecordsPagerAdapter.recyclerViewSongHistory.getContext(), llmHistory.getOrientation());
        if (!ListenerUtil.mutListener.listen(4467)) {
            historyAndRecordsPagerAdapter.recyclerViewSongHistory.addItemDecoration(dividerItemDecoration);
        }
        if (!ListenerUtil.mutListener.listen(4468)) {
            trackHistoryViewModel = ViewModelProviders.of(this).get(TrackHistoryViewModel.class);
        }
        if (!ListenerUtil.mutListener.listen(4470)) {
            trackHistoryViewModel.getAllHistoryPaged().observe(this, new Observer<PagedList<TrackHistoryEntry>>() {

                @Override
                public void onChanged(@Nullable PagedList<TrackHistoryEntry> songHistoryEntries) {
                    if (!ListenerUtil.mutListener.listen(4469)) {
                        trackHistoryAdapter.submitList(songHistoryEntries);
                    }
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(4471)) {
            recordingsAdapter = new RecordingsAdapter(requireContext());
        }
        if (!ListenerUtil.mutListener.listen(4479)) {
            recordingsAdapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {

                public void onItemRangeInserted(int positionStart, int itemCount) {
                    final LinearLayoutManager lm = (LinearLayoutManager) historyAndRecordsPagerAdapter.recyclerViewRecordings.getLayoutManager();
                    if (!ListenerUtil.mutListener.listen(4478)) {
                        if ((ListenerUtil.mutListener.listen(4476) ? (lm.findFirstVisibleItemPosition() >= 2) : (ListenerUtil.mutListener.listen(4475) ? (lm.findFirstVisibleItemPosition() <= 2) : (ListenerUtil.mutListener.listen(4474) ? (lm.findFirstVisibleItemPosition() > 2) : (ListenerUtil.mutListener.listen(4473) ? (lm.findFirstVisibleItemPosition() != 2) : (ListenerUtil.mutListener.listen(4472) ? (lm.findFirstVisibleItemPosition() == 2) : (lm.findFirstVisibleItemPosition() < 2))))))) {
                            if (!ListenerUtil.mutListener.listen(4477)) {
                                historyAndRecordsPagerAdapter.recyclerViewRecordings.scrollToPosition(0);
                            }
                        }
                    }
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(4480)) {
            historyAndRecordsPagerAdapter.recyclerViewRecordings.setAdapter(recordingsAdapter);
        }
        LinearLayoutManager llmRecordings = new LinearLayoutManager(getContext());
        if (!ListenerUtil.mutListener.listen(4481)) {
            llmRecordings.setOrientation(RecyclerView.VERTICAL);
        }
        if (!ListenerUtil.mutListener.listen(4482)) {
            historyAndRecordsPagerAdapter.recyclerViewRecordings.setLayoutManager(llmRecordings);
        }
        if (!ListenerUtil.mutListener.listen(4483)) {
            historyAndRecordsPagerAdapter.recyclerViewRecordings.addItemDecoration(dividerItemDecoration);
        }
        // we only can do this at the runtime.
        ViewTreeObserver viewTreeObserver = pagerHistoryAndRecordings.getViewTreeObserver();
        if (!ListenerUtil.mutListener.listen(4485)) {
            if (viewTreeObserver.isAlive()) {
                if (!ListenerUtil.mutListener.listen(4484)) {
                    viewTreeObserver.addOnGlobalLayoutListener(() -> {
                        ViewGroup.LayoutParams layoutParams = pagerHistoryAndRecordings.getLayoutParams();
                        final int newHeight = scrollViewContent.getHeight();
                        if (newHeight != layoutParams.height) {
                            layoutParams.height = newHeight;
                            pagerHistoryAndRecordings.setLayoutParams(layoutParams);
                        }
                    });
                }
            }
        }
        return view;
    }

    public void init() {
        if (!ListenerUtil.mutListener.listen(4487)) {
            if (!initialized) {
                if (!ListenerUtil.mutListener.listen(4486)) {
                    fullUpdate();
                }
            }
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(4488)) {
            super.onActivityCreated(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(4496)) {
            btnPlay.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    if (!ListenerUtil.mutListener.listen(4494)) {
                        if (PlayerServiceUtil.isPlaying()) {
                            if (!ListenerUtil.mutListener.listen(4492)) {
                                if (PlayerServiceUtil.isRecording()) {
                                    if (!ListenerUtil.mutListener.listen(4490)) {
                                        PlayerServiceUtil.stopRecording();
                                    }
                                    if (!ListenerUtil.mutListener.listen(4491)) {
                                        updateRunningRecording();
                                    }
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(4493)) {
                                PlayerServiceUtil.pause(PauseReason.USER);
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(4489)) {
                                playLastFromHistory();
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(4495)) {
                        updatePlaybackButtons(PlayerServiceUtil.isPlaying(), PlayerServiceUtil.isRecording());
                    }
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(4497)) {
            btnPrev.setOnClickListener(view -> PlayerServiceUtil.skipToPrevious());
        }
        if (!ListenerUtil.mutListener.listen(4498)) {
            btnNext.setOnClickListener(view -> PlayerServiceUtil.skipToNext());
        }
        if (!ListenerUtil.mutListener.listen(4499)) {
            btnRecord.setOnClickListener(view -> {
                if (PlayerServiceUtil.isPlaying()) {
                    if (PlayerServiceUtil.isRecording()) {
                        PlayerServiceUtil.stopRecording();
                    } else {
                        if (Utils.verifyStoragePermissions(FragmentPlayerFull.this, PERM_REQ_STORAGE_RECORD)) {
                            PlayerServiceUtil.startRecording();
                        }
                    }
                    updateRunningRecording();
                    pagerHistoryAndRecordings.setCurrentItem(1, true);
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(4500)) {
            btnFavourite.setOnClickListener(v -> {
                DataRadioStation station = Utils.getCurrentOrLastStation(requireContext());
                if (station == null) {
                    return;
                }
                if (favouriteManager.has(station.StationUuid)) {
                    StationActions.removeFromFavourites(requireContext(), null, station);
                } else {
                    StationActions.markAsFavourite(requireContext(), station);
                }
            });
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        if (!ListenerUtil.mutListener.listen(4501)) {
            super.onHiddenChanged(hidden);
        }
        if (!ListenerUtil.mutListener.listen(4504)) {
            if (hidden) {
                if (!ListenerUtil.mutListener.listen(4503)) {
                    stopUpdating();
                }
            } else {
                if (!ListenerUtil.mutListener.listen(4502)) {
                    startUpdating();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(4506)) {
            if (touchInterceptListener != null) {
                if (!ListenerUtil.mutListener.listen(4505)) {
                    touchInterceptListener.requestDisallowInterceptTouchEvent(false);
                }
            }
        }
    }

    @Override
    public void onResume() {
        if (!ListenerUtil.mutListener.listen(4507)) {
            super.onResume();
        }
        if (!ListenerUtil.mutListener.listen(4508)) {
            startUpdating();
        }
    }

    @Override
    public void onPause() {
        if (!ListenerUtil.mutListener.listen(4509)) {
            super.onPause();
        }
        if (!ListenerUtil.mutListener.listen(4510)) {
            stopUpdating();
        }
    }

    public void setTouchInterceptListener(TouchInterceptListener touchInterceptListener) {
        if (!ListenerUtil.mutListener.listen(4511)) {
            this.touchInterceptListener = touchInterceptListener;
        }
    }

    private void startUpdating() {
        if (!ListenerUtil.mutListener.listen(4512)) {
            if (!isVisible()) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(4513)) {
            fullUpdate();
        }
        if (!ListenerUtil.mutListener.listen(4514)) {
            refreshHandler.executePeriodically(timedUpdateTask, TIMED_UPDATE_INTERVAL);
        }
        IntentFilter filter = new IntentFilter();
        if (!ListenerUtil.mutListener.listen(4515)) {
            filter.addAction(PlayerService.PLAYER_SERVICE_TIMER_UPDATE);
        }
        if (!ListenerUtil.mutListener.listen(4516)) {
            filter.addAction(PlayerService.PLAYER_SERVICE_STATE_CHANGE);
        }
        if (!ListenerUtil.mutListener.listen(4517)) {
            filter.addAction(PlayerService.PLAYER_SERVICE_META_UPDATE);
        }
        if (!ListenerUtil.mutListener.listen(4518)) {
            LocalBroadcastManager.getInstance(requireContext()).registerReceiver(updateUIReceiver, filter);
        }
        if (!ListenerUtil.mutListener.listen(4519)) {
            recordingsManager.getSavedRecordingsObservable().addObserver(recordingsObserver);
        }
        if (!ListenerUtil.mutListener.listen(4520)) {
            favouriteManager.addObserver(favouritesObserver);
        }
    }

    private void stopUpdating() {
        if (!ListenerUtil.mutListener.listen(4521)) {
            if (getView() == null) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(4522)) {
            refreshHandler.cancel();
        }
        if (!ListenerUtil.mutListener.listen(4524)) {
            if (trackMetadataCallback != null) {
                if (!ListenerUtil.mutListener.listen(4523)) {
                    trackMetadataCallback.cancel();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(4525)) {
            LocalBroadcastManager.getInstance(requireContext()).unregisterReceiver(updateUIReceiver);
        }
        if (!ListenerUtil.mutListener.listen(4526)) {
            recordingsManager.getSavedRecordingsObservable().deleteObserver(recordingsObserver);
        }
        if (!ListenerUtil.mutListener.listen(4527)) {
            favouriteManager.deleteObserver(favouritesObserver);
        }
    }

    public void resetScroll() {
        if (!ListenerUtil.mutListener.listen(4528)) {
            scrollViewContent.scrollTo(0, 0);
        }
        if (!ListenerUtil.mutListener.listen(4529)) {
            historyAndRecordsPagerAdapter.recyclerViewSongHistory.scrollToPosition(0);
        }
        if (!ListenerUtil.mutListener.listen(4530)) {
            historyAndRecordsPagerAdapter.recyclerViewRecordings.scrollToPosition(0);
        }
    }

    public boolean isScrolled() {
        return (ListenerUtil.mutListener.listen(4535) ? (scrollViewContent.getScrollY() >= 0) : (ListenerUtil.mutListener.listen(4534) ? (scrollViewContent.getScrollY() <= 0) : (ListenerUtil.mutListener.listen(4533) ? (scrollViewContent.getScrollY() < 0) : (ListenerUtil.mutListener.listen(4532) ? (scrollViewContent.getScrollY() != 0) : (ListenerUtil.mutListener.listen(4531) ? (scrollViewContent.getScrollY() == 0) : (scrollViewContent.getScrollY() > 0))))));
    }

    private void playLastFromHistory() {
        RadioDroidApp radioDroidApp = (RadioDroidApp) requireActivity().getApplication();
        DataRadioStation station = PlayerServiceUtil.getCurrentStation();
        if (!ListenerUtil.mutListener.listen(4537)) {
            if (station == null) {
                HistoryManager historyManager = radioDroidApp.getHistoryManager();
                if (!ListenerUtil.mutListener.listen(4536)) {
                    station = historyManager.getFirst();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(4539)) {
            if (station != null) {
                if (!ListenerUtil.mutListener.listen(4538)) {
                    Utils.showPlaySelection(radioDroidApp, station, getActivity().getSupportFragmentManager());
                }
            }
        }
    }

    private void fullUpdate() {
        DataRadioStation station = Utils.getCurrentOrLastStation(requireContext());
        if (!ListenerUtil.mutListener.listen(4563)) {
            if (station != null) {
                final ShoutcastInfo shoutcastInfo = PlayerServiceUtil.getShoutcastInfo();
                final StreamLiveInfo liveInfo = PlayerServiceUtil.getMetadataLive();
                String streamTitle = liveInfo.getTitle();
                if (!ListenerUtil.mutListener.listen(4542)) {
                    if (!TextUtils.isEmpty(streamTitle)) {
                        if (!ListenerUtil.mutListener.listen(4541)) {
                            textViewGeneralInfo.setText(streamTitle);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(4540)) {
                            textViewGeneralInfo.setText(station.Name);
                        }
                    }
                }
                Drawable flag = CountryFlagsLoader.getInstance().getFlag(requireContext(), station.CountryCode);
                if (!ListenerUtil.mutListener.listen(4552)) {
                    if (flag != null) {
                        float k = (ListenerUtil.mutListener.listen(4546) ? (flag.getMinimumWidth() % (float) flag.getMinimumHeight()) : (ListenerUtil.mutListener.listen(4545) ? (flag.getMinimumWidth() * (float) flag.getMinimumHeight()) : (ListenerUtil.mutListener.listen(4544) ? (flag.getMinimumWidth() - (float) flag.getMinimumHeight()) : (ListenerUtil.mutListener.listen(4543) ? (flag.getMinimumWidth() + (float) flag.getMinimumHeight()) : (flag.getMinimumWidth() / (float) flag.getMinimumHeight())))));
                        float viewHeight = artAndInfoPagerAdapter.textViewStationDescription.getTextSize();
                        if (!ListenerUtil.mutListener.listen(4551)) {
                            flag.setBounds(0, 0, (int) ((ListenerUtil.mutListener.listen(4550) ? (k % viewHeight) : (ListenerUtil.mutListener.listen(4549) ? (k / viewHeight) : (ListenerUtil.mutListener.listen(4548) ? (k - viewHeight) : (ListenerUtil.mutListener.listen(4547) ? (k + viewHeight) : (k * viewHeight)))))), (int) viewHeight);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(4560)) {
                    if ((ListenerUtil.mutListener.listen(4557) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.JELLY_BEAN_MR1) : (ListenerUtil.mutListener.listen(4556) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN_MR1) : (ListenerUtil.mutListener.listen(4555) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR1) : (ListenerUtil.mutListener.listen(4554) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.JELLY_BEAN_MR1) : (ListenerUtil.mutListener.listen(4553) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.JELLY_BEAN_MR1) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1))))))) {
                        if (!ListenerUtil.mutListener.listen(4559)) {
                            artAndInfoPagerAdapter.textViewStationDescription.setCompoundDrawablesRelative(flag, null, null, null);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(4558)) {
                            artAndInfoPagerAdapter.textViewStationDescription.setCompoundDrawables(flag, null, null, null);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(4561)) {
                    artAndInfoPagerAdapter.textViewStationDescription.setText(station.getLongDetails(requireContext()));
                }
                String[] tags = station.TagsAll.split(",");
                if (!ListenerUtil.mutListener.listen(4562)) {
                    artAndInfoPagerAdapter.viewTags.setTags(Arrays.asList(tags));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(4564)) {
            updateAlbumArt();
        }
        if (!ListenerUtil.mutListener.listen(4565)) {
            updateRecordings();
        }
        if (!ListenerUtil.mutListener.listen(4566)) {
            updatePlaybackButtons(PlayerServiceUtil.isPlaying(), PlayerServiceUtil.isRecording());
        }
        if (!ListenerUtil.mutListener.listen(4567)) {
            updateFavouriteButton();
        }
        if (!ListenerUtil.mutListener.listen(4568)) {
            timedUpdateTask.run();
        }
        if (!ListenerUtil.mutListener.listen(4569)) {
            initialized = true;
        }
    }

    private void updatePlaybackButtons(boolean playing, boolean recording) {
        if (!ListenerUtil.mutListener.listen(4570)) {
            updatePlayButton(playing);
        }
        if (!ListenerUtil.mutListener.listen(4571)) {
            updateRecordButton(playing, recording);
        }
    }

    private void updatePlayButton(boolean playing) {
        if (!ListenerUtil.mutListener.listen(4576)) {
            if (playing) {
                if (!ListenerUtil.mutListener.listen(4574)) {
                    btnPlay.setImageResource(R.drawable.ic_pause_circle);
                }
                if (!ListenerUtil.mutListener.listen(4575)) {
                    btnPlay.setContentDescription(getResources().getString(R.string.detail_pause));
                }
            } else {
                if (!ListenerUtil.mutListener.listen(4572)) {
                    btnPlay.setImageResource(R.drawable.ic_play_circle);
                }
                if (!ListenerUtil.mutListener.listen(4573)) {
                    btnPlay.setContentDescription(getResources().getString(R.string.detail_play));
                }
            }
        }
    }

    private void updateRecordButton(boolean playing, boolean recording) {
        if (!ListenerUtil.mutListener.listen(4577)) {
            btnRecord.setEnabled(playing);
        }
        if (!ListenerUtil.mutListener.listen(4584)) {
            if (recording) {
                if (!ListenerUtil.mutListener.listen(4582)) {
                    btnRecord.setImageResource(R.drawable.ic_stop_recording);
                }
                if (!ListenerUtil.mutListener.listen(4583)) {
                    btnRecord.setContentDescription(getResources().getString(R.string.detail_stop));
                }
            } else {
                if (!ListenerUtil.mutListener.listen(4578)) {
                    btnRecord.setImageResource(R.drawable.ic_start_recording);
                }
                if (!ListenerUtil.mutListener.listen(4581)) {
                    if (!storagePermissionsDenied) {
                        if (!ListenerUtil.mutListener.listen(4580)) {
                            btnRecord.setContentDescription(getResources().getString(R.string.image_button_record));
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(4579)) {
                            btnRecord.setContentDescription(getResources().getString(R.string.image_button_record_request_permission));
                        }
                    }
                }
            }
        }
    }

    private void updateRecordings() {
        if (!ListenerUtil.mutListener.listen(4585)) {
            recordingsAdapter.setRecordings(recordingsManager.getSavedRecordings());
        }
        if (!ListenerUtil.mutListener.listen(4586)) {
            updateRunningRecording();
        }
    }

    private void updateRunningRecording() {
        if (!ListenerUtil.mutListener.listen(4593)) {
            if (PlayerServiceUtil.isRecording()) {
                final Map<Recordable, RunningRecordingInfo> runningRecordings = recordingsManager.getRunningRecordings();
                final RunningRecordingInfo recordingInfo = runningRecordings.entrySet().iterator().next().getValue();
                if (!ListenerUtil.mutListener.listen(4589)) {
                    groupRecordings.setVisibility(View.VISIBLE);
                }
                if (!ListenerUtil.mutListener.listen(4590)) {
                    imgRecordingIcon.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.blink_recording));
                }
                if (!ListenerUtil.mutListener.listen(4591)) {
                    textViewRecordingSize.setText(Utils.getReadableBytes(recordingInfo.getBytesWritten()));
                }
                if (!ListenerUtil.mutListener.listen(4592)) {
                    textViewRecordingName.setText(recordingInfo.getFileName());
                }
            } else {
                if (!ListenerUtil.mutListener.listen(4587)) {
                    groupRecordings.setVisibility(View.GONE);
                }
                if (!ListenerUtil.mutListener.listen(4588)) {
                    imgRecordingIcon.clearAnimation();
                }
            }
        }
    }

    private void updateAlbumArt() {
        DataRadioStation station = PlayerServiceUtil.getCurrentStation();
        if (!ListenerUtil.mutListener.listen(4594)) {
            if (station == null) {
                return;
            }
        }
        final StreamLiveInfo liveInfo = PlayerServiceUtil.getMetadataLive();
        if (!ListenerUtil.mutListener.listen(4598)) {
            if ((ListenerUtil.mutListener.listen(4597) ? ((ListenerUtil.mutListener.listen(4596) ? ((ListenerUtil.mutListener.listen(4595) ? (lastLiveInfoForTrackMetadata != null || TextUtils.equals(lastLiveInfoForTrackMetadata.getArtist(), liveInfo.getArtist())) : (lastLiveInfoForTrackMetadata != null && TextUtils.equals(lastLiveInfoForTrackMetadata.getArtist(), liveInfo.getArtist()))) || TextUtils.equals(lastLiveInfoForTrackMetadata.getTrack(), liveInfo.getTrack())) : ((ListenerUtil.mutListener.listen(4595) ? (lastLiveInfoForTrackMetadata != null || TextUtils.equals(lastLiveInfoForTrackMetadata.getArtist(), liveInfo.getArtist())) : (lastLiveInfoForTrackMetadata != null && TextUtils.equals(lastLiveInfoForTrackMetadata.getArtist(), liveInfo.getArtist()))) && TextUtils.equals(lastLiveInfoForTrackMetadata.getTrack(), liveInfo.getTrack()))) || !TrackMetadataCallback.FailureType.RECOVERABLE.equals(trackMetadataLastFailureType)) : ((ListenerUtil.mutListener.listen(4596) ? ((ListenerUtil.mutListener.listen(4595) ? (lastLiveInfoForTrackMetadata != null || TextUtils.equals(lastLiveInfoForTrackMetadata.getArtist(), liveInfo.getArtist())) : (lastLiveInfoForTrackMetadata != null && TextUtils.equals(lastLiveInfoForTrackMetadata.getArtist(), liveInfo.getArtist()))) || TextUtils.equals(lastLiveInfoForTrackMetadata.getTrack(), liveInfo.getTrack())) : ((ListenerUtil.mutListener.listen(4595) ? (lastLiveInfoForTrackMetadata != null || TextUtils.equals(lastLiveInfoForTrackMetadata.getArtist(), liveInfo.getArtist())) : (lastLiveInfoForTrackMetadata != null && TextUtils.equals(lastLiveInfoForTrackMetadata.getArtist(), liveInfo.getArtist()))) && TextUtils.equals(lastLiveInfoForTrackMetadata.getTrack(), liveInfo.getTrack()))) && !TrackMetadataCallback.FailureType.RECOVERABLE.equals(trackMetadataLastFailureType)))) {
                return;
            }
        }
        final RadioDroidApp radioDroidApp = (RadioDroidApp) requireActivity().getApplication();
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(radioDroidApp);
        String LastFMApiKey = sharedPref.getString("last_fm_api_key", "");
        if (!ListenerUtil.mutListener.listen(4604)) {
            if ((ListenerUtil.mutListener.listen(4600) ? ((ListenerUtil.mutListener.listen(4599) ? (TextUtils.isEmpty(liveInfo.getArtist()) && TextUtils.isEmpty(liveInfo.getTrack())) : (TextUtils.isEmpty(liveInfo.getArtist()) || TextUtils.isEmpty(liveInfo.getTrack()))) && LastFMApiKey.isEmpty()) : ((ListenerUtil.mutListener.listen(4599) ? (TextUtils.isEmpty(liveInfo.getArtist()) && TextUtils.isEmpty(liveInfo.getTrack())) : (TextUtils.isEmpty(liveInfo.getArtist()) || TextUtils.isEmpty(liveInfo.getTrack()))) || LastFMApiKey.isEmpty()))) {
                if (!ListenerUtil.mutListener.listen(4603)) {
                    if (station.hasIcon()) {
                        if (!ListenerUtil.mutListener.listen(4602)) {
                            // TODO: Check if we already have this station's icon loaded into image view
                            Picasso.get().load(station.IconUrl).error(R.drawable.ic_launcher).into(artAndInfoPagerAdapter.imageViewArt);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(4601)) {
                            artAndInfoPagerAdapter.imageViewArt.setImageResource(R.drawable.ic_launcher);
                        }
                    }
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(4605)) {
            trackMetadataLastFailureType = null;
        }
        if (!ListenerUtil.mutListener.listen(4606)) {
            lastLiveInfoForTrackMetadata = liveInfo;
        }
        if (!ListenerUtil.mutListener.listen(4608)) {
            if (trackMetadataCallback != null) {
                if (!ListenerUtil.mutListener.listen(4607)) {
                    trackMetadataCallback.cancel();
                }
            }
        }
        TrackMetadataSearcher trackMetadataSearcher = radioDroidApp.getTrackMetadataSearcher();
        final WeakReference<FragmentPlayerFull> fragmentWeakReference = new WeakReference<>(this);
        if (!ListenerUtil.mutListener.listen(4609)) {
            trackHistoryRepository.getLastInsertedHistoryItem((trackHistoryEntry, dao) -> {
                if (trackHistoryEntry == null) {
                    Log.e(TAG, "trackHistoryEntry is null in updateAlbumArt which should not happen.");
                    return;
                }
                if (!TextUtils.isEmpty(trackHistoryEntry.artUrl)) {
                    return;
                }
                FragmentPlayerFull fragment = fragmentWeakReference.get();
                if (fragment != null) {
                    fragment.requireActivity().runOnUiThread(() -> {
                        if (fragment.isResumed()) {
                            fragment.trackMetadataCallback = new PlayerTrackMetadataCallback(fragmentWeakReference, trackHistoryEntry);
                            trackMetadataSearcher.fetchTrackMetadata(LastFMApiKey, liveInfo.getArtist(), liveInfo.getTrack(), fragment.trackMetadataCallback);
                        }
                    });
                }
            });
        }
    }

    private void updateFavouriteButton() {
        DataRadioStation station = Utils.getCurrentOrLastStation(requireContext());
        if (!ListenerUtil.mutListener.listen(4615)) {
            if ((ListenerUtil.mutListener.listen(4610) ? (station != null || favouriteManager.has(station.StationUuid)) : (station != null && favouriteManager.has(station.StationUuid)))) {
                if (!ListenerUtil.mutListener.listen(4613)) {
                    btnFavourite.setImageResource(R.drawable.ic_star_24dp);
                }
                if (!ListenerUtil.mutListener.listen(4614)) {
                    btnFavourite.setContentDescription(requireContext().getApplicationContext().getString(R.string.detail_unstar));
                }
            } else {
                if (!ListenerUtil.mutListener.listen(4611)) {
                    btnFavourite.setImageResource(R.drawable.ic_star_border_24dp);
                }
                if (!ListenerUtil.mutListener.listen(4612)) {
                    btnFavourite.setContentDescription(requireContext().getApplicationContext().getString(R.string.detail_star));
                }
            }
        }
    }

    private class FavouritesObserver implements java.util.Observer {

        @Override
        public void update(Observable o, Object arg) {
            if (!ListenerUtil.mutListener.listen(4616)) {
                updateFavouriteButton();
            }
        }
    }

    private static class PlayerTrackMetadataCallback implements TrackMetadataCallback {

        private boolean canceled = false;

        private WeakReference<FragmentPlayerFull> fragmentWeakReference;

        private TrackHistoryEntry trackHistoryEntry;

        private PlayerTrackMetadataCallback(@NonNull WeakReference<FragmentPlayerFull> fragmentWeakReference, TrackHistoryEntry trackHistoryEntry) {
            if (!ListenerUtil.mutListener.listen(4617)) {
                this.fragmentWeakReference = fragmentWeakReference;
            }
            if (!ListenerUtil.mutListener.listen(4618)) {
                this.trackHistoryEntry = trackHistoryEntry;
            }
        }

        public void cancel() {
            if (!ListenerUtil.mutListener.listen(4619)) {
                canceled = true;
            }
        }

        @Override
        public void onFailure(@NonNull FailureType failureType) {
            FragmentPlayerFull fragment = fragmentWeakReference.get();
            if (!ListenerUtil.mutListener.listen(4621)) {
                if (fragment != null) {
                    if (!ListenerUtil.mutListener.listen(4620)) {
                        fragment.requireActivity().runOnUiThread(() -> {
                            if (canceled) {
                                return;
                            }
                            fragment.trackMetadataLastFailureType = failureType;
                            DataRadioStation station = Utils.getCurrentOrLastStation(fragment.requireContext());
                            if (station != null && station.hasIcon()) {
                                Picasso.get().load(station.IconUrl).error(R.drawable.ic_launcher).into(fragment.artAndInfoPagerAdapter.imageViewArt);
                            } else {
                                fragment.artAndInfoPagerAdapter.imageViewArt.setImageResource(R.drawable.ic_launcher);
                            }
                            fragment.trackMetadataCallback = null;
                        });
                    }
                }
            }
        }

        @Override
        public void onSuccess(@NonNull final TrackMetadata trackMetadata) {
            FragmentPlayerFull fragment = fragmentWeakReference.get();
            if (!ListenerUtil.mutListener.listen(4623)) {
                if (fragment != null) {
                    if (!ListenerUtil.mutListener.listen(4622)) {
                        fragment.requireActivity().runOnUiThread(() -> {
                            if (canceled) {
                                return;
                            }
                            final List<TrackMetadata.AlbumArt> albumArts = trackMetadata.getAlbumArts();
                            if (!albumArts.isEmpty()) {
                                final String albumArtUrl = albumArts.get(0).url;
                                if (!TextUtils.isEmpty(albumArtUrl)) {
                                    Picasso.get().load(albumArtUrl).into(fragment.artAndInfoPagerAdapter.imageViewArt);
                                    if (!albumArtUrl.equals(trackHistoryEntry.stationIconUrl)) {
                                        fragment.trackHistoryRepository.setTrackArtUrl(trackHistoryEntry.uid, albumArtUrl);
                                    }
                                    fragment.trackMetadataCallback = null;
                                    return;
                                }
                            }
                            onFailure(FailureType.UNRECOVERABLE);
                        });
                    }
                }
            }
        }
    }

    private class ArtAndInfoPagerAdapter extends PagerAdapter {

        private ViewGroup layoutAlbumArt;

        private ViewGroup layoutStationInfo;

        private String[] titles;

        ImageView imageViewArt;

        TextView textViewStationDescription;

        TagsView viewTags;

        ArtAndInfoPagerAdapter(@NonNull Context context, @NonNull ViewGroup parent) {
            LayoutInflater inflater = LayoutInflater.from(context);
            if (!ListenerUtil.mutListener.listen(4624)) {
                layoutAlbumArt = (ViewGroup) inflater.inflate(R.layout.page_player_album_art, parent, false);
            }
            if (!ListenerUtil.mutListener.listen(4625)) {
                layoutStationInfo = (ViewGroup) inflater.inflate(R.layout.page_player_station_info, parent, false);
            }
            if (!ListenerUtil.mutListener.listen(4626)) {
                titles = new String[] { getResources().getString(R.string.tab_player_art), getResources().getString(R.string.tab_player_info) };
            }
            if (!ListenerUtil.mutListener.listen(4627)) {
                imageViewArt = layoutAlbumArt.findViewById(R.id.imageViewArt);
            }
            if (!ListenerUtil.mutListener.listen(4628)) {
                textViewStationDescription = layoutStationInfo.findViewById(R.id.textViewStationDescription);
            }
            if (!ListenerUtil.mutListener.listen(4629)) {
                viewTags = layoutStationInfo.findViewById(R.id.viewTags);
            }
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup collection, int position) {
            if ((ListenerUtil.mutListener.listen(4634) ? (position >= 0) : (ListenerUtil.mutListener.listen(4633) ? (position <= 0) : (ListenerUtil.mutListener.listen(4632) ? (position > 0) : (ListenerUtil.mutListener.listen(4631) ? (position < 0) : (ListenerUtil.mutListener.listen(4630) ? (position != 0) : (position == 0))))))) {
                if (!ListenerUtil.mutListener.listen(4636)) {
                    collection.addView(layoutAlbumArt);
                }
                return layoutAlbumArt;
            } else {
                if (!ListenerUtil.mutListener.listen(4635)) {
                    collection.addView(layoutStationInfo);
                }
                return layoutStationInfo;
            }
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object view) {
            if (!ListenerUtil.mutListener.listen(4637)) {
                container.removeView((View) view);
            }
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == object;
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }
    }

    private class HistoryAndRecordsPagerAdapter extends PagerAdapter {

        private ViewGroup layoutSongHistory;

        private ViewGroup layoutRecordings;

        private String[] titles;

        RecyclerView recyclerViewSongHistory;

        RecyclerView recyclerViewRecordings;

        HistoryAndRecordsPagerAdapter(@NonNull Context context, @NonNull ViewGroup parent) {
            LayoutInflater inflater = LayoutInflater.from(context);
            if (!ListenerUtil.mutListener.listen(4638)) {
                layoutSongHistory = (ViewGroup) inflater.inflate(R.layout.page_player_history, parent, false);
            }
            if (!ListenerUtil.mutListener.listen(4639)) {
                layoutRecordings = (ViewGroup) inflater.inflate(R.layout.page_player_recordings, parent, false);
            }
            if (!ListenerUtil.mutListener.listen(4640)) {
                titles = new String[] { getResources().getString(R.string.tab_player_history), getResources().getString(R.string.tab_player_recordings) };
            }
            if (!ListenerUtil.mutListener.listen(4641)) {
                recyclerViewSongHistory = layoutSongHistory.findViewById(R.id.recyclerViewSongHistory);
            }
            if (!ListenerUtil.mutListener.listen(4642)) {
                recyclerViewRecordings = layoutRecordings.findViewById(R.id.recyclerViewRecordings);
            }
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup collection, int position) {
            if ((ListenerUtil.mutListener.listen(4647) ? (position >= 0) : (ListenerUtil.mutListener.listen(4646) ? (position <= 0) : (ListenerUtil.mutListener.listen(4645) ? (position > 0) : (ListenerUtil.mutListener.listen(4644) ? (position < 0) : (ListenerUtil.mutListener.listen(4643) ? (position != 0) : (position == 0))))))) {
                if (!ListenerUtil.mutListener.listen(4649)) {
                    collection.addView(layoutSongHistory);
                }
                return layoutSongHistory;
            } else {
                if (!ListenerUtil.mutListener.listen(4648)) {
                    collection.addView(layoutRecordings);
                }
                return layoutRecordings;
            }
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object view) {
            if (!ListenerUtil.mutListener.listen(4650)) {
                container.removeView((View) view);
            }
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == object;
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }
    }

    private static class TimedUpdateTask extends RefreshHandler.ObjectBoundRunnable<FragmentPlayerFull> {

        TimedUpdateTask(FragmentPlayerFull obj) {
            super(obj);
        }

        @Override
        protected void run(FragmentPlayerFull fragmentPlayerFull) {
            final ShoutcastInfo shoutcastInfo = PlayerServiceUtil.getShoutcastInfo();
            if (!ListenerUtil.mutListener.listen(4677)) {
                if (PlayerServiceUtil.isPlaying()) {
                    String networkUsageInfo = Utils.getReadableBytes(PlayerServiceUtil.getTransferredBytes());
                    if (!ListenerUtil.mutListener.listen(4658)) {
                        if ((ListenerUtil.mutListener.listen(4656) ? (shoutcastInfo != null || (ListenerUtil.mutListener.listen(4655) ? (shoutcastInfo.bitrate >= 0) : (ListenerUtil.mutListener.listen(4654) ? (shoutcastInfo.bitrate <= 0) : (ListenerUtil.mutListener.listen(4653) ? (shoutcastInfo.bitrate < 0) : (ListenerUtil.mutListener.listen(4652) ? (shoutcastInfo.bitrate != 0) : (ListenerUtil.mutListener.listen(4651) ? (shoutcastInfo.bitrate == 0) : (shoutcastInfo.bitrate > 0))))))) : (shoutcastInfo != null && (ListenerUtil.mutListener.listen(4655) ? (shoutcastInfo.bitrate >= 0) : (ListenerUtil.mutListener.listen(4654) ? (shoutcastInfo.bitrate <= 0) : (ListenerUtil.mutListener.listen(4653) ? (shoutcastInfo.bitrate < 0) : (ListenerUtil.mutListener.listen(4652) ? (shoutcastInfo.bitrate != 0) : (ListenerUtil.mutListener.listen(4651) ? (shoutcastInfo.bitrate == 0) : (shoutcastInfo.bitrate > 0))))))))) {
                            if (!ListenerUtil.mutListener.listen(4657)) {
                                networkUsageInfo += " (" + shoutcastInfo.bitrate + " kbps)";
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(4659)) {
                        fragmentPlayerFull.textViewNetworkUsageInfo.setText(networkUsageInfo);
                    }
                    final long now = System.currentTimeMillis();
                    final long startTime = PlayerServiceUtil.getLastPlayStartTime();
                    long deltaSeconds = (ListenerUtil.mutListener.listen(4664) ? (startTime >= 0) : (ListenerUtil.mutListener.listen(4663) ? (startTime <= 0) : (ListenerUtil.mutListener.listen(4662) ? (startTime < 0) : (ListenerUtil.mutListener.listen(4661) ? (startTime != 0) : (ListenerUtil.mutListener.listen(4660) ? (startTime == 0) : (startTime > 0)))))) ? ((ListenerUtil.mutListener.listen(4672) ? (((ListenerUtil.mutListener.listen(4668) ? (now % startTime) : (ListenerUtil.mutListener.listen(4667) ? (now / startTime) : (ListenerUtil.mutListener.listen(4666) ? (now * startTime) : (ListenerUtil.mutListener.listen(4665) ? (now + startTime) : (now - startTime)))))) % 1000) : (ListenerUtil.mutListener.listen(4671) ? (((ListenerUtil.mutListener.listen(4668) ? (now % startTime) : (ListenerUtil.mutListener.listen(4667) ? (now / startTime) : (ListenerUtil.mutListener.listen(4666) ? (now * startTime) : (ListenerUtil.mutListener.listen(4665) ? (now + startTime) : (now - startTime)))))) * 1000) : (ListenerUtil.mutListener.listen(4670) ? (((ListenerUtil.mutListener.listen(4668) ? (now % startTime) : (ListenerUtil.mutListener.listen(4667) ? (now / startTime) : (ListenerUtil.mutListener.listen(4666) ? (now * startTime) : (ListenerUtil.mutListener.listen(4665) ? (now + startTime) : (now - startTime)))))) - 1000) : (ListenerUtil.mutListener.listen(4669) ? (((ListenerUtil.mutListener.listen(4668) ? (now % startTime) : (ListenerUtil.mutListener.listen(4667) ? (now / startTime) : (ListenerUtil.mutListener.listen(4666) ? (now * startTime) : (ListenerUtil.mutListener.listen(4665) ? (now + startTime) : (now - startTime)))))) + 1000) : (((ListenerUtil.mutListener.listen(4668) ? (now % startTime) : (ListenerUtil.mutListener.listen(4667) ? (now / startTime) : (ListenerUtil.mutListener.listen(4666) ? (now * startTime) : (ListenerUtil.mutListener.listen(4665) ? (now + startTime) : (now - startTime)))))) / 1000)))))) : 0;
                    if (!ListenerUtil.mutListener.listen(4673)) {
                        deltaSeconds = Math.max(deltaSeconds, 0);
                    }
                    if (!ListenerUtil.mutListener.listen(4674)) {
                        fragmentPlayerFull.textViewTimePlayed.setText(DateUtils.formatElapsedTime(deltaSeconds));
                    }
                    if (!ListenerUtil.mutListener.listen(4675)) {
                        fragmentPlayerFull.textViewTimeCached.setText(DateUtils.formatElapsedTime(PlayerServiceUtil.getBufferedSeconds()));
                    }
                    if (!ListenerUtil.mutListener.listen(4676)) {
                        fragmentPlayerFull.updateRunningRecording();
                    }
                }
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (!ListenerUtil.mutListener.listen(4696)) {
            // If request is cancelled, the result arrays are empty.
            if ((ListenerUtil.mutListener.listen(4682) ? (requestCode >= PERM_REQ_STORAGE_RECORD) : (ListenerUtil.mutListener.listen(4681) ? (requestCode <= PERM_REQ_STORAGE_RECORD) : (ListenerUtil.mutListener.listen(4680) ? (requestCode > PERM_REQ_STORAGE_RECORD) : (ListenerUtil.mutListener.listen(4679) ? (requestCode < PERM_REQ_STORAGE_RECORD) : (ListenerUtil.mutListener.listen(4678) ? (requestCode != PERM_REQ_STORAGE_RECORD) : (requestCode == PERM_REQ_STORAGE_RECORD))))))) {
                if (!ListenerUtil.mutListener.listen(4693)) {
                    if ((ListenerUtil.mutListener.listen(4688) ? ((ListenerUtil.mutListener.listen(4687) ? (grantResults.length >= 0) : (ListenerUtil.mutListener.listen(4686) ? (grantResults.length <= 0) : (ListenerUtil.mutListener.listen(4685) ? (grantResults.length < 0) : (ListenerUtil.mutListener.listen(4684) ? (grantResults.length != 0) : (ListenerUtil.mutListener.listen(4683) ? (grantResults.length == 0) : (grantResults.length > 0)))))) || grantResults[0] == PackageManager.PERMISSION_GRANTED) : ((ListenerUtil.mutListener.listen(4687) ? (grantResults.length >= 0) : (ListenerUtil.mutListener.listen(4686) ? (grantResults.length <= 0) : (ListenerUtil.mutListener.listen(4685) ? (grantResults.length < 0) : (ListenerUtil.mutListener.listen(4684) ? (grantResults.length != 0) : (ListenerUtil.mutListener.listen(4683) ? (grantResults.length == 0) : (grantResults.length > 0)))))) && grantResults[0] == PackageManager.PERMISSION_GRANTED))) {
                        if (!ListenerUtil.mutListener.listen(4691)) {
                            storagePermissionsDenied = false;
                        }
                        if (!ListenerUtil.mutListener.listen(4692)) {
                            PlayerServiceUtil.startRecording();
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(4689)) {
                            storagePermissionsDenied = true;
                        }
                        Toast toast = Toast.makeText(getActivity(), getResources().getString(R.string.error_record_needs_write), Toast.LENGTH_SHORT);
                        if (!ListenerUtil.mutListener.listen(4690)) {
                            toast.show();
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(4694)) {
                    updatePlaybackButtons(PlayerServiceUtil.isPlaying(), PlayerServiceUtil.isRecording());
                }
                if (!ListenerUtil.mutListener.listen(4695)) {
                    updateRecordings();
                }
            }
        }
    }
}
