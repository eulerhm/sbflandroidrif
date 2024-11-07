package com.github.pockethub.android.core.gist;

import android.app.Activity;
import com.github.pockethub.android.util.HttpImageGetter;
import javax.annotation.Generated;
import javax.inject.Inject;
import javax.inject.Provider;

@Generated(
    value = "com.google.auto.factory.processor.AutoFactoryProcessor",
    comments = "https://github.com/google/auto/tree/master/factory")
public final class RefreshGistTaskFactory {
  private final Provider<GistStore> storeProvider;

  private final Provider<HttpImageGetter> imageGetterProvider;

  @Inject
  public RefreshGistTaskFactory(
      Provider<GistStore> storeProvider, Provider<HttpImageGetter> imageGetterProvider) {
    this.storeProvider = checkNotNull(storeProvider, 1);
    this.imageGetterProvider = checkNotNull(imageGetterProvider, 2);
  }

  public RefreshGistTask create(Activity activity, String gistId) {
    return new RefreshGistTask(
        checkNotNull(storeProvider.get(), 1),
        checkNotNull(imageGetterProvider.get(), 2),
        checkNotNull(activity, 3),
        checkNotNull(gistId, 4));
  }

  private static <T> T checkNotNull(T reference, int argumentIndex) {
    if (reference == null) {
      throw new NullPointerException(
          "@AutoFactory method argument is null but is not marked @Nullable. Argument index: "
              + argumentIndex);
    }
    return reference;
  }
}
