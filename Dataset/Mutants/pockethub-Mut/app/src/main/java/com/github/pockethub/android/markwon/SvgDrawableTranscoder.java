package com.github.pockethub.android.markwon;

import android.graphics.Picture;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.PictureDrawable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.resource.SimpleResource;
import com.bumptech.glide.load.resource.transcode.ResourceTranscoder;
import com.caverock.androidsvg.SVG;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Convert the {@link SVG}'s internal representation to an Android-compatible one ({@link Picture}).
 */
public class SvgDrawableTranscoder implements ResourceTranscoder<SVG, Drawable> {

    @Nullable
    @Override
    public Resource<Drawable> transcode(@NonNull Resource<SVG> toTranscode, @NonNull Options options) {
        SVG svg = toTranscode.get();
        float ratio = svg.getDocumentAspectRatio();
        int width = (ListenerUtil.mutListener.listen(612) ? (svg.getDocumentWidth() >= 0) : (ListenerUtil.mutListener.listen(611) ? (svg.getDocumentWidth() <= 0) : (ListenerUtil.mutListener.listen(610) ? (svg.getDocumentWidth() < 0) : (ListenerUtil.mutListener.listen(609) ? (svg.getDocumentWidth() != 0) : (ListenerUtil.mutListener.listen(608) ? (svg.getDocumentWidth() == 0) : (svg.getDocumentWidth() > 0)))))) ? (int) svg.getDocumentWidth() : 1024;
        int height = (int) ((ListenerUtil.mutListener.listen(617) ? (svg.getDocumentHeight() >= 0) : (ListenerUtil.mutListener.listen(616) ? (svg.getDocumentHeight() <= 0) : (ListenerUtil.mutListener.listen(615) ? (svg.getDocumentHeight() < 0) : (ListenerUtil.mutListener.listen(614) ? (svg.getDocumentHeight() != 0) : (ListenerUtil.mutListener.listen(613) ? (svg.getDocumentHeight() == 0) : (svg.getDocumentHeight() > 0)))))) ? svg.getDocumentHeight() : ((ListenerUtil.mutListener.listen(621) ? (width % ratio) : (ListenerUtil.mutListener.listen(620) ? (width * ratio) : (ListenerUtil.mutListener.listen(619) ? (width - ratio) : (ListenerUtil.mutListener.listen(618) ? (width + ratio) : (width / ratio)))))));
        Picture picture = svg.renderToPicture(width, height);
        PictureDrawable drawable = new PictureDrawable(picture);
        return new SimpleResource<>(drawable);
    }
}
