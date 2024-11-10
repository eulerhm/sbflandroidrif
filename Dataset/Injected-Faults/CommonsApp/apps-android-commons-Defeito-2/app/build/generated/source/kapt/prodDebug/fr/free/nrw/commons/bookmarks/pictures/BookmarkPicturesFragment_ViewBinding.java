// Generated code from Butter Knife. Do not modify!
package fr.free.nrw.commons.bookmarks.pictures;

import android.view.View;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import fr.free.nrw.commons.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class BookmarkPicturesFragment_ViewBinding implements Unbinder {
  private BookmarkPicturesFragment target;

  @UiThread
  public BookmarkPicturesFragment_ViewBinding(BookmarkPicturesFragment target, View source) {
    this.target = target;

    target.statusTextView = Utils.findRequiredViewAsType(source, R.id.statusMessage, "field 'statusTextView'", TextView.class);
    target.progressBar = Utils.findRequiredViewAsType(source, R.id.loadingImagesProgressBar, "field 'progressBar'", ProgressBar.class);
    target.gridView = Utils.findRequiredViewAsType(source, R.id.bookmarkedPicturesList, "field 'gridView'", GridView.class);
    target.parentLayout = Utils.findRequiredViewAsType(source, R.id.parentLayout, "field 'parentLayout'", RelativeLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    BookmarkPicturesFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.statusTextView = null;
    target.progressBar = null;
    target.gridView = null;
    target.parentLayout = null;
  }
}
