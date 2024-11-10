package com.google.android.gnd.ui.submissiondetails;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.navigation.NavArgs;
import java.lang.IllegalArgumentException;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.HashMap;

public class SubmissionDetailsFragmentArgs implements NavArgs {
  private final HashMap arguments = new HashMap();

  private SubmissionDetailsFragmentArgs() {
  }

  @SuppressWarnings("unchecked")
  private SubmissionDetailsFragmentArgs(HashMap argumentsMap) {
    this.arguments.putAll(argumentsMap);
  }

  @NonNull
  @SuppressWarnings("unchecked")
  public static SubmissionDetailsFragmentArgs fromBundle(@NonNull Bundle bundle) {
    SubmissionDetailsFragmentArgs __result = new SubmissionDetailsFragmentArgs();
    bundle.setClassLoader(SubmissionDetailsFragmentArgs.class.getClassLoader());
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
    SubmissionDetailsFragmentArgs that = (SubmissionDetailsFragmentArgs) object;
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
    return true;
  }

  @Override
  public int hashCode() {
    int result = 1;
    result = 31 * result + (getSurveyId() != null ? getSurveyId().hashCode() : 0);
    result = 31 * result + (getFeatureId() != null ? getFeatureId().hashCode() : 0);
    result = 31 * result + (getSubmissionId() != null ? getSubmissionId().hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    return "SubmissionDetailsFragmentArgs{"
        + "surveyId=" + getSurveyId()
        + ", featureId=" + getFeatureId()
        + ", submissionId=" + getSubmissionId()
        + "}";
  }

  public static class Builder {
    private final HashMap arguments = new HashMap();

    @SuppressWarnings("unchecked")
    public Builder(SubmissionDetailsFragmentArgs original) {
      this.arguments.putAll(original.arguments);
    }

    @SuppressWarnings("unchecked")
    public Builder(@NonNull String surveyId, @NonNull String featureId,
        @NonNull String submissionId) {
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
    }

    @NonNull
    public SubmissionDetailsFragmentArgs build() {
      SubmissionDetailsFragmentArgs result = new SubmissionDetailsFragmentArgs(arguments);
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
  }
}
