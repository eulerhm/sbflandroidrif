package fr.free.nrw.commons.upload;

import java.lang.System;

/**
 * This class handles the display of language dialog and their views for UploadMediaDetailFragment
 *
 * @property selectedLanguages - controls the enabled state of item views
 *
 * @param context - required by super constructor
 */
@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\u0018\u0000 (2\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0001:\u0002()B\u001f\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0010\u0010\u0005\u001a\f\u0012\u0002\b\u0003\u0012\u0004\u0012\u00020\u00020\u0006\u00a2\u0006\u0002\u0010\u0007J\b\u0010\u0018\u001a\u00020\u0019H\u0016J\f\u0010\u001a\u001a\u00060\tR\u00020\u0000H\u0016J\u000e\u0010\u001b\u001a\u00020\u00192\u0006\u0010\u001c\u001a\u00020\u0002J\u000e\u0010\u001d\u001a\u00020\u00192\u0006\u0010\u0003\u001a\u00020\u0004J\u000e\u0010\u001e\u001a\u00020\u00022\u0006\u0010\u001f\u001a\u00020\u0019J\u000e\u0010 \u001a\u00020\u00022\u0006\u0010\u001f\u001a\u00020\u0019J\"\u0010!\u001a\u00020\"2\u0006\u0010\u001f\u001a\u00020\u00192\b\u0010#\u001a\u0004\u0018\u00010\"2\u0006\u0010$\u001a\u00020%H\u0016J\u0010\u0010&\u001a\u00020\'2\u0006\u0010\u001f\u001a\u00020\u0019H\u0016R\u0012\u0010\b\u001a\u00060\tR\u00020\u0000X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\n\u001a\u00020\u000bX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u0014\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00020\u0011X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00020\u0011X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0013\u001a\u00020\u0002X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u0018\u0010\u0005\u001a\f\u0012\u0002\b\u0003\u0012\u0004\u0012\u00020\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006*"}, d2 = {"Lfr/free/nrw/commons/upload/LanguagesAdapter;", "Landroid/widget/ArrayAdapter;", "", "context", "Landroid/content/Context;", "selectedLanguages", "Ljava/util/HashMap;", "(Landroid/content/Context;Ljava/util/HashMap;)V", "filter", "Lfr/free/nrw/commons/upload/LanguagesAdapter$LanguageFilter;", "language", "Lorg/wikipedia/language/AppLanguageLookUpTable;", "getLanguage", "()Lorg/wikipedia/language/AppLanguageLookUpTable;", "setLanguage", "(Lorg/wikipedia/language/AppLanguageLookUpTable;)V", "languageCodesList", "", "languageNamesList", "selectedLangCode", "getSelectedLangCode", "()Ljava/lang/String;", "setSelectedLangCode", "(Ljava/lang/String;)V", "getCount", "", "getFilter", "getIndexOfLanguageCode", "languageCode", "getIndexOfUserDefaultLocale", "getLanguageCode", "position", "getLanguageName", "getView", "Landroid/view/View;", "convertView", "parent", "Landroid/view/ViewGroup;", "isEnabled", "", "Companion", "LanguageFilter", "app-commons-v4.2.1-master_prodDebug"})
public final class LanguagesAdapter extends android.widget.ArrayAdapter<java.lang.String> {
    private final java.util.HashMap<?, java.lang.String> selectedLanguages = null;
    @org.jetbrains.annotations.NotNull
    public static final fr.free.nrw.commons.upload.LanguagesAdapter.Companion Companion = null;
    
    /**
     * Represents the default index for the language list. By default, this index corresponds to the
     * English language. This serves as a fallback when the user's system language is not present in
     * the language_list.xml. Though this default can be changed by the user, it does not affect other
     * functionalities of the application. Fixes bug issue 5338
     */
    public static final int DEFAULT_INDEX = 0;
    private java.util.List<java.lang.String> languageNamesList;
    private java.util.List<java.lang.String> languageCodesList;
    @org.jetbrains.annotations.NotNull
    private org.wikipedia.language.AppLanguageLookUpTable language;
    private final fr.free.nrw.commons.upload.LanguagesAdapter.LanguageFilter filter = null;
    @org.jetbrains.annotations.NotNull
    private java.lang.String selectedLangCode = "";
    
    public LanguagesAdapter(@org.jetbrains.annotations.NotNull
    android.content.Context context, @org.jetbrains.annotations.NotNull
    java.util.HashMap<?, java.lang.String> selectedLanguages) {
        super(null, 0);
    }
    
    @org.jetbrains.annotations.NotNull
    public final org.wikipedia.language.AppLanguageLookUpTable getLanguage() {
        return null;
    }
    
    public final void setLanguage(@org.jetbrains.annotations.NotNull
    org.wikipedia.language.AppLanguageLookUpTable p0) {
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
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getLanguageCode(int position) {
        return null;
    }
    
    /**
     * Provides name of a language from languages for a specific position
     */
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getLanguageName(int position) {
        return null;
    }
    
    /**
     * Retrieves the index of the user's default locale from the list of available languages.
     *
     * This function checks the user's system language and finds its index within the application's
     * list of supported languages. If the system language is not supported, or any error occurs,
     * it falls back to the default language index, typically representing English.
     *
     *
     * @param context The context used to get the user's system locale.
     * @return The index of the user's default language in the supported language list,
     *        or the default index if the language is not found.
     * Note: This function was implemented to address a bug where unsupported system languages
     * resulted in an incorrect language selection. Directly returning the result of `indexOf`
     * without checking its validity could result in returning an index of -1, leading to ArrayIndex
     * OutOfBoundsException.
     * [See bug  issue 5338]
     * It's essential to ensure that the returned index is valid or fall back to a default index.
     * Future contributors are advised not to simplify this function without addressing this concern.
     */
    public final int getIndexOfUserDefaultLocale(@org.jetbrains.annotations.NotNull
    android.content.Context context) {
        return 0;
    }
    
    public final int getIndexOfLanguageCode(@org.jetbrains.annotations.NotNull
    java.lang.String languageCode) {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull
    @java.lang.Override
    public fr.free.nrw.commons.upload.LanguagesAdapter.LanguageFilter getFilter() {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\r\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0014J\u001a\u0010\u0007\u001a\u00020\b2\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\t\u001a\u00020\u0004H\u0014\u00a8\u0006\n"}, d2 = {"Lfr/free/nrw/commons/upload/LanguagesAdapter$LanguageFilter;", "Landroid/widget/Filter;", "(Lfr/free/nrw/commons/upload/LanguagesAdapter;)V", "performFiltering", "Landroid/widget/Filter$FilterResults;", "constraint", "", "publishResults", "", "results", "app-commons-v4.2.1-master_prodDebug"})
    public final class LanguageFilter extends android.widget.Filter {
        
        public LanguageFilter() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull
        @java.lang.Override
        protected android.widget.Filter.FilterResults performFiltering(@org.jetbrains.annotations.Nullable
        java.lang.CharSequence constraint) {
            return null;
        }
        
        @java.lang.Override
        protected void publishResults(@org.jetbrains.annotations.Nullable
        java.lang.CharSequence constraint, @org.jetbrains.annotations.NotNull
        android.widget.Filter.FilterResults results) {
        }
    }
    
    @kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, d2 = {"Lfr/free/nrw/commons/upload/LanguagesAdapter$Companion;", "", "()V", "DEFAULT_INDEX", "", "app-commons-v4.2.1-master_prodDebug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}