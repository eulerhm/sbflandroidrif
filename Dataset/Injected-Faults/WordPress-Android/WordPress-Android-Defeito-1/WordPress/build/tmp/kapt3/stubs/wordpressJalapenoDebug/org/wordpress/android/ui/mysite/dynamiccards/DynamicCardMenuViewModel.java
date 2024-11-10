package org.wordpress.android.ui.mysite.dynamiccards;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u00002\u00020\u0001:\u0001\u0018B\u0007\b\u0007\u00a2\u0006\u0002\u0010\u0002J\u0006\u0010\u0012\u001a\u00020\u0013J\u0006\u0010\u0014\u001a\u00020\u0013J\u0006\u0010\u0015\u001a\u00020\u0013J\u0016\u0010\u0016\u001a\u00020\u00132\u0006\u0010\u0007\u001a\u00020\u00172\u0006\u0010\t\u001a\u00020\nR\u001a\u0010\u0003\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082.\u00a2\u0006\u0002\n\u0000R\u001a\u0010\t\u001a\u00020\nX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\u000b\"\u0004\b\f\u0010\rR\u001d\u0010\u000e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\u000f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011\u00a8\u0006\u0019"}, d2 = {"Lorg/wordpress/android/ui/mysite/dynamiccards/DynamicCardMenuViewModel;", "Landroidx/lifecycle/ViewModel;", "()V", "_onInteraction", "Landroidx/lifecycle/MutableLiveData;", "Lorg/wordpress/android/viewmodel/Event;", "Lorg/wordpress/android/ui/mysite/dynamiccards/DynamicCardMenuViewModel$DynamicCardMenuInteraction;", "cardType", "Lorg/wordpress/android/fluxc/model/DynamicCardType;", "isPinned", "", "()Z", "setPinned", "(Z)V", "onInteraction", "Landroidx/lifecycle/LiveData;", "getOnInteraction", "()Landroidx/lifecycle/LiveData;", "onHideActionClicked", "", "onPinActionClicked", "onRemoveActionClicked", "start", "", "DynamicCardMenuInteraction", "org.wordpress.android_wordpressJalapenoDebug"})
public final class DynamicCardMenuViewModel extends androidx.lifecycle.ViewModel {
    private final androidx.lifecycle.MutableLiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.ui.mysite.dynamiccards.DynamicCardMenuViewModel.DynamicCardMenuInteraction>> _onInteraction = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.ui.mysite.dynamiccards.DynamicCardMenuViewModel.DynamicCardMenuInteraction>> onInteraction = null;
    private org.wordpress.android.fluxc.model.DynamicCardType cardType;
    private boolean isPinned = false;
    
    @javax.inject.Inject()
    public DynamicCardMenuViewModel() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.ui.mysite.dynamiccards.DynamicCardMenuViewModel.DynamicCardMenuInteraction>> getOnInteraction() {
        return null;
    }
    
    public final boolean isPinned() {
        return false;
    }
    
    public final void setPinned(boolean p0) {
    }
    
    public final void onPinActionClicked() {
    }
    
    public final void onHideActionClicked() {
    }
    
    public final void onRemoveActionClicked() {
    }
    
