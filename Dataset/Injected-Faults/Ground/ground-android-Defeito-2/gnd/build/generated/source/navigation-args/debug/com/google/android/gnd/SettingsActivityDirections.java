package com.google.android.gnd;

import androidx.annotation.NonNull;
import androidx.navigation.NavDirections;

public class SettingsActivityDirections {
  private SettingsActivityDirections() {
  }

  @NonNull
  public static NavDirections showSignInScreen() {
    return NavGraphDirections.showSignInScreen();
  }

  @NonNull
  public static NavDirections showHomeScreen() {
    return NavGraphDirections.showHomeScreen();
  }
}
