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
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.TextView;
import java.util.List;
import java.util.Map;
import androidx.annotation.NonNull;
import ch.threema.app.R;
import ch.threema.app.ThreemaApplication;
import ch.threema.app.ui.CheckableRelativeLayout;
import ch.threema.app.ui.CountBoxView;
import ch.threema.app.utils.TestUtil;
import ch.threema.storage.models.ballot.BallotChoiceModel;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 */
public class BallotVoteListAdapter extends ArrayAdapter<BallotChoiceModel> {

    private Context context;

    private List<BallotChoiceModel> values;

    private final Map<Integer, Integer> selected;

    private final boolean readonly;

    private final boolean multipleChoice;

    private final boolean showVoting;

    public BallotVoteListAdapter(Context context, List<BallotChoiceModel> values, Map<Integer, Integer> selected, boolean readonly, boolean multipleChoice, boolean showVoting) {
        super(context, R.layout.item_ballot_choice_vote, values);
        if (!ListenerUtil.mutListener.listen(7270)) {
            this.context = context;
        }
        this.readonly = readonly;
        this.multipleChoice = multipleChoice;
        this.showVoting = showVoting;
        if (!ListenerUtil.mutListener.listen(7271)) {
            this.values = values;
        }
        this.selected = selected;
    }

    private static class BallotAdminChoiceItemHolder {

        public TextView name;

        public CountBoxView voteCount;

        public RadioButton radioButton;

        public CheckBox checkBox;

        int originalPosition;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        CheckableRelativeLayout itemView = (CheckableRelativeLayout) convertView;
        BallotAdminChoiceItemHolder holder;
        if (convertView == null) {
            holder = new BallotAdminChoiceItemHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            if (!ListenerUtil.mutListener.listen(7272)) {
                itemView = (CheckableRelativeLayout) inflater.inflate(R.layout.item_ballot_choice_vote, parent, false);
            }
            if (!ListenerUtil.mutListener.listen(7273)) {
                holder.name = itemView.findViewById(R.id.choice_name);
            }
            if (!ListenerUtil.mutListener.listen(7274)) {
                holder.voteCount = itemView.findViewById(R.id.vote_count);
            }
            if (!ListenerUtil.mutListener.listen(7275)) {
                holder.radioButton = itemView.findViewById(R.id.choice_radio);
            }
            if (!ListenerUtil.mutListener.listen(7276)) {
                holder.checkBox = itemView.findViewById(R.id.choice_checkbox);
            }
            if (!ListenerUtil.mutListener.listen(7277)) {
                itemView.setTag(holder);
            }
        } else {
            holder = (BallotAdminChoiceItemHolder) itemView.getTag();
        }
        if (!ListenerUtil.mutListener.listen(7278)) {
            itemView.setOnCheckedChangeListener(null);
        }
        final BallotChoiceModel choiceModel = values.get(position);
        if (!ListenerUtil.mutListener.listen(7279)) {
            holder.originalPosition = position;
        }
        if (!ListenerUtil.mutListener.listen(7289)) {
            if (choiceModel != null) {
                if (!ListenerUtil.mutListener.listen(7281)) {
                    if (holder.name != null) {
                        if (!ListenerUtil.mutListener.listen(7280)) {
                            holder.name.setText(choiceModel.getName());
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(7287)) {
                    if (holder.voteCount != null) {
                        if (!ListenerUtil.mutListener.listen(7282)) {
                            holder.voteCount.setVisibility(this.showVoting ? View.VISIBLE : View.GONE);
                        }
                        if (!ListenerUtil.mutListener.listen(7286)) {
                            if (this.showVoting) {
                                long c = 0;
                                try {
                                    if (!ListenerUtil.mutListener.listen(7283)) {
                                        c = ThreemaApplication.getServiceManager().getBallotService().getVotingCount(choiceModel);
                                    }
                                } catch (Exception ignored) {
                                }
                                if (!ListenerUtil.mutListener.listen(7284)) {
                                    holder.voteCount.setText(String.valueOf(c));
                                }
                                if (!ListenerUtil.mutListener.listen(7285)) {
                                    holder.voteCount.setVisibility(View.VISIBLE);
                                }
                            }
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(7288)) {
                    itemView.setChecked(this.isSelected(choiceModel));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(7294)) {
            if (TestUtil.required(holder.checkBox, holder.radioButton)) {
                if (!ListenerUtil.mutListener.listen(7290)) {
                    holder.radioButton.setVisibility(!this.multipleChoice ? View.VISIBLE : View.GONE);
                }
                if (!ListenerUtil.mutListener.listen(7291)) {
                    holder.radioButton.setEnabled(!this.readonly);
                }
                if (!ListenerUtil.mutListener.listen(7292)) {
                    holder.checkBox.setVisibility(this.multipleChoice ? View.VISIBLE : View.GONE);
                }
                if (!ListenerUtil.mutListener.listen(7293)) {
                    holder.checkBox.setEnabled(!this.readonly);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(7296)) {
            if (!this.readonly) {
                if (!ListenerUtil.mutListener.listen(7295)) {
                    itemView.setOnCheckedChangeListener((checkableView, isChecked) -> {
                        select(values.get(((BallotAdminChoiceItemHolder) checkableView.getTag()).originalPosition), isChecked);
                    });
                }
            }
        }
        return itemView;
    }

    public Map<Integer, Integer> getSelectedChoices() {
        return this.selected;
    }

    public boolean isSelected(final BallotChoiceModel model) {
        synchronized (this.selected) {
            int k = model.getId();
            return (ListenerUtil.mutListener.listen(7302) ? (selected.containsKey(k) || (ListenerUtil.mutListener.listen(7301) ? (selected.get(k) >= 1) : (ListenerUtil.mutListener.listen(7300) ? (selected.get(k) <= 1) : (ListenerUtil.mutListener.listen(7299) ? (selected.get(k) > 1) : (ListenerUtil.mutListener.listen(7298) ? (selected.get(k) < 1) : (ListenerUtil.mutListener.listen(7297) ? (selected.get(k) != 1) : (selected.get(k) == 1))))))) : (selected.containsKey(k) && (ListenerUtil.mutListener.listen(7301) ? (selected.get(k) >= 1) : (ListenerUtil.mutListener.listen(7300) ? (selected.get(k) <= 1) : (ListenerUtil.mutListener.listen(7299) ? (selected.get(k) > 1) : (ListenerUtil.mutListener.listen(7298) ? (selected.get(k) < 1) : (ListenerUtil.mutListener.listen(7297) ? (selected.get(k) != 1) : (selected.get(k) == 1))))))));
        }
    }

    public void select(final BallotChoiceModel model, boolean select) {
        synchronized (this.selected) {
            int id = model.getId();
            if (!ListenerUtil.mutListener.listen(7307)) {
                if (!this.multipleChoice) {
                    if (!ListenerUtil.mutListener.listen(7304)) {
                        this.selected.clear();
                    }
                    if (!ListenerUtil.mutListener.listen(7305)) {
                        this.selected.put(id, 1);
                    }
                    if (!ListenerUtil.mutListener.listen(7306)) {
                        notifyDataSetChanged();
                    }
                } else {
                    if (!ListenerUtil.mutListener.listen(7303)) {
                        this.selected.put(id, (select ? 1 : 0));
                    }
                }
            }
        }
    }
}
