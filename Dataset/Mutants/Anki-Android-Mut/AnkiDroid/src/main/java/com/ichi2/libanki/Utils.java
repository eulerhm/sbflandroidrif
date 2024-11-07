/**
 * *************************************************************************************
 *  Copyright (c) 2009 Daniel Svärd <daniel.svard@gmail.com>                             *
 *  Copyright (c) 2009 Edu Zamora <edu.zasu@gmail.com>                                   *
 *  Copyright (c) 2011 Norbert Nagold <norbert.nagold@gmail.com>                         *
 *  Copyright (c) 2012 Kostas Spyropoulos <inigo.aldana@gmail.com>                       *
 *                                                                                       *
 *  This program is free software; you can redistribute it and/or modify it under        *
 *  the terms of the GNU General Public License as published by the Free Software        *
 *  Foundation; either version 3 of the License, or (at your option) any later           *
 *  version.                                                                             *
 *                                                                                       *
 *  This program is distributed in the hope that it will be useful, but WITHOUT ANY      *
 *  WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A      *
 *  PARTICULAR PURPOSE. See the GNU General Public License for more details.             *
 *                                                                                       *
 *  You should have received a copy of the GNU General Public License along with         *
 *  this program.  If not, see <http://www.gnu.org/licenses/>.                           *
 * **************************************************************************************
 */
package com.ichi2.libanki;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.net.Uri;
import android.text.Spanned;
import androidx.annotation.NonNull;
import android.os.StatFs;
import android.text.TextUtils;
import android.util.Pair;
import com.ichi2.anki.AnkiFont;
import com.ichi2.anki.CollectionHelper;
import com.ichi2.anki.R;
import com.ichi2.compat.CompatHelper;
import com.ichi2.utils.ImportUtils;
import com.ichi2.utils.JSONArray;
import com.ichi2.utils.JSONException;
import com.ichi2.utils.JSONObject;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipFile;
import androidx.annotation.Nullable;
import androidx.core.text.HtmlCompat;
import timber.log.Timber;
import static com.ichi2.libanki.Consts.FIELD_SEPARATOR;
import static com.ichi2.utils.CollectionUtils.addAll;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

@SuppressWarnings({ "PMD.AvoidThrowingRawExceptionTypes", "PMD.AvoidReassigningParameters", "PMD.MethodNamingConventions", "PMD.FieldDeclarationsShouldBeAtStartOfClass" })
public class Utils {

    // Used to format doubles with English's decimal separator system
    public static final Locale ENGLISH_LOCALE = new Locale("en_US");

    public static final int CHUNK_SIZE = 32768;

    // seconds
    private static final long TIME_MINUTE_LONG = 60;

    private static final long TIME_HOUR_LONG = 60 * TIME_MINUTE_LONG;

    private static final long TIME_DAY_LONG = 24 * TIME_HOUR_LONG;

    // seconds
    private static final double TIME_MINUTE = 60.0;

    private static final double TIME_HOUR = 60.0 * TIME_MINUTE;

    private static final double TIME_DAY = 24.0 * TIME_HOUR;

    private static final double TIME_MONTH = 30.0 * TIME_DAY;

    private static final double TIME_YEAR = 12.0 * TIME_MONTH;

    // List of all extensions we accept as font files.
    private static final String[] FONT_FILE_EXTENSIONS = new String[] { ".ttf", ".ttc", ".otf" };

    /* Prevent class from being instantiated */
    private Utils() {
    }

    // Regex pattern used in removing tags from text before diff
    private static final Pattern stylePattern = Pattern.compile("(?si)<style.*?>.*?</style>");

    private static final Pattern scriptPattern = Pattern.compile("(?si)<script.*?>.*?</script>");

    private static final Pattern tagPattern = Pattern.compile("<.*?>");

    private static final Pattern imgPattern = Pattern.compile("(?i)<img[^>]+src=[\"']?([^\"'>]+)[\"']?[^>]*>");

    private static final Pattern soundPattern = Pattern.compile("(?i)\\[sound:([^]]+)]");

    private static final Pattern htmlEntitiesPattern = Pattern.compile("&#?\\w+;");

    private static final String ALL_CHARACTERS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

    private static final String BASE91_EXTRA_CHARS = "!#$%&()*+,-./:;<=>?@[]^_`{|}~";

    private static final int FILE_COPY_BUFFER_SIZE = 1024 * 32;

    /**
     * Return a string representing a time quantity
     *
     * Equivalent to Anki's anki/utils.py's shortTimeFmt, applied to a number.
     * I.e. equivalent to Anki's anki/utils.py's fmtTimeSpan, with the parameter short=True.
     *
     * @param context The application's environment.
     * @param time_s The time to format, in seconds
     * @return The time quantity string. Something like "3 s" or "1.7
     * yr". Only months and year have a number after the decimal.
     */
    public static String timeQuantityTopDeckPicker(Context context, long time_s) {
        Resources res = context.getResources();
        // hard-coded. See also 01-core.xml
        if ((ListenerUtil.mutListener.listen(24147) ? (Math.abs(time_s) >= TIME_MINUTE) : (ListenerUtil.mutListener.listen(24146) ? (Math.abs(time_s) <= TIME_MINUTE) : (ListenerUtil.mutListener.listen(24145) ? (Math.abs(time_s) > TIME_MINUTE) : (ListenerUtil.mutListener.listen(24144) ? (Math.abs(time_s) != TIME_MINUTE) : (ListenerUtil.mutListener.listen(24143) ? (Math.abs(time_s) == TIME_MINUTE) : (Math.abs(time_s) < TIME_MINUTE))))))) {
            return res.getString(R.string.time_quantity_seconds, time_s);
        } else if ((ListenerUtil.mutListener.listen(24152) ? (Math.abs(time_s) >= TIME_HOUR) : (ListenerUtil.mutListener.listen(24151) ? (Math.abs(time_s) <= TIME_HOUR) : (ListenerUtil.mutListener.listen(24150) ? (Math.abs(time_s) > TIME_HOUR) : (ListenerUtil.mutListener.listen(24149) ? (Math.abs(time_s) != TIME_HOUR) : (ListenerUtil.mutListener.listen(24148) ? (Math.abs(time_s) == TIME_HOUR) : (Math.abs(time_s) < TIME_HOUR))))))) {
            return res.getString(R.string.time_quantity_minutes, (int) Math.round((ListenerUtil.mutListener.listen(24203) ? (time_s % TIME_MINUTE) : (ListenerUtil.mutListener.listen(24202) ? (time_s * TIME_MINUTE) : (ListenerUtil.mutListener.listen(24201) ? (time_s - TIME_MINUTE) : (ListenerUtil.mutListener.listen(24200) ? (time_s + TIME_MINUTE) : (time_s / TIME_MINUTE)))))));
        } else if ((ListenerUtil.mutListener.listen(24157) ? (Math.abs(time_s) >= TIME_DAY) : (ListenerUtil.mutListener.listen(24156) ? (Math.abs(time_s) <= TIME_DAY) : (ListenerUtil.mutListener.listen(24155) ? (Math.abs(time_s) > TIME_DAY) : (ListenerUtil.mutListener.listen(24154) ? (Math.abs(time_s) != TIME_DAY) : (ListenerUtil.mutListener.listen(24153) ? (Math.abs(time_s) == TIME_DAY) : (Math.abs(time_s) < TIME_DAY))))))) {
            return res.getString(R.string.time_quantity_hours_minutes, (int) Math.floor((ListenerUtil.mutListener.listen(24191) ? (time_s % TIME_HOUR) : (ListenerUtil.mutListener.listen(24190) ? (time_s * TIME_HOUR) : (ListenerUtil.mutListener.listen(24189) ? (time_s - TIME_HOUR) : (ListenerUtil.mutListener.listen(24188) ? (time_s + TIME_HOUR) : (time_s / TIME_HOUR)))))), (int) Math.round((ListenerUtil.mutListener.listen(24199) ? (((ListenerUtil.mutListener.listen(24195) ? (time_s / TIME_HOUR) : (ListenerUtil.mutListener.listen(24194) ? (time_s * TIME_HOUR) : (ListenerUtil.mutListener.listen(24193) ? (time_s - TIME_HOUR) : (ListenerUtil.mutListener.listen(24192) ? (time_s + TIME_HOUR) : (time_s % TIME_HOUR)))))) % TIME_MINUTE) : (ListenerUtil.mutListener.listen(24198) ? (((ListenerUtil.mutListener.listen(24195) ? (time_s / TIME_HOUR) : (ListenerUtil.mutListener.listen(24194) ? (time_s * TIME_HOUR) : (ListenerUtil.mutListener.listen(24193) ? (time_s - TIME_HOUR) : (ListenerUtil.mutListener.listen(24192) ? (time_s + TIME_HOUR) : (time_s % TIME_HOUR)))))) * TIME_MINUTE) : (ListenerUtil.mutListener.listen(24197) ? (((ListenerUtil.mutListener.listen(24195) ? (time_s / TIME_HOUR) : (ListenerUtil.mutListener.listen(24194) ? (time_s * TIME_HOUR) : (ListenerUtil.mutListener.listen(24193) ? (time_s - TIME_HOUR) : (ListenerUtil.mutListener.listen(24192) ? (time_s + TIME_HOUR) : (time_s % TIME_HOUR)))))) - TIME_MINUTE) : (ListenerUtil.mutListener.listen(24196) ? (((ListenerUtil.mutListener.listen(24195) ? (time_s / TIME_HOUR) : (ListenerUtil.mutListener.listen(24194) ? (time_s * TIME_HOUR) : (ListenerUtil.mutListener.listen(24193) ? (time_s - TIME_HOUR) : (ListenerUtil.mutListener.listen(24192) ? (time_s + TIME_HOUR) : (time_s % TIME_HOUR)))))) + TIME_MINUTE) : (((ListenerUtil.mutListener.listen(24195) ? (time_s / TIME_HOUR) : (ListenerUtil.mutListener.listen(24194) ? (time_s * TIME_HOUR) : (ListenerUtil.mutListener.listen(24193) ? (time_s - TIME_HOUR) : (ListenerUtil.mutListener.listen(24192) ? (time_s + TIME_HOUR) : (time_s % TIME_HOUR)))))) / TIME_MINUTE)))))));
        } else if ((ListenerUtil.mutListener.listen(24162) ? (Math.abs(time_s) >= TIME_MONTH) : (ListenerUtil.mutListener.listen(24161) ? (Math.abs(time_s) <= TIME_MONTH) : (ListenerUtil.mutListener.listen(24160) ? (Math.abs(time_s) > TIME_MONTH) : (ListenerUtil.mutListener.listen(24159) ? (Math.abs(time_s) != TIME_MONTH) : (ListenerUtil.mutListener.listen(24158) ? (Math.abs(time_s) == TIME_MONTH) : (Math.abs(time_s) < TIME_MONTH))))))) {
            return res.getString(R.string.time_quantity_days_hours, (int) Math.floor((ListenerUtil.mutListener.listen(24179) ? (time_s % TIME_DAY) : (ListenerUtil.mutListener.listen(24178) ? (time_s * TIME_DAY) : (ListenerUtil.mutListener.listen(24177) ? (time_s - TIME_DAY) : (ListenerUtil.mutListener.listen(24176) ? (time_s + TIME_DAY) : (time_s / TIME_DAY)))))), (int) Math.round((ListenerUtil.mutListener.listen(24187) ? (((ListenerUtil.mutListener.listen(24183) ? (time_s / TIME_DAY) : (ListenerUtil.mutListener.listen(24182) ? (time_s * TIME_DAY) : (ListenerUtil.mutListener.listen(24181) ? (time_s - TIME_DAY) : (ListenerUtil.mutListener.listen(24180) ? (time_s + TIME_DAY) : (time_s % TIME_DAY)))))) % TIME_HOUR) : (ListenerUtil.mutListener.listen(24186) ? (((ListenerUtil.mutListener.listen(24183) ? (time_s / TIME_DAY) : (ListenerUtil.mutListener.listen(24182) ? (time_s * TIME_DAY) : (ListenerUtil.mutListener.listen(24181) ? (time_s - TIME_DAY) : (ListenerUtil.mutListener.listen(24180) ? (time_s + TIME_DAY) : (time_s % TIME_DAY)))))) * TIME_HOUR) : (ListenerUtil.mutListener.listen(24185) ? (((ListenerUtil.mutListener.listen(24183) ? (time_s / TIME_DAY) : (ListenerUtil.mutListener.listen(24182) ? (time_s * TIME_DAY) : (ListenerUtil.mutListener.listen(24181) ? (time_s - TIME_DAY) : (ListenerUtil.mutListener.listen(24180) ? (time_s + TIME_DAY) : (time_s % TIME_DAY)))))) - TIME_HOUR) : (ListenerUtil.mutListener.listen(24184) ? (((ListenerUtil.mutListener.listen(24183) ? (time_s / TIME_DAY) : (ListenerUtil.mutListener.listen(24182) ? (time_s * TIME_DAY) : (ListenerUtil.mutListener.listen(24181) ? (time_s - TIME_DAY) : (ListenerUtil.mutListener.listen(24180) ? (time_s + TIME_DAY) : (time_s % TIME_DAY)))))) + TIME_HOUR) : (((ListenerUtil.mutListener.listen(24183) ? (time_s / TIME_DAY) : (ListenerUtil.mutListener.listen(24182) ? (time_s * TIME_DAY) : (ListenerUtil.mutListener.listen(24181) ? (time_s - TIME_DAY) : (ListenerUtil.mutListener.listen(24180) ? (time_s + TIME_DAY) : (time_s % TIME_DAY)))))) / TIME_HOUR)))))));
        } else if ((ListenerUtil.mutListener.listen(24167) ? (Math.abs(time_s) >= TIME_YEAR) : (ListenerUtil.mutListener.listen(24166) ? (Math.abs(time_s) <= TIME_YEAR) : (ListenerUtil.mutListener.listen(24165) ? (Math.abs(time_s) > TIME_YEAR) : (ListenerUtil.mutListener.listen(24164) ? (Math.abs(time_s) != TIME_YEAR) : (ListenerUtil.mutListener.listen(24163) ? (Math.abs(time_s) == TIME_YEAR) : (Math.abs(time_s) < TIME_YEAR))))))) {
            return res.getString(R.string.time_quantity_months, (ListenerUtil.mutListener.listen(24175) ? (time_s % TIME_MONTH) : (ListenerUtil.mutListener.listen(24174) ? (time_s * TIME_MONTH) : (ListenerUtil.mutListener.listen(24173) ? (time_s - TIME_MONTH) : (ListenerUtil.mutListener.listen(24172) ? (time_s + TIME_MONTH) : (time_s / TIME_MONTH))))));
        } else {
            return res.getString(R.string.time_quantity_years, (ListenerUtil.mutListener.listen(24171) ? (time_s % TIME_YEAR) : (ListenerUtil.mutListener.listen(24170) ? (time_s * TIME_YEAR) : (ListenerUtil.mutListener.listen(24169) ? (time_s - TIME_YEAR) : (ListenerUtil.mutListener.listen(24168) ? (time_s + TIME_YEAR) : (time_s / TIME_YEAR))))));
        }
    }

