package fr.free.nrw.commons.recentlanguages;

import java.lang.System;

/**
 * Array adapter for recent languages
 */
@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\u0018\u00002\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0001B-\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\u0010\u0010\b\u001a\f\u0012\u0002\b\u0003\u0012\u0004\u0012\u00020\u00020\t\u00a2\u0006\u0002\u0010\nJ\b\u0010\u0014\u001a\u00020\u0015H\u0016J\u000e\u0010\u0016\u001a\u00020\u00022\u0006\u0010\u0017\u001a\u00020\u0015J\u000e\u0010\u0018\u001a\u00020\u00022\u0006\u0010\u0017\u001a\u00020\u0015J\"\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u0017\u001a\u00020\u00152\b\u0010\u001b\u001a\u0004\u0018\u00010\u001a2\u0006\u0010\u001c\u001a\u00020\u001dH\u0016J\u0010\u0010\u001e\u001a\u00020\u001f2\u0006\u0010\u0017\u001a\u00020\u0015H\u0016R \u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001a\u0010\u000f\u001a\u00020\u0002X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u0018\u0010\b\u001a\f\u0012\u0002\b\u0003\u0012\u0004\u0012\u00020\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006 "}, d2 = {"Lfr/free/nrw/commons/recentlanguages/RecentLanguagesAdapter;", "Landroid/widget/ArrayAdapter;", "", "context", "Landroid/content/Context;", "recentLanguages", "", "Lfr/free/nrw/commons/recentlanguages/Language;", "selectedLanguages", "Ljava/util/HashMap;", "(Landroid/content/Context;Ljava/util/List;Ljava/util/HashMap;)V", "getRecentLanguages", "()Ljava/util/List;", "setRecentLanguages", "(Ljava/util/List;)V", "selectedLangCode", "getSelectedLangCode", "()Ljava/lang/String;", "setSelectedLangCode", "(Ljava/lang/String;)V", "getCount", "", "getLanguageCode", "position", "getLanguageName", "getView", "Landroid/view/View;", "convertView", "parent", "Landroid/view/ViewGroup;", "isEnabled", "", "app-commons-v4.2.1-master_prodDebug"})
public final class RecentLanguagesAdapter extends android.widget.ArrayAdapter<java.lang.String> {
    @org.jetbrains.annotations.NotNull
    private java.util.List<fr.free.nrw.commons.recentlanguages.Language> recentLanguages;
    private final java.util.HashMap<?, java.lang.String> selectedLanguages = null;
    
    /**
     * Selected language code in UploadMediaDetailAdapter
     * Used for marking selected ones
     */
    @org.jetbrains.annotations.NotNull
    private java.lang.String selectedLangCode = "";
    
    public RecentLanguagesAdapter(@org.jetbrains.annotations.NotNull
    android.content.Context context, @org.jetbrains.annotations.NotNull
    java.util.List<fr.free.nrw.commons.recentlanguages.Language> recentLanguages, @org.jetbrains.annotations.NotNull
    java.util.HashMap<?, java.lang.String> selectedLanguages) {
        super(null, 0);
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.List<fr.free.nrw.commons.recentlanguages.Language> getRecentLanguages() {
        return null;
    }
    
    public final void setRecentLanguages(@org.jetbrains.annotations.NotNull
    java.util.List<fr.free.nrw.commons.recentlanguages.Language> p0) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getSelectedLangCode() {
        return null;
    }
    
    public final void setSelectedLangCode(@org.jetbrains.annotations.NotNull
    java.lang.String p0) {
    }
    
    @java.lang.Override
    public boolean isEnabled(int position) {
        return false;
    }
    
    @java.lang.Override
    public int getCount() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull
    @java.lang.Override
    public android.view.View getView(int position, @org.jetbrains.annotations.Nullable
    android.view.View convertView, @org.jetbrains.annotations.NotNull
    android.view.ViewGroup parent) {
        return null;
    }
    
    /**
     * Provides code of a language from recent languages for a specific position
     */
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getLanguageCode(int position) {
        return null;
    }
    
    /**
     * Provides name of a language from recent languages for a specific position
     */
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getLanguageName(int position) {
        return null;
    }
}