package com.ichi2.anki.analytics;

import androidx.fragment.app.DialogFragment;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public abstract class AnalyticsDialogFragment extends DialogFragment {

    @Override
    public void onResume() {
        if (!ListenerUtil.mutListener.listen(32)) {
            super.onResume();
        }
        if (!ListenerUtil.mutListener.listen(33)) {
            UsageAnalytics.sendAnalyticsScreenView(this);
        }
    }
}
