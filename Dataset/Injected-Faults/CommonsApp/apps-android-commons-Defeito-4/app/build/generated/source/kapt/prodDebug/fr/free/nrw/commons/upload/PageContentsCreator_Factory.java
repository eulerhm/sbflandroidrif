// Generated by Dagger (https://google.github.io/dagger).
package fr.free.nrw.commons.upload;

import android.content.Context;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class PageContentsCreator_Factory implements Factory<PageContentsCreator> {
  private final Provider<Context> contextProvider;

  public PageContentsCreator_Factory(Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public PageContentsCreator get() {
    return new PageContentsCreator(contextProvider.get());
  }

  public static PageContentsCreator_Factory create(Provider<Context> contextProvider) {
    return new PageContentsCreator_Factory(contextProvider);
  }

  public static PageContentsCreator newInstance(Context context) {
    return new PageContentsCreator(context);
  }
}
