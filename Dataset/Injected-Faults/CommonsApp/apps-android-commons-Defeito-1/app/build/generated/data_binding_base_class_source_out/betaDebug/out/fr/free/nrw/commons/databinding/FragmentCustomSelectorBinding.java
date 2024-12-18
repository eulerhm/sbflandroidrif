// Generated by view binder compiler. Do not edit!
package fr.free.nrw.commons.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.simplecityapps.recyclerview_fastscroll.views.FastScrollRecyclerView;
import fr.free.nrw.commons.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentCustomSelectorBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final TextView emptyText;

  @NonNull
  public final ProgressBar loader;

  @NonNull
  public final ProgressBar progressBar;

  @NonNull
  public final ConstraintLayout progressLayout;

  @NonNull
  public final FastScrollRecyclerView selectorRv;

  @NonNull
  public final Switch switchWidget;

  @NonNull
  public final TextView text;

  private FragmentCustomSelectorBinding(@NonNull ConstraintLayout rootView,
      @NonNull TextView emptyText, @NonNull ProgressBar loader, @NonNull ProgressBar progressBar,
      @NonNull ConstraintLayout progressLayout, @NonNull FastScrollRecyclerView selectorRv,
      @NonNull Switch switchWidget, @NonNull TextView text) {
    this.rootView = rootView;
    this.emptyText = emptyText;
    this.loader = loader;
    this.progressBar = progressBar;
    this.progressLayout = progressLayout;
    this.selectorRv = selectorRv;
    this.switchWidget = switchWidget;
    this.text = text;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentCustomSelectorBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentCustomSelectorBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_custom_selector, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentCustomSelectorBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.empty_text;
      TextView emptyText = ViewBindings.findChildViewById(rootView, id);
      if (emptyText == null) {
        break missingId;
      }

      id = R.id.loader;
      ProgressBar loader = ViewBindings.findChildViewById(rootView, id);
      if (loader == null) {
        break missingId;
      }

      id = R.id.progressBar;
      ProgressBar progressBar = ViewBindings.findChildViewById(rootView, id);
      if (progressBar == null) {
        break missingId;
      }

      id = R.id.progressLayout;
      ConstraintLayout progressLayout = ViewBindings.findChildViewById(rootView, id);
      if (progressLayout == null) {
        break missingId;
      }

      id = R.id.selector_rv;
      FastScrollRecyclerView selectorRv = ViewBindings.findChildViewById(rootView, id);
      if (selectorRv == null) {
        break missingId;
      }

      id = R.id.switchWidget;
      Switch switchWidget = ViewBindings.findChildViewById(rootView, id);
      if (switchWidget == null) {
        break missingId;
      }

      id = R.id.text;
      TextView text = ViewBindings.findChildViewById(rootView, id);
      if (text == null) {
        break missingId;
      }

      return new FragmentCustomSelectorBinding((ConstraintLayout) rootView, emptyText, loader,
          progressBar, progressLayout, selectorRv, switchWidget, text);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
