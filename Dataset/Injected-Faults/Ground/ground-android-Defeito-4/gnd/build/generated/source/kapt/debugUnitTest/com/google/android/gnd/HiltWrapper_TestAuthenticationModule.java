package com.google.android.gnd;

import com.google.android.gnd.system.auth.AuthenticationModule;
import dagger.Module;
import dagger.hilt.codegen.OriginatingElement;
import dagger.hilt.components.SingletonComponent;
import dagger.hilt.testing.TestInstallIn;
import javax.annotation.Generated;

@OriginatingElement(
    topLevelClass = TestAuthenticationModule.class
)
@TestInstallIn(
    components = SingletonComponent.class,
    replaces = AuthenticationModule.class
)
@Module(
    includes = TestAuthenticationModule.class
)
@Generated("dagger.hilt.processor.internal.aggregateddeps.PkgPrivateModuleGenerator")
public final class HiltWrapper_TestAuthenticationModule {
}
