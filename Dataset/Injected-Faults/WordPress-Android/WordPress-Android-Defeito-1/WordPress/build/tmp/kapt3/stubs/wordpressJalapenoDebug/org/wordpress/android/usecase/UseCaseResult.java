package org.wordpress.android.usecase;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u0000*\u0004\b\u0000\u0010\u0001*\u0006\b\u0001\u0010\u0002 \u0001*\u0006\b\u0002\u0010\u0003 \u00012\u00020\u0004:\u0003\n\u000b\fB\u000f\b\u0004\u0012\u0006\u0010\u0005\u001a\u00028\u0000\u00a2\u0006\u0002\u0010\u0006R\u0013\u0010\u0005\u001a\u00028\u0000\u00a2\u0006\n\n\u0002\u0010\t\u001a\u0004\b\u0007\u0010\b\u0082\u0001\u0003\r\u000e\u000f\u00a8\u0006\u0010"}, d2 = {"Lorg/wordpress/android/usecase/UseCaseResult;", "USE_CASE_TYPE", "ERROR", "DATA", "", "type", "(Ljava/lang/Object;)V", "getType", "()Ljava/lang/Object;", "Ljava/lang/Object;", "Failure", "Loading", "Success", "Lorg/wordpress/android/usecase/UseCaseResult$Success;", "Lorg/wordpress/android/usecase/UseCaseResult$Failure;", "Lorg/wordpress/android/usecase/UseCaseResult$Loading;", "org.wordpress.android_wordpressJalapenoDebug"})
public abstract class UseCaseResult<USE_CASE_TYPE extends java.lang.Object, ERROR extends java.lang.Object, DATA extends java.lang.Object> {
    private final USE_CASE_TYPE type = null;
    
    private UseCaseResult(USE_CASE_TYPE type) {
        super();
    }
    
    public final USE_CASE_TYPE getType() {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0001\n\u0002\b\u0007\u0018\u0000*\u0004\b\u0003\u0010\u0001*\u0004\b\u0004\u0010\u00022\u0014\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u0002H\u00020\u0003B\u0015\u0012\u0006\u0010\u0005\u001a\u00028\u0003\u0012\u0006\u0010\u0006\u001a\u00028\u0004\u00a2\u0006\u0002\u0010\u0007R\u0013\u0010\u0006\u001a\u00028\u0004\u00a2\u0006\n\n\u0002\u0010\n\u001a\u0004\b\b\u0010\t\u00a8\u0006\u000b"}, d2 = {"Lorg/wordpress/android/usecase/UseCaseResult$Success;", "USE_CASE_TYPE", "DATA", "Lorg/wordpress/android/usecase/UseCaseResult;", "", "type", "data", "(Ljava/lang/Object;Ljava/lang/Object;)V", "getData", "()Ljava/lang/Object;", "Ljava/lang/Object;", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Success<USE_CASE_TYPE extends java.lang.Object, DATA extends java.lang.Object> extends org.wordpress.android.usecase.UseCaseResult {
        private final DATA data = null;
        
        public Success(USE_CASE_TYPE type, DATA data) {
            super(null);
        }
        
        public final DATA getData() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u0000*\u0004\b\u0003\u0010\u0001*\u0004\b\u0004\u0010\u0002*\u0004\b\u0005\u0010\u00032\u0014\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0004B\u001d\u0012\u0006\u0010\u0005\u001a\u00028\u0003\u0012\u0006\u0010\u0006\u001a\u00028\u0004\u0012\u0006\u0010\u0007\u001a\u00028\u0005\u00a2\u0006\u0002\u0010\bR\u0013\u0010\u0007\u001a\u00028\u0005\u00a2\u0006\n\n\u0002\u0010\u000b\u001a\u0004\b\t\u0010\nR\u0013\u0010\u0006\u001a\u00028\u0004\u00a2\u0006\n\n\u0002\u0010\u000b\u001a\u0004\b\f\u0010\n\u00a8\u0006\r"}, d2 = {"Lorg/wordpress/android/usecase/UseCaseResult$Failure;", "USE_CASE_TYPE", "ERROR", "DATA", "Lorg/wordpress/android/usecase/UseCaseResult;", "type", "error", "cachedData", "(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)V", "getCachedData", "()Ljava/lang/Object;", "Ljava/lang/Object;", "getError", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Failure<USE_CASE_TYPE extends java.lang.Object, ERROR extends java.lang.Object, DATA extends java.lang.Object> extends org.wordpress.android.usecase.UseCaseResult<USE_CASE_TYPE, ERROR, DATA> {
        private final ERROR error = null;
        private final DATA cachedData = null;
        
        public Failure(USE_CASE_TYPE type, ERROR error, DATA cachedData) {
            super(null);
        }
        
        public final ERROR getError() {
            return null;
        }
        
        public final DATA getCachedData() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0001\n\u0002\b\u0003\u0018\u0000*\u0004\b\u0003\u0010\u00012\u0014\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u0002B\r\u0012\u0006\u0010\u0004\u001a\u00028\u0003\u00a2\u0006\u0002\u0010\u0005\u00a8\u0006\u0006"}, d2 = {"Lorg/wordpress/android/usecase/UseCaseResult$Loading;", "USE_CASE_TYPE", "Lorg/wordpress/android/usecase/UseCaseResult;", "", "type", "(Ljava/lang/Object;)V", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Loading<USE_CASE_TYPE extends java.lang.Object> extends org.wordpress.android.usecase.UseCaseResult {
        
        public Loading(USE_CASE_TYPE type) {
            super(null);
        }
    }
}