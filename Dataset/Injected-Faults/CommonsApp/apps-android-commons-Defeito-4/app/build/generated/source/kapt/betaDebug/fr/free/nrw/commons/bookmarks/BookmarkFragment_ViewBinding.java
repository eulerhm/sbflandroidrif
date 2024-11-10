// Generated code from Butter Knife. Do not modify!
package fr.free.nrw.commons.bookmarks;

import android.view.View;
import android.widget.FrameLayout;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.google.android.material.tabs.TabLayout;
import fr.free.nrw.commons.R;
import fr.free.nrw.commons.explore.ParentViewPager;
import java.lang.IllegalStateException;
import java.lang.Override;

public class BookmarkFragment_ViewBinding implements Unbinder {
  private BookmarkFragment target;

  @UiThread
  public BookmarkFragment_ViewBinding(BookmarkFragment target, View source) {
    this.target = target;

    target.viewPager = Utils.findRequiredViewAsType(source, R.id.viewPagerBookmarks, "field 'viewPager'", ParentViewPager.class);
    target.tabLayout = Utils.findRequiredViewAsType(source, R.id.tab_layout, "field 'tabLayout'", TabLayout.class);
    target.fragmentContainer = Utils.findRequiredViewAsType(source, R.id.fragmentContainer, "field 'fragmentContainer'", FrameLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    BookmarkFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.viewPager = null;
    target.tabLayout = null;
    target.fragmentContainer = null;
  }
}
