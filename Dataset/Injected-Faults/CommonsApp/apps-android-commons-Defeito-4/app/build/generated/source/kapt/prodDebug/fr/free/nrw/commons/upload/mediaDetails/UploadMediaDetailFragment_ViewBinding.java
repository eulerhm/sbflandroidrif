// Generated code from Butter Knife. Do not modify!
package fr.free.nrw.commons.upload.mediaDetails;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.github.chrisbanes.photoview.PhotoView;
import fr.free.nrw.commons.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class UploadMediaDetailFragment_ViewBinding implements Unbinder {
  private UploadMediaDetailFragment target;

  private View view7f0901c0;

  private View view7f090077;

  private View view7f090079;

  private View view7f0901be;

  private View view7f090074;

  private View view7f0901bc;

  @UiThread
  public UploadMediaDetailFragment_ViewBinding(final UploadMediaDetailFragment target,
      View source) {
    this.target = target;

    View view;
    target.tvTitle = Utils.findRequiredViewAsType(source, R.id.tv_title, "field 'tvTitle'", TextView.class);
    target.locationImageView = Utils.findRequiredViewAsType(source, R.id.location_image_view, "field 'locationImageView'", ImageView.class);
    target.locationTextView = Utils.findRequiredViewAsType(source, R.id.location_text_view, "field 'locationTextView'", TextView.class);
    view = Utils.findRequiredView(source, R.id.ll_location_status, "field 'llLocationStatus' and method 'onIbMapClicked'");
    target.llLocationStatus = Utils.castView(view, R.id.ll_location_status, "field 'llLocationStatus'", LinearLayout.class);
    view7f0901c0 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onIbMapClicked();
      }
    });
    target.ibExpandCollapse = Utils.findRequiredViewAsType(source, R.id.ib_expand_collapse, "field 'ibExpandCollapse'", AppCompatImageButton.class);
    target.llContainerMediaDetail = Utils.findRequiredViewAsType(source, R.id.ll_container_media_detail, "field 'llContainerMediaDetail'", LinearLayout.class);
    target.rvDescriptions = Utils.findRequiredViewAsType(source, R.id.rv_descriptions, "field 'rvDescriptions'", RecyclerView.class);
    target.photoViewBackgroundImage = Utils.findRequiredViewAsType(source, R.id.backgroundImage, "field 'photoViewBackgroundImage'", PhotoView.class);
    view = Utils.findRequiredView(source, R.id.btn_next, "field 'btnNext' and method 'onNextButtonClicked'");
    target.btnNext = Utils.castView(view, R.id.btn_next, "field 'btnNext'", AppCompatButton.class);
    view7f090077 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onNextButtonClicked();
      }
    });
    view = Utils.findRequiredView(source, R.id.btn_previous, "field 'btnPrevious' and method 'onPreviousButtonClicked'");
    target.btnPrevious = Utils.castView(view, R.id.btn_previous, "field 'btnPrevious'", AppCompatButton.class);
    view7f090079 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onPreviousButtonClicked();
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_edit_image, "field 'llEditImage' and method 'onEditButtonClicked'");
    target.llEditImage = Utils.castView(view, R.id.ll_edit_image, "field 'llEditImage'", LinearLayout.class);
    view7f0901be = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onEditButtonClicked();
      }
    });
    target.tooltip = Utils.findRequiredViewAsType(source, R.id.tooltip, "field 'tooltip'", ImageView.class);
    view = Utils.findRequiredView(source, R.id.btn_copy_subsequent_media, "field 'btnCopyToSubsequentMedia' and method 'onButtonCopyTitleDescToSubsequentMedia'");
    target.btnCopyToSubsequentMedia = Utils.castView(view, R.id.btn_copy_subsequent_media, "field 'btnCopyToSubsequentMedia'", AppCompatButton.class);
    view7f090074 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onButtonCopyTitleDescToSubsequentMedia();
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_container_title, "method 'onLlContainerTitleClicked'");
    view7f0901bc = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onLlContainerTitleClicked();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    UploadMediaDetailFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tvTitle = null;
    target.locationImageView = null;
    target.locationTextView = null;
    target.llLocationStatus = null;
    target.ibExpandCollapse = null;
    target.llContainerMediaDetail = null;
    target.rvDescriptions = null;
    target.photoViewBackgroundImage = null;
    target.btnNext = null;
    target.btnPrevious = null;
    target.llEditImage = null;
    target.tooltip = null;
    target.btnCopyToSubsequentMedia = null;

    view7f0901c0.setOnClickListener(null);
    view7f0901c0 = null;
    view7f090077.setOnClickListener(null);
    view7f090077 = null;
    view7f090079.setOnClickListener(null);
    view7f090079 = null;
    view7f0901be.setOnClickListener(null);
    view7f0901be = null;
    view7f090074.setOnClickListener(null);
    view7f090074 = null;
    view7f0901bc.setOnClickListener(null);
    view7f0901bc = null;
  }
}
