package org.wordpress.android.viewmodel.pages;

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
public final class PageListEventListener_Factory_Factory implements Factory<PageListEventListener.Factory> {
  @Override
  public PageListEventListener.Factory get() {
    return newInstance();
  }

  public static PageListEventListener_Factory_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static PageListEventListener.Factory newInstance() {
    return new PageListEventListener.Factory();
  }

  private static final class InstanceHolder {
    private static final PageListEventListener_Factory_Factory INSTANCE = new PageListEventListener_Factory_Factory();
  }
}
