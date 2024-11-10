// Generated code from Butter Knife. Do not modify!
package fr.free.nrw.commons.upload;

import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import fr.free.nrw.commons.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class UploadActivity_ViewBinding implements Unbinder {
  private UploadActivity target;

  private View view7f09027d;

  @UiThread
  public UploadActivity_ViewBinding(UploadActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public UploadActivity_ViewBinding(final UploadActivity target, View source) {
    this.target = target;

    View view;
    target.cvContainerTopCard = Utils.findRequiredViewAsType(source, R.id.cv_container_top_card, "field 'cvContainerTopCard'", CardView.class);
    target.llContainerTopCard = Utils.findRequiredViewAsType(source, R.id.ll_container_top_card, "field 'llContainerTopCard'", LinearLayout.class);
    view = Utils.findRequiredView(source, R.id.rl_container_title, "field 'rlContainerTitle' and method 'onRlContainerTitleClicked'");
    target.rlContainerTitle = Utils.castView(view, R.id.rl_container_title, "field 'rlContainerTitle'", RelativeLayout.class);
    view7f09027d = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onRlContainerTitleClicked();
      }
    });
    target.tvTopCardTitle = Utils.findRequiredViewAsType(source, R.id.tv_top_card_title, "field 'tvTopCardTitle'", TextView.class);
    target.ibToggleTopCard = Utils.findRequiredViewAsType(source, R.id.ib_toggle_top_card, "field 'ibToggleTopCard'", ImageButton.class);
    target.rvThumbnails = Utils.findRequiredViewAsType(source, R.id.rv_thumbnails, "field 'rvThumbnails'", RecyclerView.class);
    target.vpUpload = Utils.findRequiredViewAsType(source, R.id.vp_upload, "field 'vpUpload'", ViewPager.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    UploadActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.cvContainerTopCard = null;
    target.llContainerTopCard = null;
    target.rlContainerTitle = null;
    target.tvTopCardTitle = null;
    target.ibToggleTopCard = null;
    target.rvThumbnails = null;
    target.vpUpload = null;

    view7f09027d.setOnClickListener(null);
    view7f09027d = null;
  }
}
