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
package ch.threema.app.preference;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Date;
import androidx.annotation.Nullable;
import androidx.annotation.UiThread;
import androidx.annotation.WorkerThread;
import androidx.preference.Preference;
import ch.threema.app.R;
import ch.threema.app.ThreemaApplication;
import ch.threema.app.exceptions.EntryAlreadyExistsException;
import ch.threema.app.exceptions.InvalidEntryException;
import ch.threema.app.exceptions.PolicyViolationException;
import ch.threema.app.managers.ServiceManager;
import ch.threema.app.messagereceiver.ContactMessageReceiver;
import ch.threema.app.services.ContactService;
import ch.threema.app.services.MessageService;
import ch.threema.app.services.PreferenceService;
import ch.threema.app.services.UserService;
import ch.threema.app.utils.TestUtil;
import ch.threema.client.BoxTextMessage;
import ch.threema.client.MessageId;
import ch.threema.client.voip.VoipCallAnswerData;
import ch.threema.storage.DatabaseServiceNew;
import ch.threema.storage.models.ContactModel;
import ch.threema.storage.models.data.status.VoipStatusDataModel;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class SettingsDeveloperFragment extends ThreemaPreferenceFragment {

    private static final Logger logger = LoggerFactory.getLogger(SettingsDeveloperFragment.class);

    // Test identities.
    private static final String TEST_IDENTITY_1 = "ADDRTCNX";

    private static final String TEST_IDENTITY_2 = "H6AXSHKC";

    private PreferenceService preferenceService;

    private DatabaseServiceNew databaseService;

    private ContactService contactService;

    private MessageService messageService;

    private UserService userService;

    @Override
    public void onCreatePreferencesFix(@Nullable Bundle savedInstanceState, String rootKey) {
        if (!ListenerUtil.mutListener.listen(32342)) {
            if (!requiredInstances()) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(32343)) {
            addPreferencesFromResource(R.xml.preference_developers);
        }
        // Generate VoIP messages
        final Preference generateVoipPreference = findPreference(getResources().getString(R.string.preferences__generate_voip_messages));
        if (!ListenerUtil.mutListener.listen(32344)) {
            generateVoipPreference.setSummary("Create the test identity " + TEST_IDENTITY_1 + " and add all possible VoIP messages to that conversation.");
        }
        if (!ListenerUtil.mutListener.listen(32345)) {
            generateVoipPreference.setOnPreferenceClickListener(this::generateVoipMessages);
        }
        // Generate test quotes
        final Preference generateRecursiveQuote = findPreference(getResources().getString(R.string.preferences__generate_test_quotes));
        if (!ListenerUtil.mutListener.listen(32346)) {
            generateRecursiveQuote.setSummary("Create the test identities " + TEST_IDENTITY_1 + " and " + TEST_IDENTITY_2 + " and add some test quotes.");
        }
        if (!ListenerUtil.mutListener.listen(32347)) {
            generateRecursiveQuote.setOnPreferenceClickListener(this::generateTestQuotes);
        }
        // Remove developer menu
        final Preference removeMenuPreference = findPreference(getResources().getString(R.string.preferences__remove_menu));
        if (!ListenerUtil.mutListener.listen(32348)) {
            removeMenuPreference.setSummary("Hide the developer menu from the settings.");
        }
        if (!ListenerUtil.mutListener.listen(32349)) {
            removeMenuPreference.setOnPreferenceClickListener(this::hideDeveloperMenu);
        }
    }

    @UiThread
    private void showOk(CharSequence msg) {
        if (!ListenerUtil.mutListener.listen(32350)) {
            Toast.makeText(this.getContext(), msg, Toast.LENGTH_LONG).show();
        }
    }

    @UiThread
    private void showError(Exception e) {
        if (!ListenerUtil.mutListener.listen(32351)) {
            logger.error("Exception", e);
        }
        if (!ListenerUtil.mutListener.listen(32352)) {
            Toast.makeText(this.getContext(), e.toString(), Toast.LENGTH_LONG).show();
        }
    }

    @WorkerThread
    private ContactModel createTestContact(String identity, String firstName, String lastName) throws EntryAlreadyExistsException, InvalidEntryException, PolicyViolationException {
        ContactModel contact = contactService.getByIdentity(identity);
        if (!ListenerUtil.mutListener.listen(32354)) {
            if (contact == null) {
                if (!ListenerUtil.mutListener.listen(32353)) {
                    contact = contactService.createContactByIdentity(identity, true);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(32355)) {
            contact.setName(firstName, lastName);
        }
        if (!ListenerUtil.mutListener.listen(32356)) {
            databaseService.getContactModelFactory().createOrUpdate(contact);
        }
        return contact;
    }

    @UiThread
    @SuppressLint("StaticFieldLeak")
    private boolean generateVoipMessages(Preference preference) {
        // Pojo for holding test data.
        class VoipMessage {

            VoipStatusDataModel dataModel;

            String description;

            VoipMessage(VoipStatusDataModel dataModel, String description) {
                if (!ListenerUtil.mutListener.listen(32408)) {
                    if (!ListenerUtil.mutListener.listen(32357)) {
                        if (!ListenerUtil.mutListener.listen(32407)) {
                            this.dataModel = dataModel;
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(32410)) {
                    if (!ListenerUtil.mutListener.listen(32358)) {
                        if (!ListenerUtil.mutListener.listen(32409)) {
                            this.description = description;
                        }
                    }
                }
            }
        }
        // Test messages
        final VoipMessage[] testMessages = new VoipMessage[] { new VoipMessage(VoipStatusDataModel.createMissed(), "missed"), new VoipMessage(VoipStatusDataModel.createFinished(42), "finished"), new VoipMessage(VoipStatusDataModel.createRejected(VoipCallAnswerData.RejectReason.UNKNOWN), "rejected (unknown)"), new VoipMessage(VoipStatusDataModel.createRejected(VoipCallAnswerData.RejectReason.BUSY), "rejected (busy)"), new VoipMessage(VoipStatusDataModel.createRejected(VoipCallAnswerData.RejectReason.TIMEOUT), "rejected (timeout)"), new VoipMessage(VoipStatusDataModel.createRejected(VoipCallAnswerData.RejectReason.REJECTED), "rejected (rejected)"), new VoipMessage(VoipStatusDataModel.createRejected(VoipCallAnswerData.RejectReason.DISABLED), "rejected (disabled)"), new VoipMessage(VoipStatusDataModel.createRejected((byte) 99), "rejected (invalid reason code)"), new VoipMessage(VoipStatusDataModel.createRejected(null), "rejected (null reason code)"), new VoipMessage(VoipStatusDataModel.createAborted(), "aborted") };
        if (!ListenerUtil.mutListener.listen(32367)) {
            new AsyncTask<Void, Void, Exception>() {

                @Override
                @Nullable
                protected Exception doInBackground(Void... voids) {
                    try {
                        // Create test identity
                        final ContactModel contact = createTestContact(TEST_IDENTITY_1, "Developer", "Testcontact");
                        // Create test messages
                        final ContactMessageReceiver receiver = contactService.createReceiver(contact);
                        if (!ListenerUtil.mutListener.listen(32359)) {
                            messageService.createStatusMessage("Creating test messages...", receiver);
                        }
                        if (!ListenerUtil.mutListener.listen(32363)) {
                            {
                                long _loopCounter227 = 0;
                                for (boolean isOutbox : new boolean[] { true, false }) {
                                    ListenerUtil.loopListener.listen("_loopCounter227", ++_loopCounter227);
                                    if (!ListenerUtil.mutListener.listen(32362)) {
                                        {
                                            long _loopCounter226 = 0;
                                            for (VoipMessage msg : testMessages) {
                                                ListenerUtil.loopListener.listen("_loopCounter226", ++_loopCounter226);
                                                final String text = (isOutbox ? "Outgoing " : "Incoming ") + msg.description;
                                                if (!ListenerUtil.mutListener.listen(32360)) {
                                                    messageService.createStatusMessage(text, receiver);
                                                }
                                                if (!ListenerUtil.mutListener.listen(32361)) {
                                                    messageService.createVoipStatus(msg.dataModel, receiver, isOutbox, true);
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        return null;
                    } catch (Exception e) {
                        return e;
                    }
                }

                @Override
                protected void onPostExecute(@Nullable Exception e) {
                    if (!ListenerUtil.mutListener.listen(32366)) {
                        if (e == null) {
                            if (!ListenerUtil.mutListener.listen(32365)) {
                                showOk("Test messages created!");
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(32364)) {
                                showError(e);
                            }
                        }
                    }
                }
            }.execute();
        }
        return true;
    }

    @UiThread
    @SuppressLint("StaticFieldLeak")
    private boolean generateTestQuotes(Preference preference) {
        if (!ListenerUtil.mutListener.listen(32391)) {
            new AsyncTask<Void, Void, Exception>() {

                @Override
                @Nullable
                protected Exception doInBackground(Void... voids) {
                    try {
                        // Create test identity
                        final ContactModel contact1 = createTestContact(TEST_IDENTITY_1, "Developer", "Testcontact");
                        final ContactMessageReceiver receiver1 = contactService.createReceiver(contact1);
                        final ContactModel contact2 = createTestContact(TEST_IDENTITY_2, "Another Developer", "Testcontact");
                        final ContactMessageReceiver receiver2 = contactService.createReceiver(contact2);
                        if (!ListenerUtil.mutListener.listen(32368)) {
                            messageService.createStatusMessage("Creating test quotes...", receiver1);
                        }
                        // Create recursive quote
                        final MessageId messageIdRecursive = new MessageId();
                        BoxTextMessage messageRecursive = new BoxTextMessage();
                        if (!ListenerUtil.mutListener.listen(32369)) {
                            messageRecursive.setFromIdentity(contact1.getIdentity());
                        }
                        if (!ListenerUtil.mutListener.listen(32370)) {
                            messageRecursive.setToIdentity(userService.getIdentity());
                        }
                        if (!ListenerUtil.mutListener.listen(32371)) {
                            messageRecursive.setDate(new Date());
                        }
                        if (!ListenerUtil.mutListener.listen(32372)) {
                            messageRecursive.setMessageId(messageIdRecursive);
                        }
                        if (!ListenerUtil.mutListener.listen(32373)) {
                            messageRecursive.setText("> quote #" + messageIdRecursive.toString() + "\n\na quote that references itself");
                        }
                        if (!ListenerUtil.mutListener.listen(32374)) {
                            messageService.processIncomingContactMessage(messageRecursive);
                        }
                        // Create cross-chat quote
                        final MessageId messageIdCrossChat1 = new MessageId();
                        final MessageId messageIdCrossChat2 = new MessageId();
                        BoxTextMessage messageChat2 = new BoxTextMessage();
                        if (!ListenerUtil.mutListener.listen(32375)) {
                            messageChat2.setFromIdentity(contact2.getIdentity());
                        }
                        if (!ListenerUtil.mutListener.listen(32376)) {
                            messageChat2.setToIdentity(userService.getIdentity());
                        }
                        if (!ListenerUtil.mutListener.listen(32377)) {
                            messageChat2.setDate(new Date());
                        }
                        if (!ListenerUtil.mutListener.listen(32378)) {
                            messageChat2.setMessageId(messageIdCrossChat2);
                        }
                        if (!ListenerUtil.mutListener.listen(32379)) {
                            messageChat2.setText("hello, this is a secret message");
                        }
                        if (!ListenerUtil.mutListener.listen(32380)) {
                            messageService.processIncomingContactMessage(messageChat2);
                        }
                        BoxTextMessage messageChat1 = new BoxTextMessage();
                        if (!ListenerUtil.mutListener.listen(32381)) {
                            messageChat1.setFromIdentity(contact1.getIdentity());
                        }
                        if (!ListenerUtil.mutListener.listen(32382)) {
                            messageChat1.setToIdentity(userService.getIdentity());
                        }
                        if (!ListenerUtil.mutListener.listen(32383)) {
                            messageChat1.setDate(new Date());
                        }
                        if (!ListenerUtil.mutListener.listen(32384)) {
                            messageChat1.setMessageId(messageIdCrossChat1);
                        }
                        if (!ListenerUtil.mutListener.listen(32385)) {
                            messageChat1.setText("> quote #" + messageIdCrossChat2.toString() + "\n\nOMG!");
                        }
                        if (!ListenerUtil.mutListener.listen(32386)) {
                            messageService.processIncomingContactMessage(messageChat1);
                        }
                        if (!ListenerUtil.mutListener.listen(32387)) {
                            messageService.createStatusMessage("Done creating test quotes", receiver1);
                        }
                        return null;
                    } catch (Exception e) {
                        return e;
                    }
                }

                @Override
                protected void onPostExecute(@Nullable Exception e) {
                    if (!ListenerUtil.mutListener.listen(32390)) {
                        if (e == null) {
                            if (!ListenerUtil.mutListener.listen(32389)) {
                                showOk("Test quotes created!");
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(32388)) {
                                showError(e);
                            }
                        }
                    }
                }
            }.execute();
        }
        return true;
    }

    @UiThread
    @SuppressLint("StaticFieldLeak")
    private boolean hideDeveloperMenu(Preference preference) {
        if (!ListenerUtil.mutListener.listen(32392)) {
            this.preferenceService.setShowDeveloperMenu(false);
        }
        if (!ListenerUtil.mutListener.listen(32393)) {
            this.showOk("Not everybody can be a craaazy developer!");
        }
        final Activity activity = this.getActivity();
        if (!ListenerUtil.mutListener.listen(32395)) {
            if (activity != null) {
                if (!ListenerUtil.mutListener.listen(32394)) {
                    activity.finish();
                }
            }
        }
        return true;
    }

    protected final boolean requiredInstances() {
        if (!ListenerUtil.mutListener.listen(32397)) {
            if (!this.checkInstances()) {
                if (!ListenerUtil.mutListener.listen(32396)) {
                    this.instantiate();
                }
            }
        }
        return this.checkInstances();
    }

    protected boolean checkInstances() {
        return TestUtil.required(this.preferenceService, this.databaseService, this.contactService, this.messageService, this.userService);
    }

    protected void instantiate() {
        ServiceManager serviceManager = ThreemaApplication.getServiceManager();
        if (!ListenerUtil.mutListener.listen(32404)) {
            if (serviceManager != null) {
                try {
                    if (!ListenerUtil.mutListener.listen(32399)) {
                        this.preferenceService = serviceManager.getPreferenceService();
                    }
                    if (!ListenerUtil.mutListener.listen(32400)) {
                        this.databaseService = serviceManager.getDatabaseServiceNew();
                    }
                    if (!ListenerUtil.mutListener.listen(32401)) {
                        this.contactService = serviceManager.getContactService();
                    }
                    if (!ListenerUtil.mutListener.listen(32402)) {
                        this.messageService = serviceManager.getMessageService();
                    }
                    if (!ListenerUtil.mutListener.listen(32403)) {
                        this.userService = serviceManager.getUserService();
                    }
                } catch (Exception e) {
                    if (!ListenerUtil.mutListener.listen(32398)) {
                        logger.error("Exception", e);
                    }
                }
            }
        }
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(32405)) {
            preferenceFragmentCallbackInterface.setToolbarTitle(R.string.prefs_developers);
        }
        if (!ListenerUtil.mutListener.listen(32406)) {
            super.onViewCreated(view, savedInstanceState);
        }
    }
}
