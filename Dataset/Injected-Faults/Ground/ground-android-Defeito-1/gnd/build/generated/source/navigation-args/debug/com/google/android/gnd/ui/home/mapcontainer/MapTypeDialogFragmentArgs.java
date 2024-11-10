package com.google.android.gnd.ui.home.mapcontainer;

import android.os.Bundle;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.navigation.NavArgs;
import com.google.android.gnd.ui.map.MapType;
import java.lang.IllegalArgumentException;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.lang.System;
import java.util.HashMap;

public class MapTypeDialogFragmentArgs implements NavArgs {
  private final HashMap arguments = new HashMap();

  private MapTypeDialogFragmentArgs() {
  }

  @SuppressWarnings("unchecked")
  private MapTypeDialogFragmentArgs(HashMap argumentsMap) {
    this.arguments.putAll(argumentsMap);
  }

  @NonNull
  @SuppressWarnings("unchecked")
  public static MapTypeDialogFragmentArgs fromBundle(@NonNull Bundle bundle) {
    MapTypeDialogFragmentArgs __result = new MapTypeDialogFragmentArgs();
    bundle.setClassLoader(MapTypeDialogFragmentArgs.class.getClassLoader());
    if (bundle.containsKey("mapTypes")) {
      MapType[] mapTypes;
      Parcelable[] __array = bundle.getParcelableArray("mapTypes");
      if (__array != null) {
        mapTypes = new MapType[__array.length];
        System.arraycopy(__array, 0, mapTypes, 0, __array.length);
      } else {
        mapTypes = null;
      }
      if (mapTypes == null) {
        throw new IllegalArgumentException("Argument \"mapTypes\" is marked as non-null but was passed a null value.");
      }
      __result.arguments.put("mapTypes", mapTypes);
    } else {
      throw new IllegalArgumentException("Required argument \"mapTypes\" is missing and does not have an android:defaultValue");
    }
    return __result;
  }

  @SuppressWarnings("unchecked")
  @NonNull
  public MapType[] getMapTypes() {
    return (MapType[]) arguments.get("mapTypes");
  }

  @SuppressWarnings("unchecked")
  @NonNull
  public Bundle toBundle() {
    Bundle __result = new Bundle();
    if (arguments.containsKey("mapTypes")) {
      MapType[] mapTypes = (MapType[]) arguments.get("mapTypes");
      __result.putParcelableArray("mapTypes", mapTypes);
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
    MapTypeDialogFragmentArgs that = (MapTypeDialogFragmentArgs) object;
    if (arguments.containsKey("mapTypes") != that.arguments.containsKey("mapTypes")) {
      return false;
    }
    if (getMapTypes() != null ? !getMapTypes().equals(that.getMapTypes()) : that.getMapTypes() != null) {
      return false;
    }
    return true;
  }

  @Override
  public int hashCode() {
    int result = 1;
    result = 31 * result + java.util.Arrays.hashCode(getMapTypes());
    return result;
  }

  @Override
  public String toString() {
    return "MapTypeDialogFragmentArgs{"
        + "mapTypes=" + getMapTypes()
        + "}";
  }

  public static class Builder {
    private final HashMap arguments = new HashMap();

    @SuppressWarnings("unchecked")
    public Builder(MapTypeDialogFragmentArgs original) {
      this.arguments.putAll(original.arguments);
    }

    @SuppressWarnings("unchecked")
    public Builder(@NonNull MapType[] mapTypes) {
      if (mapTypes == null) {
        throw new IllegalArgumentException("Argument \"mapTypes\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("mapTypes", mapTypes);
    }

    @NonNull
    public MapTypeDialogFragmentArgs build() {
      MapTypeDialogFragmentArgs result = new MapTypeDialogFragmentArgs(arguments);
      return result;
    }

    @NonNull
    @SuppressWarnings("unchecked")
    public Builder setMapTypes(@NonNull MapType[] mapTypes) {
      if (mapTypes == null) {
        throw new IllegalArgumentException("Argument \"mapTypes\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("mapTypes", mapTypes);
      return this;
    }

    @SuppressWarnings("unchecked")
    @NonNull
    public MapType[] getMapTypes() {
      return (MapType[]) arguments.get("mapTypes");
    }
  }
}
