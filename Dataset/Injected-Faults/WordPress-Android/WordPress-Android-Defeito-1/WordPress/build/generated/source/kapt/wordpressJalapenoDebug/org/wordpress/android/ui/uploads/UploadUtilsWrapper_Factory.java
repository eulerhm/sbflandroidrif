package org.wordpress.android.ui.uploads;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.fluxc.Dispatcher;
import org.wordpress.android.util.SnackbarSequencer;

@ScopeMetadata("dagger.Reusable")
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
public final class UploadUtilsWrapper_Factory implements Factory<UploadUtilsWrapper> {
  private final Provider<SnackbarSequencer> sequencerProvider;

  private final Provider<Dispatcher> dispatcherProvider;

  public UploadUtilsWrapper_Factory(Provider<SnackbarSequencer> sequencerProvider,
      Provider<Dispatcher> dispatcherProvider) {
    this.sequencerProvider = sequencerProvider;
    this.dispatcherProvider = dispatcherProvider;
  }

  @Override
  public UploadUtilsWrapper get() {
    return newInstance(sequencerProvider.get(), dispatcherProvider.get());
  }

  public static UploadUtilsWrapper_Factory create(Provider<SnackbarSequencer> sequencerProvider,
      Provider<Dispatcher> dispatcherProvider) {
    return new UploadUtilsWrapper_Factory(sequencerProvider, dispatcherProvider);
  }

  public static UploadUtilsWrapper newInstance(SnackbarSequencer sequencer, Dispatcher dispatcher) {
    return new UploadUtilsWrapper(sequencer, dispatcher);
  }
}
