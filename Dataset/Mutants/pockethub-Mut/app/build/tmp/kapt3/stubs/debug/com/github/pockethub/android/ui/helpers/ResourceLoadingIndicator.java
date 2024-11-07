package com.github.pockethub.android.ui.helpers;

import com.github.pockethub.android.ui.item.LoadingItem;
import com.xwray.groupie.Section;

/**
 * Helper for showing more items are being loaded at the bottom of a list via a
 * custom footer view
 *
 * @param section the adapter that this indicator should be added as a footer to.
 */
@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R$\u0010\t\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\b@FX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\r\u00a8\u0006\u000e"}, d2 = {"Lcom/github/pockethub/android/ui/helpers/ResourceLoadingIndicator;", "", "section", "Lcom/xwray/groupie/Section;", "(Lcom/xwray/groupie/Section;)V", "loadingItem", "Lcom/github/pockethub/android/ui/item/LoadingItem;", "value", "", "visible", "getVisible", "()Z", "setVisible", "(Z)V", "app_debug"})
public final class ResourceLoadingIndicator {
    private final com.github.pockethub.android.ui.item.LoadingItem loadingItem = null;
    
    /**
     * Visibility of entire indicator view.
     */
    private boolean visible;
    private final com.xwray.groupie.Section section = null;
    
    public final boolean getVisible() {
        return false;
    }
    
    public final void setVisible(boolean value) {
    }
    
    public ResourceLoadingIndicator(@org.jetbrains.annotations.NotNull()
    com.xwray.groupie.Section section) {
        super();
    }
}