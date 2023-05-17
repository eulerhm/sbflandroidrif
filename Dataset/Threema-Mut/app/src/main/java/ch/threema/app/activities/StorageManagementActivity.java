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

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.format.Formatter;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import com.google.android.material.snackbar.Snackbar;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;
import androidx.appcompat.app.ActionBar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import ch.threema.app.R;
import ch.threema.app.asynctasks.DeleteIdentityAsyncTask;
import ch.threema.app.dialogs.CancelableHorizontalProgressDialog;
import ch.threema.app.dialogs.GenericAlertDialog;
import ch.threema.app.listeners.ConversationListener;
import ch.threema.app.managers.ListenerManager;
import ch.threema.app.services.ConversationService;
import ch.threema.app.services.FileService;
import ch.threema.app.services.MessageService;
import ch.threema.app.utils.DialogUtil;
import ch.threema.storage.models.AbstractMessageModel;
import ch.threema.storage.models.ConversationModel;
import ch.threema.storage.models.MessageType;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class StorageManagementActivity extends ThreemaToolbarActivity implements GenericAlertDialog.DialogClickListener, CancelableHorizontalProgressDialog.ProgressDialogClickListener {

    private static final Logger logger = LoggerFactory.getLogger(StorageManagementActivity.class);

    private static final String DELETE_CONFIRM_TAG = "delconf";

    private static final String DELETE_PROGRESS_TAG = "delprog";

    private static final String DELETE_MESSAGES_CONFIRM_TAG = "delmsgsconf";

    private static final String DELETE_MESSAGES_PROGRESS_TAG = "delmsgs";

    private static final String DIALOG_TAG_DELETE_ID = "delid";

    private static final String DIALOG_TAG_REALLY_DELETE = "rlydelete";

    private FileService fileService;

    private MessageService messageService;

    private ConversationService conversationService;

    private TextView totalView, usageView, freeView, messageView, inuseView;

    private Spinner timeSpinner, messageTimeSpinner;

    private Button deleteButton, messageDeleteButton;

    private ProgressBar progressBar;

    private boolean isCancelled, isMessageDeleteCancelled;

    private int selectedSpinnerItem, selectedMessageSpinnerItem;

    private FrameLayout storageFull, storageThreema, storageEmpty;

    private CoordinatorLayout coordinatorLayout;

    private int[] dayValues = { 730, 365, 183, 92, 31, 7, 0 };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(6721)) {
            super.onCreate(savedInstanceState);
        }
        ActionBar actionBar = getSupportActionBar();
        if (!ListenerUtil.mutListener.listen(6724)) {
            if (actionBar != null) {
                if (!ListenerUtil.mutListener.listen(6722)) {
                    actionBar.setDisplayHomeAsUpEnabled(true);
                }
                if (!ListenerUtil.mutListener.listen(6723)) {
                    actionBar.setTitle(R.string.storage_management);
                }
            }
        }
        try {
            if (!ListenerUtil.mutListener.listen(6727)) {
                this.fileService = serviceManager.getFileService();
            }
            if (!ListenerUtil.mutListener.listen(6728)) {
                this.messageService = serviceManager.getMessageService();
            }
            if (!ListenerUtil.mutListener.listen(6729)) {
                this.conversationService = serviceManager.getConversationService();
            }
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(6725)) {
                logger.error("Exception", e);
            }
            if (!ListenerUtil.mutListener.listen(6726)) {
                finish();
            }
            return;
        }
        if (!ListenerUtil.mutListener.listen(6730)) {
            coordinatorLayout = findViewById(R.id.content);
        }
        if (!ListenerUtil.mutListener.listen(6731)) {
            totalView = findViewById(R.id.total_view);
        }
        if (!ListenerUtil.mutListener.listen(6732)) {
            usageView = findViewById(R.id.usage_view);
        }
        if (!ListenerUtil.mutListener.listen(6733)) {
            freeView = findViewById(R.id.free_view);
        }
        if (!ListenerUtil.mutListener.listen(6734)) {
            inuseView = findViewById(R.id.in_use_view);
        }
        if (!ListenerUtil.mutListener.listen(6735)) {
            messageView = findViewById(R.id.num_messages_view);
        }
        if (!ListenerUtil.mutListener.listen(6736)) {
            timeSpinner = findViewById(R.id.time_spinner);
        }
        if (!ListenerUtil.mutListener.listen(6737)) {
            messageTimeSpinner = findViewById(R.id.time_spinner_messages);
        }
        if (!ListenerUtil.mutListener.listen(6738)) {
            deleteButton = findViewById(R.id.delete_button);
        }
        if (!ListenerUtil.mutListener.listen(6739)) {
            messageDeleteButton = findViewById(R.id.delete_button_messages);
        }
        if (!ListenerUtil.mutListener.listen(6740)) {
            storageFull = findViewById(R.id.storage_full);
        }
        if (!ListenerUtil.mutListener.listen(6741)) {
            storageThreema = findViewById(R.id.storage_threema);
        }
        if (!ListenerUtil.mutListener.listen(6742)) {
            storageEmpty = findViewById(R.id.storage_empty);
        }
        if (!ListenerUtil.mutListener.listen(6743)) {
            progressBar = findViewById(R.id.progressbar);
        }
        if (!ListenerUtil.mutListener.listen(6744)) {
            selectedSpinnerItem = 0;
        }
        if (!ListenerUtil.mutListener.listen(6745)) {
            selectedMessageSpinnerItem = 0;
        }
        if (!ListenerUtil.mutListener.listen(6748)) {
            if (deleteButton == null) {
                if (!ListenerUtil.mutListener.listen(6746)) {
                    logger.info("deleteButton is null");
                }
                if (!ListenerUtil.mutListener.listen(6747)) {
                    finish();
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(6750)) {
            deleteButton.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    if (!ListenerUtil.mutListener.listen(6749)) {
                        GenericAlertDialog.newInstance(R.string.delete_data, R.string.delete_date_confirm_message, R.string.delete_data, R.string.cancel).show(getSupportFragmentManager(), DELETE_CONFIRM_TAG);
                    }
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(6752)) {
            messageDeleteButton.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    if (!ListenerUtil.mutListener.listen(6751)) {
                        GenericAlertDialog.newInstance(R.string.delete_message, R.string.really_delete_messages, R.string.delete_message, R.string.cancel).show(getSupportFragmentManager(), DELETE_MESSAGES_CONFIRM_TAG);
                    }
                }
            });
        }
        Button deleteAllButton = findViewById(R.id.delete_everything_button);
        if (!ListenerUtil.mutListener.listen(6754)) {
            deleteAllButton.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    if (!ListenerUtil.mutListener.listen(6753)) {
                        GenericAlertDialog.newInstance(R.string.delete_id_title, R.string.delete_id_message, R.string.delete_everything, R.string.cancel).show(getSupportFragmentManager(), DIALOG_TAG_DELETE_ID);
                    }
                }
            });
        }
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.storagemanager_timeout, android.R.layout.simple_spinner_item);
        if (!ListenerUtil.mutListener.listen(6755)) {
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        }
        if (!ListenerUtil.mutListener.listen(6756)) {
            timeSpinner.setAdapter(adapter);
        }
        if (!ListenerUtil.mutListener.listen(6759)) {
            timeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    if (!ListenerUtil.mutListener.listen(6757)) {
                        selectedSpinnerItem = position;
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                    if (!ListenerUtil.mutListener.listen(6758)) {
                        selectedSpinnerItem = 0;
                    }
                }
            });
        }
        ArrayAdapter<CharSequence> messageCleanupAdapter = ArrayAdapter.createFromResource(this, R.array.storagemanager_timeout, android.R.layout.simple_spinner_item);
        if (!ListenerUtil.mutListener.listen(6760)) {
            messageCleanupAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        }
        if (!ListenerUtil.mutListener.listen(6761)) {
            messageTimeSpinner.setAdapter(messageCleanupAdapter);
        }
        if (!ListenerUtil.mutListener.listen(6764)) {
            messageTimeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    if (!ListenerUtil.mutListener.listen(6762)) {
                        selectedMessageSpinnerItem = position;
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                    if (!ListenerUtil.mutListener.listen(6763)) {
                        selectedMessageSpinnerItem = 0;
                    }
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(6766)) {
            storageFull.post(new Runnable() {

                @Override
                public void run() {
                    if (!ListenerUtil.mutListener.listen(6765)) {
                        updateStorageDisplay();
                    }
                }
            });
        }
    }

    @SuppressLint("StaticFieldLeak")
    private void updateStorageDisplay() {
        if (!ListenerUtil.mutListener.listen(6811)) {
            new AsyncTask<Void, Void, Void>() {

                long total, usage, free, messages;

                @Override
                protected void onPreExecute() {
                    if (!ListenerUtil.mutListener.listen(6767)) {
                        progressBar.setVisibility(View.VISIBLE);
                    }
                }

                @Override
                protected Void doInBackground(Void... params) {
                    if (!ListenerUtil.mutListener.listen(6768)) {
                        total = fileService.getInternalStorageSize();
                    }
                    if (!ListenerUtil.mutListener.listen(6769)) {
                        usage = fileService.getInternalStorageUsage();
                    }
                    if (!ListenerUtil.mutListener.listen(6770)) {
                        free = fileService.getInternalStorageFree();
                    }
                    if (!ListenerUtil.mutListener.listen(6771)) {
                        messages = messageService.getTotalMessageCount();
                    }
                    return null;
                }

                @Override
                protected void onPostExecute(Void result) {
                    if (!ListenerUtil.mutListener.listen(6772)) {
                        messageView.setText(String.valueOf(messages));
                    }
                    if (!ListenerUtil.mutListener.listen(6773)) {
                        progressBar.setVisibility(View.GONE);
                    }
                    if (!ListenerUtil.mutListener.listen(6774)) {
                        totalView.setText(Formatter.formatFileSize(StorageManagementActivity.this, total));
                    }
                    if (!ListenerUtil.mutListener.listen(6775)) {
                        usageView.setText(Formatter.formatFileSize(StorageManagementActivity.this, usage));
                    }
                    if (!ListenerUtil.mutListener.listen(6776)) {
                        freeView.setText(Formatter.formatFileSize(StorageManagementActivity.this, free));
                    }
                    if (!ListenerUtil.mutListener.listen(6810)) {
                        if ((ListenerUtil.mutListener.listen(6781) ? (total >= 0) : (ListenerUtil.mutListener.listen(6780) ? (total <= 0) : (ListenerUtil.mutListener.listen(6779) ? (total < 0) : (ListenerUtil.mutListener.listen(6778) ? (total != 0) : (ListenerUtil.mutListener.listen(6777) ? (total == 0) : (total > 0))))))) {
                            if (!ListenerUtil.mutListener.listen(6790)) {
                                inuseView.setText(Formatter.formatFileSize(StorageManagementActivity.this, (ListenerUtil.mutListener.listen(6789) ? (total % free) : (ListenerUtil.mutListener.listen(6788) ? (total / free) : (ListenerUtil.mutListener.listen(6787) ? (total * free) : (ListenerUtil.mutListener.listen(6786) ? (total + free) : (total - free)))))));
                            }
                            int fullWidth = storageFull.getWidth();
                            if (!ListenerUtil.mutListener.listen(6799)) {
                                storageThreema.setLayoutParams(new FrameLayout.LayoutParams((int) ((ListenerUtil.mutListener.listen(6798) ? ((ListenerUtil.mutListener.listen(6794) ? (fullWidth % usage) : (ListenerUtil.mutListener.listen(6793) ? (fullWidth / usage) : (ListenerUtil.mutListener.listen(6792) ? (fullWidth - usage) : (ListenerUtil.mutListener.listen(6791) ? (fullWidth + usage) : (fullWidth * usage))))) % total) : (ListenerUtil.mutListener.listen(6797) ? ((ListenerUtil.mutListener.listen(6794) ? (fullWidth % usage) : (ListenerUtil.mutListener.listen(6793) ? (fullWidth / usage) : (ListenerUtil.mutListener.listen(6792) ? (fullWidth - usage) : (ListenerUtil.mutListener.listen(6791) ? (fullWidth + usage) : (fullWidth * usage))))) * total) : (ListenerUtil.mutListener.listen(6796) ? ((ListenerUtil.mutListener.listen(6794) ? (fullWidth % usage) : (ListenerUtil.mutListener.listen(6793) ? (fullWidth / usage) : (ListenerUtil.mutListener.listen(6792) ? (fullWidth - usage) : (ListenerUtil.mutListener.listen(6791) ? (fullWidth + usage) : (fullWidth * usage))))) - total) : (ListenerUtil.mutListener.listen(6795) ? ((ListenerUtil.mutListener.listen(6794) ? (fullWidth % usage) : (ListenerUtil.mutListener.listen(6793) ? (fullWidth / usage) : (ListenerUtil.mutListener.listen(6792) ? (fullWidth - usage) : (ListenerUtil.mutListener.listen(6791) ? (fullWidth + usage) : (fullWidth * usage))))) + total) : ((ListenerUtil.mutListener.listen(6794) ? (fullWidth % usage) : (ListenerUtil.mutListener.listen(6793) ? (fullWidth / usage) : (ListenerUtil.mutListener.listen(6792) ? (fullWidth - usage) : (ListenerUtil.mutListener.listen(6791) ? (fullWidth + usage) : (fullWidth * usage))))) / total)))))), FrameLayout.LayoutParams.MATCH_PARENT));
                            }
                            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams((int) ((ListenerUtil.mutListener.listen(6807) ? ((ListenerUtil.mutListener.listen(6803) ? (fullWidth % free) : (ListenerUtil.mutListener.listen(6802) ? (fullWidth / free) : (ListenerUtil.mutListener.listen(6801) ? (fullWidth - free) : (ListenerUtil.mutListener.listen(6800) ? (fullWidth + free) : (fullWidth * free))))) % total) : (ListenerUtil.mutListener.listen(6806) ? ((ListenerUtil.mutListener.listen(6803) ? (fullWidth % free) : (ListenerUtil.mutListener.listen(6802) ? (fullWidth / free) : (ListenerUtil.mutListener.listen(6801) ? (fullWidth - free) : (ListenerUtil.mutListener.listen(6800) ? (fullWidth + free) : (fullWidth * free))))) * total) : (ListenerUtil.mutListener.listen(6805) ? ((ListenerUtil.mutListener.listen(6803) ? (fullWidth % free) : (ListenerUtil.mutListener.listen(6802) ? (fullWidth / free) : (ListenerUtil.mutListener.listen(6801) ? (fullWidth - free) : (ListenerUtil.mutListener.listen(6800) ? (fullWidth + free) : (fullWidth * free))))) - total) : (ListenerUtil.mutListener.listen(6804) ? ((ListenerUtil.mutListener.listen(6803) ? (fullWidth % free) : (ListenerUtil.mutListener.listen(6802) ? (fullWidth / free) : (ListenerUtil.mutListener.listen(6801) ? (fullWidth - free) : (ListenerUtil.mutListener.listen(6800) ? (fullWidth + free) : (fullWidth * free))))) + total) : ((ListenerUtil.mutListener.listen(6803) ? (fullWidth % free) : (ListenerUtil.mutListener.listen(6802) ? (fullWidth / free) : (ListenerUtil.mutListener.listen(6801) ? (fullWidth - free) : (ListenerUtil.mutListener.listen(6800) ? (fullWidth + free) : (fullWidth * free))))) / total)))))), FrameLayout.LayoutParams.MATCH_PARENT);
                            if (!ListenerUtil.mutListener.listen(6808)) {
                                params.gravity = Gravity.RIGHT;
                            }
                            if (!ListenerUtil.mutListener.listen(6809)) {
                                storageEmpty.setLayoutParams(params);
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(6782)) {
                                inuseView.setText(Formatter.formatFileSize(StorageManagementActivity.this, 0));
                            }
                            if (!ListenerUtil.mutListener.listen(6783)) {
                                storageFull.setVisibility(View.GONE);
                            }
                            if (!ListenerUtil.mutListener.listen(6784)) {
                                storageThreema.setVisibility(View.GONE);
                            }
                            if (!ListenerUtil.mutListener.listen(6785)) {
                                storageEmpty.setVisibility(View.GONE);
                            }
                        }
                    }
                }
            }.execute();
        }
    }

    @Override
    public int getLayoutResource() {
        return R.layout.activity_storagemanagement;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (!ListenerUtil.mutListener.listen(6813)) {
            switch(item.getItemId()) {
                case android.R.id.home:
                    if (!ListenerUtil.mutListener.listen(6812)) {
                        finish();
                    }
                    return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     *  TODO: replace with Date.before
     *
     *  @param d1
     *  @param d2
     *  @return
     */
    private long getDifferenceDays(Date d1, Date d2) {
        if (!ListenerUtil.mutListener.listen(6819)) {
            if ((ListenerUtil.mutListener.listen(6814) ? (d1 != null || d2 != null) : (d1 != null && d2 != null))) {
                long diff = (ListenerUtil.mutListener.listen(6818) ? (d2.getTime() % d1.getTime()) : (ListenerUtil.mutListener.listen(6817) ? (d2.getTime() / d1.getTime()) : (ListenerUtil.mutListener.listen(6816) ? (d2.getTime() * d1.getTime()) : (ListenerUtil.mutListener.listen(6815) ? (d2.getTime() + d1.getTime()) : (d2.getTime() - d1.getTime())))));
                return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
            }
        }
        return 0;
    }

    @SuppressLint("StaticFieldLeak")
    private boolean deleteMessages(final int days) {
        final Date today = new Date();
        if (!ListenerUtil.mutListener.listen(6854)) {
            new AsyncTask<Void, Integer, Void>() {

                int delCount = 0;

                @Override
                protected void onPreExecute() {
                    if (!ListenerUtil.mutListener.listen(6820)) {
                        isMessageDeleteCancelled = false;
                    }
                    if (!ListenerUtil.mutListener.listen(6821)) {
                        CancelableHorizontalProgressDialog.newInstance(R.string.delete_message, 0, R.string.cancel, 100).show(getSupportFragmentManager(), DELETE_MESSAGES_PROGRESS_TAG);
                    }
                }

                @Override
                protected void onProgressUpdate(Integer... values) {
                    if (!ListenerUtil.mutListener.listen(6822)) {
                        DialogUtil.updateProgress(getSupportFragmentManager(), DELETE_MESSAGES_PROGRESS_TAG, values[0]);
                    }
                }

                @Override
                protected Void doInBackground(Void... params) {
                    final List<ConversationModel> conversations = new CopyOnWriteArrayList<>(conversationService.getAll(true));
                    final int numConversations = conversations.size();
                    int i = 0;
                    if (!ListenerUtil.mutListener.listen(6847)) {
                        {
                            long _loopCounter57 = 0;
                            for (Iterator<ConversationModel> iterator = conversations.iterator(); iterator.hasNext(); ) {
                                ListenerUtil.loopListener.listen("_loopCounter57", ++_loopCounter57);
                                ConversationModel conversationModel = iterator.next();
                                if (!ListenerUtil.mutListener.listen(6823)) {
                                    if (isMessageDeleteCancelled) {
                                        // cancel task if aborted by user
                                        break;
                                    }
                                }
                                if (!ListenerUtil.mutListener.listen(6832)) {
                                    publishProgress((ListenerUtil.mutListener.listen(6831) ? ((ListenerUtil.mutListener.listen(6827) ? (i++ % 100) : (ListenerUtil.mutListener.listen(6826) ? (i++ / 100) : (ListenerUtil.mutListener.listen(6825) ? (i++ - 100) : (ListenerUtil.mutListener.listen(6824) ? (i++ + 100) : (i++ * 100))))) % numConversations) : (ListenerUtil.mutListener.listen(6830) ? ((ListenerUtil.mutListener.listen(6827) ? (i++ % 100) : (ListenerUtil.mutListener.listen(6826) ? (i++ / 100) : (ListenerUtil.mutListener.listen(6825) ? (i++ - 100) : (ListenerUtil.mutListener.listen(6824) ? (i++ + 100) : (i++ * 100))))) * numConversations) : (ListenerUtil.mutListener.listen(6829) ? ((ListenerUtil.mutListener.listen(6827) ? (i++ % 100) : (ListenerUtil.mutListener.listen(6826) ? (i++ / 100) : (ListenerUtil.mutListener.listen(6825) ? (i++ - 100) : (ListenerUtil.mutListener.listen(6824) ? (i++ + 100) : (i++ * 100))))) - numConversations) : (ListenerUtil.mutListener.listen(6828) ? ((ListenerUtil.mutListener.listen(6827) ? (i++ % 100) : (ListenerUtil.mutListener.listen(6826) ? (i++ / 100) : (ListenerUtil.mutListener.listen(6825) ? (i++ - 100) : (ListenerUtil.mutListener.listen(6824) ? (i++ + 100) : (i++ * 100))))) + numConversations) : ((ListenerUtil.mutListener.listen(6827) ? (i++ % 100) : (ListenerUtil.mutListener.listen(6826) ? (i++ / 100) : (ListenerUtil.mutListener.listen(6825) ? (i++ - 100) : (ListenerUtil.mutListener.listen(6824) ? (i++ + 100) : (i++ * 100))))) / numConversations))))));
                                }
                                final List<AbstractMessageModel> messageModels = messageService.getMessagesForReceiver(conversationModel.getReceiver(), null);
                                if (!ListenerUtil.mutListener.listen(6846)) {
                                    {
                                        long _loopCounter56 = 0;
                                        for (AbstractMessageModel messageModel : messageModels) {
                                            ListenerUtil.loopListener.listen("_loopCounter56", ++_loopCounter56);
                                            if (!ListenerUtil.mutListener.listen(6833)) {
                                                if (isMessageDeleteCancelled) {
                                                    // cancel task if aborted by user
                                                    break;
                                                }
                                            }
                                            Date postedDate = messageModel.getPostedAt();
                                            if (!ListenerUtil.mutListener.listen(6835)) {
                                                if (postedDate == null) {
                                                    if (!ListenerUtil.mutListener.listen(6834)) {
                                                        postedDate = messageModel.getCreatedAt();
                                                    }
                                                }
                                            }
                                            if (!ListenerUtil.mutListener.listen(6845)) {
                                                if ((ListenerUtil.mutListener.listen(6842) ? (days == 0 && ((ListenerUtil.mutListener.listen(6841) ? (postedDate != null || (ListenerUtil.mutListener.listen(6840) ? (getDifferenceDays(postedDate, today) >= days) : (ListenerUtil.mutListener.listen(6839) ? (getDifferenceDays(postedDate, today) <= days) : (ListenerUtil.mutListener.listen(6838) ? (getDifferenceDays(postedDate, today) < days) : (ListenerUtil.mutListener.listen(6837) ? (getDifferenceDays(postedDate, today) != days) : (ListenerUtil.mutListener.listen(6836) ? (getDifferenceDays(postedDate, today) == days) : (getDifferenceDays(postedDate, today) > days))))))) : (postedDate != null && (ListenerUtil.mutListener.listen(6840) ? (getDifferenceDays(postedDate, today) >= days) : (ListenerUtil.mutListener.listen(6839) ? (getDifferenceDays(postedDate, today) <= days) : (ListenerUtil.mutListener.listen(6838) ? (getDifferenceDays(postedDate, today) < days) : (ListenerUtil.mutListener.listen(6837) ? (getDifferenceDays(postedDate, today) != days) : (ListenerUtil.mutListener.listen(6836) ? (getDifferenceDays(postedDate, today) == days) : (getDifferenceDays(postedDate, today) > days)))))))))) : (days == 0 || ((ListenerUtil.mutListener.listen(6841) ? (postedDate != null || (ListenerUtil.mutListener.listen(6840) ? (getDifferenceDays(postedDate, today) >= days) : (ListenerUtil.mutListener.listen(6839) ? (getDifferenceDays(postedDate, today) <= days) : (ListenerUtil.mutListener.listen(6838) ? (getDifferenceDays(postedDate, today) < days) : (ListenerUtil.mutListener.listen(6837) ? (getDifferenceDays(postedDate, today) != days) : (ListenerUtil.mutListener.listen(6836) ? (getDifferenceDays(postedDate, today) == days) : (getDifferenceDays(postedDate, today) > days))))))) : (postedDate != null && (ListenerUtil.mutListener.listen(6840) ? (getDifferenceDays(postedDate, today) >= days) : (ListenerUtil.mutListener.listen(6839) ? (getDifferenceDays(postedDate, today) <= days) : (ListenerUtil.mutListener.listen(6838) ? (getDifferenceDays(postedDate, today) < days) : (ListenerUtil.mutListener.listen(6837) ? (getDifferenceDays(postedDate, today) != days) : (ListenerUtil.mutListener.listen(6836) ? (getDifferenceDays(postedDate, today) == days) : (getDifferenceDays(postedDate, today) > days)))))))))))) {
                                                    if (!ListenerUtil.mutListener.listen(6843)) {
                                                        messageService.remove(messageModel, true);
                                                    }
                                                    if (!ListenerUtil.mutListener.listen(6844)) {
                                                        delCount++;
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    return null;
                }

                @Override
                protected void onPostExecute(Void result) {
                    if (!ListenerUtil.mutListener.listen(6848)) {
                        DialogUtil.dismissDialog(getSupportFragmentManager(), DELETE_MESSAGES_PROGRESS_TAG, true);
                    }
                    if (!ListenerUtil.mutListener.listen(6849)) {
                        Snackbar.make(coordinatorLayout, String.valueOf(delCount) + " " + getString(R.string.message_deleted), Snackbar.LENGTH_LONG).show();
                    }
                    if (!ListenerUtil.mutListener.listen(6850)) {
                        updateStorageDisplay();
                    }
                    if (!ListenerUtil.mutListener.listen(6851)) {
                        conversationService.reset();
                    }
                    if (!ListenerUtil.mutListener.listen(6853)) {
                        ListenerManager.conversationListeners.handle(new ListenerManager.HandleListener<ConversationListener>() {

                            @Override
                            public void handle(ConversationListener listener) {
                                if (!ListenerUtil.mutListener.listen(6852)) {
                                    listener.onModifiedAll();
                                }
                            }
                        });
                    }
                }
            }.execute();
        }
        return false;
    }

    @SuppressLint("StaticFieldLeak")
    private boolean deleteMediaFiles(final int days) {
        final Date today = new Date();
        final MessageService.MessageFilter messageFilter = new MessageService.MessageFilter() {

            @Override
            public long getPageSize() {
                return 0;
            }

            @Override
            public Integer getPageReferenceId() {
                return null;
            }

            @Override
            public boolean withStatusMessages() {
                return false;
            }

            @Override
            public boolean withUnsaved() {
                return true;
            }

            @Override
            public boolean onlyUnread() {
                return false;
            }

            @Override
            public boolean onlyDownloaded() {
                return true;
            }

            @Override
            public MessageType[] types() {
                return new MessageType[] { MessageType.IMAGE, MessageType.VIDEO, MessageType.VOICEMESSAGE, MessageType.FILE };
            }

            @Override
            public int[] contentTypes() {
                return null;
            }
        };
        if (!ListenerUtil.mutListener.listen(6889)) {
            new AsyncTask<Void, Integer, Void>() {

                int delCount = 0;

                @Override
                protected void onPreExecute() {
                    if (!ListenerUtil.mutListener.listen(6855)) {
                        isCancelled = false;
                    }
                    if (!ListenerUtil.mutListener.listen(6856)) {
                        CancelableHorizontalProgressDialog.newInstance(R.string.delete_data, 0, R.string.cancel, 100).show(getSupportFragmentManager(), DELETE_PROGRESS_TAG);
                    }
                }

                @Override
                protected void onProgressUpdate(Integer... values) {
                    if (!ListenerUtil.mutListener.listen(6857)) {
                        DialogUtil.updateProgress(getSupportFragmentManager(), DELETE_PROGRESS_TAG, values[0]);
                    }
                }

                @Override
                protected Void doInBackground(Void... params) {
                    final List<ConversationModel> conversations = new ArrayList<>(conversationService.getAll(true));
                    final int numConversations = conversations.size();
                    int i = 0;
                    if (!ListenerUtil.mutListener.listen(6882)) {
                        {
                            long _loopCounter59 = 0;
                            for (ConversationModel conversationModel : conversations) {
                                ListenerUtil.loopListener.listen("_loopCounter59", ++_loopCounter59);
                                if (!ListenerUtil.mutListener.listen(6858)) {
                                    if (isCancelled) {
                                        // cancel task if aborted by user
                                        break;
                                    }
                                }
                                if (!ListenerUtil.mutListener.listen(6867)) {
                                    publishProgress((ListenerUtil.mutListener.listen(6866) ? ((ListenerUtil.mutListener.listen(6862) ? (i++ % 100) : (ListenerUtil.mutListener.listen(6861) ? (i++ / 100) : (ListenerUtil.mutListener.listen(6860) ? (i++ - 100) : (ListenerUtil.mutListener.listen(6859) ? (i++ + 100) : (i++ * 100))))) % numConversations) : (ListenerUtil.mutListener.listen(6865) ? ((ListenerUtil.mutListener.listen(6862) ? (i++ % 100) : (ListenerUtil.mutListener.listen(6861) ? (i++ / 100) : (ListenerUtil.mutListener.listen(6860) ? (i++ - 100) : (ListenerUtil.mutListener.listen(6859) ? (i++ + 100) : (i++ * 100))))) * numConversations) : (ListenerUtil.mutListener.listen(6864) ? ((ListenerUtil.mutListener.listen(6862) ? (i++ % 100) : (ListenerUtil.mutListener.listen(6861) ? (i++ / 100) : (ListenerUtil.mutListener.listen(6860) ? (i++ - 100) : (ListenerUtil.mutListener.listen(6859) ? (i++ + 100) : (i++ * 100))))) - numConversations) : (ListenerUtil.mutListener.listen(6863) ? ((ListenerUtil.mutListener.listen(6862) ? (i++ % 100) : (ListenerUtil.mutListener.listen(6861) ? (i++ / 100) : (ListenerUtil.mutListener.listen(6860) ? (i++ - 100) : (ListenerUtil.mutListener.listen(6859) ? (i++ + 100) : (i++ * 100))))) + numConversations) : ((ListenerUtil.mutListener.listen(6862) ? (i++ % 100) : (ListenerUtil.mutListener.listen(6861) ? (i++ / 100) : (ListenerUtil.mutListener.listen(6860) ? (i++ - 100) : (ListenerUtil.mutListener.listen(6859) ? (i++ + 100) : (i++ * 100))))) / numConversations))))));
                                }
                                final List<AbstractMessageModel> messageModels = messageService.getMessagesForReceiver(conversationModel.getReceiver(), messageFilter);
                                if (!ListenerUtil.mutListener.listen(6881)) {
                                    {
                                        long _loopCounter58 = 0;
                                        for (AbstractMessageModel messageModel : messageModels) {
                                            ListenerUtil.loopListener.listen("_loopCounter58", ++_loopCounter58);
                                            if (!ListenerUtil.mutListener.listen(6868)) {
                                                if (isCancelled) {
                                                    // cancel task if aborted by user
                                                    break;
                                                }
                                            }
                                            Date postedDate = messageModel.getPostedAt();
                                            if (!ListenerUtil.mutListener.listen(6870)) {
                                                if (postedDate == null) {
                                                    if (!ListenerUtil.mutListener.listen(6869)) {
                                                        postedDate = messageModel.getCreatedAt();
                                                    }
                                                }
                                            }
                                            if (!ListenerUtil.mutListener.listen(6880)) {
                                                if ((ListenerUtil.mutListener.listen(6877) ? (days == 0 && ((ListenerUtil.mutListener.listen(6876) ? (postedDate != null || (ListenerUtil.mutListener.listen(6875) ? (getDifferenceDays(postedDate, today) >= days) : (ListenerUtil.mutListener.listen(6874) ? (getDifferenceDays(postedDate, today) <= days) : (ListenerUtil.mutListener.listen(6873) ? (getDifferenceDays(postedDate, today) < days) : (ListenerUtil.mutListener.listen(6872) ? (getDifferenceDays(postedDate, today) != days) : (ListenerUtil.mutListener.listen(6871) ? (getDifferenceDays(postedDate, today) == days) : (getDifferenceDays(postedDate, today) > days))))))) : (postedDate != null && (ListenerUtil.mutListener.listen(6875) ? (getDifferenceDays(postedDate, today) >= days) : (ListenerUtil.mutListener.listen(6874) ? (getDifferenceDays(postedDate, today) <= days) : (ListenerUtil.mutListener.listen(6873) ? (getDifferenceDays(postedDate, today) < days) : (ListenerUtil.mutListener.listen(6872) ? (getDifferenceDays(postedDate, today) != days) : (ListenerUtil.mutListener.listen(6871) ? (getDifferenceDays(postedDate, today) == days) : (getDifferenceDays(postedDate, today) > days)))))))))) : (days == 0 || ((ListenerUtil.mutListener.listen(6876) ? (postedDate != null || (ListenerUtil.mutListener.listen(6875) ? (getDifferenceDays(postedDate, today) >= days) : (ListenerUtil.mutListener.listen(6874) ? (getDifferenceDays(postedDate, today) <= days) : (ListenerUtil.mutListener.listen(6873) ? (getDifferenceDays(postedDate, today) < days) : (ListenerUtil.mutListener.listen(6872) ? (getDifferenceDays(postedDate, today) != days) : (ListenerUtil.mutListener.listen(6871) ? (getDifferenceDays(postedDate, today) == days) : (getDifferenceDays(postedDate, today) > days))))))) : (postedDate != null && (ListenerUtil.mutListener.listen(6875) ? (getDifferenceDays(postedDate, today) >= days) : (ListenerUtil.mutListener.listen(6874) ? (getDifferenceDays(postedDate, today) <= days) : (ListenerUtil.mutListener.listen(6873) ? (getDifferenceDays(postedDate, today) < days) : (ListenerUtil.mutListener.listen(6872) ? (getDifferenceDays(postedDate, today) != days) : (ListenerUtil.mutListener.listen(6871) ? (getDifferenceDays(postedDate, today) == days) : (getDifferenceDays(postedDate, today) > days)))))))))))) {
                                                    if (!ListenerUtil.mutListener.listen(6879)) {
                                                        if (fileService.removeMessageFiles(messageModel, false)) {
                                                            if (!ListenerUtil.mutListener.listen(6878)) {
                                                                delCount++;
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    return null;
                }

                @Override
                protected void onPostExecute(Void result) {
                    if (!ListenerUtil.mutListener.listen(6883)) {
                        DialogUtil.dismissDialog(getSupportFragmentManager(), DELETE_PROGRESS_TAG, true);
                    }
                    if (!ListenerUtil.mutListener.listen(6884)) {
                        Snackbar.make(coordinatorLayout, String.format(getString(R.string.media_files_deleted), delCount), Snackbar.LENGTH_LONG).show();
                    }
                    if (!ListenerUtil.mutListener.listen(6885)) {
                        updateStorageDisplay();
                    }
                    if (!ListenerUtil.mutListener.listen(6886)) {
                        conversationService.reset();
                    }
                    if (!ListenerUtil.mutListener.listen(6888)) {
                        ListenerManager.conversationListeners.handle(new ListenerManager.HandleListener<ConversationListener>() {

                            @Override
                            public void handle(ConversationListener listener) {
                                if (!ListenerUtil.mutListener.listen(6887)) {
                                    listener.onModifiedAll();
                                }
                            }
                        });
                    }
                }
            }.execute();
        }
        return false;
    }

    @Override
    public void onYes(String tag, Object data) {
        if (!ListenerUtil.mutListener.listen(6896)) {
            if (tag.equals(DELETE_CONFIRM_TAG)) {
                if (!ListenerUtil.mutListener.listen(6895)) {
                    deleteMediaFiles(dayValues[selectedSpinnerItem]);
                }
            } else if (tag.equals(DELETE_MESSAGES_CONFIRM_TAG)) {
                if (!ListenerUtil.mutListener.listen(6894)) {
                    deleteMessages(dayValues[selectedMessageSpinnerItem]);
                }
            } else if (DIALOG_TAG_DELETE_ID.equals(tag)) {
                if (!ListenerUtil.mutListener.listen(6893)) {
                    GenericAlertDialog.newInstance(R.string.delete_id_title, R.string.delete_id_message2, R.string.delete_everything, R.string.cancel).show(getSupportFragmentManager(), DIALOG_TAG_REALLY_DELETE);
                }
            } else if (DIALOG_TAG_REALLY_DELETE.equals(tag)) {
                if (!ListenerUtil.mutListener.listen(6892)) {
                    new DeleteIdentityAsyncTask(getSupportFragmentManager(), new Runnable() {

                        @Override
                        public void run() {
                            if (!ListenerUtil.mutListener.listen(6890)) {
                                finishAffinity();
                            }
                            if (!ListenerUtil.mutListener.listen(6891)) {
                                System.exit(0);
                            }
                        }
                    }).execute();
                }
            }
        }
    }

    @Override
    public void onNo(String tag, Object data) {
    }

    @Override
    public void onCancel(String tag, Object object) {
        if (!ListenerUtil.mutListener.listen(6899)) {
            if (tag.equals(DELETE_PROGRESS_TAG)) {
                if (!ListenerUtil.mutListener.listen(6898)) {
                    isCancelled = true;
                }
            } else if (tag.equals(DELETE_MESSAGES_PROGRESS_TAG)) {
                if (!ListenerUtil.mutListener.listen(6897)) {
                    isMessageDeleteCancelled = true;
                }
            }
        }
    }
}
