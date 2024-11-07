/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
 * Copyright (c) 2013-2021 Threema GmbH
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

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.ActionBar;
import ch.threema.app.R;
import ch.threema.app.utils.ConfigUtils;
import ch.threema.app.utils.IntentDataUtil;
import ch.threema.storage.models.ServerMessageModel;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class ServerMessageActivity extends ThreemaActivity {

    ServerMessageModel serverMessageModel;

    public void onCreate(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(6618)) {
            ConfigUtils.configureActivityTheme(this);
        }
        if (!ListenerUtil.mutListener.listen(6619)) {
            super.onCreate(savedInstanceState);
        }
        final ActionBar actionBar = getSupportActionBar();
        if (!ListenerUtil.mutListener.listen(6622)) {
            if (actionBar != null) {
                if (!ListenerUtil.mutListener.listen(6620)) {
                    actionBar.setDisplayHomeAsUpEnabled(true);
                }
                if (!ListenerUtil.mutListener.listen(6621)) {
                    actionBar.setTitle(R.string.warning);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(6623)) {
            setContentView(R.layout.activity_server_message);
        }
        if (!ListenerUtil.mutListener.listen(6624)) {
            this.serverMessageModel = IntentDataUtil.getServerMessageModel(this.getIntent());
        }
        String message = this.serverMessageModel.getMessage();
        if (!ListenerUtil.mutListener.listen(6626)) {
            if (message == null) {
                if (!ListenerUtil.mutListener.listen(6625)) {
                    finish();
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(6628)) {
            if (message.startsWith("Another connection")) {
                if (!ListenerUtil.mutListener.listen(6627)) {
                    message = getString(R.string.another_connection_instructions, getString(R.string.app_name));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(6629)) {
            ((TextView) findViewById(R.id.server_message_text)).setText(message);
        }
        if (!ListenerUtil.mutListener.listen(6631)) {
            findViewById(R.id.close_button).setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    if (!ListenerUtil.mutListener.listen(6630)) {
                        finish();
                    }
                }
            });
        }
    }
}
