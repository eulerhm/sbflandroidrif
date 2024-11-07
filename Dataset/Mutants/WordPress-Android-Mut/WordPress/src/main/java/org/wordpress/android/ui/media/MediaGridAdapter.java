package org.wordpress.android.ui.media;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import org.wordpress.android.R;
import org.wordpress.android.WordPress;
import org.wordpress.android.fluxc.model.MediaModel;
import org.wordpress.android.fluxc.model.MediaModel.MediaUploadState;
import org.wordpress.android.fluxc.model.SiteModel;
import org.wordpress.android.ui.utils.AuthenticationUtils;
import org.wordpress.android.util.AccessibilityUtils;
import org.wordpress.android.util.AniUtils;
import org.wordpress.android.util.AppLog;
import org.wordpress.android.util.ColorUtils;
import org.wordpress.android.util.DisplayUtils;
import org.wordpress.android.util.MediaUtils;
import org.wordpress.android.util.PhotoPickerUtils;
import org.wordpress.android.util.PhotonUtils;
import org.wordpress.android.util.SiteUtils;
import org.wordpress.android.util.StringUtils;
import org.wordpress.android.util.UrlUtils;
import org.wordpress.android.util.ViewUtils;
import org.wordpress.android.util.extensions.ViewExtensionsKt;
import org.wordpress.android.util.WPMediaUtils;
import org.wordpress.android.util.image.ImageManager;
import org.wordpress.android.util.image.ImageType;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.inject.Inject;
import javax.inject.Named;
import static org.wordpress.android.modules.ThreadModuleKt.APPLICATION_SCOPE;
import kotlinx.coroutines.CoroutineScope;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * An adapter for the media gallery grid.
 */
public class MediaGridAdapter extends RecyclerView.Adapter<MediaGridAdapter.GridViewHolder> {

    private MediaGridAdapterCallback mCallback;

    private final MediaBrowserType mBrowserType;

    private boolean mHasRetrievedAll;

    private boolean mInMultiSelect;

    private boolean mLoadThumbnails = true;

    private final Handler mHandler;

    private final LayoutInflater mInflater;

    private GridLayoutManager mLayoutManager;

    private final Context mContext;

    private final SiteModel mSite;

    private final ArrayList<MediaModel> mMediaList = new ArrayList<>();

    private final ArrayList<Integer> mSelectedItems = new ArrayList<>();

    private final int mThumbWidth;

    private final int mThumbHeight;

    private static final float SCALE_NORMAL = 1.0f;

    private static final float SCALE_SELECTED = .8f;

    private static final String VIEW_TAG_EXTRACT_FROM_REMOTE_VIDEO_URL = "view_tag_extract_from_remote_video_url";

    @Inject
    ImageManager mImageManager;

    @Inject
    AuthenticationUtils mAuthenticationUtils;

    @Inject
    @Named(APPLICATION_SCOPE)
    CoroutineScope mAppScope;

    public interface MediaGridAdapterCallback {

        void onAdapterFetchMoreData();

        void onAdapterItemClicked(int position, boolean isLongClick);

        void onAdapterSelectionCountChanged(int count);

        void onAdapterRequestRetry(int position);

        void onAdapterRequestDelete(int position);
    }

    private static final int INVALID_POSITION = -1;

    public MediaGridAdapter(@NonNull Context context, @NonNull SiteModel site, @NonNull MediaBrowserType browserType) {
        super();
        if (!ListenerUtil.mutListener.listen(6851)) {
            ((WordPress) WordPress.getContext().getApplicationContext()).component().inject(this);
        }
        if (!ListenerUtil.mutListener.listen(6852)) {
            setHasStableIds(true);
        }
        mContext = context;
        mSite = site;
        mBrowserType = browserType;
        mInflater = LayoutInflater.from(context);
        mHandler = new Handler();
        int displayWidth = DisplayUtils.getWindowPixelWidth(mContext);
        mThumbWidth = (ListenerUtil.mutListener.listen(6856) ? (displayWidth % getColumnCount(mContext)) : (ListenerUtil.mutListener.listen(6855) ? (displayWidth * getColumnCount(mContext)) : (ListenerUtil.mutListener.listen(6854) ? (displayWidth - getColumnCount(mContext)) : (ListenerUtil.mutListener.listen(6853) ? (displayWidth + getColumnCount(mContext)) : (displayWidth / getColumnCount(mContext))))));
        mThumbHeight = (int) ((ListenerUtil.mutListener.listen(6860) ? (mThumbWidth % 0.75f) : (ListenerUtil.mutListener.listen(6859) ? (mThumbWidth / 0.75f) : (ListenerUtil.mutListener.listen(6858) ? (mThumbWidth - 0.75f) : (ListenerUtil.mutListener.listen(6857) ? (mThumbWidth + 0.75f) : (mThumbWidth * 0.75f))))));
    }

    @Override
    public long getItemId(int position) {
        return getLocalMediaIdAtPosition(position);
    }

    public void setMediaList(@NonNull List<MediaModel> mediaList) {
        if (!ListenerUtil.mutListener.listen(6864)) {
            if (!isSameList(mediaList)) {
                if (!ListenerUtil.mutListener.listen(6861)) {
                    mMediaList.clear();
                }
                if (!ListenerUtil.mutListener.listen(6862)) {
                    mMediaList.addAll(mediaList);
                }
                if (!ListenerUtil.mutListener.listen(6863)) {
                    notifyDataSetChanged();
                }
            }
        }
    }

