/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
 * Copyright (c) 2014-2021 Threema GmbH
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License, version 3,
 * as published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program. If not, see <https://www.gnu.org/licenses/>.
 */
package ch.threema.app.fragments.mediaviews;

import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import com.davemorrissey.labs.subscaleview.ImageSource;
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.File;
import java.lang.ref.WeakReference;
import ch.threema.app.R;
import ch.threema.app.utils.BitmapUtil;
import ch.threema.app.utils.TestUtil;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class ImageViewFragment extends MediaViewFragment {

    private static final Logger logger = LoggerFactory.getLogger(ImageViewFragment.class);

    private WeakReference<SubsamplingScaleImageView> imageViewReference;

    private WeakReference<ImageView> previewViewReference;

    private boolean uiVisibilityStatus = false;

    public ImageViewFragment() {
        super();
    }

    @Override
    protected int getFragmentResourceId() {
        return R.layout.fragment_media_viewer_image;
    }

    @Override
    public boolean inquireClose() {
        return true;
    }

    @Override
    protected void showThumbnail(Bitmap thumbnail, boolean isGeneric, String filename) {
        if (!ListenerUtil.mutListener.listen(23602)) {
            if (TestUtil.required(imageViewReference.get(), thumbnail)) {
                if (!ListenerUtil.mutListener.listen(23599)) {
                    if (!thumbnail.isRecycled()) {
                        if (!ListenerUtil.mutListener.listen(23597)) {
                            previewViewReference.get().setVisibility(View.VISIBLE);
                        }
                        if (!ListenerUtil.mutListener.listen(23598)) {
                            previewViewReference.get().setImageBitmap(thumbnail);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(23600)) {
                    logger.debug("invisible");
                }
                if (!ListenerUtil.mutListener.listen(23601)) {
                    imageViewReference.get().setVisibility(View.INVISIBLE);
                }
            }
        }
    }

    @Override
    protected void hideThumbnail() {
        if (!ListenerUtil.mutListener.listen(23603)) {
            previewViewReference.get().setVisibility(View.INVISIBLE);
        }
    }

    @Override
    protected void created(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(23604)) {
            SubsamplingScaleImageView.setPreferredBitmapConfig(Bitmap.Config.ARGB_8888);
        }
        if (!ListenerUtil.mutListener.listen(23615)) {
            if ((ListenerUtil.mutListener.listen(23605) ? (rootViewReference != null || rootViewReference.get() != null) : (rootViewReference != null && rootViewReference.get() != null))) {
                if (!ListenerUtil.mutListener.listen(23606)) {
                    imageViewReference = new WeakReference<>(rootViewReference.get().findViewById(R.id.subsampling_image));
                }
                if (!ListenerUtil.mutListener.listen(23607)) {
                    previewViewReference = new WeakReference<>(rootViewReference.get().findViewById(R.id.preview_image));
                }
                if (!ListenerUtil.mutListener.listen(23608)) {
                    imageViewReference.get().setMaxScale(8);
                }
                if (!ListenerUtil.mutListener.listen(23609)) {
                    imageViewReference.get().setDoubleTapZoomStyle(SubsamplingScaleImageView.ZOOM_FOCUS_CENTER);
                }
                if (!ListenerUtil.mutListener.listen(23612)) {
                    imageViewReference.get().setOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View view) {
                            if (!ListenerUtil.mutListener.listen(23610)) {
                                showUi(uiVisibilityStatus);
                            }
                            if (!ListenerUtil.mutListener.listen(23611)) {
                                uiVisibilityStatus = !uiVisibilityStatus;
                            }
                        }
                    });
                }
                if (!ListenerUtil.mutListener.listen(23614)) {
                    imageViewReference.get().setOnImageEventListener(new SubsamplingScaleImageView.OnImageEventListener() {

                        @Override
                        public void onReady() {
                        }

                        @Override
                        public void onImageLoaded() {
                            if (!ListenerUtil.mutListener.listen(23613)) {
                                hideThumbnail();
                            }
                        }

                        @Override
                        public void onPreviewLoadError(Exception e) {
                        }

                        @Override
                        public void onImageLoadError(Exception e) {
                        }

                        @Override
                        public void onTileLoadError(Exception e) {
                        }

                        @Override
                        public void onPreviewReleased() {
                        }
                    });
                }
            }
        }
    }

    @Override
    protected void handleDecryptingFile() {
    }

    @Override
    protected void handleDecryptFailure() {
    }

    @Override
    protected void handleDecryptedFile(File file) {
        if (!ListenerUtil.mutListener.listen(23622)) {
            if (this.isAdded()) {
                if (!ListenerUtil.mutListener.listen(23616)) {
                    imageViewReference.get().setImage(ImageSource.uri(file.getPath()));
                }
                try {
                    BitmapUtil.ExifOrientation exifOrientation = BitmapUtil.getExifOrientation(getContext(), Uri.fromFile(file));
                    if (!ListenerUtil.mutListener.listen(23618)) {
                        logger.debug("Orientation = " + exifOrientation);
                    }
                    if (!ListenerUtil.mutListener.listen(23620)) {
                        if (exifOrientation.getRotation() != 0) {
                            if (!ListenerUtil.mutListener.listen(23619)) {
                                imageViewReference.get().setOrientation((int) exifOrientation.getRotation());
                            }
                        }
                    }
                } catch (Exception e) {
                    if (!ListenerUtil.mutListener.listen(23617)) {
                        logger.error("Exception", e);
                    }
                }
                if (!ListenerUtil.mutListener.listen(23621)) {
                    imageViewReference.get().setVisibility(View.VISIBLE);
                }
            }
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        if (!ListenerUtil.mutListener.listen(23623)) {
            super.onConfigurationChanged(newConfig);
        }
        if (!ListenerUtil.mutListener.listen(23632)) {
            if ((ListenerUtil.mutListener.listen(23624) ? (this.rootViewReference != null || this.rootViewReference.get() != null) : (this.rootViewReference != null && this.rootViewReference.get() != null))) {
                if (!ListenerUtil.mutListener.listen(23631)) {
                    this.rootViewReference.get().getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

                        @Override
                        public void onGlobalLayout() {
                            if (!ListenerUtil.mutListener.listen(23630)) {
                                if ((ListenerUtil.mutListener.listen(23625) ? (rootViewReference != null || rootViewReference.get() != null) : (rootViewReference != null && rootViewReference.get() != null))) {
                                    if (!ListenerUtil.mutListener.listen(23626)) {
                                        rootViewReference.get().getViewTreeObserver().removeOnGlobalLayoutListener(this);
                                    }
                                    if (!ListenerUtil.mutListener.listen(23629)) {
                                        if ((ListenerUtil.mutListener.listen(23627) ? (imageViewReference != null || imageViewReference.get() != null) : (imageViewReference != null && imageViewReference.get() != null))) {
                                            if (!ListenerUtil.mutListener.listen(23628)) {
                                                imageViewReference.get().resetScaleAndCenter();
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    });
                }
            }
        }
    }
}
