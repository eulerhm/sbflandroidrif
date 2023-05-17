/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
 * Copyright (c) 2019-2021 Threema GmbH
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
import android.os.Bundle;
import android.text.format.Formatter;
import android.view.View;
import android.widget.TextView;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import java.util.Date;
import androidx.annotation.NonNull;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialog;
import ch.threema.app.R;
import ch.threema.app.ThreemaApplication;
import ch.threema.app.services.MessageService;
import ch.threema.app.utils.LocaleUtil;
import ch.threema.app.utils.TestUtil;
import ch.threema.app.utils.TextUtil;
import ch.threema.storage.models.AbstractMessageModel;
import ch.threema.storage.models.GroupMessageModel;
import ch.threema.storage.models.MessageState;
import ch.threema.storage.models.MessageType;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class MessageDetailDialog extends ThreemaDialogFragment {

    private AlertDialog alertDialog;

    private Activity activity;

    public static MessageDetailDialog newInstance(@StringRes int title, int messageId, String type) {
        MessageDetailDialog dialog = new MessageDetailDialog();
        Bundle args = new Bundle();
        if (!ListenerUtil.mutListener.listen(13591)) {
            args.putInt("title", title);
        }
        if (!ListenerUtil.mutListener.listen(13592)) {
            args.putInt("messageId", messageId);
        }
        if (!ListenerUtil.mutListener.listen(13593)) {
            args.putString("messageType", type);
        }
        if (!ListenerUtil.mutListener.listen(13594)) {
            dialog.setArguments(args);
        }
        return dialog;
    }

    @Override
    public void onAttach(@NonNull Activity activity) {
        if (!ListenerUtil.mutListener.listen(13595)) {
            super.onAttach(activity);
        }
        if (!ListenerUtil.mutListener.listen(13596)) {
            this.activity = activity;
        }
    }

    @NonNull
    @Override
    public AppCompatDialog onCreateDialog(Bundle savedInstanceState) {
        MessageService messageService = null;
        try {
            if (!ListenerUtil.mutListener.listen(13597)) {
                messageService = ThreemaApplication.getServiceManager().getMessageService();
            }
        } catch (Exception e) {
        }
        if (!ListenerUtil.mutListener.listen(13666)) {
            if (messageService != null) {
                @StringRes
                int title = getArguments().getInt("title");
                int messageId = getArguments().getInt("messageId");
                String messageType = getArguments().getString("messageType");
                AbstractMessageModel messageModel = messageService.getMessageModelFromId(messageId, messageType);
                final View dialogView = activity.getLayoutInflater().inflate(R.layout.dialog_message_detail, null);
                final TextView createdText = dialogView.findViewById(R.id.created_text);
                final TextView createdDate = dialogView.findViewById(R.id.created_date);
                final TextView postedText = dialogView.findViewById(R.id.posted_text);
                final TextView postedDate = dialogView.findViewById(R.id.posted_date);
                final TextView modifiedText = dialogView.findViewById(R.id.modified_text);
                final TextView modifiedDate = dialogView.findViewById(R.id.modified_date);
                final TextView messageIdText = dialogView.findViewById(R.id.messageid_text);
                final TextView messageIdDate = dialogView.findViewById(R.id.messageid_date);
                final TextView mimeTypeText = dialogView.findViewById(R.id.filetype_text);
                final TextView mimeTypeMime = dialogView.findViewById(R.id.filetype_mime);
                final TextView fileSizeText = dialogView.findViewById(R.id.filesize_text);
                final TextView fileSizeData = dialogView.findViewById(R.id.filesize_data);
                MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(getActivity(), getTheme());
                if (!ListenerUtil.mutListener.listen(13598)) {
                    builder.setView(dialogView);
                }
                if (!ListenerUtil.mutListener.listen(13605)) {
                    if ((ListenerUtil.mutListener.listen(13603) ? (title >= -1) : (ListenerUtil.mutListener.listen(13602) ? (title <= -1) : (ListenerUtil.mutListener.listen(13601) ? (title > -1) : (ListenerUtil.mutListener.listen(13600) ? (title < -1) : (ListenerUtil.mutListener.listen(13599) ? (title == -1) : (title != -1))))))) {
                        if (!ListenerUtil.mutListener.listen(13604)) {
                            builder.setTitle(title);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(13606)) {
                    builder.setPositiveButton(getString(R.string.ok), null);
                }
                @StringRes
                int stateResource = getStateTextRes(messageModel);
                MessageState messageState = messageModel.getState();
                boolean showPostedAt = (ListenerUtil.mutListener.listen(13610) ? (((ListenerUtil.mutListener.listen(13609) ? ((ListenerUtil.mutListener.listen(13608) ? ((ListenerUtil.mutListener.listen(13607) ? (messageState != null || messageState != MessageState.SENDING) : (messageState != null && messageState != MessageState.SENDING)) || messageState != MessageState.SENDFAILED) : ((ListenerUtil.mutListener.listen(13607) ? (messageState != null || messageState != MessageState.SENDING) : (messageState != null && messageState != MessageState.SENDING)) && messageState != MessageState.SENDFAILED)) || messageState != MessageState.PENDING) : ((ListenerUtil.mutListener.listen(13608) ? ((ListenerUtil.mutListener.listen(13607) ? (messageState != null || messageState != MessageState.SENDING) : (messageState != null && messageState != MessageState.SENDING)) || messageState != MessageState.SENDFAILED) : ((ListenerUtil.mutListener.listen(13607) ? (messageState != null || messageState != MessageState.SENDING) : (messageState != null && messageState != MessageState.SENDING)) && messageState != MessageState.SENDFAILED)) && messageState != MessageState.PENDING))) && messageModel.getType() == MessageType.BALLOT) : (((ListenerUtil.mutListener.listen(13609) ? ((ListenerUtil.mutListener.listen(13608) ? ((ListenerUtil.mutListener.listen(13607) ? (messageState != null || messageState != MessageState.SENDING) : (messageState != null && messageState != MessageState.SENDING)) || messageState != MessageState.SENDFAILED) : ((ListenerUtil.mutListener.listen(13607) ? (messageState != null || messageState != MessageState.SENDING) : (messageState != null && messageState != MessageState.SENDING)) && messageState != MessageState.SENDFAILED)) || messageState != MessageState.PENDING) : ((ListenerUtil.mutListener.listen(13608) ? ((ListenerUtil.mutListener.listen(13607) ? (messageState != null || messageState != MessageState.SENDING) : (messageState != null && messageState != MessageState.SENDING)) || messageState != MessageState.SENDFAILED) : ((ListenerUtil.mutListener.listen(13607) ? (messageState != null || messageState != MessageState.SENDING) : (messageState != null && messageState != MessageState.SENDING)) && messageState != MessageState.SENDFAILED)) && messageState != MessageState.PENDING))) || messageModel.getType() == MessageType.BALLOT));
                if (!ListenerUtil.mutListener.listen(13664)) {
                    if (messageModel.isStatusMessage()) {
                        if (!ListenerUtil.mutListener.listen(13663)) {
                            createdDate.setText(LocaleUtil.formatTimeStampStringAbsolute(getContext(), messageModel.getCreatedAt().getTime()));
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(13643)) {
                            if (messageModel.isOutbox()) {
                                if (!ListenerUtil.mutListener.listen(13630)) {
                                    // outgoing msgs
                                    if (messageModel.getCreatedAt() != null) {
                                        if (!ListenerUtil.mutListener.listen(13629)) {
                                            createdDate.setText(LocaleUtil.formatTimeStampStringAbsolute(getContext(), messageModel.getCreatedAt().getTime()));
                                        }
                                    } else {
                                        if (!ListenerUtil.mutListener.listen(13627)) {
                                            createdText.setVisibility(View.GONE);
                                        }
                                        if (!ListenerUtil.mutListener.listen(13628)) {
                                            createdDate.setVisibility(View.GONE);
                                        }
                                    }
                                }
                                if (!ListenerUtil.mutListener.listen(13635)) {
                                    if ((ListenerUtil.mutListener.listen(13631) ? (showPostedAt || messageModel.getPostedAt() != null) : (showPostedAt && messageModel.getPostedAt() != null))) {
                                        if (!ListenerUtil.mutListener.listen(13632)) {
                                            postedDate.setText(LocaleUtil.formatTimeStampStringAbsolute(getContext(), messageModel.getPostedAt().getTime()));
                                        }
                                        if (!ListenerUtil.mutListener.listen(13633)) {
                                            postedText.setVisibility(View.VISIBLE);
                                        }
                                        if (!ListenerUtil.mutListener.listen(13634)) {
                                            postedDate.setVisibility(View.VISIBLE);
                                        }
                                    }
                                }
                                if (!ListenerUtil.mutListener.listen(13642)) {
                                    if ((ListenerUtil.mutListener.listen(13637) ? (messageState != MessageState.SENT || !((ListenerUtil.mutListener.listen(13636) ? (messageModel.getType() == MessageType.BALLOT || messageModel instanceof GroupMessageModel) : (messageModel.getType() == MessageType.BALLOT && messageModel instanceof GroupMessageModel)))) : (messageState != MessageState.SENT && !((ListenerUtil.mutListener.listen(13636) ? (messageModel.getType() == MessageType.BALLOT || messageModel instanceof GroupMessageModel) : (messageModel.getType() == MessageType.BALLOT && messageModel instanceof GroupMessageModel)))))) {
                                        Date modifiedAt = messageModel.getModifiedAt();
                                        if (!ListenerUtil.mutListener.listen(13638)) {
                                            modifiedText.setText(TextUtil.capitalize(getString(stateResource)));
                                        }
                                        if (!ListenerUtil.mutListener.listen(13639)) {
                                            modifiedDate.setText(modifiedAt != null ? LocaleUtil.formatTimeStampStringAbsolute(getContext(), messageModel.getModifiedAt().getTime()) : "");
                                        }
                                        if (!ListenerUtil.mutListener.listen(13640)) {
                                            modifiedText.setVisibility(View.VISIBLE);
                                        }
                                        if (!ListenerUtil.mutListener.listen(13641)) {
                                            modifiedDate.setVisibility(View.VISIBLE);
                                        }
                                    }
                                }
                            } else {
                                if (!ListenerUtil.mutListener.listen(13615)) {
                                    // incoming msgs
                                    if (messageModel.getPostedAt() != null) {
                                        if (!ListenerUtil.mutListener.listen(13613)) {
                                            createdText.setText(R.string.state_dialog_posted);
                                        }
                                        if (!ListenerUtil.mutListener.listen(13614)) {
                                            createdDate.setText(LocaleUtil.formatTimeStampStringAbsolute(getContext(), messageModel.getPostedAt().getTime()));
                                        }
                                    } else {
                                        if (!ListenerUtil.mutListener.listen(13611)) {
                                            createdText.setVisibility(View.GONE);
                                        }
                                        if (!ListenerUtil.mutListener.listen(13612)) {
                                            createdDate.setVisibility(View.GONE);
                                        }
                                    }
                                }
                                if (!ListenerUtil.mutListener.listen(13620)) {
                                    if (messageModel.getCreatedAt() != null) {
                                        if (!ListenerUtil.mutListener.listen(13616)) {
                                            postedText.setText(R.string.state_dialog_received);
                                        }
                                        if (!ListenerUtil.mutListener.listen(13617)) {
                                            postedDate.setText(LocaleUtil.formatTimeStampStringAbsolute(getContext(), messageModel.getCreatedAt().getTime()));
                                        }
                                        if (!ListenerUtil.mutListener.listen(13618)) {
                                            postedText.setVisibility(View.VISIBLE);
                                        }
                                        if (!ListenerUtil.mutListener.listen(13619)) {
                                            postedDate.setVisibility(View.VISIBLE);
                                        }
                                    }
                                }
                                if (!ListenerUtil.mutListener.listen(13626)) {
                                    if ((ListenerUtil.mutListener.listen(13621) ? (messageModel.getModifiedAt() != null || messageState != MessageState.READ) : (messageModel.getModifiedAt() != null && messageState != MessageState.READ))) {
                                        if (!ListenerUtil.mutListener.listen(13622)) {
                                            modifiedText.setText(TextUtil.capitalize(getString(R.string.state_read)));
                                        }
                                        if (!ListenerUtil.mutListener.listen(13623)) {
                                            modifiedDate.setText(LocaleUtil.formatTimeStampStringAbsolute(getContext(), messageModel.getModifiedAt().getTime()));
                                        }
                                        if (!ListenerUtil.mutListener.listen(13624)) {
                                            modifiedText.setVisibility(View.VISIBLE);
                                        }
                                        if (!ListenerUtil.mutListener.listen(13625)) {
                                            modifiedDate.setVisibility(View.VISIBLE);
                                        }
                                    }
                                }
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(13658)) {
                            if ((ListenerUtil.mutListener.listen(13644) ? (messageModel.getType() == MessageType.FILE || messageModel.getFileData() != null) : (messageModel.getType() == MessageType.FILE && messageModel.getFileData() != null))) {
                                if (!ListenerUtil.mutListener.listen(13648)) {
                                    if (!TestUtil.empty(messageModel.getFileData().getMimeType())) {
                                        if (!ListenerUtil.mutListener.listen(13645)) {
                                            mimeTypeMime.setText(messageModel.getFileData().getMimeType());
                                        }
                                        if (!ListenerUtil.mutListener.listen(13646)) {
                                            mimeTypeMime.setVisibility(View.VISIBLE);
                                        }
                                        if (!ListenerUtil.mutListener.listen(13647)) {
                                            mimeTypeText.setVisibility(View.VISIBLE);
                                        }
                                    }
                                }
                                if (!ListenerUtil.mutListener.listen(13657)) {
                                    if ((ListenerUtil.mutListener.listen(13653) ? (messageModel.getFileData().getFileSize() >= 0) : (ListenerUtil.mutListener.listen(13652) ? (messageModel.getFileData().getFileSize() <= 0) : (ListenerUtil.mutListener.listen(13651) ? (messageModel.getFileData().getFileSize() < 0) : (ListenerUtil.mutListener.listen(13650) ? (messageModel.getFileData().getFileSize() != 0) : (ListenerUtil.mutListener.listen(13649) ? (messageModel.getFileData().getFileSize() == 0) : (messageModel.getFileData().getFileSize() > 0))))))) {
                                        if (!ListenerUtil.mutListener.listen(13654)) {
                                            fileSizeData.setText(Formatter.formatShortFileSize(getContext(), messageModel.getFileData().getFileSize()));
                                        }
                                        if (!ListenerUtil.mutListener.listen(13655)) {
                                            fileSizeData.setVisibility(View.VISIBLE);
                                        }
                                        if (!ListenerUtil.mutListener.listen(13656)) {
                                            fileSizeText.setVisibility(View.VISIBLE);
                                        }
                                    }
                                }
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(13662)) {
                            if (!TestUtil.empty(messageModel.getApiMessageId())) {
                                if (!ListenerUtil.mutListener.listen(13659)) {
                                    messageIdDate.setText(messageModel.getApiMessageId());
                                }
                                if (!ListenerUtil.mutListener.listen(13660)) {
                                    messageIdDate.setVisibility(View.VISIBLE);
                                }
                                if (!ListenerUtil.mutListener.listen(13661)) {
                                    messageIdText.setVisibility(View.VISIBLE);
                                }
                            }
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(13665)) {
                    alertDialog = builder.create();
                }
                return alertDialog;
            }
        }
        return null;
    }

    @StringRes
    private int getStateTextRes(AbstractMessageModel messageModel) {
        int stateResource = 0;
        if (!ListenerUtil.mutListener.listen(13679)) {
            if (messageModel.getState() != null) {
                if (!ListenerUtil.mutListener.listen(13678)) {
                    switch(messageModel.getState()) {
                        case READ:
                            if (!ListenerUtil.mutListener.listen(13668)) {
                                stateResource = R.string.state_read;
                            }
                            break;
                        case USERACK:
                            if (!ListenerUtil.mutListener.listen(13669)) {
                                stateResource = R.string.state_ack;
                            }
                            break;
                        case USERDEC:
                            if (!ListenerUtil.mutListener.listen(13670)) {
                                stateResource = R.string.state_dec;
                            }
                            break;
                        case DELIVERED:
                            if (!ListenerUtil.mutListener.listen(13671)) {
                                stateResource = R.string.state_delivered;
                            }
                            break;
                        case SENT:
                            if (!ListenerUtil.mutListener.listen(13672)) {
                                stateResource = R.string.state_sent;
                            }
                            break;
                        case SENDING:
                            if (!ListenerUtil.mutListener.listen(13673)) {
                                stateResource = R.string.state_sending;
                            }
                            break;
                        case SENDFAILED:
                            if (!ListenerUtil.mutListener.listen(13674)) {
                                stateResource = R.string.state_failed;
                            }
                            break;
                        case PENDING:
                            if (!ListenerUtil.mutListener.listen(13675)) {
                                stateResource = R.string.state_pending;
                            }
                            break;
                        case TRANSCODING:
                            if (!ListenerUtil.mutListener.listen(13676)) {
                                stateResource = R.string.state_transcoding;
                            }
                            break;
                        case CONSUMED:
                            if (!ListenerUtil.mutListener.listen(13677)) {
                                stateResource = R.string.listened_to;
                            }
                            break;
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(13667)) {
                    stateResource = R.string.state_sent;
                }
            }
        }
        return stateResource;
    }
}
