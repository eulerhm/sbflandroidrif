/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
 * Copyright (c) 2016-2021 Threema GmbH
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
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import java.io.IOException;
import androidx.annotation.NonNull;
import ch.threema.app.R;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class StickerSelectorAdapter extends ArrayAdapter<String> {

    private String[] items;

    private LayoutInflater layoutInflater;

    public StickerSelectorAdapter(Context context, String[] items) {
        super(context, R.layout.item_sticker_selector, items);
        if (!ListenerUtil.mutListener.listen(9612)) {
            this.items = items;
        }
        if (!ListenerUtil.mutListener.listen(9613)) {
            this.layoutInflater = LayoutInflater.from(context);
        }
    }

    private class StickerSelectorHolder {

        ImageView imageView;

        int position;
    }

    @NonNull
    @Override
    public View getView(final int position, View convertView, @NonNull ViewGroup parent) {
        View itemView = convertView;
        final StickerSelectorHolder holder;
        if (convertView == null) {
            holder = new StickerSelectorHolder();
            if (!ListenerUtil.mutListener.listen(9615)) {
                // This a new view we inflate the new layout
                itemView = layoutInflater.inflate(R.layout.item_sticker_selector, parent, false);
            }
            if (!ListenerUtil.mutListener.listen(9616)) {
                holder.imageView = itemView.findViewById(R.id.sticker);
            }
            if (!ListenerUtil.mutListener.listen(9617)) {
                itemView.setTag(holder);
            }
        } else {
            holder = (StickerSelectorHolder) itemView.getTag();
            if (!ListenerUtil.mutListener.listen(9614)) {
                holder.imageView.setImageBitmap(null);
            }
        }
        final String item = items[position];
        if (!ListenerUtil.mutListener.listen(9618)) {
            holder.position = position;
        }
        if (!ListenerUtil.mutListener.listen(9622)) {
            new AsyncTask<Void, Void, Bitmap>() {

                @Override
                protected Bitmap doInBackground(Void... params) {
                    try {
                        return BitmapFactory.decodeStream(getContext().getAssets().open(item));
                    } catch (IOException e) {
                        return null;
                    }
                }

                @Override
                protected void onPostExecute(Bitmap bitmap) {
                    if (!ListenerUtil.mutListener.listen(9621)) {
                        if (bitmap != null) {
                            if (!ListenerUtil.mutListener.listen(9620)) {
                                if (holder.position == position) {
                                    if (!ListenerUtil.mutListener.listen(9619)) {
                                        holder.imageView.setImageBitmap(bitmap);
                                    }
                                }
                            }
                        }
                    }
                }
            }.executeOnExecutor(AsyncTask.SERIAL_EXECUTOR);
        }
        return itemView;
    }

    public String getItem(int index) {
        return items[index];
    }
}
