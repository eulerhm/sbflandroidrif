package com.google.android.gnd;

import com.google.android.gnd.persistence.local.LocalDatabaseModule;
import dagger.Module;
import dagger.hilt.codegen.OriginatingElement;
import dagger.hilt.components.SingletonComponent;
import dagger.hilt.testing.TestInstallIn;

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
public final class HiltWrapper_TestLocalDatabaseModule {
}
