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
package ch.threema.app.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Toast;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;
import ch.threema.app.R;
import ch.threema.app.ThreemaApplication;
import ch.threema.app.managers.ServiceManager;
import ch.threema.app.services.UserService;
import ch.threema.app.utils.TestUtil;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class SMSVerificationLinkActivity extends AppCompatActivity {

    private static final Logger logger = LoggerFactory.getLogger(SMSVerificationLinkActivity.class);

    private UserService userService;

    public void onCreate(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(6663)) {
            super.onCreate(savedInstanceState);
        }
        Integer resultText = R.string.verify_failed_summary;
        ServiceManager serviceManager = ThreemaApplication.getServiceManager();
        if (!ListenerUtil.mutListener.listen(6676)) {
            if (serviceManager != null) {
                if (!ListenerUtil.mutListener.listen(6664)) {
                    this.userService = serviceManager.getUserService();
                }
                if (!ListenerUtil.mutListener.listen(6675)) {
                    if (this.userService != null) {
                        if (!ListenerUtil.mutListener.listen(6674)) {
                            if (this.userService.getMobileLinkingState() == UserService.LinkingState_PENDING) {
                                Intent intent = getIntent();
                                Uri data = intent.getData();
                                if (!ListenerUtil.mutListener.listen(6673)) {
                                    if (data != null) {
                                        final String code = data.getQueryParameter("code");
                                        if (!ListenerUtil.mutListener.listen(6672)) {
                                            if (!TestUtil.empty(code)) {
                                                if (!ListenerUtil.mutListener.listen(6667)) {
                                                    resultText = null;
                                                }
                                                if (!ListenerUtil.mutListener.listen(6671)) {
                                                    new AsyncTask<Void, Void, Boolean>() {

                                                        @Override
                                                        protected Boolean doInBackground(Void... params) {
                                                            try {
                                                                if (!ListenerUtil.mutListener.listen(6669)) {
                                                                    userService.verifyMobileNumber(code);
                                                                }
                                                                return true;
                                                            } catch (Exception x) {
                                                                if (!ListenerUtil.mutListener.listen(6668)) {
                                                                    logger.error("Exception", x);
                                                                }
                                                            }
                                                            return false;
                                                        }

                                                        @Override
                                                        protected void onPostExecute(Boolean result) {
                                                            if (!ListenerUtil.mutListener.listen(6670)) {
                                                                showConfirmation(result ? R.string.verify_success_text : R.string.verify_failed_summary);
                                                            }
                                                        }
                                                    }.execute();
                                                }
                                            }
                                        }
                                    }
                                }
                            } else if (this.userService.getMobileLinkingState() == UserService.LinkingState_LINKED) {
                                if (!ListenerUtil.mutListener.listen(6666)) {
                                    // already linked
                                    resultText = R.string.verify_success_text;
                                }
                            } else if (this.userService.getMobileLinkingState() == UserService.LinkingState_NONE) {
                                if (!ListenerUtil.mutListener.listen(6665)) {
                                    resultText = R.string.verify_failed_not_linked;
                                }
                            }
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(6677)) {
            showConfirmation(resultText);
        }
        if (!ListenerUtil.mutListener.listen(6678)) {
            finish();
        }
    }

    private void showConfirmation(Integer resultText) {
        if (!ListenerUtil.mutListener.listen(6680)) {
            if (resultText != null) {
                @StringRes
                int resId = resultText;
                if (!ListenerUtil.mutListener.listen(6679)) {
                    Toast.makeText(getApplicationContext(), resId, Toast.LENGTH_LONG).show();
                }
            }
        }
    }
}
