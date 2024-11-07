package org.wordpress.android.ui.posts;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import org.apache.commons.text.StringEscapeUtils;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.wordpress.android.R;
import org.wordpress.android.WordPress;
import org.wordpress.android.analytics.AnalyticsTracker.Stat;
import org.wordpress.android.fluxc.Dispatcher;
import org.wordpress.android.fluxc.action.TaxonomyAction;
import org.wordpress.android.fluxc.generated.SiteActionBuilder;
import org.wordpress.android.fluxc.generated.TaxonomyActionBuilder;
import org.wordpress.android.fluxc.model.MediaModel;
import org.wordpress.android.fluxc.model.PostFormatModel;
import org.wordpress.android.fluxc.model.PostImmutableModel;
import org.wordpress.android.fluxc.model.SiteModel;
import org.wordpress.android.fluxc.model.TermModel;
import org.wordpress.android.fluxc.model.post.PostStatus;
import org.wordpress.android.fluxc.store.MediaStore.OnMediaUploaded;
import org.wordpress.android.fluxc.store.SiteStore;
import org.wordpress.android.fluxc.store.SiteStore.OnPostFormatsChanged;
import org.wordpress.android.fluxc.store.TaxonomyStore;
import org.wordpress.android.fluxc.store.TaxonomyStore.OnTaxonomyChanged;
import org.wordpress.android.ui.RequestCodes;
import org.wordpress.android.ui.photopicker.MediaPickerLauncher;
import org.wordpress.android.ui.posts.EditPostRepository.UpdatePostResult;
import org.wordpress.android.ui.posts.FeaturedImageHelper.FeaturedImageData;
import org.wordpress.android.ui.posts.FeaturedImageHelper.FeaturedImageState;
import org.wordpress.android.ui.posts.FeaturedImageHelper.TrackableEvent;
import org.wordpress.android.ui.posts.PostSettingsListDialogFragment.DialogType;
import org.wordpress.android.ui.posts.PublishSettingsViewModel.PublishUiModel;
import org.wordpress.android.ui.posts.prepublishing.visibility.usecases.UpdatePostStatusUseCase;
import org.wordpress.android.ui.prefs.SiteSettingsInterface;
import org.wordpress.android.ui.prefs.SiteSettingsInterface.SiteSettingsListener;
import org.wordpress.android.ui.utils.UiHelpers;
import org.wordpress.android.util.AccessibilityUtils;
import org.wordpress.android.util.AppLog;
import org.wordpress.android.util.AppLog.T;
import org.wordpress.android.util.DateTimeUtils;
import org.wordpress.android.util.StringUtils;
import org.wordpress.android.util.analytics.AnalyticsTrackerWrapper;
import org.wordpress.android.util.image.ImageManager;
import org.wordpress.android.util.image.ImageManager.RequestListener;
import org.wordpress.android.util.image.ImageType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import javax.inject.Inject;
import static android.app.Activity.RESULT_OK;
import static org.wordpress.android.ui.posts.EditPostActivity.EXTRA_POST_LOCAL_ID;
import static org.wordpress.android.ui.posts.SelectCategoriesActivity.KEY_SELECTED_CATEGORY_IDS;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class EditPostSettingsFragment extends Fragment {

    private static final String POST_FORMAT_STANDARD_KEY = "standard";

    private static final int ACTIVITY_REQUEST_CODE_SELECT_CATEGORIES = 5;

    private static final int ACTIVITY_REQUEST_CODE_SELECT_TAGS = 6;

    private static final int CHOOSE_FEATURED_IMAGE_MENU_ID = 100;

    private static final int REMOVE_FEATURED_IMAGE_MENU_ID = 101;

    private static final int REMOVE_FEATURED_IMAGE_UPLOAD_MENU_ID = 102;

    private static final int RETRY_FEATURED_IMAGE_UPLOAD_MENU_ID = 103;

    private SiteSettingsInterface mSiteSettings;

    private LinearLayout mCategoriesContainer;

    private LinearLayout mExcerptContainer;

    private LinearLayout mFormatContainer;

    private LinearLayout mTagsContainer;

    private LinearLayout mPublishDateContainer;

    private TextView mExcerptTextView;

    private TextView mSlugTextView;

    private TextView mCategoriesTextView;

    private TextView mTagsTextView;

    private TextView mStatusTextView;

    private TextView mPostFormatTextView;

    private TextView mPasswordTextView;

    private TextView mPublishDateTextView;

    private TextView mPublishDateTitleTextView;

    private TextView mCategoriesTagsHeaderTextView;

    private TextView mFeaturedImageHeaderTextView;

    private TextView mMoreOptionsHeaderTextView;

    private TextView mPublishHeaderTextView;

    private ImageView mFeaturedImageView;

    private ImageView mLocalFeaturedImageView;

    private Button mFeaturedImageButton;

    private SwitchCompat mStickySwitch;

    private ViewGroup mFeaturedImageRetryOverlay;

    private ViewGroup mFeaturedImageProgressOverlay;

    private ArrayList<String> mDefaultPostFormatKeys;

    private ArrayList<String> mDefaultPostFormatNames;

    private ArrayList<String> mPostFormatKeys;

    private ArrayList<String> mPostFormatNames;

    @Inject
    SiteStore mSiteStore;

    @Inject
    TaxonomyStore mTaxonomyStore;

    @Inject
    Dispatcher mDispatcher;

    @Inject
    ImageManager mImageManager;

    @Inject
    FeaturedImageHelper mFeaturedImageHelper;

    @Inject
    UiHelpers mUiHelpers;

    @Inject
    PostSettingsUtils mPostSettingsUtils;

    @Inject
    AnalyticsTrackerWrapper mAnalyticsTrackerWrapper;

    @Inject
    UpdatePostStatusUseCase mUpdatePostStatusUseCase;

    @Inject
    MediaPickerLauncher mMediaPickerLauncher;

    @Inject
    UpdateFeaturedImageUseCase mUpdateFeaturedImageUseCase;

    @Inject
    ViewModelProvider.Factory mViewModelFactory;

    private EditPostPublishSettingsViewModel mPublishedViewModel;

    private final OnCheckedChangeListener mOnStickySwitchChangeListener = (buttonView, isChecked) -> onStickySwitchChanged(isChecked);

    public interface EditPostActivityHook {

        EditPostRepository getEditPostRepository();

        SiteModel getSite();
    }

    public static EditPostSettingsFragment newInstance() {
        return new EditPostSettingsFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(12279)) {
            super.onCreate(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(12280)) {
            ((WordPress) getActivity().getApplicationContext()).component().inject(this);
        }
        if (!ListenerUtil.mutListener.listen(12281)) {
            mDispatcher.register(this);
        }
        if (!ListenerUtil.mutListener.listen(12282)) {
            // Will use it later without needing to have access to the Resources.
            mDefaultPostFormatKeys = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.post_format_keys)));
        }
        if (!ListenerUtil.mutListener.listen(12283)) {
            mDefaultPostFormatNames = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.post_format_display_names)));
        }
        if (!ListenerUtil.mutListener.listen(12284)) {
            mPublishedViewModel = new ViewModelProvider(getActivity(), mViewModelFactory).get(EditPostPublishSettingsViewModel.class);
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(12285)) {
            super.onActivityCreated(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(12286)) {
            updatePostFormatKeysAndNames();
        }
        if (!ListenerUtil.mutListener.listen(12287)) {
            fetchSiteSettingsAndUpdateDefaultPostFormatIfNecessary();
        }
        // Update post formats and categories, in case anything changed.
        SiteModel siteModel = getSite();
        if (!ListenerUtil.mutListener.listen(12288)) {
            mDispatcher.dispatch(SiteActionBuilder.newFetchPostFormatsAction(siteModel));
        }
        if (!ListenerUtil.mutListener.listen(12290)) {
            if (!getEditPostRepository().isPage()) {
                if (!ListenerUtil.mutListener.listen(12289)) {
                    mDispatcher.dispatch(TaxonomyActionBuilder.newFetchCategoriesAction(siteModel));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(12291)) {
            refreshViews();
        }
    }

    private void fetchSiteSettingsAndUpdateDefaultPostFormatIfNecessary() {
        if (!ListenerUtil.mutListener.listen(12292)) {
            // A format is already set for the post, no need to fetch the default post format
            if (!TextUtils.isEmpty(getEditPostRepository().getPostFormat())) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(12295)) {
            // we need to fetch site settings in order to get the latest default post format
            mSiteSettings = SiteSettingsInterface.getInterface(getActivity(), getSite(), new SiteSettingsListener() {

                @Override
                public void onSaveError(Exception error) {
                }

                @Override
                public void onFetchError(Exception error) {
                }

                @Override
                public void onSettingsUpdated() {
                    if (!ListenerUtil.mutListener.listen(12294)) {
                        // mEditPostActivityHook will be null if the fragment is detached
                        if (getEditPostActivityHook() != null) {
                            if (!ListenerUtil.mutListener.listen(12293)) {
                                updatePostFormat(mSiteSettings.getDefaultPostFormat());
                            }
                        }
                    }
                }

                @Override
                public void onSettingsSaved() {
                }

                @Override
                public void onCredentialsValidated(Exception error) {
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(12297)) {
            if (mSiteSettings != null) {
                if (!ListenerUtil.mutListener.listen(12296)) {
                    // init will fetch remote settings for us
                    mSiteSettings.init(true);
                }
            }
        }
    }

    @Override
    public void onDestroy() {
        if (!ListenerUtil.mutListener.listen(12299)) {
            if (mSiteSettings != null) {
                if (!ListenerUtil.mutListener.listen(12298)) {
                    mSiteSettings.clear();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(12300)) {
            mDispatcher.unregister(this);
        }
        if (!ListenerUtil.mutListener.listen(12301)) {
            super.onDestroy();
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.edit_post_settings_fragment, container, false);
        if (!ListenerUtil.mutListener.listen(12302)) {
            if (rootView == null) {
                return null;
            }
        }
        if (!ListenerUtil.mutListener.listen(12303)) {
            mExcerptTextView = rootView.findViewById(R.id.post_excerpt);
        }
        if (!ListenerUtil.mutListener.listen(12304)) {
            mSlugTextView = rootView.findViewById(R.id.post_slug);
        }
        if (!ListenerUtil.mutListener.listen(12305)) {
            mCategoriesTextView = rootView.findViewById(R.id.post_categories);
        }
        if (!ListenerUtil.mutListener.listen(12306)) {
            mTagsTextView = rootView.findViewById(R.id.post_tags);
        }
        if (!ListenerUtil.mutListener.listen(12307)) {
            mStatusTextView = rootView.findViewById(R.id.post_status);
        }
        if (!ListenerUtil.mutListener.listen(12308)) {
            mPostFormatTextView = rootView.findViewById(R.id.post_format);
        }
        if (!ListenerUtil.mutListener.listen(12309)) {
            mPasswordTextView = rootView.findViewById(R.id.post_password);
        }
        if (!ListenerUtil.mutListener.listen(12310)) {
            mPublishDateTextView = rootView.findViewById(R.id.publish_date);
        }
        if (!ListenerUtil.mutListener.listen(12311)) {
            mPublishDateTitleTextView = rootView.findViewById(R.id.publish_date_title);
        }
        if (!ListenerUtil.mutListener.listen(12312)) {
            mCategoriesTagsHeaderTextView = rootView.findViewById(R.id.post_settings_categories_and_tags_header);
        }
        if (!ListenerUtil.mutListener.listen(12313)) {
            mMoreOptionsHeaderTextView = rootView.findViewById(R.id.post_settings_more_options_header);
        }
        if (!ListenerUtil.mutListener.listen(12314)) {
            mFeaturedImageHeaderTextView = rootView.findViewById(R.id.post_settings_featured_image_header);
        }
        if (!ListenerUtil.mutListener.listen(12315)) {
            mPublishHeaderTextView = rootView.findViewById(R.id.post_settings_publish);
        }
        if (!ListenerUtil.mutListener.listen(12316)) {
            mPublishDateContainer = rootView.findViewById(R.id.publish_date_container);
        }
        if (!ListenerUtil.mutListener.listen(12317)) {
            mStickySwitch = rootView.findViewById(R.id.post_settings_sticky_switch);
        }
        if (!ListenerUtil.mutListener.listen(12318)) {
            mFeaturedImageView = rootView.findViewById(R.id.post_featured_image);
        }
        if (!ListenerUtil.mutListener.listen(12319)) {
            mLocalFeaturedImageView = rootView.findViewById(R.id.post_featured_image_local);
        }
        if (!ListenerUtil.mutListener.listen(12320)) {
            mFeaturedImageButton = rootView.findViewById(R.id.post_add_featured_image_button);
        }
        if (!ListenerUtil.mutListener.listen(12321)) {
            mFeaturedImageRetryOverlay = rootView.findViewById(R.id.post_featured_image_retry_overlay);
        }
        if (!ListenerUtil.mutListener.listen(12322)) {
            mFeaturedImageProgressOverlay = rootView.findViewById(R.id.post_featured_image_progress_overlay);
        }
        OnClickListener showContextMenuListener = new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if (!ListenerUtil.mutListener.listen(12323)) {
                    view.showContextMenu();
                }
            }
        };
        if (!ListenerUtil.mutListener.listen(12324)) {
            mFeaturedImageView.setOnClickListener(showContextMenuListener);
        }
        if (!ListenerUtil.mutListener.listen(12325)) {
            mLocalFeaturedImageView.setOnClickListener(showContextMenuListener);
        }
        if (!ListenerUtil.mutListener.listen(12326)) {
            mFeaturedImageRetryOverlay.setOnClickListener(showContextMenuListener);
        }
        if (!ListenerUtil.mutListener.listen(12327)) {
            mFeaturedImageProgressOverlay.setOnClickListener(showContextMenuListener);
        }
        if (!ListenerUtil.mutListener.listen(12328)) {
            registerForContextMenu(mFeaturedImageView);
        }
        if (!ListenerUtil.mutListener.listen(12329)) {
            registerForContextMenu(mLocalFeaturedImageView);
        }
        if (!ListenerUtil.mutListener.listen(12330)) {
            registerForContextMenu(mFeaturedImageRetryOverlay);
        }
        if (!ListenerUtil.mutListener.listen(12331)) {
            registerForContextMenu(mFeaturedImageProgressOverlay);
        }
        if (!ListenerUtil.mutListener.listen(12333)) {
            mFeaturedImageButton.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    if (!ListenerUtil.mutListener.listen(12332)) {
                        launchFeaturedMediaPicker();
                    }
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(12334)) {
            mExcerptContainer = rootView.findViewById(R.id.post_excerpt_container);
        }
        if (!ListenerUtil.mutListener.listen(12336)) {
            mExcerptContainer.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    if (!ListenerUtil.mutListener.listen(12335)) {
                        showPostExcerptDialog();
                    }
                }
            });
        }
        final LinearLayout slugContainer = rootView.findViewById(R.id.post_slug_container);
        if (!ListenerUtil.mutListener.listen(12338)) {
            slugContainer.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    if (!ListenerUtil.mutListener.listen(12337)) {
                        showSlugDialog();
                    }
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(12339)) {
            mCategoriesContainer = rootView.findViewById(R.id.post_categories_container);
        }
        if (!ListenerUtil.mutListener.listen(12341)) {
            mCategoriesContainer.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    if (!ListenerUtil.mutListener.listen(12340)) {
                        showCategoriesActivity();
                    }
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(12342)) {
            mTagsContainer = rootView.findViewById(R.id.post_tags_container);
        }
        if (!ListenerUtil.mutListener.listen(12344)) {
            mTagsContainer.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    if (!ListenerUtil.mutListener.listen(12343)) {
                        showTagsActivity();
                    }
                }
            });
        }
        final LinearLayout statusContainer = rootView.findViewById(R.id.post_status_container);
        if (!ListenerUtil.mutListener.listen(12346)) {
            statusContainer.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    if (!ListenerUtil.mutListener.listen(12345)) {
                        showStatusDialog();
                    }
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(12347)) {
            mFormatContainer = rootView.findViewById(R.id.post_format_container);
        }
        if (!ListenerUtil.mutListener.listen(12349)) {
            mFormatContainer.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    if (!ListenerUtil.mutListener.listen(12348)) {
                        showPostFormatDialog();
                    }
                }
            });
        }
        final LinearLayout passwordContainer = rootView.findViewById(R.id.post_password_container);
        if (!ListenerUtil.mutListener.listen(12351)) {
            passwordContainer.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    if (!ListenerUtil.mutListener.listen(12350)) {
                        showPostPasswordDialog();
                    }
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(12354)) {
            mPublishDateContainer.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    FragmentActivity activity = getActivity();
                    if (!ListenerUtil.mutListener.listen(12353)) {
                        if (activity instanceof EditPostSettingsCallback) {
                            if (!ListenerUtil.mutListener.listen(12352)) {
                                ((EditPostSettingsCallback) activity).onEditPostPublishedSettingsClick();
                            }
                        }
                    }
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(12355)) {
            mStickySwitch.setOnCheckedChangeListener(mOnStickySwitchChangeListener);
        }
        if (!ListenerUtil.mutListener.listen(12361)) {
            if ((ListenerUtil.mutListener.listen(12356) ? (getEditPostRepository() != null || getEditPostRepository().isPage()) : (getEditPostRepository() != null && getEditPostRepository().isPage()))) {
                // remove post specific views
                final View categoriesTagsContainer = rootView.findViewById(R.id.post_categories_and_tags_card);
                final View formatBottomSeparator = rootView.findViewById(R.id.post_format_bottom_separator);
                final View markAsStickyContainer = rootView.findViewById(R.id.post_settings_mark_as_sticky_container);
                if (!ListenerUtil.mutListener.listen(12357)) {
                    categoriesTagsContainer.setVisibility(View.GONE);
                }
                if (!ListenerUtil.mutListener.listen(12358)) {
                    formatBottomSeparator.setVisibility(View.GONE);
                }
                if (!ListenerUtil.mutListener.listen(12359)) {
                    mFormatContainer.setVisibility(View.GONE);
                }
                if (!ListenerUtil.mutListener.listen(12360)) {
                    markAsStickyContainer.setVisibility(View.GONE);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(12363)) {
            mPublishedViewModel.getOnUiModel().observe(getViewLifecycleOwner(), new Observer<PublishUiModel>() {

                @Override
                public void onChanged(PublishUiModel uiModel) {
                    if (!ListenerUtil.mutListener.listen(12362)) {
                        updatePublishDateTextView(uiModel.getPublishDateLabel(), Objects.requireNonNull(getEditPostRepository().getPost()));
                    }
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(12365)) {
            mPublishedViewModel.getOnPostStatusChanged().observe(getViewLifecycleOwner(), new Observer<PostStatus>() {

                @Override
                public void onChanged(PostStatus postStatus) {
                    if (!ListenerUtil.mutListener.listen(12364)) {
                        updatePostStatus(postStatus);
                    }
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(12366)) {
            setupSettingHintsForAccessibility();
        }
        if (!ListenerUtil.mutListener.listen(12367)) {
            applyAccessibilityHeadingToSettings();
        }
        return rootView;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        if (!ListenerUtil.mutListener.listen(12372)) {
            if (mFeaturedImageRetryOverlay.getVisibility() == View.VISIBLE) {
                if (!ListenerUtil.mutListener.listen(12370)) {
                    menu.add(0, RETRY_FEATURED_IMAGE_UPLOAD_MENU_ID, 0, getString(R.string.post_settings_retry_featured_image));
                }
                if (!ListenerUtil.mutListener.listen(12371)) {
                    menu.add(0, REMOVE_FEATURED_IMAGE_UPLOAD_MENU_ID, 0, getString(R.string.post_settings_remove_featured_image));
                }
            } else {
                if (!ListenerUtil.mutListener.listen(12368)) {
                    menu.add(0, CHOOSE_FEATURED_IMAGE_MENU_ID, 0, getString(R.string.post_settings_choose_featured_image));
                }
                if (!ListenerUtil.mutListener.listen(12369)) {
                    menu.add(0, REMOVE_FEATURED_IMAGE_MENU_ID, 0, getString(R.string.post_settings_remove_featured_image));
                }
            }
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        SiteModel site = getSite();
        PostImmutableModel post = getEditPostRepository().getPost();
        if ((ListenerUtil.mutListener.listen(12373) ? (site == null && post == null) : (site == null || post == null))) {
            if (!ListenerUtil.mutListener.listen(12374)) {
                AppLog.w(T.POSTS, "Unexpected state: Post or Site is null.");
            }
            return false;
        }
        switch(item.getItemId()) {
            case CHOOSE_FEATURED_IMAGE_MENU_ID:
                if (!ListenerUtil.mutListener.listen(12375)) {
                    mFeaturedImageHelper.cancelFeaturedImageUpload(site, post, false);
                }
                if (!ListenerUtil.mutListener.listen(12376)) {
                    launchFeaturedMediaPicker();
                }
                return true;
            case REMOVE_FEATURED_IMAGE_UPLOAD_MENU_ID:
            case REMOVE_FEATURED_IMAGE_MENU_ID:
                if (!ListenerUtil.mutListener.listen(12377)) {
                    mFeaturedImageHelper.cancelFeaturedImageUpload(site, post, false);
                }
                if (!ListenerUtil.mutListener.listen(12378)) {
                    clearFeaturedImage();
                }
                if (!ListenerUtil.mutListener.listen(12379)) {
                    mFeaturedImageHelper.trackFeaturedImageEvent(TrackableEvent.IMAGE_REMOVE_CLICKED, post.getId());
                }
                return true;
            case RETRY_FEATURED_IMAGE_UPLOAD_MENU_ID:
                if (!ListenerUtil.mutListener.listen(12380)) {
                    retryFeaturedImageUpload(site, post);
                }
                return true;
            default:
                return false;
        }
    }

    private void setupSettingHintsForAccessibility() {
        if (!ListenerUtil.mutListener.listen(12381)) {
            AccessibilityUtils.disableHintAnnouncement(mPublishDateTextView);
        }
        if (!ListenerUtil.mutListener.listen(12382)) {
            AccessibilityUtils.disableHintAnnouncement(mCategoriesTextView);
        }
        if (!ListenerUtil.mutListener.listen(12383)) {
            AccessibilityUtils.disableHintAnnouncement(mTagsTextView);
        }
        if (!ListenerUtil.mutListener.listen(12384)) {
            AccessibilityUtils.disableHintAnnouncement(mPasswordTextView);
        }
        if (!ListenerUtil.mutListener.listen(12385)) {
            AccessibilityUtils.disableHintAnnouncement(mSlugTextView);
        }
        if (!ListenerUtil.mutListener.listen(12386)) {
            AccessibilityUtils.disableHintAnnouncement(mExcerptTextView);
        }
    }

    private void applyAccessibilityHeadingToSettings() {
        if (!ListenerUtil.mutListener.listen(12387)) {
            AccessibilityUtils.enableAccessibilityHeading(mCategoriesTagsHeaderTextView);
        }
        if (!ListenerUtil.mutListener.listen(12388)) {
            AccessibilityUtils.enableAccessibilityHeading(mFeaturedImageHeaderTextView);
        }
        if (!ListenerUtil.mutListener.listen(12389)) {
            AccessibilityUtils.enableAccessibilityHeading(mMoreOptionsHeaderTextView);
        }
        if (!ListenerUtil.mutListener.listen(12390)) {
            AccessibilityUtils.enableAccessibilityHeading(mPublishHeaderTextView);
        }
    }

    private void retryFeaturedImageUpload(@NonNull SiteModel site, @NonNull PostImmutableModel post) {
        MediaModel mediaModel = mFeaturedImageHelper.retryFeaturedImageUpload(site, post);
        if (!ListenerUtil.mutListener.listen(12392)) {
            if (mediaModel == null) {
                if (!ListenerUtil.mutListener.listen(12391)) {
                    clearFeaturedImage();
                }
            }
        }
    }

    public void refreshViews() {
        if (!ListenerUtil.mutListener.listen(12393)) {
            if (!isAdded()) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(12398)) {
            if (getEditPostRepository().isPage()) {
                if (!ListenerUtil.mutListener.listen(12394)) {
                    // remove post specific views
                    mCategoriesContainer.setVisibility(View.GONE);
                }
                if (!ListenerUtil.mutListener.listen(12395)) {
                    mExcerptContainer.setVisibility(View.GONE);
                }
                if (!ListenerUtil.mutListener.listen(12396)) {
                    mFormatContainer.setVisibility(View.GONE);
                }
                if (!ListenerUtil.mutListener.listen(12397)) {
                    mTagsContainer.setVisibility(View.GONE);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(12399)) {
            mExcerptTextView.setText(getEditPostRepository().getExcerpt());
        }
        if (!ListenerUtil.mutListener.listen(12400)) {
            mSlugTextView.setText(getEditPostRepository().getSlug());
        }
        if (!ListenerUtil.mutListener.listen(12401)) {
            mPasswordTextView.setText(getEditPostRepository().getPassword());
        }
        PostImmutableModel postModel = getEditPostRepository().getPost();
        if (!ListenerUtil.mutListener.listen(12402)) {
            updatePostFormatTextView(postModel);
        }
        if (!ListenerUtil.mutListener.listen(12403)) {
            updateTagsTextView(postModel);
        }
        if (!ListenerUtil.mutListener.listen(12404)) {
            updateStatusTextView();
        }
        if (!ListenerUtil.mutListener.listen(12405)) {
            updatePublishDateTextView(postModel);
        }
        if (!ListenerUtil.mutListener.listen(12406)) {
            mPublishedViewModel.start(getEditPostRepository());
        }
        if (!ListenerUtil.mutListener.listen(12407)) {
            updateCategoriesTextView(postModel);
        }
        if (!ListenerUtil.mutListener.listen(12408)) {
            updateFeaturedImageView(postModel);
        }
        if (!ListenerUtil.mutListener.listen(12409)) {
            updateStickySwitch(postModel);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (!ListenerUtil.mutListener.listen(12410)) {
            super.onActivityResult(requestCode, resultCode, data);
        }
        if (!ListenerUtil.mutListener.listen(12421)) {
            if ((ListenerUtil.mutListener.listen(12412) ? (data != null && (((ListenerUtil.mutListener.listen(12411) ? (requestCode == RequestCodes.TAKE_PHOTO && requestCode == RequestCodes.TAKE_VIDEO) : (requestCode == RequestCodes.TAKE_PHOTO || requestCode == RequestCodes.TAKE_VIDEO))))) : (data != null || (((ListenerUtil.mutListener.listen(12411) ? (requestCode == RequestCodes.TAKE_PHOTO && requestCode == RequestCodes.TAKE_VIDEO) : (requestCode == RequestCodes.TAKE_PHOTO || requestCode == RequestCodes.TAKE_VIDEO))))))) {
                Bundle extras;
                switch(requestCode) {
                    case ACTIVITY_REQUEST_CODE_SELECT_CATEGORIES:
                        extras = data.getExtras();
                        if (!ListenerUtil.mutListener.listen(12416)) {
                            if ((ListenerUtil.mutListener.listen(12413) ? (extras != null || extras.containsKey(KEY_SELECTED_CATEGORY_IDS)) : (extras != null && extras.containsKey(KEY_SELECTED_CATEGORY_IDS)))) {
                                @SuppressWarnings("unchecked")
                                List<Long> categoryList = (ArrayList<Long>) extras.getSerializable(KEY_SELECTED_CATEGORY_IDS);
                                if (!ListenerUtil.mutListener.listen(12414)) {
                                    PostAnalyticsUtilsKt.trackPostSettings(mAnalyticsTrackerWrapper, Stat.EDITOR_POST_CATEGORIES_ADDED);
                                }
                                if (!ListenerUtil.mutListener.listen(12415)) {
                                    updateCategories(categoryList);
                                }
                            }
                        }
                        break;
                    case ACTIVITY_REQUEST_CODE_SELECT_TAGS:
                        extras = data.getExtras();
                        if (!ListenerUtil.mutListener.listen(12420)) {
                            if ((ListenerUtil.mutListener.listen(12417) ? (resultCode == RESULT_OK || extras != null) : (resultCode == RESULT_OK && extras != null))) {
                                String selectedTags = extras.getString(PostSettingsTagsActivity.KEY_SELECTED_TAGS);
                                if (!ListenerUtil.mutListener.listen(12418)) {
                                    PostAnalyticsUtilsKt.trackPostSettings(mAnalyticsTrackerWrapper, Stat.EDITOR_POST_TAGS_CHANGED);
                                }
                                if (!ListenerUtil.mutListener.listen(12419)) {
                                    updateTags(selectedTags);
                                }
                            }
                        }
                        break;
                }
            }
        }
    }

    private void showPostExcerptDialog() {
        if (!ListenerUtil.mutListener.listen(12422)) {
            if (!isAdded()) {
                return;
            }
        }
        PostSettingsInputDialogFragment dialog = PostSettingsInputDialogFragment.newInstance(getEditPostRepository().getExcerpt(), getString(R.string.post_settings_excerpt), getString(R.string.post_settings_excerpt_dialog_hint), false);
        if (!ListenerUtil.mutListener.listen(12425)) {
            dialog.setPostSettingsInputDialogListener(new PostSettingsInputDialogFragment.PostSettingsInputDialogListener() {

                @Override
                public void onInputUpdated(String input) {
                    if (!ListenerUtil.mutListener.listen(12423)) {
                        mAnalyticsTrackerWrapper.track(Stat.EDITOR_POST_EXCERPT_CHANGED);
                    }
                    if (!ListenerUtil.mutListener.listen(12424)) {
                        updateExcerpt(input);
                    }
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(12426)) {
            dialog.show(getChildFragmentManager(), null);
        }
    }

    private void showSlugDialog() {
        if (!ListenerUtil.mutListener.listen(12427)) {
            if (!isAdded()) {
                return;
            }
        }
        PostSettingsInputDialogFragment dialog = PostSettingsInputDialogFragment.newInstance(getEditPostRepository().getSlug(), getString(R.string.post_settings_slug), getString(R.string.post_settings_slug_dialog_hint), true);
        if (!ListenerUtil.mutListener.listen(12430)) {
            dialog.setPostSettingsInputDialogListener(new PostSettingsInputDialogFragment.PostSettingsInputDialogListener() {

                @Override
                public void onInputUpdated(String input) {
                    if (!ListenerUtil.mutListener.listen(12428)) {
                        mAnalyticsTrackerWrapper.track(Stat.EDITOR_POST_SLUG_CHANGED);
                    }
                    if (!ListenerUtil.mutListener.listen(12429)) {
                        updateSlug(input);
                    }
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(12431)) {
            dialog.show(getFragmentManager(), null);
        }
    }

    private void showCategoriesActivity() {
        if (!ListenerUtil.mutListener.listen(12432)) {
            if (!isAdded()) {
                return;
            }
        }
        Intent categoriesIntent = new Intent(getActivity(), SelectCategoriesActivity.class);
        if (!ListenerUtil.mutListener.listen(12433)) {
            categoriesIntent.putExtra(WordPress.SITE, getSite());
        }
        if (!ListenerUtil.mutListener.listen(12434)) {
            categoriesIntent.putExtra(EXTRA_POST_LOCAL_ID, getEditPostRepository().getId());
        }
        if (!ListenerUtil.mutListener.listen(12435)) {
            startActivityForResult(categoriesIntent, ACTIVITY_REQUEST_CODE_SELECT_CATEGORIES);
        }
    }

    private void showTagsActivity() {
        if (!ListenerUtil.mutListener.listen(12436)) {
            if (!isAdded()) {
                return;
            }
        }
        // Fetch/refresh the tags in preparation for the PostSettingsTagsActivity
        SiteModel siteModel = getSite();
        if (!ListenerUtil.mutListener.listen(12437)) {
            mDispatcher.dispatch(TaxonomyActionBuilder.newFetchTagsAction(siteModel));
        }
        Intent tagsIntent = new Intent(getActivity(), PostSettingsTagsActivity.class);
        if (!ListenerUtil.mutListener.listen(12438)) {
            tagsIntent.putExtra(WordPress.SITE, siteModel);
        }
        String tags = TextUtils.join(",", getEditPostRepository().getTagNameList());
        if (!ListenerUtil.mutListener.listen(12439)) {
            tagsIntent.putExtra(PostSettingsTagsActivity.KEY_TAGS, tags);
        }
        if (!ListenerUtil.mutListener.listen(12440)) {
            startActivityForResult(tagsIntent, ACTIVITY_REQUEST_CODE_SELECT_TAGS);
        }
    }

    private void onStickySwitchChanged(boolean checked) {
        EditPostRepository editPostRepository = getEditPostRepository();
        if (!ListenerUtil.mutListener.listen(12442)) {
            if (editPostRepository != null) {
                if (!ListenerUtil.mutListener.listen(12441)) {
                    editPostRepository.updateAsync(postModel -> {
                        postModel.setSticky(checked);
                        return true;
                    }, null);
                }
            }
        }
    }

    /*
     * called by the activity when the user taps OK on a PostSettingsDialogFragment
     */
    public void onPostSettingsFragmentPositiveButtonClicked(@NonNull PostSettingsListDialogFragment fragment) {
        int index;
        PostStatus status = null;
        switch(fragment.getDialogType()) {
            case HOMEPAGE_STATUS:
                index = fragment.getCheckedIndex();
                if (!ListenerUtil.mutListener.listen(12443)) {
                    status = getHomepageStatusAtIndex(index);
                }
                break;
            case POST_STATUS:
                index = fragment.getCheckedIndex();
                if (!ListenerUtil.mutListener.listen(12444)) {
                    status = getPostStatusAtIndex(index);
                }
                break;
            case POST_FORMAT:
                String formatName = fragment.getSelectedItem();
                if (!ListenerUtil.mutListener.listen(12445)) {
                    updatePostFormat(getPostFormatKeyFromName(formatName));
                }
                if (!ListenerUtil.mutListener.listen(12446)) {
                    mAnalyticsTrackerWrapper.track(Stat.EDITOR_POST_FORMAT_CHANGED);
                }
                break;
        }
        if (!ListenerUtil.mutListener.listen(12449)) {
            if (status != null) {
                if (!ListenerUtil.mutListener.listen(12447)) {
                    updatePostStatus(status);
                }
                if (!ListenerUtil.mutListener.listen(12448)) {
                    PostAnalyticsUtilsKt.trackPostSettings(mAnalyticsTrackerWrapper, Stat.EDITOR_POST_VISIBILITY_CHANGED);
                }
            }
        }
    }

    private void showStatusDialog() {
        if (!ListenerUtil.mutListener.listen(12450)) {
            if (!isAdded()) {
                return;
            }
        }
        boolean isSiteHomepage = isSiteHomepage();
        int index = isSiteHomepage ? getCurrentHomepageStatusIndex() : getCurrentPostStatusIndex();
        FragmentManager fm = getActivity().getSupportFragmentManager();
        DialogType statusType = isSiteHomepage ? DialogType.HOMEPAGE_STATUS : DialogType.POST_STATUS;
        PostSettingsListDialogFragment fragment = PostSettingsListDialogFragment.newInstance(statusType, index);
        if (!ListenerUtil.mutListener.listen(12451)) {
            fragment.show(fm, PostSettingsListDialogFragment.TAG);
        }
    }

    private boolean isSiteHomepage() {
        EditPostRepository postRepository = getEditPostRepository();
        boolean isPage = postRepository.isPage();
        boolean isPublishedPage = (ListenerUtil.mutListener.listen(12452) ? (postRepository.getStatus() == PostStatus.PUBLISHED && postRepository.getStatus() == PostStatus.PRIVATE) : (postRepository.getStatus() == PostStatus.PUBLISHED || postRepository.getStatus() == PostStatus.PRIVATE));
        boolean isHomepage = postRepository.getRemotePostId() == getSite().getPageOnFront();
        return (ListenerUtil.mutListener.listen(12454) ? ((ListenerUtil.mutListener.listen(12453) ? (isPage || isPublishedPage) : (isPage && isPublishedPage)) || isHomepage) : ((ListenerUtil.mutListener.listen(12453) ? (isPage || isPublishedPage) : (isPage && isPublishedPage)) && isHomepage));
    }

    private void showPostFormatDialog() {
        if (!ListenerUtil.mutListener.listen(12455)) {
            if (!isAdded()) {
                return;
            }
        }
        int checkedIndex = 0;
        String postFormat = getEditPostRepository().getPostFormat();
        if (!ListenerUtil.mutListener.listen(12464)) {
            if (!TextUtils.isEmpty(postFormat)) {
                if (!ListenerUtil.mutListener.listen(12463)) {
                    {
                        long _loopCounter215 = 0;
                        for (int i = 0; (ListenerUtil.mutListener.listen(12462) ? (i >= mPostFormatKeys.size()) : (ListenerUtil.mutListener.listen(12461) ? (i <= mPostFormatKeys.size()) : (ListenerUtil.mutListener.listen(12460) ? (i > mPostFormatKeys.size()) : (ListenerUtil.mutListener.listen(12459) ? (i != mPostFormatKeys.size()) : (ListenerUtil.mutListener.listen(12458) ? (i == mPostFormatKeys.size()) : (i < mPostFormatKeys.size())))))); i++) {
                            ListenerUtil.loopListener.listen("_loopCounter215", ++_loopCounter215);
                            if (!ListenerUtil.mutListener.listen(12457)) {
                                if (postFormat.equals(mPostFormatKeys.get(i))) {
                                    if (!ListenerUtil.mutListener.listen(12456)) {
                                        checkedIndex = i;
                                    }
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }
        FragmentManager fm = getActivity().getSupportFragmentManager();
        PostSettingsListDialogFragment fragment = PostSettingsListDialogFragment.newInstance(DialogType.POST_FORMAT, checkedIndex);
        if (!ListenerUtil.mutListener.listen(12465)) {
            fragment.show(fm, PostSettingsListDialogFragment.TAG);
        }
    }

    private void showPostPasswordDialog() {
        if (!ListenerUtil.mutListener.listen(12466)) {
            if (!isAdded()) {
                return;
            }
        }
        PostSettingsInputDialogFragment dialog = PostSettingsInputDialogFragment.newInstance(getEditPostRepository().getPassword(), getString(R.string.password), getString(R.string.post_settings_password_dialog_hint), false);
        if (!ListenerUtil.mutListener.listen(12469)) {
            dialog.setPostSettingsInputDialogListener(new PostSettingsInputDialogFragment.PostSettingsInputDialogListener() {

                @Override
                public void onInputUpdated(String input) {
                    if (!ListenerUtil.mutListener.listen(12467)) {
                        PostAnalyticsUtilsKt.trackPostSettings(mAnalyticsTrackerWrapper, Stat.EDITOR_POST_PASSWORD_CHANGED);
                    }
                    if (!ListenerUtil.mutListener.listen(12468)) {
                        updatePassword(input);
                    }
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(12470)) {
            dialog.show(getFragmentManager(), null);
        }
    }

    private EditPostRepository getEditPostRepository() {
        if (!ListenerUtil.mutListener.listen(12471)) {
            if (getEditPostActivityHook() == null) {
                // This can only happen during a callback while activity is re-created for some reason (config changes etc)
                return null;
            }
        }
        return getEditPostActivityHook().getEditPostRepository();
    }

    private SiteModel getSite() {
        if (!ListenerUtil.mutListener.listen(12472)) {
            if (getEditPostActivityHook() == null) {
                // This can only happen during a callback while activity is re-created for some reason (config changes etc)
                return null;
            }
        }
        return getEditPostActivityHook().getSite();
    }

    private EditPostActivityHook getEditPostActivityHook() {
        Activity activity = getActivity();
        if (activity == null) {
            return null;
        }
        if (activity instanceof EditPostActivityHook) {
            return (EditPostActivityHook) activity;
        } else {
            throw new RuntimeException(activity.toString() + " must implement EditPostActivityHook");
        }
    }

    private void updateSaveButton() {
        if (!ListenerUtil.mutListener.listen(12474)) {
            if (isAdded()) {
                if (!ListenerUtil.mutListener.listen(12473)) {
                    getActivity().invalidateOptionsMenu();
                }
            }
        }
    }

    private void updateExcerpt(String excerpt) {
        EditPostRepository editPostRepository = getEditPostRepository();
        if (!ListenerUtil.mutListener.listen(12476)) {
            if (editPostRepository != null) {
                if (!ListenerUtil.mutListener.listen(12475)) {
                    editPostRepository.updateAsync(postModel -> {
                        postModel.setExcerpt(excerpt);
                        return true;
                    }, (postModel, result) -> {
                        if (result == UpdatePostResult.Updated.INSTANCE) {
                            mExcerptTextView.setText(excerpt);
                        }
                        return null;
                    });
                }
            }
        }
    }

    private void updateSlug(String slug) {
        EditPostRepository editPostRepository = getEditPostRepository();
        if (!ListenerUtil.mutListener.listen(12478)) {
            if (editPostRepository != null) {
                if (!ListenerUtil.mutListener.listen(12477)) {
                    editPostRepository.updateAsync(postModel -> {
                        postModel.setSlug(slug);
                        return true;
                    }, (postModel, result) -> {
                        if (result == UpdatePostResult.Updated.INSTANCE) {
                            mSlugTextView.setText(slug);
                        }
                        return null;
                    });
                }
            }
        }
    }

    private void updatePassword(String password) {
        EditPostRepository editPostRepository = getEditPostRepository();
        if (!ListenerUtil.mutListener.listen(12479)) {
            if (editPostRepository == null)
                return;
        }
        String trimmedPassword = password.trim();
        Boolean isNewPasswordBlank = trimmedPassword.isEmpty();
        String previousPassword = editPostRepository.getPassword();
        Boolean isPreviousPasswordBlank = previousPassword.trim().isEmpty();
        if (!ListenerUtil.mutListener.listen(12481)) {
            // Nothing to save
            if ((ListenerUtil.mutListener.listen(12480) ? (isNewPasswordBlank || isPreviousPasswordBlank) : (isNewPasswordBlank && isPreviousPasswordBlank)))
                return;
        }
        // Save untrimmed password if not blank, else save empty string
        String newPassword = isNewPasswordBlank ? trimmedPassword : password;
        if (!ListenerUtil.mutListener.listen(12482)) {
            editPostRepository.updateAsync(postModel -> {
                postModel.setPassword(newPassword);
                return true;
            }, (postModel, result) -> {
                if (result == UpdatePostResult.Updated.INSTANCE) {
                    mPasswordTextView.setText(newPassword);
                }
                return null;
            });
        }
    }

    private void updateCategories(List<Long> categoryList) {
        if (!ListenerUtil.mutListener.listen(12483)) {
            if (categoryList == null) {
                return;
            }
        }
        EditPostRepository editPostRepository = getEditPostRepository();
        if (!ListenerUtil.mutListener.listen(12485)) {
            if (editPostRepository != null) {
                if (!ListenerUtil.mutListener.listen(12484)) {
                    editPostRepository.updateAsync(postModel -> {
                        postModel.setCategoryIdList(categoryList);
                        return true;
                    }, (postModel, result) -> {
                        if (result == UpdatePostResult.Updated.INSTANCE) {
                            updateCategoriesTextView(postModel);
                        }
                        return null;
                    });
                }
            }
        }
    }

    void updatePostStatus(PostStatus postStatus) {
        EditPostRepository editPostRepository = getEditPostRepository();
        if (!ListenerUtil.mutListener.listen(12487)) {
            if (editPostRepository != null) {
                if (!ListenerUtil.mutListener.listen(12486)) {
                    mUpdatePostStatusUseCase.updatePostStatus(postStatus, editPostRepository, postImmutableModel -> {
                        updatePostStatusRelatedViews(postImmutableModel);
                        updateSaveButton();
                        return null;
                    });
                }
            }
        }
    }

    private void updatePostFormat(String postFormat) {
        EditPostRepository editPostRepository = getEditPostRepository();
        if (!ListenerUtil.mutListener.listen(12489)) {
            if (editPostRepository != null) {
                if (!ListenerUtil.mutListener.listen(12488)) {
                    editPostRepository.updateAsync(postModel -> {
                        postModel.setPostFormat(postFormat);
                        return true;
                    }, (postModel, result) -> {
                        if (result == UpdatePostResult.Updated.INSTANCE) {
                            updatePostFormatTextView(postModel);
                        }
                        return null;
                    });
                }
            }
        }
    }

    public void updatePostStatusRelatedViews(PostImmutableModel postModel) {
        if (!ListenerUtil.mutListener.listen(12490)) {
            updateStatusTextView();
        }
        if (!ListenerUtil.mutListener.listen(12491)) {
            updatePublishDateTextView(postModel);
        }
        if (!ListenerUtil.mutListener.listen(12492)) {
            mPublishedViewModel.onPostStatusChanged(postModel);
        }
    }

    private void updateStatusTextView() {
        if (!ListenerUtil.mutListener.listen(12493)) {
            if (!isAdded()) {
                return;
            }
        }
        String[] statuses = getResources().getStringArray(R.array.post_settings_statuses);
        int index = getCurrentPostStatusIndex();
        if (!ListenerUtil.mutListener.listen(12494)) {
            // we should let it crash so we can fix the underlying issue
            mStatusTextView.setText(statuses[index]);
        }
    }

    private void updateTags(String selectedTags) {
        EditPostRepository postRepository = getEditPostRepository();
        if (!ListenerUtil.mutListener.listen(12495)) {
            if (postRepository == null) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(12496)) {
            postRepository.updateAsync(postModel -> {
                if (!TextUtils.isEmpty(selectedTags)) {
                    String tags = selectedTags.replace("\n", " ");
                    postModel.setTagNameList(Arrays.asList(TextUtils.split(tags, ",")));
                } else {
                    postModel.setTagNameList(new ArrayList<>());
                }
                return true;
            }, (postModel, result) -> {
                if (result == UpdatePostResult.Updated.INSTANCE) {
                    updateTagsTextView(postModel);
                }
                return null;
            });
        }
    }

    private void updateTagsTextView(PostImmutableModel postModel) {
        String tags = TextUtils.join(",", postModel.getTagNameList());
        if (!ListenerUtil.mutListener.listen(12497)) {
            // If `tags` is empty, the hint "Not Set" will be shown instead
            tags = StringEscapeUtils.unescapeHtml4(tags);
        }
        if (!ListenerUtil.mutListener.listen(12498)) {
            mTagsTextView.setText(tags);
        }
    }

    private void updateStickySwitch(PostImmutableModel postModel) {
        if (!ListenerUtil.mutListener.listen(12501)) {
            if ((ListenerUtil.mutListener.listen(12500) ? ((ListenerUtil.mutListener.listen(12499) ? (!isAdded() && postModel == null) : (!isAdded() || postModel == null)) && mStickySwitch == null) : ((ListenerUtil.mutListener.listen(12499) ? (!isAdded() && postModel == null) : (!isAdded() || postModel == null)) || mStickySwitch == null))) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(12502)) {
            // We need to remove the listener first, otherwise the listener will be triggered
            mStickySwitch.setOnCheckedChangeListener(null);
        }
        if (!ListenerUtil.mutListener.listen(12503)) {
            mStickySwitch.setChecked(postModel.getSticky());
        }
        if (!ListenerUtil.mutListener.listen(12504)) {
            mStickySwitch.setOnCheckedChangeListener(mOnStickySwitchChangeListener);
        }
    }

    private void updatePostFormatTextView(PostImmutableModel postModel) {
        if (!ListenerUtil.mutListener.listen(12505)) {
            // Post format can be updated due to a site settings fetch and the textView might not have been initialized yet
            if (mPostFormatTextView == null) {
                return;
            }
        }
        String postFormat = getPostFormatNameFromKey(postModel.getPostFormat());
        if (!ListenerUtil.mutListener.listen(12506)) {
            mPostFormatTextView.setText(postFormat);
        }
    }

    private void updatePublishDateTextView(PostImmutableModel postModel) {
        if (!ListenerUtil.mutListener.listen(12507)) {
            if (!isAdded()) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(12509)) {
            if (postModel != null) {
                String labelToUse = mPostSettingsUtils.getPublishDateLabel(postModel);
                if (!ListenerUtil.mutListener.listen(12508)) {
                    updatePublishDateTextView(labelToUse, postModel);
                }
            }
        }
    }

    private void updatePublishDateTextView(String label, PostImmutableModel postImmutableModel) {
        if (!ListenerUtil.mutListener.listen(12510)) {
            mPublishDateTextView.setText(label);
        }
        boolean isPrivatePost = postImmutableModel.getStatus().equals(PostStatus.PRIVATE.toString());
        if (!ListenerUtil.mutListener.listen(12511)) {
            mPublishDateTextView.setEnabled(!isPrivatePost);
        }
        if (!ListenerUtil.mutListener.listen(12512)) {
            mPublishDateTitleTextView.setEnabled(!isPrivatePost);
        }
        if (!ListenerUtil.mutListener.listen(12513)) {
            mPublishDateContainer.setEnabled(!isPrivatePost);
        }
    }

    private void updateCategoriesTextView(PostImmutableModel post) {
        if (!ListenerUtil.mutListener.listen(12515)) {
            if ((ListenerUtil.mutListener.listen(12514) ? (post == null && getSite() == null) : (post == null || getSite() == null))) {
                // Since this method can get called after a callback, we have to make sure we have the post and site
                return;
            }
        }
        List<TermModel> categories = mTaxonomyStore.getCategoriesForPost(post, getSite());
        StringBuilder sb = new StringBuilder();
        Iterator<TermModel> it = categories.iterator();
        if (!ListenerUtil.mutListener.listen(12520)) {
            if (it.hasNext()) {
                if (!ListenerUtil.mutListener.listen(12516)) {
                    sb.append(it.next().getName());
                }
                if (!ListenerUtil.mutListener.listen(12519)) {
                    {
                        long _loopCounter216 = 0;
                        while (it.hasNext()) {
                            ListenerUtil.loopListener.listen("_loopCounter216", ++_loopCounter216);
                            if (!ListenerUtil.mutListener.listen(12517)) {
                                sb.append(", ");
                            }
                            if (!ListenerUtil.mutListener.listen(12518)) {
                                sb.append(it.next().getName());
                            }
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(12521)) {
            // If `sb` is empty, the hint "Not Set" will be shown instead
            mCategoriesTextView.setText(StringEscapeUtils.unescapeHtml4(sb.toString()));
        }
    }

    private PostStatus getPostStatusAtIndex(int index) {
        switch(index) {
            case 0:
                return PostStatus.PUBLISHED;
            case 1:
                return PostStatus.DRAFT;
            case 2:
                return PostStatus.PENDING;
            case 3:
                return PostStatus.PRIVATE;
            default:
                return PostStatus.UNKNOWN;
        }
    }

    private int getCurrentPostStatusIndex() {
        if (!ListenerUtil.mutListener.listen(12522)) {
            switch(getEditPostRepository().getStatus()) {
                case DRAFT:
                    return 1;
                case PENDING:
                    return 2;
                case PRIVATE:
                    return 3;
                case TRASHED:
                case UNKNOWN:
                case PUBLISHED:
                case SCHEDULED:
                    return 0;
            }
        }
        return 0;
    }

    private PostStatus getHomepageStatusAtIndex(int index) {
        switch(index) {
            case 0:
                return PostStatus.PUBLISHED;
            case 1:
                return PostStatus.PRIVATE;
            default:
                return PostStatus.UNKNOWN;
        }
    }

    private int getCurrentHomepageStatusIndex() {
        if (!ListenerUtil.mutListener.listen(12523)) {
            switch(getEditPostRepository().getStatus()) {
                case PRIVATE:
                    return 1;
                case DRAFT:
                case PENDING:
                case TRASHED:
                case UNKNOWN:
                case PUBLISHED:
                case SCHEDULED:
                    return 0;
            }
        }
        return 0;
    }

    private void updatePostFormatKeysAndNames() {
        final SiteModel site = getSite();
        if (!ListenerUtil.mutListener.listen(12524)) {
            if (site == null) {
                // Since this method can get called after a callback, we have to make sure we have the site
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(12525)) {
            // Initialize the lists from the defaults
            mPostFormatKeys = new ArrayList<>(mDefaultPostFormatKeys);
        }
        if (!ListenerUtil.mutListener.listen(12526)) {
            mPostFormatNames = new ArrayList<>(mDefaultPostFormatNames);
        }
        // If we have specific values for this site, use them
        List<PostFormatModel> postFormatModels = mSiteStore.getPostFormats(site);
        if (!ListenerUtil.mutListener.listen(12530)) {
            {
                long _loopCounter217 = 0;
                for (PostFormatModel postFormatModel : postFormatModels) {
                    ListenerUtil.loopListener.listen("_loopCounter217", ++_loopCounter217);
                    if (!ListenerUtil.mutListener.listen(12529)) {
                        if (!mPostFormatKeys.contains(postFormatModel.getSlug())) {
                            if (!ListenerUtil.mutListener.listen(12527)) {
                                mPostFormatKeys.add(postFormatModel.getSlug());
                            }
                            if (!ListenerUtil.mutListener.listen(12528)) {
                                mPostFormatNames.add(postFormatModel.getDisplayName());
                            }
                        }
                    }
                }
            }
        }
    }

    private String getPostFormatKeyFromName(String postFormatName) {
        if (!ListenerUtil.mutListener.listen(12537)) {
            {
                long _loopCounter218 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(12536) ? (i >= mPostFormatNames.size()) : (ListenerUtil.mutListener.listen(12535) ? (i <= mPostFormatNames.size()) : (ListenerUtil.mutListener.listen(12534) ? (i > mPostFormatNames.size()) : (ListenerUtil.mutListener.listen(12533) ? (i != mPostFormatNames.size()) : (ListenerUtil.mutListener.listen(12532) ? (i == mPostFormatNames.size()) : (i < mPostFormatNames.size())))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter218", ++_loopCounter218);
                    if (!ListenerUtil.mutListener.listen(12531)) {
                        if (postFormatName.equalsIgnoreCase(mPostFormatNames.get(i))) {
                            return mPostFormatKeys.get(i);
                        }
                    }
                }
            }
        }
        return POST_FORMAT_STANDARD_KEY;
    }

    private String getPostFormatNameFromKey(String postFormatKey) {
        if (!ListenerUtil.mutListener.listen(12539)) {
            if (TextUtils.isEmpty(postFormatKey)) {
                if (!ListenerUtil.mutListener.listen(12538)) {
                    postFormatKey = POST_FORMAT_STANDARD_KEY;
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(12546)) {
            {
                long _loopCounter219 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(12545) ? (i >= mPostFormatKeys.size()) : (ListenerUtil.mutListener.listen(12544) ? (i <= mPostFormatKeys.size()) : (ListenerUtil.mutListener.listen(12543) ? (i > mPostFormatKeys.size()) : (ListenerUtil.mutListener.listen(12542) ? (i != mPostFormatKeys.size()) : (ListenerUtil.mutListener.listen(12541) ? (i == mPostFormatKeys.size()) : (i < mPostFormatKeys.size())))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter219", ++_loopCounter219);
                    if (!ListenerUtil.mutListener.listen(12540)) {
                        if (postFormatKey.equalsIgnoreCase(mPostFormatKeys.get(i))) {
                            return mPostFormatNames.get(i);
                        }
                    }
                }
            }
        }
        // return the capitalized key as the name which should be better than returning `null`
        return StringUtils.capitalize(postFormatKey);
    }

    public void updateFeaturedImage(long featuredImageId, boolean imagePicked) {
        if (!ListenerUtil.mutListener.listen(12549)) {
            if ((ListenerUtil.mutListener.listen(12547) ? (isAdded() || imagePicked) : (isAdded() && imagePicked))) {
                int postId = getEditPostRepository().getId();
                if (!ListenerUtil.mutListener.listen(12548)) {
                    mFeaturedImageHelper.trackFeaturedImageEvent(TrackableEvent.IMAGE_PICKED_POST_SETTINGS, postId);
                }
            }
        }
        EditPostRepository postRepository = getEditPostRepository();
        if (!ListenerUtil.mutListener.listen(12550)) {
            if (postRepository == null) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(12551)) {
            mUpdateFeaturedImageUseCase.updateFeaturedImage(featuredImageId, postRepository, postModel -> {
                updateFeaturedImageView(postModel);
                return null;
            });
        }
    }

    private void clearFeaturedImage() {
        if (!ListenerUtil.mutListener.listen(12552)) {
            updateFeaturedImage(0, false);
        }
        if (!ListenerUtil.mutListener.listen(12554)) {
            if (getActivity() instanceof EditPostSettingsCallback) {
                if (!ListenerUtil.mutListener.listen(12553)) {
                    ((EditPostSettingsCallback) getActivity()).clearFeaturedImage();
                }
            }
        }
    }

    private void updateFeaturedImageView(PostImmutableModel postModel) {
        Context context = getContext();
        SiteModel site = getSite();
        if (!ListenerUtil.mutListener.listen(12558)) {
            if ((ListenerUtil.mutListener.listen(12557) ? ((ListenerUtil.mutListener.listen(12556) ? ((ListenerUtil.mutListener.listen(12555) ? (!isAdded() && postModel == null) : (!isAdded() || postModel == null)) && site == null) : ((ListenerUtil.mutListener.listen(12555) ? (!isAdded() && postModel == null) : (!isAdded() || postModel == null)) || site == null)) && context == null) : ((ListenerUtil.mutListener.listen(12556) ? ((ListenerUtil.mutListener.listen(12555) ? (!isAdded() && postModel == null) : (!isAdded() || postModel == null)) && site == null) : ((ListenerUtil.mutListener.listen(12555) ? (!isAdded() && postModel == null) : (!isAdded() || postModel == null)) || site == null)) || context == null))) {
                return;
            }
        }
        final FeaturedImageData currentFeaturedImageState = mFeaturedImageHelper.createCurrentFeaturedImageState(site, postModel);
        FeaturedImageState uiState = currentFeaturedImageState.getUiState();
        if (!ListenerUtil.mutListener.listen(12559)) {
            updateFeaturedImageViews(currentFeaturedImageState.getUiState());
        }
        if (!ListenerUtil.mutListener.listen(12565)) {
            if (currentFeaturedImageState.getMediaUri() != null) {
                if (!ListenerUtil.mutListener.listen(12564)) {
                    if (uiState == FeaturedImageState.REMOTE_IMAGE_LOADING) {
                        if (!ListenerUtil.mutListener.listen(12563)) {
                            /*
                 *  Fetch the remote image, but keep showing the local image (when present) until "onResourceReady"
                 *  is invoked.  We use this hack to prevent showing an empty view when the local image is replaced
                 *  with a remote image.
                 */
                            mImageManager.loadWithResultListener(mFeaturedImageView, ImageType.IMAGE, currentFeaturedImageState.getMediaUri(), ScaleType.FIT_CENTER, null, new RequestListener<Drawable>() {

                                @Override
                                public void onLoadFailed(@Nullable Exception e, @Nullable Object model) {
                                }

                                @Override
                                public void onResourceReady(@NonNull Drawable resource, @Nullable Object model) {
                                    if (!ListenerUtil.mutListener.listen(12562)) {
                                        if (currentFeaturedImageState.getUiState() == FeaturedImageState.REMOTE_IMAGE_LOADING) {
                                            if (!ListenerUtil.mutListener.listen(12561)) {
                                                updateFeaturedImageViews(FeaturedImageState.REMOTE_IMAGE_SET);
                                            }
                                        }
                                    }
                                }
                            });
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(12560)) {
                            mImageManager.load(mLocalFeaturedImageView, ImageType.IMAGE, currentFeaturedImageState.getMediaUri(), ScaleType.FIT_CENTER);
                        }
                    }
                }
            }
        }
    }

    private void launchFeaturedMediaPicker() {
        if (!ListenerUtil.mutListener.listen(12568)) {
            if (isAdded()) {
                int postId = getEditPostRepository().getId();
                if (!ListenerUtil.mutListener.listen(12566)) {
                    mFeaturedImageHelper.trackFeaturedImageEvent(TrackableEvent.IMAGE_SET_CLICKED, postId);
                }
                if (!ListenerUtil.mutListener.listen(12567)) {
                    mMediaPickerLauncher.showFeaturedImagePicker(requireActivity(), getSite(), postId);
                }
            }
        }
    }

    private Calendar getCurrentPublishDateAsCalendar() {
        Calendar calendar = Calendar.getInstance();
        String dateCreated = getEditPostRepository().getDateCreated();
        if (!ListenerUtil.mutListener.listen(12570)) {
            // Set the currently selected time if available
            if (!TextUtils.isEmpty(dateCreated)) {
                if (!ListenerUtil.mutListener.listen(12569)) {
                    calendar.setTime(DateTimeUtils.dateFromIso8601(dateCreated));
                }
            }
        }
        return calendar;
    }

    @SuppressWarnings("unused")
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onTaxonomyChanged(OnTaxonomyChanged event) {
        if (!ListenerUtil.mutListener.listen(12572)) {
            if (event.isError()) {
                if (!ListenerUtil.mutListener.listen(12571)) {
                    AppLog.e(T.POSTS, "An error occurred while updating taxonomy with type: " + event.error.type);
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(12574)) {
            if (event.causeOfChange == TaxonomyAction.FETCH_CATEGORIES) {
                if (!ListenerUtil.mutListener.listen(12573)) {
                    updateCategoriesTextView(getEditPostRepository().getPost());
                }
            }
        }
    }

    @SuppressWarnings("unused")
    @Subscribe
    public void onPostFormatsChanged(OnPostFormatsChanged event) {
        if (!ListenerUtil.mutListener.listen(12576)) {
            if (event.isError()) {
                if (!ListenerUtil.mutListener.listen(12575)) {
                    AppLog.e(T.POSTS, "An error occurred while updating the post formats with type: " + event.error.type);
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(12577)) {
            AppLog.v(T.POSTS, "Post formats successfully fetched!");
        }
        if (!ListenerUtil.mutListener.listen(12578)) {
            updatePostFormatKeysAndNames();
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMediaUploaded(OnMediaUploaded event) {
        if (!ListenerUtil.mutListener.listen(12580)) {
            if (event.media.getMarkedLocallyAsFeatured()) {
                if (!ListenerUtil.mutListener.listen(12579)) {
                    refreshViews();
                }
            }
        }
    }

    private void updateFeaturedImageViews(FeaturedImageState state) {
        if (!ListenerUtil.mutListener.listen(12581)) {
            mUiHelpers.updateVisibility(mFeaturedImageView, state.getImageViewVisible());
        }
        if (!ListenerUtil.mutListener.listen(12582)) {
            mUiHelpers.updateVisibility(mLocalFeaturedImageView, state.getLocalImageViewVisible());
        }
        if (!ListenerUtil.mutListener.listen(12583)) {
            mUiHelpers.updateVisibility(mFeaturedImageButton, state.getButtonVisible());
        }
        if (!ListenerUtil.mutListener.listen(12584)) {
            mUiHelpers.updateVisibility(mFeaturedImageRetryOverlay, state.getRetryOverlayVisible());
        }
        if (!ListenerUtil.mutListener.listen(12585)) {
            mUiHelpers.updateVisibility(mFeaturedImageProgressOverlay, state.getProgressOverlayVisible());
        }
        if (!ListenerUtil.mutListener.listen(12587)) {
            if (!state.getLocalImageViewVisible()) {
                if (!ListenerUtil.mutListener.listen(12586)) {
                    mImageManager.cancelRequestAndClearImageView(mLocalFeaturedImageView);
                }
            }
        }
    }

    interface EditPostSettingsCallback {

        void onEditPostPublishedSettingsClick();

        void clearFeaturedImage();
    }
}
