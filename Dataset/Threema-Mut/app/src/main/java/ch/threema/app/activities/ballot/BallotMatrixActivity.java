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

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import androidx.appcompat.app.ActionBar;
import ch.threema.app.R;
import ch.threema.app.ThreemaApplication;
import ch.threema.app.exceptions.FileSystemNotPresentException;
import ch.threema.app.exceptions.NoIdentityException;
import ch.threema.app.listeners.BallotListener;
import ch.threema.app.listeners.BallotVoteListener;
import ch.threema.app.managers.ListenerManager;
import ch.threema.app.managers.ServiceManager;
import ch.threema.app.services.ContactService;
import ch.threema.app.services.GroupService;
import ch.threema.app.services.ballot.BallotMatrixData;
import ch.threema.app.services.ballot.BallotMatrixService;
import ch.threema.app.services.ballot.BallotService;
import ch.threema.app.ui.HintedImageView;
import ch.threema.app.ui.HintedTextView;
import ch.threema.app.utils.IntentDataUtil;
import ch.threema.app.utils.LogUtil;
import ch.threema.app.utils.NameUtil;
import ch.threema.app.utils.RuntimeUtil;
import ch.threema.app.utils.TestUtil;
import ch.threema.app.utils.ViewUtil;
import ch.threema.base.ThreemaException;
import ch.threema.localcrypto.MasterKeyLockedException;
import ch.threema.storage.models.ContactModel;
import ch.threema.storage.models.ballot.BallotModel;
import ch.threema.storage.models.ballot.BallotVoteModel;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class BallotMatrixActivity extends BallotDetailActivity {

    private static final Logger logger = LoggerFactory.getLogger(BallotMatrixActivity.class);

    private BallotService ballotService;

    private ContactService contactService;

    private GroupService groupService;

    private String identity;

    private BallotVoteListener ballotVoteListener = new BallotVoteListener() {

        @Override
        public void onSelfVote(BallotModel ballotModel) {
            if (!ListenerUtil.mutListener.listen(136)) {
                ballotListener.onModified(ballotModel);
            }
        }

        @Override
        public void onVoteChanged(BallotModel ballotModel, String votingIdentity, boolean isFirstVote) {
            if (!ListenerUtil.mutListener.listen(137)) {
                ballotListener.onModified(ballotModel);
            }
        }

        @Override
        public void onVoteRemoved(BallotModel ballotModel, String votingIdentity) {
            if (!ListenerUtil.mutListener.listen(138)) {
                ballotListener.onModified(ballotModel);
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
            if (!ListenerUtil.mutListener.listen(139)) {
                this.onModified(ballotModel);
            }
        }

        @Override
        public void onModified(BallotModel ballotModel) {
            if (!ListenerUtil.mutListener.listen(140)) {
                RuntimeUtil.runOnUiThread(() -> {
                    // keep it simple man!
                    updateView();
                });
            }
        }

        @Override
        public void onCreated(BallotModel ballotModel) {
        }

        @Override
        public void onRemoved(BallotModel ballotModel) {
            if (!ListenerUtil.mutListener.listen(141)) {
                RuntimeUtil.runOnUiThread(() -> {
                    Toast.makeText(BallotMatrixActivity.this, "ballot removed", Toast.LENGTH_SHORT).show();
                    finish();
                });
            }
        }

        @Override
        public boolean handle(BallotModel b) {
            return (ListenerUtil.mutListener.listen(143) ? ((ListenerUtil.mutListener.listen(142) ? (getBallotModel() != null || b != null) : (getBallotModel() != null && b != null)) || getBallotModel().getId() == b.getId()) : ((ListenerUtil.mutListener.listen(142) ? (getBallotModel() != null || b != null) : (getBallotModel() != null && b != null)) && getBallotModel().getId() == b.getId()));
        }
    };

    public void onCreate(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(144)) {
            super.onCreate(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(145)) {
            if (!this.requireInstancesOrExit()) {
                return;
            }
        }
        int ballotId = IntentDataUtil.getBallotId(this.getIntent());
        if (!ListenerUtil.mutListener.listen(155)) {
            if ((ListenerUtil.mutListener.listen(150) ? (ballotId >= 0) : (ListenerUtil.mutListener.listen(149) ? (ballotId <= 0) : (ListenerUtil.mutListener.listen(148) ? (ballotId > 0) : (ListenerUtil.mutListener.listen(147) ? (ballotId < 0) : (ListenerUtil.mutListener.listen(146) ? (ballotId == 0) : (ballotId != 0))))))) {
                try {
                    BallotModel ballotModel = this.ballotService.get(ballotId);
                    if (!ListenerUtil.mutListener.listen(153)) {
                        if (ballotModel == null) {
                            throw new ThreemaException("invalid ballot");
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(154)) {
                        this.setBallotModel(ballotModel);
                    }
                } catch (ThreemaException e) {
                    if (!ListenerUtil.mutListener.listen(151)) {
                        LogUtil.exception(e, this);
                    }
                    if (!ListenerUtil.mutListener.listen(152)) {
                        finish();
                    }
                    return;
                }
            }
        }
        ActionBar actionBar = getSupportActionBar();
        if (!ListenerUtil.mutListener.listen(160)) {
            if (actionBar != null) {
                if (!ListenerUtil.mutListener.listen(156)) {
                    actionBar.setDisplayHomeAsUpEnabled(true);
                }
                if (!ListenerUtil.mutListener.listen(159)) {
                    if (getBallotModel().getState() == BallotModel.State.CLOSED) {
                        if (!ListenerUtil.mutListener.listen(158)) {
                            actionBar.setTitle(R.string.ballot_result_final);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(157)) {
                            actionBar.setTitle(R.string.ballot_result_intermediate);
                        }
                    }
                }
            }
        }
        TextView textView = findViewById(R.id.text_view);
        if (!ListenerUtil.mutListener.listen(162)) {
            if (TestUtil.required(textView, this.getBallotModel().getName())) {
                if (!ListenerUtil.mutListener.listen(161)) {
                    textView.setText(this.getBallotModel().getName());
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(163)) {
            ListenerManager.ballotListeners.add(this.ballotListener);
        }
        if (!ListenerUtil.mutListener.listen(164)) {
            ListenerManager.ballotVoteListeners.add(this.ballotVoteListener);
        }
        if (!ListenerUtil.mutListener.listen(165)) {
            this.updateView();
        }
    }

    @Override
    public int getLayoutResource() {
        return R.layout.activity_ballot_matrix;
    }

    private void refreshList() {
        TableLayout dataTableLayout = findViewById(R.id.matrix_data);
        if (!ListenerUtil.mutListener.listen(167)) {
            if (dataTableLayout != null) {
                if (!ListenerUtil.mutListener.listen(166)) {
                    dataTableLayout.removeAllViews();
                }
            }
        }
        final BallotMatrixData matrixData = this.ballotService.getMatrixData(this.getBallotModelId());
        if (!ListenerUtil.mutListener.listen(170)) {
            if (matrixData == null) {
                if (!ListenerUtil.mutListener.listen(168)) {
                    // wrong data! exit now
                    Toast.makeText(this, "invalid data", Toast.LENGTH_SHORT).show();
                }
                if (!ListenerUtil.mutListener.listen(169)) {
                    finish();
                }
                return;
            }
        }
        // add header row containing names/avatars of participants
        TableRow nameHeaderRow = new TableRow(this);
        View emptyCell = getLayoutInflater().inflate(R.layout.row_cell_ballot_matrix_empty, null);
        if (!ListenerUtil.mutListener.listen(171)) {
            nameHeaderRow.addView(emptyCell);
        }
        View emptyCell2 = getLayoutInflater().inflate(R.layout.row_cell_ballot_matrix_empty, null);
        if (!ListenerUtil.mutListener.listen(172)) {
            nameHeaderRow.addView(emptyCell2);
        }
        if (!ListenerUtil.mutListener.listen(177)) {
            {
                long _loopCounter2 = 0;
                for (BallotMatrixService.Participant p : matrixData.getParticipants()) {
                    ListenerUtil.loopListener.listen("_loopCounter2", ++_loopCounter2);
                    final ContactModel contactModel = this.contactService.getByIdentity(p.getIdentity());
                    View nameCell = getLayoutInflater().inflate(R.layout.row_cell_ballot_matrix_name, null);
                    String name = NameUtil.getDisplayNameOrNickname(contactModel, true);
                    HintedImageView hintedImageView = nameCell.findViewById(R.id.avatar_view);
                    if (!ListenerUtil.mutListener.listen(175)) {
                        if (hintedImageView != null) {
                            if (!ListenerUtil.mutListener.listen(173)) {
                                hintedImageView.setContentDescription(name);
                            }
                            Bitmap avatar = contactService.getAvatar(contactModel, false);
                            if (!ListenerUtil.mutListener.listen(174)) {
                                hintedImageView.setImageBitmap(avatar);
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(176)) {
                        nameHeaderRow.addView(nameCell);
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(178)) {
            dataTableLayout.addView(nameHeaderRow);
        }
        if (!ListenerUtil.mutListener.listen(196)) {
            {
                long _loopCounter4 = 0;
                for (BallotMatrixService.Choice c : matrixData.getChoices()) {
                    ListenerUtil.loopListener.listen("_loopCounter4", ++_loopCounter4);
                    // create a new row for each answer
                    TableRow row = new TableRow(this);
                    // add answer first
                    View headerCell = getLayoutInflater().inflate(R.layout.row_cell_ballot_matrix_choice_label, null);
                    if (!ListenerUtil.mutListener.listen(179)) {
                        ((HintedTextView) headerCell.findViewById(R.id.choice_label)).setText(c.getBallotChoiceModel().getName());
                    }
                    if (!ListenerUtil.mutListener.listen(180)) {
                        row.addView(headerCell);
                    }
                    // add sums
                    View sumCell = getLayoutInflater().inflate(R.layout.row_cell_ballot_matrix_choice_sum, null);
                    TextView sumText = sumCell.findViewById(R.id.voting_sum);
                    if (!ListenerUtil.mutListener.listen(181)) {
                        sumText.setText(String.valueOf(c.getVoteCount()));
                    }
                    if (!ListenerUtil.mutListener.listen(184)) {
                        if (c.isWinner()) {
                            if (!ListenerUtil.mutListener.listen(182)) {
                                sumCell.findViewById(R.id.cell).setBackgroundResource(R.drawable.matrix_winner_cell);
                            }
                            if (!ListenerUtil.mutListener.listen(183)) {
                                sumText.setTextColor(getResources().getColor(android.R.color.white));
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(185)) {
                        row.addView(sumCell);
                    }
                    if (!ListenerUtil.mutListener.listen(194)) {
                        {
                            long _loopCounter3 = 0;
                            for (BallotMatrixService.Participant p : matrixData.getParticipants()) {
                                ListenerUtil.loopListener.listen("_loopCounter3", ++_loopCounter3);
                                View choiceVoteView;
                                if (c.isWinner()) {
                                    choiceVoteView = getLayoutInflater().inflate(R.layout.row_cell_ballot_matrix_choice_winner, null);
                                } else {
                                    choiceVoteView = getLayoutInflater().inflate(R.layout.row_cell_ballot_matrix_choice, null);
                                }
                                BallotVoteModel vote = matrixData.getVote(p, c);
                                if (!ListenerUtil.mutListener.listen(188)) {
                                    ViewUtil.show((View) choiceVoteView.findViewById(R.id.voting_value_1), (ListenerUtil.mutListener.listen(187) ? ((ListenerUtil.mutListener.listen(186) ? (p.hasVoted() || vote != null) : (p.hasVoted() && vote != null)) || vote.getChoice() == 1) : ((ListenerUtil.mutListener.listen(186) ? (p.hasVoted() || vote != null) : (p.hasVoted() && vote != null)) && vote.getChoice() == 1)));
                                }
                                if (!ListenerUtil.mutListener.listen(191)) {
                                    ViewUtil.show((View) choiceVoteView.findViewById(R.id.voting_value_0), (ListenerUtil.mutListener.listen(190) ? (p.hasVoted() || ((ListenerUtil.mutListener.listen(189) ? (vote == null && vote.getChoice() != 1) : (vote == null || vote.getChoice() != 1)))) : (p.hasVoted() && ((ListenerUtil.mutListener.listen(189) ? (vote == null && vote.getChoice() != 1) : (vote == null || vote.getChoice() != 1))))));
                                }
                                if (!ListenerUtil.mutListener.listen(192)) {
                                    ViewUtil.show((View) choiceVoteView.findViewById(R.id.voting_value_none), !p.hasVoted());
                                }
                                if (!ListenerUtil.mutListener.listen(193)) {
                                    row.addView(choiceVoteView);
                                }
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(195)) {
                        dataTableLayout.addView(row);
                    }
                }
            }
        }
    }

    private void updateView() {
        if (!ListenerUtil.mutListener.listen(197)) {
            this.refreshList();
        }
    }

    @Override
    protected void onDestroy() {
        if (!ListenerUtil.mutListener.listen(198)) {
            ListenerManager.ballotListeners.remove(this.ballotListener);
        }
        if (!ListenerUtil.mutListener.listen(199)) {
            ListenerManager.ballotVoteListeners.remove(this.ballotVoteListener);
        }
        if (!ListenerUtil.mutListener.listen(200)) {
            super.onDestroy();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (!ListenerUtil.mutListener.listen(202)) {
            switch(item.getItemId()) {
                case android.R.id.home:
                    if (!ListenerUtil.mutListener.listen(201)) {
                        finish();
                    }
                    break;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected boolean checkInstances() {
        return TestUtil.required(this.ballotService, this.contactService, this.groupService, this.identity);
    }

    @Override
    protected void instantiate() {
        if (!ListenerUtil.mutListener.listen(203)) {
            super.instantiate();
        }
        ServiceManager serviceManager = ThreemaApplication.getServiceManager();
        if (!ListenerUtil.mutListener.listen(209)) {
            if (serviceManager != null) {
                try {
                    if (!ListenerUtil.mutListener.listen(205)) {
                        this.ballotService = serviceManager.getBallotService();
                    }
                    if (!ListenerUtil.mutListener.listen(206)) {
                        this.identity = serviceManager.getUserService().getIdentity();
                    }
                    if (!ListenerUtil.mutListener.listen(207)) {
                        this.contactService = serviceManager.getContactService();
                    }
                    if (!ListenerUtil.mutListener.listen(208)) {
                        this.groupService = serviceManager.getGroupService();
                    }
                } catch (NoIdentityException | MasterKeyLockedException | FileSystemNotPresentException e) {
                    if (!ListenerUtil.mutListener.listen(204)) {
                        logger.error("Exception", e);
                    }
                }
            }
        }
    }

    private boolean requireInstancesOrExit() {
        if (!ListenerUtil.mutListener.listen(212)) {
            if (!this.requiredInstances()) {
                if (!ListenerUtil.mutListener.listen(210)) {
                    logger.error("Required instances failed");
                }
                if (!ListenerUtil.mutListener.listen(211)) {
                    this.finish();
                }
                return false;
            }
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        if (!ListenerUtil.mutListener.listen(213)) {
            setResult(RESULT_OK);
        }
        if (!ListenerUtil.mutListener.listen(214)) {
            finish();
        }
    }

    @Override
    BallotService getBallotService() {
        if (!ListenerUtil.mutListener.listen(215)) {
            if (this.requiredInstances()) {
                return this.ballotService;
            }
        }
        return null;
    }

    @Override
    public ContactService getContactService() {
        if (!ListenerUtil.mutListener.listen(216)) {
            if (requiredInstances()) {
                return this.contactService;
            }
        }
        return null;
    }

    @Override
    public GroupService getGroupService() {
        if (!ListenerUtil.mutListener.listen(217)) {
            if (requiredInstances()) {
                return this.groupService;
            }
        }
        return null;
    }

    @Override
    public String getIdentity() {
        if (!ListenerUtil.mutListener.listen(218)) {
            if (requiredInstances()) {
                return this.identity;
            }
        }
        return null;
    }
}
