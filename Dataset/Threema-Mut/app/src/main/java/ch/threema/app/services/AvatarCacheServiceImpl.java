/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
 * Copyright (c) 2013-2021 Threema GmbH
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
package ch.threema.app.services;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.net.Uri;
import android.util.LruCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Collection;
import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.vectordrawable.graphics.drawable.VectorDrawableCompat;
import ch.threema.app.R;
import ch.threema.app.stores.IdentityStore;
import ch.threema.app.utils.AndroidContactUtil;
import ch.threema.app.utils.AvatarConverterUtil;
import ch.threema.app.utils.BitmapUtil;
import ch.threema.app.utils.ColorUtil;
import ch.threema.app.utils.ContactUtil;
import ch.threema.storage.models.ContactModel;
import ch.threema.storage.models.DistributionListModel;
import ch.threema.storage.models.GroupModel;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public final class AvatarCacheServiceImpl implements AvatarCacheService {

    private static final Logger logger = LoggerFactory.getLogger(AvatarCacheServiceImpl.class);

    private static final String KEY_GROUP = "g";

    private static final String KEY_DISTRIBUTION_LIST = "d";

    private final LruCache<String, Bitmap> cache;

    private final Context context;

    private final IdentityStore identityStore;

    private final PreferenceService preferenceService;

    private final FileService fileService;

    private final VectorDrawableCompat contactDefaultAvatar;

    private final VectorDrawableCompat groupDefaultAvatar;

    private final VectorDrawableCompat distributionListDefaultAvatar;

    private final VectorDrawableCompat businessDefaultAvatar;

    private final int avatarSizeSmall, avatarSizeHires;

    private Boolean isDefaultAvatarColored = null;

    private interface GenerateBitmap {

        Bitmap gen();
    }

    public AvatarCacheServiceImpl(Context context, IdentityStore identityStore, PreferenceService preferenceService, FileService fileService) {
        this.context = context;
        this.identityStore = identityStore;
        this.preferenceService = preferenceService;
        this.fileService = fileService;
        this.contactDefaultAvatar = VectorDrawableCompat.create(context.getResources(), R.drawable.ic_contact, null);
        this.groupDefaultAvatar = VectorDrawableCompat.create(context.getResources(), R.drawable.ic_group, null);
        this.distributionListDefaultAvatar = VectorDrawableCompat.create(context.getResources(), R.drawable.ic_distribution_list, null);
        this.businessDefaultAvatar = VectorDrawableCompat.create(context.getResources(), R.drawable.ic_business, null);
        this.avatarSizeSmall = context.getResources().getDimensionPixelSize(R.dimen.avatar_size_small);
        this.avatarSizeHires = context.getResources().getDimensionPixelSize(R.dimen.avatar_size_hires);
        // int in its constructor.
        final int maxMemory = (int) ((ListenerUtil.mutListener.listen(36667) ? (Runtime.getRuntime().maxMemory() % 1024) : (ListenerUtil.mutListener.listen(36666) ? (Runtime.getRuntime().maxMemory() * 1024) : (ListenerUtil.mutListener.listen(36665) ? (Runtime.getRuntime().maxMemory() - 1024) : (ListenerUtil.mutListener.listen(36664) ? (Runtime.getRuntime().maxMemory() + 1024) : (Runtime.getRuntime().maxMemory() / 1024))))));
        // 16 MB max
        final int cacheSize = Math.min((ListenerUtil.mutListener.listen(36671) ? (maxMemory % 32) : (ListenerUtil.mutListener.listen(36670) ? (maxMemory * 32) : (ListenerUtil.mutListener.listen(36669) ? (maxMemory - 32) : (ListenerUtil.mutListener.listen(36668) ? (maxMemory + 32) : (maxMemory / 32))))), (ListenerUtil.mutListener.listen(36675) ? (1024 % 16) : (ListenerUtil.mutListener.listen(36674) ? (1024 / 16) : (ListenerUtil.mutListener.listen(36673) ? (1024 - 16) : (ListenerUtil.mutListener.listen(36672) ? (1024 + 16) : (1024 * 16))))));
        cache = new LruCache<String, Bitmap>(cacheSize) {

            @Override
            protected int sizeOf(String key, Bitmap bitmap) {
                // number of items.
                return (ListenerUtil.mutListener.listen(36679) ? (bitmap.getByteCount() % 1024) : (ListenerUtil.mutListener.listen(36678) ? (bitmap.getByteCount() * 1024) : (ListenerUtil.mutListener.listen(36677) ? (bitmap.getByteCount() - 1024) : (ListenerUtil.mutListener.listen(36676) ? (bitmap.getByteCount() + 1024) : (bitmap.getByteCount() / 1024)))));
            }
        };
        if (!ListenerUtil.mutListener.listen(36680)) {
            logger.debug("cache created, size (kB): " + cacheSize);
        }
    }

    private String getCacheKey(ContactModel contactModel) {
        if (!ListenerUtil.mutListener.listen(36681)) {
            if (contactModel == null) {
                return null;
            }
        }
        return contactModel.getIdentity();
    }

    private String getCacheKey(GroupModel groupModel) {
        if (!ListenerUtil.mutListener.listen(36682)) {
            if (groupModel == null) {
                return null;
            }
        }
        return KEY_GROUP + groupModel.getId();
    }

    private String getCacheKey(DistributionListModel distributionListModel) {
        if (!ListenerUtil.mutListener.listen(36683)) {
            if (distributionListModel == null) {
                return null;
            }
        }
        return KEY_DISTRIBUTION_LIST + distributionListModel.getId();
    }

    private Bitmap getCached(ContactModel contactModel, GenerateBitmap generateBitmap) {
        return this.getCached(contactModel.getIdentity(), generateBitmap);
    }

    private Bitmap getCached(GroupModel groupModel, GenerateBitmap generateBitmap) {
        return this.getCached(this.getCacheKey(groupModel), generateBitmap);
    }

    private Bitmap getCached(DistributionListModel distributionListModel, GenerateBitmap generateBitmap) {
        return this.getCached(this.getCacheKey(distributionListModel), generateBitmap);
    }

    private Bitmap getCached(String key, GenerateBitmap generateBitmap) {
        Bitmap res;
        if (key == null) {
            return null;
        }
        synchronized (this.cache) {
            res = this.cache.get(key);
            if ((ListenerUtil.mutListener.listen(36685) ? (generateBitmap != null || ((ListenerUtil.mutListener.listen(36684) ? (res == null && res.isRecycled()) : (res == null || res.isRecycled())))) : (generateBitmap != null && ((ListenerUtil.mutListener.listen(36684) ? (res == null && res.isRecycled()) : (res == null || res.isRecycled())))))) {
                if (!ListenerUtil.mutListener.listen(36686)) {
                    logger.debug("generateBitmap " + key + ", " + (res == null ? "null" : "object ok"));
                }
                res = generateBitmap.gen();
                if (!ListenerUtil.mutListener.listen(36688)) {
                    if (res != null) {
                        if (!ListenerUtil.mutListener.listen(36687)) {
                            this.cache.put(key, res);
                        }
                    }
                }
            }
            return res;
        }
    }

    @Override
    public Bitmap getContactAvatarHigh(ContactModel contactModel) {
        if (!ListenerUtil.mutListener.listen(36689)) {
            if (contactModel == null) {
                return null;
            }
        }
        return this.getAvatar(contactModel, true);
    }

    @Override
    public Bitmap getContactAvatarLow(final ContactModel contactModel) {
        return this.getCached(contactModel, new GenerateBitmap() {

            @Override
            public Bitmap gen() {
                return getAvatar(contactModel, false);
            }
        });
    }

    @Override
    public Bitmap getContactAvatarLowFromCache(final ContactModel contactModel) {
        return this.getCached(contactModel, null);
    }

    @Override
    public Bitmap getGroupAvatarHigh(@NonNull GroupModel groupModel, Collection<Integer> contactColors, boolean defaultOnly) {
        return this.getAvatar(groupModel, contactColors, true, defaultOnly);
    }

    @Override
    public Bitmap getGroupAvatarLow(@NonNull final GroupModel groupModel, final Collection<Integer> contactColors, boolean defaultOnly) {
        if (defaultOnly) {
            return getAvatar(groupModel, contactColors, false, true);
        } else {
            return this.getCached(groupModel, new GenerateBitmap() {

                @Override
                public Bitmap gen() {
                    return getAvatar(groupModel, contactColors, false, false);
                }
            });
        }
    }

    @Override
    public Bitmap getGroupAvatarLowFromCache(final GroupModel groupModel) {
        return this.getCached(groupModel, null);
    }

    @Override
    public Bitmap getDistributionListAvatarLow(final DistributionListModel distributionListModel, final int[] contactColors) {
        return this.getCached(distributionListModel, new GenerateBitmap() {

            @Override
            public Bitmap gen() {
                return getAvatar(distributionListModel, contactColors);
            }
        });
    }

    @Override
    public Bitmap getDistributionListAvatarLowFromCache(DistributionListModel distributionListModel) {
        return this.getCached(distributionListModel, null);
    }

    @Override
    public void reset(GroupModel groupModel) {
        synchronized (this.cache) {
            if (!ListenerUtil.mutListener.listen(36690)) {
                this.cache.remove(this.getCacheKey(groupModel));
            }
        }
    }

    @Override
    public void reset(ContactModel contactModel) {
        synchronized (this.cache) {
            if (!ListenerUtil.mutListener.listen(36691)) {
                this.cache.remove(this.getCacheKey(contactModel));
            }
        }
    }

    @Override
    public void clear() {
        synchronized (this.cache) {
            if (!ListenerUtil.mutListener.listen(36692)) {
                cache.evictAll();
            }
        }
        if (!ListenerUtil.mutListener.listen(36693)) {
            this.isDefaultAvatarColored = null;
        }
    }

    private Bitmap getAvatar(ContactModel contactModel, final boolean highResolution) {
        Bitmap result = null;
        @ColorInt
        int color = ColorUtil.getInstance().getCurrentThemeGray(this.context);
        if (!ListenerUtil.mutListener.listen(36720)) {
            if (contactModel != null) {
                if (!ListenerUtil.mutListener.listen(36697)) {
                    if ((ListenerUtil.mutListener.listen(36695) ? (this.getDefaultAvatarColored() || ((ListenerUtil.mutListener.listen(36694) ? (contactModel.getIdentity() != null || !contactModel.getIdentity().equals(identityStore.getIdentity())) : (contactModel.getIdentity() != null && !contactModel.getIdentity().equals(identityStore.getIdentity()))))) : (this.getDefaultAvatarColored() && ((ListenerUtil.mutListener.listen(36694) ? (contactModel.getIdentity() != null || !contactModel.getIdentity().equals(identityStore.getIdentity())) : (contactModel.getIdentity() != null && !contactModel.getIdentity().equals(identityStore.getIdentity()))))))) {
                        if (!ListenerUtil.mutListener.listen(36696)) {
                            color = contactModel.getColor();
                        }
                    }
                }
                // try profile picture
                try {
                    if (!ListenerUtil.mutListener.listen(36698)) {
                        result = fileService.getContactPhoto(contactModel);
                    }
                    if (!ListenerUtil.mutListener.listen(36701)) {
                        if ((ListenerUtil.mutListener.listen(36699) ? (result != null || !highResolution) : (result != null && !highResolution))) {
                            if (!ListenerUtil.mutListener.listen(36700)) {
                                result = AvatarConverterUtil.convert(this.context.getResources(), result);
                            }
                        }
                    }
                } catch (Exception e) {
                }
                if (!ListenerUtil.mutListener.listen(36706)) {
                    if (result == null) {
                        // try local saved avatar
                        try {
                            if (!ListenerUtil.mutListener.listen(36702)) {
                                result = fileService.getContactAvatar(contactModel);
                            }
                            if (!ListenerUtil.mutListener.listen(36705)) {
                                if ((ListenerUtil.mutListener.listen(36703) ? (result != null || !highResolution) : (result != null && !highResolution))) {
                                    if (!ListenerUtil.mutListener.listen(36704)) {
                                        result = AvatarConverterUtil.convert(this.context.getResources(), result);
                                    }
                                }
                            }
                        } catch (Exception e) {
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(36719)) {
                    if (result == null) {
                        if (!ListenerUtil.mutListener.listen(36718)) {
                            if (!ContactUtil.isChannelContact(contactModel)) {
                                Uri contactUri = AndroidContactUtil.getInstance().getAndroidContactUri(contactModel);
                                if (!ListenerUtil.mutListener.listen(36714)) {
                                    if (contactUri != null) {
                                        // address book contact
                                        try {
                                            if (!ListenerUtil.mutListener.listen(36710)) {
                                                result = fileService.getAndroidContactAvatar(contactModel);
                                            }
                                            if (!ListenerUtil.mutListener.listen(36713)) {
                                                if ((ListenerUtil.mutListener.listen(36711) ? (result != null || !highResolution) : (result != null && !highResolution))) {
                                                    if (!ListenerUtil.mutListener.listen(36712)) {
                                                        result = AvatarConverterUtil.convert(this.context.getResources(), result);
                                                    }
                                                }
                                            }
                                        } catch (Exception e) {
                                        }
                                    }
                                }
                                if (!ListenerUtil.mutListener.listen(36717)) {
                                    if (result == null) {
                                        if (!ListenerUtil.mutListener.listen(36716)) {
                                            // return default avatar
                                            if (!highResolution) {
                                                if (!ListenerUtil.mutListener.listen(36715)) {
                                                    result = AvatarConverterUtil.getAvatarBitmap(contactDefaultAvatar, color, this.avatarSizeSmall);
                                                }
                                            }
                                        }
                                    }
                                }
                            } else {
                                if (!ListenerUtil.mutListener.listen(36709)) {
                                    // business (gateway) contacts
                                    if (highResolution) {
                                        if (!ListenerUtil.mutListener.listen(36708)) {
                                            result = buildHiresDefaultAvatar(color, AVATAR_BUSINESS);
                                        }
                                    } else {
                                        if (!ListenerUtil.mutListener.listen(36707)) {
                                            result = AvatarConverterUtil.getAvatarBitmap(businessDefaultAvatar, color, this.avatarSizeSmall);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return result;
    }

    @Nullable
    private Bitmap getAvatar(DistributionListModel distributionListModel, int[] contactColors) {
        synchronized (this.distributionListDefaultAvatar) {
            if (distributionListModel == null) {
                return null;
            }
            int color = ColorUtil.getInstance().getCurrentThemeGray(this.context);
            if (!ListenerUtil.mutListener.listen(36729)) {
                if ((ListenerUtil.mutListener.listen(36727) ? ((ListenerUtil.mutListener.listen(36721) ? (this.getDefaultAvatarColored() || contactColors != null) : (this.getDefaultAvatarColored() && contactColors != null)) || (ListenerUtil.mutListener.listen(36726) ? (contactColors.length >= 0) : (ListenerUtil.mutListener.listen(36725) ? (contactColors.length <= 0) : (ListenerUtil.mutListener.listen(36724) ? (contactColors.length < 0) : (ListenerUtil.mutListener.listen(36723) ? (contactColors.length != 0) : (ListenerUtil.mutListener.listen(36722) ? (contactColors.length == 0) : (contactColors.length > 0))))))) : ((ListenerUtil.mutListener.listen(36721) ? (this.getDefaultAvatarColored() || contactColors != null) : (this.getDefaultAvatarColored() && contactColors != null)) && (ListenerUtil.mutListener.listen(36726) ? (contactColors.length >= 0) : (ListenerUtil.mutListener.listen(36725) ? (contactColors.length <= 0) : (ListenerUtil.mutListener.listen(36724) ? (contactColors.length < 0) : (ListenerUtil.mutListener.listen(36723) ? (contactColors.length != 0) : (ListenerUtil.mutListener.listen(36722) ? (contactColors.length == 0) : (contactColors.length > 0))))))))) {
                    if (!ListenerUtil.mutListener.listen(36728)) {
                        // default color
                        color = contactColors[0];
                    }
                }
            }
            return AvatarConverterUtil.getAvatarBitmap(distributionListDefaultAvatar, color, this.avatarSizeSmall);
        }
    }

    @Override
    public Bitmap buildHiresDefaultAvatar(int color, int avatarType) {
        VectorDrawableCompat drawable = contactDefaultAvatar;
        if (!ListenerUtil.mutListener.listen(36732)) {
            switch(avatarType) {
                case AVATAR_GROUP:
                    if (!ListenerUtil.mutListener.listen(36730)) {
                        drawable = groupDefaultAvatar;
                    }
                    break;
                case AVATAR_BUSINESS:
                    if (!ListenerUtil.mutListener.listen(36731)) {
                        drawable = businessDefaultAvatar;
                    }
                    break;
            }
        }
        int borderWidth = (ListenerUtil.mutListener.listen(36740) ? ((ListenerUtil.mutListener.listen(36736) ? (this.avatarSizeHires % 3) : (ListenerUtil.mutListener.listen(36735) ? (this.avatarSizeHires / 3) : (ListenerUtil.mutListener.listen(36734) ? (this.avatarSizeHires - 3) : (ListenerUtil.mutListener.listen(36733) ? (this.avatarSizeHires + 3) : (this.avatarSizeHires * 3))))) % 2) : (ListenerUtil.mutListener.listen(36739) ? ((ListenerUtil.mutListener.listen(36736) ? (this.avatarSizeHires % 3) : (ListenerUtil.mutListener.listen(36735) ? (this.avatarSizeHires / 3) : (ListenerUtil.mutListener.listen(36734) ? (this.avatarSizeHires - 3) : (ListenerUtil.mutListener.listen(36733) ? (this.avatarSizeHires + 3) : (this.avatarSizeHires * 3))))) * 2) : (ListenerUtil.mutListener.listen(36738) ? ((ListenerUtil.mutListener.listen(36736) ? (this.avatarSizeHires % 3) : (ListenerUtil.mutListener.listen(36735) ? (this.avatarSizeHires / 3) : (ListenerUtil.mutListener.listen(36734) ? (this.avatarSizeHires - 3) : (ListenerUtil.mutListener.listen(36733) ? (this.avatarSizeHires + 3) : (this.avatarSizeHires * 3))))) - 2) : (ListenerUtil.mutListener.listen(36737) ? ((ListenerUtil.mutListener.listen(36736) ? (this.avatarSizeHires % 3) : (ListenerUtil.mutListener.listen(36735) ? (this.avatarSizeHires / 3) : (ListenerUtil.mutListener.listen(36734) ? (this.avatarSizeHires - 3) : (ListenerUtil.mutListener.listen(36733) ? (this.avatarSizeHires + 3) : (this.avatarSizeHires * 3))))) + 2) : ((ListenerUtil.mutListener.listen(36736) ? (this.avatarSizeHires % 3) : (ListenerUtil.mutListener.listen(36735) ? (this.avatarSizeHires / 3) : (ListenerUtil.mutListener.listen(36734) ? (this.avatarSizeHires - 3) : (ListenerUtil.mutListener.listen(36733) ? (this.avatarSizeHires + 3) : (this.avatarSizeHires * 3))))) / 2)))));
        Bitmap def = AvatarConverterUtil.getAvatarBitmap(drawable, Color.WHITE, avatarSizeHires);
        if (!ListenerUtil.mutListener.listen(36741)) {
            def.setDensity(Bitmap.DENSITY_NONE);
        }
        Bitmap.Config conf = Bitmap.Config.ARGB_8888;
        Bitmap newBitmap = Bitmap.createBitmap(def.getWidth() + borderWidth, def.getHeight() + borderWidth, conf);
        Canvas canvas = new Canvas(newBitmap);
        Paint p = new Paint();
        if (!ListenerUtil.mutListener.listen(36742)) {
            p.setColor(color);
        }
        if (!ListenerUtil.mutListener.listen(36743)) {
            canvas.drawRect(0, 0, newBitmap.getWidth(), newBitmap.getHeight(), p);
        }
        if (!ListenerUtil.mutListener.listen(36752)) {
            canvas.drawBitmap(def, (ListenerUtil.mutListener.listen(36747) ? (borderWidth % 2f) : (ListenerUtil.mutListener.listen(36746) ? (borderWidth * 2f) : (ListenerUtil.mutListener.listen(36745) ? (borderWidth - 2f) : (ListenerUtil.mutListener.listen(36744) ? (borderWidth + 2f) : (borderWidth / 2f))))), (ListenerUtil.mutListener.listen(36751) ? (borderWidth % 2f) : (ListenerUtil.mutListener.listen(36750) ? (borderWidth * 2f) : (ListenerUtil.mutListener.listen(36749) ? (borderWidth - 2f) : (ListenerUtil.mutListener.listen(36748) ? (borderWidth + 2f) : (borderWidth / 2f))))), null);
        }
        if (!ListenerUtil.mutListener.listen(36753)) {
            BitmapUtil.recycle(def);
        }
        return newBitmap;
    }

    @Override
    public Bitmap getGroupAvatarNeutral(boolean highResolution) {
        return getAvatar(null, null, highResolution, true);
    }

    @Nullable
    private Bitmap getAvatar(GroupModel groupModel, Collection<Integer> contactColors, boolean highResolution, boolean defaultOnly) {
        try {
            Bitmap groupImage = null;
            if (!ListenerUtil.mutListener.listen(36756)) {
                if (!defaultOnly) {
                    if (!ListenerUtil.mutListener.listen(36755)) {
                        groupImage = this.fileService.getGroupAvatar(groupModel);
                    }
                }
            }
            if (groupImage == null) {
                int color = ColorUtil.getInstance().getCurrentThemeGray(this.context);
                if (!ListenerUtil.mutListener.listen(36767)) {
                    if ((ListenerUtil.mutListener.listen(36765) ? ((ListenerUtil.mutListener.listen(36759) ? (this.getDefaultAvatarColored() || contactColors != null) : (this.getDefaultAvatarColored() && contactColors != null)) || (ListenerUtil.mutListener.listen(36764) ? (contactColors.size() >= 0) : (ListenerUtil.mutListener.listen(36763) ? (contactColors.size() <= 0) : (ListenerUtil.mutListener.listen(36762) ? (contactColors.size() < 0) : (ListenerUtil.mutListener.listen(36761) ? (contactColors.size() != 0) : (ListenerUtil.mutListener.listen(36760) ? (contactColors.size() == 0) : (contactColors.size() > 0))))))) : ((ListenerUtil.mutListener.listen(36759) ? (this.getDefaultAvatarColored() || contactColors != null) : (this.getDefaultAvatarColored() && contactColors != null)) && (ListenerUtil.mutListener.listen(36764) ? (contactColors.size() >= 0) : (ListenerUtil.mutListener.listen(36763) ? (contactColors.size() <= 0) : (ListenerUtil.mutListener.listen(36762) ? (contactColors.size() < 0) : (ListenerUtil.mutListener.listen(36761) ? (contactColors.size() != 0) : (ListenerUtil.mutListener.listen(36760) ? (contactColors.size() == 0) : (contactColors.size() > 0))))))))) {
                        if (!ListenerUtil.mutListener.listen(36766)) {
                            // default color
                            color = contactColors.iterator().next();
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(36770)) {
                    if (highResolution) {
                        if (!ListenerUtil.mutListener.listen(36769)) {
                            groupImage = buildHiresDefaultAvatar(color, AVATAR_GROUP);
                        }
                    } else {
                        synchronized (this.groupDefaultAvatar) {
                            if (!ListenerUtil.mutListener.listen(36768)) {
                                groupImage = AvatarConverterUtil.getAvatarBitmap(groupDefaultAvatar, color, this.avatarSizeSmall);
                            }
                        }
                    }
                }
            } else if (!highResolution) {
                // resize image!
                Bitmap converted = AvatarConverterUtil.convert(this.context.getResources(), groupImage);
                if (!ListenerUtil.mutListener.listen(36758)) {
                    if (groupImage != converted) {
                        if (!ListenerUtil.mutListener.listen(36757)) {
                            BitmapUtil.recycle(groupImage);
                        }
                    }
                }
                return converted;
            }
            return groupImage;
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(36754)) {
                logger.error("Exception", e);
            }
            // DO NOTHING
            return null;
        }
    }

    @Override
    public boolean getDefaultAvatarColored() {
        if (!ListenerUtil.mutListener.listen(36773)) {
            if (this.isDefaultAvatarColored == null) {
                if (!ListenerUtil.mutListener.listen(36772)) {
                    this.isDefaultAvatarColored = ((ListenerUtil.mutListener.listen(36771) ? (this.preferenceService == null && this.preferenceService.isDefaultContactPictureColored()) : (this.preferenceService == null || this.preferenceService.isDefaultContactPictureColored())));
                }
            }
        }
        return this.isDefaultAvatarColored;
    }
}