    /**
     * Return a string representing a time quantity
     *
     * Equivalent to Anki's anki/utils.py's shortTimeFmt, applied to a number.
     * I.e. equivalent to Anki's anki/utils.py's fmtTimeSpan, with the parameter short=True.
     *
     * @param context The application's environment.
     * @param time_s The time to format, in seconds
     * @return The time quantity string. Something like "3 s" or "1.7
     * yr". Only months and year have a number after the decimal.
     */
    public static String timeQuantityNextIvl(Context context, long time_s) {
        Resources res = context.getResources();
        // hard-coded. See also 01-core.xml
        if ((ListenerUtil.mutListener.listen(24208) ? (Math.abs(time_s) >= TIME_MINUTE) : (ListenerUtil.mutListener.listen(24207) ? (Math.abs(time_s) <= TIME_MINUTE) : (ListenerUtil.mutListener.listen(24206) ? (Math.abs(time_s) > TIME_MINUTE) : (ListenerUtil.mutListener.listen(24205) ? (Math.abs(time_s) != TIME_MINUTE) : (ListenerUtil.mutListener.listen(24204) ? (Math.abs(time_s) == TIME_MINUTE) : (Math.abs(time_s) < TIME_MINUTE))))))) {
            return res.getString(R.string.time_quantity_seconds, time_s);
        } else if ((ListenerUtil.mutListener.listen(24213) ? (Math.abs(time_s) >= TIME_HOUR) : (ListenerUtil.mutListener.listen(24212) ? (Math.abs(time_s) <= TIME_HOUR) : (ListenerUtil.mutListener.listen(24211) ? (Math.abs(time_s) > TIME_HOUR) : (ListenerUtil.mutListener.listen(24210) ? (Math.abs(time_s) != TIME_HOUR) : (ListenerUtil.mutListener.listen(24209) ? (Math.abs(time_s) == TIME_HOUR) : (Math.abs(time_s) < TIME_HOUR))))))) {
            return res.getString(R.string.time_quantity_minutes, (int) Math.round((ListenerUtil.mutListener.listen(24248) ? (time_s % TIME_MINUTE) : (ListenerUtil.mutListener.listen(24247) ? (time_s * TIME_MINUTE) : (ListenerUtil.mutListener.listen(24246) ? (time_s - TIME_MINUTE) : (ListenerUtil.mutListener.listen(24245) ? (time_s + TIME_MINUTE) : (time_s / TIME_MINUTE)))))));
        } else if ((ListenerUtil.mutListener.listen(24218) ? (Math.abs(time_s) >= TIME_DAY) : (ListenerUtil.mutListener.listen(24217) ? (Math.abs(time_s) <= TIME_DAY) : (ListenerUtil.mutListener.listen(24216) ? (Math.abs(time_s) > TIME_DAY) : (ListenerUtil.mutListener.listen(24215) ? (Math.abs(time_s) != TIME_DAY) : (ListenerUtil.mutListener.listen(24214) ? (Math.abs(time_s) == TIME_DAY) : (Math.abs(time_s) < TIME_DAY))))))) {
            return res.getString(R.string.time_quantity_hours, (int) Math.round((ListenerUtil.mutListener.listen(24244) ? (time_s % TIME_HOUR) : (ListenerUtil.mutListener.listen(24243) ? (time_s * TIME_HOUR) : (ListenerUtil.mutListener.listen(24242) ? (time_s - TIME_HOUR) : (ListenerUtil.mutListener.listen(24241) ? (time_s + TIME_HOUR) : (time_s / TIME_HOUR)))))));
        } else if ((ListenerUtil.mutListener.listen(24223) ? (Math.abs(time_s) >= TIME_MONTH) : (ListenerUtil.mutListener.listen(24222) ? (Math.abs(time_s) <= TIME_MONTH) : (ListenerUtil.mutListener.listen(24221) ? (Math.abs(time_s) > TIME_MONTH) : (ListenerUtil.mutListener.listen(24220) ? (Math.abs(time_s) != TIME_MONTH) : (ListenerUtil.mutListener.listen(24219) ? (Math.abs(time_s) == TIME_MONTH) : (Math.abs(time_s) < TIME_MONTH))))))) {
            return res.getString(R.string.time_quantity_days, (int) Math.round((ListenerUtil.mutListener.listen(24240) ? (time_s % TIME_DAY) : (ListenerUtil.mutListener.listen(24239) ? (time_s * TIME_DAY) : (ListenerUtil.mutListener.listen(24238) ? (time_s - TIME_DAY) : (ListenerUtil.mutListener.listen(24237) ? (time_s + TIME_DAY) : (time_s / TIME_DAY)))))));
        } else if ((ListenerUtil.mutListener.listen(24228) ? (Math.abs(time_s) >= TIME_YEAR) : (ListenerUtil.mutListener.listen(24227) ? (Math.abs(time_s) <= TIME_YEAR) : (ListenerUtil.mutListener.listen(24226) ? (Math.abs(time_s) > TIME_YEAR) : (ListenerUtil.mutListener.listen(24225) ? (Math.abs(time_s) != TIME_YEAR) : (ListenerUtil.mutListener.listen(24224) ? (Math.abs(time_s) == TIME_YEAR) : (Math.abs(time_s) < TIME_YEAR))))))) {
            return res.getString(R.string.time_quantity_months, (ListenerUtil.mutListener.listen(24236) ? (time_s % TIME_MONTH) : (ListenerUtil.mutListener.listen(24235) ? (time_s * TIME_MONTH) : (ListenerUtil.mutListener.listen(24234) ? (time_s - TIME_MONTH) : (ListenerUtil.mutListener.listen(24233) ? (time_s + TIME_MONTH) : (time_s / TIME_MONTH))))));
        } else {
            return res.getString(R.string.time_quantity_years, (ListenerUtil.mutListener.listen(24232) ? (time_s % TIME_YEAR) : (ListenerUtil.mutListener.listen(24231) ? (time_s * TIME_YEAR) : (ListenerUtil.mutListener.listen(24230) ? (time_s - TIME_YEAR) : (ListenerUtil.mutListener.listen(24229) ? (time_s + TIME_YEAR) : (time_s / TIME_YEAR))))));
        }
    }

    /**
     * Return a string representing how much time remains
     *
     * @param context The application's environment.
     * @param time_s The time to format, in seconds
     * @return The time quantity string. Something like "3 minutes left" or "2 hours left".
     */
    public static String remainingTime(Context context, long time_s) {
        // Time in unit x
        int time_x;
        // Time not counted in the number in unit x
        int remaining_seconds;
        // Time in the unit smaller than x
        int remaining;
        Resources res = context.getResources();
        if ((ListenerUtil.mutListener.listen(24253) ? (time_s >= TIME_HOUR_LONG) : (ListenerUtil.mutListener.listen(24252) ? (time_s <= TIME_HOUR_LONG) : (ListenerUtil.mutListener.listen(24251) ? (time_s > TIME_HOUR_LONG) : (ListenerUtil.mutListener.listen(24250) ? (time_s != TIME_HOUR_LONG) : (ListenerUtil.mutListener.listen(24249) ? (time_s == TIME_HOUR_LONG) : (time_s < TIME_HOUR_LONG))))))) {
            time_x = (int) Math.round((ListenerUtil.mutListener.listen(24286) ? (time_s % TIME_MINUTE) : (ListenerUtil.mutListener.listen(24285) ? (time_s * TIME_MINUTE) : (ListenerUtil.mutListener.listen(24284) ? (time_s - TIME_MINUTE) : (ListenerUtil.mutListener.listen(24283) ? (time_s + TIME_MINUTE) : (time_s / TIME_MINUTE))))));
            return res.getQuantityString(R.plurals.reviewer_window_title, time_x, time_x);
        } else if ((ListenerUtil.mutListener.listen(24258) ? (time_s >= TIME_DAY_LONG) : (ListenerUtil.mutListener.listen(24257) ? (time_s <= TIME_DAY_LONG) : (ListenerUtil.mutListener.listen(24256) ? (time_s > TIME_DAY_LONG) : (ListenerUtil.mutListener.listen(24255) ? (time_s != TIME_DAY_LONG) : (ListenerUtil.mutListener.listen(24254) ? (time_s == TIME_DAY_LONG) : (time_s < TIME_DAY_LONG))))))) {
            time_x = (int) ((ListenerUtil.mutListener.listen(24274) ? (time_s % TIME_HOUR_LONG) : (ListenerUtil.mutListener.listen(24273) ? (time_s * TIME_HOUR_LONG) : (ListenerUtil.mutListener.listen(24272) ? (time_s - TIME_HOUR_LONG) : (ListenerUtil.mutListener.listen(24271) ? (time_s + TIME_HOUR_LONG) : (time_s / TIME_HOUR_LONG))))));
            remaining_seconds = (int) ((ListenerUtil.mutListener.listen(24278) ? (time_s / TIME_HOUR_LONG) : (ListenerUtil.mutListener.listen(24277) ? (time_s * TIME_HOUR_LONG) : (ListenerUtil.mutListener.listen(24276) ? (time_s - TIME_HOUR_LONG) : (ListenerUtil.mutListener.listen(24275) ? (time_s + TIME_HOUR_LONG) : (time_s % TIME_HOUR_LONG))))));
            remaining = (int) Math.round((ListenerUtil.mutListener.listen(24282) ? ((float) remaining_seconds % TIME_MINUTE) : (ListenerUtil.mutListener.listen(24281) ? ((float) remaining_seconds * TIME_MINUTE) : (ListenerUtil.mutListener.listen(24280) ? ((float) remaining_seconds - TIME_MINUTE) : (ListenerUtil.mutListener.listen(24279) ? ((float) remaining_seconds + TIME_MINUTE) : ((float) remaining_seconds / TIME_MINUTE))))));
            return res.getQuantityString(R.plurals.reviewer_window_title_hours, time_x, time_x, remaining);
        } else {
            time_x = (int) ((ListenerUtil.mutListener.listen(24262) ? (time_s % TIME_DAY_LONG) : (ListenerUtil.mutListener.listen(24261) ? (time_s * TIME_DAY_LONG) : (ListenerUtil.mutListener.listen(24260) ? (time_s - TIME_DAY_LONG) : (ListenerUtil.mutListener.listen(24259) ? (time_s + TIME_DAY_LONG) : (time_s / TIME_DAY_LONG))))));
            remaining_seconds = (int) ((ListenerUtil.mutListener.listen(24266) ? ((float) time_s / TIME_DAY_LONG) : (ListenerUtil.mutListener.listen(24265) ? ((float) time_s * TIME_DAY_LONG) : (ListenerUtil.mutListener.listen(24264) ? ((float) time_s - TIME_DAY_LONG) : (ListenerUtil.mutListener.listen(24263) ? ((float) time_s + TIME_DAY_LONG) : ((float) time_s % TIME_DAY_LONG))))));
            remaining = (int) Math.round((ListenerUtil.mutListener.listen(24270) ? (remaining_seconds % TIME_HOUR) : (ListenerUtil.mutListener.listen(24269) ? (remaining_seconds * TIME_HOUR) : (ListenerUtil.mutListener.listen(24268) ? (remaining_seconds - TIME_HOUR) : (ListenerUtil.mutListener.listen(24267) ? (remaining_seconds + TIME_HOUR) : (remaining_seconds / TIME_HOUR))))));
            return res.getQuantityString(R.plurals.reviewer_window_title_days, time_x, time_x, remaining);
        }
    }

