package com.google.android.gnd.ui;

import android.content.Context;
import dagger.MembersInjector;
import dagger.hilt.android.qualifiers.ApplicationContext;
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
public final class MarkerIconFactoryTest_MembersInjector implements MembersInjector<MarkerIconFactoryTest> {
  private final Provider<Context> contextProvider;

  private final Provider<MarkerIconFactory> markerIconFactoryProvider;

  public MarkerIconFactoryTest_MembersInjector(Provider<Context> contextProvider,
      Provider<MarkerIconFactory> markerIconFactoryProvider) {
    this.contextProvider = contextProvider;
    this.markerIconFactoryProvider = markerIconFactoryProvider;
  }

  public static MembersInjector<MarkerIconFactoryTest> create(Provider<Context> contextProvider,
      Provider<MarkerIconFactory> markerIconFactoryProvider) {
    return new MarkerIconFactoryTest_MembersInjector(contextProvider, markerIconFactoryProvider);
  }

  @Override
  public void injectMembers(MarkerIconFactoryTest instance) {
    injectContext(instance, contextProvider.get());
    injectMarkerIconFactory(instance, markerIconFactoryProvider.get());
  }

  @InjectedFieldSignature("com.google.android.gnd.ui.MarkerIconFactoryTest.context")
  @ApplicationContext
  public static void injectContext(MarkerIconFactoryTest instance, Context context) {
    instance.context = context;
  }

  @InjectedFieldSignature("com.google.android.gnd.ui.MarkerIconFactoryTest.markerIconFactory")
  public static void injectMarkerIconFactory(MarkerIconFactoryTest instance,
      MarkerIconFactory markerIconFactory) {
    instance.markerIconFactory = markerIconFactory;
  }
}
