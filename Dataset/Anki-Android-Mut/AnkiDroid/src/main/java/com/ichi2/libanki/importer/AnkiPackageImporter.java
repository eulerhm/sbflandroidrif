/**
 * ************************************************************************************
 *  Copyright (c) 2016 Houssam Salem <houssam.salem.au@gmail.com>                        *
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
package com.ichi2.libanki.importer;

import com.google.gson.stream.JsonReader;
import com.ichi2.anki.AnkiDroidApp;
import com.ichi2.anki.BackupManager;
import com.ichi2.anki.CollectionHelper;
import com.ichi2.anki.R;
import com.ichi2.anki.exception.ImportExportException;
import com.ichi2.libanki.Collection;
import com.ichi2.libanki.Storage;
import com.ichi2.libanki.Utils;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.compress.archivers.zip.ZipFile;
import timber.log.Timber;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

@SuppressWarnings({ "PMD.NPathComplexity" })
public class AnkiPackageImporter extends Anki2Importer {

    private ZipFile mZip;

    private Map<String, String> mNameToNum;

    public AnkiPackageImporter(Collection col, String file) {
        super(col, file);
    }

    @Override
    public void run() throws ImportExportException {
        if (!ListenerUtil.mutListener.listen(14186)) {
            publishProgress(0, 0, 0);
        }
        File tempDir = new File(new File(mCol.getPath()).getParent(), "tmpzip");
        // self.col into Anki.
        Collection tmpCol;
        if (!ListenerUtil.mutListener.listen(14187)) {
            Timber.d("Attempting to import package %s", mFile);
        }
        try {
            // validation than the desktop client to ensure the extracted collection is an apkg.
            String colname = "collection.anki21";
            try {
                // extract the deck from the zip file
                try {
                    if (!ListenerUtil.mutListener.listen(14196)) {
                        mZip = new ZipFile(new File(mFile));
                    }
                } catch (FileNotFoundException fileNotFound) {
                    if (!ListenerUtil.mutListener.listen(14195)) {
                        // The cache can be cleared between copying the file in and importing. This is temporary
                        if (fileNotFound.getMessage().contains("ENOENT")) {
                            if (!ListenerUtil.mutListener.listen(14194)) {
                                mLog.add(getRes().getString(R.string.import_log_file_cache_cleared));
                            }
                            return;
                        }
                    }
                    // displays: failed to unzip
                    throw fileNotFound;
                }
                if (!ListenerUtil.mutListener.listen(14198)) {
                    // v2 scheduler?
                    if (mZip.getEntry(colname) == null) {
                        if (!ListenerUtil.mutListener.listen(14197)) {
                            colname = CollectionHelper.COLLECTION_FILENAME;
                        }
                    }
                }
                // Make sure we have sufficient free space
                long uncompressedSize = Utils.calculateUncompressedSize(mZip);
                long availableSpace = Utils.determineBytesAvailable(mCol.getPath());
                if (!ListenerUtil.mutListener.listen(14199)) {
                    Timber.d("Total uncompressed size will be: %d", uncompressedSize);
                }
                if (!ListenerUtil.mutListener.listen(14200)) {
                    Timber.d("Total available size is:         %d", availableSpace);
                }
                if (!ListenerUtil.mutListener.listen(14208)) {
                    if ((ListenerUtil.mutListener.listen(14205) ? (uncompressedSize >= availableSpace) : (ListenerUtil.mutListener.listen(14204) ? (uncompressedSize <= availableSpace) : (ListenerUtil.mutListener.listen(14203) ? (uncompressedSize < availableSpace) : (ListenerUtil.mutListener.listen(14202) ? (uncompressedSize != availableSpace) : (ListenerUtil.mutListener.listen(14201) ? (uncompressedSize == availableSpace) : (uncompressedSize > availableSpace))))))) {
                        if (!ListenerUtil.mutListener.listen(14206)) {
                            Timber.e("Not enough space to unzip, need %d, available %d", uncompressedSize, availableSpace);
                        }
                        if (!ListenerUtil.mutListener.listen(14207)) {
                            mLog.add(getRes().getString(R.string.import_log_insufficient_space, uncompressedSize, availableSpace));
                        }
                        return;
                    }
                }
                // We follow how Anki does it and fix the problem here.
                HashMap<String, String> mediaToFileNameMap = new HashMap<>(1);
                if (!ListenerUtil.mutListener.listen(14209)) {
                    mediaToFileNameMap.put(colname, CollectionHelper.COLLECTION_FILENAME);
                }
                if (!ListenerUtil.mutListener.listen(14210)) {
                    Utils.unzipFiles(mZip, tempDir.getAbsolutePath(), new String[] { colname, "media" }, mediaToFileNameMap);
                }
                if (!ListenerUtil.mutListener.listen(14211)) {
                    colname = CollectionHelper.COLLECTION_FILENAME;
                }
            } catch (IOException e) {
                if (!ListenerUtil.mutListener.listen(14191)) {
                    Timber.e(e, "Failed to unzip apkg.");
                }
                if (!ListenerUtil.mutListener.listen(14192)) {
                    AnkiDroidApp.sendExceptionReport(e, "AnkiPackageImporter::run() - unzip");
                }
                if (!ListenerUtil.mutListener.listen(14193)) {
                    mLog.add(getRes().getString(R.string.import_log_failed_unzip, e.getLocalizedMessage()));
                }
                return;
            }
            String colpath = new File(tempDir, colname).getAbsolutePath();
            if (!ListenerUtil.mutListener.listen(14213)) {
                if (!(new File(colpath)).exists()) {
                    if (!ListenerUtil.mutListener.listen(14212)) {
                        mLog.add(getRes().getString(R.string.import_log_failed_copy_to, colpath));
                    }
                    return;
                }
            }
            tmpCol = Storage.Collection(mContext, colpath);
            try {
                if (!ListenerUtil.mutListener.listen(14217)) {
                    if (!tmpCol.validCollection()) {
                        if (!ListenerUtil.mutListener.listen(14216)) {
                            mLog.add(getRes().getString(R.string.import_log_failed_validate));
                        }
                        return;
                    }
                }
            } finally {
                if (!ListenerUtil.mutListener.listen(14215)) {
                    if (tmpCol != null) {
                        if (!ListenerUtil.mutListener.listen(14214)) {
                            tmpCol.close();
                        }
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(14218)) {
                mFile = colpath;
            }
            // number to use during the import
            File mediaMapFile = new File(tempDir, "media");
            if (!ListenerUtil.mutListener.listen(14219)) {
                // Number of file in mediamMMapFile as json. Not knowable
                mNameToNum = new HashMap<>();
            }
            String dirPath = tmpCol.getMedia().dir();
            File dir = new File(dirPath);
            // Number of file in mediamMMapFile as json. Not knowable
            Map<String, String> numToName = new HashMap<>();
            try (JsonReader jr = new JsonReader(new FileReader(mediaMapFile))) {
                if (!ListenerUtil.mutListener.listen(14222)) {
                    jr.beginObject();
                }
                // v in anki
                String name;
                // k in anki
                String num;
                {
                    long _loopCounter275 = 0;
                    while (jr.hasNext()) {
                        ListenerUtil.loopListener.listen("_loopCounter275", ++_loopCounter275);
                        num = jr.nextName();
                        name = jr.nextString();
                        File file = new File(dir, name);
                        if (!ListenerUtil.mutListener.listen(14223)) {
                            if (!Utils.isInside(file, dir)) {
                                throw (new RuntimeException("Invalid file"));
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(14224)) {
                            Utils.nfcNormalized(num);
                        }
                        if (!ListenerUtil.mutListener.listen(14225)) {
                            mNameToNum.put(name, num);
                        }
                        if (!ListenerUtil.mutListener.listen(14226)) {
                            numToName.put(num, name);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(14227)) {
                    jr.endObject();
                }
            } catch (FileNotFoundException e) {
                if (!ListenerUtil.mutListener.listen(14220)) {
                    Timber.e("Apkg did not contain a media dict. No media will be imported.");
                }
            } catch (IOException e) {
                if (!ListenerUtil.mutListener.listen(14221)) {
                    Timber.e("Malformed media dict. Media import will be incomplete.");
                }
            }
            if (!ListenerUtil.mutListener.listen(14228)) {
                // run anki2 importer
                super.run();
            }
            if (!ListenerUtil.mutListener.listen(14234)) {
                {
                    long _loopCounter276 = 0;
                    // import static media
                    for (Map.Entry<String, String> entry : mNameToNum.entrySet()) {
                        ListenerUtil.loopListener.listen("_loopCounter276", ++_loopCounter276);
                        String file = entry.getKey();
                        String c = entry.getValue();
                        if (!ListenerUtil.mutListener.listen(14230)) {
                            if ((ListenerUtil.mutListener.listen(14229) ? (!file.startsWith("_") || !file.startsWith("latex-")) : (!file.startsWith("_") && !file.startsWith("latex-")))) {
                                continue;
                            }
                        }
                        File path = new File(mCol.getMedia().dir(), Utils.nfcNormalized(file));
                        if (!ListenerUtil.mutListener.listen(14233)) {
                            if (!path.exists()) {
                                try {
                                    if (!ListenerUtil.mutListener.listen(14232)) {
                                        Utils.unzipFiles(mZip, mCol.getMedia().dir(), new String[] { c }, numToName);
                                    }
                                } catch (IOException e) {
                                    if (!ListenerUtil.mutListener.listen(14231)) {
                                        Timber.e("Failed to extract static media file. Ignoring.");
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } finally {
            long availableSpace = Utils.determineBytesAvailable(mCol.getPath());
            if (!ListenerUtil.mutListener.listen(14188)) {
                Timber.d("Total available size is: %d", availableSpace);
            }
            if (!ListenerUtil.mutListener.listen(14190)) {
                // Clean up our temporary files
                if (tempDir.exists()) {
                    if (!ListenerUtil.mutListener.listen(14189)) {
                        BackupManager.removeDir(tempDir);
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(14235)) {
            publishProgress(100, 100, 100);
        }
    }

    @Override
    protected BufferedInputStream _srcMediaData(String fname) {
        if (!ListenerUtil.mutListener.listen(14237)) {
            if (mNameToNum.containsKey(fname)) {
                try {
                    return new BufferedInputStream(mZip.getInputStream(mZip.getEntry(mNameToNum.get(fname))));
                } catch (IOException | NullPointerException e) {
                    if (!ListenerUtil.mutListener.listen(14236)) {
                        Timber.e("Could not extract media file %s from zip file.", fname);
                    }
                }
            }
        }
        return null;
    }
}
