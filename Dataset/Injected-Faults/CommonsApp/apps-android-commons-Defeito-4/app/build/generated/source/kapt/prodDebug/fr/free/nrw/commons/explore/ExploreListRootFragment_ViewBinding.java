// Generated code from Butter Knife. Do not modify!
package fr.free.nrw.commons.explore;

import android.view.View;
import android.widget.FrameLayout;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import fr.free.nrw.commons.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ExploreListRootFragment_ViewBinding implements Unbinder {
  private ExploreListRootFragment target;

  @UiThread
  public ExploreListRootFragment_ViewBinding(ExploreListRootFragment target, View source) {
    this.target = target;

    target.container = Utils.findRequiredViewAsType(source, R.id.explore_container, "field 'container'", FrameLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    ExploreListRootFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.container = null;
  }
}
