package com.github.pockethub.android.markwon;

import androidx.annotation.NonNull;
import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.ResourceDecoder;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.resource.SimpleResource;
import com.caverock.androidsvg.SVG;
import com.caverock.androidsvg.SVGParseException;
import java.io.IOException;
import java.io.InputStream;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Decodes an SVG internal representation from an {@link InputStream}.
 */
public class SvgDecoder implements ResourceDecoder<InputStream, SVG> {

    @Override
    public boolean handles(@NonNull InputStream source, @NonNull Options options) {
        // TODO: Can we tell?
        return true;
    }

    public Resource<SVG> decode(@NonNull InputStream source, int width, int height, @NonNull Options options) throws IOException {
        try {
            SVG svg = SVG.getFromInputStream(source);
            if (!ListenerUtil.mutListener.listen(600)) {
                if ((ListenerUtil.mutListener.listen(598) ? (width >= 0) : (ListenerUtil.mutListener.listen(597) ? (width <= 0) : (ListenerUtil.mutListener.listen(596) ? (width < 0) : (ListenerUtil.mutListener.listen(595) ? (width != 0) : (ListenerUtil.mutListener.listen(594) ? (width == 0) : (width > 0))))))) {
                    if (!ListenerUtil.mutListener.listen(599)) {
                        svg.setDocumentWidth(width);
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(607)) {
                if ((ListenerUtil.mutListener.listen(605) ? (height >= 0) : (ListenerUtil.mutListener.listen(604) ? (height <= 0) : (ListenerUtil.mutListener.listen(603) ? (height < 0) : (ListenerUtil.mutListener.listen(602) ? (height != 0) : (ListenerUtil.mutListener.listen(601) ? (height == 0) : (height > 0))))))) {
                    if (!ListenerUtil.mutListener.listen(606)) {
                        svg.setDocumentHeight(height);
                    }
                }
            }
            return new SimpleResource<>(svg);
        } catch (SVGParseException ex) {
            throw new IOException("Cannot load SVG from stream", ex);
        }
    }
}
