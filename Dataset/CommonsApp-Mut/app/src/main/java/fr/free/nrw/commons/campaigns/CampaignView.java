package fr.free.nrw.commons.campaigns;

import android.content.Context;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import fr.free.nrw.commons.campaigns.models.Campaign;
import fr.free.nrw.commons.theme.BaseActivity;
import org.wikipedia.util.DateUtil;
import java.text.ParseException;
import java.util.Date;
import butterknife.BindView;
import butterknife.ButterKnife;
import fr.free.nrw.commons.R;
import fr.free.nrw.commons.Utils;
import fr.free.nrw.commons.contributions.MainActivity;
import fr.free.nrw.commons.utils.CommonsDateUtil;
import fr.free.nrw.commons.utils.SwipableCardView;
import fr.free.nrw.commons.utils.ViewUtil;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * A view which represents a single campaign
 */
public class CampaignView extends SwipableCardView {

    Campaign campaign;

    private ViewHolder viewHolder;

    public static final String CAMPAIGNS_DEFAULT_PREFERENCE = "displayCampaignsCardView";

    public static final String WLM_CARD_PREFERENCE = "displayWLMCardView";

    private String campaignPreference = CAMPAIGNS_DEFAULT_PREFERENCE;

    public CampaignView(@NonNull Context context) {
        super(context);
        if (!ListenerUtil.mutListener.listen(6042)) {
            init();
        }
    }

    public CampaignView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        if (!ListenerUtil.mutListener.listen(6043)) {
            init();
        }
    }

    public CampaignView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        if (!ListenerUtil.mutListener.listen(6044)) {
            init();
        }
    }

    public void setCampaign(final Campaign campaign) {
        if (!ListenerUtil.mutListener.listen(6045)) {
            this.campaign = campaign;
        }
        if (!ListenerUtil.mutListener.listen(6051)) {
            if (campaign != null) {
                if (!ListenerUtil.mutListener.listen(6048)) {
                    if (campaign.isWLMCampaign()) {
                        if (!ListenerUtil.mutListener.listen(6047)) {
                            campaignPreference = WLM_CARD_PREFERENCE;
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(6049)) {
                    setVisibility(View.VISIBLE);
                }
                if (!ListenerUtil.mutListener.listen(6050)) {
                    viewHolder.init();
                }
            } else {
                if (!ListenerUtil.mutListener.listen(6046)) {
                    this.setVisibility(View.GONE);
                }
            }
        }
    }

    @Override
    public boolean onSwipe(final View view) {
        if (!ListenerUtil.mutListener.listen(6052)) {
            view.setVisibility(View.GONE);
        }
        if (!ListenerUtil.mutListener.listen(6053)) {
            ((BaseActivity) getContext()).defaultKvStore.putBoolean(campaignPreference, false);
        }
        if (!ListenerUtil.mutListener.listen(6054)) {
            ViewUtil.showLongToast(getContext(), getResources().getString(R.string.nearby_campaign_dismiss_message));
        }
        return true;
    }

    private void init() {
        final View rootView = inflate(getContext(), R.layout.layout_campagin, this);
        if (!ListenerUtil.mutListener.listen(6055)) {
            viewHolder = new ViewHolder(rootView);
        }
        if (!ListenerUtil.mutListener.listen(6056)) {
            setOnClickListener(view -> {
                if (campaign != null) {
                    if (campaign.isWLMCampaign()) {
                        ((MainActivity) (getContext())).showNearby();
                    } else {
                        Utils.handleWebUrl(getContext(), Uri.parse(campaign.getLink()));
                    }
                }
            });
        }
    }

    public class ViewHolder {

        @BindView(R.id.iv_campaign)
        ImageView ivCampaign;

        @BindView(R.id.tv_title)
        TextView tvTitle;

        @BindView(R.id.tv_description)
        TextView tvDescription;

        @BindView(R.id.tv_dates)
        TextView tvDates;

        public ViewHolder(View itemView) {
            if (!ListenerUtil.mutListener.listen(6057)) {
                ButterKnife.bind(this, itemView);
            }
        }

        public void init() {
            if (!ListenerUtil.mutListener.listen(6065)) {
                if (campaign != null) {
                    if (!ListenerUtil.mutListener.listen(6058)) {
                        ivCampaign.setImageDrawable(getResources().getDrawable(R.drawable.ic_campaign));
                    }
                    if (!ListenerUtil.mutListener.listen(6059)) {
                        tvTitle.setText(campaign.getTitle());
                    }
                    if (!ListenerUtil.mutListener.listen(6060)) {
                        tvDescription.setText(campaign.getDescription());
                    }
                    try {
                        if (!ListenerUtil.mutListener.listen(6064)) {
                            if (campaign.isWLMCampaign()) {
                                if (!ListenerUtil.mutListener.listen(6063)) {
                                    tvDates.setText(String.format("%1s - %2s", campaign.getStartDate(), campaign.getEndDate()));
                                }
                            } else {
                                final Date startDate = CommonsDateUtil.getIso8601DateFormatShort().parse(campaign.getStartDate());
                                final Date endDate = CommonsDateUtil.getIso8601DateFormatShort().parse(campaign.getEndDate());
                                if (!ListenerUtil.mutListener.listen(6062)) {
                                    tvDates.setText(String.format("%1s - %2s", DateUtil.getExtraShortDateString(startDate), DateUtil.getExtraShortDateString(endDate)));
                                }
                            }
                        }
                    } catch (final ParseException e) {
                        if (!ListenerUtil.mutListener.listen(6061)) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }
}
