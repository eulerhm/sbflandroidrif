/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
 * Copyright (c) 2017-2021 Threema GmbH
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

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import ch.threema.app.R;
import ch.threema.app.utils.AnimationUtil;
import ch.threema.app.utils.ConfigUtils;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class WhatsNewActivity extends ThreemaAppCompatActivity {

    public static final String EXTRA_NO_ANIMATION = "noanim";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(7224)) {
            ConfigUtils.configureActivityTheme(this);
        }
        if (!ListenerUtil.mutListener.listen(7225)) {
            super.onCreate(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(7226)) {
            setContentView(R.layout.activity_whatsnew);
        }
        if (!ListenerUtil.mutListener.listen(7227)) {
            ((TextView) findViewById(R.id.whatsnew_title)).setText(getString(R.string.whatsnew_title, getString(R.string.app_name)));
        }
        if (!ListenerUtil.mutListener.listen(7228)) {
            ((TextView) findViewById(R.id.whatsnew_body)).setText(getString(R.string.whatsnew_headline, getString(R.string.app_name)));
        }
        if (!ListenerUtil.mutListener.listen(7229)) {
            findViewById(R.id.next_text).setOnClickListener(v -> {
                // overridePendingTransition(R.anim.slide_in_right_short, R.anim.slide_out_left_short);
                finish();
            });
        }
        if (!ListenerUtil.mutListener.listen(7233)) {
            if (!getIntent().getBooleanExtra(EXTRA_NO_ANIMATION, false)) {
                LinearLayout buttonLayout = findViewById(R.id.button_layout);
                if (!ListenerUtil.mutListener.listen(7232)) {
                    if (savedInstanceState == null) {
                        if (!ListenerUtil.mutListener.listen(7230)) {
                            buttonLayout.setVisibility(View.GONE);
                        }
                        if (!ListenerUtil.mutListener.listen(7231)) {
                            buttonLayout.postDelayed(() -> AnimationUtil.slideInFromBottomOvershoot(buttonLayout), 200);
                        }
                    }
                }
            }
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        if (!ListenerUtil.mutListener.listen(7234)) {
            super.onConfigurationChanged(newConfig);
        }
    }
}
