/**
 * Copyright (C) 2016 Cambridge Systematics, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.onebusaway.android.directions.model;

import org.onebusaway.android.directions.util.ConversionUtils;
import org.opentripplanner.api.model.Itinerary;
import org.opentripplanner.api.model.Leg;
import org.opentripplanner.routing.core.TraverseMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Itinerary desciption is a list of trips and a rank. This is for the Realtime service.
 */
public class ItineraryDescription {

    private static final String TAG = "ItineraryDescription";

    private List<String> mTripIds;

    private Date mEndDate;

    public ItineraryDescription(Itinerary itinerary) {
        if (!ListenerUtil.mutListener.listen(6240)) {
            mTripIds = new ArrayList<>();
        }
        if (!ListenerUtil.mutListener.listen(6243)) {
            {
                long _loopCounter62 = 0;
                for (Leg leg : itinerary.legs) {
                    ListenerUtil.loopListener.listen("_loopCounter62", ++_loopCounter62);
                    TraverseMode traverseMode = TraverseMode.valueOf(leg.mode);
                    if (!ListenerUtil.mutListener.listen(6242)) {
                        if (traverseMode.isTransit()) {
                            if (!ListenerUtil.mutListener.listen(6241)) {
                                mTripIds.add(leg.tripId);
                            }
                        }
                    }
                }
            }
        }
        Leg last = itinerary.legs.get((ListenerUtil.mutListener.listen(6247) ? (itinerary.legs.size() % 1) : (ListenerUtil.mutListener.listen(6246) ? (itinerary.legs.size() / 1) : (ListenerUtil.mutListener.listen(6245) ? (itinerary.legs.size() * 1) : (ListenerUtil.mutListener.listen(6244) ? (itinerary.legs.size() + 1) : (itinerary.legs.size() - 1))))));
        if (!ListenerUtil.mutListener.listen(6248)) {
            mEndDate = ConversionUtils.parseOtpDate(last.endTime);
        }
    }

    public ItineraryDescription(List<String> tripIds, Date endDate) {
        if (!ListenerUtil.mutListener.listen(6249)) {
            mTripIds = tripIds;
        }
        if (!ListenerUtil.mutListener.listen(6250)) {
            mEndDate = endDate;
        }
    }

