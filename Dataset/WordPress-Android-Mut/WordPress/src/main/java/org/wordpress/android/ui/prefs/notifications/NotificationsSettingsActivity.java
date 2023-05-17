package org.wordpress.android.ui.prefs.notifications;

import android.app.FragmentManager;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.preference.PreferenceManager;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.wordpress.android.R;
import org.wordpress.android.analytics.AnalyticsTracker;
import org.wordpress.android.ui.LocaleAwareActivity;
import org.wordpress.android.ui.notifications.NotificationEvents;
import org.wordpress.android.ui.prefs.notifications.PrefMainSwitchToolbarView.MainSwitchToolbarListener;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

// Simple wrapper activity for NotificationsSettingsFragment
public class NotificationsSettingsActivity extends LocaleAwareActivity implements MainSwitchToolbarListener {

    private TextView mMessageTextView;

    private View mMessageContainer;

    protected SharedPreferences mSharedPreferences;

    protected View mFragmentContainer;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(13333)) {
            super.onCreate(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(13334)) {
            setContentView(R.layout.notifications_settings_activity);
        }
        if (!ListenerUtil.mutListener.listen(13335)) {
            mFragmentContainer = findViewById(R.id.fragment_container);
        }
        if (!ListenerUtil.mutListener.listen(13336)) {
            // Get shared preferences for main switch.
            mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(NotificationsSettingsActivity.this);
        }
        if (!ListenerUtil.mutListener.listen(13337)) {
            // Set up primary toolbar
            setUpToolbar();
        }
        if (!ListenerUtil.mutListener.listen(13338)) {
            // Set up main switch
            setUpMainSwitch();
        }
        FragmentManager fragmentManager = getFragmentManager();
        if (!ListenerUtil.mutListener.listen(13340)) {
            if (savedInstanceState == null) {
                if (!ListenerUtil.mutListener.listen(13339)) {
                    fragmentManager.beginTransaction().add(R.id.fragment_container, new NotificationsSettingsFragment()).commit();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(13341)) {
            mMessageContainer = findViewById(R.id.notifications_settings_message_container);
        }
        if (!ListenerUtil.mutListener.listen(13342)) {
            mMessageTextView = findViewById(R.id.notifications_settings_message);
        }
    }

    @Override
    protected void onStop() {
        if (!ListenerUtil.mutListener.listen(13343)) {
            EventBus.getDefault().unregister(this);
        }
        if (!ListenerUtil.mutListener.listen(13344)) {
            super.onStop();
        }
    }

    @Override
    protected void onStart() {
        if (!ListenerUtil.mutListener.listen(13345)) {
            super.onStart();
        }
        if (!ListenerUtil.mutListener.listen(13346)) {
            EventBus.getDefault().register(this);
        }
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        if (!ListenerUtil.mutListener.listen(13348)) {
            switch(item.getItemId()) {
                case android.R.id.home:
                    if (!ListenerUtil.mutListener.listen(13347)) {
                        onBackPressed();
                    }
                    return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("unused")
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(NotificationEvents.NotificationsSettingsStatusChanged event) {
        if (!ListenerUtil.mutListener.listen(13352)) {
            if (TextUtils.isEmpty(event.getMessage())) {
                if (!ListenerUtil.mutListener.listen(13351)) {
                    mMessageContainer.setVisibility(View.GONE);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(13349)) {
                    mMessageContainer.setVisibility(View.VISIBLE);
                }
                if (!ListenerUtil.mutListener.listen(13350)) {
                    mMessageTextView.setText(event.getMessage());
                }
            }
        }
    }

    /**
     * Set up primary toolbar for navigation and search
     */
    private void setUpToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar_with_search);
        if (!ListenerUtil.mutListener.listen(13354)) {
            if (toolbar != null) {
                if (!ListenerUtil.mutListener.listen(13353)) {
                    setSupportActionBar(toolbar);
                }
            }
        }
        ActionBar actionBar = getSupportActionBar();
        if (!ListenerUtil.mutListener.listen(13358)) {
            if (actionBar != null) {
                if (!ListenerUtil.mutListener.listen(13355)) {
                    actionBar.setTitle(R.string.notification_settings);
                }
                if (!ListenerUtil.mutListener.listen(13356)) {
                    actionBar.setDisplayHomeAsUpEnabled(true);
                }
                if (!ListenerUtil.mutListener.listen(13357)) {
                    actionBar.setDisplayShowTitleEnabled(true);
                }
            }
        }
    }

    /**
     * Sets up main switch to disable/enable all notification settings
     */
    private void setUpMainSwitch() {
        PrefMainSwitchToolbarView mainSwitchToolBarView = findViewById(R.id.main_switch);
        if (!ListenerUtil.mutListener.listen(13359)) {
            mainSwitchToolBarView.setMainSwitchToolbarListener(this);
        }
        // Set main switch state from shared preferences.
        boolean isMainChecked = mSharedPreferences.getBoolean(getString(R.string.wp_pref_notifications_main), true);
        if (!ListenerUtil.mutListener.listen(13360)) {
            mainSwitchToolBarView.loadInitialState(isMainChecked);
        }
        if (!ListenerUtil.mutListener.listen(13361)) {
            hideDisabledView(isMainChecked);
        }
    }

    @Override
    public void onMainSwitchCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (!ListenerUtil.mutListener.listen(13362)) {
            mSharedPreferences.edit().putBoolean(getString(R.string.wp_pref_notifications_main), isChecked).apply();
        }
        if (!ListenerUtil.mutListener.listen(13363)) {
            hideDisabledView(isChecked);
        }
        if (!ListenerUtil.mutListener.listen(13366)) {
            if (isChecked) {
                if (!ListenerUtil.mutListener.listen(13365)) {
                    AnalyticsTracker.track(AnalyticsTracker.Stat.NOTIFICATION_SETTINGS_APP_NOTIFICATIONS_ENABLED);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(13364)) {
                    AnalyticsTracker.track(AnalyticsTracker.Stat.NOTIFICATION_SETTINGS_APP_NOTIFICATIONS_DISABLED);
                }
            }
        }
    }

    /**
     * Hide view when Notification Settings are disabled by toggling the main switch off.
     *
     * @param isMainChecked TRUE to hide disabled view, FALSE to show disabled view
     */
    protected void hideDisabledView(boolean isMainChecked) {
        LinearLayout notificationsDisabledView = findViewById(R.id.notification_settings_disabled_view);
        if (!ListenerUtil.mutListener.listen(13367)) {
            notificationsDisabledView.setVisibility(isMainChecked ? View.INVISIBLE : View.VISIBLE);
        }
        if (!ListenerUtil.mutListener.listen(13368)) {
            mFragmentContainer.setVisibility(isMainChecked ? View.VISIBLE : View.GONE);
        }
    }
}
