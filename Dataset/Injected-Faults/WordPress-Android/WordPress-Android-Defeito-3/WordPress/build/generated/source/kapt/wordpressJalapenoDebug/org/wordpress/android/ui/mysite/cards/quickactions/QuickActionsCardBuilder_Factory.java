package org.wordpress.android.ui.mysite.cards.quickactions;

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
public final class QuickActionsCardBuilder_Factory implements Factory<QuickActionsCardBuilder> {
  @Override
  public QuickActionsCardBuilder get() {
    return newInstance();
  }

  public static QuickActionsCardBuilder_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static QuickActionsCardBuilder newInstance() {
    return new QuickActionsCardBuilder();
  }

  private static final class InstanceHolder {
    private static final QuickActionsCardBuilder_Factory INSTANCE = new QuickActionsCardBuilder_Factory();
  }
}
