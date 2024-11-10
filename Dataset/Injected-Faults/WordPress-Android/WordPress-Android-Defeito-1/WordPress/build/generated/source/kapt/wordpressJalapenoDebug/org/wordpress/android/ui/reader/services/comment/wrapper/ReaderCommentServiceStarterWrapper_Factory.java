package org.wordpress.android.ui.reader.services.comment.wrapper;

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
public final class ReaderCommentServiceStarterWrapper_Factory implements Factory<ReaderCommentServiceStarterWrapper> {
  @Override
  public ReaderCommentServiceStarterWrapper get() {
    return newInstance();
  }

  public static ReaderCommentServiceStarterWrapper_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static ReaderCommentServiceStarterWrapper newInstance() {
    return new ReaderCommentServiceStarterWrapper();
  }

  private static final class InstanceHolder {
    private static final ReaderCommentServiceStarterWrapper_Factory INSTANCE = new ReaderCommentServiceStarterWrapper_Factory();
  }
}
