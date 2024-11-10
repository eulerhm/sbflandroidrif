package fr.free.nrw.commons.upload;

import java.lang.System;

/**
 * Holds a description of an item being uploaded by [UploadActivity]
 */
@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\u0006\u0018\u0000 \u00182\u00020\u0001:\u0001\u0018B\u0005\u00a2\u0006\u0002\u0010\u0002R\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u0011\u0010\t\u001a\u00020\n8F\u00a2\u0006\u0006\u001a\u0004\b\t\u0010\u000bR\u001a\u0010\f\u001a\u00020\nX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\u000b\"\u0004\b\r\u0010\u000eR\u001c\u0010\u000f\u001a\u0004\u0018\u00010\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0006\"\u0004\b\u0011\u0010\bR\u001a\u0010\u0012\u001a\u00020\u0013X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017\u00a8\u0006\u0019"}, d2 = {"Lfr/free/nrw/commons/upload/Description;", "", "()V", "descriptionText", "", "getDescriptionText", "()Ljava/lang/String;", "setDescriptionText", "(Ljava/lang/String;)V", "isEmpty", "", "()Z", "isManuallyAdded", "setManuallyAdded", "(Z)V", "languageCode", "getLanguageCode", "setLanguageCode", "selectedLanguageIndex", "", "getSelectedLanguageIndex", "()I", "setSelectedLanguageIndex", "(I)V", "Companion", "app-commons-v4.2.1-main_betaDebug"})
public final class Description {
    
    /**
     * @param languageCode The language code ie. "en" or "fr"
     */
    @org.jetbrains.annotations.Nullable
    private java.lang.String languageCode;
    @org.jetbrains.annotations.Nullable
    private java.lang.String descriptionText;
    
    /**
     * @param selectedLanguageIndex the index of the language selected in a spinner with [SpinnerLanguagesAdapter]
     */
    private int selectedLanguageIndex = -1;
    
    /**
     * sets to true if the description was manually added by the user
     * @param manuallyAdded
     */
    private boolean isManuallyAdded = false;
    @org.jetbrains.annotations.NotNull
    public static final fr.free.nrw.commons.upload.Description.Companion Companion = null;
    
    public Description() {
        super();
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getLanguageCode() {
        return null;
    }
    
    public final void setLanguageCode(@org.jetbrains.annotations.Nullable
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getDescriptionText() {
        return null;
    }
    
    public final void setDescriptionText(@org.jetbrains.annotations.Nullable
    java.lang.String p0) {
    }
    
    public final int getSelectedLanguageIndex() {
        return 0;
    }
    
    public final void setSelectedLanguageIndex(int p0) {
    }
    
    public final boolean isManuallyAdded() {
        return false;
    }
    
    public final void setManuallyAdded(boolean p0) {
    }
    
    public final boolean isEmpty() {
        return false;
    }
    
    /**
     * Formats the list of descriptions into the format Commons requires for uploads.
     *
     * @param descriptions the list of descriptions, description is ignored if text is null.
     * @return a string with the pattern of {{en|1=descriptionText}}
     */
    @org.jetbrains.annotations.NotNull
    @kotlin.jvm.JvmStatic
    public static final java.lang.String formatList(@org.jetbrains.annotations.NotNull
    java.util.List<fr.free.nrw.commons.upload.Description> descriptions) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0016\u0010\u0003\u001a\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006H\u0007\u00a8\u0006\b"}, d2 = {"Lfr/free/nrw/commons/upload/Description$Companion;", "", "()V", "formatList", "", "descriptions", "", "Lfr/free/nrw/commons/upload/Description;", "app-commons-v4.2.1-main_betaDebug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        /**
         * Formats the list of descriptions into the format Commons requires for uploads.
         *
         * @param descriptions the list of descriptions, description is ignored if text is null.
         * @return a string with the pattern of {{en|1=descriptionText}}
         */
        @org.jetbrains.annotations.NotNull
        @kotlin.jvm.JvmStatic
        public final java.lang.String formatList(@org.jetbrains.annotations.NotNull
        java.util.List<fr.free.nrw.commons.upload.Description> descriptions) {
            return null;
        }
    }
}