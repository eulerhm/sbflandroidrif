package org.owntracks.android.geocoding;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.TextView;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import org.owntracks.android.R;
import org.owntracks.android.injection.qualifier.AppContext;
import javax.inject.Singleton;
import org.owntracks.android.model.messages.MessageLocation;
import org.owntracks.android.services.BackgroundService;
import org.owntracks.android.support.Preferences;
import org.owntracks.android.support.preferences.OnModeChangedPreferenceChangedListener;
import timber.log.Timber;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0019\b\u0007\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u001b\u0010\r\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0082@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0011J\u000e\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u000f\u001a\u00020\u0010J\u0016\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0014\u001a\u00020\u0015J\u0016\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0016\u001a\u00020\u0017J\u001a\u0010\u0018\u001a\u00020\u00132\b\b\u0001\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0002R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u000e\u0010\t\u001a\u00020\nX\u0082.\u00a2\u0006\u0002\n\u0000R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u0019"}, d2 = {"Lorg/owntracks/android/geocoding/GeocoderProvider;", "", "context", "Landroid/content/Context;", "preferences", "Lorg/owntracks/android/support/Preferences;", "(Landroid/content/Context;Lorg/owntracks/android/support/Preferences;)V", "getContext", "()Landroid/content/Context;", "geocoder", "Lorg/owntracks/android/geocoding/Geocoder;", "getPreferences", "()Lorg/owntracks/android/support/Preferences;", "geocoderResolve", "", "messageLocation", "Lorg/owntracks/android/model/messages/MessageLocation;", "(Lorg/owntracks/android/model/messages/MessageLocation;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "resolve", "", "textView", "Landroid/widget/TextView;", "backgroundService", "Lorg/owntracks/android/services/BackgroundService;", "setGeocoderProvider", "app_debug"})
@javax.inject.Singleton()
public final class GeocoderProvider {
    private org.owntracks.android.geocoding.Geocoder geocoder;
    @org.jetbrains.annotations.NotNull()
    private final android.content.Context context = null;
    @org.jetbrains.annotations.NotNull()
    private final org.owntracks.android.support.Preferences preferences = null;
    
    private final void setGeocoderProvider(@org.owntracks.android.injection.qualifier.AppContext()
    android.content.Context context, org.owntracks.android.support.Preferences preferences) {
    }
    
    public final void resolve(@org.jetbrains.annotations.NotNull()
    org.owntracks.android.model.messages.MessageLocation messageLocation) {
    }
    
    public final void resolve(@org.jetbrains.annotations.NotNull()
    org.owntracks.android.model.messages.MessageLocation messageLocation, @org.jetbrains.annotations.NotNull()
    org.owntracks.android.services.BackgroundService backgroundService) {
    }
    
    public final void resolve(@org.jetbrains.annotations.NotNull()
    org.owntracks.android.model.messages.MessageLocation messageLocation, @org.jetbrains.annotations.NotNull()
    android.widget.TextView textView) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final android.content.Context getContext() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.owntracks.android.support.Preferences getPreferences() {
        return null;
    }
    
    @javax.inject.Inject()
    public GeocoderProvider(@org.jetbrains.annotations.NotNull()
    @org.owntracks.android.injection.qualifier.AppContext()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    org.owntracks.android.support.Preferences preferences) {
        super();
    }
}