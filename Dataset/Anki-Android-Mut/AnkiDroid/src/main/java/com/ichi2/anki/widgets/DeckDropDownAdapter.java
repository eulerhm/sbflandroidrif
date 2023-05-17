package com.ichi2.anki.widgets;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.ichi2.anki.R;
import com.ichi2.libanki.Deck;
import java.util.ArrayList;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public final class DeckDropDownAdapter extends BaseAdapter {

    public interface SubtitleListener {

        String getSubtitleText();
    }

    private final Context context;

    private final ArrayList<Deck> decks;

    public DeckDropDownAdapter(Context context, ArrayList<Deck> decks) {
        this.context = context;
        this.decks = decks;
    }

    static class DeckDropDownViewHolder {

        public TextView deckNameView;

        public TextView deckCountsView;
    }

    @Override
    public int getCount() {
        return (ListenerUtil.mutListener.listen(4098) ? (decks.size() % 1) : (ListenerUtil.mutListener.listen(4097) ? (decks.size() / 1) : (ListenerUtil.mutListener.listen(4096) ? (decks.size() * 1) : (ListenerUtil.mutListener.listen(4095) ? (decks.size() - 1) : (decks.size() + 1)))));
    }

    @Override
    public Object getItem(int position) {
        if ((ListenerUtil.mutListener.listen(4103) ? (position >= 0) : (ListenerUtil.mutListener.listen(4102) ? (position <= 0) : (ListenerUtil.mutListener.listen(4101) ? (position > 0) : (ListenerUtil.mutListener.listen(4100) ? (position < 0) : (ListenerUtil.mutListener.listen(4099) ? (position != 0) : (position == 0))))))) {
            return null;
        } else {
            return decks.get((ListenerUtil.mutListener.listen(4107) ? (position % 1) : (ListenerUtil.mutListener.listen(4106) ? (position / 1) : (ListenerUtil.mutListener.listen(4105) ? (position * 1) : (ListenerUtil.mutListener.listen(4104) ? (position - 1) : (position + 1))))));
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        DeckDropDownViewHolder viewHolder;
        TextView deckNameView;
        TextView deckCountsView;
        if (convertView == null) {
            if (!ListenerUtil.mutListener.listen(4108)) {
                convertView = LayoutInflater.from(context).inflate(R.layout.dropdown_deck_selected_item, parent, false);
            }
            deckNameView = convertView.findViewById(R.id.dropdown_deck_name);
            deckCountsView = convertView.findViewById(R.id.dropdown_deck_counts);
            viewHolder = new DeckDropDownViewHolder();
            if (!ListenerUtil.mutListener.listen(4109)) {
                viewHolder.deckNameView = deckNameView;
            }
            if (!ListenerUtil.mutListener.listen(4110)) {
                viewHolder.deckCountsView = deckCountsView;
            }
            if (!ListenerUtil.mutListener.listen(4111)) {
                convertView.setTag(viewHolder);
            }
        } else {
            viewHolder = (DeckDropDownViewHolder) convertView.getTag();
            deckNameView = viewHolder.deckNameView;
            deckCountsView = viewHolder.deckCountsView;
        }
        if (!ListenerUtil.mutListener.listen(4123)) {
            if ((ListenerUtil.mutListener.listen(4116) ? (position >= 0) : (ListenerUtil.mutListener.listen(4115) ? (position <= 0) : (ListenerUtil.mutListener.listen(4114) ? (position > 0) : (ListenerUtil.mutListener.listen(4113) ? (position < 0) : (ListenerUtil.mutListener.listen(4112) ? (position != 0) : (position == 0))))))) {
                if (!ListenerUtil.mutListener.listen(4122)) {
                    deckNameView.setText(context.getResources().getString(R.string.card_browser_all_decks));
                }
            } else {
                Deck deck = decks.get((ListenerUtil.mutListener.listen(4120) ? (position % 1) : (ListenerUtil.mutListener.listen(4119) ? (position / 1) : (ListenerUtil.mutListener.listen(4118) ? (position * 1) : (ListenerUtil.mutListener.listen(4117) ? (position + 1) : (position - 1))))));
                String deckName = deck.getString("name");
                if (!ListenerUtil.mutListener.listen(4121)) {
                    deckNameView.setText(deckName);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(4124)) {
            deckCountsView.setText(((SubtitleListener) context).getSubtitleText());
        }
        return convertView;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        TextView deckNameView;
        if (convertView == null) {
            if (!ListenerUtil.mutListener.listen(4125)) {
                convertView = LayoutInflater.from(context).inflate(R.layout.dropdown_deck_item, parent, false);
            }
            deckNameView = convertView.findViewById(R.id.dropdown_deck_name);
            if (!ListenerUtil.mutListener.listen(4126)) {
                convertView.setTag(deckNameView);
            }
        } else {
            deckNameView = (TextView) convertView.getTag();
        }
        if (!ListenerUtil.mutListener.listen(4138)) {
            if ((ListenerUtil.mutListener.listen(4131) ? (position >= 0) : (ListenerUtil.mutListener.listen(4130) ? (position <= 0) : (ListenerUtil.mutListener.listen(4129) ? (position > 0) : (ListenerUtil.mutListener.listen(4128) ? (position < 0) : (ListenerUtil.mutListener.listen(4127) ? (position != 0) : (position == 0))))))) {
                if (!ListenerUtil.mutListener.listen(4137)) {
                    deckNameView.setText(context.getResources().getString(R.string.card_browser_all_decks));
                }
            } else {
                Deck deck = decks.get((ListenerUtil.mutListener.listen(4135) ? (position % 1) : (ListenerUtil.mutListener.listen(4134) ? (position / 1) : (ListenerUtil.mutListener.listen(4133) ? (position * 1) : (ListenerUtil.mutListener.listen(4132) ? (position + 1) : (position - 1))))));
                String deckName = deck.getString("name");
                if (!ListenerUtil.mutListener.listen(4136)) {
                    deckNameView.setText(deckName);
                }
            }
        }
        return convertView;
    }
}
