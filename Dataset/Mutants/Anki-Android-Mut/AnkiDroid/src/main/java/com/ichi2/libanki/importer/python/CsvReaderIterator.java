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

 This file incorporates work covered by the following copyright and permission notice.
 Please see the file LICENSE in this directory for full details

 Ported from https://github.com/python/cpython/blob/c88239f864a27f673c0f0a9e62d2488563f9d081/Modules/_csv.c
 */
package com.ichi2.libanki.importer.python;

import com.ichi2.libanki.importer.CsvException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import timber.log.Timber;
import static com.ichi2.libanki.importer.python.CsvDialect.Quoting.*;
import static com.ichi2.libanki.importer.python.CsvReaderIterator.State.*;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class CsvReaderIterator implements Iterator<List<String>> {

    private final CsvReader reader;

    private int field_len;

    private State state;

    private int line_num;

    private List<String> fields;

    private int numeric_field;

    private static final int field_size = 5000;

    // These were modified from a bare array and size to a StringBuilder
    private final char[] field = new char[field_size];

    public CsvReaderIterator(@NonNull CsvReader reader) {
        this.reader = reader;
    }

    @Override
    public boolean hasNext() {
        return this.reader.input_iter.hasNext();
    }

    enum State {

        START_RECORD,
        START_FIELD,
        IN_QUOTED_FIELD,
        EAT_CRNL,
        AFTER_ESCAPED_CRNL,
        ESCAPED_CHAR,
        IN_FIELD,
        ESCAPE_IN_QUOTED_FIELD,
        QUOTE_IN_QUOTED_FIELD
    }

    private int parse_save_field() {
        // ignored field.length
        String field = new String(this.field, 0, this.field_len);
        if (!ListenerUtil.mutListener.listen(13304)) {
            this.field_len = 0;
        }
        if (!ListenerUtil.mutListener.listen(13311)) {
            if ((ListenerUtil.mutListener.listen(13309) ? (this.numeric_field >= 0) : (ListenerUtil.mutListener.listen(13308) ? (this.numeric_field <= 0) : (ListenerUtil.mutListener.listen(13307) ? (this.numeric_field > 0) : (ListenerUtil.mutListener.listen(13306) ? (this.numeric_field < 0) : (ListenerUtil.mutListener.listen(13305) ? (this.numeric_field == 0) : (this.numeric_field != 0))))))) {
                if (!ListenerUtil.mutListener.listen(13310)) {
                    Timber.w("skipping numeric field");
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(13312)) {
            this.fields.add(field);
        }
        return 0;
    }

    private int parse_add_char(char c) {
        if (!ListenerUtil.mutListener.listen(13318)) {
            // }
            if ((ListenerUtil.mutListener.listen(13317) ? (this.field_len >= field_size) : (ListenerUtil.mutListener.listen(13316) ? (this.field_len <= field_size) : (ListenerUtil.mutListener.listen(13315) ? (this.field_len > field_size) : (ListenerUtil.mutListener.listen(13314) ? (this.field_len < field_size) : (ListenerUtil.mutListener.listen(13313) ? (this.field_len != field_size) : (this.field_len == field_size)))))))
                return -1;
        }
        if (!ListenerUtil.mutListener.listen(13319)) {
            this.field[this.field_len++] = c;
        }
        return 0;
    }

    void parse_reset() {
        if (!ListenerUtil.mutListener.listen(13320)) {
            this.fields = new ArrayList<>();
        }
        if (!ListenerUtil.mutListener.listen(13321)) {
            this.field_len = 0;
        }
        if (!ListenerUtil.mutListener.listen(13322)) {
            this.state = START_RECORD;
        }
        if (!ListenerUtil.mutListener.listen(13323)) {
            this.numeric_field = 0;
        }
    }

    // noinspection ControlFlowStatementWithoutBraces
    // Copied from C code
    @SuppressWarnings({ "fallthrough", "RedundantSuppression" })
    private int parse_process_char(char c) {
        CsvDialect dialect = this.reader.dialect;
        if (!ListenerUtil.mutListener.listen(13458)) {
            switch(this.state) {
                case START_RECORD:
                    if (!ListenerUtil.mutListener.listen(13326)) {
                        /* start of record */
                        if (c == '\0')
                            /* empty line - return [] */
                            break;
                        else if ((ListenerUtil.mutListener.listen(13324) ? (c == '\n' && c == '\r') : (c == '\n' || c == '\r'))) {
                            if (!ListenerUtil.mutListener.listen(13325)) {
                                this.state = EAT_CRNL;
                            }
                            break;
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(13327)) {
                        /* normal character - handle as START_FIELD */
                        this.state = START_FIELD;
                    }
                /* fallthru */
                case START_FIELD:
                    if (!ListenerUtil.mutListener.listen(13356)) {
                        /* expecting field */
                        if ((ListenerUtil.mutListener.listen(13329) ? ((ListenerUtil.mutListener.listen(13328) ? (c == '\n' && c == '\r') : (c == '\n' || c == '\r')) && c == '\0') : ((ListenerUtil.mutListener.listen(13328) ? (c == '\n' && c == '\r') : (c == '\n' || c == '\r')) || c == '\0'))) {
                            if (!ListenerUtil.mutListener.listen(13354)) {
                                /* save empty field - return [fields] */
                                if ((ListenerUtil.mutListener.listen(13353) ? (parse_save_field() >= 0) : (ListenerUtil.mutListener.listen(13352) ? (parse_save_field() <= 0) : (ListenerUtil.mutListener.listen(13351) ? (parse_save_field() > 0) : (ListenerUtil.mutListener.listen(13350) ? (parse_save_field() != 0) : (ListenerUtil.mutListener.listen(13349) ? (parse_save_field() == 0) : (parse_save_field() < 0)))))))
                                    return -1;
                            }
                            if (!ListenerUtil.mutListener.listen(13355)) {
                                this.state = (c == '\0' ? START_RECORD : EAT_CRNL);
                            }
                        } else if ((ListenerUtil.mutListener.listen(13330) ? (c == dialect.mQuotechar || dialect.mQuoting != QUOTE_NONE) : (c == dialect.mQuotechar && dialect.mQuoting != QUOTE_NONE))) {
                            if (!ListenerUtil.mutListener.listen(13348)) {
                                /* start quoted field */
                                this.state = IN_QUOTED_FIELD;
                            }
                        } else if (c == dialect.mEscapechar) {
                            if (!ListenerUtil.mutListener.listen(13347)) {
                                /* possible escaped character */
                                this.state = ESCAPED_CHAR;
                            }
                        } else if ((ListenerUtil.mutListener.listen(13331) ? (c == ' ' || dialect.mSkipInitialSpace) : (c == ' ' && dialect.mSkipInitialSpace)))
                            /* ignore space at start of field */
                            ;
                        else if (c == dialect.mDelimiter) {
                            if (!ListenerUtil.mutListener.listen(13346)) {
                                /* save empty field */
                                if ((ListenerUtil.mutListener.listen(13345) ? (parse_save_field() >= 0) : (ListenerUtil.mutListener.listen(13344) ? (parse_save_field() <= 0) : (ListenerUtil.mutListener.listen(13343) ? (parse_save_field() > 0) : (ListenerUtil.mutListener.listen(13342) ? (parse_save_field() != 0) : (ListenerUtil.mutListener.listen(13341) ? (parse_save_field() == 0) : (parse_save_field() < 0)))))))
                                    return -1;
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(13333)) {
                                /* begin new unquoted field */
                                if (dialect.mQuoting == QUOTE_NONNUMERIC)
                                    if (!ListenerUtil.mutListener.listen(13332)) {
                                        this.numeric_field = 1;
                                    }
                            }
                            if (!ListenerUtil.mutListener.listen(13339)) {
                                if ((ListenerUtil.mutListener.listen(13338) ? (parse_add_char(c) >= 0) : (ListenerUtil.mutListener.listen(13337) ? (parse_add_char(c) <= 0) : (ListenerUtil.mutListener.listen(13336) ? (parse_add_char(c) > 0) : (ListenerUtil.mutListener.listen(13335) ? (parse_add_char(c) != 0) : (ListenerUtil.mutListener.listen(13334) ? (parse_add_char(c) == 0) : (parse_add_char(c) < 0)))))))
                                    return -1;
                            }
                            if (!ListenerUtil.mutListener.listen(13340)) {
                                this.state = IN_FIELD;
                            }
                        }
                    }
                    break;
                case ESCAPED_CHAR:
                    if (!ListenerUtil.mutListener.listen(13365)) {
                        if ((ListenerUtil.mutListener.listen(13357) ? (c == '\n' && c == '\r') : (c == '\n' || c == '\r'))) {
                            if (!ListenerUtil.mutListener.listen(13363)) {
                                if ((ListenerUtil.mutListener.listen(13362) ? (parse_add_char(c) >= 0) : (ListenerUtil.mutListener.listen(13361) ? (parse_add_char(c) <= 0) : (ListenerUtil.mutListener.listen(13360) ? (parse_add_char(c) > 0) : (ListenerUtil.mutListener.listen(13359) ? (parse_add_char(c) != 0) : (ListenerUtil.mutListener.listen(13358) ? (parse_add_char(c) == 0) : (parse_add_char(c) < 0)))))))
                                    return -1;
                            }
                            if (!ListenerUtil.mutListener.listen(13364)) {
                                this.state = AFTER_ESCAPED_CRNL;
                            }
                            break;
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(13367)) {
                        if (c == '\0')
                            if (!ListenerUtil.mutListener.listen(13366)) {
                                c = '\n';
                            }
                    }
                    if (!ListenerUtil.mutListener.listen(13373)) {
                        if ((ListenerUtil.mutListener.listen(13372) ? (parse_add_char(c) >= 0) : (ListenerUtil.mutListener.listen(13371) ? (parse_add_char(c) <= 0) : (ListenerUtil.mutListener.listen(13370) ? (parse_add_char(c) > 0) : (ListenerUtil.mutListener.listen(13369) ? (parse_add_char(c) != 0) : (ListenerUtil.mutListener.listen(13368) ? (parse_add_char(c) == 0) : (parse_add_char(c) < 0)))))))
                            return -1;
                    }
                    if (!ListenerUtil.mutListener.listen(13374)) {
                        this.state = IN_FIELD;
                    }
                    break;
                case AFTER_ESCAPED_CRNL:
                    if (!ListenerUtil.mutListener.listen(13375)) {
                        if (c == '\0')
                            break;
                    }
                case IN_FIELD:
                    if (!ListenerUtil.mutListener.listen(13399)) {
                        /* in unquoted field */
                        if ((ListenerUtil.mutListener.listen(13377) ? ((ListenerUtil.mutListener.listen(13376) ? (c == '\n' && c == '\r') : (c == '\n' || c == '\r')) && c == '\0') : ((ListenerUtil.mutListener.listen(13376) ? (c == '\n' && c == '\r') : (c == '\n' || c == '\r')) || c == '\0'))) {
                            if (!ListenerUtil.mutListener.listen(13397)) {
                                /* end of line - return [fields] */
                                if ((ListenerUtil.mutListener.listen(13396) ? (parse_save_field() >= 0) : (ListenerUtil.mutListener.listen(13395) ? (parse_save_field() <= 0) : (ListenerUtil.mutListener.listen(13394) ? (parse_save_field() > 0) : (ListenerUtil.mutListener.listen(13393) ? (parse_save_field() != 0) : (ListenerUtil.mutListener.listen(13392) ? (parse_save_field() == 0) : (parse_save_field() < 0)))))))
                                    return -1;
                            }
                            if (!ListenerUtil.mutListener.listen(13398)) {
                                this.state = (c == '\0' ? START_RECORD : EAT_CRNL);
                            }
                        } else if (c == dialect.mEscapechar) {
                            if (!ListenerUtil.mutListener.listen(13391)) {
                                /* possible escaped character */
                                this.state = ESCAPED_CHAR;
                            }
                        } else if (c == dialect.mDelimiter) {
                            if (!ListenerUtil.mutListener.listen(13389)) {
                                /* save field - wait for new field */
                                if ((ListenerUtil.mutListener.listen(13388) ? (parse_save_field() >= 0) : (ListenerUtil.mutListener.listen(13387) ? (parse_save_field() <= 0) : (ListenerUtil.mutListener.listen(13386) ? (parse_save_field() > 0) : (ListenerUtil.mutListener.listen(13385) ? (parse_save_field() != 0) : (ListenerUtil.mutListener.listen(13384) ? (parse_save_field() == 0) : (parse_save_field() < 0)))))))
                                    return -1;
                            }
                            if (!ListenerUtil.mutListener.listen(13390)) {
                                this.state = START_FIELD;
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(13383)) {
                                /* normal character - save in field */
                                if ((ListenerUtil.mutListener.listen(13382) ? (parse_add_char(c) >= 0) : (ListenerUtil.mutListener.listen(13381) ? (parse_add_char(c) <= 0) : (ListenerUtil.mutListener.listen(13380) ? (parse_add_char(c) > 0) : (ListenerUtil.mutListener.listen(13379) ? (parse_add_char(c) != 0) : (ListenerUtil.mutListener.listen(13378) ? (parse_add_char(c) == 0) : (parse_add_char(c) < 0)))))))
                                    return -1;
                            }
                        }
                    }
                    break;
                case IN_QUOTED_FIELD:
                    if (!ListenerUtil.mutListener.listen(13411)) {
                        /* in quoted field */
                        if (c == '\0')
                            ;
                        else if (c == dialect.mEscapechar) {
                            if (!ListenerUtil.mutListener.listen(13410)) {
                                /* Possible escape character */
                                this.state = ESCAPE_IN_QUOTED_FIELD;
                            }
                        } else if ((ListenerUtil.mutListener.listen(13400) ? (c == dialect.mQuotechar || dialect.mQuoting != QUOTE_NONE) : (c == dialect.mQuotechar && dialect.mQuoting != QUOTE_NONE))) {
                            if (!ListenerUtil.mutListener.listen(13409)) {
                                if (dialect.mDoublequote) {
                                    if (!ListenerUtil.mutListener.listen(13408)) {
                                        /* doublequote; " represented by "" */
                                        this.state = QUOTE_IN_QUOTED_FIELD;
                                    }
                                } else {
                                    if (!ListenerUtil.mutListener.listen(13407)) {
                                        /* end of quote part of field */
                                        this.state = IN_FIELD;
                                    }
                                }
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(13406)) {
                                /* normal character - save in field */
                                if ((ListenerUtil.mutListener.listen(13405) ? (parse_add_char(c) >= 0) : (ListenerUtil.mutListener.listen(13404) ? (parse_add_char(c) <= 0) : (ListenerUtil.mutListener.listen(13403) ? (parse_add_char(c) > 0) : (ListenerUtil.mutListener.listen(13402) ? (parse_add_char(c) != 0) : (ListenerUtil.mutListener.listen(13401) ? (parse_add_char(c) == 0) : (parse_add_char(c) < 0)))))))
                                    return -1;
                            }
                        }
                    }
                    break;
                case ESCAPE_IN_QUOTED_FIELD:
                    if (!ListenerUtil.mutListener.listen(13413)) {
                        if (c == '\0')
                            if (!ListenerUtil.mutListener.listen(13412)) {
                                c = '\n';
                            }
                    }
                    if (!ListenerUtil.mutListener.listen(13419)) {
                        if ((ListenerUtil.mutListener.listen(13418) ? (parse_add_char(c) >= 0) : (ListenerUtil.mutListener.listen(13417) ? (parse_add_char(c) <= 0) : (ListenerUtil.mutListener.listen(13416) ? (parse_add_char(c) > 0) : (ListenerUtil.mutListener.listen(13415) ? (parse_add_char(c) != 0) : (ListenerUtil.mutListener.listen(13414) ? (parse_add_char(c) == 0) : (parse_add_char(c) < 0)))))))
                            return -1;
                    }
                    if (!ListenerUtil.mutListener.listen(13420)) {
                        this.state = IN_QUOTED_FIELD;
                    }
                    break;
                case QUOTE_IN_QUOTED_FIELD:
                    if (!ListenerUtil.mutListener.listen(13453)) {
                        /* doublequote - seen a quote in a quoted field */
                        if ((ListenerUtil.mutListener.listen(13421) ? (dialect.mQuoting != QUOTE_NONE || c == dialect.mQuotechar) : (dialect.mQuoting != QUOTE_NONE && c == dialect.mQuotechar))) {
                            if (!ListenerUtil.mutListener.listen(13451)) {
                                /* save "" as " */
                                if ((ListenerUtil.mutListener.listen(13450) ? (parse_add_char(c) >= 0) : (ListenerUtil.mutListener.listen(13449) ? (parse_add_char(c) <= 0) : (ListenerUtil.mutListener.listen(13448) ? (parse_add_char(c) > 0) : (ListenerUtil.mutListener.listen(13447) ? (parse_add_char(c) != 0) : (ListenerUtil.mutListener.listen(13446) ? (parse_add_char(c) == 0) : (parse_add_char(c) < 0)))))))
                                    return -1;
                            }
                            if (!ListenerUtil.mutListener.listen(13452)) {
                                this.state = IN_QUOTED_FIELD;
                            }
                        } else if (c == dialect.mDelimiter) {
                            if (!ListenerUtil.mutListener.listen(13444)) {
                                /* save field - wait for new field */
                                if ((ListenerUtil.mutListener.listen(13443) ? (parse_save_field() >= 0) : (ListenerUtil.mutListener.listen(13442) ? (parse_save_field() <= 0) : (ListenerUtil.mutListener.listen(13441) ? (parse_save_field() > 0) : (ListenerUtil.mutListener.listen(13440) ? (parse_save_field() != 0) : (ListenerUtil.mutListener.listen(13439) ? (parse_save_field() == 0) : (parse_save_field() < 0)))))))
                                    return -1;
                            }
                            if (!ListenerUtil.mutListener.listen(13445)) {
                                this.state = START_FIELD;
                            }
                        } else if ((ListenerUtil.mutListener.listen(13423) ? ((ListenerUtil.mutListener.listen(13422) ? (c == '\n' && c == '\r') : (c == '\n' || c == '\r')) && c == '\0') : ((ListenerUtil.mutListener.listen(13422) ? (c == '\n' && c == '\r') : (c == '\n' || c == '\r')) || c == '\0'))) {
                            if (!ListenerUtil.mutListener.listen(13437)) {
                                /* end of line - return [fields] */
                                if ((ListenerUtil.mutListener.listen(13436) ? (parse_save_field() >= 0) : (ListenerUtil.mutListener.listen(13435) ? (parse_save_field() <= 0) : (ListenerUtil.mutListener.listen(13434) ? (parse_save_field() > 0) : (ListenerUtil.mutListener.listen(13433) ? (parse_save_field() != 0) : (ListenerUtil.mutListener.listen(13432) ? (parse_save_field() == 0) : (parse_save_field() < 0)))))))
                                    return -1;
                            }
                            if (!ListenerUtil.mutListener.listen(13438)) {
                                this.state = (c == '\0' ? START_RECORD : EAT_CRNL);
                            }
                        } else if (!dialect.mStrict) {
                            if (!ListenerUtil.mutListener.listen(13430)) {
                                if ((ListenerUtil.mutListener.listen(13429) ? (parse_add_char(c) >= 0) : (ListenerUtil.mutListener.listen(13428) ? (parse_add_char(c) <= 0) : (ListenerUtil.mutListener.listen(13427) ? (parse_add_char(c) > 0) : (ListenerUtil.mutListener.listen(13426) ? (parse_add_char(c) != 0) : (ListenerUtil.mutListener.listen(13425) ? (parse_add_char(c) == 0) : (parse_add_char(c) < 0)))))))
                                    return -1;
                            }
                            if (!ListenerUtil.mutListener.listen(13431)) {
                                this.state = IN_FIELD;
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(13424)) {
                                /* illegal */
                                Timber.w("'%c' expected after '%c'", dialect.mDelimiter, dialect.mQuotechar);
                            }
                            return -1;
                        }
                    }
                    break;
                case EAT_CRNL:
                    if (!ListenerUtil.mutListener.listen(13457)) {
                        if ((ListenerUtil.mutListener.listen(13454) ? (c == '\n' && c == '\r') : (c == '\n' || c == '\r')))
                            ;
                        else if (c == '\0') {
                            if (!ListenerUtil.mutListener.listen(13456)) {
                                this.state = START_RECORD;
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(13455)) {
                                Timber.w("new-line character seen in unquoted field - do you need to open the file in universal-newline mode?");
                            }
                            return -1;
                        }
                    }
                    break;
            }
        }
        return 0;
    }

    @Override
    @Nullable
    public List<String> next() {
        if (!ListenerUtil.mutListener.listen(13459)) {
            parse_reset();
        }
        if (!ListenerUtil.mutListener.listen(13495)) {
            {
                long _loopCounter240 = 0;
                do {
                    ListenerUtil.loopListener.listen("_loopCounter240", ++_loopCounter240);
                    if (!ListenerUtil.mutListener.listen(13473)) {
                        if (!reader.input_iter.hasNext()) {
                            if (!ListenerUtil.mutListener.listen(13472)) {
                                if ((ListenerUtil.mutListener.listen(13465) ? ((ListenerUtil.mutListener.listen(13464) ? (this.field_len >= 0) : (ListenerUtil.mutListener.listen(13463) ? (this.field_len <= 0) : (ListenerUtil.mutListener.listen(13462) ? (this.field_len > 0) : (ListenerUtil.mutListener.listen(13461) ? (this.field_len < 0) : (ListenerUtil.mutListener.listen(13460) ? (this.field_len == 0) : (this.field_len != 0)))))) && this.state == IN_QUOTED_FIELD) : ((ListenerUtil.mutListener.listen(13464) ? (this.field_len >= 0) : (ListenerUtil.mutListener.listen(13463) ? (this.field_len <= 0) : (ListenerUtil.mutListener.listen(13462) ? (this.field_len > 0) : (ListenerUtil.mutListener.listen(13461) ? (this.field_len < 0) : (ListenerUtil.mutListener.listen(13460) ? (this.field_len == 0) : (this.field_len != 0)))))) || this.state == IN_QUOTED_FIELD))) {
                                    if (!ListenerUtil.mutListener.listen(13471)) {
                                        if (this.reader.dialect.mStrict) {
                                            throw new CsvException("unexpected end of data");
                                        } else if ((ListenerUtil.mutListener.listen(13470) ? (parse_save_field() <= 0) : (ListenerUtil.mutListener.listen(13469) ? (parse_save_field() > 0) : (ListenerUtil.mutListener.listen(13468) ? (parse_save_field() < 0) : (ListenerUtil.mutListener.listen(13467) ? (parse_save_field() != 0) : (ListenerUtil.mutListener.listen(13466) ? (parse_save_field() == 0) : (parse_save_field() >= 0))))))) {
                                            break;
                                        }
                                    }
                                }
                            }
                        }
                    }
                    String lineobj = this.reader.input_iter.next();
                    if (!ListenerUtil.mutListener.listen(13474)) {
                        line_num++;
                    }
                    int pos = 0;
                    int linelen = lineobj.length();
                    if (!ListenerUtil.mutListener.listen(13488)) {
                        {
                            long _loopCounter239 = 0;
                            while ((ListenerUtil.mutListener.listen(13487) ? (linelen-- >= 0) : (ListenerUtil.mutListener.listen(13486) ? (linelen-- <= 0) : (ListenerUtil.mutListener.listen(13485) ? (linelen-- < 0) : (ListenerUtil.mutListener.listen(13484) ? (linelen-- != 0) : (ListenerUtil.mutListener.listen(13483) ? (linelen-- == 0) : (linelen-- > 0))))))) {
                                ListenerUtil.loopListener.listen("_loopCounter239", ++_loopCounter239);
                                char c = lineobj.charAt(pos);
                                if (!ListenerUtil.mutListener.listen(13475)) {
                                    if (c == '\0') {
                                        throw new CsvException("line contains NUL");
                                    }
                                }
                                if (!ListenerUtil.mutListener.listen(13481)) {
                                    if ((ListenerUtil.mutListener.listen(13480) ? (parse_process_char(c) >= 0) : (ListenerUtil.mutListener.listen(13479) ? (parse_process_char(c) <= 0) : (ListenerUtil.mutListener.listen(13478) ? (parse_process_char(c) > 0) : (ListenerUtil.mutListener.listen(13477) ? (parse_process_char(c) != 0) : (ListenerUtil.mutListener.listen(13476) ? (parse_process_char(c) == 0) : (parse_process_char(c) < 0))))))) {
                                        // error
                                        return null;
                                    }
                                }
                                if (!ListenerUtil.mutListener.listen(13482)) {
                                    pos++;
                                }
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(13494)) {
                        if ((ListenerUtil.mutListener.listen(13493) ? (parse_process_char('\0') >= 0) : (ListenerUtil.mutListener.listen(13492) ? (parse_process_char('\0') <= 0) : (ListenerUtil.mutListener.listen(13491) ? (parse_process_char('\0') > 0) : (ListenerUtil.mutListener.listen(13490) ? (parse_process_char('\0') != 0) : (ListenerUtil.mutListener.listen(13489) ? (parse_process_char('\0') == 0) : (parse_process_char('\0') < 0))))))) {
                            return null;
                        }
                    }
                } while (state != START_RECORD);
            }
        }
        List<String> fields = this.fields;
        if (!ListenerUtil.mutListener.listen(13496)) {
            this.fields = null;
        }
        return fields;
    }
}
