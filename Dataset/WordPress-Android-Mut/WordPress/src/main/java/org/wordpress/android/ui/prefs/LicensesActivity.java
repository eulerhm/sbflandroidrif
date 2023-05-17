package org.wordpress.android.ui.prefs;

import android.os.Bundle;
import org.wordpress.android.R;
import org.wordpress.android.ui.WebViewActivity;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Display open source licenses for the application.
 */
public class LicensesActivity extends WebViewActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(14851)) {
            super.onCreate(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(14852)) {
            setTitle(getResources().getText(R.string.open_source_licenses));
        }
    }

    @Override
    protected void loadContent() {
        if (!ListenerUtil.mutListener.listen(14853)) {
            loadUrl("file:///android_asset/licenses.html");
        }
    }
}
