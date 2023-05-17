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

import static java8.lang.Iterables.forEach;
import androidx.annotation.Nullable;
import com.google.android.gnd.model.submission.DateResponse;
import com.google.android.gnd.model.submission.MultipleChoiceResponse;
import com.google.android.gnd.model.submission.NumberResponse;
import com.google.android.gnd.model.submission.Response;
import com.google.android.gnd.model.submission.TextResponse;
import com.google.android.gnd.model.submission.TimeResponse;
import com.google.android.gnd.model.task.Field;
import com.google.android.gnd.persistence.remote.DataStoreException;
import com.google.common.collect.ImmutableList;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java8.util.Optional;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import timber.log.Timber;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

class ResponseJsonConverter {

    private static final DateFormat ISO_INSTANT_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mmZ", Locale.getDefault());

    static Object toJsonObject(Response response) {
        if (response instanceof TextResponse) {
            return ((TextResponse) response).getText();
        } else if (response instanceof MultipleChoiceResponse) {
            return toJsonArray((MultipleChoiceResponse) response);
        } else if (response instanceof NumberResponse) {
            double value = ((NumberResponse) response).getValue();
            if (Double.isNaN(value)) {
                return JSONObject.NULL;
            }
            return value;
        } else if (response instanceof DateResponse) {
            return dateToIsoString(((DateResponse) response).getDate());
        } else if (response instanceof TimeResponse) {
            return dateToIsoString(((TimeResponse) response).getTime());
        } else {
            throw new UnsupportedOperationException("Unimplemented Response " + response.getClass());
        }
    }

    protected static String dateToIsoString(Date date) {
        synchronized (ISO_INSTANT_FORMAT) {
            if (!ListenerUtil.mutListener.listen(1301)) {
                ISO_INSTANT_FORMAT.setTimeZone(TimeZone.getTimeZone("UTC"));
            }
            return ISO_INSTANT_FORMAT.format(date);
        }
    }

    @Nullable
    protected static Date isoStringToDate(String isoString) {
        try {
            synchronized (ISO_INSTANT_FORMAT) {
                if (!ListenerUtil.mutListener.listen(1303)) {
                    ISO_INSTANT_FORMAT.setTimeZone(TimeZone.getTimeZone("UTC"));
                }
                return ISO_INSTANT_FORMAT.parse(isoString);
            }
        } catch (ParseException e) {
            if (!ListenerUtil.mutListener.listen(1302)) {
                Timber.e("Error parsing Date : %s", e.getMessage());
            }
        }
        return null;
    }

    private static Object toJsonArray(MultipleChoiceResponse response) {
        JSONArray array = new JSONArray();
        if (!ListenerUtil.mutListener.listen(1304)) {
            forEach(response.getSelectedOptionIds(), array::put);
        }
        return array;
    }

    static Optional<Response> toResponse(Field field, Object obj) {
        switch(field.getType()) {
            case TEXT_FIELD:
            case PHOTO:
                if (obj == JSONObject.NULL) {
                    return TextResponse.fromString("");
                }
                if (!ListenerUtil.mutListener.listen(1305)) {
                    DataStoreException.checkType(String.class, obj);
                }
                return TextResponse.fromString((String) obj);
            case MULTIPLE_CHOICE:
                if (obj == JSONObject.NULL) {
                    return MultipleChoiceResponse.fromList(field.getMultipleChoice(), Collections.emptyList());
                }
                if (!ListenerUtil.mutListener.listen(1306)) {
                    DataStoreException.checkType(JSONArray.class, obj);
                }
                return MultipleChoiceResponse.fromList(field.getMultipleChoice(), toList((JSONArray) obj));
            case NUMBER:
                if (JSONObject.NULL == obj) {
                    return NumberResponse.fromNumber("");
                }
                if (!ListenerUtil.mutListener.listen(1307)) {
                    DataStoreException.checkType(Number.class, obj);
                }
                return NumberResponse.fromNumber(obj.toString());
            case DATE:
                if (!ListenerUtil.mutListener.listen(1308)) {
                    DataStoreException.checkType(String.class, obj);
                }
                return DateResponse.fromDate(ResponseJsonConverter.isoStringToDate((String) obj));
            case TIME:
                if (!ListenerUtil.mutListener.listen(1309)) {
                    DataStoreException.checkType(String.class, obj);
                }
                return TimeResponse.fromDate(ResponseJsonConverter.isoStringToDate((String) obj));
            case UNKNOWN:
            default:
                throw new DataStoreException("Unknown type in field: " + obj.getClass().getName());
        }
    }

    private static ImmutableList<String> toList(JSONArray jsonArray) {
        List<String> list = new ArrayList<>(jsonArray.length());
        if (!ListenerUtil.mutListener.listen(1317)) {
            {
                long _loopCounter38 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(1316) ? (i >= jsonArray.length()) : (ListenerUtil.mutListener.listen(1315) ? (i <= jsonArray.length()) : (ListenerUtil.mutListener.listen(1314) ? (i > jsonArray.length()) : (ListenerUtil.mutListener.listen(1313) ? (i != jsonArray.length()) : (ListenerUtil.mutListener.listen(1312) ? (i == jsonArray.length()) : (i < jsonArray.length())))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter38", ++_loopCounter38);
                    try {
                        if (!ListenerUtil.mutListener.listen(1311)) {
                            list.add(jsonArray.getString(i));
                        }
                    } catch (JSONException e) {
                        if (!ListenerUtil.mutListener.listen(1310)) {
                            Timber.e("Error parsing JSONArray in db: %s", jsonArray);
                        }
                    }
                }
            }
        }
        return ImmutableList.<String>builder().addAll(list).build();
    }
}
