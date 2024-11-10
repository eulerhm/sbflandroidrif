package com.google.android.gnd;

import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.codegen.OriginatingElement;
import dagger.hilt.components.SingletonComponent;

@OriginatingElement(
    topLevelClass = GndApplicationModule.class
)
@InstallIn(SingletonComponent.class)
@Module(
    includes = GndApplicationModule.class
)
public final class HiltWrapper_GndApplicationModule {
}
