package fr.free.nrw.commons.theme;

import android.content.res.Configuration;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import javax.inject.Inject;
import javax.inject.Named;
import fr.free.nrw.commons.R;
import fr.free.nrw.commons.di.CommonsDaggerAppCompatActivity;
import fr.free.nrw.commons.kvstore.JsonKvStore;
import fr.free.nrw.commons.utils.SystemThemeUtils;
import io.reactivex.disposables.CompositeDisposable;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public abstract class BaseActivity extends CommonsDaggerAppCompatActivity {

    @Inject
    @Named("default_preferences")
    public JsonKvStore defaultKvStore;

    @Inject
    SystemThemeUtils systemThemeUtils;

    protected CompositeDisposable compositeDisposable = new CompositeDisposable();

    protected boolean wasPreviouslyDarkTheme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(5588)) {
            super.onCreate(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(5589)) {
            wasPreviouslyDarkTheme = systemThemeUtils.isDeviceInNightMode();
        }
        if (!ListenerUtil.mutListener.listen(5590)) {
            setTheme(wasPreviouslyDarkTheme ? R.style.DarkAppTheme : R.style.LightAppTheme);
        }
        float fontScale = android.provider.Settings.System.getFloat(getBaseContext().getContentResolver(), android.provider.Settings.System.FONT_SCALE, 1f);
        if (!ListenerUtil.mutListener.listen(5591)) {
            adjustFontScale(getResources().getConfiguration(), fontScale);
        }
    }

    @Override
    protected void onResume() {
        if (!ListenerUtil.mutListener.listen(5593)) {
            // Restart activity if theme is changed
            if (wasPreviouslyDarkTheme != systemThemeUtils.isDeviceInNightMode()) {
                if (!ListenerUtil.mutListener.listen(5592)) {
                    recreate();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(5594)) {
            super.onResume();
        }
    }

    @Override
    protected void onDestroy() {
        if (!ListenerUtil.mutListener.listen(5595)) {
            super.onDestroy();
        }
        if (!ListenerUtil.mutListener.listen(5596)) {
            compositeDisposable.clear();
        }
    }

    /**
     * Apply fontScale on device
     */
    public void adjustFontScale(Configuration configuration, float scale) {
        if (!ListenerUtil.mutListener.listen(5597)) {
            configuration.fontScale = scale;
        }
        final DisplayMetrics metrics = getResources().getDisplayMetrics();
        final WindowManager wm = (WindowManager) getSystemService(WINDOW_SERVICE);
        if (!ListenerUtil.mutListener.listen(5598)) {
            wm.getDefaultDisplay().getMetrics(metrics);
        }
        if (!ListenerUtil.mutListener.listen(5603)) {
            metrics.scaledDensity = (ListenerUtil.mutListener.listen(5602) ? (configuration.fontScale % metrics.density) : (ListenerUtil.mutListener.listen(5601) ? (configuration.fontScale / metrics.density) : (ListenerUtil.mutListener.listen(5600) ? (configuration.fontScale - metrics.density) : (ListenerUtil.mutListener.listen(5599) ? (configuration.fontScale + metrics.density) : (configuration.fontScale * metrics.density)))));
        }
        if (!ListenerUtil.mutListener.listen(5604)) {
            getBaseContext().getResources().updateConfiguration(configuration, metrics);
        }
    }
}
