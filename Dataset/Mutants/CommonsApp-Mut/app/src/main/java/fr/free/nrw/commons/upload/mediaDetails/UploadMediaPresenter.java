package fr.free.nrw.commons.upload.mediaDetails;

import static fr.free.nrw.commons.di.CommonsApplicationModule.IO_THREAD;
import static fr.free.nrw.commons.di.CommonsApplicationModule.MAIN_THREAD;
import static fr.free.nrw.commons.utils.ImageUtils.EMPTY_CAPTION;
import static fr.free.nrw.commons.utils.ImageUtils.FILE_NAME_EXISTS;
import static fr.free.nrw.commons.utils.ImageUtils.IMAGE_KEEP;
import static fr.free.nrw.commons.utils.ImageUtils.IMAGE_OK;
import androidx.annotation.Nullable;
import fr.free.nrw.commons.R;
import fr.free.nrw.commons.filepicker.UploadableFile;
import fr.free.nrw.commons.kvstore.JsonKvStore;
import fr.free.nrw.commons.location.LatLng;
import fr.free.nrw.commons.nearby.Place;
import fr.free.nrw.commons.repository.UploadRepository;
import fr.free.nrw.commons.upload.ImageCoordinates;
import fr.free.nrw.commons.upload.SimilarImageInterface;
import fr.free.nrw.commons.upload.UploadItem;
import fr.free.nrw.commons.upload.UploadMediaDetail;
import fr.free.nrw.commons.upload.mediaDetails.UploadMediaDetailsContract.UserActionListener;
import fr.free.nrw.commons.upload.mediaDetails.UploadMediaDetailsContract.View;
import io.github.coordinates2country.Coordinates2Country;
import io.reactivex.Maybe;
import io.reactivex.Scheduler;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import java.lang.reflect.Proxy;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.inject.Inject;
import javax.inject.Named;
import org.jetbrains.annotations.NotNull;
import timber.log.Timber;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class UploadMediaPresenter implements UserActionListener, SimilarImageInterface {

    private static final UploadMediaDetailsContract.View DUMMY = (UploadMediaDetailsContract.View) Proxy.newProxyInstance(UploadMediaDetailsContract.View.class.getClassLoader(), new Class[] { UploadMediaDetailsContract.View.class }, (proxy, method, methodArgs) -> null);

    private final UploadRepository repository;

    private UploadMediaDetailsContract.View view = DUMMY;

    private CompositeDisposable compositeDisposable;

    private final JsonKvStore defaultKVStore;

    private Scheduler ioScheduler;

    private Scheduler mainThreadScheduler;

    private final List<String> WLM_SUPPORTED_COUNTRIES = Arrays.asList("am", "at", "az", "br", "hr", "sv", "fi", "fr", "de", "gh", "in", "ie", "il", "mk", "my", "mt", "pk", "pe", "pl", "ru", "rw", "si", "es", "se", "tw", "ug", "ua", "us");

    private Map<String, String> countryNamesAndCodes = null;

    @Inject
    public UploadMediaPresenter(UploadRepository uploadRepository, @Named("default_preferences") JsonKvStore defaultKVStore, @Named(IO_THREAD) Scheduler ioScheduler, @Named(MAIN_THREAD) Scheduler mainThreadScheduler) {
        this.repository = uploadRepository;
        this.defaultKVStore = defaultKVStore;
        if (!ListenerUtil.mutListener.listen(6625)) {
            this.ioScheduler = ioScheduler;
        }
        if (!ListenerUtil.mutListener.listen(6626)) {
            this.mainThreadScheduler = mainThreadScheduler;
        }
        if (!ListenerUtil.mutListener.listen(6627)) {
            compositeDisposable = new CompositeDisposable();
        }
    }

    @Override
    public void onAttachView(View view) {
        if (!ListenerUtil.mutListener.listen(6628)) {
            this.view = view;
        }
    }

    @Override
    public void onDetachView() {
        if (!ListenerUtil.mutListener.listen(6629)) {
            this.view = DUMMY;
        }
        if (!ListenerUtil.mutListener.listen(6630)) {
            compositeDisposable.clear();
        }
    }

    /**
     * Sets the Upload Media Details for the corresponding upload item
     *
     * @param uploadMediaDetails
     * @param uploadItemIndex
     */
    @Override
    public void setUploadMediaDetails(List<UploadMediaDetail> uploadMediaDetails, int uploadItemIndex) {
        if (!ListenerUtil.mutListener.listen(6631)) {
            repository.getUploads().get(uploadItemIndex).setMediaDetails(uploadMediaDetails);
        }
    }

    /**
     * Receives the corresponding uploadable file, processes it and return the view with and uplaod item
     *  @param uploadableFile
     * @param place
     */
    @Override
    public void receiveImage(final UploadableFile uploadableFile, final Place place, LatLng inAppPictureLocation) {
        if (!ListenerUtil.mutListener.listen(6632)) {
            view.showProgress(true);
        }
        if (!ListenerUtil.mutListener.listen(6633)) {
            compositeDisposable.add(repository.preProcessImage(uploadableFile, place, this, inAppPictureLocation).map(uploadItem -> {
                if (place != null && place.isMonument()) {
                    if (place.location != null) {
                        final String countryCode = reverseGeoCode(place.location);
                        if (countryCode != null && WLM_SUPPORTED_COUNTRIES.contains(countryCode.toLowerCase())) {
                            uploadItem.setWLMUpload(true);
                            uploadItem.setCountryCode(countryCode.toLowerCase());
                        }
                    }
                }
                return uploadItem;
            }).subscribeOn(ioScheduler).observeOn(mainThreadScheduler).subscribe(uploadItem -> {
                view.onImageProcessed(uploadItem, place);
                view.updateMediaDetails(uploadItem.getUploadMediaDetails());
                final ImageCoordinates gpsCoords = uploadItem.getGpsCoords();
                final boolean hasImageCoordinates = gpsCoords != null && gpsCoords.getImageCoordsExists();
                view.showProgress(false);
                if (hasImageCoordinates && place == null) {
                    checkNearbyPlaces(uploadItem);
                }
            }, throwable -> Timber.e(throwable, "Error occurred in processing images")));
        }
    }

    @Nullable
    private String reverseGeoCode(final LatLng latLng) {
        if (!ListenerUtil.mutListener.listen(6635)) {
            if (countryNamesAndCodes == null) {
                if (!ListenerUtil.mutListener.listen(6634)) {
                    countryNamesAndCodes = getCountryNamesAndCodes();
                }
            }
        }
        return countryNamesAndCodes.get(Coordinates2Country.country(latLng.getLatitude(), latLng.getLongitude()));
    }

    /**
     * Creates HashMap containing all ISO countries 2-letter codes provided by <code>Locale.getISOCountries()</code>
     * and their english names
     *
     * @return HashMap where Key is country english name and Value is 2-letter country code
     * e.g. ["Germany":"DE", ...]
     */
    private Map<String, String> getCountryNamesAndCodes() {
        final Map<String, String> result = new HashMap<>();
        final String[] isoCountries = Locale.getISOCountries();
        if (!ListenerUtil.mutListener.listen(6637)) {
            {
                long _loopCounter102 = 0;
                for (final String isoCountry : isoCountries) {
                    ListenerUtil.loopListener.listen("_loopCounter102", ++_loopCounter102);
                    if (!ListenerUtil.mutListener.listen(6636)) {
                        result.put(new Locale("en", isoCountry).getDisplayCountry(Locale.ENGLISH), isoCountry);
                    }
                }
            }
        }
        return result;
    }

    /**
     * This method checks for the nearest location that needs images and suggests it to the user.
     * @param uploadItem
     */
    private void checkNearbyPlaces(final UploadItem uploadItem) {
        final Disposable checkNearbyPlaces = Maybe.fromCallable(() -> repository.checkNearbyPlaces(uploadItem.getGpsCoords().getDecLatitude(), uploadItem.getGpsCoords().getDecLongitude())).subscribeOn(ioScheduler).observeOn(mainThreadScheduler).subscribe(place -> {
            if (place != null) {
                view.onNearbyPlaceFound(uploadItem, place);
            }
        }, throwable -> Timber.e(throwable, "Error occurred in processing images"));
        if (!ListenerUtil.mutListener.listen(6638)) {
            compositeDisposable.add(checkNearbyPlaces);
        }
    }

    /**
     * asks the repository to verify image quality
     *
     * @param uploadItemIndex
     */
    @Override
    public boolean verifyImageQuality(int uploadItemIndex, LatLng inAppPictureLocation) {
        final List<UploadItem> uploadItems = repository.getUploads();
        if (!ListenerUtil.mutListener.listen(6641)) {
            if (uploadItems.size() == 0) {
                if (!ListenerUtil.mutListener.listen(6639)) {
                    view.showProgress(false);
                }
                if (!ListenerUtil.mutListener.listen(6640)) {
                    // No internationalization required for this error message because it's an internal error.
                    view.showMessage("Internal error: Zero upload items received by the Upload Media Detail Fragment. Sorry, please upload again.", R.color.color_error);
                }
                return false;
            }
        }
        UploadItem uploadItem = uploadItems.get(uploadItemIndex);
        if (!ListenerUtil.mutListener.listen(6646)) {
            if ((ListenerUtil.mutListener.listen(6642) ? (uploadItem.getGpsCoords().getDecimalCoords() == null || inAppPictureLocation == null) : (uploadItem.getGpsCoords().getDecimalCoords() == null && inAppPictureLocation == null))) {
                final Runnable onSkipClicked = () -> {
                    view.showProgress(true);
                    compositeDisposable.add(repository.getImageQuality(uploadItem, inAppPictureLocation).observeOn(mainThreadScheduler).subscribe(imageResult -> {
                        view.showProgress(false);
                        handleImageResult(imageResult, uploadItem);
                    }, throwable -> {
                        view.showProgress(false);
                        if (throwable instanceof UnknownHostException) {
                            view.showConnectionErrorPopup();
                        } else {
                            view.showMessage("" + throwable.getLocalizedMessage(), R.color.color_error);
                        }
                        Timber.e(throwable, "Error occurred while handling image");
                    }));
                };
                if (!ListenerUtil.mutListener.listen(6645)) {
                    view.displayAddLocationDialog(onSkipClicked);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(6643)) {
                    view.showProgress(true);
                }
                if (!ListenerUtil.mutListener.listen(6644)) {
                    compositeDisposable.add(repository.getImageQuality(uploadItem, inAppPictureLocation).observeOn(mainThreadScheduler).subscribe(imageResult -> {
                        view.showProgress(false);
                        handleImageResult(imageResult, uploadItem);
                    }, throwable -> {
                        view.showProgress(false);
                        if (throwable instanceof UnknownHostException) {
                            view.showConnectionErrorPopup();
                        } else {
                            view.showMessage("" + throwable.getLocalizedMessage(), R.color.color_error);
                        }
                        Timber.e(throwable, "Error occurred while handling image");
                    }));
                }
            }
        }
        return true;
    }

    /**
     * Copies the caption and description of the current item to the subsequent media
     *
     * @param indexInViewFlipper
     */
    @Override
    public void copyTitleAndDescriptionToSubsequentMedia(int indexInViewFlipper) {
        if (!ListenerUtil.mutListener.listen(6657)) {
            {
                long _loopCounter103 = 0;
                for (int i = (ListenerUtil.mutListener.listen(6656) ? (indexInViewFlipper % 1) : (ListenerUtil.mutListener.listen(6655) ? (indexInViewFlipper / 1) : (ListenerUtil.mutListener.listen(6654) ? (indexInViewFlipper * 1) : (ListenerUtil.mutListener.listen(6653) ? (indexInViewFlipper - 1) : (indexInViewFlipper + 1))))); (ListenerUtil.mutListener.listen(6652) ? (i >= repository.getCount()) : (ListenerUtil.mutListener.listen(6651) ? (i <= repository.getCount()) : (ListenerUtil.mutListener.listen(6650) ? (i > repository.getCount()) : (ListenerUtil.mutListener.listen(6649) ? (i != repository.getCount()) : (ListenerUtil.mutListener.listen(6648) ? (i == repository.getCount()) : (i < repository.getCount())))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter103", ++_loopCounter103);
                    final UploadItem subsequentUploadItem = repository.getUploads().get(i);
                    if (!ListenerUtil.mutListener.listen(6647)) {
                        subsequentUploadItem.setMediaDetails(deepCopy(repository.getUploads().get(indexInViewFlipper).getUploadMediaDetails()));
                    }
                }
            }
        }
    }

    /**
     * Fetches and set the caption and description of the item
     *
     * @param indexInViewFlipper
     */
    @Override
    public void fetchTitleAndDescription(int indexInViewFlipper) {
        final UploadItem currentUploadItem = repository.getUploads().get(indexInViewFlipper);
        if (!ListenerUtil.mutListener.listen(6658)) {
            view.updateMediaDetails(currentUploadItem.getUploadMediaDetails());
        }
    }

    @NotNull
    private List<UploadMediaDetail> deepCopy(List<UploadMediaDetail> uploadMediaDetails) {
        final ArrayList<UploadMediaDetail> newList = new ArrayList<>();
        if (!ListenerUtil.mutListener.listen(6660)) {
            {
                long _loopCounter104 = 0;
                for (UploadMediaDetail uploadMediaDetail : uploadMediaDetails) {
                    ListenerUtil.loopListener.listen("_loopCounter104", ++_loopCounter104);
                    if (!ListenerUtil.mutListener.listen(6659)) {
                        newList.add(uploadMediaDetail.javaCopy());
                    }
                }
            }
        }
        return newList;
    }

    @Override
    public void useSimilarPictureCoordinates(ImageCoordinates imageCoordinates, int uploadItemIndex) {
        if (!ListenerUtil.mutListener.listen(6661)) {
            repository.useSimilarPictureCoordinates(imageCoordinates, uploadItemIndex);
        }
    }

    @Override
    public void onMapIconClicked(int indexInViewFlipper) {
        if (!ListenerUtil.mutListener.listen(6662)) {
            view.showExternalMap(repository.getUploads().get(indexInViewFlipper));
        }
    }

    @Override
    public void onEditButtonClicked(int indexInViewFlipper) {
        if (!ListenerUtil.mutListener.listen(6663)) {
            view.showEditActivity(repository.getUploads().get(indexInViewFlipper));
        }
    }

    @Override
    public void onUserConfirmedUploadIsOfPlace(Place place, int uploadItemPosition) {
        final List<UploadMediaDetail> uploadMediaDetails = repository.getUploads().get(uploadItemPosition).getUploadMediaDetails();
        UploadItem uploadItem = repository.getUploads().get(uploadItemPosition);
        if (!ListenerUtil.mutListener.listen(6664)) {
            uploadItem.setPlace(place);
        }
        if (!ListenerUtil.mutListener.listen(6665)) {
            uploadMediaDetails.set(0, new UploadMediaDetail(place));
        }
        if (!ListenerUtil.mutListener.listen(6666)) {
            view.updateMediaDetails(uploadMediaDetails);
        }
    }

    /**
     * handles image quality verifications
     *
     * @param imageResult
     * @param uploadItem
     */
    public void handleImageResult(Integer imageResult, UploadItem uploadItem) {
        if (!ListenerUtil.mutListener.listen(6671)) {
            if ((ListenerUtil.mutListener.listen(6667) ? (imageResult == IMAGE_KEEP && imageResult == IMAGE_OK) : (imageResult == IMAGE_KEEP || imageResult == IMAGE_OK))) {
                if (!ListenerUtil.mutListener.listen(6669)) {
                    view.onImageValidationSuccess();
                }
                if (!ListenerUtil.mutListener.listen(6670)) {
                    uploadItem.setHasInvalidLocation(false);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(6668)) {
                    handleBadImage(imageResult, uploadItem);
                }
            }
        }
    }

    /**
     * Handle  images, say empty caption, duplicate file name, bad picture(in all other cases)
     *
     * @param errorCode
     * @param uploadItem
     */
    public void handleBadImage(Integer errorCode, UploadItem uploadItem) {
        if (!ListenerUtil.mutListener.listen(6672)) {
            Timber.d("Handle bad picture with error code %d", errorCode);
        }
        if (!ListenerUtil.mutListener.listen(6679)) {
            if ((ListenerUtil.mutListener.listen(6677) ? (errorCode <= 8) : (ListenerUtil.mutListener.listen(6676) ? (errorCode > 8) : (ListenerUtil.mutListener.listen(6675) ? (errorCode < 8) : (ListenerUtil.mutListener.listen(6674) ? (errorCode != 8) : (ListenerUtil.mutListener.listen(6673) ? (errorCode == 8) : (errorCode >= 8))))))) {
                if (!ListenerUtil.mutListener.listen(6678)) {
                    // If location of image and nearby does not match
                    uploadItem.setHasInvalidLocation(true);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(6682)) {
            // If errorCode is empty caption show message
            if (errorCode == EMPTY_CAPTION) {
                if (!ListenerUtil.mutListener.listen(6680)) {
                    Timber.d("Captions are empty. Showing toast");
                }
                if (!ListenerUtil.mutListener.listen(6681)) {
                    view.showMessage(R.string.add_caption_toast, R.color.color_error);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(6690)) {
            // If image with same file name exists check the bit in errorCode is set or not
            if ((ListenerUtil.mutListener.listen(6687) ? ((errorCode & FILE_NAME_EXISTS) >= 0) : (ListenerUtil.mutListener.listen(6686) ? ((errorCode & FILE_NAME_EXISTS) <= 0) : (ListenerUtil.mutListener.listen(6685) ? ((errorCode & FILE_NAME_EXISTS) > 0) : (ListenerUtil.mutListener.listen(6684) ? ((errorCode & FILE_NAME_EXISTS) < 0) : (ListenerUtil.mutListener.listen(6683) ? ((errorCode & FILE_NAME_EXISTS) == 0) : ((errorCode & FILE_NAME_EXISTS) != 0))))))) {
                if (!ListenerUtil.mutListener.listen(6688)) {
                    Timber.d("Trying to show duplicate picture popup");
                }
                if (!ListenerUtil.mutListener.listen(6689)) {
                    view.showDuplicatePicturePopup(uploadItem);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(6693)) {
            // If image has some other problems, show popup accordingly
            if ((ListenerUtil.mutListener.listen(6691) ? (errorCode != EMPTY_CAPTION || errorCode != FILE_NAME_EXISTS) : (errorCode != EMPTY_CAPTION && errorCode != FILE_NAME_EXISTS))) {
                if (!ListenerUtil.mutListener.listen(6692)) {
                    view.showBadImagePopup(errorCode, uploadItem);
                }
            }
        }
    }

    /**
     * notifies the user that a similar image exists
     * @param originalFilePath
     * @param possibleFilePath
     * @param similarImageCoordinates
     */
    @Override
    public void showSimilarImageFragment(String originalFilePath, String possibleFilePath, ImageCoordinates similarImageCoordinates) {
        if (!ListenerUtil.mutListener.listen(6694)) {
            view.showSimilarImageFragment(originalFilePath, possibleFilePath, similarImageCoordinates);
        }
    }
}
