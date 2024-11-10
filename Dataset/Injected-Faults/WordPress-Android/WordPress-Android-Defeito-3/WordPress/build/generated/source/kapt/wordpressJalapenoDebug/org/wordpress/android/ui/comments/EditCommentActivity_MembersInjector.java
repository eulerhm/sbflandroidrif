package org.wordpress.android.ui.comments;

import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.QualifierMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.fluxc.store.SiteStore;
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
public final class EditCommentActivity_MembersInjector implements MembersInjector<EditCommentActivity> {
  private final Provider<CommentsStoreAdapter> mCommentsStoreAdapterProvider;

  private final Provider<SiteStore> mSiteStoreProvider;

  public EditCommentActivity_MembersInjector(
      Provider<CommentsStoreAdapter> mCommentsStoreAdapterProvider,
      Provider<SiteStore> mSiteStoreProvider) {
    this.mCommentsStoreAdapterProvider = mCommentsStoreAdapterProvider;
    this.mSiteStoreProvider = mSiteStoreProvider;
  }

  public static MembersInjector<EditCommentActivity> create(
      Provider<CommentsStoreAdapter> mCommentsStoreAdapterProvider,
      Provider<SiteStore> mSiteStoreProvider) {
    return new EditCommentActivity_MembersInjector(mCommentsStoreAdapterProvider, mSiteStoreProvider);
  }

  @Override
  public void injectMembers(EditCommentActivity instance) {
    injectMCommentsStoreAdapter(instance, mCommentsStoreAdapterProvider.get());
    injectMSiteStore(instance, mSiteStoreProvider.get());
  }

  @InjectedFieldSignature("org.wordpress.android.ui.comments.EditCommentActivity.mCommentsStoreAdapter")
  public static void injectMCommentsStoreAdapter(EditCommentActivity instance,
      CommentsStoreAdapter mCommentsStoreAdapter) {
    instance.mCommentsStoreAdapter = mCommentsStoreAdapter;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.comments.EditCommentActivity.mSiteStore")
  public static void injectMSiteStore(EditCommentActivity instance, SiteStore mSiteStore) {
    instance.mSiteStore = mSiteStore;
  }
}
