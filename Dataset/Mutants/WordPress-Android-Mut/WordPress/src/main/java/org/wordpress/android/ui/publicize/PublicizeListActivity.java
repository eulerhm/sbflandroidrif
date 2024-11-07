package org.wordpress.android.ui.publicize;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.MenuItem;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.jetbrains.annotations.NotNull;
import org.wordpress.android.R;
import org.wordpress.android.WordPress;
import org.wordpress.android.analytics.AnalyticsTracker.Stat;
import org.wordpress.android.datasets.PublicizeTable;
import org.wordpress.android.fluxc.model.SiteModel;
import org.wordpress.android.fluxc.store.SiteStore;
import org.wordpress.android.models.PublicizeConnection;
import org.wordpress.android.models.PublicizeService;
import org.wordpress.android.ui.ActivityLauncher;
import org.wordpress.android.ui.LocaleAwareActivity;
import org.wordpress.android.ui.ScrollableViewInitializedListener;
import org.wordpress.android.ui.publicize.PublicizeConstants.ConnectAction;
import org.wordpress.android.ui.publicize.adapters.PublicizeServiceAdapter;
import org.wordpress.android.ui.publicize.services.PublicizeUpdateService;
import org.wordpress.android.util.extensions.AppBarLayoutExtensionsKt;
import org.wordpress.android.util.SiteUtils;
import org.wordpress.android.util.ToastUtils;
import org.wordpress.android.util.analytics.AnalyticsUtils;
import java.util.HashMap;
import java.util.Map;
import javax.inject.Inject;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class PublicizeListActivity extends LocaleAwareActivity implements PublicizeActions.OnPublicizeActionListener, PublicizeServiceAdapter.OnServiceClickListener, PublicizeListFragment.PublicizeButtonPrefsListener, ScrollableViewInitializedListener {

    private static final String WPCOM_CONNECTIONS_URL = "https://wordpress.com/marketing/connections/";

    private SiteModel mSite;

    private ProgressDialog mProgressDialog;

    private AppBarLayout mAppBarLayout;

    @Inject
    SiteStore mSiteStore;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(17593)) {
            super.onCreate(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(17594)) {
            ((WordPress) getApplication()).component().inject(this);
        }
        if (!ListenerUtil.mutListener.listen(17595)) {
            setContentView(R.layout.publicize_list_activity);
        }
        Toolbar toolbar = findViewById(R.id.toolbar_main);
        if (!ListenerUtil.mutListener.listen(17596)) {
            setSupportActionBar(toolbar);
        }
        ActionBar actionBar = getSupportActionBar();
        if (!ListenerUtil.mutListener.listen(17599)) {
            if (actionBar != null) {
                if (!ListenerUtil.mutListener.listen(17597)) {
                    actionBar.setDisplayShowTitleEnabled(true);
                }
                if (!ListenerUtil.mutListener.listen(17598)) {
                    actionBar.setDisplayHomeAsUpEnabled(true);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(17600)) {
            mAppBarLayout = findViewById(R.id.appbar_main);
        }
        if (!ListenerUtil.mutListener.listen(17609)) {
            if (savedInstanceState == null) {
                if (!ListenerUtil.mutListener.listen(17602)) {
                    mSite = (SiteModel) getIntent().getSerializableExtra(WordPress.SITE);
                }
                if (!ListenerUtil.mutListener.listen(17603)) {
                    PublicizeTable.createTables(WordPress.wpDB.getDatabase());
                }
                if (!ListenerUtil.mutListener.listen(17604)) {
                    showListFragment();
                }
                if (!ListenerUtil.mutListener.listen(17607)) {
                    if (mSite == null) {
                        if (!ListenerUtil.mutListener.listen(17605)) {
                            ToastUtils.showToast(this, R.string.blog_not_found, ToastUtils.Duration.SHORT);
                        }
                        if (!ListenerUtil.mutListener.listen(17606)) {
                            finish();
                        }
                        return;
                    }
                }
                if (!ListenerUtil.mutListener.listen(17608)) {
                    PublicizeUpdateService.updateConnectionsForSite(this, mSite);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(17601)) {
                    mSite = (SiteModel) savedInstanceState.getSerializable(WordPress.SITE);
                }
            }
        }
    }

    @Override
    protected void onSaveInstanceState(@NotNull Bundle outState) {
        if (!ListenerUtil.mutListener.listen(17610)) {
            super.onSaveInstanceState(outState);
        }
        if (!ListenerUtil.mutListener.listen(17611)) {
            outState.putSerializable(WordPress.SITE, mSite);
        }
    }

    @Override
    protected void onPause() {
        if (!ListenerUtil.mutListener.listen(17612)) {
            super.onPause();
        }
        if (!ListenerUtil.mutListener.listen(17613)) {
            EventBus.getDefault().unregister(this);
        }
    }

    @Override
    protected void onResume() {
        if (!ListenerUtil.mutListener.listen(17614)) {
            super.onResume();
        }
        if (!ListenerUtil.mutListener.listen(17615)) {
            EventBus.getDefault().register(this);
        }
    }

    private void showListFragment() {
        if (!ListenerUtil.mutListener.listen(17616)) {
            if (isFinishing()) {
                return;
            }
        }
        String tag = getString(R.string.fragment_tag_publicize_list);
        Fragment fragment = PublicizeListFragment.newInstance(mSite);
        if (!ListenerUtil.mutListener.listen(17617)) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment, tag).commit();
        }
    }

    private PublicizeListFragment getListFragment() {
        String tag = getString(R.string.fragment_tag_publicize_list);
        Fragment fragment = getSupportFragmentManager().findFragmentByTag(tag);
        if (fragment != null) {
            return (PublicizeListFragment) fragment;
        } else {
            return null;
        }
    }

    private void reloadListFragment() {
        PublicizeListFragment listFragment = getListFragment();
        if (!ListenerUtil.mutListener.listen(17619)) {
            if (listFragment != null) {
                if (!ListenerUtil.mutListener.listen(17618)) {
                    listFragment.reload();
                }
            }
        }
    }

    /*
     * close all but the first (list) fragment
     */
    private void returnToListFragment() {
        if (!ListenerUtil.mutListener.listen(17620)) {
            if (getSupportFragmentManager().getBackStackEntryCount() == 0) {
                return;
            }
        }
        String tag = getString(R.string.fragment_tag_publicize_detail);
        if (!ListenerUtil.mutListener.listen(17621)) {
            getSupportFragmentManager().popBackStack(tag, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }
    }

    private void showDetailFragment(PublicizeService service) {
        if (!ListenerUtil.mutListener.listen(17622)) {
            if (isFinishing()) {
                return;
            }
        }
        String tag = getString(R.string.fragment_tag_publicize_detail);
        Fragment detailFragment = PublicizeDetailFragment.newInstance(mSite, service);
        if (!ListenerUtil.mutListener.listen(17623)) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, detailFragment, tag).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).addToBackStack(tag).commit();
        }
    }

    private PublicizeDetailFragment getDetailFragment() {
        String tag = getString(R.string.fragment_tag_publicize_detail);
        Fragment fragment = getSupportFragmentManager().findFragmentByTag(tag);
        if (fragment != null) {
            return (PublicizeDetailFragment) fragment;
        } else {
            return null;
        }
    }

    private void reloadDetailFragment() {
        PublicizeDetailFragment detailFragment = getDetailFragment();
        if (!ListenerUtil.mutListener.listen(17625)) {
            if (detailFragment != null) {
                if (!ListenerUtil.mutListener.listen(17624)) {
                    detailFragment.loadData();
                }
            }
        }
    }

    private void showWebViewFragment(PublicizeService service, PublicizeConnection publicizeConnection) {
        if (!ListenerUtil.mutListener.listen(17626)) {
            if (isFinishing()) {
                return;
            }
        }
        String tag = getString(R.string.fragment_tag_publicize_webview);
        Fragment webViewFragment = PublicizeWebViewFragment.newInstance(mSite, service, publicizeConnection);
        if (!ListenerUtil.mutListener.listen(17627)) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, webViewFragment, tag).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).addToBackStack(tag).commit();
        }
    }

    private void closeWebViewFragment() {
        String tag = getString(R.string.fragment_tag_publicize_webview);
        if (!ListenerUtil.mutListener.listen(17628)) {
            getSupportFragmentManager().popBackStack(tag, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        int itemId = item.getItemId();
        if (!ListenerUtil.mutListener.listen(17630)) {
            if (itemId == android.R.id.home) {
                if (!ListenerUtil.mutListener.listen(17629)) {
                    onBackPressed();
                }
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (!ListenerUtil.mutListener.listen(17638)) {
            if ((ListenerUtil.mutListener.listen(17635) ? (getSupportFragmentManager().getBackStackEntryCount() >= 0) : (ListenerUtil.mutListener.listen(17634) ? (getSupportFragmentManager().getBackStackEntryCount() <= 0) : (ListenerUtil.mutListener.listen(17633) ? (getSupportFragmentManager().getBackStackEntryCount() < 0) : (ListenerUtil.mutListener.listen(17632) ? (getSupportFragmentManager().getBackStackEntryCount() != 0) : (ListenerUtil.mutListener.listen(17631) ? (getSupportFragmentManager().getBackStackEntryCount() == 0) : (getSupportFragmentManager().getBackStackEntryCount() > 0))))))) {
                if (!ListenerUtil.mutListener.listen(17637)) {
                    getSupportFragmentManager().popBackStack();
                }
            } else {
                if (!ListenerUtil.mutListener.listen(17636)) {
                    super.onBackPressed();
                }
            }
        }
    }

    /*
     * user tapped a service in the list fragment
     */
    @Override
    public void onServiceClicked(PublicizeService service) {
        if (!ListenerUtil.mutListener.listen(17639)) {
            showDetailFragment(service);
        }
    }

    /*
     * user requested to connect to a service from the detail fragment
     */
    @Override
    public void onRequestConnect(PublicizeService service) {
        if (!ListenerUtil.mutListener.listen(17642)) {
            if (isFacebook(service)) {
                if (!ListenerUtil.mutListener.listen(17641)) {
                    showFacebookWarning();
                }
            } else {
                if (!ListenerUtil.mutListener.listen(17640)) {
                    showWebViewFragment(service, null);
                }
            }
        }
    }

    /*
     * user requested to reconnect a broken publicizeConnection from the detail fragment
     */
    @Override
    public void onRequestReconnect(PublicizeService service, PublicizeConnection publicizeConnection) {
        if (!ListenerUtil.mutListener.listen(17646)) {
            if (isFacebook(service)) {
                if (!ListenerUtil.mutListener.listen(17645)) {
                    showFacebookWarning();
                }
            } else {
                if (!ListenerUtil.mutListener.listen(17643)) {
                    PublicizeActions.reconnect(publicizeConnection);
                }
                if (!ListenerUtil.mutListener.listen(17644)) {
                    showWebViewFragment(service, null);
                }
            }
        }
    }

    /*
     * user requested to disconnect a service publicizeConnection from the detail fragment
     */
    @Override
    public void onRequestDisconnect(PublicizeConnection publicizeConnection) {
        if (!ListenerUtil.mutListener.listen(17647)) {
            confirmDisconnect(publicizeConnection);
        }
    }

    private boolean isFacebook(PublicizeService service) {
        return service.getId().equals(PublicizeConstants.FACEBOOK_ID);
    }

    private String getConnectionsUrl(SiteModel site) {
        return WPCOM_CONNECTIONS_URL + SiteUtils.getHomeURLOrHostName(site);
    }

    /*
     * As of Oct 5, 2021 Facebook has deprecated support for authentication on embedded browsers, so Publicize
     * connections can't be established through web views anymore (ref: pbArwn-3uU-p2).
     * This method shows a temporary warning message to the user instead.
     */
    private void showFacebookWarning() {
        if (!ListenerUtil.mutListener.listen(17648)) {
            new MaterialAlertDialogBuilder(this).setMessage(R.string.sharing_facebook_warning_message).setPositiveButton(R.string.sharing_facebook_warning_positive_button, (dialog, id) -> ActivityLauncher.openUrlExternal(this, getConnectionsUrl(mSite))).setNegativeButton(R.string.cancel, null).setCancelable(true).create().show();
        }
    }

    private void confirmDisconnect(final PublicizeConnection publicizeConnection) {
        AlertDialog.Builder builder = new MaterialAlertDialogBuilder(this);
        if (!ListenerUtil.mutListener.listen(17649)) {
            builder.setMessage(String.format(getString(R.string.dlg_confirm_publicize_disconnect), publicizeConnection.getLabel()));
        }
        if (!ListenerUtil.mutListener.listen(17650)) {
            builder.setTitle(R.string.share_btn_disconnect);
        }
        if (!ListenerUtil.mutListener.listen(17651)) {
            builder.setCancelable(true);
        }
        if (!ListenerUtil.mutListener.listen(17652)) {
            builder.setPositiveButton(R.string.share_btn_disconnect, (dialog, id) -> {
                PublicizeActions.disconnect(publicizeConnection);
                // detail fragment would give them the ability to reconnect
                if (publicizeConnection.getService().equals(PublicizeConstants.GOOGLE_PLUS_ID)) {
                    returnToListFragment();
                } else {
                    reloadDetailFragment();
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(17653)) {
            builder.setNegativeButton(R.string.cancel, null);
        }
        AlertDialog alert = builder.create();
        if (!ListenerUtil.mutListener.listen(17654)) {
            alert.show();
        }
    }

    /*
     * list of available services or list of connections has changed
     */
    @SuppressWarnings("unused")
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(PublicizeEvents.ConnectionsChanged event) {
        if (!ListenerUtil.mutListener.listen(17655)) {
            reloadListFragment();
        }
    }

    /*
     * request from fragment to connect/disconnect/reconnect completed
     */
    @SuppressWarnings("unused")
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(PublicizeEvents.ActionCompleted event) {
        if (!ListenerUtil.mutListener.listen(17656)) {
            if (isFinishing()) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(17662)) {
            if (event.getAction() != ConnectAction.RECONNECT) {
                if (!ListenerUtil.mutListener.listen(17657)) {
                    closeWebViewFragment();
                }
                if (!ListenerUtil.mutListener.listen(17660)) {
                    if ((ListenerUtil.mutListener.listen(17658) ? (mProgressDialog != null || mProgressDialog.isShowing()) : (mProgressDialog != null && mProgressDialog.isShowing()))) {
                        if (!ListenerUtil.mutListener.listen(17659)) {
                            mProgressDialog.dismiss();
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(17661)) {
                    reloadDetailFragment();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(17670)) {
            if (event.didSucceed()) {
                Map<String, Object> analyticsProperties = new HashMap<>();
                if (!ListenerUtil.mutListener.listen(17666)) {
                    analyticsProperties.put("service", event.getService());
                }
                if (!ListenerUtil.mutListener.listen(17669)) {
                    if (event.getAction() == ConnectAction.CONNECT) {
                        if (!ListenerUtil.mutListener.listen(17668)) {
                            AnalyticsUtils.trackWithSiteDetails(Stat.PUBLICIZE_SERVICE_CONNECTED, mSite, analyticsProperties);
                        }
                    } else if (event.getAction() == ConnectAction.DISCONNECT) {
                        if (!ListenerUtil.mutListener.listen(17667)) {
                            AnalyticsUtils.trackWithSiteDetails(Stat.PUBLICIZE_SERVICE_DISCONNECTED, mSite, analyticsProperties);
                        }
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(17665)) {
                    if (event.getReasonResId() != null) {
                        DialogFragment fragment = PublicizeErrorDialogFragment.newInstance(event.getReasonResId());
                        if (!ListenerUtil.mutListener.listen(17664)) {
                            fragment.show(getSupportFragmentManager(), PublicizeErrorDialogFragment.TAG);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(17663)) {
                            ToastUtils.showToast(this, R.string.error_generic);
                        }
                    }
                }
            }
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(PublicizeEvents.ActionAccountChosen event) {
        if (!ListenerUtil.mutListener.listen(17671)) {
            if (isFinishing()) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(17672)) {
            PublicizeActions.connectStepTwo(event.getSiteId(), event.getKeychainId(), event.getService(), event.getExternalUserId());
        }
        if (!ListenerUtil.mutListener.listen(17673)) {
            mProgressDialog = new ProgressDialog(this);
        }
        if (!ListenerUtil.mutListener.listen(17674)) {
            mProgressDialog.setMessage(getString(R.string.connecting_account));
        }
        if (!ListenerUtil.mutListener.listen(17675)) {
            mProgressDialog.show();
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(PublicizeEvents.ActionRequestChooseAccount event) {
        if (!ListenerUtil.mutListener.listen(17676)) {
            if (isFinishing()) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(17677)) {
            closeWebViewFragment();
        }
        SiteModel site = mSiteStore.getSiteBySiteId(event.getSiteId());
        if (!ListenerUtil.mutListener.listen(17679)) {
            if (site == null) {
                if (!ListenerUtil.mutListener.listen(17678)) {
                    ToastUtils.showToast(this, R.string.blog_not_found, ToastUtils.Duration.SHORT);
                }
                return;
            }
        }
        PublicizeAccountChooserDialogFragment dialogFragment = new PublicizeAccountChooserDialogFragment();
        Bundle args = new Bundle();
        if (!ListenerUtil.mutListener.listen(17680)) {
            args.putString(PublicizeConstants.ARG_CONNECTION_ARRAY_JSON, event.getJSONObject().toString());
        }
        if (!ListenerUtil.mutListener.listen(17681)) {
            args.putSerializable(WordPress.SITE, site);
        }
        if (!ListenerUtil.mutListener.listen(17682)) {
            args.putString(PublicizeConstants.ARG_SERVICE_ID, event.getServiceId());
        }
        if (!ListenerUtil.mutListener.listen(17683)) {
            dialogFragment.setArguments(args);
        }
        if (!ListenerUtil.mutListener.listen(17684)) {
            dialogFragment.show(getSupportFragmentManager(), PublicizeAccountChooserDialogFragment.TAG);
        }
    }

    @Override
    public void onButtonPrefsClicked() {
        if (!ListenerUtil.mutListener.listen(17685)) {
            AnalyticsUtils.trackWithSiteDetails(Stat.OPENED_SHARING_BUTTON_MANAGEMENT, mSite);
        }
        Fragment fragment = PublicizeButtonPrefsFragment.newInstance(mSite);
        if (!ListenerUtil.mutListener.listen(17686)) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).addToBackStack(null).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).commit();
        }
    }

    @Override
    public void onScrollableViewInitialized(int containerId) {
        if (!ListenerUtil.mutListener.listen(17687)) {
            AppBarLayoutExtensionsKt.setLiftOnScrollTargetViewIdAndRequestLayout(mAppBarLayout, containerId);
        }
    }
}
