// Generated by view binder compiler. Do not edit!
package fr.free.nrw.commons.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.SearchView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;
import fr.free.nrw.commons.R;
import fr.free.nrw.commons.explore.ParentViewPager;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivitySearchBinding implements ViewBinding {
  @NonNull
  private final DrawerLayout rootView;

  @NonNull
  public final DrawerLayout drawerLayout;

  @NonNull
  public final FrameLayout mediaContainer;

  @NonNull
  public final SearchView searchBox;

  @NonNull
  public final FrameLayout searchHistoryContainer;

  @NonNull
  public final TabLayout tabLayout;

  @NonNull
  public final AppBarLayout toolbarLayout;

  @NonNull
  public final Toolbar toolbarSearch;

  @NonNull
  public final ParentViewPager viewPager;

  private ActivitySearchBinding(@NonNull DrawerLayout rootView, @NonNull DrawerLayout drawerLayout,
      @NonNull FrameLayout mediaContainer, @NonNull SearchView searchBox,
      @NonNull FrameLayout searchHistoryContainer, @NonNull TabLayout tabLayout,
      @NonNull AppBarLayout toolbarLayout, @NonNull Toolbar toolbarSearch,
      @NonNull ParentViewPager viewPager) {
    this.rootView = rootView;
    this.drawerLayout = drawerLayout;
    this.mediaContainer = mediaContainer;
    this.searchBox = searchBox;
    this.searchHistoryContainer = searchHistoryContainer;
    this.tabLayout = tabLayout;
    this.toolbarLayout = toolbarLayout;
    this.toolbarSearch = toolbarSearch;
    this.viewPager = viewPager;
  }

  @Override
  @NonNull
  public DrawerLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivitySearchBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivitySearchBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_search, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivitySearchBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      DrawerLayout drawerLayout = (DrawerLayout) rootView;

      id = R.id.mediaContainer;
      FrameLayout mediaContainer = ViewBindings.findChildViewById(rootView, id);
      if (mediaContainer == null) {
        break missingId;
      }

      id = R.id.searchBox;
      SearchView searchBox = ViewBindings.findChildViewById(rootView, id);
      if (searchBox == null) {
        break missingId;
      }

      id = R.id.searchHistoryContainer;
      FrameLayout searchHistoryContainer = ViewBindings.findChildViewById(rootView, id);
      if (searchHistoryContainer == null) {
        break missingId;
      }

      id = R.id.tab_layout;
      TabLayout tabLayout = ViewBindings.findChildViewById(rootView, id);
      if (tabLayout == null) {
        break missingId;
      }

      id = R.id.toolbar_layout;
      AppBarLayout toolbarLayout = ViewBindings.findChildViewById(rootView, id);
      if (toolbarLayout == null) {
        break missingId;
      }

      id = R.id.toolbar_search;
      Toolbar toolbarSearch = ViewBindings.findChildViewById(rootView, id);
      if (toolbarSearch == null) {
        break missingId;
      }

      id = R.id.viewPager;
      ParentViewPager viewPager = ViewBindings.findChildViewById(rootView, id);
      if (viewPager == null) {
        break missingId;
      }

      return new ActivitySearchBinding((DrawerLayout) rootView, drawerLayout, mediaContainer,
          searchBox, searchHistoryContainer, tabLayout, toolbarLayout, toolbarSearch, viewPager);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
