package org.wordpress.android.ui.themes;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import org.wordpress.android.R;
import org.wordpress.android.fluxc.model.SiteModel;
import org.wordpress.android.fluxc.model.ThemeModel;
import org.wordpress.android.ui.ActivityLauncher;
import org.wordpress.android.ui.WPWebViewActivity;
import org.wordpress.android.util.AppLog;
import org.wordpress.android.util.ToastUtils;
import org.wordpress.android.util.UrlUtils;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class ThemeWebActivity extends WPWebViewActivity {

    public static final String IS_CURRENT_THEME = "is_current_theme";

    public static final String IS_PREMIUM_THEME = "is_premium_theme";

    public static final String THEME_NAME = "theme_name";

    public static final String THEME_HTTP_PREFIX = "http";

    public static final String THEME_HTTPS_PROTOCOL = "https://";

    private static final String THEME_DOMAIN_PUBLIC = "pub";

    private static final String THEME_DOMAIN_PREMIUM = "premium";

    private static final String THEME_URL_PREVIEW = "https://wordpress.com/customize/%s?theme=%s/%s&hide_close=true";

    private static final String THEME_URL_SUPPORT = "https://wordpress.com/theme/%s/support/?preview=true&iframe=true";

    private static final String THEME_URL_DETAILS = "https://wordpress.com/theme/%s/?preview=true&iframe=true";

    private static final String THEME_URL_DEMO_PARAMETER = "demo=true&iframe=true&theme_preview=true";

    enum ThemeWebActivityType {

        PREVIEW, DEMO, DETAILS, SUPPORT
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(23625)) {
            super.onCreate(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(23626)) {
            setActionBarTitleToThemeName();
        }
        if (!ListenerUtil.mutListener.listen(23627)) {
            toggleNavbarVisibility(false);
        }
    }

    public static String getSiteLoginUrl(SiteModel site) {
        if (!ListenerUtil.mutListener.listen(23628)) {
            if (site.isJetpackConnected()) {
                return WPCOM_LOGIN_URL;
            }
        }
        return WPWebViewActivity.getSiteLoginUrl(site);
    }

    public static void openTheme(Activity activity, @NonNull SiteModel site, @NonNull ThemeModel theme, @NonNull ThemeWebActivityType type) {
        String url = getUrl(site, theme, type, !theme.isFree());
        if (!ListenerUtil.mutListener.listen(23630)) {
            if (TextUtils.isEmpty(url)) {
                if (!ListenerUtil.mutListener.listen(23629)) {
                    ToastUtils.showToast(activity, R.string.could_not_load_theme);
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(23634)) {
            if ((ListenerUtil.mutListener.listen(23631) ? (type == ThemeWebActivityType.PREVIEW && !theme.isWpComTheme()) : (type == ThemeWebActivityType.PREVIEW || !theme.isWpComTheme()))) {
                if (!ListenerUtil.mutListener.listen(23633)) {
                    // Ref: https://github.com/wordpress-mobile/WordPress-Android/issues/4934
                    ActivityLauncher.openUrlExternal(activity, url);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(23632)) {
                    openWPCOMURL(activity, url, theme, site);
                }
            }
        }
    }

    private static void openWPCOMURL(Activity activity, String url, ThemeModel theme, SiteModel site) {
        if (!ListenerUtil.mutListener.listen(23638)) {
            if (activity == null) {
                if (!ListenerUtil.mutListener.listen(23637)) {
                    AppLog.e(AppLog.T.UTILS, "ThemeWebActivity requires a non-null activity");
                }
                return;
            } else if (TextUtils.isEmpty(url)) {
                if (!ListenerUtil.mutListener.listen(23635)) {
                    AppLog.e(AppLog.T.UTILS, "ThemeWebActivity requires non-empty URL");
                }
                if (!ListenerUtil.mutListener.listen(23636)) {
                    ToastUtils.showToast(activity, R.string.invalid_site_url_message, ToastUtils.Duration.SHORT);
                }
                return;
            }
        }
        String authURL = ThemeWebActivity.getSiteLoginUrl(site);
        Intent intent = new Intent(activity, ThemeWebActivity.class);
        if (!ListenerUtil.mutListener.listen(23639)) {
            intent.putExtra(WPWebViewActivity.URL_TO_LOAD, url);
        }
        if (!ListenerUtil.mutListener.listen(23640)) {
            intent.putExtra(WPWebViewActivity.AUTHENTICATION_URL, authURL);
        }
        if (!ListenerUtil.mutListener.listen(23641)) {
            intent.putExtra(WPWebViewActivity.LOCAL_BLOG_ID, site.getId());
        }
        if (!ListenerUtil.mutListener.listen(23642)) {
            intent.putExtra(WPWebViewActivity.USE_GLOBAL_WPCOM_USER, true);
        }
        if (!ListenerUtil.mutListener.listen(23643)) {
            intent.putExtra(IS_PREMIUM_THEME, !theme.isFree());
        }
        if (!ListenerUtil.mutListener.listen(23644)) {
            intent.putExtra(IS_CURRENT_THEME, theme.getActive());
        }
        if (!ListenerUtil.mutListener.listen(23645)) {
            intent.putExtra(THEME_NAME, theme.getName());
        }
        if (!ListenerUtil.mutListener.listen(23646)) {
            intent.putExtra(ThemeBrowserActivity.THEME_ID, theme.getThemeId());
        }
        if (!ListenerUtil.mutListener.listen(23647)) {
            activity.startActivityForResult(intent, ThemeBrowserActivity.ACTIVATE_THEME);
        }
    }

    public static String getIdentifierForCustomizer(@NonNull SiteModel site, @NonNull ThemeModel theme) {
        if (site.isJetpackConnected()) {
            return theme.getThemeId();
        } else {
            return theme.getStylesheet();
        }
    }

    public static String getUrl(@NonNull SiteModel site, @NonNull ThemeModel theme, @NonNull ThemeWebActivityType type, boolean isPremium) {
        if (!ListenerUtil.mutListener.listen(23651)) {
            if (theme.isWpComTheme()) {
                if (!ListenerUtil.mutListener.listen(23650)) {
                    switch(type) {
                        case PREVIEW:
                            String domain = isPremium ? THEME_DOMAIN_PREMIUM : THEME_DOMAIN_PUBLIC;
                            return String.format(THEME_URL_PREVIEW, UrlUtils.getHost(site.getUrl()), domain, theme.getThemeId());
                        case DEMO:
                            String url = theme.getDemoUrl();
                            if (!ListenerUtil.mutListener.listen(23649)) {
                                if (url.contains("?")) {
                                    return url + "&" + THEME_URL_DEMO_PARAMETER;
                                } else {
                                    return url + "?" + THEME_URL_DEMO_PARAMETER;
                                }
                            }
                        case DETAILS:
                            return String.format(THEME_URL_DETAILS, theme.getThemeId());
                        case SUPPORT:
                            return String.format(THEME_URL_SUPPORT, theme.getThemeId());
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(23648)) {
                    switch(type) {
                        case PREVIEW:
                            return site.getAdminUrl() + "customize.php?theme=" + getIdentifierForCustomizer(site, theme);
                        case DEMO:
                            return site.getAdminUrl() + "themes.php?theme=" + theme.getThemeId();
                        case DETAILS:
                        case SUPPORT:
                            return theme.getThemeUrl();
                    }
                }
            }
        }
        return "";
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!ListenerUtil.mutListener.listen(23653)) {
            if (shouldShowActivateMenuItem()) {
                if (!ListenerUtil.mutListener.listen(23652)) {
                    getMenuInflater().inflate(R.menu.theme_web, menu);
                }
            }
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (!ListenerUtil.mutListener.listen(23657)) {
            if (item.getItemId() == R.id.action_activate) {
                Intent returnIntent = new Intent();
                if (!ListenerUtil.mutListener.listen(23654)) {
                    setResult(RESULT_OK, returnIntent);
                }
                if (!ListenerUtil.mutListener.listen(23655)) {
                    returnIntent.putExtra(ThemeBrowserActivity.THEME_ID, getIntent().getStringExtra(ThemeBrowserActivity.THEME_ID));
                }
                if (!ListenerUtil.mutListener.listen(23656)) {
                    finish();
                }
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private void setActionBarTitleToThemeName() {
        String themeName = getIntent().getStringExtra(THEME_NAME);
        if (!ListenerUtil.mutListener.listen(23660)) {
            if ((ListenerUtil.mutListener.listen(23658) ? (getSupportActionBar() != null || themeName != null) : (getSupportActionBar() != null && themeName != null))) {
                if (!ListenerUtil.mutListener.listen(23659)) {
                    getSupportActionBar().setTitle(themeName);
                }
            }
        }
    }

    /**
     * Show Activate in the Action Bar menu if the theme is free and not the current theme.
     */
    private boolean shouldShowActivateMenuItem() {
        boolean isPremiumTheme = getIntent().getBooleanExtra(IS_PREMIUM_THEME, false);
        boolean isCurrentTheme = getIntent().getBooleanExtra(IS_CURRENT_THEME, false);
        return (ListenerUtil.mutListener.listen(23661) ? (!isCurrentTheme || !isPremiumTheme) : (!isCurrentTheme && !isPremiumTheme));
    }
}
