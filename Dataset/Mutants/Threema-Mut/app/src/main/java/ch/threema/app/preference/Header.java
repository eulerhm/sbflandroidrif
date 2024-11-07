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

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import androidx.annotation.StringRes;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * @author [大前良介 (OHMAE Ryosuke)](mailto:ryo@mm2d.net)
 */
public final class Header implements Parcelable {

    public long id = PreferenceActivityCompatDelegate.HEADER_ID_UNDEFINED;

    @StringRes
    public int titleRes;

    public CharSequence title;

    @StringRes
    public int summaryRes;

    public CharSequence summary;

    @StringRes
    public int breadCrumbTitleRes;

    public CharSequence breadCrumbTitle;

    public int iconRes;

    public String fragment;

    public Bundle fragmentArguments;

    public Intent intent;

    public Bundle extras;

    public Header() {
    }

    public CharSequence getTitle(final Resources res) {
        if (!ListenerUtil.mutListener.listen(31721)) {
            if ((ListenerUtil.mutListener.listen(31720) ? (titleRes >= 0) : (ListenerUtil.mutListener.listen(31719) ? (titleRes <= 0) : (ListenerUtil.mutListener.listen(31718) ? (titleRes > 0) : (ListenerUtil.mutListener.listen(31717) ? (titleRes < 0) : (ListenerUtil.mutListener.listen(31716) ? (titleRes == 0) : (titleRes != 0))))))) {
                return res.getText(titleRes);
            }
        }
        return title;
    }

    public CharSequence getSummary(final Resources res) {
        if (!ListenerUtil.mutListener.listen(31727)) {
            if ((ListenerUtil.mutListener.listen(31726) ? (summaryRes >= 0) : (ListenerUtil.mutListener.listen(31725) ? (summaryRes <= 0) : (ListenerUtil.mutListener.listen(31724) ? (summaryRes > 0) : (ListenerUtil.mutListener.listen(31723) ? (summaryRes < 0) : (ListenerUtil.mutListener.listen(31722) ? (summaryRes == 0) : (summaryRes != 0))))))) {
                return res.getText(summaryRes);
            }
        }
        return summary;
    }

    public CharSequence getBreadCrumbTitle(final Resources res) {
        if (!ListenerUtil.mutListener.listen(31733)) {
            if ((ListenerUtil.mutListener.listen(31732) ? (breadCrumbTitleRes >= 0) : (ListenerUtil.mutListener.listen(31731) ? (breadCrumbTitleRes <= 0) : (ListenerUtil.mutListener.listen(31730) ? (breadCrumbTitleRes > 0) : (ListenerUtil.mutListener.listen(31729) ? (breadCrumbTitleRes < 0) : (ListenerUtil.mutListener.listen(31728) ? (breadCrumbTitleRes == 0) : (breadCrumbTitleRes != 0))))))) {
                return res.getText(breadCrumbTitleRes);
            }
        }
        return breadCrumbTitle;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(final Parcel dest, final int flags) {
        if (!ListenerUtil.mutListener.listen(31734)) {
            dest.writeLong(id);
        }
        if (!ListenerUtil.mutListener.listen(31735)) {
            dest.writeInt(titleRes);
        }
        if (!ListenerUtil.mutListener.listen(31736)) {
            TextUtils.writeToParcel(title, dest, flags);
        }
        if (!ListenerUtil.mutListener.listen(31737)) {
            dest.writeInt(summaryRes);
        }
        if (!ListenerUtil.mutListener.listen(31738)) {
            TextUtils.writeToParcel(summary, dest, flags);
        }
        if (!ListenerUtil.mutListener.listen(31739)) {
            dest.writeInt(breadCrumbTitleRes);
        }
        if (!ListenerUtil.mutListener.listen(31740)) {
            TextUtils.writeToParcel(breadCrumbTitle, dest, flags);
        }
        if (!ListenerUtil.mutListener.listen(31741)) {
            dest.writeInt(iconRes);
        }
        if (!ListenerUtil.mutListener.listen(31742)) {
            dest.writeString(fragment);
        }
        if (!ListenerUtil.mutListener.listen(31743)) {
            dest.writeBundle(fragmentArguments);
        }
        if (!ListenerUtil.mutListener.listen(31747)) {
            if (intent != null) {
                if (!ListenerUtil.mutListener.listen(31745)) {
                    dest.writeInt(1);
                }
                if (!ListenerUtil.mutListener.listen(31746)) {
                    intent.writeToParcel(dest, flags);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(31744)) {
                    dest.writeInt(0);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(31748)) {
            dest.writeBundle(extras);
        }
    }

    private void readFromParcel(final Parcel in) {
        if (!ListenerUtil.mutListener.listen(31749)) {
            id = in.readLong();
        }
        if (!ListenerUtil.mutListener.listen(31750)) {
            titleRes = in.readInt();
        }
        if (!ListenerUtil.mutListener.listen(31751)) {
            title = TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(in);
        }
        if (!ListenerUtil.mutListener.listen(31752)) {
            summaryRes = in.readInt();
        }
        if (!ListenerUtil.mutListener.listen(31753)) {
            summary = TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(in);
        }
        if (!ListenerUtil.mutListener.listen(31754)) {
            breadCrumbTitleRes = in.readInt();
        }
        if (!ListenerUtil.mutListener.listen(31755)) {
            breadCrumbTitle = TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(in);
        }
        if (!ListenerUtil.mutListener.listen(31756)) {
            iconRes = in.readInt();
        }
        if (!ListenerUtil.mutListener.listen(31757)) {
            fragment = in.readString();
        }
        if (!ListenerUtil.mutListener.listen(31758)) {
            fragmentArguments = in.readBundle(getClass().getClassLoader());
        }
        if (!ListenerUtil.mutListener.listen(31760)) {
            if (in.readInt() != 0) {
                if (!ListenerUtil.mutListener.listen(31759)) {
                    intent = Intent.CREATOR.createFromParcel(in);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(31761)) {
            extras = in.readBundle(getClass().getClassLoader());
        }
    }

    private Header(final Parcel in) {
        if (!ListenerUtil.mutListener.listen(31762)) {
            readFromParcel(in);
        }
    }

    public static final Creator<Header> CREATOR = new Creator<Header>() {

        public Header createFromParcel(final Parcel source) {
            return new Header(source);
        }

        public Header[] newArray(final int size) {
            return new Header[size];
        }
    };
}
