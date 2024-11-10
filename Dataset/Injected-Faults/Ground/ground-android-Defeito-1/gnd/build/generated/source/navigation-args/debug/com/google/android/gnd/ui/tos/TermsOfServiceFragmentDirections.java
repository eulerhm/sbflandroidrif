package com.google.android.gnd.ui.tos;

import androidx.annotation.NonNull;
import androidx.navigation.ActionOnlyNavDirections;
import androidx.navigation.NavDirections;
import com.google.android.gnd.NavGraphDirections;
import com.google.android.gnd.R;

public class TermsOfServiceFragmentDirections {
  private TermsOfServiceFragmentDirections() {
  }

  @NonNull
  public static NavDirections proceedToHomeScreen() {
    return new ActionOnlyNavDirections(R.id.proceedToHomeScreen);
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
