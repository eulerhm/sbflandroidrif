package fr.free.nrw.commons.profile.leaderboard;

import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;
import fr.free.nrw.commons.auth.SessionManager;
import fr.free.nrw.commons.mwapi.OkHttpJsonApiClient;
import io.reactivex.disposables.CompositeDisposable;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * This class will create a new instance of the data source class on pagination
 */
public class DataSourceFactory extends DataSource.Factory<Integer, LeaderboardList> {

    private MutableLiveData<DataSourceClass> liveData;

    private OkHttpJsonApiClient okHttpJsonApiClient;

    private CompositeDisposable compositeDisposable;

    private SessionManager sessionManager;

    private String duration;

    private String category;

    private int limit;

    private int offset;

    /**
     * Gets the current set leaderboard list duration
     */
    public String getDuration() {
        return duration;
    }

    /**
     * Sets the current set leaderboard duration with the new duration
     */
    public void setDuration(final String duration) {
        if (!ListenerUtil.mutListener.listen(5484)) {
            this.duration = duration;
        }
    }

    /**
     * Gets the current set leaderboard list category
     */
    public String getCategory() {
        return category;
    }

    /**
     * Sets the current set leaderboard category with the new category
     */
    public void setCategory(final String category) {
        if (!ListenerUtil.mutListener.listen(5485)) {
            this.category = category;
        }
    }

    /**
     * Gets the current set leaderboard list limit
     */
    public int getLimit() {
        return limit;
    }

    /**
     * Sets the current set leaderboard limit with the new limit
     */
    public void setLimit(final int limit) {
        if (!ListenerUtil.mutListener.listen(5486)) {
            this.limit = limit;
        }
    }

    /**
     * Gets the current set leaderboard list offset
     */
    public int getOffset() {
        return offset;
    }

    /**
     * Sets the current set leaderboard offset with the new offset
     */
    public void setOffset(final int offset) {
        if (!ListenerUtil.mutListener.listen(5487)) {
            this.offset = offset;
        }
    }

    /**
     * Constructor for DataSourceFactory class
     * @param okHttpJsonApiClient client for OKhttp
     * @param compositeDisposable composite disposable
     * @param sessionManager sessionManager
     */
    public DataSourceFactory(OkHttpJsonApiClient okHttpJsonApiClient, CompositeDisposable compositeDisposable, SessionManager sessionManager) {
        if (!ListenerUtil.mutListener.listen(5488)) {
            this.okHttpJsonApiClient = okHttpJsonApiClient;
        }
        if (!ListenerUtil.mutListener.listen(5489)) {
            this.compositeDisposable = compositeDisposable;
        }
        if (!ListenerUtil.mutListener.listen(5490)) {
            this.sessionManager = sessionManager;
        }
        if (!ListenerUtil.mutListener.listen(5491)) {
            liveData = new MutableLiveData<>();
        }
    }

    /**
     * @return the live data
     */
    public MutableLiveData<DataSourceClass> getMutableLiveData() {
        return liveData;
    }

    /**
     * Creates the new instance of data source class
     * @return
     */
    @Override
    public DataSource<Integer, LeaderboardList> create() {
        DataSourceClass dataSourceClass = new DataSourceClass(okHttpJsonApiClient, sessionManager, duration, category, limit, offset);
        if (!ListenerUtil.mutListener.listen(5492)) {
            liveData.postValue(dataSourceClass);
        }
        return dataSourceClass;
    }
}
