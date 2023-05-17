/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
 * Copyright (c) 2019-2021 Threema GmbH
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
package ch.threema.app.archive;

import java.util.List;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import ch.threema.storage.models.ConversationModel;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class ArchiveViewModel extends ViewModel {

    private LiveData<List<ConversationModel>> conversationModels;

    private ArchiveRepository repository;

    public ArchiveViewModel() {
        super();
        if (!ListenerUtil.mutListener.listen(10018)) {
            repository = new ArchiveRepository();
        }
        if (!ListenerUtil.mutListener.listen(10019)) {
            conversationModels = repository.getConversationModels();
        }
    }

    LiveData<List<ConversationModel>> getConversationModels() {
        return conversationModels;
    }

    public void onDataChanged() {
        if (!ListenerUtil.mutListener.listen(10020)) {
            repository.onDataChanged();
        }
    }

    public void filter(String constraint) {
        if (!ListenerUtil.mutListener.listen(10021)) {
            repository.setFilter(constraint);
        }
        if (!ListenerUtil.mutListener.listen(10022)) {
            repository.onDataChanged();
        }
    }
}