    public final void start(@org.jetbrains.annotations.NotNull()
    java.lang.String cardType, boolean isPinned) {
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0004\u0007\b\t\nB\u000f\b\u0004\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u0082\u0001\u0004\u000b\f\r\u000e\u00a8\u0006\u000f"}, d2 = {"Lorg/wordpress/android/ui/mysite/dynamiccards/DynamicCardMenuViewModel$DynamicCardMenuInteraction;", "", "cardType", "Lorg/wordpress/android/fluxc/model/DynamicCardType;", "(Lorg/wordpress/android/fluxc/model/DynamicCardType;)V", "getCardType", "()Lorg/wordpress/android/fluxc/model/DynamicCardType;", "Hide", "Pin", "Remove", "Unpin", "Lorg/wordpress/android/ui/mysite/dynamiccards/DynamicCardMenuViewModel$DynamicCardMenuInteraction$Pin;", "Lorg/wordpress/android/ui/mysite/dynamiccards/DynamicCardMenuViewModel$DynamicCardMenuInteraction$Unpin;", "Lorg/wordpress/android/ui/mysite/dynamiccards/DynamicCardMenuViewModel$DynamicCardMenuInteraction$Hide;", "Lorg/wordpress/android/ui/mysite/dynamiccards/DynamicCardMenuViewModel$DynamicCardMenuInteraction$Remove;", "org.wordpress.android_wordpressJalapenoDebug"})
    public static abstract class DynamicCardMenuInteraction {
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.fluxc.model.DynamicCardType cardType = null;
        
        private DynamicCardMenuInteraction(org.wordpress.android.fluxc.model.DynamicCardType cardType) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public org.wordpress.android.fluxc.model.DynamicCardType getCardType() {
            return null;
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003H\u00c6\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u00d6\u0003J\t\u0010\r\u001a\u00020\u000eH\u00d6\u0001J\t\u0010\u000f\u001a\u00020\u0010H\u00d6\u0001R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0011"}, d2 = {"Lorg/wordpress/android/ui/mysite/dynamiccards/DynamicCardMenuViewModel$DynamicCardMenuInteraction$Pin;", "Lorg/wordpress/android/ui/mysite/dynamiccards/DynamicCardMenuViewModel$DynamicCardMenuInteraction;", "cardType", "Lorg/wordpress/android/fluxc/model/DynamicCardType;", "(Lorg/wordpress/android/fluxc/model/DynamicCardType;)V", "getCardType", "()Lorg/wordpress/android/fluxc/model/DynamicCardType;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class Pin extends org.wordpress.android.ui.mysite.dynamiccards.DynamicCardMenuViewModel.DynamicCardMenuInteraction {
            @org.jetbrains.annotations.NotNull()
            private final org.wordpress.android.fluxc.model.DynamicCardType cardType = null;
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.mysite.dynamiccards.DynamicCardMenuViewModel.DynamicCardMenuInteraction.Pin copy(@org.jetbrains.annotations.NotNull()
            org.wordpress.android.fluxc.model.DynamicCardType cardType) {
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
            
            public Pin(@org.jetbrains.annotations.NotNull()
            org.wordpress.android.fluxc.model.DynamicCardType cardType) {
                super(null);
            }
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.fluxc.model.DynamicCardType component1() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            @java.lang.Override()
            public org.wordpress.android.fluxc.model.DynamicCardType getCardType() {
                return null;
            }
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003H\u00c6\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u00d6\u0003J\t\u0010\r\u001a\u00020\u000eH\u00d6\u0001J\t\u0010\u000f\u001a\u00020\u0010H\u00d6\u0001R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0011"}, d2 = {"Lorg/wordpress/android/ui/mysite/dynamiccards/DynamicCardMenuViewModel$DynamicCardMenuInteraction$Unpin;", "Lorg/wordpress/android/ui/mysite/dynamiccards/DynamicCardMenuViewModel$DynamicCardMenuInteraction;", "cardType", "Lorg/wordpress/android/fluxc/model/DynamicCardType;", "(Lorg/wordpress/android/fluxc/model/DynamicCardType;)V", "getCardType", "()Lorg/wordpress/android/fluxc/model/DynamicCardType;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class Unpin extends org.wordpress.android.ui.mysite.dynamiccards.DynamicCardMenuViewModel.DynamicCardMenuInteraction {
            @org.jetbrains.annotations.NotNull()
            private final org.wordpress.android.fluxc.model.DynamicCardType cardType = null;
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.mysite.dynamiccards.DynamicCardMenuViewModel.DynamicCardMenuInteraction.Unpin copy(@org.jetbrains.annotations.NotNull()
            org.wordpress.android.fluxc.model.DynamicCardType cardType) {
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
            
            public Unpin(@org.jetbrains.annotations.NotNull()
            org.wordpress.android.fluxc.model.DynamicCardType cardType) {
                super(null);
            }
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.fluxc.model.DynamicCardType component1() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            @java.lang.Override()
            public org.wordpress.android.fluxc.model.DynamicCardType getCardType() {
                return null;
            }
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003H\u00c6\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u00d6\u0003J\t\u0010\r\u001a\u00020\u000eH\u00d6\u0001J\t\u0010\u000f\u001a\u00020\u0010H\u00d6\u0001R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0011"}, d2 = {"Lorg/wordpress/android/ui/mysite/dynamiccards/DynamicCardMenuViewModel$DynamicCardMenuInteraction$Hide;", "Lorg/wordpress/android/ui/mysite/dynamiccards/DynamicCardMenuViewModel$DynamicCardMenuInteraction;", "cardType", "Lorg/wordpress/android/fluxc/model/DynamicCardType;", "(Lorg/wordpress/android/fluxc/model/DynamicCardType;)V", "getCardType", "()Lorg/wordpress/android/fluxc/model/DynamicCardType;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class Hide extends org.wordpress.android.ui.mysite.dynamiccards.DynamicCardMenuViewModel.DynamicCardMenuInteraction {
            @org.jetbrains.annotations.NotNull()
            private final org.wordpress.android.fluxc.model.DynamicCardType cardType = null;
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.mysite.dynamiccards.DynamicCardMenuViewModel.DynamicCardMenuInteraction.Hide copy(@org.jetbrains.annotations.NotNull()
            org.wordpress.android.fluxc.model.DynamicCardType cardType) {
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
            
            public Hide(@org.jetbrains.annotations.NotNull()
            org.wordpress.android.fluxc.model.DynamicCardType cardType) {
                super(null);
            }
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.fluxc.model.DynamicCardType component1() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            @java.lang.Override()
            public org.wordpress.android.fluxc.model.DynamicCardType getCardType() {
                return null;
            }
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003H\u00c6\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u00d6\u0003J\t\u0010\r\u001a\u00020\u000eH\u00d6\u0001J\t\u0010\u000f\u001a\u00020\u0010H\u00d6\u0001R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0011"}, d2 = {"Lorg/wordpress/android/ui/mysite/dynamiccards/DynamicCardMenuViewModel$DynamicCardMenuInteraction$Remove;", "Lorg/wordpress/android/ui/mysite/dynamiccards/DynamicCardMenuViewModel$DynamicCardMenuInteraction;", "cardType", "Lorg/wordpress/android/fluxc/model/DynamicCardType;", "(Lorg/wordpress/android/fluxc/model/DynamicCardType;)V", "getCardType", "()Lorg/wordpress/android/fluxc/model/DynamicCardType;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class Remove extends org.wordpress.android.ui.mysite.dynamiccards.DynamicCardMenuViewModel.DynamicCardMenuInteraction {
            @org.jetbrains.annotations.NotNull()
            private final org.wordpress.android.fluxc.model.DynamicCardType cardType = null;
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.mysite.dynamiccards.DynamicCardMenuViewModel.DynamicCardMenuInteraction.Remove copy(@org.jetbrains.annotations.NotNull()
            org.wordpress.android.fluxc.model.DynamicCardType cardType) {
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
            
            public Remove(@org.jetbrains.annotations.NotNull()
            org.wordpress.android.fluxc.model.DynamicCardType cardType) {
                super(null);
            }
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.fluxc.model.DynamicCardType component1() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            @java.lang.Override()
            public org.wordpress.android.fluxc.model.DynamicCardType getCardType() {
                return null;
            }
        }
    }
}