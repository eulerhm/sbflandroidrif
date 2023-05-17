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
package ch.threema.app.dialogs;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.InputType;
import android.view.View;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textfield.TextInputLayout;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.File;
import java.lang.ref.WeakReference;
import androidx.annotation.NonNull;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatDialog;
import ch.threema.app.R;
import ch.threema.app.ThreemaApplication;
import ch.threema.app.emojis.EmojiEditText;
import ch.threema.app.exceptions.FileSystemNotPresentException;
import ch.threema.app.exceptions.NoIdentityException;
import ch.threema.app.services.ContactService;
import ch.threema.app.services.GroupService;
import ch.threema.app.ui.AvatarEditView;
import ch.threema.app.utils.ContactUtil;
import ch.threema.app.utils.TestUtil;
import ch.threema.app.utils.ViewUtil;
import ch.threema.base.Contact;
import ch.threema.localcrypto.MasterKeyLockedException;
import ch.threema.storage.models.ContactModel;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class ContactEditDialog extends ThreemaDialogFragment implements AvatarEditView.AvatarEditListener {

    private static final Logger logger = LoggerFactory.getLogger(ContactEditDialog.class);

    private static final String ARG_TITLE = "title";

    private static final String ARG_TEXT1 = "text1";

    private static final String ARG_TEXT2 = "text2";

    private static final String ARG_HINT1 = "hint1";

    private static final String ARG_HINT2 = "hint2";

    private static final String ARG_IDENTITY = "identity";

    private static final String ARG_GROUP_ID = "groupId";

    private static final String BUNDLE_CROPPED_AVATAR_FILE = "cropped_avatar_file";

    public static int CONTACT_AVATAR_HEIGHT_PX = 512;

    public static int CONTACT_AVATAR_WIDTH_PX = 512;

    private WeakReference<ContactEditDialogClickListener> callbackRef = new WeakReference<>(null);

    private Activity activity;

    private AvatarEditView avatarEditView;

    private File croppedAvatarFile = null;

    public static ContactEditDialog newInstance(ContactModel contactModel) {
        final int inputType = InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PERSON_NAME | InputType.TYPE_TEXT_FLAG_CAP_WORDS;
        if (ContactUtil.isChannelContact(contactModel)) {
            // business contact don't have a second name
            return newInstance(R.string.edit_name_only, contactModel.getFirstName(), null, R.string.name, 0, contactModel.getIdentity(), inputType, ContactUtil.CHANNEL_NAME_MAX_LENGTH_BYTES);
        } else {
            return newInstance(R.string.edit_name_only, contactModel.getFirstName(), contactModel.getLastName(), R.string.first_name, R.string.last_name, contactModel.getIdentity(), inputType, Contact.CONTACT_NAME_MAX_LENGTH_BYTES);
        }
    }

    public static ContactEditDialog newInstance(Bundle args) {
        ContactEditDialog dialog = new ContactEditDialog();
        if (!ListenerUtil.mutListener.listen(13332)) {
            dialog.setArguments(args);
        }
        return dialog;
    }

    /**
     *  Create a ContactEditDialog with two input fields.
     */
    public static ContactEditDialog newInstance(@StringRes int title, String text1, String text2, @StringRes int hint1, @StringRes int hint2, String identity, int inputType, int maxLength) {
        final Bundle args = new Bundle();
        if (!ListenerUtil.mutListener.listen(13333)) {
            args.putInt(ARG_TITLE, title);
        }
        if (!ListenerUtil.mutListener.listen(13334)) {
            args.putString(ARG_TEXT1, text1);
        }
        if (!ListenerUtil.mutListener.listen(13335)) {
            args.putString(ARG_TEXT2, text2);
        }
        if (!ListenerUtil.mutListener.listen(13336)) {
            args.putInt(ARG_HINT1, hint1);
        }
        if (!ListenerUtil.mutListener.listen(13337)) {
            args.putInt(ARG_HINT2, hint2);
        }
        if (!ListenerUtil.mutListener.listen(13338)) {
            args.putString(ARG_IDENTITY, identity);
        }
        if (!ListenerUtil.mutListener.listen(13339)) {
            args.putInt("inputType", inputType);
        }
        if (!ListenerUtil.mutListener.listen(13340)) {
            args.putInt("maxLength", maxLength);
        }
        return newInstance(args);
    }

    /**
     *  Create a ContactEditDialog with just one input field.
     */
    public static ContactEditDialog newInstance(@StringRes int title, String text1, @StringRes int hint1, String identity, int inputType, int maxLength) {
        final Bundle args = new Bundle();
        if (!ListenerUtil.mutListener.listen(13341)) {
            args.putInt(ARG_TITLE, title);
        }
        if (!ListenerUtil.mutListener.listen(13342)) {
            args.putString(ARG_TEXT1, text1);
        }
        if (!ListenerUtil.mutListener.listen(13343)) {
            args.putInt(ARG_HINT1, hint1);
        }
        if (!ListenerUtil.mutListener.listen(13344)) {
            args.putString(ARG_IDENTITY, identity);
        }
        if (!ListenerUtil.mutListener.listen(13345)) {
            args.putInt("inputType", inputType);
        }
        if (!ListenerUtil.mutListener.listen(13346)) {
            args.putInt("maxLength", maxLength);
        }
        return newInstance(args);
    }

    /**
     *  Create a ContactEditDialog for a group
     */
    public static ContactEditDialog newInstance(@StringRes int title, @StringRes int hint1, int groupId, int inputType, File avatarPreset, boolean useDefaultAvatar, int maxLength) {
        final Bundle args = new Bundle();
        if (!ListenerUtil.mutListener.listen(13347)) {
            args.putInt(ARG_TITLE, title);
        }
        if (!ListenerUtil.mutListener.listen(13348)) {
            args.putInt(ARG_HINT1, hint1);
        }
        if (!ListenerUtil.mutListener.listen(13349)) {
            args.putInt(ARG_GROUP_ID, groupId);
        }
        if (!ListenerUtil.mutListener.listen(13350)) {
            args.putInt("inputType", inputType);
        }
        if (!ListenerUtil.mutListener.listen(13351)) {
            args.putSerializable("avatarPreset", avatarPreset);
        }
        if (!ListenerUtil.mutListener.listen(13352)) {
            args.putBoolean("useDefaultAvatar", useDefaultAvatar);
        }
        if (!ListenerUtil.mutListener.listen(13353)) {
            args.putInt("maxLength", maxLength);
        }
        return newInstance(args);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (!ListenerUtil.mutListener.listen(13354)) {
            super.onActivityResult(requestCode, resultCode, intent);
        }
        if (!ListenerUtil.mutListener.listen(13356)) {
            if (this.avatarEditView != null) {
                if (!ListenerUtil.mutListener.listen(13355)) {
                    this.avatarEditView.onActivityResult(requestCode, resultCode, intent);
                }
            }
        }
    }

    @Override
    public void onAvatarSet(File avatarFile) {
        if (!ListenerUtil.mutListener.listen(13357)) {
            croppedAvatarFile = avatarFile;
        }
    }

    @Override
    public void onAvatarRemoved() {
        if (!ListenerUtil.mutListener.listen(13358)) {
            croppedAvatarFile = null;
        }
    }

    public interface ContactEditDialogClickListener {

        void onYes(String tag, String text1, String text2, File avatar);

        void onNo(String tag);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(13359)) {
            super.onCreate(savedInstanceState);
        }
        try {
            if (!ListenerUtil.mutListener.listen(13360)) {
                callbackRef = new WeakReference<>((ContactEditDialogClickListener) getTargetFragment());
            }
        } catch (ClassCastException e) {
        }
        if (!ListenerUtil.mutListener.listen(13363)) {
            // called from an activity rather than a fragment
            if (callbackRef.get() == null) {
                if (!ListenerUtil.mutListener.listen(13361)) {
                    if (!(activity instanceof ContactEditDialogClickListener)) {
                        throw new ClassCastException("Calling fragment must implement ContactEditDialogClickListener interface");
                    }
                }
                if (!ListenerUtil.mutListener.listen(13362)) {
                    callbackRef = new WeakReference<>((ContactEditDialogClickListener) activity);
                }
            }
        }
    }

    @Override
    public void onAttach(@NonNull Activity activity) {
        if (!ListenerUtil.mutListener.listen(13364)) {
            super.onAttach(activity);
        }
        if (!ListenerUtil.mutListener.listen(13365)) {
            this.activity = activity;
        }
    }

    @NonNull
    @Override
    public AppCompatDialog onCreateDialog(Bundle savedInstanceState) {
        int title = getArguments().getInt(ARG_TITLE);
        String text1 = getArguments().getString(ARG_TEXT1);
        String text2 = getArguments().getString(ARG_TEXT2);
        int hint1 = getArguments().getInt(ARG_HINT1);
        int hint2 = getArguments().getInt(ARG_HINT2);
        String identity = getArguments().getString(ARG_IDENTITY);
        int groupId = getArguments().getInt(ARG_GROUP_ID);
        int inputType = getArguments().getInt("inputType");
        int maxLength = getArguments().getInt("maxLength", 0);
        final String tag = this.getTag();
        if (!ListenerUtil.mutListener.listen(13366)) {
            croppedAvatarFile = (File) getArguments().getSerializable("avatarPreset");
        }
        ContactService contactService = null;
        GroupService groupService = null;
        try {
            if (!ListenerUtil.mutListener.listen(13368)) {
                contactService = ThreemaApplication.getServiceManager().getContactService();
            }
            if (!ListenerUtil.mutListener.listen(13369)) {
                groupService = ThreemaApplication.getServiceManager().getGroupService();
            }
        } catch (MasterKeyLockedException | FileSystemNotPresentException | NoIdentityException e) {
            if (!ListenerUtil.mutListener.listen(13367)) {
                logger.error("Exception", e);
            }
        }
        if (!ListenerUtil.mutListener.listen(13371)) {
            if (savedInstanceState != null) {
                if (!ListenerUtil.mutListener.listen(13370)) {
                    croppedAvatarFile = (File) savedInstanceState.getSerializable(BUNDLE_CROPPED_AVATAR_FILE);
                }
            }
        }
        final View dialogView = activity.getLayoutInflater().inflate(R.layout.dialog_contact_edit, null);
        final EmojiEditText editText1 = dialogView.findViewById(R.id.first_name);
        final EmojiEditText editText2 = dialogView.findViewById(R.id.last_name);
        final TextInputLayout editText1Layout = dialogView.findViewById(R.id.firstname_layout);
        final TextInputLayout editText2Layout = dialogView.findViewById(R.id.lastname_layout);
        if (!ListenerUtil.mutListener.listen(13372)) {
            avatarEditView = dialogView.findViewById(R.id.avatar_edit_view);
        }
        if (!ListenerUtil.mutListener.listen(13375)) {
            if (savedInstanceState == null) {
                if (!ListenerUtil.mutListener.listen(13373)) {
                    avatarEditView.setFragment(this);
                }
                if (!ListenerUtil.mutListener.listen(13374)) {
                    avatarEditView.setListener(this);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(13388)) {
            if (!TestUtil.empty(identity)) {
                if (!ListenerUtil.mutListener.listen(13384)) {
                    avatarEditView.setVisibility(View.GONE);
                }
                if (!ListenerUtil.mutListener.listen(13387)) {
                    if (contactService != null) {
                        ContactModel contactModel = contactService.getByIdentity(identity);
                        if (!ListenerUtil.mutListener.listen(13386)) {
                            // hide second name on business contact
                            if (ContactUtil.isChannelContact(contactModel)) {
                                if (!ListenerUtil.mutListener.listen(13385)) {
                                    ViewUtil.show(editText2, false);
                                }
                            }
                        }
                    }
                }
            } else if ((ListenerUtil.mutListener.listen(13380) ? (groupId >= 0) : (ListenerUtil.mutListener.listen(13379) ? (groupId <= 0) : (ListenerUtil.mutListener.listen(13378) ? (groupId > 0) : (ListenerUtil.mutListener.listen(13377) ? (groupId < 0) : (ListenerUtil.mutListener.listen(13376) ? (groupId == 0) : (groupId != 0))))))) {
                if (!ListenerUtil.mutListener.listen(13381)) {
                    editText2.setVisibility(View.GONE);
                }
                if (!ListenerUtil.mutListener.listen(13382)) {
                    avatarEditView.setUndefinedAvatar(AvatarEditView.AVATAR_TYPE_GROUP);
                }
                if (!ListenerUtil.mutListener.listen(13383)) {
                    avatarEditView.setEditable(true);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(13395)) {
            if ((ListenerUtil.mutListener.listen(13393) ? (hint1 >= 0) : (ListenerUtil.mutListener.listen(13392) ? (hint1 <= 0) : (ListenerUtil.mutListener.listen(13391) ? (hint1 > 0) : (ListenerUtil.mutListener.listen(13390) ? (hint1 < 0) : (ListenerUtil.mutListener.listen(13389) ? (hint1 == 0) : (hint1 != 0))))))) {
                if (!ListenerUtil.mutListener.listen(13394)) {
                    editText1Layout.setHint(getString(hint1));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(13404)) {
            if ((ListenerUtil.mutListener.listen(13400) ? (hint2 >= 0) : (ListenerUtil.mutListener.listen(13399) ? (hint2 <= 0) : (ListenerUtil.mutListener.listen(13398) ? (hint2 > 0) : (ListenerUtil.mutListener.listen(13397) ? (hint2 < 0) : (ListenerUtil.mutListener.listen(13396) ? (hint2 == 0) : (hint2 != 0))))))) {
                if (!ListenerUtil.mutListener.listen(13403)) {
                    editText2Layout.setHint(getString(hint2));
                }
            } else {
                if (!ListenerUtil.mutListener.listen(13401)) {
                    editText2.setVisibility(View.GONE);
                }
                if (!ListenerUtil.mutListener.listen(13402)) {
                    editText2Layout.setVisibility(View.GONE);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(13406)) {
            if (!TestUtil.empty(text1)) {
                if (!ListenerUtil.mutListener.listen(13405)) {
                    editText1.setText(text1);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(13408)) {
            if (!TestUtil.empty(text2)) {
                if (!ListenerUtil.mutListener.listen(13407)) {
                    editText2.setText(text2);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(13416)) {
            if ((ListenerUtil.mutListener.listen(13413) ? (inputType >= 0) : (ListenerUtil.mutListener.listen(13412) ? (inputType <= 0) : (ListenerUtil.mutListener.listen(13411) ? (inputType > 0) : (ListenerUtil.mutListener.listen(13410) ? (inputType < 0) : (ListenerUtil.mutListener.listen(13409) ? (inputType == 0) : (inputType != 0))))))) {
                if (!ListenerUtil.mutListener.listen(13414)) {
                    editText1.setInputType(inputType);
                }
                if (!ListenerUtil.mutListener.listen(13415)) {
                    editText2.setInputType(inputType);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(13424)) {
            if ((ListenerUtil.mutListener.listen(13421) ? (maxLength >= 0) : (ListenerUtil.mutListener.listen(13420) ? (maxLength <= 0) : (ListenerUtil.mutListener.listen(13419) ? (maxLength < 0) : (ListenerUtil.mutListener.listen(13418) ? (maxLength != 0) : (ListenerUtil.mutListener.listen(13417) ? (maxLength == 0) : (maxLength > 0))))))) {
                if (!ListenerUtil.mutListener.listen(13422)) {
                    editText1.setFilters(new InputFilter[] { new InputFilter.LengthFilter(maxLength) });
                }
                if (!ListenerUtil.mutListener.listen(13423)) {
                    editText2.setFilters(new InputFilter[] { new InputFilter.LengthFilter(maxLength) });
                }
            }
        }
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(getActivity(), getTheme());
        if (!ListenerUtil.mutListener.listen(13431)) {
            if ((ListenerUtil.mutListener.listen(13429) ? (title >= 0) : (ListenerUtil.mutListener.listen(13428) ? (title <= 0) : (ListenerUtil.mutListener.listen(13427) ? (title > 0) : (ListenerUtil.mutListener.listen(13426) ? (title < 0) : (ListenerUtil.mutListener.listen(13425) ? (title == 0) : (title != 0))))))) {
                if (!ListenerUtil.mutListener.listen(13430)) {
                    builder.setTitle(title);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(13432)) {
            builder.setView(dialogView);
        }
        if (!ListenerUtil.mutListener.listen(13435)) {
            builder.setPositiveButton(getString(R.string.ok), new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialog, int whichButton) {
                    if (!ListenerUtil.mutListener.listen(13434)) {
                        if (callbackRef.get() != null) {
                            if (!ListenerUtil.mutListener.listen(13433)) {
                                callbackRef.get().onYes(tag, editText1.getText().toString(), editText2.getText().toString(), croppedAvatarFile);
                            }
                        }
                    }
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(13438)) {
            builder.setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialog, int whichButton) {
                    if (!ListenerUtil.mutListener.listen(13437)) {
                        if (callbackRef.get() != null) {
                            if (!ListenerUtil.mutListener.listen(13436)) {
                                callbackRef.get().onNo(tag);
                            }
                        }
                    }
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(13439)) {
            setCancelable(false);
        }
        return builder.create();
    }

    @Override
    public void onCancel(@NonNull DialogInterface dialogInterface) {
        if (!ListenerUtil.mutListener.listen(13441)) {
            if (callbackRef.get() != null) {
                if (!ListenerUtil.mutListener.listen(13440)) {
                    callbackRef.get().onNo(this.getTag());
                }
            }
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        if (!ListenerUtil.mutListener.listen(13442)) {
            super.onSaveInstanceState(outState);
        }
        if (!ListenerUtil.mutListener.listen(13443)) {
            outState.putSerializable(BUNDLE_CROPPED_AVATAR_FILE, croppedAvatarFile);
        }
    }
}
