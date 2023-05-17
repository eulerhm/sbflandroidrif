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
package ch.threema.app.ui;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.DrawableRes;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class BottomSheetItem implements Parcelable {

    private Bitmap bitmap;

    private String title;

    private String tag;

    @DrawableRes
    private int resource;

    public BottomSheetItem(Bitmap bitmap, String title, String tag) {
        if (!ListenerUtil.mutListener.listen(44713)) {
            this.bitmap = bitmap;
        }
        if (!ListenerUtil.mutListener.listen(44714)) {
            this.title = title;
        }
        if (!ListenerUtil.mutListener.listen(44715)) {
            this.tag = tag;
        }
        if (!ListenerUtil.mutListener.listen(44716)) {
            this.resource = 0;
        }
    }

    public BottomSheetItem(@DrawableRes int resource, String title, String tag) {
        if (!ListenerUtil.mutListener.listen(44717)) {
            this.bitmap = null;
        }
        if (!ListenerUtil.mutListener.listen(44718)) {
            this.title = title;
        }
        if (!ListenerUtil.mutListener.listen(44719)) {
            this.tag = tag;
        }
        if (!ListenerUtil.mutListener.listen(44720)) {
            this.resource = resource;
        }
    }

    public Bitmap getBitmap() {
        return this.bitmap;
    }

    public String getTitle() {
        return this.title;
    }

    public String getTag() {
        return this.tag;
    }

    @DrawableRes
    public int getResource() {
        return this.resource;
    }

    protected BottomSheetItem(Parcel in) {
        if (!ListenerUtil.mutListener.listen(44721)) {
            bitmap = (Bitmap) in.readValue(Bitmap.class.getClassLoader());
        }
        if (!ListenerUtil.mutListener.listen(44722)) {
            title = in.readString();
        }
        if (!ListenerUtil.mutListener.listen(44723)) {
            tag = in.readString();
        }
        if (!ListenerUtil.mutListener.listen(44724)) {
            resource = in.readInt();
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (!ListenerUtil.mutListener.listen(44725)) {
            dest.writeValue(bitmap);
        }
        if (!ListenerUtil.mutListener.listen(44726)) {
            dest.writeString(title);
        }
        if (!ListenerUtil.mutListener.listen(44727)) {
            dest.writeString(tag);
        }
        if (!ListenerUtil.mutListener.listen(44728)) {
            dest.writeInt(resource);
        }
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<BottomSheetItem> CREATOR = new Parcelable.Creator<BottomSheetItem>() {

        @Override
        public BottomSheetItem createFromParcel(Parcel in) {
            return new BottomSheetItem(in);
        }

        @Override
        public BottomSheetItem[] newArray(int size) {
            return new BottomSheetItem[size];
        }
    };
}
