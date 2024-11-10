package org.wordpress.android.ui.posts.editor.media;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.fluxc.Dispatcher;

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
public final class UpdateMediaModelUseCase_Factory implements Factory<UpdateMediaModelUseCase> {
  private final Provider<Dispatcher> dispatcherProvider;

  public UpdateMediaModelUseCase_Factory(Provider<Dispatcher> dispatcherProvider) {
    this.dispatcherProvider = dispatcherProvider;
  }

  @Override
  public UpdateMediaModelUseCase get() {
    return newInstance(dispatcherProvider.get());
  }

  public static UpdateMediaModelUseCase_Factory create(Provider<Dispatcher> dispatcherProvider) {
    return new UpdateMediaModelUseCase_Factory(dispatcherProvider);
  }

  public static UpdateMediaModelUseCase newInstance(Dispatcher dispatcher) {
    return new UpdateMediaModelUseCase(dispatcher);
  }
}
