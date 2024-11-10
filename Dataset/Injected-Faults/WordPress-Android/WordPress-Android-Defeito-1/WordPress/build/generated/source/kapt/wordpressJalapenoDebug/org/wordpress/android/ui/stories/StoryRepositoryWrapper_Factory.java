package org.wordpress.android.ui.stories;

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
public final class StoryRepositoryWrapper_Factory implements Factory<StoryRepositoryWrapper> {
  @Override
  public StoryRepositoryWrapper get() {
    return newInstance();
  }

  public static StoryRepositoryWrapper_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static StoryRepositoryWrapper newInstance() {
    return new StoryRepositoryWrapper();
  }

  private static final class InstanceHolder {
    private static final StoryRepositoryWrapper_Factory INSTANCE = new StoryRepositoryWrapper_Factory();
  }
}
