package org.wordpress.android.ui.media.services;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import androidx.annotation.NonNull;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.wordpress.android.WordPress;
import org.wordpress.android.fluxc.Dispatcher;
import org.wordpress.android.fluxc.generated.MediaActionBuilder;
import org.wordpress.android.fluxc.model.MediaModel;
import org.wordpress.android.fluxc.model.SiteModel;
import org.wordpress.android.fluxc.store.MediaStore;
import org.wordpress.android.fluxc.store.MediaStore.MediaPayload;
import org.wordpress.android.fluxc.store.MediaStore.OnMediaChanged;
import org.wordpress.android.util.AppLog;
import org.wordpress.android.util.AppLog.T;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class MediaDeleteService extends Service {

    public static final String SITE_KEY = "mediaSite";

    public static final String MEDIA_LIST_KEY = "mediaList";

    public class MediaDeleteBinder extends Binder {

        public MediaDeleteService getService() {
            return MediaDeleteService.this;
        }

        public void addMediaToDeleteQueue(@NonNull MediaModel media) {
            if (!ListenerUtil.mutListener.listen(6365)) {
                getDeleteQueue().add(media);
            }
            if (!ListenerUtil.mutListener.listen(6366)) {
                deleteNextInQueue();
            }
        }

        public void removeMediaFromDeleteQueue(@NonNull MediaModel media) {
            if (!ListenerUtil.mutListener.listen(6367)) {
                getDeleteQueue().remove(media);
            }
            if (!ListenerUtil.mutListener.listen(6368)) {
                deleteNextInQueue();
            }
        }
    }

    private final IBinder mBinder = new MediaDeleteBinder();

    // required for payloads
    private SiteModel mSite;

    @Inject
    Dispatcher mDispatcher;

    @Inject
    MediaStore mMediaStore;

    private MediaModel mCurrentDelete;

    private List<MediaModel> mDeleteQueue;

    private List<MediaModel> mCompletedItems;

    @Override
    public void onCreate() {
        if (!ListenerUtil.mutListener.listen(6369)) {
            super.onCreate();
        }
        if (!ListenerUtil.mutListener.listen(6370)) {
            ((WordPress) getApplication()).component().inject(this);
        }
        if (!ListenerUtil.mutListener.listen(6371)) {
            mDispatcher.register(this);
        }
        if (!ListenerUtil.mutListener.listen(6372)) {
            mCurrentDelete = null;
        }
    }

    @Override
    public void onDestroy() {
        if (!ListenerUtil.mutListener.listen(6373)) {
            mDispatcher.unregister(this);
        }
        if (!ListenerUtil.mutListener.listen(6374)) {
            // TODO: if event not dispatched for ongoing delete cancel it and dispatch cancel event
            super.onDestroy();
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (!ListenerUtil.mutListener.listen(6377)) {
            // stop service if no site is given
            if ((ListenerUtil.mutListener.listen(6375) ? (intent == null && !intent.hasExtra(SITE_KEY)) : (intent == null || !intent.hasExtra(SITE_KEY)))) {
                if (!ListenerUtil.mutListener.listen(6376)) {
                    stopSelf();
                }
                return START_NOT_STICKY;
            }
        }
        if (!ListenerUtil.mutListener.listen(6378)) {
            mSite = (SiteModel) intent.getSerializableExtra(SITE_KEY);
        }
        if (!ListenerUtil.mutListener.listen(6379)) {
            mDeleteQueue = (List<MediaModel>) intent.getSerializableExtra(MEDIA_LIST_KEY);
        }
        if (!ListenerUtil.mutListener.listen(6380)) {
            // start deleting queued media
            deleteNextInQueue();
        }
        // only run while app process is running, allows service to be stopped by user force closing the app
        return START_NOT_STICKY;
    }

    @SuppressWarnings("unused")
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMediaChanged(OnMediaChanged event) {
        if (!ListenerUtil.mutListener.listen(6384)) {
            // event for unknown media, ignoring
            if ((ListenerUtil.mutListener.listen(6382) ? ((ListenerUtil.mutListener.listen(6381) ? (event.mediaList == null && event.mediaList.isEmpty()) : (event.mediaList == null || event.mediaList.isEmpty())) && !matchesInProgressMedia(event.mediaList.get(0))) : ((ListenerUtil.mutListener.listen(6381) ? (event.mediaList == null && event.mediaList.isEmpty()) : (event.mediaList == null || event.mediaList.isEmpty())) || !matchesInProgressMedia(event.mediaList.get(0))))) {
                if (!ListenerUtil.mutListener.listen(6383)) {
                    AppLog.w(T.MEDIA, "Media event not recognized: " + event.mediaList);
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(6387)) {
            if (event.isError()) {
                if (!ListenerUtil.mutListener.listen(6386)) {
                    handleOnMediaChangedError(event);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(6385)) {
                    handleMediaChangedSuccess(event);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(6388)) {
            deleteNextInQueue();
        }
    }

    @NonNull
    public List<MediaModel> getDeleteQueue() {
        if (!ListenerUtil.mutListener.listen(6390)) {
            if (mDeleteQueue == null) {
                if (!ListenerUtil.mutListener.listen(6389)) {
                    mDeleteQueue = new ArrayList<>();
                }
            }
        }
        return mDeleteQueue;
    }

    @NonNull
    public List<MediaModel> getCompletedItems() {
        if (!ListenerUtil.mutListener.listen(6392)) {
            if (mCompletedItems == null) {
                if (!ListenerUtil.mutListener.listen(6391)) {
                    mCompletedItems = new ArrayList<>();
                }
            }
        }
        return mCompletedItems;
    }

    public boolean isMediaBeingDeleted(@NonNull MediaModel media) {
        if (!ListenerUtil.mutListener.listen(6395)) {
            if (mDeleteQueue != null) {
                if (!ListenerUtil.mutListener.listen(6394)) {
                    {
                        long _loopCounter143 = 0;
                        for (MediaModel deletingMedia : mDeleteQueue) {
                            ListenerUtil.loopListener.listen("_loopCounter143", ++_loopCounter143);
                            if (!ListenerUtil.mutListener.listen(6393)) {
                                if (deletingMedia.getId() == media.getId()) {
                                    return true;
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    public boolean isAnyMediaBeingDeleted() {
        return (ListenerUtil.mutListener.listen(6401) ? (mDeleteQueue != null || (ListenerUtil.mutListener.listen(6400) ? (mDeleteQueue.size() >= 0) : (ListenerUtil.mutListener.listen(6399) ? (mDeleteQueue.size() <= 0) : (ListenerUtil.mutListener.listen(6398) ? (mDeleteQueue.size() < 0) : (ListenerUtil.mutListener.listen(6397) ? (mDeleteQueue.size() != 0) : (ListenerUtil.mutListener.listen(6396) ? (mDeleteQueue.size() == 0) : (mDeleteQueue.size() > 0))))))) : (mDeleteQueue != null && (ListenerUtil.mutListener.listen(6400) ? (mDeleteQueue.size() >= 0) : (ListenerUtil.mutListener.listen(6399) ? (mDeleteQueue.size() <= 0) : (ListenerUtil.mutListener.listen(6398) ? (mDeleteQueue.size() < 0) : (ListenerUtil.mutListener.listen(6397) ? (mDeleteQueue.size() != 0) : (ListenerUtil.mutListener.listen(6396) ? (mDeleteQueue.size() == 0) : (mDeleteQueue.size() > 0))))))));
    }

    private void handleMediaChangedSuccess(@NonNull OnMediaChanged event) {
        if (!ListenerUtil.mutListener.listen(6408)) {
            switch(event.cause) {
                case DELETE_MEDIA:
                    if (!ListenerUtil.mutListener.listen(6404)) {
                        if (mCurrentDelete != null) {
                            if (!ListenerUtil.mutListener.listen(6402)) {
                                AppLog.d(T.MEDIA, mCurrentDelete.getTitle() + " successfully deleted!");
                            }
                            if (!ListenerUtil.mutListener.listen(6403)) {
                                completeCurrentDelete();
                            }
                        }
                    }
                    break;
                case REMOVE_MEDIA:
                    if (!ListenerUtil.mutListener.listen(6407)) {
                        if (mCurrentDelete != null) {
                            if (!ListenerUtil.mutListener.listen(6405)) {
                                AppLog.d(T.MEDIA, "Successfully deleted " + mCurrentDelete.getTitle());
                            }
                            if (!ListenerUtil.mutListener.listen(6406)) {
                                completeCurrentDelete();
                            }
                        }
                    }
                    break;
            }
        }
    }

    private void handleOnMediaChangedError(@NonNull OnMediaChanged event) {
        MediaModel media = event.mediaList.get(0);
        if (!ListenerUtil.mutListener.listen(6419)) {
            switch(event.error.type) {
                case AUTHORIZATION_REQUIRED:
                    if (!ListenerUtil.mutListener.listen(6409)) {
                        AppLog.v(T.MEDIA, "Authorization required. Stopping MediaDeleteService.");
                    }
                    if (!ListenerUtil.mutListener.listen(6410)) {
                        // stop delete service until authorized to perform actions on site
                        stopSelf();
                    }
                    break;
                case NULL_MEDIA_ARG:
                    if (!ListenerUtil.mutListener.listen(6411)) {
                        // shouldn't happen, get back to deleting the queue
                        AppLog.d(T.MEDIA, "Null media argument supplied, skipping current delete.");
                    }
                    if (!ListenerUtil.mutListener.listen(6412)) {
                        completeCurrentDelete();
                    }
                    break;
                case NOT_FOUND:
                    if (!ListenerUtil.mutListener.listen(6413)) {
                        if (media == null) {
                            break;
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(6414)) {
                        AppLog.d(T.MEDIA, "Could not find media (id=" + media.getMediaId() + "). on remote");
                    }
                    if (!ListenerUtil.mutListener.listen(6415)) {
                        // remove media from local database
                        mDispatcher.dispatch(MediaActionBuilder.newRemoveMediaAction(mCurrentDelete));
                    }
                    break;
                case PARSE_ERROR:
                    if (!ListenerUtil.mutListener.listen(6416)) {
                        AppLog.d(T.MEDIA, "Error parsing reponse to " + event.cause.toString() + ".");
                    }
                    if (!ListenerUtil.mutListener.listen(6417)) {
                        completeCurrentDelete();
                    }
                    break;
                default:
                    if (!ListenerUtil.mutListener.listen(6418)) {
                        completeCurrentDelete();
                    }
                    break;
            }
        }
    }

    /**
     * Delete next media item in queue. Only one media item is deleted at a time.
     */
    private void deleteNextInQueue() {
        if (!ListenerUtil.mutListener.listen(6421)) {
            // waiting for response to current delete request
            if (mCurrentDelete != null) {
                if (!ListenerUtil.mutListener.listen(6420)) {
                    AppLog.i(T.MEDIA, "Ignoring request to deleteNextInQueue, only one media item can be deleted at a time.");
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(6424)) {
            // somehow lost our reference to the site, stop service
            if (mSite == null) {
                if (!ListenerUtil.mutListener.listen(6422)) {
                    AppLog.i(T.MEDIA, "Unexpected state, site is null. Stopping MediaDeleteService.");
                }
                if (!ListenerUtil.mutListener.listen(6423)) {
                    stopSelf();
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(6425)) {
            mCurrentDelete = nextMediaToDelete();
        }
        if (!ListenerUtil.mutListener.listen(6428)) {
            // no more items to delete, stop service
            if (mCurrentDelete == null) {
                if (!ListenerUtil.mutListener.listen(6426)) {
                    AppLog.v(T.MEDIA, "No more media items to delete. Stopping MediaDeleteService.");
                }
                if (!ListenerUtil.mutListener.listen(6427)) {
                    stopSelf();
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(6429)) {
            dispatchDeleteAction(mCurrentDelete);
        }
    }

    private void dispatchDeleteAction(@NonNull MediaModel media) {
        if (!ListenerUtil.mutListener.listen(6430)) {
            AppLog.v(T.MEDIA, "Deleting " + media.getTitle() + " (id=" + media.getMediaId() + ")");
        }
        MediaPayload payload = new MediaPayload(mSite, media);
        if (!ListenerUtil.mutListener.listen(6431)) {
            mDispatcher.dispatch(MediaActionBuilder.newDeleteMediaAction(payload));
        }
    }

    /**
     * Compares site ID and media ID to determine if a given media item matches the current media item being deleted.
     */
    private boolean matchesInProgressMedia(@NonNull final MediaModel media) {
        return (ListenerUtil.mutListener.listen(6433) ? ((ListenerUtil.mutListener.listen(6432) ? (mCurrentDelete != null || media.getLocalSiteId() == mCurrentDelete.getLocalSiteId()) : (mCurrentDelete != null && media.getLocalSiteId() == mCurrentDelete.getLocalSiteId())) || media.getMediaId() == mCurrentDelete.getMediaId()) : ((ListenerUtil.mutListener.listen(6432) ? (mCurrentDelete != null || media.getLocalSiteId() == mCurrentDelete.getLocalSiteId()) : (mCurrentDelete != null && media.getLocalSiteId() == mCurrentDelete.getLocalSiteId())) && media.getMediaId() == mCurrentDelete.getMediaId()));
    }

    /**
     * @return the next item in the queue to delete, null if queue is empty
     */
    private MediaModel nextMediaToDelete() {
        if (!ListenerUtil.mutListener.listen(6434)) {
            if (!getDeleteQueue().isEmpty()) {
                return getDeleteQueue().get(0);
            }
        }
        return null;
    }

    /**
     * Moves current delete from the queue into the completed list.
     */
    private void completeCurrentDelete() {
        if (!ListenerUtil.mutListener.listen(6438)) {
            if (mCurrentDelete != null) {
                if (!ListenerUtil.mutListener.listen(6435)) {
                    getCompletedItems().add(mCurrentDelete);
                }
                if (!ListenerUtil.mutListener.listen(6436)) {
                    getDeleteQueue().remove(mCurrentDelete);
                }
                if (!ListenerUtil.mutListener.listen(6437)) {
                    mCurrentDelete = null;
                }
            }
        }
    }
}
