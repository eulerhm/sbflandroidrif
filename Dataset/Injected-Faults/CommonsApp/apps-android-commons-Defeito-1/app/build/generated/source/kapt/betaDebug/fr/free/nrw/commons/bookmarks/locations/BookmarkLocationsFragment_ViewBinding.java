// Generated code from Butter Knife. Do not modify!
package fr.free.nrw.commons.bookmarks.locations;

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

public class BookmarkLocationsFragment_ViewBinding implements Unbinder {
  private BookmarkLocationsFragment target;

  @UiThread
  public BookmarkLocationsFragment_ViewBinding(BookmarkLocationsFragment target, View source) {
    this.target = target;

    target.statusTextView = Utils.findRequiredViewAsType(source, R.id.statusMessage, "field 'statusTextView'", TextView.class);
    target.progressBar = Utils.findRequiredViewAsType(source, R.id.loadingImagesProgressBar, "field 'progressBar'", ProgressBar.class);
    target.recyclerView = Utils.findRequiredViewAsType(source, R.id.listView, "field 'recyclerView'", RecyclerView.class);
    target.parentLayout = Utils.findRequiredViewAsType(source, R.id.parentLayout, "field 'parentLayout'", RelativeLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    BookmarkLocationsFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.statusTextView = null;
    target.progressBar = null;
    target.recyclerView = null;
    target.parentLayout = null;
  }
}
