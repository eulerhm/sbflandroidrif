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
package ch.threema.app.activities;

import ch.threema.app.R;
import ch.threema.app.ThreemaApplication;
import ch.threema.app.services.IdListService;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class BlackListActivity extends IdentityListActivity {

    private IdListService listService;

    @Override
    protected IdListService getIdentityListService() {
        if (!ListenerUtil.mutListener.listen(1932)) {
            if (this.listService == null) {
                if (!ListenerUtil.mutListener.listen(1931)) {
                    this.listService = ThreemaApplication.getServiceManager().getBlackListService();
                }
            }
        }
        return this.listService;
    }

    @Override
    protected String getBlankListText() {
        return this.getString(R.string.prefs_sum_black_list);
    }

    @Override
    protected String getTitleText() {
        return this.getString(R.string.prefs_title_black_list);
    }
}
