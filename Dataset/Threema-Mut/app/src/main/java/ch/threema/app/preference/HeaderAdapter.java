/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
 * Copyright (c) 2018-2021 Threema GmbH
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
package ch.threema.app.preference;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.StateListDrawable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.TintTypedArray;
import ch.threema.app.R;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * @author [大前良介 (OHMAE Ryosuke)](mailto:ryo@mm2d.net)
 */
class HeaderAdapter extends ArrayAdapter<Header> {

    private static class ViewHolder {

        private final ImageView mIcon;

        private final TextView mTitle;

        private final TextView mSummary;

        ViewHolder(@NonNull final ImageView icon, @NonNull final TextView title, @NonNull final TextView summary) {
            mIcon = icon;
            mTitle = title;
            mSummary = summary;
        }

        ImageView getIcon() {
            return mIcon;
        }

        TextView getTitle() {
            return mTitle;
        }

        TextView getSummary() {
            return mSummary;
        }
    }

    private final int mColorAccent;

    private final LayoutInflater mInflater;

    HeaderAdapter(@NonNull final Context context, @NonNull final List<Header> objects) {
        super(context, 0, objects);
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        // if (VERSION.SDK_INT < VERSION_CODES.LOLLIPOP) {
        mColorAccent = getThemeAttrColor(context, R.attr.settings_multipane_selection_bg);
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable final View convertView, @NonNull final ViewGroup parent) {
        final ViewHolder holder;
        final View view;
        if (convertView == null) {
            view = mInflater.inflate(R.layout.pref_header_item, parent, false);
            if (!ListenerUtil.mutListener.listen(31763)) {
                setBackground(view);
            }
            holder = new ViewHolder(view.findViewById(R.id.icon), view.findViewById(R.id.title), view.findViewById(R.id.summary));
            if (!ListenerUtil.mutListener.listen(31764)) {
                view.setTag(holder);
            }
        } else {
            view = convertView;
            holder = (ViewHolder) view.getTag();
        }
        final ImageView icon = holder.getIcon();
        final TextView title = holder.getTitle();
        final TextView summary = holder.getSummary();
        final Header header = getItem(position);
        assert header != null;
        if (!ListenerUtil.mutListener.listen(31768)) {
            if (header.iconRes == 0) {
                if (!ListenerUtil.mutListener.listen(31767)) {
                    icon.setVisibility(View.GONE);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(31765)) {
                    icon.setVisibility(View.VISIBLE);
                }
                if (!ListenerUtil.mutListener.listen(31766)) {
                    icon.setImageResource(header.iconRes);
                }
            }
        }
        final Resources resources = getContext().getResources();
        if (!ListenerUtil.mutListener.listen(31769)) {
            title.setText(header.getTitle(resources));
        }
        final CharSequence text = header.getSummary(resources);
        if (!ListenerUtil.mutListener.listen(31773)) {
            if (!TextUtils.isEmpty(text)) {
                if (!ListenerUtil.mutListener.listen(31771)) {
                    summary.setVisibility(View.VISIBLE);
                }
                if (!ListenerUtil.mutListener.listen(31772)) {
                    summary.setText(text);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(31770)) {
                    summary.setVisibility(View.GONE);
                }
            }
        }
        return view;
    }

    @SuppressLint("RestrictedApi")
    private static int getThemeAttrColor(@NonNull final Context context, final int attr) {
        final TintTypedArray a = TintTypedArray.obtainStyledAttributes(context, null, new int[] { attr });
        try {
            return a.getColor(0, 0);
        } finally {
            if (!ListenerUtil.mutListener.listen(31774)) {
                a.recycle();
            }
        }
    }

    private void setBackground(@NonNull final View view) {
        if (!ListenerUtil.mutListener.listen(31780)) {
            if ((ListenerUtil.mutListener.listen(31779) ? (mColorAccent >= 0) : (ListenerUtil.mutListener.listen(31778) ? (mColorAccent <= 0) : (ListenerUtil.mutListener.listen(31777) ? (mColorAccent > 0) : (ListenerUtil.mutListener.listen(31776) ? (mColorAccent < 0) : (ListenerUtil.mutListener.listen(31775) ? (mColorAccent != 0) : (mColorAccent == 0))))))) {
                return;
            }
        }
        final StateListDrawable drawable = new StateListDrawable();
        if (!ListenerUtil.mutListener.listen(31781)) {
            drawable.addState(new int[] { android.R.attr.state_activated }, new ColorDrawable(mColorAccent));
        }
        if (!ListenerUtil.mutListener.listen(31782)) {
            drawable.addState(new int[] { -android.R.attr.state_activated }, new ColorDrawable(Color.TRANSPARENT));
        }
        if (!ListenerUtil.mutListener.listen(31783)) {
            view.setBackground(drawable);
        }
    }
}
