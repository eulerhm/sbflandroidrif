package org.wordpress.android.ui.prefs;

import android.content.Context;
import org.wordpress.android.datasets.SiteSettingsTable;
import org.wordpress.android.fluxc.model.SiteModel;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

class SelfHostedSiteSettings extends SiteSettingsInterface {

    /**
     * Only instantiated by {@link SiteSettingsInterface}.
     */
    SelfHostedSiteSettings(Context host, SiteModel site, SiteSettingsListener listener) {
        super(host, site, listener);
    }

    @Override
    protected void fetchRemoteData() {
        if (!ListenerUtil.mutListener.listen(15038)) {
            // TODO - Call the XML-RPC endpoint
            SiteSettingsTable.saveSettings(mSettings);
        }
    }

    @Override
    public void saveSettings() {
        if (!ListenerUtil.mutListener.listen(15039)) {
            super.saveSettings();
        }
        if (!ListenerUtil.mutListener.listen(15040)) {
            mSite.setUsername(mSettings.username);
        }
        if (!ListenerUtil.mutListener.listen(15041)) {
            mSite.setPassword(mSettings.password);
        }
    }
}
