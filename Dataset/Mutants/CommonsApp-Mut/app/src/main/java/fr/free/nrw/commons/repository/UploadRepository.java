package fr.free.nrw.commons.repository;

import androidx.annotation.Nullable;
import fr.free.nrw.commons.Media;
import fr.free.nrw.commons.category.CategoriesModel;
import fr.free.nrw.commons.category.CategoryItem;
import fr.free.nrw.commons.contributions.Contribution;
import fr.free.nrw.commons.contributions.ContributionDao;
import fr.free.nrw.commons.filepicker.UploadableFile;
import fr.free.nrw.commons.location.LatLng;
import fr.free.nrw.commons.nearby.NearbyPlaces;
import fr.free.nrw.commons.nearby.Place;
import fr.free.nrw.commons.upload.ImageCoordinates;
import fr.free.nrw.commons.upload.SimilarImageInterface;
import fr.free.nrw.commons.upload.UploadController;
import fr.free.nrw.commons.upload.UploadItem;
import fr.free.nrw.commons.upload.UploadModel;
import fr.free.nrw.commons.upload.structure.depictions.DepictModel;
import fr.free.nrw.commons.upload.structure.depictions.DepictedItem;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Single;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import javax.inject.Inject;
import javax.inject.Singleton;
import timber.log.Timber;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * The repository class for UploadActivity
 */
@Singleton
public class UploadRepository {

    private final UploadModel uploadModel;

    private final UploadController uploadController;

    private final CategoriesModel categoriesModel;

    private final NearbyPlaces nearbyPlaces;

    private final DepictModel depictModel;

    // 100 meters
    private static final double NEARBY_RADIUS_IN_KILO_METERS = 0.1;

    private final ContributionDao contributionDao;

    @Inject
    public UploadRepository(UploadModel uploadModel, UploadController uploadController, CategoriesModel categoriesModel, NearbyPlaces nearbyPlaces, DepictModel depictModel, ContributionDao contributionDao) {
        this.uploadModel = uploadModel;
        this.uploadController = uploadController;
        this.categoriesModel = categoriesModel;
        this.nearbyPlaces = nearbyPlaces;
        this.depictModel = depictModel;
        this.contributionDao = contributionDao;
    }

    /**
     * asks the RemoteDataSource to build contributions
     *
     * @return
     */
    public Observable<Contribution> buildContributions() {
        return uploadModel.buildContributions();
    }

    public void prepareMedia(Contribution contribution) {
        if (!ListenerUtil.mutListener.listen(182)) {
            uploadController.prepareMedia(contribution);
        }
    }

    public void saveContribution(Contribution contribution) {
        if (!ListenerUtil.mutListener.listen(183)) {
            contributionDao.save(contribution).blockingAwait();
        }
    }

    /**
     * Fetches and returns all the Upload Items
     *
     * @return
     */
    public List<UploadItem> getUploads() {
        return uploadModel.getUploads();
    }

    /**
     *Prepare for a fresh upload
     */
    public void cleanup() {
        if (!ListenerUtil.mutListener.listen(184)) {
            uploadModel.cleanUp();
        }
        if (!ListenerUtil.mutListener.listen(185)) {
            // This needs further refactoring, this should not be here, right now the structure wont suppoort rhis
            categoriesModel.cleanUp();
        }
        if (!ListenerUtil.mutListener.listen(186)) {
            depictModel.cleanUp();
        }
    }

    /**
     * Fetches and returns the selected categories for the current upload
     *
     * @return
     */
    public List<CategoryItem> getSelectedCategories() {
        return categoriesModel.getSelectedCategories();
    }

    /**
     * all categories from MWApi
     *
     * @param query
     * @param imageTitleList
     * @param selectedDepictions
     * @return
     */
    public Observable<List<CategoryItem>> searchAll(String query, List<String> imageTitleList, List<DepictedItem> selectedDepictions) {
        return categoriesModel.searchAll(query, imageTitleList, selectedDepictions);
    }

