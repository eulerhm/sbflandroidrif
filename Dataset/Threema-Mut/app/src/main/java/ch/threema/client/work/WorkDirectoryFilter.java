/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema Java Client
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
package ch.threema.client.work;

import java.util.ArrayList;
import java.util.List;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class WorkDirectoryFilter {

    public static final int SORT_BY_FIRST_NAME = 1;

    public static final int SORT_BY_LAST_NAME = 2;

    private String query;

    private int page = 0;

    private int sortBy = SORT_BY_FIRST_NAME;

    private boolean sortAscending = true;

    private final List<WorkDirectoryCategory> categories = new ArrayList<>();

    public WorkDirectoryFilter query(String query) {
        if (!ListenerUtil.mutListener.listen(66279)) {
            this.query = query;
        }
        return this;
    }

    public String getQuery() {
        return this.query;
    }

    public WorkDirectoryFilter page(int page) {
        if (!ListenerUtil.mutListener.listen(66280)) {
            this.page = page;
        }
        return this;
    }

    public int getPage() {
        return this.page;
    }

    public WorkDirectoryFilter sortBy(int sortBy, boolean sortAscending) {
        if (!ListenerUtil.mutListener.listen(66283)) {
            switch(this.sortBy) {
                case SORT_BY_FIRST_NAME:
                case SORT_BY_LAST_NAME:
                    if (!ListenerUtil.mutListener.listen(66281)) {
                        this.sortBy = sortBy;
                    }
                    if (!ListenerUtil.mutListener.listen(66282)) {
                        this.sortAscending = sortAscending;
                    }
                    break;
            }
        }
        return this;
    }

    public int getSortBy() {
        return this.sortBy;
    }

    public boolean isSortAscending() {
        return this.sortAscending;
    }

    public WorkDirectoryFilter addCategory(WorkDirectoryCategory category) {
        if (!ListenerUtil.mutListener.listen(66285)) {
            if (!this.categories.contains(category)) {
                if (!ListenerUtil.mutListener.listen(66284)) {
                    this.categories.add(category);
                }
            }
        }
        return this;
    }

    public List<WorkDirectoryCategory> getCategories() {
        return this.categories;
    }

    public WorkDirectoryFilter copy() {
        WorkDirectoryFilter newFilter = new WorkDirectoryFilter();
        if (!ListenerUtil.mutListener.listen(66286)) {
            newFilter.sortBy = this.sortBy;
        }
        if (!ListenerUtil.mutListener.listen(66287)) {
            newFilter.sortAscending = this.sortAscending;
        }
        if (!ListenerUtil.mutListener.listen(66288)) {
            newFilter.page = this.page;
        }
        if (!ListenerUtil.mutListener.listen(66289)) {
            newFilter.query = this.query;
        }
        if (!ListenerUtil.mutListener.listen(66291)) {
            {
                long _loopCounter822 = 0;
                // Copy categories
                for (WorkDirectoryCategory c : this.categories) {
                    ListenerUtil.loopListener.listen("_loopCounter822", ++_loopCounter822);
                    if (!ListenerUtil.mutListener.listen(66290)) {
                        newFilter.categories.add(c);
                    }
                }
            }
        }
        return newFilter;
    }
}
