package org.wordpress.android.ui.jetpack.backup.download.builders;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.ui.jetpack.common.CheckboxSpannableLabel;

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
public final class BackupDownloadStateListItemBuilder_Factory implements Factory<BackupDownloadStateListItemBuilder> {
  private final Provider<CheckboxSpannableLabel> checkboxSpannableLabelProvider;

  public BackupDownloadStateListItemBuilder_Factory(
      Provider<CheckboxSpannableLabel> checkboxSpannableLabelProvider) {
    this.checkboxSpannableLabelProvider = checkboxSpannableLabelProvider;
  }

  @Override
  public BackupDownloadStateListItemBuilder get() {
    return newInstance(checkboxSpannableLabelProvider.get());
  }

  public static BackupDownloadStateListItemBuilder_Factory create(
      Provider<CheckboxSpannableLabel> checkboxSpannableLabelProvider) {
    return new BackupDownloadStateListItemBuilder_Factory(checkboxSpannableLabelProvider);
  }

  public static BackupDownloadStateListItemBuilder newInstance(
      CheckboxSpannableLabel checkboxSpannableLabel) {
    return new BackupDownloadStateListItemBuilder(checkboxSpannableLabel);
  }
}
