package com.health.openscale;

import androidx.annotation.NonNull;
import androidx.navigation.ActionOnlyNavDirections;
import androidx.navigation.NavDirections;

public class SlideNavigationDirections {
  private SlideNavigationDirections() {
  }

  @NonNull
  public static NavDirections actionNavSlideNavigationToNavUsersettings() {
    return new ActionOnlyNavDirections(R.id.action_nav_slide_navigation_to_nav_usersettings);
  }

  @NonNull
  public static NavDirections actionNavSlideNavigationToNavBluetoothsettings() {
    return new ActionOnlyNavDirections(R.id.action_nav_slide_navigation_to_nav_bluetoothsettings);
  }
}
