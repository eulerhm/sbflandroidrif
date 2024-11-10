package com.google.android.gnd;

import com.google.android.gnd.persistence.local.LocalDatabaseModule;
import dagger.Module;
import dagger.hilt.codegen.OriginatingElement;
import dagger.hilt.components.SingletonComponent;
import dagger.hilt.testing.TestInstallIn;
import javax.annotation.Generated;

@OriginatingElement(
    topLevelClass = TestLocalDatabaseModule.class
)
@TestInstallIn(
    components = SingletonComponent.class,
    replaces = LocalDatabaseModule.class
)
@Module(
    includes = TestLocalDatabaseModule.class
)
@Generated("dagger.hilt.processor.internal.aggregateddeps.PkgPrivateModuleGenerator")
public final class HiltWrapper_TestLocalDatabaseModule {
}
