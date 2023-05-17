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

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;
import ch.threema.app.R;
import ch.threema.app.ThreemaApplication;
import ch.threema.app.activities.ThreemaActivity;
import ch.threema.app.exceptions.NotAllowedException;
import ch.threema.app.managers.ServiceManager;
import ch.threema.app.messagereceiver.MessageReceiver;
import ch.threema.app.services.ContactService;
import ch.threema.app.services.GroupService;
import ch.threema.app.services.MessageService;
import ch.threema.app.services.ballot.BallotService;
import ch.threema.app.ui.StepPagerStrip;
import ch.threema.app.utils.BallotUtil;
import ch.threema.app.utils.ConfigUtils;
import ch.threema.app.utils.IntentDataUtil;
import ch.threema.app.utils.TestUtil;
import ch.threema.base.ThreemaException;
import ch.threema.client.APIConnector;
import ch.threema.storage.models.ballot.BallotChoiceModel;
import ch.threema.storage.models.ballot.BallotModel;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class BallotWizardActivity extends ThreemaActivity {

    private static final Logger logger = LoggerFactory.getLogger(BallotWizardActivity.class);

    private static final int NUM_PAGES = 2;

    private ViewPager pager;

    private ScreenSlidePagerAdapter pagerAdapter;

    private BallotService ballotService;

    private ContactService contactService;

    private APIConnector apiConnector;

    private GroupService groupService;

    private String identity;

    private StepPagerStrip stepPagerStrip;

    private ImageView nextButton, copyButton, prevButton;

    private Button nextText;

    private MessageReceiver receiver;

    private final List<BallotChoiceModel> ballotChoiceModelList = new ArrayList<>();

    private String ballotTitle;

    private BallotModel.Type ballotType;

    private BallotModel.Assessment ballotAssessment;

    private MessageService messageService;

    private final List<WeakReference<BallotWizardFragment>> fragmentList = new ArrayList<>();

    private final Runnable createBallotRunnable = new Runnable() {

        @Override
        public void run() {
            if (!ListenerUtil.mutListener.listen(367)) {
                BallotUtil.createBallot(receiver, ballotTitle, ballotType, ballotAssessment, ballotChoiceModelList);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(368)) {
            ConfigUtils.configureActivityTheme(this);
        }
        if (!ListenerUtil.mutListener.listen(369)) {
            super.onCreate(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(370)) {
            setContentView(R.layout.activity_ballot_wizard);
        }
        if (!ListenerUtil.mutListener.listen(371)) {
            pager = findViewById(R.id.pager);
        }
        if (!ListenerUtil.mutListener.listen(372)) {
            pagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        }
        if (!ListenerUtil.mutListener.listen(373)) {
            pager.setAdapter(pagerAdapter);
        }
        if (!ListenerUtil.mutListener.listen(374)) {
            stepPagerStrip = findViewById(R.id.strip);
        }
        if (!ListenerUtil.mutListener.listen(375)) {
            stepPagerStrip.setPageCount(NUM_PAGES);
        }
        if (!ListenerUtil.mutListener.listen(376)) {
            stepPagerStrip.setCurrentPage(0);
        }
        if (!ListenerUtil.mutListener.listen(377)) {
            copyButton = findViewById(R.id.copy_ballot);
        }
        if (!ListenerUtil.mutListener.listen(378)) {
            copyButton.setOnClickListener(v -> startCopy());
        }
        if (!ListenerUtil.mutListener.listen(379)) {
            prevButton = findViewById(R.id.prev_page_button);
        }
        if (!ListenerUtil.mutListener.listen(380)) {
            prevButton.setOnClickListener(v -> prevPage());
        }
        if (!ListenerUtil.mutListener.listen(381)) {
            nextButton = findViewById(R.id.next_page_button);
        }
        if (!ListenerUtil.mutListener.listen(382)) {
            nextButton.setOnClickListener(v -> nextPage());
        }
        if (!ListenerUtil.mutListener.listen(383)) {
            nextText = findViewById(R.id.next_text);
        }
        if (!ListenerUtil.mutListener.listen(384)) {
            nextText.setOnClickListener(v -> nextPage());
        }
        if (!ListenerUtil.mutListener.listen(405)) {
            pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

                @Override
                public void onPageScrolled(int i, float v, int i2) {
                }

                @Override
                public void onPageSelected(int position) {
                    if (!ListenerUtil.mutListener.listen(387)) {
                        {
                            long _loopCounter7 = 0;
                            for (WeakReference<BallotWizardFragment> fragment : fragmentList) {
                                ListenerUtil.loopListener.listen("_loopCounter7", ++_loopCounter7);
                                BallotWizardCallback callback = (BallotWizardCallback) fragment.get();
                                if (!ListenerUtil.mutListener.listen(386)) {
                                    if (callback != null) {
                                        if (!ListenerUtil.mutListener.listen(385)) {
                                            callback.onPageSelected(position);
                                        }
                                    }
                                }
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(403)) {
                        if ((ListenerUtil.mutListener.listen(392) ? (position >= 1) : (ListenerUtil.mutListener.listen(391) ? (position <= 1) : (ListenerUtil.mutListener.listen(390) ? (position > 1) : (ListenerUtil.mutListener.listen(389) ? (position < 1) : (ListenerUtil.mutListener.listen(388) ? (position != 1) : (position == 1))))))) {
                            if (!ListenerUtil.mutListener.listen(402)) {
                                if (checkTitle()) {
                                    if (!ListenerUtil.mutListener.listen(398)) {
                                        nextButton.setVisibility(View.GONE);
                                    }
                                    if (!ListenerUtil.mutListener.listen(399)) {
                                        prevButton.setVisibility(View.VISIBLE);
                                    }
                                    if (!ListenerUtil.mutListener.listen(400)) {
                                        nextText.setVisibility(View.VISIBLE);
                                    }
                                    if (!ListenerUtil.mutListener.listen(401)) {
                                        copyButton.setVisibility(View.GONE);
                                    }
                                } else {
                                    if (!ListenerUtil.mutListener.listen(397)) {
                                        position = 0;
                                    }
                                }
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(393)) {
                                nextButton.setVisibility(View.VISIBLE);
                            }
                            if (!ListenerUtil.mutListener.listen(394)) {
                                prevButton.setVisibility(View.GONE);
                            }
                            if (!ListenerUtil.mutListener.listen(395)) {
                                nextText.setVisibility(View.GONE);
                            }
                            if (!ListenerUtil.mutListener.listen(396)) {
                                copyButton.setVisibility(View.VISIBLE);
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(404)) {
                        stepPagerStrip.setCurrentPage(position);
                    }
                }

                @Override
                public void onPageScrollStateChanged(int i) {
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(406)) {
            instantiate();
        }
        if (!ListenerUtil.mutListener.listen(407)) {
            setDefaults();
        }
        if (!ListenerUtil.mutListener.listen(408)) {
            handleIntent();
        }
    }

    @Override
    protected void onDestroy() {
        synchronized (this.fragmentList) {
            if (!ListenerUtil.mutListener.listen(409)) {
                fragmentList.clear();
            }
        }
        if (!ListenerUtil.mutListener.listen(410)) {
            super.onDestroy();
        }
    }

    /**
     *  save the attached fragments to update on copy command
     *  @param fragment
     */
    @Override
    public void onAttachFragment(Fragment fragment) {
        if (!ListenerUtil.mutListener.listen(411)) {
            super.onAttachFragment(fragment);
        }
        if (!ListenerUtil.mutListener.listen(413)) {
            if (fragment instanceof BallotWizardFragment) {
                synchronized (this.fragmentList) {
                    if (!ListenerUtil.mutListener.listen(412)) {
                        this.fragmentList.add(new WeakReference<>((BallotWizardFragment) fragment));
                    }
                }
            }
        }
    }

    private void setDefaults() {
        if (!ListenerUtil.mutListener.listen(414)) {
            setBallotType(BallotModel.Type.INTERMEDIATE);
        }
        if (!ListenerUtil.mutListener.listen(415)) {
            setBallotAssessment(BallotModel.Assessment.SINGLE_CHOICE);
        }
        if (!ListenerUtil.mutListener.listen(416)) {
            setResult(RESULT_CANCELED);
        }
    }

    private void handleIntent() {
        if (!ListenerUtil.mutListener.listen(417)) {
            this.receiver = IntentDataUtil.getMessageReceiverFromIntent(this, getIntent());
        }
    }

    @Override
    public void onBackPressed() {
        int currentItem = pager.getCurrentItem();
        if (!ListenerUtil.mutListener.listen(429)) {
            if ((ListenerUtil.mutListener.listen(422) ? (currentItem >= 0) : (ListenerUtil.mutListener.listen(421) ? (currentItem <= 0) : (ListenerUtil.mutListener.listen(420) ? (currentItem > 0) : (ListenerUtil.mutListener.listen(419) ? (currentItem < 0) : (ListenerUtil.mutListener.listen(418) ? (currentItem != 0) : (currentItem == 0))))))) {
                if (!ListenerUtil.mutListener.listen(428)) {
                    super.onBackPressed();
                }
            } else {
                if (!ListenerUtil.mutListener.listen(427)) {
                    pager.setCurrentItem((ListenerUtil.mutListener.listen(426) ? (currentItem % 1) : (ListenerUtil.mutListener.listen(425) ? (currentItem / 1) : (ListenerUtil.mutListener.listen(424) ? (currentItem * 1) : (ListenerUtil.mutListener.listen(423) ? (currentItem + 1) : (currentItem - 1))))));
                }
            }
        }
    }

    private boolean checkTitle() {
        if (!ListenerUtil.mutListener.listen(433)) {
            if (TestUtil.empty(this.ballotTitle)) {
                BallotWizardCallback callback = (BallotWizardCallback) this.fragmentList.get(0).get();
                if (!ListenerUtil.mutListener.listen(431)) {
                    if (callback != null) {
                        if (!ListenerUtil.mutListener.listen(430)) {
                            callback.onMissingTitle();
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(432)) {
                    pager.setCurrentItem(0);
                }
                return false;
            }
        }
        return true;
    }

    public void nextPage() {
        int currentItem = pager.getCurrentItem() + 1;
        if (!ListenerUtil.mutListener.listen(452)) {
            if ((ListenerUtil.mutListener.listen(438) ? (currentItem >= NUM_PAGES) : (ListenerUtil.mutListener.listen(437) ? (currentItem <= NUM_PAGES) : (ListenerUtil.mutListener.listen(436) ? (currentItem > NUM_PAGES) : (ListenerUtil.mutListener.listen(435) ? (currentItem != NUM_PAGES) : (ListenerUtil.mutListener.listen(434) ? (currentItem == NUM_PAGES) : (currentItem < NUM_PAGES))))))) {
                if (!ListenerUtil.mutListener.listen(451)) {
                    pager.setCurrentItem(currentItem);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(450)) {
                    /* end */
                    if (checkTitle()) {
                        BallotWizardFragment1 fragment = (BallotWizardFragment1) pagerAdapter.instantiateItem(pager, pager.getCurrentItem());
                        if (!ListenerUtil.mutListener.listen(439)) {
                            fragment.saveUnsavedData();
                        }
                        if (!ListenerUtil.mutListener.listen(449)) {
                            if ((ListenerUtil.mutListener.listen(444) ? (this.ballotChoiceModelList.size() >= 1) : (ListenerUtil.mutListener.listen(443) ? (this.ballotChoiceModelList.size() <= 1) : (ListenerUtil.mutListener.listen(442) ? (this.ballotChoiceModelList.size() < 1) : (ListenerUtil.mutListener.listen(441) ? (this.ballotChoiceModelList.size() != 1) : (ListenerUtil.mutListener.listen(440) ? (this.ballotChoiceModelList.size() == 1) : (this.ballotChoiceModelList.size() > 1))))))) {
                                if (!ListenerUtil.mutListener.listen(446)) {
                                    ThreemaApplication.sendMessageExecutorService.execute(createBallotRunnable);
                                }
                                if (!ListenerUtil.mutListener.listen(447)) {
                                    setResult(RESULT_OK);
                                }
                                if (!ListenerUtil.mutListener.listen(448)) {
                                    finish();
                                }
                            } else {
                                if (!ListenerUtil.mutListener.listen(445)) {
                                    Toast.makeText(BallotWizardActivity.this, getString(R.string.ballot_answer_count_error), Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public void prevPage() {
        if (!ListenerUtil.mutListener.listen(453)) {
            pager.setCurrentItem(0);
        }
    }

    public void setBallotTitle(String title) {
        if (!ListenerUtil.mutListener.listen(454)) {
            this.ballotTitle = title != null ? title.trim() : title;
        }
    }

    public void setBallotType(BallotModel.Type ballotType) {
        if (!ListenerUtil.mutListener.listen(455)) {
            this.ballotType = ballotType;
        }
    }

    public void setBallotAssessment(BallotModel.Assessment ballotAssessment) {
        if (!ListenerUtil.mutListener.listen(456)) {
            this.ballotAssessment = ballotAssessment;
        }
    }

    public List<BallotChoiceModel> getBallotChoiceModelList() {
        return this.ballotChoiceModelList;
    }

    public String getBallotTitle() {
        return this.ballotTitle;
    }

    public BallotModel.Type getBallotType() {
        return this.ballotType;
    }

    public BallotModel.Assessment getBallotAssessment() {
        return this.ballotAssessment;
    }

    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {

        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if (!ListenerUtil.mutListener.listen(457)) {
                switch(position) {
                    case 0:
                        return new BallotWizardFragment0();
                    case 1:
                        return new BallotWizardFragment1();
                    default:
                        break;
                }
            }
            return null;
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }

    protected void instantiate() {
        ServiceManager serviceManager = ThreemaApplication.getServiceManager();
        if (!ListenerUtil.mutListener.listen(465)) {
            if (serviceManager != null) {
                try {
                    if (!ListenerUtil.mutListener.listen(459)) {
                        this.messageService = serviceManager.getMessageService();
                    }
                    if (!ListenerUtil.mutListener.listen(460)) {
                        this.ballotService = serviceManager.getBallotService();
                    }
                    if (!ListenerUtil.mutListener.listen(461)) {
                        this.contactService = serviceManager.getContactService();
                    }
                    if (!ListenerUtil.mutListener.listen(462)) {
                        this.apiConnector = serviceManager.getAPIConnector();
                    }
                    if (!ListenerUtil.mutListener.listen(463)) {
                        this.groupService = serviceManager.getGroupService();
                    }
                    if (!ListenerUtil.mutListener.listen(464)) {
                        this.identity = serviceManager.getUserService().getIdentity();
                    }
                } catch (ThreemaException e) {
                    if (!ListenerUtil.mutListener.listen(458)) {
                        logger.error("Exception", e);
                    }
                }
            }
        }
    }

    protected boolean checkInstances() {
        return TestUtil.required(this.messageService, this.ballotService, this.apiConnector, this.contactService, this.groupService, this.identity);
    }

    public void startCopy() {
        Intent copyIntent = new Intent(this, BallotChooserActivity.class);
        if (!ListenerUtil.mutListener.listen(466)) {
            startActivityForResult(copyIntent, ThreemaActivity.ACTIVITY_ID_COPY_BALLOT);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (!ListenerUtil.mutListener.listen(478)) {
            if (resultCode == Activity.RESULT_OK) {
                if (!ListenerUtil.mutListener.listen(477)) {
                    if (requestCode == ThreemaActivity.ACTIVITY_ID_COPY_BALLOT) {
                        // get the ballot to copy
                        int ballotToCopyId = IntentDataUtil.getBallotId(data);
                        if (!ListenerUtil.mutListener.listen(476)) {
                            if ((ListenerUtil.mutListener.listen(472) ? ((ListenerUtil.mutListener.listen(471) ? (ballotToCopyId >= 0) : (ListenerUtil.mutListener.listen(470) ? (ballotToCopyId <= 0) : (ListenerUtil.mutListener.listen(469) ? (ballotToCopyId < 0) : (ListenerUtil.mutListener.listen(468) ? (ballotToCopyId != 0) : (ListenerUtil.mutListener.listen(467) ? (ballotToCopyId == 0) : (ballotToCopyId > 0)))))) || this.requiredInstances()) : ((ListenerUtil.mutListener.listen(471) ? (ballotToCopyId >= 0) : (ListenerUtil.mutListener.listen(470) ? (ballotToCopyId <= 0) : (ListenerUtil.mutListener.listen(469) ? (ballotToCopyId < 0) : (ListenerUtil.mutListener.listen(468) ? (ballotToCopyId != 0) : (ListenerUtil.mutListener.listen(467) ? (ballotToCopyId == 0) : (ballotToCopyId > 0)))))) && this.requiredInstances()))) {
                                BallotModel ballotModel = this.ballotService.get(ballotToCopyId);
                                if (!ListenerUtil.mutListener.listen(475)) {
                                    if (ballotModel != null) {
                                        if (!ListenerUtil.mutListener.listen(474)) {
                                            this.copyFrom(ballotModel);
                                        }
                                    } else {
                                        if (!ListenerUtil.mutListener.listen(473)) {
                                            logger.error("not a valid ballot model");
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(479)) {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void copyFrom(BallotModel ballotModel) {
        if (!ListenerUtil.mutListener.listen(495)) {
            if ((ListenerUtil.mutListener.listen(480) ? (ballotModel != null || this.requiredInstances()) : (ballotModel != null && this.requiredInstances()))) {
                if (!ListenerUtil.mutListener.listen(481)) {
                    this.ballotTitle = ballotModel.getName();
                }
                if (!ListenerUtil.mutListener.listen(482)) {
                    this.ballotType = ballotModel.getType();
                }
                if (!ListenerUtil.mutListener.listen(483)) {
                    this.ballotAssessment = ballotModel.getAssessment();
                }
                if (!ListenerUtil.mutListener.listen(484)) {
                    this.ballotChoiceModelList.clear();
                }
                try {
                    if (!ListenerUtil.mutListener.listen(489)) {
                        {
                            long _loopCounter8 = 0;
                            for (BallotChoiceModel ballotChoiceModel : this.ballotService.getChoices(ballotModel.getId())) {
                                ListenerUtil.loopListener.listen("_loopCounter8", ++_loopCounter8);
                                BallotChoiceModel choiceModel = new BallotChoiceModel();
                                if (!ListenerUtil.mutListener.listen(486)) {
                                    choiceModel.setName(ballotChoiceModel.getName());
                                }
                                if (!ListenerUtil.mutListener.listen(487)) {
                                    choiceModel.setType(ballotChoiceModel.getType());
                                }
                                if (!ListenerUtil.mutListener.listen(488)) {
                                    this.ballotChoiceModelList.add(choiceModel);
                                }
                            }
                        }
                    }
                } catch (NotAllowedException e) {
                    if (!ListenerUtil.mutListener.listen(485)) {
                        // cannot get choices
                        logger.error("Exception", e);
                    }
                }
                if (!ListenerUtil.mutListener.listen(490)) {
                    // goto first page
                    pager.setCurrentItem(0);
                }
                if (!ListenerUtil.mutListener.listen(494)) {
                    {
                        long _loopCounter9 = 0;
                        // loop all active fragments
                        for (WeakReference<BallotWizardFragment> ballotFragment : this.fragmentList) {
                            ListenerUtil.loopListener.listen("_loopCounter9", ++_loopCounter9);
                            BallotWizardFragment f = ballotFragment.get();
                            if (!ListenerUtil.mutListener.listen(493)) {
                                if ((ListenerUtil.mutListener.listen(491) ? (f != null || f.isAdded()) : (f != null && f.isAdded()))) {
                                    if (!ListenerUtil.mutListener.listen(492)) {
                                        f.updateView();
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public interface BallotWizardCallback {

        void onMissingTitle();

        void onPageSelected(int page);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        if (!ListenerUtil.mutListener.listen(496)) {
            super.onConfigurationChanged(newConfig);
        }
    }
}
