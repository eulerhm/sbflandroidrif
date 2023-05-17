/* Copyright (C) 2020  olie.xdev <olie.xdev@googlemail.com>
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
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import com.health.openscale.R;
import com.health.openscale.SlideNavigationDirections;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

// TODO HACK to access from AppIntro activity to MainActivity fragments until AppIntro support native Androidx navigation component
public class SlideToNavigationAdapter extends AppCompatActivity {

    public static String EXTRA_MODE = "mode";

    public static final int EXTRA_USER_SETTING_MODE = 100;

    public static final int EXTRA_BLUETOOTH_SETTING_MODE = 200;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(8853)) {
            super.onCreate(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(8854)) {
            setContentView(R.layout.activity_slidetonavigation);
        }
        // Set a Toolbar to replace the ActionBar.
        Toolbar toolbar = findViewById(R.id.toolbar);
        if (!ListenerUtil.mutListener.listen(8855)) {
            setSupportActionBar(toolbar);
        }
        if (!ListenerUtil.mutListener.listen(8856)) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        if (!ListenerUtil.mutListener.listen(8858)) {
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    if (!ListenerUtil.mutListener.listen(8857)) {
                        finish();
                    }
                }
            });
        }
        int mode = getIntent().getExtras().getInt(EXTRA_MODE);
        NavDirections action = null;
        if (!ListenerUtil.mutListener.listen(8863)) {
            switch(mode) {
                case EXTRA_USER_SETTING_MODE:
                    if (!ListenerUtil.mutListener.listen(8859)) {
                        action = SlideNavigationDirections.actionNavSlideNavigationToNavUsersettings();
                    }
                    if (!ListenerUtil.mutListener.listen(8860)) {
                        setTitle(R.string.label_add_user);
                    }
                    break;
                case EXTRA_BLUETOOTH_SETTING_MODE:
                    if (!ListenerUtil.mutListener.listen(8861)) {
                        action = SlideNavigationDirections.actionNavSlideNavigationToNavBluetoothsettings();
                    }
                    if (!ListenerUtil.mutListener.listen(8862)) {
                        setTitle(R.string.label_bluetooth_title);
                    }
                    break;
            }
        }
        if (!ListenerUtil.mutListener.listen(8865)) {
            if (action != null) {
                if (!ListenerUtil.mutListener.listen(8864)) {
                    Navigation.findNavController(this, R.id.nav_slide_navigation).navigate(action);
                }
            }
        }
    }
}
