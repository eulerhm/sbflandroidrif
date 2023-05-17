/*
 * The MIT License (MIT)

 Copyright (c) 2014-2016 Aidan Michael Follestad

 Permission is hereby granted, free of charge, to any person obtaining a copy
 of this software and associated documentation files (the "Software"), to deal
 in the Software without restriction, including without limitation the rights
 to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 copies of the Software, and to permit persons to whom the Software is
 furnished to do so, subject to the following conditions:

 The above copyright notice and this permission notice shall be included in all
 copies or substantial portions of the Software.

 THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 SOFTWARE.
 */
package com.ichi2.ui;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import com.ichi2.anki.R;
import java.util.ArrayList;
import java.util.Collections;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * RecyclerView.Adapter class copied almost completely from the Material Dialogs library example
 * {@see <a href="https://github.com/afollestad/material-dialogs/blob/0.9.6.0/sample/src/main/java/com/afollestad/materialdialogssample/ButtonItemAdapter.java>ButtonItemAdapter.java</a>
 */
public class ButtonItemAdapter extends RecyclerView.Adapter<ButtonItemAdapter.ButtonVH> {

    private final ArrayList<String> items;

    private ItemCallback itemCallback;

    private ButtonCallback buttonCallback;

    public ButtonItemAdapter(ArrayList<String> items) {
        this.items = items;
    }

    public void remove(String searchName) {
        if (!ListenerUtil.mutListener.listen(25083)) {
            items.remove(searchName);
        }
    }

    public void setCallbacks(ItemCallback itemCallback, ButtonCallback buttonCallback) {
        if (!ListenerUtil.mutListener.listen(25084)) {
            this.itemCallback = itemCallback;
        }
        if (!ListenerUtil.mutListener.listen(25085)) {
            this.buttonCallback = buttonCallback;
        }
    }

    @Override
    @NonNull
    public ButtonVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_browser_item_my_searches_dialog, parent, false);
        return new ButtonVH(view, this);
    }

    @Override
    public void onBindViewHolder(@NonNull ButtonVH holder, int position) {
        if (!ListenerUtil.mutListener.listen(25086)) {
            holder.title.setText(items.get(position));
        }
        if (!ListenerUtil.mutListener.listen(25087)) {
            holder.button.setTag(items.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public interface ItemCallback {

        void onItemClicked(String searchName);
    }

    public interface ButtonCallback {

        void onButtonClicked(String searchName);
    }

    class ButtonVH extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final TextView title;

        private final ImageButton button;

        private final ButtonItemAdapter adapter;

        ButtonVH(View itemView, ButtonItemAdapter adapter) {
            super(itemView);
            title = itemView.findViewById(R.id.card_browser_my_search_name_textview);
            button = itemView.findViewById(R.id.card_browser_my_search_remove_button);
            this.adapter = adapter;
            if (!ListenerUtil.mutListener.listen(25088)) {
                itemView.setOnClickListener(this);
            }
            if (!ListenerUtil.mutListener.listen(25089)) {
                button.setOnClickListener(this);
            }
        }

        @Override
        public void onClick(View view) {
            if (!ListenerUtil.mutListener.listen(25090)) {
                if (adapter.itemCallback == null) {
                    return;
                }
            }
            if (!ListenerUtil.mutListener.listen(25093)) {
                if (view instanceof ImageButton) {
                    if (!ListenerUtil.mutListener.listen(25092)) {
                        adapter.buttonCallback.onButtonClicked(items.get(getAdapterPosition()));
                    }
                } else {
                    if (!ListenerUtil.mutListener.listen(25091)) {
                        adapter.itemCallback.onItemClicked(items.get(getAdapterPosition()));
                    }
                }
            }
        }
    }

    /**
     * Ensure our strings are sorted alphabetically - call this explicitly after changing
     * the saved searches in any way, prior to displaying them again
     */
    public void notifyAdapterDataSetChanged() {
        if (!ListenerUtil.mutListener.listen(25094)) {
            Collections.sort(items, String::compareToIgnoreCase);
        }
        if (!ListenerUtil.mutListener.listen(25095)) {
            super.notifyDataSetChanged();
        }
    }
}
