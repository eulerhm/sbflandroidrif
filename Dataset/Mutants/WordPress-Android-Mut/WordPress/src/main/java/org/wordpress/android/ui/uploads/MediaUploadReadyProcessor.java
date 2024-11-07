package org.wordpress.android.ui.uploads;

import androidx.annotation.Nullable;
import org.wordpress.android.WordPress;
import org.wordpress.android.editor.AztecEditorFragment;
import org.wordpress.android.fluxc.model.PostModel;
import org.wordpress.android.fluxc.model.SiteModel;
import org.wordpress.android.ui.media.services.MediaUploadReadyListener;
import org.wordpress.android.ui.posts.PostUtils;
import org.wordpress.android.ui.prefs.AppPrefs;
import org.wordpress.android.ui.stories.SaveStoryGutenbergBlockUseCase;
import org.wordpress.android.util.helpers.MediaFile;
import javax.inject.Inject;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class MediaUploadReadyProcessor implements MediaUploadReadyListener {

    @Inject
    SaveStoryGutenbergBlockUseCase mSaveStoryGutenbergBlockUseCase;

    @Inject
    public MediaUploadReadyProcessor() {
        if (!ListenerUtil.mutListener.listen(23820)) {
            ((WordPress) WordPress.getContext().getApplicationContext()).component().inject(this);
        }
    }

    @Override
    public PostModel replaceMediaFileWithUrlInPost(@Nullable PostModel post, String localMediaId, MediaFile mediaFile, @Nullable SiteModel site) {
        if (!ListenerUtil.mutListener.listen(23826)) {
            if (post != null) {
                boolean showAztecEditor = AppPrefs.isAztecEditorEnabled();
                boolean showGutenbergEditor = AppPrefs.isGutenbergEditorEnabled();
                if (!ListenerUtil.mutListener.listen(23825)) {
                    if (PostUtils.contentContainsWPStoryGutenbergBlocks(post.getContent())) {
                        if (!ListenerUtil.mutListener.listen(23824)) {
                            mSaveStoryGutenbergBlockUseCase.replaceLocalMediaIdsWithRemoteMediaIdsInPost(post, site, mediaFile);
                        }
                    } else if ((ListenerUtil.mutListener.listen(23821) ? (showGutenbergEditor || PostUtils.contentContainsGutenbergBlocks(post.getContent())) : (showGutenbergEditor && PostUtils.contentContainsGutenbergBlocks(post.getContent())))) {
                        String siteUrl = site != null ? site.getUrl() : "";
                        if (!ListenerUtil.mutListener.listen(23823)) {
                            post.setContent(PostUtils.replaceMediaFileWithUrlInGutenbergPost(post.getContent(), localMediaId, mediaFile, siteUrl));
                        }
                    } else if (showAztecEditor) {
                        if (!ListenerUtil.mutListener.listen(23822)) {
                            post.setContent(AztecEditorFragment.replaceMediaFileWithUrl(WordPress.getContext(), post.getContent(), localMediaId, mediaFile));
                        }
                    }
                }
            }
        }
        return post;
    }

    @Override
    public PostModel markMediaUploadFailedInPost(@Nullable PostModel post, String localMediaId, final MediaFile mediaFile) {
        if (!ListenerUtil.mutListener.listen(23829)) {
            if (post != null) {
                boolean showAztecEditor = AppPrefs.isAztecEditorEnabled();
                boolean showGutenbergEditor = AppPrefs.isGutenbergEditorEnabled();
                if (!ListenerUtil.mutListener.listen(23828)) {
                    if (showGutenbergEditor) {
                    } else if (showAztecEditor) {
                        if (!ListenerUtil.mutListener.listen(23827)) {
                            post.setContent(AztecEditorFragment.markMediaFailed(WordPress.getContext(), post.getContent(), localMediaId, mediaFile));
                        }
                    }
                }
            }
        }
        return post;
    }
}
