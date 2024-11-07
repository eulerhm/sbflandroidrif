package com.github.pockethub.android.markwon;

import android.content.Context;
import androidx.annotation.NonNull;
import org.commonmark.node.AbstractVisitor;
import org.commonmark.node.FencedCodeBlock;
import org.commonmark.node.HtmlBlock;
import org.commonmark.node.Node;
import org.commonmark.node.Visitor;
import org.commonmark.parser.Parser;
import org.commonmark.parser.PostProcessor;
import io.noties.markwon.AbstractMarkwonPlugin;
import io.noties.markwon.Markwon;
import io.noties.markwon.MarkwonVisitor;
import io.noties.markwon.ext.strikethrough.StrikethroughPlugin;
import io.noties.markwon.ext.tables.TablePlugin;
import io.noties.markwon.ext.tasklist.TaskListPlugin;
import io.noties.markwon.html.HtmlPlugin;
import io.noties.markwon.image.glide.GlideImagesPlugin;
import io.noties.markwon.recycler.table.TableEntryPlugin;
import io.noties.markwon.syntax.Prism4jThemeDefault;
import io.noties.markwon.syntax.SyntaxHighlightPlugin;
import io.noties.prism4j.Prism4j;
import io.noties.prism4j.annotations.PrismBundle;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

@PrismBundle(includeAll = true)
public class MarkwonUtils {

    public static Markwon createMarkwon(Context context, String baseUrl) {
        final Prism4j prism4j = new Prism4j(new GrammarLocatorDef());
        return Markwon.builder(context).usePlugin(StrikethroughPlugin.create()).usePlugin(TaskListPlugin.create(context)).usePlugin(HtmlPlugin.create()).usePlugin(new AbstractMarkwonPlugin() {

            @Override
            public void configureVisitor(@NonNull MarkwonVisitor.Builder builder) {
                if (!ListenerUtil.mutListener.listen(581)) {
                    builder.on(FencedCodeBlock.class, (visitor, fencedCodeBlock) -> {
                        // NB the `trim` operation on literal (as code will have a new line at the end)
                        final CharSequence code = visitor.configuration().syntaxHighlight().highlight(fencedCodeBlock.getInfo(), fencedCodeBlock.getLiteral().trim());
                        visitor.builder().append(code);
                    });
                }
            }

            @Override
            public void configureParser(@NonNull Parser.Builder builder) {
                if (!ListenerUtil.mutListener.listen(582)) {
                    super.configureParser(builder);
                }
                if (!ListenerUtil.mutListener.listen(587)) {
                    builder.postProcessor(new PostProcessor() {

                        @Override
                        public Node process(Node node) {
                            Visitor t = new AbstractVisitor() {

                                @Override
                                public void visit(HtmlBlock htmlBlock) {
                                    String literal = htmlBlock.getLiteral();
                                    if (!ListenerUtil.mutListener.listen(585)) {
                                        if (literal.startsWith("<!--")) {
                                            if (!ListenerUtil.mutListener.listen(584)) {
                                                htmlBlock.unlink();
                                            }
                                        } else {
                                            if (!ListenerUtil.mutListener.listen(583)) {
                                                super.visit(htmlBlock);
                                            }
                                        }
                                    }
                                }
                            };
                            if (!ListenerUtil.mutListener.listen(586)) {
                                node.accept(t);
                            }
                            return node;
                        }
                    });
                }
            }
        }).usePlugin(GlideImagesPlugin.create(new GifAwareGlideStore(context))).usePlugin(new SpanLinkPlugin(baseUrl)).usePlugin(new AbstractMarkwonPlugin() {

            @Override
            public void configure(@NonNull Registry registry) {
                if (!ListenerUtil.mutListener.listen(580)) {
                    registry.require(HtmlPlugin.class, htmlPlugin -> htmlPlugin.addHandler(new AlignHandler()));
                }
            }
        }).usePlugin(TableEntryPlugin.create(TablePlugin.create(context))).usePlugin(SyntaxHighlightPlugin.create(prism4j, Prism4jThemeDefault.create())).usePlugin(new AsyncDrawableSchedulerPlugin()).build();
    }
}
