// Generated code from Butter Knife. Do not modify!
package fr.free.nrw.commons.campaigns;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import fr.free.nrw.commons.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class CampaignView$ViewHolder_ViewBinding implements Unbinder {
  private CampaignView.ViewHolder target;

  @UiThread
  public CampaignView$ViewHolder_ViewBinding(CampaignView.ViewHolder target, View source) {
    this.target = target;

    target.ivCampaign = Utils.findRequiredViewAsType(source, R.id.iv_campaign, "field 'ivCampaign'", ImageView.class);
    target.tvTitle = Utils.findRequiredViewAsType(source, R.id.tv_title, "field 'tvTitle'", TextView.class);
    target.tvDescription = Utils.findRequiredViewAsType(source, R.id.tv_description, "field 'tvDescription'", TextView.class);
    target.tvDates = Utils.findRequiredViewAsType(source, R.id.tv_dates, "field 'tvDates'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    CampaignView.ViewHolder target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.ivCampaign = null;
    target.tvTitle = null;
    target.tvDescription = null;
    target.tvDates = null;
  }
}
