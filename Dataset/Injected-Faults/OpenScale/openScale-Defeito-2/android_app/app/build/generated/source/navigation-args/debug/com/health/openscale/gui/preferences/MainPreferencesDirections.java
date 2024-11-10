package com.health.openscale.gui.preferences;

import androidx.annotation.NonNull;
import androidx.navigation.ActionOnlyNavDirections;
import androidx.navigation.NavDirections;
import com.health.openscale.MobileNavigationDirections;
import com.health.openscale.R;

public class MainPreferencesDirections {
  private MainPreferencesDirections() {
  }

  @NonNull
  public static NavDirections actionNavMainPreferencesToNavAboutPreferences() {
    return new ActionOnlyNavDirections(R.id.action_nav_main_preferences_to_nav_about_preferences);
  }

  @NonNull
  public static NavDirections actionNavMainPreferencesToNavReminderPreferences() {
    return new ActionOnlyNavDirections(R.id.action_nav_main_preferences_to_nav_reminder_preferences);
  }

  @NonNull
  public static NavDirections actionNavMainPreferencesToNavMeasurementPreferences() {
    return new ActionOnlyNavDirections(R.id.action_nav_main_preferences_to_nav_measurement_preferences);
  }

  @NonNull
  public static NavDirections actionNavMainPreferencesToNavBluetoothPreferences() {
    return new ActionOnlyNavDirections(R.id.action_nav_main_preferences_to_nav_bluetooth_preferences);
  }

  @NonNull
  public static NavDirections actionNavMainPreferencesToNavBackupPreferences() {
    return new ActionOnlyNavDirections(R.id.action_nav_main_preferences_to_nav_backup_preferences);
  }

  @NonNull
  public static NavDirections actionNavMainPreferencesToNavGeneralPreferences() {
    return new ActionOnlyNavDirections(R.id.action_nav_main_preferences_to_nav_general_preferences);
  }

  @NonNull
  public static NavDirections actionNavMainPreferencesToNavGraphPreferences() {
    return new ActionOnlyNavDirections(R.id.action_nav_main_preferences_to_nav_graph_preferences);
  }

  @NonNull
  public static NavDirections actionNavMainPreferencesToNavUserPreferences() {
    return new ActionOnlyNavDirections(R.id.action_nav_main_preferences_to_nav_user_preferences);
  }

  @NonNull
  public static MobileNavigationDirections.ActionNavMobileNavigationToNavDataentry actionNavMobileNavigationToNavDataentry(
      ) {
    return MobileNavigationDirections.actionNavMobileNavigationToNavDataentry();
  }

  @NonNull
  public static MobileNavigationDirections.ActionNavMobileNavigationToNavUsersettings actionNavMobileNavigationToNavUsersettings(
      ) {
    return MobileNavigationDirections.actionNavMobileNavigationToNavUsersettings();
  }
}
