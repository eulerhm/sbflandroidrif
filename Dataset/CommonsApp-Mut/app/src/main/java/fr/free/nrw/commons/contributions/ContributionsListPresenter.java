package fr.free.nrw.commons.contributions;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.paging.DataSource;
import androidx.paging.DataSource.Factory;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;
import fr.free.nrw.commons.contributions.ContributionsListContract.UserActionListener;
import fr.free.nrw.commons.di.CommonsApplicationModule;
import io.reactivex.Scheduler;
import io.reactivex.disposables.CompositeDisposable;
import javax.inject.Inject;
import javax.inject.Named;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * The presenter class for Contributions
 */
public class ContributionsListPresenter implements UserActionListener {

    private final ContributionBoundaryCallback contributionBoundaryCallback;

    private final ContributionsRepository repository;

    private final Scheduler ioThreadScheduler;

    private final CompositeDisposable compositeDisposable;

    private final ContributionsRemoteDataSource contributionsRemoteDataSource;

    LiveData<PagedList<Contribution>> contributionList;

    @Inject
    ContributionsListPresenter(final ContributionBoundaryCallback contributionBoundaryCallback, final ContributionsRemoteDataSource contributionsRemoteDataSource, final ContributionsRepository repository, @Named(CommonsApplicationModule.IO_THREAD) final Scheduler ioThreadScheduler) {
        this.contributionBoundaryCallback = contributionBoundaryCallback;
        this.repository = repository;
        this.ioThreadScheduler = ioThreadScheduler;
        this.contributionsRemoteDataSource = contributionsRemoteDataSource;
        compositeDisposable = new CompositeDisposable();
    }

    @Override
    public void onAttachView(final ContributionsListContract.View view) {
    }

    /**
     * Setup the paged list. This method sets the configuration for paged list and ties it up with
     * the live data object. This method can be tweaked to update the lazy loading behavior of the
     * contributions list
     */
    void setup(String userName, boolean isSelf) {
        final PagedList.Config pagedListConfig = (new PagedList.Config.Builder()).setPrefetchDistance(50).setPageSize(10).build();
        Factory<Integer, Contribution> factory;
        boolean shouldSetBoundaryCallback;
        if (!isSelf) {
            if (!ListenerUtil.mutListener.listen(574)) {
                // creating a new DataSource for them
                contributionsRemoteDataSource.setUserName(userName);
            }
            factory = new Factory<Integer, Contribution>() {

                @NonNull
                @Override
                public DataSource<Integer, Contribution> create() {
                    return contributionsRemoteDataSource;
                }
            };
            shouldSetBoundaryCallback = false;
        } else {
            if (!ListenerUtil.mutListener.listen(573)) {
                contributionBoundaryCallback.setUserName(userName);
            }
            shouldSetBoundaryCallback = true;
            factory = repository.fetchContributions();
        }
        LivePagedListBuilder livePagedListBuilder = new LivePagedListBuilder(factory, pagedListConfig);
        if (!ListenerUtil.mutListener.listen(576)) {
            if (shouldSetBoundaryCallback) {
                if (!ListenerUtil.mutListener.listen(575)) {
                    livePagedListBuilder.setBoundaryCallback(contributionBoundaryCallback);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(577)) {
            contributionList = livePagedListBuilder.build();
        }
    }

    @Override
    public void onDetachView() {
        if (!ListenerUtil.mutListener.listen(578)) {
            compositeDisposable.clear();
        }
        if (!ListenerUtil.mutListener.listen(579)) {
            contributionsRemoteDataSource.dispose();
        }
        if (!ListenerUtil.mutListener.listen(580)) {
            contributionBoundaryCallback.dispose();
        }
    }

    /**
     * Delete a failed contribution from the local db
     */
    @Override
    public void deleteUpload(final Contribution contribution) {
        if (!ListenerUtil.mutListener.listen(581)) {
            compositeDisposable.add(repository.deleteContributionFromDB(contribution).subscribeOn(ioThreadScheduler).subscribe());
        }
    }
}
