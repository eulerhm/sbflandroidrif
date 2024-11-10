package org.wordpress.android.ui.uploads;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0004\u0007\b\t\nB\u000f\b\u0004\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u0082\u0001\u0004\u000b\f\r\u000e\u00a8\u0006\u000f"}, d2 = {"Lorg/wordpress/android/ui/uploads/AutoSavePostIfNotDraftResult;", "", "post", "Lorg/wordpress/android/fluxc/model/PostModel;", "(Lorg/wordpress/android/fluxc/model/PostModel;)V", "getPost", "()Lorg/wordpress/android/fluxc/model/PostModel;", "FetchPostStatusFailed", "PostAutoSaveFailed", "PostAutoSaved", "PostIsDraftInRemote", "Lorg/wordpress/android/ui/uploads/AutoSavePostIfNotDraftResult$FetchPostStatusFailed;", "Lorg/wordpress/android/ui/uploads/AutoSavePostIfNotDraftResult$PostIsDraftInRemote;", "Lorg/wordpress/android/ui/uploads/AutoSavePostIfNotDraftResult$PostAutoSaved;", "Lorg/wordpress/android/ui/uploads/AutoSavePostIfNotDraftResult$PostAutoSaveFailed;", "org.wordpress.android_wordpressJalapenoDebug"})
public abstract class AutoSavePostIfNotDraftResult {
    @org.jetbrains.annotations.NotNull()
    private final org.wordpress.android.fluxc.model.PostModel post = null;
    
