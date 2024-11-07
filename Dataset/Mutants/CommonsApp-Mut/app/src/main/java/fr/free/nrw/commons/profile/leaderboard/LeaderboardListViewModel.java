package fr.free.nrw.commons.profile.leaderboard;

import static fr.free.nrw.commons.profile.leaderboard.LeaderboardConstants.PAGE_SIZE;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;
import fr.free.nrw.commons.auth.SessionManager;
import fr.free.nrw.commons.mwapi.OkHttpJsonApiClient;
import io.reactivex.disposables.CompositeDisposable;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Extends the ViewModel class and creates the LeaderboardList View Model
 */
public class LeaderboardListViewModel extends ViewModel {

    private DataSourceFactory dataSourceFactory;

    private LiveData<PagedList<LeaderboardList>> listLiveData;

    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    private LiveData<String> progressLoadStatus = new MutableLiveData<>();

    /**
     * Constructor for a new LeaderboardListViewModel
     * @param okHttpJsonApiClient
     * @param sessionManager
     */
    public LeaderboardListViewModel(OkHttpJsonApiClient okHttpJsonApiClient, SessionManager sessionManager) {
        if (!ListenerUtil.mutListener.listen(5455)) {
            dataSourceFactory = new DataSourceFactory(okHttpJsonApiClient, compositeDisposable, sessionManager);
        }
        if (!ListenerUtil.mutListener.listen(5456)) {
            initializePaging();
        }
    }

    /**
     * Initialises the paging
     */
    private void initializePaging() {
        PagedList.Config pagedListConfig = new PagedList.Config.Builder().setEnablePlaceholders(false).setInitialLoadSizeHint(PAGE_SIZE).setPageSize(PAGE_SIZE).build();
        if (!ListenerUtil.mutListener.listen(5457)) {
            listLiveData = new LivePagedListBuilder<>(dataSourceFactory, pagedListConfig).build();
        }
        if (!ListenerUtil.mutListener.listen(5458)) {
            progressLoadStatus = Transformations.switchMap(dataSourceFactory.getMutableLiveData(), DataSourceClass::getProgressLiveStatus);
        }
    }

    /**
     * Refreshes the paged list with the new params and starts the loading of new data
     * @param duration
     * @param category
     * @param limit
     * @param offset
     */
    public void refresh(String duration, String category, int limit, int offset) {
        if (!ListenerUtil.mutListener.listen(5459)) {
            dataSourceFactory.setDuration(duration);
        }
        if (!ListenerUtil.mutListener.listen(5460)) {
            dataSourceFactory.setCategory(category);
        }
        if (!ListenerUtil.mutListener.listen(5461)) {
            dataSourceFactory.setLimit(limit);
        }
        if (!ListenerUtil.mutListener.listen(5462)) {
            dataSourceFactory.setOffset(offset);
        }
        if (!ListenerUtil.mutListener.listen(5463)) {
            dataSourceFactory.getMutableLiveData().getValue().invalidate();
        }
    }

    /**
     * Sets the new params for the paged list API calls
     * @param duration
     * @param category
     * @param limit
     * @param offset
     */
    public void setParams(String duration, String category, int limit, int offset) {
        if (!ListenerUtil.mutListener.listen(5464)) {
            dataSourceFactory.setDuration(duration);
        }
        if (!ListenerUtil.mutListener.listen(5465)) {
            dataSourceFactory.setCategory(category);
        }
        if (!ListenerUtil.mutListener.listen(5466)) {
            dataSourceFactory.setLimit(limit);
        }
        if (!ListenerUtil.mutListener.listen(5467)) {
            dataSourceFactory.setOffset(offset);
        }
    }

    /**
     * @return the loading status of paged list
     */
    public LiveData<String> getProgressLoadStatus() {
        return progressLoadStatus;
    }

    /**
     * @return the paged list with live data
     */
    public LiveData<PagedList<LeaderboardList>> getListLiveData() {
        return listLiveData;
    }

    @Override
    protected void onCleared() {
        if (!ListenerUtil.mutListener.listen(5468)) {
            super.onCleared();
        }
        if (!ListenerUtil.mutListener.listen(5469)) {
            compositeDisposable.clear();
        }
    }
}
