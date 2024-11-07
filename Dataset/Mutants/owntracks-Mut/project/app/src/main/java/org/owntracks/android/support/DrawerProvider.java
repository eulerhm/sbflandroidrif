package org.owntracks.android.support;

import android.app.Activity;
import android.content.Intent;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.SecondarySwitchDrawerItem;
import org.owntracks.android.R;
import org.owntracks.android.injection.scopes.PerActivity;
import org.owntracks.android.services.BackgroundService;
import org.owntracks.android.services.worker.Scheduler;
import org.owntracks.android.ui.base.BaseActivity;
import org.owntracks.android.ui.contacts.ContactsActivity;
import org.owntracks.android.ui.map.MapActivity;
import org.owntracks.android.ui.preferences.PreferencesActivity;
import org.owntracks.android.ui.regions.RegionsActivity;
import org.owntracks.android.ui.status.StatusActivity;
import javax.inject.Inject;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

@PerActivity
public class DrawerProvider {

    private static final int COLOR_ICON_PRIMARY = R.color.md_light_primary_icon;

    private static final int COLOR_ICON_PRIMARY_ACTIVE = R.color.md_blue_600;

    private static final int COLOR_ICON_SECONDARY = R.color.md_light_secondary;

    private static final int COLOR_ICON_SECONDARY_ACTIVE = COLOR_ICON_PRIMARY_ACTIVE;

    private static final int EXIT_OPERATION_ID = 88296;

    private final AppCompatActivity activity;

    private final Scheduler scheduler;

    @Inject
    public DrawerProvider(AppCompatActivity activity, Scheduler scheduler) {
        this.activity = activity;
        this.scheduler = scheduler;
    }

    private AppCompatActivity getActivity() {
        return activity;
    }

    private PrimaryDrawerItem drawerItemForClass(AppCompatActivity activeActivity, Class<?> targetActivityClass, @StringRes int targetActivityTitleRessource, @DrawableRes int iconResource) {
        return new PrimaryDrawerItem().withName(activeActivity.getString(targetActivityTitleRessource)).withSelectable(false).withSelectedTextColorRes(COLOR_ICON_PRIMARY_ACTIVE).withIcon(iconResource).withIconColorRes(COLOR_ICON_PRIMARY).withIconTintingEnabled(true).withSelectedIconColorRes(COLOR_ICON_PRIMARY_ACTIVE).withTag(targetActivityClass).withIdentifier(targetActivityClass.hashCode());
    }

    private SecondaryDrawerItem secondaryDrawerItemForClass(AppCompatActivity activeActivity, Class<?> targetActivityClass, @StringRes int targetActivityTitleResource, @DrawableRes int iconResource) {
        SecondaryDrawerItem sdi = new SecondaryDrawerItem();
        if (!ListenerUtil.mutListener.listen(1242)) {
            sdi.withName(activeActivity.getString(targetActivityTitleResource));
        }
        if (!ListenerUtil.mutListener.listen(1243)) {
            sdi.withIcon(iconResource);
        }
        if (!ListenerUtil.mutListener.listen(1244)) {
            sdi.withIconColorRes(COLOR_ICON_SECONDARY);
        }
        if (!ListenerUtil.mutListener.listen(1245)) {
            sdi.withSelectedIconColorRes(COLOR_ICON_SECONDARY_ACTIVE);
        }
        if (!ListenerUtil.mutListener.listen(1246)) {
            sdi.withIconTintingEnabled(true);
        }
        if (!ListenerUtil.mutListener.listen(1250)) {
            // The exit operation has no target class
            if (targetActivityClass != null) {
                if (!ListenerUtil.mutListener.listen(1248)) {
                    sdi.withTag(targetActivityClass);
                }
                if (!ListenerUtil.mutListener.listen(1249)) {
                    sdi.withIdentifier(targetActivityClass.hashCode());
                }
            } else {
                if (!ListenerUtil.mutListener.listen(1247)) {
                    sdi.withIdentifier(EXIT_OPERATION_ID);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(1251)) {
            sdi.withSelectable(false);
        }
        return sdi;
    }

    public void attach(@NonNull Toolbar toolbar) {
        if (!ListenerUtil.mutListener.listen(1252)) {
            new DrawerBuilder().withActivity(activity).withToolbar(toolbar).withStickyFooterShadow(false).withStickyFooterDivider(true).addDrawerItems(drawerItemForClass(activity, MapActivity.class, R.string.title_activity_map, R.drawable.ic_baseline_layers_24), drawerItemForClass(activity, ContactsActivity.class, R.string.title_activity_contacts, R.drawable.ic_baseline_supervisor_account_24), drawerItemForClass(activity, RegionsActivity.class, R.string.title_activity_regions, R.drawable.ic_baseline_adjust_24)).addStickyDrawerItems(secondaryDrawerItemForClass(activity, StatusActivity.class, R.string.title_activity_status, R.drawable.ic_baseline_info_24), secondaryDrawerItemForClass(activity, PreferencesActivity.class, R.string.title_activity_preferences, R.drawable.ic_baseline_settings_24), secondaryDrawerItemForClass(activity, null, R.string.title_exit, R.drawable.ic_baseline_power_settings_new_24)).withOnDrawerItemClickListener((view, position, drawerItem) -> {
                if (drawerItem == null)
                    return false;
                if (drawerItem instanceof SecondarySwitchDrawerItem)
                    return true;
                // Finish when exit app drawer option selected
                if (drawerItem.getIdentifier() == EXIT_OPERATION_ID) {
                    // Stop the background service
                    activity.stopService((new Intent(activity, BackgroundService.class)));
                    // Finish the activity
                    activity.finishAffinity();
                    // Kill scheduled tasks
                    scheduler.cancelAllTasks();
                    return true;
                }
                Class<BaseActivity> targetclass = (Class<BaseActivity>) drawerItem.getTag();
                if (activity.getClass() == targetclass) {
                    return false;
                }
                startActivity(targetclass);
                // return false to enable withCloseOnClick
                return false;
            }).build();
        }
    }

    private void startActivity(@NonNull Class<? extends Activity> activityClass) {
        Activity activity = getActivity();
        Intent intent = new Intent(activity, activityClass);
        if (!ListenerUtil.mutListener.listen(1253)) {
            activity.startActivity(intent);
        }
    }
}
