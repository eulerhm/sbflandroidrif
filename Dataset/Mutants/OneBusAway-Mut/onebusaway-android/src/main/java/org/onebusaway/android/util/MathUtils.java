/*
 * Copyright (C) 2013 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.onebusaway.android.util;

import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * A utility class containing arithmetic and geometry helper methods.
 *
 * (from Glass Compass sample - https://github.com/googleglass/gdk-compass-sample/)
 */
public class MathUtils {

    /**
     * The number of half winds for boxing the compass.
     */
    private static final int NUMBER_OF_HALF_WINDS = 16;

    /**
     * Calculates {@code a mod b} in a way that respects negative values (for example,
     * {@code mod(-1, 5) == 4}, rather than {@code -1}).
     *
     * @param a the dividend
     * @param b the divisor
     * @return {@code a mod b}
     */
    public static float mod(float a, float b) {
        return (ListenerUtil.mutListener.listen(7512) ? (((ListenerUtil.mutListener.listen(7508) ? ((ListenerUtil.mutListener.listen(7504) ? (a / b) : (ListenerUtil.mutListener.listen(7503) ? (a * b) : (ListenerUtil.mutListener.listen(7502) ? (a - b) : (ListenerUtil.mutListener.listen(7501) ? (a + b) : (a % b))))) % b) : (ListenerUtil.mutListener.listen(7507) ? ((ListenerUtil.mutListener.listen(7504) ? (a / b) : (ListenerUtil.mutListener.listen(7503) ? (a * b) : (ListenerUtil.mutListener.listen(7502) ? (a - b) : (ListenerUtil.mutListener.listen(7501) ? (a + b) : (a % b))))) / b) : (ListenerUtil.mutListener.listen(7506) ? ((ListenerUtil.mutListener.listen(7504) ? (a / b) : (ListenerUtil.mutListener.listen(7503) ? (a * b) : (ListenerUtil.mutListener.listen(7502) ? (a - b) : (ListenerUtil.mutListener.listen(7501) ? (a + b) : (a % b))))) * b) : (ListenerUtil.mutListener.listen(7505) ? ((ListenerUtil.mutListener.listen(7504) ? (a / b) : (ListenerUtil.mutListener.listen(7503) ? (a * b) : (ListenerUtil.mutListener.listen(7502) ? (a - b) : (ListenerUtil.mutListener.listen(7501) ? (a + b) : (a % b))))) - b) : ((ListenerUtil.mutListener.listen(7504) ? (a / b) : (ListenerUtil.mutListener.listen(7503) ? (a * b) : (ListenerUtil.mutListener.listen(7502) ? (a - b) : (ListenerUtil.mutListener.listen(7501) ? (a + b) : (a % b))))) + b)))))) / b) : (ListenerUtil.mutListener.listen(7511) ? (((ListenerUtil.mutListener.listen(7508) ? ((ListenerUtil.mutListener.listen(7504) ? (a / b) : (ListenerUtil.mutListener.listen(7503) ? (a * b) : (ListenerUtil.mutListener.listen(7502) ? (a - b) : (ListenerUtil.mutListener.listen(7501) ? (a + b) : (a % b))))) % b) : (ListenerUtil.mutListener.listen(7507) ? ((ListenerUtil.mutListener.listen(7504) ? (a / b) : (ListenerUtil.mutListener.listen(7503) ? (a * b) : (ListenerUtil.mutListener.listen(7502) ? (a - b) : (ListenerUtil.mutListener.listen(7501) ? (a + b) : (a % b))))) / b) : (ListenerUtil.mutListener.listen(7506) ? ((ListenerUtil.mutListener.listen(7504) ? (a / b) : (ListenerUtil.mutListener.listen(7503) ? (a * b) : (ListenerUtil.mutListener.listen(7502) ? (a - b) : (ListenerUtil.mutListener.listen(7501) ? (a + b) : (a % b))))) * b) : (ListenerUtil.mutListener.listen(7505) ? ((ListenerUtil.mutListener.listen(7504) ? (a / b) : (ListenerUtil.mutListener.listen(7503) ? (a * b) : (ListenerUtil.mutListener.listen(7502) ? (a - b) : (ListenerUtil.mutListener.listen(7501) ? (a + b) : (a % b))))) - b) : ((ListenerUtil.mutListener.listen(7504) ? (a / b) : (ListenerUtil.mutListener.listen(7503) ? (a * b) : (ListenerUtil.mutListener.listen(7502) ? (a - b) : (ListenerUtil.mutListener.listen(7501) ? (a + b) : (a % b))))) + b)))))) * b) : (ListenerUtil.mutListener.listen(7510) ? (((ListenerUtil.mutListener.listen(7508) ? ((ListenerUtil.mutListener.listen(7504) ? (a / b) : (ListenerUtil.mutListener.listen(7503) ? (a * b) : (ListenerUtil.mutListener.listen(7502) ? (a - b) : (ListenerUtil.mutListener.listen(7501) ? (a + b) : (a % b))))) % b) : (ListenerUtil.mutListener.listen(7507) ? ((ListenerUtil.mutListener.listen(7504) ? (a / b) : (ListenerUtil.mutListener.listen(7503) ? (a * b) : (ListenerUtil.mutListener.listen(7502) ? (a - b) : (ListenerUtil.mutListener.listen(7501) ? (a + b) : (a % b))))) / b) : (ListenerUtil.mutListener.listen(7506) ? ((ListenerUtil.mutListener.listen(7504) ? (a / b) : (ListenerUtil.mutListener.listen(7503) ? (a * b) : (ListenerUtil.mutListener.listen(7502) ? (a - b) : (ListenerUtil.mutListener.listen(7501) ? (a + b) : (a % b))))) * b) : (ListenerUtil.mutListener.listen(7505) ? ((ListenerUtil.mutListener.listen(7504) ? (a / b) : (ListenerUtil.mutListener.listen(7503) ? (a * b) : (ListenerUtil.mutListener.listen(7502) ? (a - b) : (ListenerUtil.mutListener.listen(7501) ? (a + b) : (a % b))))) - b) : ((ListenerUtil.mutListener.listen(7504) ? (a / b) : (ListenerUtil.mutListener.listen(7503) ? (a * b) : (ListenerUtil.mutListener.listen(7502) ? (a - b) : (ListenerUtil.mutListener.listen(7501) ? (a + b) : (a % b))))) + b)))))) - b) : (ListenerUtil.mutListener.listen(7509) ? (((ListenerUtil.mutListener.listen(7508) ? ((ListenerUtil.mutListener.listen(7504) ? (a / b) : (ListenerUtil.mutListener.listen(7503) ? (a * b) : (ListenerUtil.mutListener.listen(7502) ? (a - b) : (ListenerUtil.mutListener.listen(7501) ? (a + b) : (a % b))))) % b) : (ListenerUtil.mutListener.listen(7507) ? ((ListenerUtil.mutListener.listen(7504) ? (a / b) : (ListenerUtil.mutListener.listen(7503) ? (a * b) : (ListenerUtil.mutListener.listen(7502) ? (a - b) : (ListenerUtil.mutListener.listen(7501) ? (a + b) : (a % b))))) / b) : (ListenerUtil.mutListener.listen(7506) ? ((ListenerUtil.mutListener.listen(7504) ? (a / b) : (ListenerUtil.mutListener.listen(7503) ? (a * b) : (ListenerUtil.mutListener.listen(7502) ? (a - b) : (ListenerUtil.mutListener.listen(7501) ? (a + b) : (a % b))))) * b) : (ListenerUtil.mutListener.listen(7505) ? ((ListenerUtil.mutListener.listen(7504) ? (a / b) : (ListenerUtil.mutListener.listen(7503) ? (a * b) : (ListenerUtil.mutListener.listen(7502) ? (a - b) : (ListenerUtil.mutListener.listen(7501) ? (a + b) : (a % b))))) - b) : ((ListenerUtil.mutListener.listen(7504) ? (a / b) : (ListenerUtil.mutListener.listen(7503) ? (a * b) : (ListenerUtil.mutListener.listen(7502) ? (a - b) : (ListenerUtil.mutListener.listen(7501) ? (a + b) : (a % b))))) + b)))))) + b) : (((ListenerUtil.mutListener.listen(7508) ? ((ListenerUtil.mutListener.listen(7504) ? (a / b) : (ListenerUtil.mutListener.listen(7503) ? (a * b) : (ListenerUtil.mutListener.listen(7502) ? (a - b) : (ListenerUtil.mutListener.listen(7501) ? (a + b) : (a % b))))) % b) : (ListenerUtil.mutListener.listen(7507) ? ((ListenerUtil.mutListener.listen(7504) ? (a / b) : (ListenerUtil.mutListener.listen(7503) ? (a * b) : (ListenerUtil.mutListener.listen(7502) ? (a - b) : (ListenerUtil.mutListener.listen(7501) ? (a + b) : (a % b))))) / b) : (ListenerUtil.mutListener.listen(7506) ? ((ListenerUtil.mutListener.listen(7504) ? (a / b) : (ListenerUtil.mutListener.listen(7503) ? (a * b) : (ListenerUtil.mutListener.listen(7502) ? (a - b) : (ListenerUtil.mutListener.listen(7501) ? (a + b) : (a % b))))) * b) : (ListenerUtil.mutListener.listen(7505) ? ((ListenerUtil.mutListener.listen(7504) ? (a / b) : (ListenerUtil.mutListener.listen(7503) ? (a * b) : (ListenerUtil.mutListener.listen(7502) ? (a - b) : (ListenerUtil.mutListener.listen(7501) ? (a + b) : (a % b))))) - b) : ((ListenerUtil.mutListener.listen(7504) ? (a / b) : (ListenerUtil.mutListener.listen(7503) ? (a * b) : (ListenerUtil.mutListener.listen(7502) ? (a - b) : (ListenerUtil.mutListener.listen(7501) ? (a + b) : (a % b))))) + b)))))) % b)))));
    }

