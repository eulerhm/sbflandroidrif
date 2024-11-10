package org.wordpress.android.viewmodel;

import androidx.lifecycle.ViewModel;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import java.util.Map;
import javax.annotation.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
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
public final class ViewModelFactory_Factory implements Factory<ViewModelFactory> {
  private final Provider<Map<Class<? extends ViewModel>, Provider<ViewModel>>> viewModelsMapProvider;

  public ViewModelFactory_Factory(
      Provider<Map<Class<? extends ViewModel>, Provider<ViewModel>>> viewModelsMapProvider) {
    this.viewModelsMapProvider = viewModelsMapProvider;
  }

  @Override
  public ViewModelFactory get() {
    return newInstance(viewModelsMapProvider.get());
  }

  public static ViewModelFactory_Factory create(
      Provider<Map<Class<? extends ViewModel>, Provider<ViewModel>>> viewModelsMapProvider) {
    return new ViewModelFactory_Factory(viewModelsMapProvider);
  }

  public static ViewModelFactory newInstance(
      Map<Class<? extends ViewModel>, Provider<ViewModel>> viewModelsMap) {
    return new ViewModelFactory(viewModelsMap);
  }
}
