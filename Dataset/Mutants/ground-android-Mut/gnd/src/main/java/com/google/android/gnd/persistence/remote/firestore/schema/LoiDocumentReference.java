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

import com.google.android.gnd.model.User;
import com.google.android.gnd.model.mutation.FeatureMutation;
import com.google.android.gnd.persistence.remote.firestore.base.FluentDocumentReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.WriteBatch;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class LoiDocumentReference extends FluentDocumentReference {

    LoiDocumentReference(DocumentReference ref) {
        super(ref);
    }

    /**
     * Appends the operation described by the specified mutation to the provided write batch.
     */
    public void addMutationToBatch(FeatureMutation mutation, User user, WriteBatch batch) {
        if (!ListenerUtil.mutListener.listen(1517)) {
            switch(mutation.getType()) {
                case CREATE:
                case UPDATE:
                    if (!ListenerUtil.mutListener.listen(1515)) {
                        merge(LoiMutationConverter.toMap(mutation, user), batch);
                    }
                    break;
                case DELETE:
                    if (!ListenerUtil.mutListener.listen(1516)) {
                        // The server is expected to do a cascading delete of all submissions for the deleted LOI.
                        delete(batch);
                    }
                    break;
                default:
                    throw new IllegalArgumentException("Unknown mutation type " + mutation.getType());
            }
        }
    }
}
