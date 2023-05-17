/*
 Copyright (c) 2020 David Allison <davidallisongithub@gmail.com>

 This program is free software; you can redistribute it and/or modify it under
 the terms of the GNU General Public License as published by the Free Software
 Foundation; either version 3 of the License, or (at your option) any later
 version.

 This program is distributed in the hope that it will be useful, but WITHOUT ANY
 WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A
 PARTICULAR PURPOSE. See the GNU General Public License for more details.

 You should have received a copy of the GNU General Public License along with
 this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.ichi2.ui;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.MenuItem;
import com.ichi2.anki.R;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.vectordrawable.graphics.drawable.VectorDrawableCompat;
import timber.log.Timber;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class BadgeDrawableBuilder {

    private final Resources mResources;

    private char mChar;

    private Integer mColor;

    public BadgeDrawableBuilder(@NonNull Resources resources) {
        mResources = resources;
    }

    public static void removeBadge(MenuItem menuItem) {
        if (!ListenerUtil.mutListener.listen(25060)) {
            if ((ListenerUtil.mutListener.listen(25059) ? (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(25058) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(25057) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(25056) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(25055) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.M) : (Build.VERSION.SDK_INT < Build.VERSION_CODES.M))))))) {
                return;
            }
        }
        Drawable icon = menuItem.getIcon();
        if (!ListenerUtil.mutListener.listen(25063)) {
            if (icon instanceof BadgeDrawable) {
                BadgeDrawable bd = (BadgeDrawable) icon;
                if (!ListenerUtil.mutListener.listen(25061)) {
                    menuItem.setIcon(bd.getCurrent());
                }
                if (!ListenerUtil.mutListener.listen(25062)) {
                    Timber.d("Badge removed");
                }
            }
        }
    }

    @NonNull
    public BadgeDrawableBuilder withText(char c) {
        if (!ListenerUtil.mutListener.listen(25064)) {
            this.mChar = c;
        }
        return this;
    }

    @NonNull
    public BadgeDrawableBuilder withColor(@Nullable Integer color) {
        if (!ListenerUtil.mutListener.listen(25065)) {
            this.mColor = color;
        }
        return this;
    }

    public void replaceBadge(@NonNull MenuItem menuItem) {
        if (!ListenerUtil.mutListener.listen(25071)) {
            if ((ListenerUtil.mutListener.listen(25070) ? (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(25069) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(25068) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(25067) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(25066) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.M) : (Build.VERSION.SDK_INT < Build.VERSION_CODES.M))))))) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(25072)) {
            Timber.d("Adding badge");
        }
        Drawable originalIcon = menuItem.getIcon();
        if (!ListenerUtil.mutListener.listen(25074)) {
            if (originalIcon instanceof BadgeDrawable) {
                BadgeDrawable bd = (BadgeDrawable) originalIcon;
                if (!ListenerUtil.mutListener.listen(25073)) {
                    originalIcon = bd.getCurrent();
                }
            }
        }
        BadgeDrawable badge = new BadgeDrawable(originalIcon);
        if (!ListenerUtil.mutListener.listen(25076)) {
            if (mChar != '\0') {
                if (!ListenerUtil.mutListener.listen(25075)) {
                    badge.setText(mChar);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(25082)) {
            if (mColor != null) {
                Drawable badgeDrawable = VectorDrawableCompat.create(mResources, R.drawable.badge_drawable, null);
                if (!ListenerUtil.mutListener.listen(25078)) {
                    if (badgeDrawable == null) {
                        if (!ListenerUtil.mutListener.listen(25077)) {
                            Timber.w("Unable to find badge_drawable - not drawing badge");
                        }
                        return;
                    }
                }
                Drawable mutableDrawable = badgeDrawable.mutate();
                if (!ListenerUtil.mutListener.listen(25079)) {
                    mutableDrawable.setTint(mColor);
                }
                if (!ListenerUtil.mutListener.listen(25080)) {
                    badge.setBadgeDrawable(mutableDrawable);
                }
                if (!ListenerUtil.mutListener.listen(25081)) {
                    menuItem.setIcon(badge);
                }
            }
        }
    }
}