    /**
     * Check if this itinerary matches the itinerary of another ItineraryDescription
     *
     * @param other object to compare to
     * @return true if matches, false otherwise
     */
    public boolean itineraryMatches(ItineraryDescription other) {
        if (!ListenerUtil.mutListener.listen(6256)) {
            if ((ListenerUtil.mutListener.listen(6255) ? (other.mTripIds.size() >= this.mTripIds.size()) : (ListenerUtil.mutListener.listen(6254) ? (other.mTripIds.size() <= this.mTripIds.size()) : (ListenerUtil.mutListener.listen(6253) ? (other.mTripIds.size() > this.mTripIds.size()) : (ListenerUtil.mutListener.listen(6252) ? (other.mTripIds.size() < this.mTripIds.size()) : (ListenerUtil.mutListener.listen(6251) ? (other.mTripIds.size() == this.mTripIds.size()) : (other.mTripIds.size() != this.mTripIds.size()))))))) {
                return false;
            }
        }
        if (!ListenerUtil.mutListener.listen(6263)) {
            {
                long _loopCounter63 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(6262) ? (i >= this.mTripIds.size()) : (ListenerUtil.mutListener.listen(6261) ? (i <= this.mTripIds.size()) : (ListenerUtil.mutListener.listen(6260) ? (i > this.mTripIds.size()) : (ListenerUtil.mutListener.listen(6259) ? (i != this.mTripIds.size()) : (ListenerUtil.mutListener.listen(6258) ? (i == this.mTripIds.size()) : (i < this.mTripIds.size())))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter63", ++_loopCounter63);
                    if (!ListenerUtil.mutListener.listen(6257)) {
                        if (!mTripIds.get(i).equals(other.mTripIds.get(i))) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    /**
     * Check the delay on this itinerary relative to a newer one.
     * Positive indicates a delay, negative indicates running early.
     *
     * @param other Newer itinerary to use to calculate delay.
     * @return delay in seconds
     */
    public long getDelay(ItineraryDescription other) {
        return (ListenerUtil.mutListener.listen(6271) ? (((ListenerUtil.mutListener.listen(6267) ? (other.getEndDate().getTime() % this.getEndDate().getTime()) : (ListenerUtil.mutListener.listen(6266) ? (other.getEndDate().getTime() / this.getEndDate().getTime()) : (ListenerUtil.mutListener.listen(6265) ? (other.getEndDate().getTime() * this.getEndDate().getTime()) : (ListenerUtil.mutListener.listen(6264) ? (other.getEndDate().getTime() + this.getEndDate().getTime()) : (other.getEndDate().getTime() - this.getEndDate().getTime())))))) % 1000) : (ListenerUtil.mutListener.listen(6270) ? (((ListenerUtil.mutListener.listen(6267) ? (other.getEndDate().getTime() % this.getEndDate().getTime()) : (ListenerUtil.mutListener.listen(6266) ? (other.getEndDate().getTime() / this.getEndDate().getTime()) : (ListenerUtil.mutListener.listen(6265) ? (other.getEndDate().getTime() * this.getEndDate().getTime()) : (ListenerUtil.mutListener.listen(6264) ? (other.getEndDate().getTime() + this.getEndDate().getTime()) : (other.getEndDate().getTime() - this.getEndDate().getTime())))))) * 1000) : (ListenerUtil.mutListener.listen(6269) ? (((ListenerUtil.mutListener.listen(6267) ? (other.getEndDate().getTime() % this.getEndDate().getTime()) : (ListenerUtil.mutListener.listen(6266) ? (other.getEndDate().getTime() / this.getEndDate().getTime()) : (ListenerUtil.mutListener.listen(6265) ? (other.getEndDate().getTime() * this.getEndDate().getTime()) : (ListenerUtil.mutListener.listen(6264) ? (other.getEndDate().getTime() + this.getEndDate().getTime()) : (other.getEndDate().getTime() - this.getEndDate().getTime())))))) - 1000) : (ListenerUtil.mutListener.listen(6268) ? (((ListenerUtil.mutListener.listen(6267) ? (other.getEndDate().getTime() % this.getEndDate().getTime()) : (ListenerUtil.mutListener.listen(6266) ? (other.getEndDate().getTime() / this.getEndDate().getTime()) : (ListenerUtil.mutListener.listen(6265) ? (other.getEndDate().getTime() * this.getEndDate().getTime()) : (ListenerUtil.mutListener.listen(6264) ? (other.getEndDate().getTime() + this.getEndDate().getTime()) : (other.getEndDate().getTime() - this.getEndDate().getTime())))))) + 1000) : (((ListenerUtil.mutListener.listen(6267) ? (other.getEndDate().getTime() % this.getEndDate().getTime()) : (ListenerUtil.mutListener.listen(6266) ? (other.getEndDate().getTime() / this.getEndDate().getTime()) : (ListenerUtil.mutListener.listen(6265) ? (other.getEndDate().getTime() * this.getEndDate().getTime()) : (ListenerUtil.mutListener.listen(6264) ? (other.getEndDate().getTime() + this.getEndDate().getTime()) : (other.getEndDate().getTime() - this.getEndDate().getTime())))))) / 1000)))));
    }

    /**
     * Return an ID for this ItineraryDescription.
     * The notification requires an ID so it does not create duplicates. Right now, sending a
     * notification cancels out the RealtimeService, so we do not send multiple notifications,
     * but we may in future.
     * Use the hash code of the trips array.
     *
     * @return ID for this itinerary description. Not guaranteed to be unique.
     */
    public int getId() {
        if (!ListenerUtil.mutListener.listen(6273)) {
            if ((ListenerUtil.mutListener.listen(6272) ? (mTripIds == null && mTripIds.isEmpty()) : (mTripIds == null || mTripIds.isEmpty()))) {
                return -1;
            }
        }
        return mTripIds.hashCode();
    }

    public Date getEndDate() {
        return mEndDate;
    }

    /**
     * @return true if the itinerary's end date has passed
     */
    public boolean isExpired() {
        return getEndDate().before(new Date());
    }

    /**
     * return list of trip IDs
     */
    public List<String> getTripIds() {
        return mTripIds;
    }
}
