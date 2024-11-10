package com.google.android.gnd.ui.tos;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.NavArgs;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.HashMap;

public class TermsOfServiceFragmentArgs implements NavArgs {
  private final HashMap arguments = new HashMap();

  private TermsOfServiceFragmentArgs() {
  }

  @SuppressWarnings("unchecked")
  private TermsOfServiceFragmentArgs(HashMap argumentsMap) {
    this.arguments.putAll(argumentsMap);
  }

  @NonNull
  @SuppressWarnings("unchecked")
  public static TermsOfServiceFragmentArgs fromBundle(@NonNull Bundle bundle) {
    TermsOfServiceFragmentArgs __result = new TermsOfServiceFragmentArgs();
    bundle.setClassLoader(TermsOfServiceFragmentArgs.class.getClassLoader());
    if (bundle.containsKey("termsOfServiceText")) {
      String termsOfServiceText;
      termsOfServiceText = bundle.getString("termsOfServiceText");
      __result.arguments.put("termsOfServiceText", termsOfServiceText);
    } else {
      __result.arguments.put("termsOfServiceText", "");
    }
    return __result;
  }

  @SuppressWarnings("unchecked")
  @Nullable
  public String getTermsOfServiceText() {
    return (String) arguments.get("termsOfServiceText");
  }

  @SuppressWarnings("unchecked")
  @NonNull
  public Bundle toBundle() {
    Bundle __result = new Bundle();
    if (arguments.containsKey("termsOfServiceText")) {
      String termsOfServiceText = (String) arguments.get("termsOfServiceText");
      __result.putString("termsOfServiceText", termsOfServiceText);
    } else {
      __result.putString("termsOfServiceText", "");
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
    TermsOfServiceFragmentArgs that = (TermsOfServiceFragmentArgs) object;
    if (arguments.containsKey("termsOfServiceText") != that.arguments.containsKey("termsOfServiceText")) {
      return false;
    }
    if (getTermsOfServiceText() != null ? !getTermsOfServiceText().equals(that.getTermsOfServiceText()) : that.getTermsOfServiceText() != null) {
      return false;
    }
    return true;
  }

  @Override
  public int hashCode() {
    int result = 1;
    result = 31 * result + (getTermsOfServiceText() != null ? getTermsOfServiceText().hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    return "TermsOfServiceFragmentArgs{"
        + "termsOfServiceText=" + getTermsOfServiceText()
        + "}";
  }

  public static class Builder {
    private final HashMap arguments = new HashMap();

    @SuppressWarnings("unchecked")
    public Builder(TermsOfServiceFragmentArgs original) {
      this.arguments.putAll(original.arguments);
    }

    public Builder() {
    }

    @NonNull
    public TermsOfServiceFragmentArgs build() {
      TermsOfServiceFragmentArgs result = new TermsOfServiceFragmentArgs(arguments);
      return result;
    }

    @NonNull
    @SuppressWarnings("unchecked")
    public Builder setTermsOfServiceText(@Nullable String termsOfServiceText) {
      this.arguments.put("termsOfServiceText", termsOfServiceText);
      return this;
    }

    @SuppressWarnings("unchecked")
    @Nullable
    public String getTermsOfServiceText() {
      return (String) arguments.get("termsOfServiceText");
    }
  }
}
