package org.wordpress.android.modules;

import java.lang.System;

@dagger.hilt.InstallIn(value = {dagger.hilt.components.SingletonComponent.class})
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\bg\u0018\u00002\u00020\u0001J\u000e\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\'\u00a8\u0006\u0005"}, d2 = {"Lorg/wordpress/android/modules/ExperimentModule;", "", "experiments", "", "Lorg/wordpress/android/util/experiments/Experiment;", "org.wordpress.android_wordpressJalapenoDebug"})
@dagger.Module()
public abstract interface ExperimentModule {
    
    @org.jetbrains.annotations.NotNull()
    @dagger.multibindings.Multibinds()
    public abstract java.util.Set<org.wordpress.android.util.experiments.Experiment> experiments();
}