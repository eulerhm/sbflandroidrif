package org.wordpress.android.ui.publicize;

import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import androidx.annotation.NonNull;
import org.greenrobot.eventbus.EventBus;
import org.wordpress.android.R;
import org.wordpress.android.WordPress;
import org.wordpress.android.datasets.PublicizeTable;
import org.wordpress.android.fluxc.model.SiteModel;
import org.wordpress.android.fluxc.store.AccountStore;
import org.wordpress.android.models.PublicizeConnection;
import org.wordpress.android.models.PublicizeService;
import org.wordpress.android.ui.ScrollableViewInitializedListener;
import org.wordpress.android.ui.WPWebViewActivity;
import org.wordpress.android.ui.publicize.PublicizeConstants.ConnectAction;
import org.wordpress.android.util.WebViewUtils;
import org.wordpress.android.util.helpers.WebChromeClientWithVideoPoster;
import javax.inject.Inject;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class PublicizeWebViewFragment extends PublicizeBaseFragment {

    private SiteModel mSite;

    private String mServiceId;

    private int mConnectionId;

    private WebView mWebView;

    private ProgressBar mProgress;

    private View mNestedScrollView;

    @Inject
    AccountStore mAccountStore;

    /*
     * returns a new webView fragment to connect to a publicize service - if passed connection
     * is non-null then we're reconnecting a broken connection, otherwise we're creating a
     * new connection to the service
     */
    public static PublicizeWebViewFragment newInstance(@NonNull SiteModel site, @NonNull PublicizeService service, PublicizeConnection connection) {
        Bundle args = new Bundle();
        if (!ListenerUtil.mutListener.listen(17763)) {
            args.putSerializable(WordPress.SITE, site);
        }
        if (!ListenerUtil.mutListener.listen(17764)) {
            args.putString(PublicizeConstants.ARG_SERVICE_ID, service.getId());
        }
        if (!ListenerUtil.mutListener.listen(17766)) {
            if (connection != null) {
                if (!ListenerUtil.mutListener.listen(17765)) {
                    args.putInt(PublicizeConstants.ARG_CONNECTION_ID, connection.connectionId);
                }
            }
        }
        PublicizeWebViewFragment fragment = new PublicizeWebViewFragment();
        if (!ListenerUtil.mutListener.listen(17767)) {
            fragment.setArguments(args);
        }
        return fragment;
    }

    @Override
    public void setArguments(Bundle args) {
        if (!ListenerUtil.mutListener.listen(17768)) {
            super.setArguments(args);
        }
        if (!ListenerUtil.mutListener.listen(17772)) {
            if (args != null) {
                if (!ListenerUtil.mutListener.listen(17769)) {
                    mSite = (SiteModel) args.getSerializable(WordPress.SITE);
                }
                if (!ListenerUtil.mutListener.listen(17770)) {
                    mServiceId = args.getString(PublicizeConstants.ARG_SERVICE_ID);
                }
                if (!ListenerUtil.mutListener.listen(17771)) {
                    mConnectionId = args.getInt(PublicizeConstants.ARG_CONNECTION_ID);
                }
            }
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(17773)) {
            super.onCreate(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(17774)) {
            ((WordPress) getActivity().getApplication()).component().inject(this);
        }
        if (!ListenerUtil.mutListener.listen(17778)) {
            if (savedInstanceState != null) {
                if (!ListenerUtil.mutListener.listen(17775)) {
                    mSite = (SiteModel) savedInstanceState.getSerializable(WordPress.SITE);
                }
                if (!ListenerUtil.mutListener.listen(17776)) {
                    mServiceId = savedInstanceState.getString(PublicizeConstants.ARG_SERVICE_ID);
                }
                if (!ListenerUtil.mutListener.listen(17777)) {
                    mConnectionId = savedInstanceState.getInt(PublicizeConstants.ARG_CONNECTION_ID);
                }
            }
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        if (!ListenerUtil.mutListener.listen(17779)) {
            super.onSaveInstanceState(outState);
        }
        if (!ListenerUtil.mutListener.listen(17780)) {
            outState.putSerializable(WordPress.SITE, mSite);
        }
        if (!ListenerUtil.mutListener.listen(17781)) {
            outState.putInt(PublicizeConstants.ARG_CONNECTION_ID, mConnectionId);
        }
        if (!ListenerUtil.mutListener.listen(17782)) {
            outState.putString(PublicizeConstants.ARG_SERVICE_ID, mServiceId);
        }
        if (!ListenerUtil.mutListener.listen(17783)) {
            mWebView.saveState(outState);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.publicize_webview_fragment, container, false);
        if (!ListenerUtil.mutListener.listen(17784)) {
            mProgress = rootView.findViewById(R.id.progress);
        }
        if (!ListenerUtil.mutListener.listen(17785)) {
            mWebView = rootView.findViewById(R.id.webView);
        }
        if (!ListenerUtil.mutListener.listen(17786)) {
            mNestedScrollView = rootView.findViewById(R.id.publicize_webview_nested_scroll_view);
        }
        if (!ListenerUtil.mutListener.listen(17787)) {
            mWebView.setWebViewClient(new PublicizeWebViewClient());
        }
        if (!ListenerUtil.mutListener.listen(17788)) {
            mWebView.setWebChromeClient(new PublicizeWebChromeClient());
        }
        if (!ListenerUtil.mutListener.listen(17789)) {
            mWebView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        }
        if (!ListenerUtil.mutListener.listen(17790)) {
            mWebView.getSettings().setJavaScriptEnabled(true);
        }
        if (!ListenerUtil.mutListener.listen(17791)) {
            mWebView.getSettings().setDomStorageEnabled(true);
        }
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(17792)) {
            super.onActivityCreated(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(17796)) {
            if (savedInstanceState == null) {
                if (!ListenerUtil.mutListener.listen(17794)) {
                    mProgress.setVisibility(View.VISIBLE);
                }
                if (!ListenerUtil.mutListener.listen(17795)) {
                    loadConnectUrl();
                }
            } else {
                if (!ListenerUtil.mutListener.listen(17793)) {
                    mWebView.restoreState(savedInstanceState);
                }
            }
        }
    }

    @Override
    public void onResume() {
        if (!ListenerUtil.mutListener.listen(17797)) {
            super.onResume();
        }
        if (!ListenerUtil.mutListener.listen(17798)) {
            setNavigationIcon(R.drawable.ic_close_white_24dp);
        }
        if (!ListenerUtil.mutListener.listen(17800)) {
            if (getActivity() instanceof ScrollableViewInitializedListener) {
                if (!ListenerUtil.mutListener.listen(17799)) {
                    ((ScrollableViewInitializedListener) getActivity()).onScrollableViewInitialized(mNestedScrollView.getId());
                }
            }
        }
    }

    /*
     * display the current connect URL for this service - this will ask the user to
     * authorize the connection via the external service
     */
    private void loadConnectUrl() {
        if (!ListenerUtil.mutListener.listen(17801)) {
            if (!isAdded()) {
                return;
            }
        }
        // connect url depends on whether we're connecting or reconnecting
        String connectUrl;
        if ((ListenerUtil.mutListener.listen(17806) ? (mConnectionId >= 0) : (ListenerUtil.mutListener.listen(17805) ? (mConnectionId <= 0) : (ListenerUtil.mutListener.listen(17804) ? (mConnectionId > 0) : (ListenerUtil.mutListener.listen(17803) ? (mConnectionId < 0) : (ListenerUtil.mutListener.listen(17802) ? (mConnectionId == 0) : (mConnectionId != 0))))))) {
            connectUrl = PublicizeTable.getRefreshUrlForConnection(mConnectionId);
        } else {
            connectUrl = PublicizeTable.getConnectUrlForService(mServiceId);
        }
        // request must be authenticated with wp.com credentials
        String postData = WPWebViewActivity.getAuthenticationPostData(WPWebViewActivity.WPCOM_LOGIN_URL, connectUrl, mAccountStore.getAccount().getUserName(), "", mAccountStore.getAccessToken());
        if (!ListenerUtil.mutListener.listen(17807)) {
            mWebView.postUrl(WPWebViewActivity.WPCOM_LOGIN_URL, postData.getBytes());
        }
    }

    private class PublicizeWebViewClient extends WebViewClient {

        PublicizeWebViewClient() {
            super();
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            if (!ListenerUtil.mutListener.listen(17808)) {
                super.onPageFinished(view, url);
            }
            if (!ListenerUtil.mutListener.listen(17817)) {
                // does this url denotes that we made it past the auth stage?
                if ((ListenerUtil.mutListener.listen(17809) ? (isAdded() || url != null) : (isAdded() && url != null))) {
                    Uri uri = Uri.parse(url);
                    if (!ListenerUtil.mutListener.listen(17816)) {
                        if ((ListenerUtil.mutListener.listen(17811) ? ((ListenerUtil.mutListener.listen(17810) ? (uri.getHost().equals("public-api.wordpress.com") || uri.getPath().equals("/connect/")) : (uri.getHost().equals("public-api.wordpress.com") && uri.getPath().equals("/connect/"))) || uri.getQueryParameter("action").equals("verify")) : ((ListenerUtil.mutListener.listen(17810) ? (uri.getHost().equals("public-api.wordpress.com") || uri.getPath().equals("/connect/")) : (uri.getHost().equals("public-api.wordpress.com") && uri.getPath().equals("/connect/"))) && uri.getQueryParameter("action").equals("verify")))) {
                            // "denied" param will appear on failure or cancellation
                            String denied = uri.getQueryParameter("denied");
                            if (!ListenerUtil.mutListener.listen(17813)) {
                                if (!TextUtils.isEmpty(denied)) {
                                    if (!ListenerUtil.mutListener.listen(17812)) {
                                        EventBus.getDefault().post(new PublicizeEvents.ActionCompleted(false, ConnectAction.CONNECT, mServiceId));
                                    }
                                    return;
                                }
                            }
                            long currentUserId = mAccountStore.getAccount().getUserId();
                            if (!ListenerUtil.mutListener.listen(17814)) {
                                // call the endpoint to make the actual connection
                                PublicizeActions.connect(mSite.getSiteId(), mServiceId, currentUserId);
                            }
                            if (!ListenerUtil.mutListener.listen(17815)) {
                                WebViewUtils.clearCookiesAsync();
                            }
                        }
                    }
                }
            }
        }
    }

    private class PublicizeWebChromeClient extends WebChromeClientWithVideoPoster {

        PublicizeWebChromeClient() {
            super(mWebView, R.drawable.media_movieclip);
        }

        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            if (!ListenerUtil.mutListener.listen(17818)) {
                super.onProgressChanged(view, newProgress);
            }
            if (!ListenerUtil.mutListener.listen(17826)) {
                if ((ListenerUtil.mutListener.listen(17824) ? ((ListenerUtil.mutListener.listen(17823) ? (newProgress >= 100) : (ListenerUtil.mutListener.listen(17822) ? (newProgress <= 100) : (ListenerUtil.mutListener.listen(17821) ? (newProgress > 100) : (ListenerUtil.mutListener.listen(17820) ? (newProgress < 100) : (ListenerUtil.mutListener.listen(17819) ? (newProgress != 100) : (newProgress == 100)))))) || isAdded()) : ((ListenerUtil.mutListener.listen(17823) ? (newProgress >= 100) : (ListenerUtil.mutListener.listen(17822) ? (newProgress <= 100) : (ListenerUtil.mutListener.listen(17821) ? (newProgress > 100) : (ListenerUtil.mutListener.listen(17820) ? (newProgress < 100) : (ListenerUtil.mutListener.listen(17819) ? (newProgress != 100) : (newProgress == 100)))))) && isAdded()))) {
                    if (!ListenerUtil.mutListener.listen(17825)) {
                        mProgress.setVisibility(View.GONE);
                    }
                }
            }
        }

        @Override
        public void onReceivedTitle(WebView view, String title) {
            if (!ListenerUtil.mutListener.listen(17827)) {
                super.onReceivedTitle(view, title);
            }
            if (!ListenerUtil.mutListener.listen(17830)) {
                if ((ListenerUtil.mutListener.listen(17828) ? (title != null || !title.startsWith("http")) : (title != null && !title.startsWith("http")))) {
                    if (!ListenerUtil.mutListener.listen(17829)) {
                        setTitle(title);
                    }
                }
            }
        }
    }
}
