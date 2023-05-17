package org.wordpress.android.networking;

import android.os.Bundle;
import androidx.appcompat.app.ActionBar;
import org.wordpress.android.R;
import org.wordpress.android.ui.WebViewActivity;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Display details of a SSL cert
 */
public class SSLCertsViewActivity extends WebViewActivity {

    public static final String CERT_DETAILS_KEYS = "CertDetails";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(2706)) {
            super.onCreate(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(2707)) {
            setTitle(getResources().getText(R.string.ssl_certificate_details));
        }
        ActionBar actionBar = getSupportActionBar();
        if (!ListenerUtil.mutListener.listen(2709)) {
            if (actionBar != null) {
                if (!ListenerUtil.mutListener.listen(2708)) {
                    actionBar.setDisplayHomeAsUpEnabled(false);
                }
            }
        }
    }

    @Override
    protected void loadContent() {
        Bundle extras = getIntent().getExtras();
        if (!ListenerUtil.mutListener.listen(2714)) {
            if ((ListenerUtil.mutListener.listen(2710) ? (extras != null || extras.containsKey(CERT_DETAILS_KEYS)) : (extras != null && extras.containsKey(CERT_DETAILS_KEYS)))) {
                String certDetails = extras.getString(CERT_DETAILS_KEYS);
                StringBuilder sb = new StringBuilder("<html><body>");
                if (!ListenerUtil.mutListener.listen(2711)) {
                    sb.append(certDetails);
                }
                if (!ListenerUtil.mutListener.listen(2712)) {
                    sb.append("</body></html>");
                }
                if (!ListenerUtil.mutListener.listen(2713)) {
                    mWebView.loadDataWithBaseURL(null, sb.toString(), "text/html", "utf-8", null);
                }
            }
        }
    }

    @Override
    protected void configureWebView() {
        if (!ListenerUtil.mutListener.listen(2715)) {
            mWebView.getSettings().setDefaultTextEncodingName("utf-8");
        }
    }
}
