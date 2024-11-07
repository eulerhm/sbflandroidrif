package fr.free.nrw.commons.campaigns;

import android.annotation.SuppressLint;
import fr.free.nrw.commons.campaigns.models.Campaign;
import java.text.ParseException;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import fr.free.nrw.commons.BasePresenter;
import fr.free.nrw.commons.mwapi.OkHttpJsonApiClient;
import fr.free.nrw.commons.utils.CommonsDateUtil;
import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;
import timber.log.Timber;
import static fr.free.nrw.commons.di.CommonsApplicationModule.IO_THREAD;
import static fr.free.nrw.commons.di.CommonsApplicationModule.MAIN_THREAD;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * The presenter for the campaigns view, fetches the campaigns from the api and informs the view on
 * success and error
 */
@Singleton
public class CampaignsPresenter implements BasePresenter<ICampaignsView> {

    private final OkHttpJsonApiClient okHttpJsonApiClient;

    private final Scheduler mainThreadScheduler;

    private final Scheduler ioScheduler;

    private ICampaignsView view;

    private Disposable disposable;

    private Campaign campaign;

    @Inject
    public CampaignsPresenter(OkHttpJsonApiClient okHttpJsonApiClient, @Named(IO_THREAD) Scheduler ioScheduler, @Named(MAIN_THREAD) Scheduler mainThreadScheduler) {
        this.okHttpJsonApiClient = okHttpJsonApiClient;
        this.mainThreadScheduler = mainThreadScheduler;
        this.ioScheduler = ioScheduler;
    }

    @Override
    public void onAttachView(ICampaignsView view) {
        if (!ListenerUtil.mutListener.listen(6066)) {
            this.view = view;
        }
    }

    @Override
    public void onDetachView() {
        if (!ListenerUtil.mutListener.listen(6067)) {
            this.view = null;
        }
        if (!ListenerUtil.mutListener.listen(6069)) {
            if (disposable != null) {
                if (!ListenerUtil.mutListener.listen(6068)) {
                    disposable.dispose();
                }
            }
        }
    }

