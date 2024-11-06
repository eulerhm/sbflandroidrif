package fr.free.nrw.commons.media;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;
import static android.view.View.GONE;
import static android.view.View.VISIBLE;
import static fr.free.nrw.commons.category.CategoryClientKt.CATEGORY_NEEDING_CATEGORIES;
import static fr.free.nrw.commons.category.CategoryClientKt.CATEGORY_UNCATEGORISED;
import static fr.free.nrw.commons.description.EditDescriptionConstants.LIST_OF_DESCRIPTION_AND_CAPTION;
import static fr.free.nrw.commons.description.EditDescriptionConstants.UPDATED_WIKITEXT;
import static fr.free.nrw.commons.description.EditDescriptionConstants.WIKITEXT;
import static fr.free.nrw.commons.upload.mediaDetails.UploadMediaDetailFragment.LAST_LOCATION;
import static fr.free.nrw.commons.utils.LangCodeUtils.getLocalizedResources;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.drawable.Animatable;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnKeyListener;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.webkit.WebView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.drawee.controller.ControllerListener;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.image.ImageInfo;
import com.facebook.imagepipeline.request.ImageRequest;
import com.mapbox.mapboxsdk.camera.CameraPosition;
import com.mapbox.mapboxsdk.geometry.LatLng;
import fr.free.nrw.commons.BuildConfig;
import fr.free.nrw.commons.LocationPicker.LocationPicker;
import fr.free.nrw.commons.Media;
import fr.free.nrw.commons.MediaDataExtractor;
import fr.free.nrw.commons.R;
import fr.free.nrw.commons.Utils;
import fr.free.nrw.commons.actions.ThanksClient;
import fr.free.nrw.commons.auth.AccountUtil;
import fr.free.nrw.commons.auth.SessionManager;
import fr.free.nrw.commons.category.CategoryClient;
import fr.free.nrw.commons.category.CategoryDetailsActivity;
import fr.free.nrw.commons.category.CategoryEditHelper;
import fr.free.nrw.commons.contributions.ContributionsFragment;
import fr.free.nrw.commons.coordinates.CoordinateEditHelper;
import fr.free.nrw.commons.delete.DeleteHelper;
import fr.free.nrw.commons.delete.ReasonBuilder;
import fr.free.nrw.commons.description.DescriptionEditActivity;
import fr.free.nrw.commons.description.DescriptionEditHelper;
import fr.free.nrw.commons.di.ApplicationlessInjection;
import fr.free.nrw.commons.di.CommonsDaggerSupportFragment;
import fr.free.nrw.commons.explore.depictions.WikidataItemDetailsActivity;
import fr.free.nrw.commons.kvstore.JsonKvStore;
import fr.free.nrw.commons.location.LocationServiceManager;
import fr.free.nrw.commons.media.ZoomableActivity.ZoomableActivityConstants;
import fr.free.nrw.commons.profile.ProfileActivity;
import fr.free.nrw.commons.review.ReviewController;
import fr.free.nrw.commons.review.ReviewHelper;
import fr.free.nrw.commons.settings.Prefs;
import fr.free.nrw.commons.ui.widget.HtmlTextView;
import fr.free.nrw.commons.upload.categories.UploadCategoriesFragment;
import fr.free.nrw.commons.upload.depicts.DepictsFragment;
import fr.free.nrw.commons.upload.UploadMediaDetail;
import fr.free.nrw.commons.utils.DialogUtil;
import fr.free.nrw.commons.utils.PermissionUtils;
import fr.free.nrw.commons.utils.ViewUtil;
import fr.free.nrw.commons.utils.ViewUtilWrapper;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.inject.Inject;
import javax.inject.Named;
import org.apache.commons.lang3.StringUtils;
import org.wikipedia.dataclient.mwapi.MwQueryPage;
import org.wikipedia.language.AppLanguageLookUpTable;
import org.wikipedia.util.DateUtil;
import timber.log.Timber;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class MediaDetailFragment extends CommonsDaggerSupportFragment implements CategoryEditHelper.Callback {

    private static final int REQUEST_CODE = 1001;

    private static final int REQUEST_CODE_EDIT_DESCRIPTION = 1002;

    private static final String IMAGE_BACKGROUND_COLOR = "image_background_color";

    static final int DEFAULT_IMAGE_BACKGROUND_COLOR = 0;

    private boolean editable;

    private boolean isCategoryImage;

    private MediaDetailPagerFragment.MediaDetailProvider detailProvider;

    private int index;

    private boolean isDeleted = false;

    private boolean isWikipediaButtonDisplayed;

    private Callback callback;

    @Inject
    LocationServiceManager locationManager;

    public static MediaDetailFragment forMedia(int index, boolean editable, boolean isCategoryImage, boolean isWikipediaButtonDisplayed) {
        MediaDetailFragment mf = new MediaDetailFragment();
        Bundle state = new Bundle();
        if (!ListenerUtil.mutListener.listen(8738)) {
            state.putBoolean("editable", editable);
        }
        if (!ListenerUtil.mutListener.listen(8739)) {
            state.putBoolean("isCategoryImage", isCategoryImage);
        }
        if (!ListenerUtil.mutListener.listen(8740)) {
            state.putInt("index", index);
        }
        if (!ListenerUtil.mutListener.listen(8741)) {
            state.putInt("listIndex", 0);
        }
        if (!ListenerUtil.mutListener.listen(8742)) {
            state.putInt("listTop", 0);
        }
        if (!ListenerUtil.mutListener.listen(8743)) {
            state.putBoolean("isWikipediaButtonDisplayed", isWikipediaButtonDisplayed);
        }
        if (!ListenerUtil.mutListener.listen(8744)) {
            mf.setArguments(state);
        }
        return mf;
    }

    @Inject
    SessionManager sessionManager;

    @Inject
    MediaDataExtractor mediaDataExtractor;

    @Inject
    ReasonBuilder reasonBuilder;

    @Inject
    DeleteHelper deleteHelper;

    @Inject
    ReviewHelper reviewHelper;

    @Inject
    CategoryEditHelper categoryEditHelper;

    @Inject
    CoordinateEditHelper coordinateEditHelper;

    @Inject
    DescriptionEditHelper descriptionEditHelper;

    @Inject
    ViewUtilWrapper viewUtil;

    @Inject
    CategoryClient categoryClient;

    @Inject
    ThanksClient thanksClient;

    @Inject
    @Named("default_preferences")
    JsonKvStore applicationKvStore;

    private int initialListTop = 0;

    @BindView(R.id.description_webview)
    WebView descriptionWebView;

    @BindView(R.id.mediaDetailFrameLayout)
    FrameLayout frameLayout;

    @BindView(R.id.mediaDetailImageView)
    SimpleDraweeView image;

    @BindView(R.id.mediaDetailImageViewSpacer)
    LinearLayout imageSpacer;

    @BindView(R.id.mediaDetailTitle)
    TextView title;

    @BindView(R.id.caption_layout)
    LinearLayout captionLayout;

    @BindView(R.id.depicts_layout)
    LinearLayout depictsLayout;

    @BindView(R.id.depictionsEditButton)
    Button depictEditButton;

    @BindView(R.id.media_detail_caption)
    TextView mediaCaption;

    @BindView(R.id.mediaDetailDesc)
    HtmlTextView desc;

    @BindView(R.id.mediaDetailAuthor)
    TextView author;

    @BindView(R.id.mediaDetailLicense)
    TextView license;

    @BindView(R.id.mediaDetailCoordinates)
    TextView coordinates;

    @BindView(R.id.mediaDetailuploadeddate)
    TextView uploadedDate;

    @BindView(R.id.mediaDetailDisc)
    TextView mediaDiscussion;

    @BindView(R.id.seeMore)
    TextView seeMore;

    @BindView(R.id.nominatedDeletionBanner)
    LinearLayout nominatedForDeletion;

    @BindView(R.id.mediaDetailCategoryContainer)
    LinearLayout categoryContainer;

    @BindView(R.id.categoryEditButton)
    Button categoryEditButton;

    @BindView(R.id.media_detail_depiction_container)
    LinearLayout depictionContainer;

    @BindView(R.id.authorLinearLayout)
    LinearLayout authorLayout;

    @BindView(R.id.nominateDeletion)
    Button delete;

    @BindView(R.id.mediaDetailScrollView)
    ScrollView scrollView;

    @BindView(R.id.toDoLayout)
    LinearLayout toDoLayout;

    @BindView(R.id.toDoReason)
    TextView toDoReason;

    @BindView(R.id.coordinate_edit)
    Button coordinateEditButton;

    @BindView(R.id.dummy_caption_description_container)
    LinearLayout showCaptionAndDescriptionContainer;

    @BindView(R.id.show_caption_description_textview)
    TextView showCaptionDescriptionTextView;

    @BindView(R.id.caption_listview)
    ListView captionsListView;

    @BindView(R.id.caption_label)
    TextView captionLabel;

    @BindView(R.id.description_label)
    TextView descriptionLabel;

    @BindView(R.id.pb_circular)
    ProgressBar progressBar;

    String descriptionHtmlCode;

    @BindView(R.id.progressBarDeletion)
    ProgressBar progressBarDeletion;

    @BindView(R.id.progressBarEdit)
    ProgressBar progressBarEditDescription;

    @BindView(R.id.progressBarEditCategory)
    ProgressBar progressBarEditCategory;

    @BindView(R.id.description_edit)
    Button editDescription;

    @BindView(R.id.sendThanks)
    Button sendThanksButton;

    private ArrayList<String> categoryNames = new ArrayList<>();

    private String categorySearchQuery;

    /**
     * Depicts is a feature part of Structured data. Multiple Depictions can be added for an image just like categories.
     * However unlike categories depictions is multi-lingual
     * Ex: key: en value: monument
     */
    private ImageInfo imageInfoCache;

    private int oldWidthOfImageView;

    private int newWidthOfImageView;

    // helps in maintaining aspect ratio
    private boolean heightVerifyingBoolean = true;

    // for layout stuff, only used once!
    private ViewTreeObserver.OnGlobalLayoutListener layoutListener;

    // Had to make this class variable, to implement various onClicks, which access the media, also I fell why make separate variables when one can serve the purpose
    private Media media;

    private ArrayList<String> reasonList;

    private ArrayList<String> reasonListEnglishMappings;

    /**
     * Height stores the height of the frame layout as soon as it is initialised and updates itself on
     * configuration changes.
     * Used to adjust aspect ratio of image when length of the image is too large.
     */
    private int frameLayoutHeight;

    /**
     * Minimum height of the metadata, in pixels.
     * Images with a very narrow aspect ratio will be reduced so that the metadata information panel always has at least this height.
     */
    private int minimumHeightOfMetadata = 200;

    static final String NOMINATING_FOR_DELETION_MEDIA = "Nominating for deletion %s";

    @Override
    public void onSaveInstanceState(Bundle outState) {
        if (!ListenerUtil.mutListener.listen(8745)) {
            super.onSaveInstanceState(outState);
        }
        if (!ListenerUtil.mutListener.listen(8746)) {
            outState.putInt("index", index);
        }
        if (!ListenerUtil.mutListener.listen(8747)) {
            outState.putBoolean("editable", editable);
        }
        if (!ListenerUtil.mutListener.listen(8748)) {
            outState.putBoolean("isCategoryImage", isCategoryImage);
        }
        if (!ListenerUtil.mutListener.listen(8749)) {
            outState.putBoolean("isWikipediaButtonDisplayed", isWikipediaButtonDisplayed);
        }
        if (!ListenerUtil.mutListener.listen(8750)) {
            getScrollPosition();
        }
        if (!ListenerUtil.mutListener.listen(8751)) {
            outState.putInt("listTop", initialListTop);
        }
    }

    private void getScrollPosition() {
        if (!ListenerUtil.mutListener.listen(8752)) {
            initialListTop = scrollView.getScrollY();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(8755)) {
            if ((ListenerUtil.mutListener.listen(8753) ? (getParentFragment() != null || getParentFragment() instanceof MediaDetailPagerFragment) : (getParentFragment() != null && getParentFragment() instanceof MediaDetailPagerFragment))) {
                if (!ListenerUtil.mutListener.listen(8754)) {
                    detailProvider = ((MediaDetailPagerFragment) getParentFragment()).getMediaDetailProvider();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(8766)) {
            if (savedInstanceState != null) {
                if (!ListenerUtil.mutListener.listen(8761)) {
                    editable = savedInstanceState.getBoolean("editable");
                }
                if (!ListenerUtil.mutListener.listen(8762)) {
                    isCategoryImage = savedInstanceState.getBoolean("isCategoryImage");
                }
                if (!ListenerUtil.mutListener.listen(8763)) {
                    isWikipediaButtonDisplayed = savedInstanceState.getBoolean("isWikipediaButtonDisplayed");
                }
                if (!ListenerUtil.mutListener.listen(8764)) {
                    index = savedInstanceState.getInt("index");
                }
                if (!ListenerUtil.mutListener.listen(8765)) {
                    initialListTop = savedInstanceState.getInt("listTop");
                }
            } else {
                if (!ListenerUtil.mutListener.listen(8756)) {
                    editable = getArguments().getBoolean("editable");
                }
                if (!ListenerUtil.mutListener.listen(8757)) {
                    isCategoryImage = getArguments().getBoolean("isCategoryImage");
                }
                if (!ListenerUtil.mutListener.listen(8758)) {
                    isWikipediaButtonDisplayed = getArguments().getBoolean("isWikipediaButtonDisplayed");
                }
                if (!ListenerUtil.mutListener.listen(8759)) {
                    index = getArguments().getInt("index");
                }
                if (!ListenerUtil.mutListener.listen(8760)) {
                    initialListTop = 0;
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(8767)) {
            reasonList = new ArrayList<>();
        }
        if (!ListenerUtil.mutListener.listen(8768)) {
            reasonList.add(getString(R.string.deletion_reason_uploaded_by_mistake));
        }
        if (!ListenerUtil.mutListener.listen(8769)) {
            reasonList.add(getString(R.string.deletion_reason_publicly_visible));
        }
        if (!ListenerUtil.mutListener.listen(8770)) {
            reasonList.add(getString(R.string.deletion_reason_not_interesting));
        }
        if (!ListenerUtil.mutListener.listen(8771)) {
            reasonList.add(getString(R.string.deletion_reason_no_longer_want_public));
        }
        if (!ListenerUtil.mutListener.listen(8772)) {
            reasonList.add(getString(R.string.deletion_reason_bad_for_my_privacy));
        }
        if (!ListenerUtil.mutListener.listen(8773)) {
            // Add corresponding mappings in english locale so that we can upload it in deletion request
            reasonListEnglishMappings = new ArrayList<>();
        }
        if (!ListenerUtil.mutListener.listen(8774)) {
            reasonListEnglishMappings.add(getLocalizedResources(getContext(), Locale.ENGLISH).getString(R.string.deletion_reason_uploaded_by_mistake));
        }
        if (!ListenerUtil.mutListener.listen(8775)) {
            reasonListEnglishMappings.add(getLocalizedResources(getContext(), Locale.ENGLISH).getString(R.string.deletion_reason_publicly_visible));
        }
        if (!ListenerUtil.mutListener.listen(8776)) {
            reasonListEnglishMappings.add(getLocalizedResources(getContext(), Locale.ENGLISH).getString(R.string.deletion_reason_not_interesting));
        }
        if (!ListenerUtil.mutListener.listen(8777)) {
            reasonListEnglishMappings.add(getLocalizedResources(getContext(), Locale.ENGLISH).getString(R.string.deletion_reason_no_longer_want_public));
        }
        if (!ListenerUtil.mutListener.listen(8778)) {
            reasonListEnglishMappings.add(getLocalizedResources(getContext(), Locale.ENGLISH).getString(R.string.deletion_reason_bad_for_my_privacy));
        }
        final View view = inflater.inflate(R.layout.fragment_media_detail, container, false);
        if (!ListenerUtil.mutListener.listen(8779)) {
            ButterKnife.bind(this, view);
        }
        if (!ListenerUtil.mutListener.listen(8780)) {
            Utils.setUnderlinedText(seeMore, R.string.nominated_see_more, requireContext());
        }
        if (!ListenerUtil.mutListener.listen(8783)) {
            if (isCategoryImage) {
                if (!ListenerUtil.mutListener.listen(8782)) {
                    authorLayout.setVisibility(VISIBLE);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(8781)) {
                    authorLayout.setVisibility(GONE);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(8785)) {
            if (!sessionManager.isUserLoggedIn()) {
                if (!ListenerUtil.mutListener.listen(8784)) {
                    categoryEditButton.setVisibility(GONE);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(8788)) {
            if (applicationKvStore.getBoolean("login_skipped")) {
                if (!ListenerUtil.mutListener.listen(8786)) {
                    delete.setVisibility(GONE);
                }
                if (!ListenerUtil.mutListener.listen(8787)) {
                    coordinateEditButton.setVisibility(GONE);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(8789)) {
            handleBackEvent(view);
        }
        if (!ListenerUtil.mutListener.listen(8792)) {
            /**
             * Gets the height of the frame layout as soon as the view is ready and updates aspect ratio
             * of the picture.
             */
            view.post(new Runnable() {

                @Override
                public void run() {
                    if (!ListenerUtil.mutListener.listen(8790)) {
                        frameLayoutHeight = frameLayout.getMeasuredHeight();
                    }
                    if (!ListenerUtil.mutListener.listen(8791)) {
                        updateAspectRatio(scrollView.getWidth());
                    }
                }
            });
        }
        return view;
    }

    @OnClick(R.id.mediaDetailImageViewSpacer)
    public void launchZoomActivity(final View view) {
        final boolean hasPermission = PermissionUtils.hasPermission(getActivity(), PermissionUtils.PERMISSIONS_STORAGE);
        if (!ListenerUtil.mutListener.listen(8795)) {
            if (hasPermission) {
                if (!ListenerUtil.mutListener.listen(8794)) {
                    launchZoomActivityAfterPermissionCheck(view);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(8793)) {
                    PermissionUtils.checkPermissionsAndPerformAction(getActivity(), () -> {
                        launchZoomActivityAfterPermissionCheck(view);
                    }, R.string.storage_permission_title, R.string.read_storage_permission_rationale, PermissionUtils.PERMISSIONS_STORAGE);
                }
            }
        }
    }

    /**
     * launch zoom acitivity after permission check
     * @param view as ImageView
     */
    private void launchZoomActivityAfterPermissionCheck(final View view) {
        if (!ListenerUtil.mutListener.listen(8806)) {
            if (media.getImageUrl() != null) {
                final Context ctx = view.getContext();
                final Intent zoomableIntent = new Intent(ctx, ZoomableActivity.class);
                if (!ListenerUtil.mutListener.listen(8796)) {
                    zoomableIntent.setData(Uri.parse(media.getImageUrl()));
                }
                if (!ListenerUtil.mutListener.listen(8797)) {
                    zoomableIntent.putExtra(ZoomableActivity.ZoomableActivityConstants.ORIGIN, "MediaDetails");
                }
                int backgroundColor = getImageBackgroundColor();
                if (!ListenerUtil.mutListener.listen(8804)) {
                    if ((ListenerUtil.mutListener.listen(8802) ? (backgroundColor >= DEFAULT_IMAGE_BACKGROUND_COLOR) : (ListenerUtil.mutListener.listen(8801) ? (backgroundColor <= DEFAULT_IMAGE_BACKGROUND_COLOR) : (ListenerUtil.mutListener.listen(8800) ? (backgroundColor > DEFAULT_IMAGE_BACKGROUND_COLOR) : (ListenerUtil.mutListener.listen(8799) ? (backgroundColor < DEFAULT_IMAGE_BACKGROUND_COLOR) : (ListenerUtil.mutListener.listen(8798) ? (backgroundColor == DEFAULT_IMAGE_BACKGROUND_COLOR) : (backgroundColor != DEFAULT_IMAGE_BACKGROUND_COLOR))))))) {
                        if (!ListenerUtil.mutListener.listen(8803)) {
                            zoomableIntent.putExtra(ZoomableActivity.ZoomableActivityConstants.PHOTO_BACKGROUND_COLOR, backgroundColor);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(8805)) {
                    ctx.startActivity(zoomableIntent);
                }
            }
        }
    }

    @Override
    public void onResume() {
        if (!ListenerUtil.mutListener.listen(8807)) {
            super.onResume();
        }
        if (!ListenerUtil.mutListener.listen(8811)) {
            if ((ListenerUtil.mutListener.listen(8808) ? (getParentFragment() != null || getParentFragment().getParentFragment() != null) : (getParentFragment() != null && getParentFragment().getParentFragment() != null))) {
                if (!ListenerUtil.mutListener.listen(8810)) {
                    // in the case when MediaDetailPagerFragment is directly started by the CategoryImagesActivity
                    if (getParentFragment() instanceof ContributionsFragment) {
                        if (!ListenerUtil.mutListener.listen(8809)) {
                            ((ContributionsFragment) (getParentFragment().getParentFragment())).nearbyNotificationCardView.setVisibility(View.GONE);
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(8814)) {
            // detail provider is null when fragment is shown in review activity
            if (detailProvider != null) {
                if (!ListenerUtil.mutListener.listen(8813)) {
                    media = detailProvider.getMediaAtPosition(index);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(8812)) {
                    media = getArguments().getParcelable("media");
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(8817)) {
            if ((ListenerUtil.mutListener.listen(8815) ? (media != null || applicationKvStore.getBoolean(String.format(NOMINATING_FOR_DELETION_MEDIA, media.getImageUrl()), false)) : (media != null && applicationKvStore.getBoolean(String.format(NOMINATING_FOR_DELETION_MEDIA, media.getImageUrl()), false)))) {
                if (!ListenerUtil.mutListener.listen(8816)) {
                    enableProgressBar();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(8822)) {
            if ((ListenerUtil.mutListener.listen(8819) ? ((ListenerUtil.mutListener.listen(8818) ? (AccountUtil.getUserName(getContext()) != null || media != null) : (AccountUtil.getUserName(getContext()) != null && media != null)) || AccountUtil.getUserName(getContext()).equals(media.getAuthor())) : ((ListenerUtil.mutListener.listen(8818) ? (AccountUtil.getUserName(getContext()) != null || media != null) : (AccountUtil.getUserName(getContext()) != null && media != null)) && AccountUtil.getUserName(getContext()).equals(media.getAuthor())))) {
                if (!ListenerUtil.mutListener.listen(8821)) {
                    sendThanksButton.setVisibility(GONE);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(8820)) {
                    sendThanksButton.setVisibility(VISIBLE);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(8828)) {
            scrollView.getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener() {

                @Override
                public void onGlobalLayout() {
                    if (!ListenerUtil.mutListener.listen(8823)) {
                        if (getContext() == null) {
                            return;
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(8824)) {
                        scrollView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    }
                    if (!ListenerUtil.mutListener.listen(8825)) {
                        oldWidthOfImageView = scrollView.getWidth();
                    }
                    if (!ListenerUtil.mutListener.listen(8827)) {
                        if (media != null) {
                            if (!ListenerUtil.mutListener.listen(8826)) {
                                displayMediaDetails();
                            }
                        }
                    }
                }
            });
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        if (!ListenerUtil.mutListener.listen(8829)) {
            super.onConfigurationChanged(newConfig);
        }
        if (!ListenerUtil.mutListener.listen(8838)) {
            scrollView.getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener() {

                @Override
                public void onGlobalLayout() {
                    if (!ListenerUtil.mutListener.listen(8832)) {
                        /**
                         * We update the height of the frame layout as the configuration changes.
                         */
                        frameLayout.post(new Runnable() {

                            @Override
                            public void run() {
                                if (!ListenerUtil.mutListener.listen(8830)) {
                                    frameLayoutHeight = frameLayout.getMeasuredHeight();
                                }
                                if (!ListenerUtil.mutListener.listen(8831)) {
                                    updateAspectRatio(scrollView.getWidth());
                                }
                            }
                        });
                    }
                    if (!ListenerUtil.mutListener.listen(8837)) {
                        if (scrollView.getWidth() != oldWidthOfImageView) {
                            if (!ListenerUtil.mutListener.listen(8835)) {
                                if (newWidthOfImageView == 0) {
                                    if (!ListenerUtil.mutListener.listen(8833)) {
                                        newWidthOfImageView = scrollView.getWidth();
                                    }
                                    if (!ListenerUtil.mutListener.listen(8834)) {
                                        updateAspectRatio(newWidthOfImageView);
                                    }
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(8836)) {
                                scrollView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                            }
                        }
                    }
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(8843)) {
            // Ensuring correct aspect ratio for landscape mode
            if (heightVerifyingBoolean) {
                if (!ListenerUtil.mutListener.listen(8841)) {
                    updateAspectRatio(newWidthOfImageView);
                }
                if (!ListenerUtil.mutListener.listen(8842)) {
                    heightVerifyingBoolean = false;
                }
            } else {
                if (!ListenerUtil.mutListener.listen(8839)) {
                    updateAspectRatio(oldWidthOfImageView);
                }
                if (!ListenerUtil.mutListener.listen(8840)) {
                    heightVerifyingBoolean = true;
                }
            }
        }
    }

    private void displayMediaDetails() {
        if (!ListenerUtil.mutListener.listen(8844)) {
            setTextFields(media);
        }
        if (!ListenerUtil.mutListener.listen(8845)) {
            compositeDisposable.addAll(mediaDataExtractor.refresh(media).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(this::onMediaRefreshed, Timber::e), mediaDataExtractor.getCurrentWikiText(Objects.requireNonNull(media.getFilename())).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(this::updateCategoryList, Timber::e), mediaDataExtractor.checkDeletionRequestExists(media).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(this::onDeletionPageExists, Timber::e), mediaDataExtractor.fetchDiscussion(media).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(this::onDiscussionLoaded, Timber::e));
        }
    }

    private void onMediaRefreshed(Media media) {
        if (!ListenerUtil.mutListener.listen(8846)) {
            media.setCategories(this.media.getCategories());
        }
        if (!ListenerUtil.mutListener.listen(8847)) {
            this.media = media;
        }
        if (!ListenerUtil.mutListener.listen(8848)) {
            setTextFields(media);
        }
        if (!ListenerUtil.mutListener.listen(8849)) {
            compositeDisposable.addAll(mediaDataExtractor.fetchDepictionIdsAndLabels(media).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(this::onDepictionsLoaded, Timber::e));
        }
    }

    private void onDiscussionLoaded(String discussion) {
        if (!ListenerUtil.mutListener.listen(8850)) {
            mediaDiscussion.setText(prettyDiscussion(discussion.trim()));
        }
    }

    private void onDeletionPageExists(Boolean deletionPageExists) {
        if (!ListenerUtil.mutListener.listen(8858)) {
            if (deletionPageExists) {
                if (!ListenerUtil.mutListener.listen(8855)) {
                    if (applicationKvStore.getBoolean(String.format(NOMINATING_FOR_DELETION_MEDIA, media.getImageUrl()), false)) {
                        if (!ListenerUtil.mutListener.listen(8853)) {
                            applicationKvStore.remove(String.format(NOMINATING_FOR_DELETION_MEDIA, media.getImageUrl()));
                        }
                        if (!ListenerUtil.mutListener.listen(8854)) {
                            progressBarDeletion.setVisibility(GONE);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(8856)) {
                    delete.setVisibility(GONE);
                }
                if (!ListenerUtil.mutListener.listen(8857)) {
                    nominatedForDeletion.setVisibility(VISIBLE);
                }
            } else if (!isCategoryImage) {
                if (!ListenerUtil.mutListener.listen(8851)) {
                    delete.setVisibility(VISIBLE);
                }
                if (!ListenerUtil.mutListener.listen(8852)) {
                    nominatedForDeletion.setVisibility(GONE);
                }
            }
        }
    }

    private void onDepictionsLoaded(List<IdAndCaptions> idAndCaptions) {
        if (!ListenerUtil.mutListener.listen(8859)) {
            depictsLayout.setVisibility(idAndCaptions.isEmpty() ? GONE : VISIBLE);
        }
        if (!ListenerUtil.mutListener.listen(8860)) {
            depictEditButton.setVisibility(idAndCaptions.isEmpty() ? GONE : VISIBLE);
        }
        if (!ListenerUtil.mutListener.listen(8861)) {
            buildDepictionList(idAndCaptions);
        }
    }

    /**
     * By clicking on the edit depictions button, it will send user to depict fragment
     */
    @OnClick(R.id.depictionsEditButton)
    public void onDepictionsEditButtonClicked() {
        if (!ListenerUtil.mutListener.listen(8862)) {
            depictionContainer.removeAllViews();
        }
        if (!ListenerUtil.mutListener.listen(8863)) {
            depictEditButton.setVisibility(GONE);
        }
        final Fragment depictsFragment = new DepictsFragment();
        final Bundle bundle = new Bundle();
        if (!ListenerUtil.mutListener.listen(8864)) {
            bundle.putParcelable("Existing_Depicts", media);
        }
        if (!ListenerUtil.mutListener.listen(8865)) {
            depictsFragment.setArguments(bundle);
        }
        final FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        if (!ListenerUtil.mutListener.listen(8866)) {
            transaction.replace(R.id.mediaDetailFrameLayout, depictsFragment);
        }
        if (!ListenerUtil.mutListener.listen(8867)) {
            transaction.addToBackStack(null);
        }
        if (!ListenerUtil.mutListener.listen(8868)) {
            transaction.commit();
        }
    }

    /**
     * The imageSpacer is Basically a transparent overlay for the SimpleDraweeView
     * which holds the image to be displayed( moreover this image is out of
     * the scroll view )
     *
     * If the image is sufficiently large i.e. the image height extends the view height, we reduce
     * the height and change the width to maintain the aspect ratio, otherwise image takes up the
     * total possible width and height is adjusted accordingly.
     *
     * @param scrollWidth the current width of the scrollView
     */
    private void updateAspectRatio(int scrollWidth) {
        if (!ListenerUtil.mutListener.listen(8906)) {
            if (imageInfoCache != null) {
                int finalHeight = (ListenerUtil.mutListener.listen(8876) ? (((ListenerUtil.mutListener.listen(8872) ? (scrollWidth % imageInfoCache.getHeight()) : (ListenerUtil.mutListener.listen(8871) ? (scrollWidth / imageInfoCache.getHeight()) : (ListenerUtil.mutListener.listen(8870) ? (scrollWidth - imageInfoCache.getHeight()) : (ListenerUtil.mutListener.listen(8869) ? (scrollWidth + imageInfoCache.getHeight()) : (scrollWidth * imageInfoCache.getHeight())))))) % imageInfoCache.getWidth()) : (ListenerUtil.mutListener.listen(8875) ? (((ListenerUtil.mutListener.listen(8872) ? (scrollWidth % imageInfoCache.getHeight()) : (ListenerUtil.mutListener.listen(8871) ? (scrollWidth / imageInfoCache.getHeight()) : (ListenerUtil.mutListener.listen(8870) ? (scrollWidth - imageInfoCache.getHeight()) : (ListenerUtil.mutListener.listen(8869) ? (scrollWidth + imageInfoCache.getHeight()) : (scrollWidth * imageInfoCache.getHeight())))))) * imageInfoCache.getWidth()) : (ListenerUtil.mutListener.listen(8874) ? (((ListenerUtil.mutListener.listen(8872) ? (scrollWidth % imageInfoCache.getHeight()) : (ListenerUtil.mutListener.listen(8871) ? (scrollWidth / imageInfoCache.getHeight()) : (ListenerUtil.mutListener.listen(8870) ? (scrollWidth - imageInfoCache.getHeight()) : (ListenerUtil.mutListener.listen(8869) ? (scrollWidth + imageInfoCache.getHeight()) : (scrollWidth * imageInfoCache.getHeight())))))) - imageInfoCache.getWidth()) : (ListenerUtil.mutListener.listen(8873) ? (((ListenerUtil.mutListener.listen(8872) ? (scrollWidth % imageInfoCache.getHeight()) : (ListenerUtil.mutListener.listen(8871) ? (scrollWidth / imageInfoCache.getHeight()) : (ListenerUtil.mutListener.listen(8870) ? (scrollWidth - imageInfoCache.getHeight()) : (ListenerUtil.mutListener.listen(8869) ? (scrollWidth + imageInfoCache.getHeight()) : (scrollWidth * imageInfoCache.getHeight())))))) + imageInfoCache.getWidth()) : (((ListenerUtil.mutListener.listen(8872) ? (scrollWidth % imageInfoCache.getHeight()) : (ListenerUtil.mutListener.listen(8871) ? (scrollWidth / imageInfoCache.getHeight()) : (ListenerUtil.mutListener.listen(8870) ? (scrollWidth - imageInfoCache.getHeight()) : (ListenerUtil.mutListener.listen(8869) ? (scrollWidth + imageInfoCache.getHeight()) : (scrollWidth * imageInfoCache.getHeight())))))) / imageInfoCache.getWidth())))));
                ViewGroup.LayoutParams params = image.getLayoutParams();
                ViewGroup.LayoutParams spacerParams = imageSpacer.getLayoutParams();
                if (!ListenerUtil.mutListener.listen(8877)) {
                    params.width = scrollWidth;
                }
                if (!ListenerUtil.mutListener.listen(8901)) {
                    if ((ListenerUtil.mutListener.listen(8886) ? (finalHeight >= (ListenerUtil.mutListener.listen(8881) ? (frameLayoutHeight % minimumHeightOfMetadata) : (ListenerUtil.mutListener.listen(8880) ? (frameLayoutHeight / minimumHeightOfMetadata) : (ListenerUtil.mutListener.listen(8879) ? (frameLayoutHeight * minimumHeightOfMetadata) : (ListenerUtil.mutListener.listen(8878) ? (frameLayoutHeight + minimumHeightOfMetadata) : (frameLayoutHeight - minimumHeightOfMetadata)))))) : (ListenerUtil.mutListener.listen(8885) ? (finalHeight <= (ListenerUtil.mutListener.listen(8881) ? (frameLayoutHeight % minimumHeightOfMetadata) : (ListenerUtil.mutListener.listen(8880) ? (frameLayoutHeight / minimumHeightOfMetadata) : (ListenerUtil.mutListener.listen(8879) ? (frameLayoutHeight * minimumHeightOfMetadata) : (ListenerUtil.mutListener.listen(8878) ? (frameLayoutHeight + minimumHeightOfMetadata) : (frameLayoutHeight - minimumHeightOfMetadata)))))) : (ListenerUtil.mutListener.listen(8884) ? (finalHeight < (ListenerUtil.mutListener.listen(8881) ? (frameLayoutHeight % minimumHeightOfMetadata) : (ListenerUtil.mutListener.listen(8880) ? (frameLayoutHeight / minimumHeightOfMetadata) : (ListenerUtil.mutListener.listen(8879) ? (frameLayoutHeight * minimumHeightOfMetadata) : (ListenerUtil.mutListener.listen(8878) ? (frameLayoutHeight + minimumHeightOfMetadata) : (frameLayoutHeight - minimumHeightOfMetadata)))))) : (ListenerUtil.mutListener.listen(8883) ? (finalHeight != (ListenerUtil.mutListener.listen(8881) ? (frameLayoutHeight % minimumHeightOfMetadata) : (ListenerUtil.mutListener.listen(8880) ? (frameLayoutHeight / minimumHeightOfMetadata) : (ListenerUtil.mutListener.listen(8879) ? (frameLayoutHeight * minimumHeightOfMetadata) : (ListenerUtil.mutListener.listen(8878) ? (frameLayoutHeight + minimumHeightOfMetadata) : (frameLayoutHeight - minimumHeightOfMetadata)))))) : (ListenerUtil.mutListener.listen(8882) ? (finalHeight == (ListenerUtil.mutListener.listen(8881) ? (frameLayoutHeight % minimumHeightOfMetadata) : (ListenerUtil.mutListener.listen(8880) ? (frameLayoutHeight / minimumHeightOfMetadata) : (ListenerUtil.mutListener.listen(8879) ? (frameLayoutHeight * minimumHeightOfMetadata) : (ListenerUtil.mutListener.listen(8878) ? (frameLayoutHeight + minimumHeightOfMetadata) : (frameLayoutHeight - minimumHeightOfMetadata)))))) : (finalHeight > (ListenerUtil.mutListener.listen(8881) ? (frameLayoutHeight % minimumHeightOfMetadata) : (ListenerUtil.mutListener.listen(8880) ? (frameLayoutHeight / minimumHeightOfMetadata) : (ListenerUtil.mutListener.listen(8879) ? (frameLayoutHeight * minimumHeightOfMetadata) : (ListenerUtil.mutListener.listen(8878) ? (frameLayoutHeight + minimumHeightOfMetadata) : (frameLayoutHeight - minimumHeightOfMetadata)))))))))))) {
                        // Adjust the height and width of image.
                        int temp = (ListenerUtil.mutListener.listen(8890) ? (frameLayoutHeight % minimumHeightOfMetadata) : (ListenerUtil.mutListener.listen(8889) ? (frameLayoutHeight / minimumHeightOfMetadata) : (ListenerUtil.mutListener.listen(8888) ? (frameLayoutHeight * minimumHeightOfMetadata) : (ListenerUtil.mutListener.listen(8887) ? (frameLayoutHeight + minimumHeightOfMetadata) : (frameLayoutHeight - minimumHeightOfMetadata)))));
                        if (!ListenerUtil.mutListener.listen(8899)) {
                            params.width = (ListenerUtil.mutListener.listen(8898) ? (((ListenerUtil.mutListener.listen(8894) ? (scrollWidth % temp) : (ListenerUtil.mutListener.listen(8893) ? (scrollWidth / temp) : (ListenerUtil.mutListener.listen(8892) ? (scrollWidth - temp) : (ListenerUtil.mutListener.listen(8891) ? (scrollWidth + temp) : (scrollWidth * temp)))))) % finalHeight) : (ListenerUtil.mutListener.listen(8897) ? (((ListenerUtil.mutListener.listen(8894) ? (scrollWidth % temp) : (ListenerUtil.mutListener.listen(8893) ? (scrollWidth / temp) : (ListenerUtil.mutListener.listen(8892) ? (scrollWidth - temp) : (ListenerUtil.mutListener.listen(8891) ? (scrollWidth + temp) : (scrollWidth * temp)))))) * finalHeight) : (ListenerUtil.mutListener.listen(8896) ? (((ListenerUtil.mutListener.listen(8894) ? (scrollWidth % temp) : (ListenerUtil.mutListener.listen(8893) ? (scrollWidth / temp) : (ListenerUtil.mutListener.listen(8892) ? (scrollWidth - temp) : (ListenerUtil.mutListener.listen(8891) ? (scrollWidth + temp) : (scrollWidth * temp)))))) - finalHeight) : (ListenerUtil.mutListener.listen(8895) ? (((ListenerUtil.mutListener.listen(8894) ? (scrollWidth % temp) : (ListenerUtil.mutListener.listen(8893) ? (scrollWidth / temp) : (ListenerUtil.mutListener.listen(8892) ? (scrollWidth - temp) : (ListenerUtil.mutListener.listen(8891) ? (scrollWidth + temp) : (scrollWidth * temp)))))) + finalHeight) : (((ListenerUtil.mutListener.listen(8894) ? (scrollWidth % temp) : (ListenerUtil.mutListener.listen(8893) ? (scrollWidth / temp) : (ListenerUtil.mutListener.listen(8892) ? (scrollWidth - temp) : (ListenerUtil.mutListener.listen(8891) ? (scrollWidth + temp) : (scrollWidth * temp)))))) / finalHeight)))));
                        }
                        if (!ListenerUtil.mutListener.listen(8900)) {
                            finalHeight = temp;
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(8902)) {
                    params.height = finalHeight;
                }
                if (!ListenerUtil.mutListener.listen(8903)) {
                    spacerParams.height = finalHeight;
                }
                if (!ListenerUtil.mutListener.listen(8904)) {
                    image.setLayoutParams(params);
                }
                if (!ListenerUtil.mutListener.listen(8905)) {
                    imageSpacer.setLayoutParams(spacerParams);
                }
            }
        }
    }

    private final ControllerListener aspectRatioListener = new BaseControllerListener<ImageInfo>() {

        @Override
        public void onIntermediateImageSet(String id, @Nullable ImageInfo imageInfo) {
            if (!ListenerUtil.mutListener.listen(8907)) {
                imageInfoCache = imageInfo;
            }
            if (!ListenerUtil.mutListener.listen(8908)) {
                updateAspectRatio(scrollView.getWidth());
            }
        }

        @Override
        public void onFinalImageSet(String id, @Nullable ImageInfo imageInfo, @Nullable Animatable animatable) {
            if (!ListenerUtil.mutListener.listen(8909)) {
                imageInfoCache = imageInfo;
            }
            if (!ListenerUtil.mutListener.listen(8910)) {
                updateAspectRatio(scrollView.getWidth());
            }
        }
    };

    /**
     * Uses two image sources.
     * - low resolution thumbnail is shown initially
     * - when the high resolution image is available, it replaces the low resolution image
     */
    private void setupImageView() {
        int imageBackgroundColor = getImageBackgroundColor();
        if (!ListenerUtil.mutListener.listen(8917)) {
            if ((ListenerUtil.mutListener.listen(8915) ? (imageBackgroundColor >= DEFAULT_IMAGE_BACKGROUND_COLOR) : (ListenerUtil.mutListener.listen(8914) ? (imageBackgroundColor <= DEFAULT_IMAGE_BACKGROUND_COLOR) : (ListenerUtil.mutListener.listen(8913) ? (imageBackgroundColor > DEFAULT_IMAGE_BACKGROUND_COLOR) : (ListenerUtil.mutListener.listen(8912) ? (imageBackgroundColor < DEFAULT_IMAGE_BACKGROUND_COLOR) : (ListenerUtil.mutListener.listen(8911) ? (imageBackgroundColor == DEFAULT_IMAGE_BACKGROUND_COLOR) : (imageBackgroundColor != DEFAULT_IMAGE_BACKGROUND_COLOR))))))) {
                if (!ListenerUtil.mutListener.listen(8916)) {
                    image.setBackgroundColor(imageBackgroundColor);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(8918)) {
            image.getHierarchy().setPlaceholderImage(R.drawable.image_placeholder);
        }
        if (!ListenerUtil.mutListener.listen(8919)) {
            image.getHierarchy().setFailureImage(R.drawable.image_placeholder);
        }
        DraweeController controller = Fresco.newDraweeControllerBuilder().setLowResImageRequest(ImageRequest.fromUri(media != null ? media.getThumbUrl() : null)).setRetainImageOnFailure(true).setImageRequest(ImageRequest.fromUri(media != null ? media.getImageUrl() : null)).setControllerListener(aspectRatioListener).setOldController(image.getController()).build();
        if (!ListenerUtil.mutListener.listen(8920)) {
            image.setController(controller);
        }
    }

    private void updateToDoWarning() {
        String toDoMessage = "";
        boolean toDoNeeded = false;
        boolean categoriesPresent = media.getCategories() == null ? false : (media.getCategories().size() == 0 ? false : true);
        if (!ListenerUtil.mutListener.listen(8925)) {
            // Check if the presented category is about need of category
            if (categoriesPresent) {
                if (!ListenerUtil.mutListener.listen(8924)) {
                    {
                        long _loopCounter146 = 0;
                        for (String category : media.getCategories()) {
                            ListenerUtil.loopListener.listen("_loopCounter146", ++_loopCounter146);
                            if (!ListenerUtil.mutListener.listen(8923)) {
                                if ((ListenerUtil.mutListener.listen(8921) ? (category.toLowerCase().contains(CATEGORY_NEEDING_CATEGORIES) && category.toLowerCase().contains(CATEGORY_UNCATEGORISED)) : (category.toLowerCase().contains(CATEGORY_NEEDING_CATEGORIES) || category.toLowerCase().contains(CATEGORY_UNCATEGORISED)))) {
                                    if (!ListenerUtil.mutListener.listen(8922)) {
                                        categoriesPresent = false;
                                    }
                                }
                            }
                            break;
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(8928)) {
            if (!categoriesPresent) {
                if (!ListenerUtil.mutListener.listen(8926)) {
                    toDoNeeded = true;
                }
                if (!ListenerUtil.mutListener.listen(8927)) {
                    toDoMessage += getString(R.string.missing_category);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(8931)) {
            if (isWikipediaButtonDisplayed) {
                if (!ListenerUtil.mutListener.listen(8929)) {
                    toDoNeeded = true;
                }
                if (!ListenerUtil.mutListener.listen(8930)) {
                    toDoMessage += (toDoMessage.isEmpty()) ? "" : "\n" + getString(R.string.missing_article);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(8936)) {
            if (toDoNeeded) {
                if (!ListenerUtil.mutListener.listen(8933)) {
                    toDoMessage = getString(R.string.todo_improve) + "\n" + toDoMessage;
                }
                if (!ListenerUtil.mutListener.listen(8934)) {
                    toDoLayout.setVisibility(VISIBLE);
                }
                if (!ListenerUtil.mutListener.listen(8935)) {
                    toDoReason.setText(toDoMessage);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(8932)) {
                    toDoLayout.setVisibility(GONE);
                }
            }
        }
    }

    @Override
    public void onDestroyView() {
        if (!ListenerUtil.mutListener.listen(8940)) {
            if ((ListenerUtil.mutListener.listen(8937) ? (layoutListener != null || getView() != null) : (layoutListener != null && getView() != null))) {
                if (!ListenerUtil.mutListener.listen(8938)) {
                    // old Android was on crack. CRACK IS WHACK
                    getView().getViewTreeObserver().removeGlobalOnLayoutListener(layoutListener);
                }
                if (!ListenerUtil.mutListener.listen(8939)) {
                    layoutListener = null;
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(8941)) {
            compositeDisposable.clear();
        }
        if (!ListenerUtil.mutListener.listen(8942)) {
            super.onDestroyView();
        }
    }

    private void setTextFields(Media media) {
        if (!ListenerUtil.mutListener.listen(8943)) {
            setupImageView();
        }
        if (!ListenerUtil.mutListener.listen(8944)) {
            title.setText(media.getDisplayTitle());
        }
        if (!ListenerUtil.mutListener.listen(8945)) {
            desc.setHtmlText(prettyDescription(media));
        }
        if (!ListenerUtil.mutListener.listen(8946)) {
            license.setText(prettyLicense(media));
        }
        if (!ListenerUtil.mutListener.listen(8947)) {
            coordinates.setText(prettyCoordinates(media));
        }
        if (!ListenerUtil.mutListener.listen(8948)) {
            uploadedDate.setText(prettyUploadedDate(media));
        }
        if (!ListenerUtil.mutListener.listen(8951)) {
            if (prettyCaption(media).equals(getContext().getString(R.string.detail_caption_empty))) {
                if (!ListenerUtil.mutListener.listen(8950)) {
                    captionLayout.setVisibility(GONE);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(8949)) {
                    mediaCaption.setText(prettyCaption(media));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(8952)) {
            categoryNames.clear();
        }
        if (!ListenerUtil.mutListener.listen(8953)) {
            categoryNames.addAll(media.getCategories());
        }
        if (!ListenerUtil.mutListener.listen(8957)) {
            if ((ListenerUtil.mutListener.listen(8954) ? (media.getAuthor() == null && media.getAuthor().equals("")) : (media.getAuthor() == null || media.getAuthor().equals("")))) {
                if (!ListenerUtil.mutListener.listen(8956)) {
                    authorLayout.setVisibility(GONE);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(8955)) {
                    author.setText(media.getAuthor());
                }
            }
        }
    }

    /**
     * Gets new categories from the WikiText and updates it on the UI
     *
     * @param s WikiText
     */
    private void updateCategoryList(final String s) {
        final List<String> allCategories = new ArrayList<String>();
        int i = s.indexOf("[[Category:");
        if (!ListenerUtil.mutListener.listen(8970)) {
            {
                long _loopCounter147 = 0;
                while ((ListenerUtil.mutListener.listen(8969) ? (i >= -1) : (ListenerUtil.mutListener.listen(8968) ? (i <= -1) : (ListenerUtil.mutListener.listen(8967) ? (i > -1) : (ListenerUtil.mutListener.listen(8966) ? (i < -1) : (ListenerUtil.mutListener.listen(8965) ? (i == -1) : (i != -1))))))) {
                    ListenerUtil.loopListener.listen("_loopCounter147", ++_loopCounter147);
                    final String category = s.substring((ListenerUtil.mutListener.listen(8961) ? (i % 11) : (ListenerUtil.mutListener.listen(8960) ? (i / 11) : (ListenerUtil.mutListener.listen(8959) ? (i * 11) : (ListenerUtil.mutListener.listen(8958) ? (i - 11) : (i + 11))))), s.indexOf("]]", i));
                    if (!ListenerUtil.mutListener.listen(8962)) {
                        allCategories.add(category);
                    }
                    if (!ListenerUtil.mutListener.listen(8963)) {
                        i = s.indexOf("]]", i);
                    }
                    if (!ListenerUtil.mutListener.listen(8964)) {
                        i = s.indexOf("[[Category:", i);
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(8971)) {
            media.setCategories(allCategories);
        }
        if (!ListenerUtil.mutListener.listen(8973)) {
            if (allCategories.isEmpty()) {
                if (!ListenerUtil.mutListener.listen(8972)) {
                    // Stick in a filler element.
                    allCategories.add(getString(R.string.detail_panel_cats_none));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(8974)) {
            categoryEditButton.setVisibility(VISIBLE);
        }
        if (!ListenerUtil.mutListener.listen(8975)) {
            rebuildCatList(allCategories);
        }
    }

    /**
     * Updates the categories
     */
    public void updateCategories() {
        List<String> allCategories = new ArrayList<String>(media.getAddedCategories());
        if (!ListenerUtil.mutListener.listen(8976)) {
            media.setCategories(allCategories);
        }
        if (!ListenerUtil.mutListener.listen(8978)) {
            if (allCategories.isEmpty()) {
                if (!ListenerUtil.mutListener.listen(8977)) {
                    // Stick in a filler element.
                    allCategories.add(getString(R.string.detail_panel_cats_none));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(8979)) {
            rebuildCatList(allCategories);
        }
    }

    /**
     * Populates media details fragment with depiction list
     * @param idAndCaptions
     */
    private void buildDepictionList(List<IdAndCaptions> idAndCaptions) {
        if (!ListenerUtil.mutListener.listen(8980)) {
            depictionContainer.removeAllViews();
        }
        String locale = Locale.getDefault().getLanguage();
        if (!ListenerUtil.mutListener.listen(8982)) {
            {
                long _loopCounter148 = 0;
                for (IdAndCaptions idAndCaption : idAndCaptions) {
                    ListenerUtil.loopListener.listen("_loopCounter148", ++_loopCounter148);
                    if (!ListenerUtil.mutListener.listen(8981)) {
                        depictionContainer.addView(buildDepictLabel(getDepictionCaption(idAndCaption, locale), idAndCaption.getId(), depictionContainer));
                    }
                }
            }
        }
    }

    private String getDepictionCaption(IdAndCaptions idAndCaption, String locale) {
        if (!ListenerUtil.mutListener.listen(8983)) {
            // Check if the Depiction Caption is available in user's locale if not then check for english, else show any available.
            if (idAndCaption.getCaptions().get(locale) != null) {
                return idAndCaption.getCaptions().get(locale);
            }
        }
        if (!ListenerUtil.mutListener.listen(8984)) {
            if (idAndCaption.getCaptions().get("en") != null) {
                return idAndCaption.getCaptions().get("en");
            }
        }
        return idAndCaption.getCaptions().values().iterator().next();
    }

    @OnClick(R.id.mediaDetailLicense)
    public void onMediaDetailLicenceClicked() {
        String url = media.getLicenseUrl();
        if (!ListenerUtil.mutListener.listen(8988)) {
            if ((ListenerUtil.mutListener.listen(8985) ? (!StringUtils.isBlank(url) || getActivity() != null) : (!StringUtils.isBlank(url) && getActivity() != null))) {
                if (!ListenerUtil.mutListener.listen(8987)) {
                    Utils.handleWebUrl(getActivity(), Uri.parse(url));
                }
            } else {
                if (!ListenerUtil.mutListener.listen(8986)) {
                    viewUtil.showShortToast(getActivity(), getString(R.string.null_url));
                }
            }
        }
    }

    @OnClick(R.id.mediaDetailCoordinates)
    public void onMediaDetailCoordinatesClicked() {
        if (!ListenerUtil.mutListener.listen(8991)) {
            if ((ListenerUtil.mutListener.listen(8989) ? (media.getCoordinates() != null || getActivity() != null) : (media.getCoordinates() != null && getActivity() != null))) {
                if (!ListenerUtil.mutListener.listen(8990)) {
                    Utils.handleGeoCoordinates(getActivity(), media.getCoordinates());
                }
            }
        }
    }

    @OnClick(R.id.copyWikicode)
    public void onCopyWikicodeClicked() {
        String data = "[[" + media.getFilename() + "|thumb|" + media.getFallbackDescription() + "]]";
        if (!ListenerUtil.mutListener.listen(8992)) {
            Utils.copy("wikiCode", data, getContext());
        }
        if (!ListenerUtil.mutListener.listen(8993)) {
            Timber.d("Generated wikidata copy code: %s", data);
        }
        if (!ListenerUtil.mutListener.listen(8994)) {
            Toast.makeText(getContext(), getString(R.string.wikicode_copied), Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Sends thanks to author if the author is not the user
     */
    @OnClick(R.id.sendThanks)
    public void sendThanksToAuthor() {
        String fileName = media.getFilename();
        if (!ListenerUtil.mutListener.listen(8996)) {
            if (TextUtils.isEmpty(fileName)) {
                if (!ListenerUtil.mutListener.listen(8995)) {
                    Toast.makeText(getContext(), getString(R.string.error_sending_thanks), Toast.LENGTH_SHORT).show();
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(8997)) {
            compositeDisposable.add(reviewHelper.getFirstRevisionOfFile(fileName).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(revision -> sendThanks(getContext(), revision)));
        }
    }

    /**
     * Api call for sending thanks to the author when the author is not the user
     * and display toast depending on the result
     * @param context context
     * @param firstRevision the revision id of the image
     */
    @SuppressLint({ "CheckResult", "StringFormatInvalid" })
    void sendThanks(Context context, MwQueryPage.Revision firstRevision) {
        if (!ListenerUtil.mutListener.listen(8998)) {
            ViewUtil.showShortToast(context, context.getString(R.string.send_thank_toast, media.getDisplayTitle()));
        }
        if (!ListenerUtil.mutListener.listen(8999)) {
            if (firstRevision == null) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(9000)) {
            Observable.defer((Callable<ObservableSource<Boolean>>) () -> thanksClient.thank(firstRevision.getRevisionId())).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe((result) -> {
                displayThanksToast(context, result);
            }, Timber::e);
        }
    }

    /**
     * Method to display toast when api call to thank the author is completed
     * @param context context
     * @param result true if success, false otherwise
     */
    @SuppressLint("StringFormatInvalid")
    private void displayThanksToast(final Context context, final boolean result) {
        final String message;
        final String title;
        if (result) {
            title = context.getString(R.string.send_thank_success_title);
            message = context.getString(R.string.send_thank_success_message, media.getDisplayTitle());
        } else {
            title = context.getString(R.string.send_thank_failure_title);
            message = context.getString(R.string.send_thank_failure_message, media.getDisplayTitle());
        }
        if (!ListenerUtil.mutListener.listen(9001)) {
            ViewUtil.showShortToast(context, message);
        }
    }

    @OnClick(R.id.categoryEditButton)
    public void onCategoryEditButtonClicked() {
        if (!ListenerUtil.mutListener.listen(9002)) {
            progressBarEditCategory.setVisibility(VISIBLE);
        }
        if (!ListenerUtil.mutListener.listen(9003)) {
            categoryEditButton.setVisibility(GONE);
        }
        if (!ListenerUtil.mutListener.listen(9004)) {
            getWikiText();
        }
    }

    /**
     * Gets WikiText from the server and send it to catgory editor
     */
    private void getWikiText() {
        if (!ListenerUtil.mutListener.listen(9005)) {
            compositeDisposable.add(mediaDataExtractor.getCurrentWikiText(Objects.requireNonNull(media.getFilename())).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(this::gotoCategoryEditor, Timber::e));
        }
    }

    /**
     * Opens the category editor
     *
     * @param s WikiText
     */
    private void gotoCategoryEditor(final String s) {
        if (!ListenerUtil.mutListener.listen(9006)) {
            categoryEditButton.setVisibility(VISIBLE);
        }
        if (!ListenerUtil.mutListener.listen(9007)) {
            progressBarEditCategory.setVisibility(GONE);
        }
        final Fragment categoriesFragment = new UploadCategoriesFragment();
        final Bundle bundle = new Bundle();
        if (!ListenerUtil.mutListener.listen(9008)) {
            bundle.putParcelable("Existing_Categories", media);
        }
        if (!ListenerUtil.mutListener.listen(9009)) {
            bundle.putString("WikiText", s);
        }
        if (!ListenerUtil.mutListener.listen(9010)) {
            categoriesFragment.setArguments(bundle);
        }
        final FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        if (!ListenerUtil.mutListener.listen(9011)) {
            transaction.replace(R.id.mediaDetailFrameLayout, categoriesFragment);
        }
        if (!ListenerUtil.mutListener.listen(9012)) {
            transaction.addToBackStack(null);
        }
        if (!ListenerUtil.mutListener.listen(9013)) {
            transaction.commit();
        }
    }

    @OnClick(R.id.coordinate_edit)
    public void onUpdateCoordinatesClicked() {
        if (!ListenerUtil.mutListener.listen(9014)) {
            goToLocationPickerActivity();
        }
    }

    /**
     * Start location picker activity with a request code and get the coordinates from the activity.
     */
    private void goToLocationPickerActivity() {
        /*
        If location is not provided in media this coordinates will act as a placeholder in
        location picker activity
         */
        double defaultLatitude = 37.773972;
        double defaultLongitude = -122.431297;
        if (!ListenerUtil.mutListener.listen(9022)) {
            if (media.getCoordinates() != null) {
                if (!ListenerUtil.mutListener.listen(9020)) {
                    defaultLatitude = media.getCoordinates().getLatitude();
                }
                if (!ListenerUtil.mutListener.listen(9021)) {
                    defaultLongitude = media.getCoordinates().getLongitude();
                }
            } else {
                if (!ListenerUtil.mutListener.listen(9019)) {
                    if (locationManager.getLastLocation() != null) {
                        if (!ListenerUtil.mutListener.listen(9017)) {
                            defaultLatitude = locationManager.getLastLocation().getLatitude();
                        }
                        if (!ListenerUtil.mutListener.listen(9018)) {
                            defaultLongitude = locationManager.getLastLocation().getLongitude();
                        }
                    } else {
                        String[] lastLocation = applicationKvStore.getString(LAST_LOCATION, (defaultLatitude + "," + defaultLongitude)).split(",");
                        if (!ListenerUtil.mutListener.listen(9015)) {
                            defaultLatitude = Double.parseDouble(lastLocation[0]);
                        }
                        if (!ListenerUtil.mutListener.listen(9016)) {
                            defaultLongitude = Double.parseDouble(lastLocation[1]);
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(9023)) {
            startActivityForResult(new LocationPicker.IntentBuilder().defaultLocation(new CameraPosition.Builder().target(new LatLng(defaultLatitude, defaultLongitude)).zoom(16).build()).activityKey("MediaActivity").build(getActivity()), REQUEST_CODE);
        }
    }

    @OnClick(R.id.description_edit)
    public void onDescriptionEditClicked() {
        if (!ListenerUtil.mutListener.listen(9024)) {
            progressBarEditDescription.setVisibility(VISIBLE);
        }
        if (!ListenerUtil.mutListener.listen(9025)) {
            editDescription.setVisibility(GONE);
        }
        if (!ListenerUtil.mutListener.listen(9026)) {
            getDescriptionList();
        }
    }

    /**
     * Gets descriptions from wikitext
     */
    private void getDescriptionList() {
        if (!ListenerUtil.mutListener.listen(9027)) {
            compositeDisposable.add(mediaDataExtractor.getCurrentWikiText(Objects.requireNonNull(media.getFilename())).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(this::extractCaptionDescription, Timber::e));
        }
    }

    /**
     * Gets captions and descriptions and merge them according to language code and arranges it in a
     * single list.
     * Send the list to DescriptionEditActivity
     * @param s wikitext
     */
    private void extractCaptionDescription(final String s) {
        final LinkedHashMap<String, String> descriptions = getDescriptions(s);
        final LinkedHashMap<String, String> captions = getCaptionsList();
        final ArrayList<UploadMediaDetail> descriptionAndCaptions = new ArrayList<>();
        if (!ListenerUtil.mutListener.listen(9047)) {
            if ((ListenerUtil.mutListener.listen(9032) ? (captions.size() <= descriptions.size()) : (ListenerUtil.mutListener.listen(9031) ? (captions.size() > descriptions.size()) : (ListenerUtil.mutListener.listen(9030) ? (captions.size() < descriptions.size()) : (ListenerUtil.mutListener.listen(9029) ? (captions.size() != descriptions.size()) : (ListenerUtil.mutListener.listen(9028) ? (captions.size() == descriptions.size()) : (captions.size() >= descriptions.size()))))))) {
                if (!ListenerUtil.mutListener.listen(9043)) {
                    {
                        long _loopCounter151 = 0;
                        for (final Map.Entry mapElement : captions.entrySet()) {
                            ListenerUtil.loopListener.listen("_loopCounter151", ++_loopCounter151);
                            final String language = (String) mapElement.getKey();
                            if (!ListenerUtil.mutListener.listen(9042)) {
                                if (descriptions.containsKey(language)) {
                                    if (!ListenerUtil.mutListener.listen(9041)) {
                                        descriptionAndCaptions.add(new UploadMediaDetail(language, Objects.requireNonNull(descriptions.get(language)), (String) mapElement.getValue()));
                                    }
                                } else {
                                    if (!ListenerUtil.mutListener.listen(9040)) {
                                        descriptionAndCaptions.add(new UploadMediaDetail(language, "", (String) mapElement.getValue()));
                                    }
                                }
                            }
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(9046)) {
                    {
                        long _loopCounter152 = 0;
                        for (final Map.Entry mapElement : descriptions.entrySet()) {
                            ListenerUtil.loopListener.listen("_loopCounter152", ++_loopCounter152);
                            final String language = (String) mapElement.getKey();
                            if (!ListenerUtil.mutListener.listen(9045)) {
                                if (!captions.containsKey(language)) {
                                    if (!ListenerUtil.mutListener.listen(9044)) {
                                        descriptionAndCaptions.add(new UploadMediaDetail(language, Objects.requireNonNull(descriptions.get(language)), ""));
                                    }
                                }
                            }
                        }
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(9036)) {
                    {
                        long _loopCounter149 = 0;
                        for (final Map.Entry mapElement : descriptions.entrySet()) {
                            ListenerUtil.loopListener.listen("_loopCounter149", ++_loopCounter149);
                            final String language = (String) mapElement.getKey();
                            if (!ListenerUtil.mutListener.listen(9035)) {
                                if (captions.containsKey(language)) {
                                    if (!ListenerUtil.mutListener.listen(9034)) {
                                        descriptionAndCaptions.add(new UploadMediaDetail(language, (String) mapElement.getValue(), Objects.requireNonNull(captions.get(language))));
                                    }
                                } else {
                                    if (!ListenerUtil.mutListener.listen(9033)) {
                                        descriptionAndCaptions.add(new UploadMediaDetail(language, (String) mapElement.getValue(), ""));
                                    }
                                }
                            }
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(9039)) {
                    {
                        long _loopCounter150 = 0;
                        for (final Map.Entry mapElement : captions.entrySet()) {
                            ListenerUtil.loopListener.listen("_loopCounter150", ++_loopCounter150);
                            final String language = (String) mapElement.getKey();
                            if (!ListenerUtil.mutListener.listen(9038)) {
                                if (!descriptions.containsKey(language)) {
                                    if (!ListenerUtil.mutListener.listen(9037)) {
                                        descriptionAndCaptions.add(new UploadMediaDetail(language, "", Objects.requireNonNull(descriptions.get(language))));
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        final Intent intent = new Intent(requireContext(), DescriptionEditActivity.class);
        final Bundle bundle = new Bundle();
        if (!ListenerUtil.mutListener.listen(9048)) {
            bundle.putParcelableArrayList(LIST_OF_DESCRIPTION_AND_CAPTION, descriptionAndCaptions);
        }
        if (!ListenerUtil.mutListener.listen(9049)) {
            bundle.putString(WIKITEXT, s);
        }
        if (!ListenerUtil.mutListener.listen(9050)) {
            bundle.putString(Prefs.DESCRIPTION_LANGUAGE, applicationKvStore.getString(Prefs.DESCRIPTION_LANGUAGE, ""));
        }
        if (!ListenerUtil.mutListener.listen(9051)) {
            intent.putExtras(bundle);
        }
        if (!ListenerUtil.mutListener.listen(9052)) {
            startActivityForResult(intent, REQUEST_CODE_EDIT_DESCRIPTION);
        }
    }

    /**
     * Filters descriptions from current wikiText and arranges it in LinkedHashmap according to the
     * language code
     * @param s wikitext
     * @return LinkedHashMap<LanguageCode,Description>
     */
    private LinkedHashMap<String, String> getDescriptions(String s) {
        final Pattern pattern = Pattern.compile("[dD]escription *=(.*?)\n *\\|", Pattern.DOTALL);
        final Matcher matcher = pattern.matcher(s);
        String description = null;
        if (!ListenerUtil.mutListener.listen(9054)) {
            if (matcher.find()) {
                if (!ListenerUtil.mutListener.listen(9053)) {
                    description = matcher.group();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(9055)) {
            if (description == null) {
                return new LinkedHashMap<>();
            }
        }
        final LinkedHashMap<String, String> descriptionList = new LinkedHashMap<>();
        // number of "{{"
        int count = 0;
        int startCode = 0;
        int endCode = 0;
        int startDescription = 0;
        int endDescription = 0;
        final HashSet<String> allLanguageCodes = new HashSet<>(Arrays.asList("en", "es", "de", "ja", "fr", "ru", "pt", "it", "zh-hans", "zh-hant", "ar", "ko", "id", "pl", "nl", "fa", "hi", "th", "vi", "sv", "uk", "cs", "simple", "hu", "ro", "fi", "el", "he", "nb", "da", "sr", "hr", "ms", "bg", "ca", "tr", "sk", "sh", "bn", "tl", "mr", "ta", "kk", "lt", "az", "bs", "sl", "sq", "arz", "zh-yue", "ka", "te", "et", "lv", "ml", "hy", "uz", "kn", "af", "nn", "mk", "gl", "sw", "eu", "ur", "ky", "gu", "bh", "sco", "ast", "is", "mn", "be", "an", "km", "si", "ceb", "jv", "eo", "als", "ig", "su", "be-x-old", "la", "my", "cy", "ne", "bar", "azb", "mzn", "as", "am", "so", "pa", "map-bms", "scn", "tg", "ckb", "ga", "lb", "war", "zh-min-nan", "nds", "fy", "vec", "pnb", "zh-classical", "lmo", "tt", "io", "ia", "br", "hif", "mg", "wuu", "gan", "ang", "or", "oc", "yi", "ps", "tk", "ba", "sah", "fo", "nap", "vls", "sa", "ce", "qu", "ku", "min", "bcl", "ilo", "ht", "li", "wa", "vo", "nds-nl", "pam", "new", "mai", "sn", "pms", "eml", "yo", "ha", "gn", "frr", "gd", "hsb", "cv", "lo", "os", "se", "cdo", "sd", "ksh", "bat-smg", "bo", "nah", "xmf", "ace", "roa-tara", "hak", "bjn", "gv", "mt", "pfl", "szl", "bpy", "rue", "co", "diq", "sc", "rw", "vep", "lij", "kw", "fur", "pcd", "lad", "tpi", "ext", "csb", "rm", "kab", "gom", "udm", "mhr", "glk", "za", "pdc", "om", "iu", "nv", "mi", "nrm", "tcy", "frp", "myv", "kbp", "dsb", "zu", "ln", "mwl", "fiu-vro", "tum", "tet", "tn", "pnt", "stq", "nov", "ny", "xh", "crh", "lfn", "st", "pap", "ay", "zea", "bxr", "kl", "sm", "ak", "ve", "pag", "nso", "kaa", "lez", "gag", "kv", "bm", "to", "lbe", "krc", "jam", "ss", "roa-rup", "dv", "ie", "av", "cbk-zam", "chy", "inh", "ug", "ch", "arc", "pih", "mrj", "kg", "rmy", "dty", "na", "ts", "xal", "wo", "fj", "tyv", "olo", "ltg", "ff", "jbo", "haw", "ki", "chr", "sg", "atj", "sat", "ady", "ty", "lrc", "ti", "din", "gor", "lg", "rn", "bi", "cu", "kbd", "pi", "cr", "koi", "ik", "mdf", "bug", "ee", "shn", "tw", "dz", "srn", "ks", "test", "en-x-piglatin", "ab"));
        if (!ListenerUtil.mutListener.listen(9103)) {
            {
                long _loopCounter153 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(9102) ? (i >= (ListenerUtil.mutListener.listen(9097) ? (description.length() % 1) : (ListenerUtil.mutListener.listen(9096) ? (description.length() / 1) : (ListenerUtil.mutListener.listen(9095) ? (description.length() * 1) : (ListenerUtil.mutListener.listen(9094) ? (description.length() + 1) : (description.length() - 1)))))) : (ListenerUtil.mutListener.listen(9101) ? (i <= (ListenerUtil.mutListener.listen(9097) ? (description.length() % 1) : (ListenerUtil.mutListener.listen(9096) ? (description.length() / 1) : (ListenerUtil.mutListener.listen(9095) ? (description.length() * 1) : (ListenerUtil.mutListener.listen(9094) ? (description.length() + 1) : (description.length() - 1)))))) : (ListenerUtil.mutListener.listen(9100) ? (i > (ListenerUtil.mutListener.listen(9097) ? (description.length() % 1) : (ListenerUtil.mutListener.listen(9096) ? (description.length() / 1) : (ListenerUtil.mutListener.listen(9095) ? (description.length() * 1) : (ListenerUtil.mutListener.listen(9094) ? (description.length() + 1) : (description.length() - 1)))))) : (ListenerUtil.mutListener.listen(9099) ? (i != (ListenerUtil.mutListener.listen(9097) ? (description.length() % 1) : (ListenerUtil.mutListener.listen(9096) ? (description.length() / 1) : (ListenerUtil.mutListener.listen(9095) ? (description.length() * 1) : (ListenerUtil.mutListener.listen(9094) ? (description.length() + 1) : (description.length() - 1)))))) : (ListenerUtil.mutListener.listen(9098) ? (i == (ListenerUtil.mutListener.listen(9097) ? (description.length() % 1) : (ListenerUtil.mutListener.listen(9096) ? (description.length() / 1) : (ListenerUtil.mutListener.listen(9095) ? (description.length() * 1) : (ListenerUtil.mutListener.listen(9094) ? (description.length() + 1) : (description.length() - 1)))))) : (i < (ListenerUtil.mutListener.listen(9097) ? (description.length() % 1) : (ListenerUtil.mutListener.listen(9096) ? (description.length() / 1) : (ListenerUtil.mutListener.listen(9095) ? (description.length() * 1) : (ListenerUtil.mutListener.listen(9094) ? (description.length() + 1) : (description.length() - 1))))))))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter153", ++_loopCounter153);
                    if (!ListenerUtil.mutListener.listen(9093)) {
                        if (description.startsWith("{{", i)) {
                            if (!ListenerUtil.mutListener.listen(9090)) {
                                if ((ListenerUtil.mutListener.listen(9075) ? (count >= 0) : (ListenerUtil.mutListener.listen(9074) ? (count <= 0) : (ListenerUtil.mutListener.listen(9073) ? (count > 0) : (ListenerUtil.mutListener.listen(9072) ? (count < 0) : (ListenerUtil.mutListener.listen(9071) ? (count != 0) : (count == 0))))))) {
                                    if (!ListenerUtil.mutListener.listen(9076)) {
                                        startCode = i;
                                    }
                                    if (!ListenerUtil.mutListener.listen(9077)) {
                                        endCode = description.indexOf("|", i);
                                    }
                                    if (!ListenerUtil.mutListener.listen(9082)) {
                                        startDescription = (ListenerUtil.mutListener.listen(9081) ? (endCode % 1) : (ListenerUtil.mutListener.listen(9080) ? (endCode / 1) : (ListenerUtil.mutListener.listen(9079) ? (endCode * 1) : (ListenerUtil.mutListener.listen(9078) ? (endCode - 1) : (endCode + 1)))));
                                    }
                                    if (!ListenerUtil.mutListener.listen(9089)) {
                                        if (description.startsWith("1=", (ListenerUtil.mutListener.listen(9086) ? (endCode % 1) : (ListenerUtil.mutListener.listen(9085) ? (endCode / 1) : (ListenerUtil.mutListener.listen(9084) ? (endCode * 1) : (ListenerUtil.mutListener.listen(9083) ? (endCode - 1) : (endCode + 1))))))) {
                                            if (!ListenerUtil.mutListener.listen(9087)) {
                                                startDescription += 2;
                                            }
                                            if (!ListenerUtil.mutListener.listen(9088)) {
                                                i += 2;
                                            }
                                        }
                                    }
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(9091)) {
                                i++;
                            }
                            if (!ListenerUtil.mutListener.listen(9092)) {
                                count++;
                            }
                        } else if (description.startsWith("}}", i)) {
                            if (!ListenerUtil.mutListener.listen(9056)) {
                                count--;
                            }
                            if (!ListenerUtil.mutListener.listen(9069)) {
                                if ((ListenerUtil.mutListener.listen(9061) ? (count >= 0) : (ListenerUtil.mutListener.listen(9060) ? (count <= 0) : (ListenerUtil.mutListener.listen(9059) ? (count > 0) : (ListenerUtil.mutListener.listen(9058) ? (count < 0) : (ListenerUtil.mutListener.listen(9057) ? (count != 0) : (count == 0))))))) {
                                    if (!ListenerUtil.mutListener.listen(9062)) {
                                        endDescription = i;
                                    }
                                    final String languageCode = description.substring((ListenerUtil.mutListener.listen(9066) ? (startCode % 2) : (ListenerUtil.mutListener.listen(9065) ? (startCode / 2) : (ListenerUtil.mutListener.listen(9064) ? (startCode * 2) : (ListenerUtil.mutListener.listen(9063) ? (startCode - 2) : (startCode + 2))))), endCode);
                                    final String languageDescription = description.substring(startDescription, endDescription);
                                    if (!ListenerUtil.mutListener.listen(9068)) {
                                        if (allLanguageCodes.contains(languageCode)) {
                                            if (!ListenerUtil.mutListener.listen(9067)) {
                                                descriptionList.put(languageCode, languageDescription);
                                            }
                                        }
                                    }
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(9070)) {
                                i++;
                            }
                        }
                    }
                }
            }
        }
        return descriptionList;
    }

    /**
     * Gets list of caption and arranges it in a LinkedHashmap according to the language code
     * @return LinkedHashMap<LanguageCode,Caption>
     */
    private LinkedHashMap<String, String> getCaptionsList() {
        final LinkedHashMap<String, String> captionList = new LinkedHashMap<>();
        final Map<String, String> captions = media.getCaptions();
        if (!ListenerUtil.mutListener.listen(9105)) {
            {
                long _loopCounter154 = 0;
                for (final Map.Entry<String, String> map : captions.entrySet()) {
                    ListenerUtil.loopListener.listen("_loopCounter154", ++_loopCounter154);
                    final String language = map.getKey();
                    final String languageCaption = map.getValue();
                    if (!ListenerUtil.mutListener.listen(9104)) {
                        captionList.put(language, languageCaption);
                    }
                }
            }
        }
        return captionList;
    }

    /**
     * Get the result from another activity and act accordingly.
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    public void onActivityResult(final int requestCode, final int resultCode, @Nullable final Intent data) {
        if (!ListenerUtil.mutListener.listen(9106)) {
            super.onActivityResult(requestCode, resultCode, data);
        }
        if (!ListenerUtil.mutListener.listen(9147)) {
            if ((ListenerUtil.mutListener.listen(9112) ? ((ListenerUtil.mutListener.listen(9111) ? (requestCode >= REQUEST_CODE) : (ListenerUtil.mutListener.listen(9110) ? (requestCode <= REQUEST_CODE) : (ListenerUtil.mutListener.listen(9109) ? (requestCode > REQUEST_CODE) : (ListenerUtil.mutListener.listen(9108) ? (requestCode < REQUEST_CODE) : (ListenerUtil.mutListener.listen(9107) ? (requestCode != REQUEST_CODE) : (requestCode == REQUEST_CODE)))))) || resultCode == RESULT_OK) : ((ListenerUtil.mutListener.listen(9111) ? (requestCode >= REQUEST_CODE) : (ListenerUtil.mutListener.listen(9110) ? (requestCode <= REQUEST_CODE) : (ListenerUtil.mutListener.listen(9109) ? (requestCode > REQUEST_CODE) : (ListenerUtil.mutListener.listen(9108) ? (requestCode < REQUEST_CODE) : (ListenerUtil.mutListener.listen(9107) ? (requestCode != REQUEST_CODE) : (requestCode == REQUEST_CODE)))))) && resultCode == RESULT_OK))) {
                assert data != null;
                final CameraPosition cameraPosition = LocationPicker.getCameraPosition(data);
                if (!ListenerUtil.mutListener.listen(9146)) {
                    if (cameraPosition != null) {
                        final String latitude = String.valueOf(cameraPosition.target.getLatitude());
                        final String longitude = String.valueOf(cameraPosition.target.getLongitude());
                        final String accuracy = String.valueOf(cameraPosition.target.getAltitude());
                        String currentLatitude = null;
                        String currentLongitude = null;
                        if (!ListenerUtil.mutListener.listen(9141)) {
                            if (media.getCoordinates() != null) {
                                if (!ListenerUtil.mutListener.listen(9139)) {
                                    currentLatitude = String.valueOf(media.getCoordinates().getLatitude());
                                }
                                if (!ListenerUtil.mutListener.listen(9140)) {
                                    currentLongitude = String.valueOf(media.getCoordinates().getLongitude());
                                }
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(9145)) {
                            if ((ListenerUtil.mutListener.listen(9142) ? (!latitude.equals(currentLatitude) && !longitude.equals(currentLongitude)) : (!latitude.equals(currentLatitude) || !longitude.equals(currentLongitude)))) {
                                if (!ListenerUtil.mutListener.listen(9144)) {
                                    updateCoordinates(latitude, longitude, accuracy);
                                }
                            } else if (media.getCoordinates() == null) {
                                if (!ListenerUtil.mutListener.listen(9143)) {
                                    updateCoordinates(latitude, longitude, accuracy);
                                }
                            }
                        }
                    }
                }
            } else if ((ListenerUtil.mutListener.listen(9118) ? ((ListenerUtil.mutListener.listen(9117) ? (requestCode >= REQUEST_CODE_EDIT_DESCRIPTION) : (ListenerUtil.mutListener.listen(9116) ? (requestCode <= REQUEST_CODE_EDIT_DESCRIPTION) : (ListenerUtil.mutListener.listen(9115) ? (requestCode > REQUEST_CODE_EDIT_DESCRIPTION) : (ListenerUtil.mutListener.listen(9114) ? (requestCode < REQUEST_CODE_EDIT_DESCRIPTION) : (ListenerUtil.mutListener.listen(9113) ? (requestCode != REQUEST_CODE_EDIT_DESCRIPTION) : (requestCode == REQUEST_CODE_EDIT_DESCRIPTION)))))) || resultCode == RESULT_OK) : ((ListenerUtil.mutListener.listen(9117) ? (requestCode >= REQUEST_CODE_EDIT_DESCRIPTION) : (ListenerUtil.mutListener.listen(9116) ? (requestCode <= REQUEST_CODE_EDIT_DESCRIPTION) : (ListenerUtil.mutListener.listen(9115) ? (requestCode > REQUEST_CODE_EDIT_DESCRIPTION) : (ListenerUtil.mutListener.listen(9114) ? (requestCode < REQUEST_CODE_EDIT_DESCRIPTION) : (ListenerUtil.mutListener.listen(9113) ? (requestCode != REQUEST_CODE_EDIT_DESCRIPTION) : (requestCode == REQUEST_CODE_EDIT_DESCRIPTION)))))) && resultCode == RESULT_OK))) {
                final String updatedWikiText = data.getStringExtra(UPDATED_WIKITEXT);
                if (!ListenerUtil.mutListener.listen(9134)) {
                    compositeDisposable.add(descriptionEditHelper.addDescription(getContext(), media, updatedWikiText).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(s -> {
                        Timber.d("Descriptions are added.");
                    }));
                }
                final ArrayList<UploadMediaDetail> uploadMediaDetails = data.getParcelableArrayListExtra(LIST_OF_DESCRIPTION_AND_CAPTION);
                LinkedHashMap<String, String> updatedCaptions = new LinkedHashMap<>();
                if (!ListenerUtil.mutListener.listen(9136)) {
                    {
                        long _loopCounter155 = 0;
                        for (UploadMediaDetail mediaDetail : uploadMediaDetails) {
                            ListenerUtil.loopListener.listen("_loopCounter155", ++_loopCounter155);
                            if (!ListenerUtil.mutListener.listen(9135)) {
                                compositeDisposable.add(descriptionEditHelper.addCaption(getContext(), media, mediaDetail.getLanguageCode(), mediaDetail.getCaptionText()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(s -> {
                                    updateCaptions(mediaDetail, updatedCaptions);
                                    Timber.d("Caption is added.");
                                }));
                            }
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(9137)) {
                    progressBarEditDescription.setVisibility(GONE);
                }
                if (!ListenerUtil.mutListener.listen(9138)) {
                    editDescription.setVisibility(VISIBLE);
                }
            } else if ((ListenerUtil.mutListener.listen(9124) ? ((ListenerUtil.mutListener.listen(9123) ? (requestCode >= REQUEST_CODE) : (ListenerUtil.mutListener.listen(9122) ? (requestCode <= REQUEST_CODE) : (ListenerUtil.mutListener.listen(9121) ? (requestCode > REQUEST_CODE) : (ListenerUtil.mutListener.listen(9120) ? (requestCode < REQUEST_CODE) : (ListenerUtil.mutListener.listen(9119) ? (requestCode != REQUEST_CODE) : (requestCode == REQUEST_CODE)))))) || resultCode == RESULT_CANCELED) : ((ListenerUtil.mutListener.listen(9123) ? (requestCode >= REQUEST_CODE) : (ListenerUtil.mutListener.listen(9122) ? (requestCode <= REQUEST_CODE) : (ListenerUtil.mutListener.listen(9121) ? (requestCode > REQUEST_CODE) : (ListenerUtil.mutListener.listen(9120) ? (requestCode < REQUEST_CODE) : (ListenerUtil.mutListener.listen(9119) ? (requestCode != REQUEST_CODE) : (requestCode == REQUEST_CODE)))))) && resultCode == RESULT_CANCELED))) {
                if (!ListenerUtil.mutListener.listen(9133)) {
                    viewUtil.showShortToast(getContext(), requireContext().getString(R.string.coordinates_picking_unsuccessful));
                }
            } else if ((ListenerUtil.mutListener.listen(9130) ? ((ListenerUtil.mutListener.listen(9129) ? (requestCode >= REQUEST_CODE_EDIT_DESCRIPTION) : (ListenerUtil.mutListener.listen(9128) ? (requestCode <= REQUEST_CODE_EDIT_DESCRIPTION) : (ListenerUtil.mutListener.listen(9127) ? (requestCode > REQUEST_CODE_EDIT_DESCRIPTION) : (ListenerUtil.mutListener.listen(9126) ? (requestCode < REQUEST_CODE_EDIT_DESCRIPTION) : (ListenerUtil.mutListener.listen(9125) ? (requestCode != REQUEST_CODE_EDIT_DESCRIPTION) : (requestCode == REQUEST_CODE_EDIT_DESCRIPTION)))))) || resultCode == RESULT_CANCELED) : ((ListenerUtil.mutListener.listen(9129) ? (requestCode >= REQUEST_CODE_EDIT_DESCRIPTION) : (ListenerUtil.mutListener.listen(9128) ? (requestCode <= REQUEST_CODE_EDIT_DESCRIPTION) : (ListenerUtil.mutListener.listen(9127) ? (requestCode > REQUEST_CODE_EDIT_DESCRIPTION) : (ListenerUtil.mutListener.listen(9126) ? (requestCode < REQUEST_CODE_EDIT_DESCRIPTION) : (ListenerUtil.mutListener.listen(9125) ? (requestCode != REQUEST_CODE_EDIT_DESCRIPTION) : (requestCode == REQUEST_CODE_EDIT_DESCRIPTION)))))) && resultCode == RESULT_CANCELED))) {
                if (!ListenerUtil.mutListener.listen(9131)) {
                    progressBarEditDescription.setVisibility(GONE);
                }
                if (!ListenerUtil.mutListener.listen(9132)) {
                    editDescription.setVisibility(VISIBLE);
                }
            }
        }
    }

    /**
     * Adds caption to the map and updates captions
     * @param mediaDetail UploadMediaDetail
     * @param updatedCaptions updated captionds
     */
    private void updateCaptions(UploadMediaDetail mediaDetail, LinkedHashMap<String, String> updatedCaptions) {
        if (!ListenerUtil.mutListener.listen(9148)) {
            updatedCaptions.put(mediaDetail.getLanguageCode(), mediaDetail.getCaptionText());
        }
        if (!ListenerUtil.mutListener.listen(9149)) {
            media.setCaptions(updatedCaptions);
        }
    }

    /**
     * Fetched coordinates are replaced with existing coordinates by a POST API call.
     * @param Latitude to be added
     * @param Longitude to be added
     * @param Accuracy to be added
     */
    public void updateCoordinates(final String Latitude, final String Longitude, final String Accuracy) {
        if (!ListenerUtil.mutListener.listen(9150)) {
            compositeDisposable.add(coordinateEditHelper.makeCoordinatesEdit(getContext(), media, Latitude, Longitude, Accuracy).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(s -> {
                Timber.d("Coordinates are added.");
                coordinates.setText(prettyCoordinates(media));
            }));
        }
    }

    @SuppressLint("StringFormatInvalid")
    @OnClick(R.id.nominateDeletion)
    public void onDeleteButtonClicked() {
        if (!ListenerUtil.mutListener.listen(9165)) {
            if ((ListenerUtil.mutListener.listen(9151) ? (AccountUtil.getUserName(getContext()) != null || AccountUtil.getUserName(getContext()).equals(media.getAuthor())) : (AccountUtil.getUserName(getContext()) != null && AccountUtil.getUserName(getContext()).equals(media.getAuthor())))) {
                final ArrayAdapter<String> languageAdapter = new ArrayAdapter<>(getActivity(), R.layout.simple_spinner_dropdown_list, reasonList);
                final Spinner spinner = new Spinner(getActivity());
                if (!ListenerUtil.mutListener.listen(9160)) {
                    spinner.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                }
                if (!ListenerUtil.mutListener.listen(9161)) {
                    spinner.setAdapter(languageAdapter);
                }
                if (!ListenerUtil.mutListener.listen(9162)) {
                    spinner.setGravity(17);
                }
                AlertDialog dialog = DialogUtil.showAlertDialog(getActivity(), getString(R.string.nominate_delete), null, getString(R.string.about_translate_proceed), getString(R.string.about_translate_cancel), () -> onDeleteClicked(spinner), () -> {
                }, spinner, true);
                if (!ListenerUtil.mutListener.listen(9164)) {
                    if (isDeleted) {
                        if (!ListenerUtil.mutListener.listen(9163)) {
                            dialog.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(false);
                        }
                    }
                }
            } else // enableDeleteButton(true);   makes sense ?
            {
                final EditText input = new EditText(getActivity());
                if (!ListenerUtil.mutListener.listen(9152)) {
                    input.requestFocus();
                }
                AlertDialog d = DialogUtil.showAlertDialog(getActivity(), null, getString(R.string.dialog_box_text_nomination, media.getDisplayTitle()), getString(R.string.ok), getString(R.string.cancel), () -> {
                    String reason = input.getText().toString();
                    onDeleteClickeddialogtext(reason);
                }, () -> {
                }, input, true);
                if (!ListenerUtil.mutListener.listen(9158)) {
                    input.addTextChangedListener(new TextWatcher() {

                        private void handleText() {
                            final Button okButton = d.getButton(AlertDialog.BUTTON_POSITIVE);
                            if (!ListenerUtil.mutListener.listen(9156)) {
                                if ((ListenerUtil.mutListener.listen(9153) ? (input.getText().length() == 0 && isDeleted) : (input.getText().length() == 0 || isDeleted))) {
                                    if (!ListenerUtil.mutListener.listen(9155)) {
                                        okButton.setEnabled(false);
                                    }
                                } else {
                                    if (!ListenerUtil.mutListener.listen(9154)) {
                                        okButton.setEnabled(true);
                                    }
                                }
                            }
                        }

                        @Override
                        public void afterTextChanged(Editable arg0) {
                            if (!ListenerUtil.mutListener.listen(9157)) {
                                handleText();
                            }
                        }

                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                        }
                    });
                }
                if (!ListenerUtil.mutListener.listen(9159)) {
                    d.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(false);
                }
            }
        }
    }

    @SuppressLint("CheckResult")
    private void onDeleteClicked(Spinner spinner) {
        if (!ListenerUtil.mutListener.listen(9166)) {
            applicationKvStore.putBoolean(String.format(NOMINATING_FOR_DELETION_MEDIA, media.getImageUrl()), true);
        }
        if (!ListenerUtil.mutListener.listen(9167)) {
            enableProgressBar();
        }
        String reason = reasonListEnglishMappings.get(spinner.getSelectedItemPosition());
        String finalReason = reason;
        Single<Boolean> resultSingle = reasonBuilder.getReason(media, reason).flatMap(reasonString -> deleteHelper.makeDeletion(getContext(), media, finalReason));
        if (!ListenerUtil.mutListener.listen(9168)) {
            resultSingle.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(s -> {
                if (applicationKvStore.getBoolean(String.format(NOMINATING_FOR_DELETION_MEDIA, media.getImageUrl()), false)) {
                    applicationKvStore.remove(String.format(NOMINATING_FOR_DELETION_MEDIA, media.getImageUrl()));
                    callback.nominatingForDeletion(index);
                }
            });
        }
    }

    @SuppressLint("CheckResult")
    private void onDeleteClickeddialogtext(String reason) {
        if (!ListenerUtil.mutListener.listen(9169)) {
            applicationKvStore.putBoolean(String.format(NOMINATING_FOR_DELETION_MEDIA, media.getImageUrl()), true);
        }
        if (!ListenerUtil.mutListener.listen(9170)) {
            enableProgressBar();
        }
        Single<Boolean> resultSingletext = reasonBuilder.getReason(media, reason).flatMap(reasonString -> deleteHelper.makeDeletion(getContext(), media, reason));
        if (!ListenerUtil.mutListener.listen(9171)) {
            resultSingletext.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(s -> {
                if (applicationKvStore.getBoolean(String.format(NOMINATING_FOR_DELETION_MEDIA, media.getImageUrl()), false)) {
                    applicationKvStore.remove(String.format(NOMINATING_FOR_DELETION_MEDIA, media.getImageUrl()));
                    callback.nominatingForDeletion(index);
                }
            });
        }
    }

    @OnClick(R.id.seeMore)
    public void onSeeMoreClicked() {
        if (!ListenerUtil.mutListener.listen(9174)) {
            if ((ListenerUtil.mutListener.listen(9172) ? (nominatedForDeletion.getVisibility() == VISIBLE || getActivity() != null) : (nominatedForDeletion.getVisibility() == VISIBLE && getActivity() != null))) {
                if (!ListenerUtil.mutListener.listen(9173)) {
                    Utils.handleWebUrl(getActivity(), Uri.parse(media.getPageTitle().getMobileUri()));
                }
            }
        }
    }

    @OnClick(R.id.mediaDetailAuthor)
    public void onAuthorViewClicked() {
        if (!ListenerUtil.mutListener.listen(9176)) {
            if ((ListenerUtil.mutListener.listen(9175) ? (media == null && media.getUser() == null) : (media == null || media.getUser() == null))) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(9178)) {
            if (sessionManager.getUserName() == null) {
                String userProfileLink = BuildConfig.COMMONS_URL + "/wiki/User:" + media.getUser();
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(userProfileLink));
                if (!ListenerUtil.mutListener.listen(9177)) {
                    startActivity(browserIntent);
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(9179)) {
            ProfileActivity.startYourself(getActivity(), media.getUser(), !Objects.equals(sessionManager.getUserName(), media.getUser()));
        }
    }

    /**
     * Enable Progress Bar and Update delete button text.
     */
    private void enableProgressBar() {
        if (!ListenerUtil.mutListener.listen(9180)) {
            progressBarDeletion.setVisibility(VISIBLE);
        }
        if (!ListenerUtil.mutListener.listen(9181)) {
            delete.setText("Nominating for Deletion");
        }
        if (!ListenerUtil.mutListener.listen(9182)) {
            isDeleted = true;
        }
    }

    private void rebuildCatList(List<String> categories) {
        if (!ListenerUtil.mutListener.listen(9183)) {
            categoryContainer.removeAllViews();
        }
        if (!ListenerUtil.mutListener.listen(9185)) {
            {
                long _loopCounter156 = 0;
                for (String category : categories) {
                    ListenerUtil.loopListener.listen("_loopCounter156", ++_loopCounter156);
                    if (!ListenerUtil.mutListener.listen(9184)) {
                        categoryContainer.addView(buildCatLabel(sanitise(category), categoryContainer));
                    }
                }
            }
        }
    }

    // that was meant for alphabetical sorting of the categories and can be safely removed.
    private String sanitise(String category) {
        int indexOfPipe = category.indexOf('|');
        if (!ListenerUtil.mutListener.listen(9191)) {
            if ((ListenerUtil.mutListener.listen(9190) ? (indexOfPipe >= -1) : (ListenerUtil.mutListener.listen(9189) ? (indexOfPipe <= -1) : (ListenerUtil.mutListener.listen(9188) ? (indexOfPipe > -1) : (ListenerUtil.mutListener.listen(9187) ? (indexOfPipe < -1) : (ListenerUtil.mutListener.listen(9186) ? (indexOfPipe == -1) : (indexOfPipe != -1))))))) {
                // Removed everything after '|'
                return category.substring(0, indexOfPipe);
            }
        }
        return category;
    }

    /**
     * Add view to depictions obtained also tapping on depictions should open the url
     */
    private View buildDepictLabel(String depictionName, String entityId, LinearLayout depictionContainer) {
        final View item = LayoutInflater.from(getContext()).inflate(R.layout.detail_category_item, depictionContainer, false);
        final TextView textView = item.findViewById(R.id.mediaDetailCategoryItemText);
        if (!ListenerUtil.mutListener.listen(9192)) {
            textView.setText(depictionName);
        }
        if (!ListenerUtil.mutListener.listen(9193)) {
            item.setOnClickListener(view -> {
                Intent intent = new Intent(getContext(), WikidataItemDetailsActivity.class);
                intent.putExtra("wikidataItemName", depictionName);
                intent.putExtra("entityId", entityId);
                intent.putExtra("fragment", "MediaDetailFragment");
                getContext().startActivity(intent);
            });
        }
        return item;
    }

    private View buildCatLabel(final String catName, ViewGroup categoryContainer) {
        final View item = LayoutInflater.from(getContext()).inflate(R.layout.detail_category_item, categoryContainer, false);
        final TextView textView = item.findViewById(R.id.mediaDetailCategoryItemText);
        if (!ListenerUtil.mutListener.listen(9194)) {
            textView.setText(catName);
        }
        if (!ListenerUtil.mutListener.listen(9196)) {
            if (!getString(R.string.detail_panel_cats_none).equals(catName)) {
                if (!ListenerUtil.mutListener.listen(9195)) {
                    textView.setOnClickListener(view -> {
                        // Open Category Details page
                        Intent intent = new Intent(getContext(), CategoryDetailsActivity.class);
                        intent.putExtra("categoryName", catName);
                        getContext().startActivity(intent);
                    });
                }
            }
        }
        return item;
    }

    /**
     * Returns captions for media details
     *
     * @param media object of class media
     * @return caption as string
     */
    private String prettyCaption(Media media) {
        if (!ListenerUtil.mutListener.listen(9198)) {
            {
                long _loopCounter157 = 0;
                for (String caption : media.getCaptions().values()) {
                    ListenerUtil.loopListener.listen("_loopCounter157", ++_loopCounter157);
                    if (!ListenerUtil.mutListener.listen(9197)) {
                        if (caption.equals("")) {
                            return getString(R.string.detail_caption_empty);
                        } else {
                            return caption;
                        }
                    }
                }
            }
        }
        return getString(R.string.detail_caption_empty);
    }

    private String prettyDescription(Media media) {
        final String description = chooseDescription(media);
        return description.isEmpty() ? getString(R.string.detail_description_empty) : description;
    }

    private String chooseDescription(Media media) {
        final Map<String, String> descriptions = media.getDescriptions();
        final String multilingualDesc = descriptions.get(Locale.getDefault().getLanguage());
        if (!ListenerUtil.mutListener.listen(9199)) {
            if (multilingualDesc != null) {
                return multilingualDesc;
            }
        }
        if (!ListenerUtil.mutListener.listen(9200)) {
            {
                long _loopCounter158 = 0;
                for (String description : descriptions.values()) {
                    ListenerUtil.loopListener.listen("_loopCounter158", ++_loopCounter158);
                    return description;
                }
            }
        }
        return media.getFallbackDescription();
    }

    private String prettyDiscussion(String discussion) {
        return discussion.isEmpty() ? getString(R.string.detail_discussion_empty) : discussion;
    }

    private String prettyLicense(Media media) {
        String licenseKey = media.getLicense();
        if (!ListenerUtil.mutListener.listen(9201)) {
            Timber.d("Media license is: %s", licenseKey);
        }
        if (!ListenerUtil.mutListener.listen(9203)) {
            if ((ListenerUtil.mutListener.listen(9202) ? (licenseKey == null && licenseKey.equals("")) : (licenseKey == null || licenseKey.equals("")))) {
                return getString(R.string.detail_license_empty);
            }
        }
        return licenseKey;
    }

    private String prettyUploadedDate(Media media) {
        Date date = media.getDateUploaded();
        if (!ListenerUtil.mutListener.listen(9206)) {
            if ((ListenerUtil.mutListener.listen(9205) ? ((ListenerUtil.mutListener.listen(9204) ? (date == null && date.toString() == null) : (date == null || date.toString() == null)) && date.toString().isEmpty()) : ((ListenerUtil.mutListener.listen(9204) ? (date == null && date.toString() == null) : (date == null || date.toString() == null)) || date.toString().isEmpty()))) {
                return "Uploaded date not available";
            }
        }
        return DateUtil.getDateStringWithSkeletonPattern(date, "dd MMM yyyy");
    }

    /**
     * Returns the coordinates nicely formatted.
     *
     * @return Coordinates as text.
     */
    private String prettyCoordinates(Media media) {
        if (!ListenerUtil.mutListener.listen(9207)) {
            if (media.getCoordinates() == null) {
                return getString(R.string.media_detail_coordinates_empty);
            }
        }
        return media.getCoordinates().getPrettyCoordinateString();
    }

    @Override
    public boolean updateCategoryDisplay(List<String> categories) {
        if (categories == null) {
            return false;
        } else {
            if (!ListenerUtil.mutListener.listen(9208)) {
                rebuildCatList(categories);
            }
            return true;
        }
    }

    @OnClick(R.id.show_caption_description_textview)
    void showCaptionAndDescription() {
        if (!ListenerUtil.mutListener.listen(9212)) {
            if (showCaptionAndDescriptionContainer.getVisibility() == GONE) {
                if (!ListenerUtil.mutListener.listen(9210)) {
                    showCaptionAndDescriptionContainer.setVisibility(VISIBLE);
                }
                if (!ListenerUtil.mutListener.listen(9211)) {
                    setUpCaptionAndDescriptionLayout();
                }
            } else {
                if (!ListenerUtil.mutListener.listen(9209)) {
                    showCaptionAndDescriptionContainer.setVisibility(GONE);
                }
            }
        }
    }

    /**
     * setUp Caption And Description Layout
     */
    private void setUpCaptionAndDescriptionLayout() {
        List<Caption> captions = getCaptions();
        if (!ListenerUtil.mutListener.listen(9214)) {
            if (descriptionHtmlCode == null) {
                if (!ListenerUtil.mutListener.listen(9213)) {
                    progressBar.setVisibility(VISIBLE);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(9215)) {
            getDescription();
        }
        CaptionListViewAdapter adapter = new CaptionListViewAdapter(captions);
        if (!ListenerUtil.mutListener.listen(9216)) {
            captionsListView.setAdapter(adapter);
        }
    }

    /**
     * Generate the caption with language
     */
    private List<Caption> getCaptions() {
        List<Caption> captionList = new ArrayList<>();
        Map<String, String> captions = media.getCaptions();
        AppLanguageLookUpTable appLanguageLookUpTable = new AppLanguageLookUpTable(getContext());
        if (!ListenerUtil.mutListener.listen(9218)) {
            {
                long _loopCounter159 = 0;
                for (Map.Entry<String, String> map : captions.entrySet()) {
                    ListenerUtil.loopListener.listen("_loopCounter159", ++_loopCounter159);
                    String language = appLanguageLookUpTable.getLocalizedName(map.getKey());
                    String languageCaption = map.getValue();
                    if (!ListenerUtil.mutListener.listen(9217)) {
                        captionList.add(new Caption(language, languageCaption));
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(9220)) {
            if (captionList.size() == 0) {
                if (!ListenerUtil.mutListener.listen(9219)) {
                    captionList.add(new Caption("", "No Caption"));
                }
            }
        }
        return captionList;
    }

    private void getDescription() {
        if (!ListenerUtil.mutListener.listen(9221)) {
            compositeDisposable.add(mediaDataExtractor.getHtmlOfPage(media.getFilename()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(this::extractDescription, Timber::e));
        }
    }

    /**
     * extract the description from html of imagepage
     */
    private void extractDescription(String s) {
        String descriptionClassName = "<td class=\"description\">";
        int start = (ListenerUtil.mutListener.listen(9225) ? (s.indexOf(descriptionClassName) % descriptionClassName.length()) : (ListenerUtil.mutListener.listen(9224) ? (s.indexOf(descriptionClassName) / descriptionClassName.length()) : (ListenerUtil.mutListener.listen(9223) ? (s.indexOf(descriptionClassName) * descriptionClassName.length()) : (ListenerUtil.mutListener.listen(9222) ? (s.indexOf(descriptionClassName) - descriptionClassName.length()) : (s.indexOf(descriptionClassName) + descriptionClassName.length())))));
        int end = s.indexOf("</td>", start);
        if (!ListenerUtil.mutListener.listen(9226)) {
            descriptionHtmlCode = "";
        }
        if (!ListenerUtil.mutListener.listen(9233)) {
            {
                long _loopCounter160 = 0;
                for (int i = start; (ListenerUtil.mutListener.listen(9232) ? (i >= end) : (ListenerUtil.mutListener.listen(9231) ? (i <= end) : (ListenerUtil.mutListener.listen(9230) ? (i > end) : (ListenerUtil.mutListener.listen(9229) ? (i != end) : (ListenerUtil.mutListener.listen(9228) ? (i == end) : (i < end)))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter160", ++_loopCounter160);
                    if (!ListenerUtil.mutListener.listen(9227)) {
                        descriptionHtmlCode = descriptionHtmlCode + s.toCharArray()[i];
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(9234)) {
            descriptionWebView.loadDataWithBaseURL(null, descriptionHtmlCode, "text/html", "utf-8", null);
        }
        if (!ListenerUtil.mutListener.listen(9235)) {
            progressBar.setVisibility(GONE);
        }
    }

    /**
     * Handle back event when fragment when showCaptionAndDescriptionContainer is visible
     */
    private void handleBackEvent(View view) {
        if (!ListenerUtil.mutListener.listen(9236)) {
            view.setFocusableInTouchMode(true);
        }
        if (!ListenerUtil.mutListener.listen(9237)) {
            view.requestFocus();
        }
        if (!ListenerUtil.mutListener.listen(9241)) {
            view.setOnKeyListener(new OnKeyListener() {

                @Override
                public boolean onKey(View view, int keycode, KeyEvent keyEvent) {
                    if (!ListenerUtil.mutListener.listen(9240)) {
                        if (keycode == KeyEvent.KEYCODE_BACK) {
                            if (!ListenerUtil.mutListener.listen(9239)) {
                                if (showCaptionAndDescriptionContainer.getVisibility() == VISIBLE) {
                                    if (!ListenerUtil.mutListener.listen(9238)) {
                                        showCaptionAndDescriptionContainer.setVisibility(GONE);
                                    }
                                    return true;
                                }
                            }
                        }
                    }
                    return false;
                }
            });
        }
    }

    public interface Callback {

        void nominatingForDeletion(int index);
    }

    /**
     * Called when the image background color is changed.
     * You should pass a useable color, not a resource id.
     * @param color
     */
    public void onImageBackgroundChanged(int color) {
        int currentColor = getImageBackgroundColor();
        if (!ListenerUtil.mutListener.listen(9247)) {
            if ((ListenerUtil.mutListener.listen(9246) ? (currentColor >= color) : (ListenerUtil.mutListener.listen(9245) ? (currentColor <= color) : (ListenerUtil.mutListener.listen(9244) ? (currentColor > color) : (ListenerUtil.mutListener.listen(9243) ? (currentColor < color) : (ListenerUtil.mutListener.listen(9242) ? (currentColor != color) : (currentColor == color))))))) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(9248)) {
            image.setBackgroundColor(color);
        }
        if (!ListenerUtil.mutListener.listen(9249)) {
            getImageBackgroundColorPref().edit().putInt(IMAGE_BACKGROUND_COLOR, color).apply();
        }
    }

    private SharedPreferences getImageBackgroundColorPref() {
        return getContext().getSharedPreferences(IMAGE_BACKGROUND_COLOR + media.getPageId(), Context.MODE_PRIVATE);
    }

    private int getImageBackgroundColor() {
        SharedPreferences imageBackgroundColorPref = this.getImageBackgroundColorPref();
        return imageBackgroundColorPref.getInt(IMAGE_BACKGROUND_COLOR, DEFAULT_IMAGE_BACKGROUND_COLOR);
    }
}
