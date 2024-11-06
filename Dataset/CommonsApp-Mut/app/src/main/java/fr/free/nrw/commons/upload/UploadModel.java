package fr.free.nrw.commons.upload;

import android.content.Context;
import android.net.Uri;
import fr.free.nrw.commons.Media;
import fr.free.nrw.commons.auth.SessionManager;
import fr.free.nrw.commons.contributions.Contribution;
import fr.free.nrw.commons.filepicker.UploadableFile;
import fr.free.nrw.commons.kvstore.JsonKvStore;
import fr.free.nrw.commons.location.LatLng;
import fr.free.nrw.commons.nearby.Place;
import fr.free.nrw.commons.settings.Prefs;
import fr.free.nrw.commons.upload.depicts.DepictsFragment;
import fr.free.nrw.commons.upload.structure.depictions.DepictedItem;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.disposables.CompositeDisposable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import org.jetbrains.annotations.NotNull;
import timber.log.Timber;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

@Singleton
public class UploadModel {

    private final JsonKvStore store;

    private final List<String> licenses;

    private final Context context;

    private String license;

    private final Map<String, String> licensesByName;

    private final List<UploadItem> items = new ArrayList<>();

    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    private final SessionManager sessionManager;

    private final FileProcessor fileProcessor;

    private final ImageProcessingService imageProcessingService;

    private final List<String> selectedCategories = new ArrayList<>();

    private final List<DepictedItem> selectedDepictions = new ArrayList<>();

    /**
     * Existing depicts which are selected
     */
    private List<String> selectedExistingDepictions = new ArrayList<>();

    @Inject
    UploadModel(@Named("licenses") final List<String> licenses, @Named("default_preferences") final JsonKvStore store, @Named("licenses_by_name") final Map<String, String> licensesByName, final Context context, final SessionManager sessionManager, final FileProcessor fileProcessor, final ImageProcessingService imageProcessingService) {
        this.licenses = licenses;
        this.store = store;
        if (!ListenerUtil.mutListener.listen(6885)) {
            this.license = store.getString(Prefs.DEFAULT_LICENSE, Prefs.Licenses.CC_BY_SA_3);
        }
        this.licensesByName = licensesByName;
        this.context = context;
        this.sessionManager = sessionManager;
        this.fileProcessor = fileProcessor;
        this.imageProcessingService = imageProcessingService;
    }

    /**
     * cleanup the resources, I am Singleton, preparing for fresh upload
     */
    public void cleanUp() {
        if (!ListenerUtil.mutListener.listen(6886)) {
            compositeDisposable.clear();
        }
        if (!ListenerUtil.mutListener.listen(6887)) {
            fileProcessor.cleanup();
        }
        if (!ListenerUtil.mutListener.listen(6888)) {
            items.clear();
        }
        if (!ListenerUtil.mutListener.listen(6889)) {
            selectedCategories.clear();
        }
        if (!ListenerUtil.mutListener.listen(6890)) {
            selectedDepictions.clear();
        }
        if (!ListenerUtil.mutListener.listen(6891)) {
            selectedExistingDepictions.clear();
        }
    }

    public void setSelectedCategories(List<String> selectedCategories) {
        if (!ListenerUtil.mutListener.listen(6892)) {
            this.selectedCategories.clear();
        }
        if (!ListenerUtil.mutListener.listen(6893)) {
            this.selectedCategories.addAll(selectedCategories);
        }
    }

    /**
     * pre process a one item at a time
     */
    public Observable<UploadItem> preProcessImage(final UploadableFile uploadableFile, final Place place, final SimilarImageInterface similarImageInterface, LatLng inAppPictureLocation) {
        return Observable.just(createAndAddUploadItem(uploadableFile, place, similarImageInterface, inAppPictureLocation));
    }

    public Single<Integer> getImageQuality(final UploadItem uploadItem, LatLng inAppPictureLocation) {
        return imageProcessingService.validateImage(uploadItem, inAppPictureLocation);
    }

