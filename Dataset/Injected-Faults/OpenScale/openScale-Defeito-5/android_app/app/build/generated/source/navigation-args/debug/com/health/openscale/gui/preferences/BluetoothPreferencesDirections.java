package com.health.openscale.gui.preferences;

import androidx.annotation.NonNull;
import androidx.navigation.ActionOnlyNavDirections;
import androidx.navigation.NavDirections;
import com.health.openscale.MobileNavigationDirections;
import com.health.openscale.R;

public class BluetoothPreferencesDirections {
  private BluetoothPreferencesDirections() {
  }

  @NonNull
  public static NavDirections actionNavBluetoothPreferencesToNavBluetoothSettings() {
    return new ActionOnlyNavDirections(R.id.action_nav_bluetooth_preferences_to_nav_bluetooth_settings);
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
