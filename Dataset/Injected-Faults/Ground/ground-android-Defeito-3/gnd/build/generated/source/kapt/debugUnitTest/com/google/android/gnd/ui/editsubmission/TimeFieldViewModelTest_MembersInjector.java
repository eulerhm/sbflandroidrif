package com.google.android.gnd.ui.editsubmission;

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
public final class TimeFieldViewModelTest_MembersInjector implements MembersInjector<TimeFieldViewModelTest> {
  private final Provider<TimeFieldViewModel> timeFieldViewModelProvider;

  public TimeFieldViewModelTest_MembersInjector(
      Provider<TimeFieldViewModel> timeFieldViewModelProvider) {
    this.timeFieldViewModelProvider = timeFieldViewModelProvider;
  }

  public static MembersInjector<TimeFieldViewModelTest> create(
      Provider<TimeFieldViewModel> timeFieldViewModelProvider) {
    return new TimeFieldViewModelTest_MembersInjector(timeFieldViewModelProvider);
  }

  @Override
  public void injectMembers(TimeFieldViewModelTest instance) {
    injectTimeFieldViewModel(instance, timeFieldViewModelProvider.get());
  }

  @InjectedFieldSignature("com.google.android.gnd.ui.editsubmission.TimeFieldViewModelTest.timeFieldViewModel")
  public static void injectTimeFieldViewModel(TimeFieldViewModelTest instance,
      TimeFieldViewModel timeFieldViewModel) {
    instance.timeFieldViewModel = timeFieldViewModel;
  }
}