    /**
     * sets the list of selected categories for the current upload
     *
     * @param categoryStringList
     */
    public void setSelectedCategories(List<String> categoryStringList) {
        if (!ListenerUtil.mutListener.listen(187)) {
            uploadModel.setSelectedCategories(categoryStringList);
        }
    }

    /**
     * handles the category selection/deselection
     *
     * @param categoryItem
     */
    public void onCategoryClicked(CategoryItem categoryItem, final Media media) {
        if (!ListenerUtil.mutListener.listen(188)) {
            categoriesModel.onCategoryItemClicked(categoryItem, media);
        }
    }

    /**
     * prunes the category list for irrelevant categories see #750
     *
     * @param name
     * @return
     */
    public boolean containsYear(String name) {
        return categoriesModel.containsYear(name);
    }

    /**
     * retursn the string list of available license from the LocalDataSource
     *
     * @return
     */
    public List<String> getLicenses() {
        return uploadModel.getLicenses();
    }

    /**
     * returns the selected license for the current upload
     *
     * @return
     */
    public String getSelectedLicense() {
        return uploadModel.getSelectedLicense();
    }

    /**
     * returns the number of Upload Items
     *
     * @return
     */
    public int getCount() {
        return uploadModel.getCount();
    }

    /**
     * ask the RemoteDataSource to pre process the image
     *
     * @param uploadableFile
     * @param place
     * @param similarImageInterface
     * @return
     */
    public Observable<UploadItem> preProcessImage(UploadableFile uploadableFile, Place place, SimilarImageInterface similarImageInterface, LatLng inAppPictureLocation) {
        return uploadModel.preProcessImage(uploadableFile, place, similarImageInterface, inAppPictureLocation);
    }

    /**
     * query the RemoteDataSource for image quality
     *
     * @param uploadItem
     * @return
     */
    public Single<Integer> getImageQuality(UploadItem uploadItem, LatLng location) {
        return uploadModel.getImageQuality(uploadItem, location);
    }

    /**
     * asks the LocalDataSource to delete the file with the given file path
     *
     * @param filePath
     */
    public void deletePicture(String filePath) {
        if (!ListenerUtil.mutListener.listen(189)) {
            uploadModel.deletePicture(filePath);
        }
    }

    /**
     * fetches and returns the upload item
     *
     * @param index
     * @return
     */
    public UploadItem getUploadItem(int index) {
        if (!ListenerUtil.mutListener.listen(195)) {
            if ((ListenerUtil.mutListener.listen(194) ? (index <= 0) : (ListenerUtil.mutListener.listen(193) ? (index > 0) : (ListenerUtil.mutListener.listen(192) ? (index < 0) : (ListenerUtil.mutListener.listen(191) ? (index != 0) : (ListenerUtil.mutListener.listen(190) ? (index == 0) : (index >= 0))))))) {
                return uploadModel.getItems().get(index);
            }
        }
        // There is no item to copy details
        return null;
    }

    /**
     * set selected license for the current upload
     *
     * @param licenseName
     */
    public void setSelectedLicense(String licenseName) {
        if (!ListenerUtil.mutListener.listen(196)) {
            uploadModel.setSelectedLicense(licenseName);
        }
    }

    public void onDepictItemClicked(DepictedItem depictedItem, final Media media) {
        if (!ListenerUtil.mutListener.listen(197)) {
            uploadModel.onDepictItemClicked(depictedItem, media);
        }
    }

    public List<DepictedItem> getSelectedDepictions() {
        return uploadModel.getSelectedDepictions();
    }

    /**
     * Provides selected existing depicts
     *
     * @return selected existing depicts
     */
    public List<String> getSelectedExistingDepictions() {
        return uploadModel.getSelectedExistingDepictions();
    }

    /**
     * Initialize existing depicts
     *
     * @param selectedExistingDepictions existing depicts
     */
    public void setSelectedExistingDepictions(final List<String> selectedExistingDepictions) {
        if (!ListenerUtil.mutListener.listen(198)) {
            uploadModel.setSelectedExistingDepictions(selectedExistingDepictions);
        }
    }

