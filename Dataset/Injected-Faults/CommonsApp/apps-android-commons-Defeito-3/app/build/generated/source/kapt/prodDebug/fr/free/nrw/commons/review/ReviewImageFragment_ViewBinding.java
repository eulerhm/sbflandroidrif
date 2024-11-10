// Generated code from Butter Knife. Do not modify!
package fr.free.nrw.commons.review;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import fr.free.nrw.commons.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ReviewImageFragment_ViewBinding implements Unbinder {
  private ReviewImageFragment target;

  private View view7f09008b;

  @UiThread
  public ReviewImageFragment_ViewBinding(final ReviewImageFragment target, View source) {
    this.target = target;

    View view;
    target.textViewQuestion = Utils.findRequiredViewAsType(source, R.id.tv_review_question, "field 'textViewQuestion'", TextView.class);
    target.textViewQuestionContext = Utils.findRequiredViewAsType(source, R.id.tv_review_question_context, "field 'textViewQuestionContext'", TextView.class);
    view = Utils.findRequiredView(source, R.id.button_yes, "field 'yesButton' and method 'onYesButtonClicked'");
    target.yesButton = Utils.castView(view, R.id.button_yes, "field 'yesButton'", Button.class);
    view7f09008b = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onYesButtonClicked();
      }
    });
    target.noButton = Utils.findRequiredViewAsType(source, R.id.button_no, "field 'noButton'", Button.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    ReviewImageFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.textViewQuestion = null;
    target.textViewQuestionContext = null;
    target.yesButton = null;
    target.noButton = null;

    view7f09008b.setOnClickListener(null);
    view7f09008b = null;
  }
}
