/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
 * Copyright (c) 2020-2021 Threema GmbH
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
package ch.threema.app.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Build;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipDrawable;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.UiThread;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.ContextThemeWrapper;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.view.menu.MenuPopupHelper;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.transition.Fade;
import androidx.transition.Transition;
import androidx.transition.TransitionManager;
import ch.threema.app.R;
import ch.threema.app.ThreemaApplication;
import ch.threema.app.exceptions.NotAllowedException;
import ch.threema.app.listeners.BallotListener;
import ch.threema.app.listeners.BallotVoteListener;
import ch.threema.app.managers.ListenerManager;
import ch.threema.app.messagereceiver.MessageReceiver;
import ch.threema.app.services.ContactService;
import ch.threema.app.services.PreferenceService;
import ch.threema.app.services.UserService;
import ch.threema.app.services.ballot.BallotService;
import ch.threema.app.utils.AvatarConverterUtil;
import ch.threema.app.utils.BallotUtil;
import ch.threema.app.utils.BitmapUtil;
import ch.threema.app.utils.ConfigUtils;
import ch.threema.app.utils.RuntimeUtil;
import ch.threema.app.utils.TestUtil;
import ch.threema.storage.models.ballot.BallotModel;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * A view that shows all open ballots (polls) for a chat in a ChipGroup and allows users to vote or close the ballot
 */
public class OpenBallotNoticeView extends ConstraintLayout implements DefaultLifecycleObserver, Chip.OnClickListener {

    private static final Logger logger = LoggerFactory.getLogger(OpenBallotNoticeView.class);

    private static final int MAX_BALLOTS_SHOWN = 20;

    private static final int MAX_BALLOT_TITLE_LENGTH = 25;

    private ChipGroup chipGroup;

    private BallotService ballotService;

    private UserService userService;

    private PreferenceService preferenceService;

    private ContactService contactService;

    private String identity;

    private MessageReceiver messageReceiver;

    private int numOpenBallots;

    private BallotVoteListener ballotVoteListener = new BallotVoteListener() {

        @Override
        public void onSelfVote(BallotModel ballotModel) {
            if (!ListenerUtil.mutListener.listen(46452)) {
                RuntimeUtil.runOnUiThread(() -> updateBallotDisplay());
            }
        }

        @Override
        public void onVoteChanged(BallotModel ballotModel, String votingIdentity, boolean isFirstVote) {
            if (!ListenerUtil.mutListener.listen(46453)) {
                RuntimeUtil.runOnUiThread(() -> updateBallotDisplay());
            }
        }

        @Override
        public void onVoteRemoved(BallotModel ballotModel, String votingIdentity) {
            if (!ListenerUtil.mutListener.listen(46454)) {
                RuntimeUtil.runOnUiThread(() -> updateBallotDisplay());
            }
        }

        @Override
        public boolean handle(BallotModel ballotModel) {
            if (!ListenerUtil.mutListener.listen(46456)) {
                if (ballotModel != null) {
                    try {
                        return ballotService.belongsToMe(ballotModel.getId(), messageReceiver);
                    } catch (NotAllowedException e) {
                        if (!ListenerUtil.mutListener.listen(46455)) {
                            logger.error("Exception", e);
                        }
                    }
                }
            }
            return false;
        }
    };

    private BallotListener ballotListener = new BallotListener() {

        @Override
        public void onClosed(BallotModel ballotModel) {
            if (!ListenerUtil.mutListener.listen(46457)) {
                RuntimeUtil.runOnUiThread(() -> updateBallotDisplay());
            }
        }

        @Override
        public void onModified(BallotModel ballotModel) {
            if (!ListenerUtil.mutListener.listen(46458)) {
                RuntimeUtil.runOnUiThread(() -> updateBallotDisplay());
            }
        }

        @Override
        public void onCreated(BallotModel ballotModel) {
            if (!ListenerUtil.mutListener.listen(46459)) {
                RuntimeUtil.runOnUiThread(() -> updateBallotDisplay());
            }
        }

        @Override
        public void onRemoved(BallotModel ballotModel) {
            if (!ListenerUtil.mutListener.listen(46460)) {
                RuntimeUtil.runOnUiThread(() -> updateBallotDisplay());
            }
        }

        @Override
        public boolean handle(BallotModel ballotModel) {
            if (!ListenerUtil.mutListener.listen(46462)) {
                if (ballotModel != null) {
                    try {
                        return ballotService.belongsToMe(ballotModel.getId(), messageReceiver);
                    } catch (NotAllowedException e) {
                        if (!ListenerUtil.mutListener.listen(46461)) {
                            logger.error("Exception", e);
                        }
                    }
                }
            }
            return false;
        }
    };

