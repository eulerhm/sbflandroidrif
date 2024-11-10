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
public final class DateFieldViewModelTest_MembersInjector implements MembersInjector<DateFieldViewModelTest> {
  private final Provider<DateFieldViewModel> dateFieldViewModelProvider;

  public DateFieldViewModelTest_MembersInjector(
      Provider<DateFieldViewModel> dateFieldViewModelProvider) {
    this.dateFieldViewModelProvider = dateFieldViewModelProvider;
  }

  public static MembersInjector<DateFieldViewModelTest> create(
      Provider<DateFieldViewModel> dateFieldViewModelProvider) {
    return new DateFieldViewModelTest_MembersInjector(dateFieldViewModelProvider);
  }

  @Override
  public void injectMembers(DateFieldViewModelTest instance) {
    injectDateFieldViewModel(instance, dateFieldViewModelProvider.get());
  }

  @InjectedFieldSignature("com.google.android.gnd.ui.editsubmission.DateFieldViewModelTest.dateFieldViewModel")
  public static void injectDateFieldViewModel(DateFieldViewModelTest instance,
      DateFieldViewModel dateFieldViewModel) {
    instance.dateFieldViewModel = dateFieldViewModel;
  }
}
