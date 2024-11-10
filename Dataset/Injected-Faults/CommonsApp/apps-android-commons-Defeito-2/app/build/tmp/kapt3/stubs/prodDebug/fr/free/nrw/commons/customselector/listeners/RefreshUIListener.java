package fr.free.nrw.commons.customselector.listeners;

import java.lang.System;

/**
 * Refresh UI Listener
 */
@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&\u00a8\u0006\u0004"}, d2 = {"Lfr/free/nrw/commons/customselector/listeners/RefreshUIListener;", "", "refresh", "", "app-commons-v4.2.1-master_prodDebug"})
public abstract interface RefreshUIListener {
    
    /**
     * Refreshes the data in adapter
     */
    public abstract void refresh();
}