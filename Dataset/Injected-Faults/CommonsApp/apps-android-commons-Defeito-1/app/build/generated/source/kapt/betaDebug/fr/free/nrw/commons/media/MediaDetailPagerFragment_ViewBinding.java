// Generated code from Butter Knife. Do not modify!
package fr.free.nrw.commons.media;

import android.view.View;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.viewpager.widget.ViewPager;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import fr.free.nrw.commons.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MediaDetailPagerFragment_ViewBinding implements Unbinder {
  private MediaDetailPagerFragment target;

  @UiThread
  public MediaDetailPagerFragment_ViewBinding(MediaDetailPagerFragment target, View source) {
    this.target = target;

    target.pager = Utils.findRequiredViewAsType(source, R.id.mediaDetailsPager, "field 'pager'", ViewPager.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    MediaDetailPagerFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.pager = null;
  }
}
