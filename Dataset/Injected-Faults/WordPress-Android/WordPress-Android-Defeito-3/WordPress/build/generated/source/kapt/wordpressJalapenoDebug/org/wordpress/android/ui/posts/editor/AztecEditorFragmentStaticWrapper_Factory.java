package org.wordpress.android.ui.posts.editor;

import android.content.Context;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;

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
public final class AztecEditorFragmentStaticWrapper_Factory implements Factory<AztecEditorFragmentStaticWrapper> {
  private final Provider<Context> appContextProvider;

  public AztecEditorFragmentStaticWrapper_Factory(Provider<Context> appContextProvider) {
    this.appContextProvider = appContextProvider;
  }

  @Override
  public AztecEditorFragmentStaticWrapper get() {
    return newInstance(appContextProvider.get());
  }

  public static AztecEditorFragmentStaticWrapper_Factory create(
      Provider<Context> appContextProvider) {
    return new AztecEditorFragmentStaticWrapper_Factory(appContextProvider);
  }

  public static AztecEditorFragmentStaticWrapper newInstance(Context appContext) {
    return new AztecEditorFragmentStaticWrapper(appContext);
  }
}
