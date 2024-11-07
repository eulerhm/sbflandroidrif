package fr.free.nrw.commons.upload.mediaDetails;

import static android.app.Activity.RESULT_OK;
import static fr.free.nrw.commons.utils.ActivityUtils.startActivityWithFlags;
import static fr.free.nrw.commons.utils.ImageUtils.FILE_NAME_EXISTS;
import static fr.free.nrw.commons.utils.ImageUtils.getErrorMessageForResult;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.graphics.drawable.Drawable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.github.chrisbanes.photoview.PhotoView;
import com.mapbox.mapboxsdk.camera.CameraPosition;
import fr.free.nrw.commons.LocationPicker.LocationPicker;
import fr.free.nrw.commons.R;
import fr.free.nrw.commons.edit.EditActivity;
import fr.free.nrw.commons.contributions.MainActivity;
import fr.free.nrw.commons.filepicker.UploadableFile;
import fr.free.nrw.commons.kvstore.JsonKvStore;
import fr.free.nrw.commons.location.LatLng;
import fr.free.nrw.commons.nearby.Place;
import fr.free.nrw.commons.recentlanguages.RecentLanguagesDao;
import fr.free.nrw.commons.settings.Prefs;
import fr.free.nrw.commons.upload.ImageCoordinates;
import fr.free.nrw.commons.upload.SimilarImageDialogFragment;
import fr.free.nrw.commons.upload.UploadActivity;
import fr.free.nrw.commons.upload.UploadBaseFragment;
import fr.free.nrw.commons.upload.UploadItem;
import fr.free.nrw.commons.upload.UploadMediaDetail;
import fr.free.nrw.commons.upload.UploadMediaDetailAdapter;
import fr.free.nrw.commons.utils.DialogUtil;
import fr.free.nrw.commons.utils.ImageUtils;
import fr.free.nrw.commons.utils.ViewUtil;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import javax.inject.Inject;
import javax.inject.Named;
import org.apache.commons.lang3.StringUtils;
import timber.log.Timber;
import android.os.Parcelable;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class UploadMediaDetailFragment extends UploadBaseFragment implements UploadMediaDetailsContract.View, UploadMediaDetailAdapter.EventListener {

    private static final int REQUEST_CODE = 1211;

    private static final int REQUEST_CODE_FOR_EDIT_ACTIVITY = 1212;

    private static final int REQUEST_CODE_FOR_VOICE_INPUT = 1213;

    /**
     * A key for applicationKvStore. By this key we can retrieve the location of last UploadItem ex.
     * 12.3433,54.78897 from applicationKvStore.
     */
    public static final String LAST_LOCATION = "last_location_while_uploading";

    public static final String LAST_ZOOM = "last_zoom_level_while_uploading";

    public static final String UPLOADABLE_FILE = "uploadable_file";

    public static final String UPLOAD_MEDIA_DETAILS = "upload_media_detail_adapter";

    @BindView(R.id.tv_title)
    TextView tvTitle;

    @BindView(R.id.location_image_view)
    ImageView locationImageView;

    @BindView(R.id.location_text_view)
    TextView locationTextView;

    @BindView(R.id.ll_location_status)
    LinearLayout llLocationStatus;

    @BindView(R.id.ib_expand_collapse)
    AppCompatImageButton ibExpandCollapse;

    @BindView(R.id.ll_container_media_detail)
    LinearLayout llContainerMediaDetail;

    @BindView(R.id.rv_descriptions)
    RecyclerView rvDescriptions;

    @BindView(R.id.backgroundImage)
    PhotoView photoViewBackgroundImage;

    @BindView(R.id.btn_next)
    AppCompatButton btnNext;

    @BindView(R.id.btn_previous)
    AppCompatButton btnPrevious;

    @BindView(R.id.ll_edit_image)
    LinearLayout llEditImage;

    @BindView(R.id.tooltip)
    ImageView tooltip;

    private UploadMediaDetailAdapter uploadMediaDetailAdapter;

    @BindView(R.id.btn_copy_subsequent_media)
    AppCompatButton btnCopyToSubsequentMedia;

    @Inject
    UploadMediaDetailsContract.UserActionListener presenter;

    @Inject
    @Named("default_preferences")
    JsonKvStore defaultKvStore;

    @Inject
    RecentLanguagesDao recentLanguagesDao;

    private UploadableFile uploadableFile;

    private Place place;

    private boolean isExpanded = true;

    /**
     * True if location is added via the "missing location" popup dialog (which appears after
     * tapping "Next" if the picture has no geographical coordinates).
     */
    private boolean isMissingLocationDialog;

    /**
     * showNearbyFound will be true, if any nearby location found that needs pictures and the nearby
     * popup is yet to be shown Used to show and check if the nearby found popup is already shown
     */
    private boolean showNearbyFound;

    /**
     * nearbyPlace holds the detail of nearby place that need pictures, if any found
     */
    private Place nearbyPlace;

    private UploadItem uploadItem;

    /**
     * inAppPictureLocation: use location recorded while using the in-app camera if device camera
     * does not record it in the EXIF
     */
    private LatLng inAppPictureLocation;

    /**
     * editableUploadItem : Storing the upload item before going to update the coordinates
     */
    private UploadItem editableUploadItem;

    private UploadMediaDetailFragmentCallback callback;

    public void setCallback(UploadMediaDetailFragmentCallback callback) {
        if (!ListenerUtil.mutListener.listen(6431)) {
            this.callback = callback;
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(6432)) {
            super.onCreate(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(6435)) {
            if ((ListenerUtil.mutListener.listen(6433) ? (savedInstanceState != null || uploadableFile == null) : (savedInstanceState != null && uploadableFile == null))) {
                if (!ListenerUtil.mutListener.listen(6434)) {
                    uploadableFile = savedInstanceState.getParcelable(UPLOADABLE_FILE);
                }
            }
        }
    }

    public void setImageTobeUploaded(UploadableFile uploadableFile, Place place, LatLng inAppPictureLocation) {
        if (!ListenerUtil.mutListener.listen(6436)) {
            this.uploadableFile = uploadableFile;
        }
        if (!ListenerUtil.mutListener.listen(6437)) {
            this.place = place;
        }
        if (!ListenerUtil.mutListener.listen(6438)) {
            this.inAppPictureLocation = inAppPictureLocation;
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_upload_media_detail_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(6439)) {
            super.onViewCreated(view, savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(6440)) {
            ButterKnife.bind(this, view);
        }
        if (!ListenerUtil.mutListener.listen(6442)) {
            if (callback != null) {
                if (!ListenerUtil.mutListener.listen(6441)) {
                    init();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(6446)) {
            if (savedInstanceState != null) {
                if (!ListenerUtil.mutListener.listen(6445)) {
                    if (uploadMediaDetailAdapter.getItems().size() == 0) {
                        if (!ListenerUtil.mutListener.listen(6443)) {
                            uploadMediaDetailAdapter.setItems(savedInstanceState.getParcelableArrayList(UPLOAD_MEDIA_DETAILS));
                        }
                        if (!ListenerUtil.mutListener.listen(6444)) {
                            presenter.setUploadMediaDetails(uploadMediaDetailAdapter.getItems(), callback.getIndexInViewFlipper(this));
                        }
                    }
                }
            }
        }
    }

    private void init() {
        if (!ListenerUtil.mutListener.listen(6447)) {
            tvTitle.setText(getString(R.string.step_count, callback.getIndexInViewFlipper(this) + 1, callback.getTotalNumberOfSteps(), getString(R.string.media_detail_step_title)));
        }
        if (!ListenerUtil.mutListener.listen(6448)) {
            tooltip.setOnClickListener(v -> showInfoAlert(R.string.media_detail_step_title, R.string.media_details_tooltip));
        }
        if (!ListenerUtil.mutListener.listen(6449)) {
            initPresenter();
        }
        if (!ListenerUtil.mutListener.listen(6450)) {
            presenter.receiveImage(uploadableFile, place, inAppPictureLocation);
        }
        if (!ListenerUtil.mutListener.listen(6451)) {
            initRecyclerView();
        }
        if (!ListenerUtil.mutListener.listen(6456)) {
            if (callback.getIndexInViewFlipper(this) == 0) {
                if (!ListenerUtil.mutListener.listen(6454)) {
                    btnPrevious.setEnabled(false);
                }
                if (!ListenerUtil.mutListener.listen(6455)) {
                    btnPrevious.setAlpha(0.5f);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(6452)) {
                    btnPrevious.setEnabled(true);
                }
                if (!ListenerUtil.mutListener.listen(6453)) {
                    btnPrevious.setAlpha(1.0f);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(6463)) {
            // If the image EXIF data contains the location, show the map icon with a green tick
            if ((ListenerUtil.mutListener.listen(6458) ? (inAppPictureLocation != null && ((ListenerUtil.mutListener.listen(6457) ? (uploadableFile != null || uploadableFile.hasLocation()) : (uploadableFile != null && uploadableFile.hasLocation())))) : (inAppPictureLocation != null || ((ListenerUtil.mutListener.listen(6457) ? (uploadableFile != null || uploadableFile.hasLocation()) : (uploadableFile != null && uploadableFile.hasLocation())))))) {
                Drawable mapTick = getResources().getDrawable(R.drawable.ic_map_available_20dp);
                if (!ListenerUtil.mutListener.listen(6461)) {
                    locationImageView.setImageDrawable(mapTick);
                }
                if (!ListenerUtil.mutListener.listen(6462)) {
                    locationTextView.setText(R.string.edit_location);
                }
            } else {
                // Otherwise, show the map icon with a red question mark
                Drawable mapQuestionMark = getResources().getDrawable(R.drawable.ic_map_not_available_20dp);
                if (!ListenerUtil.mutListener.listen(6459)) {
                    locationImageView.setImageDrawable(mapQuestionMark);
                }
                if (!ListenerUtil.mutListener.listen(6460)) {
                    locationTextView.setText(R.string.add_location);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(6470)) {
            // If this is the last media, we have nothing to copy, lets not show the button
            if (callback.getIndexInViewFlipper(this) == (ListenerUtil.mutListener.listen(6467) ? (callback.getTotalNumberOfSteps() % 4) : (ListenerUtil.mutListener.listen(6466) ? (callback.getTotalNumberOfSteps() / 4) : (ListenerUtil.mutListener.listen(6465) ? (callback.getTotalNumberOfSteps() * 4) : (ListenerUtil.mutListener.listen(6464) ? (callback.getTotalNumberOfSteps() + 4) : (callback.getTotalNumberOfSteps() - 4)))))) {
                if (!ListenerUtil.mutListener.listen(6469)) {
                    btnCopyToSubsequentMedia.setVisibility(View.GONE);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(6468)) {
                    btnCopyToSubsequentMedia.setVisibility(View.VISIBLE);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(6471)) {
            attachImageViewScaleChangeListener();
        }
    }

    /**
     * Attaches the scale change listener to the image view
     */
    private void attachImageViewScaleChangeListener() {
        if (!ListenerUtil.mutListener.listen(6472)) {
            photoViewBackgroundImage.setOnScaleChangeListener((scaleFactor, focusX, focusY) -> {
                // Whenever the uses plays with the image, lets collapse the media detail container
                expandCollapseLlMediaDetail(false);
            });
        }
    }

    /**
     * attach the presenter with the view
     */
    private void initPresenter() {
        if (!ListenerUtil.mutListener.listen(6473)) {
            presenter.onAttachView(this);
        }
    }

    /**
     * init the description recycler veiw and caption recyclerview
     */
    private void initRecyclerView() {
        if (!ListenerUtil.mutListener.listen(6474)) {
            uploadMediaDetailAdapter = new UploadMediaDetailAdapter(this, defaultKvStore.getString(Prefs.DESCRIPTION_LANGUAGE, ""), recentLanguagesDao);
        }
        if (!ListenerUtil.mutListener.listen(6475)) {
            uploadMediaDetailAdapter.setCallback(this::showInfoAlert);
        }
        if (!ListenerUtil.mutListener.listen(6476)) {
            uploadMediaDetailAdapter.setEventListener(this);
        }
        if (!ListenerUtil.mutListener.listen(6477)) {
            rvDescriptions.setLayoutManager(new LinearLayoutManager(getContext()));
        }
        if (!ListenerUtil.mutListener.listen(6478)) {
            rvDescriptions.setAdapter(uploadMediaDetailAdapter);
        }
    }

    /**
     * show dialog with info
     * @param titleStringID
     * @param messageStringId
     */
    private void showInfoAlert(int titleStringID, int messageStringId) {
        if (!ListenerUtil.mutListener.listen(6479)) {
            DialogUtil.showAlertDialog(getActivity(), getString(titleStringID), getString(messageStringId), getString(android.R.string.ok), null, true);
        }
    }

    @OnClick(R.id.btn_next)
    public void onNextButtonClicked() {
        boolean isValidUploads = presenter.verifyImageQuality(callback.getIndexInViewFlipper(this), inAppPictureLocation);
        if (!ListenerUtil.mutListener.listen(6481)) {
            if (!isValidUploads) {
                if (!ListenerUtil.mutListener.listen(6480)) {
                    startActivityWithFlags(getActivity(), MainActivity.class, Intent.FLAG_ACTIVITY_CLEAR_TOP, Intent.FLAG_ACTIVITY_SINGLE_TOP);
                }
            }
        }
    }

    @OnClick(R.id.btn_previous)
    public void onPreviousButtonClicked() {
        if (!ListenerUtil.mutListener.listen(6482)) {
            callback.onPreviousButtonClicked(callback.getIndexInViewFlipper(this));
        }
    }

    @OnClick(R.id.ll_edit_image)
    public void onEditButtonClicked() {
        if (!ListenerUtil.mutListener.listen(6483)) {
            presenter.onEditButtonClicked(callback.getIndexInViewFlipper(this));
        }
    }

    @Override
    public void showSimilarImageFragment(String originalFilePath, String possibleFilePath, ImageCoordinates similarImageCoordinates) {
        SimilarImageDialogFragment newFragment = new SimilarImageDialogFragment();
        if (!ListenerUtil.mutListener.listen(6489)) {
            newFragment.setCallback(new SimilarImageDialogFragment.Callback() {

                @Override
                public void onPositiveResponse() {
                    if (!ListenerUtil.mutListener.listen(6484)) {
                        Timber.d("positive response from similar image fragment");
                    }
                    if (!ListenerUtil.mutListener.listen(6485)) {
                        presenter.useSimilarPictureCoordinates(similarImageCoordinates, callback.getIndexInViewFlipper(UploadMediaDetailFragment.this));
                    }
                    if (!ListenerUtil.mutListener.listen(6486)) {
                        // fixing: https://github.com/commons-app/apps-android-commons/issues/4700
                        uploadMediaDetailAdapter.getItems().get(0).setDescriptionText(getString(R.string.similar_coordinate_description_auto_set));
                    }
                    if (!ListenerUtil.mutListener.listen(6487)) {
                        updateMediaDetails(uploadMediaDetailAdapter.getItems());
                    }
                }

                @Override
                public void onNegativeResponse() {
                    if (!ListenerUtil.mutListener.listen(6488)) {
                        Timber.d("negative response from similar image fragment");
                    }
                }
            });
        }
        Bundle args = new Bundle();
        if (!ListenerUtil.mutListener.listen(6490)) {
            args.putString("originalImagePath", originalFilePath);
        }
        if (!ListenerUtil.mutListener.listen(6491)) {
            args.putString("possibleImagePath", possibleFilePath);
        }
        if (!ListenerUtil.mutListener.listen(6492)) {
            newFragment.setArguments(args);
        }
        if (!ListenerUtil.mutListener.listen(6493)) {
            newFragment.show(getChildFragmentManager(), "dialog");
        }
    }

    @Override
    public void onImageProcessed(UploadItem uploadItem, Place place) {
        if (!ListenerUtil.mutListener.listen(6494)) {
            photoViewBackgroundImage.setImageURI(uploadItem.getMediaUri());
        }
    }

    /**
     * Sets variables to Show popup if any nearby location needing pictures matches uploadable picture's GPS location
     * @param uploadItem
     * @param place
     */
    @Override
    public void onNearbyPlaceFound(UploadItem uploadItem, Place place) {
        if (!ListenerUtil.mutListener.listen(6495)) {
            nearbyPlace = place;
        }
        if (!ListenerUtil.mutListener.listen(6496)) {
            this.uploadItem = uploadItem;
        }
        if (!ListenerUtil.mutListener.listen(6497)) {
            showNearbyFound = true;
        }
        if (!ListenerUtil.mutListener.listen(6503)) {
            if (callback.getIndexInViewFlipper(this) == 0) {
                if (!ListenerUtil.mutListener.listen(6501)) {
                    if (UploadActivity.nearbyPopupAnswers.containsKey(nearbyPlace)) {
                        final boolean response = UploadActivity.nearbyPopupAnswers.get(nearbyPlace);
                        if (!ListenerUtil.mutListener.listen(6500)) {
                            if (response) {
                                if (!ListenerUtil.mutListener.listen(6499)) {
                                    presenter.onUserConfirmedUploadIsOfPlace(nearbyPlace, callback.getIndexInViewFlipper(this));
                                }
                            }
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(6498)) {
                            showNearbyPlaceFound(nearbyPlace);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(6502)) {
                    showNearbyFound = false;
                }
            }
        }
    }

    /**
     * Shows nearby place found popup
     * @param place
     */
    @SuppressLint("StringFormatInvalid")
    private // To avoid the unwanted lint warning that string 'upload_nearby_place_found_description' is not of a valid format
    void showNearbyPlaceFound(Place place) {
        final View customLayout = getLayoutInflater().inflate(R.layout.custom_nearby_found, null);
        ImageView nearbyFoundImage = customLayout.findViewById(R.id.nearbyItemImage);
        if (!ListenerUtil.mutListener.listen(6504)) {
            nearbyFoundImage.setImageURI(uploadItem.getMediaUri());
        }
        if (!ListenerUtil.mutListener.listen(6505)) {
            DialogUtil.showAlertDialog(getActivity(), getString(R.string.upload_nearby_place_found_title), String.format(Locale.getDefault(), getString(R.string.upload_nearby_place_found_description), place.getName()), () -> {
                UploadActivity.nearbyPopupAnswers.put(place, true);
                presenter.onUserConfirmedUploadIsOfPlace(place, callback.getIndexInViewFlipper(this));
            }, () -> {
                UploadActivity.nearbyPopupAnswers.put(place, false);
            }, customLayout, true);
        }
    }

    @Override
    public void showProgress(boolean shouldShow) {
        if (!ListenerUtil.mutListener.listen(6506)) {
            callback.showProgress(shouldShow);
        }
    }

    @Override
    public void onImageValidationSuccess() {
        if (!ListenerUtil.mutListener.listen(6507)) {
            callback.onNextButtonClicked(callback.getIndexInViewFlipper(this));
        }
    }

    /**
     * This method gets called whenever the next/previous button is pressed
     */
    @Override
    protected void onBecameVisible() {
        if (!ListenerUtil.mutListener.listen(6508)) {
            super.onBecameVisible();
        }
        if (!ListenerUtil.mutListener.listen(6509)) {
            presenter.fetchTitleAndDescription(callback.getIndexInViewFlipper(this));
        }
        if (!ListenerUtil.mutListener.listen(6515)) {
            if (showNearbyFound) {
                if (!ListenerUtil.mutListener.listen(6513)) {
                    if (UploadActivity.nearbyPopupAnswers.containsKey(nearbyPlace)) {
                        final boolean response = UploadActivity.nearbyPopupAnswers.get(nearbyPlace);
                        if (!ListenerUtil.mutListener.listen(6512)) {
                            if (response) {
                                if (!ListenerUtil.mutListener.listen(6511)) {
                                    presenter.onUserConfirmedUploadIsOfPlace(nearbyPlace, callback.getIndexInViewFlipper(this));
                                }
                            }
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(6510)) {
                            showNearbyPlaceFound(nearbyPlace);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(6514)) {
                    showNearbyFound = false;
                }
            }
        }
    }

    @Override
    public void showMessage(int stringResourceId, int colorResourceId) {
        if (!ListenerUtil.mutListener.listen(6516)) {
            ViewUtil.showLongToast(getContext(), stringResourceId);
        }
    }

    @Override
    public void showMessage(String message, int colorResourceId) {
        if (!ListenerUtil.mutListener.listen(6517)) {
            ViewUtil.showLongToast(getContext(), message);
        }
    }

    @Override
    public void showDuplicatePicturePopup(UploadItem uploadItem) {
        if (!ListenerUtil.mutListener.listen(6522)) {
            if (defaultKvStore.getBoolean("showDuplicatePicturePopup", true)) {
                String uploadTitleFormat = getString(R.string.upload_title_duplicate);
                View checkBoxView = View.inflate(getActivity(), R.layout.nearby_permission_dialog, null);
                CheckBox checkBox = (CheckBox) checkBoxView.findViewById(R.id.never_ask_again);
                if (!ListenerUtil.mutListener.listen(6520)) {
                    checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
                        if (isChecked) {
                            defaultKvStore.putBoolean("showDuplicatePicturePopup", false);
                        }
                    });
                }
                if (!ListenerUtil.mutListener.listen(6521)) {
                    DialogUtil.showAlertDialog(getActivity(), getString(R.string.duplicate_file_name), String.format(Locale.getDefault(), uploadTitleFormat, uploadItem.getFileName()), getString(R.string.upload), getString(R.string.cancel), () -> {
                        uploadItem.setImageQuality(ImageUtils.IMAGE_KEEP);
                        onImageValidationSuccess();
                    }, null, checkBoxView, false);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(6518)) {
                    uploadItem.setImageQuality(ImageUtils.IMAGE_KEEP);
                }
                if (!ListenerUtil.mutListener.listen(6519)) {
                    onNextButtonClicked();
                }
            }
        }
    }

    @Override
    public void showBadImagePopup(Integer errorCode, UploadItem uploadItem) {
        String errorMessageForResult = getErrorMessageForResult(getContext(), errorCode);
        if (!ListenerUtil.mutListener.listen(6524)) {
            if (!StringUtils.isBlank(errorMessageForResult)) {
                if (!ListenerUtil.mutListener.listen(6523)) {
                    DialogUtil.showAlertDialog(getActivity(), getString(R.string.upload_problem_image), errorMessageForResult, getString(R.string.upload), getString(R.string.cancel), () -> {
                        // show the same file name error if exists.
                        if ((errorCode & FILE_NAME_EXISTS) == 0) {
                            uploadItem.setImageQuality(ImageUtils.IMAGE_KEEP);
                            onImageValidationSuccess();
                        }
                    }, () -> deleteThisPicture());
                }
            }
        }
    }

    @Override
    public void showConnectionErrorPopup() {
        if (!ListenerUtil.mutListener.listen(6525)) {
            DialogUtil.showAlertDialog(getActivity(), getString(R.string.upload_connection_error_alert_title), getString(R.string.upload_connection_error_alert_detail), getString(R.string.ok), () -> {
            }, true);
        }
    }

    @Override
    public void showExternalMap(final UploadItem uploadItem) {
        if (!ListenerUtil.mutListener.listen(6526)) {
            goToLocationPickerActivity(uploadItem);
        }
    }

    /**
     * Launches the image editing activity to edit the specified UploadItem.
     *
     * @param uploadItem The UploadItem to be edited.
     *
     * This method is called to start the image editing activity for a specific UploadItem.
     * It sets the UploadItem as the currently editable item, creates an intent to launch the
     * EditActivity, and passes the image file path as an extra in the intent. The activity
     * is started with a request code, allowing the result to be handled in onActivityResult.
     */
    @Override
    public void showEditActivity(UploadItem uploadItem) {
        if (!ListenerUtil.mutListener.listen(6527)) {
            editableUploadItem = uploadItem;
        }
        Intent intent = new Intent(getContext(), EditActivity.class);
        if (!ListenerUtil.mutListener.listen(6528)) {
            intent.putExtra("image", uploadableFile.getFilePath().toString());
        }
        if (!ListenerUtil.mutListener.listen(6529)) {
            startActivityForResult(intent, REQUEST_CODE_FOR_EDIT_ACTIVITY);
        }
    }

    /**
     * Start Location picker activity. Show the location first then user can modify it by clicking
     * modify location button.
     * @param uploadItem current upload item
     */
    private void goToLocationPickerActivity(final UploadItem uploadItem) {
        if (!ListenerUtil.mutListener.listen(6530)) {
            editableUploadItem = uploadItem;
        }
        double defaultLatitude = 37.773972;
        double defaultLongitude = -122.431297;
        double defaultZoom = 16.0;
        if (!ListenerUtil.mutListener.listen(6543)) {
            /* Retrieve image location from EXIF if present or
           check if user has provided location while using the in-app camera.
           Use location of last UploadItem if none of them is available */
            if ((ListenerUtil.mutListener.listen(6532) ? ((ListenerUtil.mutListener.listen(6531) ? (uploadItem.getGpsCoords() != null || uploadItem.getGpsCoords().getDecLatitude() != 0.0) : (uploadItem.getGpsCoords() != null && uploadItem.getGpsCoords().getDecLatitude() != 0.0)) || uploadItem.getGpsCoords().getDecLongitude() != 0.0) : ((ListenerUtil.mutListener.listen(6531) ? (uploadItem.getGpsCoords() != null || uploadItem.getGpsCoords().getDecLatitude() != 0.0) : (uploadItem.getGpsCoords() != null && uploadItem.getGpsCoords().getDecLatitude() != 0.0)) && uploadItem.getGpsCoords().getDecLongitude() != 0.0))) {
                if (!ListenerUtil.mutListener.listen(6539)) {
                    defaultLatitude = uploadItem.getGpsCoords().getDecLatitude();
                }
                if (!ListenerUtil.mutListener.listen(6540)) {
                    defaultLongitude = uploadItem.getGpsCoords().getDecLongitude();
                }
                if (!ListenerUtil.mutListener.listen(6541)) {
                    defaultZoom = uploadItem.getGpsCoords().getZoomLevel();
                }
                if (!ListenerUtil.mutListener.listen(6542)) {
                    startActivityForResult(new LocationPicker.IntentBuilder().defaultLocation(new CameraPosition.Builder().target(new com.mapbox.mapboxsdk.geometry.LatLng(defaultLatitude, defaultLongitude)).zoom(defaultZoom).build()).activityKey("UploadActivity").build(getActivity()), REQUEST_CODE);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(6535)) {
                    if (defaultKvStore.getString(LAST_LOCATION) != null) {
                        final String[] locationLatLng = defaultKvStore.getString(LAST_LOCATION).split(",");
                        if (!ListenerUtil.mutListener.listen(6533)) {
                            defaultLatitude = Double.parseDouble(locationLatLng[0]);
                        }
                        if (!ListenerUtil.mutListener.listen(6534)) {
                            defaultLongitude = Double.parseDouble(locationLatLng[1]);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(6537)) {
                    if (defaultKvStore.getString(LAST_ZOOM) != null) {
                        if (!ListenerUtil.mutListener.listen(6536)) {
                            defaultZoom = Double.parseDouble(defaultKvStore.getString(LAST_ZOOM));
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(6538)) {
                    startActivityForResult(new LocationPicker.IntentBuilder().defaultLocation(new CameraPosition.Builder().target(new com.mapbox.mapboxsdk.geometry.LatLng(defaultLatitude, defaultLongitude)).zoom(defaultZoom).build()).activityKey("NoLocationUploadActivity").build(getActivity()), REQUEST_CODE);
                }
            }
        }
    }

    /**
     * Get the coordinates and update the existing coordinates.
     * @param requestCode code of request
     * @param resultCode code of result
     * @param data intent
     */
    @Override
    public void onActivityResult(final int requestCode, final int resultCode, @Nullable final Intent data) {
        if (!ListenerUtil.mutListener.listen(6544)) {
            super.onActivityResult(requestCode, resultCode, data);
        }
        if (!ListenerUtil.mutListener.listen(6556)) {
            if ((ListenerUtil.mutListener.listen(6550) ? ((ListenerUtil.mutListener.listen(6549) ? (requestCode >= REQUEST_CODE) : (ListenerUtil.mutListener.listen(6548) ? (requestCode <= REQUEST_CODE) : (ListenerUtil.mutListener.listen(6547) ? (requestCode > REQUEST_CODE) : (ListenerUtil.mutListener.listen(6546) ? (requestCode < REQUEST_CODE) : (ListenerUtil.mutListener.listen(6545) ? (requestCode != REQUEST_CODE) : (requestCode == REQUEST_CODE)))))) || resultCode == RESULT_OK) : ((ListenerUtil.mutListener.listen(6549) ? (requestCode >= REQUEST_CODE) : (ListenerUtil.mutListener.listen(6548) ? (requestCode <= REQUEST_CODE) : (ListenerUtil.mutListener.listen(6547) ? (requestCode > REQUEST_CODE) : (ListenerUtil.mutListener.listen(6546) ? (requestCode < REQUEST_CODE) : (ListenerUtil.mutListener.listen(6545) ? (requestCode != REQUEST_CODE) : (requestCode == REQUEST_CODE)))))) && resultCode == RESULT_OK))) {
                assert data != null;
                final CameraPosition cameraPosition = LocationPicker.getCameraPosition(data);
                if (!ListenerUtil.mutListener.listen(6555)) {
                    if (cameraPosition != null) {
                        final String latitude = String.valueOf(cameraPosition.target.getLatitude());
                        final String longitude = String.valueOf(cameraPosition.target.getLongitude());
                        final double zoom = cameraPosition.zoom;
                        if (!ListenerUtil.mutListener.listen(6551)) {
                            editLocation(latitude, longitude, zoom);
                        }
                        if (!ListenerUtil.mutListener.listen(6554)) {
                            /*
                       If isMissingLocationDialog is true, it means that the user has already tapped the
                       "Next" button, so go directly to the next step.
                 */
                            if (isMissingLocationDialog) {
                                if (!ListenerUtil.mutListener.listen(6552)) {
                                    isMissingLocationDialog = false;
                                }
                                if (!ListenerUtil.mutListener.listen(6553)) {
                                    onNextButtonClicked();
                                }
                            }
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(6578)) {
            if ((ListenerUtil.mutListener.listen(6562) ? ((ListenerUtil.mutListener.listen(6561) ? (requestCode >= REQUEST_CODE_FOR_EDIT_ACTIVITY) : (ListenerUtil.mutListener.listen(6560) ? (requestCode <= REQUEST_CODE_FOR_EDIT_ACTIVITY) : (ListenerUtil.mutListener.listen(6559) ? (requestCode > REQUEST_CODE_FOR_EDIT_ACTIVITY) : (ListenerUtil.mutListener.listen(6558) ? (requestCode < REQUEST_CODE_FOR_EDIT_ACTIVITY) : (ListenerUtil.mutListener.listen(6557) ? (requestCode != REQUEST_CODE_FOR_EDIT_ACTIVITY) : (requestCode == REQUEST_CODE_FOR_EDIT_ACTIVITY)))))) || resultCode == RESULT_OK) : ((ListenerUtil.mutListener.listen(6561) ? (requestCode >= REQUEST_CODE_FOR_EDIT_ACTIVITY) : (ListenerUtil.mutListener.listen(6560) ? (requestCode <= REQUEST_CODE_FOR_EDIT_ACTIVITY) : (ListenerUtil.mutListener.listen(6559) ? (requestCode > REQUEST_CODE_FOR_EDIT_ACTIVITY) : (ListenerUtil.mutListener.listen(6558) ? (requestCode < REQUEST_CODE_FOR_EDIT_ACTIVITY) : (ListenerUtil.mutListener.listen(6557) ? (requestCode != REQUEST_CODE_FOR_EDIT_ACTIVITY) : (requestCode == REQUEST_CODE_FOR_EDIT_ACTIVITY)))))) && resultCode == RESULT_OK))) {
                String result = data.getStringExtra("editedImageFilePath");
                if (!ListenerUtil.mutListener.listen(6573)) {
                    if (Objects.equals(result, "Error")) {
                        if (!ListenerUtil.mutListener.listen(6572)) {
                            Timber.e("Error in rotating image");
                        }
                        return;
                    }
                }
                try {
                    if (!ListenerUtil.mutListener.listen(6575)) {
                        photoViewBackgroundImage.setImageURI(Uri.fromFile(new File(result)));
                    }
                    if (!ListenerUtil.mutListener.listen(6576)) {
                        editableUploadItem.setContentUri(Uri.fromFile(new File(result)));
                    }
                    if (!ListenerUtil.mutListener.listen(6577)) {
                        callback.changeThumbnail(callback.getIndexInViewFlipper(this), result);
                    }
                } catch (Exception e) {
                    if (!ListenerUtil.mutListener.listen(6574)) {
                        Timber.e(e);
                    }
                }
            } else if ((ListenerUtil.mutListener.listen(6567) ? (requestCode >= REQUEST_CODE_FOR_VOICE_INPUT) : (ListenerUtil.mutListener.listen(6566) ? (requestCode <= REQUEST_CODE_FOR_VOICE_INPUT) : (ListenerUtil.mutListener.listen(6565) ? (requestCode > REQUEST_CODE_FOR_VOICE_INPUT) : (ListenerUtil.mutListener.listen(6564) ? (requestCode < REQUEST_CODE_FOR_VOICE_INPUT) : (ListenerUtil.mutListener.listen(6563) ? (requestCode != REQUEST_CODE_FOR_VOICE_INPUT) : (requestCode == REQUEST_CODE_FOR_VOICE_INPUT))))))) {
                if (!ListenerUtil.mutListener.listen(6571)) {
                    if ((ListenerUtil.mutListener.listen(6568) ? (resultCode == RESULT_OK || data != null) : (resultCode == RESULT_OK && data != null))) {
                        ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                        if (!ListenerUtil.mutListener.listen(6570)) {
                            uploadMediaDetailAdapter.handleSpeechResult(result.get(0));
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(6569)) {
                            Timber.e("Error %s", resultCode);
                        }
                    }
                }
            }
        }
    }

    /**
     * Update the old coordinates with new one
     * @param latitude new latitude
     * @param longitude new longitude
     */
    public void editLocation(final String latitude, final String longitude, final double zoom) {
        if (!ListenerUtil.mutListener.listen(6579)) {
            editableUploadItem.getGpsCoords().setDecLatitude(Double.parseDouble(latitude));
        }
        if (!ListenerUtil.mutListener.listen(6580)) {
            editableUploadItem.getGpsCoords().setDecLongitude(Double.parseDouble(longitude));
        }
        if (!ListenerUtil.mutListener.listen(6581)) {
            editableUploadItem.getGpsCoords().setDecimalCoords(latitude + "|" + longitude);
        }
        if (!ListenerUtil.mutListener.listen(6582)) {
            editableUploadItem.getGpsCoords().setImageCoordsExists(true);
        }
        if (!ListenerUtil.mutListener.listen(6583)) {
            editableUploadItem.getGpsCoords().setZoomLevel(zoom);
        }
        // Replace the map icon using the one with a green tick
        Drawable mapTick = getResources().getDrawable(R.drawable.ic_map_available_20dp);
        if (!ListenerUtil.mutListener.listen(6584)) {
            locationImageView.setImageDrawable(mapTick);
        }
        if (!ListenerUtil.mutListener.listen(6585)) {
            locationTextView.setText(R.string.edit_location);
        }
        if (!ListenerUtil.mutListener.listen(6586)) {
            Toast.makeText(getContext(), "Location Updated", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void updateMediaDetails(List<UploadMediaDetail> uploadMediaDetails) {
        if (!ListenerUtil.mutListener.listen(6587)) {
            uploadMediaDetailAdapter.setItems(uploadMediaDetails);
        }
        if (!ListenerUtil.mutListener.listen(6591)) {
            showNearbyFound = (ListenerUtil.mutListener.listen(6590) ? (showNearbyFound || ((ListenerUtil.mutListener.listen(6589) ? ((ListenerUtil.mutListener.listen(6588) ? (uploadMediaDetails == null && uploadMediaDetails.isEmpty()) : (uploadMediaDetails == null || uploadMediaDetails.isEmpty())) && listContainsEmptyDetails(uploadMediaDetails)) : ((ListenerUtil.mutListener.listen(6588) ? (uploadMediaDetails == null && uploadMediaDetails.isEmpty()) : (uploadMediaDetails == null || uploadMediaDetails.isEmpty())) || listContainsEmptyDetails(uploadMediaDetails))))) : (showNearbyFound && ((ListenerUtil.mutListener.listen(6589) ? ((ListenerUtil.mutListener.listen(6588) ? (uploadMediaDetails == null && uploadMediaDetails.isEmpty()) : (uploadMediaDetails == null || uploadMediaDetails.isEmpty())) && listContainsEmptyDetails(uploadMediaDetails)) : ((ListenerUtil.mutListener.listen(6588) ? (uploadMediaDetails == null && uploadMediaDetails.isEmpty()) : (uploadMediaDetails == null || uploadMediaDetails.isEmpty())) || listContainsEmptyDetails(uploadMediaDetails))))));
        }
    }

    /**
     * if the media details that come in here are empty
     * (empty caption AND empty description, with caption being the decider here)
     * this method allows usage of nearby place caption and description if any
     * else it takes the media details saved in prior for this picture
     * @param uploadMediaDetails saved media details,
     *                           ex: in case when "copy to subsequent media" button is clicked
     *                           for a previous image
     * @return boolean whether the details are empty or not
     */
    private boolean listContainsEmptyDetails(List<UploadMediaDetail> uploadMediaDetails) {
        if (!ListenerUtil.mutListener.listen(6594)) {
            {
                long _loopCounter101 = 0;
                for (UploadMediaDetail uploadDetail : uploadMediaDetails) {
                    ListenerUtil.loopListener.listen("_loopCounter101", ++_loopCounter101);
                    if (!ListenerUtil.mutListener.listen(6593)) {
                        if ((ListenerUtil.mutListener.listen(6592) ? (!TextUtils.isEmpty(uploadDetail.getCaptionText()) || !TextUtils.isEmpty(uploadDetail.getDescriptionText())) : (!TextUtils.isEmpty(uploadDetail.getCaptionText()) && !TextUtils.isEmpty(uploadDetail.getDescriptionText())))) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    /**
     * Showing dialog for adding location
     *
     * @param onSkipClicked proceed for verifying image quality
     */
    @Override
    public void displayAddLocationDialog(final Runnable onSkipClicked) {
        if (!ListenerUtil.mutListener.listen(6595)) {
            isMissingLocationDialog = true;
        }
        if (!ListenerUtil.mutListener.listen(6596)) {
            DialogUtil.showAlertDialog(Objects.requireNonNull(getActivity()), getString(R.string.no_location_found_title), getString(R.string.no_location_found_message), getString(R.string.add_location), getString(R.string.skip_login), this::onIbMapClicked, onSkipClicked);
        }
    }

    private void deleteThisPicture() {
        if (!ListenerUtil.mutListener.listen(6597)) {
            callback.deletePictureAtIndex(callback.getIndexInViewFlipper(this));
        }
    }

    @Override
    public void onDestroyView() {
        if (!ListenerUtil.mutListener.listen(6598)) {
            super.onDestroyView();
        }
        if (!ListenerUtil.mutListener.listen(6599)) {
            presenter.onDetachView();
        }
    }

    @OnClick(R.id.ll_container_title)
    public void onLlContainerTitleClicked() {
        if (!ListenerUtil.mutListener.listen(6600)) {
            expandCollapseLlMediaDetail(!isExpanded);
        }
    }

    /**
     * show hide media detail based on
     * @param shouldExpand
     */
    private void expandCollapseLlMediaDetail(boolean shouldExpand) {
        if (!ListenerUtil.mutListener.listen(6601)) {
            llContainerMediaDetail.setVisibility(shouldExpand ? View.VISIBLE : View.GONE);
        }
        if (!ListenerUtil.mutListener.listen(6602)) {
            isExpanded = !isExpanded;
        }
        if (!ListenerUtil.mutListener.listen(6603)) {
            ibExpandCollapse.setRotation(ibExpandCollapse.getRotation() + 180);
        }
    }

    @OnClick(R.id.ll_location_status)
    public void onIbMapClicked() {
        if (!ListenerUtil.mutListener.listen(6604)) {
            presenter.onMapIconClicked(callback.getIndexInViewFlipper(this));
        }
    }

    @Override
    public void onPrimaryCaptionTextChange(boolean isNotEmpty) {
        if (!ListenerUtil.mutListener.listen(6605)) {
            btnCopyToSubsequentMedia.setEnabled(isNotEmpty);
        }
        if (!ListenerUtil.mutListener.listen(6606)) {
            btnCopyToSubsequentMedia.setClickable(isNotEmpty);
        }
        if (!ListenerUtil.mutListener.listen(6607)) {
            btnCopyToSubsequentMedia.setAlpha(isNotEmpty ? 1.0f : 0.5f);
        }
        if (!ListenerUtil.mutListener.listen(6608)) {
            btnNext.setEnabled(isNotEmpty);
        }
        if (!ListenerUtil.mutListener.listen(6609)) {
            btnNext.setClickable(isNotEmpty);
        }
        if (!ListenerUtil.mutListener.listen(6610)) {
            btnNext.setAlpha(isNotEmpty ? 1.0f : 0.5f);
        }
    }

    /**
     * Adds new language item to RecyclerView
     */
    @Override
    public void addLanguage() {
        UploadMediaDetail uploadMediaDetail = new UploadMediaDetail();
        if (!ListenerUtil.mutListener.listen(6611)) {
            // This was manually added by the user
            uploadMediaDetail.setManuallyAdded(true);
        }
        if (!ListenerUtil.mutListener.listen(6612)) {
            uploadMediaDetailAdapter.addDescription(uploadMediaDetail);
        }
        if (!ListenerUtil.mutListener.listen(6617)) {
            rvDescriptions.smoothScrollToPosition((ListenerUtil.mutListener.listen(6616) ? (uploadMediaDetailAdapter.getItemCount() % 1) : (ListenerUtil.mutListener.listen(6615) ? (uploadMediaDetailAdapter.getItemCount() / 1) : (ListenerUtil.mutListener.listen(6614) ? (uploadMediaDetailAdapter.getItemCount() * 1) : (ListenerUtil.mutListener.listen(6613) ? (uploadMediaDetailAdapter.getItemCount() + 1) : (uploadMediaDetailAdapter.getItemCount() - 1))))));
        }
    }

    public interface UploadMediaDetailFragmentCallback extends Callback {

        void deletePictureAtIndex(int index);

        void changeThumbnail(int index, String uri);
    }

    @OnClick(R.id.btn_copy_subsequent_media)
    public void onButtonCopyTitleDescToSubsequentMedia() {
        if (!ListenerUtil.mutListener.listen(6618)) {
            presenter.copyTitleAndDescriptionToSubsequentMedia(callback.getIndexInViewFlipper(this));
        }
        if (!ListenerUtil.mutListener.listen(6619)) {
            Toast.makeText(getContext(), getResources().getString(R.string.copied_successfully), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onSaveInstanceState(final Bundle outState) {
        if (!ListenerUtil.mutListener.listen(6620)) {
            super.onSaveInstanceState(outState);
        }
        if (!ListenerUtil.mutListener.listen(6622)) {
            if (uploadableFile != null) {
                if (!ListenerUtil.mutListener.listen(6621)) {
                    outState.putParcelable(UPLOADABLE_FILE, uploadableFile);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(6624)) {
            if (uploadMediaDetailAdapter != null) {
                if (!ListenerUtil.mutListener.listen(6623)) {
                    outState.putParcelableArrayList(UPLOAD_MEDIA_DETAILS, (ArrayList<? extends Parcelable>) uploadMediaDetailAdapter.getItems());
                }
            }
        }
    }
}
