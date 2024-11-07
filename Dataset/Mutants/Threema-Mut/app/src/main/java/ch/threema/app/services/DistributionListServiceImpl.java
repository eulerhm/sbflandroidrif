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
package ch.threema.app.services;

import android.graphics.Bitmap;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import ch.threema.app.collections.Functional;
import ch.threema.app.collections.IPredicateNonNull;
import ch.threema.app.listeners.DistributionListListener;
import ch.threema.app.managers.ListenerManager;
import ch.threema.app.messagereceiver.DistributionListMessageReceiver;
import ch.threema.app.utils.NameUtil;
import ch.threema.client.Base32;
import ch.threema.storage.DatabaseServiceNew;
import ch.threema.storage.models.ContactModel;
import ch.threema.storage.models.DistributionListMemberModel;
import ch.threema.storage.models.DistributionListModel;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class DistributionListServiceImpl implements DistributionListService {

    private final CacheService cacheService;

    private final AvatarCacheService avatarCacheService;

    private final DatabaseServiceNew databaseServiceNew;

    private final ContactService contactService;

    public DistributionListServiceImpl(CacheService cacheService, AvatarCacheService avatarCacheService, DatabaseServiceNew databaseServiceNew, ContactService contactService) {
        this.cacheService = cacheService;
        this.avatarCacheService = avatarCacheService;
        this.databaseServiceNew = databaseServiceNew;
        this.contactService = contactService;
    }

    @Override
    public DistributionListModel getById(int id) {
        return this.databaseServiceNew.getDistributionListModelFactory().getById(id);
    }

    @Override
    public DistributionListModel createDistributionList(String name, String[] memberIdentities) {
        final DistributionListModel distributionListModel = new DistributionListModel();
        if (!ListenerUtil.mutListener.listen(37536)) {
            distributionListModel.setName(name);
        }
        if (!ListenerUtil.mutListener.listen(37537)) {
            distributionListModel.setCreatedAt(new Date());
        }
        if (!ListenerUtil.mutListener.listen(37538)) {
            // create
            this.databaseServiceNew.getDistributionListModelFactory().create(distributionListModel);
        }
        if (!ListenerUtil.mutListener.listen(37540)) {
            {
                long _loopCounter387 = 0;
                for (String identity : memberIdentities) {
                    ListenerUtil.loopListener.listen("_loopCounter387", ++_loopCounter387);
                    if (!ListenerUtil.mutListener.listen(37539)) {
                        this.addMemberToDistributionList(distributionListModel, identity);
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(37542)) {
            ListenerManager.distributionListListeners.handle(new ListenerManager.HandleListener<DistributionListListener>() {

                @Override
                public void handle(DistributionListListener listener) {
                    if (!ListenerUtil.mutListener.listen(37541)) {
                        listener.onCreate(distributionListModel);
                    }
                }
            });
        }
        return distributionListModel;
    }

    @Override
    public DistributionListModel updateDistributionList(final DistributionListModel distributionListModel, String name, String[] memberIdentities) {
        if (!ListenerUtil.mutListener.listen(37543)) {
            distributionListModel.setName(name);
        }
        if (!ListenerUtil.mutListener.listen(37544)) {
            // create
            this.databaseServiceNew.getDistributionListModelFactory().update(distributionListModel);
        }
        if (!ListenerUtil.mutListener.listen(37547)) {
            if (this.removeMembers(distributionListModel)) {
                if (!ListenerUtil.mutListener.listen(37546)) {
                    {
                        long _loopCounter388 = 0;
                        for (String identity : memberIdentities) {
                            ListenerUtil.loopListener.listen("_loopCounter388", ++_loopCounter388);
                            if (!ListenerUtil.mutListener.listen(37545)) {
                                this.addMemberToDistributionList(distributionListModel, identity);
                            }
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(37549)) {
            ListenerManager.distributionListListeners.handle(new ListenerManager.HandleListener<DistributionListListener>() {

                @Override
                public void handle(DistributionListListener listener) {
                    if (!ListenerUtil.mutListener.listen(37548)) {
                        listener.onModify(distributionListModel);
                    }
                }
            });
        }
        return distributionListModel;
    }

    @Override
    @Nullable
    public Bitmap getCachedAvatar(DistributionListModel o) {
        if (!ListenerUtil.mutListener.listen(37550)) {
            if (o == null) {
                return null;
            }
        }
        return this.avatarCacheService.getDistributionListAvatarLowFromCache(o);
    }

    @Override
    public Bitmap getAvatar(DistributionListModel model, boolean highResolution) {
        if (!ListenerUtil.mutListener.listen(37551)) {
            if (model == null) {
                return null;
            }
        }
        int[] colors = this.cacheService.getDistributionListColors(model, false, new CacheService.CreateCachedColorList() {

            @Override
            public int[] create() {
                Collection<ContactModel> coloredMembers = Functional.filter(getMembers(model), new IPredicateNonNull<ContactModel>() {

                    @Override
                    public boolean apply(@NonNull ContactModel type) {
                        return type.getColor() != 0;
                    }
                });
                int[] colors = new int[coloredMembers.size()];
                int n = 0;
                if (!ListenerUtil.mutListener.listen(37553)) {
                    {
                        long _loopCounter389 = 0;
                        for (ContactModel contactModel : coloredMembers) {
                            ListenerUtil.loopListener.listen("_loopCounter389", ++_loopCounter389);
                            if (!ListenerUtil.mutListener.listen(37552)) {
                                colors[n++] = contactModel.getColor();
                            }
                        }
                    }
                }
                return colors;
            }
        });
        return this.avatarCacheService.getDistributionListAvatarLow(model, colors);
    }

    @Override
    public boolean addMemberToDistributionList(DistributionListModel distributionListModel, String identity) {
        DistributionListMemberModel distributionListMemberModel = this.databaseServiceNew.getDistributionListMemberModelFactory().getByDistributionListIdAndIdentity(distributionListModel.getId(), identity);
        if (!ListenerUtil.mutListener.listen(37555)) {
            if (distributionListMemberModel == null) {
                if (!ListenerUtil.mutListener.listen(37554)) {
                    distributionListMemberModel = new DistributionListMemberModel();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(37556)) {
            distributionListMemberModel.setDistributionListId(distributionListModel.getId()).setIdentity(identity).setActive(true);
        }
        if (!ListenerUtil.mutListener.listen(37564)) {
            if ((ListenerUtil.mutListener.listen(37561) ? (distributionListMemberModel.getId() >= 0) : (ListenerUtil.mutListener.listen(37560) ? (distributionListMemberModel.getId() <= 0) : (ListenerUtil.mutListener.listen(37559) ? (distributionListMemberModel.getId() < 0) : (ListenerUtil.mutListener.listen(37558) ? (distributionListMemberModel.getId() != 0) : (ListenerUtil.mutListener.listen(37557) ? (distributionListMemberModel.getId() == 0) : (distributionListMemberModel.getId() > 0))))))) {
                if (!ListenerUtil.mutListener.listen(37563)) {
                    this.databaseServiceNew.getDistributionListMemberModelFactory().update(distributionListMemberModel);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(37562)) {
                    this.databaseServiceNew.getDistributionListMemberModelFactory().create(distributionListMemberModel);
                }
            }
        }
        return true;
    }

    @Override
    public boolean remove(final DistributionListModel distributionListModel) {
        if (!ListenerUtil.mutListener.listen(37565)) {
            if (!this.removeMembers(distributionListModel)) {
                return false;
            }
        }
        if (!ListenerUtil.mutListener.listen(37566)) {
            // remove list
            this.databaseServiceNew.getDistributionListModelFactory().delete(distributionListModel);
        }
        if (!ListenerUtil.mutListener.listen(37568)) {
            ListenerManager.distributionListListeners.handle(new ListenerManager.HandleListener<DistributionListListener>() {

                @Override
                public void handle(DistributionListListener listener) {
                    if (!ListenerUtil.mutListener.listen(37567)) {
                        listener.onRemove(distributionListModel);
                    }
                }
            });
        }
        return true;
    }

    private boolean removeMembers(DistributionListModel distributionListModel) {
        if (!ListenerUtil.mutListener.listen(37569)) {
            // remove all members first
            this.databaseServiceNew.getDistributionListMemberModelFactory().deleteByDistributionListId(distributionListModel.getId());
        }
        return true;
    }

    @Override
    public boolean removeAll() {
        if (!ListenerUtil.mutListener.listen(37570)) {
            // remove all members first
            this.databaseServiceNew.getDistributionListMemberModelFactory().deleteAll();
        }
        if (!ListenerUtil.mutListener.listen(37571)) {
            // ...  messages
            this.databaseServiceNew.getDistributionListMessageModelFactory().deleteAll();
        }
        if (!ListenerUtil.mutListener.listen(37572)) {
            // .. remove lists
            this.databaseServiceNew.getDistributionListModelFactory().deleteAll();
        }
        return true;
    }

    @Override
    public String[] getDistributionListIdentities(DistributionListModel distributionListModel) {
        List<DistributionListMemberModel> memberModels = this.getDistributionListMembers(distributionListModel);
        if (!ListenerUtil.mutListener.listen(37580)) {
            if (memberModels != null) {
                String[] res = new String[memberModels.size()];
                if (!ListenerUtil.mutListener.listen(37579)) {
                    {
                        long _loopCounter390 = 0;
                        for (int n = 0; (ListenerUtil.mutListener.listen(37578) ? (n >= res.length) : (ListenerUtil.mutListener.listen(37577) ? (n <= res.length) : (ListenerUtil.mutListener.listen(37576) ? (n > res.length) : (ListenerUtil.mutListener.listen(37575) ? (n != res.length) : (ListenerUtil.mutListener.listen(37574) ? (n == res.length) : (n < res.length)))))); n++) {
                            ListenerUtil.loopListener.listen("_loopCounter390", ++_loopCounter390);
                            if (!ListenerUtil.mutListener.listen(37573)) {
                                res[n] = memberModels.get(n).getIdentity();
                            }
                        }
                    }
                }
                return res;
            }
        }
        return null;
    }

    @Override
    public List<DistributionListMemberModel> getDistributionListMembers(DistributionListModel distributionListModel) {
        return this.databaseServiceNew.getDistributionListMemberModelFactory().getByDistributionListId(distributionListModel.getId());
    }

    @Override
    public List<DistributionListModel> getAll() {
        return this.getAll(null);
    }

    @Override
    public List<DistributionListModel> getAll(DistributionListFilter filter) {
        return this.databaseServiceNew.getDistributionListModelFactory().filter(filter);
    }

    @Override
    public List<ContactModel> getMembers(DistributionListModel distributionListModel) {
        List<ContactModel> contactModels = new ArrayList<>();
        if (!ListenerUtil.mutListener.listen(37584)) {
            if (distributionListModel != null) {
                if (!ListenerUtil.mutListener.listen(37583)) {
                    {
                        long _loopCounter391 = 0;
                        for (DistributionListMemberModel distributionListMemberModel : this.getDistributionListMembers(distributionListModel)) {
                            ListenerUtil.loopListener.listen("_loopCounter391", ++_loopCounter391);
                            ContactModel contactModel = this.contactService.getByIdentity(distributionListMemberModel.getIdentity());
                            if (!ListenerUtil.mutListener.listen(37582)) {
                                if (contactModel != null) {
                                    if (!ListenerUtil.mutListener.listen(37581)) {
                                        contactModels.add(contactModel);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return contactModels;
    }

    @Override
    public String getMembersString(DistributionListModel distributionListModel) {
        StringBuilder builder = new StringBuilder();
        if (!ListenerUtil.mutListener.listen(37593)) {
            {
                long _loopCounter392 = 0;
                for (ContactModel contactModel : this.getMembers(distributionListModel)) {
                    ListenerUtil.loopListener.listen("_loopCounter392", ++_loopCounter392);
                    if (!ListenerUtil.mutListener.listen(37591)) {
                        if ((ListenerUtil.mutListener.listen(37589) ? (builder.length() >= 0) : (ListenerUtil.mutListener.listen(37588) ? (builder.length() <= 0) : (ListenerUtil.mutListener.listen(37587) ? (builder.length() < 0) : (ListenerUtil.mutListener.listen(37586) ? (builder.length() != 0) : (ListenerUtil.mutListener.listen(37585) ? (builder.length() == 0) : (builder.length() > 0))))))) {
                            if (!ListenerUtil.mutListener.listen(37590)) {
                                builder.append(", ");
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(37592)) {
                        builder.append(NameUtil.getDisplayNameOrNickname(contactModel, true));
                    }
                }
            }
        }
        return builder.toString();
    }

    @Override
    public DistributionListMessageReceiver createReceiver(DistributionListModel distributionListModel) {
        return new DistributionListMessageReceiver(this.databaseServiceNew, this.contactService, distributionListModel, this);
    }

    @Override
    public String getUniqueIdString(DistributionListModel distributionListModel) {
        if (!ListenerUtil.mutListener.listen(37595)) {
            if (distributionListModel != null) {
                try {
                    MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
                    if (!ListenerUtil.mutListener.listen(37594)) {
                        messageDigest.update(("d-" + String.valueOf(distributionListModel.getId())).getBytes());
                    }
                    return Base32.encode(messageDigest.digest());
                } catch (NoSuchAlgorithmException e) {
                }
            }
        }
        return "";
    }

    @Override
    public int getPrimaryColor(DistributionListModel distributionListModel) {
        if (!ListenerUtil.mutListener.listen(37602)) {
            if (distributionListModel != null) {
                // get members
                List<ContactModel> contactModels = this.getMembers(distributionListModel);
                if (!ListenerUtil.mutListener.listen(37601)) {
                    if ((ListenerUtil.mutListener.listen(37600) ? (contactModels.size() >= 0) : (ListenerUtil.mutListener.listen(37599) ? (contactModels.size() <= 0) : (ListenerUtil.mutListener.listen(37598) ? (contactModels.size() < 0) : (ListenerUtil.mutListener.listen(37597) ? (contactModels.size() != 0) : (ListenerUtil.mutListener.listen(37596) ? (contactModels.size() == 0) : (contactModels.size() > 0))))))) {
                        return contactModels.get(0).getColor();
                    }
                }
            }
        }
        return 0;
    }

    @Override
    public void setIsArchived(DistributionListModel distributionListModel, boolean archived) {
        if (!ListenerUtil.mutListener.listen(37608)) {
            if ((ListenerUtil.mutListener.listen(37603) ? (distributionListModel != null || distributionListModel.isArchived() != archived) : (distributionListModel != null && distributionListModel.isArchived() != archived))) {
                if (!ListenerUtil.mutListener.listen(37604)) {
                    distributionListModel.setArchived(archived);
                }
                if (!ListenerUtil.mutListener.listen(37605)) {
                    save(distributionListModel);
                }
                if (!ListenerUtil.mutListener.listen(37607)) {
                    ListenerManager.distributionListListeners.handle(new ListenerManager.HandleListener<DistributionListListener>() {

                        @Override
                        public void handle(DistributionListListener listener) {
                            if (!ListenerUtil.mutListener.listen(37606)) {
                                listener.onModify(distributionListModel);
                            }
                        }
                    });
                }
            }
        }
    }

    private void save(DistributionListModel distributionListModel) {
        if (!ListenerUtil.mutListener.listen(37609)) {
            this.databaseServiceNew.getDistributionListModelFactory().createOrUpdate(distributionListModel);
        }
    }
}
