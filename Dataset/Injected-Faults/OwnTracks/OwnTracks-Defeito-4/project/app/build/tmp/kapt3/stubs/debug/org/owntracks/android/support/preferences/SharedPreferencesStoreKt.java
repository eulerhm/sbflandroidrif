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

@kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 2, d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T\u00a2\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0003"}, d2 = {"FILENAME_HTTP", "", "FILENAME_PRIVATE", "app_debug"})
public final class SharedPreferencesStoreKt {
    private static final java.lang.String FILENAME_PRIVATE = "org.owntracks.android.preferences.private";
    private static final java.lang.String FILENAME_HTTP = "org.owntracks.android.preferences.http";
}