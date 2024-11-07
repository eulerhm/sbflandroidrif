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
package ch.threema.app.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.SectionIndexer;
import android.widget.TextView;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.text.Collator;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import androidx.annotation.NonNull;
import ch.threema.app.R;
import ch.threema.app.ThreemaApplication;
import ch.threema.app.emojis.EmojiTextView;
import ch.threema.app.services.ContactService;
import ch.threema.app.services.IdListService;
import ch.threema.app.services.PreferenceService;
import ch.threema.app.ui.AvatarListItemUtil;
import ch.threema.app.ui.CheckableConstraintLayout;
import ch.threema.app.ui.VerificationLevelImageView;
import ch.threema.app.ui.listitemholder.AvatarListItemHolder;
import ch.threema.app.utils.AdapterUtil;
import ch.threema.app.utils.ContactUtil;
import ch.threema.app.utils.LocaleUtil;
import ch.threema.app.utils.NameUtil;
import ch.threema.app.utils.ViewUtil;
import ch.threema.storage.models.ContactModel;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class ContactListAdapter extends FilterableListAdapter implements SectionIndexer {

    private static final Logger logger = LoggerFactory.getLogger(ContactListAdapter.class);

    private static final int MAX_RECENTLY_ADDED_CONTACTS = 3;

    private final ContactService contactService;

    private final PreferenceService preferenceService;

    private final IdListService blackListIdentityService;

    public static final int VIEW_TYPE_NORMAL = 0;

    public static final int VIEW_TYPE_COUNT = 2;

    private static final String PLACEHOLDER_BLANK_HEADER = " ";

    private static final String PLACEHOLDER_CHANNELS = "\uffff";

    private static final String PLACEHOLDER_RECENTLY_ADDED = "\u0001";

    private static final String CHANNEL_SIGN = "\u002a";

    private static final String RECENTLY_ADDED_SIGN = "+";

    private List<ContactModel> values, ovalues, recentlyAdded = new ArrayList<>();

    private ContactListFilter contactListFilter;

    private final AvatarListener avatarListener;

    private final Bitmap defaultContactImage;

    private final HashMap<String, Integer> alphaIndexer = new HashMap<String, Integer>();

    private final HashMap<Integer, String> positionIndexer = new HashMap<Integer, String>();

    private String[] sections;

    private Integer[] counts;

    private final LayoutInflater inflater;

    private final Collator collator;

    public interface AvatarListener {

        void onAvatarClick(View view, int position);

        boolean onAvatarLongClick(View view, int position);
    }

    public ContactListAdapter(@NonNull Context context, @NonNull List<ContactModel> values, ContactService contactService, PreferenceService preferenceService, IdListService blackListIdentityService, AvatarListener avatarListener) {
        super(context, R.layout.item_contact_list, (List<Object>) (Object) values);
        if (!ListenerUtil.mutListener.listen(8700)) {
            this.values = updateRecentlyAdded(values);
        }
        if (!ListenerUtil.mutListener.listen(8701)) {
            this.ovalues = this.values;
        }
        this.contactService = contactService;
        this.preferenceService = preferenceService;
        this.blackListIdentityService = blackListIdentityService;
        this.defaultContactImage = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_contact);
        this.avatarListener = avatarListener;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.collator = Collator.getInstance();
        if (!ListenerUtil.mutListener.listen(8702)) {
            this.collator.setStrength(Collator.PRIMARY);
        }
        if (!ListenerUtil.mutListener.listen(8703)) {
            setupIndexer();
        }
    }

    private List<ContactModel> updateRecentlyAdded(List<ContactModel> all) {
        ArrayList<ContactModel> recents = new ArrayList<>();
        Date recentlyAddedDate = new Date((ListenerUtil.mutListener.listen(8707) ? (System.currentTimeMillis() % DateUtils.DAY_IN_MILLIS) : (ListenerUtil.mutListener.listen(8706) ? (System.currentTimeMillis() / DateUtils.DAY_IN_MILLIS) : (ListenerUtil.mutListener.listen(8705) ? (System.currentTimeMillis() * DateUtils.DAY_IN_MILLIS) : (ListenerUtil.mutListener.listen(8704) ? (System.currentTimeMillis() + DateUtils.DAY_IN_MILLIS) : (System.currentTimeMillis() - DateUtils.DAY_IN_MILLIS))))));
        if (!ListenerUtil.mutListener.listen(8713)) {
            {
                long _loopCounter68 = 0;
                for (ContactModel contactModel : all) {
                    ListenerUtil.loopListener.listen("_loopCounter68", ++_loopCounter68);
                    if (!ListenerUtil.mutListener.listen(8712)) {
                        if ((ListenerUtil.mutListener.listen(8710) ? ((ListenerUtil.mutListener.listen(8709) ? ((ListenerUtil.mutListener.listen(8708) ? (contactModel != null || contactModel.getDateCreated() != null) : (contactModel != null && contactModel.getDateCreated() != null)) || recentlyAddedDate.before(contactModel.getDateCreated())) : ((ListenerUtil.mutListener.listen(8708) ? (contactModel != null || contactModel.getDateCreated() != null) : (contactModel != null && contactModel.getDateCreated() != null)) && recentlyAddedDate.before(contactModel.getDateCreated()))) || !ContactUtil.isChannelContact(contactModel)) : ((ListenerUtil.mutListener.listen(8709) ? ((ListenerUtil.mutListener.listen(8708) ? (contactModel != null || contactModel.getDateCreated() != null) : (contactModel != null && contactModel.getDateCreated() != null)) || recentlyAddedDate.before(contactModel.getDateCreated())) : ((ListenerUtil.mutListener.listen(8708) ? (contactModel != null || contactModel.getDateCreated() != null) : (contactModel != null && contactModel.getDateCreated() != null)) && recentlyAddedDate.before(contactModel.getDateCreated()))) && !ContactUtil.isChannelContact(contactModel)))) {
                            if (!ListenerUtil.mutListener.listen(8711)) {
                                recents.add(contactModel);
                            }
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(8724)) {
            if ((ListenerUtil.mutListener.listen(8718) ? (recents.size() >= 0) : (ListenerUtil.mutListener.listen(8717) ? (recents.size() <= 0) : (ListenerUtil.mutListener.listen(8716) ? (recents.size() < 0) : (ListenerUtil.mutListener.listen(8715) ? (recents.size() != 0) : (ListenerUtil.mutListener.listen(8714) ? (recents.size() == 0) : (recents.size() > 0))))))) {
                if (!ListenerUtil.mutListener.listen(8720)) {
                    // filter latest
                    Collections.sort(recents, (o1, o2) -> o2.getDateCreated().compareTo(o1.getDateCreated()));
                }
                if (!ListenerUtil.mutListener.listen(8721)) {
                    this.recentlyAdded = recents.subList(0, Math.min(recents.size(), MAX_RECENTLY_ADDED_CONTACTS));
                }
                if (!ListenerUtil.mutListener.listen(8722)) {
                    all.removeAll(this.recentlyAdded);
                }
                if (!ListenerUtil.mutListener.listen(8723)) {
                    all.addAll(0, this.recentlyAdded);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(8719)) {
                    this.recentlyAdded.clear();
                }
            }
        }
        return all;
    }

    public void updateData(@NonNull List<ContactModel> all) {
        if (!ListenerUtil.mutListener.listen(8725)) {
            setNotifyOnChange(false);
        }
        if (!ListenerUtil.mutListener.listen(8726)) {
            this.values = updateRecentlyAdded(all);
        }
        if (!ListenerUtil.mutListener.listen(8727)) {
            this.ovalues = this.values;
        }
        if (!ListenerUtil.mutListener.listen(8728)) {
            setupIndexer();
        }
        if (!ListenerUtil.mutListener.listen(8729)) {
            setNotifyOnChange(true);
        }
        if (!ListenerUtil.mutListener.listen(8730)) {
            notifyDataSetChanged();
        }
    }

    private boolean containsKeyLocaleAware(String newKey) {
        if (!ListenerUtil.mutListener.listen(8732)) {
            {
                long _loopCounter69 = 0;
                for (String key : alphaIndexer.keySet()) {
                    ListenerUtil.loopListener.listen("_loopCounter69", ++_loopCounter69);
                    if (!ListenerUtil.mutListener.listen(8731)) {
                        if (collator.equals(key, newKey)) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    /**
     *  Get Unicode-aware index character for headers and thumbscroller
     *  @param input Input string
     *  @return Unicode character at beginning of input
     */
    private String getIndexCharacter(String input) {
        try {
            int codePoint = Character.codePointAt(input, 0);
            return input.substring(0, Character.charCount(codePoint)).toUpperCase();
        } catch (Exception e) {
            return input.substring(0, 1).toUpperCase();
        }
    }

    private void setupIndexer() {
        int size = values.size();
        String firstLetter;
        if (!ListenerUtil.mutListener.listen(8733)) {
            alphaIndexer.clear();
        }
        if (!ListenerUtil.mutListener.listen(8734)) {
            positionIndexer.clear();
        }
        {
            long _loopCounter70 = 0;
            // create index for fast scroll
            for (int i = 0; (ListenerUtil.mutListener.listen(8750) ? (i >= size) : (ListenerUtil.mutListener.listen(8749) ? (i <= size) : (ListenerUtil.mutListener.listen(8748) ? (i > size) : (ListenerUtil.mutListener.listen(8747) ? (i != size) : (ListenerUtil.mutListener.listen(8746) ? (i == size) : (i < size)))))); i++) {
                ListenerUtil.loopListener.listen("_loopCounter70", ++_loopCounter70);
                ContactModel c = values.get(i);
                if (!ListenerUtil.mutListener.listen(8738)) {
                    if (c == null) {
                        if (!ListenerUtil.mutListener.listen(8735)) {
                            // values that already have headers added
                            values.remove(i);
                        }
                        if (!ListenerUtil.mutListener.listen(8736)) {
                            i--;
                        }
                        if (!ListenerUtil.mutListener.listen(8737)) {
                            size--;
                        }
                        continue;
                    }
                }
                firstLetter = getInitial(c, false);
                if ((ListenerUtil.mutListener.listen(8740) ? ((ListenerUtil.mutListener.listen(8739) ? (PLACEHOLDER_BLANK_HEADER.equals(firstLetter) && PLACEHOLDER_CHANNELS.equals(firstLetter)) : (PLACEHOLDER_BLANK_HEADER.equals(firstLetter) || PLACEHOLDER_CHANNELS.equals(firstLetter))) && PLACEHOLDER_RECENTLY_ADDED.equals(firstLetter)) : ((ListenerUtil.mutListener.listen(8739) ? (PLACEHOLDER_BLANK_HEADER.equals(firstLetter) && PLACEHOLDER_CHANNELS.equals(firstLetter)) : (PLACEHOLDER_BLANK_HEADER.equals(firstLetter) || PLACEHOLDER_CHANNELS.equals(firstLetter))) || PLACEHOLDER_RECENTLY_ADDED.equals(firstLetter)))) {
                    if (!ListenerUtil.mutListener.listen(8745)) {
                        // placeholders
                        if (!alphaIndexer.containsKey(firstLetter)) {
                            if (!ListenerUtil.mutListener.listen(8743)) {
                                alphaIndexer.put(firstLetter, i);
                            }
                            if (!ListenerUtil.mutListener.listen(8744)) {
                                positionIndexer.put(i, firstLetter);
                            }
                        }
                    }
                } else {
                    if (!containsKeyLocaleAware(firstLetter)) {
                        firstLetter = Normalizer.normalize(firstLetter, Normalizer.Form.NFD);
                        if (!ListenerUtil.mutListener.listen(8741)) {
                            alphaIndexer.put(firstLetter, i);
                        }
                        if (!ListenerUtil.mutListener.listen(8742)) {
                            positionIndexer.put(i, firstLetter);
                        }
                    }
                }
            }
        }
        // create a list from the set to sort
        ArrayList<String> sectionList = new ArrayList<String>(alphaIndexer.keySet());
        if (!ListenerUtil.mutListener.listen(8751)) {
            Collections.sort(sectionList, collator);
        }
        if (!ListenerUtil.mutListener.listen(8756)) {
            if (sectionList.contains(PLACEHOLDER_CHANNELS)) {
                if (!ListenerUtil.mutListener.listen(8752)) {
                    // replace channels placeholder by copyright sign AFTER sorting
                    sectionList.set(sectionList.indexOf(PLACEHOLDER_CHANNELS), CHANNEL_SIGN);
                }
                if (!ListenerUtil.mutListener.listen(8755)) {
                    if (alphaIndexer.containsKey(PLACEHOLDER_CHANNELS)) {
                        if (!ListenerUtil.mutListener.listen(8753)) {
                            alphaIndexer.put(CHANNEL_SIGN, alphaIndexer.get(PLACEHOLDER_CHANNELS));
                        }
                        if (!ListenerUtil.mutListener.listen(8754)) {
                            alphaIndexer.remove(PLACEHOLDER_CHANNELS);
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(8757)) {
            sections = new String[sectionList.size()];
        }
        if (!ListenerUtil.mutListener.listen(8758)) {
            sectionList.toArray(sections);
        }
        // create array for reverse lookup
        ArrayList<Integer> countsList = new ArrayList<Integer>(positionIndexer.keySet());
        if (!ListenerUtil.mutListener.listen(8759)) {
            Collections.sort(countsList);
        }
        if (!ListenerUtil.mutListener.listen(8760)) {
            counts = new Integer[countsList.size()];
        }
        if (!ListenerUtil.mutListener.listen(8761)) {
            countsList.toArray(counts);
        }
    }

    private String getInitial(ContactModel c, boolean afterSorting) {
        String firstLetter, sortingValue;
        sortingValue = ContactUtil.getSafeNameStringNoNickname(c, preferenceService);
        if ((ListenerUtil.mutListener.listen(8766) ? (sortingValue.length() >= 0) : (ListenerUtil.mutListener.listen(8765) ? (sortingValue.length() <= 0) : (ListenerUtil.mutListener.listen(8764) ? (sortingValue.length() > 0) : (ListenerUtil.mutListener.listen(8763) ? (sortingValue.length() < 0) : (ListenerUtil.mutListener.listen(8762) ? (sortingValue.length() != 0) : (sortingValue.length() == 0))))))) {
            firstLetter = PLACEHOLDER_BLANK_HEADER;
        } else {
            if (ContactUtil.isChannelContact(c)) {
                firstLetter = afterSorting ? CHANNEL_SIGN : PLACEHOLDER_CHANNELS;
            } else if ((ListenerUtil.mutListener.listen(8773) ? ((ListenerUtil.mutListener.listen(8772) ? (recentlyAdded != null || (ListenerUtil.mutListener.listen(8771) ? (recentlyAdded.size() >= 0) : (ListenerUtil.mutListener.listen(8770) ? (recentlyAdded.size() <= 0) : (ListenerUtil.mutListener.listen(8769) ? (recentlyAdded.size() < 0) : (ListenerUtil.mutListener.listen(8768) ? (recentlyAdded.size() != 0) : (ListenerUtil.mutListener.listen(8767) ? (recentlyAdded.size() == 0) : (recentlyAdded.size() > 0))))))) : (recentlyAdded != null && (ListenerUtil.mutListener.listen(8771) ? (recentlyAdded.size() >= 0) : (ListenerUtil.mutListener.listen(8770) ? (recentlyAdded.size() <= 0) : (ListenerUtil.mutListener.listen(8769) ? (recentlyAdded.size() < 0) : (ListenerUtil.mutListener.listen(8768) ? (recentlyAdded.size() != 0) : (ListenerUtil.mutListener.listen(8767) ? (recentlyAdded.size() == 0) : (recentlyAdded.size() > 0)))))))) || recentlyAdded.contains(c)) : ((ListenerUtil.mutListener.listen(8772) ? (recentlyAdded != null || (ListenerUtil.mutListener.listen(8771) ? (recentlyAdded.size() >= 0) : (ListenerUtil.mutListener.listen(8770) ? (recentlyAdded.size() <= 0) : (ListenerUtil.mutListener.listen(8769) ? (recentlyAdded.size() < 0) : (ListenerUtil.mutListener.listen(8768) ? (recentlyAdded.size() != 0) : (ListenerUtil.mutListener.listen(8767) ? (recentlyAdded.size() == 0) : (recentlyAdded.size() > 0))))))) : (recentlyAdded != null && (ListenerUtil.mutListener.listen(8771) ? (recentlyAdded.size() >= 0) : (ListenerUtil.mutListener.listen(8770) ? (recentlyAdded.size() <= 0) : (ListenerUtil.mutListener.listen(8769) ? (recentlyAdded.size() < 0) : (ListenerUtil.mutListener.listen(8768) ? (recentlyAdded.size() != 0) : (ListenerUtil.mutListener.listen(8767) ? (recentlyAdded.size() == 0) : (recentlyAdded.size() > 0)))))))) && recentlyAdded.contains(c)))) {
                firstLetter = afterSorting ? RECENTLY_ADDED_SIGN : PLACEHOLDER_RECENTLY_ADDED;
            } else {
                firstLetter = getIndexCharacter(sortingValue);
            }
        }
        return firstLetter;
    }

    private static class ContactListHolder extends AvatarListItemHolder {

        TextView nameTextView;

        TextView idTextView;

        TextView nickTextView;

        VerificationLevelImageView verificationLevelView;

        ImageView blockedContactView;

        EmojiTextView initialView;

        ImageView initialImageView;

        int originalPosition;
    }

    @NonNull
    @Override
    public View getView(final int position, View convertView, @NonNull ViewGroup parent) {
        CheckableConstraintLayout itemView = (CheckableConstraintLayout) convertView;
        ContactListHolder holder;
        if (convertView == null) {
            // This a new view we inflate the new layout
            holder = new ContactListHolder();
            if (!ListenerUtil.mutListener.listen(8774)) {
                itemView = (CheckableConstraintLayout) inflater.inflate(R.layout.item_contact_list, parent, false);
            }
            if (!ListenerUtil.mutListener.listen(8775)) {
                holder.nameTextView = itemView.findViewById(R.id.name);
            }
            if (!ListenerUtil.mutListener.listen(8776)) {
                holder.idTextView = itemView.findViewById(R.id.subject);
            }
            if (!ListenerUtil.mutListener.listen(8777)) {
                holder.nickTextView = itemView.findViewById(R.id.nick);
            }
            if (!ListenerUtil.mutListener.listen(8778)) {
                holder.verificationLevelView = itemView.findViewById(R.id.verification_level);
            }
            if (!ListenerUtil.mutListener.listen(8779)) {
                holder.avatarView = itemView.findViewById(R.id.avatar_view);
            }
            if (!ListenerUtil.mutListener.listen(8780)) {
                holder.blockedContactView = itemView.findViewById(R.id.blocked_contact);
            }
            if (!ListenerUtil.mutListener.listen(8781)) {
                holder.initialView = itemView.findViewById(R.id.initial);
            }
            if (!ListenerUtil.mutListener.listen(8782)) {
                holder.initialImageView = itemView.findViewById(R.id.initial_image);
            }
            if (!ListenerUtil.mutListener.listen(8783)) {
                itemView.setTag(holder);
            }
            if (!ListenerUtil.mutListener.listen(8787)) {
                itemView.setOnCheckedChangeListener(new CheckableConstraintLayout.OnCheckedChangeListener() {

                    @Override
                    public void onCheckedChanged(CheckableConstraintLayout checkableView, boolean isChecked) {
                        if (!ListenerUtil.mutListener.listen(8786)) {
                            if (isChecked) {
                                if (!ListenerUtil.mutListener.listen(8785)) {
                                    checkedItems.add(((ContactListHolder) checkableView.getTag()).originalPosition);
                                }
                            } else {
                                if (!ListenerUtil.mutListener.listen(8784)) {
                                    checkedItems.remove(((ContactListHolder) checkableView.getTag()).originalPosition);
                                }
                            }
                        }
                    }
                });
            }
        } else {
            holder = (ContactListHolder) itemView.getTag();
        }
        if (!ListenerUtil.mutListener.listen(8789)) {
            holder.avatarView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    if (!ListenerUtil.mutListener.listen(8788)) {
                        avatarListener.onAvatarClick(v, position);
                    }
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(8790)) {
            holder.avatarView.setOnLongClickListener(new View.OnLongClickListener() {

                @Override
                public boolean onLongClick(View v) {
                    return avatarListener.onAvatarLongClick(v, position);
                }
            });
        }
        final ContactModel contactModel = values.get(position);
        if (!ListenerUtil.mutListener.listen(8791)) {
            holder.originalPosition = ovalues.indexOf(contactModel);
        }
        String filterString = null;
        if (!ListenerUtil.mutListener.listen(8793)) {
            if (contactListFilter != null) {
                if (!ListenerUtil.mutListener.listen(8792)) {
                    filterString = contactListFilter.getFilterString();
                }
            }
        }
        String displayName = NameUtil.getDisplayNameOrNickname(contactModel, true);
        if (!ListenerUtil.mutListener.listen(8794)) {
            ViewUtil.showAndSet(holder.nameTextView, highlightMatches(displayName, filterString, true));
        }
        if (!ListenerUtil.mutListener.listen(8795)) {
            holder.avatarView.setContentDescription(ThreemaApplication.getAppContext().getString(R.string.edit_type_content_description, ThreemaApplication.getAppContext().getString(R.string.mime_contact), displayName));
        }
        if (!ListenerUtil.mutListener.listen(8796)) {
            AdapterUtil.styleContact(holder.nameTextView, contactModel);
        }
        if (!ListenerUtil.mutListener.listen(8797)) {
            ViewUtil.showAndSet(holder.idTextView, highlightMatches(contactModel.getIdentity(), filterString, true));
        }
        if (!ListenerUtil.mutListener.listen(8798)) {
            AdapterUtil.styleContact(holder.idTextView, contactModel);
        }
        if (!ListenerUtil.mutListener.listen(8799)) {
            holder.verificationLevelView.setContactModel(contactModel);
        }
        if (!ListenerUtil.mutListener.listen(8801)) {
            ViewUtil.show(holder.blockedContactView, (ListenerUtil.mutListener.listen(8800) ? (blackListIdentityService != null || blackListIdentityService.has(contactModel.getIdentity())) : (blackListIdentityService != null && blackListIdentityService.has(contactModel.getIdentity()))));
        }
        if (!ListenerUtil.mutListener.listen(8811)) {
            if ((ListenerUtil.mutListener.listen(8808) ? ((ListenerUtil.mutListener.listen(8807) ? ((ListenerUtil.mutListener.listen(8806) ? (displayName.length() >= 1) : (ListenerUtil.mutListener.listen(8805) ? (displayName.length() <= 1) : (ListenerUtil.mutListener.listen(8804) ? (displayName.length() < 1) : (ListenerUtil.mutListener.listen(8803) ? (displayName.length() != 1) : (ListenerUtil.mutListener.listen(8802) ? (displayName.length() == 1) : (displayName.length() > 1)))))) || displayName.startsWith("~")) : ((ListenerUtil.mutListener.listen(8806) ? (displayName.length() >= 1) : (ListenerUtil.mutListener.listen(8805) ? (displayName.length() <= 1) : (ListenerUtil.mutListener.listen(8804) ? (displayName.length() < 1) : (ListenerUtil.mutListener.listen(8803) ? (displayName.length() != 1) : (ListenerUtil.mutListener.listen(8802) ? (displayName.length() == 1) : (displayName.length() > 1)))))) && displayName.startsWith("~"))) || displayName.substring(1).equals(contactModel.getPublicNickName())) : ((ListenerUtil.mutListener.listen(8807) ? ((ListenerUtil.mutListener.listen(8806) ? (displayName.length() >= 1) : (ListenerUtil.mutListener.listen(8805) ? (displayName.length() <= 1) : (ListenerUtil.mutListener.listen(8804) ? (displayName.length() < 1) : (ListenerUtil.mutListener.listen(8803) ? (displayName.length() != 1) : (ListenerUtil.mutListener.listen(8802) ? (displayName.length() == 1) : (displayName.length() > 1)))))) || displayName.startsWith("~")) : ((ListenerUtil.mutListener.listen(8806) ? (displayName.length() >= 1) : (ListenerUtil.mutListener.listen(8805) ? (displayName.length() <= 1) : (ListenerUtil.mutListener.listen(8804) ? (displayName.length() < 1) : (ListenerUtil.mutListener.listen(8803) ? (displayName.length() != 1) : (ListenerUtil.mutListener.listen(8802) ? (displayName.length() == 1) : (displayName.length() > 1)))))) && displayName.startsWith("~"))) && displayName.substring(1).equals(contactModel.getPublicNickName())))) {
                if (!ListenerUtil.mutListener.listen(8810)) {
                    holder.nickTextView.setText("");
                }
            } else {
                if (!ListenerUtil.mutListener.listen(8809)) {
                    NameUtil.showNicknameInView(holder.nickTextView, contactModel, filterString, this);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(8812)) {
            AvatarListItemUtil.loadAvatar(position, contactModel, this.defaultContactImage, this.contactService, holder);
        }
        String previousInitial = PLACEHOLDER_CHANNELS;
        String currentInitial = getInitial(contactModel, true);
        if (!ListenerUtil.mutListener.listen(8823)) {
            if ((ListenerUtil.mutListener.listen(8817) ? (position >= 0) : (ListenerUtil.mutListener.listen(8816) ? (position <= 0) : (ListenerUtil.mutListener.listen(8815) ? (position < 0) : (ListenerUtil.mutListener.listen(8814) ? (position != 0) : (ListenerUtil.mutListener.listen(8813) ? (position == 0) : (position > 0))))))) {
                if (!ListenerUtil.mutListener.listen(8822)) {
                    previousInitial = getInitial(values.get((ListenerUtil.mutListener.listen(8821) ? (position % 1) : (ListenerUtil.mutListener.listen(8820) ? (position / 1) : (ListenerUtil.mutListener.listen(8819) ? (position * 1) : (ListenerUtil.mutListener.listen(8818) ? (position + 1) : (position - 1)))))), true);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(8833)) {
            if ((ListenerUtil.mutListener.listen(8824) ? (previousInitial != null || !previousInitial.equals(currentInitial)) : (previousInitial != null && !previousInitial.equals(currentInitial)))) {
                if (!ListenerUtil.mutListener.listen(8832)) {
                    if (RECENTLY_ADDED_SIGN.equals(currentInitial)) {
                        if (!ListenerUtil.mutListener.listen(8830)) {
                            holder.initialView.setVisibility(View.GONE);
                        }
                        if (!ListenerUtil.mutListener.listen(8831)) {
                            holder.initialImageView.setVisibility(View.VISIBLE);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(8827)) {
                            holder.initialView.setText(currentInitial);
                        }
                        if (!ListenerUtil.mutListener.listen(8828)) {
                            holder.initialView.setVisibility(View.VISIBLE);
                        }
                        if (!ListenerUtil.mutListener.listen(8829)) {
                            holder.initialImageView.setVisibility(View.GONE);
                        }
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(8825)) {
                    holder.initialView.setVisibility(View.GONE);
                }
                if (!ListenerUtil.mutListener.listen(8826)) {
                    holder.initialImageView.setVisibility(View.GONE);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(8834)) {
            holder.avatarView.setBadgeVisible(contactService.showBadge(contactModel));
        }
        return itemView;
    }

    @Override
    public int getItemViewType(int position) {
        return VIEW_TYPE_NORMAL;
    }

    @Override
    public int getViewTypeCount() {
        return VIEW_TYPE_COUNT;
    }

    @Override
    public boolean isEnabled(int position) {
        return true;
    }

    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }

    @Override
    public int getPositionForSection(int section) {
        if (!ListenerUtil.mutListener.listen(8846)) {
            if ((ListenerUtil.mutListener.listen(8845) ? ((ListenerUtil.mutListener.listen(8839) ? (section >= 0) : (ListenerUtil.mutListener.listen(8838) ? (section <= 0) : (ListenerUtil.mutListener.listen(8837) ? (section > 0) : (ListenerUtil.mutListener.listen(8836) ? (section != 0) : (ListenerUtil.mutListener.listen(8835) ? (section == 0) : (section < 0)))))) && (ListenerUtil.mutListener.listen(8844) ? (section <= sections.length) : (ListenerUtil.mutListener.listen(8843) ? (section > sections.length) : (ListenerUtil.mutListener.listen(8842) ? (section < sections.length) : (ListenerUtil.mutListener.listen(8841) ? (section != sections.length) : (ListenerUtil.mutListener.listen(8840) ? (section == sections.length) : (section >= sections.length))))))) : ((ListenerUtil.mutListener.listen(8839) ? (section >= 0) : (ListenerUtil.mutListener.listen(8838) ? (section <= 0) : (ListenerUtil.mutListener.listen(8837) ? (section > 0) : (ListenerUtil.mutListener.listen(8836) ? (section != 0) : (ListenerUtil.mutListener.listen(8835) ? (section == 0) : (section < 0)))))) || (ListenerUtil.mutListener.listen(8844) ? (section <= sections.length) : (ListenerUtil.mutListener.listen(8843) ? (section > sections.length) : (ListenerUtil.mutListener.listen(8842) ? (section < sections.length) : (ListenerUtil.mutListener.listen(8841) ? (section != sections.length) : (ListenerUtil.mutListener.listen(8840) ? (section == sections.length) : (section >= sections.length))))))))) {
                return -1;
            }
        }
        return alphaIndexer.get(sections[section]);
    }

    @Override
    public int getSectionForPosition(int position) {
        if (!ListenerUtil.mutListener.listen(8858)) {
            if ((ListenerUtil.mutListener.listen(8857) ? ((ListenerUtil.mutListener.listen(8851) ? (position >= 0) : (ListenerUtil.mutListener.listen(8850) ? (position <= 0) : (ListenerUtil.mutListener.listen(8849) ? (position > 0) : (ListenerUtil.mutListener.listen(8848) ? (position != 0) : (ListenerUtil.mutListener.listen(8847) ? (position == 0) : (position < 0)))))) && (ListenerUtil.mutListener.listen(8856) ? (position <= values.size()) : (ListenerUtil.mutListener.listen(8855) ? (position > values.size()) : (ListenerUtil.mutListener.listen(8854) ? (position < values.size()) : (ListenerUtil.mutListener.listen(8853) ? (position != values.size()) : (ListenerUtil.mutListener.listen(8852) ? (position == values.size()) : (position >= values.size()))))))) : ((ListenerUtil.mutListener.listen(8851) ? (position >= 0) : (ListenerUtil.mutListener.listen(8850) ? (position <= 0) : (ListenerUtil.mutListener.listen(8849) ? (position > 0) : (ListenerUtil.mutListener.listen(8848) ? (position != 0) : (ListenerUtil.mutListener.listen(8847) ? (position == 0) : (position < 0)))))) || (ListenerUtil.mutListener.listen(8856) ? (position <= values.size()) : (ListenerUtil.mutListener.listen(8855) ? (position > values.size()) : (ListenerUtil.mutListener.listen(8854) ? (position < values.size()) : (ListenerUtil.mutListener.listen(8853) ? (position != values.size()) : (ListenerUtil.mutListener.listen(8852) ? (position == values.size()) : (position >= values.size()))))))))) {
                return -1;
            }
        }
        int index = Arrays.binarySearch(counts, position);
        /*
         * Consider this example: section positions are 0, 3, 5; the supplied
         * position is 4. The section corresponding to position 4 starts at
         * position 3, so the expected return value is 1. Binary search will not
         * find 4 in the array and thus will return -insertPosition-1, i.e. -3.
         * To get from that number to the expected value of 1 we need to negate
         * and subtract 2.
         */
        return (ListenerUtil.mutListener.listen(8863) ? (index <= 0) : (ListenerUtil.mutListener.listen(8862) ? (index > 0) : (ListenerUtil.mutListener.listen(8861) ? (index < 0) : (ListenerUtil.mutListener.listen(8860) ? (index != 0) : (ListenerUtil.mutListener.listen(8859) ? (index == 0) : (index >= 0)))))) ? index : (ListenerUtil.mutListener.listen(8867) ? (-index % 2) : (ListenerUtil.mutListener.listen(8866) ? (-index / 2) : (ListenerUtil.mutListener.listen(8865) ? (-index * 2) : (ListenerUtil.mutListener.listen(8864) ? (-index + 2) : (-index - 2)))));
    }

    @Override
    public Object[] getSections() {
        return sections;
    }

    public class ContactListFilter extends Filter {

        String filterString = null;

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            if (!ListenerUtil.mutListener.listen(8885)) {
                if ((ListenerUtil.mutListener.listen(8873) ? (constraint == null && (ListenerUtil.mutListener.listen(8872) ? (constraint.length() >= 0) : (ListenerUtil.mutListener.listen(8871) ? (constraint.length() <= 0) : (ListenerUtil.mutListener.listen(8870) ? (constraint.length() > 0) : (ListenerUtil.mutListener.listen(8869) ? (constraint.length() < 0) : (ListenerUtil.mutListener.listen(8868) ? (constraint.length() != 0) : (constraint.length() == 0))))))) : (constraint == null || (ListenerUtil.mutListener.listen(8872) ? (constraint.length() >= 0) : (ListenerUtil.mutListener.listen(8871) ? (constraint.length() <= 0) : (ListenerUtil.mutListener.listen(8870) ? (constraint.length() > 0) : (ListenerUtil.mutListener.listen(8869) ? (constraint.length() < 0) : (ListenerUtil.mutListener.listen(8868) ? (constraint.length() != 0) : (constraint.length() == 0))))))))) {
                    if (!ListenerUtil.mutListener.listen(8882)) {
                        // no filtering
                        filterString = null;
                    }
                    if (!ListenerUtil.mutListener.listen(8883)) {
                        results.values = ovalues;
                    }
                    if (!ListenerUtil.mutListener.listen(8884)) {
                        results.count = ovalues.size();
                    }
                } else {
                    // perform filtering
                    List<ContactModel> nContactList = new ArrayList<ContactModel>();
                    if (!ListenerUtil.mutListener.listen(8874)) {
                        filterString = LocaleUtil.normalize(constraint.toString());
                    }
                    if (!ListenerUtil.mutListener.listen(8879)) {
                        {
                            long _loopCounter71 = 0;
                            for (ContactModel contactModel : ovalues) {
                                ListenerUtil.loopListener.listen("_loopCounter71", ++_loopCounter71);
                                if (!ListenerUtil.mutListener.listen(8878)) {
                                    if (contactModel != null) {
                                        if (!ListenerUtil.mutListener.listen(8877)) {
                                            if ((ListenerUtil.mutListener.listen(8875) ? ((LocaleUtil.normalize(NameUtil.getDisplayNameOrNickname(contactModel, false)).contains(filterString)) && (contactModel.getIdentity().toUpperCase().contains(filterString))) : ((LocaleUtil.normalize(NameUtil.getDisplayNameOrNickname(contactModel, false)).contains(filterString)) || (contactModel.getIdentity().toUpperCase().contains(filterString))))) {
                                                if (!ListenerUtil.mutListener.listen(8876)) {
                                                    nContactList.add(contactModel);
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(8880)) {
                        results.values = nContactList;
                    }
                    if (!ListenerUtil.mutListener.listen(8881)) {
                        results.count = nContactList.size();
                    }
                }
            }
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            if (!ListenerUtil.mutListener.listen(8886)) {
                values = (List<ContactModel>) results.values;
            }
            if (!ListenerUtil.mutListener.listen(8887)) {
                notifyDataSetChanged();
            }
        }

        public String getFilterString() {
            return filterString;
        }
    }

    @NotNull
    @Override
    public Filter getFilter() {
        if (!ListenerUtil.mutListener.listen(8889)) {
            if (contactListFilter == null)
                if (!ListenerUtil.mutListener.listen(8888)) {
                    contactListFilter = new ContactListFilter();
                }
        }
        return contactListFilter;
    }

    @Override
    public int getCount() {
        return values != null ? values.size() : 0;
    }

    @Override
    public HashSet<ContactModel> getCheckedItems() {
        HashSet<ContactModel> contacts = new HashSet<>();
        ContactModel contactModel;
        {
            long _loopCounter72 = 0;
            for (int position : checkedItems) {
                ListenerUtil.loopListener.listen("_loopCounter72", ++_loopCounter72);
                contactModel = ovalues.get(position);
                if (!ListenerUtil.mutListener.listen(8891)) {
                    if (contactModel != null) {
                        if (!ListenerUtil.mutListener.listen(8890)) {
                            contacts.add(contactModel);
                        }
                    }
                }
            }
        }
        return contacts;
    }

    @Override
    public ContactModel getClickedItem(View v) {
        if (!ListenerUtil.mutListener.listen(8897)) {
            if ((ListenerUtil.mutListener.listen(8896) ? (ovalues.size() >= 0) : (ListenerUtil.mutListener.listen(8895) ? (ovalues.size() <= 0) : (ListenerUtil.mutListener.listen(8894) ? (ovalues.size() < 0) : (ListenerUtil.mutListener.listen(8893) ? (ovalues.size() != 0) : (ListenerUtil.mutListener.listen(8892) ? (ovalues.size() == 0) : (ovalues.size() > 0))))))) {
                return ovalues.get(getClickedItemPosition(v));
            }
        }
        return null;
    }

    public int getClickedItemPosition(View v) {
        if (!ListenerUtil.mutListener.listen(8899)) {
            if ((ListenerUtil.mutListener.listen(8898) ? (v != null || v.getTag() != null) : (v != null && v.getTag() != null))) {
                return ((ContactListAdapter.ContactListHolder) v.getTag()).originalPosition;
            }
        }
        return 0;
    }

    public String getInitial(int position) {
        if (!ListenerUtil.mutListener.listen(8911)) {
            if ((ListenerUtil.mutListener.listen(8910) ? ((ListenerUtil.mutListener.listen(8904) ? (position >= values.size()) : (ListenerUtil.mutListener.listen(8903) ? (position <= values.size()) : (ListenerUtil.mutListener.listen(8902) ? (position > values.size()) : (ListenerUtil.mutListener.listen(8901) ? (position != values.size()) : (ListenerUtil.mutListener.listen(8900) ? (position == values.size()) : (position < values.size())))))) || (ListenerUtil.mutListener.listen(8909) ? (position >= 0) : (ListenerUtil.mutListener.listen(8908) ? (position <= 0) : (ListenerUtil.mutListener.listen(8907) ? (position < 0) : (ListenerUtil.mutListener.listen(8906) ? (position != 0) : (ListenerUtil.mutListener.listen(8905) ? (position == 0) : (position > 0))))))) : ((ListenerUtil.mutListener.listen(8904) ? (position >= values.size()) : (ListenerUtil.mutListener.listen(8903) ? (position <= values.size()) : (ListenerUtil.mutListener.listen(8902) ? (position > values.size()) : (ListenerUtil.mutListener.listen(8901) ? (position != values.size()) : (ListenerUtil.mutListener.listen(8900) ? (position == values.size()) : (position < values.size())))))) && (ListenerUtil.mutListener.listen(8909) ? (position >= 0) : (ListenerUtil.mutListener.listen(8908) ? (position <= 0) : (ListenerUtil.mutListener.listen(8907) ? (position < 0) : (ListenerUtil.mutListener.listen(8906) ? (position != 0) : (ListenerUtil.mutListener.listen(8905) ? (position == 0) : (position > 0))))))))) {
                return getInitial(values.get(position), true);
            }
        }
        return "";
    }

    @Override
    public boolean isEmpty() {
        return (ListenerUtil.mutListener.listen(8917) ? (values != null || (ListenerUtil.mutListener.listen(8916) ? (getCount() >= 0) : (ListenerUtil.mutListener.listen(8915) ? (getCount() <= 0) : (ListenerUtil.mutListener.listen(8914) ? (getCount() > 0) : (ListenerUtil.mutListener.listen(8913) ? (getCount() < 0) : (ListenerUtil.mutListener.listen(8912) ? (getCount() != 0) : (getCount() == 0))))))) : (values != null && (ListenerUtil.mutListener.listen(8916) ? (getCount() >= 0) : (ListenerUtil.mutListener.listen(8915) ? (getCount() <= 0) : (ListenerUtil.mutListener.listen(8914) ? (getCount() > 0) : (ListenerUtil.mutListener.listen(8913) ? (getCount() < 0) : (ListenerUtil.mutListener.listen(8912) ? (getCount() != 0) : (getCount() == 0))))))));
    }
}
