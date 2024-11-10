package org.wordpress.android.util.experiments;

import dagger.Lazy;
import dagger.internal.DaggerGenerated;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import java.util.Set;
import javax.annotation.Generated;
import javax.inject.Provider;
import kotlinx.coroutines.CoroutineScope;
import org.wordpress.android.fluxc.store.AccountStore;
import org.wordpress.android.fluxc.store.ExperimentStore;
import org.wordpress.android.fluxc.utils.AppLogWrapper;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata("javax.inject.Named")
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class ExPlat_Factory implements Factory<ExPlat> {
  private final Provider<Set<Experiment>> experimentsProvider;

  private final Provider<ExperimentStore> experimentStoreProvider;

  private final Provider<AppLogWrapper> appLogProvider;

  private final Provider<AccountStore> accountStoreProvider;

  private final Provider<CoroutineScope> coroutineScopeProvider;

  public ExPlat_Factory(Provider<Set<Experiment>> experimentsProvider,
      Provider<ExperimentStore> experimentStoreProvider, Provider<AppLogWrapper> appLogProvider,
      Provider<AccountStore> accountStoreProvider,
      Provider<CoroutineScope> coroutineScopeProvider) {
    this.experimentsProvider = experimentsProvider;
    this.experimentStoreProvider = experimentStoreProvider;
    this.appLogProvider = appLogProvider;
    this.accountStoreProvider = accountStoreProvider;
    this.coroutineScopeProvider = coroutineScopeProvider;
  }

  @Override
  public ExPlat get() {
    return newInstance(DoubleCheck.lazy(experimentsProvider), experimentStoreProvider.get(), appLogProvider.get(), accountStoreProvider.get(), coroutineScopeProvider.get());
  }

  public static ExPlat_Factory create(Provider<Set<Experiment>> experimentsProvider,
      Provider<ExperimentStore> experimentStoreProvider, Provider<AppLogWrapper> appLogProvider,
      Provider<AccountStore> accountStoreProvider,
      Provider<CoroutineScope> coroutineScopeProvider) {
    return new ExPlat_Factory(experimentsProvider, experimentStoreProvider, appLogProvider, accountStoreProvider, coroutineScopeProvider);
  }

  public static ExPlat newInstance(Lazy<Set<Experiment>> experiments,
      ExperimentStore experimentStore, AppLogWrapper appLog, AccountStore accountStore,
      CoroutineScope coroutineScope) {
    return new ExPlat(experiments, experimentStore, appLog, accountStore, coroutineScope);
  }
}
