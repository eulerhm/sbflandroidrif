package com.github.pockethub.android.markwon;

import android.text.style.URLSpan;
import androidx.annotation.NonNull;
import org.commonmark.node.Image;
import org.commonmark.node.Link;
import io.noties.markwon.AbstractMarkwonPlugin;
import io.noties.markwon.MarkwonSpansFactory;
import io.noties.markwon.SpanFactory;
import io.noties.markwon.core.CoreProps;
import io.noties.markwon.image.ImageProps;
import io.noties.markwon.urlprocessor.UrlProcessor;
import io.noties.markwon.urlprocessor.UrlProcessorRelativeToAbsolute;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class SpanLinkPlugin extends AbstractMarkwonPlugin {

    private UrlProcessor imageUrlProcessor;

    private UrlProcessor linkUrlProcessor;

    public SpanLinkPlugin(String baseUrl) {
        if (!ListenerUtil.mutListener.listen(588)) {
            this.imageUrlProcessor = new UrlProcessorRelativeToAbsolute(String.format(baseUrl, "raw"));
        }
        if (!ListenerUtil.mutListener.listen(589)) {
            this.linkUrlProcessor = new UrlProcessorRelativeToAbsolute(String.format(baseUrl, "blob"));
        }
    }

    @Override
    public void configureSpansFactory(@NonNull MarkwonSpansFactory.Builder builder) {
        final SpanFactory imageOrigin = builder.getFactory(Image.class);
        if (!ListenerUtil.mutListener.listen(591)) {
            if (imageOrigin != null) {
                if (!ListenerUtil.mutListener.listen(590)) {
                    builder.setFactory(Image.class, (configuration, props) -> {
                        String dest = ImageProps.DESTINATION.require(props);
                        ImageProps.DESTINATION.set(props, imageUrlProcessor.process(dest));
                        return new Object[] { imageOrigin.getSpans(configuration, props), new URLSpan(linkUrlProcessor.process(dest)) };
                    });
                }
            }
        }
        final SpanFactory linkOrigin = builder.getFactory(Link.class);
        if (!ListenerUtil.mutListener.listen(593)) {
            if (linkOrigin != null) {
                if (!ListenerUtil.mutListener.listen(592)) {
                    builder.setFactory(Link.class, (configuration, props) -> {
                        String dest = CoreProps.LINK_DESTINATION.require(props);
                        CoreProps.LINK_DESTINATION.set(props, linkUrlProcessor.process(dest));
                        return new Object[] { linkOrigin.getSpans(configuration, props) };
                    });
                }
            }
        }
    }
}
