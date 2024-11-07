/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
 * Copyright (c) 2020-2021 Threema GmbH
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
package ch.threema.app.globalsearch;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import ch.threema.app.ThreemaApplication;
import ch.threema.app.managers.ServiceManager;
import ch.threema.app.services.MessageService;
import ch.threema.app.utils.TestUtil;
import ch.threema.base.ThreemaException;
import ch.threema.storage.models.AbstractMessageModel;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class GlobalSearchRepository {

    private final MutableLiveData<Boolean> isLoading = new MutableLiveData<>();

    private MutableLiveData<List<AbstractMessageModel>> messageModels;

    private MessageService messageService;

    private String queryString = "";

    GlobalSearchRepository() {
        ServiceManager serviceManager = ThreemaApplication.getServiceManager();
        if (!ListenerUtil.mutListener.listen(28370)) {
            if (serviceManager != null) {
                if (!ListenerUtil.mutListener.listen(28366)) {
                    messageService = null;
                }
                try {
                    if (!ListenerUtil.mutListener.listen(28367)) {
                        messageService = serviceManager.getMessageService();
                    }
                } catch (ThreemaException e) {
                    return;
                }
                if (!ListenerUtil.mutListener.listen(28369)) {
                    if (messageService != null) {
                        if (!ListenerUtil.mutListener.listen(28368)) {
                            messageModels = new MutableLiveData<List<AbstractMessageModel>>() {

                                @Nullable
                                @Override
                                public List<AbstractMessageModel> getValue() {
                                    return getMessagesForText(queryString, GlobalSearchActivity.FILTER_CHATS | GlobalSearchActivity.FILTER_GROUPS | GlobalSearchActivity.FILTER_INCLUDE_ARCHIVED);
                                }
                            };
                        }
                    }
                }
            }
        }
    }

    LiveData<List<AbstractMessageModel>> getMessageModels() {
        return messageModels;
    }

    List<AbstractMessageModel> getMessagesForText(String queryString, int filterFlags) {
        List<AbstractMessageModel> messageModels = new ArrayList<>();
        boolean includeArchived = (filterFlags & GlobalSearchActivity.FILTER_INCLUDE_ARCHIVED) == GlobalSearchActivity.FILTER_INCLUDE_ARCHIVED;
        if (!ListenerUtil.mutListener.listen(28372)) {
            if ((filterFlags & GlobalSearchActivity.FILTER_CHATS) == GlobalSearchActivity.FILTER_CHATS) {
                if (!ListenerUtil.mutListener.listen(28371)) {
                    messageModels.addAll(messageService.getContactMessagesForText(queryString, includeArchived));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(28374)) {
            if ((filterFlags & GlobalSearchActivity.FILTER_GROUPS) == GlobalSearchActivity.FILTER_GROUPS) {
                if (!ListenerUtil.mutListener.listen(28373)) {
                    messageModels.addAll(messageService.getGroupMessagesForText(queryString, includeArchived));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(28381)) {
            if ((ListenerUtil.mutListener.listen(28379) ? (messageModels.size() >= 0) : (ListenerUtil.mutListener.listen(28378) ? (messageModels.size() <= 0) : (ListenerUtil.mutListener.listen(28377) ? (messageModels.size() < 0) : (ListenerUtil.mutListener.listen(28376) ? (messageModels.size() != 0) : (ListenerUtil.mutListener.listen(28375) ? (messageModels.size() == 0) : (messageModels.size() > 0))))))) {
                if (!ListenerUtil.mutListener.listen(28380)) {
                    Collections.sort(messageModels, (o1, o2) -> o2.getCreatedAt().compareTo(o1.getCreatedAt()));
                }
            }
        }
        return messageModels;
    }

    @SuppressLint("StaticFieldLeak")
    void onQueryChanged(String query, int filterFlags) {
        if (!ListenerUtil.mutListener.listen(28382)) {
            queryString = query;
        }
        if (!ListenerUtil.mutListener.listen(28390)) {
            new AsyncTask<String, Void, Void>() {

                @Override
                protected Void doInBackground(String... strings) {
                    if (!ListenerUtil.mutListener.listen(28389)) {
                        if (messageService != null) {
                            if (!ListenerUtil.mutListener.listen(28388)) {
                                if (TestUtil.empty(query)) {
                                    if (!ListenerUtil.mutListener.listen(28386)) {
                                        messageModels.postValue(new ArrayList<>());
                                    }
                                    if (!ListenerUtil.mutListener.listen(28387)) {
                                        isLoading.postValue(false);
                                    }
                                } else {
                                    if (!ListenerUtil.mutListener.listen(28383)) {
                                        isLoading.postValue(true);
                                    }
                                    if (!ListenerUtil.mutListener.listen(28384)) {
                                        messageModels.postValue(getMessagesForText(query, filterFlags));
                                    }
                                    if (!ListenerUtil.mutListener.listen(28385)) {
                                        isLoading.postValue(false);
                                    }
                                }
                            }
                        }
                    }
                    return null;
                }
            }.execute();
        }
    }

    LiveData<Boolean> getIsLoading() {
        return isLoading;
    }
}
