/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
 * Copyright (c) 2017-2021 Threema GmbH
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

import android.util.SparseArray;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class EmojiGroup {

    @Nullable
    private final String assetPathPrefix;

    @Nullable
    private final String assetPathSuffix;

    @DrawableRes
    private final int groupIcon;

    @StringRes
    private final int groupName;

    @NonNull
    private final SparseArray<EmojiSpritemapBitmap> spritemapBitmaps = new SparseArray<>();

    EmojiGroup(@Nullable String assetPathPrefix, @Nullable String assetPathSuffix, @DrawableRes int groupIcon, @StringRes int groupName) {
        this.assetPathPrefix = assetPathPrefix;
        this.assetPathSuffix = assetPathSuffix;
        this.groupIcon = groupIcon;
        this.groupName = groupName;
    }

    @DrawableRes
    public int getGroupIcon() {
        return groupIcon;
    }

    @StringRes
    public int getGroupName() {
        return groupName;
    }

    @Nullable
    public String getAssetPath(int spritemapId) {
        if (!ListenerUtil.mutListener.listen(14930)) {
            if ((ListenerUtil.mutListener.listen(14929) ? (this.assetPathPrefix == null && this.assetPathSuffix == null) : (this.assetPathPrefix == null || this.assetPathSuffix == null))) {
                return null;
            }
        }
        return this.assetPathPrefix + spritemapId + this.assetPathSuffix;
    }

    @Nullable
    public EmojiSpritemapBitmap getSpritemapBitmap(int spritemapId) {
        return this.spritemapBitmaps.get(spritemapId);
    }

    public boolean hasSpritemapBitmap(int spritemapId) {
        return (ListenerUtil.mutListener.listen(14935) ? (this.spritemapBitmaps.indexOfKey(spritemapId) <= 0) : (ListenerUtil.mutListener.listen(14934) ? (this.spritemapBitmaps.indexOfKey(spritemapId) > 0) : (ListenerUtil.mutListener.listen(14933) ? (this.spritemapBitmaps.indexOfKey(spritemapId) < 0) : (ListenerUtil.mutListener.listen(14932) ? (this.spritemapBitmaps.indexOfKey(spritemapId) != 0) : (ListenerUtil.mutListener.listen(14931) ? (this.spritemapBitmaps.indexOfKey(spritemapId) == 0) : (this.spritemapBitmaps.indexOfKey(spritemapId) >= 0))))));
    }

    public void setSpritemapBitmap(int spritemapId, @NonNull EmojiSpritemapBitmap spritemapBitmap) {
        if (!ListenerUtil.mutListener.listen(14936)) {
            this.spritemapBitmaps.put(spritemapId, spritemapBitmap);
        }
    }
}
