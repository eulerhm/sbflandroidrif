package org.wordpress.android.ui.bloggingreminders;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.ui.utils.UiHelpers;

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
public final class BloggingRemindersAdapter_Factory implements Factory<BloggingRemindersAdapter> {
  private final Provider<UiHelpers> uiHelpersProvider;

  public BloggingRemindersAdapter_Factory(Provider<UiHelpers> uiHelpersProvider) {
    this.uiHelpersProvider = uiHelpersProvider;
  }

  @Override
  public BloggingRemindersAdapter get() {
    return newInstance(uiHelpersProvider.get());
  }

  public static BloggingRemindersAdapter_Factory create(Provider<UiHelpers> uiHelpersProvider) {
    return new BloggingRemindersAdapter_Factory(uiHelpersProvider);
  }

  public static BloggingRemindersAdapter newInstance(UiHelpers uiHelpers) {
    return new BloggingRemindersAdapter(uiHelpers);
  }
}
