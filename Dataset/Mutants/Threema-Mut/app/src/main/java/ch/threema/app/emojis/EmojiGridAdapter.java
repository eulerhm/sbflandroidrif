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
package ch.threema.app.emojis;

import android.content.Context;
import androidx.annotation.ColorInt;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import java.util.ArrayList;
import java.util.HashMap;
import ch.threema.app.R;
import ch.threema.app.utils.ConfigUtils;
import static ch.threema.app.emojis.EmojiSpritemap.emojiCategories;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class EmojiGridAdapter extends BaseAdapter {

    private int pageNumber, emojiItemSize, emojiItemPaddingSize;

    @ColorInt
    private int diverseHintColor;

    private EmojiRecent emojiRecent;

    private HashMap<String, String> diverseEmojiPrefs;

    private ArrayList<EmojiInfo> emojis;

    Context context;

    private KeyClickListener keyClickListener;

    public EmojiGridAdapter(Context context, int pageNumber, EmojiRecent emojiRecent, HashMap<String, String> diverseEmojiPrefs, KeyClickListener listener) {
        if (!ListenerUtil.mutListener.listen(14853)) {
            this.context = context;
        }
        if (!ListenerUtil.mutListener.listen(14854)) {
            this.pageNumber = pageNumber;
        }
        if (!ListenerUtil.mutListener.listen(14855)) {
            this.keyClickListener = listener;
        }
        if (!ListenerUtil.mutListener.listen(14856)) {
            this.emojiRecent = emojiRecent;
        }
        if (!ListenerUtil.mutListener.listen(14857)) {
            this.diverseEmojiPrefs = diverseEmojiPrefs;
        }
        if (!ListenerUtil.mutListener.listen(14858)) {
            this.diverseHintColor = ConfigUtils.getColorFromAttribute(context, R.attr.emoji_picker_hint);
        }
        if (!ListenerUtil.mutListener.listen(14879)) {
            if (EmojiManager.getInstance(context).getSpritemapInSampleSize() == 1) {
                if (!ListenerUtil.mutListener.listen(14869)) {
                    this.emojiItemSize = context.getResources().getDimensionPixelSize(R.dimen.emoji_picker_item_size);
                }
                if (!ListenerUtil.mutListener.listen(14878)) {
                    this.emojiItemPaddingSize = (ListenerUtil.mutListener.listen(14877) ? (((ListenerUtil.mutListener.listen(14873) ? (emojiItemSize % context.getResources().getDimensionPixelSize(R.dimen.emoji_picker_emoji_size)) : (ListenerUtil.mutListener.listen(14872) ? (emojiItemSize / context.getResources().getDimensionPixelSize(R.dimen.emoji_picker_emoji_size)) : (ListenerUtil.mutListener.listen(14871) ? (emojiItemSize * context.getResources().getDimensionPixelSize(R.dimen.emoji_picker_emoji_size)) : (ListenerUtil.mutListener.listen(14870) ? (emojiItemSize + context.getResources().getDimensionPixelSize(R.dimen.emoji_picker_emoji_size)) : (emojiItemSize - context.getResources().getDimensionPixelSize(R.dimen.emoji_picker_emoji_size))))))) % 2) : (ListenerUtil.mutListener.listen(14876) ? (((ListenerUtil.mutListener.listen(14873) ? (emojiItemSize % context.getResources().getDimensionPixelSize(R.dimen.emoji_picker_emoji_size)) : (ListenerUtil.mutListener.listen(14872) ? (emojiItemSize / context.getResources().getDimensionPixelSize(R.dimen.emoji_picker_emoji_size)) : (ListenerUtil.mutListener.listen(14871) ? (emojiItemSize * context.getResources().getDimensionPixelSize(R.dimen.emoji_picker_emoji_size)) : (ListenerUtil.mutListener.listen(14870) ? (emojiItemSize + context.getResources().getDimensionPixelSize(R.dimen.emoji_picker_emoji_size)) : (emojiItemSize - context.getResources().getDimensionPixelSize(R.dimen.emoji_picker_emoji_size))))))) * 2) : (ListenerUtil.mutListener.listen(14875) ? (((ListenerUtil.mutListener.listen(14873) ? (emojiItemSize % context.getResources().getDimensionPixelSize(R.dimen.emoji_picker_emoji_size)) : (ListenerUtil.mutListener.listen(14872) ? (emojiItemSize / context.getResources().getDimensionPixelSize(R.dimen.emoji_picker_emoji_size)) : (ListenerUtil.mutListener.listen(14871) ? (emojiItemSize * context.getResources().getDimensionPixelSize(R.dimen.emoji_picker_emoji_size)) : (ListenerUtil.mutListener.listen(14870) ? (emojiItemSize + context.getResources().getDimensionPixelSize(R.dimen.emoji_picker_emoji_size)) : (emojiItemSize - context.getResources().getDimensionPixelSize(R.dimen.emoji_picker_emoji_size))))))) - 2) : (ListenerUtil.mutListener.listen(14874) ? (((ListenerUtil.mutListener.listen(14873) ? (emojiItemSize % context.getResources().getDimensionPixelSize(R.dimen.emoji_picker_emoji_size)) : (ListenerUtil.mutListener.listen(14872) ? (emojiItemSize / context.getResources().getDimensionPixelSize(R.dimen.emoji_picker_emoji_size)) : (ListenerUtil.mutListener.listen(14871) ? (emojiItemSize * context.getResources().getDimensionPixelSize(R.dimen.emoji_picker_emoji_size)) : (ListenerUtil.mutListener.listen(14870) ? (emojiItemSize + context.getResources().getDimensionPixelSize(R.dimen.emoji_picker_emoji_size)) : (emojiItemSize - context.getResources().getDimensionPixelSize(R.dimen.emoji_picker_emoji_size))))))) + 2) : (((ListenerUtil.mutListener.listen(14873) ? (emojiItemSize % context.getResources().getDimensionPixelSize(R.dimen.emoji_picker_emoji_size)) : (ListenerUtil.mutListener.listen(14872) ? (emojiItemSize / context.getResources().getDimensionPixelSize(R.dimen.emoji_picker_emoji_size)) : (ListenerUtil.mutListener.listen(14871) ? (emojiItemSize * context.getResources().getDimensionPixelSize(R.dimen.emoji_picker_emoji_size)) : (ListenerUtil.mutListener.listen(14870) ? (emojiItemSize + context.getResources().getDimensionPixelSize(R.dimen.emoji_picker_emoji_size)) : (emojiItemSize - context.getResources().getDimensionPixelSize(R.dimen.emoji_picker_emoji_size))))))) / 2)))));
                }
            } else {
                if (!ListenerUtil.mutListener.listen(14859)) {
                    this.emojiItemSize = 44;
                }
                if (!ListenerUtil.mutListener.listen(14868)) {
                    this.emojiItemPaddingSize = (ListenerUtil.mutListener.listen(14867) ? (((ListenerUtil.mutListener.listen(14863) ? (emojiItemSize % 32) : (ListenerUtil.mutListener.listen(14862) ? (emojiItemSize / 32) : (ListenerUtil.mutListener.listen(14861) ? (emojiItemSize * 32) : (ListenerUtil.mutListener.listen(14860) ? (emojiItemSize + 32) : (emojiItemSize - 32)))))) % 2) : (ListenerUtil.mutListener.listen(14866) ? (((ListenerUtil.mutListener.listen(14863) ? (emojiItemSize % 32) : (ListenerUtil.mutListener.listen(14862) ? (emojiItemSize / 32) : (ListenerUtil.mutListener.listen(14861) ? (emojiItemSize * 32) : (ListenerUtil.mutListener.listen(14860) ? (emojiItemSize + 32) : (emojiItemSize - 32)))))) * 2) : (ListenerUtil.mutListener.listen(14865) ? (((ListenerUtil.mutListener.listen(14863) ? (emojiItemSize % 32) : (ListenerUtil.mutListener.listen(14862) ? (emojiItemSize / 32) : (ListenerUtil.mutListener.listen(14861) ? (emojiItemSize * 32) : (ListenerUtil.mutListener.listen(14860) ? (emojiItemSize + 32) : (emojiItemSize - 32)))))) - 2) : (ListenerUtil.mutListener.listen(14864) ? (((ListenerUtil.mutListener.listen(14863) ? (emojiItemSize % 32) : (ListenerUtil.mutListener.listen(14862) ? (emojiItemSize / 32) : (ListenerUtil.mutListener.listen(14861) ? (emojiItemSize * 32) : (ListenerUtil.mutListener.listen(14860) ? (emojiItemSize + 32) : (emojiItemSize - 32)))))) + 2) : (((ListenerUtil.mutListener.listen(14863) ? (emojiItemSize % 32) : (ListenerUtil.mutListener.listen(14862) ? (emojiItemSize / 32) : (ListenerUtil.mutListener.listen(14861) ? (emojiItemSize * 32) : (ListenerUtil.mutListener.listen(14860) ? (emojiItemSize + 32) : (emojiItemSize - 32)))))) / 2)))));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(14880)) {
            this.emojis = new ArrayList<>();
        }
        if (!ListenerUtil.mutListener.listen(14896)) {
            if ((ListenerUtil.mutListener.listen(14885) ? (pageNumber >= 0) : (ListenerUtil.mutListener.listen(14884) ? (pageNumber <= 0) : (ListenerUtil.mutListener.listen(14883) ? (pageNumber > 0) : (ListenerUtil.mutListener.listen(14882) ? (pageNumber < 0) : (ListenerUtil.mutListener.listen(14881) ? (pageNumber == 0) : (pageNumber != 0))))))) {
                if (!ListenerUtil.mutListener.listen(14895)) {
                    {
                        long _loopCounter134 = 0;
                        for (EmojiInfo entry : emojiCategories.get((ListenerUtil.mutListener.listen(14894) ? (pageNumber % 1) : (ListenerUtil.mutListener.listen(14893) ? (pageNumber / 1) : (ListenerUtil.mutListener.listen(14892) ? (pageNumber * 1) : (ListenerUtil.mutListener.listen(14891) ? (pageNumber + 1) : (pageNumber - 1)))))).emojiInfos) {
                            ListenerUtil.loopListener.listen("_loopCounter134", ++_loopCounter134);
                            if (!ListenerUtil.mutListener.listen(14888)) {
                                // filter diversity child emojis
                                if (entry.diversityFlag == EmojiSpritemap.DIVERSITY_CHILD) {
                                    continue;
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(14889)) {
                                // filter emojis with display flag set to 0
                                if (entry.displayFlag == EmojiSpritemap.DISPLAY_NO) {
                                    continue;
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(14890)) {
                                this.emojis.add(entry);
                            }
                        }
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(14886)) {
                    emojiRecent.syncRecents();
                }
                if (!ListenerUtil.mutListener.listen(14887)) {
                    getRecents();
                }
            }
        }
    }

    @Override
    public void notifyDataSetChanged() {
        if (!ListenerUtil.mutListener.listen(14904)) {
            // TODO hack
            if ((ListenerUtil.mutListener.listen(14901) ? (pageNumber >= 0) : (ListenerUtil.mutListener.listen(14900) ? (pageNumber <= 0) : (ListenerUtil.mutListener.listen(14899) ? (pageNumber > 0) : (ListenerUtil.mutListener.listen(14898) ? (pageNumber < 0) : (ListenerUtil.mutListener.listen(14897) ? (pageNumber != 0) : (pageNumber == 0))))))) {
                if (!ListenerUtil.mutListener.listen(14902)) {
                    this.emojis.clear();
                }
                if (!ListenerUtil.mutListener.listen(14903)) {
                    getRecents();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(14905)) {
            super.notifyDataSetChanged();
        }
    }

    private void getRecents() {
        if (!ListenerUtil.mutListener.listen(14907)) {
            {
                long _loopCounter135 = 0;
                for (final String emojiSequence : emojiRecent.getRecentList()) {
                    ListenerUtil.loopListener.listen("_loopCounter135", ++_loopCounter135);
                    if (!ListenerUtil.mutListener.listen(14906)) {
                        this.emojis.add(new EmojiInfo(emojiSequence, EmojiSpritemap.DIVERSITY_NONE, null, EmojiSpritemap.GENDER_NONE, null, EmojiSpritemap.DISPLAY_NO));
                    }
                }
            }
        }
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final EmojiInfo item = getItem(position);
        final String emojiKey = getKey(item.emojiSequence);
        final EmojiItemView view;
        if ((ListenerUtil.mutListener.listen(14908) ? (convertView != null || convertView instanceof EmojiItemView) : (convertView != null && convertView instanceof EmojiItemView))) {
            view = (EmojiItemView) convertView;
        } else {
            final EmojiItemView emojiItemView = new EmojiItemView(context);
            if (!ListenerUtil.mutListener.listen(14909)) {
                emojiItemView.setBackground(context.getResources().getDrawable(R.drawable.listitem_background_selector_noripple));
            }
            if (!ListenerUtil.mutListener.listen(14910)) {
                emojiItemView.setPadding(emojiItemPaddingSize, emojiItemPaddingSize, emojiItemPaddingSize, emojiItemPaddingSize);
            }
            if (!ListenerUtil.mutListener.listen(14911)) {
                emojiItemView.setLayoutParams(new AbsListView.LayoutParams(emojiItemSize, emojiItemSize));
            }
            view = emojiItemView;
        }
        if (!ListenerUtil.mutListener.listen(14918)) {
            view.setEmoji(emojiKey, (ListenerUtil.mutListener.listen(14917) ? ((ListenerUtil.mutListener.listen(14916) ? (pageNumber >= 0) : (ListenerUtil.mutListener.listen(14915) ? (pageNumber <= 0) : (ListenerUtil.mutListener.listen(14914) ? (pageNumber > 0) : (ListenerUtil.mutListener.listen(14913) ? (pageNumber < 0) : (ListenerUtil.mutListener.listen(14912) ? (pageNumber == 0) : (pageNumber != 0)))))) || item.diversityFlag == EmojiSpritemap.DIVERSITY_PARENT) : ((ListenerUtil.mutListener.listen(14916) ? (pageNumber >= 0) : (ListenerUtil.mutListener.listen(14915) ? (pageNumber <= 0) : (ListenerUtil.mutListener.listen(14914) ? (pageNumber > 0) : (ListenerUtil.mutListener.listen(14913) ? (pageNumber < 0) : (ListenerUtil.mutListener.listen(14912) ? (pageNumber == 0) : (pageNumber != 0)))))) && item.diversityFlag == EmojiSpritemap.DIVERSITY_PARENT)), diverseHintColor);
        }
        if (!ListenerUtil.mutListener.listen(14919)) {
            view.setContentDescription(emojiKey);
        }
        if (!ListenerUtil.mutListener.listen(14920)) {
            view.setOnClickListener(v -> keyClickListener.onEmojiKeyClicked(getKey(item.emojiSequence)));
        }
        if (!ListenerUtil.mutListener.listen(14921)) {
            view.setOnLongClickListener(v -> {
                if (pageNumber != 0) {
                    keyClickListener.onEmojiKeyLongClicked(v, item.emojiSequence);
                } else {
                    keyClickListener.onRecentLongClicked(v, item.emojiSequence);
                }
                return true;
            });
        }
        return view;
    }

    private String getKey(String parentKey) {
        if (!ListenerUtil.mutListener.listen(14928)) {
            if ((ListenerUtil.mutListener.listen(14927) ? ((ListenerUtil.mutListener.listen(14926) ? (pageNumber >= 0) : (ListenerUtil.mutListener.listen(14925) ? (pageNumber <= 0) : (ListenerUtil.mutListener.listen(14924) ? (pageNumber > 0) : (ListenerUtil.mutListener.listen(14923) ? (pageNumber < 0) : (ListenerUtil.mutListener.listen(14922) ? (pageNumber == 0) : (pageNumber != 0)))))) || diverseEmojiPrefs.containsKey(parentKey)) : ((ListenerUtil.mutListener.listen(14926) ? (pageNumber >= 0) : (ListenerUtil.mutListener.listen(14925) ? (pageNumber <= 0) : (ListenerUtil.mutListener.listen(14924) ? (pageNumber > 0) : (ListenerUtil.mutListener.listen(14923) ? (pageNumber < 0) : (ListenerUtil.mutListener.listen(14922) ? (pageNumber == 0) : (pageNumber != 0)))))) && diverseEmojiPrefs.containsKey(parentKey)))) {
                return diverseEmojiPrefs.get(parentKey);
            }
        }
        return parentKey;
    }

    @Override
    public int getCount() {
        return this.emojis.size();
    }

    @Override
    public EmojiInfo getItem(int position) {
        return this.emojis.get(position);
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public interface KeyClickListener {

        void onEmojiKeyClicked(String emojiCodeString);

        void onEmojiKeyLongClicked(View view, String emojiCodeString);

        void onRecentLongClicked(View v, String emojiCodeString);
    }
}
