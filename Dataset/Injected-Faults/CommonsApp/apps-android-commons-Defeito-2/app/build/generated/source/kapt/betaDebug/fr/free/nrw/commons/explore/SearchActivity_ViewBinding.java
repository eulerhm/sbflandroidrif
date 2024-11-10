// Generated code from Butter Knife. Do not modify!
package fr.free.nrw.commons.explore;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.SearchView;
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

public class SearchActivity_ViewBinding implements Unbinder {
  private SearchActivity target;

  @UiThread
  public SearchActivity_ViewBinding(SearchActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public SearchActivity_ViewBinding(SearchActivity target, View source) {
    this.target = target;

    target.toolbar = Utils.findRequiredViewAsType(source, R.id.toolbar_search, "field 'toolbar'", Toolbar.class);
    target.searchHistoryContainer = Utils.findRequiredViewAsType(source, R.id.searchHistoryContainer, "field 'searchHistoryContainer'", FrameLayout.class);
    target.mediaContainer = Utils.findRequiredViewAsType(source, R.id.mediaContainer, "field 'mediaContainer'", FrameLayout.class);
    target.searchView = Utils.findRequiredViewAsType(source, R.id.searchBox, "field 'searchView'", SearchView.class);
    target.tabLayout = Utils.findRequiredViewAsType(source, R.id.tab_layout, "field 'tabLayout'", TabLayout.class);
    target.viewPager = Utils.findRequiredViewAsType(source, R.id.viewPager, "field 'viewPager'", ViewPager.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    SearchActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.toolbar = null;
    target.searchHistoryContainer = null;
    target.mediaContainer = null;
    target.searchView = null;
    target.tabLayout = null;
    target.viewPager = null;
  }
}
