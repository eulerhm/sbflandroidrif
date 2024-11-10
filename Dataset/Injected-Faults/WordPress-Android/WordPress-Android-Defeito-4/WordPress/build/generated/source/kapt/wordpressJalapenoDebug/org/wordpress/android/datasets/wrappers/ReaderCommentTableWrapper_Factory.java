package org.wordpress.android.datasets.wrappers;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;

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
public final class ReaderCommentTableWrapper_Factory implements Factory<ReaderCommentTableWrapper> {
  @Override
  public ReaderCommentTableWrapper get() {
    return newInstance();
  }

  public static ReaderCommentTableWrapper_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static ReaderCommentTableWrapper newInstance() {
    return new ReaderCommentTableWrapper();
  }

  private static final class InstanceHolder {
    private static final ReaderCommentTableWrapper_Factory INSTANCE = new ReaderCommentTableWrapper_Factory();
  }
}
