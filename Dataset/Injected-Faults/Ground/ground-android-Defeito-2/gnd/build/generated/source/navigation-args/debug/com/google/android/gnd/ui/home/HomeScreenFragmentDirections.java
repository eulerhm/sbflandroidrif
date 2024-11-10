package com.google.android.gnd.ui.home;

import android.os.Bundle;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.ActionOnlyNavDirections;
import androidx.navigation.NavDirections;
import com.google.android.gnd.NavGraphDirections;
import com.google.android.gnd.R;
import com.google.android.gnd.ui.map.MapType;
import java.io.Serializable;
import java.lang.IllegalArgumentException;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.HashMap;

public class HomeScreenFragmentDirections {
  private HomeScreenFragmentDirections() {
  }

  @NonNull
  public static ShowSubmissionDetails showSubmissionDetails(@NonNull String surveyId,
      @NonNull String featureId, @NonNull String submissionId) {
    return new ShowSubmissionDetails(surveyId, featureId, submissionId);
  }

  @NonNull
  public static AddSubmission addSubmission(@NonNull String surveyId, @NonNull String featureId,
      @NonNull String taskId) {
    return new AddSubmission(surveyId, featureId, taskId);
  }

  @NonNull
  public static NavDirections fromHomeScreenToSignInScreen() {
    return new ActionOnlyNavDirections(R.id.fromHomeScreenToSignInScreen);
  }

  @NonNull
  public static NavDirections showSyncStatus() {
    return new ActionOnlyNavDirections(R.id.show_sync_status);
  }

  @NonNull
  public static NavDirections showOfflineAreas() {
    return new ActionOnlyNavDirections(R.id.showOfflineAreas);
  }

  @NonNull
  public static NavDirections actionHomeScreenFragmentToSettingsActivity() {
    return new ActionOnlyNavDirections(R.id.action_home_screen_fragment_to_settings_activity);
  }

  @NonNull
  public static NavDirections actionHomeScreenFragmentToProjectSelectorDialogFragment() {
    return new ActionOnlyNavDirections(R.id.action_home_screen_fragment_to_projectSelectorDialogFragment);
  }

  @NonNull
  public static NavDirections actionHomeScreenFragmentToDataCollectionFragment() {
    return new ActionOnlyNavDirections(R.id.action_home_screen_fragment_to_dataCollectionFragment);
  }

  @NonNull
  public static NavDirections actionHomeScreenFragmentToFeatureSelectorFragment() {
    return new ActionOnlyNavDirections(R.id.action_home_screen_fragment_to_featureSelectorFragment);
  }

  @NonNull
  public static ActionHomeScreenFragmentToMapTypeDialogFragment actionHomeScreenFragmentToMapTypeDialogFragment(
      @NonNull MapType[] mapTypes) {
    return new ActionHomeScreenFragmentToMapTypeDialogFragment(mapTypes);
  }

  @NonNull
  public static NavDirections showSignInScreen() {
    return NavGraphDirections.showSignInScreen();
  }

  @NonNull
  public static NavDirections showHomeScreen() {
    return NavGraphDirections.showHomeScreen();
  }

  public static class ShowSubmissionDetails implements NavDirections {
    private final HashMap arguments = new HashMap();

