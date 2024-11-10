package org.wordpress.android.ui.reader;

import androidx.lifecycle.ViewModelProvider;
import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.QualifierMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;

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
public final class SubfilterBottomSheetFragment_MembersInjector implements MembersInjector<SubfilterBottomSheetFragment> {
  private final Provider<ViewModelProvider.Factory> viewModelFactoryProvider;

  public SubfilterBottomSheetFragment_MembersInjector(
      Provider<ViewModelProvider.Factory> viewModelFactoryProvider) {
    this.viewModelFactoryProvider = viewModelFactoryProvider;
  }

  public static MembersInjector<SubfilterBottomSheetFragment> create(
      Provider<ViewModelProvider.Factory> viewModelFactoryProvider) {
    return new SubfilterBottomSheetFragment_MembersInjector(viewModelFactoryProvider);
  }

  @Override
  public void injectMembers(SubfilterBottomSheetFragment instance) {
    injectViewModelFactory(instance, viewModelFactoryProvider.get());
  }

  @InjectedFieldSignature("org.wordpress.android.ui.reader.SubfilterBottomSheetFragment.viewModelFactory")
  public static void injectViewModelFactory(SubfilterBottomSheetFragment instance,
      ViewModelProvider.Factory viewModelFactory) {
    instance.viewModelFactory = viewModelFactory;
  }
}
