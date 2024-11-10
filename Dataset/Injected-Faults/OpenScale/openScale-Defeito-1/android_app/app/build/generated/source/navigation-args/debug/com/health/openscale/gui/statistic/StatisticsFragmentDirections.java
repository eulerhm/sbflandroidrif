package com.health.openscale.gui.statistic;

import androidx.annotation.NonNull;
import com.health.openscale.MobileNavigationDirections;

public class StatisticsFragmentDirections {
  private StatisticsFragmentDirections() {
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
