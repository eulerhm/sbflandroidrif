/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
 * Copyright (c) 2019-2021 Threema GmbH
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
import android.os.Bundle;
import android.widget.Toast;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ch.threema.app.R;
import ch.threema.app.ThreemaApplication;
import ch.threema.app.asynctasks.AddContactAsyncTask;
import ch.threema.app.services.LockAppService;
import ch.threema.app.utils.ConfigUtils;
import ch.threema.app.utils.HiddenChatUtil;
import ch.threema.client.ProtocolDefines;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class AppLinksActivity extends ThreemaToolbarActivity {

    private static final Logger logger = LoggerFactory.getLogger(AppLinksActivity.class);

    public void onCreate(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(1786)) {
            super.onCreate(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(1787)) {
            checkLock();
        }
    }

    @Override
    public int getLayoutResource() {
        // invisible activity
        return 0;
    }

    @Override
    protected boolean isPinLockable() {
        // we handle pin locking ourselves
        return false;
    }

    private void checkLock() {
        LockAppService lockAppService;
        try {
            lockAppService = ThreemaApplication.getServiceManager().getLockAppService();
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(1788)) {
                finish();
            }
            return;
        }
        if (!ListenerUtil.mutListener.listen(1793)) {
            if (lockAppService != null) {
                if (!ListenerUtil.mutListener.listen(1792)) {
                    if (lockAppService.isLocked()) {
                        if (!ListenerUtil.mutListener.listen(1791)) {
                            HiddenChatUtil.launchLockCheckDialog(this, preferenceService);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(1790)) {
                            handleIntent();
                        }
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(1789)) {
                    finish();
                }
            }
        }
    }

    private void handleIntent() {
        String appLinkAction = getIntent().getAction();
        final Uri appLinkData = getIntent().getData();
        if (!ListenerUtil.mutListener.listen(1803)) {
            if ((ListenerUtil.mutListener.listen(1794) ? (Intent.ACTION_VIEW.equals(appLinkAction) || appLinkData != null) : (Intent.ACTION_VIEW.equals(appLinkAction) && appLinkData != null))) {
                final String threemaId = appLinkData.getLastPathSegment();
                if (!ListenerUtil.mutListener.listen(1802)) {
                    if (threemaId != null) {
                        if (!ListenerUtil.mutListener.listen(1801)) {
                            if (threemaId.equalsIgnoreCase("compose")) {
                                Intent intent = new Intent(this, RecipientListActivity.class);
                                if (!ListenerUtil.mutListener.listen(1798)) {
                                    intent.setAction(appLinkAction);
                                }
                                if (!ListenerUtil.mutListener.listen(1799)) {
                                    intent.setData(appLinkData);
                                }
                                if (!ListenerUtil.mutListener.listen(1800)) {
                                    startActivity(intent);
                                }
                            } else if (threemaId.length() == ProtocolDefines.IDENTITY_LEN) {
                                if (!ListenerUtil.mutListener.listen(1797)) {
                                    new AddContactAsyncTask(null, null, threemaId, false, () -> {
                                        String text = appLinkData.getQueryParameter("text");
                                        Intent intent = new Intent(AppLinksActivity.this, text != null ? ComposeMessageActivity.class : ContactDetailActivity.class);
                                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                        intent.putExtra(ThreemaApplication.INTENT_DATA_CONTACT, threemaId);
                                        intent.putExtra(ThreemaApplication.INTENT_DATA_EDITFOCUS, Boolean.TRUE);
                                        if (text != null) {
                                            text = text.trim();
                                            intent.putExtra(ThreemaApplication.INTENT_DATA_TEXT, text);
                                        }
                                        startActivity(intent);
                                    }).execute();
                                }
                            } else {
                                if (!ListenerUtil.mutListener.listen(1796)) {
                                    Toast.makeText(this, R.string.invalid_input, Toast.LENGTH_LONG).show();
                                }
                            }
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(1795)) {
                            Toast.makeText(this, R.string.invalid_input, Toast.LENGTH_LONG).show();
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(1804)) {
            finish();
        }
    }

    @Override
    public void finish() {
        if (!ListenerUtil.mutListener.listen(1805)) {
            super.finish();
        }
        if (!ListenerUtil.mutListener.listen(1806)) {
            overridePendingTransition(0, 0);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (!ListenerUtil.mutListener.listen(1807)) {
            logger.debug("onActivityResult");
        }
        if (!ListenerUtil.mutListener.listen(1816)) {
            switch(requestCode) {
                case ThreemaActivity.ACTIVITY_ID_CHECK_LOCK:
                    if (!ListenerUtil.mutListener.listen(1811)) {
                        if (resultCode == RESULT_OK) {
                            if (!ListenerUtil.mutListener.listen(1810)) {
                                handleIntent();
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(1808)) {
                                Toast.makeText(this, getString(R.string.pin_locked_cannot_send), Toast.LENGTH_LONG).show();
                            }
                            if (!ListenerUtil.mutListener.listen(1809)) {
                                finish();
                            }
                        }
                    }
                    break;
                case ThreemaActivity.ACTIVITY_ID_UNLOCK_MASTER_KEY:
                    if (!ListenerUtil.mutListener.listen(1814)) {
                        if (ThreemaApplication.getMasterKey().isLocked()) {
                            if (!ListenerUtil.mutListener.listen(1813)) {
                                finish();
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(1812)) {
                                ConfigUtils.recreateActivity(this, AppLinksActivity.class, getIntent().getExtras());
                            }
                        }
                    }
                    break;
                default:
                    if (!ListenerUtil.mutListener.listen(1815)) {
                        super.onActivityResult(requestCode, resultCode, data);
                    }
            }
        }
    }
}
