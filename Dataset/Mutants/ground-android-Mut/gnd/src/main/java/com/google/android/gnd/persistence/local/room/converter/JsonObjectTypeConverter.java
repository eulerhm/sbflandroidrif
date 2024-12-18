/*
 * Copyright 2021 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.google.android.gnd.persistence.local.room.converter;

import androidx.annotation.Nullable;
import androidx.room.TypeConverter;
import org.json.JSONException;
import org.json.JSONObject;
import timber.log.Timber;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class JsonObjectTypeConverter {

    @TypeConverter
    @Nullable
    public static String toString(@Nullable JSONObject jsonObject) {
        return jsonObject == null ? null : jsonObject.toString();
    }

    @TypeConverter
    @Nullable
    public static JSONObject fromString(@Nullable String jsonString) {
        try {
            return jsonString == null ? null : new JSONObject(jsonString);
        } catch (JSONException e) {
            if (!ListenerUtil.mutListener.listen(1318)) {
                Timber.d(e, "Invalid JSON in db");
            }
            return new JSONObject();
        }
    }
}
