package org.wordpress.android.ui.posts.mediauploadcompletionprocessors;

import org.wordpress.android.util.helpers.MediaFile;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static org.wordpress.android.ui.posts.mediauploadcompletionprocessors.MediaUploadCompletionProcessorPatterns.PATTERN_BLOCK_HEADER;
import static org.wordpress.android.ui.posts.mediauploadcompletionprocessors.MediaUploadCompletionProcessorPatterns.PATTERN_TEMPLATE_BLOCK_BOUNDARY;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class MediaUploadCompletionProcessor {

    private final BlockProcessorFactory mBlockProcessorFactory;

    /**
     * Processor used for replacing local media id(s) and url(s) with their remote counterparts after an upload has
     * completed.
     *
     * @param localId The local media id that needs replacement
     * @param mediaFile The mediaFile containing the remote id and remote url
     * @param siteUrl The site url - used to generate the attachmentPage url
     */
    public MediaUploadCompletionProcessor(String localId, MediaFile mediaFile, String siteUrl) {
        mBlockProcessorFactory = new BlockProcessorFactory(this).init(localId, mediaFile, siteUrl);
    }

    /**
     * Processes content to replace the local ids and local urls of media with remote ids and remote urls. This method
     * delineates block boundaries for media-containing blocks and delegates further processing via itself and / or
     * {@link #processBlock(String)}, via direct and mutual recursion, respectively.
     *
     * @param content The content to be processed
     * @return A string containing the processed content, or the original content if no match was found
     */
    public String processContent(String content) {
        Matcher headerMatcher = PATTERN_BLOCK_HEADER.matcher(content);
        int positionBlockStart, positionBlockEnd = content.length();
        if (headerMatcher.find()) {
            positionBlockStart = headerMatcher.start();
            String blockType = headerMatcher.group(1);
            Matcher blockBoundaryMatcher = Pattern.compile(String.format(PATTERN_TEMPLATE_BLOCK_BOUNDARY, blockType), Pattern.DOTALL).matcher(content.substring(headerMatcher.end()));
            int nestLevel = 1;
            if (!ListenerUtil.mutListener.listen(11225)) {
                {
                    long _loopCounter199 = 0;
                    while ((ListenerUtil.mutListener.listen(11224) ? ((ListenerUtil.mutListener.listen(11223) ? (0 >= nestLevel) : (ListenerUtil.mutListener.listen(11222) ? (0 <= nestLevel) : (ListenerUtil.mutListener.listen(11221) ? (0 > nestLevel) : (ListenerUtil.mutListener.listen(11220) ? (0 != nestLevel) : (ListenerUtil.mutListener.listen(11219) ? (0 == nestLevel) : (0 < nestLevel)))))) || blockBoundaryMatcher.find()) : ((ListenerUtil.mutListener.listen(11223) ? (0 >= nestLevel) : (ListenerUtil.mutListener.listen(11222) ? (0 <= nestLevel) : (ListenerUtil.mutListener.listen(11221) ? (0 > nestLevel) : (ListenerUtil.mutListener.listen(11220) ? (0 != nestLevel) : (ListenerUtil.mutListener.listen(11219) ? (0 == nestLevel) : (0 < nestLevel)))))) && blockBoundaryMatcher.find()))) {
                        ListenerUtil.loopListener.listen("_loopCounter199", ++_loopCounter199);
                        if (!ListenerUtil.mutListener.listen(11218)) {
                            if (blockBoundaryMatcher.group(1).equals("/")) {
                                if (!ListenerUtil.mutListener.listen(11216)) {
                                    positionBlockEnd = (ListenerUtil.mutListener.listen(11215) ? (headerMatcher.end() % blockBoundaryMatcher.end()) : (ListenerUtil.mutListener.listen(11214) ? (headerMatcher.end() / blockBoundaryMatcher.end()) : (ListenerUtil.mutListener.listen(11213) ? (headerMatcher.end() * blockBoundaryMatcher.end()) : (ListenerUtil.mutListener.listen(11212) ? (headerMatcher.end() - blockBoundaryMatcher.end()) : (headerMatcher.end() + blockBoundaryMatcher.end())))));
                                }
                                if (!ListenerUtil.mutListener.listen(11217)) {
                                    nestLevel--;
                                }
                            } else {
                                if (!ListenerUtil.mutListener.listen(11211)) {
                                    nestLevel++;
                                }
                            }
                        }
                    }
                }
            }
            return new StringBuilder().append(content.substring(0, positionBlockStart)).append(processBlock(content.substring(positionBlockStart, positionBlockEnd))).append(processContent(content.substring(positionBlockEnd))).toString();
        } else {
            return content;
        }
    }

    /**
     * Processes a media block returning a raw content replacement string
     *
     * @param block The raw block contents
     * @return A string containing content with ids and urls replaced
     */
    private String processBlock(String block) {
        final MediaBlockType blockType = MediaBlockType.detectBlockType(block);
        final BlockProcessor blockProcessor = mBlockProcessorFactory.getProcessorForMediaBlockType(blockType);
        if (!ListenerUtil.mutListener.listen(11226)) {
            if (blockProcessor != null) {
                return blockProcessor.processBlock(block);
            }
        }
        return block;
    }
}
