// Generated code from Butter Knife. Do not modify!
package fr.free.nrw.commons.media;

import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import fr.free.nrw.commons.R;
import fr.free.nrw.commons.media.zoomControllers.zoomable.ZoomableDraweeView;
import java.lang.IllegalStateException;
import java.lang.Override;

public final class ZoomableActivity_ViewBinding implements Unbinder {
  private ZoomableActivity target;

  @UiThread
  public ZoomableActivity_ViewBinding(ZoomableActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public ZoomableActivity_ViewBinding(ZoomableActivity target, View source) {
    this.target = target;

    target.photo = Utils.findOptionalViewAsType(source, R.id.zoomable, "field 'photo'", ZoomableDraweeView.class);
    target.spinner = Utils.findOptionalViewAsType(source, R.id.zoom_progress_bar, "field 'spinner'", ProgressBar.class);
    target.selectedCount = Utils.findOptionalViewAsType(source, R.id.selection_count, "field 'selectedCount'", TextView.class);
  }

  @Override
  public void unbind() {
    ZoomableActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.photo = null;
    target.spinner = null;
    target.selectedCount = null;
  }
}
