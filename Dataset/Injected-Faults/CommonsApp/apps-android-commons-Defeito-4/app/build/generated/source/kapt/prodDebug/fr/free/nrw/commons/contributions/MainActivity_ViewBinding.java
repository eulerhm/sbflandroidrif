// Generated code from Butter Knife. Do not modify!
package fr.free.nrw.commons.contributions;

import android.view.View;
import android.widget.FrameLayout;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.appcompat.widget.Toolbar;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import fr.free.nrw.commons.R;
import fr.free.nrw.commons.navtab.NavTabLayout;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MainActivity_ViewBinding implements Unbinder {
  private MainActivity target;

  @UiThread
  public MainActivity_ViewBinding(MainActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public MainActivity_ViewBinding(MainActivity target, View source) {
    this.target = target;

    target.toolbar = Utils.findRequiredViewAsType(source, R.id.toolbar, "field 'toolbar'", Toolbar.class);
    target.viewPager = Utils.findRequiredViewAsType(source, R.id.pager, "field 'viewPager'", UnswipableViewPager.class);
    target.fragmentContainer = Utils.findRequiredViewAsType(source, R.id.fragmentContainer, "field 'fragmentContainer'", FrameLayout.class);
    target.tabLayout = Utils.findRequiredViewAsType(source, R.id.fragment_main_nav_tab_layout, "field 'tabLayout'", NavTabLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    MainActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.toolbar = null;
    target.viewPager = null;
    target.fragmentContainer = null;
    target.tabLayout = null;
  }
}
