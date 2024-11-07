package org.wordpress.android.ui.media;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Spannable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.ActionMode;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.snackbar.Snackbar;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.wordpress.android.R;
import org.wordpress.android.WordPress;
import org.wordpress.android.fluxc.Dispatcher;
import org.wordpress.android.fluxc.generated.MediaActionBuilder;
import org.wordpress.android.fluxc.generated.SiteActionBuilder;
import org.wordpress.android.fluxc.model.MediaModel;
import org.wordpress.android.fluxc.model.MediaModel.MediaUploadState;
import org.wordpress.android.fluxc.model.SiteModel;
import org.wordpress.android.fluxc.store.MediaStore;
import org.wordpress.android.fluxc.store.MediaStore.FetchMediaListPayload;
import org.wordpress.android.fluxc.store.MediaStore.MediaErrorType;
import org.wordpress.android.fluxc.store.MediaStore.OnMediaListFetched;
import org.wordpress.android.fluxc.store.QuickStartStore.QuickStartExistingSiteTask;
import org.wordpress.android.fluxc.utils.MimeType;
import org.wordpress.android.ui.ActionableEmptyView;
import org.wordpress.android.ui.EmptyViewMessageType;
import org.wordpress.android.ui.media.MediaGridAdapter.MediaGridAdapterCallback;
import org.wordpress.android.ui.media.services.MediaDeleteService;
import org.wordpress.android.ui.mysite.SelectedSiteRepository;
import org.wordpress.android.ui.mysite.cards.quickstart.QuickStartRepository;
import org.wordpress.android.ui.prefs.EmptyViewRecyclerView;
import org.wordpress.android.ui.quickstart.QuickStartEvent;
import org.wordpress.android.ui.utils.UiString.UiStringText;
import org.wordpress.android.util.AccessibilityUtils;
import org.wordpress.android.util.AppLog;
import org.wordpress.android.util.ListUtils;
import org.wordpress.android.util.NetworkUtils;
import org.wordpress.android.util.QuickStartUtilsWrapper;
import org.wordpress.android.util.SnackbarItem;
import org.wordpress.android.util.SnackbarSequencer;
import org.wordpress.android.util.ToastUtils;
import org.wordpress.android.util.WPMediaUtils;
import org.wordpress.android.util.helpers.SwipeToRefreshHelper;
import org.wordpress.android.util.helpers.SwipeToRefreshHelper.RefreshListener;
import org.wordpress.android.util.widgets.CustomSwipeRefreshLayout;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import static android.app.Activity.RESULT_OK;
import static org.greenrobot.eventbus.ThreadMode.MAIN;
import static org.wordpress.android.fluxc.utils.MimeType.Type.APPLICATION;
import static org.wordpress.android.fluxc.utils.MimeType.Type.AUDIO;
import static org.wordpress.android.fluxc.utils.MimeType.Type.IMAGE;
import static org.wordpress.android.fluxc.utils.MimeType.Type.VIDEO;
import static org.wordpress.android.util.WPSwipeToRefreshHelper.buildSwipeToRefreshHelper;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * The grid displaying the media items.
 */
@SuppressWarnings("ALL")
public class MediaGridFragment extends Fragment implements MediaGridAdapterCallback {

    private static final String BUNDLE_SELECTED_STATES = "BUNDLE_SELECTED_STATES";

    private static final String BUNDLE_IN_MULTI_SELECT_MODE = "BUNDLE_IN_MULTI_SELECT_MODE";

    private static final String BUNDLE_SCROLL_POSITION = "BUNDLE_SCROLL_POSITION";

    private static final String BUNDLE_RETRIEVED_ALL_FILTERS = "BUNDLE_RETRIEVED_ALL_FILTERS";

    private static final String BUNDLE_FETCHED_FILTERS = "BUNDLE_FETCHED_FILTERS";

    private static final String BUNDLE_EMPTY_VIEW_MESSAGE = "BUNDLE_EMPTY_VIEW_MESSAGE";

    static final String TAG = "media_grid_fragment";

    // should be a multiple of both the column counts (3 in portrait, 4 in landscape)
    private static final int NUM_MEDIA_PER_FETCH = 48;

    enum MediaFilter {

        FILTER_ALL(0), FILTER_IMAGES(1), FILTER_DOCUMENTS(2), FILTER_VIDEOS(3), FILTER_AUDIO(4);

        private final int mValue;

        MediaFilter(int value) {
            this.mValue = value;
        }

        int getValue() {
            return mValue;
        }

        private MimeType.Type toMimeType() {
            switch(this) {
                case FILTER_AUDIO:
                    return AUDIO;
                case FILTER_DOCUMENTS:
                    return APPLICATION;
                case FILTER_IMAGES:
                    return IMAGE;
                case FILTER_VIDEOS:
                    return VIDEO;
                default:
                    return null;
            }
        }

        private static MediaFilter fromMimeType(@NonNull MimeType.Type mimeType) {
            switch(mimeType) {
                case APPLICATION:
                    return MediaFilter.FILTER_DOCUMENTS;
                case AUDIO:
                    return MediaFilter.FILTER_AUDIO;
                case IMAGE:
                    return MediaFilter.FILTER_IMAGES;
                case VIDEO:
                    return MediaFilter.FILTER_VIDEOS;
                default:
                    return MediaFilter.FILTER_ALL;
            }
        }
    }

    // describes which filters we've fetched media for
    private boolean[] mFetchedFilters = new boolean[MediaFilter.values().length];

    // describes which filters we've fetched ALL media for
    private boolean[] mFetchedAllFilters = new boolean[MediaFilter.values().length];

    @Inject
    Dispatcher mDispatcher;

    @Inject
    MediaStore mMediaStore;

    @Inject
    QuickStartRepository mQuickStartRepository;

    @Inject
    QuickStartUtilsWrapper mQuickStartUtilsWrapper;

    @Inject
    SnackbarSequencer mSnackbarSequencer;

    @Inject
    SelectedSiteRepository mSelectedSiteRepository;

    private MediaBrowserType mBrowserType;

    private EmptyViewRecyclerView mRecycler;

    private GridLayoutManager mGridManager;

    private MediaGridAdapter mGridAdapter;

    private MediaGridListener mListener;

    private boolean mIsRefreshing;

    private ActionMode mActionMode;

    private String mSearchTerm;

    private MediaFilter mFilter = MediaFilter.FILTER_ALL;

