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
package ch.threema.app.services;

import android.annotation.TargetApi;
import android.app.Person;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ShortcutInfo;
import android.content.pm.ShortcutManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Icon;
import android.net.Uri;
import android.os.Build;
import android.os.SystemClock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.ArrayList;
import java.util.List;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.pm.ShortcutInfoCompat;
import androidx.core.content.pm.ShortcutManagerCompat;
import androidx.core.graphics.drawable.IconCompat;
import androidx.vectordrawable.graphics.drawable.VectorDrawableCompat;
import ch.threema.app.R;
import ch.threema.app.ThreemaApplication;
import ch.threema.app.activities.ComposeMessageActivity;
import ch.threema.app.utils.AvatarConverterUtil;
import ch.threema.app.utils.BitmapUtil;
import ch.threema.app.utils.NameUtil;
import ch.threema.app.utils.TestUtil;
import ch.threema.app.voip.activities.CallActivity;
import ch.threema.app.voip.services.VoipCallService;
import ch.threema.storage.models.ContactModel;
import ch.threema.storage.models.DistributionListModel;
import ch.threema.storage.models.GroupModel;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class ShortcutServiceImpl implements ShortcutService {

    private static final Logger logger = LoggerFactory.getLogger(ShortcutService.class);

    private final Context context;

    private final ContactService contactService;

    private final GroupService groupService;

    private final DistributionListService distributionListService;

    public ShortcutServiceImpl(Context context, ContactService contactService, GroupService groupService, DistributionListService distributionListService) {
        this.context = context;
        this.contactService = contactService;
        this.groupService = groupService;
        this.distributionListService = distributionListService;
    }

    private class CommonShortcutInfo {

        Intent intent;

        Bitmap bitmap;

        String longLabel;
    }

    @TargetApi(Build.VERSION_CODES.O)
    @Override
    public void updateShortcut(ContactModel contactModel) {
        if (!ListenerUtil.mutListener.listen(40951)) {
            if ((ListenerUtil.mutListener.listen(40938) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.O) : (ListenerUtil.mutListener.listen(40937) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.O) : (ListenerUtil.mutListener.listen(40936) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) : (ListenerUtil.mutListener.listen(40935) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.O) : (ListenerUtil.mutListener.listen(40934) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.O) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O))))))) {
                ShortcutManager shortcutManager = context.getSystemService(ShortcutManager.class);
                String uniqueId = contactService.getUniqueIdString(contactModel);
                if (!ListenerUtil.mutListener.listen(40950)) {
                    if (!TestUtil.empty(uniqueId)) {
                        List<ShortcutInfo> matchingShortcuts = new ArrayList<>();
                        if (!ListenerUtil.mutListener.listen(40942)) {
                            {
                                long _loopCounter466 = 0;
                                for (ShortcutInfo shortcutInfo : shortcutManager.getPinnedShortcuts()) {
                                    ListenerUtil.loopListener.listen("_loopCounter466", ++_loopCounter466);
                                    if (!ListenerUtil.mutListener.listen(40941)) {
                                        if (shortcutInfo.getId().equals(TYPE_CHAT + uniqueId)) {
                                            if (!ListenerUtil.mutListener.listen(40940)) {
                                                matchingShortcuts.add(getShortcutInfo(contactModel, TYPE_CHAT));
                                            }
                                        } else if (shortcutInfo.getId().equals(TYPE_CALL + uniqueId)) {
                                            if (!ListenerUtil.mutListener.listen(40939)) {
                                                matchingShortcuts.add(getShortcutInfo(contactModel, TYPE_CALL));
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(40949)) {
                            if ((ListenerUtil.mutListener.listen(40947) ? (matchingShortcuts.size() >= 0) : (ListenerUtil.mutListener.listen(40946) ? (matchingShortcuts.size() <= 0) : (ListenerUtil.mutListener.listen(40945) ? (matchingShortcuts.size() < 0) : (ListenerUtil.mutListener.listen(40944) ? (matchingShortcuts.size() != 0) : (ListenerUtil.mutListener.listen(40943) ? (matchingShortcuts.size() == 0) : (matchingShortcuts.size() > 0))))))) {
                                if (!ListenerUtil.mutListener.listen(40948)) {
                                    shortcutManager.updateShortcuts(matchingShortcuts);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @TargetApi(Build.VERSION_CODES.O)
    @Override
    public void updateShortcut(GroupModel groupModel) {
        if (!ListenerUtil.mutListener.listen(40968)) {
            if ((ListenerUtil.mutListener.listen(40956) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.O) : (ListenerUtil.mutListener.listen(40955) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.O) : (ListenerUtil.mutListener.listen(40954) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) : (ListenerUtil.mutListener.listen(40953) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.O) : (ListenerUtil.mutListener.listen(40952) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.O) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O))))))) {
                ShortcutManager shortcutManager = context.getSystemService(ShortcutManager.class);
                String uniqueId = groupService.getUniqueIdString(groupModel);
                if (!ListenerUtil.mutListener.listen(40967)) {
                    if (!TestUtil.empty(uniqueId)) {
                        List<ShortcutInfo> matchingShortcuts = new ArrayList<>();
                        if (!ListenerUtil.mutListener.listen(40959)) {
                            {
                                long _loopCounter467 = 0;
                                for (ShortcutInfo shortcutInfo : shortcutManager.getPinnedShortcuts()) {
                                    ListenerUtil.loopListener.listen("_loopCounter467", ++_loopCounter467);
                                    if (!ListenerUtil.mutListener.listen(40958)) {
                                        if (shortcutInfo.getId().equals(TYPE_CHAT + uniqueId)) {
                                            if (!ListenerUtil.mutListener.listen(40957)) {
                                                matchingShortcuts.add(getShortcutInfo(groupModel));
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(40966)) {
                            if ((ListenerUtil.mutListener.listen(40964) ? (matchingShortcuts.size() >= 0) : (ListenerUtil.mutListener.listen(40963) ? (matchingShortcuts.size() <= 0) : (ListenerUtil.mutListener.listen(40962) ? (matchingShortcuts.size() < 0) : (ListenerUtil.mutListener.listen(40961) ? (matchingShortcuts.size() != 0) : (ListenerUtil.mutListener.listen(40960) ? (matchingShortcuts.size() == 0) : (matchingShortcuts.size() > 0))))))) {
                                if (!ListenerUtil.mutListener.listen(40965)) {
                                    shortcutManager.updateShortcuts(matchingShortcuts);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @TargetApi(Build.VERSION_CODES.O)
    @Override
    public void updateShortcut(DistributionListModel distributionListModel) {
        if (!ListenerUtil.mutListener.listen(40985)) {
            if ((ListenerUtil.mutListener.listen(40973) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.O) : (ListenerUtil.mutListener.listen(40972) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.O) : (ListenerUtil.mutListener.listen(40971) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) : (ListenerUtil.mutListener.listen(40970) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.O) : (ListenerUtil.mutListener.listen(40969) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.O) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O))))))) {
                ShortcutManager shortcutManager = context.getSystemService(ShortcutManager.class);
                String uniqueId = distributionListService.getUniqueIdString(distributionListModel);
                if (!ListenerUtil.mutListener.listen(40984)) {
                    if (!TestUtil.empty(uniqueId)) {
                        List<ShortcutInfo> matchingShortcuts = new ArrayList<>();
                        if (!ListenerUtil.mutListener.listen(40976)) {
                            {
                                long _loopCounter468 = 0;
                                for (ShortcutInfo shortcutInfo : shortcutManager.getPinnedShortcuts()) {
                                    ListenerUtil.loopListener.listen("_loopCounter468", ++_loopCounter468);
                                    if (!ListenerUtil.mutListener.listen(40975)) {
                                        if (shortcutInfo.getId().equals(TYPE_CHAT + uniqueId)) {
                                            if (!ListenerUtil.mutListener.listen(40974)) {
                                                matchingShortcuts.add(getShortcutInfo(distributionListModel));
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(40983)) {
                            if ((ListenerUtil.mutListener.listen(40981) ? (matchingShortcuts.size() >= 0) : (ListenerUtil.mutListener.listen(40980) ? (matchingShortcuts.size() <= 0) : (ListenerUtil.mutListener.listen(40979) ? (matchingShortcuts.size() < 0) : (ListenerUtil.mutListener.listen(40978) ? (matchingShortcuts.size() != 0) : (ListenerUtil.mutListener.listen(40977) ? (matchingShortcuts.size() == 0) : (matchingShortcuts.size() > 0))))))) {
                                if (!ListenerUtil.mutListener.listen(40982)) {
                                    shortcutManager.updateShortcuts(matchingShortcuts);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public void createShortcut(ContactModel contactModel, int type) {
        ShortcutInfoCompat shortcutInfoCompat = getShortcutInfoCompat(contactModel, type);
        if (!ListenerUtil.mutListener.listen(40987)) {
            if (shortcutInfoCompat != null) {
                if (!ListenerUtil.mutListener.listen(40986)) {
                    ShortcutManagerCompat.requestPinShortcut(context, shortcutInfoCompat, null);
                }
            }
        }
    }

    @Override
    public void createShortcut(GroupModel groupModel) {
        ShortcutInfoCompat shortcutInfoCompat = getShortcutInfoCompat(groupModel);
        if (!ListenerUtil.mutListener.listen(40989)) {
            if (shortcutInfoCompat != null) {
                if (!ListenerUtil.mutListener.listen(40988)) {
                    ShortcutManagerCompat.requestPinShortcut(context, shortcutInfoCompat, null);
                }
            }
        }
    }

    @Override
    public void createShortcut(DistributionListModel distributionListModel) {
        ShortcutInfoCompat shortcutInfoCompat = getShortcutInfoCompat(distributionListModel);
        if (!ListenerUtil.mutListener.listen(40991)) {
            if (shortcutInfoCompat != null) {
                if (!ListenerUtil.mutListener.listen(40990)) {
                    ShortcutManagerCompat.requestPinShortcut(context, shortcutInfoCompat, null);
                }
            }
        }
    }

    @NonNull
    private CommonShortcutInfo getCommonShortcutInfo(ContactModel contactModel, int type) {
        CommonShortcutInfo commonShortcutInfo = new CommonShortcutInfo();
        if (!ListenerUtil.mutListener.listen(41000)) {
            if (type == TYPE_CALL) {
                if (!ListenerUtil.mutListener.listen(40996)) {
                    commonShortcutInfo.intent = getCallShortcutIntent();
                }
                if (!ListenerUtil.mutListener.listen(40997)) {
                    commonShortcutInfo.intent.putExtra(VoipCallService.EXTRA_CONTACT_IDENTITY, contactModel.getIdentity());
                }
                if (!ListenerUtil.mutListener.listen(40998)) {
                    commonShortcutInfo.longLabel = String.format(context.getString(R.string.threema_call_with), NameUtil.getDisplayNameOrNickname(contactModel, true));
                }
                VectorDrawableCompat phoneDrawable = VectorDrawableCompat.create(context.getResources(), R.drawable.ic_phone_locked, null);
                Bitmap phoneBitmap = AvatarConverterUtil.getAvatarBitmap(phoneDrawable, Color.BLACK, context.getResources().getDimensionPixelSize(R.dimen.shortcut_overlay_size));
                if (!ListenerUtil.mutListener.listen(40999)) {
                    commonShortcutInfo.bitmap = BitmapUtil.addOverlay(getRoundBitmap(contactService.getAvatar(contactModel, false)), phoneBitmap, context.getResources().getDimensionPixelSize(R.dimen.call_shortcut_shadow_offset));
                }
            } else {
                if (!ListenerUtil.mutListener.listen(40992)) {
                    commonShortcutInfo.intent = getChatShortcutIntent();
                }
                if (!ListenerUtil.mutListener.listen(40993)) {
                    commonShortcutInfo.intent.putExtra(ThreemaApplication.INTENT_DATA_CONTACT, contactModel.getIdentity());
                }
                if (!ListenerUtil.mutListener.listen(40994)) {
                    commonShortcutInfo.longLabel = String.format(context.getString(R.string.chat_with), NameUtil.getDisplayNameOrNickname(contactModel, true));
                }
                if (!ListenerUtil.mutListener.listen(40995)) {
                    commonShortcutInfo.bitmap = getRoundBitmap(contactService.getAvatar(contactModel, false));
                }
            }
        }
        return commonShortcutInfo;
    }

    @Nullable
    private ShortcutInfoCompat getShortcutInfoCompat(ContactModel contactModel, int type) {
        CommonShortcutInfo commonShortcutInfo = getCommonShortcutInfo(contactModel, type);
        try {
            return new ShortcutInfoCompat.Builder(context, type + contactService.getUniqueIdString(contactModel)).setIcon(IconCompat.createWithBitmap(commonShortcutInfo.bitmap)).setShortLabel(NameUtil.getDisplayNameOrNickname(contactModel, true)).setLongLabel(commonShortcutInfo.longLabel).setIntent(commonShortcutInfo.intent).build();
        } catch (IllegalArgumentException e) {
            if (!ListenerUtil.mutListener.listen(41001)) {
                logger.error("Exception", e);
            }
        }
        return null;
    }

    @Nullable
    private ShortcutInfoCompat getShortcutInfoCompat(GroupModel groupModel) {
        Intent intent = getChatShortcutIntent();
        if (!ListenerUtil.mutListener.listen(41002)) {
            intent.putExtra(ThreemaApplication.INTENT_DATA_GROUP, groupModel.getId());
        }
        Bitmap avatarBitmap = getRoundBitmap(groupService.getAvatar(groupModel, false));
        if (!ListenerUtil.mutListener.listen(41003)) {
            if (avatarBitmap != null) {
                return new ShortcutInfoCompat.Builder(context, TYPE_CHAT + groupService.getUniqueIdString(groupModel)).setIcon(IconCompat.createWithBitmap(avatarBitmap)).setShortLabel(groupModel.getName()).setLongLabel(String.format(context.getString(R.string.chat_with), groupModel.getName())).setIntent(intent).build();
            }
        }
        return null;
    }

    @Nullable
    private ShortcutInfoCompat getShortcutInfoCompat(DistributionListModel distributionListModel) {
        Intent intent = getChatShortcutIntent();
        if (!ListenerUtil.mutListener.listen(41004)) {
            intent.putExtra(ThreemaApplication.INTENT_DATA_DISTRIBUTION_LIST, distributionListModel.getId());
        }
        Bitmap avatarBitmap = getRoundBitmap(distributionListService.getAvatar(distributionListModel, false));
        if (!ListenerUtil.mutListener.listen(41005)) {
            if (avatarBitmap != null) {
                return new ShortcutInfoCompat.Builder(context, TYPE_CHAT + distributionListService.getUniqueIdString(distributionListModel)).setIcon(IconCompat.createWithBitmap(avatarBitmap)).setShortLabel(distributionListModel.getName()).setLongLabel(String.format(context.getString(R.string.chat_with), distributionListModel.getName())).setIntent(intent).build();
            }
        }
        return null;
    }

    @TargetApi(Build.VERSION_CODES.O)
    @Override
    public ShortcutInfo getShortcutInfo(ContactModel contactModel, int type) {
        CommonShortcutInfo commonShortcutInfo = getCommonShortcutInfo(contactModel, type);
        if ((ListenerUtil.mutListener.listen(41010) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.Q) : (ListenerUtil.mutListener.listen(41009) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.Q) : (ListenerUtil.mutListener.listen(41008) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) : (ListenerUtil.mutListener.listen(41007) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.Q) : (ListenerUtil.mutListener.listen(41006) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.Q) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q))))))) {
            return new ShortcutInfo.Builder(context, type + contactService.getUniqueIdString(contactModel)).setIcon(Icon.createWithAdaptiveBitmap(commonShortcutInfo.bitmap)).setShortLabel(NameUtil.getDisplayNameOrNickname(contactModel, true)).setLongLabel(commonShortcutInfo.longLabel).setLongLived(true).setPerson(new Person.Builder().setName(contactModel.getIdentity()).setIcon(Icon.createWithBitmap((commonShortcutInfo.bitmap))).build()).setIntent(commonShortcutInfo.intent).build();
        } else {
            return new ShortcutInfo.Builder(context, type + contactService.getUniqueIdString(contactModel)).setIcon(Icon.createWithAdaptiveBitmap(commonShortcutInfo.bitmap)).setShortLabel(NameUtil.getDisplayNameOrNickname(contactModel, true)).setLongLabel(commonShortcutInfo.longLabel).setIntent(commonShortcutInfo.intent).build();
        }
    }

    @Nullable
    @TargetApi(Build.VERSION_CODES.O)
    @Override
    public ShortcutInfo getShortcutInfo(GroupModel groupModel) {
        Intent intent = getChatShortcutIntent();
        if (!ListenerUtil.mutListener.listen(41011)) {
            intent.putExtra(ThreemaApplication.INTENT_DATA_GROUP, groupModel.getId());
        }
        Bitmap avatarBitmap = getRoundBitmap(groupService.getAvatar(groupModel, false));
        if (!ListenerUtil.mutListener.listen(41018)) {
            if (avatarBitmap != null) {
                if (!ListenerUtil.mutListener.listen(41017)) {
                    if ((ListenerUtil.mutListener.listen(41016) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.Q) : (ListenerUtil.mutListener.listen(41015) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.Q) : (ListenerUtil.mutListener.listen(41014) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) : (ListenerUtil.mutListener.listen(41013) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.Q) : (ListenerUtil.mutListener.listen(41012) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.Q) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q))))))) {
                        return new ShortcutInfo.Builder(context, TYPE_CHAT + groupService.getUniqueIdString(groupModel)).setIcon(Icon.createWithAdaptiveBitmap(avatarBitmap)).setShortLabel(groupModel.getName()).setLongLived(true).setLongLabel(String.format(context.getString(R.string.chat_with), groupModel.getName())).setIntent(intent).build();
                    } else {
                        return new ShortcutInfo.Builder(context, TYPE_CHAT + groupService.getUniqueIdString(groupModel)).setIcon(Icon.createWithAdaptiveBitmap(avatarBitmap)).setShortLabel(groupModel.getName()).setLongLabel(String.format(context.getString(R.string.chat_with), groupModel.getName())).setIntent(intent).build();
                    }
                }
            }
        }
        return null;
    }

    @Nullable
    @TargetApi(Build.VERSION_CODES.O)
    @Override
    public ShortcutInfo getShortcutInfo(DistributionListModel distributionListModel) {
        Intent intent = getChatShortcutIntent();
        if (!ListenerUtil.mutListener.listen(41019)) {
            intent.putExtra(ThreemaApplication.INTENT_DATA_DISTRIBUTION_LIST, distributionListModel.getId());
        }
        Bitmap avatarBitmap = getRoundBitmap(distributionListService.getAvatar(distributionListModel, false));
        if (!ListenerUtil.mutListener.listen(41026)) {
            if (avatarBitmap != null) {
                if (!ListenerUtil.mutListener.listen(41025)) {
                    if ((ListenerUtil.mutListener.listen(41024) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.Q) : (ListenerUtil.mutListener.listen(41023) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.Q) : (ListenerUtil.mutListener.listen(41022) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) : (ListenerUtil.mutListener.listen(41021) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.Q) : (ListenerUtil.mutListener.listen(41020) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.Q) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q))))))) {
                        return new ShortcutInfo.Builder(context, TYPE_CHAT + distributionListService.getUniqueIdString(distributionListModel)).setIcon(Icon.createWithAdaptiveBitmap(avatarBitmap)).setShortLabel(distributionListModel.getName()).setLongLived(true).setLongLabel(String.format(context.getString(R.string.chat_with), distributionListModel.getName())).setIntent(intent).build();
                    } else {
                        return new ShortcutInfo.Builder(context, TYPE_CHAT + distributionListService.getUniqueIdString(distributionListModel)).setIcon(Icon.createWithAdaptiveBitmap(avatarBitmap)).setShortLabel(distributionListModel.getName()).setLongLabel(String.format(context.getString(R.string.chat_with), distributionListModel.getName())).setIntent(intent).build();
                    }
                }
            }
        }
        return null;
    }

    private Intent getChatShortcutIntent() {
        Intent intent = new Intent(context, ComposeMessageActivity.class);
        if (!ListenerUtil.mutListener.listen(41027)) {
            intent.setData((Uri.parse("foobar://" + SystemClock.elapsedRealtime())));
        }
        if (!ListenerUtil.mutListener.listen(41028)) {
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        }
        if (!ListenerUtil.mutListener.listen(41029)) {
            intent.setAction(Intent.ACTION_MAIN);
        }
        return intent;
    }

    private Intent getCallShortcutIntent() {
        Intent intent = new Intent(context, CallActivity.class);
        if (!ListenerUtil.mutListener.listen(41030)) {
            intent.setData((Uri.parse("foobar://" + SystemClock.elapsedRealtime())));
        }
        if (!ListenerUtil.mutListener.listen(41031)) {
            intent.setAction(Intent.ACTION_MAIN);
        }
        if (!ListenerUtil.mutListener.listen(41032)) {
            intent.putExtra(CallActivity.EXTRA_CALL_FROM_SHORTCUT, true);
        }
        if (!ListenerUtil.mutListener.listen(41033)) {
            intent.putExtra(VoipCallService.EXTRA_IS_INITIATOR, true);
        }
        if (!ListenerUtil.mutListener.listen(41034)) {
            intent.putExtra(VoipCallService.EXTRA_CALL_ID, -1L);
        }
        return intent;
    }

    @Nullable
    private Bitmap getRoundBitmap(@Nullable Bitmap src) {
        if (!ListenerUtil.mutListener.listen(41035)) {
            if (src != null) {
                return AvatarConverterUtil.convertToRound(context.getResources(), BitmapUtil.replaceTransparency(src, Color.WHITE), Color.WHITE, null, src.getWidth());
            }
        }
        return null;
    }
}
