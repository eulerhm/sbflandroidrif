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
package ch.threema.storage.models;

import java.util.Date;
import ch.threema.client.Utils;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class DistributionListModel implements ReceiverModel {

    public static final int DISTRIBUTIONLIST_NAME_MAX_LENGTH_BYTES = 256;

    public static final String TABLE = "distribution_list";

    public static final String COLUMN_ID = "id";

    public static final String COLUMN_NAME = "name";

    public static final String COLUMN_CREATED_AT = "createdAt";

    public static final String COLUMN_IS_ARCHIVED = "isArchived";

    private int id;

    private String name;

    private Date createdAt;

    private boolean isArchived;

    // dummy class
    public String getName() {
        return this.name;
    }

    public DistributionListModel setName(String name) {
        if (!ListenerUtil.mutListener.listen(70979)) {
            this.name = Utils.truncateUTF8String(name, DISTRIBUTIONLIST_NAME_MAX_LENGTH_BYTES);
        }
        return this;
    }

    public int getId() {
        return this.id;
    }

    public DistributionListModel setId(int id) {
        if (!ListenerUtil.mutListener.listen(70980)) {
            this.id = id;
        }
        return this;
    }

    public Date getCreatedAt() {
        return this.createdAt;
    }

    public DistributionListModel setCreatedAt(Date createdAt) {
        if (!ListenerUtil.mutListener.listen(70981)) {
            this.createdAt = createdAt;
        }
        return this;
    }

    public boolean isArchived() {
        return isArchived;
    }

    public DistributionListModel setArchived(boolean archived) {
        if (!ListenerUtil.mutListener.listen(70982)) {
            isArchived = archived;
        }
        return this;
    }
}
