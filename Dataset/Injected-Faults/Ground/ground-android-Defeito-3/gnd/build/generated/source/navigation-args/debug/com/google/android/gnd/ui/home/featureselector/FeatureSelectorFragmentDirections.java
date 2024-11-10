package com.google.android.gnd.ui.home.featureselector;

import androidx.annotation.NonNull;
import androidx.navigation.NavDirections;
import com.google.android.gnd.NavGraphDirections;

public class FeatureSelectorFragmentDirections {
  private FeatureSelectorFragmentDirections() {
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