    /**
     * Return a string representing a time
     * (If you want a certain unit, use the strings directly)
     *
     * @param context The application's environment.
     * @param time_s The time to format, in seconds
     * @return The formatted, localized time string. The time is always an integer.
     *  e.g. something like "3 seconds" or "1 year".
     */
    public static String timeSpan(Context context, long time_s) {
        // Time in unit x
        int time_x;
        Resources res = context.getResources();
        if ((ListenerUtil.mutListener.listen(24291) ? (Math.abs(time_s) >= TIME_MINUTE) : (ListenerUtil.mutListener.listen(24290) ? (Math.abs(time_s) <= TIME_MINUTE) : (ListenerUtil.mutListener.listen(24289) ? (Math.abs(time_s) > TIME_MINUTE) : (ListenerUtil.mutListener.listen(24288) ? (Math.abs(time_s) != TIME_MINUTE) : (ListenerUtil.mutListener.listen(24287) ? (Math.abs(time_s) == TIME_MINUTE) : (Math.abs(time_s) < TIME_MINUTE))))))) {
            time_x = (int) time_s;
            return res.getQuantityString(R.plurals.time_span_seconds, time_x, time_x);
        } else if ((ListenerUtil.mutListener.listen(24296) ? (Math.abs(time_s) >= TIME_HOUR) : (ListenerUtil.mutListener.listen(24295) ? (Math.abs(time_s) <= TIME_HOUR) : (ListenerUtil.mutListener.listen(24294) ? (Math.abs(time_s) > TIME_HOUR) : (ListenerUtil.mutListener.listen(24293) ? (Math.abs(time_s) != TIME_HOUR) : (ListenerUtil.mutListener.listen(24292) ? (Math.abs(time_s) == TIME_HOUR) : (Math.abs(time_s) < TIME_HOUR))))))) {
            time_x = (int) Math.round((ListenerUtil.mutListener.listen(24331) ? (time_s % TIME_MINUTE) : (ListenerUtil.mutListener.listen(24330) ? (time_s * TIME_MINUTE) : (ListenerUtil.mutListener.listen(24329) ? (time_s - TIME_MINUTE) : (ListenerUtil.mutListener.listen(24328) ? (time_s + TIME_MINUTE) : (time_s / TIME_MINUTE))))));
            return res.getQuantityString(R.plurals.time_span_minutes, time_x, time_x);
        } else if ((ListenerUtil.mutListener.listen(24301) ? (Math.abs(time_s) >= TIME_DAY) : (ListenerUtil.mutListener.listen(24300) ? (Math.abs(time_s) <= TIME_DAY) : (ListenerUtil.mutListener.listen(24299) ? (Math.abs(time_s) > TIME_DAY) : (ListenerUtil.mutListener.listen(24298) ? (Math.abs(time_s) != TIME_DAY) : (ListenerUtil.mutListener.listen(24297) ? (Math.abs(time_s) == TIME_DAY) : (Math.abs(time_s) < TIME_DAY))))))) {
            time_x = (int) Math.round((ListenerUtil.mutListener.listen(24327) ? (time_s % TIME_HOUR) : (ListenerUtil.mutListener.listen(24326) ? (time_s * TIME_HOUR) : (ListenerUtil.mutListener.listen(24325) ? (time_s - TIME_HOUR) : (ListenerUtil.mutListener.listen(24324) ? (time_s + TIME_HOUR) : (time_s / TIME_HOUR))))));
            return res.getQuantityString(R.plurals.time_span_hours, time_x, time_x);
        } else if ((ListenerUtil.mutListener.listen(24306) ? (Math.abs(time_s) >= TIME_MONTH) : (ListenerUtil.mutListener.listen(24305) ? (Math.abs(time_s) <= TIME_MONTH) : (ListenerUtil.mutListener.listen(24304) ? (Math.abs(time_s) > TIME_MONTH) : (ListenerUtil.mutListener.listen(24303) ? (Math.abs(time_s) != TIME_MONTH) : (ListenerUtil.mutListener.listen(24302) ? (Math.abs(time_s) == TIME_MONTH) : (Math.abs(time_s) < TIME_MONTH))))))) {
            time_x = (int) Math.round((ListenerUtil.mutListener.listen(24323) ? (time_s % TIME_DAY) : (ListenerUtil.mutListener.listen(24322) ? (time_s * TIME_DAY) : (ListenerUtil.mutListener.listen(24321) ? (time_s - TIME_DAY) : (ListenerUtil.mutListener.listen(24320) ? (time_s + TIME_DAY) : (time_s / TIME_DAY))))));
            return res.getQuantityString(R.plurals.time_span_days, time_x, time_x);
        } else if ((ListenerUtil.mutListener.listen(24311) ? (Math.abs(time_s) >= TIME_YEAR) : (ListenerUtil.mutListener.listen(24310) ? (Math.abs(time_s) <= TIME_YEAR) : (ListenerUtil.mutListener.listen(24309) ? (Math.abs(time_s) > TIME_YEAR) : (ListenerUtil.mutListener.listen(24308) ? (Math.abs(time_s) != TIME_YEAR) : (ListenerUtil.mutListener.listen(24307) ? (Math.abs(time_s) == TIME_YEAR) : (Math.abs(time_s) < TIME_YEAR))))))) {
            time_x = (int) Math.round((ListenerUtil.mutListener.listen(24319) ? (time_s % TIME_MONTH) : (ListenerUtil.mutListener.listen(24318) ? (time_s * TIME_MONTH) : (ListenerUtil.mutListener.listen(24317) ? (time_s - TIME_MONTH) : (ListenerUtil.mutListener.listen(24316) ? (time_s + TIME_MONTH) : (time_s / TIME_MONTH))))));
            return res.getQuantityString(R.plurals.time_span_months, time_x, time_x);
        } else {
            time_x = (int) Math.round((ListenerUtil.mutListener.listen(24315) ? (time_s % TIME_YEAR) : (ListenerUtil.mutListener.listen(24314) ? (time_s * TIME_YEAR) : (ListenerUtil.mutListener.listen(24313) ? (time_s - TIME_YEAR) : (ListenerUtil.mutListener.listen(24312) ? (time_s + TIME_YEAR) : (time_s / TIME_YEAR))))));
            return res.getQuantityString(R.plurals.time_span_years, time_x, time_x);
        }
    }

    /**
     * Return a proper string for a time value in seconds
     *
     * Similar to Anki anki/utils.py's fmtTimeSpan.
     *
     * @param context The application's environment.
     * @param time_s The time to format, in seconds
     * @return The formatted, localized time string. The time is always a float. E.g. "27.0 days"
     */
    public static String roundedTimeSpanUnformatted(Context context, long time_s) {
        // As roundedTimeSpan, but without tags; for place where you don't use HTML
        return roundedTimeSpan(context, time_s).replace("<b>", "").replace("</b>", "");
    }

    /**
     * Return a proper string for a time value in seconds
     *
     * Similar to Anki anki/utils.py's fmtTimeSpan.
     *
     * @param context The application's environment.
     * @param time_s The time to format, in seconds
     * @return The formatted, localized time string. The time is always a float. E.g. "<b>27.0</b> days"
     */
    public static String roundedTimeSpan(Context context, long time_s) {
        if ((ListenerUtil.mutListener.listen(24336) ? (Math.abs(time_s) >= TIME_DAY) : (ListenerUtil.mutListener.listen(24335) ? (Math.abs(time_s) <= TIME_DAY) : (ListenerUtil.mutListener.listen(24334) ? (Math.abs(time_s) > TIME_DAY) : (ListenerUtil.mutListener.listen(24333) ? (Math.abs(time_s) != TIME_DAY) : (ListenerUtil.mutListener.listen(24332) ? (Math.abs(time_s) == TIME_DAY) : (Math.abs(time_s) < TIME_DAY))))))) {
            return context.getResources().getString(R.string.stats_overview_hours, (ListenerUtil.mutListener.listen(24362) ? (time_s % TIME_HOUR) : (ListenerUtil.mutListener.listen(24361) ? (time_s * TIME_HOUR) : (ListenerUtil.mutListener.listen(24360) ? (time_s - TIME_HOUR) : (ListenerUtil.mutListener.listen(24359) ? (time_s + TIME_HOUR) : (time_s / TIME_HOUR))))));
        } else if ((ListenerUtil.mutListener.listen(24341) ? (Math.abs(time_s) >= TIME_MONTH) : (ListenerUtil.mutListener.listen(24340) ? (Math.abs(time_s) <= TIME_MONTH) : (ListenerUtil.mutListener.listen(24339) ? (Math.abs(time_s) > TIME_MONTH) : (ListenerUtil.mutListener.listen(24338) ? (Math.abs(time_s) != TIME_MONTH) : (ListenerUtil.mutListener.listen(24337) ? (Math.abs(time_s) == TIME_MONTH) : (Math.abs(time_s) < TIME_MONTH))))))) {
            return context.getResources().getString(R.string.stats_overview_days, (ListenerUtil.mutListener.listen(24358) ? (time_s % TIME_DAY) : (ListenerUtil.mutListener.listen(24357) ? (time_s * TIME_DAY) : (ListenerUtil.mutListener.listen(24356) ? (time_s - TIME_DAY) : (ListenerUtil.mutListener.listen(24355) ? (time_s + TIME_DAY) : (time_s / TIME_DAY))))));
        } else if ((ListenerUtil.mutListener.listen(24346) ? (Math.abs(time_s) >= TIME_YEAR) : (ListenerUtil.mutListener.listen(24345) ? (Math.abs(time_s) <= TIME_YEAR) : (ListenerUtil.mutListener.listen(24344) ? (Math.abs(time_s) > TIME_YEAR) : (ListenerUtil.mutListener.listen(24343) ? (Math.abs(time_s) != TIME_YEAR) : (ListenerUtil.mutListener.listen(24342) ? (Math.abs(time_s) == TIME_YEAR) : (Math.abs(time_s) < TIME_YEAR))))))) {
            return context.getResources().getString(R.string.stats_overview_months, (ListenerUtil.mutListener.listen(24354) ? (time_s % TIME_MONTH) : (ListenerUtil.mutListener.listen(24353) ? (time_s * TIME_MONTH) : (ListenerUtil.mutListener.listen(24352) ? (time_s - TIME_MONTH) : (ListenerUtil.mutListener.listen(24351) ? (time_s + TIME_MONTH) : (time_s / TIME_MONTH))))));
        } else {
            return context.getResources().getString(R.string.stats_overview_years, (ListenerUtil.mutListener.listen(24350) ? (time_s % TIME_YEAR) : (ListenerUtil.mutListener.listen(24349) ? (time_s * TIME_YEAR) : (ListenerUtil.mutListener.listen(24348) ? (time_s - TIME_YEAR) : (ListenerUtil.mutListener.listen(24347) ? (time_s + TIME_YEAR) : (time_s / TIME_YEAR))))));
        }
    }

    /**
     * Strips a text from <style>...</style>, <script>...</script> and <_any_tag_> HTML tags.
     * @param s The HTML text to be cleaned.
     * @return The text without the aforementioned tags.
     */
    public static String stripHTML(String s) {
        if (!ListenerUtil.mutListener.listen(24363)) {
            s = stripHTMLScriptAndStyleTags(s);
        }
        Matcher htmlMatcher = tagPattern.matcher(s);
        if (!ListenerUtil.mutListener.listen(24364)) {
            s = htmlMatcher.replaceAll("");
        }
        return entsToTxt(s);
    }

    /**
     * Strips <style>...</style> and <script>...</script> HTML tags and content from a string.
     * @param s The HTML text to be cleaned.
     * @return The text without the aforementioned tags.
     */
    public static String stripHTMLScriptAndStyleTags(String s) {
        Matcher htmlMatcher = stylePattern.matcher(s);
        if (!ListenerUtil.mutListener.listen(24365)) {
            s = htmlMatcher.replaceAll("");
        }
        if (!ListenerUtil.mutListener.listen(24366)) {
            htmlMatcher = scriptPattern.matcher(s);
        }
        return htmlMatcher.replaceAll("");
    }

    /**
     * Strip HTML but keep media filenames
     */
    public static String stripHTMLMedia(@NonNull String s) {
        return stripHTMLMedia(s, " $1 ");
    }

    public static String stripHTMLMedia(@NonNull String s, String replacement) {
        Matcher imgMatcher = imgPattern.matcher(s);
        return stripHTML(imgMatcher.replaceAll(replacement));
    }

    /**
     * Strip sound but keep media filenames
     */
    public static String stripSoundMedia(String s) {
        return stripSoundMedia(s, " $1 ");
    }

    public static String stripSoundMedia(String s, String replacement) {
        Matcher soundMatcher = soundPattern.matcher(s);
        return soundMatcher.replaceAll(replacement);
    }

