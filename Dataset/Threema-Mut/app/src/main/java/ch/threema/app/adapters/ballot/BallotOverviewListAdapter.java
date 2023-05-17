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
package ch.threema.app.adapters.ballot;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.List;
import java.util.Locale;
import ch.threema.app.R;
import ch.threema.app.services.ContactService;
import ch.threema.app.services.ballot.BallotService;
import ch.threema.app.ui.AvatarListItemUtil;
import ch.threema.app.ui.CountBoxView;
import ch.threema.app.ui.listitemholder.AvatarListItemHolder;
import ch.threema.app.utils.BallotUtil;
import ch.threema.app.utils.LocaleUtil;
import ch.threema.app.utils.NameUtil;
import ch.threema.app.utils.ViewUtil;
import ch.threema.storage.models.ContactModel;
import ch.threema.storage.models.ballot.BallotModel;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 */
public class BallotOverviewListAdapter extends ArrayAdapter<BallotModel> {

    private Context context;

    private List<BallotModel> values;

    private final BallotService ballotService;

    private final ContactService contactService;

    public BallotOverviewListAdapter(Context context, List<BallotModel> values, BallotService ballotService, ContactService contactService) {
        super(context, R.layout.item_ballot_overview, values);
        if (!ListenerUtil.mutListener.listen(7241)) {
            this.context = context;
        }
        if (!ListenerUtil.mutListener.listen(7242)) {
            this.values = values;
        }
        this.ballotService = ballotService;
        this.contactService = contactService;
    }

    private static class BallotOverviewItemHolder extends AvatarListItemHolder {

        public TextView name;

        public TextView state;

        public TextView creator;

        public TextView creationDate;

        public CountBoxView countBoxView;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View itemView = convertView;
        BallotOverviewItemHolder holder;
        if (convertView == null) {
            holder = new BallotOverviewItemHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            if (!ListenerUtil.mutListener.listen(7243)) {
                itemView = inflater.inflate(R.layout.item_ballot_overview, parent, false);
            }
            if (!ListenerUtil.mutListener.listen(7244)) {
                holder.name = itemView.findViewById(R.id.ballot_name);
            }
            if (!ListenerUtil.mutListener.listen(7245)) {
                holder.state = itemView.findViewById(R.id.ballot_state);
            }
            if (!ListenerUtil.mutListener.listen(7246)) {
                holder.creationDate = itemView.findViewById(R.id.ballot_creation_date);
            }
            if (!ListenerUtil.mutListener.listen(7247)) {
                holder.creator = itemView.findViewById(R.id.ballot_creator);
            }
            if (!ListenerUtil.mutListener.listen(7248)) {
                holder.countBoxView = itemView.findViewById(R.id.ballot_updates);
            }
            if (!ListenerUtil.mutListener.listen(7249)) {
                holder.avatarView = itemView.findViewById(R.id.avatar_view);
            }
            if (!ListenerUtil.mutListener.listen(7250)) {
                itemView.setTag(holder);
            }
        } else {
            holder = (BallotOverviewItemHolder) itemView.getTag();
        }
        final BallotModel ballotModel = values.get(position);
        if (!ListenerUtil.mutListener.listen(7269)) {
            if (ballotModel != null) {
                final ContactModel contactModel = this.contactService.getByIdentity(ballotModel.getCreatorIdentity());
                if (!ListenerUtil.mutListener.listen(7251)) {
                    AvatarListItemUtil.loadAvatar(position, contactModel, null, contactService, holder);
                }
                if (!ListenerUtil.mutListener.listen(7253)) {
                    if (holder.name != null) {
                        if (!ListenerUtil.mutListener.listen(7252)) {
                            holder.name.setText(ballotModel.getName());
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(7263)) {
                    if (ballotModel.getState() == BallotModel.State.CLOSED) {
                        if (!ListenerUtil.mutListener.listen(7261)) {
                            holder.state.setText(R.string.ballot_state_closed);
                        }
                        if (!ListenerUtil.mutListener.listen(7262)) {
                            holder.state.setVisibility(View.VISIBLE);
                        }
                    } else if (ballotModel.getState() == BallotModel.State.OPEN) {
                        if (!ListenerUtil.mutListener.listen(7259)) {
                            if ((ListenerUtil.mutListener.listen(7256) ? (BallotUtil.canClose(ballotModel, contactService.getMe().getIdentity()) && BallotUtil.canViewMatrix(ballotModel, contactService.getMe().getIdentity())) : (BallotUtil.canClose(ballotModel, contactService.getMe().getIdentity()) || BallotUtil.canViewMatrix(ballotModel, contactService.getMe().getIdentity())))) {
                                if (!ListenerUtil.mutListener.listen(7258)) {
                                    holder.state.setText(String.format(Locale.US, "%d / %d", ballotService.getVotedParticipants(ballotModel.getId()).size(), ballotService.getParticipants(ballotModel.getId()).length));
                                }
                            } else {
                                if (!ListenerUtil.mutListener.listen(7257)) {
                                    holder.state.setText(R.string.ballot_secret);
                                }
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(7260)) {
                            holder.state.setVisibility(View.VISIBLE);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(7254)) {
                            holder.state.setText("");
                        }
                        if (!ListenerUtil.mutListener.listen(7255)) {
                            holder.state.setVisibility(View.GONE);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(7264)) {
                    ViewUtil.show(holder.countBoxView, false);
                }
                if (!ListenerUtil.mutListener.listen(7266)) {
                    if (holder.creationDate != null) {
                        if (!ListenerUtil.mutListener.listen(7265)) {
                            holder.creationDate.setText(LocaleUtil.formatTimeStampString(this.getContext(), ballotModel.getCreatedAt().getTime(), true));
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(7268)) {
                    if (holder.creator != null) {
                        if (!ListenerUtil.mutListener.listen(7267)) {
                            holder.creator.setText(NameUtil.getDisplayName(this.contactService.getByIdentity(ballotModel.getCreatorIdentity())));
                        }
                    }
                }
            }
        }
        return itemView;
    }
}
