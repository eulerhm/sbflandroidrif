package org.wordpress.android.util.experiments;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;

@ScopeMetadata
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class ExampleExperiment_Factory implements Factory<ExampleExperiment> {
  private final Provider<ExPlat> exPlatProvider;

  public ExampleExperiment_Factory(Provider<ExPlat> exPlatProvider) {
    this.exPlatProvider = exPlatProvider;
  }

  @Override
  public ExampleExperiment get() {
    return newInstance(exPlatProvider.get());
  }

  public static ExampleExperiment_Factory create(Provider<ExPlat> exPlatProvider) {
    return new ExampleExperiment_Factory(exPlatProvider);
  }

  public static ExampleExperiment newInstance(ExPlat exPlat) {
    return new ExampleExperiment(exPlat);
  }
}
