package org.wordpress.android.ui;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.CookieManager;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.widget.TooltipCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import com.google.android.material.elevation.ElevationOverlayProvider;
import com.google.android.material.snackbar.Snackbar;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.wordpress.android.R;
import org.wordpress.android.WordPress;
import org.wordpress.android.analytics.AnalyticsTracker.Stat;
import org.wordpress.android.fluxc.Dispatcher;
import org.wordpress.android.fluxc.generated.SiteActionBuilder;
import org.wordpress.android.fluxc.model.PostImmutableModel;
import org.wordpress.android.fluxc.model.SiteModel;
import org.wordpress.android.fluxc.network.rest.wpcom.site.PrivateAtomicCookie;
import org.wordpress.android.fluxc.store.AccountStore;
import org.wordpress.android.fluxc.store.SiteStore;
import org.wordpress.android.fluxc.store.SiteStore.FetchPrivateAtomicCookiePayload;
import org.wordpress.android.fluxc.store.SiteStore.OnPrivateAtomicCookieFetched;
import org.wordpress.android.ui.PrivateAtCookieRefreshProgressDialog.PrivateAtCookieProgressDialogOnDismissListener;
import org.wordpress.android.ui.WPWebChromeClientWithFileChooser.OnShowFileChooserListener;
import org.wordpress.android.ui.reader.ReaderActivityLauncher;
import org.wordpress.android.ui.utils.UiHelpers;
import org.wordpress.android.util.AniUtils;
import org.wordpress.android.util.AppLog;
import org.wordpress.android.util.DisplayUtilsWrapper;
import org.wordpress.android.util.ErrorManagedWebViewClient.ErrorManagedWebViewClientListener;
import org.wordpress.android.util.StringUtils;
import org.wordpress.android.util.ToastUtils;
import org.wordpress.android.util.URLFilteredWebViewClient;
import org.wordpress.android.util.UrlUtils;
import org.wordpress.android.util.WPUrlUtils;
import org.wordpress.android.util.WPWebViewClient;
import org.wordpress.android.viewmodel.wpwebview.WPWebViewViewModel;
import org.wordpress.android.viewmodel.wpwebview.WPWebViewViewModel.NavBarUiState;
import org.wordpress.android.viewmodel.wpwebview.WPWebViewViewModel.PreviewModeSelectorStatus;
import org.wordpress.android.viewmodel.wpwebview.WPWebViewViewModel.WebPreviewUiState;
import org.wordpress.android.viewmodel.wpwebview.WPWebViewViewModel.WebPreviewUiState.WebPreviewFullscreenUiState;
import org.wordpress.android.widgets.WPSnackbar;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import kotlin.Unit;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Activity for opening external WordPress links in a webview.
 * <p/>
 * Try to use one of the methods below to open the webview:
 * - openURL
 * - openUrlByUsingMainWPCOMCredentials
 * - openUrlByUsingWPCOMCredentials
 * - openUrlByUsingBlogCredentials (for self hosted sites)
 * <p/>
 * If you need to start the activity with delay, start activity with result, or none of the methods above are enough
 * for your needs,
 * you can start the activity by passing the required parameters, depending on what you need to do.
 * <p/>
 * 1. Load a simple URL (without any kind of authentication)
 * - Start the activity with the parameter URL_TO_LOAD set to the URL to load.
 * <p/>
 * 2. Load a WordPress.com URL
 * Start the activity with the following parameters:
 * - URL_TO_LOAD: target URL to load in the webview.
 * - AUTHENTICATION_URL: The address of the WordPress.com authentication endpoint. Please use WPCOM_LOGIN_URL.
 * - AUTHENTICATION_USER: username.
 * - AUTHENTICATION_PASSWD: password.
 * <p/>
 * 3. Load a WordPress.org URL with authentication
 * - URL_TO_LOAD: target URL to load in the webview.
 * - AUTHENTICATION_URL: The address of the authentication endpoint. Please use the value of getSiteLoginUrl()
 * to retrieve the correct address of the authentication endpoint.
 * - AUTHENTICATION_USER: username.
 * - AUTHENTICATION_PASSWD: password.
 * - LOCAL_BLOG_ID: local id of the blog in the app database. This is required since some blogs could have HTTP Auth,
 * or self-signed certs in place.
 * - REFERRER_URL: url to add as an HTTP referrer header, currently only used for non-authed reader posts
 */
public class WPWebViewActivity extends WebViewActivity implements ErrorManagedWebViewClientListener, PrivateAtCookieProgressDialogOnDismissListener, OnShowFileChooserListener {

    public static final String AUTHENTICATION_URL = "authenticated_url";

    public static final String AUTHENTICATION_USER = "authenticated_user";

    public static final String AUTHENTICATION_PASSWD = "authenticated_passwd";

    public static final String USE_GLOBAL_WPCOM_USER = "USE_GLOBAL_WPCOM_USER";

    public static final String URL_TO_LOAD = "url_to_load";

    public static final String WPCOM_LOGIN_URL = "https://wordpress.com/wp-login.php";

    public static final String LOCAL_BLOG_ID = "local_blog_id";

    public static final String SHAREABLE_URL = "shareable_url";

    public static final String SHARE_SUBJECT = "share_subject";

    public static final String REFERRER_URL = "referrer_url";

    public static final String DISABLE_LINKS_ON_PAGE = "DISABLE_LINKS_ON_PAGE";

    public static final String ALLOWED_URLS = "allowed_urls";

    public static final String ENCODING_UTF8 = "UTF-8";

    public static final String WEBVIEW_USAGE_TYPE = "webview_usage_type";

    public static final String ACTION_BAR_TITLE = "action_bar_title";

    public static final String SHOW_PREVIEW_MODE_TOGGLE = "SHOW_PREVIEW_MODE_TOGGLE";

    public static final String PRIVATE_AT_SITE_ID = "PRIVATE_AT_SITE_ID";

    private static final int PREVIEW_INITIAL_SCALE = 90;

    private static final long PREVIEW_JS_EVALUATION_DELAY = 250L;

    @Inject
    AccountStore mAccountStore;

    @Inject
    SiteStore mSiteStore;

    @Inject
    ViewModelProvider.Factory mViewModelFactory;

    @Inject
    UiHelpers mUiHelpers;

    @Inject
    PrivateAtomicCookie mPrivateAtomicCookie;

    @Inject
    Dispatcher mDispatcher;

    @Inject
    DisplayUtilsWrapper mDisplayUtilsWrapper;

    private ActionableEmptyView mActionableEmptyView;

    private ViewGroup mFullScreenProgressLayout;

    private WPWebViewViewModel mViewModel;

    private PreviewModeSelectorPopup mPreviewModeSelectorPopup;

    private ElevationOverlayProvider mElevationOverlayProvider;

    private View mNavBarContainer;

    private LinearLayout mNavBar;

    private View mNavigateForwardButton;

    private View mNavigateBackButton;

    private View mShareButton;

    private View mExternalBrowserButton;

    private View mPreviewModeButton;

    private TextView mDesktopPreviewHint;

    private boolean mPreviewModeChangeAllowed = false;

