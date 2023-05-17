package org.wordpress.android.ui.prefs;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import androidx.appcompat.widget.Toolbar;
import org.wordpress.android.Constants;
import org.wordpress.android.R;
import org.wordpress.android.WordPress;
import org.wordpress.android.ui.ActivityLauncher;
import org.wordpress.android.ui.LocaleAwareActivity;
import org.wordpress.android.util.WPUrlUtils;
import org.wordpress.android.widgets.WPTextView;
import java.util.Calendar;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class AboutActivity extends LocaleAwareActivity implements OnClickListener {

    private int mCurrentTapCountForSecretCrash = 0;

    @Override
    public void onCreate(Bundle icicle) {
        if (!ListenerUtil.mutListener.listen(13871)) {
            super.onCreate(icicle);
        }
        if (!ListenerUtil.mutListener.listen(13872)) {
            setContentView(R.layout.about_activity);
        }
        Toolbar toolbar = findViewById(R.id.toolbar);
        if (!ListenerUtil.mutListener.listen(13873)) {
            setSupportActionBar(toolbar);
        }
        if (!ListenerUtil.mutListener.listen(13874)) {
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close_white_24dp);
        }
        if (!ListenerUtil.mutListener.listen(13875)) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        if (!ListenerUtil.mutListener.listen(13876)) {
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        if (!ListenerUtil.mutListener.listen(13877)) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
        WPTextView version = findViewById(R.id.about_version);
        if (!ListenerUtil.mutListener.listen(13878)) {
            version.setText(getString(R.string.version_with_name_param, WordPress.versionName));
        }
        View tos = findViewById(R.id.about_tos);
        if (!ListenerUtil.mutListener.listen(13879)) {
            tos.setOnClickListener(this);
        }
        View pp = findViewById(R.id.about_privacy);
        if (!ListenerUtil.mutListener.listen(13880)) {
            pp.setOnClickListener(this);
        }
        WPTextView publisher = findViewById(R.id.about_publisher);
        if (!ListenerUtil.mutListener.listen(13881)) {
            publisher.setText(getString(R.string.publisher_with_company_param, getString(R.string.automattic_inc)));
        }
        WPTextView copyright = findViewById(R.id.about_copyright);
        if (!ListenerUtil.mutListener.listen(13882)) {
            copyright.setText(getString(R.string.copyright_with_year_and_company_params, Calendar.getInstance().get(Calendar.YEAR), getString(R.string.automattic_inc)));
        }
        View about = findViewById(R.id.about_url);
        if (!ListenerUtil.mutListener.listen(13883)) {
            about.setOnClickListener(this);
        }
        View secretCrash = findViewById(R.id.about_secret_crash);
        if (!ListenerUtil.mutListener.listen(13884)) {
            secretCrash.setOnClickListener(view -> {
                mCurrentTapCountForSecretCrash++;
                if (mCurrentTapCountForSecretCrash >= 10) {
                    throw new IllegalStateException("This is a secret crash triggered from an invisible button in " + "the about page in case it's necessary to test a crash");
                }
            });
        }
    }

    @Override
    public void onClick(View v) {
        String url;
        int id = v.getId();
        if (id == R.id.about_url) {
            url = Constants.URL_AUTOMATTIC;
        } else if (id == R.id.about_tos) {
            url = WPUrlUtils.buildTermsOfServiceUrl(this);
        } else if (id == R.id.about_privacy) {
            url = Constants.URL_PRIVACY_POLICY;
        } else {
            return;
        }
        if (!ListenerUtil.mutListener.listen(13885)) {
            ActivityLauncher.openUrlExternal(this, url);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (!ListenerUtil.mutListener.listen(13887)) {
            if (item.getItemId() == android.R.id.home) {
                if (!ListenerUtil.mutListener.listen(13886)) {
                    finish();
                }
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
