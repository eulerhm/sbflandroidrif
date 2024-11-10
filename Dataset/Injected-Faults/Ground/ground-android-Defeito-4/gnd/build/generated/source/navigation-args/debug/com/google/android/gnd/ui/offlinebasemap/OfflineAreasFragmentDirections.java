package com.google.android.gnd.ui.offlinebasemap;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.navigation.ActionOnlyNavDirections;
import androidx.navigation.NavDirections;
import com.google.android.gnd.NavGraphDirections;
import com.google.android.gnd.R;
import java.lang.IllegalArgumentException;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.HashMap;

public class OfflineAreasFragmentDirections {
  private OfflineAreasFragmentDirections() {
  }

  @NonNull
  public static NavDirections showOfflineAreaSelector() {
    return new ActionOnlyNavDirections(R.id.showOfflineAreaSelector);
  }

  @NonNull
  public static NavDirections backToHomeScreen() {
    return new ActionOnlyNavDirections(R.id.backToHomeScreen);
  }

  @NonNull
  public static ViewOfflineArea viewOfflineArea(@NonNull String offlineAreaId) {
    return new ViewOfflineArea(offlineAreaId);
  }

  @NonNull
  public static NavDirections showSignInScreen() {
    return NavGraphDirections.showSignInScreen();
  }

  @NonNull
  public static NavDirections showHomeScreen() {
    return NavGraphDirections.showHomeScreen();
  }

  public static class ViewOfflineArea implements NavDirections {
    private final HashMap arguments = new HashMap();

    @SuppressWarnings("unchecked")
    private ViewOfflineArea(@NonNull String offlineAreaId) {
      if (offlineAreaId == null) {
        throw new IllegalArgumentException("Argument \"offlineAreaId\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("offlineAreaId", offlineAreaId);
    }

    @NonNull
    @SuppressWarnings("unchecked")
    public ViewOfflineArea setOfflineAreaId(@NonNull String offlineAreaId) {
      if (offlineAreaId == null) {
        throw new IllegalArgumentException("Argument \"offlineAreaId\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("offlineAreaId", offlineAreaId);
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    @NonNull
    public Bundle getArguments() {
      Bundle __result = new Bundle();
      if (arguments.containsKey("offlineAreaId")) {
        String offlineAreaId = (String) arguments.get("offlineAreaId");
        __result.putString("offlineAreaId", offlineAreaId);
      }
      return __result;
    }

    @Override
    public int getActionId() {
      return R.id.viewOfflineArea;
    }

    @SuppressWarnings("unchecked")
    @NonNull
    public String getOfflineAreaId() {
      return (String) arguments.get("offlineAreaId");
    }

    @Override
    public boolean equals(Object object) {
      if (this == object) {
          return true;
      }
      if (object == null || getClass() != object.getClass()) {
          return false;
      }
      ViewOfflineArea that = (ViewOfflineArea) object;
      if (arguments.containsKey("offlineAreaId") != that.arguments.containsKey("offlineAreaId")) {
        return false;
      }
      if (getOfflineAreaId() != null ? !getOfflineAreaId().equals(that.getOfflineAreaId()) : that.getOfflineAreaId() != null) {
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
      result = 31 * result + (getOfflineAreaId() != null ? getOfflineAreaId().hashCode() : 0);
      result = 31 * result + getActionId();
      return result;
    }

    @Override
    public String toString() {
      return "ViewOfflineArea(actionId=" + getActionId() + "){"
          + "offlineAreaId=" + getOfflineAreaId()
          + "}";
    }
  }
}
