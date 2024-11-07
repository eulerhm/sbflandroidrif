package org.wordpress.android.ui.media;

import android.Manifest;
import android.app.Activity;
import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.text.TextUtils;
import android.text.format.DateUtils;
import android.view.HapticFeedbackConstants;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.DrawableRes;
import androidx.annotation.IntegerRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.app.ActivityCompat;
import androidx.core.app.ActivityOptionsCompat;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.elevation.ElevationOverlayProvider;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.jetbrains.annotations.NotNull;
import org.wordpress.android.R;
import org.wordpress.android.WordPress;
import org.wordpress.android.editor.EditorImageMetaData;
import org.wordpress.android.fluxc.Dispatcher;
import org.wordpress.android.fluxc.action.MediaAction;
import org.wordpress.android.fluxc.generated.MediaActionBuilder;
import org.wordpress.android.fluxc.model.MediaModel;
import org.wordpress.android.fluxc.model.SiteModel;
import org.wordpress.android.fluxc.store.MediaStore;
import org.wordpress.android.fluxc.store.MediaStore.MediaPayload;
import org.wordpress.android.fluxc.store.MediaStore.OnMediaChanged;
import org.wordpress.android.ui.LocaleAwareActivity;
import org.wordpress.android.ui.RequestCodes;
import org.wordpress.android.ui.media.MediaPreviewActivity.MediaPreviewSwiped;
import org.wordpress.android.ui.utils.AuthenticationUtils;
import org.wordpress.android.util.AniUtils;
import org.wordpress.android.util.AppLog;
import org.wordpress.android.util.AppLog.T;
import org.wordpress.android.util.ColorUtils;
import org.wordpress.android.util.extensions.ContextExtensionsKt;
import org.wordpress.android.util.DateTimeUtils;
import org.wordpress.android.util.DisplayUtils;
import org.wordpress.android.util.EditTextUtils;
import org.wordpress.android.util.ImageUtils;
import org.wordpress.android.util.MediaUtils;
import org.wordpress.android.util.NetworkUtils;
import org.wordpress.android.util.PermissionUtils;
import org.wordpress.android.util.PhotonUtils;
import org.wordpress.android.util.SiteUtils;
import org.wordpress.android.util.StringUtils;
import org.wordpress.android.util.ToastUtils;
import org.wordpress.android.util.extensions.ViewExtensionsKt;
import org.wordpress.android.util.WPMediaUtils;
import org.wordpress.android.util.WPPermissionUtils;
import org.wordpress.android.util.image.ImageManager;
import org.wordpress.android.util.image.ImageManager.RequestListener;
import org.wordpress.android.util.image.ImageType;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;
import javax.inject.Inject;
import static org.wordpress.android.editor.EditorImageMetaData.ARG_EDITOR_IMAGE_METADATA;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class MediaSettingsActivity extends LocaleAwareActivity implements ActivityCompat.OnRequestPermissionsResultCallback {

    private static final String ARG_MEDIA_LOCAL_ID = "media_local_id";

    private static final String ARG_ID_LIST = "id_list";

    private static final String ARG_DELETE_MEDIA_DIALOG_VISIBLE = "delete_media_dialog_visible";

    public static final int RESULT_MEDIA_DELETED = RESULT_FIRST_USER;

    private long mDownloadId;

    private String mTitle;

    private boolean mDidRegisterEventBus;

    private SiteModel mSite;

    private MediaModel mMedia;

    private EditorImageMetaData mEditorImageMetaData;

    private ArrayList<String> mMediaIdList;

    private String[] mAlignmentKeyArray;

    private MediaSettingsImageSize mImageSize = MediaSettingsImageSize.FULL;

    private ImageView mImageView;

    private ImageView mImagePlay;

    private EditText mTitleView;

    private EditText mCaptionView;

    private EditText mAltTextView;

    private EditText mDescriptionView;

    private EditText mLinkView;

    private CheckBox mLinkTargetNewWindowView;

    private TextView mImageSizeView;

    private SeekBar mImageSizeSeekBarView;

    private Spinner mAlignmentSpinnerView;

    private FloatingActionButton mFabView;

    private AlertDialog mDeleteMediaConfirmationDialog;

    private ProgressDialog mProgressDialog;

    private enum MediaType {

        IMAGE, VIDEO, AUDIO, DOCUMENT
    }

    private MediaType mMediaType;

    @Inject
    MediaStore mMediaStore;

    @Inject
    Dispatcher mDispatcher;

    @Inject
    ImageManager mImageManager;

    @Inject
    AuthenticationUtils mAuthenticationUtils;

    /**
     * @param activity    calling activity
     * @param site        site this media is associated with
     * @param media       media model to display
     * @param mediaIdList optional list of media IDs to page through in preview screen
     */
    public static void showForResult(@NonNull Activity activity, @NonNull SiteModel site, @NonNull MediaModel media, @Nullable ArrayList<String> mediaIdList) {
        if (!ListenerUtil.mutListener.listen(7624)) {
            // go directly to preview for local images, videos and audio (do nothing for local documents)
            if (MediaUtils.isLocalFile(media.getUploadState())) {
                if (!ListenerUtil.mutListener.listen(7623)) {
                    if ((ListenerUtil.mutListener.listen(7621) ? ((ListenerUtil.mutListener.listen(7620) ? (MediaUtils.isValidImage(media.getFilePath()) && MediaUtils.isAudio(media.getFilePath())) : (MediaUtils.isValidImage(media.getFilePath()) || MediaUtils.isAudio(media.getFilePath()))) && media.isVideo()) : ((ListenerUtil.mutListener.listen(7620) ? (MediaUtils.isValidImage(media.getFilePath()) && MediaUtils.isAudio(media.getFilePath())) : (MediaUtils.isValidImage(media.getFilePath()) || MediaUtils.isAudio(media.getFilePath()))) || media.isVideo()))) {
                        if (!ListenerUtil.mutListener.listen(7622)) {
                            MediaPreviewActivity.showPreview(activity, site, media.getFilePath());
                        }
                    }
                }
                return;
            }
        }
        Intent intent = new Intent(activity, MediaSettingsActivity.class);
        if (!ListenerUtil.mutListener.listen(7625)) {
            intent.putExtra(ARG_MEDIA_LOCAL_ID, media.getId());
        }
        if (!ListenerUtil.mutListener.listen(7626)) {
            intent.putExtra(WordPress.SITE, site);
        }
        if (!ListenerUtil.mutListener.listen(7628)) {
            if (mediaIdList != null) {
                if (!ListenerUtil.mutListener.listen(7627)) {
                    intent.putExtra(ARG_ID_LIST, mediaIdList);
                }
            }
        }
        ActivityOptionsCompat options = ActivityOptionsCompat.makeCustomAnimation(activity, R.anim.activity_slide_up_from_bottom, R.anim.do_nothing);
        if (!ListenerUtil.mutListener.listen(7629)) {
            ActivityCompat.startActivityForResult(activity, intent, RequestCodes.MEDIA_SETTINGS, options.toBundle());
        }
    }

    /**
     * @param activity    calling activity
     * @param site        site this media is associated with
     * @param editorMedia editor image metadata
     */
    public static void showForResult(@NonNull Activity activity, @NonNull SiteModel site, @NonNull EditorImageMetaData editorMedia) {
        Intent intent = new Intent(activity, MediaSettingsActivity.class);
        if (!ListenerUtil.mutListener.listen(7630)) {
            intent.putExtra(WordPress.SITE, site);
        }
        if (!ListenerUtil.mutListener.listen(7631)) {
            intent.putExtra(ARG_EDITOR_IMAGE_METADATA, editorMedia);
        }
        ActivityOptionsCompat options = ActivityOptionsCompat.makeCustomAnimation(activity, R.anim.activity_slide_up_from_bottom, R.anim.do_nothing);
        if (!ListenerUtil.mutListener.listen(7632)) {
            ActivityCompat.startActivityForResult(activity, intent, RequestCodes.MEDIA_SETTINGS, options.toBundle());
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(7633)) {
            super.onCreate(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(7634)) {
            ((WordPress) getApplication()).component().inject(this);
        }
        if (!ListenerUtil.mutListener.listen(7635)) {
            setContentView(R.layout.media_settings_activity);
        }
        if (!ListenerUtil.mutListener.listen(7636)) {
            setSupportActionBar(findViewById(R.id.toolbar));
        }
        ActionBar actionBar = getSupportActionBar();
        if (!ListenerUtil.mutListener.listen(7640)) {
            if (actionBar != null) {
                if (!ListenerUtil.mutListener.listen(7637)) {
                    actionBar.setDisplayShowTitleEnabled(false);
                }
                if (!ListenerUtil.mutListener.listen(7638)) {
                    actionBar.setDisplayHomeAsUpEnabled(true);
                }
                if (!ListenerUtil.mutListener.listen(7639)) {
                    actionBar.setHomeAsUpIndicator(R.drawable.ic_close_white_24dp);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(7641)) {
            mImageView = findViewById(R.id.image_preview);
        }
        if (!ListenerUtil.mutListener.listen(7642)) {
            mImagePlay = findViewById(R.id.image_play);
        }
        if (!ListenerUtil.mutListener.listen(7643)) {
            mTitleView = findViewById(R.id.edit_title);
        }
        if (!ListenerUtil.mutListener.listen(7644)) {
            mCaptionView = findViewById(R.id.edit_caption);
        }
        if (!ListenerUtil.mutListener.listen(7645)) {
            mAltTextView = findViewById(R.id.edit_alt_text);
        }
        if (!ListenerUtil.mutListener.listen(7646)) {
            mDescriptionView = findViewById(R.id.edit_description);
        }
        if (!ListenerUtil.mutListener.listen(7647)) {
            mLinkView = findViewById(R.id.edit_link);
        }
        if (!ListenerUtil.mutListener.listen(7648)) {
            mLinkTargetNewWindowView = findViewById(R.id.edit_link_target_new_widnow_checkbox);
        }
        if (!ListenerUtil.mutListener.listen(7649)) {
            mImageSizeView = findViewById(R.id.image_size_hint);
        }
        if (!ListenerUtil.mutListener.listen(7650)) {
            mImageSizeSeekBarView = findViewById(R.id.image_size_seekbar);
        }
        if (!ListenerUtil.mutListener.listen(7651)) {
            mAlignmentSpinnerView = findViewById(org.wordpress.android.editor.R.id.alignment_spinner);
        }
        if (!ListenerUtil.mutListener.listen(7652)) {
            mFabView = findViewById(R.id.fab_button);
        }
        int mediaId;
        if (savedInstanceState != null) {
            if (!ListenerUtil.mutListener.listen(7657)) {
                mSite = (SiteModel) savedInstanceState.getSerializable(WordPress.SITE);
            }
            if (!ListenerUtil.mutListener.listen(7658)) {
                mEditorImageMetaData = savedInstanceState.getParcelable(ARG_EDITOR_IMAGE_METADATA);
            }
            mediaId = savedInstanceState.getInt(ARG_MEDIA_LOCAL_ID);
            if (!ListenerUtil.mutListener.listen(7660)) {
                if (savedInstanceState.containsKey(ARG_ID_LIST)) {
                    if (!ListenerUtil.mutListener.listen(7659)) {
                        mMediaIdList = savedInstanceState.getStringArrayList(ARG_ID_LIST);
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(7662)) {
                if (savedInstanceState.getBoolean(ARG_DELETE_MEDIA_DIALOG_VISIBLE, false)) {
                    if (!ListenerUtil.mutListener.listen(7661)) {
                        deleteMediaWithConfirmation();
                    }
                }
            }
        } else {
            if (!ListenerUtil.mutListener.listen(7653)) {
                mSite = (SiteModel) getIntent().getSerializableExtra(WordPress.SITE);
            }
            if (!ListenerUtil.mutListener.listen(7654)) {
                mEditorImageMetaData = getIntent().getParcelableExtra(ARG_EDITOR_IMAGE_METADATA);
            }
            mediaId = getIntent().getIntExtra(ARG_MEDIA_LOCAL_ID, 0);
            if (!ListenerUtil.mutListener.listen(7656)) {
                if (getIntent().hasExtra(ARG_ID_LIST)) {
                    if (!ListenerUtil.mutListener.listen(7655)) {
                        mMediaIdList = getIntent().getStringArrayListExtra(ARG_ID_LIST);
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(7664)) {
            if (isMediaFromEditor() ? !loadMediaFromEditor() : !loadMediaWithId(mediaId)) {
                if (!ListenerUtil.mutListener.listen(7663)) {
                    delayedFinishWithError();
                }
                return;
            }
        }
        // only show title when toolbar is collapsed
        final CollapsingToolbarLayout collapsingToolbar = findViewById(R.id.collapsing_toolbar);
        AppBarLayout appBarLayout = findViewById(R.id.app_bar_layout);
        if (!ListenerUtil.mutListener.listen(7665)) {
            collapsingToolbar.setCollapsedTitleTextColor(AppCompatResources.getColorStateList(this, ContextExtensionsKt.getColorResIdFromAttribute(this, R.attr.colorOnSurface)));
        }
        ElevationOverlayProvider elevationOverlayProvider = new ElevationOverlayProvider(this);
        float appbarElevation = getResources().getDimension(R.dimen.appbar_elevation);
        int elevatedColor = elevationOverlayProvider.compositeOverlayIfNeeded(ContextExtensionsKt.getColorFromAttribute(this, R.attr.wpColorAppBar), appbarElevation);
        if (!ListenerUtil.mutListener.listen(7666)) {
            collapsingToolbar.setContentScrimColor(elevatedColor);
        }
        if (!ListenerUtil.mutListener.listen(7672)) {
            appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {

                int mScrollRange = -1;

                @Override
                public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                    if (!ListenerUtil.mutListener.listen(7668)) {
                        if (mScrollRange == -1) {
                            if (!ListenerUtil.mutListener.listen(7667)) {
                                mScrollRange = appBarLayout.getTotalScrollRange();
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(7671)) {
                        if (mScrollRange + verticalOffset == 0) {
                            if (!ListenerUtil.mutListener.listen(7670)) {
                                collapsingToolbar.setTitle(mTitle);
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(7669)) {
                                // space between double quotes is on purpose
                                collapsingToolbar.setTitle(" ");
                            }
                        }
                    }
                }
            });
        }
        // make image 40% of screen height
        int displayHeight = DisplayUtils.getWindowPixelHeight(this);
        int imageHeight = (int) ((ListenerUtil.mutListener.listen(7676) ? (displayHeight % 0.4) : (ListenerUtil.mutListener.listen(7675) ? (displayHeight / 0.4) : (ListenerUtil.mutListener.listen(7674) ? (displayHeight - 0.4) : (ListenerUtil.mutListener.listen(7673) ? (displayHeight + 0.4) : (displayHeight * 0.4))))));
        if (!ListenerUtil.mutListener.listen(7677)) {
            mImageView.getLayoutParams().height = imageHeight;
        }
        // position progress in middle of image
        View progressView = findViewById(R.id.progress);
        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) progressView.getLayoutParams();
        int topMargin = (ListenerUtil.mutListener.listen(7689) ? (((ListenerUtil.mutListener.listen(7681) ? (imageHeight % 2) : (ListenerUtil.mutListener.listen(7680) ? (imageHeight * 2) : (ListenerUtil.mutListener.listen(7679) ? (imageHeight - 2) : (ListenerUtil.mutListener.listen(7678) ? (imageHeight + 2) : (imageHeight / 2)))))) % ((ListenerUtil.mutListener.listen(7685) ? (progressView.getHeight() % 2) : (ListenerUtil.mutListener.listen(7684) ? (progressView.getHeight() * 2) : (ListenerUtil.mutListener.listen(7683) ? (progressView.getHeight() - 2) : (ListenerUtil.mutListener.listen(7682) ? (progressView.getHeight() + 2) : (progressView.getHeight() / 2))))))) : (ListenerUtil.mutListener.listen(7688) ? (((ListenerUtil.mutListener.listen(7681) ? (imageHeight % 2) : (ListenerUtil.mutListener.listen(7680) ? (imageHeight * 2) : (ListenerUtil.mutListener.listen(7679) ? (imageHeight - 2) : (ListenerUtil.mutListener.listen(7678) ? (imageHeight + 2) : (imageHeight / 2)))))) / ((ListenerUtil.mutListener.listen(7685) ? (progressView.getHeight() % 2) : (ListenerUtil.mutListener.listen(7684) ? (progressView.getHeight() * 2) : (ListenerUtil.mutListener.listen(7683) ? (progressView.getHeight() - 2) : (ListenerUtil.mutListener.listen(7682) ? (progressView.getHeight() + 2) : (progressView.getHeight() / 2))))))) : (ListenerUtil.mutListener.listen(7687) ? (((ListenerUtil.mutListener.listen(7681) ? (imageHeight % 2) : (ListenerUtil.mutListener.listen(7680) ? (imageHeight * 2) : (ListenerUtil.mutListener.listen(7679) ? (imageHeight - 2) : (ListenerUtil.mutListener.listen(7678) ? (imageHeight + 2) : (imageHeight / 2)))))) * ((ListenerUtil.mutListener.listen(7685) ? (progressView.getHeight() % 2) : (ListenerUtil.mutListener.listen(7684) ? (progressView.getHeight() * 2) : (ListenerUtil.mutListener.listen(7683) ? (progressView.getHeight() - 2) : (ListenerUtil.mutListener.listen(7682) ? (progressView.getHeight() + 2) : (progressView.getHeight() / 2))))))) : (ListenerUtil.mutListener.listen(7686) ? (((ListenerUtil.mutListener.listen(7681) ? (imageHeight % 2) : (ListenerUtil.mutListener.listen(7680) ? (imageHeight * 2) : (ListenerUtil.mutListener.listen(7679) ? (imageHeight - 2) : (ListenerUtil.mutListener.listen(7678) ? (imageHeight + 2) : (imageHeight / 2)))))) + ((ListenerUtil.mutListener.listen(7685) ? (progressView.getHeight() % 2) : (ListenerUtil.mutListener.listen(7684) ? (progressView.getHeight() * 2) : (ListenerUtil.mutListener.listen(7683) ? (progressView.getHeight() - 2) : (ListenerUtil.mutListener.listen(7682) ? (progressView.getHeight() + 2) : (progressView.getHeight() / 2))))))) : (((ListenerUtil.mutListener.listen(7681) ? (imageHeight % 2) : (ListenerUtil.mutListener.listen(7680) ? (imageHeight * 2) : (ListenerUtil.mutListener.listen(7679) ? (imageHeight - 2) : (ListenerUtil.mutListener.listen(7678) ? (imageHeight + 2) : (imageHeight / 2)))))) - ((ListenerUtil.mutListener.listen(7685) ? (progressView.getHeight() % 2) : (ListenerUtil.mutListener.listen(7684) ? (progressView.getHeight() * 2) : (ListenerUtil.mutListener.listen(7683) ? (progressView.getHeight() - 2) : (ListenerUtil.mutListener.listen(7682) ? (progressView.getHeight() + 2) : (progressView.getHeight() / 2)))))))))));
        if (!ListenerUtil.mutListener.listen(7690)) {
            params.setMargins(0, topMargin, 0, 0);
        }
        // set the height of the gradient scrim that appears atop the image
        int toolbarHeight = DisplayUtils.getActionBarHeight(this);
        ImageView imgScrim = findViewById(R.id.image_gradient_scrim);
        if (!ListenerUtil.mutListener.listen(7695)) {
            imgScrim.getLayoutParams().height = (ListenerUtil.mutListener.listen(7694) ? (toolbarHeight % 3) : (ListenerUtil.mutListener.listen(7693) ? (toolbarHeight / 3) : (ListenerUtil.mutListener.listen(7692) ? (toolbarHeight - 3) : (ListenerUtil.mutListener.listen(7691) ? (toolbarHeight + 3) : (toolbarHeight * 3)))));
        }
        if (!ListenerUtil.mutListener.listen(7696)) {
            adjustToolbar();
        }
        if (!ListenerUtil.mutListener.listen(7702)) {
            // tap to show full screen view (not supported for documents)
            if (!isDocument()) {
                View.OnClickListener listener = v -> showFullScreen();
                if (!ListenerUtil.mutListener.listen(7697)) {
                    mImageView.setOnClickListener(listener);
                }
                if (!ListenerUtil.mutListener.listen(7698)) {
                    mImagePlay.setOnClickListener(listener);
                }
                if (!ListenerUtil.mutListener.listen(7699)) {
                    mFabView.setOnClickListener(listener);
                }
                if (!ListenerUtil.mutListener.listen(7700)) {
                    mFabView.setOnLongClickListener(view -> {
                        if (view.isHapticFeedbackEnabled()) {
                            view.performHapticFeedback(HapticFeedbackConstants.LONG_PRESS);
                        }
                        Toast.makeText(view.getContext(), R.string.button_preview, Toast.LENGTH_SHORT).show();
                        return true;
                    });
                }
                if (!ListenerUtil.mutListener.listen(7701)) {
                    ViewExtensionsKt.redirectContextClickToLongPressListener(mFabView);
                }
            }
        }
    }

    private boolean isMediaFromEditor() {
        return mEditorImageMetaData != null;
    }

    private void reloadMedia() {
        if (!ListenerUtil.mutListener.listen(7703)) {
            loadMediaWithId(mMedia.getId());
        }
    }

    private boolean loadMediaWithId(int mediaId) {
        MediaModel media = mMediaStore.getMediaWithLocalId(mediaId);
        return loadMedia(media);
    }

    private boolean loadMediaFromEditor() {
        MediaModel media = getMediaModelFromEditorImageMetaData();
        return loadMedia(media);
    }

    private boolean loadMedia(MediaModel media) {
        if (!ListenerUtil.mutListener.listen(7704)) {
            if (media == null) {
                return false;
            }
        }
        if (!ListenerUtil.mutListener.listen(7705)) {
            mMedia = media;
        }
        // try to get a file without parameters so we can more reliably determine media type
        String uriFilePath = !TextUtils.isEmpty(mMedia.getUrl()) ? Uri.parse(mMedia.getUrl()).getPath() : "";
        if (!ListenerUtil.mutListener.listen(7714)) {
            // determine media type up front, default to DOCUMENT if we can't detect it's an image, video, or audio file
            if (MediaUtils.isValidImage(uriFilePath)) {
                if (!ListenerUtil.mutListener.listen(7712)) {
                    mMediaType = MediaType.IMAGE;
                }
                if (!ListenerUtil.mutListener.listen(7713)) {
                    mTitle = getString(R.string.media_title_image_details);
                }
            } else if (mMedia.isVideo()) {
                if (!ListenerUtil.mutListener.listen(7710)) {
                    mMediaType = MediaType.VIDEO;
                }
                if (!ListenerUtil.mutListener.listen(7711)) {
                    mTitle = getString(R.string.media_title_video_details);
                }
            } else if (MediaUtils.isAudio(uriFilePath)) {
                if (!ListenerUtil.mutListener.listen(7708)) {
                    mMediaType = MediaType.AUDIO;
                }
                if (!ListenerUtil.mutListener.listen(7709)) {
                    mTitle = getString(R.string.media_title_audio_details);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(7706)) {
                    mMediaType = MediaType.DOCUMENT;
                }
                if (!ListenerUtil.mutListener.listen(7707)) {
                    mTitle = getString(R.string.media_title_document_details);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(7716)) {
            mImagePlay.setVisibility((ListenerUtil.mutListener.listen(7715) ? (isVideo() && isAudio()) : (isVideo() || isAudio())) ? View.VISIBLE : View.GONE);
        }
        if (!ListenerUtil.mutListener.listen(7719)) {
            findViewById(R.id.edit_alt_text_layout).setVisibility((ListenerUtil.mutListener.listen(7718) ? ((ListenerUtil.mutListener.listen(7717) ? (isVideo() && isAudio()) : (isVideo() || isAudio())) && isDocument()) : ((ListenerUtil.mutListener.listen(7717) ? (isVideo() && isAudio()) : (isVideo() || isAudio())) || isDocument())) ? View.GONE : View.VISIBLE);
        }
        if (!ListenerUtil.mutListener.listen(7720)) {
            showMetaData();
        }
        if (!ListenerUtil.mutListener.listen(7735)) {
            // audio & documents show a placeholder on top of a gradient, otherwise we show a thumbnail
            if ((ListenerUtil.mutListener.listen(7721) ? (isAudio() && isDocument()) : (isAudio() || isDocument()))) {
                int padding = getResources().getDimensionPixelSize(R.dimen.margin_extra_extra_large);
                @DrawableRes
                int imageRes = WPMediaUtils.getPlaceholder(mMedia.getUrl());
                if (!ListenerUtil.mutListener.listen(7728)) {
                    ColorUtils.INSTANCE.setImageResourceWithTint(mImageView, (ListenerUtil.mutListener.listen(7727) ? (imageRes >= 0) : (ListenerUtil.mutListener.listen(7726) ? (imageRes <= 0) : (ListenerUtil.mutListener.listen(7725) ? (imageRes > 0) : (ListenerUtil.mutListener.listen(7724) ? (imageRes < 0) : (ListenerUtil.mutListener.listen(7723) ? (imageRes == 0) : (imageRes != 0)))))) ? imageRes : R.drawable.ic_pages_white_24dp, R.color.neutral_30);
                }
                if (!ListenerUtil.mutListener.listen(7733)) {
                    mImageView.setPadding(padding, (ListenerUtil.mutListener.listen(7732) ? (padding % 2) : (ListenerUtil.mutListener.listen(7731) ? (padding / 2) : (ListenerUtil.mutListener.listen(7730) ? (padding - 2) : (ListenerUtil.mutListener.listen(7729) ? (padding + 2) : (padding * 2))))), padding, padding);
                }
                if (!ListenerUtil.mutListener.listen(7734)) {
                    mImageView.setImageResource(imageRes);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(7722)) {
                    loadImage();
                }
            }
        }
        return true;
    }

    private MediaModel getMediaModelFromEditorImageMetaData() {
        MediaModel mediaModel = new MediaModel();
        if (!ListenerUtil.mutListener.listen(7736)) {
            mediaModel.setUrl(mEditorImageMetaData.getSrc());
        }
        if (!ListenerUtil.mutListener.listen(7737)) {
            mediaModel.setTitle(mEditorImageMetaData.getTitle());
        }
        if (!ListenerUtil.mutListener.listen(7738)) {
            mediaModel.setCaption(mEditorImageMetaData.getCaption());
        }
        if (!ListenerUtil.mutListener.listen(7739)) {
            mediaModel.setAlt(mEditorImageMetaData.getAlt());
        }
        if (!ListenerUtil.mutListener.listen(7741)) {
            if (!TextUtils.isEmpty(mEditorImageMetaData.getSrc())) {
                if (!ListenerUtil.mutListener.listen(7740)) {
                    mediaModel.setFileName(mEditorImageMetaData.getSrc().substring(mEditorImageMetaData.getSrc().lastIndexOf("/") + 1));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(7742)) {
            mediaModel.setFileExtension(org.wordpress.android.fluxc.utils.MediaUtils.getExtension(mEditorImageMetaData.getSrc()));
        }
        if (!ListenerUtil.mutListener.listen(7743)) {
            mediaModel.setWidth(mEditorImageMetaData.getWidthInt());
        }
        if (!ListenerUtil.mutListener.listen(7744)) {
            mediaModel.setHeight(mEditorImageMetaData.getHeightInt());
        }
        return mediaModel;
    }

    @Override
    protected void onResume() {
        if (!ListenerUtil.mutListener.listen(7745)) {
            super.onResume();
        }
        long delayMs = getResources().getInteger(R.integer.fab_animation_delay);
        if (!ListenerUtil.mutListener.listen(7746)) {
            new Handler().postDelayed(() -> {
                if (!isFinishing() && shouldShowFab()) {
                    showFab();
                }
            }, delayMs);
        }
        ActionBar actionBar = getSupportActionBar();
        if (!ListenerUtil.mutListener.listen(7749)) {
            if ((ListenerUtil.mutListener.listen(7747) ? (actionBar != null || !actionBar.isShowing()) : (actionBar != null && !actionBar.isShowing()))) {
                if (!ListenerUtil.mutListener.listen(7748)) {
                    actionBar.show();
                }
            }
        }
    }

    @Override
    protected void onSaveInstanceState(@NotNull Bundle outState) {
        if (!ListenerUtil.mutListener.listen(7750)) {
            super.onSaveInstanceState(outState);
        }
        if (!ListenerUtil.mutListener.listen(7751)) {
            outState.putInt(ARG_MEDIA_LOCAL_ID, mMedia.getId());
        }
        if (!ListenerUtil.mutListener.listen(7752)) {
            outState.putParcelable(ARG_EDITOR_IMAGE_METADATA, mEditorImageMetaData);
        }
        if (!ListenerUtil.mutListener.listen(7754)) {
            if (mDeleteMediaConfirmationDialog != null) {
                if (!ListenerUtil.mutListener.listen(7753)) {
                    outState.putBoolean(ARG_DELETE_MEDIA_DIALOG_VISIBLE, mDeleteMediaConfirmationDialog.isShowing());
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(7756)) {
            if (mSite != null) {
                if (!ListenerUtil.mutListener.listen(7755)) {
                    outState.putSerializable(WordPress.SITE, mSite);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(7758)) {
            if (mMediaIdList != null) {
                if (!ListenerUtil.mutListener.listen(7757)) {
                    outState.putStringArrayList(ARG_ID_LIST, mMediaIdList);
                }
            }
        }
    }

    @Override
    public void onStart() {
        if (!ListenerUtil.mutListener.listen(7759)) {
            super.onStart();
        }
        if (!ListenerUtil.mutListener.listen(7760)) {
            registerReceiver(mDownloadReceiver, new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));
        }
        if (!ListenerUtil.mutListener.listen(7761)) {
            mDispatcher.register(this);
        }
        if (!ListenerUtil.mutListener.listen(7764)) {
            // because we want to keep receiving events while the preview is showing
            if (!mDidRegisterEventBus) {
                if (!ListenerUtil.mutListener.listen(7762)) {
                    EventBus.getDefault().register(this);
                }
                if (!ListenerUtil.mutListener.listen(7763)) {
                    mDidRegisterEventBus = true;
                }
            }
        }
    }

    @Override
    public void onStop() {
        if (!ListenerUtil.mutListener.listen(7765)) {
            unregisterReceiver(mDownloadReceiver);
        }
        if (!ListenerUtil.mutListener.listen(7766)) {
            mDispatcher.unregister(this);
        }
        if (!ListenerUtil.mutListener.listen(7767)) {
            super.onStop();
        }
    }

    @Override
    protected void onDestroy() {
        if (!ListenerUtil.mutListener.listen(7769)) {
            if (mDidRegisterEventBus) {
                if (!ListenerUtil.mutListener.listen(7768)) {
                    EventBus.getDefault().unregister(this);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(7770)) {
            super.onDestroy();
        }
    }

    private void delayedFinishWithError() {
        if (!ListenerUtil.mutListener.listen(7771)) {
            ToastUtils.showToast(this, R.string.error_media_not_found);
        }
        if (!ListenerUtil.mutListener.listen(7772)) {
            new Handler().postDelayed(this::finish, 1500);
        }
    }

    @Override
    public void finish() {
        if (!ListenerUtil.mutListener.listen(7773)) {
            super.finish();
        }
        if (!ListenerUtil.mutListener.listen(7774)) {
            overridePendingTransition(R.anim.do_nothing, R.anim.activity_slide_out_to_bottom);
        }
    }

    /*
     * adjust the toolbar so it doesn't overlap the status bar
     */
    private void adjustToolbar() {
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (!ListenerUtil.mutListener.listen(7782)) {
            if ((ListenerUtil.mutListener.listen(7779) ? (resourceId >= 0) : (ListenerUtil.mutListener.listen(7778) ? (resourceId <= 0) : (ListenerUtil.mutListener.listen(7777) ? (resourceId < 0) : (ListenerUtil.mutListener.listen(7776) ? (resourceId != 0) : (ListenerUtil.mutListener.listen(7775) ? (resourceId == 0) : (resourceId > 0))))))) {
                int statusHeight = getResources().getDimensionPixelSize(resourceId);
                View toolbar = findViewById(R.id.toolbar);
                if (!ListenerUtil.mutListener.listen(7780)) {
                    toolbar.getLayoutParams().height += statusHeight;
                }
                if (!ListenerUtil.mutListener.listen(7781)) {
                    toolbar.setPadding(0, statusHeight, 0, 0);
                }
            }
        }
    }

    private boolean shouldShowFab() {
        // fab only shows for images
        return (ListenerUtil.mutListener.listen(7783) ? (mMedia != null || isImage()) : (mMedia != null && isImage()));
    }

    private void showProgress(boolean show) {
        if (!ListenerUtil.mutListener.listen(7784)) {
            findViewById(R.id.progress).setVisibility(show ? View.VISIBLE : View.GONE);
        }
    }

    @Override
    public void onBackPressed() {
        if (!ListenerUtil.mutListener.listen(7785)) {
            saveChanges();
        }
        if (!ListenerUtil.mutListener.listen(7786)) {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!ListenerUtil.mutListener.listen(7787)) {
            getMenuInflater().inflate(R.menu.media_settings, menu);
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        boolean showSaveMenu = (ListenerUtil.mutListener.listen(7789) ? ((ListenerUtil.mutListener.listen(7788) ? (mSite != null || !mSite.isPrivate()) : (mSite != null && !mSite.isPrivate())) || !isMediaFromEditor()) : ((ListenerUtil.mutListener.listen(7788) ? (mSite != null || !mSite.isPrivate()) : (mSite != null && !mSite.isPrivate())) && !isMediaFromEditor()));
        boolean showShareMenu = (ListenerUtil.mutListener.listen(7791) ? ((ListenerUtil.mutListener.listen(7790) ? (mSite != null || !mSite.isPrivate()) : (mSite != null && !mSite.isPrivate())) || !isMediaFromEditor()) : ((ListenerUtil.mutListener.listen(7790) ? (mSite != null || !mSite.isPrivate()) : (mSite != null && !mSite.isPrivate())) && !isMediaFromEditor()));
        boolean showTrashMenu = (ListenerUtil.mutListener.listen(7792) ? (mSite != null || !isMediaFromEditor()) : (mSite != null && !isMediaFromEditor()));
        boolean showRemoveImage = (ListenerUtil.mutListener.listen(7793) ? (mSite != null || isMediaFromEditor()) : (mSite != null && isMediaFromEditor()));
        MenuItem mnuSave = menu.findItem(R.id.menu_save);
        if (!ListenerUtil.mutListener.listen(7794)) {
            mnuSave.setVisible(showSaveMenu);
        }
        if (!ListenerUtil.mutListener.listen(7800)) {
            mnuSave.setEnabled((ListenerUtil.mutListener.listen(7799) ? (mDownloadId >= 0) : (ListenerUtil.mutListener.listen(7798) ? (mDownloadId <= 0) : (ListenerUtil.mutListener.listen(7797) ? (mDownloadId > 0) : (ListenerUtil.mutListener.listen(7796) ? (mDownloadId < 0) : (ListenerUtil.mutListener.listen(7795) ? (mDownloadId != 0) : (mDownloadId == 0)))))));
        }
        MenuItem mnuShare = menu.findItem(R.id.menu_share);
        if (!ListenerUtil.mutListener.listen(7801)) {
            mnuShare.setVisible(showShareMenu);
        }
        MenuItem mnuTrash = menu.findItem(R.id.menu_trash);
        if (!ListenerUtil.mutListener.listen(7802)) {
            mnuTrash.setVisible(showTrashMenu);
        }
        MenuItem mnuRemove = menu.findItem(R.id.menu_remove_image);
        if (!ListenerUtil.mutListener.listen(7803)) {
            mnuRemove.setVisible(showRemoveImage);
        }
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (!ListenerUtil.mutListener.listen(7809)) {
            if (item.getItemId() == android.R.id.home) {
                if (!ListenerUtil.mutListener.listen(7808)) {
                    onBackPressed();
                }
                return true;
            } else if (item.getItemId() == R.id.menu_save) {
                if (!ListenerUtil.mutListener.listen(7807)) {
                    saveMediaToDevice();
                }
                return true;
            } else if (item.getItemId() == R.id.menu_share) {
                if (!ListenerUtil.mutListener.listen(7806)) {
                    shareMedia();
                }
                return true;
            } else if ((ListenerUtil.mutListener.listen(7804) ? (item.getItemId() == R.id.menu_trash && item.getItemId() == R.id.menu_remove_image) : (item.getItemId() == R.id.menu_trash || item.getItemId() == R.id.menu_remove_image))) {
                if (!ListenerUtil.mutListener.listen(7805)) {
                    deleteMediaWithConfirmation();
                }
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private boolean isImage() {
        return mMediaType == MediaType.IMAGE;
    }

    private boolean isVideo() {
        return mMediaType == MediaType.VIDEO;
    }

    private boolean isAudio() {
        return mMediaType == MediaType.AUDIO;
    }

    private boolean isDocument() {
        return mMediaType == MediaType.DOCUMENT;
    }

    private void showMetaData() {
        if (!ListenerUtil.mutListener.listen(7810)) {
            mTitleView.setText(mMedia.getTitle());
        }
        if (!ListenerUtil.mutListener.listen(7811)) {
            mAltTextView.setText(mMedia.getAlt());
        }
        if (!ListenerUtil.mutListener.listen(7821)) {
            if (isMediaFromEditor()) {
                if (!ListenerUtil.mutListener.listen(7815)) {
                    mLinkView.setText(mEditorImageMetaData.getLinkUrl());
                }
                if (!ListenerUtil.mutListener.listen(7816)) {
                    mLinkTargetNewWindowView.setChecked(mEditorImageMetaData.isLinkTargetBlank());
                }
                if (!ListenerUtil.mutListener.listen(7817)) {
                    findViewById(R.id.edit_description_container).setVisibility(View.GONE);
                }
                if (!ListenerUtil.mutListener.listen(7818)) {
                    findViewById(R.id.divider_dimensions).setVisibility(View.GONE);
                }
                if (!ListenerUtil.mutListener.listen(7819)) {
                    setupAlignmentSpinner();
                }
                if (!ListenerUtil.mutListener.listen(7820)) {
                    setupImageSizeSeekBar();
                }
            } else {
                if (!ListenerUtil.mutListener.listen(7812)) {
                    mDescriptionView.setText(mMedia.getDescription());
                }
                if (!ListenerUtil.mutListener.listen(7813)) {
                    findViewById(R.id.media_customisation_options).setVisibility(View.GONE);
                }
                if (!ListenerUtil.mutListener.listen(7814)) {
                    findViewById(R.id.edit_link_container).setVisibility(View.GONE);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(7822)) {
            mCaptionView.setText(mMedia.getCaption());
        }
        TextView txtUrl = findViewById(R.id.text_url);
        if (!ListenerUtil.mutListener.listen(7823)) {
            txtUrl.setText(mMedia.getUrl());
        }
        TextView txtFilename = findViewById(R.id.text_filename);
        if (!ListenerUtil.mutListener.listen(7824)) {
            txtFilename.setText(mMedia.getFileName());
        }
        TextView txtFileType = findViewById(R.id.text_filetype);
        if (!ListenerUtil.mutListener.listen(7825)) {
            txtFileType.setText(StringUtils.notNullStr(mMedia.getFileExtension()).toUpperCase(Locale.ROOT));
        }
        if (!ListenerUtil.mutListener.listen(7826)) {
            showImageDimensions(mMedia.getWidth(), mMedia.getHeight());
        }
        String uploadDate = null;
        if (!ListenerUtil.mutListener.listen(7829)) {
            if (mMedia.getUploadDate() != null) {
                Date date = DateTimeUtils.dateFromIso8601(mMedia.getUploadDate());
                if (!ListenerUtil.mutListener.listen(7828)) {
                    if (date != null) {
                        if (!ListenerUtil.mutListener.listen(7827)) {
                            uploadDate = SimpleDateFormat.getDateInstance().format(date);
                        }
                    }
                }
            }
        }
        TextView txtUploadDate = findViewById(R.id.text_upload_date);
        TextView txtUploadDateLabel = findViewById(R.id.text_upload_date_label);
        if (!ListenerUtil.mutListener.listen(7835)) {
            if (uploadDate != null) {
                if (!ListenerUtil.mutListener.listen(7832)) {
                    txtUploadDate.setVisibility(View.VISIBLE);
                }
                if (!ListenerUtil.mutListener.listen(7833)) {
                    txtUploadDateLabel.setVisibility(View.VISIBLE);
                }
                if (!ListenerUtil.mutListener.listen(7834)) {
                    txtUploadDate.setText(uploadDate);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(7830)) {
                    txtUploadDate.setVisibility(View.GONE);
                }
                if (!ListenerUtil.mutListener.listen(7831)) {
                    txtUploadDateLabel.setVisibility(View.GONE);
                }
            }
        }
        TextView txtDuration = findViewById(R.id.text_duration);
        TextView txtDurationLabel = findViewById(R.id.text_duration_label);
        if (!ListenerUtil.mutListener.listen(7847)) {
            if ((ListenerUtil.mutListener.listen(7840) ? (mMedia.getLength() >= 0) : (ListenerUtil.mutListener.listen(7839) ? (mMedia.getLength() <= 0) : (ListenerUtil.mutListener.listen(7838) ? (mMedia.getLength() < 0) : (ListenerUtil.mutListener.listen(7837) ? (mMedia.getLength() != 0) : (ListenerUtil.mutListener.listen(7836) ? (mMedia.getLength() == 0) : (mMedia.getLength() > 0))))))) {
                if (!ListenerUtil.mutListener.listen(7844)) {
                    txtDuration.setVisibility(View.VISIBLE);
                }
                if (!ListenerUtil.mutListener.listen(7845)) {
                    txtDurationLabel.setVisibility(View.VISIBLE);
                }
                if (!ListenerUtil.mutListener.listen(7846)) {
                    txtDuration.setText(DateUtils.formatElapsedTime(mMedia.getLength()));
                }
            } else {
                if (!ListenerUtil.mutListener.listen(7841)) {
                    txtDuration.setVisibility(View.GONE);
                }
                if (!ListenerUtil.mutListener.listen(7842)) {
                    txtDurationLabel.setVisibility(View.GONE);
                }
                if (!ListenerUtil.mutListener.listen(7843)) {
                    findViewById(R.id.divider_duration).setVisibility(View.GONE);
                }
            }
        }
        boolean hasUrl = !TextUtils.isEmpty(mMedia.getUrl());
        View txtCopyUrl = findViewById(R.id.text_copy_url);
        if (!ListenerUtil.mutListener.listen(7848)) {
            txtCopyUrl.setVisibility(hasUrl ? View.VISIBLE : View.GONE);
        }
        if (!ListenerUtil.mutListener.listen(7850)) {
            if (hasUrl) {
                if (!ListenerUtil.mutListener.listen(7849)) {
                    txtCopyUrl.setOnClickListener(v -> copyMediaUrlToClipboard());
                }
            }
        }
    }

    /**
     * Initialize the image width SeekBar and accompanying EditText
     */
    private void setupImageSizeSeekBar() {
        if (!ListenerUtil.mutListener.listen(7851)) {
            // in this case we will default to the full size
            mImageSize = MediaSettingsImageSize.fromCssClass(this, mEditorImageMetaData.getSize());
        }
        if (!ListenerUtil.mutListener.listen(7856)) {
            mImageSizeSeekBarView.setMax((ListenerUtil.mutListener.listen(7855) ? (MediaSettingsImageSize.values().length % 1) : (ListenerUtil.mutListener.listen(7854) ? (MediaSettingsImageSize.values().length / 1) : (ListenerUtil.mutListener.listen(7853) ? (MediaSettingsImageSize.values().length * 1) : (ListenerUtil.mutListener.listen(7852) ? (MediaSettingsImageSize.values().length + 1) : (MediaSettingsImageSize.values().length - 1))))));
        }
        if (!ListenerUtil.mutListener.listen(7857)) {
            mImageSizeSeekBarView.setProgress(Arrays.asList(MediaSettingsImageSize.values()).indexOf(mImageSize));
        }
        if (!ListenerUtil.mutListener.listen(7858)) {
            mImageSizeView.setText(mImageSize.getLabel());
        }
        if (!ListenerUtil.mutListener.listen(7861)) {
            mImageSizeSeekBarView.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {
                }

                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    if (!ListenerUtil.mutListener.listen(7859)) {
                        mImageSize = MediaSettingsImageSize.values()[progress];
                    }
                    if (!ListenerUtil.mutListener.listen(7860)) {
                        mImageSizeView.setText(mImageSize.getLabel());
                    }
                }
            });
        }
    }

    private void showImageDimensions(int width, int height) {
        TextView txtDimensions = findViewById(R.id.text_image_dimensions);
        TextView txtDimensionsLabel = findViewById(R.id.text_image_dimensions_label);
        if (!ListenerUtil.mutListener.listen(7880)) {
            if ((ListenerUtil.mutListener.listen(7872) ? ((ListenerUtil.mutListener.listen(7866) ? (width >= 0) : (ListenerUtil.mutListener.listen(7865) ? (width <= 0) : (ListenerUtil.mutListener.listen(7864) ? (width < 0) : (ListenerUtil.mutListener.listen(7863) ? (width != 0) : (ListenerUtil.mutListener.listen(7862) ? (width == 0) : (width > 0)))))) || (ListenerUtil.mutListener.listen(7871) ? (height >= 0) : (ListenerUtil.mutListener.listen(7870) ? (height <= 0) : (ListenerUtil.mutListener.listen(7869) ? (height < 0) : (ListenerUtil.mutListener.listen(7868) ? (height != 0) : (ListenerUtil.mutListener.listen(7867) ? (height == 0) : (height > 0))))))) : ((ListenerUtil.mutListener.listen(7866) ? (width >= 0) : (ListenerUtil.mutListener.listen(7865) ? (width <= 0) : (ListenerUtil.mutListener.listen(7864) ? (width < 0) : (ListenerUtil.mutListener.listen(7863) ? (width != 0) : (ListenerUtil.mutListener.listen(7862) ? (width == 0) : (width > 0)))))) && (ListenerUtil.mutListener.listen(7871) ? (height >= 0) : (ListenerUtil.mutListener.listen(7870) ? (height <= 0) : (ListenerUtil.mutListener.listen(7869) ? (height < 0) : (ListenerUtil.mutListener.listen(7868) ? (height != 0) : (ListenerUtil.mutListener.listen(7867) ? (height == 0) : (height > 0))))))))) {
                if (!ListenerUtil.mutListener.listen(7876)) {
                    txtDimensions.setVisibility(View.VISIBLE);
                }
                if (!ListenerUtil.mutListener.listen(7877)) {
                    txtDimensionsLabel.setVisibility(View.VISIBLE);
                }
                if (!ListenerUtil.mutListener.listen(7878)) {
                    txtDimensionsLabel.setText(isVideo() ? R.string.media_edit_video_dimensions_caption : R.string.media_edit_image_dimensions_caption);
                }
                String dimens = width + " x " + height;
                if (!ListenerUtil.mutListener.listen(7879)) {
                    txtDimensions.setText(dimens);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(7873)) {
                    txtDimensions.setVisibility(View.GONE);
                }
                if (!ListenerUtil.mutListener.listen(7874)) {
                    txtDimensionsLabel.setVisibility(View.GONE);
                }
                if (!ListenerUtil.mutListener.listen(7875)) {
                    findViewById(R.id.divider_dimensions).setVisibility(View.GONE);
                }
            }
        }
    }

    private void hideImageDimensions() {
        if (!ListenerUtil.mutListener.listen(7881)) {
            findViewById(R.id.text_image_dimensions).setVisibility(View.GONE);
        }
        if (!ListenerUtil.mutListener.listen(7882)) {
            findViewById(R.id.text_image_dimensions_label).setVisibility(View.GONE);
        }
        if (!ListenerUtil.mutListener.listen(7883)) {
            findViewById(R.id.divider_dimensions).setVisibility(View.GONE);
        }
        if (!ListenerUtil.mutListener.listen(7885)) {
            // Hide file type divider too if duration is hidden (i.e. media length is zero) to remove bottom divider.
            if (mMedia.getLength() == 0) {
                if (!ListenerUtil.mutListener.listen(7884)) {
                    findViewById(R.id.divider_filetype).setVisibility(View.GONE);
                }
            }
        }
    }

    /**
     * Initialize the image alignment spinner
     */
    private void setupAlignmentSpinner() {
        String alignment = mEditorImageMetaData.getAlign();
        if (!ListenerUtil.mutListener.listen(7886)) {
            mAlignmentKeyArray = getResources().getStringArray(R.array.alignment_key_array);
        }
        int alignmentIndex = Arrays.asList(mAlignmentKeyArray).indexOf(alignment);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.alignment_array, R.layout.media_settings_alignment_spinner_item);
        if (!ListenerUtil.mutListener.listen(7887)) {
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        }
        if (!ListenerUtil.mutListener.listen(7888)) {
            mAlignmentSpinnerView.setAdapter(adapter);
        }
        if (!ListenerUtil.mutListener.listen(7894)) {
            mAlignmentSpinnerView.setSelection((ListenerUtil.mutListener.listen(7893) ? (alignmentIndex >= -1) : (ListenerUtil.mutListener.listen(7892) ? (alignmentIndex <= -1) : (ListenerUtil.mutListener.listen(7891) ? (alignmentIndex > -1) : (ListenerUtil.mutListener.listen(7890) ? (alignmentIndex < -1) : (ListenerUtil.mutListener.listen(7889) ? (alignmentIndex != -1) : (alignmentIndex == -1)))))) ? 0 : alignmentIndex);
        }
    }

    /*
     * loads and displays a remote or local image
     */
    private void loadImage() {
        int width = DisplayUtils.getWindowPixelWidth(this);
        int height = DisplayUtils.getWindowPixelHeight(this);
        int size = Math.max(width, height);
        String mediaUri;
        if (isVideo()) {
            mediaUri = mMedia.getThumbnailUrl();
        } else {
            mediaUri = mMedia.getUrl();
        }
        if (!ListenerUtil.mutListener.listen(7898)) {
            if (TextUtils.isEmpty(mediaUri)) {
                if (!ListenerUtil.mutListener.listen(7897)) {
                    if (isVideo()) {
                        if (!ListenerUtil.mutListener.listen(7896)) {
                            downloadVideoThumbnail();
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(7895)) {
                            ToastUtils.showToast(this, R.string.error_media_load);
                        }
                    }
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(7899)) {
            showProgress(true);
        }
        String imageUrl = mediaUri;
        if (!ListenerUtil.mutListener.listen(7901)) {
            if (SiteUtils.isPhotonCapable(mSite)) {
                if (!ListenerUtil.mutListener.listen(7900)) {
                    imageUrl = PhotonUtils.getPhotonImageUrl(mediaUri, size, 0, mSite.isPrivateWPComAtomic());
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(7913)) {
            mImageManager.loadWithResultListener(mImageView, ImageType.IMAGE, imageUrl, ScaleType.CENTER, null, new RequestListener<Drawable>() {

                @Override
                public void onResourceReady(@NotNull Drawable resource, @Nullable Object model) {
                    if (!ListenerUtil.mutListener.listen(7905)) {
                        if (!isFinishing()) {
                            if (!ListenerUtil.mutListener.listen(7902)) {
                                showProgress(false);
                            }
                            if (!ListenerUtil.mutListener.listen(7904)) {
                                if (isMediaFromEditor()) {
                                    if (!ListenerUtil.mutListener.listen(7903)) {
                                        hideImageDimensions();
                                    }
                                }
                            }
                        }
                    }
                }

                @Override
                public void onLoadFailed(@Nullable Exception e, @Nullable Object model) {
                    if (!ListenerUtil.mutListener.listen(7912)) {
                        if (!isFinishing()) {
                            if (!ListenerUtil.mutListener.listen(7907)) {
                                if (e != null) {
                                    if (!ListenerUtil.mutListener.listen(7906)) {
                                        AppLog.e(T.MEDIA, e);
                                    }
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(7908)) {
                                showProgress(false);
                            }
                            if (!ListenerUtil.mutListener.listen(7911)) {
                                if (isVideo()) {
                                    if (!ListenerUtil.mutListener.listen(7910)) {
                                        // let's show a toast but let the user edit the media settings!
                                        ToastUtils.showToast(MediaSettingsActivity.this, R.string.error_media_thumbnail_not_loaded);
                                    }
                                } else {
                                    if (!ListenerUtil.mutListener.listen(7909)) {
                                        delayedFinishWithError();
                                    }
                                }
                            }
                        }
                    }
                }
            });
        }
    }

    /*
     * downloads and displays the thumbnail for a video that doesn't already have a thumbnail assigned (seen most
     * often with .org and JP sites)
     */
    private void downloadVideoThumbnail() {
        if (!ListenerUtil.mutListener.listen(7916)) {
            new Thread() {

                @Override
                public void run() {
                    int width = DisplayUtils.getWindowPixelWidth(MediaSettingsActivity.this);
                    final Bitmap thumb = ImageUtils.getVideoFrameFromVideo(mMedia.getUrl(), width, mAuthenticationUtils.getAuthHeaders(mMedia.getUrl()));
                    if (!ListenerUtil.mutListener.listen(7915)) {
                        if (thumb != null) {
                            if (!ListenerUtil.mutListener.listen(7914)) {
                                runOnUiThread(() -> {
                                    if (!isFinishing()) {
                                        WordPress.getBitmapCache().put(mMedia.getUrl(), thumb);
                                        mImageView.setImageBitmap(thumb);
                                    }
                                });
                            }
                        }
                    }
                }
            }.start();
        }
    }

    private void showFullScreen() {
        if (!ListenerUtil.mutListener.listen(7917)) {
            saveChanges();
        }
        if (!ListenerUtil.mutListener.listen(7918)) {
            hideFab();
        }
        if (!ListenerUtil.mutListener.listen(7919)) {
            // show fullscreen preview after a brief delay so fab & actionBar animations don't stutter
            new Handler().postDelayed(() -> {
                if (isMediaFromEditor()) {
                    MediaPreviewActivity.showPreview(MediaSettingsActivity.this, mSite, mEditorImageMetaData.getSrc());
                } else {
                    MediaPreviewActivity.showPreview(MediaSettingsActivity.this, mSite, mMedia, mMediaIdList);
                }
            }, 200);
        }
    }

    private void showFab() {
        if (!ListenerUtil.mutListener.listen(7921)) {
            if (mFabView.getVisibility() != View.VISIBLE) {
                if (!ListenerUtil.mutListener.listen(7920)) {
                    AniUtils.scaleIn(mFabView, AniUtils.Duration.SHORT);
                }
            }
        }
    }

    private void hideFab() {
        if (!ListenerUtil.mutListener.listen(7923)) {
            if (mFabView.getVisibility() == View.VISIBLE) {
                if (!ListenerUtil.mutListener.listen(7922)) {
                    AniUtils.scaleOut(mFabView, AniUtils.Duration.SHORT);
                }
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (!ListenerUtil.mutListener.listen(7924)) {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
        boolean allGranted = WPPermissionUtils.setPermissionListAsked(this, requestCode, permissions, grantResults, true);
        if (!ListenerUtil.mutListener.listen(7927)) {
            if ((ListenerUtil.mutListener.listen(7925) ? (allGranted || requestCode == WPPermissionUtils.MEDIA_PREVIEW_PERMISSION_REQUEST_CODE) : (allGranted && requestCode == WPPermissionUtils.MEDIA_PREVIEW_PERMISSION_REQUEST_CODE))) {
                if (!ListenerUtil.mutListener.listen(7926)) {
                    saveMediaToDevice();
                }
            }
        }
    }

    /*
     * receives download completion broadcasts from the DownloadManager
     */
    private final BroadcastReceiver mDownloadReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            long thisId = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1);
            if (!ListenerUtil.mutListener.listen(7936)) {
                if (thisId == mDownloadId) {
                    DownloadManager.Query query = new DownloadManager.Query();
                    if (!ListenerUtil.mutListener.listen(7928)) {
                        query.setFilterById(mDownloadId);
                    }
                    DownloadManager dm = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
                    Cursor cursor = dm.query(query);
                    if (!ListenerUtil.mutListener.listen(7933)) {
                        if ((ListenerUtil.mutListener.listen(7929) ? (cursor != null || cursor.moveToFirst()) : (cursor != null && cursor.moveToFirst()))) {
                            // meaning of `reason` depends on the value of COLUMN_STATUS
                            int reason = cursor.getInt(cursor.getColumnIndexOrThrow(DownloadManager.COLUMN_REASON));
                            int status = cursor.getInt(cursor.getColumnIndexOrThrow(DownloadManager.COLUMN_STATUS));
                            if (!ListenerUtil.mutListener.listen(7932)) {
                                if (status == DownloadManager.STATUS_FAILED) {
                                    if (!ListenerUtil.mutListener.listen(7930)) {
                                        ToastUtils.showToast(MediaSettingsActivity.this, R.string.error_media_save);
                                    }
                                    if (!ListenerUtil.mutListener.listen(7931)) {
                                        // Otherwise, it will hold one of the ERROR_* constants
                                        AppLog.e(AppLog.T.MEDIA, "MediaSettingsActivity > save > STATUS_FAILED - reason: " + reason);
                                    }
                                }
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(7934)) {
                        mDownloadId = 0;
                    }
                    if (!ListenerUtil.mutListener.listen(7935)) {
                        invalidateOptionsMenu();
                    }
                }
            }
        }
    };

    private void saveChanges() {
        if (!ListenerUtil.mutListener.listen(7937)) {
            if (isFinishing()) {
                return;
            }
        }
        String thisTitle = EditTextUtils.getText(mTitleView);
        String thisCaption = EditTextUtils.getText(mCaptionView);
        String thisAltText = EditTextUtils.getText(mAltTextView);
        String thisDescription = EditTextUtils.getText(mDescriptionView);
        if (!ListenerUtil.mutListener.listen(7971)) {
            if (!isMediaFromEditor()) {
                MediaModel media = mMediaStore.getMediaWithLocalId(mMedia.getId());
                if (!ListenerUtil.mutListener.listen(7960)) {
                    if (media == null) {
                        if (!ListenerUtil.mutListener.listen(7958)) {
                            AppLog.w(AppLog.T.MEDIA, "MediaSettingsActivity > Cannot save null media");
                        }
                        if (!ListenerUtil.mutListener.listen(7959)) {
                            ToastUtils.showToast(this, R.string.media_edit_failure);
                        }
                        return;
                    }
                }
                boolean hasChanged = (ListenerUtil.mutListener.listen(7963) ? ((ListenerUtil.mutListener.listen(7962) ? ((ListenerUtil.mutListener.listen(7961) ? (!StringUtils.equals(media.getTitle(), thisTitle) && !StringUtils.equals(media.getCaption(), thisCaption)) : (!StringUtils.equals(media.getTitle(), thisTitle) || !StringUtils.equals(media.getCaption(), thisCaption))) && !StringUtils.equals(media.getAlt(), thisAltText)) : ((ListenerUtil.mutListener.listen(7961) ? (!StringUtils.equals(media.getTitle(), thisTitle) && !StringUtils.equals(media.getCaption(), thisCaption)) : (!StringUtils.equals(media.getTitle(), thisTitle) || !StringUtils.equals(media.getCaption(), thisCaption))) || !StringUtils.equals(media.getAlt(), thisAltText))) && !StringUtils.equals(media.getDescription(), thisDescription)) : ((ListenerUtil.mutListener.listen(7962) ? ((ListenerUtil.mutListener.listen(7961) ? (!StringUtils.equals(media.getTitle(), thisTitle) && !StringUtils.equals(media.getCaption(), thisCaption)) : (!StringUtils.equals(media.getTitle(), thisTitle) || !StringUtils.equals(media.getCaption(), thisCaption))) && !StringUtils.equals(media.getAlt(), thisAltText)) : ((ListenerUtil.mutListener.listen(7961) ? (!StringUtils.equals(media.getTitle(), thisTitle) && !StringUtils.equals(media.getCaption(), thisCaption)) : (!StringUtils.equals(media.getTitle(), thisTitle) || !StringUtils.equals(media.getCaption(), thisCaption))) || !StringUtils.equals(media.getAlt(), thisAltText))) || !StringUtils.equals(media.getDescription(), thisDescription)));
                if (!ListenerUtil.mutListener.listen(7970)) {
                    if (hasChanged) {
                        if (!ListenerUtil.mutListener.listen(7964)) {
                            AppLog.d(AppLog.T.MEDIA, "MediaSettingsActivity > Saving changes");
                        }
                        if (!ListenerUtil.mutListener.listen(7965)) {
                            media.setTitle(thisTitle);
                        }
                        if (!ListenerUtil.mutListener.listen(7966)) {
                            media.setCaption(thisCaption);
                        }
                        if (!ListenerUtil.mutListener.listen(7967)) {
                            media.setAlt(thisAltText);
                        }
                        if (!ListenerUtil.mutListener.listen(7968)) {
                            media.setDescription(thisDescription);
                        }
                        if (!ListenerUtil.mutListener.listen(7969)) {
                            mDispatcher.dispatch(MediaActionBuilder.newPushMediaAction(new MediaPayload(mSite, media)));
                        }
                    }
                }
            } else {
                String alignment = mAlignmentKeyArray[mAlignmentSpinnerView.getSelectedItemPosition()];
                String size = getString(mImageSize.getCssClass());
                String linkUrl = EditTextUtils.getText(mLinkView);
                boolean linkTargetBlank = mLinkTargetNewWindowView.isChecked();
                boolean hasSizeChanged = !StringUtils.equals(mEditorImageMetaData.getSize(), size);
                boolean hasChanged = (ListenerUtil.mutListener.listen(7943) ? ((ListenerUtil.mutListener.listen(7942) ? ((ListenerUtil.mutListener.listen(7941) ? ((ListenerUtil.mutListener.listen(7940) ? ((ListenerUtil.mutListener.listen(7939) ? ((ListenerUtil.mutListener.listen(7938) ? (hasSizeChanged && !StringUtils.equals(mEditorImageMetaData.getTitle(), thisTitle)) : (hasSizeChanged || !StringUtils.equals(mEditorImageMetaData.getTitle(), thisTitle))) && !StringUtils.equals(mEditorImageMetaData.getAlt(), thisAltText)) : ((ListenerUtil.mutListener.listen(7938) ? (hasSizeChanged && !StringUtils.equals(mEditorImageMetaData.getTitle(), thisTitle)) : (hasSizeChanged || !StringUtils.equals(mEditorImageMetaData.getTitle(), thisTitle))) || !StringUtils.equals(mEditorImageMetaData.getAlt(), thisAltText))) && !StringUtils.equals(mEditorImageMetaData.getCaption(), thisCaption)) : ((ListenerUtil.mutListener.listen(7939) ? ((ListenerUtil.mutListener.listen(7938) ? (hasSizeChanged && !StringUtils.equals(mEditorImageMetaData.getTitle(), thisTitle)) : (hasSizeChanged || !StringUtils.equals(mEditorImageMetaData.getTitle(), thisTitle))) && !StringUtils.equals(mEditorImageMetaData.getAlt(), thisAltText)) : ((ListenerUtil.mutListener.listen(7938) ? (hasSizeChanged && !StringUtils.equals(mEditorImageMetaData.getTitle(), thisTitle)) : (hasSizeChanged || !StringUtils.equals(mEditorImageMetaData.getTitle(), thisTitle))) || !StringUtils.equals(mEditorImageMetaData.getAlt(), thisAltText))) || !StringUtils.equals(mEditorImageMetaData.getCaption(), thisCaption))) && !StringUtils.equals(mEditorImageMetaData.getAlign(), alignment)) : ((ListenerUtil.mutListener.listen(7940) ? ((ListenerUtil.mutListener.listen(7939) ? ((ListenerUtil.mutListener.listen(7938) ? (hasSizeChanged && !StringUtils.equals(mEditorImageMetaData.getTitle(), thisTitle)) : (hasSizeChanged || !StringUtils.equals(mEditorImageMetaData.getTitle(), thisTitle))) && !StringUtils.equals(mEditorImageMetaData.getAlt(), thisAltText)) : ((ListenerUtil.mutListener.listen(7938) ? (hasSizeChanged && !StringUtils.equals(mEditorImageMetaData.getTitle(), thisTitle)) : (hasSizeChanged || !StringUtils.equals(mEditorImageMetaData.getTitle(), thisTitle))) || !StringUtils.equals(mEditorImageMetaData.getAlt(), thisAltText))) && !StringUtils.equals(mEditorImageMetaData.getCaption(), thisCaption)) : ((ListenerUtil.mutListener.listen(7939) ? ((ListenerUtil.mutListener.listen(7938) ? (hasSizeChanged && !StringUtils.equals(mEditorImageMetaData.getTitle(), thisTitle)) : (hasSizeChanged || !StringUtils.equals(mEditorImageMetaData.getTitle(), thisTitle))) && !StringUtils.equals(mEditorImageMetaData.getAlt(), thisAltText)) : ((ListenerUtil.mutListener.listen(7938) ? (hasSizeChanged && !StringUtils.equals(mEditorImageMetaData.getTitle(), thisTitle)) : (hasSizeChanged || !StringUtils.equals(mEditorImageMetaData.getTitle(), thisTitle))) || !StringUtils.equals(mEditorImageMetaData.getAlt(), thisAltText))) || !StringUtils.equals(mEditorImageMetaData.getCaption(), thisCaption))) || !StringUtils.equals(mEditorImageMetaData.getAlign(), alignment))) && !StringUtils.equals(mEditorImageMetaData.getLinkUrl(), linkUrl)) : ((ListenerUtil.mutListener.listen(7941) ? ((ListenerUtil.mutListener.listen(7940) ? ((ListenerUtil.mutListener.listen(7939) ? ((ListenerUtil.mutListener.listen(7938) ? (hasSizeChanged && !StringUtils.equals(mEditorImageMetaData.getTitle(), thisTitle)) : (hasSizeChanged || !StringUtils.equals(mEditorImageMetaData.getTitle(), thisTitle))) && !StringUtils.equals(mEditorImageMetaData.getAlt(), thisAltText)) : ((ListenerUtil.mutListener.listen(7938) ? (hasSizeChanged && !StringUtils.equals(mEditorImageMetaData.getTitle(), thisTitle)) : (hasSizeChanged || !StringUtils.equals(mEditorImageMetaData.getTitle(), thisTitle))) || !StringUtils.equals(mEditorImageMetaData.getAlt(), thisAltText))) && !StringUtils.equals(mEditorImageMetaData.getCaption(), thisCaption)) : ((ListenerUtil.mutListener.listen(7939) ? ((ListenerUtil.mutListener.listen(7938) ? (hasSizeChanged && !StringUtils.equals(mEditorImageMetaData.getTitle(), thisTitle)) : (hasSizeChanged || !StringUtils.equals(mEditorImageMetaData.getTitle(), thisTitle))) && !StringUtils.equals(mEditorImageMetaData.getAlt(), thisAltText)) : ((ListenerUtil.mutListener.listen(7938) ? (hasSizeChanged && !StringUtils.equals(mEditorImageMetaData.getTitle(), thisTitle)) : (hasSizeChanged || !StringUtils.equals(mEditorImageMetaData.getTitle(), thisTitle))) || !StringUtils.equals(mEditorImageMetaData.getAlt(), thisAltText))) || !StringUtils.equals(mEditorImageMetaData.getCaption(), thisCaption))) && !StringUtils.equals(mEditorImageMetaData.getAlign(), alignment)) : ((ListenerUtil.mutListener.listen(7940) ? ((ListenerUtil.mutListener.listen(7939) ? ((ListenerUtil.mutListener.listen(7938) ? (hasSizeChanged && !StringUtils.equals(mEditorImageMetaData.getTitle(), thisTitle)) : (hasSizeChanged || !StringUtils.equals(mEditorImageMetaData.getTitle(), thisTitle))) && !StringUtils.equals(mEditorImageMetaData.getAlt(), thisAltText)) : ((ListenerUtil.mutListener.listen(7938) ? (hasSizeChanged && !StringUtils.equals(mEditorImageMetaData.getTitle(), thisTitle)) : (hasSizeChanged || !StringUtils.equals(mEditorImageMetaData.getTitle(), thisTitle))) || !StringUtils.equals(mEditorImageMetaData.getAlt(), thisAltText))) && !StringUtils.equals(mEditorImageMetaData.getCaption(), thisCaption)) : ((ListenerUtil.mutListener.listen(7939) ? ((ListenerUtil.mutListener.listen(7938) ? (hasSizeChanged && !StringUtils.equals(mEditorImageMetaData.getTitle(), thisTitle)) : (hasSizeChanged || !StringUtils.equals(mEditorImageMetaData.getTitle(), thisTitle))) && !StringUtils.equals(mEditorImageMetaData.getAlt(), thisAltText)) : ((ListenerUtil.mutListener.listen(7938) ? (hasSizeChanged && !StringUtils.equals(mEditorImageMetaData.getTitle(), thisTitle)) : (hasSizeChanged || !StringUtils.equals(mEditorImageMetaData.getTitle(), thisTitle))) || !StringUtils.equals(mEditorImageMetaData.getAlt(), thisAltText))) || !StringUtils.equals(mEditorImageMetaData.getCaption(), thisCaption))) || !StringUtils.equals(mEditorImageMetaData.getAlign(), alignment))) || !StringUtils.equals(mEditorImageMetaData.getLinkUrl(), linkUrl))) && linkTargetBlank != mEditorImageMetaData.isLinkTargetBlank()) : ((ListenerUtil.mutListener.listen(7942) ? ((ListenerUtil.mutListener.listen(7941) ? ((ListenerUtil.mutListener.listen(7940) ? ((ListenerUtil.mutListener.listen(7939) ? ((ListenerUtil.mutListener.listen(7938) ? (hasSizeChanged && !StringUtils.equals(mEditorImageMetaData.getTitle(), thisTitle)) : (hasSizeChanged || !StringUtils.equals(mEditorImageMetaData.getTitle(), thisTitle))) && !StringUtils.equals(mEditorImageMetaData.getAlt(), thisAltText)) : ((ListenerUtil.mutListener.listen(7938) ? (hasSizeChanged && !StringUtils.equals(mEditorImageMetaData.getTitle(), thisTitle)) : (hasSizeChanged || !StringUtils.equals(mEditorImageMetaData.getTitle(), thisTitle))) || !StringUtils.equals(mEditorImageMetaData.getAlt(), thisAltText))) && !StringUtils.equals(mEditorImageMetaData.getCaption(), thisCaption)) : ((ListenerUtil.mutListener.listen(7939) ? ((ListenerUtil.mutListener.listen(7938) ? (hasSizeChanged && !StringUtils.equals(mEditorImageMetaData.getTitle(), thisTitle)) : (hasSizeChanged || !StringUtils.equals(mEditorImageMetaData.getTitle(), thisTitle))) && !StringUtils.equals(mEditorImageMetaData.getAlt(), thisAltText)) : ((ListenerUtil.mutListener.listen(7938) ? (hasSizeChanged && !StringUtils.equals(mEditorImageMetaData.getTitle(), thisTitle)) : (hasSizeChanged || !StringUtils.equals(mEditorImageMetaData.getTitle(), thisTitle))) || !StringUtils.equals(mEditorImageMetaData.getAlt(), thisAltText))) || !StringUtils.equals(mEditorImageMetaData.getCaption(), thisCaption))) && !StringUtils.equals(mEditorImageMetaData.getAlign(), alignment)) : ((ListenerUtil.mutListener.listen(7940) ? ((ListenerUtil.mutListener.listen(7939) ? ((ListenerUtil.mutListener.listen(7938) ? (hasSizeChanged && !StringUtils.equals(mEditorImageMetaData.getTitle(), thisTitle)) : (hasSizeChanged || !StringUtils.equals(mEditorImageMetaData.getTitle(), thisTitle))) && !StringUtils.equals(mEditorImageMetaData.getAlt(), thisAltText)) : ((ListenerUtil.mutListener.listen(7938) ? (hasSizeChanged && !StringUtils.equals(mEditorImageMetaData.getTitle(), thisTitle)) : (hasSizeChanged || !StringUtils.equals(mEditorImageMetaData.getTitle(), thisTitle))) || !StringUtils.equals(mEditorImageMetaData.getAlt(), thisAltText))) && !StringUtils.equals(mEditorImageMetaData.getCaption(), thisCaption)) : ((ListenerUtil.mutListener.listen(7939) ? ((ListenerUtil.mutListener.listen(7938) ? (hasSizeChanged && !StringUtils.equals(mEditorImageMetaData.getTitle(), thisTitle)) : (hasSizeChanged || !StringUtils.equals(mEditorImageMetaData.getTitle(), thisTitle))) && !StringUtils.equals(mEditorImageMetaData.getAlt(), thisAltText)) : ((ListenerUtil.mutListener.listen(7938) ? (hasSizeChanged && !StringUtils.equals(mEditorImageMetaData.getTitle(), thisTitle)) : (hasSizeChanged || !StringUtils.equals(mEditorImageMetaData.getTitle(), thisTitle))) || !StringUtils.equals(mEditorImageMetaData.getAlt(), thisAltText))) || !StringUtils.equals(mEditorImageMetaData.getCaption(), thisCaption))) || !StringUtils.equals(mEditorImageMetaData.getAlign(), alignment))) && !StringUtils.equals(mEditorImageMetaData.getLinkUrl(), linkUrl)) : ((ListenerUtil.mutListener.listen(7941) ? ((ListenerUtil.mutListener.listen(7940) ? ((ListenerUtil.mutListener.listen(7939) ? ((ListenerUtil.mutListener.listen(7938) ? (hasSizeChanged && !StringUtils.equals(mEditorImageMetaData.getTitle(), thisTitle)) : (hasSizeChanged || !StringUtils.equals(mEditorImageMetaData.getTitle(), thisTitle))) && !StringUtils.equals(mEditorImageMetaData.getAlt(), thisAltText)) : ((ListenerUtil.mutListener.listen(7938) ? (hasSizeChanged && !StringUtils.equals(mEditorImageMetaData.getTitle(), thisTitle)) : (hasSizeChanged || !StringUtils.equals(mEditorImageMetaData.getTitle(), thisTitle))) || !StringUtils.equals(mEditorImageMetaData.getAlt(), thisAltText))) && !StringUtils.equals(mEditorImageMetaData.getCaption(), thisCaption)) : ((ListenerUtil.mutListener.listen(7939) ? ((ListenerUtil.mutListener.listen(7938) ? (hasSizeChanged && !StringUtils.equals(mEditorImageMetaData.getTitle(), thisTitle)) : (hasSizeChanged || !StringUtils.equals(mEditorImageMetaData.getTitle(), thisTitle))) && !StringUtils.equals(mEditorImageMetaData.getAlt(), thisAltText)) : ((ListenerUtil.mutListener.listen(7938) ? (hasSizeChanged && !StringUtils.equals(mEditorImageMetaData.getTitle(), thisTitle)) : (hasSizeChanged || !StringUtils.equals(mEditorImageMetaData.getTitle(), thisTitle))) || !StringUtils.equals(mEditorImageMetaData.getAlt(), thisAltText))) || !StringUtils.equals(mEditorImageMetaData.getCaption(), thisCaption))) && !StringUtils.equals(mEditorImageMetaData.getAlign(), alignment)) : ((ListenerUtil.mutListener.listen(7940) ? ((ListenerUtil.mutListener.listen(7939) ? ((ListenerUtil.mutListener.listen(7938) ? (hasSizeChanged && !StringUtils.equals(mEditorImageMetaData.getTitle(), thisTitle)) : (hasSizeChanged || !StringUtils.equals(mEditorImageMetaData.getTitle(), thisTitle))) && !StringUtils.equals(mEditorImageMetaData.getAlt(), thisAltText)) : ((ListenerUtil.mutListener.listen(7938) ? (hasSizeChanged && !StringUtils.equals(mEditorImageMetaData.getTitle(), thisTitle)) : (hasSizeChanged || !StringUtils.equals(mEditorImageMetaData.getTitle(), thisTitle))) || !StringUtils.equals(mEditorImageMetaData.getAlt(), thisAltText))) && !StringUtils.equals(mEditorImageMetaData.getCaption(), thisCaption)) : ((ListenerUtil.mutListener.listen(7939) ? ((ListenerUtil.mutListener.listen(7938) ? (hasSizeChanged && !StringUtils.equals(mEditorImageMetaData.getTitle(), thisTitle)) : (hasSizeChanged || !StringUtils.equals(mEditorImageMetaData.getTitle(), thisTitle))) && !StringUtils.equals(mEditorImageMetaData.getAlt(), thisAltText)) : ((ListenerUtil.mutListener.listen(7938) ? (hasSizeChanged && !StringUtils.equals(mEditorImageMetaData.getTitle(), thisTitle)) : (hasSizeChanged || !StringUtils.equals(mEditorImageMetaData.getTitle(), thisTitle))) || !StringUtils.equals(mEditorImageMetaData.getAlt(), thisAltText))) || !StringUtils.equals(mEditorImageMetaData.getCaption(), thisCaption))) || !StringUtils.equals(mEditorImageMetaData.getAlign(), alignment))) || !StringUtils.equals(mEditorImageMetaData.getLinkUrl(), linkUrl))) || linkTargetBlank != mEditorImageMetaData.isLinkTargetBlank()));
                if (!ListenerUtil.mutListener.listen(7957)) {
                    if (hasChanged) {
                        if (!ListenerUtil.mutListener.listen(7945)) {
                            mEditorImageMetaData.setTitle(thisTitle);
                        }
                        if (!ListenerUtil.mutListener.listen(7946)) {
                            mEditorImageMetaData.setSize(size);
                        }
                        if (!ListenerUtil.mutListener.listen(7947)) {
                            mEditorImageMetaData.setAlt(thisAltText);
                        }
                        if (!ListenerUtil.mutListener.listen(7948)) {
                            mEditorImageMetaData.setAlign(alignment);
                        }
                        if (!ListenerUtil.mutListener.listen(7949)) {
                            mEditorImageMetaData.setCaption(thisCaption);
                        }
                        if (!ListenerUtil.mutListener.listen(7950)) {
                            mEditorImageMetaData.setLinkUrl(linkUrl);
                        }
                        if (!ListenerUtil.mutListener.listen(7951)) {
                            mEditorImageMetaData.setLinkTargetBlank(linkTargetBlank);
                        }
                        if (!ListenerUtil.mutListener.listen(7954)) {
                            // because css image size classes wont have any effect there
                            if ((ListenerUtil.mutListener.listen(7952) ? (!mSite.isWPCom() || hasSizeChanged) : (!mSite.isWPCom() && hasSizeChanged))) {
                                if (!ListenerUtil.mutListener.listen(7953)) {
                                    updateImageSizeParameters();
                                }
                            }
                        }
                        Intent intent = new Intent();
                        if (!ListenerUtil.mutListener.listen(7955)) {
                            intent.putExtra(ARG_EDITOR_IMAGE_METADATA, mEditorImageMetaData);
                        }
                        if (!ListenerUtil.mutListener.listen(7956)) {
                            this.setResult(Activity.RESULT_OK, intent);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(7944)) {
                            this.setResult(Activity.RESULT_CANCELED);
                        }
                    }
                }
            }
        }
    }

    private void updateImageSizeParameters() {
        if (!ListenerUtil.mutListener.listen(7975)) {
            // if caption is empty we can safely remove width and height attributes
            if ((ListenerUtil.mutListener.listen(7972) ? (mImageSize == MediaSettingsImageSize.FULL || TextUtils.isEmpty(mEditorImageMetaData.getCaption())) : (mImageSize == MediaSettingsImageSize.FULL && TextUtils.isEmpty(mEditorImageMetaData.getCaption())))) {
                if (!ListenerUtil.mutListener.listen(7973)) {
                    mEditorImageMetaData.setWidth(null);
                }
                if (!ListenerUtil.mutListener.listen(7974)) {
                    mEditorImageMetaData.setHeight(null);
                }
                return;
            }
        }
        int imageWidth = mEditorImageMetaData.getWidthInt();
        int imageHeight = mEditorImageMetaData.getHeightInt();
        int newImageSize = getResources().getInteger(mImageSize.getSize());
        float aspectRatio = ((ListenerUtil.mutListener.listen(7979) ? ((float) imageWidth % (float) imageHeight) : (ListenerUtil.mutListener.listen(7978) ? ((float) imageWidth * (float) imageHeight) : (ListenerUtil.mutListener.listen(7977) ? ((float) imageWidth - (float) imageHeight) : (ListenerUtil.mutListener.listen(7976) ? ((float) imageWidth + (float) imageHeight) : ((float) imageWidth / (float) imageHeight))))));
        if (!ListenerUtil.mutListener.listen(8004)) {
            if ((ListenerUtil.mutListener.listen(7984) ? (imageWidth >= imageHeight) : (ListenerUtil.mutListener.listen(7983) ? (imageWidth <= imageHeight) : (ListenerUtil.mutListener.listen(7982) ? (imageWidth < imageHeight) : (ListenerUtil.mutListener.listen(7981) ? (imageWidth != imageHeight) : (ListenerUtil.mutListener.listen(7980) ? (imageWidth == imageHeight) : (imageWidth > imageHeight))))))) {
                if (!ListenerUtil.mutListener.listen(8002)) {
                    imageHeight = Math.round((ListenerUtil.mutListener.listen(8001) ? (newImageSize % aspectRatio) : (ListenerUtil.mutListener.listen(8000) ? (newImageSize * aspectRatio) : (ListenerUtil.mutListener.listen(7999) ? (newImageSize - aspectRatio) : (ListenerUtil.mutListener.listen(7998) ? (newImageSize + aspectRatio) : (newImageSize / aspectRatio))))));
                }
                if (!ListenerUtil.mutListener.listen(8003)) {
                    imageWidth = newImageSize;
                }
            } else if ((ListenerUtil.mutListener.listen(7989) ? (imageWidth >= imageHeight) : (ListenerUtil.mutListener.listen(7988) ? (imageWidth <= imageHeight) : (ListenerUtil.mutListener.listen(7987) ? (imageWidth > imageHeight) : (ListenerUtil.mutListener.listen(7986) ? (imageWidth != imageHeight) : (ListenerUtil.mutListener.listen(7985) ? (imageWidth == imageHeight) : (imageWidth < imageHeight))))))) {
                if (!ListenerUtil.mutListener.listen(7996)) {
                    imageWidth = Math.round((ListenerUtil.mutListener.listen(7995) ? (newImageSize % aspectRatio) : (ListenerUtil.mutListener.listen(7994) ? (newImageSize / aspectRatio) : (ListenerUtil.mutListener.listen(7993) ? (newImageSize - aspectRatio) : (ListenerUtil.mutListener.listen(7992) ? (newImageSize + aspectRatio) : (newImageSize * aspectRatio))))));
                }
                if (!ListenerUtil.mutListener.listen(7997)) {
                    imageHeight = newImageSize;
                }
            } else {
                if (!ListenerUtil.mutListener.listen(7990)) {
                    // image is square
                    imageHeight = newImageSize;
                }
                if (!ListenerUtil.mutListener.listen(7991)) {
                    imageWidth = newImageSize;
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(8005)) {
            mEditorImageMetaData.setWidth(Integer.toString(imageWidth));
        }
        if (!ListenerUtil.mutListener.listen(8006)) {
            mEditorImageMetaData.setHeight(Integer.toString(imageHeight));
        }
    }

    /*
     * saves the media to the local device using the Android DownloadManager
     */
    private void saveMediaToDevice() {
        // must request permissions even though they're already defined in the manifest
        String[] permissionList = { Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE };
        if (!ListenerUtil.mutListener.listen(8007)) {
            if (!PermissionUtils.checkAndRequestPermissions(this, WPPermissionUtils.MEDIA_PREVIEW_PERMISSION_REQUEST_CODE, permissionList)) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(8008)) {
            if (!NetworkUtils.checkConnection(this)) {
                return;
            }
        }
        DownloadManager dm = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(mMedia.getUrl()));
        try {
            if (!ListenerUtil.mutListener.listen(8011)) {
                request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, mMedia.getFileName());
            }
        } catch (IllegalStateException error) {
            if (!ListenerUtil.mutListener.listen(8009)) {
                AppLog.e(AppLog.T.MEDIA, error);
            }
            if (!ListenerUtil.mutListener.listen(8010)) {
                ToastUtils.showToast(MediaSettingsActivity.this, R.string.error_media_save);
            }
            return;
        }
        if (!ListenerUtil.mutListener.listen(8012)) {
            request.allowScanningByMediaScanner();
        }
        if (!ListenerUtil.mutListener.listen(8013)) {
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE);
        }
        if (!ListenerUtil.mutListener.listen(8014)) {
            request.addRequestHeader("User-Agent", WordPress.getUserAgent());
        }
        if (!ListenerUtil.mutListener.listen(8015)) {
            mDownloadId = dm.enqueue(request);
        }
        if (!ListenerUtil.mutListener.listen(8016)) {
            invalidateOptionsMenu();
        }
        if (!ListenerUtil.mutListener.listen(8017)) {
            ToastUtils.showToast(this, R.string.media_downloading);
        }
    }

    private void shareMedia() {
        Intent intent = new Intent(Intent.ACTION_SEND);
        if (!ListenerUtil.mutListener.listen(8018)) {
            intent.setType("text/plain");
        }
        if (!ListenerUtil.mutListener.listen(8019)) {
            intent.putExtra(Intent.EXTRA_TEXT, mMedia.getUrl());
        }
        if (!ListenerUtil.mutListener.listen(8022)) {
            if (!TextUtils.isEmpty(mMedia.getTitle())) {
                if (!ListenerUtil.mutListener.listen(8021)) {
                    intent.putExtra(Intent.EXTRA_SUBJECT, mMedia.getTitle());
                }
            } else if (!TextUtils.isEmpty(mMedia.getDescription())) {
                if (!ListenerUtil.mutListener.listen(8020)) {
                    intent.putExtra(Intent.EXTRA_SUBJECT, mMedia.getDescription());
                }
            }
        }
        try {
            if (!ListenerUtil.mutListener.listen(8024)) {
                startActivity(Intent.createChooser(intent, getString(R.string.share_link)));
            }
        } catch (android.content.ActivityNotFoundException ex) {
            if (!ListenerUtil.mutListener.listen(8023)) {
                ToastUtils.showToast(this, R.string.reader_toast_err_share_intent);
            }
        }
    }

    /*
     * Depending on the media source it either removes it from post or deletes it from MediaBrowser
     */
    private void deleteMediaWithConfirmation() {
        if (!ListenerUtil.mutListener.listen(8026)) {
            if (mDeleteMediaConfirmationDialog != null) {
                if (!ListenerUtil.mutListener.listen(8025)) {
                    mDeleteMediaConfirmationDialog.show();
                }
                return;
            }
        }
        @StringRes
        int resId;
        if (isMediaFromEditor()) {
            resId = R.string.confirm_remove_media_image;
        } else if (isVideo()) {
            resId = R.string.confirm_delete_media_video;
        } else {
            resId = R.string.confirm_delete_media_image;
        }
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(this).setMessage(resId).setCancelable(true).setPositiveButton(isMediaFromEditor() ? R.string.remove : R.string.delete, (dialog, which) -> {
            if (isMediaFromEditor()) {
                removeMediaFromPost();
            } else {
                deleteMedia();
            }
        }).setNegativeButton(R.string.cancel, null);
        if (!ListenerUtil.mutListener.listen(8027)) {
            mDeleteMediaConfirmationDialog = builder.create();
        }
        if (!ListenerUtil.mutListener.listen(8028)) {
            mDeleteMediaConfirmationDialog.show();
        }
    }

    private void deleteMedia() {
        if (!ListenerUtil.mutListener.listen(8029)) {
            if (!NetworkUtils.checkConnection(this)) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(8030)) {
            mProgressDialog = new ProgressDialog(this);
        }
        if (!ListenerUtil.mutListener.listen(8031)) {
            mProgressDialog.setCancelable(false);
        }
        if (!ListenerUtil.mutListener.listen(8032)) {
            mProgressDialog.setIndeterminate(true);
        }
        if (!ListenerUtil.mutListener.listen(8033)) {
            mProgressDialog.setMessage(getString(R.string.deleting_media_dlg));
        }
        if (!ListenerUtil.mutListener.listen(8034)) {
            mProgressDialog.show();
        }
        if (!ListenerUtil.mutListener.listen(8035)) {
            AppLog.v(AppLog.T.MEDIA, "Deleting " + mMedia.getTitle() + " (id=" + mMedia.getMediaId() + ")");
        }
        MediaPayload payload = new MediaPayload(mSite, mMedia);
        if (!ListenerUtil.mutListener.listen(8036)) {
            mDispatcher.dispatch(MediaActionBuilder.newDeleteMediaAction(payload));
        }
    }

    private void removeMediaFromPost() {
        if (!ListenerUtil.mutListener.listen(8037)) {
            mEditorImageMetaData.markAsRemoved();
        }
        Intent intent = new Intent();
        if (!ListenerUtil.mutListener.listen(8038)) {
            intent.putExtra(ARG_EDITOR_IMAGE_METADATA, mEditorImageMetaData);
        }
        if (!ListenerUtil.mutListener.listen(8039)) {
            this.setResult(Activity.RESULT_OK, intent);
        }
        if (!ListenerUtil.mutListener.listen(8040)) {
            finish();
        }
    }

    @SuppressWarnings("unused")
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMediaChanged(OnMediaChanged event) {
        if (!ListenerUtil.mutListener.listen(8049)) {
            if (event.cause == MediaAction.DELETE_MEDIA) {
                if (!ListenerUtil.mutListener.listen(8044)) {
                    if ((ListenerUtil.mutListener.listen(8042) ? (mProgressDialog != null || mProgressDialog.isShowing()) : (mProgressDialog != null && mProgressDialog.isShowing()))) {
                        if (!ListenerUtil.mutListener.listen(8043)) {
                            mProgressDialog.dismiss();
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(8048)) {
                    if (event.isError()) {
                        if (!ListenerUtil.mutListener.listen(8047)) {
                            ToastUtils.showToast(this, R.string.error_generic);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(8045)) {
                            setResult(RESULT_MEDIA_DELETED);
                        }
                        if (!ListenerUtil.mutListener.listen(8046)) {
                            finish();
                        }
                    }
                }
            } else if (!event.isError()) {
                if (!ListenerUtil.mutListener.listen(8041)) {
                    reloadMedia();
                }
            }
        }
    }

    /*
     * user swiped to another media item in the preview activity, so update this one to show the same media
     */
    @SuppressWarnings("unused")
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMediaPreviewSwiped(MediaPreviewSwiped event) {
        if (!ListenerUtil.mutListener.listen(8051)) {
            if (event.mediaId != mMedia.getId()) {
                if (!ListenerUtil.mutListener.listen(8050)) {
                    loadMediaWithId(event.mediaId);
                }
            }
        }
    }

    private void copyMediaUrlToClipboard() {
        try {
            ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
            if (!ListenerUtil.mutListener.listen(8054)) {
                clipboard.setPrimaryClip(ClipData.newPlainText(getString(R.string.app_name), mMedia.getUrl()));
            }
            if (!ListenerUtil.mutListener.listen(8055)) {
                ToastUtils.showToast(this, R.string.media_edit_copy_url_toast);
            }
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(8052)) {
                AppLog.e(AppLog.T.UTILS, e);
            }
            if (!ListenerUtil.mutListener.listen(8053)) {
                ToastUtils.showToast(this, R.string.error_copy_to_clipboard);
            }
        }
    }

    public enum MediaSettingsImageSize {

        THUMBNAIL(R.string.image_size_thumbnail_label, R.string.image_size_thumbnail_css_class, R.integer.image_size_thumbnail_px), MEDIUM(R.string.image_size_medium_label, R.string.image_size_medium_css_class, R.integer.image_size_medium_px), LARGE(R.string.image_size_large_label, R.string.image_size_large_css_class, R.integer.image_size_large_px), FULL(R.string.image_size_full_label, R.string.image_size_full_css_class, R.integer.image_size_large_px);

        private final int mLabel;

        private final int mCssClass;

        private final int mSize;

        MediaSettingsImageSize(@StringRes int label, @StringRes int cssClass, @IntegerRes int size) {
            mLabel = label;
            mCssClass = cssClass;
            mSize = size;
        }

        public int getSize() {
            return mSize;
        }

        public int getLabel() {
            return mLabel;
        }

        public int getCssClass() {
            return mCssClass;
        }

        public static MediaSettingsImageSize fromCssClass(Context context, String cssClass) {
            if (!ListenerUtil.mutListener.listen(8057)) {
                {
                    long _loopCounter164 = 0;
                    for (MediaSettingsImageSize mediaSettingsImageSize : values()) {
                        ListenerUtil.loopListener.listen("_loopCounter164", ++_loopCounter164);
                        if (!ListenerUtil.mutListener.listen(8056)) {
                            if (context.getString(mediaSettingsImageSize.mCssClass).equals(cssClass)) {
                                return mediaSettingsImageSize;
                            }
                        }
                    }
                }
            }
            return FULL;
        }
    }
}
