package com.google.android.gnd.ui.submissiondetails;

import android.os.Bundle;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.NavDirections;
import com.google.android.gnd.NavGraphDirections;
import com.google.android.gnd.R;
import java.io.Serializable;
import java.lang.IllegalArgumentException;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.HashMap;

public class SubmissionDetailsFragmentDirections {
  private SubmissionDetailsFragmentDirections() {
  }

  @NonNull
  public static EditSubmission editSubmission(@NonNull String surveyId, @NonNull String featureId,
      @NonNull String submissionId) {
    return new EditSubmission(surveyId, featureId, submissionId);
  }

  @NonNull
  public static NavDirections showSignInScreen() {
    return NavGraphDirections.showSignInScreen();
  }

  @NonNull
  public static NavDirections showHomeScreen() {
    return NavGraphDirections.showHomeScreen();
  }

  public static class EditSubmission implements NavDirections {
    private final HashMap arguments = new HashMap();

    @SuppressWarnings("unchecked")
    private EditSubmission(@NonNull String surveyId, @NonNull String featureId,
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
    @SuppressWarnings("unchecked")
    public EditSubmission setTaskId(@NonNull String taskId) {
      if (taskId == null) {
        throw new IllegalArgumentException("Argument \"taskId\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("taskId", taskId);
      return this;
    }

    @NonNull
    @SuppressWarnings("unchecked")
    public EditSubmission setSurveyId(@NonNull String surveyId) {
      if (surveyId == null) {
        throw new IllegalArgumentException("Argument \"surveyId\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("surveyId", surveyId);
      return this;
    }

    @NonNull
    @SuppressWarnings("unchecked")
    public EditSubmission setFeatureId(@NonNull String featureId) {
      if (featureId == null) {
        throw new IllegalArgumentException("Argument \"featureId\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("featureId", featureId);
      return this;
    }

    @NonNull
    @SuppressWarnings("unchecked")
    public EditSubmission setSubmissionId(@NonNull String submissionId) {
      if (submissionId == null) {
        throw new IllegalArgumentException("Argument \"submissionId\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("submissionId", submissionId);
      return this;
    }

    @NonNull
    @SuppressWarnings("unchecked")
    public EditSubmission setRestoredResponses(@Nullable HashMap restoredResponses) {
      this.arguments.put("restoredResponses", restoredResponses);
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    @NonNull
    public Bundle getArguments() {
      Bundle __result = new Bundle();
      if (arguments.containsKey("taskId")) {
        String taskId = (String) arguments.get("taskId");
        __result.putString("taskId", taskId);
      } else {
        __result.putString("taskId", "N/A");
      }
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
    public int getActionId() {
      return R.id.editSubmission;
    }

    @SuppressWarnings("unchecked")
    @NonNull
    public String getTaskId() {
      return (String) arguments.get("taskId");
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
    @Nullable
    public HashMap getRestoredResponses() {
      return (HashMap) arguments.get("restoredResponses");
    }

    @Override
    public boolean equals(Object object) {
      if (this == object) {
          return true;
      }
      if (object == null || getClass() != object.getClass()) {
          return false;
      }
      EditSubmission that = (EditSubmission) object;
      if (arguments.containsKey("taskId") != that.arguments.containsKey("taskId")) {
        return false;
      }
      if (getTaskId() != null ? !getTaskId().equals(that.getTaskId()) : that.getTaskId() != null) {
        return false;
      }
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
      if (arguments.containsKey("restoredResponses") != that.arguments.containsKey("restoredResponses")) {
        return false;
      }
      if (getRestoredResponses() != null ? !getRestoredResponses().equals(that.getRestoredResponses()) : that.getRestoredResponses() != null) {
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
      result = 31 * result + (getTaskId() != null ? getTaskId().hashCode() : 0);
      result = 31 * result + (getSurveyId() != null ? getSurveyId().hashCode() : 0);
      result = 31 * result + (getFeatureId() != null ? getFeatureId().hashCode() : 0);
      result = 31 * result + (getSubmissionId() != null ? getSubmissionId().hashCode() : 0);
      result = 31 * result + (getRestoredResponses() != null ? getRestoredResponses().hashCode() : 0);
      result = 31 * result + getActionId();
      return result;
    }

    @Override
    public String toString() {
      return "EditSubmission(actionId=" + getActionId() + "){"
          + "taskId=" + getTaskId()
          + ", surveyId=" + getSurveyId()
          + ", featureId=" + getFeatureId()
          + ", submissionId=" + getSubmissionId()
          + ", restoredResponses=" + getRestoredResponses()
          + "}";
    }
  }
}
