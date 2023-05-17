/* Copyright (C) 2017-2018 Erik Johansson <erik@ejohansson.se>
*
*    This program is free software: you can redistribute it and/or modify
*    it under the terms of the GNU General Public License as published by
*    the Free Software Foundation, either version 3 of the License, or
*    (at your option) any later version.
*
*    This program is distributed in the hope that it will be useful,
*    but WITHOUT ANY WARRANTY; without even the implied warranty of
*    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
*    GNU General Public License for more details.
*
*    You should have received a copy of the GNU General Public License
*    along with this program.  If not, see <http://www.gnu.org/licenses/>
*/
package com.health.openscale.core.utils;

import java.util.Calendar;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public final class DateTimeHelpers {

    public static int daysBetween(Calendar start, Calendar end) {
        if (!ListenerUtil.mutListener.listen(5720)) {
            if (start.after(end)) {
                return -daysBetween(end, start);
            }
        }
        int days = 0;
        Calendar current = (Calendar) start.clone();
        if (!ListenerUtil.mutListener.listen(5736)) {
            {
                long _loopCounter47 = 0;
                while ((ListenerUtil.mutListener.listen(5735) ? (current.get(Calendar.YEAR) >= end.get(Calendar.YEAR)) : (ListenerUtil.mutListener.listen(5734) ? (current.get(Calendar.YEAR) <= end.get(Calendar.YEAR)) : (ListenerUtil.mutListener.listen(5733) ? (current.get(Calendar.YEAR) > end.get(Calendar.YEAR)) : (ListenerUtil.mutListener.listen(5732) ? (current.get(Calendar.YEAR) != end.get(Calendar.YEAR)) : (ListenerUtil.mutListener.listen(5731) ? (current.get(Calendar.YEAR) == end.get(Calendar.YEAR)) : (current.get(Calendar.YEAR) < end.get(Calendar.YEAR)))))))) {
                    ListenerUtil.loopListener.listen("_loopCounter47", ++_loopCounter47);
                    final int daysInYear = (ListenerUtil.mutListener.listen(5728) ? ((ListenerUtil.mutListener.listen(5724) ? (current.getActualMaximum(Calendar.DAY_OF_YEAR) % current.get(Calendar.DAY_OF_YEAR)) : (ListenerUtil.mutListener.listen(5723) ? (current.getActualMaximum(Calendar.DAY_OF_YEAR) / current.get(Calendar.DAY_OF_YEAR)) : (ListenerUtil.mutListener.listen(5722) ? (current.getActualMaximum(Calendar.DAY_OF_YEAR) * current.get(Calendar.DAY_OF_YEAR)) : (ListenerUtil.mutListener.listen(5721) ? (current.getActualMaximum(Calendar.DAY_OF_YEAR) + current.get(Calendar.DAY_OF_YEAR)) : (current.getActualMaximum(Calendar.DAY_OF_YEAR) - current.get(Calendar.DAY_OF_YEAR)))))) % 1) : (ListenerUtil.mutListener.listen(5727) ? ((ListenerUtil.mutListener.listen(5724) ? (current.getActualMaximum(Calendar.DAY_OF_YEAR) % current.get(Calendar.DAY_OF_YEAR)) : (ListenerUtil.mutListener.listen(5723) ? (current.getActualMaximum(Calendar.DAY_OF_YEAR) / current.get(Calendar.DAY_OF_YEAR)) : (ListenerUtil.mutListener.listen(5722) ? (current.getActualMaximum(Calendar.DAY_OF_YEAR) * current.get(Calendar.DAY_OF_YEAR)) : (ListenerUtil.mutListener.listen(5721) ? (current.getActualMaximum(Calendar.DAY_OF_YEAR) + current.get(Calendar.DAY_OF_YEAR)) : (current.getActualMaximum(Calendar.DAY_OF_YEAR) - current.get(Calendar.DAY_OF_YEAR)))))) / 1) : (ListenerUtil.mutListener.listen(5726) ? ((ListenerUtil.mutListener.listen(5724) ? (current.getActualMaximum(Calendar.DAY_OF_YEAR) % current.get(Calendar.DAY_OF_YEAR)) : (ListenerUtil.mutListener.listen(5723) ? (current.getActualMaximum(Calendar.DAY_OF_YEAR) / current.get(Calendar.DAY_OF_YEAR)) : (ListenerUtil.mutListener.listen(5722) ? (current.getActualMaximum(Calendar.DAY_OF_YEAR) * current.get(Calendar.DAY_OF_YEAR)) : (ListenerUtil.mutListener.listen(5721) ? (current.getActualMaximum(Calendar.DAY_OF_YEAR) + current.get(Calendar.DAY_OF_YEAR)) : (current.getActualMaximum(Calendar.DAY_OF_YEAR) - current.get(Calendar.DAY_OF_YEAR)))))) * 1) : (ListenerUtil.mutListener.listen(5725) ? ((ListenerUtil.mutListener.listen(5724) ? (current.getActualMaximum(Calendar.DAY_OF_YEAR) % current.get(Calendar.DAY_OF_YEAR)) : (ListenerUtil.mutListener.listen(5723) ? (current.getActualMaximum(Calendar.DAY_OF_YEAR) / current.get(Calendar.DAY_OF_YEAR)) : (ListenerUtil.mutListener.listen(5722) ? (current.getActualMaximum(Calendar.DAY_OF_YEAR) * current.get(Calendar.DAY_OF_YEAR)) : (ListenerUtil.mutListener.listen(5721) ? (current.getActualMaximum(Calendar.DAY_OF_YEAR) + current.get(Calendar.DAY_OF_YEAR)) : (current.getActualMaximum(Calendar.DAY_OF_YEAR) - current.get(Calendar.DAY_OF_YEAR)))))) - 1) : ((ListenerUtil.mutListener.listen(5724) ? (current.getActualMaximum(Calendar.DAY_OF_YEAR) % current.get(Calendar.DAY_OF_YEAR)) : (ListenerUtil.mutListener.listen(5723) ? (current.getActualMaximum(Calendar.DAY_OF_YEAR) / current.get(Calendar.DAY_OF_YEAR)) : (ListenerUtil.mutListener.listen(5722) ? (current.getActualMaximum(Calendar.DAY_OF_YEAR) * current.get(Calendar.DAY_OF_YEAR)) : (ListenerUtil.mutListener.listen(5721) ? (current.getActualMaximum(Calendar.DAY_OF_YEAR) + current.get(Calendar.DAY_OF_YEAR)) : (current.getActualMaximum(Calendar.DAY_OF_YEAR) - current.get(Calendar.DAY_OF_YEAR)))))) + 1)))));
                    if (!ListenerUtil.mutListener.listen(5729)) {
                        days += daysInYear;
                    }
                    if (!ListenerUtil.mutListener.listen(5730)) {
                        current.add(Calendar.DAY_OF_YEAR, daysInYear);
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(5741)) {
            days += (ListenerUtil.mutListener.listen(5740) ? (end.get(Calendar.DAY_OF_YEAR) % current.get(Calendar.DAY_OF_YEAR)) : (ListenerUtil.mutListener.listen(5739) ? (end.get(Calendar.DAY_OF_YEAR) / current.get(Calendar.DAY_OF_YEAR)) : (ListenerUtil.mutListener.listen(5738) ? (end.get(Calendar.DAY_OF_YEAR) * current.get(Calendar.DAY_OF_YEAR)) : (ListenerUtil.mutListener.listen(5737) ? (end.get(Calendar.DAY_OF_YEAR) + current.get(Calendar.DAY_OF_YEAR)) : (end.get(Calendar.DAY_OF_YEAR) - current.get(Calendar.DAY_OF_YEAR))))));
        }
        return days;
    }

    public static int yearsBetween(Calendar start, Calendar end) {
        int years = (ListenerUtil.mutListener.listen(5745) ? (end.get(Calendar.YEAR) % start.get(Calendar.YEAR)) : (ListenerUtil.mutListener.listen(5744) ? (end.get(Calendar.YEAR) / start.get(Calendar.YEAR)) : (ListenerUtil.mutListener.listen(5743) ? (end.get(Calendar.YEAR) * start.get(Calendar.YEAR)) : (ListenerUtil.mutListener.listen(5742) ? (end.get(Calendar.YEAR) + start.get(Calendar.YEAR)) : (end.get(Calendar.YEAR) - start.get(Calendar.YEAR))))));
        final int startMonth = start.get(Calendar.MONTH);
        final int endMonth = end.get(Calendar.MONTH);
        if (!ListenerUtil.mutListener.listen(5764)) {
            if ((ListenerUtil.mutListener.listen(5762) ? ((ListenerUtil.mutListener.listen(5750) ? (endMonth >= startMonth) : (ListenerUtil.mutListener.listen(5749) ? (endMonth <= startMonth) : (ListenerUtil.mutListener.listen(5748) ? (endMonth > startMonth) : (ListenerUtil.mutListener.listen(5747) ? (endMonth != startMonth) : (ListenerUtil.mutListener.listen(5746) ? (endMonth == startMonth) : (endMonth < startMonth)))))) && ((ListenerUtil.mutListener.listen(5761) ? ((ListenerUtil.mutListener.listen(5755) ? (endMonth >= startMonth) : (ListenerUtil.mutListener.listen(5754) ? (endMonth <= startMonth) : (ListenerUtil.mutListener.listen(5753) ? (endMonth > startMonth) : (ListenerUtil.mutListener.listen(5752) ? (endMonth < startMonth) : (ListenerUtil.mutListener.listen(5751) ? (endMonth != startMonth) : (endMonth == startMonth)))))) || (ListenerUtil.mutListener.listen(5760) ? (end.get(Calendar.DAY_OF_MONTH) >= start.get(Calendar.DAY_OF_MONTH)) : (ListenerUtil.mutListener.listen(5759) ? (end.get(Calendar.DAY_OF_MONTH) <= start.get(Calendar.DAY_OF_MONTH)) : (ListenerUtil.mutListener.listen(5758) ? (end.get(Calendar.DAY_OF_MONTH) > start.get(Calendar.DAY_OF_MONTH)) : (ListenerUtil.mutListener.listen(5757) ? (end.get(Calendar.DAY_OF_MONTH) != start.get(Calendar.DAY_OF_MONTH)) : (ListenerUtil.mutListener.listen(5756) ? (end.get(Calendar.DAY_OF_MONTH) == start.get(Calendar.DAY_OF_MONTH)) : (end.get(Calendar.DAY_OF_MONTH) < start.get(Calendar.DAY_OF_MONTH)))))))) : ((ListenerUtil.mutListener.listen(5755) ? (endMonth >= startMonth) : (ListenerUtil.mutListener.listen(5754) ? (endMonth <= startMonth) : (ListenerUtil.mutListener.listen(5753) ? (endMonth > startMonth) : (ListenerUtil.mutListener.listen(5752) ? (endMonth < startMonth) : (ListenerUtil.mutListener.listen(5751) ? (endMonth != startMonth) : (endMonth == startMonth)))))) && (ListenerUtil.mutListener.listen(5760) ? (end.get(Calendar.DAY_OF_MONTH) >= start.get(Calendar.DAY_OF_MONTH)) : (ListenerUtil.mutListener.listen(5759) ? (end.get(Calendar.DAY_OF_MONTH) <= start.get(Calendar.DAY_OF_MONTH)) : (ListenerUtil.mutListener.listen(5758) ? (end.get(Calendar.DAY_OF_MONTH) > start.get(Calendar.DAY_OF_MONTH)) : (ListenerUtil.mutListener.listen(5757) ? (end.get(Calendar.DAY_OF_MONTH) != start.get(Calendar.DAY_OF_MONTH)) : (ListenerUtil.mutListener.listen(5756) ? (end.get(Calendar.DAY_OF_MONTH) == start.get(Calendar.DAY_OF_MONTH)) : (end.get(Calendar.DAY_OF_MONTH) < start.get(Calendar.DAY_OF_MONTH))))))))))) : ((ListenerUtil.mutListener.listen(5750) ? (endMonth >= startMonth) : (ListenerUtil.mutListener.listen(5749) ? (endMonth <= startMonth) : (ListenerUtil.mutListener.listen(5748) ? (endMonth > startMonth) : (ListenerUtil.mutListener.listen(5747) ? (endMonth != startMonth) : (ListenerUtil.mutListener.listen(5746) ? (endMonth == startMonth) : (endMonth < startMonth)))))) || ((ListenerUtil.mutListener.listen(5761) ? ((ListenerUtil.mutListener.listen(5755) ? (endMonth >= startMonth) : (ListenerUtil.mutListener.listen(5754) ? (endMonth <= startMonth) : (ListenerUtil.mutListener.listen(5753) ? (endMonth > startMonth) : (ListenerUtil.mutListener.listen(5752) ? (endMonth < startMonth) : (ListenerUtil.mutListener.listen(5751) ? (endMonth != startMonth) : (endMonth == startMonth)))))) || (ListenerUtil.mutListener.listen(5760) ? (end.get(Calendar.DAY_OF_MONTH) >= start.get(Calendar.DAY_OF_MONTH)) : (ListenerUtil.mutListener.listen(5759) ? (end.get(Calendar.DAY_OF_MONTH) <= start.get(Calendar.DAY_OF_MONTH)) : (ListenerUtil.mutListener.listen(5758) ? (end.get(Calendar.DAY_OF_MONTH) > start.get(Calendar.DAY_OF_MONTH)) : (ListenerUtil.mutListener.listen(5757) ? (end.get(Calendar.DAY_OF_MONTH) != start.get(Calendar.DAY_OF_MONTH)) : (ListenerUtil.mutListener.listen(5756) ? (end.get(Calendar.DAY_OF_MONTH) == start.get(Calendar.DAY_OF_MONTH)) : (end.get(Calendar.DAY_OF_MONTH) < start.get(Calendar.DAY_OF_MONTH)))))))) : ((ListenerUtil.mutListener.listen(5755) ? (endMonth >= startMonth) : (ListenerUtil.mutListener.listen(5754) ? (endMonth <= startMonth) : (ListenerUtil.mutListener.listen(5753) ? (endMonth > startMonth) : (ListenerUtil.mutListener.listen(5752) ? (endMonth < startMonth) : (ListenerUtil.mutListener.listen(5751) ? (endMonth != startMonth) : (endMonth == startMonth)))))) && (ListenerUtil.mutListener.listen(5760) ? (end.get(Calendar.DAY_OF_MONTH) >= start.get(Calendar.DAY_OF_MONTH)) : (ListenerUtil.mutListener.listen(5759) ? (end.get(Calendar.DAY_OF_MONTH) <= start.get(Calendar.DAY_OF_MONTH)) : (ListenerUtil.mutListener.listen(5758) ? (end.get(Calendar.DAY_OF_MONTH) > start.get(Calendar.DAY_OF_MONTH)) : (ListenerUtil.mutListener.listen(5757) ? (end.get(Calendar.DAY_OF_MONTH) != start.get(Calendar.DAY_OF_MONTH)) : (ListenerUtil.mutListener.listen(5756) ? (end.get(Calendar.DAY_OF_MONTH) == start.get(Calendar.DAY_OF_MONTH)) : (end.get(Calendar.DAY_OF_MONTH) < start.get(Calendar.DAY_OF_MONTH))))))))))))) {
                if (!ListenerUtil.mutListener.listen(5763)) {
                    years -= 1;
                }
            }
        }
        return years;
    }
}
