package com.google.android.gnd.ui.editsubmission;

import android.os.Bundle;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.NavArgs;
import java.io.Serializable;
import java.lang.IllegalArgumentException;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.HashMap;

public class EditSubmissionFragmentArgs implements NavArgs {
  private final HashMap arguments = new HashMap();

  private EditSubmissionFragmentArgs() {
  }

  @SuppressWarnings("unchecked")
  private EditSubmissionFragmentArgs(HashMap argumentsMap) {
    this.arguments.putAll(argumentsMap);
  }

  @NonNull
  @SuppressWarnings("unchecked")
  public static EditSubmissionFragmentArgs fromBundle(@NonNull Bundle bundle) {
    EditSubmissionFragmentArgs __result = new EditSubmissionFragmentArgs();
    bundle.setClassLoader(EditSubmissionFragmentArgs.class.getClassLoader());
    if (bundle.containsKey("surveyId")) {
      String surveyId;
      surveyId = bundle.getString("surveyId");
      if (surveyId == null) {
        throw new IllegalArgumentException("Argument \"surveyId\" is marked as non-null but was passed a null value.");
      }
      __result.arguments.put("surveyId", surveyId);
    } else {
      throw new IllegalArgumentException("Required argument \"surveyId\" is missing and does not have an android:defaultValue");
    }
    if (bundle.containsKey("featureId")) {
      String featureId;
      featureId = bundle.getString("featureId");
      if (featureId == null) {
        throw new IllegalArgumentException("Argument \"featureId\" is marked as non-null but was passed a null value.");
      }
      __result.arguments.put("featureId", featureId);
    } else {
      throw new IllegalArgumentException("Required argument \"featureId\" is missing and does not have an android:defaultValue");
    }
    if (bundle.containsKey("submissionId")) {
      String submissionId;
      submissionId = bundle.getString("submissionId");
      if (submissionId == null) {
        throw new IllegalArgumentException("Argument \"submissionId\" is marked as non-null but was passed a null value.");
      }
      __result.arguments.put("submissionId", submissionId);
    } else {
      throw new IllegalArgumentException("Required argument \"submissionId\" is missing and does not have an android:defaultValue");
    }
    if (bundle.containsKey("taskId")) {
      String taskId;
      taskId = bundle.getString("taskId");
      if (taskId == null) {
        throw new IllegalArgumentException("Argument \"taskId\" is marked as non-null but was passed a null value.");
      }
      __result.arguments.put("taskId", taskId);
    } else {
      throw new IllegalArgumentException("Required argument \"taskId\" is missing and does not have an android:defaultValue");
    }
    if (bundle.containsKey("restoredResponses")) {
      HashMap restoredResponses;
      if (Parcelable.class.isAssignableFrom(HashMap.class) || Serializable.class.isAssignableFrom(HashMap.class)) {
        restoredResponses = (HashMap) bundle.get("restoredResponses");
      } else {
        throw new UnsupportedOperationException(HashMap.class.getName() + " must implement Parcelable or Serializable or must be an Enum.");
      }
      __result.arguments.put("restoredResponses", restoredResponses);
    } else {
      __result.arguments.put("restoredResponses", null);
    }
    return __result;
  }

  @SuppressWarnings("unchecked")
  @NonNull
  public String getSurveyId() {
    return (String) arguments.get("surveyId");
  }

  @SuppressWarnings("unchecked")
  @NonNull
  public String getFeatureId() {
    return (String) arguments.get("featureId");
  }

  @SuppressWarnings("unchecked")
  @NonNull
  public String getSubmissionId() {
    return (String) arguments.get("submissionId");
  }

  @SuppressWarnings("unchecked")
  @NonNull
  public String getTaskId() {
    return (String) arguments.get("taskId");
  }

  @SuppressWarnings("unchecked")
  @Nullable
  public HashMap getRestoredResponses() {
    return (HashMap) arguments.get("restoredResponses");
  }

