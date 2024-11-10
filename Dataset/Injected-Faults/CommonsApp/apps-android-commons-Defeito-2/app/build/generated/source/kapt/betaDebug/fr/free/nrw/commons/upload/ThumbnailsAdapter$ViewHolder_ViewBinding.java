// Generated code from Butter Knife. Do not modify!
package fr.free.nrw.commons.upload;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.facebook.drawee.view.SimpleDraweeView;
import fr.free.nrw.commons.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ThumbnailsAdapter$ViewHolder_ViewBinding implements Unbinder {
  private ThumbnailsAdapter.ViewHolder target;

  @UiThread
  public ThumbnailsAdapter$ViewHolder_ViewBinding(ThumbnailsAdapter.ViewHolder target,
      View source) {
    this.target = target;

    target.rlContainer = Utils.findRequiredViewAsType(source, R.id.rl_container, "field 'rlContainer'", RelativeLayout.class);
    target.background = Utils.findRequiredViewAsType(source, R.id.iv_thumbnail, "field 'background'", SimpleDraweeView.class);
    target.ivError = Utils.findRequiredViewAsType(source, R.id.iv_error, "field 'ivError'", ImageView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    ThumbnailsAdapter.ViewHolder target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.rlContainer = null;
    target.background = null;
    target.ivError = null;
  }
}
