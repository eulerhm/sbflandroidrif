package com.health.openscale.gui.preferences;

import androidx.annotation.NonNull;
import androidx.navigation.NavDirections;
import com.health.openscale.SlideNavigationDirections;

public class UserSettingsFragmentDirections {
  private UserSettingsFragmentDirections() {
  }

  @NonNull
  public static NavDirections actionNavSlideNavigationToNavUsersettings() {
    return SlideNavigationDirections.actionNavSlideNavigationToNavUsersettings();
  }

  @NonNull
  public static NavDirections actionNavSlideNavigationToNavBluetoothsettings() {
    return SlideNavigationDirections.actionNavSlideNavigationToNavBluetoothsettings();
  }
}
