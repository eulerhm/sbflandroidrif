package com.health.openscale.gui.preferences;

import android.os.Bundle;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.navigation.NavDirections;
import com.health.openscale.MobileNavigationDirections;
import com.health.openscale.R;
import java.io.Serializable;
import java.lang.IllegalArgumentException;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.HashMap;

public class UsersPreferencesDirections {
  private UsersPreferencesDirections() {
  }

  @NonNull
  public static ActionNavUserPreferencesToNavUsersettings actionNavUserPreferencesToNavUsersettings(
      ) {
    return new ActionNavUserPreferencesToNavUsersettings();
  }

  @NonNull
  public static MobileNavigationDirections.ActionNavMobileNavigationToNavDataentry actionNavMobileNavigationToNavDataentry(
      ) {
    return MobileNavigationDirections.actionNavMobileNavigationToNavDataentry();
  }

  @NonNull
  public static MobileNavigationDirections.ActionNavMobileNavigationToNavUsersettings actionNavMobileNavigationToNavUsersettings(
      ) {
    return MobileNavigationDirections.actionNavMobileNavigationToNavUsersettings();
  }

  public static class ActionNavUserPreferencesToNavUsersettings implements NavDirections {
    private final HashMap arguments = new HashMap();

    private ActionNavUserPreferencesToNavUsersettings() {
    }

    @NonNull
    public ActionNavUserPreferencesToNavUsersettings setMode(
        @NonNull UserSettingsFragment.USER_SETTING_MODE mode) {
      if (mode == null) {
        throw new IllegalArgumentException("Argument \"mode\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("mode", mode);
      return this;
    }

    @NonNull
    public ActionNavUserPreferencesToNavUsersettings setUserId(int userId) {
      this.arguments.put("userId", userId);
      return this;
    }

    @NonNull
    public ActionNavUserPreferencesToNavUsersettings setTitle(@NonNull String title) {
      if (title == null) {
        throw new IllegalArgumentException("Argument \"title\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("title", title);
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    @NonNull
    public Bundle getArguments() {
      Bundle __result = new Bundle();
      if (arguments.containsKey("mode")) {
        UserSettingsFragment.USER_SETTING_MODE mode = (UserSettingsFragment.USER_SETTING_MODE) arguments.get("mode");
        if (Parcelable.class.isAssignableFrom(UserSettingsFragment.USER_SETTING_MODE.class) || mode == null) {
          __result.putParcelable("mode", Parcelable.class.cast(mode));
        } else if (Serializable.class.isAssignableFrom(UserSettingsFragment.USER_SETTING_MODE.class)) {
          __result.putSerializable("mode", Serializable.class.cast(mode));
        } else {
          throw new UnsupportedOperationException(UserSettingsFragment.USER_SETTING_MODE.class.getName() + " must implement Parcelable or Serializable or must be an Enum.");
        }
      } else {
        __result.putSerializable("mode", UserSettingsFragment.USER_SETTING_MODE.ADD);
      }
      if (arguments.containsKey("userId")) {
        int userId = (int) arguments.get("userId");
        __result.putInt("userId", userId);
      } else {
        __result.putInt("userId", -1);
      }
      if (arguments.containsKey("title")) {
        String title = (String) arguments.get("title");
        __result.putString("title", title);
      } else {
        __result.putString("title", " ");
      }
      return __result;
    }

    @Override
    public int getActionId() {
      return R.id.action_nav_user_preferences_to_nav_usersettings;
    }

    @SuppressWarnings("unchecked")
    @NonNull
    public UserSettingsFragment.USER_SETTING_MODE getMode() {
      return (UserSettingsFragment.USER_SETTING_MODE) arguments.get("mode");
    }

    @SuppressWarnings("unchecked")
    public int getUserId() {
      return (int) arguments.get("userId");
    }

    @SuppressWarnings("unchecked")
    @NonNull
    public String getTitle() {
      return (String) arguments.get("title");
    }

    @Override
    public boolean equals(Object object) {
      if (this == object) {
          return true;
      }
      if (object == null || getClass() != object.getClass()) {
          return false;
      }
      ActionNavUserPreferencesToNavUsersettings that = (ActionNavUserPreferencesToNavUsersettings) object;
      if (arguments.containsKey("mode") != that.arguments.containsKey("mode")) {
        return false;
      }
      if (getMode() != null ? !getMode().equals(that.getMode()) : that.getMode() != null) {
        return false;
      }
      if (arguments.containsKey("userId") != that.arguments.containsKey("userId")) {
        return false;
      }
      if (getUserId() != that.getUserId()) {
        return false;
      }
      if (arguments.containsKey("title") != that.arguments.containsKey("title")) {
        return false;
      }
      if (getTitle() != null ? !getTitle().equals(that.getTitle()) : that.getTitle() != null) {
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
      result = 31 * result + (getMode() != null ? getMode().hashCode() : 0);
      result = 31 * result + getUserId();
      result = 31 * result + (getTitle() != null ? getTitle().hashCode() : 0);
      result = 31 * result + getActionId();
      return result;
    }

    @Override
    public String toString() {
      return "ActionNavUserPreferencesToNavUsersettings(actionId=" + getActionId() + "){"
          + "mode=" + getMode()
          + ", userId=" + getUserId()
          + ", title=" + getTitle()
          + "}";
    }
  }
}
