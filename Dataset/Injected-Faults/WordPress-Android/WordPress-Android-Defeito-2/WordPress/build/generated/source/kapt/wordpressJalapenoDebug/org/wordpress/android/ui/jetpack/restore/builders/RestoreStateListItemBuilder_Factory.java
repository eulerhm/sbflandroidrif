package org.wordpress.android.ui.jetpack.restore.builders;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.ui.jetpack.common.CheckboxSpannableLabel;
import org.wordpress.android.ui.utils.HtmlMessageUtils;

@ScopeMetadata("dagger.Reusable")
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class RestoreStateListItemBuilder_Factory implements Factory<RestoreStateListItemBuilder> {
  private final Provider<CheckboxSpannableLabel> checkboxSpannableLabelProvider;

  private final Provider<HtmlMessageUtils> htmlMessageUtilsProvider;

  public RestoreStateListItemBuilder_Factory(
      Provider<CheckboxSpannableLabel> checkboxSpannableLabelProvider,
      Provider<HtmlMessageUtils> htmlMessageUtilsProvider) {
    this.checkboxSpannableLabelProvider = checkboxSpannableLabelProvider;
    this.htmlMessageUtilsProvider = htmlMessageUtilsProvider;
  }

  @Override
  public RestoreStateListItemBuilder get() {
    return newInstance(checkboxSpannableLabelProvider.get(), htmlMessageUtilsProvider.get());
  }

  public static RestoreStateListItemBuilder_Factory create(
      Provider<CheckboxSpannableLabel> checkboxSpannableLabelProvider,
      Provider<HtmlMessageUtils> htmlMessageUtilsProvider) {
    return new RestoreStateListItemBuilder_Factory(checkboxSpannableLabelProvider, htmlMessageUtilsProvider);
  }

  public static RestoreStateListItemBuilder newInstance(
      CheckboxSpannableLabel checkboxSpannableLabel, HtmlMessageUtils htmlMessageUtils) {
    return new RestoreStateListItemBuilder(checkboxSpannableLabel, htmlMessageUtils);
  }
}
