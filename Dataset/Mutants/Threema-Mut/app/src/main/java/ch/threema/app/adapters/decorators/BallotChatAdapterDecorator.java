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
package ch.threema.app.adapters.decorators;

import android.content.Context;
import android.os.Parcel;
import android.view.View;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.ArrayList;
import ch.threema.app.R;
import ch.threema.app.ThreemaApplication;
import ch.threema.app.dialogs.SelectorDialog;
import ch.threema.app.exceptions.NotAllowedException;
import ch.threema.app.services.GroupService;
import ch.threema.app.ui.listitemholder.ComposeMessageHolder;
import ch.threema.app.utils.BallotUtil;
import ch.threema.storage.models.AbstractMessageModel;
import ch.threema.storage.models.GroupMessageModel;
import ch.threema.storage.models.GroupModel;
import ch.threema.storage.models.ballot.BallotModel;
import ch.threema.storage.models.data.media.BallotDataModel;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class BallotChatAdapterDecorator extends ChatAdapterDecorator {

    private static final Logger logger = LoggerFactory.getLogger(BallotChatAdapterDecorator.class);

    private static final int ACTION_VOTE = 0, ACTION_RESULTS = 1, ACTION_CLOSE = 2;

    public BallotChatAdapterDecorator(Context context, AbstractMessageModel messageModel, Helper helper) {
        super(context, messageModel, helper);
    }

    @Override
    protected void configureChatMessage(final ComposeMessageHolder holder, final int position) {
        try {
            final AbstractMessageModel messageModel = this.getMessageModel();
            String explain = "";
            BallotDataModel ballotData = messageModel.getBallotData();
            if (!ListenerUtil.mutListener.listen(7480)) {
                if (ballotData == null) {
                    throw new NotAllowedException("invalid ballot message");
                }
            }
            final BallotModel ballotModel = this.helper.getBallotService().get(ballotData.getBallotId());
            if (!ListenerUtil.mutListener.listen(7491)) {
                if (ballotModel == null) {
                    if (!ListenerUtil.mutListener.listen(7489)) {
                        explain = "";
                    }
                    if (!ListenerUtil.mutListener.listen(7490)) {
                        holder.bodyTextView.setText("");
                    }
                } else {
                    if (!ListenerUtil.mutListener.listen(7486)) {
                        switch(ballotData.getType()) {
                            case BALLOT_CREATED:
                                if (!ListenerUtil.mutListener.listen(7482)) {
                                    if (ballotModel.getState() != BallotModel.State.CLOSED) {
                                        if (!ListenerUtil.mutListener.listen(7481)) {
                                            explain = getContext().getString(R.string.ballot_tap_to_vote);
                                        }
                                    }
                                }
                                break;
                            case BALLOT_MODIFIED:
                                if (!ListenerUtil.mutListener.listen(7484)) {
                                    if (ballotModel.getState() != BallotModel.State.CLOSED) {
                                        if (!ListenerUtil.mutListener.listen(7483)) {
                                            explain = getContext().getString(R.string.ballot_tap_to_vote);
                                        }
                                    }
                                }
                                break;
                            case BALLOT_CLOSED:
                                if (!ListenerUtil.mutListener.listen(7485)) {
                                    explain = getContext().getString(R.string.ballot_tap_to_view_results);
                                }
                                break;
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(7488)) {
                        if (this.showHide(holder.bodyTextView, true)) {
                            if (!ListenerUtil.mutListener.listen(7487)) {
                                holder.bodyTextView.setText(ballotModel.getName());
                            }
                        }
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(7493)) {
                if (this.showHide(holder.secondaryTextView, true)) {
                    if (!ListenerUtil.mutListener.listen(7492)) {
                        holder.secondaryTextView.setText(explain);
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(7495)) {
                this.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {
                        if (!ListenerUtil.mutListener.listen(7494)) {
                            onActionButtonClick(ballotModel);
                        }
                    }
                }, holder.messageBlockView);
            }
            if (!ListenerUtil.mutListener.listen(7497)) {
                if (holder.controller != null) {
                    if (!ListenerUtil.mutListener.listen(7496)) {
                        holder.controller.setImageResource(R.drawable.ic_outline_rule);
                    }
                }
            }
        } catch (NotAllowedException x) {
            if (!ListenerUtil.mutListener.listen(7479)) {
                logger.error("Exception", x);
            }
        }
    }

    private void onActionButtonClick(final BallotModel ballotModel) {
        if (!ListenerUtil.mutListener.listen(7504)) {
            if (getMessageModel() instanceof GroupMessageModel) {
                try {
                    GroupService groupService = ThreemaApplication.getServiceManager().getGroupService();
                    GroupMessageModel groupMessageModel = (GroupMessageModel) getMessageModel();
                    if (!ListenerUtil.mutListener.listen(7503)) {
                        if (groupService != null) {
                            GroupModel groupModel = groupService.getById(groupMessageModel.getGroupId());
                            if (!ListenerUtil.mutListener.listen(7502)) {
                                if (groupModel != null) {
                                    if (!ListenerUtil.mutListener.listen(7501)) {
                                        if (groupService.isGroupMember(groupModel)) {
                                            if (!ListenerUtil.mutListener.listen(7500)) {
                                                showChooser(ballotModel);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                } catch (Exception e) {
                    if (!ListenerUtil.mutListener.listen(7499)) {
                        logger.error("Exception", e);
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(7498)) {
                    showChooser(ballotModel);
                }
            }
        }
    }

    private void showChooser(final BallotModel ballotModel) {
        final ArrayList<String> items = new ArrayList<>();
        final ArrayList<Integer> action = new ArrayList<>();
        String title = null;
        if (!ListenerUtil.mutListener.listen(7507)) {
            if (BallotUtil.canVote(ballotModel, helper.getMyIdentity())) {
                if (!ListenerUtil.mutListener.listen(7505)) {
                    items.add(getContext().getString(R.string.ballot_vote));
                }
                if (!ListenerUtil.mutListener.listen(7506)) {
                    action.add(ACTION_VOTE);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(7512)) {
            if (BallotUtil.canViewMatrix(ballotModel, helper.getMyIdentity())) {
                if (!ListenerUtil.mutListener.listen(7510)) {
                    if (ballotModel.getState() == BallotModel.State.CLOSED) {
                        if (!ListenerUtil.mutListener.listen(7509)) {
                            items.add(getContext().getString(R.string.ballot_result_final));
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(7508)) {
                            items.add(getContext().getString(R.string.ballot_result_intermediate));
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(7511)) {
                    action.add(ACTION_RESULTS);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(7515)) {
            if (BallotUtil.canClose(ballotModel, helper.getMyIdentity())) {
                if (!ListenerUtil.mutListener.listen(7513)) {
                    items.add(getContext().getString(R.string.ballot_close));
                }
                if (!ListenerUtil.mutListener.listen(7514)) {
                    action.add(ACTION_CLOSE);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(7518)) {
            if ((ListenerUtil.mutListener.listen(7516) ? (BallotUtil.canClose(ballotModel, helper.getMyIdentity()) && BallotUtil.canViewMatrix(ballotModel, helper.getMyIdentity())) : (BallotUtil.canClose(ballotModel, helper.getMyIdentity()) || BallotUtil.canViewMatrix(ballotModel, helper.getMyIdentity())))) {
                if (!ListenerUtil.mutListener.listen(7517)) {
                    title = String.format(getContext().getString(R.string.ballot_received_votes), helper.getBallotService().getVotedParticipants(ballotModel.getId()).size(), helper.getBallotService().getParticipants(ballotModel.getId()).length);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(7530)) {
            if ((ListenerUtil.mutListener.listen(7523) ? (items.size() >= 1) : (ListenerUtil.mutListener.listen(7522) ? (items.size() <= 1) : (ListenerUtil.mutListener.listen(7521) ? (items.size() < 1) : (ListenerUtil.mutListener.listen(7520) ? (items.size() != 1) : (ListenerUtil.mutListener.listen(7519) ? (items.size() == 1) : (items.size() > 1))))))) {
                SelectorDialog selectorDialog = SelectorDialog.newInstance(title, items, null, new SelectorDialog.SelectorDialogInlineClickListener() {

                    @Override
                    public void onClick(String tag, int which, Object data) {
                        if (!ListenerUtil.mutListener.listen(7528)) {
                            switch(action.get(which)) {
                                case ACTION_VOTE:
                                    if (!ListenerUtil.mutListener.listen(7525)) {
                                        BallotUtil.openVoteDialog(helper.getFragment().getFragmentManager(), ballotModel, helper.getMyIdentity());
                                    }
                                    break;
                                case ACTION_RESULTS:
                                    if (!ListenerUtil.mutListener.listen(7526)) {
                                        BallotUtil.openMatrixActivity(getContext(), ballotModel, helper.getMyIdentity());
                                    }
                                    break;
                                case ACTION_CLOSE:
                                    if (!ListenerUtil.mutListener.listen(7527)) {
                                        BallotUtil.requestCloseBallot(ballotModel, helper.getMyIdentity(), helper.getFragment(), null);
                                    }
                                    break;
                                default:
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

                    @Override
                    public int describeContents() {
                        return 0;
                    }

                    @Override
                    public void writeToParcel(Parcel dest, int flags) {
                    }
                });
                if (!ListenerUtil.mutListener.listen(7529)) {
                    selectorDialog.show(helper.getFragment().getFragmentManager(), "chooseAction");
                }
            } else {
                if (!ListenerUtil.mutListener.listen(7524)) {
                    BallotUtil.openDefaultActivity(getContext(), helper.getFragment().getFragmentManager(), ballotModel, helper.getMyIdentity());
                }
            }
        }
    }
}
