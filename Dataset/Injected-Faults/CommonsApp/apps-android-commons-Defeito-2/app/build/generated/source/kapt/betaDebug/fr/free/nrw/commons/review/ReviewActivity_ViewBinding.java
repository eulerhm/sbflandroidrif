// Generated code from Butter Knife. Do not modify!
package fr.free.nrw.commons.review;

import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.facebook.drawee.view.SimpleDraweeView;
import com.viewpagerindicator.CirclePageIndicator;
import fr.free.nrw.commons.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ReviewActivity_ViewBinding implements Unbinder {
  private ReviewActivity target;

  @UiThread
  public ReviewActivity_ViewBinding(ReviewActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public ReviewActivity_ViewBinding(ReviewActivity target, View source) {
    this.target = target;

    target.pagerIndicator = Utils.findRequiredViewAsType(source, R.id.pager_indicator_review, "field 'pagerIndicator'", CirclePageIndicator.class);
    target.toolbar = Utils.findRequiredViewAsType(source, R.id.toolbar, "field 'toolbar'", Toolbar.class);
    target.drawerLayout = Utils.findRequiredViewAsType(source, R.id.drawer_layout, "field 'drawerLayout'", DrawerLayout.class);
    target.reviewPager = Utils.findRequiredViewAsType(source, R.id.view_pager_review, "field 'reviewPager'", ReviewViewPager.class);
    target.btnSkipImage = Utils.findRequiredViewAsType(source, R.id.skip_image, "field 'btnSkipImage'", Button.class);
    target.simpleDraweeView = Utils.findRequiredViewAsType(source, R.id.review_image_view, "field 'simpleDraweeView'", SimpleDraweeView.class);
    target.progressBar = Utils.findRequiredViewAsType(source, R.id.pb_review_image, "field 'progressBar'", ProgressBar.class);
    target.imageCaption = Utils.findRequiredViewAsType(source, R.id.tv_image_caption, "field 'imageCaption'", TextView.class);
    target.mediaDetailContainer = Utils.findRequiredViewAsType(source, R.id.mediaDetailContainer, "field 'mediaDetailContainer'", FrameLayout.class);
    target.reviewContainer = Utils.findRequiredViewAsType(source, R.id.reviewActivityContainer, "field 'reviewContainer'", LinearLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    ReviewActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.pagerIndicator = null;
    target.toolbar = null;
    target.drawerLayout = null;
    target.reviewPager = null;
    target.btnSkipImage = null;
    target.simpleDraweeView = null;
    target.progressBar = null;
    target.imageCaption = null;
    target.mediaDetailContainer = null;
    target.reviewContainer = null;
  }
}
