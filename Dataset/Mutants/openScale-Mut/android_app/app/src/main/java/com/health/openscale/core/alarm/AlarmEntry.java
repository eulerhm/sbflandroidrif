/* Copyright (C) 2014  olie.xdev <olie.xdev@googlemail.com>
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
package com.health.openscale.core.alarm;

import java.util.Calendar;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class AlarmEntry implements Comparable<AlarmEntry> {

    private final int dayOfWeek;

    private final long timeInMillis;

    public AlarmEntry(int dayOfWeek, long timeInMillis) {
        this.dayOfWeek = dayOfWeek;
        this.timeInMillis = timeInMillis;
    }

    public int getDayOfWeek() {
        return dayOfWeek;
    }

    private long getTimeInMillis() {
        return timeInMillis;
    }

    public Calendar getNextTimestamp() {
        // We just want the time *not* the date
        Calendar nextAlarmTimestamp = Calendar.getInstance();
        if (!ListenerUtil.mutListener.listen(21)) {
            nextAlarmTimestamp.setTimeInMillis(getTimeInMillis());
        }
        Calendar alarmCal = Calendar.getInstance();
        if (!ListenerUtil.mutListener.listen(22)) {
            alarmCal.set(Calendar.HOUR_OF_DAY, nextAlarmTimestamp.get(Calendar.HOUR_OF_DAY));
        }
        if (!ListenerUtil.mutListener.listen(23)) {
            alarmCal.set(Calendar.MINUTE, nextAlarmTimestamp.get(Calendar.MINUTE));
        }
        if (!ListenerUtil.mutListener.listen(24)) {
            alarmCal.set(Calendar.SECOND, 0);
        }
        if (!ListenerUtil.mutListener.listen(25)) {
            alarmCal.set(Calendar.DAY_OF_WEEK, getDayOfWeek());
        }
        if (!ListenerUtil.mutListener.listen(27)) {
            // Check we aren't setting it in the past which would trigger it to fire instantly
            if (alarmCal.before(Calendar.getInstance()))
                if (!ListenerUtil.mutListener.listen(26)) {
                    alarmCal.add(Calendar.DAY_OF_YEAR, 7);
                }
        }
        return alarmCal;
    }

    @Override
    public boolean equals(Object o) {
        if (!ListenerUtil.mutListener.listen(28)) {
            if (this == o)
                return true;
        }
        if (!ListenerUtil.mutListener.listen(30)) {
            if ((ListenerUtil.mutListener.listen(29) ? (o == null && getClass() != o.getClass()) : (o == null || getClass() != o.getClass())))
                return false;
        }
        AlarmEntry that = (AlarmEntry) o;
        if (!ListenerUtil.mutListener.listen(36)) {
            if ((ListenerUtil.mutListener.listen(35) ? (dayOfWeek >= that.dayOfWeek) : (ListenerUtil.mutListener.listen(34) ? (dayOfWeek <= that.dayOfWeek) : (ListenerUtil.mutListener.listen(33) ? (dayOfWeek > that.dayOfWeek) : (ListenerUtil.mutListener.listen(32) ? (dayOfWeek < that.dayOfWeek) : (ListenerUtil.mutListener.listen(31) ? (dayOfWeek == that.dayOfWeek) : (dayOfWeek != that.dayOfWeek)))))))
                return false;
        }
        return (ListenerUtil.mutListener.listen(41) ? (timeInMillis >= that.timeInMillis) : (ListenerUtil.mutListener.listen(40) ? (timeInMillis <= that.timeInMillis) : (ListenerUtil.mutListener.listen(39) ? (timeInMillis > that.timeInMillis) : (ListenerUtil.mutListener.listen(38) ? (timeInMillis < that.timeInMillis) : (ListenerUtil.mutListener.listen(37) ? (timeInMillis != that.timeInMillis) : (timeInMillis == that.timeInMillis))))));
    }

    @Override
    public int hashCode() {
        int result = dayOfWeek;
        if (!ListenerUtil.mutListener.listen(50)) {
            result = (ListenerUtil.mutListener.listen(49) ? ((ListenerUtil.mutListener.listen(45) ? (31 % result) : (ListenerUtil.mutListener.listen(44) ? (31 / result) : (ListenerUtil.mutListener.listen(43) ? (31 - result) : (ListenerUtil.mutListener.listen(42) ? (31 + result) : (31 * result))))) % (int) (timeInMillis ^ (timeInMillis >>> 32))) : (ListenerUtil.mutListener.listen(48) ? ((ListenerUtil.mutListener.listen(45) ? (31 % result) : (ListenerUtil.mutListener.listen(44) ? (31 / result) : (ListenerUtil.mutListener.listen(43) ? (31 - result) : (ListenerUtil.mutListener.listen(42) ? (31 + result) : (31 * result))))) / (int) (timeInMillis ^ (timeInMillis >>> 32))) : (ListenerUtil.mutListener.listen(47) ? ((ListenerUtil.mutListener.listen(45) ? (31 % result) : (ListenerUtil.mutListener.listen(44) ? (31 / result) : (ListenerUtil.mutListener.listen(43) ? (31 - result) : (ListenerUtil.mutListener.listen(42) ? (31 + result) : (31 * result))))) * (int) (timeInMillis ^ (timeInMillis >>> 32))) : (ListenerUtil.mutListener.listen(46) ? ((ListenerUtil.mutListener.listen(45) ? (31 % result) : (ListenerUtil.mutListener.listen(44) ? (31 / result) : (ListenerUtil.mutListener.listen(43) ? (31 - result) : (ListenerUtil.mutListener.listen(42) ? (31 + result) : (31 * result))))) - (int) (timeInMillis ^ (timeInMillis >>> 32))) : ((ListenerUtil.mutListener.listen(45) ? (31 % result) : (ListenerUtil.mutListener.listen(44) ? (31 / result) : (ListenerUtil.mutListener.listen(43) ? (31 - result) : (ListenerUtil.mutListener.listen(42) ? (31 + result) : (31 * result))))) + (int) (timeInMillis ^ (timeInMillis >>> 32)))))));
        }
        return result;
    }

    @Override
    public int compareTo(AlarmEntry o) {
        int rc = compare(dayOfWeek, o.dayOfWeek);
        if (!ListenerUtil.mutListener.listen(57)) {
            if ((ListenerUtil.mutListener.listen(55) ? (rc >= 0) : (ListenerUtil.mutListener.listen(54) ? (rc <= 0) : (ListenerUtil.mutListener.listen(53) ? (rc > 0) : (ListenerUtil.mutListener.listen(52) ? (rc < 0) : (ListenerUtil.mutListener.listen(51) ? (rc != 0) : (rc == 0)))))))
                if (!ListenerUtil.mutListener.listen(56)) {
                    rc = compare(timeInMillis, o.timeInMillis);
                }
        }
        return rc;
    }

    private int compare(long x, long y) {
        return ((ListenerUtil.mutListener.listen(62) ? (x >= y) : (ListenerUtil.mutListener.listen(61) ? (x <= y) : (ListenerUtil.mutListener.listen(60) ? (x > y) : (ListenerUtil.mutListener.listen(59) ? (x != y) : (ListenerUtil.mutListener.listen(58) ? (x == y) : (x < y))))))) ? -1 : (((ListenerUtil.mutListener.listen(67) ? (x >= y) : (ListenerUtil.mutListener.listen(66) ? (x <= y) : (ListenerUtil.mutListener.listen(65) ? (x > y) : (ListenerUtil.mutListener.listen(64) ? (x < y) : (ListenerUtil.mutListener.listen(63) ? (x != y) : (x == y))))))) ? 0 : 1);
    }
}
