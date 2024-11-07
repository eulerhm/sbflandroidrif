package org.wordpress.android.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import org.wordpress.android.R;
import org.wordpress.android.WordPress;
import org.wordpress.android.analytics.AnalyticsTracker.Stat;
import org.wordpress.android.fluxc.Dispatcher;
import org.wordpress.android.fluxc.generated.SiteActionBuilder;
import org.wordpress.android.fluxc.model.SiteModel;
import org.wordpress.android.fluxc.store.AccountStore;
import org.wordpress.android.fluxc.store.SiteStore;
import org.wordpress.android.login.LoginMode;
import org.wordpress.android.ui.accounts.LoginActivity;
import org.wordpress.android.util.AppLog;
import org.wordpress.android.util.SiteUtils;
import org.wordpress.android.util.ToastUtils;
import org.wordpress.android.util.analytics.AnalyticsUtils;
import javax.inject.Inject;
import static org.wordpress.android.WordPress.SITE;
import static org.wordpress.android.ui.RequestCodes.JETPACK_LOGIN;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * An activity to handle result of Jetpack connection
 * <p>
 * wordpress://jetpack-connection?reason={error}
 * <p>
 * Redirects users to the stats activity if the jetpack connection was succesful
 */
public class JetpackConnectionResultActivity extends LocaleAwareActivity {

    private static final String ALREADY_CONNECTED = "already-connected";

    private static final String REASON_PARAM = "reason";

    private static final String SOURCE_PARAM = "source";

    private JetpackConnectionSource mSource;

    private String mReason;

    @Inject
    AccountStore mAccountStore;

    @Inject
    SiteStore mSiteStore;

    @Inject
    Dispatcher mDispatcher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(26367)) {
            super.onCreate(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(26368)) {
            ((WordPress) getApplication()).component().inject(this);
        }
        if (!ListenerUtil.mutListener.listen(26369)) {
            setContentView(R.layout.stats_loading_activity);
        }
        Toolbar toolbar = findViewById(R.id.toolbar_main);
        if (!ListenerUtil.mutListener.listen(26370)) {
            setSupportActionBar(toolbar);
        }
        ActionBar actionBar = getSupportActionBar();
        if (!ListenerUtil.mutListener.listen(26375)) {
            if (actionBar != null) {
                if (!ListenerUtil.mutListener.listen(26371)) {
                    actionBar.setElevation(0);
                }
                if (!ListenerUtil.mutListener.listen(26372)) {
                    actionBar.setTitle(R.string.stats);
                }
                if (!ListenerUtil.mutListener.listen(26373)) {
                    actionBar.setDisplayShowTitleEnabled(true);
                }
                if (!ListenerUtil.mutListener.listen(26374)) {
                    actionBar.setDisplayHomeAsUpEnabled(true);
                }
            }
        }
        String action = getIntent().getAction();
        Uri uri = getIntent().getData();
        String host = "";
        if (!ListenerUtil.mutListener.listen(26377)) {
            if (uri != null) {
                if (!ListenerUtil.mutListener.listen(26376)) {
                    host = uri.getHost();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(26379)) {
            if (Intent.ACTION_VIEW.equals(action)) {
                if (!ListenerUtil.mutListener.listen(26378)) {
                    AnalyticsUtils.trackWithDeepLinkData(Stat.DEEP_LINKED, action, host, uri);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(26388)) {
            // check if this intent is started via custom scheme link
            if (uri != null) {
                if (!ListenerUtil.mutListener.listen(26381)) {
                    // - one of the errors is "already-connected"
                    mReason = uri.getQueryParameter(REASON_PARAM);
                }
                if (!ListenerUtil.mutListener.listen(26382)) {
                    mSource = JetpackConnectionSource.fromString(uri.getQueryParameter(SOURCE_PARAM));
                }
                if (!ListenerUtil.mutListener.listen(26387)) {
                    if (mAccountStore.hasAccessToken()) {
                        if (!ListenerUtil.mutListener.listen(26385)) {
                            // if user is signed in wpcom show the stats or notifications right away
                            trackResult();
                        }
                        if (!ListenerUtil.mutListener.listen(26386)) {
                            finishAndGoBackToSource();
                        }
                    } else {
                        // An edgecase when the user is logged out in the app but logged in in webview
                        Intent loginIntent = new Intent(this, LoginActivity.class);
                        if (!ListenerUtil.mutListener.listen(26383)) {
                            LoginMode.JETPACK_STATS.putInto(loginIntent);
                        }
                        if (!ListenerUtil.mutListener.listen(26384)) {
                            this.startActivityForResult(loginIntent, JETPACK_LOGIN);
                        }
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(26380)) {
                    finish();
                }
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (!ListenerUtil.mutListener.listen(26389)) {
            super.onActivityResult(requestCode, resultCode, data);
        }
        if (!ListenerUtil.mutListener.listen(26393)) {
            if (requestCode == RequestCodes.JETPACK_LOGIN) {
                if (!ListenerUtil.mutListener.listen(26392)) {
                    if (resultCode == RESULT_OK) {
                        if (!ListenerUtil.mutListener.listen(26391)) {
                            trackResult();
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(26390)) {
                            finishAndGoBackToSource();
                        }
                    }
                }
            }
        }
    }

    @Override
    public void onBackPressed() {
        if (!ListenerUtil.mutListener.listen(26394)) {
            super.onBackPressed();
        }
        if (!ListenerUtil.mutListener.listen(26395)) {
            finishAndGoBackToSource();
        }
    }

    private void trackResult() {
        if (!ListenerUtil.mutListener.listen(26403)) {
            if (!TextUtils.isEmpty(mReason)) {
                if (!ListenerUtil.mutListener.listen(26402)) {
                    if (mReason.equals(ALREADY_CONNECTED)) {
                        if (!ListenerUtil.mutListener.listen(26400)) {
                            AppLog.d(AppLog.T.API, "Already connected to Jetpack.");
                        }
                        if (!ListenerUtil.mutListener.listen(26401)) {
                            ToastUtils.showToast(this, getString(R.string.jetpack_already_connected_toast));
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(26397)) {
                            AppLog.e(AppLog.T.API, "Could not connect to Jetpack, reason: " + mReason);
                        }
                        if (!ListenerUtil.mutListener.listen(26398)) {
                            JetpackConnectionUtils.trackFailureWithSource(mSource, mReason);
                        }
                        if (!ListenerUtil.mutListener.listen(26399)) {
                            ToastUtils.showToast(this, getString(R.string.jetpack_connection_failed_with_reason, mReason));
                        }
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(26396)) {
                    JetpackConnectionUtils.trackWithSource(Stat.SIGNED_INTO_JETPACK, mSource);
                }
            }
        }
    }

    private void finishAndGoBackToSource() {
        if (!ListenerUtil.mutListener.listen(26406)) {
            if (mSource == JetpackConnectionSource.STATS) {
                SiteModel site = (SiteModel) getIntent().getSerializableExtra(SITE);
                if (!ListenerUtil.mutListener.listen(26404)) {
                    mDispatcher.dispatch(SiteActionBuilder.newFetchSitesAction(SiteUtils.getFetchSitesPayload()));
                }
                if (!ListenerUtil.mutListener.listen(26405)) {
                    ActivityLauncher.viewBlogStatsAfterJetpackSetup(this, site);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(26407)) {
            finish();
        }
    }
}
