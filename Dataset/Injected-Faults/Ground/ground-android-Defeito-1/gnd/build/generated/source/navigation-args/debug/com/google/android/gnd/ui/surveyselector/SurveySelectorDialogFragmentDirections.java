package com.google.android.gnd.ui.surveyselector;

import androidx.annotation.NonNull;
import androidx.navigation.NavDirections;
import com.google.android.gnd.NavGraphDirections;

public class SurveySelectorDialogFragmentDirections {
  private SurveySelectorDialogFragmentDirections() {
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
