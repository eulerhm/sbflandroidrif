package com.google.android.gnd;

import com.google.android.gnd.rx.SchedulersModule;
import dagger.Module;
import dagger.hilt.codegen.OriginatingElement;
import dagger.hilt.components.SingletonComponent;
import dagger.hilt.testing.TestInstallIn;

@OriginatingElement(
    topLevelClass = TestSchedulersModule.class
)
@TestInstallIn(
    components = SingletonComponent.class,
    replaces = SchedulersModule.class
)
@Module(
    includes = TestSchedulersModule.class
)
public final class HiltWrapper_TestSchedulersModule {
}