    @SuppressWarnings("unchecked")
    private ShowSubmissionDetails(@NonNull String surveyId, @NonNull String featureId,
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
    public ShowSubmissionDetails setSurveyId(@NonNull String surveyId) {
      if (surveyId == null) {
        throw new IllegalArgumentException("Argument \"surveyId\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("surveyId", surveyId);
      return this;
    }

    @NonNull
    @SuppressWarnings("unchecked")
    public ShowSubmissionDetails setFeatureId(@NonNull String featureId) {
      if (featureId == null) {
        throw new IllegalArgumentException("Argument \"featureId\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("featureId", featureId);
      return this;
    }

    @NonNull
    @SuppressWarnings("unchecked")
    public ShowSubmissionDetails setSubmissionId(@NonNull String submissionId) {
      if (submissionId == null) {
        throw new IllegalArgumentException("Argument \"submissionId\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("submissionId", submissionId);
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    @NonNull
    public Bundle getArguments() {
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
    public int getActionId() {
      return R.id.showSubmissionDetails;
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

    @Override
    public boolean equals(Object object) {
      if (this == object) {
          return true;
      }
      if (object == null || getClass() != object.getClass()) {
          return false;
      }
      ShowSubmissionDetails that = (ShowSubmissionDetails) object;
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
      if (getActionId() != that.getActionId()) {
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
      result = 31 * result + getActionId();
      return result;
    }

    @Override
    public String toString() {
      return "ShowSubmissionDetails(actionId=" + getActionId() + "){"
          + "surveyId=" + getSurveyId()
          + ", featureId=" + getFeatureId()
          + ", submissionId=" + getSubmissionId()
          + "}";
    }
  }

  public static class AddSubmission implements NavDirections {
    private final HashMap arguments = new HashMap();

    @SuppressWarnings("unchecked")
    private AddSubmission(@NonNull String surveyId, @NonNull String featureId,
        @NonNull String taskId) {
      if (surveyId == null) {
        throw new IllegalArgumentException("Argument \"surveyId\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("surveyId", surveyId);
      if (featureId == null) {
        throw new IllegalArgumentException("Argument \"featureId\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("featureId", featureId);
      if (taskId == null) {
        throw new IllegalArgumentException("Argument \"taskId\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("taskId", taskId);
    }

    @NonNull
    @SuppressWarnings("unchecked")
    public AddSubmission setSubmissionId(@NonNull String submissionId) {
      if (submissionId == null) {
        throw new IllegalArgumentException("Argument \"submissionId\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("submissionId", submissionId);
      return this;
    }

    @NonNull
    @SuppressWarnings("unchecked")
    public AddSubmission setSurveyId(@NonNull String surveyId) {
      if (surveyId == null) {
        throw new IllegalArgumentException("Argument \"surveyId\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("surveyId", surveyId);
      return this;
    }

    @NonNull
    @SuppressWarnings("unchecked")
    public AddSubmission setFeatureId(@NonNull String featureId) {
      if (featureId == null) {
        throw new IllegalArgumentException("Argument \"featureId\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("featureId", featureId);
      return this;
    }

    @NonNull
    @SuppressWarnings("unchecked")
    public AddSubmission setTaskId(@NonNull String taskId) {
      if (taskId == null) {
        throw new IllegalArgumentException("Argument \"taskId\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("taskId", taskId);
      return this;
    }

    @NonNull
    @SuppressWarnings("unchecked")
    public AddSubmission setRestoredResponses(@Nullable HashMap restoredResponses) {
      this.arguments.put("restoredResponses", restoredResponses);
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    @NonNull
    public Bundle getArguments() {
      Bundle __result = new Bundle();
      if (arguments.containsKey("submissionId")) {
        String submissionId = (String) arguments.get("submissionId");
        __result.putString("submissionId", submissionId);
      } else {
        __result.putString("submissionId", "");
      }
      if (arguments.containsKey("surveyId")) {
        String surveyId = (String) arguments.get("surveyId");
        __result.putString("surveyId", surveyId);
      }
      if (arguments.containsKey("featureId")) {
        String featureId = (String) arguments.get("featureId");
        __result.putString("featureId", featureId);
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
    public int getActionId() {
      return R.id.addSubmission;
    }

    @SuppressWarnings("unchecked")
    @NonNull
    public String getSubmissionId() {
      return (String) arguments.get("submissionId");
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
    public String getTaskId() {
      return (String) arguments.get("taskId");
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
      AddSubmission that = (AddSubmission) object;
      if (arguments.containsKey("submissionId") != that.arguments.containsKey("submissionId")) {
        return false;
      }
      if (getSubmissionId() != null ? !getSubmissionId().equals(that.getSubmissionId()) : that.getSubmissionId() != null) {
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
      if (getActionId() != that.getActionId()) {
        return false;
      }
      return true;
    }

    @Override
    public int hashCode() {
      int result = 1;
      result = 31 * result + (getSubmissionId() != null ? getSubmissionId().hashCode() : 0);
      result = 31 * result + (getSurveyId() != null ? getSurveyId().hashCode() : 0);
      result = 31 * result + (getFeatureId() != null ? getFeatureId().hashCode() : 0);
      result = 31 * result + (getTaskId() != null ? getTaskId().hashCode() : 0);
      result = 31 * result + (getRestoredResponses() != null ? getRestoredResponses().hashCode() : 0);
      result = 31 * result + getActionId();
      return result;
    }

    @Override
    public String toString() {
      return "AddSubmission(actionId=" + getActionId() + "){"
          + "submissionId=" + getSubmissionId()
          + ", surveyId=" + getSurveyId()
          + ", featureId=" + getFeatureId()
          + ", taskId=" + getTaskId()
          + ", restoredResponses=" + getRestoredResponses()
          + "}";
    }
  }

  public static class ActionHomeScreenFragmentToMapTypeDialogFragment implements NavDirections {
    private final HashMap arguments = new HashMap();

    @SuppressWarnings("unchecked")
    private ActionHomeScreenFragmentToMapTypeDialogFragment(@NonNull MapType[] mapTypes) {
      if (mapTypes == null) {
        throw new IllegalArgumentException("Argument \"mapTypes\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("mapTypes", mapTypes);
    }

    @NonNull
    @SuppressWarnings("unchecked")
    public ActionHomeScreenFragmentToMapTypeDialogFragment setMapTypes(
        @NonNull MapType[] mapTypes) {
      if (mapTypes == null) {
        throw new IllegalArgumentException("Argument \"mapTypes\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("mapTypes", mapTypes);
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    @NonNull
    public Bundle getArguments() {
      Bundle __result = new Bundle();
      if (arguments.containsKey("mapTypes")) {
        MapType[] mapTypes = (MapType[]) arguments.get("mapTypes");
        __result.putParcelableArray("mapTypes", mapTypes);
      }
      return __result;
    }

    @Override
    public int getActionId() {
      return R.id.action_home_screen_fragment_to_mapTypeDialogFragment;
    }

    @SuppressWarnings("unchecked")
    @NonNull
    public MapType[] getMapTypes() {
      return (MapType[]) arguments.get("mapTypes");
    }

    @Override
    public boolean equals(Object object) {
      if (this == object) {
          return true;
      }
      if (object == null || getClass() != object.getClass()) {
          return false;
      }
      ActionHomeScreenFragmentToMapTypeDialogFragment that = (ActionHomeScreenFragmentToMapTypeDialogFragment) object;
      if (arguments.containsKey("mapTypes") != that.arguments.containsKey("mapTypes")) {
        return false;
      }
      if (getMapTypes() != null ? !getMapTypes().equals(that.getMapTypes()) : that.getMapTypes() != null) {
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
      result = 31 * result + java.util.Arrays.hashCode(getMapTypes());
      result = 31 * result + getActionId();
      return result;
    }

    @Override
    public String toString() {
      return "ActionHomeScreenFragmentToMapTypeDialogFragment(actionId=" + getActionId() + "){"
          + "mapTypes=" + getMapTypes()
          + "}";
    }
  }
}
