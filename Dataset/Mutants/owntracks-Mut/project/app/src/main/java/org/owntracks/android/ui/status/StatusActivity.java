package org.owntracks.android.ui.status;

import android.os.Bundle;
import androidx.annotation.Nullable;
import org.owntracks.android.R;
import org.owntracks.android.databinding.UiStatusBinding;
import org.owntracks.android.ui.base.BaseActivity;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class StatusActivity extends BaseActivity<UiStatusBinding, StatusMvvm.ViewModel<StatusMvvm.View>> implements StatusMvvm.View {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(2273)) {
            super.onCreate(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(2274)) {
            bindAndAttachContentView(R.layout.ui_status, savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(2275)) {
            setSupportToolbar(binding.toolbar);
        }
        if (!ListenerUtil.mutListener.listen(2276)) {
            setDrawer(binding.toolbar);
        }
    }
}
