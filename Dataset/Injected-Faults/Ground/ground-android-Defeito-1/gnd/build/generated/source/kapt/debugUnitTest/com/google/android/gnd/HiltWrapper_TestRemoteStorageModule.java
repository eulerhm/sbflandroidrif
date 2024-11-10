package com.google.android.gnd;

import com.google.android.gnd.persistence.remote.RemoteStorageModule;
import dagger.Module;
import dagger.hilt.codegen.OriginatingElement;
import dagger.hilt.components.SingletonComponent;
import dagger.hilt.testing.TestInstallIn;
import javax.annotation.Generated;

@OriginatingElement(
    topLevelClass = TestRemoteStorageModule.class
)
@TestInstallIn(
    components = SingletonComponent.class,
    replaces = RemoteStorageModule.class
)
@Module(
    includes = TestRemoteStorageModule.class
)
@Generated("dagger.hilt.processor.internal.aggregateddeps.PkgPrivateModuleGenerator")
public final class HiltWrapper_TestRemoteStorageModule {
}
