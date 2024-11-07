/*
 *  Copyright (c) 2020 David Allison <davidallisongithub@gmail.com>
 *
 *  This program is free software; you can redistribute it and/or modify it under
 *  the terms of the GNU General Public License as published by the Free Software
 *  Foundation; either version 3 of the License, or (at your option) any later
 *  version.
 *
 *  This program is distributed in the hope that it will be useful, but WITHOUT ANY
 *  WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A
 *  PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License along with
 *  this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.ichi2.anki.dialogs;

import android.app.Dialog;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.afollestad.materialdialogs.MaterialDialog;
import com.ichi2.anki.AnkiActivity;
import com.ichi2.anki.R;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import androidx.annotation.CheckResult;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.vectordrawable.graphics.drawable.VectorDrawableCompat;
import timber.log.Timber;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * A Dialog displaying The various options for "Help" in a nested structure
 */
public class RecursivePictureMenu extends DialogFragment {

    public RecursivePictureMenu() {
    }

    @CheckResult
    public static RecursivePictureMenu createInstance(ArrayList<Item> itemList, @StringRes int title) {
        RecursivePictureMenu helpDialog = new RecursivePictureMenu();
        Bundle args = new Bundle();
        if (!ListenerUtil.mutListener.listen(939)) {
            args.putParcelableArrayList("bundle", itemList);
        }
        if (!ListenerUtil.mutListener.listen(940)) {
            args.putInt("titleRes", title);
        }
        if (!ListenerUtil.mutListener.listen(941)) {
            helpDialog.setArguments(args);
        }
        return helpDialog;
    }

