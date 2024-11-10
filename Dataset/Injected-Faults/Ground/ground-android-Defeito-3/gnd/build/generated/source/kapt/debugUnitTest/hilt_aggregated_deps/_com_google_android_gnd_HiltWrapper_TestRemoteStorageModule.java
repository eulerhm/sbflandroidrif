package hilt_aggregated_deps;

import dagger.hilt.processor.internal.aggregateddeps.AggregatedDeps;
import javax.annotation.Generated;

/**
 * This class should only be referenced by generated code!This class aggregates information across multiple compilations.
 */
@AggregatedDeps(
    components = "dagger.hilt.components.SingletonComponent",
    replaces = "com.google.android.gnd.persistence.remote.RemoteStorageModule",
    modules = "com.google.android.gnd.HiltWrapper_TestRemoteStorageModule"
)
@Generated("dagger.hilt.processor.internal.aggregateddeps.AggregatedDepsGenerator")
public class _com_google_android_gnd_HiltWrapper_TestRemoteStorageModule {
}
