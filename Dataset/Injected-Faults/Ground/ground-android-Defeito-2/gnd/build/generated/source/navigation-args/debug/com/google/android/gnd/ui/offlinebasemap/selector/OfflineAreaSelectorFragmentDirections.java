package com.google.android.gnd.ui.offlinebasemap.selector;

import androidx.annotation.NonNull;
import androidx.navigation.ActionOnlyNavDirections;
import androidx.navigation.NavDirections;
import com.google.android.gnd.NavGraphDirections;
import com.google.android.gnd.R;

public class OfflineAreaSelectorFragmentDirections {
  private OfflineAreaSelectorFragmentDirections() {
  }

  @NonNull
  public static NavDirections backToOfflineAreas() {
    return new ActionOnlyNavDirections(R.id.backToOfflineAreas);
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
