/* Copyright (C) 2018 Erik Johansson <erik@ejohansson.se>
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

import com.health.openscale.core.datatypes.ScaleMeasurement;
import com.j256.simplecsv.converter.DateConverter;
import com.j256.simplecsv.processor.ColumnInfo;
import com.j256.simplecsv.processor.ColumnNameMatcher;
import com.j256.simplecsv.processor.CsvProcessor;
import com.j256.simplecsv.processor.ParseError;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.Writer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class CsvHelper {

    public static void exportTo(Writer writer, List<ScaleMeasurement> measurements) throws IOException {
        CsvProcessor<ScaleMeasurement> csvProcessor = new CsvProcessor<>(ScaleMeasurement.class);
        if (!ListenerUtil.mutListener.listen(5682)) {
            csvProcessor.writeAll(writer, measurements, true);
        }
    }

    private static String[] getOldStyleHeaders(String sampleLine) {
        if (!ListenerUtil.mutListener.listen(5683)) {
            if (sampleLine == null) {
                return null;
            }
        }
        final String[] fields = sampleLine.split(",", -1);
        if (!ListenerUtil.mutListener.listen(5709)) {
            // Return an array with header fields that match the guessed version.
            if ((ListenerUtil.mutListener.listen(5688) ? (fields.length >= 10) : (ListenerUtil.mutListener.listen(5687) ? (fields.length <= 10) : (ListenerUtil.mutListener.listen(5686) ? (fields.length > 10) : (ListenerUtil.mutListener.listen(5685) ? (fields.length < 10) : (ListenerUtil.mutListener.listen(5684) ? (fields.length != 10) : (fields.length == 10))))))) {
                // From version 1.6 up to 1.7
                return new String[] { "dateTime", "weight", "fat", "water", "muscle", "lbm", "bone", "waist", "hip", "comment" };
            } else if ((ListenerUtil.mutListener.listen(5693) ? (fields.length >= 9) : (ListenerUtil.mutListener.listen(5692) ? (fields.length <= 9) : (ListenerUtil.mutListener.listen(5691) ? (fields.length > 9) : (ListenerUtil.mutListener.listen(5690) ? (fields.length < 9) : (ListenerUtil.mutListener.listen(5689) ? (fields.length != 9) : (fields.length == 9))))))) {
                // From version 1.5.5
                return new String[] { "dateTime", "weight", "fat", "water", "muscle", "bone", "waist", "hip", "comment" };
            } else if ((ListenerUtil.mutListener.listen(5698) ? (fields.length >= 8) : (ListenerUtil.mutListener.listen(5697) ? (fields.length <= 8) : (ListenerUtil.mutListener.listen(5696) ? (fields.length > 8) : (ListenerUtil.mutListener.listen(5695) ? (fields.length < 8) : (ListenerUtil.mutListener.listen(5694) ? (fields.length != 8) : (fields.length == 8))))))) {
                // From version 1.3
                return new String[] { "dateTime", "weight", "fat", "water", "muscle", "waist", "hip", "comment" };
            } else if ((ListenerUtil.mutListener.listen(5703) ? (fields.length >= 6) : (ListenerUtil.mutListener.listen(5702) ? (fields.length <= 6) : (ListenerUtil.mutListener.listen(5701) ? (fields.length > 6) : (ListenerUtil.mutListener.listen(5700) ? (fields.length < 6) : (ListenerUtil.mutListener.listen(5699) ? (fields.length != 6) : (fields.length == 6))))))) {
                // From version 1.2
                return new String[] { "dateTime", "weight", "fat", "water", "muscle", "comment" };
            } else if ((ListenerUtil.mutListener.listen(5708) ? (fields.length >= 5) : (ListenerUtil.mutListener.listen(5707) ? (fields.length <= 5) : (ListenerUtil.mutListener.listen(5706) ? (fields.length > 5) : (ListenerUtil.mutListener.listen(5705) ? (fields.length < 5) : (ListenerUtil.mutListener.listen(5704) ? (fields.length != 5) : (fields.length == 5))))))) {
                // From version 1.0
                return new String[] { "dateTime", "weight", "fat", "water", "comment" };
            }
        }
        // Unknown input data format
        return null;
    }

    public static List<ScaleMeasurement> importFrom(BufferedReader reader) throws IOException, ParseException {
        CsvProcessor<ScaleMeasurement> csvProcessor = new CsvProcessor<>(ScaleMeasurement.class).withHeaderValidation(true).withFlexibleOrder(true).withAlwaysTrimInput(true).withAllowPartialLines(true);
        if (!ListenerUtil.mutListener.listen(5712)) {
            csvProcessor.setColumnNameMatcher(new ColumnNameMatcher() {

                @Override
                public boolean matchesColumnName(String definitionName, String csvName) {
                    return (ListenerUtil.mutListener.listen(5711) ? (definitionName.equals(csvName) && ((ListenerUtil.mutListener.listen(5710) ? (definitionName.equals("lbm") || csvName.equals("lbw")) : (definitionName.equals("lbm") && csvName.equals("lbw"))))) : (definitionName.equals(csvName) || ((ListenerUtil.mutListener.listen(5710) ? (definitionName.equals("lbm") || csvName.equals("lbw")) : (definitionName.equals("lbm") && csvName.equals("lbw"))))));
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(5713)) {
            reader.mark(1000);
        }
        try {
            if (!ListenerUtil.mutListener.listen(5718)) {
                csvProcessor.readHeader(reader, null);
            }
        } catch (ParseException ex) {
            if (!ListenerUtil.mutListener.listen(5714)) {
                // Try to import it as an old style CSV export
                reader.reset();
            }
            final String sampleLine = reader.readLine();
            if (!ListenerUtil.mutListener.listen(5715)) {
                reader.reset();
            }
            final String[] header = getOldStyleHeaders(sampleLine);
            if (!ListenerUtil.mutListener.listen(5716)) {
                if (header == null) {
                    // Don't know what to do with this, let Simple CSV error out
                    return csvProcessor.readAll(reader, null);
                }
            }
            if (!ListenerUtil.mutListener.listen(5717)) {
                csvProcessor.validateHeaderColumns(header, null);
            }
        }
        return csvProcessor.readRows(reader, null);
    }

    // backward compatible for openScale version >= 2.1.2 to support old date format dd.MM.yyyy, see issue #506
    public static class DateTimeConverter extends DateConverter {

        private static final SimpleDateFormat srcDateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm");

        private static final SimpleDateFormat dstDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

        @Override
        public Date stringToJava(String line, int lineNumber, int linePos, ColumnInfo<Date> columnInfo, String value, ParseError parseError) throws ParseException {
            try {
                Date srcDate = srcDateFormat.parse(value);
                if (!ListenerUtil.mutListener.listen(5719)) {
                    value = dstDateFormat.format(srcDate);
                }
            } catch (ParseException ex) {
            }
            return super.stringToJava(line, lineNumber, linePos, columnInfo, value, parseError);
        }
    }
}
