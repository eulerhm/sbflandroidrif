// Generated code from Butter Knife. Do not modify!
package fr.free.nrw.commons.media;

import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.facebook.drawee.view.SimpleDraweeView;
import fr.free.nrw.commons.R;
import fr.free.nrw.commons.ui.widget.HtmlTextView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MediaDetailFragment_ViewBinding implements Unbinder {
  private MediaDetailFragment target;

  private View view7f0901e7;

  private View view7f0900dd;

  private View view7f0901dd;

  private View view7f0901e8;

  private View view7f0901e1;

  private View view7f0902a6;

  private View view7f09009b;

  private View view7f090229;

  private View view7f0900cf;

  private View view7f0902b8;

  private View view7f0900eb;

  private View view7f0902b0;

  private View view7f0900d2;

  @UiThread
  public MediaDetailFragment_ViewBinding(final MediaDetailFragment target, View source) {
    this.target = target;

    View view;
    target.descriptionWebView = Utils.findRequiredViewAsType(source, R.id.description_webview, "field 'descriptionWebView'", WebView.class);
    target.frameLayout = Utils.findRequiredViewAsType(source, R.id.mediaDetailFrameLayout, "field 'frameLayout'", FrameLayout.class);
    target.image = Utils.findRequiredViewAsType(source, R.id.mediaDetailImageView, "field 'image'", SimpleDraweeView.class);
    view = Utils.findRequiredView(source, R.id.mediaDetailImageViewSpacer, "field 'imageSpacer' and method 'launchZoomActivity'");
    target.imageSpacer = Utils.castView(view, R.id.mediaDetailImageViewSpacer, "field 'imageSpacer'", LinearLayout.class);
    view7f0901e7 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.launchZoomActivity(p0);
      }
    });
    target.title = Utils.findRequiredViewAsType(source, R.id.mediaDetailTitle, "field 'title'", TextView.class);
    target.captionLayout = Utils.findRequiredViewAsType(source, R.id.caption_layout, "field 'captionLayout'", LinearLayout.class);
    target.depictsLayout = Utils.findRequiredViewAsType(source, R.id.depicts_layout, "field 'depictsLayout'", LinearLayout.class);
    view = Utils.findRequiredView(source, R.id.depictionsEditButton, "field 'depictEditButton' and method 'onDepictionsEditButtonClicked'");
    target.depictEditButton = Utils.castView(view, R.id.depictionsEditButton, "field 'depictEditButton'", Button.class);
    view7f0900dd = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onDepictionsEditButtonClicked();
      }
    });
    target.mediaCaption = Utils.findRequiredViewAsType(source, R.id.media_detail_caption, "field 'mediaCaption'", TextView.class);
    target.desc = Utils.findRequiredViewAsType(source, R.id.mediaDetailDesc, "field 'desc'", HtmlTextView.class);
    view = Utils.findRequiredView(source, R.id.mediaDetailAuthor, "field 'author' and method 'onAuthorViewClicked'");
    target.author = Utils.castView(view, R.id.mediaDetailAuthor, "field 'author'", TextView.class);
    view7f0901dd = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onAuthorViewClicked();
      }
    });
    view = Utils.findRequiredView(source, R.id.mediaDetailLicense, "field 'license' and method 'onMediaDetailLicenceClicked'");
    target.license = Utils.castView(view, R.id.mediaDetailLicense, "field 'license'", TextView.class);
    view7f0901e8 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onMediaDetailLicenceClicked();
      }
    });
    view = Utils.findRequiredView(source, R.id.mediaDetailCoordinates, "field 'coordinates' and method 'onMediaDetailCoordinatesClicked'");
    target.coordinates = Utils.castView(view, R.id.mediaDetailCoordinates, "field 'coordinates'", TextView.class);
    view7f0901e1 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onMediaDetailCoordinatesClicked();
      }
    });
    target.uploadedDate = Utils.findRequiredViewAsType(source, R.id.mediaDetailuploadeddate, "field 'uploadedDate'", TextView.class);
    target.mediaDiscussion = Utils.findRequiredViewAsType(source, R.id.mediaDetailDisc, "field 'mediaDiscussion'", TextView.class);
    view = Utils.findRequiredView(source, R.id.seeMore, "field 'seeMore' and method 'onSeeMoreClicked'");
    target.seeMore = Utils.castView(view, R.id.seeMore, "field 'seeMore'", TextView.class);
    view7f0902a6 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onSeeMoreClicked();
      }
    });
    target.nominatedForDeletion = Utils.findRequiredViewAsType(source, R.id.nominatedDeletionBanner, "field 'nominatedForDeletion'", LinearLayout.class);
    target.categoryContainer = Utils.findRequiredViewAsType(source, R.id.mediaDetailCategoryContainer, "field 'categoryContainer'", LinearLayout.class);
    view = Utils.findRequiredView(source, R.id.categoryEditButton, "field 'categoryEditButton' and method 'onCategoryEditButtonClicked'");
    target.categoryEditButton = Utils.castView(view, R.id.categoryEditButton, "field 'categoryEditButton'", Button.class);
    view7f09009b = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onCategoryEditButtonClicked();
      }
    });
    target.depictionContainer = Utils.findRequiredViewAsType(source, R.id.media_detail_depiction_container, "field 'depictionContainer'", LinearLayout.class);
    target.authorLayout = Utils.findRequiredViewAsType(source, R.id.authorLinearLayout, "field 'authorLayout'", LinearLayout.class);
    view = Utils.findRequiredView(source, R.id.nominateDeletion, "field 'delete' and method 'onDeleteButtonClicked'");
    target.delete = Utils.castView(view, R.id.nominateDeletion, "field 'delete'", Button.class);
    view7f090229 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onDeleteButtonClicked();
      }
    });
    target.scrollView = Utils.findRequiredViewAsType(source, R.id.mediaDetailScrollView, "field 'scrollView'", ScrollView.class);
    target.toDoLayout = Utils.findRequiredViewAsType(source, R.id.toDoLayout, "field 'toDoLayout'", LinearLayout.class);
    target.toDoReason = Utils.findRequiredViewAsType(source, R.id.toDoReason, "field 'toDoReason'", TextView.class);
    view = Utils.findRequiredView(source, R.id.coordinate_edit, "field 'coordinateEditButton' and method 'onUpdateCoordinatesClicked'");
    target.coordinateEditButton = Utils.castView(view, R.id.coordinate_edit, "field 'coordinateEditButton'", Button.class);
    view7f0900cf = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onUpdateCoordinatesClicked();
      }
    });
    target.showCaptionAndDescriptionContainer = Utils.findRequiredViewAsType(source, R.id.dummy_caption_description_container, "field 'showCaptionAndDescriptionContainer'", LinearLayout.class);
    view = Utils.findRequiredView(source, R.id.show_caption_description_textview, "field 'showCaptionDescriptionTextView' and method 'showCaptionAndDescription'");
    target.showCaptionDescriptionTextView = Utils.castView(view, R.id.show_caption_description_textview, "field 'showCaptionDescriptionTextView'", TextView.class);
    view7f0902b8 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.showCaptionAndDescription();
      }
    });
    target.captionsListView = Utils.findRequiredViewAsType(source, R.id.caption_listview, "field 'captionsListView'", ListView.class);
    target.captionLabel = Utils.findRequiredViewAsType(source, R.id.caption_label, "field 'captionLabel'", TextView.class);
    target.descriptionLabel = Utils.findRequiredViewAsType(source, R.id.description_label, "field 'descriptionLabel'", TextView.class);
    target.progressBar = Utils.findRequiredViewAsType(source, R.id.pb_circular, "field 'progressBar'", ProgressBar.class);
    target.progressBarDeletion = Utils.findRequiredViewAsType(source, R.id.progressBarDeletion, "field 'progressBarDeletion'", ProgressBar.class);
    target.progressBarEditDescription = Utils.findRequiredViewAsType(source, R.id.progressBarEdit, "field 'progressBarEditDescription'", ProgressBar.class);
    target.progressBarEditCategory = Utils.findRequiredViewAsType(source, R.id.progressBarEditCategory, "field 'progressBarEditCategory'", ProgressBar.class);
    view = Utils.findRequiredView(source, R.id.description_edit, "field 'editDescription' and method 'onDescriptionEditClicked'");
    target.editDescription = Utils.castView(view, R.id.description_edit, "field 'editDescription'", Button.class);
    view7f0900eb = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onDescriptionEditClicked();
      }
    });
    view = Utils.findRequiredView(source, R.id.sendThanks, "field 'sendThanksButton' and method 'sendThanksToAuthor'");
    target.sendThanksButton = Utils.castView(view, R.id.sendThanks, "field 'sendThanksButton'", Button.class);
    view7f0902b0 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.sendThanksToAuthor();
      }
    });
    view = Utils.findRequiredView(source, R.id.copyWikicode, "method 'onCopyWikicodeClicked'");
    view7f0900d2 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onCopyWikicodeClicked();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    MediaDetailFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.descriptionWebView = null;
    target.frameLayout = null;
    target.image = null;
    target.imageSpacer = null;
    target.title = null;
    target.captionLayout = null;
    target.depictsLayout = null;
    target.depictEditButton = null;
    target.mediaCaption = null;
    target.desc = null;
    target.author = null;
    target.license = null;
    target.coordinates = null;
    target.uploadedDate = null;
    target.mediaDiscussion = null;
    target.seeMore = null;
    target.nominatedForDeletion = null;
    target.categoryContainer = null;
    target.categoryEditButton = null;
    target.depictionContainer = null;
    target.authorLayout = null;
    target.delete = null;
    target.scrollView = null;
    target.toDoLayout = null;
    target.toDoReason = null;
    target.coordinateEditButton = null;
    target.showCaptionAndDescriptionContainer = null;
    target.showCaptionDescriptionTextView = null;
    target.captionsListView = null;
    target.captionLabel = null;
    target.descriptionLabel = null;
    target.progressBar = null;
    target.progressBarDeletion = null;
    target.progressBarEditDescription = null;
    target.progressBarEditCategory = null;
    target.editDescription = null;
    target.sendThanksButton = null;

    view7f0901e7.setOnClickListener(null);
    view7f0901e7 = null;
    view7f0900dd.setOnClickListener(null);
    view7f0900dd = null;
    view7f0901dd.setOnClickListener(null);
    view7f0901dd = null;
    view7f0901e8.setOnClickListener(null);
    view7f0901e8 = null;
    view7f0901e1.setOnClickListener(null);
    view7f0901e1 = null;
    view7f0902a6.setOnClickListener(null);
    view7f0902a6 = null;
    view7f09009b.setOnClickListener(null);
    view7f09009b = null;
    view7f090229.setOnClickListener(null);
    view7f090229 = null;
    view7f0900cf.setOnClickListener(null);
    view7f0900cf = null;
    view7f0902b8.setOnClickListener(null);
    view7f0902b8 = null;
    view7f0900eb.setOnClickListener(null);
    view7f0900eb = null;
    view7f0902b0.setOnClickListener(null);
    view7f0902b0 = null;
    view7f0900d2.setOnClickListener(null);
    view7f0900d2 = null;
  }
}
