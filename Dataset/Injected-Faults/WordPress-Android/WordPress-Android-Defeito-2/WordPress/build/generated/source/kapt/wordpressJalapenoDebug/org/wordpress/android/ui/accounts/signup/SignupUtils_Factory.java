package org.wordpress.android.ui.accounts.signup;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;

@ScopeMetadata
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class SignupUtils_Factory implements Factory<SignupUtils> {
  @Override
  public SignupUtils get() {
    return newInstance();
  }

  public static SignupUtils_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static SignupUtils newInstance() {
    return new SignupUtils();
  }

  private static final class InstanceHolder {
    private static final SignupUtils_Factory INSTANCE = new SignupUtils_Factory();
  }
}