    private SwipeToRefreshHelper mSwipeToRefreshHelper;

    private ActionableEmptyView mActionableEmptyView;

    private EmptyViewMessageType mEmptyViewMessageType = EmptyViewMessageType.NO_CONTENT;

    private SiteModel mSite;

    private QuickStartEvent mQuickStartEvent;

    public interface MediaGridListener {

        void onMediaItemSelected(int localMediaId, boolean isLongClick);

        void onMediaRequestRetry(int localMediaId);

        void onMediaRequestDelete(int localMediaId);
    }

    public static MediaGridFragment newInstance(@NonNull SiteModel site, @NonNull MediaBrowserType browserType, @NonNull MediaFilter filter) {
        Bundle args = new Bundle();
        if (!ListenerUtil.mutListener.listen(7108)) {
            args.putSerializable(WordPress.SITE, site);
        }
        if (!ListenerUtil.mutListener.listen(7109)) {
            args.putSerializable(MediaBrowserActivity.ARG_BROWSER_TYPE, browserType);
        }
        if (!ListenerUtil.mutListener.listen(7110)) {
            args.putSerializable(MediaBrowserActivity.ARG_FILTER, filter);
        }
        MediaGridFragment fragment = new MediaGridFragment();
        if (!ListenerUtil.mutListener.listen(7111)) {
            fragment.setArguments(args);
        }
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(7112)) {
            super.onCreate(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(7113)) {
            ((WordPress) getActivity().getApplication()).component().inject(this);
        }
        Bundle args = getArguments();
        if (!ListenerUtil.mutListener.listen(7114)) {
            mSite = (SiteModel) args.getSerializable(WordPress.SITE);
        }
        if (!ListenerUtil.mutListener.listen(7115)) {
            mBrowserType = (MediaBrowserType) args.getSerializable(MediaBrowserActivity.ARG_BROWSER_TYPE);
        }
        if (!ListenerUtil.mutListener.listen(7116)) {
            mFilter = (MediaFilter) args.getSerializable(MediaBrowserActivity.ARG_FILTER);
        }
        if (!ListenerUtil.mutListener.listen(7118)) {
            if (savedInstanceState != null) {
                if (!ListenerUtil.mutListener.listen(7117)) {
                    mQuickStartEvent = savedInstanceState.getParcelable(QuickStartEvent.KEY);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(7121)) {
            if (mSite == null) {
                if (!ListenerUtil.mutListener.listen(7119)) {
                    ToastUtils.showToast(getActivity(), R.string.blog_not_found, ToastUtils.Duration.SHORT);
                }
                if (!ListenerUtil.mutListener.listen(7120)) {
                    getActivity().finish();
                }
            }
        }
    }

    @Override
    public void onStart() {
        if (!ListenerUtil.mutListener.listen(7122)) {
            super.onStart();
        }
        if (!ListenerUtil.mutListener.listen(7123)) {
            mDispatcher.register(this);
        }
        if (!ListenerUtil.mutListener.listen(7124)) {
            EventBus.getDefault().register(this);
        }
        if (!ListenerUtil.mutListener.listen(7125)) {
            mGridAdapter.refreshCurrentItems(mRecycler);
        }
    }

    @Override
    public void onStop() {
        if (!ListenerUtil.mutListener.listen(7126)) {
            mDispatcher.unregister(this);
        }
        if (!ListenerUtil.mutListener.listen(7127)) {
            EventBus.getDefault().unregister(this);
        }
        if (!ListenerUtil.mutListener.listen(7128)) {
            mGridAdapter.cancelPendingRequestsForVisibleItems(mRecycler);
        }
        if (!ListenerUtil.mutListener.listen(7129)) {
            super.onStop();
        }
    }

    @Subscribe(sticky = true, threadMode = MAIN)
    public void onEvent(QuickStartEvent event) {
        if (!ListenerUtil.mutListener.listen(7131)) {
            if ((ListenerUtil.mutListener.listen(7130) ? (!isAdded() && getView() == null) : (!isAdded() || getView() == null))) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(7132)) {
            mQuickStartEvent = event;
        }
        if (!ListenerUtil.mutListener.listen(7133)) {
            EventBus.getDefault().removeStickyEvent(event);
        }
        if (!ListenerUtil.mutListener.listen(7138)) {
            if ((ListenerUtil.mutListener.listen(7134) ? (mQuickStartEvent.getTask() == QuickStartExistingSiteTask.UPLOAD_MEDIA || isAdded()) : (mQuickStartEvent.getTask() == QuickStartExistingSiteTask.UPLOAD_MEDIA && isAdded()))) {
                if (!ListenerUtil.mutListener.listen(7135)) {
                    showQuickStartSnackbar();
                }
                if (!ListenerUtil.mutListener.listen(7137)) {
                    if (getActivity() instanceof MediaBrowserActivity) {
                        MediaBrowserActivity activity = (MediaBrowserActivity) getActivity();
                        if (!ListenerUtil.mutListener.listen(7136)) {
                            getView().post(() -> activity.updateMenuNewMediaQuickStartFocusPoint(true));
                        }
                    }
                }
            }
        }
    }

    private void showQuickStartSnackbar() {
        Spannable title = mQuickStartUtilsWrapper.stylizeQuickStartPrompt(requireContext(), R.string.quick_start_dialog_upload_media_message_short_plus, R.drawable.ic_plus_white_12dp);
        if (!ListenerUtil.mutListener.listen(7139)) {
            mSnackbarSequencer.enqueue(new SnackbarItem(new SnackbarItem.Info(getSnackbarParent(), new UiStringText(title), Snackbar.LENGTH_LONG)));
        }
    }

    private View getSnackbarParent() {
        View coordinator = getActivity().findViewById(R.id.coordinator_layout);
        if (!ListenerUtil.mutListener.listen(7140)) {
            if (coordinator != null) {
                return coordinator;
            }
        }
        return getView();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        if (!ListenerUtil.mutListener.listen(7141)) {
            super.onSaveInstanceState(outState);
        }
        if (!ListenerUtil.mutListener.listen(7142)) {
            outState.putParcelable(QuickStartEvent.KEY, mQuickStartEvent);
        }
        if (!ListenerUtil.mutListener.listen(7143)) {
            saveState(outState);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(7144)) {
            super.onCreateView(inflater, container, savedInstanceState);
        }
        View view = inflater.inflate(R.layout.media_grid_fragment, container, false);
        if (!ListenerUtil.mutListener.listen(7145)) {
            mRecycler = view.findViewById(R.id.recycler);
        }
        if (!ListenerUtil.mutListener.listen(7146)) {
            mRecycler.setHasFixedSize(true);
        }
        int numColumns = MediaGridAdapter.getColumnCount(getActivity());
        if (!ListenerUtil.mutListener.listen(7147)) {
            mGridManager = new GridLayoutManager(getActivity(), numColumns);
        }
        if (!ListenerUtil.mutListener.listen(7148)) {
            mRecycler.setLayoutManager(mGridManager);
        }
        if (!ListenerUtil.mutListener.listen(7149)) {
            mRecycler.setAdapter(getAdapter());
        }
        // disable thumbnail loading during a fling to conserve memory
        final int minDistance = WPMediaUtils.getFlingDistanceToDisableThumbLoading(getActivity());
        if (!ListenerUtil.mutListener.listen(7157)) {
            mRecycler.setOnFlingListener(new RecyclerView.OnFlingListener() {

                @Override
                public boolean onFling(int velocityX, int velocityY) {
                    if (!ListenerUtil.mutListener.listen(7156)) {
                        if ((ListenerUtil.mutListener.listen(7154) ? (Math.abs(velocityY) >= minDistance) : (ListenerUtil.mutListener.listen(7153) ? (Math.abs(velocityY) <= minDistance) : (ListenerUtil.mutListener.listen(7152) ? (Math.abs(velocityY) < minDistance) : (ListenerUtil.mutListener.listen(7151) ? (Math.abs(velocityY) != minDistance) : (ListenerUtil.mutListener.listen(7150) ? (Math.abs(velocityY) == minDistance) : (Math.abs(velocityY) > minDistance))))))) {
                            if (!ListenerUtil.mutListener.listen(7155)) {
                                getAdapter().setLoadThumbnails(false);
                            }
                        }
                    }
                    return false;
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(7161)) {
            mRecycler.addOnScrollListener(new RecyclerView.OnScrollListener() {

                @Override
                public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                    if (!ListenerUtil.mutListener.listen(7158)) {
                        super.onScrollStateChanged(recyclerView, newState);
                    }
                    if (!ListenerUtil.mutListener.listen(7160)) {
                        if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                            if (!ListenerUtil.mutListener.listen(7159)) {
                                getAdapter().setLoadThumbnails(true);
                            }
                        }
                    }
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(7162)) {
            mActionableEmptyView = (ActionableEmptyView) view.findViewById(R.id.actionable_empty_view);
        }
        if (!ListenerUtil.mutListener.listen(7166)) {
            mActionableEmptyView.button.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View view) {
                    if (!ListenerUtil.mutListener.listen(7165)) {
                        if ((ListenerUtil.mutListener.listen(7163) ? (isAdded() || getActivity() instanceof MediaBrowserActivity) : (isAdded() && getActivity() instanceof MediaBrowserActivity))) {
                            if (!ListenerUtil.mutListener.listen(7164)) {
                                ((MediaBrowserActivity) getActivity()).showAddMediaPopup();
                            }
                        }
                    }
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(7167)) {
            mRecycler.setEmptyView(mActionableEmptyView);
        }
        if (!ListenerUtil.mutListener.listen(7173)) {
            // swipe to refresh setup
            mSwipeToRefreshHelper = buildSwipeToRefreshHelper((CustomSwipeRefreshLayout) view.findViewById(R.id.ptr_layout), new RefreshListener() {

                @Override
                public void onRefreshStarted() {
                    if (!ListenerUtil.mutListener.listen(7168)) {
                        if (!isAdded()) {
                            return;
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(7171)) {
                        if (!NetworkUtils.checkConnection(getActivity())) {
                            if (!ListenerUtil.mutListener.listen(7169)) {
                                updateEmptyView(EmptyViewMessageType.NETWORK_ERROR);
                            }
                            if (!ListenerUtil.mutListener.listen(7170)) {
                                setRefreshing(false);
                            }
                            return;
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(7172)) {
                        fetchMediaList(false);
                    }
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(7175)) {
            if (savedInstanceState != null) {
                if (!ListenerUtil.mutListener.listen(7174)) {
                    restoreState(savedInstanceState);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(7176)) {
            setFilter(mFilter);
        }
        return view;
    }

    private boolean hasAdapter() {
        return mGridAdapter != null;
    }

    private MediaGridAdapter getAdapter() {
        if (!ListenerUtil.mutListener.listen(7179)) {
            if (!hasAdapter()) {
                if (!ListenerUtil.mutListener.listen(7177)) {
                    mGridAdapter = new MediaGridAdapter(getActivity(), mSite, mBrowserType);
                }
                if (!ListenerUtil.mutListener.listen(7178)) {
                    mGridAdapter.setCallback(this);
                }
            }
        }
        return mGridAdapter;
    }

    @Override
    public void onAttach(Activity activity) {
        if (!ListenerUtil.mutListener.listen(7180)) {
            super.onAttach(activity);
        }
        try {
            if (!ListenerUtil.mutListener.listen(7181)) {
                mListener = (MediaGridListener) activity;
            }
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement MediaGridListener");
        }
    }

    boolean isEmpty() {
        return (ListenerUtil.mutListener.listen(7182) ? (hasAdapter() || getAdapter().isEmpty()) : (hasAdapter() && getAdapter().isEmpty()));
    }

    MediaFilter getFilter() {
        return mFilter;
    }

    /*
     * called when we know we've retrieved and fetched all media for all filters
     */
    private void setHasFetchedMediaForAllFilters() {
        if (!ListenerUtil.mutListener.listen(7190)) {
            {
                long _loopCounter159 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(7189) ? (i >= mFetchedAllFilters.length) : (ListenerUtil.mutListener.listen(7188) ? (i <= mFetchedAllFilters.length) : (ListenerUtil.mutListener.listen(7187) ? (i > mFetchedAllFilters.length) : (ListenerUtil.mutListener.listen(7186) ? (i != mFetchedAllFilters.length) : (ListenerUtil.mutListener.listen(7185) ? (i == mFetchedAllFilters.length) : (i < mFetchedAllFilters.length)))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter159", ++_loopCounter159);
                    if (!ListenerUtil.mutListener.listen(7183)) {
                        mFetchedFilters[i] = true;
                    }
                    if (!ListenerUtil.mutListener.listen(7184)) {
                        mFetchedAllFilters[i] = true;
                    }
                }
            }
        }
    }

    /*
      * this method has two purposes: (1) make sure media that is being deleted still has the right UploadState,
      * as it may have been overwritten by a refresh while deletion was still in progress, (2) remove any local
      * files (ie: media not uploaded yet) that no longer exist (in case user deleted them from the device)
      */
    private void ensureCorrectState(List<MediaModel> mediaModels) {
        if (!ListenerUtil.mutListener.listen(7212)) {
            if ((ListenerUtil.mutListener.listen(7191) ? (isAdded() || getActivity() instanceof MediaBrowserActivity) : (isAdded() && getActivity() instanceof MediaBrowserActivity))) {
                // we only need to check the deletion state if media are currently being deleted
                MediaDeleteService service = ((MediaBrowserActivity) getActivity()).getMediaDeleteService();
                boolean checkDeleteState = (ListenerUtil.mutListener.listen(7192) ? (service != null || service.isAnyMediaBeingDeleted()) : (service != null && service.isAnyMediaBeingDeleted()));
                if (!ListenerUtil.mutListener.listen(7211)) {
                    {
                        long _loopCounter160 = 0;
                        // note we count backwards so we can remove from the list
                        for (int i = (ListenerUtil.mutListener.listen(7210) ? (mediaModels.size() % 1) : (ListenerUtil.mutListener.listen(7209) ? (mediaModels.size() / 1) : (ListenerUtil.mutListener.listen(7208) ? (mediaModels.size() * 1) : (ListenerUtil.mutListener.listen(7207) ? (mediaModels.size() + 1) : (mediaModels.size() - 1))))); (ListenerUtil.mutListener.listen(7206) ? (i <= 0) : (ListenerUtil.mutListener.listen(7205) ? (i > 0) : (ListenerUtil.mutListener.listen(7204) ? (i < 0) : (ListenerUtil.mutListener.listen(7203) ? (i != 0) : (ListenerUtil.mutListener.listen(7202) ? (i == 0) : (i >= 0)))))); i--) {
                            ListenerUtil.loopListener.listen("_loopCounter160", ++_loopCounter160);
                            MediaModel media = mediaModels.get(i);
                            if (!ListenerUtil.mutListener.listen(7195)) {
                                // ensure correct upload state for media being deleted
                                if ((ListenerUtil.mutListener.listen(7193) ? (checkDeleteState || service.isMediaBeingDeleted(media)) : (checkDeleteState && service.isMediaBeingDeleted(media)))) {
                                    if (!ListenerUtil.mutListener.listen(7194)) {
                                        media.setUploadState(MediaUploadState.DELETING);
                                    }
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(7201)) {
                                // remove local media that no longer exists
                                if ((ListenerUtil.mutListener.listen(7196) ? (media.getFilePath() != null || org.wordpress.android.util.MediaUtils.isLocalFile(media.getUploadState())) : (media.getFilePath() != null && org.wordpress.android.util.MediaUtils.isLocalFile(media.getUploadState())))) {
                                    File file = new File(media.getFilePath());
                                    if (!ListenerUtil.mutListener.listen(7200)) {
                                        if (!file.exists()) {
                                            if (!ListenerUtil.mutListener.listen(7197)) {
                                                AppLog.w(AppLog.T.MEDIA, "removing nonexistent local media " + media.getFilePath());
                                            }
                                            if (!ListenerUtil.mutListener.listen(7198)) {
                                                // remove from the store
                                                mDispatcher.dispatch(MediaActionBuilder.newRemoveMediaAction(media));
                                            }
                                            if (!ListenerUtil.mutListener.listen(7199)) {
                                                // remove from the passed list
                                                mediaModels.remove(i);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    List<MediaModel> getFilteredMedia() {
        List<MediaModel> mediaList;
        if (!TextUtils.isEmpty(mSearchTerm)) {
            switch(mFilter) {
                case FILTER_IMAGES:
                    mediaList = mMediaStore.searchSiteImages(mSite, mSearchTerm);
                    break;
                case FILTER_DOCUMENTS:
                    mediaList = mMediaStore.searchSiteDocuments(mSite, mSearchTerm);
                    break;
                case FILTER_VIDEOS:
                    mediaList = mMediaStore.searchSiteVideos(mSite, mSearchTerm);
                    break;
                case FILTER_AUDIO:
                    mediaList = mMediaStore.searchSiteAudio(mSite, mSearchTerm);
                    break;
                default:
                    mediaList = mMediaStore.searchSiteMedia(mSite, mSearchTerm);
                    break;
            }
        } else if (mBrowserType.isSingleImagePicker()) {
            mediaList = mMediaStore.getSiteImages(mSite);
        } else if ((ListenerUtil.mutListener.listen(7215) ? ((ListenerUtil.mutListener.listen(7214) ? ((ListenerUtil.mutListener.listen(7213) ? (mBrowserType.canFilter() && mBrowserType.canOnlyDoInitialFilter()) : (mBrowserType.canFilter() || mBrowserType.canOnlyDoInitialFilter())) && mBrowserType.isSingleFilePicker()) : ((ListenerUtil.mutListener.listen(7213) ? (mBrowserType.canFilter() && mBrowserType.canOnlyDoInitialFilter()) : (mBrowserType.canFilter() || mBrowserType.canOnlyDoInitialFilter())) || mBrowserType.isSingleFilePicker())) && mBrowserType.isSingleAudioFilePicker()) : ((ListenerUtil.mutListener.listen(7214) ? ((ListenerUtil.mutListener.listen(7213) ? (mBrowserType.canFilter() && mBrowserType.canOnlyDoInitialFilter()) : (mBrowserType.canFilter() || mBrowserType.canOnlyDoInitialFilter())) && mBrowserType.isSingleFilePicker()) : ((ListenerUtil.mutListener.listen(7213) ? (mBrowserType.canFilter() && mBrowserType.canOnlyDoInitialFilter()) : (mBrowserType.canFilter() || mBrowserType.canOnlyDoInitialFilter())) || mBrowserType.isSingleFilePicker())) || mBrowserType.isSingleAudioFilePicker()))) {
            mediaList = getMediaList();
        } else {
            List<MediaModel> allMedia = mMediaStore.getAllSiteMedia(mSite);
            mediaList = new ArrayList<>();
            if (!ListenerUtil.mutListener.listen(7220)) {
                {
                    long _loopCounter161 = 0;
                    for (MediaModel media : allMedia) {
                        ListenerUtil.loopListener.listen("_loopCounter161", ++_loopCounter161);
                        String mime = media.getMimeType();
                        if (!ListenerUtil.mutListener.listen(7219)) {
                            if ((ListenerUtil.mutListener.listen(7217) ? (mime != null || ((ListenerUtil.mutListener.listen(7216) ? (mime.startsWith("image") && mime.startsWith("video")) : (mime.startsWith("image") || mime.startsWith("video"))))) : (mime != null && ((ListenerUtil.mutListener.listen(7216) ? (mime.startsWith("image") && mime.startsWith("video")) : (mime.startsWith("image") || mime.startsWith("video"))))))) {
                                if (!ListenerUtil.mutListener.listen(7218)) {
                                    mediaList.add(media);
                                }
                            }
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(7221)) {
            ensureCorrectState(mediaList);
        }
        return mediaList;
    }

    private List<MediaModel> getMediaList() {
        switch(mFilter) {
            case FILTER_IMAGES:
                return mMediaStore.getSiteImages(mSite);
            case FILTER_DOCUMENTS:
                return mMediaStore.getSiteDocuments(mSite);
            case FILTER_VIDEOS:
                return mMediaStore.getSiteVideos(mSite);
            case FILTER_AUDIO:
                return mMediaStore.getSiteAudio(mSite);
            default:
                return mMediaStore.getAllSiteMedia(mSite);
        }
    }

    void setFilter(@NonNull MediaFilter filter) {
        if (!ListenerUtil.mutListener.listen(7222)) {
            mFilter = filter;
        }
        if (!ListenerUtil.mutListener.listen(7223)) {
            getArguments().putSerializable(MediaBrowserActivity.ARG_FILTER, filter);
        }
        if (!ListenerUtil.mutListener.listen(7224)) {
            if (!isAdded()) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(7225)) {
            // when they change the filter
            mRecycler.setItemAnimator(null);
        }
        if (!ListenerUtil.mutListener.listen(7226)) {
            getAdapter().setMediaList(getFilteredMedia());
        }
        if (!ListenerUtil.mutListener.listen(7228)) {
            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    if (!ListenerUtil.mutListener.listen(7227)) {
                        mRecycler.setItemAnimator(new DefaultItemAnimator());
                    }
                }
            }, 500L);
        }
        if (!ListenerUtil.mutListener.listen(7231)) {
            if (mEmptyViewMessageType == EmptyViewMessageType.LOADING) {
                if (!ListenerUtil.mutListener.listen(7230)) {
                    updateEmptyView(EmptyViewMessageType.NO_CONTENT);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(7229)) {
                    updateEmptyView(mEmptyViewMessageType);
                }
            }
        }
        boolean hasFetchedThisFilter = mFetchedFilters[filter.getValue()];
        if (!ListenerUtil.mutListener.listen(7236)) {
            if ((ListenerUtil.mutListener.listen(7232) ? (!hasFetchedThisFilter || NetworkUtils.isNetworkAvailable(getActivity())) : (!hasFetchedThisFilter && NetworkUtils.isNetworkAvailable(getActivity())))) {
                if (!ListenerUtil.mutListener.listen(7234)) {
                    if (isEmpty()) {
                        if (!ListenerUtil.mutListener.listen(7233)) {
                            mSwipeToRefreshHelper.setRefreshing(true);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(7235)) {
                    fetchMediaList(false);
                }
            }
        }
    }

    @Override
    public void onAdapterFetchMoreData() {
        boolean hasFetchedAll = mFetchedAllFilters[mFilter.getValue()];
        if (!ListenerUtil.mutListener.listen(7238)) {
            if (!hasFetchedAll) {
                if (!ListenerUtil.mutListener.listen(7237)) {
                    fetchMediaList(true);
                }
            }
        }
    }

    @Override
    public void onAdapterItemClicked(int position, boolean isLongPress) {
        int localMediaId = getAdapter().getLocalMediaIdAtPosition(position);
        if (!ListenerUtil.mutListener.listen(7239)) {
            mListener.onMediaItemSelected(localMediaId, isLongPress);
        }
    }

    @Override
    public void onAdapterSelectionCountChanged(int count) {
        if (!ListenerUtil.mutListener.listen(7240)) {
            if (!mBrowserType.canMultiselect()) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(7249)) {
            if ((ListenerUtil.mutListener.listen(7246) ? ((ListenerUtil.mutListener.listen(7245) ? (count >= 0) : (ListenerUtil.mutListener.listen(7244) ? (count <= 0) : (ListenerUtil.mutListener.listen(7243) ? (count > 0) : (ListenerUtil.mutListener.listen(7242) ? (count < 0) : (ListenerUtil.mutListener.listen(7241) ? (count != 0) : (count == 0)))))) || mActionMode != null) : ((ListenerUtil.mutListener.listen(7245) ? (count >= 0) : (ListenerUtil.mutListener.listen(7244) ? (count <= 0) : (ListenerUtil.mutListener.listen(7243) ? (count > 0) : (ListenerUtil.mutListener.listen(7242) ? (count < 0) : (ListenerUtil.mutListener.listen(7241) ? (count != 0) : (count == 0)))))) && mActionMode != null))) {
                if (!ListenerUtil.mutListener.listen(7248)) {
                    mActionMode.finish();
                }
            } else if (mActionMode == null) {
                if (!ListenerUtil.mutListener.listen(7247)) {
                    ((AppCompatActivity) getActivity()).startSupportActionMode(new ActionModeCallback());
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(7250)) {
            updateActionModeTitle(count);
        }
    }

    @Override
    public void onAdapterRequestRetry(int position) {
        int localMediaId = getAdapter().getLocalMediaIdAtPosition(position);
        if (!ListenerUtil.mutListener.listen(7251)) {
            mListener.onMediaRequestRetry(localMediaId);
        }
    }

    @Override
    public void onAdapterRequestDelete(int position) {
        int localMediaId = getAdapter().getLocalMediaIdAtPosition(position);
        if (!ListenerUtil.mutListener.listen(7252)) {
            mListener.onMediaRequestDelete(localMediaId);
        }
    }

    @SuppressWarnings("unused")
    @Subscribe(threadMode = MAIN)
    public void onMediaListFetched(OnMediaListFetched event) {
        if (!ListenerUtil.mutListener.listen(7254)) {
            if (event.isError()) {
                if (!ListenerUtil.mutListener.listen(7253)) {
                    handleFetchAllMediaError(event);
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(7255)) {
            handleFetchAllMediaSuccess(event);
        }
    }

    public void showActionableEmptyViewButton(boolean show) {
        if (!ListenerUtil.mutListener.listen(7256)) {
            mActionableEmptyView.button.setVisibility(show ? View.VISIBLE : View.GONE);
        }
    }

    /*
     * load the adapter from the local store
     */
    void reload() {
        if (!ListenerUtil.mutListener.listen(7258)) {
            if (isAdded()) {
                if (!ListenerUtil.mutListener.listen(7257)) {
                    getAdapter().setMediaList(getFilteredMedia());
                }
            }
        }
    }

    /*
     * update just the passed media item - if it doesn't exist it may be because
     * it was just added, so reload the adapter
     */
    void updateMediaItem(@NonNull MediaModel media, boolean forceUpdate) {
        if (!ListenerUtil.mutListener.listen(7260)) {
            if ((ListenerUtil.mutListener.listen(7259) ? (!isAdded() && !hasAdapter()) : (!isAdded() || !hasAdapter()))) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(7263)) {
            if (getAdapter().mediaExists(media)) {
                if (!ListenerUtil.mutListener.listen(7262)) {
                    getAdapter().updateMediaItem(media, forceUpdate);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(7261)) {
                    reload();
                }
            }
        }
    }

    void removeMediaItem(@NonNull MediaModel media) {
        if (!ListenerUtil.mutListener.listen(7265)) {
            if ((ListenerUtil.mutListener.listen(7264) ? (!isAdded() && !hasAdapter()) : (!isAdded() || !hasAdapter()))) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(7266)) {
            getAdapter().removeMediaItem(media);
        }
    }

    public void search(String searchTerm) {
        if (!ListenerUtil.mutListener.listen(7267)) {
            mSearchTerm = searchTerm;
        }
        List<MediaModel> mediaList = getFilteredMedia();
        if (!ListenerUtil.mutListener.listen(7268)) {
            mGridAdapter.setMediaList(mediaList);
        }
        if (!ListenerUtil.mutListener.listen(7270)) {
            if (isEmpty()) {
                if (!ListenerUtil.mutListener.listen(7269)) {
                    updateEmptyView(EmptyViewMessageType.NO_CONTENT);
                }
            }
        }
    }

    public void clearSelection() {
        if (!ListenerUtil.mutListener.listen(7271)) {
            getAdapter().clearSelection();
        }
    }

    public void removeFromMultiSelect(int localMediaId) {
        if (!ListenerUtil.mutListener.listen(7275)) {
            if ((ListenerUtil.mutListener.listen(7273) ? ((ListenerUtil.mutListener.listen(7272) ? (hasAdapter() || getAdapter().isInMultiSelect()) : (hasAdapter() && getAdapter().isInMultiSelect())) || getAdapter().isItemSelected(localMediaId)) : ((ListenerUtil.mutListener.listen(7272) ? (hasAdapter() || getAdapter().isInMultiSelect()) : (hasAdapter() && getAdapter().isInMultiSelect())) && getAdapter().isItemSelected(localMediaId)))) {
                if (!ListenerUtil.mutListener.listen(7274)) {
                    getAdapter().removeSelectionByLocalId(localMediaId);
                }
            }
        }
    }

    private void setRefreshing(boolean isRefreshing) {
        if (!ListenerUtil.mutListener.listen(7276)) {
            mIsRefreshing = isRefreshing;
        }
        if (!ListenerUtil.mutListener.listen(7278)) {
            if (!isRefreshing) {
                if (!ListenerUtil.mutListener.listen(7277)) {
                    mSwipeToRefreshHelper.setRefreshing(false);
                }
            }
        }
    }

    private void setSwipeToRefreshEnabled(boolean enabled) {
        if (!ListenerUtil.mutListener.listen(7280)) {
            if (isAdded()) {
                if (!ListenerUtil.mutListener.listen(7279)) {
                    mSwipeToRefreshHelper.setEnabled(enabled);
                }
            }
        }
    }

    private void updateEmptyView(EmptyViewMessageType emptyViewMessageType) {
        if (!ListenerUtil.mutListener.listen(7281)) {
            mEmptyViewMessageType = emptyViewMessageType;
        }
        if (!ListenerUtil.mutListener.listen(7283)) {
            if ((ListenerUtil.mutListener.listen(7282) ? (!isAdded() && mActionableEmptyView == null) : (!isAdded() || mActionableEmptyView == null))) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(7290)) {
            if (isEmpty()) {
                int stringId;
                switch(emptyViewMessageType) {
                    case LOADING:
                        stringId = R.string.media_fetching;
                        break;
                    case NO_CONTENT:
                        if (!TextUtils.isEmpty(mSearchTerm)) {
                            if (!ListenerUtil.mutListener.listen(7287)) {
                                mActionableEmptyView.updateLayoutForSearch(true, 0);
                            }
                            stringId = R.string.media_empty_search_list;
                        } else {
                            if (!ListenerUtil.mutListener.listen(7285)) {
                                mActionableEmptyView.updateLayoutForSearch(false, 0);
                            }
                            if (!ListenerUtil.mutListener.listen(7286)) {
                                mActionableEmptyView.image.setVisibility(View.VISIBLE);
                            }
                            switch(mFilter) {
                                case FILTER_IMAGES:
                                    stringId = R.string.media_empty_image_list;
                                    break;
                                case FILTER_VIDEOS:
                                    stringId = R.string.media_empty_videos_list;
                                    break;
                                case FILTER_DOCUMENTS:
                                    stringId = R.string.media_empty_documents_list;
                                    break;
                                case FILTER_AUDIO:
                                    stringId = R.string.media_empty_audio_list;
                                    break;
                                default:
                                    stringId = R.string.media_empty_list;
                                    break;
                            }
                        }
                        break;
                    case NETWORK_ERROR:
                        stringId = R.string.no_network_message;
                        break;
                    case PERMISSION_ERROR:
                        stringId = R.string.media_error_no_permission;
                        break;
                    default:
                        stringId = R.string.error_refresh_media;
                        break;
                }
                if (!ListenerUtil.mutListener.listen(7288)) {
                    mActionableEmptyView.title.setText(stringId);
                }
                if (!ListenerUtil.mutListener.listen(7289)) {
                    mActionableEmptyView.setVisibility(View.VISIBLE);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(7284)) {
                    mActionableEmptyView.setVisibility(View.GONE);
                }
            }
        }
    }

    private void hideEmptyView() {
        if (!ListenerUtil.mutListener.listen(7293)) {
            if ((ListenerUtil.mutListener.listen(7291) ? (isAdded() || mActionableEmptyView != null) : (isAdded() && mActionableEmptyView != null))) {
                if (!ListenerUtil.mutListener.listen(7292)) {
                    mActionableEmptyView.setVisibility(View.GONE);
                }
            }
        }
    }

    private void saveState(Bundle outState) {
        if (!ListenerUtil.mutListener.listen(7294)) {
            outState.putIntArray(BUNDLE_SELECTED_STATES, ListUtils.toIntArray(getAdapter().getSelectedItems()));
        }
        if (!ListenerUtil.mutListener.listen(7295)) {
            outState.putInt(BUNDLE_SCROLL_POSITION, mGridManager.findFirstCompletelyVisibleItemPosition());
        }
        if (!ListenerUtil.mutListener.listen(7296)) {
            outState.putBoolean(BUNDLE_IN_MULTI_SELECT_MODE, getAdapter().isInMultiSelect());
        }
        if (!ListenerUtil.mutListener.listen(7297)) {
            outState.putString(BUNDLE_EMPTY_VIEW_MESSAGE, mEmptyViewMessageType.name());
        }
        if (!ListenerUtil.mutListener.listen(7298)) {
            outState.putBooleanArray(BUNDLE_FETCHED_FILTERS, mFetchedFilters);
        }
        if (!ListenerUtil.mutListener.listen(7299)) {
            outState.putBooleanArray(BUNDLE_RETRIEVED_ALL_FILTERS, mFetchedAllFilters);
        }
    }

    private void updateActionModeTitle(int selectCount) {
        if (!ListenerUtil.mutListener.listen(7301)) {
            if (mActionMode != null) {
                if (!ListenerUtil.mutListener.listen(7300)) {
                    mActionMode.setTitle(String.format(getString(R.string.cab_selected), selectCount));
                }
            }
        }
    }

    private void restoreState(@NonNull Bundle savedInstanceState) {
        boolean isInMultiSelectMode = savedInstanceState.getBoolean(BUNDLE_IN_MULTI_SELECT_MODE);
        if (!ListenerUtil.mutListener.listen(7306)) {
            if (isInMultiSelectMode) {
                if (!ListenerUtil.mutListener.listen(7302)) {
                    getAdapter().setInMultiSelect(true);
                }
                if (!ListenerUtil.mutListener.listen(7305)) {
                    if (savedInstanceState.containsKey(BUNDLE_SELECTED_STATES)) {
                        ArrayList<Integer> selectedItems = ListUtils.fromIntArray(savedInstanceState.getIntArray(BUNDLE_SELECTED_STATES));
                        if (!ListenerUtil.mutListener.listen(7303)) {
                            getAdapter().setSelectedItems(selectedItems);
                        }
                        if (!ListenerUtil.mutListener.listen(7304)) {
                            setSwipeToRefreshEnabled(false);
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(7307)) {
            mFetchedFilters = savedInstanceState.getBooleanArray(BUNDLE_FETCHED_FILTERS);
        }
        if (!ListenerUtil.mutListener.listen(7308)) {
            mFetchedAllFilters = savedInstanceState.getBooleanArray(BUNDLE_RETRIEVED_ALL_FILTERS);
        }
        EmptyViewMessageType emptyType = EmptyViewMessageType.getEnumFromString(savedInstanceState.getString(BUNDLE_EMPTY_VIEW_MESSAGE));
        if (!ListenerUtil.mutListener.listen(7309)) {
            updateEmptyView(emptyType);
        }
    }

    private void fetchMediaList(boolean loadMore) {
        if (!ListenerUtil.mutListener.listen(7312)) {
            // do not refresh if there is no network
            if (!NetworkUtils.isNetworkAvailable(getActivity())) {
                if (!ListenerUtil.mutListener.listen(7310)) {
                    updateEmptyView(EmptyViewMessageType.NETWORK_ERROR);
                }
                if (!ListenerUtil.mutListener.listen(7311)) {
                    setRefreshing(false);
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(7314)) {
            // do not refresh if in search
            if (!TextUtils.isEmpty(mSearchTerm)) {
                if (!ListenerUtil.mutListener.listen(7313)) {
                    setRefreshing(false);
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(7322)) {
            if (!mIsRefreshing) {
                if (!ListenerUtil.mutListener.listen(7315)) {
                    setRefreshing(true);
                }
                if (!ListenerUtil.mutListener.listen(7316)) {
                    updateEmptyView(EmptyViewMessageType.LOADING);
                }
                if (!ListenerUtil.mutListener.listen(7318)) {
                    if (loadMore) {
                        if (!ListenerUtil.mutListener.listen(7317)) {
                            mSwipeToRefreshHelper.setRefreshing(true);
                        }
                    }
                }
                FetchMediaListPayload payload = new FetchMediaListPayload(mSite, NUM_MEDIA_PER_FETCH, loadMore, mFilter.toMimeType());
                if (!ListenerUtil.mutListener.listen(7319)) {
                    mDispatcher.dispatch(MediaActionBuilder.newFetchMediaListAction(payload));
                }
                if (!ListenerUtil.mutListener.listen(7321)) {
                    if (!loadMore) {
                        if (!ListenerUtil.mutListener.listen(7320)) {
                            // Fetch site to refresh space quota in activity.
                            mDispatcher.dispatch(SiteActionBuilder.newFetchSiteAction(mSite));
                        }
                    }
                }
            }
        }
    }

    private void handleFetchAllMediaSuccess(OnMediaListFetched event) {
        if (!ListenerUtil.mutListener.listen(7323)) {
            if (!isAdded()) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(7325)) {
            // make sure this request was for the current filter
            if ((ListenerUtil.mutListener.listen(7324) ? (event.mimeType != null || MediaFilter.fromMimeType(event.mimeType) != mFilter) : (event.mimeType != null && MediaFilter.fromMimeType(event.mimeType) != mFilter))) {
                return;
            }
        }
        List<MediaModel> filteredMedia = getFilteredMedia();
        if (!ListenerUtil.mutListener.listen(7326)) {
            ensureCorrectState(filteredMedia);
        }
        if (!ListenerUtil.mutListener.listen(7327)) {
            getAdapter().setMediaList(filteredMedia);
        }
        boolean hasRetrievedAll = !event.canLoadMore;
        if (!ListenerUtil.mutListener.listen(7328)) {
            getAdapter().setHasRetrievedAll(hasRetrievedAll);
        }
        int position = mFilter.getValue();
        if (!ListenerUtil.mutListener.listen(7329)) {
            mFetchedFilters[position] = true;
        }
        if (!ListenerUtil.mutListener.listen(7333)) {
            if (hasRetrievedAll) {
                if (!ListenerUtil.mutListener.listen(7332)) {
                    if (mFilter == MediaFilter.FILTER_ALL) {
                        if (!ListenerUtil.mutListener.listen(7331)) {
                            setHasFetchedMediaForAllFilters();
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(7330)) {
                            mFetchedAllFilters[position] = true;
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(7334)) {
            setRefreshing(false);
        }
        if (!ListenerUtil.mutListener.listen(7335)) {
            updateEmptyView(EmptyViewMessageType.NO_CONTENT);
        }
    }

    private void handleFetchAllMediaError(OnMediaListFetched event) {
        MediaErrorType errorType = event.error.type;
        if (!ListenerUtil.mutListener.listen(7336)) {
            AppLog.e(AppLog.T.MEDIA, "Media error occurred: " + errorType);
        }
        if (!ListenerUtil.mutListener.listen(7337)) {
            if (!isAdded()) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(7339)) {
            if ((ListenerUtil.mutListener.listen(7338) ? (event.mimeType != null || MediaFilter.fromMimeType(event.mimeType) != mFilter) : (event.mimeType != null && MediaFilter.fromMimeType(event.mimeType) != mFilter))) {
                return;
            }
        }
        int toastResId;
        if (errorType == MediaErrorType.AUTHORIZATION_REQUIRED) {
            if (!ListenerUtil.mutListener.listen(7341)) {
                updateEmptyView(EmptyViewMessageType.PERMISSION_ERROR);
            }
            toastResId = R.string.media_error_no_permission;
        } else {
            if (!ListenerUtil.mutListener.listen(7340)) {
                updateEmptyView(EmptyViewMessageType.GENERIC_ERROR);
            }
            toastResId = R.string.error_refresh_media;
        }
        if (!ListenerUtil.mutListener.listen(7343)) {
            // only show the toast if the list is NOT empty since the empty view shows the same message
            if (!isEmpty()) {
                if (!ListenerUtil.mutListener.listen(7342)) {
                    ToastUtils.showToast(getActivity(), getString(toastResId));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(7344)) {
            setRefreshing(false);
        }
        if (!ListenerUtil.mutListener.listen(7345)) {
            setHasFetchedMediaForAllFilters();
        }
        if (!ListenerUtil.mutListener.listen(7346)) {
            getAdapter().setHasRetrievedAll(true);
        }
    }

    private void setResultIdsAndFinish() {
        Intent intent = new Intent();
        if (!ListenerUtil.mutListener.listen(7356)) {
            if ((ListenerUtil.mutListener.listen(7351) ? (getAdapter().getSelectedItemCount() >= 0) : (ListenerUtil.mutListener.listen(7350) ? (getAdapter().getSelectedItemCount() <= 0) : (ListenerUtil.mutListener.listen(7349) ? (getAdapter().getSelectedItemCount() < 0) : (ListenerUtil.mutListener.listen(7348) ? (getAdapter().getSelectedItemCount() != 0) : (ListenerUtil.mutListener.listen(7347) ? (getAdapter().getSelectedItemCount() == 0) : (getAdapter().getSelectedItemCount() > 0))))))) {
                ArrayList<Long> remoteMediaIds = new ArrayList<>();
                if (!ListenerUtil.mutListener.listen(7354)) {
                    {
                        long _loopCounter162 = 0;
                        for (Integer localId : getAdapter().getSelectedItems()) {
                            ListenerUtil.loopListener.listen("_loopCounter162", ++_loopCounter162);
                            MediaModel media = mMediaStore.getMediaWithLocalId(localId);
                            if (!ListenerUtil.mutListener.listen(7353)) {
                                if (media != null) {
                                    if (!ListenerUtil.mutListener.listen(7352)) {
                                        remoteMediaIds.add(media.getMediaId());
                                    }
                                }
                            }
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(7355)) {
                    intent.putExtra(MediaBrowserActivity.RESULT_IDS, ListUtils.toLongArray(remoteMediaIds));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(7357)) {
            getActivity().setResult(RESULT_OK, intent);
        }
        if (!ListenerUtil.mutListener.listen(7358)) {
            getActivity().finish();
        }
    }

    private final class ActionModeCallback implements ActionMode.Callback {

        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            if (!ListenerUtil.mutListener.listen(7359)) {
                mActionMode = mode;
            }
            int selectCount = getAdapter().getSelectedItemCount();
            MenuInflater inflater = mode.getMenuInflater();
            if (!ListenerUtil.mutListener.listen(7360)) {
                inflater.inflate(R.menu.media_multiselect, menu);
            }
            if (!ListenerUtil.mutListener.listen(7361)) {
                setSwipeToRefreshEnabled(false);
            }
            if (!ListenerUtil.mutListener.listen(7362)) {
                getAdapter().setInMultiSelect(true);
            }
            if (!ListenerUtil.mutListener.listen(7363)) {
                updateActionModeTitle(selectCount);
            }
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            MenuItem mnuConfirm = menu.findItem(R.id.mnu_confirm_selection);
            if (!ListenerUtil.mutListener.listen(7364)) {
                mnuConfirm.setVisible(mBrowserType.isPicker());
            }
            if (!ListenerUtil.mutListener.listen(7365)) {
                AccessibilityUtils.setActionModeDoneButtonContentDescription(getActivity(), getString(R.string.cancel));
            }
            return true;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            if (!ListenerUtil.mutListener.listen(7367)) {
                if (item.getItemId() == R.id.mnu_confirm_selection) {
                    if (!ListenerUtil.mutListener.listen(7366)) {
                        setResultIdsAndFinish();
                    }
                }
            }
            return true;
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {
            if (!ListenerUtil.mutListener.listen(7368)) {
                setSwipeToRefreshEnabled(true);
            }
            if (!ListenerUtil.mutListener.listen(7369)) {
                getAdapter().setInMultiSelect(false);
            }
            if (!ListenerUtil.mutListener.listen(7370)) {
                mActionMode = null;
            }
        }
    }
}
