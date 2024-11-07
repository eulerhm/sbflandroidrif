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
package ch.threema.app.preference;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import androidx.annotation.Nullable;
import androidx.preference.CheckBoxPreference;
import androidx.preference.DropDownPreference;
import androidx.preference.Preference;
import ch.threema.app.R;
import ch.threema.app.ThreemaApplication;
import ch.threema.app.dialogs.GenericAlertDialog;
import ch.threema.app.exceptions.FileSystemNotPresentException;
import ch.threema.app.listeners.ContactSettingsListener;
import ch.threema.app.managers.ListenerManager;
import ch.threema.app.managers.ServiceManager;
import ch.threema.app.services.FileService;
import ch.threema.app.services.PreferenceService;
import ch.threema.app.services.WallpaperService;
import ch.threema.app.utils.AppRestrictionUtil;
import ch.threema.app.utils.ConfigUtils;
import ch.threema.app.utils.StateBitmapUtil;
import ch.threema.app.utils.TestUtil;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class SettingsAppearanceFragment extends ThreemaPreferenceFragment implements GenericAlertDialog.DialogClickListener {

    private static final Logger logger = LoggerFactory.getLogger(SettingsAppearanceFragment.class);

    private int oldTheme;

    private SharedPreferences sharedPreferences;

    private WallpaperService wallpaperService;

    private FileService fileService;

    private CheckBoxPreference showBadge;

    private boolean showBadgeChecked = false;

    @Override
    public void onCreatePreferencesFix(@Nullable Bundle savedInstanceState, String rootKey) {
        if (!ListenerUtil.mutListener.listen(32206)) {
            sharedPreferences = getPreferenceManager().getSharedPreferences();
        }
        if (!ListenerUtil.mutListener.listen(32207)) {
            if (!requiredInstances()) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(32208)) {
            addPreferencesFromResource(R.xml.preference_appearance);
        }
        if (!ListenerUtil.mutListener.listen(32209)) {
            this.showBadge = (CheckBoxPreference) findPreference(getResources().getString(R.string.preferences__show_unread_badge));
        }
        if (!ListenerUtil.mutListener.listen(32210)) {
            this.showBadgeChecked = this.showBadge.isChecked();
        }
        CheckBoxPreference defaultColoredAvatar = (CheckBoxPreference) findPreference(getResources().getString(R.string.preferences__default_contact_picture_colored));
        if (!ListenerUtil.mutListener.listen(32215)) {
            if (defaultColoredAvatar != null) {
                if (!ListenerUtil.mutListener.listen(32214)) {
                    defaultColoredAvatar.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {

                        public boolean onPreferenceChange(Preference preference, Object newValue) {
                            boolean newCheckedValue = newValue.equals(true);
                            if (!ListenerUtil.mutListener.listen(32213)) {
                                if (((CheckBoxPreference) preference).isChecked() != newCheckedValue) {
                                    if (!ListenerUtil.mutListener.listen(32212)) {
                                        ListenerManager.contactSettingsListeners.handle(new ListenerManager.HandleListener<ContactSettingsListener>() {

                                            @Override
                                            public void handle(ContactSettingsListener listener) {
                                                if (!ListenerUtil.mutListener.listen(32211)) {
                                                    listener.onAvatarSettingChanged();
                                                }
                                            }
                                        });
                                    }
                                }
                            }
                            return true;
                        }
                    });
                }
            }
        }
        CheckBoxPreference showProfilePics = (CheckBoxPreference) findPreference(getResources().getString(R.string.preferences__receive_profilepics));
        if (!ListenerUtil.mutListener.listen(32220)) {
            if (showProfilePics != null) {
                if (!ListenerUtil.mutListener.listen(32219)) {
                    showProfilePics.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {

                        public boolean onPreferenceChange(Preference preference, Object newValue) {
                            boolean newCheckedValue = newValue.equals(true);
                            if (!ListenerUtil.mutListener.listen(32218)) {
                                if (((CheckBoxPreference) preference).isChecked() != newCheckedValue) {
                                    if (!ListenerUtil.mutListener.listen(32217)) {
                                        ListenerManager.contactSettingsListeners.handle(new ListenerManager.HandleListener<ContactSettingsListener>() {

                                            @Override
                                            public void handle(ContactSettingsListener listener) {
                                                if (!ListenerUtil.mutListener.listen(32216)) {
                                                    listener.onAvatarSettingChanged();
                                                }
                                            }
                                        });
                                    }
                                }
                            }
                            return true;
                        }
                    });
                }
            }
        }
        CheckBoxPreference biggerSingleEmojis = (CheckBoxPreference) findPreference(getResources().getString(R.string.preferences__bigger_single_emojis));
        if (!ListenerUtil.mutListener.listen(32223)) {
            if (biggerSingleEmojis != null) {
                if (!ListenerUtil.mutListener.listen(32222)) {
                    biggerSingleEmojis.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {

                        @Override
                        public boolean onPreferenceChange(Preference preference, Object newValue) {
                            if (!ListenerUtil.mutListener.listen(32221)) {
                                ConfigUtils.setBiggerSingleEmojis(newValue.equals(true));
                            }
                            return true;
                        }
                    });
                }
            }
        }
        DropDownPreference themePreference = (DropDownPreference) findPreference(getResources().getString(R.string.preferences__theme));
        int themeIndex = Integer.parseInt(sharedPreferences.getString(getResources().getString(R.string.preferences__theme), "0"));
        final String[] themeArray = getResources().getStringArray(R.array.list_theme);
        if (!ListenerUtil.mutListener.listen(32230)) {
            if ((ListenerUtil.mutListener.listen(32228) ? (themeIndex <= themeArray.length) : (ListenerUtil.mutListener.listen(32227) ? (themeIndex > themeArray.length) : (ListenerUtil.mutListener.listen(32226) ? (themeIndex < themeArray.length) : (ListenerUtil.mutListener.listen(32225) ? (themeIndex != themeArray.length) : (ListenerUtil.mutListener.listen(32224) ? (themeIndex == themeArray.length) : (themeIndex >= themeArray.length))))))) {
                if (!ListenerUtil.mutListener.listen(32229)) {
                    themeIndex = 0;
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(32231)) {
            oldTheme = themeIndex;
        }
        if (!ListenerUtil.mutListener.listen(32232)) {
            themePreference.setSummary(themeArray[themeIndex]);
        }
        if (!ListenerUtil.mutListener.listen(32240)) {
            themePreference.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {

                public boolean onPreferenceChange(Preference preference, Object newValue) {
                    int newTheme = Integer.parseInt(newValue.toString());
                    if (!ListenerUtil.mutListener.listen(32239)) {
                        if (newTheme != oldTheme) {
                            if (!ListenerUtil.mutListener.listen(32233)) {
                                ConfigUtils.setAppTheme(newTheme);
                            }
                            if (!ListenerUtil.mutListener.listen(32234)) {
                                StateBitmapUtil.init(ThreemaApplication.getAppContext());
                            }
                            if (!ListenerUtil.mutListener.listen(32235)) {
                                preference.setSummary(themeArray[newTheme]);
                            }
                            if (!ListenerUtil.mutListener.listen(32237)) {
                                ListenerManager.contactSettingsListeners.handle(new ListenerManager.HandleListener<ContactSettingsListener>() {

                                    @Override
                                    public void handle(ContactSettingsListener listener) {
                                        if (!ListenerUtil.mutListener.listen(32236)) {
                                            listener.onAvatarSettingChanged();
                                        }
                                    }
                                });
                            }
                            if (!ListenerUtil.mutListener.listen(32238)) {
                                ConfigUtils.recreateActivity(getActivity());
                            }
                        }
                    }
                    return true;
                }
            });
        }
        DropDownPreference emojiPreference = (DropDownPreference) findPreference(getResources().getString(R.string.preferences__emoji_style));
        int emojiIndex = Integer.parseInt(sharedPreferences.getString(getResources().getString(R.string.preferences__emoji_style), "0"));
        String[] emojiArray = getResources().getStringArray(R.array.list_emoji_style);
        if (!ListenerUtil.mutListener.listen(32247)) {
            if ((ListenerUtil.mutListener.listen(32245) ? (emojiIndex <= emojiArray.length) : (ListenerUtil.mutListener.listen(32244) ? (emojiIndex > emojiArray.length) : (ListenerUtil.mutListener.listen(32243) ? (emojiIndex < emojiArray.length) : (ListenerUtil.mutListener.listen(32242) ? (emojiIndex != emojiArray.length) : (ListenerUtil.mutListener.listen(32241) ? (emojiIndex == emojiArray.length) : (emojiIndex >= emojiArray.length))))))) {
                if (!ListenerUtil.mutListener.listen(32246)) {
                    emojiIndex = 0;
                }
            }
        }
        final int oldEmojiStyle = emojiIndex;
        if (!ListenerUtil.mutListener.listen(32248)) {
            emojiPreference.setSummary(emojiArray[emojiIndex]);
        }
        if (!ListenerUtil.mutListener.listen(32257)) {
            emojiPreference.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {

                public boolean onPreferenceChange(Preference preference, Object newValue) {
                    int newEmojiStyle = Integer.parseInt(newValue.toString());
                    if (!ListenerUtil.mutListener.listen(32256)) {
                        if (newEmojiStyle != oldEmojiStyle) {
                            if (!ListenerUtil.mutListener.listen(32255)) {
                                if (newEmojiStyle == PreferenceService.EmojiStyle_ANDROID) {
                                    GenericAlertDialog dialog = GenericAlertDialog.newInstance(R.string.prefs_android_emojis, R.string.android_emojis_warning, R.string.ok, R.string.cancel);
                                    if (!ListenerUtil.mutListener.listen(32252)) {
                                        dialog.setData(newEmojiStyle);
                                    }
                                    if (!ListenerUtil.mutListener.listen(32253)) {
                                        dialog.setTargetFragment(SettingsAppearanceFragment.this, 0);
                                    }
                                    if (!ListenerUtil.mutListener.listen(32254)) {
                                        dialog.show(getFragmentManager(), "android_emojis");
                                    }
                                } else {
                                    if (!ListenerUtil.mutListener.listen(32249)) {
                                        ConfigUtils.setEmojiStyle(getActivity(), newEmojiStyle);
                                    }
                                    if (!ListenerUtil.mutListener.listen(32250)) {
                                        updateEmojiPrefs(newEmojiStyle);
                                    }
                                    if (!ListenerUtil.mutListener.listen(32251)) {
                                        ConfigUtils.recreateActivity(getActivity());
                                    }
                                }
                            }
                        }
                    }
                    return true;
                }
            });
        }
        final String[] languageArray = getResources().getStringArray(R.array.list_language_override);
        DropDownPreference languagePreference = (DropDownPreference) findPreference(getResources().getString(R.string.preferences__language_override));
        final String oldLocale = languagePreference.getValue();
        try {
            if (!ListenerUtil.mutListener.listen(32258)) {
                languagePreference.setSummary(languageArray[languagePreference.findIndexOfValue(oldLocale)]);
            }
        } catch (Exception e) {
        }
        if (!ListenerUtil.mutListener.listen(32264)) {
            languagePreference.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {

                @Override
                public boolean onPreferenceChange(Preference preference, Object newValue) {
                    String newLocale = newValue.toString();
                    if (!ListenerUtil.mutListener.listen(32263)) {
                        if ((ListenerUtil.mutListener.listen(32259) ? (newLocale != null || !newLocale.equals(oldLocale)) : (newLocale != null && !newLocale.equals(oldLocale)))) {
                            if (!ListenerUtil.mutListener.listen(32260)) {
                                ConfigUtils.updateLocaleOverride(newValue);
                            }
                            if (!ListenerUtil.mutListener.listen(32261)) {
                                ConfigUtils.updateAppContextLocale(ThreemaApplication.getAppContext(), newLocale);
                            }
                            if (!ListenerUtil.mutListener.listen(32262)) {
                                ConfigUtils.recreateActivity(getActivity());
                            }
                        }
                    }
                    return true;
                }
            });
        }
        Preference wallpaperPreference = findPreference(getResources().getString(R.string.preferences__wallpaper));
        if (!ListenerUtil.mutListener.listen(32266)) {
            wallpaperPreference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {

                @Override
                public boolean onPreferenceClick(Preference preference) {
                    if (!ListenerUtil.mutListener.listen(32265)) {
                        wallpaperService.selectWallpaper(SettingsAppearanceFragment.this, null, null);
                    }
                    return true;
                }
            });
        }
        DropDownPreference sortingPreference = (DropDownPreference) findPreference(getResources().getString(R.string.preferences__contact_sorting));
        if (!ListenerUtil.mutListener.listen(32270)) {
            if (sortingPreference != null) {
                if (!ListenerUtil.mutListener.listen(32269)) {
                    sortingPreference.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {

                        public boolean onPreferenceChange(Preference preference, Object newValue) {
                            if (!ListenerUtil.mutListener.listen(32268)) {
                                // trigger sort change
                                ListenerManager.contactSettingsListeners.handle(new ListenerManager.HandleListener<ContactSettingsListener>() {

                                    @Override
                                    public void handle(ContactSettingsListener listener) {
                                        if (!ListenerUtil.mutListener.listen(32267)) {
                                            listener.onSortingChanged();
                                        }
                                    }
                                });
                            }
                            return true;
                        }
                    });
                }
            }
        }
        DropDownPreference formatPreference = (DropDownPreference) findPreference(getResources().getString(R.string.preferences__contact_format));
        if (!ListenerUtil.mutListener.listen(32273)) {
            formatPreference.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {

                public boolean onPreferenceChange(Preference preference, Object newValue) {
                    if (!ListenerUtil.mutListener.listen(32272)) {
                        // trigger format name change
                        ListenerManager.contactSettingsListeners.handle(new ListenerManager.HandleListener<ContactSettingsListener>() {

                            @Override
                            public void handle(ContactSettingsListener listener) {
                                if (!ListenerUtil.mutListener.listen(32271)) {
                                    listener.onNameFormatChanged();
                                }
                            }
                        });
                    }
                    return true;
                }
            });
        }
        CheckBoxPreference showInactiveContacts = (CheckBoxPreference) findPreference(getResources().getString(R.string.preferences__show_inactive_contacts));
        if (!ListenerUtil.mutListener.listen(32282)) {
            if (showInactiveContacts != null) {
                if (!ListenerUtil.mutListener.listen(32277)) {
                    showInactiveContacts.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {

                        public boolean onPreferenceChange(Preference preference, Object newValue) {
                            boolean newCheckedValue = newValue.equals(true);
                            if (!ListenerUtil.mutListener.listen(32276)) {
                                if (((CheckBoxPreference) preference).isChecked() != newCheckedValue) {
                                    if (!ListenerUtil.mutListener.listen(32275)) {
                                        ListenerManager.contactSettingsListeners.handle(new ListenerManager.HandleListener<ContactSettingsListener>() {

                                            @Override
                                            public void handle(ContactSettingsListener listener) {
                                                if (!ListenerUtil.mutListener.listen(32274)) {
                                                    listener.onInactiveContactsSettingChanged();
                                                }
                                            }
                                        });
                                    }
                                }
                            }
                            return true;
                        }
                    });
                }
                if (!ListenerUtil.mutListener.listen(32281)) {
                    if (ConfigUtils.isWorkRestricted()) {
                        Boolean value = AppRestrictionUtil.getBooleanRestriction(getString(R.string.restriction__hide_inactive_ids));
                        if (!ListenerUtil.mutListener.listen(32280)) {
                            if (value != null) {
                                if (!ListenerUtil.mutListener.listen(32278)) {
                                    showInactiveContacts.setEnabled(false);
                                }
                                if (!ListenerUtil.mutListener.listen(32279)) {
                                    showInactiveContacts.setSelectable(false);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    protected final boolean requiredInstances() {
        if (!ListenerUtil.mutListener.listen(32284)) {
            if (!this.checkInstances()) {
                if (!ListenerUtil.mutListener.listen(32283)) {
                    this.instantiate();
                }
            }
        }
        return this.checkInstances();
    }

    protected boolean checkInstances() {
        return TestUtil.required(this.fileService, this.wallpaperService);
    }

    protected void instantiate() {
        ServiceManager serviceManager = ThreemaApplication.getServiceManager();
        if (!ListenerUtil.mutListener.listen(32288)) {
            if (serviceManager != null) {
                try {
                    if (!ListenerUtil.mutListener.listen(32286)) {
                        this.fileService = serviceManager.getFileService();
                    }
                    if (!ListenerUtil.mutListener.listen(32287)) {
                        this.wallpaperService = serviceManager.getWallpaperService();
                    }
                } catch (FileSystemNotPresentException e) {
                    if (!ListenerUtil.mutListener.listen(32285)) {
                        logger.error("Exception", e);
                    }
                }
            }
        }
    }

    private void updateEmojiPrefs(int newEmojiStyle) {
        DropDownPreference preference = (DropDownPreference) findPreference(getResources().getString(R.string.preferences__emoji_style));
        if (!ListenerUtil.mutListener.listen(32289)) {
            preference.setValueIndex(newEmojiStyle);
        }
        if (!ListenerUtil.mutListener.listen(32290)) {
            preference.setSummary(getResources().getStringArray(R.array.list_emoji_style)[newEmojiStyle]);
        }
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(32291)) {
            preferenceFragmentCallbackInterface.setToolbarTitle(R.string.prefs_header_appearance);
        }
        if (!ListenerUtil.mutListener.listen(32292)) {
            super.onViewCreated(view, savedInstanceState);
        }
    }

    @Override
    public void onYes(String tag, Object data) {
        if (!ListenerUtil.mutListener.listen(32293)) {
            ConfigUtils.setEmojiStyle(getActivity(), (int) data);
        }
        if (!ListenerUtil.mutListener.listen(32294)) {
            updateEmojiPrefs((int) data);
        }
        if (!ListenerUtil.mutListener.listen(32295)) {
            ConfigUtils.recreateActivity(getActivity());
        }
    }

    @Override
    public void onNo(String tag, Object data) {
        if (!ListenerUtil.mutListener.listen(32296)) {
            updateEmojiPrefs(PreferenceService.EmojiStyle_DEFAULT);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (!ListenerUtil.mutListener.listen(32297)) {
            wallpaperService.handleActivityResult(this, requestCode, resultCode, data, null);
        }
        if (!ListenerUtil.mutListener.listen(32298)) {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public void onDetach() {
        if (!ListenerUtil.mutListener.listen(32299)) {
            super.onDetach();
        }
        if (!ListenerUtil.mutListener.listen(32301)) {
            if (this.showBadge.isChecked() != this.showBadgeChecked) {
                if (!ListenerUtil.mutListener.listen(32300)) {
                    ConfigUtils.recreateActivity(getActivity());
                }
            }
        }
    }
}
