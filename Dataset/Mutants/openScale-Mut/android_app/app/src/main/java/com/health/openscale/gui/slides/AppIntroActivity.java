/* Copyright (C) 2019  olie.xdev <olie.xdev@googlemail.com>
 *
 *    This program is free software: you can redistribute it and/or modify
 *    it under the terms of the GNU General Public License as published by
 *    the Free Software Foundation, either version 3 of the License, or
 *    (at your option) any later version.
 *
 *    This program is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *    GNU General Public License for more details.
 *
 *    You should have received a copy of the GNU General Public License
 *    along with this program.  If not, see <http://www.gnu.org/licenses/>
 */
package com.health.openscale.gui.slides;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.github.appintro.AppIntro;
import com.health.openscale.R;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class AppIntroActivity extends AppIntro {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(8791)) {
            super.onCreate(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(8792)) {
            setBarColor(getResources().getColor(R.color.blue_normal));
        }
        if (!ListenerUtil.mutListener.listen(8793)) {
            setSkipButtonEnabled(true);
        }
        if (!ListenerUtil.mutListener.listen(8794)) {
            addSlide(WelcomeIntroSlide.newInstance(R.layout.slide_welcome));
        }
        if (!ListenerUtil.mutListener.listen(8795)) {
            addSlide(PrivacyIntroSlide.newInstance(R.layout.slide_privacy));
        }
        if (!ListenerUtil.mutListener.listen(8796)) {
            addSlide(UserIntroSlide.newInstance(R.layout.slide_user));
        }
        if (!ListenerUtil.mutListener.listen(8797)) {
            addSlide(OpenSourceIntroSlide.newInstance(R.layout.slide_opensource));
        }
        if (!ListenerUtil.mutListener.listen(8798)) {
            addSlide(BluetoothIntroSlide.newInstance(R.layout.slide_bluetooth));
        }
        if (!ListenerUtil.mutListener.listen(8799)) {
            addSlide(MetricsIntroSlide.newInstance(R.layout.slide_metrics));
        }
        if (!ListenerUtil.mutListener.listen(8800)) {
            addSlide(SupportIntroSlide.newInstance(R.layout.slide_support));
        }
    }

    @Override
    public void onSkipPressed(Fragment currentFragment) {
        if (!ListenerUtil.mutListener.listen(8801)) {
            super.onSkipPressed(currentFragment);
        }
        if (!ListenerUtil.mutListener.listen(8802)) {
            finish();
        }
    }

    @Override
    public void onDonePressed(Fragment currentFragment) {
        if (!ListenerUtil.mutListener.listen(8803)) {
            super.onDonePressed(currentFragment);
        }
        if (!ListenerUtil.mutListener.listen(8804)) {
            finish();
        }
    }

    @Override
    public void onSlideChanged(@Nullable Fragment oldFragment, @Nullable Fragment newFragment) {
        if (!ListenerUtil.mutListener.listen(8805)) {
            super.onSlideChanged(oldFragment, newFragment);
        }
        if (!ListenerUtil.mutListener.listen(8810)) {
            if (newFragment instanceof WelcomeIntroSlide) {
                if (!ListenerUtil.mutListener.listen(8808)) {
                    setSkipButtonEnabled(true);
                }
                if (!ListenerUtil.mutListener.listen(8809)) {
                    setWizardMode(false);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(8806)) {
                    setSkipButtonEnabled(false);
                }
                if (!ListenerUtil.mutListener.listen(8807)) {
                    setWizardMode(true);
                }
            }
        }
    }
}
