package org.wordpress.android.ui.themes;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.jetbrains.annotations.NotNull;
import org.wordpress.android.R;
import org.wordpress.android.WordPress;
import org.wordpress.android.analytics.AnalyticsTracker.Stat;
import org.wordpress.android.fluxc.Dispatcher;
import org.wordpress.android.fluxc.action.ThemeAction;
import org.wordpress.android.fluxc.generated.ThemeActionBuilder;
import org.wordpress.android.fluxc.model.SiteModel;
import org.wordpress.android.fluxc.model.ThemeModel;
import org.wordpress.android.fluxc.store.ThemeStore;
import org.wordpress.android.fluxc.store.ThemeStore.OnCurrentThemeFetched;
import org.wordpress.android.fluxc.store.ThemeStore.OnSiteThemesChanged;
import org.wordpress.android.fluxc.store.ThemeStore.OnThemeActivated;
import org.wordpress.android.fluxc.store.ThemeStore.OnThemeInstalled;
import org.wordpress.android.fluxc.store.ThemeStore.OnWpComThemesChanged;
import org.wordpress.android.fluxc.store.ThemeStore.SiteThemePayload;
import org.wordpress.android.ui.ActivityId;
import org.wordpress.android.ui.LocaleAwareActivity;
import org.wordpress.android.ui.prefs.AppPrefs;
import org.wordpress.android.ui.themes.ThemeBrowserFragment.ThemeBrowserFragmentCallback;
import org.wordpress.android.util.AppLog;
import org.wordpress.android.util.AppLog.T;
import org.wordpress.android.util.ToastUtils;
import org.wordpress.android.util.analytics.AnalyticsUtils;
import java.util.HashMap;
import java.util.Map;
import javax.inject.Inject;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class ThemeBrowserActivity extends LocaleAwareActivity implements ThemeBrowserFragmentCallback {

    public static final int ACTIVATE_THEME = 1;

    public static final String THEME_ID = "theme_id";

    // refresh WP.com themes every 3 days
    private static final long WP_COM_THEMES_SYNC_TIMEOUT = 1000 * 60 * 60 * 24 * 3;

    private ThemeBrowserFragment mThemeBrowserFragment;

    private ThemeModel mCurrentTheme;

    private boolean mIsFetchingInstalledThemes;

    private SiteModel mSite;

    @Inject
    ThemeStore mThemeStore;

    @Inject
    Dispatcher mDispatcher;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(23240)) {
            super.onCreate(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(23241)) {
            ((WordPress) getApplication()).component().inject(this);
        }
        if (!ListenerUtil.mutListener.listen(23242)) {
            mDispatcher.register(this);
        }
        if (!ListenerUtil.mutListener.listen(23245)) {
            if (savedInstanceState == null) {
                if (!ListenerUtil.mutListener.listen(23244)) {
                    mSite = (SiteModel) getIntent().getSerializableExtra(WordPress.SITE);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(23243)) {
                    mSite = (SiteModel) savedInstanceState.getSerializable(WordPress.SITE);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(23248)) {
            if (mSite == null) {
                if (!ListenerUtil.mutListener.listen(23246)) {
                    ToastUtils.showToast(this, R.string.blog_not_found, ToastUtils.Duration.SHORT);
                }
                if (!ListenerUtil.mutListener.listen(23247)) {
                    finish();
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(23249)) {
            setContentView(R.layout.theme_browser_activity);
        }
        if (!ListenerUtil.mutListener.listen(23254)) {
            if (savedInstanceState == null) {
                if (!ListenerUtil.mutListener.listen(23251)) {
                    addBrowserFragment();
                }
                if (!ListenerUtil.mutListener.listen(23252)) {
                    fetchInstalledThemesIfJetpackSite();
                }
                if (!ListenerUtil.mutListener.listen(23253)) {
                    fetchWpComThemesIfSyncTimedOut(false);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(23250)) {
                    mThemeBrowserFragment = (ThemeBrowserFragment) getSupportFragmentManager().findFragmentByTag(ThemeBrowserFragment.TAG);
                }
            }
        }
        Toolbar toolbar = findViewById(R.id.toolbar_main);
        if (!ListenerUtil.mutListener.listen(23255)) {
            setSupportActionBar(toolbar);
        }
        ActionBar actionBar = getSupportActionBar();
        if (!ListenerUtil.mutListener.listen(23258)) {
            if (actionBar != null) {
                if (!ListenerUtil.mutListener.listen(23256)) {
                    actionBar.setDisplayShowTitleEnabled(true);
                }
                if (!ListenerUtil.mutListener.listen(23257)) {
                    actionBar.setDisplayHomeAsUpEnabled(true);
                }
            }
        }
    }

    @Override
    protected void onResume() {
        if (!ListenerUtil.mutListener.listen(23259)) {
            super.onResume();
        }
        if (!ListenerUtil.mutListener.listen(23260)) {
            ActivityId.trackLastActivity(ActivityId.THEMES);
        }
        if (!ListenerUtil.mutListener.listen(23261)) {
            fetchCurrentTheme();
        }
    }

    @Override
    protected void onSaveInstanceState(@NotNull Bundle outState) {
        if (!ListenerUtil.mutListener.listen(23262)) {
            super.onSaveInstanceState(outState);
        }
        if (!ListenerUtil.mutListener.listen(23263)) {
            outState.putSerializable(WordPress.SITE, mSite);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int i = item.getItemId();
        if (!ListenerUtil.mutListener.listen(23265)) {
            if (i == android.R.id.home) {
                if (!ListenerUtil.mutListener.listen(23264)) {
                    onBackPressed();
                }
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        FragmentManager fm = getSupportFragmentManager();
        if (!ListenerUtil.mutListener.listen(23273)) {
            if ((ListenerUtil.mutListener.listen(23270) ? (fm.getBackStackEntryCount() >= 0) : (ListenerUtil.mutListener.listen(23269) ? (fm.getBackStackEntryCount() <= 0) : (ListenerUtil.mutListener.listen(23268) ? (fm.getBackStackEntryCount() < 0) : (ListenerUtil.mutListener.listen(23267) ? (fm.getBackStackEntryCount() != 0) : (ListenerUtil.mutListener.listen(23266) ? (fm.getBackStackEntryCount() == 0) : (fm.getBackStackEntryCount() > 0))))))) {
                if (!ListenerUtil.mutListener.listen(23272)) {
                    fm.popBackStack();
                }
            } else {
                if (!ListenerUtil.mutListener.listen(23271)) {
                    super.onBackPressed();
                }
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (!ListenerUtil.mutListener.listen(23274)) {
            super.onActivityResult(requestCode, resultCode, data);
        }
        if (!ListenerUtil.mutListener.listen(23284)) {
            if ((ListenerUtil.mutListener.listen(23281) ? ((ListenerUtil.mutListener.listen(23280) ? ((ListenerUtil.mutListener.listen(23279) ? (requestCode >= ACTIVATE_THEME) : (ListenerUtil.mutListener.listen(23278) ? (requestCode <= ACTIVATE_THEME) : (ListenerUtil.mutListener.listen(23277) ? (requestCode > ACTIVATE_THEME) : (ListenerUtil.mutListener.listen(23276) ? (requestCode < ACTIVATE_THEME) : (ListenerUtil.mutListener.listen(23275) ? (requestCode != ACTIVATE_THEME) : (requestCode == ACTIVATE_THEME)))))) || resultCode == RESULT_OK) : ((ListenerUtil.mutListener.listen(23279) ? (requestCode >= ACTIVATE_THEME) : (ListenerUtil.mutListener.listen(23278) ? (requestCode <= ACTIVATE_THEME) : (ListenerUtil.mutListener.listen(23277) ? (requestCode > ACTIVATE_THEME) : (ListenerUtil.mutListener.listen(23276) ? (requestCode < ACTIVATE_THEME) : (ListenerUtil.mutListener.listen(23275) ? (requestCode != ACTIVATE_THEME) : (requestCode == ACTIVATE_THEME)))))) && resultCode == RESULT_OK)) || data != null) : ((ListenerUtil.mutListener.listen(23280) ? ((ListenerUtil.mutListener.listen(23279) ? (requestCode >= ACTIVATE_THEME) : (ListenerUtil.mutListener.listen(23278) ? (requestCode <= ACTIVATE_THEME) : (ListenerUtil.mutListener.listen(23277) ? (requestCode > ACTIVATE_THEME) : (ListenerUtil.mutListener.listen(23276) ? (requestCode < ACTIVATE_THEME) : (ListenerUtil.mutListener.listen(23275) ? (requestCode != ACTIVATE_THEME) : (requestCode == ACTIVATE_THEME)))))) || resultCode == RESULT_OK) : ((ListenerUtil.mutListener.listen(23279) ? (requestCode >= ACTIVATE_THEME) : (ListenerUtil.mutListener.listen(23278) ? (requestCode <= ACTIVATE_THEME) : (ListenerUtil.mutListener.listen(23277) ? (requestCode > ACTIVATE_THEME) : (ListenerUtil.mutListener.listen(23276) ? (requestCode < ACTIVATE_THEME) : (ListenerUtil.mutListener.listen(23275) ? (requestCode != ACTIVATE_THEME) : (requestCode == ACTIVATE_THEME)))))) && resultCode == RESULT_OK)) && data != null))) {
                String themeId = data.getStringExtra(THEME_ID);
                if (!ListenerUtil.mutListener.listen(23283)) {
                    if (!TextUtils.isEmpty(themeId)) {
                        if (!ListenerUtil.mutListener.listen(23282)) {
                            activateTheme(themeId);
                        }
                    }
                }
            }
        }
    }

    @Override
    public void onDestroy() {
        if (!ListenerUtil.mutListener.listen(23285)) {
            super.onDestroy();
        }
        if (!ListenerUtil.mutListener.listen(23286)) {
            mDispatcher.unregister(this);
        }
    }

    @Override
    public void onActivateSelected(String themeId) {
        if (!ListenerUtil.mutListener.listen(23287)) {
            activateTheme(themeId);
        }
    }

    @Override
    public void onTryAndCustomizeSelected(String themeId) {
        if (!ListenerUtil.mutListener.listen(23288)) {
            startWebActivity(themeId, ThemeWebActivity.ThemeWebActivityType.PREVIEW);
        }
    }

    @Override
    public void onViewSelected(String themeId) {
        if (!ListenerUtil.mutListener.listen(23289)) {
            startWebActivity(themeId, ThemeWebActivity.ThemeWebActivityType.DEMO);
        }
    }

    @Override
    public void onDetailsSelected(String themeId) {
        if (!ListenerUtil.mutListener.listen(23290)) {
            startWebActivity(themeId, ThemeWebActivity.ThemeWebActivityType.DETAILS);
        }
    }

    @Override
    public void onSupportSelected(String themeId) {
        if (!ListenerUtil.mutListener.listen(23291)) {
            startWebActivity(themeId, ThemeWebActivity.ThemeWebActivityType.SUPPORT);
        }
    }

    @Override
    public void onSwipeToRefresh() {
        if (!ListenerUtil.mutListener.listen(23292)) {
            fetchInstalledThemesIfJetpackSite();
        }
        if (!ListenerUtil.mutListener.listen(23293)) {
            fetchWpComThemesIfSyncTimedOut(true);
        }
    }

    @SuppressWarnings("unused")
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onWpComThemesChanged(OnWpComThemesChanged event) {
        if (!ListenerUtil.mutListener.listen(23296)) {
            // always unset refreshing status to remove progress indicator
            if (mThemeBrowserFragment != null) {
                if (!ListenerUtil.mutListener.listen(23294)) {
                    mThemeBrowserFragment.setRefreshing(false);
                }
                if (!ListenerUtil.mutListener.listen(23295)) {
                    mThemeBrowserFragment.refreshView();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(23300)) {
            if (event.isError()) {
                if (!ListenerUtil.mutListener.listen(23298)) {
                    AppLog.e(T.THEMES, "Error fetching themes: " + event.error.message);
                }
                if (!ListenerUtil.mutListener.listen(23299)) {
                    ToastUtils.showToast(this, R.string.theme_fetch_failed, ToastUtils.Duration.SHORT);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(23297)) {
                    AppLog.d(T.THEMES, "WordPress.com Theme fetch successful!");
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(23301)) {
            AppPrefs.setLastWpComThemeSync(System.currentTimeMillis());
        }
    }

    @SuppressWarnings("unused")
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onSiteThemesChanged(OnSiteThemesChanged event) {
        if (!ListenerUtil.mutListener.listen(23302)) {
            if (event.site.getId() != mSite.getId()) {
                // ignore this event as it's not related to the currently selected site
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(23312)) {
            if (event.origin == ThemeAction.FETCH_INSTALLED_THEMES) {
                if (!ListenerUtil.mutListener.listen(23306)) {
                    // always unset refreshing status to remove progress indicator
                    if (mThemeBrowserFragment != null) {
                        if (!ListenerUtil.mutListener.listen(23304)) {
                            mThemeBrowserFragment.setRefreshing(false);
                        }
                        if (!ListenerUtil.mutListener.listen(23305)) {
                            mThemeBrowserFragment.refreshView();
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(23307)) {
                    mIsFetchingInstalledThemes = false;
                }
                if (!ListenerUtil.mutListener.listen(23311)) {
                    if (event.isError()) {
                        if (!ListenerUtil.mutListener.listen(23309)) {
                            AppLog.e(T.THEMES, "Error fetching themes: " + event.error.message);
                        }
                        if (!ListenerUtil.mutListener.listen(23310)) {
                            ToastUtils.showToast(this, R.string.theme_fetch_failed, ToastUtils.Duration.SHORT);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(23308)) {
                            AppLog.d(T.THEMES, "Installed themes fetch successful!");
                        }
                    }
                }
            } else if (event.origin == ThemeAction.REMOVE_SITE_THEMES) {
                if (!ListenerUtil.mutListener.listen(23303)) {
                    // Since this is a logout event, we don't need to do anything
                    AppLog.d(T.THEMES, "Site themes removed for site: " + event.site.getDisplayName());
                }
            }
        }
    }

    @SuppressWarnings("unused")
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onCurrentThemeFetched(OnCurrentThemeFetched event) {
        if (!ListenerUtil.mutListener.listen(23313)) {
            if (event.site.getId() != mSite.getId()) {
                // ignore this event as it's not related to the currently selected site
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(23325)) {
            if (event.isError()) {
                if (!ListenerUtil.mutListener.listen(23318)) {
                    AppLog.e(T.THEMES, "Error fetching current theme: " + event.error.message);
                }
                if (!ListenerUtil.mutListener.listen(23319)) {
                    ToastUtils.showToast(this, R.string.theme_fetch_failed, ToastUtils.Duration.SHORT);
                }
                if (!ListenerUtil.mutListener.listen(23324)) {
                    // set the new current theme to update header
                    if ((ListenerUtil.mutListener.listen(23320) ? (mCurrentTheme != null || mThemeBrowserFragment != null) : (mCurrentTheme != null && mThemeBrowserFragment != null))) {
                        if (!ListenerUtil.mutListener.listen(23323)) {
                            if (mThemeBrowserFragment.getCurrentThemeTextView() != null) {
                                if (!ListenerUtil.mutListener.listen(23321)) {
                                    mThemeBrowserFragment.getCurrentThemeTextView().setText(mCurrentTheme.getName());
                                }
                                if (!ListenerUtil.mutListener.listen(23322)) {
                                    mThemeBrowserFragment.setCurrentThemeId(mCurrentTheme.getThemeId());
                                }
                            }
                        }
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(23314)) {
                    AppLog.d(T.THEMES, "Current Theme fetch successful!");
                }
                if (!ListenerUtil.mutListener.listen(23315)) {
                    mCurrentTheme = mThemeStore.getActiveThemeForSite(event.site);
                }
                if (!ListenerUtil.mutListener.listen(23316)) {
                    AppLog.d(T.THEMES, "Current theme is " + (mCurrentTheme == null ? "(null)" : mCurrentTheme.getName()));
                }
                if (!ListenerUtil.mutListener.listen(23317)) {
                    updateCurrentThemeView();
                }
            }
        }
    }

    @SuppressWarnings("unused")
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onThemeInstalled(OnThemeInstalled event) {
        if (!ListenerUtil.mutListener.listen(23326)) {
            if (event.site.getId() != mSite.getId()) {
                // ignore this event as it's not related to the currently selected site
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(23330)) {
            if (event.isError()) {
                if (!ListenerUtil.mutListener.listen(23329)) {
                    AppLog.e(T.THEMES, "Error installing theme: " + event.error.message);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(23327)) {
                    AppLog.d(T.THEMES, "Theme installation successful! Installed theme: " + event.theme.getName());
                }
                if (!ListenerUtil.mutListener.listen(23328)) {
                    activateTheme(event.theme.getThemeId());
                }
            }
        }
    }

    @SuppressWarnings("unused")
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onThemeActivated(OnThemeActivated event) {
        if (!ListenerUtil.mutListener.listen(23331)) {
            if (event.site.getId() != mSite.getId()) {
                // ignore this event as it's not related to the currently selected site
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(23343)) {
            if (event.isError()) {
                if (!ListenerUtil.mutListener.listen(23341)) {
                    AppLog.e(T.THEMES, "Error activating theme: " + event.error.message);
                }
                if (!ListenerUtil.mutListener.listen(23342)) {
                    ToastUtils.showToast(this, R.string.theme_activation_error, ToastUtils.Duration.SHORT);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(23332)) {
                    AppLog.d(T.THEMES, "Theme activation successful! New theme: " + event.theme.getName());
                }
                if (!ListenerUtil.mutListener.listen(23333)) {
                    mCurrentTheme = mThemeStore.getActiveThemeForSite(event.site);
                }
                if (!ListenerUtil.mutListener.listen(23335)) {
                    if (mCurrentTheme == null) {
                        if (!ListenerUtil.mutListener.listen(23334)) {
                            AppLog.e(T.THEMES, "NOT A CRASH: OnThemeActivated event is ignored as `getActiveThemeForSite` " + "returned null.");
                        }
                        return;
                    }
                }
                if (!ListenerUtil.mutListener.listen(23336)) {
                    updateCurrentThemeView();
                }
                Map<String, Object> themeProperties = new HashMap<>();
                if (!ListenerUtil.mutListener.listen(23337)) {
                    themeProperties.put(THEME_ID, mCurrentTheme.getThemeId());
                }
                if (!ListenerUtil.mutListener.listen(23338)) {
                    AnalyticsUtils.trackWithSiteDetails(Stat.THEMES_CHANGED_THEME, mSite, themeProperties);
                }
                if (!ListenerUtil.mutListener.listen(23340)) {
                    if (!isFinishing()) {
                        if (!ListenerUtil.mutListener.listen(23339)) {
                            showAlertDialogOnNewSettingNewTheme(mCurrentTheme);
                        }
                    }
                }
            }
        }
    }

    private void updateCurrentThemeView() {
        if (!ListenerUtil.mutListener.listen(23348)) {
            if ((ListenerUtil.mutListener.listen(23345) ? ((ListenerUtil.mutListener.listen(23344) ? (mCurrentTheme != null || mThemeBrowserFragment != null) : (mCurrentTheme != null && mThemeBrowserFragment != null)) || mThemeBrowserFragment.getCurrentThemeTextView() != null) : ((ListenerUtil.mutListener.listen(23344) ? (mCurrentTheme != null || mThemeBrowserFragment != null) : (mCurrentTheme != null && mThemeBrowserFragment != null)) && mThemeBrowserFragment.getCurrentThemeTextView() != null))) {
                String text = TextUtils.isEmpty(mCurrentTheme.getName()) ? getString(R.string.unknown) : mCurrentTheme.getName();
                if (!ListenerUtil.mutListener.listen(23346)) {
                    mThemeBrowserFragment.getCurrentThemeTextView().setText(text);
                }
                if (!ListenerUtil.mutListener.listen(23347)) {
                    mThemeBrowserFragment.setCurrentThemeId(mCurrentTheme.getThemeId());
                }
            }
        }
    }

    private void fetchCurrentTheme() {
        if (!ListenerUtil.mutListener.listen(23349)) {
            mDispatcher.dispatch(ThemeActionBuilder.newFetchCurrentThemeAction(mSite));
        }
    }

    private void fetchWpComThemesIfSyncTimedOut(boolean force) {
        long currentTime = System.currentTimeMillis();
        if (!ListenerUtil.mutListener.listen(23361)) {
            if ((ListenerUtil.mutListener.listen(23359) ? (force && (ListenerUtil.mutListener.listen(23358) ? ((ListenerUtil.mutListener.listen(23353) ? (currentTime % AppPrefs.getLastWpComThemeSync()) : (ListenerUtil.mutListener.listen(23352) ? (currentTime / AppPrefs.getLastWpComThemeSync()) : (ListenerUtil.mutListener.listen(23351) ? (currentTime * AppPrefs.getLastWpComThemeSync()) : (ListenerUtil.mutListener.listen(23350) ? (currentTime + AppPrefs.getLastWpComThemeSync()) : (currentTime - AppPrefs.getLastWpComThemeSync()))))) >= WP_COM_THEMES_SYNC_TIMEOUT) : (ListenerUtil.mutListener.listen(23357) ? ((ListenerUtil.mutListener.listen(23353) ? (currentTime % AppPrefs.getLastWpComThemeSync()) : (ListenerUtil.mutListener.listen(23352) ? (currentTime / AppPrefs.getLastWpComThemeSync()) : (ListenerUtil.mutListener.listen(23351) ? (currentTime * AppPrefs.getLastWpComThemeSync()) : (ListenerUtil.mutListener.listen(23350) ? (currentTime + AppPrefs.getLastWpComThemeSync()) : (currentTime - AppPrefs.getLastWpComThemeSync()))))) <= WP_COM_THEMES_SYNC_TIMEOUT) : (ListenerUtil.mutListener.listen(23356) ? ((ListenerUtil.mutListener.listen(23353) ? (currentTime % AppPrefs.getLastWpComThemeSync()) : (ListenerUtil.mutListener.listen(23352) ? (currentTime / AppPrefs.getLastWpComThemeSync()) : (ListenerUtil.mutListener.listen(23351) ? (currentTime * AppPrefs.getLastWpComThemeSync()) : (ListenerUtil.mutListener.listen(23350) ? (currentTime + AppPrefs.getLastWpComThemeSync()) : (currentTime - AppPrefs.getLastWpComThemeSync()))))) < WP_COM_THEMES_SYNC_TIMEOUT) : (ListenerUtil.mutListener.listen(23355) ? ((ListenerUtil.mutListener.listen(23353) ? (currentTime % AppPrefs.getLastWpComThemeSync()) : (ListenerUtil.mutListener.listen(23352) ? (currentTime / AppPrefs.getLastWpComThemeSync()) : (ListenerUtil.mutListener.listen(23351) ? (currentTime * AppPrefs.getLastWpComThemeSync()) : (ListenerUtil.mutListener.listen(23350) ? (currentTime + AppPrefs.getLastWpComThemeSync()) : (currentTime - AppPrefs.getLastWpComThemeSync()))))) != WP_COM_THEMES_SYNC_TIMEOUT) : (ListenerUtil.mutListener.listen(23354) ? ((ListenerUtil.mutListener.listen(23353) ? (currentTime % AppPrefs.getLastWpComThemeSync()) : (ListenerUtil.mutListener.listen(23352) ? (currentTime / AppPrefs.getLastWpComThemeSync()) : (ListenerUtil.mutListener.listen(23351) ? (currentTime * AppPrefs.getLastWpComThemeSync()) : (ListenerUtil.mutListener.listen(23350) ? (currentTime + AppPrefs.getLastWpComThemeSync()) : (currentTime - AppPrefs.getLastWpComThemeSync()))))) == WP_COM_THEMES_SYNC_TIMEOUT) : ((ListenerUtil.mutListener.listen(23353) ? (currentTime % AppPrefs.getLastWpComThemeSync()) : (ListenerUtil.mutListener.listen(23352) ? (currentTime / AppPrefs.getLastWpComThemeSync()) : (ListenerUtil.mutListener.listen(23351) ? (currentTime * AppPrefs.getLastWpComThemeSync()) : (ListenerUtil.mutListener.listen(23350) ? (currentTime + AppPrefs.getLastWpComThemeSync()) : (currentTime - AppPrefs.getLastWpComThemeSync()))))) > WP_COM_THEMES_SYNC_TIMEOUT))))))) : (force || (ListenerUtil.mutListener.listen(23358) ? ((ListenerUtil.mutListener.listen(23353) ? (currentTime % AppPrefs.getLastWpComThemeSync()) : (ListenerUtil.mutListener.listen(23352) ? (currentTime / AppPrefs.getLastWpComThemeSync()) : (ListenerUtil.mutListener.listen(23351) ? (currentTime * AppPrefs.getLastWpComThemeSync()) : (ListenerUtil.mutListener.listen(23350) ? (currentTime + AppPrefs.getLastWpComThemeSync()) : (currentTime - AppPrefs.getLastWpComThemeSync()))))) >= WP_COM_THEMES_SYNC_TIMEOUT) : (ListenerUtil.mutListener.listen(23357) ? ((ListenerUtil.mutListener.listen(23353) ? (currentTime % AppPrefs.getLastWpComThemeSync()) : (ListenerUtil.mutListener.listen(23352) ? (currentTime / AppPrefs.getLastWpComThemeSync()) : (ListenerUtil.mutListener.listen(23351) ? (currentTime * AppPrefs.getLastWpComThemeSync()) : (ListenerUtil.mutListener.listen(23350) ? (currentTime + AppPrefs.getLastWpComThemeSync()) : (currentTime - AppPrefs.getLastWpComThemeSync()))))) <= WP_COM_THEMES_SYNC_TIMEOUT) : (ListenerUtil.mutListener.listen(23356) ? ((ListenerUtil.mutListener.listen(23353) ? (currentTime % AppPrefs.getLastWpComThemeSync()) : (ListenerUtil.mutListener.listen(23352) ? (currentTime / AppPrefs.getLastWpComThemeSync()) : (ListenerUtil.mutListener.listen(23351) ? (currentTime * AppPrefs.getLastWpComThemeSync()) : (ListenerUtil.mutListener.listen(23350) ? (currentTime + AppPrefs.getLastWpComThemeSync()) : (currentTime - AppPrefs.getLastWpComThemeSync()))))) < WP_COM_THEMES_SYNC_TIMEOUT) : (ListenerUtil.mutListener.listen(23355) ? ((ListenerUtil.mutListener.listen(23353) ? (currentTime % AppPrefs.getLastWpComThemeSync()) : (ListenerUtil.mutListener.listen(23352) ? (currentTime / AppPrefs.getLastWpComThemeSync()) : (ListenerUtil.mutListener.listen(23351) ? (currentTime * AppPrefs.getLastWpComThemeSync()) : (ListenerUtil.mutListener.listen(23350) ? (currentTime + AppPrefs.getLastWpComThemeSync()) : (currentTime - AppPrefs.getLastWpComThemeSync()))))) != WP_COM_THEMES_SYNC_TIMEOUT) : (ListenerUtil.mutListener.listen(23354) ? ((ListenerUtil.mutListener.listen(23353) ? (currentTime % AppPrefs.getLastWpComThemeSync()) : (ListenerUtil.mutListener.listen(23352) ? (currentTime / AppPrefs.getLastWpComThemeSync()) : (ListenerUtil.mutListener.listen(23351) ? (currentTime * AppPrefs.getLastWpComThemeSync()) : (ListenerUtil.mutListener.listen(23350) ? (currentTime + AppPrefs.getLastWpComThemeSync()) : (currentTime - AppPrefs.getLastWpComThemeSync()))))) == WP_COM_THEMES_SYNC_TIMEOUT) : ((ListenerUtil.mutListener.listen(23353) ? (currentTime % AppPrefs.getLastWpComThemeSync()) : (ListenerUtil.mutListener.listen(23352) ? (currentTime / AppPrefs.getLastWpComThemeSync()) : (ListenerUtil.mutListener.listen(23351) ? (currentTime * AppPrefs.getLastWpComThemeSync()) : (ListenerUtil.mutListener.listen(23350) ? (currentTime + AppPrefs.getLastWpComThemeSync()) : (currentTime - AppPrefs.getLastWpComThemeSync()))))) > WP_COM_THEMES_SYNC_TIMEOUT))))))))) {
                if (!ListenerUtil.mutListener.listen(23360)) {
                    mDispatcher.dispatch(ThemeActionBuilder.newFetchWpComThemesAction());
                }
            }
        }
    }

    private void fetchInstalledThemesIfJetpackSite() {
        if (!ListenerUtil.mutListener.listen(23366)) {
            if ((ListenerUtil.mutListener.listen(23363) ? ((ListenerUtil.mutListener.listen(23362) ? (mSite.isJetpackConnected() || mSite.isUsingWpComRestApi()) : (mSite.isJetpackConnected() && mSite.isUsingWpComRestApi())) || !mIsFetchingInstalledThemes) : ((ListenerUtil.mutListener.listen(23362) ? (mSite.isJetpackConnected() || mSite.isUsingWpComRestApi()) : (mSite.isJetpackConnected() && mSite.isUsingWpComRestApi())) && !mIsFetchingInstalledThemes))) {
                if (!ListenerUtil.mutListener.listen(23364)) {
                    mDispatcher.dispatch(ThemeActionBuilder.newFetchInstalledThemesAction(mSite));
                }
                if (!ListenerUtil.mutListener.listen(23365)) {
                    mIsFetchingInstalledThemes = true;
                }
            }
        }
    }

    private void activateTheme(String themeId) {
        if (!ListenerUtil.mutListener.listen(23368)) {
            if (!mSite.isUsingWpComRestApi()) {
                if (!ListenerUtil.mutListener.listen(23367)) {
                    AppLog.i(T.THEMES, "Theme activation requires a site using WP.com REST API. Aborting request.");
                }
                return;
            }
        }
        ThemeModel theme = mThemeStore.getInstalledThemeByThemeId(mSite, themeId);
        if (!ListenerUtil.mutListener.listen(23374)) {
            if (theme == null) {
                if (!ListenerUtil.mutListener.listen(23369)) {
                    theme = mThemeStore.getWpComThemeByThemeId(themeId);
                }
                if (!ListenerUtil.mutListener.listen(23371)) {
                    if (theme == null) {
                        if (!ListenerUtil.mutListener.listen(23370)) {
                            AppLog.w(T.THEMES, "Theme unavailable to activate. Fetch it and try again.");
                        }
                        return;
                    }
                }
                if (!ListenerUtil.mutListener.listen(23373)) {
                    if (mSite.isJetpackConnected()) {
                        if (!ListenerUtil.mutListener.listen(23372)) {
                            // first install the theme, then activate it
                            mDispatcher.dispatch(ThemeActionBuilder.newInstallThemeAction(new SiteThemePayload(mSite, theme)));
                        }
                        return;
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(23375)) {
            mDispatcher.dispatch(ThemeActionBuilder.newActivateThemeAction(new SiteThemePayload(mSite, theme)));
        }
    }

    private void addBrowserFragment() {
        if (!ListenerUtil.mutListener.listen(23376)) {
            mThemeBrowserFragment = ThemeBrowserFragment.newInstance(mSite);
        }
        if (!ListenerUtil.mutListener.listen(23377)) {
            getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, mThemeBrowserFragment, ThemeBrowserFragment.TAG).commit();
        }
    }

    private void showAlertDialogOnNewSettingNewTheme(ThemeModel newTheme) {
        AlertDialog.Builder dialogBuilder = new MaterialAlertDialogBuilder(this);
        String thanksMessage = String.format(getString(R.string.theme_prompt), newTheme.getName());
        if (!ListenerUtil.mutListener.listen(23379)) {
            if (!TextUtils.isEmpty(newTheme.getAuthorName())) {
                String append = String.format(getString(R.string.theme_by_author_prompt_append), newTheme.getAuthorName());
                if (!ListenerUtil.mutListener.listen(23378)) {
                    thanksMessage = thanksMessage + " " + append;
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(23380)) {
            dialogBuilder.setMessage(thanksMessage);
        }
        if (!ListenerUtil.mutListener.listen(23381)) {
            dialogBuilder.setNegativeButton(R.string.theme_done, null);
        }
        if (!ListenerUtil.mutListener.listen(23382)) {
            dialogBuilder.setPositiveButton(R.string.theme_manage_site, (dialog, which) -> finish());
        }
        AlertDialog alertDialog = dialogBuilder.create();
        if (!ListenerUtil.mutListener.listen(23383)) {
            alertDialog.show();
        }
    }

    private void startWebActivity(String themeId, ThemeWebActivity.ThemeWebActivityType type) {
        ThemeModel theme = TextUtils.isEmpty(themeId) ? null : mThemeStore.getWpComThemeByThemeId(themeId.replace("-wpcom", ""));
        if (!ListenerUtil.mutListener.listen(23387)) {
            if (theme == null) {
                if (!ListenerUtil.mutListener.listen(23384)) {
                    theme = mThemeStore.getInstalledThemeByThemeId(mSite, themeId);
                }
                if (!ListenerUtil.mutListener.listen(23386)) {
                    if (theme == null) {
                        if (!ListenerUtil.mutListener.listen(23385)) {
                            ToastUtils.showToast(this, R.string.could_not_load_theme);
                        }
                        return;
                    }
                }
            }
        }
        Map<String, Object> themeProperties = new HashMap<>();
        if (!ListenerUtil.mutListener.listen(23388)) {
            themeProperties.put(THEME_ID, themeId);
        }
        if (!ListenerUtil.mutListener.listen(23389)) {
            theme.setActive(isActiveThemeForSite(theme.getThemeId()));
        }
        if (!ListenerUtil.mutListener.listen(23394)) {
            switch(type) {
                case PREVIEW:
                    if (!ListenerUtil.mutListener.listen(23390)) {
                        AnalyticsUtils.trackWithSiteDetails(Stat.THEMES_PREVIEWED_SITE, mSite, themeProperties);
                    }
                    break;
                case DEMO:
                    if (!ListenerUtil.mutListener.listen(23391)) {
                        AnalyticsUtils.trackWithSiteDetails(Stat.THEMES_DEMO_ACCESSED, mSite, themeProperties);
                    }
                    break;
                case DETAILS:
                    if (!ListenerUtil.mutListener.listen(23392)) {
                        AnalyticsUtils.trackWithSiteDetails(Stat.THEMES_DETAILS_ACCESSED, mSite, themeProperties);
                    }
                    break;
                case SUPPORT:
                    if (!ListenerUtil.mutListener.listen(23393)) {
                        AnalyticsUtils.trackWithSiteDetails(Stat.THEMES_SUPPORT_ACCESSED, mSite, themeProperties);
                    }
                    break;
            }
        }
        if (!ListenerUtil.mutListener.listen(23395)) {
            ThemeWebActivity.openTheme(this, mSite, theme, type);
        }
    }

    private boolean isActiveThemeForSite(@NonNull String themeId) {
        final ThemeModel storedActiveTheme = mThemeStore.getActiveThemeForSite(mSite);
        return (ListenerUtil.mutListener.listen(23396) ? (storedActiveTheme != null || themeId.equals(storedActiveTheme.getThemeId().replace("-wpcom", ""))) : (storedActiveTheme != null && themeId.equals(storedActiveTheme.getThemeId().replace("-wpcom", ""))));
    }
}
