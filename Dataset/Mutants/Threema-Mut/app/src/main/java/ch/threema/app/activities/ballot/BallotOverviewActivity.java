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
import android.util.SparseBooleanArray;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.ArrayList;
import java.util.List;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.view.ActionMode;
import ch.threema.app.R;
import ch.threema.app.ThreemaApplication;
import ch.threema.app.activities.ThreemaToolbarActivity;
import ch.threema.app.adapters.ballot.BallotOverviewListAdapter;
import ch.threema.app.dialogs.GenericAlertDialog;
import ch.threema.app.dialogs.SelectorDialog;
import ch.threema.app.exceptions.NotAllowedException;
import ch.threema.app.listeners.BallotListener;
import ch.threema.app.listeners.BallotVoteListener;
import ch.threema.app.managers.ListenerManager;
import ch.threema.app.messagereceiver.MessageReceiver;
import ch.threema.app.services.ContactService;
import ch.threema.app.services.GroupService;
import ch.threema.app.services.ballot.BallotService;
import ch.threema.app.ui.EmptyView;
import ch.threema.app.utils.BallotUtil;
import ch.threema.app.utils.ConfigUtils;
import ch.threema.app.utils.IntentDataUtil;
import ch.threema.app.utils.LogUtil;
import ch.threema.app.utils.RuntimeUtil;
import ch.threema.app.utils.TestUtil;
import ch.threema.storage.models.ballot.BallotModel;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class BallotOverviewActivity extends ThreemaToolbarActivity implements ListView.OnItemClickListener, GenericAlertDialog.DialogClickListener, SelectorDialog.SelectorDialogClickListener {

    private static final Logger logger = LoggerFactory.getLogger(BallotOverviewActivity.class);

    private static final String DIALOG_TAG_BALLOT_DELETE = "bd";

    private static final String DIALOG_TAG_CHOOSE_ACTION = "ca";

    private static final int SELECTOR_ID_VOTE = 1;

    private static final int SELECTOR_ID_RESULTS = 2;

    private static final int SELECTOR_ID_CLOSE = 3;

    private BallotService ballotService;

    private ContactService contactService;

    private GroupService groupService;

    private String myIdentity;

    private Intent receivedIntent;

    private MessageReceiver messageReceiver;

    private BallotOverviewListAdapter listAdapter = null;

    private List<BallotModel> ballots;

    private ListView listView;

    private ActionMode actionMode = null;

    private boolean enableBallotListeners = true;

    private Runnable updateList = new Runnable() {

        @Override
        public void run() {
            if (!ListenerUtil.mutListener.listen(220)) {
                if (listAdapter != null) {
                    if (!ListenerUtil.mutListener.listen(219)) {
                        listAdapter.notifyDataSetChanged();
                    }
                }
            }
        }
    };

    private BallotVoteListener ballotVoteListener = new BallotVoteListener() {

        @Override
        public void onSelfVote(BallotModel ballotModel) {
            if (!ListenerUtil.mutListener.listen(221)) {
                RuntimeUtil.runOnUiThread(updateList);
            }
        }

        @Override
        public void onVoteChanged(BallotModel ballotModel, String votingIdentity, boolean isFirstVote) {
            if (!ListenerUtil.mutListener.listen(222)) {
                RuntimeUtil.runOnUiThread(updateList);
            }
        }

        @Override
        public void onVoteRemoved(BallotModel ballotModel, String votingIdentity) {
            if (!ListenerUtil.mutListener.listen(223)) {
                RuntimeUtil.runOnUiThread(updateList);
            }
        }

        @Override
        public boolean handle(BallotModel ballotModel) {
            return ballotListener.handle(ballotModel);
        }
    };

    private BallotListener ballotListener = new BallotListener() {

        @Override
        public void onClosed(BallotModel ballotModel) {
            if (!ListenerUtil.mutListener.listen(224)) {
                RuntimeUtil.runOnUiThread(() -> updateList());
            }
        }

        @Override
        public void onModified(BallotModel ballotModel) {
            if (!ListenerUtil.mutListener.listen(225)) {
                RuntimeUtil.runOnUiThread(() -> updateList());
            }
        }

        @Override
        public void onCreated(BallotModel ballotModel) {
            if (!ListenerUtil.mutListener.listen(226)) {
                RuntimeUtil.runOnUiThread(() -> updateList());
            }
        }

        @Override
        public void onRemoved(BallotModel ballotModel) {
            if (!ListenerUtil.mutListener.listen(227)) {
                RuntimeUtil.runOnUiThread(() -> updateList());
            }
        }

        @Override
        public boolean handle(BallotModel ballotModel) {
            if (!ListenerUtil.mutListener.listen(231)) {
                if ((ListenerUtil.mutListener.listen(229) ? ((ListenerUtil.mutListener.listen(228) ? (enableBallotListeners || requiredInstances()) : (enableBallotListeners && requiredInstances())) || messageReceiver != null) : ((ListenerUtil.mutListener.listen(228) ? (enableBallotListeners || requiredInstances()) : (enableBallotListeners && requiredInstances())) && messageReceiver != null))) {
                    try {
                        return ballotService.belongsToMe(ballotModel.getId(), messageReceiver);
                    } catch (NotAllowedException e) {
                        if (!ListenerUtil.mutListener.listen(230)) {
                            logger.error("Exception", e);
                        }
                    }
                }
            }
            return false;
        }
    };

    public void onCreate(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(232)) {
            super.onCreate(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(233)) {
            if (!this.requireInstancesOrExit()) {
                return;
            }
        }
        ActionBar actionBar = getSupportActionBar();
        if (!ListenerUtil.mutListener.listen(234)) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        if (!ListenerUtil.mutListener.listen(235)) {
            actionBar.setTitle(R.string.ballot_overview);
        }
        if (!ListenerUtil.mutListener.listen(236)) {
            listView = this.findViewById(android.R.id.list);
        }
        if (!ListenerUtil.mutListener.listen(237)) {
            listView.setOnItemClickListener(this);
        }
        EmptyView emptyView = new EmptyView(this);
        if (!ListenerUtil.mutListener.listen(238)) {
            emptyView.setup(getString(R.string.ballot_no_ballots_yet));
        }
        if (!ListenerUtil.mutListener.listen(239)) {
            ((ViewGroup) listView.getParent()).addView(emptyView);
        }
        if (!ListenerUtil.mutListener.listen(240)) {
            listView.setEmptyView(emptyView);
        }
        if (!ListenerUtil.mutListener.listen(241)) {
            receivedIntent = getIntent();
        }
        if (!ListenerUtil.mutListener.listen(242)) {
            this.messageReceiver = IntentDataUtil.getMessageReceiverFromIntent(this, receivedIntent);
        }
        if (!ListenerUtil.mutListener.listen(245)) {
            if (this.messageReceiver == null) {
                if (!ListenerUtil.mutListener.listen(243)) {
                    logger.error("cannot instantiate receiver");
                }
                if (!ListenerUtil.mutListener.listen(244)) {
                    finish();
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(246)) {
            this.setupList();
        }
        if (!ListenerUtil.mutListener.listen(247)) {
            this.updateList();
        }
    }

    @Override
    protected void onResume() {
        if (!ListenerUtil.mutListener.listen(248)) {
            super.onResume();
        }
        if (!ListenerUtil.mutListener.listen(249)) {
            ListenerManager.ballotListeners.add(this.ballotListener);
        }
        if (!ListenerUtil.mutListener.listen(250)) {
            ListenerManager.ballotVoteListeners.add(this.ballotVoteListener);
        }
    }

    @Override
    public int getLayoutResource() {
        return R.layout.activity_list_toolbar;
    }

    @Override
    protected void onDestroy() {
        if (!ListenerUtil.mutListener.listen(251)) {
            ListenerManager.ballotListeners.remove(this.ballotListener);
        }
        if (!ListenerUtil.mutListener.listen(252)) {
            ListenerManager.ballotVoteListeners.remove(this.ballotVoteListener);
        }
        if (!ListenerUtil.mutListener.listen(253)) {
            super.onDestroy();
        }
    }

    private void setupList() {
        final ListView listView = this.listView;
        if (!ListenerUtil.mutListener.listen(261)) {
            if (listView != null) {
                if (!ListenerUtil.mutListener.listen(254)) {
                    listView.setDividerHeight(0);
                }
                if (!ListenerUtil.mutListener.listen(255)) {
                    listView.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);
                }
                if (!ListenerUtil.mutListener.listen(260)) {
                    listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

                        public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                            if (!ListenerUtil.mutListener.listen(256)) {
                                view.setSelected(true);
                            }
                            if (!ListenerUtil.mutListener.listen(257)) {
                                listView.setItemChecked(position, true);
                            }
                            if (!ListenerUtil.mutListener.listen(258)) {
                                listView.setChoiceMode(AbsListView.CHOICE_MODE_MULTIPLE);
                            }
                            if (!ListenerUtil.mutListener.listen(259)) {
                                actionMode = startSupportActionMode(new MessageSectionAction());
                            }
                            return true;
                        }
                    });
                }
            }
        }
    }

    private void updateList() {
        if (!ListenerUtil.mutListener.listen(262)) {
            if (!this.requiredInstances()) {
                return;
            }
        }
        try {
            if (!ListenerUtil.mutListener.listen(265)) {
                this.ballots = this.ballotService.getBallots(new BallotService.BallotFilter() {

                    @Override
                    public MessageReceiver getReceiver() {
                        return messageReceiver;
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
            if (!ListenerUtil.mutListener.listen(268)) {
                if (this.ballots != null) {
                    if (!ListenerUtil.mutListener.listen(266)) {
                        this.listAdapter = new BallotOverviewListAdapter(this, this.ballots, this.ballotService, this.contactService);
                    }
                    if (!ListenerUtil.mutListener.listen(267)) {
                        listView.setAdapter(this.listAdapter);
                    }
                }
            }
        } catch (NotAllowedException e) {
            if (!ListenerUtil.mutListener.listen(263)) {
                logger.error("Exception", e);
            }
            if (!ListenerUtil.mutListener.listen(264)) {
                finish();
            }
            return;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (!ListenerUtil.mutListener.listen(269)) {
            if (this.listAdapter == null) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(298)) {
            if (actionMode == null) {
                if (!ListenerUtil.mutListener.listen(278)) {
                    this.deselectItem();
                }
                BallotModel ballotModel = listAdapter.getItem(position);
                if (!ListenerUtil.mutListener.listen(297)) {
                    if (ballotModel != null) {
                        ArrayList<String> items = new ArrayList<>(3);
                        ArrayList<Integer> values = new ArrayList<>(3);
                        if (!ListenerUtil.mutListener.listen(281)) {
                            if (BallotUtil.canVote(ballotModel, myIdentity)) {
                                if (!ListenerUtil.mutListener.listen(279)) {
                                    items.add(getString(R.string.ballot_vote));
                                }
                                if (!ListenerUtil.mutListener.listen(280)) {
                                    values.add(SELECTOR_ID_VOTE);
                                }
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(284)) {
                            if (BallotUtil.canViewMatrix(ballotModel, myIdentity)) {
                                if (!ListenerUtil.mutListener.listen(282)) {
                                    items.add(getString(ballotModel.getState() == BallotModel.State.CLOSED ? R.string.ballot_result_final : R.string.ballot_result_intermediate));
                                }
                                if (!ListenerUtil.mutListener.listen(283)) {
                                    values.add(SELECTOR_ID_RESULTS);
                                }
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(287)) {
                            if (BallotUtil.canClose(ballotModel, myIdentity)) {
                                if (!ListenerUtil.mutListener.listen(285)) {
                                    items.add(getString(R.string.ballot_close));
                                }
                                if (!ListenerUtil.mutListener.listen(286)) {
                                    values.add(SELECTOR_ID_CLOSE);
                                }
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(296)) {
                            if ((ListenerUtil.mutListener.listen(292) ? (items.size() >= 1) : (ListenerUtil.mutListener.listen(291) ? (items.size() <= 1) : (ListenerUtil.mutListener.listen(290) ? (items.size() > 1) : (ListenerUtil.mutListener.listen(289) ? (items.size() < 1) : (ListenerUtil.mutListener.listen(288) ? (items.size() != 1) : (items.size() == 1))))))) {
                                if (!ListenerUtil.mutListener.listen(295)) {
                                    BallotUtil.openDefaultActivity(this, this.getSupportFragmentManager(), ballotModel, myIdentity);
                                }
                            } else {
                                SelectorDialog selectorDialog = SelectorDialog.newInstance(null, items, values, null);
                                if (!ListenerUtil.mutListener.listen(293)) {
                                    selectorDialog.setData(ballotModel);
                                }
                                if (!ListenerUtil.mutListener.listen(294)) {
                                    selectorDialog.show(getSupportFragmentManager(), DIALOG_TAG_CHOOSE_ACTION);
                                }
                            }
                        }
                    }
                }
            } else {
                // invalidate menu to update display => onPrepareActionMode()
                final int checked = listView.getCheckedItemCount();
                if (!ListenerUtil.mutListener.listen(277)) {
                    if ((ListenerUtil.mutListener.listen(274) ? (checked >= 0) : (ListenerUtil.mutListener.listen(273) ? (checked <= 0) : (ListenerUtil.mutListener.listen(272) ? (checked < 0) : (ListenerUtil.mutListener.listen(271) ? (checked != 0) : (ListenerUtil.mutListener.listen(270) ? (checked == 0) : (checked > 0))))))) {
                        if (!ListenerUtil.mutListener.listen(276)) {
                            actionMode.invalidate();
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(275)) {
                            actionMode.finish();
                        }
                    }
                }
            }
        }
    }

    protected boolean checkInstances() {
        return (ListenerUtil.mutListener.listen(299) ? (!TestUtil.empty(this.myIdentity) || TestUtil.required(this.ballotService, this.contactService, this.groupService)) : (!TestUtil.empty(this.myIdentity) && TestUtil.required(this.ballotService, this.contactService, this.groupService)));
    }

    protected void instantiate() {
        if (!ListenerUtil.mutListener.listen(305)) {
            if (serviceManager != null) {
                try {
                    if (!ListenerUtil.mutListener.listen(301)) {
                        this.ballotService = serviceManager.getBallotService();
                    }
                    if (!ListenerUtil.mutListener.listen(302)) {
                        this.contactService = serviceManager.getContactService();
                    }
                    if (!ListenerUtil.mutListener.listen(303)) {
                        this.groupService = serviceManager.getGroupService();
                    }
                    if (!ListenerUtil.mutListener.listen(304)) {
                        this.myIdentity = serviceManager.getUserService().getIdentity();
                    }
                } catch (Exception e) {
                    if (!ListenerUtil.mutListener.listen(300)) {
                        logger.error("Exception", e);
                    }
                }
            }
        }
    }

    private boolean requireInstancesOrExit() {
        if (!ListenerUtil.mutListener.listen(308)) {
            if (!this.requiredInstances()) {
                if (!ListenerUtil.mutListener.listen(306)) {
                    logger.error("Required instances failed");
                }
                if (!ListenerUtil.mutListener.listen(307)) {
                    this.finish();
                }
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (!ListenerUtil.mutListener.listen(310)) {
            switch(item.getItemId()) {
                case android.R.id.home:
                    if (!ListenerUtil.mutListener.listen(309)) {
                        finish();
                    }
                    break;
            }
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        if (!ListenerUtil.mutListener.listen(311)) {
            setResult(RESULT_OK);
        }
        if (!ListenerUtil.mutListener.listen(312)) {
            finish();
        }
    }

    private void deselectItem() {
        if (!ListenerUtil.mutListener.listen(316)) {
            if (listView != null) {
                if (!ListenerUtil.mutListener.listen(313)) {
                    listView.clearChoices();
                }
                if (!ListenerUtil.mutListener.listen(314)) {
                    listView.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);
                }
                if (!ListenerUtil.mutListener.listen(315)) {
                    listView.requestLayout();
                }
            }
        }
    }

    private int getFirstCheckedPosition(ListView listView) {
        SparseBooleanArray checked = listView.getCheckedItemPositions();
        if (!ListenerUtil.mutListener.listen(323)) {
            {
                long _loopCounter5 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(322) ? (i >= checked.size()) : (ListenerUtil.mutListener.listen(321) ? (i <= checked.size()) : (ListenerUtil.mutListener.listen(320) ? (i > checked.size()) : (ListenerUtil.mutListener.listen(319) ? (i != checked.size()) : (ListenerUtil.mutListener.listen(318) ? (i == checked.size()) : (i < checked.size())))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter5", ++_loopCounter5);
                    if (!ListenerUtil.mutListener.listen(317)) {
                        if (checked.valueAt(i)) {
                            return checked.keyAt(i);
                        }
                    }
                }
            }
        }
        return AbsListView.INVALID_POSITION;
    }

    private void removeSelectedBallots() {
        final SparseBooleanArray checkedItems = listView.getCheckedItemPositions();
        final int numCheckedItems = listView.getCheckedItemCount();
        GenericAlertDialog dialog = GenericAlertDialog.newInstance(R.string.ballot_really_delete, getString(R.string.ballot_really_delete_text, numCheckedItems), R.string.ok, R.string.cancel);
        if (!ListenerUtil.mutListener.listen(324)) {
            dialog.setData(checkedItems);
        }
        if (!ListenerUtil.mutListener.listen(325)) {
            dialog.show(getSupportFragmentManager(), DIALOG_TAG_BALLOT_DELETE);
        }
    }

    private boolean removeSelectedBallotsDo(SparseBooleanArray checkedItems) {
        if (!ListenerUtil.mutListener.listen(326)) {
            if (!this.requiredInstances()) {
                return false;
            }
        }
        synchronized (this.ballots) {
            if (!ListenerUtil.mutListener.listen(327)) {
                // disable listener
                enableBallotListeners = false;
            }
            if (!ListenerUtil.mutListener.listen(348)) {
                {
                    long _loopCounter6 = 0;
                    for (int i = 0; (ListenerUtil.mutListener.listen(347) ? (i >= checkedItems.size()) : (ListenerUtil.mutListener.listen(346) ? (i <= checkedItems.size()) : (ListenerUtil.mutListener.listen(345) ? (i > checkedItems.size()) : (ListenerUtil.mutListener.listen(344) ? (i != checkedItems.size()) : (ListenerUtil.mutListener.listen(343) ? (i == checkedItems.size()) : (i < checkedItems.size())))))); i++) {
                        ListenerUtil.loopListener.listen("_loopCounter6", ++_loopCounter6);
                        if (!ListenerUtil.mutListener.listen(342)) {
                            if (checkedItems.valueAt(i)) {
                                final int index = checkedItems.keyAt(i);
                                if (!ListenerUtil.mutListener.listen(341)) {
                                    if ((ListenerUtil.mutListener.listen(338) ? ((ListenerUtil.mutListener.listen(332) ? (index <= 0) : (ListenerUtil.mutListener.listen(331) ? (index > 0) : (ListenerUtil.mutListener.listen(330) ? (index < 0) : (ListenerUtil.mutListener.listen(329) ? (index != 0) : (ListenerUtil.mutListener.listen(328) ? (index == 0) : (index >= 0)))))) || (ListenerUtil.mutListener.listen(337) ? (index >= this.ballots.size()) : (ListenerUtil.mutListener.listen(336) ? (index <= this.ballots.size()) : (ListenerUtil.mutListener.listen(335) ? (index > this.ballots.size()) : (ListenerUtil.mutListener.listen(334) ? (index != this.ballots.size()) : (ListenerUtil.mutListener.listen(333) ? (index == this.ballots.size()) : (index < this.ballots.size()))))))) : ((ListenerUtil.mutListener.listen(332) ? (index <= 0) : (ListenerUtil.mutListener.listen(331) ? (index > 0) : (ListenerUtil.mutListener.listen(330) ? (index < 0) : (ListenerUtil.mutListener.listen(329) ? (index != 0) : (ListenerUtil.mutListener.listen(328) ? (index == 0) : (index >= 0)))))) && (ListenerUtil.mutListener.listen(337) ? (index >= this.ballots.size()) : (ListenerUtil.mutListener.listen(336) ? (index <= this.ballots.size()) : (ListenerUtil.mutListener.listen(335) ? (index > this.ballots.size()) : (ListenerUtil.mutListener.listen(334) ? (index != this.ballots.size()) : (ListenerUtil.mutListener.listen(333) ? (index == this.ballots.size()) : (index < this.ballots.size()))))))))) {
                                        try {
                                            if (!ListenerUtil.mutListener.listen(340)) {
                                                this.ballotService.remove(this.ballots.get(index));
                                            }
                                        } catch (NotAllowedException e) {
                                            if (!ListenerUtil.mutListener.listen(339)) {
                                                LogUtil.exception(e, this);
                                            }
                                            return false;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(349)) {
                enableBallotListeners = true;
            }
        }
        if (!ListenerUtil.mutListener.listen(351)) {
            if (actionMode != null) {
                if (!ListenerUtil.mutListener.listen(350)) {
                    actionMode.finish();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(352)) {
            this.updateList();
        }
        return true;
    }

    @Override
    public void onYes(String tag, Object data) {
        if (!ListenerUtil.mutListener.listen(355)) {
            if (tag.equals(DIALOG_TAG_BALLOT_DELETE)) {
                if (!ListenerUtil.mutListener.listen(354)) {
                    removeSelectedBallotsDo((SparseBooleanArray) data);
                }
            } else if (tag.equals(ThreemaApplication.CONFIRM_TAG_CLOSE_BALLOT)) {
                if (!ListenerUtil.mutListener.listen(353)) {
                    BallotUtil.closeBallot(this, (BallotModel) data, ballotService);
                }
            }
        }
    }

    @Override
    public void onNo(String tag, Object data) {
    }

    @Override
    public void onClick(String tag, int which, Object data) {
        final BallotModel ballotModel = (BallotModel) data;
        if (!ListenerUtil.mutListener.listen(359)) {
            switch(which) {
                case SELECTOR_ID_VOTE:
                    if (!ListenerUtil.mutListener.listen(356)) {
                        BallotUtil.openVoteDialog(this.getSupportFragmentManager(), ballotModel, myIdentity);
                    }
                    break;
                case SELECTOR_ID_RESULTS:
                    if (!ListenerUtil.mutListener.listen(357)) {
                        BallotUtil.openMatrixActivity(this, ballotModel, myIdentity);
                    }
                    break;
                case SELECTOR_ID_CLOSE:
                    if (!ListenerUtil.mutListener.listen(358)) {
                        BallotUtil.requestCloseBallot(ballotModel, myIdentity, null, this);
                    }
                    break;
            }
        }
    }

    @Override
    public void onCancel(String tag) {
    }

    @Override
    public void onNo(String tag) {
    }

    public class MessageSectionAction implements ActionMode.Callback {

        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            if (!ListenerUtil.mutListener.listen(360)) {
                mode.getMenuInflater().inflate(R.menu.action_ballot_overview, menu);
            }
            if (!ListenerUtil.mutListener.listen(361)) {
                ConfigUtils.themeMenu(menu, ConfigUtils.getColorFromAttribute(BallotOverviewActivity.this, R.attr.colorAccent));
            }
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            final int checked = listView.getCheckedItemCount();
            final int firstCheckedItem = getFirstCheckedPosition(listView);
            if (!ListenerUtil.mutListener.listen(362)) {
                if (firstCheckedItem == AbsListView.INVALID_POSITION) {
                    return false;
                }
            }
            if (!ListenerUtil.mutListener.listen(363)) {
                mode.setTitle(String.format(getString(R.string.num_items_sected), Integer.toString(checked)));
            }
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            final int firstCheckedItem = getFirstCheckedPosition(listView);
            if (firstCheckedItem == AbsListView.INVALID_POSITION) {
                return false;
            }
            switch(item.getItemId()) {
                case R.id.menu_ballot_remove:
                    if (!ListenerUtil.mutListener.listen(364)) {
                        removeSelectedBallots();
                    }
                    return true;
                default:
                    return false;
            }
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {
            if (!ListenerUtil.mutListener.listen(365)) {
                actionMode = null;
            }
            if (!ListenerUtil.mutListener.listen(366)) {
                deselectItem();
            }
        }
    }
}
