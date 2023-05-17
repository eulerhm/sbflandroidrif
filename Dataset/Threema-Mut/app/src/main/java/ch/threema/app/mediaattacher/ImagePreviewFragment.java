/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
 * Copyright (c) 2020-2021 Threema GmbH
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
package ch.threema.app.mediaattacher;

import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomViewTarget;
import com.bumptech.glide.request.transition.Transition;
import com.davemorrissey.labs.subscaleview.ImageSource;
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import ch.threema.app.R;
import ch.threema.app.ThreemaApplication;
import ch.threema.app.ui.MediaItem;
import pl.droidsonroids.gif.GifImageView;
import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class ImagePreviewFragment extends PreviewFragment {

    private GifImageView gifView;

    private SubsamplingScaleImageView imageView;

    ImagePreviewFragment(MediaAttachItem mediaItem, MediaAttachViewModel mediaAttachViewModel) {
        super(mediaItem, mediaAttachViewModel);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(29346)) {
            this.rootView = inflater.inflate(R.layout.fragment_image_preview, container, false);
        }
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(29347)) {
            super.onViewCreated(view, savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(29357)) {
            if (rootView != null) {
                if (!ListenerUtil.mutListener.listen(29348)) {
                    this.imageView = rootView.findViewById(R.id.image_view);
                }
                if (!ListenerUtil.mutListener.listen(29349)) {
                    this.gifView = rootView.findViewById(R.id.gif_view);
                }
                if (!ListenerUtil.mutListener.listen(29356)) {
                    if (mediaItem.getType() == MediaItem.TYPE_GIF) {
                        if (!ListenerUtil.mutListener.listen(29354)) {
                            imageView.setVisibility(View.GONE);
                        }
                        if (!ListenerUtil.mutListener.listen(29355)) {
                            Glide.with(ThreemaApplication.getAppContext()).load(mediaItem.getUri()).transition(withCrossFade()).into(gifView);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(29350)) {
                            gifView.setVisibility(View.GONE);
                        }
                        if (!ListenerUtil.mutListener.listen(29353)) {
                            Glide.with(this).load(mediaItem.getUri()).transition(withCrossFade()).into(new CustomViewTarget<SubsamplingScaleImageView, Drawable>(imageView) {

                                @Override
                                public void onLoadFailed(@Nullable Drawable errorDrawable) {
                                }

                                @Override
                                public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                                    if (!ListenerUtil.mutListener.listen(29352)) {
                                        if (resource instanceof BitmapDrawable) {
                                            if (!ListenerUtil.mutListener.listen(29351)) {
                                                imageView.setImage(ImageSource.bitmap(((BitmapDrawable) resource).getBitmap()));
                                            }
                                        }
                                    }
                                }

                                @Override
                                protected void onResourceCleared(@Nullable Drawable placeholder) {
                                }
                            });
                        }
                    }
                }
            }
        }
    }
}
