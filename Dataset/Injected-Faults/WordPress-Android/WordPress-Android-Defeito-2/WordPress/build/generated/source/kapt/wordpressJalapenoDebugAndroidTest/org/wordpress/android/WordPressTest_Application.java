package org.wordpress.android;

import android.content.Context;
import dagger.hilt.android.internal.testing.TestApplicationComponentManager;
import dagger.hilt.android.internal.testing.TestApplicationComponentManagerHolder;
import dagger.hilt.internal.GeneratedComponentManager;
import java.lang.Object;
import java.lang.Override;

public final class WordPressTest_Application extends BaseWordPressTest implements GeneratedComponentManager<Object>, TestApplicationComponentManagerHolder {
  private TestApplicationComponentManager componentManager;

  @Override
  protected final void attachBaseContext(Context base) {
    super.attachBaseContext(base);
    componentManager = new TestApplicationComponentManager(this);
  }

  @Override
  public final GeneratedComponentManager<Object> componentManager() {
    return componentManager;
  }

  @Override
  public final Object generatedComponent() {
    return componentManager.generatedComponent();
  }
}
