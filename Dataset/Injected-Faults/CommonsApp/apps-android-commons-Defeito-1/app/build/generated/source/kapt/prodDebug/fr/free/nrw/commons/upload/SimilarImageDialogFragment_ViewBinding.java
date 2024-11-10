// Generated code from Butter Knife. Do not modify!
package fr.free.nrw.commons.upload;

import android.view.View;
import android.widget.Button;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.facebook.drawee.view.SimpleDraweeView;
import fr.free.nrw.commons.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class SimilarImageDialogFragment_ViewBinding implements Unbinder {
  private SimilarImageDialogFragment target;

  private View view7f090253;

  private View view7f09021f;

  @UiThread
  public SimilarImageDialogFragment_ViewBinding(final SimilarImageDialogFragment target,
      View source) {
    this.target = target;

    View view;
    target.originalImage = Utils.findRequiredViewAsType(source, R.id.orginalImage, "field 'originalImage'", SimpleDraweeView.class);
    target.possibleImage = Utils.findRequiredViewAsType(source, R.id.possibleImage, "field 'possibleImage'", SimpleDraweeView.class);
    view = Utils.findRequiredView(source, R.id.postive_button, "field 'positiveButton' and method 'onPositiveButtonClicked'");
    target.positiveButton = Utils.castView(view, R.id.postive_button, "field 'positiveButton'", Button.class);
    view7f090253 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onPositiveButtonClicked();
      }
    });
    view = Utils.findRequiredView(source, R.id.negative_button, "field 'negativeButton' and method 'onNegativeButtonClicked'");
    target.negativeButton = Utils.castView(view, R.id.negative_button, "field 'negativeButton'", Button.class);
    view7f09021f = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onNegativeButtonClicked();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    SimilarImageDialogFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.originalImage = null;
    target.possibleImage = null;
    target.positiveButton = null;
    target.negativeButton = null;

    view7f090253.setOnClickListener(null);
    view7f090253 = null;
    view7f09021f.setOnClickListener(null);
    view7f09021f = null;
  }
}