    @Override
    public GridViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.media_grid_item, parent, false);
        return new GridViewHolder(view);
    }

    /*
     * returns the most optimal url to use when retrieving a media image for display here
     */
    private String getBestImageUrl(@NonNull MediaModel media) {
        if (!ListenerUtil.mutListener.listen(6865)) {
            // exact size we need here
            if (SiteUtils.isPhotonCapable(mSite)) {
                return PhotonUtils.getPhotonImageUrl(media.getUrl(), mThumbWidth, mThumbHeight, mSite.isPrivateWPComAtomic());
            }
        }
        if (!ListenerUtil.mutListener.listen(6866)) {
            // medium because they're more bandwidth-friendly than large
            if (!TextUtils.isEmpty(media.getFileUrlMediumLargeSize())) {
                return media.getFileUrlMediumLargeSize();
            } else if (!TextUtils.isEmpty(media.getFileUrlMediumSize())) {
                return media.getFileUrlMediumSize();
            } else if (!TextUtils.isEmpty(media.getFileUrlLargeSize())) {
                return media.getFileUrlLargeSize();
            }
        }
        if (!ListenerUtil.mutListener.listen(6867)) {
            // better than eating bandwidth showing the full-sized image
            if (!TextUtils.isEmpty(media.getThumbnailUrl())) {
                return media.getThumbnailUrl();
            }
        }
        // last resort, return the full-sized image url
        return UrlUtils.removeQuery(media.getUrl());
    }

    @Override
    public void onBindViewHolder(GridViewHolder holder, int position) {
        if (!ListenerUtil.mutListener.listen(6868)) {
            if (!isValidPosition(position)) {
                return;
            }
        }
        MediaModel media = mMediaList.get(position);
        String strState = media.getUploadState();
        MediaUploadState state = MediaUploadState.fromString(strState);
        boolean isLocalFile = (ListenerUtil.mutListener.listen(6869) ? (MediaUtils.isLocalFile(strState) || !TextUtils.isEmpty(media.getFilePath())) : (MediaUtils.isLocalFile(strState) && !TextUtils.isEmpty(media.getFilePath())));
        boolean isSelected = isItemSelected(media.getId());
        boolean canSelect = canSelectPosition(position);
        boolean isImage = (ListenerUtil.mutListener.listen(6870) ? (media.getMimeType() != null || media.getMimeType().startsWith("image/")) : (media.getMimeType() != null && media.getMimeType().startsWith("image/")));
        if (!ListenerUtil.mutListener.listen(6884)) {
            if (!mLoadThumbnails) {
                if (!ListenerUtil.mutListener.listen(6882)) {
                    holder.mFileContainer.setVisibility(View.GONE);
                }
                if (!ListenerUtil.mutListener.listen(6883)) {
                    mImageManager.load(holder.mImageView, ImageType.PHOTO, "", ScaleType.CENTER_CROP);
                }
            } else if (isImage) {
                if (!ListenerUtil.mutListener.listen(6878)) {
                    holder.mFileContainer.setVisibility(View.GONE);
                }
                if (!ListenerUtil.mutListener.listen(6881)) {
                    if (isLocalFile) {
                        if (!ListenerUtil.mutListener.listen(6880)) {
                            mImageManager.load(holder.mImageView, ImageType.PHOTO, media.getFilePath(), ScaleType.CENTER_CROP);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(6879)) {
                            mImageManager.load(holder.mImageView, ImageType.PHOTO, getBestImageUrl(media), ScaleType.CENTER_CROP);
                        }
                    }
                }
            } else if (media.isVideo()) {
                if (!ListenerUtil.mutListener.listen(6876)) {
                    holder.mFileContainer.setVisibility(View.GONE);
                }
                if (!ListenerUtil.mutListener.listen(6877)) {
                    loadVideoThumbnail(position, media, holder.mImageView);
                }
            } else {
                // not an image or video, so show file name and file type
                String fileName = media.getFileName();
                String title = media.getTitle();
                String fileExtension = MediaUtils.getExtensionForMimeType(media.getMimeType());
                if (!ListenerUtil.mutListener.listen(6871)) {
                    holder.mFileContainer.setVisibility(View.VISIBLE);
                }
                if (!ListenerUtil.mutListener.listen(6872)) {
                    holder.mTitleView.setText(TextUtils.isEmpty(title) ? fileName : title);
                }
                if (!ListenerUtil.mutListener.listen(6873)) {
                    holder.mFileTypeView.setText(fileExtension.toUpperCase(Locale.ROOT));
                }
                int placeholderResId = WPMediaUtils.getPlaceholder(fileName);
                if (!ListenerUtil.mutListener.listen(6874)) {
                    ColorUtils.INSTANCE.setImageResourceWithTint(holder.mFileTypeImageView, placeholderResId, R.color.neutral_30);
                }
                if (!ListenerUtil.mutListener.listen(6875)) {
                    mImageManager.cancelRequestAndClearImageView(holder.mImageView);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(6885)) {
            holder.mImageView.setContentDescription(mContext.getString(R.string.media_grid_item_image_desc, StringUtils.notNullStr(media.getFileName())));
        }
        if (!ListenerUtil.mutListener.listen(6895)) {
            if ((ListenerUtil.mutListener.listen(6886) ? (mBrowserType.canMultiselect() || canSelect) : (mBrowserType.canMultiselect() && canSelect))) {
                if (!ListenerUtil.mutListener.listen(6889)) {
                    holder.mSelectionCountContainer.setVisibility(View.VISIBLE);
                }
                if (!ListenerUtil.mutListener.listen(6890)) {
                    holder.mSelectionCountTextView.setVisibility(View.VISIBLE);
                }
                if (!ListenerUtil.mutListener.listen(6891)) {
                    holder.mSelectionCountTextView.setSelected(isSelected);
                }
                if (!ListenerUtil.mutListener.listen(6894)) {
                    if (isSelected) {
                        int count = mSelectedItems.indexOf(media.getId()) + 1;
                        if (!ListenerUtil.mutListener.listen(6893)) {
                            holder.mSelectionCountTextView.setText(String.format(Locale.getDefault(), "%d", count));
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(6892)) {
                            holder.mSelectionCountTextView.setText(null);
                        }
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(6887)) {
                    holder.mSelectionCountContainer.setVisibility(View.GONE);
                }
                if (!ListenerUtil.mutListener.listen(6888)) {
                    holder.mSelectionCountTextView.setVisibility(View.GONE);
                }
            }
        }
        // make sure the thumbnail scale reflects its selection state
        float scale = isSelected ? SCALE_SELECTED : SCALE_NORMAL;
        if (!ListenerUtil.mutListener.listen(6898)) {
            if (holder.mImageView.getScaleX() != scale) {
                if (!ListenerUtil.mutListener.listen(6896)) {
                    holder.mImageView.setScaleX(scale);
                }
                if (!ListenerUtil.mutListener.listen(6897)) {
                    holder.mImageView.setScaleY(scale);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(6912)) {
            // show upload state unless it's already uploaded
            if (state != MediaUploadState.UPLOADED) {
                if (!ListenerUtil.mutListener.listen(6902)) {
                    holder.mStateContainer.setVisibility(View.VISIBLE);
                }
                // only show progress for items currently being uploaded or deleted
                boolean showProgress = (ListenerUtil.mutListener.listen(6903) ? (state == MediaUploadState.UPLOADING && state == MediaUploadState.DELETING) : (state == MediaUploadState.UPLOADING || state == MediaUploadState.DELETING));
                if (!ListenerUtil.mutListener.listen(6904)) {
                    holder.mProgressUpload.setVisibility(showProgress ? View.VISIBLE : View.GONE);
                }
                if (!ListenerUtil.mutListener.listen(6909)) {
                    // failed uploads can be retried or deleted, queued items can be deleted
                    if ((ListenerUtil.mutListener.listen(6905) ? (state == MediaUploadState.FAILED && state == MediaUploadState.QUEUED) : (state == MediaUploadState.FAILED || state == MediaUploadState.QUEUED))) {
                        if (!ListenerUtil.mutListener.listen(6907)) {
                            holder.mRetryDeleteContainer.setVisibility(View.VISIBLE);
                        }
                        if (!ListenerUtil.mutListener.listen(6908)) {
                            holder.mImgRetry.setVisibility(state == MediaUploadState.FAILED ? View.VISIBLE : View.GONE);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(6906)) {
                            holder.mRetryDeleteContainer.setVisibility(View.GONE);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(6910)) {
                    holder.mStateTextView.setText(getLabelForMediaUploadState(state));
                }
                if (!ListenerUtil.mutListener.listen(6911)) {
                    // hide the video player icon so it doesn't overlap state label
                    holder.mVideoOverlayContainer.setVisibility(View.GONE);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(6899)) {
                    holder.mStateContainer.setVisibility(View.GONE);
                }
                if (!ListenerUtil.mutListener.listen(6900)) {
                    holder.mStateContainer.setOnClickListener(null);
                }
                if (!ListenerUtil.mutListener.listen(6901)) {
                    holder.mVideoOverlayContainer.setVisibility(media.isVideo() ? View.VISIBLE : View.GONE);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(6925)) {
            // if we are near the end, make a call to fetch more
            if ((ListenerUtil.mutListener.listen(6923) ? ((ListenerUtil.mutListener.listen(6922) ? ((ListenerUtil.mutListener.listen(6921) ? (position >= (ListenerUtil.mutListener.listen(6916) ? (getItemCount() % 1) : (ListenerUtil.mutListener.listen(6915) ? (getItemCount() / 1) : (ListenerUtil.mutListener.listen(6914) ? (getItemCount() * 1) : (ListenerUtil.mutListener.listen(6913) ? (getItemCount() + 1) : (getItemCount() - 1)))))) : (ListenerUtil.mutListener.listen(6920) ? (position <= (ListenerUtil.mutListener.listen(6916) ? (getItemCount() % 1) : (ListenerUtil.mutListener.listen(6915) ? (getItemCount() / 1) : (ListenerUtil.mutListener.listen(6914) ? (getItemCount() * 1) : (ListenerUtil.mutListener.listen(6913) ? (getItemCount() + 1) : (getItemCount() - 1)))))) : (ListenerUtil.mutListener.listen(6919) ? (position > (ListenerUtil.mutListener.listen(6916) ? (getItemCount() % 1) : (ListenerUtil.mutListener.listen(6915) ? (getItemCount() / 1) : (ListenerUtil.mutListener.listen(6914) ? (getItemCount() * 1) : (ListenerUtil.mutListener.listen(6913) ? (getItemCount() + 1) : (getItemCount() - 1)))))) : (ListenerUtil.mutListener.listen(6918) ? (position < (ListenerUtil.mutListener.listen(6916) ? (getItemCount() % 1) : (ListenerUtil.mutListener.listen(6915) ? (getItemCount() / 1) : (ListenerUtil.mutListener.listen(6914) ? (getItemCount() * 1) : (ListenerUtil.mutListener.listen(6913) ? (getItemCount() + 1) : (getItemCount() - 1)))))) : (ListenerUtil.mutListener.listen(6917) ? (position != (ListenerUtil.mutListener.listen(6916) ? (getItemCount() % 1) : (ListenerUtil.mutListener.listen(6915) ? (getItemCount() / 1) : (ListenerUtil.mutListener.listen(6914) ? (getItemCount() * 1) : (ListenerUtil.mutListener.listen(6913) ? (getItemCount() + 1) : (getItemCount() - 1)))))) : (position == (ListenerUtil.mutListener.listen(6916) ? (getItemCount() % 1) : (ListenerUtil.mutListener.listen(6915) ? (getItemCount() / 1) : (ListenerUtil.mutListener.listen(6914) ? (getItemCount() * 1) : (ListenerUtil.mutListener.listen(6913) ? (getItemCount() + 1) : (getItemCount() - 1))))))))))) || !mHasRetrievedAll) : ((ListenerUtil.mutListener.listen(6921) ? (position >= (ListenerUtil.mutListener.listen(6916) ? (getItemCount() % 1) : (ListenerUtil.mutListener.listen(6915) ? (getItemCount() / 1) : (ListenerUtil.mutListener.listen(6914) ? (getItemCount() * 1) : (ListenerUtil.mutListener.listen(6913) ? (getItemCount() + 1) : (getItemCount() - 1)))))) : (ListenerUtil.mutListener.listen(6920) ? (position <= (ListenerUtil.mutListener.listen(6916) ? (getItemCount() % 1) : (ListenerUtil.mutListener.listen(6915) ? (getItemCount() / 1) : (ListenerUtil.mutListener.listen(6914) ? (getItemCount() * 1) : (ListenerUtil.mutListener.listen(6913) ? (getItemCount() + 1) : (getItemCount() - 1)))))) : (ListenerUtil.mutListener.listen(6919) ? (position > (ListenerUtil.mutListener.listen(6916) ? (getItemCount() % 1) : (ListenerUtil.mutListener.listen(6915) ? (getItemCount() / 1) : (ListenerUtil.mutListener.listen(6914) ? (getItemCount() * 1) : (ListenerUtil.mutListener.listen(6913) ? (getItemCount() + 1) : (getItemCount() - 1)))))) : (ListenerUtil.mutListener.listen(6918) ? (position < (ListenerUtil.mutListener.listen(6916) ? (getItemCount() % 1) : (ListenerUtil.mutListener.listen(6915) ? (getItemCount() / 1) : (ListenerUtil.mutListener.listen(6914) ? (getItemCount() * 1) : (ListenerUtil.mutListener.listen(6913) ? (getItemCount() + 1) : (getItemCount() - 1)))))) : (ListenerUtil.mutListener.listen(6917) ? (position != (ListenerUtil.mutListener.listen(6916) ? (getItemCount() % 1) : (ListenerUtil.mutListener.listen(6915) ? (getItemCount() / 1) : (ListenerUtil.mutListener.listen(6914) ? (getItemCount() * 1) : (ListenerUtil.mutListener.listen(6913) ? (getItemCount() + 1) : (getItemCount() - 1)))))) : (position == (ListenerUtil.mutListener.listen(6916) ? (getItemCount() % 1) : (ListenerUtil.mutListener.listen(6915) ? (getItemCount() / 1) : (ListenerUtil.mutListener.listen(6914) ? (getItemCount() * 1) : (ListenerUtil.mutListener.listen(6913) ? (getItemCount() + 1) : (getItemCount() - 1))))))))))) && !mHasRetrievedAll)) || mCallback != null) : ((ListenerUtil.mutListener.listen(6922) ? ((ListenerUtil.mutListener.listen(6921) ? (position >= (ListenerUtil.mutListener.listen(6916) ? (getItemCount() % 1) : (ListenerUtil.mutListener.listen(6915) ? (getItemCount() / 1) : (ListenerUtil.mutListener.listen(6914) ? (getItemCount() * 1) : (ListenerUtil.mutListener.listen(6913) ? (getItemCount() + 1) : (getItemCount() - 1)))))) : (ListenerUtil.mutListener.listen(6920) ? (position <= (ListenerUtil.mutListener.listen(6916) ? (getItemCount() % 1) : (ListenerUtil.mutListener.listen(6915) ? (getItemCount() / 1) : (ListenerUtil.mutListener.listen(6914) ? (getItemCount() * 1) : (ListenerUtil.mutListener.listen(6913) ? (getItemCount() + 1) : (getItemCount() - 1)))))) : (ListenerUtil.mutListener.listen(6919) ? (position > (ListenerUtil.mutListener.listen(6916) ? (getItemCount() % 1) : (ListenerUtil.mutListener.listen(6915) ? (getItemCount() / 1) : (ListenerUtil.mutListener.listen(6914) ? (getItemCount() * 1) : (ListenerUtil.mutListener.listen(6913) ? (getItemCount() + 1) : (getItemCount() - 1)))))) : (ListenerUtil.mutListener.listen(6918) ? (position < (ListenerUtil.mutListener.listen(6916) ? (getItemCount() % 1) : (ListenerUtil.mutListener.listen(6915) ? (getItemCount() / 1) : (ListenerUtil.mutListener.listen(6914) ? (getItemCount() * 1) : (ListenerUtil.mutListener.listen(6913) ? (getItemCount() + 1) : (getItemCount() - 1)))))) : (ListenerUtil.mutListener.listen(6917) ? (position != (ListenerUtil.mutListener.listen(6916) ? (getItemCount() % 1) : (ListenerUtil.mutListener.listen(6915) ? (getItemCount() / 1) : (ListenerUtil.mutListener.listen(6914) ? (getItemCount() * 1) : (ListenerUtil.mutListener.listen(6913) ? (getItemCount() + 1) : (getItemCount() - 1)))))) : (position == (ListenerUtil.mutListener.listen(6916) ? (getItemCount() % 1) : (ListenerUtil.mutListener.listen(6915) ? (getItemCount() / 1) : (ListenerUtil.mutListener.listen(6914) ? (getItemCount() * 1) : (ListenerUtil.mutListener.listen(6913) ? (getItemCount() + 1) : (getItemCount() - 1))))))))))) || !mHasRetrievedAll) : ((ListenerUtil.mutListener.listen(6921) ? (position >= (ListenerUtil.mutListener.listen(6916) ? (getItemCount() % 1) : (ListenerUtil.mutListener.listen(6915) ? (getItemCount() / 1) : (ListenerUtil.mutListener.listen(6914) ? (getItemCount() * 1) : (ListenerUtil.mutListener.listen(6913) ? (getItemCount() + 1) : (getItemCount() - 1)))))) : (ListenerUtil.mutListener.listen(6920) ? (position <= (ListenerUtil.mutListener.listen(6916) ? (getItemCount() % 1) : (ListenerUtil.mutListener.listen(6915) ? (getItemCount() / 1) : (ListenerUtil.mutListener.listen(6914) ? (getItemCount() * 1) : (ListenerUtil.mutListener.listen(6913) ? (getItemCount() + 1) : (getItemCount() - 1)))))) : (ListenerUtil.mutListener.listen(6919) ? (position > (ListenerUtil.mutListener.listen(6916) ? (getItemCount() % 1) : (ListenerUtil.mutListener.listen(6915) ? (getItemCount() / 1) : (ListenerUtil.mutListener.listen(6914) ? (getItemCount() * 1) : (ListenerUtil.mutListener.listen(6913) ? (getItemCount() + 1) : (getItemCount() - 1)))))) : (ListenerUtil.mutListener.listen(6918) ? (position < (ListenerUtil.mutListener.listen(6916) ? (getItemCount() % 1) : (ListenerUtil.mutListener.listen(6915) ? (getItemCount() / 1) : (ListenerUtil.mutListener.listen(6914) ? (getItemCount() * 1) : (ListenerUtil.mutListener.listen(6913) ? (getItemCount() + 1) : (getItemCount() - 1)))))) : (ListenerUtil.mutListener.listen(6917) ? (position != (ListenerUtil.mutListener.listen(6916) ? (getItemCount() % 1) : (ListenerUtil.mutListener.listen(6915) ? (getItemCount() / 1) : (ListenerUtil.mutListener.listen(6914) ? (getItemCount() * 1) : (ListenerUtil.mutListener.listen(6913) ? (getItemCount() + 1) : (getItemCount() - 1)))))) : (position == (ListenerUtil.mutListener.listen(6916) ? (getItemCount() % 1) : (ListenerUtil.mutListener.listen(6915) ? (getItemCount() / 1) : (ListenerUtil.mutListener.listen(6914) ? (getItemCount() * 1) : (ListenerUtil.mutListener.listen(6913) ? (getItemCount() + 1) : (getItemCount() - 1))))))))))) && !mHasRetrievedAll)) && mCallback != null))) {
                if (!ListenerUtil.mutListener.listen(6924)) {
                    mCallback.onAdapterFetchMoreData();
                }
            }
        }
    }

    public ArrayList<Integer> getSelectedItems() {
        return mSelectedItems;
    }

    public int getSelectedItemCount() {
        return mSelectedItems.size();
    }

    class GridViewHolder extends RecyclerView.ViewHolder {

        private final TextView mTitleView;

        private final ImageView mImageView;

        private final TextView mFileTypeView;

        private final ImageView mFileTypeImageView;

        private final TextView mSelectionCountTextView;

        private final TextView mStateTextView;

        private final ProgressBar mProgressUpload;

        private final ViewGroup mStateContainer;

        private final ViewGroup mFileContainer;

        private final ViewGroup mVideoOverlayContainer;

        private final ViewGroup mSelectionCountContainer;

        private final ViewGroup mRetryDeleteContainer;

        private final ImageView mImgRetry;

        private final ImageView mImgTrash;

        GridViewHolder(View view) {
            super(view);
            mImageView = view.findViewById(R.id.media_grid_item_image);
            mSelectionCountTextView = view.findViewById(R.id.text_selection_count);
            mStateContainer = view.findViewById(R.id.media_grid_item_upload_state_container);
            mStateTextView = mStateContainer.findViewById(R.id.media_grid_item_upload_state);
            mProgressUpload = mStateContainer.findViewById(R.id.media_grid_item_upload_progress);
            mFileContainer = view.findViewById(R.id.media_grid_item_file_container);
            mTitleView = mFileContainer.findViewById(R.id.media_grid_item_name);
            mFileTypeView = mFileContainer.findViewById(R.id.media_grid_item_filetype);
            mFileTypeImageView = mFileContainer.findViewById(R.id.media_grid_item_filetype_image);
            mVideoOverlayContainer = view.findViewById(R.id.frame_video_overlay);
            mSelectionCountContainer = view.findViewById(R.id.frame_selection_count);
            if (!ListenerUtil.mutListener.listen(6926)) {
                // make the progress bar white
                mProgressUpload.getIndeterminateDrawable().setColorFilter(Color.WHITE, PorterDuff.Mode.MULTIPLY);
            }
            mRetryDeleteContainer = view.findViewById(R.id.container_retry_delete);
            mImgRetry = view.findViewById(R.id.image_retry);
            mImgTrash = view.findViewById(R.id.image_trash);
            if (!ListenerUtil.mutListener.listen(6928)) {
                itemView.setOnClickListener(new OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        int position = getAdapterPosition();
                        if (!ListenerUtil.mutListener.listen(6927)) {
                            doAdapterItemClicked(position, false);
                        }
                    }
                });
            }
            if (!ListenerUtil.mutListener.listen(6930)) {
                itemView.setOnLongClickListener(new View.OnLongClickListener() {

                    @Override
                    public boolean onLongClick(View v) {
                        int position = getAdapterPosition();
                        if (!ListenerUtil.mutListener.listen(6929)) {
                            doAdapterItemClicked(position, true);
                        }
                        return true;
                    }
                });
            }
            if (!ListenerUtil.mutListener.listen(6931)) {
                ViewExtensionsKt.redirectContextClickToLongPressListener(itemView);
            }
            if (!ListenerUtil.mutListener.listen(6935)) {
                mSelectionCountContainer.setOnClickListener(new OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        int position = getAdapterPosition();
                        if (!ListenerUtil.mutListener.listen(6934)) {
                            if (canSelectPosition(position)) {
                                if (!ListenerUtil.mutListener.listen(6932)) {
                                    setInMultiSelect(true);
                                }
                                if (!ListenerUtil.mutListener.listen(6933)) {
                                    toggleItemSelected(GridViewHolder.this, position);
                                }
                            }
                        }
                    }
                });
            }
            if (!ListenerUtil.mutListener.listen(6939)) {
                mImgRetry.setOnClickListener(new OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        int position = getAdapterPosition();
                        if (!ListenerUtil.mutListener.listen(6938)) {
                            if ((ListenerUtil.mutListener.listen(6936) ? (isValidPosition(position) || mCallback != null) : (isValidPosition(position) && mCallback != null))) {
                                if (!ListenerUtil.mutListener.listen(6937)) {
                                    mCallback.onAdapterRequestRetry(position);
                                }
                            }
                        }
                    }
                });
            }
            if (!ListenerUtil.mutListener.listen(6943)) {
                mImgTrash.setOnClickListener(new OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        int position = getAdapterPosition();
                        if (!ListenerUtil.mutListener.listen(6942)) {
                            if ((ListenerUtil.mutListener.listen(6940) ? (isValidPosition(position) || mCallback != null) : (isValidPosition(position) && mCallback != null))) {
                                if (!ListenerUtil.mutListener.listen(6941)) {
                                    mCallback.onAdapterRequestDelete(position);
                                }
                            }
                        }
                    }
                });
            }
            if (!ListenerUtil.mutListener.listen(6944)) {
                ViewUtils.addCircularShadowOutline(mSelectionCountTextView);
            }
            if (!ListenerUtil.mutListener.listen(6945)) {
                addImageSelectedToAccessibilityFocusedEvent(mImageView);
            }
        }

        private void addImageSelectedToAccessibilityFocusedEvent(ImageView imageView) {
            if (!ListenerUtil.mutListener.listen(6946)) {
                AccessibilityUtils.addPopulateAccessibilityEventFocusedListener(imageView, event -> {
                    int position = getAdapterPosition();
                    if (isValidPosition(position)) {
                        if (isItemSelectedByPosition(position)) {
                            final String imageSelectedText = imageView.getContext().getString(R.string.photo_picker_image_selected);
                            if (!imageView.getContentDescription().toString().contains(imageSelectedText)) {
                                imageView.setContentDescription(imageView.getContentDescription() + " " + imageSelectedText);
                            }
                        }
                    }
                });
            }
        }

        private void doAdapterItemClicked(int position, boolean isLongClick) {
            if (!ListenerUtil.mutListener.listen(6947)) {
                if (!isValidPosition(position)) {
                    return;
                }
            }
            if (!ListenerUtil.mutListener.listen(6958)) {
                if ((ListenerUtil.mutListener.listen(6948) ? (isInMultiSelect() || !isLongClick) : (isInMultiSelect() && !isLongClick))) {
                    if (!ListenerUtil.mutListener.listen(6957)) {
                        if (canSelectPosition(position)) {
                            if (!ListenerUtil.mutListener.listen(6956)) {
                                toggleItemSelected(GridViewHolder.this, position);
                            }
                        }
                    }
                } else {
                    if (!ListenerUtil.mutListener.listen(6953)) {
                        if ((ListenerUtil.mutListener.listen(6950) ? ((ListenerUtil.mutListener.listen(6949) ? (mBrowserType.canMultiselect() || canSelectPosition(position)) : (mBrowserType.canMultiselect() && canSelectPosition(position))) || !isLongClick) : ((ListenerUtil.mutListener.listen(6949) ? (mBrowserType.canMultiselect() || canSelectPosition(position)) : (mBrowserType.canMultiselect() && canSelectPosition(position))) && !isLongClick))) {
                            if (!ListenerUtil.mutListener.listen(6951)) {
                                setInMultiSelect(true);
                            }
                            if (!ListenerUtil.mutListener.listen(6952)) {
                                toggleItemSelected(GridViewHolder.this, position);
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(6955)) {
                        if (mCallback != null) {
                            if (!ListenerUtil.mutListener.listen(6954)) {
                                mCallback.onAdapterItemClicked(position, isLongClick);
                            }
                        }
                    }
                }
            }
        }
    }

    public boolean isInMultiSelect() {
        return mInMultiSelect;
    }

    public void setInMultiSelect(boolean value) {
        if (!ListenerUtil.mutListener.listen(6961)) {
            if (mInMultiSelect != value) {
                if (!ListenerUtil.mutListener.listen(6959)) {
                    mInMultiSelect = value;
                }
                if (!ListenerUtil.mutListener.listen(6960)) {
                    clearSelection();
                }
            }
        }
    }

    private boolean isValidPosition(int position) {
        return (ListenerUtil.mutListener.listen(6972) ? ((ListenerUtil.mutListener.listen(6966) ? (position <= 0) : (ListenerUtil.mutListener.listen(6965) ? (position > 0) : (ListenerUtil.mutListener.listen(6964) ? (position < 0) : (ListenerUtil.mutListener.listen(6963) ? (position != 0) : (ListenerUtil.mutListener.listen(6962) ? (position == 0) : (position >= 0)))))) || (ListenerUtil.mutListener.listen(6971) ? (position >= getItemCount()) : (ListenerUtil.mutListener.listen(6970) ? (position <= getItemCount()) : (ListenerUtil.mutListener.listen(6969) ? (position > getItemCount()) : (ListenerUtil.mutListener.listen(6968) ? (position != getItemCount()) : (ListenerUtil.mutListener.listen(6967) ? (position == getItemCount()) : (position < getItemCount()))))))) : ((ListenerUtil.mutListener.listen(6966) ? (position <= 0) : (ListenerUtil.mutListener.listen(6965) ? (position > 0) : (ListenerUtil.mutListener.listen(6964) ? (position < 0) : (ListenerUtil.mutListener.listen(6963) ? (position != 0) : (ListenerUtil.mutListener.listen(6962) ? (position == 0) : (position >= 0)))))) && (ListenerUtil.mutListener.listen(6971) ? (position >= getItemCount()) : (ListenerUtil.mutListener.listen(6970) ? (position <= getItemCount()) : (ListenerUtil.mutListener.listen(6969) ? (position > getItemCount()) : (ListenerUtil.mutListener.listen(6968) ? (position != getItemCount()) : (ListenerUtil.mutListener.listen(6967) ? (position == getItemCount()) : (position < getItemCount()))))))));
    }

    public int getLocalMediaIdAtPosition(int position) {
        if (!ListenerUtil.mutListener.listen(6973)) {
            if (isValidPosition(position)) {
                return mMediaList.get(position).getId();
            }
        }
        if (!ListenerUtil.mutListener.listen(6974)) {
            AppLog.w(AppLog.T.MEDIA, "MediaGridAdapter > Invalid position " + position);
        }
        return INVALID_POSITION;
    }

    /*
     * determines whether the media item at the passed position can be selected - not allowed
     * for local files or deleted items when used as a picker
     */
    private boolean canSelectPosition(int position) {
        if (!isValidPosition(position)) {
            return false;
        }
        if (mBrowserType.isPicker()) {
            MediaModel media = mMediaList.get(position);
            if (MediaUtils.isLocalFile(media.getUploadState())) {
                return false;
            }
            MediaUploadState state = MediaUploadState.fromString(media.getUploadState());
            return (ListenerUtil.mutListener.listen(6975) ? (state != MediaUploadState.DELETING || state != MediaUploadState.DELETED) : (state != MediaUploadState.DELETING && state != MediaUploadState.DELETED));
        } else {
            return true;
        }
    }

    @Override
    public void onViewRecycled(@NonNull GridViewHolder holder) {
        if (!ListenerUtil.mutListener.listen(6976)) {
            mImageManager.cancelRequestAndClearImageView(holder.mImageView);
        }
        if (!ListenerUtil.mutListener.listen(6977)) {
            holder.mImageView.setTag(R.id.media_grid_remote_thumb_extract_id, null);
        }
        if (!ListenerUtil.mutListener.listen(6978)) {
            super.onViewRecycled(holder);
        }
    }

    public void cancelPendingRequestsForVisibleItems(@NonNull RecyclerView recyclerView) {
        int firstVisibleItemPosition = mLayoutManager.findFirstVisibleItemPosition();
        int lastVisibleItemPosition = mLayoutManager.findLastVisibleItemPosition();
        if (!ListenerUtil.mutListener.listen(6987)) {
            {
                long _loopCounter155 = 0;
                for (int i = firstVisibleItemPosition; (ListenerUtil.mutListener.listen(6986) ? (i >= lastVisibleItemPosition) : (ListenerUtil.mutListener.listen(6985) ? (i > lastVisibleItemPosition) : (ListenerUtil.mutListener.listen(6984) ? (i < lastVisibleItemPosition) : (ListenerUtil.mutListener.listen(6983) ? (i != lastVisibleItemPosition) : (ListenerUtil.mutListener.listen(6982) ? (i == lastVisibleItemPosition) : (i <= lastVisibleItemPosition)))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter155", ++_loopCounter155);
                    GridViewHolder holder = (GridViewHolder) recyclerView.findViewHolderForAdapterPosition(i);
                    if (!ListenerUtil.mutListener.listen(6981)) {
                        if ((ListenerUtil.mutListener.listen(6979) ? (holder != null || (VIEW_TAG_EXTRACT_FROM_REMOTE_VIDEO_URL.equals(holder.mImageView.getTag(R.id.media_grid_remote_thumb_extract_id)))) : (holder != null && (VIEW_TAG_EXTRACT_FROM_REMOTE_VIDEO_URL.equals(holder.mImageView.getTag(R.id.media_grid_remote_thumb_extract_id)))))) {
                            if (!ListenerUtil.mutListener.listen(6980)) {
                                mImageManager.cancelRequestAndClearImageView(holder.mImageView);
                            }
                        }
                    }
                }
            }
        }
    }

    public void refreshCurrentItems(@NonNull RecyclerView recyclerView) {
        int firstVisibleItemPosition = mLayoutManager.findFirstVisibleItemPosition();
        int lastVisibleItemPosition = mLayoutManager.findLastVisibleItemPosition();
        if (!ListenerUtil.mutListener.listen(7008)) {
            if ((ListenerUtil.mutListener.listen(6998) ? ((ListenerUtil.mutListener.listen(6992) ? (mMediaList.size() <= lastVisibleItemPosition) : (ListenerUtil.mutListener.listen(6991) ? (mMediaList.size() > lastVisibleItemPosition) : (ListenerUtil.mutListener.listen(6990) ? (mMediaList.size() < lastVisibleItemPosition) : (ListenerUtil.mutListener.listen(6989) ? (mMediaList.size() != lastVisibleItemPosition) : (ListenerUtil.mutListener.listen(6988) ? (mMediaList.size() == lastVisibleItemPosition) : (mMediaList.size() >= lastVisibleItemPosition)))))) || (ListenerUtil.mutListener.listen(6997) ? (lastVisibleItemPosition >= -1) : (ListenerUtil.mutListener.listen(6996) ? (lastVisibleItemPosition <= -1) : (ListenerUtil.mutListener.listen(6995) ? (lastVisibleItemPosition < -1) : (ListenerUtil.mutListener.listen(6994) ? (lastVisibleItemPosition != -1) : (ListenerUtil.mutListener.listen(6993) ? (lastVisibleItemPosition == -1) : (lastVisibleItemPosition > -1))))))) : ((ListenerUtil.mutListener.listen(6992) ? (mMediaList.size() <= lastVisibleItemPosition) : (ListenerUtil.mutListener.listen(6991) ? (mMediaList.size() > lastVisibleItemPosition) : (ListenerUtil.mutListener.listen(6990) ? (mMediaList.size() < lastVisibleItemPosition) : (ListenerUtil.mutListener.listen(6989) ? (mMediaList.size() != lastVisibleItemPosition) : (ListenerUtil.mutListener.listen(6988) ? (mMediaList.size() == lastVisibleItemPosition) : (mMediaList.size() >= lastVisibleItemPosition)))))) && (ListenerUtil.mutListener.listen(6997) ? (lastVisibleItemPosition >= -1) : (ListenerUtil.mutListener.listen(6996) ? (lastVisibleItemPosition <= -1) : (ListenerUtil.mutListener.listen(6995) ? (lastVisibleItemPosition < -1) : (ListenerUtil.mutListener.listen(6994) ? (lastVisibleItemPosition != -1) : (ListenerUtil.mutListener.listen(6993) ? (lastVisibleItemPosition == -1) : (lastVisibleItemPosition > -1))))))))) {
                if (!ListenerUtil.mutListener.listen(7007)) {
                    {
                        long _loopCounter156 = 0;
                        for (int i = firstVisibleItemPosition; (ListenerUtil.mutListener.listen(7006) ? (i >= lastVisibleItemPosition) : (ListenerUtil.mutListener.listen(7005) ? (i > lastVisibleItemPosition) : (ListenerUtil.mutListener.listen(7004) ? (i < lastVisibleItemPosition) : (ListenerUtil.mutListener.listen(7003) ? (i != lastVisibleItemPosition) : (ListenerUtil.mutListener.listen(7002) ? (i == lastVisibleItemPosition) : (i <= lastVisibleItemPosition)))))); i++) {
                            ListenerUtil.loopListener.listen("_loopCounter156", ++_loopCounter156);
                            // only refresh this one
                            GridViewHolder holder = (GridViewHolder) recyclerView.findViewHolderForAdapterPosition(i);
                            if (!ListenerUtil.mutListener.listen(7001)) {
                                if ((ListenerUtil.mutListener.listen(6999) ? (holder != null || (VIEW_TAG_EXTRACT_FROM_REMOTE_VIDEO_URL.equals(holder.mImageView.getTag(R.id.media_grid_remote_thumb_extract_id)))) : (holder != null && (VIEW_TAG_EXTRACT_FROM_REMOTE_VIDEO_URL.equals(holder.mImageView.getTag(R.id.media_grid_remote_thumb_extract_id)))))) {
                                    if (!ListenerUtil.mutListener.listen(7000)) {
                                        notifyItemChanged(i);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        if (!ListenerUtil.mutListener.listen(7009)) {
            super.onAttachedToRecyclerView(recyclerView);
        }
        if (!ListenerUtil.mutListener.listen(7010)) {
            mLayoutManager = (GridLayoutManager) recyclerView.getLayoutManager();
        }
    }

    /*
     * loads the thumbnail for the passed video media item - works with both local and network videos
     */
    private void loadVideoThumbnail(final int position, @NonNull final MediaModel media, @NonNull final ImageView imageView) {
        if (!ListenerUtil.mutListener.listen(7013)) {
            // if we have a thumbnail url, use it and be done
            if ((ListenerUtil.mutListener.listen(7011) ? (!TextUtils.isEmpty(media.getThumbnailUrl()) || !MediaUtils.isVideo(media.getThumbnailUrl())) : (!TextUtils.isEmpty(media.getThumbnailUrl()) && !MediaUtils.isVideo(media.getThumbnailUrl())))) {
                if (!ListenerUtil.mutListener.listen(7012)) {
                    mImageManager.load(imageView, ImageType.VIDEO, media.getThumbnailUrl(), ScaleType.CENTER_CROP);
                }
                return;
            }
        }
        // hasn't supplied the thumbnail url
        final String filePath;
        if ((ListenerUtil.mutListener.listen(7014) ? (!TextUtils.isEmpty(media.getFilePath()) || new File(media.getFilePath()).exists()) : (!TextUtils.isEmpty(media.getFilePath()) && new File(media.getFilePath()).exists()))) {
            filePath = media.getFilePath();
        } else {
            filePath = media.getUrl();
        }
        if (!ListenerUtil.mutListener.listen(7016)) {
            if (TextUtils.isEmpty(filePath)) {
                if (!ListenerUtil.mutListener.listen(7015)) {
                    AppLog.w(AppLog.T.MEDIA, "MediaGridAdapter > No path to video thumbnail");
                }
                return;
            }
        }
        // see if we have a cached thumbnail before retrieving it
        Bitmap bitmap = WordPress.getBitmapCache().get(filePath);
        if (!ListenerUtil.mutListener.listen(7018)) {
            if (bitmap != null) {
                if (!ListenerUtil.mutListener.listen(7017)) {
                    mImageManager.load(imageView, bitmap, ScaleType.CENTER_CROP);
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(7019)) {
            imageView.setTag(R.id.media_grid_remote_thumb_extract_id, VIEW_TAG_EXTRACT_FROM_REMOTE_VIDEO_URL);
        }
        if (!ListenerUtil.mutListener.listen(7025)) {
            mImageManager.loadThumbnailFromVideoUrl(mAppScope, imageView, filePath, ScaleType.CENTER_CROP, new ImageManager.RequestListener<Drawable>() {

                @Override
                public void onLoadFailed(@Nullable Exception e, @Nullable Object model) {
                    if (!ListenerUtil.mutListener.listen(7021)) {
                        if (e != null) {
                            if (!ListenerUtil.mutListener.listen(7020)) {
                                AppLog.d(AppLog.T.MEDIA, "MediaGridAdapter > error loading video thumbnail = " + e);
                            }
                        }
                    }
                }

                @Override
                public void onResourceReady(@NonNull Drawable resource, @Nullable Object model) {
                    if (!ListenerUtil.mutListener.listen(7022)) {
                        imageView.setTag(R.id.media_grid_remote_thumb_extract_id, null);
                    }
                    Bitmap bitmap = ((BitmapDrawable) resource).getBitmap();
                    if (!ListenerUtil.mutListener.listen(7023)) {
                        // create a copy since the original bitmap may by automatically recycled
                        bitmap = bitmap.copy(bitmap.getConfig(), true);
                    }
                    if (!ListenerUtil.mutListener.listen(7024)) {
                        WordPress.getBitmapCache().put(filePath, bitmap);
                    }
                }
            });
        }
    }

    public boolean isEmpty() {
        return mMediaList.isEmpty();
    }

    @Override
    public int getItemCount() {
        return mMediaList.size();
    }

    public static int getColumnCount(Context context) {
        return DisplayUtils.isLandscape(context) ? 4 : 3;
    }

    public void setCallback(MediaGridAdapterCallback callback) {
        if (!ListenerUtil.mutListener.listen(7026)) {
            mCallback = callback;
        }
    }

    public void setHasRetrievedAll(boolean b) {
        if (!ListenerUtil.mutListener.listen(7027)) {
            mHasRetrievedAll = b;
        }
    }

    void setLoadThumbnails(boolean loadThumbnails) {
        if (!ListenerUtil.mutListener.listen(7032)) {
            if (loadThumbnails != mLoadThumbnails) {
                if (!ListenerUtil.mutListener.listen(7028)) {
                    mLoadThumbnails = loadThumbnails;
                }
                if (!ListenerUtil.mutListener.listen(7029)) {
                    AppLog.d(AppLog.T.MEDIA, "MediaGridAdapter > loadThumbnails = " + loadThumbnails);
                }
                if (!ListenerUtil.mutListener.listen(7031)) {
                    if (mLoadThumbnails) {
                        if (!ListenerUtil.mutListener.listen(7030)) {
                            notifyDataSetChanged();
                        }
                    }
                }
            }
        }
    }

    public void clearSelection() {
        if (!ListenerUtil.mutListener.listen(7040)) {
            if ((ListenerUtil.mutListener.listen(7037) ? (mSelectedItems.size() >= 0) : (ListenerUtil.mutListener.listen(7036) ? (mSelectedItems.size() <= 0) : (ListenerUtil.mutListener.listen(7035) ? (mSelectedItems.size() < 0) : (ListenerUtil.mutListener.listen(7034) ? (mSelectedItems.size() != 0) : (ListenerUtil.mutListener.listen(7033) ? (mSelectedItems.size() == 0) : (mSelectedItems.size() > 0))))))) {
                if (!ListenerUtil.mutListener.listen(7038)) {
                    mSelectedItems.clear();
                }
                if (!ListenerUtil.mutListener.listen(7039)) {
                    notifyDataSetChanged();
                }
            }
        }
    }

    public boolean isItemSelected(int localMediaId) {
        return mSelectedItems.contains(localMediaId);
    }

    public void removeSelectionByLocalId(int localMediaId) {
        if (!ListenerUtil.mutListener.listen(7045)) {
            if (isItemSelected(localMediaId)) {
                if (!ListenerUtil.mutListener.listen(7041)) {
                    mSelectedItems.remove(Integer.valueOf(localMediaId));
                }
                if (!ListenerUtil.mutListener.listen(7043)) {
                    if (mCallback != null) {
                        if (!ListenerUtil.mutListener.listen(7042)) {
                            mCallback.onAdapterSelectionCountChanged(mSelectedItems.size());
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(7044)) {
                    notifyDataSetChanged();
                }
            }
        }
    }

    private void setItemSelectedByPosition(GridViewHolder holder, int position, boolean isVideo, boolean selected) {
        if (!ListenerUtil.mutListener.listen(7046)) {
            if (!isValidPosition(position)) {
                return;
            }
        }
        int localMediaId = mMediaList.get(position).getId();
        if (!ListenerUtil.mutListener.listen(7049)) {
            if (selected) {
                if (!ListenerUtil.mutListener.listen(7048)) {
                    mSelectedItems.add(localMediaId);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(7047)) {
                    mSelectedItems.remove(Integer.valueOf(localMediaId));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(7056)) {
            // show and animate the count
            if (selected) {
                if (!ListenerUtil.mutListener.listen(7055)) {
                    holder.mSelectionCountTextView.setText(String.format(Locale.getDefault(), "%d", (ListenerUtil.mutListener.listen(7054) ? (mSelectedItems.indexOf(localMediaId) % 1) : (ListenerUtil.mutListener.listen(7053) ? (mSelectedItems.indexOf(localMediaId) / 1) : (ListenerUtil.mutListener.listen(7052) ? (mSelectedItems.indexOf(localMediaId) * 1) : (ListenerUtil.mutListener.listen(7051) ? (mSelectedItems.indexOf(localMediaId) - 1) : (mSelectedItems.indexOf(localMediaId) + 1)))))));
                }
            } else {
                if (!ListenerUtil.mutListener.listen(7050)) {
                    holder.mSelectionCountTextView.setText(null);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(7057)) {
            AniUtils.startAnimation(holder.mSelectionCountContainer, R.anim.pop);
        }
        if (!ListenerUtil.mutListener.listen(7058)) {
            holder.mSelectionCountTextView.setVisibility(selected ? View.VISIBLE : View.GONE);
        }
        if (!ListenerUtil.mutListener.listen(7061)) {
            // scale the thumbnail
            if (selected) {
                if (!ListenerUtil.mutListener.listen(7060)) {
                    AniUtils.scale(holder.mImageView, SCALE_NORMAL, SCALE_SELECTED, AniUtils.Duration.SHORT);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(7059)) {
                    AniUtils.scale(holder.mImageView, SCALE_SELECTED, SCALE_NORMAL, AniUtils.Duration.SHORT);
                }
            }
        }
        // redraw after the scale animation completes
        long delayMs = AniUtils.Duration.SHORT.toMillis(mContext);
        if (!ListenerUtil.mutListener.listen(7063)) {
            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    if (!ListenerUtil.mutListener.listen(7062)) {
                        notifyDataSetChanged();
                    }
                }
            }, delayMs);
        }
        if (!ListenerUtil.mutListener.listen(7065)) {
            if (mCallback != null) {
                if (!ListenerUtil.mutListener.listen(7064)) {
                    mCallback.onAdapterSelectionCountChanged(mSelectedItems.size());
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(7066)) {
            PhotoPickerUtils.announceSelectedMediaForAccessibility(holder.mImageView, isVideo, selected);
        }
    }

    private void toggleItemSelected(GridViewHolder holder, int position) {
        if (!ListenerUtil.mutListener.listen(7067)) {
            if (!isValidPosition(position)) {
                return;
            }
        }
        boolean isSelected = isItemSelectedByPosition(position);
        boolean isVideo = isItemIsVideoByPosition(position);
        if (!ListenerUtil.mutListener.listen(7068)) {
            setItemSelectedByPosition(holder, position, isVideo, !isSelected);
        }
    }

    private boolean isItemSelectedByPosition(int position) {
        int localMediaId = mMediaList.get(position).getId();
        return mSelectedItems.contains(localMediaId);
    }

    private boolean isItemIsVideoByPosition(int position) {
        return mMediaList.get(position).isVideo();
    }

    public void setSelectedItems(ArrayList<Integer> selectedItems) {
        if (!ListenerUtil.mutListener.listen(7069)) {
            mSelectedItems.clear();
        }
        if (!ListenerUtil.mutListener.listen(7070)) {
            mSelectedItems.addAll(selectedItems);
        }
        if (!ListenerUtil.mutListener.listen(7072)) {
            if (mCallback != null) {
                if (!ListenerUtil.mutListener.listen(7071)) {
                    mCallback.onAdapterSelectionCountChanged(mSelectedItems.size());
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(7073)) {
            notifyDataSetChanged();
        }
    }

    private String getLabelForMediaUploadState(MediaUploadState uploadState) {
        if (!ListenerUtil.mutListener.listen(7074)) {
            switch(uploadState) {
                case QUEUED:
                    return mContext.getString(R.string.media_upload_state_queued);
                case UPLOADING:
                    return mContext.getString(R.string.media_upload_state_uploading);
                case DELETING:
                    return mContext.getString(R.string.media_upload_state_deleting);
                case DELETED:
                    return mContext.getString(R.string.media_upload_state_deleted);
                case FAILED:
                    return mContext.getString(R.string.media_upload_state_failed);
                case UPLOADED:
                    return mContext.getString(R.string.media_upload_state_uploaded);
            }
        }
        return "";
    }

    void updateMediaItem(@NonNull MediaModel media, boolean forceUpdate) {
        int index = indexOfMedia(media);
        if (!ListenerUtil.mutListener.listen(7084)) {
            if ((ListenerUtil.mutListener.listen(7081) ? ((ListenerUtil.mutListener.listen(7079) ? (index >= -1) : (ListenerUtil.mutListener.listen(7078) ? (index <= -1) : (ListenerUtil.mutListener.listen(7077) ? (index < -1) : (ListenerUtil.mutListener.listen(7076) ? (index != -1) : (ListenerUtil.mutListener.listen(7075) ? (index == -1) : (index > -1)))))) || ((ListenerUtil.mutListener.listen(7080) ? (forceUpdate && !media.equals(mMediaList.get(index))) : (forceUpdate || !media.equals(mMediaList.get(index)))))) : ((ListenerUtil.mutListener.listen(7079) ? (index >= -1) : (ListenerUtil.mutListener.listen(7078) ? (index <= -1) : (ListenerUtil.mutListener.listen(7077) ? (index < -1) : (ListenerUtil.mutListener.listen(7076) ? (index != -1) : (ListenerUtil.mutListener.listen(7075) ? (index == -1) : (index > -1)))))) && ((ListenerUtil.mutListener.listen(7080) ? (forceUpdate && !media.equals(mMediaList.get(index))) : (forceUpdate || !media.equals(mMediaList.get(index)))))))) {
                if (!ListenerUtil.mutListener.listen(7082)) {
                    mMediaList.set(index, media);
                }
                if (!ListenerUtil.mutListener.listen(7083)) {
                    notifyItemChanged(index);
                }
            }
        }
    }

    void removeMediaItem(@NonNull MediaModel media) {
        int index = indexOfMedia(media);
        if (!ListenerUtil.mutListener.listen(7092)) {
            if ((ListenerUtil.mutListener.listen(7089) ? (index >= -1) : (ListenerUtil.mutListener.listen(7088) ? (index <= -1) : (ListenerUtil.mutListener.listen(7087) ? (index < -1) : (ListenerUtil.mutListener.listen(7086) ? (index != -1) : (ListenerUtil.mutListener.listen(7085) ? (index == -1) : (index > -1))))))) {
                if (!ListenerUtil.mutListener.listen(7090)) {
                    mMediaList.remove(index);
                }
                if (!ListenerUtil.mutListener.listen(7091)) {
                    notifyItemRemoved(index);
                }
            }
        }
    }

    boolean mediaExists(@NonNull MediaModel media) {
        return (ListenerUtil.mutListener.listen(7097) ? (indexOfMedia(media) >= -1) : (ListenerUtil.mutListener.listen(7096) ? (indexOfMedia(media) <= -1) : (ListenerUtil.mutListener.listen(7095) ? (indexOfMedia(media) < -1) : (ListenerUtil.mutListener.listen(7094) ? (indexOfMedia(media) != -1) : (ListenerUtil.mutListener.listen(7093) ? (indexOfMedia(media) == -1) : (indexOfMedia(media) > -1))))));
    }

    private int indexOfMedia(@NonNull MediaModel media) {
        if (!ListenerUtil.mutListener.listen(7104)) {
            {
                long _loopCounter157 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(7103) ? (i >= mMediaList.size()) : (ListenerUtil.mutListener.listen(7102) ? (i <= mMediaList.size()) : (ListenerUtil.mutListener.listen(7101) ? (i > mMediaList.size()) : (ListenerUtil.mutListener.listen(7100) ? (i != mMediaList.size()) : (ListenerUtil.mutListener.listen(7099) ? (i == mMediaList.size()) : (i < mMediaList.size())))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter157", ++_loopCounter157);
                    if (!ListenerUtil.mutListener.listen(7098)) {
                        if (media.getId() == mMediaList.get(i).getId()) {
                            return i;
                        }
                    }
                }
            }
        }
        return -1;
    }

    /*
     * returns true if the passed list is the same as the existing one
     */
    private boolean isSameList(@NonNull List<MediaModel> otherList) {
        if (!ListenerUtil.mutListener.listen(7105)) {
            if (otherList.size() != mMediaList.size()) {
                return false;
            }
        }
        if (!ListenerUtil.mutListener.listen(7107)) {
            {
                long _loopCounter158 = 0;
                for (MediaModel otherMedia : otherList) {
                    ListenerUtil.loopListener.listen("_loopCounter158", ++_loopCounter158);
                    if (!ListenerUtil.mutListener.listen(7106)) {
                        if (!mediaExists(otherMedia)) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }
}
