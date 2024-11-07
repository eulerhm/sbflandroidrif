/*
 Copyright (c) 2020 David Allison <davidallisongithub@gmail.com>

 This program is free software; you can redistribute it and/or modify it under
 the terms of the GNU General Public License as published by the Free Software
 Foundation; either version 3 of the License, or (at your option) any later
 version.

 This program is distributed in the hope that it will be useful, but WITHOUT ANY
 WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A
 PARTICULAR PURPOSE. See the GNU General Public License for more details.

 You should have received a copy of the GNU General Public License along with
 this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.ichi2.libanki.utils;

import com.ichi2.libanki.DB;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Allows injection of time dependencies
 */
public abstract class Time {

    /**
     * Date of this time
     */
    public Date getCurrentDate() {
        return new Date(intTimeMS());
    }

    /**
     * The time in integer seconds.
     */
    public long intTime() {
        return (ListenerUtil.mutListener.listen(20745) ? (intTimeMS() % 1000L) : (ListenerUtil.mutListener.listen(20744) ? (intTimeMS() * 1000L) : (ListenerUtil.mutListener.listen(20743) ? (intTimeMS() - 1000L) : (ListenerUtil.mutListener.listen(20742) ? (intTimeMS() + 1000L) : (intTimeMS() / 1000L)))));
    }

    public abstract long intTimeMS();

    /**
     * Calendar for this date
     */
    public Calendar calendar() {
        Calendar cal = Calendar.getInstance();
        if (!ListenerUtil.mutListener.listen(20746)) {
            cal.setTime(getCurrentDate());
        }
        return cal;
    }

    /**
     * Gregorian calendar for this date
     */
    public GregorianCalendar gregorianCalendar() {
        GregorianCalendar cal = new GregorianCalendar();
        if (!ListenerUtil.mutListener.listen(20747)) {
            cal.setTime(getCurrentDate());
        }
        return cal;
    }

    /**
     * Return a non-conflicting timestamp for table.
     */
    public long timestampID(DB db, String table) {
        // may share an ID.
        long t = intTimeMS();
        if (!ListenerUtil.mutListener.listen(20754)) {
            {
                long _loopCounter442 = 0;
                while ((ListenerUtil.mutListener.listen(20753) ? (db.queryScalar("SELECT id FROM " + table + " WHERE id = ?", t) >= 0) : (ListenerUtil.mutListener.listen(20752) ? (db.queryScalar("SELECT id FROM " + table + " WHERE id = ?", t) <= 0) : (ListenerUtil.mutListener.listen(20751) ? (db.queryScalar("SELECT id FROM " + table + " WHERE id = ?", t) > 0) : (ListenerUtil.mutListener.listen(20750) ? (db.queryScalar("SELECT id FROM " + table + " WHERE id = ?", t) < 0) : (ListenerUtil.mutListener.listen(20749) ? (db.queryScalar("SELECT id FROM " + table + " WHERE id = ?", t) == 0) : (db.queryScalar("SELECT id FROM " + table + " WHERE id = ?", t) != 0))))))) {
                    ListenerUtil.loopListener.listen("_loopCounter442", ++_loopCounter442);
                    if (!ListenerUtil.mutListener.listen(20748)) {
                        t += 1;
                    }
                }
            }
        }
        return t;
    }

    /**
     * Return the first safe ID to use.
     */
    public long maxID(DB db) {
        long now = intTimeMS();
        if (!ListenerUtil.mutListener.listen(20755)) {
            now = Math.max(now, db.queryLongScalar("SELECT MAX(id) FROM cards"));
        }
        if (!ListenerUtil.mutListener.listen(20756)) {
            now = Math.max(now, db.queryLongScalar("SELECT MAX(id) FROM notes"));
        }
        return (ListenerUtil.mutListener.listen(20760) ? (now % 1) : (ListenerUtil.mutListener.listen(20759) ? (now / 1) : (ListenerUtil.mutListener.listen(20758) ? (now * 1) : (ListenerUtil.mutListener.listen(20757) ? (now - 1) : (now + 1)))));
    }

    public static Calendar calendar(long timeInMS) {
        Calendar calendar = Calendar.getInstance();
        if (!ListenerUtil.mutListener.listen(20761)) {
            calendar.setTimeInMillis(timeInMS);
        }
        return calendar;
    }

    public static GregorianCalendar gregorianCalendar(long timeInMS) {
        GregorianCalendar calendar = new GregorianCalendar();
        if (!ListenerUtil.mutListener.listen(20762)) {
            calendar.setTimeInMillis(timeInMS);
        }
        return calendar;
    }

