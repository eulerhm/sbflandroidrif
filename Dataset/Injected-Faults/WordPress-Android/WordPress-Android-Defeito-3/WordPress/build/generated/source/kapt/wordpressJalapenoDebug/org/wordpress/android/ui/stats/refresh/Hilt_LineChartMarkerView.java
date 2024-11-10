package org.wordpress.android.ui.stats.refresh;

import android.content.Context;
import com.github.mikephil.charting.components.MarkerView;
import dagger.hilt.android.internal.managers.ViewComponentManager;
import dagger.hilt.internal.GeneratedComponentManagerHolder;
import dagger.hilt.internal.UnsafeCasts;
import java.lang.Object;
import java.lang.Override;
import java.lang.SuppressWarnings;
import javax.annotation.Generated;

/**
 * A generated base class to be extended by the @dagger.hilt.android.AndroidEntryPoint annotated class. If using the Gradle plugin, this is swapped as the base class via bytecode transformation.
 */
@Generated("dagger.hilt.android.processor.internal.androidentrypoint.ViewGenerator")
@SuppressWarnings("MagicNumber")
public abstract class Hilt_LineChartMarkerView extends MarkerView implements GeneratedComponentManagerHolder {
  private ViewComponentManager componentManager;

  private boolean injected;

  Hilt_LineChartMarkerView(Context context, int layoutResource) {
    super(context, layoutResource);
    inject();
  }

  @Override
  public final Object generatedComponent() {
    return this.componentManager().generatedComponent();
  }

  protected ViewComponentManager createComponentManager() {
    return new ViewComponentManager(this, false /* hasFragmentBindings */);
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
      ((LineChartMarkerView_GeneratedInjector) this.generatedComponent()).injectLineChartMarkerView(UnsafeCasts.<LineChartMarkerView>unsafeCast(this));
    }
  }
}
