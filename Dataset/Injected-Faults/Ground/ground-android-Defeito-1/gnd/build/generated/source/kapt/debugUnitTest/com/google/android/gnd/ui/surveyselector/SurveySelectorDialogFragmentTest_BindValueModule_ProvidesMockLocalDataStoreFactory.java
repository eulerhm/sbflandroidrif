package com.google.android.gnd.ui.surveyselector;

import com.google.android.gnd.persistence.local.LocalDataStore;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
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
public final class SurveySelectorDialogFragmentTest_BindValueModule_ProvidesMockLocalDataStoreFactory implements Factory<LocalDataStore> {
  private final Provider<SurveySelectorDialogFragmentTest> testProvider;

  public SurveySelectorDialogFragmentTest_BindValueModule_ProvidesMockLocalDataStoreFactory(
      Provider<SurveySelectorDialogFragmentTest> testProvider) {
    this.testProvider = testProvider;
  }

  @Override
  public LocalDataStore get() {
    return providesMockLocalDataStore(testProvider.get());
  }

  public static SurveySelectorDialogFragmentTest_BindValueModule_ProvidesMockLocalDataStoreFactory create(
      Provider<SurveySelectorDialogFragmentTest> testProvider) {
    return new SurveySelectorDialogFragmentTest_BindValueModule_ProvidesMockLocalDataStoreFactory(testProvider);
  }

  public static LocalDataStore providesMockLocalDataStore(SurveySelectorDialogFragmentTest test) {
    return Preconditions.checkNotNullFromProvides(SurveySelectorDialogFragmentTest_BindValueModule.providesMockLocalDataStore(test));
  }
}
