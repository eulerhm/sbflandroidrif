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

import android.content.DialogInterface;
import android.os.AsyncTask;
import android.view.View;
import com.google.android.material.snackbar.Snackbar;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Iterator;
import java.util.List;
import androidx.fragment.app.FragmentManager;
import ch.threema.app.R;
import ch.threema.app.ThreemaApplication;
import ch.threema.app.dialogs.CancelableHorizontalProgressDialog;
import ch.threema.app.listeners.ConversationListener;
import ch.threema.app.managers.ListenerManager;
import ch.threema.app.managers.ServiceManager;
import ch.threema.app.services.ContactService;
import ch.threema.app.services.ConversationService;
import ch.threema.app.services.DistributionListService;
import ch.threema.app.services.GroupService;
import ch.threema.app.utils.DialogUtil;
import ch.threema.storage.models.ConversationModel;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class DeleteConversationsAsyncTask extends AsyncTask<Void, Integer, Integer> {

    private static final Logger logger = LoggerFactory.getLogger(DeleteConversationsAsyncTask.class);

    private static final String DIALOG_TAG = "dcon";

    private final FragmentManager fragmentManager;

    private final Runnable runOnCompletion;

    private final List<ConversationModel> conversationModels;

    private ContactService contactService;

    private GroupService groupService;

    private DistributionListService distributionListService;

    private ConversationService conversationService;

    private View feedbackView;

    private boolean cancelled = false;

    public DeleteConversationsAsyncTask(FragmentManager fragmentManager, List<ConversationModel> conversationModels, View feedbackView, Runnable runOnCompletion) {
        this.fragmentManager = fragmentManager;
        this.runOnCompletion = runOnCompletion;
        this.conversationModels = conversationModels;
        if (!ListenerUtil.mutListener.listen(10063)) {
            this.feedbackView = feedbackView;
        }
        ServiceManager serviceManager = ThreemaApplication.getServiceManager();
        try {
            if (!ListenerUtil.mutListener.listen(10065)) {
                this.contactService = serviceManager.getContactService();
            }
            if (!ListenerUtil.mutListener.listen(10066)) {
                this.groupService = serviceManager.getGroupService();
            }
            if (!ListenerUtil.mutListener.listen(10067)) {
                this.distributionListService = serviceManager.getDistributionListService();
            }
            if (!ListenerUtil.mutListener.listen(10068)) {
                this.conversationService = serviceManager.getConversationService();
            }
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(10064)) {
                logger.error("Exception", e);
            }
        }
    }

    @Override
    protected void onPreExecute() {
        CancelableHorizontalProgressDialog dialog = CancelableHorizontalProgressDialog.newInstance(R.string.deleting_thread, R.string.please_wait, R.string.cancel, conversationModels.size());
        if (!ListenerUtil.mutListener.listen(10070)) {
            dialog.setOnCancelListener(new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if (!ListenerUtil.mutListener.listen(10069)) {
                        cancelled = true;
                    }
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(10071)) {
            dialog.show(fragmentManager, DIALOG_TAG);
        }
    }

    @Override
    protected void onProgressUpdate(Integer... index) {
        if (!ListenerUtil.mutListener.listen(10072)) {
            DialogUtil.updateProgress(fragmentManager, DIALOG_TAG, index[0]);
        }
    }

    @Override
    protected Integer doInBackground(Void... params) {
        int i = 0;
        Iterator<ConversationModel> conversationModelIterator = conversationModels.iterator();
        if (!ListenerUtil.mutListener.listen(10080)) {
            {
                long _loopCounter88 = 0;
                while ((ListenerUtil.mutListener.listen(10079) ? (conversationModelIterator.hasNext() || !cancelled) : (conversationModelIterator.hasNext() && !cancelled))) {
                    ListenerUtil.loopListener.listen("_loopCounter88", ++_loopCounter88);
                    if (!ListenerUtil.mutListener.listen(10073)) {
                        publishProgress(++i);
                    }
                    ConversationModel conversationModel = conversationModelIterator.next();
                    if (!ListenerUtil.mutListener.listen(10074)) {
                        // remove all messages
                        conversationService.clear(conversationModel);
                    }
                    if (!ListenerUtil.mutListener.listen(10078)) {
                        if (conversationModel.isGroupConversation()) {
                            if (!ListenerUtil.mutListener.listen(10076)) {
                                groupService.leaveGroup(conversationModel.getGroup());
                            }
                            if (!ListenerUtil.mutListener.listen(10077)) {
                                groupService.remove(conversationModel.getGroup());
                            }
                        } else if (conversationModel.isDistributionListConversation()) {
                            if (!ListenerUtil.mutListener.listen(10075)) {
                                distributionListService.remove(conversationModel.getDistributionList());
                            }
                        }
                    }
                }
            }
        }
        return i;
    }

    @Override
    protected void onPostExecute(Integer count) {
        if (!ListenerUtil.mutListener.listen(10094)) {
            if ((ListenerUtil.mutListener.listen(10085) ? (count >= 0) : (ListenerUtil.mutListener.listen(10084) ? (count <= 0) : (ListenerUtil.mutListener.listen(10083) ? (count < 0) : (ListenerUtil.mutListener.listen(10082) ? (count != 0) : (ListenerUtil.mutListener.listen(10081) ? (count == 0) : (count > 0))))))) {
                if (!ListenerUtil.mutListener.listen(10086)) {
                    DialogUtil.dismissDialog(fragmentManager, DIALOG_TAG, true);
                }
                if (!ListenerUtil.mutListener.listen(10088)) {
                    ListenerManager.conversationListeners.handle(new ListenerManager.HandleListener<ConversationListener>() {

                        @Override
                        public void handle(ConversationListener listener) {
                            if (!ListenerUtil.mutListener.listen(10087)) {
                                listener.onModifiedAll();
                            }
                        }
                    });
                }
                if (!ListenerUtil.mutListener.listen(10091)) {
                    // API 19 min
                    if ((ListenerUtil.mutListener.listen(10089) ? (feedbackView != null || feedbackView.isAttachedToWindow()) : (feedbackView != null && feedbackView.isAttachedToWindow()))) {
                        if (!ListenerUtil.mutListener.listen(10090)) {
                            Snackbar.make(feedbackView, String.format(ThreemaApplication.getAppContext().getString(R.string.chat_deleted), count), Snackbar.LENGTH_SHORT).show();
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(10093)) {
                    if (runOnCompletion != null) {
                        if (!ListenerUtil.mutListener.listen(10092)) {
                            runOnCompletion.run();
                        }
                    }
                }
            }
        }
    }
}
