package com.github.pockethub.android.sync;

import dagger.MembersInjector;
import dagger.internal.InjectedFieldSignature;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class SyncAdapterService_MembersInjector implements MembersInjector<SyncAdapterService> {
  private final Provider<SyncAdapter> syncAdapterProvider;

  public SyncAdapterService_MembersInjector(Provider<SyncAdapter> syncAdapterProvider) {
    this.syncAdapterProvider = syncAdapterProvider;
  }

  public static MembersInjector<SyncAdapterService> create(
      Provider<SyncAdapter> syncAdapterProvider) {
    return new SyncAdapterService_MembersInjector(syncAdapterProvider);}

  @Override
  public void injectMembers(SyncAdapterService instance) {
    injectSyncAdapterProvider(instance, syncAdapterProvider);
  }

  @InjectedFieldSignature("com.github.pockethub.android.sync.SyncAdapterService.syncAdapterProvider")
  public static void injectSyncAdapterProvider(SyncAdapterService instance,
      Provider<SyncAdapter> syncAdapterProvider) {
    instance.syncAdapterProvider = syncAdapterProvider;
  }
}
