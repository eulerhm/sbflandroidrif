/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
 * Copyright (c) 2017-2021 Threema GmbH
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
import android.graphics.PorterDuff;
import android.view.View;
import ch.threema.app.R;
import ch.threema.app.dialogs.GenericAlertDialog;
import ch.threema.app.fragments.ComposeMessageFragment;
import ch.threema.app.ui.SingleToast;
import ch.threema.app.ui.listitemholder.ComposeMessageHolder;
import ch.threema.app.utils.ConfigUtils;
import ch.threema.app.utils.MessageUtil;
import ch.threema.app.utils.NameUtil;
import ch.threema.app.utils.StringConversionUtil;
import ch.threema.app.utils.ViewUtil;
import ch.threema.storage.models.AbstractMessageModel;
import ch.threema.storage.models.ContactModel;
import ch.threema.storage.models.data.status.VoipStatusDataModel;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class VoipStatusDataChatAdapterDecorator extends ChatAdapterDecorator {

    public VoipStatusDataChatAdapterDecorator(Context context, AbstractMessageModel messageModel, Helper helper) {
        super(context, messageModel, helper);
    }

    @Override
    protected void configureChatMessage(final ComposeMessageHolder holder, final int position) {
        if (!ListenerUtil.mutListener.listen(8075)) {
            if (holder.controller != null) {
                if (!ListenerUtil.mutListener.listen(8073)) {
                    holder.controller.setClickable(false);
                }
                if (!ListenerUtil.mutListener.listen(8074)) {
                    holder.controller.setImageResource(R.drawable.ic_phone_locked_outline);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(8086)) {
            if (holder.bodyTextView != null) {
                MessageUtil.MessageViewElement viewElement = MessageUtil.getViewElement(this.getContext(), this.getMessageModel());
                if (!ListenerUtil.mutListener.listen(8085)) {
                    if (viewElement != null) {
                        if (!ListenerUtil.mutListener.listen(8077)) {
                            if (viewElement.placeholder != null) {
                                if (!ListenerUtil.mutListener.listen(8076)) {
                                    holder.bodyTextView.setText(viewElement.placeholder);
                                }
                            }
                        }
                        VoipStatusDataModel status = this.getMessageModel().getVoipStatusData();
                        if (!ListenerUtil.mutListener.listen(8081)) {
                            if ((ListenerUtil.mutListener.listen(8078) ? (status != null || status.getStatus() == VoipStatusDataModel.FINISHED) : (status != null && status.getStatus() == VoipStatusDataModel.FINISHED))) {
                                if (!ListenerUtil.mutListener.listen(8080)) {
                                    // Show duration
                                    if (holder.dateView != null) {
                                        if (!ListenerUtil.mutListener.listen(8079)) {
                                            this.setDatePrefix(StringConversionUtil.secondsToString(status.getDuration(), false), holder.dateView.getTextSize());
                                        }
                                    }
                                }
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(8084)) {
                            // Set and tint the phone image
                            if (ViewUtil.showAndSet(holder.attachmentImage, viewElement.icon)) {
                                if (!ListenerUtil.mutListener.listen(8083)) {
                                    if (viewElement.color != null) {
                                        if (!ListenerUtil.mutListener.listen(8082)) {
                                            holder.attachmentImage.setColorFilter(getContext().getResources().getColor(viewElement.color), PorterDuff.Mode.SRC_IN);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(8092)) {
            this.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    if (!ListenerUtil.mutListener.listen(8091)) {
                        // load the the contact
                        if (ConfigUtils.isCallsEnabled(getContext(), getPreferenceService(), getLicenseService())) {
                            ContactModel contactModel = helper.getContactService().getByIdentity(getMessageModel().getIdentity());
                            if (!ListenerUtil.mutListener.listen(8090)) {
                                if (contactModel != null) {
                                    String name = NameUtil.getDisplayNameOrNickname(contactModel, false);
                                    GenericAlertDialog dialog = GenericAlertDialog.newInstance(R.string.threema_call, String.format(getContext().getString(R.string.voip_call_confirm), name), R.string.ok, R.string.cancel);
                                    if (!ListenerUtil.mutListener.listen(8088)) {
                                        dialog.setTargetFragment(helper.getFragment(), 0);
                                    }
                                    if (!ListenerUtil.mutListener.listen(8089)) {
                                        dialog.show(helper.getFragment().getFragmentManager(), ComposeMessageFragment.DIALOG_TAG_CONFIRM_CALL);
                                    }
                                }
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(8087)) {
                                SingleToast.getInstance().showLongText(getContext().getString(R.string.voip_disabled));
                            }
                        }
                    }
                }
            }, holder.messageBlockView);
        }
    }
}
