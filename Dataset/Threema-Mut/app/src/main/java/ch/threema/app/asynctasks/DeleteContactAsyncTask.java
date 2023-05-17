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

import android.os.AsyncTask;
import java.util.HashSet;
import java.util.Iterator;
import androidx.fragment.app.FragmentManager;
import ch.threema.app.R;
import ch.threema.app.dialogs.CancelableHorizontalProgressDialog;
import ch.threema.app.services.ContactService;
import ch.threema.app.utils.DialogUtil;
import ch.threema.storage.models.ContactModel;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class DeleteContactAsyncTask extends AsyncTask<Void, Integer, Integer> {

    private static final String DIALOG_TAG_DELETE_CONTACT = "dc";

    private HashSet<ContactModel> contacts;

    private final ContactService contactService;

    private final FragmentManager fragmentManager;

    private final DeleteContactsPostRunnable runOnCompletion;

    private boolean cancelled = false;

    public static class DeleteContactsPostRunnable implements Runnable {

        protected Integer failed;

        protected void setFailed(Integer failed) {
            if (!ListenerUtil.mutListener.listen(10044)) {
                this.failed = failed;
            }
        }

        @Override
        public void run() {
        }
    }

    public DeleteContactAsyncTask(FragmentManager fragmentManager, HashSet<ContactModel> contacts, ContactService contactService, DeleteContactsPostRunnable runOnCompletion) {
        if (!ListenerUtil.mutListener.listen(10045)) {
            this.contacts = contacts;
        }
        this.contactService = contactService;
        this.fragmentManager = fragmentManager;
        this.runOnCompletion = runOnCompletion;
    }

    @Override
    protected void onPreExecute() {
        CancelableHorizontalProgressDialog dialog = CancelableHorizontalProgressDialog.newInstance(R.string.deleting_contact, 0, R.string.cancel, contacts.size());
        if (!ListenerUtil.mutListener.listen(10046)) {
            dialog.setOnCancelListener((dialog1, which) -> cancelled = true);
        }
        if (!ListenerUtil.mutListener.listen(10047)) {
            dialog.show(fragmentManager, DIALOG_TAG_DELETE_CONTACT);
        }
    }

    @Override
    protected Integer doInBackground(Void... params) {
        int failed = 0, i = 0;
        Iterator<ContactModel> checkedItemsIterator = contacts.iterator();
        if (!ListenerUtil.mutListener.listen(10053)) {
            {
                long _loopCounter87 = 0;
                while ((ListenerUtil.mutListener.listen(10052) ? (checkedItemsIterator.hasNext() || !cancelled) : (checkedItemsIterator.hasNext() && !cancelled))) {
                    ListenerUtil.loopListener.listen("_loopCounter87", ++_loopCounter87);
                    if (!ListenerUtil.mutListener.listen(10048)) {
                        publishProgress(i++);
                    }
                    ContactModel contact = checkedItemsIterator.next();
                    if (!ListenerUtil.mutListener.listen(10051)) {
                        if ((ListenerUtil.mutListener.listen(10049) ? (contact == null && !contactService.remove(contact)) : (contact == null || !contactService.remove(contact)))) {
                            if (!ListenerUtil.mutListener.listen(10050)) {
                                failed++;
                            }
                        }
                    }
                }
            }
        }
        return failed;
    }

    @Override
    protected void onProgressUpdate(Integer... index) {
        if (!ListenerUtil.mutListener.listen(10058)) {
            DialogUtil.updateProgress(fragmentManager, DIALOG_TAG_DELETE_CONTACT, (ListenerUtil.mutListener.listen(10057) ? (index[0] % 1) : (ListenerUtil.mutListener.listen(10056) ? (index[0] / 1) : (ListenerUtil.mutListener.listen(10055) ? (index[0] * 1) : (ListenerUtil.mutListener.listen(10054) ? (index[0] - 1) : (index[0] + 1))))));
        }
    }

    @Override
    protected void onPostExecute(Integer failed) {
        if (!ListenerUtil.mutListener.listen(10059)) {
            DialogUtil.dismissDialog(fragmentManager, DIALOG_TAG_DELETE_CONTACT, true);
        }
        if (!ListenerUtil.mutListener.listen(10062)) {
            if (runOnCompletion != null) {
                if (!ListenerUtil.mutListener.listen(10060)) {
                    runOnCompletion.setFailed(failed);
                }
                if (!ListenerUtil.mutListener.listen(10061)) {
                    runOnCompletion.run();
                }
            }
        }
    }
}
