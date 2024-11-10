package fr.free.nrw.commons.contributions;

import java.lang.System;

/**
 * Dialog fragment for displaying instructions for editing wikipedia
 */
@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \u00162\u00020\u0001:\u0002\u0015\u0016B\u0005\u00a2\u0006\u0002\u0010\u0002J$\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0016J\u001a\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0016R\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b\u00a8\u0006\u0017"}, d2 = {"Lfr/free/nrw/commons/contributions/WikipediaInstructionsDialogFragment;", "Landroidx/fragment/app/DialogFragment;", "()V", "callback", "Lfr/free/nrw/commons/contributions/WikipediaInstructionsDialogFragment$Callback;", "getCallback", "()Lfr/free/nrw/commons/contributions/WikipediaInstructionsDialogFragment$Callback;", "setCallback", "(Lfr/free/nrw/commons/contributions/WikipediaInstructionsDialogFragment$Callback;)V", "onCreateView", "Landroid/widget/ScrollView;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onViewCreated", "", "view", "Landroid/view/View;", "Callback", "Companion", "app-commons-v4.2.1-main_betaDebug"})
public final class WikipediaInstructionsDialogFragment extends androidx.fragment.app.DialogFragment {
    @org.jetbrains.annotations.Nullable
    private fr.free.nrw.commons.contributions.WikipediaInstructionsDialogFragment.Callback callback;
    @org.jetbrains.annotations.NotNull
    public static final fr.free.nrw.commons.contributions.WikipediaInstructionsDialogFragment.Companion Companion = null;
    @org.jetbrains.annotations.NotNull
    public static final java.lang.String ARG_CONTRIBUTION = "contribution";
    private java.util.HashMap _$_findViewCache;
    
    public WikipediaInstructionsDialogFragment() {
        super();
    }
    
    @org.jetbrains.annotations.Nullable
    public final fr.free.nrw.commons.contributions.WikipediaInstructionsDialogFragment.Callback getCallback() {
        return null;
    }
    
    public final void setCallback(@org.jetbrains.annotations.Nullable
    fr.free.nrw.commons.contributions.WikipediaInstructionsDialogFragment.Callback p0) {
    }
    
    @org.jetbrains.annotations.NotNull
    @java.lang.Override
    public android.widget.ScrollView onCreateView(@org.jetbrains.annotations.NotNull
    android.view.LayoutInflater inflater, @org.jetbrains.annotations.Nullable
    android.view.ViewGroup container, @org.jetbrains.annotations.Nullable
    android.os.Bundle savedInstanceState) {
        return null;
    }
    
    @java.lang.Override
    public void onViewCreated(@org.jetbrains.annotations.NotNull
    android.view.View view, @org.jetbrains.annotations.Nullable
    android.os.Bundle savedInstanceState) {
    }
    
    @org.jetbrains.annotations.NotNull
    @kotlin.jvm.JvmStatic
    public static final fr.free.nrw.commons.contributions.WikipediaInstructionsDialogFragment newInstance(@org.jetbrains.annotations.NotNull
    fr.free.nrw.commons.contributions.Contribution contribution) {
        return null;
    }
    
    /**
     * Callback for handling confirm button clicked
     */
    @kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\bf\u0018\u00002\u00020\u0001J\u001a\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&\u00a8\u0006\b"}, d2 = {"Lfr/free/nrw/commons/contributions/WikipediaInstructionsDialogFragment$Callback;", "", "onConfirmClicked", "", "contribution", "Lfr/free/nrw/commons/contributions/Contribution;", "copyWikicode", "", "app-commons-v4.2.1-main_betaDebug"})
    public static abstract interface Callback {
        
        public abstract void onConfirmClicked(@org.jetbrains.annotations.Nullable
        fr.free.nrw.commons.contributions.Contribution contribution, boolean copyWikicode);
    }
    
    @kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\t"}, d2 = {"Lfr/free/nrw/commons/contributions/WikipediaInstructionsDialogFragment$Companion;", "", "()V", "ARG_CONTRIBUTION", "", "newInstance", "Lfr/free/nrw/commons/contributions/WikipediaInstructionsDialogFragment;", "contribution", "Lfr/free/nrw/commons/contributions/Contribution;", "app-commons-v4.2.1-main_betaDebug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull
        @kotlin.jvm.JvmStatic
        public final fr.free.nrw.commons.contributions.WikipediaInstructionsDialogFragment newInstance(@org.jetbrains.annotations.NotNull
        fr.free.nrw.commons.contributions.Contribution contribution) {
            return null;
        }
    }
}