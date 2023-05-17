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

import androidx.annotation.Nullable;
import java.util.Map;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Firestore representation of multiple choice question options.
 */
class OptionNestedObject {

    @Nullable
    private Integer index;

    @Nullable
    private String code;

    @Nullable
    private Map<String, String> label;

    @SuppressWarnings("unused")
    public OptionNestedObject() {
    }

    @SuppressWarnings("unused")
    OptionNestedObject(@Nullable Integer index, @Nullable String code, @Nullable Map<String, String> label) {
        if (!ListenerUtil.mutListener.listen(1438)) {
            this.index = index;
        }
        if (!ListenerUtil.mutListener.listen(1439)) {
            this.code = code;
        }
        if (!ListenerUtil.mutListener.listen(1440)) {
            this.label = label;
        }
    }

    public int getIndex() {
        // Degrade gracefully if Options missing index in remote db.
        return index == null ? -1 : index;
    }

    @Nullable
    public String getCode() {
        return code;
    }

    @Nullable
    public Map<String, String> getLabel() {
        return label;
    }
}
