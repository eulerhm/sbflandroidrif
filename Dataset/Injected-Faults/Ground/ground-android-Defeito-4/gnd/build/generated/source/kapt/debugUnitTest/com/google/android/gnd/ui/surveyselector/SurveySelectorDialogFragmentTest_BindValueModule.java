package com.google.android.gnd.ui.surveyselector;

import android.content.Context;
import com.google.android.gnd.persistence.local.LocalDataStore;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.internal.testing.TestApplicationComponentManager;
import dagger.hilt.android.internal.testing.TestApplicationComponentManagerHolder;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.codegen.OriginatingElement;
import dagger.hilt.components.SingletonComponent;
import javax.annotation.Generated;

@OriginatingElement(
    topLevelClass = SurveySelectorDialogFragmentTest.class
)
@Module
@InstallIn(SingletonComponent.class)
@Generated("dagger.hilt.android.processor.internal.bindvalue.BindValueGenerator")
public final class SurveySelectorDialogFragmentTest_BindValueModule {
  @Provides
  static SurveySelectorDialogFragmentTest providesSurveySelectorDialogFragmentTest(
      @ApplicationContext Context context) {
    return (SurveySelectorDialogFragmentTest) ((TestApplicationComponentManager) ((TestApplicationComponentManagerHolder) context).componentManager()).getTestInstance();
  }

  @Provides
  static LocalDataStore providesMockLocalDataStore(SurveySelectorDialogFragmentTest test) {
    return test.mockLocalDataStore;
  }
}
