package org.wordpress.android.ui.posts;

import android.os.Bundle;
import org.wordpress.android.analytics.AnalyticsTracker.Stat;
import org.wordpress.android.fluxc.model.PostImmutableModel;
import org.wordpress.android.fluxc.model.SiteModel;
import org.wordpress.android.ui.posts.PostUtils.EntryPoint;
import org.wordpress.android.ui.prefs.AppPrefs;
import org.wordpress.android.util.AppLog;
import org.wordpress.android.util.AppLog.T;
import org.wordpress.android.util.SiteUtils;
import org.wordpress.android.util.analytics.AnalyticsTrackerWrapper;
import org.wordpress.android.util.analytics.AnalyticsUtils;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class PostEditorAnalyticsSession implements Serializable {

    private static final String KEY_BLOG_TYPE = "blog_type";

    private static final String KEY_CONTENT_TYPE = "content_type";

    private static final String KEY_EDITOR = "editor";

    private static final String KEY_HAS_UNSUPPORTED_BLOCKS = "has_unsupported_blocks";

    private static final String KEY_UNSUPPORTED_BLOCKS = "unsupported_blocks";

    private static final String KEY_GALLERY_WITH_IMAGE_BLOCKS = "unstable_gallery_with_image_blocks";

    private static final String KEY_POST_TYPE = "post_type";

    private static final String KEY_OUTCOME = "outcome";

    private static final String KEY_SESSION_ID = "session_id";

    private static final String KEY_STARTUP_TIME = "startup_time_ms";

    private static final String KEY_TEMPLATE = "template";

    private static final String KEY_FULL_SITE_EDITING = "full_site_editing";

    private static final String KEY_ENDPOINT = "endpoint";

    private static final String KEY_ENTRY_POINT = "entry_point";

    private transient AnalyticsTrackerWrapper mAnalyticsTrackerWrapper;

    private String mSessionId = UUID.randomUUID().toString();

    private SiteModel mSiteModel;

    private String mPostType;

    private String mContentType;

    private boolean mStarted = false;

    private Editor mCurrentEditor;

    private boolean mHasUnsupportedBlocks = false;

    private Outcome mOutcome = null;

    private String mTemplate;

    private boolean mHWAccOff = false;

    private long mStartTime = System.currentTimeMillis();

    public enum Editor {

        GUTENBERG, CLASSIC, HTML, WP_STORIES_CREATOR
    }

    public enum Outcome {

        CANCEL,
        // not used in WPAndroid, but kept for parity with iOS
        DISCARD,
        // see https://github.com/wordpress-mobile/gutenberg-mobile/issues/556#issuecomment-462678807
        SAVE,
        PUBLISH
    }

    public static PostEditorAnalyticsSession fromBundle(Bundle bundle, String key, AnalyticsTrackerWrapper analyticsTrackerWrapper) {
        PostEditorAnalyticsSession postEditorAnalyticsSession = (PostEditorAnalyticsSession) bundle.getSerializable(key);
        if (!ListenerUtil.mutListener.listen(12740)) {
            postEditorAnalyticsSession.mAnalyticsTrackerWrapper = analyticsTrackerWrapper;
        }
        return postEditorAnalyticsSession;
    }

    PostEditorAnalyticsSession(Editor editor, PostImmutableModel post, SiteModel site, boolean isNewPost, AnalyticsTrackerWrapper analyticsTrackerWrapper) {
        if (!ListenerUtil.mutListener.listen(12741)) {
            mAnalyticsTrackerWrapper = analyticsTrackerWrapper;
        }
        if (!ListenerUtil.mutListener.listen(12742)) {
            // fill in which the current Editor is
            mCurrentEditor = editor;
        }
        if (!ListenerUtil.mutListener.listen(12745)) {
            // fill in mPostType
            if (post.isPage()) {
                if (!ListenerUtil.mutListener.listen(12744)) {
                    mPostType = "page";
                }
            } else {
                if (!ListenerUtil.mutListener.listen(12743)) {
                    mPostType = "post";
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(12746)) {
            mSiteModel = site;
        }
        // fill in mContentType
        String postContent = post.getContent();
        if (!ListenerUtil.mutListener.listen(12750)) {
            if (isNewPost) {
                if (!ListenerUtil.mutListener.listen(12749)) {
                    mContentType = "new";
                }
            } else if (PostUtils.contentContainsGutenbergBlocks(postContent)) {
                if (!ListenerUtil.mutListener.listen(12748)) {
                    mContentType = SiteUtils.GB_EDITOR_NAME;
                }
            } else {
                if (!ListenerUtil.mutListener.listen(12747)) {
                    mContentType = "classic";
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(12751)) {
            mHWAccOff = AppPrefs.isPostWithHWAccelerationOff(site.getId(), post.getId());
        }
    }

    public static PostEditorAnalyticsSession getNewPostEditorAnalyticsSession(Editor editor, PostImmutableModel post, SiteModel site, boolean isNewPost, AnalyticsTrackerWrapper analyticsTrackerWrapper) {
        return new PostEditorAnalyticsSession(editor, post, site, isNewPost, analyticsTrackerWrapper);
    }

    public static PostEditorAnalyticsSession getNewPostEditorAnalyticsSession(Editor editor, PostImmutableModel post, SiteModel site, boolean isNewPost) {
        return getNewPostEditorAnalyticsSession(editor, post, site, isNewPost, new AnalyticsTrackerWrapper());
    }

    public void start(ArrayList<Object> unsupportedBlocksList, Boolean galleryWithImageBlocks, final EntryPoint entryPoint) {
        if (!ListenerUtil.mutListener.listen(12772)) {
            if (!mStarted) {
                if (!ListenerUtil.mutListener.listen(12759)) {
                    mHasUnsupportedBlocks = (ListenerUtil.mutListener.listen(12758) ? (unsupportedBlocksList != null || (ListenerUtil.mutListener.listen(12757) ? (unsupportedBlocksList.size() >= 0) : (ListenerUtil.mutListener.listen(12756) ? (unsupportedBlocksList.size() <= 0) : (ListenerUtil.mutListener.listen(12755) ? (unsupportedBlocksList.size() < 0) : (ListenerUtil.mutListener.listen(12754) ? (unsupportedBlocksList.size() != 0) : (ListenerUtil.mutListener.listen(12753) ? (unsupportedBlocksList.size() == 0) : (unsupportedBlocksList.size() > 0))))))) : (unsupportedBlocksList != null && (ListenerUtil.mutListener.listen(12757) ? (unsupportedBlocksList.size() >= 0) : (ListenerUtil.mutListener.listen(12756) ? (unsupportedBlocksList.size() <= 0) : (ListenerUtil.mutListener.listen(12755) ? (unsupportedBlocksList.size() < 0) : (ListenerUtil.mutListener.listen(12754) ? (unsupportedBlocksList.size() != 0) : (ListenerUtil.mutListener.listen(12753) ? (unsupportedBlocksList.size() == 0) : (unsupportedBlocksList.size() > 0))))))));
                }
                Map<String, Object> properties = getCommonProperties();
                if (!ListenerUtil.mutListener.listen(12760)) {
                    properties.put(KEY_UNSUPPORTED_BLOCKS, unsupportedBlocksList != null ? unsupportedBlocksList : new ArrayList<>());
                }
                if (!ListenerUtil.mutListener.listen(12762)) {
                    if (galleryWithImageBlocks != null) {
                        if (!ListenerUtil.mutListener.listen(12761)) {
                            properties.put(KEY_GALLERY_WITH_IMAGE_BLOCKS, galleryWithImageBlocks);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(12767)) {
                    // the session.
                    properties.put(KEY_STARTUP_TIME, (ListenerUtil.mutListener.listen(12766) ? (System.currentTimeMillis() % mStartTime) : (ListenerUtil.mutListener.listen(12765) ? (System.currentTimeMillis() / mStartTime) : (ListenerUtil.mutListener.listen(12764) ? (System.currentTimeMillis() * mStartTime) : (ListenerUtil.mutListener.listen(12763) ? (System.currentTimeMillis() + mStartTime) : (System.currentTimeMillis() - mStartTime))))));
                }
                if (!ListenerUtil.mutListener.listen(12769)) {
                    if (entryPoint != null) {
                        if (!ListenerUtil.mutListener.listen(12768)) {
                            properties.put(KEY_ENTRY_POINT, entryPoint.getTrackingValue());
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(12770)) {
                    AnalyticsUtils.trackWithSiteDetails(mAnalyticsTrackerWrapper, Stat.EDITOR_SESSION_START, mSiteModel, properties);
                }
                if (!ListenerUtil.mutListener.listen(12771)) {
                    mStarted = true;
                }
            } else {
                if (!ListenerUtil.mutListener.listen(12752)) {
                    AppLog.w(T.EDITOR, "An editor session cannot be attempted to be started more than once, " + "unless it's due to rotation or Editor switch");
                }
            }
        }
    }

    public void resetStartTime() {
        if (!ListenerUtil.mutListener.listen(12775)) {
            if (!mStarted) {
                if (!ListenerUtil.mutListener.listen(12774)) {
                    mStartTime = System.currentTimeMillis();
                }
            } else {
                if (!ListenerUtil.mutListener.listen(12773)) {
                    AppLog.w(T.EDITOR, "An editor session start time cannot be reset once it's started");
                }
            }
        }
    }

    public void switchEditor(Editor editor) {
        if (!ListenerUtil.mutListener.listen(12776)) {
            mCurrentEditor = editor;
        }
        Map<String, Object> properties = getCommonProperties();
        if (!ListenerUtil.mutListener.listen(12777)) {
            AnalyticsUtils.trackWithSiteDetails(mAnalyticsTrackerWrapper, Stat.EDITOR_SESSION_SWITCH_EDITOR, mSiteModel, properties);
        }
    }

    public void setOutcome(Outcome newOutcome) {
        if (!ListenerUtil.mutListener.listen(12778)) {
            // Session ending will only properly end the session with the outcome already being set.
            mOutcome = newOutcome;
        }
    }

    public void applyTemplate(String template) {
        if (!ListenerUtil.mutListener.listen(12779)) {
            mTemplate = template;
        }
        final Map<String, Object> properties = getCommonProperties();
        if (!ListenerUtil.mutListener.listen(12780)) {
            properties.put(KEY_TEMPLATE, template);
        }
        if (!ListenerUtil.mutListener.listen(12781)) {
            AnalyticsUtils.trackWithSiteDetails(mAnalyticsTrackerWrapper, Stat.EDITOR_SESSION_TEMPLATE_APPLY, mSiteModel, properties);
        }
    }

    public void editorSettingsFetched(Boolean fullSiteEditing, String endpoint) {
        final Map<String, Object> properties = getCommonProperties();
        if (!ListenerUtil.mutListener.listen(12782)) {
            properties.put(KEY_FULL_SITE_EDITING, fullSiteEditing);
        }
        if (!ListenerUtil.mutListener.listen(12783)) {
            properties.put(KEY_ENDPOINT, endpoint);
        }
        if (!ListenerUtil.mutListener.listen(12784)) {
            AnalyticsUtils.trackWithSiteDetails(mAnalyticsTrackerWrapper, Stat.EDITOR_SETTINGS_FETCHED, mSiteModel, properties);
        }
    }

    public void end() {
        if (!ListenerUtil.mutListener.listen(12790)) {
            // don't try to send an "end" event if the session wasn't started in the first place
            if (mStarted) {
                if (!ListenerUtil.mutListener.listen(12787)) {
                    if (mOutcome == null) {
                        if (!ListenerUtil.mutListener.listen(12786)) {
                            // if outcome is still unknown, chances are Activity was killed / user cancelled so, set to CANCEL.
                            mOutcome = Outcome.CANCEL;
                        }
                    }
                }
                Map<String, Object> properties = getCommonProperties();
                if (!ListenerUtil.mutListener.listen(12788)) {
                    properties.put(KEY_OUTCOME, mOutcome.toString().toLowerCase(Locale.ROOT));
                }
                if (!ListenerUtil.mutListener.listen(12789)) {
                    AnalyticsUtils.trackWithSiteDetails(mAnalyticsTrackerWrapper, Stat.EDITOR_SESSION_END, mSiteModel, properties);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(12785)) {
                    AppLog.e(T.EDITOR, "A non-started editor session cannot be attempted to be ended");
                }
            }
        }
    }

    private String getBlockType() {
        if (!ListenerUtil.mutListener.listen(12791)) {
            if (mSiteModel.isWPCom()) {
                return "wpcom";
            } else if (mSiteModel.isJetpackConnected()) {
                return "jetpack";
            }
        }
        return "core";
    }

    private Map<String, Object> getCommonProperties() {
        Map<String, Object> properties = new HashMap<>();
        if (!ListenerUtil.mutListener.listen(12792)) {
            properties.put(KEY_EDITOR, mCurrentEditor.toString().toLowerCase(Locale.ROOT));
        }
        if (!ListenerUtil.mutListener.listen(12793)) {
            properties.put(KEY_CONTENT_TYPE, mContentType);
        }
        if (!ListenerUtil.mutListener.listen(12794)) {
            properties.put(KEY_POST_TYPE, mPostType);
        }
        if (!ListenerUtil.mutListener.listen(12795)) {
            properties.put(KEY_BLOG_TYPE, getBlockType());
        }
        if (!ListenerUtil.mutListener.listen(12796)) {
            properties.put(KEY_SESSION_ID, mSessionId);
        }
        if (!ListenerUtil.mutListener.listen(12797)) {
            properties.put(KEY_HAS_UNSUPPORTED_BLOCKS, mHasUnsupportedBlocks ? "1" : "0");
        }
        if (!ListenerUtil.mutListener.listen(12798)) {
            properties.put(AnalyticsUtils.EDITOR_HAS_HW_ACCELERATION_DISABLED_KEY, mHWAccOff ? "1" : "0");
        }
        if (!ListenerUtil.mutListener.listen(12800)) {
            if (mTemplate != null) {
                if (!ListenerUtil.mutListener.listen(12799)) {
                    properties.put(KEY_TEMPLATE, mTemplate);
                }
            }
        }
        return properties;
    }
}
