// Generated code from Butter Knife. Do not modify!
package fr.free.nrw.commons.explore.recentsearches;

import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import fr.free.nrw.commons.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class RecentSearchesFragment_ViewBinding implements Unbinder {
  private RecentSearchesFragment target;

  @UiThread
  public RecentSearchesFragment_ViewBinding(RecentSearchesFragment target, View source) {
    this.target = target;

    target.recentSearchesList = Utils.findRequiredViewAsType(source, R.id.recent_searches_list, "field 'recentSearchesList'", ListView.class);
    target.recent_searches_delete_button = Utils.findRequiredViewAsType(source, R.id.recent_searches_delete_button, "field 'recent_searches_delete_button'", ImageView.class);
    target.recent_searches_text_view = Utils.findRequiredViewAsType(source, R.id.recent_searches_text_view, "field 'recent_searches_text_view'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    RecentSearchesFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.recentSearchesList = null;
    target.recent_searches_delete_button = null;
    target.recent_searches_text_view = null;
  }
}
