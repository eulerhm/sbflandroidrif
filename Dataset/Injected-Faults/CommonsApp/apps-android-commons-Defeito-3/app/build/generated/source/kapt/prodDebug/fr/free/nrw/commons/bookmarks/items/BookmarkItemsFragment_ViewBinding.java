// Generated code from Butter Knife. Do not modify!
package fr.free.nrw.commons.bookmarks.items;

import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import fr.free.nrw.commons.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class BookmarkItemsFragment_ViewBinding implements Unbinder {
  private BookmarkItemsFragment target;

  @UiThread
  public BookmarkItemsFragment_ViewBinding(BookmarkItemsFragment target, View source) {
    this.target = target;

    target.statusTextView = Utils.findRequiredViewAsType(source, R.id.status_message, "field 'statusTextView'", TextView.class);
    target.progressBar = Utils.findRequiredViewAsType(source, R.id.loading_images_progress_bar, "field 'progressBar'", ProgressBar.class);
    target.recyclerView = Utils.findRequiredViewAsType(source, R.id.list_view, "field 'recyclerView'", RecyclerView.class);
    target.parentLayout = Utils.findRequiredViewAsType(source, R.id.parent_layout, "field 'parentLayout'", RelativeLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    BookmarkItemsFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.statusTextView = null;
    target.progressBar = null;
    target.recyclerView = null;
    target.parentLayout = null;
  }
}
