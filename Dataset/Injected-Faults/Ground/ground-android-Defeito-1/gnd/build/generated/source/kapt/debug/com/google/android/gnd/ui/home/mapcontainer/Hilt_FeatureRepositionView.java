package com.google.android.gnd.ui.home.mapcontainer;

import android.content.Context;
import com.google.android.gnd.ui.common.AbstractView;
import dagger.hilt.android.internal.managers.ViewComponentManager;
import dagger.hilt.internal.GeneratedComponentManagerHolder;
import dagger.hilt.internal.UnsafeCasts;
import java.lang.Object;
import java.lang.Override;

/**
 * A generated base class to be extended by the @dagger.hilt.android.AndroidEntryPoint annotated class. If using the Gradle plugin, this is swapped as the base class via bytecode transformation.
 */
abstract class Hilt_FeatureRepositionView extends AbstractView implements GeneratedComponentManagerHolder {
  private ViewComponentManager componentManager;

  private boolean injected;

  Hilt_FeatureRepositionView(Context context) {
    super(context);
    inject();
  }

  @Override
  public final Object generatedComponent() {
    return this.componentManager().generatedComponent();
  }

  protected ViewComponentManager createComponentManager() {
    return new ViewComponentManager(this, true /* hasFragmentBindings */);
  }

  @Override
  public final ViewComponentManager componentManager() {
    if (componentManager == null) {
      componentManager = createComponentManager();
    }
    return componentManager;
  }

  protected void inject() {
    if (!injected) {
      injected = true;
      ((FeatureRepositionView_GeneratedInjector) this.generatedComponent()).injectFeatureRepositionView(UnsafeCasts.<FeatureRepositionView>unsafeCast(this));
    }
  }
}
