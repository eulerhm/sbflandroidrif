package com.health.openscale.gui.preferences;

import androidx.annotation.NonNull;
import androidx.navigation.ActionOnlyNavDirections;
import androidx.navigation.NavDirections;
import com.health.openscale.MobileNavigationDirections;
import com.health.openscale.R;

public class MeasurementPreferencesDirections {
  private MeasurementPreferencesDirections() {
  }

  @NonNull
  public static NavDirections actionNavMeasurementPreferencesToNavMeasurementDetailPreferences() {
    return new ActionOnlyNavDirections(R.id.action_nav_measurement_preferences_to_nav_measurement_detail_preferences);
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
