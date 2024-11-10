package fr.free.nrw.commons.campaigns;

import java.lang.System;

/**
 * Data class to hold the response from the campaigns api
 */
@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002R\u0018\u0010\u0003\u001a\u0004\u0018\u00010\u00048\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u001e\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\b8\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b\u00a8\u0006\f"}, d2 = {"Lfr/free/nrw/commons/campaigns/CampaignResponseDTO;", "", "()V", "campaignConfig", "Lfr/free/nrw/commons/campaigns/CampaignConfig;", "getCampaignConfig", "()Lfr/free/nrw/commons/campaigns/CampaignConfig;", "campaigns", "", "Lfr/free/nrw/commons/campaigns/models/Campaign;", "getCampaigns", "()Ljava/util/List;", "app-commons-v4.2.1-main_betaDebug"})
public final class CampaignResponseDTO {
    @org.jetbrains.annotations.Nullable
    @com.google.gson.annotations.SerializedName(value = "config")
    private final fr.free.nrw.commons.campaigns.CampaignConfig campaignConfig = null;
    @org.jetbrains.annotations.Nullable
    @com.google.gson.annotations.SerializedName(value = "campaigns")
    private final java.util.List<fr.free.nrw.commons.campaigns.models.Campaign> campaigns = null;
    
    public CampaignResponseDTO() {
        super();
    }
    
    @org.jetbrains.annotations.Nullable
    public final fr.free.nrw.commons.campaigns.CampaignConfig getCampaignConfig() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.util.List<fr.free.nrw.commons.campaigns.models.Campaign> getCampaigns() {
        return null;
    }
}