    /**
     * Takes a string and replaces all the HTML symbols in it with their unescaped representation.
     * This should only affect substrings of the form &something; and not tags.
     * Internet rumour says that Html.fromHtml() doesn't cover all cases, but it doesn't get less
     * vague than that.
     * @param html The HTML escaped text
     * @return The text with its HTML entities unescaped.
     */
    private static String entsToTxt(String html) {
        if (!ListenerUtil.mutListener.listen(24367)) {
            // replace it first
            html = html.replace("&nbsp;", " ");
        }
        Matcher htmlEntities = htmlEntitiesPattern.matcher(html);
        StringBuffer sb = new StringBuffer();
        if (!ListenerUtil.mutListener.listen(24369)) {
            {
                long _loopCounter642 = 0;
                while (htmlEntities.find()) {
                    ListenerUtil.loopListener.listen("_loopCounter642", ++_loopCounter642);
                    final Spanned spanned = HtmlCompat.fromHtml(htmlEntities.group(), HtmlCompat.FROM_HTML_MODE_LEGACY);
                    final String replacement = Matcher.quoteReplacement(spanned.toString());
                    if (!ListenerUtil.mutListener.listen(24368)) {
                        htmlEntities.appendReplacement(sb, replacement);
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(24370)) {
            htmlEntities.appendTail(sb);
        }
        return sb.toString();
    }

    /**
     * Given a list of integers, return a string '(int1,int2,...)'.
     */
    public static String ids2str(int[] ids) {
        StringBuilder sb = new StringBuilder();
        if (!ListenerUtil.mutListener.listen(24371)) {
            sb.append("(");
        }
        if (!ListenerUtil.mutListener.listen(24377)) {
            if (ids != null) {
                String s = Arrays.toString(ids);
                if (!ListenerUtil.mutListener.listen(24376)) {
                    sb.append(s.substring(1, (ListenerUtil.mutListener.listen(24375) ? (s.length() % 1) : (ListenerUtil.mutListener.listen(24374) ? (s.length() / 1) : (ListenerUtil.mutListener.listen(24373) ? (s.length() * 1) : (ListenerUtil.mutListener.listen(24372) ? (s.length() + 1) : (s.length() - 1)))))));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(24378)) {
            sb.append(")");
        }
        return sb.toString();
    }

    /**
     * Given a list of integers, return a string '(int1,int2,...)'.
     */
    public static String ids2str(long[] ids) {
        StringBuilder sb = new StringBuilder();
        if (!ListenerUtil.mutListener.listen(24379)) {
            sb.append("(");
        }
        if (!ListenerUtil.mutListener.listen(24385)) {
            if (ids != null) {
                String s = Arrays.toString(ids);
                if (!ListenerUtil.mutListener.listen(24384)) {
                    sb.append(s.substring(1, (ListenerUtil.mutListener.listen(24383) ? (s.length() % 1) : (ListenerUtil.mutListener.listen(24382) ? (s.length() / 1) : (ListenerUtil.mutListener.listen(24381) ? (s.length() * 1) : (ListenerUtil.mutListener.listen(24380) ? (s.length() + 1) : (s.length() - 1)))))));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(24386)) {
            sb.append(")");
        }
        return sb.toString();
    }

    /**
     * Given a list of integers, return a string '(int1,int2,...)'.
     */
    public static String ids2str(Long[] ids) {
        StringBuilder sb = new StringBuilder();
        if (!ListenerUtil.mutListener.listen(24387)) {
            sb.append("(");
        }
        if (!ListenerUtil.mutListener.listen(24393)) {
            if (ids != null) {
                String s = Arrays.toString(ids);
                if (!ListenerUtil.mutListener.listen(24392)) {
                    sb.append(s.substring(1, (ListenerUtil.mutListener.listen(24391) ? (s.length() % 1) : (ListenerUtil.mutListener.listen(24390) ? (s.length() / 1) : (ListenerUtil.mutListener.listen(24389) ? (s.length() * 1) : (ListenerUtil.mutListener.listen(24388) ? (s.length() + 1) : (s.length() - 1)))))));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(24394)) {
            sb.append(")");
        }
        return sb.toString();
    }

    /**
     * Given a list of integers, return a string '(int1,int2,...)', in order given by the iterator.
     */
    public static <T> String ids2str(Iterable<T> ids) {
        StringBuilder sb = new StringBuilder(512);
        if (!ListenerUtil.mutListener.listen(24395)) {
            sb.append("(");
        }
        boolean isNotFirst = false;
        if (!ListenerUtil.mutListener.listen(24400)) {
            {
                long _loopCounter643 = 0;
                for (T id : ids) {
                    ListenerUtil.loopListener.listen("_loopCounter643", ++_loopCounter643);
                    if (!ListenerUtil.mutListener.listen(24398)) {
                        if (isNotFirst) {
                            if (!ListenerUtil.mutListener.listen(24397)) {
                                sb.append(", ");
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(24396)) {
                                isNotFirst = true;
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(24399)) {
                        sb.append(id);
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(24401)) {
            sb.append(")");
        }
        return sb.toString();
    }

    /**
     * Given a list of integers, return a string '(int1,int2,...)'.
     */
    public static String ids2str(JSONArray ids) {
        StringBuilder str = new StringBuilder(512);
        if (!ListenerUtil.mutListener.listen(24402)) {
            str.append("(");
        }
        if (!ListenerUtil.mutListener.listen(24422)) {
            if (ids != null) {
                int len = ids.length();
                if (!ListenerUtil.mutListener.listen(24421)) {
                    {
                        long _loopCounter644 = 0;
                        for (int i = 0; (ListenerUtil.mutListener.listen(24420) ? (i >= len) : (ListenerUtil.mutListener.listen(24419) ? (i <= len) : (ListenerUtil.mutListener.listen(24418) ? (i > len) : (ListenerUtil.mutListener.listen(24417) ? (i != len) : (ListenerUtil.mutListener.listen(24416) ? (i == len) : (i < len)))))); i++) {
                            ListenerUtil.loopListener.listen("_loopCounter644", ++_loopCounter644);
                            try {
                                if (!ListenerUtil.mutListener.listen(24415)) {
                                    if ((ListenerUtil.mutListener.listen(24412) ? (i >= ((ListenerUtil.mutListener.listen(24407) ? (len % 1) : (ListenerUtil.mutListener.listen(24406) ? (len / 1) : (ListenerUtil.mutListener.listen(24405) ? (len * 1) : (ListenerUtil.mutListener.listen(24404) ? (len + 1) : (len - 1))))))) : (ListenerUtil.mutListener.listen(24411) ? (i <= ((ListenerUtil.mutListener.listen(24407) ? (len % 1) : (ListenerUtil.mutListener.listen(24406) ? (len / 1) : (ListenerUtil.mutListener.listen(24405) ? (len * 1) : (ListenerUtil.mutListener.listen(24404) ? (len + 1) : (len - 1))))))) : (ListenerUtil.mutListener.listen(24410) ? (i > ((ListenerUtil.mutListener.listen(24407) ? (len % 1) : (ListenerUtil.mutListener.listen(24406) ? (len / 1) : (ListenerUtil.mutListener.listen(24405) ? (len * 1) : (ListenerUtil.mutListener.listen(24404) ? (len + 1) : (len - 1))))))) : (ListenerUtil.mutListener.listen(24409) ? (i < ((ListenerUtil.mutListener.listen(24407) ? (len % 1) : (ListenerUtil.mutListener.listen(24406) ? (len / 1) : (ListenerUtil.mutListener.listen(24405) ? (len * 1) : (ListenerUtil.mutListener.listen(24404) ? (len + 1) : (len - 1))))))) : (ListenerUtil.mutListener.listen(24408) ? (i != ((ListenerUtil.mutListener.listen(24407) ? (len % 1) : (ListenerUtil.mutListener.listen(24406) ? (len / 1) : (ListenerUtil.mutListener.listen(24405) ? (len * 1) : (ListenerUtil.mutListener.listen(24404) ? (len + 1) : (len - 1))))))) : (i == ((ListenerUtil.mutListener.listen(24407) ? (len % 1) : (ListenerUtil.mutListener.listen(24406) ? (len / 1) : (ListenerUtil.mutListener.listen(24405) ? (len * 1) : (ListenerUtil.mutListener.listen(24404) ? (len + 1) : (len - 1))))))))))))) {
                                        if (!ListenerUtil.mutListener.listen(24414)) {
                                            str.append(ids.getLong(i));
                                        }
                                    } else {
                                        if (!ListenerUtil.mutListener.listen(24413)) {
                                            str.append(ids.getLong(i)).append(",");
                                        }
                                    }
                                }
                            } catch (JSONException e) {
                                if (!ListenerUtil.mutListener.listen(24403)) {
                                    Timber.e(e, "ids2str :: JSONException");
                                }
                            }
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(24423)) {
            str.append(")");
        }
        return str.toString();
    }

    /**
     * LIBANKI: not in libanki
     *  Transform a collection of Long into an array of Long
     */
    public static long[] collection2Array(java.util.Collection<Long> list) {
        long[] ar = new long[list.size()];
        int i = 0;
        if (!ListenerUtil.mutListener.listen(24425)) {
            {
                long _loopCounter645 = 0;
                for (long l : list) {
                    ListenerUtil.loopListener.listen("_loopCounter645", ++_loopCounter645);
                    if (!ListenerUtil.mutListener.listen(24424)) {
                        ar[i++] = l;
                    }
                }
            }
        }
        return ar;
    }

    public static Long[] list2ObjectArray(List<Long> list) {
        return list.toArray(new Long[0]);
    }

    // used in ankiweb
    private static String base62(int num, String extra) {
        String table = ALL_CHARACTERS + extra;
        int len = table.length();
        String buf = "";
        int mod;
        {
            long _loopCounter646 = 0;
            while ((ListenerUtil.mutListener.listen(24444) ? (num >= 0) : (ListenerUtil.mutListener.listen(24443) ? (num <= 0) : (ListenerUtil.mutListener.listen(24442) ? (num > 0) : (ListenerUtil.mutListener.listen(24441) ? (num < 0) : (ListenerUtil.mutListener.listen(24440) ? (num == 0) : (num != 0))))))) {
                ListenerUtil.loopListener.listen("_loopCounter646", ++_loopCounter646);
                mod = (ListenerUtil.mutListener.listen(24429) ? (num / len) : (ListenerUtil.mutListener.listen(24428) ? (num * len) : (ListenerUtil.mutListener.listen(24427) ? (num - len) : (ListenerUtil.mutListener.listen(24426) ? (num + len) : (num % len)))));
                if (!ListenerUtil.mutListener.listen(24434)) {
                    buf = buf + table.substring(mod, (ListenerUtil.mutListener.listen(24433) ? (mod % 1) : (ListenerUtil.mutListener.listen(24432) ? (mod / 1) : (ListenerUtil.mutListener.listen(24431) ? (mod * 1) : (ListenerUtil.mutListener.listen(24430) ? (mod - 1) : (mod + 1))))));
                }
                if (!ListenerUtil.mutListener.listen(24439)) {
                    num = (ListenerUtil.mutListener.listen(24438) ? (num % len) : (ListenerUtil.mutListener.listen(24437) ? (num * len) : (ListenerUtil.mutListener.listen(24436) ? (num - len) : (ListenerUtil.mutListener.listen(24435) ? (num + len) : (num / len)))));
                }
            }
        }
        return buf;
    }

    // all printable characters minus quotes, backslash and separators
    private static String base91(int num) {
        return base62(num, BASE91_EXTRA_CHARS);
    }

    /**
     * return a base91-encoded 64bit random number
     */
    public static String guid64() {
        return base91((new Random()).nextInt((int) ((ListenerUtil.mutListener.listen(24448) ? (Math.pow(2, 61) % 1) : (ListenerUtil.mutListener.listen(24447) ? (Math.pow(2, 61) / 1) : (ListenerUtil.mutListener.listen(24446) ? (Math.pow(2, 61) * 1) : (ListenerUtil.mutListener.listen(24445) ? (Math.pow(2, 61) + 1) : (Math.pow(2, 61) - 1))))))));
    }

    // increment a guid by one, for note type conflicts
    // used in Anki
    @SuppressWarnings({ "unused" })
    public static String incGuid(String guid) {
        return new StringBuffer(_incGuid(new StringBuffer(guid).reverse().toString())).reverse().toString();
    }

    private static String _incGuid(String guid) {
        String table = ALL_CHARACTERS + BASE91_EXTRA_CHARS;
        int idx = table.indexOf(guid.substring(0, 1));
        if (!ListenerUtil.mutListener.listen(24464)) {
            if ((ListenerUtil.mutListener.listen(24457) ? ((ListenerUtil.mutListener.listen(24452) ? (idx % 1) : (ListenerUtil.mutListener.listen(24451) ? (idx / 1) : (ListenerUtil.mutListener.listen(24450) ? (idx * 1) : (ListenerUtil.mutListener.listen(24449) ? (idx - 1) : (idx + 1))))) >= table.length()) : (ListenerUtil.mutListener.listen(24456) ? ((ListenerUtil.mutListener.listen(24452) ? (idx % 1) : (ListenerUtil.mutListener.listen(24451) ? (idx / 1) : (ListenerUtil.mutListener.listen(24450) ? (idx * 1) : (ListenerUtil.mutListener.listen(24449) ? (idx - 1) : (idx + 1))))) <= table.length()) : (ListenerUtil.mutListener.listen(24455) ? ((ListenerUtil.mutListener.listen(24452) ? (idx % 1) : (ListenerUtil.mutListener.listen(24451) ? (idx / 1) : (ListenerUtil.mutListener.listen(24450) ? (idx * 1) : (ListenerUtil.mutListener.listen(24449) ? (idx - 1) : (idx + 1))))) > table.length()) : (ListenerUtil.mutListener.listen(24454) ? ((ListenerUtil.mutListener.listen(24452) ? (idx % 1) : (ListenerUtil.mutListener.listen(24451) ? (idx / 1) : (ListenerUtil.mutListener.listen(24450) ? (idx * 1) : (ListenerUtil.mutListener.listen(24449) ? (idx - 1) : (idx + 1))))) < table.length()) : (ListenerUtil.mutListener.listen(24453) ? ((ListenerUtil.mutListener.listen(24452) ? (idx % 1) : (ListenerUtil.mutListener.listen(24451) ? (idx / 1) : (ListenerUtil.mutListener.listen(24450) ? (idx * 1) : (ListenerUtil.mutListener.listen(24449) ? (idx - 1) : (idx + 1))))) != table.length()) : ((ListenerUtil.mutListener.listen(24452) ? (idx % 1) : (ListenerUtil.mutListener.listen(24451) ? (idx / 1) : (ListenerUtil.mutListener.listen(24450) ? (idx * 1) : (ListenerUtil.mutListener.listen(24449) ? (idx - 1) : (idx + 1))))) == table.length()))))))) {
                if (!ListenerUtil.mutListener.listen(24463)) {
                    // overflow
                    guid = table.substring(0, 1) + _incGuid(guid.substring(1));
                }
            } else {
                if (!ListenerUtil.mutListener.listen(24462)) {
                    guid = table.substring((ListenerUtil.mutListener.listen(24461) ? (idx % 1) : (ListenerUtil.mutListener.listen(24460) ? (idx / 1) : (ListenerUtil.mutListener.listen(24459) ? (idx * 1) : (ListenerUtil.mutListener.listen(24458) ? (idx - 1) : (idx + 1)))))) + guid.substring(1);
                }
            }
        }
        return guid;
    }

    public static Object[] jsonArray2Objects(JSONArray array) {
        Object[] o = new Object[array.length()];
        if (!ListenerUtil.mutListener.listen(24471)) {
            {
                long _loopCounter647 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(24470) ? (i >= array.length()) : (ListenerUtil.mutListener.listen(24469) ? (i <= array.length()) : (ListenerUtil.mutListener.listen(24468) ? (i > array.length()) : (ListenerUtil.mutListener.listen(24467) ? (i != array.length()) : (ListenerUtil.mutListener.listen(24466) ? (i == array.length()) : (i < array.length())))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter647", ++_loopCounter647);
                    if (!ListenerUtil.mutListener.listen(24465)) {
                        o[i] = array.get(i);
                    }
                }
            }
        }
        return o;
    }

    public static String joinFields(String[] list) {
        StringBuilder result = new StringBuilder(128);
        if (!ListenerUtil.mutListener.listen(24482)) {
            {
                long _loopCounter648 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(24481) ? (i >= (ListenerUtil.mutListener.listen(24476) ? (list.length % 1) : (ListenerUtil.mutListener.listen(24475) ? (list.length / 1) : (ListenerUtil.mutListener.listen(24474) ? (list.length * 1) : (ListenerUtil.mutListener.listen(24473) ? (list.length + 1) : (list.length - 1)))))) : (ListenerUtil.mutListener.listen(24480) ? (i <= (ListenerUtil.mutListener.listen(24476) ? (list.length % 1) : (ListenerUtil.mutListener.listen(24475) ? (list.length / 1) : (ListenerUtil.mutListener.listen(24474) ? (list.length * 1) : (ListenerUtil.mutListener.listen(24473) ? (list.length + 1) : (list.length - 1)))))) : (ListenerUtil.mutListener.listen(24479) ? (i > (ListenerUtil.mutListener.listen(24476) ? (list.length % 1) : (ListenerUtil.mutListener.listen(24475) ? (list.length / 1) : (ListenerUtil.mutListener.listen(24474) ? (list.length * 1) : (ListenerUtil.mutListener.listen(24473) ? (list.length + 1) : (list.length - 1)))))) : (ListenerUtil.mutListener.listen(24478) ? (i != (ListenerUtil.mutListener.listen(24476) ? (list.length % 1) : (ListenerUtil.mutListener.listen(24475) ? (list.length / 1) : (ListenerUtil.mutListener.listen(24474) ? (list.length * 1) : (ListenerUtil.mutListener.listen(24473) ? (list.length + 1) : (list.length - 1)))))) : (ListenerUtil.mutListener.listen(24477) ? (i == (ListenerUtil.mutListener.listen(24476) ? (list.length % 1) : (ListenerUtil.mutListener.listen(24475) ? (list.length / 1) : (ListenerUtil.mutListener.listen(24474) ? (list.length * 1) : (ListenerUtil.mutListener.listen(24473) ? (list.length + 1) : (list.length - 1)))))) : (i < (ListenerUtil.mutListener.listen(24476) ? (list.length % 1) : (ListenerUtil.mutListener.listen(24475) ? (list.length / 1) : (ListenerUtil.mutListener.listen(24474) ? (list.length * 1) : (ListenerUtil.mutListener.listen(24473) ? (list.length + 1) : (list.length - 1))))))))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter648", ++_loopCounter648);
                    if (!ListenerUtil.mutListener.listen(24472)) {
                        result.append(list[i]).append("\u001f");
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(24493)) {
            if ((ListenerUtil.mutListener.listen(24487) ? (list.length >= 0) : (ListenerUtil.mutListener.listen(24486) ? (list.length <= 0) : (ListenerUtil.mutListener.listen(24485) ? (list.length < 0) : (ListenerUtil.mutListener.listen(24484) ? (list.length != 0) : (ListenerUtil.mutListener.listen(24483) ? (list.length == 0) : (list.length > 0))))))) {
                if (!ListenerUtil.mutListener.listen(24492)) {
                    result.append(list[(ListenerUtil.mutListener.listen(24491) ? (list.length % 1) : (ListenerUtil.mutListener.listen(24490) ? (list.length / 1) : (ListenerUtil.mutListener.listen(24489) ? (list.length * 1) : (ListenerUtil.mutListener.listen(24488) ? (list.length + 1) : (list.length - 1)))))]);
                }
            }
        }
        return result.toString();
    }

    public static String[] splitFields(String fields) {
        // -1 ensures that we don't drop empty fields at the ends
        return fields.split(FIELD_SEPARATOR, -1);
    }

    /**
     * SHA1 checksum.
     * Equivalent to python sha1.hexdigest()
     *
     * @param data the string to generate hash from
     * @return A string of length 40 containing the hexadecimal representation of the MD5 checksum of data.
     */
    @SuppressWarnings("CharsetObjectCanBeUsed")
    public static String checksum(String data) {
        String result = "";
        if (!ListenerUtil.mutListener.listen(24510)) {
            if (data != null) {
                MessageDigest md;
                byte[] digest = null;
                try {
                    md = MessageDigest.getInstance("SHA1");
                    if (!ListenerUtil.mutListener.listen(24497)) {
                        digest = md.digest(data.getBytes("UTF-8"));
                    }
                } catch (NoSuchAlgorithmException e) {
                    if (!ListenerUtil.mutListener.listen(24494)) {
                        Timber.e(e, "Utils.checksum: No such algorithm.");
                    }
                    throw new RuntimeException(e);
                } catch (UnsupportedEncodingException e) {
                    if (!ListenerUtil.mutListener.listen(24495)) {
                        Timber.e(e, "Utils.checksum :: UnsupportedEncodingException");
                    }
                    if (!ListenerUtil.mutListener.listen(24496)) {
                        e.printStackTrace();
                    }
                }
                BigInteger biginteger = new BigInteger(1, digest);
                if (!ListenerUtil.mutListener.listen(24498)) {
                    result = biginteger.toString(16);
                }
                if (!ListenerUtil.mutListener.listen(24509)) {
                    // not 32.
                    if ((ListenerUtil.mutListener.listen(24503) ? (result.length() >= 40) : (ListenerUtil.mutListener.listen(24502) ? (result.length() <= 40) : (ListenerUtil.mutListener.listen(24501) ? (result.length() > 40) : (ListenerUtil.mutListener.listen(24500) ? (result.length() != 40) : (ListenerUtil.mutListener.listen(24499) ? (result.length() == 40) : (result.length() < 40))))))) {
                        String zeroes = "0000000000000000000000000000000000000000";
                        if (!ListenerUtil.mutListener.listen(24508)) {
                            result = zeroes.substring(0, (ListenerUtil.mutListener.listen(24507) ? (zeroes.length() % result.length()) : (ListenerUtil.mutListener.listen(24506) ? (zeroes.length() / result.length()) : (ListenerUtil.mutListener.listen(24505) ? (zeroes.length() * result.length()) : (ListenerUtil.mutListener.listen(24504) ? (zeroes.length() + result.length()) : (zeroes.length() - result.length())))))) + result;
                        }
                    }
                }
            }
        }
        return result;
    }

    /**
     * Optimized in case of sortIdx = 0
     * @param fields Fields of a note
     * @param sortIdx An index of the field
     * @return The field at sortIdx, without html media, and the csum of the first field.
     */
    public static Pair<String, Long> sfieldAndCsum(String[] fields, int sortIdx) {
        String firstStripped = stripHTMLMedia(fields[0]);
        String sortStripped = ((ListenerUtil.mutListener.listen(24515) ? (sortIdx >= 0) : (ListenerUtil.mutListener.listen(24514) ? (sortIdx <= 0) : (ListenerUtil.mutListener.listen(24513) ? (sortIdx > 0) : (ListenerUtil.mutListener.listen(24512) ? (sortIdx < 0) : (ListenerUtil.mutListener.listen(24511) ? (sortIdx != 0) : (sortIdx == 0))))))) ? firstStripped : stripHTMLMedia(fields[sortIdx]);
        return new Pair<>(sortStripped, fieldChecksumWithoutHtmlMedia(firstStripped));
    }

    /**
     * @param data the string to generate hash from.
     * @return 32 bit unsigned number from first 8 digits of sha1 hash
     */
    public static long fieldChecksum(String data) {
        return fieldChecksumWithoutHtmlMedia(stripHTMLMedia(data));
    }

    /**
     * @param data the string to generate hash from. Html media should be removed
     * @return 32 bit unsigned number from first 8 digits of sha1 hash
     */
    public static long fieldChecksumWithoutHtmlMedia(String data) {
        return Long.valueOf(checksum(data).substring(0, 8), 16);
    }

    /**
     * Generate the SHA1 checksum of a file.
     * @param file The file to be checked
     * @return A string of length 32 containing the hexadecimal representation of the SHA1 checksum of the file's contents.
     */
    public static String fileChecksum(String file) {
        byte[] buffer = new byte[1024];
        byte[] digest = null;
        try {
            InputStream fis = new FileInputStream(file);
            MessageDigest md = MessageDigest.getInstance("SHA1");
            int numRead = 0;
            if (!ListenerUtil.mutListener.listen(24532)) {
                {
                    long _loopCounter649 = 0;
                    do {
                        ListenerUtil.loopListener.listen("_loopCounter649", ++_loopCounter649);
                        if (!ListenerUtil.mutListener.listen(24519)) {
                            numRead = fis.read(buffer);
                        }
                        if (!ListenerUtil.mutListener.listen(24526)) {
                            if ((ListenerUtil.mutListener.listen(24524) ? (numRead >= 0) : (ListenerUtil.mutListener.listen(24523) ? (numRead <= 0) : (ListenerUtil.mutListener.listen(24522) ? (numRead < 0) : (ListenerUtil.mutListener.listen(24521) ? (numRead != 0) : (ListenerUtil.mutListener.listen(24520) ? (numRead == 0) : (numRead > 0))))))) {
                                if (!ListenerUtil.mutListener.listen(24525)) {
                                    md.update(buffer, 0, numRead);
                                }
                            }
                        }
                    } while ((ListenerUtil.mutListener.listen(24531) ? (numRead >= -1) : (ListenerUtil.mutListener.listen(24530) ? (numRead <= -1) : (ListenerUtil.mutListener.listen(24529) ? (numRead > -1) : (ListenerUtil.mutListener.listen(24528) ? (numRead < -1) : (ListenerUtil.mutListener.listen(24527) ? (numRead == -1) : (numRead != -1)))))));
                }
            }
            if (!ListenerUtil.mutListener.listen(24533)) {
                fis.close();
            }
            if (!ListenerUtil.mutListener.listen(24534)) {
                digest = md.digest();
            }
        } catch (FileNotFoundException e) {
            if (!ListenerUtil.mutListener.listen(24516)) {
                Timber.e(e, "Utils.fileChecksum: File not found.");
            }
        } catch (NoSuchAlgorithmException e) {
            if (!ListenerUtil.mutListener.listen(24517)) {
                Timber.e(e, "Utils.fileChecksum: No such algorithm.");
            }
        } catch (IOException e) {
            if (!ListenerUtil.mutListener.listen(24518)) {
                Timber.e(e, "Utils.fileChecksum: IO exception.");
            }
        }
        BigInteger biginteger = new BigInteger(1, digest);
        String result = biginteger.toString(16);
        if (!ListenerUtil.mutListener.listen(24545)) {
            // pad with zeros to length of 40 - SHA1 is 160bit long
            if ((ListenerUtil.mutListener.listen(24539) ? (result.length() >= 40) : (ListenerUtil.mutListener.listen(24538) ? (result.length() <= 40) : (ListenerUtil.mutListener.listen(24537) ? (result.length() > 40) : (ListenerUtil.mutListener.listen(24536) ? (result.length() != 40) : (ListenerUtil.mutListener.listen(24535) ? (result.length() == 40) : (result.length() < 40))))))) {
                if (!ListenerUtil.mutListener.listen(24544)) {
                    result = "0000000000000000000000000000000000000000".substring(0, (ListenerUtil.mutListener.listen(24543) ? (40 % result.length()) : (ListenerUtil.mutListener.listen(24542) ? (40 / result.length()) : (ListenerUtil.mutListener.listen(24541) ? (40 * result.length()) : (ListenerUtil.mutListener.listen(24540) ? (40 + result.length()) : (40 - result.length())))))) + result;
                }
            }
        }
        return result;
    }

    public static String fileChecksum(File file) {
        return fileChecksum(file.getAbsolutePath());
    }

    /**
     * Converts an InputStream to a String.
     * @param is InputStream to convert
     * @return String version of the InputStream
     */
    public static String convertStreamToString(InputStream is) {
        String contentOfMyInputStream = "";
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is), 4096);
            String line;
            StringBuilder sb = new StringBuilder();
            if (!ListenerUtil.mutListener.listen(24548)) {
                {
                    long _loopCounter650 = 0;
                    while ((line = rd.readLine()) != null) {
                        ListenerUtil.loopListener.listen("_loopCounter650", ++_loopCounter650);
                        if (!ListenerUtil.mutListener.listen(24547)) {
                            sb.append(line);
                        }
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(24549)) {
                rd.close();
            }
            if (!ListenerUtil.mutListener.listen(24550)) {
                contentOfMyInputStream = sb.toString();
            }
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(24546)) {
                e.printStackTrace();
            }
        }
        return contentOfMyInputStream;
    }

    public static void unzipAllFiles(ZipFile zipFile, String targetDirectory) throws IOException {
        List<String> entryNames = new ArrayList<>();
        Enumeration<ZipArchiveEntry> i = zipFile.getEntries();
        if (!ListenerUtil.mutListener.listen(24552)) {
            {
                long _loopCounter651 = 0;
                while (i.hasMoreElements()) {
                    ListenerUtil.loopListener.listen("_loopCounter651", ++_loopCounter651);
                    ZipArchiveEntry e = i.nextElement();
                    if (!ListenerUtil.mutListener.listen(24551)) {
                        entryNames.add(e.getName());
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(24553)) {
            unzipFiles(zipFile, targetDirectory, entryNames.toArray(new String[0]), null);
        }
    }

    /**
     * @param zipFile A zip file
     * @param targetDirectory Directory in which to unzip some of the zipped field
     * @param zipEntries files of the zip folder to unzip
     * @param zipEntryToFilenameMap Renaming rules from name in zip file to name in the device
     * @throws IOException if the directory can't be created
     */
    public static void unzipFiles(ZipFile zipFile, String targetDirectory, @NonNull String[] zipEntries, @Nullable Map<String, String> zipEntryToFilenameMap) throws IOException {
        File dir = new File(targetDirectory);
        if (!ListenerUtil.mutListener.listen(24555)) {
            if ((ListenerUtil.mutListener.listen(24554) ? (!dir.exists() || !dir.mkdirs()) : (!dir.exists() && !dir.mkdirs()))) {
                throw new IOException("Failed to create target directory: " + targetDirectory);
            }
        }
        if (!ListenerUtil.mutListener.listen(24557)) {
            if (zipEntryToFilenameMap == null) {
                if (!ListenerUtil.mutListener.listen(24556)) {
                    zipEntryToFilenameMap = new HashMap<>(0);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(24566)) {
            {
                long _loopCounter652 = 0;
                for (String requestedEntry : zipEntries) {
                    ListenerUtil.loopListener.listen("_loopCounter652", ++_loopCounter652);
                    ZipArchiveEntry ze = zipFile.getEntry(requestedEntry);
                    if (!ListenerUtil.mutListener.listen(24565)) {
                        if (ze != null) {
                            String name = ze.getName();
                            if (!ListenerUtil.mutListener.listen(24559)) {
                                if (zipEntryToFilenameMap.containsKey(name)) {
                                    if (!ListenerUtil.mutListener.listen(24558)) {
                                        name = zipEntryToFilenameMap.get(name);
                                    }
                                }
                            }
                            File destFile = new File(dir, name);
                            if (!ListenerUtil.mutListener.listen(24561)) {
                                if (!isInside(destFile, dir)) {
                                    if (!ListenerUtil.mutListener.listen(24560)) {
                                        Timber.e("Refusing to decompress invalid path: %s", destFile.getCanonicalPath());
                                    }
                                    throw new IOException("File is outside extraction target directory.");
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(24564)) {
                                if (!ze.isDirectory()) {
                                    if (!ListenerUtil.mutListener.listen(24562)) {
                                        Timber.i("uncompress %s", name);
                                    }
                                    try (InputStream zis = zipFile.getInputStream(ze)) {
                                        if (!ListenerUtil.mutListener.listen(24563)) {
                                            writeToFile(zis, destFile.getAbsolutePath());
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * Checks to see if a given file path resides inside a given directory.
     * Useful for protection against path traversal attacks prior to creating the file
     * @param file the file with an uncertain filesystem location
     * @param dir the directory that should contain the file
     * @return true if the file path is inside the directory
     * @exception IOException if there are security or filesystem issues determining the paths
     */
    public static boolean isInside(@NonNull File file, @NonNull File dir) throws IOException {
        return file.getCanonicalPath().startsWith(dir.getCanonicalPath());
    }

    /**
     * Given a ZipFile, iterate through the ZipEntries to determine the total uncompressed size
     * TODO warning: vulnerable to resource exhaustion attack if entries contain spoofed sizes
     *
     * @param zipFile ZipFile of unknown total uncompressed size
     * @return total uncompressed size of zipFile
     */
    public static long calculateUncompressedSize(ZipFile zipFile) {
        long totalUncompressedSize = 0;
        Enumeration<ZipArchiveEntry> e = zipFile.getEntries();
        if (!ListenerUtil.mutListener.listen(24568)) {
            {
                long _loopCounter653 = 0;
                while (e.hasMoreElements()) {
                    ListenerUtil.loopListener.listen("_loopCounter653", ++_loopCounter653);
                    ZipArchiveEntry ze = e.nextElement();
                    if (!ListenerUtil.mutListener.listen(24567)) {
                        totalUncompressedSize += ze.getSize();
                    }
                }
            }
        }
        return totalUncompressedSize;
    }

    /**
     * Determine available storage space
     *
     * @param path the filesystem path you need free space information on
     * @return long indicating the bytes available for that path
     */
    public static long determineBytesAvailable(String path) {
        return new StatFs(path).getAvailableBytes();
    }

    /**
     * Calls {@link #writeToFileImpl(InputStream, String)} and handles IOExceptions
     * Does not close the provided stream
     * @throws IOException Rethrows exception after a set number of retries
     */
    public static void writeToFile(InputStream source, String destination) throws IOException {
        // sometimes this fails and works on retries (hardware issue?)
        final int retries = 5;
        int retryCnt = 0;
        boolean success = false;
        if (!ListenerUtil.mutListener.listen(24587)) {
            {
                long _loopCounter654 = 0;
                while ((ListenerUtil.mutListener.listen(24586) ? (!success || (ListenerUtil.mutListener.listen(24585) ? (retryCnt++ >= retries) : (ListenerUtil.mutListener.listen(24584) ? (retryCnt++ <= retries) : (ListenerUtil.mutListener.listen(24583) ? (retryCnt++ > retries) : (ListenerUtil.mutListener.listen(24582) ? (retryCnt++ != retries) : (ListenerUtil.mutListener.listen(24581) ? (retryCnt++ == retries) : (retryCnt++ < retries))))))) : (!success && (ListenerUtil.mutListener.listen(24585) ? (retryCnt++ >= retries) : (ListenerUtil.mutListener.listen(24584) ? (retryCnt++ <= retries) : (ListenerUtil.mutListener.listen(24583) ? (retryCnt++ > retries) : (ListenerUtil.mutListener.listen(24582) ? (retryCnt++ != retries) : (ListenerUtil.mutListener.listen(24581) ? (retryCnt++ == retries) : (retryCnt++ < retries))))))))) {
                    ListenerUtil.loopListener.listen("_loopCounter654", ++_loopCounter654);
                    try {
                        if (!ListenerUtil.mutListener.listen(24579)) {
                            writeToFileImpl(source, destination);
                        }
                        if (!ListenerUtil.mutListener.listen(24580)) {
                            success = true;
                        }
                    } catch (IOException e) {
                        if (!ListenerUtil.mutListener.listen(24578)) {
                            if ((ListenerUtil.mutListener.listen(24573) ? (retryCnt >= retries) : (ListenerUtil.mutListener.listen(24572) ? (retryCnt <= retries) : (ListenerUtil.mutListener.listen(24571) ? (retryCnt > retries) : (ListenerUtil.mutListener.listen(24570) ? (retryCnt < retries) : (ListenerUtil.mutListener.listen(24569) ? (retryCnt != retries) : (retryCnt == retries))))))) {
                                if (!ListenerUtil.mutListener.listen(24577)) {
                                    Timber.e("IOException while writing to file, out of retries.");
                                }
                                throw e;
                            } else {
                                if (!ListenerUtil.mutListener.listen(24574)) {
                                    Timber.e("IOException while writing to file, retrying...");
                                }
                                try {
                                    if (!ListenerUtil.mutListener.listen(24576)) {
                                        Thread.sleep(200);
                                    }
                                } catch (InterruptedException e1) {
                                    if (!ListenerUtil.mutListener.listen(24575)) {
                                        e1.printStackTrace();
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * Utility method to write to a file.
     * Throws the exception, so we can report it in syncing log
     */
    private static void writeToFileImpl(InputStream source, String destination) throws IOException {
        File f = new File(destination);
        try {
            if (!ListenerUtil.mutListener.listen(24588)) {
                Timber.d("Creating new file... = %s", destination);
            }
            if (!ListenerUtil.mutListener.listen(24589)) {
                f.createNewFile();
            }
            @SuppressLint("DirectSystemCurrentTimeMillisUsage")
            long startTimeMillis = System.currentTimeMillis();
            long sizeBytes = CompatHelper.getCompat().copyFile(source, destination);
            @SuppressLint("DirectSystemCurrentTimeMillisUsage")
            long endTimeMillis = System.currentTimeMillis();
            if (!ListenerUtil.mutListener.listen(24590)) {
                Timber.d("Finished writeToFile!");
            }
            long durationSeconds = (ListenerUtil.mutListener.listen(24598) ? (((ListenerUtil.mutListener.listen(24594) ? (endTimeMillis % startTimeMillis) : (ListenerUtil.mutListener.listen(24593) ? (endTimeMillis / startTimeMillis) : (ListenerUtil.mutListener.listen(24592) ? (endTimeMillis * startTimeMillis) : (ListenerUtil.mutListener.listen(24591) ? (endTimeMillis + startTimeMillis) : (endTimeMillis - startTimeMillis)))))) % 1000) : (ListenerUtil.mutListener.listen(24597) ? (((ListenerUtil.mutListener.listen(24594) ? (endTimeMillis % startTimeMillis) : (ListenerUtil.mutListener.listen(24593) ? (endTimeMillis / startTimeMillis) : (ListenerUtil.mutListener.listen(24592) ? (endTimeMillis * startTimeMillis) : (ListenerUtil.mutListener.listen(24591) ? (endTimeMillis + startTimeMillis) : (endTimeMillis - startTimeMillis)))))) * 1000) : (ListenerUtil.mutListener.listen(24596) ? (((ListenerUtil.mutListener.listen(24594) ? (endTimeMillis % startTimeMillis) : (ListenerUtil.mutListener.listen(24593) ? (endTimeMillis / startTimeMillis) : (ListenerUtil.mutListener.listen(24592) ? (endTimeMillis * startTimeMillis) : (ListenerUtil.mutListener.listen(24591) ? (endTimeMillis + startTimeMillis) : (endTimeMillis - startTimeMillis)))))) - 1000) : (ListenerUtil.mutListener.listen(24595) ? (((ListenerUtil.mutListener.listen(24594) ? (endTimeMillis % startTimeMillis) : (ListenerUtil.mutListener.listen(24593) ? (endTimeMillis / startTimeMillis) : (ListenerUtil.mutListener.listen(24592) ? (endTimeMillis * startTimeMillis) : (ListenerUtil.mutListener.listen(24591) ? (endTimeMillis + startTimeMillis) : (endTimeMillis - startTimeMillis)))))) + 1000) : (((ListenerUtil.mutListener.listen(24594) ? (endTimeMillis % startTimeMillis) : (ListenerUtil.mutListener.listen(24593) ? (endTimeMillis / startTimeMillis) : (ListenerUtil.mutListener.listen(24592) ? (endTimeMillis * startTimeMillis) : (ListenerUtil.mutListener.listen(24591) ? (endTimeMillis + startTimeMillis) : (endTimeMillis - startTimeMillis)))))) / 1000)))));
            long sizeKb = (ListenerUtil.mutListener.listen(24602) ? (sizeBytes % 1024) : (ListenerUtil.mutListener.listen(24601) ? (sizeBytes * 1024) : (ListenerUtil.mutListener.listen(24600) ? (sizeBytes - 1024) : (ListenerUtil.mutListener.listen(24599) ? (sizeBytes + 1024) : (sizeBytes / 1024)))));
            long speedKbSec = 0;
            if (!ListenerUtil.mutListener.listen(24621)) {
                if ((ListenerUtil.mutListener.listen(24607) ? (endTimeMillis >= startTimeMillis) : (ListenerUtil.mutListener.listen(24606) ? (endTimeMillis <= startTimeMillis) : (ListenerUtil.mutListener.listen(24605) ? (endTimeMillis > startTimeMillis) : (ListenerUtil.mutListener.listen(24604) ? (endTimeMillis < startTimeMillis) : (ListenerUtil.mutListener.listen(24603) ? (endTimeMillis == startTimeMillis) : (endTimeMillis != startTimeMillis))))))) {
                    if (!ListenerUtil.mutListener.listen(24620)) {
                        speedKbSec = (ListenerUtil.mutListener.listen(24619) ? ((ListenerUtil.mutListener.listen(24611) ? (sizeKb % 1000) : (ListenerUtil.mutListener.listen(24610) ? (sizeKb / 1000) : (ListenerUtil.mutListener.listen(24609) ? (sizeKb - 1000) : (ListenerUtil.mutListener.listen(24608) ? (sizeKb + 1000) : (sizeKb * 1000))))) % ((ListenerUtil.mutListener.listen(24615) ? (endTimeMillis % startTimeMillis) : (ListenerUtil.mutListener.listen(24614) ? (endTimeMillis / startTimeMillis) : (ListenerUtil.mutListener.listen(24613) ? (endTimeMillis * startTimeMillis) : (ListenerUtil.mutListener.listen(24612) ? (endTimeMillis + startTimeMillis) : (endTimeMillis - startTimeMillis))))))) : (ListenerUtil.mutListener.listen(24618) ? ((ListenerUtil.mutListener.listen(24611) ? (sizeKb % 1000) : (ListenerUtil.mutListener.listen(24610) ? (sizeKb / 1000) : (ListenerUtil.mutListener.listen(24609) ? (sizeKb - 1000) : (ListenerUtil.mutListener.listen(24608) ? (sizeKb + 1000) : (sizeKb * 1000))))) * ((ListenerUtil.mutListener.listen(24615) ? (endTimeMillis % startTimeMillis) : (ListenerUtil.mutListener.listen(24614) ? (endTimeMillis / startTimeMillis) : (ListenerUtil.mutListener.listen(24613) ? (endTimeMillis * startTimeMillis) : (ListenerUtil.mutListener.listen(24612) ? (endTimeMillis + startTimeMillis) : (endTimeMillis - startTimeMillis))))))) : (ListenerUtil.mutListener.listen(24617) ? ((ListenerUtil.mutListener.listen(24611) ? (sizeKb % 1000) : (ListenerUtil.mutListener.listen(24610) ? (sizeKb / 1000) : (ListenerUtil.mutListener.listen(24609) ? (sizeKb - 1000) : (ListenerUtil.mutListener.listen(24608) ? (sizeKb + 1000) : (sizeKb * 1000))))) - ((ListenerUtil.mutListener.listen(24615) ? (endTimeMillis % startTimeMillis) : (ListenerUtil.mutListener.listen(24614) ? (endTimeMillis / startTimeMillis) : (ListenerUtil.mutListener.listen(24613) ? (endTimeMillis * startTimeMillis) : (ListenerUtil.mutListener.listen(24612) ? (endTimeMillis + startTimeMillis) : (endTimeMillis - startTimeMillis))))))) : (ListenerUtil.mutListener.listen(24616) ? ((ListenerUtil.mutListener.listen(24611) ? (sizeKb % 1000) : (ListenerUtil.mutListener.listen(24610) ? (sizeKb / 1000) : (ListenerUtil.mutListener.listen(24609) ? (sizeKb - 1000) : (ListenerUtil.mutListener.listen(24608) ? (sizeKb + 1000) : (sizeKb * 1000))))) + ((ListenerUtil.mutListener.listen(24615) ? (endTimeMillis % startTimeMillis) : (ListenerUtil.mutListener.listen(24614) ? (endTimeMillis / startTimeMillis) : (ListenerUtil.mutListener.listen(24613) ? (endTimeMillis * startTimeMillis) : (ListenerUtil.mutListener.listen(24612) ? (endTimeMillis + startTimeMillis) : (endTimeMillis - startTimeMillis))))))) : ((ListenerUtil.mutListener.listen(24611) ? (sizeKb % 1000) : (ListenerUtil.mutListener.listen(24610) ? (sizeKb / 1000) : (ListenerUtil.mutListener.listen(24609) ? (sizeKb - 1000) : (ListenerUtil.mutListener.listen(24608) ? (sizeKb + 1000) : (sizeKb * 1000))))) / ((ListenerUtil.mutListener.listen(24615) ? (endTimeMillis % startTimeMillis) : (ListenerUtil.mutListener.listen(24614) ? (endTimeMillis / startTimeMillis) : (ListenerUtil.mutListener.listen(24613) ? (endTimeMillis * startTimeMillis) : (ListenerUtil.mutListener.listen(24612) ? (endTimeMillis + startTimeMillis) : (endTimeMillis - startTimeMillis)))))))))));
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(24622)) {
                Timber.d("Utils.writeToFile: Size: %d Kb, Duration: %d s, Speed: %d Kb/s", sizeKb, durationSeconds, speedKbSec);
            }
        } catch (IOException e) {
            throw new IOException(f.getName() + ": " + e.getLocalizedMessage(), e);
        }
    }

    /**
     * Indicates whether the specified action can be used as an intent. This method queries the package manager for
     * installed packages that can respond to an intent with the specified action. If no suitable package is found, this
     * method returns false.
     * @param context The application's environment.
     * @param action The Intent action to check for availability.
     * @return True if an Intent with the specified action can be sent and responded to, false otherwise.
     */
    public static boolean isIntentAvailable(Context context, String action) {
        return isIntentAvailable(context, action, null);
    }

    public static boolean isIntentAvailable(Context context, String action, ComponentName componentName) {
        final PackageManager packageManager = context.getPackageManager();
        final Intent intent = new Intent(action);
        if (!ListenerUtil.mutListener.listen(24623)) {
            intent.setComponent(componentName);
        }
        List<ResolveInfo> list = packageManager.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
        return (ListenerUtil.mutListener.listen(24628) ? (list.size() >= 0) : (ListenerUtil.mutListener.listen(24627) ? (list.size() <= 0) : (ListenerUtil.mutListener.listen(24626) ? (list.size() < 0) : (ListenerUtil.mutListener.listen(24625) ? (list.size() != 0) : (ListenerUtil.mutListener.listen(24624) ? (list.size() == 0) : (list.size() > 0))))));
    }

    /**
     * @param mediaDir media directory path on SD card
     * @return path converted to file URL, properly UTF-8 URL encoded
     */
    public static String getBaseUrl(String mediaDir) {
        if (!ListenerUtil.mutListener.listen(24635)) {
            // with existing slashes
            if ((ListenerUtil.mutListener.listen(24634) ? ((ListenerUtil.mutListener.listen(24633) ? (mediaDir.length() >= 0) : (ListenerUtil.mutListener.listen(24632) ? (mediaDir.length() <= 0) : (ListenerUtil.mutListener.listen(24631) ? (mediaDir.length() > 0) : (ListenerUtil.mutListener.listen(24630) ? (mediaDir.length() < 0) : (ListenerUtil.mutListener.listen(24629) ? (mediaDir.length() == 0) : (mediaDir.length() != 0)))))) || !"null".equalsIgnoreCase(mediaDir)) : ((ListenerUtil.mutListener.listen(24633) ? (mediaDir.length() >= 0) : (ListenerUtil.mutListener.listen(24632) ? (mediaDir.length() <= 0) : (ListenerUtil.mutListener.listen(24631) ? (mediaDir.length() > 0) : (ListenerUtil.mutListener.listen(24630) ? (mediaDir.length() < 0) : (ListenerUtil.mutListener.listen(24629) ? (mediaDir.length() == 0) : (mediaDir.length() != 0)))))) && !"null".equalsIgnoreCase(mediaDir)))) {
                Uri mediaDirUri = Uri.fromFile(new File(mediaDir));
                return mediaDirUri.toString() + "/";
            }
        }
        return "";
    }

    /**
     * Take an array of Long and return an array of long
     *
     * @param array The input with type Long[]
     * @return The output with type long[]
     */
    public static long[] toPrimitive(Collection<Long> array) {
        if (!ListenerUtil.mutListener.listen(24636)) {
            if (array == null) {
                return null;
            }
        }
        long[] results = new long[array.size()];
        int i = 0;
        if (!ListenerUtil.mutListener.listen(24638)) {
            {
                long _loopCounter655 = 0;
                for (Long item : array) {
                    ListenerUtil.loopListener.listen("_loopCounter655", ++_loopCounter655);
                    if (!ListenerUtil.mutListener.listen(24637)) {
                        results[i++] = item;
                    }
                }
            }
        }
        return results;
    }

    /**
     * Returns a String array with two elements:
     * 0 - file name
     * 1 - extension
     */
    public static String[] splitFilename(String filename) {
        String name = filename;
        String ext = "";
        int dotPosition = filename.lastIndexOf('.');
        if (!ListenerUtil.mutListener.listen(24646)) {
            if ((ListenerUtil.mutListener.listen(24643) ? (dotPosition >= -1) : (ListenerUtil.mutListener.listen(24642) ? (dotPosition <= -1) : (ListenerUtil.mutListener.listen(24641) ? (dotPosition > -1) : (ListenerUtil.mutListener.listen(24640) ? (dotPosition < -1) : (ListenerUtil.mutListener.listen(24639) ? (dotPosition == -1) : (dotPosition != -1))))))) {
                if (!ListenerUtil.mutListener.listen(24644)) {
                    name = filename.substring(0, dotPosition);
                }
                if (!ListenerUtil.mutListener.listen(24645)) {
                    ext = filename.substring(dotPosition);
                }
            }
        }
        return new String[] { name, ext };
    }

    /**
     * Returns a list of files for the installed custom fonts.
     */
    public static List<AnkiFont> getCustomFonts(Context context) {
        String deckPath = CollectionHelper.getCurrentAnkiDroidDirectory(context);
        String fontsPath = deckPath + "/fonts/";
        File fontsDir = new File(fontsPath);
        int fontsCount = 0;
        File[] fontsList = null;
        if (!ListenerUtil.mutListener.listen(24650)) {
            if ((ListenerUtil.mutListener.listen(24647) ? (fontsDir.exists() || fontsDir.isDirectory()) : (fontsDir.exists() && fontsDir.isDirectory()))) {
                if (!ListenerUtil.mutListener.listen(24648)) {
                    fontsCount = fontsDir.listFiles().length;
                }
                if (!ListenerUtil.mutListener.listen(24649)) {
                    fontsList = fontsDir.listFiles();
                }
            }
        }
        String[] ankiDroidFonts = null;
        try {
            if (!ListenerUtil.mutListener.listen(24652)) {
                ankiDroidFonts = context.getAssets().list("fonts");
            }
        } catch (IOException e) {
            if (!ListenerUtil.mutListener.listen(24651)) {
                Timber.e(e, "Error on retrieving ankidroid fonts");
            }
        }
        List<AnkiFont> fonts = new ArrayList<>(fontsCount);
        if (!ListenerUtil.mutListener.listen(24662)) {
            {
                long _loopCounter657 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(24661) ? (i >= fontsCount) : (ListenerUtil.mutListener.listen(24660) ? (i <= fontsCount) : (ListenerUtil.mutListener.listen(24659) ? (i > fontsCount) : (ListenerUtil.mutListener.listen(24658) ? (i != fontsCount) : (ListenerUtil.mutListener.listen(24657) ? (i == fontsCount) : (i < fontsCount)))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter657", ++_loopCounter657);
                    String filePath = fontsList[i].getAbsolutePath();
                    String filePathExtension = splitFilename(filePath)[1];
                    if (!ListenerUtil.mutListener.listen(24656)) {
                        {
                            long _loopCounter656 = 0;
                            for (String fontExtension : FONT_FILE_EXTENSIONS) {
                                ListenerUtil.loopListener.listen("_loopCounter656", ++_loopCounter656);
                                if (!ListenerUtil.mutListener.listen(24655)) {
                                    // Go through the list of allowed extensions.
                                    if (filePathExtension.equalsIgnoreCase(fontExtension)) {
                                        // This looks like a font file.
                                        AnkiFont font = AnkiFont.createAnkiFont(context, filePath, false);
                                        if (!ListenerUtil.mutListener.listen(24654)) {
                                            if (font != null) {
                                                if (!ListenerUtil.mutListener.listen(24653)) {
                                                    fonts.add(font);
                                                }
                                            }
                                        }
                                        // No need to look for other file extensions.
                                        break;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(24666)) {
            if (ankiDroidFonts != null) {
                if (!ListenerUtil.mutListener.listen(24665)) {
                    {
                        long _loopCounter658 = 0;
                        for (String ankiDroidFont : ankiDroidFonts) {
                            ListenerUtil.loopListener.listen("_loopCounter658", ++_loopCounter658);
                            // Assume all files in the assets directory are actually fonts.
                            AnkiFont font = AnkiFont.createAnkiFont(context, ankiDroidFont, true);
                            if (!ListenerUtil.mutListener.listen(24664)) {
                                if (font != null) {
                                    if (!ListenerUtil.mutListener.listen(24663)) {
                                        fonts.add(font);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return fonts;
    }

    /**
     * Returns a list of apkg-files.
     */
    public static List<File> getImportableDecks(Context context) {
        String deckPath = CollectionHelper.getCurrentAnkiDroidDirectory(context);
        File dir = new File(deckPath);
        List<File> decks = new ArrayList<>();
        if (!ListenerUtil.mutListener.listen(24669)) {
            if ((ListenerUtil.mutListener.listen(24667) ? (dir.exists() || dir.isDirectory()) : (dir.exists() && dir.isDirectory()))) {
                File[] deckList = dir.listFiles(pathname -> pathname.isFile() && ImportUtils.isValidPackageName(pathname.getName()));
                if (!ListenerUtil.mutListener.listen(24668)) {
                    decks.addAll(Arrays.asList(deckList).subList(0, deckList.length));
                }
            }
        }
        return decks;
    }

    /**
     * Simply copy a file to another location
     * @param sourceFile The source file
     * @param destFile The destination file, doesn't need to exist yet.
     */
    public static void copyFile(File sourceFile, File destFile) throws IOException {
        try (FileInputStream source = new FileInputStream(sourceFile)) {
            if (!ListenerUtil.mutListener.listen(24670)) {
                writeToFile(source, destFile.getAbsolutePath());
            }
        }
    }

    /**
     * Like org.json.JSONObject except that it doesn't escape forward slashes
     * The necessity for this method is due to python's 2.7 json.dumps() function that doesn't escape character '/'.
     * The org.json.JSONObject parser accepts both escaped and unescaped forward slashes, so we only need to worry for
     * our output, when we write to the database or syncing.
     *
     * @param json a json object to serialize
     * @return the json serialization of the object
     * @see org.json.JSONObject#toString()
     */
    public static String jsonToString(JSONObject json) {
        return json.toString().replaceAll("\\\\/", "/");
    }

    /**
     * Like org.json.JSONArray except that it doesn't escape forward slashes
     * The necessity for this method is due to python's 2.7 json.dumps() function that doesn't escape character '/'.
     * The org.json.JSONArray parser accepts both escaped and unescaped forward slashes, so we only need to worry for
     * our output, when we write to the database or syncing.
     *
     * @param json a json object to serialize
     * @return the json serialization of the object
     * @see org.json.JSONArray#toString()
     */
    public static String jsonToString(JSONArray json) {
        return json.toString().replaceAll("\\\\/", "/");
    }

    /**
     * @return A description of the device, including the model and android version. No commas are present in the
     * returned string.
     */
    public static String platDesc() {
        // AnkiWeb reads this string and uses , and : as delimiters, so we remove them.
        String model = android.os.Build.MODEL.replace(',', ' ').replace(':', ' ');
        return String.format(Locale.US, "android:%s:%s", android.os.Build.VERSION.RELEASE, model);
    }

    /*
     *  Return the input string in the Unicode normalized form. This helps with text comparisons, for example a ü
     *  stored as u plus the dots but typed as a single character compare as the same.
     *
     * @param txt Text to be normalized
     * @return The input text in its NFC normalized form form.
    */
    public static String nfcNormalized(String txt) {
        if (!ListenerUtil.mutListener.listen(24671)) {
            if (!Normalizer.isNormalized(txt, Normalizer.Form.NFC)) {
                return Normalizer.normalize(txt, Normalizer.Form.NFC);
            }
        }
        return txt;
    }

    /**
     * Unescapes all sequences within the given string of text, interpreting them as HTML escaped characters.
     * <p/>
     * Not that this code strips any HTML tags untouched, so if the text contains any HTML tags, they will be ignored.
     *
     * @param htmlText the text to convert
     * @return the unescaped text
     */
    public static String unescape(String htmlText) {
        return HtmlCompat.fromHtml(htmlText, HtmlCompat.FROM_HTML_MODE_LEGACY).toString();
    }

    /**
     * Return a random float within the range of min and max.
     */
    public static float randomFloatInRange(float min, float max) {
        Random rand = new Random();
        return (ListenerUtil.mutListener.listen(24683) ? ((ListenerUtil.mutListener.listen(24679) ? (rand.nextFloat() % ((ListenerUtil.mutListener.listen(24675) ? (max % min) : (ListenerUtil.mutListener.listen(24674) ? (max / min) : (ListenerUtil.mutListener.listen(24673) ? (max * min) : (ListenerUtil.mutListener.listen(24672) ? (max + min) : (max - min))))))) : (ListenerUtil.mutListener.listen(24678) ? (rand.nextFloat() / ((ListenerUtil.mutListener.listen(24675) ? (max % min) : (ListenerUtil.mutListener.listen(24674) ? (max / min) : (ListenerUtil.mutListener.listen(24673) ? (max * min) : (ListenerUtil.mutListener.listen(24672) ? (max + min) : (max - min))))))) : (ListenerUtil.mutListener.listen(24677) ? (rand.nextFloat() - ((ListenerUtil.mutListener.listen(24675) ? (max % min) : (ListenerUtil.mutListener.listen(24674) ? (max / min) : (ListenerUtil.mutListener.listen(24673) ? (max * min) : (ListenerUtil.mutListener.listen(24672) ? (max + min) : (max - min))))))) : (ListenerUtil.mutListener.listen(24676) ? (rand.nextFloat() + ((ListenerUtil.mutListener.listen(24675) ? (max % min) : (ListenerUtil.mutListener.listen(24674) ? (max / min) : (ListenerUtil.mutListener.listen(24673) ? (max * min) : (ListenerUtil.mutListener.listen(24672) ? (max + min) : (max - min))))))) : (rand.nextFloat() * ((ListenerUtil.mutListener.listen(24675) ? (max % min) : (ListenerUtil.mutListener.listen(24674) ? (max / min) : (ListenerUtil.mutListener.listen(24673) ? (max * min) : (ListenerUtil.mutListener.listen(24672) ? (max + min) : (max - min))))))))))) % min) : (ListenerUtil.mutListener.listen(24682) ? ((ListenerUtil.mutListener.listen(24679) ? (rand.nextFloat() % ((ListenerUtil.mutListener.listen(24675) ? (max % min) : (ListenerUtil.mutListener.listen(24674) ? (max / min) : (ListenerUtil.mutListener.listen(24673) ? (max * min) : (ListenerUtil.mutListener.listen(24672) ? (max + min) : (max - min))))))) : (ListenerUtil.mutListener.listen(24678) ? (rand.nextFloat() / ((ListenerUtil.mutListener.listen(24675) ? (max % min) : (ListenerUtil.mutListener.listen(24674) ? (max / min) : (ListenerUtil.mutListener.listen(24673) ? (max * min) : (ListenerUtil.mutListener.listen(24672) ? (max + min) : (max - min))))))) : (ListenerUtil.mutListener.listen(24677) ? (rand.nextFloat() - ((ListenerUtil.mutListener.listen(24675) ? (max % min) : (ListenerUtil.mutListener.listen(24674) ? (max / min) : (ListenerUtil.mutListener.listen(24673) ? (max * min) : (ListenerUtil.mutListener.listen(24672) ? (max + min) : (max - min))))))) : (ListenerUtil.mutListener.listen(24676) ? (rand.nextFloat() + ((ListenerUtil.mutListener.listen(24675) ? (max % min) : (ListenerUtil.mutListener.listen(24674) ? (max / min) : (ListenerUtil.mutListener.listen(24673) ? (max * min) : (ListenerUtil.mutListener.listen(24672) ? (max + min) : (max - min))))))) : (rand.nextFloat() * ((ListenerUtil.mutListener.listen(24675) ? (max % min) : (ListenerUtil.mutListener.listen(24674) ? (max / min) : (ListenerUtil.mutListener.listen(24673) ? (max * min) : (ListenerUtil.mutListener.listen(24672) ? (max + min) : (max - min))))))))))) / min) : (ListenerUtil.mutListener.listen(24681) ? ((ListenerUtil.mutListener.listen(24679) ? (rand.nextFloat() % ((ListenerUtil.mutListener.listen(24675) ? (max % min) : (ListenerUtil.mutListener.listen(24674) ? (max / min) : (ListenerUtil.mutListener.listen(24673) ? (max * min) : (ListenerUtil.mutListener.listen(24672) ? (max + min) : (max - min))))))) : (ListenerUtil.mutListener.listen(24678) ? (rand.nextFloat() / ((ListenerUtil.mutListener.listen(24675) ? (max % min) : (ListenerUtil.mutListener.listen(24674) ? (max / min) : (ListenerUtil.mutListener.listen(24673) ? (max * min) : (ListenerUtil.mutListener.listen(24672) ? (max + min) : (max - min))))))) : (ListenerUtil.mutListener.listen(24677) ? (rand.nextFloat() - ((ListenerUtil.mutListener.listen(24675) ? (max % min) : (ListenerUtil.mutListener.listen(24674) ? (max / min) : (ListenerUtil.mutListener.listen(24673) ? (max * min) : (ListenerUtil.mutListener.listen(24672) ? (max + min) : (max - min))))))) : (ListenerUtil.mutListener.listen(24676) ? (rand.nextFloat() + ((ListenerUtil.mutListener.listen(24675) ? (max % min) : (ListenerUtil.mutListener.listen(24674) ? (max / min) : (ListenerUtil.mutListener.listen(24673) ? (max * min) : (ListenerUtil.mutListener.listen(24672) ? (max + min) : (max - min))))))) : (rand.nextFloat() * ((ListenerUtil.mutListener.listen(24675) ? (max % min) : (ListenerUtil.mutListener.listen(24674) ? (max / min) : (ListenerUtil.mutListener.listen(24673) ? (max * min) : (ListenerUtil.mutListener.listen(24672) ? (max + min) : (max - min))))))))))) * min) : (ListenerUtil.mutListener.listen(24680) ? ((ListenerUtil.mutListener.listen(24679) ? (rand.nextFloat() % ((ListenerUtil.mutListener.listen(24675) ? (max % min) : (ListenerUtil.mutListener.listen(24674) ? (max / min) : (ListenerUtil.mutListener.listen(24673) ? (max * min) : (ListenerUtil.mutListener.listen(24672) ? (max + min) : (max - min))))))) : (ListenerUtil.mutListener.listen(24678) ? (rand.nextFloat() / ((ListenerUtil.mutListener.listen(24675) ? (max % min) : (ListenerUtil.mutListener.listen(24674) ? (max / min) : (ListenerUtil.mutListener.listen(24673) ? (max * min) : (ListenerUtil.mutListener.listen(24672) ? (max + min) : (max - min))))))) : (ListenerUtil.mutListener.listen(24677) ? (rand.nextFloat() - ((ListenerUtil.mutListener.listen(24675) ? (max % min) : (ListenerUtil.mutListener.listen(24674) ? (max / min) : (ListenerUtil.mutListener.listen(24673) ? (max * min) : (ListenerUtil.mutListener.listen(24672) ? (max + min) : (max - min))))))) : (ListenerUtil.mutListener.listen(24676) ? (rand.nextFloat() + ((ListenerUtil.mutListener.listen(24675) ? (max % min) : (ListenerUtil.mutListener.listen(24674) ? (max / min) : (ListenerUtil.mutListener.listen(24673) ? (max * min) : (ListenerUtil.mutListener.listen(24672) ? (max + min) : (max - min))))))) : (rand.nextFloat() * ((ListenerUtil.mutListener.listen(24675) ? (max % min) : (ListenerUtil.mutListener.listen(24674) ? (max / min) : (ListenerUtil.mutListener.listen(24673) ? (max * min) : (ListenerUtil.mutListener.listen(24672) ? (max + min) : (max - min))))))))))) - min) : ((ListenerUtil.mutListener.listen(24679) ? (rand.nextFloat() % ((ListenerUtil.mutListener.listen(24675) ? (max % min) : (ListenerUtil.mutListener.listen(24674) ? (max / min) : (ListenerUtil.mutListener.listen(24673) ? (max * min) : (ListenerUtil.mutListener.listen(24672) ? (max + min) : (max - min))))))) : (ListenerUtil.mutListener.listen(24678) ? (rand.nextFloat() / ((ListenerUtil.mutListener.listen(24675) ? (max % min) : (ListenerUtil.mutListener.listen(24674) ? (max / min) : (ListenerUtil.mutListener.listen(24673) ? (max * min) : (ListenerUtil.mutListener.listen(24672) ? (max + min) : (max - min))))))) : (ListenerUtil.mutListener.listen(24677) ? (rand.nextFloat() - ((ListenerUtil.mutListener.listen(24675) ? (max % min) : (ListenerUtil.mutListener.listen(24674) ? (max / min) : (ListenerUtil.mutListener.listen(24673) ? (max * min) : (ListenerUtil.mutListener.listen(24672) ? (max + min) : (max - min))))))) : (ListenerUtil.mutListener.listen(24676) ? (rand.nextFloat() + ((ListenerUtil.mutListener.listen(24675) ? (max % min) : (ListenerUtil.mutListener.listen(24674) ? (max / min) : (ListenerUtil.mutListener.listen(24673) ? (max * min) : (ListenerUtil.mutListener.listen(24672) ? (max + min) : (max - min))))))) : (rand.nextFloat() * ((ListenerUtil.mutListener.listen(24675) ? (max % min) : (ListenerUtil.mutListener.listen(24674) ? (max / min) : (ListenerUtil.mutListener.listen(24673) ? (max * min) : (ListenerUtil.mutListener.listen(24672) ? (max + min) : (max - min))))))))))) + min)))));
    }

    /**
     *       Set usn to 0 in every object.
     *
     *       This method is called during full sync, before uploading, so
     *       during an instant, the value will be zero while the object is
     *       not actually online. This is not a problem because if the sync
     *       fails, a full sync will occur again next time.
     *
     *       @return whether there was a non-zero usn; in this case the list
     *       should be saved before the upload.
     */
    public static boolean markAsUploaded(ArrayList<? extends JSONObject> ar) {
        boolean changed = false;
        if (!ListenerUtil.mutListener.listen(24687)) {
            {
                long _loopCounter659 = 0;
                for (JSONObject obj : ar) {
                    ListenerUtil.loopListener.listen("_loopCounter659", ++_loopCounter659);
                    if (!ListenerUtil.mutListener.listen(24686)) {
                        if (obj.optInt("usn", 1) != 0) {
                            if (!ListenerUtil.mutListener.listen(24684)) {
                                obj.put("usn", 0);
                            }
                            if (!ListenerUtil.mutListener.listen(24685)) {
                                changed = true;
                            }
                        }
                    }
                }
            }
        }
        return changed;
    }

    // Similar as Objects.equals. So deprecated starting at API Level 19 where this methods exists.
    public static <T> boolean equals(@Nullable T left, @Nullable T right) {
        // noinspection EqualsReplaceableByObjectsCall
        return (ListenerUtil.mutListener.listen(24689) ? (left == right && ((ListenerUtil.mutListener.listen(24688) ? (left != null || left.equals(right)) : (left != null && left.equals(right))))) : (left == right || ((ListenerUtil.mutListener.listen(24688) ? (left != null || left.equals(right)) : (left != null && left.equals(right))))));
    }

    /**
     * @param sflds Some fields
     * @return Array with the same elements, trimmed
     */
    @NonNull
    public static String[] trimArray(@NonNull String[] sflds) {
        int nbField = sflds.length;
        String[] fields = new String[nbField];
        if (!ListenerUtil.mutListener.listen(24696)) {
            {
                long _loopCounter660 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(24695) ? (i >= nbField) : (ListenerUtil.mutListener.listen(24694) ? (i <= nbField) : (ListenerUtil.mutListener.listen(24693) ? (i > nbField) : (ListenerUtil.mutListener.listen(24692) ? (i != nbField) : (ListenerUtil.mutListener.listen(24691) ? (i == nbField) : (i < nbField)))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter660", ++_loopCounter660);
                    if (!ListenerUtil.mutListener.listen(24690)) {
                        fields[i] = sflds[i].trim();
                    }
                }
            }
        }
        return fields;
    }

    /**
     * @param fields A map from field name to field value
     * @return The set of non empty field values.
     */
    public static Set<String> nonEmptyFields(Map<String, String> fields) {
        Set<String> nonempty_fields = new HashSet<>(fields.size());
        if (!ListenerUtil.mutListener.listen(24700)) {
            {
                long _loopCounter661 = 0;
                for (Map.Entry<String, String> kv : fields.entrySet()) {
                    ListenerUtil.loopListener.listen("_loopCounter661", ++_loopCounter661);
                    String value = kv.getValue();
                    if (!ListenerUtil.mutListener.listen(24697)) {
                        value = Utils.stripHTMLMedia(value).trim();
                    }
                    if (!ListenerUtil.mutListener.listen(24699)) {
                        if (!TextUtils.isEmpty(value)) {
                            if (!ListenerUtil.mutListener.listen(24698)) {
                                nonempty_fields.add(kv.getKey());
                            }
                        }
                    }
                }
            }
        }
        return nonempty_fields;
    }
}
