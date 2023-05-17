package com.ichi2.libanki.importer;

import android.os.Build;
import android.text.TextUtils;
import com.ichi2.anki.R;
import com.ichi2.libanki.Collection;
import com.ichi2.libanki.importer.python.CsvDialect;
import com.ichi2.libanki.importer.python.CsvReader;
import com.ichi2.libanki.importer.python.CsvSniffer;
import org.jetbrains.annotations.Contract;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import timber.log.Timber;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

@RequiresApi(api = Build.VERSION_CODES.O)
public class TextImporter extends NoteImporter {

    private boolean mNeedDelimiter = true;

    final String mPatterns = "\t|,;:";

    private FileObj fileobj;

    private char delimiter;

    private String[] tagsToAdd;

    private CsvDialect dialect;

    private int numFields;

    private boolean mFirstLineWasTags;

    public TextImporter(Collection col, String file) {
        super(col, file);
        if (!ListenerUtil.mutListener.listen(14391)) {
            fileobj = null;
        }
        if (!ListenerUtil.mutListener.listen(14392)) {
            delimiter = '\0';
        }
        if (!ListenerUtil.mutListener.listen(14393)) {
            tagsToAdd = new String[0];
        }
    }

    @NonNull
    @Override
    protected List<ForeignNote> foreignNotes() {
        if (!ListenerUtil.mutListener.listen(14394)) {
            open();
        }
        // Number of element is reader's size
        List<String> log = new ArrayList<>();
        // Number of element is reader's size
        List<ForeignNote> notes = new ArrayList<>();
        int lineNum = 0;
        int ignored = 0;
        // Note: This differs from libAnki as we don't have csv.reader
        Iterator<String> data = getDataStream().iterator();
        CsvReader reader;
        if (delimiter != '\0') {
            reader = CsvReader.fromDelimiter(data, delimiter);
        } else {
            reader = CsvReader.fromDialect(data, dialect);
        }
        try {
            if (!ListenerUtil.mutListener.listen(14412)) {
                {
                    long _loopCounter286 = 0;
                    for (List<String> row : reader) {
                        ListenerUtil.loopListener.listen("_loopCounter286", ++_loopCounter286);
                        if (!ListenerUtil.mutListener.listen(14396)) {
                            if (row == null) {
                                continue;
                            }
                        }
                        List<String> rowAsString = new ArrayList<>(row);
                        if (!ListenerUtil.mutListener.listen(14410)) {
                            if ((ListenerUtil.mutListener.listen(14401) ? (rowAsString.size() >= numFields) : (ListenerUtil.mutListener.listen(14400) ? (rowAsString.size() <= numFields) : (ListenerUtil.mutListener.listen(14399) ? (rowAsString.size() > numFields) : (ListenerUtil.mutListener.listen(14398) ? (rowAsString.size() < numFields) : (ListenerUtil.mutListener.listen(14397) ? (rowAsString.size() == numFields) : (rowAsString.size() != numFields))))))) {
                                if (!ListenerUtil.mutListener.listen(14409)) {
                                    if ((ListenerUtil.mutListener.listen(14406) ? (rowAsString.size() >= 0) : (ListenerUtil.mutListener.listen(14405) ? (rowAsString.size() <= 0) : (ListenerUtil.mutListener.listen(14404) ? (rowAsString.size() < 0) : (ListenerUtil.mutListener.listen(14403) ? (rowAsString.size() != 0) : (ListenerUtil.mutListener.listen(14402) ? (rowAsString.size() == 0) : (rowAsString.size() > 0))))))) {
                                        String formatted = getString(R.string.csv_importer_error_invalid_field_count, TextUtils.join(" ", rowAsString), rowAsString.size(), numFields);
                                        if (!ListenerUtil.mutListener.listen(14407)) {
                                            log.add(formatted);
                                        }
                                        if (!ListenerUtil.mutListener.listen(14408)) {
                                            ignored += 1;
                                        }
                                    }
                                }
                                continue;
                            }
                        }
                        ForeignNote note = noteFromFields(rowAsString);
                        if (!ListenerUtil.mutListener.listen(14411)) {
                            notes.add(note);
                        }
                    }
                }
            }
        } catch (CsvException e) {
            if (!ListenerUtil.mutListener.listen(14395)) {
                log.add(getString(R.string.csv_importer_error_exception, e));
            }
        }
        if (!ListenerUtil.mutListener.listen(14413)) {
            mLog = log;
        }
        if (!ListenerUtil.mutListener.listen(14414)) {
            fileobj.close();
        }
        return notes;
    }