    public Flowable<List<DepictedItem>> searchAllEntities(String query) {
        return depictModel.searchAllEntities(query, this);
    }

    /**
     * Gets the depiction for each unique {@link Place} associated with an {@link UploadItem}
     * from {@link #getUploads()}
     *
     * @return a single that provides the depictions
     */
    public Single<List<DepictedItem>> getPlaceDepictions() {
        final Set<String> qids = new HashSet<>();
        if (!ListenerUtil.mutListener.listen(201)) {
            {
                long _loopCounter2 = 0;
                for (final UploadItem item : getUploads()) {
                    ListenerUtil.loopListener.listen("_loopCounter2", ++_loopCounter2);
                    final Place place = item.getPlace();
                    if (!ListenerUtil.mutListener.listen(200)) {
                        if (place != null) {
                            if (!ListenerUtil.mutListener.listen(199)) {
                                qids.add(place.getWikiDataEntityId());
                            }
                        }
                    }
                }
            }
        }
        return depictModel.getPlaceDepictions(new ArrayList<>(qids));
    }

    /**
     * Gets the category for each unique {@link Place} associated with an {@link UploadItem}
     * from {@link #getUploads()}
     *
     * @return a single that provides the categories
     */
    public Single<List<CategoryItem>> getPlaceCategories() {
        final Set<String> qids = new HashSet<>();
        if (!ListenerUtil.mutListener.listen(204)) {
            {
                long _loopCounter3 = 0;
                for (final UploadItem item : getUploads()) {
                    ListenerUtil.loopListener.listen("_loopCounter3", ++_loopCounter3);
                    final Place place = item.getPlace();
                    if (!ListenerUtil.mutListener.listen(203)) {
                        if (place != null) {
                            if (!ListenerUtil.mutListener.listen(202)) {
                                qids.add(place.getCategory());
                            }
                        }
                    }
                }
            }
        }
        return Single.fromObservable(categoriesModel.getCategoriesByName(new ArrayList<>(qids)));
    }

    /**
     * Takes depict IDs as a parameter, converts into a slash separated String and Gets DepictItem
     * from the server
     *
     * @param depictionsQIDs IDs of Depiction
     * @return Flowable<List<DepictedItem>>
     */
    public Flowable<List<DepictedItem>> getDepictions(final List<String> depictionsQIDs) {
        final String ids = joinQIDs(depictionsQIDs);
        return depictModel.getDepictions(ids).toFlowable();
    }

    /**
     * Builds a string by joining all IDs divided by "|"
     *
     * @param depictionsQIDs IDs of depiction ex. ["Q11023","Q1356"]
     * @return string ex. "Q11023|Q1356"
     */
    private String joinQIDs(final List<String> depictionsQIDs) {
        if (!ListenerUtil.mutListener.listen(220)) {
            if ((ListenerUtil.mutListener.listen(205) ? (depictionsQIDs != null || !depictionsQIDs.isEmpty()) : (depictionsQIDs != null && !depictionsQIDs.isEmpty()))) {
                final StringBuilder buffer = new StringBuilder(depictionsQIDs.get(0));
                if (!ListenerUtil.mutListener.listen(219)) {
                    if ((ListenerUtil.mutListener.listen(210) ? (depictionsQIDs.size() >= 1) : (ListenerUtil.mutListener.listen(209) ? (depictionsQIDs.size() <= 1) : (ListenerUtil.mutListener.listen(208) ? (depictionsQIDs.size() < 1) : (ListenerUtil.mutListener.listen(207) ? (depictionsQIDs.size() != 1) : (ListenerUtil.mutListener.listen(206) ? (depictionsQIDs.size() == 1) : (depictionsQIDs.size() > 1))))))) {
                        if (!ListenerUtil.mutListener.listen(218)) {
                            {
                                long _loopCounter4 = 0;
                                for (int i = 1; (ListenerUtil.mutListener.listen(217) ? (i >= depictionsQIDs.size()) : (ListenerUtil.mutListener.listen(216) ? (i <= depictionsQIDs.size()) : (ListenerUtil.mutListener.listen(215) ? (i > depictionsQIDs.size()) : (ListenerUtil.mutListener.listen(214) ? (i != depictionsQIDs.size()) : (ListenerUtil.mutListener.listen(213) ? (i == depictionsQIDs.size()) : (i < depictionsQIDs.size())))))); i++) {
                                    ListenerUtil.loopListener.listen("_loopCounter4", ++_loopCounter4);
                                    if (!ListenerUtil.mutListener.listen(211)) {
                                        buffer.append("|");
                                    }
                                    if (!ListenerUtil.mutListener.listen(212)) {
                                        buffer.append(depictionsQIDs.get(i));
                                    }
                                }
                            }
                        }
                    }
                }
                return buffer.toString();
            }
        }
        return null;
    }