    private WPWebChromeClientWithFileChooser mWPWebChromeClientWithFileChooser;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(26723)) {
            ((WordPress) getApplication()).component().inject(this);
        }
        if (!ListenerUtil.mutListener.listen(26724)) {
            super.onCreate(savedInstanceState);
        }
    }

    @Override
    public final void configureView() {
        if (!ListenerUtil.mutListener.listen(26725)) {
            setContentView(R.layout.wpwebview_activity);
        }
        if (!ListenerUtil.mutListener.listen(26726)) {
            mActionableEmptyView = findViewById(R.id.actionable_empty_view);
        }
        if (!ListenerUtil.mutListener.listen(26727)) {
            mFullScreenProgressLayout = findViewById(R.id.progress_layout);
        }
        if (!ListenerUtil.mutListener.listen(26728)) {
            mWebView = findViewById(R.id.webView);
        }
        WPWebViewUsageCategory webViewUsageCategory = WPWebViewUsageCategory.fromInt(getIntent().getIntExtra(WEBVIEW_USAGE_TYPE, 0));
        if (!ListenerUtil.mutListener.listen(26729)) {
            initRetryButton();
        }
        if (!ListenerUtil.mutListener.listen(26730)) {
            initViewModel(webViewUsageCategory);
        }
        if (!ListenerUtil.mutListener.listen(26731)) {
            mNavBarContainer = findViewById(R.id.navbar_container);
        }
        if (!ListenerUtil.mutListener.listen(26732)) {
            mElevationOverlayProvider = new ElevationOverlayProvider(WPWebViewActivity.this);
        }
        int elevatedAppbarColor = mElevationOverlayProvider.compositeOverlayWithThemeSurfaceColorIfNeeded(getResources().getDimension(R.dimen.appbar_elevation));
        if (!ListenerUtil.mutListener.listen(26733)) {
            mNavBarContainer.setBackgroundColor(elevatedAppbarColor);
        }
        if (!ListenerUtil.mutListener.listen(26734)) {
            mNavBar = findViewById(R.id.navbar);
        }
        if (!ListenerUtil.mutListener.listen(26735)) {
            mNavigateBackButton = findViewById(R.id.back_button);
        }
        if (!ListenerUtil.mutListener.listen(26736)) {
            mNavigateForwardButton = findViewById(R.id.forward_button);
        }
        if (!ListenerUtil.mutListener.listen(26737)) {
            mShareButton = findViewById(R.id.share_button);
        }
        if (!ListenerUtil.mutListener.listen(26738)) {
            mExternalBrowserButton = findViewById(R.id.external_browser_button);
        }
        if (!ListenerUtil.mutListener.listen(26739)) {
            mPreviewModeButton = findViewById(R.id.preview_type_selector_button);
        }
        if (!ListenerUtil.mutListener.listen(26740)) {
            mDesktopPreviewHint = findViewById(R.id.desktop_preview_hint);
        }
        if (!ListenerUtil.mutListener.listen(26741)) {
            TooltipCompat.setTooltipText(mNavigateBackButton, mNavigateBackButton.getContentDescription());
        }
        if (!ListenerUtil.mutListener.listen(26742)) {
            TooltipCompat.setTooltipText(mNavigateForwardButton, mNavigateForwardButton.getContentDescription());
        }
        if (!ListenerUtil.mutListener.listen(26743)) {
            TooltipCompat.setTooltipText(mShareButton, mShareButton.getContentDescription());
        }
        if (!ListenerUtil.mutListener.listen(26744)) {
            TooltipCompat.setTooltipText(mExternalBrowserButton, mExternalBrowserButton.getContentDescription());
        }
        if (!ListenerUtil.mutListener.listen(26745)) {
            TooltipCompat.setTooltipText(mPreviewModeButton, mPreviewModeButton.getContentDescription());
        }
        if (!ListenerUtil.mutListener.listen(26747)) {
            mNavigateBackButton.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {
                    if (!ListenerUtil.mutListener.listen(26746)) {
                        mViewModel.navigateBack();
                    }
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(26749)) {
            mNavigateForwardButton.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {
                    if (!ListenerUtil.mutListener.listen(26748)) {
                        mViewModel.navigateForward();
                    }
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(26751)) {
            mShareButton.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {
                    if (!ListenerUtil.mutListener.listen(26750)) {
                        mViewModel.share();
                    }
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(26753)) {
            mExternalBrowserButton.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {
                    if (!ListenerUtil.mutListener.listen(26752)) {
                        mViewModel.openPageInExternalBrowser();
                    }
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(26755)) {
            mPreviewModeButton.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {
                    if (!ListenerUtil.mutListener.listen(26754)) {
                        mViewModel.togglePreviewModeSelectorVisibility(true);
                    }
                }
            });
        }
        final Bundle extras = getIntent().getExtras();
        if (!ListenerUtil.mutListener.listen(26760)) {
            if (extras != null) {
                if (!ListenerUtil.mutListener.listen(26756)) {
                    mPreviewModeChangeAllowed = extras.getBoolean(SHOW_PREVIEW_MODE_TOGGLE, false);
                }
                if (!ListenerUtil.mutListener.listen(26759)) {
                    if (!mPreviewModeChangeAllowed) {
                        if (!ListenerUtil.mutListener.listen(26757)) {
                            mNavBar.setWeightSum(80);
                        }
                        if (!ListenerUtil.mutListener.listen(26758)) {
                            mPreviewModeButton.setVisibility(View.GONE);
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(26761)) {
            mPreviewModeSelectorPopup = new PreviewModeSelectorPopup(this, mPreviewModeButton);
        }
        if (!ListenerUtil.mutListener.listen(26762)) {
            setupToolbar();
        }
        if (!ListenerUtil.mutListener.listen(26763)) {
            mViewModel.track(Stat.WEBVIEW_DISPLAYED);
        }
    }

    private void setupToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        if (!ListenerUtil.mutListener.listen(26772)) {
            if (toolbar != null) {
                if (!ListenerUtil.mutListener.listen(26764)) {
                    setSupportActionBar(toolbar);
                }
                ActionBar actionBar = getSupportActionBar();
                if (!ListenerUtil.mutListener.listen(26771)) {
                    if (actionBar != null) {
                        if (!ListenerUtil.mutListener.listen(26765)) {
                            showSubtitle(actionBar);
                        }
                        if (!ListenerUtil.mutListener.listen(26766)) {
                            actionBar.setDisplayShowTitleEnabled(true);
                        }
                        if (!ListenerUtil.mutListener.listen(26767)) {
                            actionBar.setDisplayHomeAsUpEnabled(true);
                        }
                        if (!ListenerUtil.mutListener.listen(26770)) {
                            if (isActionableDirectUsage()) {
                                String title = getIntent().getStringExtra(ACTION_BAR_TITLE);
                                if (!ListenerUtil.mutListener.listen(26769)) {
                                    if (title != null) {
                                        if (!ListenerUtil.mutListener.listen(26768)) {
                                            actionBar.setTitle(title);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private void showSubtitle(ActionBar actionBar) {
        Bundle extras = getIntent().getExtras();
        if (!ListenerUtil.mutListener.listen(26773)) {
            if (extras == null) {
                return;
            }
        }
        String originalUrl = extras.getString(URL_TO_LOAD);
        if (!ListenerUtil.mutListener.listen(26775)) {
            if (originalUrl != null) {
                Uri uri = Uri.parse(originalUrl);
                if (!ListenerUtil.mutListener.listen(26774)) {
                    actionBar.setSubtitle(uri.getHost());
                }
            }
        }
    }

    private void initRetryButton() {
        if (!ListenerUtil.mutListener.listen(26777)) {
            mActionableEmptyView.button.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {
                    if (!ListenerUtil.mutListener.listen(26776)) {
                        mViewModel.loadIfNecessary();
                    }
                }
            });
        }
    }

    private void initViewModel(WPWebViewUsageCategory webViewUsageCategory) {
        if (!ListenerUtil.mutListener.listen(26778)) {
            mViewModel = new ViewModelProvider(this, mViewModelFactory).get(WPWebViewViewModel.class);
        }
        if (!ListenerUtil.mutListener.listen(26788)) {
            mViewModel.getUiState().observe(this, new Observer<WebPreviewUiState>() {

                @Override
                public void onChanged(@Nullable WebPreviewUiState webPreviewUiState) {
                    if (!ListenerUtil.mutListener.listen(26787)) {
                        if (webPreviewUiState != null) {
                            if (!ListenerUtil.mutListener.listen(26779)) {
                                mUiHelpers.updateVisibility(mActionableEmptyView, webPreviewUiState.getActionableEmptyView());
                            }
                            if (!ListenerUtil.mutListener.listen(26780)) {
                                mUiHelpers.updateVisibility(mFullScreenProgressLayout, webPreviewUiState.getFullscreenProgressLayoutVisibility());
                            }
                            if (!ListenerUtil.mutListener.listen(26785)) {
                                if (webPreviewUiState instanceof WebPreviewFullscreenUiState) {
                                    WebPreviewFullscreenUiState state = (WebPreviewFullscreenUiState) webPreviewUiState;
                                    if (!ListenerUtil.mutListener.listen(26781)) {
                                        mUiHelpers.setImageOrHide(mActionableEmptyView.image, state.getImageRes());
                                    }
                                    if (!ListenerUtil.mutListener.listen(26782)) {
                                        mUiHelpers.setTextOrHide(mActionableEmptyView.title, state.getTitleText());
                                    }
                                    if (!ListenerUtil.mutListener.listen(26783)) {
                                        mUiHelpers.setTextOrHide(mActionableEmptyView.subtitle, state.getSubtitleText());
                                    }
                                    if (!ListenerUtil.mutListener.listen(26784)) {
                                        mUiHelpers.updateVisibility(mActionableEmptyView.button, state.getButtonVisibility());
                                    }
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(26786)) {
                                invalidateOptionsMenu();
                            }
                        }
                    }
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(26793)) {
            mViewModel.getLoadNeeded().observe(this, new Observer<Boolean>() {

                @Override
                public void onChanged(@Nullable Boolean loadNeeded) {
                    if (!ListenerUtil.mutListener.listen(26792)) {
                        if ((ListenerUtil.mutListener.listen(26790) ? ((ListenerUtil.mutListener.listen(26789) ? (!isActionableDirectUsage() || loadNeeded != null) : (!isActionableDirectUsage() && loadNeeded != null)) || loadNeeded) : ((ListenerUtil.mutListener.listen(26789) ? (!isActionableDirectUsage() || loadNeeded != null) : (!isActionableDirectUsage() && loadNeeded != null)) && loadNeeded))) {
                            if (!ListenerUtil.mutListener.listen(26791)) {
                                loadContent();
                            }
                        }
                    }
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(26799)) {
            mViewModel.getNavbarUiState().observe(this, new Observer<NavBarUiState>() {

                @Override
                public void onChanged(@Nullable NavBarUiState navBarUiState) {
                    if (!ListenerUtil.mutListener.listen(26798)) {
                        if (navBarUiState != null) {
                            if (!ListenerUtil.mutListener.listen(26794)) {
                                mNavigateBackButton.setEnabled(navBarUiState.getBackNavigationEnabled());
                            }
                            if (!ListenerUtil.mutListener.listen(26795)) {
                                mNavigateForwardButton.setEnabled(navBarUiState.getForwardNavigationEnabled());
                            }
                            if (!ListenerUtil.mutListener.listen(26796)) {
                                AniUtils.animateBottomBar(mDesktopPreviewHint, navBarUiState.getPreviewModeHintVisible());
                            }
                            if (!ListenerUtil.mutListener.listen(26797)) {
                                mDesktopPreviewHint.setText(navBarUiState.getReviewHintResId());
                            }
                        }
                    }
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(26803)) {
            mViewModel.getNavigateBack().observe(this, new Observer<Unit>() {

                @Override
                public void onChanged(@Nullable Unit unit) {
                    if (!ListenerUtil.mutListener.listen(26802)) {
                        if (mWebView.canGoBack()) {
                            if (!ListenerUtil.mutListener.listen(26800)) {
                                mWebView.goBack();
                            }
                            if (!ListenerUtil.mutListener.listen(26801)) {
                                refreshBackForwardNavButtons();
                            }
                        }
                    }
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(26807)) {
            mViewModel.getNavigateForward().observe(this, new Observer<Unit>() {

                @Override
                public void onChanged(@Nullable Unit unit) {
                    if (!ListenerUtil.mutListener.listen(26806)) {
                        if (mWebView.canGoForward()) {
                            if (!ListenerUtil.mutListener.listen(26804)) {
                                mWebView.goForward();
                            }
                            if (!ListenerUtil.mutListener.listen(26805)) {
                                refreshBackForwardNavButtons();
                            }
                        }
                    }
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(26815)) {
            mViewModel.getShare().observe(this, new Observer<Unit>() {

                @Override
                public void onChanged(@Nullable Unit unit) {
                    Intent share = new Intent(Intent.ACTION_SEND);
                    if (!ListenerUtil.mutListener.listen(26808)) {
                        share.setType("text/plain");
                    }
                    // Use the preferred shareable URL or the default webview URL
                    Bundle extras = getIntent().getExtras();
                    String shareableUrl = extras.getString(SHAREABLE_URL, null);
                    if (!ListenerUtil.mutListener.listen(26810)) {
                        if (TextUtils.isEmpty(shareableUrl)) {
                            if (!ListenerUtil.mutListener.listen(26809)) {
                                shareableUrl = mWebView.getUrl();
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(26811)) {
                        share.putExtra(Intent.EXTRA_TEXT, shareableUrl);
                    }
                    String shareSubject = extras.getString(SHARE_SUBJECT, null);
                    if (!ListenerUtil.mutListener.listen(26813)) {
                        if (!TextUtils.isEmpty(shareSubject)) {
                            if (!ListenerUtil.mutListener.listen(26812)) {
                                share.putExtra(Intent.EXTRA_SUBJECT, shareSubject);
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(26814)) {
                        startActivity(Intent.createChooser(share, getText(R.string.share_link)));
                    }
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(26817)) {
            mViewModel.getOpenExternalBrowser().observe(this, new Observer<Unit>() {

                @Override
                public void onChanged(@Nullable Unit unit) {
                    if (!ListenerUtil.mutListener.listen(26816)) {
                        ReaderActivityLauncher.openUrl(WPWebViewActivity.this, mWebView.getUrl(), ReaderActivityLauncher.OpenUrlType.EXTERNAL);
                    }
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(26822)) {
            mViewModel.getPreviewModeSelector().observe(this, new Observer<PreviewModeSelectorStatus>() {

                @Override
                public void onChanged(@Nullable final PreviewModeSelectorStatus previewModelSelectorStatus) {
                    if (!ListenerUtil.mutListener.listen(26821)) {
                        if (previewModelSelectorStatus != null) {
                            if (!ListenerUtil.mutListener.listen(26818)) {
                                mPreviewModeButton.setEnabled(previewModelSelectorStatus.isEnabled());
                            }
                            if (!ListenerUtil.mutListener.listen(26820)) {
                                if (previewModelSelectorStatus.isVisible()) {
                                    if (!ListenerUtil.mutListener.listen(26819)) {
                                        mPreviewModeSelectorPopup.show(mViewModel);
                                    }
                                }
                            }
                        }
                    }
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(26824)) {
            mViewModel.getPreviewMode().observe(this, new Observer<PreviewMode>() {

                @Override
                public void onChanged(@Nullable PreviewMode previewMode) {
                    if (!ListenerUtil.mutListener.listen(26823)) {
                        mWebView.reload();
                    }
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(26825)) {
            mViewModel.start(webViewUsageCategory);
        }
    }

    public static void openUrlByUsingGlobalWPCOMCredentials(Context context, String url) {
        if (!ListenerUtil.mutListener.listen(26826)) {
            openWPCOMURL(context, url, null, null, false, false);
        }
    }

    public static void openUrlByUsingGlobalWPCOMCredentials(Context context, String url, boolean allowPreviewModeSelection) {
        if (!ListenerUtil.mutListener.listen(26827)) {
            openWPCOMURL(context, url, null, null, allowPreviewModeSelection, false);
        }
    }

    public static void openPostUrlByUsingGlobalWPCOMCredentials(Context context, String url, String shareableUrl, String shareSubject, boolean allowPreviewModeSelection, boolean startPreviewForResult) {
        if (!ListenerUtil.mutListener.listen(26828)) {
            openWPCOMURL(context, url, shareableUrl, shareSubject, allowPreviewModeSelection, startPreviewForResult);
        }
    }

    // frameNonce is used to show drafts, without it "no page found" error would be thrown
    public static void openJetpackBlogPostPreview(Context context, String url, String shareableUrl, String shareSubject, String frameNonce, boolean allowPreviewModeSelection, boolean startPreviewForResult, long privateSiteId) {
        if (!ListenerUtil.mutListener.listen(26830)) {
            if (!TextUtils.isEmpty(frameNonce)) {
                if (!ListenerUtil.mutListener.listen(26829)) {
                    url += "&frame-nonce=" + UrlUtils.urlEncode(frameNonce);
                }
            }
        }
        Intent intent = new Intent(context, WPWebViewActivity.class);
        if (!ListenerUtil.mutListener.listen(26831)) {
            intent.putExtra(WPWebViewActivity.URL_TO_LOAD, url);
        }
        if (!ListenerUtil.mutListener.listen(26832)) {
            intent.putExtra(WPWebViewActivity.DISABLE_LINKS_ON_PAGE, false);
        }
        if (!ListenerUtil.mutListener.listen(26833)) {
            intent.putExtra(WPWebViewActivity.SHOW_PREVIEW_MODE_TOGGLE, allowPreviewModeSelection);
        }
        if (!ListenerUtil.mutListener.listen(26835)) {
            if (!TextUtils.isEmpty(shareableUrl)) {
                if (!ListenerUtil.mutListener.listen(26834)) {
                    intent.putExtra(WPWebViewActivity.SHAREABLE_URL, shareableUrl);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(26837)) {
            if (!TextUtils.isEmpty(shareSubject)) {
                if (!ListenerUtil.mutListener.listen(26836)) {
                    intent.putExtra(WPWebViewActivity.SHARE_SUBJECT, shareSubject);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(26844)) {
            if ((ListenerUtil.mutListener.listen(26842) ? (privateSiteId >= 0) : (ListenerUtil.mutListener.listen(26841) ? (privateSiteId <= 0) : (ListenerUtil.mutListener.listen(26840) ? (privateSiteId < 0) : (ListenerUtil.mutListener.listen(26839) ? (privateSiteId != 0) : (ListenerUtil.mutListener.listen(26838) ? (privateSiteId == 0) : (privateSiteId > 0))))))) {
                if (!ListenerUtil.mutListener.listen(26843)) {
                    intent.putExtra(WPWebViewActivity.PRIVATE_AT_SITE_ID, privateSiteId);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(26848)) {
            if (startPreviewForResult) {
                if (!ListenerUtil.mutListener.listen(26846)) {
                    intent.putExtra(WPWebViewActivity.WEBVIEW_USAGE_TYPE, WPWebViewUsageCategory.REMOTE_PREVIEWING.getValue());
                }
                if (!ListenerUtil.mutListener.listen(26847)) {
                    ((Activity) context).startActivityForResult(intent, RequestCodes.REMOTE_PREVIEW_POST);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(26845)) {
                    context.startActivity(intent);
                }
            }
        }
    }

    public static void openUrlByUsingBlogCredentials(Context context, SiteModel site, PostImmutableModel post, String url, String[] listOfAllowedURLs, boolean disableLinks, boolean allowPreviewModeSelection, boolean startPreviewForResult) {
        if (!ListenerUtil.mutListener.listen(26850)) {
            if (context == null) {
                if (!ListenerUtil.mutListener.listen(26849)) {
                    AppLog.e(AppLog.T.UTILS, "Context is null");
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(26852)) {
            if (site == null) {
                if (!ListenerUtil.mutListener.listen(26851)) {
                    AppLog.e(AppLog.T.UTILS, "Site is null");
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(26855)) {
            if (TextUtils.isEmpty(url)) {
                if (!ListenerUtil.mutListener.listen(26853)) {
                    AppLog.e(AppLog.T.UTILS, "Empty or null URL");
                }
                if (!ListenerUtil.mutListener.listen(26854)) {
                    ToastUtils.showToast(context, R.string.invalid_site_url_message, ToastUtils.Duration.SHORT);
                }
                return;
            }
        }
        String authURL = WPWebViewActivity.getSiteLoginUrl(site);
        Intent intent = new Intent(context, WPWebViewActivity.class);
        if (!ListenerUtil.mutListener.listen(26856)) {
            intent.putExtra(WPWebViewActivity.AUTHENTICATION_USER, site.getUsername());
        }
        if (!ListenerUtil.mutListener.listen(26857)) {
            intent.putExtra(WPWebViewActivity.AUTHENTICATION_PASSWD, site.getPassword());
        }
        if (!ListenerUtil.mutListener.listen(26858)) {
            intent.putExtra(WPWebViewActivity.URL_TO_LOAD, url);
        }
        if (!ListenerUtil.mutListener.listen(26859)) {
            intent.putExtra(WPWebViewActivity.AUTHENTICATION_URL, authURL);
        }
        if (!ListenerUtil.mutListener.listen(26860)) {
            intent.putExtra(WPWebViewActivity.LOCAL_BLOG_ID, site.getId());
        }
        if (!ListenerUtil.mutListener.listen(26861)) {
            intent.putExtra(WPWebViewActivity.DISABLE_LINKS_ON_PAGE, disableLinks);
        }
        if (!ListenerUtil.mutListener.listen(26862)) {
            intent.putExtra(WPWebViewActivity.SHOW_PREVIEW_MODE_TOGGLE, allowPreviewModeSelection);
        }
        if (!ListenerUtil.mutListener.listen(26863)) {
            intent.putExtra(ALLOWED_URLS, listOfAllowedURLs);
        }
        if (!ListenerUtil.mutListener.listen(26867)) {
            if (post != null) {
                if (!ListenerUtil.mutListener.listen(26864)) {
                    intent.putExtra(WPWebViewActivity.SHAREABLE_URL, post.getLink());
                }
                if (!ListenerUtil.mutListener.listen(26866)) {
                    if (!TextUtils.isEmpty(post.getTitle())) {
                        if (!ListenerUtil.mutListener.listen(26865)) {
                            intent.putExtra(WPWebViewActivity.SHARE_SUBJECT, post.getTitle());
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(26871)) {
            if (startPreviewForResult) {
                if (!ListenerUtil.mutListener.listen(26869)) {
                    intent.putExtra(WPWebViewActivity.WEBVIEW_USAGE_TYPE, WPWebViewUsageCategory.REMOTE_PREVIEWING.getValue());
                }
                if (!ListenerUtil.mutListener.listen(26870)) {
                    ((Activity) context).startActivityForResult(intent, RequestCodes.REMOTE_PREVIEW_POST);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(26868)) {
                    context.startActivity(intent);
                }
            }
        }
    }

    public static void openActionableEmptyViewDirectly(Context context, WPWebViewUsageCategory directUsageCategory, String postTitle) {
        Intent intent = new Intent(context, WPWebViewActivity.class);
        if (!ListenerUtil.mutListener.listen(26872)) {
            intent.putExtra(WPWebViewActivity.WEBVIEW_USAGE_TYPE, directUsageCategory.getValue());
        }
        if (!ListenerUtil.mutListener.listen(26873)) {
            intent.putExtra(WPWebViewActivity.ACTION_BAR_TITLE, postTitle);
        }
        if (!ListenerUtil.mutListener.listen(26874)) {
            context.startActivity(intent);
        }
    }

    protected void toggleNavbarVisibility(boolean isVisible) {
        if (!ListenerUtil.mutListener.listen(26878)) {
            if (mNavBarContainer != null) {
                if (!ListenerUtil.mutListener.listen(26877)) {
                    if (isVisible) {
                        if (!ListenerUtil.mutListener.listen(26876)) {
                            mNavBarContainer.setVisibility(View.VISIBLE);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(26875)) {
                            mNavBarContainer.setVisibility(View.GONE);
                        }
                    }
                }
            }
        }
    }

    public static void openURL(Context context, String url) {
        if (!ListenerUtil.mutListener.listen(26879)) {
            openURL(context, url, false, 0);
        }
    }

    public static void openURL(Context context, String url, String referrer) {
        if (!ListenerUtil.mutListener.listen(26880)) {
            openURL(context, url, referrer, false, 0);
        }
    }

    public static void openURL(Context context, String url, boolean allowPreviewModeSelection, long privateSiteId) {
        if (!ListenerUtil.mutListener.listen(26881)) {
            openURL(context, url, null, allowPreviewModeSelection, privateSiteId);
        }
    }

    public static void openURL(Context context, String url, String referrer, boolean allowPreviewModeSelection, long privateSiteId) {
        if (!ListenerUtil.mutListener.listen(26883)) {
            if (context == null) {
                if (!ListenerUtil.mutListener.listen(26882)) {
                    AppLog.e(AppLog.T.UTILS, "Context is null");
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(26886)) {
            if (TextUtils.isEmpty(url)) {
                if (!ListenerUtil.mutListener.listen(26884)) {
                    AppLog.e(AppLog.T.UTILS, "Empty or null URL");
                }
                if (!ListenerUtil.mutListener.listen(26885)) {
                    ToastUtils.showToast(context, R.string.invalid_site_url_message, ToastUtils.Duration.SHORT);
                }
                return;
            }
        }
        Intent intent = new Intent(context, WPWebViewActivity.class);
        if (!ListenerUtil.mutListener.listen(26887)) {
            intent.putExtra(WPWebViewActivity.URL_TO_LOAD, url);
        }
        if (!ListenerUtil.mutListener.listen(26888)) {
            intent.putExtra(WPWebViewActivity.SHOW_PREVIEW_MODE_TOGGLE, allowPreviewModeSelection);
        }
        if (!ListenerUtil.mutListener.listen(26895)) {
            if ((ListenerUtil.mutListener.listen(26893) ? (privateSiteId >= 0) : (ListenerUtil.mutListener.listen(26892) ? (privateSiteId <= 0) : (ListenerUtil.mutListener.listen(26891) ? (privateSiteId < 0) : (ListenerUtil.mutListener.listen(26890) ? (privateSiteId != 0) : (ListenerUtil.mutListener.listen(26889) ? (privateSiteId == 0) : (privateSiteId > 0))))))) {
                if (!ListenerUtil.mutListener.listen(26894)) {
                    intent.putExtra(WPWebViewActivity.PRIVATE_AT_SITE_ID, privateSiteId);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(26897)) {
            if (!TextUtils.isEmpty(referrer)) {
                if (!ListenerUtil.mutListener.listen(26896)) {
                    intent.putExtra(REFERRER_URL, referrer);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(26898)) {
            context.startActivity(intent);
        }
    }

    protected static boolean checkContextAndUrl(Context context, String url) {
        if (!ListenerUtil.mutListener.listen(26900)) {
            if (context == null) {
                if (!ListenerUtil.mutListener.listen(26899)) {
                    AppLog.e(AppLog.T.UTILS, "Context is null");
                }
                return false;
            }
        }
        if (!ListenerUtil.mutListener.listen(26903)) {
            if (TextUtils.isEmpty(url)) {
                if (!ListenerUtil.mutListener.listen(26901)) {
                    AppLog.e(AppLog.T.UTILS, "Empty or null URL passed to openUrlByUsingMainWPCOMCredentials");
                }
                if (!ListenerUtil.mutListener.listen(26902)) {
                    ToastUtils.showToast(context, R.string.invalid_site_url_message, ToastUtils.Duration.SHORT);
                }
                return false;
            }
        }
        return true;
    }

    private static void openWPCOMURL(Context context, String url, String shareableUrl, String shareSubject) {
        if (!ListenerUtil.mutListener.listen(26904)) {
            openWPCOMURL(context, url, shareableUrl, shareSubject, false, false);
        }
    }

    private static void openWPCOMURL(Context context, String url, String shareableUrl, String shareSubject, boolean allowPreviewModeSelection, boolean startPreviewForResult) {
        if (!ListenerUtil.mutListener.listen(26905)) {
            if (!checkContextAndUrl(context, url)) {
                return;
            }
        }
        Intent intent = new Intent(context, WPWebViewActivity.class);
        if (!ListenerUtil.mutListener.listen(26906)) {
            intent.putExtra(WPWebViewActivity.USE_GLOBAL_WPCOM_USER, true);
        }
        if (!ListenerUtil.mutListener.listen(26907)) {
            intent.putExtra(WPWebViewActivity.URL_TO_LOAD, url);
        }
        if (!ListenerUtil.mutListener.listen(26908)) {
            intent.putExtra(WPWebViewActivity.AUTHENTICATION_URL, WPCOM_LOGIN_URL);
        }
        if (!ListenerUtil.mutListener.listen(26909)) {
            intent.putExtra(WPWebViewActivity.SHOW_PREVIEW_MODE_TOGGLE, allowPreviewModeSelection);
        }
        if (!ListenerUtil.mutListener.listen(26911)) {
            if (!TextUtils.isEmpty(shareableUrl)) {
                if (!ListenerUtil.mutListener.listen(26910)) {
                    intent.putExtra(WPWebViewActivity.SHAREABLE_URL, shareableUrl);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(26913)) {
            if (!TextUtils.isEmpty(shareSubject)) {
                if (!ListenerUtil.mutListener.listen(26912)) {
                    intent.putExtra(WPWebViewActivity.SHARE_SUBJECT, shareSubject);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(26917)) {
            if (startPreviewForResult) {
                if (!ListenerUtil.mutListener.listen(26915)) {
                    intent.putExtra(WPWebViewActivity.WEBVIEW_USAGE_TYPE, WPWebViewUsageCategory.REMOTE_PREVIEWING.getValue());
                }
                if (!ListenerUtil.mutListener.listen(26916)) {
                    ((Activity) context).startActivityForResult(intent, RequestCodes.REMOTE_PREVIEW_POST);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(26914)) {
                    context.startActivity(intent);
                }
            }
        }
    }

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void configureWebView() {
        if (!ListenerUtil.mutListener.listen(26918)) {
            if (isActionableDirectUsage()) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(26919)) {
            mWebView.getSettings().setJavaScriptEnabled(true);
        }
        if (!ListenerUtil.mutListener.listen(26920)) {
            mWebView.getSettings().setDomStorageEnabled(true);
        }
        CookieManager cookieManager = CookieManager.getInstance();
        if (!ListenerUtil.mutListener.listen(26921)) {
            cookieManager.setAcceptThirdPartyCookies(mWebView, true);
        }
        final Bundle extras = getIntent().getExtras();
        // Configure the allowed URLs if available
        ArrayList<String> allowedURL = null;
        if (!ListenerUtil.mutListener.listen(26931)) {
            if ((ListenerUtil.mutListener.listen(26922) ? (extras != null || extras.getBoolean(DISABLE_LINKS_ON_PAGE, false)) : (extras != null && extras.getBoolean(DISABLE_LINKS_ON_PAGE, false)))) {
                String addressToLoad = extras.getString(URL_TO_LOAD);
                String authURL = extras.getString(AUTHENTICATION_URL);
                if (!ListenerUtil.mutListener.listen(26923)) {
                    allowedURL = new ArrayList<>();
                }
                if (!ListenerUtil.mutListener.listen(26925)) {
                    if (!TextUtils.isEmpty(addressToLoad)) {
                        if (!ListenerUtil.mutListener.listen(26924)) {
                            allowedURL.add(addressToLoad);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(26927)) {
                    if (!TextUtils.isEmpty(authURL)) {
                        if (!ListenerUtil.mutListener.listen(26926)) {
                            allowedURL.add(authURL);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(26930)) {
                    if (extras.getStringArray(ALLOWED_URLS) != null) {
                        String[] urls = extras.getStringArray(ALLOWED_URLS);
                        if (!ListenerUtil.mutListener.listen(26929)) {
                            {
                                long _loopCounter401 = 0;
                                for (String currentURL : urls) {
                                    ListenerUtil.loopListener.listen("_loopCounter401", ++_loopCounter401);
                                    if (!ListenerUtil.mutListener.listen(26928)) {
                                        allowedURL.add(currentURL);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(26934)) {
            if (mPreviewModeChangeAllowed) {
                if (!ListenerUtil.mutListener.listen(26932)) {
                    mWebView.getSettings().setUseWideViewPort(true);
                }
                if (!ListenerUtil.mutListener.listen(26933)) {
                    mWebView.setInitialScale(PREVIEW_INITIAL_SCALE);
                }
            }
        }
        WebViewClient webViewClient = createWebViewClient(allowedURL);
        if (!ListenerUtil.mutListener.listen(26935)) {
            mWebView.setWebViewClient(webViewClient);
        }
        if (!ListenerUtil.mutListener.listen(26936)) {
            mWPWebChromeClientWithFileChooser = new WPWebChromeClientWithFileChooser(this, mWebView, R.drawable.media_movieclip, (ProgressBar) findViewById(R.id.progress_bar), this);
        }
        if (!ListenerUtil.mutListener.listen(26937)) {
            mWebView.setWebChromeClient(mWPWebChromeClientWithFileChooser);
        }
    }

    protected WebViewClient createWebViewClient(List<String> allowedURL) {
        URLFilteredWebViewClient webViewClient;
        if (getIntent().hasExtra(LOCAL_BLOG_ID)) {
            SiteModel site = mSiteStore.getSiteByLocalId(getIntent().getIntExtra(LOCAL_BLOG_ID, -1));
            if (!ListenerUtil.mutListener.listen(26940)) {
                if (site == null) {
                    if (!ListenerUtil.mutListener.listen(26938)) {
                        AppLog.e(AppLog.T.UTILS, "No valid blog passed to WPWebViewActivity");
                    }
                    if (!ListenerUtil.mutListener.listen(26939)) {
                        setResultIfNeededAndFinish();
                    }
                }
            }
            webViewClient = new WPWebViewClient(site, mAccountStore.getAccessToken(), allowedURL, this);
        } else {
            webViewClient = new URLFilteredWebViewClient(allowedURL, this);
        }
        return webViewClient;
    }

    @Override
    public void onWebViewPageLoaded() {
        if (!ListenerUtil.mutListener.listen(26943)) {
            if (mPreviewModeChangeAllowed) {
                if (!ListenerUtil.mutListener.listen(26942)) {
                    enforcePreviewMode();
                }
            } else {
                if (!ListenerUtil.mutListener.listen(26941)) {
                    mViewModel.onUrlLoaded();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(26944)) {
            refreshBackForwardNavButtons();
        }
    }

    private void refreshBackForwardNavButtons() {
        if (!ListenerUtil.mutListener.listen(26945)) {
            mViewModel.toggleBackNavigation(mWebView.canGoBack());
        }
        if (!ListenerUtil.mutListener.listen(26946)) {
            mViewModel.toggleForwardNavigation(mWebView.canGoForward());
        }
    }

    private void enforcePreviewMode() {
        int previewWidth = mViewModel.selectedPreviewMode().getPreviewWidth();
        if (!ListenerUtil.mutListener.listen(26947)) {
            setWebViewWidth(previewWidth);
        }
        String script = getResources().getString(R.string.web_preview_width_script, previewWidth);
        if (!ListenerUtil.mutListener.listen(26949)) {
            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    if (!ListenerUtil.mutListener.listen(26948)) {
                        mWebView.evaluateJavascript(script, value -> mViewModel.onUrlLoaded());
                    }
                }
            }, PREVIEW_JS_EVALUATION_DELAY);
        }
    }

    private void setWebViewWidth(int previewWidth) {
        if (!ListenerUtil.mutListener.listen(26950)) {
            if (!mDisplayUtilsWrapper.isTablet())
                return;
        }
        if (!ListenerUtil.mutListener.listen(26958)) {
            if (mViewModel.selectedPreviewMode() == PreviewMode.MOBILE) {
                int width = (ListenerUtil.mutListener.listen(26955) ? (previewWidth % (int) getResources().getDisplayMetrics().density) : (ListenerUtil.mutListener.listen(26954) ? (previewWidth / (int) getResources().getDisplayMetrics().density) : (ListenerUtil.mutListener.listen(26953) ? (previewWidth - (int) getResources().getDisplayMetrics().density) : (ListenerUtil.mutListener.listen(26952) ? (previewWidth + (int) getResources().getDisplayMetrics().density) : (previewWidth * (int) getResources().getDisplayMetrics().density)))));
                LayoutParams params = new LayoutParams(width, LayoutParams.MATCH_PARENT);
                if (!ListenerUtil.mutListener.listen(26956)) {
                    params.addRule(RelativeLayout.CENTER_HORIZONTAL);
                }
                if (!ListenerUtil.mutListener.listen(26957)) {
                    mWebView.setLayoutParams(params);
                }
            } else {
                LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
                if (!ListenerUtil.mutListener.listen(26951)) {
                    mWebView.setLayoutParams(params);
                }
            }
        }
    }

    @Override
    public void onWebViewReceivedError() {
        if (!ListenerUtil.mutListener.listen(26959)) {
            mViewModel.onReceivedError();
        }
    }

    @Override
    protected void loadContent() {
        if (!ListenerUtil.mutListener.listen(26960)) {
            if (isActionableDirectUsage()) {
                return;
            }
        }
        Bundle extras = getIntent().getExtras();
        if (!ListenerUtil.mutListener.listen(26963)) {
            if (extras == null) {
                if (!ListenerUtil.mutListener.listen(26961)) {
                    AppLog.e(AppLog.T.UTILS, "No valid parameters passed to WPWebViewActivity");
                }
                if (!ListenerUtil.mutListener.listen(26962)) {
                    setResultIfNeededAndFinish();
                }
                return;
            }
        }
        // if we load content of private AT site we need to make sure we have a special cookie first
        long privateAtSiteId = extras.getLong(PRIVATE_AT_SITE_ID);
        if (!ListenerUtil.mutListener.listen(26979)) {
            if ((ListenerUtil.mutListener.listen(26969) ? ((ListenerUtil.mutListener.listen(26968) ? (privateAtSiteId >= 0) : (ListenerUtil.mutListener.listen(26967) ? (privateAtSiteId <= 0) : (ListenerUtil.mutListener.listen(26966) ? (privateAtSiteId < 0) : (ListenerUtil.mutListener.listen(26965) ? (privateAtSiteId != 0) : (ListenerUtil.mutListener.listen(26964) ? (privateAtSiteId == 0) : (privateAtSiteId > 0)))))) || mPrivateAtomicCookie.isCookieRefreshRequired()) : ((ListenerUtil.mutListener.listen(26968) ? (privateAtSiteId >= 0) : (ListenerUtil.mutListener.listen(26967) ? (privateAtSiteId <= 0) : (ListenerUtil.mutListener.listen(26966) ? (privateAtSiteId < 0) : (ListenerUtil.mutListener.listen(26965) ? (privateAtSiteId != 0) : (ListenerUtil.mutListener.listen(26964) ? (privateAtSiteId == 0) : (privateAtSiteId > 0)))))) && mPrivateAtomicCookie.isCookieRefreshRequired()))) {
                if (!ListenerUtil.mutListener.listen(26977)) {
                    PrivateAtCookieRefreshProgressDialog.Companion.showIfNecessary(getSupportFragmentManager());
                }
                if (!ListenerUtil.mutListener.listen(26978)) {
                    mDispatcher.dispatch(SiteActionBuilder.newFetchPrivateAtomicCookieAction(new FetchPrivateAtomicCookiePayload(privateAtSiteId)));
                }
                return;
            } else if ((ListenerUtil.mutListener.listen(26975) ? ((ListenerUtil.mutListener.listen(26974) ? (privateAtSiteId >= 0) : (ListenerUtil.mutListener.listen(26973) ? (privateAtSiteId <= 0) : (ListenerUtil.mutListener.listen(26972) ? (privateAtSiteId < 0) : (ListenerUtil.mutListener.listen(26971) ? (privateAtSiteId != 0) : (ListenerUtil.mutListener.listen(26970) ? (privateAtSiteId == 0) : (privateAtSiteId > 0)))))) || mPrivateAtomicCookie.exists()) : ((ListenerUtil.mutListener.listen(26974) ? (privateAtSiteId >= 0) : (ListenerUtil.mutListener.listen(26973) ? (privateAtSiteId <= 0) : (ListenerUtil.mutListener.listen(26972) ? (privateAtSiteId < 0) : (ListenerUtil.mutListener.listen(26971) ? (privateAtSiteId != 0) : (ListenerUtil.mutListener.listen(26970) ? (privateAtSiteId == 0) : (privateAtSiteId > 0)))))) && mPrivateAtomicCookie.exists()))) {
                if (!ListenerUtil.mutListener.listen(26976)) {
                    // make sure we add cookie to the cookie manager if it exists
                    CookieManager.getInstance().setCookie(mPrivateAtomicCookie.getDomain(), mPrivateAtomicCookie.getCookieContent());
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(26980)) {
            loadWebContent();
        }
    }

    private void loadWebContent() {
        Bundle extras = getIntent().getExtras();
        String addressToLoad = extras.getString(URL_TO_LOAD);
        String username = extras.getString(AUTHENTICATION_USER, "");
        String password = extras.getString(AUTHENTICATION_PASSWD, "");
        String authURL = extras.getString(AUTHENTICATION_URL);
        if (!ListenerUtil.mutListener.listen(26985)) {
            if ((ListenerUtil.mutListener.listen(26981) ? (TextUtils.isEmpty(addressToLoad) && !UrlUtils.isValidUrlAndHostNotNull(addressToLoad)) : (TextUtils.isEmpty(addressToLoad) || !UrlUtils.isValidUrlAndHostNotNull(addressToLoad)))) {
                if (!ListenerUtil.mutListener.listen(26982)) {
                    AppLog.e(AppLog.T.UTILS, "Empty or null or invalid URL passed to WPWebViewActivity");
                }
                if (!ListenerUtil.mutListener.listen(26983)) {
                    ToastUtils.showToast(this, R.string.invalid_site_url_message, ToastUtils.Duration.SHORT);
                }
                if (!ListenerUtil.mutListener.listen(26984)) {
                    setResultIfNeededAndFinish();
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(26992)) {
            if (extras.getBoolean(USE_GLOBAL_WPCOM_USER, false)) {
                if (!ListenerUtil.mutListener.listen(26986)) {
                    username = mAccountStore.getAccount().getUserName();
                }
                if (!ListenerUtil.mutListener.listen(26991)) {
                    // Custom domains are not properly authenticated due to a server side(?) issue, so this gets around that
                    if (!addressToLoad.contains(".wordpress.com")) {
                        List<SiteModel> wpComSites = mSiteStore.getWPComSites();
                        if (!ListenerUtil.mutListener.listen(26990)) {
                            {
                                long _loopCounter402 = 0;
                                for (SiteModel siteModel : wpComSites) {
                                    ListenerUtil.loopListener.listen("_loopCounter402", ++_loopCounter402);
                                    if (!ListenerUtil.mutListener.listen(26989)) {
                                        // Only replace the url if we know the unmapped url and if it's a custom domain
                                        if ((ListenerUtil.mutListener.listen(26987) ? (!TextUtils.isEmpty(siteModel.getUnmappedUrl()) || !siteModel.getUrl().contains(".wordpress.com")) : (!TextUtils.isEmpty(siteModel.getUnmappedUrl()) && !siteModel.getUrl().contains(".wordpress.com")))) {
                                            if (!ListenerUtil.mutListener.listen(26988)) {
                                                addressToLoad = addressToLoad.replace(siteModel.getUrl(), siteModel.getUnmappedUrl());
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(27009)) {
            if ((ListenerUtil.mutListener.listen(26994) ? ((ListenerUtil.mutListener.listen(26993) ? (TextUtils.isEmpty(authURL) || TextUtils.isEmpty(username)) : (TextUtils.isEmpty(authURL) && TextUtils.isEmpty(username))) || TextUtils.isEmpty(password)) : ((ListenerUtil.mutListener.listen(26993) ? (TextUtils.isEmpty(authURL) || TextUtils.isEmpty(username)) : (TextUtils.isEmpty(authURL) && TextUtils.isEmpty(username))) && TextUtils.isEmpty(password)))) {
                // loader, optionally with our referrer header
                String referrerUrl = extras.getString(REFERRER_URL);
                if (!ListenerUtil.mutListener.listen(27008)) {
                    if (!TextUtils.isEmpty(referrerUrl)) {
                        Map<String, String> headers = new HashMap<>();
                        if (!ListenerUtil.mutListener.listen(27006)) {
                            headers.put("Referer", referrerUrl);
                        }
                        if (!ListenerUtil.mutListener.listen(27007)) {
                            loadUrl(addressToLoad, headers);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(27005)) {
                            loadUrl(addressToLoad);
                        }
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(26999)) {
                    if ((ListenerUtil.mutListener.listen(26995) ? (TextUtils.isEmpty(authURL) && !UrlUtils.isValidUrlAndHostNotNull(authURL)) : (TextUtils.isEmpty(authURL) || !UrlUtils.isValidUrlAndHostNotNull(authURL)))) {
                        if (!ListenerUtil.mutListener.listen(26996)) {
                            AppLog.e(AppLog.T.UTILS, "Empty or null or invalid auth URL passed to WPWebViewActivity");
                        }
                        if (!ListenerUtil.mutListener.listen(26997)) {
                            ToastUtils.showToast(this, R.string.invalid_site_url_message, ToastUtils.Duration.SHORT);
                        }
                        if (!ListenerUtil.mutListener.listen(26998)) {
                            setResultIfNeededAndFinish();
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(27003)) {
                    if (TextUtils.isEmpty(username)) {
                        if (!ListenerUtil.mutListener.listen(27000)) {
                            AppLog.e(AppLog.T.UTILS, "Username empty/null");
                        }
                        if (!ListenerUtil.mutListener.listen(27001)) {
                            ToastUtils.showToast(this, R.string.incorrect_credentials, ToastUtils.Duration.SHORT);
                        }
                        if (!ListenerUtil.mutListener.listen(27002)) {
                            setResultIfNeededAndFinish();
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(27004)) {
                    loadAuthenticatedUrl(authURL, addressToLoad, username, password);
                }
            }
        }
    }

    /**
     * Login to the WordPress.com and load the specified URL.
     */
    protected void loadAuthenticatedUrl(String authenticationURL, String urlToLoad, String username, String password) {
        String postData = getAuthenticationPostData(authenticationURL, urlToLoad, username, password, mAccountStore.getAccessToken());
        if (!ListenerUtil.mutListener.listen(27010)) {
            mWebView.postUrl(authenticationURL, postData.getBytes());
        }
    }

    public static String getAuthenticationPostData(String authenticationUrl, String urlToLoad, String username, String password, String token) {
        if (!ListenerUtil.mutListener.listen(27011)) {
            if (TextUtils.isEmpty(authenticationUrl)) {
                return "";
            }
        }
        try {
            String postData = String.format("log=%s&pwd=%s&redirect_to=%s", URLEncoder.encode(StringUtils.notNullStr(username), ENCODING_UTF8), URLEncoder.encode(StringUtils.notNullStr(password), ENCODING_UTF8), URLEncoder.encode(StringUtils.notNullStr(urlToLoad), ENCODING_UTF8));
            if (!ListenerUtil.mutListener.listen(27016)) {
                // Add token authorization when signing in to WP.com
                if ((ListenerUtil.mutListener.listen(27014) ? ((ListenerUtil.mutListener.listen(27013) ? (WPUrlUtils.safeToAddWordPressComAuthToken(authenticationUrl) || authenticationUrl.contains("wordpress.com/wp-login.php")) : (WPUrlUtils.safeToAddWordPressComAuthToken(authenticationUrl) && authenticationUrl.contains("wordpress.com/wp-login.php"))) || !TextUtils.isEmpty(token)) : ((ListenerUtil.mutListener.listen(27013) ? (WPUrlUtils.safeToAddWordPressComAuthToken(authenticationUrl) || authenticationUrl.contains("wordpress.com/wp-login.php")) : (WPUrlUtils.safeToAddWordPressComAuthToken(authenticationUrl) && authenticationUrl.contains("wordpress.com/wp-login.php"))) && !TextUtils.isEmpty(token)))) {
                    if (!ListenerUtil.mutListener.listen(27015)) {
                        postData += "&authorization=Bearer " + URLEncoder.encode(token, ENCODING_UTF8);
                    }
                }
            }
            return postData;
        } catch (UnsupportedEncodingException e) {
            if (!ListenerUtil.mutListener.listen(27012)) {
                AppLog.e(AppLog.T.UTILS, e);
            }
        }
        return "";
    }

    /**
     * Get the URL of the WordPress login page.
     *
     * @return URL of the login page.
     */
    public static String getSiteLoginUrl(SiteModel site) {
        String loginURL = site.getLoginUrl();
        if (!ListenerUtil.mutListener.listen(27018)) {
            // Try to guess the login URL if blogOptions is null (blog not added to the app), or WP version is < 3.6
            if (loginURL == null) {
                if (!ListenerUtil.mutListener.listen(27017)) {
                    if (site.getUrl() != null) {
                        return site.getUrl() + "/wp-login.php";
                    } else {
                        return site.getXmlRpcUrl().replace("xmlrpc.php", "wp-login.php");
                    }
                }
            }
        }
        return loginURL;
    }

    private boolean isActionableDirectUsage() {
        return mViewModel.isActionableDirectUsage();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!ListenerUtil.mutListener.listen(27019)) {
            super.onCreateOptionsMenu(menu);
        }
        MenuInflater inflater = getMenuInflater();
        if (!ListenerUtil.mutListener.listen(27020)) {
            inflater.inflate(R.menu.webview, menu);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        if (!ListenerUtil.mutListener.listen(27021)) {
            if (mWebView == null) {
                return false;
            }
        }
        int itemID = item.getItemId();
        if (!ListenerUtil.mutListener.listen(27026)) {
            if (itemID == android.R.id.home) {
                if (!ListenerUtil.mutListener.listen(27024)) {
                    mViewModel.track(Stat.WEBVIEW_DISMISSED);
                }
                if (!ListenerUtil.mutListener.listen(27025)) {
                    setResultIfNeeded();
                }
            } else if (itemID == R.id.menu_refresh) {
                if (!ListenerUtil.mutListener.listen(27022)) {
                    mViewModel.track(Stat.WEBVIEW_RELOAD_TAPPED);
                }
                if (!ListenerUtil.mutListener.listen(27023)) {
                    mWebView.reload();
                }
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    // to be explicit in case of future expansions on this.
    private void setResultIfNeeded() {
        if (!ListenerUtil.mutListener.listen(27028)) {
            if (getCallingActivity() != null) {
                if (!ListenerUtil.mutListener.listen(27027)) {
                    setResult(RESULT_OK);
                }
            }
        }
    }

    private void setResultIfNeededAndFinish() {
        if (!ListenerUtil.mutListener.listen(27029)) {
            setResultIfNeeded();
        }
        if (!ListenerUtil.mutListener.listen(27030)) {
            finish();
        }
    }

    @Override
    public void onBackPressed() {
        if (!ListenerUtil.mutListener.listen(27036)) {
            if (mWebView.canGoBack()) {
                if (!ListenerUtil.mutListener.listen(27034)) {
                    mWebView.goBack();
                }
                if (!ListenerUtil.mutListener.listen(27035)) {
                    refreshBackForwardNavButtons();
                }
            } else {
                if (!ListenerUtil.mutListener.listen(27031)) {
                    super.onBackPressed();
                }
                if (!ListenerUtil.mutListener.listen(27032)) {
                    mViewModel.track(Stat.WEBVIEW_DISMISSED);
                }
                if (!ListenerUtil.mutListener.listen(27033)) {
                    setResultIfNeeded();
                }
            }
        }
    }

    @Override
    public void onStart() {
        if (!ListenerUtil.mutListener.listen(27037)) {
            super.onStart();
        }
        if (!ListenerUtil.mutListener.listen(27038)) {
            mDispatcher.register(this);
        }
    }

    @Override
    public void onStop() {
        if (!ListenerUtil.mutListener.listen(27039)) {
            mDispatcher.unregister(this);
        }
        if (!ListenerUtil.mutListener.listen(27040)) {
            super.onStop();
        }
    }

    @SuppressWarnings("unused")
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onPrivateAtomicCookieFetched(OnPrivateAtomicCookieFetched event) {
        if (!ListenerUtil.mutListener.listen(27044)) {
            if (event.isError()) {
                if (!ListenerUtil.mutListener.listen(27042)) {
                    AppLog.e(AppLog.T.MAIN, "Failed to load private AT cookie. " + event.error.type + " - " + event.error.message);
                }
                if (!ListenerUtil.mutListener.listen(27043)) {
                    WPSnackbar.make(findViewById(R.id.webview_wrapper), R.string.media_accessing_failed, Snackbar.LENGTH_LONG).show();
                }
            } else {
                if (!ListenerUtil.mutListener.listen(27041)) {
                    CookieManager.getInstance().setCookie(mPrivateAtomicCookie.getDomain(), mPrivateAtomicCookie.getCookieContent());
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(27047)) {
            // if the dialog is not showing by the time cookie fetched it means that it was dismissed and content was loaded
            if (PrivateAtCookieRefreshProgressDialog.Companion.isShowing(getSupportFragmentManager())) {
                if (!ListenerUtil.mutListener.listen(27045)) {
                    loadWebContent();
                }
                if (!ListenerUtil.mutListener.listen(27046)) {
                    PrivateAtCookieRefreshProgressDialog.Companion.dismissIfNecessary(getSupportFragmentManager());
                }
            }
        }
    }

    @Override
    public void onCookieProgressDialogCancelled() {
        if (!ListenerUtil.mutListener.listen(27048)) {
            WPSnackbar.make(findViewById(R.id.webview_wrapper), R.string.media_accessing_failed, Snackbar.LENGTH_LONG).show();
        }
        if (!ListenerUtil.mutListener.listen(27049)) {
            loadWebContent();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (!ListenerUtil.mutListener.listen(27050)) {
            super.onActivityResult(requestCode, resultCode, data);
        }
        if (!ListenerUtil.mutListener.listen(27052)) {
            if (mWPWebChromeClientWithFileChooser != null) {
                if (!ListenerUtil.mutListener.listen(27051)) {
                    mWPWebChromeClientWithFileChooser.onActivityResult(requestCode, resultCode, data);
                }
            }
        }
    }

    @Override
    public void startActivityForFileChooserResult(Intent intent, int requestCode) {
        if (!ListenerUtil.mutListener.listen(27053)) {
            startActivityForResult(intent, requestCode);
        }
    }
}
