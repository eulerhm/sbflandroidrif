/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
 * Copyright (c) 2015-2021 Threema GmbH
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
package ch.threema.app.activities.wizard;

import android.accounts.Account;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;
import ch.threema.app.R;
import ch.threema.app.ThreemaApplication;
import ch.threema.app.activities.ThreemaAppCompatActivity;
import ch.threema.app.dialogs.WizardDialog;
import ch.threema.app.exceptions.EntryAlreadyExistsException;
import ch.threema.app.exceptions.FileSystemNotPresentException;
import ch.threema.app.exceptions.InvalidEntryException;
import ch.threema.app.fragments.wizard.WizardFragment0;
import ch.threema.app.fragments.wizard.WizardFragment1;
import ch.threema.app.fragments.wizard.WizardFragment2;
import ch.threema.app.fragments.wizard.WizardFragment3;
import ch.threema.app.fragments.wizard.WizardFragment4;
import ch.threema.app.fragments.wizard.WizardFragment5;
import ch.threema.app.jobs.WorkSyncService;
import ch.threema.app.managers.ServiceManager;
import ch.threema.app.routines.SynchronizeContactsRoutine;
import ch.threema.app.services.ConversationService;
import ch.threema.app.services.LocaleService;
import ch.threema.app.services.PreferenceService;
import ch.threema.app.services.SynchronizeContactsService;
import ch.threema.app.services.UserService;
import ch.threema.app.threemasafe.ThreemaSafeMDMConfig;
import ch.threema.app.threemasafe.ThreemaSafeServerInfo;
import ch.threema.app.threemasafe.ThreemaSafeService;
import ch.threema.app.ui.ParallaxViewPager;
import ch.threema.app.ui.StepPagerStrip;
import ch.threema.app.utils.AppRestrictionUtil;
import ch.threema.app.utils.ConfigUtils;
import ch.threema.app.utils.RuntimeUtil;
import ch.threema.app.utils.TestUtil;
import ch.threema.app.utils.TextUtil;
import ch.threema.app.workers.IdentityStatesWorker;
import ch.threema.app.exceptions.PolicyViolationException;
import ch.threema.client.LinkEmailException;
import ch.threema.client.LinkMobileNoException;
import ch.threema.localcrypto.MasterKeyLockedException;
import ch.threema.storage.models.ContactModel;
import static ch.threema.app.ThreemaApplication.PHONE_LINKED_PLACEHOLDER;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class WizardBaseActivity extends ThreemaAppCompatActivity implements ViewPager.OnPageChangeListener, View.OnClickListener, WizardFragment1.OnSettingsChangedListener, WizardFragment2.OnSettingsChangedListener, WizardFragment3.OnSettingsChangedListener, WizardFragment4.OnSettingsChangedListener, WizardFragment5.SettingsInterface, WizardDialog.WizardDialogCallback {

    private static final Logger logger = LoggerFactory.getLogger(WizardBaseActivity.class);

    private static final String DIALOG_TAG_USE_ID_AS_NICKNAME = "nd";

    private static final String DIALOG_TAG_INVALID_ENTRY = "ie";

    private static final String DIALOG_TAG_USE_ANONYMOUSLY = "ano";

    private static final String DIALOG_TAG_THREEMA_SAFE = "sd";

    private static final String DIALOG_TAG_PASSWORD_BAD = "pwb";

    private static final int PERMISSION_REQUEST_READ_CONTACTS = 2;

    private static final int NUM_PAGES = 6;

    private static final long FINISH_DELAY = 3 * 1000;

    private static final long DIALOG_DELAY = 200;

    public static final boolean DEFAULT_SYNC_CONTACTS = true;

    private static int lastPage = 0;

    private ParallaxViewPager viewPager;

    private ImageView prevButton, nextButton;

    private Button finishButton;

    private TextView nextText;

    private StepPagerStrip stepPagerStrip;

    private String nickname, email, number, prefix, presetMobile, presetEmail, safePassword;

    private ThreemaSafeServerInfo safeServerInfo = new ThreemaSafeServerInfo();

    private boolean isSyncContacts, syncContactsRestricted = false, skipWizard = false, readOnlyProfile = false;

    private ThreemaSafeMDMConfig safeConfig;

    private ServiceManager serviceManager;

    private UserService userService;

    private LocaleService localeService;

    private PreferenceService preferenceService;

    private ThreemaSafeService threemaSafeService;

    private boolean errorRaised = false;

    private WizardFragment5 fragment5;

    private final Handler finishHandler = new Handler();

    private final Handler dialogHandler = new Handler();

    private Runnable finishTask = new Runnable() {

        @Override
        public void run() {
            if (!ListenerUtil.mutListener.listen(699)) {
                RuntimeUtil.runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        if (!ListenerUtil.mutListener.listen(697)) {
                            fragment5.setContactsSyncInProgress(false, null);
                        }
                        if (!ListenerUtil.mutListener.listen(698)) {
                            prepareThreemaSafe();
                        }
                    }
                });
            }
        }
    };

    private Runnable showDialogDelayedTask(final int current, final int previous) {
        return () -> {
            RuntimeUtil.runOnUiThread(() -> {
                if (current == WizardFragment2.PAGE_ID && previous == WizardFragment1.PAGE_ID && TestUtil.empty(getSafePassword())) {
                    if (safeConfig.isBackupForced()) {
                        setPage(WizardFragment1.PAGE_ID);
                    } else if (!isReadOnlyProfile()) {
                        WizardDialog wizardDialog = WizardDialog.newInstance(R.string.safe_disable_confirm, R.string.yes, R.string.no);
                        wizardDialog.show(getSupportFragmentManager(), DIALOG_TAG_THREEMA_SAFE);
                    }
                }
                if (current == WizardFragment3.PAGE_ID && previous == WizardFragment2.PAGE_ID && TestUtil.empty(nickname)) {
                    if (!isReadOnlyProfile()) {
                        WizardDialog wizardDialog = WizardDialog.newInstance(R.string.new_wizard_use_id_as_nickname, R.string.yes, R.string.no);
                        wizardDialog.show(getSupportFragmentManager(), DIALOG_TAG_USE_ID_AS_NICKNAME);
                    }
                }
                if (current == WizardFragment4.PAGE_ID && previous == WizardFragment3.PAGE_ID) {
                    if (!isReadOnlyProfile()) {
                        if ((!TestUtil.empty(number) && TestUtil.empty(presetMobile) && !localeService.validatePhoneNumber(getPhone())) || ((!TestUtil.empty(email) && TestUtil.empty(presetEmail) && !Patterns.EMAIL_ADDRESS.matcher(email).matches()))) {
                            WizardDialog wizardDialog = WizardDialog.newInstance(ConfigUtils.isWorkBuild() ? R.string.new_wizard_phone_email_invalid : R.string.new_wizard_phone_invalid, R.string.ok);
                            wizardDialog.show(getSupportFragmentManager(), DIALOG_TAG_INVALID_ENTRY);
                        }
                    }
                }
                if (current == WizardFragment5.PAGE_ID && previous == WizardFragment4.PAGE_ID) {
                    if (!isReadOnlyProfile()) {
                        boolean needConfirm;
                        if (ConfigUtils.isWorkBuild()) {
                            needConfirm = TestUtil.empty(number) && TestUtil.empty(email) && TestUtil.empty(getPresetEmail()) && TestUtil.empty(getPresetPhone());
                        } else {
                            needConfirm = TestUtil.empty(number) && TestUtil.empty(getPresetPhone());
                        }
                        if (needConfirm) {
                            WizardDialog wizardDialog = WizardDialog.newInstance(ConfigUtils.isWorkBuild() ? R.string.new_wizard_anonymous_confirm : R.string.new_wizard_anonymous_confirm_phone_only, R.string.yes, R.string.no);
                            wizardDialog.show(getSupportFragmentManager(), DIALOG_TAG_USE_ANONYMOUSLY);
                        }
                    }
                }
            });
        };
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(700)) {
            super.onCreate(savedInstanceState);
        }
        try {
            if (!ListenerUtil.mutListener.listen(702)) {
                serviceManager = ThreemaApplication.getServiceManager();
            }
            if (!ListenerUtil.mutListener.listen(707)) {
                if (serviceManager != null) {
                    if (!ListenerUtil.mutListener.listen(703)) {
                        userService = serviceManager.getUserService();
                    }
                    if (!ListenerUtil.mutListener.listen(704)) {
                        localeService = serviceManager.getLocaleService();
                    }
                    if (!ListenerUtil.mutListener.listen(705)) {
                        preferenceService = serviceManager.getPreferenceService();
                    }
                    if (!ListenerUtil.mutListener.listen(706)) {
                        threemaSafeService = serviceManager.getThreemaSafeService();
                    }
                }
            }
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(701)) {
                finish();
            }
            return;
        }
        if (!ListenerUtil.mutListener.listen(711)) {
            if ((ListenerUtil.mutListener.listen(709) ? ((ListenerUtil.mutListener.listen(708) ? (userService == null && localeService == null) : (userService == null || localeService == null)) && preferenceService == null) : ((ListenerUtil.mutListener.listen(708) ? (userService == null && localeService == null) : (userService == null || localeService == null)) || preferenceService == null))) {
                if (!ListenerUtil.mutListener.listen(710)) {
                    finish();
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(712)) {
            setContentView(R.layout.activity_wizard);
        }
        if (!ListenerUtil.mutListener.listen(713)) {
            nextButton = findViewById(R.id.next_page_button);
        }
        if (!ListenerUtil.mutListener.listen(715)) {
            nextButton.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    if (!ListenerUtil.mutListener.listen(714)) {
                        nextPage();
                    }
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(716)) {
            prevButton = findViewById(R.id.prev_page_button);
        }
        if (!ListenerUtil.mutListener.listen(717)) {
            prevButton.setVisibility(View.GONE);
        }
        if (!ListenerUtil.mutListener.listen(719)) {
            prevButton.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    if (!ListenerUtil.mutListener.listen(718)) {
                        prevPage();
                    }
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(720)) {
            nextText = findViewById(R.id.next_text);
        }
        if (!ListenerUtil.mutListener.listen(721)) {
            nextText.setOnClickListener(this);
        }
        if (!ListenerUtil.mutListener.listen(722)) {
            stepPagerStrip = findViewById(R.id.strip);
        }
        if (!ListenerUtil.mutListener.listen(723)) {
            stepPagerStrip.setPageCount(NUM_PAGES);
        }
        if (!ListenerUtil.mutListener.listen(724)) {
            stepPagerStrip.setCurrentPage(WizardFragment0.PAGE_ID);
        }
        if (!ListenerUtil.mutListener.listen(725)) {
            viewPager = findViewById(R.id.pager);
        }
        if (!ListenerUtil.mutListener.listen(726)) {
            viewPager.addLayer(findViewById(R.id.layer0));
        }
        if (!ListenerUtil.mutListener.listen(727)) {
            viewPager.addLayer(findViewById(R.id.layer1));
        }
        if (!ListenerUtil.mutListener.listen(728)) {
            viewPager.setAdapter(new ScreenSlidePagerAdapter(getSupportFragmentManager()));
        }
        if (!ListenerUtil.mutListener.listen(729)) {
            viewPager.addOnPageChangeListener(this);
        }
        if (!ListenerUtil.mutListener.listen(730)) {
            presetMobile = this.userService.getLinkedMobile();
        }
        if (!ListenerUtil.mutListener.listen(731)) {
            presetEmail = this.userService.getLinkedEmail();
        }
        if (!ListenerUtil.mutListener.listen(732)) {
            safeConfig = ThreemaSafeMDMConfig.getInstance();
        }
        if (!ListenerUtil.mutListener.listen(757)) {
            if (ConfigUtils.isWorkRestricted()) {
                if (!ListenerUtil.mutListener.listen(739)) {
                    if (!getSafeDisabled()) {
                        if (!ListenerUtil.mutListener.listen(737)) {
                            safePassword = safeConfig.getPassword();
                        }
                        if (!ListenerUtil.mutListener.listen(738)) {
                            safeServerInfo = safeConfig.getServerInfo();
                        }
                    }
                }
                String stringPreset;
                Boolean booleanPreset;
                stringPreset = AppRestrictionUtil.getStringRestriction(getString(R.string.restriction__linked_email));
                if (!ListenerUtil.mutListener.listen(741)) {
                    if (stringPreset != null) {
                        if (!ListenerUtil.mutListener.listen(740)) {
                            email = stringPreset;
                        }
                    }
                }
                stringPreset = AppRestrictionUtil.getStringRestriction(getString(R.string.restriction__linked_phone));
                if (!ListenerUtil.mutListener.listen(743)) {
                    if (stringPreset != null) {
                        if (!ListenerUtil.mutListener.listen(742)) {
                            splitMobile(stringPreset);
                        }
                    }
                }
                stringPreset = AppRestrictionUtil.getStringRestriction(getString(R.string.restriction__nickname));
                if (!ListenerUtil.mutListener.listen(746)) {
                    if (stringPreset != null) {
                        if (!ListenerUtil.mutListener.listen(745)) {
                            nickname = stringPreset;
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(744)) {
                            nickname = userService.getIdentity();
                        }
                    }
                }
                booleanPreset = AppRestrictionUtil.getBooleanRestriction(getString(R.string.restriction__contact_sync));
                if (!ListenerUtil.mutListener.listen(750)) {
                    if (booleanPreset != null) {
                        if (!ListenerUtil.mutListener.listen(748)) {
                            isSyncContacts = booleanPreset;
                        }
                        if (!ListenerUtil.mutListener.listen(749)) {
                            syncContactsRestricted = true;
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(747)) {
                            isSyncContacts = DEFAULT_SYNC_CONTACTS;
                        }
                    }
                }
                booleanPreset = AppRestrictionUtil.getBooleanRestriction(getString(R.string.restriction__readonly_profile));
                if (!ListenerUtil.mutListener.listen(752)) {
                    if (booleanPreset != null) {
                        if (!ListenerUtil.mutListener.listen(751)) {
                            readOnlyProfile = booleanPreset;
                        }
                    }
                }
                booleanPreset = AppRestrictionUtil.getBooleanRestriction(getString(R.string.restriction__skip_wizard));
                if (!ListenerUtil.mutListener.listen(756)) {
                    if (booleanPreset != null) {
                        if (!ListenerUtil.mutListener.listen(755)) {
                            if (booleanPreset) {
                                if (!ListenerUtil.mutListener.listen(753)) {
                                    skipWizard = true;
                                }
                                if (!ListenerUtil.mutListener.listen(754)) {
                                    viewPager.setCurrentItem(WizardFragment5.PAGE_ID);
                                }
                            }
                        }
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(734)) {
                    // ignore backup presets in restricted mode
                    if (!TestUtil.empty(presetMobile)) {
                        if (!ListenerUtil.mutListener.listen(733)) {
                            splitMobile(presetMobile);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(736)) {
                    if (!TestUtil.empty(presetEmail)) {
                        if (!ListenerUtil.mutListener.listen(735)) {
                            email = presetEmail;
                        }
                    }
                }
            }
        }
    }

    private void splitMobile(String phoneNumber) {
        if (!ListenerUtil.mutListener.listen(764)) {
            if (PHONE_LINKED_PLACEHOLDER.equals(phoneNumber)) {
                if (!ListenerUtil.mutListener.listen(762)) {
                    prefix = "";
                }
                if (!ListenerUtil.mutListener.listen(763)) {
                    number = PHONE_LINKED_PLACEHOLDER;
                }
            } else {
                try {
                    PhoneNumberUtil phoneNumberUtil = PhoneNumberUtil.getInstance();
                    Phonenumber.PhoneNumber numberProto = null;
                    if (!ListenerUtil.mutListener.listen(759)) {
                        numberProto = phoneNumberUtil.parse(phoneNumber, "");
                    }
                    if (!ListenerUtil.mutListener.listen(760)) {
                        prefix = "+" + String.valueOf(numberProto.getCountryCode());
                    }
                    if (!ListenerUtil.mutListener.listen(761)) {
                        number = String.valueOf(numberProto.getNationalNumber());
                    }
                } catch (NumberParseException e) {
                    if (!ListenerUtil.mutListener.listen(758)) {
                        logger.error("Exception", e);
                    }
                }
            }
        }
    }

    @Override
    protected void onDestroy() {
        if (!ListenerUtil.mutListener.listen(765)) {
            viewPager.removeOnPageChangeListener(this);
        }
        if (!ListenerUtil.mutListener.listen(766)) {
            super.onDestroy();
        }
    }

    /**
     *  This method will be invoked when the current page is scrolled, either as part
     *  of a programmatically initiated smooth scroll or a user initiated touch scroll.
     *
     *  @param position             Position index of the first page currently being displayed.
     *                              Page position+1 will be visible if positionOffset is nonzero.
     *  @param positionOffset       Value from [0, 1) indicating the offset from the page at position.
     *  @param positionOffsetPixels Value in pixels indicating the offset from position.
     */
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    /**
     *  This method will be invoked when a new page becomes selected. Animation is not
     *  necessarily complete.
     *
     *  @param position Position index of the new selected page.
     */
    @SuppressLint("StaticFieldLeak")
    @Override
    public void onPageSelected(int position) {
        if (!ListenerUtil.mutListener.listen(767)) {
            prevButton.setVisibility(position == WizardFragment0.PAGE_ID ? View.GONE : View.VISIBLE);
        }
        if (!ListenerUtil.mutListener.listen(777)) {
            nextButton.setVisibility((ListenerUtil.mutListener.listen(776) ? (position >= (ListenerUtil.mutListener.listen(771) ? (NUM_PAGES % 1) : (ListenerUtil.mutListener.listen(770) ? (NUM_PAGES / 1) : (ListenerUtil.mutListener.listen(769) ? (NUM_PAGES * 1) : (ListenerUtil.mutListener.listen(768) ? (NUM_PAGES + 1) : (NUM_PAGES - 1)))))) : (ListenerUtil.mutListener.listen(775) ? (position <= (ListenerUtil.mutListener.listen(771) ? (NUM_PAGES % 1) : (ListenerUtil.mutListener.listen(770) ? (NUM_PAGES / 1) : (ListenerUtil.mutListener.listen(769) ? (NUM_PAGES * 1) : (ListenerUtil.mutListener.listen(768) ? (NUM_PAGES + 1) : (NUM_PAGES - 1)))))) : (ListenerUtil.mutListener.listen(774) ? (position > (ListenerUtil.mutListener.listen(771) ? (NUM_PAGES % 1) : (ListenerUtil.mutListener.listen(770) ? (NUM_PAGES / 1) : (ListenerUtil.mutListener.listen(769) ? (NUM_PAGES * 1) : (ListenerUtil.mutListener.listen(768) ? (NUM_PAGES + 1) : (NUM_PAGES - 1)))))) : (ListenerUtil.mutListener.listen(773) ? (position < (ListenerUtil.mutListener.listen(771) ? (NUM_PAGES % 1) : (ListenerUtil.mutListener.listen(770) ? (NUM_PAGES / 1) : (ListenerUtil.mutListener.listen(769) ? (NUM_PAGES * 1) : (ListenerUtil.mutListener.listen(768) ? (NUM_PAGES + 1) : (NUM_PAGES - 1)))))) : (ListenerUtil.mutListener.listen(772) ? (position != (ListenerUtil.mutListener.listen(771) ? (NUM_PAGES % 1) : (ListenerUtil.mutListener.listen(770) ? (NUM_PAGES / 1) : (ListenerUtil.mutListener.listen(769) ? (NUM_PAGES * 1) : (ListenerUtil.mutListener.listen(768) ? (NUM_PAGES + 1) : (NUM_PAGES - 1)))))) : (position == (ListenerUtil.mutListener.listen(771) ? (NUM_PAGES % 1) : (ListenerUtil.mutListener.listen(770) ? (NUM_PAGES / 1) : (ListenerUtil.mutListener.listen(769) ? (NUM_PAGES * 1) : (ListenerUtil.mutListener.listen(768) ? (NUM_PAGES + 1) : (NUM_PAGES - 1))))))))))) ? View.GONE : View.VISIBLE);
        }
        if (!ListenerUtil.mutListener.listen(778)) {
            nextText.setVisibility(View.GONE);
        }
        if (!ListenerUtil.mutListener.listen(779)) {
            stepPagerStrip.setCurrentPage(position);
        }
        if (!ListenerUtil.mutListener.listen(784)) {
            if ((ListenerUtil.mutListener.listen(780) ? (position == WizardFragment1.PAGE_ID || safeConfig.isSkipBackupPasswordEntry()) : (position == WizardFragment1.PAGE_ID && safeConfig.isSkipBackupPasswordEntry()))) {
                if (!ListenerUtil.mutListener.listen(783)) {
                    if (lastPage == WizardFragment0.PAGE_ID) {
                        if (!ListenerUtil.mutListener.listen(782)) {
                            nextPage();
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(781)) {
                            prevPage();
                        }
                    }
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(792)) {
            if ((ListenerUtil.mutListener.listen(785) ? (position == WizardFragment2.PAGE_ID || lastPage == WizardFragment1.PAGE_ID) : (position == WizardFragment2.PAGE_ID && lastPage == WizardFragment1.PAGE_ID))) {
                if (!ListenerUtil.mutListener.listen(791)) {
                    if (!TextUtils.isEmpty(safePassword)) {
                        if (!ListenerUtil.mutListener.listen(790)) {
                            new AsyncTask<Void, Void, Boolean>() {

                                @Override
                                protected Boolean doInBackground(Void... voids) {
                                    return TextUtil.checkBadPassword(getApplicationContext(), safePassword);
                                }

                                @Override
                                protected void onPostExecute(Boolean isBad) {
                                    if (!ListenerUtil.mutListener.listen(789)) {
                                        if (isBad) {
                                            Context context = WizardBaseActivity.this;
                                            if (!ListenerUtil.mutListener.listen(788)) {
                                                if (AppRestrictionUtil.isSafePasswordPatternSet(context)) {
                                                    WizardDialog wizardDialog = WizardDialog.newInstance(AppRestrictionUtil.getSafePasswordMessage(context), R.string.try_again);
                                                    if (!ListenerUtil.mutListener.listen(787)) {
                                                        wizardDialog.show(getSupportFragmentManager(), DIALOG_TAG_PASSWORD_BAD);
                                                    }
                                                } else {
                                                    WizardDialog wizardDialog = WizardDialog.newInstance(R.string.password_bad_explain, R.string.try_again, R.string.continue_anyway);
                                                    if (!ListenerUtil.mutListener.listen(786)) {
                                                        wizardDialog.show(getSupportFragmentManager(), DIALOG_TAG_PASSWORD_BAD);
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }.execute();
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(812)) {
            if ((ListenerUtil.mutListener.listen(809) ? ((ListenerUtil.mutListener.listen(803) ? ((ListenerUtil.mutListener.listen(797) ? (position >= lastPage) : (ListenerUtil.mutListener.listen(796) ? (position <= lastPage) : (ListenerUtil.mutListener.listen(795) ? (position < lastPage) : (ListenerUtil.mutListener.listen(794) ? (position != lastPage) : (ListenerUtil.mutListener.listen(793) ? (position == lastPage) : (position > lastPage)))))) || (ListenerUtil.mutListener.listen(802) ? (position <= WizardFragment2.PAGE_ID) : (ListenerUtil.mutListener.listen(801) ? (position > WizardFragment2.PAGE_ID) : (ListenerUtil.mutListener.listen(800) ? (position < WizardFragment2.PAGE_ID) : (ListenerUtil.mutListener.listen(799) ? (position != WizardFragment2.PAGE_ID) : (ListenerUtil.mutListener.listen(798) ? (position == WizardFragment2.PAGE_ID) : (position >= WizardFragment2.PAGE_ID))))))) : ((ListenerUtil.mutListener.listen(797) ? (position >= lastPage) : (ListenerUtil.mutListener.listen(796) ? (position <= lastPage) : (ListenerUtil.mutListener.listen(795) ? (position < lastPage) : (ListenerUtil.mutListener.listen(794) ? (position != lastPage) : (ListenerUtil.mutListener.listen(793) ? (position == lastPage) : (position > lastPage)))))) && (ListenerUtil.mutListener.listen(802) ? (position <= WizardFragment2.PAGE_ID) : (ListenerUtil.mutListener.listen(801) ? (position > WizardFragment2.PAGE_ID) : (ListenerUtil.mutListener.listen(800) ? (position < WizardFragment2.PAGE_ID) : (ListenerUtil.mutListener.listen(799) ? (position != WizardFragment2.PAGE_ID) : (ListenerUtil.mutListener.listen(798) ? (position == WizardFragment2.PAGE_ID) : (position >= WizardFragment2.PAGE_ID)))))))) || (ListenerUtil.mutListener.listen(808) ? (position >= WizardFragment5.PAGE_ID) : (ListenerUtil.mutListener.listen(807) ? (position > WizardFragment5.PAGE_ID) : (ListenerUtil.mutListener.listen(806) ? (position < WizardFragment5.PAGE_ID) : (ListenerUtil.mutListener.listen(805) ? (position != WizardFragment5.PAGE_ID) : (ListenerUtil.mutListener.listen(804) ? (position == WizardFragment5.PAGE_ID) : (position <= WizardFragment5.PAGE_ID))))))) : ((ListenerUtil.mutListener.listen(803) ? ((ListenerUtil.mutListener.listen(797) ? (position >= lastPage) : (ListenerUtil.mutListener.listen(796) ? (position <= lastPage) : (ListenerUtil.mutListener.listen(795) ? (position < lastPage) : (ListenerUtil.mutListener.listen(794) ? (position != lastPage) : (ListenerUtil.mutListener.listen(793) ? (position == lastPage) : (position > lastPage)))))) || (ListenerUtil.mutListener.listen(802) ? (position <= WizardFragment2.PAGE_ID) : (ListenerUtil.mutListener.listen(801) ? (position > WizardFragment2.PAGE_ID) : (ListenerUtil.mutListener.listen(800) ? (position < WizardFragment2.PAGE_ID) : (ListenerUtil.mutListener.listen(799) ? (position != WizardFragment2.PAGE_ID) : (ListenerUtil.mutListener.listen(798) ? (position == WizardFragment2.PAGE_ID) : (position >= WizardFragment2.PAGE_ID))))))) : ((ListenerUtil.mutListener.listen(797) ? (position >= lastPage) : (ListenerUtil.mutListener.listen(796) ? (position <= lastPage) : (ListenerUtil.mutListener.listen(795) ? (position < lastPage) : (ListenerUtil.mutListener.listen(794) ? (position != lastPage) : (ListenerUtil.mutListener.listen(793) ? (position == lastPage) : (position > lastPage)))))) && (ListenerUtil.mutListener.listen(802) ? (position <= WizardFragment2.PAGE_ID) : (ListenerUtil.mutListener.listen(801) ? (position > WizardFragment2.PAGE_ID) : (ListenerUtil.mutListener.listen(800) ? (position < WizardFragment2.PAGE_ID) : (ListenerUtil.mutListener.listen(799) ? (position != WizardFragment2.PAGE_ID) : (ListenerUtil.mutListener.listen(798) ? (position == WizardFragment2.PAGE_ID) : (position >= WizardFragment2.PAGE_ID)))))))) && (ListenerUtil.mutListener.listen(808) ? (position >= WizardFragment5.PAGE_ID) : (ListenerUtil.mutListener.listen(807) ? (position > WizardFragment5.PAGE_ID) : (ListenerUtil.mutListener.listen(806) ? (position < WizardFragment5.PAGE_ID) : (ListenerUtil.mutListener.listen(805) ? (position != WizardFragment5.PAGE_ID) : (ListenerUtil.mutListener.listen(804) ? (position == WizardFragment5.PAGE_ID) : (position <= WizardFragment5.PAGE_ID))))))))) {
                if (!ListenerUtil.mutListener.listen(810)) {
                    // we delay dialogs for a few milliseconds to prevent stuttering of the page change animation
                    dialogHandler.removeCallbacks(showDialogDelayedTask(position, lastPage));
                }
                if (!ListenerUtil.mutListener.listen(811)) {
                    dialogHandler.postDelayed(showDialogDelayedTask(position, lastPage), DIALOG_DELAY);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(813)) {
            lastPage = position;
        }
    }

    /**
     *  Called when the scroll state changes. Useful for discovering when the user
     *  begins dragging, when the pager is automatically settling to the current page,
     *  or when it is fully stopped/idle.
     *
     *  @param state The new scroll state.
     *  @see ViewPager#SCROLL_STATE_IDLE
     *  @see ViewPager#SCROLL_STATE_DRAGGING
     *  @see ViewPager#SCROLL_STATE_SETTLING
     */
    @Override
    public void onPageScrollStateChanged(int state) {
    }

    /**
     *  Called when a view has been clicked.
     *
     *  @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        if (!ListenerUtil.mutListener.listen(816)) {
            if (v.equals(nextButton)) {
                if (!ListenerUtil.mutListener.listen(815)) {
                    nextPage();
                }
            } else if (v.equals(prevButton)) {
                if (!ListenerUtil.mutListener.listen(814)) {
                    prevPage();
                }
            }
        }
    }

    @Override
    public void onWizardFinished(WizardFragment5 fragment, Button finishButton) {
        if (!ListenerUtil.mutListener.listen(817)) {
            errorRaised = false;
        }
        if (!ListenerUtil.mutListener.listen(818)) {
            fragment5 = fragment;
        }
        if (!ListenerUtil.mutListener.listen(819)) {
            viewPager.lock(true);
        }
        if (!ListenerUtil.mutListener.listen(820)) {
            this.finishButton = finishButton;
        }
        if (!ListenerUtil.mutListener.listen(821)) {
            prevButton.setVisibility(View.GONE);
        }
        if (!ListenerUtil.mutListener.listen(823)) {
            if (finishButton != null) {
                if (!ListenerUtil.mutListener.listen(822)) {
                    finishButton.setEnabled(false);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(824)) {
            userService.setPublicNickname(this.nickname);
        }
        if (!ListenerUtil.mutListener.listen(825)) {
            // also calls linkEmail() and syncContactsAndFinish();
            linkPhone();
        }
    }

    @Override
    public void onNicknameSet(String nickname) {
        if (!ListenerUtil.mutListener.listen(826)) {
            this.nickname = nickname;
        }
    }

    @Override
    public void onPhoneSet(String phoneNumber) {
        if (!ListenerUtil.mutListener.listen(827)) {
            this.number = phoneNumber;
        }
    }

    @Override
    public void onPrefixSet(String prefix) {
        if (!ListenerUtil.mutListener.listen(828)) {
            this.prefix = prefix;
        }
    }

    @Override
    public void onEmailSet(String email) {
        if (!ListenerUtil.mutListener.listen(829)) {
            this.email = email;
        }
    }

    @Override
    public void onSafePasswordSet(final String password) {
        if (!ListenerUtil.mutListener.listen(830)) {
            safePassword = password;
        }
    }

    @Override
    public void onSafeServerInfoSet(ThreemaSafeServerInfo safeServerInfo) {
        if (!ListenerUtil.mutListener.listen(831)) {
            this.safeServerInfo = safeServerInfo;
        }
    }

    @Override
    public void onSyncContactsSet(boolean enabled) {
        if (!ListenerUtil.mutListener.listen(833)) {
            if (!this.syncContactsRestricted) {
                if (!ListenerUtil.mutListener.listen(832)) {
                    this.isSyncContacts = enabled;
                }
            }
        }
    }

    @Override
    public String getNickname() {
        return this.nickname;
    }

    @Override
    public String getPhone() {
        if (!ListenerUtil.mutListener.listen(834)) {
            if (PHONE_LINKED_PLACEHOLDER.equals(this.number)) {
                return this.number;
            }
        }
        String phone = this.prefix + this.number;
        if (!ListenerUtil.mutListener.listen(835)) {
            if (localeService.validatePhoneNumber(phone)) {
                return serviceManager.getLocaleService().getNormalizedPhoneNumber(phone);
            }
        }
        return "";
    }

    @Override
    public String getNumber() {
        return this.number;
    }

    @Override
    public String getPrefix() {
        return this.prefix;
    }

    @Override
    public String getEmail() {
        return ((ListenerUtil.mutListener.listen(841) ? (this.email != null || (ListenerUtil.mutListener.listen(840) ? (this.email.length() >= 4) : (ListenerUtil.mutListener.listen(839) ? (this.email.length() <= 4) : (ListenerUtil.mutListener.listen(838) ? (this.email.length() < 4) : (ListenerUtil.mutListener.listen(837) ? (this.email.length() != 4) : (ListenerUtil.mutListener.listen(836) ? (this.email.length() == 4) : (this.email.length() > 4))))))) : (this.email != null && (ListenerUtil.mutListener.listen(840) ? (this.email.length() >= 4) : (ListenerUtil.mutListener.listen(839) ? (this.email.length() <= 4) : (ListenerUtil.mutListener.listen(838) ? (this.email.length() < 4) : (ListenerUtil.mutListener.listen(837) ? (this.email.length() != 4) : (ListenerUtil.mutListener.listen(836) ? (this.email.length() == 4) : (this.email.length() > 4))))))))) ? this.email : "";
    }

    @Override
    public String getPresetPhone() {
        return this.presetMobile;
    }

    @Override
    public String getPresetEmail() {
        return this.presetEmail;
    }

    @Override
    public boolean getSafeForcePasswordEntry() {
        return safeConfig.isBackupForced();
    }

    @Override
    public boolean getSafeSkipBackupPasswordEntry() {
        return safeConfig.isSkipBackupPasswordEntry();
    }

    @Override
    public boolean getSafeDisabled() {
        return safeConfig.isBackupDisabled();
    }

    @Override
    public String getSafePassword() {
        return this.safePassword;
    }

    @Override
    public ThreemaSafeServerInfo getSafeServerInfo() {
        return this.safeServerInfo;
    }

    @Override
    public boolean getSyncContacts() {
        return this.isSyncContacts;
    }

    @Override
    public boolean isReadOnlyProfile() {
        return this.readOnlyProfile;
    }

    @Override
    public boolean isSkipWizard() {
        return this.skipWizard;
    }

    @Override
    public void onYes(String tag, Object data) {
        if (!ListenerUtil.mutListener.listen(845)) {
            switch(tag) {
                case DIALOG_TAG_USE_ID_AS_NICKNAME:
                    if (!ListenerUtil.mutListener.listen(842)) {
                        this.nickname = this.userService.getIdentity();
                    }
                    break;
                case DIALOG_TAG_INVALID_ENTRY:
                    if (!ListenerUtil.mutListener.listen(843)) {
                        prevPage();
                    }
                    break;
                case DIALOG_TAG_PASSWORD_BAD:
                    if (!ListenerUtil.mutListener.listen(844)) {
                        setPage(WizardFragment1.PAGE_ID);
                    }
                    break;
                case DIALOG_TAG_THREEMA_SAFE:
                    break;
            }
        }
    }

    @Override
    public void onNo(String tag) {
        if (!ListenerUtil.mutListener.listen(849)) {
            switch(tag) {
                case DIALOG_TAG_USE_ID_AS_NICKNAME:
                    if (!ListenerUtil.mutListener.listen(846)) {
                        prevPage();
                    }
                    break;
                case DIALOG_TAG_USE_ANONYMOUSLY:
                    if (!ListenerUtil.mutListener.listen(847)) {
                        setPage(WizardFragment3.PAGE_ID);
                    }
                    break;
                case DIALOG_TAG_THREEMA_SAFE:
                    if (!ListenerUtil.mutListener.listen(848)) {
                        prevPage();
                    }
                    break;
                case DIALOG_TAG_PASSWORD_BAD:
                    break;
            }
        }
    }

    @Override
    public void onBackPressed() {
        if (!ListenerUtil.mutListener.listen(852)) {
            if ((ListenerUtil.mutListener.listen(850) ? (prevButton != null || prevButton.getVisibility() == View.VISIBLE) : (prevButton != null && prevButton.getVisibility() == View.VISIBLE))) {
                if (!ListenerUtil.mutListener.listen(851)) {
                    prevPage();
                }
            }
        }
    }

    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {

        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm, FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        }

        @Override
        public Fragment getItem(int position) {
            if (!ListenerUtil.mutListener.listen(853)) {
                switch(position) {
                    case WizardFragment0.PAGE_ID:
                        return new WizardFragment0();
                    case WizardFragment1.PAGE_ID:
                        return new WizardFragment1();
                    case WizardFragment2.PAGE_ID:
                        return new WizardFragment2();
                    case WizardFragment3.PAGE_ID:
                        return new WizardFragment3();
                    case WizardFragment4.PAGE_ID:
                        return new WizardFragment4();
                    case WizardFragment5.PAGE_ID:
                        return new WizardFragment5();
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

    public void nextPage() {
        int currentItem = viewPager.getCurrentItem() + 1;
        if (!ListenerUtil.mutListener.listen(860)) {
            if ((ListenerUtil.mutListener.listen(858) ? (currentItem >= NUM_PAGES) : (ListenerUtil.mutListener.listen(857) ? (currentItem <= NUM_PAGES) : (ListenerUtil.mutListener.listen(856) ? (currentItem > NUM_PAGES) : (ListenerUtil.mutListener.listen(855) ? (currentItem != NUM_PAGES) : (ListenerUtil.mutListener.listen(854) ? (currentItem == NUM_PAGES) : (currentItem < NUM_PAGES))))))) {
                if (!ListenerUtil.mutListener.listen(859)) {
                    viewPager.setCurrentItem(currentItem);
                }
            }
        }
    }

    public void prevPage() {
        int currentItem = viewPager.getCurrentItem();
        if (!ListenerUtil.mutListener.listen(871)) {
            if ((ListenerUtil.mutListener.listen(865) ? (currentItem >= 0) : (ListenerUtil.mutListener.listen(864) ? (currentItem <= 0) : (ListenerUtil.mutListener.listen(863) ? (currentItem > 0) : (ListenerUtil.mutListener.listen(862) ? (currentItem < 0) : (ListenerUtil.mutListener.listen(861) ? (currentItem == 0) : (currentItem != 0))))))) {
                if (!ListenerUtil.mutListener.listen(870)) {
                    viewPager.setCurrentItem((ListenerUtil.mutListener.listen(869) ? (currentItem % 1) : (ListenerUtil.mutListener.listen(868) ? (currentItem / 1) : (ListenerUtil.mutListener.listen(867) ? (currentItem * 1) : (ListenerUtil.mutListener.listen(866) ? (currentItem + 1) : (currentItem - 1))))));
                }
            }
        }
    }

    public void setPage(int page) {
        if (!ListenerUtil.mutListener.listen(872)) {
            viewPager.setCurrentItem(page);
        }
    }

    @SuppressLint("StaticFieldLeak")
    private void linkEmail(final WizardFragment5 fragment) {
        final String newEmail = getEmail();
        if (!ListenerUtil.mutListener.listen(874)) {
            if (TestUtil.empty(newEmail)) {
                if (!ListenerUtil.mutListener.listen(873)) {
                    initSyncAndFinish();
                }
                return;
            }
        }
        boolean isNewEmail = (!((ListenerUtil.mutListener.listen(875) ? (presetEmail != null || presetEmail.equals(newEmail)) : (presetEmail != null && presetEmail.equals(newEmail)))));
        if (!ListenerUtil.mutListener.listen(888)) {
            if ((ListenerUtil.mutListener.listen(876) ? ((userService.getEmailLinkingState() != UserService.LinkingState_LINKED) || isNewEmail) : ((userService.getEmailLinkingState() != UserService.LinkingState_LINKED) && isNewEmail))) {
                if (!ListenerUtil.mutListener.listen(887)) {
                    new AsyncTask<Void, Void, String>() {

                        @Override
                        protected void onPreExecute() {
                            if (!ListenerUtil.mutListener.listen(878)) {
                                fragment.setEmailLinkingInProgress(true);
                            }
                        }

                        @Override
                        protected String doInBackground(Void... params) {
                            try {
                                if (!ListenerUtil.mutListener.listen(881)) {
                                    userService.linkWithEmail(email);
                                }
                            } catch (LinkEmailException e) {
                                if (!ListenerUtil.mutListener.listen(879)) {
                                    logger.error("Exception", e);
                                }
                                return e.getMessage();
                            } catch (Exception e) {
                                if (!ListenerUtil.mutListener.listen(880)) {
                                    logger.error("Exception", e);
                                }
                                return getString(R.string.internet_connection_required);
                            }
                            return null;
                        }

                        @Override
                        protected void onPostExecute(String result) {
                            if (!ListenerUtil.mutListener.listen(885)) {
                                if (result != null) {
                                    if (!ListenerUtil.mutListener.listen(883)) {
                                        fragment.setEmailLinkingAlert(result);
                                    }
                                    if (!ListenerUtil.mutListener.listen(884)) {
                                        errorRaised = true;
                                    }
                                } else {
                                    if (!ListenerUtil.mutListener.listen(882)) {
                                        fragment.setEmailLinkingInProgress(false);
                                    }
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(886)) {
                                initSyncAndFinish();
                            }
                        }
                    }.execute();
                }
            } else {
                if (!ListenerUtil.mutListener.listen(877)) {
                    initSyncAndFinish();
                }
            }
        }
    }

    @SuppressLint("StaticFieldLeak")
    private void linkPhone() {
        final String phone = getPhone();
        if (!ListenerUtil.mutListener.listen(890)) {
            if (TestUtil.empty(phone)) {
                if (!ListenerUtil.mutListener.listen(889)) {
                    linkEmail(fragment5);
                }
                return;
            }
        }
        boolean isNewPhoneNumber = ((ListenerUtil.mutListener.listen(891) ? (presetMobile == null && !presetMobile.equals(phone)) : (presetMobile == null || !presetMobile.equals(phone))));
        if (!ListenerUtil.mutListener.listen(904)) {
            // start linking activity only if not already linked
            if ((ListenerUtil.mutListener.listen(892) ? ((userService.getMobileLinkingState() != UserService.LinkingState_LINKED) || isNewPhoneNumber) : ((userService.getMobileLinkingState() != UserService.LinkingState_LINKED) && isNewPhoneNumber))) {
                if (!ListenerUtil.mutListener.listen(903)) {
                    new AsyncTask<Void, Void, String>() {

                        @Override
                        protected void onPreExecute() {
                            if (!ListenerUtil.mutListener.listen(894)) {
                                fragment5.setMobileLinkingInProgress(true);
                            }
                        }

                        @Override
                        protected String doInBackground(Void... params) {
                            try {
                                if (!ListenerUtil.mutListener.listen(897)) {
                                    userService.linkWithMobileNumber(phone);
                                }
                            } catch (LinkMobileNoException e) {
                                if (!ListenerUtil.mutListener.listen(895)) {
                                    logger.error("Exception", e);
                                }
                                return e.getMessage();
                            } catch (Exception e) {
                                if (!ListenerUtil.mutListener.listen(896)) {
                                    logger.error("Exception", e);
                                }
                                return getString(R.string.internet_connection_required);
                            }
                            return null;
                        }

                        @Override
                        protected void onPostExecute(String result) {
                            if (!ListenerUtil.mutListener.listen(901)) {
                                if (result != null) {
                                    if (!ListenerUtil.mutListener.listen(899)) {
                                        fragment5.setMobileLinkingAlert(result);
                                    }
                                    if (!ListenerUtil.mutListener.listen(900)) {
                                        errorRaised = true;
                                    }
                                } else {
                                    if (!ListenerUtil.mutListener.listen(898)) {
                                        fragment5.setMobileLinkingInProgress(false);
                                    }
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(902)) {
                                linkEmail(fragment5);
                            }
                        }
                    }.execute();
                }
            } else {
                if (!ListenerUtil.mutListener.listen(893)) {
                    linkEmail(fragment5);
                }
            }
        }
    }

    @SuppressLint("StaticFieldLeak")
    private void addUser(final String id, final String first, final String last) {
        if (!ListenerUtil.mutListener.listen(910)) {
            new AsyncTask<Void, Void, Void>() {

                @Override
                protected Void doInBackground(Void... params) {
                    try {
                        ContactModel newUser = serviceManager.getContactService().createContactByIdentity(id, true);
                        if (!ListenerUtil.mutListener.listen(909)) {
                            if (newUser != null) {
                                if (!ListenerUtil.mutListener.listen(906)) {
                                    newUser.setFirstName(first);
                                }
                                if (!ListenerUtil.mutListener.listen(907)) {
                                    newUser.setLastName(last);
                                }
                                if (!ListenerUtil.mutListener.listen(908)) {
                                    serviceManager.getContactService().save(newUser);
                                }
                            }
                        }
                    } catch (InvalidEntryException | MasterKeyLockedException | FileSystemNotPresentException e) {
                        if (!ListenerUtil.mutListener.listen(905)) {
                            logger.error("Exception", e);
                        }
                    } catch (EntryAlreadyExistsException | PolicyViolationException e) {
                    }
                    return null;
                }
            }.execute();
        }
    }

    private void finishAndRestart() {
        if (!ListenerUtil.mutListener.listen(911)) {
            preferenceService.setWizardRunning(false);
        }
        if (!ListenerUtil.mutListener.listen(912)) {
            preferenceService.setLatestVersion(this);
        }
        if (!ListenerUtil.mutListener.listen(913)) {
            addUser(ThreemaApplication.ECHO_USER_IDENTITY, "Echo", "Test");
        }
        // flush conversation cache (after a restore)
        try {
            ConversationService conversationService = serviceManager.getConversationService();
            if (!ListenerUtil.mutListener.listen(916)) {
                if (conversationService != null) {
                    if (!ListenerUtil.mutListener.listen(915)) {
                        conversationService.reset();
                    }
                }
            }
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(914)) {
                logger.error("Exception", e);
            }
        }
        if (!ListenerUtil.mutListener.listen(917)) {
            ConfigUtils.recreateActivity(this);
        }
    }

    private void ensureMasterKeyWrite() {
        if (!ListenerUtil.mutListener.listen(919)) {
            // Write master key now if no passphrase has been set - don't leave it up to the MainActivity
            if (!ThreemaApplication.getMasterKey().isProtected()) {
                try {
                    if (!ListenerUtil.mutListener.listen(918)) {
                        ThreemaApplication.getMasterKey().setPassphrase(null);
                    }
                } catch (Exception e) {
                    // better die if something went wrong as the master key may not have been saved
                    throw new RuntimeException(e);
                }
            }
        }
    }

    @SuppressLint("StaticFieldLeak")
    private void reallySyncContactsAndFinish() {
        if (!ListenerUtil.mutListener.listen(920)) {
            ensureMasterKeyWrite();
        }
        if (!ListenerUtil.mutListener.listen(937)) {
            if (preferenceService.isSyncContacts()) {
                if (!ListenerUtil.mutListener.listen(936)) {
                    new AsyncTask<Void, Void, Void>() {

                        @Override
                        protected void onPreExecute() {
                            if (!ListenerUtil.mutListener.listen(923)) {
                                fragment5.setContactsSyncInProgress(true, getString(R.string.wizard1_sync_contacts));
                            }
                        }

                        @Override
                        protected Void doInBackground(Void... params) {
                            try {
                                final Account account = userService.getAccount(true);
                                if (!ListenerUtil.mutListener.listen(925)) {
                                    // disable
                                    userService.enableAccountAutoSync(false);
                                }
                                SynchronizeContactsService synchronizeContactsService = serviceManager.getSynchronizeContactsService();
                                SynchronizeContactsRoutine routine = synchronizeContactsService.instantiateSynchronization(account);
                                if (!ListenerUtil.mutListener.listen(928)) {
                                    routine.setOnStatusUpdate(new SynchronizeContactsRoutine.OnStatusUpdate() {

                                        @Override
                                        public void newStatus(final long percent, final String message) {
                                            if (!ListenerUtil.mutListener.listen(926)) {
                                                RuntimeUtil.runOnUiThread(() -> fragment5.setContactsSyncInProgress(true, message));
                                            }
                                        }

                                        @Override
                                        public void error(final Exception x) {
                                            if (!ListenerUtil.mutListener.listen(927)) {
                                                RuntimeUtil.runOnUiThread(() -> fragment5.setContactsSyncInProgress(false, x.getMessage()));
                                            }
                                        }
                                    });
                                }
                                if (!ListenerUtil.mutListener.listen(930)) {
                                    // on finished, close the dialog
                                    routine.addOnFinished(new SynchronizeContactsRoutine.OnFinished() {

                                        @Override
                                        public void finished(boolean success, long modifiedAccounts, List<ContactModel> createdContacts, long deletedAccounts) {
                                            if (!ListenerUtil.mutListener.listen(929)) {
                                                userService.enableAccountAutoSync(true);
                                            }
                                        }
                                    });
                                }
                                if (!ListenerUtil.mutListener.listen(931)) {
                                    routine.run();
                                }
                            } catch (MasterKeyLockedException | FileSystemNotPresentException e) {
                                if (!ListenerUtil.mutListener.listen(924)) {
                                    logger.error("Exception", e);
                                }
                            }
                            return null;
                        }

                        @Override
                        protected void onPostExecute(Void result) {
                            if (!ListenerUtil.mutListener.listen(932)) {
                                startWorkSync();
                            }
                            if (!ListenerUtil.mutListener.listen(933)) {
                                startIdentityStatesSync();
                            }
                            if (!ListenerUtil.mutListener.listen(934)) {
                                finishHandler.removeCallbacks(finishTask);
                            }
                            if (!ListenerUtil.mutListener.listen(935)) {
                                finishHandler.postDelayed(finishTask, FINISH_DELAY);
                            }
                        }
                    }.execute();
                }
            } else {
                if (!ListenerUtil.mutListener.listen(921)) {
                    userService.removeAccount();
                }
                if (!ListenerUtil.mutListener.listen(922)) {
                    prepareThreemaSafe();
                }
            }
        }
    }

    @SuppressLint("StaticFieldLeak")
    private void prepareThreemaSafe() {
        if (!ListenerUtil.mutListener.listen(952)) {
            if (!TestUtil.empty(getSafePassword())) {
                if (!ListenerUtil.mutListener.listen(951)) {
                    new AsyncTask<Void, Void, byte[]>() {

                        @Override
                        protected void onPreExecute() {
                            if (!ListenerUtil.mutListener.listen(942)) {
                                fragment5.setThreemaSafeInProgress(true, getString(R.string.preparing_threema_safe));
                            }
                        }

                        @Override
                        protected byte[] doInBackground(Void... voids) {
                            return threemaSafeService.deriveMasterKey(getSafePassword(), userService.getIdentity());
                        }

                        @Override
                        protected void onPostExecute(byte[] masterkey) {
                            if (!ListenerUtil.mutListener.listen(943)) {
                                fragment5.setThreemaSafeInProgress(false, getString(R.string.menu_done));
                            }
                            if (!ListenerUtil.mutListener.listen(949)) {
                                if (masterkey != null) {
                                    if (!ListenerUtil.mutListener.listen(945)) {
                                        threemaSafeService.storeMasterKey(masterkey);
                                    }
                                    if (!ListenerUtil.mutListener.listen(946)) {
                                        preferenceService.setThreemaSafeServerInfo(safeServerInfo);
                                    }
                                    if (!ListenerUtil.mutListener.listen(947)) {
                                        threemaSafeService.setEnabled(true);
                                    }
                                    if (!ListenerUtil.mutListener.listen(948)) {
                                        threemaSafeService.uploadNow(WizardBaseActivity.this, true);
                                    }
                                } else {
                                    if (!ListenerUtil.mutListener.listen(944)) {
                                        Toast.makeText(WizardBaseActivity.this, R.string.safe_error_preparing, Toast.LENGTH_LONG).show();
                                    }
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(950)) {
                                finishAndRestart();
                            }
                        }
                    }.execute();
                }
            } else {
                if (!ListenerUtil.mutListener.listen(940)) {
                    // do not save mdm settings if backup is forced and no password was set - this will cause a password prompt later
                    if (!((ListenerUtil.mutListener.listen(938) ? (ConfigUtils.isWorkRestricted() || ThreemaSafeMDMConfig.getInstance().isBackupForced()) : (ConfigUtils.isWorkRestricted() && ThreemaSafeMDMConfig.getInstance().isBackupForced())))) {
                        if (!ListenerUtil.mutListener.listen(939)) {
                            threemaSafeService.storeMasterKey(new byte[0]);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(941)) {
                    finishAndRestart();
                }
            }
        }
    }

    private void initSyncAndFinish() {
        if (!ListenerUtil.mutListener.listen(960)) {
            if ((ListenerUtil.mutListener.listen(953) ? (!errorRaised && ConfigUtils.isWorkRestricted()) : (!errorRaised || ConfigUtils.isWorkRestricted()))) {
                if (!ListenerUtil.mutListener.listen(958)) {
                    // set setting flag!
                    preferenceService.setSyncContacts(this.isSyncContacts);
                }
                if (!ListenerUtil.mutListener.listen(959)) {
                    // directly goto sync contacts
                    syncContactsAndFinish();
                }
            } else {
                if (!ListenerUtil.mutListener.listen(954)) {
                    // unlock UI to try again
                    viewPager.lock(false);
                }
                if (!ListenerUtil.mutListener.listen(955)) {
                    prevButton.setVisibility(View.VISIBLE);
                }
                if (!ListenerUtil.mutListener.listen(957)) {
                    if (finishButton != null) {
                        if (!ListenerUtil.mutListener.listen(956)) {
                            finishButton.setEnabled(true);
                        }
                    }
                }
            }
        }
    }

    private void startWorkSync() {
        if (!ListenerUtil.mutListener.listen(962)) {
            if (ConfigUtils.isWorkBuild()) {
                if (!ListenerUtil.mutListener.listen(961)) {
                    WorkSyncService.enqueueWork(this, new Intent(), true);
                }
            }
        }
    }

    private void startIdentityStatesSync() {
        OneTimeWorkRequest workRequest = new OneTimeWorkRequest.Builder(IdentityStatesWorker.class).build();
        if (!ListenerUtil.mutListener.listen(963)) {
            WorkManager.getInstance(this).enqueue(workRequest);
        }
    }

    private void syncContactsAndFinish() {
        if (!ListenerUtil.mutListener.listen(964)) {
            /* trigger a connection now - as application lifecycle was set to resumed state when there was no identity yet */
            serviceManager.getLifetimeService().acquireConnection("Wizard");
        }
        if (!ListenerUtil.mutListener.listen(970)) {
            if (this.isSyncContacts) {
                if (!ListenerUtil.mutListener.listen(969)) {
                    if (ConfigUtils.requestContactPermissions(this, null, PERMISSION_REQUEST_READ_CONTACTS)) {
                        if (!ListenerUtil.mutListener.listen(968)) {
                            reallySyncContactsAndFinish();
                        }
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(965)) {
                    startWorkSync();
                }
                if (!ListenerUtil.mutListener.listen(966)) {
                    startIdentityStatesSync();
                }
                if (!ListenerUtil.mutListener.listen(967)) {
                    prepareThreemaSafe();
                }
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (!ListenerUtil.mutListener.listen(980)) {
            switch(requestCode) {
                case PERMISSION_REQUEST_READ_CONTACTS:
                    if (!ListenerUtil.mutListener.listen(978)) {
                        if ((ListenerUtil.mutListener.listen(976) ? ((ListenerUtil.mutListener.listen(975) ? (grantResults.length >= 0) : (ListenerUtil.mutListener.listen(974) ? (grantResults.length <= 0) : (ListenerUtil.mutListener.listen(973) ? (grantResults.length < 0) : (ListenerUtil.mutListener.listen(972) ? (grantResults.length != 0) : (ListenerUtil.mutListener.listen(971) ? (grantResults.length == 0) : (grantResults.length > 0)))))) || grantResults[0] != PackageManager.PERMISSION_GRANTED) : ((ListenerUtil.mutListener.listen(975) ? (grantResults.length >= 0) : (ListenerUtil.mutListener.listen(974) ? (grantResults.length <= 0) : (ListenerUtil.mutListener.listen(973) ? (grantResults.length < 0) : (ListenerUtil.mutListener.listen(972) ? (grantResults.length != 0) : (ListenerUtil.mutListener.listen(971) ? (grantResults.length == 0) : (grantResults.length > 0)))))) && grantResults[0] != PackageManager.PERMISSION_GRANTED))) {
                            if (!ListenerUtil.mutListener.listen(977)) {
                                this.serviceManager.getPreferenceService().setSyncContacts(false);
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(979)) {
                        reallySyncContactsAndFinish();
                    }
            }
        }
    }
}
