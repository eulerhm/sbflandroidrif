package com.google.android.gnd;

import androidx.annotation.NonNull;
import androidx.navigation.ActionOnlyNavDirections;
import androidx.navigation.NavDirections;

public class NavGraphDirections {
  private NavGraphDirections() {
  }

  @NonNull
  public static NavDirections showSignInScreen() {
    return new ActionOnlyNavDirections(R.id.showSignInScreen);
  }

  @NonNull
  public static NavDirections showHomeScreen() {
    return new ActionOnlyNavDirections(R.id.showHomeScreen);
  }
}
