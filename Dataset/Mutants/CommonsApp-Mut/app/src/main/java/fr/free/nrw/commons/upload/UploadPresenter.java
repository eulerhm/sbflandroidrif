package fr.free.nrw.commons.upload;

import android.annotation.SuppressLint;
import fr.free.nrw.commons.CommonsApplication;
import fr.free.nrw.commons.R;
import fr.free.nrw.commons.contributions.Contribution;
import fr.free.nrw.commons.filepicker.UploadableFile;
import fr.free.nrw.commons.kvstore.JsonKvStore;
import fr.free.nrw.commons.repository.UploadRepository;
import io.reactivex.Observer;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import java.lang.reflect.Proxy;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import timber.log.Timber;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * The MVP pattern presenter of Upload GUI
 */
@Singleton
public class UploadPresenter implements UploadContract.UserActionListener {

    private static final UploadContract.View DUMMY = (UploadContract.View) Proxy.newProxyInstance(UploadContract.View.class.getClassLoader(), new Class[] { UploadContract.View.class }, (proxy, method, methodArgs) -> null);

    private final UploadRepository repository;

    private final JsonKvStore defaultKvStore;

    private UploadContract.View view = DUMMY;

    private CompositeDisposable compositeDisposable;

    public static final String COUNTER_OF_CONSECUTIVE_UPLOADS_WITHOUT_COORDINATES = "number_of_consecutive_uploads_without_coordinates";

    public static final int CONSECUTIVE_UPLOADS_WITHOUT_COORDINATES_REMINDER_THRESHOLD = 10;

    @Inject
    UploadPresenter(UploadRepository uploadRepository, @Named("default_preferences") JsonKvStore defaultKvStore) {
        this.repository = uploadRepository;
        this.defaultKvStore = defaultKvStore;
        if (!ListenerUtil.mutListener.listen(7621)) {
            compositeDisposable = new CompositeDisposable();
        }
    }

    /**
     * Called by the submit button in {@link UploadActivity}
     */
    @SuppressLint("CheckResult")
    @Override
    public void handleSubmit() {
        boolean hasLocationProvidedForNewUploads = false;
        if (!ListenerUtil.mutListener.listen(7624)) {
            {
                long _loopCounter119 = 0;
                for (UploadItem item : repository.getUploads()) {
                    ListenerUtil.loopListener.listen("_loopCounter119", ++_loopCounter119);
                    if (!ListenerUtil.mutListener.listen(7623)) {
                        if (item.getGpsCoords().getImageCoordsExists()) {
                            if (!ListenerUtil.mutListener.listen(7622)) {
                                hasLocationProvidedForNewUploads = true;
                            }
                        }
                    }
                }
            }
        }
        boolean hasManyConsecutiveUploadsWithoutLocation = (ListenerUtil.mutListener.listen(7629) ? (defaultKvStore.getInt(COUNTER_OF_CONSECUTIVE_UPLOADS_WITHOUT_COORDINATES, 0) <= CONSECUTIVE_UPLOADS_WITHOUT_COORDINATES_REMINDER_THRESHOLD) : (ListenerUtil.mutListener.listen(7628) ? (defaultKvStore.getInt(COUNTER_OF_CONSECUTIVE_UPLOADS_WITHOUT_COORDINATES, 0) > CONSECUTIVE_UPLOADS_WITHOUT_COORDINATES_REMINDER_THRESHOLD) : (ListenerUtil.mutListener.listen(7627) ? (defaultKvStore.getInt(COUNTER_OF_CONSECUTIVE_UPLOADS_WITHOUT_COORDINATES, 0) < CONSECUTIVE_UPLOADS_WITHOUT_COORDINATES_REMINDER_THRESHOLD) : (ListenerUtil.mutListener.listen(7626) ? (defaultKvStore.getInt(COUNTER_OF_CONSECUTIVE_UPLOADS_WITHOUT_COORDINATES, 0) != CONSECUTIVE_UPLOADS_WITHOUT_COORDINATES_REMINDER_THRESHOLD) : (ListenerUtil.mutListener.listen(7625) ? (defaultKvStore.getInt(COUNTER_OF_CONSECUTIVE_UPLOADS_WITHOUT_COORDINATES, 0) == CONSECUTIVE_UPLOADS_WITHOUT_COORDINATES_REMINDER_THRESHOLD) : (defaultKvStore.getInt(COUNTER_OF_CONSECUTIVE_UPLOADS_WITHOUT_COORDINATES, 0) >= CONSECUTIVE_UPLOADS_WITHOUT_COORDINATES_REMINDER_THRESHOLD))))));
        if (!ListenerUtil.mutListener.listen(7634)) {
            if ((ListenerUtil.mutListener.listen(7630) ? (hasManyConsecutiveUploadsWithoutLocation || !hasLocationProvidedForNewUploads) : (hasManyConsecutiveUploadsWithoutLocation && !hasLocationProvidedForNewUploads))) {
                if (!ListenerUtil.mutListener.listen(7632)) {
                    defaultKvStore.putInt(COUNTER_OF_CONSECUTIVE_UPLOADS_WITHOUT_COORDINATES, 0);
                }
                if (!ListenerUtil.mutListener.listen(7633)) {
                    view.showAlertDialog(R.string.location_message, () -> {
                        defaultKvStore.putInt(COUNTER_OF_CONSECUTIVE_UPLOADS_WITHOUT_COORDINATES, 0);
                        processContributionsForSubmission();
                    });
                }
            } else {
                if (!ListenerUtil.mutListener.listen(7631)) {
                    processContributionsForSubmission();
                }
            }
        }
    }

