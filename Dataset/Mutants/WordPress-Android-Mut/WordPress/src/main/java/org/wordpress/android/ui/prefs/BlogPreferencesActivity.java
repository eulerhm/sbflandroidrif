package org.wordpress.android.ui.prefs;

import android.app.FragmentManager;
import android.os.Bundle;
import android.view.MenuItem;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import org.apache.commons.text.StringEscapeUtils;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.wordpress.android.R;
import org.wordpress.android.WordPress;
import org.wordpress.android.fluxc.Dispatcher;
import org.wordpress.android.fluxc.model.SiteModel;
import org.wordpress.android.fluxc.store.AccountStore;
import org.wordpress.android.fluxc.store.SiteStore;
import org.wordpress.android.fluxc.store.SiteStore.OnSiteDeleted;
import org.wordpress.android.fluxc.store.SiteStore.OnSiteRemoved;
import org.wordpress.android.networking.ConnectionChangeReceiver;
import org.wordpress.android.ui.LocaleAwareActivity;
import org.wordpress.android.util.SiteUtils;
import org.wordpress.android.util.ToastUtils;
import org.wordpress.android.util.ToastUtils.Duration;
import javax.inject.Inject;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Activity for configuring blog specific settings.
 */
public class BlogPreferencesActivity extends LocaleAwareActivity {

    private static final String KEY_SETTINGS_FRAGMENT = "settings-fragment";

    private SiteModel mSite;

    @Inject
    AccountStore mAccountStore;

    @Inject
    SiteStore mSiteStore;

