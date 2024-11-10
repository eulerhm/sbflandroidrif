package org.wordpress.android;

import dagger.MembersInjector;
import dagger.android.DispatchingAndroidInjector;
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
public final class WordPressApp_MembersInjector implements MembersInjector<WordPressApp> {
  private final Provider<DispatchingAndroidInjector<Object>> mDispatchingAndroidInjectorProvider;

  private final Provider<AppInitializer> mAppInitializerProvider;

  public WordPressApp_MembersInjector(
      Provider<DispatchingAndroidInjector<Object>> mDispatchingAndroidInjectorProvider,
      Provider<AppInitializer> mAppInitializerProvider) {
    this.mDispatchingAndroidInjectorProvider = mDispatchingAndroidInjectorProvider;
    this.mAppInitializerProvider = mAppInitializerProvider;
  }

  public static MembersInjector<WordPressApp> create(
      Provider<DispatchingAndroidInjector<Object>> mDispatchingAndroidInjectorProvider,
      Provider<AppInitializer> mAppInitializerProvider) {
    return new WordPressApp_MembersInjector(mDispatchingAndroidInjectorProvider, mAppInitializerProvider);
  }

  @Override
  public void injectMembers(WordPressApp instance) {
    injectMDispatchingAndroidInjector(instance, mDispatchingAndroidInjectorProvider.get());
    injectMAppInitializer(instance, mAppInitializerProvider.get());
  }

  @InjectedFieldSignature("org.wordpress.android.WordPressApp.mDispatchingAndroidInjector")
  public static void injectMDispatchingAndroidInjector(WordPressApp instance,
      DispatchingAndroidInjector<Object> mDispatchingAndroidInjector) {
    instance.mDispatchingAndroidInjector = mDispatchingAndroidInjector;
  }

  @InjectedFieldSignature("org.wordpress.android.WordPressApp.mAppInitializer")
  public static void injectMAppInitializer(WordPressApp instance, AppInitializer mAppInitializer) {
    instance.mAppInitializer = mAppInitializer;
  }
}
