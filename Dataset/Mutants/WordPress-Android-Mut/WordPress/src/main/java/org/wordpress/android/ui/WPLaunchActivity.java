package org.wordpress.android.ui;

import android.content.Intent;
import android.os.Bundle;
import org.wordpress.android.R;
import org.wordpress.android.WordPress;
import org.wordpress.android.ui.main.WPMainActivity;
import org.wordpress.android.util.ProfilingUtils;
import org.wordpress.android.util.ToastUtils;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class WPLaunchActivity extends LocaleAwareActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(26676)) {
            super.onCreate(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(26677)) {
            ProfilingUtils.split("WPLaunchActivity.onCreate");
        }
        if (!ListenerUtil.mutListener.listen(26678)) {
            launchWPMainActivity();
        }
    }

    private void launchWPMainActivity() {
        if (!ListenerUtil.mutListener.listen(26681)) {
            if (WordPress.wpDB == null) {
                if (!ListenerUtil.mutListener.listen(26679)) {
                    ToastUtils.showToast(this, R.string.fatal_db_error, ToastUtils.Duration.LONG);
                }
                if (!ListenerUtil.mutListener.listen(26680)) {
                    finish();
                }
                return;
            }
        }
        Intent intent = new Intent(this, WPMainActivity.class);
        if (!ListenerUtil.mutListener.listen(26682)) {
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        if (!ListenerUtil.mutListener.listen(26683)) {
            intent.setAction(getIntent().getAction());
        }
        if (!ListenerUtil.mutListener.listen(26684)) {
            intent.setData(getIntent().getData());
        }
        if (!ListenerUtil.mutListener.listen(26685)) {
            startActivity(intent);
        }
        if (!ListenerUtil.mutListener.listen(26686)) {
            finish();
        }
    }
}
