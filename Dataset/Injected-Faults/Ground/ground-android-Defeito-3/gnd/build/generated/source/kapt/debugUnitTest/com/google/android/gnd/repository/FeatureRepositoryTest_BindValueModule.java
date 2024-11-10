package com.google.android.gnd.repository;

import android.content.Context;
import com.google.android.gnd.persistence.local.LocalDataStore;
import com.google.android.gnd.persistence.sync.DataSyncWorkManager;
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
    topLevelClass = FeatureRepositoryTest.class
)
@Module
@InstallIn(SingletonComponent.class)
@Generated("dagger.hilt.android.processor.internal.bindvalue.BindValueGenerator")
public final class FeatureRepositoryTest_BindValueModule {
  @Provides
  static FeatureRepositoryTest providesFeatureRepositoryTest(@ApplicationContext Context context) {
    return (FeatureRepositoryTest) ((TestApplicationComponentManager) ((TestApplicationComponentManagerHolder) context).componentManager()).getTestInstance();
  }

  @Provides
  static LocalDataStore providesMockLocalDataStore(FeatureRepositoryTest test) {
    return test.mockLocalDataStore;
  }

  @Provides
  static DataSyncWorkManager providesMockWorkManager(FeatureRepositoryTest test) {
    return test.mockWorkManager;
  }

  @Provides
  static SurveyRepository providesMockSurveyRepository(FeatureRepositoryTest test) {
    return test.mockSurveyRepository;
  }
}
