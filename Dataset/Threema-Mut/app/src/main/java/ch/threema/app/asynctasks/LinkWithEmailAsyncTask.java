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
package ch.threema.app.asynctasks;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import androidx.annotation.StringRes;
import androidx.fragment.app.FragmentManager;
import ch.threema.app.R;
import ch.threema.app.ThreemaApplication;
import ch.threema.app.dialogs.GenericProgressDialog;
import ch.threema.app.managers.ServiceManager;
import ch.threema.app.services.UserService;
import ch.threema.app.utils.DialogUtil;
import ch.threema.app.utils.TestUtil;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class LinkWithEmailAsyncTask extends AsyncTask<Void, Void, String> {

    private static final Logger logger = LoggerFactory.getLogger(LinkWithEmailAsyncTask.class);

    private static final String DIALOG_TAG_PROGRESS = "lpr";

    private UserService userService;

    private final Runnable runOnCompletion;

    private final String emailAddress;

    private final FragmentManager fragmentManager;

    private final Context context;

    private static final int MODE_NONE = 0;

    private static final int MODE_LINK = 1;

    private static final int MODE_UNLINK = 2;

    private static final int MODE_CHECK = 3;

    private int linkingMode = MODE_NONE;

    public LinkWithEmailAsyncTask(Context context, FragmentManager fragmentManager, String emailAddress, Runnable runOnCompletion) {
        this.fragmentManager = fragmentManager;
        this.emailAddress = emailAddress;
        this.runOnCompletion = runOnCompletion;
        this.context = context;
        ServiceManager serviceManager = ThreemaApplication.getServiceManager();
        try {
            if (!ListenerUtil.mutListener.listen(10177)) {
                this.userService = serviceManager.getUserService();
            }
        } catch (Exception e) {
        }
    }

    @Override
    protected void onPreExecute() {
        @StringRes
        int dialogText = 0;
        if (!ListenerUtil.mutListener.listen(10186)) {
            if (TestUtil.empty(emailAddress)) {
                if (!ListenerUtil.mutListener.listen(10185)) {
                    if (userService.getEmailLinkingState() != UserService.LinkingState_NONE) {
                        if (!ListenerUtil.mutListener.listen(10183)) {
                            linkingMode = MODE_UNLINK;
                        }
                        if (!ListenerUtil.mutListener.listen(10184)) {
                            dialogText = R.string.unlinking_email;
                        }
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(10182)) {
                    if ((ListenerUtil.mutListener.listen(10178) ? (userService.getEmailLinkingState() != UserService.LinkingState_NONE || userService.getLinkedEmail().equals(emailAddress)) : (userService.getEmailLinkingState() != UserService.LinkingState_NONE && userService.getLinkedEmail().equals(emailAddress)))) {
                        if (!ListenerUtil.mutListener.listen(10181)) {
                            linkingMode = MODE_CHECK;
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(10179)) {
                            linkingMode = MODE_LINK;
                        }
                        if (!ListenerUtil.mutListener.listen(10180)) {
                            dialogText = R.string.wizard2_email_linking;
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(10193)) {
            if ((ListenerUtil.mutListener.listen(10191) ? (dialogText >= 0) : (ListenerUtil.mutListener.listen(10190) ? (dialogText <= 0) : (ListenerUtil.mutListener.listen(10189) ? (dialogText > 0) : (ListenerUtil.mutListener.listen(10188) ? (dialogText < 0) : (ListenerUtil.mutListener.listen(10187) ? (dialogText == 0) : (dialogText != 0))))))) {
                if (!ListenerUtil.mutListener.listen(10192)) {
                    GenericProgressDialog.newInstance(dialogText, R.string.please_wait).show(fragmentManager, DIALOG_TAG_PROGRESS);
                }
            }
        }
    }

    @Override
    protected String doInBackground(Void... params) {
        if (!ListenerUtil.mutListener.listen(10195)) {
            if (this.userService == null) {
                if (!ListenerUtil.mutListener.listen(10194)) {
                    logger.error("UserService not available");
                }
                return null;
            }
        }
        String resultString = null;
        if (!ListenerUtil.mutListener.listen(10202)) {
            switch(linkingMode) {
                case MODE_UNLINK:
                    try {
                        if (!ListenerUtil.mutListener.listen(10198)) {
                            userService.unlinkEmail();
                        }
                    } catch (Exception x) {
                        if (!ListenerUtil.mutListener.listen(10196)) {
                            logger.error("exception", x);
                        }
                        if (!ListenerUtil.mutListener.listen(10197)) {
                            resultString = String.format(context.getString(R.string.an_error_occurred_more), x.getMessage());
                        }
                    }
                    break;
                case MODE_CHECK:
                    if (!ListenerUtil.mutListener.listen(10199)) {
                        resultString = context.getString(R.string.email_already_linked);
                    }
                    break;
                case MODE_LINK:
                    try {
                        if (!ListenerUtil.mutListener.listen(10201)) {
                            userService.linkWithEmail(emailAddress);
                        }
                    } catch (Exception x) {
                        if (!ListenerUtil.mutListener.listen(10200)) {
                            resultString = String.format(context.getString(R.string.an_error_occurred_more), x.getMessage());
                        }
                    }
                    break;
                default:
                    break;
            }
        }
        return resultString;
    }

    @Override
    protected void onPostExecute(String resultString) {
        if (!ListenerUtil.mutListener.listen(10203)) {
            DialogUtil.dismissDialog(fragmentManager, DIALOG_TAG_PROGRESS, true);
        }
        if (!ListenerUtil.mutListener.listen(10205)) {
            if (resultString != null) {
                if (!ListenerUtil.mutListener.listen(10204)) {
                    Toast.makeText(ThreemaApplication.getAppContext(), resultString, Toast.LENGTH_LONG).show();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(10207)) {
            if (runOnCompletion != null) {
                if (!ListenerUtil.mutListener.listen(10206)) {
                    runOnCompletion.run();
                }
            }
        }
    }
}