    /**
     * Converts the specified heading angle into an index between 0-15 that can be used to retrieve
     * the direction name for that heading (known as "boxing the compass", down to the half-wind
     * level).
     *
     * @param heading the heading angle
     * @return the index of the direction name for the angle
     */
    public static int getHalfWindIndex(float heading) {
        return getHalfWindIndex(heading, NUMBER_OF_HALF_WINDS);
    }

    /**
     * Converts the specified heading angle into an index between 0 and numHalfWinds-1 that can be used to retrieve
     * the direction name for that heading (known as "boxing the compass", down to the half-wind
     * level).
     *
     * @param heading the heading angle
     * @param numHalfWinds number of half winds to divide the compass into
     * @return the index of the direction name for the angle
     */
    public static int getHalfWindIndex(float heading, int numHalfWinds) {
        float partitionSize = (ListenerUtil.mutListener.listen(7516) ? (360.0f % numHalfWinds) : (ListenerUtil.mutListener.listen(7515) ? (360.0f * numHalfWinds) : (ListenerUtil.mutListener.listen(7514) ? (360.0f - numHalfWinds) : (ListenerUtil.mutListener.listen(7513) ? (360.0f + numHalfWinds) : (360.0f / numHalfWinds)))));
        float displacedHeading = MathUtils.mod((ListenerUtil.mutListener.listen(7524) ? (heading % (ListenerUtil.mutListener.listen(7520) ? (partitionSize % 2) : (ListenerUtil.mutListener.listen(7519) ? (partitionSize * 2) : (ListenerUtil.mutListener.listen(7518) ? (partitionSize - 2) : (ListenerUtil.mutListener.listen(7517) ? (partitionSize + 2) : (partitionSize / 2)))))) : (ListenerUtil.mutListener.listen(7523) ? (heading / (ListenerUtil.mutListener.listen(7520) ? (partitionSize % 2) : (ListenerUtil.mutListener.listen(7519) ? (partitionSize * 2) : (ListenerUtil.mutListener.listen(7518) ? (partitionSize - 2) : (ListenerUtil.mutListener.listen(7517) ? (partitionSize + 2) : (partitionSize / 2)))))) : (ListenerUtil.mutListener.listen(7522) ? (heading * (ListenerUtil.mutListener.listen(7520) ? (partitionSize % 2) : (ListenerUtil.mutListener.listen(7519) ? (partitionSize * 2) : (ListenerUtil.mutListener.listen(7518) ? (partitionSize - 2) : (ListenerUtil.mutListener.listen(7517) ? (partitionSize + 2) : (partitionSize / 2)))))) : (ListenerUtil.mutListener.listen(7521) ? (heading - (ListenerUtil.mutListener.listen(7520) ? (partitionSize % 2) : (ListenerUtil.mutListener.listen(7519) ? (partitionSize * 2) : (ListenerUtil.mutListener.listen(7518) ? (partitionSize - 2) : (ListenerUtil.mutListener.listen(7517) ? (partitionSize + 2) : (partitionSize / 2)))))) : (heading + (ListenerUtil.mutListener.listen(7520) ? (partitionSize % 2) : (ListenerUtil.mutListener.listen(7519) ? (partitionSize * 2) : (ListenerUtil.mutListener.listen(7518) ? (partitionSize - 2) : (ListenerUtil.mutListener.listen(7517) ? (partitionSize + 2) : (partitionSize / 2)))))))))), 360.0f);
        return (int) ((ListenerUtil.mutListener.listen(7528) ? (displacedHeading % partitionSize) : (ListenerUtil.mutListener.listen(7527) ? (displacedHeading * partitionSize) : (ListenerUtil.mutListener.listen(7526) ? (displacedHeading - partitionSize) : (ListenerUtil.mutListener.listen(7525) ? (displacedHeading + partitionSize) : (displacedHeading / partitionSize))))));
    }

