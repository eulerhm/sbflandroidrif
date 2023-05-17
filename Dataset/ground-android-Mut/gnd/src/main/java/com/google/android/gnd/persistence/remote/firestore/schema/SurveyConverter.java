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

import static com.google.android.gnd.model.basemap.BaseMap.typeFromExtension;
import static com.google.android.gnd.util.Localization.getLocalizedMessage;
import com.google.android.gnd.model.Survey;
import com.google.android.gnd.model.basemap.BaseMap;
import com.google.android.gnd.persistence.remote.DataStoreException;
import com.google.common.collect.ImmutableMap;
import com.google.firebase.firestore.DocumentSnapshot;
import java.net.MalformedURLException;
import java.net.URL;
import java8.util.Maps;
import timber.log.Timber;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Converts between Firestore documents and {@link Survey} instances.
 */
class SurveyConverter {

    static Survey toSurvey(DocumentSnapshot doc) throws DataStoreException {
        SurveyDocument pd = doc.toObject(SurveyDocument.class);
        Survey.Builder survey = Survey.newBuilder();
        if (!ListenerUtil.mutListener.listen(1463)) {
            survey.setId(doc.getId()).setTitle(getLocalizedMessage(pd.getTitle())).setDescription(getLocalizedMessage(pd.getDescription()));
        }
        if (!ListenerUtil.mutListener.listen(1465)) {
            if (pd.getJobs() != null) {
                if (!ListenerUtil.mutListener.listen(1464)) {
                    Maps.forEach(pd.getJobs(), (id, obj) -> survey.putJob(JobConverter.toJob(id, obj)));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(1466)) {
            survey.setAcl(ImmutableMap.copyOf(pd.getAcl()));
        }
        if (!ListenerUtil.mutListener.listen(1468)) {
            if (pd.getBaseMaps() != null) {
                if (!ListenerUtil.mutListener.listen(1467)) {
                    convertOfflineBaseMapSources(pd, survey);
                }
            }
        }
        return survey.build();
    }

    private static void convertOfflineBaseMapSources(SurveyDocument pd, Survey.Builder builder) {
        if (!ListenerUtil.mutListener.listen(1473)) {
            {
                long _loopCounter45 = 0;
                for (BaseMapNestedObject src : pd.getBaseMaps()) {
                    ListenerUtil.loopListener.listen("_loopCounter45", ++_loopCounter45);
                    if (!ListenerUtil.mutListener.listen(1470)) {
                        if (src.getUrl() == null) {
                            if (!ListenerUtil.mutListener.listen(1469)) {
                                Timber.d("Skipping base map source in survey with missing URL");
                            }
                            continue;
                        }
                    }
                    try {
                        URL url = new URL(src.getUrl());
                        if (!ListenerUtil.mutListener.listen(1472)) {
                            builder.addBaseMap(BaseMap.builder().setUrl(url).setType(typeFromExtension(src.getUrl())).build());
                        }
                    } catch (MalformedURLException e) {
                        if (!ListenerUtil.mutListener.listen(1471)) {
                            Timber.d("Skipping base map source in survey with malformed URL");
                        }
                    }
                }
            }
        }
    }
}
