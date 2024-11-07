/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
 * Copyright (c) 2014-2021 Threema GmbH
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
package ch.threema.app.activities.ballot;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
import androidx.appcompat.app.ActionBar;
import ch.threema.app.R;
import ch.threema.app.activities.ThreemaToolbarActivity;
import ch.threema.app.adapters.ballot.BallotOverviewListAdapter;
import ch.threema.app.exceptions.NotAllowedException;
import ch.threema.app.listeners.BallotListener;
import ch.threema.app.managers.ListenerManager;
import ch.threema.app.messagereceiver.MessageReceiver;
import ch.threema.app.services.ContactService;
import ch.threema.app.services.GroupService;
import ch.threema.app.services.ballot.BallotService;
import ch.threema.app.ui.EmptyView;
import ch.threema.app.utils.IntentDataUtil;
import ch.threema.app.utils.RuntimeUtil;
import ch.threema.app.utils.TestUtil;
import ch.threema.storage.models.ballot.BallotModel;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class BallotChooserActivity extends ThreemaToolbarActivity implements ListView.OnItemClickListener {

    private static final Logger logger = LoggerFactory.getLogger(BallotChooserActivity.class);

    private BallotService ballotService;

    private ContactService contactService;

    private GroupService groupService;

    private String myIdentity;

    private BallotOverviewListAdapter listAdapter = null;

    private List<BallotModel> ballots;

    private ListView listView;

    private BallotListener ballotListener = new BallotListener() {

        @Override
        public void onClosed(BallotModel ballotModel) {
        }

        @Override
        public void onModified(BallotModel ballotModel) {
            if (!ListenerUtil.mutListener.listen(69)) {
                RuntimeUtil.runOnUiThread(() -> updateList());
            }
        }

        @Override
        public void onCreated(BallotModel ballotModel) {
            if (!ListenerUtil.mutListener.listen(70)) {
                RuntimeUtil.runOnUiThread(() -> updateList());
            }
        }

        @Override
        public void onRemoved(BallotModel ballotModel) {
            if (!ListenerUtil.mutListener.listen(71)) {
                RuntimeUtil.runOnUiThread(() -> updateList());
            }
        }

        @Override
        public boolean handle(BallotModel ballotModel) {
            return true;
        }
    };

    public void onCreate(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(72)) {
            super.onCreate(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(73)) {
            if (!this.requireInstancesOrExit()) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(74)) {
            listView = this.findViewById(android.R.id.list);
        }
        if (!ListenerUtil.mutListener.listen(75)) {
            listView.setOnItemClickListener(this);
        }
        if (!ListenerUtil.mutListener.listen(76)) {
            listView.setDividerHeight(0);
        }
        // add text view if list is empty
        EmptyView emptyView = new EmptyView(this);
        if (!ListenerUtil.mutListener.listen(77)) {
            emptyView.setup(R.string.ballot_no_ballots_yet);
        }
        if (!ListenerUtil.mutListener.listen(78)) {
            ((ViewGroup) listView.getParent()).addView(emptyView);
        }
        if (!ListenerUtil.mutListener.listen(79)) {
            listView.setEmptyView(emptyView);
        }
        ActionBar actionBar = getSupportActionBar();
        if (!ListenerUtil.mutListener.listen(83)) {
            if (actionBar != null) {
                if (!ListenerUtil.mutListener.listen(81)) {
                    actionBar.setDisplayHomeAsUpEnabled(true);
                }
                if (!ListenerUtil.mutListener.listen(82)) {
                    actionBar.setTitle(R.string.ballot_copy);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(80)) {
                    setTitle(R.string.ballot_copy);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(84)) {
            this.setupList();
        }
        if (!ListenerUtil.mutListener.listen(85)) {
            this.updateList();
        }
    }

    @Override
    protected void onResume() {
        if (!ListenerUtil.mutListener.listen(86)) {
            super.onResume();
        }
        if (!ListenerUtil.mutListener.listen(87)) {
            ListenerManager.ballotListeners.add(this.ballotListener);
        }
    }

    @Override
    public int getLayoutResource() {
        return R.layout.activity_list_toolbar;
    }

    @Override
    protected void onDestroy() {
        if (!ListenerUtil.mutListener.listen(88)) {
            ListenerManager.ballotListeners.remove(this.ballotListener);
        }
        if (!ListenerUtil.mutListener.listen(89)) {
            super.onDestroy();
        }
    }

    private void setupList() {
        final ListView listView = this.listView;
        if (!ListenerUtil.mutListener.listen(91)) {
            if (listView != null) {
                if (!ListenerUtil.mutListener.listen(90)) {
                    listView.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);
                }
            }
        }
    }

    private void updateList() {
        if (!ListenerUtil.mutListener.listen(92)) {
            if (!this.requiredInstances()) {
                return;
            }
        }
        try {
            if (!ListenerUtil.mutListener.listen(95)) {
                this.ballots = this.ballotService.getBallots(new BallotService.BallotFilter() {

                    @Override
                    public MessageReceiver getReceiver() {
                        return null;
                    }

                    @Override
                    public BallotModel.State[] getStates() {
                        return null;
                    }

                    @Override
                    public boolean filter(BallotModel ballotModel) {
                        return true;
                    }
                });
            }
            if (!ListenerUtil.mutListener.listen(98)) {
                if (this.ballots != null) {
                    if (!ListenerUtil.mutListener.listen(96)) {
                        this.listAdapter = new BallotOverviewListAdapter(this, this.ballots, this.ballotService, this.contactService);
                    }
                    if (!ListenerUtil.mutListener.listen(97)) {
                        listView.setAdapter(this.listAdapter);
                    }
                }
            }
        } catch (NotAllowedException e) {
            if (!ListenerUtil.mutListener.listen(93)) {
                logger.error("Exception", e);
            }
            if (!ListenerUtil.mutListener.listen(94)) {
                finish();
            }
            return;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (!ListenerUtil.mutListener.listen(99)) {
            if (this.listAdapter == null) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(104)) {
            if (listAdapter != null) {
                BallotModel b = listAdapter.getItem(position);
                if (!ListenerUtil.mutListener.listen(103)) {
                    if (b != null) {
                        Intent resultIntent = this.getIntent();
                        if (!ListenerUtil.mutListener.listen(100)) {
                            // append ballot
                            IntentDataUtil.append(b, this.getIntent());
                        }
                        if (!ListenerUtil.mutListener.listen(101)) {
                            setResult(RESULT_OK, resultIntent);
                        }
                        if (!ListenerUtil.mutListener.listen(102)) {
                            finish();
                        }
                    }
                }
            }
        }
    }

    protected boolean checkInstances() {
        return (ListenerUtil.mutListener.listen(105) ? (!TestUtil.empty(this.myIdentity) || TestUtil.required(this.ballotService, this.contactService, this.groupService)) : (!TestUtil.empty(this.myIdentity) && TestUtil.required(this.ballotService, this.contactService, this.groupService)));
    }

    protected void instantiate() {
        if (!ListenerUtil.mutListener.listen(111)) {
            if (serviceManager != null) {
                try {
                    if (!ListenerUtil.mutListener.listen(107)) {
                        this.ballotService = serviceManager.getBallotService();
                    }
                    if (!ListenerUtil.mutListener.listen(108)) {
                        this.contactService = serviceManager.getContactService();
                    }
                    if (!ListenerUtil.mutListener.listen(109)) {
                        this.groupService = serviceManager.getGroupService();
                    }
                    if (!ListenerUtil.mutListener.listen(110)) {
                        this.myIdentity = serviceManager.getUserService().getIdentity();
                    }
                } catch (Exception e) {
                    if (!ListenerUtil.mutListener.listen(106)) {
                        logger.error("Exception", e);
                    }
                }
            }
        }
    }

    private boolean requireInstancesOrExit() {
        if (!ListenerUtil.mutListener.listen(114)) {
            if (!this.requiredInstances()) {
                if (!ListenerUtil.mutListener.listen(112)) {
                    logger.error("Required instances failed");
                }
                if (!ListenerUtil.mutListener.listen(113)) {
                    this.finish();
                }
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (!ListenerUtil.mutListener.listen(117)) {
            switch(item.getItemId()) {
                case android.R.id.home:
                    if (!ListenerUtil.mutListener.listen(115)) {
                        setResult(RESULT_CANCELED);
                    }
                    if (!ListenerUtil.mutListener.listen(116)) {
                        finish();
                    }
                    break;
            }
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        if (!ListenerUtil.mutListener.listen(118)) {
            setResult(RESULT_CANCELED);
        }
        if (!ListenerUtil.mutListener.listen(119)) {
            finish();
        }
    }
}
