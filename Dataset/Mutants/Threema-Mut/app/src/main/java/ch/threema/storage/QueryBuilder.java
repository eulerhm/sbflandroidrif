/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
 * Copyright (c) 2015-2021 Threema GmbH
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
package ch.threema.storage;

import net.sqlcipher.database.SQLiteQueryBuilder;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class QueryBuilder extends SQLiteQueryBuilder {

    private int whereCount = 0;

    @Override
    public void appendWhere(CharSequence inWhere) {
        if (!ListenerUtil.mutListener.listen(71690)) {
            inWhere = "(" + inWhere + ")";
        }
        if (!ListenerUtil.mutListener.listen(71697)) {
            if ((ListenerUtil.mutListener.listen(71695) ? (this.whereCount >= 0) : (ListenerUtil.mutListener.listen(71694) ? (this.whereCount <= 0) : (ListenerUtil.mutListener.listen(71693) ? (this.whereCount < 0) : (ListenerUtil.mutListener.listen(71692) ? (this.whereCount != 0) : (ListenerUtil.mutListener.listen(71691) ? (this.whereCount == 0) : (this.whereCount > 0))))))) {
                if (!ListenerUtil.mutListener.listen(71696)) {
                    // append a AND
                    inWhere = " AND " + inWhere;
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(71698)) {
            this.whereCount++;
        }
        if (!ListenerUtil.mutListener.listen(71699)) {
            super.appendWhere(inWhere);
        }
    }
}
