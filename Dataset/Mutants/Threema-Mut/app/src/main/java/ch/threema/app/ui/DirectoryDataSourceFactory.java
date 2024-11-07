/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
 * Copyright (c) 2018-2021 Threema GmbH
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
package ch.threema.app.ui;

import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;
import ch.threema.client.work.WorkDirectory;
import ch.threema.client.work.WorkDirectoryContact;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class DirectoryDataSourceFactory extends DataSource.Factory<WorkDirectory, WorkDirectoryContact> {

    private boolean init = false;

    // Used to hold a reference to the data source
    public MutableLiveData<DirectoryDataSource> postLiveData;

    public DirectoryDataSourceFactory() {
        if (!ListenerUtil.mutListener.listen(45036)) {
            this.init = true;
        }
    }

    @Override
    public DataSource<WorkDirectory, WorkDirectoryContact> create() {
        DirectoryDataSource dataSource = new DirectoryDataSource();
        if (!ListenerUtil.mutListener.listen(45039)) {
            if (this.init) {
                if (!ListenerUtil.mutListener.listen(45037)) {
                    dataSource.setQueryText(null);
                }
                if (!ListenerUtil.mutListener.listen(45038)) {
                    this.init = false;
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(45040)) {
            // Keep reference to the data source with a MutableLiveData reference
            postLiveData = new MutableLiveData<>();
        }
        if (!ListenerUtil.mutListener.listen(45041)) {
            postLiveData.postValue(dataSource);
        }
        return dataSource;
    }
}
