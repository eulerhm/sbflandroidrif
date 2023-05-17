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
package ch.threema.app.adapters;

import android.Manifest;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import ch.threema.app.R;
import ch.threema.app.ThreemaApplication;
import ch.threema.app.managers.ServiceManager;
import ch.threema.app.services.FingerPrintService;
import ch.threema.app.services.GroupService;
import ch.threema.app.services.IdListService;
import ch.threema.app.services.PreferenceService;
import ch.threema.app.ui.VerificationLevelImageView;
import ch.threema.app.utils.AndroidContactUtil;
import ch.threema.app.utils.ConfigUtils;
import ch.threema.app.utils.ContactUtil;
import ch.threema.storage.models.ContactModel;
import ch.threema.storage.models.GroupModel;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class ContactDetailAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final Logger logger = LoggerFactory.getLogger(ContactDetailAdapter.class);

    private static final int TYPE_HEADER = 0;

    private static final int TYPE_ITEM = 1;

    private final Context context;

    private GroupService groupService;

    private PreferenceService preferenceService;

    private FingerPrintService fingerprintService;

    private IdListService excludeFromSyncListService;

    private IdListService blackListIdentityService;

    private final ContactModel contactModel;

    private final List<GroupModel> values;

    private OnClickListener onClickListener;

    public static class ItemHolder extends RecyclerView.ViewHolder {

        public final View view;

        public final TextView nameView;

        public final ImageView avatarView, statusView;

        public ItemHolder(View view) {
            super(view);
            this.view = view;
            this.nameView = itemView.findViewById(R.id.contact_name);
            this.avatarView = itemView.findViewById(R.id.contact_avatar);
            this.statusView = itemView.findViewById(R.id.status);
        }
    }

    public class HeaderHolder extends RecyclerView.ViewHolder {

        private final VerificationLevelImageView verificationLevelImageView;

        private final TextView threemaIdView, fingerprintView;

        private final CheckBox synchronize;

        private final View nicknameContainer, synchronizeContainer;

        private final ImageView syncSourceIcon;

        private final TextView publicNickNameView;

        private final LinearLayout groupMembershipTitle;

        public HeaderHolder(View view) {
            super(view);
            // items in object
            this.threemaIdView = itemView.findViewById(R.id.threema_id);
            this.fingerprintView = itemView.findViewById(R.id.key_fingerprint);
            this.verificationLevelImageView = itemView.findViewById(R.id.verification_level_image);
            ImageView verificationLevelIconView = itemView.findViewById(R.id.verification_information_icon);
            this.synchronize = itemView.findViewById(R.id.synchronize_contact);
            this.synchronizeContainer = itemView.findViewById(R.id.synchronize_contact_container);
            this.nicknameContainer = itemView.findViewById(R.id.nickname_container);
            this.publicNickNameView = itemView.findViewById(R.id.public_nickname);
            this.groupMembershipTitle = itemView.findViewById(R.id.group_members_title_container);
            this.syncSourceIcon = itemView.findViewById(R.id.sync_source_icon);
            if (!ListenerUtil.mutListener.listen(8627)) {
                verificationLevelIconView.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        if (!ListenerUtil.mutListener.listen(8626)) {
                            if (onClickListener != null) {
                                if (!ListenerUtil.mutListener.listen(8625)) {
                                    onClickListener.onVerificationInfoClick(v);
                                }
                            }
                        }
                    }
                });
            }
        }
    }

    public ContactDetailAdapter(Context context, List<GroupModel> values, ContactModel contactModel) {
        this.context = context;
        this.values = values;
        this.contactModel = contactModel;
        ServiceManager serviceManager = ThreemaApplication.getServiceManager();
        try {
            if (!ListenerUtil.mutListener.listen(8629)) {
                this.groupService = serviceManager.getGroupService();
            }
            if (!ListenerUtil.mutListener.listen(8630)) {
                this.fingerprintService = serviceManager.getFingerPrintService();
            }
            if (!ListenerUtil.mutListener.listen(8631)) {
                this.excludeFromSyncListService = serviceManager.getExcludedSyncIdentitiesService();
            }
            if (!ListenerUtil.mutListener.listen(8632)) {
                this.blackListIdentityService = serviceManager.getBlackListService();
            }
            if (!ListenerUtil.mutListener.listen(8633)) {
                this.preferenceService = serviceManager.getPreferenceService();
            }
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(8628)) {
                logger.error("Exception", e);
            }
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if ((ListenerUtil.mutListener.listen(8638) ? (viewType >= TYPE_ITEM) : (ListenerUtil.mutListener.listen(8637) ? (viewType <= TYPE_ITEM) : (ListenerUtil.mutListener.listen(8636) ? (viewType > TYPE_ITEM) : (ListenerUtil.mutListener.listen(8635) ? (viewType < TYPE_ITEM) : (ListenerUtil.mutListener.listen(8634) ? (viewType != TYPE_ITEM) : (viewType == TYPE_ITEM))))))) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_contact_detail, parent, false);
            return new ItemHolder(v);
        } else if ((ListenerUtil.mutListener.listen(8643) ? (viewType >= TYPE_HEADER) : (ListenerUtil.mutListener.listen(8642) ? (viewType <= TYPE_HEADER) : (ListenerUtil.mutListener.listen(8641) ? (viewType > TYPE_HEADER) : (ListenerUtil.mutListener.listen(8640) ? (viewType < TYPE_HEADER) : (ListenerUtil.mutListener.listen(8639) ? (viewType != TYPE_HEADER) : (viewType == TYPE_HEADER))))))) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.header_contact_detail, parent, false);
            return new HeaderHolder(v);
        }
        throw new RuntimeException("no matching item type");
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (!ListenerUtil.mutListener.listen(8688)) {
            if (holder instanceof ItemHolder) {
                ItemHolder itemHolder = (ItemHolder) holder;
                final GroupModel groupModel = getItem(position);
                Bitmap avatar = this.groupService.getAvatar(groupModel, false);
                if (!ListenerUtil.mutListener.listen(8682)) {
                    itemHolder.nameView.setText(groupModel.getName());
                }
                if (!ListenerUtil.mutListener.listen(8683)) {
                    itemHolder.avatarView.setImageBitmap(avatar);
                }
                if (!ListenerUtil.mutListener.listen(8686)) {
                    if (groupService.isGroupOwner(groupModel)) {
                        if (!ListenerUtil.mutListener.listen(8685)) {
                            itemHolder.statusView.setImageResource(R.drawable.ic_group_outline);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(8684)) {
                            itemHolder.statusView.setImageResource(R.drawable.ic_group_filled);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(8687)) {
                    itemHolder.view.setOnClickListener(v -> onClickListener.onItemClick(v, groupModel));
                }
            } else {
                HeaderHolder headerHolder = (HeaderHolder) holder;
                String identityAdditional = null;
                if (!ListenerUtil.mutListener.listen(8649)) {
                    if (this.contactModel.getState() != null) {
                        if (!ListenerUtil.mutListener.listen(8648)) {
                            switch(this.contactModel.getState()) {
                                case TEMPORARY:
                                case ACTIVE:
                                    if (!ListenerUtil.mutListener.listen(8645)) {
                                        if (blackListIdentityService.has(contactModel.getIdentity())) {
                                            if (!ListenerUtil.mutListener.listen(8644)) {
                                                identityAdditional = context.getString(R.string.blocked);
                                            }
                                        }
                                    }
                                    break;
                                case INACTIVE:
                                    if (!ListenerUtil.mutListener.listen(8646)) {
                                        identityAdditional = context.getString(R.string.contact_state_inactive);
                                    }
                                    break;
                                case INVALID:
                                    if (!ListenerUtil.mutListener.listen(8647)) {
                                        identityAdditional = context.getString(R.string.contact_state_invalid);
                                    }
                                    break;
                            }
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(8650)) {
                    headerHolder.threemaIdView.setText(contactModel.getIdentity() + (identityAdditional != null ? " (" + identityAdditional + ")" : ""));
                }
                if (!ListenerUtil.mutListener.listen(8651)) {
                    headerHolder.fingerprintView.setText(this.fingerprintService.getFingerPrint(contactModel.getIdentity()));
                }
                if (!ListenerUtil.mutListener.listen(8652)) {
                    headerHolder.verificationLevelImageView.setContactModel(contactModel);
                }
                if (!ListenerUtil.mutListener.listen(8653)) {
                    headerHolder.verificationLevelImageView.setVisibility(View.VISIBLE);
                }
                if (!ListenerUtil.mutListener.listen(8664)) {
                    if ((ListenerUtil.mutListener.listen(8655) ? ((ListenerUtil.mutListener.listen(8654) ? (preferenceService.isSyncContacts() || ContactUtil.isSynchronized(contactModel)) : (preferenceService.isSyncContacts() && ContactUtil.isSynchronized(contactModel))) || ConfigUtils.isPermissionGranted(ThreemaApplication.getAppContext(), Manifest.permission.READ_CONTACTS)) : ((ListenerUtil.mutListener.listen(8654) ? (preferenceService.isSyncContacts() || ContactUtil.isSynchronized(contactModel)) : (preferenceService.isSyncContacts() && ContactUtil.isSynchronized(contactModel))) && ConfigUtils.isPermissionGranted(ThreemaApplication.getAppContext(), Manifest.permission.READ_CONTACTS)))) {
                        if (!ListenerUtil.mutListener.listen(8657)) {
                            headerHolder.synchronizeContainer.setVisibility(View.VISIBLE);
                        }
                        Drawable icon = AndroidContactUtil.getInstance().getAccountIcon(contactModel);
                        if (!ListenerUtil.mutListener.listen(8661)) {
                            if (icon != null) {
                                if (!ListenerUtil.mutListener.listen(8659)) {
                                    headerHolder.syncSourceIcon.setImageDrawable(icon);
                                }
                                if (!ListenerUtil.mutListener.listen(8660)) {
                                    headerHolder.syncSourceIcon.setVisibility(View.VISIBLE);
                                }
                            } else {
                                if (!ListenerUtil.mutListener.listen(8658)) {
                                    headerHolder.syncSourceIcon.setVisibility(View.INVISIBLE);
                                }
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(8662)) {
                            headerHolder.synchronize.setChecked(excludeFromSyncListService.has(contactModel.getIdentity()));
                        }
                        if (!ListenerUtil.mutListener.listen(8663)) {
                            headerHolder.synchronize.setOnCheckedChangeListener((buttonView, isChecked) -> {
                                if (isChecked) {
                                    excludeFromSyncListService.add(contactModel.getIdentity());
                                } else {
                                    excludeFromSyncListService.remove(contactModel.getIdentity());
                                }
                            });
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(8656)) {
                            headerHolder.synchronizeContainer.setVisibility(View.GONE);
                        }
                    }
                }
                String nicknameString = contactModel.getPublicNickName();
                if (!ListenerUtil.mutListener.listen(8673)) {
                    if ((ListenerUtil.mutListener.listen(8670) ? (nicknameString != null || (ListenerUtil.mutListener.listen(8669) ? (nicknameString.length() >= 0) : (ListenerUtil.mutListener.listen(8668) ? (nicknameString.length() <= 0) : (ListenerUtil.mutListener.listen(8667) ? (nicknameString.length() < 0) : (ListenerUtil.mutListener.listen(8666) ? (nicknameString.length() != 0) : (ListenerUtil.mutListener.listen(8665) ? (nicknameString.length() == 0) : (nicknameString.length() > 0))))))) : (nicknameString != null && (ListenerUtil.mutListener.listen(8669) ? (nicknameString.length() >= 0) : (ListenerUtil.mutListener.listen(8668) ? (nicknameString.length() <= 0) : (ListenerUtil.mutListener.listen(8667) ? (nicknameString.length() < 0) : (ListenerUtil.mutListener.listen(8666) ? (nicknameString.length() != 0) : (ListenerUtil.mutListener.listen(8665) ? (nicknameString.length() == 0) : (nicknameString.length() > 0))))))))) {
                        if (!ListenerUtil.mutListener.listen(8672)) {
                            headerHolder.publicNickNameView.setText(contactModel.getPublicNickName());
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(8671)) {
                            headerHolder.nicknameContainer.setVisibility(View.GONE);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(8681)) {
                    if ((ListenerUtil.mutListener.listen(8678) ? (values.size() >= 0) : (ListenerUtil.mutListener.listen(8677) ? (values.size() <= 0) : (ListenerUtil.mutListener.listen(8676) ? (values.size() < 0) : (ListenerUtil.mutListener.listen(8675) ? (values.size() != 0) : (ListenerUtil.mutListener.listen(8674) ? (values.size() == 0) : (values.size() > 0))))))) {
                        if (!ListenerUtil.mutListener.listen(8680)) {
                            headerHolder.groupMembershipTitle.setVisibility(View.VISIBLE);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(8679)) {
                            headerHolder.groupMembershipTitle.setVisibility(View.GONE);
                        }
                    }
                }
            }
        }
    }

    @Override
    public int getItemCount() {
        return values.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (!ListenerUtil.mutListener.listen(8689)) {
            if (isPositionHeader(position))
                return TYPE_HEADER;
        }
        return TYPE_ITEM;
    }

    private boolean isPositionHeader(int position) {
        return (ListenerUtil.mutListener.listen(8694) ? (position >= 0) : (ListenerUtil.mutListener.listen(8693) ? (position <= 0) : (ListenerUtil.mutListener.listen(8692) ? (position > 0) : (ListenerUtil.mutListener.listen(8691) ? (position < 0) : (ListenerUtil.mutListener.listen(8690) ? (position != 0) : (position == 0))))));
    }

    private GroupModel getItem(int position) {
        return values.get((ListenerUtil.mutListener.listen(8698) ? (position % 1) : (ListenerUtil.mutListener.listen(8697) ? (position / 1) : (ListenerUtil.mutListener.listen(8696) ? (position * 1) : (ListenerUtil.mutListener.listen(8695) ? (position + 1) : (position - 1))))));
    }

    public void setOnClickListener(OnClickListener listener) {
        if (!ListenerUtil.mutListener.listen(8699)) {
            onClickListener = listener;
        }
    }

    public interface OnClickListener {

        void onItemClick(View v, GroupModel groupModel);

        void onVerificationInfoClick(View v);
    }
}
