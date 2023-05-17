package org.wordpress.android.ui.reader.views;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import org.wordpress.android.R;
import org.wordpress.android.WordPress;
import org.wordpress.android.fluxc.model.ReaderSiteModel;
import org.wordpress.android.ui.reader.actions.ReaderActions;
import org.wordpress.android.ui.reader.actions.ReaderBlogActions;
import org.wordpress.android.ui.reader.tracker.ReaderTracker;
import org.wordpress.android.util.NetworkUtils;
import org.wordpress.android.util.StringUtils;
import org.wordpress.android.util.ToastUtils;
import org.wordpress.android.util.UrlUtils;
import org.wordpress.android.util.image.ImageManager;
import org.wordpress.android.util.image.ImageType;
import javax.inject.Inject;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * single feed search result
 */
public class ReaderSiteSearchResultView extends LinearLayout {

    public interface OnSiteFollowedListener {

        void onSiteFollowed(@NonNull ReaderSiteModel site);

        void onSiteUnFollowed(@NonNull ReaderSiteModel site);
    }

    private ReaderFollowButton mFollowButton;

    private ReaderSiteModel mSite;

    private OnSiteFollowedListener mFollowListener;

    @Inject
    ReaderTracker mReaderTracker;

    public ReaderSiteSearchResultView(Context context) {
        this(context, null);
    }

    public ReaderSiteSearchResultView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ReaderSiteSearchResultView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        if (!ListenerUtil.mutListener.listen(20188)) {
            ((WordPress) context.getApplicationContext()).component().inject(this);
        }
        if (!ListenerUtil.mutListener.listen(20189)) {
            initView(context);
        }
    }

    private void initView(Context context) {
        View view = inflate(context, R.layout.reader_site_search_result, this);
        LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        if (!ListenerUtil.mutListener.listen(20190)) {
            view.setLayoutParams(params);
        }
        if (!ListenerUtil.mutListener.listen(20191)) {
            mFollowButton = view.findViewById(R.id.follow_button);
        }
        if (!ListenerUtil.mutListener.listen(20193)) {
            mFollowButton.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {
                    if (!ListenerUtil.mutListener.listen(20192)) {
                        toggleFollowStatus();
                    }
                }
            });
        }
    }

    public void setSite(@NonNull ReaderSiteModel site, @NonNull OnSiteFollowedListener followListener) {
        if (!ListenerUtil.mutListener.listen(20194)) {
            mSite = site;
        }
        if (!ListenerUtil.mutListener.listen(20195)) {
            mFollowListener = followListener;
        }
        TextView txtTitle = findViewById(R.id.text_title);
        TextView txtUrl = findViewById(R.id.text_url);
        ImageView imgBlavatar = findViewById(R.id.image_blavatar);
        if (!ListenerUtil.mutListener.listen(20198)) {
            if (!TextUtils.isEmpty(site.getTitle())) {
                if (!ListenerUtil.mutListener.listen(20197)) {
                    txtTitle.setText(site.getTitle());
                }
            } else {
                if (!ListenerUtil.mutListener.listen(20196)) {
                    txtTitle.setText(R.string.untitled_in_parentheses);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(20199)) {
            txtUrl.setText(UrlUtils.getHost(site.getUrl()));
        }
        if (!ListenerUtil.mutListener.listen(20200)) {
            ImageManager.getInstance().load(imgBlavatar, ImageType.BLAVATAR, StringUtils.notNullStr(site.getIconUrl()));
        }
        if (!ListenerUtil.mutListener.listen(20201)) {
            mFollowButton.setIsFollowed(site.isFollowing());
        }
    }

    private void toggleFollowStatus() {
        if (!ListenerUtil.mutListener.listen(20202)) {
            if (!NetworkUtils.checkConnection(getContext())) {
                return;
            }
        }
        final boolean isAskingToFollow = !mSite.isFollowing();
        ReaderActions.ActionListener listener = new ReaderActions.ActionListener() {

            @Override
            public void onActionResult(boolean succeeded) {
                if (!ListenerUtil.mutListener.listen(20203)) {
                    if (getContext() == null) {
                        return;
                    }
                }
                if (!ListenerUtil.mutListener.listen(20204)) {
                    mFollowButton.setEnabled(true);
                }
                if (!ListenerUtil.mutListener.listen(20208)) {
                    if (!succeeded) {
                        int errResId = isAskingToFollow ? R.string.reader_toast_err_follow_blog : R.string.reader_toast_err_unfollow_blog;
                        if (!ListenerUtil.mutListener.listen(20205)) {
                            ToastUtils.showToast(getContext(), errResId);
                        }
                        if (!ListenerUtil.mutListener.listen(20206)) {
                            mFollowButton.setIsFollowed(!isAskingToFollow);
                        }
                        if (!ListenerUtil.mutListener.listen(20207)) {
                            mSite.setFollowing(!isAskingToFollow);
                        }
                    }
                }
            }
        };
        if (!ListenerUtil.mutListener.listen(20209)) {
            // disable follow button until API call returns
            mFollowButton.setEnabled(false);
        }
        boolean result = ReaderBlogActions.followFeedById(mSite.getSiteId(), mSite.getFeedId(), isAskingToFollow, listener, ReaderTracker.SOURCE_SEARCH, mReaderTracker);
        if (!ListenerUtil.mutListener.listen(20215)) {
            if (result) {
                if (!ListenerUtil.mutListener.listen(20210)) {
                    mFollowButton.setIsFollowedAnimated(isAskingToFollow);
                }
                if (!ListenerUtil.mutListener.listen(20211)) {
                    mSite.setFollowing(isAskingToFollow);
                }
                if (!ListenerUtil.mutListener.listen(20214)) {
                    if (isAskingToFollow) {
                        if (!ListenerUtil.mutListener.listen(20213)) {
                            mFollowListener.onSiteFollowed(mSite);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(20212)) {
                            mFollowListener.onSiteUnFollowed(mSite);
                        }
                    }
                }
            }
        }
    }
}