    /**
     * Number of fields.
     */
    @Override
    protected int fields() {
        if (!ListenerUtil.mutListener.listen(14415)) {
            open();
        }
        return numFields;
    }

    private ForeignNote noteFromFields(List<String> fields) {
        ForeignNote note = new ForeignNote();
        if (!ListenerUtil.mutListener.listen(14416)) {
            note.mFields.addAll(fields);
        }
        if (!ListenerUtil.mutListener.listen(14417)) {
            note.mTags.addAll(Arrays.asList(tagsToAdd));
        }
        return note;
    }

    /**
     * Parse the top line and determine the pattern and number of fields.
     */
    @Override
    protected void open() {
        if (!ListenerUtil.mutListener.listen(14418)) {
            // load & look for the right pattern
            cacheFile();
        }
    }

    /**
     * Read file into self.lines if not already there.
     */
    private void cacheFile() {
        if (!ListenerUtil.mutListener.listen(14420)) {
            if (fileobj == null) {
                if (!ListenerUtil.mutListener.listen(14419)) {
                    openFile();
                }
            }
        }
    }

    private void openFile() {
        if (!ListenerUtil.mutListener.listen(14421)) {
            dialect = null;
        }
        if (!ListenerUtil.mutListener.listen(14422)) {
            fileobj = FileObj.open(mFile);
        }
        String firstLine = getFirstFileLine().orElse(null);
        if (!ListenerUtil.mutListener.listen(14427)) {
            if (firstLine != null) {
                if (!ListenerUtil.mutListener.listen(14425)) {
                    if (firstLine.startsWith("tags:")) {
                        String tags = firstLine.substring("tags:".length()).trim();
                        if (!ListenerUtil.mutListener.listen(14423)) {
                            tagsToAdd = tags.split(" ");
                        }
                        if (!ListenerUtil.mutListener.listen(14424)) {
                            this.mFirstLineWasTags = true;
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(14426)) {
                    updateDelimiter();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(14429)) {
            if ((ListenerUtil.mutListener.listen(14428) ? (dialect == null || delimiter == '\0') : (dialect == null && delimiter == '\0'))) {
                throw new RuntimeException("unknownFormat");
            }
        }
    }

    @Contract(" -> fail")
    private void err() {
        throw new RuntimeException("unknownFormat");
    }

    private void updateDelimiter() {
        if (!ListenerUtil.mutListener.listen(14430)) {
            dialect = null;
        }
        CsvSniffer sniffer = new CsvSniffer();
        if (!ListenerUtil.mutListener.listen(14433)) {
            if (delimiter == '\0') {
                try {
                    String join = getLinesFromFile(10);
                    if (!ListenerUtil.mutListener.listen(14432)) {
                        dialect = sniffer.sniff(join, mPatterns.toCharArray());
                    }
                } catch (Exception e) {
                    try {
                        if (!ListenerUtil.mutListener.listen(14431)) {
                            dialect = sniffer.sniff(getFirstFileLine().orElse(""), mPatterns.toCharArray());
                        }
                    } catch (Exception ex) {
                    }
                }
            }
        }
        Iterator<String> data = getDataStream().iterator();
        CsvReader reader = null;
        if (!ListenerUtil.mutListener.listen(14443)) {
            if (dialect != null) {
                try {
                    if (!ListenerUtil.mutListener.listen(14442)) {
                        reader = CsvReader.fromDialect(data, dialect);
                    }
                } catch (Exception e) {
                    if (!ListenerUtil.mutListener.listen(14441)) {
                        err();
                    }
                }
            } else {
                // PERF: This starts the file read twice - whereas we only need the first line
                String firstLine = getFirstFileLine().orElse("");
                if (!ListenerUtil.mutListener.listen(14439)) {
                    if (delimiter == '\0') {
                        if (!ListenerUtil.mutListener.listen(14438)) {
                            if (firstLine.contains("\t")) {
                                if (!ListenerUtil.mutListener.listen(14437)) {
                                    delimiter = '\t';
                                }
                            } else if (firstLine.contains(";")) {
                                if (!ListenerUtil.mutListener.listen(14436)) {
                                    delimiter = ';';
                                }
                            } else if (firstLine.contains(",")) {
                                if (!ListenerUtil.mutListener.listen(14435)) {
                                    delimiter = ',';
                                }
                            } else {
                                if (!ListenerUtil.mutListener.listen(14434)) {
                                    delimiter = ' ';
                                }
                            }
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(14440)) {
                    reader = CsvReader.fromDelimiter(data, delimiter);
                }
            }
        }
        try {
            if (!ListenerUtil.mutListener.listen(14453)) {
                {
                    long _loopCounter287 = 0;
                    while (true) {
                        ListenerUtil.loopListener.listen("_loopCounter287", ++_loopCounter287);
                        List<String> row = reader.next();
                        if (!ListenerUtil.mutListener.listen(14452)) {
                            if ((ListenerUtil.mutListener.listen(14450) ? (row.size() >= 0) : (ListenerUtil.mutListener.listen(14449) ? (row.size() <= 0) : (ListenerUtil.mutListener.listen(14448) ? (row.size() < 0) : (ListenerUtil.mutListener.listen(14447) ? (row.size() != 0) : (ListenerUtil.mutListener.listen(14446) ? (row.size() == 0) : (row.size() > 0))))))) {
                                if (!ListenerUtil.mutListener.listen(14451)) {
                                    numFields = row.size();
                                }
                                break;
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(14444)) {
                Timber.e(e);
            }
            if (!ListenerUtil.mutListener.listen(14445)) {
                err();
            }
        }
        if (!ListenerUtil.mutListener.listen(14454)) {
            initMapping();
        }
    }

    /*
    In python:
    >>> pp(re.sub("^\#.*$", "__comment", "#\r\n"))
    '__comment\n'
    In Java:
    COMMENT_PATTERN.matcher("#\r\n").replaceAll("__comment") -> "__comment\r\n"
    So we use .DOTALL to ensure we get the \r
    */
    private static final Pattern COMMENT_PATTERN = Pattern.compile("^#.*$", Pattern.DOTALL);

    private String sub(String s) {
        return COMMENT_PATTERN.matcher(s).replaceAll("__comment");
    }

    private Stream<String> getDataStream() {
        Stream<String> data;
        try {
            data = fileobj.readAsUtf8WithoutBOM();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Stream<String> withoutComments = data.filter(x -> !"__comment".equals(sub(x))).map(s -> s + "\n");
        if (!ListenerUtil.mutListener.listen(14456)) {
            if (this.mFirstLineWasTags) {
                if (!ListenerUtil.mutListener.listen(14455)) {
                    withoutComments = withoutComments.skip(1);
                }
            }
        }
        return withoutComments;
    }

    private Optional<String> getFirstFileLine() {
        return getDataStream().findFirst();
    }

    private String getLinesFromFile(int numberOfLines) {
        return TextUtils.join("\n", getDataStream().limit(numberOfLines).collect(Collectors.toList()));
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private static class FileObj {

        private final File mFile;

        public FileObj(@NonNull File file) {
            this.mFile = file;
        }

        @NonNull
        public static FileObj open(@NonNull String mFile) {
            return new FileObj(new File(mFile));
        }

        public void close() {
        }

        @NonNull
        public Stream<String> readAsUtf8WithoutBOM() throws IOException {
            return Files.lines(Paths.get(mFile.getAbsolutePath()), StandardCharsets.UTF_8);
        }
    }
}
