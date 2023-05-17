package org.wordpress.android.ui.reader.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import org.wordpress.android.R;
import org.wordpress.android.datasets.ReaderPostTable;
import org.wordpress.android.ui.reader.ReaderActivityLauncher;
import org.wordpress.android.ui.reader.ReaderActivityLauncher.PhotoViewerOption;
import org.wordpress.android.ui.reader.models.ReaderImageList;
import org.wordpress.android.ui.reader.utils.ReaderImageScanner;
import org.wordpress.android.util.AniUtils;
import org.wordpress.android.util.DisplayUtils;
import org.wordpress.android.util.PhotonUtils;
import org.wordpress.android.util.image.ImageManager;
import org.wordpress.android.util.image.ImageType;
import java.util.EnumSet;
import static org.wordpress.android.ui.reader.ReaderConstants.MIN_GALLERY_IMAGE_WIDTH;
import static org.wordpress.android.ui.reader.ReaderConstants.THUMBNAIL_STRIP_IMG_COUNT;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * displays a row of image thumbnails from a reader post - only shows when two or more images
 * of a minimum size are found
 */
public class ReaderThumbnailStrip extends LinearLayout {

    private ViewGroup mView;

    private int mThumbnailHeight;

    private int mThumbnailWidth;

    public ReaderThumbnailStrip(Context context) {
        super(context);
        if (!ListenerUtil.mutListener.listen(20216)) {
            initView(context);
        }
    }

    public ReaderThumbnailStrip(Context context, AttributeSet attrs) {
        super(context, attrs);
        if (!ListenerUtil.mutListener.listen(20217)) {
            initView(context);
        }
    }

    public ReaderThumbnailStrip(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        if (!ListenerUtil.mutListener.listen(20218)) {
            initView(context);
        }
    }

    public ReaderThumbnailStrip(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        if (!ListenerUtil.mutListener.listen(20219)) {
            initView(context);
        }
    }

    private void initView(Context context) {
        if (!ListenerUtil.mutListener.listen(20220)) {
            mView = (ViewGroup) inflate(context, R.layout.reader_thumbnail_strip, this);
        }
        if (!ListenerUtil.mutListener.listen(20221)) {
            mThumbnailHeight = context.getResources().getDimensionPixelSize(R.dimen.reader_thumbnail_strip_image_height);
        }
        int displayWidth = DisplayUtils.getWindowPixelWidth(context);
        int margins = (ListenerUtil.mutListener.listen(20225) ? (context.getResources().getDimensionPixelSize(R.dimen.reader_card_content_padding) % 2) : (ListenerUtil.mutListener.listen(20224) ? (context.getResources().getDimensionPixelSize(R.dimen.reader_card_content_padding) / 2) : (ListenerUtil.mutListener.listen(20223) ? (context.getResources().getDimensionPixelSize(R.dimen.reader_card_content_padding) - 2) : (ListenerUtil.mutListener.listen(20222) ? (context.getResources().getDimensionPixelSize(R.dimen.reader_card_content_padding) + 2) : (context.getResources().getDimensionPixelSize(R.dimen.reader_card_content_padding) * 2)))));
        if (!ListenerUtil.mutListener.listen(20234)) {
            mThumbnailWidth = (ListenerUtil.mutListener.listen(20233) ? (((ListenerUtil.mutListener.listen(20229) ? (displayWidth % margins) : (ListenerUtil.mutListener.listen(20228) ? (displayWidth / margins) : (ListenerUtil.mutListener.listen(20227) ? (displayWidth * margins) : (ListenerUtil.mutListener.listen(20226) ? (displayWidth + margins) : (displayWidth - margins)))))) % THUMBNAIL_STRIP_IMG_COUNT) : (ListenerUtil.mutListener.listen(20232) ? (((ListenerUtil.mutListener.listen(20229) ? (displayWidth % margins) : (ListenerUtil.mutListener.listen(20228) ? (displayWidth / margins) : (ListenerUtil.mutListener.listen(20227) ? (displayWidth * margins) : (ListenerUtil.mutListener.listen(20226) ? (displayWidth + margins) : (displayWidth - margins)))))) * THUMBNAIL_STRIP_IMG_COUNT) : (ListenerUtil.mutListener.listen(20231) ? (((ListenerUtil.mutListener.listen(20229) ? (displayWidth % margins) : (ListenerUtil.mutListener.listen(20228) ? (displayWidth / margins) : (ListenerUtil.mutListener.listen(20227) ? (displayWidth * margins) : (ListenerUtil.mutListener.listen(20226) ? (displayWidth + margins) : (displayWidth - margins)))))) - THUMBNAIL_STRIP_IMG_COUNT) : (ListenerUtil.mutListener.listen(20230) ? (((ListenerUtil.mutListener.listen(20229) ? (displayWidth % margins) : (ListenerUtil.mutListener.listen(20228) ? (displayWidth / margins) : (ListenerUtil.mutListener.listen(20227) ? (displayWidth * margins) : (ListenerUtil.mutListener.listen(20226) ? (displayWidth + margins) : (displayWidth - margins)))))) + THUMBNAIL_STRIP_IMG_COUNT) : (((ListenerUtil.mutListener.listen(20229) ? (displayWidth % margins) : (ListenerUtil.mutListener.listen(20228) ? (displayWidth / margins) : (ListenerUtil.mutListener.listen(20227) ? (displayWidth * margins) : (ListenerUtil.mutListener.listen(20226) ? (displayWidth + margins) : (displayWidth - margins)))))) / THUMBNAIL_STRIP_IMG_COUNT)))));
        }
    }