    /**
     * Converts from OBA orientation to direction.
     *
     * From OBA REST API docs for trip status (http://developer.onebusaway.org/modules/onebusaway-application-modules/current/api/where/elements/trip-status.html)
     * : "orientation - ...0º is east, 90º is north, 180º is west, and 270º is south."
     *
     * @param orientation 0º is east, 90º is north, 180º is west, and 270º is south
     * @return direction, where 0º is north, 90º is east, 180º is south, and 270º is west
     */
    public static double toDirection(double orientation) {
        double direction = (ListenerUtil.mutListener.listen(7536) ? (((ListenerUtil.mutListener.listen(7532) ? (-orientation % 90) : (ListenerUtil.mutListener.listen(7531) ? (-orientation / 90) : (ListenerUtil.mutListener.listen(7530) ? (-orientation * 90) : (ListenerUtil.mutListener.listen(7529) ? (-orientation - 90) : (-orientation + 90)))))) / 360) : (ListenerUtil.mutListener.listen(7535) ? (((ListenerUtil.mutListener.listen(7532) ? (-orientation % 90) : (ListenerUtil.mutListener.listen(7531) ? (-orientation / 90) : (ListenerUtil.mutListener.listen(7530) ? (-orientation * 90) : (ListenerUtil.mutListener.listen(7529) ? (-orientation - 90) : (-orientation + 90)))))) * 360) : (ListenerUtil.mutListener.listen(7534) ? (((ListenerUtil.mutListener.listen(7532) ? (-orientation % 90) : (ListenerUtil.mutListener.listen(7531) ? (-orientation / 90) : (ListenerUtil.mutListener.listen(7530) ? (-orientation * 90) : (ListenerUtil.mutListener.listen(7529) ? (-orientation - 90) : (-orientation + 90)))))) - 360) : (ListenerUtil.mutListener.listen(7533) ? (((ListenerUtil.mutListener.listen(7532) ? (-orientation % 90) : (ListenerUtil.mutListener.listen(7531) ? (-orientation / 90) : (ListenerUtil.mutListener.listen(7530) ? (-orientation * 90) : (ListenerUtil.mutListener.listen(7529) ? (-orientation - 90) : (-orientation + 90)))))) + 360) : (((ListenerUtil.mutListener.listen(7532) ? (-orientation % 90) : (ListenerUtil.mutListener.listen(7531) ? (-orientation / 90) : (ListenerUtil.mutListener.listen(7530) ? (-orientation * 90) : (ListenerUtil.mutListener.listen(7529) ? (-orientation - 90) : (-orientation + 90)))))) % 360)))));
        if (!ListenerUtil.mutListener.listen(7543)) {
            if ((ListenerUtil.mutListener.listen(7541) ? (direction >= 0) : (ListenerUtil.mutListener.listen(7540) ? (direction <= 0) : (ListenerUtil.mutListener.listen(7539) ? (direction > 0) : (ListenerUtil.mutListener.listen(7538) ? (direction != 0) : (ListenerUtil.mutListener.listen(7537) ? (direction == 0) : (direction < 0))))))) {
                if (!ListenerUtil.mutListener.listen(7542)) {
                    direction += 360;
                }
            }
        }
        return direction;
    }
}
