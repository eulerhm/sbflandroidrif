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
package com.google.android.gnd.persistence.local.room.entity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import com.google.android.gnd.model.Survey;
import com.google.android.gnd.persistence.local.room.relations.JobEntityAndRelations;
import com.google.android.gnd.persistence.local.room.relations.SurveyEntityAndRelations;
import com.google.auto.value.AutoValue;
import com.google.auto.value.AutoValue.CopyAnnotations;
import com.google.common.collect.ImmutableMap;
import java.net.MalformedURLException;
import java.util.Iterator;
import org.json.JSONObject;
import timber.log.Timber;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

@AutoValue
@Entity(tableName = "survey")
public abstract class SurveyEntity {

    @CopyAnnotations
    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "id")
    public abstract String getId();

    @CopyAnnotations
    @Nullable
    @ColumnInfo(name = "title")
    public abstract String getTitle();

    @CopyAnnotations
    @Nullable
    @ColumnInfo(name = "description")
    public abstract String getDescription();

    @CopyAnnotations
    @Nullable
    @ColumnInfo(name = "acl")
    public abstract JSONObject getAcl();

    public static SurveyEntity fromSurvey(Survey survey) {
        return SurveyEntity.builder().setId(survey.getId()).setTitle(survey.getTitle()).setDescription(survey.getDescription()).setAcl(new JSONObject(survey.getAcl())).build();
    }

    public static Survey toSurvey(SurveyEntityAndRelations surveyEntityAndRelations) {
        SurveyEntity surveyEntity = surveyEntityAndRelations.surveyEntity;
        Survey.Builder surveyBuilder = Survey.newBuilder().setId(surveyEntity.getId()).setTitle(surveyEntity.getTitle()).setDescription(surveyEntity.getDescription()).setAcl(toStringMap(surveyEntity.getAcl()));
        if (!ListenerUtil.mutListener.listen(1269)) {
            {
                long _loopCounter31 = 0;
                for (JobEntityAndRelations jobEntityAndRelations : surveyEntityAndRelations.jobEntityAndRelations) {
                    ListenerUtil.loopListener.listen("_loopCounter31", ++_loopCounter31);
                    if (!ListenerUtil.mutListener.listen(1268)) {
                        surveyBuilder.putJob(JobEntity.toJob(jobEntityAndRelations));
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(1272)) {
            {
                long _loopCounter32 = 0;
                for (BaseMapEntity source : surveyEntityAndRelations.baseMapEntityAndRelations) {
                    ListenerUtil.loopListener.listen("_loopCounter32", ++_loopCounter32);
                    try {
                        if (!ListenerUtil.mutListener.listen(1271)) {
                            surveyBuilder.addBaseMap(BaseMapEntity.toModel(source));
                        }
                    } catch (MalformedURLException e) {
                        if (!ListenerUtil.mutListener.listen(1270)) {
                            Timber.d("Skipping basemap source with malformed URL %s", source.getUrl());
                        }
                    }
                }
            }
        }
        return surveyBuilder.build();
    }

    private static ImmutableMap<String, String> toStringMap(JSONObject jsonObject) {
        ImmutableMap.Builder builder = ImmutableMap.builder();
        Iterator<String> keys = jsonObject.keys();
        if (!ListenerUtil.mutListener.listen(1275)) {
            {
                long _loopCounter33 = 0;
                while (keys.hasNext()) {
                    ListenerUtil.loopListener.listen("_loopCounter33", ++_loopCounter33);
                    String key = keys.next();
                    String value = jsonObject.optString(key, null);
                    if (!ListenerUtil.mutListener.listen(1274)) {
                        if (value != null) {
                            if (!ListenerUtil.mutListener.listen(1273)) {
                                builder.put(key, value);
                            }
                        }
                    }
                }
            }
        }
        return builder.build();
    }

    public static SurveyEntity create(String id, String title, String description, JSONObject acl) {
        return builder().setId(id).setTitle(title).setDescription(description).setAcl(acl).build();
    }

    public static Builder builder() {
        return new AutoValue_SurveyEntity.Builder();
    }

    @AutoValue.Builder
    public abstract static class Builder {

        public abstract Builder setId(String id);

        public abstract Builder setTitle(String title);

        public abstract Builder setDescription(String description);

        public abstract Builder setAcl(JSONObject acl);

        public abstract SurveyEntity build();
    }
}
