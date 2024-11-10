package com.google.android.gnd;

import androidx.test.core.app.ApplicationProvider;
import dagger.hilt.android.internal.Contexts;
import dagger.hilt.android.internal.modules.ApplicationContextModule;
import dagger.hilt.android.internal.testing.TestComponentData;
import dagger.hilt.android.internal.testing.TestComponentDataSupplier;
import dagger.hilt.internal.GeneratedComponentManager;
import java.lang.SuppressWarnings;
import java.util.Collections;

public final class AddFeatureTest_TestComponentDataSupplier extends TestComponentDataSupplier {
  protected TestComponentData get() {
    return new TestComponentData(false, testInstance -> injectInternal((AddFeatureTest) testInstance), Collections.emptySet(), Collections.emptySet(), (modules, testInstance, autoAddModuleEnabled) -> DaggerAddFeatureTest_HiltComponents_SingletonC.builder()
        .applicationContextModule(
            new ApplicationContextModule(Contexts.getApplication(ApplicationProvider.getApplicationContext())))
        .build());
  }

  @SuppressWarnings("unchecked")
  private static void injectInternal(AddFeatureTest testInstance) {
    ((AddFeatureTest_GeneratedInjector) ((GeneratedComponentManager) Contexts.getApplication(ApplicationProvider.getApplicationContext())).generatedComponent()).injectTest(testInstance);
  }
}
