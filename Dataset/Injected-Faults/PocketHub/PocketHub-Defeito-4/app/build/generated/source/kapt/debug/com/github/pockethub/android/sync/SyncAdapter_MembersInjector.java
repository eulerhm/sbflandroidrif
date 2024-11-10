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
public final class SyncAdapter_MembersInjector implements MembersInjector<SyncAdapter> {
  private final Provider<SyncCampaignFactory> campaignFactoryProvider;

  public SyncAdapter_MembersInjector(Provider<SyncCampaignFactory> campaignFactoryProvider) {
    this.campaignFactoryProvider = campaignFactoryProvider;
  }

  public static MembersInjector<SyncAdapter> create(
      Provider<SyncCampaignFactory> campaignFactoryProvider) {
    return new SyncAdapter_MembersInjector(campaignFactoryProvider);}

  @Override
  public void injectMembers(SyncAdapter instance) {
    injectCampaignFactory(instance, campaignFactoryProvider.get());
  }

  @InjectedFieldSignature("com.github.pockethub.android.sync.SyncAdapter.campaignFactory")
  public static void injectCampaignFactory(SyncAdapter instance,
      SyncCampaignFactory campaignFactory) {
    instance.campaignFactory = campaignFactory;
  }
}
