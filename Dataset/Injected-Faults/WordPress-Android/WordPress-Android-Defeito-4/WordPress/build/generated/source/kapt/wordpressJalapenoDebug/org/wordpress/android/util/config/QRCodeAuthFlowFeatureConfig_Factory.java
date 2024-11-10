package org.wordpress.android.util.config;

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
public final class QRCodeAuthFlowFeatureConfig_Factory implements Factory<QRCodeAuthFlowFeatureConfig> {
  private final Provider<AppConfig> appConfigProvider;

  public QRCodeAuthFlowFeatureConfig_Factory(Provider<AppConfig> appConfigProvider) {
    this.appConfigProvider = appConfigProvider;
  }

  @Override
  public QRCodeAuthFlowFeatureConfig get() {
    return newInstance(appConfigProvider.get());
  }

  public static QRCodeAuthFlowFeatureConfig_Factory create(Provider<AppConfig> appConfigProvider) {
    return new QRCodeAuthFlowFeatureConfig_Factory(appConfigProvider);
  }

  public static QRCodeAuthFlowFeatureConfig newInstance(AppConfig appConfig) {
    return new QRCodeAuthFlowFeatureConfig(appConfig);
  }
}
