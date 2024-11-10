package com.google.android.gnd.ui.startup;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.ActionOnlyNavDirections;
import androidx.navigation.NavDirections;
import com.google.android.gnd.NavGraphDirections;
import com.google.android.gnd.R;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.HashMap;

public class StartupFragmentDirections {
  private StartupFragmentDirections() {
  }

  @NonNull
  public static NavDirections proceedToSignInScreen() {
    return new ActionOnlyNavDirections(R.id.proceedToSignInScreen);
  }

  @NonNull
  public static NavDirections proceedToHomeScreen() {
    return new ActionOnlyNavDirections(R.id.proceedToHomeScreen);
  }

  @NonNull
  public static ShowTermsOfService showTermsOfService() {
    return new ShowTermsOfService();
  }

  @NonNull
  public static NavDirections showSignInScreen() {
    return NavGraphDirections.showSignInScreen();
  }

  @NonNull
  public static NavDirections showHomeScreen() {
    return NavGraphDirections.showHomeScreen();
  }

  public static class ShowTermsOfService implements NavDirections {
    private final HashMap arguments = new HashMap();

    private ShowTermsOfService() {
    }

    @NonNull
    @SuppressWarnings("unchecked")
    public ShowTermsOfService setTermsOfServiceText(@Nullable String termsOfServiceText) {
      this.arguments.put("termsOfServiceText", termsOfServiceText);
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    @NonNull
    public Bundle getArguments() {
      Bundle __result = new Bundle();
      if (arguments.containsKey("termsOfServiceText")) {
        String termsOfServiceText = (String) arguments.get("termsOfServiceText");
        __result.putString("termsOfServiceText", termsOfServiceText);
      } else {
        __result.putString("termsOfServiceText", "");
      }
      return __result;
    }

    @Override
    public int getActionId() {
      return R.id.showTermsOfService;
    }

    @SuppressWarnings("unchecked")
    @Nullable
    public String getTermsOfServiceText() {
      return (String) arguments.get("termsOfServiceText");
    }

    @Override
    public boolean equals(Object object) {
      if (this == object) {
          return true;
      }
      if (object == null || getClass() != object.getClass()) {
          return false;
      }
      ShowTermsOfService that = (ShowTermsOfService) object;
      if (arguments.containsKey("termsOfServiceText") != that.arguments.containsKey("termsOfServiceText")) {
        return false;
      }
      if (getTermsOfServiceText() != null ? !getTermsOfServiceText().equals(that.getTermsOfServiceText()) : that.getTermsOfServiceText() != null) {
        return false;
      }
      if (getActionId() != that.getActionId()) {
        return false;
      }
      return true;
    }

    @Override
    public int hashCode() {
      int result = 1;
      result = 31 * result + (getTermsOfServiceText() != null ? getTermsOfServiceText().hashCode() : 0);
      result = 31 * result + getActionId();
      return result;
    }

    @Override
    public String toString() {
      return "ShowTermsOfService(actionId=" + getActionId() + "){"
          + "termsOfServiceText=" + getTermsOfServiceText()
          + "}";
    }
  }
}
