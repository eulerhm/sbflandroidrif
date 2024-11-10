// Generated code from Butter Knife. Do not modify!
package fr.free.nrw.commons.upload.categories;

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

public class UploadCategoriesFragment_ViewBinding implements Unbinder {
  private UploadCategoriesFragment target;

  private View view7f090077;

  private View view7f090079;

  @UiThread
  public UploadCategoriesFragment_ViewBinding(final UploadCategoriesFragment target, View source) {
    this.target = target;

    View view;
    target.tvTitle = Utils.findRequiredViewAsType(source, R.id.tv_title, "field 'tvTitle'", TextView.class);
    target.tvSubTitle = Utils.findRequiredViewAsType(source, R.id.tv_subtitle, "field 'tvSubTitle'", TextView.class);
    target.tilContainerEtSearch = Utils.findRequiredViewAsType(source, R.id.til_container_search, "field 'tilContainerEtSearch'", TextInputLayout.class);
    target.etSearch = Utils.findRequiredViewAsType(source, R.id.et_search, "field 'etSearch'", PasteSensitiveTextInputEditText.class);
    target.pbCategories = Utils.findRequiredViewAsType(source, R.id.pb_categories, "field 'pbCategories'", ProgressBar.class);
    target.rvCategories = Utils.findRequiredViewAsType(source, R.id.rv_categories, "field 'rvCategories'", RecyclerView.class);
    target.tooltip = Utils.findRequiredViewAsType(source, R.id.tooltip, "field 'tooltip'", ImageView.class);
    view = Utils.findRequiredView(source, R.id.btn_next, "field 'btnNext' and method 'onNextButtonClicked'");
    target.btnNext = Utils.castView(view, R.id.btn_next, "field 'btnNext'", Button.class);
    view7f090077 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onNextButtonClicked();
      }
    });
    view = Utils.findRequiredView(source, R.id.btn_previous, "field 'btnPrevious' and method 'onPreviousButtonClicked'");
    target.btnPrevious = Utils.castView(view, R.id.btn_previous, "field 'btnPrevious'", Button.class);
    view7f090079 = view;
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
    UploadCategoriesFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tvTitle = null;
    target.tvSubTitle = null;
    target.tilContainerEtSearch = null;
    target.etSearch = null;
    target.pbCategories = null;
    target.rvCategories = null;
    target.tooltip = null;
    target.btnNext = null;
    target.btnPrevious = null;

    view7f090077.setOnClickListener(null);
    view7f090077 = null;
    view7f090079.setOnClickListener(null);
    view7f090079 = null;
  }
}
