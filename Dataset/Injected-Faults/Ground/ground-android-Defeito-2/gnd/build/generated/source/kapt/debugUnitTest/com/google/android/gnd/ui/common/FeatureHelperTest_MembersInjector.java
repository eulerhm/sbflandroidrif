package com.google.android.gnd.ui.common;

import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import javax.annotation.Generated;
import javax.inject.Provider;

@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class FeatureHelperTest_MembersInjector implements MembersInjector<FeatureHelperTest> {
  private final Provider<FeatureHelper> featureHelperProvider;

  public FeatureHelperTest_MembersInjector(Provider<FeatureHelper> featureHelperProvider) {
    this.featureHelperProvider = featureHelperProvider;
  }

  public static MembersInjector<FeatureHelperTest> create(
      Provider<FeatureHelper> featureHelperProvider) {
    return new FeatureHelperTest_MembersInjector(featureHelperProvider);
  }

  @Override
  public void injectMembers(FeatureHelperTest instance) {
    injectFeatureHelper(instance, featureHelperProvider.get());
  }

  @InjectedFieldSignature("com.google.android.gnd.ui.common.FeatureHelperTest.featureHelper")
  public static void injectFeatureHelper(FeatureHelperTest instance, FeatureHelper featureHelper) {
    instance.featureHelper = featureHelper;
  }
}