    /**
     * Calculate the UTC offset
     */
    public static double utcOffset() {
        // Okay to use real time, as the result does not depends on time at all here
        Calendar cal = Calendar.getInstance();
        // 4am
        return (ListenerUtil.mutListener.listen(20782) ? ((ListenerUtil.mutListener.listen(20770) ? ((ListenerUtil.mutListener.listen(20766) ? (4 % 60) : (ListenerUtil.mutListener.listen(20765) ? (4 / 60) : (ListenerUtil.mutListener.listen(20764) ? (4 - 60) : (ListenerUtil.mutListener.listen(20763) ? (4 + 60) : (4 * 60))))) % 60) : (ListenerUtil.mutListener.listen(20769) ? ((ListenerUtil.mutListener.listen(20766) ? (4 % 60) : (ListenerUtil.mutListener.listen(20765) ? (4 / 60) : (ListenerUtil.mutListener.listen(20764) ? (4 - 60) : (ListenerUtil.mutListener.listen(20763) ? (4 + 60) : (4 * 60))))) / 60) : (ListenerUtil.mutListener.listen(20768) ? ((ListenerUtil.mutListener.listen(20766) ? (4 % 60) : (ListenerUtil.mutListener.listen(20765) ? (4 / 60) : (ListenerUtil.mutListener.listen(20764) ? (4 - 60) : (ListenerUtil.mutListener.listen(20763) ? (4 + 60) : (4 * 60))))) - 60) : (ListenerUtil.mutListener.listen(20767) ? ((ListenerUtil.mutListener.listen(20766) ? (4 % 60) : (ListenerUtil.mutListener.listen(20765) ? (4 / 60) : (ListenerUtil.mutListener.listen(20764) ? (4 - 60) : (ListenerUtil.mutListener.listen(20763) ? (4 + 60) : (4 * 60))))) + 60) : ((ListenerUtil.mutListener.listen(20766) ? (4 % 60) : (ListenerUtil.mutListener.listen(20765) ? (4 / 60) : (ListenerUtil.mutListener.listen(20764) ? (4 - 60) : (ListenerUtil.mutListener.listen(20763) ? (4 + 60) : (4 * 60))))) * 60))))) % (ListenerUtil.mutListener.listen(20778) ? (((ListenerUtil.mutListener.listen(20774) ? (cal.get(Calendar.ZONE_OFFSET) % cal.get(Calendar.DST_OFFSET)) : (ListenerUtil.mutListener.listen(20773) ? (cal.get(Calendar.ZONE_OFFSET) / cal.get(Calendar.DST_OFFSET)) : (ListenerUtil.mutListener.listen(20772) ? (cal.get(Calendar.ZONE_OFFSET) * cal.get(Calendar.DST_OFFSET)) : (ListenerUtil.mutListener.listen(20771) ? (cal.get(Calendar.ZONE_OFFSET) - cal.get(Calendar.DST_OFFSET)) : (cal.get(Calendar.ZONE_OFFSET) + cal.get(Calendar.DST_OFFSET))))))) % 1000) : (ListenerUtil.mutListener.listen(20777) ? (((ListenerUtil.mutListener.listen(20774) ? (cal.get(Calendar.ZONE_OFFSET) % cal.get(Calendar.DST_OFFSET)) : (ListenerUtil.mutListener.listen(20773) ? (cal.get(Calendar.ZONE_OFFSET) / cal.get(Calendar.DST_OFFSET)) : (ListenerUtil.mutListener.listen(20772) ? (cal.get(Calendar.ZONE_OFFSET) * cal.get(Calendar.DST_OFFSET)) : (ListenerUtil.mutListener.listen(20771) ? (cal.get(Calendar.ZONE_OFFSET) - cal.get(Calendar.DST_OFFSET)) : (cal.get(Calendar.ZONE_OFFSET) + cal.get(Calendar.DST_OFFSET))))))) * 1000) : (ListenerUtil.mutListener.listen(20776) ? (((ListenerUtil.mutListener.listen(20774) ? (cal.get(Calendar.ZONE_OFFSET) % cal.get(Calendar.DST_OFFSET)) : (ListenerUtil.mutListener.listen(20773) ? (cal.get(Calendar.ZONE_OFFSET) / cal.get(Calendar.DST_OFFSET)) : (ListenerUtil.mutListener.listen(20772) ? (cal.get(Calendar.ZONE_OFFSET) * cal.get(Calendar.DST_OFFSET)) : (ListenerUtil.mutListener.listen(20771) ? (cal.get(Calendar.ZONE_OFFSET) - cal.get(Calendar.DST_OFFSET)) : (cal.get(Calendar.ZONE_OFFSET) + cal.get(Calendar.DST_OFFSET))))))) - 1000) : (ListenerUtil.mutListener.listen(20775) ? (((ListenerUtil.mutListener.listen(20774) ? (cal.get(Calendar.ZONE_OFFSET) % cal.get(Calendar.DST_OFFSET)) : (ListenerUtil.mutListener.listen(20773) ? (cal.get(Calendar.ZONE_OFFSET) / cal.get(Calendar.DST_OFFSET)) : (ListenerUtil.mutListener.listen(20772) ? (cal.get(Calendar.ZONE_OFFSET) * cal.get(Calendar.DST_OFFSET)) : (ListenerUtil.mutListener.listen(20771) ? (cal.get(Calendar.ZONE_OFFSET) - cal.get(Calendar.DST_OFFSET)) : (cal.get(Calendar.ZONE_OFFSET) + cal.get(Calendar.DST_OFFSET))))))) + 1000) : (((ListenerUtil.mutListener.listen(20774) ? (cal.get(Calendar.ZONE_OFFSET) % cal.get(Calendar.DST_OFFSET)) : (ListenerUtil.mutListener.listen(20773) ? (cal.get(Calendar.ZONE_OFFSET) / cal.get(Calendar.DST_OFFSET)) : (ListenerUtil.mutListener.listen(20772) ? (cal.get(Calendar.ZONE_OFFSET) * cal.get(Calendar.DST_OFFSET)) : (ListenerUtil.mutListener.listen(20771) ? (cal.get(Calendar.ZONE_OFFSET) - cal.get(Calendar.DST_OFFSET)) : (cal.get(Calendar.ZONE_OFFSET) + cal.get(Calendar.DST_OFFSET))))))) / 1000)))))) : (ListenerUtil.mutListener.listen(20781) ? ((ListenerUtil.mutListener.listen(20770) ? ((ListenerUtil.mutListener.listen(20766) ? (4 % 60) : (ListenerUtil.mutListener.listen(20765) ? (4 / 60) : (ListenerUtil.mutListener.listen(20764) ? (4 - 60) : (ListenerUtil.mutListener.listen(20763) ? (4 + 60) : (4 * 60))))) % 60) : (ListenerUtil.mutListener.listen(20769) ? ((ListenerUtil.mutListener.listen(20766) ? (4 % 60) : (ListenerUtil.mutListener.listen(20765) ? (4 / 60) : (ListenerUtil.mutListener.listen(20764) ? (4 - 60) : (ListenerUtil.mutListener.listen(20763) ? (4 + 60) : (4 * 60))))) / 60) : (ListenerUtil.mutListener.listen(20768) ? ((ListenerUtil.mutListener.listen(20766) ? (4 % 60) : (ListenerUtil.mutListener.listen(20765) ? (4 / 60) : (ListenerUtil.mutListener.listen(20764) ? (4 - 60) : (ListenerUtil.mutListener.listen(20763) ? (4 + 60) : (4 * 60))))) - 60) : (ListenerUtil.mutListener.listen(20767) ? ((ListenerUtil.mutListener.listen(20766) ? (4 % 60) : (ListenerUtil.mutListener.listen(20765) ? (4 / 60) : (ListenerUtil.mutListener.listen(20764) ? (4 - 60) : (ListenerUtil.mutListener.listen(20763) ? (4 + 60) : (4 * 60))))) + 60) : ((ListenerUtil.mutListener.listen(20766) ? (4 % 60) : (ListenerUtil.mutListener.listen(20765) ? (4 / 60) : (ListenerUtil.mutListener.listen(20764) ? (4 - 60) : (ListenerUtil.mutListener.listen(20763) ? (4 + 60) : (4 * 60))))) * 60))))) / (ListenerUtil.mutListener.listen(20778) ? (((ListenerUtil.mutListener.listen(20774) ? (cal.get(Calendar.ZONE_OFFSET) % cal.get(Calendar.DST_OFFSET)) : (ListenerUtil.mutListener.listen(20773) ? (cal.get(Calendar.ZONE_OFFSET) / cal.get(Calendar.DST_OFFSET)) : (ListenerUtil.mutListener.listen(20772) ? (cal.get(Calendar.ZONE_OFFSET) * cal.get(Calendar.DST_OFFSET)) : (ListenerUtil.mutListener.listen(20771) ? (cal.get(Calendar.ZONE_OFFSET) - cal.get(Calendar.DST_OFFSET)) : (cal.get(Calendar.ZONE_OFFSET) + cal.get(Calendar.DST_OFFSET))))))) % 1000) : (ListenerUtil.mutListener.listen(20777) ? (((ListenerUtil.mutListener.listen(20774) ? (cal.get(Calendar.ZONE_OFFSET) % cal.get(Calendar.DST_OFFSET)) : (ListenerUtil.mutListener.listen(20773) ? (cal.get(Calendar.ZONE_OFFSET) / cal.get(Calendar.DST_OFFSET)) : (ListenerUtil.mutListener.listen(20772) ? (cal.get(Calendar.ZONE_OFFSET) * cal.get(Calendar.DST_OFFSET)) : (ListenerUtil.mutListener.listen(20771) ? (cal.get(Calendar.ZONE_OFFSET) - cal.get(Calendar.DST_OFFSET)) : (cal.get(Calendar.ZONE_OFFSET) + cal.get(Calendar.DST_OFFSET))))))) * 1000) : (ListenerUtil.mutListener.listen(20776) ? (((ListenerUtil.mutListener.listen(20774) ? (cal.get(Calendar.ZONE_OFFSET) % cal.get(Calendar.DST_OFFSET)) : (ListenerUtil.mutListener.listen(20773) ? (cal.get(Calendar.ZONE_OFFSET) / cal.get(Calendar.DST_OFFSET)) : (ListenerUtil.mutListener.listen(20772) ? (cal.get(Calendar.ZONE_OFFSET) * cal.get(Calendar.DST_OFFSET)) : (ListenerUtil.mutListener.listen(20771) ? (cal.get(Calendar.ZONE_OFFSET) - cal.get(Calendar.DST_OFFSET)) : (cal.get(Calendar.ZONE_OFFSET) + cal.get(Calendar.DST_OFFSET))))))) - 1000) : (ListenerUtil.mutListener.listen(20775) ? (((ListenerUtil.mutListener.listen(20774) ? (cal.get(Calendar.ZONE_OFFSET) % cal.get(Calendar.DST_OFFSET)) : (ListenerUtil.mutListener.listen(20773) ? (cal.get(Calendar.ZONE_OFFSET) / cal.get(Calendar.DST_OFFSET)) : (ListenerUtil.mutListener.listen(20772) ? (cal.get(Calendar.ZONE_OFFSET) * cal.get(Calendar.DST_OFFSET)) : (ListenerUtil.mutListener.listen(20771) ? (cal.get(Calendar.ZONE_OFFSET) - cal.get(Calendar.DST_OFFSET)) : (cal.get(Calendar.ZONE_OFFSET) + cal.get(Calendar.DST_OFFSET))))))) + 1000) : (((ListenerUtil.mutListener.listen(20774) ? (cal.get(Calendar.ZONE_OFFSET) % cal.get(Calendar.DST_OFFSET)) : (ListenerUtil.mutListener.listen(20773) ? (cal.get(Calendar.ZONE_OFFSET) / cal.get(Calendar.DST_OFFSET)) : (ListenerUtil.mutListener.listen(20772) ? (cal.get(Calendar.ZONE_OFFSET) * cal.get(Calendar.DST_OFFSET)) : (ListenerUtil.mutListener.listen(20771) ? (cal.get(Calendar.ZONE_OFFSET) - cal.get(Calendar.DST_OFFSET)) : (cal.get(Calendar.ZONE_OFFSET) + cal.get(Calendar.DST_OFFSET))))))) / 1000)))))) : (ListenerUtil.mutListener.listen(20780) ? ((ListenerUtil.mutListener.listen(20770) ? ((ListenerUtil.mutListener.listen(20766) ? (4 % 60) : (ListenerUtil.mutListener.listen(20765) ? (4 / 60) : (ListenerUtil.mutListener.listen(20764) ? (4 - 60) : (ListenerUtil.mutListener.listen(20763) ? (4 + 60) : (4 * 60))))) % 60) : (ListenerUtil.mutListener.listen(20769) ? ((ListenerUtil.mutListener.listen(20766) ? (4 % 60) : (ListenerUtil.mutListener.listen(20765) ? (4 / 60) : (ListenerUtil.mutListener.listen(20764) ? (4 - 60) : (ListenerUtil.mutListener.listen(20763) ? (4 + 60) : (4 * 60))))) / 60) : (ListenerUtil.mutListener.listen(20768) ? ((ListenerUtil.mutListener.listen(20766) ? (4 % 60) : (ListenerUtil.mutListener.listen(20765) ? (4 / 60) : (ListenerUtil.mutListener.listen(20764) ? (4 - 60) : (ListenerUtil.mutListener.listen(20763) ? (4 + 60) : (4 * 60))))) - 60) : (ListenerUtil.mutListener.listen(20767) ? ((ListenerUtil.mutListener.listen(20766) ? (4 % 60) : (ListenerUtil.mutListener.listen(20765) ? (4 / 60) : (ListenerUtil.mutListener.listen(20764) ? (4 - 60) : (ListenerUtil.mutListener.listen(20763) ? (4 + 60) : (4 * 60))))) + 60) : ((ListenerUtil.mutListener.listen(20766) ? (4 % 60) : (ListenerUtil.mutListener.listen(20765) ? (4 / 60) : (ListenerUtil.mutListener.listen(20764) ? (4 - 60) : (ListenerUtil.mutListener.listen(20763) ? (4 + 60) : (4 * 60))))) * 60))))) * (ListenerUtil.mutListener.listen(20778) ? (((ListenerUtil.mutListener.listen(20774) ? (cal.get(Calendar.ZONE_OFFSET) % cal.get(Calendar.DST_OFFSET)) : (ListenerUtil.mutListener.listen(20773) ? (cal.get(Calendar.ZONE_OFFSET) / cal.get(Calendar.DST_OFFSET)) : (ListenerUtil.mutListener.listen(20772) ? (cal.get(Calendar.ZONE_OFFSET) * cal.get(Calendar.DST_OFFSET)) : (ListenerUtil.mutListener.listen(20771) ? (cal.get(Calendar.ZONE_OFFSET) - cal.get(Calendar.DST_OFFSET)) : (cal.get(Calendar.ZONE_OFFSET) + cal.get(Calendar.DST_OFFSET))))))) % 1000) : (ListenerUtil.mutListener.listen(20777) ? (((ListenerUtil.mutListener.listen(20774) ? (cal.get(Calendar.ZONE_OFFSET) % cal.get(Calendar.DST_OFFSET)) : (ListenerUtil.mutListener.listen(20773) ? (cal.get(Calendar.ZONE_OFFSET) / cal.get(Calendar.DST_OFFSET)) : (ListenerUtil.mutListener.listen(20772) ? (cal.get(Calendar.ZONE_OFFSET) * cal.get(Calendar.DST_OFFSET)) : (ListenerUtil.mutListener.listen(20771) ? (cal.get(Calendar.ZONE_OFFSET) - cal.get(Calendar.DST_OFFSET)) : (cal.get(Calendar.ZONE_OFFSET) + cal.get(Calendar.DST_OFFSET))))))) * 1000) : (ListenerUtil.mutListener.listen(20776) ? (((ListenerUtil.mutListener.listen(20774) ? (cal.get(Calendar.ZONE_OFFSET) % cal.get(Calendar.DST_OFFSET)) : (ListenerUtil.mutListener.listen(20773) ? (cal.get(Calendar.ZONE_OFFSET) / cal.get(Calendar.DST_OFFSET)) : (ListenerUtil.mutListener.listen(20772) ? (cal.get(Calendar.ZONE_OFFSET) * cal.get(Calendar.DST_OFFSET)) : (ListenerUtil.mutListener.listen(20771) ? (cal.get(Calendar.ZONE_OFFSET) - cal.get(Calendar.DST_OFFSET)) : (cal.get(Calendar.ZONE_OFFSET) + cal.get(Calendar.DST_OFFSET))))))) - 1000) : (ListenerUtil.mutListener.listen(20775) ? (((ListenerUtil.mutListener.listen(20774) ? (cal.get(Calendar.ZONE_OFFSET) % cal.get(Calendar.DST_OFFSET)) : (ListenerUtil.mutListener.listen(20773) ? (cal.get(Calendar.ZONE_OFFSET) / cal.get(Calendar.DST_OFFSET)) : (ListenerUtil.mutListener.listen(20772) ? (cal.get(Calendar.ZONE_OFFSET) * cal.get(Calendar.DST_OFFSET)) : (ListenerUtil.mutListener.listen(20771) ? (cal.get(Calendar.ZONE_OFFSET) - cal.get(Calendar.DST_OFFSET)) : (cal.get(Calendar.ZONE_OFFSET) + cal.get(Calendar.DST_OFFSET))))))) + 1000) : (((ListenerUtil.mutListener.listen(20774) ? (cal.get(Calendar.ZONE_OFFSET) % cal.get(Calendar.DST_OFFSET)) : (ListenerUtil.mutListener.listen(20773) ? (cal.get(Calendar.ZONE_OFFSET) / cal.get(Calendar.DST_OFFSET)) : (ListenerUtil.mutListener.listen(20772) ? (cal.get(Calendar.ZONE_OFFSET) * cal.get(Calendar.DST_OFFSET)) : (ListenerUtil.mutListener.listen(20771) ? (cal.get(Calendar.ZONE_OFFSET) - cal.get(Calendar.DST_OFFSET)) : (cal.get(Calendar.ZONE_OFFSET) + cal.get(Calendar.DST_OFFSET))))))) / 1000)))))) : (ListenerUtil.mutListener.listen(20779) ? ((ListenerUtil.mutListener.listen(20770) ? ((ListenerUtil.mutListener.listen(20766) ? (4 % 60) : (ListenerUtil.mutListener.listen(20765) ? (4 / 60) : (ListenerUtil.mutListener.listen(20764) ? (4 - 60) : (ListenerUtil.mutListener.listen(20763) ? (4 + 60) : (4 * 60))))) % 60) : (ListenerUtil.mutListener.listen(20769) ? ((ListenerUtil.mutListener.listen(20766) ? (4 % 60) : (ListenerUtil.mutListener.listen(20765) ? (4 / 60) : (ListenerUtil.mutListener.listen(20764) ? (4 - 60) : (ListenerUtil.mutListener.listen(20763) ? (4 + 60) : (4 * 60))))) / 60) : (ListenerUtil.mutListener.listen(20768) ? ((ListenerUtil.mutListener.listen(20766) ? (4 % 60) : (ListenerUtil.mutListener.listen(20765) ? (4 / 60) : (ListenerUtil.mutListener.listen(20764) ? (4 - 60) : (ListenerUtil.mutListener.listen(20763) ? (4 + 60) : (4 * 60))))) - 60) : (ListenerUtil.mutListener.listen(20767) ? ((ListenerUtil.mutListener.listen(20766) ? (4 % 60) : (ListenerUtil.mutListener.listen(20765) ? (4 / 60) : (ListenerUtil.mutListener.listen(20764) ? (4 - 60) : (ListenerUtil.mutListener.listen(20763) ? (4 + 60) : (4 * 60))))) + 60) : ((ListenerUtil.mutListener.listen(20766) ? (4 % 60) : (ListenerUtil.mutListener.listen(20765) ? (4 / 60) : (ListenerUtil.mutListener.listen(20764) ? (4 - 60) : (ListenerUtil.mutListener.listen(20763) ? (4 + 60) : (4 * 60))))) * 60))))) + (ListenerUtil.mutListener.listen(20778) ? (((ListenerUtil.mutListener.listen(20774) ? (cal.get(Calendar.ZONE_OFFSET) % cal.get(Calendar.DST_OFFSET)) : (ListenerUtil.mutListener.listen(20773) ? (cal.get(Calendar.ZONE_OFFSET) / cal.get(Calendar.DST_OFFSET)) : (ListenerUtil.mutListener.listen(20772) ? (cal.get(Calendar.ZONE_OFFSET) * cal.get(Calendar.DST_OFFSET)) : (ListenerUtil.mutListener.listen(20771) ? (cal.get(Calendar.ZONE_OFFSET) - cal.get(Calendar.DST_OFFSET)) : (cal.get(Calendar.ZONE_OFFSET) + cal.get(Calendar.DST_OFFSET))))))) % 1000) : (ListenerUtil.mutListener.listen(20777) ? (((ListenerUtil.mutListener.listen(20774) ? (cal.get(Calendar.ZONE_OFFSET) % cal.get(Calendar.DST_OFFSET)) : (ListenerUtil.mutListener.listen(20773) ? (cal.get(Calendar.ZONE_OFFSET) / cal.get(Calendar.DST_OFFSET)) : (ListenerUtil.mutListener.listen(20772) ? (cal.get(Calendar.ZONE_OFFSET) * cal.get(Calendar.DST_OFFSET)) : (ListenerUtil.mutListener.listen(20771) ? (cal.get(Calendar.ZONE_OFFSET) - cal.get(Calendar.DST_OFFSET)) : (cal.get(Calendar.ZONE_OFFSET) + cal.get(Calendar.DST_OFFSET))))))) * 1000) : (ListenerUtil.mutListener.listen(20776) ? (((ListenerUtil.mutListener.listen(20774) ? (cal.get(Calendar.ZONE_OFFSET) % cal.get(Calendar.DST_OFFSET)) : (ListenerUtil.mutListener.listen(20773) ? (cal.get(Calendar.ZONE_OFFSET) / cal.get(Calendar.DST_OFFSET)) : (ListenerUtil.mutListener.listen(20772) ? (cal.get(Calendar.ZONE_OFFSET) * cal.get(Calendar.DST_OFFSET)) : (ListenerUtil.mutListener.listen(20771) ? (cal.get(Calendar.ZONE_OFFSET) - cal.get(Calendar.DST_OFFSET)) : (cal.get(Calendar.ZONE_OFFSET) + cal.get(Calendar.DST_OFFSET))))))) - 1000) : (ListenerUtil.mutListener.listen(20775) ? (((ListenerUtil.mutListener.listen(20774) ? (cal.get(Calendar.ZONE_OFFSET) % cal.get(Calendar.DST_OFFSET)) : (ListenerUtil.mutListener.listen(20773) ? (cal.get(Calendar.ZONE_OFFSET) / cal.get(Calendar.DST_OFFSET)) : (ListenerUtil.mutListener.listen(20772) ? (cal.get(Calendar.ZONE_OFFSET) * cal.get(Calendar.DST_OFFSET)) : (ListenerUtil.mutListener.listen(20771) ? (cal.get(Calendar.ZONE_OFFSET) - cal.get(Calendar.DST_OFFSET)) : (cal.get(Calendar.ZONE_OFFSET) + cal.get(Calendar.DST_OFFSET))))))) + 1000) : (((ListenerUtil.mutListener.listen(20774) ? (cal.get(Calendar.ZONE_OFFSET) % cal.get(Calendar.DST_OFFSET)) : (ListenerUtil.mutListener.listen(20773) ? (cal.get(Calendar.ZONE_OFFSET) / cal.get(Calendar.DST_OFFSET)) : (ListenerUtil.mutListener.listen(20772) ? (cal.get(Calendar.ZONE_OFFSET) * cal.get(Calendar.DST_OFFSET)) : (ListenerUtil.mutListener.listen(20771) ? (cal.get(Calendar.ZONE_OFFSET) - cal.get(Calendar.DST_OFFSET)) : (cal.get(Calendar.ZONE_OFFSET) + cal.get(Calendar.DST_OFFSET))))))) / 1000)))))) : ((ListenerUtil.mutListener.listen(20770) ? ((ListenerUtil.mutListener.listen(20766) ? (4 % 60) : (ListenerUtil.mutListener.listen(20765) ? (4 / 60) : (ListenerUtil.mutListener.listen(20764) ? (4 - 60) : (ListenerUtil.mutListener.listen(20763) ? (4 + 60) : (4 * 60))))) % 60) : (ListenerUtil.mutListener.listen(20769) ? ((ListenerUtil.mutListener.listen(20766) ? (4 % 60) : (ListenerUtil.mutListener.listen(20765) ? (4 / 60) : (ListenerUtil.mutListener.listen(20764) ? (4 - 60) : (ListenerUtil.mutListener.listen(20763) ? (4 + 60) : (4 * 60))))) / 60) : (ListenerUtil.mutListener.listen(20768) ? ((ListenerUtil.mutListener.listen(20766) ? (4 % 60) : (ListenerUtil.mutListener.listen(20765) ? (4 / 60) : (ListenerUtil.mutListener.listen(20764) ? (4 - 60) : (ListenerUtil.mutListener.listen(20763) ? (4 + 60) : (4 * 60))))) - 60) : (ListenerUtil.mutListener.listen(20767) ? ((ListenerUtil.mutListener.listen(20766) ? (4 % 60) : (ListenerUtil.mutListener.listen(20765) ? (4 / 60) : (ListenerUtil.mutListener.listen(20764) ? (4 - 60) : (ListenerUtil.mutListener.listen(20763) ? (4 + 60) : (4 * 60))))) + 60) : ((ListenerUtil.mutListener.listen(20766) ? (4 % 60) : (ListenerUtil.mutListener.listen(20765) ? (4 / 60) : (ListenerUtil.mutListener.listen(20764) ? (4 - 60) : (ListenerUtil.mutListener.listen(20763) ? (4 + 60) : (4 * 60))))) * 60))))) - (ListenerUtil.mutListener.listen(20778) ? (((ListenerUtil.mutListener.listen(20774) ? (cal.get(Calendar.ZONE_OFFSET) % cal.get(Calendar.DST_OFFSET)) : (ListenerUtil.mutListener.listen(20773) ? (cal.get(Calendar.ZONE_OFFSET) / cal.get(Calendar.DST_OFFSET)) : (ListenerUtil.mutListener.listen(20772) ? (cal.get(Calendar.ZONE_OFFSET) * cal.get(Calendar.DST_OFFSET)) : (ListenerUtil.mutListener.listen(20771) ? (cal.get(Calendar.ZONE_OFFSET) - cal.get(Calendar.DST_OFFSET)) : (cal.get(Calendar.ZONE_OFFSET) + cal.get(Calendar.DST_OFFSET))))))) % 1000) : (ListenerUtil.mutListener.listen(20777) ? (((ListenerUtil.mutListener.listen(20774) ? (cal.get(Calendar.ZONE_OFFSET) % cal.get(Calendar.DST_OFFSET)) : (ListenerUtil.mutListener.listen(20773) ? (cal.get(Calendar.ZONE_OFFSET) / cal.get(Calendar.DST_OFFSET)) : (ListenerUtil.mutListener.listen(20772) ? (cal.get(Calendar.ZONE_OFFSET) * cal.get(Calendar.DST_OFFSET)) : (ListenerUtil.mutListener.listen(20771) ? (cal.get(Calendar.ZONE_OFFSET) - cal.get(Calendar.DST_OFFSET)) : (cal.get(Calendar.ZONE_OFFSET) + cal.get(Calendar.DST_OFFSET))))))) * 1000) : (ListenerUtil.mutListener.listen(20776) ? (((ListenerUtil.mutListener.listen(20774) ? (cal.get(Calendar.ZONE_OFFSET) % cal.get(Calendar.DST_OFFSET)) : (ListenerUtil.mutListener.listen(20773) ? (cal.get(Calendar.ZONE_OFFSET) / cal.get(Calendar.DST_OFFSET)) : (ListenerUtil.mutListener.listen(20772) ? (cal.get(Calendar.ZONE_OFFSET) * cal.get(Calendar.DST_OFFSET)) : (ListenerUtil.mutListener.listen(20771) ? (cal.get(Calendar.ZONE_OFFSET) - cal.get(Calendar.DST_OFFSET)) : (cal.get(Calendar.ZONE_OFFSET) + cal.get(Calendar.DST_OFFSET))))))) - 1000) : (ListenerUtil.mutListener.listen(20775) ? (((ListenerUtil.mutListener.listen(20774) ? (cal.get(Calendar.ZONE_OFFSET) % cal.get(Calendar.DST_OFFSET)) : (ListenerUtil.mutListener.listen(20773) ? (cal.get(Calendar.ZONE_OFFSET) / cal.get(Calendar.DST_OFFSET)) : (ListenerUtil.mutListener.listen(20772) ? (cal.get(Calendar.ZONE_OFFSET) * cal.get(Calendar.DST_OFFSET)) : (ListenerUtil.mutListener.listen(20771) ? (cal.get(Calendar.ZONE_OFFSET) - cal.get(Calendar.DST_OFFSET)) : (cal.get(Calendar.ZONE_OFFSET) + cal.get(Calendar.DST_OFFSET))))))) + 1000) : (((ListenerUtil.mutListener.listen(20774) ? (cal.get(Calendar.ZONE_OFFSET) % cal.get(Calendar.DST_OFFSET)) : (ListenerUtil.mutListener.listen(20773) ? (cal.get(Calendar.ZONE_OFFSET) / cal.get(Calendar.DST_OFFSET)) : (ListenerUtil.mutListener.listen(20772) ? (cal.get(Calendar.ZONE_OFFSET) * cal.get(Calendar.DST_OFFSET)) : (ListenerUtil.mutListener.listen(20771) ? (cal.get(Calendar.ZONE_OFFSET) - cal.get(Calendar.DST_OFFSET)) : (cal.get(Calendar.ZONE_OFFSET) + cal.get(Calendar.DST_OFFSET))))))) / 1000))))))))));
    }