    public static void removeFrom(List<Item> allItems, Item toRemove) {
        if (!ListenerUtil.mutListener.listen(943)) {
            {
                long _loopCounter14 = 0;
                // Note: currently doesn't remove the top-level elements.
                for (Item i : allItems) {
                    ListenerUtil.loopListener.listen("_loopCounter14", ++_loopCounter14);
                    if (!ListenerUtil.mutListener.listen(942)) {
                        i.remove(toRemove);
                    }
                }
            }
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        @NonNull
        final List<Item> items = requireArguments().getParcelableArrayList("bundle");
        @NonNull
        final String title = requireContext().getString(requireArguments().getInt("titleRes"));
        RecyclerView.Adapter<?> adapter = new RecyclerView.Adapter<RecyclerView.ViewHolder>() {

            @NonNull
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View root = getLayoutInflater().inflate(R.layout.material_dialog_list_item, parent, false);
                return new RecyclerView.ViewHolder(root) {
                };
            }

            @Override
            public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
                TextView textView = (TextView) holder.itemView;
                Item val = items.get(position);
                if (!ListenerUtil.mutListener.listen(944)) {
                    textView.setText(val.mText);
                }
                if (!ListenerUtil.mutListener.listen(945)) {
                    textView.setOnClickListener((l) -> val.execute((AnkiActivity) requireActivity()));
                }
                int mIcon = val.mIcon;
                if (!ListenerUtil.mutListener.listen(946)) {
                    textView.setCompoundDrawablesRelativeWithIntrinsicBounds(mIcon, 0, 0, 0);
                }
            }

            @Override
            public int getItemCount() {
                return items.size();
            }
        };
        MaterialDialog dialog = new MaterialDialog.Builder(requireContext()).adapter(adapter, null).title(title).show();
        if (!ListenerUtil.mutListener.listen(947)) {
            setMenuBreadcrumbHeader(dialog);
        }
        View v = dialog.findViewById(R.id.md_contentRecyclerView);
        if (!ListenerUtil.mutListener.listen(948)) {
            v.setPadding(v.getPaddingLeft(), v.getPaddingTop(), v.getPaddingRight(), 0);
        }
        return dialog;
    }

    protected void setMenuBreadcrumbHeader(MaterialDialog dialog) {
        try {
            View titleFrame = dialog.findViewById(R.id.md_titleFrame);
            if (!ListenerUtil.mutListener.listen(950)) {
                titleFrame.setPadding(10, 22, 10, 10);
            }
            if (!ListenerUtil.mutListener.listen(951)) {
                titleFrame.setOnClickListener((l) -> dismiss());
            }
            View icon = dialog.findViewById(R.id.md_icon);
            if (!ListenerUtil.mutListener.listen(952)) {
                icon.setVisibility(View.VISIBLE);
            }
            Drawable iconValue = VectorDrawableCompat.create(getResources(), R.drawable.ic_menu_back_black_24dp, requireActivity().getTheme());
            if (!ListenerUtil.mutListener.listen(953)) {
                icon.setBackground(iconValue);
            }
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(949)) {
                Timber.w(e, "Failed to set Menu title/icon");
            }
        }
    }

    public abstract static class Item implements Parcelable {

        @StringRes
        private final int mText;

        @DrawableRes
        private final int mIcon;

        public Item(@StringRes int titleString, @DrawableRes int iconDrawable) {
            this.mText = titleString;
            this.mIcon = iconDrawable;
        }

        public List<Item> getChildren() {
            return new ArrayList<>(0);
        }

        protected Item(Parcel in) {
            mText = in.readInt();
            mIcon = in.readInt();
        }

        @StringRes
        protected int getTitle() {
            return mText;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            if (!ListenerUtil.mutListener.listen(954)) {
                dest.writeInt(mText);
            }
            if (!ListenerUtil.mutListener.listen(955)) {
                dest.writeInt(mIcon);
            }
        }

        public abstract void execute(AnkiActivity activity);

        public abstract void remove(Item toRemove);
    }

    public static class ItemHeader extends Item implements Parcelable {

        private final List<Item> mChildren;

        public ItemHeader(@StringRes int titleString, int i, Item... children) {
            super(titleString, i);
            mChildren = new ArrayList<>(Arrays.asList(children));
        }

        @Override
        public List<Item> getChildren() {
            return new ArrayList<>(mChildren);
        }

        @Override
        public void execute(AnkiActivity activity) {
            ArrayList<Item> children = new ArrayList<>(this.getChildren());
            DialogFragment nextFragment = RecursivePictureMenu.createInstance(children, getTitle());
            if (!ListenerUtil.mutListener.listen(956)) {
                activity.showDialogFragment(nextFragment);
            }
        }

        @Override
        public void remove(Item toRemove) {
            if (!ListenerUtil.mutListener.listen(957)) {
                mChildren.remove(toRemove);
            }
            if (!ListenerUtil.mutListener.listen(959)) {
                {
                    long _loopCounter15 = 0;
                    for (Item i : mChildren) {
                        ListenerUtil.loopListener.listen("_loopCounter15", ++_loopCounter15);
                        if (!ListenerUtil.mutListener.listen(958)) {
                            i.remove(toRemove);
                        }
                    }
                }
            }
        }

        protected ItemHeader(Parcel in) {
            super(in);
            if (in.readByte() == 0x01) {
                mChildren = new ArrayList<>();
                if (!ListenerUtil.mutListener.listen(960)) {
                    in.readList(mChildren, Item.class.getClassLoader());
                }
            } else {
                mChildren = new ArrayList<>(0);
            }
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            if (!ListenerUtil.mutListener.listen(961)) {
                super.writeToParcel(dest, flags);
            }
            if (!ListenerUtil.mutListener.listen(965)) {
                if (mChildren == null) {
                    if (!ListenerUtil.mutListener.listen(964)) {
                        dest.writeByte((byte) (0x00));
                    }
                } else {
                    if (!ListenerUtil.mutListener.listen(962)) {
                        dest.writeByte((byte) (0x01));
                    }
                    if (!ListenerUtil.mutListener.listen(963)) {
                        dest.writeList(mChildren);
                    }
                }
            }
        }

        @SuppressWarnings("unused")
        public static final Parcelable.Creator<ItemHeader> CREATOR = new Parcelable.Creator<ItemHeader>() {

            @Override
            public ItemHeader createFromParcel(Parcel in) {
                return new ItemHeader(in);
            }

            @Override
            public ItemHeader[] newArray(int size) {
                return new ItemHeader[size];
            }
        };
    }
}
