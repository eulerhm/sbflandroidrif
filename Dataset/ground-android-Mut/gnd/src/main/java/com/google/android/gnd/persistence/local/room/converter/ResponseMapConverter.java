/*
 * Copyright 2019 Google LLC
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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gnd.model.submission.ResponseMap;
import com.google.android.gnd.model.task.Field;
import com.google.android.gnd.model.task.Task;
import com.google.android.gnd.persistence.local.LocalDataConsistencyException;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;
import timber.log.Timber;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Converts between {@link ResponseMap} and JSON strings used to represent them in the local db.
 */
public class ResponseMapConverter {

    @Nullable
    public static String toString(@NonNull ResponseMap responseDeltas) {
        JSONObject json = new JSONObject();
        if (!ListenerUtil.mutListener.listen(1295)) {
            {
                long _loopCounter36 = 0;
                for (String fieldId : responseDeltas.fieldIds()) {
                    ListenerUtil.loopListener.listen("_loopCounter36", ++_loopCounter36);
                    try {
                        if (!ListenerUtil.mutListener.listen(1294)) {
                            json.put(fieldId, responseDeltas.getResponse(fieldId).map(ResponseJsonConverter::toJsonObject).orElse(null));
                        }
                    } catch (JSONException e) {
                        if (!ListenerUtil.mutListener.listen(1293)) {
                            Timber.e(e, "Error building JSON");
                        }
                    }
                }
            }
        }
        return json.toString();
    }

    @NonNull
    public static ResponseMap fromString(Task task, @Nullable String jsonString) {
        ResponseMap.Builder map = ResponseMap.builder();
        if (!ListenerUtil.mutListener.listen(1296)) {
            if (jsonString == null) {
                return map.build();
            }
        }
        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            Iterator<String> keys = jsonObject.keys();
            if (!ListenerUtil.mutListener.listen(1300)) {
                {
                    long _loopCounter37 = 0;
                    while (keys.hasNext()) {
                        ListenerUtil.loopListener.listen("_loopCounter37", ++_loopCounter37);
                        try {
                            String fieldId = keys.next();
                            Field field = task.getField(fieldId).orElseThrow(() -> new LocalDataConsistencyException("Unknown field id " + fieldId));
                            if (!ListenerUtil.mutListener.listen(1299)) {
                                ResponseJsonConverter.toResponse(field, jsonObject.get(fieldId)).ifPresent(response -> map.putResponse(fieldId, response));
                            }
                        } catch (LocalDataConsistencyException e) {
                            if (!ListenerUtil.mutListener.listen(1298)) {
                                Timber.d("Bad response in local db: " + e.getMessage());
                            }
                        }
                    }
                }
            }
        } catch (JSONException e) {
            if (!ListenerUtil.mutListener.listen(1297)) {
                Timber.e(e, "Error parsing JSON string");
            }
        }
        return map.build();
    }
}
