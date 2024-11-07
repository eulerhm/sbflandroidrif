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

import static com.google.android.gnd.util.Enums.toEnum;
import static com.google.android.gnd.util.Localization.getLocalizedMessage;
import com.google.android.gnd.model.task.Field;
import java8.util.Objects;
import java8.util.Optional;
import timber.log.Timber;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Converts between Firestore nested objects and {@link Field} instances.
 */
class FieldConverter {

    static Optional<Field> toField(String id, ElementNestedObject em) {
        Field.Type type = toEnum(Field.Type.class, em.getType());
        if (!ListenerUtil.mutListener.listen(1484)) {
            if (type == Field.Type.UNKNOWN) {
                if (!ListenerUtil.mutListener.listen(1483)) {
                    Timber.d("Unsupported task step type: " + em.getType());
                }
                return Optional.empty();
            }
        }
        Field.Builder field = Field.newBuilder();
        if (!ListenerUtil.mutListener.listen(1485)) {
            field.setType(type);
        }
        if (!ListenerUtil.mutListener.listen(1487)) {
            if (type == Field.Type.MULTIPLE_CHOICE) {
                if (!ListenerUtil.mutListener.listen(1486)) {
                    field.setMultipleChoice(MultipleChoiceConverter.toMultipleChoice(em));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(1489)) {
            field.setRequired((ListenerUtil.mutListener.listen(1488) ? (em.getRequired() != null || em.getRequired()) : (em.getRequired() != null && em.getRequired())));
        }
        if (!ListenerUtil.mutListener.listen(1490)) {
            field.setId(id);
        }
        if (!ListenerUtil.mutListener.listen(1491)) {
            // Default index to -1 to degrade gracefully on older dev db instances and surveys.
            field.setIndex(Objects.requireNonNullElse(em.getIndex(), -1));
        }
        if (!ListenerUtil.mutListener.listen(1492)) {
            field.setLabel(getLocalizedMessage(em.getLabel()));
        }
        return Optional.of(field.build());
    }
}
