package fr.free.nrw.commons.upload;

import java.lang.System;

/**
 * Decorates an OkHttp request body to count the number of bytes written when writing it. Can
 * decorate any request body, but is most useful for tracking the upload progress of large multipart
 * requests.
 *
 * @author Ashish Kumar
 */
@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001:\u0002$%B%\u0012\u0006\u0010\u0002\u001a\u00020\u0001\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u00a2\u0006\u0002\u0010\bJ\b\u0010\u001d\u001a\u00020\u0006H\u0016J\n\u0010\u001e\u001a\u0004\u0018\u00010\u001fH\u0016J\u0010\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020#H\u0016R \u0010\t\u001a\b\u0018\u00010\nR\u00020\u0000X\u0084\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001a\u0010\u0002\u001a\u00020\u0001X\u0084\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u001a\u0010\u0003\u001a\u00020\u0004X\u0084\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\u001a\u0010\u0007\u001a\u00020\u0006X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u0018\"\u0004\b\u001c\u0010\u001a\u00a8\u0006&"}, d2 = {"Lfr/free/nrw/commons/upload/CountingRequestBody;", "Lokhttp3/RequestBody;", "delegate", "listener", "Lfr/free/nrw/commons/upload/CountingRequestBody$Listener;", "offset", "", "totalContentLength", "(Lokhttp3/RequestBody;Lfr/free/nrw/commons/upload/CountingRequestBody$Listener;JJ)V", "countingSink", "Lfr/free/nrw/commons/upload/CountingRequestBody$CountingSink;", "getCountingSink", "()Lfr/free/nrw/commons/upload/CountingRequestBody$CountingSink;", "setCountingSink", "(Lfr/free/nrw/commons/upload/CountingRequestBody$CountingSink;)V", "getDelegate", "()Lokhttp3/RequestBody;", "setDelegate", "(Lokhttp3/RequestBody;)V", "getListener", "()Lfr/free/nrw/commons/upload/CountingRequestBody$Listener;", "setListener", "(Lfr/free/nrw/commons/upload/CountingRequestBody$Listener;)V", "getOffset", "()J", "setOffset", "(J)V", "getTotalContentLength", "setTotalContentLength", "contentLength", "contentType", "Lokhttp3/MediaType;", "writeTo", "", "sink", "Lokio/BufferedSink;", "CountingSink", "Listener", "app-commons-v4.2.1-main_betaDebug"})
public final class CountingRequestBody extends okhttp3.RequestBody {
    @org.jetbrains.annotations.NotNull
    private okhttp3.RequestBody delegate;
    @org.jetbrains.annotations.NotNull
    private fr.free.nrw.commons.upload.CountingRequestBody.Listener listener;
    private long offset;
    private long totalContentLength;
    @org.jetbrains.annotations.Nullable
    private fr.free.nrw.commons.upload.CountingRequestBody.CountingSink countingSink;
    
    public CountingRequestBody(@org.jetbrains.annotations.NotNull
    okhttp3.RequestBody delegate, @org.jetbrains.annotations.NotNull
    fr.free.nrw.commons.upload.CountingRequestBody.Listener listener, long offset, long totalContentLength) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    protected final okhttp3.RequestBody getDelegate() {
        return null;
    }
    
    protected final void setDelegate(@org.jetbrains.annotations.NotNull
    okhttp3.RequestBody p0) {
    }
    
    @org.jetbrains.annotations.NotNull
    protected final fr.free.nrw.commons.upload.CountingRequestBody.Listener getListener() {
        return null;
    }
    
    protected final void setListener(@org.jetbrains.annotations.NotNull
    fr.free.nrw.commons.upload.CountingRequestBody.Listener p0) {
    }
    
    public final long getOffset() {
        return 0L;
    }
    
    public final void setOffset(long p0) {
    }
    
    public final long getTotalContentLength() {
        return 0L;
    }
    
    public final void setTotalContentLength(long p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    protected final fr.free.nrw.commons.upload.CountingRequestBody.CountingSink getCountingSink() {
        return null;
    }
    
    protected final void setCountingSink(@org.jetbrains.annotations.Nullable
    fr.free.nrw.commons.upload.CountingRequestBody.CountingSink p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    @java.lang.Override
    public okhttp3.MediaType contentType() {
        return null;
    }
    
    @java.lang.Override
    public long contentLength() {
        return 0L;
    }
    
    @kotlin.jvm.Throws(exceptionClasses = {java.io.IOException.class})
    @java.lang.Override
    public void writeTo(@org.jetbrains.annotations.NotNull
    okio.BufferedSink sink) throws java.io.IOException {
    }
    
    @kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0084\u0004\u0018\u00002\u00020\u0001B\u000f\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\u0002\u0010\u0004J\u0018\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0006H\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\f"}, d2 = {"Lfr/free/nrw/commons/upload/CountingRequestBody$CountingSink;", "Lokio/ForwardingSink;", "delegate", "Lokio/Sink;", "(Lfr/free/nrw/commons/upload/CountingRequestBody;Lokio/Sink;)V", "bytesWritten", "", "write", "", "source", "Lokio/Buffer;", "byteCount", "app-commons-v4.2.1-main_betaDebug"})
    public final class CountingSink extends okio.ForwardingSink {
        private long bytesWritten = 0L;
        
        public CountingSink(@org.jetbrains.annotations.Nullable
        okio.Sink delegate) {
            super(null);
        }
        
        @kotlin.jvm.Throws(exceptionClasses = {java.io.IOException.class})
        @java.lang.Override
        public void write(@org.jetbrains.annotations.NotNull
        okio.Buffer source, long byteCount) throws java.io.IOException {
        }
    }
    
    @kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005H&\u00a8\u0006\u0007"}, d2 = {"Lfr/free/nrw/commons/upload/CountingRequestBody$Listener;", "", "onRequestProgress", "", "bytesWritten", "", "contentLength", "app-commons-v4.2.1-main_betaDebug"})
    public static abstract interface Listener {
        
        /**
         * Will be triggered when write progresses
         * @param bytesWritten
         * @param contentLength
         */
        public abstract void onRequestProgress(long bytesWritten, long contentLength);
    }
}