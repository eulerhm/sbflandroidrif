package org.wordpress.android.ui.reader;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.ProgressBar;
import androidx.annotation.NonNull;
import org.wordpress.android.R;
import org.wordpress.android.WordPress;
import org.wordpress.android.ui.LocaleAwareActivity;
import org.wordpress.android.util.helpers.WebChromeClientWithVideoPoster;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Full screen landscape video player for the reader
 */
public class ReaderVideoViewerActivity extends LocaleAwareActivity {

    private String mVideoUrl;

    private WebView mWebView;

    private ProgressBar mProgress;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(22715)) {
            super.onCreate(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(22716)) {
            setContentView(R.layout.reader_activity_video_player);
        }
        if (!ListenerUtil.mutListener.listen(22717)) {
            mWebView = (WebView) findViewById(R.id.web_view);
        }
        if (!ListenerUtil.mutListener.listen(22718)) {
            mProgress = (ProgressBar) findViewById(R.id.progress);
        }
        if (!ListenerUtil.mutListener.listen(22719)) {
            mWebView.setBackgroundColor(Color.TRANSPARENT);
        }
        if (!ListenerUtil.mutListener.listen(22720)) {
            mWebView.getSettings().setJavaScriptEnabled(true);
        }
        if (!ListenerUtil.mutListener.listen(22721)) {
            mWebView.getSettings().setUserAgentString(WordPress.getUserAgent());
        }
        if (!ListenerUtil.mutListener.listen(22732)) {
            mWebView.setWebChromeClient(new WebChromeClientWithVideoPoster(mWebView, R.drawable.media_movieclip) {

                public void onProgressChanged(WebView view, int progress) {
                    if (!ListenerUtil.mutListener.listen(22731)) {
                        if ((ListenerUtil.mutListener.listen(22726) ? (progress >= 100) : (ListenerUtil.mutListener.listen(22725) ? (progress <= 100) : (ListenerUtil.mutListener.listen(22724) ? (progress > 100) : (ListenerUtil.mutListener.listen(22723) ? (progress < 100) : (ListenerUtil.mutListener.listen(22722) ? (progress != 100) : (progress == 100))))))) {
                            if (!ListenerUtil.mutListener.listen(22730)) {
                                mProgress.setVisibility(View.GONE);
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(22727)) {
                                mProgress.setProgress(progress);
                            }
                            if (!ListenerUtil.mutListener.listen(22729)) {
                                if (mProgress.getVisibility() != View.VISIBLE) {
                                    if (!ListenerUtil.mutListener.listen(22728)) {
                                        mProgress.setVisibility(View.VISIBLE);
                                    }
                                }
                            }
                        }
                    }
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(22737)) {
            if (savedInstanceState == null) {
                if (!ListenerUtil.mutListener.listen(22735)) {
                    mVideoUrl = getIntent().getStringExtra(ReaderConstants.ARG_VIDEO_URL);
                }
                if (!ListenerUtil.mutListener.listen(22736)) {
                    mWebView.loadUrl(mVideoUrl);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(22733)) {
                    mVideoUrl = savedInstanceState.getString(ReaderConstants.ARG_VIDEO_URL);
                }
                if (!ListenerUtil.mutListener.listen(22734)) {
                    mWebView.restoreState(savedInstanceState);
                }
            }
        }
    }

    @Override
    protected void onDestroy() {
        if (!ListenerUtil.mutListener.listen(22738)) {
            // even though the activity has been destroyed
            mWebView.onPause();
        }
        if (!ListenerUtil.mutListener.listen(22739)) {
            super.onDestroy();
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        if (!ListenerUtil.mutListener.listen(22740)) {
            outState.putString(ReaderConstants.ARG_VIDEO_URL, mVideoUrl);
        }
        if (!ListenerUtil.mutListener.listen(22741)) {
            mWebView.saveState(outState);
        }
        if (!ListenerUtil.mutListener.listen(22742)) {
            super.onSaveInstanceState(outState);
        }
    }
}
