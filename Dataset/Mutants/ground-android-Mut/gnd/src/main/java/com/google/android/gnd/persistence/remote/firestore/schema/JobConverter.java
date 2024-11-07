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

import static com.google.android.gnd.util.Localization.getLocalizedMessage;
import com.google.android.gnd.model.job.Job;
import timber.log.Timber;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Converts between Firestore documents and {@link Job} instances.
 */
class JobConverter {

    static Job toJob(String id, JobNestedObject obj) {
        Job.Builder builder = Job.newBuilder();
        if (!ListenerUtil.mutListener.listen(1441)) {
            builder.setId(id).setName(getLocalizedMessage(obj.getName()));
        }
        if (!ListenerUtil.mutListener.listen(1451)) {
            if ((ListenerUtil.mutListener.listen(1442) ? (obj.getTasks() != null || !obj.getTasks().isEmpty()) : (obj.getTasks() != null && !obj.getTasks().isEmpty()))) {
                if (!ListenerUtil.mutListener.listen(1449)) {
                    if ((ListenerUtil.mutListener.listen(1447) ? (obj.getTasks().size() >= 1) : (ListenerUtil.mutListener.listen(1446) ? (obj.getTasks().size() <= 1) : (ListenerUtil.mutListener.listen(1445) ? (obj.getTasks().size() < 1) : (ListenerUtil.mutListener.listen(1444) ? (obj.getTasks().size() != 1) : (ListenerUtil.mutListener.listen(1443) ? (obj.getTasks().size() == 1) : (obj.getTasks().size() > 1))))))) {
                        if (!ListenerUtil.mutListener.listen(1448)) {
                            Timber.e("Multiple forms not supported");
                        }
                    }
                }
                String taskId = obj.getTasks().keySet().iterator().next();
                if (!ListenerUtil.mutListener.listen(1450)) {
                    builder.setTask(TaskConverter.toTask(taskId, obj.getTasks().get(taskId)));
                }
            }
        }
        return builder.build();
    }
}
