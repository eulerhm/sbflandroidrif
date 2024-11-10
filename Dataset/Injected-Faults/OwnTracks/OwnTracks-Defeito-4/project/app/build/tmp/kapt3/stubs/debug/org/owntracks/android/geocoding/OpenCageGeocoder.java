package org.owntracks.android.geocoding;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.owntracks.android.services.MessageProcessorEndpointHttp;
import timber.log.Timber;
import java.math.BigDecimal;

@kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 \r2\u00020\u0001:\u0003\r\u000e\u000fB\u0019\b\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u001a\u0010\t\u001a\u0004\u0018\u00010\u00032\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000bH\u0014R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0010"}, d2 = {"Lorg/owntracks/android/geocoding/OpenCageGeocoder;", "Lorg/owntracks/android/geocoding/CachingGeocoder;", "apiKey", "", "httpClient", "Lokhttp3/OkHttpClient;", "(Ljava/lang/String;Lokhttp3/OkHttpClient;)V", "jsonMapper", "Lcom/fasterxml/jackson/databind/ObjectMapper;", "doLookup", "latitude", "Ljava/math/BigDecimal;", "longitude", "Companion", "OpenCageResponse", "OpenCageResult", "app_debug"})
public final class OpenCageGeocoder extends org.owntracks.android.geocoding.CachingGeocoder {
    private final com.fasterxml.jackson.databind.ObjectMapper jsonMapper = null;
    private final java.lang.String apiKey = null;
    private final okhttp3.OkHttpClient httpClient = null;
    private static final java.lang.String OPENCAGE_HOST = "api.opencagedata.com";
    @org.jetbrains.annotations.NotNull()
    public static final org.owntracks.android.geocoding.OpenCageGeocoder.Companion Companion = null;
    
    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    protected java.lang.String doLookup(@org.jetbrains.annotations.NotNull()
    java.math.BigDecimal latitude, @org.jetbrains.annotations.NotNull()
    java.math.BigDecimal longitude) {
        return null;
    }
    
    public OpenCageGeocoder(@org.jetbrains.annotations.NotNull()
    java.lang.String apiKey, @org.jetbrains.annotations.NotNull()
    okhttp3.OkHttpClient httpClient) {
        super();
    }
    
    public OpenCageGeocoder(@org.jetbrains.annotations.NotNull()
    java.lang.String apiKey) {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002R\u0013\u0010\u0003\u001a\u0004\u0018\u00010\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0007"}, d2 = {"Lorg/owntracks/android/geocoding/OpenCageGeocoder$OpenCageResult;", "", "()V", "formatted", "", "getFormatted", "()Ljava/lang/String;", "app_debug"})
    public static final class OpenCageResult {
        @org.jetbrains.annotations.Nullable()
        private final java.lang.String formatted = null;
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String getFormatted() {
            return null;
        }
        
        public OpenCageResult() {
            super();
        }
    }
    
    @kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0001\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002R\u0013\u0010\u0003\u001a\u0004\u0018\u00010\u00048F\u00a2\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006R\u0019\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b\u00a8\u0006\f"}, d2 = {"Lorg/owntracks/android/geocoding/OpenCageGeocoder$OpenCageResponse;", "", "()V", "formatted", "", "getFormatted", "()Ljava/lang/String;", "results", "", "Lorg/owntracks/android/geocoding/OpenCageGeocoder$OpenCageResult;", "getResults", "()Ljava/util/List;", "app_debug"})
    @com.fasterxml.jackson.annotation.JsonInclude(value = com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL)
    public static final class OpenCageResponse {
        @org.jetbrains.annotations.Nullable()
        private final java.util.List<org.owntracks.android.geocoding.OpenCageGeocoder.OpenCageResult> results = null;
        
        @org.jetbrains.annotations.Nullable()
        public final java.util.List<org.owntracks.android.geocoding.OpenCageGeocoder.OpenCageResult> getResults() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String getFormatted() {
            return null;
        }
        
        public OpenCageResponse() {
            super();
        }
    }
    
    @kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, d2 = {"Lorg/owntracks/android/geocoding/OpenCageGeocoder$Companion;", "", "()V", "OPENCAGE_HOST", "", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}