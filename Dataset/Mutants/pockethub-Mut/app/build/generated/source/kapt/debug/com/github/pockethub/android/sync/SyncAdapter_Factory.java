package com.github.pockethub.android.sync;

import android.content.Context;
import dagger.internal.Factory;
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
public final class SyncAdapter_Factory implements Factory<SyncAdapter> {
  private final Provider<Context> contextProvider;

  private final Provider<SyncCampaignFactory> campaignFactoryProvider;

  public SyncAdapter_Factory(Provider<Context> contextProvider,
      Provider<SyncCampaignFactory> campaignFactoryProvider) {
    this.contextProvider = contextProvider;
    this.campaignFactoryProvider = campaignFactoryProvider;
  }

  @Override
  public SyncAdapter get() {
    SyncAdapter instance = newInstance(contextProvider.get());
    SyncAdapter_MembersInjector.injectCampaignFactory(instance, campaignFactoryProvider.get());
    return instance;
  }

  public static SyncAdapter_Factory create(Provider<Context> contextProvider,
      Provider<SyncCampaignFactory> campaignFactoryProvider) {
    return new SyncAdapter_Factory(contextProvider, campaignFactoryProvider);
  }

  public static SyncAdapter newInstance(Context context) {
    return new SyncAdapter(context);
  }
}
