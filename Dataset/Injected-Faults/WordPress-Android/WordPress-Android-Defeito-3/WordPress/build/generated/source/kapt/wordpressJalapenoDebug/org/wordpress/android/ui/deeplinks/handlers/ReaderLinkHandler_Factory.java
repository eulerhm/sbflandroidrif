package org.wordpress.android.ui.deeplinks.handlers;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.ui.utils.IntentUtils;
import org.wordpress.android.util.analytics.AnalyticsUtilsWrapper;

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
public final class ReaderLinkHandler_Factory implements Factory<ReaderLinkHandler> {
  private final Provider<IntentUtils> intentUtilsProvider;

  private final Provider<AnalyticsUtilsWrapper> analyticsUtilsWrapperProvider;

  public ReaderLinkHandler_Factory(Provider<IntentUtils> intentUtilsProvider,
      Provider<AnalyticsUtilsWrapper> analyticsUtilsWrapperProvider) {
    this.intentUtilsProvider = intentUtilsProvider;
    this.analyticsUtilsWrapperProvider = analyticsUtilsWrapperProvider;
  }

  @Override
  public ReaderLinkHandler get() {
    return newInstance(intentUtilsProvider.get(), analyticsUtilsWrapperProvider.get());
  }

  public static ReaderLinkHandler_Factory create(Provider<IntentUtils> intentUtilsProvider,
      Provider<AnalyticsUtilsWrapper> analyticsUtilsWrapperProvider) {
    return new ReaderLinkHandler_Factory(intentUtilsProvider, analyticsUtilsWrapperProvider);
  }

  public static ReaderLinkHandler newInstance(IntentUtils intentUtils,
      AnalyticsUtilsWrapper analyticsUtilsWrapper) {
    return new ReaderLinkHandler(intentUtils, analyticsUtilsWrapper);
  }
}
