package org.wordpress.android.ui.reader;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;
import org.wordpress.android.datasets.ReaderPostTable;
import org.wordpress.android.models.ReaderPost;
import org.wordpress.android.ui.reader.views.ReaderWebView;
import org.wordpress.android.util.AppLog;
import org.wordpress.android.util.AppLog.T;
import org.wordpress.android.util.NetworkUtils;
import org.wordpress.android.util.UrlUtils;
import javax.inject.Inject;
import dagger.android.support.DaggerFragment;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Fragment responsible for caching post content into WebView.
 * Caching happens on UI thread, so any configuration change will restart it from scratch.
 */
public class ReaderPostWebViewCachingFragment extends DaggerFragment {

    private static final String ARG_BLOG_ID = "blog_id";

    private static final String ARG_POST_ID = "post_id";

    private long mBlogId;

    private long mPostId;

    @Inject
    ReaderCssProvider mReaderCssProvider;

    public static ReaderPostWebViewCachingFragment newInstance(long blogId, long postId) {
        ReaderPostWebViewCachingFragment fragment = new ReaderPostWebViewCachingFragment();
        Bundle args = new Bundle();
        if (!ListenerUtil.mutListener.listen(22455)) {
            args.putLong(ARG_BLOG_ID, blogId);
        }
        if (!ListenerUtil.mutListener.listen(22456)) {
            args.putLong(ARG_POST_ID, postId);
        }
        if (!ListenerUtil.mutListener.listen(22457)) {
            fragment.setArguments(args);
        }
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(22458)) {
            super.onCreate(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(22459)) {
            mBlogId = getArguments().getLong(ARG_BLOG_ID);
        }
        if (!ListenerUtil.mutListener.listen(22460)) {
            mPostId = getArguments().getLong(ARG_POST_ID);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return new ReaderWebView(getActivity());
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(22461)) {
            super.onViewCreated(view, savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(22470)) {
            // check network again to detect disconnects during loading + configuration change
            if (NetworkUtils.isNetworkAvailable(view.getContext())) {
                ReaderPost post = ReaderPostTable.getBlogPost(mBlogId, mPostId, false);
                if (!ListenerUtil.mutListener.listen(22469)) {
                    if (post != null) {
                        if (!ListenerUtil.mutListener.listen(22464)) {
                            ((ReaderWebView) view).setIsPrivatePost(post.isPrivate);
                        }
                        if (!ListenerUtil.mutListener.listen(22465)) {
                            ((ReaderWebView) view).setBlogSchemeIsHttps(UrlUtils.isHttps(post.getBlogUrl()));
                        }
                        if (!ListenerUtil.mutListener.listen(22467)) {
                            ((ReaderWebView) view).setPageFinishedListener(new ReaderWebView.ReaderWebViewPageFinishedListener() {

                                @Override
                                public void onPageFinished(WebView view, String url) {
                                    if (!ListenerUtil.mutListener.listen(22466)) {
                                        selfRemoveFragment();
                                    }
                                }
                            });
                        }
                        ReaderPostRenderer rendered = new ReaderPostRenderer((ReaderWebView) view, post, mReaderCssProvider);
                        if (!ListenerUtil.mutListener.listen(22468)) {
                            // rendering will cache post content using native WebView implementation.
                            rendered.beginRender();
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(22463)) {
                            // abort mission if post is not available
                            selfRemoveFragment();
                        }
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(22462)) {
                    // abort mission if no network is available
                    selfRemoveFragment();
                }
            }
        }
    }

    private void selfRemoveFragment() {
        if (!ListenerUtil.mutListener.listen(22474)) {
            if (isAdded()) {
                FragmentManager fm = getFragmentManager();
                if (!ListenerUtil.mutListener.listen(22473)) {
                    if (fm != null) {
                        if (!ListenerUtil.mutListener.listen(22472)) {
                            fm.beginTransaction().remove(ReaderPostWebViewCachingFragment.this).commitAllowingStateLoss();
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(22471)) {
                            AppLog.w(T.READER, "Fragment manager is null.");
                        }
                    }
                }
            }
        }
    }
}
