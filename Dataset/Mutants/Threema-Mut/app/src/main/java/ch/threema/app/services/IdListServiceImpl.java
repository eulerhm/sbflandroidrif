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

import android.content.Context;
import android.widget.Toast;
import java.util.Arrays;
import java.util.List;
import ch.threema.app.R;
import ch.threema.app.listeners.ContactListener;
import ch.threema.app.managers.ListenerManager;
import ch.threema.storage.models.ContactModel;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class IdListServiceImpl implements IdListService {

    private final Object lock = new Object();

    private String[] ids;

    private final String uniqueListName;

    private final PreferenceService preferenceService;

    public IdListServiceImpl(String uniqueListName, PreferenceService preferenceService) {
        this.uniqueListName = uniqueListName;
        this.preferenceService = preferenceService;
        if (!ListenerUtil.mutListener.listen(38620)) {
            this.ids = preferenceService.getList(this.uniqueListName);
        }
    }

    @Override
    public boolean has(String id) {
        if (!ListenerUtil.mutListener.listen(38621)) {
            if (this.ids != null) {
                synchronized (this.lock) {
                    return Arrays.asList(this.ids).contains(id);
                }
            }
        }
        return false;
    }

    @Override
    public void remove(String id) {
        if (!ListenerUtil.mutListener.listen(38633)) {
            if (this.ids != null) {
                synchronized (this.lock) {
                    List<String> idList = Arrays.asList(this.ids);
                    if (!ListenerUtil.mutListener.listen(38632)) {
                        if (idList.contains(id)) {
                            String[] newIdentities = new String[(ListenerUtil.mutListener.listen(38625) ? (idList.size() % 1) : (ListenerUtil.mutListener.listen(38624) ? (idList.size() / 1) : (ListenerUtil.mutListener.listen(38623) ? (idList.size() * 1) : (ListenerUtil.mutListener.listen(38622) ? (idList.size() + 1) : (idList.size() - 1)))))];
                            int pos = 0;
                            if (!ListenerUtil.mutListener.listen(38629)) {
                                {
                                    long _loopCounter426 = 0;
                                    for (String other : idList) {
                                        ListenerUtil.loopListener.listen("_loopCounter426", ++_loopCounter426);
                                        if (!ListenerUtil.mutListener.listen(38628)) {
                                            if ((ListenerUtil.mutListener.listen(38626) ? (other != null || !other.equals(id)) : (other != null && !other.equals(id)))) {
                                                if (!ListenerUtil.mutListener.listen(38627)) {
                                                    newIdentities[pos++] = other;
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(38630)) {
                                this.preferenceService.setList(this.uniqueListName, newIdentities);
                            }
                            if (!ListenerUtil.mutListener.listen(38631)) {
                                this.ids = newIdentities;
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public void add(String id) {
        if (!ListenerUtil.mutListener.listen(38653)) {
            if ((ListenerUtil.mutListener.listen(38640) ? (this.ids != null || ((ListenerUtil.mutListener.listen(38639) ? (id != null || (ListenerUtil.mutListener.listen(38638) ? (id.length() >= 0) : (ListenerUtil.mutListener.listen(38637) ? (id.length() <= 0) : (ListenerUtil.mutListener.listen(38636) ? (id.length() < 0) : (ListenerUtil.mutListener.listen(38635) ? (id.length() != 0) : (ListenerUtil.mutListener.listen(38634) ? (id.length() == 0) : (id.length() > 0))))))) : (id != null && (ListenerUtil.mutListener.listen(38638) ? (id.length() >= 0) : (ListenerUtil.mutListener.listen(38637) ? (id.length() <= 0) : (ListenerUtil.mutListener.listen(38636) ? (id.length() < 0) : (ListenerUtil.mutListener.listen(38635) ? (id.length() != 0) : (ListenerUtil.mutListener.listen(38634) ? (id.length() == 0) : (id.length() > 0)))))))))) : (this.ids != null && ((ListenerUtil.mutListener.listen(38639) ? (id != null || (ListenerUtil.mutListener.listen(38638) ? (id.length() >= 0) : (ListenerUtil.mutListener.listen(38637) ? (id.length() <= 0) : (ListenerUtil.mutListener.listen(38636) ? (id.length() < 0) : (ListenerUtil.mutListener.listen(38635) ? (id.length() != 0) : (ListenerUtil.mutListener.listen(38634) ? (id.length() == 0) : (id.length() > 0))))))) : (id != null && (ListenerUtil.mutListener.listen(38638) ? (id.length() >= 0) : (ListenerUtil.mutListener.listen(38637) ? (id.length() <= 0) : (ListenerUtil.mutListener.listen(38636) ? (id.length() < 0) : (ListenerUtil.mutListener.listen(38635) ? (id.length() != 0) : (ListenerUtil.mutListener.listen(38634) ? (id.length() == 0) : (id.length() > 0)))))))))))) {
                synchronized (this.lock) {
                    List<String> idList = Arrays.asList(this.ids);
                    if (!ListenerUtil.mutListener.listen(38652)) {
                        if (!idList.contains(id)) {
                            if (!ListenerUtil.mutListener.listen(38645)) {
                                this.ids = Arrays.copyOf(this.ids, (ListenerUtil.mutListener.listen(38644) ? (this.ids.length % 1) : (ListenerUtil.mutListener.listen(38643) ? (this.ids.length / 1) : (ListenerUtil.mutListener.listen(38642) ? (this.ids.length * 1) : (ListenerUtil.mutListener.listen(38641) ? (this.ids.length - 1) : (this.ids.length + 1))))));
                            }
                            if (!ListenerUtil.mutListener.listen(38650)) {
                                this.ids[(ListenerUtil.mutListener.listen(38649) ? (ids.length % 1) : (ListenerUtil.mutListener.listen(38648) ? (ids.length / 1) : (ListenerUtil.mutListener.listen(38647) ? (ids.length * 1) : (ListenerUtil.mutListener.listen(38646) ? (ids.length + 1) : (ids.length - 1)))))] = id;
                            }
                            if (!ListenerUtil.mutListener.listen(38651)) {
                                this.preferenceService.setList(this.uniqueListName, ids);
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public void toggle(Context context, final ContactModel contactModel) {
        String id = contactModel.getIdentity();
        if (!ListenerUtil.mutListener.listen(38662)) {
            if (this.has(id)) {
                if (!ListenerUtil.mutListener.listen(38658)) {
                    this.remove(id);
                }
                if (!ListenerUtil.mutListener.listen(38659)) {
                    Toast.makeText(context, context.getString(R.string.contact_now_unblocked), Toast.LENGTH_SHORT).show();
                }
                if (!ListenerUtil.mutListener.listen(38661)) {
                    ListenerManager.contactListeners.handle(new ListenerManager.HandleListener<ContactListener>() {

                        @Override
                        public void handle(ContactListener listener) {
                            if (!ListenerUtil.mutListener.listen(38660)) {
                                listener.onModified(contactModel);
                            }
                        }
                    });
                }
            } else {
                if (!ListenerUtil.mutListener.listen(38654)) {
                    IdListServiceImpl.this.add(contactModel.getIdentity());
                }
                if (!ListenerUtil.mutListener.listen(38655)) {
                    Toast.makeText(context, context.getString(R.string.contact_now_blocked), Toast.LENGTH_SHORT).show();
                }
                if (!ListenerUtil.mutListener.listen(38657)) {
                    ListenerManager.contactListeners.handle(new ListenerManager.HandleListener<ContactListener>() {

                        @Override
                        public void handle(ContactListener listener) {
                            if (!ListenerUtil.mutListener.listen(38656)) {
                                listener.onModified(contactModel);
                            }
                        }
                    });
                }
            }
        }
    }

    @Override
    public synchronized String[] getAll() {
        return this.ids;
    }

    @Override
    public void addAll(String[] ids) {
        if (!ListenerUtil.mutListener.listen(38663)) {
            this.ids = ids;
        }
        if (!ListenerUtil.mutListener.listen(38664)) {
            this.preferenceService.setList(this.uniqueListName, this.ids);
        }
    }

    @Override
    public void removeAll() {
        if (!ListenerUtil.mutListener.listen(38665)) {
            this.ids = new String[0];
        }
        if (!ListenerUtil.mutListener.listen(38666)) {
            this.preferenceService.setList(this.uniqueListName, this.ids);
        }
    }
}
