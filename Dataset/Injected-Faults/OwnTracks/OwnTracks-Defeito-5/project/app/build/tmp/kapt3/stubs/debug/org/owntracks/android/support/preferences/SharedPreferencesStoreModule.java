package org.owntracks.android.support.preferences;

import android.content.Context;
import android.content.SharedPreferences;
import androidx.preference.PreferenceManager;
import dagger.Binds;
import dagger.Module;
import org.owntracks.android.R;
import org.owntracks.android.injection.qualifier.AppContext;
import javax.inject.Singleton;
import org.owntracks.android.services.MessageProcessorEndpointHttp;
import org.owntracks.android.services.MessageProcessorEndpointMqtt;
import java.util.*;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\'\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\'\u00a8\u0006\u0007"}, d2 = {"Lorg/owntracks/android/support/preferences/SharedPreferencesStoreModule;", "", "()V", "bindSharedPreferencesStoreModule", "Lorg/owntracks/android/support/preferences/PreferencesStore;", "sharedPreferencesStore", "Lorg/owntracks/android/support/preferences/SharedPreferencesStore;", "app_debug"})
@dagger.Module()
public abstract class SharedPreferencesStoreModule {
    
    @org.jetbrains.annotations.NotNull()
    @dagger.Binds()
    public abstract org.owntracks.android.support.preferences.PreferencesStore bindSharedPreferencesStoreModule(@org.jetbrains.annotations.NotNull()
    org.owntracks.android.support.preferences.SharedPreferencesStore sharedPreferencesStore);
    
    public SharedPreferencesStoreModule() {
        super();
    }
}