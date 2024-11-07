package org.wordpress.android.ui.photopicker;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import org.wordpress.android.BuildConfig;
import org.wordpress.android.R;
import org.wordpress.android.WordPress;
import org.wordpress.android.fluxc.Dispatcher;
import org.wordpress.android.fluxc.model.SiteModel;
import org.wordpress.android.fluxc.store.MediaStore;
import org.wordpress.android.imageeditor.preview.PreviewImageFragment;
import org.wordpress.android.ui.ActivityLauncher;
import org.wordpress.android.ui.LocaleAwareActivity;
import org.wordpress.android.ui.RequestCodes;
import org.wordpress.android.ui.media.MediaBrowserActivity;
import org.wordpress.android.ui.media.MediaBrowserType;
import org.wordpress.android.ui.posts.FeaturedImageHelper;
import org.wordpress.android.ui.posts.FeaturedImageHelper.EnqueueFeaturedImageResult;
import org.wordpress.android.ui.posts.editor.ImageEditorTracker;
import org.wordpress.android.util.AppLog;
import org.wordpress.android.util.ListUtils;
import org.wordpress.android.util.ToastUtils;
import org.wordpress.android.util.WPMediaUtils;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.inject.Inject;
import static org.wordpress.android.ui.RequestCodes.IMAGE_EDITOR_EDIT_IMAGE;
import static org.wordpress.android.ui.media.MediaBrowserActivity.ARG_BROWSER_TYPE;
import static org.wordpress.android.ui.posts.FeaturedImageHelperKt.EMPTY_LOCAL_POST_ID;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * This class is being refactored, if you implement any change, please also update
 * {@link org.wordpress.android.ui.mediapicker.MediaPickerActivity}
 */
@Deprecated
public class PhotoPickerActivity extends LocaleAwareActivity implements PhotoPickerFragment.PhotoPickerListener {

    private static final String PICKER_FRAGMENT_TAG = "picker_fragment_tag";

    private static final String KEY_MEDIA_CAPTURE_PATH = "media_capture_path";

    private String mMediaCapturePath;

    private MediaBrowserType mBrowserType;

    // note that the site isn't required and may be null
    private SiteModel mSite;

    // note that the local post id isn't required (default value is EMPTY_LOCAL_POST_ID)
    private Integer mLocalPostId;

    @Inject
    Dispatcher mDispatcher;

    @Inject
    MediaStore mMediaStore;

    @Inject
    FeaturedImageHelper mFeaturedImageHelper;

    @Inject
    ImageEditorTracker mImageEditorTracker;

    public enum PhotoPickerMediaSource {

        ANDROID_CAMERA, ANDROID_PICKER, APP_PICKER, WP_MEDIA_PICKER, STOCK_MEDIA_PICKER;

