package org.wordpress.android.ui.reader;

import dagger.MembersInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.DaggerFragment_MembersInjector;
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
public final class ReaderPostWebViewCachingFragment_MembersInjector implements MembersInjector<ReaderPostWebViewCachingFragment> {
  private final Provider<DispatchingAndroidInjector<Object>> androidInjectorProvider;

  private final Provider<ReaderCssProvider> mReaderCssProvider;

  public ReaderPostWebViewCachingFragment_MembersInjector(
      Provider<DispatchingAndroidInjector<Object>> androidInjectorProvider,
      Provider<ReaderCssProvider> mReaderCssProvider) {
    this.androidInjectorProvider = androidInjectorProvider;
    this.mReaderCssProvider = mReaderCssProvider;
  }

  public static MembersInjector<ReaderPostWebViewCachingFragment> create(
      Provider<DispatchingAndroidInjector<Object>> androidInjectorProvider,
      Provider<ReaderCssProvider> mReaderCssProvider) {
    return new ReaderPostWebViewCachingFragment_MembersInjector(androidInjectorProvider, mReaderCssProvider);
  }

  @Override
  public void injectMembers(ReaderPostWebViewCachingFragment instance) {
    DaggerFragment_MembersInjector.injectAndroidInjector(instance, androidInjectorProvider.get());
    injectMReaderCssProvider(instance, mReaderCssProvider.get());
  }

  @InjectedFieldSignature("org.wordpress.android.ui.reader.ReaderPostWebViewCachingFragment.mReaderCssProvider")
  public static void injectMReaderCssProvider(ReaderPostWebViewCachingFragment instance,
      ReaderCssProvider mReaderCssProvider) {
    instance.mReaderCssProvider = mReaderCssProvider;
  }
}