    private AutoSavePostIfNotDraftResult(org.wordpress.android.fluxc.model.PostModel post) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public org.wordpress.android.fluxc.model.PostModel getPost() {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\f\u001a\u00020\u0005H\u00c6\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005H\u00c6\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u00d6\u0003J\t\u0010\u0012\u001a\u00020\u0013H\u00d6\u0001J\t\u0010\u0014\u001a\u00020\u0015H\u00d6\u0001R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n\u00a8\u0006\u0016"}, d2 = {"Lorg/wordpress/android/ui/uploads/AutoSavePostIfNotDraftResult$FetchPostStatusFailed;", "Lorg/wordpress/android/ui/uploads/AutoSavePostIfNotDraftResult;", "post", "Lorg/wordpress/android/fluxc/model/PostModel;", "error", "Lorg/wordpress/android/fluxc/store/PostStore$PostError;", "(Lorg/wordpress/android/fluxc/model/PostModel;Lorg/wordpress/android/fluxc/store/PostStore$PostError;)V", "getError", "()Lorg/wordpress/android/fluxc/store/PostStore$PostError;", "getPost", "()Lorg/wordpress/android/fluxc/model/PostModel;", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class FetchPostStatusFailed extends org.wordpress.android.ui.uploads.AutoSavePostIfNotDraftResult {
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.fluxc.model.PostModel post = null;
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.fluxc.store.PostStore.PostError error = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.uploads.AutoSavePostIfNotDraftResult.FetchPostStatusFailed copy(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.fluxc.model.PostModel post, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.fluxc.store.PostStore.PostError error) {
            return null;
        }
        
        @java.lang.Override()
        public boolean equals(@org.jetbrains.annotations.Nullable()
        java.lang.Object other) {
            return false;
        }
        
        @java.lang.Override()
        public int hashCode() {
            return 0;
        }
        
        @org.jetbrains.annotations.NotNull()
        @java.lang.Override()
        public java.lang.String toString() {
            return null;
        }
        
        public FetchPostStatusFailed(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.fluxc.model.PostModel post, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.fluxc.store.PostStore.PostError error) {
            super(null);
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.fluxc.model.PostModel component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        @java.lang.Override()
        public org.wordpress.android.fluxc.model.PostModel getPost() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.fluxc.store.PostStore.PostError component2() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.fluxc.store.PostStore.PostError getError() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003H\u00c6\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u00d6\u0003J\t\u0010\r\u001a\u00020\u000eH\u00d6\u0001J\t\u0010\u000f\u001a\u00020\u0010H\u00d6\u0001R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0011"}, d2 = {"Lorg/wordpress/android/ui/uploads/AutoSavePostIfNotDraftResult$PostIsDraftInRemote;", "Lorg/wordpress/android/ui/uploads/AutoSavePostIfNotDraftResult;", "post", "Lorg/wordpress/android/fluxc/model/PostModel;", "(Lorg/wordpress/android/fluxc/model/PostModel;)V", "getPost", "()Lorg/wordpress/android/fluxc/model/PostModel;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class PostIsDraftInRemote extends org.wordpress.android.ui.uploads.AutoSavePostIfNotDraftResult {
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.fluxc.model.PostModel post = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.uploads.AutoSavePostIfNotDraftResult.PostIsDraftInRemote copy(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.fluxc.model.PostModel post) {
            return null;
        }
        
        @java.lang.Override()
        public boolean equals(@org.jetbrains.annotations.Nullable()
        java.lang.Object other) {
            return false;
        }
        
        @java.lang.Override()
        public int hashCode() {
            return 0;
        }
        
        @org.jetbrains.annotations.NotNull()
        @java.lang.Override()
        public java.lang.String toString() {
            return null;
        }
        
        public PostIsDraftInRemote(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.fluxc.model.PostModel post) {
            super(null);
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.fluxc.model.PostModel component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        @java.lang.Override()
        public org.wordpress.android.fluxc.model.PostModel getPost() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003H\u00c6\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u00d6\u0003J\t\u0010\r\u001a\u00020\u000eH\u00d6\u0001J\t\u0010\u000f\u001a\u00020\u0010H\u00d6\u0001R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0011"}, d2 = {"Lorg/wordpress/android/ui/uploads/AutoSavePostIfNotDraftResult$PostAutoSaved;", "Lorg/wordpress/android/ui/uploads/AutoSavePostIfNotDraftResult;", "post", "Lorg/wordpress/android/fluxc/model/PostModel;", "(Lorg/wordpress/android/fluxc/model/PostModel;)V", "getPost", "()Lorg/wordpress/android/fluxc/model/PostModel;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class PostAutoSaved extends org.wordpress.android.ui.uploads.AutoSavePostIfNotDraftResult {
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.fluxc.model.PostModel post = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.uploads.AutoSavePostIfNotDraftResult.PostAutoSaved copy(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.fluxc.model.PostModel post) {
            return null;
        }
        
        @java.lang.Override()
        public boolean equals(@org.jetbrains.annotations.Nullable()
        java.lang.Object other) {
            return false;
        }
        
        @java.lang.Override()
        public int hashCode() {
            return 0;
        }
        
        @org.jetbrains.annotations.NotNull()
        @java.lang.Override()
        public java.lang.String toString() {
            return null;
        }
        
        public PostAutoSaved(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.fluxc.model.PostModel post) {
            super(null);
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.fluxc.model.PostModel component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        @java.lang.Override()
        public org.wordpress.android.fluxc.model.PostModel getPost() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\f\u001a\u00020\u0005H\u00c6\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005H\u00c6\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u00d6\u0003J\t\u0010\u0012\u001a\u00020\u0013H\u00d6\u0001J\t\u0010\u0014\u001a\u00020\u0015H\u00d6\u0001R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n\u00a8\u0006\u0016"}, d2 = {"Lorg/wordpress/android/ui/uploads/AutoSavePostIfNotDraftResult$PostAutoSaveFailed;", "Lorg/wordpress/android/ui/uploads/AutoSavePostIfNotDraftResult;", "post", "Lorg/wordpress/android/fluxc/model/PostModel;", "error", "Lorg/wordpress/android/fluxc/store/PostStore$PostError;", "(Lorg/wordpress/android/fluxc/model/PostModel;Lorg/wordpress/android/fluxc/store/PostStore$PostError;)V", "getError", "()Lorg/wordpress/android/fluxc/store/PostStore$PostError;", "getPost", "()Lorg/wordpress/android/fluxc/model/PostModel;", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class PostAutoSaveFailed extends org.wordpress.android.ui.uploads.AutoSavePostIfNotDraftResult {
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.fluxc.model.PostModel post = null;
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.fluxc.store.PostStore.PostError error = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.uploads.AutoSavePostIfNotDraftResult.PostAutoSaveFailed copy(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.fluxc.model.PostModel post, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.fluxc.store.PostStore.PostError error) {
            return null;
        }
        
        @java.lang.Override()
        public boolean equals(@org.jetbrains.annotations.Nullable()
        java.lang.Object other) {
            return false;
        }
        
        @java.lang.Override()
        public int hashCode() {
            return 0;
        }
        
        @org.jetbrains.annotations.NotNull()
        @java.lang.Override()
        public java.lang.String toString() {
            return null;
        }
        
        public PostAutoSaveFailed(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.fluxc.model.PostModel post, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.fluxc.store.PostStore.PostError error) {
            super(null);
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.fluxc.model.PostModel component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        @java.lang.Override()
        public org.wordpress.android.fluxc.model.PostModel getPost() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.fluxc.store.PostStore.PostError component2() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.fluxc.store.PostStore.PostError getError() {
            return null;
        }
    }
}