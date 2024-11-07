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
package ch.threema.app.dialogs;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import androidx.annotation.NonNull;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialog;
import ch.threema.app.R;
import ch.threema.app.ThreemaApplication;
import ch.threema.app.adapters.ballot.BallotVoteListAdapter;
import ch.threema.app.emojis.EmojiConversationTextView;
import ch.threema.app.exceptions.NotAllowedException;
import ch.threema.app.listeners.BallotListener;
import ch.threema.app.listeners.BallotVoteListener;
import ch.threema.app.managers.ListenerManager;
import ch.threema.app.managers.ServiceManager;
import ch.threema.app.services.ballot.BallotService;
import ch.threema.app.services.ballot.BallotVoteResult;
import ch.threema.app.ui.CheckableRelativeLayout;
import ch.threema.app.utils.BallotUtil;
import ch.threema.app.utils.LoadingUtil;
import ch.threema.app.utils.RuntimeUtil;
import ch.threema.storage.models.ballot.BallotChoiceModel;
import ch.threema.storage.models.ballot.BallotModel;
import ch.threema.storage.models.ballot.BallotVoteModel;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class BallotVoteDialog extends ThreemaDialogFragment {

    private static final Logger logger = LoggerFactory.getLogger(BallotVoteDialog.class);

    private Activity activity;

    private AlertDialog alertDialog;

    private ListView listView;

    private BallotModel ballotModel;

    private BallotService ballotService;

    private String identity;

    private int ballotId;

    private BallotVoteListAdapter listAdapter = null;

    private EmojiConversationTextView titleTextView;

    private boolean disableBallotModelListener = false;

    private Thread votingThread = null;

    private BallotListener ballotListener = new BallotListener() {

        @Override
        public void onClosed(BallotModel ballotModel) {
        }

        @Override
        public void onModified(BallotModel ballotModel) {
            if (!ListenerUtil.mutListener.listen(13109)) {
                RuntimeUtil.runOnUiThread(() -> updateView());
            }
        }

        @Override
        public void onCreated(BallotModel ballotModel) {
        }

        @Override
        public void onRemoved(BallotModel ballotModel) {
            if (!ListenerUtil.mutListener.listen(13110)) {
                RuntimeUtil.runOnUiThread(() -> {
                    Toast.makeText(getContext(), "ballot removed", Toast.LENGTH_SHORT).show();
                    dismiss();
                });
            }
        }

        @Override
        public boolean handle(BallotModel ballotModel) {
            return (ListenerUtil.mutListener.listen(13111) ? (!disableBallotModelListener || ballotId == ballotModel.getId()) : (!disableBallotModelListener && ballotId == ballotModel.getId()));
        }
    };

    private BallotVoteListener ballotVoteListener = new BallotVoteListener() {

        @Override
        public void onSelfVote(BallotModel ballotModel) {
        }

        @Override
        public void onVoteChanged(BallotModel ballotModel, String votingIdentity, boolean isFirstVote) {
            if (!ListenerUtil.mutListener.listen(13112)) {
                RuntimeUtil.runOnUiThread(() -> updateView());
            }
        }

        @Override
        public void onVoteRemoved(BallotModel ballotModel, String votingIdentity) {
            if (!ListenerUtil.mutListener.listen(13113)) {
                RuntimeUtil.runOnUiThread(() -> updateView());
            }
        }

        @Override
        public boolean handle(BallotModel b) {
            return (ListenerUtil.mutListener.listen(13115) ? ((ListenerUtil.mutListener.listen(13114) ? (b != null || ballotModel != null) : (b != null && ballotModel != null)) || b.getId() == ballotModel.getId()) : ((ListenerUtil.mutListener.listen(13114) ? (b != null || ballotModel != null) : (b != null && ballotModel != null)) && b.getId() == ballotModel.getId()));
        }
    };

    public static BallotVoteDialog newInstance(@StringRes int ballotId) {
        BallotVoteDialog dialog = new BallotVoteDialog();
        Bundle args = new Bundle();
        if (!ListenerUtil.mutListener.listen(13116)) {
            args.putInt("ballotId", ballotId);
        }
        if (!ListenerUtil.mutListener.listen(13117)) {
            dialog.setArguments(args);
        }
        return dialog;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(13118)) {
            super.onCreate(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(13119)) {
            ListenerManager.ballotListeners.add(this.ballotListener);
        }
        if (!ListenerUtil.mutListener.listen(13120)) {
            ListenerManager.ballotVoteListeners.add(this.ballotVoteListener);
        }
    }

    @Override
    public void onAttach(@NonNull Activity activity) {
        if (!ListenerUtil.mutListener.listen(13121)) {
            super.onAttach(activity);
        }
        if (!ListenerUtil.mutListener.listen(13122)) {
            this.activity = activity;
        }
    }

    @Override
    public void onDetach() {
        if (!ListenerUtil.mutListener.listen(13123)) {
            this.activity = null;
        }
        if (!ListenerUtil.mutListener.listen(13124)) {
            super.onDetach();
        }
    }

    @NonNull
    @Override
    public AppCompatDialog onCreateDialog(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(13126)) {
            if ((ListenerUtil.mutListener.listen(13125) ? (savedInstanceState != null || alertDialog != null) : (savedInstanceState != null && alertDialog != null))) {
                return alertDialog;
            }
        }
        ServiceManager serviceManager = ThreemaApplication.getServiceManager();
        if (!ListenerUtil.mutListener.listen(13130)) {
            if (serviceManager != null) {
                try {
                    if (!ListenerUtil.mutListener.listen(13128)) {
                        this.ballotService = serviceManager.getBallotService();
                    }
                    if (!ListenerUtil.mutListener.listen(13129)) {
                        this.identity = serviceManager.getUserService().getIdentity();
                    }
                } catch (Exception e) {
                    if (!ListenerUtil.mutListener.listen(13127)) {
                        logger.error("Exception", e);
                    }
                    return null;
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(13131)) {
            ballotId = getArguments().getInt("ballotId");
        }
        if (!ListenerUtil.mutListener.listen(13132)) {
            ballotModel = ballotService.get(ballotId);
        }
        final View dialogView = activity.getLayoutInflater().inflate(R.layout.dialog_ballot_vote, null);
        if (!ListenerUtil.mutListener.listen(13133)) {
            this.listView = dialogView.findViewById(R.id.ballot_list);
        }
        if (!ListenerUtil.mutListener.listen(13134)) {
            this.titleTextView = dialogView.findViewById(R.id.title);
        }
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(getActivity(), getTheme());
        if (!ListenerUtil.mutListener.listen(13135)) {
            builder.setView(dialogView);
        }
        if (!ListenerUtil.mutListener.listen(13136)) {
            builder.setPositiveButton(getString(R.string.ballot_vote), (dialog, whichButton) -> vote());
        }
        if (!ListenerUtil.mutListener.listen(13137)) {
            builder.setNegativeButton(R.string.cancel, (dialog, whichButton) -> dismiss());
        }
        if (!ListenerUtil.mutListener.listen(13138)) {
            alertDialog = builder.create();
        }
        if (!ListenerUtil.mutListener.listen(13140)) {
            if (titleTextView != null) {
                if (!ListenerUtil.mutListener.listen(13139)) {
                    titleTextView.setText(ballotModel.getName());
                }
            }
        }
        return alertDialog;
    }

    @Override
    public void onStart() {
        if (!ListenerUtil.mutListener.listen(13141)) {
            super.onStart();
        }
        if (!ListenerUtil.mutListener.listen(13144)) {
            if (this.listView != null) {
                if (!ListenerUtil.mutListener.listen(13142)) {
                    this.listView.setOnItemClickListener((adapterView, view, i, l) -> {
                        ((CheckableRelativeLayout) view).toggle();
                    });
                }
                if (!ListenerUtil.mutListener.listen(13143)) {
                    this.listView.setClipToPadding(false);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(13145)) {
            this.updateView();
        }
    }

    @Override
    public void onDestroy() {
        if (!ListenerUtil.mutListener.listen(13146)) {
            ListenerManager.ballotListeners.remove(this.ballotListener);
        }
        if (!ListenerUtil.mutListener.listen(13147)) {
            ListenerManager.ballotVoteListeners.remove(this.ballotVoteListener);
        }
        if (!ListenerUtil.mutListener.listen(13148)) {
            super.onDestroy();
        }
    }

    private void updateView() {
        try {
            if (!ListenerUtil.mutListener.listen(13156)) {
                if ((ListenerUtil.mutListener.listen(13154) ? (this.ballotId >= 0) : (ListenerUtil.mutListener.listen(13153) ? (this.ballotId > 0) : (ListenerUtil.mutListener.listen(13152) ? (this.ballotId < 0) : (ListenerUtil.mutListener.listen(13151) ? (this.ballotId != 0) : (ListenerUtil.mutListener.listen(13150) ? (this.ballotId == 0) : (this.ballotId <= 0))))))) {
                    if (!ListenerUtil.mutListener.listen(13155)) {
                        dismiss();
                    }
                    return;
                }
            }
            try {
                if (!ListenerUtil.mutListener.listen(13158)) {
                    this.disableBallotModelListener = true;
                }
                BallotModel ballotModel = this.ballotService.get(this.ballotId);
                if (!ListenerUtil.mutListener.listen(13163)) {
                    if ((ListenerUtil.mutListener.listen(13159) ? (ballotModel == null || activity != null) : (ballotModel == null && activity != null))) {
                        if (!ListenerUtil.mutListener.listen(13160)) {
                            Toast.makeText(activity, R.string.ballot_not_exist, Toast.LENGTH_SHORT).show();
                        }
                        if (!ListenerUtil.mutListener.listen(13161)) {
                            logger.error("invalid ballot model");
                        }
                        if (!ListenerUtil.mutListener.listen(13162)) {
                            dismiss();
                        }
                        return;
                    }
                }
                if (!ListenerUtil.mutListener.listen(13164)) {
                    this.ballotService.viewingBallot(ballotModel, true);
                }
            } finally {
                if (!ListenerUtil.mutListener.listen(13157)) {
                    // important!
                    this.disableBallotModelListener = false;
                }
            }
            Map<Integer, Integer> selected;
            if (this.listAdapter != null) {
                selected = this.listAdapter.getSelectedChoices();
            } else {
                // load from db
                selected = new HashMap<>();
                if (!ListenerUtil.mutListener.listen(13166)) {
                    {
                        long _loopCounter130 = 0;
                        for (final BallotVoteModel c : this.ballotService.getMyVotes(this.ballotId)) {
                            ListenerUtil.loopListener.listen("_loopCounter130", ++_loopCounter130);
                            if (!ListenerUtil.mutListener.listen(13165)) {
                                selected.put(c.getBallotChoiceId(), c.getChoice());
                            }
                        }
                    }
                }
            }
            List<BallotChoiceModel> ballotChoiceModelList = this.ballotService.getChoices(this.ballotId);
            boolean showVoting = (ListenerUtil.mutListener.listen(13167) ? (this.ballotModel.getType() == BallotModel.Type.INTERMEDIATE && this.ballotModel.getState() == BallotModel.State.CLOSED) : (this.ballotModel.getType() == BallotModel.Type.INTERMEDIATE || this.ballotModel.getState() == BallotModel.State.CLOSED));
            if (!ListenerUtil.mutListener.listen(13168)) {
                this.listAdapter = new BallotVoteListAdapter(getContext(), ballotChoiceModelList, selected, this.ballotModel.getState() != BallotModel.State.OPEN, this.ballotModel.getAssessment() == BallotModel.Assessment.MULTIPLE_CHOICE, showVoting);
            }
            if (!ListenerUtil.mutListener.listen(13169)) {
                this.listView.setAdapter(this.listAdapter);
            }
        } catch (NotAllowedException e) {
            if (!ListenerUtil.mutListener.listen(13149)) {
                logger.error("cannot reload choices", e);
            }
        }
    }

    private void vote() {
        if (!ListenerUtil.mutListener.listen(13170)) {
            // show loading
            if (!BallotUtil.canVote(this.ballotModel, this.identity)) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(13171)) {
            logger.debug("vote");
        }
        if (!ListenerUtil.mutListener.listen(13174)) {
            if ((ListenerUtil.mutListener.listen(13172) ? (this.votingThread != null || this.votingThread.isAlive()) : (this.votingThread != null && this.votingThread.isAlive()))) {
                if (!ListenerUtil.mutListener.listen(13173)) {
                    logger.debug("voting thread alive, abort");
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(13175)) {
            logger.debug("create new voting thread");
        }
        if (!ListenerUtil.mutListener.listen(13179)) {
            this.votingThread = LoadingUtil.runInAlert(getFragmentManager(), R.string.ballot_vote, R.string.please_wait, new Runnable() {

                @Override
                public void run() {
                    try {
                        if (!ListenerUtil.mutListener.listen(13177)) {
                            voteThread();
                        }
                        if (!ListenerUtil.mutListener.listen(13178)) {
                            dismiss();
                        }
                    } catch (Exception x) {
                        if (!ListenerUtil.mutListener.listen(13176)) {
                            logger.error("Exception", x);
                        }
                    }
                }
            });
        }
    }

    private void voteThread() {
        if (!ListenerUtil.mutListener.listen(13180)) {
            if (!BallotUtil.canVote(ballotModel, identity)) {
                return;
            }
        }
        try {
            final BallotVoteResult result = this.ballotService.vote(ballotModel.getId(), this.listAdapter.getSelectedChoices());
            if (!ListenerUtil.mutListener.listen(13183)) {
                if (result != null) {
                    if (!ListenerUtil.mutListener.listen(13182)) {
                        RuntimeUtil.runOnUiThread(() -> {
                            if (activity != null) {
                                if (result.isSuccess()) {
                                    Toast.makeText(activity, R.string.ballot_vote_posted_successfully, Toast.LENGTH_SHORT).show();
                                    dismiss();
                                } else {
                                    Toast.makeText(activity, R.string.ballot_vote_posted_failed, Toast.LENGTH_SHORT).show();
                                    updateView();
                                }
                            }
                        });
                    }
                }
            }
        } catch (final NotAllowedException e) {
            if (!ListenerUtil.mutListener.listen(13181)) {
                RuntimeUtil.runOnUiThread(() -> {
                    if (activity != null) {
                        Toast.makeText(activity, e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
            }
        }
    }
}
