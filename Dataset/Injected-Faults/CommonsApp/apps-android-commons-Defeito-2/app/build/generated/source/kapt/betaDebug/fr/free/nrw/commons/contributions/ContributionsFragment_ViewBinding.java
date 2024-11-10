// Generated code from Butter Knife. Do not modify!
package fr.free.nrw.commons.contributions;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import fr.free.nrw.commons.R;
import fr.free.nrw.commons.campaigns.CampaignView;
import fr.free.nrw.commons.nearby.NearbyNotificationCardView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ContributionsFragment_ViewBinding implements Unbinder {
  private ContributionsFragment target;

  @UiThread
  public ContributionsFragment_ViewBinding(ContributionsFragment target, View source) {
    this.target = target;

    target.nearbyNotificationCardView = Utils.findRequiredViewAsType(source, R.id.card_view_nearby, "field 'nearbyNotificationCardView'", NearbyNotificationCardView.class);
    target.campaignView = Utils.findRequiredViewAsType(source, R.id.campaigns_view, "field 'campaignView'", CampaignView.class);
    target.limitedConnectionEnabledLayout = Utils.findRequiredViewAsType(source, R.id.limited_connection_enabled_layout, "field 'limitedConnectionEnabledLayout'", LinearLayout.class);
    target.limitedConnectionDescriptionTextView = Utils.findRequiredViewAsType(source, R.id.limited_connection_description_text_view, "field 'limitedConnectionDescriptionTextView'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    ContributionsFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.nearbyNotificationCardView = null;
    target.campaignView = null;
    target.limitedConnectionEnabledLayout = null;
    target.limitedConnectionDescriptionTextView = null;
  }
}
