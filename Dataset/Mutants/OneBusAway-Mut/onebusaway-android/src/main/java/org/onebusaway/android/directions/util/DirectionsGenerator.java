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
package org.onebusaway.android.directions.util;

import org.onebusaway.android.R;
import org.onebusaway.android.directions.model.Direction;
import org.opentripplanner.api.model.AbsoluteDirection;
import org.opentripplanner.api.model.Leg;
import org.opentripplanner.api.model.Place;
import org.opentripplanner.api.model.RelativeDirection;
import org.opentripplanner.api.model.WalkStep;
import org.opentripplanner.routing.core.TraverseMode;
import org.opentripplanner.routing.core.TraverseModeSet;
import org.opentripplanner.routing.patch.Alerts;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.preference.PreferenceManager;
import android.text.SpannableString;
import android.text.TextUtils;
import android.util.Log;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class DirectionsGenerator {

    private static final String TAG = "DirectionsGenerator";

    public static final String PREFERENCE_KEY_API_VERSION = "last_api_version";

    public static final int API_VERSION_V1 = 1;

    public static final String FORMAT_OTP_SERVER_DATE_RESPONSE = "yyyy-MM-dd\'T\'HH:mm:ssZZ";

    private List<Leg> legs = new ArrayList<Leg>();

    private ArrayList<Direction> directions = new ArrayList<Direction>();

    private double totalDistance = 0;

    private double totalTimeTraveled = 0;

    private Context applicationContext;

    public DirectionsGenerator(List<Leg> legs, Context applicationContext) {
        if (!ListenerUtil.mutListener.listen(5393)) {
            this.legs.addAll(legs);
        }
        if (!ListenerUtil.mutListener.listen(5394)) {
            this.applicationContext = applicationContext;
        }
        if (!ListenerUtil.mutListener.listen(5395)) {
            convertToDirectionList();
        }
    }

    /**
     * @return the directions
     */
    public ArrayList<Direction> getDirections() {
        return directions;
    }

    /**
     * @param directions the directions to set
     */
    public void setDirections(ArrayList<Direction> directions) {
        if (!ListenerUtil.mutListener.listen(5396)) {
            this.directions = directions;
        }
    }

    public void addDirection(Direction dir) {
        if (!ListenerUtil.mutListener.listen(5398)) {
            if (directions == null) {
                if (!ListenerUtil.mutListener.listen(5397)) {
                    directions = new ArrayList<Direction>();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(5399)) {
            directions.add(dir);
        }
    }

    private void convertToDirectionList() {
        int index = 0;
        if (!ListenerUtil.mutListener.listen(5415)) {
            {
                long _loopCounter46 = 0;
                for (Leg leg : legs) {
                    ListenerUtil.loopListener.listen("_loopCounter46", ++_loopCounter46);
                    if (!ListenerUtil.mutListener.listen(5400)) {
                        index++;
                    }
                    if (!ListenerUtil.mutListener.listen(5401)) {
                        setTotalDistance(getTotalDistance() + leg.distance);
                    }
                    TraverseMode traverseMode = TraverseMode.valueOf((String) leg.mode);
                    if (!ListenerUtil.mutListener.listen(5414)) {
                        if (traverseMode.isOnStreetNonTransit()) {
                            Direction dir = generateNonTransitDirections(leg);
                            if (!ListenerUtil.mutListener.listen(5411)) {
                                if (dir == null) {
                                    continue;
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(5412)) {
                                dir.setDirectionIndex(index);
                            }
                            if (!ListenerUtil.mutListener.listen(5413)) {
                                addDirection(dir);
                            }
                        } else {
                            ArrayList<Direction> directions = generateTransitDirections(leg);
                            if (!ListenerUtil.mutListener.listen(5402)) {
                                if (directions == null) {
                                    continue;
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(5405)) {
                                if (directions.get(0) != null) {
                                    if (!ListenerUtil.mutListener.listen(5403)) {
                                        directions.get(0).setDirectionIndex(index);
                                    }
                                    if (!ListenerUtil.mutListener.listen(5404)) {
                                        addDirection(directions.get(0));
                                    }
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(5410)) {
                                if (directions.get(1) != null) {
                                    if (!ListenerUtil.mutListener.listen(5407)) {
                                        if (directions.get(0) != null) {
                                            if (!ListenerUtil.mutListener.listen(5406)) {
                                                index++;
                                            }
                                        }
                                    }
                                    if (!ListenerUtil.mutListener.listen(5408)) {
                                        directions.get(1).setDirectionIndex(index);
                                    }
                                    if (!ListenerUtil.mutListener.listen(5409)) {
                                        addDirection(directions.get(1));
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private Direction generateNonTransitDirections(Leg leg) {
        Direction direction = new Direction();
        // Get appropriate action and icon
        String action = applicationContext.getResources().getString(R.string.step_by_step_non_transit_mode_walk_action);
        TraverseMode mode = TraverseMode.valueOf((String) leg.mode);
        int icon = getModeIcon(new TraverseModeSet(mode));
        if (!ListenerUtil.mutListener.listen(5418)) {
            if (mode.compareTo(TraverseMode.BICYCLE) == 0) {
                if (!ListenerUtil.mutListener.listen(5417)) {
                    action = applicationContext.getResources().getString(R.string.step_by_step_non_transit_mode_bicycle_action);
                }
            } else if (mode.compareTo(TraverseMode.CAR) == 0) {
                if (!ListenerUtil.mutListener.listen(5416)) {
                    action = applicationContext.getResources().getString(R.string.step_by_step_non_transit_mode_car_action);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(5419)) {
            direction.setIcon(icon);
        }
        // Main direction
        Place fromPlace = leg.from;
        Place toPlace = leg.to;
        String mainDirectionText = action;
        if (!ListenerUtil.mutListener.listen(5420)) {
            mainDirectionText += fromPlace.name == null ? "" : " " + applicationContext.getResources().getString(R.string.step_by_step_non_transit_from) + " " + getLocalizedStreetName(fromPlace.name, applicationContext.getResources());
        }
        if (!ListenerUtil.mutListener.listen(5421)) {
            mainDirectionText += toPlace.name == null ? "" : " " + applicationContext.getResources().getString(R.string.step_by_step_non_transit_to) + " " + getLocalizedStreetName(toPlace.name, applicationContext.getResources());
        }
        String extraStopInformation = toPlace.stopCode;
        long legDuration;
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(applicationContext);
        if (prefs.getInt(OTPConstants.PREFERENCE_KEY_API_VERSION, OTPConstants.API_VERSION_V1) == OTPConstants.API_VERSION_V1) {
            legDuration = leg.duration;
        } else {
            legDuration = (ListenerUtil.mutListener.listen(5425) ? (leg.duration % 1000) : (ListenerUtil.mutListener.listen(5424) ? (leg.duration * 1000) : (ListenerUtil.mutListener.listen(5423) ? (leg.duration - 1000) : (ListenerUtil.mutListener.listen(5422) ? (leg.duration + 1000) : (leg.duration / 1000)))));
        }
        if (!ListenerUtil.mutListener.listen(5427)) {
            if (!TextUtils.isEmpty(extraStopInformation)) {
                if (!ListenerUtil.mutListener.listen(5426)) {
                    mainDirectionText += " (" + extraStopInformation + ")";
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(5428)) {
            mainDirectionText += "\n[" + ConversionUtils.getFormattedDistance(leg.distance, applicationContext) + " - " + ConversionUtils.getFormattedDurationTextNoSeconds(legDuration, false, applicationContext) + " ]";
        }
        if (!ListenerUtil.mutListener.listen(5429)) {
            direction.setDirectionText(mainDirectionText);
        }
        // Sub-direction
        List<WalkStep> walkSteps = leg.getSteps();
        if (!ListenerUtil.mutListener.listen(5430)) {
            if (walkSteps == null) {
                return direction;
            }
        }
        ArrayList<Direction> subDirections = new ArrayList<Direction>(walkSteps.size());
        if (!ListenerUtil.mutListener.listen(5453)) {
            {
                long _loopCounter47 = 0;
                for (WalkStep step : walkSteps) {
                    ListenerUtil.loopListener.listen("_loopCounter47", ++_loopCounter47);
                    int subdirection_icon = -1;
                    Direction dir = new Direction();
                    String subDirectionText = "";
                    double distance = step.distance;
                    RelativeDirection relativeDir = step.relativeDirection;
                    String relativeDirString = getLocalizedRelativeDir(relativeDir, applicationContext.getResources());
                    String streetName = step.streetName;
                    AbsoluteDirection absoluteDir = step.absoluteDirection;
                    String absoluteDirString = getLocalizedAbsoluteDir(absoluteDir, applicationContext.getResources());
                    String exit = step.exit;
                    boolean isStayOn = (step.stayOn == null ? false : step.stayOn);
                    boolean isBogusName = (step.bogusName == null ? false : step.bogusName);
                    double lon = step.lon;
                    double lat = step.lat;
                    String streetConnector = applicationContext.getResources().getString(R.string.step_by_step_non_transit_connector_street_name);
                    // Elevation[] elevation = step.getElevation();  //Removed elevation for now, since we're not doing anything with it and it causes version issues between OTP server APIs v0.9.1-SNAPSHOT and v0.9.2-SNAPSHOT
                    List<Alerts> alert = step.alerts;
                    if (!ListenerUtil.mutListener.listen(5447)) {
                        // Walk East
                        if (relativeDir == null) {
                            if (!ListenerUtil.mutListener.listen(5445)) {
                                subDirectionText += action + " " + applicationContext.getResources().getString(R.string.step_by_step_non_transit_heading) + " ";
                            }
                            if (!ListenerUtil.mutListener.listen(5446)) {
                                subDirectionText += absoluteDirString + " ";
                            }
                        } else // (Turn left)/(Continue)
                        {
                            RelativeDirection rDir = RelativeDirection.valueOf(relativeDir.name());
                            if (!ListenerUtil.mutListener.listen(5431)) {
                                subdirection_icon = getRelativeDirectionIcon(rDir, applicationContext.getResources());
                            }
                            if (!ListenerUtil.mutListener.listen(5434)) {
                                // Do not need TURN Continue
                                if ((ListenerUtil.mutListener.listen(5432) ? (rDir.compareTo(RelativeDirection.RIGHT) == 0 && rDir.compareTo(RelativeDirection.LEFT) == 0) : (rDir.compareTo(RelativeDirection.RIGHT) == 0 || rDir.compareTo(RelativeDirection.LEFT) == 0))) {
                                    if (!ListenerUtil.mutListener.listen(5433)) {
                                        subDirectionText += applicationContext.getResources().getString(R.string.step_by_step_non_transit_turn) + " ";
                                    }
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(5435)) {
                                subDirectionText += relativeDirString + " ";
                            }
                            if (!ListenerUtil.mutListener.listen(5444)) {
                                if ((ListenerUtil.mutListener.listen(5436) ? (rDir.compareTo(RelativeDirection.CIRCLE_CLOCKWISE) == 0 && rDir.compareTo(RelativeDirection.CIRCLE_COUNTERCLOCKWISE) == 0) : (rDir.compareTo(RelativeDirection.CIRCLE_CLOCKWISE) == 0 || rDir.compareTo(RelativeDirection.CIRCLE_COUNTERCLOCKWISE) == 0))) {
                                    if (!ListenerUtil.mutListener.listen(5443)) {
                                        if (step.exit != null) {
                                            try {
                                                String ordinal = getOrdinal(Integer.parseInt(step.exit), applicationContext.getResources());
                                                if (!ListenerUtil.mutListener.listen(5440)) {
                                                    if (ordinal != null) {
                                                        if (!ListenerUtil.mutListener.listen(5439)) {
                                                            subDirectionText += ordinal + " ";
                                                        }
                                                    } else {
                                                        if (!ListenerUtil.mutListener.listen(5438)) {
                                                            subDirectionText += applicationContext.getResources().getString(R.string.step_by_step_non_transit_roundabout_number) + " " + ordinal + " ";
                                                        }
                                                    }
                                                }
                                            } catch (NumberFormatException e) {
                                                if (!ListenerUtil.mutListener.listen(5437)) {
                                                    // If is not a step_by_step_non_transit_roundabout_number and is not null is better to try to display it
                                                    subDirectionText += step.exit + " ";
                                                }
                                            }
                                            if (!ListenerUtil.mutListener.listen(5441)) {
                                                subDirectionText += applicationContext.getResources().getString(R.string.step_by_step_non_transit_roundabout_exit) + " ";
                                            }
                                            if (!ListenerUtil.mutListener.listen(5442)) {
                                                streetConnector = applicationContext.getResources().getString(R.string.step_by_step_non_transit_connector_street_name_roundabout);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(5448)) {
                        subDirectionText += streetConnector + " " + getLocalizedStreetName(streetName, applicationContext.getResources()) + " ";
                    }
                    if (!ListenerUtil.mutListener.listen(5449)) {
                        subDirectionText += "\n[" + ConversionUtils.getFormattedDistance(distance, applicationContext) + " ]";
                    }
                    if (!ListenerUtil.mutListener.listen(5450)) {
                        dir.setDirectionText(subDirectionText);
                    }
                    if (!ListenerUtil.mutListener.listen(5451)) {
                        dir.setIcon(subdirection_icon);
                    }
                    if (!ListenerUtil.mutListener.listen(5452)) {
                        // Add new sub-direction
                        subDirections.add(dir);
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(5454)) {
            direction.setSubDirections(subDirections);
        }
        return direction;
    }

    private static String getOrdinal(int number, Resources resources) {
        switch(number) {
            case 1:
                return resources.getString(R.string.step_by_step_non_transit_roundabout_ordinal_first);
            case 2:
                return resources.getString(R.string.step_by_step_non_transit_roundabout_ordinal_second);
            case 3:
                return resources.getString(R.string.step_by_step_non_transit_roundabout_ordinal_third);
            case 4:
                return resources.getString(R.string.step_by_step_non_transit_roundabout_ordinal_fourth);
            case 5:
                return resources.getString(R.string.step_by_step_non_transit_roundabout_ordinal_fifth);
            case 6:
                return resources.getString(R.string.step_by_step_non_transit_roundabout_ordinal_sixth);
            case 7:
                return resources.getString(R.string.step_by_step_non_transit_roundabout_ordinal_seventh);
            case 8:
                return resources.getString(R.string.step_by_step_non_transit_roundabout_ordinal_eighth);
            case 9:
                return resources.getString(R.string.step_by_step_non_transit_roundabout_ordinal_ninth);
            case 10:
                return resources.getString(R.string.step_by_step_non_transit_roundabout_ordinal_tenth);
            default:
                return null;
        }
    }

    // Dirty fix to avoid the presence of names for unnamed streets (as road, track, etc.) for other languages than English
    public static String getLocalizedStreetName(String streetName, Resources resources) {
        if (streetName != null) {
            if (streetName.equals("bike path")) {
                return resources.getString(R.string.street_type_bike_path);
            } else if (streetName.equals("open area")) {
                return resources.getString(R.string.street_type_open_area);
            } else if (streetName.equals("path")) {
                return resources.getString(R.string.street_type_path);
            } else if (streetName.equals("bridleway")) {
                return resources.getString(R.string.street_type_bridleway);
            } else if (streetName.equals("footpath")) {
                return resources.getString(R.string.street_type_footpath);
            } else if (streetName.equals("platform")) {
                return resources.getString(R.string.street_type_platform);
            } else if (streetName.equals("footbridge")) {
                return resources.getString(R.string.street_type_footbridge);
            } else if (streetName.equals("underpass")) {
                return resources.getString(R.string.street_type_underpass);
            } else if (streetName.equals("road")) {
                return resources.getString(R.string.street_type_road);
            } else if (streetName.equals("ramp")) {
                return resources.getString(R.string.street_type_ramp);
            } else if (streetName.equals("link")) {
                return resources.getString(R.string.street_type_link);
            } else if (streetName.equals("service road")) {
                return resources.getString(R.string.street_type_service_road);
            } else if (streetName.equals("alley")) {
                return resources.getString(R.string.street_type_alley);
            } else if (streetName.equals("parking aisle")) {
                return resources.getString(R.string.street_type_parking_aisle);
            } else if (streetName.equals("byway")) {
                return resources.getString(R.string.street_type_byway);
            } else if (streetName.equals("track")) {
                return resources.getString(R.string.street_type_track);
            } else if (streetName.equals("sidewalk")) {
                return resources.getString(R.string.street_type_sidewalk);
            } else if (streetName.startsWith("osm:node:")) {
                return resources.getString(R.string.street_type_sidewalk);
            } else if (streetName.equals("steps")) {
                return resources.getString(R.string.street_type_steps);
            } else {
                return streetName;
            }
        } else {
            return resources.getString(R.string.street_type_sidewalk);
        }
    }

    public static String getLocalizedRelativeDir(RelativeDirection relDir, Resources resources) {
        if (!ListenerUtil.mutListener.listen(5456)) {
            if (relDir != null) {
                if (!ListenerUtil.mutListener.listen(5455)) {
                    if (relDir.equals(RelativeDirection.CIRCLE_CLOCKWISE)) {
                        return resources.getString(R.string.step_by_step_non_transit_dir_relative_circle_clockwise);
                    } else if (relDir.equals(RelativeDirection.CIRCLE_COUNTERCLOCKWISE)) {
                        return resources.getString(R.string.step_by_step_non_transit_dir_relative_circle_counterclockwise);
                    } else if (relDir.equals(RelativeDirection.CONTINUE)) {
                        return resources.getString(R.string.step_by_step_non_transit_dir_relative_continue);
                    } else if (relDir.equals(RelativeDirection.DEPART)) {
                        return resources.getString(R.string.step_by_step_non_transit_dir_relative_depart);
                    } else if (relDir.equals(RelativeDirection.ELEVATOR)) {
                        return resources.getString(R.string.step_by_step_non_transit_dir_relative_elevator);
                    } else if (relDir.equals(RelativeDirection.HARD_LEFT)) {
                        return resources.getString(R.string.step_by_step_non_transit_dir_relative_hard_left);
                    } else if (relDir.equals(RelativeDirection.HARD_RIGHT)) {
                        return resources.getString(R.string.step_by_step_non_transit_dir_relative_hard_right);
                    } else if (relDir.equals(RelativeDirection.LEFT)) {
                        return resources.getString(R.string.step_by_step_non_transit_dir_relative_left);
                    } else if (relDir.equals(RelativeDirection.RIGHT)) {
                        return resources.getString(R.string.step_by_step_non_transit_dir_relative_right);
                    } else if (relDir.equals(RelativeDirection.SLIGHTLY_LEFT)) {
                        return resources.getString(R.string.step_by_step_non_transit_dir_relative_slightly_left);
                    } else if (relDir.equals(RelativeDirection.SLIGHTLY_RIGHT)) {
                        return resources.getString(R.string.step_by_step_non_transit_dir_relative_slightly_right);
                    } else if (relDir.equals(RelativeDirection.UTURN_LEFT)) {
                        return resources.getString(R.string.step_by_step_non_transit_dir_relative_uturn_left);
                    } else if (relDir.equals(RelativeDirection.UTURN_RIGHT)) {
                        return resources.getString(R.string.step_by_step_non_transit_dir_relative_uturn_right);
                    }
                }
            }
        }
        return null;
    }

    public static String getLocalizedAbsoluteDir(AbsoluteDirection absDir, Resources resources) {
        if (!ListenerUtil.mutListener.listen(5458)) {
            if (absDir != null) {
                if (!ListenerUtil.mutListener.listen(5457)) {
                    if (absDir.equals(AbsoluteDirection.EAST)) {
                        return resources.getString(R.string.step_by_step_non_transit_dir_absolute_east);
                    } else if (absDir.equals(AbsoluteDirection.NORTH)) {
                        return resources.getString(R.string.step_by_step_non_transit_dir_absolute_north);
                    } else if (absDir.equals(AbsoluteDirection.NORTHEAST)) {
                        return resources.getString(R.string.step_by_step_non_transit_dir_absolute_northeast);
                    } else if (absDir.equals(AbsoluteDirection.NORTHWEST)) {
                        return resources.getString(R.string.step_by_step_non_transit_dir_absolute_northwest);
                    } else if (absDir.equals(AbsoluteDirection.SOUTH)) {
                        return resources.getString(R.string.step_by_step_non_transit_dir_absolute_south);
                    } else if (absDir.equals(AbsoluteDirection.SOUTHEAST)) {
                        return resources.getString(R.string.step_by_step_non_transit_dir_absolute_southeast);
                    } else if (absDir.equals(AbsoluteDirection.SOUTHWEST)) {
                        return resources.getString(R.string.step_by_step_non_transit_dir_absolute_southwest);
                    } else if (absDir.equals(AbsoluteDirection.WEST)) {
                        return resources.getString(R.string.step_by_step_non_transit_dir_absolute_west);
                    }
                }
            }
        }
        return null;
    }

    public static String getLocalizedMode(TraverseMode mode, Resources resources) {
        if (!ListenerUtil.mutListener.listen(5460)) {
            if (mode != null) {
                if (!ListenerUtil.mutListener.listen(5459)) {
                    if (mode.equals(TraverseMode.TRAM)) {
                        return resources.getString(R.string.step_by_step_transit_mode_tram);
                    } else if (mode.equals(TraverseMode.SUBWAY)) {
                        return resources.getString(R.string.step_by_step_transit_mode_subway);
                    } else if (mode.equals(TraverseMode.RAIL)) {
                        return resources.getString(R.string.step_by_step_transit_mode_rail);
                    } else if (mode.equals(TraverseMode.BUS)) {
                        return resources.getString(R.string.step_by_step_transit_mode_bus);
                    } else if (mode.equals(TraverseMode.FERRY)) {
                        return resources.getString(R.string.step_by_step_transit_mode_ferry);
                    } else if (mode.equals(TraverseMode.CABLE_CAR)) {
                        return resources.getString(R.string.step_by_step_transit_mode_cable_car);
                    } else if (mode.equals(TraverseMode.GONDOLA)) {
                        return resources.getString(R.string.step_by_step_transit_mode_gondola);
                    } else if (mode.equals(TraverseMode.FUNICULAR)) {
                        return resources.getString(R.string.step_by_step_transit_mode_funicular);
                    } else if (mode.equals(TraverseMode.WALK)) {
                        return resources.getString(R.string.step_by_step_non_transit_mode_walk_action);
                    }
                }
            }
        }
        return null;
    }

    private ArrayList<Direction> generateTransitDirections(Leg leg) {
        ArrayList<Direction> directions = new ArrayList<Direction>(2);
        if (!ListenerUtil.mutListener.listen(5461)) {
            directions.add(generateTransitSubdirection(leg, true));
        }
        if (!ListenerUtil.mutListener.listen(5462)) {
            directions.add(generateTransitSubdirection(leg, false));
        }
        return directions;
    }

    public Direction generateTransitSubdirection(Leg leg, boolean isOnDirection) {
        Direction direction = new Direction();
        if (!ListenerUtil.mutListener.listen(5463)) {
            direction.setRealTimeInfo(leg.realTime);
        }
        // Set icon
        String mode = getLocalizedMode(TraverseMode.valueOf(leg.mode), applicationContext.getResources());
        int modeIcon;
        String route;
        String agencyName = leg.agencyName;
        Place from = leg.from;
        Place to = leg.to;
        Calendar newTime = Calendar.getInstance();
        Calendar oldTime = Calendar.getInstance();
        String shortName;
        // As a work-around for #662, we always use routeShortName and not tripShortName
        shortName = leg.routeShortName;
        route = ConversionUtils.getRouteLongNameSafe(leg.routeLongName, shortName, true);
        if (!ListenerUtil.mutListener.listen(5464)) {
            direction.setTransit(true);
        }
        String action, placeAndHeadsign, extra = "";
        if (isOnDirection) {
            action = applicationContext.getResources().getString(R.string.step_by_step_transit_get_on);
            placeAndHeadsign = from.name;
            TraverseModeSet modeSet = new TraverseModeSet(leg.mode);
            modeIcon = getModeIcon(modeSet);
            if (!ListenerUtil.mutListener.listen(5468)) {
                newTime.setTime(new Date(Long.parseLong(leg.startTime)));
            }
            if (!ListenerUtil.mutListener.listen(5469)) {
                oldTime.setTime(new Date(newTime.getTimeInMillis()));
            }
            if (!ListenerUtil.mutListener.listen(5470)) {
                oldTime.add(Calendar.SECOND, -leg.departureDelay);
            }
            // Only onDirection has subdirection (list of stops in between)
            ArrayList<Place> stopsInBetween = new ArrayList<Place>();
            if (!ListenerUtil.mutListener.listen(5475)) {
                if ((ListenerUtil.mutListener.listen(5471) ? ((leg.getIntermediateStops() != null) || !leg.getIntermediateStops().isEmpty()) : ((leg.getIntermediateStops() != null) && !leg.getIntermediateStops().isEmpty()))) {
                    if (!ListenerUtil.mutListener.listen(5474)) {
                        stopsInBetween.addAll(leg.getIntermediateStops());
                    }
                } else if ((ListenerUtil.mutListener.listen(5472) ? ((leg.stop != null) || !leg.stop.isEmpty()) : ((leg.stop != null) && !leg.stop.isEmpty()))) {
                    if (!ListenerUtil.mutListener.listen(5473)) {
                        stopsInBetween.addAll(leg.stop);
                    }
                }
            }
            // sub-direction
            ArrayList<Direction> subDirections = new ArrayList<Direction>();
            if (!ListenerUtil.mutListener.listen(5490)) {
                {
                    long _loopCounter48 = 0;
                    for (int i = 0; (ListenerUtil.mutListener.listen(5489) ? (i >= stopsInBetween.size()) : (ListenerUtil.mutListener.listen(5488) ? (i <= stopsInBetween.size()) : (ListenerUtil.mutListener.listen(5487) ? (i > stopsInBetween.size()) : (ListenerUtil.mutListener.listen(5486) ? (i != stopsInBetween.size()) : (ListenerUtil.mutListener.listen(5485) ? (i == stopsInBetween.size()) : (i < stopsInBetween.size())))))); i++) {
                        ListenerUtil.loopListener.listen("_loopCounter48", ++_loopCounter48);
                        Direction subDirection = new Direction();
                        Place stop = stopsInBetween.get(i);
                        String extraStopInformation = stop.stopCode;
                        String subDirectionText = Integer.toString((ListenerUtil.mutListener.listen(5479) ? (i % 1) : (ListenerUtil.mutListener.listen(5478) ? (i / 1) : (ListenerUtil.mutListener.listen(5477) ? (i * 1) : (ListenerUtil.mutListener.listen(5476) ? (i - 1) : (i + 1)))))) + ". " + stop.name;
                        if (!ListenerUtil.mutListener.listen(5481)) {
                            if (!TextUtils.isEmpty(extraStopInformation)) {
                                if (!ListenerUtil.mutListener.listen(5480)) {
                                    subDirectionText += " (" + extraStopInformation + ")";
                                }
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(5482)) {
                            subDirection.setDirectionText(subDirectionText);
                        }
                        if (!ListenerUtil.mutListener.listen(5483)) {
                            subDirection.setIcon(DirectionsGenerator.getStopIcon(modeSet));
                        }
                        if (!ListenerUtil.mutListener.listen(5484)) {
                            subDirections.add(subDirection);
                        }
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(5491)) {
                direction.setSubDirections(subDirections);
            }
            if (!ListenerUtil.mutListener.listen(5500)) {
                if ((ListenerUtil.mutListener.listen(5496) ? (stopsInBetween.size() >= 0) : (ListenerUtil.mutListener.listen(5495) ? (stopsInBetween.size() <= 0) : (ListenerUtil.mutListener.listen(5494) ? (stopsInBetween.size() < 0) : (ListenerUtil.mutListener.listen(5493) ? (stopsInBetween.size() != 0) : (ListenerUtil.mutListener.listen(5492) ? (stopsInBetween.size() == 0) : (stopsInBetween.size() > 0))))))) {
                    String connector = applicationContext.getResources().getString(R.string.step_by_step_transit_stops_in_between);
                    if (!ListenerUtil.mutListener.listen(5498)) {
                        if (stopsInBetween.size() == 1) {
                            if (!ListenerUtil.mutListener.listen(5497)) {
                                connector = applicationContext.getResources().getString(R.string.step_by_step_transit_stops_in_between_singular);
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(5499)) {
                        extra = stopsInBetween.size() + " " + connector;
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(5502)) {
                if (!TextUtils.isEmpty(leg.headsign)) {
                    if (!ListenerUtil.mutListener.listen(5501)) {
                        placeAndHeadsign += " " + applicationContext.getResources().getString(R.string.step_by_step_transit_connector_headsign) + " " + leg.headsign;
                    }
                }
            }
        } else {
            action = applicationContext.getResources().getString(R.string.step_by_step_transit_get_off);
            placeAndHeadsign = to.name;
            modeIcon = -1;
            if (!ListenerUtil.mutListener.listen(5465)) {
                newTime.setTime(new Date(Long.parseLong(leg.endTime)));
            }
            if (!ListenerUtil.mutListener.listen(5466)) {
                oldTime.setTime(new Date(newTime.getTimeInMillis()));
            }
            if (!ListenerUtil.mutListener.listen(5467)) {
                oldTime.add(Calendar.SECOND, -leg.arrivalDelay);
            }
        }
        if (!ListenerUtil.mutListener.listen(5503)) {
            direction.setIcon(modeIcon);
        }
        if (!ListenerUtil.mutListener.listen(5504)) {
            direction.setPlaceAndHeadsign(applicationContext.getResources().getString(R.string.step_by_step_transit_connector_stop_name) + " " + placeAndHeadsign);
        }
        if (!ListenerUtil.mutListener.listen(5505)) {
            direction.setService(action + " " + mode + " " + route);
        }
        if (!ListenerUtil.mutListener.listen(5506)) {
            direction.setAgency(agencyName);
        }
        if (!ListenerUtil.mutListener.listen(5507)) {
            direction.setExtra(extra);
        }
        SpannableString oldTimeString;
        if (!ListenerUtil.mutListener.listen(5509)) {
            if (leg.realTime) {
                CharSequence newTimeString;
                newTimeString = ConversionUtils.getTimeUpdated(applicationContext, leg.agencyTimeZoneOffset, oldTime.getTimeInMillis(), newTime.getTimeInMillis());
                if (!ListenerUtil.mutListener.listen(5508)) {
                    direction.setNewTime(newTimeString);
                }
            }
        }
        oldTimeString = new SpannableString(ConversionUtils.getTimeWithContext(applicationContext, leg.agencyTimeZoneOffset, oldTime.getTimeInMillis(), true));
        if (!ListenerUtil.mutListener.listen(5510)) {
            direction.setOldTime(oldTimeString);
        }
        return direction;
    }

    /**
     * Gets the mode icon for the given mode
     *
     * @return the mode icon for the given mode
     */
    public static int getModeIcon(TraverseModeSet mode) {
        if ((ListenerUtil.mutListener.listen(5511) ? (mode.contains(TraverseMode.BUS) || mode.contains(TraverseMode.RAIL)) : (mode.contains(TraverseMode.BUS) && mode.contains(TraverseMode.RAIL)))) {
            return R.drawable.ic_maps_directions_bus;
        } else if (mode.contains(TraverseMode.BUS)) {
            return R.drawable.ic_maps_directions_bus;
        } else if (mode.contains(TraverseMode.RAIL)) {
            return R.drawable.ic_directions_railway;
        } else if (mode.contains(TraverseMode.FERRY)) {
            return R.drawable.ic_directions_boat;
        } else if (mode.contains(TraverseMode.GONDOLA)) {
            return R.drawable.ic_directions_boat;
        } else if (mode.contains(TraverseMode.SUBWAY)) {
            return R.drawable.ic_directions_subway;
        } else if (mode.contains(TraverseMode.TRAM)) {
            return R.drawable.ic_directions_railway;
        } else if (mode.contains(TraverseMode.WALK)) {
            return R.drawable.ic_directions_walk;
        } else if (mode.contains(TraverseMode.BICYCLE)) {
            return R.drawable.ic_directions_bike;
        } else {
            if (!ListenerUtil.mutListener.listen(5512)) {
                Log.d(TAG, "No icon for mode set: " + mode);
            }
            return -1;
        }
    }

    /**
     * Get the transit stop icon for the given mode
     * @param mode
     * @return the transit stop icon for the given mode
     */
    public static int getStopIcon(TraverseModeSet mode) {
        if (!ListenerUtil.mutListener.listen(5514)) {
            if ((ListenerUtil.mutListener.listen(5513) ? (mode.contains(TraverseMode.BUS) || mode.contains(TraverseMode.RAIL)) : (mode.contains(TraverseMode.BUS) && mode.contains(TraverseMode.RAIL)))) {
                return R.drawable.ic_stop_flag_triangle;
            } else if (mode.contains(TraverseMode.BUS)) {
                return R.drawable.ic_stop_flag_triangle;
            } else if (mode.contains(TraverseMode.RAIL)) {
                return R.drawable.ic_stop_flag_triangle;
            }
        }
        // Just use the mode icon
        return getModeIcon(mode);
    }

    public static int getRelativeDirectionIcon(RelativeDirection relDir, Resources resources) {
        if (relDir.equals(RelativeDirection.CIRCLE_CLOCKWISE)) {
            return R.drawable.ic_rotary_clockwise;
        } else if (relDir.equals(RelativeDirection.CIRCLE_COUNTERCLOCKWISE)) {
            return R.drawable.ic_rotary_counterclockwise;
        } else if (relDir.equals(RelativeDirection.CONTINUE)) {
            return R.drawable.ic_continue;
        } else if (relDir.equals(RelativeDirection.DEPART)) {
            return R.drawable.ic_depart;
        } else if (relDir.equals(RelativeDirection.ELEVATOR)) {
            return R.drawable.ic_elevator;
        } else if (relDir.equals(RelativeDirection.HARD_LEFT)) {
            return R.drawable.ic_turn_sharp_left;
        } else if (relDir.equals(RelativeDirection.HARD_RIGHT)) {
            return R.drawable.ic_turn_sharp_right;
        } else if (relDir.equals(RelativeDirection.LEFT)) {
            return R.drawable.ic_turn_left;
        } else if (relDir.equals(RelativeDirection.RIGHT)) {
            return R.drawable.ic_turn_right;
        } else if (relDir.equals(RelativeDirection.SLIGHTLY_LEFT)) {
            return R.drawable.ic_turn_slight_left;
        } else if (relDir.equals(RelativeDirection.SLIGHTLY_RIGHT)) {
            return R.drawable.ic_turn_slight_right;
        } else if (relDir.equals(RelativeDirection.UTURN_LEFT)) {
            return R.drawable.ic_uturn_left;
        } else if (relDir.equals(RelativeDirection.UTURN_RIGHT)) {
            return R.drawable.ic_uturn_right;
        } else {
            if (!ListenerUtil.mutListener.listen(5515)) {
                Log.d(TAG, "No icon for direction: " + relDir);
            }
            return -1;
        }
    }

    /**
     * @return the totalDistance
     */
    public double getTotalDistance() {
        return totalDistance;
    }

    /**
     * @param totalDistance the totalDistance to set
     */
    public void setTotalDistance(double totalDistance) {
        if (!ListenerUtil.mutListener.listen(5516)) {
            this.totalDistance = totalDistance;
        }
    }

    /**
     * @return the totalTimeTraveled
     */
    public double getTotalTimeTraveled(Context context) {
        if (!ListenerUtil.mutListener.listen(5517)) {
            if (legs.isEmpty()) {
                return 0;
            }
        }
        Leg legStart = legs.get(0);
        String startTimeText = legStart.startTime;
        Leg legEnd = legs.get((ListenerUtil.mutListener.listen(5521) ? (legs.size() % 1) : (ListenerUtil.mutListener.listen(5520) ? (legs.size() / 1) : (ListenerUtil.mutListener.listen(5519) ? (legs.size() * 1) : (ListenerUtil.mutListener.listen(5518) ? (legs.size() + 1) : (legs.size() - 1))))));
        String endTimeText = legEnd.endTime;
        if (!ListenerUtil.mutListener.listen(5522)) {
            totalTimeTraveled = ConversionUtils.getDuration(startTimeText, endTimeText, context);
        }
        return totalTimeTraveled;
    }

    private String getTransitTitle(Leg leg) {
        // As a work-around for #662, we don't use leg.tripShortName
        String[] possibleTitles = { leg.routeShortName, leg.route, leg.routeId };
        if (!ListenerUtil.mutListener.listen(5529)) {
            {
                long _loopCounter49 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(5528) ? (i >= possibleTitles.length) : (ListenerUtil.mutListener.listen(5527) ? (i <= possibleTitles.length) : (ListenerUtil.mutListener.listen(5526) ? (i > possibleTitles.length) : (ListenerUtil.mutListener.listen(5525) ? (i != possibleTitles.length) : (ListenerUtil.mutListener.listen(5524) ? (i == possibleTitles.length) : (i < possibleTitles.length)))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter49", ++_loopCounter49);
                    if (!ListenerUtil.mutListener.listen(5523)) {
                        if (!TextUtils.isEmpty(possibleTitles[i])) {
                            return possibleTitles[i];
                        }
                    }
                }
            }
        }
        return null;
    }

    public String getItineraryTitle() {
        if (!ListenerUtil.mutListener.listen(5531)) {
            if (legs.size() == 1) {
                TraverseMode mode = TraverseMode.valueOf(legs.get(0).mode);
                if (!ListenerUtil.mutListener.listen(5530)) {
                    if (!mode.isTransit()) {
                        return getLocalizedMode(mode, applicationContext.getResources());
                    }
                }
            }
        }
        List<String> tokens = new ArrayList<>();
        if (!ListenerUtil.mutListener.listen(5536)) {
            {
                long _loopCounter50 = 0;
                for (Leg leg : legs) {
                    ListenerUtil.loopListener.listen("_loopCounter50", ++_loopCounter50);
                    TraverseMode traverseMode = TraverseMode.valueOf(leg.mode);
                    if (!ListenerUtil.mutListener.listen(5535)) {
                        if (traverseMode.isTransit()) {
                            if (!ListenerUtil.mutListener.listen(5534)) {
                                tokens.add(getTransitTitle(leg));
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(5533)) {
                                if (traverseMode.equals(TraverseMode.BICYCLE)) {
                                    if (!ListenerUtil.mutListener.listen(5532)) {
                                        tokens.add(applicationContext.getString(R.string.transit_directions_bikeshare_label));
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return TextUtils.join(", ", tokens);
    }
}
