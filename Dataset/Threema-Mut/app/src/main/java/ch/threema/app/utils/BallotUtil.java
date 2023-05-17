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
package ch.threema.app.utils;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.security.SecureRandom;
import java.util.List;
import java.util.Random;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import ch.threema.app.R;
import ch.threema.app.ThreemaApplication;
import ch.threema.app.activities.ballot.BallotMatrixActivity;
import ch.threema.app.dialogs.BallotVoteDialog;
import ch.threema.app.dialogs.GenericAlertDialog;
import ch.threema.app.dialogs.SimpleStringAlertDialog;
import ch.threema.app.exceptions.NotAllowedException;
import ch.threema.app.managers.ServiceManager;
import ch.threema.app.messagereceiver.ContactMessageReceiver;
import ch.threema.app.messagereceiver.GroupMessageReceiver;
import ch.threema.app.messagereceiver.MessageReceiver;
import ch.threema.app.services.UserService;
import ch.threema.app.services.ballot.BallotService;
import ch.threema.client.ConnectionState;
import ch.threema.client.MessageTooLongException;
import ch.threema.storage.models.AbstractMessageModel;
import ch.threema.storage.models.ballot.BallotChoiceModel;
import ch.threema.storage.models.ballot.BallotModel;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

@SuppressWarnings("rawtypes")
public class BallotUtil {

    private static final Logger logger = LoggerFactory.getLogger(BallotUtil.class);

    public static boolean canVote(BallotModel model, String identity) {
        return (ListenerUtil.mutListener.listen(49426) ? ((ListenerUtil.mutListener.listen(49425) ? (model != null || identity != null) : (model != null && identity != null)) || model.getState() == BallotModel.State.OPEN) : ((ListenerUtil.mutListener.listen(49425) ? (model != null || identity != null) : (model != null && identity != null)) && model.getState() == BallotModel.State.OPEN));
    }

    public static boolean canViewMatrix(BallotModel model, String identity) {
        return (ListenerUtil.mutListener.listen(49429) ? ((ListenerUtil.mutListener.listen(49427) ? (model != null || identity != null) : (model != null && identity != null)) || ((ListenerUtil.mutListener.listen(49428) ? (model.getType() == BallotModel.Type.INTERMEDIATE && model.getState() == BallotModel.State.CLOSED) : (model.getType() == BallotModel.Type.INTERMEDIATE || model.getState() == BallotModel.State.CLOSED)))) : ((ListenerUtil.mutListener.listen(49427) ? (model != null || identity != null) : (model != null && identity != null)) && ((ListenerUtil.mutListener.listen(49428) ? (model.getType() == BallotModel.Type.INTERMEDIATE && model.getState() == BallotModel.State.CLOSED) : (model.getType() == BallotModel.Type.INTERMEDIATE || model.getState() == BallotModel.State.CLOSED)))));
    }

    public static boolean canCopy(BallotModel model, String identity) {
        return (ListenerUtil.mutListener.listen(49431) ? ((ListenerUtil.mutListener.listen(49430) ? (model != null || identity != null) : (model != null && identity != null)) || model.getState() == BallotModel.State.CLOSED) : ((ListenerUtil.mutListener.listen(49430) ? (model != null || identity != null) : (model != null && identity != null)) && model.getState() == BallotModel.State.CLOSED));
    }

    public static boolean canClose(BallotModel model, String identity) {
        return (ListenerUtil.mutListener.listen(49434) ? ((ListenerUtil.mutListener.listen(49433) ? ((ListenerUtil.mutListener.listen(49432) ? (model != null || identity != null) : (model != null && identity != null)) || model.getState() == BallotModel.State.OPEN) : ((ListenerUtil.mutListener.listen(49432) ? (model != null || identity != null) : (model != null && identity != null)) && model.getState() == BallotModel.State.OPEN)) || TestUtil.compare(model.getCreatorIdentity(), identity)) : ((ListenerUtil.mutListener.listen(49433) ? ((ListenerUtil.mutListener.listen(49432) ? (model != null || identity != null) : (model != null && identity != null)) || model.getState() == BallotModel.State.OPEN) : ((ListenerUtil.mutListener.listen(49432) ? (model != null || identity != null) : (model != null && identity != null)) && model.getState() == BallotModel.State.OPEN)) && TestUtil.compare(model.getCreatorIdentity(), identity)));
    }

