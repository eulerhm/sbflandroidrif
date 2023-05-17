/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema Java Client
 * Copyright (c) 2020-2021 Threema GmbH
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License, version 3,
 * as published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program. If not, see <https://www.gnu.org/licenses/>.
 */
package ch.threema.client.voip.features;

import androidx.annotation.NonNull;
import java8.util.stream.Collectors;
import java8.util.stream.StreamSupport;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Wraps a list of call features, used by both the offer and the answer.
 */
public class FeatureList {

    @NonNull
    private final List<CallFeature> features;

    public FeatureList() {
        this(new ArrayList<>());
    }

    public FeatureList(@NonNull List<CallFeature> features) {
        this.features = features;
    }

    /**
     *  Parse a JSON feature list.
     */
    @NonNull
    public static FeatureList parse(@NonNull JSONObject obj) throws JSONException {
        final List<CallFeature> features = new ArrayList<>();
        if (!ListenerUtil.mutListener.listen(66081)) {
            {
                long _loopCounter817 = 0;
                for (Iterator<String> it = obj.keys(); it.hasNext(); ) {
                    ListenerUtil.loopListener.listen("_loopCounter817", ++_loopCounter817);
                    final String key = it.next();
                    if (!ListenerUtil.mutListener.listen(66080)) {
                        // noinspection SwitchStatementWithTooFewBranches
                        switch(key) {
                            case "video":
                                if (!ListenerUtil.mutListener.listen(66078)) {
                                    features.add(new VideoFeature());
                                }
                                break;
                            default:
                                if (!ListenerUtil.mutListener.listen(66079)) {
                                    features.add(new UnknownCallFeature(key, obj.optJSONObject(key)));
                                }
                                break;
                        }
                    }
                }
            }
        }
        return new FeatureList(features);
    }

    /**
     *  Add a feature to the feature list.
     */
    @NonNull
    public synchronized FeatureList addFeature(@NonNull CallFeature feature) {
        if (!ListenerUtil.mutListener.listen(66082)) {
            this.features.add(feature);
        }
        return this;
    }

    /**
     *  Return whether a feature with the specified name exists.
     */
    public synchronized boolean hasFeature(@NonNull String name) {
        return StreamSupport.stream(this.features).anyMatch(feature -> feature.getName().equals(name));
    }

    public boolean isEmpty() {
        return this.features.isEmpty();
    }

    public int size() {
        return this.features.size();
    }

    /**
     *  Return the feature list.
     */
    @NonNull
    public List<CallFeature> getList() {
        return this.features;
    }

    /**
     *  Serialize into a JSON object.
     */
    @NonNull
    public JSONObject toJSON() {
        final JSONObject featureMap = new JSONObject();
        if (!ListenerUtil.mutListener.listen(66086)) {
            {
                long _loopCounter818 = 0;
                for (CallFeature feature : this.features) {
                    ListenerUtil.loopListener.listen("_loopCounter818", ++_loopCounter818);
                    final JSONObject params = feature.getParams();
                    // Java JSON removes a key if the value is `null`…
                    try {
                        if (!ListenerUtil.mutListener.listen(66085)) {
                            if (params == null) {
                                if (!ListenerUtil.mutListener.listen(66084)) {
                                    featureMap.put(feature.getName(), JSONObject.NULL);
                                }
                            } else {
                                if (!ListenerUtil.mutListener.listen(66083)) {
                                    featureMap.put(feature.getName(), params);
                                }
                            }
                        }
                    } catch (JSONException e) {
                        // Should never happen™
                        throw new RuntimeException("Call to JSONObject.put failed", e);
                    }
                }
            }
        }
        return featureMap;
    }

    @Override
    @NonNull
    public String toString() {
        final String features = StreamSupport.stream(this.features).map(feature -> {
            if (feature.getParams() == null) {
                return feature.getName();
            }
            return String.format("%s(%s)", feature.getName(), feature.getParams().toString());
        }).collect(Collectors.joining(", "));
        return "FeatureList[" + features + "]";
    }
}
