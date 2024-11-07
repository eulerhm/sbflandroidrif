/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
 * Copyright (c) 2015-2021 Threema GmbH
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
package ch.threema.app.activities.wizard;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import androidx.core.app.ActivityCompat;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.util.Pair;
import ch.threema.app.R;
import ch.threema.app.ui.AnimationDrawableCallback;
import ch.threema.app.utils.ConfigUtils;
import ch.threema.app.utils.RuntimeUtil;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class WizardStartActivity extends WizardBackgroundActivity {

    private static final Logger logger = LoggerFactory.getLogger(WizardStartActivity.class);

    boolean doFinish = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(1333)) {
            super.onCreate(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(1334)) {
            setContentView(R.layout.activity_wizard_start);
        }
        final ImageView imageView = findViewById(R.id.wizard_animation);
        final AnimationDrawable frameAnimation = (AnimationDrawable) imageView.getBackground();
        if (!ListenerUtil.mutListener.listen(1335)) {
            frameAnimation.setOneShot(true);
        }
        if (!ListenerUtil.mutListener.listen(1337)) {
            frameAnimation.setCallback(new AnimationDrawableCallback(frameAnimation, imageView) {

                @Override
                public void onAnimationAdvanced(int currentFrame, int totalFrames) {
                }

                @Override
                public void onAnimationCompleted() {
                    ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(// the context of the activity
                    WizardStartActivity.this, new Pair<>(findViewById(R.id.wizard_animation), getString(R.string.transition_name_dots)), new Pair<>(findViewById(R.id.wizard_footer), getString(R.string.transition_name_logo)));
                    if (!ListenerUtil.mutListener.listen(1336)) {
                        launchNextActivity(options);
                    }
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(1348)) {
            if ((ListenerUtil.mutListener.listen(1338) ? (!RuntimeUtil.isInTest() || !ConfigUtils.isWorkRestricted()) : (!RuntimeUtil.isInTest() && !ConfigUtils.isWorkRestricted()))) {
                if (!ListenerUtil.mutListener.listen(1342)) {
                    imageView.setOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View v) {
                            if (!ListenerUtil.mutListener.listen(1340)) {
                                ((AnimationDrawable) v.getBackground()).stop();
                            }
                            if (!ListenerUtil.mutListener.listen(1341)) {
                                launchNextActivity(null);
                            }
                        }
                    });
                }
                if (!ListenerUtil.mutListener.listen(1343)) {
                    imageView.getRootView().getViewTreeObserver().addOnGlobalLayoutListener(frameAnimation::start);
                }
                if (!ListenerUtil.mutListener.listen(1347)) {
                    imageView.postDelayed(new Runnable() {

                        @Override
                        public void run() {
                            if (!ListenerUtil.mutListener.listen(1346)) {
                                if (frameAnimation.isRunning()) {
                                    if (!ListenerUtil.mutListener.listen(1344)) {
                                        // stop animation if it's still running after 5 seconds
                                        frameAnimation.stop();
                                    }
                                    if (!ListenerUtil.mutListener.listen(1345)) {
                                        launchNextActivity(null);
                                    }
                                }
                            }
                        }
                    }, 5000);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(1339)) {
                    launchNextActivity(null);
                }
            }
        }
    }

    private void launchNextActivity(ActivityOptionsCompat options) {
        Intent intent;
        if ((ListenerUtil.mutListener.listen(1349) ? (userService != null || userService.hasIdentity()) : (userService != null && userService.hasIdentity()))) {
            intent = new Intent(this, WizardBaseActivity.class);
            if (!ListenerUtil.mutListener.listen(1350)) {
                options = null;
            }
        } else {
            intent = new Intent(this, WizardIntroActivity.class);
        }
        if (!ListenerUtil.mutListener.listen(1356)) {
            if (options != null) {
                try {
                    if (!ListenerUtil.mutListener.listen(1355)) {
                        ActivityCompat.startActivity(this, intent, options.toBundle());
                    }
                } catch (Exception e) {
                    if (!ListenerUtil.mutListener.listen(1353)) {
                        // http://stackoverflow.com/questions/31026745/rjava-lang-illegalargumentexception-on-startactivityintent-bundle-animantion
                        logger.error("Exception", e);
                    }
                    if (!ListenerUtil.mutListener.listen(1354)) {
                        startActivity(intent);
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(1351)) {
                    startActivity(intent);
                }
                if (!ListenerUtil.mutListener.listen(1352)) {
                    overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(1357)) {
            doFinish = true;
        }
    }

    @Override
    public void onStop() {
        if (!ListenerUtil.mutListener.listen(1358)) {
            super.onStop();
        }
        if (!ListenerUtil.mutListener.listen(1360)) {
            if (doFinish)
                if (!ListenerUtil.mutListener.listen(1359)) {
                    finish();
                }
        }
    }
}
