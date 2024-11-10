package com.google.android.gnd;

import com.google.android.gnd.persistence.sync.WorkManagerModule;
import dagger.Module;
import dagger.hilt.codegen.OriginatingElement;
import dagger.hilt.components.SingletonComponent;
import dagger.hilt.testing.TestInstallIn;
import javax.annotation.Generated;

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
@Generated("dagger.hilt.processor.internal.aggregateddeps.PkgPrivateModuleGenerator")
public final class HiltWrapper_TestWorkManagerModule {
}
