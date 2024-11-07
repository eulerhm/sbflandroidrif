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
package ch.threema.app.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import ch.threema.app.R;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class MediaGallerySpinnerAdapter extends ArrayAdapter<String> {

    Context context;

    String[] values;

    String titleText;

    LayoutInflater inflater;

    String subtitle;

    public MediaGallerySpinnerAdapter(Context context, String[] values, String titleText) {
        super(context, R.layout.spinner_media_gallery, values);
        if (!ListenerUtil.mutListener.listen(9227)) {
            this.context = context;
        }
        if (!ListenerUtil.mutListener.listen(9228)) {
            this.values = values;
        }
        if (!ListenerUtil.mutListener.listen(9229)) {
            this.titleText = titleText;
        }
        if (!ListenerUtil.mutListener.listen(9230)) {
            this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if (!ListenerUtil.mutListener.listen(9231)) {
            this.subtitle = "";
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (!ListenerUtil.mutListener.listen(9233)) {
            if (convertView == null) {
                if (!ListenerUtil.mutListener.listen(9232)) {
                    convertView = inflater.inflate(R.layout.spinner_media_gallery, null);
                }
            }
        }
        TextView title = convertView.findViewById(R.id.title);
        TextView subtitleView = convertView.findViewById(R.id.subtitle_text);
        if (!ListenerUtil.mutListener.listen(9234)) {
            title.setText(this.titleText);
        }
        if (!ListenerUtil.mutListener.listen(9235)) {
            subtitleView.setText(subtitle);
        }
        return convertView;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        if (!ListenerUtil.mutListener.listen(9237)) {
            if (convertView == null) {
                if (!ListenerUtil.mutListener.listen(9236)) {
                    convertView = inflater.inflate(android.R.layout.simple_spinner_dropdown_item, parent, false);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(9238)) {
            ((TextView) convertView).setText(this.values[position]);
        }
        return convertView;
    }

    @Override
    public int getCount() {
        return this.values.length;
    }

    @Override
    public String getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public void setSubtitle(String subtitle) {
        if (!ListenerUtil.mutListener.listen(9239)) {
            this.subtitle = subtitle;
        }
    }
}