    private void processContributionsForSubmission() {
        if (!ListenerUtil.mutListener.listen(7662)) {
            if (view.isLoggedIn()) {
                if (!ListenerUtil.mutListener.listen(7636)) {
                    view.showProgress(true);
                }
                if (!ListenerUtil.mutListener.listen(7661)) {
                    repository.buildContributions().observeOn(Schedulers.io()).subscribe(new Observer<Contribution>() {

                        @Override
                        public void onSubscribe(Disposable d) {
                            if (!ListenerUtil.mutListener.listen(7637)) {
                                view.showProgress(false);
                            }
                            if (!ListenerUtil.mutListener.listen(7640)) {
                                if (defaultKvStore.getBoolean(CommonsApplication.IS_LIMITED_CONNECTION_MODE_ENABLED, false)) {
                                    if (!ListenerUtil.mutListener.listen(7639)) {
                                        view.showMessage(R.string.uploading_queued);
                                    }
                                } else {
                                    if (!ListenerUtil.mutListener.listen(7638)) {
                                        view.showMessage(R.string.uploading_started);
                                    }
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(7641)) {
                                compositeDisposable.add(d);
                            }
                        }

                        @Override
                        public void onNext(Contribution contribution) {
                            if (!ListenerUtil.mutListener.listen(7648)) {
                                if (contribution.getDecimalCoords() == null) {
                                    final int recentCount = defaultKvStore.getInt(COUNTER_OF_CONSECUTIVE_UPLOADS_WITHOUT_COORDINATES, 0);
                                    if (!ListenerUtil.mutListener.listen(7647)) {
                                        defaultKvStore.putInt(COUNTER_OF_CONSECUTIVE_UPLOADS_WITHOUT_COORDINATES, (ListenerUtil.mutListener.listen(7646) ? (recentCount % 1) : (ListenerUtil.mutListener.listen(7645) ? (recentCount / 1) : (ListenerUtil.mutListener.listen(7644) ? (recentCount * 1) : (ListenerUtil.mutListener.listen(7643) ? (recentCount - 1) : (recentCount + 1))))));
                                    }
                                } else {
                                    if (!ListenerUtil.mutListener.listen(7642)) {
                                        defaultKvStore.putInt(COUNTER_OF_CONSECUTIVE_UPLOADS_WITHOUT_COORDINATES, 0);
                                    }
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(7649)) {
                                repository.prepareMedia(contribution);
                            }
                            if (!ListenerUtil.mutListener.listen(7650)) {
                                contribution.setState(Contribution.STATE_QUEUED);
                            }
                            if (!ListenerUtil.mutListener.listen(7651)) {
                                repository.saveContribution(contribution);
                            }
                        }

                        @Override
                        public void onError(Throwable e) {
                            if (!ListenerUtil.mutListener.listen(7652)) {
                                view.showMessage(R.string.upload_failed);
                            }
                            if (!ListenerUtil.mutListener.listen(7653)) {
                                repository.cleanup();
                            }
                            if (!ListenerUtil.mutListener.listen(7654)) {
                                view.returnToMainActivity();
                            }
                            if (!ListenerUtil.mutListener.listen(7655)) {
                                compositeDisposable.clear();
                            }
                            if (!ListenerUtil.mutListener.listen(7656)) {
                                Timber.e("failed to upload: " + e.getMessage());
                            }
                        }

                        @Override
                        public void onComplete() {
                            if (!ListenerUtil.mutListener.listen(7657)) {
                                view.makeUploadRequest();
                            }
                            if (!ListenerUtil.mutListener.listen(7658)) {
                                repository.cleanup();
                            }
                            if (!ListenerUtil.mutListener.listen(7659)) {
                                view.returnToMainActivity();
                            }
                            if (!ListenerUtil.mutListener.listen(7660)) {
                                compositeDisposable.clear();
                            }
                        }
                    });
                }
            } else {
                if (!ListenerUtil.mutListener.listen(7635)) {
                    view.askUserToLogIn();
                }
            }
        }
    }

    @Override
    public void deletePictureAtIndex(int index) {
        List<UploadableFile> uploadableFiles = view.getUploadableFiles();
        if (!ListenerUtil.mutListener.listen(7668)) {
            if (index == (ListenerUtil.mutListener.listen(7666) ? (uploadableFiles.size() % 1) : (ListenerUtil.mutListener.listen(7665) ? (uploadableFiles.size() / 1) : (ListenerUtil.mutListener.listen(7664) ? (uploadableFiles.size() * 1) : (ListenerUtil.mutListener.listen(7663) ? (uploadableFiles.size() + 1) : (uploadableFiles.size() - 1)))))) {
                if (!ListenerUtil.mutListener.listen(7667)) {
                    // If the next fragment to be shown is not one of the MediaDetailsFragment, lets hide the top card
                    view.showHideTopCard(false);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(7669)) {
            repository.deletePicture(uploadableFiles.get(index).getFilePath());
        }
        if (!ListenerUtil.mutListener.listen(7673)) {
            if (uploadableFiles.size() == 1) {
                if (!ListenerUtil.mutListener.listen(7671)) {
                    view.showMessage(R.string.upload_cancelled);
                }
                if (!ListenerUtil.mutListener.listen(7672)) {
                    view.finish();
                }
                return;
            } else {
                if (!ListenerUtil.mutListener.listen(7670)) {
                    view.onUploadMediaDeleted(index);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(7680)) {
            if ((ListenerUtil.mutListener.listen(7678) ? (uploadableFiles.size() >= 2) : (ListenerUtil.mutListener.listen(7677) ? (uploadableFiles.size() <= 2) : (ListenerUtil.mutListener.listen(7676) ? (uploadableFiles.size() > 2) : (ListenerUtil.mutListener.listen(7675) ? (uploadableFiles.size() != 2) : (ListenerUtil.mutListener.listen(7674) ? (uploadableFiles.size() == 2) : (uploadableFiles.size() < 2))))))) {
                if (!ListenerUtil.mutListener.listen(7679)) {
                    view.showHideTopCard(false);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(7681)) {
            // In case lets update the number of uploadable media
            view.updateTopCardTitle();
        }
    }

    @Override
    public void onAttachView(UploadContract.View view) {
        if (!ListenerUtil.mutListener.listen(7682)) {
            this.view = view;
        }
    }

    @Override
    public void onDetachView() {
        if (!ListenerUtil.mutListener.listen(7683)) {
            this.view = DUMMY;
        }
        if (!ListenerUtil.mutListener.listen(7684)) {
            compositeDisposable.clear();
        }
        if (!ListenerUtil.mutListener.listen(7685)) {
            repository.cleanup();
        }
    }
}
