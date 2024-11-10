package org.wordpress.android.ui.domains;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0005\u0003\u0004\u0005\u0006\u0007B\u0007\b\u0004\u00a2\u0006\u0002\u0010\u0002\u0082\u0001\u0005\b\t\n\u000b\f\u00a8\u0006\r"}, d2 = {"Lorg/wordpress/android/ui/domains/DomainRegistrationNavigationAction;", "", "()V", "FinishDomainRegistration", "OpenDomainRegistrationCheckout", "OpenDomainRegistrationDetails", "OpenDomainRegistrationResult", "OpenDomainSuggestions", "Lorg/wordpress/android/ui/domains/DomainRegistrationNavigationAction$OpenDomainSuggestions;", "Lorg/wordpress/android/ui/domains/DomainRegistrationNavigationAction$OpenDomainRegistrationCheckout;", "Lorg/wordpress/android/ui/domains/DomainRegistrationNavigationAction$OpenDomainRegistrationDetails;", "Lorg/wordpress/android/ui/domains/DomainRegistrationNavigationAction$OpenDomainRegistrationResult;", "Lorg/wordpress/android/ui/domains/DomainRegistrationNavigationAction$FinishDomainRegistration;", "org.wordpress.android_wordpressJalapenoDebug"})
public abstract class DomainRegistrationNavigationAction {
    
    private DomainRegistrationNavigationAction() {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lorg/wordpress/android/ui/domains/DomainRegistrationNavigationAction$OpenDomainSuggestions;", "Lorg/wordpress/android/ui/domains/DomainRegistrationNavigationAction;", "()V", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class OpenDomainSuggestions extends org.wordpress.android.ui.domains.DomainRegistrationNavigationAction {
        @org.jetbrains.annotations.NotNull()
        public static final org.wordpress.android.ui.domains.DomainRegistrationNavigationAction.OpenDomainSuggestions INSTANCE = null;
        
        private OpenDomainSuggestions() {
            super();
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\f\u001a\u00020\u0005H\u00c6\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005H\u00c6\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u00d6\u0003J\t\u0010\u0012\u001a\u00020\u0013H\u00d6\u0001J\t\u0010\u0014\u001a\u00020\u0015H\u00d6\u0001R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n\u00a8\u0006\u0016"}, d2 = {"Lorg/wordpress/android/ui/domains/DomainRegistrationNavigationAction$OpenDomainRegistrationCheckout;", "Lorg/wordpress/android/ui/domains/DomainRegistrationNavigationAction;", "site", "Lorg/wordpress/android/fluxc/model/SiteModel;", "details", "Lorg/wordpress/android/ui/domains/DomainProductDetails;", "(Lorg/wordpress/android/fluxc/model/SiteModel;Lorg/wordpress/android/ui/domains/DomainProductDetails;)V", "getDetails", "()Lorg/wordpress/android/ui/domains/DomainProductDetails;", "getSite", "()Lorg/wordpress/android/fluxc/model/SiteModel;", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class OpenDomainRegistrationCheckout extends org.wordpress.android.ui.domains.DomainRegistrationNavigationAction {
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.fluxc.model.SiteModel site = null;
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.ui.domains.DomainProductDetails details = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.domains.DomainRegistrationNavigationAction.OpenDomainRegistrationCheckout copy(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.fluxc.model.SiteModel site, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.domains.DomainProductDetails details) {
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
        
        public OpenDomainRegistrationCheckout(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.fluxc.model.SiteModel site, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.domains.DomainProductDetails details) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.fluxc.model.SiteModel component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.fluxc.model.SiteModel getSite() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.domains.DomainProductDetails component2() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.domains.DomainProductDetails getDetails() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003H\u00c6\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u00d6\u0003J\t\u0010\r\u001a\u00020\u000eH\u00d6\u0001J\t\u0010\u000f\u001a\u00020\u0010H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0011"}, d2 = {"Lorg/wordpress/android/ui/domains/DomainRegistrationNavigationAction$OpenDomainRegistrationDetails;", "Lorg/wordpress/android/ui/domains/DomainRegistrationNavigationAction;", "details", "Lorg/wordpress/android/ui/domains/DomainProductDetails;", "(Lorg/wordpress/android/ui/domains/DomainProductDetails;)V", "getDetails", "()Lorg/wordpress/android/ui/domains/DomainProductDetails;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class OpenDomainRegistrationDetails extends org.wordpress.android.ui.domains.DomainRegistrationNavigationAction {
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.ui.domains.DomainProductDetails details = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.domains.DomainRegistrationNavigationAction.OpenDomainRegistrationDetails copy(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.domains.DomainProductDetails details) {
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
        
        public OpenDomainRegistrationDetails(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.domains.DomainProductDetails details) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.domains.DomainProductDetails component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.domains.DomainProductDetails getDetails() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003H\u00c6\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u00d6\u0003J\t\u0010\r\u001a\u00020\u000eH\u00d6\u0001J\t\u0010\u000f\u001a\u00020\u0010H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0011"}, d2 = {"Lorg/wordpress/android/ui/domains/DomainRegistrationNavigationAction$OpenDomainRegistrationResult;", "Lorg/wordpress/android/ui/domains/DomainRegistrationNavigationAction;", "event", "Lorg/wordpress/android/ui/domains/DomainRegistrationCompletedEvent;", "(Lorg/wordpress/android/ui/domains/DomainRegistrationCompletedEvent;)V", "getEvent", "()Lorg/wordpress/android/ui/domains/DomainRegistrationCompletedEvent;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class OpenDomainRegistrationResult extends org.wordpress.android.ui.domains.DomainRegistrationNavigationAction {
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.ui.domains.DomainRegistrationCompletedEvent event = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.domains.DomainRegistrationNavigationAction.OpenDomainRegistrationResult copy(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.domains.DomainRegistrationCompletedEvent event) {
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
        
        public OpenDomainRegistrationResult(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.domains.DomainRegistrationCompletedEvent event) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.domains.DomainRegistrationCompletedEvent component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.domains.DomainRegistrationCompletedEvent getEvent() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003H\u00c6\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u00d6\u0003J\t\u0010\r\u001a\u00020\u000eH\u00d6\u0001J\t\u0010\u000f\u001a\u00020\u0010H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0011"}, d2 = {"Lorg/wordpress/android/ui/domains/DomainRegistrationNavigationAction$FinishDomainRegistration;", "Lorg/wordpress/android/ui/domains/DomainRegistrationNavigationAction;", "event", "Lorg/wordpress/android/ui/domains/DomainRegistrationCompletedEvent;", "(Lorg/wordpress/android/ui/domains/DomainRegistrationCompletedEvent;)V", "getEvent", "()Lorg/wordpress/android/ui/domains/DomainRegistrationCompletedEvent;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class FinishDomainRegistration extends org.wordpress.android.ui.domains.DomainRegistrationNavigationAction {
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.ui.domains.DomainRegistrationCompletedEvent event = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.domains.DomainRegistrationNavigationAction.FinishDomainRegistration copy(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.domains.DomainRegistrationCompletedEvent event) {
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
        
        public FinishDomainRegistration(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.domains.DomainRegistrationCompletedEvent event) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.domains.DomainRegistrationCompletedEvent component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.domains.DomainRegistrationCompletedEvent getEvent() {
            return null;
        }
    }
}