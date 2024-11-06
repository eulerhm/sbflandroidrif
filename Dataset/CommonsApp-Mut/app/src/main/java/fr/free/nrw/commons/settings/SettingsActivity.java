package fr.free.nrw.commons.settings;

import android.os.Bundle;
import android.view.MenuItem;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import butterknife.BindView;
import butterknife.ButterKnife;
import fr.free.nrw.commons.R;
import fr.free.nrw.commons.theme.BaseActivity;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * allows the user to change the settings
 */
public class SettingsActivity extends BaseActivity {

    private AppCompatDelegate settingsDelegate;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    /**
     * to be called when the activity starts
     * @param savedInstanceState the previously saved state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(171)) {
            super.onCreate(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(172)) {
            setContentView(R.layout.activity_settings);
        }
        if (!ListenerUtil.mutListener.listen(173)) {
            ButterKnife.bind(this);
        }
        if (!ListenerUtil.mutListener.listen(174)) {
            setSupportActionBar(toolbar);
        }
        if (!ListenerUtil.mutListener.listen(175)) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    /**
     * takes care of actions taken after the creation has happened
     * @param savedInstanceState the saved state
     */
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(176)) {
            super.onPostCreate(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(178)) {
            if (settingsDelegate == null) {
                if (!ListenerUtil.mutListener.listen(177)) {
                    settingsDelegate = AppCompatDelegate.create(this, null);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(179)) {
            settingsDelegate.onPostCreate(savedInstanceState);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        if (!ListenerUtil.mutListener.listen(180)) {
            onBackPressed();
        }
        return true;
    }

    /**
     * Handle action-bar clicks
     * @param item the selected item
     * @return true on success, false on failure
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case android.R.id.home:
                if (!ListenerUtil.mutListener.listen(181)) {
                    finish();
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
