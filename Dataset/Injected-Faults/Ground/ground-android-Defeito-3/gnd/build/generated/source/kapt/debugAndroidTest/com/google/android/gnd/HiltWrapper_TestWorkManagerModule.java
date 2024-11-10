package com.google.android.gnd;

import com.google.android.gnd.persistence.sync.WorkManagerModule;
import dagger.Module;
import dagger.hilt.codegen.OriginatingElement;
import dagger.hilt.components.SingletonComponent;
import dagger.hilt.testing.TestInstallIn;

@OriginatingElement(
    topLevelClass = TestWorkManagerModule.class
)
@TestInstallIn(
    components = SingletonComponent.class,
    replaces = WorkManagerModule.class
)
@Module(
    includes = TestWorkManagerModule.class
)
public final class HiltWrapper_TestWorkManagerModule {
}
