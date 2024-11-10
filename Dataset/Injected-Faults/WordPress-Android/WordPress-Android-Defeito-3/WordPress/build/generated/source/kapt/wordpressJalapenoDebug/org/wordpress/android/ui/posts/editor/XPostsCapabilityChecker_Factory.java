package org.wordpress.android.ui.posts.editor;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.fluxc.store.XPostsStore;

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
public final class XPostsCapabilityChecker_Factory implements Factory<XPostsCapabilityChecker> {
  private final Provider<XPostsStore> xPostsStoreProvider;

  public XPostsCapabilityChecker_Factory(Provider<XPostsStore> xPostsStoreProvider) {
    this.xPostsStoreProvider = xPostsStoreProvider;
  }

  @Override
  public XPostsCapabilityChecker get() {
    return newInstance(xPostsStoreProvider.get());
  }

  public static XPostsCapabilityChecker_Factory create(Provider<XPostsStore> xPostsStoreProvider) {
    return new XPostsCapabilityChecker_Factory(xPostsStoreProvider);
  }

  public static XPostsCapabilityChecker newInstance(XPostsStore xPostsStore) {
    return new XPostsCapabilityChecker(xPostsStore);
  }
}
