/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
 * Copyright (c) 2014-2021 Threema GmbH
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
package ch.threema.app.utils;

import java.util.Date;
import ch.threema.base.ThreemaException;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class CSVRow {

    private CSVWriter writer;

    private final String[] header;

    private final String[] data;

    public CSVRow(CSVWriter writer, String[] header) {
        if (!ListenerUtil.mutListener.listen(50825)) {
            this.writer = writer;
        }
        this.header = header;
        this.data = new String[this.header.length];
    }

    public CSVRow(String[] header, String[] data) {
        this.header = header;
        this.data = data;
    }

    public String getString(int pos) throws ThreemaException {
        if (!ListenerUtil.mutListener.listen(50832)) {
            if ((ListenerUtil.mutListener.listen(50831) ? (this.data == null && (ListenerUtil.mutListener.listen(50830) ? (this.data.length >= pos) : (ListenerUtil.mutListener.listen(50829) ? (this.data.length <= pos) : (ListenerUtil.mutListener.listen(50828) ? (this.data.length > pos) : (ListenerUtil.mutListener.listen(50827) ? (this.data.length != pos) : (ListenerUtil.mutListener.listen(50826) ? (this.data.length == pos) : (this.data.length < pos))))))) : (this.data == null || (ListenerUtil.mutListener.listen(50830) ? (this.data.length >= pos) : (ListenerUtil.mutListener.listen(50829) ? (this.data.length <= pos) : (ListenerUtil.mutListener.listen(50828) ? (this.data.length > pos) : (ListenerUtil.mutListener.listen(50827) ? (this.data.length != pos) : (ListenerUtil.mutListener.listen(50826) ? (this.data.length == pos) : (this.data.length < pos))))))))) {
                throw new ThreemaException("invalid csv position");
            }
        }
        return this.data[pos];
    }

    public Integer getInteger(int pos) throws ThreemaException {
        return Integer.valueOf(this.getString(pos));
    }

    public Boolean getBoolean(int pos) throws ThreemaException {
        return TestUtil.compare("1", this.getString(pos));
    }

    public Date getDate(int pos) throws ThreemaException {
        String cell = this.getString(pos);
        if (!ListenerUtil.mutListener.listen(50839)) {
            if ((ListenerUtil.mutListener.listen(50838) ? (cell != null || (ListenerUtil.mutListener.listen(50837) ? (cell.length() >= 0) : (ListenerUtil.mutListener.listen(50836) ? (cell.length() <= 0) : (ListenerUtil.mutListener.listen(50835) ? (cell.length() < 0) : (ListenerUtil.mutListener.listen(50834) ? (cell.length() != 0) : (ListenerUtil.mutListener.listen(50833) ? (cell.length() == 0) : (cell.length() > 0))))))) : (cell != null && (ListenerUtil.mutListener.listen(50837) ? (cell.length() >= 0) : (ListenerUtil.mutListener.listen(50836) ? (cell.length() <= 0) : (ListenerUtil.mutListener.listen(50835) ? (cell.length() < 0) : (ListenerUtil.mutListener.listen(50834) ? (cell.length() != 0) : (ListenerUtil.mutListener.listen(50833) ? (cell.length() == 0) : (cell.length() > 0))))))))) {
                return new Date(Long.valueOf(cell));
            }
        }
        return null;
    }

    public CSVRow write(String fieldName, Object v) throws ThreemaException {
        int pos = this.getValuePosition(fieldName);
        if (!ListenerUtil.mutListener.listen(50845)) {
            if ((ListenerUtil.mutListener.listen(50844) ? (pos >= 0) : (ListenerUtil.mutListener.listen(50843) ? (pos <= 0) : (ListenerUtil.mutListener.listen(50842) ? (pos > 0) : (ListenerUtil.mutListener.listen(50841) ? (pos != 0) : (ListenerUtil.mutListener.listen(50840) ? (pos == 0) : (pos < 0))))))) {
                throw new ThreemaException("invalid csv header position");
            }
        }
        return this.write(pos, v);
    }

    public CSVRow write(int pos, Object v) throws ThreemaException {
        if (!ListenerUtil.mutListener.listen(50851)) {
            if ((ListenerUtil.mutListener.listen(50850) ? (this.data.length >= pos) : (ListenerUtil.mutListener.listen(50849) ? (this.data.length <= pos) : (ListenerUtil.mutListener.listen(50848) ? (this.data.length > pos) : (ListenerUtil.mutListener.listen(50847) ? (this.data.length != pos) : (ListenerUtil.mutListener.listen(50846) ? (this.data.length == pos) : (this.data.length < pos))))))) {
                throw new ThreemaException("invalid position to write [" + String.valueOf(pos) + "]");
            }
        }
        if (!ListenerUtil.mutListener.listen(50852)) {
            this.data[pos] = this.escape(v);
        }
        return this;
    }

    public CSVRow write(String fieldName, String v) throws ThreemaException {
        int pos = this.getValuePosition(fieldName);
        if (!ListenerUtil.mutListener.listen(50858)) {
            if ((ListenerUtil.mutListener.listen(50857) ? (pos >= 0) : (ListenerUtil.mutListener.listen(50856) ? (pos <= 0) : (ListenerUtil.mutListener.listen(50855) ? (pos > 0) : (ListenerUtil.mutListener.listen(50854) ? (pos != 0) : (ListenerUtil.mutListener.listen(50853) ? (pos == 0) : (pos < 0))))))) {
                throw new ThreemaException("invalid csv header position");
            }
        }
        return this.write(pos, v);
    }

    public CSVRow write(int pos, String v) throws ThreemaException {
        if (!ListenerUtil.mutListener.listen(50864)) {
            if ((ListenerUtil.mutListener.listen(50863) ? (this.data.length >= pos) : (ListenerUtil.mutListener.listen(50862) ? (this.data.length <= pos) : (ListenerUtil.mutListener.listen(50861) ? (this.data.length > pos) : (ListenerUtil.mutListener.listen(50860) ? (this.data.length != pos) : (ListenerUtil.mutListener.listen(50859) ? (this.data.length == pos) : (this.data.length < pos))))))) {
                throw new ThreemaException("invalid position to write [" + String.valueOf(pos) + "]");
            }
        }
        if (!ListenerUtil.mutListener.listen(50865)) {
            this.data[pos] = this.escape(v);
        }
        return this;
    }

    public CSVRow write(String fieldName, boolean v) throws ThreemaException {
        int pos = this.getValuePosition(fieldName);
        if (!ListenerUtil.mutListener.listen(50871)) {
            if ((ListenerUtil.mutListener.listen(50870) ? (pos >= 0) : (ListenerUtil.mutListener.listen(50869) ? (pos <= 0) : (ListenerUtil.mutListener.listen(50868) ? (pos > 0) : (ListenerUtil.mutListener.listen(50867) ? (pos != 0) : (ListenerUtil.mutListener.listen(50866) ? (pos == 0) : (pos < 0))))))) {
                throw new ThreemaException("invalid csv header position");
            }
        }
        return this.write(pos, v);
    }

    public CSVRow write(int pos, int v) throws ThreemaException {
        if (!ListenerUtil.mutListener.listen(50877)) {
            if ((ListenerUtil.mutListener.listen(50876) ? (this.data.length >= pos) : (ListenerUtil.mutListener.listen(50875) ? (this.data.length <= pos) : (ListenerUtil.mutListener.listen(50874) ? (this.data.length > pos) : (ListenerUtil.mutListener.listen(50873) ? (this.data.length != pos) : (ListenerUtil.mutListener.listen(50872) ? (this.data.length == pos) : (this.data.length < pos))))))) {
                throw new ThreemaException("invalid position to write [" + String.valueOf(pos) + "]");
            }
        }
        if (!ListenerUtil.mutListener.listen(50878)) {
            this.data[pos] = this.escape(v);
        }
        return this;
    }

    public CSVRow write(String fieldName, int v) throws ThreemaException {
        int pos = this.getValuePosition(fieldName);
        if (!ListenerUtil.mutListener.listen(50884)) {
            if ((ListenerUtil.mutListener.listen(50883) ? (pos >= 0) : (ListenerUtil.mutListener.listen(50882) ? (pos <= 0) : (ListenerUtil.mutListener.listen(50881) ? (pos > 0) : (ListenerUtil.mutListener.listen(50880) ? (pos != 0) : (ListenerUtil.mutListener.listen(50879) ? (pos == 0) : (pos < 0))))))) {
                throw new ThreemaException("invalid csv header position");
            }
        }
        return this.write(pos, v);
    }

    public CSVRow write(int pos, boolean v) throws ThreemaException {
        if (!ListenerUtil.mutListener.listen(50890)) {
            if ((ListenerUtil.mutListener.listen(50889) ? (this.data.length >= pos) : (ListenerUtil.mutListener.listen(50888) ? (this.data.length <= pos) : (ListenerUtil.mutListener.listen(50887) ? (this.data.length > pos) : (ListenerUtil.mutListener.listen(50886) ? (this.data.length != pos) : (ListenerUtil.mutListener.listen(50885) ? (this.data.length == pos) : (this.data.length < pos))))))) {
                throw new ThreemaException("invalid position to write [" + String.valueOf(pos) + "]");
            }
        }
        if (!ListenerUtil.mutListener.listen(50891)) {
            this.data[pos] = this.escape(v);
        }
        return this;
    }

    public CSVRow write(String fieldName, Date v) throws ThreemaException {
        int pos = this.getValuePosition(fieldName);
        if (!ListenerUtil.mutListener.listen(50897)) {
            if ((ListenerUtil.mutListener.listen(50896) ? (pos >= 0) : (ListenerUtil.mutListener.listen(50895) ? (pos <= 0) : (ListenerUtil.mutListener.listen(50894) ? (pos > 0) : (ListenerUtil.mutListener.listen(50893) ? (pos != 0) : (ListenerUtil.mutListener.listen(50892) ? (pos == 0) : (pos < 0))))))) {
                throw new ThreemaException("invalid csv header position");
            }
        }
        return this.write(pos, v);
    }

    public CSVRow write(int pos, Date v) throws ThreemaException {
        if (!ListenerUtil.mutListener.listen(50903)) {
            if ((ListenerUtil.mutListener.listen(50902) ? (this.data.length >= pos) : (ListenerUtil.mutListener.listen(50901) ? (this.data.length <= pos) : (ListenerUtil.mutListener.listen(50900) ? (this.data.length > pos) : (ListenerUtil.mutListener.listen(50899) ? (this.data.length != pos) : (ListenerUtil.mutListener.listen(50898) ? (this.data.length == pos) : (this.data.length < pos))))))) {
                throw new ThreemaException("invalid position to write [" + String.valueOf(pos) + "]");
            }
        }
        if (!ListenerUtil.mutListener.listen(50904)) {
            this.data[pos] = this.escape(v);
        }
        return this;
    }

    public CSVRow write(String fieldName, Object[] v) throws ThreemaException {
        int pos = this.getValuePosition(fieldName);
        if (!ListenerUtil.mutListener.listen(50910)) {
            if ((ListenerUtil.mutListener.listen(50909) ? (pos >= 0) : (ListenerUtil.mutListener.listen(50908) ? (pos <= 0) : (ListenerUtil.mutListener.listen(50907) ? (pos > 0) : (ListenerUtil.mutListener.listen(50906) ? (pos != 0) : (ListenerUtil.mutListener.listen(50905) ? (pos == 0) : (pos < 0))))))) {
                throw new ThreemaException("invalid csv header position");
            }
        }
        return this.write(pos, v);
    }

    public CSVRow write(int pos, Object[] v) throws ThreemaException {
        if (!ListenerUtil.mutListener.listen(50916)) {
            if ((ListenerUtil.mutListener.listen(50915) ? (this.data.length >= pos) : (ListenerUtil.mutListener.listen(50914) ? (this.data.length <= pos) : (ListenerUtil.mutListener.listen(50913) ? (this.data.length > pos) : (ListenerUtil.mutListener.listen(50912) ? (this.data.length != pos) : (ListenerUtil.mutListener.listen(50911) ? (this.data.length == pos) : (this.data.length < pos))))))) {
                throw new ThreemaException("invalid position to write [" + String.valueOf(pos) + "]");
            }
        }
        if (!ListenerUtil.mutListener.listen(50917)) {
            this.data[pos] = this.escape(v);
        }
        return this;
    }

    public String[] getStrings(int pos) throws ThreemaException {
        String r = this.getString(pos);
        return TestUtil.empty(r) ? new String[] {} : r.split(";");
    }

    public String getString(String fieldName) throws ThreemaException {
        int pos = this.getValuePosition(fieldName);
        if (!ListenerUtil.mutListener.listen(50923)) {
            if ((ListenerUtil.mutListener.listen(50922) ? (pos >= 0) : (ListenerUtil.mutListener.listen(50921) ? (pos <= 0) : (ListenerUtil.mutListener.listen(50920) ? (pos > 0) : (ListenerUtil.mutListener.listen(50919) ? (pos != 0) : (ListenerUtil.mutListener.listen(50918) ? (pos == 0) : (pos < 0))))))) {
                throw new ThreemaException("invalid csv header position [" + fieldName + "]");
            }
        }
        return this.getString(pos);
    }

    public Integer getInteger(String fieldName) throws ThreemaException {
        int pos = this.getValuePosition(fieldName);
        if (!ListenerUtil.mutListener.listen(50929)) {
            if ((ListenerUtil.mutListener.listen(50928) ? (pos >= 0) : (ListenerUtil.mutListener.listen(50927) ? (pos <= 0) : (ListenerUtil.mutListener.listen(50926) ? (pos > 0) : (ListenerUtil.mutListener.listen(50925) ? (pos != 0) : (ListenerUtil.mutListener.listen(50924) ? (pos == 0) : (pos < 0))))))) {
                throw new ThreemaException("invalid csv header position [" + fieldName + "]");
            }
        }
        return this.getInteger(pos);
    }

    public Boolean getBoolean(String fieldName) throws ThreemaException {
        int pos = this.getValuePosition(fieldName);
        if (!ListenerUtil.mutListener.listen(50935)) {
            if ((ListenerUtil.mutListener.listen(50934) ? (pos >= 0) : (ListenerUtil.mutListener.listen(50933) ? (pos <= 0) : (ListenerUtil.mutListener.listen(50932) ? (pos > 0) : (ListenerUtil.mutListener.listen(50931) ? (pos != 0) : (ListenerUtil.mutListener.listen(50930) ? (pos == 0) : (pos < 0))))))) {
                throw new ThreemaException("invalid csv header position [" + fieldName + "]");
            }
        }
        return this.getBoolean(pos);
    }

    public Date getDate(String fieldName) throws ThreemaException {
        int pos = this.getValuePosition(fieldName);
        if (!ListenerUtil.mutListener.listen(50941)) {
            if ((ListenerUtil.mutListener.listen(50940) ? (pos >= 0) : (ListenerUtil.mutListener.listen(50939) ? (pos <= 0) : (ListenerUtil.mutListener.listen(50938) ? (pos > 0) : (ListenerUtil.mutListener.listen(50937) ? (pos != 0) : (ListenerUtil.mutListener.listen(50936) ? (pos == 0) : (pos < 0))))))) {
                throw new ThreemaException("invalid csv header position [" + fieldName + "]");
            }
        }
        return this.getDate(pos);
    }

    public String[] getStrings(String fieldName) throws ThreemaException {
        int pos = this.getValuePosition(fieldName);
        if (!ListenerUtil.mutListener.listen(50947)) {
            if ((ListenerUtil.mutListener.listen(50946) ? (pos >= 0) : (ListenerUtil.mutListener.listen(50945) ? (pos <= 0) : (ListenerUtil.mutListener.listen(50944) ? (pos > 0) : (ListenerUtil.mutListener.listen(50943) ? (pos != 0) : (ListenerUtil.mutListener.listen(50942) ? (pos == 0) : (pos < 0))))))) {
                throw new ThreemaException("invalid csv header position [" + fieldName + "]");
            }
        }
        return this.getStrings(pos);
    }

    public int getValuePosition(String fieldName) {
        if (!ListenerUtil.mutListener.listen(50956)) {
            if ((ListenerUtil.mutListener.listen(50948) ? (this.header != null || fieldName != null) : (this.header != null && fieldName != null))) {
                if (!ListenerUtil.mutListener.listen(50955)) {
                    {
                        long _loopCounter579 = 0;
                        for (int n = 0; (ListenerUtil.mutListener.listen(50954) ? (n >= this.header.length) : (ListenerUtil.mutListener.listen(50953) ? (n <= this.header.length) : (ListenerUtil.mutListener.listen(50952) ? (n > this.header.length) : (ListenerUtil.mutListener.listen(50951) ? (n != this.header.length) : (ListenerUtil.mutListener.listen(50950) ? (n == this.header.length) : (n < this.header.length)))))); n++) {
                            ListenerUtil.loopListener.listen("_loopCounter579", ++_loopCounter579);
                            if (!ListenerUtil.mutListener.listen(50949)) {
                                if (fieldName.equals(this.header[n])) {
                                    return n;
                                }
                            }
                        }
                    }
                }
            }
        }
        return -1;
    }

    public void write() {
        if (!ListenerUtil.mutListener.listen(50958)) {
            if (this.writer != null) {
                if (!ListenerUtil.mutListener.listen(50957)) {
                    this.writer.writeNext(this.data);
                }
            }
        }
    }

    /**
     *  return a csv well formed string
     *  @param date
     *  @return
     */
    private String escape(Date date) {
        if (!ListenerUtil.mutListener.listen(50959)) {
            if (date == null) {
                return "";
            }
        }
        return String.valueOf(date.getTime());
    }

    /**
     *  return a csv well formed string
     *  @param bool
     *  @return
     */
    private String escape(boolean bool) {
        return bool ? "1" : "0";
    }

    /**
     *  return a csv well formed string
     *  @param ns
     *  @return
     */
    private String escape(String ns) {
        if (!ListenerUtil.mutListener.listen(50960)) {
            if (ns == null) {
                return "";
            }
        }
        return ns.replace("\\", "\\\\");
    }

    private String escape(Object[] os) {
        String result = "";
        if (!ListenerUtil.mutListener.listen(50970)) {
            if (os != null) {
                if (!ListenerUtil.mutListener.listen(50969)) {
                    {
                        long _loopCounter580 = 0;
                        for (Object o : os) {
                            ListenerUtil.loopListener.listen("_loopCounter580", ++_loopCounter580);
                            if (!ListenerUtil.mutListener.listen(50967)) {
                                if ((ListenerUtil.mutListener.listen(50965) ? (result.length() >= 0) : (ListenerUtil.mutListener.listen(50964) ? (result.length() <= 0) : (ListenerUtil.mutListener.listen(50963) ? (result.length() < 0) : (ListenerUtil.mutListener.listen(50962) ? (result.length() != 0) : (ListenerUtil.mutListener.listen(50961) ? (result.length() == 0) : (result.length() > 0))))))) {
                                    if (!ListenerUtil.mutListener.listen(50966)) {
                                        result += ';';
                                    }
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(50968)) {
                                result += this.escape(o);
                            }
                        }
                    }
                }
            }
        }
        return result;
    }

    /**
     *  return a csv well formed string
     *  @param ns
     *  @return
     */
    private String escape(Object ns) {
        if (!ListenerUtil.mutListener.listen(50971)) {
            if (ns == null) {
                return "";
            }
        }
        return this.escape(ns.toString());
    }
}
