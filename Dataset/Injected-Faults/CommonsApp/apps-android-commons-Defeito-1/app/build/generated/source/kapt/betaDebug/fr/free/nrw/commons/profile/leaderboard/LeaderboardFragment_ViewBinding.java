// Generated code from Butter Knife. Do not modify!
package fr.free.nrw.commons.profile.leaderboard;

import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Spinner;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import fr.free.nrw.commons.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class LeaderboardFragment_ViewBinding implements Unbinder {
  private LeaderboardFragment target;

  @UiThread
  public LeaderboardFragment_ViewBinding(LeaderboardFragment target, View source) {
    this.target = target;

    target.leaderboardListRecyclerView = Utils.findRequiredViewAsType(source, R.id.leaderboard_list, "field 'leaderboardListRecyclerView'", RecyclerView.class);
    target.progressBar = Utils.findRequiredViewAsType(source, R.id.progressBar, "field 'progressBar'", ProgressBar.class);
    target.categorySpinner = Utils.findRequiredViewAsType(source, R.id.category_spinner, "field 'categorySpinner'", Spinner.class);
    target.durationSpinner = Utils.findRequiredViewAsType(source, R.id.duration_spinner, "field 'durationSpinner'", Spinner.class);
    target.scrollButton = Utils.findRequiredViewAsType(source, R.id.scroll, "field 'scrollButton'", Button.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    LeaderboardFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.leaderboardListRecyclerView = null;
    target.progressBar = null;
    target.categorySpinner = null;
    target.durationSpinner = null;
    target.scrollButton = null;
  }
}
