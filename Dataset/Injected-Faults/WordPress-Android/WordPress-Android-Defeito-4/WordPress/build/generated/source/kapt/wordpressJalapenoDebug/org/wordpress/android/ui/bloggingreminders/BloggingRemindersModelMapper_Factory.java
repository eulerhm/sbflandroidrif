package org.wordpress.android.ui.bloggingreminders;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;

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
public final class BloggingRemindersModelMapper_Factory implements Factory<BloggingRemindersModelMapper> {
  @Override
  public BloggingRemindersModelMapper get() {
    return newInstance();
  }

  public static BloggingRemindersModelMapper_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static BloggingRemindersModelMapper newInstance() {
    return new BloggingRemindersModelMapper();
  }

  private static final class InstanceHolder {
    private static final BloggingRemindersModelMapper_Factory INSTANCE = new BloggingRemindersModelMapper_Factory();
  }
}
