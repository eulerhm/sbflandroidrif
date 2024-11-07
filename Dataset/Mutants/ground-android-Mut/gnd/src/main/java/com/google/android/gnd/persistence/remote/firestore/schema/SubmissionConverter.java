/*
 * Copyright 2020 Google LLC
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
package com.google.android.gnd.persistence.remote.firestore.schema;

import static com.google.android.gnd.persistence.remote.DataStoreException.checkNotEmpty;
import static com.google.android.gnd.persistence.remote.DataStoreException.checkNotNull;
import static java8.util.stream.StreamSupport.stream;
import com.google.android.gnd.model.feature.Feature;
import com.google.android.gnd.model.submission.DateResponse;
import com.google.android.gnd.model.submission.MultipleChoiceResponse;
import com.google.android.gnd.model.submission.NumberResponse;
import com.google.android.gnd.model.submission.ResponseMap;
import com.google.android.gnd.model.submission.ResponseMap.Builder;
import com.google.android.gnd.model.submission.Submission;
import com.google.android.gnd.model.submission.TextResponse;
import com.google.android.gnd.model.submission.TimeResponse;
import com.google.android.gnd.model.task.Field;
import com.google.android.gnd.model.task.MultipleChoice;
import com.google.android.gnd.model.task.Task;
import com.google.android.gnd.persistence.remote.DataStoreException;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.DocumentSnapshot;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java8.util.Objects;
import javax.annotation.Nullable;
import timber.log.Timber;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Converts between Firestore documents and {@link Submission} instances.
 */
class SubmissionConverter {

    static Submission toSubmission(Feature feature, DocumentSnapshot snapshot) throws DataStoreException {
        SubmissionDocument doc = snapshot.toObject(SubmissionDocument.class);
        String loiId = checkNotNull(doc.getLoiId(), "loiId");
        if (!ListenerUtil.mutListener.listen(1421)) {
            if (!feature.getId().equals(loiId)) {
                throw new DataStoreException("Submission doc featureId doesn't match specified loiId");
            }
        }
        String taskId = checkNotNull(doc.getTaskId(), "taskId");
        Task task = checkNotEmpty(feature.getJob().getTask(taskId), "task " + taskId);
        // Degrade gracefully when audit info missing in remote db.
        AuditInfoNestedObject created = Objects.requireNonNullElse(doc.getCreated(), AuditInfoNestedObject.FALLBACK_VALUE);
        AuditInfoNestedObject lastModified = Objects.requireNonNullElse(doc.getLastModified(), created);
        return Submission.newBuilder().setId(snapshot.getId()).setSurvey(feature.getSurvey()).setFeature(feature).setTask(task).setResponses(toResponseMap(snapshot.getId(), task, doc.getResponses())).setCreated(AuditInfoConverter.toAuditInfo(created)).setLastModified(AuditInfoConverter.toAuditInfo(lastModified)).build();
    }

    private static ResponseMap toResponseMap(String submissionId, Task task, @Nullable Map<String, Object> docResponses) {
        ResponseMap.Builder responses = ResponseMap.builder();
        if (!ListenerUtil.mutListener.listen(1422)) {
            if (docResponses == null) {
                return responses.build();
            }
        }
        if (!ListenerUtil.mutListener.listen(1425)) {
            {
                long _loopCounter44 = 0;
                for (Entry<String, Object> entry : docResponses.entrySet()) {
                    ListenerUtil.loopListener.listen("_loopCounter44", ++_loopCounter44);
                    String fieldId = entry.getKey();
                    try {
                        if (!ListenerUtil.mutListener.listen(1424)) {
                            putResponse(fieldId, task, entry.getValue(), responses);
                        }
                    } catch (DataStoreException e) {
                        if (!ListenerUtil.mutListener.listen(1423)) {
                            Timber.e(e, "Field " + fieldId + "in remote db in submission " + submissionId);
                        }
                    }
                }
            }
        }
        return responses.build();
    }

    private static void putResponse(String fieldId, Task task, Object obj, ResponseMap.Builder responses) {
        Field field = task.getField(fieldId).orElseThrow(() -> new DataStoreException("Not defined in task"));
        if (!ListenerUtil.mutListener.listen(1431)) {
            switch(field.getType()) {
                case PHOTO:
                // TODO(#755): Handle photo fields as PhotoResponse instead of TextResponse.
                case TEXT_FIELD:
                    if (!ListenerUtil.mutListener.listen(1426)) {
                        putTextResponse(fieldId, obj, responses);
                    }
                    break;
                case MULTIPLE_CHOICE:
                    if (!ListenerUtil.mutListener.listen(1427)) {
                        putMultipleChoiceResponse(fieldId, field.getMultipleChoice(), obj, responses);
                    }
                    break;
                case NUMBER:
                    if (!ListenerUtil.mutListener.listen(1428)) {
                        putNumberResponse(fieldId, obj, responses);
                    }
                    break;
                case DATE:
                    if (!ListenerUtil.mutListener.listen(1429)) {
                        putDateResponse(fieldId, obj, responses);
                    }
                    break;
                case TIME:
                    if (!ListenerUtil.mutListener.listen(1430)) {
                        putTimeResponse(fieldId, obj, responses);
                    }
                    break;
                default:
                    throw new DataStoreException("Unknown type " + field.getType());
            }
        }
    }

    private static void putNumberResponse(String fieldId, Object obj, Builder responses) {
        double value = (Double) DataStoreException.checkType(Double.class, obj);
        if (!ListenerUtil.mutListener.listen(1432)) {
            NumberResponse.fromNumber(Double.toString(value)).ifPresent(r -> responses.putResponse(fieldId, r));
        }
    }

    private static void putTextResponse(String fieldId, Object obj, ResponseMap.Builder responses) {
        String value = (String) DataStoreException.checkType(String.class, obj);
        if (!ListenerUtil.mutListener.listen(1433)) {
            TextResponse.fromString(value.trim()).ifPresent(r -> responses.putResponse(fieldId, r));
        }
    }

    private static void putDateResponse(String fieldId, Object obj, ResponseMap.Builder responses) {
        Timestamp value = (Timestamp) DataStoreException.checkType(Timestamp.class, obj);
        if (!ListenerUtil.mutListener.listen(1434)) {
            DateResponse.fromDate(value.toDate()).ifPresent(r -> responses.putResponse(fieldId, r));
        }
    }

    private static void putTimeResponse(String fieldId, Object obj, ResponseMap.Builder responses) {
        Timestamp value = (Timestamp) DataStoreException.checkType(Timestamp.class, obj);
        if (!ListenerUtil.mutListener.listen(1435)) {
            TimeResponse.fromDate(value.toDate()).ifPresent(r -> responses.putResponse(fieldId, r));
        }
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    private static void putMultipleChoiceResponse(String fieldId, MultipleChoice multipleChoice, Object obj, Builder responses) {
        List values = (List) DataStoreException.checkType(List.class, obj);
        if (!ListenerUtil.mutListener.listen(1436)) {
            stream(values).forEach(v -> DataStoreException.checkType(String.class, v));
        }
        if (!ListenerUtil.mutListener.listen(1437)) {
            MultipleChoiceResponse.fromList(multipleChoice, (List<String>) values).ifPresent(r -> responses.putResponse(fieldId, r));
        }
    }
}
