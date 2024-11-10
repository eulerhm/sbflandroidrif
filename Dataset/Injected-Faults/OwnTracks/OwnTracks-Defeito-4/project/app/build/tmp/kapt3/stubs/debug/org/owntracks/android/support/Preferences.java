package org.owntracks.android.support;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.greenrobot.eventbus.EventBus;
import org.owntracks.android.BuildConfig;
import org.owntracks.android.R;
import org.owntracks.android.injection.qualifier.AppContext;
import javax.inject.Singleton;
import org.owntracks.android.model.messages.MessageConfiguration;
import org.owntracks.android.services.LocationProcessor;
import org.owntracks.android.services.MessageProcessorEndpointHttp;
import org.owntracks.android.services.MessageProcessorEndpointMqtt;
import org.owntracks.android.services.worker.Scheduler;
import org.owntracks.android.support.Events.ModeChanged;
import org.owntracks.android.support.Events.MonitoringChanged;
import org.owntracks.android.support.preferences.OnModeChangedPreferenceChangedListener;
import org.owntracks.android.support.preferences.PreferencesStore;
import timber.log.Timber;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.*;
import java.util.concurrent.TimeUnit;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u008d\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\u000f\n\u0002\u0010\"\n\u0002\b\u0006\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u0006\n\u0002\b\u0006\n\u0002\u0010 \n\u0002\b\u001a\n\u0002\u0010\t\n\u0003\b\u0086\u0001\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0017\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u0000 \u009e\u00022\u00020\u0001:\u0006\u009e\u0002\u009f\u0002\u00a0\u0002B#\b\u0007\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\b\u0010\u00ed\u0001\u001a\u00030\u00ee\u0001J\u0013\u0010\u00ef\u0001\u001a\u00030\u00ee\u00012\u0007\u0010\u00f0\u0001\u001a\u00020\u001dH\u0002J\u0015\u0010\u00ef\u0001\u001a\u00030\u00ee\u00012\t\u0010\u0081\u0001\u001a\u0004\u0018\u00010\u0014H\u0002J\u001b\u0010\u00f1\u0001\u001a\u00020\u00012\b\u0010\u00f2\u0001\u001a\u00030\u00f3\u00012\u0006\u0010,\u001a\u00020\u0014H\u0002J\b\u0010\u00f4\u0001\u001a\u00030\u00f5\u0001J\u001b\u0010\u00f6\u0001\u001a\u00020\n2\u0007\u0010\u00f0\u0001\u001a\u00020\u001d2\u0007\u0010\u00f7\u0001\u001a\u00020\u001dH\u0002J\u0012\u0010\u00f8\u0001\u001a\u00020\n2\u0007\u0010\u00f9\u0001\u001a\u00020\u001dH\u0002J\u000f\u0010#\u001a\u00020\u00142\u0007\u0010\u00fa\u0001\u001a\u00020\nJ\u001b\u0010\u00fb\u0001\u001a\u00020\u001d2\u0007\u0010\u00f0\u0001\u001a\u00020\u001d2\u0007\u0010\u00f7\u0001\u001a\u00020\u001dH\u0002J\u0012\u0010\u00fc\u0001\u001a\u00020\u001d2\u0007\u0010\u00f9\u0001\u001a\u00020\u001dH\u0002J\u0012\u0010\u00fd\u0001\u001a\u00020\u00142\u0007\u0010\u00f0\u0001\u001a\u00020\u001dH\u0002J\u0010\u0010\u00fe\u0001\u001a\u00020\u00142\u0007\u0010\u00ff\u0001\u001a\u00020\u001dJ\u001b\u0010\u0080\u0002\u001a\u00020\u00142\u0007\u0010\u00f0\u0001\u001a\u00020\u001d2\u0007\u0010\u00f7\u0001\u001a\u00020\u001dH\u0002J\u0012\u0010\u0081\u0002\u001a\u00020\u00142\u0007\u0010\u00f9\u0001\u001a\u00020\u001dH\u0002J\u0018\u0010\u0082\u0002\u001a\b\u0012\u0004\u0012\u00020\u00140-2\u0007\u0010\u00f0\u0001\u001a\u00020\u001dH\u0002J\u0010\u0010\u00df\u0001\u001a\u00020\u00142\u0007\u0010\u0083\u0002\u001a\u00020\nJ\u0014\u0010\u0084\u0002\u001a\u00030\u00ee\u00012\b\u0010\u0085\u0002\u001a\u00030\u00f5\u0001H\u0007J\u001d\u0010\u0086\u0002\u001a\u00030\u00ee\u00012\t\u0010\u0081\u0001\u001a\u0004\u0018\u00010\u00142\b\u0010,\u001a\u0004\u0018\u00010\u0014J\u0010\u0010\u0087\u0002\u001a\u00020\n2\u0007\u0010\u0088\u0002\u001a\u00020\u0014J\u0010\u0010\u0089\u0002\u001a\u00020\n2\u0007\u0010\u008a\u0002\u001a\u00020\u001dJ\u0014\u0010\u008b\u0002\u001a\u00030\u00ee\u00012\n\u0010\u008c\u0002\u001a\u0005\u0018\u00010\u008d\u0002J\u001b\u0010\u008e\u0002\u001a\u00030\u00ee\u00012\u0007\u0010\u00f0\u0001\u001a\u00020\u001d2\u0006\u0010,\u001a\u00020\nH\u0002J\u0012\u0010\u008f\u0002\u001a\u00030\u00ee\u00012\u0006\u0010\t\u001a\u00020\nH\u0007J\u001b\u0010\u0090\u0002\u001a\u00030\u00ee\u00012\u0007\u0010\u00f0\u0001\u001a\u00020\u001d2\u0006\u0010,\u001a\u00020\u001dH\u0002J\b\u0010\u0091\u0002\u001a\u00030\u00ee\u0001J\u001b\u0010m\u001a\u00030\u00ee\u00012\u0007\u0010\u0092\u0002\u001a\u00020\u001d2\u0007\u0010\u0093\u0002\u001a\u00020\nH\u0002J\b\u0010\u0094\u0002\u001a\u00030\u00ee\u0001J\b\u0010\u0095\u0002\u001a\u00030\u00ee\u0001J\b\u0010\u0096\u0002\u001a\u00030\u00ee\u0001J\b\u0010\u0097\u0002\u001a\u00030\u00ee\u0001J\u001b\u0010\u0098\u0002\u001a\u00030\u00ee\u00012\u0007\u0010\u00f0\u0001\u001a\u00020\u001d2\u0006\u0010,\u001a\u00020\u0014H\u0002J!\u0010\u0099\u0002\u001a\u00030\u00ee\u00012\u0007\u0010\u00f0\u0001\u001a\u00020\u001d2\f\u0010,\u001a\b\u0012\u0004\u0012\u00020\u00140-H\u0002J\u0014\u0010\u009a\u0002\u001a\u00030\u00ee\u00012\n\u0010\u008c\u0002\u001a\u0005\u0018\u00010\u008d\u0002J\u001e\u0010\u009b\u0002\u001a\u000e\u0012\t\u0012\u0007\u0012\u0002\b\u00030\u009d\u00020\u009c\u0002*\u0007\u0012\u0002\b\u00030\u009d\u0002H\u0002R$\u0010\u000b\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\n8G@GX\u0086\u000e\u00a2\u0006\f\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR$\u0010\u0010\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\n8G@GX\u0086\u000e\u00a2\u0006\f\u001a\u0004\b\u0011\u0010\r\"\u0004\b\u0012\u0010\u000fR$\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0013\u001a\u00020\u00148G@GX\u0086\u000e\u00a2\u0006\f\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\u0014\u0010\u0019\u001a\u00020\u00148BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\u001a\u0010\u0016R\u000e\u0010\u001b\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u001dX\u0082\u000e\u00a2\u0006\u0002\n\u0000R$\u0010\u001f\u001a\u00020\n2\u0006\u0010\u001e\u001a\u00020\n8G@GX\u0086\u000e\u00a2\u0006\f\u001a\u0004\b \u0010\r\"\u0004\b!\u0010\u000fR$\u0010\"\u001a\u00020\u00142\u0006\u0010\"\u001a\u00020\u00148G@GX\u0086\u000e\u00a2\u0006\f\u001a\u0004\b#\u0010\u0016\"\u0004\b$\u0010\u0018R\u0014\u0010%\u001a\u00020\u00148BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b&\u0010\u0016R$\u0010\'\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\n8G@GX\u0086\u000e\u00a2\u0006\f\u001a\u0004\b(\u0010\r\"\u0004\b)\u0010\u000fR\u0011\u0010*\u001a\u00020\u00148F\u00a2\u0006\u0006\u001a\u0004\b+\u0010\u0016R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R0\u0010.\u001a\b\u0012\u0004\u0012\u00020\u00140-2\f\u0010,\u001a\b\u0012\u0004\u0012\u00020\u00140-8G@GX\u0086\u000e\u00a2\u0006\f\u001a\u0004\b/\u00100\"\u0004\b1\u00102R \u00103\u001a\u000e\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u000205048BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b6\u00107R$\u00108\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\n8G@GX\u0086\u000e\u00a2\u0006\f\u001a\u0004\b9\u0010\r\"\u0004\b:\u0010\u000fR$\u0010;\u001a\u00020\u00142\u0006\u0010,\u001a\u00020\u00148G@GX\u0086\u000e\u00a2\u0006\f\u001a\u0004\b<\u0010\u0016\"\u0004\b=\u0010\u0018R$\u0010?\u001a\u00020\u001d2\u0006\u0010>\u001a\u00020\u001d8G@GX\u0086\u000e\u00a2\u0006\f\u001a\u0004\b@\u0010A\"\u0004\bB\u0010CR$\u0010F\u001a\u00020E2\u0006\u0010D\u001a\u00020E8G@GX\u0086\u000e\u00a2\u0006\f\u001a\u0004\bG\u0010H\"\u0004\bI\u0010JR\u0017\u0010K\u001a\b\u0012\u0004\u0012\u00020\u00140L8F\u00a2\u0006\u0006\u001a\u0004\bM\u0010NR \u0010O\u001a\u000e\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u000205048BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\bP\u00107R$\u0010Q\u001a\u00020\n2\u0006\u0010Q\u001a\u00020\n8G@GX\u0086\u000e\u00a2\u0006\f\u001a\u0004\bR\u0010\r\"\u0004\bS\u0010\u000fR\u000e\u0010T\u001a\u00020\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0011\u0010U\u001a\u00020\n8F\u00a2\u0006\u0006\u001a\u0004\bU\u0010\rR\u0011\u0010V\u001a\u00020\n8F\u00a2\u0006\u0006\u001a\u0004\bV\u0010\rR$\u0010W\u001a\u00020\u001d2\u0006\u0010,\u001a\u00020\u001d8G@GX\u0086\u000e\u00a2\u0006\f\u001a\u0004\bX\u0010A\"\u0004\bY\u0010CR\u0011\u0010Z\u001a\u00020\u00148F\u00a2\u0006\u0006\u001a\u0004\b[\u0010\u0016R$\u0010]\u001a\u00020\u001d2\u0006\u0010\\\u001a\u00020\u001d8G@GX\u0086\u000e\u00a2\u0006\f\u001a\u0004\b^\u0010A\"\u0004\b_\u0010CR$\u0010`\u001a\u00020\u001d2\u0006\u0010\\\u001a\u00020\u001d8G@GX\u0086\u000e\u00a2\u0006\f\u001a\u0004\ba\u0010A\"\u0004\bb\u0010CR$\u0010c\u001a\u00020\u001d2\u0006\u0010\\\u001a\u00020\u001d8G@GX\u0086\u000e\u00a2\u0006\f\u001a\u0004\bd\u0010A\"\u0004\be\u0010CR\u0011\u0010f\u001a\u00020g\u00a2\u0006\b\n\u0000\u001a\u0004\bh\u0010iR$\u0010k\u001a\u00020\u001d2\u0006\u0010j\u001a\u00020\u001d8G@GX\u0086\u000e\u00a2\u0006\f\u001a\u0004\bl\u0010A\"\u0004\bm\u0010CR$\u0010o\u001a\u00020\u001d2\u0006\u0010n\u001a\u00020\u001d8G@GX\u0086\u000e\u00a2\u0006\f\u001a\u0004\bp\u0010A\"\u0004\bq\u0010CR$\u0010r\u001a\u00020\u001d2\u0006\u0010r\u001a\u00020\u001d8G@GX\u0086\u000e\u00a2\u0006\f\u001a\u0004\bs\u0010A\"\u0004\bt\u0010CR$\u0010u\u001a\u00020\u001d2\u0006\u0010u\u001a\u00020\u001d8G@GX\u0086\u000e\u00a2\u0006\f\u001a\u0004\bv\u0010A\"\u0004\bw\u0010CR$\u0010x\u001a\u00020\n2\u0006\u0010x\u001a\u00020\n8G@GX\u0086\u000e\u00a2\u0006\f\u001a\u0004\by\u0010\r\"\u0004\bz\u0010\u000fR$\u0010{\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\n8G@GX\u0086\u000e\u00a2\u0006\f\u001a\u0004\b|\u0010\r\"\u0004\b}\u0010\u000fR%\u0010~\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\n8G@GX\u0086\u000e\u00a2\u0006\r\u001a\u0004\b\u007f\u0010\r\"\u0005\b\u0080\u0001\u0010\u000fR(\u0010\u0082\u0001\u001a\u00020\u00142\u0007\u0010\u0081\u0001\u001a\u00020\u00148G@GX\u0086\u000e\u00a2\u0006\u000e\u001a\u0005\b\u0083\u0001\u0010\u0016\"\u0005\b\u0084\u0001\u0010\u0018R(\u0010\u0085\u0001\u001a\u00020\u00142\u0007\u0010\u0085\u0001\u001a\u00020\u00148G@GX\u0086\u000e\u00a2\u0006\u000e\u001a\u0005\b\u0086\u0001\u0010\u0016\"\u0005\b\u0087\u0001\u0010\u0018R\'\u0010\u0088\u0001\u001a\u00020\u001d2\u0006\u0010\\\u001a\u00020\u001d8G@GX\u0086\u000e\u00a2\u0006\u000e\u001a\u0005\b\u0089\u0001\u0010A\"\u0005\b\u008a\u0001\u0010CR(\u0010\u008b\u0001\u001a\u00020\u001d2\u0007\u0010\u008b\u0001\u001a\u00020\u001d8G@GX\u0086\u000e\u00a2\u0006\u000e\u001a\u0005\b\u008c\u0001\u0010A\"\u0005\b\u008d\u0001\u0010CR\u0013\u0010\u008e\u0001\u001a\u00020\u00148F\u00a2\u0006\u0007\u001a\u0005\b\u008f\u0001\u0010\u0016R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\'\u0010\u0090\u0001\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\n8G@GX\u0086\u000e\u00a2\u0006\u000e\u001a\u0005\b\u0091\u0001\u0010\r\"\u0005\b\u0092\u0001\u0010\u000fR\'\u0010\u0093\u0001\u001a\u00020\u001d2\u0006\u0010\\\u001a\u00020\u001d8G@GX\u0086\u000e\u00a2\u0006\u000e\u001a\u0005\b\u0094\u0001\u0010A\"\u0005\b\u0095\u0001\u0010CR\u0013\u0010\u0096\u0001\u001a\u00020\u001d8F\u00a2\u0006\u0007\u001a\u0005\b\u0097\u0001\u0010AR\u0013\u0010\u0098\u0001\u001a\u00020\u001d8F\u00a2\u0006\u0007\u001a\u0005\b\u0099\u0001\u0010AR\u0013\u0010\u009a\u0001\u001a\u00020\u001d8F\u00a2\u0006\u0007\u001a\u0005\b\u009b\u0001\u0010AR\'\u0010\u009c\u0001\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\n8G@GX\u0086\u000e\u00a2\u0006\u000e\u001a\u0005\b\u009d\u0001\u0010\r\"\u0005\b\u009e\u0001\u0010\u000fR\u0013\u0010\u009f\u0001\u001a\u00020\n8F\u00a2\u0006\u0007\u001a\u0005\b\u00a0\u0001\u0010\rR\u0013\u0010\u00a1\u0001\u001a\u00020\n8F\u00a2\u0006\u0007\u001a\u0005\b\u00a2\u0001\u0010\rR\u0013\u0010\u00a3\u0001\u001a\u00020\n8F\u00a2\u0006\u0007\u001a\u0005\b\u00a4\u0001\u0010\rR\u0013\u0010\u00a5\u0001\u001a\u00020\u00148F\u00a2\u0006\u0007\u001a\u0005\b\u00a6\u0001\u0010\u0016R(\u0010\u00a8\u0001\u001a\u00020\u00142\u0007\u0010\u00a7\u0001\u001a\u00020\u00148G@GX\u0086\u000e\u00a2\u0006\u000e\u001a\u0005\b\u00a9\u0001\u0010\u0016\"\u0005\b\u00aa\u0001\u0010\u0018R\u0013\u0010\u00ab\u0001\u001a\u00020\u00148F\u00a2\u0006\u0007\u001a\u0005\b\u00ac\u0001\u0010\u0016R\u0013\u0010\u00ad\u0001\u001a\u00020\u00148F\u00a2\u0006\u0007\u001a\u0005\b\u00ae\u0001\u0010\u0016R\u0013\u0010\u00af\u0001\u001a\u00020\u00148F\u00a2\u0006\u0007\u001a\u0005\b\u00b0\u0001\u0010\u0016R\u0013\u0010\u00b1\u0001\u001a\u00020\u00148F\u00a2\u0006\u0007\u001a\u0005\b\u00b2\u0001\u0010\u0016R\u0013\u0010\u00b3\u0001\u001a\u00020\u00148F\u00a2\u0006\u0007\u001a\u0005\b\u00b4\u0001\u0010\u0016R\u0013\u0010\u00b5\u0001\u001a\u00020\u00148F\u00a2\u0006\u0007\u001a\u0005\b\u00b6\u0001\u0010\u0016R\u0013\u0010\u00b7\u0001\u001a\u00020\u00148F\u00a2\u0006\u0007\u001a\u0005\b\u00b8\u0001\u0010\u0016R\u0013\u0010\u00b9\u0001\u001a\u00020\u00148F\u00a2\u0006\u0007\u001a\u0005\b\u00ba\u0001\u0010\u0016R\'\u0010\u00bb\u0001\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\n8G@GX\u0086\u000e\u00a2\u0006\u000e\u001a\u0005\b\u00bc\u0001\u0010\r\"\u0005\b\u00bd\u0001\u0010\u000fR\'\u0010\u00be\u0001\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\n8G@GX\u0086\u000e\u00a2\u0006\u000e\u001a\u0005\b\u00bf\u0001\u0010\r\"\u0005\b\u00c0\u0001\u0010\u000fR\'\u0010\u00c1\u0001\u001a\u00020\u00142\u0006\u0010\t\u001a\u00020\u00148G@GX\u0086\u000e\u00a2\u0006\u000e\u001a\u0005\b\u00c2\u0001\u0010\u0016\"\u0005\b\u00c3\u0001\u0010\u0018R\u0013\u0010\u00c4\u0001\u001a\u00020\u00148F\u00a2\u0006\u0007\u001a\u0005\b\u00c5\u0001\u0010\u0016R(\u0010\u00c6\u0001\u001a\u00020\n2\u0007\u0010\u00c6\u0001\u001a\u00020\n8G@GX\u0086\u000e\u00a2\u0006\u000e\u001a\u0005\b\u00c7\u0001\u0010\r\"\u0005\b\u00c8\u0001\u0010\u000fR\'\u0010\u00c9\u0001\u001a\u00020\u001d2\u0006\u0010\\\u001a\u00020\u001d8G@GX\u0086\u000e\u00a2\u0006\u000e\u001a\u0005\b\u00ca\u0001\u0010A\"\u0005\b\u00cb\u0001\u0010CR(\u0010\u00cd\u0001\u001a\u00020\u00142\u0007\u0010\u00cc\u0001\u001a\u00020\u00148G@GX\u0086\u000e\u00a2\u0006\u000e\u001a\u0005\b\u00ce\u0001\u0010\u0016\"\u0005\b\u00cf\u0001\u0010\u0018R(\u0010\u00d1\u0001\u001a\u00020\n2\u0007\u0010\u00d0\u0001\u001a\u00020\n8G@GX\u0086\u000e\u00a2\u0006\u000e\u001a\u0005\b\u00d2\u0001\u0010\r\"\u0005\b\u00d3\u0001\u0010\u000fR(\u0010\u00d5\u0001\u001a\u00020\u00142\u0007\u0010\u00d4\u0001\u001a\u00020\u00148G@GX\u0086\u000e\u00a2\u0006\u000e\u001a\u0005\b\u00d6\u0001\u0010\u0016\"\u0005\b\u00d7\u0001\u0010\u0018R(\u0010\u00d8\u0001\u001a\u00020\u00142\u0007\u0010\u00d4\u0001\u001a\u00020\u00148G@GX\u0086\u000e\u00a2\u0006\u000e\u001a\u0005\b\u00d9\u0001\u0010\u0016\"\u0005\b\u00da\u0001\u0010\u0018R(\u0010\u00db\u0001\u001a\u00020\u00142\u0007\u0010\u0085\u0001\u001a\u00020\u00148G@GX\u0086\u000e\u00a2\u0006\u000e\u001a\u0005\b\u00dc\u0001\u0010\u0016\"\u0005\b\u00dd\u0001\u0010\u0018R(\u0010\u00de\u0001\u001a\u00020\u00142\u0007\u0010\u00de\u0001\u001a\u00020\u00148G@GX\u0086\u000e\u00a2\u0006\u000e\u001a\u0005\b\u00df\u0001\u0010\u0016\"\u0005\b\u00e0\u0001\u0010\u0018R\u0016\u0010\u00e1\u0001\u001a\u00020\u00148BX\u0082\u0004\u00a2\u0006\u0007\u001a\u0005\b\u00e2\u0001\u0010\u0016R(\u0010\u00e3\u0001\u001a\u00020\u00142\u0007\u0010\u00e3\u0001\u001a\u00020\u00148G@GX\u0086\u000e\u00a2\u0006\u000e\u001a\u0005\b\u00e4\u0001\u0010\u0016\"\u0005\b\u00e5\u0001\u0010\u0018R\'\u0010\u00e6\u0001\u001a\u00020\u00142\u0006\u0010,\u001a\u00020\u00148G@GX\u0086\u000e\u00a2\u0006\u000e\u001a\u0005\b\u00e7\u0001\u0010\u0016\"\u0005\b\u00e8\u0001\u0010\u0018R(\u0010\u00ea\u0001\u001a\u00020\n2\u0007\u0010\u00e9\u0001\u001a\u00020\n8G@GX\u0086\u000e\u00a2\u0006\u000e\u001a\u0005\b\u00eb\u0001\u0010\r\"\u0005\b\u00ec\u0001\u0010\u000f\u00a8\u0006\u00a1\u0002"}, d2 = {"Lorg/owntracks/android/support/Preferences;", "", "c", "Landroid/content/Context;", "eventBus", "Lorg/greenrobot/eventbus/EventBus;", "preferencesStore", "Lorg/owntracks/android/support/preferences/PreferencesStore;", "(Landroid/content/Context;Lorg/greenrobot/eventbus/EventBus;Lorg/owntracks/android/support/preferences/PreferencesStore;)V", "newValue", "", "autostartOnBoot", "getAutostartOnBoot", "()Z", "setAutostartOnBoot", "(Z)V", "cleanSession", "getCleanSession", "setCleanSession", "clientId", "", "getClientId", "()Ljava/lang/String;", "setClientId", "(Ljava/lang/String;)V", "clientIdDefault", "getClientIdDefault", "context", "currentMode", "", "debug", "debugLog", "getDebugLog", "setDebugLog", "deviceId", "getDeviceId", "setDeviceId", "deviceIdDefault", "getDeviceIdDefault", "dontReuseHttpClient", "getDontReuseHttpClient", "setDontReuseHttpClient", "encryptionKey", "getEncryptionKey", "value", "", "experimentalFeatures", "getExperimentalFeatures", "()Ljava/util/Set;", "setExperimentalFeatures", "(Ljava/util/Set;)V", "exportMethods", "", "Ljava/lang/reflect/Method;", "getExportMethods", "()Ljava/util/Map;", "fusedRegionDetection", "getFusedRegionDetection", "setFusedRegionDetection", "host", "getHost", "setHost", "meters", "ignoreInaccurateLocations", "getIgnoreInaccurateLocations", "()I", "setIgnoreInaccurateLocations", "(I)V", "days", "", "ignoreStaleLocations", "getIgnoreStaleLocations", "()D", "setIgnoreStaleLocations", "(D)V", "importKeys", "", "getImportKeys", "()Ljava/util/List;", "importMethods", "getImportMethods", "info", "getInfo", "setInfo", "isFirstStart", "isObjectboxMigrated", "isSetupCompleted", "keepalive", "getKeepalive", "setKeepalive", "keepaliveWithHintSupport", "getKeepaliveWithHintSupport", "anInt", "locatorDisplacement", "getLocatorDisplacement", "setLocatorDisplacement", "locatorInterval", "getLocatorInterval", "setLocatorInterval", "locatorPriority", "getLocatorPriority", "setLocatorPriority", "minimumKeepalive", "", "getMinimumKeepalive", "()J", "active", "mode", "getMode", "setMode", "newMode", "monitoring", "getMonitoring", "setMonitoring", "moveModeLocatorInterval", "getMoveModeLocatorInterval", "setMoveModeLocatorInterval", "mqttProtocolLevel", "getMqttProtocolLevel", "setMqttProtocolLevel", "notificationEvents", "getNotificationEvents", "setNotificationEvents", "notificationHigherPriority", "getNotificationHigherPriority", "setNotificationHigherPriority", "notificationLocation", "getNotificationLocation", "setNotificationLocation", "key", "openCageGeocoderApiKey", "getOpenCageGeocoderApiKey", "setOpenCageGeocoderApiKey", "password", "getPassword", "setPassword", "ping", "getPing", "setPing", "port", "getPort", "setPort", "portWithHintSupport", "getPortWithHintSupport", "pubLocationExtendedData", "getPubLocationExtendedData", "setPubLocationExtendedData", "pubQos", "getPubQos", "setPubQos", "pubQosEvents", "getPubQosEvents", "pubQosLocations", "getPubQosLocations", "pubQosWaypoints", "getPubQosWaypoints", "pubRetain", "getPubRetain", "setPubRetain", "pubRetainEvents", "getPubRetainEvents", "pubRetainLocations", "getPubRetainLocations", "pubRetainWaypoints", "getPubRetainWaypoints", "pubTopicBase", "getPubTopicBase", "deviceTopic", "pubTopicBaseFormatString", "getPubTopicBaseFormatString", "setPubTopicBaseFormatString", "pubTopicCommands", "getPubTopicCommands", "pubTopicCommandsPart", "getPubTopicCommandsPart", "pubTopicEvents", "getPubTopicEvents", "pubTopicEventsPart", "getPubTopicEventsPart", "pubTopicInfoPart", "getPubTopicInfoPart", "pubTopicLocations", "getPubTopicLocations", "pubTopicWaypoints", "getPubTopicWaypoints", "pubTopicWaypointsPart", "getPubTopicWaypointsPart", "remoteCommand", "getRemoteCommand", "setRemoteCommand", "remoteConfiguration", "getRemoteConfiguration", "setRemoteConfiguration", "reverseGeocodeProvider", "getReverseGeocodeProvider", "setReverseGeocodeProvider", "sharedPreferencesName", "getSharedPreferencesName", "sub", "getSub", "setSub", "subQos", "getSubQos", "setSubQos", "string", "subTopic", "getSubTopic", "setSubTopic", "tlsSpecifier", "tls", "getTls", "setTls", "name", "tlsCaCrt", "getTlsCaCrt", "setTlsCaCrt", "tlsClientCrt", "getTlsClientCrt", "setTlsClientCrt", "tlsClientCrtPassword", "getTlsClientCrtPassword", "setTlsClientCrtPassword", "trackerId", "getTrackerId", "setTrackerId", "trackerIdDefault", "getTrackerIdDefault", "url", "getUrl", "setUrl", "username", "getUsername", "setUsername", "wsEnable", "ws", "getWs", "setWs", "checkFirstStart", "", "clearKey", "resKeyId", "convert", "t", "Ljava/lang/reflect/Type;", "exportToMessage", "Lorg/owntracks/android/model/messages/MessageConfiguration;", "getBooleanOrDefault", "defId", "getBooleanResource", "resId", "fallbackToDefault", "getIntOrDefault", "getIntResource", "getIntWithHintSupport", "getPreferenceKey", "res", "getStringOrDefault", "getStringResource", "getStringSet", "fallback", "importFromMessage", "messageConfiguration", "importKeyValue", "isExperimentalFeatureEnabled", "feature", "keepAliveInRange", "i", "registerOnPreferenceChangedListener", "listener", "Lorg/owntracks/android/support/preferences/OnModeChangedPreferenceChangedListener;", "setBoolean", "setGeocodeEnabled", "setInt", "setKeepaliveDefault", "requestedMode", "init", "setMonitoringNext", "setObjectBoxMigrated", "setPortDefault", "setSetupCompleted", "setString", "setStringSet", "unregisterOnPreferenceChangedListener", "parentClasses", "Lkotlin/sequences/Sequence;", "Ljava/lang/Class;", "Companion", "Export", "Import", "app_debug"})
@javax.inject.Singleton()
public final class Preferences {
    private final android.content.Context context = null;
    private boolean isFirstStart = false;
    private int currentMode = org.owntracks.android.services.MessageProcessorEndpointMqtt.MODE_ID;
    private final long minimumKeepalive = 0L;
    private final org.greenrobot.eventbus.EventBus eventBus = null;
    private final org.owntracks.android.support.preferences.PreferencesStore preferencesStore = null;
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String EXPERIMENTAL_FEATURE_ALLOW_SMALL_KEEPALIVE = "allowSmallKeepalive";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String REVERSE_GEOCODE_PROVIDER_NONE = "None";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String REVERSE_GEOCODE_PROVIDER_GOOGLE = "Google";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String REVERSE_GEOCODE_PROVIDER_OPENCAGE = "OpenCage";
    @org.jetbrains.annotations.NotNull()
    private static final java.util.List<java.lang.String> REVERSE_GEOCODE_PROVIDERS = null;
    @org.jetbrains.annotations.NotNull()
    public static final org.owntracks.android.support.Preferences.Companion Companion = null;
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getSharedPreferencesName() {
        return null;
    }
    