    private UploadItem createAndAddUploadItem(final UploadableFile uploadableFile, final Place place, final SimilarImageInterface similarImageInterface, LatLng inAppPictureLocation) {
        final UploadableFile.DateTimeWithSource dateTimeWithSource = uploadableFile.getFileCreatedDate(context);
        long fileCreatedDate = -1;
        String createdTimestampSource = "";
        String fileCreatedDateString = "";
        if (!ListenerUtil.mutListener.listen(6897)) {
            if (dateTimeWithSource != null) {
                if (!ListenerUtil.mutListener.listen(6894)) {
                    fileCreatedDate = dateTimeWithSource.getEpochDate();
                }
                if (!ListenerUtil.mutListener.listen(6895)) {
                    fileCreatedDateString = dateTimeWithSource.getDateString();
                }
                if (!ListenerUtil.mutListener.listen(6896)) {
                    createdTimestampSource = dateTimeWithSource.getSource();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(6898)) {
            Timber.d("File created date is %d", fileCreatedDate);
        }
        final ImageCoordinates imageCoordinates = fileProcessor.processFileCoordinates(similarImageInterface, uploadableFile.getFilePath(), inAppPictureLocation);
        final UploadItem uploadItem = new UploadItem(Uri.parse(uploadableFile.getFilePath()), uploadableFile.getMimeType(context), imageCoordinates, place, fileCreatedDate, createdTimestampSource, uploadableFile.getContentUri(), fileCreatedDateString);
        if (!ListenerUtil.mutListener.listen(6899)) {
            // This is to avoid multiple instances of uploadItem of same file passed around.
            if (items.contains(uploadItem)) {
                return items.get(items.indexOf(uploadItem));
            }
        }
        if (!ListenerUtil.mutListener.listen(6901)) {
            if (place != null) {
                if (!ListenerUtil.mutListener.listen(6900)) {
                    uploadItem.getUploadMediaDetails().set(0, new UploadMediaDetail(place));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(6903)) {
            if (!items.contains(uploadItem)) {
                if (!ListenerUtil.mutListener.listen(6902)) {
                    items.add(uploadItem);
                }
            }
        }
        return uploadItem;
    }

    public int getCount() {
        return items.size();
    }

    public List<UploadItem> getUploads() {
        return items;
    }

    public List<String> getLicenses() {
        return licenses;
    }

    public String getSelectedLicense() {
        return license;
    }

    public void setSelectedLicense(final String licenseName) {
        if (!ListenerUtil.mutListener.listen(6904)) {
            this.license = licensesByName.get(licenseName);
        }
        if (!ListenerUtil.mutListener.listen(6905)) {
            store.putString(Prefs.DEFAULT_LICENSE, license);
        }
    }

    public Observable<Contribution> buildContributions() {
        return Observable.fromIterable(items).map(item -> {
            String imageSHA1 = FileUtils.getSHA1(context.getContentResolver().openInputStream(item.getContentUri()));
            final Contribution contribution = new Contribution(item, sessionManager, newListOf(selectedDepictions), newListOf(selectedCategories), imageSHA1);
            contribution.setHasInvalidLocation(item.hasInvalidLocation());
            Timber.d("Created timestamp while building contribution is %s, %s", item.getCreatedTimestamp(), new Date(item.getCreatedTimestamp()));
            if (item.getCreatedTimestamp() != -1L) {
                contribution.setDateCreated(new Date(item.getCreatedTimestamp()));
                contribution.setDateCreatedSource(item.getCreatedTimestampSource());
            }
            if (contribution.getWikidataPlace() != null) {
                if (item.isWLMUpload()) {
                    contribution.getWikidataPlace().setMonumentUpload(true);
                } else {
                    contribution.getWikidataPlace().setMonumentUpload(false);
                }
            }
            contribution.setCountryCode(item.getCountryCode());
            return contribution;
        });
    }

    public void deletePicture(final String filePath) {
        final Iterator<UploadItem> iterator = items.iterator();
        if (!ListenerUtil.mutListener.listen(6908)) {
            {
                long _loopCounter106 = 0;
                while (iterator.hasNext()) {
                    ListenerUtil.loopListener.listen("_loopCounter106", ++_loopCounter106);
                    if (!ListenerUtil.mutListener.listen(6907)) {
                        if (iterator.next().getMediaUri().toString().contains(filePath)) {
                            if (!ListenerUtil.mutListener.listen(6906)) {
                                iterator.remove();
                            }
                            break;
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(6910)) {
            if (items.isEmpty()) {
                if (!ListenerUtil.mutListener.listen(6909)) {
                    cleanUp();
                }
            }
        }
    }

    public List<UploadItem> getItems() {
        return items;
    }

    public void onDepictItemClicked(DepictedItem depictedItem, Media media) {
        if (!ListenerUtil.mutListener.listen(6925)) {
            if (media == null) {
                if (!ListenerUtil.mutListener.listen(6924)) {
                    if (depictedItem.isSelected()) {
                        if (!ListenerUtil.mutListener.listen(6923)) {
                            selectedDepictions.add(depictedItem);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(6922)) {
                            selectedDepictions.remove(depictedItem);
                        }
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(6921)) {
                    if (depictedItem.isSelected()) {
                        if (!ListenerUtil.mutListener.listen(6920)) {
                            if (media.getDepictionIds().contains(depictedItem.getId())) {
                                if (!ListenerUtil.mutListener.listen(6919)) {
                                    selectedExistingDepictions.add(depictedItem.getId());
                                }
                            } else {
                                if (!ListenerUtil.mutListener.listen(6918)) {
                                    selectedDepictions.add(depictedItem);
                                }
                            }
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(6917)) {
                            if (media.getDepictionIds().contains(depictedItem.getId())) {
                                if (!ListenerUtil.mutListener.listen(6912)) {
                                    selectedExistingDepictions.remove(depictedItem.getId());
                                }
                                if (!ListenerUtil.mutListener.listen(6916)) {
                                    if (!media.getDepictionIds().contains(depictedItem.getId())) {
                                        final List<String> depictsList = new ArrayList<>();
                                        if (!ListenerUtil.mutListener.listen(6913)) {
                                            depictsList.add(depictedItem.getId());
                                        }
                                        if (!ListenerUtil.mutListener.listen(6914)) {
                                            depictsList.addAll(media.getDepictionIds());
                                        }
                                        if (!ListenerUtil.mutListener.listen(6915)) {
                                            media.setDepictionIds(depictsList);
                                        }
                                    }
                                }
                            } else {
                                if (!ListenerUtil.mutListener.listen(6911)) {
                                    selectedDepictions.remove(depictedItem);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @NotNull
    private <T> List<T> newListOf(final List<T> items) {
        return items != null ? new ArrayList<>(items) : new ArrayList<>();
    }

    public void useSimilarPictureCoordinates(final ImageCoordinates imageCoordinates, final int uploadItemIndex) {
        if (!ListenerUtil.mutListener.listen(6926)) {
            fileProcessor.prePopulateCategoriesAndDepictionsBy(imageCoordinates);
        }
        if (!ListenerUtil.mutListener.listen(6927)) {
            items.get(uploadItemIndex).setGpsCoords(imageCoordinates);
        }
    }

    public List<DepictedItem> getSelectedDepictions() {
        return selectedDepictions;
    }

    /**
     * Provides selected existing depicts
     *
     * @return selected existing depicts
     */
    public List<String> getSelectedExistingDepictions() {
        return selectedExistingDepictions;
    }

    /**
     * Initialize existing depicts
     *
     * @param selectedExistingDepictions existing depicts
     */
    public void setSelectedExistingDepictions(final List<String> selectedExistingDepictions) {
        if (!ListenerUtil.mutListener.listen(6928)) {
            this.selectedExistingDepictions = selectedExistingDepictions;
        }
    }
}
