package org.wordpress.android.ui.mysite.cards.quickstart;

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
public final class QuickStartCardBuilder_Factory implements Factory<QuickStartCardBuilder> {
  @Override
  public QuickStartCardBuilder get() {
    return newInstance();
  }

  public static QuickStartCardBuilder_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static QuickStartCardBuilder newInstance() {
    return new QuickStartCardBuilder();
  }

  private static final class InstanceHolder {
    private static final QuickStartCardBuilder_Factory INSTANCE = new QuickStartCardBuilder_Factory();
  }
}
