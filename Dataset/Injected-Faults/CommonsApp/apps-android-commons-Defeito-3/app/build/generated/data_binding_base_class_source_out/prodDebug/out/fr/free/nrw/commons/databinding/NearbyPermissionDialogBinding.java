// Generated by view binder compiler. Do not edit!
package fr.free.nrw.commons.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import fr.free.nrw.commons.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class NearbyPermissionDialogBinding implements ViewBinding {
  @NonNull
  private final FrameLayout rootView;

  @NonNull
  public final CheckBox neverAskAgain;

  private NearbyPermissionDialogBinding(@NonNull FrameLayout rootView,
      @NonNull CheckBox neverAskAgain) {
    this.rootView = rootView;
    this.neverAskAgain = neverAskAgain;
  }

  @Override
  @NonNull
  public FrameLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static NearbyPermissionDialogBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static NearbyPermissionDialogBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.nearby_permission_dialog, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static NearbyPermissionDialogBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.never_ask_again;
      CheckBox neverAskAgain = ViewBindings.findChildViewById(rootView, id);
      if (neverAskAgain == null) {
        break missingId;
      }

      return new NearbyPermissionDialogBinding((FrameLayout) rootView, neverAskAgain);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
