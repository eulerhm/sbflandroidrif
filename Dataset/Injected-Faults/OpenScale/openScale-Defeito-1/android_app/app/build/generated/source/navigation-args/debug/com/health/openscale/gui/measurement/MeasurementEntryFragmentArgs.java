package com.health.openscale.gui.measurement;

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

public class MeasurementEntryFragmentArgs implements NavArgs {
  private final HashMap arguments = new HashMap();

  private MeasurementEntryFragmentArgs() {
  }

  private MeasurementEntryFragmentArgs(HashMap argumentsMap) {
    this.arguments.putAll(argumentsMap);
  }

  @NonNull
  @SuppressWarnings("unchecked")
  public static MeasurementEntryFragmentArgs fromBundle(@NonNull Bundle bundle) {
    MeasurementEntryFragmentArgs __result = new MeasurementEntryFragmentArgs();
    bundle.setClassLoader(MeasurementEntryFragmentArgs.class.getClassLoader());
    if (bundle.containsKey("mode")) {
      MeasurementEntryFragment.DATA_ENTRY_MODE mode;
      if (Parcelable.class.isAssignableFrom(MeasurementEntryFragment.DATA_ENTRY_MODE.class) || Serializable.class.isAssignableFrom(MeasurementEntryFragment.DATA_ENTRY_MODE.class)) {
        mode = (MeasurementEntryFragment.DATA_ENTRY_MODE) bundle.get("mode");
      } else {
        throw new UnsupportedOperationException(MeasurementEntryFragment.DATA_ENTRY_MODE.class.getName() + " must implement Parcelable or Serializable or must be an Enum.");
      }
      if (mode == null) {
        throw new IllegalArgumentException("Argument \"mode\" is marked as non-null but was passed a null value.");
      }
      __result.arguments.put("mode", mode);
    } else {
      __result.arguments.put("mode", MeasurementEntryFragment.DATA_ENTRY_MODE.VIEW);
    }
    if (bundle.containsKey("measurementId")) {
      int measurementId;
      measurementId = bundle.getInt("measurementId");
      __result.arguments.put("measurementId", measurementId);
    } else {
      __result.arguments.put("measurementId", -1);
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
  public MeasurementEntryFragment.DATA_ENTRY_MODE getMode() {
    return (MeasurementEntryFragment.DATA_ENTRY_MODE) arguments.get("mode");
  }

  @SuppressWarnings("unchecked")
  public int getMeasurementId() {
    return (int) arguments.get("measurementId");
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
      MeasurementEntryFragment.DATA_ENTRY_MODE mode = (MeasurementEntryFragment.DATA_ENTRY_MODE) arguments.get("mode");
      if (Parcelable.class.isAssignableFrom(MeasurementEntryFragment.DATA_ENTRY_MODE.class) || mode == null) {
        __result.putParcelable("mode", Parcelable.class.cast(mode));
      } else if (Serializable.class.isAssignableFrom(MeasurementEntryFragment.DATA_ENTRY_MODE.class)) {
        __result.putSerializable("mode", Serializable.class.cast(mode));
      } else {
        throw new UnsupportedOperationException(MeasurementEntryFragment.DATA_ENTRY_MODE.class.getName() + " must implement Parcelable or Serializable or must be an Enum.");
      }
    } else {
      __result.putSerializable("mode", MeasurementEntryFragment.DATA_ENTRY_MODE.VIEW);
    }
    if (arguments.containsKey("measurementId")) {
      int measurementId = (int) arguments.get("measurementId");
      __result.putInt("measurementId", measurementId);
    } else {
      __result.putInt("measurementId", -1);
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
    MeasurementEntryFragmentArgs that = (MeasurementEntryFragmentArgs) object;
    if (arguments.containsKey("mode") != that.arguments.containsKey("mode")) {
      return false;
    }
    if (getMode() != null ? !getMode().equals(that.getMode()) : that.getMode() != null) {
      return false;
    }
    if (arguments.containsKey("measurementId") != that.arguments.containsKey("measurementId")) {
      return false;
    }
    if (getMeasurementId() != that.getMeasurementId()) {
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
    result = 31 * result + getMeasurementId();
    result = 31 * result + (getTitle() != null ? getTitle().hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    return "MeasurementEntryFragmentArgs{"
        + "mode=" + getMode()
        + ", measurementId=" + getMeasurementId()
        + ", title=" + getTitle()
        + "}";
  }

  public static class Builder {
    private final HashMap arguments = new HashMap();

    public Builder(MeasurementEntryFragmentArgs original) {
      this.arguments.putAll(original.arguments);
    }

    public Builder() {
    }

    @NonNull
    public MeasurementEntryFragmentArgs build() {
      MeasurementEntryFragmentArgs result = new MeasurementEntryFragmentArgs(arguments);
      return result;
    }

    @NonNull
    public Builder setMode(@NonNull MeasurementEntryFragment.DATA_ENTRY_MODE mode) {
      if (mode == null) {
        throw new IllegalArgumentException("Argument \"mode\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("mode", mode);
      return this;
    }

    @NonNull
    public Builder setMeasurementId(int measurementId) {
      this.arguments.put("measurementId", measurementId);
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
    public MeasurementEntryFragment.DATA_ENTRY_MODE getMode() {
      return (MeasurementEntryFragment.DATA_ENTRY_MODE) arguments.get("mode");
    }

    @SuppressWarnings("unchecked")
    public int getMeasurementId() {
      return (int) arguments.get("measurementId");
    }

    @SuppressWarnings("unchecked")
    @NonNull
    public String getTitle() {
      return (String) arguments.get("title");
    }
  }
}
