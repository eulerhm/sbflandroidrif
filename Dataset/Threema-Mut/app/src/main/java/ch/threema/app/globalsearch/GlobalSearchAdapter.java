/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
 * Copyright (c) 2020-2021 Threema GmbH
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
package ch.threema.app.globalsearch;

import android.content.Context;
import android.text.SpannableStringBuilder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import ch.threema.app.R;
import ch.threema.app.ThreemaApplication;
import ch.threema.app.emojis.EmojiImageSpan;
import ch.threema.app.emojis.EmojiMarkupUtil;
import ch.threema.app.services.ContactService;
import ch.threema.app.services.GroupService;
import ch.threema.app.ui.AvatarListItemUtil;
import ch.threema.app.ui.AvatarView;
import ch.threema.app.ui.listitemholder.AvatarListItemHolder;
import ch.threema.app.utils.LocaleUtil;
import ch.threema.app.utils.NameUtil;
import ch.threema.app.utils.TestUtil;
import ch.threema.app.utils.TextUtil;
import ch.threema.storage.models.AbstractMessageModel;
import ch.threema.storage.models.ContactModel;
import ch.threema.storage.models.GroupMessageModel;
import ch.threema.storage.models.GroupModel;
import ch.threema.storage.models.data.LocationDataModel;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class GlobalSearchAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final Logger logger = LoggerFactory.getLogger(GlobalSearchAdapter.class);

    private static final String FLOW_CHARACTER = "\u25BA\uFE0E";

    private GroupService groupService;

    private ContactService contactService;

    private final Context context;

    private OnClickItemListener onClickItemListener;

    private String queryString;

    // Cached copy of AbstractMessageModels
    private List<AbstractMessageModel> messageModels;

    private static class ItemHolder extends RecyclerView.ViewHolder {

        private final TextView titleView;

        private final TextView dateView;

        private final TextView snippetView;

        private final AvatarView avatarView;

        AvatarListItemHolder avatarListItemHolder;

        private ItemHolder(final View itemView) {
            super(itemView);
            titleView = itemView.findViewById(R.id.name);
            dateView = itemView.findViewById(R.id.date);
            snippetView = itemView.findViewById(R.id.snippet);
            avatarView = itemView.findViewById(R.id.avatar_view);
            if (!ListenerUtil.mutListener.listen(28276)) {
                avatarListItemHolder = new AvatarListItemHolder();
            }
            avatarListItemHolder.avatarView = avatarView;
            if (!ListenerUtil.mutListener.listen(28277)) {
                avatarListItemHolder.avatarLoadingAsyncTask = null;
            }
        }
    }

    GlobalSearchAdapter(Context context) {
        this.context = context;
        try {
            if (!ListenerUtil.mutListener.listen(28279)) {
                this.groupService = ThreemaApplication.getServiceManager().getGroupService();
            }
            if (!ListenerUtil.mutListener.listen(28280)) {
                this.contactService = ThreemaApplication.getServiceManager().getContactService();
            }
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(28278)) {
                logger.error("Unable to get Services", e);
            }
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_global_search, parent, false);
        return new ItemHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ItemHolder itemHolder = (ItemHolder) holder;
        if (!ListenerUtil.mutListener.listen(28293)) {
            if (messageModels != null) {
                AbstractMessageModel current = getItem(position);
                if (!ListenerUtil.mutListener.listen(28288)) {
                    if (current instanceof GroupMessageModel) {
                        final ContactModel contactModel = current.isOutbox() ? this.contactService.getMe() : this.contactService.getByIdentity(current.getIdentity());
                        final GroupModel groupModel = groupService.getById(((GroupMessageModel) current).getGroupId());
                        if (!ListenerUtil.mutListener.listen(28286)) {
                            AvatarListItemUtil.loadAvatar(position, groupModel, null, groupService, itemHolder.avatarListItemHolder);
                        }
                        String groupName = NameUtil.getDisplayName(groupModel, groupService);
                        if (!ListenerUtil.mutListener.listen(28287)) {
                            itemHolder.titleView.setText(String.format("%s %s %s", NameUtil.getDisplayNameOrNickname(contactModel, true), FLOW_CHARACTER, groupName));
                        }
                    } else {
                        final ContactModel contactModel = this.contactService.getByIdentity(current.getIdentity());
                        if (!ListenerUtil.mutListener.listen(28284)) {
                            AvatarListItemUtil.loadAvatar(position, current.isOutbox() ? contactService.getMe() : contactModel, null, contactService, itemHolder.avatarListItemHolder);
                        }
                        String name = NameUtil.getDisplayNameOrNickname(context, current, contactService);
                        if (!ListenerUtil.mutListener.listen(28285)) {
                            itemHolder.titleView.setText(current.isOutbox() ? name + " " + FLOW_CHARACTER + " " + NameUtil.getDisplayNameOrNickname(contactModel, true) : name);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(28289)) {
                    itemHolder.dateView.setText(LocaleUtil.formatDateRelative(context, current.getCreatedAt().getTime()));
                }
                if (!ListenerUtil.mutListener.listen(28290)) {
                    setSnippetToTextView(current, itemHolder);
                }
                if (!ListenerUtil.mutListener.listen(28292)) {
                    if (this.onClickItemListener != null) {
                        if (!ListenerUtil.mutListener.listen(28291)) {
                            itemHolder.itemView.setOnClickListener(v -> onClickItemListener.onClick(current, itemHolder.itemView));
                        }
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(28281)) {
                    // Covers the case of data not being ready yet.
                    itemHolder.titleView.setText("No data");
                }
                if (!ListenerUtil.mutListener.listen(28282)) {
                    itemHolder.dateView.setText("");
                }
                if (!ListenerUtil.mutListener.listen(28283)) {
                    itemHolder.snippetView.setText("");
                }
            }
        }
    }

    /**
     *  Returns a snippet containing the first occurrence of needle in fullText
     *  Splits text on emoji boundary
     *  Note: the match is case-insensitive
     *  @param fullText Full text
     *  @param needle Text to search for
     *  @param holder ItemHolder containing a textview
     *  @return Snippet containing the match with a trailing ellipsis if the match is located beyond the first 17 characters
     */
    private String getSnippet(@NonNull String fullText, @NonNull String needle, ItemHolder holder) {
        int firstMatch = fullText.toLowerCase().indexOf(needle);
        if (!ListenerUtil.mutListener.listen(28333)) {
            if ((ListenerUtil.mutListener.listen(28298) ? (firstMatch >= 17) : (ListenerUtil.mutListener.listen(28297) ? (firstMatch <= 17) : (ListenerUtil.mutListener.listen(28296) ? (firstMatch < 17) : (ListenerUtil.mutListener.listen(28295) ? (firstMatch != 17) : (ListenerUtil.mutListener.listen(28294) ? (firstMatch == 17) : (firstMatch > 17))))))) {
                int until = (ListenerUtil.mutListener.listen(28303) ? (firstMatch >= 20) : (ListenerUtil.mutListener.listen(28302) ? (firstMatch <= 20) : (ListenerUtil.mutListener.listen(28301) ? (firstMatch < 20) : (ListenerUtil.mutListener.listen(28300) ? (firstMatch != 20) : (ListenerUtil.mutListener.listen(28299) ? (firstMatch == 20) : (firstMatch > 20)))))) ? (ListenerUtil.mutListener.listen(28307) ? (firstMatch % 20) : (ListenerUtil.mutListener.listen(28306) ? (firstMatch / 20) : (ListenerUtil.mutListener.listen(28305) ? (firstMatch * 20) : (ListenerUtil.mutListener.listen(28304) ? (firstMatch + 20) : (firstMatch - 20))))) : 0;
                if (!ListenerUtil.mutListener.listen(28318)) {
                    {
                        long _loopCounter177 = 0;
                        for (int i = firstMatch; (ListenerUtil.mutListener.listen(28317) ? (i >= until) : (ListenerUtil.mutListener.listen(28316) ? (i <= until) : (ListenerUtil.mutListener.listen(28315) ? (i < until) : (ListenerUtil.mutListener.listen(28314) ? (i != until) : (ListenerUtil.mutListener.listen(28313) ? (i == until) : (i > until)))))); i--) {
                            ListenerUtil.loopListener.listen("_loopCounter177", ++_loopCounter177);
                            if (!ListenerUtil.mutListener.listen(28312)) {
                                if (Character.isWhitespace(fullText.charAt(i))) {
                                    return "…" + fullText.substring((ListenerUtil.mutListener.listen(28311) ? (i % 1) : (ListenerUtil.mutListener.listen(28310) ? (i / 1) : (ListenerUtil.mutListener.listen(28309) ? (i * 1) : (ListenerUtil.mutListener.listen(28308) ? (i - 1) : (i + 1))))));
                                }
                            }
                        }
                    }
                }
                SpannableStringBuilder emojified = (SpannableStringBuilder) EmojiMarkupUtil.getInstance().addTextSpans(context, fullText, holder.snippetView, true);
                int transitionStart = emojified.nextSpanTransition((ListenerUtil.mutListener.listen(28322) ? (firstMatch % 17) : (ListenerUtil.mutListener.listen(28321) ? (firstMatch / 17) : (ListenerUtil.mutListener.listen(28320) ? (firstMatch * 17) : (ListenerUtil.mutListener.listen(28319) ? (firstMatch + 17) : (firstMatch - 17))))), firstMatch, EmojiImageSpan.class);
                if (!ListenerUtil.mutListener.listen(28332)) {
                    if ((ListenerUtil.mutListener.listen(28327) ? (transitionStart >= firstMatch) : (ListenerUtil.mutListener.listen(28326) ? (transitionStart <= firstMatch) : (ListenerUtil.mutListener.listen(28325) ? (transitionStart > firstMatch) : (ListenerUtil.mutListener.listen(28324) ? (transitionStart < firstMatch) : (ListenerUtil.mutListener.listen(28323) ? (transitionStart != firstMatch) : (transitionStart == firstMatch))))))) {
                        // there are no spans here
                        return "…" + emojified.subSequence((ListenerUtil.mutListener.listen(28331) ? (firstMatch % 17) : (ListenerUtil.mutListener.listen(28330) ? (firstMatch / 17) : (ListenerUtil.mutListener.listen(28329) ? (firstMatch * 17) : (ListenerUtil.mutListener.listen(28328) ? (firstMatch + 17) : (firstMatch - 17))))), emojified.length()).toString();
                    } else {
                        return "…" + emojified.subSequence(transitionStart, emojified.length()).toString();
                    }
                }
            }
        }
        return fullText;
    }

    void setMessageModels(List<AbstractMessageModel> messageModels) {
        if (!ListenerUtil.mutListener.listen(28334)) {
            this.messageModels = messageModels;
        }
        if (!ListenerUtil.mutListener.listen(28335)) {
            notifyDataSetChanged();
        }
    }

    private void setSnippetToTextView(AbstractMessageModel current, ItemHolder itemHolder) {
        String snippetText = null;
        if (!ListenerUtil.mutListener.listen(28360)) {
            if (!TestUtil.empty(this.queryString)) {
                if (!ListenerUtil.mutListener.listen(28359)) {
                    switch(current.getType()) {
                        case FILE:
                        // fallthrough
                        case IMAGE:
                            if (!ListenerUtil.mutListener.listen(28337)) {
                                if (!TestUtil.empty(current.getCaption())) {
                                    if (!ListenerUtil.mutListener.listen(28336)) {
                                        snippetText = getSnippet(current.getCaption(), this.queryString, itemHolder);
                                    }
                                }
                            }
                            break;
                        case TEXT:
                        // fallthrough
                        case BALLOT:
                            if (!ListenerUtil.mutListener.listen(28339)) {
                                if (!TestUtil.empty(current.getBody())) {
                                    if (!ListenerUtil.mutListener.listen(28338)) {
                                        snippetText = getSnippet(current.getBody(), this.queryString, itemHolder);
                                    }
                                }
                            }
                            break;
                        case LOCATION:
                            final LocationDataModel location = current.getLocationData();
                            if (!ListenerUtil.mutListener.listen(28358)) {
                                if (location != null) {
                                    StringBuilder locationStringBuilder = new StringBuilder();
                                    if (!ListenerUtil.mutListener.listen(28341)) {
                                        if (!TestUtil.empty(location.getPoi())) {
                                            if (!ListenerUtil.mutListener.listen(28340)) {
                                                locationStringBuilder.append(location.getPoi());
                                            }
                                        }
                                    }
                                    if (!ListenerUtil.mutListener.listen(28350)) {
                                        if (!TestUtil.empty(location.getAddress())) {
                                            if (!ListenerUtil.mutListener.listen(28348)) {
                                                if ((ListenerUtil.mutListener.listen(28346) ? (locationStringBuilder.length() >= 0) : (ListenerUtil.mutListener.listen(28345) ? (locationStringBuilder.length() <= 0) : (ListenerUtil.mutListener.listen(28344) ? (locationStringBuilder.length() < 0) : (ListenerUtil.mutListener.listen(28343) ? (locationStringBuilder.length() != 0) : (ListenerUtil.mutListener.listen(28342) ? (locationStringBuilder.length() == 0) : (locationStringBuilder.length() > 0))))))) {
                                                    if (!ListenerUtil.mutListener.listen(28347)) {
                                                        locationStringBuilder.append(" - ");
                                                    }
                                                }
                                            }
                                            if (!ListenerUtil.mutListener.listen(28349)) {
                                                locationStringBuilder.append(location.getAddress());
                                            }
                                        }
                                    }
                                    if (!ListenerUtil.mutListener.listen(28357)) {
                                        if ((ListenerUtil.mutListener.listen(28355) ? (locationStringBuilder.length() >= 0) : (ListenerUtil.mutListener.listen(28354) ? (locationStringBuilder.length() <= 0) : (ListenerUtil.mutListener.listen(28353) ? (locationStringBuilder.length() < 0) : (ListenerUtil.mutListener.listen(28352) ? (locationStringBuilder.length() != 0) : (ListenerUtil.mutListener.listen(28351) ? (locationStringBuilder.length() == 0) : (locationStringBuilder.length() > 0))))))) {
                                            if (!ListenerUtil.mutListener.listen(28356)) {
                                                snippetText = getSnippet(locationStringBuilder.toString(), this.queryString, itemHolder);
                                            }
                                        }
                                    }
                                }
                            }
                            break;
                        default:
                            // Audio and Video Messages don't have text or captions
                            break;
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(28363)) {
            if (snippetText != null) {
                if (!ListenerUtil.mutListener.listen(28362)) {
                    itemHolder.snippetView.setText(TextUtil.highlightMatches(context, snippetText, this.queryString, true, false));
                }
            } else {
                if (!ListenerUtil.mutListener.listen(28361)) {
                    itemHolder.snippetView.setText(null);
                }
            }
        }
    }

    private AbstractMessageModel getItem(int position) {
        return messageModels.get(position);
    }

    // messageModels has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (messageModels != null) {
            return messageModels.size();
        } else {
            return 0;
        }
    }

    void setOnClickItemListener(OnClickItemListener onClickItemListener) {
        if (!ListenerUtil.mutListener.listen(28364)) {
            this.onClickItemListener = onClickItemListener;
        }
    }

    public void onQueryChanged(String queryText) {
        if (!ListenerUtil.mutListener.listen(28365)) {
            this.queryString = queryText;
        }
    }

    public interface OnClickItemListener {

        void onClick(AbstractMessageModel messageModel, View view);
    }
}
