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
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * User details nested for nesting inside entities for audit purposes.
 */
class UserNestedObject {

    /**
     * Fallback value when reading invalid or legacy schemas.
     */
    static final UserNestedObject UNKNOWN_USER = new UserNestedObject("", "", "Unknown user");

    @Nullable
    private String id;

    @Nullable
    private String email;

    @Nullable
    private String displayName;

    @SuppressWarnings("unused")
    public UserNestedObject() {
    }

    UserNestedObject(@Nullable String id, @Nullable String email, @Nullable String displayName) {
        if (!ListenerUtil.mutListener.listen(1457)) {
            this.id = id;
        }
        if (!ListenerUtil.mutListener.listen(1458)) {
            this.email = email;
        }
        if (!ListenerUtil.mutListener.listen(1459)) {
            this.displayName = displayName;
        }
    }

    @Nullable
    public String getId() {
        return id;
    }

    @Nullable
    public String getEmail() {
        return email;
    }

    @Nullable
    public String getDisplayName() {
        return displayName;
    }
}
