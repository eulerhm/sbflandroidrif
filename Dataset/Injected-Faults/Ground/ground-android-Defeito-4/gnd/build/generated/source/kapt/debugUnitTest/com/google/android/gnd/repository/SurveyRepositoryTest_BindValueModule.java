package com.google.android.gnd.repository;

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
    topLevelClass = SurveyRepositoryTest.class
)
@Module
@InstallIn(SingletonComponent.class)
@Generated("dagger.hilt.android.processor.internal.bindvalue.BindValueGenerator")
public final class SurveyRepositoryTest_BindValueModule {
  @Provides
  static SurveyRepositoryTest providesSurveyRepositoryTest(@ApplicationContext Context context) {
    return (SurveyRepositoryTest) ((TestApplicationComponentManager) ((TestApplicationComponentManagerHolder) context).componentManager()).getTestInstance();
  }

  @Provides
  static LocalDataStore providesMockLocalDataStore(SurveyRepositoryTest test) {
    return test.mockLocalDataStore;
  }

  @Provides
  static UserRepository providesUserRepository(SurveyRepositoryTest test) {
    return test.userRepository;
  }
}