    /**
     * make the api call to fetch the campaigns
     */
    @SuppressLint("CheckResult")
    public void getCampaigns() {
        if (!ListenerUtil.mutListener.listen(6096)) {
            if ((ListenerUtil.mutListener.listen(6070) ? (view != null || okHttpJsonApiClient != null) : (view != null && okHttpJsonApiClient != null))) {
                if (!ListenerUtil.mutListener.listen(6072)) {
                    // If we already have a campaign, lets not make another call
                    if (this.campaign != null) {
                        if (!ListenerUtil.mutListener.listen(6071)) {
                            view.showCampaigns(campaign);
                        }
                        return;
                    }
                }
                Single<CampaignResponseDTO> campaigns = okHttpJsonApiClient.getCampaigns();
                if (!ListenerUtil.mutListener.listen(6095)) {
                    campaigns.observeOn(mainThreadScheduler).subscribeOn(ioScheduler).subscribeWith(new SingleObserver<CampaignResponseDTO>() {

                        @Override
                        public void onSubscribe(Disposable d) {
                            if (!ListenerUtil.mutListener.listen(6073)) {
                                disposable = d;
                            }
                        }

                        @Override
                        public void onSuccess(CampaignResponseDTO campaignResponseDTO) {
                            List<Campaign> campaigns = campaignResponseDTO.getCampaigns();
                            if (!ListenerUtil.mutListener.listen(6077)) {
                                if ((ListenerUtil.mutListener.listen(6074) ? (campaigns == null && campaigns.isEmpty()) : (campaigns == null || campaigns.isEmpty()))) {
                                    if (!ListenerUtil.mutListener.listen(6075)) {
                                        Timber.e("The campaigns list is empty");
                                    }
                                    if (!ListenerUtil.mutListener.listen(6076)) {
                                        view.showCampaigns(null);
                                    }
                                    return;
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(6078)) {
                                Collections.sort(campaigns, (campaign, t1) -> {
                                    Date date1, date2;
                                    try {
                                        date1 = CommonsDateUtil.getIso8601DateFormatShort().parse(campaign.getStartDate());
                                        date2 = CommonsDateUtil.getIso8601DateFormatShort().parse(t1.getStartDate());
                                    } catch (ParseException e) {
                                        e.printStackTrace();
                                        return -1;
                                    }
                                    return date1.compareTo(date2);
                                });
                            }
                            Date campaignEndDate, campaignStartDate;
                            Date currentDate = new Date();
                            try {
                                {
                                    long _loopCounter91 = 0;
                                    for (Campaign aCampaign : campaigns) {
                                        ListenerUtil.loopListener.listen("_loopCounter91", ++_loopCounter91);
                                        campaignEndDate = CommonsDateUtil.getIso8601DateFormatShort().parse(aCampaign.getEndDate());
                                        campaignStartDate = CommonsDateUtil.getIso8601DateFormatShort().parse(aCampaign.getStartDate());
                                        if (!ListenerUtil.mutListener.listen(6092)) {
                                            if ((ListenerUtil.mutListener.listen(6090) ? ((ListenerUtil.mutListener.listen(6084) ? (campaignEndDate.compareTo(currentDate) <= 0) : (ListenerUtil.mutListener.listen(6083) ? (campaignEndDate.compareTo(currentDate) > 0) : (ListenerUtil.mutListener.listen(6082) ? (campaignEndDate.compareTo(currentDate) < 0) : (ListenerUtil.mutListener.listen(6081) ? (campaignEndDate.compareTo(currentDate) != 0) : (ListenerUtil.mutListener.listen(6080) ? (campaignEndDate.compareTo(currentDate) == 0) : (campaignEndDate.compareTo(currentDate) >= 0)))))) || (ListenerUtil.mutListener.listen(6089) ? (campaignStartDate.compareTo(currentDate) >= 0) : (ListenerUtil.mutListener.listen(6088) ? (campaignStartDate.compareTo(currentDate) > 0) : (ListenerUtil.mutListener.listen(6087) ? (campaignStartDate.compareTo(currentDate) < 0) : (ListenerUtil.mutListener.listen(6086) ? (campaignStartDate.compareTo(currentDate) != 0) : (ListenerUtil.mutListener.listen(6085) ? (campaignStartDate.compareTo(currentDate) == 0) : (campaignStartDate.compareTo(currentDate) <= 0))))))) : ((ListenerUtil.mutListener.listen(6084) ? (campaignEndDate.compareTo(currentDate) <= 0) : (ListenerUtil.mutListener.listen(6083) ? (campaignEndDate.compareTo(currentDate) > 0) : (ListenerUtil.mutListener.listen(6082) ? (campaignEndDate.compareTo(currentDate) < 0) : (ListenerUtil.mutListener.listen(6081) ? (campaignEndDate.compareTo(currentDate) != 0) : (ListenerUtil.mutListener.listen(6080) ? (campaignEndDate.compareTo(currentDate) == 0) : (campaignEndDate.compareTo(currentDate) >= 0)))))) && (ListenerUtil.mutListener.listen(6089) ? (campaignStartDate.compareTo(currentDate) >= 0) : (ListenerUtil.mutListener.listen(6088) ? (campaignStartDate.compareTo(currentDate) > 0) : (ListenerUtil.mutListener.listen(6087) ? (campaignStartDate.compareTo(currentDate) < 0) : (ListenerUtil.mutListener.listen(6086) ? (campaignStartDate.compareTo(currentDate) != 0) : (ListenerUtil.mutListener.listen(6085) ? (campaignStartDate.compareTo(currentDate) == 0) : (campaignStartDate.compareTo(currentDate) <= 0))))))))) {
                                                if (!ListenerUtil.mutListener.listen(6091)) {
                                                    campaign = aCampaign;
                                                }
                                                break;
                                            }
                                        }
                                    }
                                }
                            } catch (ParseException e) {
                                if (!ListenerUtil.mutListener.listen(6079)) {
                                    e.printStackTrace();
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(6093)) {
                                view.showCampaigns(campaign);
                            }
                        }

                        @Override
                        public void onError(Throwable e) {
                            if (!ListenerUtil.mutListener.listen(6094)) {
                                Timber.e(e, "could not fetch campaigns");
                            }
                        }
                    });
                }
            }
        }
    }
}
