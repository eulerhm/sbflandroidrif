package org.wordpress.android.ui.prefs;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import org.wordpress.android.R;
import org.wordpress.android.WordPress;
import org.wordpress.android.ui.LocaleAwareActivity;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class AppSettingsActivity extends LocaleAwareActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(14320)) {
            super.onCreate(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(14321)) {
            ((WordPress) getApplication()).component().inject(this);
        }
        if (!ListenerUtil.mutListener.listen(14322)) {
            setContentView(R.layout.app_settings_activity);
        }
        Toolbar toolbar = findViewById(R.id.toolbar_main);
        if (!ListenerUtil.mutListener.listen(14323)) {
            setSupportActionBar(toolbar);
        }
        ActionBar actionBar = getSupportActionBar();
        if (!ListenerUtil.mutListener.listen(14327)) {
            if (actionBar != null) {
                if (!ListenerUtil.mutListener.listen(14324)) {
                    actionBar.setHomeButtonEnabled(true);
                }
                if (!ListenerUtil.mutListener.listen(14325)) {
                    actionBar.setDisplayHomeAsUpEnabled(true);
                }
                if (!ListenerUtil.mutListener.listen(14326)) {
                    actionBar.setTitle(R.string.me_btn_app_settings);
                }
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        if (!ListenerUtil.mutListener.listen(14329)) {
            if (item.getItemId() == android.R.id.home) {
                if (!ListenerUtil.mutListener.listen(14328)) {
                    onBackPressed();
                }
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void recreate() {
        Intent intent = new Intent(AppSettingsActivity.this, AppSettingsActivity.class);
        if (!ListenerUtil.mutListener.listen(14330)) {
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        if (!ListenerUtil.mutListener.listen(14331)) {
            startActivity(intent);
        }
        if (!ListenerUtil.mutListener.listen(14332)) {
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        }
        if (!ListenerUtil.mutListener.listen(14333)) {
            finish();
        }
    }
}
