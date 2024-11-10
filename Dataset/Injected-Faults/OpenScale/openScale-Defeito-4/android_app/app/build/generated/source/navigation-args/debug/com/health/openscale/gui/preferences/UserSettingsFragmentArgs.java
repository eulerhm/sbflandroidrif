package com.health.openscale.gui.preferences;

import android.os.Bundle;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.navigation.NavArgs;
import java.io.Serializable;
import java.lang.IllegalArgumentException;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.HashMap;

public class UserSettingsFragmentArgs implements NavArgs {
  private final HashMap arguments = new HashMap();

  private UserSettingsFragmentArgs() {
  }

  private UserSettingsFragmentArgs(HashMap argumentsMap) {
    this.arguments.putAll(argumentsMap);
  }

  @NonNull
  @SuppressWarnings("unchecked")
  public static UserSettingsFragmentArgs fromBundle(@NonNull Bundle bundle) {
    UserSettingsFragmentArgs __result = new UserSettingsFragmentArgs();
    bundle.setClassLoader(UserSettingsFragmentArgs.class.getClassLoader());
    if (bundle.containsKey("mode")) {
      UserSettingsFragment.USER_SETTING_MODE mode;
      if (Parcelable.class.isAssignableFrom(UserSettingsFragment.USER_SETTING_MODE.class) || Serializable.class.isAssignableFrom(UserSettingsFragment.USER_SETTING_MODE.class)) {
        mode = (UserSettingsFragment.USER_SETTING_MODE) bundle.get("mode");
      } else {
        throw new UnsupportedOperationException(UserSettingsFragment.USER_SETTING_MODE.class.getName() + " must implement Parcelable or Serializable or must be an Enum.");
      }
      if (mode == null) {
        throw new IllegalArgumentException("Argument \"mode\" is marked as non-null but was passed a null value.");
      }
      __result.arguments.put("mode", mode);
    } else {
      __result.arguments.put("mode", UserSettingsFragment.USER_SETTING_MODE.ADD);
    }
    if (bundle.containsKey("userId")) {
      int userId;
      userId = bundle.getInt("userId");
      __result.arguments.put("userId", userId);
    } else {
      __result.arguments.put("userId", -1);
    }
    if (bundle.containsKey("title")) {
      String title;
      title = bundle.getString("title");
      if (title == null) {
        throw new IllegalArgumentException("Argument \"title\" is marked as non-null but was passed a null value.");
      }
      __result.arguments.put("title", title);
    } else {
      __result.arguments.put("title", " ");
    }
    return __result;
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

  @SuppressWarnings("unchecked")
  @NonNull
  public Bundle toBundle() {
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
  public boolean equals(Object object) {
    if (this == object) {
        return true;
    }
    if (object == null || getClass() != object.getClass()) {
        return false;
    }
    UserSettingsFragmentArgs that = (UserSettingsFragmentArgs) object;
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
    return true;
  }

  @Override
  public int hashCode() {
    int result = 1;
    result = 31 * result + (getMode() != null ? getMode().hashCode() : 0);
    result = 31 * result + getUserId();
    result = 31 * result + (getTitle() != null ? getTitle().hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    return "UserSettingsFragmentArgs{"
        + "mode=" + getMode()
        + ", userId=" + getUserId()
        + ", title=" + getTitle()
        + "}";
  }

  public static class Builder {
    private final HashMap arguments = new HashMap();

    public Builder(UserSettingsFragmentArgs original) {
      this.arguments.putAll(original.arguments);
    }

    public Builder() {
    }

    @NonNull
    public UserSettingsFragmentArgs build() {
      UserSettingsFragmentArgs result = new UserSettingsFragmentArgs(arguments);
      return result;
    }

    @NonNull
    public Builder setMode(@NonNull UserSettingsFragment.USER_SETTING_MODE mode) {
      if (mode == null) {
        throw new IllegalArgumentException("Argument \"mode\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("mode", mode);
      return this;
    }

    @NonNull
    public Builder setUserId(int userId) {
      this.arguments.put("userId", userId);
      return this;
    }

    @NonNull
    public Builder setTitle(@NonNull String title) {
      if (title == null) {
        throw new IllegalArgumentException("Argument \"title\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("title", title);
      return this;
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
  }
}
