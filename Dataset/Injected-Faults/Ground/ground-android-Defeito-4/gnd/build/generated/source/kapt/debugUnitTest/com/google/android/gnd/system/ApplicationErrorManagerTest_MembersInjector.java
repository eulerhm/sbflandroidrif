package com.google.android.gnd.system;

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
public final class ApplicationErrorManagerTest_MembersInjector implements MembersInjector<ApplicationErrorManagerTest> {
  private final Provider<ApplicationErrorManager> errorManagerProvider;

  public ApplicationErrorManagerTest_MembersInjector(
      Provider<ApplicationErrorManager> errorManagerProvider) {
    this.errorManagerProvider = errorManagerProvider;
  }

  public static MembersInjector<ApplicationErrorManagerTest> create(
      Provider<ApplicationErrorManager> errorManagerProvider) {
    return new ApplicationErrorManagerTest_MembersInjector(errorManagerProvider);
  }

  @Override
  public void injectMembers(ApplicationErrorManagerTest instance) {
    injectErrorManager(instance, errorManagerProvider.get());
  }

  @InjectedFieldSignature("com.google.android.gnd.system.ApplicationErrorManagerTest.errorManager")
  public static void injectErrorManager(ApplicationErrorManagerTest instance,
      ApplicationErrorManager errorManager) {
    instance.errorManager = errorManager;
  }
}