    public OpenBallotNoticeView(Context context) {
        super(context);
        if (!ListenerUtil.mutListener.listen(46463)) {
            init(context);
        }
    }

    public OpenBallotNoticeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        if (!ListenerUtil.mutListener.listen(46464)) {
            init(context);
        }
    }

    public OpenBallotNoticeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        if (!ListenerUtil.mutListener.listen(46465)) {
            init(context);
        }
    }

    private void init(Context context) {
        if (!ListenerUtil.mutListener.listen(46466)) {
            if (!(getContext() instanceof AppCompatActivity)) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(46467)) {
            getActivity().getLifecycle().addObserver(this);
        }
        try {
            if (!ListenerUtil.mutListener.listen(46469)) {
                ballotService = ThreemaApplication.getServiceManager().getBallotService();
            }
            if (!ListenerUtil.mutListener.listen(46470)) {
                userService = ThreemaApplication.getServiceManager().getUserService();
            }
            if (!ListenerUtil.mutListener.listen(46471)) {
                preferenceService = ThreemaApplication.getServiceManager().getPreferenceService();
            }
            if (!ListenerUtil.mutListener.listen(46472)) {
                contactService = ThreemaApplication.getServiceManager().getContactService();
            }
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(46468)) {
                logger.error("Exception", e);
            }
        }
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (!ListenerUtil.mutListener.listen(46473)) {
            inflater.inflate(R.layout.view_open_ballots, this);
        }
        if (!ListenerUtil.mutListener.listen(46474)) {
            identity = userService.getIdentity();
        }
    }

    @UiThread
    public void show(boolean animated) {
        if (!ListenerUtil.mutListener.listen(46488)) {
            if ((ListenerUtil.mutListener.listen(46481) ? ((ListenerUtil.mutListener.listen(46480) ? (getVisibility() != VISIBLE || (ListenerUtil.mutListener.listen(46479) ? (numOpenBallots >= 0) : (ListenerUtil.mutListener.listen(46478) ? (numOpenBallots <= 0) : (ListenerUtil.mutListener.listen(46477) ? (numOpenBallots < 0) : (ListenerUtil.mutListener.listen(46476) ? (numOpenBallots != 0) : (ListenerUtil.mutListener.listen(46475) ? (numOpenBallots == 0) : (numOpenBallots > 0))))))) : (getVisibility() != VISIBLE && (ListenerUtil.mutListener.listen(46479) ? (numOpenBallots >= 0) : (ListenerUtil.mutListener.listen(46478) ? (numOpenBallots <= 0) : (ListenerUtil.mutListener.listen(46477) ? (numOpenBallots < 0) : (ListenerUtil.mutListener.listen(46476) ? (numOpenBallots != 0) : (ListenerUtil.mutListener.listen(46475) ? (numOpenBallots == 0) : (numOpenBallots > 0)))))))) || !preferenceService.getBallotOverviewHidden()) : ((ListenerUtil.mutListener.listen(46480) ? (getVisibility() != VISIBLE || (ListenerUtil.mutListener.listen(46479) ? (numOpenBallots >= 0) : (ListenerUtil.mutListener.listen(46478) ? (numOpenBallots <= 0) : (ListenerUtil.mutListener.listen(46477) ? (numOpenBallots < 0) : (ListenerUtil.mutListener.listen(46476) ? (numOpenBallots != 0) : (ListenerUtil.mutListener.listen(46475) ? (numOpenBallots == 0) : (numOpenBallots > 0))))))) : (getVisibility() != VISIBLE && (ListenerUtil.mutListener.listen(46479) ? (numOpenBallots >= 0) : (ListenerUtil.mutListener.listen(46478) ? (numOpenBallots <= 0) : (ListenerUtil.mutListener.listen(46477) ? (numOpenBallots < 0) : (ListenerUtil.mutListener.listen(46476) ? (numOpenBallots != 0) : (ListenerUtil.mutListener.listen(46475) ? (numOpenBallots == 0) : (numOpenBallots > 0)))))))) && !preferenceService.getBallotOverviewHidden()))) {
                if (!ListenerUtil.mutListener.listen(46486)) {
                    if (animated) {
                        Transition transition = new Fade();
                        if (!ListenerUtil.mutListener.listen(46482)) {
                            transition.setDuration(250);
                        }
                        if (!ListenerUtil.mutListener.listen(46483)) {
                            transition.addTarget(this);
                        }
                        if (!ListenerUtil.mutListener.listen(46484)) {
                            TransitionManager.endTransitions((ViewGroup) getParent());
                        }
                        if (!ListenerUtil.mutListener.listen(46485)) {
                            TransitionManager.beginDelayedTransition((ViewGroup) getParent(), transition);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(46487)) {
                    setVisibility(VISIBLE);
                }
            }
        }
    }

    @UiThread
    public void hide(boolean animated) {
        if (!ListenerUtil.mutListener.listen(46495)) {
            if (getVisibility() != GONE) {
                if (!ListenerUtil.mutListener.listen(46493)) {
                    if (animated) {
                        Transition transition = new Fade();
                        if (!ListenerUtil.mutListener.listen(46489)) {
                            transition.setDuration(250);
                        }
                        if (!ListenerUtil.mutListener.listen(46490)) {
                            transition.addTarget(this);
                        }
                        if (!ListenerUtil.mutListener.listen(46491)) {
                            TransitionManager.endTransitions((ViewGroup) getParent());
                        }
                        if (!ListenerUtil.mutListener.listen(46492)) {
                            TransitionManager.beginDelayedTransition((ViewGroup) getParent(), transition);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(46494)) {
                    setVisibility(GONE);
                }
            }
        }
    }

    @UiThread
    @SuppressLint("StaticFieldLeak")
    private void updateBallotDisplay() {
        if (!ListenerUtil.mutListener.listen(46496)) {
            if (messageReceiver == null) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(46581)) {
            new AsyncTask<Void, Void, List<BallotModel>>() {

                @Override
                protected List<BallotModel> doInBackground(Void... voids) {
                    try {
                        return ballotService.getBallots(new BallotService.BallotFilter() {

                            @Override
                            public MessageReceiver getReceiver() {
                                return messageReceiver;
                            }

                            @Override
                            public BallotModel.State[] getStates() {
                                return new BallotModel.State[] { BallotModel.State.OPEN };
                            }

                            @Override
                            public String createdOrNotVotedByIdentity() {
                                return identity;
                            }

                            @Override
                            public boolean filter(BallotModel ballotModel) {
                                return true;
                            }
                        });
                    } catch (NotAllowedException | IllegalStateException e) {
                        if (!ListenerUtil.mutListener.listen(46497)) {
                            logger.error("Exception", e);
                        }
                    }
                    return null;
                }

                @Override
                protected void onPostExecute(List<BallotModel> ballotModels) {
                    if (!ListenerUtil.mutListener.listen(46498)) {
                        chipGroup.removeAllViews();
                    }
                    if (!ListenerUtil.mutListener.listen(46499)) {
                        numOpenBallots = ballotModels.size();
                    }
                    if (!ListenerUtil.mutListener.listen(46580)) {
                        if ((ListenerUtil.mutListener.listen(46504) ? (numOpenBallots >= 0) : (ListenerUtil.mutListener.listen(46503) ? (numOpenBallots > 0) : (ListenerUtil.mutListener.listen(46502) ? (numOpenBallots < 0) : (ListenerUtil.mutListener.listen(46501) ? (numOpenBallots != 0) : (ListenerUtil.mutListener.listen(46500) ? (numOpenBallots == 0) : (numOpenBallots <= 0))))))) {
                            if (!ListenerUtil.mutListener.listen(46579)) {
                                hide(false);
                            }
                        } else {
                            int i = 0;
                            Chip firstChip = new Chip(getContext());
                            ChipDrawable firstChipDrawable = ChipDrawable.createFromAttributes(getContext(), null, 0, R.style.Chip_Ballot_Overview_Intro);
                            if (!ListenerUtil.mutListener.listen(46505)) {
                                firstChip.setChipDrawable(firstChipDrawable);
                            }
                            if (!ListenerUtil.mutListener.listen(46513)) {
                                if ((ListenerUtil.mutListener.listen(46510) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(46509) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(46508) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(46507) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(46506) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.M) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M))))))) {
                                    if (!ListenerUtil.mutListener.listen(46512)) {
                                        firstChip.setTextAppearance(R.style.TextAppearance_Chip_Ballot);
                                    }
                                } else {
                                    if (!ListenerUtil.mutListener.listen(46511)) {
                                        firstChip.setTextSize(14);
                                    }
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(46514)) {
                                firstChip.setTextColor(ConfigUtils.getColorFromAttribute(getContext(), R.attr.text_color_openballots));
                            }
                            if (!ListenerUtil.mutListener.listen(46515)) {
                                firstChip.setChipBackgroundColor(ColorStateList.valueOf(ConfigUtils.getColorFromAttribute(getContext(), R.attr.background_openballots)));
                            }
                            if (!ListenerUtil.mutListener.listen(46516)) {
                                firstChip.setText(R.string.ballot_open);
                            }
                            if (!ListenerUtil.mutListener.listen(46517)) {
                                firstChip.setClickable(false);
                            }
                            if (!ListenerUtil.mutListener.listen(46518)) {
                                chipGroup.addView(firstChip);
                            }
                            int j = 0;
                            if (!ListenerUtil.mutListener.listen(46571)) {
                                {
                                    long _loopCounter548 = 0;
                                    for (BallotModel ballot : ballotModels) {
                                        ListenerUtil.loopListener.listen("_loopCounter548", ++_loopCounter548);
                                        if (!ListenerUtil.mutListener.listen(46524)) {
                                            // show only the latest MAX_BALLOTS_SHOWN open ballots
                                            if ((ListenerUtil.mutListener.listen(46523) ? (i++ <= MAX_BALLOTS_SHOWN) : (ListenerUtil.mutListener.listen(46522) ? (i++ > MAX_BALLOTS_SHOWN) : (ListenerUtil.mutListener.listen(46521) ? (i++ < MAX_BALLOTS_SHOWN) : (ListenerUtil.mutListener.listen(46520) ? (i++ != MAX_BALLOTS_SHOWN) : (ListenerUtil.mutListener.listen(46519) ? (i++ == MAX_BALLOTS_SHOWN) : (i++ >= MAX_BALLOTS_SHOWN))))))) {
                                                break;
                                            }
                                        }
                                        int voters = ballotService.getVotedParticipants(ballot.getId()).size();
                                        int participants = ballotService.getParticipants(ballot.getId()).length;
                                        if (!ListenerUtil.mutListener.listen(46530)) {
                                            if ((ListenerUtil.mutListener.listen(46529) ? (participants >= 0) : (ListenerUtil.mutListener.listen(46528) ? (participants <= 0) : (ListenerUtil.mutListener.listen(46527) ? (participants > 0) : (ListenerUtil.mutListener.listen(46526) ? (participants < 0) : (ListenerUtil.mutListener.listen(46525) ? (participants != 0) : (participants == 0))))))) {
                                                continue;
                                            }
                                        }
                                        String name = ballot.getName();
                                        if (!ListenerUtil.mutListener.listen(46540)) {
                                            if (TestUtil.empty(name)) {
                                                if (!ListenerUtil.mutListener.listen(46539)) {
                                                    name = getContext().getString(R.string.ballot_placeholder);
                                                }
                                            } else {
                                                if (!ListenerUtil.mutListener.listen(46538)) {
                                                    if ((ListenerUtil.mutListener.listen(46535) ? (name.length() >= MAX_BALLOT_TITLE_LENGTH) : (ListenerUtil.mutListener.listen(46534) ? (name.length() <= MAX_BALLOT_TITLE_LENGTH) : (ListenerUtil.mutListener.listen(46533) ? (name.length() < MAX_BALLOT_TITLE_LENGTH) : (ListenerUtil.mutListener.listen(46532) ? (name.length() != MAX_BALLOT_TITLE_LENGTH) : (ListenerUtil.mutListener.listen(46531) ? (name.length() == MAX_BALLOT_TITLE_LENGTH) : (name.length() > MAX_BALLOT_TITLE_LENGTH))))))) {
                                                        if (!ListenerUtil.mutListener.listen(46536)) {
                                                            name = name.substring(0, MAX_BALLOT_TITLE_LENGTH);
                                                        }
                                                        if (!ListenerUtil.mutListener.listen(46537)) {
                                                            name += "…";
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                        Chip chip = new Chip(getContext());
                                        ChipDrawable chipDrawable = ChipDrawable.createFromAttributes(getContext(), null, 0, R.style.Chip_Ballot_Overview);
                                        if (!ListenerUtil.mutListener.listen(46541)) {
                                            chip.setChipDrawable(chipDrawable);
                                        }
                                        if (!ListenerUtil.mutListener.listen(46549)) {
                                            if ((ListenerUtil.mutListener.listen(46546) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(46545) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(46544) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(46543) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(46542) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.M) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M))))))) {
                                                if (!ListenerUtil.mutListener.listen(46548)) {
                                                    chip.setTextAppearance(R.style.TextAppearance_Chip_Ballot);
                                                }
                                            } else {
                                                if (!ListenerUtil.mutListener.listen(46547)) {
                                                    chip.setTextSize(14);
                                                }
                                            }
                                        }
                                        if (!ListenerUtil.mutListener.listen(46550)) {
                                            chip.setOnClickListener((View v) -> {
                                                OpenBallotNoticeView.this.onChipClick(v, voters == participants);
                                            });
                                        }
                                        if (!ListenerUtil.mutListener.listen(46555)) {
                                            new AsyncTask<Void, Void, Bitmap>() {

                                                @Override
                                                protected Bitmap doInBackground(Void... params) {
                                                    Bitmap bitmap = contactService.getAvatar(contactService.getByIdentity(ballot.getCreatorIdentity()), false);
                                                    if (!ListenerUtil.mutListener.listen(46551)) {
                                                        if (bitmap != null) {
                                                            return BitmapUtil.replaceTransparency(bitmap, Color.WHITE);
                                                        }
                                                    }
                                                    return null;
                                                }

                                                @Override
                                                protected void onPostExecute(Bitmap avatar) {
                                                    if (!ListenerUtil.mutListener.listen(46554)) {
                                                        if (avatar != null) {
                                                            if (!ListenerUtil.mutListener.listen(46553)) {
                                                                chip.setChipIcon(AvatarConverterUtil.convertToRound(getResources(), avatar));
                                                            }
                                                        } else {
                                                            if (!ListenerUtil.mutListener.listen(46552)) {
                                                                chip.setChipIconResource(R.drawable.ic_vote_outline);
                                                            }
                                                        }
                                                    }
                                                }
                                            }.execute();
                                        }
                                        if (!ListenerUtil.mutListener.listen(46556)) {
                                            chip.setTag(ballot);
                                        }
                                        if (!ListenerUtil.mutListener.listen(46557)) {
                                            chip.setTextEndPadding(getResources().getDimensionPixelSize(R.dimen.chip_end_padding_text_only));
                                        }
                                        ColorStateList foregroundColor, backgroundColor;
                                        boolean isMine = BallotUtil.isMine(ballot, userService);
                                        if (!ListenerUtil.mutListener.listen(46560)) {
                                            if (isMine) {
                                                if (!ListenerUtil.mutListener.listen(46559)) {
                                                    chip.setText(name + " (" + voters + "/" + participants + ")");
                                                }
                                            } else {
                                                if (!ListenerUtil.mutListener.listen(46558)) {
                                                    chip.setText(name);
                                                }
                                            }
                                        }
                                        if ((ListenerUtil.mutListener.listen(46566) ? (isMine || (ListenerUtil.mutListener.listen(46565) ? (voters >= participants) : (ListenerUtil.mutListener.listen(46564) ? (voters <= participants) : (ListenerUtil.mutListener.listen(46563) ? (voters > participants) : (ListenerUtil.mutListener.listen(46562) ? (voters < participants) : (ListenerUtil.mutListener.listen(46561) ? (voters != participants) : (voters == participants))))))) : (isMine && (ListenerUtil.mutListener.listen(46565) ? (voters >= participants) : (ListenerUtil.mutListener.listen(46564) ? (voters <= participants) : (ListenerUtil.mutListener.listen(46563) ? (voters > participants) : (ListenerUtil.mutListener.listen(46562) ? (voters < participants) : (ListenerUtil.mutListener.listen(46561) ? (voters != participants) : (voters == participants))))))))) {
                                            // all votes are in
                                            if (ConfigUtils.getAppTheme(getContext()) == ConfigUtils.THEME_DARK) {
                                                foregroundColor = ColorStateList.valueOf(ConfigUtils.getColorFromAttribute(getContext(), R.attr.textColorSecondary));
                                                backgroundColor = ColorStateList.valueOf(getResources().getColor(R.color.material_red));
                                            } else {
                                                foregroundColor = ColorStateList.valueOf(getResources().getColor(R.color.material_red));
                                                backgroundColor = foregroundColor.withAlpha(0x1A);
                                            }
                                        } else {
                                            if (ConfigUtils.getAppTheme(getContext()) == ConfigUtils.THEME_DARK) {
                                                foregroundColor = ColorStateList.valueOf(ConfigUtils.getColorFromAttribute(getContext(), R.attr.textColorPrimary));
                                                backgroundColor = ColorStateList.valueOf(ConfigUtils.getColorFromAttribute(getContext(), R.attr.colorAccent));
                                            } else {
                                                foregroundColor = ColorStateList.valueOf(ConfigUtils.getColorFromAttribute(getContext(), R.attr.colorAccent));
                                                backgroundColor = foregroundColor.withAlpha(0x1A);
                                            }
                                        }
                                        if (!ListenerUtil.mutListener.listen(46567)) {
                                            chip.setTextColor(foregroundColor);
                                        }
                                        if (!ListenerUtil.mutListener.listen(46568)) {
                                            chip.setChipBackgroundColor(backgroundColor);
                                        }
                                        if (!ListenerUtil.mutListener.listen(46569)) {
                                            chipGroup.addView(chip);
                                        }
                                        if (!ListenerUtil.mutListener.listen(46570)) {
                                            j++;
                                        }
                                    }
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(46578)) {
                                if ((ListenerUtil.mutListener.listen(46576) ? (j >= 0) : (ListenerUtil.mutListener.listen(46575) ? (j <= 0) : (ListenerUtil.mutListener.listen(46574) ? (j < 0) : (ListenerUtil.mutListener.listen(46573) ? (j != 0) : (ListenerUtil.mutListener.listen(46572) ? (j == 0) : (j > 0))))))) {
                                    if (!ListenerUtil.mutListener.listen(46577)) {
                                        show(false);
                                    }
                                }
                            }
                        }
                    }
                }
            }.execute();
        }
    }

    public void setMessageReceiver(@NonNull MessageReceiver messageReceiver) {
        if (!ListenerUtil.mutListener.listen(46582)) {
            this.messageReceiver = messageReceiver;
        }
        if (!ListenerUtil.mutListener.listen(46583)) {
            updateBallotDisplay();
        }
    }

    public void setVisibilityListener(VisibilityListener listener) {
    }

    @Override
    protected void onFinishInflate() {
        if (!ListenerUtil.mutListener.listen(46584)) {
            super.onFinishInflate();
        }
        if (!ListenerUtil.mutListener.listen(46585)) {
            this.chipGroup = findViewById(R.id.chip_group);
        }
    }

    @Override
    public void onCreate(@NonNull LifecycleOwner owner) {
        if (!ListenerUtil.mutListener.listen(46586)) {
            ListenerManager.ballotListeners.add(this.ballotListener);
        }
        if (!ListenerUtil.mutListener.listen(46587)) {
            ListenerManager.ballotVoteListeners.add(this.ballotVoteListener);
        }
    }

    @Override
    public void onDestroy(@NonNull LifecycleOwner owner) {
        if (!ListenerUtil.mutListener.listen(46588)) {
            ListenerManager.ballotVoteListeners.remove(this.ballotVoteListener);
        }
        if (!ListenerUtil.mutListener.listen(46589)) {
            ListenerManager.ballotListeners.remove(this.ballotListener);
        }
    }

    @Override
    public void onClick(View v) {
        BallotModel model = (BallotModel) v.getTag();
        if (!ListenerUtil.mutListener.listen(46603)) {
            if (BallotUtil.canClose(model, identity)) {
                int voters = ballotService.getVotedParticipants(model.getId()).size();
                int participants = ballotService.getParticipants(model.getId()).length;
                if (!ListenerUtil.mutListener.listen(46602)) {
                    if ((ListenerUtil.mutListener.listen(46600) ? ((ListenerUtil.mutListener.listen(46594) ? (participants >= 0) : (ListenerUtil.mutListener.listen(46593) ? (participants <= 0) : (ListenerUtil.mutListener.listen(46592) ? (participants < 0) : (ListenerUtil.mutListener.listen(46591) ? (participants != 0) : (ListenerUtil.mutListener.listen(46590) ? (participants == 0) : (participants > 0)))))) || (ListenerUtil.mutListener.listen(46599) ? (voters >= participants) : (ListenerUtil.mutListener.listen(46598) ? (voters <= participants) : (ListenerUtil.mutListener.listen(46597) ? (voters > participants) : (ListenerUtil.mutListener.listen(46596) ? (voters < participants) : (ListenerUtil.mutListener.listen(46595) ? (voters != participants) : (voters == participants))))))) : ((ListenerUtil.mutListener.listen(46594) ? (participants >= 0) : (ListenerUtil.mutListener.listen(46593) ? (participants <= 0) : (ListenerUtil.mutListener.listen(46592) ? (participants < 0) : (ListenerUtil.mutListener.listen(46591) ? (participants != 0) : (ListenerUtil.mutListener.listen(46590) ? (participants == 0) : (participants > 0)))))) && (ListenerUtil.mutListener.listen(46599) ? (voters >= participants) : (ListenerUtil.mutListener.listen(46598) ? (voters <= participants) : (ListenerUtil.mutListener.listen(46597) ? (voters > participants) : (ListenerUtil.mutListener.listen(46596) ? (voters < participants) : (ListenerUtil.mutListener.listen(46595) ? (voters != participants) : (voters == participants))))))))) {
                        if (!ListenerUtil.mutListener.listen(46601)) {
                            onChipClick(v, true);
                        }
                        return;
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(46604)) {
            vote(model);
        }
    }

    @SuppressLint("RestrictedApi")
    public void onChipClick(View v, boolean isVoteComplete) {
        BallotModel ballotModel = (BallotModel) v.getTag();
        if (!ListenerUtil.mutListener.listen(46624)) {
            if (ballotModel != null) {
                MenuBuilder menuBuilder = new MenuBuilder(getContext());
                if (!ListenerUtil.mutListener.listen(46605)) {
                    new MenuInflater(getContext()).inflate(R.menu.chip_open_ballots, menuBuilder);
                }
                if (!ListenerUtil.mutListener.listen(46606)) {
                    ConfigUtils.themeMenu(menuBuilder, ConfigUtils.getColorFromAttribute(getContext(), R.attr.textColorSecondary));
                }
                if (!ListenerUtil.mutListener.listen(46608)) {
                    if (BallotUtil.canViewMatrix(ballotModel, identity)) {
                        if (!ListenerUtil.mutListener.listen(46607)) {
                            menuBuilder.findItem(R.id.menu_ballot_results).setTitle(ballotModel.getState() == BallotModel.State.CLOSED ? R.string.ballot_result_final : R.string.ballot_result_intermediate);
                        }
                    }
                }
                MenuItem highlightItem;
                @ColorInt
                int highlightColor;
                if (isVoteComplete) {
                    highlightItem = menuBuilder.findItem(R.id.menu_ballot_close);
                    highlightColor = getContext().getResources().getColor(R.color.material_red);
                } else {
                    if (ballotService.hasVoted(ballotModel.getId(), userService.getIdentity())) {
                        highlightItem = menuBuilder.findItem(R.id.menu_ballot_results);
                    } else {
                        highlightItem = menuBuilder.findItem(R.id.menu_ballot_vote);
                    }
                    highlightColor = ConfigUtils.getColorFromAttribute(getContext(), R.attr.colorAccent);
                }
                SpannableString s = new SpannableString(highlightItem.getTitle());
                if (!ListenerUtil.mutListener.listen(46609)) {
                    s.setSpan(new ForegroundColorSpan(highlightColor), 0, s.length(), 0);
                }
                if (!ListenerUtil.mutListener.listen(46610)) {
                    highlightItem.setTitle(s);
                }
                if (!ListenerUtil.mutListener.listen(46611)) {
                    ConfigUtils.themeMenuItem(highlightItem, highlightColor);
                }
                if (!ListenerUtil.mutListener.listen(46617)) {
                    menuBuilder.setCallback(new MenuBuilder.Callback() {

                        @Override
                        public boolean onMenuItemSelected(MenuBuilder menu, MenuItem item) {
                            if (!ListenerUtil.mutListener.listen(46616)) {
                                switch(item.getItemId()) {
                                    case R.id.menu_ballot_vote:
                                        if (!ListenerUtil.mutListener.listen(46612)) {
                                            vote(ballotModel);
                                        }
                                        break;
                                    case R.id.menu_ballot_results:
                                        if (!ListenerUtil.mutListener.listen(46613)) {
                                            BallotUtil.openMatrixActivity(getContext(), ballotModel, identity);
                                        }
                                        break;
                                    case R.id.menu_ballot_close:
                                        if (!ListenerUtil.mutListener.listen(46614)) {
                                            close(ballotModel);
                                        }
                                        break;
                                    case R.id.menu_ballot_delete:
                                        if (!ListenerUtil.mutListener.listen(46615)) {
                                            delete(ballotModel);
                                        }
                                        break;
                                }
                            }
                            return true;
                        }

                        @Override
                        public void onMenuModeChange(MenuBuilder menu) {
                        }
                    });
                }
                if (!ListenerUtil.mutListener.listen(46619)) {
                    if (!BallotUtil.canViewMatrix(ballotModel, identity)) {
                        if (!ListenerUtil.mutListener.listen(46618)) {
                            menuBuilder.removeItem(R.id.menu_ballot_results);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(46621)) {
                    if (!BallotUtil.canClose(ballotModel, identity)) {
                        if (!ListenerUtil.mutListener.listen(46620)) {
                            menuBuilder.removeItem(R.id.menu_ballot_close);
                        }
                        ;
                    }
                }
                Context wrapper = new ContextThemeWrapper(getContext(), ConfigUtils.getAppTheme(getContext()) == ConfigUtils.THEME_DARK ? R.style.AppBaseTheme_Dark : R.style.AppBaseTheme);
                MenuPopupHelper optionsMenu = new MenuPopupHelper(wrapper, menuBuilder, v);
                if (!ListenerUtil.mutListener.listen(46622)) {
                    optionsMenu.setForceShowIcon(true);
                }
                if (!ListenerUtil.mutListener.listen(46623)) {
                    optionsMenu.show();
                }
            }
        }
    }

    private void vote(BallotModel model) {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        if (!ListenerUtil.mutListener.listen(46626)) {
            if (BallotUtil.canVote(model, identity)) {
                if (!ListenerUtil.mutListener.listen(46625)) {
                    BallotUtil.openVoteDialog(fragmentManager, model, identity);
                }
            }
        }
    }

    private void close(BallotModel model) {
        if (!ListenerUtil.mutListener.listen(46629)) {
            if (BallotUtil.canClose(model, identity)) {
                MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(getContext()).setTitle(R.string.ballot_close).setMessage(R.string.ballot_really_close).setNegativeButton(R.string.no, null).setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (!ListenerUtil.mutListener.listen(46627)) {
                            BallotUtil.closeBallot(getActivity(), model, ballotService);
                        }
                    }
                });
                if (!ListenerUtil.mutListener.listen(46628)) {
                    builder.create().show();
                }
            }
        }
    }

    private void delete(BallotModel model) {
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(getContext()).setTitle(R.string.ballot_really_delete).setMessage(getContext().getString(R.string.ballot_really_delete_text, 1)).setNegativeButton(R.string.no, null).setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                try {
                    if (!ListenerUtil.mutListener.listen(46631)) {
                        ballotService.remove(model);
                    }
                } catch (NotAllowedException e) {
                    if (!ListenerUtil.mutListener.listen(46630)) {
                        logger.error("Exception", e);
                    }
                }
            }
        });
        if (!ListenerUtil.mutListener.listen(46632)) {
            builder.create().show();
        }
    }

    private AppCompatActivity getActivity() {
        return (AppCompatActivity) getContext();
    }

    public interface VisibilityListener {

        void onDismissed();
    }
}
