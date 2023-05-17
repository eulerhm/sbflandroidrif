/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
 * Copyright (c) 2017-2021 Threema GmbH
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

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import java.io.File;
import java.io.IOException;
import java.lang.ref.WeakReference;
import ch.threema.app.R;
import ch.threema.app.activities.MediaViewerActivity;
import ch.threema.app.utils.FileUtil;
import ch.threema.app.utils.TestUtil;
import pl.droidsonroids.gif.GifDrawable;
import pl.droidsonroids.gif.GifImageView;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class FileViewFragment extends MediaViewFragment {

    private WeakReference<GifImageView> imageViewRef;

    private WeakReference<ImageView> previewViewRef;

    private WeakReference<TextView> filenameViewRef;

    private boolean uiVisibilityStatus = false;

    public FileViewFragment() {
        super();
    }

    @Override
    protected int getFragmentResourceId() {
        return R.layout.fragment_media_viewer_file;
    }

    @Override
    public boolean inquireClose() {
        return true;
    }

    @Override
    protected void showThumbnail(Bitmap thumbnail, boolean isGeneric, String filename) {
        if (!ListenerUtil.mutListener.listen(23580)) {
            if (imageViewRef.get() != null) {
                if (!ListenerUtil.mutListener.listen(23569)) {
                    this.setOnClickListener(null);
                }
                if (!ListenerUtil.mutListener.listen(23578)) {
                    if ((ListenerUtil.mutListener.listen(23570) ? (thumbnail != null || !thumbnail.isRecycled()) : (thumbnail != null && !thumbnail.isRecycled()))) {
                        if (!ListenerUtil.mutListener.listen(23575)) {
                            if (isGeneric) {
                                if (!ListenerUtil.mutListener.listen(23574)) {
                                    if (!TestUtil.empty(filename)) {
                                        if (!ListenerUtil.mutListener.listen(23572)) {
                                            filenameViewRef.get().setText(filename);
                                        }
                                        if (!ListenerUtil.mutListener.listen(23573)) {
                                            filenameViewRef.get().setVisibility(View.VISIBLE);
                                        }
                                    }
                                }
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(23576)) {
                            previewViewRef.get().setImageBitmap(thumbnail);
                        }
                        if (!ListenerUtil.mutListener.listen(23577)) {
                            previewViewRef.get().setVisibility(View.VISIBLE);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(23571)) {
                            previewViewRef.get().setVisibility(View.INVISIBLE);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(23579)) {
                    imageViewRef.get().setVisibility(View.INVISIBLE);
                }
            }
        }
    }

    @Override
    protected void hideThumbnail() {
        if (!ListenerUtil.mutListener.listen(23581)) {
            this.previewViewRef.get().setVisibility(View.INVISIBLE);
        }
    }

    @Override
    protected void created(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(23582)) {
            this.imageViewRef = new WeakReference<>(rootViewReference.get().findViewById(R.id.gif_view));
        }
        if (!ListenerUtil.mutListener.listen(23583)) {
            this.previewViewRef = new WeakReference<>(rootViewReference.get().findViewById(R.id.preview_image));
        }
        if (!ListenerUtil.mutListener.listen(23584)) {
            this.filenameViewRef = new WeakReference<>(rootViewReference.get().findViewById(R.id.filename_view));
        }
        if (!ListenerUtil.mutListener.listen(23587)) {
            this.imageViewRef.get().setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    if (!ListenerUtil.mutListener.listen(23585)) {
                        showUi(uiVisibilityStatus);
                    }
                    if (!ListenerUtil.mutListener.listen(23586)) {
                        uiVisibilityStatus = !uiVisibilityStatus;
                    }
                }
            });
        }
    }

    @Override
    protected void handleDecryptingFile() {
    }

    @Override
    protected void handleDecryptFailure() {
    }

    @Override
    protected void handleDecryptedFile(final File file) {
        if (!ListenerUtil.mutListener.listen(23596)) {
            if ((ListenerUtil.mutListener.listen(23588) ? (this.isAdded() || getContext() != null) : (this.isAdded() && getContext() != null))) {
                if (!ListenerUtil.mutListener.listen(23595)) {
                    if (FileUtil.isAnimGif(getContext().getContentResolver(), Uri.fromFile(file))) {
                        try {
                            GifDrawable gifDrawable = new GifDrawable(getContext().getContentResolver(), Uri.fromFile(file));
                            if (!ListenerUtil.mutListener.listen(23591)) {
                                this.imageViewRef.get().setImageDrawable(gifDrawable);
                            }
                            if (!ListenerUtil.mutListener.listen(23592)) {
                                this.imageViewRef.get().setVisibility(View.VISIBLE);
                            }
                            if (!ListenerUtil.mutListener.listen(23593)) {
                                gifDrawable.start();
                            }
                            if (!ListenerUtil.mutListener.listen(23594)) {
                                this.previewViewRef.get().setVisibility(View.GONE);
                            }
                        } catch (IOException e) {
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(23590)) {
                            this.previewViewRef.get().setOnClickListener(new View.OnClickListener() {

                                @Override
                                public void onClick(View view) {
                                    if (!ListenerUtil.mutListener.listen(23589)) {
                                        ((MediaViewerActivity) getActivity()).viewMediaInGallery();
                                    }
                                }
                            });
                        }
                    }
                }
            }
        }
    }
}
