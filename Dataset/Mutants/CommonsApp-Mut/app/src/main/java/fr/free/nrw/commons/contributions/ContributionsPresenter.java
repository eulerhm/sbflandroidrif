package fr.free.nrw.commons.contributions;

import androidx.work.ExistingWorkPolicy;
import fr.free.nrw.commons.MediaDataExtractor;
import fr.free.nrw.commons.contributions.ContributionsContract.UserActionListener;
import fr.free.nrw.commons.di.CommonsApplicationModule;
import fr.free.nrw.commons.upload.worker.WorkRequestHelper;
import io.reactivex.Scheduler;
import io.reactivex.disposables.CompositeDisposable;
import javax.inject.Inject;
import javax.inject.Named;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * The presenter class for Contributions
 */
public class ContributionsPresenter implements UserActionListener {

    private final ContributionsRepository repository;

    private final Scheduler ioThreadScheduler;

    private CompositeDisposable compositeDisposable;

    private ContributionsContract.View view;

    @Inject
    MediaDataExtractor mediaDataExtractor;

    @Inject
    ContributionsPresenter(ContributionsRepository repository, @Named(CommonsApplicationModule.IO_THREAD) Scheduler ioThreadScheduler) {
        this.repository = repository;
        this.ioThreadScheduler = ioThreadScheduler;
    }

    @Override
    public void onAttachView(ContributionsContract.View view) {
        if (!ListenerUtil.mutListener.listen(1154)) {
            this.view = view;
        }
        if (!ListenerUtil.mutListener.listen(1155)) {
            compositeDisposable = new CompositeDisposable();
        }
    }

    @Override
    public void onDetachView() {
        if (!ListenerUtil.mutListener.listen(1156)) {
            this.view = null;
        }
        if (!ListenerUtil.mutListener.listen(1157)) {
            compositeDisposable.clear();
        }
    }

    @Override
    public Contribution getContributionsWithTitle(String title) {
        return repository.getContributionWithFileName(title);
    }

    /**
     * Delete a failed contribution from the local db
     * @param contribution
     */
    @Override
    public void deleteUpload(Contribution contribution) {
        if (!ListenerUtil.mutListener.listen(1158)) {
            compositeDisposable.add(repository.deleteContributionFromDB(contribution).subscribeOn(ioThreadScheduler).subscribe());
        }
    }

    /**
     * Update the contribution's state in the databse, upon completion, trigger the workmanager to
     * process this contribution
     *
     * @param contribution
     */
    @Override
    public void saveContribution(Contribution contribution) {
        if (!ListenerUtil.mutListener.listen(1159)) {
            compositeDisposable.add(repository.save(contribution).subscribeOn(ioThreadScheduler).subscribe(() -> WorkRequestHelper.Companion.makeOneTimeWorkRequest(view.getContext().getApplicationContext(), ExistingWorkPolicy.KEEP)));
        }
    }
}