        public static PhotoPickerMediaSource fromString(String strSource) {
            if (!ListenerUtil.mutListener.listen(10253)) {
                if (strSource != null) {
                    if (!ListenerUtil.mutListener.listen(10252)) {
                        {
                            long _loopCounter195 = 0;
                            for (PhotoPickerMediaSource source : PhotoPickerMediaSource.values()) {
                                ListenerUtil.loopListener.listen("_loopCounter195", ++_loopCounter195);
                                if (!ListenerUtil.mutListener.listen(10251)) {
                                    if (source.name().equalsIgnoreCase(strSource)) {
                                        return source;
                                    }
                                }
                            }
                        }
                    }
                }
            }
            return null;
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(10254)) {
            super.onCreate(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(10255)) {
            ((WordPress) getApplication()).component().inject(this);
        }
        if (!ListenerUtil.mutListener.listen(10256)) {
            setContentView(R.layout.photo_picker_activity);
        }
        Toolbar toolbar = findViewById(R.id.toolbar_main);
        if (!ListenerUtil.mutListener.listen(10257)) {
            toolbar.setNavigationIcon(R.drawable.ic_close_white_24dp);
        }
        if (!ListenerUtil.mutListener.listen(10258)) {
            setSupportActionBar(toolbar);
        }
        ActionBar actionBar = getSupportActionBar();
        if (!ListenerUtil.mutListener.listen(10261)) {
            if (actionBar != null) {
                if (!ListenerUtil.mutListener.listen(10259)) {
                    actionBar.setDisplayHomeAsUpEnabled(true);
                }
                if (!ListenerUtil.mutListener.listen(10260)) {
                    actionBar.setDisplayShowTitleEnabled(true);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(10268)) {
            if (savedInstanceState == null) {
                if (!ListenerUtil.mutListener.listen(10265)) {
                    mBrowserType = (MediaBrowserType) getIntent().getSerializableExtra(ARG_BROWSER_TYPE);
                }
                if (!ListenerUtil.mutListener.listen(10266)) {
                    mSite = (SiteModel) getIntent().getSerializableExtra(WordPress.SITE);
                }
                if (!ListenerUtil.mutListener.listen(10267)) {
                    mLocalPostId = getIntent().getIntExtra(MediaPickerConstants.LOCAL_POST_ID, EMPTY_LOCAL_POST_ID);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(10262)) {
                    mBrowserType = (MediaBrowserType) savedInstanceState.getSerializable(ARG_BROWSER_TYPE);
                }
                if (!ListenerUtil.mutListener.listen(10263)) {
                    mSite = (SiteModel) savedInstanceState.getSerializable(WordPress.SITE);
                }
                if (!ListenerUtil.mutListener.listen(10264)) {
                    mLocalPostId = savedInstanceState.getInt(MediaPickerConstants.LOCAL_POST_ID, EMPTY_LOCAL_POST_ID);
                }
            }
        }
        PhotoPickerFragment fragment = getPickerFragment();
        if (!ListenerUtil.mutListener.listen(10272)) {
            if (fragment == null) {
                if (!ListenerUtil.mutListener.listen(10270)) {
                    fragment = PhotoPickerFragment.newInstance(this, mBrowserType, mSite);
                }
                if (!ListenerUtil.mutListener.listen(10271)) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment, PICKER_FRAGMENT_TAG).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).commitAllowingStateLoss();
                }
            } else {
                if (!ListenerUtil.mutListener.listen(10269)) {
                    fragment.setPhotoPickerListener(this);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(10273)) {
            updateTitle(mBrowserType, actionBar);
        }
    }

    private void updateTitle(MediaBrowserType browserType, ActionBar actionBar) {
        if (!ListenerUtil.mutListener.listen(10278)) {
            if ((ListenerUtil.mutListener.listen(10274) ? (browserType.isImagePicker() || browserType.isVideoPicker()) : (browserType.isImagePicker() && browserType.isVideoPicker()))) {
                if (!ListenerUtil.mutListener.listen(10277)) {
                    actionBar.setTitle(R.string.photo_picker_photo_or_video_title);
                }
            } else if (browserType.isVideoPicker()) {
                if (!ListenerUtil.mutListener.listen(10276)) {
                    actionBar.setTitle(R.string.photo_picker_video_title);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(10275)) {
                    actionBar.setTitle(R.string.photo_picker_title);
                }
            }
        }
    }

    private PhotoPickerFragment getPickerFragment() {
        Fragment fragment = getSupportFragmentManager().findFragmentByTag(PICKER_FRAGMENT_TAG);
        if (!ListenerUtil.mutListener.listen(10279)) {
            if (fragment != null) {
                return (PhotoPickerFragment) fragment;
            }
        }
        return null;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        if (!ListenerUtil.mutListener.listen(10280)) {
            super.onSaveInstanceState(outState);
        }
        if (!ListenerUtil.mutListener.listen(10281)) {
            outState.putSerializable(ARG_BROWSER_TYPE, mBrowserType);
        }
        if (!ListenerUtil.mutListener.listen(10282)) {
            outState.putInt(MediaPickerConstants.LOCAL_POST_ID, mLocalPostId);
        }
        if (!ListenerUtil.mutListener.listen(10284)) {
            if (mSite != null) {
                if (!ListenerUtil.mutListener.listen(10283)) {
                    outState.putSerializable(WordPress.SITE, mSite);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(10286)) {
            if (!TextUtils.isEmpty(mMediaCapturePath)) {
                if (!ListenerUtil.mutListener.listen(10285)) {
                    outState.putString(KEY_MEDIA_CAPTURE_PATH, mMediaCapturePath);
                }
            }
        }
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(10287)) {
            super.onRestoreInstanceState(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(10288)) {
            mMediaCapturePath = savedInstanceState.getString(KEY_MEDIA_CAPTURE_PATH);
        }
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        if (!ListenerUtil.mutListener.listen(10291)) {
            if (item.getItemId() == android.R.id.home) {
                if (!ListenerUtil.mutListener.listen(10289)) {
                    setResult(RESULT_CANCELED);
                }
                if (!ListenerUtil.mutListener.listen(10290)) {
                    finish();
                }
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (!ListenerUtil.mutListener.listen(10292)) {
            super.onActivityResult(requestCode, resultCode, data);
        }
        if (!ListenerUtil.mutListener.listen(10293)) {
            if (resultCode != Activity.RESULT_OK) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(10308)) {
            switch(requestCode) {
                // user chose a photo from the device library
                case RequestCodes.PICTURE_LIBRARY:
                case RequestCodes.VIDEO_LIBRARY:
                    if (!ListenerUtil.mutListener.listen(10295)) {
                        if (data != null) {
                            List<Uri> mediaUris = WPMediaUtils.retrieveMediaUris(data);
                            if (!ListenerUtil.mutListener.listen(10294)) {
                                getPickerFragment().urisSelectedFromSystemPicker(mediaUris);
                            }
                        }
                    }
                    break;
                case RequestCodes.TAKE_PHOTO:
                    try {
                        if (!ListenerUtil.mutListener.listen(10297)) {
                            WPMediaUtils.scanMediaFile(this, mMediaCapturePath);
                        }
                        File f = new File(mMediaCapturePath);
                        List<Uri> capturedImageUri = Collections.singletonList(Uri.fromFile(f));
                        if (!ListenerUtil.mutListener.listen(10298)) {
                            doMediaUrisSelected(capturedImageUri, PhotoPickerMediaSource.ANDROID_CAMERA);
                        }
                    } catch (RuntimeException e) {
                        if (!ListenerUtil.mutListener.listen(10296)) {
                            AppLog.e(AppLog.T.MEDIA, e);
                        }
                    }
                    break;
                // user selected from WP media library, extract the media ID and pass to caller
                case RequestCodes.MULTI_SELECT_MEDIA_PICKER:
                case RequestCodes.SINGLE_SELECT_MEDIA_PICKER:
                    if (!ListenerUtil.mutListener.listen(10300)) {
                        if (data.hasExtra(MediaBrowserActivity.RESULT_IDS)) {
                            ArrayList<Long> ids = ListUtils.fromLongArray(data.getLongArrayExtra(MediaBrowserActivity.RESULT_IDS));
                            if (!ListenerUtil.mutListener.listen(10299)) {
                                doMediaIdsSelected(ids, PhotoPickerMediaSource.WP_MEDIA_PICKER);
                            }
                        }
                    }
                    break;
                // user selected a stock photo
                case RequestCodes.STOCK_MEDIA_PICKER_SINGLE_SELECT:
                    if (!ListenerUtil.mutListener.listen(10304)) {
                        if ((ListenerUtil.mutListener.listen(10301) ? (data != null || data.hasExtra(MediaPickerConstants.EXTRA_MEDIA_ID)) : (data != null && data.hasExtra(MediaPickerConstants.EXTRA_MEDIA_ID)))) {
                            long mediaId = data.getLongExtra(MediaPickerConstants.EXTRA_MEDIA_ID, 0);
                            ArrayList<Long> ids = new ArrayList<>();
                            if (!ListenerUtil.mutListener.listen(10302)) {
                                ids.add(mediaId);
                            }
                            if (!ListenerUtil.mutListener.listen(10303)) {
                                doMediaIdsSelected(ids, PhotoPickerMediaSource.STOCK_MEDIA_PICKER);
                            }
                        }
                    }
                    break;
                case IMAGE_EDITOR_EDIT_IMAGE:
                    if (!ListenerUtil.mutListener.listen(10307)) {
                        if ((ListenerUtil.mutListener.listen(10305) ? (data != null || data.hasExtra(PreviewImageFragment.ARG_EDIT_IMAGE_DATA)) : (data != null && data.hasExtra(PreviewImageFragment.ARG_EDIT_IMAGE_DATA)))) {
                            List<Uri> uris = WPMediaUtils.retrieveImageEditorResult(data);
                            if (!ListenerUtil.mutListener.listen(10306)) {
                                doMediaUrisSelected(uris, PhotoPickerMediaSource.APP_PICKER);
                            }
                        }
                    }
                    break;
            }
        }
    }

    private void launchCameraForImage() {
        if (!ListenerUtil.mutListener.listen(10309)) {
            WPMediaUtils.launchCamera(this, BuildConfig.APPLICATION_ID, mediaCapturePath -> mMediaCapturePath = mediaCapturePath);
        }
    }

    private void launchCameraForVideo() {
        if (!ListenerUtil.mutListener.listen(10310)) {
            WPMediaUtils.launchVideoCamera(this);
        }
    }

    private void launchPictureLibrary(boolean multiSelect) {
        if (!ListenerUtil.mutListener.listen(10311)) {
            WPMediaUtils.launchPictureLibrary(this, multiSelect);
        }
    }

    private void launchVideoLibrary(boolean multiSelect) {
        if (!ListenerUtil.mutListener.listen(10312)) {
            WPMediaUtils.launchVideoLibrary(this, multiSelect);
        }
    }

    private void launchWPMediaLibrary() {
        if (!ListenerUtil.mutListener.listen(10315)) {
            if (mSite != null) {
                if (!ListenerUtil.mutListener.listen(10314)) {
                    ActivityLauncher.viewMediaPickerForResult(this, mSite, mBrowserType);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(10313)) {
                    ToastUtils.showToast(this, R.string.blog_not_found);
                }
            }
        }
    }

    private void launchStockMediaPicker() {
        if (!ListenerUtil.mutListener.listen(10318)) {
            if (mSite != null) {
                if (!ListenerUtil.mutListener.listen(10317)) {
                    ActivityLauncher.showStockMediaPickerForResult(this, mSite, RequestCodes.STOCK_MEDIA_PICKER_SINGLE_SELECT);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(10316)) {
                    ToastUtils.showToast(this, R.string.blog_not_found);
                }
            }
        }
    }

    private void launchWPStoriesCamera() {
        Intent intent = new Intent().putExtra(MediaPickerConstants.EXTRA_LAUNCH_WPSTORIES_CAMERA_REQUESTED, true);
        if (!ListenerUtil.mutListener.listen(10319)) {
            setResult(RESULT_OK, intent);
        }
        if (!ListenerUtil.mutListener.listen(10320)) {
            finish();
        }
    }

    private void doMediaUrisSelected(@NonNull List<? extends Uri> mediaUris, @NonNull PhotoPickerMediaSource source) {
        if (!ListenerUtil.mutListener.listen(10330)) {
            // if user chose a featured image, we need to upload it and return the uploaded media object
            if (mBrowserType == MediaBrowserType.FEATURED_IMAGE_PICKER) {
                Uri mediaUri = mediaUris.get(0);
                final String mimeType = getContentResolver().getType(mediaUri);
                if (!ListenerUtil.mutListener.listen(10323)) {
                    mFeaturedImageHelper.trackFeaturedImageEvent(FeaturedImageHelper.TrackableEvent.IMAGE_PICKED_POST_SETTINGS, mLocalPostId);
                }
                if (!ListenerUtil.mutListener.listen(10329)) {
                    WPMediaUtils.fetchMediaAndDoNext(this, mediaUri, new WPMediaUtils.MediaFetchDoNext() {

                        @Override
                        public void doNext(Uri uri) {
                            EnqueueFeaturedImageResult queueImageResult = mFeaturedImageHelper.queueFeaturedImageForUpload(mLocalPostId, mSite, uri, mimeType);
                            if (!ListenerUtil.mutListener.listen(10326)) {
                                // right after this call
                                switch(queueImageResult) {
                                    case FILE_NOT_FOUND:
                                        if (!ListenerUtil.mutListener.listen(10324)) {
                                            Toast.makeText(getApplicationContext(), R.string.file_not_found, Toast.LENGTH_SHORT).show();
                                        }
                                        break;
                                    case INVALID_POST_ID:
                                        if (!ListenerUtil.mutListener.listen(10325)) {
                                            Toast.makeText(getApplicationContext(), R.string.error_generic, Toast.LENGTH_SHORT).show();
                                        }
                                        break;
                                    case SUCCESS:
                                        // noop
                                        break;
                                }
                            }
                            Intent intent = new Intent();
                            if (!ListenerUtil.mutListener.listen(10327)) {
                                setResult(RESULT_OK, intent);
                            }
                            if (!ListenerUtil.mutListener.listen(10328)) {
                                finish();
                            }
                        }
                    });
                }
            } else {
                Intent intent = new Intent().putExtra(MediaPickerConstants.EXTRA_MEDIA_URIS, convertUrisListToStringArray(mediaUris)).putExtra(MediaPickerConstants.EXTRA_MEDIA_SOURCE, source.name()).putExtra(ARG_BROWSER_TYPE, mBrowserType);
                if (!ListenerUtil.mutListener.listen(10321)) {
                    setResult(RESULT_OK, intent);
                }
                if (!ListenerUtil.mutListener.listen(10322)) {
                    finish();
                }
            }
        }
    }

    private void doMediaIdsSelected(ArrayList<Long> mediaIds, @NonNull PhotoPickerMediaSource source) {
        if (!ListenerUtil.mutListener.listen(10343)) {
            if ((ListenerUtil.mutListener.listen(10336) ? (mediaIds != null || (ListenerUtil.mutListener.listen(10335) ? (mediaIds.size() >= 0) : (ListenerUtil.mutListener.listen(10334) ? (mediaIds.size() <= 0) : (ListenerUtil.mutListener.listen(10333) ? (mediaIds.size() < 0) : (ListenerUtil.mutListener.listen(10332) ? (mediaIds.size() != 0) : (ListenerUtil.mutListener.listen(10331) ? (mediaIds.size() == 0) : (mediaIds.size() > 0))))))) : (mediaIds != null && (ListenerUtil.mutListener.listen(10335) ? (mediaIds.size() >= 0) : (ListenerUtil.mutListener.listen(10334) ? (mediaIds.size() <= 0) : (ListenerUtil.mutListener.listen(10333) ? (mediaIds.size() < 0) : (ListenerUtil.mutListener.listen(10332) ? (mediaIds.size() != 0) : (ListenerUtil.mutListener.listen(10331) ? (mediaIds.size() == 0) : (mediaIds.size() > 0))))))))) {
                if (!ListenerUtil.mutListener.listen(10342)) {
                    if (mBrowserType == MediaBrowserType.WP_STORIES_MEDIA_PICKER) {
                        if (!ListenerUtil.mutListener.listen(10341)) {
                            // TODO WPSTORIES add TRACKS (see how it's tracked below? maybe do along the same lines)
                            getPickerFragment().mediaIdsSelectedFromWPMediaPicker(mediaIds);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(10338)) {
                            // if user chose a featured image, track image picked event
                            if (mBrowserType == MediaBrowserType.FEATURED_IMAGE_PICKER) {
                                if (!ListenerUtil.mutListener.listen(10337)) {
                                    mFeaturedImageHelper.trackFeaturedImageEvent(FeaturedImageHelper.TrackableEvent.IMAGE_PICKED_POST_SETTINGS, mLocalPostId);
                                }
                            }
                        }
                        Intent data = new Intent().putExtra(MediaPickerConstants.EXTRA_MEDIA_ID, mediaIds.get(0)).putExtra(MediaPickerConstants.EXTRA_MEDIA_SOURCE, source.name());
                        if (!ListenerUtil.mutListener.listen(10339)) {
                            setResult(RESULT_OK, data);
                        }
                        if (!ListenerUtil.mutListener.listen(10340)) {
                            finish();
                        }
                    }
                }
            } else {
                throw new IllegalArgumentException("call to doMediaIdsSelected with null or empty mediaIds array");
            }
        }
    }

    @Override
    public void onPhotoPickerMediaChosen(@NonNull List<? extends Uri> uriList) {
        if (!ListenerUtil.mutListener.listen(10350)) {
            if ((ListenerUtil.mutListener.listen(10348) ? (uriList.size() >= 0) : (ListenerUtil.mutListener.listen(10347) ? (uriList.size() <= 0) : (ListenerUtil.mutListener.listen(10346) ? (uriList.size() < 0) : (ListenerUtil.mutListener.listen(10345) ? (uriList.size() != 0) : (ListenerUtil.mutListener.listen(10344) ? (uriList.size() == 0) : (uriList.size() > 0))))))) {
                if (!ListenerUtil.mutListener.listen(10349)) {
                    doMediaUrisSelected(uriList, PhotoPickerMediaSource.APP_PICKER);
                }
            }
        }
    }

    @Override
    public void onPhotoPickerIconClicked(@NonNull PhotoPickerFragment.PhotoPickerIcon icon, boolean multiple) {
        if (!ListenerUtil.mutListener.listen(10358)) {
            switch(icon) {
                case ANDROID_CAPTURE_PHOTO:
                    if (!ListenerUtil.mutListener.listen(10351)) {
                        launchCameraForImage();
                    }
                    break;
                case ANDROID_CHOOSE_PHOTO:
                    if (!ListenerUtil.mutListener.listen(10352)) {
                        launchPictureLibrary(multiple);
                    }
                    break;
                case ANDROID_CAPTURE_VIDEO:
                    if (!ListenerUtil.mutListener.listen(10353)) {
                        launchCameraForVideo();
                    }
                    break;
                case ANDROID_CHOOSE_VIDEO:
                    if (!ListenerUtil.mutListener.listen(10354)) {
                        launchVideoLibrary(multiple);
                    }
                    break;
                case WP_MEDIA:
                    if (!ListenerUtil.mutListener.listen(10355)) {
                        launchWPMediaLibrary();
                    }
                    break;
                case STOCK_MEDIA:
                    if (!ListenerUtil.mutListener.listen(10356)) {
                        launchStockMediaPicker();
                    }
                    break;
                case WP_STORIES_CAPTURE:
                    if (!ListenerUtil.mutListener.listen(10357)) {
                        launchWPStoriesCamera();
                    }
                    break;
            }
        }
    }

    private String[] convertUrisListToStringArray(List<? extends Uri> uris) {
        String[] stringUris = new String[uris.size()];
        if (!ListenerUtil.mutListener.listen(10365)) {
            {
                long _loopCounter196 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(10364) ? (i >= uris.size()) : (ListenerUtil.mutListener.listen(10363) ? (i <= uris.size()) : (ListenerUtil.mutListener.listen(10362) ? (i > uris.size()) : (ListenerUtil.mutListener.listen(10361) ? (i != uris.size()) : (ListenerUtil.mutListener.listen(10360) ? (i == uris.size()) : (i < uris.size())))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter196", ++_loopCounter196);
                    if (!ListenerUtil.mutListener.listen(10359)) {
                        stringUris[i] = uris.get(i).toString();
                    }
                }
            }
        }
        return stringUris;
    }
}