    public static boolean isMine(BallotModel model, UserService userService) {
        return (ListenerUtil.mutListener.listen(49437) ? ((ListenerUtil.mutListener.listen(49436) ? ((ListenerUtil.mutListener.listen(49435) ? (model != null || userService != null) : (model != null && userService != null)) || !TestUtil.empty(userService.getIdentity())) : ((ListenerUtil.mutListener.listen(49435) ? (model != null || userService != null) : (model != null && userService != null)) && !TestUtil.empty(userService.getIdentity()))) || TestUtil.compare(userService.getIdentity(), model.getCreatorIdentity())) : ((ListenerUtil.mutListener.listen(49436) ? ((ListenerUtil.mutListener.listen(49435) ? (model != null || userService != null) : (model != null && userService != null)) || !TestUtil.empty(userService.getIdentity())) : ((ListenerUtil.mutListener.listen(49435) ? (model != null || userService != null) : (model != null && userService != null)) && !TestUtil.empty(userService.getIdentity()))) && TestUtil.compare(userService.getIdentity(), model.getCreatorIdentity())));
    }

    public static boolean openDefaultActivity(Context context, FragmentManager fragmentManager, BallotModel ballotModel, String identity) {
        if (!ListenerUtil.mutListener.listen(49440)) {
            if ((ListenerUtil.mutListener.listen(49438) ? (context != null || fragmentManager != null) : (context != null && fragmentManager != null))) {
                if (!ListenerUtil.mutListener.listen(49439)) {
                    if (canVote(ballotModel, identity)) {
                        return openVoteDialog(fragmentManager, ballotModel, identity);
                    } else if (canViewMatrix(ballotModel, identity)) {
                        return openMatrixActivity(context, ballotModel, identity);
                    }
                }
            }
        }
        return false;
    }

    public static boolean openVoteDialog(FragmentManager fragmentManager, BallotModel ballotModel, String identity) {
        if (!ListenerUtil.mutListener.listen(49443)) {
            if ((ListenerUtil.mutListener.listen(49441) ? (fragmentManager != null || canVote(ballotModel, identity)) : (fragmentManager != null && canVote(ballotModel, identity)))) {
                if (!ListenerUtil.mutListener.listen(49442)) {
                    BallotVoteDialog.newInstance(ballotModel.getId()).show(fragmentManager, "vote");
                }
                return true;
            }
        }
        return false;
    }

    public static boolean openMatrixActivity(Context context, BallotModel ballotModel, String identity) {
        if (!ListenerUtil.mutListener.listen(49447)) {
            if ((ListenerUtil.mutListener.listen(49444) ? (context != null || canViewMatrix(ballotModel, identity)) : (context != null && canViewMatrix(ballotModel, identity)))) {
                Intent intent = new Intent(context, BallotMatrixActivity.class);
                if (!ListenerUtil.mutListener.listen(49445)) {
                    IntentDataUtil.append(ballotModel, intent);
                }
                if (!ListenerUtil.mutListener.listen(49446)) {
                    context.startActivity(intent);
                }
                return intent != null;
            }
        }
        return false;
    }

