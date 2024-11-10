package org.wordpress.android.ui.posts;

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
public final class AddCategoryUseCase_Factory implements Factory<AddCategoryUseCase> {
  private final Provider<Dispatcher> dispatcherProvider;

  public AddCategoryUseCase_Factory(Provider<Dispatcher> dispatcherProvider) {
    this.dispatcherProvider = dispatcherProvider;
  }

  @Override
  public AddCategoryUseCase get() {
    return newInstance(dispatcherProvider.get());
  }

  public static AddCategoryUseCase_Factory create(Provider<Dispatcher> dispatcherProvider) {
    return new AddCategoryUseCase_Factory(dispatcherProvider);
  }

  public static AddCategoryUseCase newInstance(Dispatcher dispatcher) {
    return new AddCategoryUseCase(dispatcher);
  }
}
