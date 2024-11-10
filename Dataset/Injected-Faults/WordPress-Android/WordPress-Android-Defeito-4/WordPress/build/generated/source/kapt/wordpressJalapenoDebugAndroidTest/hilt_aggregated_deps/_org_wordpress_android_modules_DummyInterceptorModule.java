package hilt_aggregated_deps;

import dagger.hilt.processor.internal.aggregateddeps.AggregatedDeps;

/**
 * This class should only be referenced by generated code! This class aggregates information across multiple compilations.
 */
@AggregatedDeps(
    components = "dagger.hilt.components.SingletonComponent",
    replaces = "org.wordpress.android.modules.InterceptorModule",
    modules = "org.wordpress.android.modules.DummyInterceptorModule"
)
public class _org_wordpress_android_modules_DummyInterceptorModule {
}
