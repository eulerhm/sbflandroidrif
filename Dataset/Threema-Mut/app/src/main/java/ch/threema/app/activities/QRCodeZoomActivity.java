/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
 * Copyright (c) 2014-2021 Threema GmbH
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License, version 3,
 * as published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program. If not, see <https://www.gnu.org/licenses/>.
 */
package ch.threema.app.activities;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import ch.threema.app.R;
import ch.threema.app.ui.QRCodePopup;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 *  Activity displaying QR Code popup. Used by Launcher shortcut
 */
public class QRCodeZoomActivity extends AppCompatActivity {

    QRCodePopup qrPopup = null;

    public void onCreate(final Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(5391)) {
            super.onCreate(savedInstanceState);
        }
        final View rootView = getWindow().getDecorView().getRootView();
        if (!ListenerUtil.mutListener.listen(5399)) {
            if ((ListenerUtil.mutListener.listen(5396) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(5395) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(5394) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(5393) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(5392) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.LOLLIPOP) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP))))))) {
                if (!ListenerUtil.mutListener.listen(5398)) {
                    ViewCompat.setOnApplyWindowInsetsListener(rootView, (v, insets) -> {
                        showPopup(v);
                        return insets;
                    });
                }
            } else {
                if (!ListenerUtil.mutListener.listen(5397)) {
                    showPopup(rootView);
                }
            }
        }
    }

    private void showPopup(final View v) {
        if (!ListenerUtil.mutListener.listen(5400)) {
            v.post(() -> {
                if (qrPopup == null || !qrPopup.isShowing()) {
                    qrPopup = new QRCodePopup(this, v, this);
                    qrPopup.setOnDismissListener(QRCodeZoomActivity.this::finish);
                    if (!isDestroyed() && !isFinishing()) {
                        qrPopup.show(v, null);
                    }
                }
            });
        }
    }

    @Override
    protected void onDestroy() {
        if (!ListenerUtil.mutListener.listen(5404)) {
            if ((ListenerUtil.mutListener.listen(5401) ? (qrPopup != null || qrPopup.isShowing()) : (qrPopup != null && qrPopup.isShowing()))) {
                if (!ListenerUtil.mutListener.listen(5402)) {
                    qrPopup.setOnDismissListener(null);
                }
                if (!ListenerUtil.mutListener.listen(5403)) {
                    qrPopup = null;
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(5405)) {
            super.onDestroy();
        }
    }

    @Override
    protected void onPause() {
        if (!ListenerUtil.mutListener.listen(5406)) {
            super.onPause();
        }
        if (!ListenerUtil.mutListener.listen(5408)) {
            if (isFinishing()) {
                if (!ListenerUtil.mutListener.listen(5407)) {
                    overridePendingTransition(R.anim.fast_fade_in, R.anim.fast_fade_out);
                }
            }
        }
    }
}
