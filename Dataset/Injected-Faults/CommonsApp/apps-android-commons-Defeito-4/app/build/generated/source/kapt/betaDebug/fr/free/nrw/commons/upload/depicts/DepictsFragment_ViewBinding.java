// Generated code from Butter Knife. Do not modify!
package fr.free.nrw.commons.upload.depicts;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.google.android.material.textfield.TextInputLayout;
import fr.free.nrw.commons.R;
import fr.free.nrw.commons.ui.PasteSensitiveTextInputEditText;
import java.lang.IllegalStateException;
import java.lang.Override;

public class DepictsFragment_ViewBinding implements Unbinder {
  private DepictsFragment target;

  private View view7f0900e2;

  private View view7f0900e3;

  @UiThread
  public DepictsFragment_ViewBinding(final DepictsFragment target, View source) {
    this.target = target;

    View view;
    target.depictsTitle = Utils.findRequiredViewAsType(source, R.id.depicts_title, "field 'depictsTitle'", TextView.class);
    target.depictsSubTitle = Utils.findRequiredViewAsType(source, R.id.depicts_subtitle, "field 'depictsSubTitle'", TextView.class);
    target.depictsSearchContainer = Utils.findRequiredViewAsType(source, R.id.depicts_search_container, "field 'depictsSearchContainer'", TextInputLayout.class);
    target.depictsSearch = Utils.findRequiredViewAsType(source, R.id.depicts_search, "field 'depictsSearch'", PasteSensitiveTextInputEditText.class);
    target.depictsSearchInProgress = Utils.findRequiredViewAsType(source, R.id.depictsSearchInProgress, "field 'depictsSearchInProgress'", ProgressBar.class);
    target.depictsRecyclerView = Utils.findRequiredViewAsType(source, R.id.depicts_recycler_view, "field 'depictsRecyclerView'", RecyclerView.class);
    target.tooltip = Utils.findRequiredViewAsType(source, R.id.tooltip, "field 'tooltip'", ImageView.class);
    view = Utils.findRequiredView(source, R.id.depicts_next, "field 'btnNext' and method 'onNextButtonClicked'");
    target.btnNext = Utils.castView(view, R.id.depicts_next, "field 'btnNext'", Button.class);
    view7f0900e2 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onNextButtonClicked();
      }
    });
    view = Utils.findRequiredView(source, R.id.depicts_previous, "field 'btnPrevious' and method 'onPreviousButtonClicked'");
    target.btnPrevious = Utils.castView(view, R.id.depicts_previous, "field 'btnPrevious'", Button.class);
    view7f0900e3 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onPreviousButtonClicked();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    DepictsFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.depictsTitle = null;
    target.depictsSubTitle = null;
    target.depictsSearchContainer = null;
    target.depictsSearch = null;
    target.depictsSearchInProgress = null;
    target.depictsRecyclerView = null;
    target.tooltip = null;
    target.btnNext = null;
    target.btnPrevious = null;

    view7f0900e2.setOnClickListener(null);
    view7f0900e2 = null;
    view7f0900e3.setOnClickListener(null);
    view7f0900e3 = null;
  }
}
