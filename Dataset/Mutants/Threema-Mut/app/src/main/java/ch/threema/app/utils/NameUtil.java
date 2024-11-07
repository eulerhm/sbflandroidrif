/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
 * Copyright (c) 2013-2021 Threema GmbH
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
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.util.Pair;
import ch.threema.app.R;
import ch.threema.app.ThreemaApplication;
import ch.threema.app.adapters.FilterableListAdapter;
import ch.threema.app.managers.ServiceManager;
import ch.threema.app.services.ContactService;
import ch.threema.app.services.DistributionListService;
import ch.threema.app.services.GroupService;
import ch.threema.app.services.PreferenceService;
import ch.threema.app.services.UserService;
import ch.threema.app.stores.PreferenceStore;
import ch.threema.storage.models.AbstractMessageModel;
import ch.threema.storage.models.ContactModel;
import ch.threema.storage.models.DistributionListModel;
import ch.threema.storage.models.GroupModel;
import java8.util.J8Arrays;
import java8.util.stream.Collectors;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class NameUtil {

    private static PreferenceService preferenceService;

    private NameUtil(PreferenceStore preferenceStore) {
    }

    private static PreferenceService getPreferenceService() {
        if (!ListenerUtil.mutListener.listen(55038)) {
            if (NameUtil.preferenceService == null) {
                ServiceManager serviceManager = ThreemaApplication.getServiceManager();
                if (!ListenerUtil.mutListener.listen(55037)) {
                    if (serviceManager != null) {
                        if (!ListenerUtil.mutListener.listen(55036)) {
                            NameUtil.preferenceService = serviceManager.getPreferenceService();
                        }
                    }
                }
            }
        }
        return NameUtil.preferenceService;
    }

    /**
     *  Return the display name for a group.
     */
    public static String getDisplayName(GroupModel groupModel, GroupService groupService) {
        if (!ListenerUtil.mutListener.listen(55045)) {
            if ((ListenerUtil.mutListener.listen(55044) ? (groupModel.getName() != null || (ListenerUtil.mutListener.listen(55043) ? (groupModel.getName().length() >= 0) : (ListenerUtil.mutListener.listen(55042) ? (groupModel.getName().length() <= 0) : (ListenerUtil.mutListener.listen(55041) ? (groupModel.getName().length() < 0) : (ListenerUtil.mutListener.listen(55040) ? (groupModel.getName().length() != 0) : (ListenerUtil.mutListener.listen(55039) ? (groupModel.getName().length() == 0) : (groupModel.getName().length() > 0))))))) : (groupModel.getName() != null && (ListenerUtil.mutListener.listen(55043) ? (groupModel.getName().length() >= 0) : (ListenerUtil.mutListener.listen(55042) ? (groupModel.getName().length() <= 0) : (ListenerUtil.mutListener.listen(55041) ? (groupModel.getName().length() < 0) : (ListenerUtil.mutListener.listen(55040) ? (groupModel.getName().length() != 0) : (ListenerUtil.mutListener.listen(55039) ? (groupModel.getName().length() == 0) : (groupModel.getName().length() > 0))))))))) {
                return groupModel.getName();
            }
        }
        // list members
        StringBuilder name = new StringBuilder();
        if (!ListenerUtil.mutListener.listen(55054)) {
            {
                long _loopCounter666 = 0;
                for (ContactModel contactModel : groupService.getMembers(groupModel)) {
                    ListenerUtil.loopListener.listen("_loopCounter666", ++_loopCounter666);
                    if (!ListenerUtil.mutListener.listen(55052)) {
                        if ((ListenerUtil.mutListener.listen(55050) ? (name.length() >= 0) : (ListenerUtil.mutListener.listen(55049) ? (name.length() <= 0) : (ListenerUtil.mutListener.listen(55048) ? (name.length() < 0) : (ListenerUtil.mutListener.listen(55047) ? (name.length() != 0) : (ListenerUtil.mutListener.listen(55046) ? (name.length() == 0) : (name.length() > 0))))))) {
                            if (!ListenerUtil.mutListener.listen(55051)) {
                                name.append(", ");
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(55053)) {
                        name.append(NameUtil.getDisplayName(contactModel));
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(55060)) {
            if ((ListenerUtil.mutListener.listen(55059) ? (name.length() >= 0) : (ListenerUtil.mutListener.listen(55058) ? (name.length() <= 0) : (ListenerUtil.mutListener.listen(55057) ? (name.length() < 0) : (ListenerUtil.mutListener.listen(55056) ? (name.length() != 0) : (ListenerUtil.mutListener.listen(55055) ? (name.length() == 0) : (name.length() > 0))))))) {
                return name.toString();
            }
        }
        return groupModel.getApiGroupId();
    }

    /**
     *  Return the display name for a distribution list.
     */
    public static String getDisplayName(DistributionListModel distributionListModel, DistributionListService distributionListService) {
        if (!ListenerUtil.mutListener.listen(55062)) {
            if ((ListenerUtil.mutListener.listen(55061) ? (!TestUtil.empty(distributionListModel.getName()) && distributionListService == null) : (!TestUtil.empty(distributionListModel.getName()) || distributionListService == null))) {
                return distributionListModel.getName();
            }
        }
        StringBuilder name = new StringBuilder();
        if (!ListenerUtil.mutListener.listen(55071)) {
            {
                long _loopCounter667 = 0;
                for (ContactModel contactModel : distributionListService.getMembers(distributionListModel)) {
                    ListenerUtil.loopListener.listen("_loopCounter667", ++_loopCounter667);
                    if (!ListenerUtil.mutListener.listen(55069)) {
                        if ((ListenerUtil.mutListener.listen(55067) ? (name.length() >= 0) : (ListenerUtil.mutListener.listen(55066) ? (name.length() <= 0) : (ListenerUtil.mutListener.listen(55065) ? (name.length() < 0) : (ListenerUtil.mutListener.listen(55064) ? (name.length() != 0) : (ListenerUtil.mutListener.listen(55063) ? (name.length() == 0) : (name.length() > 0))))))) {
                            if (!ListenerUtil.mutListener.listen(55068)) {
                                name.append(", ");
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(55070)) {
                        name.append(NameUtil.getDisplayName(contactModel));
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(55077)) {
            if ((ListenerUtil.mutListener.listen(55076) ? (name.length() >= 0) : (ListenerUtil.mutListener.listen(55075) ? (name.length() <= 0) : (ListenerUtil.mutListener.listen(55074) ? (name.length() < 0) : (ListenerUtil.mutListener.listen(55073) ? (name.length() != 0) : (ListenerUtil.mutListener.listen(55072) ? (name.length() == 0) : (name.length() > 0))))))) {
                return name.toString();
            }
        }
        return String.valueOf(distributionListModel.getId());
    }

    public static String getDisplayNameOrNickname(Context context, AbstractMessageModel messageModel, ContactService contactService) {
        if (!ListenerUtil.mutListener.listen(55078)) {
            if (TestUtil.required(context, messageModel)) {
                ContactModel model;
                if (messageModel.isOutbox()) {
                    model = contactService.getMe();
                } else {
                    model = contactService.getByIdentity(messageModel.getIdentity());
                }
                return getDisplayNameOrNickname(model, true);
            }
        }
        return null;
    }

    public static String getShortName(String identity, ContactService contactService) {
        String shortname = null;
        if (!ListenerUtil.mutListener.listen(55079)) {
            if (identity.equals(ContactService.ALL_USERS_PLACEHOLDER_ID)) {
                return ThreemaApplication.getAppContext().getString(R.string.all);
            }
        }
        if (!ListenerUtil.mutListener.listen(55081)) {
            if (contactService != null) {
                if (!ListenerUtil.mutListener.listen(55080)) {
                    shortname = NameUtil.getShortName(contactService.getByIdentity(identity));
                }
            }
        }
        return shortname != null ? shortname : identity;
    }

    public static String getShortName(ContactModel model) {
        if (!ListenerUtil.mutListener.listen(55084)) {
            if (model != null) {
                if (!ListenerUtil.mutListener.listen(55083)) {
                    if (TestUtil.empty(model.getFirstName())) {
                        if (!ListenerUtil.mutListener.listen(55082)) {
                            if (TestUtil.empty(model.getLastName())) {
                                return getFallbackName(model);
                            } else {
                                return getDisplayName(model);
                            }
                        }
                    } else {
                        return model.getFirstName();
                    }
                }
            }
        }
        return null;
    }

    public static String getShortName(Context context, AbstractMessageModel messageModel, ContactService contactService) {
        if (!ListenerUtil.mutListener.listen(55086)) {
            if (TestUtil.required(context, messageModel)) {
                if (!ListenerUtil.mutListener.listen(55085)) {
                    if (messageModel.isOutbox()) {
                        return context.getString(R.string.me_myself_and_i);
                    } else {
                        return getShortName(contactService.getByIdentity(messageModel.getIdentity()));
                    }
                }
            }
        }
        return null;
    }

    public static String getPersonName(Context context, AbstractMessageModel messageModel, ContactService contactService) {
        if (!ListenerUtil.mutListener.listen(55088)) {
            if (TestUtil.required(context, messageModel)) {
                if (!ListenerUtil.mutListener.listen(55087)) {
                    if (messageModel.isOutbox()) {
                        return context.getString(R.string.me_myself_and_i);
                    } else {
                        return getDisplayNameOrNickname(messageModel.getIdentity(), contactService);
                    }
                }
            }
        }
        return null;
    }

    private static String getFallbackName(ContactModel model) {
        if ((ListenerUtil.mutListener.listen(55089) ? (!TestUtil.empty(model.getPublicNickName()) || !model.getPublicNickName().equals(model.getIdentity())) : (!TestUtil.empty(model.getPublicNickName()) && !model.getPublicNickName().equals(model.getIdentity())))) {
            return "~" + model.getPublicNickName();
        } else {
            return model.getIdentity();
        }
    }

    /**
     *  Return the display name for a contact.
     */
    @NonNull
    public static String getDisplayName(ContactModel contactModel) {
        String c = "";
        if (!ListenerUtil.mutListener.listen(55090)) {
            if (contactModel == null) {
                return "undefined";
            }
        }
        if (!ListenerUtil.mutListener.listen(55092)) {
            if ((ListenerUtil.mutListener.listen(55091) ? (contactModel.getIdentity() == null && contactModel.getIdentity().length() == 0) : (contactModel.getIdentity() == null || contactModel.getIdentity().length() == 0))) {
                return "invalid contact";
            }
        }
        String f = contactModel.getFirstName();
        String l = contactModel.getLastName();
        if (!ListenerUtil.mutListener.listen(55093)) {
            if (TestUtil.empty(f, l)) {
                return contactModel.getIdentity();
            }
        }
        PreferenceService preferenceService = NameUtil.getPreferenceService();
        if (!ListenerUtil.mutListener.listen(55103)) {
            if ((ListenerUtil.mutListener.listen(55094) ? (preferenceService == null && preferenceService.isContactFormatFirstNameLastName()) : (preferenceService == null || preferenceService.isContactFormatFirstNameLastName()))) {
                if (!ListenerUtil.mutListener.listen(55100)) {
                    if (f != null) {
                        if (!ListenerUtil.mutListener.listen(55099)) {
                            c += f + " ";
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(55102)) {
                    if (l != null) {
                        if (!ListenerUtil.mutListener.listen(55101)) {
                            c += l;
                        }
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(55096)) {
                    if (l != null) {
                        if (!ListenerUtil.mutListener.listen(55095)) {
                            c += l + " ";
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(55098)) {
                    if (f != null) {
                        if (!ListenerUtil.mutListener.listen(55097)) {
                            c += f;
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(55105)) {
            if (TestUtil.empty(c)) {
                if (!ListenerUtil.mutListener.listen(55104)) {
                    c = contactModel.getIdentity();
                }
            }
        }
        return c.trim();
    }

    /**
     *  Return the display name for a contact, or fall back to the nickname.
     */
    @NonNull
    public static String getDisplayNameOrNickname(@Nullable ContactModel contactModel, boolean withPrefix) {
        if (contactModel == null)
            return "";
        String displayName = NameUtil.getDisplayName(contactModel);
        String nickName = contactModel.getPublicNickName();
        if ((ListenerUtil.mutListener.listen(55108) ? ((ListenerUtil.mutListener.listen(55107) ? ((ListenerUtil.mutListener.listen(55106) ? (displayName.equals(contactModel.getIdentity()) || nickName != null) : (displayName.equals(contactModel.getIdentity()) && nickName != null)) || !nickName.isEmpty()) : ((ListenerUtil.mutListener.listen(55106) ? (displayName.equals(contactModel.getIdentity()) || nickName != null) : (displayName.equals(contactModel.getIdentity()) && nickName != null)) && !nickName.isEmpty())) || !displayName.equals(nickName)) : ((ListenerUtil.mutListener.listen(55107) ? ((ListenerUtil.mutListener.listen(55106) ? (displayName.equals(contactModel.getIdentity()) || nickName != null) : (displayName.equals(contactModel.getIdentity()) && nickName != null)) || !nickName.isEmpty()) : ((ListenerUtil.mutListener.listen(55106) ? (displayName.equals(contactModel.getIdentity()) || nickName != null) : (displayName.equals(contactModel.getIdentity()) && nickName != null)) && !nickName.isEmpty())) && !displayName.equals(nickName)))) {
            return withPrefix ? "~" + nickName : nickName;
        } else {
            return displayName;
        }
    }

    public static String getDisplayNameOrNickname(String identity, ContactService contactService) {
        String displayName = null;
        if (!ListenerUtil.mutListener.listen(55110)) {
            if (contactService != null) {
                if (!ListenerUtil.mutListener.listen(55109)) {
                    displayName = NameUtil.getDisplayNameOrNickname(contactService.getByIdentity(identity), true);
                }
            }
        }
        return TextUtils.isEmpty(displayName) ? identity : displayName.substring(0, Math.min(displayName.length(), 24));
    }

    /**
     *  Return the name used for quotes and mentions.
     */
    public static String getQuoteName(@Nullable ContactModel contactModel, @NonNull UserService userService) {
        if (!ListenerUtil.mutListener.listen(55111)) {
            if (contactModel == null) {
                return "";
            }
        }
        if (!ListenerUtil.mutListener.listen(55114)) {
            // return the nickname.
            if (userService.isMe(contactModel.getIdentity())) {
                final String myNickname = userService.getPublicNickname();
                if (!ListenerUtil.mutListener.listen(55113)) {
                    if ((ListenerUtil.mutListener.listen(55112) ? (!TestUtil.empty(myNickname) || !myNickname.equals(userService.getIdentity())) : (!TestUtil.empty(myNickname) && !myNickname.equals(userService.getIdentity())))) {
                        return myNickname;
                    }
                }
            }
        }
        return getDisplayNameOrNickname(contactModel, true);
    }

    /**
     *  Return the name used for quotes and mentions.
     */
    public static String getQuoteName(@Nullable String identity, ContactService contactService, UserService userService) {
        if (!ListenerUtil.mutListener.listen(55117)) {
            if ((ListenerUtil.mutListener.listen(55116) ? ((ListenerUtil.mutListener.listen(55115) ? (contactService == null && userService == null) : (contactService == null || userService == null)) && identity == null) : ((ListenerUtil.mutListener.listen(55115) ? (contactService == null && userService == null) : (contactService == null || userService == null)) || identity == null))) {
                return "";
            }
        }
        if (!ListenerUtil.mutListener.listen(55118)) {
            if (ContactService.ALL_USERS_PLACEHOLDER_ID.equals(identity)) {
                return ThreemaApplication.getAppContext().getString(R.string.all);
            }
        }
        final ContactModel contactModel = contactService.getByIdentity(identity);
        return getQuoteName(contactModel, userService);
    }

    public static void showNicknameInView(TextView nickNameTextView, ContactModel contactModel, String filterString, FilterableListAdapter adapter) {
        if (!ListenerUtil.mutListener.listen(55128)) {
            if (nickNameTextView != null) {
                if (!ListenerUtil.mutListener.listen(55126)) {
                    if (contactModel != null) {
                        String nickname = contactModel.getPublicNickName();
                        if (!ListenerUtil.mutListener.listen(55125)) {
                            if ((ListenerUtil.mutListener.listen(55119) ? (!TestUtil.empty(nickname) || !nickname.equals(contactModel.getIdentity())) : (!TestUtil.empty(nickname) && !nickname.equals(contactModel.getIdentity())))) {
                                String text = "~" + nickname;
                                if (!ListenerUtil.mutListener.listen(55123)) {
                                    if ((ListenerUtil.mutListener.listen(55120) ? (filterString != null || adapter != null) : (filterString != null && adapter != null))) {
                                        if (!ListenerUtil.mutListener.listen(55122)) {
                                            nickNameTextView.setText(adapter.highlightMatches(text, filterString));
                                        }
                                    } else {
                                        if (!ListenerUtil.mutListener.listen(55121)) {
                                            nickNameTextView.setText(text);
                                        }
                                    }
                                }
                                if (!ListenerUtil.mutListener.listen(55124)) {
                                    nickNameTextView.setVisibility(View.VISIBLE);
                                }
                                return;
                            }
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(55127)) {
                    nickNameTextView.setVisibility(View.GONE);
                }
            }
        }
    }

    /**
     *  Extract first and last name from display name as provided by the Android contact database
     *  If displayName is empty or null, empty strings will be returned for first/last name.
     *
     *  @param displayName Name of the contact to split
     *  @return A Pair containing first and last name
     */
    @NonNull
    public static Pair<String, String> getFirstLastNameFromDisplayName(@Nullable String displayName) {
        final String[] parts = displayName == null ? null : displayName.split(" ");
        if (!ListenerUtil.mutListener.listen(55135)) {
            if ((ListenerUtil.mutListener.listen(55134) ? (parts == null && (ListenerUtil.mutListener.listen(55133) ? (parts.length >= 0) : (ListenerUtil.mutListener.listen(55132) ? (parts.length <= 0) : (ListenerUtil.mutListener.listen(55131) ? (parts.length > 0) : (ListenerUtil.mutListener.listen(55130) ? (parts.length < 0) : (ListenerUtil.mutListener.listen(55129) ? (parts.length != 0) : (parts.length == 0))))))) : (parts == null || (ListenerUtil.mutListener.listen(55133) ? (parts.length >= 0) : (ListenerUtil.mutListener.listen(55132) ? (parts.length <= 0) : (ListenerUtil.mutListener.listen(55131) ? (parts.length > 0) : (ListenerUtil.mutListener.listen(55130) ? (parts.length < 0) : (ListenerUtil.mutListener.listen(55129) ? (parts.length != 0) : (parts.length == 0))))))))) {
                return new Pair<>("", "");
            }
        }
        final String firstName = parts[0];
        final String lastName = J8Arrays.stream(parts).skip(1).collect(Collectors.joining(" "));
        return new Pair<>(firstName, lastName);
    }
}
