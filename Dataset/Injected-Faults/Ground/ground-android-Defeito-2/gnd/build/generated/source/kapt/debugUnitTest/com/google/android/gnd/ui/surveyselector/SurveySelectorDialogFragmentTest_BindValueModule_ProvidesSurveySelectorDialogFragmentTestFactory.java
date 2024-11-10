package com.google.android.gnd.ui.surveyselector;

import android.content.Context;
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
public final class SurveySelectorDialogFragmentTest_BindValueModule_ProvidesSurveySelectorDialogFragmentTestFactory implements Factory<SurveySelectorDialogFragmentTest> {
  private final Provider<Context> contextProvider;

  public SurveySelectorDialogFragmentTest_BindValueModule_ProvidesSurveySelectorDialogFragmentTestFactory(
      Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public SurveySelectorDialogFragmentTest get() {
    return providesSurveySelectorDialogFragmentTest(contextProvider.get());
  }

  public static SurveySelectorDialogFragmentTest_BindValueModule_ProvidesSurveySelectorDialogFragmentTestFactory create(
      Provider<Context> contextProvider) {
    return new SurveySelectorDialogFragmentTest_BindValueModule_ProvidesSurveySelectorDialogFragmentTestFactory(contextProvider);
  }

  public static SurveySelectorDialogFragmentTest providesSurveySelectorDialogFragmentTest(
      Context context) {
    return Preconditions.checkNotNullFromProvides(SurveySelectorDialogFragmentTest_BindValueModule.providesSurveySelectorDialogFragmentTest(context));
  }
}