    public static String getNotificationString(Context context, AbstractMessageModel messageModel) {
        String message = "";
        BallotService ballotService = null;
        ServiceManager serviceManager = ThreemaApplication.getServiceManager();
        if (!ListenerUtil.mutListener.listen(49449)) {
            if (serviceManager != null) {
                try {
                    if (!ListenerUtil.mutListener.listen(49448)) {
                        ballotService = serviceManager.getBallotService();
                    }
                } catch (Exception e) {
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(49455)) {
            if ((ListenerUtil.mutListener.listen(49450) ? (ballotService != null || messageModel.getBallotData() != null) : (ballotService != null && messageModel.getBallotData() != null))) {
                BallotModel ballotModel = ballotService.get(messageModel.getBallotData().getBallotId());
                if (!ListenerUtil.mutListener.listen(49454)) {
                    if (ballotModel != null) {
                        if (!ListenerUtil.mutListener.listen(49453)) {
                            if (ballotModel.getState() == BallotModel.State.OPEN) {
                                if (!ListenerUtil.mutListener.listen(49452)) {
                                    message += " " + ballotModel.getName();
                                }
                            } else if (ballotModel.getState() == BallotModel.State.CLOSED) {
                                if (!ListenerUtil.mutListener.listen(49451)) {
                                    message += " " + context.getResources().getString(R.string.ballot_message_closed);
                                }
                            }
                        }
                    }
                }
            }
        }
        return message;
    }

    public static void requestCloseBallot(BallotModel ballotModel, String identity, Fragment targetFragment, AppCompatActivity targetActivity) {
        if (!ListenerUtil.mutListener.listen(49462)) {
            if (BallotUtil.canClose(ballotModel, identity)) {
                FragmentManager fragmentManager = targetActivity != null ? targetActivity.getSupportFragmentManager() : targetFragment.getFragmentManager();
                if (!ListenerUtil.mutListener.listen(49461)) {
                    if (ThreemaApplication.getServiceManager().getConnection().getConnectionState() == ConnectionState.LOGGEDIN) {
                        GenericAlertDialog dialog = GenericAlertDialog.newInstance(R.string.ballot_close, R.string.ballot_really_close, R.string.ok, R.string.cancel);
                        if (!ListenerUtil.mutListener.listen(49457)) {
                            dialog.setData(ballotModel);
                        }
                        if (!ListenerUtil.mutListener.listen(49459)) {
                            if (targetFragment != null) {
                                if (!ListenerUtil.mutListener.listen(49458)) {
                                    dialog.setTargetFragment(targetFragment, 0);
                                }
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(49460)) {
                            dialog.show(fragmentManager, ThreemaApplication.CONFIRM_TAG_CLOSE_BALLOT);
                        }
                    } else {
                        SimpleStringAlertDialog dialog = SimpleStringAlertDialog.newInstance(R.string.ballot_close, R.string.ballot_not_connected);
                        if (!ListenerUtil.mutListener.listen(49456)) {
                            dialog.show(fragmentManager, "na");
                        }
                    }
                }
            }
        }
    }

    public static void closeBallot(AppCompatActivity activity, final BallotModel ballotModel, final BallotService ballotService) {
        if (!ListenerUtil.mutListener.listen(49467)) {
            if ((ListenerUtil.mutListener.listen(49463) ? (ballotModel != null || ballotModel.getState() != BallotModel.State.CLOSED) : (ballotModel != null && ballotModel.getState() != BallotModel.State.CLOSED))) {
                if (!ListenerUtil.mutListener.listen(49466)) {
                    LoadingUtil.runInAlert(activity.getSupportFragmentManager(), R.string.ballot_close, R.string.please_wait, new Runnable() {

                        @Override
                        public void run() {
                            try {
                                if (!ListenerUtil.mutListener.listen(49465)) {
                                    ballotService.close(ballotModel.getId());
                                }
                            } catch (final NotAllowedException | MessageTooLongException e) {
                                if (!ListenerUtil.mutListener.listen(49464)) {
                                    logger.error("Exception", e);
                                }
                            }
                        }
                    });
                }
            }
        }
    }

    public static void createBallot(MessageReceiver receiver, String ballotTitle, BallotModel.Type ballotType, BallotModel.Assessment ballotAssessment, List<BallotChoiceModel> ballotChoiceModelList) {
        BallotModel ballotModel = null;
        try {
            BallotService ballotService = ThreemaApplication.getServiceManager().getBallotService();
            BallotModel.ChoiceType choiceType = BallotModel.ChoiceType.TEXT;
            if (!ListenerUtil.mutListener.listen(49472)) {
                switch(receiver.getType()) {
                    case MessageReceiver.Type_GROUP:
                        if (!ListenerUtil.mutListener.listen(49470)) {
                            ballotModel = ballotService.create(((GroupMessageReceiver) receiver).getGroup(), ballotTitle, BallotModel.State.TEMPORARY, ballotAssessment, ballotType, choiceType);
                        }
                        break;
                    case MessageReceiver.Type_CONTACT:
                        if (!ListenerUtil.mutListener.listen(49471)) {
                            ballotModel = ballotService.create(((ContactMessageReceiver) receiver).getContact(), ballotTitle, BallotModel.State.TEMPORARY, ballotAssessment, ballotType, choiceType);
                        }
                        break;
                    default:
                        throw new NotAllowedException("not allowed");
                }
            }
            // generate ids
            Random r = new SecureRandom();
            int[] ids = new int[ballotChoiceModelList.size()];
            if (!ListenerUtil.mutListener.listen(49497)) {
                {
                    long _loopCounter568 = 0;
                    for (int n = 0; (ListenerUtil.mutListener.listen(49496) ? (n >= ids.length) : (ListenerUtil.mutListener.listen(49495) ? (n <= ids.length) : (ListenerUtil.mutListener.listen(49494) ? (n > ids.length) : (ListenerUtil.mutListener.listen(49493) ? (n != ids.length) : (ListenerUtil.mutListener.listen(49492) ? (n == ids.length) : (n < ids.length)))))); n++) {
                        ListenerUtil.loopListener.listen("_loopCounter568", ++_loopCounter568);
                        int rId;
                        boolean exists;
                        {
                            long _loopCounter567 = 0;
                            do {
                                ListenerUtil.loopListener.listen("_loopCounter567", ++_loopCounter567);
                                exists = false;
                                rId = Math.abs(r.nextInt());
                                {
                                    long _loopCounter566 = 0;
                                    for (int id : ids) {
                                        ListenerUtil.loopListener.listen("_loopCounter566", ++_loopCounter566);
                                        if ((ListenerUtil.mutListener.listen(49477) ? (id >= rId) : (ListenerUtil.mutListener.listen(49476) ? (id <= rId) : (ListenerUtil.mutListener.listen(49475) ? (id > rId) : (ListenerUtil.mutListener.listen(49474) ? (id < rId) : (ListenerUtil.mutListener.listen(49473) ? (id != rId) : (id == rId))))))) {
                                            exists = true;
                                            break;
                                        }
                                    }
                                }
                            } while (exists);
                        }
                        if (!ListenerUtil.mutListener.listen(49478)) {
                            ids[n] = rId;
                        }
                        BallotChoiceModel b = ballotChoiceModelList.get(n);
                        if (!ListenerUtil.mutListener.listen(49491)) {
                            if (b != null) {
                                if (!ListenerUtil.mutListener.listen(49483)) {
                                    b.setOrder((ListenerUtil.mutListener.listen(49482) ? (n % 1) : (ListenerUtil.mutListener.listen(49481) ? (n / 1) : (ListenerUtil.mutListener.listen(49480) ? (n * 1) : (ListenerUtil.mutListener.listen(49479) ? (n - 1) : (n + 1))))));
                                }
                                if (!ListenerUtil.mutListener.listen(49490)) {
                                    if ((ListenerUtil.mutListener.listen(49488) ? (b.getApiBallotChoiceId() >= 0) : (ListenerUtil.mutListener.listen(49487) ? (b.getApiBallotChoiceId() > 0) : (ListenerUtil.mutListener.listen(49486) ? (b.getApiBallotChoiceId() < 0) : (ListenerUtil.mutListener.listen(49485) ? (b.getApiBallotChoiceId() != 0) : (ListenerUtil.mutListener.listen(49484) ? (b.getApiBallotChoiceId() == 0) : (b.getApiBallotChoiceId() <= 0))))))) {
                                        if (!ListenerUtil.mutListener.listen(49489)) {
                                            b.setApiBallotChoiceId(rId);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(49499)) {
                {
                    long _loopCounter569 = 0;
                    // add choices
                    for (BallotChoiceModel c : ballotChoiceModelList) {
                        ListenerUtil.loopListener.listen("_loopCounter569", ++_loopCounter569);
                        if (!ListenerUtil.mutListener.listen(49498)) {
                            ballotService.update(ballotModel, c);
                        }
                    }
                }
            }
            try {
                if (!ListenerUtil.mutListener.listen(49503)) {
                    ballotService.modifyFinished(ballotModel);
                }
                if (!ListenerUtil.mutListener.listen(49504)) {
                    RuntimeUtil.runOnUiThread(() -> Toast.makeText(ThreemaApplication.getAppContext(), R.string.ballot_created_successfully, Toast.LENGTH_LONG).show());
                }
            } catch (MessageTooLongException e) {
                if (!ListenerUtil.mutListener.listen(49500)) {
                    ballotService.remove(ballotModel);
                }
                if (!ListenerUtil.mutListener.listen(49501)) {
                    RuntimeUtil.runOnUiThread(() -> Toast.makeText(ThreemaApplication.getAppContext(), R.string.message_too_long, Toast.LENGTH_LONG).show());
                }
                if (!ListenerUtil.mutListener.listen(49502)) {
                    logger.error("Exception", e);
                }
            }
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(49468)) {
                RuntimeUtil.runOnUiThread(() -> Toast.makeText(ThreemaApplication.getAppContext(), R.string.error, Toast.LENGTH_LONG).show());
            }
            if (!ListenerUtil.mutListener.listen(49469)) {
                logger.error("Exception", e);
            }
        }
    }
}