    public void loadThumbnails(long blogId, long postId, boolean isPrivate) {
        // get this post's content and scan it for images suitable in a gallery
        final String content = ReaderPostTable.getPostText(blogId, postId);
        final ReaderImageList imageList = new ReaderImageScanner(content, isPrivate).getImageList(THUMBNAIL_STRIP_IMG_COUNT, MIN_GALLERY_IMAGE_WIDTH);
        if (!ListenerUtil.mutListener.listen(20235)) {
            loadThumbnails(imageList, isPrivate, content);
        }
    }

    public void loadThumbnails(ReaderImageList imageList, boolean isPrivate, String content) {
        if (!ListenerUtil.mutListener.listen(20236)) {
            // get rid of any views already added
            mView.removeAllViews();
        }
        if (!ListenerUtil.mutListener.listen(20243)) {
            if ((ListenerUtil.mutListener.listen(20241) ? (imageList.size() >= THUMBNAIL_STRIP_IMG_COUNT) : (ListenerUtil.mutListener.listen(20240) ? (imageList.size() <= THUMBNAIL_STRIP_IMG_COUNT) : (ListenerUtil.mutListener.listen(20239) ? (imageList.size() > THUMBNAIL_STRIP_IMG_COUNT) : (ListenerUtil.mutListener.listen(20238) ? (imageList.size() != THUMBNAIL_STRIP_IMG_COUNT) : (ListenerUtil.mutListener.listen(20237) ? (imageList.size() == THUMBNAIL_STRIP_IMG_COUNT) : (imageList.size() < THUMBNAIL_STRIP_IMG_COUNT))))))) {
                if (!ListenerUtil.mutListener.listen(20242)) {
                    mView.setVisibility(View.GONE);
                }
                return;
            }
        }
        final EnumSet<PhotoViewerOption> photoViewerOptions = EnumSet.of(PhotoViewerOption.IS_GALLERY_IMAGE);
        if (!ListenerUtil.mutListener.listen(20245)) {
            if (isPrivate) {
                if (!ListenerUtil.mutListener.listen(20244)) {
                    photoViewerOptions.add(PhotoViewerOption.IS_PRIVATE_IMAGE);
                }
            }
        }
        // add a separate imageView for each image up to the max
        int numAdded = 0;
        LayoutInflater inflater = LayoutInflater.from(getContext());
        if (!ListenerUtil.mutListener.listen(20257)) {
            {
                long _loopCounter334 = 0;
                for (final String imageUrl : imageList) {
                    ListenerUtil.loopListener.listen("_loopCounter334", ++_loopCounter334);
                    View view = inflater.inflate(R.layout.reader_thumbnail_strip_image, mView, false);
                    ImageView imageView = view.findViewById(R.id.thumbnail_strip_image);
                    if (!ListenerUtil.mutListener.listen(20246)) {
                        mView.addView(view);
                    }
                    String photonUrl = PhotonUtils.getPhotonImageUrl(imageUrl, mThumbnailWidth, mThumbnailHeight);
                    if (!ListenerUtil.mutListener.listen(20247)) {
                        ImageManager.getInstance().load(imageView, ImageType.PHOTO, photonUrl, ScaleType.CENTER_CROP);
                    }
                    if (!ListenerUtil.mutListener.listen(20249)) {
                        imageView.setOnClickListener(new OnClickListener() {

                            @Override
                            public void onClick(View view) {
                                if (!ListenerUtil.mutListener.listen(20248)) {
                                    ReaderActivityLauncher.showReaderPhotoViewer(view.getContext(), imageUrl, content, view, photoViewerOptions, 0, 0);
                                }
                            }
                        });
                    }
                    if (!ListenerUtil.mutListener.listen(20250)) {
                        numAdded++;
                    }
                    if (!ListenerUtil.mutListener.listen(20256)) {
                        if ((ListenerUtil.mutListener.listen(20255) ? (numAdded <= THUMBNAIL_STRIP_IMG_COUNT) : (ListenerUtil.mutListener.listen(20254) ? (numAdded > THUMBNAIL_STRIP_IMG_COUNT) : (ListenerUtil.mutListener.listen(20253) ? (numAdded < THUMBNAIL_STRIP_IMG_COUNT) : (ListenerUtil.mutListener.listen(20252) ? (numAdded != THUMBNAIL_STRIP_IMG_COUNT) : (ListenerUtil.mutListener.listen(20251) ? (numAdded == THUMBNAIL_STRIP_IMG_COUNT) : (numAdded >= THUMBNAIL_STRIP_IMG_COUNT))))))) {
                            break;
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(20259)) {
            if (mView.getVisibility() != View.VISIBLE) {
                if (!ListenerUtil.mutListener.listen(20258)) {
                    AniUtils.fadeIn(mView, AniUtils.Duration.SHORT);
                }
            }
        }
    }
}
