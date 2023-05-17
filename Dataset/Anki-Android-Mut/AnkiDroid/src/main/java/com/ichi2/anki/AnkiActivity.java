package com.ichi2.anki;

import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.browser.customtabs.CustomTabColorSchemeParams;
import androidx.browser.customtabs.CustomTabsIntent;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.widget.ProgressBar;
import com.ichi2.anim.ActivityTransitionAnimation;
import com.ichi2.anki.analytics.UsageAnalytics;
import com.ichi2.anki.dialogs.AsyncDialogFragment;
import com.ichi2.anki.dialogs.DialogHandler;
import com.ichi2.anki.dialogs.SimpleMessageDialog;
import com.ichi2.async.CollectionLoader;
import com.ichi2.compat.customtabs.CustomTabActivityHelper;
import com.ichi2.compat.customtabs.CustomTabsFallback;
import com.ichi2.compat.customtabs.CustomTabsHelper;
import com.ichi2.libanki.Collection;
import com.ichi2.themes.Themes;
import com.ichi2.utils.AdaptionUtil;
import com.ichi2.utils.AndroidUiUtils;
import timber.log.Timber;
import static com.ichi2.anim.ActivityTransitionAnimation.Direction.*;
import static com.ichi2.anim.ActivityTransitionAnimation.Direction;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class AnkiActivity extends AppCompatActivity implements SimpleMessageDialog.SimpleMessageDialogListener {

    public final int SIMPLE_NOTIFICATION_ID = 0;

    public static final int REQUEST_REVIEW = 901;

    /**
     * The name of the parent class (Reviewer)
     */
    private final String mActivityName;

    private final DialogHandler mHandler = new DialogHandler(this);

    // custom tabs
    private CustomTabActivityHelper mCustomTabActivityHelper;

    public AnkiActivity() {
        super();
        this.mActivityName = getClass().getSimpleName();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(5581)) {
            Timber.i("AnkiActivity::onCreate - %s", mActivityName);
        }
        if (!ListenerUtil.mutListener.listen(5582)) {
            // The hardware buttons should control the music volume
            setVolumeControlStream(AudioManager.STREAM_MUSIC);
        }
        if (!ListenerUtil.mutListener.listen(5583)) {
            // Set the theme
            Themes.setTheme(this);
        }
        if (!ListenerUtil.mutListener.listen(5584)) {
            super.onCreate(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(5586)) {
            // Disable the notifications bar if running under the test monkey.
            if (AdaptionUtil.isUserATestClient()) {
                if (!ListenerUtil.mutListener.listen(5585)) {
                    getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(5587)) {
            mCustomTabActivityHelper = new CustomTabActivityHelper();
        }
    }

    @Override
    protected void attachBaseContext(Context base) {
        if (!ListenerUtil.mutListener.listen(5588)) {
            super.attachBaseContext(AnkiDroidApp.updateContextWithLanguage(base));
        }
    }

    @Override
    protected void onStart() {
        if (!ListenerUtil.mutListener.listen(5589)) {
            Timber.i("AnkiActivity::onStart - %s", mActivityName);
        }
        if (!ListenerUtil.mutListener.listen(5590)) {
            super.onStart();
        }
        if (!ListenerUtil.mutListener.listen(5591)) {
            mCustomTabActivityHelper.bindCustomTabsService(this);
        }
    }

    @Override
    protected void onStop() {
        if (!ListenerUtil.mutListener.listen(5592)) {
            Timber.i("AnkiActivity::onStop - %s", mActivityName);
        }
        if (!ListenerUtil.mutListener.listen(5593)) {
            super.onStop();
        }
        if (!ListenerUtil.mutListener.listen(5594)) {
            mCustomTabActivityHelper.unbindCustomTabsService(this);
        }
    }

    @Override
    protected void onPause() {
        if (!ListenerUtil.mutListener.listen(5595)) {
            Timber.i("AnkiActivity::onPause - %s", mActivityName);
        }
        if (!ListenerUtil.mutListener.listen(5596)) {
            super.onPause();
        }
    }

    @Override
    protected void onResume() {
        if (!ListenerUtil.mutListener.listen(5597)) {
            Timber.i("AnkiActivity::onResume - %s", mActivityName);
        }
        if (!ListenerUtil.mutListener.listen(5598)) {
            super.onResume();
        }
        if (!ListenerUtil.mutListener.listen(5599)) {
            UsageAnalytics.sendAnalyticsScreenView(this);
        }
        if (!ListenerUtil.mutListener.listen(5600)) {
            ((NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE)).cancel(SIMPLE_NOTIFICATION_ID);
        }
        if (!ListenerUtil.mutListener.listen(5601)) {
            // Show any pending dialogs which were stored persistently
            mHandler.readMessage();
        }
    }

    @Override
    protected void onDestroy() {
        if (!ListenerUtil.mutListener.listen(5602)) {
            Timber.i("AnkiActivity::onDestroy - %s", mActivityName);
        }
        if (!ListenerUtil.mutListener.listen(5603)) {
            super.onDestroy();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (!ListenerUtil.mutListener.listen(5606)) {
            if (item.getItemId() == android.R.id.home) {
                if (!ListenerUtil.mutListener.listen(5604)) {
                    Timber.i("Home button pressed");
                }
                if (!ListenerUtil.mutListener.listen(5605)) {
                    finishWithoutAnimation();
                }
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    // called when the CollectionLoader finishes... usually will be over-ridden
    protected void onCollectionLoaded(Collection col) {
        if (!ListenerUtil.mutListener.listen(5607)) {
            hideProgressBar();
        }
    }

    public Collection getCol() {
        return CollectionHelper.getInstance().getCol(this);
    }

    public boolean colIsOpen() {
        return CollectionHelper.getInstance().colIsOpen();
    }

    public boolean animationDisabled() {
        SharedPreferences preferences = AnkiDroidApp.getSharedPrefs(this);
        return preferences.getBoolean("safeDisplay", false);
    }

    public boolean animationEnabled() {
        return !animationDisabled();
    }

    @Override
    public void setContentView(View view) {
        if (!ListenerUtil.mutListener.listen(5609)) {
            if (animationDisabled()) {
                if (!ListenerUtil.mutListener.listen(5608)) {
                    view.clearAnimation();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(5610)) {
            super.setContentView(view);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!ListenerUtil.mutListener.listen(5618)) {
            // We can't access the icons yet on a TV, so show them all in the menu
            if (AndroidUiUtils.isRunningOnTv(this)) {
                if (!ListenerUtil.mutListener.listen(5617)) {
                    {
                        long _loopCounter105 = 0;
                        for (int i = 0; (ListenerUtil.mutListener.listen(5616) ? (i >= menu.size()) : (ListenerUtil.mutListener.listen(5615) ? (i <= menu.size()) : (ListenerUtil.mutListener.listen(5614) ? (i > menu.size()) : (ListenerUtil.mutListener.listen(5613) ? (i != menu.size()) : (ListenerUtil.mutListener.listen(5612) ? (i == menu.size()) : (i < menu.size())))))); i++) {
                            ListenerUtil.loopListener.listen("_loopCounter105", ++_loopCounter105);
                            if (!ListenerUtil.mutListener.listen(5611)) {
                                menu.getItem(i).setShowAsAction(MenuItem.SHOW_AS_ACTION_NEVER);
                            }
                        }
                    }
                }
            }
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void setContentView(View view, LayoutParams params) {
        if (!ListenerUtil.mutListener.listen(5620)) {
            if (animationDisabled()) {
                if (!ListenerUtil.mutListener.listen(5619)) {
                    view.clearAnimation();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(5621)) {
            super.setContentView(view, params);
        }
    }

    @Override
    public void addContentView(View view, LayoutParams params) {
        if (!ListenerUtil.mutListener.listen(5623)) {
            if (animationDisabled()) {
                if (!ListenerUtil.mutListener.listen(5622)) {
                    view.clearAnimation();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(5624)) {
            super.addContentView(view, params);
        }
    }

    @Deprecated
    @Override
    public void startActivity(Intent intent) {
        if (!ListenerUtil.mutListener.listen(5625)) {
            super.startActivity(intent);
        }
    }

    public void startActivityWithoutAnimation(Intent intent) {
        if (!ListenerUtil.mutListener.listen(5626)) {
            disableIntentAnimation(intent);
        }
        if (!ListenerUtil.mutListener.listen(5627)) {
            super.startActivity(intent);
        }
        if (!ListenerUtil.mutListener.listen(5628)) {
            disableActivityAnimation();
        }
    }

    public void startActivityWithAnimation(Intent intent, Direction animation) {
        if (!ListenerUtil.mutListener.listen(5629)) {
            enableIntentAnimation(intent);
        }
        if (!ListenerUtil.mutListener.listen(5630)) {
            super.startActivity(intent);
        }
        if (!ListenerUtil.mutListener.listen(5631)) {
            enableActivityAnimation(animation);
        }
    }

    @Deprecated
    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        try {
            if (!ListenerUtil.mutListener.listen(5633)) {
                super.startActivityForResult(intent, requestCode);
            }
        } catch (ActivityNotFoundException e) {
            if (!ListenerUtil.mutListener.listen(5632)) {
                UIUtils.showSimpleSnackbar(this, R.string.activity_start_failed, true);
            }
        }
    }

    public void startActivityForResultWithoutAnimation(Intent intent, int requestCode) {
        if (!ListenerUtil.mutListener.listen(5634)) {
            disableIntentAnimation(intent);
        }
        if (!ListenerUtil.mutListener.listen(5635)) {
            startActivityForResult(intent, requestCode);
        }
        if (!ListenerUtil.mutListener.listen(5636)) {
            disableActivityAnimation();
        }
    }

    public void startActivityForResultWithAnimation(Intent intent, int requestCode, Direction animation) {
        if (!ListenerUtil.mutListener.listen(5637)) {
            enableIntentAnimation(intent);
        }
        if (!ListenerUtil.mutListener.listen(5638)) {
            startActivityForResult(intent, requestCode);
        }
        if (!ListenerUtil.mutListener.listen(5639)) {
            enableActivityAnimation(animation);
        }
    }

    @Deprecated
    @Override
    public void finish() {
        if (!ListenerUtil.mutListener.listen(5640)) {
            super.finish();
        }
    }

    public void finishWithoutAnimation() {
        if (!ListenerUtil.mutListener.listen(5641)) {
            Timber.i("finishWithoutAnimation");
        }
        if (!ListenerUtil.mutListener.listen(5642)) {
            super.finish();
        }
        if (!ListenerUtil.mutListener.listen(5643)) {
            disableActivityAnimation();
        }
    }

    public void finishWithAnimation(Direction animation) {
        if (!ListenerUtil.mutListener.listen(5644)) {
            Timber.i("finishWithAnimation %s", animation);
        }
        if (!ListenerUtil.mutListener.listen(5645)) {
            super.finish();
        }
        if (!ListenerUtil.mutListener.listen(5646)) {
            enableActivityAnimation(animation);
        }
    }

    protected void disableViewAnimation(View view) {
        if (!ListenerUtil.mutListener.listen(5647)) {
            view.clearAnimation();
        }
    }

    protected void enableViewAnimation(View view, Animation animation) {
        if (!ListenerUtil.mutListener.listen(5650)) {
            if (animationDisabled()) {
                if (!ListenerUtil.mutListener.listen(5649)) {
                    disableViewAnimation(view);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(5648)) {
                    view.setAnimation(animation);
                }
            }
        }
    }

    /**
     * Finish Activity using FADE animation *
     */
    public static void finishActivityWithFade(Activity activity) {
        if (!ListenerUtil.mutListener.listen(5651)) {
            activity.finish();
        }
        if (!ListenerUtil.mutListener.listen(5652)) {
            ActivityTransitionAnimation.slide(activity, UP);
        }
    }

    private void disableIntentAnimation(Intent intent) {
        if (!ListenerUtil.mutListener.listen(5653)) {
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        }
    }

    private void disableActivityAnimation() {
        if (!ListenerUtil.mutListener.listen(5654)) {
            ActivityTransitionAnimation.slide(this, NONE);
        }
    }

    private void enableIntentAnimation(Intent intent) {
        if (!ListenerUtil.mutListener.listen(5656)) {
            if (animationDisabled()) {
                if (!ListenerUtil.mutListener.listen(5655)) {
                    disableIntentAnimation(intent);
                }
            }
        }
    }

    private void enableActivityAnimation(Direction animation) {
        if (!ListenerUtil.mutListener.listen(5659)) {
            if (animationDisabled()) {
                if (!ListenerUtil.mutListener.listen(5658)) {
                    disableActivityAnimation();
                }
            } else {
                if (!ListenerUtil.mutListener.listen(5657)) {
                    ActivityTransitionAnimation.slide(this, animation);
                }
            }
        }
    }

    // Method for loading the collection which is inherited by all AnkiActivitys
    public void startLoadingCollection() {
        if (!ListenerUtil.mutListener.listen(5660)) {
            Timber.d("AnkiActivity.startLoadingCollection()");
        }
        if (!ListenerUtil.mutListener.listen(5663)) {
            if (colIsOpen()) {
                if (!ListenerUtil.mutListener.listen(5661)) {
                    Timber.d("Synchronously calling onCollectionLoaded");
                }
                if (!ListenerUtil.mutListener.listen(5662)) {
                    onCollectionLoaded(getCol());
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(5664)) {
            // Open collection asynchronously if it hasn't already been opened
            showProgressBar();
        }
        if (!ListenerUtil.mutListener.listen(5665)) {
            CollectionLoader.load(this, col -> {
                if (col != null) {
                    Timber.d("Asynchronously calling onCollectionLoaded");
                    onCollectionLoaded(col);
                } else {
                    Intent deckPicker = new Intent(this, DeckPicker.class);
                    // don't currently do anything with this
                    deckPicker.putExtra("collectionLoadError", true);
                    deckPicker.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivityWithAnimation(deckPicker, LEFT);
                }
            });
        }
    }

    public void showProgressBar() {
        ProgressBar progressBar = findViewById(R.id.progress_bar);
        if (!ListenerUtil.mutListener.listen(5667)) {
            if (progressBar != null) {
                if (!ListenerUtil.mutListener.listen(5666)) {
                    progressBar.setVisibility(View.VISIBLE);
                }
            }
        }
    }

    public void hideProgressBar() {
        ProgressBar progressBar = findViewById(R.id.progress_bar);
        if (!ListenerUtil.mutListener.listen(5669)) {
            if (progressBar != null) {
                if (!ListenerUtil.mutListener.listen(5668)) {
                    progressBar.setVisibility(View.GONE);
                }
            }
        }
    }

    protected void mayOpenUrl(Uri url) {
        boolean success = mCustomTabActivityHelper.mayLaunchUrl(url, null, null);
        if (!ListenerUtil.mutListener.listen(5671)) {
            if (!success) {
                if (!ListenerUtil.mutListener.listen(5670)) {
                    Timber.w("Couldn't preload url: %s", url.toString());
                }
            }
        }
    }

    public void openUrl(Uri url) {
        if (!ListenerUtil.mutListener.listen(5673)) {
            // display the toast
            if (!AdaptionUtil.hasWebBrowser(this)) {
                if (!ListenerUtil.mutListener.listen(5672)) {
                    UIUtils.showThemedToast(this, getResources().getString(R.string.no_browser_notification) + url, false);
                }
                return;
            }
        }
        CustomTabActivityHelper helper = getCustomTabActivityHelper();
        CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder(helper.getSession());
        CustomTabColorSchemeParams colorScheme = new CustomTabColorSchemeParams.Builder().setToolbarColor(ContextCompat.getColor(this, R.color.material_light_blue_500)).build();
        if (!ListenerUtil.mutListener.listen(5674)) {
            builder.setDefaultColorSchemeParams(colorScheme).setShowTitle(true);
        }
        if (!ListenerUtil.mutListener.listen(5675)) {
            builder.setStartAnimations(this, R.anim.slide_right_in, R.anim.slide_left_out);
        }
        if (!ListenerUtil.mutListener.listen(5676)) {
            builder.setExitAnimations(this, R.anim.slide_left_in, R.anim.slide_right_out);
        }
        if (!ListenerUtil.mutListener.listen(5677)) {
            builder.setCloseButtonIcon(BitmapFactory.decodeResource(this.getResources(), R.drawable.ic_arrow_back_white_24dp));
        }
        CustomTabsIntent customTabsIntent = builder.build();
        if (!ListenerUtil.mutListener.listen(5678)) {
            CustomTabsHelper.addKeepAliveExtra(this, customTabsIntent.intent);
        }
        if (!ListenerUtil.mutListener.listen(5679)) {
            CustomTabActivityHelper.openCustomTab(this, customTabsIntent, url, new CustomTabsFallback());
        }
    }

    public CustomTabActivityHelper getCustomTabActivityHelper() {
        return mCustomTabActivityHelper;
    }

    /**
     * Global method to show dialog fragment including adding it to back stack Note: DO NOT call this from an async
     * task! If you need to show a dialog from an async task, use showAsyncDialogFragment()
     *
     * @param newFragment  the DialogFragment you want to show
     */
    public void showDialogFragment(DialogFragment newFragment) {
        if (!ListenerUtil.mutListener.listen(5680)) {
            showDialogFragment(this, newFragment);
        }
    }

    public static void showDialogFragment(AnkiActivity activity, DialogFragment newFragment) {
        // dialog, so make our own transaction and take care of that here.
        FragmentManager manager = activity.getSupportFragmentManager();
        FragmentTransaction ft = manager.beginTransaction();
        Fragment prev = manager.findFragmentByTag("dialog");
        if (!ListenerUtil.mutListener.listen(5682)) {
            if (prev != null) {
                if (!ListenerUtil.mutListener.listen(5681)) {
                    ft.remove(prev);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(5683)) {
            // save transaction to the back stack
            ft.addToBackStack("dialog");
        }
        if (!ListenerUtil.mutListener.listen(5684)) {
            newFragment.show(ft, "dialog");
        }
        if (!ListenerUtil.mutListener.listen(5685)) {
            manager.executePendingTransactions();
        }
    }

    /**
     * Calls {@link #showAsyncDialogFragment(AsyncDialogFragment, NotificationChannels.Channel)} internally, using the channel
     * {@link NotificationChannels.Channel#GENERAL}
     *
     * @param newFragment  the AsyncDialogFragment you want to show
     */
    public void showAsyncDialogFragment(AsyncDialogFragment newFragment) {
        if (!ListenerUtil.mutListener.listen(5686)) {
            showAsyncDialogFragment(newFragment, NotificationChannels.Channel.GENERAL);
        }
    }

    /**
     * Global method to show a dialog fragment including adding it to back stack and handling the case where the dialog
     * is shown from an async task, by showing the message in the notification bar if the activity was stopped before the
     * AsyncTask completed
     *
     * @param newFragment  the AsyncDialogFragment you want to show
     * @param channel the NotificationChannels.Channel to use for the notification
     */
    public void showAsyncDialogFragment(AsyncDialogFragment newFragment, NotificationChannels.Channel channel) {
        try {
            if (!ListenerUtil.mutListener.listen(5689)) {
                showDialogFragment(newFragment);
            }
        } catch (IllegalStateException e) {
            if (!ListenerUtil.mutListener.listen(5687)) {
                // Store a persistent message to SharedPreferences instructing AnkiDroid to show dialog
                DialogHandler.storeMessage(newFragment.getDialogHandlerMessage());
            }
            // Show a basic notification to the user in the notification bar in the meantime
            String title = newFragment.getNotificationTitle();
            String message = newFragment.getNotificationMessage();
            if (!ListenerUtil.mutListener.listen(5688)) {
                showSimpleNotification(title, message, channel);
            }
        }
    }

    /**
     * Show a simple message dialog, dismissing the message without taking any further action when OK button is pressed.
     * If a DialogFragment cannot be shown due to the Activity being stopped then the message is shown in the
     * notification bar instead.
     *
     * @param message
     */
    protected void showSimpleMessageDialog(String message) {
        if (!ListenerUtil.mutListener.listen(5690)) {
            showSimpleMessageDialog(message, false);
        }
    }

    protected void showSimpleMessageDialog(String title, String message) {
        if (!ListenerUtil.mutListener.listen(5691)) {
            showSimpleMessageDialog(title, message, false);
        }
    }

    /**
     * Show a simple message dialog, dismissing the message without taking any further action when OK button is pressed.
     * If a DialogFragment cannot be shown due to the Activity being stopped then the message is shown in the
     * notification bar instead.
     *
     * @param message
     * @param reload flag which forces app to be restarted when true
     */
    protected void showSimpleMessageDialog(String message, boolean reload) {
        AsyncDialogFragment newFragment = SimpleMessageDialog.newInstance(message, reload);
        if (!ListenerUtil.mutListener.listen(5692)) {
            showAsyncDialogFragment(newFragment);
        }
    }

    protected void showSimpleMessageDialog(String title, @Nullable String message, boolean reload) {
        AsyncDialogFragment newFragment = SimpleMessageDialog.newInstance(title, message, reload);
        if (!ListenerUtil.mutListener.listen(5693)) {
            showAsyncDialogFragment(newFragment);
        }
    }

    public void showSimpleNotification(String title, String message, NotificationChannels.Channel channel) {
        SharedPreferences prefs = AnkiDroidApp.getSharedPrefs(this);
        if (!ListenerUtil.mutListener.listen(5708)) {
            // Show a notification unless all notifications have been totally disabled
            if ((ListenerUtil.mutListener.listen(5698) ? (Integer.parseInt(prefs.getString("minimumCardsDueForNotification", "0")) >= Preferences.PENDING_NOTIFICATIONS_ONLY) : (ListenerUtil.mutListener.listen(5697) ? (Integer.parseInt(prefs.getString("minimumCardsDueForNotification", "0")) > Preferences.PENDING_NOTIFICATIONS_ONLY) : (ListenerUtil.mutListener.listen(5696) ? (Integer.parseInt(prefs.getString("minimumCardsDueForNotification", "0")) < Preferences.PENDING_NOTIFICATIONS_ONLY) : (ListenerUtil.mutListener.listen(5695) ? (Integer.parseInt(prefs.getString("minimumCardsDueForNotification", "0")) != Preferences.PENDING_NOTIFICATIONS_ONLY) : (ListenerUtil.mutListener.listen(5694) ? (Integer.parseInt(prefs.getString("minimumCardsDueForNotification", "0")) == Preferences.PENDING_NOTIFICATIONS_ONLY) : (Integer.parseInt(prefs.getString("minimumCardsDueForNotification", "0")) <= Preferences.PENDING_NOTIFICATIONS_ONLY))))))) {
                // Use the title as the ticker unless the title is simply "AnkiDroid"
                String ticker = title;
                if (!ListenerUtil.mutListener.listen(5700)) {
                    if (title.equals(getResources().getString(R.string.app_name))) {
                        if (!ListenerUtil.mutListener.listen(5699)) {
                            ticker = message;
                        }
                    }
                }
                // Build basic notification
                NotificationCompat.Builder builder = new NotificationCompat.Builder(this, NotificationChannels.getId(channel)).setSmallIcon(R.drawable.ic_stat_notify).setContentTitle(title).setContentText(message).setColor(ContextCompat.getColor(this, R.color.material_light_blue_500)).setStyle(new NotificationCompat.BigTextStyle().bigText(message)).setVisibility(NotificationCompat.VISIBILITY_PUBLIC).setTicker(ticker);
                if (!ListenerUtil.mutListener.listen(5702)) {
                    // Enable vibrate and blink if set in preferences
                    if (prefs.getBoolean("widgetVibrate", false)) {
                        if (!ListenerUtil.mutListener.listen(5701)) {
                            builder.setVibrate(new long[] { 1000, 1000, 1000 });
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(5704)) {
                    if (prefs.getBoolean("widgetBlink", false)) {
                        if (!ListenerUtil.mutListener.listen(5703)) {
                            builder.setLights(Color.BLUE, 1000, 1000);
                        }
                    }
                }
                // Creates an explicit intent for an Activity in your app
                Intent resultIntent = new Intent(this, DeckPicker.class);
                if (!ListenerUtil.mutListener.listen(5705)) {
                    resultIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                }
                PendingIntent resultPendingIntent = PendingIntent.getActivity(this, 0, resultIntent, PendingIntent.FLAG_UPDATE_CURRENT);
                if (!ListenerUtil.mutListener.listen(5706)) {
                    builder.setContentIntent(resultPendingIntent);
                }
                NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                if (!ListenerUtil.mutListener.listen(5707)) {
                    // mId allows you to update the notification later on.
                    notificationManager.notify(SIMPLE_NOTIFICATION_ID, builder.build());
                }
            }
        }
    }

    public DialogHandler getDialogHandler() {
        return mHandler;
    }

    // Handle closing simple message dialog
    @Override
    public void dismissSimpleMessageDialog(boolean reload) {
        if (!ListenerUtil.mutListener.listen(5709)) {
            dismissAllDialogFragments();
        }
        if (!ListenerUtil.mutListener.listen(5712)) {
            if (reload) {
                Intent deckPicker = new Intent(this, DeckPicker.class);
                if (!ListenerUtil.mutListener.listen(5710)) {
                    deckPicker.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                }
                if (!ListenerUtil.mutListener.listen(5711)) {
                    startActivityWithoutAnimation(deckPicker);
                }
            }
        }
    }

    // Dismiss whatever dialog is showing
    public void dismissAllDialogFragments() {
        if (!ListenerUtil.mutListener.listen(5713)) {
            getSupportFragmentManager().popBackStack("dialog", FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }
    }

    // Restart the activity
    public void restartActivity() {
        if (!ListenerUtil.mutListener.listen(5714)) {
            Timber.i("AnkiActivity -- restartActivity()");
        }
        Intent intent = new Intent();
        if (!ListenerUtil.mutListener.listen(5715)) {
            intent.setClass(this, this.getClass());
        }
        if (!ListenerUtil.mutListener.listen(5716)) {
            intent.putExtras(new Bundle());
        }
        if (!ListenerUtil.mutListener.listen(5717)) {
            this.startActivityWithoutAnimation(intent);
        }
        if (!ListenerUtil.mutListener.listen(5718)) {
            this.finishWithoutAnimation();
        }
    }

    protected void enableToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        if (!ListenerUtil.mutListener.listen(5720)) {
            if (toolbar != null) {
                if (!ListenerUtil.mutListener.listen(5719)) {
                    setSupportActionBar(toolbar);
                }
            }
        }
    }

    protected void enableToolbar(@Nullable View view) {
        if (!ListenerUtil.mutListener.listen(5722)) {
            if (view == null) {
                if (!ListenerUtil.mutListener.listen(5721)) {
                    Timber.w("Unable to enable toolbar - invalid view supplied");
                }
                return;
            }
        }
        Toolbar toolbar = view.findViewById(R.id.toolbar);
        if (!ListenerUtil.mutListener.listen(5724)) {
            if (toolbar != null) {
                if (!ListenerUtil.mutListener.listen(5723)) {
                    setSupportActionBar(toolbar);
                }
            }
        }
    }

    protected boolean showedActivityFailedScreen(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(5725)) {
            if (!AnkiDroidApp.isInitialized()) {
                return false;
            }
        }
        if (!ListenerUtil.mutListener.listen(5726)) {
            Timber.w("Activity started with no application instance");
        }
        if (!ListenerUtil.mutListener.listen(5727)) {
            UIUtils.showThemedToast(this, getString(R.string.ankidroid_cannot_open_after_backup_try_again), false);
        }
        if (!ListenerUtil.mutListener.listen(5728)) {
            // Avoids a SuperNotCalledException
            super.onCreate(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(5729)) {
            finishActivityWithFade(this);
        }
        if (!ListenerUtil.mutListener.listen(5730)) {
            // If we don't kill the process, the backup is not "done" and reopening the app show the same message.
            new Thread(() -> {
                // Same as the default value of LENGTH_LONG
                try {
                    Thread.sleep(3500);
                } catch (InterruptedException e) {
                    Timber.w(e);
                }
                android.os.Process.killProcess(android.os.Process.myPid());
            }).start();
        }
        return true;
    }
}
