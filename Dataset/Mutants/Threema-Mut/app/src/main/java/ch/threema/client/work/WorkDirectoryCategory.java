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

import org.json.JSONException;
import org.json.JSONObject;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class WorkDirectoryCategory {

    public final String id;

    public final String name;

    public WorkDirectoryCategory(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public WorkDirectoryCategory(JSONObject jsonObject) {
        this.id = jsonObject.optString("id");
        this.name = jsonObject.optString("name");
    }

    public String getName() {
        return this.name;
    }

    public String getId() {
        return this.id;
    }

    public String toJSON() {
        JSONObject jsonObject = new JSONObject();
        try {
            if (!ListenerUtil.mutListener.listen(66271)) {
                jsonObject.put("id", getId());
            }
            if (!ListenerUtil.mutListener.listen(66272)) {
                jsonObject.put("name", getName());
            }
            return jsonObject.toString();
        } catch (JSONException e) {
            return "";
        }
    }
}
