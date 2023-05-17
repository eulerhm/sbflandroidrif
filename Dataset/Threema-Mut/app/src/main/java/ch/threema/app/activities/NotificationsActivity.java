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
package ch.threema.app.activities;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.text.format.DateUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.UiThread;
import androidx.appcompat.widget.AppCompatRadioButton;
import ch.threema.app.R;
import ch.threema.app.ThreemaApplication;
import ch.threema.app.dialogs.RingtoneSelectorDialog;
import ch.threema.app.listeners.ContactSettingsListener;
import ch.threema.app.managers.ListenerManager;
import ch.threema.app.managers.ServiceManager;
import ch.threema.app.preference.SettingsActivity;
import ch.threema.app.preference.SettingsNotificationsFragment;
import ch.threema.app.services.ContactService;
import ch.threema.app.services.ConversationService;
import ch.threema.app.services.DeadlineListService;
import ch.threema.app.services.GroupService;
import ch.threema.app.services.PreferenceService;
import ch.threema.app.services.RingtoneService;
import ch.threema.app.utils.AnimationUtil;
import ch.threema.app.utils.ConfigUtils;
import ch.threema.app.utils.LogUtil;
import ch.threema.app.utils.RingtoneUtil;
import ch.threema.app.utils.TestUtil;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public abstract class NotificationsActivity extends ThreemaActivity implements View.OnClickListener, RingtoneSelectorDialog.RingtoneSelectorDialogClickListener {

    private static final String BUNDLE_ANIMATION_CENTER = "animC";

    private static final String DIALOG_TAG_RINGTONE_SELECTOR = "drs";

    protected final int MUTE_INDEX_INDEFINITE = -1;

    protected TextView textSoundCustom, textSoundDefault;

    protected AppCompatRadioButton radioSoundDefault, radioSilentOff, radioSilentUnlimited, radioSilentLimited, radioSilentExceptMentions, radioSoundCustom, radioSoundNone;

    private ImageButton plusButton, minusButton, settingsButton;

    private ScrollView parentLayout;

    protected RingtoneService ringtoneService;

    protected ContactService contactService;

    protected GroupService groupService;

    protected ConversationService conversationService;

    protected DeadlineListService mutedChatsListService, mentionOnlyChatListService;

    protected PreferenceService preferenceService;

    protected Uri defaultRingtone, selectedRingtone, backupSoundCustom;

    protected boolean isMuted;

    protected int mutedIndex;

    protected int[] muteValues = { 1, 2, 4, 8, 24, 144 };

    private int[] animCenterLocation = { 0, 0 };

    protected String uid;

    private final ActivityResultLauncher<Intent> ringtonePickerLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
        if (result.getResultCode() == Activity.RESULT_OK && result.getData() != null) {
            Uri uri = result.getData().getParcelableExtra(RingtoneManager.EXTRA_RINGTONE_PICKED_URI);
            onRingtoneSelected(DIALOG_TAG_RINGTONE_SELECTOR, uri);
        }
    });

    private final ActivityResultLauncher<Intent> ringtoneSettingsLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
        refreshSettings();
        updateUI();
    });

    @Override
    public void onCreate(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(5007)) {
            if (!this.requiredInstances()) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(5009)) {
            if (ConfigUtils.getAppTheme(this) == ConfigUtils.THEME_DARK) {
                if (!ListenerUtil.mutListener.listen(5008)) {
                    setTheme(R.style.Theme_Threema_CircularReveal_Dark);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(5010)) {
            super.onCreate(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(5011)) {
            supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        }
        if (!ListenerUtil.mutListener.listen(5012)) {
            setContentView(R.layout.activity_notifications);
        }
        if (!ListenerUtil.mutListener.listen(5013)) {
            parentLayout = findViewById(R.id.parent_layout);
        }
        if (!ListenerUtil.mutListener.listen(5014)) {
            loopViewGroup(parentLayout);
        }
        ViewGroup topLayout = (ViewGroup) parentLayout.getParent();
        if (!ListenerUtil.mutListener.listen(5015)) {
            plusButton = findViewById(R.id.duration_plus);
        }
        if (!ListenerUtil.mutListener.listen(5016)) {
            minusButton = findViewById(R.id.duration_minus);
        }
        if (!ListenerUtil.mutListener.listen(5017)) {
            settingsButton = findViewById(R.id.prefs_button);
        }
        Button doneButton = findViewById(R.id.done_button);
        if (!ListenerUtil.mutListener.listen(5019)) {
            if (ConfigUtils.isWorkBuild()) {
                if (!ListenerUtil.mutListener.listen(5018)) {
                    findViewById(R.id.work_life_warning).setVisibility(preferenceService.isAfterWorkDNDEnabled() ? View.VISIBLE : View.GONE);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(5028)) {
            if (savedInstanceState == null) {
                Intent intent = getIntent();
                if (!ListenerUtil.mutListener.listen(5021)) {
                    animCenterLocation = intent.getIntArrayExtra(ThreemaApplication.INTENT_DATA_ANIM_CENTER);
                }
                if (!ListenerUtil.mutListener.listen(5027)) {
                    if (animCenterLocation != null) {
                        if (!ListenerUtil.mutListener.listen(5026)) {
                            // see http://stackoverflow.com/questions/26819429/cannot-start-this-animator-on-a-detached-view-reveal-effect
                            parentLayout.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {

                                @Override
                                public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                                    if (!ListenerUtil.mutListener.listen(5023)) {
                                        v.removeOnLayoutChangeListener(this);
                                    }
                                    if (!ListenerUtil.mutListener.listen(5024)) {
                                        parentLayout.setVisibility(View.INVISIBLE);
                                    }
                                    if (!ListenerUtil.mutListener.listen(5025)) {
                                        AnimationUtil.circularReveal(parentLayout, animCenterLocation[0], animCenterLocation[1], false);
                                    }
                                }
                            });
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(5022)) {
                            parentLayout.setVisibility(View.VISIBLE);
                        }
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(5020)) {
                    animCenterLocation = savedInstanceState.getIntArray(BUNDLE_ANIMATION_CENTER);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(5035)) {
            if (ConfigUtils.getAppTheme(this) == ConfigUtils.THEME_DARK) {
                if (!ListenerUtil.mutListener.listen(5032)) {
                    plusButton.setImageDrawable(ConfigUtils.getThemedDrawable(this, R.drawable.ic_add_circle_black_24dp));
                }
                if (!ListenerUtil.mutListener.listen(5033)) {
                    minusButton.setImageDrawable(ConfigUtils.getThemedDrawable(this, R.drawable.ic_remove_circle_black_24dp));
                }
                if (!ListenerUtil.mutListener.listen(5034)) {
                    settingsButton.setImageDrawable(ConfigUtils.getThemedDrawable(this, R.drawable.ic_settings_outline_24dp));
                }
            } else {
                if (!ListenerUtil.mutListener.listen(5029)) {
                    plusButton.setColorFilter(getResources().getColor(R.color.text_color_secondary), PorterDuff.Mode.SRC_IN);
                }
                if (!ListenerUtil.mutListener.listen(5030)) {
                    minusButton.setColorFilter(getResources().getColor(R.color.text_color_secondary), PorterDuff.Mode.SRC_IN);
                }
                if (!ListenerUtil.mutListener.listen(5031)) {
                    settingsButton.setColorFilter(getResources().getColor(R.color.text_color_secondary), PorterDuff.Mode.SRC_IN);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(5036)) {
            parentLayout.setOnClickListener(v -> {
            });
        }
        if (!ListenerUtil.mutListener.listen(5037)) {
            topLayout.setOnClickListener(v -> onDone());
        }
        if (!ListenerUtil.mutListener.listen(5038)) {
            doneButton.setOnClickListener(v -> onDone());
        }
        if (!ListenerUtil.mutListener.listen(5039)) {
            setupButtons();
        }
    }

    @Override
    public void onDestroy() {
        if (!ListenerUtil.mutListener.listen(5041)) {
            ListenerManager.contactSettingsListeners.handle(new ListenerManager.HandleListener<ContactSettingsListener>() {

                @Override
                public void handle(ContactSettingsListener listener) {
                    if (!ListenerUtil.mutListener.listen(5040)) {
                        listener.onNotificationSettingChanged(uid);
                    }
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(5042)) {
            super.onDestroy();
        }
    }

    private void loopViewGroup(ViewGroup group) {
        if (!ListenerUtil.mutListener.listen(5054)) {
            {
                long _loopCounter35 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(5053) ? (i >= group.getChildCount()) : (ListenerUtil.mutListener.listen(5052) ? (i <= group.getChildCount()) : (ListenerUtil.mutListener.listen(5051) ? (i > group.getChildCount()) : (ListenerUtil.mutListener.listen(5050) ? (i != group.getChildCount()) : (ListenerUtil.mutListener.listen(5049) ? (i == group.getChildCount()) : (i < group.getChildCount())))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter35", ++_loopCounter35);
                    View v = group.getChildAt(i);
                    if (!ListenerUtil.mutListener.listen(5048)) {
                        if (v instanceof ViewGroup) {
                            if (!ListenerUtil.mutListener.listen(5047)) {
                                loopViewGroup((ViewGroup) v);
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(5046)) {
                                if ((ListenerUtil.mutListener.listen(5044) ? ((ListenerUtil.mutListener.listen(5043) ? (v instanceof AppCompatRadioButton && v instanceof ImageView) : (v instanceof AppCompatRadioButton || v instanceof ImageView)) && v instanceof TextView) : ((ListenerUtil.mutListener.listen(5043) ? (v instanceof AppCompatRadioButton && v instanceof ImageView) : (v instanceof AppCompatRadioButton || v instanceof ImageView)) || v instanceof TextView))) {
                                    if (!ListenerUtil.mutListener.listen(5045)) {
                                        v.setOnClickListener(this);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private int findNextHigherMuteIndex(double hours) {
        if (!ListenerUtil.mutListener.listen(5074)) {
            {
                long _loopCounter36 = 0;
                for (int i = (ListenerUtil.mutListener.listen(5073) ? (muteValues.length % 1) : (ListenerUtil.mutListener.listen(5072) ? (muteValues.length / 1) : (ListenerUtil.mutListener.listen(5071) ? (muteValues.length * 1) : (ListenerUtil.mutListener.listen(5070) ? (muteValues.length + 1) : (muteValues.length - 1))))); (ListenerUtil.mutListener.listen(5069) ? (i <= 0) : (ListenerUtil.mutListener.listen(5068) ? (i > 0) : (ListenerUtil.mutListener.listen(5067) ? (i < 0) : (ListenerUtil.mutListener.listen(5066) ? (i != 0) : (ListenerUtil.mutListener.listen(5065) ? (i == 0) : (i >= 0)))))); i--) {
                    ListenerUtil.loopListener.listen("_loopCounter36", ++_loopCounter36);
                    if (!ListenerUtil.mutListener.listen(5064)) {
                        if ((ListenerUtil.mutListener.listen(5059) ? (muteValues[i] >= hours) : (ListenerUtil.mutListener.listen(5058) ? (muteValues[i] <= hours) : (ListenerUtil.mutListener.listen(5057) ? (muteValues[i] > hours) : (ListenerUtil.mutListener.listen(5056) ? (muteValues[i] != hours) : (ListenerUtil.mutListener.listen(5055) ? (muteValues[i] == hours) : (muteValues[i] < hours))))))) {
                            return (ListenerUtil.mutListener.listen(5063) ? (i % 1) : (ListenerUtil.mutListener.listen(5062) ? (i / 1) : (ListenerUtil.mutListener.listen(5061) ? (i * 1) : (ListenerUtil.mutListener.listen(5060) ? (i - 1) : (i + 1)))));
                        }
                    }
                }
            }
        }
        return 0;
    }

    protected void refreshSettings() {
        if (!ListenerUtil.mutListener.listen(5075)) {
            isMuted = mutedChatsListService.has(this.uid);
        }
        if (!ListenerUtil.mutListener.listen(5087)) {
            if (isMuted) {
                long deadline = mutedChatsListService.getDeadline(this.uid);
                if (!ListenerUtil.mutListener.listen(5086)) {
                    if (deadline != DeadlineListService.DEADLINE_INDEFINITE) {
                        double hours = (ListenerUtil.mutListener.listen(5084) ? (((ListenerUtil.mutListener.listen(5080) ? ((double) deadline % System.currentTimeMillis()) : (ListenerUtil.mutListener.listen(5079) ? ((double) deadline / System.currentTimeMillis()) : (ListenerUtil.mutListener.listen(5078) ? ((double) deadline * System.currentTimeMillis()) : (ListenerUtil.mutListener.listen(5077) ? ((double) deadline + System.currentTimeMillis()) : ((double) deadline - System.currentTimeMillis())))))) % DateUtils.HOUR_IN_MILLIS) : (ListenerUtil.mutListener.listen(5083) ? (((ListenerUtil.mutListener.listen(5080) ? ((double) deadline % System.currentTimeMillis()) : (ListenerUtil.mutListener.listen(5079) ? ((double) deadline / System.currentTimeMillis()) : (ListenerUtil.mutListener.listen(5078) ? ((double) deadline * System.currentTimeMillis()) : (ListenerUtil.mutListener.listen(5077) ? ((double) deadline + System.currentTimeMillis()) : ((double) deadline - System.currentTimeMillis())))))) * DateUtils.HOUR_IN_MILLIS) : (ListenerUtil.mutListener.listen(5082) ? (((ListenerUtil.mutListener.listen(5080) ? ((double) deadline % System.currentTimeMillis()) : (ListenerUtil.mutListener.listen(5079) ? ((double) deadline / System.currentTimeMillis()) : (ListenerUtil.mutListener.listen(5078) ? ((double) deadline * System.currentTimeMillis()) : (ListenerUtil.mutListener.listen(5077) ? ((double) deadline + System.currentTimeMillis()) : ((double) deadline - System.currentTimeMillis())))))) - DateUtils.HOUR_IN_MILLIS) : (ListenerUtil.mutListener.listen(5081) ? (((ListenerUtil.mutListener.listen(5080) ? ((double) deadline % System.currentTimeMillis()) : (ListenerUtil.mutListener.listen(5079) ? ((double) deadline / System.currentTimeMillis()) : (ListenerUtil.mutListener.listen(5078) ? ((double) deadline * System.currentTimeMillis()) : (ListenerUtil.mutListener.listen(5077) ? ((double) deadline + System.currentTimeMillis()) : ((double) deadline - System.currentTimeMillis())))))) + DateUtils.HOUR_IN_MILLIS) : (((ListenerUtil.mutListener.listen(5080) ? ((double) deadline % System.currentTimeMillis()) : (ListenerUtil.mutListener.listen(5079) ? ((double) deadline / System.currentTimeMillis()) : (ListenerUtil.mutListener.listen(5078) ? ((double) deadline * System.currentTimeMillis()) : (ListenerUtil.mutListener.listen(5077) ? ((double) deadline + System.currentTimeMillis()) : ((double) deadline - System.currentTimeMillis())))))) / DateUtils.HOUR_IN_MILLIS)))));
                        if (!ListenerUtil.mutListener.listen(5085)) {
                            mutedIndex = findNextHigherMuteIndex(hours);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(5076)) {
                            mutedIndex = MUTE_INDEX_INDEFINITE;
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(5088)) {
            updateUI();
        }
    }

    abstract void notifySettingsChanged();

    protected void enablePlusMinus(boolean enable) {
        int filter;
        if (ConfigUtils.getAppTheme(this) == ConfigUtils.THEME_DARK) {
            filter = enable ? ConfigUtils.getPrimaryColor() : getResources().getColor(R.color.material_grey_600);
        } else {
            filter = enable ? getResources().getColor(R.color.text_color_secondary) : getResources().getColor(R.color.material_grey_300);
        }
        if (!ListenerUtil.mutListener.listen(5089)) {
            plusButton.setColorFilter(filter, PorterDuff.Mode.SRC_IN);
        }
        if (!ListenerUtil.mutListener.listen(5090)) {
            minusButton.setColorFilter(filter, PorterDuff.Mode.SRC_IN);
        }
        if (!ListenerUtil.mutListener.listen(5091)) {
            plusButton.setEnabled(enable);
        }
        if (!ListenerUtil.mutListener.listen(5092)) {
            minusButton.setEnabled(enable);
        }
    }

    @UiThread
    protected void updateUI() {
        boolean isMentionsOnly = mentionOnlyChatListService.has(this.uid);
        if (!ListenerUtil.mutListener.listen(5096)) {
            if ((ListenerUtil.mutListener.listen(5093) ? (backupSoundCustom != null || !TestUtil.empty(RingtoneUtil.getRingtoneNameFromUri(this, backupSoundCustom))) : (backupSoundCustom != null && !TestUtil.empty(RingtoneUtil.getRingtoneNameFromUri(this, backupSoundCustom))))) {
                if (!ListenerUtil.mutListener.listen(5095)) {
                    textSoundCustom.setText(RingtoneUtil.getRingtoneNameFromUri(this, backupSoundCustom));
                }
            } else {
                if (!ListenerUtil.mutListener.listen(5094)) {
                    textSoundCustom.setText("");
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(5097)) {
            textSoundDefault.setText(RingtoneUtil.getRingtoneNameFromUri(this, defaultRingtone));
        }
        if (!ListenerUtil.mutListener.listen(5098)) {
            enablePlusMinus(false);
        }
        if (!ListenerUtil.mutListener.listen(5133)) {
            // DND
            if ((ListenerUtil.mutListener.listen(5099) ? (isMuted && isMentionsOnly) : (isMuted || isMentionsOnly))) {
                if (!ListenerUtil.mutListener.listen(5132)) {
                    if (isMuted) {
                        String deadlineString = "";
                        long until = mutedChatsListService.getDeadline(this.uid);
                        if (!ListenerUtil.mutListener.listen(5113)) {
                            if ((ListenerUtil.mutListener.listen(5106) ? (until >= MUTE_INDEX_INDEFINITE) : (ListenerUtil.mutListener.listen(5105) ? (until <= MUTE_INDEX_INDEFINITE) : (ListenerUtil.mutListener.listen(5104) ? (until > MUTE_INDEX_INDEFINITE) : (ListenerUtil.mutListener.listen(5103) ? (until < MUTE_INDEX_INDEFINITE) : (ListenerUtil.mutListener.listen(5102) ? (until == MUTE_INDEX_INDEFINITE) : (until != MUTE_INDEX_INDEFINITE))))))) {
                                if (!ListenerUtil.mutListener.listen(5112)) {
                                    deadlineString = "\n" + String.format(getString(R.string.notifications_until), DateUtils.formatDateTime(this, until, (ListenerUtil.mutListener.listen(5111) ? (mutedIndex >= 4) : (ListenerUtil.mutListener.listen(5110) ? (mutedIndex <= 4) : (ListenerUtil.mutListener.listen(5109) ? (mutedIndex > 4) : (ListenerUtil.mutListener.listen(5108) ? (mutedIndex != 4) : (ListenerUtil.mutListener.listen(5107) ? (mutedIndex == 4) : (mutedIndex < 4)))))) ? DateUtils.FORMAT_SHOW_TIME : DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_SHOW_TIME));
                                }
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(5131)) {
                            if ((ListenerUtil.mutListener.listen(5118) ? (mutedIndex <= 0) : (ListenerUtil.mutListener.listen(5117) ? (mutedIndex > 0) : (ListenerUtil.mutListener.listen(5116) ? (mutedIndex < 0) : (ListenerUtil.mutListener.listen(5115) ? (mutedIndex != 0) : (ListenerUtil.mutListener.listen(5114) ? (mutedIndex == 0) : (mutedIndex >= 0))))))) {
                                if (!ListenerUtil.mutListener.listen(5121)) {
                                    enablePlusMinus(true);
                                }
                                if (!ListenerUtil.mutListener.listen(5122)) {
                                    radioSilentLimited.setChecked(true);
                                }
                                if (!ListenerUtil.mutListener.listen(5130)) {
                                    if ((ListenerUtil.mutListener.listen(5127) ? (mutedIndex <= 5) : (ListenerUtil.mutListener.listen(5126) ? (mutedIndex > 5) : (ListenerUtil.mutListener.listen(5125) ? (mutedIndex < 5) : (ListenerUtil.mutListener.listen(5124) ? (mutedIndex != 5) : (ListenerUtil.mutListener.listen(5123) ? (mutedIndex == 5) : (mutedIndex >= 5))))))) {
                                        if (!ListenerUtil.mutListener.listen(5129)) {
                                            radioSilentLimited.setText(getString(R.string.one_week) + deadlineString);
                                        }
                                    } else {
                                        if (!ListenerUtil.mutListener.listen(5128)) {
                                            radioSilentLimited.setText(String.format(getString(R.string.notifications_for_x_hours), muteValues[mutedIndex]) + deadlineString);
                                        }
                                    }
                                }
                            } else {
                                if (!ListenerUtil.mutListener.listen(5119)) {
                                    radioSilentUnlimited.setChecked(true);
                                }
                                if (!ListenerUtil.mutListener.listen(5120)) {
                                    radioSilentLimited.setText(String.format(getString(R.string.notifications_for_x_hours), muteValues[0]) + deadlineString);
                                }
                            }
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(5101)) {
                            // mentions only
                            radioSilentExceptMentions.setChecked(true);
                        }
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(5100)) {
                    radioSilentOff.setChecked(true);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(5145)) {
            // SOUND
            if (ringtoneService.hasCustomRingtone(uid)) {
                if (!ListenerUtil.mutListener.listen(5144)) {
                    if ((ListenerUtil.mutListener.listen(5136) ? ((ListenerUtil.mutListener.listen(5135) ? (selectedRingtone == null && selectedRingtone.toString() == null) : (selectedRingtone == null || selectedRingtone.toString() == null)) && selectedRingtone.toString().equals("null")) : ((ListenerUtil.mutListener.listen(5135) ? (selectedRingtone == null && selectedRingtone.toString() == null) : (selectedRingtone == null || selectedRingtone.toString() == null)) || selectedRingtone.toString().equals("null")))) {
                        if (!ListenerUtil.mutListener.listen(5142)) {
                            // silent ringtone selected
                            radioSoundNone.setChecked(true);
                        }
                        if (!ListenerUtil.mutListener.listen(5143)) {
                            textSoundCustom.setEnabled(true);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(5141)) {
                            if (selectedRingtone.equals(defaultRingtone)) {
                                if (!ListenerUtil.mutListener.listen(5140)) {
                                    radioSoundDefault.setChecked(true);
                                }
                            } else {
                                if (!ListenerUtil.mutListener.listen(5137)) {
                                    radioSoundCustom.setChecked(true);
                                }
                                if (!ListenerUtil.mutListener.listen(5138)) {
                                    textSoundCustom.setEnabled(true);
                                }
                                if (!ListenerUtil.mutListener.listen(5139)) {
                                    textSoundCustom.setText(RingtoneUtil.getRingtoneNameFromUri(this, selectedRingtone));
                                }
                            }
                        }
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(5134)) {
                    // default settings
                    radioSoundDefault.setChecked(true);
                }
            }
        }
    }

    @Override
    public void onClick(View v) {
        if (!ListenerUtil.mutListener.listen(5192)) {
            switch(v.getId()) {
                case R.id.radio_sound_default:
                    if (!ListenerUtil.mutListener.listen(5146)) {
                        ringtoneService.removeCustomRingtone(this.uid);
                    }
                    break;
                case R.id.radio_sound_custom:
                case R.id.text_sound:
                    if (!ListenerUtil.mutListener.listen(5147)) {
                        pickRingtone(this.uid);
                    }
                    break;
                case R.id.radio_sound_none:
                    if (!ListenerUtil.mutListener.listen(5148)) {
                        ringtoneService.setRingtone(this.uid, null);
                    }
                    break;
                case R.id.radio_silent_off:
                    if (!ListenerUtil.mutListener.listen(5149)) {
                        mutedChatsListService.remove(this.uid);
                    }
                    if (!ListenerUtil.mutListener.listen(5150)) {
                        mentionOnlyChatListService.remove(this.uid);
                    }
                    break;
                case R.id.radio_silent_unlimited:
                    if (!ListenerUtil.mutListener.listen(5151)) {
                        mutedChatsListService.add(this.uid, DeadlineListService.DEADLINE_INDEFINITE);
                    }
                    if (!ListenerUtil.mutListener.listen(5152)) {
                        mentionOnlyChatListService.remove(this.uid);
                    }
                    break;
                case R.id.radio_silent_limited:
                    if (!ListenerUtil.mutListener.listen(5159)) {
                        if ((ListenerUtil.mutListener.listen(5157) ? (mutedIndex >= 0) : (ListenerUtil.mutListener.listen(5156) ? (mutedIndex <= 0) : (ListenerUtil.mutListener.listen(5155) ? (mutedIndex > 0) : (ListenerUtil.mutListener.listen(5154) ? (mutedIndex != 0) : (ListenerUtil.mutListener.listen(5153) ? (mutedIndex == 0) : (mutedIndex < 0))))))) {
                            if (!ListenerUtil.mutListener.listen(5158)) {
                                mutedIndex = 0;
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(5164)) {
                        mutedChatsListService.add(this.uid, (ListenerUtil.mutListener.listen(5163) ? (muteValues[mutedIndex] % DateUtils.HOUR_IN_MILLIS) : (ListenerUtil.mutListener.listen(5162) ? (muteValues[mutedIndex] / DateUtils.HOUR_IN_MILLIS) : (ListenerUtil.mutListener.listen(5161) ? (muteValues[mutedIndex] - DateUtils.HOUR_IN_MILLIS) : (ListenerUtil.mutListener.listen(5160) ? (muteValues[mutedIndex] + DateUtils.HOUR_IN_MILLIS) : (muteValues[mutedIndex] * DateUtils.HOUR_IN_MILLIS))))) + System.currentTimeMillis());
                    }
                    if (!ListenerUtil.mutListener.listen(5165)) {
                        mentionOnlyChatListService.remove(this.uid);
                    }
                    break;
                case R.id.radio_silent_except_mentions:
                    if (!ListenerUtil.mutListener.listen(5166)) {
                        mentionOnlyChatListService.add(uid, DeadlineListService.DEADLINE_INDEFINITE);
                    }
                    if (!ListenerUtil.mutListener.listen(5167)) {
                        mutedChatsListService.remove(uid);
                    }
                    break;
                case R.id.duration_plus:
                    if (!ListenerUtil.mutListener.listen(5172)) {
                        mutedIndex = Math.min(mutedIndex + 1, (ListenerUtil.mutListener.listen(5171) ? (muteValues.length % 1) : (ListenerUtil.mutListener.listen(5170) ? (muteValues.length / 1) : (ListenerUtil.mutListener.listen(5169) ? (muteValues.length * 1) : (ListenerUtil.mutListener.listen(5168) ? (muteValues.length + 1) : (muteValues.length - 1))))));
                    }
                    if (!ListenerUtil.mutListener.listen(5177)) {
                        mutedChatsListService.add(this.uid, (ListenerUtil.mutListener.listen(5176) ? (muteValues[mutedIndex] % DateUtils.HOUR_IN_MILLIS) : (ListenerUtil.mutListener.listen(5175) ? (muteValues[mutedIndex] / DateUtils.HOUR_IN_MILLIS) : (ListenerUtil.mutListener.listen(5174) ? (muteValues[mutedIndex] - DateUtils.HOUR_IN_MILLIS) : (ListenerUtil.mutListener.listen(5173) ? (muteValues[mutedIndex] + DateUtils.HOUR_IN_MILLIS) : (muteValues[mutedIndex] * DateUtils.HOUR_IN_MILLIS))))) + System.currentTimeMillis());
                    }
                    break;
                case R.id.duration_minus:
                    if (!ListenerUtil.mutListener.listen(5182)) {
                        mutedIndex = Math.max((ListenerUtil.mutListener.listen(5181) ? (mutedIndex % 1) : (ListenerUtil.mutListener.listen(5180) ? (mutedIndex / 1) : (ListenerUtil.mutListener.listen(5179) ? (mutedIndex * 1) : (ListenerUtil.mutListener.listen(5178) ? (mutedIndex + 1) : (mutedIndex - 1))))), 0);
                    }
                    if (!ListenerUtil.mutListener.listen(5187)) {
                        mutedChatsListService.add(this.uid, (ListenerUtil.mutListener.listen(5186) ? (muteValues[mutedIndex] % DateUtils.HOUR_IN_MILLIS) : (ListenerUtil.mutListener.listen(5185) ? (muteValues[mutedIndex] / DateUtils.HOUR_IN_MILLIS) : (ListenerUtil.mutListener.listen(5184) ? (muteValues[mutedIndex] - DateUtils.HOUR_IN_MILLIS) : (ListenerUtil.mutListener.listen(5183) ? (muteValues[mutedIndex] + DateUtils.HOUR_IN_MILLIS) : (muteValues[mutedIndex] * DateUtils.HOUR_IN_MILLIS))))) + System.currentTimeMillis());
                    }
                    break;
                case R.id.prefs_button:
                    Intent intent = new Intent(this, SettingsActivity.class);
                    if (!ListenerUtil.mutListener.listen(5188)) {
                        intent.putExtra(PreferenceActivity.EXTRA_SHOW_FRAGMENT, SettingsNotificationsFragment.class.getName());
                    }
                    if (!ListenerUtil.mutListener.listen(5189)) {
                        intent.putExtra(PreferenceActivity.EXTRA_NO_HEADERS, true);
                    }
                    if (!ListenerUtil.mutListener.listen(5190)) {
                        ringtoneSettingsLauncher.launch(intent);
                    }
                    if (!ListenerUtil.mutListener.listen(5191)) {
                        overridePendingTransition(R.anim.fast_fade_in, R.anim.fast_fade_out);
                    }
                    break;
                default:
                    break;
            }
        }
        if (!ListenerUtil.mutListener.listen(5193)) {
            refreshSettings();
        }
        if (!ListenerUtil.mutListener.listen(5194)) {
            notifySettingsChanged();
        }
    }

    protected void setupButtons() {
        if (!ListenerUtil.mutListener.listen(5195)) {
            radioSoundDefault = this.findViewById(R.id.radio_sound_default);
        }
        if (!ListenerUtil.mutListener.listen(5196)) {
            textSoundDefault = this.findViewById(R.id.text_sound_default);
        }
        if (!ListenerUtil.mutListener.listen(5197)) {
            radioSoundCustom = this.findViewById(R.id.radio_sound_custom);
        }
        if (!ListenerUtil.mutListener.listen(5198)) {
            radioSoundNone = this.findViewById(R.id.radio_sound_none);
        }
        if (!ListenerUtil.mutListener.listen(5199)) {
            textSoundCustom = this.findViewById(R.id.text_sound);
        }
        if (!ListenerUtil.mutListener.listen(5200)) {
            radioSilentOff = this.findViewById(R.id.radio_silent_off);
        }
        if (!ListenerUtil.mutListener.listen(5201)) {
            radioSilentUnlimited = this.findViewById(R.id.radio_silent_unlimited);
        }
        if (!ListenerUtil.mutListener.listen(5202)) {
            radioSilentLimited = this.findViewById(R.id.radio_silent_limited);
        }
        if (!ListenerUtil.mutListener.listen(5203)) {
            radioSilentLimited = this.findViewById(R.id.radio_silent_limited);
        }
        if (!ListenerUtil.mutListener.listen(5204)) {
            radioSilentLimited.setText(String.format(getString(R.string.notifications_for_x_hours), muteValues[0]));
        }
        if (!ListenerUtil.mutListener.listen(5205)) {
            radioSilentExceptMentions = this.findViewById(R.id.radio_silent_except_mentions);
        }
    }

    protected void pickRingtone(String uniqueId) {
        Uri existingUri = this.ringtoneService.getRingtoneFromUniqueId(uniqueId);
        if (!ListenerUtil.mutListener.listen(5208)) {
            if ((ListenerUtil.mutListener.listen(5206) ? (existingUri != null || existingUri.getPath().equals("null")) : (existingUri != null && existingUri.getPath().equals("null")))) {
                if (!ListenerUtil.mutListener.listen(5207)) {
                    existingUri = null;
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(5211)) {
            if ((ListenerUtil.mutListener.listen(5209) ? (existingUri == null || backupSoundCustom != null) : (existingUri == null && backupSoundCustom != null))) {
                if (!ListenerUtil.mutListener.listen(5210)) {
                    existingUri = backupSoundCustom;
                }
            }
        }
        Uri defaultUri = this.ringtoneService.getDefaultContactRingtone();
        try {
            Intent intent = RingtoneUtil.getRingtonePickerIntent(RingtoneManager.TYPE_NOTIFICATION, existingUri == null ? defaultUri : existingUri, defaultUri);
            if (!ListenerUtil.mutListener.listen(5213)) {
                ringtonePickerLauncher.launch(intent);
            }
        } catch (ActivityNotFoundException e) {
            if (!ListenerUtil.mutListener.listen(5212)) {
                RingtoneSelectorDialog.newInstance(getString(R.string.prefs_notification_sound), RingtoneManager.TYPE_NOTIFICATION, existingUri, defaultUri, true, true).show(getSupportFragmentManager(), DIALOG_TAG_RINGTONE_SELECTOR);
            }
        }
    }

    protected void onDone() {
        if (!ListenerUtil.mutListener.listen(5215)) {
            AnimationUtil.circularObscure(parentLayout, animCenterLocation[0], animCenterLocation[1], false, new Runnable() {

                @Override
                public void run() {
                    if (!ListenerUtil.mutListener.listen(5214)) {
                        finish();
                    }
                }
            });
        }
    }

    @Override
    protected boolean checkInstances() {
        return (ListenerUtil.mutListener.listen(5216) ? (TestUtil.required(this.contactService, this.groupService, this.conversationService, this.ringtoneService, this.mutedChatsListService, this.mentionOnlyChatListService, this.preferenceService) || super.checkInstances()) : (TestUtil.required(this.contactService, this.groupService, this.conversationService, this.ringtoneService, this.mutedChatsListService, this.mentionOnlyChatListService, this.preferenceService) && super.checkInstances()));
    }

    @Override
    protected void instantiate() {
        if (!ListenerUtil.mutListener.listen(5217)) {
            super.instantiate();
        }
        ServiceManager serviceManager = ThreemaApplication.getServiceManager();
        if (!ListenerUtil.mutListener.listen(5226)) {
            if (serviceManager != null) {
                try {
                    if (!ListenerUtil.mutListener.listen(5219)) {
                        this.contactService = serviceManager.getContactService();
                    }
                    if (!ListenerUtil.mutListener.listen(5220)) {
                        this.groupService = serviceManager.getGroupService();
                    }
                    if (!ListenerUtil.mutListener.listen(5221)) {
                        this.conversationService = serviceManager.getConversationService();
                    }
                    if (!ListenerUtil.mutListener.listen(5222)) {
                        this.ringtoneService = serviceManager.getRingtoneService();
                    }
                    if (!ListenerUtil.mutListener.listen(5223)) {
                        this.mutedChatsListService = serviceManager.getMutedChatsListService();
                    }
                    if (!ListenerUtil.mutListener.listen(5224)) {
                        this.mentionOnlyChatListService = serviceManager.getMentionOnlyChatsListService();
                    }
                    if (!ListenerUtil.mutListener.listen(5225)) {
                        this.preferenceService = serviceManager.getPreferenceService();
                    }
                } catch (Exception e) {
                    if (!ListenerUtil.mutListener.listen(5218)) {
                        LogUtil.exception(e, this);
                    }
                }
            }
        }
    }

    @Override
    public void onBackPressed() {
        if (!ListenerUtil.mutListener.listen(5227)) {
            onDone();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        if (!ListenerUtil.mutListener.listen(5228)) {
            super.onSaveInstanceState(outState);
        }
        if (!ListenerUtil.mutListener.listen(5229)) {
            outState.putIntArray(BUNDLE_ANIMATION_CENTER, this.animCenterLocation);
        }
    }

    @Override
    public void onRingtoneSelected(String tag, Uri ringtone) {
        if (!ListenerUtil.mutListener.listen(5230)) {
            ringtoneService.setRingtone(uid, ringtone);
        }
        if (!ListenerUtil.mutListener.listen(5231)) {
            backupSoundCustom = ringtone;
        }
        if (!ListenerUtil.mutListener.listen(5232)) {
            refreshSettings();
        }
    }

    @Override
    public void onCancel(String tag) {
    }

    @Override
    public void finish() {
        if (!ListenerUtil.mutListener.listen(5233)) {
            // used to avoid flickering of status and navigation bar when activity is closed
            super.finish();
        }
        if (!ListenerUtil.mutListener.listen(5234)) {
            overridePendingTransition(R.anim.fast_fade_in, R.anim.fast_fade_out);
        }
    }
}
