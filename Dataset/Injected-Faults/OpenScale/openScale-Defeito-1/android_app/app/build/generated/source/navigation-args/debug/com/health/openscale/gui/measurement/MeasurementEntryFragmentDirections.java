package com.health.openscale.gui.measurement;

import androidx.annotation.NonNull;
import com.health.openscale.MobileNavigationDirections;

public class MeasurementEntryFragmentDirections {
  private MeasurementEntryFragmentDirections() {
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