  @SuppressWarnings("unchecked")
  @NonNull
  public Bundle toBundle() {
    Bundle __result = new Bundle();
    if (arguments.containsKey("surveyId")) {
      String surveyId = (String) arguments.get("surveyId");
      __result.putString("surveyId", surveyId);
    }
    if (arguments.containsKey("featureId")) {
      String featureId = (String) arguments.get("featureId");
      __result.putString("featureId", featureId);
    }
    if (arguments.containsKey("submissionId")) {
      String submissionId = (String) arguments.get("submissionId");
      __result.putString("submissionId", submissionId);
    }
    if (arguments.containsKey("taskId")) {
      String taskId = (String) arguments.get("taskId");
      __result.putString("taskId", taskId);
    }
    if (arguments.containsKey("restoredResponses")) {
      HashMap restoredResponses = (HashMap) arguments.get("restoredResponses");
      if (Parcelable.class.isAssignableFrom(HashMap.class) || restoredResponses == null) {
        __result.putParcelable("restoredResponses", Parcelable.class.cast(restoredResponses));
      } else if (Serializable.class.isAssignableFrom(HashMap.class)) {
        __result.putSerializable("restoredResponses", Serializable.class.cast(restoredResponses));
      } else {
        throw new UnsupportedOperationException(HashMap.class.getName() + " must implement Parcelable or Serializable or must be an Enum.");
      }
    } else {
      __result.putSerializable("restoredResponses", null);
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
    EditSubmissionFragmentArgs that = (EditSubmissionFragmentArgs) object;
    if (arguments.containsKey("surveyId") != that.arguments.containsKey("surveyId")) {
      return false;
    }
    if (getSurveyId() != null ? !getSurveyId().equals(that.getSurveyId()) : that.getSurveyId() != null) {
      return false;
    }
    if (arguments.containsKey("featureId") != that.arguments.containsKey("featureId")) {
      return false;
    }
    if (getFeatureId() != null ? !getFeatureId().equals(that.getFeatureId()) : that.getFeatureId() != null) {
      return false;
    }
    if (arguments.containsKey("submissionId") != that.arguments.containsKey("submissionId")) {
      return false;
    }
    if (getSubmissionId() != null ? !getSubmissionId().equals(that.getSubmissionId()) : that.getSubmissionId() != null) {
      return false;
    }
    if (arguments.containsKey("taskId") != that.arguments.containsKey("taskId")) {
      return false;
    }
    if (getTaskId() != null ? !getTaskId().equals(that.getTaskId()) : that.getTaskId() != null) {
      return false;
    }
    if (arguments.containsKey("restoredResponses") != that.arguments.containsKey("restoredResponses")) {
      return false;
    }
    if (getRestoredResponses() != null ? !getRestoredResponses().equals(that.getRestoredResponses()) : that.getRestoredResponses() != null) {
      return false;
    }
    return true;
  }

  @Override
  public int hashCode() {
    int result = 1;
    result = 31 * result + (getSurveyId() != null ? getSurveyId().hashCode() : 0);
    result = 31 * result + (getFeatureId() != null ? getFeatureId().hashCode() : 0);
    result = 31 * result + (getSubmissionId() != null ? getSubmissionId().hashCode() : 0);
    result = 31 * result + (getTaskId() != null ? getTaskId().hashCode() : 0);
    result = 31 * result + (getRestoredResponses() != null ? getRestoredResponses().hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    return "EditSubmissionFragmentArgs{"
        + "surveyId=" + getSurveyId()
        + ", featureId=" + getFeatureId()
        + ", submissionId=" + getSubmissionId()
        + ", taskId=" + getTaskId()
        + ", restoredResponses=" + getRestoredResponses()
        + "}";
  }

  public static class Builder {
    private final HashMap arguments = new HashMap();

    @SuppressWarnings("unchecked")
    public Builder(EditSubmissionFragmentArgs original) {
      this.arguments.putAll(original.arguments);
    }

    @SuppressWarnings("unchecked")
    public Builder(@NonNull String surveyId, @NonNull String featureId,
        @NonNull String submissionId, @NonNull String taskId) {
      if (surveyId == null) {
        throw new IllegalArgumentException("Argument \"surveyId\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("surveyId", surveyId);
      if (featureId == null) {
        throw new IllegalArgumentException("Argument \"featureId\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("featureId", featureId);
      if (submissionId == null) {
        throw new IllegalArgumentException("Argument \"submissionId\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("submissionId", submissionId);
      if (taskId == null) {
        throw new IllegalArgumentException("Argument \"taskId\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("taskId", taskId);
    }

    @NonNull
    public EditSubmissionFragmentArgs build() {
      EditSubmissionFragmentArgs result = new EditSubmissionFragmentArgs(arguments);
      return result;
    }

    @NonNull
    @SuppressWarnings("unchecked")
    public Builder setSurveyId(@NonNull String surveyId) {
      if (surveyId == null) {
        throw new IllegalArgumentException("Argument \"surveyId\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("surveyId", surveyId);
      return this;
    }

    @NonNull
    @SuppressWarnings("unchecked")
    public Builder setFeatureId(@NonNull String featureId) {
      if (featureId == null) {
        throw new IllegalArgumentException("Argument \"featureId\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("featureId", featureId);
      return this;
    }

    @NonNull
    @SuppressWarnings("unchecked")
    public Builder setSubmissionId(@NonNull String submissionId) {
      if (submissionId == null) {
        throw new IllegalArgumentException("Argument \"submissionId\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("submissionId", submissionId);
      return this;
    }

    @NonNull
    @SuppressWarnings("unchecked")
    public Builder setTaskId(@NonNull String taskId) {
      if (taskId == null) {
        throw new IllegalArgumentException("Argument \"taskId\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("taskId", taskId);
      return this;
    }

    @NonNull
    @SuppressWarnings("unchecked")
    public Builder setRestoredResponses(@Nullable HashMap restoredResponses) {
      this.arguments.put("restoredResponses", restoredResponses);
      return this;
    }

    @SuppressWarnings("unchecked")
    @NonNull
    public String getSurveyId() {
      return (String) arguments.get("surveyId");
    }

    @SuppressWarnings("unchecked")
    @NonNull
    public String getFeatureId() {
      return (String) arguments.get("featureId");
    }

    @SuppressWarnings("unchecked")
    @NonNull
    public String getSubmissionId() {
      return (String) arguments.get("submissionId");
    }

    @SuppressWarnings("unchecked")
    @NonNull
    public String getTaskId() {
      return (String) arguments.get("taskId");
    }

    @SuppressWarnings("unchecked")
    @Nullable
    public HashMap getRestoredResponses() {
      return (HashMap) arguments.get("restoredResponses");
    }
  }
}
