// Generated code from Butter Knife. Do not modify!
package fr.free.nrw.commons.explore;

import android.view.View;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.google.android.material.tabs.TabLayout;
import fr.free.nrw.commons.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ExploreFragment_ViewBinding implements Unbinder {
  private ExploreFragment target;

  @UiThread
  public ExploreFragment_ViewBinding(ExploreFragment target, View source) {
    this.target = target;

    target.tabLayout = Utils.findRequiredViewAsType(source, R.id.tab_layout, "field 'tabLayout'", TabLayout.class);
    target.viewPager = Utils.findRequiredViewAsType(source, R.id.viewPager, "field 'viewPager'", ParentViewPager.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    ExploreFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tabLayout = null;
    target.viewPager = null;
  }
}
