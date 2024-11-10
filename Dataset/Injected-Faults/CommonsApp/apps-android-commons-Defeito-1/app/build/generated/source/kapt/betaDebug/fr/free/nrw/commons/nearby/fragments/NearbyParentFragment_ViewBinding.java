// Generated code from Butter Knife. Do not modify!
package fr.free.nrw.commons.nearby.fragments;

import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import fr.free.nrw.commons.R;
import fr.free.nrw.commons.nearby.CheckBoxTriStates;
import java.lang.IllegalStateException;
import java.lang.Override;
import org.osmdroid.views.MapView;

public class NearbyParentFragment_ViewBinding implements Unbinder {
  private NearbyParentFragment target;

  private View view7f090321;

  private View view7f090173;

  @UiThread
  public NearbyParentFragment_ViewBinding(final NearbyParentFragment target, View source) {
    this.target = target;

    View view;
    target.rlBottomSheet = Utils.findRequiredViewAsType(source, R.id.bottom_sheet, "field 'rlBottomSheet'", RelativeLayout.class);
    target.bottomSheetDetails = Utils.findRequiredView(source, R.id.bottom_sheet_details, "field 'bottomSheetDetails'");
    target.transparentView = Utils.findRequiredView(source, R.id.transparentView, "field 'transparentView'");
    target.directionsButtonText = Utils.findRequiredViewAsType(source, R.id.directionsButtonText, "field 'directionsButtonText'", TextView.class);
    target.wikipediaButtonText = Utils.findRequiredViewAsType(source, R.id.wikipediaButtonText, "field 'wikipediaButtonText'", TextView.class);
    target.wikidataButtonText = Utils.findRequiredViewAsType(source, R.id.wikidataButtonText, "field 'wikidataButtonText'", TextView.class);
    target.commonsButtonText = Utils.findRequiredViewAsType(source, R.id.commonsButtonText, "field 'commonsButtonText'", TextView.class);
    target.fabPlus = Utils.findRequiredViewAsType(source, R.id.fab_plus, "field 'fabPlus'", FloatingActionButton.class);
    target.fabCamera = Utils.findRequiredViewAsType(source, R.id.fab_camera, "field 'fabCamera'", FloatingActionButton.class);
    target.fabGallery = Utils.findRequiredViewAsType(source, R.id.fab_gallery, "field 'fabGallery'", FloatingActionButton.class);
    target.fabRecenter = Utils.findRequiredViewAsType(source, R.id.fab_recenter, "field 'fabRecenter'", FloatingActionButton.class);
    target.bookmarkButtonImage = Utils.findRequiredViewAsType(source, R.id.bookmarkButtonImage, "field 'bookmarkButtonImage'", ImageView.class);
    target.bookmarkButton = Utils.findRequiredViewAsType(source, R.id.bookmarkButton, "field 'bookmarkButton'", LinearLayout.class);
    target.wikipediaButton = Utils.findRequiredViewAsType(source, R.id.wikipediaButton, "field 'wikipediaButton'", LinearLayout.class);
    target.wikidataButton = Utils.findRequiredViewAsType(source, R.id.wikidataButton, "field 'wikidataButton'", LinearLayout.class);
    target.directionsButton = Utils.findRequiredViewAsType(source, R.id.directionsButton, "field 'directionsButton'", LinearLayout.class);
    target.commonsButton = Utils.findRequiredViewAsType(source, R.id.commonsButton, "field 'commonsButton'", LinearLayout.class);
    target.description = Utils.findRequiredViewAsType(source, R.id.description, "field 'description'", TextView.class);
    target.title = Utils.findRequiredViewAsType(source, R.id.title, "field 'title'", TextView.class);
    target.distance = Utils.findRequiredViewAsType(source, R.id.category, "field 'distance'", TextView.class);
    target.icon = Utils.findRequiredViewAsType(source, R.id.icon, "field 'icon'", ImageView.class);
    target.searchThisAreaButton = Utils.findRequiredViewAsType(source, R.id.search_this_area_button, "field 'searchThisAreaButton'", Button.class);
    target.progressBar = Utils.findRequiredViewAsType(source, R.id.map_progress_bar, "field 'progressBar'", ProgressBar.class);
    target.chipExists = Utils.findRequiredViewAsType(source, R.id.choice_chip_exists, "field 'chipExists'", Chip.class);
    target.chipWlm = Utils.findRequiredViewAsType(source, R.id.choice_chip_wlm, "field 'chipWlm'", Chip.class);
    target.chipNeedsPhoto = Utils.findRequiredViewAsType(source, R.id.choice_chip_needs_photo, "field 'chipNeedsPhoto'", Chip.class);
    target.choiceChipGroup = Utils.findRequiredViewAsType(source, R.id.choice_chip_group, "field 'choiceChipGroup'", ChipGroup.class);
    target.searchView = Utils.findRequiredViewAsType(source, R.id.search_view, "field 'searchView'", SearchView.class);
    target.recyclerView = Utils.findRequiredViewAsType(source, R.id.search_list_view, "field 'recyclerView'", RecyclerView.class);
    target.nearbyFilterList = Utils.findRequiredView(source, R.id.nearby_filter_list, "field 'nearbyFilterList'");
    target.checkBoxTriStates = Utils.findRequiredViewAsType(source, R.id.checkbox_tri_states, "field 'checkBoxTriStates'", CheckBoxTriStates.class);
    target.mapView = Utils.findRequiredViewAsType(source, R.id.map, "field 'mapView'", MapView.class);
    target.rvNearbyList = Utils.findRequiredViewAsType(source, R.id.rv_nearby_list, "field 'rvNearbyList'", RecyclerView.class);
    target.noResultsView = Utils.findRequiredViewAsType(source, R.id.no_results_message, "field 'noResultsView'", TextView.class);
    target.tvAttribution = Utils.findRequiredViewAsType(source, R.id.tv_attribution, "field 'tvAttribution'", AppCompatTextView.class);
    target.rlContainerWLMMonthMessage = Utils.findRequiredViewAsType(source, R.id.rl_container_wlm_month_message, "field 'rlContainerWLMMonthMessage'", RelativeLayout.class);
    view = Utils.findRequiredView(source, R.id.tv_learn_more, "field 'tvLearnMore' and method 'onLearnMoreClicked'");
    target.tvLearnMore = Utils.castView(view, R.id.tv_learn_more, "field 'tvLearnMore'", AppCompatTextView.class);
    view7f090321 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onLearnMoreClicked();
      }
    });
    view = Utils.findRequiredView(source, R.id.iv_toggle_chips, "field 'ivToggleChips' and method 'onToggleChipsClicked'");
    target.ivToggleChips = Utils.castView(view, R.id.iv_toggle_chips, "field 'ivToggleChips'", AppCompatImageView.class);
    view7f090173 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onToggleChipsClicked();
      }
    });
    target.llContainerChips = Utils.findRequiredView(source, R.id.chip_view, "field 'llContainerChips'");
    target.btnAdvancedOptions = Utils.findRequiredViewAsType(source, R.id.btn_advanced_options, "field 'btnAdvancedOptions'", AppCompatButton.class);
    target.flConainerNearbyChildren = Utils.findRequiredViewAsType(source, R.id.fl_container_nearby_children, "field 'flConainerNearbyChildren'", FrameLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    NearbyParentFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.rlBottomSheet = null;
    target.bottomSheetDetails = null;
    target.transparentView = null;
    target.directionsButtonText = null;
    target.wikipediaButtonText = null;
    target.wikidataButtonText = null;
    target.commonsButtonText = null;
    target.fabPlus = null;
    target.fabCamera = null;
    target.fabGallery = null;
    target.fabRecenter = null;
    target.bookmarkButtonImage = null;
    target.bookmarkButton = null;
    target.wikipediaButton = null;
    target.wikidataButton = null;
    target.directionsButton = null;
    target.commonsButton = null;
    target.description = null;
    target.title = null;
    target.distance = null;
    target.icon = null;
    target.searchThisAreaButton = null;
    target.progressBar = null;
    target.chipExists = null;
    target.chipWlm = null;
    target.chipNeedsPhoto = null;
    target.choiceChipGroup = null;
    target.searchView = null;
    target.recyclerView = null;
    target.nearbyFilterList = null;
    target.checkBoxTriStates = null;
    target.mapView = null;
    target.rvNearbyList = null;
    target.noResultsView = null;
    target.tvAttribution = null;
    target.rlContainerWLMMonthMessage = null;
    target.tvLearnMore = null;
    target.ivToggleChips = null;
    target.llContainerChips = null;
    target.btnAdvancedOptions = null;
    target.flConainerNearbyChildren = null;

    view7f090321.setOnClickListener(null);
    view7f090321 = null;
    view7f090173.setOnClickListener(null);
    view7f090173 = null;
  }
}
