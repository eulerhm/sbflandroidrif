// Generated code from Butter Knife. Do not modify!
package fr.free.nrw.commons.explore.map;

import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.appcompat.widget.AppCompatTextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import fr.free.nrw.commons.R;
import java.lang.IllegalStateException;
import java.lang.Override;
import org.osmdroid.views.MapView;

public class ExploreMapFragment_ViewBinding implements Unbinder {
  private ExploreMapFragment target;

  @UiThread
  public ExploreMapFragment_ViewBinding(ExploreMapFragment target, View source) {
    this.target = target;

    target.mapView = Utils.findRequiredViewAsType(source, R.id.map_view, "field 'mapView'", MapView.class);
    target.bottomSheetDetails = Utils.findRequiredView(source, R.id.bottom_sheet_details, "field 'bottomSheetDetails'");
    target.progressBar = Utils.findRequiredViewAsType(source, R.id.map_progress_bar, "field 'progressBar'", ProgressBar.class);
    target.fabRecenter = Utils.findRequiredViewAsType(source, R.id.fab_recenter, "field 'fabRecenter'", FloatingActionButton.class);
    target.searchThisAreaButton = Utils.findRequiredViewAsType(source, R.id.search_this_area_button, "field 'searchThisAreaButton'", Button.class);
    target.tvAttribution = Utils.findRequiredViewAsType(source, R.id.tv_attribution, "field 'tvAttribution'", AppCompatTextView.class);
    target.directionsButton = Utils.findRequiredViewAsType(source, R.id.directionsButton, "field 'directionsButton'", LinearLayout.class);
    target.commonsButton = Utils.findRequiredViewAsType(source, R.id.commonsButton, "field 'commonsButton'", LinearLayout.class);
    target.mediaDetailsButton = Utils.findRequiredViewAsType(source, R.id.mediaDetailsButton, "field 'mediaDetailsButton'", LinearLayout.class);
    target.description = Utils.findRequiredViewAsType(source, R.id.description, "field 'description'", TextView.class);
    target.title = Utils.findRequiredViewAsType(source, R.id.title, "field 'title'", TextView.class);
    target.distance = Utils.findRequiredViewAsType(source, R.id.category, "field 'distance'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    ExploreMapFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mapView = null;
    target.bottomSheetDetails = null;
    target.progressBar = null;
    target.fabRecenter = null;
    target.searchThisAreaButton = null;
    target.tvAttribution = null;
    target.directionsButton = null;
    target.commonsButton = null;
    target.mediaDetailsButton = null;
    target.description = null;
    target.title = null;
    target.distance = null;
  }
}
