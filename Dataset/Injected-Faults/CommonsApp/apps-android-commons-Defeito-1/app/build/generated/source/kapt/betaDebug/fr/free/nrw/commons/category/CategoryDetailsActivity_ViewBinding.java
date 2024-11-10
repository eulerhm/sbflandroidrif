// Generated code from Butter Knife. Do not modify!
package fr.free.nrw.commons.category;

import android.view.View;
import android.widget.FrameLayout;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.google.android.material.tabs.TabLayout;
import fr.free.nrw.commons.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class CategoryDetailsActivity_ViewBinding implements Unbinder {
  private CategoryDetailsActivity target;

  @UiThread
  public CategoryDetailsActivity_ViewBinding(CategoryDetailsActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public CategoryDetailsActivity_ViewBinding(CategoryDetailsActivity target, View source) {
    this.target = target;

    target.mediaContainer = Utils.findRequiredViewAsType(source, R.id.mediaContainer, "field 'mediaContainer'", FrameLayout.class);
    target.tabLayout = Utils.findRequiredViewAsType(source, R.id.tab_layout, "field 'tabLayout'", TabLayout.class);
    target.viewPager = Utils.findRequiredViewAsType(source, R.id.viewPager, "field 'viewPager'", ViewPager.class);
    target.toolbar = Utils.findRequiredViewAsType(source, R.id.toolbar, "field 'toolbar'", Toolbar.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    CategoryDetailsActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mediaContainer = null;
    target.tabLayout = null;
    target.viewPager = null;
    target.toolbar = null;
  }
}
