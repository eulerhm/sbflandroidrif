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
package ch.threema.app.webclient.activities;

import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.annotation.UiThread;
import androidx.appcompat.app.ActionBar;
import androidx.preference.PreferenceManager;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import ch.threema.app.R;
import ch.threema.app.activities.ThreemaToolbarActivity;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

@UiThread
public class SessionsIntroActivity extends ThreemaToolbarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(62284)) {
            super.onCreate(savedInstanceState);
        }
        ActionBar actionBar = getSupportActionBar();
        if (!ListenerUtil.mutListener.listen(62287)) {
            if (actionBar != null) {
                if (!ListenerUtil.mutListener.listen(62285)) {
                    actionBar.setDisplayHomeAsUpEnabled(true);
                }
                if (!ListenerUtil.mutListener.listen(62286)) {
                    actionBar.setTitle(R.string.webclient);
                }
            }
        }
        final SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        final Button launchButton = findViewById(R.id.launch_button);
        final TextView linkText = findViewById(R.id.webclient_link);
        if (!ListenerUtil.mutListener.listen(62293)) {
            if (sharedPreferences.getBoolean(getString(R.string.preferences__web_client_welcome_shown), false)) {
                if (!ListenerUtil.mutListener.listen(62289)) {
                    launchButton.setText(R.string.ok);
                }
                if (!ListenerUtil.mutListener.listen(62290)) {
                    linkText.setVisibility(View.VISIBLE);
                }
                if (!ListenerUtil.mutListener.listen(62291)) {
                    linkText.setText(Html.fromHtml("<a href=\"" + getString(R.string.webclient_url) + "\">" + getString(R.string.new_wizard_more_information) + "</a>"));
                }
                if (!ListenerUtil.mutListener.listen(62292)) {
                    linkText.setMovementMethod(LinkMovementMethod.getInstance());
                }
            } else {
                if (!ListenerUtil.mutListener.listen(62288)) {
                    linkText.setVisibility(View.GONE);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(62298)) {
            launchButton.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    if (!ListenerUtil.mutListener.listen(62294)) {
                        v.setEnabled(false);
                    }
                    if (!ListenerUtil.mutListener.listen(62295)) {
                        sharedPreferences.edit().putBoolean(getString(R.string.preferences__web_client_welcome_shown), true).apply();
                    }
                    if (!ListenerUtil.mutListener.listen(62296)) {
                        setResult(RESULT_OK);
                    }
                    if (!ListenerUtil.mutListener.listen(62297)) {
                        finish();
                    }
                }
            });
        }
    }

    @Override
    public int getLayoutResource() {
        return R.layout.activity_sessions_intro;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (!ListenerUtil.mutListener.listen(62300)) {
            switch(item.getItemId()) {
                case android.R.id.home:
                    if (!ListenerUtil.mutListener.listen(62299)) {
                        finish();
                    }
                    break;
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
