// Generated code from Butter Knife. Do not modify!
package fr.free.nrw.commons.notification;

import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import fr.free.nrw.commons.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class NotificationActivity_ViewBinding implements Unbinder {
  private NotificationActivity target;

  @UiThread
  public NotificationActivity_ViewBinding(NotificationActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public NotificationActivity_ViewBinding(NotificationActivity target, View source) {
    this.target = target;

    target.recyclerView = Utils.findRequiredViewAsType(source, R.id.listView, "field 'recyclerView'", RecyclerView.class);
    target.progressBar = Utils.findRequiredViewAsType(source, R.id.progressBar, "field 'progressBar'", ProgressBar.class);
    target.relativeLayout = Utils.findRequiredViewAsType(source, R.id.container, "field 'relativeLayout'", RelativeLayout.class);
    target.no_notification = Utils.findRequiredViewAsType(source, R.id.no_notification_background, "field 'no_notification'", ConstraintLayout.class);
    target.noNotificationText = Utils.findRequiredViewAsType(source, R.id.no_notification_text, "field 'noNotificationText'", TextView.class);
    target.toolbar = Utils.findRequiredViewAsType(source, R.id.toolbar, "field 'toolbar'", Toolbar.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    NotificationActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.recyclerView = null;
    target.progressBar = null;
    target.relativeLayout = null;
    target.no_notification = null;
    target.noNotificationText = null;
    target.toolbar = null;
  }
}