    /**
     *  Returns the effective date of the present moment.
     *  If the time is prior the cut-off time (9:00am by default as of 11/02/10) return yesterday,
     *  otherwise today
     *  Note that the Date class is java.sql.Date whose constructor sets hours, minutes etc to zero
     *
     * @param utcOffset The UTC offset in seconds we are going to use to determine today or yesterday.
     * @return The date (with time set to 00:00:00) that corresponds to today in Anki terms
     */
    public java.sql.Date genToday(double utcOffset) {
        // Timezone adjustment happens explicitly in Deck.updateCutoff(), but not in Deck.checkDailyStats()
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        if (!ListenerUtil.mutListener.listen(20783)) {
            df.setTimeZone(TimeZone.getTimeZone("GMT"));
        }
        Calendar cal = Time.gregorianCalendar((ListenerUtil.mutListener.listen(20791) ? (intTimeMS() % (ListenerUtil.mutListener.listen(20787) ? ((long) utcOffset % 1000L) : (ListenerUtil.mutListener.listen(20786) ? ((long) utcOffset / 1000L) : (ListenerUtil.mutListener.listen(20785) ? ((long) utcOffset - 1000L) : (ListenerUtil.mutListener.listen(20784) ? ((long) utcOffset + 1000L) : ((long) utcOffset * 1000L)))))) : (ListenerUtil.mutListener.listen(20790) ? (intTimeMS() / (ListenerUtil.mutListener.listen(20787) ? ((long) utcOffset % 1000L) : (ListenerUtil.mutListener.listen(20786) ? ((long) utcOffset / 1000L) : (ListenerUtil.mutListener.listen(20785) ? ((long) utcOffset - 1000L) : (ListenerUtil.mutListener.listen(20784) ? ((long) utcOffset + 1000L) : ((long) utcOffset * 1000L)))))) : (ListenerUtil.mutListener.listen(20789) ? (intTimeMS() * (ListenerUtil.mutListener.listen(20787) ? ((long) utcOffset % 1000L) : (ListenerUtil.mutListener.listen(20786) ? ((long) utcOffset / 1000L) : (ListenerUtil.mutListener.listen(20785) ? ((long) utcOffset - 1000L) : (ListenerUtil.mutListener.listen(20784) ? ((long) utcOffset + 1000L) : ((long) utcOffset * 1000L)))))) : (ListenerUtil.mutListener.listen(20788) ? (intTimeMS() + (ListenerUtil.mutListener.listen(20787) ? ((long) utcOffset % 1000L) : (ListenerUtil.mutListener.listen(20786) ? ((long) utcOffset / 1000L) : (ListenerUtil.mutListener.listen(20785) ? ((long) utcOffset - 1000L) : (ListenerUtil.mutListener.listen(20784) ? ((long) utcOffset + 1000L) : ((long) utcOffset * 1000L)))))) : (intTimeMS() - (ListenerUtil.mutListener.listen(20787) ? ((long) utcOffset % 1000L) : (ListenerUtil.mutListener.listen(20786) ? ((long) utcOffset / 1000L) : (ListenerUtil.mutListener.listen(20785) ? ((long) utcOffset - 1000L) : (ListenerUtil.mutListener.listen(20784) ? ((long) utcOffset + 1000L) : ((long) utcOffset * 1000L)))))))))));
        return java.sql.Date.valueOf(df.format(cal.getTime()));
    }
}
