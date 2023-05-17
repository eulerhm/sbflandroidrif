package org.wordpress.android.ui.prefs;

import android.os.Bundle;
import android.view.MenuItem;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import org.wordpress.android.R;
import org.wordpress.android.WordPress;
import org.wordpress.android.fluxc.Dispatcher;
import org.wordpress.android.fluxc.store.AccountStore;
import org.wordpress.android.ui.LocaleAwareActivity;
import javax.inject.Inject;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class MyProfileActivity extends LocaleAwareActivity {

    @Inject
    Dispatcher mDispatcher;

    @Inject
    AccountStore mAccountStore;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(14866)) {
            super.onCreate(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(14867)) {
            ((WordPress) getApplication()).component().inject(this);
        }
        if (!ListenerUtil.mutListener.listen(14868)) {
            setContentView(R.layout.my_profile_activity);
        }
        Toolbar toolbar = findViewById(R.id.toolbar_main);
        if (!ListenerUtil.mutListener.listen(14869)) {
            setSupportActionBar(toolbar);
        }
        ActionBar actionBar = getSupportActionBar();
        if (!ListenerUtil.mutListener.listen(14873)) {
            if (actionBar != null) {
                if (!ListenerUtil.mutListener.listen(14870)) {
                    actionBar.setHomeButtonEnabled(true);
                }
                if (!ListenerUtil.mutListener.listen(14871)) {
                    actionBar.setDisplayHomeAsUpEnabled(true);
                }
                if (!ListenerUtil.mutListener.listen(14872)) {
                    actionBar.setTitle(R.string.my_profile);
                }
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        if (!ListenerUtil.mutListener.listen(14875)) {
            if (item.getItemId() == android.R.id.home) {
                if (!ListenerUtil.mutListener.listen(14874)) {
                    onBackPressed();
                }
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
