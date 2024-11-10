package com.google.android.gnd.ui.offlinebasemap.viewer;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.navigation.NavArgs;
import java.lang.IllegalArgumentException;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.HashMap;

public class OfflineAreaViewerFragmentArgs implements NavArgs {
  private final HashMap arguments = new HashMap();

  private OfflineAreaViewerFragmentArgs() {
  }

  @SuppressWarnings("unchecked")
  private OfflineAreaViewerFragmentArgs(HashMap argumentsMap) {
    this.arguments.putAll(argumentsMap);
  }

  @NonNull
  @SuppressWarnings("unchecked")
  public static OfflineAreaViewerFragmentArgs fromBundle(@NonNull Bundle bundle) {
    OfflineAreaViewerFragmentArgs __result = new OfflineAreaViewerFragmentArgs();
    bundle.setClassLoader(OfflineAreaViewerFragmentArgs.class.getClassLoader());
    if (bundle.containsKey("offlineAreaId")) {
      String offlineAreaId;
      offlineAreaId = bundle.getString("offlineAreaId");
      if (offlineAreaId == null) {
        throw new IllegalArgumentException("Argument \"offlineAreaId\" is marked as non-null but was passed a null value.");
      }
      __result.arguments.put("offlineAreaId", offlineAreaId);
    } else {
      throw new IllegalArgumentException("Required argument \"offlineAreaId\" is missing and does not have an android:defaultValue");
    }
    return __result;
  }

  @SuppressWarnings("unchecked")
  @NonNull
  public String getOfflineAreaId() {
    return (String) arguments.get("offlineAreaId");
  }

  @SuppressWarnings("unchecked")
  @NonNull
  public Bundle toBundle() {
    Bundle __result = new Bundle();
    if (arguments.containsKey("offlineAreaId")) {
      String offlineAreaId = (String) arguments.get("offlineAreaId");
      __result.putString("offlineAreaId", offlineAreaId);
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
    OfflineAreaViewerFragmentArgs that = (OfflineAreaViewerFragmentArgs) object;
    if (arguments.containsKey("offlineAreaId") != that.arguments.containsKey("offlineAreaId")) {
      return false;
    }
    if (getOfflineAreaId() != null ? !getOfflineAreaId().equals(that.getOfflineAreaId()) : that.getOfflineAreaId() != null) {
      return false;
    }
    return true;
  }

  @Override
  public int hashCode() {
    int result = 1;
    result = 31 * result + (getOfflineAreaId() != null ? getOfflineAreaId().hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    return "OfflineAreaViewerFragmentArgs{"
        + "offlineAreaId=" + getOfflineAreaId()
        + "}";
  }

  public static class Builder {
    private final HashMap arguments = new HashMap();

    @SuppressWarnings("unchecked")
    public Builder(OfflineAreaViewerFragmentArgs original) {
      this.arguments.putAll(original.arguments);
    }

    @SuppressWarnings("unchecked")
    public Builder(@NonNull String offlineAreaId) {
      if (offlineAreaId == null) {
        throw new IllegalArgumentException("Argument \"offlineAreaId\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("offlineAreaId", offlineAreaId);
    }

    @NonNull
    public OfflineAreaViewerFragmentArgs build() {
      OfflineAreaViewerFragmentArgs result = new OfflineAreaViewerFragmentArgs(arguments);
      return result;
    }

    @NonNull
    @SuppressWarnings("unchecked")
    public Builder setOfflineAreaId(@NonNull String offlineAreaId) {
      if (offlineAreaId == null) {
        throw new IllegalArgumentException("Argument \"offlineAreaId\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("offlineAreaId", offlineAreaId);
      return this;
    }

    @SuppressWarnings("unchecked")
    @NonNull
    public String getOfflineAreaId() {
      return (String) arguments.get("offlineAreaId");
    }
  }
}
