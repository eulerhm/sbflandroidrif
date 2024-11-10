package com.google.android.gnd.system;

import androidx.test.core.app.ApplicationProvider;
import dagger.hilt.android.internal.Contexts;
import dagger.hilt.android.internal.modules.ApplicationContextModule;
import dagger.hilt.android.internal.testing.TestComponentData;
import dagger.hilt.android.internal.testing.TestComponentDataSupplier;
import dagger.hilt.internal.GeneratedComponentManager;
import java.lang.SuppressWarnings;
import java.util.Collections;
import javax.annotation.Generated;

@Generated("dagger.hilt.processor.internal.root.RootProcessor")
public final class ApplicationErrorManagerTest_TestComponentDataSupplier extends TestComponentDataSupplier {
  protected TestComponentData get() {
    return new TestComponentData(false, testInstance -> injectInternal((ApplicationErrorManagerTest) testInstance), Collections.emptySet(), Collections.emptySet(), (modules, testInstance, autoAddModuleEnabled) -> DaggerApplicationErrorManagerTest_HiltComponents_SingletonC.builder()
        .applicationContextModule(
            new ApplicationContextModule(Contexts.getApplication(ApplicationProvider.getApplicationContext())))
        .build());
  }

  @SuppressWarnings("unchecked")
  private static void injectInternal(ApplicationErrorManagerTest testInstance) {
    ((ApplicationErrorManagerTest_GeneratedInjector) ((GeneratedComponentManager) Contexts.getApplication(ApplicationProvider.getApplicationContext())).generatedComponent()).injectTest(testInstance);
  }
}