    private final java.util.Map<java.lang.String, java.lang.reflect.Method> getExportMethods() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<java.lang.String> getImportKeys() {
        return null;
    }
    
    private final java.util.Map<java.lang.String, java.lang.reflect.Method> getImportMethods() {
        return null;
    }
    
    private final kotlin.sequences.Sequence<java.lang.Class<?>> parentClasses(java.lang.Class<?> $this$parentClasses) {
        return null;
    }
    
    public final void registerOnPreferenceChangedListener(@org.jetbrains.annotations.Nullable()
    org.owntracks.android.support.preferences.OnModeChangedPreferenceChangedListener listener) {
    }
    
    public final void unregisterOnPreferenceChangedListener(@org.jetbrains.annotations.Nullable()
    org.owntracks.android.support.preferences.OnModeChangedPreferenceChangedListener listener) {
    }
    
    public final void checkFirstStart() {
    }
    
    public final void importKeyValue(@org.jetbrains.annotations.Nullable()
    java.lang.String key, @org.jetbrains.annotations.Nullable()
    java.lang.String value) throws java.lang.IllegalAccessException, java.lang.IllegalArgumentException {
    }
    
    private final java.lang.Object convert(java.lang.reflect.Type t, java.lang.String value) throws java.lang.IllegalArgumentException {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.owntracks.android.model.messages.MessageConfiguration exportToMessage() {
        return null;
    }
    
    @android.annotation.SuppressLint(value = {"CommitPrefEdits", "ApplySharedPref"})
    public final void importFromMessage(@org.jetbrains.annotations.NotNull()
    org.owntracks.android.model.messages.MessageConfiguration messageConfiguration) {
    }
    
    private final void setMode(int requestedMode, boolean init) {
    }
    
    public final void setMonitoringNext() {
    }
    
    @org.owntracks.android.support.Preferences.Export(keyResId = org.owntracks.android.R.string.preferenceKeyModeId, exportModeMqtt = true, exportModeHttp = true)
    public final int getMode() {
        return 0;
    }
    
    @org.owntracks.android.support.Preferences.Import(keyResId = org.owntracks.android.R.string.preferenceKeyModeId)
    public final void setMode(int active) {
    }
    
    @org.owntracks.android.support.Preferences.Export(keyResId = org.owntracks.android.R.string.preferenceKeyMonitoring, exportModeMqtt = true, exportModeHttp = true)
    public final int getMonitoring() {
        return 0;
    }
    
    @org.owntracks.android.support.Preferences.Import(keyResId = org.owntracks.android.R.string.preferenceKeyMonitoring)
    public final void setMonitoring(int newMode) {
    }
    
    @org.owntracks.android.support.Preferences.Export(keyResId = org.owntracks.android.R.string.preferenceKeyDontReuseHttpClient, exportModeMqtt = false, exportModeHttp = true)
    public final boolean getDontReuseHttpClient() {
        return false;
    }
    
    @org.owntracks.android.support.Preferences.Import(keyResId = org.owntracks.android.R.string.preferenceKeyDontReuseHttpClient)
    public final void setDontReuseHttpClient(boolean newValue) {
    }
    
    @org.jetbrains.annotations.NotNull()
    @org.owntracks.android.support.Preferences.Export(keyResId = org.owntracks.android.R.string.preferenceKeyOpencageGeocoderApiKey, exportModeMqtt = true, exportModeHttp = true)
    public final java.lang.String getOpenCageGeocoderApiKey() {
        return null;
    }
    
    @org.owntracks.android.support.Preferences.Import(keyResId = org.owntracks.android.R.string.preferenceKeyOpencageGeocoderApiKey)
    public final void setOpenCageGeocoderApiKey(@org.jetbrains.annotations.NotNull()
    java.lang.String key) {
    }
    
    @org.owntracks.android.support.Preferences.Export(keyResId = org.owntracks.android.R.string.preferenceKeyRemoteCommand, exportModeMqtt = true, exportModeHttp = true)
    public final boolean getRemoteCommand() {
        return false;
    }
    
    @org.owntracks.android.support.Preferences.Import(keyResId = org.owntracks.android.R.string.preferenceKeyRemoteCommand)
    public final void setRemoteCommand(boolean newValue) {
    }
    
    @org.owntracks.android.support.Preferences.Export(keyResId = org.owntracks.android.R.string.preferenceKeyCleanSession, exportModeMqtt = true, exportModeHttp = true)
    public final boolean getCleanSession() {
        return false;
    }
    
    @org.owntracks.android.support.Preferences.Import(keyResId = org.owntracks.android.R.string.preferenceKeyCleanSession)
    public final void setCleanSession(boolean newValue) {
    }
    
    @org.owntracks.android.support.Preferences.Export(keyResId = org.owntracks.android.R.string.preferenceKeyPublishExtendedData, exportModeMqtt = true, exportModeHttp = true)
    public final boolean getPubLocationExtendedData() {
        return false;
    }
    
    @org.owntracks.android.support.Preferences.Import(keyResId = org.owntracks.android.R.string.preferenceKeyPublishExtendedData)
    public final void setPubLocationExtendedData(boolean newValue) {
    }
    
    @org.owntracks.android.support.Preferences.Export(keyResId = org.owntracks.android.R.string.preferenceKeyLocatorInterval, exportModeMqtt = true, exportModeHttp = true)
    public final int getLocatorInterval() {
        return 0;
    }
    
    @org.owntracks.android.support.Preferences.Import(keyResId = org.owntracks.android.R.string.preferenceKeyLocatorInterval)
    public final void setLocatorInterval(int anInt) {
    }
    
    @org.owntracks.android.support.Preferences.Export(keyResId = org.owntracks.android.R.string.preferenceKeyMoveModeLocatorInterval, exportModeMqtt = true, exportModeHttp = true)
    public final int getMoveModeLocatorInterval() {
        return 0;
    }
    
    @org.owntracks.android.support.Preferences.Import(keyResId = org.owntracks.android.R.string.preferenceKeyMoveModeLocatorInterval)
    public final void setMoveModeLocatorInterval(int moveModeLocatorInterval) {
    }
    
    @org.owntracks.android.support.Preferences.Export(keyResId = org.owntracks.android.R.string.preferenceKeyPing, exportModeMqtt = true, exportModeHttp = true)
    public final int getPing() {
        return 0;
    }
    
    @org.owntracks.android.support.Preferences.Import(keyResId = org.owntracks.android.R.string.preferenceKeyPing)
    public final void setPing(int anInt) {
    }
    
    @org.jetbrains.annotations.NotNull()
    @org.owntracks.android.support.Preferences.Export(keyResId = org.owntracks.android.R.string.preferenceKeyUsername, exportModeMqtt = true, exportModeHttp = true)
    public final java.lang.String getUsername() {
        return null;
    }
    
    @org.owntracks.android.support.Preferences.Import(keyResId = org.owntracks.android.R.string.preferenceKeyUsername)
    public final void setUsername(@org.jetbrains.annotations.NotNull()
    java.lang.String value) {
    }
    
    @org.jetbrains.annotations.NotNull()
    @org.owntracks.android.support.Preferences.Export(keyResId = org.owntracks.android.R.string.preferenceKeyDeviceId, exportModeMqtt = true, exportModeHttp = true)
    public final java.lang.String getDeviceId() {
        return null;
    }
    
    @org.owntracks.android.support.Preferences.Import(keyResId = org.owntracks.android.R.string.preferenceKeyDeviceId)
    public final void setDeviceId(@org.jetbrains.annotations.NotNull()
    java.lang.String deviceId) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getDeviceId(boolean fallbackToDefault) {
        return null;
    }
    
    @org.owntracks.android.support.Preferences.Export(keyResId = org.owntracks.android.R.string.preferenceKeyIgnoreStaleLocations, exportModeMqtt = true, exportModeHttp = true)
    public final double getIgnoreStaleLocations() {
        return 0.0;
    }
    
    @org.owntracks.android.support.Preferences.Import(keyResId = org.owntracks.android.R.string.preferenceKeyIgnoreStaleLocations)
    public final void setIgnoreStaleLocations(double days) {
    }
    
    @org.owntracks.android.support.Preferences.Export(keyResId = org.owntracks.android.R.string.preferenceKeyIgnoreInaccurateLocations, exportModeMqtt = true, exportModeHttp = true)
    public final int getIgnoreInaccurateLocations() {
        return 0;
    }
    
    @org.owntracks.android.support.Preferences.Import(keyResId = org.owntracks.android.R.string.preferenceKeyIgnoreInaccurateLocations)
    public final void setIgnoreInaccurateLocations(int meters) {
    }
    
    @org.jetbrains.annotations.NotNull()
    @org.owntracks.android.support.Preferences.Export(keyResId = org.owntracks.android.R.string.preferenceKeyClientId, exportModeMqtt = true)
    public final java.lang.String getClientId() {
        return null;
    }
    
    @org.owntracks.android.support.Preferences.Import(keyResId = org.owntracks.android.R.string.preferenceKeyClientId)
    public final void setClientId(@org.jetbrains.annotations.NotNull()
    java.lang.String clientId) {
    }
    
    @org.jetbrains.annotations.NotNull()
    @org.owntracks.android.support.Preferences.Export(keyResId = org.owntracks.android.R.string.preferenceKeyPubTopicBase, exportModeMqtt = true)
    public final java.lang.String getPubTopicBaseFormatString() {
        return null;
    }
    
    @org.owntracks.android.support.Preferences.Import(keyResId = org.owntracks.android.R.string.preferenceKeyPubTopicBase)
    public final void setPubTopicBaseFormatString(@org.jetbrains.annotations.NotNull()
    java.lang.String deviceTopic) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getPubTopicBase() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @org.owntracks.android.support.Preferences.Export(keyResId = org.owntracks.android.R.string.preferenceKeySubTopic, exportModeMqtt = true)
    public final java.lang.String getSubTopic() {
        return null;
    }
    
    @org.owntracks.android.support.Preferences.Import(keyResId = org.owntracks.android.R.string.preferenceKeySubTopic)
    public final void setSubTopic(@org.jetbrains.annotations.NotNull()
    java.lang.String string) {
    }
    
    @org.owntracks.android.support.Preferences.Export(keyResId = org.owntracks.android.R.string.preferenceKeySub, exportModeMqtt = true)
    public final boolean getSub() {
        return false;
    }
    
    @org.owntracks.android.support.Preferences.Import(keyResId = org.owntracks.android.R.string.preferenceKeySub)
    public final void setSub(boolean sub) {
    }
    
    @org.jetbrains.annotations.NotNull()
    @org.owntracks.android.support.Preferences.Export(keyResId = org.owntracks.android.R.string.preferenceKeyTrackerId, exportModeMqtt = true, exportModeHttp = true)
    public final java.lang.String getTrackerId() {
        return null;
    }
    
    @org.owntracks.android.support.Preferences.Import(keyResId = org.owntracks.android.R.string.preferenceKeyTrackerId)
    public final void setTrackerId(@org.jetbrains.annotations.NotNull()
    java.lang.String trackerId) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getTrackerId(boolean fallback) {
        return null;
    }
    
    private final java.lang.String getTrackerIdDefault() {
        return null;
    }
    
    @org.owntracks.android.support.Preferences.Export(keyResId = org.owntracks.android.R.string.preferenceKeyPort, exportModeMqtt = true)
    public final int getPort() {
        return 0;
    }
    
    @org.owntracks.android.support.Preferences.Import(keyResId = org.owntracks.android.R.string.preferenceKeyPort)
    public final void setPort(int port) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getPortWithHintSupport() {
        return null;
    }
    
    public final void setPortDefault() {
    }
    
    @org.owntracks.android.support.Preferences.Export(keyResId = org.owntracks.android.R.string.preferenceKeyMqttProtocolLevel, exportModeMqtt = true)
    public final int getMqttProtocolLevel() {
        return 0;
    }
    
    @org.owntracks.android.support.Preferences.Import(keyResId = org.owntracks.android.R.string.preferenceKeyMqttProtocolLevel)
    public final void setMqttProtocolLevel(int mqttProtocolLevel) {
    }
    
    @org.owntracks.android.support.Preferences.Export(keyResId = org.owntracks.android.R.string.preferenceKeyKeepalive, exportModeMqtt = true)
    public final int getKeepalive() {
        return 0;
    }
    
    @org.owntracks.android.support.Preferences.Import(keyResId = org.owntracks.android.R.string.preferenceKeyKeepalive)
    public final void setKeepalive(int value) {
    }
    
    public final boolean keepAliveInRange(int i) {
        return false;
    }
    
    public final long getMinimumKeepalive() {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getKeepaliveWithHintSupport() {
        return null;
    }
    
    public final void setKeepaliveDefault() {
    }
    
    @org.owntracks.android.support.Preferences.Export(keyResId = org.owntracks.android.R.string.preferenceKeyNotificationEvents, exportModeMqtt = true, exportModeHttp = true)
    public final boolean getNotificationEvents() {
        return false;
    }
    
    @org.owntracks.android.support.Preferences.Import(keyResId = org.owntracks.android.R.string.preferenceKeyNotificationEvents)
    public final void setNotificationEvents(boolean notificationEvents) {
    }
    
    @org.owntracks.android.support.Preferences.Export(keyResId = org.owntracks.android.R.string.preferenceKeyLocatorDisplacement, exportModeMqtt = true, exportModeHttp = true)
    public final int getLocatorDisplacement() {
        return 0;
    }
    
    @org.owntracks.android.support.Preferences.Import(keyResId = org.owntracks.android.R.string.preferenceKeyLocatorDisplacement)
    public final void setLocatorDisplacement(int anInt) {
    }
    
    @org.owntracks.android.support.Preferences.Export(keyResId = org.owntracks.android.R.string.preferenceKeyLocatorPriority, exportModeMqtt = true, exportModeHttp = true)
    public final int getLocatorPriority() {
        return 0;
    }
    
    @org.owntracks.android.support.Preferences.Import(keyResId = org.owntracks.android.R.string.preferenceKeyLocatorPriority)
    public final void setLocatorPriority(int anInt) {
    }
    
    @org.jetbrains.annotations.NotNull()
    @org.owntracks.android.support.Preferences.Export(keyResId = org.owntracks.android.R.string.preferenceKeyTLSCaCrt, exportModeMqtt = true)
    public final java.lang.String getTlsCaCrt() {
        return null;
    }
    
    @org.owntracks.android.support.Preferences.Import(keyResId = org.owntracks.android.R.string.preferenceKeyTLSCaCrt)
    public final void setTlsCaCrt(@org.jetbrains.annotations.NotNull()
    java.lang.String name) {
    }
    
    @org.jetbrains.annotations.NotNull()
    @org.owntracks.android.support.Preferences.Export(keyResId = org.owntracks.android.R.string.preferenceKeyHost, exportModeMqtt = true)
    public final java.lang.String getHost() {
        return null;
    }
    
    @org.owntracks.android.support.Preferences.Import(keyResId = org.owntracks.android.R.string.preferenceKeyHost)
    public final void setHost(@org.jetbrains.annotations.NotNull()
    java.lang.String value) {
    }
    
    @org.jetbrains.annotations.NotNull()
    @org.owntracks.android.support.Preferences.Export(keyResId = org.owntracks.android.R.string.preferenceKeyPassword, exportModeMqtt = true, exportModeHttp = true)
    public final java.lang.String getPassword() {
        return null;
    }
    
    @org.owntracks.android.support.Preferences.Import(keyResId = org.owntracks.android.R.string.preferenceKeyPassword)
    public final void setPassword(@org.jetbrains.annotations.NotNull()
    java.lang.String password) {
    }
    
    @org.owntracks.android.support.Preferences.Export(keyResId = org.owntracks.android.R.string.preferenceKeyTLS, exportModeMqtt = true)
    public final boolean getTls() {
        return false;
    }
    
    @org.owntracks.android.support.Preferences.Import(keyResId = org.owntracks.android.R.string.preferenceKeyTLS)
    public final void setTls(boolean tlsSpecifier) {
    }
    
    @org.owntracks.android.support.Preferences.Export(keyResId = org.owntracks.android.R.string.preferenceKeyWS, exportModeMqtt = true)
    public final boolean getWs() {
        return false;
    }
    
    @org.owntracks.android.support.Preferences.Import(keyResId = org.owntracks.android.R.string.preferenceKeyWS)
    public final void setWs(boolean wsEnable) {
    }
    
    @org.jetbrains.annotations.NotNull()
    @org.owntracks.android.support.Preferences.Export(keyResId = org.owntracks.android.R.string.preferenceKeyTLSClientCrt, exportModeMqtt = true)
    public final java.lang.String getTlsClientCrt() {
        return null;
    }
    
    @org.owntracks.android.support.Preferences.Import(keyResId = org.owntracks.android.R.string.preferenceKeyTLSClientCrt)
    public final void setTlsClientCrt(@org.jetbrains.annotations.NotNull()
    java.lang.String name) {
    }
    
    @org.owntracks.android.support.Preferences.Export(keyResId = org.owntracks.android.R.string.preferenceKeyNotificationHigherPriority, exportModeMqtt = true, exportModeHttp = true)
    public final boolean getNotificationHigherPriority() {
        return false;
    }
    
    @org.owntracks.android.support.Preferences.Import(keyResId = org.owntracks.android.R.string.preferenceKeyNotificationHigherPriority)
    public final void setNotificationHigherPriority(boolean newValue) {
    }
    
    @org.owntracks.android.support.Preferences.Export(keyResId = org.owntracks.android.R.string.preferenceKeyNotificationLocation, exportModeMqtt = true, exportModeHttp = true)
    public final boolean getNotificationLocation() {
        return false;
    }
    
    @org.owntracks.android.support.Preferences.Import(keyResId = org.owntracks.android.R.string.preferenceKeyNotificationLocation)
    public final void setNotificationLocation(boolean newValue) {
    }
    
    @org.owntracks.android.support.Preferences.Export(keyResId = org.owntracks.android.R.string.preferenceKeyPubQos, exportModeMqtt = true)
    public final int getPubQos() {
        return 0;
    }
    
    @org.owntracks.android.support.Preferences.Import(keyResId = org.owntracks.android.R.string.preferenceKeyPubQos)
    public final void setPubQos(int anInt) {
    }
    
    @org.owntracks.android.support.Preferences.Export(keyResId = org.owntracks.android.R.string.preferenceKeyPubRetain, exportModeMqtt = true)
    public final boolean getPubRetain() {
        return false;
    }
    
    @org.owntracks.android.support.Preferences.Import(keyResId = org.owntracks.android.R.string.preferenceKeyPubRetain)
    public final void setPubRetain(boolean newValue) {
    }
    
    @org.owntracks.android.support.Preferences.Export(keyResId = org.owntracks.android.R.string.preferenceKeySubQos, exportModeMqtt = true)
    public final int getSubQos() {
        return 0;
    }
    
    @org.owntracks.android.support.Preferences.Import(keyResId = org.owntracks.android.R.string.preferenceKeySubQos)
    public final void setSubQos(int anInt) {
    }
    
    @org.owntracks.android.support.Preferences.Export(keyResId = org.owntracks.android.R.string.preferenceKeyAutostartOnBoot, exportModeMqtt = true, exportModeHttp = true)
    public final boolean getAutostartOnBoot() {
        return false;
    }
    
    @org.owntracks.android.support.Preferences.Import(keyResId = org.owntracks.android.R.string.preferenceKeyAutostartOnBoot)
    public final void setAutostartOnBoot(boolean newValue) {
    }
    
    @org.owntracks.android.support.Preferences.Export(keyResId = org.owntracks.android.R.string.preferenceKeyInfo, exportModeMqtt = true)
    public final boolean getInfo() {
        return false;
    }
    
    @org.owntracks.android.support.Preferences.Import(keyResId = org.owntracks.android.R.string.preferenceKeyInfo)
    public final void setInfo(boolean info) {
    }
    
    @org.jetbrains.annotations.NotNull()
    @org.owntracks.android.support.Preferences.Export(keyResId = org.owntracks.android.R.string.preferenceKeyTLSClientCrtPassword, exportModeMqtt = true)
    public final java.lang.String getTlsClientCrtPassword() {
        return null;
    }
    
    @org.owntracks.android.support.Preferences.Import(keyResId = org.owntracks.android.R.string.preferenceKeyTLSClientCrtPassword)
    public final void setTlsClientCrtPassword(@org.jetbrains.annotations.NotNull()
    java.lang.String password) {
    }
    
    @org.jetbrains.annotations.NotNull()
    @org.owntracks.android.support.Preferences.Export(keyResId = org.owntracks.android.R.string.preferenceKeyURL, exportModeHttp = true)
    public final java.lang.String getUrl() {
        return null;
    }
    
    @org.owntracks.android.support.Preferences.Import(keyResId = org.owntracks.android.R.string.preferenceKeyURL)
    public final void setUrl(@org.jetbrains.annotations.NotNull()
    java.lang.String url) {
    }
    
    @org.owntracks.android.support.Preferences.Export(keyResId = org.owntracks.android.R.string.preferenceKeyFusedRegionDetection, exportModeMqtt = true, exportModeHttp = true)
    public final boolean getFusedRegionDetection() {
        return false;
    }
    
    @org.owntracks.android.support.Preferences.Import(keyResId = org.owntracks.android.R.string.preferenceKeyFusedRegionDetection)
    public final void setFusedRegionDetection(boolean newValue) {
    }
    
    @org.owntracks.android.support.Preferences.Export(keyResId = org.owntracks.android.R.string.preferenceKeyDebugLog, exportModeMqtt = true, exportModeHttp = true)
    public final boolean getDebugLog() {
        return false;
    }
    
    @org.owntracks.android.support.Preferences.Import(keyResId = org.owntracks.android.R.string.preferenceKeyDebugLog)
    public final void setDebugLog(boolean debug) {
    }
    
    @org.owntracks.android.support.Preferences.Export(keyResId = org.owntracks.android.R.string.preferenceKeyRemoteConfiguration, exportModeMqtt = true, exportModeHttp = true)
    public final boolean getRemoteConfiguration() {
        return false;
    }
    
    @org.owntracks.android.support.Preferences.Import(keyResId = org.owntracks.android.R.string.preferenceKeyRemoteConfiguration)
    public final void setRemoteConfiguration(boolean newValue) {
    }
    
    @org.owntracks.android.support.Preferences.Import(keyResId = org.owntracks.android.R.string.preferenceKeyGeocodeEnabled)
    public final void setGeocodeEnabled(boolean newValue) {
    }
    
    @org.jetbrains.annotations.NotNull()
    @org.owntracks.android.support.Preferences.Export(keyResId = org.owntracks.android.R.string.preferenceKeyReverseGeocodeProvider, exportModeMqtt = true, exportModeHttp = true)
    public final java.lang.String getReverseGeocodeProvider() {
        return null;
    }
    
    @org.owntracks.android.support.Preferences.Import(keyResId = org.owntracks.android.R.string.preferenceKeyReverseGeocodeProvider)
    public final void setReverseGeocodeProvider(@org.jetbrains.annotations.NotNull()
    java.lang.String newValue) {
    }
    
    @org.jetbrains.annotations.NotNull()
    @org.owntracks.android.support.Preferences.Export(keyResId = org.owntracks.android.R.string.preferenceKeyExperimentalFeatures, exportModeMqtt = true, exportModeHttp = true)
    public final java.util.Set<java.lang.String> getExperimentalFeatures() {
        return null;
    }
    
    @org.owntracks.android.support.Preferences.Import(keyResId = org.owntracks.android.R.string.preferenceKeyExperimentalFeatures)
    public final void setExperimentalFeatures(@org.jetbrains.annotations.NotNull()
    java.util.Set<java.lang.String> value) {
    }
    
    private final java.lang.String getDeviceIdDefault() {
        return null;
    }
    
    private final java.lang.String getClientIdDefault() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getPubTopicLocations() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getPubTopicWaypoints() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getPubTopicWaypointsPart() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getPubTopicEvents() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getPubTopicEventsPart() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getPubTopicInfoPart() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getPubTopicCommands() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getPubTopicCommandsPart() {
        return null;
    }
    
    public final int getPubQosEvents() {
        return 0;
    }
    
    public final boolean getPubRetainEvents() {
        return false;
    }
    
    public final int getPubQosWaypoints() {
        return 0;
    }
    
    public final boolean getPubRetainWaypoints() {
        return false;
    }
    
    public final int getPubQosLocations() {
        return 0;
    }
    
    public final boolean getPubRetainLocations() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getEncryptionKey() {
        return null;
    }
    
    public final boolean isSetupCompleted() {
        return false;
    }
    
    public final void setSetupCompleted() {
    }
    
    public final boolean isObjectboxMigrated() {
        return false;
    }
    
    public final void setObjectBoxMigrated() {
    }
    
    private final boolean getBooleanOrDefault(int resKeyId, int defId) {
        return false;
    }
    
    private final boolean getBooleanResource(int resId) {
        return false;
    }
    
    private final int getIntOrDefault(int resKeyId, int defId) {
        return 0;
    }
    
    private final int getIntResource(int resId) {
        return 0;
    }
    
    private final java.lang.String getIntWithHintSupport(int resKeyId) {
        return null;
    }
    
    private final java.lang.String getStringOrDefault(int resKeyId, int defId) {
        return null;
    }
    
    private final java.lang.String getStringResource(int resId) {
        return null;
    }
    
    private final void setString(int resKeyId, java.lang.String value) {
    }
    
    private final void setInt(int resKeyId, int value) {
    }
    
    private final void setBoolean(int resKeyId, boolean value) {
    }
    
    private final void setStringSet(int resKeyId, java.util.Set<java.lang.String> value) {
    }
    
    private final java.util.Set<java.lang.String> getStringSet(int resKeyId) {
        return null;
    }
    
    private final void clearKey(java.lang.String key) {
    }
    
    private final void clearKey(int resKeyId) {
    }
    
    public final boolean isExperimentalFeatureEnabled(@org.jetbrains.annotations.NotNull()
    java.lang.String feature) {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getPreferenceKey(int res) {
        return null;
    }
    
    @javax.inject.Inject()
    public Preferences(@org.jetbrains.annotations.NotNull()
    @org.owntracks.android.injection.qualifier.AppContext()
    android.content.Context c, @org.jetbrains.annotations.Nullable()
    org.greenrobot.eventbus.EventBus eventBus, @org.jetbrains.annotations.NotNull()
    org.owntracks.android.support.preferences.PreferencesStore preferencesStore) {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u001b\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\u0002\u0018\u00002\u00020\u0001B\u001e\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005R\u000f\u0010\u0006\u001a\u00020\u0005\u00a2\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u000f\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0006\u001a\u0004\b\u0004\u0010\u0007R\u000f\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0006\u001a\u0004\b\u0002\u0010\b\u00a8\u0006\t"}, d2 = {"Lorg/owntracks/android/support/Preferences$Export;", "", "keyResId", "", "exportModeMqtt", "", "exportModeHttp", "()Z", "()I", "app_debug"})
    @java.lang.annotation.Target(value = {java.lang.annotation.ElementType.METHOD})
    @java.lang.annotation.Retention(value = java.lang.annotation.RetentionPolicy.RUNTIME)
    @kotlin.annotation.Target(allowedTargets = {kotlin.annotation.AnnotationTarget.FUNCTION, kotlin.annotation.AnnotationTarget.PROPERTY_GETTER, kotlin.annotation.AnnotationTarget.PROPERTY_SETTER})
    @kotlin.annotation.Retention(value = kotlin.annotation.AnnotationRetention.RUNTIME)
    public static abstract @interface Export {
        
        public abstract int keyResId() default 0;
        
        public abstract boolean exportModeMqtt() default false;
        
        public abstract boolean exportModeHttp() default false;
    }
    
    @kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u001b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\u0002\u0018\u00002\u00020\u0001B\n\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003R\u000f\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0006\u001a\u0004\b\u0002\u0010\u0004\u00a8\u0006\u0005"}, d2 = {"Lorg/owntracks/android/support/Preferences$Import;", "", "keyResId", "", "()I", "app_debug"})
    @java.lang.annotation.Target(value = {java.lang.annotation.ElementType.METHOD})
    @java.lang.annotation.Retention(value = java.lang.annotation.RetentionPolicy.RUNTIME)
    @kotlin.annotation.Target(allowedTargets = {kotlin.annotation.AnnotationTarget.FUNCTION, kotlin.annotation.AnnotationTarget.PROPERTY_GETTER, kotlin.annotation.AnnotationTarget.PROPERTY_SETTER})
    @kotlin.annotation.Retention(value = kotlin.annotation.AnnotationRetention.RUNTIME)
    public static abstract @interface Import {
        
        public abstract int keyResId() default 0;
    }
    
    @kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\b\u0006\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u000e\u0010\t\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\f"}, d2 = {"Lorg/owntracks/android/support/Preferences$Companion;", "", "()V", "EXPERIMENTAL_FEATURE_ALLOW_SMALL_KEEPALIVE", "", "REVERSE_GEOCODE_PROVIDERS", "", "getREVERSE_GEOCODE_PROVIDERS", "()Ljava/util/List;", "REVERSE_GEOCODE_PROVIDER_GOOGLE", "REVERSE_GEOCODE_PROVIDER_NONE", "REVERSE_GEOCODE_PROVIDER_OPENCAGE", "app_debug"})
    public static final class Companion {
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.List<java.lang.String> getREVERSE_GEOCODE_PROVIDERS() {
            return null;
        }
        
        private Companion() {
            super();
        }
    }
}