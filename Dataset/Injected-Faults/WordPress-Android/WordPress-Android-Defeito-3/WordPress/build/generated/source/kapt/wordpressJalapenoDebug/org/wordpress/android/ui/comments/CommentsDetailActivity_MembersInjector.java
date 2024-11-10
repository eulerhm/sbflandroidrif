package org.wordpress.android.ui.comments;

import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.QualifierMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.ui.comments.unified.CommentsStoreAdapter;

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
public final class CommentsDetailActivity_MembersInjector implements MembersInjector<CommentsDetailActivity> {
  private final Provider<CommentsStoreAdapter> mCommentsStoreAdapterProvider;

  public CommentsDetailActivity_MembersInjector(
      Provider<CommentsStoreAdapter> mCommentsStoreAdapterProvider) {
    this.mCommentsStoreAdapterProvider = mCommentsStoreAdapterProvider;
  }

  public static MembersInjector<CommentsDetailActivity> create(
      Provider<CommentsStoreAdapter> mCommentsStoreAdapterProvider) {
    return new CommentsDetailActivity_MembersInjector(mCommentsStoreAdapterProvider);
  }

  @Override
  public void injectMembers(CommentsDetailActivity instance) {
    injectMCommentsStoreAdapter(instance, mCommentsStoreAdapterProvider.get());
  }

  @InjectedFieldSignature("org.wordpress.android.ui.comments.CommentsDetailActivity.mCommentsStoreAdapter")
  public static void injectMCommentsStoreAdapter(CommentsDetailActivity instance,
      CommentsStoreAdapter mCommentsStoreAdapter) {
    instance.mCommentsStoreAdapter = mCommentsStoreAdapter;
  }
}
