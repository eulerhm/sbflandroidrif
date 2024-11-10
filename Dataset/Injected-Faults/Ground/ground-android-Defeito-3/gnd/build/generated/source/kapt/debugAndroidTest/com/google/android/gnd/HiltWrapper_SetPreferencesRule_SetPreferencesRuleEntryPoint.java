package com.google.android.gnd;

import dagger.hilt.EntryPoint;
import dagger.hilt.InstallIn;
import dagger.hilt.codegen.OriginatingElement;
import dagger.hilt.components.SingletonComponent;

@OriginatingElement(
    topLevelClass = SetPreferencesRule.class
)
@EntryPoint
@InstallIn(SingletonComponent.class)
public interface HiltWrapper_SetPreferencesRule_SetPreferencesRuleEntryPoint extends SetPreferencesRule.SetPreferencesRuleEntryPoint {
}
