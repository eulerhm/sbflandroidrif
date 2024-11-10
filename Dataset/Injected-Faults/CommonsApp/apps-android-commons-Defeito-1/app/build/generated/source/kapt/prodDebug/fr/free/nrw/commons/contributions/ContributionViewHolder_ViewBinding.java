// Generated code from Butter Knife. Do not modify!
package fr.free.nrw.commons.contributions;

import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.facebook.drawee.view.SimpleDraweeView;
import fr.free.nrw.commons.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ContributionViewHolder_ViewBinding implements Unbinder {
  private ContributionViewHolder target;

  private View view7f0900c9;

  private View view7f090358;

  private View view7f090274;

  private View view7f090090;

  private View view7f090247;

  @UiThread
  public ContributionViewHolder_ViewBinding(final ContributionViewHolder target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.contributionImage, "field 'imageView' and method 'imageClicked'");
    target.imageView = Utils.castView(view, R.id.contributionImage, "field 'imageView'", SimpleDraweeView.class);
    view7f0900c9 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.imageClicked();
      }
    });
    target.titleView = Utils.findRequiredViewAsType(source, R.id.contributionTitle, "field 'titleView'", TextView.class);
    target.authorView = Utils.findRequiredViewAsType(source, R.id.authorView, "field 'authorView'", TextView.class);
    target.stateView = Utils.findRequiredViewAsType(source, R.id.contributionState, "field 'stateView'", TextView.class);
    target.seqNumView = Utils.findRequiredViewAsType(source, R.id.contributionSequenceNumber, "field 'seqNumView'", TextView.class);
    target.progressView = Utils.findRequiredViewAsType(source, R.id.contributionProgress, "field 'progressView'", ProgressBar.class);
    target.imageOptions = Utils.findRequiredViewAsType(source, R.id.image_options, "field 'imageOptions'", RelativeLayout.class);
    view = Utils.findRequiredView(source, R.id.wikipediaButton, "field 'addToWikipediaButton' and method 'wikipediaButtonClicked'");
    target.addToWikipediaButton = Utils.castView(view, R.id.wikipediaButton, "field 'addToWikipediaButton'", ImageButton.class);
    view7f090358 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.wikipediaButtonClicked();
      }
    });
    view = Utils.findRequiredView(source, R.id.retryButton, "field 'retryButton' and method 'retryUpload'");
    target.retryButton = Utils.castView(view, R.id.retryButton, "field 'retryButton'", ImageButton.class);
    view7f090274 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.retryUpload();
      }
    });
    view = Utils.findRequiredView(source, R.id.cancelButton, "field 'cancelButton' and method 'deleteUpload'");
    target.cancelButton = Utils.castView(view, R.id.cancelButton, "field 'cancelButton'", ImageButton.class);
    view7f090090 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.deleteUpload();
      }
    });
    view = Utils.findRequiredView(source, R.id.pauseResumeButton, "field 'pauseResumeButton' and method 'onPauseResumeButtonClicked'");
    target.pauseResumeButton = Utils.castView(view, R.id.pauseResumeButton, "field 'pauseResumeButton'", ImageButton.class);
    view7f090247 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onPauseResumeButtonClicked();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    ContributionViewHolder target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.imageView = null;
    target.titleView = null;
    target.authorView = null;
    target.stateView = null;
    target.seqNumView = null;
    target.progressView = null;
    target.imageOptions = null;
    target.addToWikipediaButton = null;
    target.retryButton = null;
    target.cancelButton = null;
    target.pauseResumeButton = null;

    view7f0900c9.setOnClickListener(null);
    view7f0900c9 = null;
    view7f090358.setOnClickListener(null);
    view7f090358 = null;
    view7f090274.setOnClickListener(null);
    view7f090274 = null;
    view7f090090.setOnClickListener(null);
    view7f090090 = null;
    view7f090247.setOnClickListener(null);
    view7f090247 = null;
  }
}
