// Generated code from Butter Knife. Do not modify!
package fr.free.nrw.commons.upload;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.constraintlayout.widget.ConstraintLayout;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.google.android.material.textfield.TextInputLayout;
import fr.free.nrw.commons.R;
import fr.free.nrw.commons.ui.PasteSensitiveTextInputEditText;
import java.lang.IllegalStateException;
import java.lang.Override;

public class UploadMediaDetailAdapter$ViewHolder_ViewBinding implements Unbinder {
  private UploadMediaDetailAdapter.ViewHolder target;

  @UiThread
  public UploadMediaDetailAdapter$ViewHolder_ViewBinding(UploadMediaDetailAdapter.ViewHolder target,
      View source) {
    this.target = target;

    target.descriptionLanguages = Utils.findOptionalViewAsType(source, R.id.description_languages, "field 'descriptionLanguages'", TextView.class);
    target.descItemEditText = Utils.findRequiredViewAsType(source, R.id.description_item_edit_text, "field 'descItemEditText'", PasteSensitiveTextInputEditText.class);
    target.descInputLayout = Utils.findRequiredViewAsType(source, R.id.description_item_edit_text_input_layout, "field 'descInputLayout'", TextInputLayout.class);
    target.captionItemEditText = Utils.findRequiredViewAsType(source, R.id.caption_item_edit_text, "field 'captionItemEditText'", PasteSensitiveTextInputEditText.class);
    target.captionInputLayout = Utils.findRequiredViewAsType(source, R.id.caption_item_edit_text_input_layout, "field 'captionInputLayout'", TextInputLayout.class);
    target.removeButton = Utils.findRequiredViewAsType(source, R.id.btn_remove, "field 'removeButton'", ImageView.class);
    target.addButton = Utils.findRequiredViewAsType(source, R.id.btn_add, "field 'addButton'", ImageView.class);
    target.clParent = Utils.findRequiredViewAsType(source, R.id.cl_parent, "field 'clParent'", ConstraintLayout.class);
    target.betterCaptionLinearLayout = Utils.findRequiredViewAsType(source, R.id.ll_write_better_caption, "field 'betterCaptionLinearLayout'", LinearLayout.class);
    target.betterDescriptionLinearLayout = Utils.findRequiredViewAsType(source, R.id.ll_write_better_description, "field 'betterDescriptionLinearLayout'", LinearLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    UploadMediaDetailAdapter.ViewHolder target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.descriptionLanguages = null;
    target.descItemEditText = null;
    target.descInputLayout = null;
    target.captionItemEditText = null;
    target.captionInputLayout = null;
    target.removeButton = null;
    target.addButton = null;
    target.clParent = null;
    target.betterCaptionLinearLayout = null;
    target.betterDescriptionLinearLayout = null;
  }
}
