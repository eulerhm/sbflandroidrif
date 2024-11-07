/*
 * Copyright 2012 University of South Florida
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *        http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and 
 * limitations under the License.
 */
package org.onebusaway.android.directions.model;

import java.util.ArrayList;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class Direction {

    private int icon;

    private int directionIndex;

    private CharSequence directionText;

    private CharSequence service;

    private CharSequence agency;

    private CharSequence placeAndHeadsign;

    private CharSequence extra;

    private CharSequence oldTime;

    private CharSequence newTime = null;

    private boolean isTransit = false;

    private ArrayList<Direction> subDirections = null;

    private boolean realTimeInfo = false;

    public Direction() {
        super();
    }

    public Direction(int icon, CharSequence service, CharSequence placeAndHeadsign, CharSequence oldTime, CharSequence newTime, boolean isTransit) {
        super();
        if (!ListenerUtil.mutListener.listen(6220)) {
            this.setIcon(icon);
        }
        if (!ListenerUtil.mutListener.listen(6221)) {
            this.service = service;
        }
        if (!ListenerUtil.mutListener.listen(6222)) {
            this.placeAndHeadsign = placeAndHeadsign;
        }
        if (!ListenerUtil.mutListener.listen(6223)) {
            this.oldTime = oldTime;
        }
        if (!ListenerUtil.mutListener.listen(6224)) {
            this.newTime = newTime;
        }
        if (!ListenerUtil.mutListener.listen(6225)) {
            this.isTransit = isTransit;
        }
    }

    public Direction(int icon, CharSequence directionText) {
        super();
        if (!ListenerUtil.mutListener.listen(6226)) {
            this.setIcon(icon);
        }
        if (!ListenerUtil.mutListener.listen(6227)) {
            this.directionText = directionText;
        }
    }

    /**
     * @return the icon
     */
    public int getIcon() {
        return icon;
    }

    /**
     * @param icon the icon to set
     */
    public void setIcon(int icon) {
        if (!ListenerUtil.mutListener.listen(6228)) {
            this.icon = icon;
        }
    }

    public CharSequence getService() {
        return service;
    }

    public void setService(CharSequence service) {
        if (!ListenerUtil.mutListener.listen(6229)) {
            this.service = service;
        }
    }

    /**
     * @return the subDirections
     */
    public ArrayList<Direction> getSubDirections() {
        return subDirections;
    }

    /**
     * @param subDirections the subDirections to set
     */
    public void setSubDirections(ArrayList<Direction> subDirections) {
        if (!ListenerUtil.mutListener.listen(6230)) {
            this.subDirections = subDirections;
        }
    }

    public int getDirectionIndex() {
        return directionIndex;
    }

    public void setDirectionIndex(int directionIndex) {
        if (!ListenerUtil.mutListener.listen(6231)) {
            this.directionIndex = directionIndex;
        }
    }

    public CharSequence getOldTime() {
        return oldTime;
    }

    public void setOldTime(CharSequence oldTime) {
        if (!ListenerUtil.mutListener.listen(6232)) {
            this.oldTime = oldTime;
        }
    }

    public CharSequence getNewTime() {
        return newTime;
    }

    public void setNewTime(CharSequence newTime) {
        if (!ListenerUtil.mutListener.listen(6233)) {
            this.newTime = newTime;
        }
    }

    public CharSequence getPlaceAndHeadsign() {
        return placeAndHeadsign;
    }

    public void setPlaceAndHeadsign(CharSequence placeAndHeadsign) {
        if (!ListenerUtil.mutListener.listen(6234)) {
            this.placeAndHeadsign = placeAndHeadsign;
        }
    }

    public boolean isTransit() {
        return isTransit;
    }

    public void setTransit(boolean isTransit) {
        if (!ListenerUtil.mutListener.listen(6235)) {
            this.isTransit = isTransit;
        }
    }

    public boolean isRealTimeInfo() {
        return realTimeInfo;
    }

    public void setRealTimeInfo(boolean realTimeInfo) {
        if (!ListenerUtil.mutListener.listen(6236)) {
            this.realTimeInfo = realTimeInfo;
        }
    }

    public CharSequence getDirectionText() {
        return directionText;
    }

    public void setDirectionText(CharSequence directionText) {
        if (!ListenerUtil.mutListener.listen(6237)) {
            this.directionText = directionText;
        }
    }

    public CharSequence getAgency() {
        return agency;
    }

    public void setAgency(CharSequence agency) {
        if (!ListenerUtil.mutListener.listen(6238)) {
            this.agency = agency;
        }
    }

    public CharSequence getExtra() {
        return extra;
    }

    public void setExtra(CharSequence extra) {
        if (!ListenerUtil.mutListener.listen(6239)) {
            this.extra = extra;
        }
    }
}