    /**
     * Returns nearest place matching the passed latitude and longitude
     * @param decLatitude
     * @param decLongitude
     * @return
     */
    @Nullable
    public Place checkNearbyPlaces(final double decLatitude, final double decLongitude) {
        try {
            final List<Place> fromWikidataQuery = nearbyPlaces.getFromWikidataQuery(new LatLng(decLatitude, decLongitude, 0.0f), Locale.getDefault().getLanguage(), NEARBY_RADIUS_IN_KILO_METERS, false, null);
            return ((ListenerUtil.mutListener.listen(227) ? (fromWikidataQuery != null || (ListenerUtil.mutListener.listen(226) ? (fromWikidataQuery.size() >= 0) : (ListenerUtil.mutListener.listen(225) ? (fromWikidataQuery.size() <= 0) : (ListenerUtil.mutListener.listen(224) ? (fromWikidataQuery.size() < 0) : (ListenerUtil.mutListener.listen(223) ? (fromWikidataQuery.size() != 0) : (ListenerUtil.mutListener.listen(222) ? (fromWikidataQuery.size() == 0) : (fromWikidataQuery.size() > 0))))))) : (fromWikidataQuery != null && (ListenerUtil.mutListener.listen(226) ? (fromWikidataQuery.size() >= 0) : (ListenerUtil.mutListener.listen(225) ? (fromWikidataQuery.size() <= 0) : (ListenerUtil.mutListener.listen(224) ? (fromWikidataQuery.size() < 0) : (ListenerUtil.mutListener.listen(223) ? (fromWikidataQuery.size() != 0) : (ListenerUtil.mutListener.listen(222) ? (fromWikidataQuery.size() == 0) : (fromWikidataQuery.size() > 0))))))))) ? fromWikidataQuery.get(0) : null;
        } catch (final Exception e) {
            if (!ListenerUtil.mutListener.listen(221)) {
                Timber.e("Error fetching nearby places: %s", e.getMessage());
            }
            return null;
        }
    }

    public void useSimilarPictureCoordinates(ImageCoordinates imageCoordinates, int uploadItemIndex) {
        if (!ListenerUtil.mutListener.listen(228)) {
            uploadModel.useSimilarPictureCoordinates(imageCoordinates, uploadItemIndex);
        }
    }

    public boolean isWMLSupportedForThisPlace() {
        return uploadModel.getItems().get(0).isWLMUpload();
    }

    /**
     * Provides selected existing categories
     *
     * @return selected existing categories
     */
    public List<String> getSelectedExistingCategories() {
        return categoriesModel.getSelectedExistingCategories();
    }

    /**
     * Initialize existing categories
     *
     * @param selectedExistingCategories existing categories
     */
    public void setSelectedExistingCategories(final List<String> selectedExistingCategories) {
        if (!ListenerUtil.mutListener.listen(229)) {
            categoriesModel.setSelectedExistingCategories(selectedExistingCategories);
        }
    }

    /**
     * Takes category names and Gets CategoryItem from the server
     *
     * @param categories names of Category
     * @return Observable<List<CategoryItem>>
     */
    public Observable<List<CategoryItem>> getCategories(final List<String> categories) {
        return categoriesModel.getCategoriesByName(categories);
    }
}