    @Inject
    Dispatcher mDispatcher;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(14523)) {
            super.onCreate(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(14524)) {
            ((WordPress) getApplication()).component().inject(this);
        }
        if (!ListenerUtil.mutListener.listen(14525)) {
            setContentView(R.layout.site_settings_activity);
        }
        if (!ListenerUtil.mutListener.listen(14528)) {
            if (savedInstanceState == null) {
                if (!ListenerUtil.mutListener.listen(14527)) {
                    mSite = (SiteModel) getIntent().getSerializableExtra(WordPress.SITE);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(14526)) {
                    mSite = (SiteModel) savedInstanceState.getSerializable(WordPress.SITE);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(14531)) {
            if (mSite == null) {
                if (!ListenerUtil.mutListener.listen(14529)) {
                    ToastUtils.showToast(this, R.string.blog_not_found, ToastUtils.Duration.SHORT);
                }
                if (!ListenerUtil.mutListener.listen(14530)) {
                    finish();
                }
                return;
            }
        }
        Toolbar toolbar = findViewById(R.id.toolbar_main);
        if (!ListenerUtil.mutListener.listen(14532)) {
            setSupportActionBar(toolbar);
        }
        ActionBar actionBar = getSupportActionBar();
        if (!ListenerUtil.mutListener.listen(14536)) {
            if (actionBar != null) {
                if (!ListenerUtil.mutListener.listen(14533)) {
                    actionBar.setHomeButtonEnabled(true);
                }
                if (!ListenerUtil.mutListener.listen(14534)) {
                    actionBar.setDisplayHomeAsUpEnabled(true);
                }
                if (!ListenerUtil.mutListener.listen(14535)) {
                    actionBar.setTitle(StringEscapeUtils.unescapeHtml4(SiteUtils.getSiteNameOrHomeURL(mSite)));
                }
            }
        }
        // See https://developer.android.com/reference/android/app/Fragment
        FragmentManager fragmentManager = getFragmentManager();
        android.app.Fragment siteSettingsFragment = fragmentManager.findFragmentByTag(KEY_SETTINGS_FRAGMENT);
        if (!ListenerUtil.mutListener.listen(14540)) {
            if (siteSettingsFragment == null) {
                if (!ListenerUtil.mutListener.listen(14537)) {
                    siteSettingsFragment = new SiteSettingsFragment();
                }
                if (!ListenerUtil.mutListener.listen(14538)) {
                    siteSettingsFragment.setArguments(getIntent().getExtras());
                }
                if (!ListenerUtil.mutListener.listen(14539)) {
                    fragmentManager.beginTransaction().replace(R.id.fragment_container, siteSettingsFragment, KEY_SETTINGS_FRAGMENT).commit();
                }
            }
        }
    }

    @Override
    protected void onStart() {
        if (!ListenerUtil.mutListener.listen(14541)) {
            super.onStart();
        }
        if (!ListenerUtil.mutListener.listen(14542)) {
            EventBus.getDefault().register(this);
        }
        if (!ListenerUtil.mutListener.listen(14543)) {
            mDispatcher.register(this);
        }
    }

    @Override
    protected void onStop() {
        if (!ListenerUtil.mutListener.listen(14544)) {
            mDispatcher.unregister(this);
        }
        if (!ListenerUtil.mutListener.listen(14545)) {
            EventBus.getDefault().unregister(this);
        }
        if (!ListenerUtil.mutListener.listen(14546)) {
            super.onStop();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        if (!ListenerUtil.mutListener.listen(14547)) {
            super.onSaveInstanceState(outState);
        }
        if (!ListenerUtil.mutListener.listen(14548)) {
            outState.putSerializable(WordPress.SITE, mSite);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemID = item.getItemId();
        if (!ListenerUtil.mutListener.listen(14550)) {
            if (itemID == android.R.id.home) {
                if (!ListenerUtil.mutListener.listen(14549)) {
                    finish();
                }
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("unused")
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(ConnectionChangeReceiver.ConnectionChangeEvent event) {
        SiteSettingsFragment siteSettingsFragment = getSettingsFragment();
        if (!ListenerUtil.mutListener.listen(14554)) {
            if (siteSettingsFragment != null) {
                if (!ListenerUtil.mutListener.listen(14552)) {
                    if (!event.isConnected()) {
                        if (!ListenerUtil.mutListener.listen(14551)) {
                            ToastUtils.showToast(this, getString(R.string.site_settings_disconnected_toast), Duration.LONG);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(14553)) {
                    siteSettingsFragment.setEditingEnabled(event.isConnected());
                }
            }
        }
    }

    @SuppressWarnings("unused")
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onSiteRemoved(OnSiteRemoved event) {
        if (!ListenerUtil.mutListener.listen(14557)) {
            if (!event.isError()) {
                if (!ListenerUtil.mutListener.listen(14555)) {
                    setResult(SiteSettingsFragment.RESULT_BLOG_REMOVED);
                }
                if (!ListenerUtil.mutListener.listen(14556)) {
                    finish();
                }
            }
        }
    }

    @SuppressWarnings("unused")
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onSiteDeleted(OnSiteDeleted event) {
        SiteSettingsFragment siteSettingsFragment = getSettingsFragment();
        if (!ListenerUtil.mutListener.listen(14563)) {
            if (siteSettingsFragment != null) {
                if (!ListenerUtil.mutListener.listen(14559)) {
                    if (event.isError()) {
                        if (!ListenerUtil.mutListener.listen(14558)) {
                            siteSettingsFragment.handleDeleteSiteError(event.error);
                        }
                        return;
                    }
                }
                if (!ListenerUtil.mutListener.listen(14560)) {
                    siteSettingsFragment.handleSiteDeleted();
                }
                if (!ListenerUtil.mutListener.listen(14561)) {
                    setResult(SiteSettingsFragment.RESULT_BLOG_REMOVED);
                }
                if (!ListenerUtil.mutListener.listen(14562)) {
                    finish();
                }
            }
        }
    }

    private SiteSettingsFragment getSettingsFragment() {
        FragmentManager fragmentManager = getFragmentManager();
        return (SiteSettingsFragment) fragmentManager.findFragmentByTag(KEY_SETTINGS_FRAGMENT);
    }
}
